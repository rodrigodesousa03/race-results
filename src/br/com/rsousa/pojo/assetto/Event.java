
package br.com.rsousa.pojo.assetto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("CarId")
    @Expose
    private Integer carId;
    @SerializedName("Driver")
    @Expose
    private Driver driver;
    @SerializedName("OtherCarId")
    @Expose
    private Integer otherCarId;
    @SerializedName("OtherDriver")
    @Expose
    private OtherDriver otherDriver;
    @SerializedName("ImpactSpeed")
    @Expose
    private Double impactSpeed;
    @SerializedName("WorldPosition")
    @Expose
    private WorldPosition worldPosition;
    @SerializedName("RelPosition")
    @Expose
    private RelPosition relPosition;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getOtherCarId() {
        return otherCarId;
    }

    public void setOtherCarId(Integer otherCarId) {
        this.otherCarId = otherCarId;
    }

    public OtherDriver getOtherDriver() {
        return otherDriver;
    }

    public void setOtherDriver(OtherDriver otherDriver) {
        this.otherDriver = otherDriver;
    }

    public Double getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(Double impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public WorldPosition getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(WorldPosition worldPosition) {
        this.worldPosition = worldPosition;
    }

    public RelPosition getRelPosition() {
        return relPosition;
    }

    public void setRelPosition(RelPosition relPosition) {
        this.relPosition = relPosition;
    }

	@Override
	public String toString() {
		return "Event [type=" + type + ", carId=" + carId + ", driver=" + driver + ", otherCarId=" + otherCarId
				+ ", otherDriver=" + otherDriver + ", impactSpeed=" + impactSpeed + ", worldPosition=" + worldPosition
				+ ", relPosition=" + relPosition + "]";
	}

}
