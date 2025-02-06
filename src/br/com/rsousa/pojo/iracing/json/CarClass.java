package br.com.rsousa.pojo.iracing.json;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class CarClass {

   @SerializedName("car_class_id")
   Integer carClassId;

   @SerializedName("short_name")
   String shortName;

   @SerializedName("name")
   String name;

   @SerializedName("strength_of_field")
   Integer strengthOfField;

   @SerializedName("num_entries")
   Integer numEntries;

   @SerializedName("cars_in_class")
   List<CarsInClass> carsInClass;


    public void setCarClassId(Integer carClassId) {
        this.carClassId = carClassId;
    }
    public Integer getCarClassId() {
        return carClassId;
    }
    
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getShortName() {
        return shortName;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setStrengthOfField(Integer strengthOfField) {
        this.strengthOfField = strengthOfField;
    }
    public Integer getStrengthOfField() {
        return strengthOfField;
    }
    
    public void setNumEntries(Integer numEntries) {
        this.numEntries = numEntries;
    }
    public Integer getNumEntries() {
        return numEntries;
    }
    
    public void setCarsInClass(List<CarsInClass> carsInClass) {
        this.carsInClass = carsInClass;
    }
    public List<CarsInClass> getCarsInClass() {
        return carsInClass;
    }
    
}