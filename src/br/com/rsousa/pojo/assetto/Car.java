
package br.com.rsousa.pojo.assetto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("CarId")
    @Expose
    private Integer carId;
    @SerializedName("Driver")
    @Expose
    private Driver driver;
    @SerializedName("Model")
    @Expose
    private String model;
    @SerializedName("Skin")
    @Expose
    private String skin;
    @SerializedName("BallastKG")
    @Expose
    private Integer ballastKG;
    @SerializedName("Restrictor")
    @Expose
    private Integer restrictor;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Integer getBallastKG() {
        return ballastKG;
    }

    public void setBallastKG(Integer ballastKG) {
        this.ballastKG = ballastKG;
    }

    public Integer getRestrictor() {
        return restrictor;
    }

    public void setRestrictor(Integer restrictor) {
        this.restrictor = restrictor;
    }

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", driver=" + driver + ", model=" + model + ", skin=" + skin + ", ballastKG="
				+ ballastKG + ", restrictor=" + restrictor + "]";
	}

}
