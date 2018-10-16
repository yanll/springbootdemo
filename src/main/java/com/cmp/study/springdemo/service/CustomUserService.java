package com.cmp.study.springdemo.service;

import com.cmp.study.springdemo.bean.MyGrantedAuthority;
import com.cmp.study.springdemo.bean.Permission;
import com.cmp.study.springdemo.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = new SysUser(10001L, "admin", "{bcrypt}$2a$10$Br41d1DCzfWGHVJu/rYrOeWHXin80ZVvBl0GA526RgLrd8HiWx/ki");
        if (user != null) {
            List<Permission> permissions = new ArrayList<>();
            permissions.add(new Permission(10001L, "MAIN", "/main", "GET"));
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }


}
