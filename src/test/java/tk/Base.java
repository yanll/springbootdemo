//package com.yeepay.g3.app.wflboss;
//
//
//import com.yeepay.g3.core.yuia.yuiacommons.patron.AuthMetaData;
//import com.yeepay.g3.core.yuia.yuiacommons.patron.RequiresAuthorization;
//import com.yeepay.g3.core.yuia.yuiacommons.patron.RequiresGuest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
//import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@ContextConfiguration(classes = {FMCBossApplication.class})
//public class Base {
//
//    @Before
//    public void setUp() {
//    }
//
//
//    @Autowired
//    protected ApplicationContext ctx;
//
//    @Test
//    public void initfunction() {
//        List<String> list = new ArrayList<String>();
//        RequestMappingHandlerMapping bean = ctx.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
//        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
//            PatternsRequestCondition prc = rmi.getPatternsCondition();
//            RequestMethodsRequestCondition rrc = rmi.getMethodsCondition();
//            Set<String> patterns = prc.getPatterns();
//            Set<RequestMethod> methods = rrc.getMethods();
//            HandlerMethod method = handlerMethods.get(rmi);
//            String system_code = "##@_@##";
//            String menu_code = "##@_@##";
//            RequiresGuest requiresGuest = method.getMethodAnnotation(RequiresGuest.class);
//            boolean controlled = true;
//            if (requiresGuest != null) {
//                controlled = false;
//            }
//            RequiresAuthorization requiresAuthorization = method.getMethodAnnotation(RequiresAuthorization.class);
//            if (requiresAuthorization != null && requiresAuthorization.required() == false) {
//                controlled = false;
//            }
//            AuthMetaData metaData = method.getMethodAnnotation(AuthMetaData.class);
//            if (metaData != null) {
//                system_code = metaData.system_code();
//                menu_code = metaData.menu_code();
//            }
//            for (String p : patterns) {
//                for (RequestMethod m : methods) {
//                    String uri = p + ":" + m.name();
//                    String sql = "insert into tbl_function(system_code,function_name,url,method,menu_id) select '" + system_code + "', '" + rmi.getName() + "','" + p + "','" + m.name() + "',id from tbl_menu where menu_code='" + menu_code + "' and system_code='" + system_code + "';";
////                    if (controlled) {
//                        System.out.println(sql);
//                        list.add(sql);
////                    }
//                }
//            }
//        }
//    }
//
//
//}
