package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("counts")
    @Expose
    private Counts counts;
    @SerializedName("last_joined")
    @Expose
    private Integer lastJoined;
    @SerializedName("name")
    @Expose
    private String name;

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    public Integer getLastJoined() {
        return lastJoined;
    }

    public void setLastJoined(Integer lastJoined) {
        this.lastJoined = lastJoined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
