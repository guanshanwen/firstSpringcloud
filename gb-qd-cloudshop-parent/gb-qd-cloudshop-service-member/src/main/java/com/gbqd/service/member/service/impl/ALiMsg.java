package com.gbqd.service.member.service.impl;

/**
 * @author MrWen
 * @create 2019-02-12-16:27
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsMemberMapper;
import com.gbqd.pojo.member.CsMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 阿里大于注册短信验证
 *
 * @author Administrator
 */
@RestController
public class ALiMsg {
    @Autowired
    CsMemberMapper csMemberMapper;
    // 阿里大于key secret
//替换成你的AK
    static final String accessKeyId = "LTAId44bsEe5IkZF";//你的accessKeyId
    static final String accessKeySecret = "wbj8KiMrQPFH03HsWXnjOVfiGPT3vR";//你的accessKeySecret
    static final String url = "http://gw.api.taobao.com/router/rest";

    //telephone 电话号码             type 1.注册  2.修改密码 3登录 4绑卡身份验证
    public ResultCode<Integer> sendSmsCode(@RequestParam(value = "phone")String phone, @RequestParam(value = "type")Integer type) throws ClientException {
        CsMember mem =csMemberMapper.selectByPhone(phone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        ResultCode<Integer> rc = new ResultCode<Integer>();
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("Gbei云店");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        SendSmsResponse sendSmsResponse = null;
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"" + code + "\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //如果type==1 为注册
        if (type == 1) {
            if(mem!=null){
                rc.setStatus(ResultCodeStatus.FAIL);
                rc.setMsg("此手机号已存在");
                return rc;
            }
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_157449283");
         }else if(type == 3){
            if(mem==null){
                rc.setStatus(ResultCodeStatus.FAIL);
                rc.setMsg("该用户不存在,请注册");
                return rc;
            }
            request.setTemplateCode("SMS_157454281");
        }

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("短信获取失败");
        } catch (ServerException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("短信获取失败");

        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("短信获取失败");

        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("短信获取成功");
            rc.setContent(code);

            //请求成功
        } else if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")) {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("操作过于频繁");
        }
        return rc;
        }
    }

