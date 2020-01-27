package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Race {
	private String TimeString;

	private Driver[] Driver;

	private Stream Stream;

	private String MostLapsCompleted;

	private String Laps;

	private String DateTime;

	private String Minutes;

	public String getTimeString() {
		return TimeString;
	}

	public void setTimeString(String TimeString) {
		this.TimeString = TimeString;
	}

	public Driver[] getDriver() {
		return Driver;
	}

	public void setDriver(Driver[] Driver) {
		this.Driver = Driver;
	}

	public Stream getStream() {
		return Stream;
	}

	public void setStream(Stream Stream) {
		this.Stream = Stream;
	}

	public String getMostLapsCompleted() {
		return MostLapsCompleted;
	}

	public void setMostLapsCompleted(String MostLapsCompleted) {
		this.MostLapsCompleted = MostLapsCompleted;
	}

	public String getLaps() {
		return Laps;
	}

	public void setLaps(String Laps) {
		this.Laps = Laps;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String DateTime) {
		this.DateTime = DateTime;
	}

	public String getMinutes() {
		return Minutes;
	}

	public void setMinutes(String Minutes) {
		this.Minutes = Minutes;
	}

	@Override
	public String toString() {
		return "ClassPojo [TimeString = " + TimeString + ", Driver = " + Driver + ", Stream = " + Stream
				+ ", MostLapsCompleted = " + MostLapsCompleted + ", Laps = " + Laps + ", DateTime = " + DateTime
				+ ", Minutes = " + Minutes + "]";
	}
}
