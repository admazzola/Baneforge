package wrap;

//USED ONLY BY THE BANEFORGE MAP EDITOR

public class LoggedAction {

	int x;
	int y;
	
	int layer;
	int type;
	
	void LogAction(int x, int y, int layer, int type){
		
		this.x = x;
		this.y = y;
		this.layer = layer;
		this.type = type;
		
	}
	
}
