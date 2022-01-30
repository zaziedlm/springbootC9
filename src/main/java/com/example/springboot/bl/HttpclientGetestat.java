package com.example.springboot.bl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Httpclient_Get_estat
 *
 */
public class HttpclientGetestat 
{
    public String getestatData( String jsonUrl )
    {

        HttpClient hclt = HttpClient.newBuilder()
                        .build();

        HttpRequest hreq = HttpRequest.newBuilder()
                        .uri(URI.create(jsonUrl))
                        .GET()
                        .build();

        String data = null;
        try {
            // http requestの実行、responseの取得
            HttpResponse<String> hres = hclt.send(hreq, BodyHandlers.ofString());

            // HTTP Status Code の出力
            System.out.println(hres.statusCode());
 
            // Response Body の出力
            System.out.println(hres.body());

            data =  hres.body();

        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();   
        }

        return data;
    }
}
