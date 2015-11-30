package gameEngine;
import java.util.List;

public class Player {
    private City currentCity;
    private String role;
    private List<RoleCards> deck;
    
    public Player(City currentCity, String role, List<RoleCards> deck) {
        this.currentCity = currentCity;
        this.role = role;
        this.deck = deck;
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
    
    public List<RoleCards> getDeck() {
        return deck;
    }
    
    public void addCard(RoleCards card) {
        deck.add(card);
    }
    
    public void removeCard(RoleCards card) {
        deck.remove(card);
    }
}