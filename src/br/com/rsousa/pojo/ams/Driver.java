package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver {
	private String isPlayer;

	private String CarNumber;

	private String BestLapTime;

	private String Connected;

	private Integer Position;

	private String LapRankIncludingDiscos;

	private String UpgradeCode;

	private String FinishStatus;
	
	private String FinishTime;

	private ControlAndAids ControlAndAids;

	private String VehName;

	private String TeamName;

	private String ServerScored;

	private String ClassPosition;

	private String Name;

	private String CarClass;

	private String Points;

	private String ClassPoints;

	private String CarType;

	private String VehFile;

	private Lap[] Lap;

	private Integer Laps;

	private String SteamID;

	private String RaceRank;

	private String Pitstops;

	public String getIsPlayer() {
		return isPlayer;
	}

	public void setIsPlayer(String isPlayer) {
		this.isPlayer = isPlayer;
	}

	public String getCarNumber() {
		return CarNumber;
	}

	public void setCarNumber(String CarNumber) {
		this.CarNumber = CarNumber;
	}

	public String getBestLapTime() {
		return BestLapTime;
	}

	public void setBestLapTime(String BestLapTime) {
		this.BestLapTime = BestLapTime;
	}

	public String getConnected() {
		return Connected;
	}

	public void setConnected(String Connected) {
		this.Connected = Connected;
	}

	public Integer getPosition() {
		return Position;
	}

	public void setPosition(Integer Position) {
		this.Position = Position;
	}

	public String getLapRankIncludingDiscos() {
		return LapRankIncludingDiscos;
	}

	public void setLapRankIncludingDiscos(String LapRankIncludingDiscos) {
		this.LapRankIncludingDiscos = LapRankIncludingDiscos;
	}

	public String getUpgradeCode() {
		return UpgradeCode;
	}

	public void setUpgradeCode(String UpgradeCode) {
		this.UpgradeCode = UpgradeCode;
	}

	public String getFinishStatus() {
		return FinishStatus;
	}

	public void setFinishStatus(String FinishStatus) {
		this.FinishStatus = FinishStatus;
	}
	
	public String getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(String FinishTime) {
		this.FinishTime = FinishTime;
	}

	public ControlAndAids getControlAndAids() {
		return ControlAndAids;
	}

	public void setControlAndAids(ControlAndAids ControlAndAids) {
		this.ControlAndAids = ControlAndAids;
	}

	public String getVehName() {
		return VehName;
	}

	public void setVehName(String VehName) {
		this.VehName = VehName;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String TeamName) {
		this.TeamName = TeamName;
	}

	public String getServerScored() {
		return ServerScored;
	}

	public void setServerScored(String ServerScored) {
		this.ServerScored = ServerScored;
	}

	public String getClassPosition() {
		return ClassPosition;
	}

	public void setClassPosition(String ClassPosition) {
		this.ClassPosition = ClassPosition;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getCarClass() {
		return CarClass;
	}

	public void setCarClass(String CarClass) {
		this.CarClass = CarClass;
	}

	public String getPoints() {
		return Points;
	}

	public void setPoints(String Points) {
		this.Points = Points;
	}

	public String getClassPoints() {
		return ClassPoints;
	}

	public void setClassPoints(String ClassPoints) {
		this.ClassPoints = ClassPoints;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String CarType) {
		this.CarType = CarType;
	}

	public String getVehFile() {
		return VehFile;
	}

	public void setVehFile(String VehFile) {
		this.VehFile = VehFile;
	}

	public Lap[] getLap() {
		return Lap;
	}

	public void setLap(Lap[] Lap) {
		this.Lap = Lap;
	}

	public Integer getLaps() {
		return Laps;
	}

	public void setLaps(Integer Laps) {
		this.Laps = Laps;
	}

	public String getSteamID() {
		return SteamID;
	}

	public void setSteamID(String SteamID) {
		this.SteamID = SteamID;
	}

	public String getRaceRank() {
		return RaceRank;
	}

	public void setRaceRank(String RaceRank) {
		this.RaceRank = RaceRank;
	}

	public String getPitstops() {
		return Pitstops;
	}

	public void setPitstops(String Pitstops) {
		this.Pitstops = Pitstops;
	}

	@Override
	public String toString() {
		return "ClassPojo [isPlayer = " + isPlayer + ", CarNumber = " + CarNumber + ", BestLapTime = " + BestLapTime
				+ ", Connected = " + Connected + ", Position = " + Position + ", LapRankIncludingDiscos = "
				+ LapRankIncludingDiscos + ", UpgradeCode = " + UpgradeCode + ", FinishStatus = " + FinishStatus
				+ ", ControlAndAids = " + ControlAndAids + ", VehName = " + VehName + ", TeamName = " + TeamName
				+ ", ServerScored = " + ServerScored + ", ClassPosition = " + ClassPosition + ", Name = " + Name
				+ ", CarClass = " + CarClass + ", Points = " + Points + ", ClassPoints = " + ClassPoints
				+ ", CarType = " + CarType + ", VehFile = " + VehFile + ", Lap = " + Lap + ", Laps = " + Laps
				+ ", SteamID = " + SteamID + ", RaceRank = " + RaceRank + ", Pitstops = " + Pitstops + "]";
	}
}
