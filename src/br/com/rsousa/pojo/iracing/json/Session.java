package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class Session {

   @SerializedName("type")
   String type;

   @SerializedName("data")
   Data data;


    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }
    
}