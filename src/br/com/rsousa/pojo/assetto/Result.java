
package br.com.rsousa.pojo.assetto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

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
    @SerializedName("BestLap")
    @Expose
    private Long bestLap;
    @SerializedName("TotalTime")
    @Expose
    private Long totalTime;
    @SerializedName("BallastKG")
    @Expose
    private Integer ballastKG;
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

    public Long getBestLap() {
        return bestLap;
    }

    public void setBestLap(Long bestLap) {
        this.bestLap = bestLap;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
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
		return "Result [driverName=" + driverName + ", driverGuid=" + driverGuid + ", carId=" + carId + ", carModel="
				+ carModel + ", bestLap=" + bestLap + ", totalTime=" + totalTime + ", ballastKG=" + ballastKG
				+ ", restrictor=" + restrictor + "]";
	}
}
