
package br.com.rsousa.pojo.assetto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelPosition {

    @SerializedName("X")
    @Expose
    private Double x;
    @SerializedName("Y")
    @Expose
    private Double y;
    @SerializedName("Z")
    @Expose
    private Double z;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

	@Override
	public String toString() {
		return "RelPosition [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
