
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StageCounts {

    @SerializedName("practice1")
    @Expose
    private Integer practice1;
    @SerializedName("qualifying1")
    @Expose
    private Integer qualifying1;
    @SerializedName("race1")
    @Expose
    private Integer race1;

    public Integer getPractice1() {
        return practice1;
    }

    public void setPractice1(Integer practice1) {
        this.practice1 = practice1;
    }

    public Integer getQualifying1() {
        return qualifying1;
    }

    public void setQualifying1(Integer qualifying1) {
        this.qualifying1 = qualifying1;
    }

    public Integer getRace1() {
        return race1;
    }

    public void setRace1(Integer race1) {
        this.race1 = race1;
    }

}
