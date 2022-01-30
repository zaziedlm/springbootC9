package com.example.springboot.bl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.example.springboot.jackson.Value;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 * MultiRegression
 *
 */
public class MultiRegression 
{
    public double[] analyse(List<Value> collectData )
    {

        // Java Stream型に変換する
        Stream<Value> stm = collectData.stream();

        // Streamを使って、データ部から年齢データのみ抜き出して、その配列データを生成する
        double[] ages = stm.filter(i -> (i.getTab()).equals("33"))
                            .map(Value::get$)
                            .mapToDouble(Double::parseDouble)
                            .toArray();

        // 年齢と同様にして収入金額についても、その配列データを生成する
        stm = collectData.stream();
        double[] incomes = stm.filter(i -> (i.getTab()).equals("40"))
                            .map(Value::get$)
                            .mapToDouble(Double::parseDouble)
                            .toArray();

        // 回帰分析クラスの引数型に合わせた、二次元配列を生成、データセットする
        double[][] multiData = new double[ages.length][2];
        for(int i=0; i < ages.length; i++) {
            multiData[i][0] = Math.pow(ages[i], 2);
            multiData[i][1] = ages[i];
        }
        
        // 重回帰分析のため、インスタンス生成
        OLSMultipleLinearRegression reg = new OLSMultipleLinearRegression();
        // データのセット
        reg.newSampleData(incomes, multiData);

        // for debug. 推定したパラメータを表示
        System.out.println("定数項：" +
            Arrays.toString(reg.estimateRegressionParameters())
        );

        return reg.estimateRegressionParameters();

    }
}
