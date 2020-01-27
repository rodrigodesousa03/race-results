package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Lap {
	private String p;

	private String fuel;

	private String num;

	private String content;

	private String et;

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEt() {
		return et;
	}

	public void setEt(String et) {
		this.et = et;
	}

	@Override
	public String toString() {
		return "ClassPojo [p = " + p + ", fuel = " + fuel + ", num = " + num + ", content = " + content + ", et = " + et
				+ "]";
	}
}
