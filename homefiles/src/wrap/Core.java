package wrap;

/*
 * Copyright Baneforge Project 2012
 * 
 */

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE;
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
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/*-need to add levers that heroes can actually pulL! Buttons they can step on!!
 * -step on a button, play sound! -push block, play sound!

 -make tutorial/help - people do not know what to do! Make it just like EVE's
 -cooler loading screen
 
 sounds to add: warcry, goblin1, lava, spellwoosh, explodemini explode

-Allow the DM to queue up moves for units!

-stats and icons for assets are ALLLL messed up..
-sounds for skeles and goblins!  bones, scream, gob voice

-double clicking on the Shadows At Dawn campaign to run it will crash the game

-minotaur right animation is messed up again... !?

 -trying to join local is messed up (same issue with bob... and kyle..)
 -make unit easier to edit --  external textures!
 -if a weapon fails to hit, just make a swish sound! 
 -add dodging and blocking and missing!

 -loot tables!!! Add a param to items for it, add param to units for it. Easy!

 -talent tree

 -backstab damage
 
 -critical strikes, blocking  CRIT RATING


 Clients can de-clothe other NPCs??


 -more monster items, random ones!  (crafting mats!!) <--this would be so flippin awesome


 -make doodads (tents,crates..) destructable?


 -Test units casting abilities and using potions while outside of battle.



 - I wish the asseteditor was not so cluttered and insane...Should group things better and make it more graphical, less redundant


 -ranged NPCs, bow shooters and slingshotters.. etc
 -more spells to close distance
 -elves
 -dwarves

 -overlay GFX for casting invisibility (magic dust?) and sound for it!


 -try to get people to host campaigns and work on them for a prolonged period of time before heroes join in!  Allow
 them to create quests and things in this mode!



 -fix sounds! Implement many new ones
 * -more spells
 * 
 * 
 *
 * -better marketing
 * -need a little banner for when a spell point is ready to spend
 * 
 * 
 * 
 * -page 70...
 * Skill challenges,
 * Puzzles,
 * Traps and hazards!
 * 
 * 
 * 
 * -barricades (buildable?)
 * -ability to give gold
 * 
 * 
 * -make some abilities require others
 * 

 * -need more kiting and sprints-more movement abilities

 * 
 * -allow people to walk over bridges, draw them correctly as underneath!
 * 

 -need ranged enemies that kite, some that slow
 -need abilities that improve movement.. sprint and roll, leap, etc

 -make every skill tree have a rapid-movement skill



 -need a tool to reset password on the website... 

 -need more obvious way to see health and energy amount (numbers).. in inventory menu


 * -need an actual scenario, finish the quest menuuuuu
 * 
 * -quest menu! Save quests (and units of course) in ccampaign.) make an icon to drag onto the map as a waypoint!
 * -crafting menu!
 * -add in the new object models
 * 
 * 
 * -make icon for abilities that are "attacks'. Do not gain the energy for the next turn if one is cast.(unless stat-buff..)
 * make a stat-buff that makes walking use and require energy
 * 
 * 
 * -on the register page, put advertisements for buying the game and then a button to go to fastspring
 * 
 * 
 * -force must be logged in to load a higher level hero!!! (or gain some other functionality)
 * 
 * 

 * said that kyle had quantity 4 of all of his items!?
 * 
 * 

 * game crashed when NumberofBattlling Units function ran
 * host should be able to delete items at will. cannot delete hero items!?
 * 
 * 
 *
 * 
 * 
 * -add in item abilities (like wep skills). 
 *  
 * -minotaur corpse?
 * 
 * 
 * 
 * -sound for DM editing heath stat of a zombie.
 * -sounds for buying items, gold jingle
 * 
 * 
 * 
 * 
 * -make some effects require certain stats to be greater than, equal to, or less than, other values.
 * 
 * -add more spells
 * 
 Each spec should hvae passive perks assigned to it!

 -make a good video for thesite - gameplay example of going on a quest! getting items from a chest etc etc

 -volume slider in menu!!

 -make the ability to browse Remote servers more obvious for clients!!


 -need a button in the menu for hosts to prevent other people from joining the game from that point forward.  
 -need to filter the results from the mysql table! no duplicates or non-workers?

 LOADSCREEN THINGS
 -loading bar
 -campaign info (is it even passed over yet???)


 -make a hints/tips window


 RESISTANCES TO
 purity
 darkness
 fire
 ice
 lightning




 -need simple overlay GFX for a net on a unit



 -implement some spells requiring a minimum level


 -finish objectives menu and the button to save and quit! Tells all heroes to save and quit gracefully.
 -add default campaigns to the game, with monsters and objectives included!!!!


 -need spell immunities and such. ( by race.. undead etc...)  Abilities like MTG!!



 -draw more campaign stuff in the loading screen for heroes!




 Make the file browsers render a lot cooler than they do right now?  Just need titles and a nice scrolly bar that can be moved with a mouse


 * 

 * -make a wall that can be raised or lowered! fo puzzles..
 * -need a magic crystal object. and items.
 * -need those farm critters so you can have a polymoprh quest.
 * 
 * 
 *  
 *  -add way more spells!  Shooting a rocket, make potion, etc.
 --Make an ability for spiders to build webs which turn them invisible while they are near.


 * 
 * 
 * 
 *	need some sort of a tiny tutorial..
 * 
 * -need cool banner for going into battle mode, reward screen for leaving it.
 * 
 *  
 * -need to allow heroes to know that they should choose a spell right when they spawn...
 * -Make hero descriptions editable after the fact
 * 
 * 
 *
 * 
 * -only pick perks and spells while in game! Perks can be a new tab in the abilities menu! Replace weapon skills menu!
 * 
 * 
 *  * 
 * 
 * -after battle sequence, pop up spell-picking menu if the hero gets a new spell and a stat-picking window if they 
 * get new stats (IF they leveup from that battle)..  Upon gaining a level, a hero will never get both rewards at once.

 * -Heroes can access a crafting menu and a VENDOR MENU while outside of battle.
 * 
 * 
 * 
 * LATER...
 * -allow for some abilities to be cast outside of battle.
 * -add pause/play button for the DM?
 * 
 * 
 * 
 * BUGS---
 * 
 * -crashed from item menu.. I think
 * 
 * 
 *-quest objectives menu!!  Editable by host, readable by heroes.
 *-Allow host to edit titles and descriptions of quests, toggle their visibility, and delete them with YAY or FAIL buttons.
 *
 *
 *   When a new quest is added by DM, make a big banner for heroes and play a sound.
 *-In the menu, there should be three rectangles, one for each active quest.  You can toggle visibility of them with an eye icon and clear them by clicking a Green Checkmark or a Red X (pass or fail)


 * -AT WORK: make default abils all have friendly fire (besides the AoE and heal ones!!)
 -test and fix abils( spawn skeleton,INVISIBILITY!)-conditions should have parameters for tinting affected units (invisibility)
 -add more abilities
 -allow host to open and close doors with the lil menus!






 -draw itemmodels and animations in the asseteditor

 - make a plague condition that infects nearby units with another condition!
 -Phoenix File Damage has 'affects Allies' false yet it still burns the caster!!! WHAT!!??  casterID needs to be passed allll the way thru
 SPELL: A shield on you that shocks all nearby units at the beginning of each turn


 -need GFX for being healed?
 -use kyles sounds to make a SFX for lightning bolt cast and hit

 -asset book corruption!?

 -effect and sound for levelling up
 -effect and sound for gaining gold and for exp 



 * -release new version at this point !
 *  
 * DO more streaming...need a better video, with talking!
 * 
 * -tutorial menu MUST LATER if needed.  Make video first!!
 * 
 * -more largeobject should be slightly animated (see HoMM)

 * 
 ** 
 *  
 * -In the objective menu for the Host, put a 'grant victory' button that gives all heroes victory!
 * -make heroes respawn upon dying but make them lose stuff like items and/or gold
 * 

 -host tries to send almost all messages to players who have left...

 Later:
 * -add better cliffs, waterfall
 *  -give all objectart a solid black outline for a nicer cartoony look (burn)
 -would like it to all look somewhat like HOMM2 :o-would like it to all look somewhat like HOMM2 :o
 -just moving the screen can affect the shading on objects because the look for the tile's lit/4 or whatever!

 -need a good sound effect for iceshield

 -need better perk choosing in NewHero, draw it in invmenu. Should be a bunch of icons in the invmenu
 -PERK IDEAS
 -one perk could give a bonus to toxin resistance



 -need a flag for goodness/evilness
 * -shooting a bow always makes the hero blink..?? What animation is playing while the blink happens? maybe the static one...
 * 
 * 
 * -make a good working set of assets (and hero spells) that are all fun !!
 * 
 *  -add in hero PERKS descriptions! Add em to inv menu (with cool yellow grunge BG).. hero descrips, 
 * 
 *
 * -projectile shoot sounds all need correct delays

 * 
 * -add assets-icons for shoulder items
 * 

 * -When making a Sound effect, there are far too many useless options!
 * -Edit Stats should not have 'duration'
 * 
 * -donate to lwjgl,slick
 *
 * 
 * -List the effects that an ability does in its tooltip, stat bonuses of items!
 * 

 * 
 * -add traps, need models (pressure plate)
 * -fix the menus for the red and blue tiny wells that are supposed to heal health/mana and fix their offsets
 * 


 * 
 * 
 * -right clicking on an anvil/forge opens crafting menu for heroes
 * 
 * -need hero saving somehow.. autosaving thread thingy :D
 * 
 * -make an initiative stat (agility?) that determines which units get to go first in a turn.  Mark units properly for that.
 * 

 * -make everyone default to chat bubble AND text unless they type /OOC, then its just text!
 *  
 * -put an asset sharing page on the website
 *  
 * 
 * -fix little hills offsets!
 * 
 * -add roofs that go transparent! a simple large object.
 * 
 * 
 * 
 * 
 * -always pass unitID with length 6 !!!!
 * 
 *   
 * -hero items are not saved, neither are campaigns
 * 
 *
 * 
 *
 -house needs proper entryway/doors!!!


 -more cave oriented! need more/better cave walls??

 -menus dont show up for the blood/mana pools?



 -consumabe items do no effects :[
 -make asset editor far far nicer (just hide more useless parameters! its in the code so....)
 -health and energy bars on top of unit portraits?

 -fix human female sounds
 -gold system, merchants

 -cannot drag ability icons off the ability bar!! need to be able to!! (mouse clicked down for long enough, set that slot to 0, put it in
 ability being dragged, cancel the current cast of it!(  Also only make the ability be cast upon RELEASE?)

 Allow for draw projectile to do multiple ones (auto-offset by random delays!)

 -if a player joins into a slot that a previous player was in, they get weird socket errors upon doing anything.

 -servers never get removed from the Join Lan Server browser!

 -party GUI?

 -make cool artwork in gimp (like that of the game Zombies)


 -fix stats! makes statbonuses that are calculated when stats may change. ??
 -how in the world can i fix stats o.o (I am trying to bite off one big piece at once with this...)
 -There are many aspects I like about how I calculate stats.  Lets keep it like this until people complain! It is decent.



 -need a sound effect for EVERY LITTLE THING!
 Examples: Editing stats in the editStatsMenu

 more assets
 -add red and black splatter static overlay gfx to splat on the ground?


 -make crafting menu! (hamemr and sharpen knife for craft sounds)-allow for crafting with the defined item recipes, automatic crafting by heroes!!! (crafting spot in inv menu?)
 -fix campaign files

 -Draw aura condition and tile condition icons on units in a certain spot! They function VERY SIMILARLY

 Price: $10.00 USD

 -need auto equipping weapon skills!!!!

 -allow for more variety with custom units? (make sure invuln and immobile params work)


 -more torch-esque items :D



 -add a simple moving rectangle for a read-only slider bar on ALL mousewheel things!


 --now we will be ready ready for open beta test--


 ART NEEDED..
 -zombie atk animations
 -MORE weapon equip models (sword axe)



 -Throw a beta build up on www.baneforge.com
 -update indieDB and stuff


 -use jarsplice to make .exe files.
 -use resourcehacker to change icon of exes/ do AddResource to a .ico and name it the same as the existing folder!




 POSSIBLE BUGS...
 -when the host added items to a unit (dragged onto model), the itemcontainer nearby was getting those items added to it too...!?
 -overlaygfx animations get really slow sometimes?
 -hero got vision of an NPC one time?? why?
 -Fix flags?
 -Chests are dumb until I can figure out the best thing to do for them



 //allow map editor to specify monster markers as guides?
 make NPCs be grouped by their level 

 -make sure projectiles have the proper offset!


 -cool hero spawning effect?


 //make an animation of spewing red pixels
 //test projectile death effects


 //add kyles ZOMBIE sounds to asseteditor and convert all .wav files to .ogg!


 //fix inv menu stat display, also display bonuses in green!

 //add static overlay gfx like blood splatter and gibs
 * 
 * ///draw some sort of indicator on the unit that has the 
 most initiative and should be taking its turn!






 //mak a button to open the waeponskills menu! (make them all black with white icons and cool)
 //make it more obvious when there are weaponskills available!
 //always throw newly available abilities on the secondaryabilitybar! allow for swapping secondaryabilitybar things




 //chat whispering


 //finish in campgaign saving! (save the map plus all of the units and tileconditions!)


 //max unitID is only 999 due to comms!!  **** Could pass x and y to make it infinite...


 //dont worry too much about optimization at this point
 //inspiration from http://soapquest.googlecode.com/svn/trunk/src/Minigame.java

 NEED    
 -how else can i polish the game?


 -game are most fun when the player is in great peril and they
 work their way out of it
 -add scrolls of town portal ?

 -resizing minimap? 
 -need to be able to save your progress! campaigns AND hero saving within the game!

 -moving items should play a sound!

 * -add custom proj death sounds!
 * -draw icons for tile conditions at the very top center of the screen
 * 
 * 
 * 
 * -better tooltips and dynamic drawing edges,
 * better menus and dynamic drawing edges! * 
 * 
 *
 * 
 * -image offsets are broken
 * -some units are missing portraits
 * 
 * -add GUI stuff for host overriding end turn
 * -throw up a cool flashy banner for the beginning of a new turn, shows sun or moon in a position!
 * 
 * -if menu music is muted, mute gaem music too
 * -Allow for hiding of the minimap.
 * 
 * 
 * - add in ambient effects like water drips and birds. Make sound emitters in map editor!?
 * 
 * 
 * -load heroes like assetbooks are loaded so less crashing happens.  Or make an error pop up instead of a crash!!!
 * -make an error pop up instead of a crash in ALL wrong-version crash scenarios!
 * 
 *-make the actual assets
 * 
 * 
 * -sound effect play in asset editor? 


 * -fix NPC drops (do this in asset editor) (according to groups, make NPCs drop items on death)

 * 
 *   
 * -allow for declaring multiple projectile per tile. If this is chosen, make them all have random offsets and random speeds!
 *
 * 
 * 
 * 
 * -implement stat_affecting_amount for condition DB . make it actually do it.
 * -implement RGB choices in asset editor! Especially for overlay effects. color pallate
 * 
 * 
 -delete particleemitter and regionalcondition classes?


 -make energy deplete and regen very easily?

 *  
 *  -finish the tile based conditions implementation
 * -collapsable condition menu, allows DMs to drag and drop conditions onto tiles!

 * 
 *  
 *    all mini buttons and icons look horrible.(revamp the game UI at some point)
 * 
 * -campaign files, campaign saving
 * 
 * 
 *  * let heroes trade items with other players!(players know each others
 *  inventories now!! yay!)
 *  
 *  
 -still need an objectives menu, and map-state saving so hosts can 
 Start New Campaign or Load Existing Campaign

 -along with an objectives menu, there should also be a menu that tracks
 reputations with tribes (completely customizeable by the DM of course)



 - fix npc loot tables and let map editor places special chests that give instanced loot.

 -NPCs will spawn with some random loot, (asset editor)
 -NPCs loot is non-instanced!! all loot is non-instanced EXCEPT for the loot from magic chests!

 Instanced loot: player does the rolling
 Non Instanced Loot: DM does the rolling and reports to the players




 * 
 * -make some chests (wooden) be non-instanced and make some chests (magic) be instanced!!!
 * -NPCs that die will drop the non-instanced loot that is in their inventory slots/equipped!
 * 
 * -add many weapons with abilities
 * -add new spells
 * -add new items  
 * -allow mapeditor to define chest slots with an itemgroup number. (how the loot table will work)
 * 
 * 
 * 
 *
 * -allow items to increase a stat by a factor of the amount of another stat (gun does more power based on your science)
 * 
 * 
 *   -in the host lobby, make options for loading up texture packs and custom spellsbooks JUST LIKE MINECRAFT.
 *   no package chosen? choose default package!
 *   (this means i need to package the spritemaps?)
 * 
 * 
 * -allow for units to be edited in the asset editor (their stats and their abilities mainly)
 * 
 * -allow items to be assigned groupnumbers.  Every chest is defined individually in the map editor, so each of its 6 slots is assigned a different item group number to pull from.
 *    

 * 
 * 
 * ASSET RESOURCES
 * http://www.freesfx.co.uk/soundeffects/guns/
 * 
 * 
 *
 * 
 * -add computer terminals, cameras, trip lasers?, alarms, robots, machine turrets.


 *
 * -ability to mute the music
 * 
 * -make all sounds ogg! use openal, its nice!
 * 

 *
 * Sonic Blasts causes a gigantic lag spike. why???  It is NOT because of the cone code! it is something else.
 * 
 * -desperately need animations and sound!
 * 
 * 
 * 

 -add levers and computers 



 client lighting is strange and goes nuts every turn. why.
 * -make a 'bye' message get sent if a hero disconnects so it doesnt make 100 errors
 * 

 * --should not be able to swap weapons so quickly/readily. Maybe this will just affect the weapon abilities... make them cool down right after changing weapons out!
 *
 * 
 * -item trading goes in later
 * 

 * 
 * -level up animation, more flashy killing sequence w/ blood particle explosion and gore sound effect *********
 *   
 * 
 * -allow map editor person to assign chests a level.  Put random items in the chest based on that level.
 * 

 * -add  scrolling to weaponskill pane! very easy.
 * 
 * 
 * -make it so you cannot take abilIties off of bar if they are still cooling down!
 * 
 *    -make an objective tracker/quest log.
 *    
 *    -implement condition intensity, allow for the same condition to be stacked an infinite amount of times, since that is the way it should work

 -make sure tooltips for things like obelisks and such work!

 *         -add objectives to maps, descriptions.  There should be an objectives pane, DM can check off completed ones!!
 *       
 *      -ability effects can be turned on and off depending on equipped weapon? maybe?
 *        
 --dice icons on the general GUI and in the EditStats Window
 *       -plus and minus maintaining buttons in the edit stats window!
 *       
 *       
 *        -if a player quits, make a WAIT TO REJOIN or SAVE menu.. game freaks out when a player loses connection!
 *
 *     
 *     

 *


 */

/*
 * 
 ,,                                     :,                                       
 ~=======,        ,:                   ,======:        ,           :,           
 ==:,~==,        ==        ~=~,       ==,,,::~~      ~=,        ==,    ~~     
 ==~~==:,~=====~ =======  ======      ==,    ======~ =============  ======    
 :========~, ~==== ==~  ==,~==: ==~     ======:==,  == ===  :==, ,== ==~, ==,   
 ==,  ==:==~::== ==~  ==,~==:::::     ==,   ~==   ==,==~  :==  :== ==~::::,   
 ,~=======,======= ==~  ==,:=======~,   ==,    ======: ==~   ======= ~=======:  
 ,:~::,,,,  :~::,=~ ==:  ==,  ~::::,    ,=~,     ~~::~, ~=:    ,::~==  ,:~~~,    
 ~~:,    :,            ~~              ~:  ,~~~~===:            
 ::::,             


 An Open-style Multiplayer RPG coded in Java with Slick2D utilizing OpenGL 1.1.

 This project uses assets from the following artists (who do NOT endorse Baneforge or any part of this game/project):

 -Everyone at www.opengameart.org

 Music and SFX: Kyle Peterson

 SFX: Bart K at OpenGameArt.org

 SFX: Michael Kurinnoy

 SFX: Julien Matthey

 SFX: Augmentality (Brandon Morris)

 Music: Telaron at OpenGameArt.org

 Music: Zefz at OpenGameArt.org

 SFX: Iwan 'qubodup' Gabovitch, http://qubodup.net

 Farm Tiles, Basic Terrain: Daniel Eddeland

 NPC Models:  Stephen Challener (RedShrike), Matthew Nash, Tunicate, Ben "Cookiez" Potter ( http://cookiez999.deviantart.com/), Charles Sanchez (CharlesGabriel)

 Cliffs and Objects: "2D Circle Graphic" art by Daniel Cook (Lostgarden.com)

 Tents: Unknown Horizons of www.opengameart.org

 Ability icons: http://lorcblog.blogspot.com.au/ , Lorc, http://game-icons.net, 

 Item Icons: http://ails.deviantart.com, Ails

 Military Icons: http://angrymeteor.com

 Icons: Ravenmore at opengameart.org

 Hero Models and Equipment: Johannes Sjolund

 Projectiles, stairs, 3D zombie: Clint Bellanger of OpenGameArt.org

 Boulder: Ecrivain of OpenGameArt.org

 Menu Backgrounds: Jordan Trudgett

 Flare Portraits: Justin Nichol

 Some Large Objects: Lanea Zimmerman (Sharm)

 Female hair: Yamilian

 Archer and Necromancer on Main Screen: user kirill777 on www.opengameart.org

 Weapon item icons: user Scrittl of www.opengameart.org

 Weapons and Mechs: Skorpio and his Sprite Pack '

 SFX: Ogrebane of www.opengameart.org
 SFX: artisticdude  of www.opengameart.org

 Roofing and many objects: Hyptosis and Zabin of www.opengameart.org
 * 
 * 
 * 
 */

//http://www.lwjgl.org/wiki/index.php?title=LWJGL_Basics_2_(Input)

public class Core {

	static Core maincore;

	static SharedData myshareddata = new SharedData();

	public boolean DEBUG_MODE = false;

	public static String WINDOW_TITLE = "Baneforge " + SharedData.CURRENTVERSION;

	/** The pixel size of tiles. */
	public static final int TILE_SIZE = 40;

	/** Screen width in pixels. */
	public int SCREEN_WIDTH = 900;
	/** Screen height in pixels. */
	public int SCREEN_HEIGHT = 640;

	int SCREEN_HEIGHT_MIN = 400;
	int SCREEN_WIDTH_MIN = 300;

	int MONITOR_HEIGHT = 100;
	int MONITOR_WIDTH = 100;

	// int turn_count = 1;

	boolean BattlePhaseEngaged = false;

	int BattlePhase_ActiveUnit = 0;
	// int BattlingUnits[BattlePhase_ActiveUnit] = 0; //remove this, older ways
	// are better! all clients do calculations themselves!!

	int BattlePhase_CurrentTurn = 0;

	BattleReport myBattleReport = new BattleReport();

	int Selected_Unit_Walk_Queue = -1;

	int BattlingUnits[] = new int[10000];
	int number_of_found_battling_units = 0;
	boolean BattlingUnitAlreadyWentThisRound[] = new boolean[10000];

	static final int MainBarIcons[] = {44, 692, 791, 779, 670};// walk, weapon
																// skill,
																// ability,
																// consumables,
																// end turn

	static final String MainBarTooltips[] = {"Walk to an adjacent tile. [WASD]", "Attack with a weapon skill. [Z]", "Cast a spell. [X]",
			"Use an item ability. [C]", "End turn. [TAB]",};

	// public int HERO_VIEWDIST = 22; // change this to get rid of bugs
	/** truly affects how far Units can see */
	public static final double HERO_VIEWDIST_DECAY = 0.05;

	// public static final double EXPLOREDMAP_BRIGHTNESS_INDOORS = 0.1;
	public double EXPLOREDMAP_BRIGHTNESS_OUTDOORS = 0.7;// for clients
	public double EXPLOREDMAP_BRIGHTNESS_INDOORS = 0.2;// for clients
	public double UNEXPLOREDMAP_BRIGHTNESS_OUTDOORS = 0.5;
	public double UNEXPLOREDMAP_BRIGHTNESS_INDOORS = 0.05;
	// public double HERO_REQUIRED_LIGHT_TO_SEE_ENEMIES = 0.4;

	// during the night hours, brightness outdoors becomes halved!!!

	// if explored... see below
	public static final double MINIMUM_LIT_INDOORS = 0.3;
	public static final double MINIMUM_LIT_OUTDOORS = 0.7;

	public final int tooltiplinelength = 40;

	public static final double DM_MIN_FOG = 0.15;

	int HOUR_OF_DAY = 6;
	int MINUTES_OF_DAY = 0;
	// Map data

	boolean game_fully_loaded = false;

	Player Players[] = new Player[10];

	/** The type on each map tile, or NOTHING if it's empty. */
	protected int[][][] map = new int[SharedData.NUMBER_OF_LAYERS][SharedData.MAP_SIZE][SharedData.MAP_SIZE];

	static final int MAX_OVERLAY_GFX_FRAMES = 16;

	// /BEGIN ITEM DECLARATION

	// items are in a separate category

	public static final int ASSETTABLE_UNITS = 0;
	public static final int ASSETTABLE_ITEMS = 1;
	public static final int ASSETTABLE_EFFECTS = 2;

	int[] AssetTableComboStringIndices = {11, 4, 5};

	String PopUpMessage = "";
	int PopUpMessageTimeLeft = 0;

	static final String ChooseHeroMenu_HeroStatNamestoDraw[] = {"Level", "Experience", "Health", "Energy", "Melee Power", "Ranged Power",
			"Spell Power", "Armor Rating", "Magic Resist", "Toxin Resist"};

	static final int ChooseHeroMenu_HeroStatstoDraw[] = {SharedData.LEVEL, SharedData.EXP, SharedData.HEALTH, SharedData.ENERGY,
			SharedData.MELEEPOWER, SharedData.RANGEDPOWER, SharedData.SPELLPOWER, SharedData.ARMORRATING, SharedData.MAGICRESIST,
			SharedData.TOXINRESIST};

	int[] number_of_purchasable_items = new int[20];
	int[][] purchasable_items = new int[20][1000];

	Image2D[] lightrays = new Image2D[4];// really? lol..... image animated
											// dirctional , bro!!

	// SimpleGuiObject Menus[MENU_LOGIN] = new SimpleGuiObject();

	String MainMenuHostHintText[] = {"Runs the game server", "Full control over the game", "One host allowed per game"};
	String MainMenuHeroHintText[] = {"Joins hosted servers", "Complete objectives for rewards", "Up to seven heroes per game"};

	Hint TutorialHint[] = new Hint[50];
	int tutorial_phase = 0;

	int InitSettingsData[] = new int[100];// for settings.txt

	String myHeroFiles[] = new String[1000];
	String myProfileImageFiles[] = new String[1000];
	String myMapFiles[] = new String[1000];
	String myCampaignFiles[] = new String[1000];
	String myCampaignImageFiles[] = new String[1000];

	int chooseheromenu_number_of_found_herofiles = 0;
	int chooseheromenu_herofileoffset = 0;

	int chooseheromenu_number_of_found_profileimagefiles = 0;
	int chooseheromenu_profileimagefileoffset = 0;

	int choosemapmenu_number_of_found_files = 0;
	int choosemapmenu_mapfileoffset = 0;

	int choosemapmenu_number_of_found_images = 0;
	int choosemapmenu_imagefileoffset = 0;

	int choosecampaignmenu_number_of_found_files = 0;
	int choosecampaignmenu_campaignfileoffset = 0;

	int ItemVendor_itemBeingDragged = -1;
	int bagSlotOf_itemBeingDragged = -1;
	int abilityicon_beingDragged = -1;
	int menu_beingDragged = -1;
	int menu_dragoffset_x;
	int menu_dragoffset_y;

	boolean map_beingDragged = false;
	int map_dragoffset_x;
	int map_dragoffset_y;
	int map_drag_original_x;
	int map_drag_original_y;

	int BattleResMenu_StoredCurrentExp = 0;
	int BattleResMenu_StoredCurrentLevel = 0;

	boolean notesVisible = true;

	// public static final int NUM_OF_UNITSTATS = 17; // 0 to 16

	// static DBs that never change in game
	DB_Abilities[] myAbilityDB = new DB_Abilities[1000];
	DB_Effects[] myEffectDB = new DB_Effects[10000];
	DB_Conditions[] myConditionDB = new DB_Conditions[100];
	DB_Items[] myItemDB = new DB_Items[1000];
	DB_Types[] myTypeDB = new DB_Types[1000];

	DB_NPCs[] myNPCDB = new DB_NPCs[1000];
	ProjectileModels[] myProjectileModels = new ProjectileModels[100];

	// dynamic DBs that can change in the game
	Unit[] Units = new Unit[10000];// 2,3,4,5,6,7,8 are heroes, NPCs start at 10
	Projectiles[] Flying_Projectiles = new Projectiles[100];
	ParticleEmitter[] myEmitters = new ParticleEmitter[1000];
	OverlayGFX[] myOverlayGFX = new OverlayGFX[1000];
	Quest myquests[] = new Quest[3];
	ItemContainer[][] ItemContainers = new ItemContainer[SharedData.MAP_SIZE][SharedData.MAP_SIZE];// x,
																									// y,
																									// storage,
																									// quantity.
																									// there
																									// is
																									// one
																									// on
																									// every
																									// tile!
	SimpleGuiObject[] Menus = new SimpleGuiObject[20];
	SimpleGuiObject[] DialogMenus = new SimpleGuiObject[10];

	SimpleGuiObject SleekMessageBox = new SimpleGuiObject();

	Model[] myUnitModels = new Model[1000];

	int[][] UnitMap = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE];
	// int[][] ItemContainerMap = new
	// int[SharedData.MAP_SIZE][SharedData.MAP_SIZE];
	int[][] TerrainVariantMap = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE];

	public int NUMBER_OF_UNITS = 10; // start at 10 to account for heroes!
	public int Number_of_Projectiles = 0;
	// public int Number_of_ItemContainers = 0;
	// public int Number_of_ParticleEmitters =0;

	long MYSQL_UNIQUEKEY = 0;
	String ACCOUNT_NAME = null; // if null, not logged in officially.
	boolean loadedcustommaporbook = false; // utilize this

	int[] invmenu_bonusstats = new int[50];

	int newhero_pointsleft = 5;
	// int newhero_perksleft = 2;
	int[] default_hero_stats = new int[SharedData.NUM_OF_UNITSTATS];
	int[] default_NPC_stats = new int[SharedData.NUM_OF_UNITSTATS];

	int newhero_chosenPreviewSprite;
	int newhero_align1;
	int newhero_align2;
	// int chosenPreviewProfileImage=1;

	int animationframe = 0; // goes from 0 to 319, and the animations will take
							// the modulus (remainder, % ) of this to figure out
							// their frame number to show!
	// int animationframe = 0; // goes to 47

	// Image2D CustomHeroProfileImages[] = new Image2D[10];

	public static final int OVERLAY_GFX_SLASH = 1;// just an animation
	public static final int OVERLAY_GFX_BURNING = 2;// just an animation
	public static final int OVERLAY_GFX_BLACKDRIP = 3;// just an animation
	public static final int OVERLAY_GFX_WHITEFLARE = 4;

	public static final int SOUND_COIN = 1;
	public static final int SOUND_BURNING = 2;
	public static final int SOUND_BOWANDARROW = 3;
	public static final int SOUND_DICEROLL = 4;
	public static final int SOUND_EVILSPELL1 = 5;
	public static final int SOUND_SCREECH1 = 6;
	public static final int SOUND_STEPGRASS1 = 7;
	public static final int SOUND_STEPGRASS2 = 8;
	public static final int SOUND_STEPGRASS_RANDOM = 9;// randomized soundgroup
	public static final int SOUND_HISS = 10;
	public static final int SOUND_GHOSTHIT = 11;
	public static final int SOUND_ENERGYGLOW = 12;
	public static final int SOUND_ENERGYRIFLE = 13;
	public static final int SOUND_ENERGYSHIELDBOUNCE = 14;
	public static final int SOUND_FIREBALL = 15;
	public static final int SOUND_GLOWUP = 16;
	public static final int SOUND_LASERBLASTER = 17;
	public static final int SOUND_LASERHIT = 18;
	public static final int SOUND_MELEEGORE = 19;
	public static final int SOUND_MELEESMACK = 20;
	public static final int SOUND_MELEETHUD = 21;
	public static final int SOUND_MELEETHWACK = 22;
	public static final int SOUND_PHASEOUT = 23;
	public static final int SOUND_PLASMA = 24;
	public static final int SOUND_POWERDOWN = 25;
	public static final int SOUND_POWERONLINE = 26;
	public static final int SOUND_SHOOTRIFLE = 27;
	public static final int SOUND_SYSTEMSBEEPING = 28;
	public static final int SOUND_WOODEXPLODE = 29;
	public static final int SOUND_GOREEXPLODE1 = 30;
	public static final int SOUND_GOREEXPLODE2 = 31;
	public static final int SOUND_GOREEXPLODE3 = 32;
	public static final int SOUND_GOREEXPLODE4 = 33;
	public static final int SOUND_DRUMLOW = 34;
	public static final int SOUND_DRUMLOW2 = 35;
	public static final int SOUND_LEATHERITEM = 36;
	public static final int SOUND_PISTOLFIRE1 = 37;
	public static final int SOUND_PISTOLFIRE2 = 38;
	public static final int SOUND_SHOTGUNFIRE1 = 39;
	public static final int SOUND_SHOTGUNFIRE2 = 40;
	public static final int SOUND_PUNCH = 41;
	public static final int SOUND_HUMANGROAN1 = 42;
	public static final int SOUND_HUMANGROAN2 = 43;
	public static final int SOUND_HUMANGROAN3 = 44;
	public static final int SOUND_HUMANGROAN4 = 45;
	public static final int SOUND_ZOMBIE1 = 46;
	public static final int SOUND_ZOMBIE2 = 47;
	public static final int SOUND_ZOMBIEMUNCH = 48;
	public static final int SOUND_BEAST1 = 49;
	public static final int SOUND_BEAST2 = 50;
	public static final int SOUND_EXPBARTICK = 51;
	public static final int SOUND_SWISH1 = 52;
	public static final int SOUND_SWISH2 = 53;
	public static final int SOUND_SWISH3 = 54;
	public static final int SOUND_SWISH4 = 55;
	public static final int SOUND_MONSTERGRUNT1 = 56;
	public static final int SOUND_MONSTERGRUNT2 = 57;
	public static final int SOUND_MONSTERGRUNT3 = 58;
	public static final int SOUND_BUBBLE = 59;
	public static final int SOUND_BOTTLE = 60;

	public static final int PROJECTILE_FIREBALL = 0;
	public static final int PROJECTILE_ARROW = 1;
	public static final int PROJECTILE_JAVELIN = 2;
	public static final int PROJECTILE_ICICLE = 3;
	public static final int PROJECTILE_LIGHTNING = 4;
	public static final int PROJECTILE_BOLAS = 5;
	public static final int PROJECTILE_BOULDER = 6;
	public static final int PROJECTILE_NET = 7;

	// public static final int PROJECTILE_RADIANTORB = 2;// needs an image
	// public static final int PROJECTILE_LASERBLUE = 3;
	// public static final int PROJECTILE_SONICWAVE = 4;
	// public static final int PROJECTILE_PARTICLEBLUE = 5;

	static final int CHANGEFACING_ENERGYCOST = 5;

	public static final int SPECIALABILITYBUFFER = 10;
	public static final int SPECIALABIL_DMMOVE = 1;
	public static final int SPECIALABIL_WALK = 2;

	public static final int SPECIALEFFECTBUFFER = 10;
	public static final int SPECIALEFFECT_DMMOVE = 1;
	public static final int SPECIALEFFECT_WALK = 2;
	public static final int SPECIALEFFECT_WALKSOUND = 3;

	public static final int MENUSCREEN_FRONT = 0;
	public static final int MENUSCREEN_CHOOSEHERO = 1;
	public static final int MENUSCREEN_NEWHERO = 2;
	public static final int MENUSCREEN_JOINSERVER = 3;
	public static final int MENUSCREEN_CHOOSECAMPAIGN = 4;
	public static final int MENUSCREEN_NEWCAMPAIGN = 5;
	public static final int MENUSCREEN_LOADSCREEN = 6;

	public static final int DIALOG_RESPAWN = 0;
	public static final int DIALOG_CHOOSESPEC = 1;

	public static final int TYPEFOCUS_CHAT = 1;
	public static final int TYPEFOCUS_QUESTNAME = 2;

	Texture CustomProfileImageTextures[] = new Texture[10];
	int number_of_customtextures = 0;

	Image2D[] ProfileImages = new Image2D[20];// FOR HEROES
	Image2D ProfileImage_blank;
	BufferedImage myBufferedProfileImage;
	BufferedImage myBufferedCampaignImage;
	BufferedImage[] StoredBufferedProfileImages = new BufferedImage[9];// for
																		// the
																		// Host
																		// to
																		// pass
																		// on to
																		// new
																		// heroes

	int EditStatsMenu_Stats[] = {SharedData.LEVEL, SharedData.HEALTH, SharedData.HEALTHREGEN, SharedData.ENERGY, SharedData.STARTINGENERGY,
			SharedData.ENERGYGROWTH, SharedData.ENERGYMAX, SharedData.MELEEPOWER, SharedData.RANGEDPOWER, SharedData.SPELLPOWER,
			SharedData.ARMORRATING, SharedData.MAGICRESIST, SharedData.TOXINRESIST, SharedData.INITIATIVE, SharedData.SPEED, SharedData.GOLD_CARRIED,
			SharedData.EXP_REWARD, SharedData.WALK_MOVES_COUNT};

	int assettablemenu_currentasset = 0;// units items conditions
	int assettablemenu_currentscreen = -1;
	// int Menus[MENU_ASSETTABLE].scrolloffset=0;
	int assettablemenu_ItemCurrentlyDragged = -1;

	/**
	 * How much each tile on the map is lit. Can only see objects on player
	 * layers with 0.5 lit or more
	 */
	// protected double[][] lit = new
	// double[SharedData.MAP_SIZE][SharedData.MAP_SIZE];
	protected double[][] lit = new double[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4]; // lit[y][x]
	protected double[][] alltorchlit = new double[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4]; // add
																										// the
																										// light
																										// of
																										// all
																										// sources
																										// of
																										// light
																										// together
																										// and
																										// then
																										// add
																										// that
																										// to
																										// lit
																										// !
																										// This
																										// will
																										// allow
																										// them
																										// to
																										// blend.

	protected boolean[][] shading_Explored = new boolean[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];
	protected boolean[][][] define_regions = new boolean[5][SharedData.MAP_SIZE][SharedData.MAP_SIZE];

	/** for procedural lighting code to revert back to the true lit */
	double[][] stored_lit = new double[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];

	int[] herospawnpoint_x = new int[10];
	int[] herospawnpoint_y = new int[10];

	// int[][] NoteTypes = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE];
	String[][] NoteMessages = new String[SharedData.MAP_SIZE][SharedData.MAP_SIZE];
	// String displayedRightTooltip[] = new String[5];
	// String displayedHovertooltip_lines[] = new String[10];
	String displayedGenericHovertooltip;
	String displayedMiniHovertooltip;
	int displayedAbilityHovertooltip;
	int displayedItemHovertooltip;
	int displayedConditionHovertooltip;

	int MainMenu_CurrentHint = 0;

	int displayedDMtooltipcolor = 0;

	FloatingText myFloatingText[] = new FloatingText[1000];

	// int inventoryslot_currentlyselected = -1; // for displaying the item
	// menu!
	// (happens on right click)

	// String ItemMenuOptions[] = { "Destroy One Item", "Destroy Stack" };

	// int editstatsmenu_selectedstat;
	int editstatsmenu_selectedstat_index;
	int editstatsmenu_increment;

	long counter_mousehelddown = -1;

	int intropanelanimation = 0;
	int intropanel_switchingto = 0;

	int abilitymenu_pane = 0;
	int itemtablemenu_pane = 0;// take advantage of me!!!

	int[] invmenu_equipslots_x = new int[9];
	int[] invmenu_equipslots_y = new int[9];

	/** ABILITIES AND CONDITIONS */

	SimpleGuiObject primaryabilitybar = new SimpleGuiObject();
	SimpleGuiObject secondaryabilitybar = new SimpleGuiObject();
	SimpleGuiObject conditionbar = new SimpleGuiObject();
	SimpleGuiObject minimenubar = new SimpleGuiObject();

	SimpleGuiObject tileconditionbar = new SimpleGuiObject();

	// public static final int MENU_TUTORIAL = 0;
	public static final int MENU_INV = 1;
	public static final int MENU_ESC = 2;
	public static final int MENU_ABILITIES = 3;
	public static final int MENU_EDITSTATS = 4;
	public static final int MENU_LOOTING = 5;
	public static final int MENU_ASSETTABLE = 6;
	public static final int MENU_MINIMAP = 7;
	public static final int MENU_QUESTTRACKER = 8;
	public static final int MENU_ITEMVENDOR = 9;
	public static final int MENU_BATTLERESULTS = 10;
	public static final int MENU_LOGIN = 11;
	public static final int MENU_TUTORIAL = 12;
	// int[] active_DMabilities = new int[10];

	public static final int EFFECT_SHAPE_POINT = 0;
	public static final int EFFECT_SHAPE_LINE = 1;
	public static final int EFFECT_SHAPE_CONE = 2;
	public static final int EFFECT_SHAPE_SQUARE = 3;
	public static final int EFFECT_SHAPE_CIRCLE = 4;

	public static final int EFFECT_ACTION_MOVEUNIT = 0;
	public static final int EFFECT_ACTION_EDITSTATS = 1;
	public static final int EFFECT_ACTION_APPLYCONDITION = 2;
	public static final int EFFECT_ACTION_DRAWPROJECTILE = 3;
	public static final int EFFECT_ACTION_CREATEUNIT = 4;
	public static final int EFFECT_ACTION_OVERLAYGFX = 5;
	public static final int EFFECT_ACTION_SOUND = 6;

	public static final int TEXTBOX_JOINIP = 0;
	public static final int TEXTBOX_JOINPORT = 1;
	public static final int TEXTBOX_HOSTPORT = 2;

	public static final int TEXTBOX_HERONAME = 3;
	public static final int TEXTBOX_HERODESCRIP = 4;

	public static final int TEXTBOX_LOGINNAME = 5;
	public static final int TEXTBOX_LOGINPASSWORD = 6;

	public static final int TEXTBOX_CAMPAIGNNAME = 8;
	public static final int TEXTBOX_CAMPAIGNDESCRIP = 9;

	public static final int TEXTBOX_NEWQUESTTITLE = 10;
	public static final int TEXTBOX_NEWQUESTDESCRIP = 11;

	/*
	 * // Condition declarations // debuffs are 1-50 // public static final int
	 * NOTHING = 0; public static final int CONDITION_DOWNED = 1; public static
	 * final int CONDITION_BURNING = 2; // DoT public static final int
	 * CONDITION_POISONED = 3; // DoT public static final int CONDITION_CHILLED
	 * = 4; public static final int CONDITION_CRIPPLED = 5; public static final
	 * int CONDITION_FROZEN = 6; public static final int CONDITION_STUNNED = 7;
	 * public static final int CONDITION_FRIGHTENED = 8; public static final int
	 * CONDITION_BLEEDING = 9; // DoT public static final int CONDITION_WET =
	 * 10; // /amplifies lightning and // turns chilled into frozen but //
	 * become immune to burning public static final int CONDITION_SHOCKED = 11;
	 * public static final int CONDITION_MINDCONTROLLED = 12; public static
	 * final int CONDITION_ENTANGLED = 13; public static final int
	 * CONDITION_CONFUSED = 14;
	 * 
	 * // buffs are 50-99 public static final int CONDITION_MIGHTY = 51;//
	 * strength public static final int CONDITION_FEARLESS = 52; public static
	 * final int CONDITION_HARDENED = 53;// armor public static final int
	 * CONDITION_FOCUSED = 54;// intelligence public static final int
	 * CONDITION_QUICKENED = 55;// agility public static final int
	 * CONDITION_UNSTOPPABLE = 56;// cannot suffer from // some conditions
	 * public static final int CONDITION_INVULNERABLE = 57;// cannot take power
	 * public static final int CONDITION_FORTUNATE = 58; // luck
	 */

	/** MISC */

	int myPNum = 0; // /player 0 is the one for

	String mapName;
	String mapDescrip;

	boolean finished_map_download = false;

	String localIP; // the ip address (like 192.168.1.1) that joiners will
					// attempt to join to with a client socket
	int serverport = 36650; // the server port that the Host will bind the
							// socket to and that the joiners will attempt to
							// bind to

	int current_MainMenu_Screen = MENUSCREEN_FRONT;
	int queued_MainMenu_Screen = MENUSCREEN_FRONT;
	int MainMenu_tween_x;
	int MainMenu_tween_y;

	int loadscreen_currentstage = 0;

	int current_D20_roll = 20;

	boolean hostpnl_MapIsLoaded;

	// public ClientThreadold networkThread_cli;
	// public ClientThreaded networkThread_cli;
	public ClientThreaded networkThread_cli;
	public UDPBroadcastClient UDPThread_receive;

	public ServerMultiThreaded networkThread_serv;
	public UDPBroadcastServer UDPThread_broadcast;

	boolean ServerOpenToPublic = false;

	// THESE HELP THE SERVER KEEP TRACK OF THINGS
	int num_clientsconnected;
	// int num_clientsready;
	int num_clientsendedturn;

	long BGMusic_finish_timer;

	int targetting_currentability = 0;// the targetted ability that was
										// activated by a client but not yet
										// placed down on the map
	int targetting_currentcaster = 0;

	int serverbrowser_mode = 1;

	int itemcontainer_currently_looting_x = 0;
	int itemcontainer_currently_looting_y = 0;

	int[][] AbilitiesInEachSpec = new int[11][500];

	String lastHeroFilePath;
	// Boolean mySettings.AutomaticSavingOn = false;// i should be true

	Camera cam = new Camera();
	MouseCursor cursor = new MouseCursor();

	HostServerInfo[] myFoundLocalHosts = new HostServerInfo[100];
	HostServerInfo[] myFoundRemoteHosts = new HostServerInfo[100];

	String ipAddr_local;
	String ipAddr_global;

	static final String[] darkMusic_path = {"Mystery.ogg", "Straight to Hell.ogg", "midnightrising.ogg"};
	static final String[] lightMusic_path = {"Japanese Taiko Drum.ogg", "Serenade Stream.ogg", "World At Rest.ogg"};
	static final String[] battleMusic_path = {"battle_1.ogg", "battle_2.ogg", "battle_3.ogg"};

	int[] lightMusic_duration = {70, 120, 70};
	int[] darkMusic_duration = {85, 70, 77};
	int[] battleMusic_duration = {85, 90, 200};

	final float MUSICVOLUME_FULL = 0.06f;
	// float initMusicVolume = MUSICVOLUME_FULL;

	URL menuMusic_url;

	int game_typing_focus;
	String chat_typing_text = "";

	// Image2D[][][] TypeImages_animated_directional = new
	// Image2D[SharedData.NUMBER_OF_TYPES][4][8]; // facing,
	SpriteSheet[] Object_Spritesheet = new SpriteSheet[SharedData.NUMBER_OF_TYPES];

	String[] mySoundPaths = new String[100];// allows just the ID to be passed

	TextureLoader textureLoader = new TextureLoader();

	Settings mySettings = new Settings();// stored and loaded with XML

	static MapEditorCore proxymapeditor;
	public static void launch_mapeditor() {
		Display.destroy();

		proxymapeditor = new MapEditorCore();
		System.exit(0);
	}

	static AssetEditorCore proxyasseteditor;
	public static void launch_asseteditor() {
		Display.destroy();

		proxyasseteditor = new AssetEditorCore();
		System.exit(0);
	}

	public static void main(String[] args) {

		maincore = new Core();

	}

	public int SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1;
	public int SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;

	String menuMusic_path;

	// /SimpleGuiObject EndTurnButton = new SimpleGuiObject(SCREEN_WIDTH - 220 -
	// 50, 10, 50, 50);

	SimpleGuiObject PhaseButton = new SimpleGuiObject(SCREEN_WIDTH / 2 - 50, 10, 120, 55, true);

	SimpleGuiObject GUIProfileImage = new SimpleGuiObject(20, 30, 100, 100, true);
	SimpleGuiObject GUIHealthBar = new SimpleGuiObject(20, 115, 100, 10, true);
	SimpleGuiObject GUIEnergyBar = new SimpleGuiObject(20, 125, 100, 5, true);

	SimpleGuiObject button_editinv = new SimpleGuiObject(24, 8, 30, 30, true);
	SimpleGuiObject button_moveunit = new SimpleGuiObject(55, 8, 30, 30, true);
	SimpleGuiObject button_editstats = new SimpleGuiObject(86, 8, 30, 30, true);
	SimpleGuiObject GodButtons[] = {button_editinv, button_moveunit, button_editstats};
	String GodButtonText[] = {"Edit Inventory", "Move Unit", "Edit Stats"};

	public Core() {

		MYSQL_UNIQUEKEY = (long) (Math.random() * 4294967290L);
		debug(MYSQL_UNIQUEKEY);

		MONITOR_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
		MONITOR_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

		// load width and height here from the settings file!

		if (SCREEN_WIDTH > Toolkit.getDefaultToolkit().getScreenSize().width) {
			SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
		}
		if (SCREEN_HEIGHT > Toolkit.getDefaultToolkit().getScreenSize().height) {
			SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height - 50;
		}

		try {
			ipAddr_local = InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {}

		// GOTTA PING A WEBSITE AND THEN PARSE THAT INFO TO GET THIS!!!

		URL whatismyip;
		try {
			whatismyip = new URL("http://baneforge.com/getexternalip/");

			URLConnection connection = whatismyip.openConnection();
			connection.addRequestProperty("Protocol", "Http/1.1");
			connection.addRequestProperty("Connection", "keep-alive");
			connection.addRequestProperty("Keep-Alive", "1000");
			connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			ipAddr_global = in.readLine(); // you get the IP as a String

			debug(ipAddr_global);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new File(SharedData.baseDirectory()).mkdir();
		new File(SharedData.defaultDirectory()).mkdir();
		new File(SharedData.defaultDirectory() + "\\Profile Images").mkdir();
		new File(SharedData.defaultDirectory() + "\\Heroes").mkdir();
		new File(SharedData.defaultDirectory() + "\\Maps").mkdir();
		new File(SharedData.defaultDirectory() + "\\Campaigns").mkdir();
		new File(SharedData.defaultDirectory() + "\\Campaign Images").mkdir();
		new File(SharedData.defaultDirectory() + "\\Textures").mkdir();
		new File(SharedData.defaultDirectory() + "\\Textures\\NPCS").mkdir();

		CopyLocalFileIfNotFoundInAppData("wrap/assets/MyAssetBook.spl", SharedData.defaultDirectory() + "\\" + "MyAssetBook.spl");

		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultTerrainSheet.png", SharedData.defaultDirectory() + "\\Textures\\" + "TerrainSheet.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultSmallObjectSheet_50.png", SharedData.defaultDirectory() + "\\Textures\\"
				+ "SmallObjectSheet_50.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultLargeObjectSheet_50.png", SharedData.defaultDirectory() + "\\Textures\\"
				+ "LargeObjectSheet_50.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultLargeObjectSheet_100.png", SharedData.defaultDirectory() + "\\Textures\\"
				+ "LargeObjectSheet_100.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultBWIconSheet.png", SharedData.defaultDirectory() + "\\Textures\\" + "BWIconSheet.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultOverlayGFXSheet.png", SharedData.defaultDirectory() + "\\Textures\\"
				+ "OverlayGFXSheet.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/DefaultItemIconSheet.png", SharedData.defaultDirectory() + "\\Textures\\" + "ItemIconSheet.png");

		final String[] NPCPaths = {"isogoblin.png", "isozombie.png", "isominotaur.png", "isoskeleton.png"};
		for (int i = 0; i < NPCPaths.length; i++) {
			CopyLocalFileIfNotFoundInAppData("wrap/assets/npcs/" + NPCPaths[i], SharedData.defaultDirectory() + "\\Textures\\NPCS\\" + NPCPaths[i]);
		}

		CopyLocalFileIfNotFoundInAppData("wrap/assets/About.txt", SharedData.defaultDirectory() + "\\" + "About Baneforge.txt");

		CopyLocalFileIfNotFoundInAppData("wrap/assets/LostVillage.vmm", SharedData.defaultDirectory() + "\\Maps\\" + "LostVillage.vmm");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/Shadows at Dawn.bfc", SharedData.defaultDirectory() + "\\Campaigns\\" + "Shadows at Dawn.bfc");

		CopyLocalFileIfNotFoundInAppData("wrap/assets/Wanderer.vhf", SharedData.defaultDirectory() + "\\Heroes\\" + "Wanderer.vhf");

		CopyLocalFileIfNotFoundInAppData("wrap/assets/face1.png", SharedData.defaultDirectory() + "\\Profile Images\\" + "Wise.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/face2.png", SharedData.defaultDirectory() + "\\Profile Images\\" + "Brave.png");
		CopyLocalFileIfNotFoundInAppData("wrap/assets/face3.png", SharedData.defaultDirectory() + "\\Profile Images\\" + "Insane.png");

		CopyLocalFileIfNotFoundInAppData("wrap/assets/greenforest.png", SharedData.defaultDirectory() + "\\Campaign Images\\" + "greenforest.png");

		// lagging
		writeSettingstoFile(false);

		menuMusic_path = "wrap/assets/music/Vast.ogg";
		// if(Math.random() > .5){menuMusic_path =
		// "wrap/assets/music/mind.ogg";}

		// menuMusic_path = "wrap/assets/music/testmusic.ogg";
		menuMusic_url = this.getClass().getClassLoader().getResource(menuMusic_path);

		for (int i = 0; i < 10; i++) {
			Units[i] = new Unit();
		}// initiate the heroes

		for (int i = 0; i < myshareddata.NUMBER_OF_TYPES; i++) {
			myTypeDB[i] = new DB_Types();
		}
		for (int i = 0; i < myUnitModels.length; i++) {
			myUnitModels[i] = new Model();
		}
		for (int i = 0; i < 1000; i++) {
			myAbilityDB[i] = new DB_Abilities();
		}

		for (int i = 0; i < 10; i++) {
			Players[i] = new Player();
		}
		for (int i = 0; i < myquests.length; i++) {
			myquests[i] = new Quest();
		}

		for (int x = 0; x < SharedData.MAP_SIZE; x++) {// make every tile have
														// an empty item
														// container on it!
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				ItemContainers[x][y] = new ItemContainer();
			}
		}

		for (int i = 0; i < 10000; i++) {
			myEffectDB[i] = new DB_Effects();

		}

		for (int i = 0; i < 1000; i++) {
			myOverlayGFX[i] = new OverlayGFX();
		}

		for (int i = 0; i < 100; i++) {
			myConditionDB[i] = new DB_Conditions();
		}

		for (int i = 0; i < 1000; i++) {
			myItemDB[i] = new DB_Items();
		}
		for (int i = 0; i < 1000; i++) {
			myNPCDB[i] = new DB_NPCs();
		}
		for (int i = 0; i < 100; i++) {
			myProjectileModels[i] = new ProjectileModels();
		}
		for (int i = 0; i < 20; i++) {
			Menus[i] = new SimpleGuiObject();
		}
		for (int i = 0; i < DialogMenus.length; i++) {
			DialogMenus[i] = new SimpleGuiObject();
		}

		for (int i = 0; i < 100; i++) {
			myFoundLocalHosts[i] = new HostServerInfo();
			myFoundRemoteHosts[i] = new HostServerInfo();
		}

		for (int i = 0; i < 1000; i++) {
			myEmitters[i] = new ParticleEmitter();
		}

		for (int i = 0; i < 1000; i++) {
			myFloatingText[i] = new FloatingText();
		}

		readSettingsfromFile();

		TryToAutoLogin();

		initMainMenu();

		// try { MenuMusicLoop = new OggClip("wrap/DarkMarchMenuMusic.ogg");}
		// catch (IOException e) {e.printStackTrace();}

		// MenuMusicLoop.loop();

	}

	SimpleGuiObject BackButton[] = new SimpleGuiObject[10];
	SimpleGuiObject ForwardButton[] = new SimpleGuiObject[10];

	TextBox[] myTextBoxes = new TextBox[20];

	Music BackgroundMusic;

	// public JFrame frm = new JFrame();
	// public introPanel intropnl = new introPanel();

	public void initMainMenu() {

		Display.setTitle(WINDOW_TITLE);

		setDisplayMode();

		String[] DisplayIconPaths = {"wrap/assets/misc/bficon16.png", "wrap/assets/misc/bficon32.png", "wrap/assets/misc/bficon128.png"};
		ByteBuffer[] BFIcons = textureLoader.getByteBuffer(DisplayIconPaths);
		Display.setIcon(BFIcons);

		config_UI_sizes_initial();

		playSound("wrap/assets/sounds/introfireball.wav", 20);

		SplashScreenLogo = generate_typeimage_local("gameGUI/infernaltoastlogo.png");
		DrawSplashScreen();

		LoadGameDB();

		LoadGameAssets();

		RandomizeMainMenuHint();

		try {
			BackgroundMusic = new Music(this.getClass().getClassLoader().getResource(menuMusic_path), true);// the
																											// boolean
																											// means
																											// STREAM
			BackgroundMusic.loop();
			BackgroundMusic.setVolume(GetCurrentMusicVolume());

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		 PingAccountTimestampThreaded PingAccountThread = new
	     PingAccountTimestampThreaded(this); PingAccountThread.start();
		 

		MYSQL_FindAvailableServers FindAvailServersThread = new MYSQL_FindAvailableServers(this);
		FindAvailServersThread.start();

		startGameLoop();

	}

	Image2D SplashScreenLogo;

	void DrawSplashScreen() {

		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		GL11.glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		GL11.glDisable(GL_DEPTH_TEST);

		GL11.glMatrixMode(GL_MODELVIEW);

		GL11.glLoadIdentity();

		GL11.glEnable(GL_BLEND);
		GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		// SplashScreenLogo.draw(90, -60);
		int offset_x = SCREEN_WIDTH / 2 - 200;
		int offset_y = SCREEN_HEIGHT / 2 - 250;
		int width = 500;
		int height = 500;

		SplashScreenLogo.bind();
		SplashScreenLogo.begin();
		GL11.glColor3f(1, 1, 1);

		GL11.glBegin(GL11.GL_QUADS);

		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f + offset_x, 0.0f + offset_y);
		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f + offset_x, height + offset_y);
		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(width + offset_x, height + offset_y);
		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(width + offset_x, 0.0f + offset_y);
		GL11.glEnd();

		SplashScreenLogo.end();

		Display.update();

		// this helps reduce the 'not responding' thing
		// assetstreamdone = LoadNextStreamingGameAsset();

	}

	/*
	 * void loop_menu_music(){
	 * 
	 * if(MenuMusicStreamPlayer.looping && MenuMusicStreamPlayer.ready_to_loop){
	 * int savedvolume = MenuMusicStreamPlayer.volume; MenuMusicStreamPlayer =
	 * new AudioStreamPlayer(menuMusic_url); MenuMusicStreamPlayer.start();
	 * MenuMusicStreamPlayer.loop(); MenuMusicStreamPlayer.volume = savedvolume;
	 * } }
	 */

	int mainmenu_smoothcursor_x = 0;
	int mainmenu_smoothcursor_y = 0;
	float frontmenu_affiliation = 0.5f;

	int tinydelta;

	public void menuRendering() {

		// work out how long its been since the last update, this
		// will be used to calculate how far the entities should
		// move this loop
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

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		glDisable(GL_DEPTH_TEST);

		glMatrixMode(GL_MODELVIEW);

		glLoadIdentity();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		boolean draw_back_button = false;
		boolean draw_front_button = false;

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		if (current_MainMenu_Screen == MENUSCREEN_FRONT) {

			boolean mouse_over_hero = false;
			boolean mouse_over_fm = false;
			if (cursor.x <= SCREEN_WIDTH / 2) {
				mouse_over_hero = true;
			} else {
				mouse_over_fm = true;
			}

			float offset_x = 0;
			float offset_y = 0;
			offset_x = -(SCREEN_WIDTH / 8) - ((mainmenu_smoothcursor_x - (SCREEN_WIDTH / 2)) / 8);
			offset_y = -(SCREEN_HEIGHT / 4) - ((mainmenu_smoothcursor_y - (SCREEN_HEIGHT / 2)) / 8);

			float width = (float) (SCREEN_WIDTH * 2);
			float height = (float) (SCREEN_HEIGHT * 1.4);

			glEnable(GL_TEXTURE_2D);

			MainMenuBG1.bind();
			MainMenuBG1.begin();
			GL11.glColor4f(.8f, .8f, .8f, .5f);

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width + offset_x, 0.0f + offset_y);
			GL11.glEnd();

			MainMenuBG1.end();

			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			if (!mouse_over_hero) {
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
			}

			offset_x = (SCREEN_WIDTH / 2) - ((cursor.x - (SCREEN_WIDTH / 2)) / 32) - 260 - 170 + MainMenu_tween_x;
			offset_y = (SCREEN_HEIGHT / 2) - ((cursor.y - (SCREEN_HEIGHT / 2)) / 32) - 170 + MainMenu_tween_y;

			height = MainMenu_HeroImage.getHeight();
			width = MainMenu_HeroImage.getWidth();

			if (!mouse_over_hero) {
				width = width * 0.8f;
				offset_x += 10;
			}
			if (!mouse_over_hero) {
				height = height * 0.8f;
				offset_y += 20;
			}

			MainMenu_HeroImage.bind();
			MainMenu_HeroImage.begin();

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);

			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 0.0f);

			glVertex2f(width + offset_x, 0.0f + offset_y);
			GL11.glEnd();
			MainMenu_HeroImage.end();

			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			if (!mouse_over_fm) {
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
			}

			offset_x = (SCREEN_WIDTH / 2) - ((cursor.x - (SCREEN_WIDTH / 2)) / 32) + 260 - 170 + MainMenu_tween_x;
			offset_y = (SCREEN_HEIGHT / 2) - ((cursor.y - (SCREEN_HEIGHT / 2)) / 32) - 170 + MainMenu_tween_y;

			height = MainMenu_HostImage.getHeight();
			width = MainMenu_HostImage.getWidth();

			if (!mouse_over_fm) {
				width = width * 0.8f;
				offset_x += 10;
			}
			if (!mouse_over_fm) {
				height = height * 0.8f;
				offset_y += 20;
			}

			MainMenu_HostImage.bind();
			MainMenu_HostImage.begin();

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);

			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);

			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width + offset_x, 0.0f + offset_y);

			GL11.glEnd();

			MainMenu_HostImage.end();

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			offset_x = (SCREEN_WIDTH / 2) - ((cursor.x - (SCREEN_WIDTH / 2)) / 16) - 130 + MainMenu_tween_x;
			offset_y = (SCREEN_HEIGHT / 2) - ((cursor.y - (SCREEN_HEIGHT / 2)) / 16) - 30 + MainMenu_tween_y;
			height = MainMenu_Text1.getHeight();
			width = MainMenu_Text1.getWidth();
			MainMenu_Text1.bind();
			MainMenu_Text1.begin();

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);

			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);

			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width + offset_x, 0.0f + offset_y);

			GL11.glEnd();
			MainMenu_Text1.end();

			offset_x = (SCREEN_WIDTH / 2) - ((cursor.x - (SCREEN_WIDTH / 2)) / 16) - 62 + MainMenu_tween_x;
			offset_y = (SCREEN_HEIGHT / 2) - ((cursor.y - (SCREEN_HEIGHT / 2)) / 16) + 0 + MainMenu_tween_y;
			height = MainMenu_HeroText.getHeight();
			width = MainMenu_HeroText.getWidth();

			Image2D text_texture = MainMenu_HostText;
			if (mouse_over_hero) {
				text_texture = MainMenu_HeroText;

			}

			text_texture.bind();
			text_texture.begin();

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);

			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);

			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width + offset_x, 0.0f + offset_y);

			GL11.glEnd();
			text_texture.end();

			offset_x = (SCREEN_WIDTH / 2) - ((cursor.x - (SCREEN_WIDTH / 2)) / 16) - 110 + MainMenu_tween_x;
			offset_y = (SCREEN_HEIGHT / 2) - ((cursor.y - (SCREEN_HEIGHT / 2)) / 16) + 60 + MainMenu_tween_y;

			for (int i = 0; i < 3; i++) {

				if (mouse_over_hero) {
					GL11.glColor4f(1, 1, 1, 1f - frontmenu_affiliation - i * 0.2f + 0.1f);
				} else {
					GL11.glColor4f(1, 1, 1, frontmenu_affiliation - i * 0.2f + 0.1f);
				}

				if (mouse_over_hero) {
					Verdana_14.drawString((int) offset_x, (int) offset_y + 20 * i, "- " + MainMenuHeroHintText[i]);
				} else {
					Verdana_14.drawString((int) offset_x, (int) offset_y + 20 * i, "- " + MainMenuHostHintText[i]);
				}

			}

			// frontmenu_affiliation
			if (mouse_over_hero && frontmenu_affiliation > 0) {
				frontmenu_affiliation -= 0.01f;
			}
			if (!mouse_over_hero && frontmenu_affiliation < 1) {
				frontmenu_affiliation += 0.01f;
			}

			float r = (1 - frontmenu_affiliation) * (0.2f) + frontmenu_affiliation * (0.1f);
			float g = (1 - frontmenu_affiliation) * (0.7f) + frontmenu_affiliation * (0.4f);
			float b = (1 - frontmenu_affiliation) * (0.2f) + frontmenu_affiliation * (0.7f);
			GL11.glColor3f(r, g, b);

			// if(mouse_over_hero){GL11.glColor3f(0.2f, 0.8f,
			// 0.2f);}else{GL11.glColor3f(0.1f, 0.4f,
			// 0.8f);/*GL11.glColor3f(0.8f, 0.4f, 0.1f);*/}

			intropnl_logo.draw(SCREEN_WIDTH / 2 - 200 + MainMenu_tween_x, 30 + MainMenu_tween_y);

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (cursor.x >= SCREEN_WIDTH - 40 && cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			// if(BackgroundMusic.getVolume() > 0 ){volume_on.draw(SCREEN_WIDTH
			// - 30, 15);}else{volume_off.draw(SCREEN_WIDTH - 30, 15);}
			smallgear.draw(SCREEN_WIDTH - 35, 12);

			if (cursor.x < SCREEN_WIDTH - 40 && cursor.x > SCREEN_WIDTH - 70 && cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			smallfolder.draw(SCREEN_WIDTH - 60, 15);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (cursor.x < SCREEN_WIDTH - 70 && cursor.x > SCREEN_WIDTH - 95 && cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			editicon.draw_FitToSize(SCREEN_WIDTH - 95, 10, 30);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (cursor.x < SCREEN_WIDTH - 95 && cursor.x > SCREEN_WIDTH - 125 && cursor.y < 50) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			} else {
				GL11.glColor3f(0.4f, 0.4f, 0.4f);
			}
			bookicon.draw_FitToSize(SCREEN_WIDTH - 125, 10, 30);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			
			
			
			DrawMiniHoverTooltip(displayedMiniHovertooltip);

		}

		if (current_MainMenu_Screen == MENUSCREEN_CHOOSEHERO) {
			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			Verdana_20.drawString(SCREEN_WIDTH / 2 - 50 + MainMenu_tween_x, 30 + MainMenu_tween_y, "Choose a Character");

			for (int i = 0; i < 1000; i++) {
				myHeroFiles[i] = null;
			}

			chooseheromenu_number_of_found_herofiles = 0;

			File folder = new File(SharedData.defaultDirectory() + "\\Heroes\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {
				// if
				// (listOfFile.isDirectory()){System.out.println(listOfFile.getName());}
				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".vhf")) {
					myHeroFiles[chooseheromenu_number_of_found_herofiles] = listOfFile.getName();
					// System.out.println(listOfFile.getName());
					chooseheromenu_number_of_found_herofiles++;
				}
			}

			if (chooseheromenu_number_of_found_herofiles < 8) {
				chooseheromenu_herofileoffset = 0;
			}

			// if(RunningInGoldMode()){

			if (chooseheromenu_number_of_found_herofiles == 0) {
				queued_MainMenu_Screen = MENUSCREEN_NEWHERO;
				ChangeMainMenuScreen();
			}

			for (int j = 0; j < 8; j++) {
				int i = j + chooseheromenu_herofileoffset;
				if (i < chooseheromenu_number_of_found_herofiles + 1) {

					int x = SCREEN_WIDTH / 2 - 300 + MainMenu_tween_x;
					int y = 100 + 60 * j + MainMenu_tween_y;

					if (i != 0) {
						if (Players[myPNum].name.equals(myHeroFiles[i - 1].substring(0, myHeroFiles[i - 1].length() - 4))) {
							GL11.glColor3f(0.2f, 1.0f, 0.2f);
						}
					}
					Outline_Rect(x, y, 300, 50);
					GL11.glColor3f(1.0f, 1.0f, 1.0f);

					if (i == 0) {
						GL11.glColor3f(0.2f, 1.0f, 0.2f);
						Verdana_20.drawString(x + 10, y + 10, "Create New Hero");
						GL11.glColor3f(1.0f, 1.0f, 1.0f);
					} else {
						Verdana_20.drawString(x + 10, y + 10, myHeroFiles[i - 1].substring(0, myHeroFiles[i - 1].length() - 4));
					}
				}
			}

			/*
			 * }else{ GL11.glColor3f(1.0f, 1.0f, 1.0f);
			 * //Verdana_20.drawString(SCREEN_WIDTH / 2 - 250 +
			 * MainMenu_tween_x, 300 + MainMenu_tween_y,
			 * "Log in with a Gold Account to play with custom heroes.");
			 * HeroPromo.draw(SCREEN_WIDTH / 2 - 350 + MainMenu_tween_x, 120 +
			 * MainMenu_tween_y); }
			 */

			if (Players[myPNum].name.length() > 0) {

				int x = SCREEN_WIDTH / 2 + 100 + MainMenu_tween_x;
				int y = 120 + MainMenu_tween_y;

				if (ProfileImages[0] != null) {
					ProfileImages[0].draw(x + MainMenu_tween_x, y + 40 + MainMenu_tween_y);
				}

				DrawUnitModel(myPNum, x + 150, y + 70);

				int pixellength = Verdana_20.getStringPixelLength(Players[myPNum].name);
				Verdana_20.drawString(x + 60 - (pixellength / 2), y + 10, Players[myPNum].name);

				Verdana_16.drawString(x + 10, y + 320, Players[myPNum].herodescription, 300, 400, 20);

				for (int i = 0; i < ChooseHeroMenu_HeroStatstoDraw.length; i++) {
					Verdana_16.drawString(x + 10 + 150 * (i / 5), y + 190 + 20 * (i % 5), ChooseHeroMenu_HeroStatNamestoDraw[i] + ":  "
							+ Units[myPNum].stat[ChooseHeroMenu_HeroStatstoDraw[i]]);

				}
				draw_front_button = true;

			}
			draw_back_button = true;

		}

		if (current_MainMenu_Screen == MENUSCREEN_NEWHERO) {

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			Verdana_20.drawString(SCREEN_WIDTH / 2 - 90 + MainMenu_tween_x, 30 + MainMenu_tween_y, "Create a New Character");

			// String[] TBDescrip =
			// {"Character Name:","Race:","Hometown:","Height:","Weight:"};

			int alignbuttons_x = SCREEN_WIDTH / 2 - 420 + MainMenu_tween_x;
			int alignbuttons_y = 340 + MainMenu_tween_y;

			if (newhero_align1 == 0) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 15, alignbuttons_y + 0, "Lawful");
			if (newhero_align1 == 1) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 100, alignbuttons_y + 0, "Neutral");
			if (newhero_align1 == 2) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 190, alignbuttons_y + 0, "Chaotic");

			if (newhero_align2 == 0) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 20, alignbuttons_y + 40, "Good");
			if (newhero_align2 == 1) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 100, alignbuttons_y + 40, "Neutral");
			if (newhero_align2 == 2) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			Verdana_16.drawString(alignbuttons_x + 200, alignbuttons_y + 40, "Evil");

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (ProfileImages[0] == null) {
				ProfileImage_blank.draw(SCREEN_WIDTH / 2 - 55 + MainMenu_tween_x, 100 + MainMenu_tween_y);
			} else {
				ProfileImages[0].draw(SCREEN_WIDTH / 2 - 55 + MainMenu_tween_x, 100 + MainMenu_tween_y);
			}

			// draw profileimage folder files

			GL11.glColor3f(1, 1, 1);
			Verdana_16.drawString(SCREEN_WIDTH / 2 - 40 + MainMenu_tween_x, 234 + MainMenu_tween_y, "Profile Image");

			for (int i = 0; i < 1000; i++) {
				myProfileImageFiles[i] = null;
			}

			chooseheromenu_number_of_found_profileimagefiles = 0;

			File folder = new File(SharedData.defaultDirectory() + "\\Profile Images\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {
				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".png")) {
					myProfileImageFiles[chooseheromenu_number_of_found_profileimagefiles] = listOfFile.getName();

					chooseheromenu_number_of_found_profileimagefiles++;
				}
			}

			if (chooseheromenu_number_of_found_profileimagefiles < 8) {
				chooseheromenu_profileimagefileoffset = 0;
			}

			for (int j = 0; j < 8; j++) {
				int i = j + chooseheromenu_profileimagefileoffset;
				if (i < chooseheromenu_number_of_found_profileimagefiles + 0) {

					int x = SCREEN_WIDTH / 2 - 140 + MainMenu_tween_x;
					int y = 260 + 60 * j + MainMenu_tween_y;

					// if (i != 0) {

					String path = "";
					if (lastCustomProfileImagePath != null) {
						path = lastCustomProfileImagePath.substring(lastCustomProfileImagePath.lastIndexOf("\\") + 1);
					}

					// debug(path);debug(myProfileImageFiles[i]);
					if (path.equals(myProfileImageFiles[i])) {
						GL11.glColor3f(0.2f, 1.0f, 0.2f);
					}

					Outline_Rect(x, y, 300, 50);
					GL11.glColor3f(1.0f, 1.0f, 1.0f);

					Verdana_20.drawString(x + 10, y + 10, myProfileImageFiles[i].substring(0, myProfileImageFiles[i].length() - 4));

				}

			}

			GL11.glColor3f(1, 1, 1);
			Verdana_16.drawString(SCREEN_WIDTH / 2 + 245 + MainMenu_tween_x, 70 + MainMenu_tween_y, "Basic Stats");

			// basestat choosers
			for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {
				int pos_x = SCREEN_WIDTH / 2 + 200 + MainMenu_tween_x;
				int pos_y = 110 + 40 * (i % 8) + MainMenu_tween_y;

				GL11.glColor3f(0.1f, 0.1f, 0.1f);
				Outline_Rect(pos_x - 10, pos_y - 8, 200, 40);
				GL11.glColor3f(1.0f, 1.0f, 1.0f);

				int stat = i + SharedData.ENDURANCE;
				int string_pos = GetStatStringIndex(stat);// this number may
															// have to change!

				Verdana_16.drawString(pos_x, pos_y, SharedData.ComboStrings[SharedData.COMBOTEXT_STATS][string_pos] + ":");
				Verdana_16.drawString(pos_x + 150, pos_y, "" + default_hero_stats[stat]);

			}
			GL11.glColor3f(0.3f, 1f, 0.3f);
			Verdana_16.drawString(SCREEN_WIDTH / 2 + 245 + MainMenu_tween_x, 110 + 40 * 8 + MainMenu_tween_y, "Points Left: " + newhero_pointsleft);

			GL11.glColor3f(1, 1, 1);

			draw_back_button = true;

			draw_front_button = true;

			for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {

				int pos_x = SCREEN_WIDTH / 2 + 200 + MainMenu_tween_x;
				int pos_y = 110 + 40 * (i % 8) + MainMenu_tween_y;

				if (cursor.x > pos_x && cursor.x < pos_x + 200 && cursor.y > pos_y && cursor.y < pos_y + 40) {

					DrawGenericHoverTooltip(SharedData.BaseStatDescriptions[i]);

				}

			}

		}

		if (current_MainMenu_Screen == MENUSCREEN_JOINSERVER) {

			if (serverbrowser_mode == 0) {
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
				button_localserverbrowser.draw(SCREEN_WIDTH / 2 - 250 + MainMenu_tween_x, 20 + MainMenu_tween_y);
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
				button_remoteserverbrowserGS.draw(SCREEN_WIDTH / 2 + 0 + MainMenu_tween_x, 20 + MainMenu_tween_y);
				// Verdana_18.drawString(SCREEN_WIDTH / 2 - 80 +
				// MainMenu_tween_x, 50 + MainMenu_tween_y,
				// "Local Server Browser");
			} else {
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
				button_localserverbrowserGS.draw(SCREEN_WIDTH / 2 - 250 + MainMenu_tween_x, 20 + MainMenu_tween_y);
				GL11.glColor3f(1, 1, 1);
				button_remoteserverbrowser.draw(SCREEN_WIDTH / 2 + 0 + MainMenu_tween_x, 20 + MainMenu_tween_y);
				// Verdana_18.drawString(SCREEN_WIDTH / 2 - 85 +
				// MainMenu_tween_x, 50 + MainMenu_tween_y,
				// "Remote Server Browser");
			}

			GL11.glColor3f(1, 1, 1);

			int x = SCREEN_WIDTH / 2 - 300 + MainMenu_tween_x;
			int y = 100 + MainMenu_tween_y;

			Outline_Rect(x, y, 600, SCREEN_HEIGHT - 400);

			if (serverbrowser_mode == 0) {
				for (int i = 0; i < 3; i++) {

					int y_offset = 0;
					if (myFoundLocalHosts[i].isActive) {
						y_offset = 80 * i;

						GL11.glColor3f(0.3f, 1.0f, 0.3f);
						// Verdana_14.drawString(x + 70, y + 20 + y_offset,
						// "Server " + (i + 1));
						Verdana_14.drawString(x + 70, y + 20 + y_offset, myFoundLocalHosts[i].name);
						GL11.glColor3f(1.0f, 1.0f, 1.0f);

						Verdana_12.drawString(x + 50, y + 50 + y_offset, "IP Address:");
						Verdana_14.drawString(x + 50, y + 70 + y_offset, myFoundLocalHosts[i].IPAddress);

						Verdana_12.drawString(x + 190, y + 50 + y_offset, "Port: ");
						Verdana_14.drawString(x + 190, y + 70 + y_offset, "" + myFoundLocalHosts[i].port);

						if (myFoundLocalHosts[i].IPAddress.equals(myTextBoxes[0].text)) {
							GL11.glColor3f(0.1f, 0.3f, 0.1f);
						} else {
							GL11.glColor3f(0.1f, 0.1f, 0.1f);
						}
						Outline_Rect(x + 30, y + 15 + y_offset, 600 - 60, 100);
					}

				}
			}

			if (serverbrowser_mode == 1) {// remote servers

				int num_servers = (SCREEN_HEIGHT - 400) / 40;

				int y_offset = 0;
				for (int i = 0; i < num_servers; i++) {

					if (myFoundRemoteHosts[i].isActive) {

						GL11.glColor3f(0.3f, 0.3f, 1.0f);
						Verdana_14.drawString(x + 70, y + 20 + y_offset, myFoundRemoteHosts[i].name);
						GL11.glColor3f(1.0f, 1.0f, 1.0f);

						Verdana_12.drawString(x + 320, y + 20 + y_offset, "IP Address:");
						Verdana_14.drawString(x + 320, y + 20 + 20 + y_offset, myFoundRemoteHosts[i].IPAddress);

						Verdana_12.drawString(x + 490, y + 20 + y_offset, "Port: ");
						Verdana_14.drawString(x + 490, y + 20 + 20 + y_offset, "" + myFoundRemoteHosts[i].port);

						if (myFoundRemoteHosts[i].IPAddress.equals(myTextBoxes[0].text)) {
							GL11.glColor3f(0.1f, 0.1f, 0.3f);
						} else {
							GL11.glColor3f(0.1f, 0.1f, 0.1f);
						}
						Outline_Rect(x + 30, y + 15 + y_offset, 600 - 60, 40);

						y_offset += 40;
					}

				}

			}

			draw_back_button = true;

			draw_front_button = true;

		}

		if (current_MainMenu_Screen == MENUSCREEN_CHOOSECAMPAIGN) {

			GL11.glColor3f(1, 1, 1);

			float offset_x = 0;
			float offset_y = 0;
			offset_x = -(SCREEN_WIDTH / 8);
			offset_y = -(SCREEN_HEIGHT / 4);

			float width = (float) (SCREEN_WIDTH * 2);
			float height = (float) (SCREEN_HEIGHT * 1.4);

			//This causes the game to crash?
			MainMenuBG2.bind();
			MainMenuBG2.begin();
			GL11.glColor3f(0.2f, 0.2f, 0.2f);

			GL11.glBegin(GL11.GL_QUADS);

			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(0.0f + offset_x, 0.0f + offset_y);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(0.0f + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width + offset_x, height + offset_y);
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width + offset_x, 0.0f + offset_y);
			GL11.glEnd();

			MainMenuBG2.end();

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			for (int i = 0; i < 1000; i++) {
				myCampaignFiles[i] = null;
			}

			choosecampaignmenu_number_of_found_files = 0;

			File folder = new File(SharedData.defaultDirectory() + "\\Campaigns\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {
				// if
				// (listOfFile.isDirectory()){System.out.println(listOfFile.getName());}
				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".bfc")) {
					myCampaignFiles[choosecampaignmenu_number_of_found_files] = listOfFile.getName();
					// System.out.println(listOfFile.getName());
					choosecampaignmenu_number_of_found_files++;
				}
			}

			if (choosecampaignmenu_number_of_found_files == 0) {
				queued_MainMenu_Screen = MENUSCREEN_NEWCAMPAIGN;
				ChangeMainMenuScreen();
			}

			if (choosecampaignmenu_number_of_found_files < 8) {
				choosecampaignmenu_campaignfileoffset = 0;
			}

			for (int j = 0; j < 8; j++) {
				int i = j + choosecampaignmenu_campaignfileoffset;
				if (i < choosecampaignmenu_number_of_found_files + 1) {

					int x = SCREEN_WIDTH / 2 - 300 + MainMenu_tween_x;
					int y = 100 + 60 * j + MainMenu_tween_y;

					if (i != 0) {
						String path = "";
						if (lastCampaignFilePath != null) {
							path = lastCampaignFilePath.substring(lastCampaignFilePath.lastIndexOf("\\") + 1);
						}
						if (path.equals(myCampaignFiles[i - 1])) {
							GL11.glColor3f(0.2f, 1.0f, 0.2f);
						}
					}
					Outline_Rect(x, y, 300, 50);
					GL11.glColor3f(1.0f, 1.0f, 1.0f);

					if (i == 0) {
						GL11.glColor3f(0.2f, 1.0f, 0.2f);
						Verdana_20.drawString(x + 10, y + 10, "Start New Campaign");
						GL11.glColor3f(1.0f, 1.0f, 1.0f);
					} else {
						Verdana_20.drawString(x + 10, y + 10, myCampaignFiles[i - 1].substring(0, myCampaignFiles[i - 1].length() - 4));
					}
				}
			}

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			Verdana_20.drawString(SCREEN_WIDTH / 2 - 40 + MainMenu_tween_x, 30 + MainMenu_tween_y, "Choose a Campaign");

			if (ServerOpenToPublic) {
				button_publicgame.draw(SCREEN_WIDTH / 2 + 90 + MainMenu_tween_x, SCREEN_HEIGHT - 110 + MainMenu_tween_y);
				Verdana_14.drawString(SCREEN_WIDTH / 2 - 140, SCREEN_HEIGHT - 40,
						"(Port must be forwarded to your local IP Address in your router settings)");

			} else {
				button_privategame.draw(SCREEN_WIDTH / 2 + 90 + MainMenu_tween_x, SCREEN_HEIGHT - 110 + MainMenu_tween_y);
			}

			draw_back_button = true;

			if (hostpnl_MapIsLoaded) {

				int minimap_x = SCREEN_WIDTH / 2 + 140 + MainMenu_tween_x;
				int minimap_y = 300 + MainMenu_tween_y;

				lastCampaignImage.draw(SCREEN_WIDTH / 2 + 40 + MainMenu_tween_x, 110 + MainMenu_tween_y);

				Verdana_20.drawString(SCREEN_WIDTH / 2 + 190 + MainMenu_tween_x, 110 + MainMenu_tween_y, CampaignName);
				Verdana_16.drawString(SCREEN_WIDTH / 2 + 190 + MainMenu_tween_x, 140 + MainMenu_tween_y, CampaignDescrip, 200, 200, 20);

				// draw tiles
				glDisable(GL_TEXTURE_2D);
				GL11.glBegin(GL11.GL_QUADS);

				// Minimap
				for (int y = 0; y < SharedData.MAP_SIZE; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE; x++) {

						int pixel_size = 2;

						int mmx = minimap_x + (x * pixel_size);
						int mmy = minimap_y + (y * pixel_size);

						int layer = SharedData.TERRAIN_LAYER_HIGHER;
						if (map[layer][y][x] == 0) {
							layer = SharedData.TERRAIN_LAYER_LOWER;
						}

						float r = (float) myTypeDB[map[layer][y][x]].minimapcolors[0] / 255;
						float g = (float) myTypeDB[map[layer][y][x]].minimapcolors[1] / 255;
						float b = (float) myTypeDB[map[layer][y][x]].minimapcolors[2] / 255;

						float alpha = (float) lit[y * 4][x * 4];
						alpha = 1;
						GL11.glColor4f(r, g, b, alpha);

						GL11.glVertex2f(mmx, mmy);
						GL11.glVertex2f((mmx) + pixel_size, mmy);
						GL11.glVertex2f((mmx) + pixel_size, (mmy) + pixel_size);
						GL11.glVertex2f(mmx, (mmy) + pixel_size);

					}
				}

				GL11.glEnd();// end Quads

				glEnable(GL_TEXTURE_2D);

				draw_front_button = true;

				/*
				 * // title if (mapName != null && !mapName.equals("null")) {
				 * int pixellength = Verdana_20.getStringPixelLength(mapName);
				 * Verdana_20.drawString(minimap_x + 100 - (pixellength / 2),
				 * minimap_y - 50, mapName); }
				 * 
				 * // description
				 * 
				 * if (mapDescrip != null && !mapDescrip.equals("null")) {
				 * Verdana_14.drawString(minimap_x + 5, minimap_y + 200 + 50,
				 * mapDescrip, 200,400, 20); }
				 */

				minimappreview_border.draw(minimap_x - 5, minimap_y - 5);

			} else {
				// minimappreview_striped.draw(SCREEN_WIDTH/2 + 100, 50);

			}
			// minimappreview_border.draw(SCREEN_WIDTH/2 + 100 - 5, 50 - 5);

		}

		if (current_MainMenu_Screen == MENUSCREEN_NEWCAMPAIGN) {

			Verdana_20.drawString(SCREEN_WIDTH / 2 - 40 + MainMenu_tween_x, 30 + MainMenu_tween_y, "New Campaign");

			Verdana_16.drawString(SCREEN_WIDTH / 2 - 270 + MainMenu_tween_x, 270 + MainMenu_tween_y, "Maps");

			for (int i = 0; i < 1000; i++) {
				myMapFiles[i] = null;
			}

			choosemapmenu_number_of_found_files = 0;

			File folder = new File(SharedData.defaultDirectory() + "\\Maps\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {
				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".vmm")) {
					myMapFiles[choosemapmenu_number_of_found_files] = listOfFile.getName();

					choosemapmenu_number_of_found_files++;
				}
			}

			if (choosemapmenu_number_of_found_files < 8) {
				choosemapmenu_mapfileoffset = 0;
			}

			for (int j = 0; j < 8; j++) {
				int i = j + choosemapmenu_mapfileoffset;
				if (i < choosemapmenu_number_of_found_files + 0) {

					int x = SCREEN_WIDTH / 2 - 400 + MainMenu_tween_x;
					int y = 300 + 60 * j + MainMenu_tween_y;

					// if (i != 0) {

					String path = "";
					if (lastMapFilePath != null) {
						path = lastMapFilePath.substring(lastMapFilePath.lastIndexOf("\\") + 1);
					}

					if (path.equals(myMapFiles[i])) {
						GL11.glColor3f(0.2f, 1.0f, 0.2f);
					}

					// }
					Outline_Rect(x, y, 300, 50);
					GL11.glColor3f(1.0f, 1.0f, 1.0f);

					Verdana_20.drawString(x + 10, y + 10, myMapFiles[i].substring(0, myMapFiles[i].length() - 4));
					// }
				}
			}

			if (hostpnl_MapIsLoaded) {

				// draw tiles
				glDisable(GL_TEXTURE_2D);
				GL11.glBegin(GL11.GL_QUADS);

				int minimap_x = SCREEN_WIDTH / 2 - 350 + MainMenu_tween_x;
				int minimap_y = 40 + MainMenu_tween_y;

				// Minimap
				for (int y = 0; y < SharedData.MAP_SIZE; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE; x++) {

						int pixel_size = 2;

						int mmx = minimap_x + (x * pixel_size);
						int mmy = minimap_y + (y * pixel_size);

						int layer = myshareddata.TERRAIN_LAYER_HIGHER;
						if (map[layer][y][x] == 0) {
							layer = myshareddata.TERRAIN_LAYER_LOWER;
						}

						float r = (float) myTypeDB[map[layer][y][x]].minimapcolors[0] / 255;
						float g = (float) myTypeDB[map[layer][y][x]].minimapcolors[1] / 255;
						float b = (float) myTypeDB[map[layer][y][x]].minimapcolors[2] / 255;

						float alpha = (float) lit[y * 4][x * 4];
						alpha = 1;
						GL11.glColor4f(r, g, b, alpha);

						GL11.glVertex2f(mmx, mmy);
						GL11.glVertex2f((mmx) + pixel_size, mmy);
						GL11.glVertex2f((mmx) + pixel_size, (mmy) + pixel_size);
						GL11.glVertex2f(mmx, (mmy) + pixel_size);

					}
				}

				GL11.glEnd();// end Quads
				glEnable(GL_TEXTURE_2D);
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
				// title
				if (mapName != null && !mapName.equals("null")) {
					int pixellength = Verdana_20.getStringPixelLength(mapName);
					Verdana_20.drawString(minimap_x + 340 - (pixellength / 2), minimap_y + 200, mapName);
				}

				// description

				if (mapDescrip != null && !mapDescrip.equals("null")) {
					Verdana_14.drawString(minimap_x + 260, minimap_y + 230, mapDescrip, 160, 300, 20);
				}

				minimappreview_border.draw(minimap_x - 5, minimap_y - 5);

			}

			GL11.glColor3f(1, 1, 1);

			Verdana_16.drawString(SCREEN_WIDTH / 2 + 190 + MainMenu_tween_x, 360 + MainMenu_tween_y, "Splash Image");

			for (int i = 0; i < 1000; i++) {
				myCampaignImageFiles[i] = null;
			}

			choosemapmenu_number_of_found_images = 0;

			folder = new File(SharedData.defaultDirectory() + "\\Campaign Images\\");
			listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {
				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".png")) {
					myCampaignImageFiles[choosemapmenu_number_of_found_images] = listOfFile.getName();

					choosemapmenu_number_of_found_images++;
				}
			}

			if (choosemapmenu_number_of_found_images < 8) {
				choosemapmenu_imagefileoffset = 0;
			}

			for (int j = 0; j < 8; j++) {
				int i = j + choosemapmenu_imagefileoffset;
				if (i < choosemapmenu_number_of_found_images + 0) {

					int x = SCREEN_WIDTH / 2 + 80 + MainMenu_tween_x;
					int y = 400 + 60 * j + MainMenu_tween_y;

					// if (i != 0) {

					String path = "";
					if (lastCampaignImageFilePath != null) {
						path = lastCampaignImageFilePath.substring(lastCampaignImageFilePath.lastIndexOf("\\") + 1);
					}

					if (path.equals(myCampaignImageFiles[i])) {
						GL11.glColor3f(0.2f, 1.0f, 0.2f);
					}

					// }
					Outline_Rect(x, y, 300, 50);
					GL11.glColor3f(1.0f, 1.0f, 1.0f);

					Verdana_20.drawString(x + 10, y + 10, myCampaignImageFiles[i].substring(0, myCampaignImageFiles[i].length() - 4));
					// }
				}
			}

			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			if (lastCampaignImage != null) {
				lastCampaignImage.draw(SCREEN_WIDTH / 2 - 70 + MainMenu_tween_x, 100 + MainMenu_tween_y);
			}

			DrawSideButton(BackButton[MENUSCREEN_NEWCAMPAIGN]);

			boolean allowedtosave = true;
			if (myTextBoxes[TEXTBOX_CAMPAIGNNAME].text.equals("")) {
				allowedtosave = false;
			}

			for (int i = 0; i < 1000; i++) {
				if (myCampaignFiles[i] != null) {
					if (myTextBoxes[TEXTBOX_CAMPAIGNNAME].text.equals(myCampaignFiles[i].substring(0, myCampaignFiles[i].length() - 4))) {
						allowedtosave = false;
					}
				}
			}

			if (allowedtosave && hostpnl_MapIsLoaded) {

				draw_front_button = true;
			}

		}

		if (current_MainMenu_Screen == MENUSCREEN_LOADSCREEN) {// loading screen

			loadscreen_blueprint.draw(SCREEN_WIDTH / 2 - 500, SCREEN_HEIGHT / 2 - 312); // why
																						// does
																						// this
																						// move?

			if (!finished_map_download) {
				loadscreen_stagetext[loadscreen_currentstage].draw(SCREEN_WIDTH / 2 - 250, SCREEN_HEIGHT / 2 - 310);
			} else {

				float alpha = (Math.abs((animationframe % 32) - 24)) / 34.0f;

				GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.2f + alpha);

				loadscreen_continue.draw(SCREEN_WIDTH / 2 - 500, SCREEN_HEIGHT / 2 - 312);

				// DrawSideButton(ForwardButton[MENUSCREEN_NEWCAMPAIGN]);

			}

		}

		GL11.glColor3f(1, 1, 1);

		DrawLoginMenu();

		GL11.glColor3f(1, 1, 1);// reset color

		if (Menus[MENU_ESC].is_open) {// escmenu
			DrawESCMenu();
		}

		for (int i = 0; i < myTextBoxes.length; i++) {
			if ((myTextBoxes[i].menuscreen == -1 || myTextBoxes[i].menuscreen == current_MainMenu_Screen)
					&& (myTextBoxes[i].menu == -1 || Menus[myTextBoxes[i].menu].is_open)) {
				DrawTextBox(i);
			}

		}

		if (draw_back_button) {
			DrawSideButton(BackButton[current_MainMenu_Screen]);
		}

		if (draw_front_button) {
			DrawSideButton(ForwardButton[current_MainMenu_Screen]);
		}

		float alpha = (float) SleekMessageBox.tweencount / 800f;
		if (SleekMessageBox.y == (SCREEN_HEIGHT / 2 - 200)) {
			alpha = 1f;
		}
		GL11.glColor4f(0.4f, 0.9f, 0.4f, alpha);
		SleekMessageBox.background.draw(SleekMessageBox.x, SleekMessageBox.y);
		GL11.glColor3f(1, 1, 1);
		Verdana_20.drawString(SleekMessageBox.x + SleekMessageBox.width / 2 - (Verdana_20.getStringPixelLength(SleekMessageBox.text)) / 2,
				SleekMessageBox.y + 30, SleekMessageBox.text);

		// /warning popup
		GL11.glColor3f(1, 1, 1);

		if (PopUpMessageTimeLeft > 0) {

			int pixellength = Verdana_16.getStringPixelLength(PopUpMessage);
			GL11.glColor3f(0, 0, 0);
			Fill_Rect(SCREEN_WIDTH / 2 - pixellength / 2 - 20, SCREEN_HEIGHT / 2 - 100 - 5, pixellength + 40, 30);
			GL11.glColor3f(1, 1, 1);
			Outline_Rect(SCREEN_WIDTH / 2 - pixellength / 2 - 20, SCREEN_HEIGHT / 2 - 100 - 5, pixellength + 40, 30);
			Verdana_16.drawString(SCREEN_WIDTH / 2 - pixellength / 2, SCREEN_HEIGHT / 2 - 100, PopUpMessage);

		}

	}

	public void pollInput_MenuMouse() {

		
		displayedMiniHovertooltip = "";
		
		
		int mybuffer = 10;
		int myrate = 2;
		if (cursor.x > mainmenu_smoothcursor_x + mybuffer) {
			mainmenu_smoothcursor_x += myrate;
		}
		if (cursor.x < mainmenu_smoothcursor_x - mybuffer) {
			mainmenu_smoothcursor_x -= myrate;
		}

		if (cursor.y > mainmenu_smoothcursor_y + mybuffer) {
			mainmenu_smoothcursor_y += myrate;
		}
		if (cursor.y < mainmenu_smoothcursor_y - mybuffer) {
			mainmenu_smoothcursor_y -= myrate;
		}

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			if (cursor.x - menu_dragoffset_x > -200 && cursor.x - menu_dragoffset_x < SCREEN_WIDTH - 250 && cursor.y - menu_dragoffset_y > 10
					&& cursor.y - menu_dragoffset_y < SCREEN_HEIGHT - 50) {

				if (menu_beingDragged == MENU_ESC) {
					Menus[MENU_ESC].x = cursor.x - menu_dragoffset_x;
					Menus[MENU_ESC].y = cursor.y - menu_dragoffset_y;
				}

			}

		}

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

				// focus of all textboxes
				for (int i = 0; i < myTextBoxes.length; i++) {

					if (cursor.x > myTextBoxes[i].x && cursor.x < myTextBoxes[i].x + myTextBoxes[i].width && cursor.y > myTextBoxes[i].y
							&& cursor.y < myTextBoxes[i].y + myTextBoxes[i].height
							&& (myTextBoxes[i].menuscreen == -1 || myTextBoxes[i].menuscreen == current_MainMenu_Screen)
							&& (myTextBoxes[i].menu == -1 || Menus[myTextBoxes[i].menu].is_open)) {
						myTextBoxes[i].Has_Focus = true;
					} else {
						myTextBoxes[i].Has_Focus = false;
					}

				}
			}
		}

		
		
		if (!Menus[MENU_ESC].is_open && !Menus[MENU_LOGIN].is_open && (cursor.x > 100 || cursor.y > 50)) {

			if (current_MainMenu_Screen == MENUSCREEN_FRONT) {

				
				
				if (cursor.x >= SCREEN_WIDTH - 40 && cursor.y < 50) {
					displayedMiniHovertooltip = "Settings Menu";
				} else if (cursor.x > SCREEN_WIDTH - 70 && cursor.y < 50) {

					displayedMiniHovertooltip = "Baneforge Folder";

				} else if (cursor.x > SCREEN_WIDTH - 95 && cursor.y < 50) {
					displayedMiniHovertooltip = "Launch Map Editor";
				} else if (cursor.x > SCREEN_WIDTH - 125 && cursor.y < 50) {
					displayedMiniHovertooltip = "Launch Asset Editor";
				}

				
				
				
				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						if (cursor.x >= SCREEN_WIDTH - 40 && cursor.y < 50) {
							Menus[MENU_ESC].is_open = true;
							// toggle_music();
						} else if (cursor.x > SCREEN_WIDTH - 70 && cursor.y < 50) {

							Desktop desktop = null;
							// Before more Desktop API is used, first check
							// whether the API is supported by this particular
							// virtual machine (VM) on this particular host.
							if (Desktop.isDesktopSupported()) {
								desktop = Desktop.getDesktop();
							}
							try {
								desktop.open(new File(SharedData.defaultDirectory()));
							} catch (IOException e) {}

						} else if (cursor.x > SCREEN_WIDTH - 95 && cursor.y < 50) {
							launch_mapeditor();
						} else if (cursor.x > SCREEN_WIDTH - 125 && cursor.y < 50) {
							launch_asseteditor();
						}

						else if (cursor.x <= SCREEN_WIDTH / 2) {
							// QueueNewMainMenuScreen(MENUSCREEN_CHOOSEHERO,1);
							QueueNewMainMenuScreen(MENUSCREEN_CHOOSEHERO, 1);
						} else {
							QueueNewMainMenuScreen(MENUSCREEN_CHOOSECAMPAIGN, 3);
						}

					}
				}

				/** MOUSE RELEASE */
				if ((!Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == true)
						|| (!Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == true)) {

				}

			}// end inputs for mainscreen 0

			if (current_MainMenu_Screen == MENUSCREEN_CHOOSEHERO) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						// back
						if (BackButton[MENUSCREEN_CHOOSEHERO].BeingHovered(cursor)) {
							QueueNewMainMenuScreen(MENUSCREEN_FRONT, 1);
						}

						// save and continue
						// get the textbox data and open the save dialog!
						if (ForwardButton[MENUSCREEN_CHOOSEHERO].BeingHovered(cursor)) {

							if (Players[myPNum].name.length() > 0) {
								QueueNewMainMenuScreen(MENUSCREEN_JOINSERVER, 3);
							}

						}

						// if(RunningInGoldMode()){

						for (int j = 0; j < 8; j++) {
							int i = j + chooseheromenu_herofileoffset;

							int x = SCREEN_WIDTH / 2 - 300;
							int y = 100 + 60 * j;

							if (cursor.x > x && cursor.x < x + 300 && cursor.y > y && cursor.y < y + 50) {

								if (i == 0) {
									QueueNewMainMenuScreen(MENUSCREEN_NEWHERO, 0);
								} else if (myHeroFiles[i - 1] != null) {

									// debug(Players[myPNum].name);
									// debug(myHeroFiles[i - 1].substring(0,
									// myHeroFiles[i - 1].length() - 4));

									if (Players[myPNum].name.equals(myHeroFiles[i - 1].substring(0, myHeroFiles[i - 1].length() - 4))
											&& Players[myPNum].name.length() > 0) {
										QueueNewMainMenuScreen(MENUSCREEN_JOINSERVER, 3);
									} else {
										String mypath = SharedData.defaultDirectory() + "\\Heroes\\" + myHeroFiles[i - 1];
										readHerofromFile(mypath);
									}

								}
							}
						}

						/*
						 * }else{
						 * 
						 * if(cursor.WithinRect(SCREEN_WIDTH / 2 - 350+30,
						 * 120+270, 240, 90)){ try {
						 * java.awt.Desktop.getDesktop(
						 * ).browse(java.net.URI.create
						 * (SharedData.BANEFORGE_WEBSITE_REGISTER_URL)); } catch
						 * (IOException e) { // TODO Auto-generated catch block
						 * e.printStackTrace(); } }
						 * 
						 * }
						 */

					}

				}

				// mouse wheel

				// int cursor.wheel_delta = Mouse.getDWheel();

				/** Scroll Wheel Upwards */
				if (cursor.wheel_delta > 50) {
					if (chooseheromenu_herofileoffset > 0) {
						chooseheromenu_herofileoffset--;
					}

				}

				/** Scroll Wheel Downwards */
				if (cursor.wheel_delta < -50) {
					if (chooseheromenu_herofileoffset < chooseheromenu_number_of_found_herofiles - 8 + 1) {
						chooseheromenu_herofileoffset++;
					}

				}

			}

			if (current_MainMenu_Screen == MENUSCREEN_NEWHERO) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						// boolean back_button_pressed=false;

						// back
						if (BackButton[MENUSCREEN_NEWHERO].BeingHovered(cursor)) {

							QueueNewMainMenuScreen(MENUSCREEN_FRONT, 1);

						}

						// left clicking
						if (Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == false) {

							// click profileimage menu
							for (int j = 0; j < 8; j++) {
								int i = j + chooseheromenu_profileimagefileoffset;

								int x = SCREEN_WIDTH / 2 - 140 + MainMenu_tween_x;
								int y = 260 + 60 * j + MainMenu_tween_y;

								if (cursor.x > x && cursor.x < x + 300 && cursor.y > y && cursor.y < y + 50) {

									if (myProfileImageFiles[i] != null) {

										String mypath = SharedData.defaultDirectory() + "\\Profile Images\\" + myProfileImageFiles[i];
										debug(mypath);
										readCustomProfileImagefromFile(mypath);

									}
								}
							}

							int alignbuttons_x = SCREEN_WIDTH / 2 - 420 + MainMenu_tween_x;
							int alignbuttons_y = 340 + MainMenu_tween_y;

							// lclick alignment
							if (cursor.y > alignbuttons_y && cursor.y < alignbuttons_y + 20) {
								int num = (cursor.x - alignbuttons_x) / 80;
								if (num >= 0 && num <= 2) {
									newhero_align1 = num;
								}
							}

							if (cursor.y > alignbuttons_y + 40 && cursor.y < alignbuttons_y + 60) {
								int num = (cursor.x - alignbuttons_x) / 80;
								if (num >= 0 && num <= 2) {
									newhero_align2 = num;
								}
							}

							// lclick stats
							for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {

								int s = i + myshareddata.ENDURANCE;
								int pos_x = SCREEN_WIDTH / 2 + 200 + MainMenu_tween_x;
								int pos_y = 110 + 40 * (i % 8) + MainMenu_tween_y;

								if (cursor.x > pos_x && cursor.x < pos_x + 200 && cursor.y > pos_y && cursor.y < pos_y + 40 && newhero_pointsleft > 0
										&& default_hero_stats[s] < 15) {

									newhero_pointsleft--;
									default_hero_stats[s]++;

								}

							}

							// lclick perks
							/*
							 * for (int i = 0; i < SharedData.NUM_OF_PERKS; i++)
							 * {
							 * 
							 * int s = i + SharedData.PERK_ACROBAT;
							 * 
							 * if (cursor.x > SCREEN_WIDTH / 2 + 240 - 10 &&
							 * cursor.x < SCREEN_WIDTH / 2 + 240 - 10 + 200 &&
							 * cursor.y > 90 + 40 * i - 0 && cursor.y < 90 + 40
							 * * i - 0 + 40 && newhero_perksleft > 0
							 * 
							 * ) { if (Units[myPNum].stat[SharedData.PERK_1] !=
							 * s && Units[myPNum].stat[SharedData.PERK_2] != s)
							 * {
							 * 
							 * if (Units[myPNum].stat[SharedData.PERK_1] == 0) {
							 * newhero_perksleft--;
							 * Units[myPNum].stat[SharedData.PERK_1] = s;
							 * 
							 * } else if (Units[myPNum].stat[SharedData.PERK_2]
							 * == 0) {
							 * 
							 * newhero_perksleft--;
							 * Units[myPNum].stat[SharedData.PERK_2] = s; } }
							 * 
							 * }
							 * 
							 * }
							 */

						}

						// right clicking
						if (Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == false) {

							// rclick stats
							for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {

								int s = i + myshareddata.ENDURANCE;
								int pos_x = SCREEN_WIDTH / 2 + 200 + MainMenu_tween_x;
								int pos_y = 110 + 40 * (i % 8) + MainMenu_tween_y;

								if (cursor.x > pos_x && cursor.x < pos_x + 200 && cursor.y > pos_y && cursor.y < pos_y + 40
										&& default_hero_stats[s] > 5) {

									newhero_pointsleft++;
									default_hero_stats[s]--;

								}

							}

							/*
							 * for (int i = 0; i < SharedData.NUM_OF_PERKS; i++)
							 * {
							 * 
							 * int s = i + SharedData.PERK_ACROBAT;
							 * 
							 * if (cursor.x > SCREEN_WIDTH / 2 + 240 - 10 &&
							 * cursor.x < SCREEN_WIDTH / 2 + 240 - 10 + 200 &&
							 * cursor.y > 90 + 40 * i - 0 && cursor.y < 90 + 40
							 * * i - 0 + 40 ) { if
							 * (Units[myPNum].stat[SharedData.PERK_1] == s) {
							 * Units[myPNum].stat[SharedData.PERK_1] = 0;
							 * newhero_perksleft++; }
							 * 
							 * if (Units[myPNum].stat[SharedData.PERK_2] == s) {
							 * Units[myPNum].stat[SharedData.PERK_2] = 0;
							 * newhero_perksleft++; }
							 * 
							 * }
							 * 
							 * }
							 */

						}

						// save and continue
						// get the textbox data and open the save dialog!
						if (ForwardButton[MENUSCREEN_NEWHERO].BeingHovered(cursor)) {

							boolean allowedtosave = true;

							if (myTextBoxes[TEXTBOX_HERONAME].text.equals("")) {
								allowedtosave = false;
							}

							for (int i = 0; i < 1000; i++) {

								if (myHeroFiles[i] != null) {
									if (myTextBoxes[TEXTBOX_HERONAME].text.equals(myHeroFiles[i].substring(0, myHeroFiles[i].length() - 4))) {
										allowedtosave = false;
									}
								}
							}

							if (allowedtosave) {

								// transfer all temp data over

								Players[myPNum].name = myTextBoxes[TEXTBOX_HERONAME].text;
								Players[myPNum].herodescription = myTextBoxes[TEXTBOX_HERODESCRIP].text;

								Units[myPNum].stat[SharedData.ALIGNMENT] = (newhero_align1 + 1) + (newhero_align2) * 3;
								// Units[myPNum].stat[SharedData.MODEL_ID] =
								// myshareddata.UNITMODEL_MALEHUMAN +
								// newhero_chosenPreviewSprite;

								// apply all chosen perks and things!
								for (int i = 0; i < SharedData.NUM_OF_UNITSTATS; i++) {
									Units[myPNum].stat[i] = default_hero_stats[i];
								}

								Units[myPNum].stat[SharedData.HEALTH] = get_unit_maxhealth(myPNum);

								Units[myPNum].stat[myshareddata.ENERGYMAX] = 100;
								Units[myPNum].stat[myshareddata.ENERGYGROWTH] = 10;
								Units[myPNum].stat[myshareddata.STARTINGENERGY] = 50;
								Units[myPNum].stat[SharedData.ENERGY] = get_unit_maxenergy(myPNum);

								Units[myPNum].stat[SharedData.WALK_MOVES_COUNT] = get_unit_maxwalkmoves(myPNum);
								// Units[myPNum].stat[SharedData.ACTION_POINTS_COUNT]
								// = 4;
								Units[myPNum].stat[SharedData.LEVEL] = 0;// proof
																			// that
																			// the
																			// hero
																			// is
																			// brand
																			// new!
																			// Gives
																			// start
																			// items.
								Units[myPNum].stat[SharedData.FACING] = 2;

								Units[myPNum].stat[SharedData.MODEL_ID] = SharedData.UNITMODEL_MALEHUMAN;

								String mypath = SharedData.defaultDirectory() + "\\Heroes\\" + Players[myPNum].name;
								boolean success = writeHerotoFile(mypath);

								if (success) {
									QueueNewMainMenuScreen(MENUSCREEN_CHOOSEHERO, 3);

								}

							} else {
								ShowWarningDialog("Invalid Hero Name!");
							}
						}

					}

				}

				// mouse wheel

				// int cursor.wheel_delta = Mouse.getDWheel();

				/** Scroll Wheel Upwards */
				if (cursor.wheel_delta > 50) {
					if (chooseheromenu_profileimagefileoffset > 0) {
						chooseheromenu_profileimagefileoffset--;
					}

				}

				/** Scroll Wheel Downwards */
				if (cursor.wheel_delta < -50) {
					if (chooseheromenu_profileimagefileoffset < chooseheromenu_number_of_found_profileimagefiles - 8 + 1) {
						chooseheromenu_profileimagefileoffset++;
					}

				}

			}

			if (current_MainMenu_Screen == MENUSCREEN_JOINSERVER) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						// click on title

						if (serverbrowser_mode == 0) {
							if (cursor.WithinRect(SCREEN_WIDTH / 2 + 0, 20, 250, 60)) {
								serverbrowser_mode = 1;
							}

						} else {

							if (cursor.WithinRect(SCREEN_WIDTH / 2 - 250, 20, 250, 60)) {
								serverbrowser_mode = 0;
							}

						}

						if (serverbrowser_mode == 0) {

							// click on server browser entry
							for (int i = 0; i < 3; i++) {

								int y_offset = 80 * i;

								if (cursor.x > SCREEN_WIDTH / 2 - 300 + 30 && cursor.x < SCREEN_WIDTH / 2 - 300 + 30 + 600 - 60
										&& cursor.y > 100 + 15 + y_offset && cursor.y < 100 + 15 + y_offset + 100 && myFoundLocalHosts[i].isActive) {

									if (myFoundLocalHosts[i].IPAddress.equals(myTextBoxes[0].text)) {

										localIP = myTextBoxes[0].text;
										serverport = Integer.parseInt(myTextBoxes[1].text);

										networkThread_cli = new ClientThreaded(localIP, serverport, this);
										myNetworkingThread = new Thread(networkThread_cli);
										myNetworkingThread.start();

										QueueNewMainMenuScreen(MENUSCREEN_LOADSCREEN, 3);

									} else {
										myTextBoxes[0].text = myFoundLocalHosts[i].IPAddress;
										myTextBoxes[1].text = "" + myFoundLocalHosts[i].port;
									}

								}

							}

						}

						if (serverbrowser_mode == 1) {

							int y_offset = 0;

							// click on server browser entry
							for (int i = 0; i < 6; i++) {
								if (myFoundRemoteHosts[i].isActive) {

									if (cursor.x > SCREEN_WIDTH / 2 - 300 + 30 && cursor.x < SCREEN_WIDTH / 2 - 300 + 30 + 600 - 60
											&& cursor.y > 100 + 15 + y_offset && cursor.y < 100 + 15 + y_offset + 100
											&& myFoundRemoteHosts[i].isActive) {

										if (myFoundRemoteHosts[i].IPAddress.equals(myTextBoxes[0].text)) {

											String IP = myTextBoxes[0].text;
											serverport = Integer.parseInt(myTextBoxes[1].text);

											networkThread_cli = new ClientThreaded(IP, serverport, this);
											myNetworkingThread = new Thread(networkThread_cli);
											myNetworkingThread.start();

										} else {
											myTextBoxes[0].text = myFoundRemoteHosts[i].IPAddress;
											myTextBoxes[1].text = "" + myFoundRemoteHosts[i].port;
										}

									}

									y_offset += 40;
								}
							}

						}

						// back
						if (BackButton[MENUSCREEN_JOINSERVER].BeingHovered(cursor)) {
							QueueNewMainMenuScreen(MENUSCREEN_CHOOSEHERO, 1);
						}

						// joingame
						if (ForwardButton[MENUSCREEN_JOINSERVER].BeingHovered(cursor) && myTextBoxes[0].text.length() > 0
								&& myTextBoxes[1].text.length() > 0) {

							String IP = myTextBoxes[0].text;
							serverport = Integer.parseInt(myTextBoxes[1].text);

							networkThread_cli = new ClientThreaded(IP, serverport, this);
							myNetworkingThread = new Thread(networkThread_cli);
							myNetworkingThread.start();

						}

					}

				}

			}

			if (current_MainMenu_Screen == MENUSCREEN_CHOOSECAMPAIGN) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						// back
						if (BackButton[MENUSCREEN_CHOOSECAMPAIGN].BeingHovered(cursor)) {

							QueueNewMainMenuScreen(MENUSCREEN_FRONT, 1);
						}

						// startgame
						if (ForwardButton[MENUSCREEN_CHOOSECAMPAIGN].BeingHovered(cursor) && hostpnl_MapIsLoaded) {

							serverport = Integer.parseInt(myTextBoxes[TEXTBOX_HOSTPORT].text);
							ChooseCampaignMenu_StartGameServer(serverport);
						}

						if (cursor.WithinRect(SCREEN_WIDTH / 2 + 90 + MainMenu_tween_x, SCREEN_HEIGHT - 110 + MainMenu_tween_y, 180, 60)) {
							if (ServerOpenToPublic) {
								ServerOpenToPublic = false;
							} else {
								ServerOpenToPublic = true;
							}

						}

						String path = "";
						if (lastCampaignFilePath != null) {
							path = lastCampaignFilePath.substring(lastCampaignFilePath.lastIndexOf("\\") + 1);
						}

						for (int j = 0; j < 8; j++) {
							int i = j + choosecampaignmenu_campaignfileoffset;

							int x = SCREEN_WIDTH / 2 - 300;
							int y = 100 + 60 * j;

							if (cursor.x > x && cursor.x < x + 300 && cursor.y > y && cursor.y < y + 50) {

								if (i == 0) {
									QueueNewMainMenuScreen(MENUSCREEN_NEWCAMPAIGN, 2);
								} else if (myCampaignFiles[i - 1] != null) {

									if (path.equals(myCampaignFiles[i - 1])) {

										serverport = Integer.parseInt(myTextBoxes[TEXTBOX_HOSTPORT].text);
										ChooseCampaignMenu_StartGameServer(serverport);

									} else {

										String mypath = SharedData.defaultDirectory() + "\\Campaigns\\" + myCampaignFiles[i - 1];
										ReadCampaignFromFile(mypath);
									}

								}
							}
						}

					}
				}

				// mouse wheel

				// int cursor.wheel_delta = Mouse.getDWheel();

				/** Scroll Wheel Upwards */
				if (cursor.wheel_delta > 50) {
					if (choosecampaignmenu_campaignfileoffset > 0) {
						choosecampaignmenu_campaignfileoffset--;
					}

				}

				/** Scroll Wheel Downwards */
				if (cursor.wheel_delta < -50) {
					if (choosecampaignmenu_campaignfileoffset < choosecampaignmenu_number_of_found_files - 8 + 1) {
						choosecampaignmenu_campaignfileoffset++;
					}

				}

			}// end inputs for mainscreen 3

			if (current_MainMenu_Screen == MENUSCREEN_NEWCAMPAIGN) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						// back
						if (BackButton[MENUSCREEN_NEWCAMPAIGN].BeingHovered(cursor)) {

							QueueNewMainMenuScreen(MENUSCREEN_FRONT, 0);
						}

						boolean allowedtosave = true;
						if (myTextBoxes[TEXTBOX_CAMPAIGNNAME].text.equals("")) {
							allowedtosave = false;
						}

						for (int i = 0; i < 1000; i++) {
							if (myCampaignFiles[i] != null) {
								if (myTextBoxes[TEXTBOX_CAMPAIGNNAME].text.equals(myCampaignFiles[i].substring(0, myCampaignFiles[i].length() - 4))) {
									allowedtosave = false;
								}
							}
						}

						if (hostpnl_MapIsLoaded && ForwardButton[MENUSCREEN_NEWCAMPAIGN].BeingHovered(cursor)) {
							if (allowedtosave) {
								debug("MAKING NEW CAMP FILE");
								String mypath = SharedData.defaultDirectory() + "\\Campaigns\\" + myTextBoxes[TEXTBOX_CAMPAIGNNAME].text;
								boolean success = WriteCampaignToFile(mypath);

								QueueNewMainMenuScreen(MENUSCREEN_CHOOSECAMPAIGN, 0);
							} else {
								ShowWarningDialog("Invalid Campaign Name!");
							}
						}

						/*
						 * String path = ""; if (lastCampaignFilePath != null) {
						 * path =
						 * lastCampaignFilePath.substring(lastCampaignFilePath
						 * .lastIndexOf("\\") + 1); }
						 */

						for (int j = 0; j < 8; j++) {
							int i = j + choosemapmenu_mapfileoffset;

							int x = SCREEN_WIDTH / 2 - 400;
							int y = 300 + 60 * j;

							if (cursor.x > x && cursor.x < x + 300 && cursor.y > y && cursor.y < y + 50) {

								if (myMapFiles[i] != null) {

									String path = "";
									if (lastMapFilePath != null) {
										path = lastMapFilePath.substring(lastMapFilePath.lastIndexOf("\\") + 1);
									}

									if (path.equals(myMapFiles[i])) {
										if (allowedtosave) {
											QueueNewMainMenuScreen(MENUSCREEN_CHOOSECAMPAIGN, 0);
										}

									} else {
										String mypath = SharedData.defaultDirectory() + "\\Maps\\" + myMapFiles[i];
										loadMapfromFile(mypath);
									}

								}
							}
						}

						for (int j = 0; j < 8; j++) {
							int i = j + choosemapmenu_imagefileoffset;

							int x = SCREEN_WIDTH / 2 + 80 + MainMenu_tween_x;
							int y = 400 + 60 * j + MainMenu_tween_y;

							if (cursor.x > x && cursor.x < x + 300 && cursor.y > y && cursor.y < y + 50) {

								if (myCampaignImageFiles[i] != null) {

									String path = "";
									if (lastCampaignImageFilePath != null) {
										path = lastCampaignImageFilePath.substring(lastCampaignImageFilePath.lastIndexOf("\\") + 1);
									}

									if (path.equals(myCampaignImageFiles[i])) {
										if (allowedtosave) {
											QueueNewMainMenuScreen(MENUSCREEN_CHOOSECAMPAIGN, 0);
										}

									} else {
										String mypath = SharedData.defaultDirectory() + "\\Campaign Images\\" + myCampaignImageFiles[i];
										loadCampaignImagefromFile(mypath);
									}

								}
							}
						}

					}
				}

				// mouse wheel

				// int cursor.wheel_delta = Mouse.getDWheel();

				/** Scroll Wheel Upwards */
				if (cursor.wheel_delta > 50) {
					if (choosecampaignmenu_campaignfileoffset > 0) {
						choosecampaignmenu_campaignfileoffset--;
					}

				}

				/** Scroll Wheel Downwards */
				if (cursor.wheel_delta < -50) {
					if (choosecampaignmenu_campaignfileoffset < choosecampaignmenu_number_of_found_files - 8 + 1) {
						choosecampaignmenu_campaignfileoffset++;
					}

				}

			}

			if (current_MainMenu_Screen == MENUSCREEN_LOADSCREEN) {

				/** MOUSE DRAGGED */
				if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

					/** MOUSE PRESSED */
					if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

						if (finished_map_download) {
							debug("GOING INTO GAME");

							current_MainMenu_Screen = -1; // exit load screen,
															// go
															// into the game
						}
					}
				}

			}// end inputs for mainscreen 4

		} else {// if esc menu is open

			if (Menus[MENU_ESC].is_open) {
				handleinputs_ESCMenu();
			}

			handleinputs_LoginMenu();

		}

		/** MOUSE RELEASE */
		if ((!Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == true) || (!Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == true)) {

			menu_beingDragged = -1;
			menu_dragoffset_x = 0;
			menu_dragoffset_y = 0;

		}

	}

	int BackspaceTimer = 0;
	int KeyDowntimer = 0;
	String[] SpecialChars = {")", "!", "@", "#", "$", "%", "^", "&", "*", "("};

	public void pollInput_MenuKeyboard() {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Keyboard_KeyAlreadyDown[Keyboard.KEY_ESCAPE] == false) {
			openGameMenu(MENU_ESC);
		}// esc

		if (KeyDowntimer > 20) {
			if (BackspaceTimer < 4) {
				BackspaceTimer++;
			} else {
				BackspaceTimer = 0;
				Keyboard_KeyAlreadyDown[Keyboard.KEY_BACK] = false;
			}
		}

		boolean anykeypressed = false;
		int KeyPressed = -1;// can only be one at a time!
		for (int i = 0; i < 244; i++) {
			if (Keyboard.isKeyDown(i)) {
				KeyDowntimer++;
				anykeypressed = true;
				if (!Keyboard_KeyAlreadyDown[i]) {
					KeyPressed = i;
				}

			}

		}

		if (!anykeypressed) {
			KeyDowntimer = 0;
		}

		/*
		 * if (KeyPressed == Keyboard.KEY_TAB) { boolean alreadyfoundTB = false;
		 * if (current_MainMenu_Screen == MENUSCREEN_NEWHERO) {
		 * 
		 * for (int i = TEXTBOX_HERONAME; i < TEXTBOX_HERODESCRIP; i++) { if
		 * (myTextBoxes[i].Has_Focus && alreadyfoundTB == false) {
		 * myTextBoxes[i].Has_Focus = false; myTextBoxes[(i + 1)].Has_Focus =
		 * true; alreadyfoundTB = true; } } if (myTextBoxes[4].Has_Focus &&
		 * alreadyfoundTB == false) { myTextBoxes[4].Has_Focus = false;
		 * myTextBoxes[3].Has_Focus = true; alreadyfoundTB = true; }
		 * 
		 * }
		 * 
		 * }
		 */

		for (int j = 0; j < myTextBoxes.length; j++) { // select all textboxes
														// to add characters to
														// them if they got
														// focus!

			if (myTextBoxes[j].Has_Focus) {

				if (myTextBoxes[j].text.length() < myTextBoxes[j].maxtextlength) {

					// pressing numbers

					if (KeyPressed >= Keyboard.KEY_1 && KeyPressed <= Keyboard.KEY_0) {

						if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

							myTextBoxes[j].text += SpecialChars[Integer.parseInt(Keyboard.getKeyName(KeyPressed))];

						} else {
							myTextBoxes[j].text += Keyboard.getKeyName(KeyPressed);
						}

					}

					if (KeyPressed == Keyboard.KEY_NUMPAD0 || KeyPressed == Keyboard.KEY_NUMPAD1 || KeyPressed == Keyboard.KEY_NUMPAD2
							|| KeyPressed == Keyboard.KEY_NUMPAD3 || KeyPressed == Keyboard.KEY_NUMPAD4 || KeyPressed == Keyboard.KEY_NUMPAD5
							|| KeyPressed == Keyboard.KEY_NUMPAD6 || KeyPressed == Keyboard.KEY_NUMPAD7 || KeyPressed == Keyboard.KEY_NUMPAD8
							|| KeyPressed == Keyboard.KEY_NUMPAD9) {
						myTextBoxes[j].text += Keyboard.getKeyName(KeyPressed).substring(Keyboard.getKeyName(KeyPressed).length() - 1);
					}

					if (KeyPressed == Keyboard.KEY_PERIOD || KeyPressed == Keyboard.KEY_DECIMAL) {
						myTextBoxes[j].text += ".";
					}

					if (myTextBoxes[j].accept_alpha_characters && KeyPressed >= Keyboard.KEY_Q && KeyPressed <= Keyboard.KEY_M
							&& Keyboard.getKeyName(KeyPressed).length() == 1) {

						if (!(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))) {

							if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
								myTextBoxes[j].text += Keyboard.getKeyName(KeyPressed).toUpperCase();
							} else {
								myTextBoxes[j].text += Keyboard.getKeyName(KeyPressed).toLowerCase();
							}
						} else {

							if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
								myTextBoxes[j].text += getClipboardContents();
							}

						}

					}

					if (myTextBoxes[j].accept_alpha_characters && KeyPressed == Keyboard.KEY_SPACE) {
						myTextBoxes[j].text += " ";
					}

					if (myTextBoxes[j].accept_alpha_characters && KeyPressed == Keyboard.KEY_COMMA) {
						myTextBoxes[j].text += ",";
					}

					if (myTextBoxes[j].accept_alpha_characters && KeyPressed == Keyboard.KEY_APOSTROPHE) {

						if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
							myTextBoxes[j].text += "\"";
						} else {
							myTextBoxes[j].text += "'";
						}

					}

				}

				if (myTextBoxes[j].text.length() > 0) {
					if (KeyPressed == Keyboard.KEY_BACK || KeyPressed == Keyboard.KEY_DELETE) {
						myTextBoxes[j].text = myTextBoxes[j].text.substring(0, myTextBoxes[j].text.length() - 1);
					}
				}

			}

		}

		// tabbing thru textboxes
		if (Menus[MENU_LOGIN].is_open) {
			if (Keyboard.isKeyDown(Keyboard.KEY_TAB) && Keyboard_KeyAlreadyDown[Keyboard.KEY_TAB] == false) {

				if (myTextBoxes[TEXTBOX_LOGINNAME].Has_Focus) {
					myTextBoxes[TEXTBOX_LOGINNAME].Has_Focus = false;
					myTextBoxes[TEXTBOX_LOGINPASSWORD].Has_Focus = true;
				} else {
					if (myTextBoxes[TEXTBOX_LOGINPASSWORD].Has_Focus) {
						myTextBoxes[TEXTBOX_LOGINPASSWORD].Has_Focus = false;
						myTextBoxes[TEXTBOX_LOGINNAME].Has_Focus = true;
					}
				}

			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && Keyboard_KeyAlreadyDown[Keyboard.KEY_RETURN] == false) {
				PressedLoginAcceptButton();
			}

		}

	}

	void organizeHeroAbilities() {

		for (int i = 0; i < 1000; i++) {

			int abilityspec = myAbilityDB[i].abilityspec;
			boolean enabled = myAbilityDB[i].enabled;

			if (abilityspec >= SharedData.ABILITYSPEC_BRAWN && abilityspec <= SharedData.ABILITYSPEC_SORCERY && enabled) {
				System.out.println("added ability to spellbook! - " + myAbilityDB[i].namestring);

				int nextslotavailable = -1;
				for (int k = 0; k < 1000; k++) {
					if (AbilitiesInEachSpec[abilityspec][k] == 0) {
						nextslotavailable = k;
						break;
					}
				}
				if (nextslotavailable > -1) {
					AbilitiesInEachSpec[myAbilityDB[i].abilityspec][nextslotavailable] = i;
				}

			}
		}

	}

	Thread myNetworkingThread;
	Thread myUDPBroadcastThread;

	private Image2D intropnl_logo;

	Image2D MainMenuBG1;
	Image2D MainMenuBG2;

	Image2D MainMenu_HeroImage;
	Image2D MainMenu_HeroText;
	Image2D MainMenu_HostImage;
	Image2D MainMenu_HostText;
	Image2D MainMenu_Text1;

	Image2D volume_on;
	Image2D volume_off;
	Image2D smallfolder;
	Image2D smallgear;
	Image2D bookicon;
	Image2D editicon;

	Image2D button_localserverbrowser;
	Image2D button_remoteserverbrowser;
	Image2D button_localserverbrowserGS;
	Image2D button_remoteserverbrowserGS;

	Image2D MasterTerrainSheet;
	Image2D SmallObject50Sheet;
	Image2D LargeObject50Sheet;
	Image2D LargeObject100Sheet;

	Image2D LargeObject200Sheet;
	Image2D RoofSheet;
	Image2D SeamlessRock;

	// Image2D HeroSheet_FemaleBody;
	// Image2D HeroSheet_MaleBody;
	Image2D[] HeroSheet_Items = new Image2D[40];

	Image2D MasterBWIconSheet;
	Image2D MasterItemIconSheet;
	Image2D MasterOverlayGFXSheet;
	Image2D GenericMenuBG;
	Image2D AssetTableBG;
	// Image2D MasterAbilityIconSheet;
	// Image2D MasterConditionIconSheet;

	Image2D minimappreview_striped;
	Image2D minimappreview_border;

	Image2D endturnarrows;
	Image2D[] minimenubuttons = new Image2D[6];
	// Image2D Menu[MENU_INV].background;
	// Image2D Menu[MENU_LOOTING].background;
	// Image2D menu_red_x;
	Image2D menu_exit_x;
	Image2D quest_rect;

	Image2D red_back_arrow;
	// Image2D invmenu_plusbutton;
	Image2D invmenu_nokey;
	Image2D escmenu_autosaveonbtn;
	Image2D escmenu_autosaveoffbtn;
	Image2D escmenu_settingsbtn;
	// Image2D ability_targettinghighlightblue;
	// Image2D cursor.whitesquare;
	// Image2D ability_targettinghighlightred;
	Image2D ability_newstatpointtospend;
	Image2D ability_newabilitypointtospend;
	// Image2D ability_godmodeenabled;
	// Image2D ability_godmodedisabled;//DELETE THIS JUNK
	Image2D staticon_health;
	Image2D staticon_energy;
	Image2D staticon_power;
	Image2D staticon_armor;
	Image2D hovertooltip_bot;
	Image2D hovertooltip_mid;
	Image2D hovertooltip_top;

	Image2D chatbubble_bot;
	Image2D chatbubble_mid;
	Image2D chatbubble_top;

	Image2D minimapbg;

	Image2D loadscreen_blueprint;
	Image2D loadscreen_continue;
	Image2D[] loadscreen_stagetext = new Image2D[4];

	Image2D simpleBlackButton;
	Image2D simpleGrayButton;
	Image2D endturnbutton;
	Image2D endturnbutton_transparent;
	Image2D[] white_arrow = new Image2D[4];

	Image2D generictextboximage;

	Image2D D20_image;
	Image2D abilityicon_frame;

	// Image2D[] ability_icons = new Image2D[99];

	// Image2D button_editstats;
	// Image2D button_moveunit;
	Image2D ProfileImage_frame;

	Image2D MenuInvSlots;

	Image2D AbilityMenuBG_tab1;
	Image2D AbilityMenuBG_tab2;

	Image2D[] AbilitySpecButtons = new Image2D[10];

	Image2D healthbar;
	Image2D energybar;

	Image2D large_exp_bar;

	Image2D levelcircle;

	Image2D unitinfo_bg;
	Image2D assettable_crumb;

	Image2D battling_unit_indicator;

	Image2D tomeicon;
	Image2D coinsicon;
	Image2D hearticon;

	Image2D phasebutton_battle;
	Image2D phasebutton_passive;

	Image2D itemvendor_whitearrow;

	Image2D vitalicon_meleepower;
	Image2D vitalicon_rangedpower;
	Image2D vitalicon_spellpower;
	Image2D vitalicon_armor;
	Image2D vitalicon_magres;
	Image2D vitalicon_toxres;

	Image2D vitalicon_speed;
	Image2D vitalicon_initiative;

	Image2D minimap_static;
	Texture minimap_static_texture;

	CustomFont Verdana_12;
	CustomFont Verdana_14;
	CustomFont Verdana_16;
	CustomFont Verdana_18;
	CustomFont Verdana_20;
	CustomFont Arcade_20;

	Image2D button_publicgame;
	Image2D button_privategame;

	Image2D vend_tab_crafting;
	Image2D vend_tab_buying;

	Image2D charm_crafting;
	Image2D charm_buying;

	Image2D assettable_tab_u;
	Image2D assettable_tab_i;
	Image2D assettable_tab_c;

	Image2D defaultUnitShadow;

	Image2D button_login_inner;
	Image2D button_login_outer;

	Image2D button_login_accept;
	Image2D button_login_register;
	Image2D button_logout;

	Image2D SleekButtonBG;
	Image2D HeroPromo;

	void LoadGameAssets() {

		Verdana_12 = new CustomFont(generate_tex_local("fonts/" + "verdana12_0.png"), parse_font_lookup_table("fonts/" + "verdana12.fnt"));
		Verdana_14 = new CustomFont(generate_tex_local("fonts/" + "verdana14.png"), parse_font_lookup_table("fonts/" + "verdana14.fnt"));
		Verdana_16 = new CustomFont(generate_tex_local("fonts/" + "verdana16_0.png"), parse_font_lookup_table("fonts/" + "verdana16.fnt"));
		Verdana_18 = new CustomFont(generate_tex_local("fonts/" + "verdana18_0.png"), parse_font_lookup_table("fonts/" + "verdana18.fnt"));
		Verdana_20 = new CustomFont(generate_tex_local("fonts/" + "verdana20_0.png"), parse_font_lookup_table("fonts/" + "verdana20.fnt"));
		Arcade_20 = new CustomFont(generate_tex_local("fonts/" + "arcadeclassic_20_0.png"),
				parse_font_lookup_table("fonts/" + "arcadeclassic_20.fnt"));

		MainMenuBG1 = generate_typeimage_local("menus/ardentrystgraybg.png");
		MainMenuBG2 = generate_typeimage_local("menus/ardentrystbluebg.png");
		MainMenu_HeroImage = generate_typeimage_local("menus/mainmenuheroimage.png");
		MainMenu_HeroText = generate_typeimage_local("menus/mainmenuherotext.png");
		MainMenu_HostImage = generate_typeimage_local("menus/mainmenudmimage.png");
		MainMenu_HostText = generate_typeimage_local("menus/mainmenudmtext.png");
		MainMenu_Text1 = generate_typeimage_local("menus/mainmenutext1.png");

		for (int i = 0; i < BackButton.length; i++) {
			BackButton[i].background = generate_typeimage_local("gameGUI/trapbuttonleft.png");
			BackButton[i].text = "Back";
		}
		for (int i = 0; i < ForwardButton.length; i++) {
			ForwardButton[i].background = generate_typeimage_local("gameGUI/trapbuttonright.png");
			ForwardButton[i].text = "Continue";
		}

		MasterTerrainSheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "TerrainSheet.png");

		MasterItemIconSheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "ItemIconSheet.png");

		MasterBWIconSheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "BWIconSheet.png");
		SmallObject50Sheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "SmallObjectSheet_50.png");
		LargeObject50Sheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "LargeObjectSheet_50.png");
		LargeObject100Sheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "LargeObjectSheet_100.png");

		LargeObject200Sheet = generate_typeimage_local("objects/LargeObjectSheet_200.png");
		RoofSheet = generate_typeimage_local("objects/roofsheet.png");
		SeamlessRock = generate_typeimage_local("misc/seamlessrock.png");

		SleekButtonBG = generate_typeimage_local("menubuttons/sleek-button.png");

		HeroPromo = generate_typeimage_local("misc/HeroPromo.png");

		MasterOverlayGFXSheet = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\" + "OverlayGFXSheet.png");
		GenericMenuBG = generate_typeimage_local("gameGUI/GenericMenuBackground.png");
		AssetTableBG = generate_typeimage_local("gameGUI/AssetTableBackground.png");

		loadscreen_blueprint = generate_typeimage_local("menus/blueprintbg.png");

		defaultUnitShadow = generate_typeimage_local("herosprites/unitshadow.png");

		button_publicgame = generate_typeimage_local("menubuttons/button_publicgame.png");
		button_privategame = generate_typeimage_local("menubuttons/button_privategame.png");

		button_login_inner = generate_typeimage_local("menubuttons/login_inner.png");
		button_login_outer = generate_typeimage_local("menubuttons/login_outer.png");

		button_login_accept = generate_typeimage_local("menubuttons/login_accept.png");
		button_login_register = generate_typeimage_local("menubuttons/login_register.png");
		button_logout = generate_typeimage_local("menubuttons/logout.png");

		HeroSheet_Items[SharedData.ITEMMODEL_HAIR] = generate_typeimage_local("herosprites/herosheet_head_hair.png");
		HeroSheet_Items[SharedData.ITEMMODEL_CLOTHHEAD] = generate_typeimage_local("herosprites/herosheet_head_cloth.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERHEAD] = generate_typeimage_local("herosprites/herosheet_head_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_CHAINHEAD] = generate_typeimage_local("herosprites/herosheet_head_chain.png");
		HeroSheet_Items[SharedData.ITEMMODEL_METALHEAD] = generate_typeimage_local("herosprites/herosheet_head_metal.png");
		HeroSheet_Items[SharedData.ITEMMODEL_PLATEHEAD] = generate_typeimage_local("herosprites/herosheet_head_plate.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERSHOULDER] = generate_typeimage_local("herosprites/herosheet_shoulder_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_PLATESHOULDER] = generate_typeimage_local("herosprites/herosheet_shoulder_plate.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SHIRTBROWN] = generate_typeimage_local("herosprites/herosheet_chest_shirtbrown.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SHIRTWHITE] = generate_typeimage_local("herosprites/herosheet_chest_shirtwhite.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERCHEST] = generate_typeimage_local("herosprites/herosheet_chest_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_CHAINCHEST] = generate_typeimage_local("herosprites/herosheet_chest_chain.png");
		HeroSheet_Items[SharedData.ITEMMODEL_PLATECHEST] = generate_typeimage_local("herosprites/herosheet_chest_plate.png");
		HeroSheet_Items[SharedData.ITEMMODEL_DEMONCHEST] = generate_typeimage_local("herosprites/herosheet_chest_demon.png");
		HeroSheet_Items[SharedData.ITEMMODEL_CLOTHBELT] = generate_typeimage_local("herosprites/herosheet_belt_cloth.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERBELT] = generate_typeimage_local("herosprites/herosheet_belt_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_CLOTHLEGS] = generate_typeimage_local("herosprites/herosheet_legs_cloth.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERLEGS] = generate_typeimage_local("herosprites/herosheet_legs_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_METALLEGS] = generate_typeimage_local("herosprites/herosheet_legs_metal.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERSHOES] = generate_typeimage_local("herosprites/herosheet_shoes_leather.png");
		HeroSheet_Items[SharedData.ITEMMODEL_METALSHOES] = generate_typeimage_local("herosprites/herosheet_shoes_metal.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SHIELD] = generate_typeimage_local("herosprites/herosheet_weapon_shield.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SPEAR] = generate_typeimage_local("herosprites/herosheet_weapon_spear.png");
		HeroSheet_Items[SharedData.ITEMMODEL_DAGGER] = generate_typeimage_local("herosprites/herosheet_weapon_dagger.png");
		HeroSheet_Items[SharedData.ITEMMODEL_BOW] = generate_typeimage_local("herosprites/herosheet_weapon_bow.png");
		HeroSheet_Items[SharedData.ITEMMODEL_PISTOL] = generate_typeimage_local("herosprites/herosheet_weapon_pistol.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SHOTGUN] = generate_typeimage_local("herosprites/herosheet_weapon_shotgun.png");
		HeroSheet_Items[SharedData.ITEMMODEL_ASSAULTRIFLE] = generate_typeimage_local("herosprites/herosheet_weapon_assaultrifle.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SHORTSWORD] = generate_typeimage_local("herosprites/herosheet_weapon_shortsword.png");
		HeroSheet_Items[SharedData.ITEMMODEL_RAPIER] = generate_typeimage_local("herosprites/herosheet_weapon_rapier.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LONGSWORD] = generate_typeimage_local("herosprites/herosheet_weapon_longsword.png");
		HeroSheet_Items[SharedData.ITEMMODEL_DRESS] = generate_typeimage_local("herosprites/herosheet_chest_dress.png");
		HeroSheet_Items[SharedData.ITEMMODEL_BLOUSE] = generate_typeimage_local("herosprites/herosheet_chest_blouse.png");
		HeroSheet_Items[SharedData.ITEMMODEL_SKIRT] = generate_typeimage_local("herosprites/herosheet_legs_skirt.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERCHESTFEMALE] = generate_typeimage_local("herosprites/herosheet_chest_leather_female.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERGLOVESFEMALE] = generate_typeimage_local("herosprites/herosheet_gloves_leather_female.png");
		HeroSheet_Items[SharedData.ITEMMODEL_LEATHERSHOULDERFEMALE] = generate_typeimage_local("herosprites/herosheet_shoulder_leather_female.png");
		// add more!

		ProfileImage_frame = generate_typeimage_local("gameGUI/profileimageframe.png");

		minimapbg = generate_typeimage_local("gameGUI/grungemapbg.png");

		healthbar = generate_typeimage_local("misc/healthbar.png");
		energybar = generate_typeimage_local("misc/energybar.png");
		large_exp_bar = generate_typeimage_local("misc/large_exp_bar.png");

		phasebutton_battle = generate_typeimage_local("gameGUI/battlephasebutton.png");
		phasebutton_passive = generate_typeimage_local("gameGUI/passivephasebutton.png");

		itemvendor_whitearrow = generate_typeimage_local("gameGUI/whiterightarrow.png");

		button_localserverbrowser = generate_typeimage_local("menubuttons/button_localserverbrowser.png");
		button_remoteserverbrowser = generate_typeimage_local("menubuttons/button_remoteserverbrowser.png");
		button_localserverbrowserGS = generate_typeimage_local("menubuttons/button_localserverbrowserGS.png");
		button_remoteserverbrowserGS = generate_typeimage_local("menubuttons/button_remoteserverbrowserGS.png");

		battling_unit_indicator = generate_typeimage_local("misc/downwhitearrow.png");

		tomeicon = generate_typeimage_local("misc/tome.png");
		coinsicon = generate_typeimage_local("misc/coins.png");
		hearticon = generate_typeimage_local("misc/heart.png");

		MenuInvSlots = generate_typeimage_local("gameGUI/invmenuslots.png");

		Menus[MENU_LOGIN].background = generate_typeimage_local("misc/LoginMenuBG.png");

		myTypeDB[SharedData.TEMPLESTAIRS_DOWN_1].image = generate_typeimage_local("objects/temple_entrance.png");
		myTypeDB[SharedData.TEMPLESTAIRS_DOWN_2].image = generate_typeimage_local("objects/temple_entrance2.png");

		for (int i = SharedData.PATHING_BLOCKER_LOW; i <= SharedData.HEROSPAWN; i++) {
			myTypeDB[i].spritesheet = 1;
			myTypeDB[i].sheet_index = i - SharedData.PATHING_BLOCKER_LOW;

		}

		for (int i = SharedData.DOOR_H_OPEN; i <= SharedData.STALAGMITE3; i++) {
			myTypeDB[i].spritesheet = 2;
			myTypeDB[i].sheet_index = i - SharedData.DOOR_H_OPEN;
		}

		for (int i = SharedData.TANROCKS1; i <= SharedData.DUNGEONSTAIRS_UP_2; i++) {
			myTypeDB[i].spritesheet = 3;
			myTypeDB[i].sheet_index = i - SharedData.TANROCKS1;
		}

		Object_Spritesheet[SharedData.TORCH] = new SpriteSheet(64, 4, generate_typeimage_local("objects/torch.png"));
		Object_Spritesheet[SharedData.HEROSPAWN] = new SpriteSheet(64, 4, generate_typeimage_local("objects/stonepad.png"));

		Object_Spritesheet[SharedData.SIGNALFIRE] = new SpriteSheet(100, 8, generate_typeimage_local("objects/signalfire2.png"));

		// polishing fixes...

		for (int i = SharedData.WOODWALL_C1; i < SharedData.CLIFF_C8; i++) {
			myTypeDB[i].isEverLit = true;
		}
		myTypeDB[SharedData.STREETLAMP].isEverLit = true;

		myTypeDB[SharedData.PATHING_BLOCKER_LOW].DoNotRender = true;
		myTypeDB[SharedData.PATHING_BLOCKER_HIGH].DoNotRender = true;

		// /define object options!
		myTypeDB[SharedData.WELL_EMPTY].options[0] = "Do Nothing";
		myTypeDB[SharedData.WELL_EMPTY].options[1] = "Fill with Blood";
		myTypeDB[SharedData.WELL_EMPTY].options[2] = "Fill with Magic";

		myTypeDB[SharedData.WELL_RED].options[0] = "Do Nothing";
		myTypeDB[SharedData.WELL_RED].options[1] = "Empty the Well";
		myTypeDB[SharedData.WELL_RED].options[2] = "Fill with Magic";

		myTypeDB[SharedData.WELL_BLUE].options[0] = "Do Nothing";
		myTypeDB[SharedData.WELL_BLUE].options[1] = "Empty the Well";
		myTypeDB[SharedData.WELL_BLUE].options[2] = "Fill with Blood";

		myTypeDB[SharedData.DOOR_H_OPEN].options[0] = "Do Nothing";
		myTypeDB[SharedData.DOOR_H_OPEN].options[1] = "Close Door";

		myTypeDB[SharedData.DOOR_H_CLOSED].options[0] = "Do Nothing";
		myTypeDB[SharedData.DOOR_H_CLOSED].options[1] = "Open Door";

		myTypeDB[SharedData.PRESSUREPLATE_UP].options[0] = "Do Nothing";
		myTypeDB[SharedData.PRESSUREPLATE_UP].options[1] = "Disarm Trap";

		myTypeDB[SharedData.PRESSUREPLATE_DOWN].options[0] = "Do Nothing";
		myTypeDB[SharedData.PRESSUREPLATE_DOWN].options[1] = "Reset Trap";

		myTypeDB[SharedData.MOVEABLEBLOCK].options[0] = "Do Nothing";
		myTypeDB[SharedData.MOVEABLEBLOCK].options[1] = "Hide Block";

		myTypeDB[SharedData.HIDDENBLOCK].options[0] = "Do Nothing";
		myTypeDB[SharedData.HIDDENBLOCK].options[1] = "Show Block";

		for (int i = 0; i < 4; i++) {
			lightrays[i] = generate_typeimage_local("objects/lightrays" + (i + 1) + ".png");
		}

		myTypeDB[SharedData.SMALLCLIFF_H1].image = generate_typeimage_local("objects/smallcliff_h1.png");
		myTypeDB[SharedData.SMALLCLIFF_H2].image = generate_typeimage_local("objects/smallcliff_h2.png");
		myTypeDB[SharedData.SMALLCLIFF_V1].image = generate_typeimage_local("objects/smallcliff_v1.png");
		myTypeDB[SharedData.SMALLCLIFF_V2].image = generate_typeimage_local("objects/smallcliff_v2.png");
		myTypeDB[SharedData.SMALLCLIFF_C1].image = generate_typeimage_local("objects/smallcliff_c1.png");
		myTypeDB[SharedData.SMALLCLIFF_C2].image = generate_typeimage_local("objects/smallcliff_c2.png");
		myTypeDB[SharedData.SMALLCLIFF_C3].image = generate_typeimage_local("objects/smallcliff_c3.png");
		myTypeDB[SharedData.SMALLCLIFF_C4].image = generate_typeimage_local("objects/smallcliff_c4.png");

		// UNIT MODELS

		myUnitModels[SharedData.UNITMODEL_MALEHUMAN].image = generate_typeimage_local("herosprites/herosheet_malebody.png");
		myUnitModels[SharedData.UNITMODEL_MALEHUMAN].spritesheet_type = 1;
		myUnitModels[SharedData.UNITMODEL_MALEHUMAN].item_compatible = true;
		myUnitModels[SharedData.UNITMODEL_FEMALEHUMAN].image = generate_typeimage_local("herosprites/herosheet_femalebody.png");
		myUnitModels[SharedData.UNITMODEL_FEMALEHUMAN].spritesheet_type = 1;
		myUnitModels[SharedData.UNITMODEL_FEMALEHUMAN].item_compatible = true;
		myUnitModels[SharedData.UNITMODEL_SKELEBODY].image = generate_typeimage_local("herosprites/herosheet_skelebody.png");
		myUnitModels[SharedData.UNITMODEL_SKELEBODY].spritesheet_type = 1;
		myUnitModels[SharedData.UNITMODEL_SKELEBODY].item_compatible = true;

		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].image = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\NPCS\\"
				+ "isozombie.png");
		myUnitModels[SharedData.UNITMODEL_ISOSKELETON].image = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\NPCS\\"
				+ "isoskeleton.png");
		myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].image = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\NPCS\\"
				+ "isogoblin.png");
		myUnitModels[SharedData.UNITMODEL_ISOMINOTAUR].image = generate_typeimage_ext(SharedData.defaultDirectory() + "\\Textures\\NPCS\\"
				+ "isominotaur.png");
		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].spritesheet_type = 2;
		myUnitModels[SharedData.UNITMODEL_ISOSKELETON].spritesheet_type = 2;
		myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].spritesheet_type = 2;
		myUnitModels[SharedData.UNITMODEL_ISOMINOTAUR].spritesheet_type = 2;
		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].frame_width = 128;
		myUnitModels[SharedData.UNITMODEL_ISOSKELETON].frame_width = 128;
		myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].frame_width = 128;
		myUnitModels[SharedData.UNITMODEL_ISOMINOTAUR].frame_width = 128;

		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].NumAnimationFrames = new int[]{1, 4, 7, 10, 10, 10, 10, 6};
		myUnitModels[SharedData.UNITMODEL_ISOSKELETON].NumAnimationFrames = new int[]{1, 4, 7, 7, 7, 7, 7, 6};
		myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].NumAnimationFrames = new int[]{1, 4, 8, 4, 4, 4, 4, 6};
		myUnitModels[SharedData.UNITMODEL_ISOMINOTAUR].NumAnimationFrames = new int[]{1, 4, 8, 6, 6, 6, 6, 6};

		myUnitModels[SharedData.UNITMODEL_ZOMBIE1].image = generate_typeimage_local("npcs/NPCzombie1.png");
		myUnitModels[SharedData.UNITMODEL_ZOMBIE2].image = generate_typeimage_local("npcs/NPCzombie2.png");

		myUnitModels[SharedData.UNITMODEL_SKELETON1].image = generate_typeimage_local("npcs/NPCskeleton1.png");
		myUnitModels[SharedData.UNITMODEL_SKELETON2].image = generate_typeimage_local("npcs/NPCskeleton2.png");
		myUnitModels[SharedData.UNITMODEL_BAT].image = generate_typeimage_local("npcs/bat.png");
		myUnitModels[SharedData.UNITMODEL_BLACKSPIDER].image = generate_typeimage_local("npcs/blackspider.png");

		myUnitModels[SharedData.UNITMODEL_WIZARD].image = generate_typeimage_local("npcs/evilwizard.png");
		myUnitModels[SharedData.UNITMODEL_EYEMONSTER].image = generate_typeimage_local("npcs/eyemonster.png");

		myUnitModels[SharedData.UNITMODEL_GOBLINBRUISER].image = generate_typeimage_local("npcs/goblinbruiser.png");
		myUnitModels[SharedData.UNITMODEL_SKELETALSPEARMAN].image = generate_typeimage_local("npcs/skeletonspearman.png");
		myUnitModels[SharedData.UNITMODEL_SNAKE].image = generate_typeimage_local("npcs/snake.png");
		myUnitModels[SharedData.UNITMODEL_TURTLE].image = generate_typeimage_local("npcs/snappingturtle.png");

		myUnitModels[SharedData.UNITMODEL_GORG].image = generate_typeimage_local("npcs/gorg.png");
		myUnitModels[SharedData.UNITMODEL_GORG].frame_width = 64;
		myUnitModels[SharedData.UNITMODEL_GIANTALIEN].image = generate_typeimage_local("npcs/giantalien.png");
		myUnitModels[SharedData.UNITMODEL_GIANTALIEN].frame_width = 100;
		myUnitModels[SharedData.UNITMODEL_SHROOMTREANT].image = generate_typeimage_local("npcs/shroomtreant.png");
		myUnitModels[SharedData.UNITMODEL_SHROOMTREANT].frame_width = 64;
		myUnitModels[SharedData.UNITMODEL_DOOMRAVEN].image = generate_typeimage_local("npcs/doomraven.png");
		myUnitModels[SharedData.UNITMODEL_DOOMRAVEN].frame_width = 128;
		myUnitModels[SharedData.UNITMODEL_BIRDMAN].image = generate_typeimage_local("npcs/birdman.png");
		myUnitModels[SharedData.UNITMODEL_BIRDMAN].frame_width = 100;

		myUnitModels[SharedData.UNITMODEL_MALEHUMAN].offset_y = -5;
		myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].offset_y = -10;
		myUnitModels[SharedData.UNITMODEL_ISOSKELETON].offset_y = -10;
		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].offset_y = -10;

		// hurt sounds

		/*
		 * myUnitModels[SharedData.UNITMODEL_ZOMBIE1].hurtsound_ID =
		 * SOUND_ZOMBIE1;
		 * myUnitModels[SharedData.UNITMODEL_ZOMBIE2].hurtsound_ID =
		 * SOUND_ZOMBIE2;
		 * myUnitModels[SharedData.UNITMODEL_ISOSKELETON].hurtsound_ID =
		 * SOUND_ZOMBIE1;
		 * myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].hurtsound_ID =
		 * SOUND_ZOMBIE2;
		 * myUnitModels[SharedData.UNITMODEL_ISOMINOTAUR].hurtsound_ID =
		 * SOUND_BEAST1;
		 * myUnitModels[SharedData.UNITMODEL_ISOGOBLIN].hurtsound_ID =
		 * SOUND_BEAST2; myUnitModels[SharedData.UNITMODEL_GORG].hurtsound_ID =
		 * SOUND_BEAST2;
		 * myUnitModels[SharedData.UNITMODEL_SKELETON1].hurtsound_ID =
		 * SOUND_ZOMBIE1;
		 * myUnitModels[SharedData.UNITMODEL_SKELETON2].hurtsound_ID =
		 * SOUND_ZOMBIE2; myUnitModels[SharedData.UNITMODEL_BAT].hurtsound_ID =
		 * SOUND_SCREECH1;
		 * myUnitModels[SharedData.UNITMODEL_BLACKSPIDER].hurtsound_ID =
		 * SOUND_SCREECH1;
		 * myUnitModels[SharedData.UNITMODEL_WIZARD].hurtsound_ID =
		 * SOUND_HUMANGROAN4;
		 * myUnitModels[SharedData.UNITMODEL_EYEMONSTER].hurtsound_ID =
		 * SOUND_BEAST1;
		 * myUnitModels[SharedData.UNITMODEL_GOBLINBRUISER].hurtsound_ID =
		 * SOUND_BEAST2;// fix
		 * myUnitModels[SharedData.UNITMODEL_SKELETALSPEARMAN].hurtsound_ID =
		 * SOUND_ZOMBIE1; myUnitModels[SharedData.UNITMODEL_SNAKE].hurtsound_ID
		 * = SOUND_HISS; myUnitModels[SharedData.UNITMODEL_TURTLE].hurtsound_ID
		 * = SOUND_HISS;
		 * myUnitModels[SharedData.UNITMODEL_MALEHUMAN].hurtsound_ID =
		 * SOUND_HUMANGROAN2;
		 * myUnitModels[SharedData.UNITMODEL_FEMALEHUMAN].hurtsound_ID =
		 * SOUND_HUMANGROAN3;// fix
		 */

		myUnitModels[SharedData.UNITMODEL_ZOMBIE1].profileimage = generate_typeimage_local("profileimages/zomface1.png");
		myUnitModels[SharedData.UNITMODEL_ZOMBIE2].profileimage = generate_typeimage_local("profileimages/zomface2.png");
		myUnitModels[SharedData.UNITMODEL_ISOZOMBIE].profileimage = generate_typeimage_local("profileimages/zomface2.png");
		myUnitModels[SharedData.UNITMODEL_MALEHUMAN].profileimage = generate_typeimage_local("profileimages/MaleHero3.png");
		myUnitModels[SharedData.UNITMODEL_FEMALEHUMAN].profileimage = generate_typeimage_local("profileimages/FemaleHero1.png");

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.WOODWALL_C1 + i].image = generate_typeimage_local("objects/woodwall_c" + (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.WOODWALL_V1 + i].image = generate_typeimage_local("objects/woodwall_v" + (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.WOODWALL_H1 + i].image = generate_typeimage_local("objects/woodwall_h" + (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.WOODWALL_E1 + i].image = generate_typeimage_local("objects/woodwall_e" + (i + 1) + ".png");
		}

		for (int i = 0; i < 8; i++) {
			myTypeDB[SharedData.CLIFF_C1 + i].image = generate_typeimage_local("objects/cliff_c" + (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.CLIFF_H1 + i].image = generate_typeimage_local("objects/cliff_h" + (i + 1) + ".png");
		}

		for (int i = 0; i < 4; i++) {
			myTypeDB[SharedData.CLIFF_V1 + i].image = generate_typeimage_local("objects/cliff_v" + (i + 1) + ".png");
		}

		// change these

		myTypeDB[SharedData.YELLOW_NOTE].image = generate_typeimage_local("tiletextures/yellownote.png");
		myTypeDB[SharedData.RED_NOTE].image = generate_typeimage_local("tiletextures/rednote.png");
		myTypeDB[SharedData.BLUE_NOTE].image = generate_typeimage_local("tiletextures/bluenote.png");
		myTypeDB[SharedData.GREEN_NOTE].image = generate_typeimage_local("tiletextures/greennote.png");

		/*
		 * 
		 * 
		 * for (int i = 0; i < 4; i++) { for (int k = 0; k < 4; k++) {
		 * TypeImages_animated_directional
		 * [myshareddata.UNITMODEL_MALEHUMAN][i][k] =
		 * generate_typeimage_local("herosprites/myadv_" + i + "_" + k +
		 * ".png"); } }
		 */

		ProfileImages[0] = generate_typeimage_local("profileimages/profile_questionmark.png");
		ProfileImage_blank = generate_typeimage_local("profileimages/profile_questionmark.png");

		mySoundPaths[SOUND_COIN] = "wrap/assets/sounds/coin.wav";
		mySoundPaths[SOUND_BURNING] = "wrap/assets/sounds/effect_burn2.wav";
		mySoundPaths[SOUND_BOWANDARROW] = "wrap/assets/sounds/arrowshoot.wav";
		mySoundPaths[SOUND_DICEROLL] = "wrap/assets/sounds/diceroll1.wav";
		mySoundPaths[SOUND_EVILSPELL1] = "wrap/assets/sounds/evilspell1.wav";
		mySoundPaths[SOUND_SCREECH1] = "wrap/assets/sounds/screech1.wav";
		mySoundPaths[SOUND_STEPGRASS1] = "wrap/assets/sounds/sfx_step_grass_1.wav";
		mySoundPaths[SOUND_STEPGRASS2] = "wrap/assets/sounds/sfx_step_grass_2.wav";
		mySoundPaths[SOUND_HISS] = "wrap/assets/sounds/hiss.wav";
		mySoundPaths[SOUND_GHOSTHIT] = "wrap/assets/sounds/ghosthit2.wav";
		mySoundPaths[SOUND_ENERGYGLOW] = "wrap/assets/sounds/energyglow.wav";
		mySoundPaths[SOUND_ENERGYRIFLE] = "wrap/assets/sounds/energyrifle.wav";
		mySoundPaths[SOUND_ENERGYSHIELDBOUNCE] = "wrap/assets/sounds/energyshieldbounce.wav";
		mySoundPaths[SOUND_FIREBALL] = "wrap/assets/sounds/fireball.wav";
		mySoundPaths[SOUND_GLOWUP] = "wrap/assets/sounds/glowup.wav";
		mySoundPaths[SOUND_LASERBLASTER] = "wrap/assets/sounds/laserblaster.wav";
		mySoundPaths[SOUND_LASERHIT] = "wrap/assets/sounds/laserhit.wav";
		mySoundPaths[SOUND_MELEEGORE] = "wrap/assets/sounds/meleegore.wav";
		mySoundPaths[SOUND_MELEESMACK] = "wrap/assets/sounds/meleesmack.wav";
		mySoundPaths[SOUND_MELEETHUD] = "wrap/assets/sounds/meleethud.wav";
		mySoundPaths[SOUND_MELEETHWACK] = "wrap/assets/sounds/meleethwack.wav";
		mySoundPaths[SOUND_PHASEOUT] = "wrap/assets/sounds/phaseout.wav";
		mySoundPaths[SOUND_PLASMA] = "wrap/assets/sounds/plasma.wav";
		mySoundPaths[SOUND_POWERDOWN] = "wrap/assets/sounds/powerdown.wav";
		mySoundPaths[SOUND_POWERONLINE] = "wrap/assets/sounds/poweronline.wav";
		mySoundPaths[SOUND_SHOOTRIFLE] = "wrap/assets/sounds/shootshotgun.wav";
		mySoundPaths[SOUND_SYSTEMSBEEPING] = "wrap/assets/sounds/systemsbeeping.wav";
		mySoundPaths[SOUND_WOODEXPLODE] = "wrap/assets/sounds/woodexplode.wav";
		mySoundPaths[SOUND_GOREEXPLODE1] = "wrap/assets/sounds/bloodexplode1.wav";
		mySoundPaths[SOUND_GOREEXPLODE2] = "wrap/assets/sounds/bloodexplode2.wav";
		mySoundPaths[SOUND_GOREEXPLODE3] = "wrap/assets/sounds/bloodexplode3.wav";
		mySoundPaths[SOUND_GOREEXPLODE4] = "wrap/assets/sounds/bloodexplode4.wav";
		mySoundPaths[SOUND_DRUMLOW] = "wrap/assets/sounds/drumlow.wav";
		mySoundPaths[SOUND_DRUMLOW2] = "wrap/assets/sounds/drumlow2.wav";
		mySoundPaths[SOUND_LEATHERITEM] = "wrap/assets/sounds/leatheritem.wav";
		mySoundPaths[SOUND_PISTOLFIRE1] = "wrap/assets/sounds/pistolfire1.wav";
		mySoundPaths[SOUND_PISTOLFIRE2] = "wrap/assets/sounds/pistolfire2.wav";
		mySoundPaths[SOUND_SHOTGUNFIRE1] = "wrap/assets/sounds/shotgunfire1.wav";
		mySoundPaths[SOUND_SHOTGUNFIRE2] = "wrap/assets/sounds/shotgunfire2.wav";
		mySoundPaths[SOUND_PUNCH] = "wrap/assets/sounds/punch.wav";
		mySoundPaths[SOUND_HUMANGROAN1] = "wrap/assets/sounds/humangroan1.wav";
		mySoundPaths[SOUND_HUMANGROAN2] = "wrap/assets/sounds/humangroan2.wav";
		mySoundPaths[SOUND_HUMANGROAN3] = "wrap/assets/sounds/humangroan3.wav";
		mySoundPaths[SOUND_HUMANGROAN4] = "wrap/assets/sounds/humangroan4.wav";
		mySoundPaths[SOUND_ZOMBIE1] = "wrap/assets/sounds/zombie1.wav";
		mySoundPaths[SOUND_ZOMBIE2] = "wrap/assets/sounds/zombie2.wav";
		mySoundPaths[SOUND_ZOMBIEMUNCH] = "wrap/assets/sounds/zombiebite.wav";
		mySoundPaths[SOUND_BEAST1] = "wrap/assets/sounds/beast1.wav";
		mySoundPaths[SOUND_BEAST2] = "wrap/assets/sounds/beast2.wav";
		mySoundPaths[SOUND_EXPBARTICK] = "wrap/assets/sounds/click.wav";
		mySoundPaths[SOUND_SWISH1] = "wrap/assets/sounds/swish1.wav";
		mySoundPaths[SOUND_SWISH2] = "wrap/assets/sounds/swish2.wav";
		mySoundPaths[SOUND_SWISH3] = "wrap/assets/sounds/swish3.wav";
		mySoundPaths[SOUND_SWISH4] = "wrap/assets/sounds/swish4.wav";
		mySoundPaths[SOUND_MONSTERGRUNT1] = "wrap/assets/sounds/monstergrunt1.wav";
		mySoundPaths[SOUND_MONSTERGRUNT2] = "wrap/assets/sounds/monstergrunt2.wav";
		mySoundPaths[SOUND_MONSTERGRUNT3] = "wrap/assets/sounds/monstergrunt3.wav";
		mySoundPaths[SOUND_BUBBLE] = "wrap/assets/sounds/bubble.wav";
		mySoundPaths[SOUND_BOTTLE] = "wrap/assets/sounds/bottle.wav";

		minimenubuttons[0] = generate_typeimage_local("minibuttons/minibutton_esc.png");
		minimenubuttons[1] = generate_typeimage_local("minibuttons/minibutton_inventory.png");
		minimenubuttons[2] = generate_typeimage_local("minibuttons/minibutton_spells.png");
		minimenubuttons[3] = generate_typeimage_local("minibuttons/minibutton_quests.png");

		// invmenu_nokey = generate_typeimage_local("items/key.png");
		// invmenu_plusbutton =
		// generate_typeimage_local("gameGUI/plusbutton.png");
		escmenu_autosaveonbtn = generate_typeimage_local("menubuttons/btn_AutoSaveIsOn.png");
		escmenu_autosaveoffbtn = generate_typeimage_local("menubuttons/btn_AutoSaveIsOff.png");
		escmenu_settingsbtn = generate_typeimage_local("menubuttons/btn_Settings.png");

		AbilitySpecButtons[0] = generate_typeimage_local("gameGUI/AbilitySpecButton_Brawn.png");
		AbilitySpecButtons[1] = generate_typeimage_local("gameGUI/AbilitySpecButton_Protection.png");
		AbilitySpecButtons[2] = generate_typeimage_local("gameGUI/AbilitySpecButton_Nature.png");
		AbilitySpecButtons[3] = generate_typeimage_local("gameGUI/AbilitySpecButton_Darkness.png");
		AbilitySpecButtons[4] = generate_typeimage_local("gameGUI/AbilitySpecButton_Alchemy.png");
		AbilitySpecButtons[5] = generate_typeimage_local("gameGUI/AbilitySpecButton_Precision.png");
		AbilitySpecButtons[6] = generate_typeimage_local("gameGUI/AbilitySpecButton_Explosives.png");
		AbilitySpecButtons[7] = generate_typeimage_local("gameGUI/AbilitySpecButton_Mechatronics.png");
		AbilitySpecButtons[8] = generate_typeimage_local("gameGUI/AbilitySpecButton_Wizardry.png");
		AbilitySpecButtons[9] = generate_typeimage_local("gameGUI/AbilitySpecButton_Sorcery.png");

		AbilityMenuBG_tab1 = generate_typeimage_local("gameGUI/spellbookbg1.png");
		AbilityMenuBG_tab2 = generate_typeimage_local("gameGUI/weaponskillsbg1.png");

		Menus[MENU_LOOTING].background = generate_typeimage_local("gameGUI/lootwindow.png");
		Menus[MENU_INV].background = GenericMenuBG;
		Menus[MENU_ESC].background = GenericMenuBG;
		Menus[MENU_EDITSTATS].background = GenericMenuBG;
		Menus[MENU_QUESTTRACKER].background = GenericMenuBG;
		Menus[MENU_ITEMVENDOR].background = GenericMenuBG;
		Menus[MENU_BATTLERESULTS].background = GenericMenuBG;

		levelcircle = generate_typeimage_local("gameGUI/levelcircle.png");

		unitinfo_bg = generate_typeimage_local("gameGUI/unitinfobg.png");
		assettable_crumb = generate_typeimage_local("gameGUI/assettable_crumb.png");

		vitalicon_meleepower = generate_typeimage_local("gameGUI/meleepowericon.png");
		vitalicon_rangedpower = generate_typeimage_local("gameGUI/rangedpowericon.png");
		vitalicon_spellpower = generate_typeimage_local("gameGUI/spellpowericon.png");
		vitalicon_armor = generate_typeimage_local("gameGUI/armoricon.png");
		vitalicon_magres = generate_typeimage_local("gameGUI/magicresisticon.png");
		vitalicon_toxres = generate_typeimage_local("gameGUI/toxinresisticon.png");

		vitalicon_speed = generate_typeimage_local("gameGUI/speedicon.png");
		vitalicon_initiative = generate_typeimage_local("gameGUI/initiativeicon.png");

		assettable_tab_u = generate_typeimage_local("gameGUI/AssetTableTab_u.png");
		assettable_tab_i = generate_typeimage_local("gameGUI/AssetTableTab_i.png");
		assettable_tab_c = generate_typeimage_local("gameGUI/AssetTableTab_c.png");

		vend_tab_crafting = generate_typeimage_local("gameGUI/tab_crafting.png");
		vend_tab_buying = generate_typeimage_local("gameGUI/tab_buying.png");

		charm_crafting = generate_typeimage_local("gameGUI/charm_crafting.png");
		charm_buying = generate_typeimage_local("gameGUI/charm_buying.png");

		menu_exit_x = generate_typeimage_local("gameGUI/red_x.png");

		quest_rect = generate_typeimage_local("gameGUI/quest_rect2.png");

		red_back_arrow = generate_typeimage_local("gameGUI/redbackarrow.png");

		simpleBlackButton = generate_typeimage_local("gameGUI/blackbutton_simple.png");
		simpleGrayButton = generate_typeimage_local("gameGUI/graybutton_simple.png");

		generictextboximage = generate_typeimage_local("misc/generictextboxframe.png");

		// myTextBoxes[TEXTBOX_LOGINNAME].icon =
		// generate_typeimage_local("gameGUI/blackbutton_simple.png");

		cursor.whitesquare = generate_typeimage_local("tiletextures/highlight_white.png");
		// ability_targettinghighlightblue =
		// generate_typeimage_local("tiletextures/highlight_blue.png");
		// ability_targettinghighlightred =
		// generate_typeimage_local("tiletextures/highlight_red.png");
		ability_newstatpointtospend = generate_typeimage_local("gameGUI/newstatpoint.png");
		ability_newabilitypointtospend = generate_typeimage_local("gameGUI/newabilitypoint.png");
		// ability_godmodeenabled =
		// generate_typeimage_local("abilityicons/ability_godmodeenabled.png");
		// ability_godmodedisabled =
		// generate_typeimage_local("abilityicons/ability_godmodedisabled.png");

		// mainmenu stuff
		intropnl_logo = generate_typeimage_local("menus/BaneForgeLogoGray2.png");

		/*
		 * for (int i = 0; i < 8; i++) { button_intropnl_newhero[i] =
		 * generate_typeimage_local("menubuttons/btn_text_newhero_" + (i + 1) +
		 * ".png"); } for (int i = 0; i < 8; i++) { button_intropnl_loadhero[i]
		 * = generate_typeimage_local("menubuttons/btn_text_loadhero_" + (i + 1)
		 * + ".png"); } for (int i = 0; i < 8; i++) { button_intropnl_hostmap[i]
		 * = generate_typeimage_local("menubuttons/btn_text_hostmap_" + (i + 1)
		 * + ".png"); } for (int i = 0; i < 8; i++) {
		 * button_intropnl_joingame[i] =
		 * generate_typeimage_local("menubuttons/btn_text_joingame_" + (i + 1) +
		 * ".png"); }
		 * 
		 * for (int i = 0; i < 8; i++) { button_generic_back[i] =
		 * generate_typeimage_local("menubuttons/btn_text_back_" + (i + 1) +
		 * ".png"); } for (int i = 0; i < 8; i++) { button_generic_save[i] =
		 * generate_typeimage_local("menubuttons/btn_text_save_" + (i + 1) +
		 * ".png"); }
		 * 
		 * for (int i = 0; i < 3; i++) { button_bg_granite[i] =
		 * generate_typeimage_local("menubuttons/btn_granite" + (i + 1) +
		 * ".png"); }
		 */
		minimappreview_striped = generate_typeimage_local("menus/minimap_striped.png");
		minimappreview_border = generate_typeimage_local("menus/minimap_striped_border.png");

		/*
		 * staticon_health =
		 * generate_typeimage_local("gameGUI/staticon_health.png");
		 * staticon_energy =
		 * generate_typeimage_local("gameGUI/staticon_energy.png");
		 * staticon_power =
		 * generate_typeimage_local("gameGUI/staticon_damage.png");
		 * staticon_armor =
		 * generate_typeimage_local("gameGUI/staticon_armor.png");
		 */

		hovertooltip_bot = generate_typeimage_local("misc/tooltip_bottom.png");
		hovertooltip_mid = generate_typeimage_local("misc/tooltip_middle.png");
		hovertooltip_top = generate_typeimage_local("misc/tooltip_top.png");

		chatbubble_bot = generate_typeimage_local("misc/chatbubble_bottom_small.png");
		chatbubble_mid = generate_typeimage_local("misc/chatbubble_middle_small.png");
		chatbubble_top = generate_typeimage_local("misc/chatbubble_top_small.png");

		loadscreen_continue = generate_typeimage_local("menus/load_continue.png");
		for (int i = 0; i < 4; i++) {
			loadscreen_stagetext[i] = generate_typeimage_local("menus/load_stage" + (i + 1) + ".png");
		}

		volume_on = generate_typeimage_local("menus/icon_volume_on.png");
		volume_off = generate_typeimage_local("menus/icon_volume_off.png");

		smallfolder = generate_typeimage_local("menus/smallfolder.png");
		smallgear = generate_typeimage_local("menus/gearicon.png");
		bookicon = generate_typeimage_local("misc/bookicon.png");
		editicon = generate_typeimage_local("misc/editicon.png");

		endturnbutton = generate_typeimage_local("minibuttons/button_endturn.png");
		endturnbutton_transparent = generate_typeimage_local("minibuttons/button_endturn_transparent.png");

		white_arrow[0] = generate_typeimage_local("arrows/arrow_up.png");
		white_arrow[1] = generate_typeimage_local("arrows/arrow_right.png");
		white_arrow[2] = generate_typeimage_local("arrows/arrow_down.png");
		white_arrow[3] = generate_typeimage_local("arrows/arrow_left.png");

		cursor.whiteselector = generate_typeimage_local("misc/DMCursor.png");
		// cursor.greenselector =
		// generate_typeimage_local("misc/DMCursor3.png");
		cursor.heroselector_back = generate_typeimage_local("misc/selectioncircle_back.png");
		cursor.heroselector_front = generate_typeimage_local("misc/selectioncircle_front.png");

		/*
		 * ability_icons[0] =
		 * generate_typeimage_local("abilityicons/ability_blank.png");
		 * 
		 * ability_icons[ICON_WALK] =
		 * generate_typeimage_local("abilityicons/ability_walk.png");
		 * ability_icons[ICON_FIREBALL] =
		 * generate_typeimage_local("abilityicons/ability_fireball.png");
		 * ability_icons[ICON_SLASH] =
		 * generate_typeimage_local("abilityicons/ability_slash.png");
		 * ability_icons[ICON_SHOOTARROW] =
		 * generate_typeimage_local("abilityicons/ability_shootarrow.png");
		 */

		button_editstats.background = generate_typeimage_local("gameGUI/dmeditstatsicon.png");
		button_moveunit.background = generate_typeimage_local("gameGUI/dmmoveuniticon.png");
		button_editinv.background = generate_typeimage_local("gameGUI/dmeditinvicon.png");

		D20_image = generate_typeimage_local("gameGUI/blankD20.png");

		abilityicon_frame = generate_typeimage_local("gameGUI/ability_frame.png");

	}

	void LoadGameDB() {

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

		/*
		 * // THESE ARE FOR THE MINIMAP setTypeColors(16, STONETILE_1, 69, 69,
		 * 69); setTypeColors(16, GRAYBRICK_1, 169, 169, 169); setTypeColors(16,
		 * GRASS_1, 20, 100, 20); setTypeColors(16, REDBRICK_1, 240, 220, 200);
		 * setTypeColors(16, BROWNDIRT_1, 160, 80, 40); setTypeColors(16,
		 * BLACKDIRT_1, 33, 33, 33); setTypeColors(16, SAND_1, 234, 223, 134);
		 * setTypeColors(16, LAKEWATER_1,70,130,180);
		 */

		// make these parameters the defaults in the map editor, but allow the
		// map editor to change them!!!!
		myTypeDB[SharedData.MAGICCHEST_BLACK].Can_Contain_Items = true;// basic
																		// weapons
		myTypeDB[SharedData.MAGICCHEST_BLACK].Items_Dropped_areInstanced = true;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroups[0] = 13;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroupchances[0] = 10;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroups[1] = 16;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroupchances[1] = 10;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroups[2] = 19;
		myTypeDB[SharedData.MAGICCHEST_BLACK].LootTable_itemgroupchances[2] = 10;

		myTypeDB[SharedData.MAGICCHEST_RED].Can_Contain_Items = true;// rare
		myTypeDB[SharedData.MAGICCHEST_RED].Items_Dropped_areInstanced = true;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroups[0] = 8;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroupchances[0] = 10;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroups[1] = 9;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroupchances[1] = 10;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroups[2] = 10;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroupchances[2] = 10;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroups[3] = 11;
		myTypeDB[SharedData.MAGICCHEST_RED].LootTable_itemgroupchances[3] = 10;

		myTypeDB[SharedData.MAGICCHEST_GREEN].Can_Contain_Items = true;// custom
		myTypeDB[SharedData.MAGICCHEST_GREEN].Items_Dropped_areInstanced = true;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroups[0] = 21;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroupchances[0] = 50;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroups[1] = 22;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroupchances[1] = 50;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroups[2] = 23;
		myTypeDB[SharedData.MAGICCHEST_GREEN].LootTable_itemgroupchances[2] = 50;

		myTypeDB[SharedData.MAGICCHEST_BLUE].Can_Contain_Items = true;// misc
		myTypeDB[SharedData.MAGICCHEST_BLUE].Items_Dropped_areInstanced = true;
		myTypeDB[SharedData.MAGICCHEST_BLUE].LootTable_itemgroups[0] = 1;
		myTypeDB[SharedData.MAGICCHEST_BLUE].LootTable_itemgroupchances[0] = 50;
		myTypeDB[SharedData.MAGICCHEST_BLUE].LootTable_itemgroups[1] = 2;
		myTypeDB[SharedData.MAGICCHEST_BLUE].LootTable_itemgroupchances[1] = 50;

		myTypeDB[SharedData.MAGICCHEST_WHITE].Can_Contain_Items = true;// common
																		// mats
		myTypeDB[SharedData.MAGICCHEST_WHITE].Items_Dropped_areInstanced = true;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroups[0] = 4;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroupchances[0] = 20;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroups[1] = 5;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroupchances[1] = 20;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroups[2] = 6;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroupchances[2] = 20;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroups[3] = 7;
		myTypeDB[SharedData.MAGICCHEST_WHITE].LootTable_itemgroupchances[3] = 20;

		myTypeDB[SharedData.WOOD_CHEST].Can_Contain_Items = true;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroups[0] = 4;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroupchances[0] = 20;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroups[1] = 5;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroupchances[1] = 20;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroups[2] = 6;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroupchances[2] = 20;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroups[3] = 7;
		myTypeDB[SharedData.WOOD_CHEST].LootTable_itemgroupchances[3] = 20;

		/*
		 * String[] lootgroupdropdown_Strings = {"None", "Misc 1", "Misc 2",
		 * "Misc 3", "Common Resources", "Common Materials", "Common Ores",
		 * "Common Ingredients", "Rare Resources", "Rare Materials",
		 * "Rare Ores", "Rare Ingredients", "Weapons 1", "Weapons 2",
		 * "Weapons 3", "Armor 1", "Armor 2", "Armor 3", "Magic Items 1",
		 * "Magic Items 2", "Magic Items 3", "Custom Group 1", "Custom Group 2",
		 * "Custom Group 3", "Custom Group 4", "Custom Group 5"};
		 */

		myTypeDB[SharedData.TREASURECHEST].Can_Contain_Items = true;
		myTypeDB[SharedData.TREASURECHEST_2].Can_Contain_Items = true;
		myTypeDB[SharedData.ASHES].Can_Contain_Items = true;

		for (int i = 0; i < 20; i++) {
			myTextBoxes[i] = new TextBox();
		}

		for (int i = 0; i < 10; i++) {
			BackButton[i] = new SimpleGuiObject();
		}
		for (int i = 0; i < 10; i++) {
			ForwardButton[i] = new SimpleGuiObject();
		}

		// textbox defaults
		myTextBoxes[TEXTBOX_HOSTPORT].text = "" + 36550;

		for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {

			int j = i + SharedData.ENDURANCE;

			default_hero_stats[j] = 10;// defaults
		}

		default_hero_stats[SharedData.FACING] = 2;
		default_hero_stats[SharedData.SPEED] = 10;

		default_NPC_stats[SharedData.ENDURANCE] = 5;
		default_NPC_stats[SharedData.MELEEPOWER] = 1;
		default_NPC_stats[SharedData.ARMORRATING] = 1;
		default_NPC_stats[SharedData.STAMINA] = 5;
		default_NPC_stats[SharedData.EXP_REWARD] = 2;

		// IF THESE ARE MISSING THEN THE GAME WILL BUG OUT

		// declare type strings

		myTypeDB[SharedData.TORCH].namestring = new String("Torch");

		// fix add more

		myAbilityDB[SPECIALABIL_DMMOVE].namestring = "Super Move";
		myAbilityDB[SPECIALABIL_DMMOVE].enabled = true;
		myAbilityDB[SPECIALABIL_DMMOVE].description = "Meta Ability";
		myAbilityDB[SPECIALABIL_DMMOVE].icon_ID = 0;
		myAbilityDB[SPECIALABIL_DMMOVE].abilityspec = 0;
		myAbilityDB[SPECIALABIL_DMMOVE].requires_targetting = true;
		myAbilityDB[SPECIALABIL_DMMOVE].constrain_to_cardinal_direction = false;
		myAbilityDB[SPECIALABIL_DMMOVE].cardinal_direction_distance_override = 0;
		myAbilityDB[SPECIALABIL_DMMOVE].can_target_where_no_units_exist = true;

		myAbilityDB[SPECIALABIL_DMMOVE].cast_range_base = 999;

		myAbilityDB[SPECIALABIL_DMMOVE].effects[0] = SPECIALEFFECT_DMMOVE;

		myAbilityDB[SPECIALABIL_WALK].namestring = "Walk";
		myAbilityDB[SPECIALABIL_WALK].enabled = true;
		myAbilityDB[SPECIALABIL_WALK].description = "Walk in a cardinal direction.";
		myAbilityDB[SPECIALABIL_WALK].icon_ID = 85;
		myAbilityDB[SPECIALABIL_WALK].abilityspec = 0;
		myAbilityDB[SPECIALABIL_WALK].cast_animation = myshareddata.ANIM_WALK; // make
																				// this
																				// work!!!
		myAbilityDB[SPECIALABIL_WALK].requires_targetting = true;
		myAbilityDB[SPECIALABIL_WALK].constrain_to_cardinal_direction = true;
		myAbilityDB[SPECIALABIL_WALK].cardinal_direction_distance_override = 1;
		myAbilityDB[SPECIALABIL_WALK].can_target_where_no_units_exist = true;

		myAbilityDB[SPECIALABIL_WALK].require_targetpoint_walkable = true;
		myAbilityDB[SPECIALABIL_WALK].cast_range_base = 0;
		// myAbilityDB[i].stat_increasing_range =
		// StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		// myAbilityDB[i].percent_factor_of_stat_increasing_range =
		// StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		myAbilityDB[SPECIALABIL_WALK].energy_cost_base = 0;

		myAbilityDB[SPECIALABIL_WALK].counts_as_a_movement = true;
		// myAbilityDB[i].counts_as_an_attack =
		// StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		myAbilityDB[SPECIALABIL_WALK].effects[1] = SPECIALEFFECT_WALK;
		myAbilityDB[SPECIALABIL_WALK].effects[2] = SPECIALEFFECT_WALKSOUND;

		myEffectDB[SPECIALEFFECT_DMMOVE].namestring = "special move unit";
		myEffectDB[SPECIALEFFECT_DMMOVE].enabled = true;
		myEffectDB[SPECIALEFFECT_DMMOVE].action = EFFECT_ACTION_MOVEUNIT;
		myEffectDB[SPECIALEFFECT_DMMOVE].flip_cast_coords = false;

		myEffectDB[SPECIALEFFECT_DMMOVE].can_affect_allies = true;
		myEffectDB[SPECIALEFFECT_DMMOVE].can_affect_caster = true;
		myEffectDB[SPECIALEFFECT_DMMOVE].can_affect_enemies = true;
		myEffectDB[SPECIALEFFECT_DMMOVE].movement_animation = 0;
		myEffectDB[SPECIALEFFECT_DMMOVE].movement_speed = 12;

		myEffectDB[SPECIALEFFECT_WALK].namestring = "simple walk effect";
		myEffectDB[SPECIALEFFECT_WALK].enabled = true;
		myEffectDB[SPECIALEFFECT_WALK].action = EFFECT_ACTION_MOVEUNIT;
		myEffectDB[SPECIALEFFECT_WALK].flip_cast_coords = false;
		myEffectDB[SPECIALEFFECT_WALK].trajectory_collision = true;
		myEffectDB[SPECIALEFFECT_WALK].trajectory_endsontop = false;
		// myEffectDB[i].shape_type =
		// StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		// myEffectDB[i].shape_radius =
		// StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		// myEffectDB[i].shape_angle_spread =
		// StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
		// current_parameter_number++;
		myEffectDB[SPECIALEFFECT_WALK].can_affect_allies = true;
		myEffectDB[SPECIALEFFECT_WALK].can_affect_caster = true;
		myEffectDB[SPECIALEFFECT_WALK].can_affect_enemies = false;
		myEffectDB[SPECIALEFFECT_WALK].movement_animation = myshareddata.ANIM_WALK;
		myEffectDB[SPECIALEFFECT_WALK].movement_speed = 1;

		myEffectDB[SPECIALEFFECT_WALKSOUND].namestring = "play sound";
		myEffectDB[SPECIALEFFECT_WALKSOUND].enabled = true;
		myEffectDB[SPECIALEFFECT_WALKSOUND].action = EFFECT_ACTION_SOUND;
		myEffectDB[SPECIALEFFECT_WALKSOUND].SOUND_ID = SOUND_STEPGRASS_RANDOM;

		// Image2D[] tempimagearray = new Image2D[8];

		myProjectileModels[PROJECTILE_FIREBALL].sheet = generate_typeimage_local("projectiles/projsheet_fireball.png");
		myProjectileModels[PROJECTILE_FIREBALL].concentric = false;
		myProjectileModels[PROJECTILE_FIREBALL].animationframes = 8;

		myProjectileModels[PROJECTILE_ICICLE].sheet = generate_typeimage_local("projectiles/projsheet_icicle.png");
		myProjectileModels[PROJECTILE_ICICLE].concentric = false;
		myProjectileModels[PROJECTILE_ICICLE].animationframes = 8;

		myProjectileModels[PROJECTILE_LIGHTNING].sheet = generate_typeimage_local("projectiles/projsheet_lightning.png");
		myProjectileModels[PROJECTILE_LIGHTNING].concentric = false;
		myProjectileModels[PROJECTILE_LIGHTNING].animationframes = 4;

		myProjectileModels[PROJECTILE_ARROW].sheet = generate_typeimage_local("projectiles/staticprojectiles.png");
		myProjectileModels[PROJECTILE_ARROW].concentric = false;
		myProjectileModels[PROJECTILE_ARROW].animationframes = 1;
		myProjectileModels[PROJECTILE_ARROW].sheetindex = 0;

		myProjectileModels[PROJECTILE_JAVELIN].sheet = generate_typeimage_local("projectiles/staticprojectiles.png");
		myProjectileModels[PROJECTILE_JAVELIN].concentric = false;
		myProjectileModels[PROJECTILE_JAVELIN].animationframes = 1;
		myProjectileModels[PROJECTILE_JAVELIN].sheetindex = 1;

		myProjectileModels[PROJECTILE_BOLAS].sheet = generate_typeimage_local("projectiles/staticprojectiles.png");
		myProjectileModels[PROJECTILE_BOLAS].concentric = true;
		myProjectileModels[PROJECTILE_BOLAS].animationframes = 8;
		myProjectileModels[PROJECTILE_BOLAS].sheetindex = 2;

		myProjectileModels[PROJECTILE_BOULDER].sheet = generate_typeimage_local("projectiles/staticprojectiles.png");
		myProjectileModels[PROJECTILE_BOULDER].concentric = true;
		myProjectileModels[PROJECTILE_BOULDER].animationframes = 8;
		myProjectileModels[PROJECTILE_BOULDER].sheetindex = 3;

		myProjectileModels[PROJECTILE_NET].sheet = generate_typeimage_local("projectiles/staticprojectiles.png");
		myProjectileModels[PROJECTILE_NET].concentric = true;
		myProjectileModels[PROJECTILE_NET].animationframes = 1;
		myProjectileModels[PROJECTILE_NET].sheetindex = 4;
		myProjectileModels[PROJECTILE_NET].rowindex = 0;

		endturnarrows = generate_typeimage_local("minibuttons/button_endturnarrow.png");

	}// /end load DB

	// private SoundManager soundManager;
	boolean GameRunning = true;

	public void initgameOpenGL() {

		BackgroundMusic.pause();

		finishMapLoad();

		if (myPNum == 1) {
			DMLighting();
			current_MainMenu_Screen = -1;
		}

		if (myPNum != 1) {
			CalculateClientHeroLighting();
		}

		finished_map_download = true;
		// then wait for a click
	}

	private boolean setDisplayMode() {
		try {

			if (mySettings.FullScreen) {

				// width is becoming 1024 when going from windows to fullscreen
				// while in game!!!
				SCREEN_HEIGHT = MONITOR_HEIGHT;
				SCREEN_WIDTH = MONITOR_WIDTH;

				DisplayMode myMode = null;
				DisplayMode[] modes = Display.getAvailableDisplayModes();

				for (int i = 0; i < modes.length; i++) {
					if (modes[i].getWidth() == SCREEN_WIDTH && modes[i].getHeight() == SCREEN_HEIGHT && modes[i].isFullscreenCapable()) {
						myMode = modes[i];
					}
				}

				Display.setFullscreen(true);
				Display.setResizable(false);

			} else {

				Display.setFullscreen(false);// make sure this works!!!
				Display.setResizable(true);

				DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(SCREEN_WIDTH, SCREEN_HEIGHT, -1, -1, -1, -1, 60, 60);

				org.lwjgl.util.Display.setDisplayMode(dm, new String[]{"width=" + SCREEN_WIDTH, "height=" + SCREEN_HEIGHT, "freq=" + 60,
						"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()});

			}

			// the rest of the stuffs
			if (!Display.isCreated()) {
				Display.create();
			}

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

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("Unable to enter fullscreen, continuing in windowed mode");

		}

		return false;

	}

	private boolean refreshDisplayMode() {// BROKEN
		try {

			if (mySettings.FullScreen) {

				// width is becoming 1024 when going from windows to fullscreen
				// while in game!!!

				/*
				 * DisplayMode myMode = null; DisplayMode[] modes =
				 * Display.getAvailableDisplayModes();
				 * 
				 * for (int i = 0; i < modes.length; i++) { if
				 * (modes[i].getWidth() == MONITOR_WIDTH && modes[i].getHeight()
				 * == MONITOR_HEIGHT && modes[i].isFullscreenCapable()) { myMode
				 * = modes[i]; } }
				 */

				Display.setFullscreen(true);
				Display.setResizable(false);

				// SCREEN_HEIGHT = MONITOR_HEIGHT;
				// SCREEN_WIDTH = MONITOR_WIDTH;

				// enable textures since we're going to use these for our
				// sprites
				glEnable(GL_TEXTURE_2D);

				Display.setVSyncEnabled(true);

				// disable the OpenGL depth test since we're rendering 2D
				// graphics
				glDisable(GL_DEPTH_TEST);

				glMatrixMode(GL_PROJECTION);

				glLoadIdentity();

				glOrtho(0, MONITOR_WIDTH, MONITOR_HEIGHT, 0, -1, 1);

				glMatrixMode(GL_MODELVIEW);

				glLoadIdentity();

				glViewport(0, 0, MONITOR_WIDTH, MONITOR_HEIGHT);

			} else {

				Display.setFullscreen(false);// make sure this works!!!
				Display.setResizable(true);

				DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(SCREEN_WIDTH, SCREEN_HEIGHT, -1, -1, -1, -1, 60, 60);

				org.lwjgl.util.Display.setDisplayMode(dm, new String[]{"width=" + SCREEN_WIDTH, "height=" + SCREEN_HEIGHT, "freq=" + 60,
						"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()});

				// enable textures since we're going to use these for our
				// sprites
				glEnable(GL_TEXTURE_2D);

				Display.setVSyncEnabled(true);

				// disable the OpenGL depth test since we're rendering 2D
				// graphics
				glDisable(GL_DEPTH_TEST);

				glMatrixMode(GL_PROJECTION);

				glLoadIdentity();

				glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);

				glMatrixMode(GL_MODELVIEW);

				glLoadIdentity();

				glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

			}

			// the rest of the stuffs
			if (!Display.isCreated()) {
				Display.create();
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("Unable to enter fullscreen, continuing in windowed mode");

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

		/*
		 * new Thread(new Runnable() {//start the music thread public void run()
		 * { while(!Display.isCloseRequested() && GameRunning){ int
		 * time_since_last_poll = (int) delta;//may have to be scaled!
		 * BackgroundMusic.poll(time_since_last_poll);//stream ze musikz! } }
		 * }).start();
		 */

		while (!Display.isCloseRequested() && GameRunning) {

			// these do not work when JFrame is open... oh well
			int time_since_last_poll = (int) delta;// may have to be scaled!
			BackgroundMusic.poll(time_since_last_poll);// stream ze musikz!

			if (current_MainMenu_Screen >= 0) {
				setMainMenuGUIPositions();
			}

			config_UI_sizes_continuous();

			/** make sure resizing on the fly doesn't break anything */
			glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();

			GL11.glLineWidth(2f);

			cursor.Poll_Mouse_Position(SCREEN_HEIGHT, cam, TILE_SIZE, SharedData.MAP_SIZE, SCREEN_X_TILES, SCREEN_Y_TILES);

			if (current_MainMenu_Screen >= 0) {

				handleMainMenutweening();

				poll_timers();

				if (queued_MainMenu_Screen == current_MainMenu_Screen) {
					pollInput_MenuMouse();
					pollInput_MenuKeyboard();
				}

				set_inputs_already_down();

				menuRendering();

				// TWL_gui.update();
				Display.update(); // removing this does not fix anything!
				// TestUtils.reduceInputLag();
				Display.sync(60);
			} else {

				poll_timers();

				grabNetworkingData();

				// handleUnitAnimations();

				if (!focused_GUI) {// only poll for game inputs when not focused
									// on
									// the GUI!!!
					if (myPNum == 1) {
						pollInput_DM();
					} else {
						pollInput_Hero();
					} // look for inputs and do things with them
					pollInput_Keyboard();
				}

				set_inputs_already_down();

				gameRendering();// let paint on the display (buffered)

				calculateLightingEveryFrame();// must come after timers to
				// grabprocedural lit, after
				// render to prevent flicker

				gameMusic();

				// TWL_gui.update(); DISABLED BECAUSE BUGGY
				Display.update(); // removing this does not fix anything!
				// TestUtils.reduceInputLag(); DISABLED BECAUSE BUGGY
				Display.sync(60);
			}
		}

		// clean up

		if (myPNum >= 2) {
			networkThread_cli.sendMessage("/bye!");
		}

		MYSQL_StopHostingServer();// only occurs in an IDEAL situation! not
									// always! Still have to look at timestamp
									// time for being recent to 2 hours.

		debug("Cleaning up!");

		// soundManager.destroy();
		// frm.dispose();
		Display.destroy();
		System.exit(0);
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
		} catch (InterruptedException inte) {}
	}

	void grabNetworkingData() {

		if (myPNum == 1) {

			for (int i = 2; i < 9; i++) {
				if (ShouldSendMessagesToPlayer(i)) {
					if (networkThread_serv.t[i].myBufferedImageReady) {
						LoadProfileImageWithBufferedImage(i, networkThread_serv.t[i].mybufferedBufferedimg);
						networkThread_serv.t[i].myBufferedImageReady = false;
					}
				}
			}

		} else {
			if (networkThread_cli.PlayerProfileImageReady > 0) {
				LoadProfileImageWithBufferedImage(networkThread_cli.PlayerProfileImageReady, networkThread_cli.mybufferedBufferedimg);
				networkThread_cli.PlayerProfileImageReady = 0;
			}

		}

	}

	int Timer_main;
	int counter_nextturn;

	void poll_timers() {

		tileAnimationTimerTick(); // This is not dependent on delta and
		// will
		// actually slow down with
		// framerate.
		// That is OK

		tileSlideTimerTick();// every frame, slide objects around. This
		// should be altered so that if
		// framerate
		// dives, this just has more of an
		// effect
		// per fire! use the var Delta

		if (Timer_main >= 5000) {
			Timer_main = 0;

			if (!BattlePhaseEngaged && current_MainMenu_Screen < 0 && counter_nextturn >= 4) {
				counter_nextturn = 0;
				if (myPNum == 1 && game_fully_loaded) {
					Server_NextTurn_AllUnitStats();
				}

			} else {
				counter_nextturn++;
			}

		} else {
			Timer_main += delta;
		}

		int animtimemultiplier = 4;
		// if delta...
		if (delta > 30) {
			animtimemultiplier = 2;
		} else if (delta > 45) {
			animtimemultiplier = 1;
		}

		if (animationframe % animtimemultiplier == 0) {

			ExecuteEvenlyTimedEvents();

		}

		// mousedowntimertick
		if (counter_mousehelddown >= 0 && counter_mousehelddown < 100000) {
			counter_mousehelddown += delta;
		}

		if (cursor.stillcounter >= 0 && cursor.stillcounter < 100000) {
			cursor.stillcounter += delta;
		}

		if (chat_display_timer >= 0) {
			chat_display_timer -= delta;
		}

		if (storylog_display_timer >= 0) {
			storylog_display_timer -= delta;
		}

		if (PopUpMessageTimeLeft >= 0) {
			PopUpMessageTimeLeft -= delta;
		}

		if (Menus[MENU_ASSETTABLE].tweencount > 0) {
			Menus[MENU_ASSETTABLE].tweencount -= delta;
		} else {
			Menus[MENU_ASSETTABLE].tweencount = 0;
		}

		if (Menus[MENU_ITEMVENDOR].tweencount > 0) {
			Menus[MENU_ITEMVENDOR].tweencount -= delta;
		} else {
			Menus[MENU_ITEMVENDOR].tweencount = 0;
		}

		if (Menus[MENU_LOGIN].tweencount > 0) {
			Menus[MENU_LOGIN].tweencount -= delta * 2;
		} else {
			Menus[MENU_LOGIN].tweencount = 0;
		}

		if (SleekMessageBox.tweencount > 0) {
			SleekMessageBox.tweencount -= 10;
		} else {
			SleekMessageBox.tweencount = 0;
		}

		if (Menus[MENU_TUTORIAL].tweencount > 0) {
			Menus[MENU_TUTORIAL].tweencount -= 10;
		} else {
			Menus[MENU_TUTORIAL].tweencount = 0;
		}

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {
			if (Units[i] != null) {
				if (Units[i].chat_bubble_timeleft > 0) {
					Units[i].chat_bubble_timeleft -= delta;
					if (Units[i].chat_bubble_timeleft <= 0) {
						Units[i].chat_bubble_timeleft = 0;
					}
				}
			}
		}

		for (int i = 0; i < NUMBER_OF_PARTICLE_EMITTERS; i++) {
			if (myEmitters[i].IsActive) {
				if (myEmitters[i].count > 0) {
					myEmitters[i].count -= delta;
					if (myEmitters[i].count <= 0) {
						myEmitters[i].count = 0;
						myEmitters[i].IsActive = false;
					}
				}
			}

		}

	}

	void ExecuteEvenlyTimedEvents() {

		handleUnitAnimations();

		// count down OverlayGFX frames

		for (int i = 0; i <= number_of_OverlayGFX; i++) {
			if (myOverlayGFX[i].enabled) {

				if (myOverlayGFX[i].currentframe == 0) {

					if (myOverlayGFX[i].animate_continuously || myOverlayGFX[i].duration_left > 0) {
						myOverlayGFX[i].currentframe = MAX_OVERLAY_GFX_FRAMES - 1;
					} else {
						debug("delete because not animating continuously!-----1-----");
						if (myOverlayGFX[i].duration_left == 0) {
							DeleteOverlayGFX(i); // Host is doing this too often
													// for some reason
						}
					}

				} else {

					if (myOverlayGFX[i].currentframe > 0) {

						myOverlayGFX[i].currentframe--;
						if (myOverlayGFX[i].currentframe == 0) {

							if (myOverlayGFX[i].animate_continuously) {
								myOverlayGFX[i].currentframe = MAX_OVERLAY_GFX_FRAMES - 1;
							} else {
								debug("delete because not animating continuously!-----2-----");
								if (myOverlayGFX[i].duration_left == 0) {
									DeleteOverlayGFX(i); // Host is doing this
															// too often for
															// some reason
								}
							}

						}

					}
				}

			}
		}

	}

	int mycounter = 0;

	void handleUnitAnimations() {// just set a hero to an animation and it will
									// play it, then return to stand!

		if (mycounter < 16) {
			mycounter++;
		} else {
			mycounter = 0;
		}

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {
			if (Units[i] != null) {

				if (mycounter == i % 16) {

					if (i < 10) {
						if (Players[i].IsConnected) {
							int truemodelID = GetEffectiveUnitStat(i, myshareddata.MODEL_ID, 0);
							if (Units[i].current_model != truemodelID) {
								Units[i].current_model = truemodelID;
							}
						}
					} else {
						int truemodelID = GetEffectiveUnitStat(i, myshareddata.MODEL_ID, 0);
						if (Units[i].current_model != truemodelID) {
							Units[i].current_model = truemodelID;
						}
					}
				}

				// make it so when a unit dies, the Death Animation is started,
				// and its current frame does not get reset to 0.. ever
				int model_ID = Units[i].current_model;
				Model thismodel = myUnitModels[model_ID];

				if (Units[i].current_animation == SharedData.ANIM_WALK) {// handle
																			// walking
																			// animation

					if (Units[i].is_sliding) {
						if (Units[i].current_animation_frame < thismodel.NumAnimationFrames[Units[i].current_animation] - 1) {
							Units[i].current_animation_frame++;
						} else {
							Units[i].current_animation_frame = 0;
						}

					} else {
						Units[i].current_animation_frame = 0;
						Units[i].current_animation = 0;
					}

				} else {// handle normal
						// animations
						// that just
						// loop
					if (Units[i].current_animation_frame < thismodel.NumAnimationFrames[Units[i].current_animation] - 1) {
						Units[i].current_animation_frame++;
					} else {

						if (Units[i].current_animation != SharedData.ANIM_DEATH) {
							Units[i].current_animation_frame = 0;
							Units[i].current_animation = 0;
						}
					}

				} /*
				 * else {// dead Units[i].current_animation_frame = 0;
				 * 
				 * }
				 */

			}

		}

	}

	void handleMainMenutweening() {

		long tween_amt = delta * 4;

		if (queued_MainMenu_Screen != current_MainMenu_Screen) {

			if (MainMenu_tween_x > 0) {
				MainMenu_tween_x += tween_amt;
			}
			if (MainMenu_tween_x < 0) {
				MainMenu_tween_x -= tween_amt;
			}
			if (MainMenu_tween_y > 0) {
				MainMenu_tween_y += tween_amt;
			}
			if (MainMenu_tween_y < 0) {
				MainMenu_tween_y -= tween_amt;
			}

			if (Math.abs(MainMenu_tween_x) > SCREEN_WIDTH || Math.abs(MainMenu_tween_y) > SCREEN_HEIGHT) {
				ChangeMainMenuScreen();
			}

		}
	}

	void setMainMenuGUIPositions() {

		for (int i = 0; i < BackButton.length; i++) {
			BackButton[i].width = 30 + ((5 * BackButton[i].tweencount) / BackButton[i].MAX_TWEEN);
			BackButton[i].height = 300 + ((20 * BackButton[i].tweencount) / BackButton[i].MAX_TWEEN);;
			BackButton[i].x = 0;
			BackButton[i].y = SCREEN_HEIGHT / 2 - BackButton[i].height / 2;
			if (BackButton[i].BeingHovered(cursor)) {
				BackButton[i].tweenForward((int) delta);
			} else {
				BackButton[i].tweenBackward((int) delta);
			}
		}

		for (int i = 0; i < ForwardButton.length; i++) {
			ForwardButton[i].width = 30 + ((5 * ForwardButton[i].tweencount) / ForwardButton[i].MAX_TWEEN);
			ForwardButton[i].height = 300 + ((20 * ForwardButton[i].tweencount) / ForwardButton[i].MAX_TWEEN);;
			ForwardButton[i].x = SCREEN_WIDTH - ForwardButton[i].width;
			ForwardButton[i].y = SCREEN_HEIGHT / 2 - ForwardButton[i].height / 2;
			if (ForwardButton[i].BeingHovered(cursor)) {
				ForwardButton[i].tweenForward((int) delta);
			} else {
				ForwardButton[i].tweenBackward((int) delta);
			}
		}

		myTextBoxes[TEXTBOX_JOINIP].x = SCREEN_WIDTH / 2 - 180;
		myTextBoxes[TEXTBOX_JOINIP].y = SCREEN_HEIGHT - 220;
		myTextBoxes[TEXTBOX_JOINIP].width = 175;
		myTextBoxes[TEXTBOX_JOINIP].height = 40;
		myTextBoxes[TEXTBOX_JOINIP].label = "IP Address";
		myTextBoxes[TEXTBOX_JOINIP].menuscreen = MENUSCREEN_JOINSERVER;

		myTextBoxes[TEXTBOX_JOINPORT].x = SCREEN_WIDTH / 2 + 20;
		myTextBoxes[TEXTBOX_JOINPORT].y = SCREEN_HEIGHT - 220;
		myTextBoxes[TEXTBOX_JOINPORT].width = 90;
		myTextBoxes[TEXTBOX_JOINPORT].height = 40;
		myTextBoxes[TEXTBOX_JOINPORT].maxtextlength = 6;
		myTextBoxes[TEXTBOX_JOINPORT].label = "Port";
		myTextBoxes[TEXTBOX_JOINPORT].menuscreen = MENUSCREEN_JOINSERVER;

		myTextBoxes[TEXTBOX_HOSTPORT].x = SCREEN_WIDTH / 2 - 90 + MainMenu_tween_x;
		myTextBoxes[TEXTBOX_HOSTPORT].y = SCREEN_HEIGHT - 100 + MainMenu_tween_y;
		myTextBoxes[TEXTBOX_HOSTPORT].width = 175;
		myTextBoxes[TEXTBOX_HOSTPORT].height = 40;
		myTextBoxes[TEXTBOX_HOSTPORT].maxtextlength = 6;
		myTextBoxes[TEXTBOX_HOSTPORT].label = "Port";
		myTextBoxes[TEXTBOX_HOSTPORT].menuscreen = MENUSCREEN_CHOOSECAMPAIGN;

		myTextBoxes[TEXTBOX_CAMPAIGNNAME].x = SCREEN_WIDTH / 2 + 140 + MainMenu_tween_x;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].y = 100 + MainMenu_tween_y;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].width = 300;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].height = 40;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].maxtextlength = 40;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].label = "Campaign Title";
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_CAMPAIGNNAME].menuscreen = MENUSCREEN_NEWCAMPAIGN;

		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].label = "Description";
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].x = SCREEN_WIDTH / 2 + 140 + MainMenu_tween_x;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].y = 180 + MainMenu_tween_y;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].width = 300;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].height = 150;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].maxtextlength = 300;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].multilined = true;
		myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].menuscreen = MENUSCREEN_NEWCAMPAIGN;

		// myTextBoxes[5].label = "Hometown:";
		// myTextBoxes[6].label = "Height:";
		// myTextBoxes[7].label = "Weight:";

		// myTextBoxes[i].isVisible=true;
		myTextBoxes[TEXTBOX_HERONAME].label = "Character Name";
		myTextBoxes[TEXTBOX_HERONAME].x = SCREEN_WIDTH / 2 - 420 + MainMenu_tween_x;
		myTextBoxes[TEXTBOX_HERONAME].y = 80 + 80 * (0) + MainMenu_tween_y;
		myTextBoxes[TEXTBOX_HERONAME].width = 240;
		myTextBoxes[TEXTBOX_HERONAME].height = 40;
		myTextBoxes[TEXTBOX_HERONAME].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_HERONAME].menuscreen = MENUSCREEN_NEWHERO;

		myTextBoxes[TEXTBOX_HERODESCRIP].label = "Description";
		myTextBoxes[TEXTBOX_HERODESCRIP].x = SCREEN_WIDTH / 2 - 420 + MainMenu_tween_x;
		myTextBoxes[TEXTBOX_HERODESCRIP].y = 80 + 80 * (1) + MainMenu_tween_y;
		myTextBoxes[TEXTBOX_HERODESCRIP].width = 240;
		myTextBoxes[TEXTBOX_HERODESCRIP].height = 150;
		myTextBoxes[TEXTBOX_HERODESCRIP].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_HERODESCRIP].maxtextlength = 300;
		myTextBoxes[TEXTBOX_HERODESCRIP].multilined = true;
		myTextBoxes[TEXTBOX_HERODESCRIP].menuscreen = MENUSCREEN_NEWHERO;

		myTextBoxes[TEXTBOX_LOGINNAME].label = "Username";
		myTextBoxes[TEXTBOX_LOGINNAME].x = Menus[MENU_LOGIN].x + 40;
		myTextBoxes[TEXTBOX_LOGINNAME].y = Menus[MENU_LOGIN].y + 70;
		myTextBoxes[TEXTBOX_LOGINNAME].width = 240;
		myTextBoxes[TEXTBOX_LOGINNAME].height = 40;
		myTextBoxes[TEXTBOX_LOGINNAME].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_LOGINNAME].menu = MENU_LOGIN;
		myTextBoxes[TEXTBOX_LOGINNAME].image = generictextboximage;

		myTextBoxes[TEXTBOX_LOGINPASSWORD].label = "Password";
		myTextBoxes[TEXTBOX_LOGINPASSWORD].x = Menus[MENU_LOGIN].x + 40;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].y = Menus[MENU_LOGIN].y + 120;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].width = 240;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].height = 40;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].accept_alpha_characters = true;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].menu = MENU_LOGIN;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].hidechars = true;
		myTextBoxes[TEXTBOX_LOGINPASSWORD].image = generictextboximage;

		SleekMessageBox.background = SleekButtonBG;

	}

	// Drawing routines
	// ---------------------------------------------------------------------------------------

	private long lastFpsTime;
	private long lastLoopTime;
	int fps;
	long delta;// used for animation

	public void gameRendering() {
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
			Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
			lastFpsTime = 0;
			fps = 0;
		}

		// Display.setParent(frm);

		// Window.create(title, 100, 100, width, height, Display.getDepth(), 0,
		// 8, 0, 0);

		// glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClear(GL_COLOR_BUFFER_BIT);

		glEnable(GL_TEXTURE_2D);// begin drawing textured stuff
		glDisable(GL_DEPTH_TEST);

		// glMatrixMode(GL11.GL_PROJECTION);
		// GL11.glOrtho(-width/2, width/2, -height/2, height/2, 1, -10);//lower
		// number is closer to the player
		glMatrixMode(GL_MODELVIEW);

		glLoadIdentity();

		// GL11.glBegin(GL11.GL_QUADS);

		// GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, -1, 1);

		// animate water
		if ((animationframe % 32) == 0) {
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					if ((map[myshareddata.TERRAIN_LAYER_LOWER][y][x] == 7 && map[myshareddata.TERRAIN_LAYER_HIGHER][y][x] == 0)
							|| map[myshareddata.TERRAIN_LAYER_HIGHER][y][x] == 7) {
						if (Math.random() > 0.8) {
							TerrainVariantMap[x][y] = (int) (Math.random() * 4);
							if (Math.random() > 0.2) {
								TerrainVariantMap[x][y] = 0;
							}
						}// else{TerrainVariantMap[x][y]=0;}
					}
				}
			}

		}

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		// there will be a variation map that get created to decide where
		// variants go!

		MasterTerrainSheet.bind();
		MasterTerrainSheet.begin();

		for (int y = 0; y < SCREEN_Y_TILES; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {

					int layer = myshareddata.TERRAIN_LAYER_LOWER;
					int type = map[layer][mapY][mapX];

					if (type > 0) {

						/*
						 * if(type >= LAKEWATER_1 && type <= LAKEWATER_16 &&
						 * (animationframe%8)==0){
						 * map[TERRAIN_LAYER][mapY][mapX]=LAKEWATER_1 + (int)
						 * (16*Math.random()); }
						 */

						int tex_x = 0;
						int tex_y = 0;

						int tileset = (type - 1);
						int subtile = GetTerrainSubtile(mapX, mapY, layer);// this
																			// will
																			// be
																			// tricky
																			// to
																			// get!
																			// function
																			// goes
																			// here!

						DrawTerrainTile(tileset, subtile, mapX, mapY, x, y, layer);

					}

				}
			}
		}

		for (int y = 0; y < SCREEN_Y_TILES; y++) {// /draw higher terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
					int layer = myshareddata.TERRAIN_LAYER_HIGHER;
					int type = map[layer][mapY][mapX];

					if (type > 0) {

						int tileset = (type - 1);
						int subtile = GetTerrainSubtile(mapX, mapY, layer);// this
																			// will
																			// be
																			// tricky
																			// to
																			// get!
																			// function
																			// goes
																			// here!

						DrawTerrainTile(tileset, subtile, mapX, mapY, x, y, layer);

					}

				}
			}
		}

		MasterTerrainSheet.end();

		glDisable(GL_TEXTURE_2D);// end textured stuff

		GL11.glBegin(GL11.GL_QUADS);// start drawing squares

		// draw particles

		for (int i = 0; i < NUMBER_OF_PARTICLE_EMITTERS; i++) {
			if (myEmitters[i] != null) {
				if (myEmitters[i].IsActive) {

					int dist = pythagorean(cam.x, cam.y, myEmitters[i].mapX, myEmitters[i].mapY);
					// if(dist<(SCREEN_Y_TILES+SCREEN_X_TILES)/2){//if emitter
					// is on the screen
					if (coordinatesOnScreen(myEmitters[i].mapX, myEmitters[i].mapY)) {

						int count = myEmitters[i].count;

						int p_size = 4;

						int proj_screenX = (myEmitters[i].mapX - cam.x + (SCREEN_X_TILES / 2)) * TILE_SIZE + 20;
						int proj_screenY = (myEmitters[i].mapY - cam.y + (SCREEN_Y_TILES / 2)) * TILE_SIZE + 40;

						// set the color of the quad (R,G,B,A)
						// float alpha = 1.0f - (float)
						// lit[myEmitters[i].mapY][myEmitters[i].mapX];

						int particle_dist = 50 - (count / 10);

						float alpha = ((float) count / 500f);
						GL11.glColor4f(1.0f, 0.0f, 0.0f, alpha);

						// draw quads
						for (int j = 0; j < myEmitters[i].num_particles; j++) {

							double angle = myEmitters[i].particle_angle[j];
							double part_x = Math.cos(angle) * particle_dist;
							double part_y = Math.sin(angle) * particle_dist;

							part_y -= (count / 10);// gravity fall

							GL11.glVertex2d(proj_screenX + part_x, proj_screenY + part_y);
							GL11.glVertex2d(proj_screenX + part_x + p_size, proj_screenY + part_y);
							GL11.glVertex2d(proj_screenX + part_x + p_size, proj_screenY + part_y + p_size);
							GL11.glVertex2d(proj_screenX + part_x, proj_screenY + part_y + p_size);

						}

					}
				}
			}
		}

		// Shading system

		for (int y = 0; y < SCREEN_Y_TILES * 4; y++) {
			for (int x = 0; x < SCREEN_X_TILES * 4; x++) {
				// int mapX = x + cam.x * 4 - SCREEN_X_TILES * 2;
				// int mapY = y + cam.y * 4 - SCREEN_Y_TILES * 2;
				int mapX = x + (cam.x - SCREEN_X_TILES / 2) * 4 - 2;
				int mapY = y + (cam.y - SCREEN_Y_TILES / 2) * 4;
				// System.out.print(mapX+":"+mapY+" ");
				if (mapX > -1 && mapX < SharedData.MAP_SIZE * 4 && mapY > -1 && mapY < SharedData.MAP_SIZE * 4) {

					// drawShading(g, x, y, lit[mapY][mapX]);
					// glColor3f(0.0f, 0.0f, 0.0f); // coloring rect
					// glRectf(-0.75f, 0.75f, 0.75f, -0.75f); // draw rect

					int shixel_size = TILE_SIZE / 4;

					// set the color of the quad (R,G,B,A)
					float alpha = 1.0f - (float) lit[mapY][mapX];
					GL11.glColor4f(0.0f, 0.0f, 0.0f, alpha);

					// draw quad

					GL11.glVertex2f(x * shixel_size, y * shixel_size);
					GL11.glVertex2f((x * shixel_size) + shixel_size, y * shixel_size);
					GL11.glVertex2f((x * shixel_size) + shixel_size, (y * shixel_size) + shixel_size);
					GL11.glVertex2f(x * shixel_size, (y * shixel_size) + shixel_size);

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
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
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
							int frame = (animationframe / (animationframe / speed)) % num_frames;

							Object_Spritesheet[type].image.bind();
							Object_Spritesheet[type].image.begin();

							Object_Spritesheet[type].image.draw_subimage(frame * Object_Spritesheet[type].frame_size, 0, x * TILE_SIZE
									+ SharedData.imageOffset_x[type], y * TILE_SIZE + SharedData.imageOffset_y[type]
									- (Object_Spritesheet[type].frame_size) + TILE_SIZE, Object_Spritesheet[type].frame_size,
									Object_Spritesheet[type].frame_size);

							Object_Spritesheet[type].image.end();

						} else if (myTypeDB[type].image != null) {

							myTypeDB[type].image.draw(x * TILE_SIZE + SharedData.imageOffset_x[type], y * TILE_SIZE + SharedData.imageOffset_y[type]
									- myTypeDB[type].image.getHeight() + TILE_SIZE);

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
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
					int layer = myshareddata.SMALLOBJECTS_LAYER;
					int type = map[layer][mapY][mapX];

					if (type > 0 && !myTypeDB[type].DoNotRender) {
						if (myTypeDB[type].image == null && Object_Spritesheet[type] == null) {
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

							SmallObject50Sheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE + SharedData.imageOffset_x[type], y * TILE_SIZE
									+ SharedData.imageOffset_y[type] - (50 /* height */) + TILE_SIZE, 50, 50);

						}
					}
				}

			}
		}

		SmallObject50Sheet.end();

		GL11.glColor3f(1, 1, 1);// reset color

		// Draw highlights

		if (targetting_currentability != 0) {

			// int CasterUnitID = 0;
			// int TargetUnitID =
			// UnitMap[cursor.currentmousetile_x][cursor.currentmousetile_y];
			/*
			 * if (myPNum == 1) { CasterUnitID = targetting_currentcaster; }
			 * else { CasterUnitID = myPNum; }
			 */
			// int CasterUnitID = targetting_currentcaster;

			GL11.glColor3f(1, 1, 1);// reset color

			if (myAbilityDB[targetting_currentability].constrain_to_cardinal_direction) {// directional
																							// targetting

				double angle = 0;
				int direction = -1;

				int start_x = cam.tileselect_x;
				int start_y = cam.tileselect_y;
				int end_x = cursor.currentmousetile_x;
				int end_y = cursor.currentmousetile_y;

				int override_shift_along_facing = myAbilityDB[targetting_currentability].cardinal_direction_distance_override;
				angle = Math.toDegrees(Math.atan2(start_x - end_x, start_y - end_y));
				if (angle < 0) {
					angle += 360;
				}

				if (angle <= 45 || angle > 315) {
					direction = 0;
					end_x = start_x;
					if (override_shift_along_facing != 0) {
						end_y = start_y - override_shift_along_facing;
					}
				}
				if (angle > 225 && angle <= 315) {
					direction = 1;
					end_y = start_y;
					if (override_shift_along_facing != 0) {
						end_x = start_x + override_shift_along_facing;
					}
				}
				if (angle > 135 && angle <= 225) {
					direction = 2;
					end_x = start_x;
					if (override_shift_along_facing != 0) {
						end_y = start_y + override_shift_along_facing;
					}
				}
				if (angle > 45 && angle <= 135) {
					direction = 3;
					end_y = start_y;
					if (override_shift_along_facing != 0) {
						end_x = start_x - override_shift_along_facing;
					}
				}

				boolean Range_and_Target_Valid = UnitAbletoCastAbility(targetting_currentability, start_x, start_y, end_x, end_y);

				if (Range_and_Target_Valid) {
					GL11.glColor3f(1, 1, 1);
				} else {
					GL11.glColor3f(1, 0, 0);
				}

				int screenX = cam.tileselect_x - cam.x + SCREEN_X_TILES / 2;
				int screenY = cam.tileselect_y - cam.y + SCREEN_Y_TILES / 2;

				if (cursor.currentmousetile_x != cam.tileselect_x || cursor.currentmousetile_y != cam.tileselect_y) {
					if (direction == 0) {
						white_arrow[direction].draw(screenX * TILE_SIZE, (screenY - 1) * TILE_SIZE);
					}
					if (direction == 1) {
						white_arrow[direction].draw((screenX + 1) * TILE_SIZE, (screenY) * TILE_SIZE);
					}
					if (direction == 2) {
						white_arrow[direction].draw(screenX * TILE_SIZE, (screenY + 1) * TILE_SIZE);
					}
					if (direction == 3) {
						white_arrow[direction].draw((screenX - 1) * TILE_SIZE, (screenY) * TILE_SIZE);
					}
				}

				GL11.glColor3f(1, 1, 1);

			} else {// point targetting

				int screenX = cursor.currentmousetile_x - cam.x + SCREEN_X_TILES / 2;
				int screenY = cursor.currentmousetile_y - cam.y + SCREEN_Y_TILES / 2;

				int start_x = cam.tileselect_x;
				int start_y = cam.tileselect_y;
				int end_x = cursor.currentmousetile_x;
				int end_y = cursor.currentmousetile_y;

				boolean Range_and_Target_Valid = UnitAbletoCastAbility(targetting_currentability, start_x, start_y, end_x, end_y);

				if (Range_and_Target_Valid) {
					GL11.glColor3f(0, 1, 0);
				} else {
					GL11.glColor3f(1, 0, 0);
				}
				cursor.whitesquare.draw((screenX * TILE_SIZE), (screenY * TILE_SIZE));

			}

		}// end abilitybeing targetted check

		// Draw Player Layer

		GL11.glColor3f(1, 1, 1);

		for (int y = 0; y < SCREEN_Y_TILES; y++) {// this wraps around all
													// player and largeobject
													// stuff for better drawing!
			// GL11.glColor3f(1.0f, 1.0f, 1.0f);

			for (int x = 0; x < SCREEN_X_TILES; x++) {// PLAYER_LAYER
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {

					int type = map[myshareddata.PLAYER_LAYER][mapY][mapX];

					double brightness = lit[mapY * 4][mapX * 4];
					GL11.glColor3d(brightness, brightness, brightness);

					if (type >= myshareddata.HERO_2 && type <= myshareddata.HERO_8) {

						Object_Spritesheet[SharedData.HEROSPAWN].image.bind();
						Object_Spritesheet[SharedData.HEROSPAWN].image.begin();

						if (Players[(type - myshareddata.HERO_2 + 2)].IsConnected) {
							int frame = 1 + (animationframe % 16) / 8;
							Object_Spritesheet[SharedData.HEROSPAWN].image.draw_subimage(frame * Object_Spritesheet[SharedData.HEROSPAWN].frame_size,
									0, x * TILE_SIZE + SharedData.imageOffset_x[SharedData.HEROSPAWN], y * TILE_SIZE
											+ SharedData.imageOffset_y[SharedData.HEROSPAWN] - (Object_Spritesheet[SharedData.HEROSPAWN].frame_size)
											+ TILE_SIZE, Object_Spritesheet[SharedData.HEROSPAWN].frame_size,
									Object_Spritesheet[SharedData.HEROSPAWN].frame_size);

						} else {
							Object_Spritesheet[SharedData.HEROSPAWN].image.draw_subimage(0, 0, x * TILE_SIZE
									+ SharedData.imageOffset_x[SharedData.HEROSPAWN], y * TILE_SIZE + SharedData.imageOffset_y[SharedData.HEROSPAWN]
									- (Object_Spritesheet[SharedData.HEROSPAWN].frame_size) + TILE_SIZE,
									Object_Spritesheet[SharedData.HEROSPAWN].frame_size, Object_Spritesheet[SharedData.HEROSPAWN].frame_size);

						}

						Object_Spritesheet[SharedData.HEROSPAWN].image.end();

					}

				}
			}
		}

		for (int y = 0; y < SCREEN_Y_TILES; y++) {// this wraps around all
													// player and largeobject
													// stuff for better drawing!
			// GL11.glColor3f(1.0f, 1.0f, 1.0f);

			for (int x = 0; x < SCREEN_X_TILES; x++) {// PLAYER_LAYER
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {

					int UnitID = UnitMap[mapX][mapY];
					int model_ID = Units[UnitID].stat[SharedData.MODEL_ID];

					int frame = 0;
					int slideoffset_x = 0;
					int slideoffset_y = 0;
					int facing = 0;
					int current_animation = 0;

					int unit_model_height = 40;

					if (UnitID > 0) {
						slideoffset_x = (int) Units[UnitID].slidepixeloffset_x / 100;
						slideoffset_y = (int) Units[UnitID].slidepixeloffset_y / 100;
						facing = Units[UnitID].stat[SharedData.FACING];

						current_animation = Units[UnitID].current_animation;

						// draw circle
						if (mapX == cam.tileselect_x && mapY == cam.tileselect_y) {

							GL11.glColor3f(1.0f, 1.0f, 1.0f);

							if (Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
								GL11.glColor3f(0.2f, 1.0f, 0.2f);
							} else if (Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1) {
								GL11.glColor3f(1.0f, 0.2f, 0.2f);
							}
							if (Units[UnitID].IsAlive()) {
								cursor.heroselector_back.draw(x * TILE_SIZE + slideoffset_x, y * TILE_SIZE + slideoffset_y - 3);
								cursor.heroselector_front.draw(x * TILE_SIZE + slideoffset_x, y * TILE_SIZE + slideoffset_y - 3);
							}
							GL11.glColor3f(1.0f, 1.0f, 1.0f);
						}

						double brightness = lit[mapY * 4][mapX * 4];
						GL11.glColor3d(brightness, brightness, brightness);

						if (HeroCanSeeUnit(mapX * 4 + (slideoffset_x / 10) + 1, mapY * 4 + (slideoffset_y / 10) + 1)
								|| Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum || myPNum == 1) {

							unit_model_height = 64;

							if (Units[UnitID].stat[SharedData.DEAD] == 0 || Units[UnitID].current_animation_frame != 0) {

								defaultUnitShadow.draw(x * 40 + slideoffset_x, y * 40 + slideoffset_y - 25 + TILE_SIZE);

								DrawUnitModel(UnitID, x * 40 + slideoffset_x - 12, y * 40 + slideoffset_y - (unit_model_height) - 5 + TILE_SIZE);

							}

						}// /end lit check

						GL11.glColor3f(1, 1, 1);
						if (DEBUG_MODE) {
							if (UnitID >= 10) {
								Verdana_14.drawString(x * 40 + slideoffset_x, y * 40 + slideoffset_y, "" + UnitID);
							}
						}

						GL11.glColor3f(1, 1, 1);

						if (Units[UnitID].stat[SharedData.HEALTH] != get_unit_maxhealth(UnitID))
							// draw health bar
							DrawUnitHealthBar(UnitID, x * TILE_SIZE + slideoffset_x, y * TILE_SIZE + slideoffset_y + TILE_SIZE - unit_model_height
									- 5);

						/*
						 * if (Hero_changing_facing && queued_hero_facing > -1
						 * && UnitID == myPNum) { DrawFacingGuide(myPNum, x *
						 * TILE_SIZE + slideoffset_x, y * TILE_SIZE +
						 * slideoffset_y, queued_hero_facing); }
						 */

						GL11.glColor3f(1, 1, 1);
						if (BattlingUnits[BattlePhase_ActiveUnit] == UnitID && BattlePhaseEngaged) {

							battling_unit_indicator.draw(x * TILE_SIZE + slideoffset_x + 4,
									y * TILE_SIZE + slideoffset_y - 50 - Math.abs((animationframe % 32) - 16));
						}

					}// end unitid check

				}// end PLAYERLAYER check

			}// end x wrap for player

			// Draw Large Objects
			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			// for (int y = 0; y < SCREEN_Y_TILES; y++) {
			for (int x = -1; x < SCREEN_X_TILES + 1; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
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

							Object_Spritesheet[type].image.draw_subimage(frame * Object_Spritesheet[type].frame_size, 0, x * TILE_SIZE
									+ SharedData.imageOffset_x[type], y * TILE_SIZE + SharedData.imageOffset_y[type]
									- (Object_Spritesheet[type].frame_size) + TILE_SIZE, Object_Spritesheet[type].frame_size,
									Object_Spritesheet[type].frame_size);

							Object_Spritesheet[type].image.end();

						} else if (myTypeDB[type].image != null) {

							myTypeDB[type].image.draw(x * TILE_SIZE + SharedData.imageOffset_x[type], y * TILE_SIZE + SharedData.imageOffset_y[type]
									- myTypeDB[type].image.getHeight() + TILE_SIZE);

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
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
					int layer = SharedData.LARGEOBJECTS_LAYER;
					int type = map[layer][mapY][mapX];

					if (type > 0 && type < 500 && !myTypeDB[type].DoNotRender) {
						if (myTypeDB[type].image == null && Object_Spritesheet[type] == null) {
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

							LargeObject50Sheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE + SharedData.imageOffset_x[type], y * TILE_SIZE
									+ SharedData.imageOffset_y[type] - (50) + TILE_SIZE, 50, 50);

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
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
					int layer = myshareddata.LARGEOBJECTS_LAYER;
					int type = map[layer][mapY][mapX];

					if (type >= 500 && type < 600 && !myTypeDB[type].DoNotRender) {
						if (myTypeDB[type].image == null && Object_Spritesheet[type] == null) {
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

							LargeObject100Sheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE + SharedData.imageOffset_x[type], y * TILE_SIZE
									+ SharedData.imageOffset_y[type] - (100) + TILE_SIZE, 100, 100);

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
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {
					int layer = myshareddata.LARGEOBJECTS_LAYER;
					int type = map[layer][mapY][mapX];

					if (type >= 600 && type < 700 && !myTypeDB[type].DoNotRender) {
						if (myTypeDB[type].image == null && Object_Spritesheet[type] == null) {

							int index = type - 600;

							int tex_x = (index % 5) * 200;
							int tex_y = (index / 5) * 200;

							LargeObject200Sheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE + myshareddata.imageOffset_x[type], y * TILE_SIZE
									+ myshareddata.imageOffset_y[type] - (200) + TILE_SIZE, 200, 200);

						}
					}
				}

			}

			LargeObject200Sheet.end();

		}

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		RoofSheet.bind();
		RoofSheet.begin();

		for (int y = -5; y < SCREEN_Y_TILES + 3; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {

					if (define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX]) {
						float alpha = 1;
						if (myPNum == 1) {
							alpha = (pythagorean(cam.x, cam.y, mapX, mapY)) / 15f;
						} else {
							int RoofCamUnit = myPNum;
							int unit_selected = UnitMap[cam.tileselect_x][cam.tileselect_y];
							if (unit_selected > 0) {
								if (Units[unit_selected].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
									RoofCamUnit = unit_selected;
								}
							}
							alpha = (float) Math.pow((pythagorean(Units[RoofCamUnit].x, Units[RoofCamUnit].y, mapX, mapY)) / 15f, 2);
						}
						GL11.glColor4d(1, 1, 1, alpha);

						DrawRoofTile(mapX, mapY, x, y);

					}

				}
			}
		}

		RoofSheet.end();

		SeamlessRock.bind();
		SeamlessRock.begin();

		// Obscure dungeon tiles with repeating(should auto repeat thru opengl)
		// seamless rock (animate at some point!)
		for (int y = 0; y < SCREEN_Y_TILES; y++) {// /draw lower terrain
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < myshareddata.MAP_SIZE && mapY > -1 && mapY < myshareddata.MAP_SIZE) {

					if (define_regions[SharedData.REGION_DUNGEON - 800][mapY][mapX]) {
						float alpha = 1;
						if (myPNum == 1) {
							alpha = (float) Math.pow((pythagorean(cam.x, cam.y, mapX, mapY)) / 10f, 2);
						} else {
							int RoofCamUnit = myPNum;
							int unit_selected = UnitMap[cam.tileselect_x][cam.tileselect_y];
							if (unit_selected > 0) {
								if (Units[unit_selected].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
									RoofCamUnit = unit_selected;
								}
							}
							alpha = (float) Math.pow((pythagorean(Units[RoofCamUnit].x, Units[RoofCamUnit].y, mapX, mapY)) / 8f, 2);
						}

						GL11.glColor4d(0.3f, 0.3f, 0.3f, alpha);

						int anim = seamlessrockframe;
						SeamlessRock.draw_subimage((mapX * TILE_SIZE - anim) % SeamlessRock.getWidth(),
								(mapY * TILE_SIZE + anim) % SeamlessRock.getHeight(), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

					}

				}
			}
		}
		SeamlessRock.end();

		GL11.glColor3f(1, 1, 1);

		// draw text on loot objects
		for (int y = 0; y < SCREEN_Y_TILES; y++) {
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
					if (ItemContainers[mapX][mapY] != null) {
						if (ItemContainers[mapX][mapY].number_of_slots_with_items() > 0) {

							Verdana_12.drawString(x * TILE_SIZE + 10, y * TILE_SIZE, "(" + ItemContainers[mapX][mapY].number_of_slots_with_items()
									+ ")");

						}
					}
				}
			}
		}

		/*
		 * // notes layer if (notesVisible) {
		 * 
		 * for (int y = 0; y < SCREEN_Y_TILES; y++) { for (int x = 0; x <
		 * SCREEN_X_TILES; x++) { int mapX = x + cam.x - SCREEN_X_TILES / 2; int
		 * mapY = y + cam.y - SCREEN_Y_TILES / 2; if (mapX > -1 && mapX <
		 * SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {
		 * 
		 * int type = map[myshareddata.NOTES_LAYER][mapY][mapX];
		 * 
		 * if (myTypeDB[type].image != null) { myTypeDB[type].image.draw(x *
		 * TILE_SIZE, y TILE_SIZE); }
		 * 
		 * } } }
		 * 
		 * }
		 */

		/*
		 * //warprift lines for DM if(myPNum==1){
		 * 
		 * for(int i=0;i<num_of_warprifts;i++){ int x= warprift[i][0] *
		 * TILE_SIZE - cam.x * TILE_SIZE + (SCREEN_X_TILES * TILE_SIZE / 2); int
		 * y= warprift[i][1] * TILE_SIZE - cam.y * TILE_SIZE + (SCREEN_Y_TILES *
		 * TILE_SIZE / 2); int x2= warprift[i][2] * TILE_SIZE - cam.x *
		 * TILE_SIZE + (SCREEN_X_TILES * TILE_SIZE / 2); int y2= warprift[i][3]
		 * * TILE_SIZE - cam.y * TILE_SIZE + (SCREEN_Y_TILES * TILE_SIZE / 2);
		 * GL11.glColor4f(1f, 1f, 1f,0.6f); Draw_Line(x+20,y+20,x2+20,y2+20); }
		 * }
		 */

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		// draw regionalconditions overlay GFX... dont need this
		/*
		 * for (int y = 0; y < SCREEN_Y_TILES; y++) { for (int x = 0; x <
		 * SCREEN_X_TILES; x++) { int mapX = x + cam.x - SCREEN_X_TILES / 2; int
		 * mapY = y + cam.y - SCREEN_Y_TILES / 2; if (mapX > 0 && mapX <
		 * SharedData.MAP_SIZE && mapY > 0 && mapY < SharedData.MAP_SIZE) {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * for(int i=0;i<num_of_regionalconditions;i++){
		 * if(Regional_Conditions[i].isActive){ if (mapX >
		 * Regional_Conditions[i].x && mapX < Regional_Conditions[i].x +
		 * Regional_Conditions[i].width && mapY > Regional_Conditions[i].y &&
		 * mapY < Regional_Conditions[i].y + Regional_Conditions[i].height ) {
		 * 
		 * int frame = animationframe%6;
		 * 
		 * if (Regional_Conditions[i].GFX_ID > 0) {
		 * Overlay_GFX_library[Regional_Conditions[i].GFX_ID][frame].draw(x *
		 * TILE_SIZE , y* TILE_SIZE);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * } }
		 */

		// draw projectiles
		for (int i = 0; i <= Number_of_Projectiles; i++) {
			if (Flying_Projectiles[i] != null) {
				if (Flying_Projectiles[i].IsActive && Flying_Projectiles[i].creationdelay == 0) {

					int offset_x = (int) Flying_Projectiles[i].currentoffset_x / 100;
					int offset_y = (int) Flying_Projectiles[i].currentoffset_y / 100;

					int proj_screenX = Flying_Projectiles[i].end_mapX - cam.x + (SCREEN_X_TILES / 2);
					int proj_screenY = Flying_Projectiles[i].end_mapY - cam.y + (SCREEN_Y_TILES / 2);

					ProjectileModels this_proj_model = myProjectileModels[myEffectDB[Flying_Projectiles[i].projeffect_ID].projectiletype];

					int R = myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_R;
					int G = myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_G;
					int B = myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_B;

					int width = 64;
					int height = 64;

					int tex_x = 0;
					int tex_y = 0;

					int facing = 0;
					if (!this_proj_model.concentric) {
						facing = Flying_Projectiles[i].facing;
					}

					if (this_proj_model.animationframes == 1 && this_proj_model.concentric) {// static,
																								// nonrotating
																								// projectiles
						tex_y += 64 * this_proj_model.sheetindex;
						tex_x += 64 * this_proj_model.rowindex;

					}

					else if (this_proj_model.animationframes > 1 && this_proj_model.concentric) {// animated,
																									// nonrotating
																									// projectiles
						tex_y += 64 * this_proj_model.sheetindex;
						tex_x += 64 * Flying_Projectiles[i].animframe;

					}

					else if (this_proj_model.animationframes == 1 && !this_proj_model.concentric) {// static
																									// rotating
																									// projectiles
						tex_y += 64 * this_proj_model.sheetindex;
						tex_x += 64 * facing;

					}

					else if (this_proj_model.animationframes > 1 && !this_proj_model.concentric) {// animated
																									// rotating
																									// projectiles

						tex_y += 64 * facing;
						tex_x += 64 * Flying_Projectiles[i].animframe;

					}

					GL11.glColor3d(R / 100.0, G / 100.0, B / 100.0);

					this_proj_model.sheet.bind();
					this_proj_model.sheet.begin();

					this_proj_model.sheet.draw_subimage(tex_x, tex_y, proj_screenX * TILE_SIZE + offset_x - 12, proj_screenY * TILE_SIZE + offset_y
							- 12, width, height);

					this_proj_model.sheet.end();

					/*
					 * int width = (int)
					 * myProjectileModels[Flying_Projectiles[i]
					 * .type].image[Flying_Projectiles[i].animframe][facing]
					 * .getWidth(); int height = (int)
					 * myProjectileModels[Flying_Projectiles
					 * [i].type].image[Flying_Projectiles[i].animframe][facing]
					 * .getHeight();
					 */
					/*
					 * myProjectileModels[Flying_Projectiles[i].type].image[
					 * Flying_Projectiles[i].animframe][facing]
					 * .draw(proj_screenX * TILE_SIZE + offset_x - (width / 2),
					 * proj_screenY * TILE_SIZE + offset_y - (height / 2));
					 */

				}
			}
		}// /end projectiles

		GL11.glColor3d(1, 1, 1);
		// draw all overlayGFXanimations

		MasterOverlayGFXSheet.bind();
		MasterOverlayGFXSheet.begin();

		for (int i = 0; i <= number_of_OverlayGFX; i++) {
			if (myOverlayGFX[i].enabled) {

				int proj_screenX = myOverlayGFX[i].x - cam.x + (SCREEN_X_TILES / 2);
				int proj_screenY = myOverlayGFX[i].y - cam.y + (SCREEN_Y_TILES / 2);

				int type = myOverlayGFX[i].GFX_ID;
				int frame = myOverlayGFX[i].currentframe;

				if (type > 0) {

					int index = type - 1;

					// broad tuning
					int tex_x = (index % 4) * 512;
					int tex_y = (index / 4) * 512;

					// fine tuning
					tex_x += (frame % 4) * 128;
					tex_y += (frame / 4) * 128;

					GL11.glColor3d(myOverlayGFX[i].r / 100, myOverlayGFX[i].g / 100, myOverlayGFX[i].b / 100);

					int size = (128 * myOverlayGFX[i].scale) / 100;
					MasterOverlayGFXSheet.draw_subimage_stretch(tex_x, tex_y, proj_screenX * TILE_SIZE - (size / 2) + 24, proj_screenY * TILE_SIZE
							- (size / 2) + 6, 128, 128, size, size);

				}
			}
		}
		MasterOverlayGFXSheet.end();

		GL11.glColor3d(1, 1, 1);

		// draw mini options menu for DM. this could be animated!!
		if (myPNum == 1) {
			int[] tempint = getDMOptionObject();

			int cursortype = tempint[0];
			int rows = tempint[1];

			if (rows > 0) {

				int proj_screenX = cam.tileselect_x - cam.x + (SCREEN_X_TILES / 2);
				int proj_screenY = cam.tileselect_y - cam.y + (SCREEN_Y_TILES / 2);

				GL11.glColor4d(0.1, 0.1, 0.1, 0.5);

				Fill_Rect(proj_screenX * TILE_SIZE, proj_screenY * TILE_SIZE - (rows * 22) - 20, 120, rows * 22);

				for (int i = 0; i < rows; i++) {
					if (cursor.x > proj_screenX * TILE_SIZE && cursor.x < proj_screenX * TILE_SIZE + 120
							&& cursor.y > proj_screenY * TILE_SIZE - ((i + 1) * 22) - 20 && cursor.y <= proj_screenY * TILE_SIZE - (i * 22) - 20) {

						Fill_Rect(proj_screenX * TILE_SIZE, proj_screenY * TILE_SIZE - ((i + 1) * 22) - 20, 120, 22);
					}
				}

				GL11.glColor3d(1, 1, 1);
				for (int i = rows - 1; i >= 0; i--) {
					Verdana_16.drawString(proj_screenX * TILE_SIZE + 5, proj_screenY * TILE_SIZE - (i * 22) - 40, myTypeDB[cursortype].options[i]);

				}

			}

		}

		GL11.glColor3f(1, 1, 1);

		// draw floating text
		for (int i = 0; i < NUMBER_OF_FLOATING_TEXT; i++) {

			if (myFloatingText[i] != null) {
				if (myFloatingText[i].duration_left > 0) {

					int proj_x = (myFloatingText[i].x - cam.x + SCREEN_X_TILES / 2) * TILE_SIZE + myFloatingText[i].offset_x;
					int proj_y = (myFloatingText[i].y - cam.y + SCREEN_Y_TILES / 2) * TILE_SIZE - 15 - myFloatingText[i].offset_y;

					float r = 0;
					float g = 0;
					float b = 0;
					float alpha = (float) Math.pow((double) myFloatingText[i].duration_left / 10000.0, 0.5);

					try {// /colors for ints
						if (Integer.parseInt(myFloatingText[i].text) > 0) {
							r = 0;
							g = 1;
							b = 0;
						}

						if (Integer.parseInt(myFloatingText[i].text) < 0) {
							r = 1;
							g = 0;
							b = 0;
						}
					} catch (Exception e) {// colors for strings
						if (myFloatingText[i].text.toLowerCase().startsWith("block")) {
							r = 1;
							g = 1;
							b = 0;
						}

					}

					// sinc function!
					double denom = Math.pow(30 - (myFloatingText[i].duration_left / 380), 3);

					int float_test_dynamicheight = (int) ((5 + (myFloatingText[i].duration_left % 100) * 15) / denom);

					// alpha works now!
					GL11.glColor4f(0, 0, 0, alpha / 2);
					Arcade_20.drawString(proj_x + 2, proj_y - (float_test_dynamicheight) + 2, "" + myFloatingText[i].text);
					GL11.glColor4f(r, g, b, alpha);
					Arcade_20.drawString(proj_x, proj_y - (float_test_dynamicheight), "" + myFloatingText[i].text);

				}
			}

		}

		GL11.glColor3f(1, 1, 1);

		// old spot for shading

		// draw light rays
		for (int y = 0; y < SCREEN_Y_TILES; y++) {
			for (int x = 0; x < SCREEN_X_TILES; x++) {
				int mapX = x + cam.x - SCREEN_X_TILES / 2;
				int mapY = y + cam.y - SCREEN_Y_TILES / 2;
				if (mapX > -1 && mapX < SharedData.MAP_SIZE && mapY > -1 && mapY < SharedData.MAP_SIZE) {

					if (map[SharedData.LARGEOBJECTS_LAYER][mapY][mapX] == SharedData.LIGHTRAYS) {
						int a = ((animationframe % 32)) / 8;
						lightrays[a].draw(x * TILE_SIZE - 30, y * TILE_SIZE - 230);
						int b = a + 1;
						if (b == 4) {
							b = 0;
						}
						lightrays[b].draw(x * TILE_SIZE - 30, y * TILE_SIZE - 230);
						int c = ((animationframe % 32) - 3) / 8;
						lightrays[c].draw(x * TILE_SIZE - 30, y * TILE_SIZE - 230);

					}
				}
			}
		}

		// old spot for target arrows

		GL11.glColor3f(1, 1, 1);// reset color

		if (myPNum == 1) {

			// draw cursor for DM

			if (cam.tileselect_x > -1 && cam.tileselect_x < SharedData.MAP_SIZE && cam.tileselect_y > -1 && cam.tileselect_y < SharedData.MAP_SIZE) {
				if (UnitMap[cam.tileselect_x][cam.tileselect_y] == 0) {
					cursor.whiteselector.draw(TILE_SIZE * (cam.tileselect_x - cam.x + (SCREEN_X_TILES / 2)), TILE_SIZE
							* (cam.tileselect_y - cam.y + (SCREEN_Y_TILES / 2)));

				}
			}
			/*
			 * 
			 * 
			 * String selectedtileobjectstring =
			 * myTypeDB[map[PLAYER_LAYER][cam.tileselect_y
			 * ][cam.tileselect_x]].namestring; / if (selectedtileobjectstring
			 * == null) { selectedtileobjectstring = "Nothing"; }
			 * 
			 * 
			 * if (cursor.tileselected == true) { Verdana_16.drawString(400, 30,
			 * selectedtileobjectstring + " selected"); }
			 * 
			 * // draw DM Abilities for (int i = 0; i < 10; i++) { if
			 * (active_DMabilities[i] != 0) {
			 * ability_icons[myAbilityDB[active_DMabilities[i]].icon_ID]
			 * .draw(secondaryabilitybar.x + 50 * i, secondaryabilitybar.y); } }
			 * 
			 * // draw conditions of selected Units for DMs int
			 * num_activeconditions = 0; int UnitID =
			 * UnitMap[cam.tileselect_x][cam.tileselect_y]; if (UnitID > 0) {
			 * 
			 * for (int i = 0; i < 100; i++) { if
			 * (Units[UnitID].conditionstimeleft[i] != 0) {
			 * myConditionDB[i].icon.draw(conditionbar.x + 30
			 * (num_activeconditions % 10), conditionbar.y + (30 *
			 * (num_activeconditions / 10)));
			 * 
			 * if (Units[UnitID].conditionstimeleft[i] > 0) { Verdana_20
			 * .drawString( conditionbar.x + 30 (num_activeconditions % 10),
			 * conditionbar.y + 15 + (30 * (num_activeconditions / 10)), "" +
			 * Units[UnitID].conditionstimeleft[i]); } num_activeconditions++; }
			 * }
			 * 
			 * }
			 */

		}

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {
			if (Units[i] != null) {

				if (Units[i].chat_bubble_timeleft > 0) {

					int proj_screenX = (Units[i].x - cam.x + (SCREEN_X_TILES / 2)) * TILE_SIZE + (int) (Units[i].slidepixeloffset_x / 100);
					int proj_screenY = (Units[i].y - cam.y + (SCREEN_Y_TILES / 2)) * TILE_SIZE + (int) (Units[i].slidepixeloffset_y / 100);

					DrawUnitChatBubble(i, proj_screenX + 20, proj_screenY - 25);

				}

			}

		}

		// draw Unit GUI stuff
		if (UnitMap[cam.tileselect_x][cam.tileselect_y] > 0) {
			int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

			unitinfo_bg.draw(0, 0);
			GetUnitPortrait(UnitID).draw_FitToSize(GUIProfileImage.x, GUIProfileImage.y, 100);

			float health_pct = (float) Units[UnitID].stat[SharedData.HEALTH] / (float) get_unit_maxhealth(UnitID);

			GL11.glColor3f(1 - (health_pct * 0.7f), health_pct * 0.8f, 0.1f);

			healthbar.bind();
			healthbar.begin();
			healthbar.draw_subimage(0, 0, GUIHealthBar.x, GUIHealthBar.y, healthbar.getWidth() * health_pct, healthbar.getHeight());
			healthbar.end();

			GL11.glColor3f(.7f, .7f, .7f);

			float energy_pct = (float) Units[UnitID].stat[SharedData.ENERGY] / (float) get_unit_maxenergy(UnitID);

			energybar.bind();
			energybar.begin();
			energybar.draw_subimage(0, 0, GUIEnergyBar.x, GUIEnergyBar.y, energybar.getWidth() * energy_pct, energybar.getHeight());
			energybar.end();

			GL11.glColor3f(1, 1, 1);

			// healthbar.draw(GUIHealthBar.x,GUIHealthBar.y);
			// energybar.draw(GUIEnergyBar.x,GUIEnergyBar.y);

			/*
			 * staticon_health.draw(130, SCREEN_HEIGHT - 120);
			 * staticon_energy.draw(130, SCREEN_HEIGHT - 120 + 25 * 1);
			 * staticon_power.draw(130, SCREEN_HEIGHT - 120 + 25 * 2);
			 * staticon_armor.draw(130, SCREEN_HEIGHT - 120 + 25 * 3);
			 * 
			 * Verdana_14.drawString(158, SCREEN_HEIGHT - 121, "" +
			 * Units[UnitID].stat[myshareddata.HEALTH]);
			 * Verdana_14.drawString(158, SCREEN_HEIGHT - 121 + 25 * 1, "" +
			 * Units[UnitID].stat[SharedData.ENERGY]);
			 * Verdana_14.drawString(158, SCREEN_HEIGHT - 121 + 25 * 2, "" +
			 * Units[UnitID].stat[myshareddata.POWER]);
			 * Verdana_14.drawString(158, SCREEN_HEIGHT - 121 + 25 * 3, "" +
			 * Units[UnitID].stat[myshareddata.ARMOR]); // draw level, xp bar,
			 * etc...
			 */

			// /draw DM god buttons
			if (myPNum == 1) {

				for (int i = 0; i < GodButtons.length; i++) {

					if (GodButtons[i].BeingHovered(cursor)) {
						GL11.glColor3f(0.8f, 0.8f, 0.8f);
					} else {
						GL11.glColor3f(1, 1, 1);
					}
					GodButtons[i].background.draw(GodButtons[i].x, GodButtons[i].y);

				}

				GL11.glColor3f(1, 1, 1);
			}

		}

		/*
		 * double MainIconColors[][] = { //{1,1,1}, {0.7,0.4,0.2}, {1,.2,.2},
		 * {0.3,0.5,1}, {0.6,1,0.3}, {.8,.8,0}, };
		 */

		int UnitID_CurrentlySelected = UnitMap[cam.tileselect_x][cam.tileselect_y];
		if (UnitID_CurrentlySelected > 0) {
			if (Units[UnitID_CurrentlySelected].stat[SharedData.PLAYER_OWNERSHIP] == myPNum
					&& (!BattlePhaseEngaged || BattlingUnits[BattlePhase_ActiveUnit] == UnitID_CurrentlySelected)) {

				// for (int i = 0; i < MainBarIcons.length; i++) {

				GL11.glColor3f(1, 1, 1);
				if (!UnitCanWalk(UnitID_CurrentlySelected)) {
					GL11.glColor3f(.8f, .2f, .2f);
				}
				DrawAbilityIconFromSheet(MainBarIcons[0], primaryabilitybar.x + 50 * 0, primaryabilitybar.y);

				GL11.glColor3f(1, 1, 1);

				int ActiveWepSkills[] = GetActiveWeaponSkills();
				boolean none_available = true;
				for (int j = 0; j < 10; j++) {
					int abil_id = ActiveWepSkills[j];
					if (abil_id > 0) {
						if (Units[UnitID_CurrentlySelected].stat[SharedData.ENERGY] > myAbilityDB[abil_id].energy_cost_base) {
							none_available = false;
						}
					}
				}
				if (none_available) {
					GL11.glColor3f(.2f, .2f, .2f);
				}

				DrawAbilityIconFromSheet(MainBarIcons[1], primaryabilitybar.x + 50 * 1, primaryabilitybar.y);

				GL11.glColor3f(1, 1, 1);

				none_available = true;
				for (int j = 0; j < 10; j++) {
					int abil_id = Units[UnitID_CurrentlySelected].active_spells[j];
					if (abil_id > 0) {
						if (Units[UnitID_CurrentlySelected].stat[SharedData.ENERGY] > myAbilityDB[abil_id].energy_cost_base) {
							none_available = false;
						}
					}
				}
				if (none_available) {
					GL11.glColor3f(.2f, .2f, .2f);
				}

				DrawAbilityIconFromSheet(MainBarIcons[2], primaryabilitybar.x + 50 * 2, primaryabilitybar.y);

				GL11.glColor3f(1, 1, 1);

				int ActiveConsumables[] = GetConsumables();
				none_available = true;
				for (int j = 0; j < 10; j++) {
					if (ActiveConsumables[j] >= 0) {

						none_available = false;

					}
				}
				if (none_available) {
					GL11.glColor3f(.2f, .2f, .2f);
				}

				DrawAbilityIconFromSheet(MainBarIcons[3], primaryabilitybar.x + 50 * 3, primaryabilitybar.y);

				GL11.glColor3f(1, 1, 1);
				if (BattlePhaseEngaged) {
					DrawAbilityIconFromSheet(MainBarIcons[4], primaryabilitybar.x + 50 * 4, primaryabilitybar.y);
				}

				if (secondaryabilitybar.mode == 1) {

					GL11.glColor4f(0, 0, 0, .2f);
					Fill_Rect(secondaryabilitybar.x + 0, secondaryabilitybar.y, 50 * 10, 50);

					// int ActiveWepSkills = GetActiveWeaponSkills();

					// /draw Unit Abilities
					for (int i = 0; i < 10; i++) {

						boolean draw_cooldown_text = false;

						if (ActiveWepSkills[i] != 0) {
							int ability = ActiveWepSkills[i];

							int myenergycost = myAbilityDB[ability].getEffectiveEnergyCost(Units[UnitID_CurrentlySelected]);

							GL11.glColor3f(1, 1, 1);

							// shade gray if on cooldown
							if (Units[UnitID_CurrentlySelected].active_skill_cooldowns[ability] != 0) {
								GL11.glColor4f(0, 0, 0, 0.8f);
								// Fill_Rect(secondaryabilitybar.x + 50 * i,
								// secondaryabilitybar.y,
								// 50, 50);
								draw_cooldown_text = true;

							} else if (myenergycost > Units[UnitID_CurrentlySelected].stat[SharedData.ENERGY]) {
								// /shade blue when there is insufficient energy
								GL11.glColor4f(0.2f, 0.5f, 0.7f, 0.8f);

							} else if ((myAbilityDB[ability].counts_as_a_movement && !UnitCanWalk(UnitID_CurrentlySelected))
									|| (myAbilityDB[ability].counts_as_an_attack && !UnitCanAttack(UnitID_CurrentlySelected))) {
								// /shade red when the unit is out of walk moves
								// or
								// attack moves
								GL11.glColor4f(0.6f, 0.2f, 0.2f, 0.8f);

							} else if (myAbilityDB[ability].weapon_required != myItemDB[Units[UnitID_CurrentlySelected].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].weapontype
									&& myAbilityDB[ability].weapon_required != myItemDB[Units[UnitID_CurrentlySelected].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].weapontype) {

								GL11.glColor4f(0.6f, 0.35f, 0.1f, 0.8f);

							}

							DrawAbilityIconFromSheet(myAbilityDB[ability].icon_ID, secondaryabilitybar.x + 50 * i, secondaryabilitybar.y);

							GL11.glColor3f(0, 0, 0);

							Fill_Rect(secondaryabilitybar.x + 50 * i + 37, secondaryabilitybar.y + 37, 10, 10);

							GL11.glColor3f(1, 1, 1);
							Verdana_12.drawString(secondaryabilitybar.x + 50 * i + 40, secondaryabilitybar.y + 35, "" + (i + 1));

							if (draw_cooldown_text) {

								Verdana_20.drawString(secondaryabilitybar.x + 22 + 50 * i, secondaryabilitybar.y + 18, ""
										+ Units[UnitID_CurrentlySelected].active_skill_cooldowns[ability]);
								Verdana_20.drawString(secondaryabilitybar.x + 20 + 50 * i, secondaryabilitybar.y + 16, ""
										+ Units[UnitID_CurrentlySelected].active_skill_cooldowns[ability]);

							}

						}
					}
				}

				GL11.glColor3f(1, 1, 1);

				if (secondaryabilitybar.mode == 2) {

					GL11.glColor4f(0, 0, 0, .2f);
					Fill_Rect(secondaryabilitybar.x + 0, secondaryabilitybar.y, 50 * 10, 50);

					// /draw Unit Abilities
					for (int i = 0; i < 10; i++) {

						boolean draw_cooldown_text = false;

						if (Units[UnitID_CurrentlySelected].active_spells[i] != 0) {
							int ability = Units[UnitID_CurrentlySelected].active_spells[i];

							int myenergycost = myAbilityDB[ability].getEffectiveEnergyCost(Units[UnitID_CurrentlySelected]);

							GL11.glColor3f(1, 1, 1);

							// shade gray if on cooldown
							if (Units[UnitID_CurrentlySelected].active_spell_cooldowns[ability] != 0) {
								GL11.glColor4f(0, 0, 0, 0.8f);
								// Fill_Rect(secondaryabilitybar.x + 50 * i,
								// secondaryabilitybar.y,
								// 50, 50);
								draw_cooldown_text = true;

							} else if (myenergycost > Units[UnitID_CurrentlySelected].stat[SharedData.ENERGY]) {
								// /shade blue when there is insufficient energy
								GL11.glColor4f(0.2f, 0.5f, 0.7f, 0.8f);

							} else if ((myAbilityDB[ability].counts_as_a_movement && !UnitCanWalk(UnitID_CurrentlySelected))
									|| (myAbilityDB[ability].counts_as_an_attack && !UnitCanAttack(UnitID_CurrentlySelected))) {
								// /shade red when the unit is out of walk moves
								// or
								// attack moves
								GL11.glColor4f(0.6f, 0.2f, 0.2f, 0.8f);

							} else if (myAbilityDB[ability].weapon_required != myItemDB[Units[UnitID_CurrentlySelected].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].weapontype
									&& myAbilityDB[ability].weapon_required != myItemDB[Units[UnitID_CurrentlySelected].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].weapontype) {

								GL11.glColor4f(0.6f, 0.35f, 0.1f, 0.8f);

							}

							DrawAbilityIconFromSheet(myAbilityDB[ability].icon_ID, secondaryabilitybar.x + 50 * i, secondaryabilitybar.y);

							GL11.glColor3f(0, 0, 0);

							Fill_Rect(secondaryabilitybar.x + 50 * i + 37, secondaryabilitybar.y + 37, 10, 10);

							GL11.glColor3f(1, 1, 1);
							Verdana_12.drawString(secondaryabilitybar.x + 50 * i + 40, secondaryabilitybar.y + 35, "" + (i + 1));

							if (draw_cooldown_text) {

								Verdana_20.drawString(secondaryabilitybar.x + 22 + 50 * i, secondaryabilitybar.y + 18, ""
										+ Units[UnitID_CurrentlySelected].active_spell_cooldowns[ability]);
								Verdana_20.drawString(secondaryabilitybar.x + 20 + 50 * i, secondaryabilitybar.y + 16, ""
										+ Units[UnitID_CurrentlySelected].active_spell_cooldowns[ability]);

							}

						}
					}
				}

				GL11.glColor3f(1, 1, 1);

				if (secondaryabilitybar.mode == 3) {

					GL11.glColor4f(0, 0, 0, .2f);
					Fill_Rect(secondaryabilitybar.x + 0, secondaryabilitybar.y, 50 * 10, 50);

					// int ActiveConsumables[] = GetConsumables();

					// /draw Unit Abilities
					for (int i = 0; i < 10; i++) {

						boolean draw_cooldown_text = false;

						if (ActiveConsumables[i] >= 0) {
							int item = Units[UnitID_CurrentlySelected].itemInSlot[ActiveConsumables[i]];
							if (item > 0) {

								GL11.glColor3f(1, 1, 1);

								// if(ConsumeItemCooldown
								// >0){GL11.glColor3f(0.5f, 0.5f, 0.5f);}

								/*
								 * // shade gray if on cooldown if
								 * (Units[UnitID_CurrentlySelected
								 * ].active_skill_cooldowns[ability] != 0) {
								 * GL11.glColor4f(0, 0, 0, 0.8f); //
								 * Fill_Rect(secondaryabilitybar.x + 50 * i,
								 * secondaryabilitybar.y, // 50, 50);
								 * draw_cooldown_text = true;
								 * 
								 * } else if
								 * ((myAbilityDB[ability].counts_as_a_movement
								 * && !UnitCanWalk(UnitID_CurrentlySelected)) ||
								 * (myAbilityDB[ability].counts_as_an_attack &&
								 * !UnitCanAttack(UnitID_CurrentlySelected))) {
								 * // /shade red when the unit is out of walk
								 * moves or // attack moves GL11.glColor4f(0.6f,
								 * 0.2f, 0.2f, 0.8f);
								 * 
								 * }else if(myAbilityDB[ability].weapon_required
								 * != myItemDB[Units[UnitID_CurrentlySelected].
								 * itemInSlot
								 * [SharedData.ITEMSLOT_MAINHAND]].weapontype &&
								 * myAbilityDB[ability].weapon_required !=
								 * myItemDB
								 * [Units[UnitID_CurrentlySelected].itemInSlot
								 * [SharedData.ITEMSLOT_OFFHAND]].weapontype){
								 * 
								 * GL11.glColor4f(0.6f, 0.35f, 0.1f, 0.8f);
								 * 
								 * }
								 */

								DrawItemIconFromSheet(myItemDB[item].Icon_ID, secondaryabilitybar.x + 50 * i, secondaryabilitybar.y);

								GL11.glColor3f(0, 0, 0);

								Fill_Rect(secondaryabilitybar.x + 50 * i + 37, secondaryabilitybar.y + 37, 10, 10);

								GL11.glColor3f(1, 1, 1);
								Verdana_12.drawString(secondaryabilitybar.x + 50 * i + 40, secondaryabilitybar.y + 35, "" + (i + 1));

								/*
								 * if(draw_cooldown_text){
								 * 
								 * Verdana_20.drawString(secondaryabilitybar.x +
								 * 22 + 50 * i, secondaryabilitybar.y + 18, "" +
								 * Units
								 * [UnitID_CurrentlySelected].active_skill_cooldowns
								 * [ability]);
								 * Verdana_20.drawString(secondaryabilitybar.x +
								 * 20 + 50 * i, secondaryabilitybar.y + 16, "" +
								 * Units
								 * [UnitID_CurrentlySelected].active_skill_cooldowns
								 * [ability]);
								 * 
								 * 
								 * }
								 */

							}
						}
					}
				}

			}// end check for owning selected unit

			GL11.glColor3f(1, 1, 1);

			int num_activeconditions = 0;// draw conditionicons in a 10x10
											// matrix for everyone!
			for (int i = 0; i < 100; i++) {
				if (Units[UnitID_CurrentlySelected].conditionstimeleft[i] != 0) {
					int cond_type = Units[UnitID_CurrentlySelected].activeconditions[i];

					DrawConditionIconFromSheet(myConditionDB[cond_type].Icon_ID, conditionbar.x + 30 * (num_activeconditions % 10), conditionbar.y
							+ (30 * (num_activeconditions / 10)));

					if (Units[UnitID_CurrentlySelected].conditionstimeleft[i] > 0) {
						GL11.glColor3f(0, 0, 0);
						int width = Verdana_12.getStringPixelLength("" + Units[UnitID_CurrentlySelected].conditionstimeleft[i]);
						Fill_Rect(conditionbar.x + 30 - width + 30 * (num_activeconditions % 10), conditionbar.y + 20
								+ (30 * (num_activeconditions / 10)), width + 1, 12);
						GL11.glColor3f(1, 1, 1);
						Verdana_12.drawString(conditionbar.x + 30 - width + 30 * (num_activeconditions % 10), conditionbar.y + 20
								+ (30 * (num_activeconditions / 10)), "" + Units[UnitID_CurrentlySelected].conditionstimeleft[i]);
					}
					num_activeconditions++;
				}

			}

		}// end check for selecting any unit

		// Draw battle order
		if (BattlePhaseEngaged
				&& (BattlingUnits[BattlePhase_ActiveUnit] != UnitID_CurrentlySelected || Units[UnitID_CurrentlySelected].stat[SharedData.PLAYER_OWNERSHIP] != myPNum)) {

			int running_x = 0;
			for (int i = 0; i < number_of_found_battling_units; i++) {

				int size = 50;
				if (i == BattlingUnits[BattlePhase_ActiveUnit]) {
					GL11.glColor3f(1, 1, 1);
					size = 60;
				} else {
					GL11.glColor3f(0.5f, 0.5f, 0.5f);
					size = 50;
				}

				GetUnitPortrait(BattlingUnits[i]).draw_FitToSize(SCREEN_WIDTH / 2 - (number_of_found_battling_units * 50) / 2 - 5 + running_x,
						SCREEN_HEIGHT - size, size);
				running_x += size;
			}

		}

		// draw tileconditions
		if (cam.TileSelection_Within_Bounds()) {
			int num_activeconditions = 0;
			for (int i = 0; i < 100; i++) {
				if (tileconditionstimeleft[cam.tileselect_x][cam.tileselect_y][i] > 0) {
					int cond_type = tileactiveconditions[cam.tileselect_x][cam.tileselect_y][i];

					DrawConditionIconFromSheet(myConditionDB[cond_type].Icon_ID, tileconditionbar.x + 30 * (num_activeconditions % 10),
							tileconditionbar.y + (30 * (num_activeconditions / 10)));

					if (Units[UnitID_CurrentlySelected].conditionstimeleft[i] > 0) {
						GL11.glColor3f(0, 0, 0);
						int width = Verdana_12.getStringPixelLength("" + tileconditionstimeleft[cam.tileselect_x][cam.tileselect_y][i]);
						Fill_Rect(tileconditionbar.x + 30 - width + 30 * (num_activeconditions % 10), tileconditionbar.y + 20
								+ (30 * (num_activeconditions / 10)), width + 1, 12);
						GL11.glColor3f(1, 1, 1);
						Verdana_12.drawString(tileconditionbar.x + 30 - width + 30 * (num_activeconditions % 10), tileconditionbar.y + 20
								+ (30 * (num_activeconditions / 10)), "" + tileconditionstimeleft[cam.tileselect_x][cam.tileselect_y][i]);
					}
					num_activeconditions++;
				}

			}
		}

		// draw minimenubuttons for heroes
		if (myPNum != 1) {
			for (int i = 0; i < 3; i++) {
				minimenubuttons[i].draw(minimenubar.x + 20 * i, minimenubar.y);
			}
		} else {
			if (UnitMap[cam.tileselect_x][cam.tileselect_y] == 0) {
				minimenubuttons[0].draw(minimenubar.x + 20 * 0, minimenubar.y);
				minimenubuttons[3].draw(minimenubar.x + 20 * 1, minimenubar.y);
			}
		}

		GL11.glColor3f(1, 1, 1);
		/*
		 * if(displayedLeftTooltip!=null){ Verdana_14.drawString(30,
		 * SCREEN_HEIGHT - 300 , displayedLeftTooltip, 200); }else{
		 * 
		 * // /client stat tooltip (for hovered-over things) for (int n = 0; n <
		 * 5; n++) {
		 * 
		 * if (displayedLeftTooltip_lines[n] != null) {
		 * 
		 * Verdana_14.drawString(30, SCREEN_HEIGHT - 300 + (18 * n),
		 * displayedLeftTooltip_lines[n]); } } }
		 */

		GL11.glColor3f(1, 1, 1);// reset colors

		// /draw chat stuff

		int max_num_lines = 10;
		int chat_width = SCREEN_WIDTH / 3;
		if (chat_width > 400) {
			chat_width = 400;
		}
		if (chat_display_timer > 0 || game_typing_focus == TYPEFOCUS_CHAT) {
			GL11.glColor4f(0, 0, 0, 0.4f);
			Fill_Rect(25, SCREEN_HEIGHT - 60 - 20 * max_num_lines, chat_width, 20 * max_num_lines + 15);
			Fill_Rect(25, SCREEN_HEIGHT - 60 - 20 * max_num_lines, chat_width, 15);
			GL11.glColor3f(1, 1, 1);
			int p_length = Verdana_14.getStringPixelLength("Player Chat");
			Verdana_14.drawString(chat_width / 2 - p_length / 2 + 20, SCREEN_HEIGHT - 60 - 20 * max_num_lines, "Player Chat");
		}

		if (game_typing_focus == TYPEFOCUS_CHAT) {
			int x = 30;
			int y = SCREEN_HEIGHT - 40;

			String text = chat_typing_text;
			int BeginningChar = Verdana_14.GetStringPixelIndexFromEnd(chat_typing_text, chat_width);
			if (text.length() > BeginningChar) {
				text = chat_typing_text.substring(chat_typing_text.length() - BeginningChar, chat_typing_text.length());

			}
			GL11.glColor4f(0, 0, 0, 0.5f);
			Fill_Rect(x - 5, y - 5, chat_width + 20, 32);

			GL11.glColor3f(1, 1, 1);
			if ((animationframe % 32) / 16 == 0) {
				text += "|";
			}
			Verdana_14.drawString(x, y, text);
			Outline_Rect(x - 5, y - 5, chat_width + 20, 32);
		}

		if (chat_display_timer > 0 || game_typing_focus == TYPEFOCUS_CHAT) {
			int lines_drawn = 0;
			for (int i = 0; i < 10; i++) {
				int j = i + chatscrolloffset;
				if (chathistorylines[j] != null && lines_drawn < max_num_lines) {
					int this_many_lines = Verdana_14.GetStringNumLines(chathistorylines[j], chat_width);

					Verdana_14.drawString(30, SCREEN_HEIGHT - 60 - 20 * (lines_drawn + (this_many_lines - 1)), chathistorylines[j], chat_width, 400,
							20);

					lines_drawn += this_many_lines;

					// lines_drawn++;
				}
			}
		}

		// draw story log text
		if (SCREEN_WIDTH > 500 && storylog_display_timer > 0) {
			int x = SCREEN_WIDTH - 220;
			int y = SCREEN_HEIGHT - 30;
			int max_lines = (SCREEN_HEIGHT - 350) / 20;

			int drawn_lines = 0;
			for (int i = 0; i < 20; i++) {
				if (storyloghistorylines[i] != null) {
					drawn_lines += Verdana_14.GetStringNumLines(storyloghistorylines[i], 180);
				}
				if (drawn_lines > max_lines) {
					drawn_lines = max_lines;
				}
			}

			GL11.glColor4f(0, 0, 0, 0.4f);
			Fill_Rect(x - 11, SCREEN_HEIGHT - 30 - (drawn_lines * 20 + 30), 230, drawn_lines * 20 + 30);

			GL11.glColor3f(0.6f, 0.6f, 0.6f);
			Verdana_12.drawString(x + 70, SCREEN_HEIGHT - 30 - (drawn_lines * 20 + 30) - 2, "Story Log");

			int lines_drawn = 0;
			for (int i = 0; i < 20; i++) {
				if (storyloghistorylines[i] != null && lines_drawn < max_lines) {
					int this_many_lines = Verdana_14.GetStringNumLines(storyloghistorylines[i], 180);

					GL11.glColor3f(1, 1, 1);
					if (storyloghistorylines[i].startsWith("---")) {
						GL11.glColor4f(0.2f, 1, 0.2f, 0.6f);
					}
					Verdana_14.drawString(x, y - 30 - 20 * (lines_drawn + (this_many_lines - 1)), storyloghistorylines[i], 180, 400, 20);

					lines_drawn += this_many_lines;

					// /lines_drawn++;
				}
			}
		}

		GL11.glColor3f(1, 1, 1);// reset colors

		if (Menus[MENU_MINIMAP].is_open) {

			GL11.glColor3f(1, 1, 1);
			if (minimap_static == null) {
				generateMiniMap();
			}

			minimapbg.draw(Menus[MENU_MINIMAP].x - 29, Menus[MENU_MINIMAP].y - 30);
			minimap_static.draw(Menus[MENU_MINIMAP].x, Menus[MENU_MINIMAP].y);

			if (myPNum != 1) {// green dot for hero
				GL11.glColor3f(0, 1.0f, 0);
				Fill_Rect(Menus[MENU_MINIMAP].x + Units[myPNum].x * 2, Menus[MENU_MINIMAP].y + Units[myPNum].y * 2, 2, 2);
			}

			GL11.glColor3f(1, 1, 1);

			GL11.glDisable(GL_TEXTURE_2D);
			GL11.glBegin(GL_LINES); // begin lines
			// minimap box

			int mm_center_x = Menus[7].x + (cam.x * 2);
			int mm_center_y = Menus[7].y + (cam.y * 2);
			GL11.glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y - (SCREEN_Y_TILES));
			glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y - (SCREEN_Y_TILES));
			GL11.glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y - (SCREEN_Y_TILES));
			glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y + (SCREEN_Y_TILES));
			GL11.glVertex2f(mm_center_x + (SCREEN_X_TILES), mm_center_y + (SCREEN_Y_TILES));
			glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y + (SCREEN_Y_TILES));
			GL11.glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y + (SCREEN_Y_TILES));
			glVertex2f(mm_center_x - (SCREEN_X_TILES), mm_center_y - (SCREEN_Y_TILES));

			GL11.glEnd();

			glEnable(GL_TEXTURE_2D);// begin more textured stuff

		} // end check for minimap being enabled

		GL11.glColor3f(1, 1, 1);
		// draw dice
		D20_image.draw(SCREEN_WIDTH - 90, 232);
		int dice_text_x_offset = 70;
		if (current_D20_roll < 10) {
			dice_text_x_offset = 65;
		}
		GL11.glColor3f(0f, 0f, 0f);
		Verdana_16.drawString(SCREEN_WIDTH - dice_text_x_offset, 250, "" + current_D20_roll);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		boolean readytoendturn = false;
		// draw EndTurn button
		if (myPNum == 1) {
			if (num_clientsendedturn == num_clientsconnected) {
				readytoendturn = true;
			}
		}

		if (myPNum != 1) {
			if (!UnitCanWalk(myPNum)) {
				readytoendturn = true;
			}

			if (Unit_At_Tile(Units[myPNum].x - 1, Units[myPNum].y) || Unit_At_Tile(Units[myPNum].x, Units[myPNum].y - 1)
					|| Unit_At_Tile(Units[myPNum].x + 1, Units[myPNum].y) || Unit_At_Tile(Units[myPNum].x, Units[myPNum].y + 1)) {
				readytoendturn = false;
			}

			/*
			 * if (Units[myPNum].stat[myshareddata.ACTION_POINTS_COUNT] == 0) {
			 * readytoendturn = true; }
			 */
		}

		if (myPNum == 1 && num_clientsconnected > 0) {
			if (BattlePhaseEngaged) {
				phasebutton_battle.draw(PhaseButton.x, PhaseButton.y);
			} else {
				phasebutton_passive.draw(PhaseButton.x, PhaseButton.y);
			}
		}

		/*
		 * if (readytoendturn == false) {
		 * endturnbutton_transparent.draw(EndTurnButton.x, EndTurnButton.y);
		 * 
		 * } else { endturnbutton.draw(EndTurnButton.x, EndTurnButton.y); int
		 * frame = (animationframe) / 8; int tex_x = (frame % 2) * 30; int tex_y
		 * = (frame / 2) * 30; endturnarrows.draw_subimage(tex_x, tex_y,
		 * EndTurnButton.x, EndTurnButton.y, 30, 30); }
		 */

		GL11.glColor3f(1, 1, 1);

		// Menu Handling comes after and on top

		int UnitID_inventoryMenu = UnitMap[cam.tileselect_x][cam.tileselect_y];

		if (Menus[MENU_INV].is_open == true) { // invmenu.. really just displays
												// images depending on
			// variables!

			if (myPNum != 1) {
				if (Menus[MENU_ITEMVENDOR].is_open == true) {
					// tabs for crafting and for buying

					if (Menus[MENU_ITEMVENDOR].mode < 100) {
						GL11.glColor3f(0.5f, 0.5f, 0.5f);
						vend_tab_crafting.draw(Menus[MENU_ITEMVENDOR].x + 50, Menus[MENU_ITEMVENDOR].y);
						GL11.glColor3f(1, 1, 1);
						vend_tab_buying.draw(Menus[MENU_ITEMVENDOR].x, Menus[MENU_ITEMVENDOR].y);

					} else {

						GL11.glColor3f(0.5f, 0.5f, 0.5f);
						vend_tab_buying.draw(Menus[MENU_ITEMVENDOR].x, Menus[MENU_ITEMVENDOR].y);
						GL11.glColor3f(1, 1, 1);
						vend_tab_crafting.draw(Menus[MENU_ITEMVENDOR].x + 50, Menus[MENU_ITEMVENDOR].y);
					}

					GL11.glColor3f(0, 0, 0);
					Fill_Rect(Menus[MENU_ITEMVENDOR].x, Menus[MENU_ITEMVENDOR].y + 24, Menus[MENU_ITEMVENDOR].width + 40,
							Menus[MENU_ITEMVENDOR].height);
					GL11.glColor3f(1, 1, 1);
					itemvendor_whitearrow.draw(Menus[MENU_ITEMVENDOR].x + 180, Menus[MENU_ITEMVENDOR].y + 30);
					int number_of_slots = 0;
					if (Menus[MENU_ITEMVENDOR].mode < 100) {

						number_of_slots = 5;

						// fix assettable

						if (Menus[MENU_ITEMVENDOR].mode == 0) {
							GL11.glColor3f(0.2f, 0.2f, 0.2f);
							for (int i = 0; i < number_of_slots; i++) {
								Outline_Rect(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i,
										Menus[MENU_ITEMVENDOR].width - 20, 20);

							}
							GL11.glColor3f(1, 1, 1);
							Verdana_16.drawString(Menus[MENU_ITEMVENDOR].x + Menus[MENU_ITEMVENDOR].width / 2 - 35, Menus[MENU_ITEMVENDOR].y + 40,
									"Buy Items");

							for (int i = 0; i < 5; i++) {
								int j = i + (Menus[MENU_ITEMVENDOR].scrolloffset) + 3;
								if (SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS].length > j) {
									Verdana_16.drawString(Menus[MENU_ITEMVENDOR].x + 10 + 4, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i,
											SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS][j]);
								}
							}
							// make an offset, mousewheel changes that offset!
							// draw arrows based on what the offset is!!!!
						} else {

							int itemgroup = Menus[MENU_ITEMVENDOR].mode + 2;

							number_of_slots = number_of_purchasable_items[itemgroup];
							GL11.glColor3f(0.2f, 0.2f, 0.2f);
							for (int i = 0; i < number_of_slots; i++) {
								Outline_Rect(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i,
										Menus[MENU_ITEMVENDOR].width - 20, 20);

							}
							GL11.glColor3f(1, 1, 1);

							Verdana_16.drawString(Menus[MENU_ITEMVENDOR].x + Menus[MENU_ITEMVENDOR].width / 2 - 50, Menus[MENU_ITEMVENDOR].y + 40,
									SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS][itemgroup]);

							red_back_arrow.draw(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 40);

							for (int i = 0; i < number_of_slots; i++) {
								int j = i + (Menus[MENU_ITEMVENDOR].scrolloffset);

								if (purchasable_items[itemgroup] != null) {
									if (myItemDB[purchasable_items[itemgroup][j]].namestring != null) {

										Verdana_16.drawString(Menus[MENU_ITEMVENDOR].x + 20 + 4, Menus[MENU_ITEMVENDOR].y + 72 + 25 * i,
												myItemDB[purchasable_items[itemgroup][j]].namestring);

									}
								}

							}

						}

					} else {

						GL11.glColor3f(1, 1, 1);
						Verdana_16.drawString(Menus[MENU_ITEMVENDOR].x + Menus[MENU_ITEMVENDOR].width / 2 - 40, Menus[MENU_ITEMVENDOR].y + 40,
								" Crafting (WIP)");

					}

				} else {
					if (BattlePhaseEngaged) {
						GL11.glColor3f(0.2f, 0.2f, 0.2f);
					}
					charm_buying.draw(Menus[MENU_INV].x - 30, Menus[MENU_INV].y + 20);

					charm_crafting.draw(Menus[MENU_INV].x - 30, Menus[MENU_INV].y + 50);

				}
			}

			GL11.glColor3f(1, 1, 1);

			if (InvMenu_BonusStats_MustBeRefreshed) {
				Refresh_InvMenu_BonusStats(UnitID_inventoryMenu);
			}

			Menus[MENU_INV].background.draw(Menus[MENU_INV].x, Menus[MENU_INV].y);
			MenuInvSlots.draw(Menus[MENU_INV].x + 15, Menus[MENU_INV].y + 258);

			menu_exit_x.draw(Menus[MENU_INV].x + Menus[MENU_INV].exitbutton_x, Menus[MENU_INV].y + Menus[MENU_INV].exitbutton_y);

			if (UnitID_inventoryMenu <= 0) {
				Menus[MENU_INV].is_open = false;
			}

			ProfileImage_frame.draw(Menus[MENU_INV].x + 35 - 23, Menus[MENU_INV].y + 32 - 13);
			// GetUnitPortrait(UnitID_inventoryMenu).draw_FitToSize(Menus[MENU_INV].x
			// + 35, Menus[MENU_INV].y + 32,100);
			DrawUnitModel(UnitID_inventoryMenu, Menus[MENU_INV].x + 50, Menus[MENU_INV].y + 52);

			// draw level on profileimage
			int pixellength = Arcade_20.getStringPixelLength("" + Units[UnitID_inventoryMenu].stat[myshareddata.LEVEL]);
			GL11.glColor3f(1, 1, 1);
			levelcircle.draw(Menus[MENU_INV].x + 45 - pixellength / 2 - 10, Menus[MENU_INV].y + 35 - 5);
			GL11.glColor3f(0.1f, 0.1f, 0.1f);
			Arcade_20.drawString(Menus[MENU_INV].x + 42 - pixellength / 2, Menus[MENU_INV].y + 30, ""
					+ Units[UnitID_inventoryMenu].stat[myshareddata.LEVEL]);
			GL11.glColor3f(0.9f, 0.9f, 0.9f);

			String unit_name = GetUnitName(UnitID_inventoryMenu);

			// draw align
			int alignment = Units[UnitID_inventoryMenu].stat[SharedData.ALIGNMENT];
			if (alignment > 0) {
				String alignmenttext = SharedData.ComboStrings[SharedData.COMBOTEXT_ALIGNMENT][alignment];
				pixellength = Verdana_16.getStringPixelLength(alignmenttext);
				Verdana_16.drawString(Menus[MENU_INV].x + 250 - pixellength / 2, Menus[MENU_INV].y + 37, alignmenttext);
			}

			// draw descrip
			String descrip = null;
			int maxheight = 200;
			if (UnitID_inventoryMenu < 10) {
				maxheight = 70;
				descrip = Players[UnitID_inventoryMenu].herodescription;
			} else {
				descrip = myNPCDB[Units[UnitID_inventoryMenu].stat[SharedData.UNITDATA_NPCTYPE]].description;
			}
			if (descrip != null) {
				Verdana_12.drawString(Menus[MENU_INV].x + 160, Menus[MENU_INV].y + 60, descrip, 170, maxheight, 16);
			}

			// Draw the Stats as Strings THESE SHOULD BE DRAWN WITH ICONS SO ITS
			// NICER
			GL11.glColor3f(0.9f, 0.9f, 0.9f);

			pixellength = Verdana_16.getStringPixelLength(unit_name);
			Verdana_16.drawString(Menus[MENU_INV].x + 200 - pixellength / 2, Menus[MENU_INV].y + 5, unit_name);

			if (UnitID_inventoryMenu < 10) {
				healthbar.draw(Menus[MENU_INV].x + 37, Menus[MENU_INV].y + 145);
				GL11.glColor3f(0.3f, 0.3f, 0.9f);
				float exp_pct = (float) Units[UnitID_inventoryMenu].stat[SharedData.EXP]
						/ (float) (Units[UnitID_inventoryMenu].stat[SharedData.LEVEL] * 10);

				healthbar.bind();
				healthbar.begin();
				healthbar.draw_subimage(0, 0, Menus[MENU_INV].x + 37, Menus[MENU_INV].y + 145, (int) ((float) healthbar.getWidth() * exp_pct),
						healthbar.getHeight());
				healthbar.end();
				GL11.glColor3f(0.1f, 0.1f, 0.1f);

				int exp_to_level = (Units[UnitID_inventoryMenu].stat[SharedData.LEVEL] * 10) - Units[UnitID_inventoryMenu].stat[SharedData.EXP];
				String expbartext = exp_to_level + " xp to level";
				pixellength = Verdana_12.getStringPixelLength(expbartext);
				Verdana_12.drawString(Menus[MENU_INV].x + 37 + 50 - pixellength / 2, Menus[MENU_INV].y + 144, expbartext);
			}

			GL11.glColor3f(0.9f, 0.9f, 0.9f);

			// draw vitals
			int vitals_x = 30;
			int vitals_y = 170;

			Image2D staticonstodraw[] = {vitalicon_meleepower, vitalicon_rangedpower, vitalicon_spellpower, vitalicon_armor, vitalicon_magres,
					vitalicon_toxres};
			int statstodraw[] = {SharedData.MELEEPOWER, SharedData.RANGEDPOWER, SharedData.SPELLPOWER, SharedData.ARMORRATING,
					SharedData.MAGICRESIST, SharedData.TOXINRESIST};

			for (int i = 0; i < statstodraw.length; i++) {
				GL11.glColor3f(1, 1, 1);
				staticonstodraw[i].draw_FitToSize(Menus[MENU_INV].x + vitals_x + 60 * (i / (statstodraw.length / 2)), Menus[MENU_INV].y + vitals_y
						+ 20 * (i % (statstodraw.length / 2)), 20);

				Verdana_16.drawString(Menus[MENU_INV].x + vitals_x + 25 + 60 * (i / (statstodraw.length / 2)), Menus[MENU_INV].y + vitals_y + 20
						* (i % (statstodraw.length / 2)), "" + Units[UnitID_inventoryMenu].stat[statstodraw[i]]);

				pixellength = Verdana_16.getStringPixelLength("" + Units[UnitID_inventoryMenu].stat[statstodraw[i]]);

				String bonus_text = "";
				int bonus = invmenu_bonusstats[i];// from
													// RefreshInvMenuBonusStats
													// function!
				if (bonus > 0) {
					bonus_text = " +" + bonus;
				}
				GL11.glColor3f(0.2f, .9f, .2f);
				Verdana_16.drawString(Menus[MENU_INV].x + vitals_x + 25 + 60 * (i / (statstodraw.length / 2)) + pixellength, Menus[MENU_INV].y
						+ vitals_y + 20 * (i % (statstodraw.length / 2)), "" + bonus_text);
			}

			// draw misc stats
			int misc_stats_x = 50;
			int misc_stats_y = 240;

			Image2D misciconstodraw[] = {vitalicon_speed, vitalicon_initiative};
			int miscstatstodraw[] = {SharedData.SPEED, SharedData.INITIATIVE};

			for (int i = 0; i < miscstatstodraw.length; i++) {
				GL11.glColor3f(1, 1, 1);
				misciconstodraw[i].draw_FitToSize(Menus[MENU_INV].x + misc_stats_x, Menus[MENU_INV].y + misc_stats_y + 20 * i, 20);

				Verdana_16.drawString(Menus[MENU_INV].x + misc_stats_x + 25, Menus[MENU_INV].y + misc_stats_y + 20 * i, ""
						+ Units[UnitID_inventoryMenu].stat[miscstatstodraw[i]]);

				pixellength = Verdana_16.getStringPixelLength("" + Units[UnitID_inventoryMenu].stat[miscstatstodraw[i]]);

				String bonus_text = "";
				int bonus = invmenu_bonusstats[6 + i];// from
														// RefreshInvMenuBonusStats
														// function!
				if (bonus > 0) {
					bonus_text = " +" + bonus;
				}
				GL11.glColor3f(0.2f, .9f, .2f);
				Verdana_16
						.drawString(Menus[MENU_INV].x + misc_stats_x + 25 + pixellength, Menus[MENU_INV].y + misc_stats_y + 20 * i, "" + bonus_text);
			}

			GL11.glColor3f(1, 0.8f, 0);
			coinsicon.draw_FitToSize(Menus[MENU_INV].x + 50, Menus[MENU_INV].y + 290, 20);
			Verdana_16.drawString(Menus[MENU_INV].x + 50 + 20, Menus[MENU_INV].y + 290 + 3, ""
					+ Units[UnitID_inventoryMenu].stat[SharedData.GOLD_CARRIED]);

			GL11.glColor3f(1, 1, 1);

			int basestats_x = 170;
			int basestats_y = 180;

			// Draw Mid Stuff
			if (UnitID_inventoryMenu < 10) {// draw hero mid stuff

				if (UnitID_inventoryMenu == myPNum && Units[UnitID_inventoryMenu].stat[SharedData.STAT_POINTS_AVAIL] > 0) {

					if (Units[UnitID_inventoryMenu].stat[SharedData.STAT_POINTS_AVAIL] == 1) {
						Verdana_12.drawString(Menus[MENU_INV].x + basestats_x + 40, Menus[MENU_INV].y + basestats_y - 20,
								Units[UnitID_inventoryMenu].stat[SharedData.STAT_POINTS_AVAIL] + " stat point to spend!");
					} else {
						Verdana_12.drawString(Menus[MENU_INV].x + basestats_x + 40, Menus[MENU_INV].y + basestats_y - 20,
								Units[UnitID_inventoryMenu].stat[SharedData.STAT_POINTS_AVAIL] + " stat points to spend!");
					}

					for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {
						float alpha = (float) Math.abs((animationframe % 32) - 16) / (float) 32;
						GL11.glColor4f(.2f, .8f, .2f, alpha);
						Outline_Rect(Menus[MENU_INV].x + basestats_x - 3 + 110 * (i / 4), Menus[MENU_INV].y + basestats_y + 20 * (i % 4), 90, 14);
					}

				}

				for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {
					GL11.glColor3f(1, 1, 1);

					int stat = i + SharedData.ENDURANCE;
					int string_pos = GetStatStringIndex(stat);

					Verdana_12.drawString(Menus[MENU_INV].x + basestats_x + 110 * (i / 4), Menus[MENU_INV].y + basestats_y + 20 * (i % 4),
							SharedData.ComboStrings[SharedData.COMBOTEXT_STATS][string_pos] + ":");

					Verdana_12.drawString(Menus[MENU_INV].x + basestats_x + 70 + 110 * (i / 4), Menus[MENU_INV].y + basestats_y + 20 * (i % 4), ""
							+ Units[UnitID_inventoryMenu].stat[stat]);

					pixellength = Verdana_12.getStringPixelLength("" + Units[UnitID_inventoryMenu].stat[stat]);
					String bonus_text = "";
					int bonus = invmenu_bonusstats[stat];// from
															// RefreshInvMenuBonusStats
															// function!
					if (bonus > 0) {
						bonus_text = " +" + bonus;
					}
					GL11.glColor3f(0.2f, .9f, .2f);
					Verdana_12.drawString(Menus[MENU_INV].x + basestats_x + 70 + 110 * (i / 4) + pixellength, Menus[MENU_INV].y + basestats_y + 20
							* (i % 4), "" + bonus_text);
				}
			}

			GL11.glColor3f(1, 1, 1);

			// Draw Items

			for (int i = 0; i < 24; i++) {
				if (Units[UnitID_inventoryMenu].itemInSlot[i] != 0 && bagSlotOf_itemBeingDragged != i) {

					/*
					 * myItemDB[(Units[myPNum].itemInSlot[i])].image.draw(
					 * Menus[MENU_INV].x + 18 + 40 * (i % 6), Menus[MENU_INV].y
					 * + 318 + 40 * (i / 6));
					 */
					int Icon_ID = myItemDB[(Units[UnitID_inventoryMenu].itemInSlot[i])].Icon_ID;
					int quantity = (Units[UnitID_inventoryMenu].itemQuantity[i]);

					DrawItemIconFromSheet(Icon_ID, Menus[MENU_INV].x + 18 + 40 * (i % 6), Menus[MENU_INV].y + 318 + 40 * (i / 6));
					DrawItemQuantity(quantity, Menus[MENU_INV].x + 18 + 40 * (i % 6), Menus[MENU_INV].y + 318 + 40 * (i / 6));
				}
			}

			for (int i = 50; i < 50 + 9; i++) {

				if (Units[UnitID_inventoryMenu].itemInSlot[i] != 0 && bagSlotOf_itemBeingDragged != i) {

					/*
					 * myItemDB[(Units[myPNum].itemInSlot[i])].image.draw(
					 * invmenu_equipslots_x[i - 50], invmenu_equipslots_y[i -
					 * 50]);
					 */
					int Icon_ID = myItemDB[(Units[UnitID_inventoryMenu].itemInSlot[i])].Icon_ID;
					int quantity = (Units[UnitID_inventoryMenu].itemQuantity[i]);

					DrawItemIconFromSheet(Icon_ID, Menus[MENU_INV].x + invmenu_equipslots_x[i - 50], Menus[MENU_INV].y + invmenu_equipslots_y[i - 50]);

					DrawItemQuantity(quantity, Menus[MENU_INV].x + invmenu_equipslots_x[i - 50], Menus[MENU_INV].y + invmenu_equipslots_y[i - 50]);

				}
			}

			/*
			 * if (inventoryslot_currentlyselected >= 0 &&
			 * inventoryslot_currentlyselected < 50) {
			 * 
			 * int slot = inventoryslot_currentlyselected - 0; int rows =
			 * ItemMenuOptions.length; int minimenu_x = Menus[MENU_INV].x + 18 +
			 * 40 * (slot % 6) + 10; int minimenu_y = Menus[MENU_INV].y + 318 +
			 * 40 * (slot / 6);
			 * 
			 * GL11.glColor4d(0.1, 0.1, 0.1, 0.5); Fill_Rect(minimenu_x,
			 * minimenu_y - (rows * 22), 120, rows * 22);
			 * 
			 * for (int i = 0; i < rows; i++) { if (cursor.x > minimenu_x &&
			 * cursor.x < minimenu_x + 120 && cursor.y > minimenu_y - ((i + 1) *
			 * 22) && cursor.y <= minimenu_y - (i * 22)) {
			 * 
			 * Fill_Rect(minimenu_x, minimenu_y - ((i + 1) * 22), 120, 22); } }
			 * 
			 * GL11.glColor3d(1, 1, 1); for (int i = 0; i < rows; i++) {
			 * Verdana_16.drawString(minimenu_x + 5, minimenu_y - ((i + 1) *
			 * 22), ItemMenuOptions[i]);
			 * 
			 * }
			 * 
			 * }
			 */

			// THIS MUST BE LAST AND ALWAYS ON TOP
			if (bagSlotOf_itemBeingDragged != -1) {

				int Icon_ID = myItemDB[Units[UnitID_inventoryMenu].itemInSlot[bagSlotOf_itemBeingDragged]].Icon_ID;
				DrawItemIconFromSheet(Icon_ID, cursor.x - 20, cursor.y - 20);

			} else {
				if (ItemVendor_itemBeingDragged != -1) {
					int Icon_ID = myItemDB[ItemVendor_itemBeingDragged].Icon_ID;
					DrawItemIconFromSheet(Icon_ID, cursor.x - 20, cursor.y - 20);
				}

			}

		}// end invmenu

		if (Menus[MENU_ABILITIES].is_open) { // add skill menu

			if (abilitymenu_pane < 11) {
				AbilityMenuBG_tab1.draw(Menus[MENU_ABILITIES].x, Menus[MENU_ABILITIES].y);
			}
			if (abilitymenu_pane >= 11) {
				AbilityMenuBG_tab2.draw(Menus[MENU_ABILITIES].x, Menus[MENU_ABILITIES].y);
			}

			// menu_red_x.draw(Menus[MENU_ABILITIES].x + 375,
			// Menus[MENU_ABILITIES].y + 45);

			menu_exit_x.draw(Menus[MENU_ABILITIES].x + Menus[MENU_ABILITIES].exitbutton_x, Menus[MENU_ABILITIES].y
					+ Menus[MENU_ABILITIES].exitbutton_y);

			if (abilitymenu_pane == 0) {

				for (int i = 0; i < 10; i++) {
					GL11.glColor3f(0.4f, 0.4f, 0.4f);

					if (Units[myPNum].stat[SharedData.SPEC_1] == (i + 1) || Units[myPNum].stat[SharedData.SPEC_2] == (i + 1)) {
						GL11.glColor3f(1, 1, 1);
					}

					if (cursor.x > Menus[MENU_ABILITIES].x + 26 + (i % 2) * 175 && cursor.x < Menus[MENU_ABILITIES].x + 26 + 170 + (i % 2) * 175
							&& cursor.y > Menus[MENU_ABILITIES].y + 80 + (i / 2) * 80 && cursor.y < Menus[MENU_ABILITIES].y + 80 + 70 + (i / 2) * 80) {

						GL11.glColor3f(0.7f, 0.7f, 0.7f);
					}

					AbilitySpecButtons[i].draw(Menus[MENU_ABILITIES].x + 26 + (i % 2) * 175, Menus[MENU_ABILITIES].y + 80 + (i / 2) * 80);
				}

				GL11.glColor3f(1, 1, 1);

				Verdana_14.drawString(Menus[MENU_ABILITIES].x + 150, Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 50, "Skill Points: "
						+ Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL]);

			}
			if (abilitymenu_pane >= 1 && abilitymenu_pane <= 10) {
				String[] abilityspecstrings = {"Brawn", "Protection", "Nature", "Darkness", "Alchemy", "Precision", "Explosives", "Mechatronics",
						"Wizardry", "Sorcery"};
				GL11.glColor3f(0.9f, 0.9f, 0.9f);
				// g.setFont(new Font("Arial", Font.PLAIN, 24));
				Verdana_20.drawString((Menus[MENU_ABILITIES].x + 140) - 3 - (((abilityspecstrings[abilitymenu_pane - 1]).length() - 7) * 5),
						Menus[MENU_ABILITIES].y + 80, abilityspecstrings[abilitymenu_pane - 1] + " Skills");

				Verdana_14.drawString(Menus[MENU_ABILITIES].x + 150, Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 90, "Skill Points: "
						+ Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL]);

				GL11.glColor3f(1, 1, 1);

				GL11.glColor3f(0.1f, 0.1f, 0.1f);
				red_back_arrow.draw(Menus[MENU_ABILITIES].x + 20, Menus[MENU_ABILITIES].y + 75);

				if (Units[myPNum].stat[SharedData.SPEC_1] != abilitymenu_pane && Units[myPNum].stat[SharedData.SPEC_2] != abilitymenu_pane
						&& (Units[myPNum].stat[SharedData.SPEC_1] == 0 || Units[myPNum].stat[SharedData.SPEC_2] == 0)) {
					GL11.glColor3f(0.2f, 0.2f, 0.2f);
					simpleGrayButton.draw(Menus[MENU_ABILITIES].x + 120, Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 70);
					GL11.glColor3f(0.9f, 0.9f, 0.9f);
					Verdana_18.drawString(Menus[MENU_ABILITIES].x + 120 + 35, Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 70 + 10,
							"Specialize");
				}

				GL11.glColor3f(1, 1, 1);// reset color

				for (int i = 0; i < 10; i++) {

					if (Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL] > 0) {
						GL11.glColor3f(0.9f, 0.9f, 0.9f);
					} else {
						GL11.glColor3f(0.4f, 0.4f, 0.4f);
					}

					if (Units[myPNum].stat[SharedData.SPEC_1] == abilitymenu_pane || Units[myPNum].stat[SharedData.SPEC_2] == abilitymenu_pane) {
						Outline_Rect(Menus[MENU_ABILITIES].x + 30 + 185 * (i % 2) - 5, Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) - 5, 165, 50);
					}
					// fix add scrollign and make sure I work!

					if (myAbilityDB[AbilitiesInEachSpec[abilitymenu_pane][i]].namestring != null) {

						DrawAbilityIconFromSheet(myAbilityDB[AbilitiesInEachSpec[abilitymenu_pane][i]].icon_ID, Menus[MENU_ABILITIES].x + 30 + 185
								* (i % 2) - 5, Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) - 5);

						if (Units[myPNum].LearnedSpells[AbilitiesInEachSpec[abilitymenu_pane][i]] == true) {
							GL11.glColor3f(0.4f, 1.0f, 0.4f);
						} else {
							GL11.glColor3f(0.1f, 0.1f, 0.1f);
						}
						Verdana_14.drawString(Menus[MENU_ABILITIES].x + 80 + 185 * (i % 2), Menus[MENU_ABILITIES].y + 125 + 70 * (i / 2),
								myAbilityDB[AbilitiesInEachSpec[abilitymenu_pane][i]].namestring, 105, 35, 18);

						GL11.glColor3f(1.0f, 1.0f, 1.0f);
					}
				}

			}

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (abilitymenu_pane >= 11) {// weapon skills
				// need to implement this!!

				// catalog_weaponskills();//this can actually only be ran when a
				// hero changes their weapon loadout in order to be more
				// efficient!

				if (abilitymenu_pane >= 11) {

					Refresh_Unit_Skills(myPNum);

					for (int i = 0; i < 6 * 5; i++) {
						int j = i + Menus[MENU_ABILITIES].scrolloffset * 6;

						if (j < num_of_weaponskills_available) {
							GL11.glColor3f(1, 1, 1);
						} else {
							GL11.glColor3f(0.5f, 0.5f, 0.5f);
						}

						// ability_icons[myAbilityDB[weaponskills_in_order[i]].icon_ID].draw(Menus[MENU_ABILITIES].x
						// + 40 + 50* (i % 4), Menus[MENU_ABILITIES].y + 80 +
						// 50* (i / 4));
						int this_ability = 0;

						this_ability = weaponskillsorder[j];

						if (myAbilityDB[this_ability].icon_ID > 0) {

							DrawAbilityIconFromSheet(myAbilityDB[this_ability].icon_ID, Menus[MENU_ABILITIES].x + 40 + 50 * (i % 6),
									Menus[MENU_ABILITIES].y + 80 + 50 * (i / 6));
						}
					}
				}
				// draw the rest of them dark!

			}

			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			if (abilityicon_beingDragged != -1 /*
												 * && counter_mousehelddown >=
												 * 300
												 */) {

				DrawAbilityIconFromSheet(myAbilityDB[abilityicon_beingDragged].icon_ID, cursor.x - 20, cursor.y - 20);

				GL11.glDisable(GL_TEXTURE_2D);
				GL11.glBegin(GL11.GL_LINES);
				int height = 50;
				int width = 50;
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.4f);
				for (int i = 0; i < 10; i++) {
					int x = secondaryabilitybar.x + 50 * i;
					int y = secondaryabilitybar.y;

					GL11.glVertex2f(x, y);
					GL11.glVertex2f(x + width, y);

					GL11.glVertex2f(x + width, y);
					GL11.glVertex2f(x + width, y + height);

					GL11.glVertex2f(x + width, y + height);
					GL11.glVertex2f(x, y + height);

					GL11.glVertex2f(x, y + height);
					GL11.glVertex2f(x, y);

				}

				GL11.glEnd();
				GL11.glEnable(GL_TEXTURE_2D);

				int ActiveWepSkills[] = GetActiveWeaponSkills();
				for (int i = 0; i < 10; i++) {
					if (abilitymenu_pane < 11) {
						DrawAbilityIconFromSheet(myAbilityDB[Units[myPNum].active_spells[i]].icon_ID, secondaryabilitybar.x + 50 * i,
								secondaryabilitybar.y);
					} else {
						DrawAbilityIconFromSheet(myAbilityDB[ActiveWepSkills[i]].icon_ID, secondaryabilitybar.x + 50 * i, secondaryabilitybar.y);
					}
				}

				/*
				 * ability_icons[myAbilityDB[abilityicon_beingDragged].icon_ID]
				 * .draw(cursor.x - 20, cursor.y - 20);
				 */
			}// THIS MUST BE LAST AND ALWAYS ON TOP

		}

		GL11.glColor3f(1, 1, 1);// reset color

		if (Menus[MENU_ESC].is_open == true) {// escmenu

			DrawESCMenu();

		}

		GL11.glColor3f(1, 1, 1);// reset color

		if (Menus[MENU_EDITSTATS].is_open == true) {// fix the editskillsmenu

			int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

			GL11.glColor3f(1, 1, 1);
			/*
			 * Fill_Rect(Menus[MENU_EDITSTATS].x, Menus[MENU_EDITSTATS].y,
			 * Menus[MENU_EDITSTATS].width, Menus[MENU_EDITSTATS].height);
			 */
			Menus[MENU_EDITSTATS].background.draw(Menus[MENU_EDITSTATS].x, Menus[MENU_EDITSTATS].y);

			menu_exit_x.draw(Menus[MENU_EDITSTATS].x + Menus[MENU_EDITSTATS].exitbutton_x, Menus[MENU_EDITSTATS].y
					+ Menus[MENU_EDITSTATS].exitbutton_y);

			GL11.glColor3f(0.9f, 0.9f, 0.9f);

			if (UnitID > 0) {

				// g.setFont(new Font("Arial", Font.PLAIN, 16));

				String unit_name = "";
				if (UnitID < 10) {
					unit_name = Players[UnitID].name;
				} else {
					unit_name = myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].namestring;
				}

				Verdana_16.drawString(Menus[MENU_EDITSTATS].x + 135, Menus[MENU_EDITSTATS].y + 10, "Edit " + unit_name + " Stats");

				// int editstatsmenu_selectedstat;

				for (int i = 0; i < EditStatsMenu_Stats.length; i++) { // fix me
																		// !!

					if (editstatsmenu_selectedstat_index == i) {
						GL11.glColor3f(0.9f, 0.9f, 0.9f);
						Outline_Rect(Menus[MENU_EDITSTATS].x + 20, Menus[MENU_EDITSTATS].y + 45 + 22 * i, 200, 20);
					} else {
						GL11.glColor3f(0.1f, 0.1f, 0.1f);
						Outline_Rect(Menus[MENU_EDITSTATS].x + 20, Menus[MENU_EDITSTATS].y + 45 + 22 * i, 200, 20);
					}

					GL11.glColor3f(1, 1, 1);

					int stat = EditStatsMenu_Stats[i];
					int stat_string_ID = GetStatStringIndex(stat);

					Verdana_14.drawString(Menus[MENU_EDITSTATS].x + 30, Menus[MENU_EDITSTATS].y + 46 + 22 * i,
							SharedData.ComboStrings[SharedData.COMBOTEXT_STATS][stat_string_ID] + ": " + Units[UnitID].stat[stat]);

				}

				// increment buttons
				String[] incrementbuttonStrings = {"Increase +10", "Increase +5", "Increase +1", "Decrease -1", "Decrease -5", "Decrease -10"};

				for (int i = 0; i < 6; i++) {

					GL11.glColor3f(0, 0, 0);
					Fill_Rect(Menus[MENU_EDITSTATS].x + 250, Menus[MENU_EDITSTATS].y + 120 + 40 * i, 120, 30);

					GL11.glColor3f(0.7f, 0.7f, 0.7f);
					// g.setFont(new Font("Arial", Font.PLAIN, 16));
					Verdana_16.drawString(Menus[MENU_EDITSTATS].x + 260, Menus[MENU_EDITSTATS].y + 120 + 5 + 40 * i, incrementbuttonStrings[i]);

				}

				// /done button
				GL11.glColor3f(0, 0, 0);
				Fill_Rect(Menus[MENU_EDITSTATS].x + 140, Menus[MENU_EDITSTATS].y + 440, 120, 40);

				GL11.glColor3f(0.7f, 0.7f, 0.7f);
				// g.setFont(new Font("Verdana", Font.PLAIN, 18));
				Verdana_18.drawString(Menus[MENU_EDITSTATS].x + 173, Menus[MENU_EDITSTATS].y + 450, "Done");

			} else {

				GL11.glColor3f(0.9f, 0.9f, 0.9f);
				Verdana_16.drawString(Menus[MENU_EDITSTATS].x + 135, Menus[MENU_EDITSTATS].y + 10, "No Unit Selected!");

			}

		}

		if (Menus[MENU_QUESTTRACKER].is_open == true) {
			GL11.glColor3f(1, 1, 1);

			Menus[MENU_QUESTTRACKER].background.draw(Menus[MENU_QUESTTRACKER].x, Menus[MENU_QUESTTRACKER].y);

			menu_exit_x.draw(Menus[MENU_QUESTTRACKER].x + Menus[MENU_QUESTTRACKER].exitbutton_x, Menus[MENU_QUESTTRACKER].y
					+ Menus[MENU_QUESTTRACKER].exitbutton_y);

			Verdana_16.drawString(Menus[MENU_QUESTTRACKER].x + 135, Menus[MENU_QUESTTRACKER].y + 10, "Quest Tracker");

			GL11.glColor3f(1, 1, 1);

			int num_questrects_drawn = 0;
			for (int i = 0; i < 3; i++) {
				boolean draw = false;
				if (i == 0) {
					draw = true;
				} else {
					if (myquests[i - 1].enabled) {
						draw = true;
					}
				}
				if (draw) {

					quest_rect.draw(Menus[MENU_QUESTTRACKER].x + 40, Menus[MENU_QUESTTRACKER].y + 60 + 100 * num_questrects_drawn);

					num_questrects_drawn++;
				}
			}

		}

		GL11.glColor3f(1, 1, 1);

		if (Menus[MENU_BATTLERESULTS].is_open == true) {

			if (Menus[MENU_BATTLERESULTS].tweencount > 0) {
				Menus[MENU_BATTLERESULTS].tweencount--;
			} else {
				Menus[MENU_BATTLERESULTS].tweencount = 0;
			}// starts at 200, goes to 0

			GL11.glColor3f(1, 1, 1);

			Menus[MENU_BATTLERESULTS].background.draw(Menus[MENU_BATTLERESULTS].x, Menus[MENU_BATTLERESULTS].y);

			menu_exit_x.draw(Menus[MENU_BATTLERESULTS].x + Menus[MENU_BATTLERESULTS].exitbutton_x, Menus[MENU_BATTLERESULTS].y
					+ Menus[MENU_BATTLERESULTS].exitbutton_y);

			Verdana_16.drawString(Menus[MENU_BATTLERESULTS].x + 150, Menus[MENU_BATTLERESULTS].y + 8, "Battle Results");

			int UnitID = myPNum;

			int expbar_x = 50;
			int expbar_y = 50;

			Verdana_14.drawString(Menus[MENU_BATTLERESULTS].x + 60, Menus[MENU_BATTLERESULTS].y + 60, "Experience Gained During Battle");

			// draw an animated EXP bar that goes from their EXP when battle was
			// started to their EXP when battle ends!
			int oldlevel = myBattleReport.initialstats[SharedData.LEVEL];
			int oldexp = myBattleReport.initialstats[SharedData.EXP];
			int newlevel = Units[UnitID].stat[SharedData.LEVEL];
			int newexp = Units[UnitID].stat[SharedData.EXP];

			int oldtotalexp = oldexp + LevelToTotalExp(oldlevel);
			int newtotalexp = newexp + LevelToTotalExp(newlevel);

			large_exp_bar.draw(Menus[MENU_BATTLERESULTS].x + expbar_x, Menus[MENU_BATTLERESULTS].y + expbar_y);
			GL11.glColor3f(0.9f, 0.3f, 0.3f);
			float time_pct = 1 - ((float) Menus[MENU_BATTLERESULTS].tweencount / (float) (200));
			float current_total_xp = oldtotalexp + (newtotalexp - oldtotalexp) * time_pct;
			int tempint[] = TotalExpToLevel((int) current_total_xp);

			int currentlevel = tempint[0];
			if (currentlevel != BattleResMenu_StoredCurrentLevel) {
				playSound(mySoundPaths[SOUND_DRUMLOW2]);
			}
			BattleResMenu_StoredCurrentLevel = currentlevel;

			int currentexp = tempint[1];
			if (currentexp != BattleResMenu_StoredCurrentExp) {
				playSound(mySoundPaths[SOUND_EXPBARTICK]);
			}
			BattleResMenu_StoredCurrentExp = currentexp;

			int currentexpmax = currentlevel * 10;

			float barwidth = ((float) currentexp / (float) currentexpmax);

			large_exp_bar.bind();
			large_exp_bar.begin();
			large_exp_bar.draw_subimage(0, 0, Menus[MENU_BATTLERESULTS].x + expbar_x, Menus[MENU_BATTLERESULTS].y + expbar_y,
					(int) ((float) large_exp_bar.getWidth() * barwidth), large_exp_bar.getHeight());
			large_exp_bar.end();
			GL11.glColor3f(0.1f, 0.1f, 0.1f);

			int exp_to_level = currentexpmax - currentexp;
			String expbartext = exp_to_level + " xp to level";
			int pixellength = Verdana_12.getStringPixelLength(expbartext);
			Verdana_16.drawString(Menus[MENU_BATTLERESULTS].x + expbar_x + 140 - pixellength / 2, Menus[MENU_BATTLERESULTS].y + expbar_y + 10,
					expbartext);

			GL11.glColor3f(1, 1, 1);
			Arcade_20.drawString(Menus[MENU_BATTLERESULTS].x + expbar_x - 20, Menus[MENU_BATTLERESULTS].y + expbar_y + 7, "" + currentlevel);
			Arcade_20.drawString(Menus[MENU_BATTLERESULTS].x + expbar_x + 310, Menus[MENU_BATTLERESULTS].y + expbar_y + 7, "" + (currentlevel + 1));

			// if avail stat points...
			if (Menus[MENU_BATTLERESULTS].tweencount < 140) {
				if (Units[UnitID].stat[SharedData.ABILITY_POINTS_AVAIL] > 0) {
					DrawButton(0, "New Spells Available!", Menus[MENU_BATTLERESULTS].x + 200, Menus[MENU_BATTLERESULTS].y + 100);

				}
			}
			if (Menus[MENU_BATTLERESULTS].tweencount < 100) {
				if (Units[UnitID].stat[SharedData.STAT_POINTS_AVAIL] > 0) {
					DrawButton(0, "New Stats Available!", Menus[MENU_BATTLERESULTS].x + 200, Menus[MENU_BATTLERESULTS].y + 150);

				}
			}

			int unitinfo_x = 60;
			int unitinfo_y = 240;
			GL11.glColor3f(1, 1, 1);
			Verdana_18.drawString(Menus[MENU_BATTLERESULTS].x + 160, Menus[MENU_BATTLERESULTS].y + unitinfo_y, "Units Slain");

			final int num_units_drawn = 10;
			for (int i = 0; i < num_units_drawn; i++) {
				if (myBattleReport.num_units_slain > i) {
					if (Menus[MENU_BATTLERESULTS].tweencount < (num_units_drawn - i) * (200 / num_units_drawn)) {
						Unit this_unit = myBattleReport.UnitsSlain[i];
						GL11.glColor3f(0, 0, 0);
						Verdana_16.drawString(Menus[MENU_BATTLERESULTS].x + unitinfo_x, Menus[MENU_BATTLERESULTS].y + unitinfo_y + 20 + i * 20,
								myNPCDB[this_unit.stat[SharedData.UNITDATA_NPCTYPE]].namestring);

						GL11.glColor3f(0.1f, 0.7f, 0.2f);
						Verdana_16.drawString(Menus[MENU_BATTLERESULTS].x + unitinfo_x + 140, Menus[MENU_BATTLERESULTS].y + unitinfo_y + 20 + i * 20,
								"+" + myBattleReport.expgains[i] + " xp");

						GL11.glColor3f(1, 0.8f, 0);
						Verdana_16.drawString(Menus[MENU_BATTLERESULTS].x + unitinfo_x + 200, Menus[MENU_BATTLERESULTS].y + unitinfo_y + 20 + i * 20,
								"+" + myBattleReport.goldgains[i] + " gold");

					}
				}
			}

		}

		GL11.glColor3f(1, 1, 1);

		// loot window
		if (Menus[MENU_LOOTING].is_open == true) {

			Menus[MENU_LOOTING].background.draw(Menus[MENU_LOOTING].x, Menus[MENU_LOOTING].y);

			Verdana_12.drawString(Menus[MENU_LOOTING].x + 48, Menus[MENU_LOOTING].y - 1, "Loot");
			// draw the red X and the items too

			for (int i = 0; i < 6; i++) {
				if (ItemContainers[itemcontainer_currently_looting_x][itemcontainer_currently_looting_y].itemInSlot[i] != 0
						&& bagSlotOf_itemBeingDragged != 100 + i) {
					/*
					 * myItemDB[ItemContainers[itemcontainer_currently_looting_x]
					 * [itemcontainer_currently_looting_y].itemInSlot[i]].image
					 * .draw(Menus[MENU_LOOTING].x + 3 + 40 * (i % 3),
					 * Menus[MENU_LOOTING].y + 12 + 40 * (i / 3));
					 */

					int Icon_ID = myItemDB[ItemContainers[itemcontainer_currently_looting_x][itemcontainer_currently_looting_y].itemInSlot[i]].Icon_ID;
					int quantity = ItemContainers[itemcontainer_currently_looting_x][itemcontainer_currently_looting_y].itemQuantity[i];

					DrawItemIconFromSheet(Icon_ID, Menus[MENU_LOOTING].x + 3 + 40 * (i % 3), Menus[MENU_LOOTING].y + 12 + 40 * (i / 3));

					DrawItemQuantity(quantity, Menus[MENU_LOOTING].x + 3 + 40 * (i % 3), Menus[MENU_LOOTING].y + 12 + 40 * (i / 3));
				}
			}

			/*
			 * if (inventoryslot_currentlyselected >= 100) {
			 * 
			 * int slot = inventoryslot_currentlyselected - 100; int rows =
			 * ItemMenuOptions.length; int minimenu_x = Menus[MENU_LOOTING].x +
			 * 3 + 40 * (slot % 3) + 10; int minimenu_y = Menus[MENU_LOOTING].y
			 * + 12 + 40 * (slot / 3);// this // is // the // bottom!
			 * 
			 * GL11.glColor4d(0.1, 0.1, 0.1, 0.5); Fill_Rect(minimenu_x,
			 * minimenu_y - (rows * 22), 120, rows * 22);
			 * 
			 * for (int i = 0; i < rows; i++) { if (cursor.x > minimenu_x &&
			 * cursor.x < minimenu_x + 120 && cursor.y > minimenu_y - ((i + 1) *
			 * 22) && cursor.y <= minimenu_y - (i * 22)) {
			 * 
			 * Fill_Rect(minimenu_x, minimenu_y - ((i + 1) * 22), 120, 22); } }
			 * 
			 * GL11.glColor3d(1, 1, 1); for (int i = 0; i < rows; i++) {
			 * Verdana_16.drawString(minimenu_x + 5, minimenu_y - ((i + 1) *
			 * 22), ItemMenuOptions[i]);
			 * 
			 * }
			 * 
			 * }
			 */

			menu_exit_x.draw(Menus[MENU_LOOTING].x + Menus[MENU_LOOTING].exitbutton_x, Menus[MENU_LOOTING].y + Menus[MENU_LOOTING].exitbutton_y);

		}

		if (Menus[MENU_ASSETTABLE].is_open == true) {

			GL11.glColor3f(0.6f, 0.6f, 0.6f);
			assettable_tab_c.draw(Menus[MENU_ASSETTABLE].x + 100, Menus[MENU_ASSETTABLE].y - 25);
			assettable_tab_i.draw(Menus[MENU_ASSETTABLE].x + 50, Menus[MENU_ASSETTABLE].y - 25);
			assettable_tab_u.draw(Menus[MENU_ASSETTABLE].x, Menus[MENU_ASSETTABLE].y - 25);
			GL11.glColor3f(1, 1, 1);
			if (assettablemenu_currentasset == ASSETTABLE_UNITS) {
				assettable_tab_u.draw(Menus[MENU_ASSETTABLE].x, Menus[MENU_ASSETTABLE].y - 25);
			}
			if (assettablemenu_currentasset == ASSETTABLE_ITEMS) {
				assettable_tab_i.draw(Menus[MENU_ASSETTABLE].x + 50, Menus[MENU_ASSETTABLE].y - 25);
			}
			if (assettablemenu_currentasset == ASSETTABLE_EFFECTS) {
				assettable_tab_c.draw(Menus[MENU_ASSETTABLE].x + 100, Menus[MENU_ASSETTABLE].y - 25);
			}

			GL11.glColor3f(1, 1, 1);

			AssetTableBG.draw(Menus[MENU_ASSETTABLE].x, Menus[MENU_ASSETTABLE].y);
			// Fill_Rect(Menus[MENU_ASSETTABLE].x, Menus[MENU_ASSETTABLE].y,
			// Menus[MENU_ASSETTABLE].width, Menus[MENU_ASSETTABLE].height);
			GL11.glColor3f(0.9f, 0.9f, 0.9f);
			// g.setFont(new Font("Arial", Font.PLAIN, 16));

			GL11.glColor3f(0, 0, 0);
			int number_of_slots = (int) Math.floor((Menus[MENU_ASSETTABLE].height - 40) / 25);
			for (int i = 0; i < number_of_slots; i++) {
				Outline_Rect(Menus[MENU_ASSETTABLE].x + 20, Menus[MENU_ASSETTABLE].y + 35 + 25 * i, Menus[MENU_ASSETTABLE].width - 40, 20);

			}

			GL11.glColor3f(1, 1, 1);

			// fix assettable

			if (assettablemenu_currentasset == ASSETTABLE_UNITS) {
				if (assettablemenu_currentscreen == -1) {
					Verdana_16
							.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4, "All Units");

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset) + 0;
						if (SharedData.ComboStrings[SharedData.COMBOTEXT_UNITCATEGORIES].length > j) {
							Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
									SharedData.ComboStrings[SharedData.COMBOTEXT_UNITCATEGORIES][j]);
						}
					}
					// make an offset, mousewheel changes that offset!
					// draw arrows based on what the offset is!!!!
				} else {

					Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4,
							SharedData.ComboStrings[SharedData.COMBOTEXT_UNITCATEGORIES][assettablemenu_currentscreen]);

					red_back_arrow.draw(Menus[MENU_ASSETTABLE].x + 10, Menus[MENU_ASSETTABLE].y + 5);

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset);

						if (assets_in_groups[ASSETTABLE_UNITS][assettablemenu_currentscreen] != null) {
							if (myNPCDB[assets_in_groups[ASSETTABLE_UNITS][assettablemenu_currentscreen][j]].namestring != null) {
								Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
										myNPCDB[assets_in_groups[ASSETTABLE_UNITS][assettablemenu_currentscreen][j]].namestring);
							}
						}

					}

				}
			}

			if (assettablemenu_currentasset == ASSETTABLE_ITEMS) {
				if (assettablemenu_currentscreen == -1) {
					Verdana_16
							.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4, "All Items");

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset);
						if (SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS].length > j) {
							Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
									SharedData.ComboStrings[4][j]);
						}
					}
					// make an offset, mousewheel changes that offset!
					// draw arrows based on what the offset is!!!!
				} else {

					Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4,
							SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS][assettablemenu_currentscreen]);

					red_back_arrow.draw(Menus[MENU_ASSETTABLE].x + 10, Menus[MENU_ASSETTABLE].y + 5);

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset);

						if (assets_in_groups[ASSETTABLE_ITEMS][assettablemenu_currentscreen] != null) {
							if (myItemDB[assets_in_groups[ASSETTABLE_ITEMS][assettablemenu_currentscreen][j]].namestring != null) {
								Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
										myItemDB[assets_in_groups[ASSETTABLE_ITEMS][assettablemenu_currentscreen][j]].namestring);
							}
						}

					}

				}
			}

			if (assettablemenu_currentasset == ASSETTABLE_EFFECTS) {
				if (assettablemenu_currentscreen == -1) {
					Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4,
							"All Effects");

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset) + 0;
						if (SharedData.ComboStrings[SharedData.COMBOTEXT_EFFECTTYPES].length > j) {
							Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
									SharedData.ComboStrings[5][j]);
						}
					}
					// make an offset, mousewheel changes that offset!
					// draw arrows based on what the offset is!!!!
				} else {

					Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width / 2 - 40, Menus[MENU_ASSETTABLE].y + 4,
							SharedData.ComboStrings[5][assettablemenu_currentscreen]);

					red_back_arrow.draw(Menus[MENU_ASSETTABLE].x + 10, Menus[MENU_ASSETTABLE].y + 5);

					for (int i = 0; i < number_of_slots; i++) {
						int j = i + (Menus[MENU_ASSETTABLE].scrolloffset);

						if (assets_in_groups[ASSETTABLE_EFFECTS][assettablemenu_currentscreen] != null) {
							if (myEffectDB[assets_in_groups[ASSETTABLE_EFFECTS][assettablemenu_currentscreen][i]].namestring != null) {
								Verdana_16.drawString(Menus[MENU_ASSETTABLE].x + 20 + 4, Menus[MENU_ASSETTABLE].y + 35 + 25 * i,
										myEffectDB[assets_in_groups[ASSETTABLE_EFFECTS][assettablemenu_currentscreen][j]].namestring);
							}
						}

					}

				}
			}

			// draw read-only scroll bar
			if (Menus[MENU_ASSETTABLE].scrolloffset > 0) {
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
				int scrollbar_y = 40 + Menus[MENU_ASSETTABLE].y + (Menus[MENU_ASSETTABLE].scrolloffset * 10);
				if (scrollbar_y > Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height - 40) {
					scrollbar_y = Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height - 40;
				}
				Fill_Rect(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width - 10, scrollbar_y, 10, 30);
			}

			GL11.glColor3f(1, 1, 1);
			menu_exit_x.draw(Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].exitbutton_x, Menus[MENU_ASSETTABLE].y
					+ Menus[MENU_ASSETTABLE].exitbutton_y);
			// menu_exit_x.draw( Menus[MENU_ASSETTABLE].x +
			// Menus[MENU_ASSETTABLE].width -15 , Menus[MENU_ASSETTABLE].y -
			// 5);aa

			if (assettablemenu_ItemCurrentlyDragged > 0) {// draw the item being
															// dragged

				if (assettablemenu_currentasset == ASSETTABLE_UNITS) {
					int model_ID = myNPCDB[assettablemenu_ItemCurrentlyDragged].model_ID;
					int framewidth = myUnitModels[model_ID].frame_width;

					int framebuffer = 0;
					if (framewidth > 64) {
						framebuffer = framewidth - 64;
					}

					myUnitModels[model_ID].image.bind();
					myUnitModels[model_ID].image.begin();
					myUnitModels[model_ID].image.draw_subimage(11, 15 + (framewidth * 2), cursor.x - 20 - framebuffer / 2, cursor.y - 20
							- framebuffer, framewidth, framewidth);
					myUnitModels[model_ID].image.end();
				}
				if (assettablemenu_currentasset == ASSETTABLE_ITEMS) {
					DrawItemIconFromSheet(myItemDB[assettablemenu_ItemCurrentlyDragged].Icon_ID, cursor.x - 20, cursor.y - 20);
				}
				if (assettablemenu_currentasset == ASSETTABLE_EFFECTS) {
					Verdana_16.drawString(cursor.x - 20, cursor.y - 20, myEffectDB[assettablemenu_ItemCurrentlyDragged].namestring);
					// myUnitModels[myNPCDB[assettablemenu_ItemCurrentlyDragged].model_ID].image.draw(cursor.x
					// - 20,cursor.y - 20);
				}

				if (allbigmenus_notbeinghovered() && !Menus[MENU_LOOTING].BeingHovered_AndOpen(cursor)) {
					GL11.glColor4f(0.6f, 0.6f, 1, 0.8f);
					cursor.whitesquare.draw(TILE_SIZE * (cursor.x / TILE_SIZE), TILE_SIZE * (cursor.y / TILE_SIZE));

				}

			}

		} else {
			if (myPNum == 1) {
				GL11.glColor3f(1, 1, 1);
				assettable_crumb.draw(0, Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height / 2 - 20);
			}
		}

		if (mySettings.Tutorial) {
			// draw tutorial hints, make it look just like eve onlines

			poll_hint_phase();
			int hint_ID = GetCurrentHint();

			if (hint_ID != last_hint_ID && hint_ID > -1) {
				Menus[MENU_TUTORIAL].tweencount = 100;
			}
			last_hint_ID = hint_ID;

			float alpha = 1f - (Menus[MENU_TUTORIAL].tweencount / 100f);

			int x = Menus[MENU_TUTORIAL].x - Menus[MENU_TUTORIAL].tweencount;
			int y = Menus[MENU_TUTORIAL].y;

			int maxwidth = 340;
			int maxheight = 140;

			if (hint_ID > -1) {
				String text = TutorialHint[hint_ID].text;
				int myheight = 16 * Verdana_14.GetStringNumLines(text, maxwidth) + 12;

				GL11.glColor4f(0, 0, 0, 0.5f * alpha);
				Draw_Line_Smooth(TutorialHint[hint_ID].x, TutorialHint[hint_ID].y, x + maxwidth / 2, y + myheight);

				GL11.glColor4f(0.5f, 0.95f, 0f, 0.4f * alpha);
				Fill_Rect(x - 2, y - 2, maxwidth + 4, myheight + 4);

				GL11.glColor4f(0, 0, 0, 0.9f * alpha);
				Fill_Rect(x, y, maxwidth, myheight);

				GL11.glColor4f(1, 1, 1, alpha);
				Verdana_14.drawString(x + 5, y + 5, text, maxwidth, maxheight, 16);

			}

		}
		GL11.glColor3f(1, 1, 1);

		for (int i = 0; i < DialogMenus.length; i++) {

			if (DialogMenus[i].is_open) {
				GL11.glColor4f(0, 0, 0, 0.8f);
				Fill_Rect(DialogMenus[i].x, DialogMenus[i].y, DialogMenus[i].width, DialogMenus[i].height);

				GL11.glColor4f(0.6f, 0, 0, 0.8f);
				Fill_Rect(DialogMenus[i].x + DialogMenus[i].width / 2 - 100 / 2, DialogMenus[i].y + 33, 100, 20);

				GL11.glColor3f(1, 1, 1);

				int pixel_length = Verdana_18.getStringPixelLength(DialogMenus[i].text);
				Verdana_18.drawString(SCREEN_WIDTH / 2 - pixel_length / 2, DialogMenus[i].y + 7, DialogMenus[i].text);

				pixel_length = Verdana_14.getStringPixelLength(DialogMenus[i].buttonlabel);
				Verdana_14.drawString(SCREEN_WIDTH / 2 - pixel_length / 2, SCREEN_HEIGHT / 2 - DialogMenus[i].height / 2 + 35,
						DialogMenus[i].buttonlabel);

			}

		}

		GL11.glColor3f(1, 1, 1);

		// mouse hover tooltips for everyone!

		if (displayedGenericHovertooltip != null) {
			DrawGenericHoverTooltip(displayedGenericHovertooltip);
		} else if (displayedAbilityHovertooltip > 0) {
			DrawAbilityHoverTooltip(displayedAbilityHovertooltip);

		} else if (displayedItemHovertooltip > 0) {
			DrawItemHoverTooltip(displayedItemHovertooltip);

		} else if (displayedConditionHovertooltip > 0) {
			DrawConditionHoverTooltip(displayedConditionHovertooltip);
		} else if (displayedMiniHovertooltip != null) {
			DrawMiniHoverTooltip(displayedMiniHovertooltip);
		}

		GL11.glColor3f(1, 1, 1);

		/*
		 * if (displayedHovertooltip != null) {
		 * 
		 * 
		 * } else{
		 * 
		 * for(int i=0;i<5;i++){ if (displayedHovertooltip_lines[i] != null) {
		 * Verdana_16.drawString(cursor.x - 95 + tooltipoffset_x, cursor.y +
		 * tooltipoffset_y + 18*i - 5, displayedHovertooltip_lines[i]);
		 * 
		 * } } }
		 */

		int unit_of_bagslots = myPNum;
		if (myPNum == 1) {
			unit_of_bagslots = UnitMap[cam.tileselect_x][cam.tileselect_y];
		}

		if (unit_of_bagslots > 0) {

			if (bagSlotOf_itemBeingDragged != -1 /*
												 * &&
												 * myTypeDB[Units[unit_of_bagslots
												 * ].itemInSlot[
												 * bagSlotOf_itemBeingDragged
												 * ]].image != null
												 */) {

				/*
				 * myItemDB[Units[unit_of_bagslots].itemInSlot[
				 * bagSlotOf_itemBeingDragged]].image .draw(cursor.x - 20,
				 * cursor.y - 20);
				 */

				int Icon_ID = myItemDB[Units[unit_of_bagslots].itemInSlot[bagSlotOf_itemBeingDragged]].Icon_ID;
				DrawItemIconFromSheet(Icon_ID, cursor.x - 20, cursor.y - 20);

			}// THIS MUST BE LAST AND ALWAYS ON TOP

		}

		if (Menus[MENU_LOOTING].is_open) {
			int slot = bagSlotOf_itemBeingDragged - 100;
			if (slot > -1) {

				int mapx = cam.tileselect_x;
				int mapy = cam.tileselect_y;
				if (coordinatesWithinMapBounds(mapx, mapy)) {

					int item_ID = ItemContainers[mapx][mapy].itemInSlot[slot];

					int Icon_ID = myItemDB[item_ID].Icon_ID;
					DrawItemIconFromSheet(Icon_ID, cursor.x - 20, cursor.y - 20);

				}
			}

		}

		// /warning popup
		GL11.glColor3f(1, 1, 1);

		if (PopUpMessageTimeLeft > 0) {

			int pixellength = Verdana_16.getStringPixelLength(PopUpMessage);
			GL11.glColor3f(0, 0, 0);
			Fill_Rect(SCREEN_WIDTH / 2 - pixellength / 2 - 20, SCREEN_HEIGHT / 2 - 100 - 5, pixellength + 40, 30);
			GL11.glColor3f(1, 1, 1);
			Verdana_16.drawString(SCREEN_WIDTH / 2 - pixellength / 2, SCREEN_HEIGHT / 2 - 100, PopUpMessage);

		}

		if (DEBUG_MODE) {
			int Unit_ID = UnitMap[cam.tileselect_x][cam.tileselect_y];
			Verdana_16.drawString(30, 300 + 30 * 0, "" + Units[Unit_ID].stat[SharedData.WALK_MOVES_COUNT]);

		}

	}// end gamerendering

	boolean[] Keyboard_KeyAlreadyDown = new boolean[255];
	boolean[] Mouse_MouseAlreadyClicked = new boolean[3];

	boolean just_clickeddown_on_unit;

	public void pollInput_Hero() {

		// int cursor.wheel_delta = Mouse.getDWheel();

		// cursor.x = Mouse.getX();
		// cursor.y = SCREEN_HEIGHT - Mouse.getY();

		displayedItemHovertooltip = 0;
		displayedAbilityHovertooltip = 0;
		displayedConditionHovertooltip = 0;
		displayedGenericHovertooltip = null;
		displayedMiniHovertooltip = null;
		/*
		 * displayedLeftTooltip=null; for (int i = 0; i <
		 * displayedLeftTooltip_lines.length; i++) {
		 * displayedLeftTooltip_lines[i] = null; displayedHovertooltip_lines[i]
		 * = null; }
		 */

		/** MOUSE HOVERING */

		/*
		 * int x = (cursor.x / TILE_SIZE) + (cam.x - (SCREEN_X_TILES / 2)); int
		 * y = (cursor.y / TILE_SIZE) + (cam.y - (SCREEN_Y_TILES / 2));
		 */

		int x = cursor.currentmousetile_x;// confusing but whatev
		int y = cursor.currentmousetile_y;

		if (allbigmenus_notbeinghovered() && !Menus[MENU_LOOTING].BeingHovered_AndOpen(cursor)) {

			int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];
			if (cam.TileSelection_Within_Bounds()) {

				PollConditionbarHoverTooltips(UnitID);

				PollAbilitybarHoverTooltip(UnitID);

			}

			if (UnitID > 0) {

				if (cursor.WithinRect(GUIHealthBar.x, GUIHealthBar.y, GUIHealthBar.width, GUIHealthBar.height)) {
					int health = Units[UnitID].stat[SharedData.HEALTH];
					int maxhealth = get_unit_maxhealth(UnitID);

					displayedMiniHovertooltip = "Health: " + health + "/" + maxhealth;
				}
				if (cursor.WithinRect(GUIEnergyBar.x, GUIEnergyBar.y, GUIEnergyBar.width, GUIEnergyBar.height)) {
					int energy = Units[UnitID].stat[SharedData.ENERGY];
					int maxenergy = get_unit_maxenergy(UnitID);

					displayedMiniHovertooltip = "Energy: " + energy + "/" + maxenergy;
				}
			}

		}// end check not out of bounds

		if (Menus[MENU_INV].is_open == true) {// /inv menu hovering

			pollInvMenuHovering();

		}// end invmenu stuff

		if (Menus[MENU_ITEMVENDOR].is_open) {

			if (Menus[MENU_ITEMVENDOR].mode < 100 && Menus[MENU_ITEMVENDOR].mode > 0) {// store
																						// stuff

				int itemgroup = Menus[MENU_ITEMVENDOR].mode + 2;
				int number_of_slots = number_of_purchasable_items[itemgroup];

				for (int i = 0; i < number_of_slots; i++) {

					if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i, Menus[MENU_ITEMVENDOR].width - 20,
							20)) {

						int j = i + Menus[MENU_ITEMVENDOR].scrolloffset;

						int itemnum = purchasable_items[itemgroup][j];
						if (itemnum > 0) {
							debug(itemnum);
							displayedItemHovertooltip = itemnum; // tooltip
						}
					}

				}

			}
		}

		if (Menus[MENU_LOOTING].is_open == true) {

			if (cursor.x > Menus[MENU_LOOTING].x + 3 && cursor.x < Menus[MENU_LOOTING].x + 3 + 120 && cursor.y > Menus[MENU_LOOTING].y + 12
					&& cursor.y < Menus[MENU_LOOTING].y + 12 + 80) {

				int bagslotHoveringOver = ((cursor.x - 3 - Menus[MENU_LOOTING].x) / 40 + 3 * ((cursor.y - 12 - Menus[MENU_LOOTING].y) / 40)) + 100; // the
																																					// loot
																																					// !

				displayedItemHovertooltip = Units[myPNum].itemInSlot[bagslotHoveringOver];

			}
		}// end loot menu stuff

		if (Menus[MENU_ABILITIES].is_open == true) {// SKILL menu hovering

			// int abilityhovered = -1;

			if (abilitymenu_pane >= 1 && abilitymenu_pane <= 10) {

				for (int i = 0; i < 10; i++) {

					if (cursor.x > Menus[MENU_ABILITIES].x + 30 + 185 * (i % 2) - 5
							&& cursor.x < Menus[MENU_ABILITIES].x + 30 + 185 * (i % 2) + 165 - 5
							&& cursor.y > Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) - 5
							&& cursor.y < Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) + 50 - 5) {

						displayedAbilityHovertooltip = AbilitiesInEachSpec[abilitymenu_pane][i];

					}
				}

			}

			if (abilitymenu_pane >= 11) {

				for (int i = 0; i < 6 * 5; i++) {
					int j = i + Menus[MENU_ABILITIES].scrolloffset * 6;

					if (cursor.x > Menus[MENU_ABILITIES].x + 40 + 50 * (i % 6) && cursor.x < Menus[MENU_ABILITIES].x + 40 + 50 * (i % 6) + 40
							&& cursor.y > Menus[MENU_ABILITIES].y + 80 + 50 * (i / 6) && cursor.y < Menus[MENU_ABILITIES].y + 80 + 50 * (i / 6) + 40) {

						if (j >= 0) {
							int ability = weaponskillsorder[j];

							displayedAbilityHovertooltip = ability;

						}

					}
				}

			}

		}

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			if (cursor.x - menu_dragoffset_x > -200 && cursor.x - menu_dragoffset_x < SCREEN_WIDTH - 250 && cursor.y - menu_dragoffset_y > 10
					&& cursor.y - menu_dragoffset_y < SCREEN_HEIGHT - 50) {

				for (int i = 0; i < draggablemenus.length; i++) {
					if (draggablemenus[i] == menu_beingDragged) {
						debug(draggablemenus[i]);
						Menus[draggablemenus[i]].x = cursor.x - menu_dragoffset_x;
						Menus[draggablemenus[i]].y = cursor.y - menu_dragoffset_y;
					}

				}

			}// end offthescreen check

			if (menu_beingDragged == MENU_MINIMAP && Menus[MENU_MINIMAP].is_open) {

				int minimap_x = (cursor.x - Menus[7].x) / 2;
				int minimap_y = (cursor.y - Menus[7].y) / 2;

				if (minimap_x > 0 && minimap_x < SharedData.MAP_SIZE && minimap_y > 0 && minimap_y < SharedData.MAP_SIZE) {

					cam.x = minimap_x;
					cam.y = minimap_y;
				}

			}

			if (map_beingDragged) {
				int offset_x = (map_dragoffset_x - cursor.x) / 40;
				int offset_y = (map_dragoffset_y - cursor.y) / 40;
				cam.x = map_drag_original_x + offset_x;
				cam.y = map_drag_original_y + offset_y;
			}

			/*
			 * queued_hero_facing = -1; if (Hero_changing_facing) { double angle
			 * = Math.toDegrees(Math.atan2(Units[myPNum].x -
			 * cursor.currentmousetile_x, Units[myPNum].y -
			 * cursor.currentmousetile_y)); int direction = 0; if (angle < 0) {
			 * angle += 360; } if (angle <= 45 || angle > 315) { direction = 0;
			 * } if (angle > 225 && angle <= 315) { direction = 1; } if (angle >
			 * 135 && angle <= 225) { direction = 2; } if (angle > 45 && angle
			 * <= 135) { direction = 3; }
			 * 
			 * int cursordist = pythagorean(Units[myPNum].x, Units[myPNum].y,
			 * cursor.currentmousetile_x, cursor.currentmousetile_y);
			 * 
			 * if (Units[myPNum].stat[myshareddata.FACING] != direction &&
			 * cursordist >= 2) {
			 * 
			 * queued_hero_facing = direction;
			 * 
			 * } }
			 */

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) { // non
																									// draggy
																									// things...
																									// mouse
																									// pressed
																									// event!

				if (myPNum != 1) {// add a mouselistener for the clients so they
									// can click to move and hover over units
									// for info (no cursor though)

					counter_mousehelddown = 0;// begin the counter
					boolean justcancelledtargetting = false;

					boolean clickedGUIElement = false;

					// if mouse clicked on minimap...start dragging
					if (cursor.x > Menus[7].x && cursor.x < Menus[7].x + Menus[7].width && cursor.y > Menus[7].y
							&& cursor.y < Menus[7].y + Menus[7].height && Menus[MENU_MINIMAP].is_open) {

						menu_beingDragged = MENU_MINIMAP;
						clickedGUIElement = true;
					}

					if (cursor.x > SCREEN_WIDTH - 90 && cursor.x < SCREEN_WIDTH - 90 + 60 && cursor.y > 232 && cursor.y < 232 + 60) {
						current_D20_roll = (int) (Math.random() * 20) + 1;
						playSound(mySoundPaths[SOUND_DICEROLL]);
						clickedGUIElement = true;
						client_sendnewstorylogmessage(Players[myPNum].name + " has rolled a " + current_D20_roll + ".");

					}

					if (Mouse.isButtonDown(1) && targetting_currentability != 0) {
						targetting_currentability = 0;
						justcancelledtargetting = true;
					}// right clicking while targetting an ability will cancel
						// it

					// clicking loot panel. This is allowed to happen while
					// other menus are active! This also disables >all< other
					// actions.

					if (allbigmenus_notbeinghovered() && !Menus[MENU_LOOTING].BeingHovered_AndOpen(cursor)) {

						int UnitSelectedID = UnitMap[cam.tileselect_x][cam.tileselect_y];

						boolean clicked = PollClickingOnAbilitybars(UnitSelectedID);
						if (clicked == true) {
							clickedGUIElement = true;
						}

						clicked = PollClickingOnBattleQueue(UnitSelectedID);
						if (clicked == true) {
							clickedGUIElement = true;
						}

						// close loot menu
						if (Menus[MENU_LOOTING].is_open && !Menus[MENU_LOOTING].BeingHovered(cursor)) {
							Menus[MENU_LOOTING].is_open = false;
						}

						// check if right clicking on a lootable object. If
						// true, a
						// loot window will open
						if (clickedGUIElement == false && targetting_currentability == 0) {
							tiledist_fromhero = Math.sqrt(Math.abs(Math.pow(y - Units[myPNum].y, 2)) + Math.abs(Math.pow(x - Units[myPNum].x, 2)));
							for (int z = myshareddata.TERRAIN_LAYER_LOWER; z < myshareddata.REGION_LAYER; z++) {
								if (cursor.WithinMapBounds(SharedData.MAP_SIZE)) {
									if (myTypeDB[map[z][cursor.currentmousetile_y][cursor.currentmousetile_x]].Can_Contain_Items == true
											&& tiledist_fromhero < 2) {
										clickedGUIElement = true;
										openGameMenu(MENU_LOOTING);
										itemcontainer_currently_looting_x = x;
										itemcontainer_currently_looting_y = y;

									}
								}
							}
						}

						/*
						 * if
						 * (Units[UnitSelectedID].stat[SharedData.STAT_POINTS_AVAIL
						 * ] > 0 && cursor.x > (secondaryabilitybar.x - 60) &&
						 * cursor.x < (secondaryabilitybar.x + -60 + 45) &&
						 * cursor.y > (secondaryabilitybar.y) && cursor.y <
						 * (secondaryabilitybar.y + secondaryabilitybar.height)
						 * && targetting_currentability == 0) {
						 * 
						 * // Menus[MENU_ABILITIES].is_open = true;
						 * clickedGUIElement = true; // abilitymenu_pane = 0;
						 * openGameMenu(MENU_INV); }
						 * 
						 * if (Units[UnitSelectedID].stat[SharedData.
						 * ABILITY_POINTS_AVAIL] > 0 && cursor.x >
						 * (secondaryabilitybar.x + secondaryabilitybar.width +
						 * 60) && cursor.x < (secondaryabilitybar.x +
						 * secondaryabilitybar.width + 60 + 45) && cursor.y >
						 * (secondaryabilitybar.y) && cursor.y <
						 * (secondaryabilitybar.y + secondaryabilitybar.height)
						 * && targetting_currentability == 0) {
						 * 
						 * // Menus[MENU_ABILITIES].is_open = true;
						 * clickedGUIElement = true; // abilitymenu_pane = 0;
						 * openGameMenu(MENU_ABILITIES); }
						 */

						// end check for selecting unit with ownership

						if (UnitSelectedID > 0) {
							if (Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == false) {// if
																									// right
																									// clicking
																									// on
																									// a
																									// selected
																									// unit
								if (cursor.currentmousetile_x == cam.tileselect_x && cursor.currentmousetile_y == cam.tileselect_y) {
									openGameMenu(MENU_INV);
								}
							}
						}

						int maptileclicked_x = (cursor.x / TILE_SIZE) + cam.x - ((SCREEN_X_TILES / 2));
						int maptileclicked_y = (cursor.y / TILE_SIZE) + cam.y - ((SCREEN_Y_TILES / 2));

						boolean cast_an_ability = false;

						// cast targetted ability
						if (targetting_currentability > 0 && clickedGUIElement == false) {

							if (maptileclicked_x > -1 && maptileclicked_x < SharedData.MAP_SIZE && maptileclicked_y > -1
									&& maptileclicked_y < SharedData.MAP_SIZE) {

								cast_an_ability = true;

								Anyone_Broadcast_CastAbility(targetting_currentability, Units[targetting_currentcaster].x,
										Units[targetting_currentcaster].y, maptileclicked_x, maptileclicked_y);

							}
						}

						// begin pulling the map around
						if (Mouse.isButtonDown(1) && !clickedGUIElement) {
							map_beingDragged = true;
							map_dragoffset_x = cursor.x;
							map_dragoffset_y = cursor.y;
							map_drag_original_x = cam.x;
							map_drag_original_y = cam.y;
						}

						// select a unit
						if (clickedGUIElement == false && justcancelledtargetting == false && !cast_an_ability && Mouse.isButtonDown(0)) {

							int UnitClickedID = 0;
							if (maptileclicked_x > 0 && maptileclicked_x < SharedData.MAP_SIZE && maptileclicked_y > 0
									&& maptileclicked_y < SharedData.MAP_SIZE) {
								UnitClickedID = UnitMap[maptileclicked_x][maptileclicked_y];
							}

							if (UnitClickedID > 0) {
								if (HeroCanSeeUnit((maptileclicked_x * 4) + 2, (maptileclicked_y * 4) + 2)
										|| Units[UnitClickedID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum || myPNum == 1) {

									// if unit is already selected, give
									// walk command
									if (cam.tileselect_x == maptileclicked_x && cam.tileselect_y == maptileclicked_y
											&& Units[UnitClickedID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
										Anyone_ClickOnAbility(SPECIALABIL_WALK, UnitClickedID, false);

									} else {
										cam.tileselect_x = maptileclicked_x;
										cam.tileselect_y = maptileclicked_y;
									}

								} else {
									cam.tileselect_x = Units[myPNum].x;
									cam.tileselect_y = Units[myPNum].y;
								}

							}
						}

						int minibuttonclicked = -1;
						for (int i = 0; i < 3; i++) {
							if (cursor.x > minimenubar.x + 20 * i && cursor.x < minimenubar.x + 20 + 20 * i && cursor.y > minimenubar.y
									&& cursor.y < minimenubar.y + minimenubar.height) {
								minibuttonclicked = i;
							}

						}

						if (minibuttonclicked == 0) {
							openGameMenu(2);
						}
						if (minibuttonclicked == 1) {
							openGameMenu(1);
						}
						if (minibuttonclicked == 2) {
							openGameMenu(3);
						}

						/*
						 * if(minibuttonclicked==-1){ //close all menus for(int
						 * i=1;i<=6;i++){ Menus[i].is_open=false;} }
						 */

					}// endnomenucheck

					// dragging the looting window
					if (Menus[MENU_LOOTING].is_open == true) {
						if (cursor.x > (Menus[MENU_LOOTING].x) && cursor.x < (Menus[MENU_LOOTING].x + Menus[MENU_LOOTING].width)
								&& cursor.y > Menus[MENU_LOOTING].y - 5 && cursor.y < Menus[MENU_LOOTING].y + 15) {
							menu_beingDragged = MENU_LOOTING;
							menu_dragoffset_x = cursor.x - Menus[MENU_LOOTING].x;
							menu_dragoffset_y = cursor.y - Menus[MENU_LOOTING].y;
						}

						// Clicking on items from loot window FIX THIS SO A
						// CLICK WILL DO IT!!

						if (Menus[MENU_LOOTING].BeingHovered(cursor)) {

							if (Menus[MENU_LOOTING].ExitButtonBeingHovered_AndOpen(cursor)) {
								Menus[MENU_LOOTING].is_open = false;
							} else if (cursor.x > Menus[MENU_LOOTING].x + 3 && cursor.x < Menus[MENU_LOOTING].x + 3 + 120
									&& cursor.y > Menus[MENU_LOOTING].y + 12 && cursor.y < Menus[MENU_LOOTING].y + 12 + 80) {

								int slot = (cursor.x - 3 - Menus[MENU_LOOTING].x) / 40 + 3 * ((cursor.y - 12 - Menus[MENU_LOOTING].y) / 40);
								int item_id = ItemContainers[itemcontainer_currently_looting_x][itemcontainer_currently_looting_y].itemInSlot[slot];

								int first_open_bagslot = -1;
								first_open_bagslot = getFirstFreeBagSlot(myPNum);
								if (first_open_bagslot > -1) {

									Client_TellServer_EditUnitItemIDQuantity(Units[myPNum].x, Units[myPNum].y, item_id, +1);

									Client_TellServer_EditTileItemIDQuantity(itemcontainer_currently_looting_x, itemcontainer_currently_looting_y,
											item_id, -1);

								}
							}

						}

					}

					if (Menus[MENU_INV].is_open == true) {

						if (Menus[MENU_INV].ExitButtonBeingHovered_AndOpen(cursor)) {
							Menus[MENU_INV].is_open = false;
						} else {
							if (cursor.x > (Menus[MENU_INV].x) && cursor.x < (Menus[MENU_INV].x + Menus[MENU_INV].width)
									&& cursor.y > Menus[MENU_INV].y && cursor.y < Menus[MENU_INV].y + 30) {
								menu_beingDragged = MENU_INV;
								menu_dragoffset_x = cursor.x - Menus[MENU_INV].x;
								menu_dragoffset_y = cursor.y - Menus[MENU_INV].y;
							}

							// /charms

							if (!Menus[MENU_ITEMVENDOR].is_open) {
								if (cursor.WithinRect(Menus[MENU_INV].x - 30, Menus[MENU_INV].y + 20, 33, 33)) {
									openGameMenu(MENU_ITEMVENDOR);
									Menus[MENU_ITEMVENDOR].mode = 0;
									clickedGUIElement = true;
								}
								if (cursor.WithinRect(Menus[MENU_INV].x - 30, Menus[MENU_INV].y + 50, 33, 33)) {
									openGameMenu(MENU_ITEMVENDOR);
									Menus[MENU_ITEMVENDOR].mode = 100;
									clickedGUIElement = true;
								}
							}

							// Start dragging an item out of bag
							if (cursor.x > Menus[MENU_INV].x + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318
									&& cursor.y < Menus[MENU_INV].y + 318 + 160) {
								int slot = (cursor.x - 18 - Menus[MENU_INV].x) / 40 + 6 * ((cursor.y - 318 - Menus[MENU_INV].y) / 40);
								bagSlotOf_itemBeingDragged = slot; // start
																	// dragging
																	// an
																	// item
																	// from
																	// a
																	// bagslot
							}

							// start dragging item off of being equipped
							for (int n = 0; n < 9; n++) {

								if (cursor.x > Menus[MENU_INV].x + invmenu_equipslots_x[n]
										&& cursor.x < Menus[MENU_INV].x + invmenu_equipslots_x[n] + 40
										&& cursor.y > Menus[MENU_INV].y + invmenu_equipslots_y[n]
										&& cursor.y < Menus[MENU_INV].y + invmenu_equipslots_y[n] + 40) {
									int slot = 50 + n;
									bagSlotOf_itemBeingDragged = slot; // start
																		// dragging
																		// an
																		// item
																		// from
																		// a
																		// equipslot

								}

							}

							// invmenu_cursor.x = cursor.x;
							// invmenu_cursor.y = cursor.y;

							if (Units[myPNum].stat[SharedData.STAT_POINTS_AVAIL] > 0) {

								int basestats_x = 170;
								int basestats_y = 180;

								int statToIncrease = -1; // not increasing
															// any
															// stats

								for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {

									if (cursor.x > Menus[MENU_INV].x + basestats_x + 110 * (i / 4)
											&& cursor.x < Menus[MENU_INV].x + basestats_x + 110 * (i / 4) + 90
											&& cursor.y > Menus[MENU_INV].y + basestats_y + 20 * (i % 4)
											&& cursor.y < Menus[MENU_INV].y + basestats_y + 20 * (i % 4) + 20 && Mouse.isButtonDown(0)) {

										statToIncrease = i + SharedData.ENDURANCE;
									}

								}

								if (statToIncrease != -1) {
									Client_EditStats(SharedData.STAT_POINTS_AVAIL, Units[myPNum].stat[SharedData.STAT_POINTS_AVAIL] - 1, myPNum);
									Client_EditStats(statToIncrease, Units[myPNum].stat[statToIncrease] + 1, myPNum);
								}

							}
						}
					}

					if (Menus[MENU_ESC].is_open == true) {// ESCmenu

						handleinputs_ESCMenu();
					}

					if (Menus[MENU_BATTLERESULTS].is_open && !clickedGUIElement) {

						// close
						if (Menus[MENU_BATTLERESULTS].ExitButtonBeingHovered_AndOpen(cursor)) {
							Menus[MENU_BATTLERESULTS].is_open = false;
						} else {

							// start dragging
							if (Menus[MENU_BATTLERESULTS].GenericToolbarBeingHovered(cursor)) {
								menu_beingDragged = MENU_BATTLERESULTS;
								menu_dragoffset_x = cursor.x - Menus[MENU_BATTLERESULTS].x;
								menu_dragoffset_y = cursor.y - Menus[MENU_BATTLERESULTS].y;
							}

							// other stuff:
							/*
							 * Button to select new perk
							 */
							int UnitID = myPNum;

							// if avail stat points...
							if (Menus[MENU_BATTLERESULTS].tweencount < 140) {
								if (Units[UnitID].stat[SharedData.ABILITY_POINTS_AVAIL] > 0) {

									if (cursor.WithinRect(Menus[MENU_BATTLERESULTS].x + 100, Menus[MENU_BATTLERESULTS].y + 100, 200, 40)) {
										openGameMenu(MENU_ABILITIES);
										clickedGUIElement = true;
									}

								}
							}
							if (Menus[MENU_BATTLERESULTS].tweencount < 100) {
								if (Units[UnitID].stat[SharedData.STAT_POINTS_AVAIL] > 0) {

									if (cursor.WithinRect(Menus[MENU_BATTLERESULTS].x + 100, Menus[MENU_BATTLERESULTS].y + 150, 200, 40)) {
										openGameMenu(MENU_INV);
										clickedGUIElement = true;
									}

								}
							}

						}
					}

					if (Menus[MENU_ITEMVENDOR].is_open && !clickedGUIElement) {

						// close
						if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x + 180, Menus[MENU_ITEMVENDOR].y + 30, 14, 14)) {
							Menus[MENU_ITEMVENDOR].is_open = false;
						} else {

							// start dragging
							if (Menus[MENU_ITEMVENDOR].GenericToolbarBeingHovered(cursor)) {
								menu_beingDragged = MENU_ITEMVENDOR;
								menu_dragoffset_x = cursor.x - Menus[MENU_ITEMVENDOR].x;
								menu_dragoffset_y = cursor.y - Menus[MENU_ITEMVENDOR].y;
							}

							// tabs

							if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x, Menus[MENU_ITEMVENDOR].y, 55, 25)) {
								Menus[MENU_ITEMVENDOR].mode = 0;
							}

							if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x + 55, Menus[MENU_ITEMVENDOR].y, 50, 25)) {
								Menus[MENU_ITEMVENDOR].mode = 100;
							}

							// click items

							if (Menus[MENU_ITEMVENDOR].mode < 100) {// store
																	// stuff

								int number_of_slots = 0;
								if (Menus[MENU_ITEMVENDOR].mode == 0) {
									number_of_slots = 5;
									for (int i = 0; i < number_of_slots; i++) {

										if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i,
												Menus[MENU_ITEMVENDOR].width - 20, 20)) {

											int j = i + Menus[MENU_ITEMVENDOR].scrolloffset + 1;
											// if
											// (SharedData.ComboStrings[AssetTableComboStringIndices[assettablemenu_currentasset]].length
											// > j) {
											Menus[MENU_ITEMVENDOR].mode = j;
											Menus[MENU_ITEMVENDOR].scrolloffset = 0;
											// }
										}

									}
								} else {
									int itemgroup = Menus[MENU_ITEMVENDOR].mode + 2;
									number_of_slots = number_of_purchasable_items[itemgroup];

									// click back button
									if (cursor.x > Menus[MENU_ITEMVENDOR].x + 10 && cursor.x < Menus[MENU_ITEMVENDOR].x + 10 + 30
											&& cursor.y > Menus[MENU_ITEMVENDOR].y + 40 && cursor.y < Menus[MENU_ITEMVENDOR].y + 40 + 20) {

										Menus[MENU_ITEMVENDOR].mode = 0;
										Menus[MENU_ITEMVENDOR].scrolloffset = 0;
									}

									for (int i = 0; i < number_of_slots; i++) {

										if (cursor.WithinRect(Menus[MENU_ITEMVENDOR].x + 10, Menus[MENU_ITEMVENDOR].y + 70 + 25 * i,
												Menus[MENU_ITEMVENDOR].width - 20, 20)) {

											int j = i + Menus[MENU_ITEMVENDOR].scrolloffset;

											int itemnum = purchasable_items[itemgroup][j];
											if (itemnum > 0) {
												debug(itemnum);
												ItemVendor_itemBeingDragged = itemnum;
											}
										}

									}

								}

							} else {// crafting stuff

							}

						}
					}

					if (Menus[MENU_ABILITIES].is_open && !clickedGUIElement) {// SKILL
						// menu
						// clicking

						// close
						if (Menus[MENU_ABILITIES].ExitButtonBeingHovered_AndOpen(cursor)) {
							Menus[MENU_ABILITIES].is_open = false;
						} else {

							// start dragging ability menu
							if (Menus[MENU_ABILITIES].is_open
									&& Menus[MENU_ABILITIES].BeingHovered(cursor)

									&& ((cursor.y > Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 25)
											|| (cursor.x > Menus[MENU_ABILITIES].x + Menus[MENU_ABILITIES].width - 22)
											|| (cursor.x < Menus[MENU_ABILITIES].x + 22)
											|| (cursor.y > Menus[MENU_ABILITIES].y + 50 && cursor.y < Menus[MENU_ABILITIES].y + 65) || (cursor.y > Menus[MENU_ABILITIES].y + 15
											&& cursor.y < Menus[MENU_ABILITIES].y + 60 && cursor.x < Menus[MENU_ABILITIES].x + 300))) {
								menu_beingDragged = MENU_ABILITIES;
								menu_dragoffset_x = cursor.x - Menus[MENU_ABILITIES].x;
								menu_dragoffset_y = cursor.y - Menus[MENU_ABILITIES].y;
							}

							// click on tabs
							if (abilitymenu_pane < 11) {
								if (cursor.x > Menus[MENU_ABILITIES].x + 165 && cursor.x < Menus[MENU_ABILITIES].x + 300
										&& cursor.y > Menus[MENU_ABILITIES].y + 15 && cursor.y < Menus[MENU_ABILITIES].y + 50) {
									abilitymenu_pane = 11;
								}
								if (cursor.x > Menus[MENU_ABILITIES].x + 5 && cursor.x < Menus[MENU_ABILITIES].x + 165
										&& cursor.y > Menus[MENU_ABILITIES].y + 15 && cursor.y < Menus[MENU_ABILITIES].y + 50) {
									abilitymenu_pane = 0;
								}
							}
							if (abilitymenu_pane >= 11) {
								if (cursor.x > Menus[MENU_ABILITIES].x + 140 && cursor.x < Menus[MENU_ABILITIES].x + 300
										&& cursor.y > Menus[MENU_ABILITIES].y + 15 && cursor.y < Menus[MENU_ABILITIES].y + 50) {
									abilitymenu_pane = 11;
								}
								if (cursor.x > Menus[MENU_ABILITIES].x + 5 && cursor.x < Menus[MENU_ABILITIES].x + 140
										&& cursor.y > Menus[MENU_ABILITIES].y + 15 && cursor.y < Menus[MENU_ABILITIES].y + 50) {
									abilitymenu_pane = 0;
								}
							}

							if (abilitymenu_pane == 0) {

								for (int i = 1; i <= 10; i++) {

									if (cursor.x > (Menus[MENU_ABILITIES].x) + 26 + (((i + 1) % 2) * 175)
											&& cursor.x < (Menus[MENU_ABILITIES].x + 26 + 170) + (((i + 1) % 2) * 175)
											&& cursor.y > Menus[MENU_ABILITIES].y + 80 + (80 * ((i - 1) / 2))
											&& cursor.y < Menus[MENU_ABILITIES].y + 80 + 70 + (80 * ((i - 1) / 2))) {

										abilitymenu_pane = i;

										clickedGUIElement = true;
									}
								}

							}
							if (abilitymenu_pane >= 1 && abilitymenu_pane <= 10 && !clickedGUIElement) {

								int abilityslotclicked = -1;

								for (int i = 0; i < 10; i++) {

									if (cursor.x > Menus[MENU_ABILITIES].x + 30 + 185 * (i % 2) - 5
											&& cursor.x < Menus[MENU_ABILITIES].x + 30 + 185 * (i % 2) + 165 - 5
											&& cursor.y > Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) - 5
											&& cursor.y < Menus[MENU_ABILITIES].y + 120 + 70 * (i / 2) + 50 - 5) {

										abilityslotclicked = i;

									}
								}

								// int UnitID = myPNum;
								if (abilityslotclicked >= 0) {

									int ability = AbilitiesInEachSpec[abilitymenu_pane][abilityslotclicked];
									if (ability > 0) {
										clickedGUIElement = true;
									}

									if (Units[myPNum].stat[SharedData.SPEC_1] == abilitymenu_pane
											|| Units[myPNum].stat[SharedData.SPEC_2] == abilitymenu_pane) {

										if (Units[myPNum].LearnedSpells[ability] == true) {

											if (!BattlePhaseEngaged) {
												abilityicon_beingDragged = ability;
											}
										} else {

											if (Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL] > 0 && ability > 0) {

												Units[myPNum].LearnedSpells[ability] = true;
												Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL]--;

												for (int i = 0; i < 9; i++) {
													if (Units[myPNum].active_spells[i] == 0) {
														Units[myPNum].active_spells[i] = ability;
														break;
													}
												}

											}

										}

									} else {// if this is not one of their
											// specs...
										ShowWarningDialog("Must Specialize First!");
									}

								}

								// back
								if (cursor.x > (Menus[MENU_ABILITIES].x + 20) && cursor.x < (Menus[MENU_ABILITIES].x + 20 + 140)
										&& cursor.y > (Menus[MENU_ABILITIES].y + 60) && cursor.y < (Menus[MENU_ABILITIES].y + 60 + 44)) {
									abilitymenu_pane = 0;
								}

								// specialize
								if (cursor.WithinRect(Menus[MENU_ABILITIES].x + 120, Menus[MENU_ABILITIES].y + Menus[MENU_ABILITIES].height - 70,
										140, 44)) {

									clickedGUIElement = true;
									if (Units[myPNum].stat[SharedData.SPEC_1] != abilitymenu_pane
											&& Units[myPNum].stat[SharedData.SPEC_2] != abilitymenu_pane
											&& (Units[myPNum].stat[SharedData.SPEC_1] == 0 || Units[myPNum].stat[SharedData.SPEC_2] == 0)) {
										openDialogMenu(DIALOG_CHOOSESPEC);
										DialogMenus[DIALOG_CHOOSESPEC].mode = abilitymenu_pane;

									}

								}

							}

							/*
							 * if (abilitymenu_pane == 11 && !clickedGUIElement)
							 * {
							 * 
							 * int abilityslotclicked = -1;
							 * 
							 * for (int i = 0; i < 6 * 5; i++) { int j = i +
							 * Menus[MENU_ABILITIES].scrolloffset * 6;
							 * 
							 * if (cursor.x > Menus[MENU_ABILITIES].x + 40 + 50
							 * * (i % 6) && cursor.x < Menus[MENU_ABILITIES].x +
							 * 40 + 50 * (i % 6) + 40 && cursor.y >
							 * Menus[MENU_ABILITIES].y + 80 + 50 * (i / 6) &&
							 * cursor.y < Menus[MENU_ABILITIES].y + 80 + 50 * (i
							 * / 6) + 40) { abilityslotclicked = j; } }
							 * 
							 * 
							 * if (abilityslotclicked >= 0) { int ability =
							 * weaponskillsorder[abilityslotclicked]; if
							 * (num_of_weaponskills_available >
							 * abilityslotclicked) {
							 * 
							 * abilityicon_beingDragged = ability; } } }
							 */

						}

					}

					if (!clickedGUIElement) {
						// end last menu
						for (int i = 0; i < DialogMenus.length; i++) {
							if (DialogMenus[i].is_open) {
								if (cursor.x > DialogMenus[i].x + DialogMenus[i].width / 2 - 100 / 2
										&& cursor.x < DialogMenus[i].x + DialogMenus[i].width / 2 - 100 / 2 + 100 && cursor.y > DialogMenus[i].y + 33
										&& cursor.y < DialogMenus[i].y + 33 + 20) {
									executeDialogMenu(i);
									break;
								}
								if (!DialogMenus[i].BeingHovered(cursor)) {
									if (i == DIALOG_CHOOSESPEC) {
										closeDialogMenu(i);
									}
								}
							}
						}
					}

				}// end check for pnum!=1

			}// end check for mouse not already being down

		}// end mousebuttondown(0)

		/** MOUSE RELEASE */
		if ((!Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == true) || (!Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == true)) {
			// int mouse_rel_x = Mouse.getX();
			// int mouse_rel_y = SCREEN_HEIGHT - Mouse.getY();

			// if mouse dragged on minimap...
			if (cursor.x > Menus[7].x && cursor.x < Menus[7].x + Menus[7].width && cursor.y > Menus[7].y && cursor.y < Menus[7].y + Menus[7].height
					&& Menus[MENU_MINIMAP].is_open) {

				int minimap_x = (cursor.x - Menus[7].x) / 2;
				int minimap_y = (cursor.y - Menus[7].y) / 2;

				cam.x = minimap_x;
				cam.y = minimap_y;

			}

			counter_mousehelddown = -1;// reset and freeze the counter
			int slot_of_ability_duplicate = -1;

			menu_beingDragged = -1;
			menu_dragoffset_x = 0;
			menu_dragoffset_y = 0;
			map_beingDragged = false;

			// if(secondaryabilitybar.mode==2){
			for (int i = 0; i < 10; i++) {// can only put spells in slots 1-9
											// !!!

				if (!BattlePhaseEngaged && abilityicon_beingDragged >= 0 && cursor.x > secondaryabilitybar.x + (i * 50)
						&& cursor.x < secondaryabilitybar.x + 50 + (i * 50) && cursor.y > secondaryabilitybar.y
						&& cursor.y < secondaryabilitybar.y + 50) {

					for (int k = 0; k < 10; k++) {
						if (Units[myPNum].active_spells[k] == abilityicon_beingDragged) {
							slot_of_ability_duplicate = k;
						}
					}
					if (slot_of_ability_duplicate != -1) {
						Units[myPNum].active_spells[slot_of_ability_duplicate] = 0;
					}

					Units[myPNum].active_spells[i] = abilityicon_beingDragged;

				}

			}
			// }

			// walk by releasing the mouse
			if (allbigmenus_notbeinghovered()) {

				double dist = Math.sqrt(Math.abs(Math.pow(cursor.currentmousetile_x - cam.tileselect_x, 2))
						+ Math.abs(Math.pow(cursor.currentmousetile_y - cam.tileselect_y, 2)));

				if (dist == 0) {
					targetting_currentability = 0;
				}

				if (dist < 9 && targetting_currentability == SPECIALABIL_WALK && dist > 0) {

					if (cursor.WithinMapBounds(SharedData.MAP_SIZE) && cursor.y < secondaryabilitybar.y) {

						TryToWalkTowardsMouseCursor(targetting_currentcaster);

					}
				}
			}

			/*
			 * 
			 * 
			 * if (Hero_changing_facing && queued_hero_facing > -1) {
			 * Hero_SpecialAbility_ChangeFacing(queued_hero_facing); }
			 * 
			 * 
			 * secondaryabilitybar.width = 50*10; secondaryabilitybar.height =
			 * 50;
			 * secondaryabilitybar.x=((SCREEN_WIDTH-200)/2)-(secondaryabilitybar
			 * .width/2); secondaryabilitybar.y=SCREEN_HEIGHT-100;
			 */

			// dropping any item into the loot window
			if (Menus[MENU_LOOTING].is_open == true && bagSlotOf_itemBeingDragged > -1 && Units[myPNum].itemInSlot[bagSlotOf_itemBeingDragged] != 0
					&& cursor.x > Menus[MENU_LOOTING].x + 3 && cursor.x < Menus[MENU_LOOTING].x + 3 + 120 && cursor.y > Menus[MENU_LOOTING].y + 12
					&& cursor.y < Menus[MENU_LOOTING].y + 12 + 80) {

				int draggeditem = Units[myPNum].itemInSlot[bagSlotOf_itemBeingDragged];

				Client_TellServer_EditTileItemIDQuantity(itemcontainer_currently_looting_x, itemcontainer_currently_looting_y, draggeditem, +1);

			}

			// check to see if it is released on a body part (to equip) and
			// reset it to zero!

			if (Menus[MENU_INV].is_open && !BattlePhaseEngaged) {

				int UnitID = myPNum;

				if (bagSlotOf_itemBeingDragged > -1) {
					if (Units[myPNum].itemInSlot[bagSlotOf_itemBeingDragged] != 0) {

						// equip an item
						if (cursor.x > Menus[MENU_INV].x + 260 && cursor.x < Menus[MENU_INV].x + 380 && cursor.y > Menus[MENU_INV].y + 278
								&& cursor.y < Menus[MENU_INV].y + 478) {

							TryToEquipItem(bagSlotOf_itemBeingDragged, myPNum, true);

							bagSlotOf_itemBeingDragged = -1;
						}// end check for mouse location on the body
							// representation

						// release an item into a bagslot from anywhere
						if (cursor.x > Menus[MENU_INV].x + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318
								&& cursor.y < Menus[MENU_INV].y + 318 + 160) {

							int bagslotReleasedIn = (cursor.x - 18 - Menus[MENU_INV].x) / 40 + 6 * ((cursor.y - 318 - Menus[MENU_INV].y) / 40);

							// int draggeditem =
							// Units[myPNum].itemInSlot[bagSlotOf_itemBeingDragged];

							Client_TellServer_SwapUnitBagSlotItems(Units[UnitID].x, Units[UnitID].y, bagSlotOf_itemBeingDragged, bagslotReleasedIn);

							// remove item from itemcontainer if needed
							// this must be broadcast I think

						}

						// release an item onto the ground, drop it
						if (allbigmenus_notbeinghovered()) {

							if (map[SharedData.SMALLOBJECTS_LAYER][Units[UnitID].y][Units[UnitID].x] == 0) {

								int item_ID = Units[UnitID].itemInSlot[bagSlotOf_itemBeingDragged];

								Client_TellServer_SpawnObject(SharedData.SMALLOBJECTS_LAYER, SharedData.ASHES, 0, Units[UnitID].x, Units[UnitID].y);

								Client_TellServer_EditTileItemIDQuantity(Units[UnitID].x, Units[UnitID].y, item_ID, +1);

								Client_TellServer_EditUnitItemSlotQuantity(Units[UnitID].x, Units[UnitID].y, bagSlotOf_itemBeingDragged, -1);

							}

						}

						// sell an item
						if (Menus[MENU_ITEMVENDOR].is_open
								&& cursor.WithinRect(Menus[MENU_ITEMVENDOR].x, Menus[MENU_ITEMVENDOR].y, Menus[MENU_ITEMVENDOR].width,
										Menus[MENU_ITEMVENDOR].height)) {

							int item_type = Units[UnitID].itemInSlot[bagSlotOf_itemBeingDragged];
							int current_quantity = Units[UnitID].itemQuantity[bagSlotOf_itemBeingDragged];
							int currentgold = Units[UnitID].stat[SharedData.GOLD_CARRIED];
							int value = myItemDB[item_type].value;

							Client_TellServer_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, item_type, current_quantity - 1);

							Client_EditStats(SharedData.GOLD_CARRIED, currentgold + value / 3, myPNum);

							playSound(mySoundPaths[SOUND_COIN]);

						}

					}
				}

				// buy an item

				if (Menus[MENU_ITEMVENDOR].is_open && ItemVendor_itemBeingDragged > -1 && cursor.x > Menus[MENU_INV].x + 18
						&& cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318 && cursor.y < Menus[MENU_INV].y + 318 + 160) {

					int currentgold = Units[UnitID].stat[SharedData.GOLD_CARRIED];
					int value = myItemDB[ItemVendor_itemBeingDragged].value;

					if (currentgold >= value) {

						Client_TellServer_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, ItemVendor_itemBeingDragged, +1);

						Client_EditStats(SharedData.GOLD_CARRIED, currentgold - value, myPNum);
					}

					// remove item from itemcontainer if needed
					// this must be broadcast I think

				}

			}

			ItemVendor_itemBeingDragged = -1;
			abilityicon_beingDragged = -1;
			bagSlotOf_itemBeingDragged = -1;

			// add_weaponskills_to_bar();
		}// end mouse release code

	}// end poll for inputs

	public void pollInput_DM() {

		boolean menu_just_opened = false;

		displayedItemHovertooltip = 0;
		displayedAbilityHovertooltip = 0;
		displayedConditionHovertooltip = 0;
		displayedGenericHovertooltip = null;
		displayedMiniHovertooltip = null;

		/** MOUSE HOVERING */

		int x = (cursor.x / TILE_SIZE) + (cam.x - (SCREEN_X_TILES / 2));
		int y = (cursor.y / TILE_SIZE) + (cam.y - (SCREEN_Y_TILES / 2));

		if (x > -1 && x < SharedData.MAP_SIZE && y > -1 && y < SharedData.MAP_SIZE) {

			cursor.currentmousetile_x = x;
			cursor.currentmousetile_y = y;

			if (allbigmenus_notbeinghovered()) {
				// update the GUI such as notes things

				if (targetting_currentability == 0) {

					String s = null;

					if (myTypeDB[map[SharedData.SMALLOBJECTS_LAYER][y][x]].description != null) {
						s = myTypeDB[map[SharedData.SMALLOBJECTS_LAYER][y][x]].description;
					}
					if (myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][y][x]].description != null) {
						s = myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][y][x]].description;
					}

					if (s != null) {

						// displayedGenericHovertooltip = s;

					}

				}// end abilitybeingtargetted check

				int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];
				if (cam.TileSelection_Within_Bounds()) {

					PollConditionbarHoverTooltips(UnitID);

					PollAbilitybarHoverTooltip(UnitID);

				}

				if (UnitID > 0) {

					if (cursor.WithinRect(GUIHealthBar.x, GUIHealthBar.y, GUIHealthBar.width, GUIHealthBar.height)) {
						int health = Units[UnitID].stat[SharedData.HEALTH];
						int maxhealth = get_unit_maxhealth(UnitID);

						displayedMiniHovertooltip = "Health: " + health + "/" + maxhealth;
					}
					if (cursor.WithinRect(GUIEnergyBar.x, GUIEnergyBar.y, GUIEnergyBar.width, GUIEnergyBar.height)) {
						int energy = Units[UnitID].stat[SharedData.ENERGY];
						int maxenergy = get_unit_maxenergy(UnitID);

						displayedMiniHovertooltip = "Energy: " + energy + "/" + maxenergy;
					}

					for (int i = 0; i < GodButtons.length; i++) {
						if (GodButtons[i].BeingHovered(cursor)) {
							displayedMiniHovertooltip = GodButtonText[i];
						}
					}

				}

			}// endmenucheck

			// invmenu stuff

			if (Menus[MENU_INV].is_open == true) {

				pollInvMenuHovering();

			}// end the else

			if (Menus[MENU_ASSETTABLE].is_open == true) {// clicking the
															// item table
				int number_of_slots = (int) Math.floor((Menus[MENU_ASSETTABLE].height - 40) / 25);

				if (!(cursor.x > Menus[MENU_ASSETTABLE].x && cursor.x < Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width
						&& cursor.y > Menus[MENU_ASSETTABLE].y - 30 && cursor.y < Menus[MENU_ASSETTABLE].y)
						&& assettablemenu_currentscreen > -1) {

					for (int i = 0; i < number_of_slots; i++) {

						if (cursor.x > Menus[MENU_ASSETTABLE].x + 20 && cursor.x < Menus[MENU_ASSETTABLE].x + 20 + Menus[MENU_ASSETTABLE].width - 40
								&& cursor.y > Menus[MENU_ASSETTABLE].y + 35 + 25 * i && cursor.y < Menus[MENU_ASSETTABLE].y + 35 + 25 * i + 20) {

							int itemnum = assets_in_groups[assettablemenu_currentasset][assettablemenu_currentscreen][i
									+ Menus[MENU_ASSETTABLE].scrolloffset];
							if (itemnum > 0 && assettablemenu_currentasset == ASSETTABLE_ITEMS) {
								displayedItemHovertooltip = itemnum;
							}
						}

					}

				}
			}

			// DMLighting(x, y);
		}

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			cursor.x = cursor.x;
			cursor.y = cursor.y;

			if (cursor.x - menu_dragoffset_x > -200 && cursor.x - menu_dragoffset_x < SCREEN_WIDTH - 250 && cursor.y - menu_dragoffset_y > 10
					&& cursor.y - menu_dragoffset_y < SCREEN_HEIGHT - 50) {

				for (int i = 0; i < draggablemenus.length; i++) {
					if (draggablemenus[i] == menu_beingDragged) {
						debug(draggablemenus[i]);
						Menus[draggablemenus[i]].x = cursor.x - menu_dragoffset_x;
						Menus[draggablemenus[i]].y = cursor.y - menu_dragoffset_y;
					}

				}

			}// end offthescreen check

			if (menu_beingDragged == MENU_MINIMAP && Menus[MENU_MINIMAP].is_open) {

				int minimap_x = (cursor.x - Menus[7].x) / 2;
				int minimap_y = (cursor.y - Menus[7].y) / 2;

				if (minimap_x > 0 && minimap_x < SharedData.MAP_SIZE && minimap_y > 0 && minimap_y < SharedData.MAP_SIZE) {

					cam.x = minimap_x;
					cam.y = minimap_y;
				}

			}

			if (map_beingDragged) {
				int offset_x = (map_dragoffset_x - cursor.x) / 40;
				int offset_y = (map_dragoffset_y - cursor.y) / 40;
				cam.x = map_drag_original_x + offset_x;
				cam.y = map_drag_original_y + offset_y;
			}

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

				boolean clickedGUIElement = false;

				boolean justActivatedAbility = false;

				counter_mousehelddown = 0;// begin the counter
				if (Mouse.isButtonDown(1)) {
					targetting_currentability = 0;
				}// right clicking while targetting an ability will cancel it

				if (allbigmenus_notbeinghovered() && !Menus[MENU_LOOTING].BeingHovered_AndOpen(cursor)) {

					int UnitSelectedID = UnitMap[cam.tileselect_x][cam.tileselect_y];

					// if mouse clicked on minimap...start dragging
					if (cursor.x > Menus[7].x && cursor.x < Menus[7].x + Menus[7].width && cursor.y > Menus[7].y
							&& cursor.y < Menus[7].y + Menus[7].height && Menus[MENU_MINIMAP].is_open) {

						menu_beingDragged = MENU_MINIMAP;
						clickedGUIElement = true;
					}

					if (cursor.x > SCREEN_WIDTH - 90 && cursor.x < SCREEN_WIDTH - 90 + 60 && cursor.y > 232 && cursor.y < 232 + 60) {
						current_D20_roll = (int) (Math.random() * 20) + 1;
						playSound(mySoundPaths[SOUND_DICEROLL]);
						clickedGUIElement = true;
						serverbroadcast_printtostorylog(Players[myPNum].name + " has rolled a " + current_D20_roll + ".");

					}

					boolean clicked = PollClickingOnAbilitybars(UnitSelectedID);
					if (clicked == true) {
						clickedGUIElement = true;
					}

					clicked = PollClickingOnBattleQueue(UnitSelectedID);
					if (clicked == true) {
						clickedGUIElement = true;
					}

					// close loot menu
					if (Menus[MENU_LOOTING].is_open && allbigmenus_notbeinghovered() && !Menus[MENU_LOOTING].BeingHovered(cursor)) {
						Menus[MENU_LOOTING].is_open = false;
					}

					// right click on selected unit to open inv menu
					if (UnitSelectedID > 0) {
						if (Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == false) {
							if (cursor.currentmousetile_x == cam.tileselect_x && cursor.currentmousetile_y == cam.tileselect_y) {
								openGameMenu(MENU_INV);
							}
						}
					}

					// asset table button
					if (cursor.x < 50 && cursor.y > Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height / 2 - 20
							&& cursor.y < Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height / 2 + 20) {
						clickedGUIElement = true;
						openGameMenu(MENU_ASSETTABLE);
						menu_just_opened = true;
					}

					// phase button
					if (PhaseButton.BeingHovered(cursor) && num_clientsconnected > 0) {
						clickedGUIElement = true;
						server_changephase();
					}

					if (UnitSelectedID > 0) {
						// open inv menu
						if (button_editinv.BeingHovered(cursor)) {
							clickedGUIElement = true;
							// open inv menu and item table
							openGameMenu(MENU_INV);
							menu_just_opened = true;
						}

						// move unit button
						if (button_moveunit.BeingHovered(cursor)) {
							clickedGUIElement = true;
							targetting_currentability = SPECIALABIL_DMMOVE;
							targetting_currentcaster = UnitSelectedID;// necessary??
							menu_just_opened = true;
						}

						// edit stats button
						if (button_editstats.BeingHovered(cursor)) {
							clickedGUIElement = true;
							openGameMenu(MENU_EDITSTATS);
							menu_just_opened = true;
						}

						/*
						 * // profileimage and stat icons if
						 * ((UnitMap[cam.tileselect_x][cam.tileselect_y] > 0) &&
						 * (cursor.x > 20 && cursor.x < 20 + 100 + 40 &&
						 * cursor.y > SCREEN_HEIGHT - 125 && cursor.y <
						 * SCREEN_HEIGHT - 125 + 100)) { clickedGUIElement =
						 * true; }
						 */

					} else {

						int minibuttonclicked = -1;
						for (int i = 0; i < 2; i++) {
							if (cursor.x > minimenubar.x + 20 * i && cursor.x < minimenubar.x + 20 + 20 * i && cursor.y > minimenubar.y
									&& cursor.y < minimenubar.y + minimenubar.height) {
								minibuttonclicked = i;
								clickedGUIElement = true;
							}

						}

						if (minibuttonclicked == 0) {
							openGameMenu(MENU_ESC);
						}
						if (minibuttonclicked == 1) {
							openGameMenu(MENU_QUESTTRACKER);
						}

					}

					// object menus

					int[] tempint = getDMOptionObject();

					int cursortype = tempint[0];
					int rows = tempint[1];

					if (rows > 0) {

						int proj_screenX = cam.tileselect_x - cam.x + (SCREEN_X_TILES / 2);
						int proj_screenY = cam.tileselect_y - cam.y + (SCREEN_Y_TILES / 2);

						for (int i = 0; i < rows; i++) {
							if (cursor.x > proj_screenX * TILE_SIZE && cursor.x < proj_screenX * TILE_SIZE + 120
									&& cursor.y > proj_screenY * TILE_SIZE - ((i + 1) * 22) - 20
									&& cursor.y <= proj_screenY * TILE_SIZE - (i * 22) - 20) {

								// Fill_Rect(proj_screenX*TILE_SIZE,proj_screenY*TILE_SIZE
								// - ((i+1) * 22) - 20,120,22);

								activateDMObjectOption(i);
								clickedGUIElement = true;

							}
						}

					}

					if (targetting_currentability > 0 && clickedGUIElement == false) {

						int maptileclicked_x = (cursor.x / TILE_SIZE) + cam.x - ((SCREEN_X_TILES / 2));
						int maptileclicked_y = (cursor.y / TILE_SIZE) + cam.y - ((SCREEN_Y_TILES / 2));

						if (maptileclicked_x > -1 && maptileclicked_x < SharedData.MAP_SIZE && maptileclicked_y > -1
								&& maptileclicked_y < SharedData.MAP_SIZE) {
							justActivatedAbility = true;

							Anyone_Broadcast_CastAbility(targetting_currentability, Units[targetting_currentcaster].x,
									Units[targetting_currentcaster].y, maptileclicked_x, maptileclicked_y);

						}
					}

					if (y >= 0 && y < SharedData.MAP_SIZE && x >= 0 && x < SharedData.MAP_SIZE) {// do

						if (!clickedGUIElement && !justActivatedAbility && allbigmenus_notbeinghovered()) {

							// begin pulling the map around if right clicked
							if (Mouse.isButtonDown(1)) {
								map_beingDragged = true;
								map_dragoffset_x = cursor.x;
								map_dragoffset_y = cursor.y;
								map_drag_original_x = cam.x;
								map_drag_original_y = cam.y;
								clickedGUIElement = true;
							} else {

								// select a unit
								int UnitClickedID = UnitMap[x][y];
								if (UnitClickedID > 0) {
									InvMenu_BonusStats_MustBeRefreshed = true;

									if (cam.tileselect_x == x && cam.tileselect_y == y
											&& Units[UnitClickedID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
										Anyone_ClickOnAbility(SPECIALABIL_WALK, UnitClickedID, (secondaryabilitybar.mode == 1));

									} else {
										cam.tileselect_x = x;
										cam.tileselect_y = y;
									}
								} else {
									cam.tileselect_x = x;
									cam.tileselect_y = y;
								}

								boolean clicked_on_appropriate_chest = false;

								for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
									if (myTypeDB[map[z][cursor.currentmousetile_y][cursor.currentmousetile_x]].Can_Contain_Items

									) {
										clicked_on_appropriate_chest = true;
									}
								}

								if (clicked_on_appropriate_chest) {

									clickedGUIElement = true;
									openGameMenu(MENU_LOOTING);
									itemcontainer_currently_looting_x = x;
									itemcontainer_currently_looting_y = y;

								}

							}

						}// end abilitycheck
					}// end playablearea check

				}// endmenucheck

				if (Menus[MENU_ESC].is_open == true) {// ESCmenu

					handleinputs_ESCMenu();

				}

				if (Menus[MENU_LOOTING].is_open) {

					// start dragging the looting window
					if (Menus[MENU_LOOTING].is_open == true) {
						if (cursor.x > (Menus[MENU_LOOTING].x) && cursor.x < (Menus[MENU_LOOTING].x + Menus[MENU_LOOTING].width)
								&& cursor.y > Menus[MENU_LOOTING].y - 5 && cursor.y < Menus[MENU_LOOTING].y + 15) {
							menu_beingDragged = MENU_LOOTING;
							menu_dragoffset_x = cursor.x - Menus[MENU_LOOTING].x;
							menu_dragoffset_y = cursor.y - Menus[MENU_LOOTING].y;
						}
					}

					if (Menus[MENU_LOOTING].BeingHovered(cursor)) {

						if (Menus[MENU_LOOTING].ExitButtonBeingHovered_AndOpen(cursor)) {
							Menus[MENU_LOOTING].is_open = false;
						} else if (cursor.x > Menus[MENU_LOOTING].x + 3 && cursor.x < Menus[MENU_LOOTING].x + 3 + 120
								&& cursor.y > Menus[MENU_LOOTING].y + 12 && cursor.y < Menus[MENU_LOOTING].y + 12 + 80) {

							int slot = (cursor.x - 3 - Menus[MENU_LOOTING].x) / 40 + 3 * ((cursor.y - 12 - Menus[MENU_LOOTING].y) / 40);
							int item_id = ItemContainers[itemcontainer_currently_looting_x][itemcontainer_currently_looting_y].itemInSlot[slot];

							/*
							 * int mapx = cursor.currentmousetile_x; int mapy =
							 * cursor.currentmousetile_y;
							 * if(coordinatesWithinMapBounds(mapx,mapy)){ int
							 * UnitID = UnitMap[mapx][mapy]; if(UnitID > 0){
							 * 
							 * int first_open_bagslot = -1; first_open_bagslot =
							 * getFirstFreeBagSlot(UnitID); if
							 * (first_open_bagslot > -1) {
							 * 
							 * 
							 * Server_Broadcast_EditUnitItemIDQuantity(
							 * Units[UnitID].x, Units[UnitID].y, item_id, +
							 * 1,false,0);
							 * 
							 * Server_Broadcast_EditTileItemIDQuantity(
							 * itemcontainer_currently_looting_x,
							 * itemcontainer_currently_looting_y, item_id, -
							 * 1,false,0);
							 * 
							 * } } }
							 */

							bagSlotOf_itemBeingDragged = 100 + slot;

						}

					}// end looting menu being hovered

				}

				if (Menus[MENU_INV].is_open == true) {

					if (Menus[MENU_INV].ExitButtonBeingHovered_AndOpen(cursor)) {
						Menus[MENU_INV].is_open = false;
					} else {

						if (cursor.x > (Menus[MENU_INV].x) && cursor.x < (Menus[MENU_INV].x + Menus[MENU_INV].width) && cursor.y > Menus[MENU_INV].y
								&& cursor.y < Menus[MENU_INV].y + 30) {
							menu_beingDragged = MENU_INV;
							menu_dragoffset_x = cursor.x - Menus[MENU_INV].x;
							menu_dragoffset_y = cursor.y - Menus[MENU_INV].y;
						}
					}

					int INV_Unit_ID = UnitMap[cam.tileselect_x][cam.tileselect_y];

					if (INV_Unit_ID < 10) {// can only remove hero inv items

						// click on the minimenu in a inventory window
						/*
						 * if (inventoryslot_currentlyselected >= 0 &&
						 * inventoryslot_currentlyselected < 50) {
						 * 
						 * int rows = 2; int slot =
						 * inventoryslot_currentlyselected - 0; int item_id =
						 * Units[INV_Unit_ID].itemInSlot[slot]; int minimenu_x =
						 * Menus[MENU_INV].x + 18 + 40 (slot % 6) + 10; int
						 * minimenu_y = Menus[MENU_INV].y + 318 + 40 (slot / 6)
						 * - rows * 22; int minimenu_width = 120; int
						 * minimenu_height = rows * 22;
						 * 
						 * if (cursor.x > minimenu_x && cursor.x < minimenu_x +
						 * minimenu_width && cursor.y > minimenu_y && cursor.y <
						 * minimenu_y + minimenu_height) {
						 * 
						 * int option = (cursor.y - minimenu_y) / 22;
						 * 
						 * if (option == 0) {
						 * 
						 * Server_Broadcast_EditUnitItemSlotQuantity(
						 * Units[INV_Unit_ID].x, Units[INV_Unit_ID].y, slot,
						 * -99999 , 0); } if (option == 1) {
						 * 
						 * Server_Broadcast_EditUnitItemSlotQuantity(
						 * Units[INV_Unit_ID].x, Units[INV_Unit_ID].y, slot, - 1
						 * , 0); } inventoryslot_currentlyselected = -1;
						 * menu_just_opened = true; } }
						 */

						// Clicking on items from inv window
						/*
						 * if (!menu_just_opened && cursor.x > Menus[MENU_INV].x
						 * + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 &&
						 * cursor.y > Menus[MENU_INV].y + 318 && cursor.y <
						 * Menus[MENU_INV].y + 318 + 160) {
						 * 
						 * int slot = (cursor.x - 18 - Menus[MENU_INV].x) / 40 +
						 * 6 ((cursor.y - 318 - Menus[MENU_INV].y) / 40);
						 * 
						 * //
						 * com(Units[UnitMap[cam.tileselect_x][cam.tileselect_y
						 * ]].itemInSlot[slot-0]); if (Mouse.isButtonDown(1) &&
						 * Units
						 * [UnitMap[cam.tileselect_x][cam.tileselect_y]].itemInSlot
						 * [slot - 0] > 0) {
						 * 
						 * inventoryslot_currentlyselected = slot;
						 * menu_just_opened = true; }
						 * 
						 * }
						 */

					} else {// host has full control of NPC items

						// Start dragging an item out of bag
						if (cursor.x > Menus[MENU_INV].x + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318
								&& cursor.y < Menus[MENU_INV].y + 318 + 160) {
							int slot = (cursor.x - 18 - Menus[MENU_INV].x) / 40 + 6 * ((cursor.y - 318 - Menus[MENU_INV].y) / 40);
							bagSlotOf_itemBeingDragged = slot; // start
																// dragging
																// an
																// item
																// from
																// a
																// bagslot
						}

						// start dragging item off of being equipped
						for (int n = 0; n < 9; n++) {

							if (cursor.x > Menus[MENU_INV].x + invmenu_equipslots_x[n] && cursor.x < Menus[MENU_INV].x + invmenu_equipslots_x[n] + 40
									&& cursor.y > Menus[MENU_INV].y + invmenu_equipslots_y[n]
									&& cursor.y < Menus[MENU_INV].y + invmenu_equipslots_y[n] + 40) {
								int slot = 50 + n;
								bagSlotOf_itemBeingDragged = slot;

							}

						}

					}

				}// end click in inv menu

				if (Menus[MENU_QUESTTRACKER].is_open == true) {

					if (Menus[MENU_QUESTTRACKER].ExitButtonBeingHovered_AndOpen(cursor)) {
						Menus[MENU_QUESTTRACKER].is_open = false;
					} else {

						if (cursor.x > (Menus[MENU_QUESTTRACKER].x) && cursor.x < (Menus[MENU_QUESTTRACKER].x + Menus[MENU_QUESTTRACKER].width)
								&& cursor.y > Menus[MENU_QUESTTRACKER].y && cursor.y < Menus[MENU_QUESTTRACKER].y + 30) {
							menu_beingDragged = MENU_QUESTTRACKER;
							menu_dragoffset_x = cursor.x - Menus[MENU_QUESTTRACKER].x;
							menu_dragoffset_y = cursor.y - Menus[MENU_QUESTTRACKER].y;
						}
					}

				}

				if (Menus[MENU_EDITSTATS].is_open == true) {// clicking on the
															// editstats menu

					if (Menus[MENU_EDITSTATS].ExitButtonBeingHovered_AndOpen(cursor)) {
						Menus[MENU_EDITSTATS].is_open = false;
					} else {
						if (cursor.x > (Menus[MENU_EDITSTATS].x) && cursor.x < (Menus[MENU_EDITSTATS].x + Menus[MENU_EDITSTATS].width)
								&& cursor.y > Menus[MENU_EDITSTATS].y && cursor.y < Menus[MENU_EDITSTATS].y + 30) {
							menu_beingDragged = MENU_EDITSTATS;
							menu_dragoffset_x = cursor.x - Menus[MENU_EDITSTATS].x;
							menu_dragoffset_y = cursor.y - Menus[MENU_EDITSTATS].y;

						}

						// if selecting a new stat..
						if (cursor.x > (Menus[MENU_EDITSTATS].x + 20) && cursor.x < (Menus[MENU_EDITSTATS].x + 20) + 150
								&& cursor.y > Menus[MENU_EDITSTATS].y + 45
								&& cursor.y < Menus[MENU_EDITSTATS].y + 45 + 22 * EditStatsMenu_Stats.length) {

							editstatsmenu_selectedstat_index = (cursor.y - (Menus[MENU_EDITSTATS].y + 45)) / 22;

							editstatsmenu_increment = 0;

						}
						int editstatsmenu_selectedstat = EditStatsMenu_Stats[editstatsmenu_selectedstat_index];

						int increments[] = {10, 5, 1, -1, -5, -10};

						int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];
						if (UnitID > 0) {
							// increment buttons

							editstatsmenu_increment = 0;

							for (int i = 0; i < 6; i++) {

								if (cursor.x > Menus[MENU_EDITSTATS].x + 250 && cursor.x < Menus[MENU_EDITSTATS].x + 250 + 120
										&& cursor.y > Menus[MENU_EDITSTATS].y + 120 + (40 * i)
										&& cursor.y < Menus[MENU_EDITSTATS].y + 120 + (40 * i) + 30) {

									editstatsmenu_increment = increments[i];

								}

							}

							if (editstatsmenu_increment != 0) {
								Server_Broadcast_EditStats(editstatsmenu_selectedstat, Units[UnitID].stat[editstatsmenu_selectedstat]
										+ editstatsmenu_increment, UnitID);
							}

							// done button
							if (cursor.x > Menus[MENU_EDITSTATS].x + 140 && cursor.x < Menus[MENU_EDITSTATS].x + 140 + 120
									&& cursor.y > Menus[MENU_EDITSTATS].y + 440 && cursor.y < Menus[MENU_EDITSTATS].y + 440 + 40) {
								Menus[MENU_EDITSTATS].is_open = false;

							}

						}

					}
				}

				if (Menus[MENU_ASSETTABLE].is_open == true) {// clicking the
																// item table
					int number_of_slots = (int) Math.floor((Menus[MENU_ASSETTABLE].height - 40) / 25);
					if (!menu_just_opened) {

						if (Menus[MENU_ASSETTABLE].ExitButtonBeingHovered_AndOpen(cursor)) {
							Menus[MENU_ASSETTABLE].is_open = false;
						} else {

							if (cursor.x > Menus[MENU_ASSETTABLE].x && cursor.x < Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width
									&& cursor.y > Menus[MENU_ASSETTABLE].y - 30 && cursor.y < Menus[MENU_ASSETTABLE].y) {

								if (cursor.x < 50) {
									assettablemenu_currentasset = ASSETTABLE_UNITS;
									assettablemenu_currentscreen = -1;
									Menus[MENU_ASSETTABLE].scrolloffset = 0;
								}
								if (cursor.x > 50 && cursor.x < 100) {
									assettablemenu_currentasset = ASSETTABLE_ITEMS;
									assettablemenu_currentscreen = -1;
									Menus[MENU_ASSETTABLE].scrolloffset = 0;
								}
								if (cursor.x > 100) {
									assettablemenu_currentasset = ASSETTABLE_EFFECTS;
									assettablemenu_currentscreen = -1;
									Menus[MENU_ASSETTABLE].scrolloffset = 0;
								}
							} else

							if (assettablemenu_currentscreen == -1) {// select a
																		// subcategory
								for (int i = 0; i < number_of_slots; i++) {

									if (cursor.x > Menus[MENU_ASSETTABLE].x + 20
											&& cursor.x < Menus[MENU_ASSETTABLE].x + 20 + Menus[MENU_ASSETTABLE].width - 40
											&& cursor.y > Menus[MENU_ASSETTABLE].y + 35 + 25 * i
											&& cursor.y < Menus[MENU_ASSETTABLE].y + 35 + 25 * i + 20) {

										int j = i + Menus[MENU_ASSETTABLE].scrolloffset;
										if (SharedData.ComboStrings[AssetTableComboStringIndices[assettablemenu_currentasset]].length > j) {
											assettablemenu_currentscreen = j;
											Menus[MENU_ASSETTABLE].scrolloffset = 0;
										}
									}

								}
							} else {
								// click back button
								if (cursor.x > Menus[MENU_ASSETTABLE].x + 10 && cursor.x < Menus[MENU_ASSETTABLE].x + 10 + 30
										&& cursor.y > Menus[MENU_ASSETTABLE].y + 5 && cursor.y < Menus[MENU_ASSETTABLE].y + 5 + 20) {

									assettablemenu_currentscreen = -1;
									Menus[MENU_ASSETTABLE].scrolloffset = 0;
								}

								for (int i = 0; i < number_of_slots; i++) { // start
																			// dragging
																			// an
																			// item
																			// from
																			// the
																			// itemtable
																			// !

									if (cursor.x > Menus[MENU_ASSETTABLE].x + 20
											&& cursor.x < Menus[MENU_ASSETTABLE].x + 20 + Menus[MENU_ASSETTABLE].width - 40
											&& cursor.y > Menus[MENU_ASSETTABLE].y + 35 + 25 * i
											&& cursor.y < Menus[MENU_ASSETTABLE].y + 35 + 25 * i + 20) {

										int itemnum = assets_in_groups[assettablemenu_currentasset][assettablemenu_currentscreen][i
												+ Menus[MENU_ASSETTABLE].scrolloffset];
										if (itemnum > 0) {
											assettablemenu_ItemCurrentlyDragged = itemnum;
										}
									}

								}

							}

						}
					}

				}

				// end DM menus
				if (!clickedGUIElement) {
					for (int i = 0; i < DialogMenus.length; i++) {
						if (DialogMenus[i].is_open) {
							if (cursor.x > DialogMenus[i].x + 30 && cursor.x < DialogMenus[i].x + 30 + 100 && cursor.x > DialogMenus[i].x + 33
									&& cursor.x < DialogMenus[i].x + 33 + 20) {
								executeDialogMenu(i);
								break;
							} else {
								if (i == DIALOG_CHOOSESPEC) {
									closeDialogMenu(i);
								}
							}
						}
					}
				}

			}
		}
		// end clicking

		// System.out.println("wheel1:" + cursor.wheel_delta);

		/** Scroll Wheel Upwards */
		if (cursor.wheel_delta > 50) {

			if (Menus[MENU_ASSETTABLE].is_open == true) {
				if (cursor.x > Menus[MENU_ASSETTABLE].x && cursor.x < Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width
						&& cursor.y > Menus[MENU_ASSETTABLE].y && cursor.y < Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height) {

					if (Menus[MENU_ASSETTABLE].scrolloffset > 0) {
						Menus[MENU_ASSETTABLE].scrolloffset--;
					}

				}
			} else if (allbigmenus_notbeinghovered()) {

				if (chat_display_timer > 0 || game_typing_focus == TYPEFOCUS_CHAT) {
					if (chatscrolloffset > 0) {
						chatscrolloffset--;
					}
				}
			}

		}

		/** Scroll Wheel Downwards */
		if (cursor.wheel_delta < -50) {

			if (Menus[MENU_ASSETTABLE].is_open == true) {
				if (cursor.x > Menus[MENU_ASSETTABLE].x && cursor.x < Menus[MENU_ASSETTABLE].x + Menus[MENU_ASSETTABLE].width
						&& cursor.y > Menus[MENU_ASSETTABLE].y && cursor.y < Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height) {

					int length_of_current_list = 0;
					if (assettablemenu_currentasset == ASSETTABLE_UNITS) {
						if (assettablemenu_currentscreen == -1) {
							length_of_current_list = SharedData.ComboStrings[SharedData.COMBOTEXT_UNITCATEGORIES].length;
						} else {
							length_of_current_list = number_of_assets_in_groups[ASSETTABLE_UNITS][assettablemenu_currentscreen];
						}
					}
					if (assettablemenu_currentasset == ASSETTABLE_ITEMS) {
						if (assettablemenu_currentscreen == -1) {
							length_of_current_list = SharedData.ComboStrings[SharedData.COMBOTEXT_LOOTGROUPS].length;
						} else {
							length_of_current_list = number_of_assets_in_groups[ASSETTABLE_ITEMS][assettablemenu_currentscreen];
						}
					}
					if (assettablemenu_currentasset == ASSETTABLE_EFFECTS) {
						if (assettablemenu_currentscreen == -1) {
							length_of_current_list = SharedData.ComboStrings[SharedData.COMBOTEXT_EFFECTTYPES].length;
						} else {
							length_of_current_list = number_of_assets_in_groups[ASSETTABLE_EFFECTS][assettablemenu_currentscreen];
						}
					}

					int number_of_slots = (int) Math.floor((Menus[MENU_ASSETTABLE].height - 40) / 25);
					if (Menus[MENU_ASSETTABLE].scrolloffset < length_of_current_list - number_of_slots) {
						Menus[MENU_ASSETTABLE].scrolloffset++;
						// System.out.println(Menus[MENU_ASSETTABLE].scrolloffset);
					}

				}
			} else if (allbigmenus_notbeinghovered()) {

				if (chat_display_timer > 0 || game_typing_focus == TYPEFOCUS_CHAT) {

					if (chatscrolloffset < 350 && chathistorylines[chatscrolloffset + 1] != null) {
						chatscrolloffset++;
					}
				}
			}

		}

		/** MOUSE RELEASE */
		if ((!Mouse.isButtonDown(0) && Mouse_MouseAlreadyClicked[0] == true) || (!Mouse.isButtonDown(1) && Mouse_MouseAlreadyClicked[1] == true)) {
			menu_beingDragged = -1;
			menu_dragoffset_x = 0;
			menu_dragoffset_y = 0;
			map_beingDragged = false;

			counter_mousehelddown = -1;// reset and freeze the counter
			if (coordinatesWithinMapBounds(cursor.currentmousetile_x, cursor.currentmousetile_y)) {
				if (assettablemenu_ItemCurrentlyDragged > -1) {
					if (assettablemenu_currentasset == ASSETTABLE_UNITS) {

						if (allbigmenus_notbeinghovered() && UnitMap[cursor.currentmousetile_x][cursor.currentmousetile_y] == 0) {
							Server_Broadcast_Create_New_NPC(assettablemenu_ItemCurrentlyDragged, cursor.currentmousetile_x,
									cursor.currentmousetile_y, 1);
							cam.tileselect_x = cursor.currentmousetile_x;
							cam.tileselect_y = cursor.currentmousetile_y;
						}

					}

					if (assettablemenu_currentasset == ASSETTABLE_ITEMS) {

						// drop item onto unit on tile
						if (allbigmenus_notbeinghovered() && UnitMap[cursor.currentmousetile_x][cursor.currentmousetile_y] > 0) {

							Server_Broadcast_EditUnitItemIDQuantity(cursor.currentmousetile_x, cursor.currentmousetile_y,
									assettablemenu_ItemCurrentlyDragged, +1, false, 0);
						}

						// drop item onto smallobject cheat
						else if (allbigmenus_notbeinghovered() && myTypeDB[map[SharedData.SMALLOBJECTS_LAYER][y][x]].Can_Contain_Items) {

							Server_Broadcast_EditTileItemIDQuantity(cursor.currentmousetile_x, cursor.currentmousetile_y,
									assettablemenu_ItemCurrentlyDragged, +1, false, 0);
						}

						// drop item onto largeobject cheat
						else if (allbigmenus_notbeinghovered() && myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][y][x]].Can_Contain_Items) {

							Server_Broadcast_EditTileItemIDQuantity(cursor.currentmousetile_x, cursor.currentmousetile_y,
									assettablemenu_ItemCurrentlyDragged, +1, false, 0);
						}

						// drop item into chest loot screen
						else if (Menus[MENU_LOOTING].BeingHovered(cursor)) {

							Server_Broadcast_EditTileItemIDQuantity(itemcontainer_currently_looting_x, itemcontainer_currently_looting_y,
									assettablemenu_ItemCurrentlyDragged, +1, false, 0);

						}

						// drop item onto inventory
						else if (Menus[MENU_INV].BeingHovered(cursor)) {

							Server_Broadcast_EditUnitItemIDQuantity(cam.tileselect_x, cam.tileselect_y, assettablemenu_ItemCurrentlyDragged, +1,
									false, 0);

						}

					}

					if (assettablemenu_currentasset == ASSETTABLE_EFFECTS) {
						if (allbigmenus_notbeinghovered()) {
							// fix maybe
							// HostPlaceEffectAtTile(assettablemenu_ItemCurrentlyDragged,
							// cursor.x, cursor.y, cursor.x, cursor.y);

							debug("Place an Effect!");// /fix

							int effectnum = assettablemenu_ItemCurrentlyDragged;

							ExecuteEffectWhole(effectnum, cam.tileselect_x, cam.tileselect_y, cursor.currentmousetile_x, cursor.currentmousetile_y);

						}
					}
				}
			}

			// walk by releasing the mouse
			if (allbigmenus_notbeinghovered()) {

				double dist = Math.sqrt(Math.abs(Math.pow(cursor.currentmousetile_x - cam.tileselect_x, 2))
						+ Math.abs(Math.pow(cursor.currentmousetile_y - cam.tileselect_y, 2)));

				if (dist == 0) {
					targetting_currentability = 0;
				}

				if (dist < 9 && targetting_currentability == SPECIALABIL_WALK && dist > 0) {

					if (cursor.WithinMapBounds(SharedData.MAP_SIZE) && cursor.y < secondaryabilitybar.y) {

						TryToWalkTowardsMouseCursor(targetting_currentcaster);

					}
				}
			}

			// check to see if it is released on a body part (to equip) andreset
			// it to zero!
			int INV_Unit_ID = UnitMap[cam.tileselect_x][cam.tileselect_y];
			if (Menus[MENU_INV].is_open == true && bagSlotOf_itemBeingDragged > -1
					// && bagSlotOf_itemBeingDragged < 24
					&& Units[INV_Unit_ID].itemInSlot[bagSlotOf_itemBeingDragged] != 0 && cursor.x > Menus[MENU_INV].x + 260
					&& cursor.x < Menus[MENU_INV].x + 380 && cursor.y > Menus[MENU_INV].y + 278 && cursor.y < Menus[MENU_INV].y + 478) {

				TryToEquipItem(bagSlotOf_itemBeingDragged, INV_Unit_ID, true);

				bagSlotOf_itemBeingDragged = -1;
			}// end check for mouse location on the body representation

			// release an item into a bagslot from anywhere
			if (Menus[MENU_INV].is_open == true && bagSlotOf_itemBeingDragged > -1 && Units[INV_Unit_ID].itemInSlot[bagSlotOf_itemBeingDragged] != 0
					&& cursor.x > Menus[MENU_INV].x + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318
					&& cursor.y < Menus[MENU_INV].y + 318 + 160) {

				int bagslotReleasedIn = 0;

				bagslotReleasedIn = (cursor.x - 18 - Menus[MENU_INV].x) / 40 + 6 * ((cursor.y - 318 - Menus[MENU_INV].y) / 40);

				int UnitID = INV_Unit_ID;
				int draggeditem = Units[INV_Unit_ID].itemInSlot[bagSlotOf_itemBeingDragged];

				Server_BroadcastSwapUnitBagSlotItems(Units[UnitID].x, Units[UnitID].y, bagSlotOf_itemBeingDragged, bagslotReleasedIn);

				// remove item from itemcontainer if needed
				// this must be broadcast I think

			}

			if (Menus[MENU_INV].is_open == true && bagSlotOf_itemBeingDragged > -1 && Units[INV_Unit_ID].itemInSlot[bagSlotOf_itemBeingDragged] != 0
					&& !Menus[MENU_INV].BeingHovered(cursor)) {

				int UnitID = INV_Unit_ID;
				int draggeditem = Units[INV_Unit_ID].itemInSlot[bagSlotOf_itemBeingDragged];

				Server_Broadcast_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, draggeditem, -1, false, 0);

				// remove item from itemcontainer if needed
				// this must be broadcast I think

			}

			// end all inv menu stuff!

			bagSlotOf_itemBeingDragged = -1;
			assettablemenu_ItemCurrentlyDragged = -1;

			/*
			 * if (!Menus[MENU_LOOTING].is_open) {
			 * inventoryslot_currentlyselected = -1; }
			 */
		}

	}// end pollinputdm

	public void pollInput_Keyboard() {
		/**
		 * KEYBOARD LISTENING
		 * ------------------------------------------------------
		 */

		if (Keyboard.isKeyDown(Keyboard.KEY_F4) && (Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184))) {
			// System.out.println("SPACE KEY IS DOWN");
			GameRunning = false;
		}

		if (game_typing_focus == TYPEFOCUS_CHAT) {

			if (BackspaceTimer < 500 && (Keyboard_KeyAlreadyDown[Keyboard.KEY_BACK] || Keyboard_KeyAlreadyDown[Keyboard.KEY_DELETE])) {
				BackspaceTimer += animfactor;
			} else {
				BackspaceTimer = 0;
				Keyboard_KeyAlreadyDown[Keyboard.KEY_BACK] = false;
				Keyboard_KeyAlreadyDown[Keyboard.KEY_DELETE] = false;
			}

			// boolean anykeypressed = false;
			int KeyPressed = -1;// can only be one at a time!
			for (int i = 0; i < 244; i++) {
				if (Keyboard.isKeyDown(i) && !Keyboard_KeyAlreadyDown[i]) {

					KeyPressed = i;

				}

			}

			// pressing numbers

			if (KeyPressed >= Keyboard.KEY_1 && KeyPressed <= Keyboard.KEY_0) {

				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

					chat_typing_text += SpecialChars[Integer.parseInt(Keyboard.getKeyName(KeyPressed))];

				} else {
					chat_typing_text += Keyboard.getKeyName(KeyPressed);
				}

			}

			if (KeyPressed == Keyboard.KEY_NUMPAD0 || KeyPressed == Keyboard.KEY_NUMPAD1 || KeyPressed == Keyboard.KEY_NUMPAD2
					|| KeyPressed == Keyboard.KEY_NUMPAD3 || KeyPressed == Keyboard.KEY_NUMPAD4 || KeyPressed == Keyboard.KEY_NUMPAD5
					|| KeyPressed == Keyboard.KEY_NUMPAD6 || KeyPressed == Keyboard.KEY_NUMPAD7 || KeyPressed == Keyboard.KEY_NUMPAD8
					|| KeyPressed == Keyboard.KEY_NUMPAD9) {
				chat_typing_text += Keyboard.getKeyName(KeyPressed).substring(Keyboard.getKeyName(KeyPressed).length() - 1);
			}

			if (KeyPressed == Keyboard.KEY_PERIOD || KeyPressed == Keyboard.KEY_DECIMAL) {
				chat_typing_text += ".";
			}

			if (KeyPressed >= Keyboard.KEY_Q && KeyPressed <= Keyboard.KEY_M && Keyboard.getKeyName(KeyPressed).length() == 1) {

				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
					chat_typing_text += Keyboard.getKeyName(KeyPressed).toUpperCase();
				} else {
					chat_typing_text += Keyboard.getKeyName(KeyPressed).toLowerCase();
				}

			}

			if (KeyPressed == Keyboard.KEY_SPACE) {
				chat_typing_text += " ";
			}

			if (KeyPressed == Keyboard.KEY_COMMA) {
				chat_typing_text += ",";
			}

			if (KeyPressed == Keyboard.KEY_APOSTROPHE) {

				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
					chat_typing_text += "\"";
				} else {
					chat_typing_text += "'";
				}

			}

			if (chat_typing_text.length() > 0) {
				if (KeyPressed == Keyboard.KEY_BACK || KeyPressed == Keyboard.KEY_DELETE) {
					chat_typing_text = chat_typing_text.substring(0, chat_typing_text.length() - 1);
				}

			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game_typing_focus = 0;
			chat_typing_text = "";
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_Z]) {
			secondaryabilitybar.mode = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_X) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_X]) {
			secondaryabilitybar.mode = 2;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_C) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_C]) {
			secondaryabilitybar.mode = 3;
		}

		if ((Keyboard.isKeyDown(Keyboard.KEY_Y) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_Y])
				|| (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_RETURN]) && game_typing_focus == 0) {
			Keyboard_KeyAlreadyDown[Keyboard.KEY_RETURN] = true;
			game_typing_focus = TYPEFOCUS_CHAT;
		} else {

			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !Keyboard_KeyAlreadyDown[Keyboard.KEY_RETURN] && game_typing_focus == TYPEFOCUS_CHAT) {
				game_typing_focus = 0;

				if (chat_typing_text.length() > 0) {
					if (myPNum == 1) {

						int select_UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

						if (select_UnitID == 0) {
							Server_Broadcast_PlayerChatMessage(myPNum, chat_typing_text);
						} else {
							Server_Broadcast_UnitChatMessage(select_UnitID, chat_typing_text);
							Server_Broadcast_UnitBubbleMessage(select_UnitID, chat_typing_text);
						}

					} else {
						Client_Send_PlayerChatMessage(chat_typing_text);
					}
				}

				chat_typing_text = "";
			}

		}

		if (game_typing_focus == 0) {

			// tab to end turn
			if (Keyboard.isKeyDown(Keyboard.KEY_TAB) && Keyboard_KeyAlreadyDown[Keyboard.KEY_TAB] == false) {
				if (BattlePhaseEngaged) {
					UnitEndsBattleTurn(UnitMap[cam.tileselect_x][cam.tileselect_y]);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard_KeyAlreadyDown[Keyboard.KEY_I] == false) {
				openGameMenu(MENU_INV);
			}// inv
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Keyboard_KeyAlreadyDown[Keyboard.KEY_ESCAPE] == false) {
				openGameMenu(MENU_ESC);
			}// esc
			if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard_KeyAlreadyDown[Keyboard.KEY_K] == false) {
				openGameMenu(MENU_ABILITIES);
			}// skill
			if (Keyboard.isKeyDown(Keyboard.KEY_O) && Keyboard_KeyAlreadyDown[Keyboard.KEY_O] == false) {
				openGameMenu(MENU_QUESTTRACKER);
			}// skill
			if (Keyboard.isKeyDown(Keyboard.KEY_N) && Keyboard_KeyAlreadyDown[Keyboard.KEY_N] == false) {
				openGameMenu(MENU_ITEMVENDOR);
			}// skill

			if (Keyboard.isKeyDown(Keyboard.KEY_F3) && Keyboard_KeyAlreadyDown[Keyboard.KEY_F3] == false) {
				if (DEBUG_MODE) {
					DEBUG_MODE = false;
				} else {
					DEBUG_MODE = true;
				}
			}// skill

			// basic UI hotkeys, only work when menus are closed

			// allow all players to move their selections with WASD

			int Unit_Selected_ID = UnitMap[cam.tileselect_x][cam.tileselect_y];
			if (Unit_Selected_ID > 0) {
				if (Units[Unit_Selected_ID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
					if (UnitCanWalk(Unit_Selected_ID) && (!BattlePhaseEngaged || BattlingUnits[BattlePhase_ActiveUnit] == Unit_Selected_ID)) {

						if (!Units[Unit_Selected_ID].is_sliding) {

							if ((Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard_KeyAlreadyDown[Keyboard.KEY_W] == false)
									|| Selected_Unit_Walk_Queue == 0) {
								Anyone_Broadcast_CastAbility(SPECIALABIL_WALK, Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y,
										Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y - 1);
							}
							if ((Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard_KeyAlreadyDown[Keyboard.KEY_D] == false)
									|| Selected_Unit_Walk_Queue == 1) {
								Anyone_Broadcast_CastAbility(SPECIALABIL_WALK, Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y,
										Units[Unit_Selected_ID].x + 1, Units[Unit_Selected_ID].y);
							}
							if ((Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard_KeyAlreadyDown[Keyboard.KEY_S] == false)
									|| Selected_Unit_Walk_Queue == 2) {
								Anyone_Broadcast_CastAbility(SPECIALABIL_WALK, Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y,
										Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y + 1);
							}
							if ((Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard_KeyAlreadyDown[Keyboard.KEY_A] == false)
									|| Selected_Unit_Walk_Queue == 3) {
								Anyone_Broadcast_CastAbility(SPECIALABIL_WALK, Units[Unit_Selected_ID].x, Units[Unit_Selected_ID].y,
										Units[Unit_Selected_ID].x - 1, Units[Unit_Selected_ID].y);
							}

							Selected_Unit_Walk_Queue = -1;
						} else {
							Selected_Unit_Walk_Queue = -1;
							if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
								Selected_Unit_Walk_Queue = 0;
							}
							if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
								Selected_Unit_Walk_Queue = 1;
							}
							if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
								Selected_Unit_Walk_Queue = 2;
							}
							if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
								Selected_Unit_Walk_Queue = 3;
							}
						}

					}

					if (secondaryabilitybar.mode == 1) {

						int ActiveWepSkills[] = GetActiveWeaponSkills();

						for (int i = 0; i < 10; i++) {// ability hotkeys
							if (Keyboard.isKeyDown(Keyboard.KEY_1 + i) && Keyboard_KeyAlreadyDown[Keyboard.KEY_1 + i] == false) {

								int clickedability = ActiveWepSkills[i];
								Anyone_ClickOnAbility(clickedability, Unit_Selected_ID, (secondaryabilitybar.mode == 1));
							}
						}
					}

					if (secondaryabilitybar.mode == 2) {
						for (int i = 0; i < 10; i++) {// ability hotkeys
							if (Keyboard.isKeyDown(Keyboard.KEY_1 + i) && Keyboard_KeyAlreadyDown[Keyboard.KEY_1 + i] == false) {
								int clickedability = Units[Unit_Selected_ID].active_spells[i];
								Anyone_ClickOnAbility(clickedability, Unit_Selected_ID, (secondaryabilitybar.mode == 1));
							}
						}
					}

				}// end check for ownership of unit
			}

			// moving the camera
			if (Keyboard.isKeyDown(Keyboard.KEY_UP) && cam.y > 0) {
				cam.y--;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && cam.x > 0) {
				cam.x--;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && cam.y < SharedData.MAP_SIZE) {
				cam.y++;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && cam.x < SharedData.MAP_SIZE) {
				cam.x++;
			}

		}// end not currently typing in chat check

	}

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

	// Timer updateKeyTimer;

	void config_UI_sizes_continuous() {

		/*
		 * if (mySettings.FullScreen) { SCREEN_WIDTH = MONITOR_WIDTH;
		 * SCREEN_HEIGHT = MONITOR_HEIGHT;
		 * 
		 * } else { SCREEN_WIDTH = Display.getWidth(); SCREEN_HEIGHT =
		 * Display.getHeight(); }
		 */

		SCREEN_WIDTH = Display.getWidth();
		SCREEN_HEIGHT = Display.getHeight();

		SCREEN_X_TILES = ((SCREEN_WIDTH) / TILE_SIZE) + 1; // System.out.println(SCREEN_X_TILES);
		SCREEN_Y_TILES = ((SCREEN_HEIGHT) / TILE_SIZE) + 1;

		primaryabilitybar.width = 50 * MainBarIcons.length;
		primaryabilitybar.height = 50;
		primaryabilitybar.x = ((SCREEN_WIDTH) / 2) - (primaryabilitybar.width / 2);
		if (primaryabilitybar.x < 260) {
			primaryabilitybar.x = 260;
		}
		primaryabilitybar.y = SCREEN_HEIGHT - 50;

		secondaryabilitybar.width = 50 * 10;
		secondaryabilitybar.height = 50;
		secondaryabilitybar.x = ((SCREEN_WIDTH) / 2) - (secondaryabilitybar.width / 2);
		if (secondaryabilitybar.x < 260) {
			secondaryabilitybar.x = 260;
		}
		secondaryabilitybar.y = SCREEN_HEIGHT - 100;

		conditionbar.width = 30 * 10;
		conditionbar.height = 30 * 10;
		conditionbar.x = 130;
		conditionbar.y = 85;

		tileconditionbar.width = 30 * 10;
		tileconditionbar.height = 30 * 10;
		tileconditionbar.x = SCREEN_WIDTH / 2 - tileconditionbar.width;
		tileconditionbar.y = 5;

		minimenubar.width = 20 * 5;
		minimenubar.height = 20;
		minimenubar.x = 0;
		minimenubar.y = 0;

		Menus[MENU_ASSETTABLE].height = 500;
		if (SCREEN_HEIGHT < 700) {
			Menus[MENU_ASSETTABLE].height = 400;
		}

		Menus[MENU_MINIMAP].x = SCREEN_WIDTH - 20 - Menus[MENU_MINIMAP].width;
		Menus[MENU_MINIMAP].y = 20;
		Menus[MENU_MINIMAP].is_open = true;

		Menus[MENU_ASSETTABLE].x = 0 - Menus[MENU_ASSETTABLE].tweencount;
		Menus[MENU_ASSETTABLE].y = 70 + (SCREEN_HEIGHT - 600) / 2;
		if (Menus[MENU_ASSETTABLE].y < 160) {
			Menus[MENU_ASSETTABLE].y = 160;
		}

		Menus[MENU_ITEMVENDOR].width = 200 - Menus[MENU_ITEMVENDOR].tweencount;
		Menus[MENU_ITEMVENDOR].height = 500 - 25;
		Menus[MENU_ITEMVENDOR].x = Menus[MENU_INV].x - Menus[MENU_ITEMVENDOR].width;
		Menus[MENU_ITEMVENDOR].y = Menus[MENU_INV].y;
		Menus[MENU_ITEMVENDOR].exitbutton_x = Menus[MENU_ITEMVENDOR].width - 25;
		Menus[MENU_ITEMVENDOR].exitbutton_y = 5;

		DialogMenus[DIALOG_RESPAWN].text = "You have fallen";
		DialogMenus[DIALOG_RESPAWN].buttonlabel = "Respawn";
		DialogMenus[DIALOG_CHOOSESPEC].text = "Specialize in " + SharedData.ComboStrings[0][DialogMenus[DIALOG_CHOOSESPEC].mode] + "?";
		DialogMenus[DIALOG_CHOOSESPEC].buttonlabel = "Accept";
		for (int i = 0; i < DialogMenus.length; i++) {

			DialogMenus[i].width = 60 + Verdana_18.getStringPixelLength(DialogMenus[i].text);
			DialogMenus[i].height = 60;
			DialogMenus[i].x = SCREEN_WIDTH / 2 - DialogMenus[i].width / 2;
			DialogMenus[i].y = SCREEN_HEIGHT / 2 - DialogMenus[i].height / 2;

		}

		Menus[MENU_LOGIN].width = 400;
		Menus[MENU_LOGIN].height = 300 - (Menus[MENU_LOGIN].tweencount / 2);
		if (Menus[MENU_LOGIN].height < 50) {
			Menus[MENU_LOGIN].height = 50;
		}
		Menus[MENU_LOGIN].x = SCREEN_WIDTH / 2 - Menus[MENU_LOGIN].width / 2 - Menus[MENU_LOGIN].tweencount;
		Menus[MENU_LOGIN].y = SCREEN_HEIGHT / 2 - Menus[MENU_LOGIN].height / 2;
		Menus[MENU_LOGIN].exitbutton_x = Menus[MENU_LOGIN].width - 22;
		Menus[MENU_LOGIN].exitbutton_y = -0;

		Menus[MENU_TUTORIAL].x = SCREEN_WIDTH / 2 - 100;
		Menus[MENU_TUTORIAL].y = 120;

		PhaseButton.x = SCREEN_WIDTH / 2 - 50;

		SleekMessageBox.width = (int) SleekMessageBox.background.getWidth();
		SleekMessageBox.x = SCREEN_WIDTH / 2 - SleekMessageBox.width / 2;
		SleekMessageBox.y = -100 + SleekMessageBox.tweencount;
		if (SleekMessageBox.y > SCREEN_HEIGHT / 2 - 200) {
			SleekMessageBox.y = SCREEN_HEIGHT / 2 - 200;
		}

		// these are the DM hints
		TutorialHint[0] = new Hint(
				"You are a host, but no heroes have joined yet.  While you wait for some to join, you may spawn NPCs from the Asset Table to begin building a story scenario.",
				Menus[MENU_ASSETTABLE].x + 10, Menus[MENU_ASSETTABLE].y + Menus[MENU_ASSETTABLE].height / 2);

	}

	final int draggablemenus[] = {MENU_EDITSTATS, MENU_INV, MENU_LOOTING, MENU_ESC, MENU_QUESTTRACKER, MENU_BATTLERESULTS};

	void config_UI_sizes_initial() {

		// position misc UI elements

		Menus[MENU_ESC].x = 300;
		Menus[MENU_ESC].y = 80;
		Menus[MENU_ESC].width = 400;
		Menus[MENU_ESC].height = 500;
		Menus[MENU_ESC].exitbutton_x = Menus[MENU_ESC].width - 25;
		Menus[MENU_ESC].exitbutton_y = 5;

		Menus[MENU_QUESTTRACKER].x = (SCREEN_WIDTH - 200) / 2 - 200;
		Menus[MENU_QUESTTRACKER].y = 80;
		Menus[MENU_QUESTTRACKER].width = 400;
		Menus[MENU_QUESTTRACKER].height = 500;
		Menus[MENU_QUESTTRACKER].exitbutton_x = Menus[MENU_QUESTTRACKER].width - 25;
		Menus[MENU_QUESTTRACKER].exitbutton_y = 5;

		Menus[MENU_BATTLERESULTS].x = (SCREEN_WIDTH - 200) / 2 - 200;
		Menus[MENU_BATTLERESULTS].y = 80;
		Menus[MENU_BATTLERESULTS].width = 400;
		Menus[MENU_BATTLERESULTS].height = 500;
		Menus[MENU_BATTLERESULTS].exitbutton_x = Menus[MENU_BATTLERESULTS].width - 25;
		Menus[MENU_BATTLERESULTS].exitbutton_y = 5;

		Menus[MENU_INV].x = 300;
		Menus[MENU_INV].y = 80;
		Menus[MENU_INV].width = 400;
		Menus[MENU_INV].height = 500;
		Menus[MENU_INV].exitbutton_x = Menus[MENU_INV].width - 25;
		Menus[MENU_INV].exitbutton_y = 5;

		Menus[MENU_LOOTING].x = SCREEN_WIDTH / 2 + 100;
		Menus[MENU_LOOTING].y = SCREEN_HEIGHT / 2 - 100;
		Menus[MENU_LOOTING].width = 128;
		Menus[MENU_LOOTING].height = 100;
		Menus[MENU_LOOTING].exitbutton_x = Menus[MENU_LOOTING].width - 15;
		Menus[MENU_LOOTING].exitbutton_y = -5;

		Menus[MENU_EDITSTATS].x = 300;
		Menus[MENU_EDITSTATS].y = 80;
		Menus[MENU_EDITSTATS].width = 400;
		Menus[MENU_EDITSTATS].height = 500;
		Menus[MENU_EDITSTATS].exitbutton_x = Menus[MENU_EDITSTATS].width - 25;
		Menus[MENU_EDITSTATS].exitbutton_y = 5;

		Menus[MENU_ABILITIES].x = 300;
		Menus[MENU_ABILITIES].y = 80;
		Menus[MENU_ABILITIES].width = 400;
		Menus[MENU_ABILITIES].height = 550;
		Menus[MENU_ABILITIES].exitbutton_x = Menus[MENU_ABILITIES].width - 25;
		Menus[MENU_ABILITIES].exitbutton_y = 45;

		Menus[MENU_ASSETTABLE].x = 0 - Menus[MENU_ASSETTABLE].tweencount;
		Menus[MENU_ASSETTABLE].y = 70 + (SCREEN_HEIGHT - 600) / 2;
		if (Menus[MENU_ASSETTABLE].y < 160) {
			Menus[MENU_ASSETTABLE].y = 160;
		}
		Menus[MENU_ASSETTABLE].width = 200;
		Menus[MENU_ASSETTABLE].height = 500;
		Menus[MENU_ASSETTABLE].exitbutton_x = Menus[MENU_ASSETTABLE].width - 15;
		Menus[MENU_ASSETTABLE].exitbutton_y = -5;

		Menus[MENU_MINIMAP].width = 200;
		Menus[MENU_MINIMAP].height = 200;
		Menus[MENU_MINIMAP].x = SCREEN_WIDTH - 20 - Menus[MENU_MINIMAP].width;
		Menus[MENU_MINIMAP].y = 20;

		invmenu_equipslots_x[0] = 300;
		invmenu_equipslots_y[0] = 278 + 40 * (0);
		invmenu_equipslots_x[1] = 300;
		invmenu_equipslots_y[1] = 278 + 40 * (1);
		invmenu_equipslots_x[2] = 300;
		invmenu_equipslots_y[2] = 278 + 40 * (2);
		invmenu_equipslots_x[3] = 300;
		invmenu_equipslots_y[3] = 278 + 40 * (3);
		invmenu_equipslots_x[4] = 300;
		invmenu_equipslots_y[4] = 278 + 40 * (4);

		invmenu_equipslots_x[5] = 260;
		invmenu_equipslots_y[5] = 278 + 40 * (2);
		invmenu_equipslots_x[6] = 340;
		invmenu_equipslots_y[6] = 278 + 40 * (2);
		invmenu_equipslots_x[7] = 260;
		invmenu_equipslots_y[7] = 278 + 40 * (1);
		invmenu_equipslots_x[8] = 340;
		invmenu_equipslots_y[8] = 278 + 40 * (1);

		Menus[MENU_LOGIN].width = 400;
		Menus[MENU_LOGIN].height = 300;
		Menus[MENU_LOGIN].x = SCREEN_WIDTH / 2 - Menus[MENU_LOGIN].width / 2;
		Menus[MENU_LOGIN].y = SCREEN_HEIGHT / 2 - Menus[MENU_LOGIN].height / 2;
		Menus[MENU_LOGIN].exitbutton_x = Menus[MENU_LOGIN].width;
		Menus[MENU_LOGIN].exitbutton_y = 0;

	}

	protected void prepareMapforLoad() { // sets defaults

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				lit[y][x] = 0; // make all tiles dark

			}
		}

		// Init the map to contain grass on the ground and boundary walls.
		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				// map[TERRAIN_LAYER][y][x]=1 + (int) (Math.random()*16*3);
				// map[TERRAIN_LAYER][y][x] = GRASS_1 + ((int)(Math.random()*4))
				// ;
				/*
				 * int tileoffset = 0; double tiler = Math.random(); if (tiler >
				 * 0.5) { tileoffset = 32; }
				 */
				map[myshareddata.TERRAIN_LAYER_LOWER][y][x] = 1;
				/*
				 * map[LARGEOBJECTS_LAYER][y][0] = WALL_H1; // place walls
				 * around // perimeter of map! map[LARGEOBJECTS_LAYER][0][x] =
				 * WALL_H1; map[LARGEOBJECTS_LAYER][SharedData.MAP_SIZE - 1][x]
				 * = WALL_H1; map[LARGEOBJECTS_LAYER][y][SharedData.MAP_SIZE -
				 * 1] = WALL_H1;
				 */
			}
		}

	}

	void ChooseCampaignMenu_StartGameServer(int server_port) {

		// START GAME

		if (RunningInGoldMode()) {
			readassetbookfromFile(SharedData.defaultDirectory() + "\\" + "MyAssetBook.spl");
		} else {
			readassetbookfromFile("wrap/assets/MyAssetBook.spl");
		}

		myPNum = 1;
		Players[myPNum].name = "Game Master";

		playSound(mySoundPaths[SOUND_DRUMLOW2]);

		networkThread_serv = new ServerMultiThreaded(server_port, this);
		myNetworkingThread = new Thread(networkThread_serv);
		myNetworkingThread.start();

		UDPThread_broadcast = new UDPBroadcastServer(server_port, this);
		myUDPBroadcastThread = new Thread(UDPThread_broadcast);
		myUDPBroadcastThread.start();

		if (ServerOpenToPublic) {
			MYSQL_StartHostingServer(ipAddr_global, "" + server_port, CampaignName);
		}

		initgameOpenGL();

	}

	void gameMusic() {

		if (BGMusic_finish_timer > 0) {// timer is in milliseconds
			BGMusic_finish_timer -= delta;// subtracts off the number of
											// milliseconds since the last
											// frame!
		} else {
			// if(current_ambient_music!=null){current_ambient_music.stop();}

			if (Math.random() > 0.5) {// random bursts of silence

				System.out.println("play new music");

				PlayRandomMusic();

			} else {
				BGMusic_finish_timer = 20 * 1000;
			}

		}

	}

	void PlayRandomMusic() {

		debug("Playing Random Music");

		float previousvolume = BackgroundMusic.getVolume();
		System.out.println("vol" + BackgroundMusic.getVolume());

		if (BattlePhaseEngaged) {
			int track_num = (int) (Math.random() * battleMusic_path.length);

			try {
				BackgroundMusic = new Music("wrap/assets/music/" + battleMusic_path[track_num], true);// the
																										// boolean
																										// means
																										// STREAM
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BackgroundMusic.play();
			BGMusic_finish_timer = 60 * 1000;

		} else {

			if (cam.Cam_Within_Bounds()) {
				if (map[myshareddata.TERRAIN_LAYER_LOWER][cam.y][cam.x] == 9) {
					// play scary music, set finish timer accordingly
					int track_num = (int) (Math.random() * darkMusic_path.length);
					try {
						BackgroundMusic = new Music("wrap/assets/music/" + darkMusic_path[track_num], true);// the
																											// boolean
																											// means
																											// STREAM
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BackgroundMusic.play();
					BGMusic_finish_timer = 60 * 1000;

				} else {
					// play happy music, set finish timer accordingly

					int track_num = (int) (Math.random() * lightMusic_path.length);

					try {
						BackgroundMusic = new Music("wrap/assets/music/" + lightMusic_path[track_num], true);

					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BackgroundMusic.play();
					BGMusic_finish_timer = lightMusic_duration[track_num] * 1000;

				}

			}

		}

		BackgroundMusic.setVolume(previousvolume);

	}

	protected void finishMapLoad() {
		System.out.println("FINISH MAP LOAD");

		// generateMiniMap();
		organizeHeroAbilities();

		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				if (Math.random() > 0.7) {
					TerrainVariantMap[x][y] = 1 + (int) (Math.random() * 3);
				}

			}
		}

		// put hero camera on their spawned hero
		if (myPNum >= 2) {
			cam.x = Units[myPNum].x;
			cam.y = Units[myPNum].y;
		}

	}

	// Image generic_SideButton;

	/* --------------END ALL GRAPHICS STUFF, BEGIN 'NEXT TURN' STUFF------------ */

	private boolean UnitIsHero(int UnitID) {

		if (UnitID < 10) {
			return true;
		}
		return false;
	}

	/*
	 * private boolean object_IsEverlit(int x, int y, int z) {
	 * 
	 * //for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <=
	 * myshareddata.LARGEOBJECTS_LAYER; z++) {
	 * 
	 * for (int i = 0; i < myshareddata.EVERLIT_OBJECTS.length; i++) {
	 * 
	 * if (myshareddata.EVERLIT_OBJECTS[i] == map[z][y][x]) { return true; }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //} return false;
	 * 
	 * 
	 * }
	 */

	// for hero movement
	private boolean terrain_IsWalkable(int x, int y) {
		for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {

			for (int i = 0; i < myshareddata.COLLIDABLE_OBJECTS_FLOOR.length; i++) {

				if (myshareddata.COLLIDABLE_OBJECTS_FLOOR[i] == map[z][y][x]) {
					return false;
				}// server checks this type of collision, NPCS ARE NOT A PART OF
					// THIS

			}

			for (int i = 0; i < myshareddata.COLLIDABLE_OBJECTS_HOVER.length; i++) {

				if (myshareddata.COLLIDABLE_OBJECTS_HOVER[i] == map[z][y][x]) {
					return false;
				}// server checks this type of collision, NPCS ARE NOT A PART OF
					// THIS

			}

			// if (map[z][y][x] >= LAKEWATER_1 && map[z][y][x] <= LAKEWATER_16)
			// {
			// return false;
			// } collision with water!

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

	private boolean Unit_At_Tile(int x, int y) {

		if (x > -1 && x < SharedData.MAP_SIZE && y > -1 && y < SharedData.MAP_SIZE) {

			if (UnitMap[x][y] > 0) {

				return true;

			}
		}

		return false;

	}

	private boolean NPC_At_Tile(int x, int y) {
		int UnitID = UnitMap[x][y];
		if (UnitID >= 10) {

			return true;

		}
		return false;

	}

	private boolean TryToPushMoveableBlock(int x, int y, int direction) {

		int block_x = x;
		int block_y = y;
		if (direction == 1) {
			block_y--;
		}
		if (direction == 2) {
			block_x++;
		}
		if (direction == 3) {
			block_y++;
		}
		if (direction == 4) {
			block_x--;
		}
		int new_x = block_x;
		int new_y = block_y;
		if (direction == 1) {
			new_y--;
		}
		if (direction == 2) {
			new_x++;
		}
		if (direction == 3) {
			new_y++;
		}
		if (direction == 4) {
			new_x--;
		}

		if (coordinatesWithinMapBounds(new_x, new_y)) {
			if (map[SharedData.LARGEOBJECTS_LAYER][block_y][block_x] == SharedData.MOVEABLEBLOCK && terrain_IsWalkable(new_x, new_y)
					&& !Unit_At_Tile(new_x, new_y) && map[SharedData.LARGEOBJECTS_LAYER][new_y][new_x] == 0) {
				map[SharedData.LARGEOBJECTS_LAYER][new_y][new_x] = map[SharedData.LARGEOBJECTS_LAYER][block_y][block_x];
				map[SharedData.LARGEOBJECTS_LAYER][block_y][block_x] = 0;
				return true;
			}

		}
		return false;

	}

	boolean largeobject_blockslight(int x, int y) {

		// for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <=
		// myshareddata.LARGEOBJECTS_LAYER; z++) {
		for (int i = 0; i < myshareddata.LIGHTBLOCKING_OBJECTS.length; i++) {

			if (myshareddata.LIGHTBLOCKING_OBJECTS[i] == map[myshareddata.LARGEOBJECTS_LAYER][y][x]) {
				return true;
			}// server checks this type of collision, NPCS ARE NOT A PART OF
				// THIS

		}
		// }
		/* else */return false;

	}

	double preadditivelit[][] = new double[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4]; // for
	// storage
	double prevtilebrightness;
	double tilebrightness;

	boolean pixelsToBeDeLit[][] = new boolean[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];
	// int pixelsInPlayerRadius[][] = new int[SharedData.MAP_SIZE *
	// 4][SharedData.MAP_SIZE * 4];
	// int objectToMove;
	double tiledist_fromhero;

	// remember to declare variables in this outer space and DO NOT declare new
	// variables in loops or it will leak TONS of memory!

	void nextturn() {

		Everyone_NextTurn_AllUnitStats();
		Everyone_CountdownConditions();
		Everyone_CountTimeForward();

		poll_Unit_Special_Stats();

	}// end nextturn

	private void CalculateClientHeroLighting() {// Client Lighting and stuff

		// set lit to base values
		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				alltorchlit[y][x] = 0;
				pixelsToBeDeLit[y][x] = false;
				// stored_lit[y][x] = 0;

			}
		}

		// make a map of the torch light
		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				int UnitID = UnitMap[x][y];

				if (map[myshareddata.SMALLOBJECTS_LAYER][y][x] > 0) {
					if (myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]] > 0) {

						generatetorchlight(x, y, myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]], 0);

					}

				}

				if (map[myshareddata.LARGEOBJECTS_LAYER][y][x] > 0) {
					if (myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]] > 0) {

						generatetorchlight(x, y, myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]], 0);

					}

				}

				if (UnitID > 0) {
					if (Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] >= 2) {// can
																				// see
																				// all
																				// non-DM
																				// units!
						int litdirection = 0;// circle lit in day
						/*
						 * if ((HOUR_OF_DAY < 6 || HOUR_OF_DAY > 18) ||
						 * shading_Indoors[y / 4][x / 4]) { litdirection =
						 * Units[UnitID].stat[myshareddata.FACING] + 1; }
						 */
						generatetorchlight(x, y, Units[UnitID].stat[myshareddata.PERCEPTION] * 1, litdirection);
					}
				}

			}
		}

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				stored_lit[y][x] = 0;

			}
		}

		// add the torch light to the base light

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				if (alltorchlit[y][x] > 0) {

					stored_lit[y][x] = alltorchlit[y][x];

					if (stored_lit[y][x] > 1) {
						stored_lit[y][x] = 1;
					}

				}
			}
		}

		System.out.println("lighting completed");

	}// end players lighting calculations

	private void DMLighting() {
		// DM Lighting and stuff (no de-lighting)

		debug("Host Lighting");

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				alltorchlit[y][x] = 0;
				pixelsToBeDeLit[y][x] = false;

			}
		}

		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				int UnitID = UnitMap[x][y];

				if (map[myshareddata.SMALLOBJECTS_LAYER][y][x] > 0) {
					if (myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]] > 0) {

						generatetorchlight(x, y, myshareddata.ObjectLightRadiation[map[myshareddata.SMALLOBJECTS_LAYER][y][x]], 0);

					}

				}

				if (map[myshareddata.LARGEOBJECTS_LAYER][y][x] > 0) {
					if (myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]] > 0) {

						generatetorchlight(x, y, myshareddata.ObjectLightRadiation[map[myshareddata.LARGEOBJECTS_LAYER][y][x]], 0);

					}

				}

				if (UnitID > 0) {

					// if (Units[UnitID].stat[PLAYER_OWNERSHIP] == myPNum) {
					// generatetorchlight(x, y,
					// Units[UnitID].stat[PERCEPTION]*1);
					// }

					generatetorchlight(x, y, 10, 0);
				}

			}
		}

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				stored_lit[y][x] = 0;

			}
		}

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {

				if (alltorchlit[y][x] > 0) {
					stored_lit[y][x] = alltorchlit[y][x];
					if (stored_lit[y][x] > 1) {
						stored_lit[y][x] = 1;
					}

				}
			}
		}

	}

	void calculateLightingEveryFrame() {// the only function that affects lit...
										// period!!!

		// procedural lighting! Stored_Lit will have nothign procedural in it,
		// lit will be edited by procedurals

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {
				lit[y][x] = 0;
				if (myPNum != 1) {

					if (shading_Explored[y][x] == true) {
						lit[y][x] = EXPLOREDMAP_BRIGHTNESS_OUTDOORS;
					} else {
						lit[y][x] = UNEXPLOREDMAP_BRIGHTNESS_OUTDOORS;
					}

					if (x < SharedData.MAP_SIZE * 4 - 2) {
						if (define_regions[SharedData.REGION_INDOORS - 800][y / 4][(x + 2) / 4]) {

							// indoor lighting
							if (shading_Explored[y][x] == true) {
								lit[y][x] = EXPLOREDMAP_BRIGHTNESS_INDOORS;
							} else {
								lit[y][x] = UNEXPLOREDMAP_BRIGHTNESS_INDOORS;
							}

						}
					}

					if (HOUR_OF_DAY < 6 || HOUR_OF_DAY > 18) {
						lit[y][x] = lit[y][x] / 2;
					}

				} else {// DM
					lit[y][x] = MINIMUM_LIT_OUTDOORS;

					if (x < SharedData.MAP_SIZE * 4 - 2) {
						if (define_regions[SharedData.REGION_INDOORS - 800][y / 4][(x + 2) / 4]) {
							lit[y][x] = MINIMUM_LIT_INDOORS;
						}
					}

					if (HOUR_OF_DAY < 6 || HOUR_OF_DAY > 18) {
						lit[y][x] = lit[y][x] / 2;
					}
				}

				if (stored_lit[y][x] > 0) {
					lit[y][x] += stored_lit[y][x];
				}

				if (procedural_lit[y][x] > 0) {
					lit[y][x] += procedural_lit[y][x];
				}

				if (myPNum == 1) {
					/*
					 * if (lit[y][x] < DM_MIN_FOG ) { lit[y][x] = DM_MIN_FOG;
					 * }// map is lit up for DM
					 */
				} else {
					if (define_regions[SharedData.REGION_INDOORS - 800][y / 4][x / 4]
							|| define_regions[SharedData.REGION_DUNGEON - 800][y / 4][x / 4]) {
						if (lit[y][x] > UNEXPLOREDMAP_BRIGHTNESS_INDOORS) {
							shading_Explored[y][x] = true;
						}
					} else {
						if (lit[y][x] > UNEXPLOREDMAP_BRIGHTNESS_OUTDOORS) {
							shading_Explored[y][x] = true;
						}
					}
				}

				if (lit[y][x] > 1) {
					lit[y][x] = 1;
				}

			}
		}

	}

	private void generatetorchlight(int torch_x, int torch_y, int brightness, int facing) {
		int cone_direction = -1;
		boolean conic_light = false;
		if (facing > 0) {
			conic_light = true;
			cone_direction = facing - 1;
		}

		for (int y = 0; y < SharedData.MAP_SIZE * 4; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE * 4; x++) {
				pixelsToBeDeLit[y][x] = false;

			}
		}

		double torchtilebrightness[][] = new double[400][400];

		for (int a = (torch_y * 4) - 41; a < (torch_y * 4) + 41; a++) {
			for (int b = (torch_x * 4) - 41; b < (torch_x * 4) + 41; b++) {
				if (a > -1 && a < SharedData.MAP_SIZE * 4 && b > -1 && b < SharedData.MAP_SIZE * 4) {

					double tiledist_fromtorch = 0;

					/*
					 * tiledist_fromtorch = Math.sqrt(Math.abs(Math.pow(a -
					 * (torch_y * 4), 2)) + Math.abs(Math.pow(b - (torch_x * 4),
					 * 2)));
					 */

					tiledist_fromtorch = pythagorean(torch_x * 4, torch_y * 4, b, a);

					int lightpixeldirection = DirectionBetweenCoordinates(torch_x * 4, torch_y * 4, b, a);

					if (!conic_light) {// light goes all the way around
						torchtilebrightness[a][b] = (0.06 * brightness) - 0.05 * tiledist_fromtorch;

					} else if (cone_direction == lightpixeldirection) {// conic
																		// lightning
						torchtilebrightness[a][b] = (0.06 * brightness) - 0.05 * tiledist_fromtorch;

					}
					/*
					 * if(lightpixeldirection==1){ torchtilebrightness=1;}
					 */

					if (torchtilebrightness[a][b] > 1) {
						torchtilebrightness[a][b] = 1;
					}
					if (torchtilebrightness[a][b] < 0) {
						torchtilebrightness[a][b] = 0;
					}

					// the next 200 lines are terrible xD xD

					int wall_x = b / 4;
					int wall_y = a / 4;

					if (largeobject_blockslight(wall_x, wall_y)) {
						int offset_h_y = myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][a / 4][b / 4]].lightblock_offset_h_y;
						int offset_h_x = myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][a / 4][b / 4]].lightblock_offset_h_x;
						int offset_v_y = myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][a / 4][b / 4]].lightblock_offset_v_y;
						int offset_v_x = myTypeDB[map[SharedData.LARGEOBJECTS_LAYER][a / 4][b / 4]].lightblock_offset_v_x;

						if (torch_x < wall_x) {
							offset_h_x *= -1;
						}

						if (torch_y > wall_y) {// flag all tiles above wall for
												// delighting
							for (int new_y = a - (8 * 4) + offset_v_x; new_y < a - (1 * 4) + offset_v_x; new_y++) {
								if (new_y > -1 && new_y < SharedData.MAP_SIZE * 4 && b + offset_v_y > -1 && b + offset_v_y < SharedData.MAP_SIZE * 4) {

									pixelsToBeDeLit[new_y][b + offset_v_y] = true;

								}
							}
						}

						if (torch_y < wall_y) {// flag all tiles below wall for
												// delighting
							for (int new_y = a + (8 * 4) + offset_v_x; new_y > a + (1 * 4) + offset_v_x; new_y--) {
								if (new_y > -1 && new_y < SharedData.MAP_SIZE * 4 && b + offset_v_y > -1 && b + offset_v_y < SharedData.MAP_SIZE * 4) {

									pixelsToBeDeLit[new_y][b + offset_v_y] = true;

								}// THE BUG IS HERE!!!
							}
						}

						if (torch_x < wall_x) {// /flag all shixels right of
												// wall for delighting
							for (int new_x = b + (8 * 4) + offset_h_x; new_x > b + (1 * 4) + offset_h_x; new_x--) {
								if (new_x > -1 && new_x < SharedData.MAP_SIZE * 4 && a + offset_h_y > -1 && a + offset_h_y < SharedData.MAP_SIZE * 4) {

									pixelsToBeDeLit[a + offset_h_y][new_x] = true;

								}
							}

						}

						if (torch_x > wall_x) {// /flag all shixels left of wall
												// for delighting DEFINITELY
												// WORKS, -- THE MASTER
							for (int new_x = b - (8 * 4) + offset_h_x; new_x < b - (1 * 4) + offset_h_x; new_x++) {
								if (new_x > -1 && new_x < SharedData.MAP_SIZE * 4 && a + offset_h_y > -1 && a + offset_h_y < SharedData.MAP_SIZE * 4) {

									pixelsToBeDeLit[a + offset_h_y][new_x] = true;

								}
							}

						}

					}

					// end delighting tagging

				}
			}
		}

		for (int a = (torch_y * 4) - 41; a < (torch_y * 4) + 41; a++) {
			for (int b = (torch_x * 4) - 41; b < (torch_x * 4) + 41; b++) {
				if (a > -1 && a < SharedData.MAP_SIZE * 4 && b > -1 && b < SharedData.MAP_SIZE * 4) {

					if (torchtilebrightness[a][b] > 1) {
						torchtilebrightness[a][b] = 1;
					}
					if (torchtilebrightness[a][b] < 0) {
						torchtilebrightness[a][b] = 0;
					}

					// double torch_pre_lit = 0;
					// double torch_pre_lit = lit[a][b] + torchtilebrightness;

					if (!pixelsToBeDeLit[a][b]) {
						alltorchlit[a][b] += torchtilebrightness[a][b];
					}

					/*
					 * if ((sum > 0.1) ) { shading_Explored[a][b] = true; }
					 */

					if (alltorchlit[a][b] > 1) {
						alltorchlit[a][b] = 1;
					}
					if (alltorchlit[a][b] < 0) {
						alltorchlit[a][b] = 0;
					}

				}
			}

		}

	}

	boolean HeroCanSeeUnit(int mapX, int mapY) {

		boolean answer = false;

		if (define_regions[SharedData.REGION_INDOORS - 800][mapY / 4][mapX / 4]) {
			// can only see indoor units if you are right next to them or there
			// is a torch
			if (lit[mapY][mapX] > EXPLOREDMAP_BRIGHTNESS_INDOORS) {
				answer = true;
			}

		} else {
			// can see outdoor units if you just explored the area
			if (lit[mapY][mapX] > UNEXPLOREDMAP_BRIGHTNESS_OUTDOORS) {
				answer = true;
			}

		}

		return answer;

	}

	/*
	 * private void generateDMtorchlight(int torch_x, int torch_y, int
	 * brightness) { // no // Delighting double torchtilebrightness; double
	 * tiledist_fromtorch;
	 * 
	 * for (int a = (torch_y * 4) - 20; a <= (torch_y * 4) + 20; a++) { for (int
	 * b = (torch_x * 4) - 20; b <= (torch_x * 4) + 20; b++) { if (a > -1 && a <
	 * SharedData.MAP_SIZE * 4 && b > -1 && b < SharedData.MAP_SIZE * 4) {
	 * tiledist_fromtorch = Math.sqrt(Math.abs(Math.pow(a - (torch_y * 4), 2)) +
	 * Math.abs(Math.pow(b - (torch_x * 4) - 1, 2)));
	 * 
	 * torchtilebrightness = (0.08 * brightness) - 0.05 tiledist_fromtorch;
	 * 
	 * if (torchtilebrightness > 1) { torchtilebrightness = 1; } if
	 * (torchtilebrightness < 0) { torchtilebrightness = 0; }
	 * 
	 * double torch_pre_lit = 0; // double torch_pre_lit = lit[a][b] +
	 * torchtilebrightness; if (torchtilebrightness > lit[a][b]) { torch_pre_lit
	 * = torchtilebrightness; } else { torch_pre_lit = lit[a][b]; }
	 * 
	 * if (torch_pre_lit > 1) { torch_pre_lit = 1; } if (torch_pre_lit < 0) {
	 * torch_pre_lit = 0; }
	 * 
	 * if (torch_pre_lit > UNEXPLOREDMAP_BRIGHTNESS_INDOORS) {
	 * shading_Explored[a][b] = true; }
	 * 
	 * lit[a][b] = torch_pre_lit;
	 * 
	 * // sum = ((1-torchtilebrightness)*(lit[a][b]) + // torchtilebrightness);
	 * 
	 * // if(sum > 1){ lit[a][b] = 1;}else{lit[a][b]=sum;}
	 * 
	 * } } }
	 * 
	 * }
	 */

	// end NEXTTURN stuff

	/*
	 * //music playlist handling int ingamemusiccounter=0; ActionListener
	 * ingamemusicincrement= new ActionListener() { public void
	 * actionPerformed(ActionEvent evt) {
	 * System.out.println(ingamemusiccounter); if(ingamemusiccounter==60){
	 * ingamemusic_1.pause(); ingamemusic_2.resume(); }
	 * if(ingamemusiccounter>=60+72){ ingamemusiccounter=0;
	 * ingamemusic_2.pause(); ingamemusic_1.resume();
	 * }else{ingamemusiccounter++;}
	 * 
	 * 
	 * } };
	 */

	double animfactor = 100;

	int seamlessrockframe;

	// int ConsumeItemCooldown;

	void tileAnimationTimerTick() {

		animfactor = (delta * 100) / 15;

		if (animationframe >= 319) {
			animationframe = 0;
		} else {
			animationframe++;
		}

		if (animationframe % 4 == 0) {
			if (seamlessrockframe >= 1024) {
				seamlessrockframe = 0;
			} else {
				seamlessrockframe++;
			}
		}

		/*
		 * if (ConsumeItemCooldown <0) { ConsumeItemCooldown = 0; } else {
		 * ConsumeItemCooldown -= delta; }
		 */
	}

	// ActionListener tileSlideTimerTick= new ActionListener() { //every 10ms...
	// slide things and do procedural lighting!
	// public void actionPerformed(ActionEvent evt) {

	int procedural_lit[][] = new int[SharedData.MAP_SIZE * 4][SharedData.MAP_SIZE * 4];

	void tileSlideTimerTick() {
		for (int x = 0; x < SharedData.MAP_SIZE; x++) {
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {

				// slide units ALOT MORE CODE WILL BE HERE NOW
				int UnitID = UnitMap[x][y];
				if (UnitID > 0) {

					if (Units[UnitID].is_sliding) {
						// if(Units[UnitID].slidepixeloffset_x>0 ||
						// Units[UnitID].slidepixeloffset_y>0){
						if (Math.abs(Units[UnitID].slidepixeloffset_x) > Units[UnitID].animation_SlideSpeed * animfactor + 1
								|| Math.abs(Units[UnitID].slidepixeloffset_y) >= Units[UnitID].animation_SlideSpeed * animfactor + 1) {

							/*
							 * int start_x = Units[UnitID].slidetile_old_x; int
							 * start_y = Units[UnitID].slidetile_old_y; int
							 * end_x = Units[UnitID].x; int end_y =
							 * Units[UnitID].y;
							 */

							double dist_x = 0;
							if (Units[UnitID].slidepixeloffset_x > 0) {
								dist_x = 1;
							}
							if (Units[UnitID].slidepixeloffset_x < 0) {
								dist_x = -1;
							}

							double dist_y = 0;
							if (Units[UnitID].slidepixeloffset_y > 0) {
								dist_y = 1;
							}
							if (Units[UnitID].slidepixeloffset_y < 0) {
								dist_y = -1;
							}

							// double scalar = Math.sqrt(Math.pow(dist_x, 2) +
							// Math.pow(dist_y, 2));

							double dx = dist_x * Units[UnitID].animation_SlideSpeed;
							double dy = dist_y * Units[UnitID].animation_SlideSpeed;

							// dx = dx / scalar;// System.out.println(dx);
							// dy = dy / scalar;// System.out.println(dy);

							double newoffset_x = Units[UnitID].slidepixeloffset_x; // System.out.println(newoffset_x);
							double newoffset_y = Units[UnitID].slidepixeloffset_y;// System.out.println(newoffset_y);

							newoffset_x -= (dx * animfactor);// they move faster
																// if the
																// computer is
																// lagging
							newoffset_y -= (dy * animfactor);

							Units[UnitID].slidepixeloffset_x = newoffset_x;
							Units[UnitID].slidepixeloffset_y = newoffset_y;

							// Units[UnitID].is_sliding = true;

						} else {// upon reaching the destination...
							Units[UnitID].current_animation = 0;
							Units[UnitID].current_animation_frame = 0;
							Units[UnitID].slidepixeloffset_x = 0;
							Units[UnitID].slidepixeloffset_y = 0;
							Units[UnitID].is_sliding = false;
							Units[UnitID].is_recoiling = false;
						}

					}
				}

			}
		}

		// floating text
		for (int i = 0; i < NUMBER_OF_FLOATING_TEXT; i++) {

			if (myFloatingText[i].duration_left > 0) {
				myFloatingText[i].duration_left -= animfactor;
				if (myFloatingText[i].duration_left <= 0) {
					myFloatingText[i].duration_left = 0;
					// myFloatingText[i] = null;
				}

			}
		}

		for (int i = 0; i <= Number_of_Projectiles; i++) {
			if (Flying_Projectiles[i] != null) {
				if (Flying_Projectiles[i].IsActive == true) {
					if (Flying_Projectiles[i].creationdelay > 0) {
						Flying_Projectiles[i].creationdelay -= delta;
						if (Flying_Projectiles[i].creationdelay <= 0) {
							Flying_Projectiles[i].creationdelay = 0;
						}
					}

				}
			}
		}

		// move projectiles closer and closer to their end point, deactivate
		// them when they reach (and set a tile animation)
		for (int i = 0; i <= Number_of_Projectiles; i++) {
			if (Flying_Projectiles[i] != null) {
				if (Flying_Projectiles[i].IsActive == true && Flying_Projectiles[i].creationdelay == 0) {

					if (Math.abs(Flying_Projectiles[i].currentoffset_x) >= myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_speed
							* animfactor + 1
							|| Math.abs(Flying_Projectiles[i].currentoffset_y) >= myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_speed
									* animfactor + 1) {

						int start_x = Flying_Projectiles[i].start_mapX;
						int start_y = Flying_Projectiles[i].start_mapY;
						int end_x = Flying_Projectiles[i].end_mapX;
						int end_y = Flying_Projectiles[i].end_mapY;

						int dist_x = start_x - end_x;
						int dist_y = start_y - end_y;

						double totaldist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

						double dx = dist_x * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_speed;
						double dy = dist_y * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_speed;

						dx = dx / totaldist;// System.out.println(dx);
						dy = dy / totaldist;// System.out.println(dy);

						double newoffset_x = Flying_Projectiles[i].currentoffset_x; // System.out.println(newoffset_x);
						double newoffset_y = Flying_Projectiles[i].currentoffset_y;// System.out.println(newoffset_y);

						newoffset_x -= (dx * animfactor);// they move faster if
															// the
															// computer is
															// lagging
						newoffset_y -= (dy * animfactor);

						Flying_Projectiles[i].currentoffset_x = newoffset_x;
						Flying_Projectiles[i].currentoffset_y = newoffset_y;

						// arcing
						double dist_current_to_start = Math.sqrt(Math.pow((Flying_Projectiles[i].currentoffset_x - start_x), 2)
								+ Math.pow((Flying_Projectiles[i].currentoffset_y - start_y), 2));
						double dist_current_to_end = Math.sqrt(Math.pow((Flying_Projectiles[i].currentoffset_x - end_x), 2)
								+ Math.pow((Flying_Projectiles[i].currentoffset_y - end_y), 2));
						double pct_to_middle = 1 - Math.abs((dist_current_to_start / totaldist) - (dist_current_to_end / totaldist));

						Flying_Projectiles[i].currentoffset_y += myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_archeight * pct_to_middle;

						// lighting
						if (myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_luminescence > 0) {
							double current_x = end_x + (newoffset_x / TILE_SIZE / 100); // System.out.println("x:"+current_x);
							double current_y = end_y + (newoffset_y / TILE_SIZE / 100); // System.out.println("y:"+current_y);

							illuminate_shadepixel((int) (((current_x) * 4) + 0 /*- (dx/2)*/), (int) (((current_y) * 4) + 0 /*- (dy/2)*/),
									myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_luminescence);
						}

						// animationframe handling (works)
						if (myProjectileModels[myEffectDB[Flying_Projectiles[i].projeffect_ID].projectiletype].animationframes > 1) {
							if (Flying_Projectiles[i].animframe <= 0) {
								Flying_Projectiles[i].animframe = myProjectileModels[myEffectDB[Flying_Projectiles[i].projeffect_ID].projectiletype].animationframes - 1;
							} else {
								Flying_Projectiles[i].animframe--;
							}
						}

					} else {
						Flying_Projectiles[i].IsActive = false;
						// current_tileAnimation[Flying_Projectiles[i].end_mapX][Flying_Projectiles[i].end_mapY]
						// = Flying_Projectiles[i].deathanimation;

						if (myPNum == 1) {
							for (int n = 0; n < 5; n++) {
								if (myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_death_effect[n] > 10) {

									ExecuteEffectWhole(myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_death_effect[n],
											Flying_Projectiles[i].start_mapX, Flying_Projectiles[i].start_mapY, Flying_Projectiles[i].end_mapX,
											Flying_Projectiles[i].end_mapY);

								}

							}
						}
						/*
						 * if(Flying_Projectiles[i].death_GFX_ID > 0){
						 * 
						 * CreateOverlayGFXAnimation(Flying_Projectiles[i].end_mapX
						 * ,
						 * Flying_Projectiles[i].end_mapY,Flying_Projectiles[i].
						 * death_GFX_ID,
						 * Flying_Projectiles[i].death_GFX_R,Flying_Projectiles
						 * [i].death_GFX_G,Flying_Projectiles[i].death_GFX_B,
						 * Flying_Projectiles[i].death_GFX_Scale, 0,
						 * false,false);
						 * 
						 * } if(Flying_Projectiles[i].death_SOUND_ID > 0){
						 * playSound
						 * (mySoundPaths[Flying_Projectiles[i].death_SOUND_ID]);
						 * }
						 */

					}
				}
			}
		}// end check if active

		/*
		 * for(int i=0;i<Number_of_ParticleEmitters;i++){ for(int
		 * j=0;j<my_ParticleEmitters[i].quantity;j++){
		 * //if(already_updated_particles){ }lighitng
		 * if(my_ParticleEmitters[i].distance_from_origin[j]==0){
		 * my_ParticleEmitters[i].angle[j] = Math.random() * 6 ; }
		 * 
		 * 
		 * 
		 * if(my_ParticleEmitters[i].distance_from_origin[j] <
		 * my_ParticleEmitters[i].max_distance*100){
		 * my_ParticleEmitters[i].distance_from_origin[j]+=
		 * (my_ParticleEmitters[i].speed*animfactor)/100; }else{
		 * my_ParticleEmitters[i].distance_from_origin[j]=0; }
		 * 
		 * }}
		 */

	}

	// };

	void illuminate_shadepixel(int origin_x, int origin_y, int brightness) {
		double LightRadiance = 0.04 * brightness;
		if (LightRadiance > 1) {
			LightRadiance = 1;
		}

		for (int x = origin_x - 10; x <= origin_x + 10; x++) {
			for (int y = origin_y - 10; y <= origin_y + 10; y++) {
				if (x > -1 && x < SharedData.MAP_SIZE * 4 && y > -1 && y < SharedData.MAP_SIZE * 4) { // this
					// is
					// failing!

					// fix this algorithm!
					double shadepixeldist_origin = Math.sqrt(Math.abs(Math.pow(y - origin_y, 2)) + Math.abs(Math.pow(x - origin_x, 2)));

					double shadepixelbrightness = (150 * LightRadiance - (Math.pow(shadepixeldist_origin, 2))) / (250);

					if (shadepixelbrightness < 0) {
						shadepixelbrightness = 0;
					}

					if (shadepixelbrightness > 1) {
						shadepixelbrightness = 1;
					}

					if (shadepixelbrightness > 0) {
						procedural_lit[y][x] += shadepixelbrightness;
					}

					/*
					 * if (shadepixelbrightness > EXPLOREDMAP_BRIGHTNESS_INDOORS
					 * && stored_lit[y][x] < EXPLOREDMAP_BRIGHTNESS_INDOORS) {
					 * stored_lit[y][x] = EXPLOREDMAP_BRIGHTNESS_INDOORS;
					 * shading_Explored[y][x] = true; }// projectile
					 * illumination discovers fog of war
					 */

				}

			}
		}

	}

	void closeAllMenus() {

		int BigMenus[] = {MENU_INV, MENU_ESC, MENU_ABILITIES, MENU_EDITSTATS, MENU_LOOTING, MENU_ASSETTABLE, MENU_QUESTTRACKER, MENU_ITEMVENDOR,
				MENU_BATTLERESULTS};

		for (int i = 0; i < BigMenus.length; i++) {
			Menus[BigMenus[i]].is_open = false;
		}

	}

	void openGameMenu(int menuID) {

		boolean this_menu_is_already_open = Menus[menuID].is_open;

		int MenusToClose[] = {MENU_LOGIN, MENU_ESC, MENU_ABILITIES, MENU_EDITSTATS, MENU_QUESTTRACKER, MENU_ITEMVENDOR, MENU_BATTLERESULTS};

		for (int i = 0; i < MenusToClose.length; i++) {
			Menus[MenusToClose[i]].is_open = false;
		}

		if (menuID != MENU_ITEMVENDOR) {
			Menus[MENU_INV].is_open = false;
		}
		if (myPNum != 1 || menuID != MENU_INV) {
			Menus[MENU_ASSETTABLE].is_open = false;
		}
		if (menuID != MENU_ASSETTABLE) {
			Menus[MENU_LOOTING].is_open = false;
		}

		if (menuID == MENU_INV && UnitMap[cam.tileselect_x][cam.tileselect_y] > 0) {
			if (!this_menu_is_already_open) {
				if (myPNum == 1) {
					openGameMenu(MENU_ASSETTABLE);
				}
				Menus[MENU_INV].is_open = true;
				InvMenu_BonusStats_MustBeRefreshed = true;
			} // inv menu
		}

		if (menuID == MENU_ASSETTABLE) {
			if (!this_menu_is_already_open) {
				Menus[MENU_ASSETTABLE].is_open = true;
				assettablemenu_currentscreen = -1;
				Menus[MENU_ASSETTABLE].tweencount = Menus[MENU_ASSETTABLE].width;
				Menus[MENU_ASSETTABLE].x = 0 - Menus[MENU_ASSETTABLE].tweencount;
			} // inv menu
		}

		if (menuID == MENU_LOGIN) {
			if (!this_menu_is_already_open) {
				Menus[MENU_LOGIN].is_open = true;
				myTextBoxes[TEXTBOX_LOGINNAME].Has_Focus = true;
				Menus[MENU_LOGIN].tweencount = (Menus[MENU_LOGIN].height + Menus[MENU_LOGIN].y);
				Menus[MENU_LOGIN].x = SCREEN_WIDTH / 2 - Menus[MENU_LOGIN].width / 2 - Menus[MENU_LOGIN].tweencount;
			} // inv menu
		}

		if (menuID == MENU_LOOTING) {
			// if (!this_menu_is_already_open) {
			Menus[MENU_LOOTING].is_open = true;
			Menus[MENU_LOOTING].x = cursor.x;
			Menus[MENU_LOOTING].y = cursor.y;

			if (myPNum == 1) {
				openGameMenu(MENU_ASSETTABLE);
			}

			// }
		}

		if (menuID == MENU_ESC) {// esc menu

			if (!this_menu_is_already_open) {
				Menus[MENU_ESC].is_open = true;
				targetting_currentability = 0;
			}

		}

		if (menuID == MENU_QUESTTRACKER && myPNum == 1) {// esc menu

			if (!this_menu_is_already_open) {
				Menus[MENU_QUESTTRACKER].is_open = true;
			}

		}

		if (menuID == MENU_ITEMVENDOR && myPNum != 1) {// esc menu

			if (Menus[MENU_INV].is_open) {
				if (!this_menu_is_already_open && !BattlePhaseEngaged) {
					Menus[MENU_ITEMVENDOR].is_open = true;
					Menus[MENU_ITEMVENDOR].tweencount = Menus[MENU_ITEMVENDOR].width;
					Menus[MENU_ITEMVENDOR].width = 200 - Menus[MENU_ITEMVENDOR].tweencount;
				}
			}
		}

		if (menuID == MENU_BATTLERESULTS && myPNum != 1) {// esc menu
			Menus[MENU_BATTLERESULTS].tweencount = 200;
			if (!this_menu_is_already_open) {
				Menus[MENU_BATTLERESULTS].is_open = true;
			}

		}

		if (menuID == MENU_ABILITIES && myPNum != 1) {
			if (!this_menu_is_already_open) {
				Menus[MENU_ABILITIES].is_open = true;
				abilitymenu_pane = 0;

			}
		}

		// editstats menu
		if (menuID == MENU_EDITSTATS && myPNum == 1) {
			if (!this_menu_is_already_open) {
				Menus[MENU_EDITSTATS].is_open = true;

			}
		}

	}

	void openDialogMenu(int menuID) {

		if (menuID == DIALOG_RESPAWN) {

			if (myPNum != 1 && Units[myPNum].stat[SharedData.UNITDATA_HARDCOREMODE] == 0) {
				DialogMenus[menuID].is_open = true;
			}

		} else {

			DialogMenus[menuID].is_open = true;

		}
	}

	void closeDialogMenu(int menuID) {
		DialogMenus[menuID].is_open = false;
	}

	void executeDialogMenu(int menuID) {

		DialogMenus[menuID].is_open = false;

		if (menuID == DIALOG_RESPAWN && myPNum != 1) {

			networkThread_cli.sendMessage("/respawnmyhero");

		}

		if (menuID == DIALOG_CHOOSESPEC && myPNum != 1) {

			if (Units[myPNum].stat[SharedData.SPEC_1] == 0) {
				Client_EditStats(SharedData.SPEC_1, DialogMenus[DIALOG_CHOOSESPEC].mode, myPNum);
				abilitymenu_pane = DialogMenus[DIALOG_CHOOSESPEC].mode;
				playSound(mySoundPaths[SOUND_DRUMLOW]);
			} else if (Units[myPNum].stat[SharedData.SPEC_2] == 0) {
				Client_EditStats(SharedData.SPEC_2, DialogMenus[DIALOG_CHOOSESPEC].mode, myPNum);
				abilitymenu_pane = DialogMenus[DIALOG_CHOOSESPEC].mode;
				playSound(mySoundPaths[SOUND_DRUMLOW]);
			}

		}

	}

	void server_BroadcastHeroRespawn(int UnitID) {

		server_broadcastObjectMovement(myshareddata.PLAYER_LAYER, Units[UnitID].x, Units[UnitID].y, herospawnpoint_x[UnitID],
				herospawnpoint_y[UnitID], myshareddata.ANIM_STATIC, 10);
		Server_Broadcast_EditStats(SharedData.HEALTH, get_unit_maxhealth(UnitID), UnitID);
		Server_Broadcast_EditStats(SharedData.DEAD, 0, UnitID);
		Server_Broadcast_EditStats(SharedData.FACING, 2, UnitID);
	}
	void server_changephase() {
		int newphase = 1;
		if (BattlePhaseEngaged) {
			newphase = 0;
		}

		String[] s = {"" + newphase};
		GenericServerBroadcast("/beginphase", s);

		anyone_receive_changephase(newphase);

	}

	void anyone_receive_changephase(int newphase) {

		BattlePhase_CurrentTurn = 0;
		if (newphase == 0) {

			NewBattleRound();
			BattlePhaseEngaged = false;

			if (myBattleReport.num_units_slain > 0) {
				openGameMenu(MENU_BATTLERESULTS);
			}

			for (int i = 0; i < NUMBER_OF_UNITS; i++) {

				Units[i].stat[SharedData.ENERGY] = 100;

			}

		}

		if (newphase == 1) {
			BattlePhaseEngaged = true;

			closeAllMenus();
			NewBattleRound();
			if (myPNum != 1) {
				StartBattleRecord(myPNum);
			}
		}
		debug("New Phase: " + newphase);

		PlayRandomMusic();

	}
	final int AGGRO_RANGE = 10;

	void refreshCurrentlyBattlingUnits() {// called when a new round begins!
											// That is when dead units are
											// purged and created units are
											// added!!

		debug("refresh Battling Unit List");

		int[] NearbyUnits = new int[10000];
		number_of_found_battling_units = 0;

		boolean UnitAlreadyChosen[] = new boolean[10000];
		// one of the lines of code below is crashing the game

		for (int i = 2; i < 10; i++) {// populate NearbyUnits with all heroes
										// and units within 7 tiles of heroes
			if (Units[i] != null) {
				if (Players[i].IsConnected && Units[i].IsAlive()) {
					NearbyUnits[number_of_found_battling_units] = i;
					number_of_found_battling_units++;

					for (int x = Units[i].x - AGGRO_RANGE; x <= Units[i].x + AGGRO_RANGE; x++) {
						for (int y = Units[i].y - AGGRO_RANGE; y <= Units[i].y + AGGRO_RANGE; y++) {
							if (coordinatesWithinMapBounds(x, y)) {

								int UnitID = UnitMap[x][y];

								if (pythagorean(x, y, Units[i].x, Units[i].y) <= AGGRO_RANGE && UnitID >= 10) {
									if (Units[UnitID] != null) {
										if (Units[UnitID].stat[SharedData.DEAD] == 0 && !UnitAlreadyChosen[UnitID]) {

											UnitAlreadyChosen[UnitID] = true;
											NearbyUnits[number_of_found_battling_units] = UnitID;
											number_of_found_battling_units++;

										}
									}
								}

							}

						}
					}

				}
			}
		}

		BattlingUnits = new int[10000];
		int num_sorted = 0;

		for (int i = 0; i < number_of_found_battling_units; i++) {// for
																	// everything
																	// to be
																	// sorted

			boolean follows_everything = true;

			for (int j = 0; j < num_sorted; j++) {// for everything already
													// sorted

				int this_initiative = GetEffectiveUnitStat(NearbyUnits[i], SharedData.INITIATIVE, 0);
				int other_initiative = GetEffectiveUnitStat(BattlingUnits[j], SharedData.INITIATIVE, 0);

				if (this_initiative < other_initiative) {// /if already-placed
															// unit is faster,
															// keep goign down
															// the list..

					// KEEP GOIN

				}

				if (this_initiative > other_initiative) {// /if next unit is
															// slower then me,
															// STOP, I GO BEFORE
															// THEM,

					// /bump everything down

					for (int m = num_sorted; m > j; m--) {
						if (m < number_of_found_battling_units) {
							BattlingUnits[m] = BattlingUnits[m - 1];
							// output_IDs[m] = output_IDs[m-1];
						}
					}

					// insert the string in the middle, in the created empty gap
					BattlingUnits[j] = NearbyUnits[i];

					follows_everything = false;

				}

			}

			if (follows_everything) {

				BattlingUnits[num_sorted] = NearbyUnits[i];
				// throw this right at the end if it is found to precede
				// nothing!

			}

			num_sorted++;

		}

		// populate battlign units IN ORDER OF FASTNESS (like sorting
		// alphabetically)

		for (int i = 0; i < num_sorted; i++) {
			debug(BattlingUnits[i]);

		}

	}

	void UnitEndsBattleTurn(int UnitID) {
		targetting_currentability = 0;

		if (BattlingUnits[BattlePhase_ActiveUnit] == UnitID && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {

			GetUnitEndedBattleTurn();

		}

	}

	void GetUnitEndedBattleTurn() {

		if (myPNum == 1) {

			SimpleServerBroadcast("/activebattleunitenddedturn");
			ActivelyBattlingUnitEndedTurn();
		} else {

			networkThread_cli.sendMessage("/activebattleunitenddedturn");

		}

	}

	void ActivelyBattlingUnitEndedTurn() {

		if (BattlePhase_ActiveUnit < number_of_found_battling_units - 1) {

			BattlePhase_ActiveUnit++;

			if (Units[BattlingUnits[BattlePhase_ActiveUnit]].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
				cam.x = Units[BattlingUnits[BattlePhase_ActiveUnit]].x;
				cam.y = Units[BattlingUnits[BattlePhase_ActiveUnit]].y;
				cam.tileselect_x = Units[BattlingUnits[BattlePhase_ActiveUnit]].x;
				cam.tileselect_y = Units[BattlingUnits[BattlePhase_ActiveUnit]].y;
			}

			// if unit just died this round, skip it, it will be purged upon the
			// next round :D
			if (Units[BattlingUnits[BattlePhase_ActiveUnit]].stat[SharedData.DEAD] != 0) {
				ActivelyBattlingUnitEndedTurn();
			}

		} else {
			NewBattleRound();
		}

		// focus camera on next unit

	}

	void NewBattleRound() {

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {
			if (Units[i] != null) {
				if (Units[i].IsAlive()) {
					Units[i].stat[SharedData.ENERGY] = Units[i].stat[SharedData.STARTINGENERGY] + Units[i].stat[SharedData.ENERGYGROWTH]
							* BattlePhase_CurrentTurn;
					if (Units[i].stat[SharedData.ENERGY] > 100) {
						Units[i].stat[SharedData.ENERGY] = 100;
					}
				}
			}
		}

		BattlePhase_CurrentTurn++;

		if (myPNum == 1) {
			Server_NextTurn_AllUnitStats();
		}

		refreshCurrentlyBattlingUnits();
		BattlePhase_ActiveUnit = 0;

		if (number_of_found_battling_units > 0) {
			if (Units[BattlingUnits[BattlePhase_ActiveUnit]].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
				cam.x = Units[BattlingUnits[BattlePhase_ActiveUnit]].x;
				cam.y = Units[BattlingUnits[BattlePhase_ActiveUnit]].y;
				cam.tileselect_x = Units[BattlingUnits[BattlePhase_ActiveUnit]].x;
				cam.tileselect_y = Units[BattlingUnits[BattlePhase_ActiveUnit]].y;
			}
		}

	}

	void Server_NextTurn_AllUnitStats() {

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {

			if (Units[i] != null && Units[i].IsAlive()) {

				if (Units[i].stat[myshareddata.WALK_MOVES_COUNT] < get_unit_maxwalkmoves(i)) {
					Server_Broadcast_EditStats(myshareddata.WALK_MOVES_COUNT, get_unit_maxwalkmoves(i), i);
				}

				int healthregen = GetEffectiveUnitStat(i, myshareddata.HEALTHREGEN, 0);
				if (healthregen != 0 && Units[i].stat[myshareddata.HEALTH] < get_unit_maxhealth(i) && Units[i].stat[myshareddata.HEALTH] > 0) {
					Server_Broadcast_EditStats(myshareddata.HEALTH, Units[i].stat[myshareddata.HEALTH] + healthregen, i);
				}

			}

		}

		nextturn();

		SimpleServerBroadcast("/nextturn");

	}

	void Everyone_NextTurn_AllUnitStats() {

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {

			if (Units[i] != null) {

				for (int k = 0; k < Units[i].active_spell_cooldowns.length; k++) {
					if (Units[i].active_spell_cooldowns[k] > 0) {
						Units[i].active_spell_cooldowns[k]--;
					}
				}

				for (int k = 0; k < Units[i].active_skill_cooldowns.length; k++) {
					if (Units[i].active_skill_cooldowns[k] > 0) {
						Units[i].active_skill_cooldowns[k]--;
					}
				}

				if (Units[i].stat[SharedData.UNITDATA_TURNSLEFT] > 0) {// /remove
																		// corpse
																		// after
																		// being
																		// dead
																		// for a
																		// while
					Units[i].stat[SharedData.UNITDATA_TURNSLEFT]--;
					if (Units[i].stat[SharedData.UNITDATA_TURNSLEFT] == 0) {
						if (Units[i].stat[SharedData.DEAD] == 1) {
							UnitMap[Units[i].x][Units[i].y] = 0;
							Units[i] = new Unit();
						}

					}

				}

			}

		}

		InvMenu_BonusStats_MustBeRefreshed = true;

	}

	void Everyone_CountTimeForward() {

		if (MINUTES_OF_DAY == 0) {
			MINUTES_OF_DAY = 30;
		} else {

			MINUTES_OF_DAY = 0;

			if (HOUR_OF_DAY < 23) {
				HOUR_OF_DAY++;
			} else {
				HOUR_OF_DAY = 0;
			}

		}

	}

	void Everyone_CountdownConditions() {

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {
			if (Units[i] != null) {

				for (int k = 0; k < 100; k++) {
					if (Units[i].activeconditions[k] > 0) {

						Units[i].conditionstimeleft[k]--;
						if (myPNum == 1) {
							Server_ApplyUnitConditionEffects(i, k);
						}
						if (Units[i].conditionstimeleft[k] == 0) {

							RemoveUnitCondition(i, k);
							// ApplyHeroConditionEffects(i);
						}// refresh stat calculations from conditions for that
							// hero since their conditions changed
					}
				}// end 'k' loop

			}// end 'i' loop
		}
		// count down tile conditions
		for (int x = 0; x < SharedData.MAP_SIZE; x++) {
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {

				for (int k = 0; k < 100; k++) {
					if (tileactiveconditions[x][y][k] > 0 || tileconditionstimeleft[x][y][k] > 0) {
						tileconditionstimeleft[x][y][k]--;
						if (myPNum == 1) {
							Server_ApplyTileConditionEffects(x, y, k);
						}

						if (tileconditionstimeleft[x][y][k] <= 0) {
							RemoveTileCondition(x, y, k);// k is the slot! not
															// the condition!
						}

					}

				}

			}
		}

		// count down overlayGFX turns
		for (int i = 0; i < number_of_OverlayGFX; i++) {
			if (myOverlayGFX[i].enabled) {

				if (myOverlayGFX[i].duration_left > 0) {
					myOverlayGFX[i].duration_left--;
					if (myOverlayGFX[i].duration_left <= 0) {
						DeleteOverlayGFX(i);
					}
				}

				if (!myOverlayGFX[i].animate_continuously) {// just pulse these
															// at beginnings of
															// turns
					myOverlayGFX[i].currentframe = MAX_OVERLAY_GFX_FRAMES - 1;
				}

			}
		}

	}

	void StartBattleRecord(int UnitID) {
		myBattleReport = new BattleReport();

		// myBattleReport.initialstats = Units[UnitID].stat; This seems to be
		// passing a pointer!!

		for (int i = 0; i < SharedData.NUM_OF_UNITSTATS; i++) {
			myBattleReport.initialstats[i] = Units[UnitID].stat[i];
		}

	}

	String[] tempstring = new String[9];

	void server_receiveInitialUnitStats(int UnitID, int[] stats) {

		for (int i = 0; i < myshareddata.NUM_OF_UNITSTATS; i++) {

			Units[UnitID].stat[i] = stats[i];
		}

		Units[UnitID].current_model = GetEffectiveUnitStat(UnitID, myshareddata.MODEL_ID, 0);

		if (Units[UnitID].stat[SharedData.LEVEL] == 0) {// if the hero is new,
														// give em this stuff
			Server_Broadcast_EditStats(SharedData.LEVEL, 1, UnitID);
			Server_Broadcast_EditStats(SharedData.ABILITY_POINTS_AVAIL, 1, UnitID);
			Server_Broadcast_EditStats(SharedData.STAT_POINTS_AVAIL, 1, UnitID);

			Server_Broadcast_EditStats(SharedData.HEALTH, get_unit_maxhealth(UnitID), UnitID);

			for (int i = 0; i < assets_in_groups[ASSETTABLE_ITEMS][SharedData.ITEMTYPE_PLAYERDEFAULTS].length; i++) {

				int item_id = assets_in_groups[ASSETTABLE_ITEMS][SharedData.ITEMTYPE_PLAYERDEFAULTS][i];// add
				// all
				// starter
				// items

				Server_Broadcast_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, item_id, +1, false, 0);

			}

			for (int slot = 0; slot < 20; slot++) {
				TryToEquipItem(slot, UnitID, false);// do not broadcast, send in
				// Existing Hero
			}

			Units[UnitID].hero_who_is_brand_new = true;

		}

		if (BattlePhaseEngaged) {
			int currentenergy = Units[NUMBER_OF_UNITS].stat[SharedData.STARTINGENERGY] + Units[NUMBER_OF_UNITS].stat[SharedData.ENERGYGROWTH]
					* BattlePhase_CurrentTurn;;
			Server_Broadcast_EditStats(SharedData.ENERGY, currentenergy, UnitID);
		} else {
			Server_Broadcast_EditStats(SharedData.ENERGY, 100, UnitID);
		}

		// broadcast to clients
		String message = "/existingunitstats." + ForceStringLength("" + UnitID, 3) + ".";
		String s[] = new String[200];

		for (int n = 0; n < SharedData.NUM_OF_UNITSTATS; n++) {
			s[n] = "" + Units[UnitID].stat[n];

			s[n] = ForceStringLength(s[n], 3);

			message += s[n] + ",";

		}

		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}// end for

	}

	void server_receiveInitialUnitItems(int UnitID, int[] items) {
		if (!Units[UnitID].hero_who_is_brand_new) {
			for (int i = 0; i < 100; i++) {
				Units[UnitID].itemInSlot[i] = items[i];
			}
		}
	}

	void server_receiveInitialUnitItemQuantities(int UnitID, int[] quantities) {
		if (!Units[UnitID].hero_who_is_brand_new) {
			for (int i = 0; i < 100; i++) {
				Units[UnitID].itemQuantity[i] = quantities[i];
			}
		}
	}

	void client_receiveInitialUnitStats(int UnitID, int[] stats) {

		for (int i = 0; i < myshareddata.NUM_OF_UNITSTATS; i++) {

			Units[UnitID].stat[i] = stats[i];
		}

		Units[UnitID].current_model = GetEffectiveUnitStat(UnitID, myshareddata.MODEL_ID, 0);

		debug("Got Stats for " + UnitID + "." + Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP]);
		CalculateClientHeroLighting();

	}

	void client_receiveInitialBagItems(int UnitID, int[] items) {

		for (int i = 0; i < 30; i++) {
			Units[UnitID].itemInSlot[i] = items[i];
		}
	}

	void client_receiveInitialEquipment(int UnitID, int[] items) {

		for (int i = 50; i < 70; i++) {
			Units[UnitID].itemInSlot[i] = items[i - 50];
		}
	}

	/*
	 * void server_receiveClientEndTurn(int playernum) { // this message comes
	 * when // a client is ending // their turn!
	 * 
	 * 
	 * 
	 * Players[playernum].hasEndedTurn = true; //
	 * Units[myPNum].nextmove=direction_moving;
	 * 
	 * // Tell all the clients that THIS client ended their turn! for (int i =
	 * 0; i < 9; i++) { if (ShouldSendMessagesToPlayer(i)) {
	 * 
	 * try { networkThread_serv.t[i].sendMessage("/playerendedturn" +
	 * playernum); } catch (Exception e1) { e1.printStackTrace(); } } }// end
	 * for
	 * 
	 * }
	 */

	void Client_Send_PlayerChatMessage(String message) {

		if (networkThread_cli != null) {

			try {
				networkThread_cli.sendMessage("/chat" + myPNum + message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	String[] chathistorylines = new String[400];
	String[] storyloghistorylines = new String[40];
	int chat_display_timer = 0;
	int chatscrolloffset = 0;
	int storylog_display_timer = 0;

	void Server_Broadcast_PlayerChatMessage(int num, String message) {
		String pname = "";
		if (num == myPNum) {
			pname = Players[myPNum].name;
		} else {
			pname = Players[num].name;
			Server_Broadcast_UnitBubbleMessage(num, message);
		}
		addLineToChatHistory(pname + ": " + message);

		// Tell all the clients about this
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage("/chat" + Players[num].name + ": " + message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}// end for

	}

	void Server_Broadcast_UnitChatMessage(int UnitID, String message) {

		getUnitChatMessage(UnitID, message);

		String unitid6 = ForceStringLength("" + UnitID, 6);

		// Tell all the clients about this
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage("/unitchat" + unitid6 + ":" + message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}// end for

	}

	void getUnitChatMessage(int UnitID, String message) {

		addLineToChatHistory(GetUnitName(UnitID) + ": " + message);

	}

	void addLineToChatHistory(String nameandmessage) {

		for (int i = chathistorylines.length - 1; i > 0; i--) {
			chathistorylines[i] = chathistorylines[i - 1];
		}

		chathistorylines[0] = nameandmessage;
		chat_display_timer = 100000;
		chatscrolloffset = 0;
	}

	void client_sendnewstorylogmessage(String message) {

		if (networkThread_cli != null) {

			try {
				networkThread_cli.sendMessage("/storylog:" + message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	void serverbroadcast_printtostorylog(String message) {
		// String pname = Players[myPNum].name;

		addLineToStoryLogHistory(message);

		// Tell all the clients about this
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					/*
					 * networkThread_serv.t[i].sendMessage("/storylog" +
					 * Units[num].name + ": " + message);
					 */

					networkThread_serv.t[i].sendMessage("/storylog:" + message);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}// end for

	}

	void client_receivenewStoryLogmessage(String message) {

		addLineToStoryLogHistory(message);

	}

	void addLineToStoryLogHistory(String message) {

		// push all lines back
		for (int i = 20; i >= 0; i--) {
			storyloghistorylines[i + 1] = storyloghistorylines[i];
		}

		storyloghistorylines[0] = message;
		storylog_display_timer = 100000;
	}

	void Server_Broadcast_UnitBubbleMessage(int UnitID, String message) {

		setUnitChatBubbleMessage(UnitID, message);

		String unitid6 = ForceStringLength("" + UnitID, 6);

		// Tell all the clients about this
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage("/unitbubbletext" + unitid6 + ":" + message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}// end for

	}

	void setUnitChatBubbleMessage(int UnitID, String message) {

		Units[UnitID].chat_bubble_text = message;
		Units[UnitID].chat_bubble_timeleft = 2500 + message.length() * 150;

	}

	void server_broadcastObjectMovement(int layer, int start_x, int start_y, int end_x, int end_y, int animationtype, int SlideSpeed) {

		int UnitID = UnitMap[start_x][start_y];
		if (UnitID > 0) {

			if (start_x != end_x || start_y != end_y) {// if movement actually
														// occurs..

				/*
				 * //check for moving onto a warprift... for(int i=0;i<
				 * num_of_warprifts;i++){ if(warprift[i][0] == end_x &&
				 * warprift[i][1] == end_y && UnitMap[
				 * warprift[i][2]][warprift[i][3]]==0){end_x =
				 * warprift[i][2];end_y = warprift[i][3];SlideSpeed=10;}
				 * if(warprift[i][2] == end_x && warprift[i][3] == end_y &&
				 * UnitMap[ warprift[i][0]][warprift[i][1]]==0){end_x =
				 * warprift[i][0];end_y = warprift[i][1];SlideSpeed=10;} }
				 */

				tempstring[1] = "" + start_x;
				tempstring[2] = "" + start_y;
				tempstring[3] = "" + end_x;
				tempstring[4] = "" + end_y;
				tempstring[5] = "" + animationtype;
				tempstring[6] = "" + SlideSpeed;

				for (int n = 1; n <= 6; n++) {
					if (tempstring[n].length() == 1) {
						tempstring[n] = "00" + tempstring[n];
					} else if (tempstring[n].length() == 2) {
						tempstring[n] = "0" + tempstring[n];
					}
				}

				// Tell all the clients about this
				for (int i = 0; i < 9; i++) {
					if (ShouldSendMessagesToPlayer(i)) {

						try {
							networkThread_serv.t[i].sendMessage("/move.x" + tempstring[1] + ",y" + tempstring[2] + ".to.x" + tempstring[3] + ",y"
									+ tempstring[4] + "." + tempstring[5] + "." + tempstring[6]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};

					// "/move.x030,y040.to.x031,y050"

				}

				// store old position for animation purposes
				// Units[UnitID].slidetile_old_x = start_x;
				// Units[UnitID].slidetile_old_y = start_y;

				Units[UnitID].animation_SlideSpeed = SlideSpeed;

				Units[UnitID].current_animation = animationtype;

				if (animationtype != myshareddata.ANIM_STATIC) {
					Units[UnitID].slidepixeloffset_x = (start_x - end_x) * TILE_SIZE * 100;
					Units[UnitID].slidepixeloffset_y = (start_y - end_y) * TILE_SIZE * 100;
					Units[UnitID].is_sliding = true;
				}

				int newfacing = 0;
				double angle = 0;
				angle = Math.toDegrees(Math.atan2(start_x - end_x, start_y - end_y));
				if (angle < 0) {
					angle += 360;
				}

				// if walking, slide (apply the offset right away and diminish
				// it slowly)!
				if (angle <= 45 || angle > 315) {
					newfacing = 0;
				} // moved up
				if (angle > 225 && angle <= 315) {
					newfacing = 1;
				} // moved right
				if (angle > 135 && angle <= 225) {
					newfacing = 2;
				} // moved down
				if (angle > 45 && angle <= 135) {
					newfacing = 3;
				} // moved left

				if (animationtype == myshareddata.ANIM_WALK) {
					Units[UnitID].stat[myshareddata.FACING] = newfacing;
				}

				UnitMap[end_x][end_y] = UnitMap[start_x][start_y];
				UnitMap[start_x][start_y] = 0;

				// as well as moving the object for the clients, actually move
				// it for the server
				if (layer != myshareddata.PLAYER_LAYER) {
					map[layer][end_y][end_x] = map[layer][start_y][start_x];
					map[layer][start_y][start_x] = 0;
				}

				/*
				 * FloatingText[end_x][end_y] = FloatingText[start_x][start_y];
				 * FloatingText[start_x][start_y] = null;
				 * FloatingTextDuration[end_x][end_y] =
				 * FloatingTextDuration[start_x][start_y];
				 * FloatingTextDuration[start_x][start_y] = 0;
				 */

				// sticky overlay GFX
				for (int i = 0; i < number_of_OverlayGFX; i++) {
					if (myOverlayGFX[i].duration_left > 0 && myOverlayGFX[i].x == start_x && myOverlayGFX[i].y == start_y
							& myOverlayGFX[i].bound_to_unit) {
						myOverlayGFX[i].x = end_x;
						myOverlayGFX[i].y = end_y;
					}
				}

				Units[UnitID].x = end_x;
				Units[UnitID].y = end_y;

				if (cam.tileselect_x == start_x && cam.tileselect_y == start_y) {
					cam.tileselect_x = end_x;
					cam.tileselect_y = end_y;
				}

				if (UnitID > 1 && UnitID < 10) {// traps
					if (map[SharedData.SMALLOBJECTS_LAYER][end_y][end_x] == SharedData.PRESSUREPLATE_UP) {
						Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.PRESSUREPLATE_DOWN, 0, end_x, end_y);
						serverbroadcast_printtostorylog(GetUnitName(UnitID) + " activated a trap!");
					}

				}

			}

			DMLighting();

		}// end UnitID check
	}// end BroadcastObjectMovement

	void client_receiveObjectMovement(int layer, int start_x, int start_y, int end_x, int end_y, int animationtype, int SlideSpeed) {

		int UnitID = UnitMap[start_x][start_y];

		if (UnitID <= 0) {
			System.err.println("Null Unit ID!");
		}

		if (start_x != end_x || start_y != end_y && UnitID > 0) {

			// store old position for animation purposes
			// Units[UnitID].slidetile_old_x = start_x;
			// Units[UnitID].slidetile_old_y = start_y;

			Units[UnitID].animation_SlideSpeed = SlideSpeed;

			Units[UnitID].current_animation = animationtype;

			if (animationtype != myshareddata.ANIM_STATIC) {
				Units[UnitID].slidepixeloffset_x = (start_x - end_x) * TILE_SIZE * 100;
				Units[UnitID].slidepixeloffset_y = (start_y - end_y) * TILE_SIZE * 100;
				Units[UnitID].is_sliding = true;
			}

			int newfacing = 0;
			double angle = 0;
			angle = Math.toDegrees(Math.atan2(start_x - end_x, start_y - end_y));
			if (angle < 0) {
				angle += 360;
			}

			// if walking, slide (apply the offset right away and diminish it
			// slowly)!
			if (angle <= 45 || angle > 315) {
				newfacing = 0;
			} // moved up
			if (angle > 225 && angle <= 315) {
				newfacing = 1;
			} // moved right
			if (angle > 135 && angle <= 225) {
				newfacing = 2;
			} // moved down
			if (angle > 45 && angle <= 135) {
				newfacing = 3;
			} // moved left

			if (animationtype == myshareddata.ANIM_WALK) {
				Units[UnitID].stat[myshareddata.FACING] = newfacing;
			}

			UnitMap[end_x][end_y] = UnitMap[start_x][start_y];
			UnitMap[start_x][start_y] = 0;

			if (layer != myshareddata.PLAYER_LAYER) {
				map[layer][end_y][end_x] = map[layer][start_y][start_x];
				map[layer][start_y][start_x] = 0;
			}

			// sticky overlay GFX
			for (int i = 0; i < number_of_OverlayGFX; i++) {
				if (myOverlayGFX[i].duration_left > 0 && myOverlayGFX[i].x == start_x && myOverlayGFX[i].y == start_y & myOverlayGFX[i].bound_to_unit) {
					myOverlayGFX[i].x = end_x;
					myOverlayGFX[i].y = end_y;
				}
			}

			Units[UnitID].x = end_x;
			Units[UnitID].y = end_y;

			if (cam.tileselect_x == start_x && cam.tileselect_y == start_y) {
				cam.tileselect_x = end_x;
				cam.tileselect_y = end_y;
			}

			if (UnitID == myPNum) {
				CalculateClientHeroLighting();
			}

			// NPCs[start_x][start_y].stat[myshareddata.FACING]=2; //put start
			// back to default

		}
	}

	void Client_TellServer_SpawnObject(int layer, int type, int owner, int x, int y) {

		if (networkThread_cli != null) {
			networkThread_cli.sendMessage("/spawn." + ForceStringLength("" + layer, 3) + "," + ForceStringLength("" + type, 3) + "."
					+ ForceStringLength("" + owner, 3) + "." + ForceStringLength("" + x, 3) + "." + ForceStringLength("" + y, 3));

		}

	}

	void Server_BroadcastSpawnObject(int layer, int type, int owner, int x, int y) {

		String[] s = {"" + layer, "" + type, "" + owner, "" + x, "" + y};
		GenericServerBroadcast("/spawn", s);

		map[layer][y][x] = type;

	}

	void client_receiveSpawnObject(int layer, int type, int owner, int x, int y) {

		debug("Spawned Object!" + type);

		map[layer][y][x] = type;
		CalculateClientHeroLighting();
	}

	void server_broadcastDeleteObject(int layer, int x, int y) {

		String[] s = {"" + layer, "" + x, "" + y};
		GenericServerBroadcast("/delete", s);

		anyone_DeleteObject(layer, x, y);

	}

	void anyone_DeleteObject(int layer, int x, int y) {

		map[layer][y][x] = 0;

	}

	void client_gainexperience(int exp) {

		int newEXPValue = Units[myPNum].stat[myshareddata.EXP] + exp;

		if ((newEXPValue) >= (Units[myPNum].stat[myshareddata.LEVEL] * 10)) {
			do {
				newEXPValue -= (Units[myPNum].stat[myshareddata.LEVEL] * 10);
				Client_EditStats(myshareddata.LEVEL, (Units[myPNum].stat[myshareddata.LEVEL] + 1), myPNum);

			} while (newEXPValue >= (Units[myPNum].stat[myshareddata.LEVEL] * 10)); // Gaining
			// a
			// level!
		}

		Client_EditStats(myshareddata.EXP, (newEXPValue), myPNum);

	}

	void client_gaingold(int amount) {

		int newGoldAmount = Units[myPNum].stat[myshareddata.GOLD_CARRIED] + amount;

		Client_EditStats(myshareddata.GOLD_CARRIED, newGoldAmount, myPNum);

	}

	void Client_EditStats(int statToEdit, int newValue, int UnitID) {// client
																		// increases
		// their own stats
		// when they level
		// up
		if (newValue >= 0) {

			if (statToEdit == myshareddata.HEALTH && newValue > get_unit_maxhealth(UnitID)) {
				newValue = get_unit_maxhealth(UnitID);
			}// cap health increases out!
			if (statToEdit == SharedData.ENERGY && newValue > get_unit_maxenergy(UnitID)) {
				newValue = get_unit_maxenergy(UnitID);
			}

			Units[UnitID].stat[statToEdit] = newValue; // redundant, but good
														// for
														// speediness

			// after editing the stat for self, send it to server so the server
			// knows. The server will then automatically broadcast it to all
			// other clients and store it for itself.
			tempstring[0] = "" + statToEdit;
			tempstring[1] = "" + newValue;
			tempstring[2] = "" + UnitID;

			for (int n = 0; n <= 2; n++) {
				if (tempstring[n].length() == 1) {
					tempstring[n] = "00" + tempstring[n];
				} else if (tempstring[n].length() == 2) {
					tempstring[n] = "0" + tempstring[n];
				}
			}

			try {
				networkThread_cli.sendMessage("/editstats." + tempstring[0] + "." + tempstring[1] + "." + tempstring[2]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// /editstats.sss.vvv.xxx.yyy.aaa
		} else {

			debug("Tried to make a stat negative!!");
		}
	}

	void Server_Broadcast_EditStats(int statToEdit, int newValue, int UnitID) {

		if (newValue < 0) {
			newValue = 0;
			debug("Tried to broadcast a negative stat!");
		}// prevents the bugs! :D

		// int UnitID = UnitMap[x][y];
		if (UnitID > 0) {

			int oldValue = Units[UnitID].stat[statToEdit];

			if (UnitIsHero(UnitID)) {
				if (statToEdit == myshareddata.HEALTH && newValue > get_unit_maxhealth(UnitID)) {
					newValue = get_unit_maxhealth(UnitID);
				}// cap health increases out!
				if (statToEdit == SharedData.ENERGY && newValue > get_unit_maxenergy(UnitID)) {
					newValue = get_unit_maxenergy(UnitID);
				}
			} else {// the same for now...vv
				if (statToEdit == myshareddata.HEALTH && newValue > get_unit_maxhealth(UnitID)) {
					newValue = get_unit_maxhealth(UnitID);
				}// cap health increases out!
				if (statToEdit == SharedData.ENERGY && newValue > get_unit_maxenergy(UnitID)) {
					newValue = get_unit_maxenergy(UnitID);
				}
			}
			// levelling
			if (UnitID < 10) {
				// give points for levelling

				if (statToEdit == SharedData.LEVEL) {
					int difference = newValue - oldValue;
					for (int L = oldValue; L < newValue; L++) {
						// Client_EditStats(SharedData.STAT_POINTS_AVAIL,Units[myPNum].stat[SharedData.STAT_POINTS_AVAIL]
						// + 1);
						Server_Broadcast_EditStats(SharedData.STAT_POINTS_AVAIL, Units[UnitID].stat[SharedData.STAT_POINTS_AVAIL] + 1, UnitID);
						if (L % 2 == 0) {
							// Client_EditStats(SharedData.ABILITY_POINTS_AVAIL,Units[myPNum].stat[SharedData.ABILITY_POINTS_AVAIL]
							// + 1);
							Server_Broadcast_EditStats(SharedData.ABILITY_POINTS_AVAIL, Units[UnitID].stat[SharedData.ABILITY_POINTS_AVAIL] + 1,
									UnitID);
						}
					}

				}
			}

			String unitname = GetUnitName(UnitID);
			int string_index = GetStatStringIndex(statToEdit); // wrong
			if (string_index >= 0 && statToEdit != SharedData.DEAD && statToEdit != SharedData.FACING) {
				serverbroadcast_printtostorylog(unitname + "'s " + SharedData.ComboStrings[SharedData.COMBOTEXT_STATS][string_index] + " is now "
						+ newValue);
			}

			if (statToEdit == SharedData.HEALTH && newValue <= 0 && Units[UnitID].stat[SharedData.DEAD] == 0) {

				serverbroadcast_printtostorylog(unitname + " has fallen.");

				Broadcast_UnitDeath(Units[UnitID].x, Units[UnitID].y);

			}

			String[] s = {"" + statToEdit, "" + newValue, "" + Units[UnitID].x, "" + Units[UnitID].y};
			GenericServerBroadcast("/unitstatchange", s);

			receiveStatChange(statToEdit, newValue, Units[UnitID].x, Units[UnitID].y);

		}// end unitid check
	}

	void receiveStatChange(int statToEdit, int newValue, int x, int y) {

		int UnitID = UnitMap[x][y];

		int oldValue = Units[UnitID].stat[statToEdit];
		int difference = newValue - oldValue;

		Units[UnitID].stat[statToEdit] = newValue;

		if (statToEdit == myshareddata.HEALTH && difference != 0) {

			Create_New_Floating_Text("" + difference, x, y, 10000);

			if (difference < 0) {

				Create_New_ParticleEmitter(x, y, 4 - difference / 2, 1, 1, 1, 1);
			}
		}

		if (statToEdit == SharedData.DEAD && newValue > 0) {
			Units[UnitID].current_animation = SharedData.ANIM_DEATH;
			Units[UnitID].current_animation_frame = 4;
			Units[UnitID].stat[SharedData.UNITDATA_TURNSLEFT] = 8;
		}

		// if your own hero dies, show respawn dialog
		if (statToEdit == SharedData.DEAD && newValue > 0 && UnitID == myPNum) {
			openDialogMenu(DIALOG_RESPAWN);
		}

		if (statToEdit == SharedData.DEAD && newValue == 0 && UnitID == myPNum) {
			closeDialogMenu(DIALOG_RESPAWN);
			Units[UnitID].current_animation = 0;
			cam.x = Units[myPNum].x;
			cam.y = Units[myPNum].y;

		}

		InvMenu_BonusStats_MustBeRefreshed = true;

	}

	int NUMBER_OF_FLOATING_TEXT = 0;

	void Create_New_Floating_Text(String text, int x, int y, int duration) {

		int next_available_ID = -1;

		for (int i = 0; i < 1000; i++) {// recycle

			if (myFloatingText[i].duration_left <= 0) {
				next_available_ID = i;
				break;
			}

		}

		if (next_available_ID > -1) {
			myFloatingText[next_available_ID].duration_left = duration;
			myFloatingText[next_available_ID].text = text;
			myFloatingText[next_available_ID].x = x;
			myFloatingText[next_available_ID].y = y;
			myFloatingText[next_available_ID].offset_x = (int) (Math.random() * 20);
			myFloatingText[next_available_ID].offset_y = (int) (Math.random() * 20);

			NUMBER_OF_FLOATING_TEXT++;

		}

	}

	void server_broadcastPlayPositionalSound(int soundID, int x, int y, int volume) {
		if (soundID > 0) {
			if (soundID == SOUND_STEPGRASS_RANDOM) {
				soundID = 7 + (int) (Math.random() * 2);
			}

			String[] s = {"" + soundID, "" + x, "" + y, "" + volume};
			GenericServerBroadcast("/playpossound", s);

			PlayPositionalSound(soundID, x, y, volume);
		}
	}

	void PlayPositionalSound(int soundID, int x, int y, int volume) {

		int dist = pythagorean(cam.x, cam.y, x, y);

		if (coordinatesOnScreen(x, y)) {

			double scalar = 0.5 + ((double) (SCREEN_Y_TILES - (dist)) / (double) SCREEN_Y_TILES) / 2;
			if (scalar > 1) {
				scalar = 1;
			}
			if (scalar < 0.5) {
				scalar = 0.5;
			}
			debug(scalar);

			int newvol = (int) ((double) volume * scalar);
			playSound(mySoundPaths[soundID], newvol);
		}

	}

	void server_broadcastUnitSlide(int x, int y, int slide_x, int slide_y, int slidespeed) {

		String[] s = {"" + x, "" + y, "" + slide_x, "" + slide_y, "" + slidespeed};
		GenericServerBroadcast("/unitslide", s);

		SetUnitSlide(x, y, slide_x, slide_y, slidespeed);
	}

	void SetUnitSlide(int x, int y, int slide_x, int slide_y, int slidespeed) {

		int UnitID = UnitMap[x][y];

		if (Units[UnitID] != null) {
			Units[UnitID].slidepixeloffset_x = slide_x * 100;
			Units[UnitID].slidepixeloffset_y = slide_y * 100;
			Units[UnitID].animation_SlideSpeed = slidespeed;
			Units[UnitID].is_sliding = true;
			Units[UnitID].is_recoiling = true;
		}

	}

	void server_broadcastUnitAnimation(int x, int y, int animation) {

		String[] s = {"" + x, "" + y, "" + animation};
		GenericServerBroadcast("/unitanimation", s);

		StartUnitAnimation(x, y, animation);
	}

	void StartUnitAnimation(int x, int y, int animation) {// should be changed
															// to
															// regionalcondition

		int UnitID = UnitMap[x][y];

		if (Units[UnitID] != null) {
			Units[UnitID].current_animation = animation;
			Units[UnitID].current_animation_frame = 0;
		}
	}

	void sendAllExistingHeroesToAllPlayers() {// syncs stats and items for all
												// heroes with what the Host
												// thinks

		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {
				try {
					networkThread_serv.t[i].sendExistingHeroes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void tellserver_HeroJoinedGame(int num, String name, String descrip) {

		num_clientsconnected++;

		debug("A NEW HERO JOINED");

		for (int y = 0; y < SharedData.MAP_SIZE; y++) {
			for (int x = 0; x < SharedData.MAP_SIZE; x++) {

				if (map[myshareddata.PLAYER_LAYER][y][x] == ((myshareddata.HERO_2) - 2) + num) {

					Players[num].name = name;
					Players[num].herodescription = descrip;
					// Units[num].Is_Hero = true;
					Units[num].x = x;
					Units[num].y = y;

					// save spawnpoint for respawning upon death
					herospawnpoint_x[num] = x;
					herospawnpoint_y[num] = y;

					UnitMap[x][y] = num;
					if (num == myPNum) {
						cam.tileselect_x = x;
						cam.tileselect_y = y;
					}
					Units[num].stat[SharedData.PLAYER_OWNERSHIP] = num;

					Units[num].stat[SharedData.DEAD] = 0;
					Players[num].IsConnected = true;

					System.out.println("spawned hero " + (num) + " at " + x + "," + y);

				}

			}
		}

		// gets broadcast in 'existing heroes'

		/*
		 * // tell current clients about new client joining for (int i = 0; i <
		 * 9; i++) { if (ShouldSendMessagesToPlayer(i)) { try {
		 * networkThread_serv.t[i].sendMessage("/newplayerjoined." +
		 * ForceStringLength(""+num,3) + "."+
		 * ForceStringLength(""+Units[num].x,3) +","+
		 * ForceStringLength(""+Units[num].y,3) + name + "|" + descrip);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } }
		 */

		DMLighting();

	}

	void Client_GetExistingUnit(int UnitID, int x, int y) {
		Units[UnitID] = new Unit();
		Units[UnitID].stat[SharedData.DEAD] = 0;
		System.out.println("NPC" + UnitID);
		Units[UnitID].x = x;
		Units[UnitID].y = y;

		UnitMap[x][y] = UnitID;
		NUMBER_OF_UNITS++;
	}

	void tellclient_HeroExists(int num, int x, int y, String name, String descrip) {// fix
																					// MEEEEE

		Units[num] = new Unit();
		Players[num].name = name;
		Players[num].herodescription = descrip;

		Players[num].IsConnected = true;
		Units[num].stat[SharedData.DEAD] = 0;
		System.out.println("spawned hero " + (num) + " at " + x + "," + y);
		Units[num].x = x;
		Units[num].y = y;
		// Units[UnitID].Is_Hero = true;

		UnitMap[x][y] = num;

		if (num == myPNum) {
			cam.tileselect_x = x;
			cam.tileselect_y = y;
		}

	}

	void clientCouldNotJoinServer() {

		ShowWarningDialog("Failed to connect to server!");

	}

	void serverPortError() {

		// networkThread_serv.serverSocket = null;
		// networkThread_serv.clientSocket = null;
		// networkThread_serv = null;

		ShowWarningDialog("That port is already in use!");
		// maincore = new Core();
		current_MainMenu_Screen = MENUSCREEN_FRONT;
	}

	void tellserver_HeroLeftGame(int num) {

		// server_broadcastDeleteObject(SharedData.PLAYER_LAYER,Units[num].x,Units[num].y);
		receive_PlayerDisconnected(num);

		String[] s = {"" + num};
		GenericServerBroadcast("/playerdisconnected", s);

	}

	// this may be broken with multiple heroes
	void receive_PlayerDisconnected(int num) {

		int x = Units[num].x;
		int y = Units[num].y;

		Players[num].IsConnected = false;
		num_clientsconnected--;

		Units[num] = new Unit();
		UnitMap[x][y] = 0;

	}

	// /checks for having enough mana and being off cooldown and such
	void Anyone_ClickOnAbility(int clickedability, int casterID, boolean weaponskill) {
		// int clickedability =
		// Units[casterID].active_abilities[clickedbarslot];

		System.out.println(casterID + " clicked ability " + clickedability);

		int myenergycost = myAbilityDB[clickedability].getEffectiveEnergyCost(Units[casterID]);

		int cooldown = 0;
		if (weaponskill) {
			cooldown = Units[casterID].active_skill_cooldowns[clickedability];
		} else {
			cooldown = Units[casterID].active_spell_cooldowns[clickedability];

		}

		if (clickedability != 0
				&& cooldown == 0
				&& (UnitCanActivateAbilities(casterID) || myAbilityDB[clickedability].counts_as_a_movement)
				&& Units[casterID].stat[SharedData.ENERGY] >= myenergycost
				&& allbigmenus_notbeinghovered()
				&& targetting_currentability == 0
				&& !myAbilityDB[clickedability].passive
				&& (!myAbilityDB[clickedability].counts_as_an_attack || (UnitCanAttack(casterID)))
				&& (!myAbilityDB[clickedability].counts_as_a_movement || (UnitCanWalk(casterID)))
				&& (!BattlePhaseEngaged || BattlingUnits[BattlePhase_ActiveUnit] == casterID)
				&& (myAbilityDB[clickedability].weapon_required == myItemDB[Units[casterID].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].weapontype || myAbilityDB[clickedability].weapon_required == myItemDB[Units[casterID].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].weapontype)) {

			if (myAbilityDB[clickedability].requires_targetting) {
				targetting_currentability = clickedability;
				targetting_currentcaster = casterID;

			} else {
				Anyone_Broadcast_CastAbility(clickedability, Units[casterID].x, Units[casterID].y, Units[casterID].x, Units[casterID].y);
			}

		}

	}

	void Anyone_Broadcast_CastAbility(int ability, int start_x, int start_y, int end_x, int end_y) {

		debug("cast " + ability);

		int caster_id = UnitMap[start_x][start_y];

		// int direction = -1;
		if (myAbilityDB[ability].constrain_to_cardinal_direction) {
			double angle = 0;

			int override_shift_along_facing = myAbilityDB[ability].cardinal_direction_distance_override;
			int direction = DirectionBetweenCoordinates(start_x, start_y, end_x, end_y);

			if (direction == 0) {
				// direction = 0;
				end_x = start_x;
				if (override_shift_along_facing != 0) {
					end_y = start_y - override_shift_along_facing;
				}
			}
			if (direction == 1) {
				// direction = 1;
				end_y = start_y;
				if (override_shift_along_facing != 0) {
					end_x = start_x + override_shift_along_facing;
				}
			}
			if (direction == 2) {
				// direction = 2;
				end_x = start_x;
				if (override_shift_along_facing != 0) {
					end_y = start_y + override_shift_along_facing;
				}
			}
			if (direction == 3) {
				// direction = 3;
				end_y = start_y;
				if (override_shift_along_facing != 0) {
					end_x = start_x - override_shift_along_facing;
				}
			}

		}

		// int mid_x = end_x;
		// int mid_y = end_y;

		// make sure range is valid and target is valid

		boolean Range_and_Target_Valid = UnitAbletoCastAbility(ability, start_x, start_y, end_x, end_y);

		/*
		 * if (((Range_and_Target_Valid) ||
		 * !myAbilityDB[ability].requires_targetting) &&
		 * (!myAbilityDB[ability].require_targetpoint_walkable ||
		 * (terrain_IsWalkable( end_x, end_y)) && !object_IsNPC(end_x, end_y)))
		 * {
		 */

		if (Range_and_Target_Valid) {

			int mycooldown = myAbilityDB[ability].getEffectiveCooldown(Units[caster_id]);

			if (myAbilityDB[ability].abilityspec == 11) {

				Units[caster_id].active_skill_cooldowns[ability] = mycooldown;
			} else {

				Units[caster_id].active_spell_cooldowns[ability] = mycooldown;
			}

			targetting_currentability = 0;// right here, if the user clicked on
											// a green square when targetting,
											// their targetting sequence will
											// end
			// targetting_currentcaster = 0;
			// self

			// do all the insane targetting refinement here

			if (myPNum != 1) {

				tempstring[0] = "" + ability;
				tempstring[1] = "" + start_x;
				tempstring[2] = "" + start_y;
				tempstring[3] = "" + end_x;
				tempstring[4] = "" + end_y;

				for (int n = 0; n <= 4; n++) {
					if (tempstring[n].length() == 1) {
						tempstring[n] = "00" + tempstring[n];
					} else if (tempstring[n].length() == 2) {
						tempstring[n] = "0" + tempstring[n];
					}
				}

				try {
					networkThread_cli.sendMessage("/castability" + tempstring[0] + "." + tempstring[1] + "," + tempstring[2] + "." + tempstring[3]
							+ "," + tempstring[4]); // castabilityAAA.XXX,YYY.000,000

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {// if doing single player or are a DM...

				Server_ExecuteHeroAbility(ability, start_x, start_y, end_x, end_y);

			}

			// Auto end battle turn when an attack spell is cast!
			if (myAbilityDB[ability].counts_as_an_attack) {
				UnitEndsBattleTurn(caster_id);
			}

		}// end check for valid target and range
	}

	void activateDMObjectOption(int option) {

		int[] tempint = getDMOptionObject();

		int cursortype = tempint[0];
		int rows = tempint[1];

		if (cursortype == myshareddata.WELL_EMPTY) {

			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_RED, 0, cam.tileselect_x, cam.tileselect_y);
			}
			if (option == 2) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_BLUE, 0, cam.tileselect_x, cam.tileselect_y);
			}

		}

		if (cursortype == myshareddata.WELL_RED) {

			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_EMPTY, 0, cam.tileselect_x, cam.tileselect_y);
			}
			if (option == 2) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_BLUE, 0, cam.tileselect_x, cam.tileselect_y);
			}

		}

		if (cursortype == myshareddata.WELL_BLUE) {

			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_EMPTY, 0, cam.tileselect_x, cam.tileselect_y);
			}
			if (option == 2) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.WELL_RED, 0, cam.tileselect_x, cam.tileselect_y);
			}

		}

		if (cursortype == myshareddata.DOOR_H_OPEN) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.DOOR_H_CLOSED, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}
		if (cursortype == myshareddata.DOOR_H_CLOSED) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.LARGEOBJECTS_LAYER, myshareddata.DOOR_H_OPEN, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}
		if (cursortype == myshareddata.PRESSUREPLATE_UP) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.PRESSUREPLATE_DOWN, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}
		if (cursortype == myshareddata.PRESSUREPLATE_DOWN) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.PRESSUREPLATE_UP, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}
		if (cursortype == myshareddata.MOVEABLEBLOCK) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.HIDDENBLOCK, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}
		if (cursortype == myshareddata.HIDDENBLOCK) {
			if (option == 1) {
				Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.MOVEABLEBLOCK, 0, cam.tileselect_x, cam.tileselect_y);
			}
		}

	}

	// THIS WILL JUST RUN ALL OF THE EFFECTS OF THE REFINED ABILITY!!!
	void Server_ExecuteHeroAbility(int ability, int caster_x, int caster_y, int end_x, int end_y) {
		// Units or units

		int CasterUnitID = UnitMap[caster_x][caster_y];

		System.out.println("executing ability effects of UNITID:" + CasterUnitID);

		String unitname = GetUnitName(CasterUnitID);
		String abilityname = myAbilityDB[ability].namestring;
		if (BattlePhaseEngaged) {
			serverbroadcast_printtostorylog(unitname + " casts " + abilityname + ".");
		}

		// charge the mana cost
		if (BattlePhaseEngaged) {
			Server_Broadcast_EditStats(SharedData.ENERGY, Units[CasterUnitID].stat[SharedData.ENERGY] - myAbilityDB[ability].energy_cost_base,
					CasterUnitID);
		}

		int direction = -1;
		double angle = Math.toDegrees(Math.atan2(caster_x - end_x, caster_y - end_y));;

		while (angle < 0) {
			angle += 360;
		}

		if (angle <= 45 || angle > 315) {
			direction = 1;

		}
		if (angle > 225 && angle <= 315) {
			direction = 2;

		}
		if (angle > 135 && angle <= 225) {
			direction = 3;

		}
		if (angle > 45 && angle <= 135) {
			direction = 4;

		}

		/*
		 * if (myAbilityDB[ability].counts_as_an_attack) {
		 * Server_Broadcast_EditStats(myshareddata.ACTION_POINTS_COUNT,
		 * Units[CasterUnitID].stat[myshareddata.ACTION_POINTS_COUNT] - 1,
		 * CasterUnitID); }
		 */
		if (myAbilityDB[ability].counts_as_a_movement) {
			if (BattlePhaseEngaged) {
				Server_Broadcast_EditStats(myshareddata.WALK_MOVES_COUNT, Units[CasterUnitID].stat[myshareddata.WALK_MOVES_COUNT] - 1, CasterUnitID);
			}
			TryToPushMoveableBlock(Units[CasterUnitID].x, Units[CasterUnitID].y, direction);
		}

		if (myAbilityDB[ability].cast_animation > 0) {
			server_broadcastUnitAnimation(caster_x, caster_y, myAbilityDB[ability].cast_animation);

		}

		if (myAbilityDB[ability].requires_targetting) {
			Server_Broadcast_EditStats(SharedData.FACING, direction - 1, CasterUnitID);
		}

		for (int i = 0; i < myAbilityDB[ability].effects.length; i++) {// execute
																		// each
																		// effect

			int effectnum = myAbilityDB[ability].effects[i];

			ExecuteEffectWhole(effectnum, caster_x, caster_y, end_x, end_y);

		}
	}

	void Client_TellServer_ExecuteEffectWhole(int effectnum, int caster_x, int caster_y, int end_x, int end_y) {

		if (networkThread_cli != null) {
			networkThread_cli.sendMessage("/executeeffectwhole." + ForceStringLength("" + effectnum, 3) + "," + ForceStringLength("" + caster_x, 3)
					+ "." + ForceStringLength("" + caster_y, 3) + "." + ForceStringLength("" + end_x, 3) + "." + ForceStringLength("" + end_y, 3));

		}

	}

	void ExecuteEffectWhole(int effectnum, int caster_x, int caster_y, int end_x, int end_y) {

		double angle = Math.toDegrees(Math.atan2(caster_x - end_x, caster_y - end_y));;

		while (angle < 0) {
			angle += 360;
		}

		if (effectnum > 0) {
			if (myEffectDB[effectnum].enabled) {

				// this determines the coordinates that the effect will use.
				// When an ability is cast, three points are logged.
				// any given effect will only use two of those three points.

				int coords1_x = caster_x;
				int coords1_y = caster_y;
				int coords2_x = end_x;
				int coords2_y = end_y;

				if (myEffectDB[effectnum].flip_cast_coords) {// if using end
																// coords, flip
																// coordinates!
					int temp = coords1_x;
					coords1_x = coords2_x;
					coords2_x = temp;

					temp = coords1_y;
					coords1_y = coords2_y;
					coords2_y = temp;
				}

				if (myEffectDB[effectnum].trajectory_collision) {

					int[] tempcoordinates = new int[2];
					if (myEffectDB[effectnum].trajectory_endsontop) {

						tempcoordinates = Ability_FlyingObject_findEndSpotWithCollision(coords1_x, coords1_y, coords2_x, coords2_y);
						coords2_x = tempcoordinates[0];
						coords2_y = tempcoordinates[1];

					} else {// ends one space behind

						tempcoordinates = Ability_GroundObject_findEndSpotWithCollision(coords1_x, coords1_y, coords2_x, coords2_y);
						coords2_x = tempcoordinates[0];
						coords2_y = tempcoordinates[1];

					}

				}

				if (myEffectDB[effectnum].shape_type == EFFECT_SHAPE_POINT) {
					ExecuteEffectAtTile(effectnum, coords1_x, coords1_y, coords2_x, coords2_y);
				}

				if (myEffectDB[effectnum].action != EFFECT_ACTION_MOVEUNIT) {

					if (myEffectDB[effectnum].shape_type == EFFECT_SHAPE_LINE) {

						int moving_coord_x = coords1_x;
						int moving_coord_y = coords1_y;

						// int[] coordinates = new int[2];

						// coordinates[0] = coords2_x;
						// coordinates[1] = coords2_y;

						double angle3 = Math.toDegrees(Math.atan2(moving_coord_x - coords2_x, moving_coord_y - coords2_y));
						while (angle3 < 0) {
							angle3 += 360;
						}
						while (angle3 >= 360) {
							angle3 -= 360;
						}

						if (angle3 <= 45 || angle3 > 315) {
							moving_coord_y--;
						}
						if (angle3 > 225 && angle3 <= 315) {
							moving_coord_x++;
						}
						if (angle3 > 135 && angle3 <= 225) {
							moving_coord_y++;
						}
						if (angle3 > 45 && angle3 <= 135) {
							moving_coord_x--;
						}

						do {

							ExecuteEffectAtTile(effectnum, caster_x, caster_y, moving_coord_x, moving_coord_y);

							angle3 = Math.toDegrees(Math.atan2(moving_coord_x - coords2_x, moving_coord_y - coords2_y));
							while (angle3 < 0) {
								angle3 += 360;
							}
							while (angle3 >= 360) {
								angle3 -= 360;
							}

							if (angle3 <= 45 || angle3 > 315) {
								moving_coord_y--;
							}
							if (angle3 > 225 && angle3 <= 315) {
								moving_coord_x++;
							}
							if (angle3 > 135 && angle3 <= 225) {
								moving_coord_y++;
							}
							if (angle3 > 45 && angle3 <= 135) {
								moving_coord_x--;
							}

						} while ((coords2_x != moving_coord_x || coords2_y != moving_coord_y));

						// one last time...
						ExecuteEffectAtTile(effectnum, caster_x, caster_y, moving_coord_x, moving_coord_y);

					}

					if (myEffectDB[effectnum].shape_type == EFFECT_SHAPE_CONE) {
						// for(int x=0;x<SharedData.MAP_SIZE;x++){
						// for(int y=0;y<SharedData.MAP_SIZE;y++){
						int radius = myEffectDB[effectnum].shape_radius;

						for (int x = coords2_x - radius; x <= coords2_x + radius; x++) {
							for (int y = coords2_y - radius; y <= coords2_y + radius; y++) {
								if (x > 0 && x < SharedData.MAP_SIZE && y > 0 && y < SharedData.MAP_SIZE) {

									int spread = (myEffectDB[effectnum].shape_angle_spread / 2);

									double angle2 = Math.toDegrees(Math.atan2(coords2_x - x, coords2_y - y));
									double tile_dist = Math.sqrt(Math.abs(Math.pow(coords2_x - x, 2)) + Math.abs(Math.pow(coords2_y - y, 2)));

									double mydiff = Math.abs(angle - angle2);

									if ((fixDegrees(mydiff) % 360 <= fixDegrees(spread) || 360 - fixDegrees(mydiff) % 360 <= fixDegrees(spread))
											&& tile_dist <= radius) {

										ExecuteEffectAtTile(effectnum, caster_x, caster_y, x, y);

									}
								}
							}
						}
					}

					if (myEffectDB[effectnum].shape_type == EFFECT_SHAPE_SQUARE) {

						int radius = myEffectDB[effectnum].shape_radius;

						for (int x = coords2_x - radius; x <= coords2_x + radius; x++) {
							for (int y = coords2_y - radius; y < coords2_y + radius; y++) {

								if (x > 0 && x < SharedData.MAP_SIZE && y > 0 && y < SharedData.MAP_SIZE) {

									ExecuteEffectAtTile(effectnum, caster_x, caster_y, x, y);

								}

							}
						}
					}

				}
			}
		}
	}

	void ExecuteEffectAtTile(int effectnum, int start_x, int start_y, int end_x, int end_y) {// run
																								// only
																								// by
		if (effectnum > 0) {
			if (myEffectDB[effectnum].enabled) { // server!

				// getting effective stat changes should not always come from
				// just the
				// caster or just the target- need more parameters to choose!!!

				if (coordinatesWithinMapBounds(start_x, start_y) && coordinatesWithinMapBounds(end_x, end_y)) {

					int StartUnitID = UnitMap[start_x][start_y];
					int EndUnitID = UnitMap[end_x][end_y];

					int CasterID = StartUnitID;
					int TargetID = EndUnitID;
					if (myEffectDB[effectnum].flip_cast_coords) {// this is a
																	// fix!
						CasterID = EndUnitID;
						TargetID = StartUnitID;
					}
					/*
					 * int caster_x = coords1_x; int caster_y = coords1_y; int
					 * target_x = coords2_x; int target_y = coords2_y;
					 * 
					 * if (myEffectDB[effectnum].flip_cast_coords) { caster_x =
					 * coords2_x; caster_y = coords2_y; target_x = coords1_x;
					 * target_y = coords1_y; }
					 * 
					 * int CasterUnitID = UnitMap[caster_x][caster_y]; int
					 * TargetUnitID = UnitMap[target_x][target_y];
					 */

					boolean caster_exists = (CasterID > 0);
					boolean target_is_self = (CasterID == TargetID);
					boolean target_is_enemy = (Units[CasterID].stat[SharedData.PLAYER_OWNERSHIP] == 1 && Units[TargetID].stat[SharedData.PLAYER_OWNERSHIP] != 1)
							|| (Units[TargetID].stat[SharedData.PLAYER_OWNERSHIP] == 1 && Units[TargetID].stat[SharedData.PLAYER_OWNERSHIP] != 1);
					boolean target_is_ally = !target_is_enemy;

					boolean target_diplomacy_check = false;

					if (target_is_self) {
						if (myEffectDB[effectnum].can_affect_caster) {
							target_diplomacy_check = true;
						}
					} else {
						if (target_is_ally) {
							if (myEffectDB[effectnum].can_affect_allies) {
								target_diplomacy_check = true;
							}
						} else {
							if (target_is_enemy) {
								if (myEffectDB[effectnum].can_affect_enemies) {
									target_diplomacy_check = true;
								}
							}
						}

					}

					if (StartUnitID == 0 || EndUnitID == 0) {
						target_diplomacy_check = true;
					}

					// boolean use_caster_stats =
					// myEffectDB[effectnum].use_caster_stats;

					int UnitID_stats_to_use = StartUnitID;

					// if (Host_Override) { //no longer using host override
					if (myPNum == 1) {
						target_diplomacy_check = true;
						caster_exists = true;
					}

					// coords1 is normally the caster, coords 2 is normally the
					// target
					// point!

					debug("execute effect." + effectnum + "." + myEffectDB[effectnum].namestring);

					if (myEffectDB[effectnum].action == EFFECT_ACTION_MOVEUNIT) {

						server_broadcastObjectMovement(myshareddata.PLAYER_LAYER, start_x, start_y, end_x, end_y,
								myEffectDB[effectnum].movement_animation, myEffectDB[effectnum].movement_speed);
						server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, start_x, start_y, 100);
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_EDITSTATS) {

						if (caster_exists) {
							if (target_diplomacy_check) {

								server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);

								int statdeltas[] = new int[SharedData.NUM_OF_UNITSTATS];
								for (int n = 0; n < 5; n++) {

									if (myEffectDB[effectnum].use_caster_stats[n]) {
										UnitID_stats_to_use = CasterID;
									} else {
										UnitID_stats_to_use = TargetID;
									}

									// if(!myEffectDB[effectnum].stat_change_is_temp[n]
									// && UnitID_stats_to_use > 0){
									if (UnitID_stats_to_use > 0) {

										int statchange = myEffectDB[effectnum].stat_change_amount[n];

										// calc statchange
										if (myEffectDB[effectnum].stat_that_affects_change[n] > 0
												&& myEffectDB[effectnum].stat_that_affects_change_pct[n] > 0) {

											int affecting_stat_value = GetEffectiveUnitStat(UnitID_stats_to_use,
													myEffectDB[effectnum].stat_that_affects_change[n], 0);

											statchange += ((affecting_stat_value) * myEffectDB[effectnum].stat_that_affects_change_pct[n]) / 100;
										}

										// force bonus to not be able to change
										// the sign of the base value
										if (myEffectDB[effectnum].stat_change_amount[n] < 0 && statchange > 0) {
											statchange = 0;
										}
										if (myEffectDB[effectnum].stat_change_amount[n] > 0 && statchange < 0) {
											statchange = 0;
										}

										statdeltas[myEffectDB[effectnum].stat_to_edit[n]] += statchange;
										// int newstatvalue =
										// Units[TargetUnitID].stat[myEffectDB[effectnum].stat_to_edit[n]]
										// + statchange;
										// Server_Broadcast_EditStats(myEffectDB[effectnum].stat_to_edit[n],
										// newstatvalue, TargetUnitID);

									}
								}

								for (int i = 0; i < SharedData.NUM_OF_UNITSTATS; i++) {// this
																						// junk
																						// down
																						// here
																						// is
																						// done
																						// just
																						// for
																						// 'force
																						// negative
																						// edit'

									int this_stats_delta_sum = statdeltas[i];

									if (this_stats_delta_sum != 0) {

										if (this_stats_delta_sum < 0 || myEffectDB[effectnum].force_negative_edit == false) {// so
																																// armor
																																// cannot
																																// heal!
											int newstatvalue = Units[EndUnitID].stat[i] + this_stats_delta_sum;
											Server_Broadcast_EditStats(i, newstatvalue, EndUnitID);
										}

										if (i == SharedData.HEALTH && this_stats_delta_sum < 0) {// do
																									// cool
																									// tween
																									// effects
																									// when
																									// a
																									// unit
																									// loses
																									// health

											int slide_x = 0;
											int slide_y = 0;

											if (pythagorean(start_x, start_y, end_x, end_y) >= 1) {
												// double angle_rad =
												// Math.atan2(caster_x -
												// target_x,caster_y -
												// target_y);
												double angle_rad = Math.atan2(end_x - start_x, end_y - start_y);

												double sin = Math.sin(angle_rad);
												double cos = Math.cos(angle_rad);

												slide_x = (int) (sin * 20);
												slide_y = (int) (cos * 20);
											} else {
												slide_x = -5;
												slide_y = -10;
											}

											server_broadcastUnitSlide(Units[EndUnitID].x, Units[EndUnitID].y, slide_x, slide_y, 5);

											server_broadcastPlayPositionalSound(
											// myUnitModels[Units[EndUnitID].stat[myshareddata.MODEL_ID]].hurtsound_ID,
													myNPCDB[Units[EndUnitID].stat[myshareddata.UNITDATA_NPCTYPE]].sound_id, end_x, end_y, 100);
										}
										if (i == SharedData.HEALTH && this_stats_delta_sum > 0 && myEffectDB[effectnum].force_negative_edit) {

											Create_New_Floating_Text("Blocked!", Units[EndUnitID].x, Units[EndUnitID].y, 10000);
										}

									}
								}

							}
						}
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_APPLYCONDITION) {// only
																						// uses
																						// coords_1

						if (caster_exists || !myConditionDB[myEffectDB[effectnum].condition_ID].binds_to_units) {

							if (target_diplomacy_check && UnitID_stats_to_use > 0) {// stats_to_use
																					// ??

								int myduration = myEffectDB[effectnum].getEffectiveDuration(Units[UnitID_stats_to_use]);

								// myduration += (Units[CasterUnitID].stat[
								// myEffectDB[effectnum].stat_that_affects_duration
								// ] *
								// myEffectDB[effectnum].stat_that_affects_duration_pct
								// )/100;

								int myintensity = myEffectDB[effectnum].getEffectiveIntensity(Units[UnitID_stats_to_use]);
								// myintensity += (Units[CasterUnitID].stat[
								// myEffectDB[effectnum].stat_that_affects_intensity
								// ] *
								// myEffectDB[effectnum].stat_that_affects_intensity_pct
								// )/100;

								Server_BroadcastApplyCondition(myEffectDB[effectnum].condition_ID, myduration, myintensity, StartUnitID, end_x, end_y);
								server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);
							}
						}
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_DRAWPROJECTILE) {

						if (!myEffectDB[effectnum].projectile_requires_unit_on_tile || (EndUnitID > 0 && target_diplomacy_check)) {

							Server_BroadcastSpawnProjectile(effectnum, start_x, start_y, end_x, end_y);
							server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);
						}
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_CREATEUNIT) {

						Server_Broadcast_Create_New_NPC(myEffectDB[effectnum].unit_type, end_x, end_y,
								Units[CasterID].stat[SharedData.PLAYER_OWNERSHIP]);

						server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_OVERLAYGFX) {
						CreateOverlayGFXAnimation(end_x, end_y, myEffectDB[effectnum].Overlay_GFX_ID, myEffectDB[effectnum].Overlay_GFX_R,
								myEffectDB[effectnum].Overlay_GFX_G, myEffectDB[effectnum].Overlay_GFX_B, myEffectDB[effectnum].Overlay_GFX_Scale,
								myEffectDB[effectnum].duration_base, false, false);
						server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);
					}

					if (myEffectDB[effectnum].action == EFFECT_ACTION_SOUND) {
						server_broadcastPlayPositionalSound(myEffectDB[effectnum].SOUND_ID, end_x, end_y, 100);
					}

					poll_Unit_Special_Stats();

				}
			}
		}
	}

	/*
	 * void HostPlaceEffectAtTile(int effectnum, int coords1_x, int coords1_y,
	 * int coords2_x, int coords2_y){//run only by server!
	 * debug("Place an Effect!");///fix
	 * 
	 * //int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y]; aaa
	 * ExecuteEffectAtTile
	 * (cam.tileselect_x,cam.tileselect_y,effectnum,coords1_x
	 * ,coords1_y,coords2_x,coords2_y);
	 * 
	 * 
	 * }
	 */

	int number_of_OverlayGFX = 0;

	int CreateOverlayGFXAnimation(int x, int y, int ID, int r, int g, int b, int scale, int duration, boolean stackduration, boolean binds_to_unit) {

		int first_free_overlayGFX_spot = -1;

		int GFX_already_here = -1;
		for (int i = 0; i < 1000; i++) {
			if (myOverlayGFX[i].x == x && myOverlayGFX[i].y == y && myOverlayGFX[i].GFX_ID == ID) {
				GFX_already_here = i;
			}
		}

		if (GFX_already_here != -1 && stackduration && (binds_to_unit == myOverlayGFX[GFX_already_here].bound_to_unit)) {
			myOverlayGFX[GFX_already_here].duration_left += duration;
		} else {

			// first_free_overlayGFX_spot=-1;//recycling
			for (int i = 0; i < 1000; i++) {
				if (!myOverlayGFX[i].enabled) {
					first_free_overlayGFX_spot = i;
					break;
				}
			}

			// if(first_free_overlayGFX_spot >
			// number_of_OverlayGFX){number_of_OverlayGFX=first_free_overlayGFX_spot;}

			if (first_free_overlayGFX_spot > -1) {
				myOverlayGFX[first_free_overlayGFX_spot] = new OverlayGFX();
				myOverlayGFX[first_free_overlayGFX_spot].GFX_ID = ID;
				myOverlayGFX[first_free_overlayGFX_spot].r = r;
				myOverlayGFX[first_free_overlayGFX_spot].g = g;
				myOverlayGFX[first_free_overlayGFX_spot].b = b;
				myOverlayGFX[first_free_overlayGFX_spot].scale = scale;
				myOverlayGFX[first_free_overlayGFX_spot].x = x;
				myOverlayGFX[first_free_overlayGFX_spot].y = y;
				myOverlayGFX[first_free_overlayGFX_spot].duration_left = duration;
				myOverlayGFX[first_free_overlayGFX_spot].bound_to_unit = binds_to_unit;

				myOverlayGFX[first_free_overlayGFX_spot].currentframe = (int) (Math.random() * (MAX_OVERLAY_GFX_FRAMES - 1));
				myOverlayGFX[first_free_overlayGFX_spot].currentframe = 0; // for
																			// testing

				if (duration > 0) {
					myOverlayGFX[first_free_overlayGFX_spot].animate_continuously = true;
					debug("animating continuously!----------");
				} else {
					myOverlayGFX[first_free_overlayGFX_spot].currentframe = MAX_OVERLAY_GFX_FRAMES - 1;
				}

				myOverlayGFX[first_free_overlayGFX_spot].enabled = true;

				number_of_OverlayGFX++;

			}

		}
		return first_free_overlayGFX_spot;
	}

	void DeleteOverlayGFX(int i) {
		debug("overlayGFX Removed" + i);

		myOverlayGFX[i] = new OverlayGFX();
		// number_of_OverlayGFX--;
	}

	void Server_BroadcastSpawnProjectile(int effectnum, int start_x, int start_y, int end_x, int end_y) {

		SpawnProjectile(effectnum, start_x, start_y, end_x, end_y);// why

		String[] s = {"" + effectnum, "" + start_x, "" + start_y, "" + end_x, "" + end_y};
		GenericServerBroadcast("/projectile", s);

	}

	/*
	 * void Client_receiveSpawnProjectile(int projectiletype, int start_x, int
	 * start_y, int end_x, int end_y){
	 * 
	 * SpawnProjectile(projectiletype, start_x, start_y, end_x, end_y);
	 * 
	 * 
	 * }
	 */

	void SpawnProjectile(int effect_ID, int start_x, int start_y, int end_x, int end_y) {// recycles

		/*
		 * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectiletype
		 * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_rotates
		 * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_speed
		 * myEffectDB[Flying_Projectiles[i].projeffect_ID].projectile_archeight,
		 * myEffectDB
		 * [Flying_Projectiles[i].projeffect_ID].projectile_luminescence
		 */

		int nextOpenProjID = -1;
		int new_id = 0;

		for (int i = Number_of_Projectiles - 1; i >= 0; i--) {
			if (Flying_Projectiles[i].IsActive == false) {
				nextOpenProjID = i;
			}
		}

		if (nextOpenProjID == -1) {
			Flying_Projectiles[Number_of_Projectiles] = new Projectiles();
			new_id = Number_of_Projectiles;
		} else {
			new_id = nextOpenProjID;
		}

		debug("projectile created");

		Flying_Projectiles[new_id].projeffect_ID = effect_ID;
		// Flying_Projectiles[new_id].model = projmodel;// contains tons of
		// data!
		// death effects,
		// sounds, image
		// properties, etc
		Flying_Projectiles[new_id].creationdelay = 500;

		// Flying_Projectiles[new_id].speed = speed;
		// Flying_Projectiles[new_id].archeight = archeight;
		// Flying_Projectiles[new_id].lightradiation = luminescence;
		// Flying_Projectiles[new_id].rotates = rotates;
		Flying_Projectiles[new_id].start_mapX = start_x;
		Flying_Projectiles[new_id].start_mapY = start_y;
		Flying_Projectiles[new_id].end_mapX = end_x;
		Flying_Projectiles[new_id].end_mapY = end_y;
		Flying_Projectiles[new_id].currentoffset_x = (start_x - end_x) * TILE_SIZE * 100;
		Flying_Projectiles[new_id].currentoffset_y = (start_y - end_y) * TILE_SIZE * 100;

		// rotation handling (works)
		double angle = Math.toDegrees(Math.atan2(start_x - end_x, start_y - end_y));
		if (angle < 0) {
			angle += 360;
		}

		if (angle <= 22.5 || angle > 337.5) {
			Flying_Projectiles[new_id].facing = 2;
		}// end location is above, face up
		if (angle > 292.5 && angle <= 337.5) {
			Flying_Projectiles[new_id].facing = 3;
		}
		if (angle > 247.5 && angle <= 292.5) {
			Flying_Projectiles[new_id].facing = 4;
		}
		if (angle > 202.5 && angle <= 247.5) {
			Flying_Projectiles[new_id].facing = 5;
		}
		if (angle > 157.5 && angle <= 202.5) {
			Flying_Projectiles[new_id].facing = 6;
		}
		if (angle > 112.5 && angle <= 157.5) {
			Flying_Projectiles[new_id].facing = 7;
		}
		if (angle > 67.5 && angle <= 112.5) {
			Flying_Projectiles[new_id].facing = 0;
		}
		if (angle > 22.5 && angle <= 67.5) {
			Flying_Projectiles[new_id].facing = 1;
		}

		Flying_Projectiles[new_id].IsActive = true;

		if (nextOpenProjID == -1) {
			Number_of_Projectiles++;
		}

	}

	void Server_BroadcastApplyCondition(int condition, int duration, int intensity, int CasterID, int x, int y) {

		/*
		 * if(myConditionDB[condition].binds_to_units){
		 * 
		 * int UnitID = UnitMap[x][y]; if (UnitID > 0) {// make sure a unit is
		 * in that spot!
		 * 
		 * AddConditionToUnit(condition,duration,intensity,UnitID); }
		 * 
		 * }else{
		 * 
		 * AddConditionToTile(condition,duration,intensity,x,y);
		 * 
		 * }
		 */

		receiveApplyCondition(condition, duration, intensity, CasterID, x, y);

		String[] s = {"" + condition, "" + duration, "" + intensity, "" + CasterID, "" + x, "" + y};
		GenericServerBroadcast("/applycondition", s);

		// /applyconditionCCC.DDD.III.XXX,YYY

	}

	void receiveApplyCondition(int condition, int duration, int intensity, int CasterID, int x, int y) {

		if (myConditionDB[condition].binds_to_units) {

			int UnitID = UnitMap[x][y];
			if (UnitID > 0) {// make sure a unit is in that spot!
				debug("DOLPHEEN");
				AddConditionToUnit(condition, duration, intensity, CasterID, UnitID);
			}

		} else {

			AddConditionToTile(condition, duration, intensity, CasterID, x, y);

		}

	}

	/*
	 * void ApplyConditionEffects(int UnitID, int condition_ID, int
	 * conditiontimeleft) {//this needs more work I thinK!
	 * 
	 * 
	 * for(int i = 0;i<5;i++){ int stat =
	 * myConditionDB[condition_ID].stat_to_increase[i]; if(stat > 0){
	 * 
	 * int change = myConditionDB[condition_ID].stat_bonus_amount[i] +
	 * (Units[myPNum
	 * ].stat[myConditionDB[condition_ID].stat_affecting_amount[i]]*
	 * myConditionDB[condition_ID].stat_affecting_amount_factor[i]);
	 * 
	 * Server_Broadcast_EditStats(stat , Units[UnitID].stat[stat] + change ,
	 * UnitID); } }
	 * 
	 * // more cool condition effects go here!!! }
	 */

	void AddConditionToUnit(int condition, int duration, int intensity, int CasterID, int UnitID) { // fix
																									// this
																									// to
																									// use
																									// the
																									// new
																									// condition
																									// array
																									// (then
																									// do
																									// same
																									// thing
																									// with
																									// tiles)

		boolean GFX_stacks_duration = false;

		int condition_already_on_unit = -1;
		for (int i = 0; i < 100; i++) {
			if (Units[UnitID].activeconditions[i] == condition) {
				condition_already_on_unit = i;
			}
		}

		if (condition_already_on_unit > -1) {

			if (myConditionDB[condition].stacks_duration) {
				Units[UnitID].conditionstimeleft[condition_already_on_unit] += duration;
				Units[UnitID].conditions_source[condition_already_on_unit] = CasterID;
				GFX_stacks_duration = true;
			}
			if (myConditionDB[condition].stacks_magnitude) {
				Units[UnitID].conditionsintensity[condition_already_on_unit] += intensity;
				Units[UnitID].conditions_source[condition_already_on_unit] = CasterID;
			}

		} else {

			for (int i = 99; i > 0; i--) {// bump all active conditions down
				Units[UnitID].activeconditions[i] = Units[UnitID].activeconditions[i - 1];
				Units[UnitID].conditionstimeleft[i] = Units[UnitID].conditionstimeleft[i - 1];
				Units[UnitID].conditionsintensity[i] = Units[UnitID].conditionsintensity[i - 1];
				Units[UnitID].conditions_source[i] = Units[UnitID].conditions_source[i - 1];
			}

			Units[UnitID].activeconditions[0] = condition;
			Units[UnitID].conditionstimeleft[0] = duration;
			Units[UnitID].conditionsintensity[0] = intensity;
			Units[UnitID].conditions_source[0] = CasterID;
		}

		if (myConditionDB[condition].Overlay_GFX_ID > 0) {
			CreateOverlayGFXAnimation(Units[UnitID].x, Units[UnitID].y, myConditionDB[condition].Overlay_GFX_ID,
					myConditionDB[condition].Overlay_GFX_R, myConditionDB[condition].Overlay_GFX_G, myConditionDB[condition].Overlay_GFX_B,
					myConditionDB[condition].Overlay_GFX_Scale, duration, GFX_stacks_duration, true);

		}

		if (myConditionDB[condition].SOUND_ID > 0) {
			playSound(mySoundPaths[myConditionDB[condition].SOUND_ID]);
		}

	}

	int[][][] tileactiveconditions = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE][100];
	int[][][] tileconditionstimeleft = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE][100];
	int[][][] tileconditionsintensity = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE][100];
	int[][][] tileconditions_source = new int[SharedData.MAP_SIZE][SharedData.MAP_SIZE][100];

	void AddConditionToTile(int condition, int duration, int intensity, int CasterID, int x, int y) {// FIX
																										// ME

		boolean GFX_stacks_duration = false;

		int condition_already_on_tile = -1;
		for (int i = 0; i < 100; i++) {
			if (tileactiveconditions[x][y][i] == condition) {
				condition_already_on_tile = i;
			}
		}

		if (condition_already_on_tile > -1) {

			if (myConditionDB[condition].stacks_duration) {
				tileconditionstimeleft[x][y][condition_already_on_tile] += duration;
				tileconditions_source[x][y][condition_already_on_tile] = CasterID;
				GFX_stacks_duration = true;
			}
			if (myConditionDB[condition].stacks_magnitude) {
				tileconditionsintensity[x][y][condition_already_on_tile] += intensity;
				tileconditions_source[x][y][condition_already_on_tile] = CasterID;
			}

		} else {

			for (int i = 1; i < 99; i++) {// bump all active conditions down
				tileactiveconditions[x][y][i] = tileactiveconditions[x][y][i - 1];
				tileconditionstimeleft[x][y][i] = tileconditionstimeleft[x][y][i - 1];
				tileconditionsintensity[x][y][i] = tileconditionsintensity[x][y][i - 1];
				tileconditions_source[x][y][i] = tileconditions_source[x][y][i - 1];
			}

			tileactiveconditions[x][y][0] = condition;
			tileconditionstimeleft[x][y][0] = duration;
			tileconditionsintensity[x][y][0] = intensity;
			tileconditions_source[x][y][0] = CasterID;

		}

		if (myConditionDB[condition].Overlay_GFX_ID > 0) {
			CreateOverlayGFXAnimation(x, y, myConditionDB[condition].Overlay_GFX_ID, myConditionDB[condition].Overlay_GFX_R,
					myConditionDB[condition].Overlay_GFX_G, myConditionDB[condition].Overlay_GFX_B, myConditionDB[condition].Overlay_GFX_Scale,
					duration, GFX_stacks_duration, false);
			debug("created cond overlay gfx, unbound" + duration);
		}

		if (myConditionDB[condition].SOUND_ID > 0) {
			playSound(mySoundPaths[myConditionDB[condition].SOUND_ID]);
		}

	}

	void RemoveTileCondition(int x, int y, int conditionslot) {
		debug("tile condition removed" + conditionslot);

		tileactiveconditions[x][y][conditionslot] = 0;
		tileconditionstimeleft[x][y][conditionslot] = 0;
		tileconditionsintensity[x][y][conditionslot] = 0;
		tileconditions_source[x][y][conditionslot] = 0;

		for (int k = 99; k > conditionslot; k--) {// slide all conditions after
													// it back!

			tileactiveconditions[x][y][k - 1] = tileactiveconditions[x][y][k];
			tileconditionstimeleft[x][y][k - 1] = tileconditionstimeleft[x][y][k];
			tileconditionsintensity[x][y][k - 1] = tileconditionsintensity[x][y][k];
			tileconditions_source[x][y][k - 1] = tileconditions_source[x][y][k];
		}
	}

	void RemoveUnitCondition(int UnitID, int condition) {

		Units[UnitID].activeconditions[condition] = 0;
		Units[UnitID].conditionstimeleft[condition] = 0;
		Units[UnitID].conditionsintensity[condition] = 0;
		Units[UnitID].conditions_source[condition] = 0;

		for (int k = 99; k > condition; k--) {// slide all conditions after it
												// back!

			Units[UnitID].activeconditions[k - 1] = Units[UnitID].activeconditions[k];
			Units[UnitID].conditionstimeleft[k - 1] = Units[UnitID].conditionstimeleft[k];
			Units[UnitID].conditionsintensity[k - 1] = Units[UnitID].conditionsintensity[k];
			Units[UnitID].conditions_source[k - 1] = Units[UnitID].conditions_source[k];
		}

	}

	void Server_ApplyUnitConditionEffects(int UnitID, int slot) {// Only the
																	// server
																	// will do
																	// this!!

		int condition_ID = Units[UnitID].activeconditions[slot];
		int conditiontimeleft = Units[UnitID].conditionstimeleft[slot];

		int caster_ID = Units[UnitID].conditions_source[slot];

		int caster_x = Units[caster_ID].x;
		int caster_y = Units[caster_ID].y;

		// changes stats (usually health) based on non-temporary condition stat
		// changes

		for (int n = 0; n < 5; n++) {// for all 5 possible periodic effects
			int effect = myConditionDB[condition_ID].periodic_effect[n];
			if (effect > 0) {

				ExecuteEffectWhole(effect, Units[caster_ID].x, Units[caster_ID].y, Units[UnitID].x, Units[UnitID].y);

				/*
				 * if(myEffectDB[effect].action==EFFECT_ACTION_EDITSTATS){//for
				 * all 5 possible statedits of each periodic effect for(int
				 * j=0;j<5;j++){ int stat = myEffectDB[effect].stat_to_edit[j];
				 * 
				 * //if (stat > 0 && !myEffectDB[effect].stat_change_is_temp[j])
				 * { if (stat > 0 ) { int change =
				 * myEffectDB[effect].stat_change_amount[j];
				 * 
				 * int UnitIDs_stat_source = UnitID; if
				 * (myEffectDB[effect].use_caster_stats[j]) {
				 * UnitIDs_stat_source = Units[UnitID].conditions_source[slot];
				 * } if (UnitIDs_stat_source > 0) { change +=
				 * (Units[UnitIDs_stat_source
				 * ].stat[myEffectDB[effect].stat_that_affects_change[j]] *
				 * myEffectDB[effect].stat_that_affects_change_pct[j])/100; }
				 * 
				 * Server_Broadcast_EditStats(stat, Units[UnitID].stat[stat] +
				 * change, UnitID);
				 * 
				 * 
				 * 
				 * 
				 * } } }
				 */
			}

		}

	}

	void Server_ApplyTileConditionEffects(int x, int y, int slot) {// Only the
																	// server
																	// will do
																	// this!!

		int condition_ID = tileactiveconditions[x][y][slot];
		int conditiontimeleft = tileconditionstimeleft[x][y][slot];

		int caster_ID = tileconditions_source[x][y][slot];

		int caster_x = Units[caster_ID].x;
		int caster_y = Units[caster_ID].y;

		int UnitID = UnitMap[x][y];
		if (UnitID > 0) {

			for (int n = 0; n < 5; n++) {// for all 5 possible periodic effects
				int effect = myConditionDB[condition_ID].periodic_effect[n];
				if (effect > 0) {

					ExecuteEffectWhole(effect, Units[caster_ID].x, Units[caster_ID].y, Units[UnitID].x, Units[UnitID].y);

					/*
					 * if(myEffectDB[effect].action==EFFECT_ACTION_EDITSTATS){//for
					 * all 5 possible statedits of each periodic effect for(int
					 * j=0;j<5;j++){ int stat =
					 * myEffectDB[effect].stat_to_edit[j];
					 * 
					 * //if (stat > 0 &&
					 * !myEffectDB[effect].stat_change_is_temp[j]) { if (stat >
					 * 0 ) { int change =
					 * myEffectDB[effect].stat_change_amount[j];
					 * 
					 * int UnitIDs_stat_source = UnitID; if
					 * (myEffectDB[effect].use_caster_stats[j]) {
					 * UnitIDs_stat_source = tileconditions_source[x][y][slot];
					 * } if (UnitIDs_stat_source > 0) { change +=
					 * (Units[UnitIDs_stat_source
					 * ].stat[myEffectDB[effect].stat_that_affects_change[j]] *
					 * myEffectDB[effect].stat_that_affects_change_pct[j])/100;
					 * }
					 * 
					 * Server_Broadcast_EditStats(stat, Units[UnitID].stat[stat]
					 * + change, UnitID);
					 * 
					 * 
					 * 
					 * 
					 * } } }
					 */
				}

			}

		}
	}

	public int[] Ability_Teleport_findOpenLocation(int start_x, int start_y, int end_x, int end_y) {// this
																									// probably
																									// doesn't
																									// work,
																									// but
																									// we
																									// shall
																									// see

		int[] coordinates = new int[2];

		coordinates[0] = end_x;
		coordinates[1] = end_y;

		// change the coordinates so they will be in an acceptable and non-buggy
		// location, keep moving toward the start location to find a good spot

		if (Unit_At_Tile(coordinates[0], coordinates[1]) == true || terrain_IsWalkable(coordinates[0], coordinates[1]) == false) {
			do {

				double angle = Math.toDegrees(Math.atan2(start_x - coordinates[0], start_y - coordinates[1]));
				if (angle < 0) {
					angle += 360;
				}

				if (angle <= 45 || angle > 315) {
					coordinates[1]++;
				}// end location is above hero, so keep moving the location
					// south
				if (angle > 225 && angle <= 315) {
					coordinates[0]--;
				}// end location is right of hero, so keep moving the location
					// left
				if (angle > 135 && angle <= 225) {
					coordinates[1]--;
				}
				if (angle > 45 && angle <= 135) {
					coordinates[0]++;
				}

			} while ((Unit_At_Tile(coordinates[0], coordinates[1]) == true || terrain_IsWalkable(coordinates[0], coordinates[1]) == false)
					&& (start_x != coordinates[0] || start_y != coordinates[1]));

		}

		return coordinates;// returns end coordinates

	}

	public int[] Ability_FlyingObject_findEndSpotWithCollision(int start_x, int start_y, int end_x, int end_y) {// MAKE
																												// SURE
																												// THIS
																												// WORKS
		int[] coordinates = new int[2];

		coordinates[0] = start_x;
		coordinates[1] = start_y;

		double angle = Math.toDegrees(Math.atan2(coordinates[0] - end_x, coordinates[1] - end_y));
		if (angle < 0) {
			angle += 360;
		}
		if (angle <= 45 || angle > 315) {
			coordinates[1]--;
		}
		if (angle > 225 && angle <= 315) {
			coordinates[0]++;
		}
		if (angle > 135 && angle <= 225) {
			coordinates[1]++;
		}
		if (angle > 45 && angle <= 135) {
			coordinates[0]--;
		}

		if (Unit_At_Tile(coordinates[0], coordinates[1]) == false && terrain_IsHoverable(coordinates[0], coordinates[1]) == true) { // creeps
																																	// the
																																	// end
																																	// points
																																	// up
																																	// little
																																	// by
																																	// little
																																	// and
																																	// stops
																																	// if
																																	// a
																																	// wall
																																	// is
																																	// hit
			do {

				angle = Math.toDegrees(Math.atan2(coordinates[0] - end_x, coordinates[1] - end_y));
				if (angle < 0) {
					angle += 360;
				}

				if (angle <= 45 || angle > 315) {
					coordinates[1]--;
				}
				if (angle > 225 && angle <= 315) {
					coordinates[0]++;
				}
				if (angle > 135 && angle <= 225) {
					coordinates[1]++;
				}
				if (angle > 45 && angle <= 135) {
					coordinates[0]--;
				}

			} while ((Unit_At_Tile(coordinates[0], coordinates[1]) == false && terrain_IsHoverable(coordinates[0], coordinates[1]) == true)
					&& (end_x != coordinates[0] || end_y != coordinates[1]));

		}

		return coordinates;// returns end coordinates
	}

	public int[] Ability_GroundObject_findEndSpotWithCollision(int start_x, int start_y, int end_x, int end_y) {// MAKE
																												// SURE
																												// THIS
																												// WORKS
		int[] coordinates = new int[2];
		int[] futurecoordinates = new int[2];
		// int direction=0;

		coordinates[0] = start_x;
		coordinates[1] = start_y;

		futurecoordinates[0] = start_x;
		futurecoordinates[1] = start_y;

		double angle = Math.toDegrees(Math.atan2(coordinates[0] - end_x, coordinates[1] - end_y));
		if (angle < 0) {
			angle += 360;
		}
		if (angle <= 45 || angle > 315) {
			futurecoordinates[1]--;
		}
		if (angle > 225 && angle <= 315) {
			futurecoordinates[0]++;
		}
		if (angle > 135 && angle <= 225) {
			futurecoordinates[1]++;
		}
		if (angle > 45 && angle <= 135) {
			futurecoordinates[0]--;
		}

		if (Unit_At_Tile(futurecoordinates[0], futurecoordinates[1]) == false
				&& terrain_IsWalkable(futurecoordinates[0], futurecoordinates[1]) == true) { // creeps
																								// the
																								// end
																								// points
																								// up
																								// little
																								// by
																								// little
																								// and
																								// stops
																								// if
																								// a
																								// wall
																								// is
																								// hit
			do {

				coordinates[0] = futurecoordinates[0];
				coordinates[1] = futurecoordinates[1];

				angle = Math.toDegrees(Math.atan2(coordinates[0] - end_x, coordinates[1] - end_y));
				if (angle < 0) {
					angle += 360;
				}

				if (angle <= 45 || angle > 315) {
					futurecoordinates[1]--;
				}
				if (angle > 225 && angle <= 315) {
					futurecoordinates[0]++;
				}
				if (angle > 135 && angle <= 225) {
					futurecoordinates[1]++;
				}
				if (angle > 45 && angle <= 135) {
					futurecoordinates[0]--;
				}

			} while ((Unit_At_Tile(futurecoordinates[0], futurecoordinates[1]) == false && terrain_IsWalkable(futurecoordinates[0],
					futurecoordinates[1]) == true) && (end_x != coordinates[0] || end_y != coordinates[1]));

		}

		return coordinates;// returns end coordinates
	}

	/*
	 * int[] findNearestGraveSpot(int unitx, int unity){//hopefully this works
	 * 
	 * int[] coordinates = new int[2]; coordinates=null;
	 * 
	 * double closest_dist_seen=9999999;
	 * 
	 * //find closest grave spot to hero for(int x=0;x<SharedData.MAP_SIZE;x++){
	 * for(int y=0;y<SharedData.MAP_SIZE;y++){
	 * 
	 * if(map[LARGEOBJECTS_LAYER][y][x]>=GRAVE_1 &&
	 * map[LARGEOBJECTS_LAYER][y][x]<=GRAVE_4){
	 * 
	 * 
	 * double dist = Math.sqrt(Math.abs(Math.pow((x - unitx),2)) +
	 * Math.abs(Math.pow((y - unity),2)));
	 * 
	 * if(dist < closest_dist_seen){closest_dist_seen = dist; coordinates[0] =
	 * x;coordinates[1] = y;}
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * //find closest open spot to grave closest_dist_seen=9999999;
	 * 
	 * if(coordinates!=null){ for(int x=0;x<SharedData.MAP_SIZE;x++){ for(int
	 * y=0;y<SharedData.MAP_SIZE;y++){
	 * 
	 * if(terrain_IsWalkable(x,y) && UnitMap[x][y]==0){ double dist =
	 * Math.sqrt(Math.abs(Math.pow((x - coordinates[0]),2)) +
	 * Math.abs(Math.pow((y - coordinates[1]),2))); if(dist <
	 * closest_dist_seen){closest_dist_seen = dist; coordinates[0] =
	 * x;coordinates[1] = y;} }
	 * 
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * return coordinates;
	 * 
	 * }
	 */

	/*
	 * String[] Split_String_To_TooltipLines(String s) {
	 * 
	 * String[] finishedtooltips = new String[5]; int[] lineendpoints = new
	 * int[6]; int s_length = s.length();
	 * 
	 * if (s_length < tooltiplinelength || s.lastIndexOf(' ') == -1 ||
	 * s.lastIndexOf(' ') < tooltiplinelength) { finishedtooltips[0] = s; } else
	 * {
	 * 
	 * String temp_piece = s.substring(0, tooltiplinelength);// take the //
	 * first // line
	 * 
	 * if (temp_piece.lastIndexOf(' ') != -1) { lineendpoints[0] =
	 * temp_piece.lastIndexOf(' '); } else { lineendpoints[0] =
	 * tooltiplinelength; }
	 * 
	 * // the above is fine -----
	 * 
	 * int mid_lines = 5; boolean end_of_s_reached = false;
	 * 
	 * for (int i = 1; i < mid_lines; i++) {
	 * 
	 * if (lineendpoints[i - 1] + tooltiplinelength > s_length) { mid_lines = i;
	 * lineendpoints[i] = s_length; end_of_s_reached = true; }
	 * 
	 * if (end_of_s_reached == false) { temp_piece = s.substring(lineendpoints[i
	 * - 1], lineendpoints[i - 1] + tooltiplinelength);
	 * 
	 * lineendpoints[i] = temp_piece.lastIndexOf(' '); lineendpoints[i] +=
	 * lineendpoints[i - 1]; }
	 * 
	 * }// end 1-5 loop
	 * 
	 * finishedtooltips[0] = s.substring(0, lineendpoints[0]); for (int i = 1; i
	 * <= mid_lines; i++) {
	 * 
	 * if (i != 5) { finishedtooltips[i] = s.substring(lineendpoints[i - 1],
	 * lineendpoints[i]); }
	 * 
	 * }
	 * 
	 * }// end check for small string
	 * 
	 * return finishedtooltips;
	 * 
	 * }
	 */

	void load_spritesheet(int start_type, String ref) {

		Image2D sheet = null;
		try {
			sheet = new Image2D(textureLoader.getTexture("wrap/assets/" + ref));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 16; i++) {
			myTypeDB[start_type + i].image = sheet.getSubImage((40 * (i % 4)), (40 * (i / 4)), 40, 40);
		}

		// sheet.begin();
	}

	void DrawUnitHealthBar(int UnitID, int x, int y) {// /draws a health bar
														// similar to one seen
														// in MobA games

		final int square_portion = 25;

		int running_x = x;
		int square_height = 5;

		int health = Units[UnitID].stat[myshareddata.HEALTH];

		int maxwidth = 50;
		int width = ((health * 50) / get_unit_maxhealth(UnitID));

		int max_num_of_full_squares = get_unit_maxhealth(UnitID) / square_portion;

		int num_of_full_squares = health / square_portion;

		int size_of_last_square = health % square_portion;

		int max_size_of_last_square = get_unit_maxhealth(UnitID) % square_portion;

		double g = ((double) health) / ((double) get_unit_maxhealth(UnitID));
		double r = 1.0 - g;

		GL11.glColor4d(r, g / 2, 0, 0.5);
		// GL11.glColor4d(0.8, 0, 0,0.7);

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		// DRAW BOXES
		GL11.glBegin(GL11.GL_QUADS);

		int full_square_width = (maxwidth * square_portion) / (max_num_of_full_squares * square_portion + max_size_of_last_square);

		for (int i = 0; i < num_of_full_squares; i++) {

			GL11.glVertex2f(running_x, y);

			GL11.glVertex2f(running_x + full_square_width - 1, y);

			GL11.glVertex2f(running_x + full_square_width - 1, y + square_height);

			GL11.glVertex2f(running_x, y + square_height);

			running_x += full_square_width;
		}

		int last_square_width = ((size_of_last_square * full_square_width) / square_portion);

		GL11.glVertex2f(running_x, y);

		GL11.glVertex2f(running_x + last_square_width - 1, y);

		GL11.glVertex2f(running_x + last_square_width - 1, y + square_height);

		GL11.glVertex2f(running_x, y + square_height);

		GL11.glEnd();

		// DRAW LINES
		GL11.glColor3f(0, 0, 0);

		running_x = x;// reset

		GL11.glLineWidth(1f);

		GL11.glBegin(GL11.GL_LINES);

		int full_box_width = (maxwidth * square_portion) / (max_num_of_full_squares * square_portion + max_size_of_last_square);

		// left line
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x, y + square_height);

		for (int i = 0; i < num_of_full_squares; i++) {

			// top line
			GL11.glVertex2f(running_x, y);
			GL11.glVertex2f(running_x + full_square_width - 1, y);
			// bottom line
			GL11.glVertex2f(running_x + full_square_width - 1, y + square_height);
			GL11.glVertex2f(running_x, y + square_height);
			// right line
			GL11.glVertex2f(running_x + full_square_width - 1, y);
			GL11.glVertex2f(running_x + full_square_width - 1, y + square_height);

			// left line
			GL11.glVertex2f(running_x, y);
			GL11.glVertex2f(running_x, y + square_height);

			running_x += full_square_width;
		}

		int last_box_width = ((size_of_last_square * full_square_width) / square_portion);

		GL11.glVertex2f(running_x, y);
		GL11.glVertex2f(running_x + last_square_width, y);
		GL11.glVertex2f(running_x + last_square_width, y + square_height);
		GL11.glVertex2f(running_x, y + square_height);

		// left line
		GL11.glVertex2f(running_x, y);
		GL11.glVertex2f(running_x, y + square_height);

		// right line
		GL11.glVertex2f(running_x + last_square_width, y);
		GL11.glVertex2f(running_x + last_square_width, y + square_height);

		GL11.glEnd();

		GL11.glLineWidth(2f);

		if (TextureModeWasEnabled) {
			GL11.glEnable(GL_TEXTURE_2D);
		}
		GL11.glColor3f(1, 1, 1);

	}

	void DrawFacingGuide(int UnitID, int unit_screen_x, int unit_screen_y, int facing) {

		unit_screen_x += 20;
		// unit_screen_y+=20;

		final int shixel_size = TILE_SIZE / 4;

		// int max_dist = 10*shixel_size;

		int brightness = Units[UnitID].stat[myshareddata.PERCEPTION] * 1;

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		// DRAW BOXES
		GL11.glBegin(GL11.GL_QUADS);

		for (int a = -40; a <= 40; a++) {
			for (int b = -40; b <= 40; b++) {

				int dist = pythagorean(0, 0, b, a);
				double drawnbrightness = (0.06 * brightness) - 0.05 * dist;

				int direction = DirectionBetweenCoordinates(0, 0, b * shixel_size, a * shixel_size);

				// screen coords
				int x = unit_screen_x + b * shixel_size;
				int y = unit_screen_y + a * shixel_size;

				if (drawnbrightness > 0.1 && direction == facing) {

					/*
					 * int mapX = x + (cam.x - SCREEN_X_TILES / 2) * 4 - 2; int
					 * mapY = y + (cam.y - SCREEN_Y_TILES / 2) * 4; //
					 * System.out.print(mapX+":"+mapY+" "); if (mapX > -1 &&
					 * mapX < SharedData.MAP_SIZE * 4 && mapY > -1 && mapY <
					 * SharedData.MAP_SIZE * 4) {
					 */

					GL11.glColor4d(1, 1, 1, drawnbrightness / 2);

					// draw quad

					GL11.glVertex2f(x, y);
					GL11.glVertex2f(x + shixel_size, y);
					GL11.glVertex2f((x) + shixel_size, (y) + shixel_size);
					GL11.glVertex2f(x, (y) + shixel_size);

				}
			}

		}
		// }

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

	void Draw_Line_Smooth(int x, int y, int x2, int y2) {

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glDisable(GL_TEXTURE_2D);
		}

		// GL11.glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		// GL11.glLineWidth(1.5);

		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glBegin(GL11.GL_LINE_STRIP);

		GL11.glVertex2f(x, y);

		GL11.glVertex2f(x2, y2);

		GL11.glEnd();

		if (TextureModeWasEnabled) {
			GL11.glEnable(GL_TEXTURE_2D);
		}

	}

	void DrawCircle(float cx, float cy, float r, int num_segments) {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int ii = 0; ii < num_segments; ii++) {
			float theta = 2.0f * 3.1415926f * (float) (ii) / (float) (num_segments);// get
																					// the
																					// current
																					// angle

			float x = (float) r * (float) Math.cos(theta);// calculate the x
															// component
			float y = (float) r * (float) Math.sin(theta);// calculate the y
															// component

			glVertex2f(x + cx, y + cy);// output vertex

		}
		GL11.glEnd();
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

	Texture generate_tex_local(String ref) {

		Texture tex = null;

		// String localpath =
		// (Thread.currentThread().getContextClassLoader().getResource(
		// "wrap/assets/" + ref ).getFile());
		// System.out.println(localpath );

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

		System.out.println(ref);

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

		System.out.println(ref);

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

	void Broadcast_UnitDeath(int x, int y) {// only the DM runs this function

		int UnitID = UnitMap[x][y];

		if (UnitID >= 10) {// for NPCs, spawn lootz

			// server gets loot table before the unit is deleted (taken from the
			// units inventory!!)
			int[] loot = new int[6];
			loot = getLootFromUnitInventory(UnitMap[x][y]);

			// spawn itemcontainer/corpse!
			if (map[myshareddata.SMALLOBJECTS_LAYER][y][x] == 0) {
				Server_BroadcastSpawnObject(myshareddata.SMALLOBJECTS_LAYER, myshareddata.ASHES, 0, x, y);
			}

			for (int i = 0; i < 6; i++) {

				Server_Broadcast_EditTileItemIDQuantity(x, y, loot[i], +1, false, 0);
			}

		}

		if (BattlingUnits[BattlePhase_ActiveUnit] == UnitID) {
			GetUnitEndedBattleTurn();
		}
		Server_Broadcast_EditStats(SharedData.DEAD, 1, UnitID);

		// heroes lose half of their gold upon death
		if (UnitID < 10) {
			Server_Broadcast_EditStats(SharedData.GOLD_CARRIED, Units[UnitID].stat[SharedData.GOLD_CARRIED] / 2, UnitID);
		}

		// server_broadcastDeleteObject(SharedData.PLAYER_LAYER,Units[UnitID].x,Units[UnitID].y);

		String[] s = {"" + x, "" + y};
		GenericServerBroadcast("/unitdeath", s);

	}

	void receive_UnitDeath(int x, int y) {
		int UnitID = UnitMap[x][y];
		if (UnitID > 0) {

			if (myPNum != 1) {

				if (NPC_At_Tile(x, y) && myPNum != 1 && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1) {// split
																												// gold
																												// and
																												// exp
																												// of
																												// a
																												// dying
																												// NPC
																												// among
																												// all
																												// nearby
																												// heroes
																												// evenly

					int number_of_nearby_heroes = 0;
					for (int i = 2; i < 9; i++) {
						if (Units[i].IsAlive() && pythagorean(Units[i].x, Units[i].y, x, y) < 6) {
							number_of_nearby_heroes++;
						}
					}

					// double tiledist_fromhero = Math.sqrt(Math.abs(Math.pow(y
					// - Units[myPNum].y, 2)) + Math.abs(Math.pow(x -
					// Units[myPNum].x, 2)));

					if (pythagorean(Units[myPNum].x, Units[myPNum].y, x, y) < 6) {
						int expreward = Units[UnitID].stat[myshareddata.EXP_REWARD] / number_of_nearby_heroes;
						client_gainexperience(expreward);

						int goldreward = Units[UnitID].stat[myshareddata.GOLD_CARRIED] / number_of_nearby_heroes;
						client_gaingold(goldreward);

						myBattleReport.AddKill(Units[UnitID], expreward, goldreward);

					}

				}

			}

			// UnitMap[x][y] = 0; DO THIS PERIODICALLY LIKE GARBAGE COLLECTION

		}

	}

	int get_unit_maxhealth(int UnitID) {

		int answer = 10 + Units[UnitID].stat[myshareddata.LEVEL] * 4 + Units[UnitID].stat[myshareddata.ENDURANCE] * 4;

		return answer;
	}

	int get_unit_maxenergy(int UnitID) {

		int answer = GetEffectiveUnitStat(UnitID, myshareddata.ENERGYMAX, 0);

		return answer;
	}

	int get_unit_maxwalkmoves(int UnitID) {

		int answer = GetEffectiveUnitStat(UnitID, myshareddata.SPEED, 0) / 5;

		return answer;
	}

	int[] getObjectLootFromTable(int type) {// this fills NPCs and Chests
											// pockets when they spawn. NPC
											// pockets drop upon death!
		int[] sortedloot = new int[6];

		int[] rawloot = new int[6];

		int sorted_loot_number = 0;

		// determine the won loot
		for (int i = 0; i < 6; i++) {
			int roll = (int) (Math.random() * 100);
			if (myTypeDB[type].LootTable_itemgroupchances[i] > roll) {
				rawloot[i] = GetItemFromItemGroup(myTypeDB[type].LootTable_itemgroups[i]);
			}
		}

		// organize the raw loot
		for (int i = 0; i < 6; i++) {
			if (rawloot[i] > 0) {
				sortedloot[sorted_loot_number] = rawloot[i];
				sorted_loot_number++;
			}
		}

		// you actually only win 6 items max, so chop off the last 4

		return sortedloot;
	}

	int[] getLootFromUnitInventory(int UnitID) {

		int[] loot = new int[6];
		int number_of_items_found = 0;

		for (int i = 0; i <= 49; i++) {

			if (number_of_items_found < 6 && Units[UnitID].itemInSlot[i] > 0) {

				// if (Math.random() > 0.5) {// some inventory items are
				// destroyed
				// when dying
				loot[number_of_items_found] = Units[UnitID].itemInSlot[i];
				number_of_items_found++;
				// }
			}

		}

		for (int i = 0; i < 5; i++) {

			if (number_of_items_found < 6 && myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].items_dropped[i] > 0) {

				int randomnum = (int) (Math.random() * 100);
				if (randomnum < myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].item_drop_percent[i]) {

					loot[number_of_items_found] = myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].items_dropped[i];
					number_of_items_found++;
				}
			}

		}

		return loot;
	}

	/*
	 * void TryToWalkTowardsDirection(int UnitID, int direction) {
	 * 
	 * int x = Units[UnitID].x; int y = Units[UnitID].y;
	 * 
	 * if(direction==0){y--;} if(direction==1){x++;} if(direction==2){y++;}
	 * if(direction==3){x--;}
	 * 
	 * if (UnitCanWalk(UnitID) && !Units[UnitID].is_sliding &&
	 * Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
	 * 
	 * Anyone_Broadcast_CastAbility(targetting_currentability, Units[UnitID].x,
	 * Units[UnitID].y, x, y); }
	 * 
	 * }
	 */

	void TryToWalkTowardsMouseCursor(int UnitID) {

		if (UnitCanWalk(UnitID) && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum) {
			if (!Units[UnitID].is_sliding) {
				Anyone_Broadcast_CastAbility(targetting_currentability, Units[UnitID].x, Units[UnitID].y, cursor.currentmousetile_x,
						cursor.currentmousetile_y);
			} else {
				targetting_currentability = 0;
			}
		}

	}

	int GetItemFromItemGroup(int itemtable) {

		int item = 0;
		int num_items_found = 0;
		int[] mytable = new int[1000];

		for (int i = 0; i < 1000; i++) {// build the table

			if (myItemDB[i].itemgroup == itemtable) {
				mytable[num_items_found] = i;
				num_items_found++;
			}

		}

		item = mytable[(int) (Math.random() * num_items_found)];

		return item;
	}

	int weaponskillsorder[] = new int[1000];
	int num_of_weaponskills_available = 0;

	void Refresh_Unit_Skills(int UnitID) {// run this whenever a hero changes
											// their weapons

		int num_found = 0;

		for (int i = 0; i < 1000; i++) {
			if (myAbilityDB[i].enabled && myAbilityDB[i].abilityspec == SharedData.ABILITYSPEC_WEAPONSKILL) {
				if (weaponskill_IsAvailable(UnitID, i)) {
					weaponskillsorder[num_found] = i;

					num_found++;
				}
			}
		}

		num_of_weaponskills_available = num_found;

		for (int i = 0; i < 1000; i++) {
			if (myAbilityDB[i].enabled && myAbilityDB[i].abilityspec == SharedData.ABILITYSPEC_WEAPONSKILL) {
				if (!weaponskill_IsAvailable(UnitID, i)) {
					weaponskillsorder[num_found] = i;
					num_found++;
				}
			}
		}

	}

	int[] GetActiveWeaponSkills() {// run this whenever a hero changes their
									// weapons

		int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

		int num_found = 0;

		int skills[] = new int[10];

		for (int i = 0; i < 1000; i++) {
			if (myAbilityDB[i].enabled && myAbilityDB[i].abilityspec == SharedData.ABILITYSPEC_WEAPONSKILL) {
				if (weaponskill_IsAvailable(UnitID, i)) {

					if (num_found < 10) {
						skills[num_found] = i;
					}

					num_found++;
				}
			}
		}

		return skills;

	}

	boolean weaponskill_IsAvailable(int UnitID, int abilitynum) {
		boolean answer = false;
		// int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

		for (int n = 1; n < 20; n++) {
			if (myAbilityDB[abilitynum].weapon_required == n) {
				if ((myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].weapontype == n && myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].itemlevel >= myAbilityDB[abilitynum].required_level)
						|| (myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].weapontype == n && myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].itemlevel >= myAbilityDB[abilitynum].required_level)) {
					answer = true;
				}

			}
		}

		if (myAbilityDB[abilitynum].weapon_required == 0) {
			answer = true;
		}

		return answer;
	}

	int[] GetConsumables() {// run this whenever a hero changes their items or
							// uses a consumable

		int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

		int num_found = 0;

		int bagslot[] = new int[10];

		for (int i = 0; i < 10; i++) {
			bagslot[i] = -1;
		}

		for (int i = 0; i < 50; i++) {
			int itemID = Units[UnitID].itemInSlot[i];
			if (itemID > 0) {
				if (myItemDB[itemID].itemgroup == SharedData.ITEMTYPE_CONSUMABLES) {

					if (num_found < 10) {
						bagslot[num_found] = i;
					}

					num_found++;

				}
			}
		}

		return bagslot;

	}

	void ActivateConsumable(int UnitID, int item_bagslot) {
		if (item_bagslot >= 0) {
			int item_id = Units[UnitID].itemInSlot[item_bagslot];
			if (myPNum != 1) {

				// if(ConsumeItemCooldown ==0){

				for (int i = 0; i < 5; i++) {
					int stat = myItemDB[item_id].stat_to_increase[i];
					int oldvalue = Units[UnitID].stat[stat];
					int newvalue = oldvalue + myItemDB[item_id].stat_bonus_amount[i];
					newvalue += (Units[UnitID].stat[myItemDB[item_id].stat_affecting_amount[i]] * myItemDB[item_id].stat_affecting_amount_factor[i]) / 100;

					if (stat > 0 && newvalue != oldvalue) {
						Client_EditStats(myItemDB[item_id].stat_to_increase[i], newvalue, UnitID);
					}
				}

				for (int i = 0; i < 5; i++) {
					int effect = myItemDB[item_id].use_effects[i];
					Client_TellServer_ExecuteEffectWhole(effect, Units[UnitID].x, Units[UnitID].y, Units[UnitID].x, Units[UnitID].y);
				}

				Client_TellServer_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, item_id, -1);
				// ConsumeItemCooldown=1000;
				// }

			} else {

				for (int i = 0; i < 5; i++) {
					int stat = myItemDB[item_id].stat_to_increase[i];
					int oldvalue = Units[UnitID].stat[stat];
					int newvalue = oldvalue + myItemDB[item_id].stat_bonus_amount[i];
					newvalue += (Units[UnitID].stat[myItemDB[item_id].stat_affecting_amount[i]] * myItemDB[item_id].stat_affecting_amount_factor[i]) / 100;

					if (stat > 0 && newvalue != oldvalue) {
						Server_Broadcast_EditStats(myItemDB[item_id].stat_to_increase[i], newvalue, UnitID);
					}
				}

				for (int i = 0; i < 5; i++) {
					int effect = myItemDB[item_id].use_effects[i];
					ExecuteEffectWhole(effect, Units[UnitID].x, Units[UnitID].y, Units[UnitID].x, Units[UnitID].y);
				}

				Server_Broadcast_EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y, item_id, -1, false, 0);
				// ConsumeItemCooldown=1000;
				// }

			}

			playSound(mySoundPaths[SOUND_BOTTLE]);
		}

	}

	void setTypeColors(int type, int r, int g, int b) {

		myTypeDB[type].minimapcolors[0] = r;
		myTypeDB[type].minimapcolors[1] = g;
		myTypeDB[type].minimapcolors[2] = b;

	}

	void setTypeColors(int recursions, int type, int r, int g, int b) {

		for (int i = 0; i < recursions; i++) {
			myTypeDB[type + i].minimapcolors[0] = r + i;
			myTypeDB[type + i].minimapcolors[1] = g + i;
			myTypeDB[type + i].minimapcolors[2] = b + i;
		}

	}

	void client_downloadmap(int layer, String downloadedmappiece) {

		int z = layer;
		if (z < 4) {
			loadscreen_currentstage = z;
		}

		if (z >= myshareddata.TERRAIN_LAYER_LOWER && z <= myshareddata.LARGEOBJECTS_LAYER) {

			if (z == 0) {
				prepareMapforLoad();
			} // right before, prepare map for the load

			int chunkcount = 0;

			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					// if(downloadedmap.length()/3 >chunkcount){

					map[z][y][x] = Integer.parseInt(downloadedmappiece.substring(0 + (chunkcount * 3), 3 + (chunkcount * 3)));

					chunkcount++;
					// }
				}
			}

			System.out.println("downloaded layer!");

			if (z == myshareddata.PLAYER_LAYER) { // right after, set this
													// clients hero spawn
				// in the right spot
				for (int y = 0; y < SharedData.MAP_SIZE; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE; x++) {
						if (map[myshareddata.PLAYER_LAYER][y][x] == ((myshareddata.HERO_2) - 2) + myPNum) {
							Units[myPNum].x = x;
							Units[myPNum].y = y;// set Units[myPNum] for the
												// clients to be the right spot
												// when the game starts
						}
					}
				}
			}

		}

		/*
		 * if (z == myshareddata.NOTES_LAYER) { //
		 * "/map.4.00x.00y.00t.hellothereimamessage"
		 * 
		 * int x = Integer.parseInt(downloadedmappiece.substring(0, 3)); int y =
		 * Integer.parseInt(downloadedmappiece.substring(4, 7)); int t =
		 * Integer.parseInt(downloadedmappiece.substring(8, 11)); String m =
		 * downloadedmappiece.substring(12, downloadedmappiece.length());
		 * 
		 * map[myshareddata.NOTES_LAYER][y][x] = t; // NoteTypes[x][y]=t;
		 * NoteMessages[x][y] = m;
		 * 
		 * }// end note downloading
		 */

		// get indoor definitions
		if (z == myshareddata.REGION_LAYER) {
			int chunkcount = 0;
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {
					int i = 0;
					i = Integer.parseInt(downloadedmappiece.substring(0 + (chunkcount * 1), 1 + (chunkcount * 1)));
					if (i == 1) {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = false;
					}

					chunkcount++;
				}
			}

		}

		// get assets
		if (z == myshareddata.REGION_LAYER + 1) {
			int chunkcount = 0;
			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {
					int i = 0;
					i = Integer.parseInt(downloadedmappiece.substring(0 + (chunkcount * 1), 1 + (chunkcount * 1)));
					if (i == 1) {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = false;
					}

					chunkcount++;
				}
			}
		}

	}

	void Client_finished_map_download() {
		initgameOpenGL();

	}

	// /DO NOT DO THIS IN GAME - IT WILL LAG
	private boolean writeHerotoFile(String sFileName) {

		if (sFileName.equals("lastknownpath")) {
			sFileName = lastHeroFilePath;
		} else {
			lastHeroFilePath = sFileName;
		}

		System.out.println(sFileName);

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".vhf")) {} else {
			sFileName += (".vhf");
		}

		try {

			FileWriter writer = new FileWriter(sFileName);

			writer.append(Players[myPNum].name + "\n");
			writer.append(Players[myPNum].herodescription + "\n");

			// stats
			for (int i = 0; i < myshareddata.NUM_OF_UNITSTATS; i++) {
				writer.append(Units[myPNum].stat[i] + "\n");
			}

			// items
			for (int i = 0; i < Units[myPNum].itemInSlot.length; i++) {
				writer.append(Units[myPNum].itemInSlot[i] + "\n");
				writer.append(Units[myPNum].itemQuantity[i] + "\n");
			}

			for (int i = 0; i < Units[myPNum].active_skill_cooldowns.length; i++) {
				writer.append(Units[myPNum].active_skill_cooldowns[i] + "\n");
			}
			for (int i = 0; i < Units[myPNum].activeconditions.length; i++) {
				writer.append(Units[myPNum].activeconditions[i] + "\n");
				writer.append(Units[myPNum].conditionstimeleft[i] + "\n");
				writer.append(Units[myPNum].conditionsintensity[i] + "\n");
				writer.append(Units[myPNum].conditions_source[i] + "\n");
			}

			// append profile image here!!
			for (int x = 0; x < 128; x++) {
				for (int y = 0; y < 128; y++) {

					int c = 0;

					try {
						c = myBufferedProfileImage.getRGB(x, y);
						// c = (char)(i);
					} catch (Exception e) {}

					writer.append(c + "\n");
				}
			}

			// writer.append("\n");

			// abilitylevels
			// NOTIMPLEMENTEDYET

			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	private void readHerofromFile(String sFileName) {

		lastHeroFilePath = sFileName;

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".vhf")) {} else {
			sFileName += (".vhf");
		}

		// myPNum = 1;// should this happen?

		// new Thread(new LoadHeroFileThreaded(sFileName)).start();

		try {

			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(sFileName));
			} catch (Exception e) {
				br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(sFileName)));

			}

			Players[myPNum].name = br.readLine();
			Players[myPNum].herodescription = br.readLine();

			/*
			 * if(!RunningInGoldMode() ){ Players[myPNum].name =
			 * GenerateRandomHeroName();
			 * 
			 * }
			 */

			// stats
			for (int i = 0; i < myshareddata.NUM_OF_UNITSTATS; i++) {
				Units[myPNum].stat[i] = Integer.parseInt(br.readLine());
			}

			// items
			for (int i = 0; i < Units[myPNum].itemInSlot.length; i++) {
				Units[myPNum].itemInSlot[i] = Integer.parseInt(br.readLine());
				Units[myPNum].itemQuantity[i] = Integer.parseInt(br.readLine());
			}

			for (int i = 0; i < Units[myPNum].active_skill_cooldowns.length; i++) {
				Units[myPNum].active_skill_cooldowns[i] = Integer.parseInt(br.readLine());
			}

			for (int i = 0; i < Units[myPNum].activeconditions.length; i++) {
				Units[myPNum].activeconditions[i] = Integer.parseInt(br.readLine());
				Units[myPNum].conditionstimeleft[i] = Integer.parseInt(br.readLine());
				Units[myPNum].conditionsintensity[i] = Integer.parseInt(br.readLine());
				Units[myPNum].conditions_source[i] = Integer.parseInt(br.readLine());
			}

			try {

				myBufferedProfileImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);

				for (int x = 0; x < 128; x++) {
					for (int y = 0; y < 128; y++) {

						int c = 0;

						try {
							c = Integer.parseInt(br.readLine());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myBufferedProfileImage.setRGB(x, y, c);

					}
				}

				CustomProfileImageTextures[0] = textureLoader.getTexture(myBufferedProfileImage, "customtex" + number_of_customtextures);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			number_of_customtextures++;

			ProfileImages[0] = new Image2D(CustomProfileImageTextures[0]);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File!", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	String lastMapFilePath = null;

	private void loadMapfromFile(String sFileName) {

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".vmm")) {} else {
			sFileName += (".vmm");
		}
		// loadedMapName=sFileName.substring(0,sFileName.length()-4);
		lastMapFilePath = sFileName;

		try {

			String externalmapfile = sFileName;

			String newmapfile = SharedData.defaultDirectory() + "\\Maps\\" + sFileName.substring(sFileName.lastIndexOf('\\'));

			copyExternalFileToLocal(externalmapfile, newmapfile, false);

			FileReader fr = new FileReader(sFileName);
			BufferedReader br = new BufferedReader(fr);

			mapName = br.readLine();
			mapDescrip = br.readLine();

			// load map
			for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
				for (int y = 0; y < SharedData.MAP_SIZE; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE; x++) {

						map[z][y][x] = StringToInteger(br.readLine());

					}
				}
			}

			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					int i = StringToInteger(br.readLine());
					if (i == 1) {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = false;
					}

				}
			}

			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					int i = StringToInteger(br.readLine());
					if (i == 1) {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = false;
					}

				}
			}

			fr.close();

			hostpnl_MapIsLoaded = true;

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File!", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	String lastCampaignImageFilePath = null;
	Image2D lastCampaignImage = null;

	private void loadCampaignImagefromFile(String sFileName) {

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".png")) {} else {
			sFileName += (".png");
		}
		// loadedMapName=sFileName.substring(0,sFileName.length()-4);
		lastCampaignImageFilePath = sFileName;

		BufferedImage tempimg = null;

		try {
			tempimg = ImageIO.read(new File(sFileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {// this will be passed to the DM!
			myBufferedCampaignImage = tempimg.getSubimage(0, 0, 128, 128);
		} catch (Exception e1) {
			myBufferedCampaignImage = tempimg;
		}

		lastCampaignImage = generate_typeimage_ext(sFileName);
		// stuff
	}

	String lastCampaignFilePath;

	boolean WriteCampaignToFile(String sFileName) {

		// store the filepath of the map, data of all NPCs, tile conditions,
		// etc.

		System.out.println(sFileName);

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".bfc")) {} else {
			sFileName += (".bfc");
		}

		lastCampaignFilePath = sFileName;

		try {

			FileWriter writer = new FileWriter(sFileName);

			writer.append(myTextBoxes[TEXTBOX_CAMPAIGNNAME].text + "\n");
			writer.append(myTextBoxes[TEXTBOX_CAMPAIGNDESCRIP].text + "\n");

			// map data
			writer.append(mapName + "\n");
			writer.append(mapDescrip + "\n");

			for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
				for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
					for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

						String s = "" + map[z][y][x];

						if (s.length() == 1) {
							s = "00" + s;
						} else if (s.length() == 2) {
							s = "0" + s;
						}

						writer.append(s + "\n");

					}
				}
			}

			for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
				for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

					int i = 0;
					if (define_regions[SharedData.REGION_INDOORS - 800][y][x]) {
						i = 1;
					}

					writer.append(i + "\n");

				}
			}

			for (int y = 0; y < myshareddata.MAP_SIZE; y++) {
				for (int x = 0; x < myshareddata.MAP_SIZE; x++) {

					int i = 0;
					if (define_regions[SharedData.REGION_DUNGEON - 800][y][x]) {
						i = 1;
					}

					writer.append(i + "\n");

				}
			}

			// Write data of campaign image
			for (int x = 0; x < 128; x++) {
				for (int y = 0; y < 128; y++) {

					int c = 0;

					try {
						c = myBufferedCampaignImage.getRGB(x, y);
						// c = (char)(i);
					} catch (Exception e) {}

					writer.append(c + "\n");
				}
			}

			writer.append("NumUnits:" + NUMBER_OF_UNITS + "\n");
			for (int i = 10; i < NUMBER_OF_UNITS; i++) {// write unit data
				writer.append(Units[i].x + "\n");
				writer.append(Units[i].y + "\n");

				for (int j = 0; j < myshareddata.NUM_OF_UNITSTATS; j++) {
					writer.append(Units[i].stat[j] + "\n");
				}

				for (int j = 0; j < Units[i].itemInSlot.length; j++) {
					writer.append(Units[i].itemInSlot[j] + "\n");
					writer.append(Units[i].itemQuantity[j] + "\n");
				}
				for (int j = 0; j < Units[i].active_skill_cooldowns.length; j++) {
					writer.append(Units[i].active_skill_cooldowns[j] + "\n");
				}
				for (int j = 0; j < Units[i].activeconditions.length; j++) {
					writer.append(Units[i].activeconditions[j] + "\n");
					writer.append(Units[i].conditionstimeleft[j] + "\n");
					writer.append(Units[i].conditionsintensity[j] + "\n");
					writer.append(Units[i].conditions_source[j] + "\n");
				}

			}// stop writing unit data

			// writing tileactiveconditions would be WAY too much wasteful
			// data.... hmmm...

			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}

	String CampaignName = null;
	String CampaignDescrip = null;

	void ReadCampaignFromFile(String sFileName) {

		if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".bfc")) {} else {
			sFileName += (".bfc");
		}

		lastCampaignFilePath = sFileName;

		FileReader fr;
		try {
			fr = new FileReader(sFileName);

			BufferedReader br = new BufferedReader(fr);

			CampaignName = br.readLine();
			CampaignDescrip = br.readLine();

			// /load map info
			mapName = br.readLine();
			mapDescrip = br.readLine();

			// load map
			for (int z = myshareddata.TERRAIN_LAYER_LOWER; z <= myshareddata.LARGEOBJECTS_LAYER; z++) {
				for (int y = 0; y < SharedData.MAP_SIZE; y++) {
					for (int x = 0; x < SharedData.MAP_SIZE; x++) {

						map[z][y][x] = StringToInteger(br.readLine());

					}
				}
			}

			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					int i = StringToInteger(br.readLine());
					if (i == 1) {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_INDOORS - 800][y][x] = false;
					}

				}
			}

			for (int y = 0; y < SharedData.MAP_SIZE; y++) {
				for (int x = 0; x < SharedData.MAP_SIZE; x++) {

					int i = StringToInteger(br.readLine());
					if (i == 1) {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = true;
					} else {
						define_regions[SharedData.REGION_DUNGEON - 800][y][x] = false;
					}

				}
			}

			// Load Campaign Image
			Texture tex = null;
			myBufferedProfileImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < 128; x++) {
				for (int y = 0; y < 128; y++) {

					int c = 0;

					try {
						c = Integer.parseInt(br.readLine());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					myBufferedProfileImage.setRGB(x, y, c);
					debug("c" + c);

				}
			}
			tex = textureLoader.getTexture(myBufferedProfileImage, "customtex" + number_of_customtextures);
			number_of_customtextures++;

			lastCampaignImage = new Image2D(tex);

			hostpnl_MapIsLoaded = true;

			// read units
			String lastline = br.readLine();
			NUMBER_OF_UNITS = Integer.parseInt(lastline.substring(lastline.indexOf(":") + 1));

			for (int i = 10; i < NUMBER_OF_UNITS; i++) {// write unit data
				Units[i] = new Unit();
				Units[i].x = StringToInteger(br.readLine());
				Units[i].y = StringToInteger(br.readLine());
				UnitMap[Units[i].x][Units[i].y] = i;

				for (int j = 0; j < myshareddata.NUM_OF_UNITSTATS; j++) {
					Units[i].stat[j] = StringToInteger(br.readLine());
				}

				for (int j = 0; j < Units[i].itemInSlot.length; j++) {
					Units[i].itemInSlot[j] = StringToInteger(br.readLine());
					Units[i].itemQuantity[j] = StringToInteger(br.readLine());
				}
				for (int j = 0; j < Units[i].active_skill_cooldowns.length; j++) {
					Units[i].active_skill_cooldowns[j] = StringToInteger(br.readLine());
				}
				for (int j = 0; j < Units[i].activeconditions.length; j++) {
					Units[i].activeconditions[j] = StringToInteger(br.readLine());
					Units[i].conditionstimeleft[j] = StringToInteger(br.readLine());
					Units[i].conditionsintensity[j] = StringToInteger(br.readLine());
					Units[i].conditions_source[j] = StringToInteger(br.readLine());
				}

			}// stop writing unit data

			// read tileconditions

			fr.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		game_fully_loaded = true;

	}

	/*
	 * public void copyFile(File sourceFile, File destFile, boolean overwrite)
	 * throws IOException { boolean already_exists = destFile.exists(); if
	 * (!already_exists) {debug("NEW FILE MADE"); destFile.createNewFile(); }
	 * 
	 * if (!already_exists || overwrite) { // System.out.println("uno");
	 * 
	 * FileChannel source = null; FileChannel destination = null;
	 * 
	 * try { source = new FileInputStream(sourceFile).getChannel(); destination
	 * = new FileOutputStream(destFile).getChannel();
	 * destination.transferFrom(source, 0, source.size()); //
	 * System.out.println("dos");
	 * 
	 * } finally { if (source != null) { source.close(); } if (destination !=
	 * null) { destination.close(); } }
	 * 
	 * } }
	 */

	public void copyExternalFileToLocal(String S_external, String S_local, boolean overwrite) throws IOException {
		debug(S_external);

		byte[] dataBytes = null;

		InputStream is = null;
		try {
			is = new FileInputStream(S_external);
			dataBytes = IOUtils.toByteArray(is);
		} catch (IOException e) {
			System.err.printf("Failed while reading bytes from %s: %s");
			e.printStackTrace();
			// Perform any other exception handling that's appropriate.
		} finally {
			if (is != null) {
				is.close();
			}
		}

		File outputfile = new File(S_local);

		if (!outputfile.exists() || overwrite) {

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
		} catch (IOException e) {
			System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
			e.printStackTrace();
			// Perform any other exception handling that's appropriate.
		} finally {
			if (is != null) {
				is.close();
			}
		}

		File outputfile = new File(S_external);

		if (!outputfile.exists() || overwrite) {

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

		if (!outputfile.exists() || overwrite) {

			try {
				ImageIO.write(img, "png", outputfile);
			} catch (Exception e) {}

		}
	}

	private void CopyLocalFileIfNotFoundInAppData(String S_local, String S_external) {

		if (S_local.substring(S_local.length() - 4, S_local.length()).equals(".png")) {

			try {
				writePNGFile(S_local, S_external, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			// If there is no active assetbook, copy the default one in!
			String localpath = null;
			// URI extpath = null;
			try {
				// localpath = new
				// URI(this.getClass().getClassLoader().getResource(
				// S_local ).toString());
				localpath = (Thread.currentThread().getContextClassLoader().getResource(S_local).getFile());

				// extpath = new URI(S_external);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// File localfile = new File(localpath);
			// File extfile = new File(S_external);

			try {
				copyLocalFileToExternal(S_local, S_external, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	int[][] parse_font_lookup_table(String filepath) {

		int[][] answer = new int[256][6];// for each char..
											// x,y,width,height,xoff,yoff
		String[] lookuptable_lines = new String[1000];

		int linenum = 0;

		int indexer_start = -1;
		int indexer_end = -1;

		FileReader fr;
		try {

			// String localpath =
			// (Thread.currentThread().getContextClassLoader().getResource(
			// "wrap/assets/" + filepath ).getFile());

			InputStream is = this.getClass().getClassLoader().getResourceAsStream("wrap/assets/" + filepath);

			// String localpath = filepath;

			// InputStream in =
			// MapEditorCore.class.getResourceAsStream(filepath);

			// InputStreamReader inread = new InputStreamReader(in, "UTF-8");

			// BufferedReader reader = new BufferedReader(inread);
			// BufferedReader reader = new BufferedReader(new
			// FileReader(localpath));

			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			try {
				while (true) {
					String line = reader.readLine();

					if (line != null) {
						if (line.startsWith("char id")) {

							int char_id = -1;
							int x = 0;
							int y = 0;
							int width = 0;
							int height = 0;
							int xoffset = 0;
							int yoffset = 0;

							lookuptable_lines[linenum] = line;
							linenum++;

							// find char id
							indexer_start = line.indexOf("char id=");
							indexer_start += 8;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								char_id = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find x
							indexer_start = line.indexOf("x=");
							indexer_start += 2;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								x = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find y
							indexer_start = line.indexOf("y=");
							indexer_start += 2;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								y = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find width
							indexer_start = line.indexOf("width=");
							indexer_start += 6;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								width = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find height
							indexer_start = line.indexOf("height=");
							indexer_start += 7;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								height = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find x_off
							indexer_start = line.indexOf("xoffset=");
							indexer_start += 8;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								xoffset = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							// find y_off
							indexer_start = line.indexOf("yoffset=");
							indexer_start += 8;
							indexer_end = line.indexOf(" ", indexer_start);

							if (indexer_start > -1 && indexer_end > -1) {

								yoffset = Integer.parseInt(line.substring(indexer_start, indexer_end));

							}

							if (char_id > -1) {
								answer[char_id][0] = x;
								answer[char_id][1] = y;
								answer[char_id][2] = width;
								answer[char_id][3] = height;
								answer[char_id][4] = xoffset;
								answer[char_id][5] = yoffset;
								// System.out.println(char_id+":"+ height );
							}

						}// end check for startign with char

					} else {

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

		// lookup table

		return answer;

	}

	int[][][] assets_in_groups = new int[3][40][1000];
	int[][] number_of_assets_in_groups = new int[3][40];
	int[] number_of_assets = new int[myshareddata.NUMBER_OF_ASSETGROUPS];

	private void readassetbookfromFile(String sFileName) {

		// int number_of_assets[] = new int[myshareddata.NUMBER_OF_ASSETGROUPS];
		int this_asset_type = 0;
		int this_asset_ID = 0;

		try// import the data
		{

			if (sFileName.substring(sFileName.length() - 4, sFileName.length()).equals(".spl")) {} else {
				sFileName += (".spl");
			}

			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(sFileName));
			} catch (Exception e) {
				br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(sFileName)));

			}

			String version = br.readLine();

			String line = br.readLine();

			// load dynamically, can open outdated files just fine!
			while (line != null) {

				if (line.startsWith("~Next Asset Type")) {
					this_asset_type = Integer.parseInt(line.substring(line.indexOf(":") + 1));
					this_asset_ID = number_of_assets[this_asset_type] + 1;

					number_of_assets[this_asset_type]++;
				}

				if (this_asset_type == 0) {

					for (int p = 0; p < myshareddata.ParameterCount[this_asset_type]; p++) {

						if (line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])) {

							myAbilityDB[this_asset_ID + SPECIALABILITYBUFFER].imported_data[p] = line.substring(line.indexOf(":") + 1);
						}

					}
				}

				if (this_asset_type == 1) {

					for (int p = 0; p < myshareddata.ParameterCount[this_asset_type]; p++) {

						if (line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])) {

							myEffectDB[this_asset_ID + SPECIALABILITYBUFFER].imported_data[p] = line.substring(line.indexOf(":") + 1);
						}

					}
				}

				if (this_asset_type == 2) {

					for (int p = 0; p < myshareddata.ParameterCount[this_asset_type]; p++) {

						if (line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])) {

							myConditionDB[this_asset_ID].imported_data[p] = line.substring(line.indexOf(":") + 1);
						}

					}
				}
				if (this_asset_type == 3) {

					for (int p = 0; p < myshareddata.ParameterCount[this_asset_type]; p++) {

						if (line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])) {

							myItemDB[this_asset_ID].imported_data[p] = line.substring(line.indexOf(":") + 1);
						}

					}
				}
				if (this_asset_type == 4) {

					for (int p = 0; p < myshareddata.ParameterCount[this_asset_type]; p++) {

						if (line.startsWith(myshareddata.ParameterStrings[this_asset_type][p])) {

							myNPCDB[this_asset_ID].imported_data[p] = line.substring(line.indexOf(":") + 1);
						}

					}
				}

				line = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// /put the imported data in the proper variables

		String ignorethisparameter = null;

		for (int i = 1 + SPECIALABILITYBUFFER; i < 1 + number_of_assets[0] + SPECIALABILITYBUFFER; i++) {

			int current_parameter_number = 0;

			myAbilityDB[i].namestring = myAbilityDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			System.out.println("abil" + i + ":" + myAbilityDB[i].namestring);
			ignorethisparameter = myAbilityDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myAbilityDB[i].enabled = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].description = myAbilityDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myAbilityDB[i].icon_ID = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].passive = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].cast_animation = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].statrequired = StringToStat(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].statrequired_amt = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].abilityspec = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].weapon_required = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].requires_targetting = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].constrain_to_cardinal_direction = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].cardinal_direction_distance_override = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_target_where_no_units_exist = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_target_units = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_target_allies = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_target_self = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_target_enemies = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].require_targetpoint_walkable = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].cast_range_base = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].stat_increasing_range = StringToStat(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].percent_factor_of_stat_increasing_range = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].energy_cost_base = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].stat_reducing_cost = StringToStat(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].percent_factor_of_stat_reducing_cost = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].cooldown_base = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].stat_reducing_cooldown = StringToStat(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].percent_factor_of_stat_reducing_cooldown = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].counts_as_a_movement = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].counts_as_an_attack = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int n = 0; n < 10; n++) {
				myAbilityDB[i].effects[(n)] = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				if (myAbilityDB[i].effects[(n)] > 0) {
					myAbilityDB[i].effects[(n)] += SPECIALEFFECTBUFFER;
				}
			}
			myAbilityDB[i].can_affect_allies = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_affect_self = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].can_affect_enemies = StringToBoolean(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myAbilityDB[i].passive_condition_applied = StringToInteger(myAbilityDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;

		}

		for (int i = 1 + SPECIALEFFECTBUFFER; i < 1 + number_of_assets[1] + SPECIALEFFECTBUFFER; i++) {

			int current_parameter_number = 0;

			myEffectDB[i].namestring = myEffectDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			System.out.println("ef" + i + ":" + myEffectDB[i].namestring);
			ignorethisparameter = myEffectDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myEffectDB[i].enabled = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].action = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].flip_cast_coords = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].trajectory_collision = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].trajectory_endsontop = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].shape_type = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].shape_radius = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].shape_angle_spread = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].can_affect_allies = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].can_affect_caster = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].can_affect_enemies = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].movement_animation = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].movement_speed = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].force_negative_edit = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int n = 0; n < 5; n++) {
				myEffectDB[i].stat_to_edit[n] = StringToStat(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myEffectDB[i].stat_change_amount[n] = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myEffectDB[i].stat_that_affects_change[n] = StringToStat(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myEffectDB[i].stat_that_affects_change_pct[n] = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myEffectDB[i].sets_stat[n] = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myEffectDB[i].use_caster_stats[n] = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				// myEffectDB[i].stat_change_is_temp[n] =
				// StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
				// current_parameter_number++;
			}

			/*
			 * myEffectDB[i].stat_to_edit =
			 * StringToStat(myEffectDB[i].imported_data
			 * [current_parameter_number]); current_parameter_number++;
			 * myEffectDB[i].stat_change_amount =
			 * StringToInteger(myEffectDB[i].imported_data
			 * [current_parameter_number]); current_parameter_number++;
			 * myEffectDB[i].stat_that_affects_change =
			 * StringToStat(myEffectDB[i
			 * ].imported_data[current_parameter_number]);
			 * current_parameter_number++;
			 * myEffectDB[i].stat_that_affects_change_pct =
			 * StringToInteger(myEffectDB
			 * [i].imported_data[current_parameter_number]);
			 * current_parameter_number++;
			 */

			myEffectDB[i].condition_ID = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].duration_base = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].stat_that_affects_duration = StringToStat(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].stat_that_affects_duration_pct = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			// myEffectDB[i].use_caster_stats =
			// StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			// current_parameter_number++;
			myEffectDB[i].projectiletype = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_R = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_G = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_B = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_speed = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_archeight = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_luminescence = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].projectile_requires_unit_on_tile = StringToBoolean(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int n = 0; n < 5; n++) {
				myEffectDB[i].projectile_death_effect[n] = StringToInteger(myEffectDB[i].imported_data[current_parameter_number])
						+ SPECIALEFFECTBUFFER;
				current_parameter_number++;

			}
			myEffectDB[i].unit_type = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].Overlay_GFX_ID = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].Overlay_GFX_R = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].Overlay_GFX_G = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].Overlay_GFX_B = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].Overlay_GFX_Scale = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myEffectDB[i].SOUND_ID = StringToInteger(myEffectDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;

			int count = number_of_assets_in_groups[ASSETTABLE_EFFECTS][myEffectDB[i].action]++;
			assets_in_groups[ASSETTABLE_EFFECTS][myEffectDB[i].action][count] = i;
		}

		for (int i = 1; i < number_of_assets[2] + 1; i++) {

			int current_parameter_number = 0;

			myConditionDB[i].namestring = myConditionDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			System.out.println("cond" + i + ":" + myConditionDB[i].namestring);
			ignorethisparameter = myConditionDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myConditionDB[i].enabled = StringToBoolean(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].description = myConditionDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myConditionDB[i].Icon_ID = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].binds_to_units = StringToBoolean(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].stacks_duration = StringToBoolean(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].stacks_magnitude = StringToBoolean(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].Overlay_GFX_ID = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].Overlay_GFX_R = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].Overlay_GFX_G = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].Overlay_GFX_B = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].Overlay_GFX_Scale = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myConditionDB[i].SOUND_ID = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int j = 0; j < 5; j++) {
				myConditionDB[i].passive_effect[j] = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]) + SPECIALEFFECTBUFFER;
				current_parameter_number++;
			}
			for (int j = 0; j < 5; j++) {
				myConditionDB[i].periodic_effect[j] = StringToInteger(myConditionDB[i].imported_data[current_parameter_number]) + SPECIALEFFECTBUFFER;
				current_parameter_number++;
			}
		}

		for (int i = 1; i < number_of_assets[3] + 1; i++) {
			int current_parameter_number = 0;
			myItemDB[i].namestring = myItemDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			System.out.println("item" + i + ":" + myItemDB[i].namestring);
			ignorethisparameter = myItemDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myItemDB[i].enabled = StringToBoolean(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].description = myItemDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myItemDB[i].Icon_ID = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].purchasable = StringToBoolean(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].value = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].slotassignment = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].equipmodel = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].model_r = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].model_g = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].model_b = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].itemlevel = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].weapontype = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].itemgroup = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myItemDB[i].max_stack_size = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int j = 0; j < 5; j++) {
				myItemDB[i].crafting_material[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myItemDB[i].quantity_used[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
			}

			for (int j = 0; j < 5; j++) {
				myItemDB[i].abilities_granted[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]) + 10;
				current_parameter_number++;
			}
			for (int j = 0; j < 5; j++) {
				myItemDB[i].use_effects[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]) + 10;
				current_parameter_number++;

			}

			for (int j = 0; j < 5; j++) {
				myItemDB[i].stat_to_increase[j] = StringToStat(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myItemDB[i].stat_bonus_amount[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myItemDB[i].stat_affecting_amount[j] = StringToStat(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myItemDB[i].stat_affecting_amount_factor[j] = StringToInteger(myItemDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;

			}

			int count = number_of_assets_in_groups[ASSETTABLE_ITEMS][myItemDB[i].itemgroup]++;
			assets_in_groups[ASSETTABLE_ITEMS][myItemDB[i].itemgroup][count] = i;

		}

		for (int i = 1; i < number_of_assets[4] + 1; i++) {
			int current_parameter_number = 0;
			myNPCDB[i].namestring = myNPCDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			System.out.println("unit" + i + ":" + myNPCDB[i].namestring);
			ignorethisparameter = myNPCDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myNPCDB[i].enabled = StringToBoolean(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].description = myNPCDB[i].imported_data[current_parameter_number];
			current_parameter_number++;
			myNPCDB[i].alignment = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].model_ID = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].sound_id = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].category = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].invulnerable = StringToBoolean(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].immobile = StringToBoolean(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].expirytimer_on_spawn = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_level = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_endurance = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_stamina = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_meleepower = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_rangedpower = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_spellpower = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_armor = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_magres = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_toxres = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_initiative = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].base_speed = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].exp_reward = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			myNPCDB[i].gold_carried = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
			current_parameter_number++;
			for (int j = 0; j < 5; j++) {
				myNPCDB[i].abilities[j] = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				if (myNPCDB[i].abilities[j] > 0) {
					myNPCDB[i].abilities[j] += SPECIALABILITYBUFFER;
				}
			}
			for (int j = 0; j < 5; j++) {
				myNPCDB[i].items_held_default[j] = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
			}
			for (int j = 0; j < 5; j++) {
				myNPCDB[i].items_dropped[j] = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;
				myNPCDB[i].item_drop_percent[j] = StringToInteger(myNPCDB[i].imported_data[current_parameter_number]);
				current_parameter_number++;

			}

			int count = number_of_assets_in_groups[ASSETTABLE_UNITS][myNPCDB[i].category]++;
			assets_in_groups[ASSETTABLE_UNITS][myNPCDB[i].category][count] = i;
		}

	}

	String lastCustomProfileImagePath = "";

	private boolean readCustomProfileImagefromFile(String sFileName) {

		lastCustomProfileImagePath = sFileName;

		BufferedImage tempimage;

		// buffered image to Image2D? use this

		BufferedImage tempimg = null;

		try {
			tempimg = ImageIO.read(new File(sFileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {// this will be passed to the DM!
			myBufferedProfileImage = tempimg.getSubimage(0, 0, 128, 128);
		} catch (Exception e1) {
			myBufferedProfileImage = tempimg;
		}

		try {
			CustomProfileImageTextures[0] = textureLoader.getTexture(myBufferedProfileImage, "customtex" + number_of_customtextures);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		number_of_customtextures++;

		ProfileImages[0] = new Image2D(CustomProfileImageTextures[0]);

		return true;
	}

	void LoadProfileImageWithBufferedImage(int pnum, BufferedImage bufimg) {

		StoredBufferedProfileImages[pnum] = bufimg;

		/*
		 * BufferedImage minimapimage = new BufferedImage(256, 256,
		 * BufferedImage.TYPE_INT_ARGB);
		 * 
		 * 
		 * for (int x = 0; x < 200; x++) { for (int y = 0; y < 200; y++) {
		 * 
		 * 
		 * Color c = new Color(0, 0, 0);
		 * 
		 * minimapimage.setRGB (x, y, c.getRGB() ); } }
		 * 
		 * 
		 * try { minimap_static_texture =
		 * textureLoader.getTexture(minimapimage,"minima"); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// bufimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

		// display is not created or is in the wrong mode!?
		try {
			CustomProfileImageTextures[pnum] = textureLoader.getTexture(bufimg, "profile" + pnum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProfileImages[pnum] = new Image2D(CustomProfileImageTextures[pnum]);

		// com(pnum);
	}

	boolean StringToBoolean(String s) {

		boolean b = false;

		try {
			if (Integer.parseInt(s) == 1) {
				b = true;
			}
		} catch (Exception e) {}

		return b;
	}

	int StringToInteger(String s) {

		int i = 0;

		if (s != null) {
			if (s.indexOf("-") > -1) {// handles numbers like "0-5" and makes it
										// -5
				s = s.substring(s.indexOf("-"));
			}
		}

		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {}

		return i;
	}

	int StringToStat(String s) {

		int i = 0;

		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {}
		
		
if( i < SharedData.StatChoices.length ){
		return SharedData.StatChoices[i];
}

return 0;
		
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

			// System.out.println("Total file size to read (in bytes) : "+
			// fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				// System.out.print((char) content);
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

		mySettings = (Settings) xstream.fromXML(xmldata);

	}

	/*
	 * void createNewNote(int x, int y, int type, String message){
	 * NoteMessages[x][y]= message; map[myshareddata.NOTES_LAYER][y][x]=type; }
	 * 
	 * int num_of_warprifts; int[][] warprift = new int[1000][4]; void
	 * createWarpRift(int x1, int y1, int x2, int y2){//with recycling!
	 * 
	 * int next_available_warpriftID = num_of_warprifts; for(int
	 * i=0;i<num_of_warprifts;i++){ boolean blank = true; for(int j=0;j<4;j++){
	 * if(warprift[i][j]!=0){blank=false;} }
	 * if(blank){next_available_warpriftID=i;} }
	 * 
	 * warprift[next_available_warpriftID][0] = x1;
	 * warprift[next_available_warpriftID][1] = y1;
	 * warprift[next_available_warpriftID][2] = x2;
	 * warprift[next_available_warpriftID][3] = y2;
	 * 
	 * if(next_available_warpriftID==num_of_warprifts){ num_of_warprifts++; }
	 * //System.out.println(num_of_warprifts); }
	 */

	public boolean allbigmenus_notbeinghovered() {

		boolean answer = true;

		int MenusToCheck[] = {MENU_INV, MENU_ESC, MENU_ABILITIES, MENU_EDITSTATS, MENU_LOOTING, MENU_ASSETTABLE, MENU_QUESTTRACKER, MENU_ITEMVENDOR,
				MENU_BATTLERESULTS};

		for (int i = 0; i < MenusToCheck.length; i++) {

			if (Menus[MenusToCheck[i]].BeingHovered_AndOpen(cursor)) {
				answer = false;
			}

		}

		for (int i = 0; i < DialogMenus.length; i++) {

			if (DialogMenus[i].BeingHovered_AndOpen(cursor)) {
				answer = false;
			}

		}

		/*
		 * for (int i = 0; i <= 4; i++) {
		 * 
		 * if (Menus[i].is_open && (cursor.x > Menus[i].x && cursor.x <
		 * Menus[i].x + Menus[i].width && cursor.y > Menus[i].y && cursor.y <
		 * Menus[i].y + Menus[i].height)) { answer = false; }
		 * 
		 * }
		 * 
		 * for (int i = MENU_ASSETTABLE; i <= MENU_ASSETTABLE; i++) {
		 * 
		 * if (Menus[i].is_open && (cursor.x > Menus[i].x && cursor.x <
		 * Menus[i].x + Menus[i].width && cursor.y > Menus[i].y - 30 && cursor.y
		 * < Menus[i].y + Menus[i].height)) { answer = false; }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * Menus[MENU_LOOTING].BeingHovered(cursor)
		 */

		/*
		 * if (lootmenu_orminimenu_beinghovered()) { answer = false; }
		 */

		for (int i = MENU_ASSETTABLE; i <= MENU_ASSETTABLE; i++) {// for
																	// assettable
																	// tabs...

			if (Menus[i].is_open
					&& (cursor.x > Menus[i].x && cursor.x < Menus[i].x + Menus[i].width && cursor.y > Menus[i].y - 30 && cursor.y < Menus[i].y
							+ Menus[i].height)) {
				answer = false;
			}

		}

		return answer;

	}

	/*
	 * public boolean lootmenu_orminimenu_beinghovered() { boolean answer =
	 * false;
	 * 
	 * int rows = ItemMenuOptions.length; int minimenu_x = Menus[MENU_LOOTING].x
	 * + 3 + 40 ((inventoryslot_currentlyselected - 100) % 3) + 10; int
	 * minimenu_y = Menus[MENU_LOOTING].y + 12 + 40
	 * ((inventoryslot_currentlyselected - 100) / 3);// this // is // the //
	 * bottom!
	 * 
	 * if (Menus[MENU_LOOTING].is_open && (cursor.x > Menus[MENU_LOOTING].x - 5
	 * && cursor.x < Menus[MENU_LOOTING].x + Menus[MENU_LOOTING].width + 5 &&
	 * cursor.y > Menus[MENU_LOOTING].y - 5 && cursor.y < Menus[MENU_LOOTING].y
	 * + Menus[MENU_LOOTING].height + 5) || (inventoryslot_currentlyselected >=
	 * 100 && cursor.x > minimenu_x && cursor.x < minimenu_x + 120 && cursor.y >
	 * minimenu_y - (rows * 22) && cursor.y < minimenu_y)) { answer = true; }
	 * return answer; }
	 */

	void playSound(String s) {

		// URL url = this.getClass().getClassLoader().getResource(s);

		Runnable r = new SoundPlayerRunnable(s);
		new Thread(r).start();

	}

	void playSound(String s, int volume) {
		Runnable r = new SoundPlayerRunnable(s, volume);
		new Thread(r).start();
	}

	/*
	 * int GetQuantityOfItemOnTile(int x, int y, int item_ID) { int quantity =
	 * 0;
	 * 
	 * for (int i = 5; i >= 0; i--) { if (ItemContainers[x][y].itemInSlot[i] ==
	 * item_ID) {
	 * 
	 * quantity += ItemContainers[x][y].itemQuantity[i];
	 * 
	 * } }
	 * 
	 * return quantity; }
	 */
	void Client_TellServer_EditTileItemSlotQuantity(int x, int y, int slot, int delta) {

		boolean negative = (delta < 0);
		delta = Math.abs(delta);

		if (slot > -1) {
			if (networkThread_cli != null) {
				networkThread_cli.sendMessage("/edittileitemslotquantity." + ForceStringLength("" + x, 3) + "," + ForceStringLength("" + y, 3) + "."
						+ ForceStringLength("" + slot, 3) + "." + ForceStringLength("" + delta, 3) + "."
						+ ForceStringLength(BooleanToString(negative), 1));

			}

		}
		EditTileItemSlotQuantity(x, y, slot, delta, negative);// do it instantly
	}

	void Server_Broadcast_EditTileItemSlotQuantity(int x, int y, int slot, int delta, boolean negative, int t) {

		if (t == 0) {
			negative = (delta < 0);
		}// set negative if this comes right from the server and not from a
			// client
		delta = Math.abs(delta);

		if (slot > -1) {

			for (int i = 0; i < 9; i++) {
				if (ShouldSendMessagesToPlayer(i) && i != t) {// make sure you
																// dont tell the
																// teller about
																// it!

					networkThread_serv.t[i].sendMessage("/edittileitemslotquantity." + ForceStringLength("" + x, 3) + ","
							+ ForceStringLength("" + y, 3) + "." + ForceStringLength("" + slot, 3) + "." + ForceStringLength("" + delta, 3) + "."
							+ ForceStringLength(BooleanToString(negative), 1));

				}

			}

		}
		EditTileItemSlotQuantity(x, y, slot, delta, negative);// do it instantly
	}

	void EditTileItemSlotQuantity(int x, int y, int slot, int delta, boolean negative) {
		if (slot > -1) {

			if (negative) {
				delta = delta * -1;
			}

			ItemContainers[x][y].itemQuantity[slot] += delta;

			// underflow
			if (ItemContainers[x][y].itemQuantity[slot] <= 0) {
				ItemContainers[x][y].itemInSlot[slot] = 0;
				ItemContainers[x][y].itemQuantity[slot] = 0;

			}

			// overflow
			int item_ID = ItemContainers[x][y].itemInSlot[slot];
			if (ItemContainers[x][y].itemQuantity[slot] > myItemDB[item_ID].max_stack_size) {
				int overflow = ItemContainers[x][y].itemQuantity[slot] - myItemDB[item_ID].max_stack_size;
				ItemContainers[x][y].itemQuantity[slot] = myItemDB[item_ID].max_stack_size;

				int next_free_slot = -1;
				for (int i = 5; i >= 0; i--) {// check for the next open slot
					if (ItemContainers[x][y].itemInSlot[i] == 0) {
						next_free_slot = i;
					}
				}
				if (next_free_slot > -1) {
					ItemContainers[x][y].itemInSlot[next_free_slot] = item_ID;
					ItemContainers[x][y].itemQuantity[next_free_slot] = overflow;
				}

			}

		}
	}

	void Client_TellServer_EditTileItemIDQuantity(int x, int y, int itemID, int delta) {

		boolean negative = (delta < 0);
		delta = Math.abs(delta);

		if (itemID > 0) {
			if (networkThread_cli != null) {
				networkThread_cli.sendMessage("/edittileitemidquantity." + ForceStringLength("" + x, 3) + "," + ForceStringLength("" + y, 3) + "."
						+ ForceStringLength("" + itemID, 6) + "." + ForceStringLength("" + delta, 3) + "."
						+ ForceStringLength(BooleanToString(negative), 1));

			}

		}
		EditTileItemIDQuantity(x, y, itemID, delta, negative);// do it instantly
	}

	void Server_Broadcast_EditTileItemIDQuantity(int x, int y, int itemID, int delta, boolean negative, int t) {

		if (t == 0) {
			negative = (delta < 0);
		}// set negative if this comes right from the server and not from a
			// client
		delta = Math.abs(delta);

		if (itemID > 0) {

			for (int i = 0; i < 9; i++) {
				if (ShouldSendMessagesToPlayer(i) && i != t) {// make sure you
																// dont tell the
																// teller about
																// it!

					networkThread_serv.t[i].sendMessage("/edittileitemidquantity." + ForceStringLength("" + x, 3) + ","
							+ ForceStringLength("" + y, 3) + "." + ForceStringLength("" + itemID, 6) + "." + ForceStringLength("" + delta, 3) + "."
							+ ForceStringLength(BooleanToString(negative), 1));

				}

			}

		}
		EditTileItemIDQuantity(x, y, itemID, delta, negative);// do it instantly
	}

	void EditTileItemIDQuantity(int x, int y, int item_ID, int delta, boolean negative) {

		if (negative) {
			delta = delta * -1;
		}

		if (item_ID > 0) {

			if (delta > 0) {// adding items
				for (int j = 0; j < delta; j++) {
					int slot_to_edit = -1;
					int slot_already_holding_item = -1;
					int next_free_slot = -1;

					for (int i = 5; i >= 0; i--) {// check for the next open
													// slot
						if (ItemContainers[x][y].itemInSlot[i] == 0) {
							next_free_slot = i;
						}
					}

					for (int i = 5; i >= 0; i--) {
						if (ItemContainers[x][y].itemInSlot[i] == item_ID
								&& (myItemDB[item_ID].max_stack_size > ItemContainers[x][y].itemQuantity[i])) {
							slot_already_holding_item = i;
						}
					}

					if (slot_already_holding_item > -1) {
						slot_to_edit = slot_already_holding_item;
					} else {
						slot_to_edit = next_free_slot;
					}

					if (slot_to_edit >= 0) {
						ItemContainers[x][y].itemInSlot[slot_to_edit] = item_ID;
						ItemContainers[x][y].itemQuantity[slot_to_edit]++;

						if (ItemContainers[x][y].itemQuantity[slot_to_edit] <= 0) {
							ItemContainers[x][y].itemInSlot[slot_to_edit] = 0;
							ItemContainers[x][y].itemQuantity[slot_to_edit] = 0;

						}
					}
				}
			}

			if (delta < 0) {// /removing items
				for (int j = 0; j < (Math.abs(delta)); j++) {
					int slot_to_edit = -1;
					int slot_already_holding_item = -1;

					for (int i = 5; i >= 0; i--) {

						debug("DOLPHEE" + myItemDB[item_ID].max_stack_size + "." + ItemContainers[x][y].itemQuantity[i]);

						if (ItemContainers[x][y].itemInSlot[i] == item_ID) {
							slot_already_holding_item = i;
						}
					}

					slot_to_edit = slot_already_holding_item;

					if (slot_to_edit >= 0) {
						// ItemContainers[x][y].itemInSlot[slot_to_edit] =
						// item_ID;
						ItemContainers[x][y].itemQuantity[slot_to_edit]--;

						if (ItemContainers[x][y].itemQuantity[slot_to_edit] <= 0) {
							ItemContainers[x][y].itemInSlot[slot_to_edit] = 0;
							ItemContainers[x][y].itemQuantity[slot_to_edit] = 0;

						}

					} else {
						debug("Could not decrement that item's quantity! Not existant?");
					}

				}
			}
		}
	}

	void Client_TellServer_EditUnitItemSlotQuantity(int x, int y, int slot, int delta) {

		boolean negative = (delta < 0);
		delta = Math.abs(delta);

		if (slot > -1) {
			if (networkThread_cli != null) {
				networkThread_cli.sendMessage("/editunititemslotquantity." + ForceStringLength("" + x, 3) + "," + ForceStringLength("" + y, 3) + "."
						+ ForceStringLength("" + slot, 3) + "." + ForceStringLength("" + delta, 3) + "."
						+ ForceStringLength(BooleanToString(negative), 1));

			}

		}
		EditUnitItemSlotQuantity(x, y, slot, delta, negative);// do it instantly
	}

	void Server_Broadcast_EditUnitItemSlotQuantity(int x, int y, int slot, int delta, boolean negative, int t) {

		if (t == 0) {
			negative = (delta < 0);
		}// set negative if this comes right from the server and not from a
			// client
		delta = Math.abs(delta);

		if (slot > -1) {

			for (int i = 0; i < 9; i++) {
				if (ShouldSendMessagesToPlayer(i) && i != t) {// make sure you
																// dont tell the
																// teller about
																// it!

					networkThread_serv.t[i].sendMessage("/editunititemslotquantity." + ForceStringLength("" + x, 3) + ","
							+ ForceStringLength("" + y, 3) + "." + ForceStringLength("" + slot, 3) + "." + ForceStringLength("" + delta, 3) + "."
							+ ForceStringLength(BooleanToString(negative), 1));

				}

			}

		}
		EditUnitItemSlotQuantity(x, y, slot, delta, negative);// do it instantly
	}

	void EditUnitItemSlotQuantity(int x, int y, int slot, int delta, boolean negative) {
		int UnitID = UnitMap[x][y];
		if (slot > -1 && UnitID > 0) {

			if (negative) {
				delta = delta * -1;
			}

			Units[UnitID].itemQuantity[slot] += delta;

			// underflow
			if (Units[UnitID].itemQuantity[slot] <= 0) {
				Units[UnitID].itemInSlot[slot] = 0;
				Units[UnitID].itemQuantity[slot] = 0;

			}

			// overflow
			int item_ID = Units[UnitID].itemInSlot[slot];
			if (Units[UnitID].itemQuantity[slot] > myItemDB[item_ID].max_stack_size) {
				int overflow = Units[UnitID].itemQuantity[slot] - myItemDB[item_ID].max_stack_size;
				Units[UnitID].itemQuantity[slot] = myItemDB[item_ID].max_stack_size;

				int next_free_slot = -1;
				for (int i = 5; i >= 0; i--) {// check for the next open slot
					if (Units[UnitID].itemInSlot[i] == 0) {
						next_free_slot = i;
					}
				}
				if (next_free_slot > -1) {
					Units[UnitID].itemInSlot[next_free_slot] = item_ID;
					Units[UnitID].itemQuantity[next_free_slot] = overflow;
				}

			}

		}
	}

	void Client_TellServer_EditUnitItemIDQuantity(int x, int y, int itemID, int delta) {

		boolean negative = (delta < 0);
		delta = Math.abs(delta);

		if (itemID > 0) {
			if (networkThread_cli != null) {
				networkThread_cli.sendMessage("/editunititemidquantity." + ForceStringLength("" + x, 3) + "," + ForceStringLength("" + y, 3) + "."
						+ ForceStringLength("" + itemID, 6) + "." + ForceStringLength("" + delta, 3) + "."
						+ ForceStringLength(BooleanToString(negative), 1));

			}

		}
		EditUnitItemIDQuantity(x, y, itemID, delta, negative);// do it instantly
	}

	void Server_Broadcast_EditUnitItemIDQuantity(int x, int y, int itemID, int delta, boolean negative, int t) {

		if (t == 0) {
			negative = (delta < 0);
		}// set negative if this comes right from the server and not from a
			// client
		delta = Math.abs(delta);

		if (itemID > 0) {

			for (int i = 0; i < 9; i++) {
				if (ShouldSendMessagesToPlayer(i) && i != t) {// make sure you
																// dont tell the
																// teller about
																// it!

					networkThread_serv.t[i].sendMessage("/editunititemidquantity." + ForceStringLength("" + x, 3) + ","
							+ ForceStringLength("" + y, 3) + "." + ForceStringLength("" + itemID, 6) + "." + ForceStringLength("" + delta, 3) + "."
							+ ForceStringLength(BooleanToString(negative), 1));

				}

			}

		}
		EditUnitItemIDQuantity(x, y, itemID, delta, negative);// do it instantly
	}

	void EditUnitItemIDQuantity(int x, int y, int item_ID, int delta, boolean negative) {
		int UnitID = UnitMap[x][y];
		if (item_ID > 0 && UnitID > 0) {

			if (negative) {
				delta = delta * -1;
			}

			if (delta > 0) {// adding items
				for (int j = 0; j < delta; j++) {
					int slot_to_edit = -1;
					int slot_already_holding_item = -1;
					int next_free_slot = -1;

					for (int i = 49; i >= 0; i--) {// check for the next open
													// slot
						if (Units[UnitID].itemInSlot[i] == 0) {
							next_free_slot = i;
						}
					}

					for (int i = 49; i >= 0; i--) {
						if (Units[UnitID].itemInSlot[i] == item_ID && (myItemDB[item_ID].max_stack_size > Units[UnitID].itemQuantity[i])) {
							slot_already_holding_item = i;
						}
					}

					if (slot_already_holding_item > -1) {
						slot_to_edit = slot_already_holding_item;
					} else {
						slot_to_edit = next_free_slot;
					}

					if (slot_to_edit >= 0) {
						Units[UnitID].itemInSlot[slot_to_edit] = item_ID;
						Units[UnitID].itemQuantity[slot_to_edit]++;

						if (Units[UnitID].itemQuantity[slot_to_edit] <= 0) {
							Units[UnitID].itemInSlot[slot_to_edit] = 0;
							Units[UnitID].itemQuantity[slot_to_edit] = 0;

						}

					}

				}
			}

			if (delta < 0) {// /removing items
				for (int j = 0; j < (Math.abs(delta)); j++) {
					int slot_to_edit = -1;
					int slot_already_holding_item = -1;

					for (int i = 49; i >= 0; i--) {
						if (Units[UnitID].itemInSlot[i] == item_ID) {
							slot_already_holding_item = i;
						}
					}

					slot_to_edit = slot_already_holding_item;

					if (slot_to_edit >= 0) {
						// Units[UnitID].itemInSlot[slot_to_edit] = item_ID;
						Units[UnitID].itemQuantity[slot_to_edit]--;

						if (Units[UnitID].itemQuantity[slot_to_edit] <= 0) {
							Units[UnitID].itemInSlot[slot_to_edit] = 0;
							Units[UnitID].itemQuantity[slot_to_edit] = 0;

						}

					} else {
						debug("Could not decrement that item's quantity! Not existant?");
					}

				}
			}

		}
	}

	/*
	 * int GetQuantityOfItemOnUnit(int x, int y, int item_ID) { int quantity =
	 * 0;
	 * 
	 * //int slot_that_contains_that_itemID = -1; int UnitID = UnitMap[x][y];
	 * 
	 * if (UnitID > 0) {
	 * 
	 * for (int i = 49; i >= 0; i--) { if (Units[UnitID].itemInSlot[i] ==
	 * item_ID) {
	 * 
	 * quantity += Units[UnitID].itemQuantity[i];
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * return quantity; }
	 */

	/*
	 * void Client_TellServer_SetUnitItemQuantity(int x, int y, int item_ID, int
	 * quantity) {
	 * 
	 * if (item_ID > 0) { tempstring[0] = "" + x; tempstring[1] = "" + y;
	 * tempstring[2] = "" + item_ID; tempstring[3] = "" + quantity;
	 * 
	 * for (int n = 0; n <= 3; n++) { if (tempstring[n].length() == 1) {
	 * tempstring[n] = "00" + tempstring[n]; } else if (tempstring[n].length()
	 * == 2) { tempstring[n] = "0" + tempstring[n]; } }
	 * 
	 * if (networkThread_cli != null) {
	 * 
	 * networkThread_cli.sendMessage("/setunititemqt." + tempstring[0] + "," +
	 * tempstring[1] + "." + tempstring[2] + "." + tempstring[3]);
	 * 
	 * }
	 * 
	 * } SetUnitItemQuantity(x, y, item_ID, quantity); }
	 * 
	 * void Server_Broadcast_SetUnitItemQuantity(int x, int y, int item_ID, int
	 * quantity) {
	 * 
	 * if (item_ID > 0) {
	 * 
	 * // communicate this change to the clients
	 * 
	 * String[] s = { "" + x, "" + y, "" + item_ID, "" + quantity };
	 * GenericServerBroadcast("/setunititemqt", s);
	 * 
	 * SetUnitItemQuantity(x, y, item_ID, quantity); } }
	 * 
	 * void Client_Receive_SetUnitItemQuantity(int x, int y, int item_ID, int
	 * quantity) { if (item_ID > 0) { SetUnitItemQuantity(x, y, item_ID,
	 * quantity); } }
	 * 
	 * void SetUnitItemQuantity(int x, int y, int item_ID, int quantity) { //
	 * boolean stacking = false; if (item_ID > 0) { int UnitID = UnitMap[x][y];
	 * if (UnitID > 0) { int oldquantity = GetQuantityOfItemOnUnit(x, y,
	 * item_ID);
	 * 
	 * // check for first open slot int bag_slot_to_edit =
	 * getFirstFreeBagSlot(UnitID);
	 * 
	 * for (int i = 49; i >= 0; i--) {// if the item already exists // somewhere
	 * in the inv, // override the slot so // STACKING occurs! if
	 * (Units[UnitID].itemInSlot[i] == item_ID &&
	 * (myItemDB[item_ID].item_can_stack || quantity < oldquantity)) {
	 * bag_slot_to_edit = i; // stacking=true; } }
	 * 
	 * if (bag_slot_to_edit >= 0) { Units[UnitID].itemInSlot[bag_slot_to_edit] =
	 * item_ID; Units[UnitID].itemQuantity[bag_slot_to_edit] = quantity;
	 * 
	 * if (Units[UnitID].itemQuantity[bag_slot_to_edit] <= 0) {
	 * Units[UnitID].itemInSlot[bag_slot_to_edit] = 0;
	 * Units[UnitID].itemQuantity[bag_slot_to_edit] = 0;
	 * 
	 * }
	 * 
	 * // return first_free_bag_slot; //
	 * if(!stacking){ItemContainers[x][y].number_of_slots_with_items++;}
	 * 
	 * }
	 * 
	 * } } // return -1; }
	 */

	void Client_TellServer_SwapUnitBagSlotItems(int x, int y, int slot_1, int slot_2) {

		tempstring[0] = "" + x;
		tempstring[1] = "" + y;
		tempstring[2] = "" + slot_1;
		tempstring[3] = "" + slot_2;

		for (int n = 0; n <= 3; n++) {
			if (tempstring[n].length() == 1) {
				tempstring[n] = "00" + tempstring[n];
			} else if (tempstring[n].length() == 2) {
				tempstring[n] = "0" + tempstring[n];
			}
		}

		if (networkThread_cli != null) {

			networkThread_cli.sendMessage("/swapunitbagslotitems." + tempstring[0] + "," + tempstring[1] + "." + tempstring[2] + "." + tempstring[3]);

		}

		// RemoveItemFromTile(x, y, slot); DO NOT DO THIS. quantity based
		// system!!!
	}

	void Server_BroadcastSwapUnitBagSlotItems(int x, int y, int slot_1, int slot_2) {

		// communicate this change to the clients

		tempstring[0] = "" + x;
		tempstring[1] = "" + y;
		tempstring[2] = "" + slot_1;
		tempstring[3] = "" + slot_2;

		for (int n = 0; n <= 3; n++) {
			if (tempstring[n].length() == 1) {
				tempstring[n] = "00" + tempstring[n];
			} else if (tempstring[n].length() == 2) {
				tempstring[n] = "0" + tempstring[n];
			}
		}
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				networkThread_serv.t[i].sendMessage("/swapunitbagslotitems." + tempstring[0] + "," + tempstring[1] + "." + tempstring[2] + "."
						+ tempstring[3]);

			};
		}

		SwapUnitBagSlotItems(x, y, slot_1, slot_2);
	}

	/*
	 * void Client_ReceiveSwapUnitBagSlotItems(int x, int y, int slot_1, int
	 * slot_2) {
	 * 
	 * SwapUnitBagSlotItems(x, y, slot_1, slot_2); }
	 */

	void SwapUnitBagSlotItems(int x, int y, int slot_1, int slot_2) {

		int UnitID = UnitMap[x][y];
		if (UnitID > 0) {

			int stored_item_id = Units[UnitID].itemInSlot[slot_1];
			int stored_quantity = Units[UnitID].itemQuantity[slot_1];

			Units[UnitID].itemInSlot[slot_1] = Units[UnitID].itemInSlot[slot_2];
			Units[UnitID].itemQuantity[slot_1] = Units[UnitID].itemQuantity[slot_2];
			Units[UnitID].itemInSlot[slot_2] = stored_item_id;
			Units[UnitID].itemQuantity[slot_2] = stored_quantity;

			InvMenu_BonusStats_MustBeRefreshed = true;

		}
	}

	void DrawTerrainTile(int tileset, int subtile, int mapX, int mapY, int x, int y, int layer) {

		if (subtile != 10 && subtile != 15 && subtile != 16 && subtile != 17) {
			int tileset_2 = 0;

			if (layer == myshareddata.TERRAIN_LAYER_LOWER) {

				tileset_2 = 0;

				// cardinals
				if (subtile == 7 && mapY > 0) {
					tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY - 1][mapX];
				}
				if (subtile == 9 && mapX > 0) {
					tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX - 1];
				}
				if (subtile == 11 && mapX < SharedData.MAP_SIZE - 1) {
					tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX + 1];
				}
				if (subtile == 13 && mapY < SharedData.MAP_SIZE - 1) {
					tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY + 1][mapX];
				}

				// diags
				if (subtile == 4 || subtile == 8) {
					if (mapY > 0 && mapX < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY - 1][mapX + 1];
					}
					if (mapY > 0 && !(mapX < SharedData.MAP_SIZE - 1)) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY - 1][mapX];
					}
					if (!(mapY > 0) && mapX < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX + 1];
					}
				}

				if (subtile == 5 || subtile == 6) {
					if (mapY > 0 && mapX > 0) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY - 1][mapX - 1];
					}
					if (!(mapY > 0) && mapX > 0) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX - 1];
					}
					if (mapY > 0 && !(mapX > 0)) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY - 1][mapX];
					}
				}

				if (subtile == 2 || subtile == 12) {
					if (mapX > 0 && mapY < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY + 1][mapX - 1];
					}
					if (!(mapX > 0) && mapY < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY + 1][mapX];
					}
					if (mapX > 0 && !(mapY < SharedData.MAP_SIZE - 1)) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX - 1];
					}
				}

				if (subtile == 1 || subtile == 14) {
					if (mapX < SharedData.MAP_SIZE - 1 && mapY < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY + 1][mapX + 1];
					}
					if (!(mapX < SharedData.MAP_SIZE - 1) && mapY < SharedData.MAP_SIZE - 1) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY + 1][mapX];
					}
					if (mapX < SharedData.MAP_SIZE - 1 && !(mapY < SharedData.MAP_SIZE - 1)) {
						tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX + 1];
					}
				}

			} else {
				tileset_2 = map[myshareddata.TERRAIN_LAYER_LOWER][mapY][mapX];
			}

			if (tileset_2 > 0) {
				tileset_2 = tileset_2 - 1;
				final int subtile_2 = 10;

				// broad tuning
				int tex_x_2 = (tileset_2 % 8) * 120;
				int tex_y_2 = (tileset_2 / 8) * 240;

				// fine tuning
				tex_x_2 += 40 * (subtile_2 % 3);
				tex_y_2 += 40 * (subtile_2 / 3);

				// draw the blender underneath!
				MasterTerrainSheet.draw_subimage(tex_x_2, tex_y_2, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}

		}

		// now do the real thing
		// broad tuning
		int tex_x = (tileset % 8) * 120;
		int tex_y = (tileset / 8) * 240;

		// fine tuning
		tex_x += 40 * (subtile % 3);
		tex_y += 40 * (subtile / 3);

		MasterTerrainSheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

	}

	int GetTerrainSubtile(int mapX, int mapY, int layer) {

		int subtile = 0;

		// starts directly above and goes clockwise
		boolean[] same = new boolean[8];

		// GET SAMES
		if (mapY >= 1) {
			if (map[layer][mapY][mapX] == map[layer][mapY - 1][mapX]) {
				same[0] = true;
			}
		}
		if (mapY >= 1 && mapX <= 98) {
			if (map[layer][mapY][mapX] == map[layer][mapY - 1][mapX + 1]) {
				same[1] = true;
			}
		}
		if (mapX <= 98) {
			if (map[layer][mapY][mapX] == map[layer][mapY][mapX + 1]) {
				same[2] = true;
			}
		}
		if (mapY <= 98 && mapX <= 98) {
			if (map[layer][mapY][mapX] == map[layer][mapY + 1][mapX + 1]) {
				same[3] = true;
			}
		}
		if (mapY <= 98) {
			if (map[layer][mapY][mapX] == map[layer][mapY + 1][mapX]) {
				same[4] = true;
			}
		}
		if (mapY <= 98 && mapX >= 1) {
			if (map[layer][mapY][mapX] == map[layer][mapY + 1][mapX - 1]) {
				same[5] = true;
			}
		}
		if (mapX >= 1) {
			if (map[layer][mapY][mapX] == map[layer][mapY][mapX - 1]) {
				same[6] = true;
			}
		}
		if (mapY >= 1 && mapX >= 1) {
			if (map[layer][mapY][mapX] == map[layer][mapY - 1][mapX - 1]) {
				same[7] = true;
			}
		}
		// DONE GETTING SAMES

		// add more here, take height precedence into effect!

		if (same[0] && same[2] && same[4] && same[6]) {

			subtile = 10;

			// corners
			if (!same[1]) {
				subtile = 4;
			}
			if (!same[3]) {
				subtile = 1;
			}
			if (!same[5]) {
				subtile = 2;
			}
			if (!same[7]) {
				subtile = 5;
			}

		}

		// cardinals
		if (same[0] && same[2] && same[4] && !same[6]) {
			subtile = 9;
		}
		if (same[0] && same[4] && same[6] && !same[2]) {
			subtile = 11;
		}
		if (same[2] && same[4] && same[6] && !same[0]) {
			subtile = 7;
		}
		if (same[2] && same[0] && same[6] && !same[4]) {
			subtile = 13;
		}

		// diags
		if (same[2] && same[4] && !same[0] && !same[6] && same[3]) {
			subtile = 6;
		}
		if (same[4] && same[6] && !same[2] && !same[0] && same[5]) {
			subtile = 8;
		}
		if (same[0] && same[2] && !same[6] && !same[4] && same[1]) {
			subtile = 12;
		}
		if (same[0] && same[6] && !same[2] && !same[4] && same[7]) {
			subtile = 14;
		}

		if (map[layer][mapY][mapX] == 1) {
			subtile = 10;
		}

		if (subtile == 10 && TerrainVariantMap[mapX][mapY] > 0) {

			subtile += 4 + TerrainVariantMap[mapX][mapY];

		}

		if (subtile == 0 && TerrainVariantMap[mapX][mapY] > 0) {

			subtile = 3;

		}

		return subtile;
	}

	int GetRoofSubtile(int mapX, int mapY) {

		int subtile = 0;

		// starts directly above and goes clockwise
		boolean[] same = new boolean[8];

		// GET SAMES
		if (mapY >= 1) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY - 1][mapX]) {
				same[0] = true;
			}
		}
		if (mapY >= 1 && mapX <= 98) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY - 1][mapX + 1]) {
				same[1] = true;
			}
		}
		if (mapX <= 98) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX + 1]) {
				same[2] = true;
			}
		}
		if (mapY <= 98 && mapX <= 98) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY + 1][mapX + 1]) {
				same[3] = true;
			}
		}
		if (mapY <= 98) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY + 1][mapX]) {
				same[4] = true;
			}
		}
		if (mapY <= 98 && mapX >= 1) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY + 1][mapX - 1]) {
				same[5] = true;
			}
		}
		if (mapX >= 1) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY][mapX - 1]) {
				same[6] = true;
			}
		}
		if (mapY >= 1 && mapX >= 1) {
			if (define_regions[SharedData.REGION_INDOORS - 800][mapY - 1][mapX - 1]) {
				same[7] = true;
			}
		}
		// DONE GETTING SAMES

		// add more here, take height precedence into effect!

		if (same[0] && same[2] && same[4] && same[6]) {

			subtile = 6;

			// inner corners
			if (!same[1]) {
				subtile = 1;
			}
			if (!same[3]) {
				subtile = 9;
			}
			if (!same[5]) {
				subtile = 8;
			}
			if (!same[7]) {
				subtile = 1;
			}

		}

		// cardinals
		if (same[0] && same[2] && same[4] && !same[6]) {
			subtile = 5;
		}
		if (same[0] && same[4] && same[6] && !same[2]) {
			subtile = 7;
		}
		if (same[2] && same[4] && same[6] && !same[0]) {
			subtile = 1;
		}
		if (same[2] && same[0] && same[6] && !same[4]) {
			subtile = 11;
		}

		// diags
		if (same[2] && same[4] && !same[0] && !same[6] && same[3]) {
			subtile = 0;
		}
		if (same[4] && same[6] && !same[2] && !same[0] && same[5]) {
			subtile = 2;
		}
		if (same[0] && same[2] && !same[6] && !same[4] && same[1]) {
			subtile = 10;
		}
		if (same[0] && same[6] && !same[2] && !same[4] && same[7]) {
			subtile = 12;
		}

		if (subtile == 6 && TerrainVariantMap[mapX][mapY] == 1) {
			subtile = 3;
		}
		if (subtile == 6 && TerrainVariantMap[mapX][mapY] == 2) {
			subtile = 4;
		}

		return subtile;
	}

	void DrawRoofTile(int mapX, int mapY, int x, int y) {

		int subtile = GetRoofSubtile(mapX, mapY);

		int tex_x = 40 * (subtile % 5);
		int tex_y = 40 * (subtile / 5);

		RoofSheet.draw_subimage(tex_x, tex_y, x * TILE_SIZE, y * TILE_SIZE - 80, TILE_SIZE, TILE_SIZE);

	}

	void DrawUnitModel(int UnitID, int x, int y) {

		int facing = Units[UnitID].stat[myshareddata.FACING];
		int animation = Units[UnitID].current_animation;
		int unit_animationframe = Units[UnitID].current_animation_frame;

		float alpha = 1f;
		if (Units[UnitID].IsInvisible) {
			alpha = 0.2f;
		}

		int model_ID = Units[UnitID].current_model;
		Model thismodel = myUnitModels[model_ID];

		int framewidth = thismodel.frame_width;

		if (thismodel.image != null) {

			if (Units[UnitID].is_recoiling) {
				glBlendFunc(GL_ONE, GL_ONE);
			}

			thismodel.image.bind();
			thismodel.image.begin();

			int tex_x = 0;
			int tex_y = 0;

			if (thismodel.spritesheet_type == 1) {
				tex_x += unit_animationframe * framewidth;

				tex_y = framewidth * 4 * 2;// Default
				if (animation == myshareddata.ANIM_CAST) {
					tex_y = framewidth * 4 * 0;
				}
				if (animation == myshareddata.ANIM_THRUST) {
					tex_y = framewidth * 4 * 1;
				}
				if (animation == myshareddata.ANIM_SWING) {
					tex_y = framewidth * 4 * 3;
				}
				if (animation == myshareddata.ANIM_SHOOT) {
					tex_y = framewidth * 4 * 4;
				}
				if (animation == myshareddata.ANIM_DEATH) {
					tex_y = framewidth * 4 * 5;
				}

			}

			if (thismodel.spritesheet_type != 2 && animation != myshareddata.ANIM_DEATH) {// /all
																							// models
																							// can
																							// do
				// facing
				if (facing == 0) {
					tex_y += framewidth * 0;
				}
				if (facing == 1) {
					tex_y += framewidth * 3;
				}
				if (facing == 2) {
					tex_y += framewidth * 2;
				}
				if (facing == 3) {
					tex_y += framewidth * 1;
				}
			}

			if (thismodel.spritesheet_type == 2) {
				tex_x += unit_animationframe * framewidth;

				/*
				 * tex_y = framewidth * 4 * 0;// Default if (animation ==
				 * myshareddata.ANIM_STAND) { tex_y = framewidth * 4 * 0; }
				 */
				if (animation == myshareddata.ANIM_WALK) {
					tex_y = framewidth * 4 * 1;
				}
				if (animation == myshareddata.ANIM_CAST) {
					tex_y = framewidth * 4 * 2;
				}
				if (animation == myshareddata.ANIM_THRUST) {
					tex_y = framewidth * 4 * 2;
				}
				if (animation == myshareddata.ANIM_SWING) {
					tex_y = framewidth * 4 * 2;
				}
				if (animation == myshareddata.ANIM_SHOOT) {
					tex_y = framewidth * 4 * 2;
				}
				if (animation == myshareddata.ANIM_DEATH) {
					tex_y = framewidth * 4 * 3;
				}

				if (facing == 0) {
					tex_y += framewidth * 0;
				}
				if (facing == 1) {
					tex_y += framewidth * 3;
				}
				if (facing == 2) {
					tex_y += framewidth * 2;
				}
				if (facing == 3) {
					tex_y += framewidth * 1;
				}

			}

			int buffer_width = framewidth - 64;
			if (buffer_width > 0) {
				x -= buffer_width / 2;
				y -= buffer_width / 2;
			}

			x += thismodel.offset_x;
			y += thismodel.offset_y;

			GL11.glColor4f(1, 1, 1, alpha);
			thismodel.image.draw_subimage(tex_x, tex_y, x, y, framewidth, framewidth);

			thismodel.image.end();

			GL11.glColor3f(1, 1, 1);

			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			if (thismodel.item_compatible) {
				for (int i = 0; i < myshareddata.ItemDrawOrder_OnTop[facing].length; i++) {

					int nextSlotToDraw = myshareddata.ItemDrawOrder_OnTop[facing][i];

					int item = Units[UnitID].itemInSlot[nextSlotToDraw];

					int model = myItemDB[item].equipmodel;

					int wep_tex_x = tex_x;
					int wep_tex_y = tex_y;

					float r = myItemDB[item].model_r;
					float g = myItemDB[item].model_g;
					float b = myItemDB[item].model_b;

					if (nextSlotToDraw == SharedData.ITEMSLOT_HEAD) {// draw
																		// hair
																		// on
																		// humans,
																		// not
																		// baldness

						if (model == 0) {
							model = 1;
							r = 50f;
							g = 40f;
							b = 30f;

							if (model_ID == SharedData.UNITMODEL_SKELEBODY) {
								r = 90f;
								g = 90f;
								b = 90f;
							}
						}
					}

					boolean large_itemsheet = false;
					if (model == SharedData.ITEMMODEL_RAPIER || model == SharedData.ITEMMODEL_LONGSWORD) {
						large_itemsheet = true;
					}

					if (model > 0) {

						GL11.glColor4f(r / 100f, g / 100f, b / 100f, alpha);

						if (!large_itemsheet) {
							// draw normal-sized items

							HeroSheet_Items[model].bind();
							HeroSheet_Items[model].begin();

							HeroSheet_Items[model].draw_subimage(wep_tex_x, wep_tex_y, x, y, 64, 64);

							HeroSheet_Items[model].end();

						} else {

							if (animation == 0 && !Units[UnitID].is_sliding) {// draw
																				// ginormous
																				// weaposn
																				// while
																				// standing

								int y_index = 0;

								if (facing == 0) {
									y_index = 0;
								}
								if (facing == 1) {
									y_index = 3;
								}
								if (facing == 2) {
									y_index = 2;
								}
								if (facing == 3) {
									y_index = 1;
								}

								int big_x = tex_x * 3;
								int big_y = y_index * 64 * 3;

								HeroSheet_Items[model].bind();
								HeroSheet_Items[model].begin();

								HeroSheet_Items[model].draw_subimage(big_x, big_y, x - 64, y - 64, 64 * 3, 64 * 3);

								HeroSheet_Items[model].end();

							}

							if (animation == myshareddata.ANIM_SWING) {// draw
																		// GINORMOUS
																		// weapons
																		// being
																		// swung!

								int y_index = 0;

								if (facing == 0) {
									y_index = 0;
								}
								if (facing == 1) {
									y_index = 3;
								}
								if (facing == 2) {
									y_index = 2;
								}
								if (facing == 3) {
									y_index = 1;
								}

								int big_x = tex_x * 3;
								int big_y = y_index * 64 * 3;

								HeroSheet_Items[model].bind();
								HeroSheet_Items[model].begin();

								HeroSheet_Items[model].draw_subimage(big_x, big_y, x - 64, y - 64, 64 * 3, 64 * 3);

								HeroSheet_Items[model].end();

							}

						}

					}

				}
			}

		}
	}

	void DrawTextBox(int ID) {
		// if (myTextBoxes[ID].isVisible) {
		int x = myTextBoxes[ID].x + MainMenu_tween_x;
		int y = myTextBoxes[ID].y + MainMenu_tween_y;

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		if (myTextBoxes[ID].image == null) {
			/*
			 * if (!myTextBoxes[ID].Has_Focus) { GL11.glColor3f(0.7f, 0.7f,
			 * 0.7f); }
			 */
			Fill_Rect(x, y, myTextBoxes[ID].width, myTextBoxes[ID].height);
		} else {
			myTextBoxes[ID].image.draw(x, y);
		}

		// Verdana_12.drawString(x, y - 20, myTextBoxes[ID].label);
		GL11.glColor3f(0f, 0f, 0f);

		String text_to_draw = myTextBoxes[ID].text;

		if (myTextBoxes[ID].hidechars) {
			int length = text_to_draw.length();
			text_to_draw = "";
			for (int i = 0; i < length; i++) {
				text_to_draw += '*';
			}
		}

		if (myTextBoxes[ID].text.length() == 0) {

			if (myTextBoxes[ID].Has_Focus) {
				text_to_draw = "|";
			} else {
				text_to_draw = myTextBoxes[ID].label;
			}

			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		} else {
			if (myTextBoxes[ID].Has_Focus && (animationframe % 32) / 16 == 0) {
				text_to_draw += "|";
			}
		}

		if (!myTextBoxes[ID].multilined) {
			Verdana_16.drawString(x + 5, y + 8, text_to_draw);
		} else {
			Verdana_16.drawString(x + 5, y + 3, text_to_draw, myTextBoxes[ID].width - 50, myTextBoxes[ID].height - 20, 20);
		}

		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		// }
	}

	void DrawSideButton(SimpleGuiObject SideButton) {

		GL11.glColor4f(0.6f, 0.6f, 0.6f, 0.6f);
		if (SideButton.BeingHovered(cursor)) {
			GL11.glColor3f(0.8f, 0.8f, 0.8f);
		}

		boolean TextureModeWasEnabled = false;
		if (GL11.glIsEnabled(GL_TEXTURE_2D)) {
			TextureModeWasEnabled = true;
			GL11.glEnable(GL_TEXTURE_2D);
		}

		SideButton.background.bind();
		SideButton.background.begin();

		SideButton.background.draw_subimage_stretch(0, 0, SideButton.x, SideButton.y, SideButton.background.getWidth(),
				SideButton.background.getHeight(), SideButton.width, SideButton.height);

		SideButton.background.end();

		if (!TextureModeWasEnabled) {
			GL11.glDisable(GL_TEXTURE_2D);
		}

		GL11.glColor3f(1, 1, 1);

		if (cursor.x > SideButton.x && cursor.x < SideButton.x + SideButton.width && cursor.y > SideButton.y
				&& cursor.y < SideButton.y + SideButton.height) {
			DrawMiniHoverTooltip(SideButton.text);
		}
		/*
		 * button_bg_granite[0].draw(SideButton[menu].x, SideButton[menu].y);
		 * 
		 * int hoveranimoffset = 0; if ( SideButton[menu].BeingHovered(cursor) )
		 * { hoveranimoffset = 4; GL11.glColor3f(0.3f, 1.0f, 0.2f); }
		 * 
		 * button_generic_back[((animationframe%16)/4 ) + hoveranimoffset]
		 * .draw(SideButton[menu].x + 40, SideButton[menu].y + 15);
		 * GL11.glColor3f(1f, 1f, 1f);
		 */
	}

	int NUMBER_OF_PARTICLE_EMITTERS = 0;

	void Create_New_ParticleEmitter(int x, int y, int quantity, float r, float g, float b, float alpha) {

		int next_available_ID = -1;

		for (int i = 0; i < 1000; i++) {// recycle

			if (!myEmitters[i].IsActive) {
				next_available_ID = i;
				break;
			}

		}

		if (next_available_ID > -1) {
			myEmitters[next_available_ID] = new ParticleEmitter();
			myEmitters[next_available_ID].count = 500;
			myEmitters[next_available_ID].mapX = x;
			myEmitters[next_available_ID].mapY = y;
			myEmitters[next_available_ID].r = r;
			myEmitters[next_available_ID].g = g;
			myEmitters[next_available_ID].b = b;
			myEmitters[next_available_ID].alpha = alpha;
			myEmitters[next_available_ID].num_particles = quantity;

			myEmitters[next_available_ID].IsActive = true;

			for (int i = 0; i < myEmitters[next_available_ID].num_particles && i < myEmitters[next_available_ID].particle_angle.length; i++) {
				myEmitters[next_available_ID].particle_angle[i] = Math.random() * Math.PI * 2;
			}

			NUMBER_OF_PARTICLE_EMITTERS++;
		}

	}

	void DrawESCMenu() {

		Menus[MENU_ESC].background.draw(Menus[MENU_ESC].x, Menus[MENU_ESC].y);

		menu_exit_x.draw(Menus[MENU_ESC].x + Menus[MENU_ESC].exitbutton_x, Menus[MENU_ESC].y + Menus[MENU_ESC].exitbutton_y);

		GL11.glColor3f(0.9f, 0.9f, 0.9f);

		// g.setFont(new Font("Arial", Font.PLAIN, 16));
		Verdana_16.drawString(Menus[MENU_ESC].x + 170, Menus[MENU_ESC].y + 6, "Settings");

		drawTickBox(MENU_ESC, 50, 50 + 30 * 0, mySettings.FullScreen, "Full Screen Display");

		drawTickBox(MENU_ESC, 50, 50 + 30 * 1, mySettings.MusicMuted, "Mute Music");

		drawTickBox(MENU_ESC, 50, 50 + 30 * 2, mySettings.AutoSaving, "Auto Saving");

		drawTickBox(MENU_ESC, 50, 50 + 30 * 3, mySettings.Tutorial, "Tutorials");

		int pixellength = Verdana_16.getStringPixelLength("Baneforge Wiki & Guides");
		GL11.glColor3f(0.1f, 0.1f, 0.1f);
		Fill_Rect(Menus[MENU_ESC].x + 200 - pixellength / 2 - 10, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 110, pixellength + 20, 36);
		GL11.glColor3f(1, 1, 1);
		Outline_Rect(Menus[MENU_ESC].x + 200 - pixellength / 2 - 10, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 110, pixellength + 20, 36);
		Verdana_16.drawString(Menus[MENU_ESC].x + 200 - pixellength / 2, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 100, "Baneforge Wiki & Guides");

		pixellength = Verdana_16.getStringPixelLength("Close Baneforge");
		GL11.glColor3f(0, 0, 0);
		Fill_Rect(Menus[MENU_ESC].x + 200 - pixellength / 2 - 10, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 60, pixellength + 20, 36);
		GL11.glColor3f(1, 1, 1);
		Outline_Rect(Menus[MENU_ESC].x + 200 - pixellength / 2 - 10, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 60, pixellength + 20, 36);
		Verdana_16.drawString(Menus[MENU_ESC].x + 200 - pixellength / 2, Menus[MENU_ESC].y + Menus[MENU_ESC].height - 50, "Close Baneforge");

		if (current_MainMenu_Screen == -1 && myPNum == 1) {
			pixellength = Verdana_16.getStringPixelLength("Save Game");
			GL11.glColor3f(0, 0, 0);
			Fill_Rect(Menus[MENU_ESC].x + 300 - pixellength / 2 - 10, Menus[MENU_ESC].y + 60, pixellength + 20, 36);
			GL11.glColor3f(1, 1, 1);
			Outline_Rect(Menus[MENU_ESC].x + 300 - pixellength / 2 - 10, Menus[MENU_ESC].y + 60, pixellength + 20, 36);
			Verdana_16.drawString(Menus[MENU_ESC].x + 300 - pixellength / 2, Menus[MENU_ESC].y + 70, "Save Game");

		}

	}

	void handleinputs_ESCMenu() {

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

				if (Menus[MENU_ESC].ExitButtonBeingHovered_AndOpen(cursor)) {
					Menus[MENU_ESC].is_open = false;
				} else {

					// start dragging
					if (Menus[MENU_ESC].GenericToolbarBeingHovered(cursor)) {
						menu_beingDragged = MENU_ESC;
						menu_dragoffset_x = cursor.x - Menus[MENU_ESC].x;
						menu_dragoffset_y = cursor.y - Menus[MENU_ESC].y;
					}

					if (cursor.x > (Menus[MENU_ESC].x + 200 - 40) && cursor.x < (Menus[MENU_ESC].x + 200 + 40)
							&& cursor.y > Menus[MENU_ESC].y + Menus[MENU_ESC].height - 110
							&& cursor.y < Menus[MENU_ESC].y + Menus[MENU_ESC].height - 110 + 36) {

						try {
							java.awt.Desktop.getDesktop().browse(java.net.URI.create(SharedData.BANEFORGE_WEBSITE_URL));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (cursor.x > (Menus[MENU_ESC].x + 200 - 40) && cursor.x < (Menus[MENU_ESC].x + 200 + 40)
							&& cursor.y > Menus[MENU_ESC].y + Menus[MENU_ESC].height - 60
							&& cursor.y < Menus[MENU_ESC].y + Menus[MENU_ESC].height - 60 + 36) {
						GameRunning = false;
					}

					// tick box actions
					for (int i = 0; i <= 3; i++) {
						if (cursor.x > (Menus[MENU_ESC].x + 50) && cursor.x < (Menus[MENU_ESC].x + 50 + 120)
								&& cursor.y > Menus[MENU_ESC].y + 50 + 30 * i && cursor.y < Menus[MENU_ESC].y + 50 + 30 * i + 20) {

							if (i == 0) {
								if (mySettings.FullScreen) {
									mySettings.FullScreen = false;
								} else {
									mySettings.FullScreen = true;
								}

								writeSettingstoFile(true);

							}
							if (i == 1) {
								if (mySettings.MusicMuted) {
									mySettings.MusicMuted = false;
									BackgroundMusic.setVolume(MUSICVOLUME_FULL);
								} else {
									mySettings.MusicMuted = true;
									BackgroundMusic.setVolume(0);
								}

								writeSettingstoFile(true);
							}
							if (i == 2) {
								if (mySettings.AutoSaving) {
									mySettings.AutoSaving = false;
								} else {
									mySettings.AutoSaving = true;
								}

								writeSettingstoFile(true);
							}
							if (i == 3) {
								if (mySettings.Tutorial) {
									mySettings.Tutorial = false;
								} else {
									mySettings.Tutorial = true;
								}

								writeSettingstoFile(true);
							}

						}
					}

					if (current_MainMenu_Screen == -1 && myPNum == 1) {
						int pixellength = Verdana_16.getStringPixelLength("Save Game");
						if (cursor.WithinRect(Menus[MENU_ESC].x + 300 - pixellength / 2 - 10, Menus[MENU_ESC].y + 60, pixellength + 20, 36)) {
							if (lastCampaignFilePath != null) {
								boolean success = WriteCampaignToFile(lastCampaignFilePath);
								ShowWarningDialog("Campaign and all heroes saved successfully!");
								SimpleServerBroadcast("/savegame");
							}
						}
					}

				}
			}
		}

	}

	void drawTickBox(int Menu_ID, int x, int y, boolean b, String title) {

		Outline_Rect(Menus[Menu_ID].x + x, Menus[Menu_ID].y + y, 12, 12);

		if (b) {

			Fill_Rect(Menus[Menu_ID].x + x + 2, Menus[Menu_ID].y + y + 2, 7, 8);

		} else {

		}

		Verdana_14.drawString(Menus[Menu_ID].x + x + 20, Menus[Menu_ID].y + y, title);
	}

	int getFirstFreeBagSlot(int UnitID) {
		int answer = -1;
		for (int i = 49; i >= 0; i--) {
			if (Units[UnitID].itemInSlot[i] == 0) {
				answer = i;
			}
		}
		return answer;
	}

	void QueueNewMainMenuScreen(int i, int direction) {
		Mouse_MouseAlreadyClicked[0] = true;// should use this way more.
		Mouse_MouseAlreadyClicked[1] = true;
		MainMenu_tween_x = 0;
		MainMenu_tween_y = 0;

		queued_MainMenu_Screen = i;

		if (direction == 0) {
			MainMenu_tween_y = 1;
		}
		if (direction == 1) {
			MainMenu_tween_x = 1;
		}
		if (direction == 2) {
			MainMenu_tween_y = -1;
		}
		if (direction == 3) {
			MainMenu_tween_x = -1;
		}

		// if(i==MENUSCREEN_NEWHERO){resetDefaultHeroStats();}

	}

	void ChangeMainMenuScreen() {
		current_MainMenu_Screen = queued_MainMenu_Screen;

		MainMenu_tween_x = 0;
		MainMenu_tween_y = 0;

		playSound(mySoundPaths[SOUND_DRUMLOW]);

		if (current_MainMenu_Screen == MENUSCREEN_JOINSERVER) {

			// start udp client
			UDPThread_receive = new UDPBroadcastClient(this);
			myUDPBroadcastThread = new Thread(UDPThread_receive);
			myUDPBroadcastThread.start();

		}

		if (current_MainMenu_Screen == MENUSCREEN_NEWHERO) {// this may cause
															// bugs!
			Units[0] = new Unit();
			ProfileImages[0] = ProfileImage_blank;

			// reset the temp data!
			for (int i = 0; i < SharedData.NUM_OF_BASESTATS; i++) {
				int j = i + SharedData.ENDURANCE;
				default_hero_stats[j] = 10;// defaults
			}

			myBufferedProfileImage = null;
			lastCustomProfileImagePath = null;

			myTextBoxes[TEXTBOX_HERONAME].text = "";
			myTextBoxes[TEXTBOX_HERODESCRIP].text = "";

			newhero_pointsleft = 5;
			int newhero_perksleft = 2;

		}

		if (current_MainMenu_Screen == MENUSCREEN_CHOOSEHERO) {

			// if(RunningInGoldMode()){///load first hero file found

			String TempHeroFile = null;

			File folder = new File(SharedData.defaultDirectory() + "\\Heroes\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {

				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".vhf") && TempHeroFile == null) {
					TempHeroFile = listOfFile.getName();
				}
			}

			if (TempHeroFile != null) {
				String mypath = SharedData.defaultDirectory() + "\\Heroes\\" + TempHeroFile;
				readHerofromFile(mypath);
			}

			/*
			 * }else{//load the local wanderer file
			 * 
			 * readHerofromFile("wrap/assets/Wanderer.vhf");
			 * 
			 * }
			 */

		}

		if (current_MainMenu_Screen == MENUSCREEN_CHOOSECAMPAIGN) {

			String TempCampFile = null;

			File folder = new File(SharedData.defaultDirectory() + "\\Campaigns\\");
			File[] listOfFiles = folder.listFiles();

			for (File listOfFile : listOfFiles) {

				if (listOfFile.getName().substring(listOfFile.getName().length() - 4).equals(".bfc") && TempCampFile == null) {
					TempCampFile = listOfFile.getName();
				}
			}

			if (TempCampFile != null) {
				String mypath = SharedData.defaultDirectory() + "\\Campaigns\\" + TempCampFile;
				ReadCampaignFromFile(mypath);
			}
		}

		/*
		 * if(current_MainMenu_Screen == MENUSCREEN_JOINSERVER){
		 * 
		 * MYSQL_FindAvailableServers();//need a refresh button for this too!
		 * 
		 * }
		 */

	}

	void generateMiniMap() {

		BufferedImage minimapimage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < 200; x++) {
			for (int y = 0; y < 200; y++) {

				int layer = myshareddata.TERRAIN_LAYER_HIGHER;
				if (map[layer][y / 2][x / 2] == 0) {
					layer = myshareddata.TERRAIN_LAYER_LOWER;
				}

				float r = (float) myTypeDB[map[layer][y / 2][x / 2]].minimapcolors[0] / 255;
				float g = (float) myTypeDB[map[layer][y / 2][x / 2]].minimapcolors[1] / 255;
				float b = (float) myTypeDB[map[layer][y / 2][x / 2]].minimapcolors[2] / 255;

				// float alpha = (float) lit[y * 2][x * 2];

				// Color c = Color.red; //should be color of the correct thing
				Color c = new Color(r, g, b);

				minimapimage.setRGB(x, y, c.getRGB());
			}
		}

		try {
			minimap_static_texture = textureLoader.getTexture(minimapimage, "minimap");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		minimap_static = new Image2D(minimap_static_texture);

	}

	void Client_GetSaveGame() {

		boolean success = writeHerotoFile(lastHeroFilePath);
		if (success) {
			ShowWarningDialog("Your hero file has been updated and saved.");
		}

	}

	double fixDegrees(double d) {

		while (d < 0) {
			d += 360;
		}

		while (d >= 360) {
			d -= 360;
		}

		return d;
	}

	boolean UnitAbletoCastAbility(int ability, int start_x, int start_y, int end_x, int end_y) {
		boolean answer = false;

		boolean range_is_valid = true;
		boolean target_is_valid = true;

		boolean targetIsAllied = false;

		if (myAbilityDB[ability].requires_targetting && coordinatesWithinMapBounds(end_x, end_y)) {
			int CasterID = targetting_currentcaster;
			int target_ID = UnitMap[end_x][end_y];
			int target_owner = Units[target_ID].stat[SharedData.PLAYER_OWNERSHIP];
			int caster_owner = Units[CasterID].stat[SharedData.PLAYER_OWNERSHIP];

			if ((target_owner == 1 && caster_owner == 1) || (target_owner != 1 && caster_owner != 1)) {
				targetIsAllied = true;
			}

			int mycastrange = myAbilityDB[ability].getEffectiveRange(Units[CasterID]);

			tiledist_fromhero = Math.sqrt(Math.abs(Math.pow(start_y - end_y, 2)) + Math.abs(Math.pow(start_x - end_x, 2)));
			if (tiledist_fromhero > mycastrange && !myAbilityDB[ability].constrain_to_cardinal_direction) {
				range_is_valid = false;
			}

			if (target_ID == 0 && !myAbilityDB[ability].can_target_where_no_units_exist) {
				target_is_valid = false;
			}
			if (target_ID > 0 && !myAbilityDB[ability].can_target_units) {
				target_is_valid = false;
			}
			if (myAbilityDB[ability].can_target_units && target_ID > 0) {
				if (!targetIsAllied && !myAbilityDB[ability].can_target_enemies) {
					target_is_valid = false;
				}
				if (targetIsAllied && !myAbilityDB[ability].can_target_allies) {
					target_is_valid = false;
				}
				if ((target_owner == caster_owner) && !myAbilityDB[ability].can_target_self) {
					target_is_valid = false;
				}
			}

			if ((!terrain_IsWalkable(end_x, end_y) || Unit_At_Tile(end_x, end_y)) && myAbilityDB[ability].require_targetpoint_walkable) {
				target_is_valid = false;
			}

			if (Units[CasterID].stat[SharedData.PLAYER_OWNERSHIP] == 1) {
				target_is_valid = true;
			}// Host units can always attack things
		}

		answer = target_is_valid && range_is_valid;
		debug("allowed to cast ability?" + answer);
		return answer;
	}

	boolean EffectAbletoAffectUnit(int ability, int start_x, int start_y, int end_x, int end_y) {
		boolean answer = false;

		return answer;
	}

	int[] getDMOptionObject() {
		int[] type_and_rows = new int[2];

		// find the type of the object with options that is being selected
		for (int i = 0; i < 10; i++) {
			if (myTypeDB[map[myshareddata.SMALLOBJECTS_LAYER][cam.tileselect_y][cam.tileselect_x]].options[i] != null) {
				type_and_rows[0] = map[myshareddata.SMALLOBJECTS_LAYER][cam.tileselect_y][cam.tileselect_x];
			}
			if (myTypeDB[map[myshareddata.LARGEOBJECTS_LAYER][cam.tileselect_y][cam.tileselect_x]].options[i] != null) {
				type_and_rows[0] = map[myshareddata.LARGEOBJECTS_LAYER][cam.tileselect_y][cam.tileselect_x];
			}
		}

		// if one was found, count rows
		if (type_and_rows[0] > 0) {
			for (int i = 0; i < myTypeDB[type_and_rows[0]].options.length; i++) {
				if (myTypeDB[type_and_rows[0]].options[i] != null) {
					type_and_rows[1]++;
				}
			}
		}

		return type_and_rows;
	}

	void DrawItemIconFromSheet(int Icon_ID, int screen_x, int screen_y) {
		if (Icon_ID > -1) {

			MasterItemIconSheet.bind();
			MasterItemIconSheet.begin();

			int tex_x = 0;
			int tex_y = 0;

			// int tile = ((Icon_ID-1)%16);
			int tile = ((Icon_ID));

			// tuning
			tex_x = (tile % 25) * 40;
			tex_y = (tile / 25) * 40;

			MasterItemIconSheet.draw_subimage(tex_x, tex_y, screen_x, screen_y, 40, 40);

			MasterItemIconSheet.end();

		}
	}

	void DrawItemQuantity(int n, int screen_x, int screen_y) {
		if (n > 1) {
			GL11.glColor3f(1, 1, 1);
			Verdana_12.drawString(screen_x + 30, screen_y + 30, "" + n);
		}

	}

	/*
	 * int Effects_getEffectiveStatChange(int UnitID, int effectnum){ int answer
	 * = myEffectDB[effectnum].stat_change_amount;
	 * 
	 * if(myEffectDB[effectnum].stat_that_affects_change>0 &&
	 * myEffectDB[effectnum].stat_that_affects_change_pct>0){
	 * //affecting_stat_value =selected_unit.stat[stat_that_affects_change] int
	 * affecting_stat_value = GetEffectiveUnitStat(UnitID,
	 * myEffectDB[effectnum].stat_that_affects_change, true); answer +=
	 * ((affecting_stat_value
	 * )*myEffectDB[effectnum].stat_that_affects_change_pct)/100; }
	 * 
	 * if(myEffectDB[effectnum].stat_change_amount<0 && answer>0){answer=0;}
	 * if(myEffectDB[effectnum].stat_change_amount>0 && answer<0){answer=0;}
	 * 
	 * return answer;
	 * 
	 * }
	 */

	static final int MAX_NUM_RECURSIONS = 5;

	int GetEffectiveUnitStat(int UnitID, int stat, int recursive_iterations) {// fix
		// debug("Get Eff Unit Stats"+recursive_iterations);
		recursive_iterations++;// prevents infinite loops!

		int answer = 0;

		if (stat < 100) {// normal stats
			answer = Units[UnitID].stat[stat];

			// add stat bonuses from items here! No stat bonuses when
			// equipping/dequipping anymore!!!
			for (int i = myshareddata.ITEMSLOT_HEAD; i <= myshareddata.ITEMSLOT_OFFHAND; i++) {
				int equippeditem = Units[UnitID].itemInSlot[i];
				if (equippeditem > 0) {
					for (int n = 0; n < 5; n++) {
						if (myItemDB[equippeditem].stat_to_increase[n] == stat && stat > 0
								&& myItemDB[equippeditem].itemgroup != SharedData.ITEMTYPE_CONSUMABLES) {
							answer += myItemDB[equippeditem].stat_bonus_amount[n];

							if (recursive_iterations < MAX_NUM_RECURSIONS && stat != myItemDB[equippeditem].stat_affecting_amount[n]) {// does
																																		// inception
								answer += (GetEffectiveUnitStat(UnitID, Units[UnitID].stat[myItemDB[equippeditem].stat_affecting_amount[n]],
										recursive_iterations) * myItemDB[equippeditem].stat_affecting_amount_factor[n]) / 100;
							} else {
								answer += (Units[UnitID].stat[myItemDB[equippeditem].stat_affecting_amount[n]] * myItemDB[equippeditem].stat_affecting_amount_factor[n]) / 100;// additional,
																																												// based
																																												// on
																																												// the
																																												// victim
							}
							// answer+=(Units[UnitID].stat[myItemDB[equippeditem].stat_affecting_amount[n]]*myItemDB[equippeditem].stat_affecting_amount_factor[n])/100;

						}
					}
				}
			}

			// add stat bonuses from conditions
			for (int i = 0; i < 100; i++) {
				if (Units[UnitID].activeconditions[i] > 0) {

					int condition_ID = Units[UnitID].activeconditions[i];

					for (int n = 0; n < 5; n++) {// for all 5 possible periodic
													// effects
						int effect = myConditionDB[condition_ID].passive_effect[n];
						if (effect > 0) {
							if (myEffectDB[effect].action == EFFECT_ACTION_EDITSTATS) {// for
																						// all
																						// 5
																						// possible
																						// statedits
																						// of
																						// each
																						// periodic
																						// effect
								for (int j = 0; j < 5; j++) {
									// if(myEffectDB[effect].stat_to_edit[j] ==
									// stat && stat > 0 &&
									// myEffectDB[effect].stat_change_is_temp[j]){

									if (myEffectDB[effect].stat_to_edit[j] == stat && stat > 0) {

										debug("b");

										int UnitID_stat_source = UnitID;
										if (myEffectDB[effect].use_caster_stats[j]) {
											UnitID_stat_source = Units[UnitID].conditions_source[i];
											debug("source:" + UnitID_stat_source);
										}

										if (myEffectDB[effect].sets_stat[j]) {
											answer = myEffectDB[effect].stat_change_amount[j];
											answer += (myEffectDB[effect].stat_that_affects_change_pct[j] * Units[UnitID_stat_source].stat[myEffectDB[effect].stat_that_affects_change[j]]) / 100;
										} else {

											answer += myEffectDB[effect].stat_change_amount[j];

											if (UnitID_stat_source > 0) {

												if (recursive_iterations < MAX_NUM_RECURSIONS
														&& stat != myEffectDB[effect].stat_that_affects_change[j]) {// does
																													// inception
													answer += (GetEffectiveUnitStat(UnitID, myEffectDB[effect].stat_that_affects_change[j],
															recursive_iterations) * myEffectDB[effect].stat_that_affects_change_pct[j]) / 100;
												} else {
													answer += (myEffectDB[effect].stat_that_affects_change_pct[j] * Units[UnitID_stat_source].stat[myEffectDB[effect].stat_that_affects_change[j]]) / 100;// additional,
																																																			// victim
												}

											}

										}

									}
								}
							}
						}

					}

				} else
					break;
			}

			for (int i = 0; i < 100; i++) {
				if (tileactiveconditions[Units[UnitID].x][Units[UnitID].y][i] > 0) {

					int condition_ID = tileactiveconditions[Units[UnitID].x][Units[UnitID].y][i];

					for (int n = 0; n < 5; n++) {// for all 5 possible periodic
													// effects
						int effect = myConditionDB[condition_ID].passive_effect[n];
						if (effect > 0) {
							if (myEffectDB[effect].action == EFFECT_ACTION_EDITSTATS) {// for
																						// all
																						// 5
																						// possible
																						// statedits
																						// of
																						// each
																						// periodic
																						// effect
								for (int j = 0; j < 5; j++) {

									if (myEffectDB[effect].stat_to_edit[j] == stat && stat > 0) {

										int UnitID_stat_source = UnitID;
										if (myEffectDB[effect].use_caster_stats[j]) {
											UnitID_stat_source = Units[UnitID].conditions_source[i];
										}

										if (myEffectDB[effect].sets_stat[j]) {
											answer = myEffectDB[effect].stat_change_amount[j];
											answer += (myEffectDB[effect].stat_that_affects_change_pct[j] * Units[UnitID_stat_source].stat[myEffectDB[effect].stat_that_affects_change[j]]) / 100;
										} else {

											answer += myEffectDB[effect].stat_change_amount[j];

											if (UnitID_stat_source > 0) {

												if (recursive_iterations < MAX_NUM_RECURSIONS
														&& stat != myEffectDB[effect].stat_that_affects_change[j]) {// does
																													// inception
													answer += (GetEffectiveUnitStat(UnitID, myEffectDB[effect].stat_that_affects_change[j],
															recursive_iterations) * myEffectDB[effect].stat_that_affects_change_pct[j]) / 100;
												} else {
													answer += (myEffectDB[effect].stat_that_affects_change_pct[j] * Units[UnitID_stat_source].stat[myEffectDB[effect].stat_that_affects_change[j]]) / 100;// additional,
																																																			// victim
												}

											}

										}

									}
								}
							}
						}

					}

				} else
					break;

			}

			if (stat == myshareddata.INITIATIVE) {
				if (UnitID < 10) {
					answer += 8;
				}
				answer += Units[UnitID].stat[myshareddata.AGILITY] / 8;
			}

			/*
			 * if (stat == myshareddata.HEALTHREGEN) { answer +=
			 * Units[UnitID].stat[myshareddata.ENDURANCE] / 8;
			 * 
			 * }
			 */
			/*
			 * if (stat == SharedData.ENERGYREGEN) { answer += 15 +
			 * Units[UnitID].stat[myshareddata.STAMINA] ;
			 * 
			 * }
			 */

		} else {// special stats....aka Flags

			if (stat == myshareddata.FLAG_ALLIESNEARBY || stat == myshareddata.FLAG_ENEMIESNEARBY) {
				for (int x = Units[UnitID].x - 5; x < Units[UnitID].x + 5; x++) {
					for (int y = Units[UnitID].y - 5; y < Units[UnitID].y + 5; y++) {
						int dist = pythagorean(x, y, Units[UnitID].x, Units[UnitID].y);
						int TarID = UnitMap[x][y];
						if (dist < 5 && TarID > 0) {

							if (stat == myshareddata.FLAG_ALLIESNEARBY) {
								if ((Units[TarID].stat[SharedData.PLAYER_OWNERSHIP] != 1 && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] != 1)
										|| (Units[TarID].stat[SharedData.PLAYER_OWNERSHIP] == 1 && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1)) {

									answer++;
								}
							}

							if (stat == myshareddata.FLAG_ENEMIESNEARBY) {
								if ((Units[TarID].stat[SharedData.PLAYER_OWNERSHIP] != 1 && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1)
										|| (Units[TarID].stat[SharedData.PLAYER_OWNERSHIP] == 1 && Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] != 1)) {

									answer++;
								}
							}

						}

					}
				}
			}

			if (stat == myshareddata.FLAG_NIGHTTIME) {
				answer = 0;
				if ((HOUR_OF_DAY < 6 || HOUR_OF_DAY > 18)) {
					answer = 1;
				}
			}

			if (stat == myshareddata.FLAG_ABILITYLEVEL) {
				// answer = Units[UnitID].LearnedSpells
				answer = 0;
			}

			if (stat == myshareddata.FLAG_OPPOSITEFACING) {
				answer = 0;
				// broken
			}

		}

		// bonuses from any aura-holder units that are nearby (AURAS CANNOT COME
		// FROM WEAPONSKILLS AT THIS TIME

		/*
		 * for (int i = 0; i < NUMBER_OF_UNITS; i++) { if (Units[i] != null) {
		 * int dist = pythagorean(Units[UnitID].x, Units[UnitID].y, Units[i].x,
		 * Units[i].y); for (int slot = 0; slot < 10; slot++) {
		 * 
		 * int condition_ID = -1; int ability_ID = Units[i].active_spells[slot];
		 * 
		 * 
		 * 
		 * if (myAbilityDB[ability_ID].passive &&
		 * myAbilityDB[ability_ID].getEffectiveRange(Units[i]) >= dist) {
		 * 
		 * if ((Units[i].stat[SharedData.PLAYER_OWNERSHIP] != 1 &&
		 * Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1) ||
		 * (Units[i].stat[SharedData.PLAYER_OWNERSHIP] == 1 &&
		 * Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] != 1) &&
		 * myAbilityDB[ability_ID].can_affect_enemies) { condition_ID =
		 * myAbilityDB[ability_ID].passive_condition_applied; }
		 * 
		 * if (UnitID == i) { if (myAbilityDB[ability_ID].can_affect_self) {
		 * condition_ID = myAbilityDB[ability_ID].passive_condition_applied; } }
		 * else if ((Units[i].stat[SharedData.PLAYER_OWNERSHIP] == 1 &&
		 * Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == 1) ||
		 * (Units[i].stat[SharedData.PLAYER_OWNERSHIP] != 1 &&
		 * Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] != 1) &&
		 * myAbilityDB[ability_ID].can_affect_allies) { condition_ID =
		 * myAbilityDB[ability_ID].passive_condition_applied; }
		 * 
		 * }
		 * 
		 * if (condition_ID > 0) {// if a passive condition comes from // an
		 * ability and everything is // valid, apply all 5 stat changes // for
		 * that condition.
		 * 
		 * 
		 * for (int n = 0; n < 5; n++) {//for all 5 possible periodic effects
		 * int effect = myConditionDB[condition_ID].passive_effect[n]; if(effect
		 * > 0){ if(myEffectDB[effect].action==EFFECT_ACTION_EDITSTATS){//for
		 * all 5 possible statedits of each periodic effect for(int
		 * j=0;j<5;j++){ //if(myEffectDB[effect].stat_to_edit[j] == stat && stat
		 * > 0 && myEffectDB[effect].stat_change_is_temp[j]){
		 * if(myEffectDB[effect].stat_to_edit[j] == stat && stat > 0 ){
		 * answer+=myEffectDB[effect].stat_change_amount[j];
		 * 
		 * int UnitID_stat_source = UnitID; if
		 * (myEffectDB[effect].use_caster_stats[j]) { UnitID_stat_source =
		 * Units[UnitID].conditions_source[i]; }
		 * 
		 * if (UnitID_stat_source > 0) {
		 * 
		 * if (recursive_iterations < MAX_NUM_RECURSIONS) {// does inception
		 * answer += (GetEffectiveUnitStat(UnitID,
		 * myEffectDB[effect].stat_that_affects_change[j], recursive_iterations)
		 * * myEffectDB[effect].stat_that_affects_change_pct[j]) / 100; } else {
		 * answer += (myEffectDB[effect].stat_that_affects_change_pct[j] *
		 * Units[
		 * UnitID_stat_source].stat[myEffectDB[effect].stat_that_affects_change
		 * [j]]) / 100;// additional, // victim }
		 * 
		 * }
		 * 
		 * 
		 * } } } }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 */

		return answer;
	}

	void DrawAbilityIconFromSheet(int Icon_ID, int screen_x, int screen_y) {

		abilityicon_frame.draw(screen_x, screen_y);

		// set the color here, after the frame!!!

		MasterBWIconSheet.bind();
		MasterBWIconSheet.begin();

		int tex_x = 0;
		int tex_y = 0;

		// int tile = ((Icon_ID-1)%16);
		int tile = ((Icon_ID));

		// tuning
		tex_x = (tile % 25) * 40;
		tex_y = (tile / 25) * 40;

		MasterBWIconSheet.draw_subimage(tex_x, tex_y, screen_x + 6, screen_y + 6, 40, 40);

		MasterBWIconSheet.end();

	}

	void DrawAbilityIconFromSheet(int Icon_ID, int screen_x, int screen_y, double r, double g, double b) {

		abilityicon_frame.draw(screen_x, screen_y);

		// set the color here, after the frame!!!

		MasterBWIconSheet.bind();
		MasterBWIconSheet.begin();

		int tex_x = 0;
		int tex_y = 0;

		// int tile = ((Icon_ID-1)%16);
		int tile = ((Icon_ID));

		// tuning
		tex_x = (tile % 25) * 40;
		tex_y = (tile / 25) * 40;

		GL11.glColor3d(r, g, b);

		MasterBWIconSheet.draw_subimage(tex_x, tex_y, screen_x + 6, screen_y + 6, 40, 40);

		MasterBWIconSheet.end();

	}

	void DrawConditionIconFromSheet(int Icon_ID, int screen_x, int screen_y) {

		MasterBWIconSheet.bind();
		MasterBWIconSheet.begin();

		int tex_x = 0;
		int tex_y = 0;

		// int tile = ((Icon_ID-1)%16);
		int tile = ((Icon_ID));

		// tuning
		tex_x = (tile % 25) * 40;
		tex_y = (tile / 25) * 40;

		MasterBWIconSheet.draw_subimage_stretch(tex_x, tex_y, screen_x, screen_y, 40, 40, 30, 30);

		MasterBWIconSheet.end();

	}

	void TryToEquipItem(int bagslot_of_item, int UnitID, boolean broadcast) {

		if (UnitID > 0 && bagslot_of_item > -1) {
			int item_id = Units[UnitID].itemInSlot[bagslot_of_item];

			int slotbeingaffected = 49 + myItemDB[item_id].slotassignment;

			if (myItemDB[item_id].slotassignment > 0) {

				// int item_being_dragged =
				// Units[UnitID].itemInSlot[bagslot_of_item];

				if (myItemDB[item_id].itemgroup == SharedData.ITEMTYPE_CONSUMABLES) {

					// consumable item effects are never executed!! ?
					/*
					 * if (!broadcast) {
					 * 
					 * EditUnitItemIDQuantity(Units[UnitID].x, Units[UnitID].y,
					 * item_id, - 1); } else {
					 * 
					 * if (myPNum == 1) {
					 * 
					 * Server_Broadcast_EditUnitItemIDQuantity( Units[UnitID].x,
					 * Units[UnitID].y, item_id, - 1,0 );
					 * 
					 * } else { Client_TellServer_EditUnitItemIDQuantity(
					 * Units[UnitID].x, Units[UnitID].y, item_id, - 1);
					 * 
					 * }
					 * 
					 * }
					 */

					// playSound(mySoundPaths[SOUND_POTION]);
				} else {

					if (!broadcast) {
						SwapUnitBagSlotItems(Units[UnitID].x, Units[UnitID].y, bagslot_of_item, slotbeingaffected);

					} else {

						if (myPNum == 1) {

							Server_BroadcastSwapUnitBagSlotItems(Units[UnitID].x, Units[UnitID].y, bagslot_of_item, slotbeingaffected);

						} else {
							Client_TellServer_SwapUnitBagSlotItems(Units[UnitID].x, Units[UnitID].y, bagslot_of_item, slotbeingaffected);

						}

					}

					// playSound(mySoundPaths[SOUND_LEATHERITEM]);

					// fix adding weaponskills to bar??? how should that work...
					// Every frame? or like this?
					if (myItemDB[(Units[UnitID].itemInSlot[slotbeingaffected])].weapontype > -1) {

						// add_weaponskills_to_bar( );

					}

				}

			}// end check for item being assigned a slot
		}
	}

	void Server_Broadcast_Create_New_NPC(int type, int x, int y, int owner) {

		String[] s = {"" + type, "" + x, "" + y, "" + owner};
		GenericServerBroadcast("/createNPC", s);

		Create_New_NPC(type, x, y, owner);

		playSound(mySoundPaths[myNPCDB[type].sound_id]);
	}

	int Create_New_NPC(int type, int x, int y, int owner) {

		Units[NUMBER_OF_UNITS] = new Unit();
		UnitMap[x][y] = NUMBER_OF_UNITS;

		System.out.println("NPC" + NUMBER_OF_UNITS);
		Units[NUMBER_OF_UNITS].x = x;
		Units[NUMBER_OF_UNITS].y = y;

		for (int i = 0; i < myshareddata.NUM_OF_UNITSTATS; i++) {// put in
																	// default
																	// stats

			Units[NUMBER_OF_UNITS].stat[i] = default_NPC_stats[i];

		}

		if (myNPCDB[type].base_endurance > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.ENDURANCE] = myNPCDB[type].base_endurance;
		}
		if (myNPCDB[type].base_stamina > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.STAMINA] = myNPCDB[type].base_stamina;
		}
		if (myNPCDB[type].base_meleepower > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.MELEEPOWER] = myNPCDB[type].base_meleepower;
		}
		if (myNPCDB[type].base_rangedpower > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.RANGEDPOWER] = myNPCDB[type].base_rangedpower;
		}
		if (myNPCDB[type].base_spellpower > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.SPELLPOWER] = myNPCDB[type].base_spellpower;
		}
		if (myNPCDB[type].base_armor > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.ARMORRATING] = myNPCDB[type].base_armor;
		}
		if (myNPCDB[type].base_magres > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.MAGICRESIST] = myNPCDB[type].base_magres;
		}
		if (myNPCDB[type].base_toxres > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.TOXINRESIST] = myNPCDB[type].base_toxres;
		}
		if (myNPCDB[type].base_initiative > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.INITIATIVE] = myNPCDB[type].base_initiative;
		}
		if (myNPCDB[type].base_speed > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.SPEED] = myNPCDB[type].base_speed;
		}
		if (myNPCDB[type].exp_reward > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.EXP_REWARD] = myNPCDB[type].exp_reward;
		}
		if (myNPCDB[type].gold_carried > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.GOLD_CARRIED] = myNPCDB[type].gold_carried;
		}

		Units[NUMBER_OF_UNITS].stat[myshareddata.ALIGNMENT] = myNPCDB[type].alignment;

		if (myNPCDB[type].model_ID > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.MODEL_ID] = myNPCDB[type].model_ID;
		}

		int num_of_abils = 0;
		for (int i = 0; i < 5; i++) {
			if (myNPCDB[type].abilities[i] > 0) {
				Units[NUMBER_OF_UNITS].active_spells[num_of_abils] = myNPCDB[type].abilities[i];
				debug("ability added to NPC" + myNPCDB[type].abilities[i]);
				num_of_abils++;
			}
		}

		// change stats here or they will be overwritten!!
		if (myNPCDB[type].expirytimer_on_spawn > 0) {
			Units[NUMBER_OF_UNITS].stat[myshareddata.UNITDATA_TURNSLEFT] = myNPCDB[type].expirytimer_on_spawn;
		}

		Units[NUMBER_OF_UNITS].stat[SharedData.PLAYER_OWNERSHIP] = owner;

		Units[NUMBER_OF_UNITS].stat[myshareddata.WALK_MOVES_COUNT] = get_unit_maxwalkmoves(NUMBER_OF_UNITS);

		Units[NUMBER_OF_UNITS].stat[myshareddata.LEVEL] = 1;

		Units[NUMBER_OF_UNITS].stat[myshareddata.FACING] = 2;
		Units[NUMBER_OF_UNITS].stat[myshareddata.ENERGYMAX] = 100;
		Units[NUMBER_OF_UNITS].stat[myshareddata.ENERGYGROWTH] = 10;
		Units[NUMBER_OF_UNITS].stat[myshareddata.STARTINGENERGY] = 50;

		Units[NUMBER_OF_UNITS].stat[myshareddata.UNITDATA_NPCTYPE] = type;

		Units[NUMBER_OF_UNITS].stat[SharedData.DEAD] = 0;

		Units[NUMBER_OF_UNITS].stat[myshareddata.HEALTH] = get_unit_maxhealth(NUMBER_OF_UNITS);

		if (BattlePhaseEngaged) {
			Units[NUMBER_OF_UNITS].stat[SharedData.ENERGY] = Units[NUMBER_OF_UNITS].stat[SharedData.STARTINGENERGY]
					+ Units[NUMBER_OF_UNITS].stat[SharedData.ENERGYGROWTH] * BattlePhase_CurrentTurn;
		} else {
			Units[NUMBER_OF_UNITS].stat[SharedData.ENERGY] = 100;
		}

		Units[NUMBER_OF_UNITS].current_model = GetEffectiveUnitStat(NUMBER_OF_UNITS, myshareddata.MODEL_ID, 0);

		for (int i = 0; i < 5; i++) {

			int item_id = myNPCDB[type].items_held_default[i];
			if (item_id > 0) {
				EditUnitItemIDQuantity(x, y, item_id, +1, false);

			}
		}

		for (int slot = 0; slot < 20; slot++) {
			TryToEquipItem(slot, NUMBER_OF_UNITS, false);

		}

		NUMBER_OF_UNITS++;

		if (myPNum == 1) {
			DMLighting();
		}

		return (NUMBER_OF_UNITS - 1);
	}

	void GenericServerBroadcast(String base, String[] s) {

		String message = "";

		for (int n = 0; n < s.length; n++) {
			if (s[n].length() == 0) {
				s[n] = "000";
			} else if (s[n].length() == 1) {
				s[n] = "00" + s[n];
			} else if (s[n].length() == 2) {
				s[n] = "0" + s[n];
			}
			message = message + "." + s[n];
		}

		// Tell all the clients about this
		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage(base + message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	void SimpleServerBroadcast(String s) {

		for (int i = 0; i < 9; i++) {
			if (ShouldSendMessagesToPlayer(i)) {

				try {
					networkThread_serv.t[i].sendMessage(s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	boolean ShouldSendMessagesToPlayer(int i) {

		if (networkThread_serv.t[i] != null && Players[i] != null) {
			if (Players[i].IsConnected) {
				return true;
			}

		}
		return false;
	}

	/*
	 * void Hero_SpecialAbility_ChangeFacing(int facing) { if
	 * (!BattlePhaseEngaged ||
	 * (BattlingUnits[BattlingUnits[BattlePhase_ActiveUnit]]==myPNum &&
	 * Units[myPNum].stat[SharedData.ENERGY] >= CHANGEFACING_ENERGYCOST ) ) {
	 * 
	 * Client_EditStats(myshareddata.FACING, queued_hero_facing);
	 * if(BattlePhaseEngaged){ Client_EditStats(SharedData.ENERGY,
	 * Units[myPNum].stat[SharedData.ENERGY] - CHANGEFACING_ENERGYCOST);}
	 * CalculateClientHeroLighting(); } }
	 */

	/*
	 * void RefreshHeroWeaponSkills() {
	 * 
	 * 
	 * 
	 * 
	 * int NEXT_WEPSKILL_INDEX = 0;
	 * 
	 * int new_weaponskills[] = new int[10]; // /define this then fill it for
	 * (int i = 0; i < 1000; i++) { int this_wep =
	 * Units[myPNum].itemInSlot[SharedData.ITEMSLOT_MAINHAND]; int this_wep_type
	 * = myItemDB[this_wep].weapontype; if (NEXT_WEPSKILL_INDEX < 10 &&
	 * myAbilityDB[i].abilityspec == 11 && myAbilityDB[i].weapon_required ==
	 * this_wep_type && myAbilityDB[i].enabled) {
	 * new_weaponskills[NEXT_WEPSKILL_INDEX] = i; NEXT_WEPSKILL_INDEX++; } } for
	 * (int i = 0; i < 1000; i++) { int this_wep =
	 * Units[myPNum].itemInSlot[SharedData.ITEMSLOT_OFFHAND]; int this_wep_type
	 * = myItemDB[this_wep].weapontype; if (NEXT_WEPSKILL_INDEX < 10 &&
	 * myAbilityDB[i].abilityspec == 11 && myAbilityDB[i].weapon_required ==
	 * this_wep_type && myAbilityDB[i].enabled) {
	 * new_weaponskills[NEXT_WEPSKILL_INDEX] = i; NEXT_WEPSKILL_INDEX++; } }
	 * 
	 * NEXT_WEPSKILL_INDEX = 0;
	 * 
	 * boolean ability_is_weaponskill[] = new boolean[10]; // /define this then
	 * // fill it for (int i = 0; i < 10; i++) { int ability =
	 * Units[myPNum].active_skills[i]; if (myAbilityDB[ability].abilityspec ==
	 * 11) {// if it is a weapon // skill ability_is_weaponskill[i] = true;
	 * Units[myPNum].active_skills[i] = 0; } else { ability_is_weaponskill[i] =
	 * false; } }
	 * 
	 * // use the filled arrays to repopulate the ability bar! for (int i = 0; i
	 * < 10; i++) { if (ability_is_weaponskill[i]) {
	 * Units[myPNum].active_skills[i] = new_weaponskills[NEXT_WEPSKILL_INDEX];
	 * NEXT_WEPSKILL_INDEX++; } }
	 * 
	 * }
	 * 
	 * 
	 * void RefreshUnitWeaponSkills(int UnitID) {
	 * 
	 * int NEXT_WEPSKILL_INDEX = 0;
	 * 
	 * int new_weaponskills[] = new int[10]; // /define this then fill it for
	 * (int i = 0; i < 1000; i++) { int this_wep =
	 * Units[UnitID].itemInSlot[SharedData.ITEMSLOT_MAINHAND]; int this_wep_type
	 * = myItemDB[this_wep].weapontype; if (NEXT_WEPSKILL_INDEX < 10 &&
	 * myAbilityDB[i].abilityspec == 11 && this_wep_type>0 &&
	 * myAbilityDB[i].weapon_required == this_wep_type &&
	 * myAbilityDB[i].enabled) { new_weaponskills[NEXT_WEPSKILL_INDEX] = i;
	 * NEXT_WEPSKILL_INDEX++; } } for (int i = 0; i < 1000; i++) { int this_wep
	 * = Units[UnitID].itemInSlot[SharedData.ITEMSLOT_OFFHAND]; int
	 * this_wep_type = myItemDB[this_wep].weapontype; if (NEXT_WEPSKILL_INDEX <
	 * 10 && myAbilityDB[i].abilityspec == 11 && this_wep_type>0 &&
	 * myAbilityDB[i].weapon_required == this_wep_type &&
	 * myAbilityDB[i].enabled) { new_weaponskills[NEXT_WEPSKILL_INDEX] = i;
	 * NEXT_WEPSKILL_INDEX++; } }
	 * 
	 * NEXT_WEPSKILL_INDEX = 0;
	 * 
	 * // use the filled arrays to repopulate the ability bar! for (int i = 1; i
	 * < 10; i++) { boolean skillshouldbeoverwritten = true; for(int
	 * n=0;n<5;n++){
	 * if(myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].abilities[n]
	 * == Units[UnitID].active_skills[i] &&
	 * Units[UnitID].active_skills[i]>0){skillshouldbeoverwritten=false;} }
	 * 
	 * if (skillshouldbeoverwritten) { Units[UnitID].active_skills[i] =
	 * new_weaponskills[NEXT_WEPSKILL_INDEX]; NEXT_WEPSKILL_INDEX++; } }
	 * 
	 * }
	 */

	/*
	 * void changeInitSetting(int setting, int newstate) {
	 * InitSettingsData[setting] = newstate; writeSettingstoFile(true);
	 * 
	 * if (setting == myshareddata.INITSETTING_FULLSCREEN) { if (newstate == 0)
	 * { mySettings.FullScreen = false; } else { mySettings.FullScreen = true; }
	 * //refreshDisplayMode();
	 * ShowWarningDialog("Must restart for changes to take effect."); }
	 * 
	 * if (setting == myshareddata.INITSETTING_MUSICMUTED) { if (newstate == 0)
	 * { BackgroundMusic.setVolume(MUSICVOLUME_FULL); } else {
	 * BackgroundMusic.setVolume(0); }
	 * 
	 * }
	 * 
	 * }
	 */

	String ForceStringLength(String s, int mylength) {

		while (s.length() < mylength) {

			s = "0" + s;
		}

		return s;
	}

	private boolean PollClickingOnBattleQueue(int UnitSelectedID) {

		boolean clickedGUIElement = false;

		if (BattlePhaseEngaged
				&& (BattlingUnits[BattlePhase_ActiveUnit] != UnitSelectedID || Units[UnitSelectedID].stat[SharedData.PLAYER_OWNERSHIP] != myPNum)) {

			int running_x = 0;
			for (int i = 0; i < number_of_found_battling_units; i++) {

				int size = 50;
				if (i == BattlingUnits[BattlePhase_ActiveUnit]) {
					size = 60;
				} else {
					size = 50;
				}

				if (cursor.x > SCREEN_WIDTH / 2 - (number_of_found_battling_units * 50) / 2 - 5 + running_x
						&& cursor.x < SCREEN_WIDTH / 2 - (number_of_found_battling_units * 50) / 2 - 5 + running_x + size
						&& cursor.y > SCREEN_HEIGHT - size) {

					cam.x = Units[BattlingUnits[i]].x;
					cam.y = Units[BattlingUnits[i]].y;
					cam.tileselect_x = Units[BattlingUnits[i]].x;
					cam.tileselect_y = Units[BattlingUnits[i]].y;
					clickedGUIElement = true;
				}

				running_x += size;
			}

		}

		return clickedGUIElement;
	}

	private boolean PollClickingOnAbilitybars(int UnitSelectedID) {

		boolean clickedGUIElement = false;

		if (Units[UnitSelectedID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum
				&& (!BattlePhaseEngaged || BattlingUnits[BattlePhase_ActiveUnit] == UnitSelectedID)) {

			if (primaryabilitybar.BeingHovered(cursor)) {
				int clickedbarslot = (cursor.x - primaryabilitybar.x) / 50;

				if (clickedbarslot >= 0 && clickedbarslot < MainBarIcons.length) {
					clickedGUIElement = true;

					if (clickedbarslot == 0) {
						Anyone_ClickOnAbility(SPECIALABIL_WALK, UnitSelectedID, false);
						secondaryabilitybar.mode = 0;
					}

					if (clickedbarslot == 1) {
						secondaryabilitybar.mode = 1;
					}

					if (clickedbarslot == 2) {
						secondaryabilitybar.mode = 2;
					}

					if (clickedbarslot == 3) {
						secondaryabilitybar.mode = 3;
					}

					if (clickedbarslot == 4 && BattlePhaseEngaged) {
						UnitEndsBattleTurn(UnitSelectedID);
						secondaryabilitybar.mode = 0;
					}

				}
			}

			if (secondaryabilitybar.BeingHovered(cursor)) {
				int clickedbarslot = (cursor.x - secondaryabilitybar.x) / 50;

				int ActiveWepSkills[] = GetActiveWeaponSkills();
				int clickedability = 0;
				if (secondaryabilitybar.mode == 1) {
					clickedability = ActiveWepSkills[clickedbarslot];
				}
				if (secondaryabilitybar.mode == 2) {
					clickedability = Units[UnitSelectedID].active_spells[clickedbarslot];
				}

				if (clickedability != 0) {
					clickedGUIElement = true;
					if (targetting_currentability == 0) {
						Anyone_ClickOnAbility(clickedability, UnitSelectedID, (secondaryabilitybar.mode == 1));
					}
				}

				int ActiveConsumables[] = GetConsumables();// need to use
															// bagslots so the
															// item can be
															// deleted
				if (secondaryabilitybar.mode == 3) {
					ActivateConsumable(UnitSelectedID, ActiveConsumables[clickedbarslot]);
				}

			}

		}

		debug(clickedGUIElement);
		return clickedGUIElement;
	}

	private void PollConditionbarHoverTooltips(int UnitID) {

		// conditiontooltips

		if (UnitID != 0 && conditionbar.BeingHovered(cursor)) {
			int conditionslot_mousingover = (cursor.x - conditionbar.x) / 30 + ((cursor.y - conditionbar.y) / 30) * 10;

			int condition_mousingover = Units[UnitID].activeconditions[conditionslot_mousingover];

			if (condition_mousingover > 0) {
				displayedConditionHovertooltip = condition_mousingover;

			}

		}
		// tileconditiontooltips
		if (tileconditionbar.BeingHovered(cursor)) {
			int conditionslot_mousingover = (cursor.x - tileconditionbar.x) / 30 + ((cursor.y - tileconditionbar.y) / 30) * 10;

			int condition_mousingover = tileactiveconditions[cam.tileselect_x][cam.tileselect_y][conditionslot_mousingover];

			if (condition_mousingover > 0) {
				displayedConditionHovertooltip = condition_mousingover;

			}

		}

	}

	private void PollAbilitybarHoverTooltip(int UnitID) {
		// primaryabilitybar hover tooltips
		if (Units[UnitID].stat[SharedData.PLAYER_OWNERSHIP] == myPNum && (!BattlePhaseEngaged || BattlingUnits[BattlePhase_ActiveUnit] == UnitID)) {

			int slot = -1;
			for (int i = 0; i < MainBarIcons.length; i++) {
				if (cursor.x > primaryabilitybar.x + 50 * i && cursor.x < primaryabilitybar.x + 50 + 50 * i && cursor.y > primaryabilitybar.y
						&& cursor.y < primaryabilitybar.y + 50) {
					slot = i;
				}
			}

			if (slot > -1 && slot < MainBarIcons.length) {
				// String s =
				// myAbilityDB[Units[UnitID].active_abilities[slot]].description;

				displayedMiniHovertooltip = MainBarTooltips[slot];

			}

			slot = -1;
			for (int i = 0; i < 10; i++) {
				if (cursor.x > secondaryabilitybar.x + 50 * i && cursor.x < secondaryabilitybar.x + 50 + 50 * i && cursor.y > secondaryabilitybar.y
						&& cursor.y < secondaryabilitybar.y + 50) {
					slot = i;
				}
			}

			if (slot > -1) {

				int ActiveWepSkills[] = GetActiveWeaponSkills();
				if (secondaryabilitybar.mode == 1) {
					displayedAbilityHovertooltip = ActiveWepSkills[slot];
				}

				if (secondaryabilitybar.mode == 2) {
					displayedAbilityHovertooltip = Units[UnitID].active_spells[slot];
				}

				int ActiveConsumables[] = GetConsumables();
				if (secondaryabilitybar.mode == 3) {
					if (ActiveConsumables[slot] >= 0) {
						displayedItemHovertooltip = Units[UnitID].itemInSlot[ActiveConsumables[slot]];
					}
				}

			}

		}// end check for ownership

	}

	void pollInvMenuHovering() {

		int vitals_x = 30;
		int vitals_y = 170;

		String[] tooltiptext = {"Melee Power", "Ranged Power", "Spell Power", "Armor Rating", "Magic Resistance", "Toxin Resistance"};

		for (int i = 0; i < tooltiptext.length; i++) {
			if (cursor.x > Menus[MENU_INV].x + vitals_x + 60 * (i / (tooltiptext.length / 2))
					&& cursor.x < Menus[MENU_INV].x + vitals_x + 60 * (i / (tooltiptext.length / 2)) + 55
					&& cursor.y > Menus[MENU_INV].y + vitals_y + 20 * (i % (tooltiptext.length / 2))
					&& cursor.y < Menus[MENU_INV].y + vitals_y + 20 * (i % (tooltiptext.length / 2)) + 15) {
				displayedMiniHovertooltip = tooltiptext[i];
			}
		}

		int misc_stats_x = 50;
		int misc_stats_y = 240;
		String[] misctooltiptext = {"Speed", "Initiative"};

		for (int i = 0; i < misctooltiptext.length; i++) {
			if (cursor.x > Menus[MENU_INV].x + misc_stats_x && cursor.x < Menus[MENU_INV].x + misc_stats_x + 55
					&& cursor.y > Menus[MENU_INV].y + misc_stats_y + 20 * i && cursor.y < Menus[MENU_INV].y + misc_stats_y + 20 * i + 15) {
				displayedMiniHovertooltip = misctooltiptext[i];
			}
		}

		if (cursor.x > Menus[MENU_INV].x + 50 && cursor.x < Menus[MENU_INV].x + 50 + 55 && cursor.y > Menus[MENU_INV].y + 290
				&& cursor.y < Menus[MENU_INV].y + 290 + 15) {
			displayedMiniHovertooltip = "Gold Carried";
		}

		// mouse hover over item in bag
		int bagslotHoveringOver = -1;

		// hover over bag
		if (cursor.x > Menus[MENU_INV].x + 18 && cursor.x < Menus[MENU_INV].x + 18 + 240 && cursor.y > Menus[MENU_INV].y + 318
				&& cursor.y < Menus[MENU_INV].y + 318 + 160) {

			bagslotHoveringOver = (cursor.x - 18 - Menus[MENU_INV].x) / 40 + 6 * ((cursor.y - 318 - Menus[MENU_INV].y) / 40);

		}

		for (int i = 0; i < 9; i++) {

			// hover over bodyslots
			if (cursor.x > Menus[MENU_INV].x + invmenu_equipslots_x[i] && cursor.x < Menus[MENU_INV].x + invmenu_equipslots_x[i] + 40
					&& cursor.y > Menus[MENU_INV].y + invmenu_equipslots_y[i] && cursor.y < Menus[MENU_INV].y + invmenu_equipslots_y[i] + 40) {

				bagslotHoveringOver = i + 50; // the equip slots start at
												// 50!

			}

		}

		if (bagslotHoveringOver > -1) {

			int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];
			if (UnitID > 0) {

				displayedItemHovertooltip = Units[UnitID].itemInSlot[bagslotHoveringOver];

			}

			// displayedItemHovertooltip =
			// Units[myPNum].itemInSlot[bagslotHoveringOver];

		}// end check for being in bagslotarea

	}

	void DrawGenericHoverTooltip(String message) {// dynamically expanding

		final int maxwidth = 250;
		final int bg_piece_height = 30;

		int num_lines = Verdana_16.GetStringNumLines(message, maxwidth);
		int num_midsections = 0;
		if (num_lines >= 3) {
			num_midsections = (num_lines - 2);
		}

		int tooltipoffset_x = getTooltipOffsetX();
		int tooltipoffset_y = getTooltipOffsetY(num_midsections);

		int running_y = 0;

		GL11.glColor4f(1, 1, 1, 0.9f);

		drawTooltipBackground(tooltipoffset_x, tooltipoffset_y, num_midsections);

		GL11.glColor3f(1, 1, 1);

		Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5, message, maxwidth, SCREEN_HEIGHT, 20);

	}

	void DrawMiniHoverTooltip(String message) {// dynamically expanding

		if(message != null){
			if(message.length() > 0){
		
		int pixellength = Verdana_16.getStringPixelLength(message);

		int tooltipoffset_x = 10;
		int tooltipoffset_y = -20;
		if (cursor.x > SCREEN_WIDTH - pixellength) {
			tooltipoffset_x -= (pixellength + 20);
		}

		GL11.glColor4f(0, 0, 0, 0.9f);

		Fill_Rect(cursor.x + tooltipoffset_x - 5, cursor.y + tooltipoffset_y - 5, pixellength + 10, 16 + 10);

		GL11.glColor3f(1, 1, 1);

		Verdana_16.drawString(cursor.x + tooltipoffset_x, cursor.y + tooltipoffset_y, message);
				}
			}
		
	}

	void DrawAbilityHoverTooltip(int num) {

		final int maxwidth = 250;
		final int bg_piece_height = 30;

		String name = myAbilityDB[num].namestring;
		String descrip = myAbilityDB[num].description;

		int num_lines = 2 + Verdana_16.GetStringNumLines(descrip, maxwidth);
		int num_midsections = 0;
		if (num_lines >= 3) {
			num_midsections = (num_lines - 2);
		}

		int tooltipoffset_x = getTooltipOffsetX();
		int tooltipoffset_y = getTooltipOffsetY(num_midsections);

		int running_y = 0;

		GL11.glColor4f(1, 1, 1, 0.9f);

		drawTooltipBackground(tooltipoffset_x, tooltipoffset_y, num_midsections);

		int pixellength = Verdana_18.getStringPixelLength(name);
		GL11.glColor3f(0.3f, 1f, 0.3f);
		Verdana_18.drawString(cursor.x + tooltipoffset_x + 135 - pixellength / 2, cursor.y + tooltipoffset_y - 5 + 20 * 0, name);

		int UnitID = UnitMap[cam.tileselect_x][cam.tileselect_y];

		GL11.glColor3f(0.8f, 0.8f, 0.6f);// yellow
		Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5 + 20 * 1,
				"Cost: " + myAbilityDB[num].getEffectiveEnergyCost(Units[UnitID]));

		Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x + 80, cursor.y + tooltipoffset_y - 5 + 20 * 1,
				"Cooldown: " + myAbilityDB[num].getEffectiveCooldown(Units[UnitID]));

		if (myAbilityDB[num].getEffectiveRange(Units[UnitID]) > 0 && myAbilityDB[num].getEffectiveRange(Units[UnitID]) < 100) {
			Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x + 195, cursor.y + tooltipoffset_y - 5 + 20 * 1,
					"Range: " + myAbilityDB[num].getEffectiveRange(Units[UnitID]));
		}

		if (myAbilityDB[num].weapon_required > 0) {
			if (myAbilityDB[num].weapon_required == myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_MAINHAND]].weapontype
					|| myAbilityDB[num].weapon_required == myItemDB[Units[UnitID].itemInSlot[SharedData.ITEMSLOT_OFFHAND]].weapontype) {
				GL11.glColor3f(0.2f, 0.8f, 0.2f);
			} else {
				GL11.glColor3f(0.8f, 0.2f, 0.2f);
			}

			Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5 + 20 * 2, "Requires: "
					+ SharedData.ComboStrings[SharedData.COMBOTEXT_WEAPONTYPES][myAbilityDB[num].weapon_required]);
		}

		GL11.glColor3f(1, 1, 1);
		Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5 + 20 * 3, descrip, maxwidth, SCREEN_HEIGHT, 20);

	}

	// create these! :D
	void DrawItemHoverTooltip(int num) {

		final int maxwidth = 250;
		final int bg_piece_height = 30;

		String name = myItemDB[num].namestring;
		String descrip = myItemDB[num].description;

		int num_lines = 2 + Verdana_16.GetStringNumLines(descrip, maxwidth);
		int num_midsections = 0;
		if (num_lines >= 3) {
			num_midsections = (num_lines - 2);
		}

		int tooltipoffset_x = getTooltipOffsetX();
		int tooltipoffset_y = getTooltipOffsetY(num_midsections);

		int running_y = 0;

		GL11.glColor4f(1, 1, 1, 0.9f);

		drawTooltipBackground(tooltipoffset_x, tooltipoffset_y, num_midsections);

		// int pixellength = Verdana_18.getStringPixelLength(name);
		GL11.glColor3f(0.3f, 1f, 0.3f);
		Verdana_18.drawString(cursor.x + tooltipoffset_x + 5, cursor.y + tooltipoffset_y - 5 + 20 * 0, name);

		if (myItemDB[num].value > 0) {
			GL11.glColor3f(0.8f, 0.8f, 0.3f);
			Verdana_14.drawString(cursor.x + tooltipoffset_x + 5, cursor.y + tooltipoffset_y - 5 + 20 * 1, "" + myItemDB[num].value);
			GL11.glColor3f(1, 1, 1);
			coinsicon.draw_FitToSize(cursor.x + tooltipoffset_x + 0 + Verdana_14.getStringPixelLength("" + myItemDB[num].value) + 5, cursor.y
					+ tooltipoffset_y - 5 + 20 * 1, 15);
		}

		if (myItemDB[num].slotassignment > 0) {
			GL11.glColor3f(0.9f, 0.9f, 0.9f);
			Verdana_14.drawString(cursor.x + tooltipoffset_x + 5, cursor.y + tooltipoffset_y - 5 + 20 * 2, "Equips to "
					+ SharedData.ComboStrings[SharedData.COMBOTEXT_EQUIPSLOTS][myItemDB[num].slotassignment]);
		}

		GL11.glColor3f(0.5f, 0.5f, 0.5f);
		Verdana_14.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5 + 20 * 3, descrip, maxwidth, SCREEN_HEIGHT, 20);

	}

	void DrawConditionHoverTooltip(int num) {

		final int maxwidth = 250;
		final int bg_piece_height = 30;

		String name = myConditionDB[num].namestring;
		String descrip = myConditionDB[num].description;

		int num_lines = 0 + Verdana_16.GetStringNumLines(descrip, maxwidth);
		int num_midsections = 0;
		if (num_lines >= 2) {
			num_midsections = (num_lines - 1);
		}

		int tooltipoffset_x = getTooltipOffsetX();
		int tooltipoffset_y = getTooltipOffsetY(num_midsections);

		int running_y = 0;

		GL11.glColor4f(1, 1, 1, 0.9f);

		drawTooltipBackground(tooltipoffset_x, tooltipoffset_y, num_midsections);

		// int pixellength = Verdana_18.getStringPixelLength(name);
		GL11.glColor3f(0.3f, 1f, 0.3f);
		Verdana_18.drawString(cursor.x + tooltipoffset_x + 5, cursor.y + tooltipoffset_y - 5 + 20 * 0, name);

		GL11.glColor3f(1, 1, 1);
		Verdana_16.drawString(cursor.x + 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 5 + 20 * 1, descrip, maxwidth, SCREEN_HEIGHT, 20);

	}

	void drawTooltipBackground(int tooltipoffset_x, int tooltipoffset_y, int num_midsections) {

		hovertooltip_top.draw(cursor.x - 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 10);

		for (int i = 0; i < num_midsections; i++) {
			hovertooltip_mid.draw(cursor.x - 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 10 + (30 * i) + 30);
		}

		hovertooltip_bot.draw(cursor.x - 5 + tooltipoffset_x, cursor.y + tooltipoffset_y - 10 + (30 * num_midsections) + 30);

	}

	int getTooltipOffsetX() {
		int answer = 0;

		if (cursor.x < 100) {
			// answer = 120;
			answer = 20;
		} else if (cursor.x > SCREEN_WIDTH - 300) {
			// answer = -100;
			answer = -200;
		}
		return answer;

	}

	int getTooltipOffsetY(int num_midsections) {
		int answer = 0;

		if (cursor.y < 40 + num_midsections * 30) {
			answer = 30;
		} else {
			answer = -70 + (-30 * num_midsections);
		}

		return answer;
	}

	void DrawUnitChatBubble(int UnitID, int screen_x, int screen_y) {// dynamically
																		// expanding

		String message = Units[UnitID].chat_bubble_text;

		final int maxwidth = 100;
		final int bg_piece_height = 15;

		int num_lines = Verdana_14.GetStringNumLines(message, maxwidth);
		int num_midsections = 0;
		if (num_lines >= 2) {
			num_midsections = (num_lines - 1);
		}

		int tooltipoffset_x = 0;
		int tooltipoffset_y = num_lines * -15;

		int running_y = 0;

		GL11.glColor4f(1, 1, 1, 0.9f);

		chatbubble_top.draw(screen_x - 5 + tooltipoffset_x, screen_y + tooltipoffset_y - 10);

		for (int i = 0; i < num_midsections; i++) {
			chatbubble_mid.draw(screen_x - 5 + tooltipoffset_x, screen_y + tooltipoffset_y - 10 + (bg_piece_height * i) + bg_piece_height);
		}

		chatbubble_bot.draw(screen_x - 5 + tooltipoffset_x, screen_y + tooltipoffset_y - 10 + (bg_piece_height * num_midsections) + bg_piece_height);

		GL11.glColor3f(1, 1, 1);

		Verdana_14.drawString(screen_x + 5 + tooltipoffset_x, screen_y + tooltipoffset_y - 5, message, maxwidth, SCREEN_HEIGHT, 16);

	}

	void DrawButton(int style, String text, int x, int y) {

		int width = 200;
		int center_x = x - width / 2;

		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		Fill_Rect(center_x + 1, y + 1, width, 40);
		GL11.glColor3f(0, 0, 0);
		Fill_Rect(center_x, y, width, 40);

		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		Verdana_18.drawString(center_x + 30 + 1, y + 10 + 1, text);
		GL11.glColor3f(1, 1, 1);
		Verdana_18.drawString(center_x + 30, y + 10, text);

	}

	void DrawLoginMenu() {
		if (ACCOUNT_NAME == null) {

			if (!Menus[MENU_LOGIN].is_open) {

				GL11.glColor3f(1, 1, 1);
				button_login_inner.draw(0, 0);

				GL11.glColor3f(0.1f, 0.1f, 0.1f);
				if (cursor.WithinRect(0, 0, (int) button_login_outer.getWidth(), (int) button_login_outer.getHeight())) {
					GL11.glColor3f(0.3f, 0.3f, 0.3f);
				}
				button_login_outer.draw(0, 0);

			} else {
				GL11.glColor3f(1, 1, 1);
				// Fill_Rect(Menus[MENU_LOGIN].x,Menus[MENU_LOGIN].y,Menus[MENU_LOGIN].width,Menus[MENU_LOGIN].height);
				Menus[MENU_LOGIN].background.draw(Menus[MENU_LOGIN].x, Menus[MENU_LOGIN].y);
				GL11.glColor3f(1, 1, 1);
				menu_exit_x.draw(Menus[MENU_LOGIN].x + Menus[MENU_LOGIN].exitbutton_x, Menus[MENU_LOGIN].y + Menus[MENU_LOGIN].exitbutton_y);

				if (cursor.WithinRect(Menus[MENU_LOGIN].x + 40, Menus[MENU_LOGIN].y + 220, 130, 40)) {
					GL11.glColor3f(0.8f, 0.8f, 0.8f);
				}
				button_login_accept.draw(Menus[MENU_LOGIN].x + 40, Menus[MENU_LOGIN].y + 240);

				GL11.glColor3f(1, 1, 1);
				if (cursor.WithinRect(Menus[MENU_LOGIN].x + 230, Menus[MENU_LOGIN].y + 220, 130, 40)) {
					GL11.glColor3f(0.8f, 0.8f, 0.8f);
				}
				button_login_register.draw(Menus[MENU_LOGIN].x + 230, Menus[MENU_LOGIN].y + 240);

				drawTickBox(MENU_LOGIN, 40, 200, mySettings.RememberLogin, "Remember Login");
			}

		} else {

			GL11.glColor3f(1, 1, 1);
			Verdana_16.drawString(10, 5, "Signed in as: " + ACCOUNT_NAME);
			if (cursor.WithinRect(5, 21, (int) button_logout.getWidth(), (int) button_logout.getHeight())) {
				GL11.glColor3f(0.7f, 0.7f, 0.7f);
			} else {
				GL11.glColor3f(1, 1, 1);
			}
			button_logout.draw(5, 21);
		}
	}

	void handleinputs_LoginMenu() {

		/** MOUSE DRAGGED */
		if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {

			/** MOUSE PRESSED */
			if (Mouse_MouseAlreadyClicked[0] == false && Mouse_MouseAlreadyClicked[1] == false) {

				if (Menus[MENU_LOGIN].ExitButtonBeingHovered_AndOpen(cursor)) {
					Menus[MENU_LOGIN].is_open = false;
				} else {

					if (ACCOUNT_NAME == null) {
						if (!Menus[MENU_LOGIN].is_open) {
							if (cursor.WithinRect(0, 0, (int) button_login_inner.getWidth(), (int) button_login_inner.getHeight())) {
								openGameMenu(MENU_LOGIN);

							}

						} else {
							if (cursor.WithinRect(Menus[MENU_LOGIN].x + 40, Menus[MENU_LOGIN].y + 240, 130, 40)) {
								PressedLoginAcceptButton();
							}

							if (cursor.WithinRect(Menus[MENU_LOGIN].x + 230, Menus[MENU_LOGIN].y + 240, 130, 40)) {
								try {
									java.awt.Desktop.getDesktop().browse(java.net.URI.create(SharedData.BANEFORGE_WEBSITE_REGISTER_URL));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (cursor.WithinRect(Menus[MENU_LOGIN].x + 40, Menus[MENU_LOGIN].y + 200, 140, 20)) {
								if (mySettings.RememberLogin) {
									mySettings.RememberLogin = false;
								} else {
									mySettings.RememberLogin = true;
								}
								writeSettingstoFile(true);
							}

						}
					} else {

						if (cursor.WithinRect(5, 21, (int) button_logout.getWidth(), (int) button_logout.getHeight())) {
							ACCOUNT_NAME = null;
							mySettings.username = "";
							mySettings.password = "";
							writeSettingstoFile(true);
						}

					}

				}

			}
		}
	}

	void PressedLoginAcceptButton() {// from pressing enter or by clicking!

		String name = (myTextBoxes[TEXTBOX_LOGINNAME].text).toLowerCase();
		String password = myTextBoxes[TEXTBOX_LOGINPASSWORD].text;

		// try to contact mysql which will in turn set ACCOUNT_NAME and close
		// the login menu

		boolean success = CheckCredentialsAgainstMYSQLUsers(name, password);
		if (success) {
			Menus[MENU_LOGIN].is_open = false;
			ACCOUNT_NAME = capitalizeString(name);

			if (mySettings.RememberLogin) {
				mySettings.username = name;
				mySettings.password = password;
				writeSettingstoFile(true);

				SleekMessageBox.tweencount = 100 + SCREEN_HEIGHT;// NEED TO DRAW
																	// THIS
				SleekMessageBox.text = "Login Successful!";
			} else {
				myTextBoxes[TEXTBOX_LOGINNAME].text = "";
				myTextBoxes[TEXTBOX_LOGINPASSWORD].text = "";
			}
		} else {
			myTextBoxes[TEXTBOX_LOGINNAME].text = "";
			myTextBoxes[TEXTBOX_LOGINPASSWORD].text = "";
			ShowWarningDialog("Invalid combination.");
		}

	}

	boolean InvMenu_BonusStats_MustBeRefreshed = false;

	void Refresh_InvMenu_BonusStats(int UnitID_inventoryMenu) {// do this when
																// changing
																// selected unit
																// while invmenu
																// is open ANd
																// when items
																// are swapped
																// while invmenu
																// is open
		if (UnitID_inventoryMenu > 0) {

			InvMenu_BonusStats_MustBeRefreshed = false;

			int statstodraw[] = {SharedData.MELEEPOWER, SharedData.RANGEDPOWER, SharedData.SPELLPOWER, SharedData.ARMORRATING,
					SharedData.MAGICRESIST, SharedData.TOXINRESIST, SharedData.SPEED, SharedData.INITIATIVE};
			for (int i = 0; i < statstodraw.length; i++) {
				invmenu_bonusstats[i] = GetEffectiveUnitStat(UnitID_inventoryMenu, statstodraw[i], 0)
						- Units[UnitID_inventoryMenu].stat[statstodraw[i]];
			}

			if (UnitID_inventoryMenu > 10) {

				for (int i = 0 + SharedData.ENDURANCE; i < 8 + SharedData.ENDURANCE; i++) {
					invmenu_bonusstats[i] = GetEffectiveUnitStat(UnitID_inventoryMenu, i, 0) - Units[UnitID_inventoryMenu].stat[i];
				}

			}
		}
	}

	void debug(Object o) {
		if (SharedData.PRINT_CONSOLE_INFO) {
			System.out.println(o);
		}
	}

	void ShowWarningDialog(String message) {

		PopUpMessage = message;
		PopUpMessageTimeLeft = 5000;

		debug(message);

	}

	static int pythagorean(int x1, int y1, int x2, int y2) {
		return (int) (Math.sqrt(Math.abs(Math.pow(x1 - x2, 2)) + Math.abs(Math.pow(y1 - y2, 2))));
	}

	int DirectionBetweenCoordinates(int x1, int y1, int x2, int y2) {
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

	boolean coordinatesOnScreen(int x, int y) {

		if (x < cam.x + SCREEN_X_TILES / 2 && x > cam.x - SCREEN_X_TILES / 2 && y < cam.y + SCREEN_Y_TILES / 2 && y > cam.y - SCREEN_Y_TILES / 2) {
			return true;
		}

		return false;

	}

	String GetUnitName(int UnitID) {
		String unit_name = "";
		if (UnitID < 10) {
			unit_name = Players[UnitID].name;
		} else {
			unit_name = myNPCDB[Units[UnitID].stat[SharedData.UNITDATA_NPCTYPE]].namestring;
		}
		if (unit_name == null) {
			unit_name = "";
		}
		return unit_name;

	}

	Image2D GetUnitPortrait(int UnitID) {

		Image2D answer = null;

		if (UnitID < 10) {
			answer = ProfileImages[UnitID];
		} else {
			answer = myUnitModels[myNPCDB[Units[UnitID].stat[myshareddata.UNITDATA_NPCTYPE]].model_ID].profileimage;
		}

		if (answer == null) {
			answer = ProfileImage_blank;
		}

		return answer;

	}

	public static int LevelToTotalExp(int n) {
		int expcount = 0;
		for (int i = (n - 1); i >= 1; i--) {

			expcount += 10 * i;

		}

		return expcount;
	}

	public static int[] TotalExpToLevel(int n) {
		int levelcount = 1;
		while (n - levelcount * 10 >= 0) {

			n -= levelcount * 10;
			levelcount++;

		}

		int answer[] = {levelcount, n};

		return answer;

	}

	String BooleanToString(boolean b) {
		if (b) {
			return "1";
		}
		return "0";

	}

	int GetStatStringIndex(int stat) {
		for (int i = 0; i < SharedData.StatChoices.length; i++) {
			if (SharedData.StatChoices[i] == stat) {
				return i;

			}
		}
		return -1;
	}

	public boolean UnitCanWalk(int UnitID) {

		if (GetEffectiveUnitStat(UnitID, SharedData.DEAD, 0) == 0
		// && !Units[UnitID].is_sliding
				&& GetEffectiveUnitStat(UnitID, SharedData.WALK_MOVES_COUNT, 0) > 0 && GetEffectiveUnitStat(UnitID, SharedData.IMMOBILE, 0) == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean UnitCanAttack(int UnitID) {

		if (GetEffectiveUnitStat(UnitID, SharedData.DEAD, 0) == 0 && GetEffectiveUnitStat(UnitID, SharedData.DISARMED, 0) == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean UnitCanActivateAbilities(int UnitID) {

		if (GetEffectiveUnitStat(UnitID, SharedData.DEAD, 0) == 0 && GetEffectiveUnitStat(UnitID, SharedData.SILENCED, 0) == 0) {
			return true;
		} else {
			return false;
		}

	}

	void MYSQL_StartHostingServer(String myaddressForSQL, String myportForSQL, String mynameForSQL) {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://baneforge.com/toasty1_srvlst";
		String user = "toasty1_ingame";
		String password = "*OpTLe23#(G_"; //grants minimal access

		try {

			con = DriverManager.getConnection(url, user, password);

			// safety against injections!!!
			st = con.prepareStatement("INSERT INTO servers (IPADDRESS,PORT,NAME,UNIQUEID,VERSION) values (?, ?, ?, ?, ?)");
			st.setString(1, myaddressForSQL);
			st.setString(2, myportForSQL);
			st.setString(3, mynameForSQL);
			st.setString(4, "" + MYSQL_UNIQUEKEY);
			st.setString(5, SharedData.CURRENTVERSION);
			st.executeUpdate();

		} catch (SQLException ex) {
			debug(ex);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				debug(ex);
			}
		}

	}

	void MYSQL_StopHostingServer() {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://baneforge.com/toasty1_srvlst";
		String user = "toasty1_ingame";
		String password = "*OpTLe23#(G_";

		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");

			if (rs.next()) {

				System.out.println(rs.getString(1));

			}

			String query = "DELETE FROM servers WHERE UNIQUEID = '" + MYSQL_UNIQUEKEY + "'";
			st.executeUpdate(query);

		} catch (SQLException ex) {
			debug(ex);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				debug(ex);
			}
		}

	}

	boolean CheckCredentialsAgainstMYSQLUsers(String username, String userpassword) {

		int count_userexistswithnameandpassword = 0;

		if (username.length() < 3 || userpassword.length() < 6) {
			return false;
		}

		// send username and password (plaintext :() out to the website with
		// POST

		try {

			// you need to encode ONLY the values of the parameters
			String param = "username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(userpassword, "UTF-8")
					+ "&ipaddress=" + URLEncoder.encode(ipAddr_global, "UTF-8");
			
			//Will allow for a log in if the ipaddress is the same or if timestamp is older than an hour!

			HttpURLConnection conn = (HttpURLConnection) new URL("http://baneforge.com/queryaccount.php").openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// Android documentation suggested that you set the length of the
			// data you are sending to the server, BUT
			// do NOT specify this length in the header by using
			// conn.setRequestProperty("Content-Length", length);
			// use this instead.
			conn.setFixedLengthStreamingMode(param.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// send the POST out
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.close();

			// build the string to store the response text from the server
			String response = "";

			// start listening to the stream
			Scanner inStream = new Scanner(conn.getInputStream());

			// process the stream and store it in StringBuilder
			while (inStream.hasNextLine()) {
				response += (inStream.nextLine());
			}
			debug("r" + response);

			count_userexistswithnameandpassword = StringToInteger(response);

			debug(count_userexistswithnameandpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * 
		 * Connection con = null; PreparedStatement st = null; ResultSet rs =
		 * null;
		 * 
		 * String dburl = "jdbc:mysql://baneforge.com/toasty1_accounts"; String
		 * dbuser = "toasty1_ingame"; String dbpassword = "*********";
		 * 
		 * String salt = null;
		 * 
		 * try {
		 * 
		 * con = DriverManager.getConnection(dburl, dbuser, dbpassword); String
		 * query = "";
		 * 
		 * // /see if user exists
		 * 
		 * query = "SELECT COUNT(*) AS total FROM users WHERE name = ?";
		 * 
		 * st = con.prepareStatement(query); st.setString(1, username);
		 * 
		 * rs = st.executeQuery(); // this is erroring out
		 * 
		 * rs.next(); count_userexistswithname = rs.getInt("total"); if
		 * (count_userexistswithname == 0) { return false; }
		 * 
		 * // once we know user exists, get their salt
		 * 
		 * query = "SELECT * FROM users WHERE (name = ?) "; st =
		 * con.prepareStatement(query); st.setString(1, username); rs =
		 * st.executeQuery();
		 * 
		 * rs.next(); salt = rs.getObject(3).toString();
		 * 
		 * // /use salt + attempt = hash. Does exactly what PHP does!
		 * 
		 * String userpassword_encrypted = userpassword + salt;
		 * 
		 * for (int i = 0; i < 1471; i++) { try { userpassword_encrypted =
		 * sha(userpassword_encrypted); } catch (Exception e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } } //
		 * debug(userpassword_encrypted);
		 * 
		 * // new hash = original hash??
		 * 
		 * query =
		 * "SELECT COUNT(*) AS total FROM users WHERE (name = ?) AND (password = ?) "
		 * ;
		 * 
		 * st = con.prepareStatement(query); st.setString(1, username);
		 * st.setString(2, userpassword_encrypted); rs = st.executeQuery();
		 * 
		 * rs.next();
		 * 
		 * count_userexistswithnameandpassword = rs.getInt("total");
		 * 
		 * } catch (SQLException ex) { debug(ex);
		 * 
		 * } finally { try { if (rs != null) { rs.close(); } if (st != null) {
		 * st.close(); } if (con != null) { con.close(); }
		 * 
		 * } catch (SQLException ex) { debug(ex); return false; } }
		 */
		return (count_userexistswithnameandpassword > 0);
	}

	public String sha(String passwordandsalt) throws Exception {

		String hash = passwordandsalt;

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashbytes = hash.getBytes("UTF-8");
		md.update(hashbytes);

		byte[] byteData = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return (sb.toString());

		// return new String(hashbytes);

	}

	void TryToAutoLogin() {

		// get name and password from storage on PC STORED IN XML WITH SETTINGS
		String name = mySettings.username;
		String password = mySettings.password;

		boolean success = false;
		if (name != null && password != null) {
			success = CheckCredentialsAgainstMYSQLUsers(name, password);
		}
		if (success) {
			ACCOUNT_NAME = name;
		}

	}

	float GetCurrentMusicVolume() {

		if (mySettings.MusicMuted) {
			return 0;
		} else {
			return MUSICVOLUME_FULL;
		}

	}

	public static String capitalizeString(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
																									// can
																									// add
																									// other
																									// chars
																									// here
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	boolean coordinatesWithinMapBounds(int x, int y) {

		if (x > -1 && x < SharedData.MAP_SIZE && y > -1 && y < SharedData.MAP_SIZE) {
			return true;
		}

		return false;

	}

	boolean RunningInGoldMode() {
		// return ACCOUNT_NAME != null;
		return true; // DRM disabled for now
	}

	String GenerateRandomHeroName() {
		String name = "";

		int index = (int) (Math.random() * (SharedData.HeroNames.length));

		name = SharedData.HeroNames[index];

		return name;

	}

	void RandomizeMainMenuHint() {
		int lasthint = MainMenu_CurrentHint;
		int newhint = MainMenu_CurrentHint;

		while (newhint != lasthint) {
			newhint = (int) (Math.random() * SharedData.mainmenuhints.length);
		}

	}

	/**
	 * Get the String residing on the clipboard.
	 * 
	 * @return any text found on the Clipboard; if none found, return an empty
	 *         String.
	 */
	public String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// odd: the Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException ex) {
				// highly unlikely since we are using a standard DataFlavor
				System.out.println(ex);
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return result;
	}

	int tutorial_progress = 0;
	int last_hint_ID = -1;

	private int GetCurrentHint() {
		int answer = -1;

		if (myPNum == 1) {

			if (tutorial_phase == 0) {
				if (!Menus[MENU_ASSETTABLE].is_open) {
					answer = 0;
				}
			}

		}

		return answer;
	}

	void poll_hint_phase() {
		if (Menus[MENU_ASSETTABLE].is_open) {
			AdvanceTutorialPhase(1);
		}

	}

	void AdvanceTutorialPhase(int i) {
		if (i > tutorial_phase) {
			tutorial_phase = i;
		}
	}

	void poll_Unit_Special_Stats() {

		if (myPNum == 1) {
			SimpleServerBroadcast("/pollspecialstats");
		}

		for (int i = 0; i < NUMBER_OF_UNITS; i++) {

			if (Units[i] != null) {

				// special_stats
				if (GetEffectiveUnitStat(i, myshareddata.INVISIBLE, 0) > 0) {
					Units[i].IsInvisible = true;
				} else {
					Units[i].IsInvisible = false;
				}

				Units[i].current_model = GetEffectiveUnitStat(i, myshareddata.MODEL_ID, 0);

			}

		}
	}

}// end core as a whole

