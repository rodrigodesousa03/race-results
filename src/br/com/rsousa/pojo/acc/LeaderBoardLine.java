
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderBoardLine {

    @SerializedName("car")
    @Expose
    private Car car;
    @SerializedName("currentDriver")
    @Expose
    private CurrentDriver currentDriver;
    @SerializedName("currentDriverIndex")
    @Expose
    private Integer currentDriverIndex;
    @SerializedName("timing")
    @Expose
    private Timing timing;
    @SerializedName("missingMandatoryPitstop")
    @Expose
    private Integer missingMandatoryPitstop;
    @SerializedName("driverTotalTimes")
    @Expose
    private List<Double> driverTotalTimes = null;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CurrentDriver getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(CurrentDriver currentDriver) {
        this.currentDriver = currentDriver;
    }

    public Integer getCurrentDriverIndex() {
        return currentDriverIndex;
    }

    public void setCurrentDriverIndex(Integer currentDriverIndex) {
        this.currentDriverIndex = currentDriverIndex;
    }

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    public Integer getMissingMandatoryPitstop() {
        return missingMandatoryPitstop;
    }

    public void setMissingMandatoryPitstop(Integer missingMandatoryPitstop) {
        this.missingMandatoryPitstop = missingMandatoryPitstop;
    }

    public List<Double> getDriverTotalTimes() {
        return driverTotalTimes;
    }

    public void setDriverTotalTimes(List<Double> driverTotalTimes) {
        this.driverTotalTimes = driverTotalTimes;
    }

}
