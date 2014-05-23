package wrap;

/*
 * Copyright Baneforge Project 2012
 * 
 */

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import wrap.Launcher;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Launcher {

	public static final boolean DEBUG_MODE = true;
	
	
	
	//static SharedData myshareddata = new SharedData();
	
	
	public static String WINDOW_TITLE = "Baneforge Launcher " + SharedData.CURRENTVERSION;
	
	
	/** Screen width in pixels. */
	public int SCREEN_WIDTH = 400;
	/** Screen height in pixels. */
	public int SCREEN_HEIGHT = 500;
	
	
	
	/** The pixel size of tiles. */
	public static final int TILE_SIZE = 40;
	
	

	public int SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1;
	public int SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;
	
	
	int InitSettingsData[] = new int[100];// for settings.txt
	
	
	int animationframe = 0; // goes from 0 to 31,
			

	int current_MainMenu_Screen = 0;

	Camera cam = new Camera();
	MouseCursor cursor = new MouseCursor();
	  Settings mySettings = new Settings();//stored and loaded with XML
	
	SimpleGuiObject[] LauncherSettings = new SimpleGuiObject[5];
	
	
	//boolean FullScreen = false;
	
																					
	
	TextureLoader textureLoader = new TextureLoader();

		


	TextBox[] myTextBoxes = new TextBox[20];
	
	


	static Launcher mainlauncher;
	public static void main(String[] args) {
		mainlauncher = new Launcher();
	}
	
	
	static Core proxycore;
	public static void launch_game(){		
		proxycore = new Core();	
	}
	
	static MapEditorCore proxymapeditor;
	public static void launch_mapeditor(){		
		proxymapeditor = new MapEditorCore();	
	}
	
	static AssetEditorCore proxyasseteditor;
	public static void launch_asseteditor(){		
		proxyasseteditor = new AssetEditorCore();	
	}
	

	

	String menuMusic_path; 
	
	
	public Launcher() {
		
	
		
		
		new File(SharedData.baseDirectory()).mkdir();
		new File(SharedData.defaultDirectory()).mkdir();
		new File(SharedData.defaultDirectory() + "\\Profile Images").mkdir();
		new File(SharedData.defaultDirectory() + "\\Heroes").mkdir();
		new File(SharedData.defaultDirectory() + "\\Maps").mkdir();
		new File(SharedData.defaultDirectory() + "\\Campaigns").mkdir();
		new File(SharedData.defaultDirectory() + "\\Skins").mkdir();

		writeSettingstoFile(false);
		
		
		
		readSettingsfromFile();
		
		
	
		initDisplay();
		
		
	}
	





	public void initDisplay() {


		try {
			
			
			setDisplayMode();

			Display.setTitle(WINDOW_TITLE);


			
			
			Display.create();

			// enable textures since we're going to use these for our sprites

			glEnable(GL_TEXTURE_2D);

			Display.setVSyncEnabled(true);

			// disable the OpenGL depth test since we're rendering 2D graphics

			glDisable(GL_DEPTH_TEST);

			glMatrixMode(GL_PROJECTION);

			glLoadIdentity();

			glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);

			glMatrixMode(GL_MODELVIEW);

			glLoadIdentity();

			glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

			
			String[] DisplayIconPaths = {"wrap/assets/misc/bficon16.png","wrap/assets/misc/bficon32.png","wrap/assets/misc/bficon128.png"};
			ByteBuffer[] BFIcons = textureLoader.getByteBuffer(DisplayIconPaths);
			Display.setIcon(BFIcons);


			// soundManager.initialize(8);

		} catch (LWJGLException le) {

			System.out.println("Game exiting - exception in initialization:");

			le.printStackTrace();

			// Game.LauncherRunning = false;
		}

		
		LoadGameAssets();
		
		
		startGameLoop();

	}
	

	
	
	

	Image2D bookicon;
	Image2D editicon;
	Image2D bficon;
	

	Thread myNetworkingThread;
	Thread myUDPBroadcastThread;


	SimpleGuiObject buttons[] = new SimpleGuiObject[3];
	
	
	CustomFont Verdana_12;
	CustomFont Verdana_14;
	CustomFont Verdana_16;
	CustomFont Verdana_18;
	CustomFont Verdana_20;
	//	Image2D MasterTerrainSheet;
	
	Image2D smallfolder;
    Image2D smallgear;
	private Image2D intropnl_logo;
	
	void LoadGameAssets() {
		
		try {
			
			
		     Verdana_14 = new CustomFont(  textureLoader.getTexture("wrap/assets/fonts/" + "verdana14.png") , parse_font_lookup_table("/wrap/assets/fonts/" + "verdana14.fnt") );
			
				
				Verdana_12 = new CustomFont(  textureLoader.getTexture("wrap/assets/fonts/" + "verdana12_0.png") , parse_font_lookup_table("/wrap/assets/fonts/" + "verdana12.fnt") );	
				Verdana_16 = new CustomFont(  textureLoader.getTexture("wrap/assets/fonts/" + "verdana16_0.png") , parse_font_lookup_table("/wrap/assets/fonts/" + "verdana16.fnt") );
				Verdana_18 = new CustomFont(  textureLoader.getTexture("wrap/assets/fonts/" + "verdana18_0.png") , parse_font_lookup_table("/wrap/assets/fonts/" + "verdana18.fnt") );
				Verdana_20= new CustomFont(  textureLoader.getTexture("wrap/assets/fonts/" + "verdana20_0.png") , parse_font_lookup_table("/wrap/assets/fonts/" + "verdana20.fnt") );
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		
		intropnl_logo = generate_typeimage_local("menus/BaneForgeLogoGray2.png");
		
		
		smallfolder = generate_typeimage_local("menus/smallfolder.png");
		smallgear = generate_typeimage_local("menus/gearicon.png");
		
		bookicon = generate_typeimage_local("misc/bookicon.png");
		editicon = generate_typeimage_local("misc/editicon.png");
		bficon = generate_typeimage_local("misc/bficon.png");
		
		
		for(int i=0;i<buttons.length;i++){			
			buttons[i] = new SimpleGuiObject();			
		}
		
		for(int i=0;i<LauncherSettings.length;i++){			
			LauncherSettings[i] = new SimpleGuiObject();			
		}
		
		buttons[0].foreground = bficon;
		buttons[1].foreground = editicon;
		buttons[2].foreground = bookicon;
		
		buttons[0].text = "Launch Baneforge";
		buttons[1].text = "Map Editor";
		buttons[2].text = "Asset Editor";
		
		
		
		LauncherSettings[0].foreground = smallfolder;
		LauncherSettings[1].foreground = smallgear;
		
		LauncherSettings[0].text = "View Game Folder";
		
		
	}
	
	
	
	
	
	
	
	

//	private SoundManager soundManager;
	boolean LauncherRunning = true;


	
	private boolean setDisplayMode() {
		try {
			
			debug(SCREEN_WIDTH);
			
			Display.setFullscreen(false);//make sure this works!!!
			Display.setResizable(true);
			
			
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(
					SCREEN_WIDTH, SCREEN_HEIGHT, -1, -1, -1, -1, 60, 60);
			
			
			org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
					"width=" + SCREEN_WIDTH,
					"height=" + SCREEN_HEIGHT,
					"freq=" + 60,
					"bpp="
							+ org.lwjgl.opengl.Display.getDisplayMode()
									.getBitsPerPixel() });

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			System.out
					.println("Unable to enter fullscreen, continuing in windowed mode");

		}

		return false;

	}

	/**
	 * Run the main game loop. This method keeps rendering the scene and
	 * requesting that the callback update its screen.
	 */
	boolean focused_GUI;
	boolean assetstreamdone = false;
	private void startGameLoop() {
		
	

		while (!Display.isCloseRequested() && LauncherRunning) {
			
			config_UI_sizes_continuous();
		

			/** make sure resizing on the fly doesn't break anything */
			glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			
			
			cursor.Poll_Mouse_Position(SCREEN_HEIGHT, cam, TILE_SIZE,SharedData.MAP_SIZE,SCREEN_X_TILES,SCREEN_Y_TILES);
			
			
		

				pollInput_MenuMouse();
				
				
				pollInput_MenuKeyboard();
				
			
				
				
				if(LauncherRunning){
				
				set_inputs_already_down();				
				
				menuRendering();

				
				
				
				Display.update(); 
			
				Display.sync(60);
			 
				}
		
		
		}

	
		Display.destroy();
		System.exit(0);
	}
	
	
void config_UI_sizes_continuous() {
	
	SCREEN_WIDTH = Display.getWidth();
	SCREEN_HEIGHT = Display.getHeight();
	
		//SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1; // System.out.println(SCREEN_X_TILES);
		//SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;
	
	
for(int i=0;i<buttons.length;i++){
	buttons[i].width = 260;
	buttons[i].height = 70;
	buttons[i].x = SCREEN_WIDTH/2 - buttons[i].width/2 - 100;
	buttons[i].y = 170+(i*90);
}


for(int i=0;i<LauncherSettings.length;i++){
	LauncherSettings[i].width = 160;
	LauncherSettings[i].height = 20;
	LauncherSettings[i].x = SCREEN_WIDTH/2 - LauncherSettings[i].width/2 + 200;
	LauncherSettings[i].y = 220+(i*40);
}
	
if(InitSettingsData[SharedData.INITSETTING_FULLSCREEN]==0){
	LauncherSettings[1].text = "Fullscreen Disabled";
	}else{
	LauncherSettings[1].text = "Fullscreen Enabled";
	}
	
	}



	private static long timerTicksPerSecond = Sys.getTimerResolution();

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
	
	
	
	
	
	long Timer_main;
	long maintimercount;
	void poll_timers(){
		
		


	maintimercount+=lastLoopTime;
	if(maintimercount>10000000){
	maintimercount=0;
	Timer_main++;
	ExecuteEvenlyTimedEvents();
	}
	if(Timer_main>=64){Timer_main=0;}
}
	
	
	void ExecuteEvenlyTimedEvents(){
		

		
	}
	
	
	
	private long lastFpsTime;
	private long lastLoopTime;
	int fps;
	long delta;// used for animation

	
	
	
	
	
	
	

	int mainmenu_smoothcursor_x = 0;
	int mainmenu_smoothcursor_y = 0;
	float frontmenu_affiliation = 0.5f;

	
	
	
	
	
	
	public void menuRendering() {

		// SystemTimer.sleep(lastLoopTime+10-SystemTimer.getTime());
		

		// work out how long its been since the last update, this
		// will be used to calculate how far the entities should
		// move this loop
		delta = getTime() - lastLoopTime;
		lastLoopTime = getTime();
		lastFpsTime += delta;
		fps++;

		// update our FPS counter if a second has passed
		if (lastFpsTime >= 1000) {
			Display.setTitle(WINDOW_TITLE );
			lastFpsTime = 0;
			fps = 0;
		}


		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		glDisable(GL_DEPTH_TEST);

	
		glMatrixMode(GL_MODELVIEW);

		glLoadIdentity();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	
	

		if (current_MainMenu_Screen == 0) {
			
			
			
			
			GL11.glColor3f(0.2f, 0.8f, 0.3f);
			intropnl_logo.draw(SCREEN_WIDTH/2 - intropnl_logo.getWidth()/2,30);
			
			for(int i=0;i<buttons.length;i++){
				if(buttons[i].BeingHovered(cursor)){GL11.glColor3f(0.8f, 0.8f, 0.8f);}else{GL11.glColor3f(1.0f, 1.0f, 1.0f);}
				buttons[i].foreground.draw(buttons[i].x + 5,buttons[i].y + 5);			
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
				Verdana_20.drawString(buttons[i].x+90,buttons[i].y+20,buttons[i].text);
				if(buttons[i].BeingHovered(cursor)){Outline_Rect(buttons[i].x,buttons[i].y,buttons[i].width,buttons[i].height);}
			}
			
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			Verdana_16.drawString(SCREEN_WIDTH /2 + 110,190,"Configuration");
			for(int i=0;i<LauncherSettings.length;i++){
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
				if(LauncherSettings[i].foreground!=null){
				LauncherSettings[i].foreground.draw(LauncherSettings[i].x,LauncherSettings[i].y);	
				}
				if(LauncherSettings[i].BeingHovered(cursor)){GL11.glColor3f(0.8f, 0.8f, 0.8f);}else{GL11.glColor3f(1.0f, 1.0f, 1.0f);}
				Verdana_14.drawString(LauncherSettings[i].x+30,LauncherSettings[i].y+3,LauncherSettings[i].text);
			}
			
			
			
			
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			
			/*
			if (cursor.x >= SCREEN_WIDTH - 40 && cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			// if(BackgroundMusic.getVolume() > 0 ){volume_on.draw(SCREEN_WIDTH
			// - 30, 15);}else{volume_off.draw(SCREEN_WIDTH - 30, 15);}
			smallgear.draw(SCREEN_WIDTH - 35, 12);
			if (cursor.x < SCREEN_WIDTH - 40 && cursor.x > SCREEN_WIDTH - 75
					&& cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			smallfolder.draw(SCREEN_WIDTH - 30 - 30, 15);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			*/
			
		}
		
		
		

	}


	
	
	
	
	
	
	
	public void pollInput_MenuMouse() {

		
		
		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {
				
		
			}}
		

		if (current_MainMenu_Screen == 0) {

			/** MOUSE DRAGGED */
			if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

				/** MOUSE PRESSED */
				if (Mouse_MouseAlreadyClicked[0] == false
						&& Mouse_MouseAlreadyClicked[1] == false) {
					
					
						
						if (buttons[0].BeingHovered(cursor)) {		
							LauncherRunning=false;
							Display.destroy();
							launch_game();							
							System.exit(0);
						}  

						
						if(buttons[1].BeingHovered(cursor)){
							LauncherRunning=false;
							Display.destroy();
							launch_mapeditor();							
							System.exit(0);
							}
						
						
						if(buttons[2].BeingHovered(cursor)){
							LauncherRunning=false;
							Display.destroy();
							launch_asseteditor();							
							System.exit(0);
							}
						
						
						
						if (LauncherSettings[0].BeingHovered(cursor)) {		
							Desktop desktop = null;
							// Before more Desktop API is used, first check
							// whether the API is supported by this particular
							// virtual machine (VM) on this particular host.
							if (Desktop.isDesktopSupported()) {
								desktop = Desktop.getDesktop();
							}
							try {
								desktop.open(new File(SharedData
										.defaultDirectory()));
							} catch (IOException e) {
							}
						}  
						
						if (LauncherSettings[1].BeingHovered(cursor)) {	//toggle fullscreen in the settings file
						int setting_ID = 1;
						int new_state = 0;
						if(InitSettingsData[setting_ID]==0){new_state=1;}
						changeInitSetting(setting_ID,new_state);						
						}
						
						
						
				}
			}
	
		}
		
	}
	
	public void pollInput_MenuKeyboard() {
	

		// boolean anykeypressed = false;
		int KeyPressed = -1;// can only be one at a time!
		for (int i = 0; i < 244; i++) {
			if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]) {

				KeyPressed = i;

			}

		}

	
	/*
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Keyboard_KeyAlreadyDown[Keyboard.KEY_ESCAPE] == false) {
			openGameMenu(MENU_ESC);
		}// esc
		
		*/
		
	}
	
	
	void changeInitSetting(int setting, int newstate) {
		InitSettingsData[setting] = newstate;
		writeSettingstoFile(true);

		/*if (setting == SharedData.INITSETTING_FULLSCREEN) {
			if (newstate == 0) {
				FullScreen = false;
			} else {
				FullScreen = true;
			}
			//refreshDisplayMode();
			ShowWarningDialog("Must restart for changes to take effect.");
		}

		if (setting == SharedData.INITSETTING_MUSICMUTED) {
			if (newstate == 0) {
				BackgroundMusic.setVolume(MUSICVOLUME_FULL);
			} else {
				BackgroundMusic.setVolume(0);
			}

		}*/

	}
	

	
void writeSettingstoFile(boolean overwrite) {
		
		String sFileName = SharedData.defaultDirectory() + "\\" + "Settings.txt";

		
		BufferedWriter writer = null;
		if (new File(sFileName).exists() == false || overwrite) {

		    XStream xstream = new XStream(new DomDriver());
			
			String output = xstream.toXML(mySettings);
	        
	         try {
	        FileWriter fileWriter = null;
	        fileWriter = new FileWriter(new File(sFileName));
            fileWriter.write(output);
            
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	       
		}
		

	}

private void readSettingsfromFile() {

	String sFileName = SharedData.defaultDirectory() + "\\" + "settings.txt";


	String xmldata = "";
	FileInputStream fis = null;
	
	
	
	try {
		fis = new FileInputStream(sFileName);

		//System.out.println("Total file size to read (in bytes) : "+ fis.available());

		int content;
		while ((content = fis.read()) != -1) {
			// convert to char and display it
			//System.out.print((char) content);
			xmldata += (char) content;
		}

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (fis != null)
				fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

	

	
	XStream xstream = new XStream(new DomDriver());
	
	mySettings = (Settings)xstream.fromXML(xmldata);
	
	
	
}
	
	boolean[] Keyboard_KeyAlreadyDown = new boolean[255];
	boolean[] Mouse_MouseAlreadyClicked = new boolean[3];

	
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

	


	


	

	double animfactor = 100;

	void tileAnimationTimerTick() {

		animfactor = (delta * 100) / 15;

		if (animationframe >= 31) {
			animationframe = 0;
		} else {
			animationframe++;
		}

		

	}

	
	

	void Fill_Rect(int x, int y, int width, int height) {

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex2f(x, y);

		GL11.glVertex2f(x + width, y);

		GL11.glVertex2f(x + width, y + height);

		GL11.glVertex2f(x, y + height);

		GL11.glEnd();

		if (TextureModeWasEnabled) {
			GL11.glEnable(GL_TEXTURE_2D);
		}

	}
	
	
	
	void Draw_Line(int x, int y, int x2, int y2) {

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		GL11.glBegin(GL11.GL_LINES);

		GL11.glVertex2f(x, y);

		GL11.glVertex2f(x2, y2);

		GL11.glEnd();

		if (TextureModeWasEnabled) {
			GL11.glEnable(GL_TEXTURE_2D);
		}

	}

	void Outline_Rect(int x, int y, int width, int height) {

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		GL11.glBegin(GL11.GL_LINES);

		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + width, y);
		GL11.glVertex2f(x + width, y);
		GL11.glVertex2f(x + width, y + height);

		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x, y + height);

		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x, y);

		GL11.glEnd();

		if (TextureModeWasEnabled) {
			GL11.glEnable(GL_TEXTURE_2D);
		}

	}

	
	Image2D generate_typeimage_local(String ref) {//LOAD AN IMAGE INTO THE GAME DURING LOAD ASSETS! (Local File)
		
		System.out.println(ref );
		
		
		
		Image2D img = null;
		try {
			img = new Image2D(textureLoader.getTexture("wrap/assets/" + ref));
		} catch (IOException e) {
			System.err.print("No Texture At Path Specified!");
			e.printStackTrace();
		}

		

		return img;

	}
	
	int[][] parse_font_lookup_table(String filepath){
		
		int[][] answer = new int[256][6];//for each char.. x,y,width,height,xoff,yoff
		  String[] lookuptable_lines = new String[1000];
		   
		   
	      int linenum = 0;
	      
	      int indexer_start=-1;
	      int indexer_end=-1;
	      
	      FileReader fr;
		try {
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(Core.class.getResourceAsStream(filepath), "UTF-8"));
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
	
	


	
	
	public static void copyFile(File sourceFile, File destFile, boolean overwrite) throws IOException {
	    boolean already_exists = destFile.exists();
		if(!already_exists) {  
	        destFile.createNewFile();
	    }
	    
	    if(!already_exists || overwrite) {  
	    	//System.out.println("uno");

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	        //System.out.println("dos");

	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	    
	    
	    
	    
	    }
	}
	
	


	
	
	
	
	
	
	  boolean StringToBoolean(String s){
		  
		  boolean b = false;
		  
		  try{
		  if( Integer.parseInt(s) == 1){b = true;}
		  }catch(Exception e){}
		  
		  
		  return b;
	  }
	  
	  int StringToInteger(String s){
		  
		  int i=0;
		  
		  try{
			 i = Integer.parseInt(s) ;
		 }catch(Exception e){}
		 
		  
		  return i;
	  }
	  
	  
	  



void ChangeMainMenuScreen(int i){
	 
		
current_MainMenu_Screen = i;
Mouse_MouseAlreadyClicked[0] = true;
Mouse_MouseAlreadyClicked[1] = true;


}
	


void debug(Object o){if(DEBUG_MODE){System.out.println(o);}}


void ShowWarningDialog(String message){
	
	
}
	

}// end core as a whole

