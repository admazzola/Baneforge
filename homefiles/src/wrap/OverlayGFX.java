package wrap;

public class OverlayGFX {
	
boolean enabled = false;

int GFX_ID;//the model
int r=100;//need to implement!
int g=100;
int b=100;

int x;
int y;

int scale=100;

int currentframe;
int duration_left;//if this is >0 then the effect will last this number of turns!


boolean animate_continuously;  //if false, this will only animate at the beginning of a turn!
boolean bound_to_unit;  //if true, this will move with the unit that it is on top of !


}
