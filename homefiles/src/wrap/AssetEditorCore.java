package wrap;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

import static org.lwjgl.opengl.GL11.*;

/*
 * Copyright 2012 Baneforge Project (Multiplayer RPG).
 */

/*NEED   


-New type of effect! ChangeStat, and Set Stat !!!!

-special event effect, like changing the time of day



-draw suffixes in dropdown menu and like everywhere :O

//buffer input letters and check for them so they dont get lost from lag?
//continuous backspace!
 * 
 * add a ton more parameters to conditions so they can do cool things!
 * 
 * 
Condition type
-edit stats
-temp change owner
-cripple movement speed
-silence
-pacify
 * 
 * 
 * 
 * 
 *MAKE A COLOR PALLETTE FOR THE COLOR STUFF
 * 
 */


public class AssetEditorCore{
	
	
	static AssetEditorCore mainedit; 
	
	
	static SharedData myshareddata = new SharedData();
	
	static String WINDOW_TITLE = "Baneforge Asset Editor " + myshareddata.CURRENTVERSION;
	
	
	SimpleAsset[][] myassets = new SimpleAsset[myshareddata.NUMBER_OF_ASSETGROUPS][myshareddata.MAX_NUM_ASSETS];
	
	
	String[] ASSETGROUPNAMES = {"Abilities","Effects","Conditions","Items","Units"};
	



	JFrame fcFrame;
	
	
	long Timer_mousemoved;
	long Timer_main;
	int framecount = 0;
	long Timer_savesuccess;
	
    int menu_open =0;
	int menu_slot_hovering =0;


	int currentassetgroup;
	int currentasset;
	//int currentassetslot;
	boolean[] parameter_is_visible = new boolean[SharedData.MAX_NUM_PARAMETERS];
	

		int menu_beingDragged=-1;
		int menu_dragoffset_x;
		int menu_dragoffset_y;
		
		//stats assignments  
			
	
		
		 
       int current_combo_scroll;//implement me!
			

public static final int MENU_ABILITYICONS= 11; 
public static final int MENU_ITEMICONS= 12; 
public static final int MENU_CONDITIONICONS= 13; 



public static final int MENU_TEXT_ABILITYSPECS = 20;//set of strings
public static final int MENU_TEXT_WEAPONTYPES = 21;
public static final int MENU_TEXT_STATS = 22;
public static final int MENU_TEXT_EQUIPSLOTS = 23;
public static final int MENU_TEXT_EFFECTTYPES = 24;
public static final int MENU_TEXT_LOOTGROUPS = 25;
public static final int MENU_TEXT_SHAPES = 26;
public static final int MENU_TEXT_MOVEMENTANIMATIONS = 27;
public static final int MENU_TEXT_PROJECTILES = 28;
public static final int MENU_TEXT_GFXIDS = 29;
public static final int MENU_TEXT_SOUNDIDS = 30;
public static final int MENU_TEXT_UNITCATEGORIES = 31;
public static final int MENU_TEXT_ALIGNMENTS = 32;
public static final int MENU_TEXT_UNITMODELS = 33;
public static final int MENU_TEXT_EQUIPMODELS = 34;

public static final int MENU_TEXT_MYABILITIES = 40;
public static final int MENU_TEXT_MYEFFECTS = 41;
public static final int MENU_TEXT_MYCONDITIONS = 42;
public static final int MENU_TEXT_MYITEMS = 43;
public static final int MENU_TEXT_MYUNITS = 44;

		
	//File AppDataFolder;
	 JFileChooser fc;
	 

    TextureLoader textureLoader = new TextureLoader();

	 String Stored_Save_File_Path=null;
	
	public static void main(String[] args) {
		
		 mainedit = new AssetEditorCore();
						
	}
	
	
	
	 
	 
	//public static final int STATUS_BAR_WIDTH =200;
	
	/** Screen width in pixels. */
	public int SCREEN_WIDTH = 800;
	/** Screen height in pixels. */
	public int SCREEN_HEIGHT = 500;
	

	  
	  public AssetEditorCore(){
		 
		 // AppDataFolder = new File(System.getenv("APPDATA") + "\\Baneforge");

        	 //fc = new JFileChooser();
        		fc = new JFileChooser(  SharedData.defaultDirectory()  ) ;//defaults to where baneforge is
        		fcFrame = new JFrame();
        		fcFrame.setLocation( Toolkit.getDefaultToolkit().getScreenSize().width / 2 ,Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        		fcFrame.setUndecorated(true);
        		fcFrame.setAlwaysOnTop(true);
        		fcFrame.setVisible(false);

        		new File(SharedData.baseDirectory() ).mkdir();
        		new File(SharedData.defaultDirectory() ).mkdir();
        		new File(SharedData.defaultDirectory() + "\\Skins").mkdir();
        		
        		CopyLocalFileIfNotFoundInAppData("wrap/assets/MyAssetBook.spl", SharedData.defaultDirectory() + "\\" + "MyAssetBook.spl");

        		
        		CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultBWIconSheet.png" ,  myshareddata.defaultDirectory() + "\\Skins\\" + "BWIconSheet.png" );
        		CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultOverlayGFXSheet.png" ,  myshareddata.defaultDirectory() + "\\Skins\\" + "OverlayGFXSheet.png" );
        		CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultItemIconSheet.png" ,  myshareddata.defaultDirectory() + "\\Skins\\" + "ItemIconSheet.png" );
        		
        		CopyLocalFileIfNotFoundInAppData("wrap/assets/About.txt",
        				SharedData.defaultDirectory() + "\\" + "About Baneforge.txt");

        		
             InitDisplay();
             
             LoadAssets();
             
             
             readAssetBookFromFile(SharedData.defaultDirectory() + "\\" + "MyAssetBook.spl");
            
             initgameOpenGL();
		  
             
             
           
             
		}
	  
	  

		//  public JFrame frm = new JFrame();
		  //public introPanel intropnl = new introPanel();
		  
	
	  CustomFont Verdana_12;
		CustomFont Verdana_14;
		CustomFont Verdana_16;
		CustomFont Verdana_18;
		CustomFont Verdana_20;
		
		//CustomFont VerdanaGreen_14;
		//CustomFont VerdanaBLK16;
		//CustomFont VerdanaBLK14;
		
		Image2D baneforgelogo;
		Image2D MasterBWIconSheet;
		Image2D MasterItemIconSheet;
		Image2D tridown;
		
		Image2D greenarrow;
		
		SimpleTreeItem[] assetgroupTreeItems = new SimpleTreeItem[myshareddata.NUMBER_OF_ASSETGROUPS];
		SimpleTreeItem[][] assetTreeItems = new SimpleTreeItem[myshareddata.NUMBER_OF_ASSETGROUPS][myshareddata.MAX_NUM_ASSETS];
		
		void LoadAssets() {
			
		
			for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
				for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++){
					myassets[i][j] = new SimpleAsset();
					assetTreeItems[i][j] = new SimpleTreeItem();
				}
			}
			
			
			
			Verdana_14 = new CustomFont( generate_tex_local("fonts/" + "verdana14.png"), parse_font_lookup_table("fonts/" + "verdana14.fnt") );
			Verdana_12 = new CustomFont( generate_tex_local("fonts/" + "verdana12_0.png") , parse_font_lookup_table("fonts/" + "verdana12.fnt") );	
			Verdana_16 = new CustomFont( generate_tex_local("fonts/" + "verdana16_0.png")  , parse_font_lookup_table("fonts/" + "verdana16.fnt") );
			Verdana_18 = new CustomFont( generate_tex_local("fonts/" + "verdana18_0.png")  , parse_font_lookup_table("fonts/" + "verdana18.fnt") );
			Verdana_20= new CustomFont( generate_tex_local("fonts/" + "verdana20_0.png")   , parse_font_lookup_table("fonts/" + "verdana20.fnt") );
			
		
			
			
			for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
				
				assetgroupTreeItems[i] =new SimpleTreeItem();
				assetgroupTreeItems[i].name = ASSETGROUPNAMES[i];
			}
			


			MasterBWIconSheet= generate_typeimage_ext(myshareddata.defaultDirectory() + "\\Skins\\" + "BWIconSheet.png");

			
			MasterItemIconSheet= generate_typeimage_ext(myshareddata.defaultDirectory() + "\\Skins\\" + "ItemIconSheet.png");
	
	
			baneforgelogo = generate_typeimage_local("asseteditor/BaneForgeLogoMini.png");
			tridown = generate_typeimage_local("asseteditor/tridown.png");
			
			
			greenarrow = generate_typeimage_local("asseteditor/greenarrow.png");
			
		}



boolean GameRunning;

public void initgameOpenGL(){
	
	
	
	
	
	//startGameRender();
	GameRunning=true;
	
	
	gameLoop();
}


void InitDisplay(){
	
	

	try {
setDisplayMode();

Display.setTitle(WINDOW_TITLE);



//enable textures since we're going to use these for our sprites


Display.setResizable(true);
Display.create();

glEnable(GL_TEXTURE_2D);

Display.setVSyncEnabled(true);


glDisable(GL_DEPTH_TEST);
//disable the OpenGL depth test since we're rendering 2D graphics

glMatrixMode(GL_PROJECTION);

glLoadIdentity();

glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);

glMatrixMode(GL_MODELVIEW);

glLoadIdentity();

glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);



String[] DisplayIconPaths = {"wrap/assets/misc/bookicon16.png","wrap/assets/misc/bookicon32.png","wrap/assets/misc/bookicon128.png"};
ByteBuffer[] BFIcons = textureLoader.getByteBuffer(DisplayIconPaths);
Display.setIcon(BFIcons);


	} catch (Exception e) {
		
	
		}
	
}



private boolean setDisplayMode() {
try {
DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(SCREEN_WIDTH, SCREEN_HEIGHT, -1, -1, -1, -1, 60, 60);
org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
"width=" + SCREEN_WIDTH,
"height=" + SCREEN_HEIGHT,
"freq=" + 60,
"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
});

return true;

} catch (Exception e) {

e.printStackTrace();


}

return false;

}
    
/**
 * Run the main game loop. This method keeps rendering the scene
 * and requesting that the callback update its screen.
 */
boolean firstcycle = true;
private void gameLoop() {
	
	
	
	while (!Display.isCloseRequested() && GameRunning) {
		
		if(framecount<64){framecount++;}else{framecount=0;}
		
		//config_UI_sizes_init();
		
		
		config_UI_sizes_continuous();
		
		organize_tree_items();
		
		/** make sure resizing on the fly doesn't break anything */
		glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		

		gameRendering();// let paint on the display (buffered)
						
		pollInput_mouse();//look for inputs and do things with them
		pollInput_keyboard();
		set_inputs_already_down();
		
		
		
		Display.update();//push the buffer to what the player sees
		Display.sync(60);
		firstcycle=false;
	}

	// clean up
	//soundManager.destroy();
	Display.destroy();
	System.exit(0);
}


SimpleGuiObject topmenu = new SimpleGuiObject();
SimpleGuiObject sidecontainer = new SimpleGuiObject();
SimpleGuiObject maincontainer = new SimpleGuiObject();

SimpleGuiObject filemenu = new SimpleGuiObject();
SimpleGuiObject assetmenu = new SimpleGuiObject();

void config_UI_sizes_continuous() {
	
	if(Display.isFullscreen()){
		
	}else{
	SCREEN_WIDTH = Display.getWidth();
	SCREEN_HEIGHT = Display.getHeight();
	}
	
	//SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1; // System.out.println(SCREEN_X_TILES);
	//SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;


	
	
	topmenu.x = 0;
	topmenu.y = 0;
	topmenu.width = SCREEN_WIDTH;
	topmenu.height = 30;
	
	sidecontainer.x =0;
	sidecontainer.y = topmenu.height;
	sidecontainer.width = 200 + (SCREEN_WIDTH - 600)/10;
	sidecontainer.height = SCREEN_HEIGHT - topmenu.height;
	
	maincontainer.x = sidecontainer.width;
	maincontainer.y = topmenu.height;
	maincontainer.width = SCREEN_WIDTH - sidecontainer.width;
	maincontainer.height = SCREEN_HEIGHT - topmenu.height;
	
	filemenu.x = topmenu.x + 10;
	filemenu.y = topmenu.y + topmenu.height;
	filemenu.height = 25*4;
	filemenu.width = 150;
	
	assetmenu.x = topmenu.x + 100 + 10;
	assetmenu.y = topmenu.y + topmenu.height;
	assetmenu.height = 25*9;
	assetmenu.width = 150;
}

int[] Number_of_AssetTreeItems = new int[myshareddata.NUMBER_OF_ASSETGROUPS];//...that are alphabetized
void organize_tree_items(){
	
	//seek and alphabetize
	for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
		
		Number_of_AssetTreeItems[i]=0;
		//int active_items_found =0;
		
		for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++ ){//this breaks if there are holes!!
			
			if(myassets[i][j].Is_Active){
				Number_of_AssetTreeItems[i]++;
			
			assetTreeItems[i][j].name = myassets[i][j].data[0];
			
			assetTreeItems[i][j].asset_ID = j;
			
			//System.out.println(assetTreeItems[i][j].name);
			
			
			}
			
		}
		
		
		
		
		
	//items are fresh every time
	    SortAssetTreeItems(i,myshareddata.MAX_NUM_ASSETS);
		
		
		
	}
	
	

}



void SortAssetTreeItems(int assetgroup,int size){
	String[] output = new String[size];
	int[] output_IDs = new int[size];
	
	int num_sorted = 0;
	//String[] buffer = new String[size*2];
	//int[] buffer_IDs = new int[size*2];
	
	//output[num_sorted] = assetTreeItems[assetgroup][0].name;
	//output_IDs[num_sorted] = assetTreeItems[assetgroup][0].asset_ID;
	//num_sorted++;
	
	
		for(int i=0;i<(myshareddata.MAX_NUM_ASSETS);i++){//for every other string to be sorted...(CAN HAVE HOLES NOW :) ) 

			if(myassets[assetgroup][i].Is_Active){
			
		
			boolean already_placed = false;
			boolean follows_everything=true;
			
			
			for(int j=0;j<num_sorted;j++){
				
				
				
				int compare=0;


				//compare = assetTreeItems[assetgroup][i].name.compareTo(output[j]); 
				
				String thisassetname = (assetTreeItems[assetgroup][i].name).toLowerCase();
				String otherassetnames = (output[j]).toLowerCase();
				compare = thisassetname.compareTo(otherassetnames);
				
				
				
				if(compare > 0){/* keep going thru the j loop*/}
				if(compare == 0){/* keep going thru the j loop*/}
				if(compare < 0 && !already_placed){//if the new string precedes this string, bump following down and throw it in the middle
					//this is buggy!
					
					///bump everything down
				//	for(int m=j;m<(num_sorted);m++){
				//		if(m<myshareddata.MAX_NUM_ASSETS){output[m+1] = output[m];}
				//	}
					for(int m=num_sorted;m>j;m--){
						if(m<myshareddata.MAX_NUM_ASSETS){
							output[m] = output[m-1];
							output_IDs[m] = output_IDs[m-1];
							}
					}
					
					
					

					//insert the string in the middle, in the created empty gap
					output[j] = assetTreeItems[assetgroup][i].name;
					output_IDs[j] = assetTreeItems[assetgroup][i].asset_ID;
					
				
					
					follows_everything=false;
					already_placed=true;
				}
				
				
			}
			
			if(follows_everything){
				
				output[num_sorted] = assetTreeItems[assetgroup][i].name;
				output_IDs[num_sorted] = assetTreeItems[assetgroup][i].asset_ID;
				//throw this right at the end if it is found to precede nothing!
				
			}
			
			
			num_sorted++;
		}
	
		}
	
	//return output;	
for(int i=0;i<Number_of_AssetTreeItems[assetgroup];i++){
			assetTreeItems[assetgroup][i].name = output[i];
			assetTreeItems[assetgroup][i].asset_ID = output_IDs[i];
		}
		
}






private static long		timerTicksPerSecond		= Sys.getTimerResolution();
public static long getTime() {
	// we get the "timer ticks" from the high resolution timer
	// multiply by 1000 so our end result is in milliseconds
	// then divide by the number of ticks in a second giving
	// us a nice clear time in milliseconds
	return (Sys.getTime() * 1000) / timerTicksPerSecond;
}


public static void sleep(long duration) {
	try {
		Thread.sleep((duration * timerTicksPerSecond) / 1000);
	} catch (InterruptedException inte) {
	}
}


int datacolumn_offset_x = 200;

int AssetFolderTreePositions[] = new int[myshareddata.NUMBER_OF_ASSETGROUPS];
int AssetTreePositions[][] = new int[myshareddata.NUMBER_OF_ASSETGROUPS][myshareddata.MAX_NUM_ASSETS];
private long					lastFpsTime;
private long					lastLoopTime;
int fps;
int CurrentParameterPositions[] = new int[100];
public void gameRendering() {
	//SystemTimer.sleep(lastLoopTime+10-SystemTimer.getTime());
	
	
	
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
	glDisable(GL_DEPTH_TEST);


	glMatrixMode(GL_MODELVIEW);

	glLoadIdentity();

	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	
	
	
	
	
	
	
	
	
	
	
	GL11.glColor4f(1.0f, 1.0f, 1.0f,1.0f);
	

	
	// work out how long its been since the last update, this
	// will be used to calculate how far the entities should
	// move this loop
	long delta = getTime() - lastLoopTime;
	lastLoopTime = getTime();
	lastFpsTime += delta;
	fps++;

	// update our FPS counter if a second has passed
	if (lastFpsTime >= 1000) {
		//Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
		lastFpsTime = 0;
		fps = 0;
	}
	
	
	 /*
	boolean leftButtonDown = Mouse.isButtonDown(0); // is left mouse button down.
	boolean rightButtonDown = Mouse.isButtonDown(1); // is right mouse button down.
 */
 
	

	GL11.glColor3f(1.0f, 1.0f, 1.0f);
	
	glDisable(GL_TEXTURE_2D);
	//draw simple GUI
	glBegin(GL_QUADS);
	
	
	
	glVertex2d(0,0);
	glVertex2d(100,0);
	glVertex2d(100,100);
	glVertex2d(0,100);
	
	
	
	GL11.glColor3f(1.0f, 1.0f, 1.0f);
	glVertex2d(sidecontainer.x, sidecontainer.y);
	glVertex2d(sidecontainer.x, sidecontainer.y+sidecontainer.height);
	glVertex2d(sidecontainer.x+sidecontainer.width, sidecontainer.y+sidecontainer.height);
	glVertex2d(sidecontainer.x+sidecontainer.width, sidecontainer.y);
	
	
	
	
	glEnd();
	
	
	
	
	
	
	
	//reset
	for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
		for(int j=0;j<Number_of_AssetTreeItems[i];j++){
			AssetTreePositions[i][j]=-1;
			AssetFolderTreePositions[i]=-1;
		}
	}
	
	

 	GL11.glEnable(GL_TEXTURE_2D);
	glColor3d(0,0,0);
	sidecontainer.itemsdrawn=0;

	for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){///draw sidebar tree
		
		String expandstatus = "(hidden)";
		if( assetgroupTreeItems[i].Is_Expanded){expandstatus = " ";}
		
		glColor3d(0,0,0);
		Verdana_18.drawString(sidecontainer.x+10,10 + sidecontainer.y + sidecontainer.scrolloffset + sidecontainer.itemsdrawn*20, assetgroupTreeItems[i].name + expandstatus);
		AssetFolderTreePositions[i] = sidecontainer.itemsdrawn;
		sidecontainer.itemsdrawn++;
		
		if(assetgroupTreeItems[i].Is_Expanded){
		for(int j=0;j<Number_of_AssetTreeItems[i];j++){
			
			if(myassets[i][assetTreeItems[i][j].asset_ID].data[2].equals(""+1)){
				if(assetTreeItems[currentassetgroup][j].asset_ID==currentasset  && currentassetgroup==i){
					glColor3d(0,0,0);
				}else{glColor3d(0.5,0.5,0.5);}
			}else{
			
				if(assetTreeItems[currentassetgroup][j].asset_ID==currentasset  && currentassetgroup==i){
					glColor3d(0.3,0,0);
				}else{	glColor3d(0.5,0.1,0.1);}
			}
			
			
			
			String name = assetTreeItems[i][j].name ;
			if(myassets[i][assetTreeItems[i][j].asset_ID].data[1].length() >0){name+= " (" + myassets[i][assetTreeItems[i][j].asset_ID].data[1] + ")";  }
			if(name.length() == 0){name = "{Blank Name}";}
		
			Verdana_14.drawString(sidecontainer.x+20,10 + sidecontainer.y + sidecontainer.scrolloffset + sidecontainer.itemsdrawn*20, name );
			AssetTreePositions[i][j] = sidecontainer.itemsdrawn;
			
			sidecontainer.itemsdrawn++;
			
	
			
			
			//}
			
		}
		}
		
		
	}
	
	
	
	
	glDisable(GL_TEXTURE_2D);
	//draw simple GUI
	glBegin(GL_QUADS);


GL11.glColor3f(1.0f, 1.0f, 1.0f);
	glVertex2d(maincontainer.x, maincontainer.y);
	glVertex2d(maincontainer.x, maincontainer.y+maincontainer.height);
	glVertex2d(maincontainer.x+maincontainer.width, maincontainer.y+maincontainer.height);
	glVertex2d(maincontainer.x+maincontainer.width, maincontainer.y);
	
	
	if(parameterslot_hoveringover>-1){
		
		int hidden_shift = 0;
		for(int i=0;i<myshareddata.MAX_NUM_PARAMETERS;i++){
			if(!parameter_is_visible[i]){hidden_shift++;}
		}
		
		int slot = parameterslot_hoveringover + hidden_shift;
		
		if(slot > 0){
		
		if(myshareddata.ParameterStrings[currentassetgroup][slot]!=null){
		
		int x = maincontainer.x;
		int y= parameterslot_hoveringover*40 + maincontainer.y  + maincontainer.scrolloffset + 10;
		int width = maincontainer.width;
		int height = 40;
		
		//parameterslot_hoveringover = (mouse_y - maincontainer.y - 20)/40;
		
		GL11.glColor3f(.97f, .97f, .97f);
		glVertex2d(x,y);
		glVertex2d(x+width,y);
		glVertex2d(x+width,y+height);
		glVertex2d(x,y+height);
		}}
	}
	
	if(parameterslot_editing>-1){
		
		int hidden_shift = 0;
		for(int i=0;i<=parameterslot_editing;i++){
			if(!parameter_is_visible[i]){hidden_shift++;}
		}
		
		int slot = parameterslot_editing - hidden_shift;
		
		int x = maincontainer.x;
		int y= slot*40 + maincontainer.y + maincontainer.scrolloffset + 10;
		int width = maincontainer.width;
		int height = 40;
		
		//parameterslot_hoveringover = (mouse_y - maincontainer.y - 20)/40;
		
		GL11.glColor3f(.9f, .9f, .9f);
		glVertex2d(x,y);
		glVertex2d(x+width,y);
		glVertex2d(x+width,y+height);
		glVertex2d(x,y+height);
		//glEnd();
	
		
		
	}
	
	glEnd();
	glBegin(GL_LINES);
	if(parameterslot_focused>-1){
		
		int hidden_shift = 0;
		for(int i=0;i<=parameterslot_focused;i++){
			if(!parameter_is_visible[i]){hidden_shift++;}
		}
		
		int slot = parameterslot_focused - hidden_shift;
		
		int x = maincontainer.x;
		int y= parameterslot_focused*40 + maincontainer.y + maincontainer.scrolloffset + 10;
		int width = maincontainer.width;
		int height = 40;
		
		
		GL11.glColor3f(.3f, .3f, .3f);
		glVertex2d(x,y);
		glVertex2d(x+width,y);
		glVertex2d(x+width,y+height);
		glVertex2d(x,y+height);
		//glEnd();
	
		
		
	}
	

GL11.glColor3f(0, 0, 0);


glVertex2d(sidecontainer.x+sidecontainer.width, sidecontainer.y+sidecontainer.height);
glVertex2d(sidecontainer.x+sidecontainer.width, sidecontainer.y);

//glVertex2d(sidecontainer.x+sidecontainer.width, sidecontainer.y);


glEnd();


GL11.glEnable(GL_TEXTURE_2D);
	


GL11.glColor3f(1,1,1);


if(parameterslot_focused>-1){
	
	int hidden_shift = 0;
	for(int i=0;i<=parameterslot_focused;i++){
		if(!parameter_is_visible[i]){hidden_shift++;}
	}
	
	int slot = parameterslot_focused + hidden_shift;
	
	int x = maincontainer.x;
	int y= parameterslot_focused*40 + maincontainer.y + maincontainer.scrolloffset + 10;
	int width = maincontainer.width;
	int height = 40;
	
	
	int param = CurrentParameterPositions[parameterslot_focused];
	if(myshareddata.ParameterType_info[currentassetgroup][param] >= myshareddata.COMBOTEXT_MYABILITIES){
	
	greenarrow.draw(x+width - 30,y+10);
	//glEnd();

	}
	
}






GL11.glColor3f(0f, 0f, 0f);
	
	//draw parameter options
	int datacount = 0;
	
	maincontainer.itemsdrawn=0;
	if( myassets[currentassetgroup][currentasset].Is_Active){
	for(int i=0;i<SharedData.MAX_NUM_PARAMETERS;i++){
		
		//boolean enabled = true;
		parameter_is_visible[i]=true;
		
		for(int z = 0;z<=1;z++){
		if(myshareddata.ParameterRequirement[currentassetgroup][i][z] > 0 ){//if a requirement is found for this parameter

			
			int required_state = myshareddata.ParameterRequirement_State[currentassetgroup][i][z];
			
			int tag_ID = myshareddata.ParameterRequirement[currentassetgroup][i][z];
			int Tagged_parameter_ID = myshareddata.RequirementTag[currentassetgroup][tag_ID];
			
			
			
			String actual_state_data = myassets[currentassetgroup][currentasset].data[Tagged_parameter_ID];
			if(actual_state_data==null || actual_state_data.equals("")){actual_state_data=""+0;}
			int actual_state = Integer.parseInt( actual_state_data );
			
		if(required_state !=  actual_state  ){
			//enabled=false;
			parameter_is_visible[i]=false;
		}
		
		if(!parameter_is_visible[myshareddata.RequirementTag[currentassetgroup][myshareddata.ParameterRequirement[currentassetgroup][i][z]]]){//this allows for cascading disappearances
			parameter_is_visible[i]=false;
		}
		
		}
		
	
		
				
		}
		
		if(parameter_is_visible[i]){
			GL11.glColor3f(0f, 0f, 0f);	 
		String label = myshareddata.ParameterStrings[currentassetgroup][i];
		
		
		
		if(label!=null){
		Verdana_14.drawString(maincontainer.x+20,maincontainer.y + maincontainer.scrolloffset + 20 + datacount*40, label +":");
		maincontainer.itemsdrawn++;
		
		
		/* if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_BOOLEAN ){
				if(myassets[currentassetgroup][currentasset].data[i]==null || myassets[currentassetgroup][currentasset].data[i].equals("")){myassets[currentassetgroup][currentasset].data[i]=""+0;}
		 }*/
		
		String data = myassets[currentassetgroup][currentasset].data[i];
		
		
		
		 if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_BOOLEAN ){
			// if(data==null || data.equals("")){data=""+0;}
			 String s = "false";
			 Boolean b = StringToBoolean(data);
			 
			 if(b==true){s="true";}
			 
			 if(s.equals("true")){GL11.glColor3f(0.1f, 0.5f, 0.1f);}
			 if(s.equals("false")){GL11.glColor3f(0.5f, 0.1f, 0.1f);}
			 
			 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, s);
			 
		 }
		 
		 GL11.glColor3f(0f, 0f, 0f);
		 if(data!=null){
		 if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_ALPHANUMERIC ){
			 
			 if(parameterslot_editing==i && Timer_main/32 ==1){data+="|";}
			 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, data);
			 
		 }
		 
		 if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_ALPHANUMERICHUGE ){
			 if(parameterslot_editing==i && Timer_main/32 ==1 ){data+="|";}
			 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, data);
			 
		 }
		 
		 if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_NUMERIC ){
			 if(parameterslot_editing==i && Timer_main/32 ==1){data+="|";}
			 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, data);
			 
		 }
		 
		 if( myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_NUMERIC_PERCENT ){
			 if(parameterslot_editing==i && Timer_main/32 ==1){data+="|";}
			 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, data + " %");
			 
		 } 
		 
		 
		 } //end null check
		 
		 if(  myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_COMBOICONS ){
		 if(data==null || data.equals("")){data=""+0;}
			 
			 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_ABILITYICONS){
				 GL11.glColor3f(1f, 1f, 1f);
				 DrawAbilityIconFromSheet(Integer.parseInt(data),maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset + 10 + datacount*40);
				 
			 }
			 
			 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_ITEMICONS){
				 GL11.glColor3f(1f, 1f, 1f);
				 DrawItemIconFromSheet(Integer.parseInt(data),maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset + 10 + datacount*40);
				 
			 }
			 
			 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_CONDITIONICONS){
				 GL11.glColor3f(1f, 1f, 1f);
				 DrawConditionIconFromSheet(Integer.parseInt(data),maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset + 10 + datacount*40);
				 
			 }
			 
			 
		 }
		 
		 if(  myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_COMBOTEXT ){
			 if(data==null || data.equals("")){data=""+0;}
			 GL11.glColor3f(0f, 0f, 0f);
			 if(myshareddata.ParameterType_info[currentassetgroup][i]  <=myshareddata.COMBOTEXT_EQUIPMODELS){
				
				 
				 int StringSet = myshareddata.ParameterType_info[currentassetgroup][i];
				 
				 if(myshareddata.ComboStrings[StringSet].length > Integer.parseInt(data)){
				 
				 String selection = myshareddata.ComboStrings[StringSet][Integer.parseInt(data)];
				 
				 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
				 GL11.glColor3f(1f, 1f, 1f);
				 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
				
				 }
				// MENU_TEXT_STATS
			 }else if(myshareddata.ParameterType_info[currentassetgroup][i]  >myshareddata.COMBOTEXT_EQUIPMODELS){
				 
				 
				 
				 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_MYABILITIES){
					 
					 String selection = "None";
					 if(Integer.parseInt(data) > 0){  selection = myassets[0][Integer.parseInt(data) - 1].data[0];}
				 
				 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
				 GL11.glColor3f(1f, 1f, 1f);
				 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
					
				 }
				 
				 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_MYEFFECTS){
					 String selection = "None";
					 if(Integer.parseInt(data) > 0){  selection = myassets[1][Integer.parseInt(data) - 1].data[0];}
					 
					 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
					 GL11.glColor3f(1f, 1f, 1f);
					 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
						
					 }
				 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_MYCONDITIONS){
					 String selection = "None";
					 if(Integer.parseInt(data) > 0){  selection = myassets[2][Integer.parseInt(data) - 1].data[0];}
					 
					 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
					 GL11.glColor3f(1f, 1f, 1f);
					 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
						
					 }
				 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_MYITEMS){
					 String selection = "None";
					 if(Integer.parseInt(data) > 0){  selection = myassets[3][Integer.parseInt(data) - 1].data[0];}
					 
					 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
					 GL11.glColor3f(1f, 1f, 1f);
					 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
						
					 }
				 
				 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_MYUNITS){
					 String selection = "None";
					 if(Integer.parseInt(data) > 0){  selection = myassets[4][Integer.parseInt(data) - 1].data[0];}
					 
					 Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x,maincontainer.y + maincontainer.scrolloffset +20 + datacount*40, selection);
					 GL11.glColor3f(1f, 1f, 1f);
					 tridown.draw(maincontainer.x+20+datacolumn_offset_x + Verdana_14.getStringPixelLength(selection) + 10,maincontainer.y + maincontainer.scrolloffset +15 + datacount*40);
					 }
			 }
			 
			 
		 }
		 
		 
		 
		
		 
		
		
		
		CurrentParameterPositions[datacount] = i;//accounts for disabled parameters so we know the true parameter that was clicked!
		datacount++;
		}
		}//end check for null label
	
		
	}
	}
	
	
	
	
if(parameterslot_editing>-1){
		
		int hidden_shift = 0;
		for(int i=0;i<=parameterslot_editing;i++){
			if(!parameter_is_visible[i]){hidden_shift++;}
		}
		
		int slot = parameterslot_editing - hidden_shift;
	
		
		
		
		if(menu_open==MENU_ABILITYICONS){
			
			
			
			glEnable(GL_TEXTURE_2D);
			GL11.glColor3f(1, 1, 1);
			for(int i=0;i<50;i++){
				
				int j = i + current_combo_scroll*10;
				 DrawAbilityIconFromSheet(j,maincontainer.x-100+datacolumn_offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
				 
			}
		
		}
        if(menu_open==MENU_ITEMICONS){  
        
        	int x =maincontainer.x-100+datacolumn_offset_x;
			int y = slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 ;
			int width =400;
			int height=200;
			
			glDisable(GL_TEXTURE_2D);
			//draw simple GUI
			glBegin(GL_QUADS);
			GL11.glColor4f(0,0,0,0.8f);
			glVertex2d(x,y);
			glVertex2d(x,y+height);
			glVertex2d(x+width,y+height);
			glVertex2d(x+width,y);
			glEnd();
        	glEnable(GL_TEXTURE_2D);
    		GL11.glColor3f(1, 1, 1);
			for(int i=0;i<50;i++){
				
				int j = i + current_combo_scroll*10;
				 DrawItemIconFromSheet(j,maincontainer.x-100+datacolumn_offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
				 
			}
		
		}
        if(menu_open==MENU_CONDITIONICONS){
			
        	glEnable(GL_TEXTURE_2D);
    		GL11.glColor3f(1, 1, 1);
			for(int i=0;i<50;i++){
			
				int j = i + current_combo_scroll*10;
				 DrawConditionIconFromSheet(j,maincontainer.x-100+datacolumn_offset_x + (i%10)*30,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*30);
				 
			}
		
		}
        
       
 if(menu_open >= MENU_TEXT_ABILITYSPECS){
        	
    		
    		int num_of_options = 6;
    		
    		 for(int m=0;m<=myshareddata.COMBOTEXT_EQUIPMODELS;m++){
    			 if(menu_open==MENU_TEXT_ABILITYSPECS+m){
    		if(myshareddata.ComboStrings[m].length < num_of_options){ num_of_options = myshareddata.ComboStrings[m].length;}
    		 }}
    		 
    		 
    		 int[] number_of_assets = new int[myshareddata.NUMBER_OF_ASSETGROUPS];
    		 
    		 for(int k=0;k<myshareddata.NUMBER_OF_ASSETGROUPS;k++){
    			 number_of_assets[k]=0;
    			 for(int p=0;p<myshareddata.MAX_NUM_ASSETS;p++){
    				 if(myassets[k][p].Is_Active){number_of_assets[k]++;}
    			 }
    		 }
    		 
    		 
    		 
    		 for(int n=0;n<5;n++){
    		 if(menu_open==MENU_TEXT_MYABILITIES+n){if(number_of_assets[n]+1 < num_of_options){num_of_options = number_of_assets[n]+1;}}
    		 }
    	
    		 
    		int x =maincontainer.x+0+datacolumn_offset_x;
			int y = slot*40 + maincontainer.y + maincontainer.scrolloffset + 30 ;
			int width =260;
			int height=20*num_of_options;
			
			glDisable(GL_TEXTURE_2D);
			//draw simple GUI
			glBegin(GL_QUADS);
			GL11.glColor4f(0,0,0,.5f);//shading
			glVertex2d(x,y);
			glVertex2d(x,y+height+2);
			glVertex2d(x+width+2,y+height+2);
			glVertex2d(x+width+2,y);
			
			
			GL11.glColor3f(.95f,.95f, .95f);//actualmenu
			glVertex2d(x,y);
			glVertex2d(x,y+height);
			glVertex2d(x+width,y+height);
			glVertex2d(x+width,y);
			
			int slot_hovering = (mouse_y - slot*40 -maincontainer.y - maincontainer.scrolloffset - 30)/20;
			if(slot_hovering >=0 && slot_hovering < 6){
			GL11.glColor3f(.9f,.9f, .9f);//hover
			glVertex2d(x,y+slot_hovering*20);
			glVertex2d(x,y+20+slot_hovering*20);
			glVertex2d(x+width,y+20+slot_hovering*20);
			glVertex2d(x+width,y+slot_hovering*20);
			}
			
			glEnd();
			glBegin(GL_LINES);
			GL11.glColor3f(0,0,0);
			glVertex2d(x,y);
			glVertex2d(x,y+height);
			glVertex2d(x,y+height);
			glVertex2d(x+width,y+height);
			glVertex2d(x+width,y+height);
			glVertex2d(x+width,y);
			glVertex2d(x+width,y);
			glVertex2d(x,y);
			glEnd();
			glEnable(GL_TEXTURE_2D);
			GL11.glColor3f(0,0,0);
        	
       
        for(int m=MENU_TEXT_ABILITYSPECS;m<=MENU_TEXT_EQUIPMODELS;m++){
        	
        	int z = m - MENU_TEXT_ABILITYSPECS;
        
        	if(menu_open==MENU_TEXT_ABILITYSPECS+z){
        		for(int i=0;i<num_of_options;i++){ 
        			Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x, slot*40 + maincontainer.y + maincontainer.scrolloffset + 30 + 20*i, myshareddata.ComboStrings[z][i+current_combo_scroll]);
        		}
        		
        	}
        }
        
		
		
        for(int n=0;n<5;n++){
        if(menu_open==MENU_TEXT_MYABILITIES+n){//System.out.println(num_of_options);
        	for(int i=0;i<num_of_options;i++){
        		if(i+current_combo_scroll==0){
        		Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x, slot*40 + maincontainer.y + maincontainer.scrolloffset + 30 + 20*i, "None");
        		}else{
        			
        		int j = i+current_combo_scroll-1;
        		
        		String name = assetTreeItems[n][j].name ;
        		if(myassets[n][assetTreeItems[n][j].asset_ID].data[1].length() >0){name+= " (" + myassets[n][assetTreeItems[n][j].asset_ID].data[1] + ")";  }
    			if(name == null){name = "{Blank Name}";}
    			else if(name.length() == 0){name = "{Blank Name}";}
        			
    			Verdana_14.drawString(maincontainer.x+20+datacolumn_offset_x, slot*40 + maincontainer.y + maincontainer.scrolloffset + 30 + 20*i, name );
    	
        		
        		}}
        }
        }
        
        
        
        }
        
        
        //fix me and add more
        
        
        
	
		
	}
	
	
	
	glDisable(GL_TEXTURE_2D);
	//draw simple GUI
	glBegin(GL_QUADS);


	
	GL11.glColor3f(.95f,.95f, .95f);
	glVertex2d(topmenu.x,topmenu.y);
	glVertex2d(topmenu.x,topmenu.y+topmenu.height);
	glVertex2d(topmenu.x+topmenu.width, topmenu.y+topmenu.height);
	glVertex2d(topmenu.x+topmenu.width, topmenu.y);
	glEnd();
	glEnable(GL_TEXTURE_2D);
	GL11.glColor3f(0f, 0f, 0f);
	//draw topmenu menus and stuff
	Verdana_14.drawString(topmenu.x+20,topmenu.y+8, "File");
	Verdana_14.drawString(topmenu.x+120,topmenu.y+8, "Assets");
	GL11.glColor3f(1f, 1f, 1f);
	baneforgelogo.draw(SCREEN_WIDTH - 130, topmenu.y + 0);
	
	glEnd();
	glBegin(GL_LINES);
	GL11.glColor3f(0f, 0f, 0f);
	glVertex2d(SCREEN_WIDTH, sidecontainer.y);
	glVertex2d(sidecontainer.x, sidecontainer.y);
	
	glEnd();
	
	
	/*
	if(menu_open>0){
		
		glDisable(GL_TEXTURE_2D);
		//draw simple GUI
		glBegin(GL_QUADS);

		GL11.glColor4f(0,0,0,.5f);
	glVertex2d(0,topmenu.height);
	glVertex2d(SCREEN_WIDTH,topmenu.height);
	glVertex2d(SCREEN_WIDTH,SCREEN_HEIGHT);
	glVertex2d(0,SCREEN_HEIGHT);
	glEnd();

	}*/
	
	if(menu_open==1){
		
		
		glDisable(GL_TEXTURE_2D);
		//draw simple GUI
		glBegin(GL_QUADS);

		GL11.glColor4f(0,0,0,.6f);//shadow
		glVertex2d(filemenu.x,filemenu.y);
		glVertex2d(filemenu.x,filemenu.y+filemenu.height+3);
		glVertex2d(filemenu.x+filemenu.width+3, filemenu.y+filemenu.height+3);
		glVertex2d(filemenu.x+filemenu.width+3, filemenu.y);
		
		GL11.glColor3f(.95f,.95f, .95f);//actual menu
		glVertex2d(filemenu.x,filemenu.y);
		glVertex2d(filemenu.x,filemenu.y+filemenu.height);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y+filemenu.height);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y);
		
		if(mouse_within_container(filemenu,mouse_x,mouse_y)){
		GL11.glColor3f(.9f,.9f, .9f);//hover highlight
		glVertex2d(filemenu.x,filemenu.y+(menu_slot_hovering*25));
		glVertex2d(filemenu.x,filemenu.y+25+(menu_slot_hovering*25));
		glVertex2d(filemenu.x+filemenu.width, filemenu.y+25+(menu_slot_hovering*25));
		glVertex2d(filemenu.x+filemenu.width, filemenu.y+(menu_slot_hovering*25));
		
		}
		
		glEnd();
		glBegin(GL_LINES);
		GL11.glColor3f(0f,0f, 0f);
		glVertex2d(filemenu.x,filemenu.y);
		glVertex2d(filemenu.x,filemenu.y+filemenu.height);
		glVertex2d(filemenu.x,filemenu.y+filemenu.height);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y+filemenu.height);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y+filemenu.height);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y);
		glVertex2d(filemenu.x+filemenu.width, filemenu.y);
		glVertex2d(filemenu.x,filemenu.y);
		glEnd();
		glEnable(GL_TEXTURE_2D);
		GL11.glColor3f(0,0,0);
		Verdana_14.drawString(filemenu.x+5,filemenu.y+5+(25*0), "New Asset Book");
		Verdana_14.drawString(filemenu.x+5,filemenu.y+5+(25*1), "Open...");
		Verdana_14.drawString(filemenu.x+5,filemenu.y+5+(25*2), "Save");
		Verdana_14.drawString(filemenu.x+5,filemenu.y+5+(25*3), "Save As...");

	}
	
if(menu_open==2){
		
		
		glDisable(GL_TEXTURE_2D);
		//draw simple GUI
		glBegin(GL_QUADS);

		GL11.glColor4f(0,0,0,.6f);//shadow
		glVertex2d(assetmenu.x,assetmenu.y);
		glVertex2d(assetmenu.x,assetmenu.y+assetmenu.height+3);
		glVertex2d(assetmenu.x+assetmenu.width+3, assetmenu.y+assetmenu.height+3);
		glVertex2d(assetmenu.x+assetmenu.width+3, assetmenu.y);
		
		
		GL11.glColor3f(.95f,.95f, .95f);//actual menu
		glVertex2d(assetmenu.x,assetmenu.y);
		glVertex2d(assetmenu.x,assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y);
		
		if(mouse_within_container(assetmenu,mouse_x,mouse_y)){
			GL11.glColor3f(.9f,.9f, .9f);//hover highlight
			glVertex2d(assetmenu.x,assetmenu.y+(menu_slot_hovering*25));
			glVertex2d(assetmenu.x,assetmenu.y+25+(menu_slot_hovering*25));
			glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y+25+(menu_slot_hovering*25));
			glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y+(menu_slot_hovering*25));
			
			}
		
		glEnd();
		glBegin(GL_LINES);
		GL11.glColor3f(0f,0f, 0f);
		glVertex2d(assetmenu.x,assetmenu.y);
		glVertex2d(assetmenu.x,assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x,assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y+assetmenu.height);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y);
		glVertex2d(assetmenu.x+assetmenu.width, assetmenu.y);
		glVertex2d(assetmenu.x,assetmenu.y);
		glEnd();
		glEnable(GL_TEXTURE_2D);
		GL11.glColor3f(0,0,0);
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*0), "Duplicate This Asset");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*1), "New Ability");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*2), "New Effect");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*3), "New Condition");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*4), "New Item");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*5), "New Unit");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*6), "Delete This Asset");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*7), "Import Asset");
		Verdana_14.drawString(assetmenu.x+5,assetmenu.y+5+(25*8), "Export Asset");
	}

	
    
}//end gamerendering


int parameterslot_hoveringover = -1;
int parameterslot_focused = -1;
int parameterslot_editing = -1;

boolean[] Mouse_MouseAlreadyClicked = new boolean[3];
boolean[] Keyboard_KeyAlreadyDown = new boolean[255];


int mouse_x;
int mouse_y; 
int stored_mouse_x;
int stored_mouse_y;
boolean menujustopened = false;

long count;

public void pollInput_mouse() {
	
	 menujustopened = false;
	
	
	   mouse_x = Mouse.getX();
	    mouse_y = SCREEN_HEIGHT - Mouse.getY();
	    

		int mousewheel_delta = Mouse.getDWheel();
		
		
	    
	    
	    if(mouse_x!=stored_mouse_x || mouse_y!=stored_mouse_y){Timer_mousemoved=0;}
	    Timer_mousemoved+=lastLoopTime/1000000;
	    
	    
	    
	    count+=lastLoopTime;
	    if(count>10000000){
	    	count=0;
	    	Timer_main++;
	    	if(Timer_savesuccess>0){Timer_savesuccess--;if(Timer_savesuccess<=0){Display.setTitle(WINDOW_TITLE);}}
	    	}
	    if(Timer_main>=64){Timer_main=0;}
	  

		
		
	    
	    stored_mouse_x = mouse_x;
		stored_mouse_y = mouse_y;
		
	    
		if(menu_open==0){  
	    
	    
	    
	    parameterslot_hoveringover = -1;
	if(mouse_within_container(maincontainer,mouse_x,mouse_y) && myassets[currentassetgroup][currentasset].Is_Active){
		parameterslot_hoveringover = (mouse_y - maincontainer.y - maincontainer.scrolloffset - 20)/40;
	
		
		if(Timer_mousemoved>1000 && mouse_x < maincontainer.x + 100){

		int hidden_shift = 0;
		for(int i=0;i<=parameterslot_hoveringover;i++){
			if(!parameter_is_visible[i]){hidden_shift++;}
		}
		int slot = parameterslot_hoveringover + hidden_shift;
		//System.out.println("des"+slot);
		
		String help = myshareddata.ParameterType_helptext[currentassetgroup][slot];
		if(help!=null){
			
		// pixellength = Verdana_14.getStringPixelLength(help);
			
		GL11.glColor3f(1f, 0.99f, 0.9f);
		glDisable(GL_TEXTURE_2D);
		//draw simple GUI
		glBegin(GL_QUADS);
		
		int rectwidth = 450;
		int rectheight = 10 + 20*Verdana_14.GetStringNumLines(help, 400);
		
		glVertex2d(mouse_x,mouse_y);
		glVertex2d(mouse_x+rectwidth,mouse_y);
		glVertex2d(mouse_x+rectwidth,mouse_y-rectheight);
		glVertex2d(mouse_x,mouse_y-rectheight);
		
		glEnd();
		
        glBegin(GL_LINES);
        GL11.glColor3f(0f, 0f, 0f);
		glVertex2d(mouse_x,mouse_y);
		glVertex2d(mouse_x+rectwidth,mouse_y);
		
		glVertex2d(mouse_x+rectwidth,mouse_y);
		glVertex2d(mouse_x+rectwidth,mouse_y-rectheight);
		
		glVertex2d(mouse_x+rectwidth,mouse_y-rectheight);
		glVertex2d(mouse_x,mouse_y-rectheight);
		
		glVertex2d(mouse_x,mouse_y-rectheight);
		glVertex2d(mouse_x,mouse_y);
		glEnd();
		glEnable(GL_TEXTURE_2D);
		
		Verdana_14.drawString(mouse_x+10,mouse_y - rectheight + 10, help, 400, SCREEN_HEIGHT, 20);}
		}
		
		
		
	}
	
	
	//mouse wheel
	
	
	
	/** Scroll Wheel Upwards */
	if (mousewheel_delta > 0 && menu_open==0) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){maincontainer.scrolloffset+= mousewheel_delta;}
		if(mouse_within_container(sidecontainer,mouse_x,mouse_y)){sidecontainer.scrolloffset+= mousewheel_delta;}
	}
	
	
	
	
	/** Scroll Wheel Downwards */
	if (mousewheel_delta < 0 && menu_open==0) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){maincontainer.scrolloffset+= mousewheel_delta;}
		if(mouse_within_container(sidecontainer,mouse_x,mouse_y)){sidecontainer.scrolloffset+= mousewheel_delta;}
		
	}
	
	
	int max_scroll = 0;
	
	max_scroll = maincontainer.itemsdrawn*-40   + maincontainer.height - 40;
	if(maincontainer.scrolloffset<max_scroll){maincontainer.scrolloffset=max_scroll;}
	max_scroll = sidecontainer.itemsdrawn*-20   + sidecontainer.height - 40;
	if(sidecontainer.scrolloffset<max_scroll){sidecontainer.scrolloffset=max_scroll;}
	
	if(maincontainer.scrolloffset>0){maincontainer.scrolloffset=0;}
	if(sidecontainer.scrolloffset>0){sidecontainer.scrolloffset=0;}
  //END SCROLL STUFF
 
    
    if (mousepressed()) {
    	
    	parameterslot_editing=-1;

    	//int AssetFolderTreePositions[] = new int[myshareddata.NUMBER_OF_ASSETGROUPS];
    	//int AssetTreePositions[][] = new int[myshareddata.NUMBER_OF_ASSETGROUPS][myshareddata.MAX_NUM_ASSETS];
    	
    	//click on side tree
    	if(mouse_within_container(sidecontainer,mouse_x,mouse_y)){
    		
    		//int slot_clicked = -1;
    		
    		int slot_clicked = (mouse_y - sidecontainer.y - sidecontainer.scrolloffset - 10)/20;
    		
    		// slot_clicked += sidecontainer.scrolloffset;

    		 for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
    			 if(AssetFolderTreePositions[i]==slot_clicked){assetgroupTreeItems[i].ToggleExpanded();}
    			 
    			 for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++){
    				if(AssetTreePositions[i][j]==slot_clicked){
    					currentassetgroup=i;
    					currentasset= assetTreeItems[i][j].asset_ID;
    					maincontainer.scrolloffset=0;
    					current_combo_scroll=0;
    					}
    			 }
    		 }
    		
    	}
    	
    	if(mouse_within_container(maincontainer,mouse_x,mouse_y) && myassets[currentassetgroup][currentasset].Is_Active){
    		
    		int slot_clicked = (mouse_y - maincontainer.y - maincontainer.scrolloffset - 20)/40;
    	
    		if(mouse_x > maincontainer.x+20+ datacolumn_offset_x && mouse_x < maincontainer.x+20+ datacolumn_offset_x + 200){
    		
    		if(parameterslot_focused==slot_clicked){    		
    		ClickedParameterOption(CurrentParameterPositions[slot_clicked]);
    		}else{
    			parameterslot_focused=slot_clicked;
    			
    		}
    		
    		}else{
    			parameterslot_focused=slot_clicked;
    		}
    		
    		
    		if(mouse_x > maincontainer.x+maincontainer.width - 50 ){
    			
    		
    			int newassetgroup = myshareddata.ParameterType_info[currentassetgroup][CurrentParameterPositions[slot_clicked]] - myshareddata.COMBOTEXT_MYABILITIES ;
    			int newassetnum = StringToInteger( myassets[currentassetgroup][currentasset].data[CurrentParameterPositions[slot_clicked]]) -1;
    			
    			if(newassetgroup>-1){
    			currentassetgroup = newassetgroup;
    			}
    			if(newassetnum>0){
    			currentasset = newassetnum;
    			}
    			maincontainer.scrolloffset=0;
				current_combo_scroll=0;
    		
    		}
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    }
    
    

}//end no menu being open
		
		//opening menus
		if(mousepressed()){
		    	
		    	if(mouse_within_container(topmenu,mouse_x,mouse_y)){
		    		if(mouse_x < 60){OpenMenu(1);}
		    		if(mouse_x > 100 && mouse_x < 200){OpenMenu(2);}
		    		
		    	}
		    	
		    
		    }
		
    	
    	
    	
if(menu_open==1 && !menujustopened){	
	
	if(mouse_within_container(filemenu,mouse_x,mouse_y)){
		menu_slot_hovering = (mouse_y - filemenu.y)/25;
		
		if(mousepressed()){
			if(menu_slot_hovering==0){New_Spellbook();
			}
			
			if(menu_slot_hovering==1){
				fcFrame.setVisible(true);
				if (fc.showOpenDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {
					
					
					
					
					fcFrame.toFront();
					fcFrame.setAlwaysOnTop(true);
					
					int okCxl = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(fcFrame), "Wipe previous Assetbook?", "Confirm", JOptionPane.YES_NO_OPTION);
				
					if (okCxl == JOptionPane.YES_NO_OPTION) {
						for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
							for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++){
								myassets[i][j] = new SimpleAsset();//delete
							}
							
						}
						
					} else {
						
					}
					
				
					
					
					Stored_Save_File_Path = fc.getSelectedFile().getPath();
					readAssetBookFromFile(fc.getSelectedFile().getPath());
					
					
					
					

					
				}
				fcFrame.setVisible(false);
			}
			
			if(menu_slot_hovering==2){
				
				if(Stored_Save_File_Path==null){
				fcFrame.setVisible(true);
				if (fc.showSaveDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {

					Stored_Save_File_Path = fc.getSelectedFile().getPath();

					writeAssetBookToFile(fc.getSelectedFile().getPath());
					

				}
				fcFrame.setVisible(false);
				}else{
					
						writeAssetBookToFile(Stored_Save_File_Path);
                        Timer_savesuccess = 300;
                        Display.setTitle(WINDOW_TITLE + "  --  save successful!");
				
				}
				
			}
			
			if(menu_slot_hovering==3){
		
				fcFrame.setVisible(true);
				if (fc.showSaveDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {

					Stored_Save_File_Path = fc.getSelectedFile().getPath();

					writeAssetBookToFile(fc.getSelectedFile().getPath());
					

				}
				fcFrame.setVisible(false);
				
				
			}
		}
		
	}
	
}
if(menu_open==2 && !menujustopened){	
	if(mouse_within_container(assetmenu,mouse_x,mouse_y)){
		menu_slot_hovering = (mouse_y - assetmenu.y)/25;
		if(mousepressed()){
			
				if(menu_slot_hovering==0){Duplicate_Asset();}
				if(menu_slot_hovering==1){Create_Asset(0);}
				if(menu_slot_hovering==2){Create_Asset(1);}
				if(menu_slot_hovering==3){Create_Asset(2);}
			    if(menu_slot_hovering==4){Create_Asset(3);}
			    if(menu_slot_hovering==5){Create_Asset(4);}
				if(menu_slot_hovering==6){Delete_Asset(currentassetgroup,currentasset);}
				if(menu_slot_hovering==7){
					
					fcFrame.setVisible(true);
					if (fc.showOpenDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {

						ImportAsset(fc.getSelectedFile().getPath());
					
					}
					fcFrame.setVisible(false);
					
					
				
				}
				if(menu_slot_hovering==8){
					
					fcFrame.setVisible(true);
					if (fc.showSaveDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {

						ExportAsset(fc.getSelectedFile().getPath());
					
					}
					fcFrame.setVisible(false);
					
					}
			
		}
	}
	
}

if(menu_open==MENU_ABILITYICONS && !menujustopened){	
	
	
	/** Scroll Wheel Upwards */
	if (mousewheel_delta > 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll--;}
	}
	
	
	/** Scroll Wheel Downwards */
	if (mousewheel_delta < 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll++;}
		
	}
	
	
	 int maxscroll = 90;
	 
	if(current_combo_scroll > maxscroll ){current_combo_scroll=maxscroll;}
	if(current_combo_scroll<0){current_combo_scroll=0;}
	
	
	
	//if(mouse_within_container(assetmenu,mouse_x,mouse_y)){
	int offset_x = 200;
		menu_slot_hovering = ((mouse_y - parameterslot_editing*40 -  maincontainer.y - maincontainer.scrolloffset - 10 - 40)/40)*10;
		menu_slot_hovering += (mouse_x - maincontainer.x +100 - offset_x)/40;
		
		//System.out.println(menu_slot_hovering);
		int slot = menu_slot_hovering + current_combo_scroll*10;
		//fix
		
	
		
		
		if(mousepressed() 
				&& mouse_x > maincontainer.x+datacolumn_offset_x-100 && mouse_x < maincontainer.x+datacolumn_offset_x-100 + 400 
				&& mouse_y > parameterslot_editing*40 + maincontainer.y + maincontainer.scrolloffset + 50 ){
			
			if(menu_slot_hovering>=0 && menu_slot_hovering < 50){
			myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+slot;
			debug(slot);
			// DrawAbilityIconFromSheet(j,maincontainer.x+20+offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
			}
		}
	//}
	
}
if(menu_open==MENU_ITEMICONS && !menujustopened){	
	
	/** Scroll Wheel Upwards */
	if (mousewheel_delta > 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll--;}
	}
	
	
	/** Scroll Wheel Downwards */
	if (mousewheel_delta < 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll++;}
		
	}
	
	
	 int maxscroll = 26;
	 
	if(current_combo_scroll > maxscroll ){current_combo_scroll=maxscroll;}
	if(current_combo_scroll<0){current_combo_scroll=0;}
	
	
	
	//if(mouse_within_container(assetmenu,mouse_x,mouse_y)){
	int offset_x = 200;
		menu_slot_hovering = ((mouse_y - parameterslot_editing*40 -  maincontainer.y - maincontainer.scrolloffset - 10 - 40)/40)*10;
		menu_slot_hovering += (mouse_x - maincontainer.x +100 - offset_x)/40;
		
		int slot = menu_slot_hovering + current_combo_scroll*10;
		
		if(mousepressed()
				&& mouse_x > maincontainer.x+datacolumn_offset_x-100 && mouse_x < maincontainer.x+datacolumn_offset_x-100 + 400 
				&& mouse_y > parameterslot_editing*40 + maincontainer.y + maincontainer.scrolloffset + 50){
			
			if(menu_slot_hovering>=0 && menu_slot_hovering < 50){
			myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+slot;
			// DrawAbilityIconFromSheet(j,maincontainer.x+20+offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
			}
		}
	//}
	
}
if(menu_open==MENU_CONDITIONICONS && !menujustopened){	
	
	
	/** Scroll Wheel Upwards */
	if (mousewheel_delta > 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll--;}
	}
	
	
	/** Scroll Wheel Downwards */
	if (mousewheel_delta < 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll++;}
		
	}
	
	
	 int maxscroll = 90;
	 
	if(current_combo_scroll > maxscroll ){current_combo_scroll=maxscroll;}
	if(current_combo_scroll<0){current_combo_scroll=0;}
	
	
	//if(mouse_within_container(assetmenu,mouse_x,mouse_y)){
	int offset_x = 200;
		menu_slot_hovering = ((mouse_y - parameterslot_editing*40 -  maincontainer.y - maincontainer.scrolloffset - 10 - 40)/30)*10;
		menu_slot_hovering += (mouse_x - maincontainer.x +100 - offset_x)/30;
		
		int slot = menu_slot_hovering + current_combo_scroll*10;
		
		if(mousepressed()
				&& mouse_x > maincontainer.x+datacolumn_offset_x-100 && mouse_x < maincontainer.x+datacolumn_offset_x-100 + 300 
				&& mouse_y > parameterslot_editing*40 + maincontainer.y + maincontainer.scrolloffset + 50){
			
			if(menu_slot_hovering>=0 && menu_slot_hovering < 50){
			myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+slot;
			// DrawAbilityIconFromSheet(j,maincontainer.x+20+offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
			}
		}
	//}
	
}

if(menu_open>=MENU_TEXT_ABILITYSPECS &&  !menujustopened){
	

	/** Scroll Wheel Upwards */
	if (mousewheel_delta > 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll--;}
	}
	
	
	/** Scroll Wheel Downwards */
	if (mousewheel_delta < 0 ) {
		if(mouse_within_container(maincontainer,mouse_x,mouse_y)){current_combo_scroll++;}
		
	}
	
	
	if(menu_open<=MENU_TEXT_EQUIPMODELS){
	

	
	 int maxscroll;
	 int StringSet = myshareddata.ParameterType_info[currentassetgroup][parameterslot_editing];
	 
	 if(StringSet<=myshareddata.COMBOTEXT_EQUIPMODELS){
		 maxscroll = myshareddata.ComboStrings[StringSet].length - 6;
	 }else{
		 maxscroll = 6;
	 }
	
	 
	 
	if(current_combo_scroll > maxscroll ){current_combo_scroll=maxscroll;}
	if(current_combo_scroll<0){current_combo_scroll=0;}
	
	
	
	
	int hidden_shift = 0;
	for(int i=0;i<=parameterslot_editing;i++){
		if(!parameter_is_visible[i]){hidden_shift++;}
	}
	
	int actualslot = parameterslot_editing - hidden_shift;
	
	menu_slot_hovering = ((mouse_y - actualslot*40 -  maincontainer.y - maincontainer.scrolloffset - 30)/20);
	
	int slot = menu_slot_hovering + current_combo_scroll;
	
	if(mousepressed() && !menujustopened && mouse_within_container(maincontainer,mouse_x,mouse_y)){
		if(menu_slot_hovering>=0 && menu_slot_hovering < 6){
			
		    myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+slot;
			
		
		// DrawAbilityIconFromSheet(j,maincontainer.x+20+offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
		}
	}
	
}else{
	
	
	
	// int StringSet = myshareddata.ParameterType_info[currentassetgroup][parameterslot_editing];
	
	int n = menu_open - MENU_TEXT_MYABILITIES;
	 int maxscroll = Number_of_AssetTreeItems[n] - 5;
	
	 
	if(current_combo_scroll > maxscroll  ){current_combo_scroll=maxscroll;}
	if(current_combo_scroll<0){current_combo_scroll=0;}
	
	
	
	
	int hidden_shift = 0;
	for(int i=0;i<=parameterslot_editing;i++){
		if(!parameter_is_visible[i]){hidden_shift++;}
	}
	
	int actualslot = parameterslot_editing - hidden_shift;
	
	menu_slot_hovering = ((mouse_y - actualslot*40 -  maincontainer.y - maincontainer.scrolloffset - 30)/20);
	
	int slot = menu_slot_hovering + current_combo_scroll;

	if(mousepressed() && !menujustopened && mouse_within_container(maincontainer,mouse_x,mouse_y)){
		if(menu_slot_hovering>=0 && menu_slot_hovering < 6){
				
				//slot--;
				if(slot==0){
					 myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+0;
				}else{
				//if(myassets[n][slot-1].Is_Active){
					int asset_ID  = 0;
					int item = slot -1;
					
					asset_ID = assetTreeItems[n][item].asset_ID ;
					
					//asset_ID = assetTreeItems[n][slot].asset_ID;
					//System.out.println(slot + "." + asset_ID + "." + myassets[n][asset_ID].data[0]);
					
					 myassets[currentassetgroup][currentasset].data[parameterslot_editing]=""+(asset_ID+1);
				//}
				}
				
			
		
		// DrawAbilityIconFromSheet(j,maincontainer.x+20+offset_x + (i%10)*40,slot*40 + maincontainer.y + maincontainer.scrolloffset + 10 + 40 + (i/10)*40);
		}
	}
	
}



}	
	
if(!menujustopened){		
	if(mousepressed()){
    menu_open=0;	
    }
    
}

	
	
	
	


	

}




private void ClickedParameterOption(int i) {
	
	 String data = myassets[currentassetgroup][currentasset].data[i];
	
	if(myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_BOOLEAN){
		
		Boolean b = StringToBoolean(data);
		
		//if(data==null){data=""+0;}
		
		if(b == true){
			myassets[currentassetgroup][currentasset].data[i] = "" + 0;
		}else{
			myassets[currentassetgroup][currentasset].data[i] = "" + 1;
		}
		
		
		
	}
	
	
	if(myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_ALPHANUMERIC){
	parameterslot_editing = i;
	}
	

	if(myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_ALPHANUMERICHUGE){
	//parameterslot_editing = i;
	
	myassets[currentassetgroup][currentasset].data[i] = editOptionTextArea(i);
	fcFrame.setVisible(false);
	}
	
	
	
	if(myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_NUMERIC){
	parameterslot_editing = i;
	}
	if(myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_NUMERIC_PERCENT){
		parameterslot_editing = i;
		}
	
	
	if(  myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_COMBOICONS ){
	parameterslot_editing = i;
	
	
	if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_ABILITYICONS){
		 OpenMenu(MENU_ABILITYICONS);//misc menu!
	 }
	 
	 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_ITEMICONS){
			
		 OpenMenu(MENU_ITEMICONS);//misc menu!
	 }
	 
	 if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOICONS_CONDITIONICONS){
			
		 OpenMenu(MENU_CONDITIONICONS);//misc menu!
	 }
	
	
	
	 }
	
	if(  myshareddata.ParameterType[currentassetgroup][i] == myshareddata.PARATYPE_COMBOTEXT ){
	parameterslot_editing = i;
	
	for(int n=0;n<=myshareddata.COMBOTEXT_EQUIPMODELS;n++){
	if(myshareddata.ParameterType_info[currentassetgroup][i]  == myshareddata.COMBOTEXT_ABILITYSPECS+n){
		 OpenMenu(MENU_TEXT_ABILITYSPECS+n);
	 }
	}
	
	for(int n=myshareddata.COMBOTEXT_MYABILITIES;n<=myshareddata.COMBOTEXT_MYUNITS;n++){
		if(myshareddata.ParameterType_info[currentassetgroup][i]  == n){
			int m = n-myshareddata.COMBOTEXT_MYABILITIES;
			 OpenMenu(MENU_TEXT_MYABILITIES+m);
		 }
		}
	 
	//fix me
	//add the myability..etc stuff
	
	
	
	 }
	
}


void OpenMenu(int m){
	menu_open = m;
	menujustopened=true;
	
	
	
	int hidden_shift = 0;
	for(int i=0;i<=parameterslot_focused;i++){
		if(!parameter_is_visible[i]){hidden_shift++;}
	}
	
	int slot = parameterslot_focused - hidden_shift;
	


	int previous_scroll = 0;
	
	
	if(slot>=0 && currentassetgroup>=0 & currentasset >= 0 && parameterslot_focused>=0){
	int param = CurrentParameterPositions[parameterslot_focused];
	
	
	
	
	
	if(myshareddata.ParameterType[currentassetgroup][param] == myshareddata.PARATYPE_COMBOICONS){
		previous_scroll = StringToInteger(myassets[currentassetgroup][currentasset].data[param]) /10;
	}
	if(myshareddata.ParameterType[currentassetgroup][param] == myshareddata.PARATYPE_COMBOTEXT){
		
		if(myshareddata.ParameterType_info[currentassetgroup][param] < SharedData.COMBOTEXT_MYABILITIES){
		previous_scroll = StringToInteger(myassets[currentassetgroup][currentasset].data[param])  - 5;
		
		}else{//for myabilites or myeffects etc... have to decode from alphabetical
			
			
			
			
			int assetID = StringToInteger(myassets[currentassetgroup][currentasset].data[param]) - 1;
			
			int othergroup = myshareddata.ParameterType_info[currentassetgroup][param] - SharedData.COMBOTEXT_MYABILITIES;
			
			
			
			int alphaPlace = 0;
			for(int i=0;i<SharedData.MAX_NUM_ASSETS;i++){
			if(assetTreeItems[othergroup][i].asset_ID == assetID){
				alphaPlace = i;
				break;
			}
			}
			debug("id"+assetID);
			debug("ap"+alphaPlace);//works now! :D
			
			previous_scroll = alphaPlace  - 4;
			
			
			
		}
		
		
		
	}
	
	}
	if(previous_scroll<0){previous_scroll = 0;}
	current_combo_scroll=previous_scroll;
	
}


private boolean mouse_within_container(SimpleGuiObject container, int mouse_x, int mouse_y) {

if(mouse_x > container.x && mouse_x < container.x+container.width && mouse_y > container.y && mouse_y < container.y+container.height){
	return true;
}
	
	return false;
}



int BackspaceDownTime = 0;
public void pollInput_keyboard() {  
    
    
    
    
    /**KEYBOARD LISTENING*/
    
	//meta = alt!
if ((Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA)) && Keyboard.isKeyDown(Keyboard.KEY_F4)) {
   // System.out.println("SPACE KEY IS DOWN");
    GameRunning=false;
}


if(menu_open==0){

if(parameterslot_editing > -1){
	
	if(myshareddata.ParameterType[currentassetgroup][parameterslot_editing] == myshareddata.PARATYPE_ALPHANUMERIC){
		
		
		String typedletter = GetKeyboardAlphaInput();
		
		if(typedletter!=null){
		
		myassets[currentassetgroup][currentasset].data[parameterslot_editing] += typedletter;
		
		}else{
			if((framecount%8)==1 && BackspaceDownTime > 40){Keyboard_KeyAlreadyDown[Keyboard.KEY_BACK]=false;Keyboard_KeyAlreadyDown[Keyboard.KEY_DELETE]=false;}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){if(BackspaceDownTime<1000){BackspaceDownTime++;}}else{BackspaceDownTime=0;}
			
			
			
			
			
			int KeyPressed = -1;//can only be one at a time!
			for (int i = 0; i < 244; i++) {
				if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]){
					
				    KeyPressed=i;
				
				}
				
				
			}
			
			if(myassets[currentassetgroup][currentasset].data[parameterslot_editing] !=null){
			
			if(myassets[currentassetgroup][currentasset].data[parameterslot_editing].length() > 0){
				if(KeyPressed == Keyboard.KEY_BACK || KeyPressed == Keyboard.KEY_DELETE ){ myassets[currentassetgroup][currentasset].data[parameterslot_editing] = myassets[currentassetgroup][currentasset].data[parameterslot_editing].substring(0, myassets[currentassetgroup][currentasset].data[parameterslot_editing].length() - 1); }
	
		
		
		
			}
			}
			
		}
		
	}
	
if(myshareddata.ParameterType[currentassetgroup][parameterslot_editing] == myshareddata.PARATYPE_NUMERIC || 
myshareddata.ParameterType[currentassetgroup][parameterslot_editing] == myshareddata.PARATYPE_NUMERIC_PERCENT){
		
		
		String typedletter = GetKeyboardNumericInput();
		
		if(typedletter!=null){
		
		myassets[currentassetgroup][currentasset].data[parameterslot_editing] += typedletter;
		
		}else{
			if((framecount%8)==1 && BackspaceDownTime > 40){Keyboard_KeyAlreadyDown[Keyboard.KEY_BACK]=false;Keyboard_KeyAlreadyDown[Keyboard.KEY_DELETE]=false;}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){if(BackspaceDownTime<1000){BackspaceDownTime++;}}else{BackspaceDownTime=0;}
			
			
			int KeyPressed = -1;//can only be one at a time!
			for (int i = 0; i < 244; i++) {
				if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]){
					
				    KeyPressed=i;
				
				}
			}
			
			
			
			if(myassets[currentassetgroup][currentasset].data[parameterslot_editing] !=null){
			
			if(myassets[currentassetgroup][currentasset].data[parameterslot_editing].length() > 0){
				if(KeyPressed == Keyboard.KEY_BACK || KeyPressed == Keyboard.KEY_DELETE ){ myassets[currentassetgroup][currentasset].data[parameterslot_editing] = myassets[currentassetgroup][currentasset].data[parameterslot_editing].substring(0, myassets[currentassetgroup][currentasset].data[parameterslot_editing].length() - 1); }
	
		
		
		
			}
			}
		}
		
	}

	
}

}




}//end poll for inputs

void set_inputs_already_down() {

	// log that mouse has already been down at the very end of the mouse
	// handlers
	for (int i = 0; i < 3; i++) {
		if (Mouse.isButtonDown(i)) {
			Mouse_MouseAlreadyClicked[i] = true;
		} else if (Mouse_MouseAlreadyClicked[i]) {
			Mouse_MouseAlreadyClicked[i] = false;
		}
	}

	for (int i = 0; i < 254; i++) {// mark keys as already being done at the
									// very end so you can check for it!
		if (Keyboard.isKeyDown(i)) {
			Keyboard_KeyAlreadyDown[i] = true;
		} else if (Keyboard_KeyAlreadyDown[i]) {
			Keyboard_KeyAlreadyDown[i] = false;
		}

	}

}


String[] SpecialChars = {")","!","@","#","$","%","^","&","*","("};
String GetKeyboardAlphaInput(){
	
	String answer = null;
	
	
	
	
	int KeyPressed = -1;//can only be one at a time!
	for (int i = 0; i < 244; i++) {
		if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]){
			
		    KeyPressed=i;
		
		}
		
		
	}
	
	//pressing numbers
	
	if(KeyPressed >= Keyboard.KEY_1 && KeyPressed <= Keyboard.KEY_0 ){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			
			answer= SpecialChars[Integer.parseInt(Keyboard.getKeyName(KeyPressed))];
									
		}else{
			answer= Keyboard.getKeyName(KeyPressed);
		}
	
	}
	
	if(KeyPressed == Keyboard.KEY_NUMPAD0  
			|| KeyPressed == Keyboard.KEY_NUMPAD1 
			|| KeyPressed == Keyboard.KEY_NUMPAD2 
			|| KeyPressed == Keyboard.KEY_NUMPAD3 
			|| KeyPressed == Keyboard.KEY_NUMPAD4
			|| KeyPressed == Keyboard.KEY_NUMPAD5 
			|| KeyPressed == Keyboard.KEY_NUMPAD6 
			|| KeyPressed == Keyboard.KEY_NUMPAD7
			|| KeyPressed == Keyboard.KEY_NUMPAD8 
			|| KeyPressed == Keyboard.KEY_NUMPAD9 
			){answer= Keyboard.getKeyName(KeyPressed).substring(Keyboard.getKeyName(KeyPressed).length()-1);}
	
	
	if(KeyPressed == Keyboard.KEY_PERIOD || KeyPressed == Keyboard.KEY_DECIMAL){answer= ".";}
	
	
	if( KeyPressed >= Keyboard.KEY_Q && KeyPressed <= Keyboard.KEY_M && Keyboard.getKeyName(KeyPressed).length() ==1 ){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){answer= Keyboard.getKeyName(KeyPressed).toUpperCase();}else{answer= Keyboard.getKeyName(KeyPressed).toLowerCase();}
		
	}
	
	if( KeyPressed == Keyboard.KEY_SPACE ){answer= " ";}
	
	if( KeyPressed == Keyboard.KEY_APOSTROPHE ){
		
	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){answer= "\"";}else{answer= "'";}
	}
	
	
	
	
	
	return answer;
}

String GetKeyboardNumericInput(){
	
	String answer = null;
	
	
	
	
	int KeyPressed = -1;//can only be one at a time!
	for (int i = 0; i < 244; i++) {
		if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]){
			
		    KeyPressed=i;
		
		}
		
		
	}
	
	//pressing numbers
	
	if(KeyPressed >= Keyboard.KEY_1 && KeyPressed <= Keyboard.KEY_0 ){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			
			//answer= SpecialChars[Integer.parseInt(Keyboard.getKeyName(KeyPressed))];
									
		}else{
			answer= Keyboard.getKeyName(KeyPressed);
		}
	
	}
	
	if(KeyPressed == Keyboard.KEY_NUMPAD0  
			|| KeyPressed == Keyboard.KEY_NUMPAD1 
			|| KeyPressed == Keyboard.KEY_NUMPAD2 
			|| KeyPressed == Keyboard.KEY_NUMPAD3 
			|| KeyPressed == Keyboard.KEY_NUMPAD4
			|| KeyPressed == Keyboard.KEY_NUMPAD5 
			|| KeyPressed == Keyboard.KEY_NUMPAD6 
			|| KeyPressed == Keyboard.KEY_NUMPAD7
			|| KeyPressed == Keyboard.KEY_NUMPAD8 
			|| KeyPressed == Keyboard.KEY_NUMPAD9 
			){answer= Keyboard.getKeyName(KeyPressed).substring(Keyboard.getKeyName(KeyPressed).length()-1);}
	
	
	//if(KeyPressed == Keyboard.KEY_PERIOD || KeyPressed == Keyboard.KEY_DECIMAL){answer= ".";}
	if(KeyPressed == Keyboard.KEY_MINUS){answer= "-";}
	
	/*
	if( KeyPressed >= Keyboard.KEY_Q && KeyPressed <= Keyboard.KEY_M && Keyboard.getKeyName(KeyPressed).length() ==1 ){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){answer= Keyboard.getKeyName(KeyPressed).toUpperCase();}else{answer= Keyboard.getKeyName(KeyPressed).toLowerCase();}
		
	}
	
	if( KeyPressed == Keyboard.KEY_SPACE ){answer= " ";}
	
	if( KeyPressed == Keyboard.KEY_APOSTROPHE ){
		
	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){answer= "\"";}else{answer= "'";}
	}
	*/
	
	
	
	
	return answer;
}


 
	  
	



int[][] parse_font_lookup_table(String filepath){
	
	int[][] answer = new int[256][6];//for each char.. x,y,width,height,xoff,yoff
	  String[] lookuptable_lines = new String[1000];
	   
	   
      int linenum = 0;
      
      int indexer_start=-1;
      int indexer_end=-1;
      
      FileReader fr;
	try {
		
		
		//String localpath =  (Thread.currentThread().getContextClassLoader().getResource( "wrap/assets/" + filepath ).getFile());
		
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream( "wrap/assets/" + filepath   );
		
		
		
		//String localpath = filepath;
		
		//InputStream in = MapEditorCore.class.getResourceAsStream(filepath);
		
		//InputStreamReader inread = new InputStreamReader(in, "UTF-8");
		
		
		
		//BufferedReader reader = new BufferedReader(inread);
		//BufferedReader reader = new BufferedReader(new FileReader(localpath));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		try {
		  while (true) {
		    String line = reader.readLine();
		 
		    
		    if (line != null  ){
		    	if(line.startsWith("char id")){
		    
		    	int char_id=-1;
		    	int x=0;
		    	int y=0;
		    	int width=0;
		    	int height=0;
		    	int xoffset=0;
		    	int yoffset=0;
		    	
		    	lookuptable_lines[linenum]=line;			    	
		    	linenum++;
		    	
		    	
		    	
		    	//find char id
		    	indexer_start = line.indexOf("char id=");
		    	indexer_start += 8;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	char_id = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	
		    	
		    	//find x
		    	indexer_start = line.indexOf("x=");
		    	indexer_start += 2;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	
		    	x = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	//find y
		    	indexer_start = line.indexOf("y=");
		    	indexer_start += 2;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	
		    	y = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	//find width
		    	indexer_start = line.indexOf("width=");
		    	indexer_start += 6;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	width = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	//find height
		    	indexer_start = line.indexOf("height=");
		    	indexer_start += 7;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	height = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	//find x_off
		    	indexer_start = line.indexOf("xoffset=");
		    	indexer_start += 8;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	xoffset = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	//find y_off
		    	indexer_start = line.indexOf("yoffset=");
		    	indexer_start += 8;
		    	indexer_end = line.indexOf(" ", indexer_start);
		    	
		    	if(indexer_start > -1 && indexer_end > -1  ){
		    	
		    	yoffset = Integer.parseInt(line.substring(indexer_start, indexer_end)); 
		    				    	
		    	}
		    	
		    	
		    	if(char_id > -1){
		    	answer[char_id][0] = x;
		    	answer[char_id][1] = y;
		    	answer[char_id][2] = width;
		    	answer[char_id][3] = height;
		    	answer[char_id][4] = xoffset;
		    	answer[char_id][5] = yoffset;
		    	//System.out.println(char_id+":"+  height );
		    	}
		    	
		    	}//end check for startign with char
		    	
		    	
		    }else{			 
		    	
		    	 break;
		    }
		   // fields = line.split(",");
		    // process fields here
		  }
		} finally {
		  reader.close();
		}
		
		
      
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
	
	//lookup table
	
	
	return answer;
	
}



		int Create_Asset(int type){
			int first_slot = -1;
			
			for(int i=(myshareddata.MAX_NUM_ASSETS-1);i>=0;i--){//find first slot!
				
				if(!myassets[type][i].Is_Active){
					first_slot=i;					
				}
				
				
			}
			
			if(first_slot > -1){
				//make a new asset in the first available slot
				myassets[type][first_slot] = new SimpleAsset();
				myassets[type][first_slot].Is_Active=true;
				
				myassets[type][first_slot].data[0] = "New Asset";
				
				for(int i=0;i<myshareddata.MAX_NUM_PARAMETERS;i++){
					if(myshareddata.ParameterDefaultValue[type][i] != null){
						
						myassets[type][first_slot].data[i] = myshareddata.ParameterDefaultValue[type][i];
						
					}
					
				}
				
			}
			
			return first_slot;
		}
		
		
		void Duplicate_Asset(){
			int OldID = currentasset;
			
			int NewID = Create_Asset(currentassetgroup);
			for(int n=2;n<myassets[currentassetgroup][NewID].data.length ;n++){
			myassets[currentassetgroup][NewID].data[n] = myassets[currentassetgroup][OldID].data[n];
			}
			
			currentasset = NewID;
			maincontainer.scrolloffset=0;
			current_combo_scroll=0;
		}
		
		
       void New_Spellbook(){
			
			
			fcFrame.setVisible(true);
			fcFrame.toFront();
			fcFrame.setAlwaysOnTop(true);
			
			int okCxl = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(fcFrame), "Wipe this Assetbook?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
		
			if (okCxl == JOptionPane.OK_OPTION) {
				for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
					for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++){
						myassets[i][j] = new SimpleAsset();//delete
					}
					
				}
				
			} else {
				
			}
			
			fcFrame.setVisible(false);
			
			
			
		}
		
		void Delete_Asset(int group, int ID){//fix me !!!
			
			
			fcFrame.setVisible(true);
			fcFrame.toFront();
			fcFrame.setAlwaysOnTop(true);
			
			int okCxl = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(fcFrame), "Delete "+myassets[currentassetgroup][currentasset].data[0]+"?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
		
			if (okCxl == JOptionPane.OK_OPTION) {
				myassets[group][ID] = new SimpleAsset();//delete
			} else {
				
			}
			
			fcFrame.setVisible(false);
			
			
			
		}
		

private void readAssetBookFromFile(String sFileName) {
	
			
		
		 int this_asset_type=0;
		 int this_asset_ID=0;
		 
	  	    try//import the data
		{
			
			if(sFileName.substring(sFileName.length()-4,sFileName.length()).equals(".spl")){}else{sFileName += (".spl");}
			
			
			Stored_Save_File_Path = sFileName;
			
				FileReader fr = new FileReader(sFileName); 
				BufferedReader br = new BufferedReader(fr); 
				
				
				
				String version = br.readLine();
				
				
				String line = br.readLine();
				
				//load dynamically, can open outdated files just fine!
				while(line != null){
				
					
					if(line.startsWith("~Next Asset Type")){
						this_asset_type = Integer.parseInt(line.substring(line.indexOf(":") + 1));
						this_asset_ID = Create_Asset(this_asset_type);
						}
					  
					    	
					   for(int p=0;p<myshareddata.ParameterCount[this_asset_type];p++){
								
								if(line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])){
									
									myassets[this_asset_type][this_asset_ID].data[p] = line.substring(line.indexOf(":") + 1);
									
								}
								
						}
					    
							line = br.readLine();
				}
				
				
				
				fr.close(); 
				
				
				
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	  	    
}


private void writeAssetBookToFile(String sFileName) {
	//int number_of_assets[] = new int[myshareddata.NUMBER_OF_ASSETGROUPS];
	try {

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".spl")) {} else {
			sFileName += (".spl");
		}

		FileWriter writer = new FileWriter(sFileName);

		writer.append("Baneforge Asset Book " + myshareddata.CURRENTVERSION + "  " + "\n");

		
		
	
		
		
		
		for(int i=0;i<myshareddata.NUMBER_OF_ASSETGROUPS;i++){
			
			
			for(int j=0;j<myshareddata.MAX_NUM_ASSETS;j++){
				
				if(myassets[i][j].Is_Active){
					writer.append("~Next Asset Type:" + i + "\n");

				  for(int p=0;p<myshareddata.ParameterCount[i];p++){
					writer.append(myshareddata.ParameterStrings[i][p] + ":" + myassets[i][j].data[p] + "\n");
				//System.out.println(p);
				  }
				}
				}
			
		}
		
		

		
		writer.flush();
		writer.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



private void ExportAsset(String sFileName) {

	try {

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".ast")) {} else {
			sFileName += (".ast");
		}

		FileWriter writer = new FileWriter(sFileName);

		writer.append("Baneforge Asset " + myshareddata.CURRENTVERSION + "  " + "\n");

		
		
		
			writer.append("Asset Type:" + currentassetgroup + "\n");
			

				  for(int p=0;p<myshareddata.ParameterCount[currentassetgroup];p++){
					writer.append(myshareddata.ParameterStrings[currentassetgroup][p] + ":" + myassets[currentassetgroup][currentasset].data[p] + "\n");
				
				  
				}
				
		
		

		
		writer.flush();
		writer.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


private void ImportAsset(String sFileName) {

int this_asset_type = 0;
int this_asset_ID = 0;
	  try
			{
				
				if(sFileName.substring(sFileName.length()-4,sFileName.length()).equals(".ast")){
				
				
					FileReader fr = new FileReader(sFileName); 
					BufferedReader br = new BufferedReader(fr); 
					
					
					
					String version = br.readLine();
					
					
					String line = null;
					
					
						
						line = br.readLine();
						
						/*
						this_asset_group = Integer.parseInt(line.substring(line.indexOf(":") + 1));
						
						int this_asset_id = Create_Asset(this_asset_group);
						
			
							
							myassets[this_asset_group][this_asset_id].Is_Active = true;
							
							for(int p=0;p<myshareddata.ParameterCount[this_asset_group];p++){
								line = br.readLine();
								System.out.println(line.substring(line.indexOf(":") + 1) );
								
								myassets[this_asset_group][this_asset_id].data[p] = 
										line.substring(line.indexOf(":") + 1);
								
							}
							
							
					*/
							
							
							
							
							while(line != null){
								
								
								if(line.startsWith("~Next Asset Type")){
									this_asset_type = Integer.parseInt(line.substring(line.indexOf(":") + 1));
									this_asset_ID = Create_Asset(this_asset_type);
									}
								  
								    	
								   for(int p=0;p<myshareddata.ParameterCount[this_asset_type];p++){
											
											if(line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])){
												
												myassets[this_asset_type][this_asset_ID].data[p] = line.substring(line.indexOf(":") + 1);
												
											}
											
									}
								    
										line = br.readLine();
							}
							
							
							
							
							
							
							
							
							
					fr.close(); 
					
					
				}else{
					
					System.err.println("Invalid file type!");
				}
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		  	    
	  
	  
	
}
		
		
		//TEMP
	/*	void Create_Asset(int type, String s){
			int first_slot = -1;
			
			for(int i=(myshareddata.MAX_NUM_ASSETS-1);i>=0;i--){//find first slot!
				
				if(!myassets[type][i].Is_Active){
					first_slot=i;					
				}
				
				
			}
			
			if(first_slot > -1){
				//make a new asset in the first available slot
				myassets[type][first_slot] = new SimpleAsset();
				myassets[type][first_slot].Is_Active=true;
				myassets[type][first_slot].data[0] =s;
			}
			
			
		}*/
		

Texture generate_tex_local(String ref){
	
	
	Texture tex = null;

	//String localpath =  (Thread.currentThread().getContextClassLoader().getResource( "wrap/assets/" + ref ).getFile());
	//System.out.println(localpath );
	
	Image2D img = null;
	try {
		tex = textureLoader.getTexture("wrap/assets/" + ref);
		img = new Image2D(tex);
	} catch (IOException e) {
		System.err.print("No Texture At Path Specified!");
		e.printStackTrace();
	}

	

	return tex;
}



Image2D generate_typeimage_local(String ref) {
	
	System.out.println(ref );
	
	
	
	Image2D img = null;
	try {
		img = new Image2D(textureLoader.getTexture("wrap/assets/" + ref));
	} catch (IOException e) {
		System.err.print("No Texture At Path Specified!");
		e.printStackTrace();
	}

	// img.begin();

	return img;

}

Image2D generate_typeimage_ext(String ref) {
	
	System.out.println(ref );
	
	
	
	Image2D img = null;
	try {
		img = new Image2D(textureLoader.getTexture(ref));
	} catch (IOException e) {
		System.err.print("No Texture At Path Specified!");
		e.printStackTrace();
	}

	// img.begin();

	return img;

}
		
		boolean mousepressed(){
			boolean b = false;
			
			if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
			    if (Mouse_MouseAlreadyClicked[0] == false
						&& Mouse_MouseAlreadyClicked[1] == false) {
			    b = true;	
			    }
			    }
			
			
			return b;
					
		}
		
		void DrawAbilityIconFromSheet(int Icon_ID, int screen_x , int screen_y){
			
			MasterBWIconSheet.bind();
			MasterBWIconSheet.begin();
			
			
			int tex_x=0;
			int tex_y=0;
			
			//int tile =  ((Icon_ID-1)%16);
			int tile =  ((Icon_ID));
			
			//tuning
			tex_x=(tile%25)*40;
			tex_y=(tile/25)*40;
			
			MasterBWIconSheet.draw_subimage(tex_x,tex_y,screen_x, screen_y ,40,40);
			
			MasterBWIconSheet.end();
			
		}
		
		void DrawConditionIconFromSheet(int Icon_ID, int screen_x , int screen_y){
			
			MasterBWIconSheet.bind();
			MasterBWIconSheet.begin();
			
			
			int tex_x=0;
			int tex_y=0;
			
			//int tile =  ((Icon_ID-1)%16);
			int tile =  ((Icon_ID));
			
			//tuning
			tex_x=(tile%25)*40;
			tex_y=(tile/25)*40;

			MasterBWIconSheet.draw_subimage_stretch(tex_x,tex_y,screen_x, screen_y,40,40 ,30,30);
		
			MasterBWIconSheet.end();
			
		}
		
		void DrawItemIconFromSheet(int Icon_ID, int screen_x , int screen_y){
			
			MasterItemIconSheet.bind();
			MasterItemIconSheet.begin();
			
			
			int tex_x=0;
			int tex_y=0;
			
			//int tile =  ((Icon_ID-1)%16);
			int tile =  ((Icon_ID));
			
			//tuning
			tex_x=(tile%25)*40;
			tex_y=(tile/25)*40;

			MasterItemIconSheet.draw_subimage(tex_x,tex_y,screen_x, screen_y ,40,40);
			
			MasterItemIconSheet.end();
			
		}
		
		JTextArea myTA = new JTextArea();
		String editOptionTextArea(int param) {

			String past_text = myassets[currentassetgroup][currentasset].data[param];
			myTA.setPreferredSize(new Dimension(220, 125));

			myTA.setLineWrap(true);
			myTA.setText(past_text);

			fcFrame.setVisible(true);
			fcFrame.toFront();
			fcFrame.setAlwaysOnTop(true);
			
			int okCxl = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(fcFrame), myTA, "Enter Description", JOptionPane.OK_CANCEL_OPTION);
		
			if (okCxl == JOptionPane.OK_OPTION) {
				return myTA.getText();
			} else {
				return past_text;
			}

		}
		
		
		  boolean StringToBoolean(String s){
			  
			  boolean b = false;
			  
			  try{
			  if( Integer.parseInt(s) == 1){b = true;}
			  }catch(Exception e){}
			  
			  
			  return b;
		  }

		public void copyExternalFileToLocal(String S_external, String S_local, boolean overwrite) throws IOException {
		debug(S_external);
	
			
		
		byte[] dataBytes = null;
		
		
		InputStream is = null;
		try {
		  is = new FileInputStream(S_external);
		  dataBytes = IOUtils.toByteArray(is);
		}
		catch (IOException e) {
		  System.err.printf("Failed while reading bytes from %s: %s");
		  e.printStackTrace ();
		  // Perform any other exception handling that's appropriate.
		}
		finally {
		  if (is != null) { is.close(); }
		}
		
		
		
		File outputfile = new File(S_local);
	    
	    if(!outputfile.exists() || overwrite){
	    	
	   
	    	FileOutputStream out = new FileOutputStream(S_local);  
	    	try {  
	    	    out.write(dataBytes);  
	    	} finally {  
	    	    out.close();  
	    	}  
		
		 }
	}
	
	
	public void copyLocalFileToExternal(String S_local, String S_external, boolean overwrite) throws IOException {
		debug(S_local);
	
		URL url = this.getClass().getClassLoader().getResource(S_local);
		
		
		byte[] dataBytes = null;
		
		
		InputStream is = null;
		try {
		  is = url.openStream();
		  dataBytes = IOUtils.toByteArray(is);
		}
		catch (IOException e) {
		  System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
		  e.printStackTrace ();
		  // Perform any other exception handling that's appropriate.
		}
		finally {
		  if (is != null) { is.close(); }
		}
		
		
		
		File outputfile = new File(S_external);
	    
	    if(!outputfile.exists() || overwrite){
	    	
	   
	    	FileOutputStream out = new FileOutputStream(S_external);  
	    	try {  
	    	    out.write(dataBytes);  
	    	} finally {  
	    	    out.close();  
	    	}  
		
		 }
	}
	

			public void writePNGFile(String S_local, String S_external, boolean overwrite) throws IOException {
				
				URL url = this.getClass().getClassLoader().getResource(S_local);
				
				 BufferedImage img = ImageIO.read(url);
				 
				    File outputfile = new File(S_external);
				    
				    if(!outputfile.exists() || overwrite){
				    
				    try{
				    ImageIO.write(img, "png", outputfile);
				    }catch(Exception e){}
				    
				    }
			}
			
			





			private void CopyLocalFileIfNotFoundInAppData(String S_local, String S_external) {

				
				if(S_local.substring(S_local.length()-4,S_local.length()).equals(".png")){
					
			

				try {
					writePNGFile(S_local, S_external, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}else{
					
					
					
					// If there is no active assetbook, copy the default one in!
					String localpath = null;
					// URI extpath = null;
					try {
						// localpath = new URI(this.getClass().getClassLoader().getResource(
						// S_local ).toString());
						localpath = (Thread.currentThread().getContextClassLoader().getResource(S_local).getFile());

						// extpath = new URI(S_external);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();	
					}
					
					
					
					//File localfile = new File(localpath);
					//File extfile = new File(S_external);
					
					try {
						copyLocalFileToExternal(S_local, S_external, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}

			}
			

			void debug(Object o) {
				if (SharedData.PRINT_CONSOLE_INFO) {
					System.out.println(o);
				}
			}
			
			int StringToInteger(String s) {
				
				int i = 0;
				
				if(s!=null){
				if (s.indexOf("-") > -1) {// handles numbers like "0-5" and makes it -5
					s = s.substring(s.indexOf("-"));
				}
				}

				try {
					i = Integer.parseInt(s);
				} catch (Exception e) {}

			
				
				
				return i;
			}
			
			
			int BinaryArrayToInt(boolean b[]){
				int x = 0;
				
				for(int i=0;i<b.length;i++){
				if(b[i]){x += Math.pow(2,i);}
				}
				return x;
			}
			
			boolean[] IntToBinaryArray(int x){
				boolean b[] = new boolean[20];
				
				for(int i=b.length;i>=0;i++){
					int subtractor = (int) Math.pow(2,i);
					
					if(x >= subtractor){
						x-= subtractor;
						b[i] = true;
					}
					//if(x % Math.pow(2,(i+1)) == 0 ){}
					}
				
				return b;
			}
			
			
			
		  
}//end core as a whole




