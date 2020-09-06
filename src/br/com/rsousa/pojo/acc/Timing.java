
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timing {

    @SerializedName("lastLap")
    @Expose
    private Integer lastLap;
    @SerializedName("lastSplits")
    @Expose
    private List<Integer> lastSplits = null;
    @SerializedName("bestLap")
    @Expose
    private Integer bestLap;
    @SerializedName("bestSplits")
    @Expose
    private List<Integer> bestSplits = null;
    @SerializedName("totalTime")
    @Expose
    private Integer totalTime;
    @SerializedName("lapCount")
    @Expose
    private Integer lapCount;
    @SerializedName("lastSplitId")
    @Expose
    private Integer lastSplitId;

    public Integer getLastLap() {
        return lastLap;
    }

    public void setLastLap(Integer lastLap) {
        this.lastLap = lastLap;
    }

    public List<Integer> getLastSplits() {
        return lastSplits;
    }

    public void setLastSplits(List<Integer> lastSplits) {
        this.lastSplits = lastSplits;
    }

    public Integer getBestLap() {
        return bestLap;
    }

    public void setBestLap(Integer bestLap) {
        this.bestLap = bestLap;
    }

    public List<Integer> getBestSplits() {
        return bestSplits;
    }

    public void setBestSplits(List<Integer> bestSplits) {
        this.bestSplits = bestSplits;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getLapCount() {
        return lapCount;
    }

    public void setLapCount(Integer lapCount) {
        this.lapCount = lapCount;
    }

    public Integer getLastSplitId() {
        return lastSplitId;
    }

    public void setLastSplitId(Integer lastSplitId) {
        this.lastSplitId = lastSplitId;
    }

}
