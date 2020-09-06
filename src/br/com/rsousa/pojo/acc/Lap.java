
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lap {

    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("driverIndex")
    @Expose
    private Integer driverIndex;
    @SerializedName("laptime")
    @Expose
    private Integer laptime;
    @SerializedName("isValidForBest")
    @Expose
    private Boolean isValidForBest;
    @SerializedName("splits")
    @Expose
    private List<Integer> splits = null;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDriverIndex() {
        return driverIndex;
    }

    public void setDriverIndex(Integer driverIndex) {
        this.driverIndex = driverIndex;
    }

    public Integer getLaptime() {
        return laptime;
    }

    public void setLaptime(Integer laptime) {
        this.laptime = laptime;
    }

    public Boolean getIsValidForBest() {
        return isValidForBest;
    }

    public void setIsValidForBest(Boolean isValidForBest) {
        this.isValidForBest = isValidForBest;
    }

    public List<Integer> getSplits() {
        return splits;
    }

    public void setSplits(List<Integer> splits) {
        this.splits = splits;
    }

}
