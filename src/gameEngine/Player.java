package gameEngine;

public class Player {

	String currentCity;
	String role;

	public Player(String currentCity, String role) {

		this.currentCity = currentCity;
		this.role = role;

	}

	public String setCurrentCity(String city) {
		return 	this.currentCity = city;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public String getRole() {
		return role;
	}

}
