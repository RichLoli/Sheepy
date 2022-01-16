package com.macoloco.sheepy.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String passwd = encoder.encode("123");
        User user = new User(username, passwd,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }

}
