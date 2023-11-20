
package br.com.rsousa.pojo.ams2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Qualifying {

    @SerializedName("end_time")
    @Expose
    private Integer endTime;
    @SerializedName("events")
    @Expose
    private Event events;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Event getEvents() {
        return events;
    }

    public void setEvents(Event events) {
        this.events = events;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

}
