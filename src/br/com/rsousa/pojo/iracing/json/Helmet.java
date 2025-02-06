package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class Helmet {

   @SerializedName("pattern")
   Integer pattern;

   @SerializedName("color1")
   String color1;

   @SerializedName("color2")
   String color2;

   @SerializedName("color3")
   String color3;

   @SerializedName("face_type")
   Integer faceType;

   @SerializedName("helmet_type")
   Integer helmetType;


    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }
    public Integer getPattern() {
        return pattern;
    }
    
    public void setColor1(String color1) {
        this.color1 = color1;
    }
    public String getColor1() {
        return color1;
    }
    
    public void setColor2(String color2) {
        this.color2 = color2;
    }
    public String getColor2() {
        return color2;
    }
    
    public void setColor3(String color3) {
        this.color3 = color3;
    }
    public String getColor3() {
        return color3;
    }
    
    public void setFaceType(Integer faceType) {
        this.faceType = faceType;
    }
    public Integer getFaceType() {
        return faceType;
    }
    
    public void setHelmetType(Integer helmetType) {
        this.helmetType = helmetType;
    }
    public Integer getHelmetType() {
        return helmetType;
    }
    
}