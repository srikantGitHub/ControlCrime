package com.srikant.controlcrime.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Srikant on 4/21/2018.
 */

public class RestClient {
    String requestURL="";
    private HttpURLConnection httpConn;
    public void sendGetRequest(String apendurl, Map<String, String> params,ResultHandler result)throws Exception {
        StringBuffer requestParams=null;
        requestParams = stringParams(params);
        URL url = new URL(requestURL+apendurl+"?"+requestParams);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setUseCaches(false);
        httpConn.setDoInput(true);
        httpConn.setDoOutput(false);
        responseData(result);
    }
    public void  sendPostRequest(String apendurl, Map<String, String> params,ResultHandler result) throws Exception {
        URL url = new URL(requestURL+apendurl);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setUseCaches(false);
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        StringBuffer requestParams =stringParams(params);
        OutputStreamWriter writer = new OutputStreamWriter( httpConn.getOutputStream());
        writer.write(requestParams.toString());
        writer.flush();
       responseData(result);
    }

    private StringBuffer stringParams(Map<String, String> params) throws Exception {
        StringBuffer requestParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
                if(params.size() >1)
                    requestParams.append("&");
            }
        }
        return requestParams;
    }
    public void responseData(ResultHandler result) throws Exception {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null){sb.append(line + "\n");}
        reader.close();
        httpConn.disconnect();
        result.OnSuccess(String.valueOf(sb));
    }
    public interface ResultHandler{
        public String OnSuccess(String result);
    }
}
