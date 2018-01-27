package com.example.kamil.fragmentstest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;


public class HttpHelper {
    HttpsURLConnection connection=null;
    BufferedReader reader=null;

    public String GetHttpHelper(String link){
        try{

            URL url=new URL(link);
            connection= (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream stream=connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer=new StringBuffer();
            String line="";

            while((line=reader.readLine())!=null){
                buffer.append(line);
            }

            String finalJson= buffer.toString();

        return finalJson;

    }
            catch (MalformedURLException e){
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }  finally {
        if(connection!=null){
            connection.disconnect();
        }
        try {
            if(reader!=null){
                reader.close();}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
            return null;
}

}
