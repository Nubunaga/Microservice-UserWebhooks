package com.webhook.User.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is responsible for the calls done by this service.
 * @author Netanel Avraham Eklind
 */
public class URLCaller {
    private URL caller;


    public URLCaller(String url) throws MalformedURLException {

        caller = new URL(url);
    }


    public boolean callServer(String uName,String tag){
        try {
            HttpURLConnection con = (HttpURLConnection) caller.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"User\": \""+uName+"\""+", \"Tag\": \""+tag+"\"}";
            System.err.println(jsonInputString);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        } catch (IOException e) {
            return false;
        }


        return true;
    }


}
