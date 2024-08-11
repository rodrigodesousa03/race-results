
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Helmet {

    @SerializedName("pattern")
    @Expose
    private Integer pattern;
    @SerializedName("color1")
    @Expose
    private String color1;
    @SerializedName("color2")
    @Expose
    private String color2;
    @SerializedName("color3")
    @Expose
    private String color3;
    @SerializedName("face_type")
    @Expose
    private Integer faceType;
    @SerializedName("helmet_type")
    @Expose
    private Integer helmetType;

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public Integer getFaceType() {
        return faceType;
    }

    public void setFaceType(Integer faceType) {
        this.faceType = faceType;
    }

    public Integer getHelmetType() {
        return helmetType;
    }

    public void setHelmetType(Integer helmetType) {
        this.helmetType = helmetType;
    }

}
