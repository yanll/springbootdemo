package com.cmp.study.springdemo.web;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrustAnyHostName implements HostnameVerifier {

        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }

    }