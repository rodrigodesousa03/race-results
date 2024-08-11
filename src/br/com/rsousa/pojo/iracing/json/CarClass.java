
package br.com.rsousa.pojo.iracing.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarClass {

    @SerializedName("car_class_id")
    @Expose
    private Integer carClassId;
    @SerializedName("cars_in_class")
    @Expose
    private List<CarsInClas> carsInClass;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;

    public Integer getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(Integer carClassId) {
        this.carClassId = carClassId;
    }

    public List<CarsInClas> getCarsInClass() {
        return carsInClass;
    }

    public void setCarsInClass(List<CarsInClas> carsInClass) {
        this.carsInClass = carsInClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
