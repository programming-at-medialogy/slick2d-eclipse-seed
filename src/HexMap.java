

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HexMap {
	HexPiece [] piece;
	HexPiece [] waterPiece;
	HexNumber [] numberPiece;
	Game game;
	Controller control;
	
	int [] indexArrayTile;
	int [] indexArrayNumber;
	
	HexMap(Controller control) throws SlickException{
		this.control = control;
		piece = new HexPiece [19];
		waterPiece = new HexPiece[18];
		numberPiece = new HexNumber[19];
		indexArrayNumber = game.client.obj.SOCnumberIndex;
		indexArrayTile = game.client.obj.SOCtileIndex;
		placeMap();
	}
	

	
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
	
	
	public void shuffleArray(int[] ar){
		 Random rnd = ThreadLocalRandom.current();
		    for (int i = ar.length - 1; i > 0; i--)
		    {
		      int index = rnd.nextInt(i + 1);
		      // Simple swap
		      int a = ar[index];
		      ar[index] = ar[i];
		      ar[i] = a;
		    }
	}

	public int find (int[] array, int value) {
		int arrayNumber = 0;
	    for(int i=0; i<array.length; i++) 
	         if(array[i] == value)
	        	 arrayNumber = i;
	    return arrayNumber;
	}
	
public void placeMap() throws SlickException{
		
		int x = 0;
		int y = 112;
		int offset = 0;
		int width = 900/3;
		int height = 112;
		int heightOffset = 28;
		int xOffset = 97/2;
		int xOffsetTwo = 0;
		int tileWidth = 97;
		int indexer = 0;
		int offsetWater1 = 0;
		int offsetWater2 = 0;
		
		for(int i = 0; i < 4; i++) {
		waterPiece[i] = new HexPiece(offsetWater1+width-xOffset,y-heightOffset*3,6);
		offsetWater1 += tileWidth;
		}
		
		for (int i = 0; i < piece.length ; i ++){
			
			if(i<3){
			piece[i] = new HexPiece(x+width,y+0,indexArrayTile[indexer]);
			control.tile[i][0] = piece[i].xMiddle;
			control.tile[i][1] = piece[i].yMiddle;
			control.tile[i][2] = piece[i].index;
			indexer++;
			x += tileWidth;
			
			waterPiece[4] = new HexPiece(x-tileWidth+10,y,6);
			waterPiece[5] = new HexPiece(x+9+tileWidth*3,y,6);
			}
			
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
		
		for(int i = 14; i < waterPiece.length; i++) {
		waterPiece[i] = new HexPiece(offsetWater2+width-xOffset,y+height*4-heightOffset,6);
		offsetWater2 += tileWidth;
		}
		
		for(int i = 0; i < piece.length; i++) {
			if(piece[i].index == 2) {
				numberPiece[i] = new HexNumber(piece[i].x, piece[i].y, 1);
			}
		}
		
		
		//SPAWN NUMBERPIECES
		int indexNo = 0;
		
		
		for(int i = 0; i < numberPiece.length; i++) {
			numberPiece[i] = new HexNumber(piece[i].x, piece[i].y+20, indexArrayNumber[i]);
		}
		
		
	}
//END CLASS
}
