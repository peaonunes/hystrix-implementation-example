package com.peao.nunes.hystrix.workers;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.util.HashMap;
import java.io.*;

public class HTTPRequesterWorker {
    private static final String RESPONSE_MESSAGE = "Status code: %d from %s";
    private static String URL;
    private static HttpClient CLIENT;
    private static GetMethod GETMETHOD;
    
    public HTTPRequesterWorker(String url) {
        this.URL = url;
        this.CLIENT = new HttpClient();
        this.buildGetMethod();
    }

    public HashMap<String, String> healthCheck() throws Exception {
        HashMap<String, String> response = this.buildResponse();
        int statusCode = 0;

        try {
            statusCode = CLIENT.executeMethod(this.GETMETHOD);
            response.replace("message", String.format(RESPONSE_MESSAGE, statusCode, this.URL));
        } catch (HttpException e) {
            response.replace("message", "HTTP ERROR");
            response.replace("exception", e.toString());
        } catch (IOException e) {
            response.replace("message", "IO ERROR");
            response.replace("exception", e.toString());
        } finally {
            this.GETMETHOD.releaseConnection();
            return response;
        }
    }

    private static HashMap<String, String> buildResponse() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("message", "");
        hashMap.put("exception", "");
        return hashMap;
    }

    private void buildGetMethod() {
        this.GETMETHOD = new GetMethod(this.URL);
        this.configureRetry();
    }

    private void configureRetry() {
        this.GETMETHOD.getParams()
                .setParameter(
                        HttpMethodParams.RETRY_HANDLER,
                        new DefaultHttpMethodRetryHandler(0, false));
    }
}