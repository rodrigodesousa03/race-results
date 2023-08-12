
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Qualify {

    @SerializedName("states")
    @Expose
    private HashMap<String,Integer> states;

    public HashMap<String,Integer> getStates() {
        return states;
    }

    public void setStates(HashMap<String,Integer> states) {
        this.states = states;
    }

}
