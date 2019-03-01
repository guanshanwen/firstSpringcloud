package com.gbqd.securityg.config;

import com.gbqd.mapper.CsMemberMapper;
import com.gbqd.pojo.member.CsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 自定义用户认证
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Resource
    private CsMemberMapper csMemberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username); //任意登录用户名

        CsMember user = csMemberMapper.selectByPhone(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("User name" + username + "not find!!");
        }
        userInfo.setPassword(user.getPwd()); //从数据库获取密码

      /*  Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName()); //用户拥有的角色
            authoritiesSet.add(simpleGrantedAuthority);
        }
        userInfo.setAuthorities(authoritiesSet);*/

        return userInfo;
    }
}
