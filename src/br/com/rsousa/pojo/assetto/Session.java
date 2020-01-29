
package br.com.rsousa.pojo.assetto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("TrackName")
    @Expose
    private String trackName;
    @SerializedName("TrackConfig")
    @Expose
    private String trackConfig;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("DurationSecs")
    @Expose
    private Integer durationSecs;
    @SerializedName("RaceLaps")
    @Expose
    private Integer raceLaps;
    @SerializedName("Cars")
    @Expose
    private List<Car> cars = null;
    @SerializedName("Result")
    @Expose
    private List<Result> result = null;
    @SerializedName("Laps")
    @Expose
    private List<Lap> laps = null;
    @SerializedName("Events")
    @Expose
    private List<Event> events = null;

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackConfig() {
        return trackConfig;
    }

    public void setTrackConfig(String trackConfig) {
        this.trackConfig = trackConfig;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Integer durationSecs) {
        this.durationSecs = durationSecs;
    }

    public Integer getRaceLaps() {
        return raceLaps;
    }

    public void setRaceLaps(Integer raceLaps) {
        this.raceLaps = raceLaps;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void setLaps(List<Lap> laps) {
        this.laps = laps;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

	@Override
	public String toString() {
		return "Session [trackName=" + trackName + ", trackConfig=" + trackConfig + ", type=" + type + ", durationSecs="
				+ durationSecs + ", raceLaps=" + raceLaps + ", cars=" + cars + ", result=" + result + ", laps=" + laps
				+ ", events=" + events + "]";
	}

}
