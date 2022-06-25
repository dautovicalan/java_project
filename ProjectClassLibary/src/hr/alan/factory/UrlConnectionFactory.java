/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Alan
 */
public class UrlConnectionFactory {
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String USER_AGENT = "User-Agent";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String MOZILLA = "Mozilla/5.0";
    private static final String XML_TYPE = "text/xml; charset=utf-8";
    
    private UrlConnectionFactory(){
    }
    
    public static HttpURLConnection getHttpUrlConnection(String path) throws MalformedURLException, IOException {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(TIMEOUT);
        con.setReadTimeout(TIMEOUT);
        con.setRequestMethod(REQUEST_METHOD);
        con.addRequestProperty(USER_AGENT, MOZILLA);
        con.addRequestProperty(CONTENT_TYPE, XML_TYPE);
        return con;
    }
}
