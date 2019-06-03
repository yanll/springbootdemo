package com.cmp.study.springdemo.service;

import com.cmp.study.springdemo.bean.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private final static Logger logger = LoggerFactory.getLogger(MyInvocationSecurityMetadataSource.class);


    /**
     * 加载资源，初始化资源变量
     */
    @PostConstruct
    public void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Permission> resources = new ArrayList<>();
            resources.add(new Permission(10001L, "MAIN", "/main", "GET"));
            resources.add(new Permission(10002L, "MENU", "/menu", "GET"));
            resources.add(new Permission(10002L, "INDEX", "/index", "GET"));
            for (Permission resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                ConfigAttribute configAttribute = new SecurityConfig(resource.getUrl() + ":" + resource.getMethod());
                configAttributes.add(configAttribute);
                resourceMap.put(resource.getUrl(), configAttributes);
            }
        }
        logger.info("security info load success!!");
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (resourceMap == null) loadResourceDefine();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        Class classzz = request.getClass();

        RequestMapping annotation = request.getClass().getAnnotation(RequestMapping.class);
        String requestUrl = filterInvocation.getRequestUrl();
        Collection<ConfigAttribute> c = resourceMap.get(requestUrl);
        return c;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}