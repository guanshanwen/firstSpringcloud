package com.gbqd.service.member;

import com.gbqd.pojo.CsMember;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MrWen
 * @create 2019-01-15-11:15
 */
public interface MemberService {
    @RequestMapping("/insert")
    public void insert( CsMember member);

    @RequestMapping("/update")
    public void update(@RequestParam(value = "member", required = false) CsMember member) ;

    @RequestMapping("/find")
    public CsMember find(@RequestParam(value = "id", required = false) int id) ;

    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "id", required = false) int id);
    /**
     * @param PageNum 页数
     * @param pageSize 每页几条
     * @@describe: 分页例子
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.CsMember>
     * @date: 2019/1/17 13:53
     */
   /* @RequestMapping("/findList")
    public PageInfo<CsMember> findList(@RequestParam("PageNum") int PageNum,@RequestParam("pageSize") int pageSize);
*/
    /**
     * @param name 姓名
     * @param age  年龄
     * @@describe: 返回用户信息接口
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 11:23
     */
    @RequestMapping("/gerMember")
    public String gerMember(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age);


}
