package com.example.springboot.controller;

import java.util.Arrays;
import java.util.List;

import com.example.springboot.bl.HttpclientGetestat;
import com.example.springboot.bl.JsonReader;
import com.example.springboot.bl.MultiRegression;
import com.example.springboot.jackson.Value;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetJsonDataCalc {

    @GetMapping("/getjson")
    public String getjson(Model model) {

        String jsonUrl ="http://api.e-stat.go.jp/rest/3.0/app/json/getStatsData?cdCat03=02&cdCat04=02%2C03%2C04%2C05%2C06%2C07%2C08%2C09%2C10%2C11%2C12%2C13&cdCat05=01&cdTab=33%2C40&cdCat01=01&cdCat02=01&cdCat06=02&appId=2e99eca4bfb042927002486665e2a2f14d77aa12&lang=J&statsDataId=0003425893&metaGetFlg=Y&cntGetFlg=N&explanationGetFl";
      
        HttpclientGetestat hcg = new HttpclientGetestat();  // Class 1.
        String data = hcg.getestatData(jsonUrl);


        JsonReader jr = new JsonReader();    // Class 2.
        List<Value> collectData = null;
        try {
            collectData = jr.collectData(data);

            // for debug message.
            collectData.stream().filter(i -> (i.getTab().equals("33")))
            .forEach(i -> System.out.println(i.get$()));
            collectData.stream().filter(i -> (i.getTab().equals("40")))
            .forEach(i -> System.out.println(i.get$()));

        } catch (JsonProcessingException e) {
            // 簡易なエラー処理StackTrace出力
            e.printStackTrace();
        }

        MultiRegression mr = new MultiRegression();      // Class 3.
        double[] regparam = mr.analyse(collectData);

        data = "定数項：" + Arrays.toString(regparam);

        model.addAttribute("data", data);
        return "jsondata";
    }
    
}
