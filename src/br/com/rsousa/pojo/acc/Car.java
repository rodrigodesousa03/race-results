
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("raceNumber")
    @Expose
    private Integer raceNumber;
    @SerializedName("carModel")
    @Expose
    private Integer carModel;
    @SerializedName("cupCategory")
    @Expose
    private Integer cupCategory;
    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("drivers")
    @Expose
    private List<Driver> drivers = null;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(Integer raceNumber) {
        this.raceNumber = raceNumber;
    }

    public Integer getCarModel() {
        return carModel;
    }

    public void setCarModel(Integer carModel) {
        this.carModel = carModel;
    }

    public Integer getCupCategory() {
        return cupCategory;
    }

    public void setCupCategory(Integer cupCategory) {
        this.cupCategory = cupCategory;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

}
