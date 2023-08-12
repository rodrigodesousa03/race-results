
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {
    @SerializedName("IsPlayer")
    @Expose
    private Integer isPlayer;
    @SerializedName("LiveryId")
    @Expose
    private Integer liveryId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("RefId")
    @Expose
    private Integer refId;
    @SerializedName("VehicleId")
    @Expose
    private Integer vehicleId;

    public Integer getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(Integer isPlayer) {
        this.isPlayer = isPlayer;
    }

    public Integer getLiveryId() {
        return liveryId;
    }

    public void setLiveryId(Integer liveryId) {
        this.liveryId = liveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}
