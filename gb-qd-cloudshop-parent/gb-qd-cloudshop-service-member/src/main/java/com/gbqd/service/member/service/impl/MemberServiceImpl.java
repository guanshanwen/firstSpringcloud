package com.gbqd.service.member.service.impl;

import com.gbqd.common.utils.RedisUtil;
import com.gbqd.mapper.CsMemberMapper;
import com.gbqd.pojo.CsMember;
import com.gbqd.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrWen
 * @create 2019-01-15-13:35
 */
@RestController
public class MemberServiceImpl implements MemberService {
    @Autowired
    CsMemberMapper csMemberMapper;
    @Autowired
    RedisUtil redisUtil;
    @Value("${server.port}")
    String port;  //在application.yml文件里赋的值
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);



    @Override
    public void insert(CsMember member) {
        csMemberMapper.insert(member);
    }

    @Override
    public void update(@RequestParam(value = "member", required = false) CsMember member) {
        csMemberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public CsMember find(@RequestParam(value = "id", required = false) int id) {
        return csMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(@RequestParam(value = "id", required = false) int id) {
        csMemberMapper.deleteByPrimaryKey(id);
    }

   /**
     * @param PageNum 页数
     * @param pageSize 每页几条
     * @@describe: 分页例子
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.CsMember>
     * @date: 2019/1/17 13:53
     */
   /* @Override
    public PageInfo<CsMember> findList(@RequestParam("PageNum") int PageNum,@RequestParam("pageSize") int pageSize) {

        PageHelper.startPage(PageNum, pageSize);
        List<CsMember> listMember = csMemberMapper.selectAll();
        PageInfo<CsMember> pageInfoList = new PageInfo<CsMember>(listMember);
        return pageInfoList;
    }*/

    /**
     * @param name 姓名
     * @param age  年龄
     * @@describe: 返回用户信息接口实现类
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 13:35
     */
    @Override
    public String gerMember(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age) {
       redisUtil.set("name","郑国强");
        redisUtil.expire("name",20);
        String msg = "通过member接口 获得name:" + name + "    和 age" + age + "  参数        接口:" + port;
        return msg;

    }
}
