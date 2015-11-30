package gameEngine;
import java.util.*;
import java.util.ArrayList.*;

public class City {

	private String cityName;
	private int cityPosX;
	private int cityPosY;
	private int infectionRate;
	 
	 public City( String cityName, int cityPosX, int cityPosY, int infectionRate ) {
		 
		 this.cityName=cityName;
		 this.cityPosX=cityPosX;
		 this.cityPosY=cityPosY;
		 this.infectionRate=infectionRate ;

}
	    public String getcityName() {
	         return cityName;
	    }
	    public void setcityName(String cityName) {
		this.cityName = cityName;
	    }
	    public int getcityPosX() {
		return cityPosX;
	    }
	    public void setcityPosX(int cityPosX) {
		this.cityPosX= cityPosX;
	    }
	    public int getcityPosY() {
			return cityPosY;
		    }
		    public void setcityPosY(int cityPosY) {
			this.cityPosY= cityPosY;
		    }
	    
	    public int getinfectionRate() {
		return infectionRate;
	    }
	    public void setinfectionRate(int infectionRate) {
	 	this.infectionRate = infectionRate;
	    }	

	 
	 public static void main(String[] args) {
		


}
}

