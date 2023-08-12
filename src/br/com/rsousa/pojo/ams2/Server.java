
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Server {

    @SerializedName("next_history_index")
    @Expose
    private Integer nextHistoryIndex;
    @SerializedName("stats")
    @Expose
    private Stats stats;

    public Integer getNextHistoryIndex() {
        return nextHistoryIndex;
    }

    public void setNextHistoryIndex(Integer nextHistoryIndex) {
        this.nextHistoryIndex = nextHistoryIndex;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}
