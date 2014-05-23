package wrap;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;


import java.awt.Color;

import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;



import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


import org.apache.commons.io.IOUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;



/*
 * -simpleish elevation system?
 * 
 * -add portals?
 * 
 * -add more Largeobjects. (doors, traps, that LOOK GOOD.)
 *  -put in large objects from 'mage city'?
 *  
 * -add notes
 *
 * -add objectives
 * 
 *  -make it super easy to draw houses and cliffs...?
 * 
 * -undo button, already log past 1000 tile changes
 * 
add objects with sprite sheets, need FENCES FROM FARM PACK


//make an objectives menu


//drawing blank tiles at the beginning will make some weired green rect!?


  
 * -add computer terminals, potion stations, etc
 * 
 * 
 * 
*/
//http://www.pathofexile.com/news/2010-12-20/tile-texture-mapping
//www.cgtextures.com/

public class MapEditorCore {
	
	
	/** The pixel size of tiles. */
	public static final int TILE_SIZE = 40;
	
	public static final int NUMBER_OF_TERRAIN_TILESETS = 13;
	
	static SharedData myshareddata = new SharedData();
	
	int SCREEN_WIDTH = 800;
	/** Screen height in pixels. */
	 int SCREEN_HEIGHT = 500;	
	 
	 static boolean DEBUG_MODE = true;
	 
	/** How many tiles are displayed horizontally. */
	int SCREEN_X_TILES = SCREEN_WIDTH/TILE_SIZE;
	/** How many tiles are displayed vertically. */
	int SCREEN_Y_TILES = SCREEN_HEIGHT/TILE_SIZE;
	/** Vertical space allocated for status bar. */
	public static final int STATUS_BAR_HEIGHT = 0;
	
	
	public static final double MINIMUM_LIT_DUNGEON = 0.3;
	public static final double MINIMUM_LIT_INDOORS = 0.3;
	public static final double MINIMUM_LIT_OUTDOORS = 0.9;
	
	
	 
	
	// Map data
	
			//public static final int ITEM_LAYER = 9; 
			//public static final int REGION_LAYER = 10; 
			
			
			static final String WINDOW_TITLE = "Baneforge Map Editor "+ myshareddata.CURRENTVERSION;
			
			
			
			/** The type on each map tile, or NOTHING if it's empty. */        
			protected int[][][] map = new int[myshareddata.NUMBER_OF_LAYERS][myshareddata.MAP_SIZE][myshareddata.MAP_SIZE];    //This array will eventually be loaded by the map file (made in editor) and this will be all that is needed to load!!!
		
			//protected double[][] lit = new double[myshareddata.MAP_SIZE*2][myshareddata.MAP_SIZE*2];
			protected double[][] lit = new double[myshareddata.MAP_SIZE*4][myshareddata.MAP_SIZE*4];
			protected double[][] alltorchlit = new double[myshareddata.MAP_SIZE*4][myshareddata.MAP_SIZE*4];
			
			int [][] TerrainVariantMap = new int[myshareddata.MAP_SIZE][myshareddata.MAP_SIZE];
			
			
			boolean[][][] define_regions = new boolean[5][myshareddata.MAP_SIZE][myshareddata.MAP_SIZE];
			
			
			int[] layer_assets_start = new int[myshareddata.NUMBER_OF_LAYERS];
			int[] layer_assets_end = new int[myshareddata.NUMBER_OF_LAYERS];
			
			
			LoggedAction[] myLoggedActions = new LoggedAction[1000];
			
			File AppDataFolder;
			
			int drag_square_x = -1;
			int drag_square_y = -1;
			
			int[][] warprift = new int[1000][4]; // ID, the 4 coordinates x1 y1 x2 y2
			int num_of_warprifts;
			
			int toppaneloffset = 0;
			final int toppaneloffsetmax = 100;
			
			int brush_wheel_offset = 0;
			
			//ImageIcon[] alt_brush_icon = new ImageIcon[NUMBER_OF_TYPES];
			
			
			static final String[] regionOptions = {"Indoors","Dungeon"};
			
			
			///BEGIN ITEM DECLARATION
		
			//public static final int EQUIPPED_STEELHELM = 820;

			//END ITEMS
			
			String[] brushDescriptions = new String[NUMBER_OF_TYPES];
			
			public final int tooltiplinelength=40;
			String[] displayedHovertooltip = new String[5];
			
			public static final int NUMBER_OF_TYPES = 999;
			
			//public static final Image[] image = new Image[NUMBER_OF_TYPES];
			//public static final ImageIcon[] typeimage = new ImageIcon[NUMBER_OF_TYPES]; 
			//public static final ImageIcon[] itemicon = new ImageIcon[NUMBER_OF_TYPES];
			public static final int[] playerHero = new int[10];
			
			//public static final int[] myshareddata.imageOffset_x = new int[NUMBER_OF_TYPES]; //will usually be 0, except for images that are larger than the size of a tile
			//public static final int[] myshareddata.imageOffset_y = new int[NUMBER_OF_TYPES];
			
			
			Image2D[] lightrays = new Image2D [4];
		
			protected Color[] typeColors = new Color[NUMBER_OF_TYPES];
				
			int currenttilebrush=0;
			int current_paintbrush_layer=myshareddata.TERRAIN_LAYER_LOWER;
			int current_paintbrush_subcategory=0;
			
			int currentregionbrush=0;
			
			String[] layernames = new String[15];
			
			 
			//String displayedtooltip = null;
			String displayedLeftTooltip[] = new String[9];
			 
			 boolean paintingOnGfxpnl;//makes left and right clicking differ
			 //boolean RandomBrushMode;
			 boolean DragRectBrushMode = true;
			 
			//note[] myNotes = new note[999];//can only have a maximum of 999 notes, this is for writing to a file
			//int number_of_notes;
			String NoteMessages[][] = new String[myshareddata.MAP_SIZE][myshareddata.MAP_SIZE];  //local storage only
            
			BufferedImage bufferedImg = null;
			BufferedImage[] temp_sprites = new BufferedImage[4 * 4];
			
			
		
		
		/*
		//these objects cannot be placed or deleted normally!
		public static final int[] PROTECTED_OBJECTS = new int[] {
			
			SMALLCLIFF_H1,SMALLCLIFF_H2,SMALLCLIFF_V1,SMALLCLIFF_V2
			
		};
		*/
		
		
		

	static MapEditorCore mainedit;
	
	//Image2D[][][] TypeImages_animated_directional = new Image2D[NUMBER_OF_TYPES][4][8];
	SpriteSheet[] Object_Spritesheet = new SpriteSheet[SharedData.NUMBER_OF_TYPES];
	
	
TextureLoader textureLoader = new TextureLoader();
	
	public static void main(String[] args) {
		  mainedit = new MapEditorCore();
	}
	
	 //public JFrame frm = new JFrame();
	  //public JPanel intropnl = new JPanel();
	 // public JTextArea help = new JTextArea();
	
	  Image2D MasterTerrainSheet;
	  Image2D SmallObject50Sheet;
	  Image2D LargeObject50Sheet;
	  Image2D LargeObject100Sheet;
	  
	  Image2D LargeObject200Sheet;
	  DB_Types[] myTypeDB = new DB_Types[NUMBER_OF_TYPES];
	  
	
	  
	MapEditorCore(){
		
		new File(SharedData.baseDirectory() ).mkdir();
		new File(SharedData.defaultDirectory() ).mkdir();
		new File(SharedData.defaultDirectory()  + "\\Profile Images").mkdir();
		new File(SharedData.defaultDirectory()  + "\\Heroes").mkdir();
		new File(SharedData.defaultDirectory()  + "\\Maps").mkdir();
		new File(SharedData.defaultDirectory()  + "\\Campaigns").mkdir();
		new File(SharedData.defaultDirectory()  + "\\Skins").mkdir();
		
		 AppDataFolder = new File(SharedData.defaultDirectory() + "\\Maps");
		 
		 

			CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultTerrainSheet.png" ,  SharedData.defaultDirectory() + "\\Skins\\" + "TerrainSheet.png" );
			CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultSmallObjectSheet_50.png" ,  SharedData.defaultDirectory() + "\\Skins\\" + "SmallObjectSheet_50.png" );
			CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultLargeObjectSheet_50.png" ,  SharedData.defaultDirectory() + "\\Skins\\" + "LargeObjectSheet_50.png" );
			CopyLocalFileIfNotFoundInAppData( "wrap/assets/DefaultLargeObjectSheet_100.png" ,  SharedData.defaultDirectory() + "\\Skins\\" + "LargeObjectSheet_100.png" );
			
			
			CopyLocalFileIfNotFoundInAppData("wrap/assets/About.txt",
					SharedData.defaultDirectory() + "\\" + "About Baneforge.txt");

			CopyLocalFileIfNotFoundInAppData("wrap/assets/LostVillage.vmm",
					SharedData.defaultDirectory() + "\\Maps\\" + "LostVillage.vmm");

			
			
			fc = new JFileChooser(  AppDataFolder ) ;//defaults to where baneforge is
			fcFrame = new JFrame();
			fcFrame.setLocation( Toolkit.getDefaultToolkit().getScreenSize().width / 2 ,Toolkit.getDefaultToolkit().getScreenSize().height / 2);
			fcFrame.setUndecorated(true);
			fcFrame.setAlwaysOnTop(true);
			fcFrame.setVisible(false);
	
		
		
		for (int i = 0; i < NUMBER_OF_TYPES; i++) {
			myTypeDB[i] = new DB_Types();
		}
		
		for (int i = 0; i < 1000; i++) {
			myLoggedActions[i] = new LoggedAction();
		}
		
		
		
		mapconfigfrm = new JFrame();
		mapconfigfrm.setTitle("Map Configuration");
		mapconfigpnl = new JPanel();
		mapconfigfrm.setVisible(false);
		mapconfigfrm.setDefaultCloseOperation(mapconfigfrm.HIDE_ON_CLOSE);
		mapconfigfrm.setSize(400, 500);
	//	mapconfigfrm.setLocationRelativeTo(frm);
		mapconfigfrm.setResizable(false);
		mapconfigfrm.setFocusable(true);
		
		mapconfigpnl.setBorder(null);
		mapconfigpnl.setLayout(null);
		
		mapconfigfrm.add(mapconfigpnl);
		
		
		JLabel mapconfignamelabel = new JLabel("Map Name:");
		mapconfignamelabel.setBounds(20,20,100,30);
		mapconfigpnl.add(mapconfignamelabel);
		
		mapconfignamefield.setBounds(100,20,200,30);
		mapconfigpnl.add(mapconfignamefield);
		
		JLabel mapconfigdescriplabel = new JLabel("Description:");
		mapconfigdescriplabel.setBounds(20,70,100,30);
		mapconfigpnl.add(mapconfigdescriplabel);
		
	      Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
	    	      BorderFactory.createEmptyBorder(10, 10, 10, 10));
	      mapconfigdescrip.setBorder(border);
		mapconfigdescrip.setLineWrap(true);
		mapconfigdescrip.setBounds(20,100,350,200);
		mapconfigpnl.add(mapconfigdescrip);
		
		JButton mapconfigokaybtn = new JButton("Okay");
		mapconfigokaybtn.setBounds(300,410,80,50);
		mapconfigpnl.add(mapconfigokaybtn);  
		mapconfigokaybtn.addMouseListener(new MouseAdapter(){
       	 public void mousePressed(MouseEvent m) {
       		mapconfigfrm.setVisible(false);
       		mapName=mapconfignamefield.getText();
       		mapDescrip=mapconfigdescrip.getText();
       	 }
		});
		
		
		//for(int i=0;i<9;i++){HeroSpawnPlaced[i] = false;}
		
		
		 
		 //add more descriptions!
		 //brushDescriptions[myshareddata.SPECTRE] = "A horrifying spirit.";
		 brushDescriptions[myshareddata.LADDER] = "A ladder that units can climb up or down to reach different floors.";
		 
		

		InitDisplaySettings();
		
		
		
		
		 
		 LoadGameAssets();
	 	
		 initeditor();
		 
	 	gameLoop();

	 	
	
	}
	
	
	


	void InitDisplaySettings() {



		
		 try {
				setDisplayMode();

				Display.setTitle(WINDOW_TITLE);

				
				// Display.setFullscreen(FullScreen_Is_Enabled);
				Display.setFullscreen(false);
				Display.setResizable(true);
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

				String[] DisplayIconPaths = {"wrap/assets/misc/editicon16.png","wrap/assets/misc/editicon32.png","wrap/assets/misc/editicon128.png"};
				ByteBuffer[] BFIcons = textureLoader.getByteBuffer(DisplayIconPaths);
				Display.setIcon(BFIcons);


			} catch (LWJGLException le) {

				System.out.println("Game exiting - exception in initialization:");

				le.printStackTrace();

				// Game.gameRunning = false;
			}
		 
		 
		 

		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		GL11.glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		GL11.glDisable(GL_DEPTH_TEST);

		GL11.glMatrixMode(GL_MODELVIEW);

		GL11.glLoadIdentity();

		GL11.glEnable(GL_BLEND);
		GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		//SplashScreenLogo.draw(170, 100);
		Display.update();
		// }

	}
	
	  Image2D RoofSheet;
	  Image2D SeamlessRock;
	
	CustomFont Verdana_12;
	CustomFont Verdana_14;
	CustomFont Verdana_16;
	CustomFont Verdana_18;
	CustomFont Verdana_20;
	Image2D hovertooltipbg;
	Image2D erasericon;
	Image2D eraserglowicon;
	TextBox[] myTextBoxes = new TextBox[20];
	
	void LoadGameAssets(){
		
		
				Verdana_14 = new CustomFont( generate_tex_local("fonts/" + "verdana14.png"), parse_font_lookup_table("fonts/" + "verdana14.fnt") );
				Verdana_12 = new CustomFont( generate_tex_local("fonts/" + "verdana12_0.png") , parse_font_lookup_table("fonts/" + "verdana12.fnt") );	
				Verdana_16 = new CustomFont( generate_tex_local("fonts/" + "verdana16_0.png")  , parse_font_lookup_table("fonts/" + "verdana16.fnt") );
				Verdana_18 = new CustomFont( generate_tex_local("fonts/" + "verdana18_0.png")  , parse_font_lookup_table("fonts/" + "verdana18.fnt") );
				Verdana_20= new CustomFont( generate_tex_local("fonts/" + "verdana20_0.png")   , parse_font_lookup_table("fonts/" + "verdana20.fnt") );
				
			
		
		
		
		
		//MasterTerrainSheet= generate_typeimage_ext("tiletextures/masterterrainsheet2.png");
		//SmallObject50Sheet= generate_typeimage_ext("objects/smallobjectsheet_50.png");
		//LargeObject50Sheet= generate_typeimage_ext("objects/largeobjectsheet_50.png");
		//LargeObject100Sheet= generate_typeimage_ext("objects/largeobjectsheet_100.png");
		
		MasterTerrainSheet= generate_typeimage_ext(SharedData.defaultDirectory() + "\\Skins\\" + "TerrainSheet.png");

		SmallObject50Sheet= generate_typeimage_ext(SharedData.defaultDirectory() + "\\Skins\\" + "SmallObjectSheet_50.png");

		LargeObject50Sheet= generate_typeimage_ext(SharedData.defaultDirectory() + "\\Skins\\" + "LargeObjectSheet_50.png");
 
		LargeObject100Sheet= generate_typeimage_ext(SharedData.defaultDirectory() + "\\Skins\\" + "LargeObjectSheet_100.png");
		
		LargeObject200Sheet= generate_typeimage_local("objects/LargeObjectSheet_200.png");
		
		RoofSheet = generate_typeimage_local("objects/roofsheet.png");
		SeamlessRock = generate_typeimage_local("misc/seamlessrock.png");
   
		myTypeDB[SharedData.TEMPLESTAIRS_DOWN_1].image = generate_typeimage_local("objects/temple_entrance.png");
		myTypeDB[SharedData.TEMPLESTAIRS_DOWN_2].image = generate_typeimage_local("objects/temple_entrance2.png");
		
	
		for(int i=myshareddata.PATHING_BLOCKER_LOW;i<=myshareddata.HEROSPAWN;i++){
			myTypeDB[i].spritesheet=1;
			myTypeDB[i].sheet_index=i - myshareddata.PATHING_BLOCKER_LOW;
			
		}
		
		
		for(int i=myshareddata.DOOR_H_OPEN;i<=myshareddata.FERN_2;i++){
			myTypeDB[i].spritesheet=2;
			myTypeDB[i].sheet_index=i - myshareddata.DOOR_H_OPEN;
		}
		
		
		
		for(int i=myshareddata.TANROCKS1;i<=myshareddata.SIGNALFIRE;i++){
			myTypeDB[i].spritesheet=3;
			myTypeDB[i].sheet_index=i - myshareddata.TANROCKS1;
		}
		
		
		for(int i=myshareddata.BOOKSHELF;i<=myshareddata.TENT_12;i++){
			myTypeDB[i].spritesheet=4;
			myTypeDB[i].sheet_index=i - myshareddata.BOOKSHELF;
		}
		
		
		Object_Spritesheet[SharedData.TORCH] = new SpriteSheet(64, 4,
				generate_typeimage_local("objects/torch.png"));
		Object_Spritesheet[SharedData.HEROSPAWN] = new SpriteSheet(64, 4,
				generate_typeimage_local("objects/stonepad.png"));

		Object_Spritesheet[SharedData.SIGNALFIRE] = new SpriteSheet(100, 8,
				generate_typeimage_local("objects/signalfire2.png"));
		
		
		
			//TypeImages_spritesheet[myshareddata.SIGNALFIRE] = generate_typeimage_local("objects/signalfire.png");
		
		
/*
		myTypeDB[myshareddata.HEROSPAWN].image = generate_typeimage_local("objects/stonepad_0.png");
		for (int i = 0; i < 2; i++) {
			TypeImages_animated_directional[myshareddata.HEROSPAWN][0][i] = generate_typeimage_local("objects/stonepad_"
					+ (i + 1) + ".png");
		}*/
		
		for(int i=0;i<7;i++){
		myTypeDB[myshareddata.HERO_2+i].image = generate_typeimage_local("herosprites/heropreview.png");
		}
		
		
		
		for (int i = 0; i < 4; i++) {
			lightrays[i] = generate_typeimage_local("objects/lightrays" + (i + 1)
					+ ".png");
		}

		myTypeDB[myshareddata.SMALLCLIFF_H1].image = generate_typeimage_local("objects/smallcliff_h1.png");
		myTypeDB[myshareddata.SMALLCLIFF_H2].image = generate_typeimage_local("objects/smallcliff_h2.png");
		myTypeDB[myshareddata.SMALLCLIFF_V1].image = generate_typeimage_local("objects/smallcliff_v1.png");
		myTypeDB[myshareddata.SMALLCLIFF_V2].image = generate_typeimage_local("objects/smallcliff_v2.png");
		myTypeDB[myshareddata.SMALLCLIFF_C1].image = generate_typeimage_local("objects/smallcliff_c1.png");
		myTypeDB[myshareddata.SMALLCLIFF_C2].image = generate_typeimage_local("objects/smallcliff_c2.png");
		myTypeDB[myshareddata.SMALLCLIFF_C3].image = generate_typeimage_local("objects/smallcliff_c3.png");
		myTypeDB[myshareddata.SMALLCLIFF_C4].image = generate_typeimage_local("objects/smallcliff_c4.png");


		//myTypeDB[SharedData.PATHING_BLOCKER_LOW].DoNotRender = true;
		//myTypeDB[SharedData.PATHING_BLOCKER_HIGH].DoNotRender = true;
		
		
		
		for (int i = 0; i < 4; i++) {
			myTypeDB[myshareddata.WOODWALL_C1 + i].image = generate_typeimage_local("objects/woodwall_c"
					+ (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[myshareddata.WOODWALL_V1 + i].image = generate_typeimage_local("objects/woodwall_v"
					+ (i + 1) + ".png");
		}
		
		for (int i = 0; i < 4; i++) {
			myTypeDB[myshareddata.WOODWALL_H1 + i].image = generate_typeimage_local("objects/woodwall_h"
					+ (i + 1) + ".png");
		}
		
		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.WOODWALL_E1 + i].image = generate_typeimage_local("objects/woodwall_e" + (i + 1) + ".png");
		}
		
		for (int i = 0; i < 8; i++) {
			myTypeDB[myshareddata.CLIFF_C1 + i].image = generate_typeimage_local("objects/cliff_c"
					+ (i + 1) + ".png");
		}
		
		for (int i = 0; i < 4; i++) {
			myTypeDB[myshareddata.CLIFF_H1 + i].image = generate_typeimage_local("objects/cliff_h"
					+ (i + 1) + ".png");
		}
		
		for (int i = 0; i < 4; i++) {
			myTypeDB[myshareddata.CLIFF_V1 + i].image = generate_typeimage_local("objects/cliff_v"
					+ (i + 1) + ".png");
		}
	
		

		
		
		
		//change these
	

		myTypeDB[myshareddata.YELLOW_NOTE].image = generate_typeimage_local("tiletextures/yellownote.png");
		myTypeDB[myshareddata.RED_NOTE].image = generate_typeimage_local("tiletextures/rednote.png");
		myTypeDB[myshareddata.BLUE_NOTE].image = generate_typeimage_local("tiletextures/bluenote.png");
		myTypeDB[myshareddata.GREEN_NOTE].image = generate_typeimage_local("tiletextures/greennote.png");
		
		
		
		/*

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 2; k++) {
				TypeImages_animated_directional[myshareddata.SKELETON][i][k] = generate_typeimage_local("npcs/skeleton_"
						+ i + "_" + k + ".png");

			}
		}

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				TypeImages_animated_directional[myshareddata.SPRITE_HUMAN][i][k] = generate_typeimage_local("herosprites/myadv_"
						+ i + "_" + k + ".png");
			}
		}
*/
	
		


		erasericon = generate_typeimage_local("mapeditor/eraser.png");
		eraserglowicon = generate_typeimage_local("mapeditor/eraser_glow.png");
		hovertooltipbg = generate_typeimage_local("misc/hovertooltipbg.png");

	

		cursor.whiteselector = generate_typeimage_local("misc/DMCursor.png");
		//cursor.greenselector = generate_typeimage_local("misc/DMCursor3.png");
		cursor.heroselector_back = generate_typeimage_local("misc/selectioncircle_back.png");
		cursor.heroselector_front = generate_typeimage_local("misc/selectioncircle_front.png");

		
		
		//make these parameters the defaults in the map editor, but allow the map editor to change them!!!!
		myTypeDB[myshareddata.MAGICCHEST_BLACK].Can_Contain_Items = true;//basic weapons
		myTypeDB[myshareddata.MAGICCHEST_BLACK].Items_Dropped_areInstanced = true;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroups[0] = 13;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroupchances[0] = 10;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroups[1] = 16;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroupchances[1] = 10;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroups[2] = 19;
		myTypeDB[myshareddata.MAGICCHEST_BLACK].LootTable_itemgroupchances[2] = 10;
		
		myTypeDB[myshareddata.MAGICCHEST_RED].Can_Contain_Items = true;//rare
		myTypeDB[myshareddata.MAGICCHEST_RED].Items_Dropped_areInstanced = true;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroups[0] = 8;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroupchances[0] = 10;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroups[1] = 9;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroupchances[1] = 10;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroups[2] = 10;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroupchances[2] = 10;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroups[3] = 11;
		myTypeDB[myshareddata.MAGICCHEST_RED].LootTable_itemgroupchances[3] = 10;
		
		myTypeDB[myshareddata.MAGICCHEST_GREEN].Can_Contain_Items = true;//custom
		myTypeDB[myshareddata.MAGICCHEST_GREEN].Items_Dropped_areInstanced = true;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroups[0] = 21;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroupchances[0] = 50;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroups[1] = 22;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroupchances[1] = 50;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroups[2] = 23;
		myTypeDB[myshareddata.MAGICCHEST_GREEN].LootTable_itemgroupchances[2] = 50;
		
		myTypeDB[myshareddata.MAGICCHEST_BLUE].Can_Contain_Items = true;//misc
		myTypeDB[myshareddata.MAGICCHEST_BLUE].Items_Dropped_areInstanced = true;
		myTypeDB[myshareddata.MAGICCHEST_BLUE].LootTable_itemgroups[0] = 1;
		myTypeDB[myshareddata.MAGICCHEST_BLUE].LootTable_itemgroupchances[0] = 50;
		myTypeDB[myshareddata.MAGICCHEST_BLUE].LootTable_itemgroups[1] = 2;
		myTypeDB[myshareddata.MAGICCHEST_BLUE].LootTable_itemgroupchances[1] = 50;
		
		myTypeDB[myshareddata.MAGICCHEST_WHITE].Can_Contain_Items = true;//common mats
		myTypeDB[myshareddata.MAGICCHEST_WHITE].Items_Dropped_areInstanced = true;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroups[0] = 4;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroupchances[0] = 20;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroups[1] = 5;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroupchances[1] = 20;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroups[2] = 6;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroupchances[2] = 20;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroups[3] = 7;
		myTypeDB[myshareddata.MAGICCHEST_WHITE].LootTable_itemgroupchances[3] = 20;
		
		
		myTypeDB[myshareddata.WOOD_CHEST].Can_Contain_Items = true;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroups[0] = 4;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroupchances[0] = 20;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroups[1] = 5;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroupchances[1] = 20;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroups[2] = 6;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroupchances[2] = 20;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroups[3] = 7;
		myTypeDB[myshareddata.WOOD_CHEST].LootTable_itemgroupchances[3] = 20;
		
		
		/*String[] lootgroupdropdown_Strings = {"None", "Misc 1", "Misc 2", "Misc 3", "Common Resources", "Common Materials", "Common Ores",
			"Common Ingredients", "Rare Resources", "Rare Materials", "Rare Ores", "Rare Ingredients", "Weapons 1", "Weapons 2", "Weapons 3",
			"Armor 1", "Armor 2", "Armor 3", "Magic Items 1", "Magic Items 2", "Magic Items 3", "Custom Group 1", "Custom Group 2", "Custom Group 3",
			"Custom Group 4", "Custom Group 5"};*/


		myTypeDB[myshareddata.TREASURECHEST].Can_Contain_Items = true;
		myTypeDB[myshareddata.TREASURECHEST_2].Can_Contain_Items = true;
		myTypeDB[myshareddata.ASHES].Can_Contain_Items = true;
		
		
		for(int i=0;i<20;i++){myTextBoxes[i] = new TextBox();}

		
		
		
	
	}
	
	private boolean setDisplayMode() {
		try {
			
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

		

		}

		return false;

	}
	
	
	
	boolean LightIsVisible = true;
	boolean FullScreenMode = false;

	
   // boolean HeroSpawnPlaced[] = new boolean[9];///use me!
	
	public JFrame mapconfigfrm;
	public JPanel mapconfigpnl;
	public JTextField mapconfignamefield = new JTextField();
	public JTextArea mapconfigdescrip = new JTextArea();
	
	
	MouseCursor cursor = new MouseCursor();
	Camera cam = new Camera();
	
	//JPanel tilebrushpnl = new JPanel();
	//JPanel regionpnl = new JPanel();
	//JScrollPane brushScroll;
	//JLabel currentlayerlabel = new JLabel();
	//JLabel regionDefineIndoorsLabel = new JLabel();
	
	//JMenuItem viewItemLight;
	//JMenuItem viewItemFullscreen;
	//JMenuItem drawItemRandomBrushMode;
	//JMenuItem drawItemDragRectBrushMode;
	
	String mapName;
	String mapDescrip;
	
	//gfxpnl gfxpnl1 = new gfxpnl();
	 JFileChooser fc;
	 JFrame fcFrame;
	// 
	public void initeditor(){
		
	
		
	
		
		for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
			for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

		if(Math.random() > 0.7){
			TerrainVariantMap[x][y] = 1 + (int)(Math.random()*3);
			}
			}}


		
		layer_assets_start[myshareddata.TERRAIN_LAYER_LOWER] = 1;
		layer_assets_end[myshareddata.TERRAIN_LAYER_LOWER] = NUMBER_OF_TERRAIN_TILESETS+1;
		layer_assets_start[myshareddata.TERRAIN_LAYER_HIGHER] = 1;
		layer_assets_end[myshareddata.TERRAIN_LAYER_HIGHER] = NUMBER_OF_TERRAIN_TILESETS+1;
		layer_assets_start[myshareddata.SMALLOBJECTS_LAYER] = 100;
		layer_assets_end[myshareddata.SMALLOBJECTS_LAYER] = 299;
		layer_assets_start[myshareddata.PLAYER_LAYER] = 300;
		layer_assets_end[myshareddata.PLAYER_LAYER] = 399;
		layer_assets_start[myshareddata.LARGEOBJECTS_LAYER] = 400;
		layer_assets_end[myshareddata.LARGEOBJECTS_LAYER] = 799;
		layer_assets_start[myshareddata.REGION_LAYER] = 650;
		layer_assets_end[myshareddata.REGION_LAYER] = 653;
		
	
		setTypeColors(SharedData.LIGHTGRASS, 20, 100, 20);
		setTypeColors(SharedData.DARKGRASS, 11, 55, 11);
		setTypeColors(SharedData.LIGHTDIRT, 160, 80, 40);
		setTypeColors(SharedData.DARKDIRT, 110, 50, 20);
		setTypeColors(SharedData.SAND, 234, 223, 134);
		setTypeColors(SharedData.SANDISLAND, 234, 223, 134);
		setTypeColors(SharedData.WATER, 0, 130, 180);
		setTypeColors(SharedData.LAVA, 240, 111, 111);
		setTypeColors(SharedData.BLACKROCK, 53, 53, 53);
		setTypeColors(SharedData.PIT, 5, 5, 5);
		setTypeColors(SharedData.FARMSOIL, 110, 50, 20);		
		setTypeColors(SharedData.SNOW, 88, 88, 88);
		setTypeColors(SharedData.COBBLESTONE, 44, 44, 44);
		
		
		layernames[myshareddata.TERRAIN_LAYER_LOWER] = "Base Terrain";
		layernames[myshareddata.TERRAIN_LAYER_HIGHER] = "Secondary Terrain";
		layernames[myshareddata.SMALLOBJECTS_LAYER] = "Small Objects";
		layernames[myshareddata.PLAYER_LAYER] = "Units";
		layernames[myshareddata.LARGEOBJECTS_LAYER] = "Large Objects";
		layernames[myshareddata.REGION_LAYER] = "Regions";
		//layernames[ITEM_LAYER] = "Items";
		//layernames[REGION_LAYER] = "Regions";
	
		
		
		for(int l = 0;l<=1;l++){
			for(int t = 0;t<(NUMBER_OF_TERRAIN_TILESETS+1);t++){
			layer_brushes_found[l][t] = t+1;
			number_of_layer_brushes_found[l]++;
			}
		}
		
		
		for(int l = 2;l<=5;l++){
			//int brushesFoundInThisLayer = 0;
			for (int t = layer_assets_start[l];t<=layer_assets_end[l];t++){
			boolean imagefound = false;
			
			if(myTypeDB[t].spritesheet>0){imagefound=true;}
			if(myTypeDB[t].image!=null){imagefound=true;}
						
				if(imagefound){
					
				
			layer_brushes_found[l][number_of_layer_brushes_found[l]] = t;
			//brushesFoundInThisLayer++;
			number_of_layer_brushes_found[l]++;
				}
			
			
			}
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	private void gameLoop() {

		while (!Display.isCloseRequested() ) {

			config_UI_sizes_continuous();

			/** make sure resizing on the fly doesn't break anything */
			glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();

			
			cursor.Poll_Mouse_Position(SCREEN_HEIGHT, cam, TILE_SIZE,myshareddata.MAP_SIZE,SCREEN_X_TILES,SCREEN_Y_TILES);
			
			tileAnimationTimerTick();
			
			calculateLightingEveryFrame();///must come after timers to grab procedural lit

				pollInput_Mouse();
				pollInput_Keyboard();
				set_inputs_already_down();

				//loop_menu_music();
				
				//menuRendering();
				gameRendering();

				// TWL_gui.update();
				Display.update(); // removing this does not fix anything!
				// TestUtils.reduceInputLag();
				Display.sync(60);
			} 
		

		// clean up
		// soundManager.destroy();
		// frm.dispose();
		Display.destroy();
		System.exit(0);
	}

	
void config_UI_sizes_continuous() {
		
		if(Display.isFullscreen()){
			
		}else{
		SCREEN_WIDTH = Display.getWidth();
		SCREEN_HEIGHT = Display.getHeight();
		}
		SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1; // System.out.println(SCREEN_X_TILES);
		SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;
		
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
	
	long delta;
	long lastLoopTime;
	long lastFpsTime;
	int fps ;
	
	
	
	
	void gameRendering(){
		
	
		
		delta = getTime() - lastLoopTime;
		lastLoopTime = getTime();
		lastFpsTime += delta;
		fps++;

		// update our FPS counter if a second has passed
		if (lastFpsTime >= 1000) {
			Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
			lastFpsTime = 0;
			fps = 0;
		}

		
		glClear(GL_COLOR_BUFFER_BIT);

		glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		glDisable(GL_DEPTH_TEST);

	
		glMatrixMode(GL_MODELVIEW);

		glLoadIdentity();
	
		
		
		
		
		if(animationframe==0){
		for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
			for (int x = 0; x < myshareddata.MAP_SIZE; x++) {
				//animate water
				if((map[myshareddata.TERRAIN_LAYER_LOWER][y][x]==7 && map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]==0) || map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]==7){
				if(Math.random() > 0.8){
				TerrainVariantMap[x][y] = (int)(Math.random()*4);
				if(Math.random() > 0.2){TerrainVariantMap[x][y]=0;}
				}//else{TerrainVariantMap[x][y]=0;}
				}
				//animate lava
				if((map[myshareddata.TERRAIN_LAYER_LOWER][y][x]==8 && map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]==0) || map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]==8){
					if(Math.random() > 0.8){
					TerrainVariantMap[x][y] = (int)(Math.random()*4);
					if(Math.random() > 0.2){TerrainVariantMap[x][y]=0;}
					}//else{TerrainVariantMap[x][y]=0;}
					}
				
			}
			}
		
		}
		
	
		
		if(toppaneloffset==0){
			GL11.glColor3d(1, 1, 1);}
		else{
			GL11.glColor3d(0.5, 0.5, 0.5);
		}
		

		//there will be a variation map that get created to decide where variants go!
		
		MasterTerrainSheet.bind();
		MasterTerrainSheet.begin();
		
		for (int y = 0; y < SCREEN_Y_TILES; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {

					int layer = myshareddata.TERRAIN_LAYER_LOWER;
					int type = map[layer][mapY][mapX];
					
					if(type>0){
					
						
					
					int tex_x=0;
					int tex_y=0;
					
					
					int tileset = (type-1);
					int subtile =  GetTerrainSubtile(mapX,mapY,layer);//this will be tricky to get! function goes here!
					
					
					DrawTerrainTile(tileset,subtile,mapX,mapY,x,y,layer);
					
					
						
					
					}
					

				}
			}
		}
		
		for (int y = 0; y < SCREEN_Y_TILES; y++) {// /draw higher terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {
					int layer = myshareddata.TERRAIN_LAYER_HIGHER;
					int type = map[layer][mapY][mapX];
					
					if(type>0){
					
					int tileset = (type-1);
					int subtile =  GetTerrainSubtile(mapX,mapY,layer);//this will be tricky to get! function goes here!
					
					
					DrawTerrainTile(tileset,subtile,mapX,mapY,x,y,layer);
					
					
						
					
					}
					

				}
			}
		}
		
		
		MasterTerrainSheet.end();
		
		
	
		
		
		
		
		glDisable(GL_TEXTURE_2D);
		  GL11.glBegin(GL11.GL_QUADS);
		  
		  
			
			// Shading system
			
			for (int y = 0; y < SCREEN_Y_TILES * 4; y++) {
				for (int x = 0; x < SCREEN_X_TILES * 4; x++) {
					// int mapX = x + cam.x * 4 - SCREEN_X_TILES * 2;
					// int mapY = y + cam.y * 4 - SCREEN_Y_TILES * 2;
					int mapX = x + (cam.x - SCREEN_X_TILES / 2) * 4
							- 2;
					int mapY = y + (cam.y - SCREEN_Y_TILES / 2) * 4;
					// System.out.print(mapX+":"+mapY+" ");
					if (mapX > -1 && mapX < SharedData.MAP_SIZE * 4 && mapY > -1
							&& mapY < SharedData.MAP_SIZE * 4) {


						int shixel_size = TILE_SIZE / 4;

						// set the color of the quad (R,G,B,A)
						float alpha = 1.0f - (float) lit[mapY][mapX];
						GL11.glColor4f(0.0f, 0.0f, 0.0f, alpha);

						// draw quad

						GL11.glVertex2f(x * shixel_size, y * shixel_size);
						GL11.glVertex2f((x * shixel_size) + shixel_size, y
								* shixel_size);
						GL11.glVertex2f((x * shixel_size) + shixel_size,
								(y * shixel_size) + shixel_size);
						GL11.glVertex2f(x * shixel_size, (y * shixel_size)
								+ shixel_size);

					}

				}
			}
		  
		  
		  
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		  
		  //draw collision boxes
		
		for (int y = 0; y < SCREEN_Y_TILES; y++) {
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {
		
				
		if(!terrain_IsHoverable(mapX,mapY)){
			
			
		GL11.glColor4f(1, 0, 0,0.3f);	

	   			GL11.glVertex2f(x*TILE_SIZE, y*TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE + TILE_SIZE, y*TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE + TILE_SIZE, y*TILE_SIZE+ TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE, y*TILE_SIZE+ TILE_SIZE);

	   		
			
		}else if(!terrain_IsWalkable(mapX,mapY)){
		  GL11.glColor4f(1, 0.5f, 0,0.3f);	
			  

				GL11.glVertex2f(x*TILE_SIZE, y*TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE + TILE_SIZE, y*TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE + TILE_SIZE, y*TILE_SIZE+ TILE_SIZE);

	   			GL11.glVertex2f(x*TILE_SIZE, y*TILE_SIZE+ TILE_SIZE);
			
			
			
		}
		
		
				}
			}
		}
		
		GL11.glEnd();
		glEnable(GL_TEXTURE_2D);
		
		
		
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		// draw small objects
				for (int y = 0; y < SCREEN_Y_TILES; y++) {
					for (int x = 0; x < SCREEN_X_TILES; x++) {
						int mapX = x + cam.x - SCREEN_X_TILES / 2;
						int mapY = y + cam.y - SCREEN_Y_TILES / 2;
						if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1
								&& mapY < SharedData.MAP_SIZE) {
							int layer = myshareddata.SMALLOBJECTS_LAYER;
							int type = map[layer][mapY][mapX];

							if (type > 0 && !myTypeDB[type].DoNotRender) {

								// if the object is animated...

								double brightness = lit[mapY * 4][mapX * 4];
								if (myTypeDB[type].isEverLit) {
									brightness = 1;
								}
								GL11.glColor3d(brightness, brightness, brightness);

								if (Object_Spritesheet[type] != null) {

									int num_frames = Object_Spritesheet[type].num_frames;
									int speed = Object_Spritesheet[type].speed;
									int frame = (animationframe / (animationframe / speed))
											% num_frames;

									Object_Spritesheet[type].image.bind();
									Object_Spritesheet[type].image.begin();

									Object_Spritesheet[type].image.draw_subimage(frame
											* Object_Spritesheet[type].frame_size, 0, x
											* TILE_SIZE
											+ SharedData.imageOffset_x[type], y
											* TILE_SIZE
											+ SharedData.imageOffset_y[type]
											- (Object_Spritesheet[type].frame_size)
											+ TILE_SIZE,
											Object_Spritesheet[type].frame_size,
											Object_Spritesheet[type].frame_size);

									Object_Spritesheet[type].image.end();

								} else if (myTypeDB[type].image != null) {

									myTypeDB[type].image.draw(x * TILE_SIZE
											+ SharedData.imageOffset_x[type], y
											* TILE_SIZE
											+ SharedData.imageOffset_y[type]
											- myTypeDB[type].image.getHeight()
											+ TILE_SIZE);

								}

							}
						}
					}
				}

				SmallObject50Sheet.bind();
				SmallObject50Sheet.begin();

				for (int y = 0; y < SCREEN_Y_TILES; y++) {
					for (int x = 0; x < SCREEN_X_TILES; x++) {
						int mapX = x + cam.x - SCREEN_X_TILES / 2;
						int mapY = y + cam.y - SCREEN_Y_TILES / 2;
						if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1
								&& mapY < SharedData.MAP_SIZE) {
							int layer = myshareddata.SMALLOBJECTS_LAYER;
							int type = map[layer][mapY][mapX];

							if (type > 0 && !myTypeDB[type].DoNotRender) {
								if (myTypeDB[type].image == null
										&& Object_Spritesheet[type] == null) {
									double brightness = lit[mapY * 4][mapX * 4];
									if (myTypeDB[type].isEverLit) {
										brightness = 1;
									}
									GL11.glColor3d(brightness, brightness, brightness);// special
																						// object
																						// shading

									int index = type - 100;

									int tex_x = (index % 20) * 50;
									int tex_y = (index / 20) * 50;

									SmallObject50Sheet.draw_subimage(tex_x, tex_y, x
											* TILE_SIZE
											+ SharedData.imageOffset_x[type], y
											* TILE_SIZE
											+ SharedData.imageOffset_y[type]
											- (50 /* height */) + TILE_SIZE, 50, 50);

								}
							}
						}

					}
				}

				SmallObject50Sheet.end();
	
		
		

		
		//Draw Player Layer
		
		GL11.glColor3f(1,1,1);

		for (int y = 0; y < SCREEN_Y_TILES; y++) {//this wraps around all player and largeobject stuff for better drawing!
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {
					int layer = myshareddata.PLAYER_LAYER;
					int type = map[layer][mapY][mapX];
					
					if (type > 0 && !myTypeDB[type].DoNotRender) {

						// if the object is animated...

						
						GL11.glColor3d(0.1f, 0.1f, 0.1f);

							myTypeDB[type].image.draw(x * TILE_SIZE
									+ SharedData.imageOffset_x[type], y
									* TILE_SIZE
									+ SharedData.imageOffset_y[type]
									- myTypeDB[type].image.getHeight()
									+ TILE_SIZE);
							
							
							GL11.glColor3d(1,1,1);
							Verdana_14.drawString(x * TILE_SIZE
									+ SharedData.imageOffset_x[type] + 10, y
									* TILE_SIZE
									+ SharedData.imageOffset_y[type]
									- (int) myTypeDB[type].image.getHeight()
									+ TILE_SIZE, "" + (type - SharedData.HERO_2 + 2));
						

					}
					
					
				}
			}
		//}
		
		
		
			// Draw Large Objects
						GL11.glColor3f(1.0f, 1.0f, 1.0f);

						// for (int y = 0; y < SCREEN_Y_TILES; y++) {
						for (int x = -1; x < SCREEN_X_TILES + 1; x++) {
							int mapX = x + cam.x - SCREEN_X_TILES / 2;
							int mapY = y + cam.y - SCREEN_Y_TILES / 2;
							if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1
									&& mapY < SharedData.MAP_SIZE) {
								int layer = myshareddata.LARGEOBJECTS_LAYER;
								int type = map[layer][mapY][mapX];

								if (type > 0 && !myTypeDB[type].DoNotRender) {

									// if the object is animated...

									double brightness = lit[mapY * 4][mapX * 4];
									if (myTypeDB[type].isEverLit) {
										brightness = 1;
									}
									GL11.glColor3d(brightness, brightness, brightness);

									if (Object_Spritesheet[type] != null) {

										int num_frames = Object_Spritesheet[type].num_frames;
										int speed = Object_Spritesheet[type].speed;
										int frame = (animationframe / speed) % num_frames;

										Object_Spritesheet[type].image.bind();
										Object_Spritesheet[type].image.begin();

										Object_Spritesheet[type].image.draw_subimage(frame
												* Object_Spritesheet[type].frame_size, 0, x
												* TILE_SIZE
												+ SharedData.imageOffset_x[type], y
												* TILE_SIZE
												+ SharedData.imageOffset_y[type]
												- (Object_Spritesheet[type].frame_size)
												+ TILE_SIZE,
												Object_Spritesheet[type].frame_size,
												Object_Spritesheet[type].frame_size);

										Object_Spritesheet[type].image.end();

									} else if (myTypeDB[type].image != null) {

										myTypeDB[type].image.draw(x * TILE_SIZE
												+ SharedData.imageOffset_x[type], y
												* TILE_SIZE
												+ SharedData.imageOffset_y[type]
												- myTypeDB[type].image.getHeight()
												+ TILE_SIZE);

									}
								}
							}
						}
						// }

						LargeObject50Sheet.bind();
						LargeObject50Sheet.begin();

						// for (int y = 0; y < SCREEN_Y_TILES; y++) {
						for (int x = -1; x < SCREEN_X_TILES + 1; x++) {
							int mapX = x + cam.x - SCREEN_X_TILES / 2;
							int mapY = y + cam.y - SCREEN_Y_TILES / 2;
							if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1
									&& mapY < SharedData.MAP_SIZE) {
								int layer = SharedData.LARGEOBJECTS_LAYER;
								int type = map[layer][mapY][mapX];

								if (type > 0 && type < 500 && !myTypeDB[type].DoNotRender) {
									if (myTypeDB[type].image == null
											&& Object_Spritesheet[type] == null) {
										double brightness = lit[mapY * 4][mapX * 4];
										if (myTypeDB[type].isEverLit) {
											brightness = 1;
										}
										GL11.glColor3d(brightness, brightness, brightness);// special
																							// object
																							// shading

										int index = type - 402;

										int tex_x = (index % 20) * 50;
										int tex_y = (index / 20) * 50;

										LargeObject50Sheet.draw_subimage(tex_x, tex_y, x
												* TILE_SIZE
												+ SharedData.imageOffset_x[type], y
												* TILE_SIZE
												+ SharedData.imageOffset_y[type] - (50)
												+ TILE_SIZE, 50, 50);

									}
								}
							}

						}

						LargeObject50Sheet.end();

						// }

						LargeObject100Sheet.bind();
						LargeObject100Sheet.begin();

						// for (int y = 0; y < SCREEN_Y_TILES; y++) {
						for (int x = -1; x < SCREEN_X_TILES + 1; x++) {
							int mapX = x + cam.x - SCREEN_X_TILES / 2;
							int mapY = y + cam.y - SCREEN_Y_TILES / 2;
							if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1
									&& mapY < SharedData.MAP_SIZE) {
								int layer = myshareddata.LARGEOBJECTS_LAYER;
								int type = map[layer][mapY][mapX];

								if (type >= 500 && type < 600
										&& !myTypeDB[type].DoNotRender) {
									if (myTypeDB[type].image == null
											&& Object_Spritesheet[type] == null) {
										double brightness = lit[mapY * 4][mapX * 4];
										if (myTypeDB[type].isEverLit) {
											brightness = 1;
										}
										GL11.glColor3d(brightness, brightness, brightness);// special
																							// object
																							// shading

										int index = type - 500;

										int tex_x = (index % 10) * 100;
										int tex_y = (index / 10) * 100;

										LargeObject100Sheet.draw_subimage(tex_x, tex_y, x
												* TILE_SIZE
												+ SharedData.imageOffset_x[type], y
												* TILE_SIZE
												+ SharedData.imageOffset_y[type] - (100)
												+ TILE_SIZE, 100, 100);

									}
								}
							}

						}

						LargeObject100Sheet.end();

						LargeObject200Sheet.bind();
						LargeObject200Sheet.begin();

						// for (int y = 0; y < SCREEN_Y_TILES; y++) {
						for (int x = -1; x < SCREEN_X_TILES + 1; x++) {
							int mapX = x + cam.x - SCREEN_X_TILES / 2;
							int mapY = y + cam.y - SCREEN_Y_TILES / 2;
							if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1
									&& mapY < myshareddata.MAP_SIZE) {
								int layer = myshareddata.LARGEOBJECTS_LAYER;
								int type = map[layer][mapY][mapX];

								if (type >= 600 && type < 700
										&& !myTypeDB[type].DoNotRender) {
									if (myTypeDB[type].image == null
											&& Object_Spritesheet[type] == null) {

										int index = type - 600;

										int tex_x = (index % 5) * 200;
										int tex_y = (index / 5) * 200;

										LargeObject200Sheet.draw_subimage(tex_x, tex_y, x
												* TILE_SIZE
												+ myshareddata.imageOffset_x[type], y
												* TILE_SIZE
												+ myshareddata.imageOffset_y[type] - (200)
												+ TILE_SIZE, 200, 200);

									}
								}
							}

						}

						LargeObject200Sheet.end();

					}
		
		
		
		
		RoofSheet.bind();
		RoofSheet.begin();
		
		
		
		for (int y = -5; y < SCREEN_Y_TILES + 3; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {

				
					if(define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX]){
								
						float alpha = (pythagorean(cam.x,cam.y,mapX,mapY))/15f;
						GL11.glColor4d(1, 1, 1,alpha);
										
					DrawRoofTile(mapX,mapY,x,y);
					
						
					
					}
					

				}
			}
		}
		
		RoofSheet.end();
		
		
		SeamlessRock.bind();
		SeamlessRock.begin();
		
		//Obscure dungeon tiles with repeating(should auto repeat thru opengl) seamless rock (animate at some point!)
		for (int y = 0; y < SCREEN_Y_TILES ; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {

				
					if(define_regions[SharedData.REGION_DUNGEON - 800][mapY][mapX]){
						float alpha = 1;
						
						alpha = (float) Math.pow((pythagorean(cam.x,cam.y,mapX,mapY))/10f,2);
						
						GL11.glColor4d(0.3f, 0.3f, 0.3f,alpha);
										
					int anim = seamlessrockframe;
					SeamlessRock.draw_subimage((mapX*TILE_SIZE - anim)%SeamlessRock.getWidth(), (mapY*TILE_SIZE + anim )%SeamlessRock.getHeight(), x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
						
					
					}
					

				}
			}
		}
		SeamlessRock.end();
		
	
		
		
		
		if(toppaneloffset!=0){
	if( toppaneloffset < toppaneloffsetmax ){toppaneloffset+=10;if(toppaneloffset>toppaneloffsetmax){toppaneloffset=toppaneloffsetmax;}}
	if( toppaneloffset < 0 ){toppaneloffset+=10;if(toppaneloffset>0){toppaneloffset=0;}}
		}

		
		/**Draw the top panel */
		GL11.glColor3d(1, 1, 1);
		Fill_Rect(200,0,SCREEN_WIDTH - 200,20+(Math.abs(toppaneloffset)));
		
		
			
		GL11.glColor3d(0, 0, 0);
		if(toppaneloffset==0){
			
			
			Verdana_12.drawString(20 + 200 , 2+ (Math.abs(toppaneloffset)), "Options Menu" );
			Verdana_12.drawString((SCREEN_WIDTH-200)/2 - 40 + 200 , 2+ (Math.abs(toppaneloffset)), layernames[current_paintbrush_layer]);
			
		}else{

		
			
			Outline_Rect(220 - 5,  2+ (Math.abs(toppaneloffset)) - 90 - 2, 70, 18);
			Verdana_14.drawString(20 + 200 , 2+ (Math.abs(toppaneloffset)) - 90, "Load Map" );
			Outline_Rect(220 - 5,  2+ (Math.abs(toppaneloffset)) - 60 - 2, 70, 18);
			Verdana_14.drawString(20 + 200 , 2+ (Math.abs(toppaneloffset)) - 60, "Save Map" );
			Outline_Rect(220 - 5,  2+ (Math.abs(toppaneloffset)) - 30 - 2, 90, 18);
			Verdana_14.drawString(20 + 200 , 2+ (Math.abs(toppaneloffset)) - 30, "Map Options" );
			

			for(int i=0;i<6;i++){
				GL11.glColor3d(0.5, 0.5, 0.5);
				if(i==current_paintbrush_layer){GL11.glColor3d(0, 0, 0);}
			
			Verdana_12.drawString((SCREEN_WIDTH-200)/2 - 40 + 200 , 2+ (Math.abs(toppaneloffset)) - 20*i - 0,layernames[i]);
			
		}
			
			
			GL11.glColor3d(1, 1, 1);
			
			if(currenttilebrush ==0){eraserglowicon.draw(SCREEN_WIDTH - 60, 20+ (Math.abs(toppaneloffset)) - 50);}else{
				erasericon.draw(SCREEN_WIDTH - 60, 20+ (Math.abs(toppaneloffset)) - 50);
				}
		}
		
		/*
		GL11.glColor3d(0.5, 0.5, 0.5);
		for(int i=0;i<5;i++){
			
			int j = i+current_paintbrush_layer+1;
			if(j > 5){j-=6;};
			
			Verdana_12.drawString((SCREEN_WIDTH-200)/2 - 40 + 200 , 2+ toppaneloffset - 20*i - 20,layernames[j]);
			
		}*/
		
		
		/**Draw the side panel */
	
		
		if(toppaneloffset==0){
			GL11.glColor3d(1, 1, 1);}
		else{
			GL11.glColor3d(0.5, 0.5, 0.5);
		}
		
		Fill_Rect(0,0,200,SCREEN_HEIGHT);
		
		//draw icons
		
	
		if(current_paintbrush_layer != myshareddata.REGION_LAYER){
			
			int number_of_rows = 10;
			number_of_rows = SCREEN_HEIGHT - 250 / 50;
		
			for(int i=0;i<4*number_of_rows;i++){
			
			int x = 10 + (i%4)*45;
			int y = 200 + 10 + (i/4)*45;
			
			int spot = i;
			
			int type = 0;
			
			if(layer_brushes_found[current_paintbrush_layer].length > spot + brush_wheel_offset*4){				
			type = layer_brushes_found[current_paintbrush_layer][spot + brush_wheel_offset*4];
			}
			if(type>0){debug(type);
			 DrawBrushIcon(type,x,y);
			}
		}
	
		}else{			
			for(int i=0;i<regionOptions.length;i++){
				
				GL11.glColor3d(0.5f,0.5f,0.5f);
			if(currenttilebrush - 800 == i){
				GL11.glColor3d(0.8f,0.8f,0.8f);
				Fill_Rect(10, 200+10+i*20,170,18);	
				GL11.glColor3d(0,0,0);}			
			Outline_Rect(10, 200+10+i*20,170,18);	
				
			GL11.glColor3d(0,0,0);
			int pixellength = Verdana_14.getStringPixelLength(regionOptions[i]);
			Verdana_14.drawString(10 + 80 - pixellength/2, 200+10+i*20, regionOptions[i]);
			
		
			
			}
		}
		
		GL11.glColor3d(1,1,1);
		
		//currenttilebrush =0;
		
		
		
		
		//draw minimap
		GL11.glDisable(GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_QUADS);
		/**Draw the minimap */
		for(int x=0;x<myshareddata.MAP_SIZE;x++){
		for(int y=0;y<myshareddata.MAP_SIZE;y++){
			
			float r=0;
			float g=0;
			float b=0;
			//setTypeColors
			if(map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]>0 ){
				r = typeColors[map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]].getRed();
				g = typeColors[map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]].getGreen();
				b = typeColors[map[myshareddata.TERRAIN_LAYER_HIGHER][y][x]].getBlue();
			}else if(map[myshareddata.TERRAIN_LAYER_LOWER][y][x]>0 ){
				r = typeColors[map[myshareddata.TERRAIN_LAYER_LOWER][y][x]].getRed();
				g = typeColors[map[myshareddata.TERRAIN_LAYER_LOWER][y][x]].getGreen();
				b = typeColors[map[myshareddata.TERRAIN_LAYER_LOWER][y][x]].getBlue();
			}
			
			
			
			GL11.glColor3d(r/255, g/255, b/255);
			
			

			GL11.glVertex2f(x*2, y*2);

			GL11.glVertex2f(x*2 + 2, y*2);

			GL11.glVertex2f(x*2 + 2, y*2 + 2);

			GL11.glVertex2f(x*2, y*2 + 2);

			
		}
		}
		
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);

		GL11.glBegin(GL_LINES); // begin lines
		// minimap box
		
		int mm_center_x = 0 + (cam.x * 2);
		int mm_center_y = 0 + (cam.y * 2);
		GL11.glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y
				- (SCREEN_Y_TILES));
		glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y
				- (SCREEN_Y_TILES));
		GL11.glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y
				- (SCREEN_Y_TILES));
		glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y
				+ (SCREEN_Y_TILES));
		GL11.glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y
				+ (SCREEN_Y_TILES));
		glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y
				+ (SCREEN_Y_TILES));
		GL11.glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y
				+ (SCREEN_Y_TILES));
		glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y
				- (SCREEN_Y_TILES));

		
		
		GL11.glEnd();
		
		
		GL11.glColor3d(1, 1, 1);
		//GL11.glColor4f(0.5f, 0.5f, 0.8f,0.5f);	
		if(drag_square_x >-1 && drag_square_y > -1){
			
				
         		int low_x;
         		int high_x;
         		int low_y; 
         		int high_y; 
         		
         		int drag_proj_x = drag_square_x*TILE_SIZE - cam.x * TILE_SIZE
						+ (SCREEN_X_TILES * TILE_SIZE / 2);
         		int drag_proj_y = drag_square_y*TILE_SIZE - cam.y * TILE_SIZE
						+ (SCREEN_Y_TILES * TILE_SIZE / 2);
         		
         	
         		
         if(cursor.x < drag_proj_x){low_x = cursor.x;high_x=drag_proj_x;}else{low_x = drag_proj_x;high_x = cursor.x;}
         if(cursor.y < drag_proj_y){low_y = cursor.y;high_y=drag_proj_y;}else{low_y = drag_proj_y;high_y = cursor.y;}
 	 
       if(high_x/TILE_SIZE == low_x/TILE_SIZE && high_y/TILE_SIZE == low_y/TILE_SIZE){
        
    	   low_x = (low_x / TILE_SIZE)*TILE_SIZE;
    	   high_x = low_x + TILE_SIZE;
    	   low_y = (low_y / TILE_SIZE)*TILE_SIZE;
    	   high_y = low_y+ TILE_SIZE;
    	   System.out.println(low_x);
    	   GL11.glBegin(GL11.GL_LINES);
           GL11.glColor3f(1, 1, 1);	

   			GL11.glVertex2f(low_x, low_y);
   			GL11.glVertex2f(high_x, low_y);
   			
   			GL11.glVertex2f(high_x, low_y);
   			GL11.glVertex2f(high_x, high_y);

   			GL11.glVertex2f(high_x, high_y);
   			GL11.glVertex2f(low_x, high_y);
   			
   			GL11.glVertex2f(low_x, high_y);
   			GL11.glVertex2f(low_x, low_y);
   			
   		GL11.glEnd();
    	   
 		
       }else{
    	   
    	   
    	   GL11.glBegin(GL11.GL_QUADS);
           GL11.glColor4f(1, 1, 1,0.2f);	

   			GL11.glVertex2f(low_x, low_y);

   			GL11.glVertex2f(high_x, low_y);

   			GL11.glVertex2f(high_x, high_y);

   			GL11.glVertex2f(low_x, high_y);

   		GL11.glEnd();
   		
   		
       }
			
		}
		
		//GL11.glEnable(GL_TEXTURE_2D);
		
	}//end editor rendering
	
	
	void DrawBrushIcon(int type, int x, int y){
		
	
		
		if(type>0){
		
		if(current_paintbrush_layer == myshareddata.TERRAIN_LAYER_LOWER || current_paintbrush_layer == myshareddata.TERRAIN_LAYER_HIGHER){
			
		
				
			MasterTerrainSheet.bind();
			MasterTerrainSheet.begin();
			
			
		
			int tileset = (type-1);
			int subtile =  10;
			
			if(type==6){subtile=3;}//show snow differently			
			if(type==12){subtile=15;}//show snow differently
			
			//now do the real thing
			//broad tuning
			int tex_x=(tileset%8)*120;
			int tex_y=(tileset/8)*240;
			
			//fine tuning
			tex_x += 40*(subtile%3); 
			tex_y += 40*(subtile/3);
			
			MasterTerrainSheet.draw_subimage(tex_x,tex_y,x , y ,TILE_SIZE,TILE_SIZE);
				
			MasterTerrainSheet.end();
			GL11.glEnd();
			
			
		}
		
		
		
		
		if(current_paintbrush_layer == myshareddata.SMALLOBJECTS_LAYER ){
			
			if(myTypeDB[type].spritesheet==0){
				
				if(myTypeDB[type].image!=null){
					//this is 'fit to forty'
					myTypeDB[type].image.draw_FitToSize(x,y,40);
				
				
				
				}
			}
			
			if(myTypeDB[type].spritesheet==1){
				
				SmallObject50Sheet.bind();
				SmallObject50Sheet.begin();
				
			
				
				int subtile =  myTypeDB[type].sheet_index;
				
				//now do the real thing
				//broad tuning
				int tex_x=(subtile%20)*50;
				int tex_y=(subtile/20)*50;
				
				//fine tuning
				//tex_x += 40*(subtile%3); 
				//tex_y += 40*(subtile/3);
				
				SmallObject50Sheet.draw_subimage(tex_x,tex_y,x , y ,50,50);
					
				SmallObject50Sheet.end();
				GL11.glEnd();
				
				
				
			}
		
			
			
		}
		
		
		if(current_paintbrush_layer == myshareddata.PLAYER_LAYER ){
			
			if(myTypeDB[type].spritesheet==0){
				
				if(myTypeDB[type].image!=null){
				
				
				
				if(type>=myshareddata.HERO_2 && type<=myshareddata.HERO_8){
					GL11.glColor3f(0,0,0);
					//this is 'fit to forty'
					myTypeDB[type].image.draw_FitToSize(x,y,40);
					GL11.glColor3f(1,1,1);
					Verdana_16.drawString(x+12,y+20,"P "+(type - myshareddata.HERO_2 + 2));
				}else{
					
					//this is 'fit to forty'
					myTypeDB[type].image.draw_FitToSize(x,y,40);
				}
				
				}
			}
			
			
			/*if(myTypeDB[type].spritesheet==1){
				
				SmallObject50Sheet.bind();
				SmallObject50Sheet.begin();
				
			
				
				int subtile =  myTypeDB[type].sheet_index;
				
				//now do the real thing
				//broad tuning
				int tex_x=(subtile%20)*50;
				int tex_y=(subtile/20)*50;
				
				//fine tuning
				//tex_x += 40*(subtile%3); 
				//tex_y += 40*(subtile/3);
				
				SmallObject50Sheet.draw_subimage(tex_x,tex_y,x , y ,50,50);
					
				SmallObject50Sheet.end();
				GL11.glEnd();
				
				return true;
				
			}
		*/
			
			
		}
		
		
if(current_paintbrush_layer == myshareddata.LARGEOBJECTS_LAYER ){
			
	if(myTypeDB[type].spritesheet==0){
		
		if(myTypeDB[type].image!=null){
			//this is 'fit to forty'
			myTypeDB[type].image.draw_FitToSize(x,y,40);
		
		}
	}
			
			
			if(myTypeDB[type].spritesheet==2){
				
				LargeObject50Sheet.bind();
				LargeObject50Sheet.begin();
				
			
				
				int subtile =  myTypeDB[type].sheet_index;
				
				//now do the real thing
				//broad tuning
				int tex_x=(subtile%20)*50;
				int tex_y=(subtile/20)*50;
				
				//fine tuning
				//tex_x += 40*(subtile%3); 
				//tex_y += 40*(subtile/3);
				
				LargeObject50Sheet.draw_subimage_stretch(tex_x,tex_y,x , y ,50,50,40,40);
					
				LargeObject50Sheet.end();
				GL11.glEnd();
				
				
				
			}
		
			
			
			
				if(myTypeDB[type].spritesheet==3){
				LargeObject100Sheet.bind();
				LargeObject100Sheet.begin();
				
			
				
				int subtile =  myTypeDB[type].sheet_index;
				
				//now do the real thing
				//broad tuning
				int tex_x=(subtile%10)*100;
				int tex_y=(subtile/10)*100;
				
				//fine tuning
				//tex_x += 40*(subtile%3); 
				//tex_y += 40*(subtile/3);
				
				LargeObject100Sheet.draw_subimage_stretch(tex_x,tex_y,x , y ,100,100,40,40);
					
				LargeObject100Sheet.end();
				GL11.glEnd();
				
				
				
			}
				
				
				
				if(myTypeDB[type].spritesheet==4){
				LargeObject200Sheet.bind();
				LargeObject200Sheet.begin();
				
			
				
				int subtile =  myTypeDB[type].sheet_index;
				
				//now do the real thing
				//broad tuning
				int tex_x=(subtile%5)*200;
				int tex_y=(subtile/5)*200;
				
				//fine tuning
				//tex_x += 40*(subtile%3); 
				//tex_y += 40*(subtile/3);
				
				LargeObject200Sheet.draw_subimage_stretch(tex_x,tex_y,x , y ,200,200,40,40);
					
				LargeObject200Sheet.end();
				GL11.glEnd();
				
				
				
			}
			
		}
	
		
		
		
		
		
		}
	}
	
	
	//int[] currently_drawn_brushicons = new int[40];
	int[][] layer_brushes_found = new int[6][500];
	int[] number_of_layer_brushes_found = new int[6];
	
	
	

	void DrawTerrainTile(int tileset,int subtile, int mapX, int mapY, int x, int y, int layer){
		
		if(subtile!=10 && subtile!=15 && subtile!=16 && subtile!=17){
		int tileset_2=0;
		
		
		
		
		
		if(layer == myshareddata.TERRAIN_LAYER_LOWER){
			
			
		tileset_2 = 0;
		
		//cardinals
		if(subtile == 7 && mapY > 0){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY-1][mapX];}
		if(subtile == 9 && mapX > 0){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX-1];}
		if(subtile == 11 && mapX < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX+1];}
		if(subtile == 13 && mapY < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY+1][mapX];}
		
		//diags
		if(subtile == 4 || subtile == 8 ){
			if(mapY > 0 && mapX < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY-1][mapX+1];}
			if(mapY > 0 && !(mapX < SharedData.MAP_SIZE - 1)){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY-1][mapX];}
			if(!(mapY > 0) && mapX < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX+1];}
		}
		
		
		if(subtile == 5 || subtile == 6){
			if( mapY > 0 && mapX > 0){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY-1][mapX-1];}
			if( !(mapY > 0) && mapX > 0){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX-1];}
			if( mapY > 0 && !(mapX > 0)){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY-1][mapX];}
		}
		
		
		if(subtile == 2 || subtile == 12){
			if( mapX > 0 && mapY < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY+1][mapX-1];}
			if( !(mapX > 0) && mapY < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY+1][mapX];}
			if( mapX > 0 && !(mapY < SharedData.MAP_SIZE - 1)){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX-1];}
		}
		
		
		if(subtile == 1 || subtile == 14){ 
			if( mapX < SharedData.MAP_SIZE - 1 && mapY < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY+1][mapX+1];}
			if( !(mapX < SharedData.MAP_SIZE - 1) && mapY < SharedData.MAP_SIZE - 1){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY+1][mapX];}
			if( mapX < SharedData.MAP_SIZE - 1 && !(mapY < SharedData.MAP_SIZE - 1)){tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX+1];}
			}
		
		
		
		}else{
		tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX];
		}
		
		if(tileset_2 > 0){
		tileset_2=tileset_2-1;
		final int subtile_2 =10;   

		//broad tuning
		int tex_x_2=(tileset_2%8)*120;
		int tex_y_2=(tileset_2/8)*240;
		
		//fine tuning
		tex_x_2 += 40*(subtile_2%3); 
		tex_y_2 += 40*(subtile_2/3);
		
		//draw the blender underneath!
		MasterTerrainSheet.draw_subimage(tex_x_2,tex_y_2,x * TILE_SIZE, y * TILE_SIZE ,TILE_SIZE,TILE_SIZE);
		}
		
		
		}
		
		//now do the real thing
		//broad tuning
		int tex_x=(tileset%8)*120;
		int tex_y=(tileset/8)*240;
		
		//fine tuning
		tex_x += 40*(subtile%3); 
		tex_y += 40*(subtile/3);
		
			MasterTerrainSheet.draw_subimage(tex_x,tex_y,x * TILE_SIZE, y * TILE_SIZE ,TILE_SIZE,TILE_SIZE);
			
			
	}

	int GetTerrainSubtile(int mapX,int mapY,int layer){
		
		int subtile=0;
		
		//starts directly above and goes clockwise
		boolean[] same = new boolean[8];
		

		
		//GET SAMES
		if(mapY>=1){
	if(map[layer][mapY][mapX] == map[layer][mapY-1][mapX]){  same[0]=true;  }
		}
		if(mapY>=1 && mapX <= 98){
	if(map[layer][mapY][mapX] == map[layer][mapY-1][mapX+1]){  same[1]=true;  }
		}
		if(mapX <= 98){
	if(map[layer][mapY][mapX] == map[layer][mapY][mapX+1]){  same[2]=true;  }
		}
		if(mapY<=98 && mapX <= 98){
	if(map[layer][mapY][mapX] == map[layer][mapY+1][mapX+1]){  same[3]=true;  }
		}
		if(mapY<=98){
	if(map[layer][mapY][mapX] == map[layer][mapY+1][mapX]){  same[4]=true;  }
		}
		if(mapY<=98 && mapX >= 1 ){
	if(map[layer][mapY][mapX] == map[layer][mapY+1][mapX-1]){  same[5]=true;  }
		}
		if( mapX >= 1 ){
	if(map[layer][mapY][mapX] == map[layer][mapY][mapX-1]){  same[6]=true;  }
		}
		if( mapY >= 1 && mapX >= 1 ){
	if(map[layer][mapY][mapX] == map[layer][mapY-1][mapX-1]){  same[7]=true;  }
		}
		//DONE GETTING SAMES
		
		

		
		//add more here, take height precedence into effect!
		
		if(same[0]&&same[2]&&same[4]&&same[6]){
			
			subtile=10;
		
				//corners
				if(!same[1]){subtile=4;}
				if(!same[3]){subtile=1;}
				if(!same[5]){subtile=2;}
				if(!same[7]){subtile=5;}
				
		}
		
		
		//cardinals
		if(same[0]&&same[2]&&same[4]&&!same[6]){subtile=9;}
		if(same[0]&&same[4]&&same[6]&&!same[2]){subtile=11;}
		if(same[2]&&same[4]&&same[6]&&!same[0]){subtile=7;}
		if(same[2]&&same[0]&&same[6]&&!same[4]){subtile=13;}
		
		
		
		//diags
		if(same[2]&&same[4]&&!same[0]&&!same[6]&&same[3]){subtile=6;}
		if(same[4]&&same[6]&&!same[2]&&!same[0]&&same[5]){subtile=8;}
		if(same[0]&&same[2]&&!same[6]&&!same[4]&&same[1]){subtile=12;}
		if(same[0]&&same[6]&&!same[2]&&!same[4]&&same[7]){subtile=14;}
		
		
		
		
		if(map[layer][mapY][mapX]==1){subtile=10;}
		
	
		
		
		if(subtile==10 && TerrainVariantMap[mapX][mapY]>0){
			
			subtile+= 4 + TerrainVariantMap[mapX][mapY];
			
		}
		
		if(subtile==0 && TerrainVariantMap[mapX][mapY]>0){
			
			subtile=3;
			
		}
		
		return subtile;
	}

	
int GetRoofSubtile(int mapX,int mapY){
		
		int subtile=0;
		
		//starts directly above and goes clockwise
		boolean[] same = new boolean[8];
		

		
		//GET SAMES
		if(mapY>=1){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY-1][mapX]){  same[0]=true;  }
		}
		if(mapY>=1 && mapX <= 98){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY-1][mapX+1]){  same[1]=true;  }
		}
		if(mapX <= 98){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX+1]){  same[2]=true;  }
		}
		if(mapY<=98 && mapX <= 98){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY+1][mapX+1]){  same[3]=true;  }
		}
		if(mapY<=98){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY+1][mapX]){  same[4]=true;  }
		}
		if(mapY<=98 && mapX >= 1 ){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY+1][mapX-1]){  same[5]=true;  }
		}
		if( mapX >= 1 ){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX-1]){  same[6]=true;  }
		}
		if( mapY >= 1 && mapX >= 1 ){
	if(define_regions[SharedData.REGION_INDOORS - 800][mapY-1][mapX-1]){  same[7]=true;  }
		}
		//DONE GETTING SAMES
		
		
		//add more here, take height precedence into effect!
		
		if(same[0]&&same[2]&&same[4]&&same[6]){
			
			subtile=6;
		
				//inner corners
				if(!same[1]){subtile=1;}
				if(!same[3]){subtile=9;}
				if(!same[5]){subtile=8;}
				if(!same[7]){subtile=1;}
				
		}
		
		
		//cardinals
		if(same[0]&&same[2]&&same[4]&&!same[6]){subtile=5;}
		if(same[0]&&same[4]&&same[6]&&!same[2]){subtile=7;}
		if(same[2]&&same[4]&&same[6]&&!same[0]){subtile=1;}
		if(same[2]&&same[0]&&same[6]&&!same[4]){subtile=11;}
		
		
		
		//diags
		if(same[2]&&same[4]&&!same[0]&&!same[6]&&same[3]){subtile=0;}
		if(same[4]&&same[6]&&!same[2]&&!same[0]&&same[5]){subtile=2;}
		if(same[0]&&same[2]&&!same[6]&&!same[4]&&same[1]){subtile=10;}
		if(same[0]&&same[6]&&!same[2]&&!same[4]&&same[7]){subtile=12;}
		
		
		
	
		if (subtile == 6 && TerrainVariantMap[mapX][mapY] == 1) {subtile = 3;}
		if (subtile == 6 && TerrainVariantMap[mapX][mapY] == 2) {subtile = 4;}
		
		return subtile;
	}

	void DrawRoofTile(int mapX, int mapY, int x, int y){
		
		
		int subtile = GetRoofSubtile(mapX,mapY);
		

		int tex_x = 40*(subtile%5); 
		int tex_y = 40*(subtile/5);
		
	    RoofSheet.draw_subimage(tex_x,tex_y,x * TILE_SIZE, y * TILE_SIZE  - 80,TILE_SIZE,TILE_SIZE);
			
	}
	
	
	
	
	boolean map_beingDragged;
	int map_dragoffset_x;
	int map_dragoffset_y;
	int map_drag_original_x;
	int map_drag_original_y;
	
	boolean focused_GUI = false;
	
	void pollInput_Mouse(){
		
		
		
		/** MOUSE HOVERING */
		for (int i = 0; i < 5; i++) {
			displayedHovertooltip[i] = null;
		}

	
		int x1 = cursor.currentmousetile_x;
		int y1 = cursor.currentmousetile_y;
		
		

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
			
			//start drawing tiles
			
			
			if(cursor.x < 200 && cursor.y < 200 && drag_square_x < 0 && drag_square_y <0 && !map_beingDragged){
				
				cam.x = cursor.x/2;
				cam.y = cursor.y/2;
			}
			
			if(map_beingDragged){
				int offset_x = (map_dragoffset_x  - cursor.x)/40;
				int offset_y = (map_dragoffset_y  - cursor.y)/40;
				cam.x = map_drag_original_x + offset_x;
				cam.y = map_drag_original_y + offset_y;
			}

			
		
			
			
			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false
					&& Mouse_MouseAlreadyClicked[1] == false) {
				
				boolean clickedGUIElement = false;
				
				
				
				//side menu
				if(cursor.x < 200){
					
				/*	
				if(current_paintbrush_layer != myshareddata.REGION_LAYER){
					
					
					for(int spot=0;spot<40;spot++){
						
							int x = 10 + (spot%4)*45;
							int y = 200 + 10 + (spot/4)*45;
							if(cursor.x > x && cursor.x < x + 40 && cursor.y > y && cursor.y < y+40){
								
								int type = layer_brushes_found[current_paintbrush_layer][spot + brush_wheel_offset*4];
								
								if(type>0){
								currenttilebrush = type;
								}
										
								System.out.println("brush: " + currenttilebrush);
							}
							
						
					}
					
					
				*/
				if(current_paintbrush_layer != myshareddata.REGION_LAYER){
						
						int number_of_rows = 10;
						number_of_rows = SCREEN_HEIGHT - 250 / 50;
					
						for(int i=0;i<4*number_of_rows;i++){
						
						int x = 10 + (i%4)*45;
						int y = 200 + 10 + (i/4)*45;
						
						if(cursor.x > x && cursor.x < x + 40 && cursor.y > y && cursor.y < y+40){
						
						int spot = i;
						
						int type = 0;
						
						if(layer_brushes_found[current_paintbrush_layer].length > spot + brush_wheel_offset*4){				
						type = layer_brushes_found[current_paintbrush_layer][spot + brush_wheel_offset*4];
						}
						
						if(type>0){
							currenttilebrush = type;
							}									
							System.out.println("brush: " + currenttilebrush);
					
						}
						}
				
				}else{//region selection
					
					int type = 800 + ((cursor.y - 200 - 10)/20);
					
					currenttilebrush = type;
					debug(type);
				}	
					
					
					
					clickedGUIElement=true;
					
				}
				
				
				
				
				
				//bring down the topmenu
				if(cursor.x > 200 && cursor.y < 20 && toppaneloffset==0){
					toppaneloffset=1;
					clickedGUIElement=true; 
				}
			
				
				//click the topmenu
				if(toppaneloffset==toppaneloffsetmax && cursor.y < 20 + toppaneloffset && cursor.x > 200){
				for(int i=0;i<6;i++){

						if(cursor.x > (SCREEN_WIDTH-200)/2 - 40 + 200 && cursor.x < (SCREEN_WIDTH-200)/2 + 40 + 200 && cursor.y > 2+ toppaneloffset - 20*i  && cursor.y< 2+ toppaneloffset - 20*i +20 ){ChangeCurrentBrushLayer(i);}
					
				}
				
				if(cursor.x  > 220 - 5 && cursor.x < 220 - 5+70 && cursor.y >  toppaneloffset - 90 && cursor.y < toppaneloffset - 90 + 18 ){
					//load
					fcFrame.setVisible(true);
	       	           
		            if (fc.showOpenDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {
		              File file = fc.getSelectedFile();
		             loadMapfromFile(file.getPath());
		             
		         
		            	}
		            fcFrame.setVisible(false);
				}
				if(cursor.x  > 220 - 5 && cursor.x < 220 - 5+70 && cursor.y >  toppaneloffset - 60 && cursor.y < toppaneloffset - 60 + 18 ){
					//save
					 	fcFrame.setVisible(true);
		       	           
    		            if (fc.showSaveDialog(fcFrame) == JFileChooser.APPROVE_OPTION) {
    		              File file = fc.getSelectedFile();
    		              writeMaptoFile(file.getPath());
				}
    		            fcFrame.setVisible(false);
    		    }
				
				if(cursor.x  > 220 - 5 && cursor.x < 220 - 5+90 && cursor.y >  toppaneloffset - 30 && cursor.y < toppaneloffset - 30 + 18 ){
					//options
					openConfigMenu();
    		         
				}
				
				
			
				
				
				if(cursor.x > SCREEN_WIDTH - 60 && cursor.x < SCREEN_WIDTH - 60 + 40 && cursor.y > 20+ toppaneloffset - 50 && cursor.y < 20+ toppaneloffset - 50 + 40){
				currenttilebrush=0;
				//toppaneloffset=0;
				clickedGUIElement=true; 
				}
				
				
				}//end clicking the top menu!dw	

				
				//hide the topmenu
				if(toppaneloffset==toppaneloffsetmax						
						//&& (cursor.y > 20 + toppaneloffset || cursor.x < 200)
								){
					
					toppaneloffset=-1*toppaneloffsetmax;
					clickedGUIElement=true; 
				}
				
				
				//debug(clickedGUIElement);
				
					
					if( !clickedGUIElement && toppaneloffset==0){
					
					
						if(Mouse.isButtonDown(0) && cursor.WithinMapBounds(myshareddata.MAP_SIZE)){
					
							drag_square_x = cursor.currentmousetile_x;
							drag_square_y = cursor.currentmousetile_y;
							
         			
						}
         			
					
					if(Mouse.isButtonDown(1) ){
						//begin pulling the map around
						map_beingDragged = true;
						 map_dragoffset_x = cursor.x;
						map_dragoffset_y = cursor.y;
						 map_drag_original_x = cam.x;
						map_drag_original_y = cam.y;
					}
					
					
					}
					
					
					
				
				
		         
				
				
				
				
				
				
				
				
				
			}//end mousepress
		
		}//end mousedrag
		
		
		
		//mousewheel
		if(cursor.x  < 200){
			//int cursor.wheel_delta = Mouse.getDWheel();
			
			/** Scroll Wheel Upwards */
			if (cursor.wheel_delta > 50) {
				if(brush_wheel_offset > 0){
					brush_wheel_offset--;
					}
			}
			
			/** Scroll Wheel Downwards */
			if (cursor.wheel_delta < -50) {
				if(brush_wheel_offset < (number_of_layer_brushes_found[current_paintbrush_layer] - 40)/4  + 4){//change me!!!
				brush_wheel_offset++;
				}
			}
		
		
		
		
		}
		
		
		
		
		/** MOUSE RELEASE */
		if ((!Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == true)
				|| (!Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == true)) {

			
			if(drag_square_x >-1 && drag_square_y > -1){
				
				
	             		int low_x;
	             		int high_x;
	             		int low_y; 
	             		int high_y; 
	             if(cursor.currentmousetile_x < drag_square_x){low_x = cursor.currentmousetile_x;high_x=drag_square_x;}else{low_x = drag_square_x;high_x = cursor.currentmousetile_x;}
	             if(cursor.currentmousetile_y < drag_square_y){low_y = cursor.currentmousetile_y;high_y=drag_square_y;}else{low_y = drag_square_y;high_y = cursor.currentmousetile_y;}
         	 
	             
	            	 
	            	 for(int h = low_x;h<=high_x;h++){
         			 for(int k = low_y;k<=high_y;k++){
         				if(h>-1 && h<myshareddata.MAP_SIZE && k>-1 && k<myshareddata.MAP_SIZE){
         				 
         					paint_on_canvas(h,k);
         			 
         			 //paint_on_canvas(h,k);
         			// if(current_paintbrush_layer == myshareddata.REGION_LAYER && currenttilebrush==0){deleteNote(h,k);}
         			 
         				}
         			 
         		 }
         		 }
	            	 
				
	             
				
				
			}
			

			DMLighting();
			map_beingDragged = false;
			drag_square_x = -1;
 			drag_square_y = -1;
		}
		
		
	}
	
	
	void paint_on_canvas(int x, int y){
		if(currenttilebrush>=myshareddata.HERO_2 && currenttilebrush<=myshareddata.HERO_8){
			for(int h=0;h<myshareddata.MAP_SIZE;h++){
			for(int k=0;k<myshareddata.MAP_SIZE;k++){
				if(map[current_paintbrush_layer][k][h]==currenttilebrush){
					map[current_paintbrush_layer][k][h]=0;
				}
				
			}}
		}
		
		//log this for UNDO button!
		log_action(x,y,current_paintbrush_layer,currenttilebrush);
		
		if(current_paintbrush_layer != SharedData.REGION_LAYER){
		map[current_paintbrush_layer][y][x]=currenttilebrush;
		}else{
			
			
		if(currenttilebrush==SharedData.REGION_INDOORS){
			define_regions[SharedData.REGION_INDOORS - 800][y][x]=true;
		}
		if(currenttilebrush==SharedData.REGION_DUNGEON){
			define_regions[SharedData.REGION_DUNGEON - 800][y][x]=true;
			
		}
		
		//erase regions
		if(currenttilebrush==0){
			define_regions[SharedData.REGION_INDOORS - 800][y][x]=false;
			define_regions[SharedData.REGION_DUNGEON - 800][y][x]=false;
		}
		
		}
		
	}
	
	void log_action(int x, int y, int layer, int type){
		
		for(int i=999;i>0;i--){
			
			myLoggedActions[i] = myLoggedActions[i-1];
		}
		
		myLoggedActions[0].LogAction(x, y, layer, type);
		
	}
	
	
	
	void pollInput_Keyboard(){
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && cam.y>0 ){
		cam.y--;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && cam.x>0){
			cam.x--;
			}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && cam.y<myshareddata.MAP_SIZE){
			cam.y++;
			}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && cam.x<myshareddata.MAP_SIZE){
			cam.x++;
			}
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_1)){
			ChangeCurrentBrushLayer(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_2)){
			ChangeCurrentBrushLayer(1);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_3)){
			ChangeCurrentBrushLayer(2);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_4)){
			 ChangeCurrentBrushLayer(3);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_5)){
			 ChangeCurrentBrushLayer(4);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_6)){
		    ChangeCurrentBrushLayer(5);
		}
		
		
	}
	
	
	void ChangeCurrentBrushLayer(int newlayer){
		
		brush_wheel_offset=0;
	    current_paintbrush_layer = newlayer;
	    currenttilebrush = 0;
		
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
	
	
	
	
	
	int HOUR_OF_DAY = 12;
	
	void calculateLightingEveryFrame(){//the only function that affects lit... period!!!
		
		// procedural lighting! Stored_Lit will have nothign procedural in it,
				// lit will be edited by procedurals
			
				for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {
						lit[y][x]=MINIMUM_LIT_OUTDOORS;
						
						
					//DM
						if(x < SharedData.MAP_SIZE*4  - 2){
							if(define_regions[SharedData.REGION_DUNGEON - 800][y/4][(x+2)/4] ){lit[y][x]=MINIMUM_LIT_DUNGEON;}
							if(define_regions[SharedData.REGION_INDOORS - 800][y/4][(x+2)/4] ){lit[y][x]=MINIMUM_LIT_INDOORS;}
						}
							
							if(HOUR_OF_DAY < 6 || HOUR_OF_DAY > 18){
								lit[y][x] = lit[y][x] / 2;
							}
						

						
							if(stored_lit[y][x] > 0){
							lit[y][x] += stored_lit[y][x];
							}
						
					
							
							
							if(lit[y][x] > 1){
								lit[y][x]=1;
							}
							
							
					}
				}
		
		}
		


double torchtilebrightness;
double[][] stored_lit = new double[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];
boolean pixelsToBeDeLit[][] = new boolean[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];

	private void DMLighting() {
		// DM Lighting and stuff (no de-lighting)

		debug("Host Lighting");
		
			

		 
    
	
	for (int y = 0; y < SharedData.MAP_SIZE*4;y++) {   
		for (int x = 0; x < SharedData.MAP_SIZE*4;x++) {
			
			
			alltorchlit[y][x] = 0;
			pixelsToBeDeLit[y][x] = false;
			
			 stored_lit[y][x]=0;
			
	
		}}

 	
		
		
		

		
		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				//int UnitID = UnitMap[x][y];


				if(map[myshareddata.SMALLOBJECTS_LAYER][y][x] > 0){
					if(myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]] > 0){
						
						generatetorchlight(x, y,  myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]] , 0);
						
					}
					
				}
				
				if(map[myshareddata.LARGEOBJECTS_LAYER][y][x] > 0){
					if(myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]] > 0){
						
						generatetorchlight(x, y,  myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]] , 0);
						
					}
					
				}
				
				
				if (map[myshareddata.PLAYER_LAYER][y][x] > 0) {
					
					
					generatetorchlight(x, y, 10,0);
				}

			}
		}

		

		for (int y = 0; y < SharedData.MAP_SIZE*4;y++) {   
			for (int x = 0; x < SharedData.MAP_SIZE*4;x++) {			
				
				if(alltorchlit[y][x] > 0){		
					stored_lit[y][x] += alltorchlit[y][x];
					if(stored_lit[y][x]  > 1 ){ stored_lit[y][x] = 1;}
				
				}
				}	
			}
		
		

	}
	
	
	private void generatetorchlight(int torch_x, int torch_y, int brightness, int facing) { 
		int cone_direction=-1;
		boolean conic_light = false;
		if(facing > 0){conic_light = true;cone_direction = facing -1;}

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {
				pixelsToBeDeLit[y][x] = false;

			}
		}
		
		
		
	
		

		for (int a = (torch_y * 4) - 41; a < (torch_y * 4) + 41; a++) {
			for (int b = (torch_x * 4) - 41; b < (torch_x * 4) + 41; b++) {
				if (a > -1 && a < SharedData.MAP_SIZE * 4 && b > -1 && b < SharedData.MAP_SIZE * 4) {

					double tiledist_fromtorch = 0;
					double torchtilebrightness = 0;
					
					/*tiledist_fromtorch = Math.sqrt(Math.abs(Math.pow(a
							- (torch_y * 4), 2))
							+ Math.abs(Math.pow(b - (torch_x * 4), 2)));*/
					
					tiledist_fromtorch = pythagorean(torch_x*4,torch_y*4,b,a);
					
					int lightpixeldirection =  AngleBetweenCoordinates(torch_x*4,torch_y*4,b,a);
					


					
					if(!conic_light){//light goes all the way around
					torchtilebrightness = (0.06 * brightness) - 0.05* tiledist_fromtorch;
					
					}else if(cone_direction==lightpixeldirection){//conic lightning
						torchtilebrightness = (0.06 * brightness) - 0.05 *tiledist_fromtorch;	
						
					}
					/*if(lightpixeldirection==1){
					torchtilebrightness=1;}
					*/
					
					

					if (torchtilebrightness > 1) {
						torchtilebrightness = 1;
					}
					if (torchtilebrightness < 0) {
						torchtilebrightness = 0;
					}

					if (object_blockslight(b / 4, a / 4) == true) {
						int offset_h_y = 0;
						int offset_h_x = 0;
						int offset_v_y = 0;
						int offset_v_x = 0;

						
					
						if (map[myshareddata.LARGEOBJECTS_LAYER][a / 4][b / 4] >= myshareddata.PILLAR_1
								&& map[myshareddata.LARGEOBJECTS_LAYER][a / 4][b / 4] <= myshareddata.PILLAR_1) {
							if (torch_x > b / 4) {
								offset_h_x = 2;
							} else if (torch_x < b / 4) {
								offset_h_x = -2;
							}
						}




						if (torch_y > a / 4) {
							for (int new_y = a / 4 - 8; new_y < a / 4; new_y++) {
								for (int j = 0; j < 4; j++) {
									if ((new_y * 4) + j + offset_v_y > -1
											&& (new_y * 4) + j + offset_v_y < SharedData.MAP_SIZE * 4
											&& b - 2 + offset_v_x > -1
											&& b - 2 + offset_v_x < SharedData.MAP_SIZE * 4) {
										pixelsToBeDeLit[(new_y * 4) + j
												+ offset_v_y][b - 2
												+ offset_v_x] = true;
									}
								}
							}
						} // flag tile above wall for delighting
						if (torch_y < a / 4) {
							for (int new_y = a / 4 + 8; new_y > a / 4; new_y--) {
								for (int j = 0; j < 4; j++) {
									if ((new_y * 4) + j + offset_v_y > -1
											&& (new_y * 4) + j + offset_v_y < SharedData.MAP_SIZE * 4
											&& b - 2 + offset_v_x > -1
											&& b - 2 + offset_v_x < SharedData.MAP_SIZE * 4) {
										pixelsToBeDeLit[(new_y * 4) + j
												+ offset_v_y][b - 2
												+ offset_v_x] = true;
									}
								}
							}
						}// flag all tiles below wall for delighting
						if (torch_x > b / 4) {
							for (int new_x = b / 4 - 8; new_x < b / 4; new_x++) {
								for (int j = 0; j < 4; j++) {
									if (a + offset_h_y > -1
											&& a + offset_h_y < SharedData.MAP_SIZE * 4
											&& (new_x * 4) - 2 + j + offset_h_x > -1
											&& (new_x * 4) - 2 + j + offset_h_x < SharedData.MAP_SIZE * 4) {
										pixelsToBeDeLit[a + offset_h_y][(new_x * 4)
												- 2 + j + offset_h_x] = true;
									}
								}
							}
						} // /flag all tiles left of wall for delighting
						if (torch_x < b / 4) {
							for (int new_x = b / 4 + 8; new_x > b / 4; new_x--) {
								for (int j = 0; j < 4; j++) {
									if (a + offset_h_y > -1
											&& a + offset_h_y < SharedData.MAP_SIZE * 4
											&& (new_x * 4) - 2 + j + offset_h_x > -1
											&& (new_x * 4) - 2 + j + offset_h_x < SharedData.MAP_SIZE * 4) {
										pixelsToBeDeLit[a + offset_h_y][(new_x * 4)
												- 2 + j + offset_h_x] = true;
									}
								}
							}
						}// flag all tiles below wall for delighting

						// this is making delit lines that can cover up other
						// torches' lit areas
					}

					if (torchtilebrightness > 1) {
						torchtilebrightness = 1;
					}
					if (torchtilebrightness < 0) {
						torchtilebrightness = 0;
					}

					
					if(!pixelsToBeDeLit[a][b]){
						alltorchlit[a][b] += torchtilebrightness;	
					}
					
					
					
					if(alltorchlit[a][b]>1){alltorchlit[a][b]=1;}
					if(alltorchlit[a][b]<0){alltorchlit[a][b]=0;}
				
				}
			}
			
		}

	}
	
	
	
	
	String[] Split_String_To_TooltipLines(String s){
		
		String[] finishedtooltips = new String[5];
			  int[] lineendpoints = new int[6];
			int s_length = s.length();
			
			  
			if(s_length < tooltiplinelength || s.lastIndexOf(' ') ==-1 || s.lastIndexOf(' ') < tooltiplinelength){finishedtooltips[0]=s;}else{
			
			
			String temp_piece = s.substring(0,tooltiplinelength);//take the first line
		
			
			if(temp_piece.lastIndexOf(' ') != -1){
			lineendpoints[0] = temp_piece.lastIndexOf(' ');     
			}else{lineendpoints[0]=tooltiplinelength;}
						
			
			//the above is fine -----
			
			
			int mid_lines=5;
			boolean end_of_s_reached=false;
			
			
			
			
			for(int i=1;i<mid_lines;i++){
				
				
				
			 if(lineendpoints[i-1]+tooltiplinelength > s_length){mid_lines=i;lineendpoints[i]=s_length; end_of_s_reached=true;   }	
	        
			 
	    if(end_of_s_reached==false){
		 temp_piece = s.substring(lineendpoints[i-1],lineendpoints[i-1]+tooltiplinelength);	
	     
		 lineendpoints[i] = temp_piece.lastIndexOf(' ');
	     lineendpoints[i] += lineendpoints[i-1];
	      }
			
			
						
			}//end 1-5 loop
	 
		    System.out.println(s_length);
			System.out.println("MIDLINES:"+mid_lines);
			//for(int i=0;i<6;i++){System.out.println(i+";"+lineendpoints[i]);}
			
			
			finishedtooltips[0] = s.substring(0,lineendpoints[0]);
			for(int i=1;i<=mid_lines;i++){
				
				 if(i !=5){ finishedtooltips[i] = s.substring(lineendpoints[i-1],lineendpoints[i]); }
				
				
			}
		
		 
			}//end check for small string
			
			
			return finishedtooltips;
			
	}

	
	
	
	
	void setTypeColors(int type, int r, int g, int b){
		
	for(int i=0;i<16;i++){
		typeColors[type+i] = new Color(r+i, g+i, b+i);
		}	
	
	}
 
	

		   private void writeMaptoFile(String sFileName){
			    try {
						
				    	
						if(sFileName.substring(sFileName.length()-4,sFileName.length()).equals(".vmm")){}else{sFileName += (".vmm");}


							FileWriter writer = new FileWriter(sFileName);
							
							
							writer.append(mapName + "\n");
							writer.append(mapDescrip + "\n");
							//writer.append("Objectives:" + num_of_objectives);
						
						
						 for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
								for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
									for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

										String s = ""+map[z][y][x];
										
										if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
										
										
										writer.append(s + "\n");
						   
									}}}
						 
						 
						
						 
						 for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
								for (int x = 0; x < myshareddata.MAP_SIZE; x++) {						 
						
						 int i = 0;
						 if(define_regions[SharedData.REGION_INDOORS - 800][y][x]) {i=1;}
						 
						 writer.append(i + "\n");
						 
								}}
						 
						 
						 
						 for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
								for (int x = 0; x < myshareddata.MAP_SIZE; x++) {						 
						
						 int i = 0;
						 if(define_regions[SharedData.REGION_DUNGEON - 800][y][x]) {i=1;}
						 
						 writer.append(i + "\n");
						 
								}}
						 
						 
						// append notes
						 
						 /*
						int number_of_notes = 0;
						for(int x=0;x<myshareddata.MAP_SIZE;x++){
							for(int y=0;y<myshareddata.MAP_SIZE;y++){
								
								if(NoteMessages[x][y]!=null && map[myshareddata.REGION_LAYER][y][x] >0){number_of_notes++;}
								
							}
						}
						 
						 
						 writer.append("NumberofNoteStrings:" + number_of_notes + "\n");  //tells the file loader how many lines of note strings are coming up
						 
						 
						 String s =null;
						 
							for(int x=0;x<myshareddata.MAP_SIZE;x++){
								for(int y=0;y<myshareddata.MAP_SIZE;y++){
									
									if(NoteMessages[x][y]!=null && map[myshareddata.REGION_LAYER][y][x] >0){

									
										s = ""+x;								
										if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
										 writer.append(s);
										 
											s = ""+y;								
											if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
											 writer.append(s);
											 
												s = ""+map[myshareddata.REGION_LAYER][y][x];								
												if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
												 writer.append(s);
												 
													s = ""+NoteMessages[x][y];								
													//if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
													 writer.append(s+ "\n");
									
									
									
									}
									
								}
							}*/
						 
						
						 
					/*	SortWarpRifts();
						 
						 writer.append("NumberofWarpRifts:" + num_of_warprifts + "\n");  //tells the file loader how many lines of note strings are coming up
					for(int i=0;i<num_of_warprifts;i++){
						
						s = ""+warprift[i][0];				
						if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
						 writer.append(s);
						 
							s = ""+warprift[i][1];									
							if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
							 writer.append(s);
							 
								s = ""+warprift[i][2];								
								if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
								 writer.append(s);
								 
									s = ""+warprift[i][3];									
									if(s.length()==1){s="00"+s;}else if(s.length()==2){s="0"+s;}
									 writer.append(s+ "\n");
						
						
					}*/
						
						
						 writer.flush();
						 writer.close();
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   
		    	 
			   
		   }
	   

	   private void loadMapfromFile(String sFileName)  
	   {
		
			
			
	  	    try
		{
			
			if(sFileName.substring(sFileName.length()-4,sFileName.length()).equals(".vmm")){}else{sFileName += (".vmm");}
			
			
				FileReader fr = new FileReader(sFileName); 
				BufferedReader br = new BufferedReader(fr); 
				
				
				mapName=br.readLine();
				mapDescrip=br.readLine();
								
				
				
				//load map
				 for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
						for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
							for (int x = 0; x < myshareddata.MAP_SIZE; x++) {


							map[z][y][x]=Integer.parseInt(br.readLine());
							
			   
						}}}
				 
				 
				 
				 
				 for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
						for (int x = 0; x < myshareddata.MAP_SIZE; x++) {
				 
					int i=Integer.parseInt(br.readLine());
					if(i==1){define_regions[SharedData.REGION_INDOORS - 800][y][x]=true;}else{define_regions[SharedData.REGION_INDOORS - 800][y][x]=false;}	
			
				 
						}}
				 
				 for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
						for (int x = 0; x < myshareddata.MAP_SIZE; x++) {
				 
					int i=Integer.parseInt(br.readLine());
					if(i==1){define_regions[SharedData.REGION_DUNGEON - 800][y][x]=true;}else{define_regions[SharedData.REGION_DUNGEON - 800][y][x]=false;}	
			
				 
						}}
				 
				
				 /*
				 //load notes
				 String line;
				 line =br.readLine();
				int number_of_notes=Integer.parseInt(line.substring(20));
				
				 for(int i=0;i<number_of_notes;i++){
				 
					
					
					String readnote = br.readLine();
					
					int x=Integer.parseInt(readnote.substring(0,3));
					int y=Integer.parseInt(readnote.substring(3,6));
					int type=Integer.parseInt(readnote.substring(6,9));
					String message=readnote.substring(9);
					
				//	createNewNote(x,y,type,message);
					
					
					
				 }
				 
				 
				 
				 //load warprifts
				 line =br.readLine();
				int num_of_warprifts=Integer.parseInt(line.substring(18));
				 System.out.println("found:"+num_of_warprifts);
               for(int i=0;i<num_of_warprifts;i++){
				 
					String readrift = br.readLine();
					
					int x=Integer.parseInt(readrift.substring(0,3));
					int y=Integer.parseInt(readrift.substring(3,6));
					int x2=Integer.parseInt(readrift.substring(6,9));
					int y2=Integer.parseInt(readrift.substring(9,12));
					
					createWarpRift(x,y,x2,y2);
					
					
					
				 }
				 
				 
				 
				 */
				
				fr.close(); 
			
	      
			
			
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	  	    
	  	    
	    	        
			
	    }
	   
	   
	   
		double animfactor = 100;
		int animationframe;
		
		int seamlessrockframe;

		void tileAnimationTimerTick() {

			animfactor = (delta * 100) / 15;

			if (animationframe >= 31) {
				animationframe = 0;
			} else {
				animationframe++;
			}
			if(animationframe%4 ==0){
			if (seamlessrockframe >= 1024) {
				seamlessrockframe = 0;
			} else {
				seamlessrockframe++;
			}
			}
		}
			
	   
		
	 void createWarpRift(int x1, int y1, int x2, int y2){//with recycling!
		 
		 int next_available_warpriftID = num_of_warprifts;
		 for(int i=0;i<num_of_warprifts;i++){
			 boolean blank = true;
			 for(int j=0;j<4;j++){
			 if(warprift[i][j]!=0){blank=false;}
			 }
			 if(blank){next_available_warpriftID=i;}
		 }
		 
		 warprift[next_available_warpriftID][0] = x1;
		 warprift[next_available_warpriftID][1] = y1;
		 warprift[next_available_warpriftID][2] = x2;
		 warprift[next_available_warpriftID][3] = y2;
		 
		 if(next_available_warpriftID==num_of_warprifts){
		 num_of_warprifts++;
		 }
		 System.out.println(num_of_warprifts);
	 }
	 
	 void deleteWarpRift(int x, int y){
		 
		 for(int i=0;i<num_of_warprifts;i++){
			 if((warprift[i][0]==x && warprift[i][1]==y) || (warprift[i][2]==x && warprift[i][3]==y)){
				 
				 warprift[i][0] = 0;
				 warprift[i][1] = 0;
				 warprift[i][2] = 0;
				 warprift[i][3] = 0;
				 
			 }
		 }
		 
		 	 
		 
	 }
	 
	 
	 void SortWarpRifts(){
		 
		 for(int i=0;i<num_of_warprifts;i++){
			 if((warprift[i][0]==0 && warprift[i][1]==0) && (warprift[i][2]==0 && warprift[i][3]==0)){
				 
				 
				 for(int j=(i+1);j<num_of_warprifts;j++){
					 warprift[j-1] = warprift[j];
				 }
				 
				 
				 num_of_warprifts--;
			 }
			 }
		 
		 
		 System.out.println("sort"+num_of_warprifts);
	 }
	   
	   
	/*void createNewNote(int x, int y, int type, String message){
       	     NoteMessages[x][y]= message;
				map[myshareddata.REGION_LAYER][y][x]=type;
	    		
	}
	
	void deleteNote(int x, int y){
		
		 NoteMessages[x][y]= null;
		 map[myshareddata.REGION_LAYER][y][x]=0;
		
         
	}*/
	
	
	
	void wipeMapClean(){
		
		for (int z = 0; z < myshareddata.NUMBER_OF_LAYERS; z++) {
			for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
				for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

					map[z][y][x]=0;
					
					if(z==0){define_regions[SharedData.REGION_INDOORS - 800][y][x]=false;}
					if(z==0){define_regions[SharedData.REGION_DUNGEON - 800][y][x]=false;}
					
	   
				}}}
		
		
		for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
			for (int x = 0; x < myshareddata.MAP_SIZE; x++) {
				//deleteNote(x,y);
				deleteWarpRift(x,y);
			}
			}
    	
    	mapName="";
        mapDescrip="";
    	current_paintbrush_layer=myshareddata.TERRAIN_LAYER_LOWER;
    	current_paintbrush_subcategory = 0;
        
        
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
	
	
	void openConfigMenu(){
		mapconfigfrm.setVisible(true);
		
		mapconfignamefield.setText(mapName);
		mapconfigdescrip.setText(mapDescrip);
	}
	
	
	
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
		
		//System.out.println(ref );
		
		
		
		
		//System.out.println(localpath );
		
		Image2D img = null;
		try {
			img = new Image2D(textureLoader.getTexture( "wrap/assets/" + ref));
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


private boolean terrain_IsWalkable(int x, int y) {
	for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
		
		for (int i = 0; i < myshareddata.COLLIDABLE_OBJECTS_FLOOR.length; i++) {

			if (myshareddata.COLLIDABLE_OBJECTS_FLOOR[i] == map[z][y][x] ) {
				return false;
			}// server checks this type of collision, NPCS ARE NOT A PART OF
				// THIS

		}
		
		
		for (int i = 0; i < myshareddata.COLLIDABLE_OBJECTS_HOVER.length; i++) {

			if (myshareddata.COLLIDABLE_OBJECTS_HOVER[i] == map[z][y][x] ) {
				return false;
			}// server checks this type of collision, NPCS ARE NOT A PART OF
				// THIS

		}
					
		
		
		//if (map[z][y][x] >= LAKEWATER_1 && map[z][y][x] <= LAKEWATER_16) {
		//return false;
		//}  collision with water!
		
	}
	/* else */return true;
}

private boolean terrain_IsHoverable(int x, int y) {
	for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
		for (int i = 0; i < myshareddata.COLLIDABLE_OBJECTS_HOVER.length; i++) {

			if (myshareddata.COLLIDABLE_OBJECTS_HOVER[i] == map[z][y][x]) {
				return false;
			}// server checks this type of collision, NPCS ARE NOT A PART OF
				// THIS

		
		}
	}
	/* else */return true;
}


static int pythagorean(int x1, int y1, int x2, int y2){
	return (int) (Math.sqrt(Math.abs(Math.pow(
			x1 - x2, 2))
			+ Math.abs(Math.pow(y1
					- y2, 2))));
}
	

int AngleBetweenCoordinates(int x1, int y1, int x2, int y2){
	int direction = 0;
	double angle = Math.toDegrees(Math.atan2(x1 - x2, y1 - y2));
	
	
	while (angle < 0) {
		angle += 360;
	}
	while (angle > 360) {
		angle -= 360;
	}

	if (angle <= 45 || angle > 315) {
		direction = 0;
		
		}
	
	if (angle > 225 && angle <= 315) {
		direction = 1;
		
		}
	
	if (angle > 135 && angle <= 225) {
		direction = 2;
		
		}
	
	if (angle > 45 && angle <= 135) {
		direction = 3;
		
	}
	return direction;
}


boolean object_blockslight(int x, int y) {

	for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
		for (int i = 0; i < myshareddata.LIGHTBLOCKING_OBJECTS.length; i++) {

			if (myshareddata.LIGHTBLOCKING_OBJECTS[i] == map[z][y][x]) {
				return true;
			}// server checks this type of collision, NPCS ARE NOT A PART OF
				// THIS

		}
	}
	/* else */return false;

}



void debug(Object o){if(DEBUG_MODE){System.out.println(o);}}
	   
	}
