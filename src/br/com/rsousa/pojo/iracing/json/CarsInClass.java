package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class CarsInClass {

   @SerializedName("car_id")
   Integer carId;


    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getCarId() {
        return carId;
    }
    
}