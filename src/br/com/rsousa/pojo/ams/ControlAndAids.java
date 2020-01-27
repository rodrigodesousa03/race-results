package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ControlAndAids {
	private String startLap;

	private String endLap;

	private String content;

	public String getStartLap() {
		return startLap;
	}

	public void setStartLap(String startLap) {
		this.startLap = startLap;
	}

	public String getEndLap() {
		return endLap;
	}

	public void setEndLap(String endLap) {
		this.endLap = endLap;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ClassPojo [startLap = " + startLap + ", endLap = " + endLap + ", content = " + content + "]";
	}
}
