
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionResult {

    @SerializedName("bestlap")
    @Expose
    private Integer bestlap;
    @SerializedName("bestSplits")
    @Expose
    private List<Integer> bestSplits = null;
    @SerializedName("isWetSession")
    @Expose
    private Integer isWetSession;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("leaderBoardLines")
    @Expose
    private List<LeaderBoardLine> leaderBoardLines = null;

    public Integer getBestlap() {
        return bestlap;
    }

    public void setBestlap(Integer bestlap) {
        this.bestlap = bestlap;
    }

    public List<Integer> getBestSplits() {
        return bestSplits;
    }

    public void setBestSplits(List<Integer> bestSplits) {
        this.bestSplits = bestSplits;
    }

    public Integer getIsWetSession() {
        return isWetSession;
    }

    public void setIsWetSession(Integer isWetSession) {
        this.isWetSession = isWetSession;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<LeaderBoardLine> getLeaderBoardLines() {
        return leaderBoardLines;
    }

    public void setLeaderBoardLines(List<LeaderBoardLine> leaderBoardLines) {
        this.leaderBoardLines = leaderBoardLines;
    }

}
