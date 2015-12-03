# Settlers of Catan

This project tries to implement the game "Settlers Of Catan" as an online-based multiplayer game,
in which a total of four players will be able to connect to a server and play the game.

For simplicity, a few elements of the original game will not be implemeneted in this version, i.e. the Robber, trading and certain rules. Therefore, the game must still be played according to the rules, controlled by the players themselves. These rules are displayed below.

## Development Environment

The project has been developed primarily in the IDE Eclipse Mars, with the implementation of the game library known as Slick2D. This library has been used for more simple programming for basic game properties, such as updating and rendering game elements.


##  To Play the Game

In order to play the game, a server must be launch with a matching port and IP to the clients. 
Once a server is established, a minimum and maximum of four players must join the game. This is needed to pass the menu state of the game.

### House Rules

Some compromised have been made in creation of this game. As such, there are some house rules which have been stablished instead of the original rules. These rules are as follows:

The game will only control some of the original rules, while others must be enforced by the players themselves.

- You are not allowed to place a House or City adjacent to another House or City (does not matter whether it is an enemy or your own). (Adjacent means at every intersection between the tiles)
- Roads and Houses/Cities have to be placed next to one the player's own roads or houses/cities.
- The development card, Build Roads, in the original game it gives the player the option of placing two roads. However, in our game it will provide the player with the resources for them to be able to place two roads. The player is not allowed to place anything else other than two roads with the resources provided.
- The desert tile piece will in our game give a random effect, instead of the traditional robber.
- The development piles are created for each player, which means each player can draw the maximum of each card, (the feature for sharing development pile would be too time consuming to implement).
- The traditional way of starting the game, i.e. from player 1 to player 4 and backwards, is not implemented instead player 1 begins and after player 4's turn, the next player is player 1.
- The trade option does not trade with other players but with a bank. The bank still requires 4 of the same resource to trade for 1 other resource.
