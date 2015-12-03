package gameEngine;

public class Player {

	City currentCity;
	String role;

	public Player(City currentCity, String role) {

		this.currentCity = currentCity;
		this.role = role;
		//Setup.citiesOnBoard.get(currentCity);
System.out.println(currentCity.toString());
	}

	public void setCurrentCity(City city) {
		this.currentCity = city;
	}

	public City getCurrentCity() {
		return currentCity;
	}

	public String getRole() {
		return role;
	}

}
