
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("FastestLapTime")
    @Expose
    private Integer fastestLapTime;
    @SerializedName("Lap")
    @Expose
    private Integer lap;
    @SerializedName("RacePosition")
    @Expose
    private Integer racePosition;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("TotalTime")
    @Expose
    private Integer totalTime;
    @SerializedName("VehicleId")
    @Expose
    private Integer vehicleId;

    public Integer getFastestLapTime() {
        return fastestLapTime;
    }

    public void setFastestLapTime(Integer fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
    }

    public Integer getLap() {
        return lap;
    }

    public void setLap(Integer lap) {
        this.lap = lap;
    }

    public Integer getRacePosition() {
        return racePosition;
    }

    public void setRacePosition(Integer racePosition) {
        this.racePosition = racePosition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}
