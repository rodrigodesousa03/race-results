
package br.com.rsousa.pojo.assetto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lap {

    @SerializedName("DriverName")
    @Expose
    private String driverName;
    @SerializedName("DriverGuid")
    @Expose
    private String driverGuid;
    @SerializedName("CarId")
    @Expose
    private Integer carId;
    @SerializedName("CarModel")
    @Expose
    private String carModel;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("LapTime")
    @Expose
    private Integer lapTime;
    @SerializedName("Sectors")
    @Expose
    private List<Integer> sectors = null;
    @SerializedName("Cuts")
    @Expose
    private Integer cuts;
    @SerializedName("BallastKG")
    @Expose
    private Integer ballastKG;
    @SerializedName("Tyre")
    @Expose
    private String tyre;
    @SerializedName("Restrictor")
    @Expose
    private Integer restrictor;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverGuid() {
        return driverGuid;
    }

    public void setDriverGuid(String driverGuid) {
        this.driverGuid = driverGuid;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getLapTime() {
        return lapTime;
    }

    public void setLapTime(Integer lapTime) {
        this.lapTime = lapTime;
    }

    public List<Integer> getSectors() {
        return sectors;
    }

    public void setSectors(List<Integer> sectors) {
        this.sectors = sectors;
    }

    public Integer getCuts() {
        return cuts;
    }

    public void setCuts(Integer cuts) {
        this.cuts = cuts;
    }

    public Integer getBallastKG() {
        return ballastKG;
    }

    public void setBallastKG(Integer ballastKG) {
        this.ballastKG = ballastKG;
    }

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public Integer getRestrictor() {
        return restrictor;
    }

    public void setRestrictor(Integer restrictor) {
        this.restrictor = restrictor;
    }

	@Override
	public String toString() {
		return "Lap [driverName=" + driverName + ", driverGuid=" + driverGuid + ", carId=" + carId + ", carModel="
				+ carModel + ", timestamp=" + timestamp + ", lapTime=" + lapTime + ", sectors=" + sectors + ", cuts="
				+ cuts + ", ballastKG=" + ballastKG + ", tyre=" + tyre + ", restrictor=" + restrictor + "]";
	}

}
