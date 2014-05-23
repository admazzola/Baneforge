package pack;

public class SimpleGuiObject {
	
 final int MAX_TWEEN = 200;
	
	boolean is_open;
	
int width;
int height;
int x;
int y;

int mode;

String text="";
String buttonlabel="";

SimpleGuiObject(){}

 SimpleGuiObject(int x, int y, int width, int height, boolean openbydefault){
	 
	 this.x = x;
	 this.y = y;	 
	 this.width = width;
	 this.height = height;
	 this.is_open = openbydefault;
 }




int scrolloffset;
int itemsdrawn;

Image2D background;
Image2D foreground;


//for menus
int exitbutton_x;
int exitbutton_y;



int tweencount;

//int scroll_rect_y;

boolean BeingHovered(MouseCursor cursor){
	if(
	cursor.x > x
	&& cursor.x < x + width
	&& cursor.y > y 
	&& cursor.y < y + height){
	return true;
	}    	
	return false;
	
}

boolean BeingHovered_AndOpen(MouseCursor cursor){
	if(is_open &&
	cursor.x > x
	&& cursor.x < x + width
	&& cursor.y > y 
	&& cursor.y < y + height){
	return true;
	}    	
	return false;
	
}

boolean GenericToolbarBeingHovered(MouseCursor cursor){
	if(
	cursor.x > x
	&& cursor.x < x + width
	&& cursor.y > y 
	&& cursor.y < y + 25){
	return true;
	}    	
	return false;
	
}

boolean ExitButtonBeingHovered_AndOpen(MouseCursor cursor){
	
	
	if(is_open &&
	cursor.x > x + exitbutton_x
	&& cursor.x < x + exitbutton_x + 20
	&& cursor.y > y + exitbutton_y
	&& cursor.y < y + exitbutton_y+20 ){
	return true;
	}    	
	return false;
	
}

void tweenForward(int i){
	if(tweencount+i < MAX_TWEEN){
	tweencount+=i;
	}else{
	tweencount = MAX_TWEEN;
	}
}
void tweenBackward(int i){
	if(tweencount - i > 0){
	tweencount-=i;
	}else{
	tweencount=0;
	}
}

}
