
package br.com.rsousa.pojo.assetto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Driver {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Team")
    @Expose
    private String team;
    @SerializedName("Nation")
    @Expose
    private String nation;
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("GuidsList")
    @Expose
    private List<String> guidsList = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<String> getGuidsList() {
        return guidsList;
    }

    public void setGuidsList(List<String> guidsList) {
        this.guidsList = guidsList;
    }

	@Override
	public String toString() {
		return "Driver [name=" + name + ", team=" + team + ", nation=" + nation + ", guid=" + guid + ", guidsList="
				+ guidsList + "]";
	}
}
