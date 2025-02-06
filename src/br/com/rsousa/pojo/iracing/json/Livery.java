package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class Livery {

   @SerializedName("car_id")
   Integer carId;

   @SerializedName("pattern")
   Integer pattern;

   @SerializedName("color1")
   String color1;

   @SerializedName("color2")
   String color2;

   @SerializedName("color3")
   String color3;

   @SerializedName("number_font")
   Integer numberFont;

   @SerializedName("number_color1")
   String numberColor1;

   @SerializedName("number_color2")
   String numberColor2;

   @SerializedName("number_color3")
   String numberColor3;

   @SerializedName("number_slant")
   Integer numberSlant;

   @SerializedName("sponsor1")
   Integer sponsor1;

   @SerializedName("sponsor2")
   Integer sponsor2;

   @SerializedName("car_number")
   String carNumber;

   @SerializedName("wheel_color")
   String wheelColor;

   @SerializedName("rim_type")
   Integer rimType;


    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getCarId() {
        return carId;
    }
    
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
    
    public void setNumberFont(Integer numberFont) {
        this.numberFont = numberFont;
    }
    public Integer getNumberFont() {
        return numberFont;
    }
    
    public void setNumberColor1(String numberColor1) {
        this.numberColor1 = numberColor1;
    }
    public String getNumberColor1() {
        return numberColor1;
    }
    
    public void setNumberColor2(String numberColor2) {
        this.numberColor2 = numberColor2;
    }
    public String getNumberColor2() {
        return numberColor2;
    }
    
    public void setNumberColor3(String numberColor3) {
        this.numberColor3 = numberColor3;
    }
    public String getNumberColor3() {
        return numberColor3;
    }
    
    public void setNumberSlant(Integer numberSlant) {
        this.numberSlant = numberSlant;
    }
    public Integer getNumberSlant() {
        return numberSlant;
    }
    
    public void setSponsor1(Integer sponsor1) {
        this.sponsor1 = sponsor1;
    }
    public Integer getSponsor1() {
        return sponsor1;
    }
    
    public void setSponsor2(Integer sponsor2) {
        this.sponsor2 = sponsor2;
    }
    public Integer getSponsor2() {
        return sponsor2;
    }
    
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getCarNumber() {
        return carNumber;
    }
    
    public void setWheelColor(String wheelColor) {
        this.wheelColor = wheelColor;
    }
    public String getWheelColor() {
        return wheelColor;
    }
    
    public void setRimType(Integer rimType) {
        this.rimType = rimType;
    }
    public Integer getRimType() {
        return rimType;
    }
    
}