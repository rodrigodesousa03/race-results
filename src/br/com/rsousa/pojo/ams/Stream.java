package br.com.rsousa.pojo.ams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Stream {
	private Score[] Score;

	private Chat Chat;

	private Incident[] Incident;

	public Score[] getScore() {
		return Score;
	}

	public void setScore(Score[] Score) {
		this.Score = Score;
	}

	public Chat getChat() {
		return Chat;
	}

	public void setChat(Chat Chat) {
		this.Chat = Chat;
	}

	public Incident[] getIncident() {
		return Incident;
	}

	public void setIncident(Incident[] Incident) {
		this.Incident = Incident;
	}

	@Override
	public String toString() {
		return "ClassPojo [Score = " + Score + ", Chat = " + Chat + ", Incident = " + Incident + "]";
	}
}
