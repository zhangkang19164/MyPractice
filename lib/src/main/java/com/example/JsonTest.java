package com.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * create time : 2017/08/01
 * desc        :
 */

public class JsonTest {

    public static void main(String[] args) {


    }

    public static JSONObject createJSONObject() {
        JSONObject jSONObject = new JSONObject();
        Object nullObject = null;
        try {
            jSONObject.put("name", "张康");
            jSONObject.put("age", 21);
            jSONObject.put("birthday", "1992-07-16");
            jSONObject.put("major", new String[]{"厨师", "挖掘机", "美容"});
            jSONObject.put("has_Car", false);
            jSONObject.put("fangzi", nullObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static JSONObject createJsonByMap() {
        Map<String, Object> map = new HashMap<>();
        Object nullObject = null;
        map.put("name", "张康");
        map.put("age", 21);
        map.put("birthday", "1992-07-16");
        map.put("major", new String[]{"厨师", "挖掘机", "美容"});
        map.put("has_Car", false);
        map.put("fangzi", nullObject);
        return new JSONObject(map);
    }

    private static JSONObject createJsonByJavaBean() {
        DiaoSI diaoSI = new DiaoSI();
        diaoSI.setName("张康");
        diaoSI.setAge(25);
        diaoSI.setBirthday("1992-07-16");
        diaoSI.setMajor(new String[]{"厨师", "挖掘机", "美容"});
        diaoSI.setHas_Car(false);
        diaoSI.setFangzi(null);
        return new JSONObject(diaoSI);
    }
}
