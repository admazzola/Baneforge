package wrap;

public class SpriteSheet {

	int num_frames=8;
	int frame_size=64;
	Image2D image;
	int speed = 8;
	
	
	SpriteSheet(){}
	
	
	SpriteSheet(int frame_size,int speed,Image2D image){
		this.frame_size = frame_size;
		this.speed = speed;
		this.image = image;
		this.num_frames = (int) Math.floor ( image.getWidth() / (float) frame_size);
	}
	
	

	
	
	
	
	
	
}
