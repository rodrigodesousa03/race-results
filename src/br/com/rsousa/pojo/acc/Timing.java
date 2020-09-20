
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
    private Long bestLap;
    @SerializedName("bestSplits")
    @Expose
    private List<Integer> bestSplits = null;
    @SerializedName("totalTime")
    @Expose
    private Long totalTime;
    @SerializedName("lapCount")
    @Expose
    private Integer lapCount;
    @SerializedName("lastSplitId")
    @Expose
    private Long lastSplitId;

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

    public Long getBestLap() {
        return bestLap;
    }

    public void setBestLap(Long bestLap) {
        this.bestLap = bestLap;
    }

    public List<Integer> getBestSplits() {
        return bestSplits;
    }

    public void setBestSplits(List<Integer> bestSplits) {
        this.bestSplits = bestSplits;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getLapCount() {
        return lapCount;
    }

    public void setLapCount(Integer lapCount) {
        this.lapCount = lapCount;
    }

    public Long getLastSplitId() {
        return lastSplitId;
    }

    public void setLastSplitId(Long lastSplitId) {
        this.lastSplitId = lastSplitId;
    }

}
