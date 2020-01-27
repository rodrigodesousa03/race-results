package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RFactorXML {
	private RaceResults RaceResults;

	private String version;

	public RaceResults getRaceResults() {
		return RaceResults;
	}

	public void setRaceResults(RaceResults RaceResults) {
		this.RaceResults = RaceResults;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ClassPojo [RaceResults = " + RaceResults + ", version = " + version + "]";
	}
}
