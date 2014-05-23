package pack;

import org.lwjgl.input.Mouse;


public class MouseCursor {

	
	public Image2D whiteselector;
    public Image2D whitesquare;
    public Image2D heroselector_back;
    public Image2D heroselector_front;
    public int currentmousetile_x;//for heroes targetting abilities
    public int currentmousetile_y;
    public int x ;
    public int y;
    public int wheel_delta ;
    long stillcounter;
    
   int last_x;
   int last_y;
    
    void Poll_Mouse_Position(int SCREEN_HEIGHT,Camera cam, int TILE_SIZE, int MAP_SIZE, int SCREEN_X_TILES, int SCREEN_Y_TILES){
    	
    	 x = Mouse.getX();
         y = SCREEN_HEIGHT - Mouse.getY();
         wheel_delta = Mouse.getDWheel();
         
        // if(WithinMapBounds(MAP_SIZE)){
         currentmousetile_x = (x / TILE_SIZE)
 				+ (cam.x - (SCREEN_X_TILES / 2));
         currentmousetile_y = (y / TILE_SIZE)
 				+ (cam.y - (SCREEN_Y_TILES / 2));
        // }


         if(Math.abs(last_x - x) > 3 || Math.abs(last_y - y) > 3){
        	 stillcounter=0;
         }
         
         
         last_x=x;
         last_y=y;
         
    }
    
    
   boolean WithinMapBounds(int MAP_SIZE){
	   
	  if( currentmousetile_x > -1
		&& currentmousetile_x < MAP_SIZE
		&& currentmousetile_y > -1
		&& currentmousetile_y < MAP_SIZE){return true;}
	  
	  return false;
		
		
		
   }


 boolean WithinRect(int x, int y, int width, int height) {

	  if( this.x > x
				&& this.x < x+width
				&& this.y > y
				&& this.y < y+height){return true;}
	  
	return false;
}


}
