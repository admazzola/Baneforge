package wrap;

/*
 * Contains all of the static data and declarations for the whole game
 */


public class SharedData {
	
	
	public static final String BANEFORGE_WEBSITE_URL = "www.baneforge.com";
	public static final String BANEFORGE_WEBSITE_REGISTER_URL = "www.baneforge.com/register";

	
	public static final String CURRENTVERSION = "0.85 (Beta)";
	
	public static final boolean PRINT_CONSOLE_INFO = true;

	
	static String baseDirectory()
	{
		
		
	    String OS = System.getProperty("os.name").toUpperCase();
	    
	    String answer = System.getProperty("user.dir");
	  
	    if (OS.contains("WIN")){
	    	answer = System.getenv("APPDATA");}
	    else if (OS.contains("MAC")){
	    	answer = System.getProperty("user.home") + "/Library/Application "
	                + "Support";}
	    else if (OS.contains("NUX")){
	    	answer = System.getProperty("user.home");}
	    
	    answer += "\\Baneforge";
	    
	    return answer;
	}
	
	static String defaultDirectory()
	{
		
	    return baseDirectory()+"\\"+CURRENTVERSION;
	}
	
	
	//begin base game data

	public static final int MAP_SIZE = 100;
	public static final int NUMBER_OF_TYPES = 1000;
	/** Number of layers in the map. */
	public static final int NUMBER_OF_LAYERS = 6;
	/** The map layer of the ground. */
	public static final int TERRAIN_LAYER_LOWER = 0;   //types of terrain tiles include WALLS, DOORS, STONETILE...
	public static final int TERRAIN_LAYER_HIGHER = 1;
	/** The map layer of the small objects low on the ground like items and traps. */
	public static final int SMALLOBJECTS_LAYER = 2; 
	/** The map layer of the Objects high up on the ground. */
	public static final int PLAYER_LAYER = 3;   //types of player-layer tiles include HEROSTARTPOINT1 2 3, ENEMIES, CHEST, TRAP...
	/** The map layer of the large objects that cover up players like walls and pillars. */
	public static final int LARGEOBJECTS_LAYER = 4; 
	/** The map layer of regions */
	public static final int REGION_LAYER = 5; 
	
	
	

	public static final int ABILITYSPEC_BRAWN = 1;
	public static final int ABILITYSPEC_PROTECTION = 2;
	public static final int ABILITYSPEC_NATURE = 3;
	public static final int ABILITYSPEC_DARKNESS = 4;
	public static final int ABILITYSPEC_ALCHEMY = 5;
	public static final int ABILITYSPEC_PRECISION = 6;
	public static final int ABILITYSPEC_EXPLOSIVES = 7;
	public static final int ABILITYSPEC_MECHATRONICS = 8;
	public static final int ABILITYSPEC_WIZARDRY = 9;
	public static final int ABILITYSPEC_SORCERY = 10;
	public static final int ABILITYSPEC_WEAPONSKILL = 11;
	
	
	
	public static final int ANIM_STATIC = 0;
	public static final int ANIM_STAND = 1;
	public static final int ANIM_WALK = 2;
	public static final int ANIM_CAST = 3;
	public static final int ANIM_THRUST = 4;
	public static final int ANIM_SWING = 5;
	public static final int ANIM_SHOOT = 6;
	public static final int ANIM_DEATH = 7;
	
	public static final int INITSETTING_NAME = 0;
	public static final int INITSETTING_PASSWORD = 1;
	public static final int INITSETTING_FULLSCREEN = 2;
	public static final int INITSETTING_MUSICMUTED = 3;
	
	//int[] HeroAnimationNumFrames = {1,9,7,8,6,13,6};
	//static int[] HeroAnimationNumFrames = {1,1,9,7,8,6,11,6};
	
	int[] ObjectLightRadiation = new int[NUMBER_OF_TYPES];
	// stats assignments

	//Stat declarations
	//0 to 9 are basic stats, very important but cannot be directly changed by heroes

	public static final int LEVEL = 1;		
	public static final int EXP = 2;	
	
	//vitals
	public static final int HEALTH = 3;  //current
	public static final int HEALTHREGEN = 4;  //current
	public static final int ENERGY = 5;  //current	
	public static final int STARTINGENERGY = 6;  //current
	public static final int ENERGYGROWTH = 7;  //current
	public static final int ENERGYMAX = 8;  //current
	
	//draw these in INV menu
	public static final int MELEEPOWER = 10;//affected by STR
	public static final int RANGEDPOWER = 11;//affected by DEX
	public static final int SPELLPOWER = 12;//affected by INT
	public static final int ARMORRATING = 13; //physical resist
	public static final int MAGICRESIST = 14;
	public static final int TOXINRESIST = 15;
	public static final int INITIATIVE = 16;	
	public static final int SPEED = 17;
	
	//public static final int MISCBASESTAT_1 = 18;
	//public static final int MISCBASESTAT_2 = 19;
	
	public static final int NUM_OF_BASESTATS = 8;// set in stone for heroes
	//10 to 29 are the moddable stats, changed when making a new hero and by items/things
		public static final int ENDURANCE = 20;  //max health per level
		public static final int STAMINA = 21;   //max energy
		public static final int AGILITY = 22;  //energy regen
		public static final int PERCEPTION = 23;//replaces light
		public static final int STRENGTH = 24;//melee weps
		public static final int DEXTERITY = 25;//ranged weps
		public static final int INTELLIGENCE = 26;//magic weps
		public static final int WISDOM = 27; //spell casting
		public static final int SPEC_1 = 28; 	
		public static final int SPEC_2 = 29; 			
		
		
   //30 and beyond are misc stats. only for underlying game mechanics.
		public static final int DEAD = 30;
		public static final int DISARMED = 31;
		public static final int IMMOBILE = 32;
		public static final int SILENCED = 33;
		public static final int EXP_REWARD = 34; //for enemies, the amt of EXP granted when they are slain	
		public static final int PLAYER_OWNERSHIP = 35;
		public static final int GOLD_CARRIED = 36;
		public static final int STAT_POINTS_AVAIL = 37;
		public static final int ABILITY_POINTS_AVAIL = 38;
		public static final int WALK_MOVES_COUNT = 39;
		//public static final int ACTION_POINTS_COUNT = 39;//this is useless because of energy
		public static final int ALIGNMENT = 40;
		//public static final int PROFILEIMAGE_ID = 40;
		public static final int MODEL_ID = 41;//to draw
		public static final int FACING = 42;
		public static final int UNITDATA_NPCTYPE = 43;
		public static final int UNITDATA_TURNSLEFT = 44;
		public static final int UNITDATA_HARDCOREMODE = 45;
		public static final int INVISIBLE = 46;
		
		
		
	
	public static final int NUM_OF_UNITSTATS = 50;//can safely have up to 200 until TCP packet size is dangerously large for dialup people!

	
public static final String mainmenuhints[] = {
	"Each game session requires at least one host.  Heroes can connect to a host's active session.",
	"A host can save the current campaign and all connected heroes in the 'ESC Menu'.",
	"Heroes can spend gold on items by clicking the gold piece icon in their Inventory Menu.",
	
};
	
	

	
	
	//Flags can be chosen to affect things like stats.
	//However, they are not stored! They are calculated just in time.
	public static final int FLAG_ALLIESNEARBY = 101;
	public static final int FLAG_ENEMIESNEARBY = 102;
	public static final int FLAG_NIGHTTIME = 103;
	public static final int FLAG_ABILITYLEVEL = 104;
	public static final int FLAG_OPPOSITEFACING = 105;
//flag facingaway
	
	
	/*public static final String StatDescriptions[] = {//finish me !
		"none",
		"The level of your Hero",
		"Experience towards gaining a new level",	
		"Current Health",	
		"Amount of Health gained per turn",	
		"Current Energy for using abilities",	
		"Amount of Energy gained per turn",	
		"Amount of Energy gained per turn",	
	};*/
	
	/*public static final String PerkDescriptions[] = {
		"Scaling walls and falling gracefully feels like second nature.",
		"Mixing chemicals and inventing gadgets has never been so awesome!",
		"Beings of all sizes will bow down to your blind rage and fury.",	
		"Only the keenest senses can detect a master of stealth.",	
		"The world is filled with treasure, if you just know where to find it! ",	
		"Sometimes, words are the most powerful influence.",	
		"Mystical weapons gravitate towards your inner power.",	
		"If only every being shared a common language.",	
		"Animals love you and will become your adventurous companion.",	
		"Eating carrots as a child improves vision in the darkness.",	
		"Your ears may stick out a bit more, but they can hear falling leaves.",	
		"In your hands, a bobby pin becomes the toolbox of a locksmith.",	
		"The finest armors and weapons have been forged by your hand.",	
		"To you, riddles and puzzles are simply a way of life.",	
	};*/
	

	public static final String BaseStatDescriptions[] = {
		"Endurance increases your maximum health.",
		"Stamina increases your energy for abilities.",
		"Agility increases your movement speed.",	
		"Perception improves your sight in the dark.",	
		"Strength improves melee weapon damage. ",	
		"Dexterity improves ranged weapon damage.",	
		"Intelligence improves magic weapon damage.",	
		"Wisdom increases the potency of certain spells.",	
	};
	
	
	
	//public static final int WEAPONTYPE_NONE = 0;
	public static final int WEAPONTYPE_DAGGER = 1;
	public static final int WEAPONTYPE_SHORTSWORD = 2;
	public static final int WEAPONTYPE_LONGSWORD = 3;
	public static final int WEAPONTYPE_SHIELD = 4;
	public static final int WEAPONTYPE_BATTLEAXE = 5;
	public static final int WEAPONTYPE_SPEAR = 6;
	public static final int WEAPONTYPE_POLEARM = 7;
	public static final int WEAPONTYPE_BOW = 8;
	public static final int WEAPONTYPE_FIREARM = 9;
	public static final int WEAPONTYPE_ROCKETLAUNCHER = 10;
	public static final int WEAPONTYPE_GRENADELAUNCHER = 11;
	public static final int WEAPONTYPE_ENERGYBLADE = 12;
	public static final int WEAPONTYPE_ENERGYRIFLE = 13;
	public static final int WEAPONTYPE_STAFF = 14;
	public static final int WEAPONTYPE_SCEPTER = 15;
	public static final int WEAPONTYPE_TOTEM = 16;
	public static final int WEAPONTYPE_HAMMER = 17;
	public static final int WEAPONTYPE_ORB = 18;
	public static final int WEAPONTYPE_FISTWEAPON = 19;
	
	
	
	
	//object assignment data.
	public static final int NOTHING = 0;
	public static final int LIGHTGRASS = 1;
	public static final int DARKGRASS = 2;
	public static final int LIGHTDIRT = 3;
	public static final int DARKDIRT = 4;
	public static final int SAND = 5;
	public static final int SANDISLAND = 6;
	public static final int WATER = 7;
	public static final int LAVA = 8;
	public static final int BLACKROCK = 9;
	public static final int PIT = 10;
	public static final int FARMSOIL = 11;
	public static final int SNOW = 12;
	public static final int COBBLESTONE = 13;
	
	//Smallobjects from 100 to 299		
	public static final int PATHING_BLOCKER_LOW = 100;
	public static final int PATHING_BLOCKER_HIGH = 101;
	public static final int LADDER = 102;
	public static final int ASHES = 103;
	public static final int MUSHROOMS_1 = 104;
	public static final int MUSHROOMS_2 = 105;
	public static final int MUSHROOMS_3 = 106;
	public static final int CABBAGE = 107;
	public static final int CERAMICPOT = 108;
	public static final int PUMPKINS = 109;
	public static final int BONECORPSE = 110;
	public static final int WOODBUCKET = 111;
	public static final int GRAINBAG = 112;
	public static final int PRESSUREPLATE_UP = 113;
	public static final int PRESSUREPLATE_DOWN = 114;
	public static final int SMALLROCKS_1 = 115;
	public static final int SMALLROCKS_2 = 116;
	public static final int SMALLROCKS_3 = 117;
	public static final int SMALLROCKS_4 = 118;
	public static final int DIRTHOLE = 119;
	public static final int GRASSPATCHTHIN = 120;
	public static final int GRASSPATCHTHICK = 122;
	public static final int HEROSPAWN = 123;
	public static final int TEMPLESTAIRS_DOWN_1 = 133;
	public static final int TEMPLESTAIRS_DOWN_2 = 134;

	
	public static final int SMALLCLIFF_H1 = 200;
	public static final int SMALLCLIFF_H2 = 201;
	public static final int SMALLCLIFF_V1 = 202;
	public static final int SMALLCLIFF_V2 = 203;
	public static final int SMALLCLIFF_C1 = 204;
	public static final int SMALLCLIFF_C2 = 205;
	public static final int SMALLCLIFF_C3 = 206;
	public static final int SMALLCLIFF_C4 = 207;
	
	//NPCs from 300 to 399	
	// hero markers and sprites 
	public static final int HERO_2 = 300;
	public static final int HERO_3 = 301;
	public static final int HERO_4 = 302;
	public static final int HERO_5 = 303;
	public static final int HERO_6 = 304;
	public static final int HERO_7 = 305;
	public static final int HERO_8 = 306;
	// representations of player Units

	
	
	
	
	//THESE ARE JUST MODELS NOW!
	/*public static final int IMP = 350;
	public static final int SKELETON = 351;
	public static final int CUBE = 352;
	public static final int CUBE_LARGE = 353;
	public static final int SPECTRE = 354;
	public static final int FIRE_SPIRIT = 355;
	public static final int WASP = 356;
	public static final int SNAKE = 357;
	public static final int BAT = 358;
	public static final int EYEMONSTER = 359;
	public static final int SKELETALSPEARMAN = 360;
	public static final int TURTLE = 361;
	public static final int GOBLINBRUISER = 362;
	public static final int EVILWIZARD = 363;
	public static final int BLACKSPIDER = 364;
	public static final int TOWN_GUARD_1 = 365;
	public static final int VILLAGER_1 = 366;
	public static final int VILLAGER_2 = 367;
	public static final int VILLAGER_3 = 368;*/

	
	//large objects from 400 to 599	
	
	
	public static final int LIGHTRAYS = 400;
	
	//largeobjects50sheet
	public static final int DOOR_H_OPEN = 402;
	public static final int DOOR_H_CLOSED = 403;
	public static final int TABLE_1 = 404;
	public static final int TABLE_2 = 405;
	public static final int TABLE_3 = 406;
	public static final int LOGPILE = 407;
	public static final int ANVIL1 = 408;
	public static final int ANVIL2 = 409;
	public static final int ANVIL3 = 410;
	public static final int PILLORY = 411;
	public static final int WOODSIGN = 412;
	public static final int TOMEPODIUM = 413;
	public static final int WOODBARREL = 414;
	public static final int WOODCRATE = 415;
	public static final int FRUITSTAND_1 = 416;
	public static final int FRUITSTAND_2 = 417;
	public static final int FRUITSTAND_3 = 418;
	public static final int FLOURBAGS = 419;
	public static final int ARMORSTAND = 420;
	public static final int TREASURECHEST = 421;
	public static final int TREASURECHEST_2 = 422;
	public static final int WOOD_CHEST = 423;
	public static final int MAGICCHEST_WHITE = 424;
	public static final int MAGICCHEST_RED = 425;
	public static final int MAGICCHEST_GREEN = 426;
	public static final int MAGICCHEST_BLUE= 427;
	public static final int MAGICCHEST_BLACK = 428;
	public static final int DEMONCANDLE = 429;
	public static final int PLIERS = 430;
	public static final int HAMMERS = 431;
	public static final int BUSH_1 = 432;
	public static final int BUSH_2 = 433;
	public static final int PLANT_1 = 434;
	public static final int PLANT_2 = 435;
	public static final int PLANT_3 = 436;
	public static final int PLANT_4 = 437;
	public static final int PLANT_5 = 438;
	public static final int PLANT_6 = 439;
	public static final int PLANT_7 = 440;
	public static final int PLANT_8 = 441;
	public static final int TORCH = 442;
	public static final int THINFOUNTAIN = 443;
	public static final int BROWNROCKS1 = 444;
	public static final int BROWNROCKS2 = 445;
	public static final int MOVEABLEBLOCK = 446;
	public static final int HIDDENBLOCK = 447;
	public static final int LARGESTUMP = 448;
	public static final int LARGEBROWNMUSHROOM = 449;
	public static final int CATTAIL = 450;
	public static final int LARGEREDMUSHROOM = 451;
	public static final int BASICCHEST1 = 452;
	public static final int BASICCHEST2 = 453;
	public static final int SMALLSTUMP = 454;
	public static final int GRAYROCK1 = 455;	
	public static final int GRAYROCK2 = 456;
	public static final int STALAGMITE1 = 457;
	public static final int STALAGMITE2 = 458;
	public static final int STALAGMITE3 = 459;
	public static final int JUNGLEFERN = 459;
	public static final int WOODENSTOOL = 459;
	public static final int PUMPKIN = 459;
	public static final int IRONBARS = 459;
	public static final int NEWANVIL = 459;
	public static final int FERN_1 = 459;
	public static final int FERN_2 = 459;
	
	//largeobjects100sheet
	public static final int TANROCKS1 = 500;//horiz
	public static final int TANROCKS2 = 501;
	public static final int TANROCKS3 = 502;
	public static final int BLACKROCKS1 = 503;
	public static final int BLACKROCKS2 = 504;//corner
	public static final int BLACKROCKS3 = 505;
	//public static final int BLACKROCKS4 = 506;
	public static final int BLACKROCKS5 = 507;
	public static final int BLACKROCKS6 = 508;
	public static final int WOODBRIDGE_H = 509;
	public static final int WHITETROPHY = 510;
	public static final int ROUNDPILLAR_1 = 511;
	public static final int ROUNDPILLAR_2 = 512;//vertical
	public static final int ROUNDPILLAR_3 = 513;
	public static final int ROUNDPILLAR_4 = 514;
	public static final int ROUNDPILLAR_5 = 515;
	public static final int FURNITURE_1 = 516;
	public static final int WELL_EMPTY = 517;
	public static final int WELL_RED = 518;
	public static final int WELL_BLUE = 519;
	public static final int BED_VERTICAL = 520;
	public static final int BED_HORIZONTAL = 521;
	public static final int GRAVE_1 = 522;
	public static final int GRAVE_2 = 523;
	public static final int GRAVE_3 = 524;
	public static final int GRAVE_4 = 525;
	public static final int PILLAR_1 = 526;
	public static final int LARGEBUSH = 527;
	public static final int STREETLAMP = 528;
	public static final int STOVE = 529;
	public static final int STATUE_WARRIOR = 530;
	public static final int STATUE_MAGE = 531;
	public static final int ROCKFENCE_V1 = 532;
	public static final int ROCKFENCE_H1 = 533;
	public static final int ROCKFENCE_E1 = 534;
	public static final int ROCKFENCE_E2 = 535;
	public static final int ROCK_1 = 536;
	public static final int ROCK_2 = 537;
	public static final int THINTREE = 538;
	public static final int BIGLADDER1 = 539;
	public static final int BIGLADDER2 = 540;
	public static final int BIGLADDER3 = 541;
	public static final int DUNGEONSTAIRS_DOWN_1 = 542;
	public static final int DUNGEONSTAIRS_DOWN_2 = 543;
	public static final int DUNGEONSTAIRS_UP_1 = 544;
	public static final int DUNGEONSTAIRS_UP_2 = 545;
	public static final int TANFOUNTAIN = 546;
	public static final int PLANTPEDESTAL = 547;
	public static final int SCARECROW = 548;
	public static final int TANBIRDBATH = 549;
	public static final int ORNATESTATUE = 550;
	public static final int TREELAMPS = 551;
	public static final int TANPEDESTAL = 552;
	public static final int WOODENTABLE = 553;
	public static final int WOODENBENCH = 554;
	public static final int SIGNALFIRE = 555;
	
	
	public static final int BOOKSHELF = 600;
	public static final int TREE_1 = 601;
	public static final int TREE_2 = 602;
	public static final int TREE_3 = 603;
	public static final int TREE_4 = 604;
	public static final int TREE_5 = 605;
	public static final int TREE_6 = 606;
	public static final int WELL_LARGE = 607;
	public static final int TENT_1 = 608;
	public static final int TENT_2 = 609;
	public static final int TENT_3 = 610;
	public static final int TENT_4 = 611;
	public static final int TENT_5 = 612;
	public static final int TENT_6 = 613;
	public static final int TENT_7 = 614;
	public static final int TENT_8 = 615;
	public static final int TENT_9 = 616;
	public static final int TENT_10 = 617;
	public static final int TENT_11 = 618;
	public static final int TENT_12 = 619;

	public static final int WOODWALL_C1 = 700;
	public static final int WOODWALL_C2 = 701;
	public static final int WOODWALL_C3 = 702;
	public static final int WOODWALL_C4 = 703;
	public static final int WOODWALL_H1 = 704;
	public static final int WOODWALL_H2 = 705;
	public static final int WOODWALL_H3 = 706;
	public static final int WOODWALL_H4 = 707;
	public static final int WOODWALL_V1 = 708;
	public static final int WOODWALL_V2 = 709;
	public static final int WOODWALL_V3 = 710;
	public static final int WOODWALL_V4 = 711;
	public static final int WOODWALL_E1 = 712;
	public static final int WOODWALL_E2 = 713;
	public static final int WOODWALL_E3 = 714;
	public static final int WOODWALL_E4 = 715;
	
	public static final int CLIFF_H1 = 720;
	public static final int CLIFF_H2 = 721;
	public static final int CLIFF_H3 = 722;
	public static final int CLIFF_H4 = 723;
	public static final int CLIFF_V1 = 724;
	public static final int CLIFF_V2 = 725;
	public static final int CLIFF_V3 = 726;
	public static final int CLIFF_V4 = 727;
	public static final int CLIFF_C1 = 728;
	public static final int CLIFF_C2 = 729;
	public static final int CLIFF_C3 = 730;
	public static final int CLIFF_C4 = 731;
	public static final int CLIFF_C5 = 732;
	public static final int CLIFF_C6 = 733;
	public static final int CLIFF_C7 = 734;
	public static final int CLIFF_C8 = 735;


	//regions
	public static final int REGION_INDOORS = 800;
	public static final int REGION_DUNGEON = 801;
	//public static final int REGION_INDOORS = 700;
	
	//markers from 650-699
	public static final int YELLOW_NOTE = 900;
	public static final int RED_NOTE = 901;
	public static final int BLUE_NOTE = 902;
	public static final int GREEN_NOTE = 903;
	
	public static final int NPCMARKER_1 = 904;
	public static final int NPCMARKER_2 = 905;
	public static final int NPCMARKER_3 = 906;
	public static final int NPCMARKER_4 = 907;
	public static final int NPCMARKER_5 = 908;
	
	
	

	
	//END OBJECT DECLARATIONS
	
	
	
	//UNIT MODELS
	
	
	public static final int UNITMODEL_MALEHUMAN = 1;
	public static final int UNITMODEL_FEMALEHUMAN = 2;
	public static final int UNITMODEL_SKELEBODY = 3;

	public static final int UNITMODEL_ISOZOMBIE =4;
	public static final int UNITMODEL_ISOSKELETON =5;
	public static final int UNITMODEL_ISOGOBLIN =6;
	public static final int UNITMODEL_ISOMINOTAUR =7;
	//public static final int UNITMODEL_ISOGIANT =8;
	//public static final int UNITMODEL_ISOWIZARD =9;
	
	public static final int UNITMODEL_ZOMBIE1 =8;
	public static final int UNITMODEL_ZOMBIE2 =9;
	public static final int UNITMODEL_SKELETON1 =10;
	public static final int UNITMODEL_SKELETON2 =11;
	public static final int UNITMODEL_BAT =12;
	public static final int UNITMODEL_BLACKSPIDER =13;
	public static final int UNITMODEL_WIZARD =14;
	public static final int UNITMODEL_EYEMONSTER =15;
	public static final int UNITMODEL_GOBLINBRUISER =16;
	public static final int UNITMODEL_SKELETALSPEARMAN =17;
	public static final int UNITMODEL_SNAKE =18;
	public static final int UNITMODEL_TURTLE =19;
	public static final int UNITMODEL_GORG =20;
	public static final int UNITMODEL_GIANTALIEN =21;
	public static final int UNITMODEL_SHROOMTREANT =22;
	public static final int UNITMODEL_DOOMRAVEN =23;
	public static final int UNITMODEL_BIRDMAN =24;
	
	///ITEM MODELS
	
	public static final int ITEMMODEL_HAIR =1;
	public static final int ITEMMODEL_CLOTHHEAD =2;
	public static final int ITEMMODEL_LEATHERHEAD =3;
	public static final int ITEMMODEL_CHAINHEAD =4;
	public static final int ITEMMODEL_METALHEAD =5;
	public static final int ITEMMODEL_PLATEHEAD =6;
	public static final int ITEMMODEL_LEATHERSHOULDER =7;
	public static final int ITEMMODEL_PLATESHOULDER =8;
	public static final int ITEMMODEL_SHIRTBROWN =9;
	public static final int ITEMMODEL_SHIRTWHITE =10;
	public static final int ITEMMODEL_LEATHERCHEST =11;
	public static final int ITEMMODEL_CHAINCHEST =12;
	public static final int ITEMMODEL_PLATECHEST =13;
	public static final int ITEMMODEL_DEMONCHEST =14;	
	public static final int ITEMMODEL_LEATHERGLOVES =15;
	public static final int ITEMMODEL_METALGLOVES =16;	
	public static final int ITEMMODEL_CLOTHBELT =17;
	public static final int ITEMMODEL_LEATHERBELT =18;
	public static final int ITEMMODEL_CLOTHLEGS =19;
	public static final int ITEMMODEL_LEATHERLEGS =20;
	public static final int ITEMMODEL_METALLEGS =21;
	public static final int ITEMMODEL_LEATHERSHOES =22;
	public static final int ITEMMODEL_METALSHOES =23;
	public static final int ITEMMODEL_SHIELD =24;
	public static final int ITEMMODEL_SPEAR =25;
	public static final int ITEMMODEL_DAGGER =26;
	public static final int ITEMMODEL_BOW =27;
	public static final int ITEMMODEL_PISTOL =28;
	public static final int ITEMMODEL_SHOTGUN =29;
	public static final int ITEMMODEL_ASSAULTRIFLE =30;
	public static final int ITEMMODEL_SHORTSWORD =31;
	public static final int ITEMMODEL_RAPIER =32;
	public static final int ITEMMODEL_LONGSWORD =33;
	public static final int ITEMMODEL_DRESS =34;
	public static final int ITEMMODEL_BLOUSE =35;
	public static final int ITEMMODEL_SKIRT =36;
	public static final int ITEMMODEL_LEATHERCHESTFEMALE =37;
	public static final int ITEMMODEL_LEATHERGLOVESFEMALE =38;
	public static final int ITEMMODEL_LEATHERSHOULDERFEMALE =39;
	

	
	public static final int[] COLLIDABLE_OBJECTS_FLOOR = new int[] {WATER, LAVA, PIT, PILLAR_1,
		TABLE_1, TABLE_2, TABLE_3, TANROCKS1, TANROCKS2, TANROCKS3, BLACKROCKS1, THINTREE,
		BLACKROCKS2, BLACKROCKS3, SMALLROCKS_3, BLACKROCKS5, BLACKROCKS6, WOODBRIDGE_H, WHITETROPHY,
		ROUNDPILLAR_1, ROUNDPILLAR_2, ROUNDPILLAR_3, ROUNDPILLAR_4, ROUNDPILLAR_5, DOOR_H_CLOSED, TORCH,
		HERO_2, HERO_3, HERO_4, HERO_5, HERO_6, HERO_7, HERO_8, WOODWALL_C1, WOODWALL_C2, WOODWALL_C3, WOODWALL_C4, WOODWALL_H1, WOODWALL_H2, WOODWALL_V1,WOODWALL_V2,WOODWALL_V3,WOODWALL_V4,
		CLIFF_C1,CLIFF_C2,CLIFF_C3,CLIFF_C4,CLIFF_C5,CLIFF_C6,CLIFF_C7,CLIFF_C8,CLIFF_H1,CLIFF_H2,CLIFF_H3,CLIFF_H4,CLIFF_V1,CLIFF_V2,CLIFF_V3,CLIFF_V4, 
		TREE_1,TREE_2,TREE_3,TREE_4,TREE_5,TREE_6,ROCK_1,ROCK_2,TREASURECHEST,TREASURECHEST_2,
		ROCKFENCE_E1,ROCKFENCE_E2,ROCKFENCE_H1,ROCKFENCE_V1,GRAVE_1,GRAVE_2,GRAVE_3,GRAVE_4,PATHING_BLOCKER_LOW, ANVIL1, ANVIL2, ANVIL3,
		BED_HORIZONTAL, BED_VERTICAL, BONECORPSE, BUSH_1, BUSH_2, CABBAGE, CERAMICPOT, WOOD_CHEST,WELL_EMPTY,WELL_RED,WELL_BLUE,FLOURBAGS,FRUITSTAND_1,FRUITSTAND_2,
		FRUITSTAND_3,GRAINBAG,MUSHROOMS_1,MUSHROOMS_2,MUSHROOMS_3,PILLORY,PUMPKINS,TOMEPODIUM,WOODBARREL,WOODBUCKET,WOODSIGN,HEROSPAWN,MAGICCHEST_BLACK, MAGICCHEST_WHITE,
		MAGICCHEST_RED, MAGICCHEST_GREEN, MAGICCHEST_BLUE, LOGPILE,TEMPLESTAIRS_DOWN_1,TEMPLESTAIRS_DOWN_2,DUNGEONSTAIRS_DOWN_1,DUNGEONSTAIRS_DOWN_2,DUNGEONSTAIRS_UP_1,DUNGEONSTAIRS_UP_2,
		ARMORSTAND,TREASURECHEST,TREASURECHEST_2,DEMONCANDLE,PLIERS,HAMMERS,PLANT_1,PLANT_2,PLANT_3,PLANT_4,PLANT_5,PLANT_6,PLANT_7,PLANT_8,THINFOUNTAIN,BROWNROCKS1,BROWNROCKS2,MOVEABLEBLOCK,
		LARGESTUMP,CATTAIL,LARGEREDMUSHROOM,BASICCHEST1,BASICCHEST2,SMALLSTUMP,GRAYROCK1,GRAYROCK2,STALAGMITE1,STALAGMITE2,STALAGMITE3,JUNGLEFERN,PUMPKIN,IRONBARS,NEWANVIL,FERN_1,FERN_2,
		TANROCKS1,TANROCKS2,TANROCKS3,BLACKROCKS1,BLACKROCKS2,BLACKROCKS3,BLACKROCKS5,BLACKROCKS6,ROCK_1,ROCK_2,THINTREE,BIGLADDER1,BIGLADDER2,BIGLADDER3,TANFOUNTAIN,PLANTPEDESTAL,
		SCARECROW,TANBIRDBATH,ORNATESTATUE,TREELAMPS,TANPEDESTAL,WOODENTABLE,WOODENBENCH,SIGNALFIRE,TENT_1,TENT_2,TENT_3,TENT_4,TENT_5,TENT_6,TENT_7,TENT_8,TENT_9,TENT_10,TENT_11,TENT_12
	}; 

	

	
	
public static final int[] COLLIDABLE_OBJECTS_HOVER = new int[] { PILLAR_1, TANROCKS1, TANROCKS2, TANROCKS3, BLACKROCKS1, THINTREE,
	BLACKROCKS2, BLACKROCKS3, BLACKROCKS5, BLACKROCKS6, WOODBRIDGE_H, WHITETROPHY,
	ROUNDPILLAR_1, ROUNDPILLAR_2, ROUNDPILLAR_3, ROUNDPILLAR_4, ROUNDPILLAR_5, DOOR_H_CLOSED,TREE_1,TREE_2,TREE_3,TREE_4,TREE_5,TREE_6,
	WOODWALL_C1, WOODWALL_C2, WOODWALL_C3, WOODWALL_C4, WOODWALL_H1, WOODWALL_H2, WOODWALL_V1,WOODWALL_V2,WOODWALL_V3,WOODWALL_V4,PATHING_BLOCKER_HIGH,
	ARMORSTAND, BOOKSHELF, LARGEBUSH, FURNITURE_1,STATUE_WARRIOR,STATUE_MAGE,STOVE,STREETLAMP,WELL_LARGE,DUNGEONSTAIRS_UP_1,DUNGEONSTAIRS_UP_2   };


//need to keep this updated!
	public static final int[] LIGHTBLOCKING_OBJECTS = new int[] { PILLAR_1,
		TANROCKS1, TANROCKS2, TANROCKS3, BLACKROCKS1,BLACKROCKS2, BLACKROCKS3, BLACKROCKS5, BLACKROCKS6,
		WOODBRIDGE_H, WHITETROPHY, ROUNDPILLAR_1, ROUNDPILLAR_2, ROUNDPILLAR_3, ROUNDPILLAR_4, ROUNDPILLAR_5, DOOR_H_CLOSED, 
		WOODWALL_C1, WOODWALL_C2, WOODWALL_C3, WOODWALL_C4, WOODWALL_H1, WOODWALL_H2, WOODWALL_H3, WOODWALL_H4, 
			WOODWALL_V1,WOODWALL_V2,WOODWALL_V3,WOODWALL_V4,PATHING_BLOCKER_HIGH,
			DUNGEONSTAIRS_UP_1,DUNGEONSTAIRS_UP_2  };
	
	
	
	public static final int[] imageOffset_x = new int[NUMBER_OF_TYPES]; 
	public static final int[] imageOffset_y = new int[NUMBER_OF_TYPES];
	
	
	
	public static final int ALIGN_LAWFULGOOD = 1;
	public static final int ALIGN_NEUTRALGOOD = 2;
	public static final int ALIGN_CHAOTICGOOD = 3;
	public static final int ALIGN_LAWFULNEUTRAL = 4;
	public static final int ALIGN_NEUTRAL = 5;
	public static final int ALIGN_CHAOTICNEUTRAL = 6;
	public static final int ALIGN_LAWFULEVIL = 7;
	public static final int ALIGN_NEUTRALEVIL = 8;
	public static final int ALIGN_CHAOTICEVIL = 9;
	
	public static final int ITEMSLOT_HEAD = 50;//take these out of core and just use these ones!
	public static final int ITEMSLOT_NECK = 51;
	public static final int ITEMSLOT_TORSO = 52;
	public static final int ITEMSLOT_LEGS = 53;
	public static final int ITEMSLOT_FEET = 54;
	public static final int ITEMSLOT_HANDS = 55;
	public static final int ITEMSLOT_RING = 56;
	public static final int ITEMSLOT_MAINHAND = 57;
	public static final int ITEMSLOT_OFFHAND = 58;
	//public static final int ITEMSLOT_CONSUMABLE = 59;
	//public static final int ITEMSLOT_BOTHHANDS = 60;// not implemented yet.
	//public static final int ITEMSLOT_EITHERHAND = 61;//removed
	
	
	
	public static final int ITEMTYPE_UNASSIGNED = 0;
	public static final int ITEMTYPE_PLAYERDEFAULTS = 1;
	public static final int ITEMTYPE_NPCITEMS = 2;
	public static final int ITEMTYPE_CONSUMABLES = 3;
	public static final int ITEMTYPE_BASICWEAPONS = 4;
	public static final int ITEMTYPE_BASICARMOR = 5;
	public static final int ITEMTYPE_SIMPLEMATERIALS = 6;
	
	/*"Unassigned", "Player Defaults", "NPC Items", "Consumables", "Basic Weapons", "Basic Armor", "Simple Materials", "Advanced Materials","Magical Weapons", "Magical Armor",
	"Ultimate Weapons", "Ultimate Armor", "Craftable","Magic Trinkets", "Quest Items", "Misc"},
*/
	
	
public static final int[][] ItemDrawOrder_Under = {//stuff at end draws on top
		
		//facing 0
		{},
		
		//facing 1
		{},
			
		//facing 2
		{},
				
		//facing 3
		{},
	
		
	};

	public static final int[][] ItemDrawOrder_OnTop = {//stuff at end draws on top
		
		//facing 0
		{ITEMSLOT_NECK,ITEMSLOT_RING,ITEMSLOT_MAINHAND,ITEMSLOT_OFFHAND,ITEMSLOT_HANDS,ITEMSLOT_LEGS,ITEMSLOT_FEET,ITEMSLOT_HEAD,ITEMSLOT_TORSO},
		
		//facing 1
		{ITEMSLOT_LEGS,ITEMSLOT_FEET,ITEMSLOT_TORSO,ITEMSLOT_NECK,ITEMSLOT_HEAD,
			ITEMSLOT_HANDS,ITEMSLOT_RING,ITEMSLOT_MAINHAND,ITEMSLOT_OFFHAND},
			
		//facing 2
		{ITEMSLOT_LEGS,ITEMSLOT_FEET,ITEMSLOT_TORSO,ITEMSLOT_NECK,ITEMSLOT_HEAD,
			ITEMSLOT_HANDS,ITEMSLOT_RING,ITEMSLOT_MAINHAND,ITEMSLOT_OFFHAND},
				
		//facing 3
		{ITEMSLOT_LEGS,ITEMSLOT_FEET,ITEMSLOT_TORSO,ITEMSLOT_NECK,ITEMSLOT_HEAD,
			ITEMSLOT_HANDS,ITEMSLOT_RING,ITEMSLOT_MAINHAND,ITEMSLOT_OFFHAND},
	
	
	
	
	};
	//may have to make this depend on facing!!! We shall see!
	

	
	//init settings data
	
	
	
	
	
	
	//begin asseteditor data

	public final int NUMBER_OF_ASSETGROUPS = 5;
	public final static int MAX_NUM_ASSETS = 1000;
	public final static int MAX_NUM_PARAMETERS = 100;
	
	public static final int ASSETGROUP_ABILITIES = 0;
	public static final int ASSETGROUP_EFFECTS = 1;
	public static final int ASSETGROUP_CONDITIONS = 2;
	public static final int ASSETGROUP_ITEMS = 3;
	public static final int ASSETGROUP_UNITS = 4;
	
	public  final int PARATYPE_BOOLEAN = 0;
	public final int PARATYPE_NUMERIC = 1;
	public final int PARATYPE_NUMERIC_PERCENT = 2;
	public  final int PARATYPE_ALPHANUMERIC = 3;
	public  final int PARATYPE_ALPHANUMERICHUGE = 4;
	public  final int PARATYPE_COMBOTEXT = 5;
	public final int PARATYPE_COMBOICONS = 6;
	
	public final int COMBOICONS_ABILITYICONS = 0;//set of icons
	public final int COMBOICONS_ITEMICONS = 1;//set of icons
	public final int COMBOICONS_CONDITIONICONS =2;
	
	public static final int COMBOTEXT_ABILITYSPECS = 0;//set of strings
	public static final int COMBOTEXT_WEAPONTYPES = 1;
	public static final int COMBOTEXT_STATS = 2;
	 public static final int COMBOTEXT_EQUIPSLOTS = 3;
	 public static final int COMBOTEXT_LOOTGROUPS = 4;
	public static final int COMBOTEXT_EFFECTTYPES = 5;
	public static final int COMBOTEXT_SHAPES = 6;
	public static final int COMBOTEXT_MOVEMENTANIMATIONS = 7;
	public static final int COMBOTEXT_PROJECTILES = 8;
	public static final int COMBOTEXT_GFXIDS = 9;
	public static final int COMBOTEXT_SOUNDIDS = 10;
	public static final int COMBOTEXT_UNITCATEGORIES = 11;
	public static final int COMBOTEXT_ALIGNMENT = 12;
	public static final int COMBOTEXT_UNITMODELS = 13;
	 public static final int COMBOTEXT_EQUIPMODELS = 14;

		public static final int COMBOTEXT_MYABILITIES = 20;
		public static final int COMBOTEXT_MYEFFECTS = 21;
		public static final int COMBOTEXT_MYCONDITIONS = 22;
		public static final int COMBOTEXT_MYITEMS = 23;
		public static final int COMBOTEXT_MYUNITS = 24;

		static String[][] ComboStrings = {
				//abilityspecs
				{"None", "Brawn", "Protection", "Nature", "Darkness", "Alchemy", "Precision", "Explosives", "Mechatronics",
					"Wizardry", "Sorcery", "Weapon Skill"},
				//weapon types
				{"None", "Dagger", "Shortsword", "Longsword", "Shield", "Axe", "Spear", "Bow", "Pistol",
						"Rifle", "Rocket Launcher", "Grenade Launcher", "Energy Blade", "Energy Rifle", "Staff", "Scepter", "Totem", "Hammer", "Orb",
						"Fist Weapon", "Custom Type 1", "Custom Type 2", "Custom Type 3", "Custom Type 4", "Custom Type 5", "Custom Type 6", "Custom Type 7",
						"Custom Type 8", "Custom Type 9", "Custom Type 10"},
				//stats
				{"None", "Level", "Experience", "Health", "Health Regen", "Energy","Starting Energy", "Energy Growth", "Energy Max",
							"Melee Power", "Ranged Power", "Spell Power", "Armor","Magic Resist","Toxin Resist","Initiative","Speed",
							"Endurance", "Stamina", "Agility", "Perception", "Strength", "Dexterity", "Intelligence",  "Wisdom", "Primary Spec", "Secondary Spec",
							"Dead","Disarmed", "Immobile", "Silenced", "Exp Reward", "Player Ownership", "Gold Carried", 
							"Walk Moves Count","Alignment","Model ID","Facing","NPC Type","Time Left","Hardcore","Invisible",
							/*"Acrobat Perk","Scientist Perk","Ruthless Perk","Sneaky Perk","Finding Perk","Persuasion Perk",
							"Enchanter Perk","Communication Perk","Animal Friend Perk","Nightvision Perk","Hearing Perk",
							"Lockpick Perk","Crafting Perk","Cryptography Perk", */
							"Num Allies Near", "Num Enemies Near", "Night Time","Ability Level","Opposite Facing"},
				//equipslots
				{"None", "Head", "Shoulder", "Torso", "Legs", "Feet", "Hands", "Finger", "Main Hand", "Off Hand"
									 /*"Consumable" ,"Both Hands Held", "Either Hand Held"*/},
				//item types
				{"Unassigned", "Player Defaults", "NPC Items", "Consumables", "Basic Weapons", "Basic Armor", "Simple Materials", "Advanced Materials","Magical Weapons", "Magical Armor",
										"Ultimate Weapons", "Ultimate Armor", "Craftable","Magic Trinkets", "Quest Items", "Misc"},
				//effect types
				{"Move Unit", "Edit Stat", "Apply Condition", "Draw Projectile", "Create Unit", "Draw Overlay Effect",
						"Play Sound"},
				//shapes
			    {"Point", "Line", "Cone", "Square"},
			    //move anims
			    {"None", "SlideTurn", "Walk", "Cast", "Thrust/Shoot Firearm", "Swing", "Shoot Bow", "Death"},
			    //proj
			    {"Fireball", "Arrow", "Javelin","Icicle","Lightning","Bolas","Boulder","Net"},
			     //GFX Ids
			    {"None", "Flames", "Ice Sphere", "Ice Tentacles", "Shock", "Green Tentacle", "Tornado", "Circular Explosion", "Green Shield","Mushroom Explosion","Rain"},
			  //Sound Ids
			    {"None", "Coin", "Burn", "Shoot Arrow", "Dice Roll", "Evil Spell 1", "Screech 1", "Grass Step 1",
					"Grass Step 2", "Grass Step Random", "Hiss", "Ghosthit", "Energy Glow", "Energy Rifle",
					"Energy Shield Bounce","Fireball","Glow Up","Laser Blaster","Laser Hit","Melee Gore",
					"Melee Smack","Melee Thud","Melee Thwack","Phase Out","Plasma","Power Down","Power Online","Rifle Fire",
					"System Beeping","Wood Explode","Gore Explode 1","Gore Explode 2","Gore Explode 3","Gore Explode 1"
					,"Drum Low","Cosmic Amulet","Leather Item","Pistol Fire 1","Pistol Fire 2","Shotgun Fire 1","Shotgun Fire 2"
					,"Punch","Human Groan 1","Human Groan 2","Human Groan 3","Human Groan 4","Zombie Moan 1","Zombie Moan 2"
					,"Munch","Beast Moan 1","Beast Moan 2","Ticking","Swish 1","Swish 2","Swish 3","Swish 4","Monster Grunt 1"
					,"Monster Grunt 2","Monster Grunt 3","Bubble","Bottle"},
				//NPC types
				{ "Human", "Critter", "Beast", "Goblin","Dragonkin","Unholy","Angelic","Extraterrestrial","Inanimate"},
				//Alignments
				{"None", "Lawful Good", "Neutral Good", "Chaotic Good","Lawful Neutral", "Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil"},
				//Unit Models
				{"None", "Human Male", "Human Female", "Skeletal Human", "Zombie", "Skeletal Swordsman","Goblin Lancer", "Minotaur", "Tiny Zombie", "Zombie Warrior", "Tiny Skeleton", "Skeleton Lord", 
					"Bat","Spider","Wizard", "Eye Beast", "Goblin Bruiser", "Skeletal Dervish", "Snake", "Turtle",
					"Gorg","Giant Alien","Shroom Treant","Doom Raven","Birdman"},
				//equip models
					{"None", "Hair", "Cloth Head", "Leather Head", "Chain Head", "Metal Head", "Plate Head", "Leather Shoulders", "Metal Shoulders"
					, "Cloth Chest 1", "Cloth Chest 2", "Leather Chest", "Chain Chest", "Metal Chest", "Demon Chest", "Leather Gloves", "Metal Gloves", "Rope Belt"
					, "Leather Belt", "Cloth Leggings", "Leather Leggings", "Plate Leggings", "Leather Shoes", "Metal Shoes", "Wooden Shield", "Spear", "Dagger","Bow",
					"Pistol","Shotgun","Rifle","Short Sword","Rapier","Long Sword","Dress","Blouse","Skirt","Female Leather Chest","Female Leather Gloves","Female Leather Shoulders"}
			
		};
		
	
		
		//compensates for the blank spaces in the declarations!
	static final short[] StatChoices = {0,LEVEL,EXP,HEALTH,HEALTHREGEN,ENERGY,STARTINGENERGY,ENERGYGROWTH,ENERGYMAX,
		MELEEPOWER,RANGEDPOWER,SPELLPOWER,ARMORRATING,MAGICRESIST,TOXINRESIST,INITIATIVE,SPEED,
			ENDURANCE,STAMINA,AGILITY,PERCEPTION,STRENGTH,DEXTERITY,INTELLIGENCE,WISDOM,SPEC_1,SPEC_2,DEAD,
			DISARMED,IMMOBILE,SILENCED,EXP_REWARD,PLAYER_OWNERSHIP,GOLD_CARRIED,WALK_MOVES_COUNT,ALIGNMENT,MODEL_ID,FACING,
			UNITDATA_NPCTYPE,UNITDATA_TURNSLEFT,UNITDATA_HARDCOREMODE,INVISIBLE,
			FLAG_ALLIESNEARBY,FLAG_ENEMIESNEARBY,FLAG_NIGHTTIME  };	
	
	
			
	
	//static final int POS_OF_FIRST_BASESTAT = 15;
	
	
	 String[][] ParameterStrings = new String[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 int[][] ParameterType = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 int[][] ParameterType_info = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 String[][] ParameterType_helptext = new String[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 String[][] ParameterDefaultValue = new String[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];

	 int[][] RequirementTag = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 int[][] RequirementTag_type = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS];
	 
	 int[][][] ParameterRequirement = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS][2];//identify the tag
	 int[][][] ParameterRequirement_State  = new int[NUMBER_OF_ASSETGROUPS][MAX_NUM_PARAMETERS][2];//identify the desired state so this parameter will be drawn
	 
	int [] ParameterCount = new int[NUMBER_OF_ASSETGROUPS];
	
	
	
	String[] assetnames_plural = {"Abilities","Effects","Conditions","Items","Units"};
	
	
	
	
	
	 int focusedAssetGroup;
	 SharedData(){
		 
		 //game info
		 

		

			// declare image offsets
			//imageOffset_x[UNITMODEL_MALEHUMAN] = -10;
			imageOffset_y[UNITMODEL_MALEHUMAN] = -15;
			
			//imageOffset_x[UNITMODEL_FEMALEHUMAN] = -10;
			imageOffset_y[UNITMODEL_FEMALEHUMAN] = -15;
			
			//imageOffset_x[WASP] = -5;
			//imageOffset_y[WASP] = -15;
			
			imageOffset_x[HEROSPAWN] = -5;
			imageOffset_y[HEROSPAWN] = 0;
			
			
			
			//Unit Models in order
			imageOffset_x[1] = 0;
			imageOffset_y[1] = -10;
			imageOffset_x[2] = 0;
			imageOffset_y[2] = -10;
			imageOffset_x[3] = 0;
			imageOffset_y[3] = -10;
			imageOffset_x[4] = 0;
			imageOffset_y[4] = -10;
			
/*
			imageOffset_x[SKELETON] = 0;
			imageOffset_y[SKELETON] = -40;
			imageOffset_x[WALL_H1] = 0;
			imageOffset_y[WALL_H1] = -40;
			imageOffset_x[WALL_H2] = 0;
			imageOffset_y[WALL_H2] = -40;
			imageOffset_x[WALL_H3] = 0;
			imageOffset_y[WALL_H3] = -40;
			imageOffset_x[WALL_H4] = 0;
			imageOffset_y[WALL_H4] = -40;
			imageOffset_x[WALL_C8] = 0;
			imageOffset_y[WALL_C8] = -40;
			imageOffset_x[WALL_C1] = 0;
			imageOffset_y[WALL_C1] = -40;
			imageOffset_x[WALL_C2] = 0;
			imageOffset_y[WALL_C2] = -40;
			imageOffset_x[WALL_C3] = 0;
			imageOffset_y[WALL_C3] = -40;
			imageOffset_x[WALL_C4] = 0;
			imageOffset_y[WALL_C4] = -40;
			imageOffset_x[WALL_C5] = 0;
			imageOffset_y[WALL_C5] = -40;
			imageOffset_x[WALL_C6] = 0;
			imageOffset_y[WALL_C6] = -40;
			imageOffset_x[WALL_C7] = 0;
			imageOffset_y[WALL_C7] = -40;
			imageOffset_x[PILLAR_1] = 0;
			imageOffset_y[PILLAR_1] = -40;*/
					
			 imageOffset_x[CLIFF_C1]=-20;
			 imageOffset_y[CLIFF_C1]=0;
			 imageOffset_x[CLIFF_C2]=10;
			 imageOffset_y[CLIFF_C2]=-10;
			 imageOffset_x[CLIFF_C3]=-30;
			 imageOffset_y[CLIFF_C3]=-30;
			 imageOffset_x[CLIFF_C4]=-29;
			 imageOffset_y[CLIFF_C4]=-45;
			 imageOffset_x[CLIFF_C5]=10;
			 imageOffset_y[CLIFF_C5]=-10;
			 imageOffset_x[CLIFF_C6]=-30;
			 imageOffset_y[CLIFF_C6]=-47;
			 imageOffset_x[CLIFF_C7]=10;
			 imageOffset_y[CLIFF_C7]=-10;
			 imageOffset_x[CLIFF_C8]=10;
			 imageOffset_y[CLIFF_C8]=-10;		 
			 imageOffset_x[CLIFF_H1]=10;
			 imageOffset_y[CLIFF_H1]=-10;
			 imageOffset_x[CLIFF_H2]=10;
			 imageOffset_y[CLIFF_H2]=-10;
			 imageOffset_x[CLIFF_H3]=10;
			 imageOffset_y[CLIFF_H3]=-20;
			 imageOffset_x[CLIFF_H4]=10;
			 imageOffset_y[CLIFF_H4]=-20;
			 imageOffset_x[CLIFF_V1]=-10;
			 imageOffset_y[CLIFF_V1]=-37;
			 imageOffset_x[CLIFF_V2]=-10;
			 imageOffset_y[CLIFF_V2]=-37;
			 imageOffset_x[CLIFF_V3]=-30;
			 imageOffset_y[CLIFF_V3]=-47;
			 imageOffset_x[CLIFF_V4]=-30;
			 imageOffset_y[CLIFF_V4]=-47;
			 
			 
			 imageOffset_x[TREE_5]=-20;
			 imageOffset_y[TREE_5]=20;
			 imageOffset_x[TREE_6]=-27;
			 imageOffset_y[TREE_6]=20;
			 
			 
			
			 
			 imageOffset_x[WOODWALL_V1]=0;
			 imageOffset_y[WOODWALL_V1]=0;
			 imageOffset_x[WOODWALL_V2]=0;
			 imageOffset_y[WOODWALL_V2]=0;
			 imageOffset_x[WOODWALL_V3]=15;
			 imageOffset_y[WOODWALL_V3]=0;
			 imageOffset_x[WOODWALL_V4]=15;
			 imageOffset_y[WOODWALL_V4]=0;
			 
			 imageOffset_x[WOODWALL_C1]=-6;
			 imageOffset_y[WOODWALL_C1]=6;
			 imageOffset_x[WOODWALL_C2]=0;
			 imageOffset_y[WOODWALL_C2]=0;
			
			 imageOffset_x[WOODWALL_C3]=0;
			 imageOffset_y[WOODWALL_C3]=2;
			 imageOffset_x[WOODWALL_C4]=-6;
			 imageOffset_y[WOODWALL_C4]=2;
			 
			 imageOffset_x[WOODWALL_H1]=0;
			 imageOffset_y[WOODWALL_H1]=-1;
			 imageOffset_x[WOODWALL_H2]=0;
			 imageOffset_y[WOODWALL_H2]=-1;
			 
			 imageOffset_x[WOODWALL_H3]=0;
			 imageOffset_y[WOODWALL_H3]=0;
			 imageOffset_x[WOODWALL_H4]=0;
			 imageOffset_y[WOODWALL_H4]=0;
			 
			 imageOffset_x[ROCK_1]=0;
			 imageOffset_y[ROCK_1]=0;
			 imageOffset_x[ROCK_2]=-8;
			 imageOffset_y[ROCK_2]=0;
			 
			 imageOffset_x[TREE_1]=-17;
			 imageOffset_y[TREE_1]=20;
			 imageOffset_x[TREE_2]=-32;
			 imageOffset_y[TREE_2]=20;
			 
			 imageOffset_x[ROCKFENCE_E1]=-2;
			 imageOffset_y[ROCKFENCE_E1]=-5;
			 imageOffset_x[ROCKFENCE_E2]=-2;
			 imageOffset_y[ROCKFENCE_E2]=-5;
			 
			 imageOffset_x[ROCKFENCE_H1]=-5;
			 imageOffset_y[ROCKFENCE_H1]=15;
			 imageOffset_x[ROCKFENCE_V1]=0;
			 imageOffset_y[ROCKFENCE_V1]=19;
			 
			 imageOffset_x[GRAVE_1]=0;
			 imageOffset_y[GRAVE_1]=0;
			 imageOffset_x[GRAVE_2]=0;
			 imageOffset_y[GRAVE_2]=0;
			 imageOffset_x[GRAVE_3]=0;
			 imageOffset_y[GRAVE_3]=30;
			 imageOffset_x[GRAVE_4]=0;
			 imageOffset_y[GRAVE_4]=30;
			 
			 imageOffset_x[MUSHROOMS_1]=22;
			 imageOffset_y[MUSHROOMS_1]=17;
			 imageOffset_x[MUSHROOMS_2]=10;
			 imageOffset_y[MUSHROOMS_2]=12;
			 imageOffset_x[CABBAGE]=10;
			 imageOffset_y[CABBAGE]=12;
			 imageOffset_x[CERAMICPOT]=14;
			 imageOffset_y[CERAMICPOT]=14;
			 imageOffset_x[PUMPKINS]=11;
			 imageOffset_y[PUMPKINS]=5;
			 imageOffset_x[BONECORPSE]=5;
			 imageOffset_y[BONECORPSE]=15;
			 imageOffset_x[WOODBUCKET]=15;
			 imageOffset_y[WOODBUCKET]=15; 
			 imageOffset_x[GRAINBAG]=8;
			 imageOffset_y[GRAINBAG]=12;
			 imageOffset_x[ANVIL1]=5;
			 imageOffset_y[ANVIL1]=15; 
			 imageOffset_x[ANVIL2]=12;
			 imageOffset_y[ANVIL2]=15; 
			 imageOffset_x[ANVIL3]=5;
			 imageOffset_y[ANVIL3]=5; 
			 imageOffset_x[ARMORSTAND]=6;
			 imageOffset_y[ARMORSTAND]=-10; 
			 imageOffset_x[BED_HORIZONTAL]=-6;
			 imageOffset_y[BED_HORIZONTAL]=0; 
			 imageOffset_x[BED_VERTICAL]=-5;
			 imageOffset_y[BED_VERTICAL]=-5; 
			 imageOffset_x[BOOKSHELF]=0;
			 imageOffset_y[BOOKSHELF]=0; 
			 imageOffset_x[THINTREE]=-20;
			 imageOffset_y[THINTREE]=0; 
			 imageOffset_x[LARGEBUSH]=-10;
			 imageOffset_y[LARGEBUSH]=-12;
			 imageOffset_x[WOOD_CHEST]=4;
			 imageOffset_y[WOOD_CHEST]=4;
			 imageOffset_x[MAGICCHEST_BLACK]=4;
			 imageOffset_y[MAGICCHEST_BLACK]=4;
			 imageOffset_x[MAGICCHEST_BLUE]=4;
			 imageOffset_y[MAGICCHEST_BLUE]=4;
			 imageOffset_x[MAGICCHEST_GREEN]=4;
			 imageOffset_y[MAGICCHEST_GREEN]=4;
			 imageOffset_x[MAGICCHEST_RED]=4;
			 imageOffset_y[MAGICCHEST_RED]=4;
			 imageOffset_x[MAGICCHEST_WHITE]=4;
			 imageOffset_y[MAGICCHEST_WHITE]=4;
			 imageOffset_x[TREASURECHEST_2]=4;
			 imageOffset_y[TREASURECHEST_2]=4;
			 
				
				
			 imageOffset_x[WELL_EMPTY]=4;
			 imageOffset_y[WELL_EMPTY]=0;
			 imageOffset_x[WELL_RED]=4;
			 imageOffset_y[WELL_RED]=0;
			 imageOffset_x[WELL_BLUE]=4;
			 imageOffset_y[WELL_BLUE]=0;
			 imageOffset_x[FLOURBAGS]=-6;
			 imageOffset_y[FLOURBAGS]=4;
			 imageOffset_x[FRUITSTAND_1]=6;
			 imageOffset_y[FRUITSTAND_1]=-14;
			 imageOffset_x[FRUITSTAND_2]=9;
			 imageOffset_y[FRUITSTAND_2]=-2;
			 imageOffset_x[FRUITSTAND_3]=8;
			 imageOffset_y[FRUITSTAND_3]=-8;
			 imageOffset_x[FURNITURE_1]=8;
			 imageOffset_y[FURNITURE_1]=-15;
			 imageOffset_x[PILLORY]=4;
			 imageOffset_y[PILLORY]=4;
			 imageOffset_x[STATUE_WARRIOR]=-3;
			 imageOffset_y[STATUE_WARRIOR]=0;
			 imageOffset_x[STATUE_MAGE]=-3;
			 imageOffset_y[STATUE_MAGE]=5;
			 imageOffset_x[STOVE]=5;
			 imageOffset_y[STOVE]=-0;
			 imageOffset_x[STREETLAMP]=-5;
			 imageOffset_y[STREETLAMP]=-10;
			 imageOffset_x[TOMEPODIUM]=12;
			 imageOffset_y[TOMEPODIUM]=-15;
			 imageOffset_x[WOODBARREL]=3;
			 imageOffset_y[WOODBARREL]=-15;
			 imageOffset_x[WOODSIGN]=-5;
			 imageOffset_y[WOODSIGN]=-5;
			 imageOffset_x[WELL_LARGE]=-5;
			 imageOffset_y[WELL_LARGE]=5;
			 
		
			 
			imageOffset_x[TORCH] = 0;
			imageOffset_y[TORCH] = -10;
			imageOffset_x[DOOR_H_OPEN] = 0;
			imageOffset_y[DOOR_H_OPEN] = -15;
			imageOffset_x[DOOR_H_CLOSED] = 0;
			imageOffset_y[DOOR_H_CLOSED] = -15;
			
			 
			
			
			 ObjectLightRadiation[STREETLAMP]=10;
			 ObjectLightRadiation[TORCH]=10;
			 ObjectLightRadiation[DEMONCANDLE]=8;
			 ObjectLightRadiation[SIGNALFIRE]=8;
			
			
			
			//separate the strings into groups
			
			
			
			
		 //asset info
		 
		 
		 focusedAssetGroup = ASSETGROUP_ABILITIES;//---------------------------
		 
		 AddParameter("Name",PARATYPE_ALPHANUMERIC);
		 AddParameter("Suffix",PARATYPE_ALPHANUMERIC,"Only for Asset Editor ease-of-use");
		 AddParameter("Enabled",PARATYPE_BOOLEAN);
		 AddParameter("Description",PARATYPE_ALPHANUMERICHUGE);
		 AddParameter("Icon",PARATYPE_COMBOICONS,COMBOICONS_ABILITYICONS);
		 AddParameter("Passive",PARATYPE_BOOLEAN);
		 TagLastParameter(5,PARATYPE_BOOLEAN);
		 AddParameter("Cast Animation",PARATYPE_COMBOTEXT,COMBOTEXT_MOVEMENTANIMATIONS);
		 RequireForLastParameter(5,0);
		 AddParameter("Required Stat",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);//fix implement
		 AddParameter("Required Stat Amount",PARATYPE_NUMERIC);//fix implement
		 AddParameter("Assignment",PARATYPE_COMBOTEXT,COMBOTEXT_ABILITYSPECS);
		 TagLastParameter(4,PARATYPE_BOOLEAN);
		 AddParameter("Required Weapon",PARATYPE_COMBOTEXT,COMBOTEXT_WEAPONTYPES);
		// RequireForLastParameter(4,11);
		 AddParameter("Requires Targetting",PARATYPE_BOOLEAN);
		 TagLastParameter(1,PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,0);
		 AddParameter("Cardinal Direction Only",PARATYPE_BOOLEAN);
		 TagLastParameter(3,PARATYPE_BOOLEAN);
		 RequireForLastParameter(1,1);
		 RequireForLastParameter(5,0);
		 AddParameter("Cardinal Distance Override",PARATYPE_NUMERIC, "If non-zero, this distance will be used instead of mouse distance.");
		 RequireForLastParameter(3,1);
		 RequireForLastParameter(5,0);
		 AddParameter("Can Target Unitless Tiles",PARATYPE_BOOLEAN);
		 RequireForLastParameter(1,1);
		 RequireForLastParameter(5,0);
		 AddParameter("Can Target Units",PARATYPE_BOOLEAN);
		 RequireForLastParameter(1,1);
		 RequireForLastParameter(5,0);
		 TagLastParameter(2,PARATYPE_BOOLEAN);
		 AddParameter("Can Target Allies",PARATYPE_BOOLEAN);
		 RequireForLastParameter(2,1);
		 AddParameter("Can Target Self",PARATYPE_BOOLEAN);
		 RequireForLastParameter(2,1);
		 AddParameter("Can Target Enemies",PARATYPE_BOOLEAN);
		 RequireForLastParameter(2,1);
		 AddParameter("Require Target Walkable",PARATYPE_BOOLEAN);		
		 RequireForLastParameter(5,0);
		 AddParameter("Range Base",PARATYPE_NUMERIC);	
		 AddParameter("Stat Affecting Range",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);	
		 AddParameter("Stat Aff Range %",PARATYPE_NUMERIC_PERCENT);	
		 AddParameter("Energy Cost Base",PARATYPE_NUMERIC);	
		 RequireForLastParameter(5,0);
		 AddParameter("Stat Affecting Cost",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);	
		 RequireForLastParameter(5,0);
		 AddParameter("Stat Aff Cost %",PARATYPE_NUMERIC_PERCENT);	
		 RequireForLastParameter(5,0);
		 AddParameter("Cooldown Base",PARATYPE_NUMERIC);	
		 RequireForLastParameter(5,0);
		 AddParameter("Stat Affecting Cooldown",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);	
		 RequireForLastParameter(5,0);
		 AddParameter("Stat Aff Cooldown %",PARATYPE_NUMERIC_PERCENT);	
		 RequireForLastParameter(5,0);
		 AddParameter("Counts as Movement",PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,0);
		 AddParameter("Counts as an Attack",PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,0);
		 for(int i=0;i<10;i++){
		 AddParameter(i + "." +"Effect",PARATYPE_COMBOTEXT,COMBOTEXT_MYEFFECTS);
		 RequireForLastParameter(5,0);
		 }
		 AddParameter("Can Affect Allies",PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,1);
		 AddParameter("Can Affect Self",PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,1);
		 AddParameter("Can Affect Enemies",PARATYPE_BOOLEAN);
		 RequireForLastParameter(5,1);
		 AddParameter("Condition Applied",PARATYPE_COMBOTEXT,COMBOTEXT_MYCONDITIONS);
		 RequireForLastParameter(5,1);
		 
		 focusedAssetGroup = ASSETGROUP_EFFECTS;//---------------------------
		 AddParameter("Name",PARATYPE_ALPHANUMERIC);
		 AddParameter("Suffix",PARATYPE_ALPHANUMERIC,"Only for Asset Editor ease-of-use");
		 AddParameter("Enabled",PARATYPE_BOOLEAN);
		 AddParameter("Effect Type",PARATYPE_COMBOTEXT,COMBOTEXT_EFFECTTYPES);	
		 TagLastParameter(1,PARATYPE_BOOLEAN);
		 AddParameter("Flip Casting Coordinates",PARATYPE_BOOLEAN, "True: Use End Point, False: Use Caster Point");
		 AddParameter("Use Trajectory Collision",PARATYPE_BOOLEAN, "True: Effect will occur at the first obstacle, False: Occurs at the point");
		 TagLastParameter(2,PARATYPE_BOOLEAN);
		 AddParameter("Trajectory Ends on Top",PARATYPE_BOOLEAN, "True: Before obstacle, False: On top of obstacle");
		 RequireForLastParameter(2,1);
		 AddParameter("Shape Type",PARATYPE_COMBOTEXT,COMBOTEXT_SHAPES);
		 AddParameter("Shape Radius",PARATYPE_NUMERIC);
		 AddParameter("Shape Spread Angle",PARATYPE_NUMERIC, "360 makes a full circle");
		 AddParameter("Can Affect Allies",PARATYPE_BOOLEAN);
		// TagLastParameter(3,PARATYPE_BOOLEAN);
		 AddParameter("Can Affect Self",PARATYPE_BOOLEAN);
		// RequireForLastParameter(3,1);
		 AddParameter("Can Affect Enemies",PARATYPE_BOOLEAN);
		 AddParameter("Movement Animation",PARATYPE_COMBOTEXT,COMBOTEXT_MOVEMENTANIMATIONS);
		 RequireForLastParameter(1,0);
		 AddParameter("Movement Speed",PARATYPE_NUMERIC);
		 RequireForLastParameter(1,0);
		 
		 AddParameter("Force Negative Edit",PARATYPE_BOOLEAN, "Will ensure that overall stat change cannot help the target.");
		 RequireForLastParameter(1,1);
		 for(int i=0;i<5;i++){
			 AddParameter(i + "." + "Stat To Change",PARATYPE_COMBOTEXT,COMBOTEXT_STATS,"Only the condition-holders stats are changed.");
			 RequireForLastParameter(1,1);
			 AddParameter(i + "." +"Base Amount",PARATYPE_NUMERIC);
			 RequireForLastParameter(1,1);
			 AddParameter(i + "." +"Stat Affecting Amount",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);
			 RequireForLastParameter(1,1);
			 AddParameter(i + "." +"Stat Aff Amount %",PARATYPE_NUMERIC_PERCENT);
			 RequireForLastParameter(1,1);
			 AddParameter(i + "." +"Set Stat",PARATYPE_BOOLEAN,"If true, the stat is simply set to this value.  Otherwise, the stat is added to.");
			 RequireForLastParameter(1,1);
			 AddParameter(i + "." +"Use Caster Stat",PARATYPE_BOOLEAN,"If false, the targets stat is used.");
			 RequireForLastParameter(1,1);
			/* AddParameter(i + "." +"Stat Change is Temp",PARATYPE_BOOLEAN,"If false, this stat change occurs every turn for the duration.  If true, the stat change occurs once and is reverted after the duration ends.");
			 RequireForLastParameter(1,1);	 */
		 }
		 
		 
		 AddParameter("Condition Applied",PARATYPE_COMBOTEXT,COMBOTEXT_MYCONDITIONS);
		 RequireForLastParameter(1,2);
		 
		 AddParameter("Base Duration",PARATYPE_NUMERIC);
		 AddParameter("Stat Affecting Duration",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);
	     AddParameter("Stat Aff Duration %",PARATYPE_NUMERIC_PERCENT);	
		// AddParameter("Use Caster Stats",PARATYPE_BOOLEAN, "If false, targets stats will be used.");	
		 
	     AddParameter("Projectile Type",PARATYPE_COMBOTEXT,COMBOTEXT_PROJECTILES);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile R",PARATYPE_NUMERIC_PERCENT);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile G",PARATYPE_NUMERIC_PERCENT);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile B",PARATYPE_NUMERIC_PERCENT);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile Speed",PARATYPE_NUMERIC);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile Arc Height",PARATYPE_NUMERIC);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile Luminescence",PARATYPE_NUMERIC);
		 RequireForLastParameter(1,3);
	     AddParameter("Projectile Only to Units",PARATYPE_BOOLEAN, "True: Only draw projectiles towards tiles with units");
		 RequireForLastParameter(1,3);
		 for(int i=0;i<5;i++){
		 AddParameter(i + "." +"Projectile Death Effect",PARATYPE_COMBOTEXT,COMBOTEXT_MYEFFECTS);
		 RequireForLastParameter(1,3);
		 }
	     AddParameter("Unit Spawned",PARATYPE_COMBOTEXT,COMBOTEXT_MYUNITS);  //can make this a dropdown some day!
		 RequireForLastParameter(1,4);
	     AddParameter("Graphic ID Drawn",PARATYPE_COMBOTEXT,COMBOTEXT_GFXIDS);
		 RequireForLastParameter(1,5);
	     AddParameter("Graphic Red",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     RequireForLastParameter(1,5);
	     AddParameter("Graphic Green",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     RequireForLastParameter(1,5);
	     AddParameter("Graphic Blue",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     RequireForLastParameter(1,5);
	     AddParameter("Graphic Scale",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     RequireForLastParameter(1,5);
	     AddParameter("Sound ID",PARATYPE_COMBOTEXT,COMBOTEXT_SOUNDIDS); 
		 //RequireForLastParameter(1,6);
		 
		 focusedAssetGroup = ASSETGROUP_CONDITIONS;//---------------------------
		 AddParameter("Name",PARATYPE_ALPHANUMERIC);
		 AddParameter("Suffix",PARATYPE_ALPHANUMERIC,"Only for Asset Editor ease-of-use");
		 AddParameter("Enabled",PARATYPE_BOOLEAN);
		 AddParameter("Description",PARATYPE_ALPHANUMERICHUGE);
		 AddParameter("Icon",PARATYPE_COMBOICONS,COMBOICONS_CONDITIONICONS);
		 AddParameter("Binds to Units",PARATYPE_BOOLEAN);
		 AddParameter("Stacks Duration",PARATYPE_BOOLEAN);
		 AddParameter("Stacks Intensity",PARATYPE_BOOLEAN);
	     AddParameter("Graphic ID Drawn",PARATYPE_COMBOTEXT,COMBOTEXT_GFXIDS);
	     AddParameter("Graphic Red",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     AddParameter("Graphic Green",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     AddParameter("Graphic Blue",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     AddParameter("Graphic Scale",PARATYPE_NUMERIC);LastParamDefaultValue("100");
	     AddParameter("Sound ID",PARATYPE_COMBOTEXT,COMBOTEXT_SOUNDIDS); 
	     for(int i=0;i<5;i++){
			 AddParameter(i + "." +"Passive Effect",PARATYPE_COMBOTEXT,COMBOTEXT_MYEFFECTS);
	     }
	     for(int i=0;i<5;i++){
			 AddParameter(i + "." +"Periodic Effect",PARATYPE_COMBOTEXT,COMBOTEXT_MYEFFECTS);
	     }
	    
		 
		 focusedAssetGroup = ASSETGROUP_ITEMS;//---------------------------
		 AddParameter("Name",PARATYPE_ALPHANUMERIC);
		 AddParameter("Suffix",PARATYPE_ALPHANUMERIC,"Only for Asset Editor ease-of-use");
		 AddParameter("Enabled",PARATYPE_BOOLEAN);
		 AddParameter("Description",PARATYPE_ALPHANUMERICHUGE);
		 AddParameter("Icon",PARATYPE_COMBOICONS,COMBOICONS_ITEMICONS);
		 AddParameter("Purchasable",PARATYPE_BOOLEAN);
		 AddParameter("Value",PARATYPE_NUMERIC);
		 AddParameter("Equip Slot",PARATYPE_COMBOTEXT,COMBOTEXT_EQUIPSLOTS); 
		 AddParameter("Equip Model",PARATYPE_COMBOTEXT,COMBOTEXT_EQUIPMODELS);
		 AddParameter("Model Red",PARATYPE_NUMERIC); LastParamDefaultValue("100");
		 AddParameter("Model Green",PARATYPE_NUMERIC); LastParamDefaultValue("100");
		 AddParameter("Model Blue",PARATYPE_NUMERIC); LastParamDefaultValue("100");
		 AddParameter("Item Level",PARATYPE_ALPHANUMERIC);
		 AddParameter("Weapon Type",PARATYPE_COMBOTEXT,COMBOTEXT_WEAPONTYPES);
		 AddParameter("Item Type",PARATYPE_COMBOTEXT,COMBOTEXT_LOOTGROUPS);
		 AddParameter("Max Stack Size",PARATYPE_NUMERIC);LastParamDefaultValue("1");
		 for(int i=0;i<5;i++){
			 AddParameter(i + "." +"Required Crafting Mat",PARATYPE_COMBOTEXT,COMBOTEXT_MYITEMS);
			 AddParameter(i + "." +"Mat Quantity Needed",PARATYPE_NUMERIC);
			 }
		 
		 for(int i=0;i<5;i++){
			 AddParameter(i + "." +"Ability Added",PARATYPE_COMBOTEXT,COMBOTEXT_MYABILITIES);
		 }
		 for(int i=0;i<5;i++){
			 AddParameter(i + "." +"Use Effect",PARATYPE_COMBOTEXT,COMBOTEXT_MYEFFECTS);
		 }
			 
		 for(int i=0;i<5;i++){
		 AddParameter(i + "." +"Stat To Change",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);
		 AddParameter(i + "." +"Base Amount",PARATYPE_NUMERIC);
		 AddParameter(i + "." +"Stat Affecting Amount",PARATYPE_COMBOTEXT,COMBOTEXT_STATS);
		 AddParameter(i + "." +"Stat Aff Amount %",PARATYPE_NUMERIC_PERCENT);
		 }
		 
		 focusedAssetGroup = ASSETGROUP_UNITS;//---------------------------
		 AddParameter("Name",PARATYPE_ALPHANUMERIC);
		 AddParameter("Suffix",PARATYPE_ALPHANUMERIC,"Only for Asset Editor ease-of-use");
		 AddParameter("Enabled",PARATYPE_BOOLEAN);
		 AddParameter("Description",PARATYPE_ALPHANUMERICHUGE);
		// AddParameter("Race",PARATYPE_ALPHANUMERIC);
		 AddParameter("Alignment",PARATYPE_COMBOTEXT,COMBOTEXT_ALIGNMENT);
		 LastParamDefaultValue(""+4);
		 AddParameter("Model",PARATYPE_COMBOTEXT,COMBOTEXT_UNITMODELS);
		 AddParameter("Sound",PARATYPE_COMBOTEXT,COMBOTEXT_SOUNDIDS);
		 AddParameter("Category",PARATYPE_COMBOTEXT,COMBOTEXT_UNITCATEGORIES);//for organization in hosts placing menu
		 AddParameter("Invulnerable",PARATYPE_BOOLEAN);
		 AddParameter("Immobile",PARATYPE_BOOLEAN);
		 AddParameter("Expiration Timer",PARATYPE_NUMERIC, "At 0, this unit will not spawn with an expiration timer.");
		 AddParameter("Base Level",PARATYPE_NUMERIC);
		 LastParamDefaultValue(""+1);
		 AddParameter("Base Endurance",PARATYPE_NUMERIC);
		 AddParameter("Base Stamina",PARATYPE_NUMERIC);
		 AddParameter("Base Melee Power",PARATYPE_NUMERIC);
		 AddParameter("Base Ranged Power",PARATYPE_NUMERIC);
		 AddParameter("Base Spell Power",PARATYPE_NUMERIC);
		 AddParameter("Base Armor",PARATYPE_NUMERIC);
		 AddParameter("Base Magic Resist",PARATYPE_NUMERIC);
		 AddParameter("Base Toxin Resist",PARATYPE_NUMERIC);
		 AddParameter("Base Initiative",PARATYPE_NUMERIC, "Determines Unit Battle Order");
		 AddParameter("Base Speed",PARATYPE_NUMERIC , "Number of movements per turn is Speed/5");
		 AddParameter("Exp Reward",PARATYPE_NUMERIC);
		 AddParameter("Gold Carried",PARATYPE_NUMERIC);
		 for(int i=0;i<5;i++){
		 AddParameter(i + "." +"Ability",PARATYPE_COMBOTEXT,COMBOTEXT_MYABILITIES);
		 }
		 for(int i=0;i<5;i++){
		 AddParameter(i + "." +"Item Held",PARATYPE_COMBOTEXT,COMBOTEXT_MYITEMS);
		 }
		 for(int i=0;i<5;i++){
		 AddParameter(i + "." +"Item Dropped",PARATYPE_COMBOTEXT,COMBOTEXT_MYITEMS);
		 AddParameter(i + "." +"Drop Chance %",PARATYPE_NUMERIC_PERCENT);
		 }
		 
		 
		
		
	 }
	 
	 
	 
	 private void TagLastParameter(int tag_ID, int type) {
		int LastParameter = ParameterCount[focusedAssetGroup]-1;
				 
		//if(type == PARATYPE_BOOLEAN){		 
		RequirementTag[focusedAssetGroup][tag_ID] = LastParameter;
		
		RequirementTag_type[focusedAssetGroup][tag_ID] = type;
		
		//}
	}
	 
	private void LastParamDefaultValue(String value) {
		int LastParameter = ParameterCount[focusedAssetGroup]-1;
		
		ParameterDefaultValue[focusedAssetGroup][LastParameter]=value;
		 
	}
	
	private void RequireForLastParameter(int tag_ID,int state) {
		int LastParameter = ParameterCount[focusedAssetGroup]-1;
		
		
		if(ParameterRequirement[focusedAssetGroup][LastParameter][0]==0){
		ParameterRequirement[focusedAssetGroup][LastParameter][0]=tag_ID;
		  ParameterRequirement_State[focusedAssetGroup][LastParameter][0]=state;
		  }else{
			  ParameterRequirement[focusedAssetGroup][LastParameter][1]=tag_ID;
		  ParameterRequirement_State[focusedAssetGroup][LastParameter][1]=state;
			  
		  }
		 
	}
	


	


	void AddParameter(String label, int type){
		
		 ParameterStrings[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = label;
		 ParameterType[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = type;
		 
		 ParameterCount[focusedAssetGroup]++;
	 }
	
	void AddParameter(String label, int type, String helptext){
		
		 ParameterStrings[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = label;
		 ParameterType[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = type;
		 ParameterType_helptext[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = helptext;
		 
		 ParameterCount[focusedAssetGroup]++;
	 }
	
	void AddParameter( String label, int type, int extrainfo){
		
		 ParameterStrings[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = label;
		 ParameterType[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = type;
		 ParameterType_info[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = extrainfo;
		 
		 ParameterCount[focusedAssetGroup]++;
	 }
	
	void AddParameter( String label, int type, int extrainfo, String helptext){
		
		 ParameterStrings[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = label;
		 ParameterType[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = type;
		 ParameterType_info[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = extrainfo;
		 ParameterType_helptext[focusedAssetGroup][ParameterCount[focusedAssetGroup]] = helptext;
		 
		 ParameterCount[focusedAssetGroup]++;
	 }
	
	
	int BooleanToInt(boolean b){
		int answer =0;
		
		if(b){answer=1;}
		
		return answer;		
		
	} 
	
	static String[] HeroNames = {
		"Shadkeldua",

		"Ippair",

		"Unlor",

		"Anorped",

		"Zavyr",

		"Zelvyr",

		"Gamar",

		"Aryus",

		"Rowyas",

		"Lardor",

		"Athaera",

		"Kyracan",

		"Damarvyr",

		"Lyrpen",

		"Damarna",

		"Laryan",

		"Shadcan",

		"Yarzar",

		"Daligath",

		"Lyrula",

		"Noceth",

		"Venyas",

		"Banlor",

		"Corplen",

		"Venurrroth",

		"Stalgancir",

		"Agura",

		"Arenir",

		"Cryswa",

		"Unnkel",

		"Belsol",

		"Gromforlor",

		"Rowla",

		"Kamir",

		"Bucaren",

		"Raieth",

		"Wyrlor",

		"Gasar",

		"Athamir",

		"Corerla",

		"Wyrus",

		"Tiradatas",

		"Radihan",

		"Gromrath",

		"Damarfora",

		"Bradia",

		"Carzar",

		"Ignieth",

		"Ignidia",

		"Wanderer",

		"Buchisa",

		"Radimir",

		"Alfdor",

		"Marancan",

		"Alwa",

		"Kaerla",

		"Futhanos",

		"Alcel",

		"Ganos",

		"Kyrater",

		"Sir Toasty",

		"Yarmyr",

		"Rowamyr",

		"Tarasbar",

		"Unnir",

		"Notch",

		"Malnare",

		"Radilum",

		"Fanacan",

		"Ales",

		"Zellum",

		"Vidacir",

		"Gromcir",

		"Sayter",

		"Hadramyr",

		"Cordua",

		"Eddadua",

		"Selyan",

		"Stalurr",

		"Maranmurla",

		"Radigan",

		"Tarasmur",

		"Zaes",

		"Tarasdia",

		"Tirurr",

		"Crisyas",

		"Banwug",

		"Raibyr",

		"Crysmyr",

		"Aryeth",

		"Tarasmir",

		"Rowabìnhisa",

		"Sayada",

		"Carmyr",

		"Fafora",

		"Aryhisa",

		"Lyryara",

		"Hadralanradas",

		"Carpyrmyr"
	};
	
	 
}
