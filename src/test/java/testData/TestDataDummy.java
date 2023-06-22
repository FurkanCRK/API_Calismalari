package testData;

import org.json.JSONObject;

public class TestDataDummy {

    public int basariliStatusCode = 200;

    public String contentType = "application/json";

    /*
    Response Body
    {
    "status":"success"
    "data";
        {
            "id" : 3
            "employee_name" : "Ashton Cox"
            "employee_salary" : 86000
            "employee_age" : 66
            "profile_image" : ""
        },
    "message" : "Successfully! Record has been fetched."
    }
     */

    public JSONObject dataOlusturJSON (){

        JSONObject data = new JSONObject();

        data.put("id" , 3);
        data.put("employee_name" , "Ashton Cox");
        data.put("employee_salary" , 86000);
        data.put("employee_age" , 66);
        data.put("profile_image" , "");

        return data;
    }

    public JSONObject expectedDataOlusturJSON (){

        JSONObject expdata = new JSONObject();

        expdata.put("status","success");
        expdata.put("message","Successfully! Record has been fetched.");
        expdata.put("data",dataOlusturJSON());

        return expdata;
    }
}
