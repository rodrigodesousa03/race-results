package br.com.rsousa.iracing;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LapData {

    @SerializedName("lap_number")    private int lapNumber;
    @SerializedName("lap_time")      private long lapTime;
    @SerializedName("flags")         private int flags;
    @SerializedName("incident")      private boolean incident;
    @SerializedName("lap_events")    private List<String> lapEvents;
    @SerializedName("personal_best_lap") private boolean personalBestLap;

    /** Flag bitmask 0x04 = off track (confirmed via iRacing API tests). */
    public boolean isOffTrack() {
        return (flags & 4) != 0 || (lapEvents != null && lapEvents.contains("off track"));
    }

    public boolean hasValidTime() { return lapTime > 0; }

    public int          getLapNumber()      { return lapNumber; }
    public long         getLapTime()        { return lapTime; }
    public int          getFlags()          { return flags; }
    public boolean      isIncident()        { return incident; }
    public List<String> getLapEvents()      { return lapEvents; }
    public boolean      isPersonalBestLap() { return personalBestLap; }
}
