package br.com.rsousa.pojo.iracing.csv;

public class DriverSession {
	private int finalPosition;
	private String name;
	private String interval;
	private String qualifyTime;
	private String fastLap;
	private String id;
	private int completedLaps;
	private String out;
	private int startPosition;
	private int lapsLed;

	public int getFinalPosition() {
		return finalPosition;
	}

	public void setFinalPosition(int finalPosition) {
		this.finalPosition = finalPosition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getQualifyTime() {
		return qualifyTime;
	}

	public void setQualifyTime(String qualifyTime) {
		this.qualifyTime = qualifyTime;
	}

	public String getFastLap() {
		return fastLap;
	}

	public void setFastLap(String fastLap) {
		this.fastLap = fastLap;
	}

	public int getCompletedLaps() {
		return completedLaps;
	}

	public void setCompletedLaps(int completedLaps) {
		this.completedLaps = completedLaps;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	@Override
	public String toString() {
		return "DriverSession [finalPosition=" + finalPosition + ", name=" + name + ", interval=" + interval
				+ ", qualifyTime=" + qualifyTime + ", fastLap=" + fastLap + ", id=" + id + ", completedLaps="
				+ completedLaps + ", out=" + out + "]";
	}

	public int getLapsLed() {
		return lapsLed;
	}

	public void setLapsLed(int lapsLed) {
		this.lapsLed = lapsLed;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
}
