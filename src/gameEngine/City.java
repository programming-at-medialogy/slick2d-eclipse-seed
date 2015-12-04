package gameEngine;

import java.util.*;
import java.util.ArrayList.*;

public class City {

	private String cityName;
	public int cityPosX;
	public int cityPosY;
	private int infectionRate;

	public City(String cityName, int cityPosX, int cityPosY, int infectionRate) {

		this.cityName = cityName;
		this.cityPosX = cityPosX;
		this.cityPosY = cityPosY;
		this.infectionRate = infectionRate;

	}

	public void setcityName(String cityName) {
		this.cityName = cityName;
	}

	public String getcityName() {
		return cityName;
	}

	public int getcityPosX() {
		return cityPosX;
	}

	public void setcityPosX(int cityPosX) {
		this.cityPosX = cityPosX;
	}

	public int getcityPosY() {
		return cityPosY;
	}

	public void setcityPosY(int cityPosY) {
		this.cityPosY = cityPosY;
	}

	public int getInfectionRate() {
		return infectionRate;
	}

	public void setInfectionRate(int infectionRate) {
		this.infectionRate = infectionRate;
	}

	public static void main(String[] args) {

	}
}
