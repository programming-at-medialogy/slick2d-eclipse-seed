

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HexMap {
	
	//Declares an array of instances from other classes.
	HexPiece [] piece;
	HexPiece [] waterPiece;
	HexNumber [] numberPiece;
	
	Game game;
	Controller control;
	
	//Array to store the information from the server, so all clients get the same order in the arrays
	//when these are assigned by the server. This way, all clients will see the same.
	int [] indexArrayTile;
	int [] indexArrayNumber;
	
	HexMap(Controller control) throws SlickException{
		this.control = control;
		
		//Declares the length of the arrays.
		piece = new HexPiece [19];
		waterPiece = new HexPiece[18];
		numberPiece = new HexNumber[19];
		
		//Assign the information from the server.
		indexArrayNumber = game.client.obj.SOCnumberIndex;
		indexArrayTile = game.client.obj.SOCtileIndex;
		placeMap();
	}

	//Renders the water pieces, the hex tiles and the numbers on the hex tiles.
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(int i = 0; i < waterPiece.length; i++){
			waterPiece[i].render(gc, g);	
		}
		for(int i = 0; i < piece.length ; i ++){
			piece[i].render(gc, g);
		}
		
		for(int i = 0; i < numberPiece.length; i++) {
			numberPiece[i].render(gc, g);
		}	
	}
	
	//Method called placemap with bascially sets the position of every water piece and hex piece.
	public void placeMap() throws SlickException{
		
		//variables used to get the correct position of the tiles. 
		int x = 0;
		int y = 112;
		int offset = 0;
		int width = 900/3;
		int height = 112;
		int heightOffset = 28;
		int xOffset = 97/2;
		int xOffsetTwo = 0;
		int tileWidth = 97;
		int indexer = 0; //The indexer is what keeps that on what number we are currently on in the array.
		int offsetWater1 = 0;
		int offsetWater2 = 0;
		
		//Makes the first 4 water pieces.
		for(int i = 0; i < 4; i++) {
		waterPiece[i] = new HexPiece(offsetWater1+width-xOffset,y-heightOffset*3,6);
		offsetWater1 += tileWidth;
		}
		
		//A for-loop running 19 long.
		for (int i = 0; i < piece.length ; i ++){
			//if i is below 3, make the first three pieces while incrementing the indexer to get another place in the array
			if(i<3){
			piece[i] = new HexPiece(x+width,y+0,indexArrayTile[indexer]);
			control.tile[i][0] = piece[i].xMiddle; //stores the xMiddle position of the i-particular piece
			control.tile[i][1] = piece[i].yMiddle; //same for the yMiddle
			control.tile[i][2] = piece[i].index; //stores the index to know what resource the tile must give.
			indexer++; //increment the indexer
			x += tileWidth; //make x larger, so we move a tile 1 piece to the right.
			
			//Spawns a water piece to the left and right of the above three
			waterPiece[4] = new HexPiece(x-tileWidth+10,y,6);
			waterPiece[5] = new HexPiece(x+9+tileWidth*3,y,6);
			}
			
			//The below does the same as above.
			else if(i<7){
				piece[i] = new HexPiece(offset+width-xOffset,y+height-heightOffset,indexArrayTile[indexer]);
				control.tile[i][0] = piece[i].xMiddle;
				control.tile[i][1] = piece[i].yMiddle;
				control.tile[i][2] = piece[i].index;
				indexer++;
				offset += tileWidth;
				
				waterPiece[6] = new HexPiece(x-tileWidth+10-(tileWidth/2),y+height-heightOffset,6);
				waterPiece[7] = new HexPiece(x+tileWidth*3+9+(tileWidth/2),y+height-heightOffset,6);
			}
			
			else if(i<12){
				piece[i] = new HexPiece(width+xOffsetTwo-xOffset*2,y+height*2-heightOffset*2,indexArrayTile[indexer]);
				control.tile[i][0] = piece[i].xMiddle;
				control.tile[i][1] = piece[i].yMiddle;
				control.tile[i][2] = piece[i].index;
				indexer++;
				xOffsetTwo+= tileWidth;
				
				waterPiece[8] = new HexPiece(x-tileWidth*2+10,y+height*2-heightOffset*2,6);
				waterPiece[9] = new HexPiece(x+tileWidth*4+9,y+height*2-heightOffset*2,6);
			}
			
			else if(i<16){
				piece[i] = new HexPiece(offset+width-xOffset-tileWidth*4,y+height*3-heightOffset*3,indexArrayTile[indexer]);
				control.tile[i][0] = piece[i].xMiddle;
				control.tile[i][1] = piece[i].yMiddle;
				control.tile[i][2] = piece[i].index;
				indexer++;
				offset += tileWidth;
				
				waterPiece[10] = new HexPiece(x-tileWidth+9-(tileWidth/2),y+height*3-heightOffset*3,6);
				waterPiece[11] = new HexPiece(x+tileWidth*3+10+(tileWidth/2),y+height*3-heightOffset*3,6);
				
			}
			
			else if(i<=19){
				piece[i] = new HexPiece(x+width-tileWidth*3,y+height*4-heightOffset*4,indexArrayTile[indexer]);
				control.tile[i][0] = piece[i].xMiddle;
				control.tile[i][1] = piece[i].yMiddle;
				control.tile[i][2] = piece[i].index;
				indexer++;
				x += tileWidth;
				
				waterPiece[12] = new HexPiece(x+9,y+height*4-heightOffset*4,6);
				waterPiece[13] = new HexPiece(x+9-tileWidth*4,y+height*4-heightOffset*4,6);
			}
		}
		
		//Makes the last 4 water pieces.
		for(int i = 14; i < waterPiece.length; i++) {
		waterPiece[i] = new HexPiece(offsetWater2+width-xOffset,y+height*4-heightOffset,6);
		offsetWater2 += tileWidth;
		}
		
		//SPAWN NUMBERPIECES
		//The position is being set to the same as the number pieces, but 20 more on y so we get the a little lower.
		int indexNo = 0;
		for(int i = 0; i < numberPiece.length; i++) {
			numberPiece[i] = new HexNumber(piece[i].x, piece[i].y+20, indexArrayNumber[i]);
		}	
	}
//END CLASS
}
