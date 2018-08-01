package com.cmp.study.springdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;


@RestController
@RequestMapping(value = "/https")
public class HttpsController {


    private static final Logger logger = LoggerFactory.getLogger(HttpsController.class);


    @RequestMapping(value = "/rec", method = RequestMethod.POST)
    public String rec() {
        return "rec ok";
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "ping ok!";
    }

    public static void main(String[] args) throws Exception {
        long s = System.currentTimeMillis();
        String msg = "hello";
        PrintStream wr = null;

        logger.info("start_createConnection " + s + " ");
        URL httpsUrl = new URL("https://192.168.13.130:8080/https/rec");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpsUrl.openConnection();
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setConnectTimeout(60000);
        httpsURLConnection.setReadTimeout(60000);
        httpsURLConnection.setRequestProperty("Connection", "keep-alive");
        httpsURLConnection.setRequestProperty("Content-Type", "application/xml;charset=" + "UTF-8");
        httpsURLConnection.setRequestProperty("Content-Length", msg.length() + "");
        SSLContext sSlContext = SSLContext.getInstance("TLS");
        sSlContext.init(null, new TrustManager[]{new BaseHttpSSLSocketFactory.MyX509TrustManager()}, null);
        SSLEngine sSLEngine = sSlContext.createSSLEngine();
        sSLEngine.setEnabledProtocols(new String[]{"TLSv1.2"});
        sSLEngine.setEnabledCipherSuites(new String[]{"TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_256_GCM_SHA384"});
        SSLSocketFactory sSLSocketFactory = sSlContext.getSocketFactory();
        httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
        TrustAnyHostName trustAnyHostName = new TrustAnyHostName();
        httpsURLConnection.setHostnameVerifier(trustAnyHostName);
        HttpsURLConnection.setDefaultHostnameVerifier(trustAnyHostName);
        httpsURLConnection.connect();


        wr = new PrintStream(httpsURLConnection.getOutputStream(), false, "UTF-8");
        wr.print("?a=hello");
        wr.flush();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8"));
        StringBuilder responseBuilder = new StringBuilder();
        String line = reader.readLine();
        while (null != line) {
            responseBuilder.append(line);
            line = reader.readLine();
            if (null != line) {
                responseBuilder.append("\r\n");
            }
        }
        String responseMsg = responseBuilder.toString();
        logger.info((System.currentTimeMillis() - s) + " ms response_xml_{}", responseMsg);
        if (null != reader) {
            try {
                reader.close();
            } catch (IOException e) {
                logger.info("", e);
            }
        }


        logger.info("httpsURLConnection_connected " + (System.currentTimeMillis() - s) + " ms ");

    }


}
