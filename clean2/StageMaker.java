import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

public class StageMaker extends Applet implements Runnable {
   Graphics2D rd;
   Image offImage;
   Thread thredo;
   boolean exwist = false;
   FontMetrics ftm;
   int apx = 0;
   int apy = 0;
   String sstage = "";
   String suser = "Horaks";
   int tab = 0;
   int tabed = -1;
   Image[] btgame = new Image[2];
   Image logo;
   boolean onbtgame = false;
   boolean focuson = true;
   boolean overcan = false;
   boolean left = false;
   boolean right = false;
   boolean up = false;
   boolean down = false;
   boolean zoomi = false;
   boolean zoomo = false;
   String stagename = "";
   String tstage = "snap(0,0,0)\r\nsky(191,215,255)\r\nclouds(255,255,255,5,-1000)\r\nfog(195,207,230)\r\nground(192,194,202)\r\ntexture(0,0,0,50)\r\nfadefrom(5000)\r\ndensity(5)\n\rmountains("
      + (int)(Math.random() * 100000.0)
      + ")\r\nnlaps(5)\r\n\r\n";
   String bstage = "set(47,0,0,0)\r\nmaxr(11,28500,-5600)\r\nmaxb(9,-8000,-12300)\r\nmaxl(11,-14700,-5600)\r\nmaxt(9,44800,-12300)\r\n";
   String[] undos = new String[5000];
   int nundo = 0;
   Medium m = new Medium();
   CheckPoints cp = new CheckPoints();
   Trackers t = new Trackers();
   ContO[] bco = new ContO[67];
   ContO[] co = new ContO[601];
   int nob = 0;
   int xnob = 0;
   int errd = 0;
   int origfade = 5000;
   int sfase = 0;
   Smenu slstage = new Smenu(2000);
   TextField srch = new TextField("", 38);
   Smenu strtyp = new Smenu(40);
   Smenu ptyp = new Smenu(40);
   Smenu part = new Smenu(40);
   int sptyp = 0;
   int spart = 0;
   int sp = 0;
   int lsp = -1;
   int seq = 0;
   boolean setcur = false;
   boolean epart = false;
   boolean arrng = false;
   int esp = -1;
   int hi = -1;
   int arrcnt = 0;
   int chi = -1;
   boolean seqn = false;
   int rot = 0;
   int adrot = 0;
   Image[] su = new Image[2];
   Image[] sl = new Image[2];
   Image[] sd = new Image[2];
   Image[] sr = new Image[2];
   Image[] zi = new Image[2];
   Image[] zo = new Image[2];
   boolean pgen = false;
   float pwd = (float)(2L + Math.round(Math.random() * 4.0));
   float phd = (float)(2L + Math.round(Math.random() * 4.0));
   int fgen = 0;
   int sx = 0;
   int sz = 1500;
   int sy = -10000;
   TextField fixh = new TextField("2000", 5);
   int hf = 2000;
   int[][] atp = new int[][]{
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {1520, 2830, -1520, -2830},
      {-1520, 2830, 1520, -2830},
      {0, -1750, 1750, 0},
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {0, -1750, 1750, 0},
      {0, 2800, 0, -2800},
      {0, -1750, 1750, 0},
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {0, 560, 0, -560},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {385, 980, 385, -980},
      {0, 0, 0, -600},
      {0, 0, 0, 0},
      {0, 2164, 0, -2164},
      {0, 2164, 0, -2164},
      {0, 3309, 0, -1680},
      {0, 1680, 0, -3309},
      {350, 0, -350, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {1810, 980, 1810, -980},
      {0, 0, 0, 0},
      {0, 500, 0, -500},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {0, 1680, 0, -3309},
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {0, 2800, 0, -2800},
      {700, 1400, 700, -1400},
      {0, -1480, 0, -1480},
      {0, 0, 0, 0},
      {350, 0, -350, 0},
      {0, 0, 0, 0},
      {700, 0, -700, 0},
      {0, 0, 0, 0},
      {0, -2198, 0, 1482},
      {0, -1319, 0, 1391},
      {0, -1894, 0, 2271},
      {0, -826, 0, 839},
      {0, -1400, 0, 1400},
      {0, -1400, 0, 1400},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0},
      {0, 0, 0, 0}
   };
   String[] discp = new String[]{
      "NormalRoad :  Basic asphalt road.\nAttaches correctly to the following other parts :\n\n'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',  'NormalRoad Edged',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "NormalRoad Edged :  Asphalt road with edged side blocks (a destructive road).\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "NormalRoad TwistedRight :  Asphalt road twisted towards the right.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad Twistedleft',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "NormalRoad TwistedLeft :  Asphalt road twisted towards the left.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "NormalRoad Turn :  Asphalt corner road turn.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp', 'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "OffRoad :  Basic sandy dirt-road.\nAttaches correctly to the following other parts :\n\n'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
      "OffRoad BumpyGreen :  Dirt-road with bumpy greenery in the middle.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
      "OffRoad Turn :  Dirt-road corner turn.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad End',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand 'Normal-Off-Road Blend'\n\n",
      "HalfpipeRoad :  Basic road for the half-pipe ramp.\nAttaches correctly to the following other parts :\n\n'Off-Halfpipe-Road Blend',  'HalfpipeRoad',  'HalfpipeRoad Turn',  'HalfpipeRoad-Ramp Filler'\nand  'Halfpipe-Normal-Road Blend'\n\n",
      "HalfpipeRoad Turn :  Half-pipe corner road turn.\nAttaches correctly to the following other parts :\n\n'HalfpipeRoad',  'Off-Halfpipe-Road Blend',  'HalfpipeRoad'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "Normal-Off-Road Blend :  Road blend between the normal asphalt road and the dirt-road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'NormalRoad-Raised Ramp', 'Halfpipe-Normal-Road Blend' 'OffRoad',  'OffRoad Turn',\n'OffRoad End',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start'  and  'Off-Halfpipe-Road Blend'\n\n",
      "Off-Halfpipe-Road Blend :  Road blend between the dirt-road and the half-pipe road.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start',\n'HalfpipeRoad',  'HalfpipeRoad Turn',  'Halfpipe-Normal-Road Blend'  and  'Normal-Off-Road Blend'\n\n",
      "Halfpipe-Normal-Road Blend :  Road blend between the normal asphalt road and the half-pipe road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'NormalRoad-Raised Ramp',  'HalfpipeRoad',  'Off-Halfpipe-Road Blend',  'HalfpipeRoad'\nand  'Off-Halfpipe-Road Blend'\n\n",
      "NormalRoad End :  The end part of the normal asphalt road.\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad Edged',  'NormalRoad TwistedLeft',  'NormalRoad TwistedRight',\n'NormalRoad-Raised Ramp',  'Normal-Off-Road Blend'  and  'Halfpipe-Normal-Road Blend'\n\n",
      "OffRoad End :  The end part of the dirt-road.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad BumpyGreen',  ' OffRoad-BumpySides Start',  'Off-Halfpipe-Road Blend'\nand  'Normal-Off-Road Blend'\n\n",
      "HalfpipeRoad-Ramp Filler :  A part that gets placed between the half-pipe road and the half-pipe ramp to extend the distance in between.\nAttaches correctly to the following other parts :\n\n'HalfpipeRoad'  and  'Halfpipe'\n\n",
      "Basic Ramp :  Basic 30 degree asphalt ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Crash Ramp :  A 35 degree ramp with big side blocks for crashing into.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Two-Way Ramp :  Two way 15 degree inclined ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Two-Way High-Low Ramp :  Two way 15 degree inclined ramp, with peeked side for an optional higher car jump.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Landing Ramp :  A ramp that is both a landing inclination and an obstacle as well, it is usually placed just after another normal ramp.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
      "Big-Takeoff Ramp:  A big takeoff ramp for getting huge heights with the cars.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
      "Small Ramp :  A small ramp that can be placed on either side of the road.\nAttaches correctly over and to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand   'NormalRoad TwistedRight'\n\n",
      "Offroad Bump Ramp :  A small bump ramp that is to be placed over the off-road dirt tracks.\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
      "Offroad Big Ramp :  The big off-road dirt mountain like ramp!\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
      "Offroad Ramp :  Normal sized off-road dirt track ramp!\nAttaches correctly over and to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
      "Halfpipe :  The Half-pipe ramp, two of these ramps opposite each other create a half-pipe for the cars!\nAttaches correctly over and to the following other parts :\n\n'HalfpipeRoad',  'HalfpipeRoad Turn'  and  'HalfpipeRoad-Ramp Filler'\n\n",
      "Spiky Pillars :  An obstacle that is usually placed after a ramp for the cars to crash onto if they did not jump high or far enough!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Rail Doorway :  A rail doorway that works as an obstacle for cars flying above it or cars driving through it!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "The Wall",
      "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
      "Fixing Hoop :  The fixing hoop that fixes a car when it flies through it! You can add a max of 5 fixing hoops per stage.\nPlace it anywhere in the stage at an height your choose, the only important thing is that it needs to be reachable by the cars.",
      "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
      "OffRoad BumpySides :  Off-road dirt track with bumpy sandbar sides.\nAttaches correctly to the following other parts :\n\n'OffRoad-BumpySides Start'\n\n",
      "OffRoad-BumpySides Start: The start of the off-road dirt track with bumpy sandbar sides.\nAttaches correctly to the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides',\n'Off-Halfpipe-Road Blend'  and  'Normal-Off-Road Blend'\n\n",
      "NormalRoad-Raised Ramp:  The start of the raised above the ground road (NormalRoad Raised).\nAttaches correctly to the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight'  and  'NormalRoad Raised'\n\n",
      "NormalRoad Raised :  Normal road raised above the ground, cars must avoid falling off it when driving on it.\nAttaches correctly to the following other parts :\n\n'NormalRoad-Raised Ramp'\n\n",
      "The Start1",
      "The Start2",
      "Tunnel Side Ramp:  A ramp that can be used to create a tunnel like road with an open top or can be used as a wall ramp!\nAttaches correctly over only the 'NormalRoad' part.",
      "Launch Pad Ramp:  A ramp that launches your car fully upwards like a rocket, it also has sides to lock any car climbing it!\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "The Net:  An obstacle part that is to be placed in the center of the road right after a ramp, the idea is that the\ncars jumping the ramp should try to go over it or through it without getting caught crashing (without getting\ncaught in it, getting caught in the net!).\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Speed Ramp:  A ramp that is designed to have the perfect angle to catapult your car the furthest when doing forward loops, it is half the roads width.\nAttaches correctly over following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Offroad Hill Ramp:  An offroad hill ramp that has two different inclines from the front and back to jump.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
      "Bump Slide:  A small bump obstacle that is to be placed on the sides of the road or in the center.\nAttaches correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft'\nand  'NormalRoad TwistedRight'\n\n",
      "Offroad Big Hill Ramp:  An offroad big hill ramp that has two different inclines from the front and back to jump.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',  'OffRoad-BumpySides Start'\nand  'OffRoad-BumpySides'\n\n",
      "Rollercoaster Start/End:  The ramp that starts the Rollercoaster Road and ends it.\nAttaches correctly over and to following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n 'NormalRoad TwistedRight'  and  'Rollercoaster Start/End'\n\n",
      "Rollercoaster Road1\nAttaches correctly to only 'Rollercoaster Start/End', 'Rollercoaster Road2' and itself.\n\n",
      "Rollercoaster Road3\nAttaches correctly to only 'Rollercoaster Road2', 'Rollercoaster Road4' and itself.\n\n",
      "Rollercoaster Road4\nAttaches correctly to only 'Rollercoaster Road3', 'Rollercoaster Road5' and itself.\n\n",
      "Rollercoaster Road2\nAttaches correctly to only 'Rollercoaster Road1', 'Rollercoaster Road3' and itself.\n\n",
      "Rollercoaster Road5\nAttaches correctly to only 'Rollercoaster Road4' and itself.\n\n",
      "Offroad Dirt-Pile:  A dirt pile obstacle that is to be placed anywhere in the middle of the road.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start'  and  'OffRoad-BumpySides'\n\n",
      "Offroad Dirt-Pile:  A dirt pile obstacle that is to be placed anywhere in the middle of the road.\nAttaches correctly over the following other parts :\n\n'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad-BumpySides Start'  and  'OffRoad-BumpySides'\n\n",
      "Checkpoint :  The checkpoint part that ultimately decides how you stage is raced, place carefully with thought.\n(Any stage must have at least two checkpoints to work).\nMounts correctly over the following other parts :\n\n'NormalRoad',  'NormalRoad Turn',  'NormalRoad End',  'NormalRoad Edged',  'NormalRoad TwistedLeft',\n'NormalRoad TwistedRight',  'OffRoad',  'OffRoad Turn',  'OffRoad End',  'OffRoad BumpyGreen',\n'OffRoad-BumpySides Start',  'OffRoad-BumpySides',  'Rollercoaster Start/End'  and  'Rollercoaster Road 2,3,4 and 5'\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Trees/Cactus are decorative stage parts that should be placed outside the race track on the ground and NEVER on any road part or ramp!\nTrees/Cactus are not to be used as obstacles of the race course!\nThey are to be used as out of path ground decoration only.\n\n",
      "Ground Piles are to be paced outside the race track on the ground and NEVER on any road part or ramp!\nThey are to be used as ground decoration and out of race course obstacles (ground obstacles)!\n\n"
   };
   String[] errlo = new String[]{
      "The maximum allocated memory for the stage's part's details has been exerted.\nPlease decrease the amount of parts in the stage that have more details then average.",
      "The maximum amount of road points allowed in the track has been exceeded.\nPlease remove some of the road parts that are in the circler path of the track (the parts that are between the checkpoints).\nOr try to remove some of the extra checkpoints in the track as well.",
      "The maximum allowed area for a track (the area in between its walls) has been exceeded.\nPlease try to place parts only inside the current allowed area, inside the area between the current maximum wall placements.",
      "The maximum number of parts allowed per stage has been exceeded.\nPlease remove some of the already extra parts placed in order to make space.",
      "The maximum number of Fixing Hoops allowed per stage is 5!\nPlease remove the extra Fixing Hoops from your stage to have only 5 main ones left.",
      "Unknown Error, please make sure the stage you are handling is saved correctly.\nPlease go to the 'Build' tab and press 'Save & Preview'.",
      "There needs to be at least 2 checkpoints in the Stage in order for the game to work.\nPlease go to the 'Build' tab and select 'Checkpoint' in the Part Selection menu to add more checkpoints.",
      "The name of the stage is too long!\nPlease go to the 'Stage' tab, click 'Rename Stage' and give your stage a shorter name."
   };
   int[] rcheckp = new int[]{0, 1, 2, 3, 4, 12, 13, 37};
   int[] ocheckp = new int[]{5, 6, 7, 11, 14, 33, 34, 38};
   boolean onoff = false;
   boolean onfly = false;
   int flyh = 0;
   int[] csky = new int[]{170, 220, 255};
   int[] cgrnd = new int[]{205, 200, 200};
   int[] cfade = new int[]{255, 220, 220};
   int[] texture = new int[]{0, 0, 0, 10};
   int[] cldd = new int[]{210, 210, 210, 1, -1000};
   TextField mgen = new TextField("", 10);
   int vxz = 0;
   int vx = 0;
   int vz = 0;
   int vy = 0;
   int dtab = 0;
   int dtabed = -1;
   int mouseon = -1;
   float[][] hsb = new float[][]{{0.5F, 0.875F, 0.5F}, {0.5F, 0.875F, 0.5F}, {0.5F, 0.875F, 0.5F}};
   Checkbox pfog = new Checkbox("Linked Blend");
   int[] snap = new int[]{50, 50, 50};
   int[] fogn = new int[]{60, 0};
   Smenu nlaps = new Smenu(40);
   Smenu tracks = new Smenu(2000);
   String trackname = "";
   String ltrackname = "";
   int trackvol = 200;
   int tracksize = 111;
   RadicalMod track = new RadicalMod();
   int avon = 0;
   Smenu witho = new Smenu(40);
   int logged = 0;
   TextField tnick = new TextField("", 15);
   TextField tpass = new TextField("", 15);
   Smenu pubitem = new Smenu(707);
   Smenu pubtyp = new Smenu(40);
   int nms = 0;
   int roto = 0;
   String[] mystages = new String[20];
   String[] maker = new String[20];
   int[] pubt = new int[20];
   String[][] addeda = new String[20][5000];
   int[] nad = new int[20];
   String justpubd = "";
   boolean[] pessd = new boolean[]{
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false
   };
   int[] bx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   int[] by = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   int[] bw = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   int btn = 0;
   int mouses = 0;
   int xm = 0;
   int ym = 0;
   int lxm = 0;
   int lym = 0;
   int cntout = 0;
   boolean preop = false;
   boolean mousdr = false;
   String ttstage = "";

   @Override
   public void run() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 0000: aload 0
      // 0001: getfield StageMaker.thredo Ljava/lang/Thread;
      // 0004: bipush 10
      // 0006: invokevirtual java/lang/Thread.setPriority (I)V
      // 0009: aload 0
      // 000a: getfield StageMaker.btgame [Ljava/awt/Image;
      // 000d: bipush 0
      // 000e: aload 0
      // 000f: new java/lang/StringBuilder
      // 0012: dup
      // 0013: invokespecial java/lang/StringBuilder.<init> ()V
      // 0016: ldc ""
      // 0018: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 001b: getstatic Madness.fpath Ljava/lang/String;
      // 001e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0021: ldc "data/backtogame1.gif"
      // 0023: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0026: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0029: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 002c: aastore
      // 002d: aload 0
      // 002e: getfield StageMaker.btgame [Ljava/awt/Image;
      // 0031: bipush 1
      // 0032: aload 0
      // 0033: new java/lang/StringBuilder
      // 0036: dup
      // 0037: invokespecial java/lang/StringBuilder.<init> ()V
      // 003a: ldc ""
      // 003c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 003f: getstatic Madness.fpath Ljava/lang/String;
      // 0042: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0045: ldc "data/backtogame2.gif"
      // 0047: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 004a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 004d: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 0050: aastore
      // 0051: aload 0
      // 0052: aload 0
      // 0053: new java/lang/StringBuilder
      // 0056: dup
      // 0057: invokespecial java/lang/StringBuilder.<init> ()V
      // 005a: ldc ""
      // 005c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 005f: getstatic Madness.fpath Ljava/lang/String;
      // 0062: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0065: ldc "data/stagemakerlogo.gif"
      // 0067: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 006a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 006d: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 0070: putfield StageMaker.logo Ljava/awt/Image;
      // 0073: bipush 0
      // 0074: istore 1
      // 0075: iload 1
      // 0076: bipush 2
      // 0077: if_icmplt 007f
      // 007a: goto 01a1
      // 007f: aload 0
      // 0080: getfield StageMaker.su [Ljava/awt/Image;
      // 0083: iload 1
      // 0084: aload 0
      // 0085: new java/lang/StringBuilder
      // 0088: dup
      // 0089: invokespecial java/lang/StringBuilder.<init> ()V
      // 008c: ldc ""
      // 008e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0091: getstatic Madness.fpath Ljava/lang/String;
      // 0094: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0097: ldc "data/su"
      // 0099: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 009c: iload 1
      // 009d: bipush 1
      // 009e: iadd
      // 009f: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 00a2: ldc ".gif"
      // 00a4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00a7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 00aa: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 00ad: aastore
      // 00ae: aload 0
      // 00af: getfield StageMaker.sl [Ljava/awt/Image;
      // 00b2: iload 1
      // 00b3: aload 0
      // 00b4: new java/lang/StringBuilder
      // 00b7: dup
      // 00b8: invokespecial java/lang/StringBuilder.<init> ()V
      // 00bb: ldc ""
      // 00bd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00c0: getstatic Madness.fpath Ljava/lang/String;
      // 00c3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00c6: ldc "data/sl"
      // 00c8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00cb: iload 1
      // 00cc: bipush 1
      // 00cd: iadd
      // 00ce: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 00d1: ldc ".gif"
      // 00d3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00d6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 00d9: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 00dc: aastore
      // 00dd: aload 0
      // 00de: getfield StageMaker.sd [Ljava/awt/Image;
      // 00e1: iload 1
      // 00e2: aload 0
      // 00e3: new java/lang/StringBuilder
      // 00e6: dup
      // 00e7: invokespecial java/lang/StringBuilder.<init> ()V
      // 00ea: ldc ""
      // 00ec: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00ef: getstatic Madness.fpath Ljava/lang/String;
      // 00f2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00f5: ldc "data/sd"
      // 00f7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00fa: iload 1
      // 00fb: bipush 1
      // 00fc: iadd
      // 00fd: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 0100: ldc ".gif"
      // 0102: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0105: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0108: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 010b: aastore
      // 010c: aload 0
      // 010d: getfield StageMaker.sr [Ljava/awt/Image;
      // 0110: iload 1
      // 0111: aload 0
      // 0112: new java/lang/StringBuilder
      // 0115: dup
      // 0116: invokespecial java/lang/StringBuilder.<init> ()V
      // 0119: ldc ""
      // 011b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 011e: getstatic Madness.fpath Ljava/lang/String;
      // 0121: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0124: ldc "data/sr"
      // 0126: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0129: iload 1
      // 012a: bipush 1
      // 012b: iadd
      // 012c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 012f: ldc ".gif"
      // 0131: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0134: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0137: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 013a: aastore
      // 013b: aload 0
      // 013c: getfield StageMaker.zi [Ljava/awt/Image;
      // 013f: iload 1
      // 0140: aload 0
      // 0141: new java/lang/StringBuilder
      // 0144: dup
      // 0145: invokespecial java/lang/StringBuilder.<init> ()V
      // 0148: ldc ""
      // 014a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 014d: getstatic Madness.fpath Ljava/lang/String;
      // 0150: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0153: ldc "data/zi"
      // 0155: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0158: iload 1
      // 0159: bipush 1
      // 015a: iadd
      // 015b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 015e: ldc ".gif"
      // 0160: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0163: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0166: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 0169: aastore
      // 016a: aload 0
      // 016b: getfield StageMaker.zo [Ljava/awt/Image;
      // 016e: iload 1
      // 016f: aload 0
      // 0170: new java/lang/StringBuilder
      // 0173: dup
      // 0174: invokespecial java/lang/StringBuilder.<init> ()V
      // 0177: ldc ""
      // 0179: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 017c: getstatic Madness.fpath Ljava/lang/String;
      // 017f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0182: ldc "data/zo"
      // 0184: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0187: iload 1
      // 0188: bipush 1
      // 0189: iadd
      // 018a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 018d: ldc ".gif"
      // 018f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0192: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0195: invokevirtual StageMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 0198: aastore
      // 0199: iinc 1 1
      // 019c: goto 0075
      // 01a1: aload 0
      // 01a2: invokevirtual StageMaker.loadbase ()V
      // 01a5: aload 0
      // 01a6: invokevirtual StageMaker.loadsettings ()V
      // 01a9: getstatic Madness.testdrive I
      // 01ac: ifne 01b4
      // 01af: goto 0221
      // 01b4: getstatic Madness.testcar Ljava/lang/String;
      // 01b7: ldc "Failx12"
      // 01b9: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 01bc: ifne 01c4
      // 01bf: goto 01da
      // 01c4: aconst_null
      // 01c5: ldc "Failed to load stage! Please make sure stage is saved properly before Test Drive."
      // 01c7: ldc_w "Stage Maker"
      // 01ca: bipush 1
      // 01cb: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 01ce: aload 0
      // 01cf: getfield StageMaker.thredo Ljava/lang/Thread;
      // 01d2: invokevirtual java/lang/Thread.stop ()V
      // 01d5: goto 0213
      // 01da: aload 0
      // 01db: getstatic Madness.testcar Ljava/lang/String;
      // 01de: putfield StageMaker.stagename Ljava/lang/String;
      // 01e1: aload 0
      // 01e2: bipush 0
      // 01e3: putfield StageMaker.errd I
      // 01e6: aload 0
      // 01e7: bipush 3
      // 01e8: invokevirtual StageMaker.readstage (I)V
      // 01eb: aload 0
      // 01ec: getfield StageMaker.errd I
      // 01ef: ifeq 01f7
      // 01f2: goto 0213
      // 01f7: aload 0
      // 01f8: bipush 2
      // 01f9: putfield StageMaker.tab I
      // 01fc: aload 0
      // 01fd: bipush 6
      // 01ff: putfield StageMaker.dtab I
      // 0202: aload 0
      // 0203: getfield StageMaker.witho LSmenu;
      // 0206: getstatic Madness.testdrive I
      // 0209: bipush 3
      // 020a: isub
      // 020b: invokevirtual Smenu.select (I)V
      // 020e: goto 0213
      // 0213: ldc ""
      // 0215: putstatic Madness.testcar Ljava/lang/String;
      // 0218: bipush 0
      // 0219: putstatic Madness.testdrive I
      // 021c: goto 0221
      // 0221: aload 0
      // 0222: invokevirtual StageMaker.requestFocus ()V
      // 0225: aload 0
      // 0226: getfield StageMaker.exwist Z
      // 0229: ifeq 0231
      // 022c: goto adeb
      // 0231: aload 0
      // 0232: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0235: new java/awt/Color
      // 0238: dup
      // 0239: sipush 225
      // 023c: sipush 225
      // 023f: sipush 225
      // 0242: invokespecial java/awt/Color.<init> (III)V
      // 0245: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0248: aload 0
      // 0249: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 024c: bipush 0
      // 024d: bipush 25
      // 024f: sipush 800
      // 0252: sipush 525
      // 0255: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 0258: aload 0
      // 0259: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 025c: new java/awt/Color
      // 025f: dup
      // 0260: bipush 0
      // 0261: bipush 0
      // 0262: bipush 0
      // 0263: invokespecial java/awt/Color.<init> (III)V
      // 0266: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0269: aload 0
      // 026a: getfield StageMaker.tab I
      // 026d: aload 0
      // 026e: getfield StageMaker.tabed I
      // 0271: if_icmpne 0279
      // 0274: goto 0282
      // 0279: aload 0
      // 027a: invokevirtual StageMaker.hidefields ()V
      // 027d: goto 0282
      // 0282: aload 0
      // 0283: getfield StageMaker.tab I
      // 0286: ifeq 028e
      // 0289: goto 0bfc
      // 028e: aload 0
      // 028f: getfield StageMaker.tabed I
      // 0292: aload 0
      // 0293: getfield StageMaker.tab I
      // 0296: if_icmpne 029e
      // 0299: goto 03b2
      // 029e: aload 0
      // 029f: getfield StageMaker.slstage LSmenu;
      // 02a2: invokevirtual Smenu.removeAll ()V
      // 02a5: aload 0
      // 02a6: getfield StageMaker.slstage LSmenu;
      // 02a9: sipush 360
      // 02ac: putfield Smenu.maxl I
      // 02af: aload 0
      // 02b0: getfield StageMaker.slstage LSmenu;
      // 02b3: aload 0
      // 02b4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 02b7: ldc_w "Select a Stage                      "
      // 02ba: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 02bd: new java/io/File
      // 02c0: dup
      // 02c1: new java/lang/StringBuilder
      // 02c4: dup
      // 02c5: invokespecial java/lang/StringBuilder.<init> ()V
      // 02c8: ldc ""
      // 02ca: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02cd: getstatic Madness.fpath Ljava/lang/String;
      // 02d0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02d3: ldc_w "mystages/"
      // 02d6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02d9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 02dc: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 02df: invokevirtual java/io/File.list ()[Ljava/lang/String;
      // 02e2: astore 1
      // 02e3: aload 1
      // 02e4: ifnonnull 02ec
      // 02e7: goto 0339
      // 02ec: bipush 0
      // 02ed: istore 2
      // 02ee: iload 2
      // 02ef: aload 1
      // 02f0: arraylength
      // 02f1: if_icmplt 02f9
      // 02f4: goto 0334
      // 02f9: aload 1
      // 02fa: iload 2
      // 02fb: aaload
      // 02fc: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 02ff: ldc_w ".txt"
      // 0302: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 0305: ifne 030d
      // 0308: goto 032c
      // 030d: aload 0
      // 030e: getfield StageMaker.slstage LSmenu;
      // 0311: aload 0
      // 0312: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0315: aload 1
      // 0316: iload 2
      // 0317: aaload
      // 0318: bipush 0
      // 0319: aload 1
      // 031a: iload 2
      // 031b: aaload
      // 031c: invokevirtual java/lang/String.length ()I
      // 031f: bipush 4
      // 0320: isub
      // 0321: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 0324: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 0327: goto 032c
      // 032c: iinc 2 1
      // 032f: goto 02ee
      // 0334: goto 0339
      // 0339: aload 0
      // 033a: getfield StageMaker.stagename Ljava/lang/String;
      // 033d: ldc ""
      // 033f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0342: ifne 034a
      // 0345: goto 0357
      // 034a: aload 0
      // 034b: getfield StageMaker.slstage LSmenu;
      // 034e: bipush 0
      // 034f: invokevirtual Smenu.select (I)V
      // 0352: goto 03a3
      // 0357: aload 0
      // 0358: getfield StageMaker.slstage LSmenu;
      // 035b: aload 0
      // 035c: getfield StageMaker.stagename Ljava/lang/String;
      // 035f: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 0362: aload 0
      // 0363: getfield StageMaker.stagename Ljava/lang/String;
      // 0366: aload 0
      // 0367: getfield StageMaker.slstage LSmenu;
      // 036a: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 036d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0370: ifne 0378
      // 0373: goto 0395
      // 0378: aload 0
      // 0379: bipush 3
      // 037a: invokevirtual StageMaker.readstage (I)V
      // 037d: aload 0
      // 037e: bipush 0
      // 037f: putfield StageMaker.sx I
      // 0382: aload 0
      // 0383: sipush 1500
      // 0386: putfield StageMaker.sz I
      // 0389: aload 0
      // 038a: sipush -10000
      // 038d: putfield StageMaker.sy I
      // 0390: goto 03a3
      // 0395: aload 0
      // 0396: ldc ""
      // 0398: putfield StageMaker.stagename Ljava/lang/String;
      // 039b: aload 0
      // 039c: getfield StageMaker.slstage LSmenu;
      // 039f: bipush 0
      // 03a0: invokevirtual Smenu.select (I)V
      // 03a3: aload 0
      // 03a4: bipush -1
      // 03a5: putfield StageMaker.mouseon I
      // 03a8: aload 0
      // 03a9: bipush 0
      // 03aa: putfield StageMaker.sfase I
      // 03ad: goto 03b2
      // 03b2: aload 0
      // 03b3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 03b6: aload 0
      // 03b7: getfield StageMaker.logo Ljava/awt/Image;
      // 03ba: sipush 261
      // 03bd: bipush 35
      // 03bf: aconst_null
      // 03c0: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 03c3: pop
      // 03c4: aload 0
      // 03c5: getfield StageMaker.xm I
      // 03c8: sipush 261
      // 03cb: if_icmpgt 03d3
      // 03ce: goto 0422
      // 03d3: aload 0
      // 03d4: getfield StageMaker.xm I
      // 03d7: sipush 538
      // 03da: if_icmplt 03e2
      // 03dd: goto 0422
      // 03e2: aload 0
      // 03e3: getfield StageMaker.ym I
      // 03e6: bipush 35
      // 03e8: if_icmpgt 03f0
      // 03eb: goto 0422
      // 03f0: aload 0
      // 03f1: getfield StageMaker.ym I
      // 03f4: bipush 121
      // 03f6: if_icmplt 03fe
      // 03f9: goto 0422
      // 03fe: aload 0
      // 03ff: getfield StageMaker.mouseon I
      // 0402: bipush -1
      // 0403: if_icmpeq 040b
      // 0406: goto 0445
      // 040b: aload 0
      // 040c: bipush 3
      // 040d: putfield StageMaker.mouseon I
      // 0410: aload 0
      // 0411: new java/awt/Cursor
      // 0414: dup
      // 0415: bipush 12
      // 0417: invokespecial java/awt/Cursor.<init> (I)V
      // 041a: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 041d: goto 0445
      // 0422: aload 0
      // 0423: getfield StageMaker.mouseon I
      // 0426: bipush 3
      // 0427: if_icmpeq 042f
      // 042a: goto 0445
      // 042f: aload 0
      // 0430: bipush -1
      // 0431: putfield StageMaker.mouseon I
      // 0434: aload 0
      // 0435: new java/awt/Cursor
      // 0438: dup
      // 0439: bipush 0
      // 043a: invokespecial java/awt/Cursor.<init> (I)V
      // 043d: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 0440: goto 0445
      // 0445: aload 0
      // 0446: getfield StageMaker.mouseon I
      // 0449: bipush 3
      // 044a: if_icmpeq 0452
      // 044d: goto 0468
      // 0452: aload 0
      // 0453: getfield StageMaker.mouses I
      // 0456: bipush -1
      // 0457: if_icmpeq 045f
      // 045a: goto 0468
      // 045f: aload 0
      // 0460: invokevirtual StageMaker.openhlink ()V
      // 0463: goto 0468
      // 0468: aload 0
      // 0469: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 046c: new java/awt/Font
      // 046f: dup
      // 0470: ldc_w "Arial"
      // 0473: bipush 1
      // 0474: bipush 13
      // 0476: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0479: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 047c: aload 0
      // 047d: aload 0
      // 047e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0481: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0484: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 0487: aload 0
      // 0488: getfield StageMaker.xm I
      // 048b: sipush 200
      // 048e: if_icmpgt 0496
      // 0491: goto 04e7
      // 0496: aload 0
      // 0497: getfield StageMaker.xm I
      // 049a: sipush 550
      // 049d: if_icmplt 04a5
      // 04a0: goto 04e7
      // 04a5: aload 0
      // 04a6: getfield StageMaker.ym I
      // 04a9: sipush 467
      // 04ac: if_icmpgt 04b4
      // 04af: goto 04e7
      // 04b4: aload 0
      // 04b5: getfield StageMaker.ym I
      // 04b8: sipush 504
      // 04bb: if_icmplt 04c3
      // 04be: goto 04e7
      // 04c3: aload 0
      // 04c4: getfield StageMaker.mouseon I
      // 04c7: bipush -1
      // 04c8: if_icmpeq 04d0
      // 04cb: goto 050a
      // 04d0: aload 0
      // 04d1: bipush 2
      // 04d2: putfield StageMaker.mouseon I
      // 04d5: aload 0
      // 04d6: new java/awt/Cursor
      // 04d9: dup
      // 04da: bipush 12
      // 04dc: invokespecial java/awt/Cursor.<init> (I)V
      // 04df: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 04e2: goto 050a
      // 04e7: aload 0
      // 04e8: getfield StageMaker.mouseon I
      // 04eb: bipush 2
      // 04ec: if_icmpeq 04f4
      // 04ef: goto 050a
      // 04f4: aload 0
      // 04f5: bipush -1
      // 04f6: putfield StageMaker.mouseon I
      // 04f9: aload 0
      // 04fa: new java/awt/Cursor
      // 04fd: dup
      // 04fe: bipush 0
      // 04ff: invokespecial java/awt/Cursor.<init> (I)V
      // 0502: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 0505: goto 050a
      // 050a: aload 0
      // 050b: getfield StageMaker.mouseon I
      // 050e: bipush 2
      // 050f: if_icmpeq 0517
      // 0512: goto 0530
      // 0517: aload 0
      // 0518: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 051b: new java/awt/Color
      // 051e: dup
      // 051f: bipush 0
      // 0520: bipush 64
      // 0522: sipush 128
      // 0525: invokespecial java/awt/Color.<init> (III)V
      // 0528: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 052b: goto 0541
      // 0530: aload 0
      // 0531: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0534: new java/awt/Color
      // 0537: dup
      // 0538: bipush 0
      // 0539: bipush 0
      // 053a: bipush 0
      // 053b: invokespecial java/awt/Color.<init> (III)V
      // 053e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0541: aload 0
      // 0542: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0545: ldc_w "For the Stage Maker Homepage, Development Center and Forums :"
      // 0548: sipush 400
      // 054b: aload 0
      // 054c: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 054f: ldc_w "For the Stage Maker Homepage, Development Center and Forums :"
      // 0552: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0555: bipush 2
      // 0556: idiv
      // 0557: isub
      // 0558: sipush 480
      // 055b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 055e: aload 0
      // 055f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0562: new java/awt/Color
      // 0565: dup
      // 0566: bipush 0
      // 0567: sipush 128
      // 056a: sipush 255
      // 056d: invokespecial java/awt/Color.<init> (III)V
      // 0570: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0573: ldc_w "http://www.needformadness.com/developer/"
      // 0576: astore 1
      // 0577: aload 0
      // 0578: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 057b: aload 1
      // 057c: sipush 400
      // 057f: aload 0
      // 0580: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 0583: aload 1
      // 0584: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0587: bipush 2
      // 0588: idiv
      // 0589: isub
      // 058a: sipush 500
      // 058d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0590: aload 0
      // 0591: getfield StageMaker.mouseon I
      // 0594: bipush 2
      // 0595: if_icmpeq 059d
      // 0598: goto 05b7
      // 059d: aload 0
      // 059e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 05a1: new java/awt/Color
      // 05a4: dup
      // 05a5: bipush 0
      // 05a6: sipush 128
      // 05a9: sipush 255
      // 05ac: invokespecial java/awt/Color.<init> (III)V
      // 05af: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05b2: goto 05cb
      // 05b7: aload 0
      // 05b8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 05bb: new java/awt/Color
      // 05be: dup
      // 05bf: bipush 0
      // 05c0: bipush 64
      // 05c2: sipush 128
      // 05c5: invokespecial java/awt/Color.<init> (III)V
      // 05c8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05cb: aload 0
      // 05cc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 05cf: sipush 400
      // 05d2: aload 0
      // 05d3: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 05d6: aload 1
      // 05d7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 05da: bipush 2
      // 05db: idiv
      // 05dc: isub
      // 05dd: sipush 501
      // 05e0: sipush 400
      // 05e3: aload 0
      // 05e4: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 05e7: aload 1
      // 05e8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 05eb: bipush 2
      // 05ec: idiv
      // 05ed: iadd
      // 05ee: sipush 501
      // 05f1: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 05f4: aload 0
      // 05f5: getfield StageMaker.mouseon I
      // 05f8: bipush 2
      // 05f9: if_icmpeq 0601
      // 05fc: goto 0617
      // 0601: aload 0
      // 0602: getfield StageMaker.mouses I
      // 0605: bipush -1
      // 0606: if_icmpeq 060e
      // 0609: goto 0617
      // 060e: aload 0
      // 060f: invokevirtual StageMaker.openhlink ()V
      // 0612: goto 0617
      // 0617: bipush -110
      // 0619: istore 2
      // 061a: aload 0
      // 061b: getfield StageMaker.xm I
      // 061e: sipush 150
      // 0621: if_icmpgt 0629
      // 0624: goto 067e
      // 0629: aload 0
      // 062a: getfield StageMaker.xm I
      // 062d: sipush 600
      // 0630: if_icmplt 0638
      // 0633: goto 067e
      // 0638: aload 0
      // 0639: getfield StageMaker.ym I
      // 063c: sipush 467
      // 063f: iload 2
      // 0640: iadd
      // 0641: if_icmpgt 0649
      // 0644: goto 067e
      // 0649: aload 0
      // 064a: getfield StageMaker.ym I
      // 064d: sipush 504
      // 0650: iload 2
      // 0651: iadd
      // 0652: if_icmplt 065a
      // 0655: goto 067e
      // 065a: aload 0
      // 065b: getfield StageMaker.mouseon I
      // 065e: bipush -1
      // 065f: if_icmpeq 0667
      // 0662: goto 06a1
      // 0667: aload 0
      // 0668: bipush 1
      // 0669: putfield StageMaker.mouseon I
      // 066c: aload 0
      // 066d: new java/awt/Cursor
      // 0670: dup
      // 0671: bipush 12
      // 0673: invokespecial java/awt/Cursor.<init> (I)V
      // 0676: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 0679: goto 06a1
      // 067e: aload 0
      // 067f: getfield StageMaker.mouseon I
      // 0682: bipush 1
      // 0683: if_icmpeq 068b
      // 0686: goto 06a1
      // 068b: aload 0
      // 068c: bipush -1
      // 068d: putfield StageMaker.mouseon I
      // 0690: aload 0
      // 0691: new java/awt/Cursor
      // 0694: dup
      // 0695: bipush 0
      // 0696: invokespecial java/awt/Cursor.<init> (I)V
      // 0699: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 069c: goto 06a1
      // 06a1: aload 0
      // 06a2: getfield StageMaker.mouseon I
      // 06a5: bipush 1
      // 06a6: if_icmpeq 06ae
      // 06a9: goto 06c7
      // 06ae: aload 0
      // 06af: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 06b2: new java/awt/Color
      // 06b5: dup
      // 06b6: bipush 0
      // 06b7: bipush 64
      // 06b9: sipush 128
      // 06bc: invokespecial java/awt/Color.<init> (III)V
      // 06bf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 06c2: goto 06d8
      // 06c7: aload 0
      // 06c8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 06cb: new java/awt/Color
      // 06ce: dup
      // 06cf: bipush 0
      // 06d0: bipush 0
      // 06d1: bipush 0
      // 06d2: invokespecial java/awt/Color.<init> (III)V
      // 06d5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 06d8: aload 0
      // 06d9: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 06dc: ldc_w "For help and a detailed step by step description on how to use the Stage Maker :"
      // 06df: sipush 400
      // 06e2: aload 0
      // 06e3: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 06e6: ldc_w "For help and a detailed step by step description on how to use the Stage Maker :"
      // 06e9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 06ec: bipush 2
      // 06ed: idiv
      // 06ee: isub
      // 06ef: sipush 480
      // 06f2: iload 2
      // 06f3: iadd
      // 06f4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 06f7: aload 0
      // 06f8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 06fb: new java/awt/Color
      // 06fe: dup
      // 06ff: bipush 0
      // 0700: sipush 128
      // 0703: sipush 255
      // 0706: invokespecial java/awt/Color.<init> (III)V
      // 0709: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 070c: ldc_w "http://www.needformadness.com/developer/help.html"
      // 070f: astore 1
      // 0710: aload 0
      // 0711: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0714: aload 1
      // 0715: sipush 400
      // 0718: aload 0
      // 0719: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 071c: aload 1
      // 071d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0720: bipush 2
      // 0721: idiv
      // 0722: isub
      // 0723: sipush 500
      // 0726: iload 2
      // 0727: iadd
      // 0728: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 072b: aload 0
      // 072c: getfield StageMaker.mouseon I
      // 072f: bipush 1
      // 0730: if_icmpeq 0738
      // 0733: goto 0752
      // 0738: aload 0
      // 0739: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 073c: new java/awt/Color
      // 073f: dup
      // 0740: bipush 0
      // 0741: sipush 128
      // 0744: sipush 255
      // 0747: invokespecial java/awt/Color.<init> (III)V
      // 074a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 074d: goto 0766
      // 0752: aload 0
      // 0753: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0756: new java/awt/Color
      // 0759: dup
      // 075a: bipush 0
      // 075b: bipush 64
      // 075d: sipush 128
      // 0760: invokespecial java/awt/Color.<init> (III)V
      // 0763: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0766: aload 0
      // 0767: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 076a: sipush 400
      // 076d: aload 0
      // 076e: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 0771: aload 1
      // 0772: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0775: bipush 2
      // 0776: idiv
      // 0777: isub
      // 0778: sipush 501
      // 077b: iload 2
      // 077c: iadd
      // 077d: sipush 400
      // 0780: aload 0
      // 0781: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 0784: aload 1
      // 0785: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0788: bipush 2
      // 0789: idiv
      // 078a: iadd
      // 078b: sipush 501
      // 078e: iload 2
      // 078f: iadd
      // 0790: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0793: aload 0
      // 0794: getfield StageMaker.mouseon I
      // 0797: bipush 1
      // 0798: if_icmpeq 07a0
      // 079b: goto 07b6
      // 07a0: aload 0
      // 07a1: getfield StageMaker.mouses I
      // 07a4: bipush -1
      // 07a5: if_icmpeq 07ad
      // 07a8: goto 07b6
      // 07ad: aload 0
      // 07ae: invokevirtual StageMaker.openlink ()V
      // 07b1: goto 07b6
      // 07b6: bipush -60
      // 07b8: istore 3
      // 07b9: bipush 70
      // 07bb: istore 4
      // 07bd: aload 0
      // 07be: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 07c1: new java/awt/Color
      // 07c4: dup
      // 07c5: bipush 0
      // 07c6: bipush 0
      // 07c7: bipush 0
      // 07c8: invokespecial java/awt/Color.<init> (III)V
      // 07cb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 07ce: aload 0
      // 07cf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 07d2: sipush 227
      // 07d5: iload 4
      // 07d7: isub
      // 07d8: sipush 194
      // 07db: iload 3
      // 07dc: iadd
      // 07dd: sipush 346
      // 07e0: iload 4
      // 07e2: bipush 2
      // 07e3: imul
      // 07e4: iadd
      // 07e5: sipush 167
      // 07e8: iload 4
      // 07ea: bipush 5
      // 07eb: idiv
      // 07ec: iadd
      // 07ed: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 07f0: aload 0
      // 07f1: getfield StageMaker.sfase I
      // 07f4: ifeq 07fc
      // 07f7: goto 09c1
      // 07fc: aload 0
      // 07fd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0800: ldc_w "Select Stage to Edit"
      // 0803: sipush 400
      // 0806: aload 0
      // 0807: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 080a: ldc_w "Select Stage to Edit"
      // 080d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0810: bipush 2
      // 0811: idiv
      // 0812: isub
      // 0813: sipush 230
      // 0816: iload 3
      // 0817: iadd
      // 0818: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 081b: aload 0
      // 081c: getfield StageMaker.slstage LSmenu;
      // 081f: sipush 220
      // 0822: sipush 240
      // 0825: iload 3
      // 0826: iadd
      // 0827: invokevirtual Smenu.move (II)V
      // 082a: aload 0
      // 082b: getfield StageMaker.slstage LSmenu;
      // 082e: invokevirtual Smenu.getWidth ()I
      // 0831: sipush 360
      // 0834: if_icmpne 083c
      // 0837: goto 084d
      // 083c: aload 0
      // 083d: getfield StageMaker.slstage LSmenu;
      // 0840: sipush 360
      // 0843: bipush 21
      // 0845: invokevirtual Smenu.setSize (II)V
      // 0848: goto 084d
      // 084d: aload 0
      // 084e: getfield StageMaker.slstage LSmenu;
      // 0851: invokevirtual Smenu.isShowing ()Z
      // 0854: ifeq 085c
      // 0857: goto 0868
      // 085c: aload 0
      // 085d: getfield StageMaker.slstage LSmenu;
      // 0860: invokevirtual Smenu.show ()V
      // 0863: goto 0868
      // 0868: aload 0
      // 0869: ldc_w "    Make new Stage    "
      // 086c: sipush 400
      // 086f: sipush 296
      // 0872: iload 3
      // 0873: iadd
      // 0874: bipush 0
      // 0875: bipush 1
      // 0876: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0879: ifne 0881
      // 087c: goto 089b
      // 0881: aload 0
      // 0882: getfield StageMaker.srch Ljava/awt/TextField;
      // 0885: ldc ""
      // 0887: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 088a: aload 0
      // 088b: getfield StageMaker.slstage LSmenu;
      // 088e: invokevirtual Smenu.hide ()V
      // 0891: aload 0
      // 0892: bipush 1
      // 0893: putfield StageMaker.sfase I
      // 0896: goto 089b
      // 089b: aload 0
      // 089c: ldc_w "     Rename Stage     "
      // 089f: sipush 325
      // 08a2: sipush 336
      // 08a5: iload 3
      // 08a6: iadd
      // 08a7: bipush 0
      // 08a8: bipush 0
      // 08a9: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 08ac: ifne 08b4
      // 08af: goto 08f1
      // 08b4: aload 0
      // 08b5: getfield StageMaker.stagename Ljava/lang/String;
      // 08b8: ldc ""
      // 08ba: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 08bd: ifeq 08c5
      // 08c0: goto 08e1
      // 08c5: aload 0
      // 08c6: getfield StageMaker.slstage LSmenu;
      // 08c9: invokevirtual Smenu.hide ()V
      // 08cc: aload 0
      // 08cd: getfield StageMaker.srch Ljava/awt/TextField;
      // 08d0: aload 0
      // 08d1: getfield StageMaker.stagename Ljava/lang/String;
      // 08d4: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 08d7: aload 0
      // 08d8: bipush 2
      // 08d9: putfield StageMaker.sfase I
      // 08dc: goto 08f1
      // 08e1: aconst_null
      // 08e2: ldc_w "Please select a stage to rename first."
      // 08e5: ldc_w "Stage Maker"
      // 08e8: bipush 1
      // 08e9: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 08ec: goto 08f1
      // 08f1: aload 0
      // 08f2: ldc_w "      Delete Stage      "
      // 08f5: sipush 475
      // 08f8: sipush 336
      // 08fb: iload 3
      // 08fc: iadd
      // 08fd: bipush 0
      // 08fe: bipush 0
      // 08ff: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0902: ifne 090a
      // 0905: goto 0965
      // 090a: aload 0
      // 090b: getfield StageMaker.stagename Ljava/lang/String;
      // 090e: ldc ""
      // 0910: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0913: ifeq 091b
      // 0916: goto 0955
      // 091b: aconst_null
      // 091c: new java/lang/StringBuilder
      // 091f: dup
      // 0920: invokespecial java/lang/StringBuilder.<init> ()V
      // 0923: ldc_w "Are you sure you want to permanently delete this stage?\n\n"
      // 0926: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0929: aload 0
      // 092a: getfield StageMaker.stagename Ljava/lang/String;
      // 092d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0930: ldc_w "\n\n"
      // 0933: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0936: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0939: ldc_w "Stage Maker"
      // 093c: bipush 0
      // 093d: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 0940: ifeq 0948
      // 0943: goto 0965
      // 0948: aload 0
      // 0949: aload 0
      // 094a: getfield StageMaker.stagename Ljava/lang/String;
      // 094d: invokevirtual StageMaker.delstage (Ljava/lang/String;)V
      // 0950: goto 0965
      // 0955: aconst_null
      // 0956: ldc_w "Please select a stage to delete first."
      // 0959: ldc_w "Stage Maker"
      // 095c: bipush 1
      // 095d: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 0960: goto 0965
      // 0965: aload 0
      // 0966: getfield StageMaker.slstage LSmenu;
      // 0969: invokevirtual Smenu.getSelectedIndex ()I
      // 096c: ifne 0974
      // 096f: goto 09b6
      // 0974: aload 0
      // 0975: getfield StageMaker.stagename Ljava/lang/String;
      // 0978: aload 0
      // 0979: getfield StageMaker.slstage LSmenu;
      // 097c: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 097f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0982: ifeq 098a
      // 0985: goto 09c1
      // 098a: aload 0
      // 098b: aload 0
      // 098c: getfield StageMaker.slstage LSmenu;
      // 098f: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0992: putfield StageMaker.stagename Ljava/lang/String;
      // 0995: aload 0
      // 0996: bipush 3
      // 0997: invokevirtual StageMaker.readstage (I)V
      // 099a: aload 0
      // 099b: bipush 0
      // 099c: putfield StageMaker.sx I
      // 099f: aload 0
      // 09a0: sipush 1500
      // 09a3: putfield StageMaker.sz I
      // 09a6: aload 0
      // 09a7: sipush -10000
      // 09aa: putfield StageMaker.sy I
      // 09ad: aload 0
      // 09ae: invokevirtual StageMaker.requestFocus ()V
      // 09b1: goto 09c1
      // 09b6: aload 0
      // 09b7: ldc ""
      // 09b9: putfield StageMaker.stagename Ljava/lang/String;
      // 09bc: goto 09c1
      // 09c1: aload 0
      // 09c2: getfield StageMaker.sfase I
      // 09c5: bipush 1
      // 09c6: if_icmpeq 09ce
      // 09c9: goto 0ae1
      // 09ce: aload 0
      // 09cf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 09d2: ldc_w "Make a new Stage"
      // 09d5: sipush 400
      // 09d8: aload 0
      // 09d9: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 09dc: ldc_w "Make a new Stage"
      // 09df: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 09e2: bipush 2
      // 09e3: idiv
      // 09e4: isub
      // 09e5: sipush 220
      // 09e8: iload 3
      // 09e9: iadd
      // 09ea: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 09ed: aload 0
      // 09ee: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 09f1: new java/awt/Font
      // 09f4: dup
      // 09f5: ldc_w "Arial"
      // 09f8: bipush 1
      // 09f9: bipush 12
      // 09fb: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 09fe: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0a01: aload 0
      // 0a02: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0a05: ldc_w "New stage name :"
      // 0a08: sipush 200
      // 0a0b: sipush 246
      // 0a0e: iload 3
      // 0a0f: iadd
      // 0a10: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0a13: aload 0
      // 0a14: aload 0
      // 0a15: getfield StageMaker.srch Ljava/awt/TextField;
      // 0a18: sipush 310
      // 0a1b: sipush 231
      // 0a1e: iload 3
      // 0a1f: iadd
      // 0a20: sipush 290
      // 0a23: bipush 23
      // 0a25: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // 0a28: aload 0
      // 0a29: getfield StageMaker.srch Ljava/awt/TextField;
      // 0a2c: invokevirtual java/awt/TextField.isShowing ()Z
      // 0a2f: ifeq 0a37
      // 0a32: goto 0a4a
      // 0a37: aload 0
      // 0a38: getfield StageMaker.srch Ljava/awt/TextField;
      // 0a3b: invokevirtual java/awt/TextField.show ()V
      // 0a3e: aload 0
      // 0a3f: getfield StageMaker.srch Ljava/awt/TextField;
      // 0a42: invokevirtual java/awt/TextField.requestFocus ()V
      // 0a45: goto 0a4a
      // 0a4a: aload 0
      // 0a4b: aload 0
      // 0a4c: getfield StageMaker.srch Ljava/awt/TextField;
      // 0a4f: invokevirtual StageMaker.fixtext (Ljava/awt/TextField;)V
      // 0a52: aload 0
      // 0a53: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0a56: ldc_w "Starting line type :"
      // 0a59: sipush 293
      // 0a5c: sipush 272
      // 0a5f: iload 3
      // 0a60: iadd
      // 0a61: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0a64: aload 0
      // 0a65: getfield StageMaker.strtyp LSmenu;
      // 0a68: sipush 408
      // 0a6b: sipush 256
      // 0a6e: iload 3
      // 0a6f: iadd
      // 0a70: invokevirtual Smenu.move (II)V
      // 0a73: aload 0
      // 0a74: getfield StageMaker.strtyp LSmenu;
      // 0a77: invokevirtual Smenu.isShowing ()Z
      // 0a7a: ifeq 0a82
      // 0a7d: goto 0a8e
      // 0a82: aload 0
      // 0a83: getfield StageMaker.strtyp LSmenu;
      // 0a86: invokevirtual Smenu.show ()V
      // 0a89: goto 0a8e
      // 0a8e: aload 0
      // 0a8f: ldc_w "    Make Stage    "
      // 0a92: sipush 400
      // 0a95: sipush 311
      // 0a98: iload 3
      // 0a99: iadd
      // 0a9a: bipush 0
      // 0a9b: bipush 1
      // 0a9c: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0a9f: ifne 0aa7
      // 0aa2: goto 0ab0
      // 0aa7: aload 0
      // 0aa8: invokevirtual StageMaker.newstage ()V
      // 0aab: goto 0ab0
      // 0ab0: aload 0
      // 0ab1: ldc_w "  Cancel  "
      // 0ab4: sipush 400
      // 0ab7: sipush 351
      // 0aba: iload 3
      // 0abb: iadd
      // 0abc: bipush 0
      // 0abd: bipush 0
      // 0abe: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0ac1: ifne 0ac9
      // 0ac4: goto 0ae1
      // 0ac9: aload 0
      // 0aca: getfield StageMaker.strtyp LSmenu;
      // 0acd: invokevirtual Smenu.hide ()V
      // 0ad0: aload 0
      // 0ad1: getfield StageMaker.srch Ljava/awt/TextField;
      // 0ad4: invokevirtual java/awt/TextField.hide ()V
      // 0ad7: aload 0
      // 0ad8: bipush 0
      // 0ad9: putfield StageMaker.sfase I
      // 0adc: goto 0ae1
      // 0ae1: aload 0
      // 0ae2: getfield StageMaker.sfase I
      // 0ae5: bipush 2
      // 0ae6: if_icmpeq 0aee
      // 0ae9: goto 0bf7
      // 0aee: aload 0
      // 0aef: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0af2: new java/lang/StringBuilder
      // 0af5: dup
      // 0af6: invokespecial java/lang/StringBuilder.<init> ()V
      // 0af9: ldc_w "Rename Stage :  "
      // 0afc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0aff: aload 0
      // 0b00: getfield StageMaker.stagename Ljava/lang/String;
      // 0b03: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0b06: ldc ""
      // 0b08: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0b0b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0b0e: sipush 400
      // 0b11: aload 0
      // 0b12: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 0b15: new java/lang/StringBuilder
      // 0b18: dup
      // 0b19: invokespecial java/lang/StringBuilder.<init> ()V
      // 0b1c: ldc_w "Rename Stage :  "
      // 0b1f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0b22: aload 0
      // 0b23: getfield StageMaker.stagename Ljava/lang/String;
      // 0b26: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0b29: ldc ""
      // 0b2b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0b2e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0b31: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0b34: bipush 2
      // 0b35: idiv
      // 0b36: isub
      // 0b37: sipush 230
      // 0b3a: iload 3
      // 0b3b: iadd
      // 0b3c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0b3f: aload 0
      // 0b40: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0b43: new java/awt/Font
      // 0b46: dup
      // 0b47: ldc_w "Arial"
      // 0b4a: bipush 1
      // 0b4b: bipush 12
      // 0b4d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0b50: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0b53: aload 0
      // 0b54: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 0b57: ldc_w "New name :"
      // 0b5a: sipush 218
      // 0b5d: sipush 266
      // 0b60: iload 3
      // 0b61: iadd
      // 0b62: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0b65: aload 0
      // 0b66: getfield StageMaker.srch Ljava/awt/TextField;
      // 0b69: invokevirtual java/awt/TextField.isShowing ()Z
      // 0b6c: ifeq 0b74
      // 0b6f: goto 0b87
      // 0b74: aload 0
      // 0b75: getfield StageMaker.srch Ljava/awt/TextField;
      // 0b78: invokevirtual java/awt/TextField.show ()V
      // 0b7b: aload 0
      // 0b7c: getfield StageMaker.srch Ljava/awt/TextField;
      // 0b7f: invokevirtual java/awt/TextField.requestFocus ()V
      // 0b82: goto 0b87
      // 0b87: aload 0
      // 0b88: aload 0
      // 0b89: getfield StageMaker.srch Ljava/awt/TextField;
      // 0b8c: sipush 292
      // 0b8f: sipush 251
      // 0b92: iload 3
      // 0b93: iadd
      // 0b94: sipush 290
      // 0b97: bipush 23
      // 0b99: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // 0b9c: aload 0
      // 0b9d: aload 0
      // 0b9e: getfield StageMaker.srch Ljava/awt/TextField;
      // 0ba1: invokevirtual StageMaker.fixtext (Ljava/awt/TextField;)V
      // 0ba4: aload 0
      // 0ba5: ldc_w "    Rename Stage    "
      // 0ba8: sipush 400
      // 0bab: sipush 306
      // 0bae: iload 3
      // 0baf: iadd
      // 0bb0: bipush 0
      // 0bb1: bipush 1
      // 0bb2: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0bb5: ifne 0bbd
      // 0bb8: goto 0bcd
      // 0bbd: aload 0
      // 0bbe: aload 0
      // 0bbf: getfield StageMaker.srch Ljava/awt/TextField;
      // 0bc2: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 0bc5: invokevirtual StageMaker.renstage (Ljava/lang/String;)V
      // 0bc8: goto 0bcd
      // 0bcd: aload 0
      // 0bce: ldc_w "  Cancel  "
      // 0bd1: sipush 400
      // 0bd4: sipush 346
      // 0bd7: iload 3
      // 0bd8: iadd
      // 0bd9: bipush 0
      // 0bda: bipush 0
      // 0bdb: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 0bde: ifne 0be6
      // 0be1: goto 0bf7
      // 0be6: aload 0
      // 0be7: getfield StageMaker.srch Ljava/awt/TextField;
      // 0bea: invokevirtual java/awt/TextField.hide ()V
      // 0bed: aload 0
      // 0bee: bipush 0
      // 0bef: putfield StageMaker.sfase I
      // 0bf2: goto 0bf7
      // 0bf7: goto 0bfc
      // 0bfc: aload 0
      // 0bfd: getfield StageMaker.tab I
      // 0c00: bipush 1
      // 0c01: if_icmpeq 0c09
      // 0c04: goto 5585
      // 0c09: aload 0
      // 0c0a: getfield StageMaker.tabed I
      // 0c0d: aload 0
      // 0c0e: getfield StageMaker.tab I
      // 0c11: if_icmpne 0c19
      // 0c14: goto 0ce1
      // 0c19: aload 0
      // 0c1a: getfield StageMaker.m LMedium;
      // 0c1d: bipush 2
      // 0c1e: putfield Medium.trk I
      // 0c21: aload 0
      // 0c22: bipush 0
      // 0c23: invokevirtual StageMaker.readstage (I)V
      // 0c26: aload 0
      // 0c27: getfield StageMaker.sptyp I
      // 0c2a: ifeq 0c32
      // 0c2d: goto 0c3b
      // 0c32: aload 0
      // 0c33: invokevirtual StageMaker.partroads ()V
      // 0c36: goto 0c3b
      // 0c3b: aload 0
      // 0c3c: getfield StageMaker.sptyp I
      // 0c3f: bipush 1
      // 0c40: if_icmpeq 0c48
      // 0c43: goto 0c51
      // 0c48: aload 0
      // 0c49: invokevirtual StageMaker.partramps ()V
      // 0c4c: goto 0c51
      // 0c51: aload 0
      // 0c52: getfield StageMaker.sptyp I
      // 0c55: bipush 2
      // 0c56: if_icmpeq 0c5e
      // 0c59: goto 0c67
      // 0c5e: aload 0
      // 0c5f: invokevirtual StageMaker.partobst ()V
      // 0c62: goto 0c67
      // 0c67: aload 0
      // 0c68: getfield StageMaker.sptyp I
      // 0c6b: bipush 5
      // 0c6c: if_icmpeq 0c74
      // 0c6f: goto 0c7d
      // 0c74: aload 0
      // 0c75: invokevirtual StageMaker.partrees ()V
      // 0c78: goto 0c7d
      // 0c7d: aload 0
      // 0c7e: bipush 0
      // 0c7f: putfield StageMaker.onoff Z
      // 0c82: aload 0
      // 0c83: new java/awt/Cursor
      // 0c86: dup
      // 0c87: bipush 0
      // 0c88: invokespecial java/awt/Cursor.<init> (I)V
      // 0c8b: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 0c8e: aload 0
      // 0c8f: bipush 0
      // 0c90: putfield StageMaker.setcur Z
      // 0c93: aload 0
      // 0c94: bipush 0
      // 0c95: putfield StageMaker.epart Z
      // 0c98: aload 0
      // 0c99: bipush 0
      // 0c9a: putfield StageMaker.arrng Z
      // 0c9d: aload 0
      // 0c9e: getfield StageMaker.nob I
      // 0ca1: bipush 1
      // 0ca2: if_icmpeq 0caa
      // 0ca5: goto 0cd7
      // 0caa: aload 0
      // 0cab: bipush 0
      // 0cac: putfield StageMaker.sptyp I
      // 0caf: aload 0
      // 0cb0: getfield StageMaker.co [LContO;
      // 0cb3: bipush 0
      // 0cb4: aaload
      // 0cb5: getfield ContO.colok I
      // 0cb8: bipush 38
      // 0cba: if_icmpeq 0cc2
      // 0cbd: goto 0ccd
      // 0cc2: aload 0
      // 0cc3: bipush 9
      // 0cc5: putfield StageMaker.spart I
      // 0cc8: goto 0cd7
      // 0ccd: aload 0
      // 0cce: bipush 0
      // 0ccf: putfield StageMaker.spart I
      // 0cd2: goto 0cd7
      // 0cd7: aload 0
      // 0cd8: bipush -1
      // 0cd9: putfield StageMaker.mouseon I
      // 0cdc: goto 0ce1
      // 0ce1: aload 0
      // 0ce2: getfield StageMaker.sptyp I
      // 0ce5: ifeq 0ced
      // 0ce8: goto 0f6a
      // 0ced: aload 0
      // 0cee: getfield StageMaker.spart I
      // 0cf1: ifeq 0cf9
      // 0cf4: goto 0d03
      // 0cf9: aload 0
      // 0cfa: bipush 0
      // 0cfb: putfield StageMaker.sp I
      // 0cfe: goto 0d03
      // 0d03: aload 0
      // 0d04: getfield StageMaker.spart I
      // 0d07: bipush 1
      // 0d08: if_icmpeq 0d10
      // 0d0b: goto 0d1a
      // 0d10: aload 0
      // 0d11: bipush 4
      // 0d12: putfield StageMaker.sp I
      // 0d15: goto 0d1a
      // 0d1a: aload 0
      // 0d1b: getfield StageMaker.spart I
      // 0d1e: bipush 2
      // 0d1f: if_icmpeq 0d27
      // 0d22: goto 0d32
      // 0d27: aload 0
      // 0d28: bipush 13
      // 0d2a: putfield StageMaker.sp I
      // 0d2d: goto 0d32
      // 0d32: aload 0
      // 0d33: getfield StageMaker.spart I
      // 0d36: bipush 3
      // 0d37: if_icmpeq 0d3f
      // 0d3a: goto 0d49
      // 0d3f: aload 0
      // 0d40: bipush 3
      // 0d41: putfield StageMaker.sp I
      // 0d44: goto 0d49
      // 0d49: aload 0
      // 0d4a: getfield StageMaker.spart I
      // 0d4d: bipush 4
      // 0d4e: if_icmpeq 0d56
      // 0d51: goto 0d60
      // 0d56: aload 0
      // 0d57: bipush 2
      // 0d58: putfield StageMaker.sp I
      // 0d5b: goto 0d60
      // 0d60: aload 0
      // 0d61: getfield StageMaker.spart I
      // 0d64: bipush 5
      // 0d65: if_icmpeq 0d6d
      // 0d68: goto 0d77
      // 0d6d: aload 0
      // 0d6e: bipush 1
      // 0d6f: putfield StageMaker.sp I
      // 0d72: goto 0d77
      // 0d77: aload 0
      // 0d78: getfield StageMaker.spart I
      // 0d7b: bipush 6
      // 0d7d: if_icmpeq 0d85
      // 0d80: goto 0d90
      // 0d85: aload 0
      // 0d86: bipush 35
      // 0d88: putfield StageMaker.sp I
      // 0d8b: goto 0d90
      // 0d90: aload 0
      // 0d91: getfield StageMaker.spart I
      // 0d94: bipush 7
      // 0d96: if_icmpeq 0d9e
      // 0d99: goto 0da9
      // 0d9e: aload 0
      // 0d9f: bipush 36
      // 0da1: putfield StageMaker.sp I
      // 0da4: goto 0da9
      // 0da9: aload 0
      // 0daa: getfield StageMaker.spart I
      // 0dad: bipush 8
      // 0daf: if_icmpeq 0db7
      // 0db2: goto 0dc2
      // 0db7: aload 0
      // 0db8: bipush 10
      // 0dba: putfield StageMaker.sp I
      // 0dbd: goto 0dc2
      // 0dc2: aload 0
      // 0dc3: getfield StageMaker.spart I
      // 0dc6: bipush 9
      // 0dc8: if_icmpeq 0dd0
      // 0dcb: goto 0dda
      // 0dd0: aload 0
      // 0dd1: bipush 5
      // 0dd2: putfield StageMaker.sp I
      // 0dd5: goto 0dda
      // 0dda: aload 0
      // 0ddb: getfield StageMaker.spart I
      // 0dde: bipush 10
      // 0de0: if_icmpeq 0de8
      // 0de3: goto 0df3
      // 0de8: aload 0
      // 0de9: bipush 7
      // 0deb: putfield StageMaker.sp I
      // 0dee: goto 0df3
      // 0df3: aload 0
      // 0df4: getfield StageMaker.spart I
      // 0df7: bipush 11
      // 0df9: if_icmpeq 0e01
      // 0dfc: goto 0e0c
      // 0e01: aload 0
      // 0e02: bipush 14
      // 0e04: putfield StageMaker.sp I
      // 0e07: goto 0e0c
      // 0e0c: aload 0
      // 0e0d: getfield StageMaker.spart I
      // 0e10: bipush 12
      // 0e12: if_icmpeq 0e1a
      // 0e15: goto 0e25
      // 0e1a: aload 0
      // 0e1b: bipush 6
      // 0e1d: putfield StageMaker.sp I
      // 0e20: goto 0e25
      // 0e25: aload 0
      // 0e26: getfield StageMaker.spart I
      // 0e29: bipush 13
      // 0e2b: if_icmpeq 0e33
      // 0e2e: goto 0e3e
      // 0e33: aload 0
      // 0e34: bipush 34
      // 0e36: putfield StageMaker.sp I
      // 0e39: goto 0e3e
      // 0e3e: aload 0
      // 0e3f: getfield StageMaker.spart I
      // 0e42: bipush 14
      // 0e44: if_icmpeq 0e4c
      // 0e47: goto 0e57
      // 0e4c: aload 0
      // 0e4d: bipush 33
      // 0e4f: putfield StageMaker.sp I
      // 0e52: goto 0e57
      // 0e57: aload 0
      // 0e58: getfield StageMaker.spart I
      // 0e5b: bipush 15
      // 0e5d: if_icmpeq 0e65
      // 0e60: goto 0e70
      // 0e65: aload 0
      // 0e66: bipush 11
      // 0e68: putfield StageMaker.sp I
      // 0e6b: goto 0e70
      // 0e70: aload 0
      // 0e71: getfield StageMaker.spart I
      // 0e74: bipush 16
      // 0e76: if_icmpeq 0e7e
      // 0e79: goto 0e89
      // 0e7e: aload 0
      // 0e7f: bipush 8
      // 0e81: putfield StageMaker.sp I
      // 0e84: goto 0e89
      // 0e89: aload 0
      // 0e8a: getfield StageMaker.spart I
      // 0e8d: bipush 17
      // 0e8f: if_icmpeq 0e97
      // 0e92: goto 0ea2
      // 0e97: aload 0
      // 0e98: bipush 9
      // 0e9a: putfield StageMaker.sp I
      // 0e9d: goto 0ea2
      // 0ea2: aload 0
      // 0ea3: getfield StageMaker.spart I
      // 0ea6: bipush 18
      // 0ea8: if_icmpeq 0eb0
      // 0eab: goto 0ebb
      // 0eb0: aload 0
      // 0eb1: bipush 15
      // 0eb3: putfield StageMaker.sp I
      // 0eb6: goto 0ebb
      // 0ebb: aload 0
      // 0ebc: getfield StageMaker.spart I
      // 0ebf: bipush 19
      // 0ec1: if_icmpeq 0ec9
      // 0ec4: goto 0ed4
      // 0ec9: aload 0
      // 0eca: bipush 12
      // 0ecc: putfield StageMaker.sp I
      // 0ecf: goto 0ed4
      // 0ed4: aload 0
      // 0ed5: getfield StageMaker.spart I
      // 0ed8: bipush 20
      // 0eda: if_icmpeq 0ee2
      // 0edd: goto 0eed
      // 0ee2: aload 0
      // 0ee3: bipush 46
      // 0ee5: putfield StageMaker.sp I
      // 0ee8: goto 0eed
      // 0eed: aload 0
      // 0eee: getfield StageMaker.spart I
      // 0ef1: bipush 21
      // 0ef3: if_icmpeq 0efb
      // 0ef6: goto 0f06
      // 0efb: aload 0
      // 0efc: bipush 47
      // 0efe: putfield StageMaker.sp I
      // 0f01: goto 0f06
      // 0f06: aload 0
      // 0f07: getfield StageMaker.spart I
      // 0f0a: bipush 22
      // 0f0c: if_icmpeq 0f14
      // 0f0f: goto 0f1f
      // 0f14: aload 0
      // 0f15: bipush 50
      // 0f17: putfield StageMaker.sp I
      // 0f1a: goto 0f1f
      // 0f1f: aload 0
      // 0f20: getfield StageMaker.spart I
      // 0f23: bipush 23
      // 0f25: if_icmpeq 0f2d
      // 0f28: goto 0f38
      // 0f2d: aload 0
      // 0f2e: bipush 48
      // 0f30: putfield StageMaker.sp I
      // 0f33: goto 0f38
      // 0f38: aload 0
      // 0f39: getfield StageMaker.spart I
      // 0f3c: bipush 24
      // 0f3e: if_icmpeq 0f46
      // 0f41: goto 0f51
      // 0f46: aload 0
      // 0f47: bipush 49
      // 0f49: putfield StageMaker.sp I
      // 0f4c: goto 0f51
      // 0f51: aload 0
      // 0f52: getfield StageMaker.spart I
      // 0f55: bipush 25
      // 0f57: if_icmpeq 0f5f
      // 0f5a: goto 0f6a
      // 0f5f: aload 0
      // 0f60: bipush 51
      // 0f62: putfield StageMaker.sp I
      // 0f65: goto 0f6a
      // 0f6a: aload 0
      // 0f6b: getfield StageMaker.sptyp I
      // 0f6e: bipush 1
      // 0f6f: if_icmpeq 0f77
      // 0f72: goto 1100
      // 0f77: aload 0
      // 0f78: getfield StageMaker.spart I
      // 0f7b: ifeq 0f83
      // 0f7e: goto 0f8e
      // 0f83: aload 0
      // 0f84: bipush 16
      // 0f86: putfield StageMaker.sp I
      // 0f89: goto 0f8e
      // 0f8e: aload 0
      // 0f8f: getfield StageMaker.spart I
      // 0f92: bipush 1
      // 0f93: if_icmpeq 0f9b
      // 0f96: goto 0fa6
      // 0f9b: aload 0
      // 0f9c: bipush 18
      // 0f9e: putfield StageMaker.sp I
      // 0fa1: goto 0fa6
      // 0fa6: aload 0
      // 0fa7: getfield StageMaker.spart I
      // 0faa: bipush 2
      // 0fab: if_icmpeq 0fb3
      // 0fae: goto 0fbe
      // 0fb3: aload 0
      // 0fb4: bipush 19
      // 0fb6: putfield StageMaker.sp I
      // 0fb9: goto 0fbe
      // 0fbe: aload 0
      // 0fbf: getfield StageMaker.spart I
      // 0fc2: bipush 3
      // 0fc3: if_icmpeq 0fcb
      // 0fc6: goto 0fd6
      // 0fcb: aload 0
      // 0fcc: bipush 22
      // 0fce: putfield StageMaker.sp I
      // 0fd1: goto 0fd6
      // 0fd6: aload 0
      // 0fd7: getfield StageMaker.spart I
      // 0fda: bipush 4
      // 0fdb: if_icmpeq 0fe3
      // 0fde: goto 0fee
      // 0fe3: aload 0
      // 0fe4: bipush 17
      // 0fe6: putfield StageMaker.sp I
      // 0fe9: goto 0fee
      // 0fee: aload 0
      // 0fef: getfield StageMaker.spart I
      // 0ff2: bipush 5
      // 0ff3: if_icmpeq 0ffb
      // 0ff6: goto 1006
      // 0ffb: aload 0
      // 0ffc: bipush 21
      // 0ffe: putfield StageMaker.sp I
      // 1001: goto 1006
      // 1006: aload 0
      // 1007: getfield StageMaker.spart I
      // 100a: bipush 6
      // 100c: if_icmpeq 1014
      // 100f: goto 101f
      // 1014: aload 0
      // 1015: bipush 20
      // 1017: putfield StageMaker.sp I
      // 101a: goto 101f
      // 101f: aload 0
      // 1020: getfield StageMaker.spart I
      // 1023: bipush 7
      // 1025: if_icmpeq 102d
      // 1028: goto 1038
      // 102d: aload 0
      // 102e: bipush 39
      // 1030: putfield StageMaker.sp I
      // 1033: goto 1038
      // 1038: aload 0
      // 1039: getfield StageMaker.spart I
      // 103c: bipush 8
      // 103e: if_icmpeq 1046
      // 1041: goto 1051
      // 1046: aload 0
      // 1047: bipush 42
      // 1049: putfield StageMaker.sp I
      // 104c: goto 1051
      // 1051: aload 0
      // 1052: getfield StageMaker.spart I
      // 1055: bipush 9
      // 1057: if_icmpeq 105f
      // 105a: goto 106a
      // 105f: aload 0
      // 1060: bipush 40
      // 1062: putfield StageMaker.sp I
      // 1065: goto 106a
      // 106a: aload 0
      // 106b: getfield StageMaker.spart I
      // 106e: bipush 10
      // 1070: if_icmpeq 1078
      // 1073: goto 1083
      // 1078: aload 0
      // 1079: bipush 23
      // 107b: putfield StageMaker.sp I
      // 107e: goto 1083
      // 1083: aload 0
      // 1084: getfield StageMaker.spart I
      // 1087: bipush 11
      // 1089: if_icmpeq 1091
      // 108c: goto 109c
      // 1091: aload 0
      // 1092: bipush 25
      // 1094: putfield StageMaker.sp I
      // 1097: goto 109c
      // 109c: aload 0
      // 109d: getfield StageMaker.spart I
      // 10a0: bipush 12
      // 10a2: if_icmpeq 10aa
      // 10a5: goto 10b5
      // 10aa: aload 0
      // 10ab: bipush 24
      // 10ad: putfield StageMaker.sp I
      // 10b0: goto 10b5
      // 10b5: aload 0
      // 10b6: getfield StageMaker.spart I
      // 10b9: bipush 13
      // 10bb: if_icmpeq 10c3
      // 10be: goto 10ce
      // 10c3: aload 0
      // 10c4: bipush 43
      // 10c6: putfield StageMaker.sp I
      // 10c9: goto 10ce
      // 10ce: aload 0
      // 10cf: getfield StageMaker.spart I
      // 10d2: bipush 14
      // 10d4: if_icmpeq 10dc
      // 10d7: goto 10e7
      // 10dc: aload 0
      // 10dd: bipush 45
      // 10df: putfield StageMaker.sp I
      // 10e2: goto 10e7
      // 10e7: aload 0
      // 10e8: getfield StageMaker.spart I
      // 10eb: bipush 15
      // 10ed: if_icmpeq 10f5
      // 10f0: goto 1100
      // 10f5: aload 0
      // 10f6: bipush 26
      // 10f8: putfield StageMaker.sp I
      // 10fb: goto 1100
      // 1100: aload 0
      // 1101: getfield StageMaker.sptyp I
      // 1104: bipush 2
      // 1105: if_icmpeq 110d
      // 1108: goto 119c
      // 110d: aload 0
      // 110e: getfield StageMaker.spart I
      // 1111: ifeq 1119
      // 1114: goto 1124
      // 1119: aload 0
      // 111a: bipush 27
      // 111c: putfield StageMaker.sp I
      // 111f: goto 1124
      // 1124: aload 0
      // 1125: getfield StageMaker.spart I
      // 1128: bipush 1
      // 1129: if_icmpeq 1131
      // 112c: goto 113c
      // 1131: aload 0
      // 1132: bipush 28
      // 1134: putfield StageMaker.sp I
      // 1137: goto 113c
      // 113c: aload 0
      // 113d: getfield StageMaker.spart I
      // 1140: bipush 2
      // 1141: if_icmpeq 1149
      // 1144: goto 1154
      // 1149: aload 0
      // 114a: bipush 41
      // 114c: putfield StageMaker.sp I
      // 114f: goto 1154
      // 1154: aload 0
      // 1155: getfield StageMaker.spart I
      // 1158: bipush 3
      // 1159: if_icmpeq 1161
      // 115c: goto 116c
      // 1161: aload 0
      // 1162: bipush 44
      // 1164: putfield StageMaker.sp I
      // 1167: goto 116c
      // 116c: aload 0
      // 116d: getfield StageMaker.spart I
      // 1170: bipush 4
      // 1171: if_icmpeq 1179
      // 1174: goto 1184
      // 1179: aload 0
      // 117a: bipush 52
      // 117c: putfield StageMaker.sp I
      // 117f: goto 1184
      // 1184: aload 0
      // 1185: getfield StageMaker.spart I
      // 1188: bipush 5
      // 1189: if_icmpeq 1191
      // 118c: goto 119c
      // 1191: aload 0
      // 1192: bipush 53
      // 1194: putfield StageMaker.sp I
      // 1197: goto 119c
      // 119c: aload 0
      // 119d: getfield StageMaker.sptyp I
      // 11a0: bipush 3
      // 11a1: if_icmpeq 11a9
      // 11a4: goto 11e2
      // 11a9: aload 0
      // 11aa: getfield StageMaker.onfly Z
      // 11ad: ifne 11b5
      // 11b0: goto 11c0
      // 11b5: aload 0
      // 11b6: bipush 54
      // 11b8: putfield StageMaker.sp I
      // 11bb: goto 11e2
      // 11c0: aload 0
      // 11c1: getfield StageMaker.onoff Z
      // 11c4: ifeq 11cc
      // 11c7: goto 11d7
      // 11cc: aload 0
      // 11cd: bipush 30
      // 11cf: putfield StageMaker.sp I
      // 11d2: goto 11e2
      // 11d7: aload 0
      // 11d8: bipush 32
      // 11da: putfield StageMaker.sp I
      // 11dd: goto 11e2
      // 11e2: aload 0
      // 11e3: getfield StageMaker.sptyp I
      // 11e6: bipush 4
      // 11e7: if_icmpeq 11ef
      // 11ea: goto 11fa
      // 11ef: aload 0
      // 11f0: bipush 31
      // 11f2: putfield StageMaker.sp I
      // 11f5: goto 11fa
      // 11fa: aload 0
      // 11fb: getfield StageMaker.sptyp I
      // 11fe: bipush 5
      // 11ff: if_icmpeq 1207
      // 1202: goto 1313
      // 1207: aload 0
      // 1208: getfield StageMaker.spart I
      // 120b: ifeq 1213
      // 120e: goto 121e
      // 1213: aload 0
      // 1214: bipush 55
      // 1216: putfield StageMaker.sp I
      // 1219: goto 121e
      // 121e: aload 0
      // 121f: getfield StageMaker.spart I
      // 1222: bipush 1
      // 1223: if_icmpeq 122b
      // 1226: goto 1236
      // 122b: aload 0
      // 122c: bipush 56
      // 122e: putfield StageMaker.sp I
      // 1231: goto 1236
      // 1236: aload 0
      // 1237: getfield StageMaker.spart I
      // 123a: bipush 2
      // 123b: if_icmpeq 1243
      // 123e: goto 124e
      // 1243: aload 0
      // 1244: bipush 57
      // 1246: putfield StageMaker.sp I
      // 1249: goto 124e
      // 124e: aload 0
      // 124f: getfield StageMaker.spart I
      // 1252: bipush 3
      // 1253: if_icmpeq 125b
      // 1256: goto 1266
      // 125b: aload 0
      // 125c: bipush 58
      // 125e: putfield StageMaker.sp I
      // 1261: goto 1266
      // 1266: aload 0
      // 1267: getfield StageMaker.spart I
      // 126a: bipush 4
      // 126b: if_icmpeq 1273
      // 126e: goto 127e
      // 1273: aload 0
      // 1274: bipush 59
      // 1276: putfield StageMaker.sp I
      // 1279: goto 127e
      // 127e: aload 0
      // 127f: getfield StageMaker.spart I
      // 1282: bipush 5
      // 1283: if_icmpeq 128b
      // 1286: goto 1296
      // 128b: aload 0
      // 128c: bipush 60
      // 128e: putfield StageMaker.sp I
      // 1291: goto 1296
      // 1296: aload 0
      // 1297: getfield StageMaker.spart I
      // 129a: bipush 6
      // 129c: if_icmpeq 12a4
      // 129f: goto 12af
      // 12a4: aload 0
      // 12a5: bipush 61
      // 12a7: putfield StageMaker.sp I
      // 12aa: goto 12af
      // 12af: aload 0
      // 12b0: getfield StageMaker.spart I
      // 12b3: bipush 7
      // 12b5: if_icmpeq 12bd
      // 12b8: goto 12c8
      // 12bd: aload 0
      // 12be: bipush 62
      // 12c0: putfield StageMaker.sp I
      // 12c3: goto 12c8
      // 12c8: aload 0
      // 12c9: getfield StageMaker.spart I
      // 12cc: bipush 8
      // 12ce: if_icmpeq 12d6
      // 12d1: goto 12e1
      // 12d6: aload 0
      // 12d7: bipush 63
      // 12d9: putfield StageMaker.sp I
      // 12dc: goto 12e1
      // 12e1: aload 0
      // 12e2: getfield StageMaker.spart I
      // 12e5: bipush 9
      // 12e7: if_icmpeq 12ef
      // 12ea: goto 12fa
      // 12ef: aload 0
      // 12f0: bipush 64
      // 12f2: putfield StageMaker.sp I
      // 12f5: goto 12fa
      // 12fa: aload 0
      // 12fb: getfield StageMaker.spart I
      // 12fe: bipush 10
      // 1300: if_icmpeq 1308
      // 1303: goto 1313
      // 1308: aload 0
      // 1309: bipush 65
      // 130b: putfield StageMaker.sp I
      // 130e: goto 1313
      // 1313: aload 0
      // 1314: getfield StageMaker.sptyp I
      // 1317: bipush 6
      // 1319: if_icmpeq 1321
      // 131c: goto 13bd
      // 1321: aload 0
      // 1322: getfield StageMaker.pgen Z
      // 1325: ifeq 132d
      // 1328: goto 13ad
      // 132d: ldc2_w 10000.0
      // 1330: invokestatic java/lang/Math.random ()D
      // 1333: dmul
      // 1334: d2i
      // 1335: istore 1
      // 1336: aload 0
      // 1337: getfield StageMaker.fgen I
      // 133a: ifne 1342
      // 133d: goto 1351
      // 1342: aload 0
      // 1343: getfield StageMaker.fgen I
      // 1346: istore 1
      // 1347: aload 0
      // 1348: bipush 0
      // 1349: putfield StageMaker.fgen I
      // 134c: goto 1351
      // 1351: aload 0
      // 1352: getfield StageMaker.bco [LContO;
      // 1355: bipush 66
      // 1357: new ContO
      // 135a: dup
      // 135b: iload 1
      // 135c: aload 0
      // 135d: getfield StageMaker.pwd F
      // 1360: f2i
      // 1361: aload 0
      // 1362: getfield StageMaker.phd F
      // 1365: f2i
      // 1366: aload 0
      // 1367: getfield StageMaker.m LMedium;
      // 136a: aload 0
      // 136b: getfield StageMaker.t LTrackers;
      // 136e: bipush 0
      // 136f: bipush 0
      // 1370: bipush 0
      // 1371: invokespecial ContO.<init> (IIILMedium;LTrackers;III)V
      // 1374: aastore
      // 1375: aload 0
      // 1376: getfield StageMaker.bco [LContO;
      // 1379: bipush 66
      // 137b: aaload
      // 137c: iload 1
      // 137d: putfield ContO.srz I
      // 1380: aload 0
      // 1381: getfield StageMaker.bco [LContO;
      // 1384: bipush 66
      // 1386: aaload
      // 1387: aload 0
      // 1388: getfield StageMaker.pwd F
      // 138b: f2i
      // 138c: putfield ContO.srx I
      // 138f: aload 0
      // 1390: getfield StageMaker.bco [LContO;
      // 1393: bipush 66
      // 1395: aaload
      // 1396: aload 0
      // 1397: getfield StageMaker.phd F
      // 139a: f2i
      // 139b: putfield ContO.sry I
      // 139e: aload 0
      // 139f: bipush 1
      // 13a0: putfield StageMaker.pgen Z
      // 13a3: aload 0
      // 13a4: bipush 3
      // 13a5: putfield StageMaker.seq I
      // 13a8: goto 13ad
      // 13ad: aload 0
      // 13ae: bipush 66
      // 13b0: putfield StageMaker.sp I
      // 13b3: aload 0
      // 13b4: bipush 0
      // 13b5: putfield StageMaker.rot I
      // 13b8: goto 13f9
      // 13bd: aload 0
      // 13be: getfield StageMaker.pgen Z
      // 13c1: ifne 13c9
      // 13c4: goto 13f9
      // 13c9: aload 0
      // 13ca: bipush 0
      // 13cb: putfield StageMaker.pgen Z
      // 13ce: aload 0
      // 13cf: ldc2_w 2
      // 13d2: invokestatic java/lang/Math.random ()D
      // 13d5: ldc2_w 4.0
      // 13d8: dmul
      // 13d9: invokestatic java/lang/Math.round (D)J
      // 13dc: ladd
      // 13dd: l2f
      // 13de: putfield StageMaker.pwd F
      // 13e1: aload 0
      // 13e2: ldc2_w 2
      // 13e5: invokestatic java/lang/Math.random ()D
      // 13e8: ldc2_w 4.0
      // 13eb: dmul
      // 13ec: invokestatic java/lang/Math.round (D)J
      // 13ef: ladd
      // 13f0: l2f
      // 13f1: putfield StageMaker.phd F
      // 13f4: goto 13f9
      // 13f9: aload 0
      // 13fa: getfield StageMaker.sp I
      // 13fd: bipush 30
      // 13ff: if_icmpne 1407
      // 1402: goto 1431
      // 1407: aload 0
      // 1408: getfield StageMaker.sp I
      // 140b: bipush 31
      // 140d: if_icmpne 1415
      // 1410: goto 1431
      // 1415: aload 0
      // 1416: getfield StageMaker.sp I
      // 1419: bipush 32
      // 141b: if_icmpne 1423
      // 141e: goto 1431
      // 1423: aload 0
      // 1424: getfield StageMaker.sp I
      // 1427: bipush 54
      // 1429: if_icmpeq 1431
      // 142c: goto 1463
      // 1431: aload 0
      // 1432: getfield StageMaker.rot I
      // 1435: bipush -90
      // 1437: if_icmpeq 143f
      // 143a: goto 144a
      // 143f: aload 0
      // 1440: bipush 90
      // 1442: putfield StageMaker.rot I
      // 1445: goto 144a
      // 144a: aload 0
      // 144b: getfield StageMaker.rot I
      // 144e: sipush 180
      // 1451: if_icmpeq 1459
      // 1454: goto 1463
      // 1459: aload 0
      // 145a: bipush 0
      // 145b: putfield StageMaker.rot I
      // 145e: goto 1463
      // 1463: aload 0
      // 1464: bipush 0
      // 1465: putfield StageMaker.adrot I
      // 1468: aload 0
      // 1469: getfield StageMaker.sp I
      // 146c: bipush 2
      // 146d: if_icmpeq 1475
      // 1470: goto 1480
      // 1475: aload 0
      // 1476: bipush -30
      // 1478: putfield StageMaker.adrot I
      // 147b: goto 1480
      // 1480: aload 0
      // 1481: getfield StageMaker.sp I
      // 1484: bipush 3
      // 1485: if_icmpeq 148d
      // 1488: goto 1498
      // 148d: aload 0
      // 148e: bipush 30
      // 1490: putfield StageMaker.adrot I
      // 1493: goto 1498
      // 1498: aload 0
      // 1499: getfield StageMaker.sp I
      // 149c: bipush 15
      // 149e: if_icmpeq 14a6
      // 14a1: goto 14b1
      // 14a6: aload 0
      // 14a7: bipush 90
      // 14a9: putfield StageMaker.adrot I
      // 14ac: goto 14b1
      // 14b1: aload 0
      // 14b2: getfield StageMaker.sp I
      // 14b5: bipush 20
      // 14b7: if_icmpeq 14bf
      // 14ba: goto 14cb
      // 14bf: aload 0
      // 14c0: sipush 180
      // 14c3: putfield StageMaker.adrot I
      // 14c6: goto 14cb
      // 14cb: aload 0
      // 14cc: getfield StageMaker.sp I
      // 14cf: bipush 26
      // 14d1: if_icmpeq 14d9
      // 14d4: goto 14e4
      // 14d9: aload 0
      // 14da: bipush 90
      // 14dc: putfield StageMaker.adrot I
      // 14df: goto 14e4
      // 14e4: aload 0
      // 14e5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 14e8: new java/awt/Color
      // 14eb: dup
      // 14ec: sipush 200
      // 14ef: sipush 200
      // 14f2: sipush 200
      // 14f5: invokespecial java/awt/Color.<init> (III)V
      // 14f8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 14fb: aload 0
      // 14fc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 14ff: sipush 248
      // 1502: bipush 63
      // 1504: sipush 514
      // 1507: sipush 454
      // 150a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 150d: aload 0
      // 150e: getfield StageMaker.m LMedium;
      // 1511: bipush 2
      // 1512: putfield Medium.trk I
      // 1515: aload 0
      // 1516: getfield StageMaker.m LMedium;
      // 1519: bipush 90
      // 151b: putfield Medium.zy I
      // 151e: aload 0
      // 151f: getfield StageMaker.m LMedium;
      // 1522: bipush 0
      // 1523: putfield Medium.xz I
      // 1526: aload 0
      // 1527: getfield StageMaker.m LMedium;
      // 152a: sipush 248
      // 152d: putfield Medium.iw I
      // 1530: aload 0
      // 1531: getfield StageMaker.m LMedium;
      // 1534: sipush 762
      // 1537: putfield Medium.w I
      // 153a: aload 0
      // 153b: getfield StageMaker.m LMedium;
      // 153e: bipush 63
      // 1540: putfield Medium.ih I
      // 1543: aload 0
      // 1544: getfield StageMaker.m LMedium;
      // 1547: sipush 517
      // 154a: putfield Medium.h I
      // 154d: aload 0
      // 154e: getfield StageMaker.m LMedium;
      // 1551: sipush 505
      // 1554: putfield Medium.cx I
      // 1557: aload 0
      // 1558: getfield StageMaker.m LMedium;
      // 155b: sipush 290
      // 155e: putfield Medium.cy I
      // 1561: aload 0
      // 1562: getfield StageMaker.m LMedium;
      // 1565: aload 0
      // 1566: getfield StageMaker.sx I
      // 1569: aload 0
      // 156a: getfield StageMaker.m LMedium;
      // 156d: getfield Medium.cx I
      // 1570: isub
      // 1571: putfield Medium.x I
      // 1574: aload 0
      // 1575: getfield StageMaker.m LMedium;
      // 1578: aload 0
      // 1579: getfield StageMaker.sz I
      // 157c: aload 0
      // 157d: getfield StageMaker.m LMedium;
      // 1580: getfield Medium.cz I
      // 1583: isub
      // 1584: putfield Medium.z I
      // 1587: aload 0
      // 1588: getfield StageMaker.m LMedium;
      // 158b: aload 0
      // 158c: getfield StageMaker.sy I
      // 158f: putfield Medium.y I
      // 1592: bipush 0
      // 1593: istore 1
      // 1594: sipush 200
      // 1597: newarray 10
      // 1599: astore 2
      // 159a: bipush 0
      // 159b: istore 3
      // 159c: iload 3
      // 159d: aload 0
      // 159e: getfield StageMaker.nob I
      // 15a1: if_icmplt 15a9
      // 15a4: goto 15db
      // 15a9: aload 0
      // 15aa: getfield StageMaker.co [LContO;
      // 15ad: iload 3
      // 15ae: aaload
      // 15af: getfield ContO.dist I
      // 15b2: ifne 15ba
      // 15b5: goto 15c6
      // 15ba: aload 2
      // 15bb: iload 1
      // 15bc: iload 3
      // 15bd: iastore
      // 15be: iinc 1 1
      // 15c1: goto 15d3
      // 15c6: aload 0
      // 15c7: getfield StageMaker.co [LContO;
      // 15ca: iload 3
      // 15cb: aaload
      // 15cc: aload 0
      // 15cd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 15d0: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 15d3: iinc 3 1
      // 15d6: goto 159c
      // 15db: iload 1
      // 15dc: newarray 10
      // 15de: astore 3
      // 15df: bipush 0
      // 15e0: istore 4
      // 15e2: iload 4
      // 15e4: iload 1
      // 15e5: if_icmplt 15ed
      // 15e8: goto 15fa
      // 15ed: aload 3
      // 15ee: iload 4
      // 15f0: bipush 0
      // 15f1: iastore
      // 15f2: iinc 4 1
      // 15f5: goto 15e2
      // 15fa: bipush 0
      // 15fb: istore 4
      // 15fd: iload 4
      // 15ff: iload 1
      // 1600: if_icmplt 1608
      // 1603: goto 16a4
      // 1608: iload 4
      // 160a: bipush 1
      // 160b: iadd
      // 160c: istore 5
      // 160e: iload 5
      // 1610: iload 1
      // 1611: if_icmplt 1619
      // 1614: goto 169c
      // 1619: aload 0
      // 161a: getfield StageMaker.co [LContO;
      // 161d: aload 2
      // 161e: iload 4
      // 1620: iaload
      // 1621: aaload
      // 1622: getfield ContO.dist I
      // 1625: aload 0
      // 1626: getfield StageMaker.co [LContO;
      // 1629: aload 2
      // 162a: iload 5
      // 162c: iaload
      // 162d: aaload
      // 162e: getfield ContO.dist I
      // 1631: if_icmpne 1639
      // 1634: goto 1673
      // 1639: aload 0
      // 163a: getfield StageMaker.co [LContO;
      // 163d: aload 2
      // 163e: iload 4
      // 1640: iaload
      // 1641: aaload
      // 1642: getfield ContO.dist I
      // 1645: aload 0
      // 1646: getfield StageMaker.co [LContO;
      // 1649: aload 2
      // 164a: iload 5
      // 164c: iaload
      // 164d: aaload
      // 164e: getfield ContO.dist I
      // 1651: if_icmplt 1659
      // 1654: goto 1666
      // 1659: aload 3
      // 165a: iload 4
      // 165c: dup2
      // 165d: iaload
      // 165e: bipush 1
      // 165f: iadd
      // 1660: iastore
      // 1661: goto 1694
      // 1666: aload 3
      // 1667: iload 5
      // 1669: dup2
      // 166a: iaload
      // 166b: bipush 1
      // 166c: iadd
      // 166d: iastore
      // 166e: goto 1694
      // 1673: iload 5
      // 1675: iload 4
      // 1677: if_icmpgt 167f
      // 167a: goto 168c
      // 167f: aload 3
      // 1680: iload 4
      // 1682: dup2
      // 1683: iaload
      // 1684: bipush 1
      // 1685: iadd
      // 1686: iastore
      // 1687: goto 1694
      // 168c: aload 3
      // 168d: iload 5
      // 168f: dup2
      // 1690: iaload
      // 1691: bipush 1
      // 1692: iadd
      // 1693: iastore
      // 1694: iinc 5 1
      // 1697: goto 160e
      // 169c: iinc 4 1
      // 169f: goto 15fd
      // 16a4: bipush 0
      // 16a5: istore 4
      // 16a7: iload 4
      // 16a9: iload 1
      // 16aa: if_icmplt 16b2
      // 16ad: goto 1c85
      // 16b2: bipush 0
      // 16b3: istore 5
      // 16b5: iload 5
      // 16b7: iload 1
      // 16b8: if_icmplt 16c0
      // 16bb: goto 1c7d
      // 16c0: aload 3
      // 16c1: iload 5
      // 16c3: iaload
      // 16c4: iload 4
      // 16c6: if_icmpeq 16ce
      // 16c9: goto 1c75
      // 16ce: aload 2
      // 16cf: iload 5
      // 16d1: iaload
      // 16d2: aload 0
      // 16d3: getfield StageMaker.hi I
      // 16d6: if_icmpeq 16de
      // 16d9: goto 16eb
      // 16de: aload 0
      // 16df: getfield StageMaker.m LMedium;
      // 16e2: bipush 3
      // 16e3: putfield Medium.trk I
      // 16e6: goto 16eb
      // 16eb: aload 2
      // 16ec: iload 5
      // 16ee: iaload
      // 16ef: aload 0
      // 16f0: getfield StageMaker.chi I
      // 16f3: if_icmpeq 16fb
      // 16f6: goto 1975
      // 16fb: aload 0
      // 16fc: getfield StageMaker.co [LContO;
      // 16ff: aload 2
      // 1700: iload 5
      // 1702: iaload
      // 1703: aaload
      // 1704: getfield ContO.errd Z
      // 1707: ifeq 170f
      // 170a: goto 1975
      // 170f: aload 0
      // 1710: getfield StageMaker.m LMedium;
      // 1713: getfield Medium.cx I
      // 1716: aload 0
      // 1717: getfield StageMaker.co [LContO;
      // 171a: aload 2
      // 171b: iload 5
      // 171d: iaload
      // 171e: aaload
      // 171f: getfield ContO.x I
      // 1722: aload 0
      // 1723: getfield StageMaker.m LMedium;
      // 1726: getfield Medium.x I
      // 1729: isub
      // 172a: aload 0
      // 172b: getfield StageMaker.m LMedium;
      // 172e: getfield Medium.cx I
      // 1731: isub
      // 1732: i2f
      // 1733: aload 0
      // 1734: getfield StageMaker.m LMedium;
      // 1737: aload 0
      // 1738: getfield StageMaker.m LMedium;
      // 173b: getfield Medium.xz I
      // 173e: invokevirtual Medium.cos (I)F
      // 1741: fmul
      // 1742: aload 0
      // 1743: getfield StageMaker.co [LContO;
      // 1746: aload 2
      // 1747: iload 5
      // 1749: iaload
      // 174a: aaload
      // 174b: getfield ContO.z I
      // 174e: aload 0
      // 174f: getfield StageMaker.m LMedium;
      // 1752: getfield Medium.z I
      // 1755: isub
      // 1756: aload 0
      // 1757: getfield StageMaker.m LMedium;
      // 175a: getfield Medium.cz I
      // 175d: isub
      // 175e: i2f
      // 175f: aload 0
      // 1760: getfield StageMaker.m LMedium;
      // 1763: aload 0
      // 1764: getfield StageMaker.m LMedium;
      // 1767: getfield Medium.xz I
      // 176a: invokevirtual Medium.sin (I)F
      // 176d: fmul
      // 176e: fsub
      // 176f: f2i
      // 1770: iadd
      // 1771: istore 6
      // 1773: aload 0
      // 1774: getfield StageMaker.m LMedium;
      // 1777: getfield Medium.cz I
      // 177a: aload 0
      // 177b: getfield StageMaker.co [LContO;
      // 177e: aload 2
      // 177f: iload 5
      // 1781: iaload
      // 1782: aaload
      // 1783: getfield ContO.x I
      // 1786: aload 0
      // 1787: getfield StageMaker.m LMedium;
      // 178a: getfield Medium.x I
      // 178d: isub
      // 178e: aload 0
      // 178f: getfield StageMaker.m LMedium;
      // 1792: getfield Medium.cx I
      // 1795: isub
      // 1796: i2f
      // 1797: aload 0
      // 1798: getfield StageMaker.m LMedium;
      // 179b: aload 0
      // 179c: getfield StageMaker.m LMedium;
      // 179f: getfield Medium.xz I
      // 17a2: invokevirtual Medium.sin (I)F
      // 17a5: fmul
      // 17a6: aload 0
      // 17a7: getfield StageMaker.co [LContO;
      // 17aa: aload 2
      // 17ab: iload 5
      // 17ad: iaload
      // 17ae: aaload
      // 17af: getfield ContO.z I
      // 17b2: aload 0
      // 17b3: getfield StageMaker.m LMedium;
      // 17b6: getfield Medium.z I
      // 17b9: isub
      // 17ba: aload 0
      // 17bb: getfield StageMaker.m LMedium;
      // 17be: getfield Medium.cz I
      // 17c1: isub
      // 17c2: i2f
      // 17c3: aload 0
      // 17c4: getfield StageMaker.m LMedium;
      // 17c7: aload 0
      // 17c8: getfield StageMaker.m LMedium;
      // 17cb: getfield Medium.xz I
      // 17ce: invokevirtual Medium.cos (I)F
      // 17d1: fmul
      // 17d2: fadd
      // 17d3: f2i
      // 17d4: iadd
      // 17d5: istore 7
      // 17d7: aload 0
      // 17d8: getfield StageMaker.m LMedium;
      // 17db: getfield Medium.cy I
      // 17de: aload 0
      // 17df: getfield StageMaker.co [LContO;
      // 17e2: aload 2
      // 17e3: iload 5
      // 17e5: iaload
      // 17e6: aaload
      // 17e7: getfield ContO.y I
      // 17ea: aload 0
      // 17eb: getfield StageMaker.m LMedium;
      // 17ee: getfield Medium.y I
      // 17f1: isub
      // 17f2: aload 0
      // 17f3: getfield StageMaker.m LMedium;
      // 17f6: getfield Medium.cy I
      // 17f9: isub
      // 17fa: i2f
      // 17fb: aload 0
      // 17fc: getfield StageMaker.m LMedium;
      // 17ff: aload 0
      // 1800: getfield StageMaker.m LMedium;
      // 1803: getfield Medium.zy I
      // 1806: invokevirtual Medium.cos (I)F
      // 1809: fmul
      // 180a: iload 7
      // 180c: aload 0
      // 180d: getfield StageMaker.m LMedium;
      // 1810: getfield Medium.cz I
      // 1813: isub
      // 1814: i2f
      // 1815: aload 0
      // 1816: getfield StageMaker.m LMedium;
      // 1819: aload 0
      // 181a: getfield StageMaker.m LMedium;
      // 181d: getfield Medium.zy I
      // 1820: invokevirtual Medium.sin (I)F
      // 1823: fmul
      // 1824: fsub
      // 1825: f2i
      // 1826: iadd
      // 1827: istore 8
      // 1829: aload 0
      // 182a: getfield StageMaker.m LMedium;
      // 182d: getfield Medium.cz I
      // 1830: aload 0
      // 1831: getfield StageMaker.co [LContO;
      // 1834: aload 2
      // 1835: iload 5
      // 1837: iaload
      // 1838: aaload
      // 1839: getfield ContO.y I
      // 183c: aload 0
      // 183d: getfield StageMaker.m LMedium;
      // 1840: getfield Medium.y I
      // 1843: isub
      // 1844: aload 0
      // 1845: getfield StageMaker.m LMedium;
      // 1848: getfield Medium.cy I
      // 184b: isub
      // 184c: i2f
      // 184d: aload 0
      // 184e: getfield StageMaker.m LMedium;
      // 1851: aload 0
      // 1852: getfield StageMaker.m LMedium;
      // 1855: getfield Medium.zy I
      // 1858: invokevirtual Medium.sin (I)F
      // 185b: fmul
      // 185c: iload 7
      // 185e: aload 0
      // 185f: getfield StageMaker.m LMedium;
      // 1862: getfield Medium.cz I
      // 1865: isub
      // 1866: i2f
      // 1867: aload 0
      // 1868: getfield StageMaker.m LMedium;
      // 186b: aload 0
      // 186c: getfield StageMaker.m LMedium;
      // 186f: getfield Medium.zy I
      // 1872: invokevirtual Medium.cos (I)F
      // 1875: fmul
      // 1876: fadd
      // 1877: f2i
      // 1878: iadd
      // 1879: istore 9
      // 187b: ldc_w 1000000
      // 187e: aload 0
      // 187f: getfield StageMaker.sy I
      // 1882: invokestatic java/lang/Math.abs (I)I
      // 1885: idiv
      // 1886: istore 10
      // 1888: aload 0
      // 1889: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 188c: astore 11
      // 188e: aload 11
      // 1890: bipush 3
      // 1891: ldc_w 0.7
      // 1894: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 1897: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 189a: aload 0
      // 189b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 189e: new java/awt/Color
      // 18a1: dup
      // 18a2: bipush 0
      // 18a3: sipush 164
      // 18a6: sipush 255
      // 18a9: invokespecial java/awt/Color.<init> (III)V
      // 18ac: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 18af: aload 0
      // 18b0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 18b3: aload 0
      // 18b4: iload 6
      // 18b6: iload 9
      // 18b8: invokevirtual StageMaker.xs (II)I
      // 18bb: iload 10
      // 18bd: bipush 2
      // 18be: idiv
      // 18bf: isub
      // 18c0: aload 0
      // 18c1: iload 8
      // 18c3: iload 9
      // 18c5: invokevirtual StageMaker.ys (II)I
      // 18c8: iload 10
      // 18ca: bipush 2
      // 18cb: idiv
      // 18cc: isub
      // 18cd: iload 10
      // 18cf: iload 10
      // 18d1: invokevirtual java/awt/Graphics2D.fillOval (IIII)V
      // 18d4: aload 11
      // 18d6: bipush 3
      // 18d7: fconst_1
      // 18d8: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 18db: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 18de: aload 0
      // 18df: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 18e2: new java/awt/Color
      // 18e5: dup
      // 18e6: bipush 0
      // 18e7: bipush 0
      // 18e8: bipush 0
      // 18e9: invokespecial java/awt/Color.<init> (III)V
      // 18ec: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 18ef: aload 0
      // 18f0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 18f3: new java/awt/Font
      // 18f6: dup
      // 18f7: ldc_w "Arial"
      // 18fa: bipush 1
      // 18fb: bipush 12
      // 18fd: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1900: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1903: aload 0
      // 1904: aload 0
      // 1905: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1908: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 190b: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 190e: aload 0
      // 190f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1912: new java/lang/StringBuilder
      // 1915: dup
      // 1916: invokespecial java/lang/StringBuilder.<init> ()V
      // 1919: ldc_w "NO# "
      // 191c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 191f: aload 0
      // 1920: getfield StageMaker.arrcnt I
      // 1923: bipush 1
      // 1924: iadd
      // 1925: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1928: ldc ""
      // 192a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 192d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1930: aload 0
      // 1931: iload 6
      // 1933: iload 9
      // 1935: invokevirtual StageMaker.xs (II)I
      // 1938: aload 0
      // 1939: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 193c: new java/lang/StringBuilder
      // 193f: dup
      // 1940: invokespecial java/lang/StringBuilder.<init> ()V
      // 1943: ldc_w "NO# "
      // 1946: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1949: aload 0
      // 194a: getfield StageMaker.arrcnt I
      // 194d: bipush 1
      // 194e: iadd
      // 194f: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1952: ldc ""
      // 1954: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1957: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 195a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 195d: bipush 2
      // 195e: idiv
      // 195f: isub
      // 1960: aload 0
      // 1961: iload 8
      // 1963: iload 9
      // 1965: invokevirtual StageMaker.ys (II)I
      // 1968: iload 10
      // 196a: bipush 2
      // 196b: idiv
      // 196c: isub
      // 196d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1970: goto 1975
      // 1975: aload 0
      // 1976: getfield StageMaker.arrng Z
      // 1979: ifne 1981
      // 197c: goto 1c48
      // 1981: aload 0
      // 1982: getfield StageMaker.co [LContO;
      // 1985: aload 2
      // 1986: iload 5
      // 1988: iaload
      // 1989: aaload
      // 198a: getfield ContO.colok I
      // 198d: bipush 30
      // 198f: if_icmpne 1997
      // 1992: goto 19c3
      // 1997: aload 0
      // 1998: getfield StageMaker.co [LContO;
      // 199b: aload 2
      // 199c: iload 5
      // 199e: iaload
      // 199f: aaload
      // 19a0: getfield ContO.colok I
      // 19a3: bipush 32
      // 19a5: if_icmpne 19ad
      // 19a8: goto 19c3
      // 19ad: aload 0
      // 19ae: getfield StageMaker.co [LContO;
      // 19b1: aload 2
      // 19b2: iload 5
      // 19b4: iaload
      // 19b5: aaload
      // 19b6: getfield ContO.colok I
      // 19b9: bipush 54
      // 19bb: if_icmpeq 19c3
      // 19be: goto 1c48
      // 19c3: aload 0
      // 19c4: getfield StageMaker.co [LContO;
      // 19c7: aload 2
      // 19c8: iload 5
      // 19ca: iaload
      // 19cb: aaload
      // 19cc: getfield ContO.errd Z
      // 19cf: ifne 19d7
      // 19d2: goto 1c48
      // 19d7: aload 0
      // 19d8: getfield StageMaker.m LMedium;
      // 19db: getfield Medium.cx I
      // 19de: aload 0
      // 19df: getfield StageMaker.co [LContO;
      // 19e2: aload 2
      // 19e3: iload 5
      // 19e5: iaload
      // 19e6: aaload
      // 19e7: getfield ContO.x I
      // 19ea: aload 0
      // 19eb: getfield StageMaker.m LMedium;
      // 19ee: getfield Medium.x I
      // 19f1: isub
      // 19f2: aload 0
      // 19f3: getfield StageMaker.m LMedium;
      // 19f6: getfield Medium.cx I
      // 19f9: isub
      // 19fa: i2f
      // 19fb: aload 0
      // 19fc: getfield StageMaker.m LMedium;
      // 19ff: aload 0
      // 1a00: getfield StageMaker.m LMedium;
      // 1a03: getfield Medium.xz I
      // 1a06: invokevirtual Medium.cos (I)F
      // 1a09: fmul
      // 1a0a: aload 0
      // 1a0b: getfield StageMaker.co [LContO;
      // 1a0e: aload 2
      // 1a0f: iload 5
      // 1a11: iaload
      // 1a12: aaload
      // 1a13: getfield ContO.z I
      // 1a16: aload 0
      // 1a17: getfield StageMaker.m LMedium;
      // 1a1a: getfield Medium.z I
      // 1a1d: isub
      // 1a1e: aload 0
      // 1a1f: getfield StageMaker.m LMedium;
      // 1a22: getfield Medium.cz I
      // 1a25: isub
      // 1a26: i2f
      // 1a27: aload 0
      // 1a28: getfield StageMaker.m LMedium;
      // 1a2b: aload 0
      // 1a2c: getfield StageMaker.m LMedium;
      // 1a2f: getfield Medium.xz I
      // 1a32: invokevirtual Medium.sin (I)F
      // 1a35: fmul
      // 1a36: fsub
      // 1a37: f2i
      // 1a38: iadd
      // 1a39: istore 6
      // 1a3b: aload 0
      // 1a3c: getfield StageMaker.m LMedium;
      // 1a3f: getfield Medium.cz I
      // 1a42: aload 0
      // 1a43: getfield StageMaker.co [LContO;
      // 1a46: aload 2
      // 1a47: iload 5
      // 1a49: iaload
      // 1a4a: aaload
      // 1a4b: getfield ContO.x I
      // 1a4e: aload 0
      // 1a4f: getfield StageMaker.m LMedium;
      // 1a52: getfield Medium.x I
      // 1a55: isub
      // 1a56: aload 0
      // 1a57: getfield StageMaker.m LMedium;
      // 1a5a: getfield Medium.cx I
      // 1a5d: isub
      // 1a5e: i2f
      // 1a5f: aload 0
      // 1a60: getfield StageMaker.m LMedium;
      // 1a63: aload 0
      // 1a64: getfield StageMaker.m LMedium;
      // 1a67: getfield Medium.xz I
      // 1a6a: invokevirtual Medium.sin (I)F
      // 1a6d: fmul
      // 1a6e: aload 0
      // 1a6f: getfield StageMaker.co [LContO;
      // 1a72: aload 2
      // 1a73: iload 5
      // 1a75: iaload
      // 1a76: aaload
      // 1a77: getfield ContO.z I
      // 1a7a: aload 0
      // 1a7b: getfield StageMaker.m LMedium;
      // 1a7e: getfield Medium.z I
      // 1a81: isub
      // 1a82: aload 0
      // 1a83: getfield StageMaker.m LMedium;
      // 1a86: getfield Medium.cz I
      // 1a89: isub
      // 1a8a: i2f
      // 1a8b: aload 0
      // 1a8c: getfield StageMaker.m LMedium;
      // 1a8f: aload 0
      // 1a90: getfield StageMaker.m LMedium;
      // 1a93: getfield Medium.xz I
      // 1a96: invokevirtual Medium.cos (I)F
      // 1a99: fmul
      // 1a9a: fadd
      // 1a9b: f2i
      // 1a9c: iadd
      // 1a9d: istore 7
      // 1a9f: aload 0
      // 1aa0: getfield StageMaker.m LMedium;
      // 1aa3: getfield Medium.cy I
      // 1aa6: aload 0
      // 1aa7: getfield StageMaker.co [LContO;
      // 1aaa: aload 2
      // 1aab: iload 5
      // 1aad: iaload
      // 1aae: aaload
      // 1aaf: getfield ContO.y I
      // 1ab2: aload 0
      // 1ab3: getfield StageMaker.m LMedium;
      // 1ab6: getfield Medium.y I
      // 1ab9: isub
      // 1aba: aload 0
      // 1abb: getfield StageMaker.m LMedium;
      // 1abe: getfield Medium.cy I
      // 1ac1: isub
      // 1ac2: i2f
      // 1ac3: aload 0
      // 1ac4: getfield StageMaker.m LMedium;
      // 1ac7: aload 0
      // 1ac8: getfield StageMaker.m LMedium;
      // 1acb: getfield Medium.zy I
      // 1ace: invokevirtual Medium.cos (I)F
      // 1ad1: fmul
      // 1ad2: iload 7
      // 1ad4: aload 0
      // 1ad5: getfield StageMaker.m LMedium;
      // 1ad8: getfield Medium.cz I
      // 1adb: isub
      // 1adc: i2f
      // 1add: aload 0
      // 1ade: getfield StageMaker.m LMedium;
      // 1ae1: aload 0
      // 1ae2: getfield StageMaker.m LMedium;
      // 1ae5: getfield Medium.zy I
      // 1ae8: invokevirtual Medium.sin (I)F
      // 1aeb: fmul
      // 1aec: fsub
      // 1aed: f2i
      // 1aee: iadd
      // 1aef: istore 8
      // 1af1: aload 0
      // 1af2: getfield StageMaker.m LMedium;
      // 1af5: getfield Medium.cz I
      // 1af8: aload 0
      // 1af9: getfield StageMaker.co [LContO;
      // 1afc: aload 2
      // 1afd: iload 5
      // 1aff: iaload
      // 1b00: aaload
      // 1b01: getfield ContO.y I
      // 1b04: aload 0
      // 1b05: getfield StageMaker.m LMedium;
      // 1b08: getfield Medium.y I
      // 1b0b: isub
      // 1b0c: aload 0
      // 1b0d: getfield StageMaker.m LMedium;
      // 1b10: getfield Medium.cy I
      // 1b13: isub
      // 1b14: i2f
      // 1b15: aload 0
      // 1b16: getfield StageMaker.m LMedium;
      // 1b19: aload 0
      // 1b1a: getfield StageMaker.m LMedium;
      // 1b1d: getfield Medium.zy I
      // 1b20: invokevirtual Medium.sin (I)F
      // 1b23: fmul
      // 1b24: iload 7
      // 1b26: aload 0
      // 1b27: getfield StageMaker.m LMedium;
      // 1b2a: getfield Medium.cz I
      // 1b2d: isub
      // 1b2e: i2f
      // 1b2f: aload 0
      // 1b30: getfield StageMaker.m LMedium;
      // 1b33: aload 0
      // 1b34: getfield StageMaker.m LMedium;
      // 1b37: getfield Medium.zy I
      // 1b3a: invokevirtual Medium.cos (I)F
      // 1b3d: fmul
      // 1b3e: fadd
      // 1b3f: f2i
      // 1b40: iadd
      // 1b41: istore 9
      // 1b43: ldc_w 1000000
      // 1b46: aload 0
      // 1b47: getfield StageMaker.sy I
      // 1b4a: invokestatic java/lang/Math.abs (I)I
      // 1b4d: idiv
      // 1b4e: istore 10
      // 1b50: aload 0
      // 1b51: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1b54: astore 11
      // 1b56: aload 11
      // 1b58: bipush 3
      // 1b59: ldc 0.5
      // 1b5b: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 1b5e: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 1b61: aload 0
      // 1b62: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1b65: new java/awt/Color
      // 1b68: dup
      // 1b69: sipush 255
      // 1b6c: sipush 128
      // 1b6f: bipush 0
      // 1b70: invokespecial java/awt/Color.<init> (III)V
      // 1b73: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1b76: aload 0
      // 1b77: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1b7a: aload 0
      // 1b7b: iload 6
      // 1b7d: iload 9
      // 1b7f: invokevirtual StageMaker.xs (II)I
      // 1b82: iload 10
      // 1b84: bipush 2
      // 1b85: idiv
      // 1b86: isub
      // 1b87: aload 0
      // 1b88: iload 8
      // 1b8a: iload 9
      // 1b8c: invokevirtual StageMaker.ys (II)I
      // 1b8f: iload 10
      // 1b91: bipush 2
      // 1b92: idiv
      // 1b93: isub
      // 1b94: iload 10
      // 1b96: iload 10
      // 1b98: invokevirtual java/awt/Graphics2D.fillOval (IIII)V
      // 1b9b: aload 11
      // 1b9d: bipush 3
      // 1b9e: fconst_1
      // 1b9f: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 1ba2: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 1ba5: aload 0
      // 1ba6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1ba9: new java/awt/Color
      // 1bac: dup
      // 1bad: bipush 0
      // 1bae: bipush 0
      // 1baf: bipush 0
      // 1bb0: invokespecial java/awt/Color.<init> (III)V
      // 1bb3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1bb6: aload 0
      // 1bb7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1bba: new java/awt/Font
      // 1bbd: dup
      // 1bbe: ldc_w "Arial"
      // 1bc1: bipush 1
      // 1bc2: bipush 12
      // 1bc4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1bc7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1bca: aload 0
      // 1bcb: aload 0
      // 1bcc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1bcf: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1bd2: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 1bd5: aload 0
      // 1bd6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1bd9: new java/lang/StringBuilder
      // 1bdc: dup
      // 1bdd: invokespecial java/lang/StringBuilder.<init> ()V
      // 1be0: ldc_w "NO# "
      // 1be3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1be6: aload 0
      // 1be7: getfield StageMaker.co [LContO;
      // 1bea: aload 2
      // 1beb: iload 5
      // 1bed: iaload
      // 1bee: aaload
      // 1bef: getfield ContO.wh I
      // 1bf2: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1bf5: ldc ""
      // 1bf7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1bfa: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1bfd: aload 0
      // 1bfe: iload 6
      // 1c00: iload 9
      // 1c02: invokevirtual StageMaker.xs (II)I
      // 1c05: aload 0
      // 1c06: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 1c09: new java/lang/StringBuilder
      // 1c0c: dup
      // 1c0d: invokespecial java/lang/StringBuilder.<init> ()V
      // 1c10: ldc_w "NO# "
      // 1c13: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1c16: aload 0
      // 1c17: getfield StageMaker.co [LContO;
      // 1c1a: aload 2
      // 1c1b: iload 5
      // 1c1d: iaload
      // 1c1e: aaload
      // 1c1f: getfield ContO.wh I
      // 1c22: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1c25: ldc ""
      // 1c27: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1c2a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1c2d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 1c30: bipush 2
      // 1c31: idiv
      // 1c32: isub
      // 1c33: aload 0
      // 1c34: iload 8
      // 1c36: iload 9
      // 1c38: invokevirtual StageMaker.ys (II)I
      // 1c3b: iload 10
      // 1c3d: bipush 2
      // 1c3e: idiv
      // 1c3f: isub
      // 1c40: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1c43: goto 1c48
      // 1c48: aload 0
      // 1c49: getfield StageMaker.co [LContO;
      // 1c4c: aload 2
      // 1c4d: iload 5
      // 1c4f: iaload
      // 1c50: aaload
      // 1c51: aload 0
      // 1c52: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 1c55: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 1c58: aload 0
      // 1c59: getfield StageMaker.m LMedium;
      // 1c5c: getfield Medium.trk I
      // 1c5f: bipush 3
      // 1c60: if_icmpeq 1c68
      // 1c63: goto 1c75
      // 1c68: aload 0
      // 1c69: getfield StageMaker.m LMedium;
      // 1c6c: bipush 2
      // 1c6d: putfield Medium.trk I
      // 1c70: goto 1c75
      // 1c75: iinc 5 1
      // 1c78: goto 16b5
      // 1c7d: iinc 4 1
      // 1c80: goto 16a7
      // 1c85: aload 0
      // 1c86: getfield StageMaker.xm I
      // 1c89: sipush 248
      // 1c8c: if_icmpgt 1c94
      // 1c8f: goto 3644
      // 1c94: aload 0
      // 1c95: getfield StageMaker.xm I
      // 1c98: sipush 762
      // 1c9b: if_icmplt 1ca3
      // 1c9e: goto 3644
      // 1ca3: aload 0
      // 1ca4: getfield StageMaker.ym I
      // 1ca7: bipush 63
      // 1ca9: if_icmpgt 1cb1
      // 1cac: goto 3644
      // 1cb1: aload 0
      // 1cb2: getfield StageMaker.ym I
      // 1cb5: sipush 517
      // 1cb8: if_icmplt 1cc0
      // 1cbb: goto 3644
      // 1cc0: aload 0
      // 1cc1: getfield StageMaker.epart Z
      // 1cc4: ifeq 1ccc
      // 1cc7: goto 2f9b
      // 1ccc: aload 0
      // 1ccd: getfield StageMaker.arrng Z
      // 1cd0: ifeq 1cd8
      // 1cd3: goto 2f9b
      // 1cd8: aload 0
      // 1cd9: getfield StageMaker.bco [LContO;
      // 1cdc: aload 0
      // 1cdd: getfield StageMaker.sp I
      // 1ce0: aaload
      // 1ce1: aload 0
      // 1ce2: getfield StageMaker.xm I
      // 1ce5: sipush 505
      // 1ce8: isub
      // 1ce9: aload 0
      // 1cea: getfield StageMaker.sy I
      // 1ced: invokestatic java/lang/Math.abs (I)I
      // 1cf0: aload 0
      // 1cf1: getfield StageMaker.m LMedium;
      // 1cf4: getfield Medium.focus_point I
      // 1cf7: idiv
      // 1cf8: imul
      // 1cf9: aload 0
      // 1cfa: getfield StageMaker.sx I
      // 1cfd: iadd
      // 1cfe: putfield ContO.x I
      // 1d01: aload 0
      // 1d02: getfield StageMaker.bco [LContO;
      // 1d05: aload 0
      // 1d06: getfield StageMaker.sp I
      // 1d09: aaload
      // 1d0a: sipush 290
      // 1d0d: aload 0
      // 1d0e: getfield StageMaker.ym I
      // 1d11: isub
      // 1d12: aload 0
      // 1d13: getfield StageMaker.sy I
      // 1d16: invokestatic java/lang/Math.abs (I)I
      // 1d19: aload 0
      // 1d1a: getfield StageMaker.m LMedium;
      // 1d1d: getfield Medium.focus_point I
      // 1d20: idiv
      // 1d21: imul
      // 1d22: aload 0
      // 1d23: getfield StageMaker.sz I
      // 1d26: iadd
      // 1d27: putfield ContO.z I
      // 1d2a: aload 0
      // 1d2b: getfield StageMaker.bco [LContO;
      // 1d2e: aload 0
      // 1d2f: getfield StageMaker.sp I
      // 1d32: aaload
      // 1d33: aload 0
      // 1d34: getfield StageMaker.m LMedium;
      // 1d37: getfield Medium.ground I
      // 1d3a: aload 0
      // 1d3b: getfield StageMaker.bco [LContO;
      // 1d3e: aload 0
      // 1d3f: getfield StageMaker.sp I
      // 1d42: aaload
      // 1d43: getfield ContO.grat I
      // 1d46: isub
      // 1d47: putfield ContO.y I
      // 1d4a: aload 0
      // 1d4b: getfield StageMaker.bco [LContO;
      // 1d4e: aload 0
      // 1d4f: getfield StageMaker.sp I
      // 1d52: aaload
      // 1d53: aload 0
      // 1d54: getfield StageMaker.rot I
      // 1d57: aload 0
      // 1d58: getfield StageMaker.adrot I
      // 1d5b: iadd
      // 1d5c: putfield ContO.xz I
      // 1d5f: sipush 200
      // 1d62: istore 4
      // 1d64: bipush 0
      // 1d65: istore 5
      // 1d67: bipush 0
      // 1d68: istore 6
      // 1d6a: bipush 2
      // 1d6b: newarray 10
      // 1d6d: dup
      // 1d6e: bipush 0
      // 1d6f: aload 0
      // 1d70: getfield StageMaker.bco [LContO;
      // 1d73: aload 0
      // 1d74: getfield StageMaker.sp I
      // 1d77: aaload
      // 1d78: getfield ContO.x I
      // 1d7b: aload 0
      // 1d7c: getfield StageMaker.atp [[I
      // 1d7f: aload 0
      // 1d80: getfield StageMaker.sp I
      // 1d83: aaload
      // 1d84: bipush 0
      // 1d85: iaload
      // 1d86: iadd
      // 1d87: iastore
      // 1d88: dup
      // 1d89: bipush 1
      // 1d8a: aload 0
      // 1d8b: getfield StageMaker.bco [LContO;
      // 1d8e: aload 0
      // 1d8f: getfield StageMaker.sp I
      // 1d92: aaload
      // 1d93: getfield ContO.x I
      // 1d96: aload 0
      // 1d97: getfield StageMaker.atp [[I
      // 1d9a: aload 0
      // 1d9b: getfield StageMaker.sp I
      // 1d9e: aaload
      // 1d9f: bipush 2
      // 1da0: iaload
      // 1da1: iadd
      // 1da2: iastore
      // 1da3: astore 7
      // 1da5: bipush 2
      // 1da6: newarray 10
      // 1da8: dup
      // 1da9: bipush 0
      // 1daa: aload 0
      // 1dab: getfield StageMaker.bco [LContO;
      // 1dae: aload 0
      // 1daf: getfield StageMaker.sp I
      // 1db2: aaload
      // 1db3: getfield ContO.z I
      // 1db6: aload 0
      // 1db7: getfield StageMaker.atp [[I
      // 1dba: aload 0
      // 1dbb: getfield StageMaker.sp I
      // 1dbe: aaload
      // 1dbf: bipush 1
      // 1dc0: iaload
      // 1dc1: iadd
      // 1dc2: iastore
      // 1dc3: dup
      // 1dc4: bipush 1
      // 1dc5: aload 0
      // 1dc6: getfield StageMaker.bco [LContO;
      // 1dc9: aload 0
      // 1dca: getfield StageMaker.sp I
      // 1dcd: aaload
      // 1dce: getfield ContO.z I
      // 1dd1: aload 0
      // 1dd2: getfield StageMaker.atp [[I
      // 1dd5: aload 0
      // 1dd6: getfield StageMaker.sp I
      // 1dd9: aaload
      // 1dda: bipush 3
      // 1ddb: iaload
      // 1ddc: iadd
      // 1ddd: iastore
      // 1dde: astore 8
      // 1de0: aload 0
      // 1de1: aload 7
      // 1de3: aload 8
      // 1de5: aload 0
      // 1de6: getfield StageMaker.bco [LContO;
      // 1de9: aload 0
      // 1dea: getfield StageMaker.sp I
      // 1ded: aaload
      // 1dee: getfield ContO.x I
      // 1df1: aload 0
      // 1df2: getfield StageMaker.bco [LContO;
      // 1df5: aload 0
      // 1df6: getfield StageMaker.sp I
      // 1df9: aaload
      // 1dfa: getfield ContO.z I
      // 1dfd: aload 0
      // 1dfe: getfield StageMaker.rot I
      // 1e01: bipush 2
      // 1e02: invokevirtual StageMaker.rot ([I[IIIII)V
      // 1e05: bipush 0
      // 1e06: istore 9
      // 1e08: aload 0
      // 1e09: bipush 0
      // 1e0a: putfield StageMaker.onfly Z
      // 1e0d: sipush 500
      // 1e10: istore 10
      // 1e12: bipush 0
      // 1e13: istore 11
      // 1e15: iload 11
      // 1e17: aload 0
      // 1e18: getfield StageMaker.nob I
      // 1e1b: if_icmplt 1e23
      // 1e1e: goto 28b7
      // 1e23: bipush 2
      // 1e24: newarray 10
      // 1e26: dup
      // 1e27: bipush 0
      // 1e28: aload 0
      // 1e29: getfield StageMaker.co [LContO;
      // 1e2c: iload 11
      // 1e2e: aaload
      // 1e2f: getfield ContO.x I
      // 1e32: aload 0
      // 1e33: getfield StageMaker.atp [[I
      // 1e36: aload 0
      // 1e37: getfield StageMaker.co [LContO;
      // 1e3a: iload 11
      // 1e3c: aaload
      // 1e3d: getfield ContO.colok I
      // 1e40: aaload
      // 1e41: bipush 0
      // 1e42: iaload
      // 1e43: iadd
      // 1e44: iastore
      // 1e45: dup
      // 1e46: bipush 1
      // 1e47: aload 0
      // 1e48: getfield StageMaker.co [LContO;
      // 1e4b: iload 11
      // 1e4d: aaload
      // 1e4e: getfield ContO.x I
      // 1e51: aload 0
      // 1e52: getfield StageMaker.atp [[I
      // 1e55: aload 0
      // 1e56: getfield StageMaker.co [LContO;
      // 1e59: iload 11
      // 1e5b: aaload
      // 1e5c: getfield ContO.colok I
      // 1e5f: aaload
      // 1e60: bipush 2
      // 1e61: iaload
      // 1e62: iadd
      // 1e63: iastore
      // 1e64: astore 12
      // 1e66: bipush 2
      // 1e67: newarray 10
      // 1e69: dup
      // 1e6a: bipush 0
      // 1e6b: aload 0
      // 1e6c: getfield StageMaker.co [LContO;
      // 1e6f: iload 11
      // 1e71: aaload
      // 1e72: getfield ContO.z I
      // 1e75: aload 0
      // 1e76: getfield StageMaker.atp [[I
      // 1e79: aload 0
      // 1e7a: getfield StageMaker.co [LContO;
      // 1e7d: iload 11
      // 1e7f: aaload
      // 1e80: getfield ContO.colok I
      // 1e83: aaload
      // 1e84: bipush 1
      // 1e85: iaload
      // 1e86: iadd
      // 1e87: iastore
      // 1e88: dup
      // 1e89: bipush 1
      // 1e8a: aload 0
      // 1e8b: getfield StageMaker.co [LContO;
      // 1e8e: iload 11
      // 1e90: aaload
      // 1e91: getfield ContO.z I
      // 1e94: aload 0
      // 1e95: getfield StageMaker.atp [[I
      // 1e98: aload 0
      // 1e99: getfield StageMaker.co [LContO;
      // 1e9c: iload 11
      // 1e9e: aaload
      // 1e9f: getfield ContO.colok I
      // 1ea2: aaload
      // 1ea3: bipush 3
      // 1ea4: iaload
      // 1ea5: iadd
      // 1ea6: iastore
      // 1ea7: astore 13
      // 1ea9: aload 0
      // 1eaa: getfield StageMaker.co [LContO;
      // 1ead: iload 11
      // 1eaf: aaload
      // 1eb0: getfield ContO.roofat I
      // 1eb3: istore 14
      // 1eb5: aload 0
      // 1eb6: getfield StageMaker.co [LContO;
      // 1eb9: iload 11
      // 1ebb: aaload
      // 1ebc: getfield ContO.colok I
      // 1ebf: bipush 2
      // 1ec0: if_icmpeq 1ec8
      // 1ec3: goto 1ed0
      // 1ec8: iinc 14 30
      // 1ecb: goto 1ed0
      // 1ed0: aload 0
      // 1ed1: getfield StageMaker.co [LContO;
      // 1ed4: iload 11
      // 1ed6: aaload
      // 1ed7: getfield ContO.colok I
      // 1eda: bipush 3
      // 1edb: if_icmpeq 1ee3
      // 1ede: goto 1eeb
      // 1ee3: iinc 14 -30
      // 1ee6: goto 1eeb
      // 1eeb: aload 0
      // 1eec: getfield StageMaker.co [LContO;
      // 1eef: iload 11
      // 1ef1: aaload
      // 1ef2: getfield ContO.colok I
      // 1ef5: bipush 15
      // 1ef7: if_icmpeq 1eff
      // 1efa: goto 1f07
      // 1eff: iinc 14 -90
      // 1f02: goto 1f07
      // 1f07: aload 0
      // 1f08: getfield StageMaker.co [LContO;
      // 1f0b: iload 11
      // 1f0d: aaload
      // 1f0e: getfield ContO.colok I
      // 1f11: bipush 20
      // 1f13: if_icmpeq 1f1b
      // 1f16: goto 1f26
      // 1f1b: wide iinc 14 -180
      // 1f21: goto 1f26
      // 1f26: aload 0
      // 1f27: getfield StageMaker.co [LContO;
      // 1f2a: iload 11
      // 1f2c: aaload
      // 1f2d: getfield ContO.colok I
      // 1f30: bipush 26
      // 1f32: if_icmpeq 1f3a
      // 1f35: goto 1f42
      // 1f3a: iinc 14 -90
      // 1f3d: goto 1f42
      // 1f42: aload 0
      // 1f43: aload 12
      // 1f45: aload 13
      // 1f47: aload 0
      // 1f48: getfield StageMaker.co [LContO;
      // 1f4b: iload 11
      // 1f4d: aaload
      // 1f4e: getfield ContO.x I
      // 1f51: aload 0
      // 1f52: getfield StageMaker.co [LContO;
      // 1f55: iload 11
      // 1f57: aaload
      // 1f58: getfield ContO.z I
      // 1f5b: iload 14
      // 1f5d: bipush 2
      // 1f5e: invokevirtual StageMaker.rot ([I[IIIII)V
      // 1f61: aload 0
      // 1f62: getfield StageMaker.sp I
      // 1f65: bipush 54
      // 1f67: if_icmple 1f6f
      // 1f6a: goto 20a0
      // 1f6f: aload 0
      // 1f70: aload 12
      // 1f72: bipush 0
      // 1f73: iaload
      // 1f74: aload 7
      // 1f76: bipush 0
      // 1f77: iaload
      // 1f78: aload 13
      // 1f7a: bipush 0
      // 1f7b: iaload
      // 1f7c: aload 8
      // 1f7e: bipush 0
      // 1f7f: iaload
      // 1f80: invokevirtual StageMaker.py (IIII)I
      // 1f83: istore 15
      // 1f85: iload 15
      // 1f87: iload 4
      // 1f89: if_icmplt 1f91
      // 1f8c: goto 1fba
      // 1f91: iload 15
      // 1f93: ifne 1f9b
      // 1f96: goto 1fba
      // 1f9b: iload 15
      // 1f9d: istore 4
      // 1f9f: aload 12
      // 1fa1: bipush 0
      // 1fa2: iaload
      // 1fa3: aload 7
      // 1fa5: bipush 0
      // 1fa6: iaload
      // 1fa7: isub
      // 1fa8: istore 5
      // 1faa: aload 13
      // 1fac: bipush 0
      // 1fad: iaload
      // 1fae: aload 8
      // 1fb0: bipush 0
      // 1fb1: iaload
      // 1fb2: isub
      // 1fb3: istore 6
      // 1fb5: goto 1fba
      // 1fba: aload 0
      // 1fbb: aload 12
      // 1fbd: bipush 1
      // 1fbe: iaload
      // 1fbf: aload 7
      // 1fc1: bipush 0
      // 1fc2: iaload
      // 1fc3: aload 13
      // 1fc5: bipush 1
      // 1fc6: iaload
      // 1fc7: aload 8
      // 1fc9: bipush 0
      // 1fca: iaload
      // 1fcb: invokevirtual StageMaker.py (IIII)I
      // 1fce: istore 15
      // 1fd0: iload 15
      // 1fd2: iload 4
      // 1fd4: if_icmplt 1fdc
      // 1fd7: goto 2005
      // 1fdc: iload 15
      // 1fde: ifne 1fe6
      // 1fe1: goto 2005
      // 1fe6: iload 15
      // 1fe8: istore 4
      // 1fea: aload 12
      // 1fec: bipush 1
      // 1fed: iaload
      // 1fee: aload 7
      // 1ff0: bipush 0
      // 1ff1: iaload
      // 1ff2: isub
      // 1ff3: istore 5
      // 1ff5: aload 13
      // 1ff7: bipush 1
      // 1ff8: iaload
      // 1ff9: aload 8
      // 1ffb: bipush 0
      // 1ffc: iaload
      // 1ffd: isub
      // 1ffe: istore 6
      // 2000: goto 2005
      // 2005: aload 0
      // 2006: aload 12
      // 2008: bipush 1
      // 2009: iaload
      // 200a: aload 7
      // 200c: bipush 1
      // 200d: iaload
      // 200e: aload 13
      // 2010: bipush 1
      // 2011: iaload
      // 2012: aload 8
      // 2014: bipush 1
      // 2015: iaload
      // 2016: invokevirtual StageMaker.py (IIII)I
      // 2019: istore 15
      // 201b: iload 15
      // 201d: iload 4
      // 201f: if_icmplt 2027
      // 2022: goto 2050
      // 2027: iload 15
      // 2029: ifne 2031
      // 202c: goto 2050
      // 2031: iload 15
      // 2033: istore 4
      // 2035: aload 12
      // 2037: bipush 1
      // 2038: iaload
      // 2039: aload 7
      // 203b: bipush 1
      // 203c: iaload
      // 203d: isub
      // 203e: istore 5
      // 2040: aload 13
      // 2042: bipush 1
      // 2043: iaload
      // 2044: aload 8
      // 2046: bipush 1
      // 2047: iaload
      // 2048: isub
      // 2049: istore 6
      // 204b: goto 2050
      // 2050: aload 0
      // 2051: aload 12
      // 2053: bipush 0
      // 2054: iaload
      // 2055: aload 7
      // 2057: bipush 1
      // 2058: iaload
      // 2059: aload 13
      // 205b: bipush 0
      // 205c: iaload
      // 205d: aload 8
      // 205f: bipush 1
      // 2060: iaload
      // 2061: invokevirtual StageMaker.py (IIII)I
      // 2064: istore 15
      // 2066: iload 15
      // 2068: iload 4
      // 206a: if_icmplt 2072
      // 206d: goto 209b
      // 2072: iload 15
      // 2074: ifne 207c
      // 2077: goto 209b
      // 207c: iload 15
      // 207e: istore 4
      // 2080: aload 12
      // 2082: bipush 0
      // 2083: iaload
      // 2084: aload 7
      // 2086: bipush 1
      // 2087: iaload
      // 2088: isub
      // 2089: istore 5
      // 208b: aload 13
      // 208d: bipush 0
      // 208e: iaload
      // 208f: aload 8
      // 2091: bipush 1
      // 2092: iaload
      // 2093: isub
      // 2094: istore 6
      // 2096: goto 209b
      // 209b: goto 20a0
      // 20a0: aload 0
      // 20a1: getfield StageMaker.sptyp I
      // 20a4: bipush 3
      // 20a5: if_icmpeq 20ad
      // 20a8: goto 225c
      // 20ad: aload 0
      // 20ae: aload 12
      // 20b0: bipush 0
      // 20b1: iaload
      // 20b2: aload 7
      // 20b4: bipush 0
      // 20b5: iaload
      // 20b6: aload 13
      // 20b8: bipush 0
      // 20b9: iaload
      // 20ba: aload 8
      // 20bc: bipush 0
      // 20bd: iaload
      // 20be: invokevirtual StageMaker.py (IIII)I
      // 20c1: ifne 20c9
      // 20c4: goto 225c
      // 20c9: aload 0
      // 20ca: aload 12
      // 20cc: bipush 1
      // 20cd: iaload
      // 20ce: aload 7
      // 20d0: bipush 0
      // 20d1: iaload
      // 20d2: aload 13
      // 20d4: bipush 1
      // 20d5: iaload
      // 20d6: aload 8
      // 20d8: bipush 0
      // 20d9: iaload
      // 20da: invokevirtual StageMaker.py (IIII)I
      // 20dd: ifne 20e5
      // 20e0: goto 225c
      // 20e5: bipush 0
      // 20e6: istore 15
      // 20e8: iload 15
      // 20ea: aload 0
      // 20eb: getfield StageMaker.rcheckp [I
      // 20ee: arraylength
      // 20ef: if_icmplt 20f7
      // 20f2: goto 219e
      // 20f7: aload 0
      // 20f8: getfield StageMaker.co [LContO;
      // 20fb: iload 11
      // 20fd: aaload
      // 20fe: getfield ContO.colok I
      // 2101: aload 0
      // 2102: getfield StageMaker.rcheckp [I
      // 2105: iload 15
      // 2107: iaload
      // 2108: if_icmpeq 2110
      // 210b: goto 2196
      // 2110: aload 0
      // 2111: aload 12
      // 2113: bipush 0
      // 2114: iaload
      // 2115: aload 7
      // 2117: bipush 0
      // 2118: iaload
      // 2119: aload 13
      // 211b: bipush 0
      // 211c: iaload
      // 211d: aload 8
      // 211f: bipush 0
      // 2120: iaload
      // 2121: invokevirtual StageMaker.py (IIII)I
      // 2124: iload 9
      // 2126: if_icmpgt 212e
      // 2129: goto 2138
      // 212e: iload 9
      // 2130: ifeq 2138
      // 2133: goto 2158
      // 2138: aload 0
      // 2139: aload 12
      // 213b: bipush 0
      // 213c: iaload
      // 213d: aload 7
      // 213f: bipush 0
      // 2140: iaload
      // 2141: aload 13
      // 2143: bipush 0
      // 2144: iaload
      // 2145: aload 8
      // 2147: bipush 0
      // 2148: iaload
      // 2149: invokevirtual StageMaker.py (IIII)I
      // 214c: istore 9
      // 214e: aload 0
      // 214f: bipush 0
      // 2150: putfield StageMaker.onoff Z
      // 2153: goto 2158
      // 2158: aload 0
      // 2159: aload 12
      // 215b: bipush 1
      // 215c: iaload
      // 215d: aload 7
      // 215f: bipush 0
      // 2160: iaload
      // 2161: aload 13
      // 2163: bipush 1
      // 2164: iaload
      // 2165: aload 8
      // 2167: bipush 0
      // 2168: iaload
      // 2169: invokevirtual StageMaker.py (IIII)I
      // 216c: iload 9
      // 216e: if_icmple 2176
      // 2171: goto 2196
      // 2176: aload 0
      // 2177: aload 12
      // 2179: bipush 1
      // 217a: iaload
      // 217b: aload 7
      // 217d: bipush 0
      // 217e: iaload
      // 217f: aload 13
      // 2181: bipush 1
      // 2182: iaload
      // 2183: aload 8
      // 2185: bipush 0
      // 2186: iaload
      // 2187: invokevirtual StageMaker.py (IIII)I
      // 218a: istore 9
      // 218c: aload 0
      // 218d: bipush 0
      // 218e: putfield StageMaker.onoff Z
      // 2191: goto 2196
      // 2196: iinc 15 1
      // 2199: goto 20e8
      // 219e: bipush 0
      // 219f: istore 15
      // 21a1: iload 15
      // 21a3: aload 0
      // 21a4: getfield StageMaker.ocheckp [I
      // 21a7: arraylength
      // 21a8: if_icmplt 21b0
      // 21ab: goto 2257
      // 21b0: aload 0
      // 21b1: getfield StageMaker.co [LContO;
      // 21b4: iload 11
      // 21b6: aaload
      // 21b7: getfield ContO.colok I
      // 21ba: aload 0
      // 21bb: getfield StageMaker.ocheckp [I
      // 21be: iload 15
      // 21c0: iaload
      // 21c1: if_icmpeq 21c9
      // 21c4: goto 224f
      // 21c9: aload 0
      // 21ca: aload 12
      // 21cc: bipush 0
      // 21cd: iaload
      // 21ce: aload 7
      // 21d0: bipush 0
      // 21d1: iaload
      // 21d2: aload 13
      // 21d4: bipush 0
      // 21d5: iaload
      // 21d6: aload 8
      // 21d8: bipush 0
      // 21d9: iaload
      // 21da: invokevirtual StageMaker.py (IIII)I
      // 21dd: iload 9
      // 21df: if_icmpgt 21e7
      // 21e2: goto 21f1
      // 21e7: iload 9
      // 21e9: ifeq 21f1
      // 21ec: goto 2211
      // 21f1: aload 0
      // 21f2: aload 12
      // 21f4: bipush 0
      // 21f5: iaload
      // 21f6: aload 7
      // 21f8: bipush 0
      // 21f9: iaload
      // 21fa: aload 13
      // 21fc: bipush 0
      // 21fd: iaload
      // 21fe: aload 8
      // 2200: bipush 0
      // 2201: iaload
      // 2202: invokevirtual StageMaker.py (IIII)I
      // 2205: istore 9
      // 2207: aload 0
      // 2208: bipush 1
      // 2209: putfield StageMaker.onoff Z
      // 220c: goto 2211
      // 2211: aload 0
      // 2212: aload 12
      // 2214: bipush 1
      // 2215: iaload
      // 2216: aload 7
      // 2218: bipush 0
      // 2219: iaload
      // 221a: aload 13
      // 221c: bipush 1
      // 221d: iaload
      // 221e: aload 8
      // 2220: bipush 0
      // 2221: iaload
      // 2222: invokevirtual StageMaker.py (IIII)I
      // 2225: iload 9
      // 2227: if_icmple 222f
      // 222a: goto 224f
      // 222f: aload 0
      // 2230: aload 12
      // 2232: bipush 1
      // 2233: iaload
      // 2234: aload 7
      // 2236: bipush 0
      // 2237: iaload
      // 2238: aload 13
      // 223a: bipush 1
      // 223b: iaload
      // 223c: aload 8
      // 223e: bipush 0
      // 223f: iaload
      // 2240: invokevirtual StageMaker.py (IIII)I
      // 2243: istore 9
      // 2245: aload 0
      // 2246: bipush 1
      // 2247: putfield StageMaker.onoff Z
      // 224a: goto 224f
      // 224f: iinc 15 1
      // 2252: goto 21a1
      // 2257: goto 225c
      // 225c: aload 0
      // 225d: getfield StageMaker.sp I
      // 2260: bipush 12
      // 2262: if_icmpgt 226a
      // 2265: goto 2278
      // 226a: aload 0
      // 226b: getfield StageMaker.sp I
      // 226e: bipush 33
      // 2270: if_icmpge 2278
      // 2273: goto 22b0
      // 2278: aload 0
      // 2279: getfield StageMaker.sp I
      // 227c: bipush 35
      // 227e: if_icmpne 2286
      // 2281: goto 22b0
      // 2286: aload 0
      // 2287: getfield StageMaker.sp I
      // 228a: bipush 36
      // 228c: if_icmpne 2294
      // 228f: goto 22b0
      // 2294: aload 0
      // 2295: getfield StageMaker.sp I
      // 2298: bipush 39
      // 229a: if_icmpge 22a2
      // 229d: goto 24be
      // 22a2: aload 0
      // 22a3: getfield StageMaker.sp I
      // 22a6: bipush 54
      // 22a8: if_icmple 22b0
      // 22ab: goto 24be
      // 22b0: aload 0
      // 22b1: getfield StageMaker.rot I
      // 22b4: ifne 22bc
      // 22b7: goto 22e7
      // 22bc: aload 0
      // 22bd: getfield StageMaker.rot I
      // 22c0: sipush 180
      // 22c3: if_icmpne 22cb
      // 22c6: goto 22e7
      // 22cb: aload 0
      // 22cc: getfield StageMaker.sp I
      // 22cf: bipush 26
      // 22d1: if_icmpne 22d9
      // 22d4: goto 22e7
      // 22d9: aload 0
      // 22da: getfield StageMaker.sp I
      // 22dd: bipush 15
      // 22df: if_icmpeq 22e7
      // 22e2: goto 23b6
      // 22e7: iload 14
      // 22e9: ifne 22f1
      // 22ec: goto 231a
      // 22f1: iload 14
      // 22f3: sipush 180
      // 22f6: if_icmpne 22fe
      // 22f9: goto 231a
      // 22fe: aload 0
      // 22ff: getfield StageMaker.sp I
      // 2302: bipush 26
      // 2304: if_icmpne 230c
      // 2307: goto 231a
      // 230c: aload 0
      // 230d: getfield StageMaker.sp I
      // 2310: bipush 15
      // 2312: if_icmpeq 231a
      // 2315: goto 23b6
      // 231a: aload 12
      // 231c: bipush 0
      // 231d: iaload
      // 231e: aload 7
      // 2320: bipush 0
      // 2321: iaload
      // 2322: isub
      // 2323: invokestatic java/lang/Math.abs (I)I
      // 2326: sipush 200
      // 2329: if_icmplt 2331
      // 232c: goto 2341
      // 2331: aload 12
      // 2333: bipush 0
      // 2334: iaload
      // 2335: aload 7
      // 2337: bipush 0
      // 2338: iaload
      // 2339: isub
      // 233a: istore 5
      // 233c: goto 2341
      // 2341: aload 12
      // 2343: bipush 0
      // 2344: iaload
      // 2345: aload 7
      // 2347: bipush 1
      // 2348: iaload
      // 2349: isub
      // 234a: invokestatic java/lang/Math.abs (I)I
      // 234d: sipush 200
      // 2350: if_icmplt 2358
      // 2353: goto 2368
      // 2358: aload 12
      // 235a: bipush 0
      // 235b: iaload
      // 235c: aload 7
      // 235e: bipush 1
      // 235f: iaload
      // 2360: isub
      // 2361: istore 5
      // 2363: goto 2368
      // 2368: aload 12
      // 236a: bipush 1
      // 236b: iaload
      // 236c: aload 7
      // 236e: bipush 1
      // 236f: iaload
      // 2370: isub
      // 2371: invokestatic java/lang/Math.abs (I)I
      // 2374: sipush 200
      // 2377: if_icmplt 237f
      // 237a: goto 238f
      // 237f: aload 12
      // 2381: bipush 1
      // 2382: iaload
      // 2383: aload 7
      // 2385: bipush 1
      // 2386: iaload
      // 2387: isub
      // 2388: istore 5
      // 238a: goto 238f
      // 238f: aload 12
      // 2391: bipush 1
      // 2392: iaload
      // 2393: aload 7
      // 2395: bipush 0
      // 2396: iaload
      // 2397: isub
      // 2398: invokestatic java/lang/Math.abs (I)I
      // 239b: sipush 200
      // 239e: if_icmplt 23a6
      // 23a1: goto 23b6
      // 23a6: aload 12
      // 23a8: bipush 1
      // 23a9: iaload
      // 23aa: aload 7
      // 23ac: bipush 0
      // 23ad: iaload
      // 23ae: isub
      // 23af: istore 5
      // 23b1: goto 23b6
      // 23b6: aload 0
      // 23b7: getfield StageMaker.rot I
      // 23ba: bipush 90
      // 23bc: if_icmpne 23c4
      // 23bf: goto 23ee
      // 23c4: aload 0
      // 23c5: getfield StageMaker.rot I
      // 23c8: bipush -90
      // 23ca: if_icmpne 23d2
      // 23cd: goto 23ee
      // 23d2: aload 0
      // 23d3: getfield StageMaker.sp I
      // 23d6: bipush 26
      // 23d8: if_icmpne 23e0
      // 23db: goto 23ee
      // 23e0: aload 0
      // 23e1: getfield StageMaker.sp I
      // 23e4: bipush 15
      // 23e6: if_icmpeq 23ee
      // 23e9: goto 24be
      // 23ee: iload 14
      // 23f0: bipush 90
      // 23f2: if_icmpne 23fa
      // 23f5: goto 2422
      // 23fa: iload 14
      // 23fc: bipush -90
      // 23fe: if_icmpne 2406
      // 2401: goto 2422
      // 2406: aload 0
      // 2407: getfield StageMaker.sp I
      // 240a: bipush 26
      // 240c: if_icmpne 2414
      // 240f: goto 2422
      // 2414: aload 0
      // 2415: getfield StageMaker.sp I
      // 2418: bipush 15
      // 241a: if_icmpeq 2422
      // 241d: goto 24be
      // 2422: aload 13
      // 2424: bipush 0
      // 2425: iaload
      // 2426: aload 8
      // 2428: bipush 0
      // 2429: iaload
      // 242a: isub
      // 242b: invokestatic java/lang/Math.abs (I)I
      // 242e: sipush 200
      // 2431: if_icmplt 2439
      // 2434: goto 2449
      // 2439: aload 13
      // 243b: bipush 0
      // 243c: iaload
      // 243d: aload 8
      // 243f: bipush 0
      // 2440: iaload
      // 2441: isub
      // 2442: istore 6
      // 2444: goto 2449
      // 2449: aload 13
      // 244b: bipush 0
      // 244c: iaload
      // 244d: aload 8
      // 244f: bipush 1
      // 2450: iaload
      // 2451: isub
      // 2452: invokestatic java/lang/Math.abs (I)I
      // 2455: sipush 200
      // 2458: if_icmplt 2460
      // 245b: goto 2470
      // 2460: aload 13
      // 2462: bipush 0
      // 2463: iaload
      // 2464: aload 8
      // 2466: bipush 1
      // 2467: iaload
      // 2468: isub
      // 2469: istore 6
      // 246b: goto 2470
      // 2470: aload 13
      // 2472: bipush 1
      // 2473: iaload
      // 2474: aload 8
      // 2476: bipush 1
      // 2477: iaload
      // 2478: isub
      // 2479: invokestatic java/lang/Math.abs (I)I
      // 247c: sipush 200
      // 247f: if_icmplt 2487
      // 2482: goto 2497
      // 2487: aload 13
      // 2489: bipush 1
      // 248a: iaload
      // 248b: aload 8
      // 248d: bipush 1
      // 248e: iaload
      // 248f: isub
      // 2490: istore 6
      // 2492: goto 2497
      // 2497: aload 13
      // 2499: bipush 1
      // 249a: iaload
      // 249b: aload 8
      // 249d: bipush 0
      // 249e: iaload
      // 249f: isub
      // 24a0: invokestatic java/lang/Math.abs (I)I
      // 24a3: sipush 200
      // 24a6: if_icmplt 24ae
      // 24a9: goto 24be
      // 24ae: aload 13
      // 24b0: bipush 1
      // 24b1: iaload
      // 24b2: aload 8
      // 24b4: bipush 0
      // 24b5: iaload
      // 24b6: isub
      // 24b7: istore 6
      // 24b9: goto 24be
      // 24be: aload 0
      // 24bf: getfield StageMaker.sptyp I
      // 24c2: bipush 3
      // 24c3: if_icmpeq 24cb
      // 24c6: goto 28af
      // 24cb: aload 0
      // 24cc: getfield StageMaker.co [LContO;
      // 24cf: iload 11
      // 24d1: aaload
      // 24d2: getfield ContO.colok I
      // 24d5: bipush 46
      // 24d7: if_icmpge 24df
      // 24da: goto 28af
      // 24df: aload 0
      // 24e0: getfield StageMaker.co [LContO;
      // 24e3: iload 11
      // 24e5: aaload
      // 24e6: getfield ContO.colok I
      // 24e9: bipush 51
      // 24eb: if_icmple 24f3
      // 24ee: goto 28af
      // 24f3: bipush 6
      // 24f5: newarray 10
      // 24f7: dup
      // 24f8: bipush 0
      // 24f9: bipush 2
      // 24fa: iastore
      // 24fb: dup
      // 24fc: bipush 1
      // 24fd: bipush 3
      // 24fe: iastore
      // 24ff: dup
      // 2500: bipush 2
      // 2501: bipush 5
      // 2502: iastore
      // 2503: dup
      // 2504: bipush 3
      // 2505: bipush 2
      // 2506: iastore
      // 2507: dup
      // 2508: bipush 4
      // 2509: bipush 3
      // 250a: iastore
      // 250b: dup
      // 250c: bipush 5
      // 250d: bipush 3
      // 250e: iastore
      // 250f: astore 15
      // 2511: aload 0
      // 2512: getfield StageMaker.co [LContO;
      // 2515: iload 11
      // 2517: aaload
      // 2518: getfield ContO.roofat I
      // 251b: invokestatic java/lang/Math.abs (I)I
      // 251e: sipush 180
      // 2521: if_icmpne 2529
      // 2524: goto 253b
      // 2529: aload 0
      // 252a: getfield StageMaker.co [LContO;
      // 252d: iload 11
      // 252f: aaload
      // 2530: getfield ContO.roofat I
      // 2533: ifeq 253b
      // 2536: goto 26e6
      // 253b: aload 0
      // 253c: getfield StageMaker.rot I
      // 253f: ifeq 2547
      // 2542: goto 26e6
      // 2547: aload 0
      // 2548: getfield StageMaker.bco [LContO;
      // 254b: aload 0
      // 254c: getfield StageMaker.sp I
      // 254f: aaload
      // 2550: getfield ContO.x I
      // 2553: aload 0
      // 2554: getfield StageMaker.co [LContO;
      // 2557: iload 11
      // 2559: aaload
      // 255a: getfield ContO.x I
      // 255d: isub
      // 255e: invokestatic java/lang/Math.abs (I)I
      // 2561: sipush 500
      // 2564: if_icmplt 256c
      // 2567: goto 26e6
      // 256c: aload 0
      // 256d: getfield StageMaker.bco [LContO;
      // 2570: aload 0
      // 2571: getfield StageMaker.sp I
      // 2574: aaload
      // 2575: getfield ContO.z I
      // 2578: aload 0
      // 2579: getfield StageMaker.co [LContO;
      // 257c: iload 11
      // 257e: aaload
      // 257f: getfield ContO.z I
      // 2582: isub
      // 2583: invokestatic java/lang/Math.abs (I)I
      // 2586: sipush 3000
      // 2589: if_icmplt 2591
      // 258c: goto 26e6
      // 2591: bipush 0
      // 2592: istore 16
      // 2594: iload 16
      // 2596: aload 15
      // 2598: aload 0
      // 2599: getfield StageMaker.co [LContO;
      // 259c: iload 11
      // 259e: aaload
      // 259f: getfield ContO.colok I
      // 25a2: bipush 46
      // 25a4: isub
      // 25a5: iaload
      // 25a6: if_icmplt 25ae
      // 25a9: goto 26e1
      // 25ae: bipush 0
      // 25af: istore 17
      // 25b1: iload 17
      // 25b3: aload 0
      // 25b4: getfield StageMaker.co [LContO;
      // 25b7: iload 11
      // 25b9: aaload
      // 25ba: getfield ContO.p [LPlane;
      // 25bd: iload 16
      // 25bf: aaload
      // 25c0: getfield Plane.n I
      // 25c3: if_icmplt 25cb
      // 25c6: goto 26d9
      // 25cb: aload 0
      // 25cc: aload 0
      // 25cd: getfield StageMaker.bco [LContO;
      // 25d0: aload 0
      // 25d1: getfield StageMaker.sp I
      // 25d4: aaload
      // 25d5: getfield ContO.x I
      // 25d8: aload 0
      // 25d9: getfield StageMaker.co [LContO;
      // 25dc: iload 11
      // 25de: aaload
      // 25df: getfield ContO.x I
      // 25e2: aload 0
      // 25e3: getfield StageMaker.bco [LContO;
      // 25e6: aload 0
      // 25e7: getfield StageMaker.sp I
      // 25ea: aaload
      // 25eb: getfield ContO.z I
      // 25ee: aload 0
      // 25ef: getfield StageMaker.co [LContO;
      // 25f2: iload 11
      // 25f4: aaload
      // 25f5: getfield ContO.z I
      // 25f8: aload 0
      // 25f9: getfield StageMaker.co [LContO;
      // 25fc: iload 11
      // 25fe: aaload
      // 25ff: getfield ContO.p [LPlane;
      // 2602: iload 16
      // 2604: aaload
      // 2605: getfield Plane.oz [I
      // 2608: iload 17
      // 260a: iaload
      // 260b: iadd
      // 260c: invokevirtual StageMaker.py (IIII)I
      // 260f: iload 10
      // 2611: if_icmplt 2619
      // 2614: goto 26d1
      // 2619: aload 0
      // 261a: aload 0
      // 261b: getfield StageMaker.bco [LContO;
      // 261e: aload 0
      // 261f: getfield StageMaker.sp I
      // 2622: aaload
      // 2623: getfield ContO.x I
      // 2626: aload 0
      // 2627: getfield StageMaker.co [LContO;
      // 262a: iload 11
      // 262c: aaload
      // 262d: getfield ContO.x I
      // 2630: aload 0
      // 2631: getfield StageMaker.bco [LContO;
      // 2634: aload 0
      // 2635: getfield StageMaker.sp I
      // 2638: aaload
      // 2639: getfield ContO.z I
      // 263c: aload 0
      // 263d: getfield StageMaker.co [LContO;
      // 2640: iload 11
      // 2642: aaload
      // 2643: getfield ContO.z I
      // 2646: aload 0
      // 2647: getfield StageMaker.co [LContO;
      // 264a: iload 11
      // 264c: aaload
      // 264d: getfield ContO.p [LPlane;
      // 2650: iload 16
      // 2652: aaload
      // 2653: getfield Plane.oz [I
      // 2656: iload 17
      // 2658: iaload
      // 2659: iadd
      // 265a: invokevirtual StageMaker.py (IIII)I
      // 265d: istore 10
      // 265f: aload 0
      // 2660: aload 0
      // 2661: getfield StageMaker.co [LContO;
      // 2664: iload 11
      // 2666: aaload
      // 2667: getfield ContO.p [LPlane;
      // 266a: iload 16
      // 266c: aaload
      // 266d: getfield Plane.oy [I
      // 2670: iload 17
      // 2672: iaload
      // 2673: bipush 28
      // 2675: isub
      // 2676: aload 0
      // 2677: getfield StageMaker.m LMedium;
      // 267a: getfield Medium.ground I
      // 267d: iadd
      // 267e: putfield StageMaker.flyh I
      // 2681: aload 0
      // 2682: getfield StageMaker.co [LContO;
      // 2685: iload 11
      // 2687: aaload
      // 2688: getfield ContO.x I
      // 268b: aload 0
      // 268c: getfield StageMaker.bco [LContO;
      // 268f: aload 0
      // 2690: getfield StageMaker.sp I
      // 2693: aaload
      // 2694: getfield ContO.x I
      // 2697: isub
      // 2698: istore 5
      // 269a: aload 0
      // 269b: getfield StageMaker.co [LContO;
      // 269e: iload 11
      // 26a0: aaload
      // 26a1: getfield ContO.z I
      // 26a4: aload 0
      // 26a5: getfield StageMaker.co [LContO;
      // 26a8: iload 11
      // 26aa: aaload
      // 26ab: getfield ContO.p [LPlane;
      // 26ae: iload 16
      // 26b0: aaload
      // 26b1: getfield Plane.oz [I
      // 26b4: iload 17
      // 26b6: iaload
      // 26b7: iadd
      // 26b8: aload 0
      // 26b9: getfield StageMaker.bco [LContO;
      // 26bc: aload 0
      // 26bd: getfield StageMaker.sp I
      // 26c0: aaload
      // 26c1: getfield ContO.z I
      // 26c4: isub
      // 26c5: istore 6
      // 26c7: aload 0
      // 26c8: bipush 1
      // 26c9: putfield StageMaker.onfly Z
      // 26cc: goto 26d1
      // 26d1: iinc 17 1
      // 26d4: goto 25b1
      // 26d9: iinc 16 1
      // 26dc: goto 2594
      // 26e1: goto 26e6
      // 26e6: aload 0
      // 26e7: getfield StageMaker.co [LContO;
      // 26ea: iload 11
      // 26ec: aaload
      // 26ed: getfield ContO.roofat I
      // 26f0: invokestatic java/lang/Math.abs (I)I
      // 26f3: bipush 90
      // 26f5: if_icmpeq 26fd
      // 26f8: goto 28aa
      // 26fd: aload 0
      // 26fe: getfield StageMaker.rot I
      // 2701: bipush 90
      // 2703: if_icmpeq 270b
      // 2706: goto 28aa
      // 270b: aload 0
      // 270c: getfield StageMaker.bco [LContO;
      // 270f: aload 0
      // 2710: getfield StageMaker.sp I
      // 2713: aaload
      // 2714: getfield ContO.z I
      // 2717: aload 0
      // 2718: getfield StageMaker.co [LContO;
      // 271b: iload 11
      // 271d: aaload
      // 271e: getfield ContO.z I
      // 2721: isub
      // 2722: invokestatic java/lang/Math.abs (I)I
      // 2725: sipush 500
      // 2728: if_icmplt 2730
      // 272b: goto 28aa
      // 2730: aload 0
      // 2731: getfield StageMaker.bco [LContO;
      // 2734: aload 0
      // 2735: getfield StageMaker.sp I
      // 2738: aaload
      // 2739: getfield ContO.x I
      // 273c: aload 0
      // 273d: getfield StageMaker.co [LContO;
      // 2740: iload 11
      // 2742: aaload
      // 2743: getfield ContO.x I
      // 2746: isub
      // 2747: invokestatic java/lang/Math.abs (I)I
      // 274a: sipush 3000
      // 274d: if_icmplt 2755
      // 2750: goto 28aa
      // 2755: bipush 0
      // 2756: istore 16
      // 2758: iload 16
      // 275a: aload 15
      // 275c: aload 0
      // 275d: getfield StageMaker.co [LContO;
      // 2760: iload 11
      // 2762: aaload
      // 2763: getfield ContO.colok I
      // 2766: bipush 46
      // 2768: isub
      // 2769: iaload
      // 276a: if_icmplt 2772
      // 276d: goto 28a5
      // 2772: bipush 0
      // 2773: istore 17
      // 2775: iload 17
      // 2777: aload 0
      // 2778: getfield StageMaker.co [LContO;
      // 277b: iload 11
      // 277d: aaload
      // 277e: getfield ContO.p [LPlane;
      // 2781: iload 16
      // 2783: aaload
      // 2784: getfield Plane.n I
      // 2787: if_icmplt 278f
      // 278a: goto 289d
      // 278f: aload 0
      // 2790: aload 0
      // 2791: getfield StageMaker.bco [LContO;
      // 2794: aload 0
      // 2795: getfield StageMaker.sp I
      // 2798: aaload
      // 2799: getfield ContO.z I
      // 279c: aload 0
      // 279d: getfield StageMaker.co [LContO;
      // 27a0: iload 11
      // 27a2: aaload
      // 27a3: getfield ContO.z I
      // 27a6: aload 0
      // 27a7: getfield StageMaker.bco [LContO;
      // 27aa: aload 0
      // 27ab: getfield StageMaker.sp I
      // 27ae: aaload
      // 27af: getfield ContO.x I
      // 27b2: aload 0
      // 27b3: getfield StageMaker.co [LContO;
      // 27b6: iload 11
      // 27b8: aaload
      // 27b9: getfield ContO.x I
      // 27bc: aload 0
      // 27bd: getfield StageMaker.co [LContO;
      // 27c0: iload 11
      // 27c2: aaload
      // 27c3: getfield ContO.p [LPlane;
      // 27c6: iload 16
      // 27c8: aaload
      // 27c9: getfield Plane.ox [I
      // 27cc: iload 17
      // 27ce: iaload
      // 27cf: iadd
      // 27d0: invokevirtual StageMaker.py (IIII)I
      // 27d3: iload 10
      // 27d5: if_icmplt 27dd
      // 27d8: goto 2895
      // 27dd: aload 0
      // 27de: aload 0
      // 27df: getfield StageMaker.bco [LContO;
      // 27e2: aload 0
      // 27e3: getfield StageMaker.sp I
      // 27e6: aaload
      // 27e7: getfield ContO.z I
      // 27ea: aload 0
      // 27eb: getfield StageMaker.co [LContO;
      // 27ee: iload 11
      // 27f0: aaload
      // 27f1: getfield ContO.z I
      // 27f4: aload 0
      // 27f5: getfield StageMaker.bco [LContO;
      // 27f8: aload 0
      // 27f9: getfield StageMaker.sp I
      // 27fc: aaload
      // 27fd: getfield ContO.x I
      // 2800: aload 0
      // 2801: getfield StageMaker.co [LContO;
      // 2804: iload 11
      // 2806: aaload
      // 2807: getfield ContO.x I
      // 280a: aload 0
      // 280b: getfield StageMaker.co [LContO;
      // 280e: iload 11
      // 2810: aaload
      // 2811: getfield ContO.p [LPlane;
      // 2814: iload 16
      // 2816: aaload
      // 2817: getfield Plane.ox [I
      // 281a: iload 17
      // 281c: iaload
      // 281d: iadd
      // 281e: invokevirtual StageMaker.py (IIII)I
      // 2821: istore 10
      // 2823: aload 0
      // 2824: aload 0
      // 2825: getfield StageMaker.co [LContO;
      // 2828: iload 11
      // 282a: aaload
      // 282b: getfield ContO.p [LPlane;
      // 282e: iload 16
      // 2830: aaload
      // 2831: getfield Plane.oy [I
      // 2834: iload 17
      // 2836: iaload
      // 2837: bipush 28
      // 2839: isub
      // 283a: aload 0
      // 283b: getfield StageMaker.m LMedium;
      // 283e: getfield Medium.ground I
      // 2841: iadd
      // 2842: putfield StageMaker.flyh I
      // 2845: aload 0
      // 2846: getfield StageMaker.co [LContO;
      // 2849: iload 11
      // 284b: aaload
      // 284c: getfield ContO.z I
      // 284f: aload 0
      // 2850: getfield StageMaker.bco [LContO;
      // 2853: aload 0
      // 2854: getfield StageMaker.sp I
      // 2857: aaload
      // 2858: getfield ContO.z I
      // 285b: isub
      // 285c: istore 6
      // 285e: aload 0
      // 285f: getfield StageMaker.co [LContO;
      // 2862: iload 11
      // 2864: aaload
      // 2865: getfield ContO.x I
      // 2868: aload 0
      // 2869: getfield StageMaker.co [LContO;
      // 286c: iload 11
      // 286e: aaload
      // 286f: getfield ContO.p [LPlane;
      // 2872: iload 16
      // 2874: aaload
      // 2875: getfield Plane.ox [I
      // 2878: iload 17
      // 287a: iaload
      // 287b: iadd
      // 287c: aload 0
      // 287d: getfield StageMaker.bco [LContO;
      // 2880: aload 0
      // 2881: getfield StageMaker.sp I
      // 2884: aaload
      // 2885: getfield ContO.x I
      // 2888: isub
      // 2889: istore 5
      // 288b: aload 0
      // 288c: bipush 1
      // 288d: putfield StageMaker.onfly Z
      // 2890: goto 2895
      // 2895: iinc 17 1
      // 2898: goto 2775
      // 289d: iinc 16 1
      // 28a0: goto 2758
      // 28a5: goto 28aa
      // 28aa: goto 28af
      // 28af: iinc 11 1
      // 28b2: goto 1e15
      // 28b7: aload 0
      // 28b8: getfield StageMaker.bco [LContO;
      // 28bb: aload 0
      // 28bc: getfield StageMaker.sp I
      // 28bf: aaload
      // 28c0: dup
      // 28c1: getfield ContO.x I
      // 28c4: iload 5
      // 28c6: iadd
      // 28c7: putfield ContO.x I
      // 28ca: aload 0
      // 28cb: getfield StageMaker.bco [LContO;
      // 28ce: aload 0
      // 28cf: getfield StageMaker.sp I
      // 28d2: aaload
      // 28d3: dup
      // 28d4: getfield ContO.z I
      // 28d7: iload 6
      // 28d9: iadd
      // 28da: putfield ContO.z I
      // 28dd: aload 0
      // 28de: getfield StageMaker.bco [LContO;
      // 28e1: aload 0
      // 28e2: getfield StageMaker.sp I
      // 28e5: aaload
      // 28e6: getfield ContO.xy I
      // 28e9: istore 11
      // 28eb: aload 0
      // 28ec: getfield StageMaker.bco [LContO;
      // 28ef: aload 0
      // 28f0: getfield StageMaker.sp I
      // 28f3: aaload
      // 28f4: getfield ContO.zy I
      // 28f7: istore 12
      // 28f9: aload 0
      // 28fa: getfield StageMaker.sp I
      // 28fd: bipush 31
      // 28ff: if_icmpeq 2907
      // 2902: goto 2943
      // 2907: aload 0
      // 2908: getfield StageMaker.bco [LContO;
      // 290b: aload 0
      // 290c: getfield StageMaker.sp I
      // 290f: aaload
      // 2910: aload 0
      // 2911: getfield StageMaker.hf I
      // 2914: ineg
      // 2915: putfield ContO.y I
      // 2918: aload 0
      // 2919: getfield StageMaker.bco [LContO;
      // 291c: aload 0
      // 291d: getfield StageMaker.sp I
      // 2920: aaload
      // 2921: getfield ContO.y I
      // 2924: sipush -500
      // 2927: if_icmpgt 292f
      // 292a: goto 2950
      // 292f: aload 0
      // 2930: getfield StageMaker.bco [LContO;
      // 2933: aload 0
      // 2934: getfield StageMaker.sp I
      // 2937: aaload
      // 2938: sipush -500
      // 293b: putfield ContO.y I
      // 293e: goto 2950
      // 2943: aload 0
      // 2944: getfield StageMaker.bco [LContO;
      // 2947: aload 0
      // 2948: getfield StageMaker.sp I
      // 294b: aaload
      // 294c: bipush 0
      // 294d: putfield ContO.xy I
      // 2950: aload 0
      // 2951: getfield StageMaker.sp I
      // 2954: bipush 54
      // 2956: if_icmpeq 295e
      // 2959: goto 2973
      // 295e: aload 0
      // 295f: getfield StageMaker.bco [LContO;
      // 2962: aload 0
      // 2963: getfield StageMaker.sp I
      // 2966: aaload
      // 2967: aload 0
      // 2968: getfield StageMaker.flyh I
      // 296b: putfield ContO.y I
      // 296e: goto 2973
      // 2973: aload 0
      // 2974: getfield StageMaker.bco [LContO;
      // 2977: aload 0
      // 2978: getfield StageMaker.sp I
      // 297b: aaload
      // 297c: bipush 0
      // 297d: putfield ContO.zy I
      // 2980: aload 0
      // 2981: getfield StageMaker.cntout I
      // 2984: ifeq 298c
      // 2987: goto 2f19
      // 298c: aload 0
      // 298d: getfield StageMaker.mouseon I
      // 2990: bipush -1
      // 2991: if_icmpeq 2999
      // 2994: goto 2f7a
      // 2999: aload 0
      // 299a: getfield StageMaker.bco [LContO;
      // 299d: aload 0
      // 299e: getfield StageMaker.sp I
      // 29a1: aaload
      // 29a2: aload 0
      // 29a3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 29a6: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 29a9: aload 0
      // 29aa: getfield StageMaker.setcur Z
      // 29ad: ifeq 29b5
      // 29b0: goto 29cc
      // 29b5: aload 0
      // 29b6: new java/awt/Cursor
      // 29b9: dup
      // 29ba: bipush 13
      // 29bc: invokespecial java/awt/Cursor.<init> (I)V
      // 29bf: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 29c2: aload 0
      // 29c3: bipush 1
      // 29c4: putfield StageMaker.setcur Z
      // 29c7: goto 29cc
      // 29cc: aload 0
      // 29cd: getfield StageMaker.mouses I
      // 29d0: bipush -1
      // 29d1: if_icmpeq 29d9
      // 29d4: goto 2f7a
      // 29d9: aload 0
      // 29da: getfield StageMaker.nundo I
      // 29dd: sipush 5000
      // 29e0: if_icmplt 29e8
      // 29e3: goto 2a04
      // 29e8: aload 0
      // 29e9: getfield StageMaker.undos [Ljava/lang/String;
      // 29ec: aload 0
      // 29ed: getfield StageMaker.nundo I
      // 29f0: aload 0
      // 29f1: getfield StageMaker.bstage Ljava/lang/String;
      // 29f4: aastore
      // 29f5: aload 0
      // 29f6: dup
      // 29f7: getfield StageMaker.nundo I
      // 29fa: bipush 1
      // 29fb: iadd
      // 29fc: putfield StageMaker.nundo I
      // 29ff: goto 2a04
      // 2a04: aload 0
      // 2a05: getfield StageMaker.bco [LContO;
      // 2a08: aload 0
      // 2a09: getfield StageMaker.sp I
      // 2a0c: aaload
      // 2a0d: getfield ContO.xz I
      // 2a10: sipush 270
      // 2a13: if_icmpeq 2a1b
      // 2a16: goto 2a2e
      // 2a1b: aload 0
      // 2a1c: getfield StageMaker.bco [LContO;
      // 2a1f: aload 0
      // 2a20: getfield StageMaker.sp I
      // 2a23: aaload
      // 2a24: bipush -90
      // 2a26: putfield ContO.xz I
      // 2a29: goto 2a2e
      // 2a2e: aload 0
      // 2a2f: getfield StageMaker.bco [LContO;
      // 2a32: aload 0
      // 2a33: getfield StageMaker.sp I
      // 2a36: aaload
      // 2a37: getfield ContO.xz I
      // 2a3a: sipush 360
      // 2a3d: if_icmpeq 2a45
      // 2a40: goto 2a57
      // 2a45: aload 0
      // 2a46: getfield StageMaker.bco [LContO;
      // 2a49: aload 0
      // 2a4a: getfield StageMaker.sp I
      // 2a4d: aaload
      // 2a4e: bipush 0
      // 2a4f: putfield ContO.xz I
      // 2a52: goto 2a57
      // 2a57: aload 0
      // 2a58: bipush 0
      // 2a59: putfield StageMaker.errd I
      // 2a5c: bipush 0
      // 2a5d: istore 13
      // 2a5f: aload 0
      // 2a60: getfield StageMaker.xnob I
      // 2a63: sipush 601
      // 2a66: if_icmplt 2a6e
      // 2a69: goto 2d60
      // 2a6e: aload 0
      // 2a6f: getfield StageMaker.sp I
      // 2a72: bipush 31
      // 2a74: if_icmpne 2a7c
      // 2a77: goto 2b2c
      // 2a7c: aload 0
      // 2a7d: getfield StageMaker.sp I
      // 2a80: bipush 54
      // 2a82: if_icmpne 2a8a
      // 2a85: goto 2b2c
      // 2a8a: aload 0
      // 2a8b: getfield StageMaker.sp I
      // 2a8e: bipush 66
      // 2a90: if_icmpne 2a98
      // 2a93: goto 2b2c
      // 2a98: aload 0
      // 2a99: getfield StageMaker.co [LContO;
      // 2a9c: aload 0
      // 2a9d: getfield StageMaker.nob I
      // 2aa0: new ContO
      // 2aa3: dup
      // 2aa4: aload 0
      // 2aa5: getfield StageMaker.bco [LContO;
      // 2aa8: aload 0
      // 2aa9: getfield StageMaker.sp I
      // 2aac: aaload
      // 2aad: aload 0
      // 2aae: getfield StageMaker.bco [LContO;
      // 2ab1: aload 0
      // 2ab2: getfield StageMaker.sp I
      // 2ab5: aaload
      // 2ab6: getfield ContO.x I
      // 2ab9: aload 0
      // 2aba: getfield StageMaker.m LMedium;
      // 2abd: getfield Medium.ground I
      // 2ac0: aload 0
      // 2ac1: getfield StageMaker.bco [LContO;
      // 2ac4: aload 0
      // 2ac5: getfield StageMaker.sp I
      // 2ac8: aaload
      // 2ac9: getfield ContO.grat I
      // 2acc: isub
      // 2acd: aload 0
      // 2ace: getfield StageMaker.bco [LContO;
      // 2ad1: aload 0
      // 2ad2: getfield StageMaker.sp I
      // 2ad5: aaload
      // 2ad6: getfield ContO.z I
      // 2ad9: aload 0
      // 2ada: getfield StageMaker.bco [LContO;
      // 2add: aload 0
      // 2ade: getfield StageMaker.sp I
      // 2ae1: aaload
      // 2ae2: getfield ContO.xz I
      // 2ae5: invokespecial ContO.<init> (LContO;IIII)V
      // 2ae8: aastore
      // 2ae9: aload 0
      // 2aea: getfield StageMaker.co [LContO;
      // 2aed: aload 0
      // 2aee: getfield StageMaker.nob I
      // 2af1: aaload
      // 2af2: aload 0
      // 2af3: getfield StageMaker.bco [LContO;
      // 2af6: aload 0
      // 2af7: getfield StageMaker.sp I
      // 2afa: aaload
      // 2afb: getfield ContO.xz I
      // 2afe: putfield ContO.roofat I
      // 2b01: aload 0
      // 2b02: getfield StageMaker.co [LContO;
      // 2b05: aload 0
      // 2b06: getfield StageMaker.nob I
      // 2b09: aaload
      // 2b0a: aload 0
      // 2b0b: getfield StageMaker.sp I
      // 2b0e: putfield ContO.colok I
      // 2b11: aload 0
      // 2b12: dup
      // 2b13: getfield StageMaker.nob I
      // 2b16: bipush 1
      // 2b17: iadd
      // 2b18: putfield StageMaker.nob I
      // 2b1b: goto 2b2c
      // 2b20: astore 14
      // 2b22: aload 0
      // 2b23: bipush 1
      // 2b24: putfield StageMaker.errd I
      // 2b27: goto 2b2c
      // 2b2c: aload 0
      // 2b2d: getfield StageMaker.sp I
      // 2b30: bipush 31
      // 2b32: if_icmpeq 2b3a
      // 2b35: goto 2c01
      // 2b3a: aload 0
      // 2b3b: getfield StageMaker.cp LCheckPoints;
      // 2b3e: getfield CheckPoints.fn I
      // 2b41: bipush 5
      // 2b42: if_icmplt 2b4a
      // 2b45: goto 2bf7
      // 2b4a: aload 0
      // 2b4b: getfield StageMaker.co [LContO;
      // 2b4e: aload 0
      // 2b4f: getfield StageMaker.nob I
      // 2b52: new ContO
      // 2b55: dup
      // 2b56: aload 0
      // 2b57: getfield StageMaker.bco [LContO;
      // 2b5a: aload 0
      // 2b5b: getfield StageMaker.sp I
      // 2b5e: aaload
      // 2b5f: aload 0
      // 2b60: getfield StageMaker.bco [LContO;
      // 2b63: aload 0
      // 2b64: getfield StageMaker.sp I
      // 2b67: aaload
      // 2b68: getfield ContO.x I
      // 2b6b: aload 0
      // 2b6c: getfield StageMaker.bco [LContO;
      // 2b6f: aload 0
      // 2b70: getfield StageMaker.sp I
      // 2b73: aaload
      // 2b74: getfield ContO.y I
      // 2b77: aload 0
      // 2b78: getfield StageMaker.bco [LContO;
      // 2b7b: aload 0
      // 2b7c: getfield StageMaker.sp I
      // 2b7f: aaload
      // 2b80: getfield ContO.z I
      // 2b83: aload 0
      // 2b84: getfield StageMaker.bco [LContO;
      // 2b87: aload 0
      // 2b88: getfield StageMaker.sp I
      // 2b8b: aaload
      // 2b8c: getfield ContO.xz I
      // 2b8f: invokespecial ContO.<init> (LContO;IIII)V
      // 2b92: aastore
      // 2b93: aload 0
      // 2b94: getfield StageMaker.co [LContO;
      // 2b97: aload 0
      // 2b98: getfield StageMaker.nob I
      // 2b9b: aaload
      // 2b9c: aload 0
      // 2b9d: getfield StageMaker.bco [LContO;
      // 2ba0: aload 0
      // 2ba1: getfield StageMaker.sp I
      // 2ba4: aaload
      // 2ba5: getfield ContO.xz I
      // 2ba8: putfield ContO.roofat I
      // 2bab: aload 0
      // 2bac: getfield StageMaker.co [LContO;
      // 2baf: aload 0
      // 2bb0: getfield StageMaker.nob I
      // 2bb3: aaload
      // 2bb4: aload 0
      // 2bb5: getfield StageMaker.sp I
      // 2bb8: putfield ContO.colok I
      // 2bbb: aload 0
      // 2bbc: dup
      // 2bbd: getfield StageMaker.nob I
      // 2bc0: bipush 1
      // 2bc1: iadd
      // 2bc2: putfield StageMaker.nob I
      // 2bc5: aload 0
      // 2bc6: getfield StageMaker.fixh Ljava/awt/TextField;
      // 2bc9: new java/lang/StringBuilder
      // 2bcc: dup
      // 2bcd: invokespecial java/lang/StringBuilder.<init> ()V
      // 2bd0: ldc ""
      // 2bd2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2bd5: aload 0
      // 2bd6: getfield StageMaker.bco [LContO;
      // 2bd9: aload 0
      // 2bda: getfield StageMaker.sp I
      // 2bdd: aaload
      // 2bde: getfield ContO.y I
      // 2be1: invokestatic java/lang/Math.abs (I)I
      // 2be4: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2be7: ldc ""
      // 2be9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2bec: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2bef: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 2bf2: goto 2c01
      // 2bf7: aload 0
      // 2bf8: bipush 5
      // 2bf9: putfield StageMaker.errd I
      // 2bfc: goto 2c01
      // 2c01: aload 0
      // 2c02: getfield StageMaker.sp I
      // 2c05: bipush 54
      // 2c07: if_icmpeq 2c0f
      // 2c0a: goto 2c9b
      // 2c0f: aload 0
      // 2c10: getfield StageMaker.co [LContO;
      // 2c13: aload 0
      // 2c14: getfield StageMaker.nob I
      // 2c17: new ContO
      // 2c1a: dup
      // 2c1b: aload 0
      // 2c1c: getfield StageMaker.bco [LContO;
      // 2c1f: aload 0
      // 2c20: getfield StageMaker.sp I
      // 2c23: aaload
      // 2c24: aload 0
      // 2c25: getfield StageMaker.bco [LContO;
      // 2c28: aload 0
      // 2c29: getfield StageMaker.sp I
      // 2c2c: aaload
      // 2c2d: getfield ContO.x I
      // 2c30: aload 0
      // 2c31: getfield StageMaker.bco [LContO;
      // 2c34: aload 0
      // 2c35: getfield StageMaker.sp I
      // 2c38: aaload
      // 2c39: getfield ContO.y I
      // 2c3c: aload 0
      // 2c3d: getfield StageMaker.bco [LContO;
      // 2c40: aload 0
      // 2c41: getfield StageMaker.sp I
      // 2c44: aaload
      // 2c45: getfield ContO.z I
      // 2c48: aload 0
      // 2c49: getfield StageMaker.bco [LContO;
      // 2c4c: aload 0
      // 2c4d: getfield StageMaker.sp I
      // 2c50: aaload
      // 2c51: getfield ContO.xz I
      // 2c54: invokespecial ContO.<init> (LContO;IIII)V
      // 2c57: aastore
      // 2c58: aload 0
      // 2c59: getfield StageMaker.co [LContO;
      // 2c5c: aload 0
      // 2c5d: getfield StageMaker.nob I
      // 2c60: aaload
      // 2c61: aload 0
      // 2c62: getfield StageMaker.bco [LContO;
      // 2c65: aload 0
      // 2c66: getfield StageMaker.sp I
      // 2c69: aaload
      // 2c6a: getfield ContO.xz I
      // 2c6d: putfield ContO.roofat I
      // 2c70: aload 0
      // 2c71: getfield StageMaker.co [LContO;
      // 2c74: aload 0
      // 2c75: getfield StageMaker.nob I
      // 2c78: aaload
      // 2c79: aload 0
      // 2c7a: getfield StageMaker.sp I
      // 2c7d: putfield ContO.colok I
      // 2c80: aload 0
      // 2c81: dup
      // 2c82: getfield StageMaker.nob I
      // 2c85: bipush 1
      // 2c86: iadd
      // 2c87: putfield StageMaker.nob I
      // 2c8a: goto 2c9b
      // 2c8f: astore 14
      // 2c91: aload 0
      // 2c92: bipush 1
      // 2c93: putfield StageMaker.errd I
      // 2c96: goto 2c9b
      // 2c9b: aload 0
      // 2c9c: getfield StageMaker.sp I
      // 2c9f: bipush 66
      // 2ca1: if_icmpeq 2ca9
      // 2ca4: goto 2d65
      // 2ca9: aload 0
      // 2caa: getfield StageMaker.co [LContO;
      // 2cad: aload 0
      // 2cae: getfield StageMaker.nob I
      // 2cb1: new ContO
      // 2cb4: dup
      // 2cb5: aload 0
      // 2cb6: getfield StageMaker.bco [LContO;
      // 2cb9: bipush 66
      // 2cbb: aaload
      // 2cbc: getfield ContO.srz I
      // 2cbf: aload 0
      // 2cc0: getfield StageMaker.bco [LContO;
      // 2cc3: bipush 66
      // 2cc5: aaload
      // 2cc6: getfield ContO.srx I
      // 2cc9: aload 0
      // 2cca: getfield StageMaker.bco [LContO;
      // 2ccd: bipush 66
      // 2ccf: aaload
      // 2cd0: getfield ContO.sry I
      // 2cd3: aload 0
      // 2cd4: getfield StageMaker.m LMedium;
      // 2cd7: aload 0
      // 2cd8: getfield StageMaker.t LTrackers;
      // 2cdb: aload 0
      // 2cdc: getfield StageMaker.bco [LContO;
      // 2cdf: bipush 66
      // 2ce1: aaload
      // 2ce2: getfield ContO.x I
      // 2ce5: aload 0
      // 2ce6: getfield StageMaker.bco [LContO;
      // 2ce9: bipush 66
      // 2ceb: aaload
      // 2cec: getfield ContO.z I
      // 2cef: aload 0
      // 2cf0: getfield StageMaker.bco [LContO;
      // 2cf3: aload 0
      // 2cf4: getfield StageMaker.sp I
      // 2cf7: aaload
      // 2cf8: getfield ContO.y I
      // 2cfb: invokespecial ContO.<init> (IIILMedium;LTrackers;III)V
      // 2cfe: aastore
      // 2cff: aload 0
      // 2d00: getfield StageMaker.co [LContO;
      // 2d03: aload 0
      // 2d04: getfield StageMaker.nob I
      // 2d07: aaload
      // 2d08: aload 0
      // 2d09: getfield StageMaker.bco [LContO;
      // 2d0c: bipush 66
      // 2d0e: aaload
      // 2d0f: getfield ContO.srz I
      // 2d12: putfield ContO.srz I
      // 2d15: aload 0
      // 2d16: getfield StageMaker.co [LContO;
      // 2d19: aload 0
      // 2d1a: getfield StageMaker.nob I
      // 2d1d: aaload
      // 2d1e: aload 0
      // 2d1f: getfield StageMaker.bco [LContO;
      // 2d22: bipush 66
      // 2d24: aaload
      // 2d25: getfield ContO.srx I
      // 2d28: putfield ContO.srx I
      // 2d2b: aload 0
      // 2d2c: getfield StageMaker.co [LContO;
      // 2d2f: aload 0
      // 2d30: getfield StageMaker.nob I
      // 2d33: aaload
      // 2d34: aload 0
      // 2d35: getfield StageMaker.bco [LContO;
      // 2d38: bipush 66
      // 2d3a: aaload
      // 2d3b: getfield ContO.sry I
      // 2d3e: putfield ContO.sry I
      // 2d41: aload 0
      // 2d42: getfield StageMaker.co [LContO;
      // 2d45: aload 0
      // 2d46: getfield StageMaker.nob I
      // 2d49: aaload
      // 2d4a: aload 0
      // 2d4b: getfield StageMaker.sp I
      // 2d4e: putfield ContO.colok I
      // 2d51: aload 0
      // 2d52: dup
      // 2d53: getfield StageMaker.nob I
      // 2d56: bipush 1
      // 2d57: iadd
      // 2d58: putfield StageMaker.nob I
      // 2d5b: goto 2d65
      // 2d60: aload 0
      // 2d61: bipush 4
      // 2d62: putfield StageMaker.errd I
      // 2d65: aload 0
      // 2d66: getfield StageMaker.errd I
      // 2d69: ifeq 2d71
      // 2d6c: goto 2e8b
      // 2d71: aload 0
      // 2d72: invokevirtual StageMaker.sortstage ()V
      // 2d75: aload 0
      // 2d76: bipush 0
      // 2d77: invokevirtual StageMaker.readstage (I)V
      // 2d7a: bipush 1
      // 2d7b: istore 13
      // 2d7d: aload 0
      // 2d7e: getfield StageMaker.sp I
      // 2d81: bipush 66
      // 2d83: if_icmpeq 2d8b
      // 2d86: goto 2d95
      // 2d8b: aload 0
      // 2d8c: bipush 0
      // 2d8d: putfield StageMaker.pgen Z
      // 2d90: goto 2d95
      // 2d95: aload 0
      // 2d96: getfield StageMaker.sp I
      // 2d99: bipush 52
      // 2d9b: if_icmpne 2da3
      // 2d9e: goto 2dcd
      // 2da3: aload 0
      // 2da4: getfield StageMaker.sp I
      // 2da7: bipush 53
      // 2da9: if_icmpne 2db1
      // 2dac: goto 2dcd
      // 2db1: aload 0
      // 2db2: getfield StageMaker.sp I
      // 2db5: bipush 55
      // 2db7: if_icmpge 2dbf
      // 2dba: goto 2e8b
      // 2dbf: aload 0
      // 2dc0: getfield StageMaker.sp I
      // 2dc3: bipush 65
      // 2dc5: if_icmple 2dcd
      // 2dc8: goto 2e8b
      // 2dcd: aload 0
      // 2dce: bipush 3
      // 2dcf: putfield StageMaker.seq I
      // 2dd2: aload 0
      // 2dd3: getfield StageMaker.bco [LContO;
      // 2dd6: aload 0
      // 2dd7: getfield StageMaker.sp I
      // 2dda: aaload
      // 2ddb: bipush 0
      // 2ddc: putfield ContO.xy I
      // 2ddf: aload 0
      // 2de0: getfield StageMaker.bco [LContO;
      // 2de3: aload 0
      // 2de4: getfield StageMaker.sp I
      // 2de7: aaload
      // 2de8: bipush 0
      // 2de9: putfield ContO.zy I
      // 2dec: bipush 0
      // 2ded: istore 14
      // 2def: aload 0
      // 2df0: getfield StageMaker.rot I
      // 2df3: ifeq 2dfb
      // 2df6: goto 2e13
      // 2dfb: iload 14
      // 2dfd: ifeq 2e05
      // 2e00: goto 2e13
      // 2e05: aload 0
      // 2e06: bipush 90
      // 2e08: putfield StageMaker.rot I
      // 2e0b: bipush 1
      // 2e0c: istore 14
      // 2e0e: goto 2e13
      // 2e13: aload 0
      // 2e14: getfield StageMaker.rot I
      // 2e17: bipush 90
      // 2e19: if_icmpeq 2e21
      // 2e1c: goto 2e3a
      // 2e21: iload 14
      // 2e23: ifeq 2e2b
      // 2e26: goto 2e3a
      // 2e2b: aload 0
      // 2e2c: sipush 180
      // 2e2f: putfield StageMaker.rot I
      // 2e32: bipush 1
      // 2e33: istore 14
      // 2e35: goto 2e3a
      // 2e3a: aload 0
      // 2e3b: getfield StageMaker.rot I
      // 2e3e: sipush 180
      // 2e41: if_icmpeq 2e49
      // 2e44: goto 2e61
      // 2e49: iload 14
      // 2e4b: ifeq 2e53
      // 2e4e: goto 2e61
      // 2e53: aload 0
      // 2e54: bipush -90
      // 2e56: putfield StageMaker.rot I
      // 2e59: bipush 1
      // 2e5a: istore 14
      // 2e5c: goto 2e61
      // 2e61: aload 0
      // 2e62: getfield StageMaker.rot I
      // 2e65: bipush -90
      // 2e67: if_icmpeq 2e6f
      // 2e6a: goto 2e86
      // 2e6f: iload 14
      // 2e71: ifeq 2e79
      // 2e74: goto 2e86
      // 2e79: aload 0
      // 2e7a: bipush 0
      // 2e7b: putfield StageMaker.rot I
      // 2e7e: bipush 1
      // 2e7f: istore 14
      // 2e81: goto 2e86
      // 2e86: goto 2e8b
      // 2e8b: aload 0
      // 2e8c: getfield StageMaker.errd I
      // 2e8f: ifne 2e97
      // 2e92: goto 2eee
      // 2e97: aconst_null
      // 2e98: new java/lang/StringBuilder
      // 2e9b: dup
      // 2e9c: invokespecial java/lang/StringBuilder.<init> ()V
      // 2e9f: ldc_w "Error!  Unable to place part!\nReason:\n"
      // 2ea2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2ea5: aload 0
      // 2ea6: getfield StageMaker.errlo [Ljava/lang/String;
      // 2ea9: aload 0
      // 2eaa: getfield StageMaker.errd I
      // 2ead: bipush 1
      // 2eae: isub
      // 2eaf: aaload
      // 2eb0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2eb3: ldc_w "\n\n"
      // 2eb6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2eb9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2ebc: ldc_w "Stage Maker"
      // 2ebf: bipush 0
      // 2ec0: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 2ec3: iload 13
      // 2ec5: ifne 2ecd
      // 2ec8: goto 2eee
      // 2ecd: aload 0
      // 2ece: dup
      // 2ecf: getfield StageMaker.nundo I
      // 2ed2: bipush 1
      // 2ed3: isub
      // 2ed4: putfield StageMaker.nundo I
      // 2ed7: aload 0
      // 2ed8: aload 0
      // 2ed9: getfield StageMaker.undos [Ljava/lang/String;
      // 2edc: aload 0
      // 2edd: getfield StageMaker.nundo I
      // 2ee0: aaload
      // 2ee1: putfield StageMaker.bstage Ljava/lang/String;
      // 2ee4: aload 0
      // 2ee5: bipush 0
      // 2ee6: invokevirtual StageMaker.readstage (I)V
      // 2ee9: goto 2eee
      // 2eee: aload 0
      // 2eef: aload 0
      // 2ef0: getfield StageMaker.bco [LContO;
      // 2ef3: aload 0
      // 2ef4: getfield StageMaker.sp I
      // 2ef7: aaload
      // 2ef8: getfield ContO.x I
      // 2efb: putfield StageMaker.lxm I
      // 2efe: aload 0
      // 2eff: aload 0
      // 2f00: getfield StageMaker.bco [LContO;
      // 2f03: aload 0
      // 2f04: getfield StageMaker.sp I
      // 2f07: aaload
      // 2f08: getfield ContO.z I
      // 2f0b: putfield StageMaker.lym I
      // 2f0e: aload 0
      // 2f0f: bipush 10
      // 2f11: putfield StageMaker.cntout I
      // 2f14: goto 2f7a
      // 2f19: aload 0
      // 2f1a: getfield StageMaker.lxm I
      // 2f1d: aload 0
      // 2f1e: getfield StageMaker.bco [LContO;
      // 2f21: aload 0
      // 2f22: getfield StageMaker.sp I
      // 2f25: aaload
      // 2f26: getfield ContO.x I
      // 2f29: if_icmpne 2f31
      // 2f2c: goto 2f58
      // 2f31: aload 0
      // 2f32: getfield StageMaker.lxm I
      // 2f35: aload 0
      // 2f36: getfield StageMaker.bco [LContO;
      // 2f39: aload 0
      // 2f3a: getfield StageMaker.sp I
      // 2f3d: aaload
      // 2f3e: getfield ContO.z I
      // 2f41: if_icmpne 2f49
      // 2f44: goto 2f58
      // 2f49: aload 0
      // 2f4a: dup
      // 2f4b: getfield StageMaker.cntout I
      // 2f4e: bipush 1
      // 2f4f: isub
      // 2f50: putfield StageMaker.cntout I
      // 2f53: goto 2f58
      // 2f58: aload 0
      // 2f59: getfield StageMaker.setcur Z
      // 2f5c: ifne 2f64
      // 2f5f: goto 2f7a
      // 2f64: aload 0
      // 2f65: new java/awt/Cursor
      // 2f68: dup
      // 2f69: bipush 0
      // 2f6a: invokespecial java/awt/Cursor.<init> (I)V
      // 2f6d: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 2f70: aload 0
      // 2f71: bipush 0
      // 2f72: putfield StageMaker.setcur Z
      // 2f75: goto 2f7a
      // 2f7a: aload 0
      // 2f7b: getfield StageMaker.bco [LContO;
      // 2f7e: aload 0
      // 2f7f: getfield StageMaker.sp I
      // 2f82: aaload
      // 2f83: iload 11
      // 2f85: putfield ContO.xy I
      // 2f88: aload 0
      // 2f89: getfield StageMaker.bco [LContO;
      // 2f8c: aload 0
      // 2f8d: getfield StageMaker.sp I
      // 2f90: aaload
      // 2f91: iload 12
      // 2f93: putfield ContO.zy I
      // 2f96: goto 3666
      // 2f9b: aload 0
      // 2f9c: getfield StageMaker.epart Z
      // 2f9f: ifne 2fa7
      // 2fa2: goto 3302
      // 2fa7: aload 0
      // 2fa8: getfield StageMaker.esp I
      // 2fab: bipush -1
      // 2fac: if_icmpeq 2fb4
      // 2faf: goto 32e0
      // 2fb4: aload 0
      // 2fb5: getfield StageMaker.overcan Z
      // 2fb8: ifeq 2fc0
      // 2fbb: goto 32e0
      // 2fc0: aload 0
      // 2fc1: bipush -1
      // 2fc2: putfield StageMaker.hi I
      // 2fc5: bipush 0
      // 2fc6: istore 4
      // 2fc8: bipush 0
      // 2fc9: istore 5
      // 2fcb: iload 5
      // 2fcd: aload 0
      // 2fce: getfield StageMaker.nob I
      // 2fd1: if_icmplt 2fd9
      // 2fd4: goto 326a
      // 2fd9: aload 0
      // 2fda: getfield StageMaker.m LMedium;
      // 2fdd: getfield Medium.cx I
      // 2fe0: aload 0
      // 2fe1: getfield StageMaker.co [LContO;
      // 2fe4: iload 5
      // 2fe6: aaload
      // 2fe7: getfield ContO.x I
      // 2fea: aload 0
      // 2feb: getfield StageMaker.m LMedium;
      // 2fee: getfield Medium.x I
      // 2ff1: isub
      // 2ff2: aload 0
      // 2ff3: getfield StageMaker.m LMedium;
      // 2ff6: getfield Medium.cx I
      // 2ff9: isub
      // 2ffa: i2f
      // 2ffb: aload 0
      // 2ffc: getfield StageMaker.m LMedium;
      // 2fff: aload 0
      // 3000: getfield StageMaker.m LMedium;
      // 3003: getfield Medium.xz I
      // 3006: invokevirtual Medium.cos (I)F
      // 3009: fmul
      // 300a: aload 0
      // 300b: getfield StageMaker.co [LContO;
      // 300e: iload 5
      // 3010: aaload
      // 3011: getfield ContO.z I
      // 3014: aload 0
      // 3015: getfield StageMaker.m LMedium;
      // 3018: getfield Medium.z I
      // 301b: isub
      // 301c: aload 0
      // 301d: getfield StageMaker.m LMedium;
      // 3020: getfield Medium.cz I
      // 3023: isub
      // 3024: i2f
      // 3025: aload 0
      // 3026: getfield StageMaker.m LMedium;
      // 3029: aload 0
      // 302a: getfield StageMaker.m LMedium;
      // 302d: getfield Medium.xz I
      // 3030: invokevirtual Medium.sin (I)F
      // 3033: fmul
      // 3034: fsub
      // 3035: f2i
      // 3036: iadd
      // 3037: istore 6
      // 3039: aload 0
      // 303a: getfield StageMaker.m LMedium;
      // 303d: getfield Medium.cz I
      // 3040: aload 0
      // 3041: getfield StageMaker.co [LContO;
      // 3044: iload 5
      // 3046: aaload
      // 3047: getfield ContO.x I
      // 304a: aload 0
      // 304b: getfield StageMaker.m LMedium;
      // 304e: getfield Medium.x I
      // 3051: isub
      // 3052: aload 0
      // 3053: getfield StageMaker.m LMedium;
      // 3056: getfield Medium.cx I
      // 3059: isub
      // 305a: i2f
      // 305b: aload 0
      // 305c: getfield StageMaker.m LMedium;
      // 305f: aload 0
      // 3060: getfield StageMaker.m LMedium;
      // 3063: getfield Medium.xz I
      // 3066: invokevirtual Medium.sin (I)F
      // 3069: fmul
      // 306a: aload 0
      // 306b: getfield StageMaker.co [LContO;
      // 306e: iload 5
      // 3070: aaload
      // 3071: getfield ContO.z I
      // 3074: aload 0
      // 3075: getfield StageMaker.m LMedium;
      // 3078: getfield Medium.z I
      // 307b: isub
      // 307c: aload 0
      // 307d: getfield StageMaker.m LMedium;
      // 3080: getfield Medium.cz I
      // 3083: isub
      // 3084: i2f
      // 3085: aload 0
      // 3086: getfield StageMaker.m LMedium;
      // 3089: aload 0
      // 308a: getfield StageMaker.m LMedium;
      // 308d: getfield Medium.xz I
      // 3090: invokevirtual Medium.cos (I)F
      // 3093: fmul
      // 3094: fadd
      // 3095: f2i
      // 3096: iadd
      // 3097: istore 7
      // 3099: aload 0
      // 309a: getfield StageMaker.m LMedium;
      // 309d: getfield Medium.cy I
      // 30a0: aload 0
      // 30a1: getfield StageMaker.co [LContO;
      // 30a4: iload 5
      // 30a6: aaload
      // 30a7: getfield ContO.y I
      // 30aa: aload 0
      // 30ab: getfield StageMaker.m LMedium;
      // 30ae: getfield Medium.y I
      // 30b1: isub
      // 30b2: aload 0
      // 30b3: getfield StageMaker.m LMedium;
      // 30b6: getfield Medium.cy I
      // 30b9: isub
      // 30ba: i2f
      // 30bb: aload 0
      // 30bc: getfield StageMaker.m LMedium;
      // 30bf: aload 0
      // 30c0: getfield StageMaker.m LMedium;
      // 30c3: getfield Medium.zy I
      // 30c6: invokevirtual Medium.cos (I)F
      // 30c9: fmul
      // 30ca: iload 7
      // 30cc: aload 0
      // 30cd: getfield StageMaker.m LMedium;
      // 30d0: getfield Medium.cz I
      // 30d3: isub
      // 30d4: i2f
      // 30d5: aload 0
      // 30d6: getfield StageMaker.m LMedium;
      // 30d9: aload 0
      // 30da: getfield StageMaker.m LMedium;
      // 30dd: getfield Medium.zy I
      // 30e0: invokevirtual Medium.sin (I)F
      // 30e3: fmul
      // 30e4: fsub
      // 30e5: f2i
      // 30e6: iadd
      // 30e7: istore 8
      // 30e9: aload 0
      // 30ea: getfield StageMaker.m LMedium;
      // 30ed: getfield Medium.cz I
      // 30f0: aload 0
      // 30f1: getfield StageMaker.co [LContO;
      // 30f4: iload 5
      // 30f6: aaload
      // 30f7: getfield ContO.y I
      // 30fa: aload 0
      // 30fb: getfield StageMaker.m LMedium;
      // 30fe: getfield Medium.y I
      // 3101: isub
      // 3102: aload 0
      // 3103: getfield StageMaker.m LMedium;
      // 3106: getfield Medium.cy I
      // 3109: isub
      // 310a: i2f
      // 310b: aload 0
      // 310c: getfield StageMaker.m LMedium;
      // 310f: aload 0
      // 3110: getfield StageMaker.m LMedium;
      // 3113: getfield Medium.zy I
      // 3116: invokevirtual Medium.sin (I)F
      // 3119: fmul
      // 311a: iload 7
      // 311c: aload 0
      // 311d: getfield StageMaker.m LMedium;
      // 3120: getfield Medium.cz I
      // 3123: isub
      // 3124: i2f
      // 3125: aload 0
      // 3126: getfield StageMaker.m LMedium;
      // 3129: aload 0
      // 312a: getfield StageMaker.m LMedium;
      // 312d: getfield Medium.zy I
      // 3130: invokevirtual Medium.cos (I)F
      // 3133: fmul
      // 3134: fadd
      // 3135: f2i
      // 3136: iadd
      // 3137: istore 9
      // 3139: aload 0
      // 313a: getfield StageMaker.xm I
      // 313d: aload 0
      // 313e: iload 6
      // 3140: aload 0
      // 3141: getfield StageMaker.co [LContO;
      // 3144: iload 5
      // 3146: aaload
      // 3147: getfield ContO.maxR I
      // 314a: isub
      // 314b: iload 9
      // 314d: invokevirtual StageMaker.xs (II)I
      // 3150: if_icmpgt 3158
      // 3153: goto 3262
      // 3158: aload 0
      // 3159: getfield StageMaker.xm I
      // 315c: aload 0
      // 315d: iload 6
      // 315f: aload 0
      // 3160: getfield StageMaker.co [LContO;
      // 3163: iload 5
      // 3165: aaload
      // 3166: getfield ContO.maxR I
      // 3169: iadd
      // 316a: iload 9
      // 316c: invokevirtual StageMaker.xs (II)I
      // 316f: if_icmplt 3177
      // 3172: goto 3262
      // 3177: aload 0
      // 3178: getfield StageMaker.ym I
      // 317b: aload 0
      // 317c: iload 8
      // 317e: aload 0
      // 317f: getfield StageMaker.co [LContO;
      // 3182: iload 5
      // 3184: aaload
      // 3185: getfield ContO.maxR I
      // 3188: isub
      // 3189: iload 9
      // 318b: invokevirtual StageMaker.ys (II)I
      // 318e: if_icmpgt 3196
      // 3191: goto 3262
      // 3196: aload 0
      // 3197: getfield StageMaker.ym I
      // 319a: aload 0
      // 319b: iload 8
      // 319d: aload 0
      // 319e: getfield StageMaker.co [LContO;
      // 31a1: iload 5
      // 31a3: aaload
      // 31a4: getfield ContO.maxR I
      // 31a7: iadd
      // 31a8: iload 9
      // 31aa: invokevirtual StageMaker.ys (II)I
      // 31ad: if_icmplt 31b5
      // 31b0: goto 3262
      // 31b5: aload 0
      // 31b6: getfield StageMaker.co [LContO;
      // 31b9: iload 5
      // 31bb: aaload
      // 31bc: getfield ContO.colok I
      // 31bf: bipush 37
      // 31c1: if_icmpne 31c9
      // 31c4: goto 3262
      // 31c9: aload 0
      // 31ca: getfield StageMaker.co [LContO;
      // 31cd: iload 5
      // 31cf: aaload
      // 31d0: getfield ContO.colok I
      // 31d3: bipush 38
      // 31d5: if_icmpne 31dd
      // 31d8: goto 3262
      // 31dd: aload 0
      // 31de: getfield StageMaker.hi I
      // 31e1: bipush -1
      // 31e2: if_icmpeq 31ea
      // 31e5: goto 3213
      // 31ea: aload 0
      // 31eb: iload 5
      // 31ed: putfield StageMaker.hi I
      // 31f0: aload 0
      // 31f1: aload 0
      // 31f2: getfield StageMaker.xm I
      // 31f5: aload 0
      // 31f6: iload 6
      // 31f8: iload 9
      // 31fa: invokevirtual StageMaker.xs (II)I
      // 31fd: aload 0
      // 31fe: getfield StageMaker.ym I
      // 3201: aload 0
      // 3202: iload 8
      // 3204: iload 9
      // 3206: invokevirtual StageMaker.ys (II)I
      // 3209: invokevirtual StageMaker.py (IIII)I
      // 320c: istore 4
      // 320e: goto 3262
      // 3213: aload 0
      // 3214: aload 0
      // 3215: getfield StageMaker.xm I
      // 3218: aload 0
      // 3219: iload 6
      // 321b: iload 9
      // 321d: invokevirtual StageMaker.xs (II)I
      // 3220: aload 0
      // 3221: getfield StageMaker.ym I
      // 3224: aload 0
      // 3225: iload 8
      // 3227: iload 9
      // 3229: invokevirtual StageMaker.ys (II)I
      // 322c: invokevirtual StageMaker.py (IIII)I
      // 322f: iload 4
      // 3231: if_icmple 3239
      // 3234: goto 3262
      // 3239: aload 0
      // 323a: iload 5
      // 323c: putfield StageMaker.hi I
      // 323f: aload 0
      // 3240: aload 0
      // 3241: getfield StageMaker.xm I
      // 3244: aload 0
      // 3245: iload 6
      // 3247: iload 9
      // 3249: invokevirtual StageMaker.xs (II)I
      // 324c: aload 0
      // 324d: getfield StageMaker.ym I
      // 3250: aload 0
      // 3251: iload 8
      // 3253: iload 9
      // 3255: invokevirtual StageMaker.ys (II)I
      // 3258: invokevirtual StageMaker.py (IIII)I
      // 325b: istore 4
      // 325d: goto 3262
      // 3262: iinc 5 1
      // 3265: goto 2fcb
      // 326a: aload 0
      // 326b: getfield StageMaker.hi I
      // 326e: bipush -1
      // 326f: if_icmpne 3277
      // 3272: goto 32b9
      // 3277: aload 0
      // 3278: getfield StageMaker.setcur Z
      // 327b: ifeq 3283
      // 327e: goto 329a
      // 3283: aload 0
      // 3284: new java/awt/Cursor
      // 3287: dup
      // 3288: bipush 13
      // 328a: invokespecial java/awt/Cursor.<init> (I)V
      // 328d: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 3290: aload 0
      // 3291: bipush 1
      // 3292: putfield StageMaker.setcur Z
      // 3295: goto 329a
      // 329a: aload 0
      // 329b: getfield StageMaker.mouses I
      // 329e: bipush -1
      // 329f: if_icmpeq 32a7
      // 32a2: goto 32db
      // 32a7: aload 0
      // 32a8: aload 0
      // 32a9: getfield StageMaker.hi I
      // 32ac: putfield StageMaker.esp I
      // 32af: aload 0
      // 32b0: bipush 0
      // 32b1: putfield StageMaker.mouses I
      // 32b4: goto 32db
      // 32b9: aload 0
      // 32ba: getfield StageMaker.setcur Z
      // 32bd: ifne 32c5
      // 32c0: goto 32db
      // 32c5: aload 0
      // 32c6: new java/awt/Cursor
      // 32c9: dup
      // 32ca: bipush 0
      // 32cb: invokespecial java/awt/Cursor.<init> (I)V
      // 32ce: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 32d1: aload 0
      // 32d2: bipush 0
      // 32d3: putfield StageMaker.setcur Z
      // 32d6: goto 32db
      // 32db: goto 3302
      // 32e0: aload 0
      // 32e1: getfield StageMaker.setcur Z
      // 32e4: ifne 32ec
      // 32e7: goto 3302
      // 32ec: aload 0
      // 32ed: new java/awt/Cursor
      // 32f0: dup
      // 32f1: bipush 0
      // 32f2: invokespecial java/awt/Cursor.<init> (I)V
      // 32f5: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 32f8: aload 0
      // 32f9: bipush 0
      // 32fa: putfield StageMaker.setcur Z
      // 32fd: goto 3302
      // 3302: aload 0
      // 3303: getfield StageMaker.arrng Z
      // 3306: ifne 330e
      // 3309: goto 3666
      // 330e: aload 0
      // 330f: bipush -1
      // 3310: putfield StageMaker.chi I
      // 3313: sipush 5000
      // 3316: istore 4
      // 3318: bipush 0
      // 3319: istore 5
      // 331b: iload 5
      // 331d: aload 0
      // 331e: getfield StageMaker.nob I
      // 3321: if_icmplt 3329
      // 3324: goto 35af
      // 3329: aload 0
      // 332a: getfield StageMaker.co [LContO;
      // 332d: iload 5
      // 332f: aaload
      // 3330: getfield ContO.colok I
      // 3333: bipush 30
      // 3335: if_icmpne 333d
      // 3338: goto 3365
      // 333d: aload 0
      // 333e: getfield StageMaker.co [LContO;
      // 3341: iload 5
      // 3343: aaload
      // 3344: getfield ContO.colok I
      // 3347: bipush 32
      // 3349: if_icmpne 3351
      // 334c: goto 3365
      // 3351: aload 0
      // 3352: getfield StageMaker.co [LContO;
      // 3355: iload 5
      // 3357: aaload
      // 3358: getfield ContO.colok I
      // 335b: bipush 54
      // 335d: if_icmpeq 3365
      // 3360: goto 35a7
      // 3365: aload 0
      // 3366: getfield StageMaker.co [LContO;
      // 3369: iload 5
      // 336b: aaload
      // 336c: getfield ContO.errd Z
      // 336f: ifeq 3377
      // 3372: goto 35a7
      // 3377: aload 0
      // 3378: getfield StageMaker.m LMedium;
      // 337b: getfield Medium.cx I
      // 337e: aload 0
      // 337f: getfield StageMaker.co [LContO;
      // 3382: iload 5
      // 3384: aaload
      // 3385: getfield ContO.x I
      // 3388: aload 0
      // 3389: getfield StageMaker.m LMedium;
      // 338c: getfield Medium.x I
      // 338f: isub
      // 3390: aload 0
      // 3391: getfield StageMaker.m LMedium;
      // 3394: getfield Medium.cx I
      // 3397: isub
      // 3398: i2f
      // 3399: aload 0
      // 339a: getfield StageMaker.m LMedium;
      // 339d: aload 0
      // 339e: getfield StageMaker.m LMedium;
      // 33a1: getfield Medium.xz I
      // 33a4: invokevirtual Medium.cos (I)F
      // 33a7: fmul
      // 33a8: aload 0
      // 33a9: getfield StageMaker.co [LContO;
      // 33ac: iload 5
      // 33ae: aaload
      // 33af: getfield ContO.z I
      // 33b2: aload 0
      // 33b3: getfield StageMaker.m LMedium;
      // 33b6: getfield Medium.z I
      // 33b9: isub
      // 33ba: aload 0
      // 33bb: getfield StageMaker.m LMedium;
      // 33be: getfield Medium.cz I
      // 33c1: isub
      // 33c2: i2f
      // 33c3: aload 0
      // 33c4: getfield StageMaker.m LMedium;
      // 33c7: aload 0
      // 33c8: getfield StageMaker.m LMedium;
      // 33cb: getfield Medium.xz I
      // 33ce: invokevirtual Medium.sin (I)F
      // 33d1: fmul
      // 33d2: fsub
      // 33d3: f2i
      // 33d4: iadd
      // 33d5: istore 6
      // 33d7: aload 0
      // 33d8: getfield StageMaker.m LMedium;
      // 33db: getfield Medium.cz I
      // 33de: aload 0
      // 33df: getfield StageMaker.co [LContO;
      // 33e2: iload 5
      // 33e4: aaload
      // 33e5: getfield ContO.x I
      // 33e8: aload 0
      // 33e9: getfield StageMaker.m LMedium;
      // 33ec: getfield Medium.x I
      // 33ef: isub
      // 33f0: aload 0
      // 33f1: getfield StageMaker.m LMedium;
      // 33f4: getfield Medium.cx I
      // 33f7: isub
      // 33f8: i2f
      // 33f9: aload 0
      // 33fa: getfield StageMaker.m LMedium;
      // 33fd: aload 0
      // 33fe: getfield StageMaker.m LMedium;
      // 3401: getfield Medium.xz I
      // 3404: invokevirtual Medium.sin (I)F
      // 3407: fmul
      // 3408: aload 0
      // 3409: getfield StageMaker.co [LContO;
      // 340c: iload 5
      // 340e: aaload
      // 340f: getfield ContO.z I
      // 3412: aload 0
      // 3413: getfield StageMaker.m LMedium;
      // 3416: getfield Medium.z I
      // 3419: isub
      // 341a: aload 0
      // 341b: getfield StageMaker.m LMedium;
      // 341e: getfield Medium.cz I
      // 3421: isub
      // 3422: i2f
      // 3423: aload 0
      // 3424: getfield StageMaker.m LMedium;
      // 3427: aload 0
      // 3428: getfield StageMaker.m LMedium;
      // 342b: getfield Medium.xz I
      // 342e: invokevirtual Medium.cos (I)F
      // 3431: fmul
      // 3432: fadd
      // 3433: f2i
      // 3434: iadd
      // 3435: istore 7
      // 3437: aload 0
      // 3438: getfield StageMaker.m LMedium;
      // 343b: getfield Medium.cy I
      // 343e: aload 0
      // 343f: getfield StageMaker.co [LContO;
      // 3442: iload 5
      // 3444: aaload
      // 3445: getfield ContO.y I
      // 3448: aload 0
      // 3449: getfield StageMaker.m LMedium;
      // 344c: getfield Medium.y I
      // 344f: isub
      // 3450: aload 0
      // 3451: getfield StageMaker.m LMedium;
      // 3454: getfield Medium.cy I
      // 3457: isub
      // 3458: i2f
      // 3459: aload 0
      // 345a: getfield StageMaker.m LMedium;
      // 345d: aload 0
      // 345e: getfield StageMaker.m LMedium;
      // 3461: getfield Medium.zy I
      // 3464: invokevirtual Medium.cos (I)F
      // 3467: fmul
      // 3468: iload 7
      // 346a: aload 0
      // 346b: getfield StageMaker.m LMedium;
      // 346e: getfield Medium.cz I
      // 3471: isub
      // 3472: i2f
      // 3473: aload 0
      // 3474: getfield StageMaker.m LMedium;
      // 3477: aload 0
      // 3478: getfield StageMaker.m LMedium;
      // 347b: getfield Medium.zy I
      // 347e: invokevirtual Medium.sin (I)F
      // 3481: fmul
      // 3482: fsub
      // 3483: f2i
      // 3484: iadd
      // 3485: istore 8
      // 3487: aload 0
      // 3488: getfield StageMaker.m LMedium;
      // 348b: getfield Medium.cz I
      // 348e: aload 0
      // 348f: getfield StageMaker.co [LContO;
      // 3492: iload 5
      // 3494: aaload
      // 3495: getfield ContO.y I
      // 3498: aload 0
      // 3499: getfield StageMaker.m LMedium;
      // 349c: getfield Medium.y I
      // 349f: isub
      // 34a0: aload 0
      // 34a1: getfield StageMaker.m LMedium;
      // 34a4: getfield Medium.cy I
      // 34a7: isub
      // 34a8: i2f
      // 34a9: aload 0
      // 34aa: getfield StageMaker.m LMedium;
      // 34ad: aload 0
      // 34ae: getfield StageMaker.m LMedium;
      // 34b1: getfield Medium.zy I
      // 34b4: invokevirtual Medium.sin (I)F
      // 34b7: fmul
      // 34b8: iload 7
      // 34ba: aload 0
      // 34bb: getfield StageMaker.m LMedium;
      // 34be: getfield Medium.cz I
      // 34c1: isub
      // 34c2: i2f
      // 34c3: aload 0
      // 34c4: getfield StageMaker.m LMedium;
      // 34c7: aload 0
      // 34c8: getfield StageMaker.m LMedium;
      // 34cb: getfield Medium.zy I
      // 34ce: invokevirtual Medium.cos (I)F
      // 34d1: fmul
      // 34d2: fadd
      // 34d3: f2i
      // 34d4: iadd
      // 34d5: istore 9
      // 34d7: aload 0
      // 34d8: getfield StageMaker.xm I
      // 34db: aload 0
      // 34dc: iload 6
      // 34de: aload 0
      // 34df: getfield StageMaker.co [LContO;
      // 34e2: iload 5
      // 34e4: aaload
      // 34e5: getfield ContO.maxR I
      // 34e8: isub
      // 34e9: iload 9
      // 34eb: invokevirtual StageMaker.xs (II)I
      // 34ee: if_icmpgt 34f6
      // 34f1: goto 35a2
      // 34f6: aload 0
      // 34f7: getfield StageMaker.xm I
      // 34fa: aload 0
      // 34fb: iload 6
      // 34fd: aload 0
      // 34fe: getfield StageMaker.co [LContO;
      // 3501: iload 5
      // 3503: aaload
      // 3504: getfield ContO.maxR I
      // 3507: iadd
      // 3508: iload 9
      // 350a: invokevirtual StageMaker.xs (II)I
      // 350d: if_icmplt 3515
      // 3510: goto 35a2
      // 3515: aload 0
      // 3516: getfield StageMaker.ym I
      // 3519: aload 0
      // 351a: iload 8
      // 351c: aload 0
      // 351d: getfield StageMaker.co [LContO;
      // 3520: iload 5
      // 3522: aaload
      // 3523: getfield ContO.maxR I
      // 3526: isub
      // 3527: iload 9
      // 3529: invokevirtual StageMaker.ys (II)I
      // 352c: if_icmpgt 3534
      // 352f: goto 35a2
      // 3534: aload 0
      // 3535: getfield StageMaker.ym I
      // 3538: aload 0
      // 3539: iload 8
      // 353b: aload 0
      // 353c: getfield StageMaker.co [LContO;
      // 353f: iload 5
      // 3541: aaload
      // 3542: getfield ContO.maxR I
      // 3545: iadd
      // 3546: iload 9
      // 3548: invokevirtual StageMaker.ys (II)I
      // 354b: if_icmplt 3553
      // 354e: goto 35a2
      // 3553: aload 0
      // 3554: aload 0
      // 3555: getfield StageMaker.xm I
      // 3558: aload 0
      // 3559: iload 6
      // 355b: iload 9
      // 355d: invokevirtual StageMaker.xs (II)I
      // 3560: aload 0
      // 3561: getfield StageMaker.ym I
      // 3564: aload 0
      // 3565: iload 8
      // 3567: iload 9
      // 3569: invokevirtual StageMaker.ys (II)I
      // 356c: invokevirtual StageMaker.py (IIII)I
      // 356f: iload 4
      // 3571: if_icmple 3579
      // 3574: goto 35a2
      // 3579: aload 0
      // 357a: iload 5
      // 357c: putfield StageMaker.chi I
      // 357f: aload 0
      // 3580: aload 0
      // 3581: getfield StageMaker.xm I
      // 3584: aload 0
      // 3585: iload 6
      // 3587: iload 9
      // 3589: invokevirtual StageMaker.xs (II)I
      // 358c: aload 0
      // 358d: getfield StageMaker.ym I
      // 3590: aload 0
      // 3591: iload 8
      // 3593: iload 9
      // 3595: invokevirtual StageMaker.ys (II)I
      // 3598: invokevirtual StageMaker.py (IIII)I
      // 359b: istore 4
      // 359d: goto 35a2
      // 35a2: goto 35a7
      // 35a7: iinc 5 1
      // 35aa: goto 331b
      // 35af: aload 0
      // 35b0: getfield StageMaker.chi I
      // 35b3: bipush -1
      // 35b4: if_icmpne 35bc
      // 35b7: goto 361d
      // 35bc: aload 0
      // 35bd: getfield StageMaker.setcur Z
      // 35c0: ifeq 35c8
      // 35c3: goto 35df
      // 35c8: aload 0
      // 35c9: new java/awt/Cursor
      // 35cc: dup
      // 35cd: bipush 13
      // 35cf: invokespecial java/awt/Cursor.<init> (I)V
      // 35d2: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 35d5: aload 0
      // 35d6: bipush 1
      // 35d7: putfield StageMaker.setcur Z
      // 35da: goto 35df
      // 35df: aload 0
      // 35e0: getfield StageMaker.mouses I
      // 35e3: bipush -1
      // 35e4: if_icmpeq 35ec
      // 35e7: goto 363f
      // 35ec: aload 0
      // 35ed: dup
      // 35ee: getfield StageMaker.arrcnt I
      // 35f1: bipush 1
      // 35f2: iadd
      // 35f3: putfield StageMaker.arrcnt I
      // 35f6: aload 0
      // 35f7: getfield StageMaker.co [LContO;
      // 35fa: aload 0
      // 35fb: getfield StageMaker.chi I
      // 35fe: aaload
      // 35ff: aload 0
      // 3600: getfield StageMaker.arrcnt I
      // 3603: putfield ContO.wh I
      // 3606: aload 0
      // 3607: getfield StageMaker.co [LContO;
      // 360a: aload 0
      // 360b: getfield StageMaker.chi I
      // 360e: aaload
      // 360f: bipush 1
      // 3610: putfield ContO.errd Z
      // 3613: aload 0
      // 3614: bipush 0
      // 3615: putfield StageMaker.mouses I
      // 3618: goto 363f
      // 361d: aload 0
      // 361e: getfield StageMaker.setcur Z
      // 3621: ifne 3629
      // 3624: goto 363f
      // 3629: aload 0
      // 362a: new java/awt/Cursor
      // 362d: dup
      // 362e: bipush 0
      // 362f: invokespecial java/awt/Cursor.<init> (I)V
      // 3632: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 3635: aload 0
      // 3636: bipush 0
      // 3637: putfield StageMaker.setcur Z
      // 363a: goto 363f
      // 363f: goto 3666
      // 3644: aload 0
      // 3645: getfield StageMaker.setcur Z
      // 3648: ifne 3650
      // 364b: goto 3666
      // 3650: aload 0
      // 3651: new java/awt/Cursor
      // 3654: dup
      // 3655: bipush 0
      // 3656: invokespecial java/awt/Cursor.<init> (I)V
      // 3659: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 365c: aload 0
      // 365d: bipush 0
      // 365e: putfield StageMaker.setcur Z
      // 3661: goto 3666
      // 3666: aload 0
      // 3667: getfield StageMaker.epart Z
      // 366a: ifne 3672
      // 366d: goto 3a12
      // 3672: aload 0
      // 3673: getfield StageMaker.esp I
      // 3676: bipush -1
      // 3677: if_icmpne 367f
      // 367a: goto 3a12
      // 367f: aload 0
      // 3680: getfield StageMaker.co [LContO;
      // 3683: aload 0
      // 3684: getfield StageMaker.esp I
      // 3687: aaload
      // 3688: getfield ContO.dist I
      // 368b: ifne 3693
      // 368e: goto 3a08
      // 3693: aload 0
      // 3694: getfield StageMaker.m LMedium;
      // 3697: sipush 505
      // 369a: putfield Medium.cx I
      // 369d: aload 0
      // 369e: getfield StageMaker.m LMedium;
      // 36a1: sipush 290
      // 36a4: putfield Medium.cy I
      // 36a7: aload 0
      // 36a8: getfield StageMaker.m LMedium;
      // 36ab: aload 0
      // 36ac: getfield StageMaker.sx I
      // 36af: aload 0
      // 36b0: getfield StageMaker.m LMedium;
      // 36b3: getfield Medium.cx I
      // 36b6: isub
      // 36b7: putfield Medium.x I
      // 36ba: aload 0
      // 36bb: getfield StageMaker.m LMedium;
      // 36be: aload 0
      // 36bf: getfield StageMaker.sz I
      // 36c2: aload 0
      // 36c3: getfield StageMaker.m LMedium;
      // 36c6: getfield Medium.cz I
      // 36c9: isub
      // 36ca: putfield Medium.z I
      // 36cd: aload 0
      // 36ce: getfield StageMaker.m LMedium;
      // 36d1: aload 0
      // 36d2: getfield StageMaker.sy I
      // 36d5: putfield Medium.y I
      // 36d8: aload 0
      // 36d9: getfield StageMaker.m LMedium;
      // 36dc: getfield Medium.cx I
      // 36df: aload 0
      // 36e0: getfield StageMaker.co [LContO;
      // 36e3: aload 0
      // 36e4: getfield StageMaker.esp I
      // 36e7: aaload
      // 36e8: getfield ContO.x I
      // 36eb: aload 0
      // 36ec: getfield StageMaker.m LMedium;
      // 36ef: getfield Medium.x I
      // 36f2: isub
      // 36f3: aload 0
      // 36f4: getfield StageMaker.m LMedium;
      // 36f7: getfield Medium.cx I
      // 36fa: isub
      // 36fb: i2f
      // 36fc: aload 0
      // 36fd: getfield StageMaker.m LMedium;
      // 3700: aload 0
      // 3701: getfield StageMaker.m LMedium;
      // 3704: getfield Medium.xz I
      // 3707: invokevirtual Medium.cos (I)F
      // 370a: fmul
      // 370b: aload 0
      // 370c: getfield StageMaker.co [LContO;
      // 370f: aload 0
      // 3710: getfield StageMaker.esp I
      // 3713: aaload
      // 3714: getfield ContO.z I
      // 3717: aload 0
      // 3718: getfield StageMaker.m LMedium;
      // 371b: getfield Medium.z I
      // 371e: isub
      // 371f: aload 0
      // 3720: getfield StageMaker.m LMedium;
      // 3723: getfield Medium.cz I
      // 3726: isub
      // 3727: i2f
      // 3728: aload 0
      // 3729: getfield StageMaker.m LMedium;
      // 372c: aload 0
      // 372d: getfield StageMaker.m LMedium;
      // 3730: getfield Medium.xz I
      // 3733: invokevirtual Medium.sin (I)F
      // 3736: fmul
      // 3737: fsub
      // 3738: f2i
      // 3739: iadd
      // 373a: istore 4
      // 373c: aload 0
      // 373d: getfield StageMaker.m LMedium;
      // 3740: getfield Medium.cz I
      // 3743: aload 0
      // 3744: getfield StageMaker.co [LContO;
      // 3747: aload 0
      // 3748: getfield StageMaker.esp I
      // 374b: aaload
      // 374c: getfield ContO.x I
      // 374f: aload 0
      // 3750: getfield StageMaker.m LMedium;
      // 3753: getfield Medium.x I
      // 3756: isub
      // 3757: aload 0
      // 3758: getfield StageMaker.m LMedium;
      // 375b: getfield Medium.cx I
      // 375e: isub
      // 375f: i2f
      // 3760: aload 0
      // 3761: getfield StageMaker.m LMedium;
      // 3764: aload 0
      // 3765: getfield StageMaker.m LMedium;
      // 3768: getfield Medium.xz I
      // 376b: invokevirtual Medium.sin (I)F
      // 376e: fmul
      // 376f: aload 0
      // 3770: getfield StageMaker.co [LContO;
      // 3773: aload 0
      // 3774: getfield StageMaker.esp I
      // 3777: aaload
      // 3778: getfield ContO.z I
      // 377b: aload 0
      // 377c: getfield StageMaker.m LMedium;
      // 377f: getfield Medium.z I
      // 3782: isub
      // 3783: aload 0
      // 3784: getfield StageMaker.m LMedium;
      // 3787: getfield Medium.cz I
      // 378a: isub
      // 378b: i2f
      // 378c: aload 0
      // 378d: getfield StageMaker.m LMedium;
      // 3790: aload 0
      // 3791: getfield StageMaker.m LMedium;
      // 3794: getfield Medium.xz I
      // 3797: invokevirtual Medium.cos (I)F
      // 379a: fmul
      // 379b: fadd
      // 379c: f2i
      // 379d: iadd
      // 379e: istore 5
      // 37a0: aload 0
      // 37a1: getfield StageMaker.m LMedium;
      // 37a4: getfield Medium.cy I
      // 37a7: aload 0
      // 37a8: getfield StageMaker.co [LContO;
      // 37ab: aload 0
      // 37ac: getfield StageMaker.esp I
      // 37af: aaload
      // 37b0: getfield ContO.y I
      // 37b3: aload 0
      // 37b4: getfield StageMaker.m LMedium;
      // 37b7: getfield Medium.y I
      // 37ba: isub
      // 37bb: aload 0
      // 37bc: getfield StageMaker.m LMedium;
      // 37bf: getfield Medium.cy I
      // 37c2: isub
      // 37c3: i2f
      // 37c4: aload 0
      // 37c5: getfield StageMaker.m LMedium;
      // 37c8: aload 0
      // 37c9: getfield StageMaker.m LMedium;
      // 37cc: getfield Medium.zy I
      // 37cf: invokevirtual Medium.cos (I)F
      // 37d2: fmul
      // 37d3: iload 5
      // 37d5: aload 0
      // 37d6: getfield StageMaker.m LMedium;
      // 37d9: getfield Medium.cz I
      // 37dc: isub
      // 37dd: i2f
      // 37de: aload 0
      // 37df: getfield StageMaker.m LMedium;
      // 37e2: aload 0
      // 37e3: getfield StageMaker.m LMedium;
      // 37e6: getfield Medium.zy I
      // 37e9: invokevirtual Medium.sin (I)F
      // 37ec: fmul
      // 37ed: fsub
      // 37ee: f2i
      // 37ef: iadd
      // 37f0: istore 6
      // 37f2: aload 0
      // 37f3: getfield StageMaker.m LMedium;
      // 37f6: getfield Medium.cz I
      // 37f9: aload 0
      // 37fa: getfield StageMaker.co [LContO;
      // 37fd: aload 0
      // 37fe: getfield StageMaker.esp I
      // 3801: aaload
      // 3802: getfield ContO.y I
      // 3805: aload 0
      // 3806: getfield StageMaker.m LMedium;
      // 3809: getfield Medium.y I
      // 380c: isub
      // 380d: aload 0
      // 380e: getfield StageMaker.m LMedium;
      // 3811: getfield Medium.cy I
      // 3814: isub
      // 3815: i2f
      // 3816: aload 0
      // 3817: getfield StageMaker.m LMedium;
      // 381a: aload 0
      // 381b: getfield StageMaker.m LMedium;
      // 381e: getfield Medium.zy I
      // 3821: invokevirtual Medium.sin (I)F
      // 3824: fmul
      // 3825: iload 5
      // 3827: aload 0
      // 3828: getfield StageMaker.m LMedium;
      // 382b: getfield Medium.cz I
      // 382e: isub
      // 382f: i2f
      // 3830: aload 0
      // 3831: getfield StageMaker.m LMedium;
      // 3834: aload 0
      // 3835: getfield StageMaker.m LMedium;
      // 3838: getfield Medium.zy I
      // 383b: invokevirtual Medium.cos (I)F
      // 383e: fmul
      // 383f: fadd
      // 3840: f2i
      // 3841: iadd
      // 3842: istore 7
      // 3844: aload 0
      // 3845: iload 4
      // 3847: iload 7
      // 3849: invokevirtual StageMaker.xs (II)I
      // 384c: istore 8
      // 384e: aload 0
      // 384f: iload 6
      // 3851: iload 7
      // 3853: invokevirtual StageMaker.ys (II)I
      // 3856: istore 9
      // 3858: aload 0
      // 3859: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 385c: new java/awt/Color
      // 385f: dup
      // 3860: sipush 225
      // 3863: sipush 225
      // 3866: sipush 225
      // 3869: invokespecial java/awt/Color.<init> (III)V
      // 386c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 386f: aload 0
      // 3870: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3873: iload 8
      // 3875: iload 9
      // 3877: bipush 90
      // 3879: bipush 88
      // 387b: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 387e: aload 0
      // 387f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3882: new java/awt/Color
      // 3885: dup
      // 3886: sipush 138
      // 3889: sipush 147
      // 388c: sipush 160
      // 388f: invokespecial java/awt/Color.<init> (III)V
      // 3892: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3895: aload 0
      // 3896: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3899: iload 8
      // 389b: iload 9
      // 389d: bipush 90
      // 389f: bipush 88
      // 38a1: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 38a4: aload 0
      // 38a5: ldc_w "   Edit   "
      // 38a8: iload 8
      // 38aa: bipush 45
      // 38ac: iadd
      // 38ad: iload 9
      // 38af: bipush 22
      // 38b1: iadd
      // 38b2: bipush 3
      // 38b3: bipush 0
      // 38b4: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 38b7: ifne 38bf
      // 38ba: goto 38e1
      // 38bf: aload 0
      // 38c0: bipush 1
      // 38c1: invokevirtual StageMaker.copyesp (Z)V
      // 38c4: aload 0
      // 38c5: invokevirtual StageMaker.removesp ()V
      // 38c8: aload 0
      // 38c9: bipush 0
      // 38ca: putfield StageMaker.lxm I
      // 38cd: aload 0
      // 38ce: bipush 0
      // 38cf: putfield StageMaker.lym I
      // 38d2: aload 0
      // 38d3: bipush 2
      // 38d4: putfield StageMaker.cntout I
      // 38d7: aload 0
      // 38d8: bipush 0
      // 38d9: putfield StageMaker.epart Z
      // 38dc: goto 38e1
      // 38e1: aload 0
      // 38e2: ldc_w " Remove "
      // 38e5: iload 8
      // 38e7: bipush 45
      // 38e9: iadd
      // 38ea: iload 9
      // 38ec: bipush 49
      // 38ee: iadd
      // 38ef: bipush 3
      // 38f0: bipush 0
      // 38f1: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 38f4: ifne 38fc
      // 38f7: goto 390f
      // 38fc: aload 0
      // 38fd: invokevirtual StageMaker.removesp ()V
      // 3900: aload 0
      // 3901: bipush -1
      // 3902: putfield StageMaker.esp I
      // 3905: aload 0
      // 3906: bipush 0
      // 3907: putfield StageMaker.mouses I
      // 390a: goto 390f
      // 390f: aload 0
      // 3910: ldc_w "  Copy  "
      // 3913: iload 8
      // 3915: bipush 45
      // 3917: iadd
      // 3918: iload 9
      // 391a: bipush 76
      // 391c: iadd
      // 391d: bipush 3
      // 391e: bipush 0
      // 391f: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 3922: ifne 392a
      // 3925: goto 3948
      // 392a: aload 0
      // 392b: bipush 0
      // 392c: invokevirtual StageMaker.copyesp (Z)V
      // 392f: aload 0
      // 3930: bipush 0
      // 3931: putfield StageMaker.lxm I
      // 3934: aload 0
      // 3935: bipush 0
      // 3936: putfield StageMaker.lym I
      // 3939: aload 0
      // 393a: bipush 2
      // 393b: putfield StageMaker.cntout I
      // 393e: aload 0
      // 393f: bipush 0
      // 3940: putfield StageMaker.epart Z
      // 3943: goto 3948
      // 3948: aload 0
      // 3949: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 394c: new java/awt/Color
      // 394f: dup
      // 3950: sipush 255
      // 3953: bipush 0
      // 3954: bipush 0
      // 3955: invokespecial java/awt/Color.<init> (III)V
      // 3958: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 395b: aload 0
      // 395c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 395f: ldc_w "x"
      // 3962: iload 8
      // 3964: bipush 82
      // 3966: iadd
      // 3967: iload 9
      // 3969: bipush 2
      // 396a: isub
      // 396b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 396e: aload 0
      // 396f: getfield StageMaker.xm I
      // 3972: sipush 248
      // 3975: if_icmpgt 397d
      // 3978: goto 3a03
      // 397d: aload 0
      // 397e: getfield StageMaker.xm I
      // 3981: sipush 762
      // 3984: if_icmplt 398c
      // 3987: goto 3a03
      // 398c: aload 0
      // 398d: getfield StageMaker.ym I
      // 3990: bipush 63
      // 3992: if_icmpgt 399a
      // 3995: goto 3a03
      // 399a: aload 0
      // 399b: getfield StageMaker.ym I
      // 399e: sipush 517
      // 39a1: if_icmplt 39a9
      // 39a4: goto 3a03
      // 39a9: aload 0
      // 39aa: getfield StageMaker.mouses I
      // 39ad: bipush 1
      // 39ae: if_icmpeq 39b6
      // 39b1: goto 3a03
      // 39b6: aload 0
      // 39b7: getfield StageMaker.xm I
      // 39ba: iload 8
      // 39bc: if_icmpge 39c4
      // 39bf: goto 39f4
      // 39c4: aload 0
      // 39c5: getfield StageMaker.xm I
      // 39c8: iload 8
      // 39ca: bipush 90
      // 39cc: iadd
      // 39cd: if_icmple 39d5
      // 39d0: goto 39f4
      // 39d5: aload 0
      // 39d6: getfield StageMaker.ym I
      // 39d9: iload 9
      // 39db: if_icmpge 39e3
      // 39de: goto 39f4
      // 39e3: aload 0
      // 39e4: getfield StageMaker.ym I
      // 39e7: iload 9
      // 39e9: bipush 88
      // 39eb: iadd
      // 39ec: if_icmpgt 39f4
      // 39ef: goto 3a03
      // 39f4: aload 0
      // 39f5: bipush -1
      // 39f6: putfield StageMaker.esp I
      // 39f9: aload 0
      // 39fa: bipush 0
      // 39fb: putfield StageMaker.mouses I
      // 39fe: goto 3a03
      // 3a03: goto 3a12
      // 3a08: aload 0
      // 3a09: bipush -1
      // 3a0a: putfield StageMaker.esp I
      // 3a0d: goto 3a12
      // 3a12: aload 0
      // 3a13: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a16: new java/awt/Color
      // 3a19: dup
      // 3a1a: sipush 225
      // 3a1d: sipush 225
      // 3a20: sipush 225
      // 3a23: invokespecial java/awt/Color.<init> (III)V
      // 3a26: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3a29: aload 0
      // 3a2a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a2d: sipush 248
      // 3a30: bipush 25
      // 3a32: sipush 514
      // 3a35: bipush 38
      // 3a37: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 3a3a: aload 0
      // 3a3b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a3e: bipush 0
      // 3a3f: bipush 25
      // 3a41: sipush 248
      // 3a44: sipush 530
      // 3a47: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 3a4a: aload 0
      // 3a4b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a4e: sipush 248
      // 3a51: sipush 517
      // 3a54: sipush 514
      // 3a57: bipush 38
      // 3a59: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 3a5c: aload 0
      // 3a5d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a60: sipush 762
      // 3a63: bipush 25
      // 3a65: bipush 38
      // 3a67: sipush 530
      // 3a6a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 3a6d: aload 0
      // 3a6e: getfield StageMaker.sptyp I
      // 3a71: bipush 6
      // 3a73: if_icmpeq 3a7b
      // 3a76: goto 3d4b
      // 3a7b: aload 0
      // 3a7c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a7f: new java/awt/Color
      // 3a82: dup
      // 3a83: bipush 0
      // 3a84: bipush 0
      // 3a85: bipush 0
      // 3a86: invokespecial java/awt/Color.<init> (III)V
      // 3a89: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3a8c: aload 0
      // 3a8d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3a90: new java/awt/Font
      // 3a93: dup
      // 3a94: ldc_w "Arial"
      // 3a97: bipush 1
      // 3a98: bipush 12
      // 3a9a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3a9d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3aa0: aload 0
      // 3aa1: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3aa4: ldc_w "Radius:"
      // 3aa7: bipush 11
      // 3aa9: bipush 97
      // 3aab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3aae: aload 0
      // 3aaf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3ab2: ldc_w "Height:"
      // 3ab5: bipush 14
      // 3ab7: bipush 117
      // 3ab9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3abc: bipush 0
      // 3abd: istore 4
      // 3abf: aload 0
      // 3ac0: getfield StageMaker.xm I
      // 3ac3: bipush 57
      // 3ac5: if_icmpgt 3acd
      // 3ac8: goto 3b00
      // 3acd: aload 0
      // 3ace: getfield StageMaker.xm I
      // 3ad1: sipush 204
      // 3ad4: if_icmplt 3adc
      // 3ad7: goto 3b00
      // 3adc: aload 0
      // 3add: getfield StageMaker.ym I
      // 3ae0: bipush 90
      // 3ae2: if_icmpgt 3aea
      // 3ae5: goto 3b00
      // 3aea: aload 0
      // 3aeb: getfield StageMaker.ym I
      // 3aee: bipush 99
      // 3af0: if_icmplt 3af8
      // 3af3: goto 3b00
      // 3af8: bipush 1
      // 3af9: istore 4
      // 3afb: goto 3b00
      // 3b00: aload 0
      // 3b01: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3b04: new java/awt/Color
      // 3b07: dup
      // 3b08: sipush 136
      // 3b0b: sipush 148
      // 3b0e: sipush 170
      // 3b11: invokespecial java/awt/Color.<init> (III)V
      // 3b14: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3b17: iload 4
      // 3b19: ifeq 3b21
      // 3b1c: goto 3b2e
      // 3b21: aload 0
      // 3b22: getfield StageMaker.mouseon I
      // 3b25: bipush 1
      // 3b26: if_icmpeq 3b2e
      // 3b29: goto 3b54
      // 3b2e: aload 0
      // 3b2f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3b32: bipush 57
      // 3b34: bipush 90
      // 3b36: sipush 147
      // 3b39: bipush 8
      // 3b3b: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 3b3e: aload 0
      // 3b3f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3b42: new java/awt/Color
      // 3b45: dup
      // 3b46: bipush 0
      // 3b47: bipush 0
      // 3b48: bipush 0
      // 3b49: invokespecial java/awt/Color.<init> (III)V
      // 3b4c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3b4f: goto 3b54
      // 3b54: aload 0
      // 3b55: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3b58: bipush 57
      // 3b5a: bipush 94
      // 3b5c: sipush 204
      // 3b5f: bipush 94
      // 3b61: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3b64: aload 0
      // 3b65: getfield StageMaker.mouseon I
      // 3b68: bipush 1
      // 3b69: if_icmpeq 3b71
      // 3b6c: goto 3bb7
      // 3b71: aload 0
      // 3b72: aload 0
      // 3b73: getfield StageMaker.xm I
      // 3b76: bipush 57
      // 3b78: isub
      // 3b79: i2f
      // 3b7a: ldc_w 36.75
      // 3b7d: fdiv
      // 3b7e: fconst_2
      // 3b7f: fadd
      // 3b80: putfield StageMaker.pwd F
      // 3b83: aload 0
      // 3b84: getfield StageMaker.pwd F
      // 3b87: fconst_2
      // 3b88: fcmpg
      // 3b89: iflt 3b91
      // 3b8c: goto 3b9b
      // 3b91: aload 0
      // 3b92: fconst_2
      // 3b93: putfield StageMaker.pwd F
      // 3b96: goto 3b9b
      // 3b9b: aload 0
      // 3b9c: getfield StageMaker.pwd F
      // 3b9f: ldc_w 6.0
      // 3ba2: fcmpl
      // 3ba3: ifgt 3bab
      // 3ba6: goto 3bb7
      // 3bab: aload 0
      // 3bac: ldc_w 6.0
      // 3baf: putfield StageMaker.pwd F
      // 3bb2: goto 3bb7
      // 3bb7: aload 0
      // 3bb8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3bbb: ldc_w 57.0
      // 3bbe: aload 0
      // 3bbf: getfield StageMaker.pwd F
      // 3bc2: fconst_2
      // 3bc3: fsub
      // 3bc4: ldc_w 36.75
      // 3bc7: fmul
      // 3bc8: fadd
      // 3bc9: f2i
      // 3bca: bipush 90
      // 3bcc: bipush 2
      // 3bcd: bipush 8
      // 3bcf: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 3bd2: bipush 0
      // 3bd3: istore 5
      // 3bd5: aload 0
      // 3bd6: getfield StageMaker.xm I
      // 3bd9: bipush 57
      // 3bdb: if_icmpgt 3be3
      // 3bde: goto 3c16
      // 3be3: aload 0
      // 3be4: getfield StageMaker.xm I
      // 3be7: sipush 204
      // 3bea: if_icmplt 3bf2
      // 3bed: goto 3c16
      // 3bf2: aload 0
      // 3bf3: getfield StageMaker.ym I
      // 3bf6: bipush 110
      // 3bf8: if_icmpgt 3c00
      // 3bfb: goto 3c16
      // 3c00: aload 0
      // 3c01: getfield StageMaker.ym I
      // 3c04: bipush 119
      // 3c06: if_icmplt 3c0e
      // 3c09: goto 3c16
      // 3c0e: bipush 1
      // 3c0f: istore 5
      // 3c11: goto 3c16
      // 3c16: aload 0
      // 3c17: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3c1a: new java/awt/Color
      // 3c1d: dup
      // 3c1e: sipush 136
      // 3c21: sipush 148
      // 3c24: sipush 170
      // 3c27: invokespecial java/awt/Color.<init> (III)V
      // 3c2a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3c2d: iload 5
      // 3c2f: ifeq 3c37
      // 3c32: goto 3c44
      // 3c37: aload 0
      // 3c38: getfield StageMaker.mouseon I
      // 3c3b: bipush 2
      // 3c3c: if_icmpeq 3c44
      // 3c3f: goto 3c6a
      // 3c44: aload 0
      // 3c45: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3c48: bipush 57
      // 3c4a: bipush 110
      // 3c4c: sipush 147
      // 3c4f: bipush 8
      // 3c51: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 3c54: aload 0
      // 3c55: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3c58: new java/awt/Color
      // 3c5b: dup
      // 3c5c: bipush 0
      // 3c5d: bipush 0
      // 3c5e: bipush 0
      // 3c5f: invokespecial java/awt/Color.<init> (III)V
      // 3c62: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3c65: goto 3c6a
      // 3c6a: aload 0
      // 3c6b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3c6e: bipush 57
      // 3c70: bipush 114
      // 3c72: sipush 204
      // 3c75: bipush 114
      // 3c77: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3c7a: aload 0
      // 3c7b: getfield StageMaker.mouseon I
      // 3c7e: bipush 2
      // 3c7f: if_icmpeq 3c87
      // 3c82: goto 3ccd
      // 3c87: aload 0
      // 3c88: aload 0
      // 3c89: getfield StageMaker.xm I
      // 3c8c: bipush 57
      // 3c8e: isub
      // 3c8f: i2f
      // 3c90: ldc_w 36.75
      // 3c93: fdiv
      // 3c94: fconst_2
      // 3c95: fadd
      // 3c96: putfield StageMaker.phd F
      // 3c99: aload 0
      // 3c9a: getfield StageMaker.phd F
      // 3c9d: fconst_2
      // 3c9e: fcmpg
      // 3c9f: iflt 3ca7
      // 3ca2: goto 3cb1
      // 3ca7: aload 0
      // 3ca8: fconst_2
      // 3ca9: putfield StageMaker.phd F
      // 3cac: goto 3cb1
      // 3cb1: aload 0
      // 3cb2: getfield StageMaker.phd F
      // 3cb5: ldc_w 6.0
      // 3cb8: fcmpl
      // 3cb9: ifgt 3cc1
      // 3cbc: goto 3ccd
      // 3cc1: aload 0
      // 3cc2: ldc_w 6.0
      // 3cc5: putfield StageMaker.phd F
      // 3cc8: goto 3ccd
      // 3ccd: aload 0
      // 3cce: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3cd1: ldc_w 57.0
      // 3cd4: aload 0
      // 3cd5: getfield StageMaker.phd F
      // 3cd8: fconst_2
      // 3cd9: fsub
      // 3cda: ldc_w 36.75
      // 3cdd: fmul
      // 3cde: fadd
      // 3cdf: f2i
      // 3ce0: bipush 110
      // 3ce2: bipush 2
      // 3ce3: bipush 8
      // 3ce5: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 3ce8: aload 0
      // 3ce9: getfield StageMaker.mouses I
      // 3cec: bipush 1
      // 3ced: if_icmpeq 3cf5
      // 3cf0: goto 3d1d
      // 3cf5: iload 4
      // 3cf7: ifne 3cff
      // 3cfa: goto 3d09
      // 3cff: aload 0
      // 3d00: bipush 1
      // 3d01: putfield StageMaker.mouseon I
      // 3d04: goto 3d09
      // 3d09: iload 5
      // 3d0b: ifne 3d13
      // 3d0e: goto 3d46
      // 3d13: aload 0
      // 3d14: bipush 2
      // 3d15: putfield StageMaker.mouseon I
      // 3d18: goto 3d46
      // 3d1d: aload 0
      // 3d1e: getfield StageMaker.mouseon I
      // 3d21: bipush 1
      // 3d22: if_icmpne 3d2a
      // 3d25: goto 3d37
      // 3d2a: aload 0
      // 3d2b: getfield StageMaker.mouseon I
      // 3d2e: bipush 2
      // 3d2f: if_icmpeq 3d37
      // 3d32: goto 3d41
      // 3d37: aload 0
      // 3d38: bipush 0
      // 3d39: putfield StageMaker.pgen Z
      // 3d3c: goto 3d41
      // 3d41: aload 0
      // 3d42: bipush -1
      // 3d43: putfield StageMaker.mouseon I
      // 3d46: goto 3d4b
      // 3d4b: bipush 0
      // 3d4c: istore 4
      // 3d4e: aload 0
      // 3d4f: getfield StageMaker.xm I
      // 3d52: sipush 482
      // 3d55: if_icmpgt 3d5d
      // 3d58: goto 3d88
      // 3d5d: aload 0
      // 3d5e: getfield StageMaker.xm I
      // 3d61: sipush 529
      // 3d64: if_icmplt 3d6c
      // 3d67: goto 3d88
      // 3d6c: aload 0
      // 3d6d: getfield StageMaker.ym I
      // 3d70: bipush 35
      // 3d72: if_icmpgt 3d7a
      // 3d75: goto 3d88
      // 3d7a: aload 0
      // 3d7b: getfield StageMaker.ym I
      // 3d7e: bipush 61
      // 3d80: if_icmpge 3d88
      // 3d83: goto 3d94
      // 3d88: aload 0
      // 3d89: getfield StageMaker.up Z
      // 3d8c: ifne 3d94
      // 3d8f: goto 3dc1
      // 3d94: bipush 1
      // 3d95: istore 4
      // 3d97: aload 0
      // 3d98: getfield StageMaker.mouses I
      // 3d9b: bipush 1
      // 3d9c: if_icmpne 3da4
      // 3d9f: goto 3db0
      // 3da4: aload 0
      // 3da5: getfield StageMaker.up Z
      // 3da8: ifne 3db0
      // 3dab: goto 3dc1
      // 3db0: aload 0
      // 3db1: dup
      // 3db2: getfield StageMaker.sz I
      // 3db5: sipush 500
      // 3db8: iadd
      // 3db9: putfield StageMaker.sz I
      // 3dbc: goto 3dc1
      // 3dc1: aload 0
      // 3dc2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3dc5: aload 0
      // 3dc6: getfield StageMaker.su [Ljava/awt/Image;
      // 3dc9: iload 4
      // 3dcb: aaload
      // 3dcc: sipush 482
      // 3dcf: bipush 35
      // 3dd1: aconst_null
      // 3dd2: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 3dd5: pop
      // 3dd6: bipush 0
      // 3dd7: istore 4
      // 3dd9: aload 0
      // 3dda: getfield StageMaker.xm I
      // 3ddd: sipush 482
      // 3de0: if_icmpgt 3de8
      // 3de3: goto 3e15
      // 3de8: aload 0
      // 3de9: getfield StageMaker.xm I
      // 3dec: sipush 529
      // 3def: if_icmplt 3df7
      // 3df2: goto 3e15
      // 3df7: aload 0
      // 3df8: getfield StageMaker.ym I
      // 3dfb: sipush 519
      // 3dfe: if_icmpgt 3e06
      // 3e01: goto 3e15
      // 3e06: aload 0
      // 3e07: getfield StageMaker.ym I
      // 3e0a: sipush 545
      // 3e0d: if_icmpge 3e15
      // 3e10: goto 3e21
      // 3e15: aload 0
      // 3e16: getfield StageMaker.down Z
      // 3e19: ifne 3e21
      // 3e1c: goto 3e4e
      // 3e21: bipush 1
      // 3e22: istore 4
      // 3e24: aload 0
      // 3e25: getfield StageMaker.mouses I
      // 3e28: bipush 1
      // 3e29: if_icmpne 3e31
      // 3e2c: goto 3e3d
      // 3e31: aload 0
      // 3e32: getfield StageMaker.down Z
      // 3e35: ifne 3e3d
      // 3e38: goto 3e4e
      // 3e3d: aload 0
      // 3e3e: dup
      // 3e3f: getfield StageMaker.sz I
      // 3e42: sipush 500
      // 3e45: isub
      // 3e46: putfield StageMaker.sz I
      // 3e49: goto 3e4e
      // 3e4e: aload 0
      // 3e4f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3e52: aload 0
      // 3e53: getfield StageMaker.sd [Ljava/awt/Image;
      // 3e56: iload 4
      // 3e58: aaload
      // 3e59: sipush 482
      // 3e5c: sipush 519
      // 3e5f: aconst_null
      // 3e60: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 3e63: pop
      // 3e64: bipush 0
      // 3e65: istore 4
      // 3e67: aload 0
      // 3e68: getfield StageMaker.xm I
      // 3e6b: sipush 220
      // 3e6e: if_icmpgt 3e76
      // 3e71: goto 3ea3
      // 3e76: aload 0
      // 3e77: getfield StageMaker.xm I
      // 3e7a: sipush 246
      // 3e7d: if_icmplt 3e85
      // 3e80: goto 3ea3
      // 3e85: aload 0
      // 3e86: getfield StageMaker.ym I
      // 3e89: sipush 264
      // 3e8c: if_icmpgt 3e94
      // 3e8f: goto 3ea3
      // 3e94: aload 0
      // 3e95: getfield StageMaker.ym I
      // 3e98: sipush 311
      // 3e9b: if_icmpge 3ea3
      // 3e9e: goto 3eaf
      // 3ea3: aload 0
      // 3ea4: getfield StageMaker.left Z
      // 3ea7: ifne 3eaf
      // 3eaa: goto 3edc
      // 3eaf: bipush 1
      // 3eb0: istore 4
      // 3eb2: aload 0
      // 3eb3: getfield StageMaker.mouses I
      // 3eb6: bipush 1
      // 3eb7: if_icmpne 3ebf
      // 3eba: goto 3ecb
      // 3ebf: aload 0
      // 3ec0: getfield StageMaker.left Z
      // 3ec3: ifne 3ecb
      // 3ec6: goto 3edc
      // 3ecb: aload 0
      // 3ecc: dup
      // 3ecd: getfield StageMaker.sx I
      // 3ed0: sipush 500
      // 3ed3: isub
      // 3ed4: putfield StageMaker.sx I
      // 3ed7: goto 3edc
      // 3edc: aload 0
      // 3edd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3ee0: aload 0
      // 3ee1: getfield StageMaker.sl [Ljava/awt/Image;
      // 3ee4: iload 4
      // 3ee6: aaload
      // 3ee7: sipush 220
      // 3eea: sipush 264
      // 3eed: aconst_null
      // 3eee: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 3ef1: pop
      // 3ef2: bipush 0
      // 3ef3: istore 4
      // 3ef5: aload 0
      // 3ef6: getfield StageMaker.xm I
      // 3ef9: sipush 764
      // 3efc: if_icmpgt 3f04
      // 3eff: goto 3f31
      // 3f04: aload 0
      // 3f05: getfield StageMaker.xm I
      // 3f08: sipush 790
      // 3f0b: if_icmplt 3f13
      // 3f0e: goto 3f31
      // 3f13: aload 0
      // 3f14: getfield StageMaker.ym I
      // 3f17: sipush 264
      // 3f1a: if_icmpgt 3f22
      // 3f1d: goto 3f31
      // 3f22: aload 0
      // 3f23: getfield StageMaker.ym I
      // 3f26: sipush 311
      // 3f29: if_icmpge 3f31
      // 3f2c: goto 3f3d
      // 3f31: aload 0
      // 3f32: getfield StageMaker.right Z
      // 3f35: ifne 3f3d
      // 3f38: goto 3f6a
      // 3f3d: bipush 1
      // 3f3e: istore 4
      // 3f40: aload 0
      // 3f41: getfield StageMaker.mouses I
      // 3f44: bipush 1
      // 3f45: if_icmpne 3f4d
      // 3f48: goto 3f59
      // 3f4d: aload 0
      // 3f4e: getfield StageMaker.right Z
      // 3f51: ifne 3f59
      // 3f54: goto 3f6a
      // 3f59: aload 0
      // 3f5a: dup
      // 3f5b: getfield StageMaker.sx I
      // 3f5e: sipush 500
      // 3f61: iadd
      // 3f62: putfield StageMaker.sx I
      // 3f65: goto 3f6a
      // 3f6a: aload 0
      // 3f6b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 3f6e: aload 0
      // 3f6f: getfield StageMaker.sr [Ljava/awt/Image;
      // 3f72: iload 4
      // 3f74: aaload
      // 3f75: sipush 764
      // 3f78: sipush 264
      // 3f7b: aconst_null
      // 3f7c: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 3f7f: pop
      // 3f80: bipush 0
      // 3f81: istore 4
      // 3f83: aload 0
      // 3f84: getfield StageMaker.xm I
      // 3f87: sipush 616
      // 3f8a: if_icmpgt 3f92
      // 3f8d: goto 3fbd
      // 3f92: aload 0
      // 3f93: getfield StageMaker.xm I
      // 3f96: sipush 677
      // 3f99: if_icmplt 3fa1
      // 3f9c: goto 3fbd
      // 3fa1: aload 0
      // 3fa2: getfield StageMaker.ym I
      // 3fa5: bipush 30
      // 3fa7: if_icmpgt 3faf
      // 3faa: goto 3fbd
      // 3faf: aload 0
      // 3fb0: getfield StageMaker.ym I
      // 3fb3: bipush 61
      // 3fb5: if_icmpge 3fbd
      // 3fb8: goto 3fc9
      // 3fbd: aload 0
      // 3fbe: getfield StageMaker.zoomi Z
      // 3fc1: ifne 3fc9
      // 3fc4: goto 400c
      // 3fc9: bipush 1
      // 3fca: istore 4
      // 3fcc: aload 0
      // 3fcd: getfield StageMaker.mouses I
      // 3fd0: bipush 1
      // 3fd1: if_icmpne 3fd9
      // 3fd4: goto 3fe5
      // 3fd9: aload 0
      // 3fda: getfield StageMaker.zoomi Z
      // 3fdd: ifne 3fe5
      // 3fe0: goto 400c
      // 3fe5: aload 0
      // 3fe6: dup
      // 3fe7: getfield StageMaker.sy I
      // 3fea: sipush 500
      // 3fed: iadd
      // 3fee: putfield StageMaker.sy I
      // 3ff1: aload 0
      // 3ff2: getfield StageMaker.sy I
      // 3ff5: sipush -2500
      // 3ff8: if_icmpgt 4000
      // 3ffb: goto 400c
      // 4000: aload 0
      // 4001: sipush -2500
      // 4004: putfield StageMaker.sy I
      // 4007: goto 400c
      // 400c: aload 0
      // 400d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4010: aload 0
      // 4011: getfield StageMaker.zi [Ljava/awt/Image;
      // 4014: iload 4
      // 4016: aaload
      // 4017: sipush 616
      // 401a: bipush 30
      // 401c: aconst_null
      // 401d: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 4020: pop
      // 4021: bipush 0
      // 4022: istore 4
      // 4024: aload 0
      // 4025: getfield StageMaker.xm I
      // 4028: sipush 690
      // 402b: if_icmpgt 4033
      // 402e: goto 405e
      // 4033: aload 0
      // 4034: getfield StageMaker.xm I
      // 4037: sipush 751
      // 403a: if_icmplt 4042
      // 403d: goto 405e
      // 4042: aload 0
      // 4043: getfield StageMaker.ym I
      // 4046: bipush 30
      // 4048: if_icmpgt 4050
      // 404b: goto 405e
      // 4050: aload 0
      // 4051: getfield StageMaker.ym I
      // 4054: bipush 61
      // 4056: if_icmpge 405e
      // 4059: goto 406a
      // 405e: aload 0
      // 405f: getfield StageMaker.zoomo Z
      // 4062: ifne 406a
      // 4065: goto 40ad
      // 406a: bipush 1
      // 406b: istore 4
      // 406d: aload 0
      // 406e: getfield StageMaker.mouses I
      // 4071: bipush 1
      // 4072: if_icmpne 407a
      // 4075: goto 4086
      // 407a: aload 0
      // 407b: getfield StageMaker.zoomo Z
      // 407e: ifne 4086
      // 4081: goto 40ad
      // 4086: aload 0
      // 4087: dup
      // 4088: getfield StageMaker.sy I
      // 408b: sipush 500
      // 408e: isub
      // 408f: putfield StageMaker.sy I
      // 4092: aload 0
      // 4093: getfield StageMaker.sy I
      // 4096: ldc_w -55000
      // 4099: if_icmplt 40a1
      // 409c: goto 40ad
      // 40a1: aload 0
      // 40a2: ldc_w -55000
      // 40a5: putfield StageMaker.sy I
      // 40a8: goto 40ad
      // 40ad: aload 0
      // 40ae: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 40b1: aload 0
      // 40b2: getfield StageMaker.zo [Ljava/awt/Image;
      // 40b5: iload 4
      // 40b7: aaload
      // 40b8: sipush 690
      // 40bb: bipush 30
      // 40bd: aconst_null
      // 40be: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 40c1: pop
      // 40c2: aload 0
      // 40c3: getfield StageMaker.epart Z
      // 40c6: ifeq 40ce
      // 40c9: goto 40da
      // 40ce: aload 0
      // 40cf: getfield StageMaker.arrng Z
      // 40d2: ifne 40da
      // 40d5: goto 40f5
      // 40da: aload 0
      // 40db: getfield StageMaker.sy I
      // 40de: ldc_w -36000
      // 40e1: if_icmplt 40e9
      // 40e4: goto 40f5
      // 40e9: aload 0
      // 40ea: ldc_w -36000
      // 40ed: putfield StageMaker.sy I
      // 40f0: goto 40f5
      // 40f5: aload 0
      // 40f6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 40f9: new java/awt/Font
      // 40fc: dup
      // 40fd: ldc_w "Arial"
      // 4100: bipush 1
      // 4101: bipush 11
      // 4103: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4106: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4109: aload 0
      // 410a: aload 0
      // 410b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 410e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4111: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4114: aload 0
      // 4115: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4118: new java/awt/Color
      // 411b: dup
      // 411c: bipush 0
      // 411d: bipush 0
      // 411e: bipush 0
      // 411f: invokespecial java/awt/Color.<init> (III)V
      // 4122: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4125: aload 0
      // 4126: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4129: ldc_w "Part Selection"
      // 412c: bipush 11
      // 412e: bipush 47
      // 4130: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4133: aload 0
      // 4134: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4137: new java/awt/Font
      // 413a: dup
      // 413b: ldc_w "Arial"
      // 413e: bipush 1
      // 413f: bipush 13
      // 4141: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4144: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4147: aload 0
      // 4148: aload 0
      // 4149: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 414c: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 414f: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4152: aload 0
      // 4153: getfield StageMaker.ptyp LSmenu;
      // 4156: bipush 10
      // 4158: bipush 50
      // 415a: invokevirtual Smenu.move (II)V
      // 415d: aload 0
      // 415e: getfield StageMaker.ptyp LSmenu;
      // 4161: invokevirtual Smenu.isShowing ()Z
      // 4164: ifeq 416c
      // 4167: goto 4183
      // 416c: aload 0
      // 416d: getfield StageMaker.ptyp LSmenu;
      // 4170: invokevirtual Smenu.show ()V
      // 4173: aload 0
      // 4174: getfield StageMaker.ptyp LSmenu;
      // 4177: aload 0
      // 4178: getfield StageMaker.sptyp I
      // 417b: invokevirtual Smenu.select (I)V
      // 417e: goto 4183
      // 4183: aload 0
      // 4184: getfield StageMaker.sptyp I
      // 4187: aload 0
      // 4188: getfield StageMaker.ptyp LSmenu;
      // 418b: invokevirtual Smenu.getSelectedIndex ()I
      // 418e: if_icmpne 4196
      // 4191: goto 423b
      // 4196: aload 0
      // 4197: aload 0
      // 4198: getfield StageMaker.ptyp LSmenu;
      // 419b: invokevirtual Smenu.getSelectedIndex ()I
      // 419e: putfield StageMaker.sptyp I
      // 41a1: aload 0
      // 41a2: getfield StageMaker.sptyp I
      // 41a5: ifeq 41ad
      // 41a8: goto 41bd
      // 41ad: aload 0
      // 41ae: invokevirtual StageMaker.partroads ()V
      // 41b1: aload 0
      // 41b2: getfield StageMaker.part LSmenu;
      // 41b5: invokevirtual Smenu.show ()V
      // 41b8: goto 41bd
      // 41bd: aload 0
      // 41be: getfield StageMaker.sptyp I
      // 41c1: bipush 1
      // 41c2: if_icmpeq 41ca
      // 41c5: goto 41da
      // 41ca: aload 0
      // 41cb: invokevirtual StageMaker.partramps ()V
      // 41ce: aload 0
      // 41cf: getfield StageMaker.part LSmenu;
      // 41d2: invokevirtual Smenu.show ()V
      // 41d5: goto 41da
      // 41da: aload 0
      // 41db: getfield StageMaker.sptyp I
      // 41de: bipush 2
      // 41df: if_icmpeq 41e7
      // 41e2: goto 41f7
      // 41e7: aload 0
      // 41e8: invokevirtual StageMaker.partobst ()V
      // 41eb: aload 0
      // 41ec: getfield StageMaker.part LSmenu;
      // 41ef: invokevirtual Smenu.show ()V
      // 41f2: goto 41f7
      // 41f7: aload 0
      // 41f8: getfield StageMaker.sptyp I
      // 41fb: bipush 5
      // 41fc: if_icmpeq 4204
      // 41ff: goto 4214
      // 4204: aload 0
      // 4205: invokevirtual StageMaker.partrees ()V
      // 4208: aload 0
      // 4209: getfield StageMaker.part LSmenu;
      // 420c: invokevirtual Smenu.show ()V
      // 420f: goto 4214
      // 4214: aload 0
      // 4215: bipush 0
      // 4216: putfield StageMaker.spart I
      // 4219: aload 0
      // 421a: getfield StageMaker.part LSmenu;
      // 421d: aload 0
      // 421e: getfield StageMaker.spart I
      // 4221: invokevirtual Smenu.select (I)V
      // 4224: aload 0
      // 4225: invokevirtual StageMaker.requestFocus ()V
      // 4228: aload 0
      // 4229: getfield StageMaker.fixh Ljava/awt/TextField;
      // 422c: ldc "2000"
      // 422e: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 4231: aload 0
      // 4232: bipush 0
      // 4233: putfield StageMaker.focuson Z
      // 4236: goto 423b
      // 423b: aload 0
      // 423c: getfield StageMaker.part LSmenu;
      // 423f: bipush 10
      // 4241: bipush 80
      // 4243: invokevirtual Smenu.move (II)V
      // 4246: aload 0
      // 4247: getfield StageMaker.part LSmenu;
      // 424a: sipush 200
      // 424d: bipush 21
      // 424f: invokevirtual Smenu.setSize (II)V
      // 4252: aload 0
      // 4253: getfield StageMaker.sptyp I
      // 4256: ifne 425e
      // 4259: goto 4285
      // 425e: aload 0
      // 425f: getfield StageMaker.sptyp I
      // 4262: bipush 1
      // 4263: if_icmpne 426b
      // 4266: goto 4285
      // 426b: aload 0
      // 426c: getfield StageMaker.sptyp I
      // 426f: bipush 2
      // 4270: if_icmpne 4278
      // 4273: goto 4285
      // 4278: aload 0
      // 4279: getfield StageMaker.sptyp I
      // 427c: bipush 5
      // 427d: if_icmpeq 4285
      // 4280: goto 42ab
      // 4285: aload 0
      // 4286: getfield StageMaker.part LSmenu;
      // 4289: invokevirtual Smenu.isShowing ()Z
      // 428c: ifeq 4294
      // 428f: goto 42c6
      // 4294: aload 0
      // 4295: getfield StageMaker.part LSmenu;
      // 4298: invokevirtual Smenu.show ()V
      // 429b: aload 0
      // 429c: getfield StageMaker.part LSmenu;
      // 429f: aload 0
      // 42a0: getfield StageMaker.spart I
      // 42a3: invokevirtual Smenu.select (I)V
      // 42a6: goto 42c6
      // 42ab: aload 0
      // 42ac: getfield StageMaker.part LSmenu;
      // 42af: invokevirtual Smenu.isShowing ()Z
      // 42b2: ifne 42ba
      // 42b5: goto 42c6
      // 42ba: aload 0
      // 42bb: getfield StageMaker.part LSmenu;
      // 42be: invokevirtual Smenu.hide ()V
      // 42c1: goto 42c6
      // 42c6: aload 0
      // 42c7: getfield StageMaker.spart I
      // 42ca: aload 0
      // 42cb: getfield StageMaker.part LSmenu;
      // 42ce: invokevirtual Smenu.getSelectedIndex ()I
      // 42d1: if_icmpne 42d9
      // 42d4: goto 42ee
      // 42d9: aload 0
      // 42da: aload 0
      // 42db: getfield StageMaker.part LSmenu;
      // 42de: invokevirtual Smenu.getSelectedIndex ()I
      // 42e1: putfield StageMaker.spart I
      // 42e4: aload 0
      // 42e5: bipush 0
      // 42e6: putfield StageMaker.focuson Z
      // 42e9: goto 42ee
      // 42ee: aload 0
      // 42ef: getfield StageMaker.sptyp I
      // 42f2: bipush 3
      // 42f3: if_icmpeq 42fb
      // 42f6: goto 431b
      // 42fb: aload 0
      // 42fc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 42ff: ldc_w "Checkpoint"
      // 4302: bipush 110
      // 4304: aload 0
      // 4305: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4308: ldc_w "Checkpoint"
      // 430b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 430e: bipush 2
      // 430f: idiv
      // 4310: isub
      // 4311: bipush 120
      // 4313: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4316: goto 431b
      // 431b: aload 0
      // 431c: getfield StageMaker.sptyp I
      // 431f: bipush 4
      // 4320: if_icmpeq 4328
      // 4323: goto 4348
      // 4328: aload 0
      // 4329: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 432c: ldc_w "Fixing Hoop"
      // 432f: bipush 110
      // 4331: aload 0
      // 4332: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4335: ldc_w "Fixing Hoop"
      // 4338: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 433b: bipush 2
      // 433c: idiv
      // 433d: isub
      // 433e: bipush 120
      // 4340: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4343: goto 4348
      // 4348: aload 0
      // 4349: getfield StageMaker.lsp I
      // 434c: aload 0
      // 434d: getfield StageMaker.sp I
      // 4350: if_icmpne 4358
      // 4353: goto 438e
      // 4358: aload 0
      // 4359: bipush 3
      // 435a: putfield StageMaker.seq I
      // 435d: aload 0
      // 435e: getfield StageMaker.bco [LContO;
      // 4361: aload 0
      // 4362: getfield StageMaker.sp I
      // 4365: aaload
      // 4366: bipush 0
      // 4367: putfield ContO.xy I
      // 436a: aload 0
      // 436b: getfield StageMaker.bco [LContO;
      // 436e: aload 0
      // 436f: getfield StageMaker.sp I
      // 4372: aaload
      // 4373: bipush 0
      // 4374: putfield ContO.zy I
      // 4377: aload 0
      // 4378: aload 0
      // 4379: getfield StageMaker.sp I
      // 437c: putfield StageMaker.lsp I
      // 437f: aload 0
      // 4380: bipush 0
      // 4381: putfield StageMaker.epart Z
      // 4384: aload 0
      // 4385: bipush 0
      // 4386: putfield StageMaker.arrng Z
      // 4389: goto 438e
      // 438e: aload 0
      // 438f: getfield StageMaker.xm I
      // 4392: bipush 10
      // 4394: if_icmpgt 439c
      // 4397: goto 4444
      // 439c: aload 0
      // 439d: getfield StageMaker.xm I
      // 43a0: sipush 210
      // 43a3: if_icmplt 43ab
      // 43a6: goto 4444
      // 43ab: aload 0
      // 43ac: getfield StageMaker.ym I
      // 43af: sipush 130
      // 43b2: if_icmpgt 43ba
      // 43b5: goto 4444
      // 43ba: aload 0
      // 43bb: getfield StageMaker.ym I
      // 43be: sipush 334
      // 43c1: if_icmplt 43c9
      // 43c4: goto 4444
      // 43c9: aload 0
      // 43ca: getfield StageMaker.seq I
      // 43cd: bipush 3
      // 43ce: if_icmpge 43d6
      // 43d1: goto 4423
      // 43d6: aload 0
      // 43d7: getfield StageMaker.seq I
      // 43da: bipush 20
      // 43dc: if_icmpne 43e4
      // 43df: goto 43f0
      // 43e4: aload 0
      // 43e5: getfield StageMaker.seqn Z
      // 43e8: ifeq 43f0
      // 43eb: goto 4414
      // 43f0: aload 0
      // 43f1: bipush 0
      // 43f2: putfield StageMaker.seq I
      // 43f5: aload 0
      // 43f6: getfield StageMaker.bco [LContO;
      // 43f9: aload 0
      // 43fa: getfield StageMaker.sp I
      // 43fd: aaload
      // 43fe: bipush 0
      // 43ff: putfield ContO.xy I
      // 4402: aload 0
      // 4403: getfield StageMaker.bco [LContO;
      // 4406: aload 0
      // 4407: getfield StageMaker.sp I
      // 440a: aaload
      // 440b: bipush 0
      // 440c: putfield ContO.zy I
      // 440f: goto 4423
      // 4414: aload 0
      // 4415: dup
      // 4416: getfield StageMaker.seq I
      // 4419: bipush 1
      // 441a: iadd
      // 441b: putfield StageMaker.seq I
      // 441e: goto 4423
      // 4423: aload 0
      // 4424: bipush 1
      // 4425: putfield StageMaker.seqn Z
      // 4428: aload 0
      // 4429: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 442c: new java/awt/Color
      // 442f: dup
      // 4430: sipush 210
      // 4433: sipush 210
      // 4436: sipush 210
      // 4439: invokespecial java/awt/Color.<init> (III)V
      // 443c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 443f: goto 4460
      // 4444: aload 0
      // 4445: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4448: new java/awt/Color
      // 444b: dup
      // 444c: sipush 200
      // 444f: sipush 200
      // 4452: sipush 200
      // 4455: invokespecial java/awt/Color.<init> (III)V
      // 4458: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 445b: aload 0
      // 445c: bipush 0
      // 445d: putfield StageMaker.seqn Z
      // 4460: aload 0
      // 4461: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4464: bipush 10
      // 4466: sipush 130
      // 4469: sipush 200
      // 446c: sipush 200
      // 446f: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4472: aload 0
      // 4473: getfield StageMaker.sp I
      // 4476: bipush 30
      // 4478: if_icmpne 4480
      // 447b: goto 449c
      // 4480: aload 0
      // 4481: getfield StageMaker.sp I
      // 4484: bipush 32
      // 4486: if_icmpne 448e
      // 4489: goto 449c
      // 448e: aload 0
      // 448f: getfield StageMaker.sp I
      // 4492: bipush 54
      // 4494: if_icmpeq 449c
      // 4497: goto 4546
      // 449c: aload 0
      // 449d: ldc_w "  Rearrange Checkpoints  >  "
      // 44a0: bipush 110
      // 44a2: sipush 315
      // 44a5: bipush 2
      // 44a6: bipush 1
      // 44a7: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 44aa: ifne 44b2
      // 44ad: goto 4546
      // 44b2: aload 0
      // 44b3: bipush 0
      // 44b4: putfield StageMaker.mouses I
      // 44b7: aload 0
      // 44b8: bipush 0
      // 44b9: putfield StageMaker.epart Z
      // 44bc: aload 0
      // 44bd: getfield StageMaker.arrng Z
      // 44c0: ifeq 44c8
      // 44c3: goto 453c
      // 44c8: aload 0
      // 44c9: bipush 0
      // 44ca: putfield StageMaker.arrcnt I
      // 44cd: bipush 0
      // 44ce: istore 5
      // 44d0: iload 5
      // 44d2: aload 0
      // 44d3: getfield StageMaker.nob I
      // 44d6: if_icmplt 44de
      // 44d9: goto 4532
      // 44de: aload 0
      // 44df: getfield StageMaker.co [LContO;
      // 44e2: iload 5
      // 44e4: aaload
      // 44e5: getfield ContO.colok I
      // 44e8: bipush 30
      // 44ea: if_icmpne 44f2
      // 44ed: goto 451a
      // 44f2: aload 0
      // 44f3: getfield StageMaker.co [LContO;
      // 44f6: iload 5
      // 44f8: aaload
      // 44f9: getfield ContO.colok I
      // 44fc: bipush 32
      // 44fe: if_icmpne 4506
      // 4501: goto 451a
      // 4506: aload 0
      // 4507: getfield StageMaker.co [LContO;
      // 450a: iload 5
      // 450c: aaload
      // 450d: getfield ContO.colok I
      // 4510: bipush 54
      // 4512: if_icmpeq 451a
      // 4515: goto 452a
      // 451a: aload 0
      // 451b: getfield StageMaker.co [LContO;
      // 451e: iload 5
      // 4520: aaload
      // 4521: bipush 0
      // 4522: putfield ContO.errd Z
      // 4525: goto 452a
      // 452a: iinc 5 1
      // 452d: goto 44d0
      // 4532: aload 0
      // 4533: bipush 1
      // 4534: putfield StageMaker.arrng Z
      // 4537: goto 4546
      // 453c: aload 0
      // 453d: bipush 0
      // 453e: putfield StageMaker.arrng Z
      // 4541: goto 4546
      // 4546: aload 0
      // 4547: getfield StageMaker.seqn Z
      // 454a: ifne 4552
      // 454d: goto 46c1
      // 4552: aload 0
      // 4553: getfield StageMaker.mouses I
      // 4556: bipush -1
      // 4557: if_icmpeq 455f
      // 455a: goto 46c1
      // 455f: aload 0
      // 4560: getfield StageMaker.sp I
      // 4563: bipush 66
      // 4565: if_icmpne 456d
      // 4568: goto 4691
      // 456d: bipush 0
      // 456e: istore 5
      // 4570: aload 0
      // 4571: getfield StageMaker.rot I
      // 4574: ifeq 457c
      // 4577: goto 4594
      // 457c: iload 5
      // 457e: ifeq 4586
      // 4581: goto 4594
      // 4586: aload 0
      // 4587: bipush 90
      // 4589: putfield StageMaker.rot I
      // 458c: bipush 1
      // 458d: istore 5
      // 458f: goto 4594
      // 4594: aload 0
      // 4595: getfield StageMaker.rot I
      // 4598: bipush 90
      // 459a: if_icmpeq 45a2
      // 459d: goto 45bb
      // 45a2: iload 5
      // 45a4: ifeq 45ac
      // 45a7: goto 45bb
      // 45ac: aload 0
      // 45ad: sipush 180
      // 45b0: putfield StageMaker.rot I
      // 45b3: bipush 1
      // 45b4: istore 5
      // 45b6: goto 45bb
      // 45bb: aload 0
      // 45bc: getfield StageMaker.rot I
      // 45bf: sipush 180
      // 45c2: if_icmpeq 45ca
      // 45c5: goto 45e2
      // 45ca: iload 5
      // 45cc: ifeq 45d4
      // 45cf: goto 45e2
      // 45d4: aload 0
      // 45d5: bipush -90
      // 45d7: putfield StageMaker.rot I
      // 45da: bipush 1
      // 45db: istore 5
      // 45dd: goto 45e2
      // 45e2: aload 0
      // 45e3: getfield StageMaker.rot I
      // 45e6: bipush -90
      // 45e8: if_icmpeq 45f0
      // 45eb: goto 4607
      // 45f0: iload 5
      // 45f2: ifeq 45fa
      // 45f5: goto 4607
      // 45fa: aload 0
      // 45fb: bipush 0
      // 45fc: putfield StageMaker.rot I
      // 45ff: bipush 1
      // 4600: istore 5
      // 4602: goto 4607
      // 4607: aload 0
      // 4608: getfield StageMaker.sp I
      // 460b: bipush 30
      // 460d: if_icmpne 4615
      // 4610: goto 4631
      // 4615: aload 0
      // 4616: getfield StageMaker.sp I
      // 4619: bipush 31
      // 461b: if_icmpne 4623
      // 461e: goto 4631
      // 4623: aload 0
      // 4624: getfield StageMaker.sp I
      // 4627: bipush 32
      // 4629: if_icmpeq 4631
      // 462c: goto 4663
      // 4631: aload 0
      // 4632: getfield StageMaker.rot I
      // 4635: bipush -90
      // 4637: if_icmpeq 463f
      // 463a: goto 464a
      // 463f: aload 0
      // 4640: bipush 90
      // 4642: putfield StageMaker.rot I
      // 4645: goto 464a
      // 464a: aload 0
      // 464b: getfield StageMaker.rot I
      // 464e: sipush 180
      // 4651: if_icmpeq 4659
      // 4654: goto 4663
      // 4659: aload 0
      // 465a: bipush 0
      // 465b: putfield StageMaker.rot I
      // 465e: goto 4663
      // 4663: aload 0
      // 4664: bipush 5
      // 4665: putfield StageMaker.seq I
      // 4668: aload 0
      // 4669: getfield StageMaker.bco [LContO;
      // 466c: aload 0
      // 466d: getfield StageMaker.sp I
      // 4670: aaload
      // 4671: bipush 0
      // 4672: putfield ContO.xy I
      // 4675: aload 0
      // 4676: getfield StageMaker.bco [LContO;
      // 4679: aload 0
      // 467a: getfield StageMaker.sp I
      // 467d: aaload
      // 467e: bipush 0
      // 467f: putfield ContO.zy I
      // 4682: aload 0
      // 4683: bipush 0
      // 4684: putfield StageMaker.epart Z
      // 4687: aload 0
      // 4688: bipush 0
      // 4689: putfield StageMaker.arrng Z
      // 468c: goto 46c1
      // 4691: aload 0
      // 4692: bipush 0
      // 4693: putfield StageMaker.pgen Z
      // 4696: aload 0
      // 4697: ldc2_w 2
      // 469a: invokestatic java/lang/Math.random ()D
      // 469d: ldc2_w 4.0
      // 46a0: dmul
      // 46a1: invokestatic java/lang/Math.round (D)J
      // 46a4: ladd
      // 46a5: l2f
      // 46a6: putfield StageMaker.pwd F
      // 46a9: aload 0
      // 46aa: ldc2_w 2
      // 46ad: invokestatic java/lang/Math.random ()D
      // 46b0: ldc2_w 4.0
      // 46b3: dmul
      // 46b4: invokestatic java/lang/Math.round (D)J
      // 46b7: ladd
      // 46b8: l2f
      // 46b9: putfield StageMaker.phd F
      // 46bc: goto 46c1
      // 46c1: aload 0
      // 46c2: getfield StageMaker.sp I
      // 46c5: bipush 31
      // 46c7: if_icmpeq 46cf
      // 46ca: goto 4801
      // 46cf: aload 0
      // 46d0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 46d3: new java/awt/Font
      // 46d6: dup
      // 46d7: ldc_w "Arial"
      // 46da: bipush 1
      // 46db: bipush 12
      // 46dd: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 46e0: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 46e3: aload 0
      // 46e4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 46e7: new java/awt/Color
      // 46ea: dup
      // 46eb: bipush 0
      // 46ec: bipush 0
      // 46ed: bipush 0
      // 46ee: invokespecial java/awt/Color.<init> (III)V
      // 46f1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 46f4: aload 0
      // 46f5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 46f8: ldc_w "Height:"
      // 46fb: bipush 62
      // 46fd: sipush 280
      // 4700: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4703: aload 0
      // 4704: aload 0
      // 4705: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4708: bipush 107
      // 470a: sipush 266
      // 470d: bipush 50
      // 470f: bipush 20
      // 4711: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // 4714: aload 0
      // 4715: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4718: invokevirtual java/awt/TextField.hasFocus ()Z
      // 471b: ifne 4723
      // 471e: goto 472d
      // 4723: aload 0
      // 4724: bipush 0
      // 4725: putfield StageMaker.focuson Z
      // 4728: goto 472d
      // 472d: aload 0
      // 472e: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4731: invokevirtual java/awt/TextField.isShowing ()Z
      // 4734: ifeq 473c
      // 4737: goto 4748
      // 473c: aload 0
      // 473d: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4740: invokevirtual java/awt/TextField.show ()V
      // 4743: goto 4748
      // 4748: aload 0
      // 4749: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 474c: new java/awt/Font
      // 474f: dup
      // 4750: ldc_w "Arial"
      // 4753: bipush 0
      // 4754: bipush 11
      // 4756: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4759: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 475c: aload 0
      // 475d: aload 0
      // 475e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4761: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4764: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4767: aload 0
      // 4768: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 476b: ldc_w "( Height off the ground... )"
      // 476e: bipush 110
      // 4770: aload 0
      // 4771: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 4774: ldc_w "( Height off the ground... )"
      // 4777: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 477a: bipush 2
      // 477b: idiv
      // 477c: isub
      // 477d: sipush 300
      // 4780: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4783: aload 0
      // 4784: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4787: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 478a: ldc ""
      // 478c: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 478f: ifne 4797
      // 4792: goto 47af
      // 4797: aload 0
      // 4798: getfield StageMaker.fixh Ljava/awt/TextField;
      // 479b: ldc_w "0"
      // 479e: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 47a1: aload 0
      // 47a2: getfield StageMaker.fixh Ljava/awt/TextField;
      // 47a5: bipush 0
      // 47a6: bipush 0
      // 47a7: invokevirtual java/awt/TextField.select (II)V
      // 47aa: goto 47af
      // 47af: aload 0
      // 47b0: aload 0
      // 47b1: getfield StageMaker.fixh Ljava/awt/TextField;
      // 47b4: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 47b7: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 47ba: invokevirtual java/lang/Integer.intValue ()I
      // 47bd: putfield StageMaker.hf I
      // 47c0: aload 0
      // 47c1: getfield StageMaker.hf I
      // 47c4: sipush 8000
      // 47c7: if_icmpgt 47cf
      // 47ca: goto 47e5
      // 47cf: aload 0
      // 47d0: sipush 8000
      // 47d3: putfield StageMaker.hf I
      // 47d6: aload 0
      // 47d7: getfield StageMaker.fixh Ljava/awt/TextField;
      // 47da: ldc_w "8000"
      // 47dd: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 47e0: goto 47e5
      // 47e5: goto 481c
      // 47ea: astore 5
      // 47ec: aload 0
      // 47ed: sipush 2000
      // 47f0: putfield StageMaker.hf I
      // 47f3: aload 0
      // 47f4: getfield StageMaker.fixh Ljava/awt/TextField;
      // 47f7: ldc "2000"
      // 47f9: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 47fc: goto 481c
      // 4801: aload 0
      // 4802: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4805: invokevirtual java/awt/TextField.isShowing ()Z
      // 4808: ifne 4810
      // 480b: goto 481c
      // 4810: aload 0
      // 4811: getfield StageMaker.fixh Ljava/awt/TextField;
      // 4814: invokevirtual java/awt/TextField.hide ()V
      // 4817: goto 481c
      // 481c: aload 0
      // 481d: getfield StageMaker.m LMedium;
      // 4820: bipush 2
      // 4821: putfield Medium.trk I
      // 4824: aload 0
      // 4825: getfield StageMaker.m LMedium;
      // 4828: bipush 90
      // 482a: putfield Medium.zy I
      // 482d: aload 0
      // 482e: getfield StageMaker.m LMedium;
      // 4831: bipush 0
      // 4832: putfield Medium.xz I
      // 4835: aload 0
      // 4836: getfield StageMaker.m LMedium;
      // 4839: bipush 10
      // 483b: putfield Medium.iw I
      // 483e: aload 0
      // 483f: getfield StageMaker.m LMedium;
      // 4842: sipush 210
      // 4845: putfield Medium.w I
      // 4848: aload 0
      // 4849: getfield StageMaker.m LMedium;
      // 484c: sipush 130
      // 484f: putfield Medium.ih I
      // 4852: aload 0
      // 4853: getfield StageMaker.m LMedium;
      // 4856: sipush 330
      // 4859: putfield Medium.h I
      // 485c: aload 0
      // 485d: getfield StageMaker.m LMedium;
      // 4860: bipush 110
      // 4862: putfield Medium.cx I
      // 4865: aload 0
      // 4866: getfield StageMaker.m LMedium;
      // 4869: sipush 230
      // 486c: putfield Medium.cy I
      // 486f: aload 0
      // 4870: getfield StageMaker.m LMedium;
      // 4873: bipush -110
      // 4875: putfield Medium.x I
      // 4878: aload 0
      // 4879: getfield StageMaker.m LMedium;
      // 487c: sipush -230
      // 487f: putfield Medium.z I
      // 4882: aload 0
      // 4883: getfield StageMaker.m LMedium;
      // 4886: sipush -15000
      // 4889: putfield Medium.y I
      // 488c: aload 0
      // 488d: getfield StageMaker.sptyp I
      // 4890: bipush 1
      // 4891: if_icmpeq 4899
      // 4894: goto 48e0
      // 4899: aload 0
      // 489a: getfield StageMaker.sp I
      // 489d: bipush 20
      // 489f: if_icmpne 48a7
      // 48a2: goto 48e0
      // 48a7: aload 0
      // 48a8: getfield StageMaker.sp I
      // 48ab: bipush 21
      // 48ad: if_icmpne 48b5
      // 48b0: goto 48e0
      // 48b5: aload 0
      // 48b6: getfield StageMaker.sp I
      // 48b9: bipush 43
      // 48bb: if_icmpne 48c3
      // 48be: goto 48e0
      // 48c3: aload 0
      // 48c4: getfield StageMaker.sp I
      // 48c7: bipush 45
      // 48c9: if_icmpne 48d1
      // 48cc: goto 48e0
      // 48d1: aload 0
      // 48d2: getfield StageMaker.m LMedium;
      // 48d5: sipush -10000
      // 48d8: putfield Medium.y I
      // 48db: goto 48e0
      // 48e0: aload 0
      // 48e1: getfield StageMaker.sptyp I
      // 48e4: bipush 2
      // 48e5: if_icmpeq 48ed
      // 48e8: goto 490a
      // 48ed: aload 0
      // 48ee: getfield StageMaker.sp I
      // 48f1: bipush 41
      // 48f3: if_icmpne 48fb
      // 48f6: goto 490a
      // 48fb: aload 0
      // 48fc: getfield StageMaker.m LMedium;
      // 48ff: sipush -7600
      // 4902: putfield Medium.y I
      // 4905: goto 490a
      // 490a: aload 0
      // 490b: getfield StageMaker.sptyp I
      // 490e: bipush 3
      // 490f: if_icmpne 4917
      // 4912: goto 4924
      // 4917: aload 0
      // 4918: getfield StageMaker.sptyp I
      // 491b: bipush 4
      // 491c: if_icmpeq 4924
      // 491f: goto 4933
      // 4924: aload 0
      // 4925: getfield StageMaker.m LMedium;
      // 4928: sipush -5000
      // 492b: putfield Medium.y I
      // 492e: goto 4933
      // 4933: aload 0
      // 4934: getfield StageMaker.sptyp I
      // 4937: bipush 5
      // 4938: if_icmpeq 4940
      // 493b: goto 4959
      // 4940: aload 0
      // 4941: getfield StageMaker.m LMedium;
      // 4944: sipush -3000
      // 4947: putfield Medium.y I
      // 494a: aload 0
      // 494b: getfield StageMaker.m LMedium;
      // 494e: sipush 150
      // 4951: putfield Medium.z I
      // 4954: goto 4959
      // 4959: aload 0
      // 495a: getfield StageMaker.sptyp I
      // 495d: bipush 6
      // 495f: if_icmpeq 4967
      // 4962: goto 4976
      // 4967: aload 0
      // 4968: getfield StageMaker.m LMedium;
      // 496b: sipush -7600
      // 496e: putfield Medium.y I
      // 4971: goto 4976
      // 4976: aload 0
      // 4977: getfield StageMaker.sp I
      // 497a: bipush 31
      // 497c: if_icmpeq 4984
      // 497f: goto 49be
      // 4984: aload 0
      // 4985: getfield StageMaker.m LMedium;
      // 4988: sipush -500
      // 498b: putfield Medium.z I
      // 498e: aload 0
      // 498f: getfield StageMaker.rot I
      // 4992: ifne 499a
      // 4995: goto 49ac
      // 499a: aload 0
      // 499b: getfield StageMaker.bco [LContO;
      // 499e: aload 0
      // 499f: getfield StageMaker.sp I
      // 49a2: aaload
      // 49a3: bipush 1
      // 49a4: putfield ContO.roted Z
      // 49a7: goto 49be
      // 49ac: aload 0
      // 49ad: getfield StageMaker.bco [LContO;
      // 49b0: aload 0
      // 49b1: getfield StageMaker.sp I
      // 49b4: aaload
      // 49b5: bipush 0
      // 49b6: putfield ContO.roted Z
      // 49b9: goto 49be
      // 49be: aload 0
      // 49bf: getfield StageMaker.bco [LContO;
      // 49c2: aload 0
      // 49c3: getfield StageMaker.sp I
      // 49c6: aaload
      // 49c7: bipush 0
      // 49c8: putfield ContO.x I
      // 49cb: aload 0
      // 49cc: getfield StageMaker.bco [LContO;
      // 49cf: aload 0
      // 49d0: getfield StageMaker.sp I
      // 49d3: aaload
      // 49d4: bipush 0
      // 49d5: putfield ContO.y I
      // 49d8: aload 0
      // 49d9: getfield StageMaker.bco [LContO;
      // 49dc: aload 0
      // 49dd: getfield StageMaker.sp I
      // 49e0: aaload
      // 49e1: bipush 0
      // 49e2: putfield ContO.z I
      // 49e5: aload 0
      // 49e6: getfield StageMaker.bco [LContO;
      // 49e9: aload 0
      // 49ea: getfield StageMaker.sp I
      // 49ed: aaload
      // 49ee: aload 0
      // 49ef: getfield StageMaker.rot I
      // 49f2: aload 0
      // 49f3: getfield StageMaker.adrot I
      // 49f6: iadd
      // 49f7: putfield ContO.xz I
      // 49fa: aload 0
      // 49fb: getfield StageMaker.bco [LContO;
      // 49fe: aload 0
      // 49ff: getfield StageMaker.sp I
      // 4a02: aaload
      // 4a03: aload 0
      // 4a04: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 4a07: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 4a0a: bipush 1
      // 4a0b: istore 5
      // 4a0d: aload 0
      // 4a0e: getfield StageMaker.sptyp I
      // 4a11: ifne 4a19
      // 4a14: goto 4a26
      // 4a19: aload 0
      // 4a1a: getfield StageMaker.sptyp I
      // 4a1d: bipush 1
      // 4a1e: if_icmpeq 4a26
      // 4a21: goto 4b65
      // 4a26: aload 0
      // 4a27: getfield StageMaker.sp I
      // 4a2a: bipush 26
      // 4a2c: if_icmpne 4a34
      // 4a2f: goto 4a64
      // 4a34: aload 0
      // 4a35: getfield StageMaker.sp I
      // 4a38: bipush 20
      // 4a3a: if_icmpne 4a42
      // 4a3d: goto 4a64
      // 4a42: aload 0
      // 4a43: getfield StageMaker.rot I
      // 4a46: bipush -90
      // 4a48: if_icmpne 4a50
      // 4a4b: goto 4a5c
      // 4a50: aload 0
      // 4a51: getfield StageMaker.rot I
      // 4a54: ifeq 4a5c
      // 4a57: goto 4aca
      // 4a5c: bipush -1
      // 4a5d: istore 5
      // 4a5f: goto 4aca
      // 4a64: aload 0
      // 4a65: getfield StageMaker.sp I
      // 4a68: bipush 26
      // 4a6a: if_icmpeq 4a72
      // 4a6d: goto 4a97
      // 4a72: aload 0
      // 4a73: getfield StageMaker.rot I
      // 4a76: bipush -90
      // 4a78: if_icmpne 4a80
      // 4a7b: goto 4a8f
      // 4a80: aload 0
      // 4a81: getfield StageMaker.rot I
      // 4a84: sipush 180
      // 4a87: if_icmpeq 4a8f
      // 4a8a: goto 4a97
      // 4a8f: bipush -1
      // 4a90: istore 5
      // 4a92: goto 4a97
      // 4a97: aload 0
      // 4a98: getfield StageMaker.sp I
      // 4a9b: bipush 20
      // 4a9d: if_icmpeq 4aa5
      // 4aa0: goto 4aca
      // 4aa5: aload 0
      // 4aa6: getfield StageMaker.rot I
      // 4aa9: bipush 90
      // 4aab: if_icmpne 4ab3
      // 4aae: goto 4ac2
      // 4ab3: aload 0
      // 4ab4: getfield StageMaker.rot I
      // 4ab7: sipush 180
      // 4aba: if_icmpeq 4ac2
      // 4abd: goto 4aca
      // 4ac2: bipush -1
      // 4ac3: istore 5
      // 4ac5: goto 4aca
      // 4aca: aload 0
      // 4acb: getfield StageMaker.seq I
      // 4ace: bipush 2
      // 4acf: if_icmpeq 4ad7
      // 4ad2: goto 4b0a
      // 4ad7: aload 0
      // 4ad8: getfield StageMaker.bco [LContO;
      // 4adb: aload 0
      // 4adc: getfield StageMaker.sp I
      // 4adf: aaload
      // 4ae0: dup
      // 4ae1: getfield ContO.xy I
      // 4ae4: bipush 5
      // 4ae5: iload 5
      // 4ae7: imul
      // 4ae8: isub
      // 4ae9: putfield ContO.xy I
      // 4aec: aload 0
      // 4aed: getfield StageMaker.bco [LContO;
      // 4af0: aload 0
      // 4af1: getfield StageMaker.sp I
      // 4af4: aaload
      // 4af5: getfield ContO.xy I
      // 4af8: ifeq 4b00
      // 4afb: goto 4b0a
      // 4b00: aload 0
      // 4b01: bipush 3
      // 4b02: putfield StageMaker.seq I
      // 4b05: goto 4b0a
      // 4b0a: aload 0
      // 4b0b: getfield StageMaker.seq I
      // 4b0e: bipush 1
      // 4b0f: if_icmpeq 4b17
      // 4b12: goto 4b21
      // 4b17: aload 0
      // 4b18: bipush 2
      // 4b19: putfield StageMaker.seq I
      // 4b1c: goto 4b21
      // 4b21: aload 0
      // 4b22: getfield StageMaker.seq I
      // 4b25: ifeq 4b2d
      // 4b28: goto 4b65
      // 4b2d: aload 0
      // 4b2e: getfield StageMaker.bco [LContO;
      // 4b31: aload 0
      // 4b32: getfield StageMaker.sp I
      // 4b35: aaload
      // 4b36: dup
      // 4b37: getfield ContO.xy I
      // 4b3a: bipush 5
      // 4b3b: iload 5
      // 4b3d: imul
      // 4b3e: iadd
      // 4b3f: putfield ContO.xy I
      // 4b42: aload 0
      // 4b43: getfield StageMaker.bco [LContO;
      // 4b46: aload 0
      // 4b47: getfield StageMaker.sp I
      // 4b4a: aaload
      // 4b4b: getfield ContO.xy I
      // 4b4e: bipush 85
      // 4b50: iload 5
      // 4b52: imul
      // 4b53: if_icmpeq 4b5b
      // 4b56: goto 4b65
      // 4b5b: aload 0
      // 4b5c: bipush 1
      // 4b5d: putfield StageMaker.seq I
      // 4b60: goto 4b65
      // 4b65: aload 0
      // 4b66: getfield StageMaker.sptyp I
      // 4b69: bipush 2
      // 4b6a: if_icmpne 4b72
      // 4b6d: goto 4b9a
      // 4b72: aload 0
      // 4b73: getfield StageMaker.sptyp I
      // 4b76: bipush 3
      // 4b77: if_icmpne 4b7f
      // 4b7a: goto 4b9a
      // 4b7f: aload 0
      // 4b80: getfield StageMaker.sptyp I
      // 4b83: bipush 4
      // 4b84: if_icmpne 4b8c
      // 4b87: goto 4b9a
      // 4b8c: aload 0
      // 4b8d: getfield StageMaker.sptyp I
      // 4b90: bipush 6
      // 4b92: if_icmpeq 4b9a
      // 4b95: goto 4c5b
      // 4b9a: aload 0
      // 4b9b: getfield StageMaker.rot I
      // 4b9e: bipush -90
      // 4ba0: if_icmpne 4ba8
      // 4ba3: goto 4bb7
      // 4ba8: aload 0
      // 4ba9: getfield StageMaker.rot I
      // 4bac: sipush 180
      // 4baf: if_icmpeq 4bb7
      // 4bb2: goto 4bbf
      // 4bb7: bipush -1
      // 4bb8: istore 5
      // 4bba: goto 4bbf
      // 4bbf: aload 0
      // 4bc0: getfield StageMaker.seq I
      // 4bc3: bipush 2
      // 4bc4: if_icmpeq 4bcc
      // 4bc7: goto 4bff
      // 4bcc: aload 0
      // 4bcd: getfield StageMaker.bco [LContO;
      // 4bd0: aload 0
      // 4bd1: getfield StageMaker.sp I
      // 4bd4: aaload
      // 4bd5: dup
      // 4bd6: getfield ContO.zy I
      // 4bd9: bipush 5
      // 4bda: iload 5
      // 4bdc: imul
      // 4bdd: iadd
      // 4bde: putfield ContO.zy I
      // 4be1: aload 0
      // 4be2: getfield StageMaker.bco [LContO;
      // 4be5: aload 0
      // 4be6: getfield StageMaker.sp I
      // 4be9: aaload
      // 4bea: getfield ContO.zy I
      // 4bed: ifeq 4bf5
      // 4bf0: goto 4bff
      // 4bf5: aload 0
      // 4bf6: bipush 3
      // 4bf7: putfield StageMaker.seq I
      // 4bfa: goto 4bff
      // 4bff: aload 0
      // 4c00: getfield StageMaker.seq I
      // 4c03: bipush 1
      // 4c04: if_icmpeq 4c0c
      // 4c07: goto 4c16
      // 4c0c: aload 0
      // 4c0d: bipush 2
      // 4c0e: putfield StageMaker.seq I
      // 4c11: goto 4c16
      // 4c16: aload 0
      // 4c17: getfield StageMaker.seq I
      // 4c1a: ifeq 4c22
      // 4c1d: goto 4c5b
      // 4c22: aload 0
      // 4c23: getfield StageMaker.bco [LContO;
      // 4c26: aload 0
      // 4c27: getfield StageMaker.sp I
      // 4c2a: aaload
      // 4c2b: dup
      // 4c2c: getfield ContO.zy I
      // 4c2f: bipush 5
      // 4c30: iload 5
      // 4c32: imul
      // 4c33: isub
      // 4c34: putfield ContO.zy I
      // 4c37: aload 0
      // 4c38: getfield StageMaker.bco [LContO;
      // 4c3b: aload 0
      // 4c3c: getfield StageMaker.sp I
      // 4c3f: aaload
      // 4c40: getfield ContO.zy I
      // 4c43: bipush 85
      // 4c45: iload 5
      // 4c47: imul
      // 4c48: ineg
      // 4c49: if_icmpeq 4c51
      // 4c4c: goto 4c5b
      // 4c51: aload 0
      // 4c52: bipush 1
      // 4c53: putfield StageMaker.seq I
      // 4c56: goto 4c5b
      // 4c5b: aload 0
      // 4c5c: getfield StageMaker.sptyp I
      // 4c5f: bipush 5
      // 4c60: if_icmpeq 4c68
      // 4c63: goto 4dfd
      // 4c68: aload 0
      // 4c69: getfield StageMaker.rot I
      // 4c6c: bipush -90
      // 4c6e: if_icmpne 4c76
      // 4c71: goto 4c85
      // 4c76: aload 0
      // 4c77: getfield StageMaker.rot I
      // 4c7a: sipush 180
      // 4c7d: if_icmpeq 4c85
      // 4c80: goto 4c8d
      // 4c85: bipush -1
      // 4c86: istore 5
      // 4c88: goto 4c8d
      // 4c8d: bipush 0
      // 4c8e: istore 6
      // 4c90: aload 0
      // 4c91: getfield StageMaker.rot I
      // 4c94: bipush -90
      // 4c96: if_icmpne 4c9e
      // 4c99: goto 4cac
      // 4c9e: aload 0
      // 4c9f: getfield StageMaker.rot I
      // 4ca2: bipush 90
      // 4ca4: if_icmpeq 4cac
      // 4ca7: goto 4cb4
      // 4cac: bipush 1
      // 4cad: istore 6
      // 4caf: goto 4cb4
      // 4cb4: iload 6
      // 4cb6: ifeq 4cbe
      // 4cb9: goto 4cd0
      // 4cbe: aload 0
      // 4cbf: getfield StageMaker.bco [LContO;
      // 4cc2: aload 0
      // 4cc3: getfield StageMaker.sp I
      // 4cc6: aaload
      // 4cc7: bipush 0
      // 4cc8: putfield ContO.xy I
      // 4ccb: goto 4cdd
      // 4cd0: aload 0
      // 4cd1: getfield StageMaker.bco [LContO;
      // 4cd4: aload 0
      // 4cd5: getfield StageMaker.sp I
      // 4cd8: aaload
      // 4cd9: bipush 0
      // 4cda: putfield ContO.zy I
      // 4cdd: aload 0
      // 4cde: getfield StageMaker.seq I
      // 4ce1: bipush 2
      // 4ce2: if_icmpeq 4cea
      // 4ce5: goto 4d5a
      // 4cea: iload 6
      // 4cec: ifeq 4cf4
      // 4cef: goto 4d27
      // 4cf4: aload 0
      // 4cf5: getfield StageMaker.bco [LContO;
      // 4cf8: aload 0
      // 4cf9: getfield StageMaker.sp I
      // 4cfc: aaload
      // 4cfd: dup
      // 4cfe: getfield ContO.zy I
      // 4d01: bipush 5
      // 4d02: iload 5
      // 4d04: imul
      // 4d05: iadd
      // 4d06: putfield ContO.zy I
      // 4d09: aload 0
      // 4d0a: getfield StageMaker.bco [LContO;
      // 4d0d: aload 0
      // 4d0e: getfield StageMaker.sp I
      // 4d11: aaload
      // 4d12: getfield ContO.zy I
      // 4d15: ifeq 4d1d
      // 4d18: goto 4d5a
      // 4d1d: aload 0
      // 4d1e: bipush 3
      // 4d1f: putfield StageMaker.seq I
      // 4d22: goto 4d5a
      // 4d27: aload 0
      // 4d28: getfield StageMaker.bco [LContO;
      // 4d2b: aload 0
      // 4d2c: getfield StageMaker.sp I
      // 4d2f: aaload
      // 4d30: dup
      // 4d31: getfield ContO.xy I
      // 4d34: bipush 5
      // 4d35: iload 5
      // 4d37: imul
      // 4d38: isub
      // 4d39: putfield ContO.xy I
      // 4d3c: aload 0
      // 4d3d: getfield StageMaker.bco [LContO;
      // 4d40: aload 0
      // 4d41: getfield StageMaker.sp I
      // 4d44: aaload
      // 4d45: getfield ContO.xy I
      // 4d48: ifeq 4d50
      // 4d4b: goto 4d5a
      // 4d50: aload 0
      // 4d51: bipush 3
      // 4d52: putfield StageMaker.seq I
      // 4d55: goto 4d5a
      // 4d5a: aload 0
      // 4d5b: getfield StageMaker.seq I
      // 4d5e: bipush 1
      // 4d5f: if_icmpeq 4d67
      // 4d62: goto 4d71
      // 4d67: aload 0
      // 4d68: bipush 2
      // 4d69: putfield StageMaker.seq I
      // 4d6c: goto 4d71
      // 4d71: aload 0
      // 4d72: getfield StageMaker.seq I
      // 4d75: ifeq 4d7d
      // 4d78: goto 4df8
      // 4d7d: iload 6
      // 4d7f: ifeq 4d87
      // 4d82: goto 4dc0
      // 4d87: aload 0
      // 4d88: getfield StageMaker.bco [LContO;
      // 4d8b: aload 0
      // 4d8c: getfield StageMaker.sp I
      // 4d8f: aaload
      // 4d90: dup
      // 4d91: getfield ContO.zy I
      // 4d94: bipush 5
      // 4d95: iload 5
      // 4d97: imul
      // 4d98: isub
      // 4d99: putfield ContO.zy I
      // 4d9c: aload 0
      // 4d9d: getfield StageMaker.bco [LContO;
      // 4da0: aload 0
      // 4da1: getfield StageMaker.sp I
      // 4da4: aaload
      // 4da5: getfield ContO.zy I
      // 4da8: bipush 85
      // 4daa: iload 5
      // 4dac: imul
      // 4dad: ineg
      // 4dae: if_icmpeq 4db6
      // 4db1: goto 4df8
      // 4db6: aload 0
      // 4db7: bipush 1
      // 4db8: putfield StageMaker.seq I
      // 4dbb: goto 4df8
      // 4dc0: aload 0
      // 4dc1: getfield StageMaker.bco [LContO;
      // 4dc4: aload 0
      // 4dc5: getfield StageMaker.sp I
      // 4dc8: aaload
      // 4dc9: dup
      // 4dca: getfield ContO.xy I
      // 4dcd: bipush 5
      // 4dce: iload 5
      // 4dd0: imul
      // 4dd1: iadd
      // 4dd2: putfield ContO.xy I
      // 4dd5: aload 0
      // 4dd6: getfield StageMaker.bco [LContO;
      // 4dd9: aload 0
      // 4dda: getfield StageMaker.sp I
      // 4ddd: aaload
      // 4dde: getfield ContO.xy I
      // 4de1: bipush 85
      // 4de3: iload 5
      // 4de5: imul
      // 4de6: if_icmpeq 4dee
      // 4de9: goto 4df8
      // 4dee: aload 0
      // 4def: bipush 1
      // 4df0: putfield StageMaker.seq I
      // 4df3: goto 4df8
      // 4df8: goto 4dfd
      // 4dfd: aload 0
      // 4dfe: getfield StageMaker.sp I
      // 4e01: bipush 66
      // 4e03: if_icmpne 4e0b
      // 4e06: goto 4f45
      // 4e0b: aload 0
      // 4e0c: ldc_w "  Rotate  "
      // 4e0f: bipush 110
      // 4e11: sipush 348
      // 4e14: bipush 3
      // 4e15: bipush 1
      // 4e16: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 4e19: ifne 4e21
      // 4e1c: goto 4f8b
      // 4e21: bipush 0
      // 4e22: istore 6
      // 4e24: aload 0
      // 4e25: getfield StageMaker.rot I
      // 4e28: ifeq 4e30
      // 4e2b: goto 4e48
      // 4e30: iload 6
      // 4e32: ifeq 4e3a
      // 4e35: goto 4e48
      // 4e3a: aload 0
      // 4e3b: bipush 90
      // 4e3d: putfield StageMaker.rot I
      // 4e40: bipush 1
      // 4e41: istore 6
      // 4e43: goto 4e48
      // 4e48: aload 0
      // 4e49: getfield StageMaker.rot I
      // 4e4c: bipush 90
      // 4e4e: if_icmpeq 4e56
      // 4e51: goto 4e6f
      // 4e56: iload 6
      // 4e58: ifeq 4e60
      // 4e5b: goto 4e6f
      // 4e60: aload 0
      // 4e61: sipush 180
      // 4e64: putfield StageMaker.rot I
      // 4e67: bipush 1
      // 4e68: istore 6
      // 4e6a: goto 4e6f
      // 4e6f: aload 0
      // 4e70: getfield StageMaker.rot I
      // 4e73: sipush 180
      // 4e76: if_icmpeq 4e7e
      // 4e79: goto 4e96
      // 4e7e: iload 6
      // 4e80: ifeq 4e88
      // 4e83: goto 4e96
      // 4e88: aload 0
      // 4e89: bipush -90
      // 4e8b: putfield StageMaker.rot I
      // 4e8e: bipush 1
      // 4e8f: istore 6
      // 4e91: goto 4e96
      // 4e96: aload 0
      // 4e97: getfield StageMaker.rot I
      // 4e9a: bipush -90
      // 4e9c: if_icmpeq 4ea4
      // 4e9f: goto 4ebb
      // 4ea4: iload 6
      // 4ea6: ifeq 4eae
      // 4ea9: goto 4ebb
      // 4eae: aload 0
      // 4eaf: bipush 0
      // 4eb0: putfield StageMaker.rot I
      // 4eb3: bipush 1
      // 4eb4: istore 6
      // 4eb6: goto 4ebb
      // 4ebb: aload 0
      // 4ebc: getfield StageMaker.sp I
      // 4ebf: bipush 30
      // 4ec1: if_icmpne 4ec9
      // 4ec4: goto 4ee5
      // 4ec9: aload 0
      // 4eca: getfield StageMaker.sp I
      // 4ecd: bipush 31
      // 4ecf: if_icmpne 4ed7
      // 4ed2: goto 4ee5
      // 4ed7: aload 0
      // 4ed8: getfield StageMaker.sp I
      // 4edb: bipush 32
      // 4edd: if_icmpeq 4ee5
      // 4ee0: goto 4f17
      // 4ee5: aload 0
      // 4ee6: getfield StageMaker.rot I
      // 4ee9: bipush -90
      // 4eeb: if_icmpeq 4ef3
      // 4eee: goto 4efe
      // 4ef3: aload 0
      // 4ef4: bipush 90
      // 4ef6: putfield StageMaker.rot I
      // 4ef9: goto 4efe
      // 4efe: aload 0
      // 4eff: getfield StageMaker.rot I
      // 4f02: sipush 180
      // 4f05: if_icmpeq 4f0d
      // 4f08: goto 4f17
      // 4f0d: aload 0
      // 4f0e: bipush 0
      // 4f0f: putfield StageMaker.rot I
      // 4f12: goto 4f17
      // 4f17: aload 0
      // 4f18: bipush 3
      // 4f19: putfield StageMaker.seq I
      // 4f1c: aload 0
      // 4f1d: getfield StageMaker.bco [LContO;
      // 4f20: aload 0
      // 4f21: getfield StageMaker.sp I
      // 4f24: aaload
      // 4f25: bipush 0
      // 4f26: putfield ContO.xy I
      // 4f29: aload 0
      // 4f2a: getfield StageMaker.bco [LContO;
      // 4f2d: aload 0
      // 4f2e: getfield StageMaker.sp I
      // 4f31: aaload
      // 4f32: bipush 0
      // 4f33: putfield ContO.zy I
      // 4f36: aload 0
      // 4f37: bipush 0
      // 4f38: putfield StageMaker.epart Z
      // 4f3b: aload 0
      // 4f3c: bipush 0
      // 4f3d: putfield StageMaker.arrng Z
      // 4f40: goto 4f8b
      // 4f45: aload 0
      // 4f46: ldc_w "  Generate New  "
      // 4f49: bipush 110
      // 4f4b: sipush 348
      // 4f4e: bipush 3
      // 4f4f: bipush 1
      // 4f50: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 4f53: ifne 4f5b
      // 4f56: goto 4f8b
      // 4f5b: aload 0
      // 4f5c: bipush 0
      // 4f5d: putfield StageMaker.pgen Z
      // 4f60: aload 0
      // 4f61: ldc2_w 2
      // 4f64: invokestatic java/lang/Math.random ()D
      // 4f67: ldc2_w 4.0
      // 4f6a: dmul
      // 4f6b: invokestatic java/lang/Math.round (D)J
      // 4f6e: ladd
      // 4f6f: l2f
      // 4f70: putfield StageMaker.pwd F
      // 4f73: aload 0
      // 4f74: ldc2_w 2
      // 4f77: invokestatic java/lang/Math.random ()D
      // 4f7a: ldc2_w 4.0
      // 4f7d: dmul
      // 4f7e: invokestatic java/lang/Math.round (D)J
      // 4f81: ladd
      // 4f82: l2f
      // 4f83: putfield StageMaker.phd F
      // 4f86: goto 4f8b
      // 4f8b: aload 0
      // 4f8c: ldc_w ">"
      // 4f8f: sipush 191
      // 4f92: sipush 348
      // 4f95: bipush 3
      // 4f96: bipush 1
      // 4f97: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 4f9a: ifne 4fa2
      // 4f9d: goto 5016
      // 4fa2: aload 0
      // 4fa3: getfield StageMaker.sptyp I
      // 4fa6: ifne 4fae
      // 4fa9: goto 4fd5
      // 4fae: aload 0
      // 4faf: getfield StageMaker.sptyp I
      // 4fb2: bipush 1
      // 4fb3: if_icmpne 4fbb
      // 4fb6: goto 4fd5
      // 4fbb: aload 0
      // 4fbc: getfield StageMaker.sptyp I
      // 4fbf: bipush 2
      // 4fc0: if_icmpne 4fc8
      // 4fc3: goto 4fd5
      // 4fc8: aload 0
      // 4fc9: getfield StageMaker.sptyp I
      // 4fcc: bipush 5
      // 4fcd: if_icmpeq 4fd5
      // 4fd0: goto 5016
      // 4fd5: aload 0
      // 4fd6: dup
      // 4fd7: getfield StageMaker.spart I
      // 4fda: bipush 1
      // 4fdb: iadd
      // 4fdc: putfield StageMaker.spart I
      // 4fdf: aload 0
      // 4fe0: getfield StageMaker.spart I
      // 4fe3: aload 0
      // 4fe4: getfield StageMaker.part LSmenu;
      // 4fe7: invokevirtual Smenu.getItemCount ()I
      // 4fea: if_icmpeq 4ff2
      // 4fed: goto 4ffc
      // 4ff2: aload 0
      // 4ff3: bipush 0
      // 4ff4: putfield StageMaker.spart I
      // 4ff7: goto 4ffc
      // 4ffc: aload 0
      // 4ffd: getfield StageMaker.part LSmenu;
      // 5000: aload 0
      // 5001: getfield StageMaker.spart I
      // 5004: invokevirtual Smenu.select (I)V
      // 5007: aload 0
      // 5008: bipush 0
      // 5009: putfield StageMaker.epart Z
      // 500c: aload 0
      // 500d: bipush 0
      // 500e: putfield StageMaker.arrng Z
      // 5011: goto 5016
      // 5016: aload 0
      // 5017: ldc_w "<"
      // 501a: bipush 28
      // 501c: sipush 348
      // 501f: bipush 3
      // 5020: bipush 1
      // 5021: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5024: ifne 502c
      // 5027: goto 50a2
      // 502c: aload 0
      // 502d: getfield StageMaker.sptyp I
      // 5030: ifne 5038
      // 5033: goto 505f
      // 5038: aload 0
      // 5039: getfield StageMaker.sptyp I
      // 503c: bipush 1
      // 503d: if_icmpne 5045
      // 5040: goto 505f
      // 5045: aload 0
      // 5046: getfield StageMaker.sptyp I
      // 5049: bipush 2
      // 504a: if_icmpne 5052
      // 504d: goto 505f
      // 5052: aload 0
      // 5053: getfield StageMaker.sptyp I
      // 5056: bipush 5
      // 5057: if_icmpeq 505f
      // 505a: goto 50a2
      // 505f: aload 0
      // 5060: dup
      // 5061: getfield StageMaker.spart I
      // 5064: bipush 1
      // 5065: isub
      // 5066: putfield StageMaker.spart I
      // 5069: aload 0
      // 506a: getfield StageMaker.spart I
      // 506d: bipush -1
      // 506e: if_icmpeq 5076
      // 5071: goto 5088
      // 5076: aload 0
      // 5077: aload 0
      // 5078: getfield StageMaker.part LSmenu;
      // 507b: invokevirtual Smenu.getItemCount ()I
      // 507e: bipush 1
      // 507f: isub
      // 5080: putfield StageMaker.spart I
      // 5083: goto 5088
      // 5088: aload 0
      // 5089: getfield StageMaker.part LSmenu;
      // 508c: aload 0
      // 508d: getfield StageMaker.spart I
      // 5090: invokevirtual Smenu.select (I)V
      // 5093: aload 0
      // 5094: bipush 0
      // 5095: putfield StageMaker.epart Z
      // 5098: aload 0
      // 5099: bipush 0
      // 509a: putfield StageMaker.arrng Z
      // 509d: goto 50a2
      // 50a2: aload 0
      // 50a3: ldc_w "   <  Undo   "
      // 50a6: sipush 204
      // 50a9: sipush 404
      // 50ac: bipush 0
      // 50ad: bipush 1
      // 50ae: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 50b1: ifne 50b9
      // 50b4: goto 50f0
      // 50b9: aload 0
      // 50ba: bipush 0
      // 50bb: putfield StageMaker.epart Z
      // 50be: aload 0
      // 50bf: bipush 0
      // 50c0: putfield StageMaker.arrng Z
      // 50c3: aload 0
      // 50c4: getfield StageMaker.nundo I
      // 50c7: ifgt 50cf
      // 50ca: goto 50f0
      // 50cf: aload 0
      // 50d0: dup
      // 50d1: getfield StageMaker.nundo I
      // 50d4: bipush 1
      // 50d5: isub
      // 50d6: putfield StageMaker.nundo I
      // 50d9: aload 0
      // 50da: aload 0
      // 50db: getfield StageMaker.undos [Ljava/lang/String;
      // 50de: aload 0
      // 50df: getfield StageMaker.nundo I
      // 50e2: aaload
      // 50e3: putfield StageMaker.bstage Ljava/lang/String;
      // 50e6: aload 0
      // 50e7: bipush 0
      // 50e8: invokevirtual StageMaker.readstage (I)V
      // 50eb: goto 50f0
      // 50f0: aload 0
      // 50f1: ldc_w "   Remove / Edit  Part   "
      // 50f4: sipush 172
      // 50f7: sipush 454
      // 50fa: bipush 0
      // 50fb: bipush 1
      // 50fc: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 50ff: ifne 5107
      // 5102: goto 5131
      // 5107: aload 0
      // 5108: getfield StageMaker.epart Z
      // 510b: ifeq 5113
      // 510e: goto 511d
      // 5113: aload 0
      // 5114: bipush 1
      // 5115: putfield StageMaker.epart Z
      // 5118: goto 5122
      // 511d: aload 0
      // 511e: bipush 0
      // 511f: putfield StageMaker.epart Z
      // 5122: aload 0
      // 5123: bipush 0
      // 5124: putfield StageMaker.arrng Z
      // 5127: aload 0
      // 5128: bipush -1
      // 5129: putfield StageMaker.esp I
      // 512c: goto 5131
      // 5131: aload 0
      // 5132: ldc_w "   Go to >  Startline   "
      // 5135: sipush 175
      // 5138: sipush 504
      // 513b: bipush 0
      // 513c: bipush 1
      // 513d: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5140: ifne 5148
      // 5143: goto 5159
      // 5148: aload 0
      // 5149: bipush 0
      // 514a: putfield StageMaker.sx I
      // 514d: aload 0
      // 514e: sipush 1500
      // 5151: putfield StageMaker.sz I
      // 5154: goto 5159
      // 5159: aload 0
      // 515a: ldc_w " About Part "
      // 515d: sipush 164
      // 5160: bipush 66
      // 5162: bipush 3
      // 5163: bipush 0
      // 5164: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5167: ifne 516f
      // 516a: goto 5185
      // 516f: aconst_null
      // 5170: aload 0
      // 5171: getfield StageMaker.discp [Ljava/lang/String;
      // 5174: aload 0
      // 5175: getfield StageMaker.sp I
      // 5178: aaload
      // 5179: ldc_w "Stage Maker"
      // 517c: bipush 1
      // 517d: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 5180: goto 5185
      // 5185: aload 0
      // 5186: ldc_w "  Keyboard Controls  "
      // 5189: sipush 691
      // 518c: sipush 536
      // 518f: bipush 3
      // 5190: bipush 0
      // 5191: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5194: ifne 519c
      // 5197: goto 51ac
      // 519c: aconst_null
      // 519d: ldc_w "Instead of clicking the triangular buttons around the Building Area to scroll, you can use:\n[ Keyboard Arrows ]\n\nYou can also zoom in and out using the following keys:\n[+] & [-]  or  [8] & [2]  or  [Enter] & [Backspace]\n\n"
      // 51a0: ldc_w "Stage Maker"
      // 51a3: bipush 1
      // 51a4: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 51a7: goto 51ac
      // 51ac: aload 0
      // 51ad: ldc_w "  Save  "
      // 51b0: sipush 280
      // 51b3: bipush 50
      // 51b5: bipush 0
      // 51b6: bipush 0
      // 51b7: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 51ba: ifne 51c2
      // 51bd: goto 51d5
      // 51c2: aload 0
      // 51c3: bipush 0
      // 51c4: putfield StageMaker.epart Z
      // 51c7: aload 0
      // 51c8: bipush 0
      // 51c9: putfield StageMaker.arrng Z
      // 51cc: aload 0
      // 51cd: invokevirtual StageMaker.savefile ()V
      // 51d0: goto 51d5
      // 51d5: aload 0
      // 51d6: ldc_w "  Save & Preview  "
      // 51d9: sipush 380
      // 51dc: bipush 50
      // 51de: bipush 0
      // 51df: bipush 0
      // 51e0: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 51e3: ifne 51eb
      // 51e6: goto 5207
      // 51eb: aload 0
      // 51ec: bipush 0
      // 51ed: putfield StageMaker.epart Z
      // 51f0: aload 0
      // 51f1: bipush 0
      // 51f2: putfield StageMaker.arrng Z
      // 51f5: aload 0
      // 51f6: invokevirtual StageMaker.savefile ()V
      // 51f9: aload 0
      // 51fa: invokevirtual StageMaker.hidefields ()V
      // 51fd: aload 0
      // 51fe: bipush 2
      // 51ff: putfield StageMaker.tab I
      // 5202: goto 5207
      // 5207: aload 0
      // 5208: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 520b: new java/awt/Font
      // 520e: dup
      // 520f: ldc_w "Arial"
      // 5212: bipush 1
      // 5213: bipush 12
      // 5215: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5218: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 521b: aload 0
      // 521c: aload 0
      // 521d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5220: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5223: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 5226: aload 0
      // 5227: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 522a: new java/awt/Color
      // 522d: dup
      // 522e: bipush 0
      // 522f: bipush 0
      // 5230: bipush 0
      // 5231: invokespecial java/awt/Color.<init> (III)V
      // 5234: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5237: bipush 0
      // 5238: istore 6
      // 523a: bipush 0
      // 523b: istore 7
      // 523d: aload 0
      // 523e: getfield StageMaker.xnob I
      // 5241: i2f
      // 5242: ldc_w 601.0
      // 5245: fdiv
      // 5246: ldc_w 200.0
      // 5249: fmul
      // 524a: f2i
      // 524b: istore 8
      // 524d: iload 8
      // 524f: istore 6
      // 5251: aload 0
      // 5252: getfield StageMaker.t LTrackers;
      // 5255: getfield Trackers.nt I
      // 5258: i2f
      // 5259: ldc_w 6700.0
      // 525c: fdiv
      // 525d: ldc_w 200.0
      // 5260: fmul
      // 5261: f2i
      // 5262: istore 9
      // 5264: iload 9
      // 5266: iload 6
      // 5268: if_icmpgt 5270
      // 526b: goto 527c
      // 5270: iload 9
      // 5272: istore 6
      // 5274: bipush 1
      // 5275: istore 7
      // 5277: goto 527c
      // 527c: aload 0
      // 527d: getfield StageMaker.cp LCheckPoints;
      // 5280: getfield CheckPoints.n I
      // 5283: i2f
      // 5284: ldc_w 140.0
      // 5287: fdiv
      // 5288: ldc_w 200.0
      // 528b: fmul
      // 528c: f2i
      // 528d: istore 10
      // 528f: iload 10
      // 5291: iload 6
      // 5293: if_icmpgt 529b
      // 5296: goto 52a7
      // 529b: iload 10
      // 529d: istore 6
      // 529f: bipush 2
      // 52a0: istore 7
      // 52a2: goto 52a7
      // 52a7: aload 0
      // 52a8: getfield StageMaker.m LMedium;
      // 52ab: getfield Medium.nrw I
      // 52ae: aload 0
      // 52af: getfield StageMaker.m LMedium;
      // 52b2: getfield Medium.ncl I
      // 52b5: imul
      // 52b6: i2f
      // 52b7: ldc_w 16000.0
      // 52ba: fdiv
      // 52bb: ldc_w 200.0
      // 52be: fmul
      // 52bf: f2i
      // 52c0: istore 11
      // 52c2: iload 11
      // 52c4: iload 6
      // 52c6: if_icmpgt 52ce
      // 52c9: goto 52da
      // 52ce: iload 11
      // 52d0: istore 6
      // 52d2: bipush 3
      // 52d3: istore 7
      // 52d5: goto 52da
      // 52da: iload 6
      // 52dc: sipush 200
      // 52df: if_icmpgt 52e7
      // 52e2: goto 52f1
      // 52e7: sipush 200
      // 52ea: istore 6
      // 52ec: goto 52f1
      // 52f1: iload 6
      // 52f3: bipush 100
      // 52f5: if_icmple 52fd
      // 52f8: goto 531a
      // 52fd: aload 0
      // 52fe: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5301: new java/awt/Color
      // 5304: dup
      // 5305: bipush 100
      // 5307: iload 6
      // 5309: iadd
      // 530a: sipush 225
      // 530d: bipush 30
      // 530f: invokespecial java/awt/Color.<init> (III)V
      // 5312: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5315: goto 5333
      // 531a: aload 0
      // 531b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 531e: new java/awt/Color
      // 5321: dup
      // 5322: sipush 200
      // 5325: sipush 325
      // 5328: iload 6
      // 532a: isub
      // 532b: bipush 30
      // 532d: invokespecial java/awt/Color.<init> (III)V
      // 5330: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5333: aload 0
      // 5334: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5337: sipush 167
      // 533a: sipush 531
      // 533d: iload 6
      // 533f: bipush 9
      // 5341: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 5344: aload 0
      // 5345: ldc_w "Memory Consumption :"
      // 5348: bipush 85
      // 534a: sipush 540
      // 534d: bipush 3
      // 534e: bipush 0
      // 534f: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5352: ifne 535a
      // 5355: goto 53ab
      // 535a: aconst_null
      // 535b: new java/lang/StringBuilder
      // 535e: dup
      // 535f: invokespecial java/lang/StringBuilder.<init> ()V
      // 5362: ldc_w "Memory Consumption Details\n\nNumber of Parts:  "
      // 5365: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5368: iload 8
      // 536a: bipush 2
      // 536b: idiv
      // 536c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 536f: ldc_w " %\nPart's Details:  "
      // 5372: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5375: iload 9
      // 5377: bipush 2
      // 5378: idiv
      // 5379: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 537c: ldc_w " %\nRoad Points:  "
      // 537f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5382: iload 10
      // 5384: bipush 2
      // 5385: idiv
      // 5386: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5389: ldc_w " %\nStage Area:  "
      // 538c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 538f: iload 11
      // 5391: bipush 2
      // 5392: idiv
      // 5393: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5396: ldc_w " %\n \n"
      // 5399: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 539c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 539f: ldc_w "Stage Maker"
      // 53a2: bipush 1
      // 53a3: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 53a6: goto 53ab
      // 53ab: aload 0
      // 53ac: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 53af: new java/awt/Color
      // 53b2: dup
      // 53b3: bipush 0
      // 53b4: bipush 0
      // 53b5: bipush 0
      // 53b6: invokespecial java/awt/Color.<init> (III)V
      // 53b9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 53bc: aload 0
      // 53bd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 53c0: sipush 167
      // 53c3: sipush 531
      // 53c6: sipush 200
      // 53c9: bipush 9
      // 53cb: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 53ce: bipush 4
      // 53cf: anewarray 36
      // 53d2: dup
      // 53d3: bipush 0
      // 53d4: ldc_w "Number of Parts"
      // 53d7: aastore
      // 53d8: dup
      // 53d9: bipush 1
      // 53da: ldc_w "Part's Details"
      // 53dd: aastore
      // 53de: dup
      // 53df: bipush 2
      // 53e0: ldc_w "Road Points"
      // 53e3: aastore
      // 53e4: dup
      // 53e5: bipush 3
      // 53e6: ldc_w "Stage Area"
      // 53e9: aastore
      // 53ea: astore 12
      // 53ec: aload 0
      // 53ed: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 53f0: aload 12
      // 53f2: iload 7
      // 53f4: aaload
      // 53f5: sipush 267
      // 53f8: aload 0
      // 53f9: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 53fc: aload 12
      // 53fe: iload 7
      // 5400: aaload
      // 5401: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5404: bipush 2
      // 5405: idiv
      // 5406: isub
      // 5407: sipush 540
      // 540a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 540d: aload 0
      // 540e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5411: new java/lang/StringBuilder
      // 5414: dup
      // 5415: invokespecial java/lang/StringBuilder.<init> ()V
      // 5418: ldc ""
      // 541a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 541d: iload 6
      // 541f: bipush 2
      // 5420: idiv
      // 5421: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5424: ldc_w " %  used"
      // 5427: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 542a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 542d: sipush 375
      // 5430: sipush 540
      // 5433: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5436: aload 0
      // 5437: getfield StageMaker.overcan Z
      // 543a: ifne 5442
      // 543d: goto 544c
      // 5442: aload 0
      // 5443: bipush 0
      // 5444: putfield StageMaker.overcan Z
      // 5447: goto 544c
      // 544c: aload 0
      // 544d: getfield StageMaker.epart Z
      // 5450: ifne 5458
      // 5453: goto 54a7
      // 5458: aload 0
      // 5459: getfield StageMaker.esp I
      // 545c: bipush -1
      // 545d: if_icmpeq 5465
      // 5460: goto 54d5
      // 5465: aload 0
      // 5466: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5469: new java/awt/Color
      // 546c: dup
      // 546d: bipush 0
      // 546e: bipush 0
      // 546f: bipush 0
      // 5470: invokespecial java/awt/Color.<init> (III)V
      // 5473: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5476: aload 0
      // 5477: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 547a: ldc_w "Click on any part to Edit >"
      // 547d: sipush 257
      // 5480: sipush 454
      // 5483: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5486: aload 0
      // 5487: ldc_w " Cancel "
      // 548a: sipush 323
      // 548d: sipush 474
      // 5490: bipush 4
      // 5491: bipush 0
      // 5492: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 5495: ifne 549d
      // 5498: goto 54d5
      // 549d: aload 0
      // 549e: bipush 0
      // 549f: putfield StageMaker.epart Z
      // 54a2: goto 54d5
      // 54a7: aload 0
      // 54a8: getfield StageMaker.hi I
      // 54ab: bipush -1
      // 54ac: if_icmpne 54b4
      // 54af: goto 54be
      // 54b4: aload 0
      // 54b5: bipush -1
      // 54b6: putfield StageMaker.hi I
      // 54b9: goto 54be
      // 54be: aload 0
      // 54bf: getfield StageMaker.esp I
      // 54c2: bipush -1
      // 54c3: if_icmpne 54cb
      // 54c6: goto 54d5
      // 54cb: aload 0
      // 54cc: bipush -1
      // 54cd: putfield StageMaker.esp I
      // 54d0: goto 54d5
      // 54d5: aload 0
      // 54d6: getfield StageMaker.arrng Z
      // 54d9: ifne 54e1
      // 54dc: goto 5569
      // 54e1: aload 0
      // 54e2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 54e5: new java/awt/Color
      // 54e8: dup
      // 54e9: bipush 0
      // 54ea: bipush 0
      // 54eb: bipush 0
      // 54ec: invokespecial java/awt/Color.<init> (III)V
      // 54ef: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 54f2: aload 0
      // 54f3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 54f6: new java/lang/StringBuilder
      // 54f9: dup
      // 54fa: invokespecial java/lang/StringBuilder.<init> ()V
      // 54fd: ldc_w "Click on Checkpoint NO# "
      // 5500: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5503: aload 0
      // 5504: getfield StageMaker.arrcnt I
      // 5507: bipush 1
      // 5508: iadd
      // 5509: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 550c: ldc_w "  >"
      // 550f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5512: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5515: sipush 257
      // 5518: bipush 80
      // 551a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 551d: aload 0
      // 551e: ldc_w " Cancel "
      // 5521: sipush 330
      // 5524: bipush 100
      // 5526: bipush 4
      // 5527: bipush 0
      // 5528: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 552b: ifne 5533
      // 552e: goto 553d
      // 5533: aload 0
      // 5534: bipush 0
      // 5535: putfield StageMaker.arrng Z
      // 5538: goto 553d
      // 553d: aload 0
      // 553e: getfield StageMaker.arrcnt I
      // 5541: aload 0
      // 5542: getfield StageMaker.cp LCheckPoints;
      // 5545: getfield CheckPoints.nsp I
      // 5548: if_icmpeq 5550
      // 554b: goto 5580
      // 5550: aload 0
      // 5551: invokevirtual StageMaker.sortstage ()V
      // 5554: aconst_null
      // 5555: ldc_w "Checkpoints Arranged!\nPress Save and Test Drive to check the new checkpoint order.\n"
      // 5558: ldc_w "Stage Maker"
      // 555b: bipush 1
      // 555c: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 555f: aload 0
      // 5560: bipush 0
      // 5561: putfield StageMaker.arrng Z
      // 5564: goto 5580
      // 5569: aload 0
      // 556a: getfield StageMaker.chi I
      // 556d: bipush -1
      // 556e: if_icmpne 5576
      // 5571: goto 5580
      // 5576: aload 0
      // 5577: bipush -1
      // 5578: putfield StageMaker.chi I
      // 557b: goto 5580
      // 5580: goto 5585
      // 5585: aload 0
      // 5586: getfield StageMaker.tab I
      // 5589: bipush 2
      // 558a: if_icmpeq 5592
      // 558d: goto 8c8c
      // 5592: aload 0
      // 5593: getfield StageMaker.tabed I
      // 5596: aload 0
      // 5597: getfield StageMaker.tab I
      // 559a: if_icmpne 55a2
      // 559d: goto 55f6
      // 55a2: aload 0
      // 55a3: getfield StageMaker.m LMedium;
      // 55a6: bipush 0
      // 55a7: putfield Medium.trk I
      // 55aa: aload 0
      // 55ab: bipush 1
      // 55ac: invokevirtual StageMaker.readstage (I)V
      // 55af: aload 0
      // 55b0: new java/awt/Cursor
      // 55b3: dup
      // 55b4: bipush 0
      // 55b5: invokespecial java/awt/Cursor.<init> (I)V
      // 55b8: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 55bb: aload 0
      // 55bc: bipush 0
      // 55bd: putfield StageMaker.setcur Z
      // 55c0: aload 0
      // 55c1: bipush 0
      // 55c2: putfield StageMaker.vxz I
      // 55c5: aload 0
      // 55c6: aload 0
      // 55c7: getfield StageMaker.sx I
      // 55ca: sipush 400
      // 55cd: isub
      // 55ce: putfield StageMaker.vx I
      // 55d1: aload 0
      // 55d2: aload 0
      // 55d3: getfield StageMaker.sz I
      // 55d6: aload 0
      // 55d7: getfield StageMaker.m LMedium;
      // 55da: getfield Medium.cz I
      // 55dd: isub
      // 55de: sipush 8000
      // 55e1: isub
      // 55e2: putfield StageMaker.vz I
      // 55e5: aload 0
      // 55e6: sipush -1500
      // 55e9: putfield StageMaker.vy I
      // 55ec: aload 0
      // 55ed: bipush -1
      // 55ee: putfield StageMaker.dtabed I
      // 55f1: goto 55f6
      // 55f6: aload 0
      // 55f7: getfield StageMaker.m LMedium;
      // 55fa: bipush 0
      // 55fb: putfield Medium.trk I
      // 55fe: aload 0
      // 55ff: getfield StageMaker.m LMedium;
      // 5602: bipush 6
      // 5604: putfield Medium.zy I
      // 5607: aload 0
      // 5608: getfield StageMaker.m LMedium;
      // 560b: bipush 10
      // 560d: putfield Medium.iw I
      // 5610: aload 0
      // 5611: getfield StageMaker.m LMedium;
      // 5614: sipush 790
      // 5617: putfield Medium.w I
      // 561a: aload 0
      // 561b: getfield StageMaker.m LMedium;
      // 561e: bipush 35
      // 5620: putfield Medium.ih I
      // 5623: aload 0
      // 5624: getfield StageMaker.m LMedium;
      // 5627: sipush 445
      // 562a: putfield Medium.h I
      // 562d: aload 0
      // 562e: getfield StageMaker.m LMedium;
      // 5631: sipush 400
      // 5634: putfield Medium.cx I
      // 5637: aload 0
      // 5638: getfield StageMaker.m LMedium;
      // 563b: sipush 215
      // 563e: putfield Medium.cy I
      // 5641: aload 0
      // 5642: getfield StageMaker.m LMedium;
      // 5645: aload 0
      // 5646: getfield StageMaker.vxz I
      // 5649: putfield Medium.xz I
      // 564c: aload 0
      // 564d: getfield StageMaker.m LMedium;
      // 5650: aload 0
      // 5651: getfield StageMaker.vx I
      // 5654: putfield Medium.x I
      // 5657: aload 0
      // 5658: getfield StageMaker.m LMedium;
      // 565b: aload 0
      // 565c: getfield StageMaker.vz I
      // 565f: putfield Medium.z I
      // 5662: aload 0
      // 5663: getfield StageMaker.m LMedium;
      // 5666: aload 0
      // 5667: getfield StageMaker.vy I
      // 566a: putfield Medium.y I
      // 566d: aload 0
      // 566e: getfield StageMaker.m LMedium;
      // 5671: aload 0
      // 5672: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5675: invokevirtual Medium.d (Ljava/awt/Graphics2D;)V
      // 5678: bipush 0
      // 5679: istore 1
      // 567a: sipush 200
      // 567d: newarray 10
      // 567f: astore 2
      // 5680: bipush 0
      // 5681: istore 3
      // 5682: iload 3
      // 5683: aload 0
      // 5684: getfield StageMaker.nob I
      // 5687: if_icmplt 568f
      // 568a: goto 56c1
      // 568f: aload 0
      // 5690: getfield StageMaker.co [LContO;
      // 5693: iload 3
      // 5694: aaload
      // 5695: getfield ContO.dist I
      // 5698: ifne 56a0
      // 569b: goto 56ac
      // 56a0: aload 2
      // 56a1: iload 1
      // 56a2: iload 3
      // 56a3: iastore
      // 56a4: iinc 1 1
      // 56a7: goto 56b9
      // 56ac: aload 0
      // 56ad: getfield StageMaker.co [LContO;
      // 56b0: iload 3
      // 56b1: aaload
      // 56b2: aload 0
      // 56b3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 56b6: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 56b9: iinc 3 1
      // 56bc: goto 5682
      // 56c1: iload 1
      // 56c2: newarray 10
      // 56c4: astore 3
      // 56c5: bipush 0
      // 56c6: istore 4
      // 56c8: iload 4
      // 56ca: iload 1
      // 56cb: if_icmplt 56d3
      // 56ce: goto 56e0
      // 56d3: aload 3
      // 56d4: iload 4
      // 56d6: bipush 0
      // 56d7: iastore
      // 56d8: iinc 4 1
      // 56db: goto 56c8
      // 56e0: bipush 0
      // 56e1: istore 4
      // 56e3: iload 4
      // 56e5: iload 1
      // 56e6: if_icmplt 56ee
      // 56e9: goto 578a
      // 56ee: iload 4
      // 56f0: bipush 1
      // 56f1: iadd
      // 56f2: istore 5
      // 56f4: iload 5
      // 56f6: iload 1
      // 56f7: if_icmplt 56ff
      // 56fa: goto 5782
      // 56ff: aload 0
      // 5700: getfield StageMaker.co [LContO;
      // 5703: aload 2
      // 5704: iload 4
      // 5706: iaload
      // 5707: aaload
      // 5708: getfield ContO.dist I
      // 570b: aload 0
      // 570c: getfield StageMaker.co [LContO;
      // 570f: aload 2
      // 5710: iload 5
      // 5712: iaload
      // 5713: aaload
      // 5714: getfield ContO.dist I
      // 5717: if_icmpne 571f
      // 571a: goto 5759
      // 571f: aload 0
      // 5720: getfield StageMaker.co [LContO;
      // 5723: aload 2
      // 5724: iload 4
      // 5726: iaload
      // 5727: aaload
      // 5728: getfield ContO.dist I
      // 572b: aload 0
      // 572c: getfield StageMaker.co [LContO;
      // 572f: aload 2
      // 5730: iload 5
      // 5732: iaload
      // 5733: aaload
      // 5734: getfield ContO.dist I
      // 5737: if_icmplt 573f
      // 573a: goto 574c
      // 573f: aload 3
      // 5740: iload 4
      // 5742: dup2
      // 5743: iaload
      // 5744: bipush 1
      // 5745: iadd
      // 5746: iastore
      // 5747: goto 577a
      // 574c: aload 3
      // 574d: iload 5
      // 574f: dup2
      // 5750: iaload
      // 5751: bipush 1
      // 5752: iadd
      // 5753: iastore
      // 5754: goto 577a
      // 5759: iload 5
      // 575b: iload 4
      // 575d: if_icmpgt 5765
      // 5760: goto 5772
      // 5765: aload 3
      // 5766: iload 4
      // 5768: dup2
      // 5769: iaload
      // 576a: bipush 1
      // 576b: iadd
      // 576c: iastore
      // 576d: goto 577a
      // 5772: aload 3
      // 5773: iload 5
      // 5775: dup2
      // 5776: iaload
      // 5777: bipush 1
      // 5778: iadd
      // 5779: iastore
      // 577a: iinc 5 1
      // 577d: goto 56f4
      // 5782: iinc 4 1
      // 5785: goto 56e3
      // 578a: bipush 0
      // 578b: istore 4
      // 578d: iload 4
      // 578f: iload 1
      // 5790: if_icmplt 5798
      // 5793: goto 580e
      // 5798: bipush 0
      // 5799: istore 5
      // 579b: iload 5
      // 579d: iload 1
      // 579e: if_icmplt 57a6
      // 57a1: goto 5806
      // 57a6: aload 3
      // 57a7: iload 5
      // 57a9: iaload
      // 57aa: iload 4
      // 57ac: if_icmpeq 57b4
      // 57af: goto 57fe
      // 57b4: aload 2
      // 57b5: iload 5
      // 57b7: iaload
      // 57b8: aload 0
      // 57b9: getfield StageMaker.hi I
      // 57bc: if_icmpeq 57c4
      // 57bf: goto 57d1
      // 57c4: aload 0
      // 57c5: getfield StageMaker.m LMedium;
      // 57c8: bipush 3
      // 57c9: putfield Medium.trk I
      // 57cc: goto 57d1
      // 57d1: aload 0
      // 57d2: getfield StageMaker.co [LContO;
      // 57d5: aload 2
      // 57d6: iload 5
      // 57d8: iaload
      // 57d9: aaload
      // 57da: aload 0
      // 57db: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 57de: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 57e1: aload 0
      // 57e2: getfield StageMaker.m LMedium;
      // 57e5: getfield Medium.trk I
      // 57e8: bipush 3
      // 57e9: if_icmpeq 57f1
      // 57ec: goto 57fe
      // 57f1: aload 0
      // 57f2: getfield StageMaker.m LMedium;
      // 57f5: bipush 2
      // 57f6: putfield Medium.trk I
      // 57f9: goto 57fe
      // 57fe: iinc 5 1
      // 5801: goto 579b
      // 5806: iinc 4 1
      // 5809: goto 578d
      // 580e: aload 0
      // 580f: getfield StageMaker.up Z
      // 5812: ifne 581a
      // 5815: goto 5859
      // 581a: aload 0
      // 581b: dup
      // 581c: getfield StageMaker.vz I
      // 581f: i2f
      // 5820: ldc_w 500.0
      // 5823: aload 0
      // 5824: getfield StageMaker.m LMedium;
      // 5827: aload 0
      // 5828: getfield StageMaker.m LMedium;
      // 582b: getfield Medium.xz I
      // 582e: invokevirtual Medium.cos (I)F
      // 5831: fmul
      // 5832: fadd
      // 5833: f2i
      // 5834: putfield StageMaker.vz I
      // 5837: aload 0
      // 5838: dup
      // 5839: getfield StageMaker.vx I
      // 583c: i2f
      // 583d: ldc_w 500.0
      // 5840: aload 0
      // 5841: getfield StageMaker.m LMedium;
      // 5844: aload 0
      // 5845: getfield StageMaker.m LMedium;
      // 5848: getfield Medium.xz I
      // 584b: invokevirtual Medium.sin (I)F
      // 584e: fmul
      // 584f: fadd
      // 5850: f2i
      // 5851: putfield StageMaker.vx I
      // 5854: goto 5859
      // 5859: aload 0
      // 585a: getfield StageMaker.down Z
      // 585d: ifne 5865
      // 5860: goto 58a4
      // 5865: aload 0
      // 5866: dup
      // 5867: getfield StageMaker.vz I
      // 586a: i2f
      // 586b: ldc_w 500.0
      // 586e: aload 0
      // 586f: getfield StageMaker.m LMedium;
      // 5872: aload 0
      // 5873: getfield StageMaker.m LMedium;
      // 5876: getfield Medium.xz I
      // 5879: invokevirtual Medium.cos (I)F
      // 587c: fmul
      // 587d: fsub
      // 587e: f2i
      // 587f: putfield StageMaker.vz I
      // 5882: aload 0
      // 5883: dup
      // 5884: getfield StageMaker.vx I
      // 5887: i2f
      // 5888: ldc_w 500.0
      // 588b: aload 0
      // 588c: getfield StageMaker.m LMedium;
      // 588f: aload 0
      // 5890: getfield StageMaker.m LMedium;
      // 5893: getfield Medium.xz I
      // 5896: invokevirtual Medium.sin (I)F
      // 5899: fmul
      // 589a: fsub
      // 589b: f2i
      // 589c: putfield StageMaker.vx I
      // 589f: goto 58a4
      // 58a4: aload 0
      // 58a5: getfield StageMaker.left Z
      // 58a8: ifne 58b0
      // 58ab: goto 58bf
      // 58b0: aload 0
      // 58b1: dup
      // 58b2: getfield StageMaker.vxz I
      // 58b5: bipush 5
      // 58b6: isub
      // 58b7: putfield StageMaker.vxz I
      // 58ba: goto 58bf
      // 58bf: aload 0
      // 58c0: getfield StageMaker.right Z
      // 58c3: ifne 58cb
      // 58c6: goto 58da
      // 58cb: aload 0
      // 58cc: dup
      // 58cd: getfield StageMaker.vxz I
      // 58d0: bipush 5
      // 58d1: iadd
      // 58d2: putfield StageMaker.vxz I
      // 58d5: goto 58da
      // 58da: aload 0
      // 58db: getfield StageMaker.zoomi Z
      // 58de: ifne 58e6
      // 58e1: goto 590c
      // 58e6: aload 0
      // 58e7: dup
      // 58e8: getfield StageMaker.vy I
      // 58eb: bipush 100
      // 58ed: iadd
      // 58ee: putfield StageMaker.vy I
      // 58f1: aload 0
      // 58f2: getfield StageMaker.vy I
      // 58f5: sipush -500
      // 58f8: if_icmpgt 5900
      // 58fb: goto 590c
      // 5900: aload 0
      // 5901: sipush -500
      // 5904: putfield StageMaker.vy I
      // 5907: goto 590c
      // 590c: aload 0
      // 590d: getfield StageMaker.zoomo Z
      // 5910: ifne 5918
      // 5913: goto 593e
      // 5918: aload 0
      // 5919: dup
      // 591a: getfield StageMaker.vy I
      // 591d: bipush 100
      // 591f: isub
      // 5920: putfield StageMaker.vy I
      // 5923: aload 0
      // 5924: getfield StageMaker.vy I
      // 5927: sipush -5000
      // 592a: if_icmplt 5932
      // 592d: goto 593e
      // 5932: aload 0
      // 5933: sipush -5000
      // 5936: putfield StageMaker.vy I
      // 5939: goto 593e
      // 593e: aload 0
      // 593f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5942: new java/awt/Color
      // 5945: dup
      // 5946: sipush 225
      // 5949: sipush 225
      // 594c: sipush 225
      // 594f: invokespecial java/awt/Color.<init> (III)V
      // 5952: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5955: aload 0
      // 5956: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5959: bipush 0
      // 595a: bipush 25
      // 595c: bipush 10
      // 595e: sipush 525
      // 5961: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 5964: aload 0
      // 5965: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5968: sipush 790
      // 596b: bipush 25
      // 596d: bipush 10
      // 596f: sipush 525
      // 5972: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 5975: aload 0
      // 5976: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5979: bipush 10
      // 597b: bipush 25
      // 597d: sipush 780
      // 5980: bipush 10
      // 5982: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 5985: aload 0
      // 5986: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5989: bipush 10
      // 598b: sipush 445
      // 598e: sipush 780
      // 5991: bipush 105
      // 5993: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 5996: aload 0
      // 5997: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 599a: new java/awt/Font
      // 599d: dup
      // 599e: ldc_w "Arial"
      // 59a1: bipush 1
      // 59a2: bipush 12
      // 59a4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 59a7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 59aa: aload 0
      // 59ab: aload 0
      // 59ac: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 59af: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 59b2: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 59b5: bipush 7
      // 59b7: anewarray 36
      // 59ba: dup
      // 59bb: bipush 0
      // 59bc: ldc_w "Controls"
      // 59bf: aastore
      // 59c0: dup
      // 59c1: bipush 1
      // 59c2: ldc_w "Atmosphere"
      // 59c5: aastore
      // 59c6: dup
      // 59c7: bipush 2
      // 59c8: ldc_w "Colors"
      // 59cb: aastore
      // 59cc: dup
      // 59cd: bipush 3
      // 59ce: ldc_w "Scenery"
      // 59d1: aastore
      // 59d2: dup
      // 59d3: bipush 4
      // 59d4: ldc_w "Laps"
      // 59d7: aastore
      // 59d8: dup
      // 59d9: bipush 5
      // 59da: ldc_w "Sound Track"
      // 59dd: aastore
      // 59de: dup
      // 59df: bipush 6
      // 59e1: ldc_w "Test Drive"
      // 59e4: aastore
      // 59e5: astore 4
      // 59e7: bipush 4
      // 59e8: newarray 10
      // 59ea: dup
      // 59eb: bipush 0
      // 59ec: bipush 10
      // 59ee: iastore
      // 59ef: dup
      // 59f0: bipush 1
      // 59f1: bipush 10
      // 59f3: iastore
      // 59f4: dup
      // 59f5: bipush 2
      // 59f6: bipush 121
      // 59f8: iastore
      // 59f9: dup
      // 59fa: bipush 3
      // 59fb: bipush 111
      // 59fd: iastore
      // 59fe: astore 5
      // 5a00: bipush 4
      // 5a01: newarray 10
      // 5a03: dup
      // 5a04: bipush 0
      // 5a05: sipush 425
      // 5a08: iastore
      // 5a09: dup
      // 5a0a: bipush 1
      // 5a0b: sipush 445
      // 5a0e: iastore
      // 5a0f: dup
      // 5a10: bipush 2
      // 5a11: sipush 445
      // 5a14: iastore
      // 5a15: dup
      // 5a16: bipush 3
      // 5a17: sipush 425
      // 5a1a: iastore
      // 5a1b: astore 6
      // 5a1d: bipush 0
      // 5a1e: istore 7
      // 5a20: iload 7
      // 5a22: bipush 7
      // 5a24: if_icmplt 5a2c
      // 5a27: goto 5b95
      // 5a2c: aload 0
      // 5a2d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5a30: new java/awt/Color
      // 5a33: dup
      // 5a34: sipush 170
      // 5a37: sipush 170
      // 5a3a: sipush 170
      // 5a3d: invokespecial java/awt/Color.<init> (III)V
      // 5a40: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5a43: aload 0
      // 5a44: getfield StageMaker.xm I
      // 5a47: aload 5
      // 5a49: bipush 0
      // 5a4a: iaload
      // 5a4b: if_icmpgt 5a53
      // 5a4e: goto 5a9d
      // 5a53: aload 0
      // 5a54: getfield StageMaker.xm I
      // 5a57: aload 5
      // 5a59: bipush 3
      // 5a5a: iaload
      // 5a5b: if_icmplt 5a63
      // 5a5e: goto 5a9d
      // 5a63: aload 0
      // 5a64: getfield StageMaker.ym I
      // 5a67: sipush 425
      // 5a6a: if_icmpgt 5a72
      // 5a6d: goto 5a9d
      // 5a72: aload 0
      // 5a73: getfield StageMaker.ym I
      // 5a76: sipush 445
      // 5a79: if_icmplt 5a81
      // 5a7c: goto 5a9d
      // 5a81: aload 0
      // 5a82: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5a85: new java/awt/Color
      // 5a88: dup
      // 5a89: sipush 190
      // 5a8c: sipush 190
      // 5a8f: sipush 190
      // 5a92: invokespecial java/awt/Color.<init> (III)V
      // 5a95: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5a98: goto 5a9d
      // 5a9d: aload 0
      // 5a9e: getfield StageMaker.dtab I
      // 5aa1: iload 7
      // 5aa3: if_icmpeq 5aab
      // 5aa6: goto 5ac7
      // 5aab: aload 0
      // 5aac: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5aaf: new java/awt/Color
      // 5ab2: dup
      // 5ab3: sipush 225
      // 5ab6: sipush 225
      // 5ab9: sipush 225
      // 5abc: invokespecial java/awt/Color.<init> (III)V
      // 5abf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5ac2: goto 5ac7
      // 5ac7: aload 0
      // 5ac8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5acb: aload 5
      // 5acd: aload 6
      // 5acf: bipush 4
      // 5ad0: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // 5ad3: aload 0
      // 5ad4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5ad7: new java/awt/Color
      // 5ada: dup
      // 5adb: bipush 0
      // 5adc: bipush 0
      // 5add: bipush 0
      // 5ade: invokespecial java/awt/Color.<init> (III)V
      // 5ae1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5ae4: aload 0
      // 5ae5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5ae8: aload 4
      // 5aea: iload 7
      // 5aec: aaload
      // 5aed: iload 7
      // 5aef: bipush 111
      // 5af1: imul
      // 5af2: bipush 62
      // 5af4: iadd
      // 5af5: aload 0
      // 5af6: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 5af9: aload 4
      // 5afb: iload 7
      // 5afd: aaload
      // 5afe: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5b01: bipush 2
      // 5b02: idiv
      // 5b03: isub
      // 5b04: sipush 439
      // 5b07: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5b0a: aload 0
      // 5b0b: getfield StageMaker.xm I
      // 5b0e: aload 5
      // 5b10: bipush 0
      // 5b11: iaload
      // 5b12: if_icmpgt 5b1a
      // 5b15: goto 5b6d
      // 5b1a: aload 0
      // 5b1b: getfield StageMaker.xm I
      // 5b1e: aload 5
      // 5b20: bipush 3
      // 5b21: iaload
      // 5b22: if_icmplt 5b2a
      // 5b25: goto 5b6d
      // 5b2a: aload 0
      // 5b2b: getfield StageMaker.ym I
      // 5b2e: sipush 425
      // 5b31: if_icmpgt 5b39
      // 5b34: goto 5b6d
      // 5b39: aload 0
      // 5b3a: getfield StageMaker.ym I
      // 5b3d: sipush 445
      // 5b40: if_icmplt 5b48
      // 5b43: goto 5b6d
      // 5b48: aload 0
      // 5b49: getfield StageMaker.mouses I
      // 5b4c: bipush -1
      // 5b4d: if_icmpeq 5b55
      // 5b50: goto 5b6d
      // 5b55: aload 0
      // 5b56: getfield StageMaker.mouseon I
      // 5b59: bipush -1
      // 5b5a: if_icmpeq 5b62
      // 5b5d: goto 5b6d
      // 5b62: aload 0
      // 5b63: iload 7
      // 5b65: putfield StageMaker.dtab I
      // 5b68: goto 5b6d
      // 5b6d: bipush 0
      // 5b6e: istore 8
      // 5b70: iload 8
      // 5b72: bipush 4
      // 5b73: if_icmplt 5b7b
      // 5b76: goto 5b8d
      // 5b7b: aload 5
      // 5b7d: iload 8
      // 5b7f: dup2
      // 5b80: iaload
      // 5b81: bipush 111
      // 5b83: iadd
      // 5b84: iastore
      // 5b85: iinc 8 1
      // 5b88: goto 5b70
      // 5b8d: iinc 7 1
      // 5b90: goto 5a20
      // 5b95: aload 0
      // 5b96: getfield StageMaker.tabed I
      // 5b99: aload 0
      // 5b9a: getfield StageMaker.tab I
      // 5b9d: if_icmpeq 5ba5
      // 5ba0: goto 5be7
      // 5ba5: aload 0
      // 5ba6: getfield StageMaker.dtab I
      // 5ba9: aload 0
      // 5baa: getfield StageMaker.dtabed I
      // 5bad: if_icmpne 5bb5
      // 5bb0: goto 5be7
      // 5bb5: aload 0
      // 5bb6: getfield StageMaker.ttstage Ljava/lang/String;
      // 5bb9: ldc ""
      // 5bbb: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5bbe: ifeq 5bc6
      // 5bc1: goto 5bd9
      // 5bc6: aload 0
      // 5bc7: aload 0
      // 5bc8: getfield StageMaker.ttstage Ljava/lang/String;
      // 5bcb: putfield StageMaker.tstage Ljava/lang/String;
      // 5bce: aload 0
      // 5bcf: ldc ""
      // 5bd1: putfield StageMaker.ttstage Ljava/lang/String;
      // 5bd4: goto 5bd9
      // 5bd9: aload 0
      // 5bda: bipush 1
      // 5bdb: invokevirtual StageMaker.readstage (I)V
      // 5bde: aload 0
      // 5bdf: invokevirtual StageMaker.hidefields ()V
      // 5be2: goto 5be7
      // 5be7: aload 0
      // 5be8: getfield StageMaker.dtab I
      // 5beb: ifeq 5bf3
      // 5bee: goto 5c36
      // 5bf3: aload 0
      // 5bf4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5bf7: new java/awt/Color
      // 5bfa: dup
      // 5bfb: bipush 0
      // 5bfc: bipush 0
      // 5bfd: bipush 0
      // 5bfe: invokespecial java/awt/Color.<init> (III)V
      // 5c01: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5c04: aload 0
      // 5c05: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5c08: ldc_w "Use the [ Keyboard Arrows ] to navigate through the stage."
      // 5c0b: bipush 20
      // 5c0d: sipush 470
      // 5c10: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5c13: aload 0
      // 5c14: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5c17: ldc_w "[Left] & [Right] arrows are for rotating.  [Up] & [Down] arrows are for moving forwards and backwards."
      // 5c1a: bipush 20
      // 5c1c: sipush 490
      // 5c1f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5c22: aload 0
      // 5c23: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5c26: ldc_w "For moving vertically down and up use the following keys:  [+] & [-]  or  [8] & [2]  or  [Enter] & [Backspace]"
      // 5c29: bipush 20
      // 5c2b: sipush 520
      // 5c2e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5c31: goto 5c36
      // 5c36: aload 0
      // 5c37: getfield StageMaker.dtab I
      // 5c3a: bipush 2
      // 5c3b: if_icmpeq 5c43
      // 5c3e: goto 6506
      // 5c43: aload 0
      // 5c44: getfield StageMaker.dtabed I
      // 5c47: aload 0
      // 5c48: getfield StageMaker.dtab I
      // 5c4b: if_icmpne 5c53
      // 5c4e: goto 5d60
      // 5c53: aload 0
      // 5c54: getfield StageMaker.csky [I
      // 5c57: bipush 0
      // 5c58: iaload
      // 5c59: aload 0
      // 5c5a: getfield StageMaker.csky [I
      // 5c5d: bipush 1
      // 5c5e: iaload
      // 5c5f: aload 0
      // 5c60: getfield StageMaker.csky [I
      // 5c63: bipush 2
      // 5c64: iaload
      // 5c65: aload 0
      // 5c66: getfield StageMaker.hsb [[F
      // 5c69: bipush 0
      // 5c6a: aaload
      // 5c6b: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 5c6e: pop
      // 5c6f: aload 0
      // 5c70: getfield StageMaker.cfade [I
      // 5c73: bipush 0
      // 5c74: iaload
      // 5c75: aload 0
      // 5c76: getfield StageMaker.cfade [I
      // 5c79: bipush 1
      // 5c7a: iaload
      // 5c7b: aload 0
      // 5c7c: getfield StageMaker.cfade [I
      // 5c7f: bipush 2
      // 5c80: iaload
      // 5c81: aload 0
      // 5c82: getfield StageMaker.hsb [[F
      // 5c85: bipush 1
      // 5c86: aaload
      // 5c87: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 5c8a: pop
      // 5c8b: aload 0
      // 5c8c: getfield StageMaker.cgrnd [I
      // 5c8f: bipush 0
      // 5c90: iaload
      // 5c91: aload 0
      // 5c92: getfield StageMaker.cgrnd [I
      // 5c95: bipush 1
      // 5c96: iaload
      // 5c97: aload 0
      // 5c98: getfield StageMaker.cgrnd [I
      // 5c9b: bipush 2
      // 5c9c: iaload
      // 5c9d: aload 0
      // 5c9e: getfield StageMaker.hsb [[F
      // 5ca1: bipush 2
      // 5ca2: aaload
      // 5ca3: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 5ca6: pop
      // 5ca7: bipush 0
      // 5ca8: istore 7
      // 5caa: iload 7
      // 5cac: bipush 3
      // 5cad: if_icmplt 5cb5
      // 5cb0: goto 5ce5
      // 5cb5: aload 0
      // 5cb6: getfield StageMaker.hsb [[F
      // 5cb9: iload 7
      // 5cbb: aaload
      // 5cbc: bipush 1
      // 5cbd: faload
      // 5cbe: fstore 8
      // 5cc0: aload 0
      // 5cc1: getfield StageMaker.hsb [[F
      // 5cc4: iload 7
      // 5cc6: aaload
      // 5cc7: bipush 1
      // 5cc8: aload 0
      // 5cc9: getfield StageMaker.hsb [[F
      // 5ccc: iload 7
      // 5cce: aaload
      // 5ccf: bipush 2
      // 5cd0: faload
      // 5cd1: fastore
      // 5cd2: aload 0
      // 5cd3: getfield StageMaker.hsb [[F
      // 5cd6: iload 7
      // 5cd8: aaload
      // 5cd9: bipush 2
      // 5cda: fload 8
      // 5cdc: fastore
      // 5cdd: iinc 7 1
      // 5ce0: goto 5caa
      // 5ce5: aload 0
      // 5ce6: getfield StageMaker.hsb [[F
      // 5ce9: bipush 1
      // 5cea: aaload
      // 5ceb: bipush 1
      // 5cec: faload
      // 5ced: aload 0
      // 5cee: getfield StageMaker.hsb [[F
      // 5cf1: bipush 0
      // 5cf2: aaload
      // 5cf3: bipush 1
      // 5cf4: faload
      // 5cf5: aload 0
      // 5cf6: getfield StageMaker.hsb [[F
      // 5cf9: bipush 2
      // 5cfa: aaload
      // 5cfb: bipush 1
      // 5cfc: faload
      // 5cfd: fadd
      // 5cfe: fconst_2
      // 5cff: fdiv
      // 5d00: fcmpl
      // 5d01: ifeq 5d09
      // 5d04: goto 5d48
      // 5d09: aload 0
      // 5d0a: getfield StageMaker.hsb [[F
      // 5d0d: bipush 1
      // 5d0e: aaload
      // 5d0f: bipush 0
      // 5d10: faload
      // 5d11: aload 0
      // 5d12: getfield StageMaker.hsb [[F
      // 5d15: bipush 2
      // 5d16: aaload
      // 5d17: bipush 0
      // 5d18: faload
      // 5d19: fcmpl
      // 5d1a: ifeq 5d22
      // 5d1d: goto 5d48
      // 5d22: aload 0
      // 5d23: getfield StageMaker.hsb [[F
      // 5d26: bipush 1
      // 5d27: aaload
      // 5d28: bipush 2
      // 5d29: faload
      // 5d2a: aload 0
      // 5d2b: getfield StageMaker.hsb [[F
      // 5d2e: bipush 2
      // 5d2f: aaload
      // 5d30: bipush 2
      // 5d31: faload
      // 5d32: fcmpl
      // 5d33: ifeq 5d3b
      // 5d36: goto 5d48
      // 5d3b: aload 0
      // 5d3c: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 5d3f: bipush 1
      // 5d40: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5d43: goto 5d50
      // 5d48: aload 0
      // 5d49: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 5d4c: bipush 0
      // 5d4d: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5d50: aload 0
      // 5d51: ldc ""
      // 5d53: putfield StageMaker.ttstage Ljava/lang/String;
      // 5d56: aload 0
      // 5d57: bipush -1
      // 5d58: putfield StageMaker.mouseon I
      // 5d5b: goto 5d60
      // 5d60: aload 0
      // 5d61: getfield StageMaker.mouses I
      // 5d64: bipush 1
      // 5d65: if_icmpne 5d6d
      // 5d68: goto 5dcb
      // 5d6d: aload 0
      // 5d6e: getfield StageMaker.mouseon I
      // 5d71: bipush 6
      // 5d73: if_icmplt 5d7b
      // 5d76: goto 5d88
      // 5d7b: aload 0
      // 5d7c: getfield StageMaker.mouseon I
      // 5d7f: bipush 3
      // 5d80: if_icmplt 5d88
      // 5d83: goto 5dc1
      // 5d88: aload 0
      // 5d89: getfield StageMaker.mouseon I
      // 5d8c: bipush -1
      // 5d8d: if_icmpne 5d95
      // 5d90: goto 5dc1
      // 5d95: aload 0
      // 5d96: getfield StageMaker.ttstage Ljava/lang/String;
      // 5d99: ldc ""
      // 5d9b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5d9e: ifne 5da6
      // 5da1: goto 5db3
      // 5da6: aload 0
      // 5da7: aload 0
      // 5da8: getfield StageMaker.tstage Ljava/lang/String;
      // 5dab: putfield StageMaker.ttstage Ljava/lang/String;
      // 5dae: goto 5db3
      // 5db3: aload 0
      // 5db4: invokevirtual StageMaker.sortop ()V
      // 5db7: aload 0
      // 5db8: bipush 1
      // 5db9: invokevirtual StageMaker.readstage (I)V
      // 5dbc: goto 5dc1
      // 5dc1: aload 0
      // 5dc2: bipush -1
      // 5dc3: putfield StageMaker.mouseon I
      // 5dc6: goto 5dcb
      // 5dcb: bipush 3
      // 5dcc: anewarray 36
      // 5dcf: dup
      // 5dd0: bipush 0
      // 5dd1: ldc_w "Sky"
      // 5dd4: aastore
      // 5dd5: dup
      // 5dd6: bipush 1
      // 5dd7: ldc_w "Dust / Fog"
      // 5dda: aastore
      // 5ddb: dup
      // 5ddc: bipush 2
      // 5ddd: ldc_w "Ground"
      // 5de0: aastore
      // 5de1: astore 7
      // 5de3: bipush 0
      // 5de4: istore 8
      // 5de6: iload 8
      // 5de8: bipush 3
      // 5de9: if_icmplt 5df1
      // 5dec: goto 62d6
      // 5df1: aload 0
      // 5df2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5df5: new java/awt/Color
      // 5df8: dup
      // 5df9: bipush 0
      // 5dfa: bipush 0
      // 5dfb: bipush 0
      // 5dfc: invokespecial java/awt/Color.<init> (III)V
      // 5dff: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5e02: aload 0
      // 5e03: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5e06: aload 7
      // 5e08: iload 8
      // 5e0a: aaload
      // 5e0b: bipush 107
      // 5e0d: sipush 195
      // 5e10: iload 8
      // 5e12: imul
      // 5e13: iadd
      // 5e14: aload 0
      // 5e15: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 5e18: aload 7
      // 5e1a: iload 8
      // 5e1c: aaload
      // 5e1d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5e20: bipush 2
      // 5e21: idiv
      // 5e22: isub
      // 5e23: sipush 461
      // 5e26: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5e29: bipush 0
      // 5e2a: istore 9
      // 5e2c: iload 9
      // 5e2e: sipush 150
      // 5e31: if_icmplt 5e39
      // 5e34: goto 5e7b
      // 5e39: aload 0
      // 5e3a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5e3d: iload 9
      // 5e3f: i2f
      // 5e40: f2d
      // 5e41: ldc2_w 0.006667
      // 5e44: dmul
      // 5e45: d2f
      // 5e46: fconst_1
      // 5e47: fconst_1
      // 5e48: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 5e4b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5e4e: aload 0
      // 5e4f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5e52: bipush 32
      // 5e54: iload 9
      // 5e56: iadd
      // 5e57: sipush 195
      // 5e5a: iload 8
      // 5e5c: imul
      // 5e5d: iadd
      // 5e5e: sipush 467
      // 5e61: bipush 32
      // 5e63: iload 9
      // 5e65: iadd
      // 5e66: sipush 195
      // 5e69: iload 8
      // 5e6b: imul
      // 5e6c: iadd
      // 5e6d: sipush 474
      // 5e70: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5e73: iinc 9 1
      // 5e76: goto 5e2c
      // 5e7b: bipush 0
      // 5e7c: istore 9
      // 5e7e: iload 9
      // 5e80: sipush 150
      // 5e83: if_icmplt 5e8b
      // 5e86: goto 5ece
      // 5e8b: aload 0
      // 5e8c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5e8f: fconst_0
      // 5e90: fconst_0
      // 5e91: ldc 0.5
      // 5e93: iload 9
      // 5e95: i2f
      // 5e96: ldc_w 0.00333
      // 5e99: fmul
      // 5e9a: fadd
      // 5e9b: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 5e9e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5ea1: aload 0
      // 5ea2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5ea5: bipush 32
      // 5ea7: iload 9
      // 5ea9: iadd
      // 5eaa: sipush 195
      // 5ead: iload 8
      // 5eaf: imul
      // 5eb0: iadd
      // 5eb1: sipush 483
      // 5eb4: bipush 32
      // 5eb6: iload 9
      // 5eb8: iadd
      // 5eb9: sipush 195
      // 5ebc: iload 8
      // 5ebe: imul
      // 5ebf: iadd
      // 5ec0: sipush 490
      // 5ec3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5ec6: iinc 9 1
      // 5ec9: goto 5e7e
      // 5ece: bipush 0
      // 5ecf: istore 9
      // 5ed1: iload 9
      // 5ed3: sipush 150
      // 5ed6: if_icmplt 5ede
      // 5ed9: goto 5f32
      // 5ede: aload 0
      // 5edf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5ee2: aload 0
      // 5ee3: getfield StageMaker.hsb [[F
      // 5ee6: iload 8
      // 5ee8: aaload
      // 5ee9: bipush 0
      // 5eea: faload
      // 5eeb: fconst_0
      // 5eec: iload 9
      // 5eee: i2f
      // 5eef: f2d
      // 5ef0: ldc2_w 0.001667
      // 5ef3: dmul
      // 5ef4: d2f
      // 5ef5: fadd
      // 5ef6: aload 0
      // 5ef7: getfield StageMaker.hsb [[F
      // 5efa: iload 8
      // 5efc: aaload
      // 5efd: bipush 1
      // 5efe: faload
      // 5eff: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 5f02: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5f05: aload 0
      // 5f06: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5f09: bipush 32
      // 5f0b: iload 9
      // 5f0d: iadd
      // 5f0e: sipush 195
      // 5f11: iload 8
      // 5f13: imul
      // 5f14: iadd
      // 5f15: sipush 499
      // 5f18: bipush 32
      // 5f1a: iload 9
      // 5f1c: iadd
      // 5f1d: sipush 195
      // 5f20: iload 8
      // 5f22: imul
      // 5f23: iadd
      // 5f24: sipush 506
      // 5f27: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5f2a: iinc 9 1
      // 5f2d: goto 5ed1
      // 5f32: bipush 0
      // 5f33: istore 9
      // 5f35: iload 9
      // 5f37: bipush 3
      // 5f38: if_icmplt 5f40
      // 5f3b: goto 62ce
      // 5f40: aload 0
      // 5f41: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 5f44: new java/awt/Color
      // 5f47: dup
      // 5f48: bipush 0
      // 5f49: bipush 0
      // 5f4a: bipush 0
      // 5f4b: invokespecial java/awt/Color.<init> (III)V
      // 5f4e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5f51: aload 0
      // 5f52: getfield StageMaker.hsb [[F
      // 5f55: iload 8
      // 5f57: aaload
      // 5f58: iload 9
      // 5f5a: faload
      // 5f5b: ldc_w 150.0
      // 5f5e: fmul
      // 5f5f: fstore 10
      // 5f61: iload 9
      // 5f63: bipush 1
      // 5f64: if_icmpeq 5f6c
      // 5f67: goto 5fb2
      // 5f6c: ldc_w 0.75
      // 5f6f: fstore 11
      // 5f71: iload 8
      // 5f73: ifeq 5f7b
      // 5f76: goto 5f85
      // 5f7b: ldc_w 0.85
      // 5f7e: fstore 11
      // 5f80: goto 5f85
      // 5f85: iload 8
      // 5f87: bipush 1
      // 5f88: if_icmpeq 5f90
      // 5f8b: goto 5f9a
      // 5f90: ldc_w 0.8
      // 5f93: fstore 11
      // 5f95: goto 5f9a
      // 5f9a: aload 0
      // 5f9b: getfield StageMaker.hsb [[F
      // 5f9e: iload 8
      // 5fa0: aaload
      // 5fa1: iload 9
      // 5fa3: faload
      // 5fa4: fload 11
      // 5fa6: fsub
      // 5fa7: ldc_w 0.001
      // 5faa: fdiv
      // 5fab: fstore 10
      // 5fad: goto 5fb2
      // 5fb2: iload 9
      // 5fb4: bipush 2
      // 5fb5: if_icmpeq 5fbd
      // 5fb8: goto 5fd2
      // 5fbd: aload 0
      // 5fbe: getfield StageMaker.hsb [[F
      // 5fc1: iload 8
      // 5fc3: aaload
      // 5fc4: iload 9
      // 5fc6: faload
      // 5fc7: ldc_w 600.0
      // 5fca: fmul
      // 5fcb: fstore 10
      // 5fcd: goto 5fd2
      // 5fd2: fload 10
      // 5fd4: fconst_0
      // 5fd5: fcmpg
      // 5fd6: iflt 5fde
      // 5fd9: goto 5fe6
      // 5fde: fconst_0
      // 5fdf: fstore 10
      // 5fe1: goto 5fe6
      // 5fe6: fload 10
      // 5fe8: ldc_w 150.0
      // 5feb: fcmpl
      // 5fec: ifgt 5ff4
      // 5fef: goto 5ffe
      // 5ff4: ldc_w 150.0
      // 5ff7: fstore 10
      // 5ff9: goto 5ffe
      // 5ffe: aload 0
      // 5fff: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6002: bipush 32
      // 6004: sipush 195
      // 6007: iload 8
      // 6009: imul
      // 600a: iadd
      // 600b: i2f
      // 600c: fload 10
      // 600e: fadd
      // 600f: f2i
      // 6010: sipush 467
      // 6013: iload 9
      // 6015: bipush 16
      // 6017: imul
      // 6018: iadd
      // 6019: bipush 32
      // 601b: sipush 195
      // 601e: iload 8
      // 6020: imul
      // 6021: iadd
      // 6022: i2f
      // 6023: fload 10
      // 6025: fadd
      // 6026: f2i
      // 6027: sipush 474
      // 602a: iload 9
      // 602c: bipush 16
      // 602e: imul
      // 602f: iadd
      // 6030: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6033: aload 0
      // 6034: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6037: bipush 33
      // 6039: sipush 195
      // 603c: iload 8
      // 603e: imul
      // 603f: iadd
      // 6040: i2f
      // 6041: fload 10
      // 6043: fadd
      // 6044: f2i
      // 6045: sipush 467
      // 6048: iload 9
      // 604a: bipush 16
      // 604c: imul
      // 604d: iadd
      // 604e: bipush 33
      // 6050: sipush 195
      // 6053: iload 8
      // 6055: imul
      // 6056: iadd
      // 6057: i2f
      // 6058: fload 10
      // 605a: fadd
      // 605b: f2i
      // 605c: sipush 474
      // 605f: iload 9
      // 6061: bipush 16
      // 6063: imul
      // 6064: iadd
      // 6065: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6068: aload 0
      // 6069: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 606c: bipush 31
      // 606e: sipush 195
      // 6071: iload 8
      // 6073: imul
      // 6074: iadd
      // 6075: i2f
      // 6076: fload 10
      // 6078: fadd
      // 6079: f2i
      // 607a: sipush 475
      // 607d: iload 9
      // 607f: bipush 16
      // 6081: imul
      // 6082: iadd
      // 6083: bipush 4
      // 6084: bipush 2
      // 6085: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6088: aload 0
      // 6089: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 608c: bipush 30
      // 608e: sipush 195
      // 6091: iload 8
      // 6093: imul
      // 6094: iadd
      // 6095: i2f
      // 6096: fload 10
      // 6098: fadd
      // 6099: f2i
      // 609a: sipush 477
      // 609d: iload 9
      // 609f: bipush 16
      // 60a1: imul
      // 60a2: iadd
      // 60a3: bipush 35
      // 60a5: sipush 195
      // 60a8: iload 8
      // 60aa: imul
      // 60ab: iadd
      // 60ac: i2f
      // 60ad: fload 10
      // 60af: fadd
      // 60b0: f2i
      // 60b1: sipush 477
      // 60b4: iload 9
      // 60b6: bipush 16
      // 60b8: imul
      // 60b9: iadd
      // 60ba: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 60bd: aload 0
      // 60be: getfield StageMaker.xm I
      // 60c1: bipush 29
      // 60c3: sipush 195
      // 60c6: iload 8
      // 60c8: imul
      // 60c9: iadd
      // 60ca: if_icmpgt 60d2
      // 60cd: goto 613c
      // 60d2: aload 0
      // 60d3: getfield StageMaker.xm I
      // 60d6: sipush 185
      // 60d9: sipush 195
      // 60dc: iload 8
      // 60de: imul
      // 60df: iadd
      // 60e0: if_icmplt 60e8
      // 60e3: goto 613c
      // 60e8: aload 0
      // 60e9: getfield StageMaker.ym I
      // 60ec: sipush 468
      // 60ef: iload 9
      // 60f1: bipush 16
      // 60f3: imul
      // 60f4: iadd
      // 60f5: if_icmpgt 60fd
      // 60f8: goto 613c
      // 60fd: aload 0
      // 60fe: getfield StageMaker.ym I
      // 6101: sipush 477
      // 6104: iload 9
      // 6106: bipush 16
      // 6108: imul
      // 6109: iadd
      // 610a: if_icmplt 6112
      // 610d: goto 613c
      // 6112: aload 0
      // 6113: getfield StageMaker.mouses I
      // 6116: bipush 1
      // 6117: if_icmpeq 611f
      // 611a: goto 613c
      // 611f: aload 0
      // 6120: getfield StageMaker.mouseon I
      // 6123: bipush -1
      // 6124: if_icmpeq 612c
      // 6127: goto 613c
      // 612c: aload 0
      // 612d: iload 9
      // 612f: iload 8
      // 6131: bipush 3
      // 6132: imul
      // 6133: iadd
      // 6134: putfield StageMaker.mouseon I
      // 6137: goto 613c
      // 613c: aload 0
      // 613d: getfield StageMaker.mouseon I
      // 6140: iload 9
      // 6142: iload 8
      // 6144: bipush 3
      // 6145: imul
      // 6146: iadd
      // 6147: if_icmpeq 614f
      // 614a: goto 62c6
      // 614f: iload 9
      // 6151: ifeq 6159
      // 6154: goto 617b
      // 6159: aload 0
      // 615a: getfield StageMaker.hsb [[F
      // 615d: iload 8
      // 615f: aaload
      // 6160: iload 9
      // 6162: aload 0
      // 6163: getfield StageMaker.xm I
      // 6166: bipush 32
      // 6168: sipush 195
      // 616b: iload 8
      // 616d: imul
      // 616e: iadd
      // 616f: isub
      // 6170: i2f
      // 6171: ldc_w 150.0
      // 6174: fdiv
      // 6175: fastore
      // 6176: goto 617b
      // 617b: iload 9
      // 617d: bipush 1
      // 617e: if_icmpeq 6186
      // 6181: goto 622d
      // 6186: ldc_w 0.75
      // 6189: fstore 11
      // 618b: iload 8
      // 618d: ifeq 6195
      // 6190: goto 619f
      // 6195: ldc_w 0.85
      // 6198: fstore 11
      // 619a: goto 619f
      // 619f: iload 8
      // 61a1: bipush 1
      // 61a2: if_icmpeq 61aa
      // 61a5: goto 61b4
      // 61aa: ldc_w 0.8
      // 61ad: fstore 11
      // 61af: goto 61b4
      // 61b4: aload 0
      // 61b5: getfield StageMaker.hsb [[F
      // 61b8: iload 8
      // 61ba: aaload
      // 61bb: iload 9
      // 61bd: fload 11
      // 61bf: aload 0
      // 61c0: getfield StageMaker.xm I
      // 61c3: bipush 32
      // 61c5: sipush 195
      // 61c8: iload 8
      // 61ca: imul
      // 61cb: iadd
      // 61cc: isub
      // 61cd: i2f
      // 61ce: ldc_w 0.001
      // 61d1: fmul
      // 61d2: fadd
      // 61d3: fastore
      // 61d4: aload 0
      // 61d5: getfield StageMaker.hsb [[F
      // 61d8: iload 8
      // 61da: aaload
      // 61db: iload 9
      // 61dd: faload
      // 61de: fload 11
      // 61e0: fcmpg
      // 61e1: iflt 61e9
      // 61e4: goto 61fa
      // 61e9: aload 0
      // 61ea: getfield StageMaker.hsb [[F
      // 61ed: iload 8
      // 61ef: aaload
      // 61f0: iload 9
      // 61f2: fload 11
      // 61f4: fastore
      // 61f5: goto 61fa
      // 61fa: aload 0
      // 61fb: getfield StageMaker.hsb [[F
      // 61fe: iload 8
      // 6200: aaload
      // 6201: iload 9
      // 6203: faload
      // 6204: fload 11
      // 6206: ldc_w 0.15
      // 6209: fadd
      // 620a: fcmpl
      // 620b: ifgt 6213
      // 620e: goto 6228
      // 6213: aload 0
      // 6214: getfield StageMaker.hsb [[F
      // 6217: iload 8
      // 6219: aaload
      // 621a: iload 9
      // 621c: fload 11
      // 621e: ldc_w 0.15
      // 6221: fadd
      // 6222: fastore
      // 6223: goto 6228
      // 6228: goto 622d
      // 622d: iload 9
      // 622f: bipush 2
      // 6230: if_icmpeq 6238
      // 6233: goto 627e
      // 6238: aload 0
      // 6239: getfield StageMaker.hsb [[F
      // 623c: iload 8
      // 623e: aaload
      // 623f: iload 9
      // 6241: aload 0
      // 6242: getfield StageMaker.xm I
      // 6245: bipush 32
      // 6247: sipush 195
      // 624a: iload 8
      // 624c: imul
      // 624d: iadd
      // 624e: isub
      // 624f: i2f
      // 6250: ldc_w 600.0
      // 6253: fdiv
      // 6254: fastore
      // 6255: aload 0
      // 6256: getfield StageMaker.hsb [[F
      // 6259: iload 8
      // 625b: aaload
      // 625c: iload 9
      // 625e: faload
      // 625f: f2d
      // 6260: ldc2_w 0.25
      // 6263: dcmpl
      // 6264: ifgt 626c
      // 6267: goto 627e
      // 626c: aload 0
      // 626d: getfield StageMaker.hsb [[F
      // 6270: iload 8
      // 6272: aaload
      // 6273: iload 9
      // 6275: ldc_w 0.25
      // 6278: fastore
      // 6279: goto 627e
      // 627e: aload 0
      // 627f: getfield StageMaker.hsb [[F
      // 6282: iload 8
      // 6284: aaload
      // 6285: iload 9
      // 6287: faload
      // 6288: fconst_1
      // 6289: fcmpl
      // 628a: ifgt 6292
      // 628d: goto 62a2
      // 6292: aload 0
      // 6293: getfield StageMaker.hsb [[F
      // 6296: iload 8
      // 6298: aaload
      // 6299: iload 9
      // 629b: fconst_1
      // 629c: fastore
      // 629d: goto 62a2
      // 62a2: aload 0
      // 62a3: getfield StageMaker.hsb [[F
      // 62a6: iload 8
      // 62a8: aaload
      // 62a9: iload 9
      // 62ab: faload
      // 62ac: fconst_0
      // 62ad: fcmpg
      // 62ae: iflt 62b6
      // 62b1: goto 62c6
      // 62b6: aload 0
      // 62b7: getfield StageMaker.hsb [[F
      // 62ba: iload 8
      // 62bc: aaload
      // 62bd: iload 9
      // 62bf: fconst_0
      // 62c0: fastore
      // 62c1: goto 62c6
      // 62c6: iinc 9 1
      // 62c9: goto 5f35
      // 62ce: iinc 8 1
      // 62d1: goto 5de6
      // 62d6: aload 0
      // 62d7: aload 0
      // 62d8: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 62db: sipush 258
      // 62de: sipush 511
      // 62e1: sipush 200
      // 62e4: bipush 23
      // 62e6: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // 62e9: aload 0
      // 62ea: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 62ed: invokevirtual java/awt/Checkbox.isShowing ()Z
      // 62f0: ifeq 62f8
      // 62f3: goto 6304
      // 62f8: aload 0
      // 62f9: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 62fc: invokevirtual java/awt/Checkbox.show ()V
      // 62ff: goto 6304
      // 6304: aload 0
      // 6305: getfield StageMaker.pfog Ljava/awt/Checkbox;
      // 6308: invokevirtual java/awt/Checkbox.getState ()Z
      // 630b: ifne 6313
      // 630e: goto 6390
      // 6313: aload 0
      // 6314: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6317: bipush 3
      // 6318: ldc_w 0.25
      // 631b: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 631e: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 6321: aload 0
      // 6322: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6325: new java/awt/Color
      // 6328: dup
      // 6329: bipush 0
      // 632a: bipush 0
      // 632b: bipush 0
      // 632c: invokespecial java/awt/Color.<init> (III)V
      // 632f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6332: aload 0
      // 6333: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6336: sipush 215
      // 6339: sipush 464
      // 633c: sipush 175
      // 633f: bipush 47
      // 6341: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6344: aload 0
      // 6345: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6348: bipush 3
      // 6349: fconst_1
      // 634a: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 634d: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 6350: aload 0
      // 6351: getfield StageMaker.hsb [[F
      // 6354: bipush 1
      // 6355: aaload
      // 6356: bipush 1
      // 6357: aload 0
      // 6358: getfield StageMaker.hsb [[F
      // 635b: bipush 0
      // 635c: aaload
      // 635d: bipush 1
      // 635e: faload
      // 635f: aload 0
      // 6360: getfield StageMaker.hsb [[F
      // 6363: bipush 2
      // 6364: aaload
      // 6365: bipush 1
      // 6366: faload
      // 6367: fadd
      // 6368: fconst_2
      // 6369: fdiv
      // 636a: fastore
      // 636b: aload 0
      // 636c: getfield StageMaker.hsb [[F
      // 636f: bipush 1
      // 6370: aaload
      // 6371: bipush 0
      // 6372: aload 0
      // 6373: getfield StageMaker.hsb [[F
      // 6376: bipush 2
      // 6377: aaload
      // 6378: bipush 0
      // 6379: faload
      // 637a: fastore
      // 637b: aload 0
      // 637c: getfield StageMaker.hsb [[F
      // 637f: bipush 1
      // 6380: aaload
      // 6381: bipush 2
      // 6382: aload 0
      // 6383: getfield StageMaker.hsb [[F
      // 6386: bipush 2
      // 6387: aaload
      // 6388: bipush 2
      // 6389: faload
      // 638a: fastore
      // 638b: goto 6390
      // 6390: aload 0
      // 6391: getfield StageMaker.hsb [[F
      // 6394: bipush 0
      // 6395: aaload
      // 6396: bipush 0
      // 6397: faload
      // 6398: aload 0
      // 6399: getfield StageMaker.hsb [[F
      // 639c: bipush 0
      // 639d: aaload
      // 639e: bipush 2
      // 639f: faload
      // 63a0: aload 0
      // 63a1: getfield StageMaker.hsb [[F
      // 63a4: bipush 0
      // 63a5: aaload
      // 63a6: bipush 1
      // 63a7: faload
      // 63a8: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 63ab: astore 8
      // 63ad: aload 0
      // 63ae: getfield StageMaker.m LMedium;
      // 63b1: aload 8
      // 63b3: invokevirtual java/awt/Color.getRed ()I
      // 63b6: aload 8
      // 63b8: invokevirtual java/awt/Color.getGreen ()I
      // 63bb: aload 8
      // 63bd: invokevirtual java/awt/Color.getBlue ()I
      // 63c0: invokevirtual Medium.setsky (III)V
      // 63c3: aload 0
      // 63c4: getfield StageMaker.csky [I
      // 63c7: bipush 0
      // 63c8: aload 8
      // 63ca: invokevirtual java/awt/Color.getRed ()I
      // 63cd: iastore
      // 63ce: aload 0
      // 63cf: getfield StageMaker.csky [I
      // 63d2: bipush 1
      // 63d3: aload 8
      // 63d5: invokevirtual java/awt/Color.getGreen ()I
      // 63d8: iastore
      // 63d9: aload 0
      // 63da: getfield StageMaker.csky [I
      // 63dd: bipush 2
      // 63de: aload 8
      // 63e0: invokevirtual java/awt/Color.getBlue ()I
      // 63e3: iastore
      // 63e4: aload 0
      // 63e5: getfield StageMaker.hsb [[F
      // 63e8: bipush 1
      // 63e9: aaload
      // 63ea: bipush 0
      // 63eb: faload
      // 63ec: aload 0
      // 63ed: getfield StageMaker.hsb [[F
      // 63f0: bipush 1
      // 63f1: aaload
      // 63f2: bipush 2
      // 63f3: faload
      // 63f4: aload 0
      // 63f5: getfield StageMaker.hsb [[F
      // 63f8: bipush 1
      // 63f9: aaload
      // 63fa: bipush 1
      // 63fb: faload
      // 63fc: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 63ff: astore 8
      // 6401: aload 0
      // 6402: getfield StageMaker.m LMedium;
      // 6405: aload 8
      // 6407: invokevirtual java/awt/Color.getRed ()I
      // 640a: aload 8
      // 640c: invokevirtual java/awt/Color.getGreen ()I
      // 640f: aload 8
      // 6411: invokevirtual java/awt/Color.getBlue ()I
      // 6414: invokevirtual Medium.setfade (III)V
      // 6417: aload 0
      // 6418: getfield StageMaker.cfade [I
      // 641b: bipush 0
      // 641c: aload 8
      // 641e: invokevirtual java/awt/Color.getRed ()I
      // 6421: iastore
      // 6422: aload 0
      // 6423: getfield StageMaker.cfade [I
      // 6426: bipush 1
      // 6427: aload 8
      // 6429: invokevirtual java/awt/Color.getGreen ()I
      // 642c: iastore
      // 642d: aload 0
      // 642e: getfield StageMaker.cfade [I
      // 6431: bipush 2
      // 6432: aload 8
      // 6434: invokevirtual java/awt/Color.getBlue ()I
      // 6437: iastore
      // 6438: aload 0
      // 6439: getfield StageMaker.hsb [[F
      // 643c: bipush 2
      // 643d: aaload
      // 643e: bipush 0
      // 643f: faload
      // 6440: aload 0
      // 6441: getfield StageMaker.hsb [[F
      // 6444: bipush 2
      // 6445: aaload
      // 6446: bipush 2
      // 6447: faload
      // 6448: aload 0
      // 6449: getfield StageMaker.hsb [[F
      // 644c: bipush 2
      // 644d: aaload
      // 644e: bipush 1
      // 644f: faload
      // 6450: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 6453: astore 8
      // 6455: aload 0
      // 6456: getfield StageMaker.m LMedium;
      // 6459: aload 8
      // 645b: invokevirtual java/awt/Color.getRed ()I
      // 645e: aload 8
      // 6460: invokevirtual java/awt/Color.getGreen ()I
      // 6463: aload 8
      // 6465: invokevirtual java/awt/Color.getBlue ()I
      // 6468: invokevirtual Medium.setgrnd (III)V
      // 646b: aload 0
      // 646c: getfield StageMaker.cgrnd [I
      // 646f: bipush 0
      // 6470: aload 8
      // 6472: invokevirtual java/awt/Color.getRed ()I
      // 6475: iastore
      // 6476: aload 0
      // 6477: getfield StageMaker.cgrnd [I
      // 647a: bipush 1
      // 647b: aload 8
      // 647d: invokevirtual java/awt/Color.getGreen ()I
      // 6480: iastore
      // 6481: aload 0
      // 6482: getfield StageMaker.cgrnd [I
      // 6485: bipush 2
      // 6486: aload 8
      // 6488: invokevirtual java/awt/Color.getBlue ()I
      // 648b: iastore
      // 648c: aload 0
      // 648d: ldc_w " Reset "
      // 6490: sipush 650
      // 6493: sipush 510
      // 6496: bipush 0
      // 6497: bipush 1
      // 6498: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 649b: ifne 64a3
      // 649e: goto 64d7
      // 64a3: aload 0
      // 64a4: getfield StageMaker.ttstage Ljava/lang/String;
      // 64a7: ldc ""
      // 64a9: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 64ac: ifeq 64b4
      // 64af: goto 64c7
      // 64b4: aload 0
      // 64b5: aload 0
      // 64b6: getfield StageMaker.ttstage Ljava/lang/String;
      // 64b9: putfield StageMaker.tstage Ljava/lang/String;
      // 64bc: aload 0
      // 64bd: ldc ""
      // 64bf: putfield StageMaker.ttstage Ljava/lang/String;
      // 64c2: goto 64c7
      // 64c7: aload 0
      // 64c8: bipush 1
      // 64c9: invokevirtual StageMaker.readstage (I)V
      // 64cc: aload 0
      // 64cd: bipush -2
      // 64cf: putfield StageMaker.dtabed I
      // 64d2: goto 64d7
      // 64d7: aload 0
      // 64d8: ldc_w "        Save        "
      // 64db: sipush 737
      // 64de: sipush 510
      // 64e1: bipush 0
      // 64e2: bipush 1
      // 64e3: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 64e6: ifne 64ee
      // 64e9: goto 6501
      // 64ee: aload 0
      // 64ef: invokevirtual StageMaker.sortop ()V
      // 64f2: aload 0
      // 64f3: ldc ""
      // 64f5: putfield StageMaker.ttstage Ljava/lang/String;
      // 64f8: aload 0
      // 64f9: invokevirtual StageMaker.savefile ()V
      // 64fc: goto 6501
      // 6501: goto 6506
      // 6506: aload 0
      // 6507: getfield StageMaker.dtab I
      // 650a: bipush 3
      // 650b: if_icmpeq 6513
      // 650e: goto 752b
      // 6513: aload 0
      // 6514: getfield StageMaker.dtabed I
      // 6517: aload 0
      // 6518: getfield StageMaker.dtab I
      // 651b: if_icmpne 6523
      // 651e: goto 6590
      // 6523: aload 0
      // 6524: getfield StageMaker.cldd [I
      // 6527: bipush 0
      // 6528: iaload
      // 6529: aload 0
      // 652a: getfield StageMaker.cldd [I
      // 652d: bipush 1
      // 652e: iaload
      // 652f: aload 0
      // 6530: getfield StageMaker.cldd [I
      // 6533: bipush 2
      // 6534: iaload
      // 6535: aload 0
      // 6536: getfield StageMaker.hsb [[F
      // 6539: bipush 0
      // 653a: aaload
      // 653b: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 653e: pop
      // 653f: aload 0
      // 6540: getfield StageMaker.texture [I
      // 6543: bipush 0
      // 6544: iaload
      // 6545: aload 0
      // 6546: getfield StageMaker.texture [I
      // 6549: bipush 1
      // 654a: iaload
      // 654b: aload 0
      // 654c: getfield StageMaker.texture [I
      // 654f: bipush 2
      // 6550: iaload
      // 6551: aload 0
      // 6552: getfield StageMaker.hsb [[F
      // 6555: bipush 1
      // 6556: aaload
      // 6557: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 655a: pop
      // 655b: aload 0
      // 655c: getfield StageMaker.mgen Ljava/awt/TextField;
      // 655f: new java/lang/StringBuilder
      // 6562: dup
      // 6563: invokespecial java/lang/StringBuilder.<init> ()V
      // 6566: ldc ""
      // 6568: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 656b: aload 0
      // 656c: getfield StageMaker.m LMedium;
      // 656f: getfield Medium.mgen I
      // 6572: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6575: ldc ""
      // 6577: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 657a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 657d: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 6580: aload 0
      // 6581: bipush -1
      // 6582: putfield StageMaker.mouseon I
      // 6585: aload 0
      // 6586: ldc ""
      // 6588: putfield StageMaker.ttstage Ljava/lang/String;
      // 658b: goto 6590
      // 6590: aload 0
      // 6591: getfield StageMaker.mouses I
      // 6594: bipush 1
      // 6595: if_icmpne 659d
      // 6598: goto 6607
      // 659d: aload 0
      // 659e: getfield StageMaker.mouseon I
      // 65a1: ifne 65a9
      // 65a4: goto 65d1
      // 65a9: aload 0
      // 65aa: getfield StageMaker.mouseon I
      // 65ad: bipush 1
      // 65ae: if_icmpne 65b6
      // 65b1: goto 65d1
      // 65b6: aload 0
      // 65b7: getfield StageMaker.mouseon I
      // 65ba: bipush 2
      // 65bb: if_icmpne 65c3
      // 65be: goto 65d1
      // 65c3: aload 0
      // 65c4: getfield StageMaker.mouseon I
      // 65c7: bipush 6
      // 65c9: if_icmpeq 65d1
      // 65cc: goto 65fd
      // 65d1: aload 0
      // 65d2: getfield StageMaker.ttstage Ljava/lang/String;
      // 65d5: ldc ""
      // 65d7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 65da: ifne 65e2
      // 65dd: goto 65ef
      // 65e2: aload 0
      // 65e3: aload 0
      // 65e4: getfield StageMaker.tstage Ljava/lang/String;
      // 65e7: putfield StageMaker.ttstage Ljava/lang/String;
      // 65ea: goto 65ef
      // 65ef: aload 0
      // 65f0: invokevirtual StageMaker.sortop ()V
      // 65f3: aload 0
      // 65f4: bipush 1
      // 65f5: invokevirtual StageMaker.readstage (I)V
      // 65f8: goto 65fd
      // 65fd: aload 0
      // 65fe: bipush -1
      // 65ff: putfield StageMaker.mouseon I
      // 6602: goto 6607
      // 6607: aload 0
      // 6608: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 660b: new java/awt/Font
      // 660e: dup
      // 660f: ldc_w "Arial"
      // 6612: bipush 1
      // 6613: bipush 12
      // 6615: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6618: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 661b: aload 0
      // 661c: aload 0
      // 661d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6620: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6623: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 6626: aload 0
      // 6627: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 662a: new java/awt/Color
      // 662d: dup
      // 662e: bipush 0
      // 662f: bipush 0
      // 6630: bipush 0
      // 6631: invokespecial java/awt/Color.<init> (III)V
      // 6634: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6637: aload 0
      // 6638: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 663b: ldc_w "Clouds"
      // 663e: bipush 32
      // 6640: sipush 461
      // 6643: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6646: bipush 0
      // 6647: istore 7
      // 6649: iload 7
      // 664b: sipush 150
      // 664e: if_icmplt 6656
      // 6651: goto 668c
      // 6656: aload 0
      // 6657: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 665a: iload 7
      // 665c: i2f
      // 665d: ldc_w 0.006667
      // 6660: fmul
      // 6661: fconst_1
      // 6662: fconst_1
      // 6663: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 6666: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6669: aload 0
      // 666a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 666d: bipush 32
      // 666f: iload 7
      // 6671: iadd
      // 6672: bipush 0
      // 6673: iadd
      // 6674: sipush 467
      // 6677: bipush 32
      // 6679: iload 7
      // 667b: iadd
      // 667c: bipush 0
      // 667d: iadd
      // 667e: sipush 474
      // 6681: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6684: iinc 7 1
      // 6687: goto 6649
      // 668c: bipush 0
      // 668d: istore 7
      // 668f: iload 7
      // 6691: sipush 150
      // 6694: if_icmplt 669c
      // 6697: goto 66d6
      // 669c: aload 0
      // 669d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 66a0: fconst_0
      // 66a1: fconst_0
      // 66a2: ldc_w 0.75
      // 66a5: iload 7
      // 66a7: i2f
      // 66a8: ldc_w 0.001667
      // 66ab: fmul
      // 66ac: fadd
      // 66ad: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 66b0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 66b3: aload 0
      // 66b4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 66b7: bipush 32
      // 66b9: iload 7
      // 66bb: iadd
      // 66bc: bipush 0
      // 66bd: iadd
      // 66be: sipush 483
      // 66c1: bipush 32
      // 66c3: iload 7
      // 66c5: iadd
      // 66c6: bipush 0
      // 66c7: iadd
      // 66c8: sipush 490
      // 66cb: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 66ce: iinc 7 1
      // 66d1: goto 668f
      // 66d6: bipush 0
      // 66d7: istore 7
      // 66d9: iload 7
      // 66db: sipush 150
      // 66de: if_icmplt 66e6
      // 66e1: goto 672a
      // 66e6: aload 0
      // 66e7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 66ea: aload 0
      // 66eb: getfield StageMaker.hsb [[F
      // 66ee: bipush 0
      // 66ef: aaload
      // 66f0: bipush 0
      // 66f1: faload
      // 66f2: iload 7
      // 66f4: i2f
      // 66f5: ldc_w 0.003333
      // 66f8: fmul
      // 66f9: aload 0
      // 66fa: getfield StageMaker.hsb [[F
      // 66fd: bipush 0
      // 66fe: aaload
      // 66ff: bipush 2
      // 6700: faload
      // 6701: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 6704: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6707: aload 0
      // 6708: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 670b: bipush 32
      // 670d: iload 7
      // 670f: iadd
      // 6710: bipush 0
      // 6711: iadd
      // 6712: sipush 499
      // 6715: bipush 32
      // 6717: iload 7
      // 6719: iadd
      // 671a: bipush 0
      // 671b: iadd
      // 671c: sipush 506
      // 671f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6722: iinc 7 1
      // 6725: goto 66d9
      // 672a: aload 0
      // 672b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 672e: new java/awt/Font
      // 6731: dup
      // 6732: ldc_w "Arial"
      // 6735: bipush 0
      // 6736: bipush 11
      // 6738: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 673b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 673e: aload 0
      // 673f: aload 0
      // 6740: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6743: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6746: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 6749: aload 0
      // 674a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 674d: new java/awt/Color
      // 6750: dup
      // 6751: bipush 0
      // 6752: bipush 0
      // 6753: bipush 0
      // 6754: invokespecial java/awt/Color.<init> (III)V
      // 6757: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 675a: aload 0
      // 675b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 675e: ldc_w "Blend:"
      // 6761: bipush 32
      // 6763: sipush 529
      // 6766: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6769: aload 0
      // 676a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 676d: new java/awt/Color
      // 6770: dup
      // 6771: bipush 0
      // 6772: bipush 0
      // 6773: bipush 0
      // 6774: invokespecial java/awt/Color.<init> (III)V
      // 6777: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 677a: aload 0
      // 677b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 677e: bipush 70
      // 6780: sipush 522
      // 6783: bipush 112
      // 6785: bipush 2
      // 6786: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6789: aload 0
      // 678a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 678d: bipush 70
      // 678f: sipush 528
      // 6792: bipush 112
      // 6794: bipush 2
      // 6795: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6798: fconst_0
      // 6799: fstore 7
      // 679b: sipush 255
      // 679e: istore 8
      // 67a0: bipush 0
      // 67a1: istore 9
      // 67a3: iload 9
      // 67a5: bipush 112
      // 67a7: if_icmplt 67af
      // 67aa: goto 681e
      // 67af: ldc_w 255.0
      // 67b2: fload 7
      // 67b4: fconst_1
      // 67b5: fadd
      // 67b6: fdiv
      // 67b7: f2i
      // 67b8: istore 8
      // 67ba: iload 8
      // 67bc: sipush 255
      // 67bf: if_icmpgt 67c7
      // 67c2: goto 67d1
      // 67c7: sipush 255
      // 67ca: istore 8
      // 67cc: goto 67d1
      // 67d1: iload 8
      // 67d3: iflt 67db
      // 67d6: goto 67e3
      // 67db: bipush 0
      // 67dc: istore 8
      // 67de: goto 67e3
      // 67e3: fload 7
      // 67e5: ldc_w 0.02
      // 67e8: fadd
      // 67e9: fstore 7
      // 67eb: aload 0
      // 67ec: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 67ef: new java/awt/Color
      // 67f2: dup
      // 67f3: iload 8
      // 67f5: iload 8
      // 67f7: iload 8
      // 67f9: invokespecial java/awt/Color.<init> (III)V
      // 67fc: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 67ff: aload 0
      // 6800: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6803: bipush 70
      // 6805: iload 9
      // 6807: iadd
      // 6808: sipush 524
      // 680b: bipush 70
      // 680d: iload 9
      // 680f: iadd
      // 6810: sipush 527
      // 6813: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6816: iinc 9 1
      // 6819: goto 67a3
      // 681e: aload 0
      // 681f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6822: new java/awt/Color
      // 6825: dup
      // 6826: bipush 0
      // 6827: bipush 0
      // 6828: bipush 0
      // 6829: invokespecial java/awt/Color.<init> (III)V
      // 682c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 682f: aload 0
      // 6830: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6833: ldc_w "Height"
      // 6836: sipush 202
      // 6839: aload 0
      // 683a: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 683d: ldc_w "Height"
      // 6840: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6843: bipush 2
      // 6844: idiv
      // 6845: isub
      // 6846: sipush 461
      // 6849: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 684c: aload 0
      // 684d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6850: sipush 202
      // 6853: sipush 467
      // 6856: sipush 202
      // 6859: sipush 530
      // 685c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 685f: bipush 0
      // 6860: istore 9
      // 6862: iload 9
      // 6864: bipush 8
      // 6866: if_icmplt 686e
      // 6869: goto 689b
      // 686e: aload 0
      // 686f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6872: sipush 202
      // 6875: sipush 466
      // 6878: iload 9
      // 687a: bipush 8
      // 687c: imul
      // 687d: iadd
      // 687e: sipush 202
      // 6881: bipush 8
      // 6883: iload 9
      // 6885: isub
      // 6886: iadd
      // 6887: sipush 466
      // 688a: iload 9
      // 688c: bipush 8
      // 688e: imul
      // 688f: iadd
      // 6890: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6893: iinc 9 1
      // 6896: goto 6862
      // 689b: aload 0
      // 689c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 689f: new java/awt/Font
      // 68a2: dup
      // 68a3: ldc_w "Arial"
      // 68a6: bipush 1
      // 68a7: bipush 12
      // 68a9: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 68ac: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 68af: aload 0
      // 68b0: aload 0
      // 68b1: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 68b4: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 68b7: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 68ba: aload 0
      // 68bb: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 68be: new java/awt/Color
      // 68c1: dup
      // 68c2: bipush 0
      // 68c3: bipush 0
      // 68c4: bipush 0
      // 68c5: invokespecial java/awt/Color.<init> (III)V
      // 68c8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 68cb: aload 0
      // 68cc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 68cf: ldc_w "Ground Texture"
      // 68d2: sipush 257
      // 68d5: sipush 471
      // 68d8: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 68db: bipush 0
      // 68dc: istore 9
      // 68de: iload 9
      // 68e0: sipush 150
      // 68e3: if_icmplt 68eb
      // 68e6: goto 6925
      // 68eb: aload 0
      // 68ec: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 68ef: iload 9
      // 68f1: i2f
      // 68f2: ldc_w 0.006667
      // 68f5: fmul
      // 68f6: fconst_1
      // 68f7: fconst_1
      // 68f8: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 68fb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 68fe: aload 0
      // 68ff: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6902: bipush 32
      // 6904: iload 9
      // 6906: iadd
      // 6907: sipush 225
      // 690a: iadd
      // 690b: sipush 477
      // 690e: bipush 32
      // 6910: iload 9
      // 6912: iadd
      // 6913: sipush 225
      // 6916: iadd
      // 6917: sipush 484
      // 691a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 691d: iinc 9 1
      // 6920: goto 68de
      // 6925: bipush 0
      // 6926: istore 9
      // 6928: iload 9
      // 692a: sipush 150
      // 692d: if_icmplt 6935
      // 6930: goto 697c
      // 6935: aload 0
      // 6936: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6939: aload 0
      // 693a: getfield StageMaker.hsb [[F
      // 693d: bipush 1
      // 693e: aaload
      // 693f: bipush 0
      // 6940: faload
      // 6941: iload 9
      // 6943: i2f
      // 6944: ldc_w 0.006667
      // 6947: fmul
      // 6948: iload 9
      // 694a: i2f
      // 694b: ldc_w 0.006667
      // 694e: fmul
      // 694f: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 6952: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6955: aload 0
      // 6956: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6959: bipush 32
      // 695b: iload 9
      // 695d: iadd
      // 695e: sipush 225
      // 6961: iadd
      // 6962: sipush 493
      // 6965: bipush 32
      // 6967: iload 9
      // 6969: iadd
      // 696a: sipush 225
      // 696d: iadd
      // 696e: sipush 500
      // 6971: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6974: iinc 9 1
      // 6977: goto 6928
      // 697c: aload 0
      // 697d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6980: new java/awt/Font
      // 6983: dup
      // 6984: ldc_w "Arial"
      // 6987: bipush 0
      // 6988: bipush 11
      // 698a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 698d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6990: aload 0
      // 6991: aload 0
      // 6992: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6995: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6998: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 699b: aload 0
      // 699c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 699f: new java/awt/Color
      // 69a2: dup
      // 69a3: bipush 0
      // 69a4: bipush 0
      // 69a5: bipush 0
      // 69a6: invokespecial java/awt/Color.<init> (III)V
      // 69a9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 69ac: aload 0
      // 69ad: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 69b0: ldc_w "Blend:"
      // 69b3: sipush 257
      // 69b6: sipush 523
      // 69b9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 69bc: aload 0
      // 69bd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 69c0: new java/awt/Color
      // 69c3: dup
      // 69c4: bipush 0
      // 69c5: bipush 0
      // 69c6: bipush 0
      // 69c7: invokespecial java/awt/Color.<init> (III)V
      // 69ca: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 69cd: aload 0
      // 69ce: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 69d1: sipush 295
      // 69d4: sipush 516
      // 69d7: bipush 112
      // 69d9: bipush 2
      // 69da: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 69dd: aload 0
      // 69de: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 69e1: sipush 295
      // 69e4: sipush 522
      // 69e7: bipush 112
      // 69e9: bipush 2
      // 69ea: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 69ed: fconst_0
      // 69ee: fstore 7
      // 69f0: sipush 255
      // 69f3: istore 8
      // 69f5: bipush 0
      // 69f6: istore 9
      // 69f8: iload 9
      // 69fa: bipush 112
      // 69fc: if_icmplt 6a04
      // 69ff: goto 6a7b
      // 6a04: ldc_w 255.0
      // 6a07: fload 7
      // 6a09: fconst_1
      // 6a0a: fadd
      // 6a0b: fdiv
      // 6a0c: f2i
      // 6a0d: istore 8
      // 6a0f: iload 8
      // 6a11: sipush 255
      // 6a14: if_icmpgt 6a1c
      // 6a17: goto 6a26
      // 6a1c: sipush 255
      // 6a1f: istore 8
      // 6a21: goto 6a26
      // 6a26: iload 8
      // 6a28: iflt 6a30
      // 6a2b: goto 6a38
      // 6a30: bipush 0
      // 6a31: istore 8
      // 6a33: goto 6a38
      // 6a38: fload 7
      // 6a3a: ldc_w 0.02
      // 6a3d: fadd
      // 6a3e: fstore 7
      // 6a40: aload 0
      // 6a41: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6a44: new java/awt/Color
      // 6a47: dup
      // 6a48: iload 8
      // 6a4a: iload 8
      // 6a4c: iload 8
      // 6a4e: invokespecial java/awt/Color.<init> (III)V
      // 6a51: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6a54: aload 0
      // 6a55: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6a58: bipush 70
      // 6a5a: iload 9
      // 6a5c: iadd
      // 6a5d: sipush 225
      // 6a60: iadd
      // 6a61: sipush 518
      // 6a64: bipush 70
      // 6a66: iload 9
      // 6a68: iadd
      // 6a69: sipush 225
      // 6a6c: iadd
      // 6a6d: sipush 521
      // 6a70: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6a73: iinc 9 1
      // 6a76: goto 69f8
      // 6a7b: bipush 0
      // 6a7c: istore 9
      // 6a7e: iload 9
      // 6a80: bipush 2
      // 6a81: if_icmplt 6a89
      // 6a84: goto 7057
      // 6a89: bipush 3
      // 6a8a: istore 10
      // 6a8c: iload 9
      // 6a8e: bipush 1
      // 6a8f: if_icmpeq 6a97
      // 6a92: goto 6a9f
      // 6a97: bipush 2
      // 6a98: istore 10
      // 6a9a: goto 6a9f
      // 6a9f: bipush 0
      // 6aa0: istore 11
      // 6aa2: iload 11
      // 6aa4: iload 10
      // 6aa6: if_icmplt 6aae
      // 6aa9: goto 6eaa
      // 6aae: iload 11
      // 6ab0: istore 12
      // 6ab2: iload 11
      // 6ab4: bipush 1
      // 6ab5: if_icmpeq 6abd
      // 6ab8: goto 6ac5
      // 6abd: bipush 2
      // 6abe: istore 12
      // 6ac0: goto 6ac5
      // 6ac5: iload 11
      // 6ac7: bipush 2
      // 6ac8: if_icmpeq 6ad0
      // 6acb: goto 6ad8
      // 6ad0: bipush 1
      // 6ad1: istore 12
      // 6ad3: goto 6ad8
      // 6ad8: aload 0
      // 6ad9: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6adc: new java/awt/Color
      // 6adf: dup
      // 6ae0: bipush 0
      // 6ae1: bipush 0
      // 6ae2: bipush 0
      // 6ae3: invokespecial java/awt/Color.<init> (III)V
      // 6ae6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6ae9: aload 0
      // 6aea: getfield StageMaker.hsb [[F
      // 6aed: iload 9
      // 6aef: aaload
      // 6af0: iload 12
      // 6af2: faload
      // 6af3: ldc_w 150.0
      // 6af6: fmul
      // 6af7: fstore 13
      // 6af9: iload 11
      // 6afb: bipush 1
      // 6afc: if_icmpeq 6b04
      // 6aff: goto 6b2b
      // 6b04: iload 9
      // 6b06: ifeq 6b0e
      // 6b09: goto 6b2b
      // 6b0e: ldc_w 0.75
      // 6b11: fstore 14
      // 6b13: aload 0
      // 6b14: getfield StageMaker.hsb [[F
      // 6b17: iload 9
      // 6b19: aaload
      // 6b1a: iload 12
      // 6b1c: faload
      // 6b1d: fload 14
      // 6b1f: fsub
      // 6b20: ldc_w 0.001667
      // 6b23: fdiv
      // 6b24: fstore 13
      // 6b26: goto 6b2b
      // 6b2b: iload 11
      // 6b2d: bipush 2
      // 6b2e: if_icmpeq 6b36
      // 6b31: goto 6b55
      // 6b36: iload 9
      // 6b38: ifeq 6b40
      // 6b3b: goto 6b55
      // 6b40: aload 0
      // 6b41: getfield StageMaker.hsb [[F
      // 6b44: iload 9
      // 6b46: aaload
      // 6b47: iload 12
      // 6b49: faload
      // 6b4a: ldc_w 0.003333
      // 6b4d: fdiv
      // 6b4e: fstore 13
      // 6b50: goto 6b55
      // 6b55: fload 13
      // 6b57: fconst_0
      // 6b58: fcmpg
      // 6b59: iflt 6b61
      // 6b5c: goto 6b69
      // 6b61: fconst_0
      // 6b62: fstore 13
      // 6b64: goto 6b69
      // 6b69: fload 13
      // 6b6b: ldc_w 150.0
      // 6b6e: fcmpl
      // 6b6f: ifgt 6b77
      // 6b72: goto 6b81
      // 6b77: ldc_w 150.0
      // 6b7a: fstore 13
      // 6b7c: goto 6b81
      // 6b81: aload 0
      // 6b82: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6b85: bipush 32
      // 6b87: sipush 225
      // 6b8a: iload 9
      // 6b8c: imul
      // 6b8d: iadd
      // 6b8e: i2f
      // 6b8f: fload 13
      // 6b91: fadd
      // 6b92: f2i
      // 6b93: sipush 467
      // 6b96: iload 11
      // 6b98: bipush 16
      // 6b9a: imul
      // 6b9b: iadd
      // 6b9c: bipush 10
      // 6b9e: iload 9
      // 6ba0: imul
      // 6ba1: iadd
      // 6ba2: bipush 32
      // 6ba4: sipush 225
      // 6ba7: iload 9
      // 6ba9: imul
      // 6baa: iadd
      // 6bab: i2f
      // 6bac: fload 13
      // 6bae: fadd
      // 6baf: f2i
      // 6bb0: sipush 474
      // 6bb3: iload 11
      // 6bb5: bipush 16
      // 6bb7: imul
      // 6bb8: iadd
      // 6bb9: bipush 10
      // 6bbb: iload 9
      // 6bbd: imul
      // 6bbe: iadd
      // 6bbf: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6bc2: aload 0
      // 6bc3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6bc6: bipush 33
      // 6bc8: sipush 225
      // 6bcb: iload 9
      // 6bcd: imul
      // 6bce: iadd
      // 6bcf: i2f
      // 6bd0: fload 13
      // 6bd2: fadd
      // 6bd3: f2i
      // 6bd4: sipush 467
      // 6bd7: iload 11
      // 6bd9: bipush 16
      // 6bdb: imul
      // 6bdc: iadd
      // 6bdd: bipush 10
      // 6bdf: iload 9
      // 6be1: imul
      // 6be2: iadd
      // 6be3: bipush 33
      // 6be5: sipush 225
      // 6be8: iload 9
      // 6bea: imul
      // 6beb: iadd
      // 6bec: i2f
      // 6bed: fload 13
      // 6bef: fadd
      // 6bf0: f2i
      // 6bf1: sipush 474
      // 6bf4: iload 11
      // 6bf6: bipush 16
      // 6bf8: imul
      // 6bf9: iadd
      // 6bfa: bipush 10
      // 6bfc: iload 9
      // 6bfe: imul
      // 6bff: iadd
      // 6c00: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6c03: aload 0
      // 6c04: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6c07: bipush 31
      // 6c09: sipush 225
      // 6c0c: iload 9
      // 6c0e: imul
      // 6c0f: iadd
      // 6c10: i2f
      // 6c11: fload 13
      // 6c13: fadd
      // 6c14: f2i
      // 6c15: sipush 475
      // 6c18: iload 11
      // 6c1a: bipush 16
      // 6c1c: imul
      // 6c1d: iadd
      // 6c1e: bipush 10
      // 6c20: iload 9
      // 6c22: imul
      // 6c23: iadd
      // 6c24: bipush 4
      // 6c25: bipush 2
      // 6c26: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6c29: aload 0
      // 6c2a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6c2d: bipush 30
      // 6c2f: sipush 225
      // 6c32: iload 9
      // 6c34: imul
      // 6c35: iadd
      // 6c36: i2f
      // 6c37: fload 13
      // 6c39: fadd
      // 6c3a: f2i
      // 6c3b: sipush 477
      // 6c3e: iload 11
      // 6c40: bipush 16
      // 6c42: imul
      // 6c43: iadd
      // 6c44: bipush 10
      // 6c46: iload 9
      // 6c48: imul
      // 6c49: iadd
      // 6c4a: bipush 35
      // 6c4c: sipush 225
      // 6c4f: iload 9
      // 6c51: imul
      // 6c52: iadd
      // 6c53: i2f
      // 6c54: fload 13
      // 6c56: fadd
      // 6c57: f2i
      // 6c58: sipush 477
      // 6c5b: iload 11
      // 6c5d: bipush 16
      // 6c5f: imul
      // 6c60: iadd
      // 6c61: bipush 10
      // 6c63: iload 9
      // 6c65: imul
      // 6c66: iadd
      // 6c67: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6c6a: aload 0
      // 6c6b: getfield StageMaker.xm I
      // 6c6e: bipush 29
      // 6c70: sipush 225
      // 6c73: iload 9
      // 6c75: imul
      // 6c76: iadd
      // 6c77: if_icmpgt 6c7f
      // 6c7a: goto 6cf5
      // 6c7f: aload 0
      // 6c80: getfield StageMaker.xm I
      // 6c83: sipush 185
      // 6c86: sipush 225
      // 6c89: iload 9
      // 6c8b: imul
      // 6c8c: iadd
      // 6c8d: if_icmplt 6c95
      // 6c90: goto 6cf5
      // 6c95: aload 0
      // 6c96: getfield StageMaker.ym I
      // 6c99: sipush 468
      // 6c9c: iload 11
      // 6c9e: bipush 16
      // 6ca0: imul
      // 6ca1: iadd
      // 6ca2: bipush 10
      // 6ca4: iload 9
      // 6ca6: imul
      // 6ca7: iadd
      // 6ca8: if_icmpgt 6cb0
      // 6cab: goto 6cf5
      // 6cb0: aload 0
      // 6cb1: getfield StageMaker.ym I
      // 6cb4: sipush 477
      // 6cb7: iload 11
      // 6cb9: bipush 16
      // 6cbb: imul
      // 6cbc: iadd
      // 6cbd: bipush 10
      // 6cbf: iload 9
      // 6cc1: imul
      // 6cc2: iadd
      // 6cc3: if_icmplt 6ccb
      // 6cc6: goto 6cf5
      // 6ccb: aload 0
      // 6ccc: getfield StageMaker.mouses I
      // 6ccf: bipush 1
      // 6cd0: if_icmpeq 6cd8
      // 6cd3: goto 6cf5
      // 6cd8: aload 0
      // 6cd9: getfield StageMaker.mouseon I
      // 6cdc: bipush -1
      // 6cdd: if_icmpeq 6ce5
      // 6ce0: goto 6cf5
      // 6ce5: aload 0
      // 6ce6: iload 11
      // 6ce8: iload 9
      // 6cea: bipush 3
      // 6ceb: imul
      // 6cec: iadd
      // 6ced: putfield StageMaker.mouseon I
      // 6cf0: goto 6cf5
      // 6cf5: aload 0
      // 6cf6: getfield StageMaker.mouseon I
      // 6cf9: iload 11
      // 6cfb: iload 9
      // 6cfd: bipush 3
      // 6cfe: imul
      // 6cff: iadd
      // 6d00: if_icmpeq 6d08
      // 6d03: goto 6ea2
      // 6d08: aload 0
      // 6d09: getfield StageMaker.hsb [[F
      // 6d0c: iload 9
      // 6d0e: aaload
      // 6d0f: iload 12
      // 6d11: aload 0
      // 6d12: getfield StageMaker.xm I
      // 6d15: bipush 32
      // 6d17: sipush 225
      // 6d1a: iload 9
      // 6d1c: imul
      // 6d1d: iadd
      // 6d1e: isub
      // 6d1f: i2f
      // 6d20: ldc_w 0.006667
      // 6d23: fmul
      // 6d24: fastore
      // 6d25: iload 11
      // 6d27: bipush 1
      // 6d28: if_icmpeq 6d30
      // 6d2b: goto 6d9b
      // 6d30: iload 9
      // 6d32: bipush 1
      // 6d33: if_icmpeq 6d3b
      // 6d36: goto 6d9b
      // 6d3b: aload 0
      // 6d3c: getfield StageMaker.hsb [[F
      // 6d3f: iload 9
      // 6d41: aaload
      // 6d42: bipush 1
      // 6d43: aload 0
      // 6d44: getfield StageMaker.xm I
      // 6d47: bipush 32
      // 6d49: sipush 225
      // 6d4c: iload 9
      // 6d4e: imul
      // 6d4f: iadd
      // 6d50: isub
      // 6d51: i2f
      // 6d52: ldc_w 0.006667
      // 6d55: fmul
      // 6d56: fastore
      // 6d57: aload 0
      // 6d58: getfield StageMaker.hsb [[F
      // 6d5b: iload 9
      // 6d5d: aaload
      // 6d5e: bipush 1
      // 6d5f: faload
      // 6d60: fconst_1
      // 6d61: fcmpl
      // 6d62: ifgt 6d6a
      // 6d65: goto 6d79
      // 6d6a: aload 0
      // 6d6b: getfield StageMaker.hsb [[F
      // 6d6e: iload 9
      // 6d70: aaload
      // 6d71: bipush 1
      // 6d72: fconst_1
      // 6d73: fastore
      // 6d74: goto 6d79
      // 6d79: aload 0
      // 6d7a: getfield StageMaker.hsb [[F
      // 6d7d: iload 9
      // 6d7f: aaload
      // 6d80: bipush 1
      // 6d81: faload
      // 6d82: fconst_0
      // 6d83: fcmpg
      // 6d84: iflt 6d8c
      // 6d87: goto 6d9b
      // 6d8c: aload 0
      // 6d8d: getfield StageMaker.hsb [[F
      // 6d90: iload 9
      // 6d92: aaload
      // 6d93: bipush 1
      // 6d94: fconst_0
      // 6d95: fastore
      // 6d96: goto 6d9b
      // 6d9b: iload 11
      // 6d9d: bipush 1
      // 6d9e: if_icmpeq 6da6
      // 6da1: goto 6e00
      // 6da6: iload 9
      // 6da8: ifeq 6db0
      // 6dab: goto 6e00
      // 6db0: ldc_w 0.75
      // 6db3: fstore 14
      // 6db5: aload 0
      // 6db6: getfield StageMaker.hsb [[F
      // 6db9: iload 9
      // 6dbb: aaload
      // 6dbc: iload 12
      // 6dbe: fload 14
      // 6dc0: aload 0
      // 6dc1: getfield StageMaker.xm I
      // 6dc4: bipush 32
      // 6dc6: sipush 225
      // 6dc9: iload 9
      // 6dcb: imul
      // 6dcc: iadd
      // 6dcd: isub
      // 6dce: i2f
      // 6dcf: ldc_w 0.001667
      // 6dd2: fmul
      // 6dd3: fadd
      // 6dd4: fastore
      // 6dd5: aload 0
      // 6dd6: getfield StageMaker.hsb [[F
      // 6dd9: iload 9
      // 6ddb: aaload
      // 6ddc: iload 12
      // 6dde: faload
      // 6ddf: fload 14
      // 6de1: fcmpg
      // 6de2: iflt 6dea
      // 6de5: goto 6dfb
      // 6dea: aload 0
      // 6deb: getfield StageMaker.hsb [[F
      // 6dee: iload 9
      // 6df0: aaload
      // 6df1: iload 12
      // 6df3: fload 14
      // 6df5: fastore
      // 6df6: goto 6dfb
      // 6dfb: goto 6e00
      // 6e00: iload 11
      // 6e02: bipush 2
      // 6e03: if_icmpeq 6e0b
      // 6e06: goto 6e5a
      // 6e0b: iload 9
      // 6e0d: ifeq 6e15
      // 6e10: goto 6e5a
      // 6e15: aload 0
      // 6e16: getfield StageMaker.hsb [[F
      // 6e19: iload 9
      // 6e1b: aaload
      // 6e1c: iload 12
      // 6e1e: aload 0
      // 6e1f: getfield StageMaker.xm I
      // 6e22: bipush 32
      // 6e24: sipush 225
      // 6e27: iload 9
      // 6e29: imul
      // 6e2a: iadd
      // 6e2b: isub
      // 6e2c: i2f
      // 6e2d: ldc_w 0.003333
      // 6e30: fmul
      // 6e31: fastore
      // 6e32: aload 0
      // 6e33: getfield StageMaker.hsb [[F
      // 6e36: iload 9
      // 6e38: aaload
      // 6e39: iload 12
      // 6e3b: faload
      // 6e3c: f2d
      // 6e3d: ldc2_w 0.5
      // 6e40: dcmpl
      // 6e41: ifgt 6e49
      // 6e44: goto 6e5a
      // 6e49: aload 0
      // 6e4a: getfield StageMaker.hsb [[F
      // 6e4d: iload 9
      // 6e4f: aaload
      // 6e50: iload 12
      // 6e52: ldc 0.5
      // 6e54: fastore
      // 6e55: goto 6e5a
      // 6e5a: aload 0
      // 6e5b: getfield StageMaker.hsb [[F
      // 6e5e: iload 9
      // 6e60: aaload
      // 6e61: iload 12
      // 6e63: faload
      // 6e64: fconst_1
      // 6e65: fcmpl
      // 6e66: ifgt 6e6e
      // 6e69: goto 6e7e
      // 6e6e: aload 0
      // 6e6f: getfield StageMaker.hsb [[F
      // 6e72: iload 9
      // 6e74: aaload
      // 6e75: iload 12
      // 6e77: fconst_1
      // 6e78: fastore
      // 6e79: goto 6e7e
      // 6e7e: aload 0
      // 6e7f: getfield StageMaker.hsb [[F
      // 6e82: iload 9
      // 6e84: aaload
      // 6e85: iload 12
      // 6e87: faload
      // 6e88: fconst_0
      // 6e89: fcmpg
      // 6e8a: iflt 6e92
      // 6e8d: goto 6ea2
      // 6e92: aload 0
      // 6e93: getfield StageMaker.hsb [[F
      // 6e96: iload 9
      // 6e98: aaload
      // 6e99: iload 12
      // 6e9b: fconst_0
      // 6e9c: fastore
      // 6e9d: goto 6ea2
      // 6ea2: iinc 11 1
      // 6ea5: goto 6aa2
      // 6eaa: aload 0
      // 6eab: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6eae: new java/awt/Color
      // 6eb1: dup
      // 6eb2: bipush 0
      // 6eb3: bipush 0
      // 6eb4: bipush 0
      // 6eb5: invokespecial java/awt/Color.<init> (III)V
      // 6eb8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6ebb: aload 0
      // 6ebc: getfield StageMaker.texture [I
      // 6ebf: bipush 3
      // 6ec0: iaload
      // 6ec1: bipush 20
      // 6ec3: isub
      // 6ec4: i2f
      // 6ec5: ldc_w 2.8
      // 6ec8: fmul
      // 6ec9: fstore 11
      // 6ecb: iload 9
      // 6ecd: ifeq 6ed5
      // 6ed0: goto 6ee7
      // 6ed5: aload 0
      // 6ed6: getfield StageMaker.cldd [I
      // 6ed9: bipush 3
      // 6eda: iaload
      // 6edb: i2f
      // 6edc: ldc_w 11.2
      // 6edf: fmul
      // 6ee0: fstore 11
      // 6ee2: goto 6ee7
      // 6ee7: fload 11
      // 6ee9: fconst_0
      // 6eea: fcmpg
      // 6eeb: iflt 6ef3
      // 6eee: goto 6efb
      // 6ef3: fconst_0
      // 6ef4: fstore 11
      // 6ef6: goto 6efb
      // 6efb: fload 11
      // 6efd: ldc_w 112.0
      // 6f00: fcmpl
      // 6f01: ifgt 6f09
      // 6f04: goto 6f13
      // 6f09: ldc_w 112.0
      // 6f0c: fstore 11
      // 6f0e: goto 6f13
      // 6f13: aload 0
      // 6f14: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6f17: bipush 70
      // 6f19: sipush 225
      // 6f1c: iload 9
      // 6f1e: imul
      // 6f1f: iadd
      // 6f20: i2f
      // 6f21: fload 11
      // 6f23: fadd
      // 6f24: f2i
      // 6f25: sipush 522
      // 6f28: bipush 6
      // 6f2a: iload 9
      // 6f2c: imul
      // 6f2d: isub
      // 6f2e: bipush 70
      // 6f30: sipush 225
      // 6f33: iload 9
      // 6f35: imul
      // 6f36: iadd
      // 6f37: i2f
      // 6f38: fload 11
      // 6f3a: fadd
      // 6f3b: f2i
      // 6f3c: sipush 529
      // 6f3f: bipush 6
      // 6f41: iload 9
      // 6f43: imul
      // 6f44: isub
      // 6f45: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6f48: aload 0
      // 6f49: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6f4c: bipush 71
      // 6f4e: sipush 225
      // 6f51: iload 9
      // 6f53: imul
      // 6f54: iadd
      // 6f55: i2f
      // 6f56: fload 11
      // 6f58: fadd
      // 6f59: f2i
      // 6f5a: sipush 522
      // 6f5d: bipush 6
      // 6f5f: iload 9
      // 6f61: imul
      // 6f62: isub
      // 6f63: bipush 71
      // 6f65: sipush 225
      // 6f68: iload 9
      // 6f6a: imul
      // 6f6b: iadd
      // 6f6c: i2f
      // 6f6d: fload 11
      // 6f6f: fadd
      // 6f70: f2i
      // 6f71: sipush 529
      // 6f74: bipush 6
      // 6f76: iload 9
      // 6f78: imul
      // 6f79: isub
      // 6f7a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6f7d: aload 0
      // 6f7e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6f81: bipush 69
      // 6f83: sipush 225
      // 6f86: iload 9
      // 6f88: imul
      // 6f89: iadd
      // 6f8a: i2f
      // 6f8b: fload 11
      // 6f8d: fadd
      // 6f8e: f2i
      // 6f8f: sipush 530
      // 6f92: bipush 6
      // 6f94: iload 9
      // 6f96: imul
      // 6f97: isub
      // 6f98: bipush 4
      // 6f99: bipush 2
      // 6f9a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6f9d: aload 0
      // 6f9e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 6fa1: bipush 68
      // 6fa3: sipush 225
      // 6fa6: iload 9
      // 6fa8: imul
      // 6fa9: iadd
      // 6faa: i2f
      // 6fab: fload 11
      // 6fad: fadd
      // 6fae: f2i
      // 6faf: sipush 532
      // 6fb2: bipush 6
      // 6fb4: iload 9
      // 6fb6: imul
      // 6fb7: isub
      // 6fb8: bipush 73
      // 6fba: sipush 225
      // 6fbd: iload 9
      // 6fbf: imul
      // 6fc0: iadd
      // 6fc1: i2f
      // 6fc2: fload 11
      // 6fc4: fadd
      // 6fc5: f2i
      // 6fc6: sipush 532
      // 6fc9: bipush 6
      // 6fcb: iload 9
      // 6fcd: imul
      // 6fce: isub
      // 6fcf: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6fd2: aload 0
      // 6fd3: getfield StageMaker.xm I
      // 6fd6: bipush 67
      // 6fd8: sipush 225
      // 6fdb: iload 9
      // 6fdd: imul
      // 6fde: iadd
      // 6fdf: if_icmpgt 6fe7
      // 6fe2: goto 704f
      // 6fe7: aload 0
      // 6fe8: getfield StageMaker.xm I
      // 6feb: sipush 185
      // 6fee: sipush 225
      // 6ff1: iload 9
      // 6ff3: imul
      // 6ff4: iadd
      // 6ff5: if_icmplt 6ffd
      // 6ff8: goto 704f
      // 6ffd: aload 0
      // 6ffe: getfield StageMaker.ym I
      // 7001: sipush 522
      // 7004: bipush 6
      // 7006: iload 9
      // 7008: imul
      // 7009: isub
      // 700a: if_icmpgt 7012
      // 700d: goto 704f
      // 7012: aload 0
      // 7013: getfield StageMaker.ym I
      // 7016: sipush 532
      // 7019: bipush 6
      // 701b: iload 9
      // 701d: imul
      // 701e: isub
      // 701f: if_icmplt 7027
      // 7022: goto 704f
      // 7027: aload 0
      // 7028: getfield StageMaker.mouses I
      // 702b: bipush 1
      // 702c: if_icmpeq 7034
      // 702f: goto 704f
      // 7034: aload 0
      // 7035: getfield StageMaker.mouseon I
      // 7038: bipush -1
      // 7039: if_icmpeq 7041
      // 703c: goto 704f
      // 7041: aload 0
      // 7042: bipush 6
      // 7044: iload 9
      // 7046: iadd
      // 7047: putfield StageMaker.mouseon I
      // 704a: goto 704f
      // 704f: iinc 9 1
      // 7052: goto 6a7e
      // 7057: aload 0
      // 7058: getfield StageMaker.mouseon I
      // 705b: bipush 6
      // 705d: if_icmpeq 7065
      // 7060: goto 70af
      // 7065: aload 0
      // 7066: getfield StageMaker.cldd [I
      // 7069: bipush 3
      // 706a: aload 0
      // 706b: getfield StageMaker.xm I
      // 706e: bipush 70
      // 7070: isub
      // 7071: i2f
      // 7072: ldc_w 11.2
      // 7075: fdiv
      // 7076: f2i
      // 7077: iastore
      // 7078: aload 0
      // 7079: getfield StageMaker.cldd [I
      // 707c: bipush 3
      // 707d: iaload
      // 707e: iflt 7086
      // 7081: goto 7092
      // 7086: aload 0
      // 7087: getfield StageMaker.cldd [I
      // 708a: bipush 3
      // 708b: bipush 0
      // 708c: iastore
      // 708d: goto 7092
      // 7092: aload 0
      // 7093: getfield StageMaker.cldd [I
      // 7096: bipush 3
      // 7097: iaload
      // 7098: bipush 10
      // 709a: if_icmpgt 70a2
      // 709d: goto 70af
      // 70a2: aload 0
      // 70a3: getfield StageMaker.cldd [I
      // 70a6: bipush 3
      // 70a7: bipush 10
      // 70a9: iastore
      // 70aa: goto 70af
      // 70af: aload 0
      // 70b0: getfield StageMaker.mouseon I
      // 70b3: bipush 7
      // 70b5: if_icmpeq 70bd
      // 70b8: goto 7112
      // 70bd: aload 0
      // 70be: getfield StageMaker.texture [I
      // 70c1: bipush 3
      // 70c2: aload 0
      // 70c3: getfield StageMaker.xm I
      // 70c6: bipush 70
      // 70c8: isub
      // 70c9: sipush 225
      // 70cc: isub
      // 70cd: i2d
      // 70ce: ldc2_w 2.8
      // 70d1: ddiv
      // 70d2: ldc2_w 20.0
      // 70d5: dadd
      // 70d6: d2i
      // 70d7: iastore
      // 70d8: aload 0
      // 70d9: getfield StageMaker.texture [I
      // 70dc: bipush 3
      // 70dd: iaload
      // 70de: bipush 20
      // 70e0: if_icmplt 70e8
      // 70e3: goto 70f5
      // 70e8: aload 0
      // 70e9: getfield StageMaker.texture [I
      // 70ec: bipush 3
      // 70ed: bipush 20
      // 70ef: iastore
      // 70f0: goto 70f5
      // 70f5: aload 0
      // 70f6: getfield StageMaker.texture [I
      // 70f9: bipush 3
      // 70fa: iaload
      // 70fb: bipush 60
      // 70fd: if_icmpgt 7105
      // 7100: goto 7112
      // 7105: aload 0
      // 7106: getfield StageMaker.texture [I
      // 7109: bipush 3
      // 710a: bipush 60
      // 710c: iastore
      // 710d: goto 7112
      // 7112: aload 0
      // 7113: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7116: new java/awt/Color
      // 7119: dup
      // 711a: bipush 0
      // 711b: sipush 128
      // 711e: sipush 255
      // 7121: invokespecial java/awt/Color.<init> (III)V
      // 7124: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7127: sipush 1500
      // 712a: aload 0
      // 712b: getfield StageMaker.cldd [I
      // 712e: bipush 4
      // 712f: iaload
      // 7130: invokestatic java/lang/Math.abs (I)I
      // 7133: isub
      // 7134: i2f
      // 7135: ldc_w 15.625
      // 7138: fdiv
      // 7139: fstore 9
      // 713b: fload 9
      // 713d: ldc_w 64.0
      // 7140: fcmpl
      // 7141: ifgt 7149
      // 7144: goto 7153
      // 7149: ldc_w 64.0
      // 714c: fstore 9
      // 714e: goto 7153
      // 7153: fload 9
      // 7155: fconst_0
      // 7156: fcmpg
      // 7157: iflt 715f
      // 715a: goto 7167
      // 715f: fconst_0
      // 7160: fstore 9
      // 7162: goto 7167
      // 7167: aload 0
      // 7168: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 716b: sipush 199
      // 716e: ldc_w 465.0
      // 7171: fload 9
      // 7173: fadd
      // 7174: f2i
      // 7175: bipush 12
      // 7177: bipush 2
      // 7178: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 717b: aload 0
      // 717c: getfield StageMaker.xm I
      // 717f: sipush 197
      // 7182: if_icmpgt 718a
      // 7185: goto 71dc
      // 718a: aload 0
      // 718b: getfield StageMaker.xm I
      // 718e: sipush 213
      // 7191: if_icmplt 7199
      // 7194: goto 71dc
      // 7199: aload 0
      // 719a: getfield StageMaker.ym I
      // 719d: sipush 463
      // 71a0: if_icmpgt 71a8
      // 71a3: goto 71dc
      // 71a8: aload 0
      // 71a9: getfield StageMaker.ym I
      // 71ac: sipush 533
      // 71af: if_icmplt 71b7
      // 71b2: goto 71dc
      // 71b7: aload 0
      // 71b8: getfield StageMaker.mouses I
      // 71bb: bipush 1
      // 71bc: if_icmpeq 71c4
      // 71bf: goto 71dc
      // 71c4: aload 0
      // 71c5: getfield StageMaker.mouseon I
      // 71c8: bipush -1
      // 71c9: if_icmpeq 71d1
      // 71cc: goto 71dc
      // 71d1: aload 0
      // 71d2: bipush 8
      // 71d4: putfield StageMaker.mouseon I
      // 71d7: goto 71dc
      // 71dc: aload 0
      // 71dd: getfield StageMaker.mouseon I
      // 71e0: bipush 8
      // 71e2: if_icmpeq 71ea
      // 71e5: goto 7241
      // 71ea: aload 0
      // 71eb: getfield StageMaker.cldd [I
      // 71ee: bipush 4
      // 71ef: sipush 530
      // 71f2: aload 0
      // 71f3: getfield StageMaker.ym I
      // 71f6: isub
      // 71f7: i2f
      // 71f8: ldc_w 15.625
      // 71fb: fmul
      // 71fc: ldc_w 500.0
      // 71ff: fadd
      // 7200: f2i
      // 7201: ineg
      // 7202: iastore
      // 7203: aload 0
      // 7204: getfield StageMaker.cldd [I
      // 7207: bipush 4
      // 7208: iaload
      // 7209: sipush -500
      // 720c: if_icmpgt 7214
      // 720f: goto 7222
      // 7214: aload 0
      // 7215: getfield StageMaker.cldd [I
      // 7218: bipush 4
      // 7219: sipush -500
      // 721c: iastore
      // 721d: goto 7222
      // 7222: aload 0
      // 7223: getfield StageMaker.cldd [I
      // 7226: bipush 4
      // 7227: iaload
      // 7228: sipush -1500
      // 722b: if_icmplt 7233
      // 722e: goto 7241
      // 7233: aload 0
      // 7234: getfield StageMaker.cldd [I
      // 7237: bipush 4
      // 7238: sipush -1500
      // 723b: iastore
      // 723c: goto 7241
      // 7241: aload 0
      // 7242: getfield StageMaker.hsb [[F
      // 7245: bipush 0
      // 7246: aaload
      // 7247: bipush 0
      // 7248: faload
      // 7249: aload 0
      // 724a: getfield StageMaker.hsb [[F
      // 724d: bipush 0
      // 724e: aaload
      // 724f: bipush 1
      // 7250: faload
      // 7251: aload 0
      // 7252: getfield StageMaker.hsb [[F
      // 7255: bipush 0
      // 7256: aaload
      // 7257: bipush 2
      // 7258: faload
      // 7259: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 725c: astore 10
      // 725e: aload 0
      // 725f: getfield StageMaker.m LMedium;
      // 7262: aload 10
      // 7264: invokevirtual java/awt/Color.getRed ()I
      // 7267: aload 10
      // 7269: invokevirtual java/awt/Color.getGreen ()I
      // 726c: aload 10
      // 726e: invokevirtual java/awt/Color.getBlue ()I
      // 7271: aload 0
      // 7272: getfield StageMaker.cldd [I
      // 7275: bipush 3
      // 7276: iaload
      // 7277: aload 0
      // 7278: getfield StageMaker.cldd [I
      // 727b: bipush 4
      // 727c: iaload
      // 727d: invokevirtual Medium.setcloads (IIIII)V
      // 7280: aload 0
      // 7281: getfield StageMaker.cldd [I
      // 7284: bipush 0
      // 7285: aload 10
      // 7287: invokevirtual java/awt/Color.getRed ()I
      // 728a: iastore
      // 728b: aload 0
      // 728c: getfield StageMaker.cldd [I
      // 728f: bipush 1
      // 7290: aload 10
      // 7292: invokevirtual java/awt/Color.getGreen ()I
      // 7295: iastore
      // 7296: aload 0
      // 7297: getfield StageMaker.cldd [I
      // 729a: bipush 2
      // 729b: aload 10
      // 729d: invokevirtual java/awt/Color.getBlue ()I
      // 72a0: iastore
      // 72a1: aload 0
      // 72a2: getfield StageMaker.hsb [[F
      // 72a5: bipush 1
      // 72a6: aaload
      // 72a7: bipush 0
      // 72a8: faload
      // 72a9: aload 0
      // 72aa: getfield StageMaker.hsb [[F
      // 72ad: bipush 1
      // 72ae: aaload
      // 72af: bipush 1
      // 72b0: faload
      // 72b1: aload 0
      // 72b2: getfield StageMaker.hsb [[F
      // 72b5: bipush 1
      // 72b6: aaload
      // 72b7: bipush 2
      // 72b8: faload
      // 72b9: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 72bc: astore 10
      // 72be: aload 0
      // 72bf: getfield StageMaker.m LMedium;
      // 72c2: aload 10
      // 72c4: invokevirtual java/awt/Color.getRed ()I
      // 72c7: aload 10
      // 72c9: invokevirtual java/awt/Color.getGreen ()I
      // 72cc: aload 10
      // 72ce: invokevirtual java/awt/Color.getBlue ()I
      // 72d1: aload 0
      // 72d2: getfield StageMaker.texture [I
      // 72d5: bipush 3
      // 72d6: iaload
      // 72d7: invokevirtual Medium.setexture (IIII)V
      // 72da: aload 0
      // 72db: getfield StageMaker.texture [I
      // 72de: bipush 0
      // 72df: aload 10
      // 72e1: invokevirtual java/awt/Color.getRed ()I
      // 72e4: iastore
      // 72e5: aload 0
      // 72e6: getfield StageMaker.texture [I
      // 72e9: bipush 1
      // 72ea: aload 10
      // 72ec: invokevirtual java/awt/Color.getGreen ()I
      // 72ef: iastore
      // 72f0: aload 0
      // 72f1: getfield StageMaker.texture [I
      // 72f4: bipush 2
      // 72f5: aload 10
      // 72f7: invokevirtual java/awt/Color.getBlue ()I
      // 72fa: iastore
      // 72fb: aload 0
      // 72fc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 72ff: new java/awt/Font
      // 7302: dup
      // 7303: ldc_w "Arial"
      // 7306: bipush 1
      // 7307: bipush 12
      // 7309: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 730c: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 730f: aload 0
      // 7310: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7313: new java/awt/Color
      // 7316: dup
      // 7317: bipush 0
      // 7318: bipush 0
      // 7319: bipush 0
      // 731a: invokespecial java/awt/Color.<init> (III)V
      // 731d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7320: aload 0
      // 7321: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7324: ldc_w "Mountains"
      // 7327: sipush 452
      // 732a: sipush 465
      // 732d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7330: aload 0
      // 7331: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7334: new java/awt/Font
      // 7337: dup
      // 7338: ldc_w "Arial"
      // 733b: bipush 0
      // 733c: bipush 11
      // 733e: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 7341: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 7344: aload 0
      // 7345: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7348: ldc_w "Mountain Generator Key:"
      // 734b: sipush 452
      // 734e: sipush 480
      // 7351: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7354: aload 0
      // 7355: aload 0
      // 7356: getfield StageMaker.mgen Ljava/awt/TextField;
      // 7359: sipush 452
      // 735c: sipush 484
      // 735f: bipush 120
      // 7361: bipush 20
      // 7363: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // 7366: aload 0
      // 7367: getfield StageMaker.mgen Ljava/awt/TextField;
      // 736a: invokevirtual java/awt/TextField.hasFocus ()Z
      // 736d: ifne 7375
      // 7370: goto 737f
      // 7375: aload 0
      // 7376: bipush 0
      // 7377: putfield StageMaker.focuson Z
      // 737a: goto 737f
      // 737f: aload 0
      // 7380: getfield StageMaker.mgen Ljava/awt/TextField;
      // 7383: invokevirtual java/awt/TextField.isShowing ()Z
      // 7386: ifeq 738e
      // 7389: goto 739a
      // 738e: aload 0
      // 738f: getfield StageMaker.mgen Ljava/awt/TextField;
      // 7392: invokevirtual java/awt/TextField.show ()V
      // 7395: goto 739a
      // 739a: aload 0
      // 739b: ldc_w "  Generate New  "
      // 739e: sipush 512
      // 73a1: sipush 525
      // 73a4: bipush 3
      // 73a5: bipush 1
      // 73a6: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 73a9: ifne 73b1
      // 73ac: goto 7411
      // 73b1: aload 0
      // 73b2: getfield StageMaker.m LMedium;
      // 73b5: invokestatic java/lang/Math.random ()D
      // 73b8: ldc2_w 100000.0
      // 73bb: dmul
      // 73bc: d2i
      // 73bd: putfield Medium.mgen I
      // 73c0: aload 0
      // 73c1: getfield StageMaker.mgen Ljava/awt/TextField;
      // 73c4: new java/lang/StringBuilder
      // 73c7: dup
      // 73c8: invokespecial java/lang/StringBuilder.<init> ()V
      // 73cb: ldc ""
      // 73cd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 73d0: aload 0
      // 73d1: getfield StageMaker.m LMedium;
      // 73d4: getfield Medium.mgen I
      // 73d7: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 73da: ldc ""
      // 73dc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 73df: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 73e2: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 73e5: aload 0
      // 73e6: getfield StageMaker.ttstage Ljava/lang/String;
      // 73e9: ldc ""
      // 73eb: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 73ee: ifne 73f6
      // 73f1: goto 7403
      // 73f6: aload 0
      // 73f7: aload 0
      // 73f8: getfield StageMaker.tstage Ljava/lang/String;
      // 73fb: putfield StageMaker.ttstage Ljava/lang/String;
      // 73fe: goto 7403
      // 7403: aload 0
      // 7404: invokevirtual StageMaker.sortop ()V
      // 7407: aload 0
      // 7408: bipush 1
      // 7409: invokevirtual StageMaker.readstage (I)V
      // 740c: goto 7411
      // 7411: aload 0
      // 7412: getfield StageMaker.mgen Ljava/awt/TextField;
      // 7415: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 7418: new java/lang/StringBuilder
      // 741b: dup
      // 741c: invokespecial java/lang/StringBuilder.<init> ()V
      // 741f: ldc ""
      // 7421: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7424: aload 0
      // 7425: getfield StageMaker.m LMedium;
      // 7428: getfield Medium.mgen I
      // 742b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 742e: ldc ""
      // 7430: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7433: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7436: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7439: ifeq 7441
      // 743c: goto 74b1
      // 7441: aload 0
      // 7442: getfield StageMaker.mgen Ljava/awt/TextField;
      // 7445: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 7448: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 744b: invokevirtual java/lang/Integer.intValue ()I
      // 744e: istore 11
      // 7450: aload 0
      // 7451: getfield StageMaker.m LMedium;
      // 7454: iload 11
      // 7456: putfield Medium.mgen I
      // 7459: aload 0
      // 745a: getfield StageMaker.ttstage Ljava/lang/String;
      // 745d: ldc ""
      // 745f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7462: ifne 746a
      // 7465: goto 7477
      // 746a: aload 0
      // 746b: aload 0
      // 746c: getfield StageMaker.tstage Ljava/lang/String;
      // 746f: putfield StageMaker.ttstage Ljava/lang/String;
      // 7472: goto 7477
      // 7477: aload 0
      // 7478: invokevirtual StageMaker.sortop ()V
      // 747b: aload 0
      // 747c: bipush 1
      // 747d: invokevirtual StageMaker.readstage (I)V
      // 7480: goto 74b1
      // 7485: astore 11
      // 7487: aload 0
      // 7488: getfield StageMaker.mgen Ljava/awt/TextField;
      // 748b: new java/lang/StringBuilder
      // 748e: dup
      // 748f: invokespecial java/lang/StringBuilder.<init> ()V
      // 7492: ldc ""
      // 7494: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7497: aload 0
      // 7498: getfield StageMaker.m LMedium;
      // 749b: getfield Medium.mgen I
      // 749e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 74a1: ldc ""
      // 74a3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 74a6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 74a9: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 74ac: goto 74b1
      // 74b1: aload 0
      // 74b2: ldc_w " Reset "
      // 74b5: sipush 650
      // 74b8: sipush 510
      // 74bb: bipush 0
      // 74bc: bipush 1
      // 74bd: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 74c0: ifne 74c8
      // 74c3: goto 74fc
      // 74c8: aload 0
      // 74c9: getfield StageMaker.ttstage Ljava/lang/String;
      // 74cc: ldc ""
      // 74ce: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 74d1: ifeq 74d9
      // 74d4: goto 74ec
      // 74d9: aload 0
      // 74da: aload 0
      // 74db: getfield StageMaker.ttstage Ljava/lang/String;
      // 74de: putfield StageMaker.tstage Ljava/lang/String;
      // 74e1: aload 0
      // 74e2: ldc ""
      // 74e4: putfield StageMaker.ttstage Ljava/lang/String;
      // 74e7: goto 74ec
      // 74ec: aload 0
      // 74ed: bipush 1
      // 74ee: invokevirtual StageMaker.readstage (I)V
      // 74f1: aload 0
      // 74f2: bipush -2
      // 74f4: putfield StageMaker.dtabed I
      // 74f7: goto 74fc
      // 74fc: aload 0
      // 74fd: ldc_w "        Save        "
      // 7500: sipush 737
      // 7503: sipush 510
      // 7506: bipush 0
      // 7507: bipush 1
      // 7508: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 750b: ifne 7513
      // 750e: goto 7526
      // 7513: aload 0
      // 7514: invokevirtual StageMaker.sortop ()V
      // 7517: aload 0
      // 7518: ldc ""
      // 751a: putfield StageMaker.ttstage Ljava/lang/String;
      // 751d: aload 0
      // 751e: invokevirtual StageMaker.savefile ()V
      // 7521: goto 7526
      // 7526: goto 752b
      // 752b: aload 0
      // 752c: getfield StageMaker.dtab I
      // 752f: bipush 1
      // 7530: if_icmpeq 7538
      // 7533: goto 7dc7
      // 7538: aload 0
      // 7539: getfield StageMaker.dtabed I
      // 753c: aload 0
      // 753d: getfield StageMaker.dtab I
      // 7540: if_icmpne 7548
      // 7543: goto 75ad
      // 7548: bipush 0
      // 7549: istore 7
      // 754b: iload 7
      // 754d: bipush 3
      // 754e: if_icmplt 7556
      // 7551: goto 7579
      // 7556: aload 0
      // 7557: getfield StageMaker.snap [I
      // 755a: iload 7
      // 755c: aload 0
      // 755d: getfield StageMaker.m LMedium;
      // 7560: getfield Medium.snap [I
      // 7563: iload 7
      // 7565: iaload
      // 7566: i2f
      // 7567: ldc_w 1.2
      // 756a: fdiv
      // 756b: ldc_w 50.0
      // 756e: fadd
      // 756f: f2i
      // 7570: iastore
      // 7571: iinc 7 1
      // 7574: goto 754b
      // 7579: aload 0
      // 757a: getfield StageMaker.fogn [I
      // 757d: bipush 0
      // 757e: bipush 8
      // 7580: aload 0
      // 7581: getfield StageMaker.m LMedium;
      // 7584: getfield Medium.fogd I
      // 7587: bipush 1
      // 7588: iadd
      // 7589: bipush 2
      // 758a: idiv
      // 758b: bipush 1
      // 758c: isub
      // 758d: isub
      // 758e: bipush 20
      // 7590: imul
      // 7591: iastore
      // 7592: aload 0
      // 7593: getfield StageMaker.fogn [I
      // 7596: bipush 1
      // 7597: aload 0
      // 7598: getfield StageMaker.m LMedium;
      // 759b: getfield Medium.fade [I
      // 759e: bipush 0
      // 759f: iaload
      // 75a0: sipush 5000
      // 75a3: isub
      // 75a4: bipush 30
      // 75a6: idiv
      // 75a7: iastore
      // 75a8: goto 75ad
      // 75ad: aload 0
      // 75ae: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 75b1: new java/awt/Color
      // 75b4: dup
      // 75b5: bipush 0
      // 75b6: bipush 0
      // 75b7: bipush 0
      // 75b8: invokespecial java/awt/Color.<init> (III)V
      // 75bb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 75be: aload 0
      // 75bf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 75c2: ldc_w "Atmosphere RGB Mask"
      // 75c5: bipush 20
      // 75c7: sipush 461
      // 75ca: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 75cd: aload 0
      // 75ce: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 75d1: new java/awt/Color
      // 75d4: dup
      // 75d5: sipush 128
      // 75d8: sipush 128
      // 75db: sipush 128
      // 75de: invokespecial java/awt/Color.<init> (III)V
      // 75e1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 75e4: aload 0
      // 75e5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 75e8: bipush 10
      // 75ea: sipush 457
      // 75ed: bipush 17
      // 75ef: sipush 457
      // 75f2: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 75f5: aload 0
      // 75f6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 75f9: sipush 260
      // 75fc: sipush 457
      // 75ff: sipush 152
      // 7602: sipush 457
      // 7605: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7608: aload 0
      // 7609: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 760c: bipush 10
      // 760e: sipush 457
      // 7611: bipush 10
      // 7613: sipush 546
      // 7616: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7619: aload 0
      // 761a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 761d: sipush 260
      // 7620: sipush 457
      // 7623: sipush 260
      // 7626: sipush 527
      // 7629: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 762c: aload 0
      // 762d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7630: sipush 260
      // 7633: sipush 527
      // 7636: sipush 360
      // 7639: sipush 527
      // 763c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 763f: aload 0
      // 7640: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7643: bipush 10
      // 7645: sipush 546
      // 7648: sipush 360
      // 764b: sipush 546
      // 764e: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7651: aload 0
      // 7652: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7655: sipush 360
      // 7658: sipush 527
      // 765b: sipush 360
      // 765e: sipush 546
      // 7661: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7664: bipush 3
      // 7665: anewarray 36
      // 7668: dup
      // 7669: bipush 0
      // 766a: ldc_w "Red"
      // 766d: aastore
      // 766e: dup
      // 766f: bipush 1
      // 7670: ldc_w "Green"
      // 7673: aastore
      // 7674: dup
      // 7675: bipush 2
      // 7676: ldc_w "Blue"
      // 7679: aastore
      // 767a: astore 7
      // 767c: bipush 3
      // 767d: newarray 10
      // 767f: dup
      // 7680: bipush 0
      // 7681: bipush 32
      // 7683: iastore
      // 7684: dup
      // 7685: bipush 1
      // 7686: bipush 20
      // 7688: iastore
      // 7689: dup
      // 768a: bipush 2
      // 768b: bipush 29
      // 768d: iastore
      // 768e: astore 8
      // 7690: bipush 38
      // 7692: istore 9
      // 7694: bipush -70
      // 7696: istore 10
      // 7698: bipush 0
      // 7699: istore 11
      // 769b: iload 11
      // 769d: bipush 3
      // 769e: if_icmplt 76a6
      // 76a1: goto 790d
      // 76a6: aload 0
      // 76a7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 76aa: new java/awt/Color
      // 76ad: dup
      // 76ae: bipush 0
      // 76af: bipush 0
      // 76b0: bipush 0
      // 76b1: invokespecial java/awt/Color.<init> (III)V
      // 76b4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 76b7: aload 0
      // 76b8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 76bb: new java/lang/StringBuilder
      // 76be: dup
      // 76bf: invokespecial java/lang/StringBuilder.<init> ()V
      // 76c2: ldc ""
      // 76c4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 76c7: aload 7
      // 76c9: iload 11
      // 76cb: aaload
      // 76cc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 76cf: ldc_w " :"
      // 76d2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 76d5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 76d8: aload 8
      // 76da: iload 11
      // 76dc: iaload
      // 76dd: sipush 447
      // 76e0: iload 11
      // 76e2: bipush 24
      // 76e4: imul
      // 76e5: iadd
      // 76e6: iload 9
      // 76e8: iadd
      // 76e9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 76ec: aload 0
      // 76ed: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 76f0: sipush 140
      // 76f3: iload 10
      // 76f5: iadd
      // 76f6: sipush 443
      // 76f9: iload 11
      // 76fb: bipush 24
      // 76fd: imul
      // 76fe: iload 9
      // 7700: iadd
      // 7701: iadd
      // 7702: sipush 230
      // 7705: iload 10
      // 7707: iadd
      // 7708: sipush 443
      // 770b: iload 11
      // 770d: bipush 24
      // 770f: imul
      // 7710: iadd
      // 7711: iload 9
      // 7713: iadd
      // 7714: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7717: bipush 1
      // 7718: istore 12
      // 771a: iload 12
      // 771c: bipush 10
      // 771e: if_icmplt 7726
      // 7721: goto 776b
      // 7726: aload 0
      // 7727: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 772a: sipush 140
      // 772d: bipush 10
      // 772f: iload 12
      // 7731: imul
      // 7732: iadd
      // 7733: iload 10
      // 7735: iadd
      // 7736: sipush 443
      // 7739: iload 12
      // 773b: isub
      // 773c: iload 11
      // 773e: bipush 24
      // 7740: imul
      // 7741: iadd
      // 7742: iload 9
      // 7744: iadd
      // 7745: sipush 140
      // 7748: bipush 10
      // 774a: iload 12
      // 774c: imul
      // 774d: iadd
      // 774e: iload 10
      // 7750: iadd
      // 7751: sipush 443
      // 7754: iload 12
      // 7756: iadd
      // 7757: iload 11
      // 7759: bipush 24
      // 775b: imul
      // 775c: iadd
      // 775d: iload 9
      // 775f: iadd
      // 7760: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7763: iinc 12 1
      // 7766: goto 771a
      // 776b: aload 0
      // 776c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 776f: new java/awt/Color
      // 7772: dup
      // 7773: sipush 255
      // 7776: bipush 0
      // 7777: bipush 0
      // 7778: invokespecial java/awt/Color.<init> (III)V
      // 777b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 777e: aload 0
      // 777f: getfield StageMaker.snap [I
      // 7782: iload 11
      // 7784: iaload
      // 7785: i2f
      // 7786: ldc_w 1.1111
      // 7789: fdiv
      // 778a: ldc_w 10.0
      // 778d: fdiv
      // 778e: f2i
      // 778f: istore 12
      // 7791: aload 0
      // 7792: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7795: sipush 138
      // 7798: aload 0
      // 7799: getfield StageMaker.snap [I
      // 779c: iload 11
      // 779e: iaload
      // 779f: i2f
      // 77a0: ldc_w 1.1111
      // 77a3: fdiv
      // 77a4: f2i
      // 77a5: iadd
      // 77a6: iload 10
      // 77a8: iadd
      // 77a9: sipush 443
      // 77ac: iload 12
      // 77ae: isub
      // 77af: iload 11
      // 77b1: bipush 24
      // 77b3: imul
      // 77b4: iadd
      // 77b5: iload 9
      // 77b7: iadd
      // 77b8: bipush 5
      // 77b9: iload 12
      // 77bb: bipush 2
      // 77bc: imul
      // 77bd: bipush 1
      // 77be: iadd
      // 77bf: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 77c2: aload 0
      // 77c3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 77c6: new java/awt/Color
      // 77c9: dup
      // 77ca: sipush 255
      // 77cd: sipush 128
      // 77d0: bipush 0
      // 77d1: invokespecial java/awt/Color.<init> (III)V
      // 77d4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 77d7: aload 0
      // 77d8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 77db: sipush 139
      // 77de: aload 0
      // 77df: getfield StageMaker.snap [I
      // 77e2: iload 11
      // 77e4: iaload
      // 77e5: i2f
      // 77e6: ldc_w 1.1111
      // 77e9: fdiv
      // 77ea: f2i
      // 77eb: iadd
      // 77ec: iload 10
      // 77ee: iadd
      // 77ef: sipush 434
      // 77f2: iload 11
      // 77f4: bipush 24
      // 77f6: imul
      // 77f7: iadd
      // 77f8: iload 9
      // 77fa: iadd
      // 77fb: bipush 2
      // 77fc: bipush 18
      // 77fe: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 7801: aload 0
      // 7802: ldc_w " - "
      // 7805: sipush 260
      // 7808: iload 10
      // 780a: iadd
      // 780b: sipush 447
      // 780e: iload 11
      // 7810: bipush 24
      // 7812: imul
      // 7813: iadd
      // 7814: iload 9
      // 7816: iadd
      // 7817: bipush 4
      // 7818: bipush 0
      // 7819: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 781c: ifne 7824
      // 781f: goto 784b
      // 7824: aload 0
      // 7825: getfield StageMaker.snap [I
      // 7828: iload 11
      // 782a: dup2
      // 782b: iaload
      // 782c: bipush 2
      // 782d: isub
      // 782e: iastore
      // 782f: aload 0
      // 7830: getfield StageMaker.snap [I
      // 7833: iload 11
      // 7835: iaload
      // 7836: iflt 783e
      // 7839: goto 784b
      // 783e: aload 0
      // 783f: getfield StageMaker.snap [I
      // 7842: iload 11
      // 7844: bipush 0
      // 7845: iastore
      // 7846: goto 784b
      // 784b: aload 0
      // 784c: ldc_w " + "
      // 784f: sipush 300
      // 7852: iload 10
      // 7854: iadd
      // 7855: sipush 447
      // 7858: iload 11
      // 785a: bipush 24
      // 785c: imul
      // 785d: iadd
      // 785e: iload 9
      // 7860: iadd
      // 7861: bipush 4
      // 7862: bipush 0
      // 7863: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7866: ifne 786e
      // 7869: goto 7905
      // 786e: aload 0
      // 786f: getfield StageMaker.snap [I
      // 7872: bipush 0
      // 7873: iaload
      // 7874: aload 0
      // 7875: getfield StageMaker.snap [I
      // 7878: bipush 1
      // 7879: iaload
      // 787a: iadd
      // 787b: aload 0
      // 787c: getfield StageMaker.snap [I
      // 787f: bipush 2
      // 7880: iaload
      // 7881: iadd
      // 7882: sipush 200
      // 7885: if_icmpgt 788d
      // 7888: goto 78db
      // 788d: bipush 0
      // 788e: istore 13
      // 7890: iload 13
      // 7892: bipush 3
      // 7893: if_icmplt 789b
      // 7896: goto 78d6
      // 789b: iload 13
      // 789d: iload 11
      // 789f: if_icmpne 78a7
      // 78a2: goto 78ce
      // 78a7: aload 0
      // 78a8: getfield StageMaker.snap [I
      // 78ab: iload 13
      // 78ad: dup2
      // 78ae: iaload
      // 78af: bipush 1
      // 78b0: isub
      // 78b1: iastore
      // 78b2: aload 0
      // 78b3: getfield StageMaker.snap [I
      // 78b6: iload 13
      // 78b8: iaload
      // 78b9: iflt 78c1
      // 78bc: goto 78ce
      // 78c1: aload 0
      // 78c2: getfield StageMaker.snap [I
      // 78c5: iload 13
      // 78c7: bipush 0
      // 78c8: iastore
      // 78c9: goto 78ce
      // 78ce: iinc 13 1
      // 78d1: goto 7890
      // 78d6: goto 78db
      // 78db: aload 0
      // 78dc: getfield StageMaker.snap [I
      // 78df: iload 11
      // 78e1: dup2
      // 78e2: iaload
      // 78e3: bipush 2
      // 78e4: iadd
      // 78e5: iastore
      // 78e6: aload 0
      // 78e7: getfield StageMaker.snap [I
      // 78ea: iload 11
      // 78ec: iaload
      // 78ed: bipush 100
      // 78ef: if_icmpgt 78f7
      // 78f2: goto 7905
      // 78f7: aload 0
      // 78f8: getfield StageMaker.snap [I
      // 78fb: iload 11
      // 78fd: bipush 100
      // 78ff: iastore
      // 7900: goto 7905
      // 7905: iinc 11 1
      // 7908: goto 769b
      // 790d: aload 0
      // 790e: getfield StageMaker.m LMedium;
      // 7911: getfield Medium.snap [I
      // 7914: bipush 0
      // 7915: iaload
      // 7916: aload 0
      // 7917: getfield StageMaker.snap [I
      // 791a: bipush 0
      // 791b: iaload
      // 791c: i2f
      // 791d: ldc_w 1.2
      // 7920: fmul
      // 7921: ldc_w 60.0
      // 7924: fsub
      // 7925: f2i
      // 7926: if_icmpeq 792e
      // 7929: goto 7970
      // 792e: aload 0
      // 792f: getfield StageMaker.m LMedium;
      // 7932: getfield Medium.snap [I
      // 7935: bipush 1
      // 7936: iaload
      // 7937: aload 0
      // 7938: getfield StageMaker.snap [I
      // 793b: bipush 1
      // 793c: iaload
      // 793d: i2f
      // 793e: ldc_w 1.2
      // 7941: fmul
      // 7942: ldc_w 60.0
      // 7945: fsub
      // 7946: f2i
      // 7947: if_icmpeq 794f
      // 794a: goto 7970
      // 794f: aload 0
      // 7950: getfield StageMaker.m LMedium;
      // 7953: getfield Medium.snap [I
      // 7956: bipush 2
      // 7957: iaload
      // 7958: aload 0
      // 7959: getfield StageMaker.snap [I
      // 795c: bipush 2
      // 795d: iaload
      // 795e: i2f
      // 795f: ldc_w 1.2
      // 7962: fmul
      // 7963: ldc_w 60.0
      // 7966: fsub
      // 7967: f2i
      // 7968: if_icmpne 7970
      // 796b: goto 79ab
      // 7970: bipush 0
      // 7971: istore 11
      // 7973: iload 11
      // 7975: bipush 3
      // 7976: if_icmplt 797e
      // 7979: goto 79a1
      // 797e: aload 0
      // 797f: getfield StageMaker.m LMedium;
      // 7982: getfield Medium.snap [I
      // 7985: iload 11
      // 7987: aload 0
      // 7988: getfield StageMaker.snap [I
      // 798b: iload 11
      // 798d: iaload
      // 798e: i2f
      // 798f: ldc_w 1.2
      // 7992: fmul
      // 7993: ldc_w 60.0
      // 7996: fsub
      // 7997: f2i
      // 7998: iastore
      // 7999: iinc 11 1
      // 799c: goto 7973
      // 79a1: aload 0
      // 79a2: bipush 2
      // 79a3: invokevirtual StageMaker.readstage (I)V
      // 79a6: goto 79ab
      // 79ab: aload 0
      // 79ac: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 79af: new java/awt/Color
      // 79b2: dup
      // 79b3: bipush 0
      // 79b4: bipush 0
      // 79b5: bipush 0
      // 79b6: invokespecial java/awt/Color.<init> (III)V
      // 79b9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 79bc: aload 0
      // 79bd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 79c0: ldc_w "Car Lights :"
      // 79c3: sipush 265
      // 79c6: sipush 541
      // 79c9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 79cc: aload 0
      // 79cd: getfield StageMaker.snap [I
      // 79d0: bipush 0
      // 79d1: iaload
      // 79d2: aload 0
      // 79d3: getfield StageMaker.snap [I
      // 79d6: bipush 1
      // 79d7: iaload
      // 79d8: iadd
      // 79d9: aload 0
      // 79da: getfield StageMaker.snap [I
      // 79dd: bipush 2
      // 79de: iaload
      // 79df: iadd
      // 79e0: bipush 110
      // 79e2: if_icmpgt 79ea
      // 79e5: goto 7a07
      // 79ea: aload 0
      // 79eb: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 79ee: ldc_w "Off"
      // 79f1: sipush 335
      // 79f4: sipush 541
      // 79f7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 79fa: aload 0
      // 79fb: getfield StageMaker.m LMedium;
      // 79fe: bipush 0
      // 79ff: putfield Medium.lightson Z
      // 7a02: goto 7a32
      // 7a07: aload 0
      // 7a08: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a0b: new java/awt/Color
      // 7a0e: dup
      // 7a0f: bipush 0
      // 7a10: sipush 200
      // 7a13: bipush 0
      // 7a14: invokespecial java/awt/Color.<init> (III)V
      // 7a17: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7a1a: aload 0
      // 7a1b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a1e: ldc_w "On"
      // 7a21: sipush 335
      // 7a24: sipush 541
      // 7a27: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7a2a: aload 0
      // 7a2b: getfield StageMaker.m LMedium;
      // 7a2e: bipush 1
      // 7a2f: putfield Medium.lightson Z
      // 7a32: bipush 33
      // 7a34: istore 11
      // 7a36: aload 0
      // 7a37: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a3a: new java/awt/Color
      // 7a3d: dup
      // 7a3e: bipush 0
      // 7a3f: bipush 0
      // 7a40: bipush 0
      // 7a41: invokespecial java/awt/Color.<init> (III)V
      // 7a44: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7a47: aload 0
      // 7a48: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a4b: ldc_w "Dust/Fog Properties"
      // 7a4e: sipush 280
      // 7a51: iload 11
      // 7a53: iadd
      // 7a54: sipush 461
      // 7a57: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7a5a: aload 0
      // 7a5b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a5e: new java/awt/Color
      // 7a61: dup
      // 7a62: sipush 128
      // 7a65: sipush 128
      // 7a68: sipush 128
      // 7a6b: invokespecial java/awt/Color.<init> (III)V
      // 7a6e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7a71: aload 0
      // 7a72: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a75: sipush 270
      // 7a78: iload 11
      // 7a7a: iadd
      // 7a7b: sipush 457
      // 7a7e: sipush 277
      // 7a81: iload 11
      // 7a83: iadd
      // 7a84: sipush 457
      // 7a87: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7a8a: aload 0
      // 7a8b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7a8e: sipush 540
      // 7a91: iload 11
      // 7a93: iadd
      // 7a94: sipush 457
      // 7a97: sipush 393
      // 7a9a: iload 11
      // 7a9c: iadd
      // 7a9d: sipush 457
      // 7aa0: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7aa3: aload 0
      // 7aa4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7aa7: sipush 270
      // 7aaa: iload 11
      // 7aac: iadd
      // 7aad: sipush 457
      // 7ab0: sipush 270
      // 7ab3: iload 11
      // 7ab5: iadd
      // 7ab6: sipush 522
      // 7ab9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7abc: aload 0
      // 7abd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7ac0: sipush 540
      // 7ac3: iload 11
      // 7ac5: iadd
      // 7ac6: sipush 457
      // 7ac9: sipush 540
      // 7acc: iload 11
      // 7ace: iadd
      // 7acf: sipush 522
      // 7ad2: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7ad5: aload 0
      // 7ad6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7ad9: sipush 270
      // 7adc: iload 11
      // 7ade: iadd
      // 7adf: sipush 522
      // 7ae2: sipush 540
      // 7ae5: iload 11
      // 7ae7: iadd
      // 7ae8: sipush 522
      // 7aeb: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7aee: bipush 2
      // 7aef: anewarray 36
      // 7af2: dup
      // 7af3: bipush 0
      // 7af4: ldc_w "Density"
      // 7af7: aastore
      // 7af8: dup
      // 7af9: bipush 1
      // 7afa: ldc_w "Near / Far"
      // 7afd: aastore
      // 7afe: astore 12
      // 7b00: bipush 2
      // 7b01: newarray 10
      // 7b03: dup
      // 7b04: bipush 0
      // 7b05: sipush 292
      // 7b08: iload 11
      // 7b0a: iadd
      // 7b0b: iastore
      // 7b0c: dup
      // 7b0d: bipush 1
      // 7b0e: sipush 280
      // 7b11: iload 11
      // 7b13: iadd
      // 7b14: iastore
      // 7b15: astore 13
      // 7b17: bipush 2
      // 7b18: newarray 10
      // 7b1a: dup
      // 7b1b: bipush 0
      // 7b1c: bipush 20
      // 7b1e: iastore
      // 7b1f: dup
      // 7b20: bipush 1
      // 7b21: bipush 10
      // 7b23: iastore
      // 7b24: astore 14
      // 7b26: bipush 38
      // 7b28: istore 9
      // 7b2a: sipush 210
      // 7b2d: iload 11
      // 7b2f: iadd
      // 7b30: istore 10
      // 7b32: bipush 0
      // 7b33: istore 15
      // 7b35: iload 15
      // 7b37: bipush 2
      // 7b38: if_icmplt 7b40
      // 7b3b: goto 7d42
      // 7b40: aload 0
      // 7b41: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7b44: new java/awt/Color
      // 7b47: dup
      // 7b48: bipush 0
      // 7b49: bipush 0
      // 7b4a: bipush 0
      // 7b4b: invokespecial java/awt/Color.<init> (III)V
      // 7b4e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7b51: aload 0
      // 7b52: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7b55: new java/lang/StringBuilder
      // 7b58: dup
      // 7b59: invokespecial java/lang/StringBuilder.<init> ()V
      // 7b5c: ldc ""
      // 7b5e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7b61: aload 12
      // 7b63: iload 15
      // 7b65: aaload
      // 7b66: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7b69: ldc_w " :"
      // 7b6c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7b6f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7b72: aload 13
      // 7b74: iload 15
      // 7b76: iaload
      // 7b77: sipush 447
      // 7b7a: iload 15
      // 7b7c: bipush 24
      // 7b7e: imul
      // 7b7f: iadd
      // 7b80: iload 9
      // 7b82: iadd
      // 7b83: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7b86: aload 0
      // 7b87: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7b8a: sipush 140
      // 7b8d: iload 10
      // 7b8f: iadd
      // 7b90: sipush 443
      // 7b93: iload 15
      // 7b95: bipush 24
      // 7b97: imul
      // 7b98: iload 9
      // 7b9a: iadd
      // 7b9b: iadd
      // 7b9c: sipush 230
      // 7b9f: iload 10
      // 7ba1: iadd
      // 7ba2: sipush 443
      // 7ba5: iload 15
      // 7ba7: bipush 24
      // 7ba9: imul
      // 7baa: iadd
      // 7bab: iload 9
      // 7bad: iadd
      // 7bae: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7bb1: bipush 1
      // 7bb2: istore 16
      // 7bb4: iload 16
      // 7bb6: bipush 10
      // 7bb8: if_icmplt 7bc0
      // 7bbb: goto 7c05
      // 7bc0: aload 0
      // 7bc1: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7bc4: sipush 140
      // 7bc7: bipush 10
      // 7bc9: iload 16
      // 7bcb: imul
      // 7bcc: iadd
      // 7bcd: iload 10
      // 7bcf: iadd
      // 7bd0: sipush 443
      // 7bd3: iload 16
      // 7bd5: isub
      // 7bd6: iload 15
      // 7bd8: bipush 24
      // 7bda: imul
      // 7bdb: iadd
      // 7bdc: iload 9
      // 7bde: iadd
      // 7bdf: sipush 140
      // 7be2: bipush 10
      // 7be4: iload 16
      // 7be6: imul
      // 7be7: iadd
      // 7be8: iload 10
      // 7bea: iadd
      // 7beb: sipush 443
      // 7bee: iload 16
      // 7bf0: iadd
      // 7bf1: iload 15
      // 7bf3: bipush 24
      // 7bf5: imul
      // 7bf6: iadd
      // 7bf7: iload 9
      // 7bf9: iadd
      // 7bfa: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 7bfd: iinc 16 1
      // 7c00: goto 7bb4
      // 7c05: aload 0
      // 7c06: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7c09: new java/awt/Color
      // 7c0c: dup
      // 7c0d: sipush 255
      // 7c10: bipush 0
      // 7c11: bipush 0
      // 7c12: invokespecial java/awt/Color.<init> (III)V
      // 7c15: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7c18: aload 0
      // 7c19: getfield StageMaker.fogn [I
      // 7c1c: iload 15
      // 7c1e: iaload
      // 7c1f: i2f
      // 7c20: ldc_w 1.1111
      // 7c23: fdiv
      // 7c24: ldc_w 10.0
      // 7c27: fdiv
      // 7c28: f2i
      // 7c29: istore 16
      // 7c2b: aload 0
      // 7c2c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7c2f: sipush 138
      // 7c32: aload 0
      // 7c33: getfield StageMaker.fogn [I
      // 7c36: iload 15
      // 7c38: iaload
      // 7c39: i2f
      // 7c3a: ldc_w 1.1111
      // 7c3d: fdiv
      // 7c3e: f2i
      // 7c3f: iadd
      // 7c40: iload 10
      // 7c42: iadd
      // 7c43: sipush 443
      // 7c46: iload 16
      // 7c48: isub
      // 7c49: iload 15
      // 7c4b: bipush 24
      // 7c4d: imul
      // 7c4e: iadd
      // 7c4f: iload 9
      // 7c51: iadd
      // 7c52: bipush 5
      // 7c53: iload 16
      // 7c55: bipush 2
      // 7c56: imul
      // 7c57: bipush 1
      // 7c58: iadd
      // 7c59: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 7c5c: aload 0
      // 7c5d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7c60: new java/awt/Color
      // 7c63: dup
      // 7c64: sipush 255
      // 7c67: sipush 128
      // 7c6a: bipush 0
      // 7c6b: invokespecial java/awt/Color.<init> (III)V
      // 7c6e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7c71: aload 0
      // 7c72: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7c75: sipush 139
      // 7c78: aload 0
      // 7c79: getfield StageMaker.fogn [I
      // 7c7c: iload 15
      // 7c7e: iaload
      // 7c7f: i2f
      // 7c80: ldc_w 1.1111
      // 7c83: fdiv
      // 7c84: f2i
      // 7c85: iadd
      // 7c86: iload 10
      // 7c88: iadd
      // 7c89: sipush 434
      // 7c8c: iload 15
      // 7c8e: bipush 24
      // 7c90: imul
      // 7c91: iadd
      // 7c92: iload 9
      // 7c94: iadd
      // 7c95: bipush 2
      // 7c96: bipush 18
      // 7c98: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 7c9b: aload 0
      // 7c9c: ldc_w " - "
      // 7c9f: sipush 260
      // 7ca2: iload 10
      // 7ca4: iadd
      // 7ca5: sipush 447
      // 7ca8: iload 15
      // 7caa: bipush 24
      // 7cac: imul
      // 7cad: iadd
      // 7cae: iload 9
      // 7cb0: iadd
      // 7cb1: bipush 4
      // 7cb2: bipush 0
      // 7cb3: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7cb6: ifne 7cbe
      // 7cb9: goto 7ce9
      // 7cbe: aload 0
      // 7cbf: getfield StageMaker.fogn [I
      // 7cc2: iload 15
      // 7cc4: dup2
      // 7cc5: iaload
      // 7cc6: aload 14
      // 7cc8: iload 15
      // 7cca: iaload
      // 7ccb: isub
      // 7ccc: iastore
      // 7ccd: aload 0
      // 7cce: getfield StageMaker.fogn [I
      // 7cd1: iload 15
      // 7cd3: iaload
      // 7cd4: iflt 7cdc
      // 7cd7: goto 7ce9
      // 7cdc: aload 0
      // 7cdd: getfield StageMaker.fogn [I
      // 7ce0: iload 15
      // 7ce2: bipush 0
      // 7ce3: iastore
      // 7ce4: goto 7ce9
      // 7ce9: aload 0
      // 7cea: ldc_w " + "
      // 7ced: sipush 300
      // 7cf0: iload 10
      // 7cf2: iadd
      // 7cf3: sipush 447
      // 7cf6: iload 15
      // 7cf8: bipush 24
      // 7cfa: imul
      // 7cfb: iadd
      // 7cfc: iload 9
      // 7cfe: iadd
      // 7cff: bipush 4
      // 7d00: bipush 0
      // 7d01: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7d04: ifne 7d0c
      // 7d07: goto 7d3a
      // 7d0c: aload 0
      // 7d0d: getfield StageMaker.fogn [I
      // 7d10: iload 15
      // 7d12: dup2
      // 7d13: iaload
      // 7d14: aload 14
      // 7d16: iload 15
      // 7d18: iaload
      // 7d19: iadd
      // 7d1a: iastore
      // 7d1b: aload 0
      // 7d1c: getfield StageMaker.fogn [I
      // 7d1f: iload 15
      // 7d21: iaload
      // 7d22: bipush 100
      // 7d24: if_icmpgt 7d2c
      // 7d27: goto 7d3a
      // 7d2c: aload 0
      // 7d2d: getfield StageMaker.fogn [I
      // 7d30: iload 15
      // 7d32: bipush 100
      // 7d34: iastore
      // 7d35: goto 7d3a
      // 7d3a: iinc 15 1
      // 7d3d: goto 7b35
      // 7d42: aload 0
      // 7d43: getfield StageMaker.m LMedium;
      // 7d46: bipush 8
      // 7d48: aload 0
      // 7d49: getfield StageMaker.fogn [I
      // 7d4c: bipush 0
      // 7d4d: iaload
      // 7d4e: bipush 20
      // 7d50: idiv
      // 7d51: isub
      // 7d52: bipush 1
      // 7d53: iadd
      // 7d54: bipush 2
      // 7d55: imul
      // 7d56: bipush 1
      // 7d57: isub
      // 7d58: putfield Medium.fogd I
      // 7d5b: aload 0
      // 7d5c: getfield StageMaker.m LMedium;
      // 7d5f: sipush 5000
      // 7d62: aload 0
      // 7d63: getfield StageMaker.fogn [I
      // 7d66: bipush 1
      // 7d67: iaload
      // 7d68: bipush 30
      // 7d6a: imul
      // 7d6b: iadd
      // 7d6c: invokevirtual Medium.fadfrom (I)V
      // 7d6f: aload 0
      // 7d70: aload 0
      // 7d71: getfield StageMaker.m LMedium;
      // 7d74: getfield Medium.fade [I
      // 7d77: bipush 0
      // 7d78: iaload
      // 7d79: putfield StageMaker.origfade I
      // 7d7c: aload 0
      // 7d7d: ldc_w " Reset "
      // 7d80: sipush 650
      // 7d83: sipush 510
      // 7d86: bipush 0
      // 7d87: bipush 1
      // 7d88: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7d8b: ifne 7d93
      // 7d8e: goto 7d9e
      // 7d93: aload 0
      // 7d94: bipush -2
      // 7d96: putfield StageMaker.dtabed I
      // 7d99: goto 7d9e
      // 7d9e: aload 0
      // 7d9f: ldc_w "        Save        "
      // 7da2: sipush 737
      // 7da5: sipush 510
      // 7da8: bipush 0
      // 7da9: bipush 1
      // 7daa: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7dad: ifne 7db5
      // 7db0: goto 7dc2
      // 7db5: aload 0
      // 7db6: invokevirtual StageMaker.sortop ()V
      // 7db9: aload 0
      // 7dba: invokevirtual StageMaker.savefile ()V
      // 7dbd: goto 7dc2
      // 7dc2: goto 7dc7
      // 7dc7: aload 0
      // 7dc8: getfield StageMaker.dtab I
      // 7dcb: bipush 4
      // 7dcc: if_icmpeq 7dd4
      // 7dcf: goto 7edd
      // 7dd4: aload 0
      // 7dd5: getfield StageMaker.dtabed I
      // 7dd8: aload 0
      // 7dd9: getfield StageMaker.dtab I
      // 7ddc: if_icmpne 7de4
      // 7ddf: goto 7e1d
      // 7de4: aload 0
      // 7de5: getfield StageMaker.cp LCheckPoints;
      // 7de8: getfield CheckPoints.nlaps I
      // 7deb: bipush 1
      // 7dec: isub
      // 7ded: ifge 7df5
      // 7df0: goto 7e1d
      // 7df5: aload 0
      // 7df6: getfield StageMaker.cp LCheckPoints;
      // 7df9: getfield CheckPoints.nlaps I
      // 7dfc: bipush 1
      // 7dfd: isub
      // 7dfe: bipush 14
      // 7e00: if_icmple 7e08
      // 7e03: goto 7e1d
      // 7e08: aload 0
      // 7e09: getfield StageMaker.nlaps LSmenu;
      // 7e0c: aload 0
      // 7e0d: getfield StageMaker.cp LCheckPoints;
      // 7e10: getfield CheckPoints.nlaps I
      // 7e13: bipush 1
      // 7e14: isub
      // 7e15: invokevirtual Smenu.select (I)V
      // 7e18: goto 7e1d
      // 7e1d: aload 0
      // 7e1e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7e21: new java/awt/Color
      // 7e24: dup
      // 7e25: bipush 0
      // 7e26: bipush 0
      // 7e27: bipush 0
      // 7e28: invokespecial java/awt/Color.<init> (III)V
      // 7e2b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7e2e: aload 0
      // 7e2f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7e32: ldc_w "Set the number of laps for this stage:"
      // 7e35: sipush 130
      // 7e38: sipush 496
      // 7e3b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7e3e: aload 0
      // 7e3f: getfield StageMaker.nlaps LSmenu;
      // 7e42: sipush 348
      // 7e45: sipush 480
      // 7e48: invokevirtual Smenu.move (II)V
      // 7e4b: aload 0
      // 7e4c: getfield StageMaker.nlaps LSmenu;
      // 7e4f: invokevirtual Smenu.isShowing ()Z
      // 7e52: ifeq 7e5a
      // 7e55: goto 7e66
      // 7e5a: aload 0
      // 7e5b: getfield StageMaker.nlaps LSmenu;
      // 7e5e: invokevirtual Smenu.show ()V
      // 7e61: goto 7e66
      // 7e66: aload 0
      // 7e67: getfield StageMaker.cp LCheckPoints;
      // 7e6a: getfield CheckPoints.nlaps I
      // 7e6d: aload 0
      // 7e6e: getfield StageMaker.nlaps LSmenu;
      // 7e71: invokevirtual Smenu.getSelectedIndex ()I
      // 7e74: bipush 1
      // 7e75: iadd
      // 7e76: if_icmpne 7e7e
      // 7e79: goto 7e97
      // 7e7e: aload 0
      // 7e7f: getfield StageMaker.cp LCheckPoints;
      // 7e82: aload 0
      // 7e83: getfield StageMaker.nlaps LSmenu;
      // 7e86: invokevirtual Smenu.getSelectedIndex ()I
      // 7e89: bipush 1
      // 7e8a: iadd
      // 7e8b: putfield CheckPoints.nlaps I
      // 7e8e: aload 0
      // 7e8f: invokevirtual StageMaker.requestFocus ()V
      // 7e92: goto 7e97
      // 7e97: aload 0
      // 7e98: ldc_w " Reset "
      // 7e9b: sipush 530
      // 7e9e: sipush 496
      // 7ea1: bipush 0
      // 7ea2: bipush 1
      // 7ea3: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7ea6: ifne 7eae
      // 7ea9: goto 7eb9
      // 7eae: aload 0
      // 7eaf: bipush -2
      // 7eb1: putfield StageMaker.dtabed I
      // 7eb4: goto 7eb9
      // 7eb9: aload 0
      // 7eba: ldc_w "        Save        "
      // 7ebd: sipush 617
      // 7ec0: sipush 496
      // 7ec3: bipush 0
      // 7ec4: bipush 1
      // 7ec5: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 7ec8: ifne 7ed0
      // 7ecb: goto 7edd
      // 7ed0: aload 0
      // 7ed1: invokevirtual StageMaker.sortop ()V
      // 7ed4: aload 0
      // 7ed5: invokevirtual StageMaker.savefile ()V
      // 7ed8: goto 7edd
      // 7edd: aload 0
      // 7ede: getfield StageMaker.dtab I
      // 7ee1: bipush 5
      // 7ee2: if_icmpeq 7eea
      // 7ee5: goto 8b37
      // 7eea: aload 0
      // 7eeb: getfield StageMaker.dtabed I
      // 7eee: aload 0
      // 7eef: getfield StageMaker.dtab I
      // 7ef2: if_icmpne 7efa
      // 7ef5: goto 7ff4
      // 7efa: aload 0
      // 7efb: getfield StageMaker.tracks LSmenu;
      // 7efe: invokevirtual Smenu.removeAll ()V
      // 7f01: aload 0
      // 7f02: getfield StageMaker.tracks LSmenu;
      // 7f05: sipush 200
      // 7f08: putfield Smenu.maxl I
      // 7f0b: aload 0
      // 7f0c: getfield StageMaker.tracks LSmenu;
      // 7f0f: aload 0
      // 7f10: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7f13: ldc_w "The Play List  -  MOD Tracks"
      // 7f16: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 7f19: new java/io/File
      // 7f1c: dup
      // 7f1d: new java/lang/StringBuilder
      // 7f20: dup
      // 7f21: invokespecial java/lang/StringBuilder.<init> ()V
      // 7f24: ldc ""
      // 7f26: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f29: getstatic Madness.fpath Ljava/lang/String;
      // 7f2c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f2f: ldc_w "mystages/mymusic/"
      // 7f32: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f35: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7f38: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 7f3b: invokevirtual java/io/File.list ()[Ljava/lang/String;
      // 7f3e: astore 7
      // 7f40: aload 7
      // 7f42: ifnonnull 7f4a
      // 7f45: goto 7fa0
      // 7f4a: bipush 0
      // 7f4b: istore 8
      // 7f4d: iload 8
      // 7f4f: aload 7
      // 7f51: arraylength
      // 7f52: if_icmplt 7f5a
      // 7f55: goto 7f9b
      // 7f5a: aload 7
      // 7f5c: iload 8
      // 7f5e: aaload
      // 7f5f: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7f62: ldc_w ".zip"
      // 7f65: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 7f68: ifne 7f70
      // 7f6b: goto 7f93
      // 7f70: aload 0
      // 7f71: getfield StageMaker.tracks LSmenu;
      // 7f74: aload 0
      // 7f75: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 7f78: aload 7
      // 7f7a: iload 8
      // 7f7c: aaload
      // 7f7d: bipush 0
      // 7f7e: aload 7
      // 7f80: iload 8
      // 7f82: aaload
      // 7f83: invokevirtual java/lang/String.length ()I
      // 7f86: bipush 4
      // 7f87: isub
      // 7f88: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 7f8b: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 7f8e: goto 7f93
      // 7f93: iinc 8 1
      // 7f96: goto 7f4d
      // 7f9b: goto 7fa0
      // 7fa0: aload 0
      // 7fa1: getfield StageMaker.ltrackname Ljava/lang/String;
      // 7fa4: ldc ""
      // 7fa6: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7fa9: ifne 7fb1
      // 7fac: goto 7fdf
      // 7fb1: aload 0
      // 7fb2: getfield StageMaker.trackname Ljava/lang/String;
      // 7fb5: ldc ""
      // 7fb7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7fba: ifne 7fc2
      // 7fbd: goto 7fcf
      // 7fc2: aload 0
      // 7fc3: getfield StageMaker.tracks LSmenu;
      // 7fc6: bipush 0
      // 7fc7: invokevirtual Smenu.select (I)V
      // 7fca: goto 7fea
      // 7fcf: aload 0
      // 7fd0: getfield StageMaker.tracks LSmenu;
      // 7fd3: aload 0
      // 7fd4: getfield StageMaker.trackname Ljava/lang/String;
      // 7fd7: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 7fda: goto 7fea
      // 7fdf: aload 0
      // 7fe0: getfield StageMaker.tracks LSmenu;
      // 7fe3: aload 0
      // 7fe4: getfield StageMaker.ltrackname Ljava/lang/String;
      // 7fe7: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 7fea: aload 0
      // 7feb: bipush -1
      // 7fec: putfield StageMaker.mouseon I
      // 7fef: goto 7ff4
      // 7ff4: aload 0
      // 7ff5: getfield StageMaker.tracks LSmenu;
      // 7ff8: bipush 10
      // 7ffa: sipush 450
      // 7ffd: invokevirtual Smenu.move (II)V
      // 8000: aload 0
      // 8001: getfield StageMaker.tracks LSmenu;
      // 8004: invokevirtual Smenu.getWidth ()I
      // 8007: sipush 200
      // 800a: if_icmpne 8012
      // 800d: goto 8023
      // 8012: aload 0
      // 8013: getfield StageMaker.tracks LSmenu;
      // 8016: sipush 200
      // 8019: bipush 21
      // 801b: invokevirtual Smenu.setSize (II)V
      // 801e: goto 8023
      // 8023: aload 0
      // 8024: getfield StageMaker.tracks LSmenu;
      // 8027: invokevirtual Smenu.isShowing ()Z
      // 802a: ifeq 8032
      // 802d: goto 803e
      // 8032: aload 0
      // 8033: getfield StageMaker.tracks LSmenu;
      // 8036: invokevirtual Smenu.show ()V
      // 8039: goto 803e
      // 803e: aload 0
      // 803f: getfield StageMaker.track LRadicalMod;
      // 8042: getfield RadicalMod.playing Z
      // 8045: ifne 804d
      // 8048: goto 82d4
      // 804d: aload 0
      // 804e: getfield StageMaker.track LRadicalMod;
      // 8051: getfield RadicalMod.loaded I
      // 8054: bipush 2
      // 8055: if_icmpeq 805d
      // 8058: goto 82d4
      // 805d: aload 0
      // 805e: ldc_w "      Stop      "
      // 8061: bipush 110
      // 8063: sipush 495
      // 8066: bipush 2
      // 8067: bipush 0
      // 8068: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 806b: ifne 8073
      // 806e: goto 807f
      // 8073: aload 0
      // 8074: getfield StageMaker.track LRadicalMod;
      // 8077: invokevirtual RadicalMod.stop ()V
      // 807a: goto 807f
      // 807f: aload 0
      // 8080: getfield StageMaker.ltrackname Ljava/lang/String;
      // 8083: aload 0
      // 8084: getfield StageMaker.tracks LSmenu;
      // 8087: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 808a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 808d: ifeq 8095
      // 8090: goto 80a1
      // 8095: aload 0
      // 8096: getfield StageMaker.track LRadicalMod;
      // 8099: invokevirtual RadicalMod.stop ()V
      // 809c: goto 80a1
      // 80a1: aload 0
      // 80a2: getfield StageMaker.xm I
      // 80a5: bipush 10
      // 80a7: if_icmpgt 80af
      // 80aa: goto 810d
      // 80af: aload 0
      // 80b0: getfield StageMaker.xm I
      // 80b3: sipush 210
      // 80b6: if_icmplt 80be
      // 80b9: goto 810d
      // 80be: aload 0
      // 80bf: getfield StageMaker.ym I
      // 80c2: sipush 516
      // 80c5: if_icmpgt 80cd
      // 80c8: goto 810d
      // 80cd: aload 0
      // 80ce: getfield StageMaker.ym I
      // 80d1: sipush 534
      // 80d4: if_icmplt 80dc
      // 80d7: goto 810d
      // 80dc: aload 0
      // 80dd: getfield StageMaker.mouses I
      // 80e0: bipush 1
      // 80e1: if_icmpeq 80e9
      // 80e4: goto 80f3
      // 80e9: aload 0
      // 80ea: bipush 1
      // 80eb: putfield StageMaker.mouseon I
      // 80ee: goto 80f3
      // 80f3: aload 0
      // 80f4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 80f7: new java/awt/Color
      // 80fa: dup
      // 80fb: bipush 0
      // 80fc: sipush 164
      // 80ff: sipush 242
      // 8102: invokespecial java/awt/Color.<init> (III)V
      // 8105: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8108: goto 8123
      // 810d: aload 0
      // 810e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8111: new java/awt/Color
      // 8114: dup
      // 8115: bipush 120
      // 8117: sipush 210
      // 811a: sipush 255
      // 811d: invokespecial java/awt/Color.<init> (III)V
      // 8120: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8123: aload 0
      // 8124: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8127: bipush 10
      // 8129: sipush 516
      // 812c: sipush 200
      // 812f: bipush 18
      // 8131: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 8134: aload 0
      // 8135: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8138: new java/awt/Color
      // 813b: dup
      // 813c: sipush 200
      // 813f: sipush 200
      // 8142: sipush 200
      // 8145: invokespecial java/awt/Color.<init> (III)V
      // 8148: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 814b: aload 0
      // 814c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 814f: bipush 10
      // 8151: sipush 523
      // 8154: sipush 210
      // 8157: sipush 523
      // 815a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 815d: aload 0
      // 815e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8161: new java/awt/Color
      // 8164: dup
      // 8165: bipush 0
      // 8166: bipush 0
      // 8167: bipush 0
      // 8168: invokespecial java/awt/Color.<init> (III)V
      // 816b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 816e: aload 0
      // 816f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8172: bipush 10
      // 8174: sipush 524
      // 8177: sipush 210
      // 817a: sipush 524
      // 817d: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8180: aload 0
      // 8181: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8184: bipush 10
      // 8186: sipush 525
      // 8189: sipush 210
      // 818c: sipush 525
      // 818f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8192: aload 0
      // 8193: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8196: bipush 10
      // 8198: sipush 526
      // 819b: sipush 210
      // 819e: sipush 526
      // 81a1: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 81a4: aload 0
      // 81a5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 81a8: new java/awt/Color
      // 81ab: dup
      // 81ac: sipush 255
      // 81af: sipush 255
      // 81b2: sipush 255
      // 81b5: invokespecial java/awt/Color.<init> (III)V
      // 81b8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 81bb: aload 0
      // 81bc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 81bf: bipush 10
      // 81c1: sipush 527
      // 81c4: sipush 210
      // 81c7: sipush 527
      // 81ca: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 81cd: fconst_1
      // 81ce: aload 0
      // 81cf: getfield StageMaker.track LRadicalMod;
      // 81d2: getfield RadicalMod.sClip LSuperClip;
      // 81d5: getfield SuperClip.stream Ljava/io/ByteArrayInputStream;
      // 81d8: invokevirtual java/io/ByteArrayInputStream.available ()I
      // 81db: i2f
      // 81dc: aload 0
      // 81dd: getfield StageMaker.avon I
      // 81e0: i2f
      // 81e1: fdiv
      // 81e2: fsub
      // 81e3: ldc_w 200.0
      // 81e6: fmul
      // 81e7: f2i
      // 81e8: istore 7
      // 81ea: aload 0
      // 81eb: getfield StageMaker.mouseon I
      // 81ee: bipush 1
      // 81ef: if_icmpeq 81f7
      // 81f2: goto 8269
      // 81f7: aload 0
      // 81f8: getfield StageMaker.xm I
      // 81fb: bipush 10
      // 81fd: isub
      // 81fe: istore 7
      // 8200: iload 7
      // 8202: iflt 820a
      // 8205: goto 8212
      // 820a: bipush 0
      // 820b: istore 7
      // 820d: goto 8212
      // 8212: iload 7
      // 8214: sipush 200
      // 8217: if_icmpgt 821f
      // 821a: goto 8229
      // 821f: sipush 200
      // 8222: istore 7
      // 8224: goto 8229
      // 8229: aload 0
      // 822a: getfield StageMaker.mouses I
      // 822d: bipush 1
      // 822e: if_icmpne 8236
      // 8231: goto 8269
      // 8236: aload 0
      // 8237: getfield StageMaker.track LRadicalMod;
      // 823a: getfield RadicalMod.sClip LSuperClip;
      // 823d: getfield SuperClip.stream Ljava/io/ByteArrayInputStream;
      // 8240: invokevirtual java/io/ByteArrayInputStream.reset ()V
      // 8243: aload 0
      // 8244: getfield StageMaker.track LRadicalMod;
      // 8247: getfield RadicalMod.sClip LSuperClip;
      // 824a: getfield SuperClip.stream Ljava/io/ByteArrayInputStream;
      // 824d: iload 7
      // 824f: i2f
      // 8250: ldc_w 200.0
      // 8253: fdiv
      // 8254: aload 0
      // 8255: getfield StageMaker.avon I
      // 8258: i2f
      // 8259: fmul
      // 825a: f2l
      // 825b: invokevirtual java/io/ByteArrayInputStream.skip (J)J
      // 825e: pop2
      // 825f: aload 0
      // 8260: bipush -1
      // 8261: putfield StageMaker.mouseon I
      // 8264: goto 8269
      // 8269: aload 0
      // 826a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 826d: new java/awt/Color
      // 8270: dup
      // 8271: bipush 0
      // 8272: bipush 0
      // 8273: bipush 0
      // 8274: invokespecial java/awt/Color.<init> (III)V
      // 8277: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 827a: aload 0
      // 827b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 827e: bipush 8
      // 8280: iload 7
      // 8282: iadd
      // 8283: sipush 516
      // 8286: bipush 4
      // 8287: bipush 18
      // 8289: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 828c: aload 0
      // 828d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8290: new java/awt/Color
      // 8293: dup
      // 8294: bipush 0
      // 8295: sipush 164
      // 8298: sipush 242
      // 829b: invokespecial java/awt/Color.<init> (III)V
      // 829e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 82a1: aload 0
      // 82a2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 82a5: bipush 10
      // 82a7: iload 7
      // 82a9: iadd
      // 82aa: sipush 520
      // 82ad: bipush 10
      // 82af: iload 7
      // 82b1: iadd
      // 82b2: sipush 518
      // 82b5: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 82b8: aload 0
      // 82b9: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 82bc: bipush 10
      // 82be: iload 7
      // 82c0: iadd
      // 82c1: sipush 530
      // 82c4: bipush 10
      // 82c6: iload 7
      // 82c8: iadd
      // 82c9: sipush 532
      // 82cc: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 82cf: goto 83d1
      // 82d4: aload 0
      // 82d5: getfield StageMaker.tracks LSmenu;
      // 82d8: invokevirtual Smenu.getSelectedIndex ()I
      // 82db: ifne 82e3
      // 82de: goto 83d1
      // 82e3: aload 0
      // 82e4: ldc_w "      Play  >      "
      // 82e7: bipush 110
      // 82e9: sipush 495
      // 82ec: bipush 2
      // 82ed: bipush 0
      // 82ee: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 82f1: ifne 82f9
      // 82f4: goto 83d1
      // 82f9: aload 0
      // 82fa: getfield StageMaker.ltrackname Ljava/lang/String;
      // 82fd: aload 0
      // 82fe: getfield StageMaker.tracks LSmenu;
      // 8301: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8304: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8307: ifeq 830f
      // 830a: goto 8387
      // 830f: aload 0
      // 8310: getfield StageMaker.track LRadicalMod;
      // 8313: invokevirtual RadicalMod.unload ()V
      // 8316: aload 0
      // 8317: new RadicalMod
      // 831a: dup
      // 831b: new java/lang/StringBuilder
      // 831e: dup
      // 831f: invokespecial java/lang/StringBuilder.<init> ()V
      // 8322: ldc_w "mystages/mymusic/"
      // 8325: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8328: aload 0
      // 8329: getfield StageMaker.tracks LSmenu;
      // 832c: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 832f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8332: ldc_w ".zip"
      // 8335: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8338: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 833b: sipush 300
      // 833e: sipush 8000
      // 8341: bipush 125
      // 8343: bipush 1
      // 8344: bipush 0
      // 8345: invokespecial RadicalMod.<init> (Ljava/lang/String;IIIZZ)V
      // 8348: putfield StageMaker.track LRadicalMod;
      // 834b: aload 0
      // 834c: getfield StageMaker.track LRadicalMod;
      // 834f: getfield RadicalMod.loaded I
      // 8352: bipush 2
      // 8353: if_icmpeq 835b
      // 8356: goto 837c
      // 835b: aload 0
      // 835c: aload 0
      // 835d: getfield StageMaker.track LRadicalMod;
      // 8360: getfield RadicalMod.sClip LSuperClip;
      // 8363: getfield SuperClip.stream Ljava/io/ByteArrayInputStream;
      // 8366: invokevirtual java/io/ByteArrayInputStream.available ()I
      // 8369: putfield StageMaker.avon I
      // 836c: aload 0
      // 836d: aload 0
      // 836e: getfield StageMaker.tracks LSmenu;
      // 8371: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8374: putfield StageMaker.ltrackname Ljava/lang/String;
      // 8377: goto 8387
      // 837c: aload 0
      // 837d: ldc ""
      // 837f: putfield StageMaker.ltrackname Ljava/lang/String;
      // 8382: goto 8387
      // 8387: aload 0
      // 8388: getfield StageMaker.ltrackname Ljava/lang/String;
      // 838b: ldc ""
      // 838d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8390: ifeq 8398
      // 8393: goto 83a4
      // 8398: aload 0
      // 8399: getfield StageMaker.track LRadicalMod;
      // 839c: invokevirtual RadicalMod.play ()V
      // 839f: goto 83d1
      // 83a4: aconst_null
      // 83a5: new java/lang/StringBuilder
      // 83a8: dup
      // 83a9: invokespecial java/lang/StringBuilder.<init> ()V
      // 83ac: ldc_w "Failed to load '"
      // 83af: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83b2: aload 0
      // 83b3: getfield StageMaker.tracks LSmenu;
      // 83b6: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 83b9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83bc: ldc_w "', please make sure it is a valid MOD Track!"
      // 83bf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83c2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 83c5: ldc_w "Stage Maker"
      // 83c8: bipush 1
      // 83c9: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 83cc: goto 83d1
      // 83d1: aload 0
      // 83d2: getfield StageMaker.tracks LSmenu;
      // 83d5: invokevirtual Smenu.getSelectedIndex ()I
      // 83d8: ifne 83e0
      // 83db: goto 85c3
      // 83e0: aload 0
      // 83e1: ldc_w "   Set as the stage's Sound Track  >   "
      // 83e4: sipush 330
      // 83e7: sipush 466
      // 83ea: bipush 2
      // 83eb: bipush 0
      // 83ec: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 83ef: ifne 83f7
      // 83f2: goto 8573
      // 83f7: aload 0
      // 83f8: getfield StageMaker.ltrackname Ljava/lang/String;
      // 83fb: aload 0
      // 83fc: getfield StageMaker.tracks LSmenu;
      // 83ff: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8402: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8405: ifeq 840d
      // 8408: goto 8485
      // 840d: aload 0
      // 840e: getfield StageMaker.track LRadicalMod;
      // 8411: invokevirtual RadicalMod.unload ()V
      // 8414: aload 0
      // 8415: new RadicalMod
      // 8418: dup
      // 8419: new java/lang/StringBuilder
      // 841c: dup
      // 841d: invokespecial java/lang/StringBuilder.<init> ()V
      // 8420: ldc_w "mystages/mymusic/"
      // 8423: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8426: aload 0
      // 8427: getfield StageMaker.tracks LSmenu;
      // 842a: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 842d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8430: ldc_w ".zip"
      // 8433: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8436: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8439: sipush 300
      // 843c: sipush 8000
      // 843f: bipush 125
      // 8441: bipush 1
      // 8442: bipush 0
      // 8443: invokespecial RadicalMod.<init> (Ljava/lang/String;IIIZZ)V
      // 8446: putfield StageMaker.track LRadicalMod;
      // 8449: aload 0
      // 844a: getfield StageMaker.track LRadicalMod;
      // 844d: getfield RadicalMod.loaded I
      // 8450: bipush 2
      // 8451: if_icmpeq 8459
      // 8454: goto 847a
      // 8459: aload 0
      // 845a: aload 0
      // 845b: getfield StageMaker.track LRadicalMod;
      // 845e: getfield RadicalMod.sClip LSuperClip;
      // 8461: getfield SuperClip.stream Ljava/io/ByteArrayInputStream;
      // 8464: invokevirtual java/io/ByteArrayInputStream.available ()I
      // 8467: putfield StageMaker.avon I
      // 846a: aload 0
      // 846b: aload 0
      // 846c: getfield StageMaker.tracks LSmenu;
      // 846f: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8472: putfield StageMaker.ltrackname Ljava/lang/String;
      // 8475: goto 8485
      // 847a: aload 0
      // 847b: ldc ""
      // 847d: putfield StageMaker.ltrackname Ljava/lang/String;
      // 8480: goto 8485
      // 8485: aload 0
      // 8486: getfield StageMaker.ltrackname Ljava/lang/String;
      // 8489: ldc ""
      // 848b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 848e: ifeq 8496
      // 8491: goto 8546
      // 8496: aload 0
      // 8497: aload 0
      // 8498: getfield StageMaker.ltrackname Ljava/lang/String;
      // 849b: putfield StageMaker.trackname Ljava/lang/String;
      // 849e: aload 0
      // 849f: ldc_w 220.0
      // 84a2: aload 0
      // 84a3: getfield StageMaker.track LRadicalMod;
      // 84a6: getfield RadicalMod.rvol I
      // 84a9: i2f
      // 84aa: ldc_w 3750.0
      // 84ad: fdiv
      // 84ae: fdiv
      // 84af: f2i
      // 84b0: putfield StageMaker.trackvol I
      // 84b3: new java/io/File
      // 84b6: dup
      // 84b7: new java/lang/StringBuilder
      // 84ba: dup
      // 84bb: invokespecial java/lang/StringBuilder.<init> ()V
      // 84be: ldc ""
      // 84c0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 84c3: getstatic Madness.fpath Ljava/lang/String;
      // 84c6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 84c9: ldc_w "mystages/mymusic/"
      // 84cc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 84cf: aload 0
      // 84d0: getfield StageMaker.trackname Ljava/lang/String;
      // 84d3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 84d6: ldc_w ".zip"
      // 84d9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 84dc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 84df: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 84e2: astore 7
      // 84e4: aload 0
      // 84e5: aload 7
      // 84e7: invokevirtual java/io/File.length ()J
      // 84ea: ldc2_w 1024
      // 84ed: ldiv
      // 84ee: l2i
      // 84ef: putfield StageMaker.tracksize I
      // 84f2: aload 0
      // 84f3: getfield StageMaker.tracksize I
      // 84f6: sipush 700
      // 84f9: if_icmpgt 8501
      // 84fc: goto 8534
      // 8501: aconst_null
      // 8502: new java/lang/StringBuilder
      // 8505: dup
      // 8506: invokespecial java/lang/StringBuilder.<init> ()V
      // 8509: ldc_w "Cannot use '"
      // 850c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 850f: aload 0
      // 8510: getfield StageMaker.tracks LSmenu;
      // 8513: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8516: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8519: ldc_w "' as the sound track!\nIts file size is bigger then 700KB.\n\n"
      // 851c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 851f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8522: ldc_w "Stage Maker"
      // 8525: bipush 1
      // 8526: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 8529: aload 0
      // 852a: ldc ""
      // 852c: putfield StageMaker.trackname Ljava/lang/String;
      // 852f: goto 8534
      // 8534: goto 8573
      // 8539: astore 7
      // 853b: aload 0
      // 853c: bipush 111
      // 853e: putfield StageMaker.tracksize I
      // 8541: goto 8573
      // 8546: aconst_null
      // 8547: new java/lang/StringBuilder
      // 854a: dup
      // 854b: invokespecial java/lang/StringBuilder.<init> ()V
      // 854e: ldc_w "Failed to load '"
      // 8551: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8554: aload 0
      // 8555: getfield StageMaker.tracks LSmenu;
      // 8558: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 855b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 855e: ldc_w "', please make sure it is a valid MOD Track!"
      // 8561: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8564: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8567: ldc_w "Stage Maker"
      // 856a: bipush 1
      // 856b: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 856e: goto 8573
      // 8573: aload 0
      // 8574: ldc_w "   X Delete   "
      // 8577: sipush 258
      // 857a: sipush 495
      // 857d: bipush 2
      // 857e: bipush 0
      // 857f: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8582: ifne 858a
      // 8585: goto 85c3
      // 858a: aconst_null
      // 858b: new java/lang/StringBuilder
      // 858e: dup
      // 858f: invokespecial java/lang/StringBuilder.<init> ()V
      // 8592: ldc_w "Are you sure you want to permanently delete this MOD Track from your Play List?\n\n"
      // 8595: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8598: aload 0
      // 8599: getfield StageMaker.tracks LSmenu;
      // 859c: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 859f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 85a2: ldc_w "\n\n>  If you delete this Track from the Play List you will not be able to use it for other stages as well!     \n\n"
      // 85a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 85a8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 85ab: ldc_w "Stage Maker"
      // 85ae: bipush 0
      // 85af: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 85b2: ifeq 85ba
      // 85b5: goto 85c3
      // 85ba: aload 0
      // 85bb: invokevirtual StageMaker.deltrack ()V
      // 85be: goto 85c3
      // 85c3: aload 0
      // 85c4: ldc_w "      Add a new Track from a file . . .     "
      // 85c7: sipush 330
      // 85ca: sipush 530
      // 85cd: bipush 0
      // 85ce: bipush 0
      // 85cf: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 85d2: ifne 85da
      // 85d5: goto 89cd
      // 85da: aconst_null
      // 85db: ldc_w "The game only accepts Module format music files for the game ('.mod', '.xm' and '.s3m' file extensions).\nA good place to find Module Tracks is the modarchive.com, all the current Module Tracks\nthat are distributed with the game are from the modarchive.com.\n\nTo find out more about Module Tracks and to learn how to compose & remix your own\nmusic, please read the section of the Stage Maker help about it.\n\nThe Stage Maker accepts only '.mod', '.xm' & '.s3m' files or a '.zip' file containing a Module file.\nThe file size of the Module must be less the 700KB (when compressed as a zip file).\n"
      // 85de: ldc_w "Stage Maker"
      // 85e1: bipush 0
      // 85e2: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 85e5: ifeq 85ed
      // 85e8: goto 89cd
      // 85ed: aconst_null
      // 85ee: astore 7
      // 85f0: new java/awt/FileDialog
      // 85f3: dup
      // 85f4: new java/awt/Frame
      // 85f7: dup
      // 85f8: invokespecial java/awt/Frame.<init> ()V
      // 85fb: ldc_w "Stage Maker - Add MOD Track file to stage sound track play list!"
      // 85fe: invokespecial java/awt/FileDialog.<init> (Ljava/awt/Frame;Ljava/lang/String;)V
      // 8601: astore 8
      // 8603: aload 8
      // 8605: ldc_w "*.mod;*.xm;*.s3m;*.zip"
      // 8608: invokevirtual java/awt/FileDialog.setFile (Ljava/lang/String;)V
      // 860b: aload 8
      // 860d: bipush 0
      // 860e: invokevirtual java/awt/FileDialog.setMode (I)V
      // 8611: aload 8
      // 8613: bipush 1
      // 8614: invokevirtual java/awt/FileDialog.setVisible (Z)V
      // 8617: aload 8
      // 8619: invokevirtual java/awt/FileDialog.getFile ()Ljava/lang/String;
      // 861c: ifnonnull 8624
      // 861f: goto 865b
      // 8624: new java/io/File
      // 8627: dup
      // 8628: new java/lang/StringBuilder
      // 862b: dup
      // 862c: invokespecial java/lang/StringBuilder.<init> ()V
      // 862f: ldc ""
      // 8631: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8634: aload 8
      // 8636: invokevirtual java/awt/FileDialog.getDirectory ()Ljava/lang/String;
      // 8639: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 863c: ldc ""
      // 863e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8641: aload 8
      // 8643: invokevirtual java/awt/FileDialog.getFile ()Ljava/lang/String;
      // 8646: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8649: ldc ""
      // 864b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 864e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8651: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 8654: astore 7
      // 8656: goto 865b
      // 865b: goto 8662
      // 8660: astore 9
      // 8662: aload 7
      // 8664: ifnonnull 866c
      // 8667: goto 89c8
      // 866c: aload 8
      // 866e: invokevirtual java/awt/FileDialog.getFile ()Ljava/lang/String;
      // 8671: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8674: ldc_w ".mod"
      // 8677: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 867a: ifeq 8682
      // 867d: goto 86ae
      // 8682: aload 8
      // 8684: invokevirtual java/awt/FileDialog.getFile ()Ljava/lang/String;
      // 8687: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 868a: ldc_w ".xm"
      // 868d: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 8690: ifeq 8698
      // 8693: goto 86ae
      // 8698: aload 8
      // 869a: invokevirtual java/awt/FileDialog.getFile ()Ljava/lang/String;
      // 869d: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 86a0: ldc_w ".s3m"
      // 86a3: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 86a6: ifne 86ae
      // 86a9: goto 880f
      // 86ae: new java/io/File
      // 86b1: dup
      // 86b2: new java/lang/StringBuilder
      // 86b5: dup
      // 86b6: invokespecial java/lang/StringBuilder.<init> ()V
      // 86b9: ldc ""
      // 86bb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 86be: getstatic Madness.fpath Ljava/lang/String;
      // 86c1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 86c4: ldc_w "mystages/mymusic/"
      // 86c7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 86ca: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 86cd: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 86d0: astore 9
      // 86d2: aload 9
      // 86d4: invokevirtual java/io/File.exists ()Z
      // 86d7: ifeq 86df
      // 86da: goto 86ea
      // 86df: aload 9
      // 86e1: invokevirtual java/io/File.mkdirs ()Z
      // 86e4: pop
      // 86e5: goto 86ea
      // 86ea: new java/lang/StringBuilder
      // 86ed: dup
      // 86ee: invokespecial java/lang/StringBuilder.<init> ()V
      // 86f1: ldc ""
      // 86f3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 86f6: getstatic Madness.fpath Ljava/lang/String;
      // 86f9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 86fc: ldc_w "mystages/mymusic/"
      // 86ff: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8702: aload 7
      // 8704: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 8707: bipush 0
      // 8708: aload 7
      // 870a: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 870d: invokevirtual java/lang/String.length ()I
      // 8710: bipush 4
      // 8711: isub
      // 8712: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 8715: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8718: ldc_w ".zip"
      // 871b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 871e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8721: astore 10
      // 8723: new java/io/FileInputStream
      // 8726: dup
      // 8727: aload 7
      // 8729: invokespecial java/io/FileInputStream.<init> (Ljava/io/File;)V
      // 872c: astore 11
      // 872e: new java/util/zip/ZipOutputStream
      // 8731: dup
      // 8732: new java/io/FileOutputStream
      // 8735: dup
      // 8736: aload 10
      // 8738: invokespecial java/io/FileOutputStream.<init> (Ljava/lang/String;)V
      // 873b: invokespecial java/util/zip/ZipOutputStream.<init> (Ljava/io/OutputStream;)V
      // 873e: astore 12
      // 8740: new java/util/zip/ZipEntry
      // 8743: dup
      // 8744: new java/lang/StringBuilder
      // 8747: dup
      // 8748: invokespecial java/lang/StringBuilder.<init> ()V
      // 874b: ldc ""
      // 874d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8750: aload 7
      // 8752: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 8755: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8758: ldc ""
      // 875a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 875d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8760: invokespecial java/util/zip/ZipEntry.<init> (Ljava/lang/String;)V
      // 8763: astore 13
      // 8765: aload 13
      // 8767: aload 7
      // 8769: invokevirtual java/io/File.length ()J
      // 876c: invokevirtual java/util/zip/ZipEntry.setSize (J)V
      // 876f: aload 12
      // 8771: aload 13
      // 8773: invokevirtual java/util/zip/ZipOutputStream.putNextEntry (Ljava/util/zip/ZipEntry;)V
      // 8776: sipush 1024
      // 8779: newarray 8
      // 877b: astore 14
      // 877d: aload 11
      // 877f: aload 14
      // 8781: invokevirtual java/io/FileInputStream.read ([B)I
      // 8784: dup
      // 8785: istore 15
      // 8787: ifgt 878f
      // 878a: goto 879e
      // 878f: aload 12
      // 8791: aload 14
      // 8793: bipush 0
      // 8794: iload 15
      // 8796: invokevirtual java/util/zip/ZipOutputStream.write ([BII)V
      // 8799: goto 877d
      // 879e: aload 12
      // 87a0: invokevirtual java/util/zip/ZipOutputStream.closeEntry ()V
      // 87a3: aload 12
      // 87a5: invokevirtual java/util/zip/ZipOutputStream.close ()V
      // 87a8: aload 11
      // 87aa: invokevirtual java/io/FileInputStream.close ()V
      // 87ad: new java/io/File
      // 87b0: dup
      // 87b1: new java/lang/StringBuilder
      // 87b4: dup
      // 87b5: invokespecial java/lang/StringBuilder.<init> ()V
      // 87b8: ldc ""
      // 87ba: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 87bd: getstatic Madness.fpath Ljava/lang/String;
      // 87c0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 87c3: ldc_w "mystages/mymusic/"
      // 87c6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 87c9: aload 7
      // 87cb: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 87ce: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 87d1: ldc_w ".zip"
      // 87d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 87d7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 87da: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 87dd: astore 9
      // 87df: aload 9
      // 87e1: invokevirtual java/io/File.length ()J
      // 87e4: ldc2_w 1024
      // 87e7: ldiv
      // 87e8: ldc2_w 700
      // 87eb: lcmp
      // 87ec: ifge 87f4
      // 87ef: goto 880a
      // 87f4: aconst_null
      // 87f5: ldc_w "The selected file is larger then 700KB in size when zipped and therefore cannot be added!"
      // 87f8: ldc_w "Stage Maker"
      // 87fb: bipush 1
      // 87fc: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 87ff: aload 9
      // 8801: invokevirtual java/io/File.delete ()Z
      // 8804: pop
      // 8805: goto 880a
      // 880a: goto 88e9
      // 880f: aload 7
      // 8811: invokevirtual java/io/File.length ()J
      // 8814: ldc2_w 1024
      // 8817: ldiv
      // 8818: ldc2_w 700
      // 881b: lcmp
      // 881c: iflt 8824
      // 881f: goto 88de
      // 8824: new java/io/File
      // 8827: dup
      // 8828: new java/lang/StringBuilder
      // 882b: dup
      // 882c: invokespecial java/lang/StringBuilder.<init> ()V
      // 882f: ldc ""
      // 8831: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8834: getstatic Madness.fpath Ljava/lang/String;
      // 8837: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 883a: ldc_w "mystages/mymusic/"
      // 883d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8840: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8843: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 8846: astore 9
      // 8848: aload 9
      // 884a: invokevirtual java/io/File.exists ()Z
      // 884d: ifeq 8855
      // 8850: goto 8860
      // 8855: aload 9
      // 8857: invokevirtual java/io/File.mkdirs ()Z
      // 885a: pop
      // 885b: goto 8860
      // 8860: new java/io/File
      // 8863: dup
      // 8864: new java/lang/StringBuilder
      // 8867: dup
      // 8868: invokespecial java/lang/StringBuilder.<init> ()V
      // 886b: ldc ""
      // 886d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8870: getstatic Madness.fpath Ljava/lang/String;
      // 8873: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8876: ldc_w "mystages/mymusic/"
      // 8879: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 887c: aload 7
      // 887e: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 8881: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8884: ldc ""
      // 8886: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8889: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 888c: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 888f: astore 9
      // 8891: new java/io/FileInputStream
      // 8894: dup
      // 8895: aload 7
      // 8897: invokespecial java/io/FileInputStream.<init> (Ljava/io/File;)V
      // 889a: astore 10
      // 889c: new java/io/FileOutputStream
      // 889f: dup
      // 88a0: aload 9
      // 88a2: invokespecial java/io/FileOutputStream.<init> (Ljava/io/File;)V
      // 88a5: astore 11
      // 88a7: sipush 1024
      // 88aa: newarray 8
      // 88ac: astore 12
      // 88ae: aload 10
      // 88b0: aload 12
      // 88b2: invokevirtual java/io/FileInputStream.read ([B)I
      // 88b5: dup
      // 88b6: istore 13
      // 88b8: ifgt 88c0
      // 88bb: goto 88cf
      // 88c0: aload 11
      // 88c2: aload 12
      // 88c4: bipush 0
      // 88c5: iload 13
      // 88c7: invokevirtual java/io/FileOutputStream.write ([BII)V
      // 88ca: goto 88ae
      // 88cf: aload 10
      // 88d1: invokevirtual java/io/FileInputStream.close ()V
      // 88d4: aload 11
      // 88d6: invokevirtual java/io/FileOutputStream.close ()V
      // 88d9: goto 88e9
      // 88de: aconst_null
      // 88df: ldc_w "The selected file is larger then 700KB in size and therefore cannot be added!"
      // 88e2: ldc_w "Stage Maker"
      // 88e5: bipush 1
      // 88e6: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 88e9: aload 0
      // 88ea: getfield StageMaker.tracks LSmenu;
      // 88ed: invokevirtual Smenu.removeAll ()V
      // 88f0: aload 0
      // 88f1: getfield StageMaker.tracks LSmenu;
      // 88f4: aload 0
      // 88f5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 88f8: ldc_w "Select MOD Track                      "
      // 88fb: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 88fe: new java/io/File
      // 8901: dup
      // 8902: new java/lang/StringBuilder
      // 8905: dup
      // 8906: invokespecial java/lang/StringBuilder.<init> ()V
      // 8909: ldc ""
      // 890b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 890e: getstatic Madness.fpath Ljava/lang/String;
      // 8911: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8914: ldc_w "mystages/mymusic/"
      // 8917: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 891a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 891d: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 8920: invokevirtual java/io/File.list ()[Ljava/lang/String;
      // 8923: astore 9
      // 8925: aload 9
      // 8927: ifnonnull 892f
      // 892a: goto 8985
      // 892f: bipush 0
      // 8930: istore 10
      // 8932: iload 10
      // 8934: aload 9
      // 8936: arraylength
      // 8937: if_icmplt 893f
      // 893a: goto 8980
      // 893f: aload 9
      // 8941: iload 10
      // 8943: aaload
      // 8944: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8947: ldc_w ".zip"
      // 894a: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 894d: ifne 8955
      // 8950: goto 8978
      // 8955: aload 0
      // 8956: getfield StageMaker.tracks LSmenu;
      // 8959: aload 0
      // 895a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 895d: aload 9
      // 895f: iload 10
      // 8961: aaload
      // 8962: bipush 0
      // 8963: aload 9
      // 8965: iload 10
      // 8967: aaload
      // 8968: invokevirtual java/lang/String.length ()I
      // 896b: bipush 4
      // 896c: isub
      // 896d: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 8970: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 8973: goto 8978
      // 8978: iinc 10 1
      // 897b: goto 8932
      // 8980: goto 8985
      // 8985: aload 0
      // 8986: getfield StageMaker.tracks LSmenu;
      // 8989: aload 7
      // 898b: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 898e: bipush 0
      // 898f: aload 7
      // 8991: invokevirtual java/io/File.getName ()Ljava/lang/String;
      // 8994: invokevirtual java/lang/String.length ()I
      // 8997: bipush 4
      // 8998: isub
      // 8999: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 899c: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 899f: goto 89c8
      // 89a4: astore 9
      // 89a6: aconst_null
      // 89a7: new java/lang/StringBuilder
      // 89aa: dup
      // 89ab: invokespecial java/lang/StringBuilder.<init> ()V
      // 89ae: ldc_w "Unable to copy file! Error Deatials:\n"
      // 89b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 89b4: aload 9
      // 89b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 89b9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 89bc: ldc_w "Stage Maker"
      // 89bf: bipush 1
      // 89c0: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 89c3: goto 89c8
      // 89c8: goto 89cd
      // 89cd: sipush 200
      // 89d0: istore 7
      // 89d2: aload 0
      // 89d3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 89d6: new java/awt/Color
      // 89d9: dup
      // 89da: bipush 0
      // 89db: bipush 0
      // 89dc: bipush 0
      // 89dd: invokespecial java/awt/Color.<init> (III)V
      // 89e0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 89e3: aload 0
      // 89e4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 89e7: ldc_w "Sound Track"
      // 89ea: sipush 280
      // 89ed: iload 7
      // 89ef: iadd
      // 89f0: sipush 461
      // 89f3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 89f6: aload 0
      // 89f7: getfield StageMaker.trackname Ljava/lang/String;
      // 89fa: astore 8
      // 89fc: aload 8
      // 89fe: ldc ""
      // 8a00: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8a03: ifne 8a0b
      // 8a06: goto 8a15
      // 8a0b: ldc_w "No Sound Track set."
      // 8a0e: astore 8
      // 8a10: goto 8a37
      // 8a15: aload 0
      // 8a16: ldc_w "   <  Remove Track   "
      // 8a19: sipush 378
      // 8a1c: sipush 495
      // 8a1f: bipush 2
      // 8a20: bipush 0
      // 8a21: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8a24: ifne 8a2c
      // 8a27: goto 8a37
      // 8a2c: aload 0
      // 8a2d: ldc ""
      // 8a2f: putfield StageMaker.trackname Ljava/lang/String;
      // 8a32: goto 8a37
      // 8a37: aload 0
      // 8a38: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8a3b: aload 8
      // 8a3d: sipush 629
      // 8a40: aload 0
      // 8a41: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8a44: aload 8
      // 8a46: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8a49: bipush 2
      // 8a4a: idiv
      // 8a4b: isub
      // 8a4c: sipush 482
      // 8a4f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8a52: aload 0
      // 8a53: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8a56: new java/awt/Color
      // 8a59: dup
      // 8a5a: sipush 128
      // 8a5d: sipush 128
      // 8a60: sipush 128
      // 8a63: invokespecial java/awt/Color.<init> (III)V
      // 8a66: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8a69: aload 0
      // 8a6a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8a6d: sipush 270
      // 8a70: iload 7
      // 8a72: iadd
      // 8a73: sipush 457
      // 8a76: sipush 277
      // 8a79: iload 7
      // 8a7b: iadd
      // 8a7c: sipush 457
      // 8a7f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8a82: aload 0
      // 8a83: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8a86: sipush 589
      // 8a89: iload 7
      // 8a8b: iadd
      // 8a8c: sipush 457
      // 8a8f: sipush 353
      // 8a92: iload 7
      // 8a94: iadd
      // 8a95: sipush 457
      // 8a98: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8a9b: aload 0
      // 8a9c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8a9f: sipush 270
      // 8aa2: iload 7
      // 8aa4: iadd
      // 8aa5: sipush 457
      // 8aa8: sipush 270
      // 8aab: iload 7
      // 8aad: iadd
      // 8aae: sipush 497
      // 8ab1: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8ab4: aload 0
      // 8ab5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8ab8: sipush 589
      // 8abb: iload 7
      // 8abd: iadd
      // 8abe: sipush 457
      // 8ac1: sipush 589
      // 8ac4: iload 7
      // 8ac6: iadd
      // 8ac7: sipush 497
      // 8aca: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8acd: aload 0
      // 8ace: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8ad1: sipush 270
      // 8ad4: iload 7
      // 8ad6: iadd
      // 8ad7: sipush 497
      // 8ada: sipush 589
      // 8add: iload 7
      // 8adf: iadd
      // 8ae0: sipush 497
      // 8ae3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8ae6: aload 0
      // 8ae7: ldc_w " Reset "
      // 8aea: sipush 576
      // 8aed: sipush 530
      // 8af0: bipush 0
      // 8af1: bipush 1
      // 8af2: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8af5: ifne 8afd
      // 8af8: goto 8b0e
      // 8afd: aload 0
      // 8afe: ldc ""
      // 8b00: putfield StageMaker.ltrackname Ljava/lang/String;
      // 8b03: aload 0
      // 8b04: bipush -2
      // 8b06: putfield StageMaker.dtabed I
      // 8b09: goto 8b0e
      // 8b0e: aload 0
      // 8b0f: ldc_w "        Save        "
      // 8b12: sipush 663
      // 8b15: sipush 530
      // 8b18: bipush 0
      // 8b19: bipush 1
      // 8b1a: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8b1d: ifne 8b25
      // 8b20: goto 8b32
      // 8b25: aload 0
      // 8b26: invokevirtual StageMaker.sortop ()V
      // 8b29: aload 0
      // 8b2a: invokevirtual StageMaker.savefile ()V
      // 8b2d: goto 8b32
      // 8b32: goto 8b37
      // 8b37: aload 0
      // 8b38: getfield StageMaker.dtab I
      // 8b3b: bipush 6
      // 8b3d: if_icmpeq 8b45
      // 8b40: goto 8c52
      // 8b45: aload 0
      // 8b46: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8b49: new java/awt/Color
      // 8b4c: dup
      // 8b4d: bipush 0
      // 8b4e: bipush 0
      // 8b4f: bipush 0
      // 8b50: invokespecial java/awt/Color.<init> (III)V
      // 8b53: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8b56: aload 0
      // 8b57: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8b5a: new java/awt/Font
      // 8b5d: dup
      // 8b5e: ldc_w "Arial"
      // 8b61: bipush 1
      // 8b62: bipush 13
      // 8b64: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8b67: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8b6a: aload 0
      // 8b6b: aload 0
      // 8b6c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8b6f: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 8b72: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8b75: aload 0
      // 8b76: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8b79: ldc_w "Test Drive the Stage"
      // 8b7c: sipush 400
      // 8b7f: aload 0
      // 8b80: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8b83: ldc_w "Test Drive the Stage"
      // 8b86: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8b89: bipush 2
      // 8b8a: idiv
      // 8b8b: isub
      // 8b8c: sipush 470
      // 8b8f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8b92: aload 0
      // 8b93: getfield StageMaker.witho LSmenu;
      // 8b96: sipush 342
      // 8b99: sipush 480
      // 8b9c: invokevirtual Smenu.move (II)V
      // 8b9f: aload 0
      // 8ba0: getfield StageMaker.witho LSmenu;
      // 8ba3: invokevirtual Smenu.isShowing ()Z
      // 8ba6: ifeq 8bae
      // 8ba9: goto 8bba
      // 8bae: aload 0
      // 8baf: getfield StageMaker.witho LSmenu;
      // 8bb2: invokevirtual Smenu.show ()V
      // 8bb5: goto 8bba
      // 8bba: aload 0
      // 8bbb: ldc_w "     TEST DRIVE!     "
      // 8bbe: sipush 400
      // 8bc1: sipush 530
      // 8bc4: bipush 0
      // 8bc5: bipush 1
      // 8bc6: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8bc9: ifne 8bd1
      // 8bcc: goto 8c52
      // 8bd1: aload 0
      // 8bd2: invokevirtual StageMaker.savefile ()V
      // 8bd5: aload 0
      // 8bd6: bipush 0
      // 8bd7: putfield StageMaker.errd I
      // 8bda: aload 0
      // 8bdb: bipush 3
      // 8bdc: invokevirtual StageMaker.readstage (I)V
      // 8bdf: aload 0
      // 8be0: getfield StageMaker.cp LCheckPoints;
      // 8be3: getfield CheckPoints.nsp I
      // 8be6: bipush 2
      // 8be7: if_icmplt 8bef
      // 8bea: goto 8bfa
      // 8bef: aload 0
      // 8bf0: bipush 7
      // 8bf2: putfield StageMaker.errd I
      // 8bf5: goto 8bfa
      // 8bfa: aload 0
      // 8bfb: getfield StageMaker.errd I
      // 8bfe: ifeq 8c06
      // 8c01: goto 8c21
      // 8c06: aload 0
      // 8c07: getfield StageMaker.stagename Ljava/lang/String;
      // 8c0a: putstatic Madness.testcar Ljava/lang/String;
      // 8c0d: aload 0
      // 8c0e: getfield StageMaker.witho LSmenu;
      // 8c11: invokevirtual Smenu.getSelectedIndex ()I
      // 8c14: bipush 3
      // 8c15: iadd
      // 8c16: putstatic Madness.testdrive I
      // 8c19: invokestatic Madness.game ()V
      // 8c1c: goto 8c52
      // 8c21: aconst_null
      // 8c22: new java/lang/StringBuilder
      // 8c25: dup
      // 8c26: invokespecial java/lang/StringBuilder.<init> ()V
      // 8c29: ldc_w "Error!  This stage is not ready for a test drive!\nReason:\n"
      // 8c2c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8c2f: aload 0
      // 8c30: getfield StageMaker.errlo [Ljava/lang/String;
      // 8c33: aload 0
      // 8c34: getfield StageMaker.errd I
      // 8c37: bipush 1
      // 8c38: isub
      // 8c39: aaload
      // 8c3a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8c3d: ldc_w "\n\n"
      // 8c40: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8c43: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8c46: ldc_w "Stage Maker"
      // 8c49: bipush 0
      // 8c4a: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 8c4d: goto 8c52
      // 8c52: aload 0
      // 8c53: getfield StageMaker.dtabed I
      // 8c56: aload 0
      // 8c57: getfield StageMaker.dtab I
      // 8c5a: if_icmpne 8c62
      // 8c5d: goto 8c87
      // 8c62: aload 0
      // 8c63: getfield StageMaker.dtabed I
      // 8c66: bipush -2
      // 8c68: if_icmpeq 8c70
      // 8c6b: goto 8c7a
      // 8c70: aload 0
      // 8c71: bipush -1
      // 8c72: putfield StageMaker.dtabed I
      // 8c75: goto 8c87
      // 8c7a: aload 0
      // 8c7b: aload 0
      // 8c7c: getfield StageMaker.dtab I
      // 8c7f: putfield StageMaker.dtabed I
      // 8c82: goto 8c87
      // 8c87: goto 8c8c
      // 8c8c: aload 0
      // 8c8d: getfield StageMaker.tab I
      // 8c90: bipush 3
      // 8c91: if_icmpeq 8c99
      // 8c94: goto ab2b
      // 8c99: aload 0
      // 8c9a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8c9d: new java/awt/Font
      // 8ca0: dup
      // 8ca1: ldc_w "Arial"
      // 8ca4: bipush 1
      // 8ca5: bipush 13
      // 8ca7: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8caa: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8cad: aload 0
      // 8cae: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8cb1: new java/awt/Color
      // 8cb4: dup
      // 8cb5: bipush 0
      // 8cb6: bipush 0
      // 8cb7: bipush 0
      // 8cb8: invokespecial java/awt/Color.<init> (III)V
      // 8cbb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8cbe: aload 0
      // 8cbf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8cc2: new java/lang/StringBuilder
      // 8cc5: dup
      // 8cc6: invokespecial java/lang/StringBuilder.<init> ()V
      // 8cc9: ldc_w "Publish Stage :  [ "
      // 8ccc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8ccf: aload 0
      // 8cd0: getfield StageMaker.stagename Ljava/lang/String;
      // 8cd3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8cd6: ldc_w " ]"
      // 8cd9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8cdc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8cdf: bipush 30
      // 8ce1: bipush 50
      // 8ce3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8ce6: aload 0
      // 8ce7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8cea: ldc_w "Publishing Type :"
      // 8ced: bipush 30
      // 8cef: bipush 80
      // 8cf1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8cf4: aload 0
      // 8cf5: getfield StageMaker.pubtyp LSmenu;
      // 8cf8: sipush 150
      // 8cfb: bipush 63
      // 8cfd: invokevirtual Smenu.move (II)V
      // 8d00: aload 0
      // 8d01: getfield StageMaker.pubtyp LSmenu;
      // 8d04: invokevirtual Smenu.isShowing ()Z
      // 8d07: ifeq 8d0f
      // 8d0a: goto 8d23
      // 8d0f: aload 0
      // 8d10: getfield StageMaker.pubtyp LSmenu;
      // 8d13: invokevirtual Smenu.show ()V
      // 8d16: aload 0
      // 8d17: getfield StageMaker.pubtyp LSmenu;
      // 8d1a: bipush 1
      // 8d1b: invokevirtual Smenu.select (I)V
      // 8d1e: goto 8d23
      // 8d23: aload 0
      // 8d24: getfield StageMaker.pubitem LSmenu;
      // 8d27: sipush 790
      // 8d2a: aload 0
      // 8d2b: getfield StageMaker.pubitem LSmenu;
      // 8d2e: getfield Smenu.w I
      // 8d31: isub
      // 8d32: bipush 96
      // 8d34: invokevirtual Smenu.move (II)V
      // 8d37: aload 0
      // 8d38: getfield StageMaker.pubitem LSmenu;
      // 8d3b: invokevirtual Smenu.isShowing ()Z
      // 8d3e: ifeq 8d46
      // 8d41: goto 8d52
      // 8d46: aload 0
      // 8d47: getfield StageMaker.pubitem LSmenu;
      // 8d4a: invokevirtual Smenu.show ()V
      // 8d4d: goto 8d52
      // 8d52: aload 0
      // 8d53: getfield StageMaker.pubitem LSmenu;
      // 8d56: getfield Smenu.sel I
      // 8d59: ifne 8d61
      // 8d5c: goto 8db1
      // 8d61: bipush 0
      // 8d62: istore 1
      // 8d63: bipush 0
      // 8d64: istore 2
      // 8d65: iload 2
      // 8d66: aload 0
      // 8d67: getfield StageMaker.nms I
      // 8d6a: if_icmplt 8d72
      // 8d6d: goto 8d99
      // 8d72: aload 0
      // 8d73: getfield StageMaker.pubitem LSmenu;
      // 8d76: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 8d79: aload 0
      // 8d7a: getfield StageMaker.mystages [Ljava/lang/String;
      // 8d7d: iload 2
      // 8d7e: aaload
      // 8d7f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8d82: ifne 8d8a
      // 8d85: goto 8d91
      // 8d8a: bipush 1
      // 8d8b: istore 1
      // 8d8c: goto 8d91
      // 8d91: iinc 2 1
      // 8d94: goto 8d65
      // 8d99: iload 1
      // 8d9a: ifeq 8da2
      // 8d9d: goto 8dac
      // 8da2: aload 0
      // 8da3: bipush 2
      // 8da4: putfield StageMaker.logged I
      // 8da7: goto 8dac
      // 8dac: goto 8db1
      // 8db1: aload 0
      // 8db2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8db5: new java/awt/Color
      // 8db8: dup
      // 8db9: bipush 0
      // 8dba: bipush 0
      // 8dbb: bipush 0
      // 8dbc: invokespecial java/awt/Color.<init> (III)V
      // 8dbf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8dc2: aload 0
      // 8dc3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8dc6: new java/awt/Font
      // 8dc9: dup
      // 8dca: ldc_w "Arial"
      // 8dcd: bipush 0
      // 8dce: bipush 12
      // 8dd0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8dd3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8dd6: aload 0
      // 8dd7: getfield StageMaker.pubtyp LSmenu;
      // 8dda: invokevirtual Smenu.getSelectedIndex ()I
      // 8ddd: ifeq 8de5
      // 8de0: goto 8e08
      // 8de5: aload 0
      // 8de6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8de9: ldc_w "Private :  This means only you can have your stage in your account and no one else can add"
      // 8dec: sipush 268
      // 8def: bipush 72
      // 8df1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8df4: aload 0
      // 8df5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8df8: ldc_w "it to their account to play it!"
      // 8dfb: sipush 268
      // 8dfe: bipush 88
      // 8e00: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8e03: goto 8e08
      // 8e08: aload 0
      // 8e09: getfield StageMaker.pubtyp LSmenu;
      // 8e0c: invokevirtual Smenu.getSelectedIndex ()I
      // 8e0f: bipush 1
      // 8e10: if_icmpeq 8e18
      // 8e13: goto 8e3b
      // 8e18: aload 0
      // 8e19: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e1c: ldc_w "Public :  This means anyone can add this stage to their account to play it, but only you can"
      // 8e1f: sipush 268
      // 8e22: bipush 72
      // 8e24: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8e27: aload 0
      // 8e28: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e2b: ldc_w "download it to your Stage Maker and edit it (no one else but you can edit it)."
      // 8e2e: sipush 268
      // 8e31: bipush 88
      // 8e33: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8e36: goto 8e3b
      // 8e3b: aload 0
      // 8e3c: getfield StageMaker.pubtyp LSmenu;
      // 8e3f: invokevirtual Smenu.getSelectedIndex ()I
      // 8e42: bipush 2
      // 8e43: if_icmpeq 8e4b
      // 8e46: goto 8e6e
      // 8e4b: aload 0
      // 8e4c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e4f: ldc_w "Super Public :  This means anyone can add this stage to their account to play it and can also"
      // 8e52: sipush 268
      // 8e55: bipush 72
      // 8e57: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8e5a: aload 0
      // 8e5b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e5e: ldc_w "download it to their stage Maker, edit it and publish it."
      // 8e61: sipush 268
      // 8e64: bipush 88
      // 8e66: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8e69: goto 8e6e
      // 8e6e: aload 0
      // 8e6f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e72: new java/awt/Font
      // 8e75: dup
      // 8e76: ldc_w "Arial"
      // 8e79: bipush 1
      // 8e7a: bipush 12
      // 8e7c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8e7f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8e82: aload 0
      // 8e83: aload 0
      // 8e84: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e87: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 8e8a: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8e8d: aload 0
      // 8e8e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8e91: ldc_w "Stage Name"
      // 8e94: sipush 180
      // 8e97: aload 0
      // 8e98: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8e9b: ldc_w "Stage Name"
      // 8e9e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8ea1: bipush 2
      // 8ea2: idiv
      // 8ea3: isub
      // 8ea4: sipush 138
      // 8ea7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8eaa: aload 0
      // 8eab: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8eae: ldc_w "Created By"
      // 8eb1: sipush 400
      // 8eb4: aload 0
      // 8eb5: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8eb8: ldc_w "Created By"
      // 8ebb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8ebe: bipush 2
      // 8ebf: idiv
      // 8ec0: isub
      // 8ec1: sipush 138
      // 8ec4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8ec7: aload 0
      // 8ec8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8ecb: ldc_w "Added By"
      // 8ece: sipush 500
      // 8ed1: aload 0
      // 8ed2: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8ed5: ldc_w "Added By"
      // 8ed8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8edb: bipush 2
      // 8edc: idiv
      // 8edd: isub
      // 8ede: sipush 138
      // 8ee1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8ee4: aload 0
      // 8ee5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8ee8: ldc_w "Publish Type"
      // 8eeb: sipush 600
      // 8eee: aload 0
      // 8eef: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8ef2: ldc_w "Publish Type"
      // 8ef5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8ef8: bipush 2
      // 8ef9: idiv
      // 8efa: isub
      // 8efb: sipush 138
      // 8efe: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8f01: aload 0
      // 8f02: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f05: ldc_w "Options"
      // 8f08: sipush 720
      // 8f0b: aload 0
      // 8f0c: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 8f0f: ldc_w "Options"
      // 8f12: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8f15: bipush 2
      // 8f16: idiv
      // 8f17: isub
      // 8f18: sipush 138
      // 8f1b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8f1e: aload 0
      // 8f1f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f22: sipush 350
      // 8f25: sipush 129
      // 8f28: sipush 350
      // 8f2b: sipush 140
      // 8f2e: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8f31: aload 0
      // 8f32: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f35: sipush 450
      // 8f38: sipush 129
      // 8f3b: sipush 450
      // 8f3e: sipush 140
      // 8f41: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8f44: aload 0
      // 8f45: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f48: sipush 550
      // 8f4b: sipush 129
      // 8f4e: sipush 550
      // 8f51: sipush 140
      // 8f54: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8f57: aload 0
      // 8f58: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f5b: sipush 650
      // 8f5e: sipush 129
      // 8f61: sipush 650
      // 8f64: sipush 140
      // 8f67: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8f6a: aload 0
      // 8f6b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8f6e: bipush 10
      // 8f70: sipush 140
      // 8f73: sipush 780
      // 8f76: sipush 402
      // 8f79: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 8f7c: aload 0
      // 8f7d: ldc_w "       Publish  >       "
      // 8f80: bipush 102
      // 8f82: bipush 110
      // 8f84: bipush 0
      // 8f85: bipush 1
      // 8f86: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // 8f89: ifne 8f91
      // 8f8c: goto 96e3
      // 8f91: aload 0
      // 8f92: getfield StageMaker.logged I
      // 8f95: ifeq 8f9d
      // 8f98: goto 8fad
      // 8f9d: aconst_null
      // 8f9e: ldc_w "Please login to retrieve your account first before publishing!"
      // 8fa1: ldc_w "Stage Maker"
      // 8fa4: bipush 1
      // 8fa5: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 8fa8: goto 8fad
      // 8fad: aload 0
      // 8fae: getfield StageMaker.logged I
      // 8fb1: bipush 3
      // 8fb2: if_icmpne 8fba
      // 8fb5: goto 8fc7
      // 8fba: aload 0
      // 8fbb: getfield StageMaker.logged I
      // 8fbe: bipush -1
      // 8fbf: if_icmpeq 8fc7
      // 8fc2: goto 96e3
      // 8fc7: aload 0
      // 8fc8: invokevirtual StageMaker.savefile ()V
      // 8fcb: aload 0
      // 8fcc: bipush 0
      // 8fcd: putfield StageMaker.errd I
      // 8fd0: aload 0
      // 8fd1: bipush 3
      // 8fd2: invokevirtual StageMaker.readstage (I)V
      // 8fd5: aload 0
      // 8fd6: getfield StageMaker.cp LCheckPoints;
      // 8fd9: getfield CheckPoints.nsp I
      // 8fdc: bipush 2
      // 8fdd: if_icmplt 8fe5
      // 8fe0: goto 8ff0
      // 8fe5: aload 0
      // 8fe6: bipush 7
      // 8fe8: putfield StageMaker.errd I
      // 8feb: goto 8ff0
      // 8ff0: aload 0
      // 8ff1: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 8ff4: new java/awt/Font
      // 8ff7: dup
      // 8ff8: ldc_w "Arial"
      // 8ffb: bipush 1
      // 8ffc: bipush 12
      // 8ffe: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9001: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9004: aload 0
      // 9005: aload 0
      // 9006: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9009: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 900c: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 900f: aload 0
      // 9010: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9013: aload 0
      // 9014: getfield StageMaker.stagename Ljava/lang/String;
      // 9017: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 901a: sipush 274
      // 901d: if_icmpgt 9025
      // 9020: goto 9030
      // 9025: aload 0
      // 9026: bipush 8
      // 9028: putfield StageMaker.errd I
      // 902b: goto 9030
      // 9030: aload 0
      // 9031: getfield StageMaker.errd I
      // 9034: ifeq 903c
      // 9037: goto 96b2
      // 903c: bipush 0
      // 903d: istore 1
      // 903e: bipush 0
      // 903f: istore 2
      // 9040: iload 2
      // 9041: aload 0
      // 9042: getfield StageMaker.pubitem LSmenu;
      // 9045: getfield Smenu.no I
      // 9048: if_icmplt 9050
      // 904b: goto 909b
      // 9050: aload 0
      // 9051: getfield StageMaker.pubitem LSmenu;
      // 9054: getfield Smenu.opts [Ljava/lang/String;
      // 9057: iload 2
      // 9058: aaload
      // 9059: aload 0
      // 905a: getfield StageMaker.stagename Ljava/lang/String;
      // 905d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9060: ifne 9068
      // 9063: goto 9093
      // 9068: aconst_null
      // 9069: new java/lang/StringBuilder
      // 906c: dup
      // 906d: invokespecial java/lang/StringBuilder.<init> ()V
      // 9070: ldc_w "Replace your already online stage '"
      // 9073: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9076: aload 0
      // 9077: getfield StageMaker.stagename Ljava/lang/String;
      // 907a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 907d: ldc_w "' with this one?"
      // 9080: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9083: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9086: ldc_w "Stage Maker"
      // 9089: bipush 0
      // 908a: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 908d: istore 1
      // 908e: goto 9093
      // 9093: iinc 2 1
      // 9096: goto 9040
      // 909b: iload 1
      // 909c: ifeq 90a4
      // 909f: goto 96ad
      // 90a4: aload 0
      // 90a5: new java/awt/Cursor
      // 90a8: dup
      // 90a9: bipush 3
      // 90aa: invokespecial java/awt/Cursor.<init> (I)V
      // 90ad: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 90b0: aload 0
      // 90b1: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 90b4: new java/awt/Font
      // 90b7: dup
      // 90b8: ldc_w "Arial"
      // 90bb: bipush 1
      // 90bc: bipush 13
      // 90be: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 90c1: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 90c4: aload 0
      // 90c5: aload 0
      // 90c6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 90c9: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 90cc: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 90cf: aload 0
      // 90d0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 90d3: new java/awt/Color
      // 90d6: dup
      // 90d7: sipush 225
      // 90da: sipush 225
      // 90dd: sipush 225
      // 90e0: invokespecial java/awt/Color.<init> (III)V
      // 90e3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 90e6: aload 0
      // 90e7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 90ea: bipush 11
      // 90ec: sipush 141
      // 90ef: sipush 779
      // 90f2: sipush 401
      // 90f5: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 90f8: aload 0
      // 90f9: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 90fc: new java/awt/Color
      // 90ff: dup
      // 9100: bipush 0
      // 9101: bipush 0
      // 9102: bipush 0
      // 9103: invokespecial java/awt/Color.<init> (III)V
      // 9106: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9109: aload 0
      // 910a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 910d: ldc_w "Connecting to Server..."
      // 9110: sipush 400
      // 9113: aload 0
      // 9114: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9117: ldc_w "Connecting to Server..."
      // 911a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 911d: bipush 2
      // 911e: idiv
      // 911f: isub
      // 9120: sipush 250
      // 9123: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9126: aload 0
      // 9127: invokevirtual StageMaker.repaint ()V
      // 912a: aload 0
      // 912b: aload 0
      // 912c: getfield StageMaker.stagename Ljava/lang/String;
      // 912f: putfield StageMaker.justpubd Ljava/lang/String;
      // 9132: bipush -1
      // 9133: istore 2
      // 9134: new java/net/Socket
      // 9137: dup
      // 9138: ldc_w "multiplayer.needformadness.com"
      // 913b: sipush 7061
      // 913e: invokespecial java/net/Socket.<init> (Ljava/lang/String;I)V
      // 9141: astore 3
      // 9142: new java/io/BufferedReader
      // 9145: dup
      // 9146: new java/io/InputStreamReader
      // 9149: dup
      // 914a: aload 3
      // 914b: invokevirtual java/net/Socket.getInputStream ()Ljava/io/InputStream;
      // 914e: invokespecial java/io/InputStreamReader.<init> (Ljava/io/InputStream;)V
      // 9151: invokespecial java/io/BufferedReader.<init> (Ljava/io/Reader;)V
      // 9154: astore 4
      // 9156: new java/io/PrintWriter
      // 9159: dup
      // 915a: aload 3
      // 915b: invokevirtual java/net/Socket.getOutputStream ()Ljava/io/OutputStream;
      // 915e: bipush 1
      // 915f: invokespecial java/io/PrintWriter.<init> (Ljava/io/OutputStream;Z)V
      // 9162: astore 5
      // 9164: aconst_null
      // 9165: astore 6
      // 9167: aload 5
      // 9169: new java/lang/StringBuilder
      // 916c: dup
      // 916d: invokespecial java/lang/StringBuilder.<init> ()V
      // 9170: ldc_w "20|"
      // 9173: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9176: aload 0
      // 9177: getfield StageMaker.tnick Ljava/awt/TextField;
      // 917a: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 917d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9180: ldc_w "|"
      // 9183: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9186: aload 0
      // 9187: getfield StageMaker.tpass Ljava/awt/TextField;
      // 918a: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 918d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9190: ldc_w "|"
      // 9193: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9196: aload 0
      // 9197: getfield StageMaker.stagename Ljava/lang/String;
      // 919a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 919d: ldc_w "|"
      // 91a0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 91a3: aload 0
      // 91a4: getfield StageMaker.pubtyp LSmenu;
      // 91a7: invokevirtual Smenu.getSelectedIndex ()I
      // 91aa: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 91ad: ldc_w "|"
      // 91b0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 91b3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 91b6: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 91b9: aload 4
      // 91bb: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 91be: astore 7
      // 91c0: aload 7
      // 91c2: ifnonnull 91ca
      // 91c5: goto 91d7
      // 91ca: aload 0
      // 91cb: aload 7
      // 91cd: bipush 0
      // 91ce: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // 91d1: istore 2
      // 91d2: goto 91d7
      // 91d7: iload 2
      // 91d8: ifeq 91e0
      // 91db: goto 9546
      // 91e0: ldc_w " Publishing Stage "
      // 91e3: astore 8
      // 91e5: new java/lang/StringBuilder
      // 91e8: dup
      // 91e9: invokespecial java/lang/StringBuilder.<init> ()V
      // 91ec: ldc ""
      // 91ee: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 91f1: aload 0
      // 91f2: getfield StageMaker.tstage Ljava/lang/String;
      // 91f5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 91f8: ldc_w "\r\n"
      // 91fb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 91fe: aload 0
      // 91ff: getfield StageMaker.bstage Ljava/lang/String;
      // 9202: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9205: ldc ""
      // 9207: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 920a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 920d: astore 9
      // 920f: new java/io/DataInputStream
      // 9212: dup
      // 9213: new java/io/ByteArrayInputStream
      // 9216: dup
      // 9217: aload 9
      // 9219: invokevirtual java/lang/String.getBytes ()[B
      // 921c: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 921f: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 9222: astore 10
      // 9224: aconst_null
      // 9225: astore 11
      // 9227: aload 10
      // 9229: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // 922c: dup
      // 922d: astore 11
      // 922f: ifnonnull 9237
      // 9232: goto 92ec
      // 9237: aload 11
      // 9239: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 923c: astore 11
      // 923e: aload 5
      // 9240: aload 11
      // 9242: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 9245: aload 0
      // 9246: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9249: new java/awt/Color
      // 924c: dup
      // 924d: sipush 225
      // 9250: sipush 225
      // 9253: sipush 225
      // 9256: invokespecial java/awt/Color.<init> (III)V
      // 9259: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 925c: aload 0
      // 925d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9260: bipush 11
      // 9262: sipush 141
      // 9265: sipush 779
      // 9268: sipush 401
      // 926b: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 926e: aload 0
      // 926f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9272: new java/awt/Color
      // 9275: dup
      // 9276: bipush 0
      // 9277: bipush 0
      // 9278: bipush 0
      // 9279: invokespecial java/awt/Color.<init> (III)V
      // 927c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 927f: aload 0
      // 9280: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9283: aload 8
      // 9285: sipush 400
      // 9288: aload 0
      // 9289: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 928c: aload 8
      // 928e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9291: bipush 2
      // 9292: idiv
      // 9293: isub
      // 9294: sipush 250
      // 9297: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 929a: new java/lang/StringBuilder
      // 929d: dup
      // 929e: invokespecial java/lang/StringBuilder.<init> ()V
      // 92a1: ldc_w "| "
      // 92a4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 92a7: aload 8
      // 92a9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 92ac: ldc_w " |"
      // 92af: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 92b2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 92b5: astore 8
      // 92b7: aload 8
      // 92b9: ldc_w "| | | | | | | | | | | | | | | | | | | | | | | |  Publishing Stage  | | | | | | | | | | | | | | | | | | | | | | | |"
      // 92bc: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 92bf: ifne 92c7
      // 92c2: goto 92d1
      // 92c7: ldc_w " Publishing Stage "
      // 92ca: astore 8
      // 92cc: goto 92d1
      // 92d1: aload 0
      // 92d2: invokevirtual StageMaker.repaint ()V
      // 92d5: aload 0
      // 92d6: getfield StageMaker.thredo Ljava/lang/Thread;
      // 92d9: pop
      // 92da: ldc2_w 10
      // 92dd: invokestatic java/lang/Thread.sleep (J)V
      // 92e0: goto 9227
      // 92e5: astore 12
      // 92e7: goto 9227
      // 92ec: aload 5
      // 92ee: ldc_w "QUITX1111"
      // 92f1: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 92f4: aload 0
      // 92f5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 92f8: new java/awt/Color
      // 92fb: dup
      // 92fc: sipush 225
      // 92ff: sipush 225
      // 9302: sipush 225
      // 9305: invokespecial java/awt/Color.<init> (III)V
      // 9308: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 930b: aload 0
      // 930c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 930f: bipush 11
      // 9311: sipush 141
      // 9314: sipush 779
      // 9317: sipush 401
      // 931a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 931d: aload 0
      // 931e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9321: new java/awt/Color
      // 9324: dup
      // 9325: bipush 0
      // 9326: bipush 0
      // 9327: bipush 0
      // 9328: invokespecial java/awt/Color.<init> (III)V
      // 932b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 932e: aload 0
      // 932f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9332: ldc_w "Creating the stage online..."
      // 9335: sipush 400
      // 9338: aload 0
      // 9339: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 933c: ldc_w "Creating the stage online..."
      // 933f: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9342: bipush 2
      // 9343: idiv
      // 9344: isub
      // 9345: sipush 250
      // 9348: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 934b: aload 0
      // 934c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 934f: ldc_w "This may take a couple of minutes, please wait..."
      // 9352: sipush 400
      // 9355: aload 0
      // 9356: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9359: ldc_w "This may take a couple of minutes, please wait..."
      // 935c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 935f: bipush 2
      // 9360: idiv
      // 9361: isub
      // 9362: sipush 280
      // 9365: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9368: aload 0
      // 9369: invokevirtual StageMaker.repaint ()V
      // 936c: aload 4
      // 936e: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 9371: astore 7
      // 9373: aload 7
      // 9375: ifnonnull 937d
      // 9378: goto 938a
      // 937d: aload 0
      // 937e: aload 7
      // 9380: bipush 0
      // 9381: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // 9384: istore 2
      // 9385: goto 938c
      // 938a: bipush -1
      // 938b: istore 2
      // 938c: iload 2
      // 938d: ifeq 9395
      // 9390: goto 9541
      // 9395: aload 0
      // 9396: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9399: new java/awt/Color
      // 939c: dup
      // 939d: sipush 225
      // 93a0: sipush 225
      // 93a3: sipush 225
      // 93a6: invokespecial java/awt/Color.<init> (III)V
      // 93a9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 93ac: aload 0
      // 93ad: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 93b0: bipush 11
      // 93b2: sipush 141
      // 93b5: sipush 779
      // 93b8: sipush 401
      // 93bb: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 93be: aload 0
      // 93bf: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 93c2: new java/awt/Color
      // 93c5: dup
      // 93c6: bipush 0
      // 93c7: bipush 0
      // 93c8: bipush 0
      // 93c9: invokespecial java/awt/Color.<init> (III)V
      // 93cc: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 93cf: aload 0
      // 93d0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 93d3: ldc_w "Uploading stage's sound track..."
      // 93d6: sipush 400
      // 93d9: aload 0
      // 93da: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 93dd: ldc_w "Uploading Stage's Sound Track..."
      // 93e0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 93e3: bipush 2
      // 93e4: idiv
      // 93e5: isub
      // 93e6: sipush 250
      // 93e9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 93ec: aload 0
      // 93ed: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 93f0: ldc_w "This may take a couple of minutes, please wait..."
      // 93f3: sipush 400
      // 93f6: aload 0
      // 93f7: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 93fa: ldc_w "This may take a couple of minutes, please wait..."
      // 93fd: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9400: bipush 2
      // 9401: idiv
      // 9402: isub
      // 9403: sipush 280
      // 9406: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9409: aload 0
      // 940a: invokevirtual StageMaker.repaint ()V
      // 940d: new java/io/File
      // 9410: dup
      // 9411: new java/lang/StringBuilder
      // 9414: dup
      // 9415: invokespecial java/lang/StringBuilder.<init> ()V
      // 9418: ldc ""
      // 941a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 941d: getstatic Madness.fpath Ljava/lang/String;
      // 9420: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9423: ldc_w "mystages/mymusic/"
      // 9426: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9429: aload 0
      // 942a: getfield StageMaker.trackname Ljava/lang/String;
      // 942d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9430: ldc_w ".zip"
      // 9433: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9436: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9439: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 943c: astore 12
      // 943e: aload 0
      // 943f: getfield StageMaker.trackname Ljava/lang/String;
      // 9442: ldc ""
      // 9444: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9447: ifeq 944f
      // 944a: goto 952d
      // 944f: aload 12
      // 9451: invokevirtual java/io/File.exists ()Z
      // 9454: ifne 945c
      // 9457: goto 952d
      // 945c: aload 12
      // 945e: invokevirtual java/io/File.length ()J
      // 9461: l2i
      // 9462: istore 13
      // 9464: aload 5
      // 9466: new java/lang/StringBuilder
      // 9469: dup
      // 946a: invokespecial java/lang/StringBuilder.<init> ()V
      // 946d: ldc_w "track|"
      // 9470: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9473: aload 0
      // 9474: getfield StageMaker.trackname Ljava/lang/String;
      // 9477: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 947a: ldc_w "|"
      // 947d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9480: iload 13
      // 9482: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9485: ldc_w "|"
      // 9488: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 948b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 948e: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 9491: aload 4
      // 9493: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 9496: astore 7
      // 9498: aload 7
      // 949a: ifnonnull 94a2
      // 949d: goto 94af
      // 94a2: aload 0
      // 94a3: aload 7
      // 94a5: bipush 0
      // 94a6: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // 94a9: istore 2
      // 94aa: goto 94b2
      // 94af: bipush -2
      // 94b1: istore 2
      // 94b2: iload 2
      // 94b3: ifeq 94bb
      // 94b6: goto 9516
      // 94bb: new java/io/FileInputStream
      // 94be: dup
      // 94bf: aload 12
      // 94c1: invokespecial java/io/FileInputStream.<init> (Ljava/io/File;)V
      // 94c4: astore 14
      // 94c6: iload 13
      // 94c8: newarray 8
      // 94ca: astore 15
      // 94cc: aload 14
      // 94ce: aload 15
      // 94d0: invokevirtual java/io/FileInputStream.read ([B)I
      // 94d3: pop
      // 94d4: aload 14
      // 94d6: invokevirtual java/io/FileInputStream.close ()V
      // 94d9: new java/io/DataOutputStream
      // 94dc: dup
      // 94dd: aload 3
      // 94de: invokevirtual java/net/Socket.getOutputStream ()Ljava/io/OutputStream;
      // 94e1: invokespecial java/io/DataOutputStream.<init> (Ljava/io/OutputStream;)V
      // 94e4: astore 6
      // 94e6: aload 6
      // 94e8: aload 15
      // 94ea: bipush 0
      // 94eb: iload 13
      // 94ed: invokevirtual java/io/DataOutputStream.write ([BII)V
      // 94f0: aload 4
      // 94f2: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 94f5: astore 7
      // 94f7: aload 7
      // 94f9: ifnonnull 9501
      // 94fc: goto 950e
      // 9501: aload 0
      // 9502: aload 7
      // 9504: bipush 0
      // 9505: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // 9508: istore 2
      // 9509: goto 9511
      // 950e: bipush -2
      // 9510: istore 2
      // 9511: goto 9516
      // 9516: iload 2
      // 9517: bipush -67
      // 9519: if_icmpeq 9521
      // 951c: goto 9528
      // 9521: bipush 0
      // 9522: istore 2
      // 9523: goto 9528
      // 9528: goto 953c
      // 952d: aload 5
      // 952f: ldc_w "END"
      // 9532: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 9535: aload 4
      // 9537: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 953a: astore 7
      // 953c: goto 9541
      // 9541: goto 9546
      // 9546: aload 3
      // 9547: invokevirtual java/net/Socket.close ()V
      // 954a: goto 9552
      // 954f: astore 3
      // 9550: bipush -1
      // 9551: istore 2
      // 9552: aload 0
      // 9553: new java/awt/Cursor
      // 9556: dup
      // 9557: bipush 0
      // 9558: invokespecial java/awt/Cursor.<init> (I)V
      // 955b: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 955e: bipush 0
      // 955f: istore 3
      // 9560: iload 2
      // 9561: ifeq 9569
      // 9564: goto 9575
      // 9569: aload 0
      // 956a: bipush 1
      // 956b: putfield StageMaker.logged I
      // 956e: bipush 1
      // 956f: istore 3
      // 9570: goto 9575
      // 9575: iload 2
      // 9576: bipush 3
      // 9577: if_icmpeq 957f
      // 957a: goto 95ae
      // 957f: aconst_null
      // 9580: new java/lang/StringBuilder
      // 9583: dup
      // 9584: invokespecial java/lang/StringBuilder.<init> ()V
      // 9587: ldc_w "Unable to publish stage.\nReason:\n"
      // 958a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 958d: aload 0
      // 958e: getfield StageMaker.errlo [Ljava/lang/String;
      // 9591: bipush 8
      // 9593: aaload
      // 9594: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9597: ldc_w "\n\n"
      // 959a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 959d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 95a0: ldc_w "Stage Maker"
      // 95a3: bipush 1
      // 95a4: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 95a7: bipush 1
      // 95a8: istore 3
      // 95a9: goto 95ae
      // 95ae: iload 2
      // 95af: bipush 4
      // 95b0: if_icmpeq 95b8
      // 95b3: goto 95f1
      // 95b8: aconst_null
      // 95b9: new java/lang/StringBuilder
      // 95bc: dup
      // 95bd: invokespecial java/lang/StringBuilder.<init> ()V
      // 95c0: ldc_w "Unable to publish stage.\nReason:\nStage name used ("
      // 95c3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 95c6: aload 0
      // 95c7: getfield StageMaker.stagename Ljava/lang/String;
      // 95ca: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 95cd: ldc_w ").\nThe name '"
      // 95d0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 95d3: aload 0
      // 95d4: getfield StageMaker.stagename Ljava/lang/String;
      // 95d7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 95da: ldc_w "' is already used by another published stage.\nPlease rename your stage.\n\n"
      // 95dd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 95e0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 95e3: ldc_w "Stage Maker"
      // 95e6: bipush 1
      // 95e7: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 95ea: bipush 1
      // 95eb: istore 3
      // 95ec: goto 95f1
      // 95f1: iload 2
      // 95f2: bipush 5
      // 95f3: if_icmpeq 95fb
      // 95f6: goto 960d
      // 95fb: aconst_null
      // 95fc: ldc_w "Unable to create stage online!  Unknown Error.  Please try again later."
      // 95ff: ldc_w "Stage Maker"
      // 9602: bipush 1
      // 9603: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9606: bipush 1
      // 9607: istore 3
      // 9608: goto 960d
      // 960d: iload 2
      // 960e: bipush 5
      // 960f: if_icmpgt 9617
      // 9612: goto 9629
      // 9617: aconst_null
      // 9618: ldc_w "Unable to publish stage fully!  Unknown Error.  Please try again later."
      // 961b: ldc_w "Stage Maker"
      // 961e: bipush 1
      // 961f: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9622: bipush 1
      // 9623: istore 3
      // 9624: goto 9629
      // 9629: iload 2
      // 962a: bipush -4
      // 962c: if_icmpeq 9634
      // 962f: goto 964b
      // 9634: aload 0
      // 9635: bipush 1
      // 9636: putfield StageMaker.logged I
      // 9639: aconst_null
      // 963a: ldc_w "Unable to upload sound track!\nReason:\nAnother MOD Track is already uploaded with the same name, please rename your Track.\nOpen your 'mystages' folder then open 'mymusic' to find your MOD Track to rename it.\n\n"
      // 963d: ldc_w "Stage Maker"
      // 9640: bipush 1
      // 9641: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9644: bipush 1
      // 9645: istore 3
      // 9646: goto 964b
      // 964b: iload 2
      // 964c: bipush -3
      // 964e: if_icmpeq 9656
      // 9651: goto 966d
      // 9656: aload 0
      // 9657: bipush 1
      // 9658: putfield StageMaker.logged I
      // 965b: aconst_null
      // 965c: ldc_w "Unable to upload sound track!\nReason:\nYour MOD Track’s file size is too large, Track file size must be less then 700KB to be accepted.\n\n"
      // 965f: ldc_w "Stage Maker"
      // 9662: bipush 1
      // 9663: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9666: bipush 1
      // 9667: istore 3
      // 9668: goto 966d
      // 966d: iload 2
      // 966e: bipush -2
      // 9670: if_icmpeq 9678
      // 9673: goto 968f
      // 9678: aload 0
      // 9679: bipush 1
      // 967a: putfield StageMaker.logged I
      // 967d: aconst_null
      // 967e: ldc_w "Unable to upload sound track!  Unknown Error.  Please try again later."
      // 9681: ldc_w "Stage Maker"
      // 9684: bipush 1
      // 9685: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9688: bipush 1
      // 9689: istore 3
      // 968a: goto 968f
      // 968f: iload 3
      // 9690: ifeq 9698
      // 9693: goto 96a8
      // 9698: aconst_null
      // 9699: ldc_w "Unable to publish stage!  Unknown Error."
      // 969c: ldc_w "Stage Maker"
      // 969f: bipush 1
      // 96a0: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 96a3: goto 96a8
      // 96a8: goto 96ad
      // 96ad: goto 96e3
      // 96b2: aconst_null
      // 96b3: new java/lang/StringBuilder
      // 96b6: dup
      // 96b7: invokespecial java/lang/StringBuilder.<init> ()V
      // 96ba: ldc_w "Error!  This stage is not ready for publishing!\nReason:\n"
      // 96bd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 96c0: aload 0
      // 96c1: getfield StageMaker.errlo [Ljava/lang/String;
      // 96c4: aload 0
      // 96c5: getfield StageMaker.errd I
      // 96c8: bipush 1
      // 96c9: isub
      // 96ca: aaload
      // 96cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 96ce: ldc_w "\n\n"
      // 96d1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 96d4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 96d7: ldc_w "Stage Maker"
      // 96da: bipush 0
      // 96db: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 96de: goto 96e3
      // 96e3: aload 0
      // 96e4: getfield StageMaker.logged I
      // 96e7: bipush 3
      // 96e8: if_icmpeq 96f0
      // 96eb: goto a2e4
      // 96f0: bipush 0
      // 96f1: istore 1
      // 96f2: iload 1
      // 96f3: aload 0
      // 96f4: getfield StageMaker.nms I
      // 96f7: if_icmplt 96ff
      // 96fa: goto a2df
      // 96ff: aload 0
      // 9700: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9703: new java/awt/Color
      // 9706: dup
      // 9707: sipush 235
      // 970a: sipush 235
      // 970d: sipush 235
      // 9710: invokespecial java/awt/Color.<init> (III)V
      // 9713: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9716: aload 0
      // 9717: getfield StageMaker.xm I
      // 971a: bipush 11
      // 971c: if_icmpgt 9724
      // 971f: goto 9777
      // 9724: aload 0
      // 9725: getfield StageMaker.xm I
      // 9728: sipush 789
      // 972b: if_icmplt 9733
      // 972e: goto 9777
      // 9733: aload 0
      // 9734: getfield StageMaker.ym I
      // 9737: sipush 142
      // 973a: iload 1
      // 973b: bipush 20
      // 973d: imul
      // 973e: iadd
      // 973f: if_icmpgt 9747
      // 9742: goto 9777
      // 9747: aload 0
      // 9748: getfield StageMaker.ym I
      // 974b: sipush 160
      // 974e: iload 1
      // 974f: bipush 20
      // 9751: imul
      // 9752: iadd
      // 9753: if_icmplt 975b
      // 9756: goto 9777
      // 975b: aload 0
      // 975c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 975f: new java/awt/Color
      // 9762: dup
      // 9763: sipush 255
      // 9766: sipush 255
      // 9769: sipush 255
      // 976c: invokespecial java/awt/Color.<init> (III)V
      // 976f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9772: goto 9777
      // 9777: aload 0
      // 9778: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 977b: bipush 11
      // 977d: sipush 142
      // 9780: iload 1
      // 9781: bipush 20
      // 9783: imul
      // 9784: iadd
      // 9785: sipush 778
      // 9788: bipush 18
      // 978a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 978d: aload 0
      // 978e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9791: new java/awt/Font
      // 9794: dup
      // 9795: ldc_w "Arial"
      // 9798: bipush 0
      // 9799: bipush 12
      // 979b: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 979e: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 97a1: aload 0
      // 97a2: aload 0
      // 97a3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 97a6: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 97a9: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 97ac: aload 0
      // 97ad: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 97b0: new java/awt/Color
      // 97b3: dup
      // 97b4: bipush 0
      // 97b5: bipush 0
      // 97b6: bipush 0
      // 97b7: invokespecial java/awt/Color.<init> (III)V
      // 97ba: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 97bd: aload 0
      // 97be: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 97c1: aload 0
      // 97c2: getfield StageMaker.mystages [Ljava/lang/String;
      // 97c5: iload 1
      // 97c6: aaload
      // 97c7: sipush 180
      // 97ca: aload 0
      // 97cb: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 97ce: aload 0
      // 97cf: getfield StageMaker.mystages [Ljava/lang/String;
      // 97d2: iload 1
      // 97d3: aaload
      // 97d4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 97d7: bipush 2
      // 97d8: idiv
      // 97d9: isub
      // 97da: sipush 156
      // 97dd: iload 1
      // 97de: bipush 20
      // 97e0: imul
      // 97e1: iadd
      // 97e2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 97e5: aload 0
      // 97e6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 97e9: new java/awt/Color
      // 97ec: dup
      // 97ed: sipush 155
      // 97f0: sipush 155
      // 97f3: sipush 155
      // 97f6: invokespecial java/awt/Color.<init> (III)V
      // 97f9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 97fc: aload 0
      // 97fd: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9800: sipush 350
      // 9803: sipush 145
      // 9806: iload 1
      // 9807: bipush 20
      // 9809: imul
      // 980a: iadd
      // 980b: sipush 350
      // 980e: sipush 157
      // 9811: iload 1
      // 9812: bipush 20
      // 9814: imul
      // 9815: iadd
      // 9816: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 9819: aload 0
      // 981a: getfield StageMaker.pubt [I
      // 981d: iload 1
      // 981e: iaload
      // 981f: bipush -1
      // 9820: if_icmpne 9828
      // 9823: goto a175
      // 9828: aload 0
      // 9829: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 982c: sipush 450
      // 982f: sipush 145
      // 9832: iload 1
      // 9833: bipush 20
      // 9835: imul
      // 9836: iadd
      // 9837: sipush 450
      // 983a: sipush 157
      // 983d: iload 1
      // 983e: bipush 20
      // 9840: imul
      // 9841: iadd
      // 9842: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 9845: aload 0
      // 9846: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9849: sipush 550
      // 984c: sipush 145
      // 984f: iload 1
      // 9850: bipush 20
      // 9852: imul
      // 9853: iadd
      // 9854: sipush 550
      // 9857: sipush 157
      // 985a: iload 1
      // 985b: bipush 20
      // 985d: imul
      // 985e: iadd
      // 985f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 9862: aload 0
      // 9863: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9866: sipush 650
      // 9869: sipush 145
      // 986c: iload 1
      // 986d: bipush 20
      // 986f: imul
      // 9870: iadd
      // 9871: sipush 650
      // 9874: sipush 157
      // 9877: iload 1
      // 9878: bipush 20
      // 987a: imul
      // 987b: iadd
      // 987c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 987f: bipush 0
      // 9880: istore 2
      // 9881: aload 0
      // 9882: getfield StageMaker.maker [Ljava/lang/String;
      // 9885: iload 1
      // 9886: aaload
      // 9887: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 988a: aload 0
      // 988b: getfield StageMaker.tnick Ljava/awt/TextField;
      // 988e: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 9891: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9894: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9897: ifne 989f
      // 989a: goto 98da
      // 989f: bipush 1
      // 98a0: istore 2
      // 98a1: aload 0
      // 98a2: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 98a5: new java/awt/Color
      // 98a8: dup
      // 98a9: bipush 0
      // 98aa: bipush 64
      // 98ac: bipush 0
      // 98ad: invokespecial java/awt/Color.<init> (III)V
      // 98b0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 98b3: aload 0
      // 98b4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 98b7: ldc_w "You"
      // 98ba: sipush 400
      // 98bd: aload 0
      // 98be: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 98c1: ldc_w "You"
      // 98c4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 98c7: bipush 2
      // 98c8: idiv
      // 98c9: isub
      // 98ca: sipush 156
      // 98cd: iload 1
      // 98ce: bipush 20
      // 98d0: imul
      // 98d1: iadd
      // 98d2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 98d5: goto 9914
      // 98da: aload 0
      // 98db: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 98de: new java/awt/Color
      // 98e1: dup
      // 98e2: bipush 0
      // 98e3: bipush 0
      // 98e4: bipush 64
      // 98e6: invokespecial java/awt/Color.<init> (III)V
      // 98e9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 98ec: aload 0
      // 98ed: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 98f0: aload 0
      // 98f1: getfield StageMaker.maker [Ljava/lang/String;
      // 98f4: iload 1
      // 98f5: aaload
      // 98f6: sipush 400
      // 98f9: aload 0
      // 98fa: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 98fd: aload 0
      // 98fe: getfield StageMaker.maker [Ljava/lang/String;
      // 9901: iload 1
      // 9902: aaload
      // 9903: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9906: bipush 2
      // 9907: idiv
      // 9908: isub
      // 9909: sipush 156
      // 990c: iload 1
      // 990d: bipush 20
      // 990f: imul
      // 9910: iadd
      // 9911: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9914: aload 0
      // 9915: getfield StageMaker.nad [I
      // 9918: iload 1
      // 9919: iaload
      // 991a: bipush 1
      // 991b: if_icmpgt 9923
      // 991e: goto 9a81
      // 9923: aload 0
      // 9924: new java/lang/StringBuilder
      // 9927: dup
      // 9928: invokespecial java/lang/StringBuilder.<init> ()V
      // 992b: ldc ""
      // 992d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9930: aload 0
      // 9931: getfield StageMaker.nad [I
      // 9934: iload 1
      // 9935: iaload
      // 9936: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9939: ldc_w " Players"
      // 993c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 993f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9942: sipush 500
      // 9945: sipush 156
      // 9948: iload 1
      // 9949: bipush 20
      // 994b: imul
      // 994c: iadd
      // 994d: invokevirtual StageMaker.ovbutton (Ljava/lang/String;II)Z
      // 9950: ifne 9958
      // 9953: goto 9ab5
      // 9958: new java/lang/StringBuilder
      // 995b: dup
      // 995c: invokespecial java/lang/StringBuilder.<init> ()V
      // 995f: ldc_w "[ "
      // 9962: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9965: aload 0
      // 9966: getfield StageMaker.mystages [Ljava/lang/String;
      // 9969: iload 1
      // 996a: aaload
      // 996b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 996e: ldc_w " ]  has been added by the following players to their accounts:     \n\n"
      // 9971: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9974: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9977: astore 3
      // 9978: bipush 0
      // 9979: istore 4
      // 997b: bipush 0
      // 997c: istore 5
      // 997e: iload 5
      // 9980: aload 0
      // 9981: getfield StageMaker.nad [I
      // 9984: iload 1
      // 9985: iaload
      // 9986: if_icmplt 998e
      // 9989: goto 9a5e
      // 998e: iinc 4 1
      // 9991: iload 4
      // 9993: bipush 17
      // 9995: if_icmpeq 999d
      // 9998: goto 99ba
      // 999d: new java/lang/StringBuilder
      // 99a0: dup
      // 99a1: invokespecial java/lang/StringBuilder.<init> ()V
      // 99a4: aload 3
      // 99a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 99a8: ldc_w "\n"
      // 99ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 99ae: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 99b1: astore 3
      // 99b2: bipush 1
      // 99b3: istore 4
      // 99b5: goto 99ba
      // 99ba: new java/lang/StringBuilder
      // 99bd: dup
      // 99be: invokespecial java/lang/StringBuilder.<init> ()V
      // 99c1: aload 3
      // 99c2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 99c5: aload 0
      // 99c6: getfield StageMaker.addeda [[Ljava/lang/String;
      // 99c9: iload 1
      // 99ca: aaload
      // 99cb: iload 5
      // 99cd: aaload
      // 99ce: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 99d1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 99d4: astore 3
      // 99d5: iload 5
      // 99d7: aload 0
      // 99d8: getfield StageMaker.nad [I
      // 99db: iload 1
      // 99dc: iaload
      // 99dd: bipush 1
      // 99de: isub
      // 99df: if_icmpne 99e7
      // 99e2: goto 9a56
      // 99e7: iload 5
      // 99e9: aload 0
      // 99ea: getfield StageMaker.nad [I
      // 99ed: iload 1
      // 99ee: iaload
      // 99ef: bipush 2
      // 99f0: isub
      // 99f1: if_icmpne 99f9
      // 99f4: goto 9a13
      // 99f9: new java/lang/StringBuilder
      // 99fc: dup
      // 99fd: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a00: aload 3
      // 9a01: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a04: ldc_w ", "
      // 9a07: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a0a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9a0d: astore 3
      // 9a0e: goto 9a56
      // 9a13: iload 4
      // 9a15: bipush 16
      // 9a17: if_icmpeq 9a1f
      // 9a1a: goto 9a3c
      // 9a1f: new java/lang/StringBuilder
      // 9a22: dup
      // 9a23: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a26: aload 3
      // 9a27: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a2a: ldc_w "\nand "
      // 9a2d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a30: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9a33: astore 3
      // 9a34: bipush 0
      // 9a35: istore 4
      // 9a37: goto 9a56
      // 9a3c: new java/lang/StringBuilder
      // 9a3f: dup
      // 9a40: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a43: aload 3
      // 9a44: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a47: ldc_w " and "
      // 9a4a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a4d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9a50: astore 3
      // 9a51: goto 9a56
      // 9a56: iinc 5 1
      // 9a59: goto 997e
      // 9a5e: new java/lang/StringBuilder
      // 9a61: dup
      // 9a62: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a65: aload 3
      // 9a66: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a69: ldc_w "\n \n \n"
      // 9a6c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a6f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9a72: astore 3
      // 9a73: aconst_null
      // 9a74: aload 3
      // 9a75: ldc_w "Stage Maker"
      // 9a78: bipush 1
      // 9a79: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 9a7c: goto 9ab5
      // 9a81: aload 0
      // 9a82: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9a85: new java/awt/Color
      // 9a88: dup
      // 9a89: bipush 0
      // 9a8a: bipush 0
      // 9a8b: bipush 64
      // 9a8d: invokespecial java/awt/Color.<init> (III)V
      // 9a90: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9a93: aload 0
      // 9a94: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9a97: ldc_w "None"
      // 9a9a: sipush 500
      // 9a9d: aload 0
      // 9a9e: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9aa1: ldc_w "None"
      // 9aa4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9aa7: bipush 2
      // 9aa8: idiv
      // 9aa9: isub
      // 9aaa: sipush 156
      // 9aad: iload 1
      // 9aae: bipush 20
      // 9ab0: imul
      // 9ab1: iadd
      // 9ab2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9ab5: aload 0
      // 9ab6: getfield StageMaker.pubt [I
      // 9ab9: iload 1
      // 9aba: iaload
      // 9abb: ifeq 9ac3
      // 9abe: goto 9afc
      // 9ac3: aload 0
      // 9ac4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9ac7: new java/awt/Color
      // 9aca: dup
      // 9acb: bipush 0
      // 9acc: bipush 0
      // 9acd: bipush 64
      // 9acf: invokespecial java/awt/Color.<init> (III)V
      // 9ad2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9ad5: aload 0
      // 9ad6: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9ad9: ldc_w "Private"
      // 9adc: sipush 600
      // 9adf: aload 0
      // 9ae0: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9ae3: ldc_w "Private"
      // 9ae6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9ae9: bipush 2
      // 9aea: idiv
      // 9aeb: isub
      // 9aec: sipush 156
      // 9aef: iload 1
      // 9af0: bipush 20
      // 9af2: imul
      // 9af3: iadd
      // 9af4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9af7: goto 9afc
      // 9afc: aload 0
      // 9afd: getfield StageMaker.pubt [I
      // 9b00: iload 1
      // 9b01: iaload
      // 9b02: bipush 1
      // 9b03: if_icmpeq 9b0b
      // 9b06: goto 9b44
      // 9b0b: aload 0
      // 9b0c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9b0f: new java/awt/Color
      // 9b12: dup
      // 9b13: bipush 0
      // 9b14: bipush 0
      // 9b15: bipush 64
      // 9b17: invokespecial java/awt/Color.<init> (III)V
      // 9b1a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9b1d: aload 0
      // 9b1e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9b21: ldc_w "Public"
      // 9b24: sipush 600
      // 9b27: aload 0
      // 9b28: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9b2b: ldc_w "Public"
      // 9b2e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9b31: bipush 2
      // 9b32: idiv
      // 9b33: isub
      // 9b34: sipush 156
      // 9b37: iload 1
      // 9b38: bipush 20
      // 9b3a: imul
      // 9b3b: iadd
      // 9b3c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9b3f: goto 9b44
      // 9b44: aload 0
      // 9b45: getfield StageMaker.pubt [I
      // 9b48: iload 1
      // 9b49: iaload
      // 9b4a: bipush 2
      // 9b4b: if_icmpeq 9b53
      // 9b4e: goto 9b8c
      // 9b53: aload 0
      // 9b54: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9b57: new java/awt/Color
      // 9b5a: dup
      // 9b5b: bipush 0
      // 9b5c: bipush 64
      // 9b5e: bipush 0
      // 9b5f: invokespecial java/awt/Color.<init> (III)V
      // 9b62: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9b65: aload 0
      // 9b66: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9b69: ldc_w "Super Public"
      // 9b6c: sipush 600
      // 9b6f: aload 0
      // 9b70: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9b73: ldc_w "Super Public"
      // 9b76: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9b79: bipush 2
      // 9b7a: idiv
      // 9b7b: isub
      // 9b7c: sipush 156
      // 9b7f: iload 1
      // 9b80: bipush 20
      // 9b82: imul
      // 9b83: iadd
      // 9b84: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9b87: goto 9b8c
      // 9b8c: aload 0
      // 9b8d: getfield StageMaker.pubt [I
      // 9b90: iload 1
      // 9b91: iaload
      // 9b92: bipush 2
      // 9b93: if_icmpne 9b9b
      // 9b96: goto 9ba4
      // 9b9b: iload 2
      // 9b9c: ifne 9ba4
      // 9b9f: goto a170
      // 9ba4: aload 0
      // 9ba5: ldc_w "Download"
      // 9ba8: sipush 700
      // 9bab: sipush 156
      // 9bae: iload 1
      // 9baf: bipush 20
      // 9bb1: imul
      // 9bb2: iadd
      // 9bb3: invokevirtual StageMaker.ovbutton (Ljava/lang/String;II)Z
      // 9bb6: ifne 9bbe
      // 9bb9: goto a170
      // 9bbe: bipush 0
      // 9bbf: istore 3
      // 9bc0: bipush 0
      // 9bc1: istore 4
      // 9bc3: iload 4
      // 9bc5: aload 0
      // 9bc6: getfield StageMaker.slstage LSmenu;
      // 9bc9: invokevirtual Smenu.getItemCount ()I
      // 9bcc: if_icmplt 9bd4
      // 9bcf: goto 9c23
      // 9bd4: aload 0
      // 9bd5: getfield StageMaker.mystages [Ljava/lang/String;
      // 9bd8: iload 1
      // 9bd9: aaload
      // 9bda: aload 0
      // 9bdb: getfield StageMaker.slstage LSmenu;
      // 9bde: iload 4
      // 9be0: invokevirtual Smenu.getItem (I)Ljava/lang/String;
      // 9be3: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9be6: ifne 9bee
      // 9be9: goto 9c1b
      // 9bee: aconst_null
      // 9bef: new java/lang/StringBuilder
      // 9bf2: dup
      // 9bf3: invokespecial java/lang/StringBuilder.<init> ()V
      // 9bf6: ldc_w "Replace the local "
      // 9bf9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9bfc: aload 0
      // 9bfd: getfield StageMaker.mystages [Ljava/lang/String;
      // 9c00: iload 1
      // 9c01: aaload
      // 9c02: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9c05: ldc_w " in your 'mystages' folder with the published online copy?"
      // 9c08: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9c0b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9c0e: ldc_w "Stage Maker"
      // 9c11: bipush 0
      // 9c12: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 9c15: istore 3
      // 9c16: goto 9c1b
      // 9c1b: iinc 4 1
      // 9c1e: goto 9bc3
      // 9c23: iload 3
      // 9c24: ifeq 9c2c
      // 9c27: goto a16b
      // 9c2c: aload 0
      // 9c2d: new java/awt/Cursor
      // 9c30: dup
      // 9c31: bipush 3
      // 9c32: invokespecial java/awt/Cursor.<init> (I)V
      // 9c35: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // 9c38: aload 0
      // 9c39: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c3c: new java/awt/Font
      // 9c3f: dup
      // 9c40: ldc_w "Arial"
      // 9c43: bipush 1
      // 9c44: bipush 13
      // 9c46: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9c49: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9c4c: aload 0
      // 9c4d: aload 0
      // 9c4e: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c51: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9c54: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9c57: aload 0
      // 9c58: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c5b: new java/awt/Color
      // 9c5e: dup
      // 9c5f: sipush 225
      // 9c62: sipush 225
      // 9c65: sipush 225
      // 9c68: invokespecial java/awt/Color.<init> (III)V
      // 9c6b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9c6e: aload 0
      // 9c6f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c72: bipush 11
      // 9c74: sipush 141
      // 9c77: sipush 779
      // 9c7a: sipush 401
      // 9c7d: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 9c80: aload 0
      // 9c81: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c84: new java/awt/Color
      // 9c87: dup
      // 9c88: bipush 0
      // 9c89: bipush 0
      // 9c8a: bipush 0
      // 9c8b: invokespecial java/awt/Color.<init> (III)V
      // 9c8e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9c91: aload 0
      // 9c92: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9c95: ldc_w "Downloading stage, please wait..."
      // 9c98: sipush 400
      // 9c9b: aload 0
      // 9c9c: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9c9f: ldc_w "Downloading stage, please wait..."
      // 9ca2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9ca5: bipush 2
      // 9ca6: idiv
      // 9ca7: isub
      // 9ca8: sipush 250
      // 9cab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9cae: aload 0
      // 9caf: invokevirtual StageMaker.repaint ()V
      // 9cb2: new java/lang/StringBuilder
      // 9cb5: dup
      // 9cb6: invokespecial java/lang/StringBuilder.<init> ()V
      // 9cb9: ldc_w "http://multiplayer.needformadness.com/tracks/"
      // 9cbc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9cbf: aload 0
      // 9cc0: getfield StageMaker.mystages [Ljava/lang/String;
      // 9cc3: iload 1
      // 9cc4: aaload
      // 9cc5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9cc8: ldc_w ".radq?reqlo="
      // 9ccb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9cce: invokestatic java/lang/Math.random ()D
      // 9cd1: ldc2_w 1000.0
      // 9cd4: dmul
      // 9cd5: d2i
      // 9cd6: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9cd9: ldc ""
      // 9cdb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9cde: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9ce1: astore 4
      // 9ce3: aload 4
      // 9ce5: bipush 32
      // 9ce7: bipush 95
      // 9ce9: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // 9cec: astore 4
      // 9cee: new java/net/URL
      // 9cf1: dup
      // 9cf2: aload 4
      // 9cf4: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // 9cf7: astore 5
      // 9cf9: aload 5
      // 9cfb: invokevirtual java/net/URL.openConnection ()Ljava/net/URLConnection;
      // 9cfe: invokevirtual java/net/URLConnection.getContentLength ()I
      // 9d01: istore 7
      // 9d03: new java/io/DataInputStream
      // 9d06: dup
      // 9d07: aload 5
      // 9d09: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // 9d0c: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 9d0f: astore 8
      // 9d11: iload 7
      // 9d13: newarray 8
      // 9d15: astore 9
      // 9d17: aload 8
      // 9d19: aload 9
      // 9d1b: invokevirtual java/io/DataInputStream.readFully ([B)V
      // 9d1e: aload 8
      // 9d20: invokevirtual java/io/DataInputStream.close ()V
      // 9d23: aload 9
      // 9d25: bipush 0
      // 9d26: baload
      // 9d27: bipush 80
      // 9d29: if_icmpeq 9d31
      // 9d2c: goto 9d63
      // 9d31: aload 9
      // 9d33: bipush 1
      // 9d34: baload
      // 9d35: bipush 75
      // 9d37: if_icmpeq 9d3f
      // 9d3a: goto 9d63
      // 9d3f: aload 9
      // 9d41: bipush 2
      // 9d42: baload
      // 9d43: bipush 3
      // 9d44: if_icmpeq 9d4c
      // 9d47: goto 9d63
      // 9d4c: new java/util/zip/ZipInputStream
      // 9d4f: dup
      // 9d50: new java/io/ByteArrayInputStream
      // 9d53: dup
      // 9d54: aload 9
      // 9d56: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 9d59: invokespecial java/util/zip/ZipInputStream.<init> (Ljava/io/InputStream;)V
      // 9d5c: astore 6
      // 9d5e: goto 9dbf
      // 9d63: iload 7
      // 9d65: bipush 40
      // 9d67: isub
      // 9d68: newarray 8
      // 9d6a: astore 10
      // 9d6c: bipush 0
      // 9d6d: istore 11
      // 9d6f: iload 11
      // 9d71: iload 7
      // 9d73: bipush 40
      // 9d75: isub
      // 9d76: if_icmplt 9d7e
      // 9d79: goto 9dad
      // 9d7e: bipush 20
      // 9d80: istore 12
      // 9d82: iload 11
      // 9d84: sipush 500
      // 9d87: if_icmpge 9d8f
      // 9d8a: goto 9d98
      // 9d8f: bipush 40
      // 9d91: istore 12
      // 9d93: goto 9d98
      // 9d98: aload 10
      // 9d9a: iload 11
      // 9d9c: aload 9
      // 9d9e: iload 11
      // 9da0: iload 12
      // 9da2: iadd
      // 9da3: baload
      // 9da4: bastore
      // 9da5: iinc 11 1
      // 9da8: goto 9d6f
      // 9dad: new java/util/zip/ZipInputStream
      // 9db0: dup
      // 9db1: new java/io/ByteArrayInputStream
      // 9db4: dup
      // 9db5: aload 10
      // 9db7: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 9dba: invokespecial java/util/zip/ZipInputStream.<init> (Ljava/io/InputStream;)V
      // 9dbd: astore 6
      // 9dbf: aload 6
      // 9dc1: invokevirtual java/util/zip/ZipInputStream.getNextEntry ()Ljava/util/zip/ZipEntry;
      // 9dc4: astore 10
      // 9dc6: aload 10
      // 9dc8: ifnonnull 9dd0
      // 9dcb: goto a149
      // 9dd0: ldc ""
      // 9dd2: astore 11
      // 9dd4: aload 10
      // 9dd6: invokevirtual java/util/zip/ZipEntry.getName ()Ljava/lang/String;
      // 9dd9: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 9ddc: invokevirtual java/lang/Integer.intValue ()I
      // 9ddf: istore 12
      // 9de1: iload 12
      // 9de3: newarray 8
      // 9de5: astore 13
      // 9de7: bipush 0
      // 9de8: istore 14
      // 9dea: iload 12
      // 9dec: ifgt 9df4
      // 9def: goto 9e14
      // 9df4: aload 6
      // 9df6: aload 13
      // 9df8: iload 14
      // 9dfa: iload 12
      // 9dfc: invokevirtual java/util/zip/ZipInputStream.read ([BII)I
      // 9dff: istore 15
      // 9e01: iload 14
      // 9e03: iload 15
      // 9e05: iadd
      // 9e06: istore 14
      // 9e08: iload 12
      // 9e0a: iload 15
      // 9e0c: isub
      // 9e0d: istore 12
      // 9e0f: goto 9dea
      // 9e14: new java/lang/String
      // 9e17: dup
      // 9e18: aload 13
      // 9e1a: invokespecial java/lang/String.<init> ([B)V
      // 9e1d: astore 16
      // 9e1f: new java/lang/StringBuilder
      // 9e22: dup
      // 9e23: invokespecial java/lang/StringBuilder.<init> ()V
      // 9e26: aload 16
      // 9e28: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9e2b: ldc_w "\n"
      // 9e2e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9e31: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9e34: astore 16
      // 9e36: ldc ""
      // 9e38: astore 17
      // 9e3a: bipush 0
      // 9e3b: istore 18
      // 9e3d: aload 16
      // 9e3f: ldc_w "\n"
      // 9e42: bipush 0
      // 9e43: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 9e46: istore 19
      // 9e48: iload 19
      // 9e4a: bipush -1
      // 9e4b: if_icmpne 9e53
      // 9e4e: goto 9f10
      // 9e53: iload 18
      // 9e55: aload 16
      // 9e57: invokevirtual java/lang/String.length ()I
      // 9e5a: if_icmplt 9e62
      // 9e5d: goto 9f10
      // 9e62: aload 16
      // 9e64: iload 18
      // 9e66: iload 19
      // 9e68: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 9e6b: astore 20
      // 9e6d: aload 20
      // 9e6f: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 9e72: astore 20
      // 9e74: iload 19
      // 9e76: bipush 1
      // 9e77: iadd
      // 9e78: istore 18
      // 9e7a: aload 16
      // 9e7c: ldc_w "\n"
      // 9e7f: iload 18
      // 9e81: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 9e84: istore 19
      // 9e86: aload 20
      // 9e88: ldc_w "stagemaker("
      // 9e8b: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 9e8e: ifeq 9e96
      // 9e91: goto 9ecc
      // 9e96: aload 20
      // 9e98: ldc_w "publish("
      // 9e9b: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 9e9e: ifeq 9ea6
      // 9ea1: goto 9ecc
      // 9ea6: new java/lang/StringBuilder
      // 9ea9: dup
      // 9eaa: invokespecial java/lang/StringBuilder.<init> ()V
      // 9ead: aload 17
      // 9eaf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9eb2: ldc ""
      // 9eb4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9eb7: aload 20
      // 9eb9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9ebc: ldc_w "\r\n"
      // 9ebf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9ec2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9ec5: astore 17
      // 9ec7: goto 9eea
      // 9ecc: aload 17
      // 9ece: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 9ed1: astore 17
      // 9ed3: new java/lang/StringBuilder
      // 9ed6: dup
      // 9ed7: invokespecial java/lang/StringBuilder.<init> ()V
      // 9eda: aload 17
      // 9edc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9edf: ldc_w "\r\n"
      // 9ee2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9ee5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9ee8: astore 17
      // 9eea: aload 20
      // 9eec: ldc_w "soundtrack"
      // 9eef: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 9ef2: ifne 9efa
      // 9ef5: goto 9f0b
      // 9efa: aload 0
      // 9efb: ldc_w "soundtrack"
      // 9efe: aload 20
      // 9f00: bipush 0
      // 9f01: invokevirtual StageMaker.getstring (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // 9f04: astore 11
      // 9f06: goto 9f0b
      // 9f0b: goto 9e48
      // 9f10: aload 17
      // 9f12: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 9f15: astore 17
      // 9f17: new java/lang/StringBuilder
      // 9f1a: dup
      // 9f1b: invokespecial java/lang/StringBuilder.<init> ()V
      // 9f1e: aload 17
      // 9f20: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f23: ldc_w "\r\n\r\n"
      // 9f26: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f29: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9f2c: astore 17
      // 9f2e: new java/io/File
      // 9f31: dup
      // 9f32: new java/lang/StringBuilder
      // 9f35: dup
      // 9f36: invokespecial java/lang/StringBuilder.<init> ()V
      // 9f39: ldc ""
      // 9f3b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f3e: getstatic Madness.fpath Ljava/lang/String;
      // 9f41: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f44: ldc_w "mystages/"
      // 9f47: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f4a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9f4d: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 9f50: astore 20
      // 9f52: aload 20
      // 9f54: invokevirtual java/io/File.exists ()Z
      // 9f57: ifeq 9f5f
      // 9f5a: goto 9f6a
      // 9f5f: aload 20
      // 9f61: invokevirtual java/io/File.mkdirs ()Z
      // 9f64: pop
      // 9f65: goto 9f6a
      // 9f6a: new java/io/File
      // 9f6d: dup
      // 9f6e: new java/lang/StringBuilder
      // 9f71: dup
      // 9f72: invokespecial java/lang/StringBuilder.<init> ()V
      // 9f75: ldc ""
      // 9f77: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f7a: getstatic Madness.fpath Ljava/lang/String;
      // 9f7d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f80: ldc_w "mystages/"
      // 9f83: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f86: aload 0
      // 9f87: getfield StageMaker.mystages [Ljava/lang/String;
      // 9f8a: iload 1
      // 9f8b: aaload
      // 9f8c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f8f: ldc_w ".txt"
      // 9f92: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9f95: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9f98: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 9f9b: astore 20
      // 9f9d: new java/io/BufferedWriter
      // 9fa0: dup
      // 9fa1: new java/io/FileWriter
      // 9fa4: dup
      // 9fa5: aload 20
      // 9fa7: invokespecial java/io/FileWriter.<init> (Ljava/io/File;)V
      // 9faa: invokespecial java/io/BufferedWriter.<init> (Ljava/io/Writer;)V
      // 9fad: astore 21
      // 9faf: aload 21
      // 9fb1: aload 17
      // 9fb3: invokevirtual java/io/BufferedWriter.write (Ljava/lang/String;)V
      // 9fb6: aload 21
      // 9fb8: invokevirtual java/io/BufferedWriter.close ()V
      // 9fbb: aconst_null
      // 9fbc: astore 21
      // 9fbe: aload 6
      // 9fc0: invokevirtual java/util/zip/ZipInputStream.close ()V
      // 9fc3: aload 11
      // 9fc5: ldc ""
      // 9fc7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9fca: ifeq 9fd2
      // 9fcd: goto a112
      // 9fd2: aload 0
      // 9fd3: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9fd6: new java/awt/Color
      // 9fd9: dup
      // 9fda: bipush 0
      // 9fdb: bipush 0
      // 9fdc: bipush 0
      // 9fdd: invokespecial java/awt/Color.<init> (III)V
      // 9fe0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9fe3: aload 0
      // 9fe4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // 9fe7: ldc_w "Downloading stage's sound track..."
      // 9fea: sipush 400
      // 9fed: aload 0
      // 9fee: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // 9ff1: ldc_w "Downloading stage's sound track..."
      // 9ff4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9ff7: bipush 2
      // 9ff8: idiv
      // 9ff9: isub
      // 9ffa: sipush 280
      // 9ffd: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a000: aload 0
      // a001: invokevirtual StageMaker.repaint ()V
      // a004: new java/lang/StringBuilder
      // a007: dup
      // a008: invokespecial java/lang/StringBuilder.<init> ()V
      // a00b: ldc_w "http://multiplayer.needformadness.com/tracks/music/"
      // a00e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a011: aload 11
      // a013: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a016: ldc_w ".zip"
      // a019: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a01c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a01f: astore 4
      // a021: aload 4
      // a023: bipush 32
      // a025: bipush 95
      // a027: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // a02a: astore 4
      // a02c: new java/net/URL
      // a02f: dup
      // a030: aload 4
      // a032: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // a035: astore 5
      // a037: aload 5
      // a039: invokevirtual java/net/URL.openConnection ()Ljava/net/URLConnection;
      // a03c: invokevirtual java/net/URLConnection.getContentLength ()I
      // a03f: istore 7
      // a041: new java/io/File
      // a044: dup
      // a045: new java/lang/StringBuilder
      // a048: dup
      // a049: invokespecial java/lang/StringBuilder.<init> ()V
      // a04c: ldc ""
      // a04e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a051: getstatic Madness.fpath Ljava/lang/String;
      // a054: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a057: ldc_w "mystages/mymusic/"
      // a05a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a05d: aload 11
      // a05f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a062: ldc_w ".zip"
      // a065: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a068: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a06b: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // a06e: astore 20
      // a070: aload 20
      // a072: invokevirtual java/io/File.exists ()Z
      // a075: ifne a07d
      // a078: goto a0be
      // a07d: aload 20
      // a07f: invokevirtual java/io/File.length ()J
      // a082: iload 7
      // a084: i2l
      // a085: lcmp
      // a086: ifeq a08e
      // a089: goto a095
      // a08e: bipush 1
      // a08f: istore 3
      // a090: goto a0be
      // a095: aconst_null
      // a096: new java/lang/StringBuilder
      // a099: dup
      // a09a: invokespecial java/lang/StringBuilder.<init> ()V
      // a09d: ldc_w "Another track named '"
      // a0a0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a0a3: aload 11
      // a0a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a0a8: ldc_w "' already exists in your Sound Tracks folder!\nReplace it with the one attached to this stage?"
      // a0ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a0ae: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a0b1: ldc_w "Stage Maker"
      // a0b4: bipush 0
      // a0b5: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // a0b8: istore 3
      // a0b9: goto a0be
      // a0be: iload 3
      // a0bf: ifeq a0c7
      // a0c2: goto a106
      // a0c7: new java/io/DataInputStream
      // a0ca: dup
      // a0cb: aload 5
      // a0cd: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // a0d0: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // a0d3: astore 8
      // a0d5: iload 7
      // a0d7: newarray 8
      // a0d9: astore 9
      // a0db: aload 8
      // a0dd: aload 9
      // a0df: invokevirtual java/io/DataInputStream.readFully ([B)V
      // a0e2: aload 8
      // a0e4: invokevirtual java/io/DataInputStream.close ()V
      // a0e7: new java/io/FileOutputStream
      // a0ea: dup
      // a0eb: aload 20
      // a0ed: invokespecial java/io/FileOutputStream.<init> (Ljava/io/File;)V
      // a0f0: astore 22
      // a0f2: aload 22
      // a0f4: aload 9
      // a0f6: invokevirtual java/io/FileOutputStream.write ([B)V
      // a0f9: aload 22
      // a0fb: invokevirtual java/io/FileOutputStream.close ()V
      // a0fe: aconst_null
      // a0ff: astore 22
      // a101: goto a106
      // a106: goto a112
      // a10b: astore 22
      // a10d: goto a112
      // a112: aload 0
      // a113: new java/awt/Cursor
      // a116: dup
      // a117: bipush 0
      // a118: invokespecial java/awt/Cursor.<init> (I)V
      // a11b: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a11e: aconst_null
      // a11f: new java/lang/StringBuilder
      // a122: dup
      // a123: invokespecial java/lang/StringBuilder.<init> ()V
      // a126: ldc ""
      // a128: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a12b: aload 0
      // a12c: getfield StageMaker.mystages [Ljava/lang/String;
      // a12f: iload 1
      // a130: aaload
      // a131: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a134: ldc_w " has been successfully downloaded!"
      // a137: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a13a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a13d: ldc_w "Stage Maker"
      // a140: bipush 1
      // a141: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // a144: goto a154
      // a149: aconst_null
      // a14a: ldc_w "Unable to download stage.  Unknown Error!     \nPlease try again later."
      // a14d: ldc_w "Stage Maker"
      // a150: bipush 1
      // a151: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // a154: goto a16b
      // a159: astore 4
      // a15b: aconst_null
      // a15c: ldc_w "Unable to download stage.  Unknown Error!     \nPlease try again later."
      // a15f: ldc_w "Stage Maker"
      // a162: bipush 1
      // a163: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // a166: goto a16b
      // a16b: goto a170
      // a170: goto a197
      // a175: aload 0
      // a176: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a179: ldc_w "-    Error Loading this stage's info!    -"
      // a17c: sipush 550
      // a17f: aload 0
      // a180: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a183: ldc_w "-    Error Loading this stage's info!    -"
      // a186: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a189: bipush 2
      // a18a: idiv
      // a18b: isub
      // a18c: sipush 156
      // a18f: iload 1
      // a190: bipush 20
      // a192: imul
      // a193: iadd
      // a194: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a197: aload 0
      // a198: ldc_w "X"
      // a19b: sipush 765
      // a19e: sipush 156
      // a1a1: iload 1
      // a1a2: bipush 20
      // a1a4: imul
      // a1a5: iadd
      // a1a6: invokevirtual StageMaker.ovbutton (Ljava/lang/String;II)Z
      // a1a9: ifne a1b1
      // a1ac: goto a2d7
      // a1b1: aconst_null
      // a1b2: new java/lang/StringBuilder
      // a1b5: dup
      // a1b6: invokespecial java/lang/StringBuilder.<init> ()V
      // a1b9: ldc_w "Remove "
      // a1bc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a1bf: aload 0
      // a1c0: getfield StageMaker.mystages [Ljava/lang/String;
      // a1c3: iload 1
      // a1c4: aaload
      // a1c5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a1c8: ldc_w " from your account?"
      // a1cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a1ce: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a1d1: ldc_w "Stage Maker"
      // a1d4: bipush 0
      // a1d5: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // a1d8: ifeq a1e0
      // a1db: goto a2d7
      // a1e0: aload 0
      // a1e1: new java/awt/Cursor
      // a1e4: dup
      // a1e5: bipush 3
      // a1e6: invokespecial java/awt/Cursor.<init> (I)V
      // a1e9: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a1ec: bipush -1
      // a1ed: istore 2
      // a1ee: new java/net/Socket
      // a1f1: dup
      // a1f2: ldc_w "multiplayer.needformadness.com"
      // a1f5: sipush 7061
      // a1f8: invokespecial java/net/Socket.<init> (Ljava/lang/String;I)V
      // a1fb: astore 3
      // a1fc: new java/io/BufferedReader
      // a1ff: dup
      // a200: new java/io/InputStreamReader
      // a203: dup
      // a204: aload 3
      // a205: invokevirtual java/net/Socket.getInputStream ()Ljava/io/InputStream;
      // a208: invokespecial java/io/InputStreamReader.<init> (Ljava/io/InputStream;)V
      // a20b: invokespecial java/io/BufferedReader.<init> (Ljava/io/Reader;)V
      // a20e: astore 4
      // a210: new java/io/PrintWriter
      // a213: dup
      // a214: aload 3
      // a215: invokevirtual java/net/Socket.getOutputStream ()Ljava/io/OutputStream;
      // a218: bipush 1
      // a219: invokespecial java/io/PrintWriter.<init> (Ljava/io/OutputStream;Z)V
      // a21c: astore 5
      // a21e: aload 5
      // a220: new java/lang/StringBuilder
      // a223: dup
      // a224: invokespecial java/lang/StringBuilder.<init> ()V
      // a227: ldc_w "19|"
      // a22a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a22d: aload 0
      // a22e: getfield StageMaker.tnick Ljava/awt/TextField;
      // a231: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // a234: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a237: ldc_w "|"
      // a23a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a23d: aload 0
      // a23e: getfield StageMaker.tpass Ljava/awt/TextField;
      // a241: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // a244: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a247: ldc_w "|"
      // a24a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a24d: aload 0
      // a24e: getfield StageMaker.mystages [Ljava/lang/String;
      // a251: iload 1
      // a252: aaload
      // a253: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a256: ldc_w "|"
      // a259: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a25c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a25f: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // a262: aload 4
      // a264: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // a267: astore 6
      // a269: aload 6
      // a26b: ifnonnull a273
      // a26e: goto a280
      // a273: aload 0
      // a274: aload 6
      // a276: bipush 0
      // a277: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // a27a: istore 2
      // a27b: goto a280
      // a280: aload 3
      // a281: invokevirtual java/net/Socket.close ()V
      // a284: goto a28c
      // a289: astore 3
      // a28a: bipush -1
      // a28b: istore 2
      // a28c: iload 2
      // a28d: ifeq a295
      // a290: goto a29f
      // a295: aload 0
      // a296: bipush 1
      // a297: putfield StageMaker.logged I
      // a29a: goto a2d2
      // a29f: aload 0
      // a2a0: new java/awt/Cursor
      // a2a3: dup
      // a2a4: bipush 0
      // a2a5: invokespecial java/awt/Cursor.<init> (I)V
      // a2a8: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a2ab: aconst_null
      // a2ac: new java/lang/StringBuilder
      // a2af: dup
      // a2b0: invokespecial java/lang/StringBuilder.<init> ()V
      // a2b3: ldc_w "Failed to remove "
      // a2b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a2b9: aload 0
      // a2ba: getfield StageMaker.mystages [Ljava/lang/String;
      // a2bd: iload 1
      // a2be: aaload
      // a2bf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a2c2: ldc_w " from your account.  Unknown Error!     \nPlease try again later."
      // a2c5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a2c8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a2cb: ldc_w "Stage Maker"
      // a2ce: bipush 1
      // a2cf: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // a2d2: goto a2d7
      // a2d7: iinc 1 1
      // a2da: goto 96f2
      // a2df: goto a2e4
      // a2e4: aload 0
      // a2e5: getfield StageMaker.logged I
      // a2e8: bipush 2
      // a2e9: if_icmpeq a2f1
      // a2ec: goto a55a
      // a2f1: aload 0
      // a2f2: getfield StageMaker.mystages [Ljava/lang/String;
      // a2f5: aload 0
      // a2f6: getfield StageMaker.roto I
      // a2f9: aload 0
      // a2fa: getfield StageMaker.pubitem LSmenu;
      // a2fd: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // a300: aastore
      // a301: aload 0
      // a302: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a305: new java/awt/Font
      // a308: dup
      // a309: ldc_w "Arial"
      // a30c: bipush 1
      // a30d: bipush 13
      // a30f: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a312: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a315: aload 0
      // a316: aload 0
      // a317: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a31a: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a31d: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a320: aload 0
      // a321: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a324: new java/awt/Color
      // a327: dup
      // a328: sipush 225
      // a32b: sipush 225
      // a32e: sipush 225
      // a331: invokespecial java/awt/Color.<init> (III)V
      // a334: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a337: aload 0
      // a338: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a33b: bipush 50
      // a33d: sipush 150
      // a340: sipush 600
      // a343: sipush 150
      // a346: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a349: aload 0
      // a34a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a34d: new java/awt/Color
      // a350: dup
      // a351: bipush 0
      // a352: bipush 0
      // a353: bipush 0
      // a354: invokespecial java/awt/Color.<init> (III)V
      // a357: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a35a: aload 0
      // a35b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a35e: new java/lang/StringBuilder
      // a361: dup
      // a362: invokespecial java/lang/StringBuilder.<init> ()V
      // a365: ldc_w "Loading "
      // a368: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a36b: aload 0
      // a36c: getfield StageMaker.mystages [Ljava/lang/String;
      // a36f: aload 0
      // a370: getfield StageMaker.roto I
      // a373: aaload
      // a374: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a377: ldc_w "‘s info..."
      // a37a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a37d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a380: sipush 400
      // a383: aload 0
      // a384: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a387: new java/lang/StringBuilder
      // a38a: dup
      // a38b: invokespecial java/lang/StringBuilder.<init> ()V
      // a38e: ldc_w "Loading "
      // a391: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a394: aload 0
      // a395: getfield StageMaker.mystages [Ljava/lang/String;
      // a398: aload 0
      // a399: getfield StageMaker.roto I
      // a39c: aaload
      // a39d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a3a0: ldc_w "‘s info..."
      // a3a3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a3a6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a3a9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a3ac: bipush 2
      // a3ad: idiv
      // a3ae: isub
      // a3af: sipush 220
      // a3b2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a3b5: aload 0
      // a3b6: invokevirtual StageMaker.repaint ()V
      // a3b9: aload 0
      // a3ba: getfield StageMaker.maker [Ljava/lang/String;
      // a3bd: aload 0
      // a3be: getfield StageMaker.roto I
      // a3c1: ldc_w "Unkown"
      // a3c4: aastore
      // a3c5: aload 0
      // a3c6: getfield StageMaker.pubt [I
      // a3c9: aload 0
      // a3ca: getfield StageMaker.roto I
      // a3cd: bipush -1
      // a3ce: iastore
      // a3cf: aload 0
      // a3d0: getfield StageMaker.nad [I
      // a3d3: aload 0
      // a3d4: getfield StageMaker.roto I
      // a3d7: bipush 0
      // a3d8: iastore
      // a3d9: ldc ""
      // a3db: astore 1
      // a3dc: new java/lang/StringBuilder
      // a3df: dup
      // a3e0: invokespecial java/lang/StringBuilder.<init> ()V
      // a3e3: ldc_w "http://multiplayer.needformadness.com/tracks/"
      // a3e6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a3e9: aload 0
      // a3ea: getfield StageMaker.mystages [Ljava/lang/String;
      // a3ed: aload 0
      // a3ee: getfield StageMaker.roto I
      // a3f1: aaload
      // a3f2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a3f5: ldc_w ".txt?reqlo="
      // a3f8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a3fb: invokestatic java/lang/Math.random ()D
      // a3fe: ldc2_w 1000.0
      // a401: dmul
      // a402: d2i
      // a403: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a406: ldc ""
      // a408: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a40b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a40e: astore 2
      // a40f: aload 2
      // a410: bipush 32
      // a412: bipush 95
      // a414: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // a417: astore 2
      // a418: new java/net/URL
      // a41b: dup
      // a41c: aload 2
      // a41d: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // a420: astore 3
      // a421: new java/io/DataInputStream
      // a424: dup
      // a425: aload 3
      // a426: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // a429: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // a42c: astore 4
      // a42e: aload 4
      // a430: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // a433: dup
      // a434: astore 1
      // a435: ifnonnull a43d
      // a438: goto a4f9
      // a43d: new java/lang/StringBuilder
      // a440: dup
      // a441: invokespecial java/lang/StringBuilder.<init> ()V
      // a444: ldc ""
      // a446: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a449: aload 1
      // a44a: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // a44d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a450: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a453: astore 1
      // a454: aload 1
      // a455: ldc_w "details"
      // a458: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // a45b: ifne a463
      // a45e: goto a42e
      // a463: aload 0
      // a464: getfield StageMaker.maker [Ljava/lang/String;
      // a467: aload 0
      // a468: getfield StageMaker.roto I
      // a46b: aload 0
      // a46c: ldc_w "details"
      // a46f: aload 1
      // a470: bipush 0
      // a471: invokevirtual StageMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // a474: aastore
      // a475: aload 0
      // a476: getfield StageMaker.pubt [I
      // a479: aload 0
      // a47a: getfield StageMaker.roto I
      // a47d: aload 0
      // a47e: ldc_w "details"
      // a481: aload 1
      // a482: bipush 1
      // a483: invokevirtual StageMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // a486: iastore
      // a487: bipush 0
      // a488: istore 5
      // a48a: iload 5
      // a48c: ifeq a494
      // a48f: goto a4f4
      // a494: aload 0
      // a495: getfield StageMaker.addeda [[Ljava/lang/String;
      // a498: aload 0
      // a499: getfield StageMaker.roto I
      // a49c: aaload
      // a49d: aload 0
      // a49e: getfield StageMaker.nad [I
      // a4a1: aload 0
      // a4a2: getfield StageMaker.roto I
      // a4a5: iaload
      // a4a6: aload 0
      // a4a7: ldc_w "details"
      // a4aa: aload 1
      // a4ab: bipush 2
      // a4ac: aload 0
      // a4ad: getfield StageMaker.nad [I
      // a4b0: aload 0
      // a4b1: getfield StageMaker.roto I
      // a4b4: iaload
      // a4b5: iadd
      // a4b6: invokevirtual StageMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // a4b9: aastore
      // a4ba: aload 0
      // a4bb: getfield StageMaker.addeda [[Ljava/lang/String;
      // a4be: aload 0
      // a4bf: getfield StageMaker.roto I
      // a4c2: aaload
      // a4c3: aload 0
      // a4c4: getfield StageMaker.nad [I
      // a4c7: aload 0
      // a4c8: getfield StageMaker.roto I
      // a4cb: iaload
      // a4cc: aaload
      // a4cd: ldc ""
      // a4cf: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a4d2: ifne a4da
      // a4d5: goto a4e2
      // a4da: bipush 1
      // a4db: istore 5
      // a4dd: goto a48a
      // a4e2: aload 0
      // a4e3: getfield StageMaker.nad [I
      // a4e6: aload 0
      // a4e7: getfield StageMaker.roto I
      // a4ea: dup2
      // a4eb: iaload
      // a4ec: bipush 1
      // a4ed: iadd
      // a4ee: iastore
      // a4ef: goto a48a
      // a4f4: goto a42e
      // a4f9: aload 0
      // a4fa: dup
      // a4fb: getfield StageMaker.nms I
      // a4fe: bipush 1
      // a4ff: iadd
      // a500: putfield StageMaker.nms I
      // a503: aload 0
      // a504: getfield StageMaker.nms I
      // a507: bipush 20
      // a509: if_icmpgt a511
      // a50c: goto a51c
      // a511: aload 0
      // a512: bipush 20
      // a514: putfield StageMaker.nms I
      // a517: goto a51c
      // a51c: aload 0
      // a51d: dup
      // a51e: getfield StageMaker.roto I
      // a521: bipush 1
      // a522: iadd
      // a523: putfield StageMaker.roto I
      // a526: aload 0
      // a527: getfield StageMaker.roto I
      // a52a: bipush 20
      // a52c: if_icmpge a534
      // a52f: goto a53e
      // a534: aload 0
      // a535: bipush 0
      // a536: putfield StageMaker.roto I
      // a539: goto a53e
      // a53e: goto a544
      // a543: astore 2
      // a544: aload 0
      // a545: new java/awt/Cursor
      // a548: dup
      // a549: bipush 0
      // a54a: invokespecial java/awt/Cursor.<init> (I)V
      // a54d: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a550: aload 0
      // a551: bipush 3
      // a552: putfield StageMaker.logged I
      // a555: goto a55a
      // a55a: aload 0
      // a55b: getfield StageMaker.logged I
      // a55e: bipush -1
      // a55f: if_icmpeq a567
      // a562: goto a60a
      // a567: aload 0
      // a568: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a56b: new java/awt/Font
      // a56e: dup
      // a56f: ldc_w "Arial"
      // a572: bipush 1
      // a573: bipush 13
      // a575: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a578: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a57b: aload 0
      // a57c: aload 0
      // a57d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a580: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a583: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a586: aload 0
      // a587: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a58a: new java/awt/Color
      // a58d: dup
      // a58e: bipush 0
      // a58f: bipush 0
      // a590: bipush 0
      // a591: invokespecial java/awt/Color.<init> (III)V
      // a594: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a597: aload 0
      // a598: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a59b: ldc_w "Ready to publish..."
      // a59e: sipush 400
      // a5a1: aload 0
      // a5a2: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a5a5: ldc_w "Ready to publish..."
      // a5a8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a5ab: bipush 2
      // a5ac: idiv
      // a5ad: isub
      // a5ae: sipush 220
      // a5b1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a5b4: aload 0
      // a5b5: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a5b8: new java/lang/StringBuilder
      // a5bb: dup
      // a5bc: invokespecial java/lang/StringBuilder.<init> ()V
      // a5bf: ldc_w "Click ‘Publish’ above to add stage: '"
      // a5c2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5c5: aload 0
      // a5c6: getfield StageMaker.stagename Ljava/lang/String;
      // a5c9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5cc: ldc_w "'."
      // a5cf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5d2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a5d5: sipush 400
      // a5d8: aload 0
      // a5d9: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a5dc: new java/lang/StringBuilder
      // a5df: dup
      // a5e0: invokespecial java/lang/StringBuilder.<init> ()V
      // a5e3: ldc_w "Click ‘Publish’ above to add stage: '"
      // a5e6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5e9: aload 0
      // a5ea: getfield StageMaker.stagename Ljava/lang/String;
      // a5ed: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5f0: ldc_w "'."
      // a5f3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a5f6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a5f9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a5fc: bipush 2
      // a5fd: idiv
      // a5fe: isub
      // a5ff: sipush 280
      // a602: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a605: goto a60a
      // a60a: aload 0
      // a60b: getfield StageMaker.logged I
      // a60e: bipush 1
      // a60f: if_icmpeq a617
      // a612: goto a818
      // a617: aload 0
      // a618: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a61b: new java/awt/Color
      // a61e: dup
      // a61f: sipush 225
      // a622: sipush 225
      // a625: sipush 225
      // a628: invokespecial java/awt/Color.<init> (III)V
      // a62b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a62e: aload 0
      // a62f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a632: bipush 11
      // a634: sipush 141
      // a637: sipush 779
      // a63a: sipush 401
      // a63d: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a640: aload 0
      // a641: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a644: new java/awt/Font
      // a647: dup
      // a648: ldc_w "Arial"
      // a64b: bipush 1
      // a64c: bipush 13
      // a64e: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a651: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a654: aload 0
      // a655: aload 0
      // a656: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a659: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a65c: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a65f: aload 0
      // a660: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a663: new java/awt/Color
      // a666: dup
      // a667: bipush 0
      // a668: bipush 0
      // a669: bipush 0
      // a66a: invokespecial java/awt/Color.<init> (III)V
      // a66d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a670: aload 0
      // a671: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a674: ldc_w "Loading your account's stage list..."
      // a677: sipush 400
      // a67a: aload 0
      // a67b: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a67e: ldc_w "Loading your account's stage list..."
      // a681: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a684: bipush 2
      // a685: idiv
      // a686: isub
      // a687: sipush 220
      // a68a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a68d: aload 0
      // a68e: invokevirtual StageMaker.repaint ()V
      // a691: aload 0
      // a692: getfield StageMaker.pubitem LSmenu;
      // a695: invokevirtual Smenu.removeAll ()V
      // a698: aload 0
      // a699: getfield StageMaker.pubitem LSmenu;
      // a69c: aload 0
      // a69d: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a6a0: ldc_w "Account Stage"
      // a6a3: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // a6a6: aload 0
      // a6a7: bipush 0
      // a6a8: putfield StageMaker.nms I
      // a6ab: aload 0
      // a6ac: bipush 0
      // a6ad: putfield StageMaker.roto I
      // a6b0: bipush 0
      // a6b1: istore 1
      // a6b2: ldc ""
      // a6b4: astore 2
      // a6b5: new java/net/URL
      // a6b8: dup
      // a6b9: new java/lang/StringBuilder
      // a6bc: dup
      // a6bd: invokespecial java/lang/StringBuilder.<init> ()V
      // a6c0: ldc_w "http://multiplayer.needformadness.com/tracks/lists/"
      // a6c3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a6c6: aload 0
      // a6c7: getfield StageMaker.tnick Ljava/awt/TextField;
      // a6ca: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // a6cd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a6d0: ldc_w ".txt?reqlo="
      // a6d3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a6d6: invokestatic java/lang/Math.random ()D
      // a6d9: ldc2_w 1000.0
      // a6dc: dmul
      // a6dd: d2i
      // a6de: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a6e1: ldc ""
      // a6e3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a6e6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a6e9: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // a6ec: astore 3
      // a6ed: new java/io/DataInputStream
      // a6f0: dup
      // a6f1: aload 3
      // a6f2: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // a6f5: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // a6f8: astore 4
      // a6fa: aload 4
      // a6fc: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // a6ff: dup
      // a700: astore 2
      // a701: ifnonnull a709
      // a704: goto a784
      // a709: new java/lang/StringBuilder
      // a70c: dup
      // a70d: invokespecial java/lang/StringBuilder.<init> ()V
      // a710: ldc ""
      // a712: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a715: aload 2
      // a716: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // a719: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a71c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a71f: astore 2
      // a720: aload 2
      // a721: ldc_w "mystages"
      // a724: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // a727: ifne a72f
      // a72a: goto a6fa
      // a72f: bipush 1
      // a730: istore 5
      // a732: iload 5
      // a734: ifne a73c
      // a737: goto a77f
      // a73c: iload 1
      // a73d: sipush 700
      // a740: if_icmplt a748
      // a743: goto a77f
      // a748: aload 0
      // a749: ldc_w "mystages"
      // a74c: aload 2
      // a74d: iload 1
      // a74e: invokevirtual StageMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // a751: astore 6
      // a753: aload 6
      // a755: ldc ""
      // a757: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a75a: ifne a762
      // a75d: goto a76a
      // a762: bipush 0
      // a763: istore 5
      // a765: goto a77a
      // a76a: aload 0
      // a76b: getfield StageMaker.pubitem LSmenu;
      // a76e: aload 0
      // a76f: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a772: aload 6
      // a774: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // a777: iinc 1 1
      // a77a: goto a732
      // a77f: goto a6fa
      // a784: aload 0
      // a785: new java/awt/Cursor
      // a788: dup
      // a789: bipush 0
      // a78a: invokespecial java/awt/Cursor.<init> (I)V
      // a78d: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a790: aload 0
      // a791: bipush -1
      // a792: putfield StageMaker.logged I
      // a795: aload 4
      // a797: invokevirtual java/io/DataInputStream.close ()V
      // a79a: goto a7ec
      // a79f: astore 3
      // a7a0: new java/lang/StringBuilder
      // a7a3: dup
      // a7a4: invokespecial java/lang/StringBuilder.<init> ()V
      // a7a7: ldc ""
      // a7a9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a7ac: aload 3
      // a7ad: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // a7b0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a7b3: astore 4
      // a7b5: aload 4
      // a7b7: ldc_w "FileNotFound"
      // a7ba: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // a7bd: bipush -1
      // a7be: if_icmpne a7c6
      // a7c1: goto a7dc
      // a7c6: aload 0
      // a7c7: new java/awt/Cursor
      // a7ca: dup
      // a7cb: bipush 0
      // a7cc: invokespecial java/awt/Cursor.<init> (I)V
      // a7cf: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a7d2: aload 0
      // a7d3: bipush -1
      // a7d4: putfield StageMaker.logged I
      // a7d7: goto a7ec
      // a7dc: aload 0
      // a7dd: bipush 0
      // a7de: putfield StageMaker.logged I
      // a7e1: aconst_null
      // a7e2: ldc_w "Unable to connect to server at this moment, please try again later."
      // a7e5: ldc_w "Stage Maker"
      // a7e8: bipush 1
      // a7e9: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // a7ec: aload 0
      // a7ed: getfield StageMaker.justpubd Ljava/lang/String;
      // a7f0: ldc ""
      // a7f2: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a7f5: ifeq a7fd
      // a7f8: goto a813
      // a7fd: aload 0
      // a7fe: getfield StageMaker.pubitem LSmenu;
      // a801: aload 0
      // a802: getfield StageMaker.justpubd Ljava/lang/String;
      // a805: invokevirtual Smenu.select (Ljava/lang/String;)V
      // a808: aload 0
      // a809: ldc ""
      // a80b: putfield StageMaker.justpubd Ljava/lang/String;
      // a80e: goto a813
      // a813: goto a818
      // a818: aload 0
      // a819: getfield StageMaker.logged I
      // a81c: ifeq a824
      // a81f: goto ab2b
      // a824: aload 0
      // a825: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a828: new java/awt/Font
      // a82b: dup
      // a82c: ldc_w "Arial"
      // a82f: bipush 1
      // a830: bipush 13
      // a832: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a835: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a838: aload 0
      // a839: aload 0
      // a83a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a83d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a840: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a843: aload 0
      // a844: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a847: ldc_w "Login to Retrieve your Account Stages"
      // a84a: sipush 400
      // a84d: aload 0
      // a84e: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a851: ldc_w "Login to Retrieve your Account Stages"
      // a854: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a857: bipush 2
      // a858: idiv
      // a859: isub
      // a85a: sipush 220
      // a85d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a860: aload 0
      // a861: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a864: ldc_w "Nickname:"
      // a867: sipush 376
      // a86a: aload 0
      // a86b: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a86e: ldc_w "Nickname:"
      // a871: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a874: isub
      // a875: bipush 14
      // a877: isub
      // a878: sipush 266
      // a87b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a87e: aload 0
      // a87f: getfield StageMaker.tnick Ljava/awt/TextField;
      // a882: invokevirtual java/awt/TextField.isShowing ()Z
      // a885: ifeq a88d
      // a888: goto a899
      // a88d: aload 0
      // a88e: getfield StageMaker.tnick Ljava/awt/TextField;
      // a891: invokevirtual java/awt/TextField.show ()V
      // a894: goto a899
      // a899: aload 0
      // a89a: aload 0
      // a89b: getfield StageMaker.tnick Ljava/awt/TextField;
      // a89e: sipush 376
      // a8a1: sipush 250
      // a8a4: sipush 129
      // a8a7: bipush 23
      // a8a9: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // a8ac: aload 0
      // a8ad: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // a8b0: ldc_w "Password:"
      // a8b3: sipush 376
      // a8b6: aload 0
      // a8b7: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // a8ba: ldc_w "Password:"
      // a8bd: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a8c0: isub
      // a8c1: bipush 14
      // a8c3: isub
      // a8c4: sipush 296
      // a8c7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a8ca: aload 0
      // a8cb: getfield StageMaker.tpass Ljava/awt/TextField;
      // a8ce: invokevirtual java/awt/TextField.isShowing ()Z
      // a8d1: ifeq a8d9
      // a8d4: goto a8e5
      // a8d9: aload 0
      // a8da: getfield StageMaker.tpass Ljava/awt/TextField;
      // a8dd: invokevirtual java/awt/TextField.show ()V
      // a8e0: goto a8e5
      // a8e5: aload 0
      // a8e6: aload 0
      // a8e7: getfield StageMaker.tpass Ljava/awt/TextField;
      // a8ea: sipush 376
      // a8ed: sipush 280
      // a8f0: sipush 129
      // a8f3: bipush 23
      // a8f5: invokevirtual StageMaker.movefield (Ljava/awt/Component;IIII)V
      // a8f8: aload 0
      // a8f9: ldc_w "       Login       "
      // a8fc: sipush 400
      // a8ff: sipush 340
      // a902: bipush 0
      // a903: bipush 1
      // a904: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // a907: ifne a90f
      // a90a: goto aa6a
      // a90f: aload 0
      // a910: new java/awt/Cursor
      // a913: dup
      // a914: bipush 3
      // a915: invokespecial java/awt/Cursor.<init> (I)V
      // a918: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // a91b: bipush -1
      // a91c: istore 1
      // a91d: new java/net/Socket
      // a920: dup
      // a921: ldc_w "multiplayer.needformadness.com"
      // a924: sipush 7061
      // a927: invokespecial java/net/Socket.<init> (Ljava/lang/String;I)V
      // a92a: astore 2
      // a92b: new java/io/BufferedReader
      // a92e: dup
      // a92f: new java/io/InputStreamReader
      // a932: dup
      // a933: aload 2
      // a934: invokevirtual java/net/Socket.getInputStream ()Ljava/io/InputStream;
      // a937: invokespecial java/io/InputStreamReader.<init> (Ljava/io/InputStream;)V
      // a93a: invokespecial java/io/BufferedReader.<init> (Ljava/io/Reader;)V
      // a93d: astore 3
      // a93e: new java/io/PrintWriter
      // a941: dup
      // a942: aload 2
      // a943: invokevirtual java/net/Socket.getOutputStream ()Ljava/io/OutputStream;
      // a946: bipush 1
      // a947: invokespecial java/io/PrintWriter.<init> (Ljava/io/OutputStream;Z)V
      // a94a: astore 4
      // a94c: aload 4
      // a94e: new java/lang/StringBuilder
      // a951: dup
      // a952: invokespecial java/lang/StringBuilder.<init> ()V
      // a955: ldc_w "1|"
      // a958: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a95b: aload 0
      // a95c: getfield StageMaker.tnick Ljava/awt/TextField;
      // a95f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // a962: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // a965: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a968: ldc_w "|"
      // a96b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a96e: aload 0
      // a96f: getfield StageMaker.tpass Ljava/awt/TextField;
      // a972: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // a975: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a978: ldc_w "|"
      // a97b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a97e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a981: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // a984: aload 3
      // a985: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // a988: astore 5
      // a98a: aload 5
      // a98c: ifnonnull a994
      // a98f: goto a9a1
      // a994: aload 0
      // a995: aload 5
      // a997: bipush 0
      // a998: invokevirtual StageMaker.servervalue (Ljava/lang/String;I)I
      // a99b: istore 1
      // a99c: goto a9a1
      // a9a1: aload 2
      // a9a2: invokevirtual java/net/Socket.close ()V
      // a9a5: goto a9ad
      // a9aa: astore 2
      // a9ab: bipush -1
      // a9ac: istore 1
      // a9ad: iload 1
      // a9ae: ifne a9b6
      // a9b1: goto a9cb
      // a9b6: iload 1
      // a9b7: bipush 3
      // a9b8: if_icmpne a9c0
      // a9bb: goto a9cb
      // a9c0: iload 1
      // a9c1: bipush 10
      // a9c3: if_icmpgt a9cb
      // a9c6: goto a9e7
      // a9cb: aload 0
      // a9cc: getfield StageMaker.tnick Ljava/awt/TextField;
      // a9cf: invokevirtual java/awt/TextField.hide ()V
      // a9d2: aload 0
      // a9d3: getfield StageMaker.tpass Ljava/awt/TextField;
      // a9d6: invokevirtual java/awt/TextField.hide ()V
      // a9d9: aload 0
      // a9da: bipush 1
      // a9db: putfield StageMaker.logged I
      // a9de: aload 0
      // a9df: invokevirtual StageMaker.savesettings ()V
      // a9e2: goto a9e7
      // a9e7: iload 1
      // a9e8: bipush 1
      // a9e9: if_icmpne a9f1
      // a9ec: goto a9fb
      // a9f1: iload 1
      // a9f2: bipush 2
      // a9f3: if_icmpeq a9fb
      // a9f6: goto aa17
      // a9fb: aload 0
      // a9fc: new java/awt/Cursor
      // a9ff: dup
      // aa00: bipush 0
      // aa01: invokespecial java/awt/Cursor.<init> (I)V
      // aa04: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // aa07: aconst_null
      // aa08: ldc_w "Sorry.  Incorrect Nickname or Password!"
      // aa0b: ldc_w "Stage Maker"
      // aa0e: bipush 0
      // aa0f: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // aa12: goto aa17
      // aa17: iload 1
      // aa18: sipush -167
      // aa1b: if_icmpeq aa23
      // aa1e: goto aa3f
      // aa23: aload 0
      // aa24: new java/awt/Cursor
      // aa27: dup
      // aa28: bipush 0
      // aa29: invokespecial java/awt/Cursor.<init> (I)V
      // aa2c: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // aa2f: aconst_null
      // aa30: ldc_w "Sorry.  Your trial account cannot publish stages.  Please upgrade to a full account!   "
      // aa33: ldc_w "Stage Maker"
      // aa36: bipush 0
      // aa37: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // aa3a: goto aa3f
      // aa3f: iload 1
      // aa40: bipush -1
      // aa41: if_icmpeq aa49
      // aa44: goto aa65
      // aa49: aload 0
      // aa4a: new java/awt/Cursor
      // aa4d: dup
      // aa4e: bipush 0
      // aa4f: invokespecial java/awt/Cursor.<init> (I)V
      // aa52: invokevirtual StageMaker.setCursor (Ljava/awt/Cursor;)V
      // aa55: aconst_null
      // aa56: ldc_w "Unable to connect to server at this moment, please try again later."
      // aa59: ldc_w "Stage Maker"
      // aa5c: bipush 1
      // aa5d: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // aa60: goto aa65
      // aa65: goto aa6a
      // aa6a: aload 0
      // aa6b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // aa6e: new java/awt/Font
      // aa71: dup
      // aa72: ldc_w "Arial"
      // aa75: bipush 1
      // aa76: bipush 13
      // aa78: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // aa7b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // aa7e: aload 0
      // aa7f: aload 0
      // aa80: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // aa83: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // aa86: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // aa89: aload 0
      // aa8a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // aa8d: ldc_w "Register a full account or if you have a trial account upgrade it!"
      // aa90: sipush 400
      // aa93: aload 0
      // aa94: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // aa97: ldc_w "Register a full account or if you have a trial account upgrade it!"
      // aa9a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // aa9d: bipush 2
      // aa9e: idiv
      // aa9f: isub
      // aaa0: sipush 450
      // aaa3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // aaa6: aload 0
      // aaa7: ldc_w "   Register!   "
      // aaaa: sipush 340
      // aaad: sipush 480
      // aab0: bipush 0
      // aab1: bipush 1
      // aab2: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // aab5: ifne aabd
      // aab8: goto aac8
      // aabd: ldc_w "http://multiplayer.needformadness.com/register.html?ref=game"
      // aac0: invokestatic Madness.openurl (Ljava/lang/String;)V
      // aac3: goto aac8
      // aac8: aload 0
      // aac9: ldc_w "   Upgrade!   "
      // aacc: sipush 460
      // aacf: sipush 480
      // aad2: bipush 0
      // aad3: bipush 1
      // aad4: invokevirtual StageMaker.button (Ljava/lang/String;IIIZ)Z
      // aad7: ifne aadf
      // aada: goto aaea
      // aadf: ldc_w "http://multiplayer.needformadness.com/edit.pl"
      // aae2: invokestatic Madness.openurl (Ljava/lang/String;)V
      // aae5: goto aaea
      // aaea: aload 0
      // aaeb: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // aaee: new java/awt/Font
      // aaf1: dup
      // aaf2: ldc_w "Arial"
      // aaf5: bipush 0
      // aaf6: bipush 12
      // aaf8: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // aafb: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // aafe: aload 0
      // aaff: aload 0
      // ab00: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ab03: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // ab06: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // ab09: aload 0
      // ab0a: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ab0d: ldc_w "You need a full account to publish your stages to the multiplayer game!"
      // ab10: sipush 400
      // ab13: aload 0
      // ab14: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // ab17: ldc_w "You need a full account to publish your stages to the multiplayer game!"
      // ab1a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ab1d: bipush 2
      // ab1e: idiv
      // ab1f: isub
      // ab20: sipush 505
      // ab23: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ab26: goto ab2b
      // ab2b: aload 0
      // ab2c: getfield StageMaker.tabed I
      // ab2f: aload 0
      // ab30: getfield StageMaker.tab I
      // ab33: if_icmpne ab3b
      // ab36: goto ab60
      // ab3b: aload 0
      // ab3c: getfield StageMaker.tabed I
      // ab3f: bipush -2
      // ab41: if_icmpeq ab49
      // ab44: goto ab53
      // ab49: aload 0
      // ab4a: bipush -1
      // ab4b: putfield StageMaker.tabed I
      // ab4e: goto ab60
      // ab53: aload 0
      // ab54: aload 0
      // ab55: getfield StageMaker.tab I
      // ab58: putfield StageMaker.tabed I
      // ab5b: goto ab60
      // ab60: aload 0
      // ab61: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ab64: new java/awt/Color
      // ab67: dup
      // ab68: bipush 0
      // ab69: bipush 0
      // ab6a: bipush 0
      // ab6b: invokespecial java/awt/Color.<init> (III)V
      // ab6e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // ab71: aload 0
      // ab72: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ab75: bipush 0
      // ab76: bipush 0
      // ab77: sipush 800
      // ab7a: bipush 25
      // ab7c: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // ab7f: aload 0
      // ab80: getfield StageMaker.onbtgame Z
      // ab83: ifeq ab8b
      // ab86: goto aba3
      // ab8b: aload 0
      // ab8c: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ab8f: aload 0
      // ab90: getfield StageMaker.btgame [Ljava/awt/Image;
      // ab93: bipush 0
      // ab94: aaload
      // ab95: sipush 620
      // ab98: bipush 0
      // ab99: aconst_null
      // ab9a: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // ab9d: pop
      // ab9e: goto abb6
      // aba3: aload 0
      // aba4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // aba7: aload 0
      // aba8: getfield StageMaker.btgame [Ljava/awt/Image;
      // abab: bipush 1
      // abac: aaload
      // abad: sipush 620
      // abb0: bipush 0
      // abb1: aconst_null
      // abb2: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // abb5: pop
      // abb6: aload 0
      // abb7: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // abba: new java/awt/Font
      // abbd: dup
      // abbe: ldc_w "Arial"
      // abc1: bipush 1
      // abc2: bipush 13
      // abc4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // abc7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // abca: aload 0
      // abcb: aload 0
      // abcc: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // abcf: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // abd2: putfield StageMaker.ftm Ljava/awt/FontMetrics;
      // abd5: bipush 4
      // abd6: anewarray 36
      // abd9: dup
      // abda: bipush 0
      // abdb: ldc_w "Stage"
      // abde: aastore
      // abdf: dup
      // abe0: bipush 1
      // abe1: ldc_w "Build"
      // abe4: aastore
      // abe5: dup
      // abe6: bipush 2
      // abe7: ldc_w "View & Edit"
      // abea: aastore
      // abeb: dup
      // abec: bipush 3
      // abed: ldc_w "Publish"
      // abf0: aastore
      // abf1: astore 1
      // abf2: bipush 4
      // abf3: newarray 10
      // abf5: dup
      // abf6: bipush 0
      // abf7: bipush 0
      // abf8: iastore
      // abf9: dup
      // abfa: bipush 1
      // abfb: bipush 0
      // abfc: iastore
      // abfd: dup
      // abfe: bipush 2
      // abff: bipush 100
      // ac01: iastore
      // ac02: dup
      // ac03: bipush 3
      // ac04: bipush 90
      // ac06: iastore
      // ac07: astore 2
      // ac08: bipush 4
      // ac09: newarray 10
      // ac0b: dup
      // ac0c: bipush 0
      // ac0d: bipush 0
      // ac0e: iastore
      // ac0f: dup
      // ac10: bipush 1
      // ac11: bipush 25
      // ac13: iastore
      // ac14: dup
      // ac15: bipush 2
      // ac16: bipush 25
      // ac18: iastore
      // ac19: dup
      // ac1a: bipush 3
      // ac1b: bipush 0
      // ac1c: iastore
      // ac1d: astore 3
      // ac1e: bipush 4
      // ac1f: istore 4
      // ac21: aload 0
      // ac22: getfield StageMaker.stagename Ljava/lang/String;
      // ac25: ldc ""
      // ac27: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // ac2a: ifeq ac32
      // ac2d: goto ac3e
      // ac32: aload 0
      // ac33: getfield StageMaker.sfase I
      // ac36: ifne ac3e
      // ac39: goto ac4b
      // ac3e: aload 0
      // ac3f: bipush 0
      // ac40: putfield StageMaker.tab I
      // ac43: bipush 1
      // ac44: istore 4
      // ac46: goto ac4b
      // ac4b: bipush 0
      // ac4c: istore 5
      // ac4e: iload 5
      // ac50: iload 4
      // ac52: if_icmplt ac5a
      // ac55: goto ada4
      // ac5a: aload 0
      // ac5b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ac5e: new java/awt/Color
      // ac61: dup
      // ac62: sipush 170
      // ac65: sipush 170
      // ac68: sipush 170
      // ac6b: invokespecial java/awt/Color.<init> (III)V
      // ac6e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // ac71: aload 0
      // ac72: getfield StageMaker.xm I
      // ac75: aload 2
      // ac76: bipush 0
      // ac77: iaload
      // ac78: if_icmpgt ac80
      // ac7b: goto acc5
      // ac80: aload 0
      // ac81: getfield StageMaker.xm I
      // ac84: aload 2
      // ac85: bipush 3
      // ac86: iaload
      // ac87: if_icmplt ac8f
      // ac8a: goto acc5
      // ac8f: aload 0
      // ac90: getfield StageMaker.ym I
      // ac93: ifgt ac9b
      // ac96: goto acc5
      // ac9b: aload 0
      // ac9c: getfield StageMaker.ym I
      // ac9f: bipush 25
      // aca1: if_icmplt aca9
      // aca4: goto acc5
      // aca9: aload 0
      // acaa: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // acad: new java/awt/Color
      // acb0: dup
      // acb1: sipush 200
      // acb4: sipush 200
      // acb7: sipush 200
      // acba: invokespecial java/awt/Color.<init> (III)V
      // acbd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // acc0: goto acc5
      // acc5: aload 0
      // acc6: getfield StageMaker.tab I
      // acc9: iload 5
      // accb: if_icmpeq acd3
      // acce: goto acef
      // acd3: aload 0
      // acd4: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // acd7: new java/awt/Color
      // acda: dup
      // acdb: sipush 225
      // acde: sipush 225
      // ace1: sipush 225
      // ace4: invokespecial java/awt/Color.<init> (III)V
      // ace7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // acea: goto acef
      // acef: aload 0
      // acf0: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // acf3: aload 2
      // acf4: aload 3
      // acf5: bipush 4
      // acf6: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // acf9: aload 0
      // acfa: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // acfd: new java/awt/Color
      // ad00: dup
      // ad01: bipush 0
      // ad02: bipush 0
      // ad03: bipush 0
      // ad04: invokespecial java/awt/Color.<init> (III)V
      // ad07: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // ad0a: aload 0
      // ad0b: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // ad0e: aload 1
      // ad0f: iload 5
      // ad11: aaload
      // ad12: iload 5
      // ad14: bipush 100
      // ad16: imul
      // ad17: bipush 45
      // ad19: iadd
      // ad1a: aload 0
      // ad1b: getfield StageMaker.ftm Ljava/awt/FontMetrics;
      // ad1e: aload 1
      // ad1f: iload 5
      // ad21: aaload
      // ad22: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ad25: bipush 2
      // ad26: idiv
      // ad27: isub
      // ad28: bipush 17
      // ad2a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ad2d: aload 0
      // ad2e: getfield StageMaker.xm I
      // ad31: aload 2
      // ad32: bipush 0
      // ad33: iaload
      // ad34: if_icmpgt ad3c
      // ad37: goto ad7d
      // ad3c: aload 0
      // ad3d: getfield StageMaker.xm I
      // ad40: aload 2
      // ad41: bipush 3
      // ad42: iaload
      // ad43: if_icmplt ad4b
      // ad46: goto ad7d
      // ad4b: aload 0
      // ad4c: getfield StageMaker.ym I
      // ad4f: ifgt ad57
      // ad52: goto ad7d
      // ad57: aload 0
      // ad58: getfield StageMaker.ym I
      // ad5b: bipush 25
      // ad5d: if_icmplt ad65
      // ad60: goto ad7d
      // ad65: aload 0
      // ad66: getfield StageMaker.mouses I
      // ad69: bipush -1
      // ad6a: if_icmpeq ad72
      // ad6d: goto ad7d
      // ad72: aload 0
      // ad73: iload 5
      // ad75: putfield StageMaker.tab I
      // ad78: goto ad7d
      // ad7d: bipush 0
      // ad7e: istore 6
      // ad80: iload 6
      // ad82: bipush 4
      // ad83: if_icmplt ad8b
      // ad86: goto ad9c
      // ad8b: aload 2
      // ad8c: iload 6
      // ad8e: dup2
      // ad8f: iaload
      // ad90: bipush 100
      // ad92: iadd
      // ad93: iastore
      // ad94: iinc 6 1
      // ad97: goto ad80
      // ad9c: iinc 5 1
      // ad9f: goto ac4e
      // ada4: aload 0
      // ada5: getfield StageMaker.mouses I
      // ada8: bipush -1
      // ada9: if_icmpeq adb1
      // adac: goto adbb
      // adb1: aload 0
      // adb2: bipush 0
      // adb3: putfield StageMaker.mouses I
      // adb6: goto adbb
      // adbb: aload 0
      // adbc: invokevirtual StageMaker.drawms ()V
      // adbf: aload 0
      // adc0: invokevirtual StageMaker.repaint ()V
      // adc3: aload 0
      // adc4: getfield StageMaker.exwist Z
      // adc7: ifeq adcf
      // adca: goto ade6
      // adcf: aload 0
      // add0: getfield StageMaker.thredo Ljava/lang/Thread;
      // add3: pop
      // add4: ldc2_w 40
      // add7: invokestatic java/lang/Thread.sleep (J)V
      // adda: goto ade6
      // addf: astore 5
      // ade1: goto ade6
      // ade6: goto 0225
      // adeb: aload 0
      // adec: getfield StageMaker.track LRadicalMod;
      // adef: invokevirtual RadicalMod.unload ()V
      // adf2: aload 0
      // adf3: aconst_null
      // adf4: putfield StageMaker.track LRadicalMod;
      // adf7: aload 0
      // adf8: getfield StageMaker.rd Ljava/awt/Graphics2D;
      // adfb: invokevirtual java/awt/Graphics2D.dispose ()V
      // adfe: invokestatic java/lang/System.gc ()V
      // ae01: getstatic Madness.endadv I
      // ae04: bipush 2
      // ae05: if_icmpeq ae0d
      // ae08: goto ae15
      // ae0d: invokestatic Madness.advopen ()V
      // ae10: goto ae15
      // ae15: return
      // try (4841 -> 4908): 4909 java/lang/Exception
      // try (5017 -> 5080): 5081 java/lang/Exception
      // try (8064 -> 8084): 8085 java/lang/Exception
      // try (13247 -> 13273): 13274 java/lang/Exception
      // try (15132 -> 15183): 15184 java/lang/Exception
      // try (15271 -> 15296): 15297 java/lang/Exception
      // try (15301 -> 15620): 15621 java/lang/Exception
      // try (16588 -> 16593): 16594 java/lang/InterruptedException
      // try (16417 -> 16847): 16848 java/lang/Exception
      // try (18024 -> 18151): 18152 java/lang/Exception
      // try (17697 -> 18183): 18184 java/lang/Exception
      // try (18248 -> 18311): 18312 java/lang/Exception
      // try (18462 -> 18627): 18628 java/lang/Exception
      // try (18792 -> 18885): 18886 java/lang/Exception
      // try (19044 -> 19101): 19102 java/lang/Exception
      // try (19577 -> 19582): 19583 java/lang/InterruptedException
   }

   public void removesp() {
      if (this.nundo < 5000) {
         this.undos[this.nundo] = this.bstage;
         this.nundo++;
      }

      String var1 = "";
      if (this.co[this.esp].colok != 30 && this.co[this.esp].colok != 31 && this.co[this.esp].colok != 32 && this.co[this.esp].colok != 66) {
         var1 = "set(" + (this.co[this.esp].colok + 10) + "," + this.co[this.esp].x + "," + this.co[this.esp].z + "," + this.co[this.esp].roofat + ")";
      }

      if (this.co[this.esp].colok == 31) {
         var1 = "fix("
            + (this.co[this.esp].colok + 10)
            + ","
            + this.co[this.esp].x
            + ","
            + this.co[this.esp].z
            + ","
            + this.co[this.esp].y
            + ","
            + this.co[this.esp].roofat
            + ")";
      }

      if (this.co[this.esp].colok == 30 || this.co[this.esp].colok == 32) {
         var1 = "chk(" + (this.co[this.esp].colok + 10) + "," + this.co[this.esp].x + "," + this.co[this.esp].z + "," + this.co[this.esp].roofat + ")";
      }

      if (this.co[this.esp].colok == 54) {
         var1 = "chk("
            + (this.co[this.esp].colok + 10)
            + ","
            + this.co[this.esp].x
            + ","
            + this.co[this.esp].z
            + ","
            + this.co[this.esp].roofat
            + ","
            + this.co[this.esp].y
            + ")";
      }

      if (this.co[this.esp].colok == 66) {
         var1 = "pile("
            + this.co[this.esp].srz
            + ","
            + this.co[this.esp].srx
            + ","
            + this.co[this.esp].sry
            + ","
            + this.co[this.esp].x
            + ","
            + this.co[this.esp].z
            + ")";
      }

      int var2 = this.bstage.indexOf(var1);
      int var3 = var2 + var1.length();
      int var4 = -1;
      int var5 = this.bstage.indexOf("set", var3);
      if (var5 != -1) {
         var4 = var5;
      }

      var5 = this.bstage.indexOf("chk", var3);
      if (var5 != -1 && var5 < var4) {
         var4 = var5;
      }

      var5 = this.bstage.indexOf("fix", var3);
      if (var5 != -1 && var5 < var4) {
         var4 = var5;
      }

      if (var4 == -1) {
         var4 = this.bstage.indexOf("\r\n", var3);
         if (var4 != -1) {
            var4++;
         }
      }

      if (var4 != -1) {
         var3 = var4;
      }

      if (var2 != -1) {
         this.bstage = "" + this.bstage.substring(0, var2) + "" + this.bstage.substring(var3, this.bstage.length()) + "";
      }

      this.readstage(0);
   }

   public void copyesp(boolean var1) {
      this.sp = this.co[this.esp].colok;
      this.rot = this.co[this.esp].roofat;
      if (this.sp == 2) {
         this.rot -= 30;
      }

      if (this.sp == 3) {
         this.rot += 30;
      }

      if (this.sp == 15) {
         this.rot += 90;
      }

      if (this.sp == 20) {
         this.rot += 180;
      }

      if (this.sp == 26) {
         this.rot -= 90;
      }

      if (this.sp == 0) {
         this.sptyp = 0;
         this.spart = 0;
      }

      if (this.sp == 4) {
         this.sptyp = 0;
         this.spart = 1;
      }

      if (this.sp == 13) {
         this.sptyp = 0;
         this.spart = 2;
      }

      if (this.sp == 3) {
         this.sptyp = 0;
         this.spart = 3;
      }

      if (this.sp == 2) {
         this.sptyp = 0;
         this.spart = 4;
      }

      if (this.sp == 1) {
         this.sptyp = 0;
         this.spart = 5;
      }

      if (this.sp == 35) {
         this.sptyp = 0;
         this.spart = 6;
      }

      if (this.sp == 36) {
         this.sptyp = 0;
         this.spart = 7;
      }

      if (this.sp == 10) {
         this.sptyp = 0;
         this.spart = 8;
      }

      if (this.sp == 5) {
         this.sptyp = 0;
         this.spart = 9;
      }

      if (this.sp == 7) {
         this.sptyp = 0;
         this.spart = 10;
      }

      if (this.sp == 14) {
         this.sptyp = 0;
         this.spart = 11;
      }

      if (this.sp == 6) {
         this.sptyp = 0;
         this.spart = 12;
      }

      if (this.sp == 34) {
         this.sptyp = 0;
         this.spart = 13;
      }

      if (this.sp == 33) {
         this.sptyp = 0;
         this.spart = 14;
      }

      if (this.sp == 11) {
         this.sptyp = 0;
         this.spart = 15;
      }

      if (this.sp == 8) {
         this.sptyp = 0;
         this.spart = 16;
      }

      if (this.sp == 9) {
         this.sptyp = 0;
         this.spart = 17;
      }

      if (this.sp == 15) {
         this.sptyp = 0;
         this.spart = 18;
      }

      if (this.sp == 12) {
         this.sptyp = 0;
         this.spart = 19;
      }

      if (this.sp == 46) {
         this.sptyp = 0;
         this.spart = 20;
      }

      if (this.sp == 47) {
         this.sptyp = 0;
         this.spart = 21;
      }

      if (this.sp == 48) {
         this.sptyp = 0;
         this.spart = 23;
      }

      if (this.sp == 49) {
         this.sptyp = 0;
         this.spart = 24;
      }

      if (this.sp == 50) {
         this.sptyp = 0;
         this.spart = 22;
      }

      if (this.sp == 51) {
         this.sptyp = 0;
         this.spart = 25;
      }

      if (this.sp == 16) {
         this.sptyp = 1;
         this.spart = 0;
      }

      if (this.sp == 18) {
         this.sptyp = 1;
         this.spart = 1;
      }

      if (this.sp == 19) {
         this.sptyp = 1;
         this.spart = 2;
      }

      if (this.sp == 22) {
         this.sptyp = 1;
         this.spart = 3;
      }

      if (this.sp == 17) {
         this.sptyp = 1;
         this.spart = 4;
      }

      if (this.sp == 21) {
         this.sptyp = 1;
         this.spart = 5;
      }

      if (this.sp == 20) {
         this.sptyp = 1;
         this.spart = 6;
      }

      if (this.sp == 39) {
         this.sptyp = 1;
         this.spart = 7;
      }

      if (this.sp == 42) {
         this.sptyp = 1;
         this.spart = 8;
      }

      if (this.sp == 40) {
         this.sptyp = 1;
         this.spart = 9;
      }

      if (this.sp == 23) {
         this.sptyp = 1;
         this.spart = 10;
      }

      if (this.sp == 25) {
         this.sptyp = 1;
         this.spart = 11;
      }

      if (this.sp == 24) {
         this.sptyp = 1;
         this.spart = 12;
      }

      if (this.sp == 43) {
         this.sptyp = 1;
         this.spart = 13;
      }

      if (this.sp == 45) {
         this.sptyp = 1;
         this.spart = 14;
      }

      if (this.sp == 26) {
         this.sptyp = 1;
         this.spart = 15;
      }

      if (this.sp == 27) {
         this.sptyp = 2;
         this.spart = 0;
      }

      if (this.sp == 28) {
         this.sptyp = 2;
         this.spart = 1;
      }

      if (this.sp == 41) {
         this.sptyp = 2;
         this.spart = 2;
      }

      if (this.sp == 44) {
         this.sptyp = 2;
         this.spart = 3;
      }

      if (this.sp == 52) {
         this.sptyp = 2;
         this.spart = 4;
      }

      if (this.sp == 53) {
         this.sptyp = 2;
         this.spart = 5;
      }

      if (this.sp == 30 || this.sp == 32 || this.sp == 54) {
         this.sptyp = 3;
         this.spart = 0;
      }

      if (this.sp == 31) {
         this.sptyp = 4;
         this.spart = 0;
      }

      if (this.sp == 55) {
         this.sptyp = 5;
         this.spart = 0;
      }

      if (this.sp == 56) {
         this.sptyp = 5;
         this.spart = 1;
      }

      if (this.sp == 57) {
         this.sptyp = 5;
         this.spart = 2;
      }

      if (this.sp == 58) {
         this.sptyp = 5;
         this.spart = 3;
      }

      if (this.sp == 59) {
         this.sptyp = 5;
         this.spart = 4;
      }

      if (this.sp == 60) {
         this.sptyp = 5;
         this.spart = 5;
      }

      if (this.sp == 61) {
         this.sptyp = 5;
         this.spart = 6;
      }

      if (this.sp == 62) {
         this.sptyp = 5;
         this.spart = 7;
      }

      if (this.sp == 63) {
         this.sptyp = 5;
         this.spart = 8;
      }

      if (this.sp == 64) {
         this.sptyp = 5;
         this.spart = 9;
      }

      if (this.sp == 65) {
         this.sptyp = 5;
         this.spart = 10;
      }

      if (this.sp == 66) {
         if (var1) {
            this.fgen = this.co[this.esp].srz;
         } else {
            this.fgen = 0;
         }

         this.pwd = this.co[this.esp].srx;
         this.phd = this.co[this.esp].sry;
         this.pgen = false;
         this.sptyp = 6;
      }

      if (this.sptyp == 0) {
         this.partroads();
         this.part.show();
      }

      if (this.sptyp == 1) {
         this.partramps();
         this.part.show();
      }

      if (this.sptyp == 2) {
         this.partobst();
         this.part.show();
      }

      if (this.sptyp == 5) {
         this.partrees();
         this.part.show();
      }

      this.ptyp.select(this.sptyp);
      this.part.select(this.spart);
   }

   public void partrees() {
      this.part.removeAll();
      this.part.add(this.rd, "Tree 1");
      this.part.add(this.rd, "Tree 2");
      this.part.add(this.rd, "Tree 3");
      this.part.add(this.rd, "Tree 4");
      this.part.add(this.rd, "Tree 5");
      this.part.add(this.rd, "Palm Tree 1");
      this.part.add(this.rd, "Palm Tree 2");
      this.part.add(this.rd, "Palm Tree 3");
      this.part.add(this.rd, "Cactus 1");
      this.part.add(this.rd, "Cactus 2");
      this.part.add(this.rd, "Cactus 3");
   }

   public void partroads() {
      this.part.removeAll();
      this.part.add(this.rd, "NormalRoad");
      this.part.add(this.rd, "NormalRoad Turn");
      this.part.add(this.rd, "NormalRoad End");
      this.part.add(this.rd, "NormalRoad TwistedLeft");
      this.part.add(this.rd, "NormalRoad TwistedRight");
      this.part.add(this.rd, "NormalRoad Edged");
      this.part.add(this.rd, "NormalRoad-Raised Ramp");
      this.part.add(this.rd, "NormalRoad Raised");
      this.part.add(this.rd, "Normal-Off-Road Blend");
      this.part.add(this.rd, "OffRoad");
      this.part.add(this.rd, "OffRoad Turn");
      this.part.add(this.rd, "OffRoad End");
      this.part.add(this.rd, "OffRoad BumpyGreen");
      this.part.add(this.rd, "OffRoad-BumpySides Start");
      this.part.add(this.rd, "OffRoad BumpySides");
      this.part.add(this.rd, "Off-Halfpipe-Road Blend");
      this.part.add(this.rd, "HalfpipeRoad");
      this.part.add(this.rd, "HalfpipeRoad Turn");
      this.part.add(this.rd, "HalfpipeRoad-Ramp Filler");
      this.part.add(this.rd, "Halfpipe-Normal-Road Blend");
      this.part.add(this.rd, "Rollercoaster Start/End");
      this.part.add(this.rd, "Rollercoaster Road1");
      this.part.add(this.rd, "Rollercoaster Road2");
      this.part.add(this.rd, "Rollercoaster Road3");
      this.part.add(this.rd, "Rollercoaster Road4");
      this.part.add(this.rd, "Rollercoaster Road5");
   }

   public void partramps() {
      this.part.removeAll();
      this.part.add(this.rd, "Basic Ramp");
      this.part.add(this.rd, "Two-Way Ramp");
      this.part.add(this.rd, "Two-Way High-Low Ramp");
      this.part.add(this.rd, "Small Ramp");
      this.part.add(this.rd, "Crash Ramp");
      this.part.add(this.rd, "Big-Takeoff Ramp");
      this.part.add(this.rd, "Landing Ramp");
      this.part.add(this.rd, "Tunnel Side Ramp");
      this.part.add(this.rd, "Speed Ramp");
      this.part.add(this.rd, "Launch Pad Ramp");
      this.part.add(this.rd, "Offroad Bump Ramp");
      this.part.add(this.rd, "Offroad Ramp");
      this.part.add(this.rd, "Offroad Big Ramp");
      this.part.add(this.rd, "Offroad Hill Ramp");
      this.part.add(this.rd, "Offroad Big Hill Ramp");
      this.part.add(this.rd, "Halfpipe");
   }

   public void partobst() {
      this.part.removeAll();
      this.part.add(this.rd, "Spiky Pillars");
      this.part.add(this.rd, "Rail Doorway");
      this.part.add(this.rd, "The Net");
      this.part.add(this.rd, "Bump Slide");
      this.part.add(this.rd, "Offroad Dirt-Pile 1");
      this.part.add(this.rd, "Offroad Dirt-Pile 2");
   }

   @Override
   public void init() {
      this.setBackground(new Color(0, 0, 0));
      this.offImage = this.createImage(800, 550);
      if (this.offImage != null) {
         this.rd = (Graphics2D)this.offImage.getGraphics();
      }

      this.rd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      this.setLayout(null);
      this.slstage.setFont(new Font("Arial", 1, 13));
      this.slstage.add(this.rd, "Select a Stage...         ");
      this.slstage.setForeground(new Color(63, 80, 110));
      this.slstage.setBackground(new Color(209, 217, 230));
      this.srch.setFont(new Font("Arial", 1, 12));
      this.srch.setBackground(new Color(255, 255, 255));
      this.srch.setForeground(new Color(0, 0, 0));
      this.strtyp.setFont(new Font("Arial", 1, 12));
      this.strtyp.add(this.rd, "NormalRoad");
      this.strtyp.add(this.rd, "OffRoad");
      this.strtyp.setBackground(new Color(63, 80, 110));
      this.strtyp.setForeground(new Color(209, 217, 230));
      this.ptyp.setFont(new Font("Arial", 1, 12));
      this.ptyp.add(this.rd, "Roads");
      this.ptyp.add(this.rd, "Ramps");
      this.ptyp.add(this.rd, "Obstacles");
      this.ptyp.add(this.rd, "Checkpoint");
      this.ptyp.add(this.rd, "Fixing Hoop");
      this.ptyp.add(this.rd, "Trees");
      this.ptyp.add(this.rd, "Ground Pile");
      this.ptyp.setBackground(new Color(63, 80, 110));
      this.ptyp.setForeground(new Color(209, 217, 230));
      this.part.setFont(new Font("Arial", 1, 12));
      this.part.add(this.rd, "Halfpipe-Normal-Road Blend");
      this.part.setBackground(new Color(63, 80, 110));
      this.part.setForeground(new Color(209, 217, 230));
      this.fixh.setFont(new Font("Arial", 1, 12));
      this.fixh.setBackground(new Color(255, 255, 255));
      this.fixh.setForeground(new Color(0, 0, 0));
      this.mgen.setFont(new Font("Arial", 1, 12));
      this.mgen.setBackground(new Color(255, 255, 255));
      this.mgen.setForeground(new Color(0, 0, 0));
      this.pfog.setFont(new Font("Arial", 1, 12));
      this.pfog.setBackground(new Color(225, 225, 225));
      this.pfog.setForeground(new Color(0, 0, 0));
      this.nlaps.setFont(new Font("Arial", 1, 12));

      for (int var1 = 0; var1 < 15; var1++) {
         this.nlaps.add(this.rd, " " + (var1 + 1) + " ");
      }

      this.nlaps.setBackground(new Color(63, 80, 110));
      this.nlaps.setForeground(new Color(209, 217, 230));
      this.tracks.setFont(new Font("Arial", 1, 12));
      this.tracks.add(this.rd, "Select MOD Track");
      this.tracks.setForeground(new Color(63, 80, 110));
      this.tracks.setBackground(new Color(209, 217, 230));
      this.witho.setFont(new Font("Arial", 1, 12));
      this.witho.add(this.rd, "With other cars");
      this.witho.add(this.rd, "Alone");
      this.witho.setBackground(new Color(63, 80, 110));
      this.witho.setForeground(new Color(209, 217, 230));
      this.tnick.setFont(new Font("Arial", 1, 13));
      this.tnick.setBackground(new Color(255, 255, 255));
      this.tnick.setForeground(new Color(0, 0, 0));
      this.tpass.setFont(new Font("Arial", 1, 13));
      this.tpass.setEchoCharacter('*');
      this.tpass.setBackground(new Color(255, 255, 255));
      this.tpass.setForeground(new Color(0, 0, 0));
      this.pubtyp.setFont(new Font("Arial", 1, 13));
      this.pubtyp.add(this.rd, "Private");
      this.pubtyp.add(this.rd, "Public");
      this.pubtyp.add(this.rd, "Super Public");
      this.pubtyp.setBackground(new Color(63, 80, 110));
      this.pubtyp.setForeground(new Color(209, 217, 230));
      this.pubitem.setFont(new Font("Arial", 1, 13));
      this.pubitem.add(this.rd, "Account Stages");
      this.pubitem.setBackground(new Color(209, 217, 230));
      this.pubitem.setForeground(new Color(63, 80, 110));
      this.add(this.tnick);
      this.add(this.tpass);
      this.add(this.srch);
      this.add(this.fixh);
      this.add(this.mgen);
      this.add(this.pfog);
      this.hidefields();
   }

   public void hidefields() {
      this.pubtyp.hide();
      this.pubitem.hide();
      this.tpass.hide();
      this.tnick.hide();
      this.witho.hide();
      this.strtyp.hide();
      this.srch.hide();
      this.slstage.hide();
      this.tracks.hide();
      this.nlaps.hide();
      this.pfog.hide();
      this.fixh.hide();
      this.mgen.hide();
      this.ptyp.hide();
      this.part.hide();
   }

   public void movefield(Component var1, int var2, int var3, int var4, int var5) {
      var2 += this.apx;
      var3 += this.apy;
      if (var1.getX() != var2 || var1.getY() != var3 || var1.getWidth() != var4 || var1.getHeight() != var5) {
         var1.setBounds(var2, var3, var4, var5);
      }
   }

   public void drawms() {
      boolean var1 = false;
      if (this.pubtyp.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         var1 = true;
      }

      if (this.pubitem.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         var1 = true;
      }

      if (this.slstage.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         var1 = true;
      }

      if (this.strtyp.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         var1 = true;
      }

      short var2 = 0;
      if (this.preop) {
         var2 = -1000;
      }

      if (this.part.draw(this.rd, this.xm, this.ym + var2, this.mousdr && !this.preop, 550, false)) {
         var1 = true;
      }

      if (this.ptyp.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         var1 = true;
         this.preop = true;
      } else {
         this.preop = false;
      }

      if (this.nlaps.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         var1 = true;
      }

      if (this.tracks.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         var1 = true;
      }

      if (this.witho.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         var1 = true;
      }

      if (var1) {
         this.mouses = 0;
      }
   }

   @Override
   public void start() {
      if (this.thredo == null) {
         this.thredo = new Thread(this);
      }

      this.thredo.start();
   }

   @Override
   public void stop() {
      this.exwist = true;
   }

   @Override
   public void paint(Graphics var1) {
      this.apx = this.getWidth() / 2 - 400;
      this.apy = this.getHeight() / 2 - 275;
      var1.drawImage(this.offImage, this.apx, this.apy, this);
   }

   @Override
   public void update(Graphics var1) {
      this.paint(var1);
   }

   @Override
   public boolean mouseUp(Event var1, int var2, int var3) {
      this.mousdr = false;
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      if (this.mouses == 1) {
         this.mouses = -1;
      }

      if (this.onbtgame) {
         Madness.game();
      }

      return false;
   }

   @Override
   public boolean mouseDown(Event var1, int var2, int var3) {
      this.mousdr = true;
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      this.mouses = 1;
      this.requestFocus();
      this.focuson = true;
      return false;
   }

   @Override
   public boolean mouseMove(Event var1, int var2, int var3) {
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      if (this.xm > 620 && this.xm < 774 && this.ym > 0 && this.ym < 23) {
         if (!this.onbtgame) {
            this.onbtgame = true;
            this.setCursor(new Cursor(12));
         }
      } else if (this.onbtgame) {
         this.onbtgame = false;
         this.setCursor(new Cursor(0));
      }

      return false;
   }

   @Override
   public boolean mouseDrag(Event var1, int var2, int var3) {
      this.mousdr = true;
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      return false;
   }

   @Override
   public boolean keyDown(Event var1, int var2) {
      if (this.focuson) {
         if (var2 == 42 || var2 == 10 || var2 == 56 || var2 == 119 || var2 == 87 || var2 == 43 || var2 == 61) {
            this.zoomi = true;
         }

         if (var2 == 47 || var2 == 8 || var2 == 50 || var2 == 115 || var2 == 83 || var2 == 45) {
            this.zoomo = true;
         }

         if (var2 == 1006) {
            this.left = true;
         }

         if (var2 == 1007) {
            this.right = true;
         }

         if (var2 == 1005) {
            this.down = true;
         }

         if (var2 == 1004) {
            this.up = true;
         }
      }

      return false;
   }

   @Override
   public boolean keyUp(Event var1, int var2) {
      if (var2 == 42 || var2 == 10 || var2 == 56 || var2 == 119 || var2 == 87 || var2 == 43 || var2 == 61) {
         this.zoomi = false;
      }

      if (var2 == 47 || var2 == 8 || var2 == 50 || var2 == 115 || var2 == 83 || var2 == 45) {
         this.zoomo = false;
      }

      if (var2 == 1006) {
         this.left = false;
      }

      if (var2 == 1007) {
         this.right = false;
      }

      if (var2 == 1005) {
         this.down = false;
      }

      if (var2 == 1004) {
         this.up = false;
      }

      return false;
   }

   public void loadbase() {
      String[] var1 = new String[]{
         "road",
         "froad",
         "twister2",
         "twister1",
         "turn",
         "offroad",
         "bumproad",
         "offturn",
         "nroad",
         "nturn",
         "roblend",
         "noblend",
         "rnblend",
         "roadend",
         "offroadend",
         "hpground",
         "ramp30",
         "cramp35",
         "dramp15",
         "dhilo15",
         "slide10",
         "takeoff",
         "sramp22",
         "offbump",
         "offramp",
         "sofframp",
         "halfpipe",
         "spikes",
         "rail",
         "thewall",
         "checkpoint",
         "fixpoint",
         "offcheckpoint",
         "sideoff",
         "bsideoff",
         "uprise",
         "riseroad",
         "sroad",
         "soffroad",
         "tside",
         "launchpad",
         "thenet",
         "speedramp",
         "offhill",
         "slider",
         "uphill",
         "roll1",
         "roll2",
         "roll3",
         "roll4",
         "roll5",
         "roll6",
         "opile1",
         "opile2",
         "aircheckpoint",
         "tree1",
         "tree2",
         "tree3",
         "tree4",
         "tree5",
         "tree6",
         "tree7",
         "tree8",
         "cac1",
         "cac2",
         "cac3"
      };

      try {
         File var2 = new File("" + Madness.fpath + "data/models.zip");
         ZipInputStream var3 = new ZipInputStream(new FileInputStream(var2));
         ZipEntry var4 = var3.getNextEntry();
         byte[] var5 = null;

         while (var4 != null) {
            int var6 = -1;

            for (int var7 = 0; var7 < 66; var7++) {
               if (var4.getName().startsWith(var1[var7])) {
                  var6 = var7;
               }
            }

            if (var6 != -1) {
               int var13 = (int)var4.getSize();
               var5 = new byte[var13];
               int var8 = 0;

               while (var13 > 0) {
                  int var9 = var3.read(var5, var8, var13);
                  var8 += var9;
                  var13 -= var9;
               }

               this.bco[var6] = new ContO(var5, this.m, this.t);

               for (int var10 = 0; var10 < this.bco[var6].npl; var10++) {
                  this.bco[var6].p[var10].loadprojf();
               }

               if (var6 == 31) {
                  this.bco[var6].elec = true;
               }
            }

            var4 = var3.getNextEntry();
         }

         var3.close();
         this.bco[66] = new ContO((int)(10000.0 * Math.random()), (int)this.pwd, (int)this.phd, this.m, this.t, 0, 0, 0);
      } catch (Exception var11) {
         JOptionPane.showMessageDialog(null, "Unable to load file 'data/models.zip'!\nError:\n" + var11, "Stage Maker", 1);
      }

      System.gc();
   }

   public void readstage(int param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 0000: aload 0
      // 0001: bipush 0
      // 0002: putfield StageMaker.errd I
      // 0005: aload 0
      // 0006: ldc ""
      // 0008: putfield StageMaker.trackname Ljava/lang/String;
      // 000b: aload 0
      // 000c: getfield StageMaker.t LTrackers;
      // 000f: bipush 0
      // 0010: putfield Trackers.nt I
      // 0013: aload 0
      // 0014: bipush 0
      // 0015: putfield StageMaker.nob I
      // 0018: aload 0
      // 0019: bipush 0
      // 001a: putfield StageMaker.xnob I
      // 001d: aload 0
      // 001e: getfield StageMaker.cp LCheckPoints;
      // 0021: bipush 0
      // 0022: putfield CheckPoints.n I
      // 0025: aload 0
      // 0026: getfield StageMaker.cp LCheckPoints;
      // 0029: bipush 0
      // 002a: putfield CheckPoints.nsp I
      // 002d: aload 0
      // 002e: getfield StageMaker.cp LCheckPoints;
      // 0031: bipush 0
      // 0032: putfield CheckPoints.fn I
      // 0035: aload 0
      // 0036: getfield StageMaker.cp LCheckPoints;
      // 0039: bipush 0
      // 003a: putfield CheckPoints.haltall Z
      // 003d: aload 0
      // 003e: getfield StageMaker.cp LCheckPoints;
      // 0041: bipush 0
      // 0042: putfield CheckPoints.wasted I
      // 0045: aload 0
      // 0046: getfield StageMaker.cp LCheckPoints;
      // 0049: bipush 0
      // 004a: putfield CheckPoints.catchfin I
      // 004d: aload 0
      // 004e: getfield StageMaker.m LMedium;
      // 0051: sipush 250
      // 0054: putfield Medium.ground I
      // 0057: aload 0
      // 0058: getfield StageMaker.m LMedium;
      // 005b: bipush 0
      // 005c: putfield Medium.lightson Z
      // 005f: iload 1
      // 0060: ifne 0081
      // 0063: aload 0
      // 0064: getfield StageMaker.m LMedium;
      // 0067: getfield Medium.snap [I
      // 006a: bipush 0
      // 006b: bipush 0
      // 006c: iastore
      // 006d: aload 0
      // 006e: getfield StageMaker.m LMedium;
      // 0071: getfield Medium.snap [I
      // 0074: bipush 1
      // 0075: bipush 0
      // 0076: iastore
      // 0077: aload 0
      // 0078: getfield StageMaker.m LMedium;
      // 007b: getfield Medium.snap [I
      // 007e: bipush 2
      // 007f: bipush 0
      // 0080: iastore
      // 0081: iload 1
      // 0082: bipush 3
      // 0083: if_icmpne 0092
      // 0086: aload 0
      // 0087: ldc ""
      // 0089: putfield StageMaker.tstage Ljava/lang/String;
      // 008c: aload 0
      // 008d: ldc ""
      // 008f: putfield StageMaker.bstage Ljava/lang/String;
      // 0092: aload 0
      // 0093: getfield StageMaker.bstage Ljava/lang/String;
      // 0096: astore 2
      // 0097: iload 1
      // 0098: bipush 1
      // 0099: if_icmpeq 00a1
      // 009c: iload 1
      // 009d: bipush 2
      // 009e: if_icmpne 00ca
      // 00a1: new java/lang/StringBuilder
      // 00a4: dup
      // 00a5: invokespecial java/lang/StringBuilder.<init> ()V
      // 00a8: ldc ""
      // 00aa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00ad: aload 0
      // 00ae: getfield StageMaker.tstage Ljava/lang/String;
      // 00b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00b4: ldc_w "\r\n"
      // 00b7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00ba: aload 0
      // 00bb: getfield StageMaker.bstage Ljava/lang/String;
      // 00be: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00c1: ldc ""
      // 00c3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00c6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 00c9: astore 2
      // 00ca: bipush 0
      // 00cb: istore 3
      // 00cc: bipush 100
      // 00ce: istore 4
      // 00d0: bipush 0
      // 00d1: istore 5
      // 00d3: bipush 100
      // 00d5: istore 6
      // 00d7: bipush 1
      // 00d8: istore 7
      // 00da: bipush 1
      // 00db: istore 8
      // 00dd: ldc ""
      // 00df: astore 10
      // 00e1: aconst_null
      // 00e2: astore 11
      // 00e4: iload 1
      // 00e5: bipush 3
      // 00e6: if_icmpne 0134
      // 00e9: new java/io/File
      // 00ec: dup
      // 00ed: new java/lang/StringBuilder
      // 00f0: dup
      // 00f1: invokespecial java/lang/StringBuilder.<init> ()V
      // 00f4: ldc ""
      // 00f6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00f9: getstatic Madness.fpath Ljava/lang/String;
      // 00fc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 00ff: ldc_w "mystages/"
      // 0102: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0105: aload 0
      // 0106: getfield StageMaker.stagename Ljava/lang/String;
      // 0109: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 010c: ldc_w ".txt"
      // 010f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0112: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0115: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 0118: astore 12
      // 011a: new java/io/DataInputStream
      // 011d: dup
      // 011e: new java/io/FileInputStream
      // 0121: dup
      // 0122: aload 12
      // 0124: invokespecial java/io/FileInputStream.<init> (Ljava/io/File;)V
      // 0127: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 012a: astore 11
      // 012c: aload 0
      // 012d: bipush 0
      // 012e: putfield StageMaker.nundo I
      // 0131: goto 0148
      // 0134: new java/io/DataInputStream
      // 0137: dup
      // 0138: new java/io/ByteArrayInputStream
      // 013b: dup
      // 013c: aload 2
      // 013d: invokevirtual java/lang/String.getBytes ()[B
      // 0140: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 0143: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 0146: astore 11
      // 0148: aload 11
      // 014a: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // 014d: dup
      // 014e: astore 9
      // 0150: ifnull 10f0
      // 0153: new java/lang/StringBuilder
      // 0156: dup
      // 0157: invokespecial java/lang/StringBuilder.<init> ()V
      // 015a: ldc ""
      // 015c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 015f: aload 9
      // 0161: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 0164: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0167: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 016a: astore 10
      // 016c: aload 10
      // 016e: ldc_w "sky"
      // 0171: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0174: ifeq 01ea
      // 0177: aload 0
      // 0178: getfield StageMaker.csky [I
      // 017b: bipush 0
      // 017c: aload 0
      // 017d: ldc_w "sky"
      // 0180: aload 10
      // 0182: bipush 0
      // 0183: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0186: iastore
      // 0187: aload 0
      // 0188: getfield StageMaker.csky [I
      // 018b: bipush 1
      // 018c: aload 0
      // 018d: ldc_w "sky"
      // 0190: aload 10
      // 0192: bipush 1
      // 0193: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0196: iastore
      // 0197: aload 0
      // 0198: getfield StageMaker.csky [I
      // 019b: bipush 2
      // 019c: aload 0
      // 019d: ldc_w "sky"
      // 01a0: aload 10
      // 01a2: bipush 2
      // 01a3: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 01a6: iastore
      // 01a7: aload 0
      // 01a8: getfield StageMaker.m LMedium;
      // 01ab: aload 0
      // 01ac: getfield StageMaker.csky [I
      // 01af: bipush 0
      // 01b0: iaload
      // 01b1: aload 0
      // 01b2: getfield StageMaker.csky [I
      // 01b5: bipush 1
      // 01b6: iaload
      // 01b7: aload 0
      // 01b8: getfield StageMaker.csky [I
      // 01bb: bipush 2
      // 01bc: iaload
      // 01bd: invokevirtual Medium.setsky (III)V
      // 01c0: iload 1
      // 01c1: bipush 3
      // 01c2: if_icmpne 01ea
      // 01c5: new java/lang/StringBuilder
      // 01c8: dup
      // 01c9: invokespecial java/lang/StringBuilder.<init> ()V
      // 01cc: aload 0
      // 01cd: dup_x1
      // 01ce: getfield StageMaker.tstage Ljava/lang/String;
      // 01d1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 01d4: ldc ""
      // 01d6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 01d9: aload 10
      // 01db: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 01de: ldc_w "\r\n"
      // 01e1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 01e4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 01e7: putfield StageMaker.tstage Ljava/lang/String;
      // 01ea: aload 10
      // 01ec: ldc_w "ground"
      // 01ef: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 01f2: ifeq 0268
      // 01f5: aload 0
      // 01f6: getfield StageMaker.cgrnd [I
      // 01f9: bipush 0
      // 01fa: aload 0
      // 01fb: ldc_w "ground"
      // 01fe: aload 10
      // 0200: bipush 0
      // 0201: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0204: iastore
      // 0205: aload 0
      // 0206: getfield StageMaker.cgrnd [I
      // 0209: bipush 1
      // 020a: aload 0
      // 020b: ldc_w "ground"
      // 020e: aload 10
      // 0210: bipush 1
      // 0211: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0214: iastore
      // 0215: aload 0
      // 0216: getfield StageMaker.cgrnd [I
      // 0219: bipush 2
      // 021a: aload 0
      // 021b: ldc_w "ground"
      // 021e: aload 10
      // 0220: bipush 2
      // 0221: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0224: iastore
      // 0225: aload 0
      // 0226: getfield StageMaker.m LMedium;
      // 0229: aload 0
      // 022a: getfield StageMaker.cgrnd [I
      // 022d: bipush 0
      // 022e: iaload
      // 022f: aload 0
      // 0230: getfield StageMaker.cgrnd [I
      // 0233: bipush 1
      // 0234: iaload
      // 0235: aload 0
      // 0236: getfield StageMaker.cgrnd [I
      // 0239: bipush 2
      // 023a: iaload
      // 023b: invokevirtual Medium.setgrnd (III)V
      // 023e: iload 1
      // 023f: bipush 3
      // 0240: if_icmpne 0268
      // 0243: new java/lang/StringBuilder
      // 0246: dup
      // 0247: invokespecial java/lang/StringBuilder.<init> ()V
      // 024a: aload 0
      // 024b: dup_x1
      // 024c: getfield StageMaker.tstage Ljava/lang/String;
      // 024f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0252: ldc ""
      // 0254: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0257: aload 10
      // 0259: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 025c: ldc_w "\r\n"
      // 025f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0262: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0265: putfield StageMaker.tstage Ljava/lang/String;
      // 0268: aload 10
      // 026a: ldc_w "polys"
      // 026d: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0270: ifeq 02c2
      // 0273: aload 0
      // 0274: getfield StageMaker.m LMedium;
      // 0277: aload 0
      // 0278: ldc_w "polys"
      // 027b: aload 10
      // 027d: bipush 0
      // 027e: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0281: aload 0
      // 0282: ldc_w "polys"
      // 0285: aload 10
      // 0287: bipush 1
      // 0288: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 028b: aload 0
      // 028c: ldc_w "polys"
      // 028f: aload 10
      // 0291: bipush 2
      // 0292: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0295: invokevirtual Medium.setpolys (III)V
      // 0298: iload 1
      // 0299: bipush 3
      // 029a: if_icmpne 02c2
      // 029d: new java/lang/StringBuilder
      // 02a0: dup
      // 02a1: invokespecial java/lang/StringBuilder.<init> ()V
      // 02a4: aload 0
      // 02a5: dup_x1
      // 02a6: getfield StageMaker.tstage Ljava/lang/String;
      // 02a9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02ac: ldc ""
      // 02ae: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02b1: aload 10
      // 02b3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02b6: ldc_w "\r\n"
      // 02b9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02bc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 02bf: putfield StageMaker.tstage Ljava/lang/String;
      // 02c2: aload 10
      // 02c4: ldc_w "fog"
      // 02c7: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 02ca: ifeq 0340
      // 02cd: aload 0
      // 02ce: getfield StageMaker.cfade [I
      // 02d1: bipush 0
      // 02d2: aload 0
      // 02d3: ldc_w "fog"
      // 02d6: aload 10
      // 02d8: bipush 0
      // 02d9: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 02dc: iastore
      // 02dd: aload 0
      // 02de: getfield StageMaker.cfade [I
      // 02e1: bipush 1
      // 02e2: aload 0
      // 02e3: ldc_w "fog"
      // 02e6: aload 10
      // 02e8: bipush 1
      // 02e9: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 02ec: iastore
      // 02ed: aload 0
      // 02ee: getfield StageMaker.cfade [I
      // 02f1: bipush 2
      // 02f2: aload 0
      // 02f3: ldc_w "fog"
      // 02f6: aload 10
      // 02f8: bipush 2
      // 02f9: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 02fc: iastore
      // 02fd: aload 0
      // 02fe: getfield StageMaker.m LMedium;
      // 0301: aload 0
      // 0302: getfield StageMaker.cfade [I
      // 0305: bipush 0
      // 0306: iaload
      // 0307: aload 0
      // 0308: getfield StageMaker.cfade [I
      // 030b: bipush 1
      // 030c: iaload
      // 030d: aload 0
      // 030e: getfield StageMaker.cfade [I
      // 0311: bipush 2
      // 0312: iaload
      // 0313: invokevirtual Medium.setfade (III)V
      // 0316: iload 1
      // 0317: bipush 3
      // 0318: if_icmpne 0340
      // 031b: new java/lang/StringBuilder
      // 031e: dup
      // 031f: invokespecial java/lang/StringBuilder.<init> ()V
      // 0322: aload 0
      // 0323: dup_x1
      // 0324: getfield StageMaker.tstage Ljava/lang/String;
      // 0327: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 032a: ldc ""
      // 032c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 032f: aload 10
      // 0331: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0334: ldc_w "\r\n"
      // 0337: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 033a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 033d: putfield StageMaker.tstage Ljava/lang/String;
      // 0340: aload 10
      // 0342: ldc_w "texture"
      // 0345: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0348: ifeq 03d4
      // 034b: aload 0
      // 034c: getfield StageMaker.texture [I
      // 034f: bipush 0
      // 0350: aload 0
      // 0351: ldc_w "texture"
      // 0354: aload 10
      // 0356: bipush 0
      // 0357: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 035a: iastore
      // 035b: aload 0
      // 035c: getfield StageMaker.texture [I
      // 035f: bipush 1
      // 0360: aload 0
      // 0361: ldc_w "texture"
      // 0364: aload 10
      // 0366: bipush 1
      // 0367: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 036a: iastore
      // 036b: aload 0
      // 036c: getfield StageMaker.texture [I
      // 036f: bipush 2
      // 0370: aload 0
      // 0371: ldc_w "texture"
      // 0374: aload 10
      // 0376: bipush 2
      // 0377: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 037a: iastore
      // 037b: aload 0
      // 037c: getfield StageMaker.texture [I
      // 037f: bipush 3
      // 0380: aload 0
      // 0381: ldc_w "texture"
      // 0384: aload 10
      // 0386: bipush 3
      // 0387: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 038a: iastore
      // 038b: aload 0
      // 038c: getfield StageMaker.m LMedium;
      // 038f: aload 0
      // 0390: getfield StageMaker.texture [I
      // 0393: bipush 0
      // 0394: iaload
      // 0395: aload 0
      // 0396: getfield StageMaker.texture [I
      // 0399: bipush 1
      // 039a: iaload
      // 039b: aload 0
      // 039c: getfield StageMaker.texture [I
      // 039f: bipush 2
      // 03a0: iaload
      // 03a1: aload 0
      // 03a2: getfield StageMaker.texture [I
      // 03a5: bipush 3
      // 03a6: iaload
      // 03a7: invokevirtual Medium.setexture (IIII)V
      // 03aa: iload 1
      // 03ab: bipush 3
      // 03ac: if_icmpne 03d4
      // 03af: new java/lang/StringBuilder
      // 03b2: dup
      // 03b3: invokespecial java/lang/StringBuilder.<init> ()V
      // 03b6: aload 0
      // 03b7: dup_x1
      // 03b8: getfield StageMaker.tstage Ljava/lang/String;
      // 03bb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 03be: ldc ""
      // 03c0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 03c3: aload 10
      // 03c5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 03c8: ldc_w "\r\n"
      // 03cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 03ce: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 03d1: putfield StageMaker.tstage Ljava/lang/String;
      // 03d4: aload 10
      // 03d6: ldc_w "clouds"
      // 03d9: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 03dc: ifeq 047e
      // 03df: aload 0
      // 03e0: getfield StageMaker.cldd [I
      // 03e3: bipush 0
      // 03e4: aload 0
      // 03e5: ldc_w "clouds"
      // 03e8: aload 10
      // 03ea: bipush 0
      // 03eb: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 03ee: iastore
      // 03ef: aload 0
      // 03f0: getfield StageMaker.cldd [I
      // 03f3: bipush 1
      // 03f4: aload 0
      // 03f5: ldc_w "clouds"
      // 03f8: aload 10
      // 03fa: bipush 1
      // 03fb: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 03fe: iastore
      // 03ff: aload 0
      // 0400: getfield StageMaker.cldd [I
      // 0403: bipush 2
      // 0404: aload 0
      // 0405: ldc_w "clouds"
      // 0408: aload 10
      // 040a: bipush 2
      // 040b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 040e: iastore
      // 040f: aload 0
      // 0410: getfield StageMaker.cldd [I
      // 0413: bipush 3
      // 0414: aload 0
      // 0415: ldc_w "clouds"
      // 0418: aload 10
      // 041a: bipush 3
      // 041b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 041e: iastore
      // 041f: aload 0
      // 0420: getfield StageMaker.cldd [I
      // 0423: bipush 4
      // 0424: aload 0
      // 0425: ldc_w "clouds"
      // 0428: aload 10
      // 042a: bipush 4
      // 042b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 042e: iastore
      // 042f: aload 0
      // 0430: getfield StageMaker.m LMedium;
      // 0433: aload 0
      // 0434: getfield StageMaker.cldd [I
      // 0437: bipush 0
      // 0438: iaload
      // 0439: aload 0
      // 043a: getfield StageMaker.cldd [I
      // 043d: bipush 1
      // 043e: iaload
      // 043f: aload 0
      // 0440: getfield StageMaker.cldd [I
      // 0443: bipush 2
      // 0444: iaload
      // 0445: aload 0
      // 0446: getfield StageMaker.cldd [I
      // 0449: bipush 3
      // 044a: iaload
      // 044b: aload 0
      // 044c: getfield StageMaker.cldd [I
      // 044f: bipush 4
      // 0450: iaload
      // 0451: invokevirtual Medium.setcloads (IIIII)V
      // 0454: iload 1
      // 0455: bipush 3
      // 0456: if_icmpne 047e
      // 0459: new java/lang/StringBuilder
      // 045c: dup
      // 045d: invokespecial java/lang/StringBuilder.<init> ()V
      // 0460: aload 0
      // 0461: dup_x1
      // 0462: getfield StageMaker.tstage Ljava/lang/String;
      // 0465: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0468: ldc ""
      // 046a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 046d: aload 10
      // 046f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0472: ldc_w "\r\n"
      // 0475: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0478: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 047b: putfield StageMaker.tstage Ljava/lang/String;
      // 047e: iload 1
      // 047f: bipush 2
      // 0480: if_icmpeq 04dd
      // 0483: aload 10
      // 0485: ldc_w "snap"
      // 0488: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 048b: ifeq 04dd
      // 048e: aload 0
      // 048f: getfield StageMaker.m LMedium;
      // 0492: aload 0
      // 0493: ldc_w "snap"
      // 0496: aload 10
      // 0498: bipush 0
      // 0499: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 049c: aload 0
      // 049d: ldc_w "snap"
      // 04a0: aload 10
      // 04a2: bipush 1
      // 04a3: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 04a6: aload 0
      // 04a7: ldc_w "snap"
      // 04aa: aload 10
      // 04ac: bipush 2
      // 04ad: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 04b0: invokevirtual Medium.setsnap (III)V
      // 04b3: iload 1
      // 04b4: bipush 3
      // 04b5: if_icmpne 04dd
      // 04b8: new java/lang/StringBuilder
      // 04bb: dup
      // 04bc: invokespecial java/lang/StringBuilder.<init> ()V
      // 04bf: aload 0
      // 04c0: dup_x1
      // 04c1: getfield StageMaker.tstage Ljava/lang/String;
      // 04c4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 04c7: ldc ""
      // 04c9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 04cc: aload 10
      // 04ce: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 04d1: ldc_w "\r\n"
      // 04d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 04d7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 04da: putfield StageMaker.tstage Ljava/lang/String;
      // 04dd: aload 10
      // 04df: ldc_w "density"
      // 04e2: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 04e5: ifeq 0551
      // 04e8: aload 0
      // 04e9: getfield StageMaker.m LMedium;
      // 04ec: aload 0
      // 04ed: ldc_w "density"
      // 04f0: aload 10
      // 04f2: bipush 0
      // 04f3: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 04f6: bipush 1
      // 04f7: iadd
      // 04f8: bipush 2
      // 04f9: imul
      // 04fa: bipush 1
      // 04fb: isub
      // 04fc: putfield Medium.fogd I
      // 04ff: aload 0
      // 0500: getfield StageMaker.m LMedium;
      // 0503: getfield Medium.fogd I
      // 0506: bipush 1
      // 0507: if_icmpge 0512
      // 050a: aload 0
      // 050b: getfield StageMaker.m LMedium;
      // 050e: bipush 1
      // 050f: putfield Medium.fogd I
      // 0512: aload 0
      // 0513: getfield StageMaker.m LMedium;
      // 0516: getfield Medium.fogd I
      // 0519: bipush 30
      // 051b: if_icmple 0527
      // 051e: aload 0
      // 051f: getfield StageMaker.m LMedium;
      // 0522: bipush 30
      // 0524: putfield Medium.fogd I
      // 0527: iload 1
      // 0528: bipush 3
      // 0529: if_icmpne 0551
      // 052c: new java/lang/StringBuilder
      // 052f: dup
      // 0530: invokespecial java/lang/StringBuilder.<init> ()V
      // 0533: aload 0
      // 0534: dup_x1
      // 0535: getfield StageMaker.tstage Ljava/lang/String;
      // 0538: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 053b: ldc ""
      // 053d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0540: aload 10
      // 0542: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0545: ldc_w "\r\n"
      // 0548: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 054b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 054e: putfield StageMaker.tstage Ljava/lang/String;
      // 0551: aload 10
      // 0553: ldc_w "mountains"
      // 0556: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0559: ifeq 0597
      // 055c: aload 0
      // 055d: getfield StageMaker.m LMedium;
      // 0560: aload 0
      // 0561: ldc_w "mountains"
      // 0564: aload 10
      // 0566: bipush 0
      // 0567: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 056a: putfield Medium.mgen I
      // 056d: iload 1
      // 056e: bipush 3
      // 056f: if_icmpne 0597
      // 0572: new java/lang/StringBuilder
      // 0575: dup
      // 0576: invokespecial java/lang/StringBuilder.<init> ()V
      // 0579: aload 0
      // 057a: dup_x1
      // 057b: getfield StageMaker.tstage Ljava/lang/String;
      // 057e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0581: ldc ""
      // 0583: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0586: aload 10
      // 0588: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 058b: ldc_w "\r\n"
      // 058e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0591: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0594: putfield StageMaker.tstage Ljava/lang/String;
      // 0597: aload 10
      // 0599: ldc_w "fadefrom"
      // 059c: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 059f: ifeq 05ea
      // 05a2: aload 0
      // 05a3: getfield StageMaker.m LMedium;
      // 05a6: aload 0
      // 05a7: ldc_w "fadefrom"
      // 05aa: aload 10
      // 05ac: bipush 0
      // 05ad: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 05b0: invokevirtual Medium.fadfrom (I)V
      // 05b3: aload 0
      // 05b4: aload 0
      // 05b5: getfield StageMaker.m LMedium;
      // 05b8: getfield Medium.fade [I
      // 05bb: bipush 0
      // 05bc: iaload
      // 05bd: putfield StageMaker.origfade I
      // 05c0: iload 1
      // 05c1: bipush 3
      // 05c2: if_icmpne 05ea
      // 05c5: new java/lang/StringBuilder
      // 05c8: dup
      // 05c9: invokespecial java/lang/StringBuilder.<init> ()V
      // 05cc: aload 0
      // 05cd: dup_x1
      // 05ce: getfield StageMaker.tstage Ljava/lang/String;
      // 05d1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 05d4: ldc ""
      // 05d6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 05d9: aload 10
      // 05db: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 05de: ldc_w "\r\n"
      // 05e1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 05e4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 05e7: putfield StageMaker.tstage Ljava/lang/String;
      // 05ea: aload 10
      // 05ec: ldc_w "lightson"
      // 05ef: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 05f2: ifeq 0627
      // 05f5: aload 0
      // 05f6: getfield StageMaker.m LMedium;
      // 05f9: bipush 1
      // 05fa: putfield Medium.lightson Z
      // 05fd: iload 1
      // 05fe: bipush 3
      // 05ff: if_icmpne 0627
      // 0602: new java/lang/StringBuilder
      // 0605: dup
      // 0606: invokespecial java/lang/StringBuilder.<init> ()V
      // 0609: aload 0
      // 060a: dup_x1
      // 060b: getfield StageMaker.tstage Ljava/lang/String;
      // 060e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0611: ldc ""
      // 0613: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0616: aload 10
      // 0618: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 061b: ldc_w "\r\n"
      // 061e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0621: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0624: putfield StageMaker.tstage Ljava/lang/String;
      // 0627: aload 10
      // 0629: ldc_w "nlaps"
      // 062c: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 062f: ifeq 0695
      // 0632: aload 0
      // 0633: getfield StageMaker.cp LCheckPoints;
      // 0636: aload 0
      // 0637: ldc_w "nlaps"
      // 063a: aload 10
      // 063c: bipush 0
      // 063d: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0640: putfield CheckPoints.nlaps I
      // 0643: aload 0
      // 0644: getfield StageMaker.cp LCheckPoints;
      // 0647: getfield CheckPoints.nlaps I
      // 064a: bipush 1
      // 064b: if_icmpge 0656
      // 064e: aload 0
      // 064f: getfield StageMaker.cp LCheckPoints;
      // 0652: bipush 1
      // 0653: putfield CheckPoints.nlaps I
      // 0656: aload 0
      // 0657: getfield StageMaker.cp LCheckPoints;
      // 065a: getfield CheckPoints.nlaps I
      // 065d: bipush 15
      // 065f: if_icmple 066b
      // 0662: aload 0
      // 0663: getfield StageMaker.cp LCheckPoints;
      // 0666: bipush 15
      // 0668: putfield CheckPoints.nlaps I
      // 066b: iload 1
      // 066c: bipush 3
      // 066d: if_icmpne 0695
      // 0670: new java/lang/StringBuilder
      // 0673: dup
      // 0674: invokespecial java/lang/StringBuilder.<init> ()V
      // 0677: aload 0
      // 0678: dup_x1
      // 0679: getfield StageMaker.tstage Ljava/lang/String;
      // 067c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 067f: ldc ""
      // 0681: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0684: aload 10
      // 0686: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0689: ldc_w "\r\n"
      // 068c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 068f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0692: putfield StageMaker.tstage Ljava/lang/String;
      // 0695: aload 10
      // 0697: ldc_w "soundtrack"
      // 069a: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 069d: ifeq 06f4
      // 06a0: aload 0
      // 06a1: aload 0
      // 06a2: ldc_w "soundtrack"
      // 06a5: aload 10
      // 06a7: bipush 0
      // 06a8: invokevirtual StageMaker.getstring (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // 06ab: putfield StageMaker.trackname Ljava/lang/String;
      // 06ae: aload 0
      // 06af: aload 0
      // 06b0: ldc_w "soundtrack"
      // 06b3: aload 10
      // 06b5: bipush 1
      // 06b6: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 06b9: putfield StageMaker.trackvol I
      // 06bc: aload 0
      // 06bd: aload 0
      // 06be: ldc_w "soundtrack"
      // 06c1: aload 10
      // 06c3: bipush 2
      // 06c4: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 06c7: putfield StageMaker.tracksize I
      // 06ca: iload 1
      // 06cb: bipush 3
      // 06cc: if_icmpne 06f4
      // 06cf: new java/lang/StringBuilder
      // 06d2: dup
      // 06d3: invokespecial java/lang/StringBuilder.<init> ()V
      // 06d6: aload 0
      // 06d7: dup_x1
      // 06d8: getfield StageMaker.tstage Ljava/lang/String;
      // 06db: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 06de: ldc ""
      // 06e0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 06e3: aload 10
      // 06e5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 06e8: ldc_w "\r\n"
      // 06eb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 06ee: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 06f1: putfield StageMaker.tstage Ljava/lang/String;
      // 06f4: aload 10
      // 06f6: ldc_w "set"
      // 06f9: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 06fc: ifeq 08e0
      // 06ff: aload 0
      // 0700: ldc_w "set"
      // 0703: aload 10
      // 0705: bipush 0
      // 0706: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0709: istore 12
      // 070b: iload 12
      // 070d: bipush 10
      // 070f: if_icmplt 0721
      // 0712: iload 12
      // 0714: bipush 25
      // 0716: if_icmpgt 0721
      // 0719: aload 0
      // 071a: getfield StageMaker.m LMedium;
      // 071d: bipush 1
      // 071e: putfield Medium.loadnew Z
      // 0721: iinc 12 -10
      // 0724: aload 0
      // 0725: getfield StageMaker.co [LContO;
      // 0728: aload 0
      // 0729: getfield StageMaker.nob I
      // 072c: new ContO
      // 072f: dup
      // 0730: aload 0
      // 0731: getfield StageMaker.bco [LContO;
      // 0734: iload 12
      // 0736: aaload
      // 0737: aload 0
      // 0738: ldc_w "set"
      // 073b: aload 10
      // 073d: bipush 1
      // 073e: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0741: aload 0
      // 0742: getfield StageMaker.m LMedium;
      // 0745: getfield Medium.ground I
      // 0748: aload 0
      // 0749: getfield StageMaker.bco [LContO;
      // 074c: iload 12
      // 074e: aaload
      // 074f: getfield ContO.grat I
      // 0752: isub
      // 0753: aload 0
      // 0754: ldc_w "set"
      // 0757: aload 10
      // 0759: bipush 2
      // 075a: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 075d: aload 0
      // 075e: ldc_w "set"
      // 0761: aload 10
      // 0763: bipush 3
      // 0764: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0767: invokespecial ContO.<init> (LContO;IIII)V
      // 076a: aastore
      // 076b: aload 0
      // 076c: getfield StageMaker.co [LContO;
      // 076f: aload 0
      // 0770: getfield StageMaker.nob I
      // 0773: aaload
      // 0774: aload 0
      // 0775: ldc_w "set"
      // 0778: aload 10
      // 077a: bipush 3
      // 077b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 077e: putfield ContO.roofat I
      // 0781: aload 0
      // 0782: getfield StageMaker.co [LContO;
      // 0785: aload 0
      // 0786: getfield StageMaker.nob I
      // 0789: aaload
      // 078a: iload 12
      // 078c: putfield ContO.colok I
      // 078f: aload 10
      // 0791: ldc_w ")p"
      // 0794: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 0797: bipush -1
      // 0798: if_icmpeq 086d
      // 079b: aload 0
      // 079c: getfield StageMaker.cp LCheckPoints;
      // 079f: getfield CheckPoints.x [I
      // 07a2: aload 0
      // 07a3: getfield StageMaker.cp LCheckPoints;
      // 07a6: getfield CheckPoints.n I
      // 07a9: aload 0
      // 07aa: ldc_w "chk"
      // 07ad: aload 10
      // 07af: bipush 1
      // 07b0: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 07b3: iastore
      // 07b4: aload 0
      // 07b5: getfield StageMaker.cp LCheckPoints;
      // 07b8: getfield CheckPoints.z [I
      // 07bb: aload 0
      // 07bc: getfield StageMaker.cp LCheckPoints;
      // 07bf: getfield CheckPoints.n I
      // 07c2: aload 0
      // 07c3: ldc_w "chk"
      // 07c6: aload 10
      // 07c8: bipush 2
      // 07c9: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 07cc: iastore
      // 07cd: aload 0
      // 07ce: getfield StageMaker.cp LCheckPoints;
      // 07d1: getfield CheckPoints.y [I
      // 07d4: aload 0
      // 07d5: getfield StageMaker.cp LCheckPoints;
      // 07d8: getfield CheckPoints.n I
      // 07db: bipush 0
      // 07dc: iastore
      // 07dd: aload 0
      // 07de: getfield StageMaker.cp LCheckPoints;
      // 07e1: getfield CheckPoints.typ [I
      // 07e4: aload 0
      // 07e5: getfield StageMaker.cp LCheckPoints;
      // 07e8: getfield CheckPoints.n I
      // 07eb: bipush 0
      // 07ec: iastore
      // 07ed: aload 10
      // 07ef: ldc_w ")pt"
      // 07f2: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 07f5: bipush -1
      // 07f6: if_icmpeq 0809
      // 07f9: aload 0
      // 07fa: getfield StageMaker.cp LCheckPoints;
      // 07fd: getfield CheckPoints.typ [I
      // 0800: aload 0
      // 0801: getfield StageMaker.cp LCheckPoints;
      // 0804: getfield CheckPoints.n I
      // 0807: bipush -1
      // 0808: iastore
      // 0809: aload 10
      // 080b: ldc_w ")pr"
      // 080e: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 0811: bipush -1
      // 0812: if_icmpeq 0826
      // 0815: aload 0
      // 0816: getfield StageMaker.cp LCheckPoints;
      // 0819: getfield CheckPoints.typ [I
      // 081c: aload 0
      // 081d: getfield StageMaker.cp LCheckPoints;
      // 0820: getfield CheckPoints.n I
      // 0823: bipush -2
      // 0825: iastore
      // 0826: aload 10
      // 0828: ldc_w ")po"
      // 082b: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 082e: bipush -1
      // 082f: if_icmpeq 0843
      // 0832: aload 0
      // 0833: getfield StageMaker.cp LCheckPoints;
      // 0836: getfield CheckPoints.typ [I
      // 0839: aload 0
      // 083a: getfield StageMaker.cp LCheckPoints;
      // 083d: getfield CheckPoints.n I
      // 0840: bipush -3
      // 0842: iastore
      // 0843: aload 10
      // 0845: ldc_w ")ph"
      // 0848: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 084b: bipush -1
      // 084c: if_icmpeq 0860
      // 084f: aload 0
      // 0850: getfield StageMaker.cp LCheckPoints;
      // 0853: getfield CheckPoints.typ [I
      // 0856: aload 0
      // 0857: getfield StageMaker.cp LCheckPoints;
      // 085a: getfield CheckPoints.n I
      // 085d: bipush -4
      // 085f: iastore
      // 0860: aload 0
      // 0861: getfield StageMaker.cp LCheckPoints;
      // 0864: dup
      // 0865: getfield CheckPoints.n I
      // 0868: bipush 1
      // 0869: iadd
      // 086a: putfield CheckPoints.n I
      // 086d: aload 0
      // 086e: dup
      // 086f: getfield StageMaker.xnob I
      // 0872: bipush 1
      // 0873: iadd
      // 0874: putfield StageMaker.xnob I
      // 0877: aload 0
      // 0878: dup
      // 0879: getfield StageMaker.nob I
      // 087c: bipush 1
      // 087d: iadd
      // 087e: putfield StageMaker.nob I
      // 0881: iload 1
      // 0882: bipush 3
      // 0883: if_icmpne 08ce
      // 0886: iload 8
      // 0888: ifeq 08a9
      // 088b: new java/lang/StringBuilder
      // 088e: dup
      // 088f: invokespecial java/lang/StringBuilder.<init> ()V
      // 0892: aload 0
      // 0893: dup_x1
      // 0894: getfield StageMaker.bstage Ljava/lang/String;
      // 0897: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 089a: ldc_w "\r\n"
      // 089d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08a0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 08a3: putfield StageMaker.bstage Ljava/lang/String;
      // 08a6: bipush 0
      // 08a7: istore 8
      // 08a9: new java/lang/StringBuilder
      // 08ac: dup
      // 08ad: invokespecial java/lang/StringBuilder.<init> ()V
      // 08b0: aload 0
      // 08b1: dup_x1
      // 08b2: getfield StageMaker.bstage Ljava/lang/String;
      // 08b5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08b8: ldc ""
      // 08ba: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08bd: aload 10
      // 08bf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08c2: ldc_w "\r\n"
      // 08c5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08c8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 08cb: putfield StageMaker.bstage Ljava/lang/String;
      // 08ce: aload 0
      // 08cf: getfield StageMaker.m LMedium;
      // 08d2: getfield Medium.loadnew Z
      // 08d5: ifeq 08e0
      // 08d8: aload 0
      // 08d9: getfield StageMaker.m LMedium;
      // 08dc: bipush 0
      // 08dd: putfield Medium.loadnew Z
      // 08e0: aload 10
      // 08e2: ldc_w "chk"
      // 08e5: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 08e8: ifeq 0aae
      // 08eb: aload 0
      // 08ec: ldc_w "chk"
      // 08ef: aload 10
      // 08f1: bipush 0
      // 08f2: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 08f5: istore 12
      // 08f7: iinc 12 -10
      // 08fa: aload 0
      // 08fb: getfield StageMaker.m LMedium;
      // 08fe: getfield Medium.ground I
      // 0901: aload 0
      // 0902: getfield StageMaker.bco [LContO;
      // 0905: iload 12
      // 0907: aaload
      // 0908: getfield ContO.grat I
      // 090b: isub
      // 090c: istore 13
      // 090e: iload 12
      // 0910: bipush 54
      // 0912: if_icmpne 0921
      // 0915: aload 0
      // 0916: ldc_w "chk"
      // 0919: aload 10
      // 091b: bipush 4
      // 091c: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 091f: istore 13
      // 0921: aload 0
      // 0922: getfield StageMaker.co [LContO;
      // 0925: aload 0
      // 0926: getfield StageMaker.nob I
      // 0929: new ContO
      // 092c: dup
      // 092d: aload 0
      // 092e: getfield StageMaker.bco [LContO;
      // 0931: iload 12
      // 0933: aaload
      // 0934: aload 0
      // 0935: ldc_w "chk"
      // 0938: aload 10
      // 093a: bipush 1
      // 093b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 093e: iload 13
      // 0940: aload 0
      // 0941: ldc_w "chk"
      // 0944: aload 10
      // 0946: bipush 2
      // 0947: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 094a: aload 0
      // 094b: ldc_w "chk"
      // 094e: aload 10
      // 0950: bipush 3
      // 0951: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0954: invokespecial ContO.<init> (LContO;IIII)V
      // 0957: aastore
      // 0958: aload 0
      // 0959: getfield StageMaker.co [LContO;
      // 095c: aload 0
      // 095d: getfield StageMaker.nob I
      // 0960: aaload
      // 0961: aload 0
      // 0962: ldc_w "chk"
      // 0965: aload 10
      // 0967: bipush 3
      // 0968: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 096b: putfield ContO.roofat I
      // 096e: aload 0
      // 096f: getfield StageMaker.co [LContO;
      // 0972: aload 0
      // 0973: getfield StageMaker.nob I
      // 0976: aaload
      // 0977: iload 12
      // 0979: putfield ContO.colok I
      // 097c: aload 0
      // 097d: getfield StageMaker.cp LCheckPoints;
      // 0980: getfield CheckPoints.x [I
      // 0983: aload 0
      // 0984: getfield StageMaker.cp LCheckPoints;
      // 0987: getfield CheckPoints.n I
      // 098a: aload 0
      // 098b: ldc_w "chk"
      // 098e: aload 10
      // 0990: bipush 1
      // 0991: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0994: iastore
      // 0995: aload 0
      // 0996: getfield StageMaker.cp LCheckPoints;
      // 0999: getfield CheckPoints.z [I
      // 099c: aload 0
      // 099d: getfield StageMaker.cp LCheckPoints;
      // 09a0: getfield CheckPoints.n I
      // 09a3: aload 0
      // 09a4: ldc_w "chk"
      // 09a7: aload 10
      // 09a9: bipush 2
      // 09aa: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 09ad: iastore
      // 09ae: aload 0
      // 09af: getfield StageMaker.cp LCheckPoints;
      // 09b2: getfield CheckPoints.y [I
      // 09b5: aload 0
      // 09b6: getfield StageMaker.cp LCheckPoints;
      // 09b9: getfield CheckPoints.n I
      // 09bc: iload 13
      // 09be: iastore
      // 09bf: aload 0
      // 09c0: ldc_w "chk"
      // 09c3: aload 10
      // 09c5: bipush 3
      // 09c6: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 09c9: ifne 09df
      // 09cc: aload 0
      // 09cd: getfield StageMaker.cp LCheckPoints;
      // 09d0: getfield CheckPoints.typ [I
      // 09d3: aload 0
      // 09d4: getfield StageMaker.cp LCheckPoints;
      // 09d7: getfield CheckPoints.n I
      // 09da: bipush 1
      // 09db: iastore
      // 09dc: goto 09ef
      // 09df: aload 0
      // 09e0: getfield StageMaker.cp LCheckPoints;
      // 09e3: getfield CheckPoints.typ [I
      // 09e6: aload 0
      // 09e7: getfield StageMaker.cp LCheckPoints;
      // 09ea: getfield CheckPoints.n I
      // 09ed: bipush 2
      // 09ee: iastore
      // 09ef: aload 0
      // 09f0: getfield StageMaker.cp LCheckPoints;
      // 09f3: aload 0
      // 09f4: getfield StageMaker.cp LCheckPoints;
      // 09f7: getfield CheckPoints.n I
      // 09fa: putfield CheckPoints.pcs I
      // 09fd: aload 0
      // 09fe: getfield StageMaker.cp LCheckPoints;
      // 0a01: dup
      // 0a02: getfield CheckPoints.n I
      // 0a05: bipush 1
      // 0a06: iadd
      // 0a07: putfield CheckPoints.n I
      // 0a0a: aload 0
      // 0a0b: getfield StageMaker.co [LContO;
      // 0a0e: aload 0
      // 0a0f: getfield StageMaker.nob I
      // 0a12: aaload
      // 0a13: aload 0
      // 0a14: getfield StageMaker.cp LCheckPoints;
      // 0a17: getfield CheckPoints.nsp I
      // 0a1a: bipush 1
      // 0a1b: iadd
      // 0a1c: putfield ContO.checkpoint I
      // 0a1f: aload 10
      // 0a21: ldc_w ")r"
      // 0a24: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 0a27: bipush -1
      // 0a28: if_icmpeq 0a40
      // 0a2b: aload 0
      // 0a2c: getfield StageMaker.co [LContO;
      // 0a2f: aload 0
      // 0a30: getfield StageMaker.nob I
      // 0a33: aaload
      // 0a34: aload 0
      // 0a35: getfield StageMaker.cp LCheckPoints;
      // 0a38: getfield CheckPoints.nsp I
      // 0a3b: bipush 1
      // 0a3c: iadd
      // 0a3d: putfield ContO.wh I
      // 0a40: aload 0
      // 0a41: getfield StageMaker.cp LCheckPoints;
      // 0a44: dup
      // 0a45: getfield CheckPoints.nsp I
      // 0a48: bipush 1
      // 0a49: iadd
      // 0a4a: putfield CheckPoints.nsp I
      // 0a4d: aload 0
      // 0a4e: dup
      // 0a4f: getfield StageMaker.xnob I
      // 0a52: bipush 1
      // 0a53: iadd
      // 0a54: putfield StageMaker.xnob I
      // 0a57: aload 0
      // 0a58: dup
      // 0a59: getfield StageMaker.nob I
      // 0a5c: bipush 1
      // 0a5d: iadd
      // 0a5e: putfield StageMaker.nob I
      // 0a61: iload 1
      // 0a62: bipush 3
      // 0a63: if_icmpne 0aae
      // 0a66: iload 8
      // 0a68: ifeq 0a89
      // 0a6b: new java/lang/StringBuilder
      // 0a6e: dup
      // 0a6f: invokespecial java/lang/StringBuilder.<init> ()V
      // 0a72: aload 0
      // 0a73: dup_x1
      // 0a74: getfield StageMaker.bstage Ljava/lang/String;
      // 0a77: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a7a: ldc_w "\r\n"
      // 0a7d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a80: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0a83: putfield StageMaker.bstage Ljava/lang/String;
      // 0a86: bipush 0
      // 0a87: istore 8
      // 0a89: new java/lang/StringBuilder
      // 0a8c: dup
      // 0a8d: invokespecial java/lang/StringBuilder.<init> ()V
      // 0a90: aload 0
      // 0a91: dup_x1
      // 0a92: getfield StageMaker.bstage Ljava/lang/String;
      // 0a95: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a98: ldc ""
      // 0a9a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a9d: aload 10
      // 0a9f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0aa2: ldc_w "\r\n"
      // 0aa5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0aa8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0aab: putfield StageMaker.bstage Ljava/lang/String;
      // 0aae: aload 10
      // 0ab0: ldc_w "fix"
      // 0ab3: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0ab6: ifeq 0c5d
      // 0ab9: aload 0
      // 0aba: ldc_w "fix"
      // 0abd: aload 10
      // 0abf: bipush 0
      // 0ac0: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0ac3: istore 12
      // 0ac5: iinc 12 -10
      // 0ac8: aload 0
      // 0ac9: getfield StageMaker.co [LContO;
      // 0acc: aload 0
      // 0acd: getfield StageMaker.nob I
      // 0ad0: new ContO
      // 0ad3: dup
      // 0ad4: aload 0
      // 0ad5: getfield StageMaker.bco [LContO;
      // 0ad8: iload 12
      // 0ada: aaload
      // 0adb: aload 0
      // 0adc: ldc_w "fix"
      // 0adf: aload 10
      // 0ae1: bipush 1
      // 0ae2: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0ae5: aload 0
      // 0ae6: ldc_w "fix"
      // 0ae9: aload 10
      // 0aeb: bipush 3
      // 0aec: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0aef: aload 0
      // 0af0: ldc_w "fix"
      // 0af3: aload 10
      // 0af5: bipush 2
      // 0af6: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0af9: aload 0
      // 0afa: ldc_w "fix"
      // 0afd: aload 10
      // 0aff: bipush 4
      // 0b00: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b03: invokespecial ContO.<init> (LContO;IIII)V
      // 0b06: aastore
      // 0b07: aload 0
      // 0b08: getfield StageMaker.co [LContO;
      // 0b0b: aload 0
      // 0b0c: getfield StageMaker.nob I
      // 0b0f: aaload
      // 0b10: aload 0
      // 0b11: ldc_w "fix"
      // 0b14: aload 10
      // 0b16: bipush 4
      // 0b17: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b1a: putfield ContO.roofat I
      // 0b1d: aload 0
      // 0b1e: getfield StageMaker.co [LContO;
      // 0b21: aload 0
      // 0b22: getfield StageMaker.nob I
      // 0b25: aaload
      // 0b26: iload 12
      // 0b28: putfield ContO.colok I
      // 0b2b: aload 0
      // 0b2c: getfield StageMaker.cp LCheckPoints;
      // 0b2f: getfield CheckPoints.fx [I
      // 0b32: aload 0
      // 0b33: getfield StageMaker.cp LCheckPoints;
      // 0b36: getfield CheckPoints.fn I
      // 0b39: aload 0
      // 0b3a: ldc_w "fix"
      // 0b3d: aload 10
      // 0b3f: bipush 1
      // 0b40: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b43: iastore
      // 0b44: aload 0
      // 0b45: getfield StageMaker.cp LCheckPoints;
      // 0b48: getfield CheckPoints.fz [I
      // 0b4b: aload 0
      // 0b4c: getfield StageMaker.cp LCheckPoints;
      // 0b4f: getfield CheckPoints.fn I
      // 0b52: aload 0
      // 0b53: ldc_w "fix"
      // 0b56: aload 10
      // 0b58: bipush 2
      // 0b59: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b5c: iastore
      // 0b5d: aload 0
      // 0b5e: getfield StageMaker.cp LCheckPoints;
      // 0b61: getfield CheckPoints.fy [I
      // 0b64: aload 0
      // 0b65: getfield StageMaker.cp LCheckPoints;
      // 0b68: getfield CheckPoints.fn I
      // 0b6b: aload 0
      // 0b6c: ldc_w "fix"
      // 0b6f: aload 10
      // 0b71: bipush 3
      // 0b72: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b75: iastore
      // 0b76: aload 0
      // 0b77: getfield StageMaker.co [LContO;
      // 0b7a: aload 0
      // 0b7b: getfield StageMaker.nob I
      // 0b7e: aaload
      // 0b7f: bipush 1
      // 0b80: putfield ContO.elec Z
      // 0b83: aload 0
      // 0b84: ldc_w "fix"
      // 0b87: aload 10
      // 0b89: bipush 4
      // 0b8a: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0b8d: ifeq 0bb0
      // 0b90: aload 0
      // 0b91: getfield StageMaker.cp LCheckPoints;
      // 0b94: getfield CheckPoints.roted [Z
      // 0b97: aload 0
      // 0b98: getfield StageMaker.cp LCheckPoints;
      // 0b9b: getfield CheckPoints.fn I
      // 0b9e: bipush 1
      // 0b9f: bastore
      // 0ba0: aload 0
      // 0ba1: getfield StageMaker.co [LContO;
      // 0ba4: aload 0
      // 0ba5: getfield StageMaker.nob I
      // 0ba8: aaload
      // 0ba9: bipush 1
      // 0baa: putfield ContO.roted Z
      // 0bad: goto 0bc0
      // 0bb0: aload 0
      // 0bb1: getfield StageMaker.cp LCheckPoints;
      // 0bb4: getfield CheckPoints.roted [Z
      // 0bb7: aload 0
      // 0bb8: getfield StageMaker.cp LCheckPoints;
      // 0bbb: getfield CheckPoints.fn I
      // 0bbe: bipush 0
      // 0bbf: bastore
      // 0bc0: aload 10
      // 0bc2: ldc_w ")s"
      // 0bc5: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 0bc8: bipush -1
      // 0bc9: if_icmpeq 0bdf
      // 0bcc: aload 0
      // 0bcd: getfield StageMaker.cp LCheckPoints;
      // 0bd0: getfield CheckPoints.special [Z
      // 0bd3: aload 0
      // 0bd4: getfield StageMaker.cp LCheckPoints;
      // 0bd7: getfield CheckPoints.fn I
      // 0bda: bipush 1
      // 0bdb: bastore
      // 0bdc: goto 0bef
      // 0bdf: aload 0
      // 0be0: getfield StageMaker.cp LCheckPoints;
      // 0be3: getfield CheckPoints.special [Z
      // 0be6: aload 0
      // 0be7: getfield StageMaker.cp LCheckPoints;
      // 0bea: getfield CheckPoints.fn I
      // 0bed: bipush 0
      // 0bee: bastore
      // 0bef: aload 0
      // 0bf0: getfield StageMaker.cp LCheckPoints;
      // 0bf3: dup
      // 0bf4: getfield CheckPoints.fn I
      // 0bf7: bipush 1
      // 0bf8: iadd
      // 0bf9: putfield CheckPoints.fn I
      // 0bfc: aload 0
      // 0bfd: dup
      // 0bfe: getfield StageMaker.xnob I
      // 0c01: bipush 1
      // 0c02: iadd
      // 0c03: putfield StageMaker.xnob I
      // 0c06: aload 0
      // 0c07: dup
      // 0c08: getfield StageMaker.nob I
      // 0c0b: bipush 1
      // 0c0c: iadd
      // 0c0d: putfield StageMaker.nob I
      // 0c10: iload 1
      // 0c11: bipush 3
      // 0c12: if_icmpne 0c5d
      // 0c15: iload 8
      // 0c17: ifeq 0c38
      // 0c1a: new java/lang/StringBuilder
      // 0c1d: dup
      // 0c1e: invokespecial java/lang/StringBuilder.<init> ()V
      // 0c21: aload 0
      // 0c22: dup_x1
      // 0c23: getfield StageMaker.bstage Ljava/lang/String;
      // 0c26: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c29: ldc_w "\r\n"
      // 0c2c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c2f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0c32: putfield StageMaker.bstage Ljava/lang/String;
      // 0c35: bipush 0
      // 0c36: istore 8
      // 0c38: new java/lang/StringBuilder
      // 0c3b: dup
      // 0c3c: invokespecial java/lang/StringBuilder.<init> ()V
      // 0c3f: aload 0
      // 0c40: dup_x1
      // 0c41: getfield StageMaker.bstage Ljava/lang/String;
      // 0c44: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c47: ldc ""
      // 0c49: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c4c: aload 10
      // 0c4e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c51: ldc_w "\r\n"
      // 0c54: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0c57: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0c5a: putfield StageMaker.bstage Ljava/lang/String;
      // 0c5d: aload 10
      // 0c5f: ldc_w "pile"
      // 0c62: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0c65: ifeq 0d6a
      // 0c68: aload 0
      // 0c69: getfield StageMaker.co [LContO;
      // 0c6c: aload 0
      // 0c6d: getfield StageMaker.nob I
      // 0c70: new ContO
      // 0c73: dup
      // 0c74: aload 0
      // 0c75: ldc_w "pile"
      // 0c78: aload 10
      // 0c7a: bipush 0
      // 0c7b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0c7e: aload 0
      // 0c7f: ldc_w "pile"
      // 0c82: aload 10
      // 0c84: bipush 1
      // 0c85: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0c88: aload 0
      // 0c89: ldc_w "pile"
      // 0c8c: aload 10
      // 0c8e: bipush 2
      // 0c8f: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0c92: aload 0
      // 0c93: getfield StageMaker.m LMedium;
      // 0c96: aload 0
      // 0c97: getfield StageMaker.t LTrackers;
      // 0c9a: aload 0
      // 0c9b: ldc_w "pile"
      // 0c9e: aload 10
      // 0ca0: bipush 3
      // 0ca1: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0ca4: aload 0
      // 0ca5: ldc_w "pile"
      // 0ca8: aload 10
      // 0caa: bipush 4
      // 0cab: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0cae: aload 0
      // 0caf: getfield StageMaker.m LMedium;
      // 0cb2: getfield Medium.ground I
      // 0cb5: invokespecial ContO.<init> (IIILMedium;LTrackers;III)V
      // 0cb8: aastore
      // 0cb9: aload 0
      // 0cba: getfield StageMaker.co [LContO;
      // 0cbd: aload 0
      // 0cbe: getfield StageMaker.nob I
      // 0cc1: aaload
      // 0cc2: aload 0
      // 0cc3: ldc_w "pile"
      // 0cc6: aload 10
      // 0cc8: bipush 0
      // 0cc9: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0ccc: putfield ContO.srz I
      // 0ccf: aload 0
      // 0cd0: getfield StageMaker.co [LContO;
      // 0cd3: aload 0
      // 0cd4: getfield StageMaker.nob I
      // 0cd7: aaload
      // 0cd8: aload 0
      // 0cd9: ldc_w "pile"
      // 0cdc: aload 10
      // 0cde: bipush 1
      // 0cdf: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0ce2: putfield ContO.srx I
      // 0ce5: aload 0
      // 0ce6: getfield StageMaker.co [LContO;
      // 0ce9: aload 0
      // 0cea: getfield StageMaker.nob I
      // 0ced: aaload
      // 0cee: aload 0
      // 0cef: ldc_w "pile"
      // 0cf2: aload 10
      // 0cf4: bipush 2
      // 0cf5: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0cf8: putfield ContO.sry I
      // 0cfb: aload 0
      // 0cfc: getfield StageMaker.co [LContO;
      // 0cff: aload 0
      // 0d00: getfield StageMaker.nob I
      // 0d03: aaload
      // 0d04: bipush 66
      // 0d06: putfield ContO.colok I
      // 0d09: aload 0
      // 0d0a: dup
      // 0d0b: getfield StageMaker.xnob I
      // 0d0e: bipush 1
      // 0d0f: iadd
      // 0d10: putfield StageMaker.xnob I
      // 0d13: aload 0
      // 0d14: dup
      // 0d15: getfield StageMaker.nob I
      // 0d18: bipush 1
      // 0d19: iadd
      // 0d1a: putfield StageMaker.nob I
      // 0d1d: iload 1
      // 0d1e: bipush 3
      // 0d1f: if_icmpne 0d6a
      // 0d22: iload 8
      // 0d24: ifeq 0d45
      // 0d27: new java/lang/StringBuilder
      // 0d2a: dup
      // 0d2b: invokespecial java/lang/StringBuilder.<init> ()V
      // 0d2e: aload 0
      // 0d2f: dup_x1
      // 0d30: getfield StageMaker.bstage Ljava/lang/String;
      // 0d33: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d36: ldc_w "\r\n"
      // 0d39: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d3c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0d3f: putfield StageMaker.bstage Ljava/lang/String;
      // 0d42: bipush 0
      // 0d43: istore 8
      // 0d45: new java/lang/StringBuilder
      // 0d48: dup
      // 0d49: invokespecial java/lang/StringBuilder.<init> ()V
      // 0d4c: aload 0
      // 0d4d: dup_x1
      // 0d4e: getfield StageMaker.bstage Ljava/lang/String;
      // 0d51: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d54: ldc ""
      // 0d56: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d59: aload 10
      // 0d5b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d5e: ldc_w "\r\n"
      // 0d61: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d64: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0d67: putfield StageMaker.bstage Ljava/lang/String;
      // 0d6a: aload 10
      // 0d6c: ldc_w "maxr"
      // 0d6f: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0d72: ifeq 0e49
      // 0d75: aload 0
      // 0d76: ldc_w "maxr"
      // 0d79: aload 10
      // 0d7b: bipush 0
      // 0d7c: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0d7f: istore 12
      // 0d81: aload 0
      // 0d82: ldc_w "maxr"
      // 0d85: aload 10
      // 0d87: bipush 1
      // 0d88: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0d8b: istore 13
      // 0d8d: iload 13
      // 0d8f: istore 3
      // 0d90: aload 0
      // 0d91: ldc_w "maxr"
      // 0d94: aload 10
      // 0d96: bipush 2
      // 0d97: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0d9a: istore 14
      // 0d9c: bipush 0
      // 0d9d: istore 15
      // 0d9f: iload 15
      // 0da1: iload 12
      // 0da3: if_icmpge 0dfc
      // 0da6: aload 0
      // 0da7: getfield StageMaker.co [LContO;
      // 0daa: aload 0
      // 0dab: getfield StageMaker.nob I
      // 0dae: new ContO
      // 0db1: dup
      // 0db2: aload 0
      // 0db3: getfield StageMaker.bco [LContO;
      // 0db6: bipush 29
      // 0db8: aaload
      // 0db9: iload 13
      // 0dbb: aload 0
      // 0dbc: getfield StageMaker.m LMedium;
      // 0dbf: getfield Medium.ground I
      // 0dc2: aload 0
      // 0dc3: getfield StageMaker.bco [LContO;
      // 0dc6: bipush 29
      // 0dc8: aaload
      // 0dc9: getfield ContO.grat I
      // 0dcc: isub
      // 0dcd: iload 15
      // 0dcf: sipush 4800
      // 0dd2: imul
      // 0dd3: iload 14
      // 0dd5: iadd
      // 0dd6: bipush 0
      // 0dd7: invokespecial ContO.<init> (LContO;IIII)V
      // 0dda: aastore
      // 0ddb: iload 1
      // 0ddc: ifne 0dec
      // 0ddf: aload 0
      // 0de0: dup
      // 0de1: getfield StageMaker.xnob I
      // 0de4: bipush 1
      // 0de5: iadd
      // 0de6: putfield StageMaker.xnob I
      // 0de9: goto 0df6
      // 0dec: aload 0
      // 0ded: dup
      // 0dee: getfield StageMaker.nob I
      // 0df1: bipush 1
      // 0df2: iadd
      // 0df3: putfield StageMaker.nob I
      // 0df6: iinc 15 1
      // 0df9: goto 0d9f
      // 0dfc: iload 1
      // 0dfd: bipush 3
      // 0dfe: if_icmpne 0e49
      // 0e01: iload 7
      // 0e03: ifeq 0e24
      // 0e06: new java/lang/StringBuilder
      // 0e09: dup
      // 0e0a: invokespecial java/lang/StringBuilder.<init> ()V
      // 0e0d: aload 0
      // 0e0e: dup_x1
      // 0e0f: getfield StageMaker.bstage Ljava/lang/String;
      // 0e12: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e15: ldc_w "\r\n"
      // 0e18: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e1b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0e1e: putfield StageMaker.bstage Ljava/lang/String;
      // 0e21: bipush 0
      // 0e22: istore 7
      // 0e24: new java/lang/StringBuilder
      // 0e27: dup
      // 0e28: invokespecial java/lang/StringBuilder.<init> ()V
      // 0e2b: aload 0
      // 0e2c: dup_x1
      // 0e2d: getfield StageMaker.bstage Ljava/lang/String;
      // 0e30: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e33: ldc ""
      // 0e35: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e38: aload 10
      // 0e3a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e3d: ldc_w "\r\n"
      // 0e40: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0e43: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0e46: putfield StageMaker.bstage Ljava/lang/String;
      // 0e49: aload 10
      // 0e4b: ldc_w "maxl"
      // 0e4e: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0e51: ifeq 0f2b
      // 0e54: aload 0
      // 0e55: ldc_w "maxl"
      // 0e58: aload 10
      // 0e5a: bipush 0
      // 0e5b: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0e5e: istore 12
      // 0e60: aload 0
      // 0e61: ldc_w "maxl"
      // 0e64: aload 10
      // 0e66: bipush 1
      // 0e67: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0e6a: istore 13
      // 0e6c: iload 13
      // 0e6e: istore 4
      // 0e70: aload 0
      // 0e71: ldc_w "maxl"
      // 0e74: aload 10
      // 0e76: bipush 2
      // 0e77: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0e7a: istore 14
      // 0e7c: bipush 0
      // 0e7d: istore 15
      // 0e7f: iload 15
      // 0e81: iload 12
      // 0e83: if_icmpge 0ede
      // 0e86: aload 0
      // 0e87: getfield StageMaker.co [LContO;
      // 0e8a: aload 0
      // 0e8b: getfield StageMaker.nob I
      // 0e8e: new ContO
      // 0e91: dup
      // 0e92: aload 0
      // 0e93: getfield StageMaker.bco [LContO;
      // 0e96: bipush 29
      // 0e98: aaload
      // 0e99: iload 13
      // 0e9b: aload 0
      // 0e9c: getfield StageMaker.m LMedium;
      // 0e9f: getfield Medium.ground I
      // 0ea2: aload 0
      // 0ea3: getfield StageMaker.bco [LContO;
      // 0ea6: bipush 29
      // 0ea8: aaload
      // 0ea9: getfield ContO.grat I
      // 0eac: isub
      // 0ead: iload 15
      // 0eaf: sipush 4800
      // 0eb2: imul
      // 0eb3: iload 14
      // 0eb5: iadd
      // 0eb6: sipush 180
      // 0eb9: invokespecial ContO.<init> (LContO;IIII)V
      // 0ebc: aastore
      // 0ebd: iload 1
      // 0ebe: ifne 0ece
      // 0ec1: aload 0
      // 0ec2: dup
      // 0ec3: getfield StageMaker.xnob I
      // 0ec6: bipush 1
      // 0ec7: iadd
      // 0ec8: putfield StageMaker.xnob I
      // 0ecb: goto 0ed8
      // 0ece: aload 0
      // 0ecf: dup
      // 0ed0: getfield StageMaker.nob I
      // 0ed3: bipush 1
      // 0ed4: iadd
      // 0ed5: putfield StageMaker.nob I
      // 0ed8: iinc 15 1
      // 0edb: goto 0e7f
      // 0ede: iload 1
      // 0edf: bipush 3
      // 0ee0: if_icmpne 0f2b
      // 0ee3: iload 7
      // 0ee5: ifeq 0f06
      // 0ee8: new java/lang/StringBuilder
      // 0eeb: dup
      // 0eec: invokespecial java/lang/StringBuilder.<init> ()V
      // 0eef: aload 0
      // 0ef0: dup_x1
      // 0ef1: getfield StageMaker.bstage Ljava/lang/String;
      // 0ef4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0ef7: ldc_w "\r\n"
      // 0efa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0efd: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0f00: putfield StageMaker.bstage Ljava/lang/String;
      // 0f03: bipush 0
      // 0f04: istore 7
      // 0f06: new java/lang/StringBuilder
      // 0f09: dup
      // 0f0a: invokespecial java/lang/StringBuilder.<init> ()V
      // 0f0d: aload 0
      // 0f0e: dup_x1
      // 0f0f: getfield StageMaker.bstage Ljava/lang/String;
      // 0f12: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0f15: ldc ""
      // 0f17: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0f1a: aload 10
      // 0f1c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0f1f: ldc_w "\r\n"
      // 0f22: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0f25: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0f28: putfield StageMaker.bstage Ljava/lang/String;
      // 0f2b: aload 10
      // 0f2d: ldc_w "maxt"
      // 0f30: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 0f33: ifeq 100c
      // 0f36: aload 0
      // 0f37: ldc_w "maxt"
      // 0f3a: aload 10
      // 0f3c: bipush 0
      // 0f3d: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0f40: istore 12
      // 0f42: aload 0
      // 0f43: ldc_w "maxt"
      // 0f46: aload 10
      // 0f48: bipush 1
      // 0f49: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0f4c: istore 13
      // 0f4e: iload 13
      // 0f50: istore 5
      // 0f52: aload 0
      // 0f53: ldc_w "maxt"
      // 0f56: aload 10
      // 0f58: bipush 2
      // 0f59: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 0f5c: istore 14
      // 0f5e: bipush 0
      // 0f5f: istore 15
      // 0f61: iload 15
      // 0f63: iload 12
      // 0f65: if_icmpge 0fbf
      // 0f68: aload 0
      // 0f69: getfield StageMaker.co [LContO;
      // 0f6c: aload 0
      // 0f6d: getfield StageMaker.nob I
      // 0f70: new ContO
      // 0f73: dup
      // 0f74: aload 0
      // 0f75: getfield StageMaker.bco [LContO;
      // 0f78: bipush 29
      // 0f7a: aaload
      // 0f7b: iload 15
      // 0f7d: sipush 4800
      // 0f80: imul
      // 0f81: iload 14
      // 0f83: iadd
      // 0f84: aload 0
      // 0f85: getfield StageMaker.m LMedium;
      // 0f88: getfield Medium.ground I
      // 0f8b: aload 0
      // 0f8c: getfield StageMaker.bco [LContO;
      // 0f8f: bipush 29
      // 0f91: aaload
      // 0f92: getfield ContO.grat I
      // 0f95: isub
      // 0f96: iload 13
      // 0f98: bipush 90
      // 0f9a: invokespecial ContO.<init> (LContO;IIII)V
      // 0f9d: aastore
      // 0f9e: iload 1
      // 0f9f: ifne 0faf
      // 0fa2: aload 0
      // 0fa3: dup
      // 0fa4: getfield StageMaker.xnob I
      // 0fa7: bipush 1
      // 0fa8: iadd
      // 0fa9: putfield StageMaker.xnob I
      // 0fac: goto 0fb9
      // 0faf: aload 0
      // 0fb0: dup
      // 0fb1: getfield StageMaker.nob I
      // 0fb4: bipush 1
      // 0fb5: iadd
      // 0fb6: putfield StageMaker.nob I
      // 0fb9: iinc 15 1
      // 0fbc: goto 0f61
      // 0fbf: iload 1
      // 0fc0: bipush 3
      // 0fc1: if_icmpne 100c
      // 0fc4: iload 7
      // 0fc6: ifeq 0fe7
      // 0fc9: new java/lang/StringBuilder
      // 0fcc: dup
      // 0fcd: invokespecial java/lang/StringBuilder.<init> ()V
      // 0fd0: aload 0
      // 0fd1: dup_x1
      // 0fd2: getfield StageMaker.bstage Ljava/lang/String;
      // 0fd5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0fd8: ldc_w "\r\n"
      // 0fdb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0fde: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0fe1: putfield StageMaker.bstage Ljava/lang/String;
      // 0fe4: bipush 0
      // 0fe5: istore 7
      // 0fe7: new java/lang/StringBuilder
      // 0fea: dup
      // 0feb: invokespecial java/lang/StringBuilder.<init> ()V
      // 0fee: aload 0
      // 0fef: dup_x1
      // 0ff0: getfield StageMaker.bstage Ljava/lang/String;
      // 0ff3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0ff6: ldc ""
      // 0ff8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0ffb: aload 10
      // 0ffd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1000: ldc_w "\r\n"
      // 1003: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1006: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1009: putfield StageMaker.bstage Ljava/lang/String;
      // 100c: aload 10
      // 100e: ldc_w "maxb"
      // 1011: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 1014: ifeq 0148
      // 1017: aload 0
      // 1018: ldc_w "maxb"
      // 101b: aload 10
      // 101d: bipush 0
      // 101e: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 1021: istore 12
      // 1023: aload 0
      // 1024: ldc_w "maxb"
      // 1027: aload 10
      // 1029: bipush 1
      // 102a: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 102d: istore 13
      // 102f: iload 13
      // 1031: istore 6
      // 1033: aload 0
      // 1034: ldc_w "maxb"
      // 1037: aload 10
      // 1039: bipush 2
      // 103a: invokevirtual StageMaker.getint (Ljava/lang/String;Ljava/lang/String;I)I
      // 103d: istore 14
      // 103f: bipush 0
      // 1040: istore 15
      // 1042: iload 15
      // 1044: iload 12
      // 1046: if_icmpge 10a0
      // 1049: aload 0
      // 104a: getfield StageMaker.co [LContO;
      // 104d: aload 0
      // 104e: getfield StageMaker.nob I
      // 1051: new ContO
      // 1054: dup
      // 1055: aload 0
      // 1056: getfield StageMaker.bco [LContO;
      // 1059: bipush 29
      // 105b: aaload
      // 105c: iload 15
      // 105e: sipush 4800
      // 1061: imul
      // 1062: iload 14
      // 1064: iadd
      // 1065: aload 0
      // 1066: getfield StageMaker.m LMedium;
      // 1069: getfield Medium.ground I
      // 106c: aload 0
      // 106d: getfield StageMaker.bco [LContO;
      // 1070: bipush 29
      // 1072: aaload
      // 1073: getfield ContO.grat I
      // 1076: isub
      // 1077: iload 13
      // 1079: bipush -90
      // 107b: invokespecial ContO.<init> (LContO;IIII)V
      // 107e: aastore
      // 107f: iload 1
      // 1080: ifne 1090
      // 1083: aload 0
      // 1084: dup
      // 1085: getfield StageMaker.xnob I
      // 1088: bipush 1
      // 1089: iadd
      // 108a: putfield StageMaker.xnob I
      // 108d: goto 109a
      // 1090: aload 0
      // 1091: dup
      // 1092: getfield StageMaker.nob I
      // 1095: bipush 1
      // 1096: iadd
      // 1097: putfield StageMaker.nob I
      // 109a: iinc 15 1
      // 109d: goto 1042
      // 10a0: iload 1
      // 10a1: bipush 3
      // 10a2: if_icmpne 10ed
      // 10a5: iload 7
      // 10a7: ifeq 10c8
      // 10aa: new java/lang/StringBuilder
      // 10ad: dup
      // 10ae: invokespecial java/lang/StringBuilder.<init> ()V
      // 10b1: aload 0
      // 10b2: dup_x1
      // 10b3: getfield StageMaker.bstage Ljava/lang/String;
      // 10b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10b9: ldc_w "\r\n"
      // 10bc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10bf: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 10c2: putfield StageMaker.bstage Ljava/lang/String;
      // 10c5: bipush 0
      // 10c6: istore 7
      // 10c8: new java/lang/StringBuilder
      // 10cb: dup
      // 10cc: invokespecial java/lang/StringBuilder.<init> ()V
      // 10cf: aload 0
      // 10d0: dup_x1
      // 10d1: getfield StageMaker.bstage Ljava/lang/String;
      // 10d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10d7: ldc ""
      // 10d9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10dc: aload 10
      // 10de: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10e1: ldc_w "\r\n"
      // 10e4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 10e7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 10ea: putfield StageMaker.bstage Ljava/lang/String;
      // 10ed: goto 0148
      // 10f0: aload 11
      // 10f2: invokevirtual java/io/DataInputStream.close ()V
      // 10f5: aload 0
      // 10f6: getfield StageMaker.m LMedium;
      // 10f9: iload 4
      // 10fb: iload 3
      // 10fc: iload 4
      // 10fe: isub
      // 10ff: iload 6
      // 1101: iload 5
      // 1103: iload 6
      // 1105: isub
      // 1106: aload 0
      // 1107: getfield StageMaker.t LTrackers;
      // 110a: aload 0
      // 110b: getfield StageMaker.nob I
      // 110e: invokevirtual Medium.newpolys (IIIILTrackers;I)V
      // 1111: aload 0
      // 1112: getfield StageMaker.m LMedium;
      // 1115: iload 4
      // 1117: iload 3
      // 1118: iload 6
      // 111a: iload 5
      // 111c: invokevirtual Medium.newclouds (IIII)V
      // 111f: aload 0
      // 1120: getfield StageMaker.m LMedium;
      // 1123: iload 4
      // 1125: iload 3
      // 1126: iload 6
      // 1128: iload 5
      // 112a: invokevirtual Medium.newmountains (IIII)V
      // 112d: aload 0
      // 112e: getfield StageMaker.m LMedium;
      // 1131: invokevirtual Medium.newstars ()V
      // 1134: goto 11d4
      // 1137: astore 11
      // 1139: getstatic java/lang/System.out Ljava/io/PrintStream;
      // 113c: new java/lang/StringBuilder
      // 113f: dup
      // 1140: invokespecial java/lang/StringBuilder.<init> ()V
      // 1143: ldc_w "Error in stage "
      // 1146: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1149: aload 0
      // 114a: getfield StageMaker.stagename Ljava/lang/String;
      // 114d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1150: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1153: invokevirtual java/io/PrintStream.println (Ljava/lang/String;)V
      // 1156: getstatic java/lang/System.out Ljava/io/PrintStream;
      // 1159: new java/lang/StringBuilder
      // 115c: dup
      // 115d: invokespecial java/lang/StringBuilder.<init> ()V
      // 1160: ldc ""
      // 1162: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1165: aload 11
      // 1167: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 116a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 116d: invokevirtual java/io/PrintStream.println (Ljava/lang/String;)V
      // 1170: getstatic java/lang/System.out Ljava/io/PrintStream;
      // 1173: new java/lang/StringBuilder
      // 1176: dup
      // 1177: invokespecial java/lang/StringBuilder.<init> ()V
      // 117a: ldc_w "At line: "
      // 117d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1180: aload 10
      // 1182: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1185: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1188: invokevirtual java/io/PrintStream.println (Ljava/lang/String;)V
      // 118b: aload 0
      // 118c: bipush 6
      // 118e: putfield StageMaker.errd I
      // 1191: aload 0
      // 1192: getfield StageMaker.cp LCheckPoints;
      // 1195: getfield CheckPoints.fn I
      // 1198: bipush 5
      // 1199: if_icmplt 11a1
      // 119c: aload 0
      // 119d: bipush 5
      // 119e: putfield StageMaker.errd I
      // 11a1: aload 0
      // 11a2: getfield StageMaker.t LTrackers;
      // 11a5: getfield Trackers.nt I
      // 11a8: sipush 6700
      // 11ab: if_icmplt 11b3
      // 11ae: aload 0
      // 11af: bipush 1
      // 11b0: putfield StageMaker.errd I
      // 11b3: aload 0
      // 11b4: getfield StageMaker.cp LCheckPoints;
      // 11b7: getfield CheckPoints.n I
      // 11ba: sipush 140
      // 11bd: if_icmplt 11c5
      // 11c0: aload 0
      // 11c1: bipush 2
      // 11c2: putfield StageMaker.errd I
      // 11c5: aload 0
      // 11c6: getfield StageMaker.nob I
      // 11c9: sipush 601
      // 11cc: if_icmplt 11d4
      // 11cf: aload 0
      // 11d0: bipush 4
      // 11d1: putfield StageMaker.errd I
      // 11d4: aload 0
      // 11d5: getfield StageMaker.m LMedium;
      // 11d8: getfield Medium.nrw I
      // 11db: aload 0
      // 11dc: getfield StageMaker.m LMedium;
      // 11df: getfield Medium.ncl I
      // 11e2: imul
      // 11e3: sipush 16000
      // 11e6: if_icmplt 11ee
      // 11e9: aload 0
      // 11ea: bipush 3
      // 11eb: putfield StageMaker.errd I
      // 11ee: aload 0
      // 11ef: getfield StageMaker.xnob I
      // 11f2: sipush 602
      // 11f5: if_icmplt 11fd
      // 11f8: aload 0
      // 11f9: bipush 4
      // 11fa: putfield StageMaker.errd I
      // 11fd: iload 1
      // 11fe: bipush 3
      // 11ff: if_icmpne 1239
      // 1202: aload 0
      // 1203: getfield StageMaker.bstage Ljava/lang/String;
      // 1206: ldc_w "set(47,0,0,0)"
      // 1209: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 120c: bipush -1
      // 120d: if_icmpne 1239
      // 1210: aload 0
      // 1211: getfield StageMaker.bstage Ljava/lang/String;
      // 1214: ldc_w "set(48,0,0,0)"
      // 1217: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 121a: bipush -1
      // 121b: if_icmpne 1239
      // 121e: new java/lang/StringBuilder
      // 1221: dup
      // 1222: invokespecial java/lang/StringBuilder.<init> ()V
      // 1225: aload 0
      // 1226: dup_x1
      // 1227: getfield StageMaker.bstage Ljava/lang/String;
      // 122a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 122d: ldc_w "set(47,0,0,0)\r\n"
      // 1230: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1233: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1236: putfield StageMaker.bstage Ljava/lang/String;
      // 1239: return
      // try (117 -> 2112): 2113 java/lang/Exception
   }

   public void newstage() {
      if (!this.srch.getText().equals("")) {
         File var1 = new File("" + Madness.fpath + "mystages/" + this.srch.getText() + ".txt");
         if (!var1.exists()) {
            this.stagename = this.srch.getText();
            this.tstage = "snap(0,0,0)\r\nsky(191,215,255)\r\nclouds(255,255,255,5,-1000)\r\nfog(195,207,230)\r\nground(192,194,202)\r\ntexture(0,0,0,50)\r\nfadefrom(5000)\r\ndensity(5)\r\nmountains("
               + (int)(Math.random() * 100000.0)
               + ")\r\nnlaps(5)\r\n\r\n";
            if (this.strtyp.getSelectedIndex() == 1) {
               this.bstage = "set(48,0,0,0)\r\n";
            } else {
               this.bstage = "set(47,0,0,0)\r\n";
            }

            this.bstage = this.bstage + "\r\nmaxl(3,-7200,-4800)\r\nmaxb(3,-7200,-4800)\r\nmaxr(3,7200,-4800)\r\nmaxt(3,7200,-4800)\r\n";
            this.savefile();
            this.strtyp.hide();
            this.srch.hide();
            this.sfase = 0;
            this.tabed = -2;
         } else {
            JOptionPane.showMessageDialog(null, "A stage with that name already exists, please choose another name!", "Stage Maker", 1);
         }
      } else {
         JOptionPane.showMessageDialog(null, "Please enter a stage name first!", "Stage Maker", 1);
      }
   }

   public void sortop() {
      this.tstage = "snap("
         + this.m.snap[0]
         + ","
         + this.m.snap[1]
         + ","
         + this.m.snap[2]
         + ")\r\nsky("
         + this.csky[0]
         + ","
         + this.csky[1]
         + ","
         + this.csky[2]
         + ")\r\nfog("
         + this.cfade[0]
         + ","
         + this.cfade[1]
         + ","
         + this.cfade[2]
         + ")\r\nclouds("
         + this.cldd[0]
         + ","
         + this.cldd[1]
         + ","
         + this.cldd[2]
         + ","
         + this.cldd[3]
         + ","
         + this.cldd[4]
         + ")\r\nground("
         + this.cgrnd[0]
         + ","
         + this.cgrnd[1]
         + ","
         + this.cgrnd[2]
         + ")\r\ntexture("
         + this.texture[0]
         + ","
         + this.texture[1]
         + ","
         + this.texture[2]
         + ","
         + this.texture[3]
         + ")\r\nfadefrom("
         + this.origfade
         + ")\r\ndensity("
         + ((this.m.fogd + 1) / 2 - 1)
         + ")\r\nmountains("
         + this.m.mgen
         + ")\r\nnlaps("
         + this.cp.nlaps
         + ")\r\n";
      if (!this.trackname.equals("")) {
         this.tstage = this.tstage + "soundtrack(" + this.trackname + "," + this.trackvol + "," + this.tracksize + ")\r\n";
      }

      for (int var1 = 0; var1 < 3; var1++) {
         this.snap[var1] = (int)(this.m.snap[var1] / 1.2F + 50.0F);
      }

      if (this.snap[0] + this.snap[1] + this.snap[2] <= 110) {
         this.tstage = this.tstage + "lightson()\r\n";
      }

      this.tstage = this.tstage + "\r\n";
   }

   public void sortstage() {
      int[] var1 = new int[this.nob * 2];
      int[] var2 = new int[this.nob * 2];

      for (int var3 = 0; var3 < this.nob; var3++) {
         var1[var3] = 0;
      }

      int var21 = 0;
      int var4 = 0;
      var2[var4] = 0;
      var4++;
      boolean var5 = false;
      byte var6 = 0;

      while (!var5) {
         int[] var7 = new int[]{this.co[var21].x + this.atp[this.co[var21].colok][0], this.co[var21].x + this.atp[this.co[var21].colok][2]};
         int[] var8 = new int[]{this.co[var21].z + this.atp[this.co[var21].colok][1], this.co[var21].z + this.atp[this.co[var21].colok][3]};
         int var9 = this.co[var21].roofat;
         if (this.co[var21].colok == 2) {
            var9 += 30;
         }

         if (this.co[var21].colok == 3) {
            var9 -= 30;
         }

         if (this.co[var21].colok == 15) {
            var9 -= 90;
         }

         if (this.co[var21].colok == 20) {
            var9 -= 180;
         }

         if (this.co[var21].colok == 26) {
            var9 -= 90;
         }

         this.rot(var7, var8, this.co[var21].x, this.co[var21].z, var9, 2);
         int var10 = -1;
         int var11 = -1;
         if (var6 != 0) {
            for (int var12 = 0; var12 < this.nob; var12++) {
               boolean var13 = false;
               if (var4 == 2 && var12 == 0) {
                  var13 = true;
               }

               if (var21 != var12
                  && !var13
                  && var1[var12] == 0
                  && (this.co[var12].colok <= 14 || this.co[var12].colok >= 33)
                  && (this.co[var12].colok < 39 || this.co[var12].colok >= 46)
                  && this.co[var12].colok < 52) {
                  byte var14 = 0;
                  if (this.co[var12].colok != 2
                     && this.co[var12].colok != 3
                     && this.co[var12].colok != 4
                     && this.co[var12].colok != 7
                     && this.co[var12].colok != 9) {
                     if (var6 == 1
                        && this.co[var12].z > this.co[var21].z
                        && Math.abs(this.co[var12].x - this.co[var21].x) < 1000
                        && (this.co[var12].roofat == 180 || this.co[var12].roofat == 0)) {
                        var14 = 1;
                     }

                     if (var6 == 2
                        && this.co[var12].z < this.co[var21].z
                        && Math.abs(this.co[var12].x - this.co[var21].x) < 1000
                        && (this.co[var12].roofat == 180 || this.co[var12].roofat == 0)) {
                        var14 = 1;
                     }

                     if (var6 == 3
                        && this.co[var12].x > this.co[var21].x
                        && Math.abs(this.co[var12].z - this.co[var21].z) < 1000
                        && (this.co[var12].roofat == 90 || this.co[var12].roofat == -90)) {
                        var14 = 1;
                     }

                     if (var6 == 4
                        && this.co[var12].x < this.co[var21].x
                        && Math.abs(this.co[var12].z - this.co[var21].z) < 1000
                        && (this.co[var12].roofat == 90 || this.co[var12].roofat == -90)) {
                        var14 = 1;
                     }
                  } else {
                     var14 = 2;
                  }

                  if (var14 != 0) {
                     int[] var15 = new int[]{this.co[var12].x + this.atp[this.co[var12].colok][0], this.co[var12].x + this.atp[this.co[var12].colok][2]};
                     int[] var16 = new int[]{this.co[var12].z + this.atp[this.co[var12].colok][1], this.co[var12].z + this.atp[this.co[var12].colok][3]};
                     var9 = this.co[var12].roofat;
                     if (this.co[var12].colok == 2) {
                        var9 += 30;
                     }

                     if (this.co[var12].colok == 3) {
                        var9 -= 30;
                     }

                     if (this.co[var12].colok == 15) {
                        var9 -= 90;
                     }

                     if (this.co[var12].colok == 20) {
                        var9 -= 180;
                     }

                     if (this.co[var12].colok == 26) {
                        var9 -= 90;
                     }

                     this.rot(var15, var16, this.co[var12].x, this.co[var12].z, var9, 2);
                     int var17 = 0;
                     if (var12 != 0) {
                        var17 = this.pyn(var15[0], var7[0], var16[0], var8[0]);
                        if (var17 >= 0 && (var17 < 100 || var14 != 2) && (var17 < var10 || var10 == -1)) {
                           var10 = var17;
                           var11 = var12;
                        }
                     }

                     var17 = this.pyn(var15[1], var7[0], var16[1], var8[0]);
                     if (var17 >= 0 && (var17 < 100 || var14 != 2) && (var17 < var10 || var10 == -1)) {
                        var10 = var17;
                        var11 = var12;
                     }

                     if (var21 != 0) {
                        if (var12 != 0) {
                           var17 = this.pyn(var15[0], var7[1], var16[0], var8[1]);
                           if (var17 >= 0 && (var17 < 100 || var14 != 2) && var17 < var10) {
                              var10 = var17;
                              var11 = var12;
                           }
                        }

                        var17 = this.pyn(var15[1], var7[1], var16[1], var8[1]);
                        if (var17 >= 0 && (var17 < 100 || var14 != 2) && var17 < var10) {
                           var10 = var17;
                           var11 = var12;
                        }
                     }
                  }
               }
            }
         }

         if (var11 == -1) {
            for (int var66 = 0; var66 < this.nob; var66++) {
               boolean var75 = false;
               if (var4 == 2 && var66 == 0) {
                  var75 = true;
               }

               if (var21 != var66
                  && !var75
                  && var1[var66] == 0
                  && (this.co[var66].colok <= 14 || this.co[var66].colok >= 33)
                  && (this.co[var66].colok < 39 || this.co[var66].colok >= 46)
                  && this.co[var66].colok < 52) {
                  int[] var83 = new int[]{this.co[var66].x + this.atp[this.co[var66].colok][0], this.co[var66].x + this.atp[this.co[var66].colok][2]};
                  int[] var85 = new int[]{this.co[var66].z + this.atp[this.co[var66].colok][1], this.co[var66].z + this.atp[this.co[var66].colok][3]};
                  var9 = this.co[var66].roofat;
                  if (this.co[var66].colok == 2) {
                     var9 += 30;
                  }

                  if (this.co[var66].colok == 3) {
                     var9 -= 30;
                  }

                  if (this.co[var66].colok == 15) {
                     var9 -= 90;
                  }

                  if (this.co[var66].colok == 20) {
                     var9 -= 180;
                  }

                  if (this.co[var66].colok == 26) {
                     var9 -= 90;
                  }

                  this.rot(var83, var85, this.co[var66].x, this.co[var66].z, var9, 2);
                  int var88 = 0;
                  if (var66 != 0) {
                     var88 = this.pyn(var83[0], var7[0], var85[0], var8[0]);
                     if (var88 >= 0 && (var88 < var10 || var10 == -1)) {
                        var10 = var88;
                        var11 = var66;
                     }
                  }

                  var88 = this.pyn(var83[1], var7[0], var85[1], var8[0]);
                  if (var88 >= 0 && (var88 < var10 || var10 == -1)) {
                     var10 = var88;
                     var11 = var66;
                  }

                  if (var21 != 0) {
                     if (var66 != 0) {
                        var88 = this.pyn(var83[0], var7[1], var85[0], var8[1]);
                        if (var88 >= 0 && var88 < var10) {
                           var10 = var88;
                           var11 = var66;
                        }
                     }

                     var88 = this.pyn(var83[1], var7[1], var85[1], var8[1]);
                     if (var88 >= 0 && var88 < var10) {
                        var10 = var88;
                        var11 = var66;
                     }
                  }
               }
            }
         }

         if (var11 == -1) {
            var1[0] = 1;
            var5 = true;
         } else {
            var6 = 0;
            if (this.co[var11].colok != 2 && this.co[var11].colok != 3 && this.co[var11].colok != 4 && this.co[var11].colok != 7 && this.co[var11].colok != 9) {
               if ((this.co[var11].roofat == 180 || this.co[var11].roofat == 0) && this.co[var11].z > this.co[var21].z) {
                  var6 = 1;
               }

               if ((this.co[var11].roofat == 180 || this.co[var11].roofat == 0) && this.co[var11].z < this.co[var21].z) {
                  var6 = 2;
               }

               if ((this.co[var11].roofat == 90 || this.co[var11].roofat == -90) && this.co[var11].x > this.co[var21].x) {
                  var6 = 3;
               }

               if ((this.co[var11].roofat == 90 || this.co[var11].roofat == -90) && this.co[var11].x < this.co[var21].x) {
                  var6 = 4;
               }
            }

            if (this.co[var11].colok != 4 && this.co[var11].colok != 7 && this.co[var11].colok != 9) {
               var1[var11] = 1;
            } else {
               var1[var11] = 2;
            }

            if (this.co[var11].colok >= 46 && this.co[var11].colok <= 51) {
               var1[var11] = 6;
            }

            var21 = var11;
            if (var11 == 0) {
               var1[0] = 1;
               var5 = true;
            } else {
               var2[var4] = var11;
               var4++;
            }
         }
      }

      for (int var23 = 0; var23 < this.nob; var23++) {
         if (var1[var23] == 0
            && (this.co[var23].colok <= 14 || this.co[var23].colok >= 33)
            && (this.co[var23].colok < 39 || this.co[var23].colok >= 46)
            && this.co[var23].colok < 52) {
            var2[var4] = var23;
            var4++;
         }
      }

      for (int var24 = 0; var24 < var4; var24++) {
         if (this.co[var2[var24]].colok >= 46 && this.co[var2[var24]].colok <= 51) {
            for (int var26 = var24 + 1; var26 < var4; var26++) {
               int var37 = this.pyn(this.co[var2[var24]].x, this.co[var2[var26]].x, this.co[var2[var24]].z, this.co[var2[var26]].z);
               if (var37 >= 0
                  && (this.co[var2[var26]].colok < 46 || this.co[var2[var24]].colok > 51)
                  && var37 < (this.co[var2[var24]].maxR + this.co[var2[var26]].maxR) / 100 * ((this.co[var2[var24]].maxR + this.co[var2[var26]].maxR) / 100)) {
                  int var45 = var2[var26];

                  for (int var55 = var26; var55 > var24; var55--) {
                     var2[var55] = var2[var55 - 1];
                  }

                  var2[var24] = var45;
                  var1[var2[var24]] = 0;
                  var24++;
               }
            }
         }
      }

      int var25 = 1;

      for (int var27 = 0; var27 < this.cp.nsp; var27++) {
         for (int var38 = 0; var38 < this.nob; var38++) {
            if (this.co[var38].wh == var27 + 1 && (this.co[var38].colok == 30 || this.co[var38].colok == 32 || this.co[var38].colok == 54)) {
               int var46 = -1;
               int var56 = -1;

               for (int var67 = var25; var67 < var4; var67++) {
                  if (this.co[var2[var67]].colok != 30 && this.co[var2[var67]].colok != 32 && this.co[var2[var67]].colok != 54) {
                     int var76 = this.pyn(this.co[var38].x, this.co[var2[var67]].x, this.co[var38].z, this.co[var2[var67]].z);
                     if (var76 >= 0 && (var76 < var46 || var46 == -1)) {
                        var46 = var76;
                        var56 = var67;
                     }
                  }
               }

               if (var56 == -1) {
                  var2[var4] = var38;
                  var25 = var4++;
               } else {
                  var1[var2[var56]] = 0;

                  for (int var68 = var4; var68 > var56; var68--) {
                     var2[var68] = var2[var68 - 1];
                  }

                  var2[var56 + 1] = var38;
                  var25 = var56 + 1;
                  var4++;
               }
            }
         }
      }

      for (int var28 = 0; var28 < this.nob; var28++) {
         if (this.co[var28].wh == 0 && (this.co[var28].colok == 30 || this.co[var28].colok == 32 || this.co[var28].colok == 54)) {
            int var39 = -1;
            int var47 = -1;

            for (int var57 = var25; var57 < var4; var57++) {
               if (this.co[var2[var57]].colok != 30 && this.co[var2[var57]].colok != 32 && this.co[var2[var57]].colok != 54) {
                  int var69 = this.pyn(this.co[var28].x, this.co[var2[var57]].x, this.co[var28].z, this.co[var2[var57]].z);
                  if (var69 >= 0 && (var69 < var39 || var39 == -1)) {
                     var39 = var69;
                     var47 = var57;
                  }
               }
            }

            if (var47 == -1) {
               var2[var4] = var28;
               var4++;
            } else {
               var1[var2[var47]] = 0;

               for (int var58 = var4; var58 > var47; var58--) {
                  var2[var58] = var2[var58 - 1];
               }

               var2[var47 + 1] = var28;
               var4++;
            }
         }
      }

      for (int var29 = 0; var29 < this.nob; var29++) {
         if (this.co[var29].colok == 31) {
            int var40 = -1;
            int var48 = -1;

            for (int var59 = 0; var59 < var4; var59++) {
               int var70 = this.pyn(this.co[var29].x, this.co[var2[var59]].x, this.co[var29].z, this.co[var2[var59]].z);
               if (var70 >= 0 && (var70 < var40 || var40 == -1)) {
                  var40 = var70;
                  var48 = var59;
               }
            }

            if (var48 == -1) {
               var2[var4] = var29;
               var4++;
            } else {
               for (int var60 = var4; var60 > var48; var60--) {
                  var2[var60] = var2[var60 - 1];
               }

               var2[var48] = var29;
               var4++;
            }
         }
      }

      for (int var30 = 0; var30 < this.nob; var30++) {
         if (this.co[var30].colok == 15
            || this.co[var30].colok == 27
            || this.co[var30].colok == 28
            || this.co[var30].colok == 41
            || this.co[var30].colok == 44
            || this.co[var30].colok == 52
            || this.co[var30].colok == 53) {
            int var41 = -1;

            for (int var49 = 0; var49 < var4; var49++) {
               if ((this.co[var2[var49]].colok <= 14 || this.co[var2[var49]].colok >= 33) && this.co[var2[var49]].colok < 39) {
                  int var61 = this.pyn(this.co[var30].x, this.co[var2[var49]].x, this.co[var30].z, this.co[var2[var49]].z);
                  if (var61 >= 0 && var61 < (this.co[var30].maxR + this.co[var2[var49]].maxR) / 100 * ((this.co[var30].maxR + this.co[var2[var49]].maxR) / 100)
                     )
                   {
                     var41 = var49;
                  }
               }
            }

            if (var41 == -1) {
               var2[var4] = var30;
               var4++;
            } else {
               for (int var50 = var4; var50 > var41; var50--) {
                  var2[var50] = var2[var50 - 1];
               }

               var2[var41 + 1] = var30;
               var4++;
            }
         }
      }

      for (int var31 = 0; var31 < this.nob; var31++) {
         if (this.co[var31].colok >= 16 && this.co[var31].colok <= 25
            || this.co[var31].colok == 40
            || this.co[var31].colok == 42
            || this.co[var31].colok == 43
            || this.co[var31].colok == 45) {
            int var42 = -1;

            for (int var51 = 0; var51 < var4; var51++) {
               if ((this.co[var2[var51]].colok <= 14 || this.co[var2[var51]].colok >= 33) && this.co[var2[var51]].colok < 39) {
                  int var62 = this.pyn(this.co[var31].x, this.co[var2[var51]].x, this.co[var31].z, this.co[var2[var51]].z);
                  if (var62 >= 0 && var62 < (this.co[var31].maxR + this.co[var2[var51]].maxR) / 100 * ((this.co[var31].maxR + this.co[var2[var51]].maxR) / 100)
                     )
                   {
                     if (var1[var2[var51]] != 0) {
                        var1[var2[var51]] = 0;
                        if (this.co[var31].colok != 20) {
                           var1[var31] = 3;
                        } else {
                           var1[var31] = 5;
                        }
                     }

                     var42 = var51;
                  }
               }
            }

            if (var42 == -1) {
            }

            if (var42 == -1) {
               var2[var4] = var31;
               var4++;
            } else {
               for (int var52 = var4; var52 > var42; var52--) {
                  var2[var52] = var2[var52 - 1];
               }

               var2[var42 + 1] = var31;
               var4++;
            }
         }
      }

      for (int var32 = 0; var32 < this.nob; var32++) {
         if (this.co[var32].colok == 26 || this.co[var32].colok == 39) {
            boolean var43 = false;
            if (Math.random() > Math.random()) {
               var43 = true;
               if (this.co[var32].colok == 39) {
                  if (Math.random() > Math.random()) {
                     var43 = false;
                  } else if (Math.random() > Math.random()) {
                     var43 = false;
                  }
               }
            }

            int var53 = -1;

            for (int var63 = 0; var63 < var4; var63++) {
               if ((this.co[var2[var63]].colok <= 14 || this.co[var2[var63]].colok >= 33) && this.co[var2[var63]].colok < 39) {
                  int var71 = this.pyn(this.co[var32].x, this.co[var2[var63]].x, this.co[var32].z, this.co[var2[var63]].z);
                  if (var71 >= 0 && var71 < (this.co[var32].maxR + this.co[var2[var63]].maxR) / 100 * ((this.co[var32].maxR + this.co[var2[var63]].maxR) / 100)
                     )
                   {
                     boolean var77 = false;
                     if (this.co[var32].colok == 26) {
                        if (this.co[var32].roofat == 90 && this.co[var2[var63]].x > this.co[var32].x) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == -90 && this.co[var2[var63]].x < this.co[var32].x) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == 0 && this.co[var2[var63]].z < this.co[var32].z) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == 180 && this.co[var2[var63]].z > this.co[var32].z) {
                           var77 = true;
                        }
                     }

                     if (this.co[var32].colok == 39) {
                        if (this.co[var32].roofat == 90 && this.co[var2[var63]].z > this.co[var32].z) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == -90 && this.co[var2[var63]].z < this.co[var32].z) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == 0 && this.co[var2[var63]].x > this.co[var32].x) {
                           var77 = true;
                        }

                        if (this.co[var32].roofat == 180 && this.co[var2[var63]].x < this.co[var32].x) {
                           var77 = true;
                        }
                     }

                     if (var77) {
                        if (var1[var2[var63]] == 1 && var43) {
                           var1[var2[var63]] = 0;
                           var1[var32] = 4;
                        }

                        var53 = var63;
                     }
                  }
               }
            }

            if (var53 == -1) {
               var2[var4] = var32;
               var4++;
            } else {
               for (int var64 = var4; var64 > var53; var64--) {
                  var2[var64] = var2[var64 - 1];
               }

               var2[var53 + 1] = var32;
               var4++;
            }
         }
      }

      for (int var33 = 0; var33 < this.nob; var33++) {
         if (this.co[var33].colok >= 55 && this.co[var33].colok <= 65 || this.co[var33].colok == 66) {
            var2[var4] = var33;
            var4++;
         }
      }

      int var34 = 0;
      int var44 = 0;
      int var54 = 0;
      int var65 = 0;
      this.bstage = "";

      for (int var72 = 0; var72 < var4; var72++) {
         if (this.co[var2[var72]].colok != 30
            && this.co[var2[var72]].colok != 31
            && this.co[var2[var72]].colok != 32
            && this.co[var2[var72]].colok != 54
            && this.co[var2[var72]].colok != 66) {
            String var78 = "";
            if (var1[var2[var72]] == 1) {
               var78 = "p";
            }

            if (var1[var2[var72]] == 2) {
               var78 = "pt";
            }

            if (var1[var2[var72]] == 3) {
               var78 = "pr";
            }

            if (var1[var2[var72]] == 4) {
               var78 = "ph";
            }

            if (var1[var2[var72]] == 5) {
               var78 = "pl";
            }

            if (var1[var2[var72]] == 6) {
               var78 = "pr";
            }

            this.bstage = this.bstage
               + "set("
               + (this.co[var2[var72]].colok + 10)
               + ","
               + this.co[var2[var72]].x
               + ","
               + this.co[var2[var72]].z
               + ","
               + this.co[var2[var72]].roofat
               + ")"
               + var78
               + "\r\n";
         }

         if (this.co[var2[var72]].colok == 30 || this.co[var2[var72]].colok == 32) {
            if (this.co[var2[var72]].roofat == 180) {
               this.co[var2[var72]].roofat = 0;
            }

            String var79 = "";
            if (this.co[var2[var72]].wh != 0) {
               var79 = "r";
            }

            this.bstage = this.bstage
               + "chk("
               + (this.co[var2[var72]].colok + 10)
               + ","
               + this.co[var2[var72]].x
               + ","
               + this.co[var2[var72]].z
               + ","
               + this.co[var2[var72]].roofat
               + ")"
               + var79
               + "\r\n";
         }

         if (this.co[var2[var72]].colok == 54) {
            if (this.co[var2[var72]].roofat == 180) {
               this.co[var2[var72]].roofat = 0;
            }

            String var80 = "";
            if (this.co[var2[var72]].wh != 0) {
               var80 = "r";
            }

            this.bstage = this.bstage
               + "chk("
               + (this.co[var2[var72]].colok + 10)
               + ","
               + this.co[var2[var72]].x
               + ","
               + this.co[var2[var72]].z
               + ","
               + this.co[var2[var72]].roofat
               + ","
               + this.co[var2[var72]].y
               + ")"
               + var80
               + "\r\n";
         }

         if (this.co[var2[var72]].colok == 31) {
            this.bstage = this.bstage
               + "fix("
               + (this.co[var2[var72]].colok + 10)
               + ","
               + this.co[var2[var72]].x
               + ","
               + this.co[var2[var72]].z
               + ","
               + this.co[var2[var72]].y
               + ","
               + this.co[var2[var72]].roofat
               + ")\r\n";
         }

         if (this.co[var2[var72]].colok == 66) {
            this.bstage = this.bstage
               + "pile("
               + this.co[var2[var72]].srz
               + ","
               + this.co[var2[var72]].srx
               + ","
               + this.co[var2[var72]].sry
               + ","
               + this.co[var2[var72]].x
               + ","
               + this.co[var2[var72]].z
               + ")\r\n";
         }

         if (this.co[var2[var72]].x + this.co[var2[var72]].maxR > var34) {
            var34 = this.co[var2[var72]].x + this.co[var2[var72]].maxR;
         }

         if (this.co[var2[var72]].x - this.co[var2[var72]].maxR < var54) {
            var54 = this.co[var2[var72]].x - this.co[var2[var72]].maxR;
         }

         if (this.co[var2[var72]].z + this.co[var2[var72]].maxR > var44) {
            var44 = this.co[var2[var72]].z + this.co[var2[var72]].maxR;
         }

         if (this.co[var2[var72]].z - this.co[var2[var72]].maxR < var65) {
            var65 = this.co[var2[var72]].z - this.co[var2[var72]].maxR;
         }
      }

      int var73 = var54 - 0;
      int var81 = var34 + 0;
      int var84 = (int)((var81 - var73) / 4800.0F) + 1;
      int var86 = (var84 * 4800 - (var81 - var73)) / 2;
      var73 -= var86;
      var81 += var86;
      int var93 = var73 + 2400;
      int var98 = var65 - 0;
      int var18 = var44 + 0;
      int var19 = (int)((var18 - var98) / 4800.0F) + 1;
      var86 = (var19 * 4800 - (var18 - var98)) / 2;
      var98 -= var86;
      var18 += var86;
      int var20 = var98 + 2400;
      this.bstage = this.bstage
         + "\r\nmaxl("
         + var19
         + ","
         + var73
         + ","
         + var20
         + ")\r\nmaxb("
         + var84
         + ","
         + var98
         + ","
         + var93
         + ")\r\nmaxr("
         + var19
         + ","
         + var81
         + ","
         + var20
         + ")\r\nmaxt("
         + var84
         + ","
         + var18
         + ","
         + var93
         + ")\r\n";
   }

   public void savefile() {
      try {
         File var1 = new File("" + Madness.fpath + "mystages/");
         if (!var1.exists()) {
            var1.mkdirs();
         }

         var1 = new File("" + Madness.fpath + "mystages/" + this.stagename + ".txt");
         BufferedWriter var2 = new BufferedWriter(new FileWriter(var1));
         var2.write(this.tstage);
         var2.write(this.bstage);
         var2.close();
         Object var5 = null;
      } catch (Exception var3) {
         JOptionPane.showMessageDialog(null, "Unable to save file! Error Deatials:\n" + var3, "Stage Maker", 1);
      }

      this.savesettings();
   }

   public void renstage(String var1) {
      if (var1.equals("")) {
         JOptionPane.showMessageDialog(null, "Please Enter a New Stage Name!\n", "Stage Maker", 1);
      } else {
         try {
            File var2 = new File("" + Madness.fpath + "mystages/" + this.stagename + ".txt");
            File var3 = new File("" + Madness.fpath + "mystages/" + var1 + ".txt");
            if (var2.renameTo(var3)) {
               this.stagename = var1;
               this.sfase = 0;
               this.hidefields();
               this.tabed = -2;
            } else {
               JOptionPane.showMessageDialog(null, "Unable to rename stage to: '" + var1 + "', possible reason: stage name already used!\n", "Stage Maker", 1);
            }
         } catch (Exception var4) {
            JOptionPane.showMessageDialog(null, "Unable to rename file! Error Deatials:\n" + var4, "Stage Maker", 1);
         }
      }
   }

   public void delstage(String var1) {
      try {
         File var2 = new File("" + Madness.fpath + "mystages/" + var1 + ".txt");
         var2.delete();
         this.slstage.remove(var1);
         this.slstage.select(0);
      } catch (Exception var3) {
         JOptionPane.showMessageDialog(null, "Unable to delete file! Error Deatials:\n" + var3, "Stage Maker", 1);
      }
   }

   public void deltrack() {
      try {
         File var1 = new File("" + Madness.fpath + "mystages/mymusic/" + this.tracks.getSelectedItem() + ".zip");
         var1.delete();
         if (this.trackname.equals(this.tracks.getSelectedItem())) {
            this.trackname = "";
            this.sortop();
            this.savefile();
         }

         this.tracks.remove(this.tracks.getSelectedItem());
         this.tracks.select(0);
      } catch (Exception var2) {
         JOptionPane.showMessageDialog(null, "Unable to delete file! Error Deatials:\n" + var2, "Stage Maker", 1);
      }
   }

   public void loadsettings() {
      try {
         File var1 = new File("" + Madness.fpath + "mystages/settings.data");
         if (var1.exists()) {
            BufferedReader var2 = new BufferedReader(new FileReader(var1));
            String var3 = var2.readLine();
            if (var3 != null) {
               this.sstage = var3;
               this.stagename = this.sstage;
            }

            var3 = var2.readLine();
            if (var3 != null) {
               this.suser = var3;
               if (!this.suser.equals("Horaks")) {
                  this.tnick.setText(this.suser);
               }
            }

            var2.close();
            Object var5 = null;
         }
      } catch (Exception var4) {
      }
   }

   public void savesettings() {
      if (!this.sstage.equals(this.stagename) || !this.suser.equals(this.tnick.getText())) {
         String var1 = "" + this.stagename + "\n" + this.tnick.getText() + "\n\n";
         this.sstage = this.stagename;
         this.suser = this.tnick.getText();

         try {
            File var2 = new File("" + Madness.fpath + "mystages/");
            if (!var2.exists()) {
               var2.mkdirs();
            }

            var2 = new File("" + Madness.fpath + "mystages/settings.data");
            BufferedWriter var3 = new BufferedWriter(new FileWriter(var2));
            var3.write(var1);
            var3.close();
            Object var6 = null;
         } catch (Exception var4) {
         }
      }
   }

   public void fixtext(TextField var1) {
      String var2 = var1.getText();
      var2 = var2.replace('"', '#');
      String var3 = "\\";
      String var4 = "";
      int var5 = 0;
      int var6 = -1;
      this.rd.setFont(new Font("Arial", 1, 12));

      for (this.ftm = this.rd.getFontMetrics(); var5 < var2.length(); var5++) {
         String var7 = "" + var2.charAt(var5);
         if (!var7.equals("|")
            && !var7.equals(",")
            && !var7.equals("(")
            && !var7.equals(")")
            && !var7.equals("#")
            && !var7.equals(var3)
            && !var7.equals("!")
            && !var7.equals("?")
            && !var7.equals("~")
            && !var7.equals(".")
            && !var7.equals("@")
            && !var7.equals("$")
            && !var7.equals("%")
            && !var7.equals("^")
            && !var7.equals("&")
            && !var7.equals("*")
            && !var7.equals("+")
            && !var7.equals("=")
            && !var7.equals(">")
            && !var7.equals("<")
            && !var7.equals("/")
            && !var7.equals(";")
            && !var7.equals(":")
            && this.ftm.stringWidth(var4) <= 274) {
            var4 = var4 + var7;
         } else {
            var6 = var5;
         }
      }

      if (var6 != -1) {
         var1.setText(var4);
         var1.select(var6, var6);
      }
   }

   public void rot(int[] var1, int[] var2, int var3, int var4, int var5, int var6) {
      if (var5 != 0) {
         for (int var7 = 0; var7 < var6; var7++) {
            int var8 = var1[var7];
            int var9 = var2[var7];
            var1[var7] = var3 + (int)((var8 - var3) * this.m.cos(var5) - (var9 - var4) * this.m.sin(var5));
            var2[var7] = var4 + (int)((var8 - var3) * this.m.sin(var5) + (var9 - var4) * this.m.cos(var5));
         }
      }
   }

   public int xs(int var1, int var2) {
      if (var2 < this.m.cz) {
         var2 = this.m.cz;
      }

      return (var2 - this.m.focus_point) * (this.m.cx - var1) / var2 + var1;
   }

   public int ys(int var1, int var2) {
      if (var2 < this.m.cz) {
         var2 = this.m.cz;
      }

      return (var2 - this.m.focus_point) * (this.m.cy - var1) / var2 + var1;
   }

   public int py(int var1, int var2, int var3, int var4) {
      return (int)Math.sqrt((var1 - var2) * (var1 - var2) + (var3 - var4) * (var3 - var4));
   }

   public int pyn(int var1, int var2, int var3, int var4) {
      return (var1 - var2) / 100 * ((var1 - var2) / 100) + (var3 - var4) / 100 * ((var3 - var4) / 100);
   }

   public String getstring(String var1, String var2, int var3) {
      int var5 = 0;
      String var7 = "";

      for (int var4 = var1.length() + 1; var4 < var2.length(); var4++) {
         String var6 = "" + var2.charAt(var4);
         if (var6.equals(",") || var6.equals(")")) {
            var5++;
            var4++;
         }

         if (var5 == var3) {
            var7 = var7 + var2.charAt(var4);
         }
      }

      return var7;
   }

   public int getint(String var1, String var2, int var3) {
      int var5 = 0;
      String var7 = "";

      for (int var4 = var1.length() + 1; var4 < var2.length(); var4++) {
         String var6 = "" + var2.charAt(var4);
         if (var6.equals(",") || var6.equals(")")) {
            var5++;
            var4++;
         }

         if (var5 == var3) {
            var7 = var7 + var2.charAt(var4);
         }
      }

      return Integer.valueOf(var7);
   }

   public Image getImage(String var1) {
      Image var2 = Toolkit.getDefaultToolkit().createImage(var1);
      MediaTracker var3 = new MediaTracker(this);
      var3.addImage(var2, 0);

      try {
         var3.waitForID(0);
      } catch (Exception var5) {
      }

      return var2;
   }

   public int servervalue(String var1, int var2) {
      int var3 = -1;

      try {
         int var4 = 0;
         int var5 = 0;
         byte var6 = 0;
         String var7 = "";
         String var8 = "";

         while (var4 < var1.length() && var6 != 2) {
            var7 = "" + var1.charAt(var4);
            if (var7.equals("|")) {
               if (var6 == 1 || ++var5 > var2) {
                  var6 = 2;
               }
            } else if (var5 == var2) {
               var8 = var8 + var7;
               var6 = 1;
            }

            var4++;
         }

         if (var8.equals("")) {
            var8 = "-1";
         }

         var3 = Integer.valueOf(var8);
      } catch (Exception var9) {
      }

      return var3;
   }

   public String serverSvalue(String var1, int var2) {
      String var3 = "";

      try {
         int var4 = 0;
         int var5 = 0;
         byte var6 = 0;
         String var7 = "";
         String var8 = "";

         while (var4 < var1.length() && var6 != 2) {
            var7 = "" + var1.charAt(var4);
            if (var7.equals("|")) {
               if (var6 == 1 || ++var5 > var2) {
                  var6 = 2;
               }
            } else if (var5 == var2) {
               var8 = var8 + var7;
               var6 = 1;
            }

            var4++;
         }

         var3 = var8;
      } catch (Exception var9) {
      }

      return var3;
   }

   public int getvalue(String var1, String var2, int var3) {
      int var5 = 0;
      String var7 = "";

      for (int var4 = var1.length() + 1; var4 < var2.length(); var4++) {
         String var6 = "" + var2.charAt(var4);
         if (var6.equals(",") || var6.equals(")")) {
            var5++;
            var4++;
         }

         if (var5 == var3) {
            var7 = var7 + var2.charAt(var4);
         }
      }

      return Float.valueOf(var7).intValue();
   }

   public String getSvalue(String var1, String var2, int var3) {
      String var4 = "";
      int var5 = 0;

      for (int var6 = var1.length() + 1; var6 < var2.length() && var5 <= var3; var6++) {
         String var7 = "" + var2.charAt(var6);
         if (var7.equals(",") || var7.equals(")")) {
            var5++;
         } else if (var5 == var3) {
            var4 = var4 + var7;
         }
      }

      return var4;
   }

   public boolean button(String var1, int var2, int var3, int var4, boolean var5) {
      this.rd.setFont(new Font("Arial", 1, 12));
      this.ftm = this.rd.getFontMetrics();
      int var6 = this.ftm.stringWidth(var1);
      boolean var7 = false;
      boolean var8 = false;
      if (var1.equals(" Cancel ") && this.epart && Math.abs(this.xm - var2) < var6 / 2 + 12 && Math.abs(this.ym - var3 + 5) < 10) {
         this.overcan = true;
      }

      if (Math.abs(this.xm - var2) < var6 / 2 + 12 && Math.abs(this.ym - var3 + 5) < 10 && this.mouses == 1) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (Math.abs(this.xm - var2) < var6 / 2 + 12 && Math.abs(this.ym - var3 + 5) < 10 && this.mouses == -1) {
         this.mouses = 0;
         var8 = true;
      }

      boolean var9 = false;
      if (var5) {
         if (this.tab == 0) {
            this.rd.setColor(new Color(207, 207, 207));
         }

         if (this.tab == 1) {
            this.rd.setColor(new Color(200, 200, 200));
         }

         if (this.tab == 2) {
            this.rd.setColor(new Color(170, 170, 170));
         }

         if (this.tab != 3) {
            this.rd.drawRect(var2 - var6 / 2 - 15, var3 - (22 - var4), var6 + 29, 34 - var4 * 2);
            if (var4 == 2 && this.tab == 1) {
               this.rd.setColor(new Color(220, 220, 220));
               this.rd.fillRect(var2 - var6 / 2 - 15, var3 - (22 - var4), var6 + 29, 34 - var4 * 2);
            }
         } else {
            var9 = true;
         }
      }

      if (!var7) {
         this.rd.setColor(new Color(220, 220, 220));
         if (var9) {
            this.rd.setColor(new Color(230, 230, 230));
         }

         this.rd.fillRect(var2 - var6 / 2 - 10, var3 - (17 - var4), var6 + 20, 25 - var4 * 2);
         this.rd.setColor(new Color(240, 240, 240));
         if (var9) {
            this.rd.setColor(new Color(255, 255, 255));
         }

         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (17 - var4), var2 + var6 / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (18 - var4), var2 + var6 / 2 + 10, var3 - (18 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var6 / 2 - 9, var3 - (19 - var4), var2 + var6 / 2 + 9, var3 - (19 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         if (var9) {
            this.rd.setColor(new Color(192, 192, 192));
         }

         this.rd.drawLine(var2 + var6 / 2 + 10, var3 - (17 - var4), var2 + var6 / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + var6 / 2 + 11, var3 - (17 - var4), var2 + var6 / 2 + 11, var3 + (7 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         if (var9) {
            this.rd.setColor(new Color(192, 192, 192));
         }

         this.rd.drawLine(var2 + var6 / 2 + 12, var3 - (16 - var4), var2 + var6 / 2 + 12, var3 + (6 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 + (7 - var4), var2 + var6 / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 + (8 - var4), var2 + var6 / 2 + 10, var3 + (8 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 - var6 / 2 - 9, var3 + (9 - var4), var2 + var6 / 2 + 9, var3 + (9 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         if (var9) {
            this.rd.setColor(new Color(255, 255, 255));
         }

         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (17 - var4), var2 - var6 / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 11, var3 - (17 - var4), var2 - var6 / 2 - 11, var3 + (7 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var6 / 2 - 12, var3 - (16 - var4), var2 - var6 / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         if (var1.equals("  Keyboard Controls  ")) {
            this.rd.setColor(new Color(100, 100, 100));
         }

         this.rd.drawString(var1, var2 - var6 / 2, var3);
      } else {
         this.rd.setColor(new Color(220, 220, 220));
         this.rd.fillRect(var2 - var6 / 2 - 10, var3 - (17 - var4), var6 + 20, 25 - var4 * 2);
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (17 - var4), var2 + var6 / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (18 - var4), var2 + var6 / 2 + 10, var3 - (18 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 9, var3 - (19 - var4), var2 + var6 / 2 + 9, var3 - (19 - var4));
         this.rd.setColor(new Color(247, 247, 247));
         this.rd.drawLine(var2 + var6 / 2 + 10, var3 - (17 - var4), var2 + var6 / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + var6 / 2 + 11, var3 - (17 - var4), var2 + var6 / 2 + 11, var3 + (7 - var4));
         this.rd.drawLine(var2 + var6 / 2 + 12, var3 - (16 - var4), var2 + var6 / 2 + 12, var3 + (6 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 + (7 - var4), var2 + var6 / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 + (8 - var4), var2 + var6 / 2 + 10, var3 + (8 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 9, var3 + (9 - var4), var2 + var6 / 2 + 9, var3 + (9 - var4));
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - var6 / 2 - 10, var3 - (17 - var4), var2 - var6 / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 11, var3 - (17 - var4), var2 - var6 / 2 - 11, var3 + (7 - var4));
         this.rd.drawLine(var2 - var6 / 2 - 12, var3 - (16 - var4), var2 - var6 / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         if (var1.equals("  Keyboard Controls  ")) {
            this.rd.setColor(new Color(100, 100, 100));
         }

         this.rd.drawString(var1, var2 - var6 / 2 + 1, var3 + 1);
      }

      return var8;
   }

   public boolean ovbutton(String var1, int var2, int var3) {
      this.rd.setFont(new Font("Arial", 0, 12));
      this.ftm = this.rd.getFontMetrics();
      if (var1.equals("X") || var1.equals("Download")) {
         this.rd.setFont(new Font("Arial", 1, 12));
         this.ftm = this.rd.getFontMetrics();
      }

      int var4 = this.ftm.stringWidth(var1);
      byte var5 = 4;
      boolean var6 = false;
      boolean var7 = false;
      if (Math.abs(this.xm - var2) < var4 / 2 + 12 && Math.abs(this.ym - var3 + 5) < 10 && this.mouses == 1) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (Math.abs(this.xm - var2) < var4 / 2 + 12 && Math.abs(this.ym - var3 + 5) < 10 && this.mouses == -1) {
         this.mouses = 0;
         var7 = true;
      }

      if (!var6) {
         this.rd.setColor(new Color(220, 220, 220));
         this.rd.fillRect(var2 - var4 / 2 - 10, var3 - (17 - var5), var4 + 20, 25 - var5 * 2);
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (17 - var5), var2 + var4 / 2 + 10, var3 - (17 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (18 - var5), var2 + var4 / 2 + 10, var3 - (18 - var5));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var4 / 2 - 9, var3 - (19 - var5), var2 + var4 / 2 + 9, var3 - (19 - var5));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 + var4 / 2 + 10, var3 - (17 - var5), var2 + var4 / 2 + 10, var3 + (7 - var5));
         this.rd.drawLine(var2 + var4 / 2 + 11, var3 - (17 - var5), var2 + var4 / 2 + 11, var3 + (7 - var5));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 + var4 / 2 + 12, var3 - (16 - var5), var2 + var4 / 2 + 12, var3 + (6 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 + (7 - var5), var2 + var4 / 2 + 10, var3 + (7 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 + (8 - var5), var2 + var4 / 2 + 10, var3 + (8 - var5));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 - var4 / 2 - 9, var3 + (9 - var5), var2 + var4 / 2 + 9, var3 + (9 - var5));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (17 - var5), var2 - var4 / 2 - 10, var3 + (7 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 11, var3 - (17 - var5), var2 - var4 / 2 - 11, var3 + (7 - var5));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - var4 / 2 - 12, var3 - (16 - var5), var2 - var4 / 2 - 12, var3 + (6 - var5));
         this.rd.setColor(new Color(0, 0, 0));
         if (var1.equals("X")) {
            this.rd.setColor(new Color(255, 0, 0));
         }

         if (var1.equals("Download")) {
            this.rd.setColor(new Color(0, 64, 128));
         }

         this.rd.drawString(var1, var2 - var4 / 2, var3);
      } else {
         this.rd.setColor(new Color(220, 220, 220));
         this.rd.fillRect(var2 - var4 / 2 - 10, var3 - (17 - var5), var4 + 20, 25 - var5 * 2);
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (17 - var5), var2 + var4 / 2 + 10, var3 - (17 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (18 - var5), var2 + var4 / 2 + 10, var3 - (18 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 9, var3 - (19 - var5), var2 + var4 / 2 + 9, var3 - (19 - var5));
         this.rd.setColor(new Color(247, 247, 247));
         this.rd.drawLine(var2 + var4 / 2 + 10, var3 - (17 - var5), var2 + var4 / 2 + 10, var3 + (7 - var5));
         this.rd.drawLine(var2 + var4 / 2 + 11, var3 - (17 - var5), var2 + var4 / 2 + 11, var3 + (7 - var5));
         this.rd.drawLine(var2 + var4 / 2 + 12, var3 - (16 - var5), var2 + var4 / 2 + 12, var3 + (6 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 + (7 - var5), var2 + var4 / 2 + 10, var3 + (7 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 + (8 - var5), var2 + var4 / 2 + 10, var3 + (8 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 9, var3 + (9 - var5), var2 + var4 / 2 + 9, var3 + (9 - var5));
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - var4 / 2 - 10, var3 - (17 - var5), var2 - var4 / 2 - 10, var3 + (7 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 11, var3 - (17 - var5), var2 - var4 / 2 - 11, var3 + (7 - var5));
         this.rd.drawLine(var2 - var4 / 2 - 12, var3 - (16 - var5), var2 - var4 / 2 - 12, var3 + (6 - var5));
         this.rd.setColor(new Color(0, 0, 0));
         if (var1.equals("X")) {
            this.rd.setColor(new Color(255, 0, 0));
         }

         if (var1.equals("Download")) {
            this.rd.setColor(new Color(0, 64, 128));
         }

         this.rd.drawString(var1, var2 - var4 / 2 + 1, var3 + 1);
      }

      return var7;
   }

   public void openlink() {
      Madness.openurl("http://www.needformadness.com/developer/help.html");
   }

   public void openhlink() {
      Madness.openurl("http://www.needformadness.com/developer/");
   }
}
