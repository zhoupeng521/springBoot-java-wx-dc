package com.tts.logdome.common.nio;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * http get post 请求
 */
public class HttpTest {

    public static void main(String[] args) {

        String result = "";
        PrintWriter printWriter = null;
        BufferedReader reader = null;
        try{
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/token");

            URLConnection urlConnection = url.openConnection();

            urlConnection.setDoOutput(true);

            //建立实际连接
            urlConnection.connect();

            OutputStream outputStream = urlConnection.getOutputStream();

            printWriter = new PrintWriter(outputStream);
            //发出请求参数：grant_type=client_credential&appid=APPID&secret=APPSECRET
            String jsonString = JSON.toJSONString(new WeixinConfig());
            System.out.println(jsonString);
            printWriter.print(jsonString);

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line ;

            while ((line = reader.readLine()) != null){
                result += line;
            }
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            if(null != printWriter){
                printWriter.close();
            }

            if(null != reader ){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class WeixinConfig implements Serializable{

        private String grant_type;

        private String appid;

        private String secret;

        WeixinConfig(){
            this.grant_type = "client_credential";
            this.appid = "wx7bd9355a6a6345a8";
            this.secret = "78641dca3fd57c2a799c65a818c7a7ed";
        }

        public String getGrant_type() {
            return grant_type;
        }

        public void setGrant_type(String grant_type) {
            this.grant_type = grant_type;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

}
