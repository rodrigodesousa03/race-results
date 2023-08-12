
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Setup {

    @SerializedName("LiveryId")
    @Expose
    private Integer liveryId;
    @SerializedName("RaceStatFlags")
    @Expose
    private Integer raceStatFlags;
    @SerializedName("VehicleId")
    @Expose
    private Integer vehicleId;

    public Integer getLiveryId() {
        return liveryId;
    }

    public void setLiveryId(Integer liveryId) {
        this.liveryId = liveryId;
    }

    public Integer getRaceStatFlags() {
        return raceStatFlags;
    }

    public void setRaceStatFlags(Integer raceStatFlags) {
        this.raceStatFlags = raceStatFlags;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}
