package pack;

import java.awt.event.KeyEvent;

public class Camera {
	 public int tileselect_x; 
	    public int tileselect_y;
	    public int x;
	    public int y;
	    
	    Camera(){
	    	tileselect_x=50;
	    	tileselect_y=50;
	        x = 50;
	        y = 50;  
	    	
	    }
	    
	    public void keyPressed(KeyEvent f) {
	    	
	        int key = f.getKeyCode();

	        //only move cursor if it will not fall off the map
	        if (key == KeyEvent.VK_UP && tileselect_y > 15) {
	        	tileselect_y --;
	        	if(y - tileselect_y > 8){y --;}
	        }

	        if (key == KeyEvent.VK_RIGHT && tileselect_x < 115) {
	        	tileselect_x ++;
	        	if(tileselect_x - x > 9){x ++;}
	        }

	        if (key == KeyEvent.VK_DOWN && tileselect_y < 115) {
	        	tileselect_y ++;
	        	if(tileselect_y - y > 6){y ++;}
	        }

	        if (key == KeyEvent.VK_LEFT && tileselect_x > 15) {
	        	tileselect_x --;
	        	if(x - tileselect_x > 10){x --;}
	        }
	 
	 
	    

	        
	    }
	    
	    public boolean TileSelection_Within_Bounds(){
	    	
	    	if(tileselect_x > -1 && tileselect_x<SharedData.MAP_SIZE && tileselect_y > -1 && tileselect_y<SharedData.MAP_SIZE){return true;}
	    	return false;
	    }
 public boolean Cam_Within_Bounds(){
	    	
	    	if(x > -1 && x<SharedData.MAP_SIZE && y > -1 && y<SharedData.MAP_SIZE){return true;}
	    	return false;
	    }
}
