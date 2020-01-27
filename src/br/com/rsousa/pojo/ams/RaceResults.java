package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceResults {
	private String TrackCourse;

	private String TimeString;

	private String FixedUpgrades;

	private String VehiclesAllowed;

	private String DateTime;

	private String Dedicated;

	private String FreeSettings;

	private String GameVersion;

	private String TireMult;

	private String Season;

	private String DamageMult;

	private String RaceTime;

	private String FuelMult;

	private String TrackLength;

	private String FixedSetups;

	private String Mod;

	private ConnectionType ConnectionType;

	private Qualify Qualify;
	
	private Race Race;

	private String Setting;

	private String TrackEvent;

	private String PlayerFile;

	private String MechFailRate;

	private String TrackVenue;

	private String Game;

	private String RaceLaps;

	private String ServerName;

	private String ParcFerme;

	public String getTrackCourse() {
		return TrackCourse;
	}

	public void setTrackCourse(String TrackCourse) {
		this.TrackCourse = TrackCourse;
	}

	public String getTimeString() {
		return TimeString;
	}

	public void setTimeString(String TimeString) {
		this.TimeString = TimeString;
	}

	public String getFixedUpgrades() {
		return FixedUpgrades;
	}

	public void setFixedUpgrades(String FixedUpgrades) {
		this.FixedUpgrades = FixedUpgrades;
	}

	public String getVehiclesAllowed() {
		return VehiclesAllowed;
	}

	public void setVehiclesAllowed(String VehiclesAllowed) {
		this.VehiclesAllowed = VehiclesAllowed;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String DateTime) {
		this.DateTime = DateTime;
	}

	public String getDedicated() {
		return Dedicated;
	}

	public void setDedicated(String Dedicated) {
		this.Dedicated = Dedicated;
	}

	public String getFreeSettings() {
		return FreeSettings;
	}

	public void setFreeSettings(String FreeSettings) {
		this.FreeSettings = FreeSettings;
	}

	public String getGameVersion() {
		return GameVersion;
	}

	public void setGameVersion(String GameVersion) {
		this.GameVersion = GameVersion;
	}

	public String getTireMult() {
		return TireMult;
	}

	public void setTireMult(String TireMult) {
		this.TireMult = TireMult;
	}

	public String getSeason() {
		return Season;
	}

	public void setSeason(String Season) {
		this.Season = Season;
	}

	public String getDamageMult() {
		return DamageMult;
	}

	public void setDamageMult(String DamageMult) {
		this.DamageMult = DamageMult;
	}

	public String getRaceTime() {
		return RaceTime;
	}

	public void setRaceTime(String RaceTime) {
		this.RaceTime = RaceTime;
	}

	public String getFuelMult() {
		return FuelMult;
	}

	public void setFuelMult(String FuelMult) {
		this.FuelMult = FuelMult;
	}

	public String getTrackLength() {
		return TrackLength;
	}

	public void setTrackLength(String TrackLength) {
		this.TrackLength = TrackLength;
	}

	public String getFixedSetups() {
		return FixedSetups;
	}

	public void setFixedSetups(String FixedSetups) {
		this.FixedSetups = FixedSetups;
	}

	public String getMod() {
		return Mod;
	}

	public void setMod(String Mod) {
		this.Mod = Mod;
	}

	public ConnectionType getConnectionType() {
		return ConnectionType;
	}

	public void setConnectionType(ConnectionType ConnectionType) {
		this.ConnectionType = ConnectionType;
	}

	public Qualify getQualify() {
		return Qualify;
	}

	public void setQualify(Qualify Qualify) {
		this.Qualify = Qualify;
	}
	
	public Race getRace() {
		return Race;
	}

	public void setRace(Race Race) {
		this.Race = Race;
	}

	public String getSetting() {
		return Setting;
	}

	public void setSetting(String Setting) {
		this.Setting = Setting;
	}

	public String getTrackEvent() {
		return TrackEvent;
	}

	public void setTrackEvent(String TrackEvent) {
		this.TrackEvent = TrackEvent;
	}

	public String getPlayerFile() {
		return PlayerFile;
	}

	public void setPlayerFile(String PlayerFile) {
		this.PlayerFile = PlayerFile;
	}

	public String getMechFailRate() {
		return MechFailRate;
	}

	public void setMechFailRate(String MechFailRate) {
		this.MechFailRate = MechFailRate;
	}

	public String getTrackVenue() {
		return TrackVenue;
	}

	public void setTrackVenue(String TrackVenue) {
		this.TrackVenue = TrackVenue;
	}

	public String getGame() {
		return Game;
	}

	public void setGame(String Game) {
		this.Game = Game;
	}

	public String getRaceLaps() {
		return RaceLaps;
	}

	public void setRaceLaps(String RaceLaps) {
		this.RaceLaps = RaceLaps;
	}

	public String getServerName() {
		return ServerName;
	}

	public void setServerName(String ServerName) {
		this.ServerName = ServerName;
	}

	public String getParcFerme() {
		return ParcFerme;
	}

	public void setParcFerme(String ParcFerme) {
		this.ParcFerme = ParcFerme;
	}

	@Override
	public String toString() {
		return "ClassPojo [TrackCourse = " + TrackCourse + ", TimeString = " + TimeString + ", FixedUpgrades = "
				+ FixedUpgrades + ", VehiclesAllowed = " + VehiclesAllowed + ", DateTime = " + DateTime
				+ ", Dedicated = " + Dedicated + ", FreeSettings = " + FreeSettings + ", GameVersion = " + GameVersion
				+ ", TireMult = " + TireMult + ", Season = " + Season + ", DamageMult = " + DamageMult + ", RaceTime = "
				+ RaceTime + ", FuelMult = " + FuelMult + ", TrackLength = " + TrackLength + ", FixedSetups = "
				+ FixedSetups + ", Mod = " + Mod + ", ConnectionType = " + ConnectionType + ", Qualify = " + Qualify + ", Race = " + Race
				+ ", Setting = " + Setting + ", TrackEvent = " + TrackEvent + ", PlayerFile = " + PlayerFile
				+ ", MechFailRate = " + MechFailRate + ", TrackVenue = " + TrackVenue + ", Game = " + Game
				+ ", RaceLaps = " + RaceLaps + ", ServerName = " + ServerName + ", ParcFerme = " + ParcFerme + "]";
	}
}
