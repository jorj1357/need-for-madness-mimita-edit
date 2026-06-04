import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

public class CarMaker extends Applet implements Runnable, ActionListener {
   Graphics2D rd;
   Image offImage;
   Thread thredo;
   boolean exwist = false;
   FontMetrics ftm;
   int apx = 0;
   int apy = 0;
   boolean focuson = true;
   Image[] btgame = new Image[2];
   Image logo;
   boolean onbtgame = false;
   int tab = 0;
   int tabed = -1;
   boolean loadedfile = false;
   String carname = "";
   String scar = "";
   String suser = "Horaks";
   String sfont = "Monospaced";
   int sthm = 0;
   int sfase = 0;
   Smenu slcar = new Smenu(2000);
   boolean tutok = false;
   int flk = 0;
   boolean changed = false;
   String lastedo = "";
   boolean prefs = false;
   boolean mirror = false;
   PopupMenu popupMenu;
   TextArea editor = new TextArea(20, 20);
   TextField srch = new TextField("", 15);
   TextField rplc = new TextField("", 15);
   Smenu fontsel = new Smenu(40);
   String cfont = "Monospaced";
   Smenu ctheme = new Smenu(40);
   Color defb;
   Color deff;
   int cthm = 0;
   int cntprf = 0;
   int cntpls = 0;
   int cntchk = 0;
   int npolys = 0;
   boolean tomany = false;
   int ox = 335;
   int oy = 40;
   int oz = 800;
   int oxz = -90;
   int oxy = 0;
   int ozy = 0;
   Medium m = new Medium();
   Trackers t = new Trackers();
   ContO o;
   ContO[] compo = new ContO[16];
   boolean right = false;
   boolean left = false;
   boolean up = false;
   boolean down = false;
   boolean rotl = false;
   boolean rotr = false;
   boolean plus = false;
   boolean minus = false;
   boolean in = false;
   boolean out = false;
   boolean pflk = false;
   boolean breakbond = false;
   int polynum = -1;
   int prflk = 0;
   int bfo = 0;
   int dtab = 0;
   int dtabed = -1;
   int mouseon = -1;
   String fcol = "";
   String ofcol = "";
   String scol = "";
   String oscol = "";
   float[] fhsb = new float[]{0.5F, 0.5F, 0.5F};
   float[] shsb = new float[]{0.5F, 0.5F, 0.5F};
   int[] scale = new int[]{100, 100, 100};
   int[] oscale = new int[]{100, 100, 100};
   Smenu compcar = new Smenu(40);
   int compsel = 0;
   int[] adna = new int[]{276, 276, 276, 276, 276, 276};
   boolean changed2 = false;
   TextField[] wv = new TextField[16];
   boolean defnow = false;
   String aply1 = "";
   String aply2 = "";
   boolean aplyd1 = false;
   boolean aplyd2 = false;
   boolean forwheels = false;
   Smenu cls = new Smenu(40);
   Smenu simcar = new Smenu(40);
   int[] stat = new int[]{100, 100, 100, 100, 100};
   int[] rstat = new int[]{0, 0, 0, 0, 0};
   int[][] carstat = new int[][]{
      {110, 81, 131, 98, 100},
      {200, 200, 88, 16, 16},
      {108, 80, 93, 114, 125},
      {146, 119, 100, 83, 72},
      {109, 85, 141, 96, 93},
      {128, 98, 102, 109, 123},
      {115, 139, 96, 117, 133},
      {120, 81, 145, 126, 128},
      {140, 122, 101, 113, 124},
      {110, 144, 100, 154, 92},
      {133, 122, 144, 115, 126},
      {107, 96, 96, 192, 189},
      {192, 200, 106, 92, 90},
      {88, 104, 88, 200, 200},
      {148, 150, 197, 95, 90},
      {112, 128, 120, 192, 128}
   };
   int carsel = 0;
   int clsel = 0;
   boolean statdef = false;
   int pfase = 0;
   int[] phys = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
   int[] rphys = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
   int[] crash = new int[]{50, 50, 50};
   int[] rcrash = new int[]{50, 50, 50};
   String[] pname = new String[]{
      "Handbrake",
      "Turning Sensitivity",
      "Tire Grip",
      "Bouncing",
      "Empty",
      "Lifts Others",
      "Gets Lifted",
      "Pushes Others",
      "Gets Pushed",
      "Aerial Rotation Speed",
      "Aerial Control/Gliding"
   };
   int[] pnx = new int[]{62, 20, 76, 71, 60, 38, 44, 20, 33, 320, 324};
   String[] usage = new String[]{
      "Handbrake:\nThis defines the hand braking power of the car.\nThe more handbrake the car has the faster it brakes when you press Spacebar while driving.\nBut also the lesser the Handbrake the more the car can drift when you press Spacebar.\n\n",
      "Turning Sensitivity:\nThis defines how fast the car turns (or how fast the wheels respond to turning).\nThe more turning sensitive the faster the car turns and responds.\n\nWhen designing a fast car that is more racing oriented high turning sensitivity is     \nrecommended for the car to be able to take sharp and quick turns.\nHowever too much turning sensitivity can make the car hard to drive!\n\nWhen designing a slower and bigger car (like El King) lower turning sensitivity is\nrecommended for a more realistic effect.\n\n",
      "Tires Grip:\nThis defines the griping power of the car’s wheels on the ground.\n\nThe more griping the more the cars sticks to track.\nThe less gripping the more the car drifts in the turns.\n\nSome drifting can be helpful as it makes the car drive smoother while less drifting can\nmake the car more irritable, it depends on how you like to drive the car and how it\nfeels for you.\n\n",
      "Bouncing:\nThis defines how the car bounces back when it hits the ground or obstacles.\n\nBouncing can help when performing stunts as when you land up side down\nif the car bounces it can be filliped over before landing again to avoid a 'bad landing'.\n\nHowever bouncing is not helpful in controlling the car and in racing.\n\n",
      "If you can read this then you are decompiling the code! Please don’t bother trying to allow the car maker to give you max stats, the stats get check on the server before the car can be accepted. Just to save you sometime.",
      "Lifts Others:\nThis defines if the car lifts up other cars when it collides with them from the front and\nhow high it can lift them.\n\nDoes the car have a pointy nose like MAX Revenge, Radical One or La Vita Crab, a\npointy nose/front part that can go under the wheels of other cars and lift them?\nIf so then give it some Lifts Others.\n\nIf it has a nose/front part that is a block like most cars then give it 0 Lifts Others.\n\n",
      "Gets Lifted:\nThis defines if the car can get lifted over other cars when it collides with them and how\nhigh it can get lifted.\n\nIs the car higher off the ground like Wow Caninaro or has big wheels like Dr Monstaa,\nshould its jump over cars when it collides with them?\nIf so then give it some Gets Lifted depending on how high it should go.\n\nIf the car is lower to the ground like most cars then it should have 0 Gets Lifted\n\n",
      "Pushes Others:\nThis defines if the car pushes other cars away when it collides with them and how far it\ncan push them.\n\nIs the car a heavy car with a strong body like MASHEEN or El King, where when it\ncollides with other cars it pushes them away?\nOr does the car have special bumpers or body parts for pushing cars away like Sword of\nJustice has?\nIf so then give it some Pushes Others depending how strong you think it can push cars.\n\nIf it is a car like any other car, with an average weight and body strength then you should\ngive it 0 Pushes Others.\n\n",
      "Gets Pushed:\nThis defines if the car gets pushed away when it collides with other cars and how far it\ngets pushed away.\n\nIf the car is lighter then most cars, then it should get pushed away when it collides with\nothers cars.\nGetting pushed can be helpful if the car is week because it gets it away from the danger\n(from the car that hit it) faster, making it take lesser hits and escape better.\nHowever getting pushed is not helpful when racing.\n\n",
      "Aerial Rotation Speed:\nThis adjusts how fast the car can rotate and flip in the air when its performing a stunt.\n\nThis variable also depends on how much the ‘Stunts’ stat of the car is, if the car has a\nhigh Stunts stat then this variable will have a much bigger effect, if it has low Stunts stat\nthe variable will have a lower effect.\n\nIf you think the car is rotating too fast or too slow in the air when performing a stunt use\nthis variable to adjust that.\n\nIf the aerial rotation is too fast it can make the car hard to control in the air as it flips and\nhard to land upright.\n\nIf the car is a big and heavy car like MASHHEN or El King then it should have low\naerial rotation for a realistic effect.\n\n",
      "Aerial Control/Gliding:\nThis adjusts the cars ability to push itself in the air and glide when performing stunts!\n\nIf you don’t know, in the game:\nBackward looping pushes the car upwards. \nForward looping pushes the car forwards. \nLeft and right rolling pushes the car left and right. \n\nThis variable adjust the power if this aerial push.\n\nThe variable also depends on how much the ‘Stunts’ stat of the car is, if the car has a\nhigh Stunts stat then this variable will have a much bigger effect, if it has low Stunts stat\nthe variable will have a lower effect.\n\nIf the car has some kind of wings or fins like Radical One or Kool Kat have then it should\nhave higher aerial control and gliding ability.\n\n",
      "Crash Look Test!\nThis defines how the car will look when it gets damaged.\nOr in other words what the car will look like as it gets damaged until it becomes wasted!\n\nIMPORTANT:\nYou need to perform a 'Normal Crash' test with a 'Roof Crash' test until the car gets totally destroyed (gets wasted and burns).\nYou need to also try a 'Normal Crash' test alone (without the roof crash) until the car gets wasted!\nA 'Roof Crash' happens significantly more when the car falls on its roof from a high jumps.\nA 'Normal Crash' is what happens as the car crashes normally with other cars and obstacles.\n\nClick any of adjustment variable names ‘Radius’, ‘Magnitude’ and ‘Roof Destruction’ to learn about their effect.\n\n>  You must perform the crash test more then once in order to make sure that this is how your want the car to look as it gets damaged\nuntil total destruction.",
      "Crash Radius:\nThe radius around the crash at which the polygons/pieces that lay inside it get\naffected.\n\nOr basically in other words the number of pieces that get affected on collision (the pieces\naround the crash location).\n\nIncreasing the radius will result in more pieces/polygons around the point of collision\ngetting crashed and distorted.\nDecreasing the radius means less pieces/polygons around the collision point getting\ndistorted and crashed.\n\n",
      "Crash Magnitude:\nThe magnitude of the distortion and indentation to occur on the effected pieces/polygons.\n\nOr basically in other words the amount of destruction that happens to each piece when\ncrashed.\n\nHigher magnitude means the piece gets more destructed from an amount of damage,\nlower magnitude means the piece gets less destructed from that same amount of damage.\n\n",
      "Roof Destruction:\nThe amount of destruction to occur on the car’s top.\nThe length of indentation and destruction to happen from above.\n\nTo really see this variable's effect try crashing the roof alone (without a normal crash),\ntry more then once while fixing the car and changing the variable’s value to see the\ndifference.\n\nThe roof crash normally happens in the game when the car lands upside down from a\njump or when a big car like Dr Monstaa steps on it.\n\n"
   };
   int hitmag = 0;
   int actmag = 0;
   int squash = 0;
   boolean crashok = false;
   boolean crashleft = false;
   soundClip[] crashs = new soundClip[3];
   soundClip[] lowcrashs = new soundClip[3];
   Smenu engine = new Smenu(40);
   soundClip[][] engs = new soundClip[5][5];
   int engsel = 0;
   boolean engon = false;
   Smenu witho = new Smenu(40);
   boolean tested = false;
   boolean rateh = false;
   int handling = 140;
   int logged = 0;
   TextField tnick = new TextField("", 15);
   TextField tpass = new TextField("", 15);
   Smenu pubitem = new Smenu(707);
   Smenu pubtyp = new Smenu(40);
   int nmc = 0;
   int roto = 0;
   String[] mycars = new String[20];
   String[] maker = new String[20];
   int[] pubt = new int[20];
   int[] clas = new int[20];
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
   int sls = -1;
   int sle = -1;
   int crshturn = 0;
   boolean crashup = false;
   boolean openm = false;
   boolean mousdr = false;
   boolean waso = false;
   boolean objfacend = false;
   boolean multf10 = false;

   @Override
   public void run() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 0000: aload 0
      // 0001: getfield CarMaker.thredo Ljava/lang/Thread;
      // 0004: bipush 10
      // 0006: invokevirtual java/lang/Thread.setPriority (I)V
      // 0009: aload 0
      // 000a: getfield CarMaker.btgame [Ljava/awt/Image;
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
      // 0029: invokevirtual CarMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 002c: aastore
      // 002d: aload 0
      // 002e: getfield CarMaker.btgame [Ljava/awt/Image;
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
      // 004d: invokevirtual CarMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
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
      // 0065: ldc "data/carmakerlogo.gif"
      // 0067: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 006a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 006d: invokevirtual CarMaker.getImage (Ljava/lang/String;)Ljava/awt/Image;
      // 0070: putfield CarMaker.logo Ljava/awt/Image;
      // 0073: aload 0
      // 0074: getfield CarMaker.m LMedium;
      // 0077: sipush 700
      // 007a: putfield Medium.w I
      // 007d: aload 0
      // 007e: getfield CarMaker.m LMedium;
      // 0081: sipush 350
      // 0084: putfield Medium.cx I
      // 0087: aload 0
      // 0088: getfield CarMaker.m LMedium;
      // 008b: sipush -240
      // 008e: putfield Medium.y I
      // 0091: aload 0
      // 0092: getfield CarMaker.m LMedium;
      // 0095: sipush -400
      // 0098: putfield Medium.z I
      // 009b: aload 0
      // 009c: getfield CarMaker.m LMedium;
      // 009f: bipush 4
      // 00a0: putfield Medium.zy I
      // 00a3: aload 0
      // 00a4: getfield CarMaker.m LMedium;
      // 00a7: sipush 800
      // 00aa: putfield Medium.focus_point I
      // 00ad: aload 0
      // 00ae: getfield CarMaker.m LMedium;
      // 00b1: sipush 8000
      // 00b4: invokevirtual Medium.fadfrom (I)V
      // 00b7: aload 0
      // 00b8: getfield CarMaker.m LMedium;
      // 00bb: getfield Medium.cfade [I
      // 00be: bipush 0
      // 00bf: sipush 187
      // 00c2: iastore
      // 00c3: aload 0
      // 00c4: getfield CarMaker.m LMedium;
      // 00c7: getfield Medium.cfade [I
      // 00ca: bipush 1
      // 00cb: sipush 210
      // 00ce: iastore
      // 00cf: aload 0
      // 00d0: getfield CarMaker.m LMedium;
      // 00d3: getfield Medium.cfade [I
      // 00d6: bipush 2
      // 00d7: sipush 227
      // 00da: iastore
      // 00db: aload 0
      // 00dc: invokevirtual CarMaker.loadsounds ()V
      // 00df: aload 0
      // 00e0: invokevirtual CarMaker.loadbase ()V
      // 00e3: aload 0
      // 00e4: getfield CarMaker.m LMedium;
      // 00e7: bipush 1
      // 00e8: putfield Medium.loadnew Z
      // 00eb: aload 0
      // 00ec: invokevirtual CarMaker.loadsettings ()V
      // 00ef: aload 0
      // 00f0: getfield CarMaker.editor Ljava/awt/TextArea;
      // 00f3: new java/awt/Font
      // 00f6: dup
      // 00f7: aload 0
      // 00f8: getfield CarMaker.cfont Ljava/lang/String;
      // 00fb: bipush 1
      // 00fc: bipush 14
      // 00fe: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0101: invokevirtual java/awt/TextArea.setFont (Ljava/awt/Font;)V
      // 0104: aload 0
      // 0105: getfield CarMaker.srch Ljava/awt/TextField;
      // 0108: new java/awt/Font
      // 010b: dup
      // 010c: aload 0
      // 010d: getfield CarMaker.cfont Ljava/lang/String;
      // 0110: bipush 1
      // 0111: bipush 14
      // 0113: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0116: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 0119: aload 0
      // 011a: getfield CarMaker.rplc Ljava/awt/TextField;
      // 011d: new java/awt/Font
      // 0120: dup
      // 0121: aload 0
      // 0122: getfield CarMaker.cfont Ljava/lang/String;
      // 0125: bipush 1
      // 0126: bipush 14
      // 0128: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 012b: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 012e: bipush 0
      // 012f: istore 1
      // 0130: iload 1
      // 0131: bipush 16
      // 0133: if_icmpge 0153
      // 0136: aload 0
      // 0137: getfield CarMaker.wv [Ljava/awt/TextField;
      // 013a: iload 1
      // 013b: aaload
      // 013c: new java/awt/Font
      // 013f: dup
      // 0140: aload 0
      // 0141: getfield CarMaker.cfont Ljava/lang/String;
      // 0144: bipush 1
      // 0145: bipush 14
      // 0147: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 014a: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 014d: iinc 1 1
      // 0150: goto 0130
      // 0153: aload 0
      // 0154: invokevirtual CarMaker.setheme ()V
      // 0157: getstatic Madness.testdrive I
      // 015a: ifeq 01b2
      // 015d: getstatic Madness.testcar Ljava/lang/String;
      // 0160: ldc "Failx12"
      // 0162: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0165: ifeq 017b
      // 0168: aconst_null
      // 0169: ldc "Failed to load car! Please make sure car is saved before Test Drive."
      // 016b: ldc "Car Maker"
      // 016d: bipush 1
      // 016e: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 0171: aload 0
      // 0172: getfield CarMaker.thredo Ljava/lang/Thread;
      // 0175: invokevirtual java/lang/Thread.stop ()V
      // 0178: goto 01a9
      // 017b: aload 0
      // 017c: getstatic Madness.testcar Ljava/lang/String;
      // 017f: putfield CarMaker.carname Ljava/lang/String;
      // 0182: aload 0
      // 0183: invokevirtual CarMaker.loadfile ()V
      // 0186: aload 0
      // 0187: getfield CarMaker.loadedfile Z
      // 018a: ifeq 01a9
      // 018d: aload 0
      // 018e: bipush 1
      // 018f: putfield CarMaker.tested Z
      // 0192: aload 0
      // 0193: bipush 2
      // 0194: putfield CarMaker.tab I
      // 0197: aload 0
      // 0198: bipush 6
      // 019a: putfield CarMaker.dtab I
      // 019d: aload 0
      // 019e: getfield CarMaker.witho LSmenu;
      // 01a1: getstatic Madness.testdrive I
      // 01a4: bipush 1
      // 01a5: isub
      // 01a6: invokevirtual Smenu.select (I)V
      // 01a9: ldc ""
      // 01ab: putstatic Madness.testcar Ljava/lang/String;
      // 01ae: bipush 0
      // 01af: putstatic Madness.testdrive I
      // 01b2: bipush 0
      // 01b3: istore 1
      // 01b4: aload 0
      // 01b5: getfield CarMaker.carname Ljava/lang/String;
      // 01b8: ldc ""
      // 01ba: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 01bd: ifne 01c7
      // 01c0: aload 0
      // 01c1: bipush 1
      // 01c2: putfield CarMaker.tutok Z
      // 01c5: bipush 1
      // 01c6: istore 1
      // 01c7: aload 0
      // 01c8: getfield CarMaker.exwist Z
      // 01cb: ifne 72d3
      // 01ce: aload 0
      // 01cf: getfield CarMaker.tab I
      // 01d2: aload 0
      // 01d3: getfield CarMaker.tabed I
      // 01d6: if_icmpeq 0235
      // 01d9: aload 0
      // 01da: invokevirtual CarMaker.hidefields ()V
      // 01dd: aload 0
      // 01de: getfield CarMaker.tab I
      // 01e1: bipush 1
      // 01e2: if_icmpne 01ef
      // 01e5: aload 0
      // 01e6: getfield CarMaker.editor Ljava/awt/TextArea;
      // 01e9: invokevirtual java/awt/TextArea.enable ()V
      // 01ec: goto 01f6
      // 01ef: aload 0
      // 01f0: getfield CarMaker.editor Ljava/awt/TextArea;
      // 01f3: invokevirtual java/awt/TextArea.disable ()V
      // 01f6: aload 0
      // 01f7: getfield CarMaker.tabed I
      // 01fa: bipush 2
      // 01fb: if_icmpne 0229
      // 01fe: aload 0
      // 01ff: getfield CarMaker.breakbond Z
      // 0202: ifne 0224
      // 0205: aload 0
      // 0206: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0209: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 020c: aload 0
      // 020d: getfield CarMaker.lastedo Ljava/lang/String;
      // 0210: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0213: ifne 0229
      // 0216: aload 0
      // 0217: getfield CarMaker.editor Ljava/awt/TextArea;
      // 021a: aload 0
      // 021b: getfield CarMaker.lastedo Ljava/lang/String;
      // 021e: invokevirtual java/awt/TextArea.setText (Ljava/lang/String;)V
      // 0221: goto 0229
      // 0224: aload 0
      // 0225: bipush 0
      // 0226: putfield CarMaker.breakbond Z
      // 0229: aload 0
      // 022a: new java/awt/Cursor
      // 022d: dup
      // 022e: bipush 0
      // 022f: invokespecial java/awt/Cursor.<init> (I)V
      // 0232: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 0235: aload 0
      // 0236: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0239: new java/awt/Color
      // 023c: dup
      // 023d: sipush 225
      // 0240: sipush 225
      // 0243: sipush 225
      // 0246: invokespecial java/awt/Color.<init> (III)V
      // 0249: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 024c: aload 0
      // 024d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0250: bipush 0
      // 0251: bipush 0
      // 0252: sipush 700
      // 0255: sipush 550
      // 0258: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 025b: aload 0
      // 025c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 025f: new java/awt/Color
      // 0262: dup
      // 0263: bipush 0
      // 0264: bipush 0
      // 0265: bipush 0
      // 0266: invokespecial java/awt/Color.<init> (III)V
      // 0269: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 026c: aload 0
      // 026d: bipush 0
      // 026e: putfield CarMaker.btn I
      // 0271: bipush 50
      // 0273: istore 2
      // 0274: aload 0
      // 0275: getfield CarMaker.tab I
      // 0278: ifne 0d59
      // 027b: aload 0
      // 027c: getfield CarMaker.tabed I
      // 027f: aload 0
      // 0280: getfield CarMaker.tab I
      // 0283: if_icmpeq 0352
      // 0286: aload 0
      // 0287: getfield CarMaker.slcar LSmenu;
      // 028a: invokevirtual Smenu.removeAll ()V
      // 028d: aload 0
      // 028e: getfield CarMaker.slcar LSmenu;
      // 0291: sipush 200
      // 0294: putfield Smenu.maxl I
      // 0297: aload 0
      // 0298: getfield CarMaker.slcar LSmenu;
      // 029b: aload 0
      // 029c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 029f: ldc "Select a Car                      "
      // 02a1: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 02a4: new java/io/File
      // 02a7: dup
      // 02a8: new java/lang/StringBuilder
      // 02ab: dup
      // 02ac: invokespecial java/lang/StringBuilder.<init> ()V
      // 02af: ldc ""
      // 02b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02b4: getstatic Madness.fpath Ljava/lang/String;
      // 02b7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02ba: ldc "mycars/"
      // 02bc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 02bf: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 02c2: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 02c5: invokevirtual java/io/File.list ()[Ljava/lang/String;
      // 02c8: astore 3
      // 02c9: aload 3
      // 02ca: ifnull 0308
      // 02cd: bipush 0
      // 02ce: istore 4
      // 02d0: iload 4
      // 02d2: aload 3
      // 02d3: arraylength
      // 02d4: if_icmpge 0308
      // 02d7: aload 3
      // 02d8: iload 4
      // 02da: aaload
      // 02db: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 02de: ldc ".rad"
      // 02e0: invokevirtual java/lang/String.endsWith (Ljava/lang/String;)Z
      // 02e3: ifeq 0302
      // 02e6: aload 0
      // 02e7: getfield CarMaker.slcar LSmenu;
      // 02ea: aload 0
      // 02eb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 02ee: aload 3
      // 02ef: iload 4
      // 02f1: aaload
      // 02f2: bipush 0
      // 02f3: aload 3
      // 02f4: iload 4
      // 02f6: aaload
      // 02f7: invokevirtual java/lang/String.length ()I
      // 02fa: bipush 4
      // 02fb: isub
      // 02fc: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 02ff: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 0302: iinc 4 1
      // 0305: goto 02d0
      // 0308: aload 0
      // 0309: getfield CarMaker.carname Ljava/lang/String;
      // 030c: ldc ""
      // 030e: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0311: ifeq 031f
      // 0314: aload 0
      // 0315: getfield CarMaker.slcar LSmenu;
      // 0318: bipush 0
      // 0319: invokevirtual Smenu.select (I)V
      // 031c: goto 033f
      // 031f: aload 0
      // 0320: getfield CarMaker.slcar LSmenu;
      // 0323: aload 0
      // 0324: getfield CarMaker.carname Ljava/lang/String;
      // 0327: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 032a: aload 0
      // 032b: getfield CarMaker.carname Ljava/lang/String;
      // 032e: aload 0
      // 032f: getfield CarMaker.slcar LSmenu;
      // 0332: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0335: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0338: ifeq 033f
      // 033b: aload 0
      // 033c: invokevirtual CarMaker.loadfile ()V
      // 033f: aload 0
      // 0340: bipush -1
      // 0341: putfield CarMaker.mouseon I
      // 0344: aload 0
      // 0345: getfield CarMaker.srch Ljava/awt/TextField;
      // 0348: ldc ""
      // 034a: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 034d: aload 0
      // 034e: bipush 0
      // 034f: putfield CarMaker.sfase I
      // 0352: aload 0
      // 0353: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0356: new java/awt/Font
      // 0359: dup
      // 035a: ldc_w "Arial"
      // 035d: bipush 1
      // 035e: bipush 13
      // 0360: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0363: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0366: aload 0
      // 0367: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 036a: new java/awt/Color
      // 036d: dup
      // 036e: bipush 0
      // 036f: bipush 0
      // 0370: bipush 0
      // 0371: invokespecial java/awt/Color.<init> (III)V
      // 0374: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0377: aload 0
      // 0378: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 037b: aload 0
      // 037c: getfield CarMaker.logo Ljava/awt/Image;
      // 037f: sipush 214
      // 0382: bipush 35
      // 0384: aconst_null
      // 0385: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 0388: pop
      // 0389: aload 0
      // 038a: getfield CarMaker.xm I
      // 038d: sipush 214
      // 0390: if_icmple 03d3
      // 0393: aload 0
      // 0394: getfield CarMaker.xm I
      // 0397: sipush 485
      // 039a: if_icmpge 03d3
      // 039d: aload 0
      // 039e: getfield CarMaker.ym I
      // 03a1: bipush 25
      // 03a3: if_icmple 03d3
      // 03a6: aload 0
      // 03a7: getfield CarMaker.ym I
      // 03aa: bipush 104
      // 03ac: if_icmpge 03d3
      // 03af: aload 0
      // 03b0: getfield CarMaker.openm Z
      // 03b3: ifne 03d3
      // 03b6: aload 0
      // 03b7: getfield CarMaker.mouseon I
      // 03ba: bipush -1
      // 03bb: if_icmpne 03ec
      // 03be: aload 0
      // 03bf: bipush 3
      // 03c0: putfield CarMaker.mouseon I
      // 03c3: aload 0
      // 03c4: new java/awt/Cursor
      // 03c7: dup
      // 03c8: bipush 12
      // 03ca: invokespecial java/awt/Cursor.<init> (I)V
      // 03cd: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 03d0: goto 03ec
      // 03d3: aload 0
      // 03d4: getfield CarMaker.mouseon I
      // 03d7: bipush 3
      // 03d8: if_icmpne 03ec
      // 03db: aload 0
      // 03dc: bipush -1
      // 03dd: putfield CarMaker.mouseon I
      // 03e0: aload 0
      // 03e1: new java/awt/Cursor
      // 03e4: dup
      // 03e5: bipush 0
      // 03e6: invokespecial java/awt/Cursor.<init> (I)V
      // 03e9: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 03ec: aload 0
      // 03ed: getfield CarMaker.mouseon I
      // 03f0: bipush 3
      // 03f1: if_icmpne 0400
      // 03f4: aload 0
      // 03f5: getfield CarMaker.mouses I
      // 03f8: bipush -1
      // 03f9: if_icmpne 0400
      // 03fc: aload 0
      // 03fd: invokevirtual CarMaker.openhlink ()V
      // 0400: bipush 30
      // 0402: istore 3
      // 0403: bipush 0
      // 0404: istore 4
      // 0406: aload 0
      // 0407: getfield CarMaker.tutok Z
      // 040a: ifeq 0415
      // 040d: sipush 250
      // 0410: istore 3
      // 0411: bipush -70
      // 0413: istore 4
      // 0415: aload 0
      // 0416: getfield CarMaker.xm I
      // 0419: bipush 76
      // 041b: if_icmple 0463
      // 041e: aload 0
      // 041f: getfield CarMaker.xm I
      // 0422: sipush 624
      // 0425: if_icmpge 0463
      // 0428: aload 0
      // 0429: getfield CarMaker.ym I
      // 042c: bipush 84
      // 042e: iload 3
      // 042f: iadd
      // 0430: if_icmple 0463
      // 0433: aload 0
      // 0434: getfield CarMaker.ym I
      // 0437: sipush 167
      // 043a: iload 3
      // 043b: iadd
      // 043c: if_icmpge 0463
      // 043f: aload 0
      // 0440: getfield CarMaker.openm Z
      // 0443: ifne 0463
      // 0446: aload 0
      // 0447: getfield CarMaker.mouseon I
      // 044a: bipush -1
      // 044b: if_icmpne 047c
      // 044e: aload 0
      // 044f: bipush 1
      // 0450: putfield CarMaker.mouseon I
      // 0453: aload 0
      // 0454: new java/awt/Cursor
      // 0457: dup
      // 0458: bipush 12
      // 045a: invokespecial java/awt/Cursor.<init> (I)V
      // 045d: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 0460: goto 047c
      // 0463: aload 0
      // 0464: getfield CarMaker.mouseon I
      // 0467: bipush 1
      // 0468: if_icmpne 047c
      // 046b: aload 0
      // 046c: bipush -1
      // 046d: putfield CarMaker.mouseon I
      // 0470: aload 0
      // 0471: new java/awt/Cursor
      // 0474: dup
      // 0475: bipush 0
      // 0476: invokespecial java/awt/Cursor.<init> (I)V
      // 0479: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 047c: bipush 0
      // 047d: istore 5
      // 047f: aload 0
      // 0480: getfield CarMaker.tutok Z
      // 0483: ifne 04f4
      // 0486: aload 0
      // 0487: getfield CarMaker.mouseon I
      // 048a: bipush 1
      // 048b: if_icmpeq 04f4
      // 048e: iload 1
      // 048f: ifne 04f4
      // 0492: aload 0
      // 0493: getfield CarMaker.flk I
      // 0496: ifgt 04c7
      // 0499: aload 0
      // 049a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 049d: new java/awt/Color
      // 04a0: dup
      // 04a1: sipush 255
      // 04a4: bipush 0
      // 04a5: bipush 0
      // 04a6: invokespecial java/awt/Color.<init> (III)V
      // 04a9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 04ac: aload 0
      // 04ad: dup
      // 04ae: getfield CarMaker.flk I
      // 04b1: bipush 1
      // 04b2: isub
      // 04b3: putfield CarMaker.flk I
      // 04b6: aload 0
      // 04b7: getfield CarMaker.flk I
      // 04ba: bipush -2
      // 04bc: if_icmpne 04f4
      // 04bf: aload 0
      // 04c0: bipush 1
      // 04c1: putfield CarMaker.flk I
      // 04c4: goto 04f4
      // 04c7: aload 0
      // 04c8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 04cb: new java/awt/Color
      // 04ce: dup
      // 04cf: bipush 0
      // 04d0: bipush 0
      // 04d1: sipush 255
      // 04d4: invokespecial java/awt/Color.<init> (III)V
      // 04d7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 04da: bipush 2
      // 04db: istore 5
      // 04dd: aload 0
      // 04de: dup
      // 04df: getfield CarMaker.flk I
      // 04e2: bipush 1
      // 04e3: iadd
      // 04e4: putfield CarMaker.flk I
      // 04e7: aload 0
      // 04e8: getfield CarMaker.flk I
      // 04eb: bipush 3
      // 04ec: if_icmpne 04f4
      // 04ef: aload 0
      // 04f0: bipush 0
      // 04f1: putfield CarMaker.flk I
      // 04f4: aload 0
      // 04f5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 04f8: bipush 76
      // 04fa: iload 5
      // 04fc: iadd
      // 04fd: bipush 84
      // 04ff: iload 3
      // 0500: iadd
      // 0501: bipush 76
      // 0503: iload 5
      // 0505: iadd
      // 0506: sipush 167
      // 0509: iload 3
      // 050a: iadd
      // 050b: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 050e: aload 0
      // 050f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0512: bipush 76
      // 0514: iload 5
      // 0516: iadd
      // 0517: bipush 84
      // 0519: iload 3
      // 051a: iadd
      // 051b: bipush 95
      // 051d: iload 5
      // 051f: iadd
      // 0520: bipush 84
      // 0522: iload 3
      // 0523: iadd
      // 0524: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0527: aload 0
      // 0528: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 052b: bipush 76
      // 052d: iload 5
      // 052f: iadd
      // 0530: sipush 167
      // 0533: iload 3
      // 0534: iadd
      // 0535: bipush 95
      // 0537: iload 5
      // 0539: iadd
      // 053a: sipush 167
      // 053d: iload 3
      // 053e: iadd
      // 053f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0542: aload 0
      // 0543: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0546: sipush 624
      // 0549: iload 5
      // 054b: isub
      // 054c: bipush 84
      // 054e: iload 3
      // 054f: iadd
      // 0550: sipush 624
      // 0553: iload 5
      // 0555: isub
      // 0556: sipush 167
      // 0559: iload 3
      // 055a: iadd
      // 055b: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 055e: aload 0
      // 055f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0562: sipush 624
      // 0565: iload 5
      // 0567: isub
      // 0568: bipush 84
      // 056a: iload 3
      // 056b: iadd
      // 056c: sipush 605
      // 056f: iload 5
      // 0571: isub
      // 0572: bipush 84
      // 0574: iload 3
      // 0575: iadd
      // 0576: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0579: aload 0
      // 057a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 057d: sipush 624
      // 0580: iload 5
      // 0582: isub
      // 0583: sipush 167
      // 0586: iload 3
      // 0587: iadd
      // 0588: sipush 605
      // 058b: iload 5
      // 058d: isub
      // 058e: sipush 167
      // 0591: iload 3
      // 0592: iadd
      // 0593: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0596: aload 0
      // 0597: getfield CarMaker.mouseon I
      // 059a: bipush 1
      // 059b: if_icmpne 05b5
      // 059e: aload 0
      // 059f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 05a2: new java/awt/Color
      // 05a5: dup
      // 05a6: bipush 0
      // 05a7: bipush 64
      // 05a9: sipush 128
      // 05ac: invokespecial java/awt/Color.<init> (III)V
      // 05af: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05b2: goto 05c6
      // 05b5: aload 0
      // 05b6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 05b9: new java/awt/Color
      // 05bc: dup
      // 05bd: bipush 0
      // 05be: bipush 0
      // 05bf: bipush 0
      // 05c0: invokespecial java/awt/Color.<init> (III)V
      // 05c3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05c6: aload 0
      // 05c7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 05ca: ldc_w "If this is your first time creating a car please follow the tutorial found at:"
      // 05cd: bipush 106
      // 05cf: bipush 110
      // 05d1: iload 3
      // 05d2: iadd
      // 05d3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 05d6: aload 0
      // 05d7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 05da: new java/awt/Color
      // 05dd: dup
      // 05de: bipush 0
      // 05df: sipush 128
      // 05e2: sipush 255
      // 05e5: invokespecial java/awt/Color.<init> (III)V
      // 05e8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05eb: aload 0
      // 05ec: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 05ef: ldc_w "http://www.needformadness.com/developer/simplecar.html"
      // 05f2: bipush 106
      // 05f4: sipush 130
      // 05f7: iload 3
      // 05f8: iadd
      // 05f9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 05fc: aload 0
      // 05fd: getfield CarMaker.mouseon I
      // 0600: bipush 1
      // 0601: if_icmpne 061c
      // 0604: aload 0
      // 0605: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0608: new java/awt/Color
      // 060b: dup
      // 060c: bipush 0
      // 060d: sipush 128
      // 0610: sipush 255
      // 0613: invokespecial java/awt/Color.<init> (III)V
      // 0616: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0619: goto 0630
      // 061c: aload 0
      // 061d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0620: new java/awt/Color
      // 0623: dup
      // 0624: bipush 0
      // 0625: bipush 64
      // 0627: sipush 128
      // 062a: invokespecial java/awt/Color.<init> (III)V
      // 062d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0630: aload 0
      // 0631: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0634: bipush 106
      // 0636: sipush 131
      // 0639: iload 3
      // 063a: iadd
      // 063b: sipush 480
      // 063e: sipush 131
      // 0641: iload 3
      // 0642: iadd
      // 0643: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0646: aload 0
      // 0647: getfield CarMaker.mouseon I
      // 064a: bipush 1
      // 064b: if_icmpne 0665
      // 064e: aload 0
      // 064f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0652: new java/awt/Color
      // 0655: dup
      // 0656: bipush 0
      // 0657: bipush 64
      // 0659: sipush 128
      // 065c: invokespecial java/awt/Color.<init> (III)V
      // 065f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0662: goto 0676
      // 0665: aload 0
      // 0666: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0669: new java/awt/Color
      // 066c: dup
      // 066d: bipush 0
      // 066e: bipush 0
      // 066f: bipush 0
      // 0670: invokespecial java/awt/Color.<init> (III)V
      // 0673: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0676: aload 0
      // 0677: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 067a: ldc_w "It is highly recommended that you follow this tutorial before trying anything!"
      // 067d: bipush 106
      // 067f: sipush 150
      // 0682: iload 3
      // 0683: iadd
      // 0684: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0687: aload 0
      // 0688: getfield CarMaker.mouseon I
      // 068b: bipush 1
      // 068c: if_icmpne 069d
      // 068f: aload 0
      // 0690: getfield CarMaker.mouses I
      // 0693: bipush -1
      // 0694: if_icmpne 069d
      // 0697: aload 0
      // 0698: invokevirtual CarMaker.openlink ()V
      // 069b: bipush 1
      // 069c: istore 1
      // 069d: aload 0
      // 069e: getfield CarMaker.xm I
      // 06a1: sipush 200
      // 06a4: if_icmple 06e9
      // 06a7: aload 0
      // 06a8: getfield CarMaker.xm I
      // 06ab: sipush 500
      // 06ae: if_icmpge 06e9
      // 06b1: aload 0
      // 06b2: getfield CarMaker.ym I
      // 06b5: sipush 467
      // 06b8: if_icmple 06e9
      // 06bb: aload 0
      // 06bc: getfield CarMaker.ym I
      // 06bf: sipush 504
      // 06c2: if_icmpge 06e9
      // 06c5: aload 0
      // 06c6: getfield CarMaker.openm Z
      // 06c9: ifne 06e9
      // 06cc: aload 0
      // 06cd: getfield CarMaker.mouseon I
      // 06d0: bipush -1
      // 06d1: if_icmpne 0702
      // 06d4: aload 0
      // 06d5: bipush 2
      // 06d6: putfield CarMaker.mouseon I
      // 06d9: aload 0
      // 06da: new java/awt/Cursor
      // 06dd: dup
      // 06de: bipush 12
      // 06e0: invokespecial java/awt/Cursor.<init> (I)V
      // 06e3: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 06e6: goto 0702
      // 06e9: aload 0
      // 06ea: getfield CarMaker.mouseon I
      // 06ed: bipush 2
      // 06ee: if_icmpne 0702
      // 06f1: aload 0
      // 06f2: bipush -1
      // 06f3: putfield CarMaker.mouseon I
      // 06f6: aload 0
      // 06f7: new java/awt/Cursor
      // 06fa: dup
      // 06fb: bipush 0
      // 06fc: invokespecial java/awt/Cursor.<init> (I)V
      // 06ff: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 0702: aload 0
      // 0703: aload 0
      // 0704: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0707: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 070a: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 070d: aload 0
      // 070e: getfield CarMaker.mouseon I
      // 0711: bipush 2
      // 0712: if_icmpne 072c
      // 0715: aload 0
      // 0716: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0719: new java/awt/Color
      // 071c: dup
      // 071d: bipush 0
      // 071e: bipush 64
      // 0720: sipush 128
      // 0723: invokespecial java/awt/Color.<init> (III)V
      // 0726: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0729: goto 073d
      // 072c: aload 0
      // 072d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0730: new java/awt/Color
      // 0733: dup
      // 0734: bipush 0
      // 0735: bipush 0
      // 0736: bipush 0
      // 0737: invokespecial java/awt/Color.<init> (III)V
      // 073a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 073d: aload 0
      // 073e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0741: ldc_w "For the Car Maker Homepage, Development Center and Forums :"
      // 0744: sipush 350
      // 0747: aload 0
      // 0748: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 074b: ldc_w "For the Car Maker Homepage, Development Center and Forums :"
      // 074e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0751: bipush 2
      // 0752: idiv
      // 0753: isub
      // 0754: sipush 480
      // 0757: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 075a: aload 0
      // 075b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 075e: new java/awt/Color
      // 0761: dup
      // 0762: bipush 0
      // 0763: sipush 128
      // 0766: sipush 255
      // 0769: invokespecial java/awt/Color.<init> (III)V
      // 076c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 076f: ldc_w "http://www.needformadness.com/developer/"
      // 0772: astore 6
      // 0774: aload 0
      // 0775: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0778: aload 6
      // 077a: sipush 350
      // 077d: aload 0
      // 077e: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0781: aload 6
      // 0783: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0786: bipush 2
      // 0787: idiv
      // 0788: isub
      // 0789: sipush 500
      // 078c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 078f: aload 0
      // 0790: getfield CarMaker.mouseon I
      // 0793: bipush 2
      // 0794: if_icmpne 07af
      // 0797: aload 0
      // 0798: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 079b: new java/awt/Color
      // 079e: dup
      // 079f: bipush 0
      // 07a0: sipush 128
      // 07a3: sipush 255
      // 07a6: invokespecial java/awt/Color.<init> (III)V
      // 07a9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 07ac: goto 07c3
      // 07af: aload 0
      // 07b0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 07b3: new java/awt/Color
      // 07b6: dup
      // 07b7: bipush 0
      // 07b8: bipush 64
      // 07ba: sipush 128
      // 07bd: invokespecial java/awt/Color.<init> (III)V
      // 07c0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 07c3: aload 0
      // 07c4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 07c7: sipush 350
      // 07ca: aload 0
      // 07cb: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 07ce: aload 6
      // 07d0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 07d3: bipush 2
      // 07d4: idiv
      // 07d5: isub
      // 07d6: sipush 501
      // 07d9: sipush 350
      // 07dc: aload 0
      // 07dd: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 07e0: aload 6
      // 07e2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 07e5: bipush 2
      // 07e6: idiv
      // 07e7: iadd
      // 07e8: sipush 501
      // 07eb: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 07ee: aload 0
      // 07ef: getfield CarMaker.mouseon I
      // 07f2: bipush 2
      // 07f3: if_icmpne 0802
      // 07f6: aload 0
      // 07f7: getfield CarMaker.mouses I
      // 07fa: bipush -1
      // 07fb: if_icmpne 0802
      // 07fe: aload 0
      // 07ff: invokevirtual CarMaker.openhlink ()V
      // 0802: bipush 0
      // 0803: istore 7
      // 0805: aload 0
      // 0806: getfield CarMaker.sfase I
      // 0809: bipush 3
      // 080a: if_icmpne 0811
      // 080d: bipush 100
      // 080f: istore 7
      // 0811: aload 0
      // 0812: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0815: new java/awt/Color
      // 0818: dup
      // 0819: bipush 0
      // 081a: bipush 0
      // 081b: bipush 0
      // 081c: invokespecial java/awt/Color.<init> (III)V
      // 081f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0822: aload 0
      // 0823: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0826: sipush 177
      // 0829: iload 7
      // 082b: isub
      // 082c: sipush 202
      // 082f: iload 4
      // 0831: iadd
      // 0832: sipush 346
      // 0835: iload 7
      // 0837: bipush 2
      // 0838: imul
      // 0839: iadd
      // 083a: sipush 167
      // 083d: iload 7
      // 083f: bipush 5
      // 0840: idiv
      // 0841: iadd
      // 0842: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 0845: aload 0
      // 0846: getfield CarMaker.sfase I
      // 0849: ifne 0938
      // 084c: aload 0
      // 084d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0850: ldc_w "Select Car to Edit"
      // 0853: sipush 350
      // 0856: aload 0
      // 0857: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 085a: ldc_w "Select Car to Edit"
      // 085d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0860: bipush 2
      // 0861: idiv
      // 0862: isub
      // 0863: sipush 230
      // 0866: iload 4
      // 0868: iadd
      // 0869: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 086c: aload 0
      // 086d: getfield CarMaker.slcar LSmenu;
      // 0870: sipush 250
      // 0873: sipush 240
      // 0876: iload 4
      // 0878: iadd
      // 0879: invokevirtual Smenu.move (II)V
      // 087c: aload 0
      // 087d: getfield CarMaker.slcar LSmenu;
      // 0880: invokevirtual Smenu.getWidth ()I
      // 0883: sipush 200
      // 0886: if_icmpeq 0895
      // 0889: aload 0
      // 088a: getfield CarMaker.slcar LSmenu;
      // 088d: sipush 200
      // 0890: bipush 21
      // 0892: invokevirtual Smenu.setSize (II)V
      // 0895: aload 0
      // 0896: getfield CarMaker.slcar LSmenu;
      // 0899: invokevirtual Smenu.isShowing ()Z
      // 089c: ifne 08a6
      // 089f: aload 0
      // 08a0: getfield CarMaker.slcar LSmenu;
      // 08a3: invokevirtual Smenu.show ()V
      // 08a6: aload 0
      // 08a7: ldc_w "    Make new Car    "
      // 08aa: sipush 430
      // 08ad: sipush 296
      // 08b0: iload 4
      // 08b2: iadd
      // 08b3: bipush 0
      // 08b4: bipush 1
      // 08b5: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 08b8: aload 0
      // 08b9: ldc_w "     Rename Car     "
      // 08bc: sipush 270
      // 08bf: sipush 296
      // 08c2: iload 4
      // 08c4: iadd
      // 08c5: bipush 0
      // 08c6: bipush 0
      // 08c7: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 08ca: aload 0
      // 08cb: ldc_w "      Delete Car      "
      // 08ce: sipush 270
      // 08d1: sipush 336
      // 08d4: iload 4
      // 08d6: iadd
      // 08d7: bipush 0
      // 08d8: bipush 0
      // 08d9: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 08dc: aload 0
      // 08dd: ldc_w "     Import & Export     "
      // 08e0: sipush 430
      // 08e3: sipush 336
      // 08e6: iload 4
      // 08e8: iadd
      // 08e9: bipush 0
      // 08ea: bipush 0
      // 08eb: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 08ee: aload 0
      // 08ef: getfield CarMaker.slcar LSmenu;
      // 08f2: invokevirtual Smenu.getSelectedIndex ()I
      // 08f5: ifeq 0932
      // 08f8: aload 0
      // 08f9: getfield CarMaker.carname Ljava/lang/String;
      // 08fc: aload 0
      // 08fd: getfield CarMaker.slcar LSmenu;
      // 0900: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0903: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0906: ifne 0938
      // 0909: aload 0
      // 090a: bipush 0
      // 090b: putfield CarMaker.tomany Z
      // 090e: aload 0
      // 090f: aload 0
      // 0910: getfield CarMaker.slcar LSmenu;
      // 0913: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0916: putfield CarMaker.carname Ljava/lang/String;
      // 0919: aload 0
      // 091a: invokevirtual CarMaker.loadfile ()V
      // 091d: aload 0
      // 091e: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0921: bipush 0
      // 0922: bipush 0
      // 0923: invokevirtual java/awt/TextArea.select (II)V
      // 0926: aload 0
      // 0927: bipush 0
      // 0928: putfield CarMaker.tested Z
      // 092b: aload 0
      // 092c: invokevirtual CarMaker.requestFocus ()V
      // 092f: goto 0938
      // 0932: aload 0
      // 0933: ldc ""
      // 0935: putfield CarMaker.carname Ljava/lang/String;
      // 0938: aload 0
      // 0939: getfield CarMaker.sfase I
      // 093c: bipush 1
      // 093d: if_icmpne 09e1
      // 0940: aload 0
      // 0941: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0944: ldc_w "Make a new Car"
      // 0947: sipush 350
      // 094a: aload 0
      // 094b: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 094e: ldc_w "Make a new Car"
      // 0951: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0954: bipush 2
      // 0955: idiv
      // 0956: isub
      // 0957: sipush 230
      // 095a: iload 4
      // 095c: iadd
      // 095d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0960: aload 0
      // 0961: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0964: new java/awt/Font
      // 0967: dup
      // 0968: ldc_w "Arial"
      // 096b: bipush 1
      // 096c: bipush 12
      // 096e: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0971: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0974: aload 0
      // 0975: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0978: ldc_w "New car name :"
      // 097b: sipush 228
      // 097e: sipush 266
      // 0981: iload 4
      // 0983: iadd
      // 0984: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0987: aload 0
      // 0988: aload 0
      // 0989: getfield CarMaker.srch Ljava/awt/TextField;
      // 098c: sipush 335
      // 098f: sipush 250
      // 0992: iload 4
      // 0994: iadd
      // 0995: sipush 129
      // 0998: bipush 22
      // 099a: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 099d: aload 0
      // 099e: getfield CarMaker.srch Ljava/awt/TextField;
      // 09a1: invokevirtual java/awt/TextField.isShowing ()Z
      // 09a4: ifne 09b5
      // 09a7: aload 0
      // 09a8: getfield CarMaker.srch Ljava/awt/TextField;
      // 09ab: invokevirtual java/awt/TextField.show ()V
      // 09ae: aload 0
      // 09af: getfield CarMaker.srch Ljava/awt/TextField;
      // 09b2: invokevirtual java/awt/TextField.requestFocus ()V
      // 09b5: aload 0
      // 09b6: aload 0
      // 09b7: getfield CarMaker.srch Ljava/awt/TextField;
      // 09ba: invokevirtual CarMaker.fixtext (Ljava/awt/TextField;)V
      // 09bd: aload 0
      // 09be: ldc_w "    Make Car    "
      // 09c1: sipush 350
      // 09c4: sipush 306
      // 09c7: iload 4
      // 09c9: iadd
      // 09ca: bipush 0
      // 09cb: bipush 1
      // 09cc: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 09cf: aload 0
      // 09d0: ldc_w "  Cancel  "
      // 09d3: sipush 350
      // 09d6: sipush 346
      // 09d9: iload 4
      // 09db: iadd
      // 09dc: bipush 0
      // 09dd: bipush 0
      // 09de: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 09e1: aload 0
      // 09e2: getfield CarMaker.sfase I
      // 09e5: bipush 2
      // 09e6: if_icmpne 0abc
      // 09e9: aload 0
      // 09ea: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 09ed: new java/lang/StringBuilder
      // 09f0: dup
      // 09f1: invokespecial java/lang/StringBuilder.<init> ()V
      // 09f4: ldc_w "Rename Car :  "
      // 09f7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 09fa: aload 0
      // 09fb: getfield CarMaker.carname Ljava/lang/String;
      // 09fe: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a01: ldc ""
      // 0a03: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a06: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0a09: sipush 350
      // 0a0c: aload 0
      // 0a0d: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0a10: new java/lang/StringBuilder
      // 0a13: dup
      // 0a14: invokespecial java/lang/StringBuilder.<init> ()V
      // 0a17: ldc_w "Rename Car :  "
      // 0a1a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a1d: aload 0
      // 0a1e: getfield CarMaker.carname Ljava/lang/String;
      // 0a21: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a24: ldc ""
      // 0a26: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0a29: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0a2c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0a2f: bipush 2
      // 0a30: idiv
      // 0a31: isub
      // 0a32: sipush 230
      // 0a35: iload 4
      // 0a37: iadd
      // 0a38: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0a3b: aload 0
      // 0a3c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0a3f: new java/awt/Font
      // 0a42: dup
      // 0a43: ldc_w "Arial"
      // 0a46: bipush 1
      // 0a47: bipush 12
      // 0a49: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0a4c: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0a4f: aload 0
      // 0a50: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0a53: ldc_w "New name :"
      // 0a56: sipush 239
      // 0a59: sipush 266
      // 0a5c: iload 4
      // 0a5e: iadd
      // 0a5f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0a62: aload 0
      // 0a63: aload 0
      // 0a64: getfield CarMaker.srch Ljava/awt/TextField;
      // 0a67: sipush 316
      // 0a6a: sipush 250
      // 0a6d: iload 4
      // 0a6f: iadd
      // 0a70: sipush 129
      // 0a73: bipush 22
      // 0a75: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 0a78: aload 0
      // 0a79: getfield CarMaker.srch Ljava/awt/TextField;
      // 0a7c: invokevirtual java/awt/TextField.isShowing ()Z
      // 0a7f: ifne 0a90
      // 0a82: aload 0
      // 0a83: getfield CarMaker.srch Ljava/awt/TextField;
      // 0a86: invokevirtual java/awt/TextField.show ()V
      // 0a89: aload 0
      // 0a8a: getfield CarMaker.srch Ljava/awt/TextField;
      // 0a8d: invokevirtual java/awt/TextField.requestFocus ()V
      // 0a90: aload 0
      // 0a91: aload 0
      // 0a92: getfield CarMaker.srch Ljava/awt/TextField;
      // 0a95: invokevirtual CarMaker.fixtext (Ljava/awt/TextField;)V
      // 0a98: aload 0
      // 0a99: ldc_w "   Rename Car   "
      // 0a9c: sipush 350
      // 0a9f: sipush 306
      // 0aa2: iload 4
      // 0aa4: iadd
      // 0aa5: bipush 0
      // 0aa6: bipush 1
      // 0aa7: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0aaa: aload 0
      // 0aab: ldc_w "  Cancel  "
      // 0aae: sipush 350
      // 0ab1: sipush 346
      // 0ab4: iload 4
      // 0ab6: iadd
      // 0ab7: bipush 0
      // 0ab8: bipush 0
      // 0ab9: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0abc: aload 0
      // 0abd: getfield CarMaker.sfase I
      // 0ac0: bipush 3
      // 0ac1: if_icmpne 0c89
      // 0ac4: aload 0
      // 0ac5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0ac8: ldc_w "Import a Wavefront OBJ 3D Model"
      // 0acb: sipush 350
      // 0ace: aload 0
      // 0acf: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0ad2: ldc_w "Import a Wavefront OBJ 3D Model"
      // 0ad5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0ad8: bipush 2
      // 0ad9: idiv
      // 0ada: isub
      // 0adb: sipush 230
      // 0ade: iload 4
      // 0ae0: iadd
      // 0ae1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0ae4: aload 0
      // 0ae5: getfield CarMaker.xm I
      // 0ae8: bipush 116
      // 0aea: if_icmple 0b2e
      // 0aed: aload 0
      // 0aee: getfield CarMaker.xm I
      // 0af1: sipush 584
      // 0af4: if_icmpge 0b2e
      // 0af7: aload 0
      // 0af8: getfield CarMaker.ym I
      // 0afb: sipush 246
      // 0afe: iload 4
      // 0b00: iadd
      // 0b01: if_icmple 0b2e
      // 0b04: aload 0
      // 0b05: getfield CarMaker.ym I
      // 0b08: sipush 290
      // 0b0b: iload 4
      // 0b0d: iadd
      // 0b0e: if_icmpge 0b2e
      // 0b11: aload 0
      // 0b12: getfield CarMaker.mouseon I
      // 0b15: bipush -1
      // 0b16: if_icmpne 0b47
      // 0b19: aload 0
      // 0b1a: bipush 3
      // 0b1b: putfield CarMaker.mouseon I
      // 0b1e: aload 0
      // 0b1f: new java/awt/Cursor
      // 0b22: dup
      // 0b23: bipush 12
      // 0b25: invokespecial java/awt/Cursor.<init> (I)V
      // 0b28: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 0b2b: goto 0b47
      // 0b2e: aload 0
      // 0b2f: getfield CarMaker.mouseon I
      // 0b32: bipush 3
      // 0b33: if_icmpne 0b47
      // 0b36: aload 0
      // 0b37: bipush -1
      // 0b38: putfield CarMaker.mouseon I
      // 0b3b: aload 0
      // 0b3c: new java/awt/Cursor
      // 0b3f: dup
      // 0b40: bipush 0
      // 0b41: invokespecial java/awt/Cursor.<init> (I)V
      // 0b44: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 0b47: aload 0
      // 0b48: aload 0
      // 0b49: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0b4c: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0b4f: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0b52: aload 0
      // 0b53: getfield CarMaker.mouseon I
      // 0b56: bipush 3
      // 0b57: if_icmpne 0b71
      // 0b5a: aload 0
      // 0b5b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0b5e: new java/awt/Color
      // 0b61: dup
      // 0b62: bipush 0
      // 0b63: bipush 64
      // 0b65: sipush 128
      // 0b68: invokespecial java/awt/Color.<init> (III)V
      // 0b6b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0b6e: goto 0b82
      // 0b71: aload 0
      // 0b72: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0b75: new java/awt/Color
      // 0b78: dup
      // 0b79: bipush 0
      // 0b7a: bipush 0
      // 0b7b: bipush 0
      // 0b7c: invokespecial java/awt/Color.<init> (III)V
      // 0b7f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0b82: aload 0
      // 0b83: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0b86: ldc_w "Please read the important information about importing cars found at:"
      // 0b89: sipush 350
      // 0b8c: aload 0
      // 0b8d: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0b90: ldc_w "Please read the important information about importing cars, found here :"
      // 0b93: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0b96: bipush 2
      // 0b97: idiv
      // 0b98: isub
      // 0b99: sipush 260
      // 0b9c: iload 4
      // 0b9e: iadd
      // 0b9f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0ba2: aload 0
      // 0ba3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0ba6: new java/awt/Color
      // 0ba9: dup
      // 0baa: bipush 0
      // 0bab: sipush 128
      // 0bae: sipush 255
      // 0bb1: invokespecial java/awt/Color.<init> (III)V
      // 0bb4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0bb7: ldc_w "http://www.needformadness.com/developer/extras.html"
      // 0bba: astore 6
      // 0bbc: aload 0
      // 0bbd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0bc0: aload 6
      // 0bc2: sipush 350
      // 0bc5: aload 0
      // 0bc6: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0bc9: aload 6
      // 0bcb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0bce: bipush 2
      // 0bcf: idiv
      // 0bd0: isub
      // 0bd1: sipush 280
      // 0bd4: iload 4
      // 0bd6: iadd
      // 0bd7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0bda: aload 0
      // 0bdb: getfield CarMaker.mouseon I
      // 0bde: bipush 3
      // 0bdf: if_icmpne 0bfa
      // 0be2: aload 0
      // 0be3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0be6: new java/awt/Color
      // 0be9: dup
      // 0bea: bipush 0
      // 0beb: sipush 128
      // 0bee: sipush 255
      // 0bf1: invokespecial java/awt/Color.<init> (III)V
      // 0bf4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0bf7: goto 0c0e
      // 0bfa: aload 0
      // 0bfb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0bfe: new java/awt/Color
      // 0c01: dup
      // 0c02: bipush 0
      // 0c03: bipush 64
      // 0c05: sipush 128
      // 0c08: invokespecial java/awt/Color.<init> (III)V
      // 0c0b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0c0e: aload 0
      // 0c0f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0c12: sipush 350
      // 0c15: aload 0
      // 0c16: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0c19: aload 6
      // 0c1b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0c1e: bipush 2
      // 0c1f: idiv
      // 0c20: isub
      // 0c21: sipush 281
      // 0c24: iload 4
      // 0c26: iadd
      // 0c27: sipush 350
      // 0c2a: aload 0
      // 0c2b: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0c2e: aload 6
      // 0c30: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0c33: bipush 2
      // 0c34: idiv
      // 0c35: iadd
      // 0c36: sipush 281
      // 0c39: iload 4
      // 0c3b: iadd
      // 0c3c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0c3f: aload 0
      // 0c40: getfield CarMaker.mouseon I
      // 0c43: bipush 3
      // 0c44: if_icmpne 0c53
      // 0c47: aload 0
      // 0c48: getfield CarMaker.mouses I
      // 0c4b: bipush -1
      // 0c4c: if_icmpne 0c53
      // 0c4f: aload 0
      // 0c50: invokevirtual CarMaker.openelink ()V
      // 0c53: aload 0
      // 0c54: ldc_w "     Import Car     "
      // 0c57: sipush 350
      // 0c5a: sipush 326
      // 0c5d: iload 4
      // 0c5f: iadd
      // 0c60: bipush 0
      // 0c61: bipush 1
      // 0c62: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0c65: aload 0
      // 0c66: ldc_w "  Export >  "
      // 0c69: sipush 550
      // 0c6c: sipush 326
      // 0c6f: iload 4
      // 0c71: iadd
      // 0c72: bipush 0
      // 0c73: bipush 0
      // 0c74: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0c77: aload 0
      // 0c78: ldc_w "  Cancel  "
      // 0c7b: sipush 350
      // 0c7e: sipush 366
      // 0c81: iload 4
      // 0c83: iadd
      // 0c84: bipush 0
      // 0c85: bipush 0
      // 0c86: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0c89: aload 0
      // 0c8a: getfield CarMaker.sfase I
      // 0c8d: bipush 4
      // 0c8e: if_icmpne 0d59
      // 0c91: aload 0
      // 0c92: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0c95: ldc_w "Select Car to Export"
      // 0c98: sipush 350
      // 0c9b: aload 0
      // 0c9c: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 0c9f: ldc_w "Select Car to Export"
      // 0ca2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0ca5: bipush 2
      // 0ca6: idiv
      // 0ca7: isub
      // 0ca8: sipush 230
      // 0cab: iload 4
      // 0cad: iadd
      // 0cae: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0cb1: aload 0
      // 0cb2: getfield CarMaker.slcar LSmenu;
      // 0cb5: sipush 250
      // 0cb8: sipush 240
      // 0cbb: iload 4
      // 0cbd: iadd
      // 0cbe: invokevirtual Smenu.move (II)V
      // 0cc1: aload 0
      // 0cc2: getfield CarMaker.slcar LSmenu;
      // 0cc5: invokevirtual Smenu.getWidth ()I
      // 0cc8: sipush 200
      // 0ccb: if_icmpeq 0cda
      // 0cce: aload 0
      // 0ccf: getfield CarMaker.slcar LSmenu;
      // 0cd2: sipush 200
      // 0cd5: bipush 21
      // 0cd7: invokevirtual Smenu.setSize (II)V
      // 0cda: aload 0
      // 0cdb: getfield CarMaker.slcar LSmenu;
      // 0cde: invokevirtual Smenu.isShowing ()Z
      // 0ce1: ifne 0ceb
      // 0ce4: aload 0
      // 0ce5: getfield CarMaker.slcar LSmenu;
      // 0ce8: invokevirtual Smenu.show ()V
      // 0ceb: aload 0
      // 0cec: ldc_w "     Export Car     "
      // 0cef: sipush 350
      // 0cf2: sipush 306
      // 0cf5: iload 4
      // 0cf7: iadd
      // 0cf8: bipush 0
      // 0cf9: bipush 1
      // 0cfa: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0cfd: aload 0
      // 0cfe: ldc_w "  Cancel  "
      // 0d01: sipush 350
      // 0d04: sipush 346
      // 0d07: iload 4
      // 0d09: iadd
      // 0d0a: bipush 0
      // 0d0b: bipush 0
      // 0d0c: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0d0f: aload 0
      // 0d10: getfield CarMaker.slcar LSmenu;
      // 0d13: invokevirtual Smenu.getSelectedIndex ()I
      // 0d16: ifeq 0d53
      // 0d19: aload 0
      // 0d1a: getfield CarMaker.carname Ljava/lang/String;
      // 0d1d: aload 0
      // 0d1e: getfield CarMaker.slcar LSmenu;
      // 0d21: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0d24: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0d27: ifne 0d59
      // 0d2a: aload 0
      // 0d2b: bipush 0
      // 0d2c: putfield CarMaker.tomany Z
      // 0d2f: aload 0
      // 0d30: aload 0
      // 0d31: getfield CarMaker.slcar LSmenu;
      // 0d34: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0d37: putfield CarMaker.carname Ljava/lang/String;
      // 0d3a: aload 0
      // 0d3b: invokevirtual CarMaker.loadfile ()V
      // 0d3e: aload 0
      // 0d3f: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0d42: bipush 0
      // 0d43: bipush 0
      // 0d44: invokevirtual java/awt/TextArea.select (II)V
      // 0d47: aload 0
      // 0d48: bipush 0
      // 0d49: putfield CarMaker.tested Z
      // 0d4c: aload 0
      // 0d4d: invokevirtual CarMaker.requestFocus ()V
      // 0d50: goto 0d59
      // 0d53: aload 0
      // 0d54: ldc ""
      // 0d56: putfield CarMaker.carname Ljava/lang/String;
      // 0d59: aload 0
      // 0d5a: getfield CarMaker.tab I
      // 0d5d: bipush 1
      // 0d5e: if_icmpne 132e
      // 0d61: aload 0
      // 0d62: getfield CarMaker.tabed I
      // 0d65: aload 0
      // 0d66: getfield CarMaker.tab I
      // 0d69: if_icmpeq 0d8d
      // 0d6c: aload 0
      // 0d6d: getfield CarMaker.srch Ljava/awt/TextField;
      // 0d70: ldc ""
      // 0d72: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 0d75: aload 0
      // 0d76: getfield CarMaker.rplc Ljava/awt/TextField;
      // 0d79: ldc ""
      // 0d7b: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 0d7e: aload 0
      // 0d7f: bipush 1
      // 0d80: putfield CarMaker.cntchk I
      // 0d83: aload 0
      // 0d84: bipush 0
      // 0d85: putfield CarMaker.npolys I
      // 0d88: aload 0
      // 0d89: bipush 0
      // 0d8a: putfield CarMaker.prefs Z
      // 0d8d: aload 0
      // 0d8e: aload 0
      // 0d8f: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0d92: bipush 5
      // 0d93: bipush 30
      // 0d95: sipush 690
      // 0d98: sipush 400
      // 0d9b: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 0d9e: aload 0
      // 0d9f: getfield CarMaker.openm Z
      // 0da2: ifne 0dc0
      // 0da5: aload 0
      // 0da6: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0da9: invokevirtual java/awt/TextArea.isShowing ()Z
      // 0dac: ifne 0dd1
      // 0daf: aload 0
      // 0db0: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0db3: invokevirtual java/awt/TextArea.show ()V
      // 0db6: aload 0
      // 0db7: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0dba: invokevirtual java/awt/TextArea.requestFocus ()V
      // 0dbd: goto 0dd1
      // 0dc0: aload 0
      // 0dc1: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0dc4: invokevirtual java/awt/TextArea.isShowing ()Z
      // 0dc7: ifeq 0dd1
      // 0dca: aload 0
      // 0dcb: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0dce: invokevirtual java/awt/TextArea.hide ()V
      // 0dd1: aload 0
      // 0dd2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0dd5: new java/awt/Font
      // 0dd8: dup
      // 0dd9: ldc_w "Arial"
      // 0ddc: bipush 1
      // 0ddd: bipush 12
      // 0ddf: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0de2: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0de5: aload 0
      // 0de6: getfield CarMaker.prefs Z
      // 0de9: ifeq 0f3c
      // 0dec: aload 0
      // 0ded: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0df0: ldc_w "Code Font:"
      // 0df3: bipush 10
      // 0df5: sipush 446
      // 0df8: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0dfb: aload 0
      // 0dfc: getfield CarMaker.fontsel LSmenu;
      // 0dff: bipush 76
      // 0e01: sipush 430
      // 0e04: invokevirtual Smenu.move (II)V
      // 0e07: aload 0
      // 0e08: getfield CarMaker.fontsel LSmenu;
      // 0e0b: invokevirtual Smenu.isShowing ()Z
      // 0e0e: ifne 0e23
      // 0e11: aload 0
      // 0e12: getfield CarMaker.fontsel LSmenu;
      // 0e15: invokevirtual Smenu.show ()V
      // 0e18: aload 0
      // 0e19: getfield CarMaker.fontsel LSmenu;
      // 0e1c: aload 0
      // 0e1d: getfield CarMaker.cfont Ljava/lang/String;
      // 0e20: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 0e23: aload 0
      // 0e24: getfield CarMaker.cfont Ljava/lang/String;
      // 0e27: aload 0
      // 0e28: getfield CarMaker.fontsel LSmenu;
      // 0e2b: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0e2e: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0e31: ifne 0eaf
      // 0e34: aload 0
      // 0e35: bipush 0
      // 0e36: putfield CarMaker.cntprf I
      // 0e39: aload 0
      // 0e3a: aload 0
      // 0e3b: getfield CarMaker.fontsel LSmenu;
      // 0e3e: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 0e41: putfield CarMaker.cfont Ljava/lang/String;
      // 0e44: aload 0
      // 0e45: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0e48: new java/awt/Font
      // 0e4b: dup
      // 0e4c: aload 0
      // 0e4d: getfield CarMaker.cfont Ljava/lang/String;
      // 0e50: bipush 1
      // 0e51: bipush 14
      // 0e53: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e56: invokevirtual java/awt/TextArea.setFont (Ljava/awt/Font;)V
      // 0e59: aload 0
      // 0e5a: getfield CarMaker.srch Ljava/awt/TextField;
      // 0e5d: new java/awt/Font
      // 0e60: dup
      // 0e61: aload 0
      // 0e62: getfield CarMaker.cfont Ljava/lang/String;
      // 0e65: bipush 1
      // 0e66: bipush 14
      // 0e68: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e6b: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 0e6e: aload 0
      // 0e6f: getfield CarMaker.rplc Ljava/awt/TextField;
      // 0e72: new java/awt/Font
      // 0e75: dup
      // 0e76: aload 0
      // 0e77: getfield CarMaker.cfont Ljava/lang/String;
      // 0e7a: bipush 1
      // 0e7b: bipush 14
      // 0e7d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e80: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 0e83: bipush 0
      // 0e84: istore 3
      // 0e85: iload 3
      // 0e86: bipush 16
      // 0e88: if_icmpge 0ea8
      // 0e8b: aload 0
      // 0e8c: getfield CarMaker.wv [Ljava/awt/TextField;
      // 0e8f: iload 3
      // 0e90: aaload
      // 0e91: new java/awt/Font
      // 0e94: dup
      // 0e95: aload 0
      // 0e96: getfield CarMaker.cfont Ljava/lang/String;
      // 0e99: bipush 1
      // 0e9a: bipush 14
      // 0e9c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e9f: invokevirtual java/awt/TextField.setFont (Ljava/awt/Font;)V
      // 0ea2: iinc 3 1
      // 0ea5: goto 0e85
      // 0ea8: aload 0
      // 0ea9: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0eac: invokevirtual java/awt/TextArea.requestFocus ()V
      // 0eaf: aload 0
      // 0eb0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 0eb3: ldc_w "Code Theme:"
      // 0eb6: sipush 190
      // 0eb9: sipush 446
      // 0ebc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0ebf: aload 0
      // 0ec0: getfield CarMaker.ctheme LSmenu;
      // 0ec3: sipush 271
      // 0ec6: sipush 430
      // 0ec9: invokevirtual Smenu.move (II)V
      // 0ecc: aload 0
      // 0ecd: getfield CarMaker.ctheme LSmenu;
      // 0ed0: invokevirtual Smenu.isShowing ()Z
      // 0ed3: ifne 0ee8
      // 0ed6: aload 0
      // 0ed7: getfield CarMaker.ctheme LSmenu;
      // 0eda: invokevirtual Smenu.show ()V
      // 0edd: aload 0
      // 0ede: getfield CarMaker.ctheme LSmenu;
      // 0ee1: aload 0
      // 0ee2: getfield CarMaker.cthm I
      // 0ee5: invokevirtual Smenu.select (I)V
      // 0ee8: aload 0
      // 0ee9: getfield CarMaker.cthm I
      // 0eec: aload 0
      // 0eed: getfield CarMaker.ctheme LSmenu;
      // 0ef0: invokevirtual Smenu.getSelectedIndex ()I
      // 0ef3: if_icmpeq 0f11
      // 0ef6: aload 0
      // 0ef7: bipush 0
      // 0ef8: putfield CarMaker.cntprf I
      // 0efb: aload 0
      // 0efc: aload 0
      // 0efd: getfield CarMaker.ctheme LSmenu;
      // 0f00: invokevirtual Smenu.getSelectedIndex ()I
      // 0f03: putfield CarMaker.cthm I
      // 0f06: aload 0
      // 0f07: invokevirtual CarMaker.setheme ()V
      // 0f0a: aload 0
      // 0f0b: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0f0e: invokevirtual java/awt/TextArea.requestFocus ()V
      // 0f11: aload 0
      // 0f12: ldc_w "<"
      // 0f15: sipush 400
      // 0f18: sipush 446
      // 0f1b: bipush 3
      // 0f1c: bipush 0
      // 0f1d: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0f20: aload 0
      // 0f21: dup
      // 0f22: getfield CarMaker.cntprf I
      // 0f25: bipush 1
      // 0f26: iadd
      // 0f27: putfield CarMaker.cntprf I
      // 0f2a: aload 0
      // 0f2b: getfield CarMaker.cntprf I
      // 0f2e: sipush 200
      // 0f31: if_icmpne 104d
      // 0f34: aload 0
      // 0f35: bipush 0
      // 0f36: putfield CarMaker.prefs Z
      // 0f39: goto 104d
      // 0f3c: aload 0
      // 0f3d: ldc_w "Preferences"
      // 0f40: bipush 52
      // 0f42: sipush 446
      // 0f45: bipush 3
      // 0f46: bipush 0
      // 0f47: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 0f4a: aload 0
      // 0f4b: getfield CarMaker.ctheme LSmenu;
      // 0f4e: invokevirtual Smenu.isShowing ()Z
      // 0f51: ifeq 0f5b
      // 0f54: aload 0
      // 0f55: getfield CarMaker.ctheme LSmenu;
      // 0f58: invokevirtual Smenu.hide ()V
      // 0f5b: aload 0
      // 0f5c: getfield CarMaker.fontsel LSmenu;
      // 0f5f: invokevirtual Smenu.isShowing ()Z
      // 0f62: ifeq 0f6c
      // 0f65: aload 0
      // 0f66: getfield CarMaker.fontsel LSmenu;
      // 0f69: invokevirtual Smenu.hide ()V
      // 0f6c: aload 0
      // 0f6d: getfield CarMaker.cntprf I
      // 0f70: ifeq 0f78
      // 0f73: aload 0
      // 0f74: bipush 0
      // 0f75: putfield CarMaker.cntprf I
      // 0f78: aload 0
      // 0f79: getfield CarMaker.cntchk I
      // 0f7c: ifne 0ff5
      // 0f7f: aload 0
      // 0f80: bipush 0
      // 0f81: putfield CarMaker.npolys I
      // 0f84: bipush 0
      // 0f85: istore 3
      // 0f86: bipush 0
      // 0f87: istore 4
      // 0f89: iload 3
      // 0f8a: bipush -1
      // 0f8b: if_icmpeq 0fdf
      // 0f8e: aload 0
      // 0f8f: getfield CarMaker.mouses I
      // 0f92: bipush 1
      // 0f93: if_icmpeq 0fdf
      // 0f96: iload 4
      // 0f98: ifne 0fad
      // 0f9b: aload 0
      // 0f9c: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0f9f: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 0fa2: ldc_w "<p>"
      // 0fa5: iload 3
      // 0fa6: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 0fa9: istore 3
      // 0faa: goto 0fbc
      // 0fad: aload 0
      // 0fae: getfield CarMaker.editor Ljava/awt/TextArea;
      // 0fb1: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 0fb4: ldc_w "</p>"
      // 0fb7: iload 3
      // 0fb8: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 0fbb: istore 3
      // 0fbc: iload 3
      // 0fbd: bipush -1
      // 0fbe: if_icmpeq 0f89
      // 0fc1: iload 4
      // 0fc3: ifne 0fcc
      // 0fc6: bipush 1
      // 0fc7: istore 4
      // 0fc9: goto 0fd9
      // 0fcc: bipush 0
      // 0fcd: istore 4
      // 0fcf: aload 0
      // 0fd0: dup
      // 0fd1: getfield CarMaker.npolys I
      // 0fd4: bipush 1
      // 0fd5: iadd
      // 0fd6: putfield CarMaker.npolys I
      // 0fd9: iinc 3 3
      // 0fdc: goto 0f89
      // 0fdf: aload 0
      // 0fe0: getfield CarMaker.mouses I
      // 0fe3: bipush 1
      // 0fe4: if_icmpne 0fec
      // 0fe7: aload 0
      // 0fe8: bipush 0
      // 0fe9: putfield CarMaker.npolys I
      // 0fec: aload 0
      // 0fed: bipush 30
      // 0fef: putfield CarMaker.cntchk I
      // 0ff2: goto 0fff
      // 0ff5: aload 0
      // 0ff6: dup
      // 0ff7: getfield CarMaker.cntchk I
      // 0ffa: bipush 1
      // 0ffb: isub
      // 0ffc: putfield CarMaker.cntchk I
      // 0fff: aload 0
      // 1000: getfield CarMaker.npolys I
      // 1003: sipush 210
      // 1006: if_icmple 101c
      // 1009: aload 0
      // 100a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 100d: new java/awt/Color
      // 1010: dup
      // 1011: sipush 255
      // 1014: bipush 0
      // 1015: bipush 0
      // 1016: invokespecial java/awt/Color.<init> (III)V
      // 1019: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 101c: aload 0
      // 101d: getfield CarMaker.npolys I
      // 1020: ifeq 104d
      // 1023: aload 0
      // 1024: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1027: new java/lang/StringBuilder
      // 102a: dup
      // 102b: invokespecial java/lang/StringBuilder.<init> ()V
      // 102e: ldc_w "Number of Polygons :  "
      // 1031: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1034: aload 0
      // 1035: getfield CarMaker.npolys I
      // 1038: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 103b: ldc_w " / 210"
      // 103e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1041: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1044: sipush 200
      // 1047: sipush 446
      // 104a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 104d: aload 0
      // 104e: getfield CarMaker.changed Z
      // 1051: ifne 106a
      // 1054: aload 0
      // 1055: getfield CarMaker.editor Ljava/awt/TextArea;
      // 1058: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 105b: aload 0
      // 105c: getfield CarMaker.lastedo Ljava/lang/String;
      // 105f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1062: ifne 106a
      // 1065: aload 0
      // 1066: bipush 1
      // 1067: putfield CarMaker.changed Z
      // 106a: aload 0
      // 106b: ldc_w "  Save  "
      // 106e: sipush 490
      // 1071: sipush 455
      // 1074: bipush 0
      // 1075: aload 0
      // 1076: getfield CarMaker.changed Z
      // 1079: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 107c: aload 0
      // 107d: ldc_w "  Save & Preview  >  "
      // 1080: sipush 600
      // 1083: sipush 455
      // 1086: bipush 0
      // 1087: aload 0
      // 1088: getfield CarMaker.changed Z
      // 108b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 108e: aload 0
      // 108f: bipush 0
      // 1090: putfield CarMaker.mirror Z
      // 1093: aload 0
      // 1094: bipush -1
      // 1095: putfield CarMaker.polynum I
      // 1098: aload 0
      // 1099: bipush 0
      // 109a: putfield CarMaker.cntpls I
      // 109d: ldc ""
      // 109f: astore 3
      // 10a0: aload 0
      // 10a1: getfield CarMaker.editor Ljava/awt/TextArea;
      // 10a4: invokevirtual java/awt/TextArea.getSelectedText ()Ljava/lang/String;
      // 10a7: astore 3
      // 10a8: goto 10ad
      // 10ab: astore 4
      // 10ad: aload 3
      // 10ae: ldc ""
      // 10b0: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 10b3: ifne 111c
      // 10b6: aload 3
      // 10b7: ldc_w "<p>"
      // 10ba: bipush 0
      // 10bb: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 10be: istore 4
      // 10c0: iload 4
      // 10c2: bipush -1
      // 10c3: if_icmpeq 111c
      // 10c6: iload 4
      // 10c8: bipush 1
      // 10c9: iadd
      // 10ca: aload 3
      // 10cb: invokevirtual java/lang/String.length ()I
      // 10ce: if_icmpge 111c
      // 10d1: aload 0
      // 10d2: getfield CarMaker.mirror Z
      // 10d5: ifne 10fa
      // 10d8: aload 3
      // 10d9: ldc_w "</p>"
      // 10dc: iload 4
      // 10de: bipush 1
      // 10df: iadd
      // 10e0: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 10e3: istore 4
      // 10e5: iload 4
      // 10e7: bipush -1
      // 10e8: if_icmpeq 10fa
      // 10eb: aload 0
      // 10ec: bipush 1
      // 10ed: putfield CarMaker.mirror Z
      // 10f0: aload 0
      // 10f1: dup
      // 10f2: getfield CarMaker.cntpls I
      // 10f5: bipush 1
      // 10f6: iadd
      // 10f7: putfield CarMaker.cntpls I
      // 10fa: aload 0
      // 10fb: getfield CarMaker.mirror Z
      // 10fe: ifeq 10c0
      // 1101: aload 3
      // 1102: ldc_w "<p>"
      // 1105: iload 4
      // 1107: bipush 1
      // 1108: iadd
      // 1109: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 110c: istore 4
      // 110e: iload 4
      // 1110: bipush -1
      // 1111: if_icmpeq 10c0
      // 1114: aload 0
      // 1115: bipush 0
      // 1116: putfield CarMaker.mirror Z
      // 1119: goto 10c0
      // 111c: aload 0
      // 111d: getfield CarMaker.mirror Z
      // 1120: ifne 121c
      // 1123: aload 0
      // 1124: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1127: new java/awt/Color
      // 112a: dup
      // 112b: sipush 170
      // 112e: sipush 170
      // 1131: sipush 170
      // 1134: invokespecial java/awt/Color.<init> (III)V
      // 1137: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 113a: aload 0
      // 113b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 113e: bipush 5
      // 113f: sipush 474
      // 1142: sipush 494
      // 1145: bipush 70
      // 1147: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 114a: aload 0
      // 114b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 114e: new java/awt/Color
      // 1151: dup
      // 1152: bipush 0
      // 1153: bipush 0
      // 1154: bipush 0
      // 1155: invokespecial java/awt/Color.<init> (III)V
      // 1158: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 115b: aload 0
      // 115c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 115f: ldc_w "Text to find:"
      // 1162: bipush 18
      // 1164: sipush 500
      // 1167: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 116a: aload 0
      // 116b: aload 0
      // 116c: getfield CarMaker.srch Ljava/awt/TextField;
      // 116f: bipush 91
      // 1171: sipush 484
      // 1174: sipush 129
      // 1177: bipush 22
      // 1179: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 117c: aload 0
      // 117d: getfield CarMaker.srch Ljava/awt/TextField;
      // 1180: invokevirtual java/awt/TextField.isShowing ()Z
      // 1183: ifne 118d
      // 1186: aload 0
      // 1187: getfield CarMaker.srch Ljava/awt/TextField;
      // 118a: invokevirtual java/awt/TextField.show ()V
      // 118d: bipush 0
      // 118e: istore 4
      // 1190: aload 0
      // 1191: getfield CarMaker.srch Ljava/awt/TextField;
      // 1194: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 1197: ldc ""
      // 1199: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 119c: ifne 11a2
      // 119f: bipush 1
      // 11a0: istore 4
      // 11a2: aload 0
      // 11a3: ldc_w " Find "
      // 11a6: bipush 117
      // 11a8: sipush 526
      // 11ab: bipush 2
      // 11ac: iload 4
      // 11ae: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 11b1: aload 0
      // 11b2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 11b5: ldc_w "Replace with:"
      // 11b8: sipush 255
      // 11bb: sipush 500
      // 11be: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 11c1: aload 0
      // 11c2: aload 0
      // 11c3: getfield CarMaker.rplc Ljava/awt/TextField;
      // 11c6: sipush 338
      // 11c9: sipush 484
      // 11cc: sipush 129
      // 11cf: bipush 22
      // 11d1: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 11d4: aload 0
      // 11d5: getfield CarMaker.rplc Ljava/awt/TextField;
      // 11d8: invokevirtual java/awt/TextField.isShowing ()Z
      // 11db: ifne 11e5
      // 11de: aload 0
      // 11df: getfield CarMaker.rplc Ljava/awt/TextField;
      // 11e2: invokevirtual java/awt/TextField.show ()V
      // 11e5: bipush 0
      // 11e6: istore 4
      // 11e8: aload 0
      // 11e9: getfield CarMaker.srch Ljava/awt/TextField;
      // 11ec: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 11ef: ldc ""
      // 11f1: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 11f4: ifne 1209
      // 11f7: aload 0
      // 11f8: getfield CarMaker.rplc Ljava/awt/TextField;
      // 11fb: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 11fe: ldc ""
      // 1200: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1203: ifne 1209
      // 1206: bipush 1
      // 1207: istore 4
      // 1209: aload 0
      // 120a: ldc_w " Replace "
      // 120d: sipush 376
      // 1210: sipush 526
      // 1213: bipush 2
      // 1214: iload 4
      // 1216: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 1219: goto 130a
      // 121c: aload 0
      // 121d: getfield CarMaker.srch Ljava/awt/TextField;
      // 1220: invokevirtual java/awt/TextField.isShowing ()Z
      // 1223: ifeq 122d
      // 1226: aload 0
      // 1227: getfield CarMaker.srch Ljava/awt/TextField;
      // 122a: invokevirtual java/awt/TextField.hide ()V
      // 122d: aload 0
      // 122e: getfield CarMaker.rplc Ljava/awt/TextField;
      // 1231: invokevirtual java/awt/TextField.isShowing ()Z
      // 1234: ifeq 123e
      // 1237: aload 0
      // 1238: getfield CarMaker.rplc Ljava/awt/TextField;
      // 123b: invokevirtual java/awt/TextField.hide ()V
      // 123e: aload 0
      // 123f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1242: new java/awt/Color
      // 1245: dup
      // 1246: sipush 170
      // 1249: sipush 170
      // 124c: sipush 170
      // 124f: invokespecial java/awt/Color.<init> (III)V
      // 1252: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1255: aload 0
      // 1256: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1259: bipush 5
      // 125a: sipush 474
      // 125d: sipush 450
      // 1260: bipush 70
      // 1262: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 1265: aload 0
      // 1266: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1269: new java/awt/Color
      // 126c: dup
      // 126d: bipush 0
      // 126e: bipush 0
      // 126f: bipush 0
      // 1270: invokespecial java/awt/Color.<init> (III)V
      // 1273: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1276: aload 0
      // 1277: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 127a: ldc_w "Mirror [Selected] polygon(s) along:"
      // 127d: bipush 18
      // 127f: sipush 490
      // 1282: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1285: aload 0
      // 1286: ldc_w " Mirro Along X Axis "
      // 1289: bipush 90
      // 128b: sipush 525
      // 128e: bipush 2
      // 128f: bipush 1
      // 1290: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 1293: aload 0
      // 1294: ldc_w " Mirro Along Y Axis "
      // 1297: sipush 230
      // 129a: sipush 525
      // 129d: bipush 2
      // 129e: bipush 0
      // 129f: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 12a2: aload 0
      // 12a3: ldc_w " Mirro Along Z Axis "
      // 12a6: sipush 370
      // 12a9: sipush 525
      // 12ac: bipush 2
      // 12ad: bipush 0
      // 12ae: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 12b1: aload 0
      // 12b2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 12b5: new java/awt/Color
      // 12b8: dup
      // 12b9: sipush 170
      // 12bc: sipush 170
      // 12bf: sipush 170
      // 12c2: invokespecial java/awt/Color.<init> (III)V
      // 12c5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 12c8: aload 0
      // 12c9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 12cc: sipush 465
      // 12cf: sipush 474
      // 12d2: sipush 230
      // 12d5: bipush 70
      // 12d7: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 12da: aload 0
      // 12db: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 12de: new java/awt/Color
      // 12e1: dup
      // 12e2: bipush 0
      // 12e3: bipush 0
      // 12e4: bipush 0
      // 12e5: invokespecial java/awt/Color.<init> (III)V
      // 12e8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 12eb: aload 0
      // 12ec: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 12ef: ldc_w "Show [Selected] polygon(s):"
      // 12f2: sipush 478
      // 12f5: sipush 490
      // 12f8: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 12fb: aload 0
      // 12fc: ldc_w " Show in 3D  > "
      // 12ff: sipush 580
      // 1302: sipush 523
      // 1305: bipush 0
      // 1306: bipush 1
      // 1307: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 130a: aload 0
      // 130b: getfield CarMaker.npolys I
      // 130e: sipush 210
      // 1311: if_icmple 132e
      // 1314: aload 0
      // 1315: getfield CarMaker.tomany Z
      // 1318: ifne 132e
      // 131b: aload 0
      // 131c: invokevirtual CarMaker.repaint ()V
      // 131f: aconst_null
      // 1320: ldc_w "Maximum number of polygons (pieces) allowed has been exceeded!\nThe maximum allowed is 210 polygons, please decrease.\n"
      // 1323: ldc "Car Maker"
      // 1325: bipush 1
      // 1326: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 1329: aload 0
      // 132a: bipush 1
      // 132b: putfield CarMaker.tomany Z
      // 132e: aload 0
      // 132f: getfield CarMaker.tab I
      // 1332: bipush 2
      // 1333: if_icmpne 5d98
      // 1336: aload 0
      // 1337: getfield CarMaker.tabed I
      // 133a: aload 0
      // 133b: getfield CarMaker.tab I
      // 133e: if_icmpeq 134a
      // 1341: aload 0
      // 1342: invokevirtual CarMaker.setupo ()V
      // 1345: aload 0
      // 1346: bipush -1
      // 1347: putfield CarMaker.dtabed I
      // 134a: aload 0
      // 134b: getfield CarMaker.m LMedium;
      // 134e: aload 0
      // 134f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1352: invokevirtual Medium.d (Ljava/awt/Graphics2D;)V
      // 1355: aload 0
      // 1356: getfield CarMaker.o LContO;
      // 1359: aload 0
      // 135a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 135d: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 1360: aload 0
      // 1361: getfield CarMaker.dtab I
      // 1364: bipush 2
      // 1365: if_icmpne 181d
      // 1368: aload 0
      // 1369: getfield CarMaker.compsel I
      // 136c: ifle 1422
      // 136f: aload 0
      // 1370: getfield CarMaker.compsel I
      // 1373: bipush 16
      // 1375: if_icmpgt 1422
      // 1378: aload 0
      // 1379: getfield CarMaker.compo [LContO;
      // 137c: aload 0
      // 137d: getfield CarMaker.compsel I
      // 1380: bipush 1
      // 1381: isub
      // 1382: aaload
      // 1383: aload 0
      // 1384: getfield CarMaker.o LContO;
      // 1387: getfield ContO.x I
      // 138a: putfield ContO.x I
      // 138d: aload 0
      // 138e: getfield CarMaker.compo [LContO;
      // 1391: aload 0
      // 1392: getfield CarMaker.compsel I
      // 1395: bipush 1
      // 1396: isub
      // 1397: aaload
      // 1398: aload 0
      // 1399: getfield CarMaker.o LContO;
      // 139c: getfield ContO.y I
      // 139f: putfield ContO.y I
      // 13a2: aload 0
      // 13a3: getfield CarMaker.compo [LContO;
      // 13a6: aload 0
      // 13a7: getfield CarMaker.compsel I
      // 13aa: bipush 1
      // 13ab: isub
      // 13ac: aaload
      // 13ad: aload 0
      // 13ae: getfield CarMaker.o LContO;
      // 13b1: getfield ContO.z I
      // 13b4: putfield ContO.z I
      // 13b7: aload 0
      // 13b8: getfield CarMaker.compo [LContO;
      // 13bb: aload 0
      // 13bc: getfield CarMaker.compsel I
      // 13bf: bipush 1
      // 13c0: isub
      // 13c1: aaload
      // 13c2: aload 0
      // 13c3: getfield CarMaker.o LContO;
      // 13c6: getfield ContO.xz I
      // 13c9: putfield ContO.xz I
      // 13cc: aload 0
      // 13cd: getfield CarMaker.compo [LContO;
      // 13d0: aload 0
      // 13d1: getfield CarMaker.compsel I
      // 13d4: bipush 1
      // 13d5: isub
      // 13d6: aaload
      // 13d7: aload 0
      // 13d8: getfield CarMaker.o LContO;
      // 13db: getfield ContO.xy I
      // 13de: putfield ContO.xy I
      // 13e1: aload 0
      // 13e2: getfield CarMaker.compo [LContO;
      // 13e5: aload 0
      // 13e6: getfield CarMaker.compsel I
      // 13e9: bipush 1
      // 13ea: isub
      // 13eb: aaload
      // 13ec: aload 0
      // 13ed: getfield CarMaker.o LContO;
      // 13f0: getfield ContO.zy I
      // 13f3: putfield ContO.zy I
      // 13f6: aload 0
      // 13f7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 13fa: bipush 3
      // 13fb: ldc_w 0.4
      // 13fe: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 1401: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 1404: aload 0
      // 1405: getfield CarMaker.compo [LContO;
      // 1408: aload 0
      // 1409: getfield CarMaker.compsel I
      // 140c: bipush 1
      // 140d: isub
      // 140e: aaload
      // 140f: aload 0
      // 1410: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1413: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 1416: aload 0
      // 1417: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 141a: bipush 3
      // 141b: fconst_1
      // 141c: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 141f: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 1422: aload 0
      // 1423: getfield CarMaker.xm I
      // 1426: sipush 420
      // 1429: if_icmple 181d
      // 142c: aload 0
      // 142d: getfield CarMaker.xm I
      // 1430: sipush 690
      // 1433: if_icmpge 181d
      // 1436: aload 0
      // 1437: getfield CarMaker.ym I
      // 143a: sipush 425
      // 143d: if_icmple 181d
      // 1440: aload 0
      // 1441: getfield CarMaker.ym I
      // 1444: sipush 540
      // 1447: if_icmpge 181d
      // 144a: bipush 6
      // 144c: newarray 10
      // 144e: dup
      // 144f: bipush 0
      // 1450: bipush 50
      // 1452: aload 0
      // 1453: getfield CarMaker.adna [I
      // 1456: bipush 0
      // 1457: iaload
      // 1458: iadd
      // 1459: iastore
      // 145a: dup
      // 145b: bipush 1
      // 145c: bipush -50
      // 145e: aload 0
      // 145f: getfield CarMaker.adna [I
      // 1462: bipush 1
      // 1463: iaload
      // 1464: isub
      // 1465: iastore
      // 1466: dup
      // 1467: bipush 2
      // 1468: bipush 0
      // 1469: iastore
      // 146a: dup
      // 146b: bipush 3
      // 146c: bipush 0
      // 146d: iastore
      // 146e: dup
      // 146f: bipush 4
      // 1470: bipush 0
      // 1471: iastore
      // 1472: dup
      // 1473: bipush 5
      // 1474: bipush 0
      // 1475: iastore
      // 1476: astore 3
      // 1477: bipush 6
      // 1479: newarray 10
      // 147b: dup
      // 147c: bipush 0
      // 147d: bipush 0
      // 147e: iastore
      // 147f: dup
      // 1480: bipush 1
      // 1481: bipush 0
      // 1482: iastore
      // 1483: dup
      // 1484: bipush 2
      // 1485: bipush 50
      // 1487: aload 0
      // 1488: getfield CarMaker.adna [I
      // 148b: bipush 2
      // 148c: iaload
      // 148d: iadd
      // 148e: iastore
      // 148f: dup
      // 1490: bipush 3
      // 1491: bipush -50
      // 1493: aload 0
      // 1494: getfield CarMaker.adna [I
      // 1497: bipush 3
      // 1498: iaload
      // 1499: isub
      // 149a: iastore
      // 149b: dup
      // 149c: bipush 4
      // 149d: bipush 0
      // 149e: iastore
      // 149f: dup
      // 14a0: bipush 5
      // 14a1: bipush 0
      // 14a2: iastore
      // 14a3: astore 4
      // 14a5: bipush 6
      // 14a7: newarray 10
      // 14a9: dup
      // 14aa: bipush 0
      // 14ab: bipush 0
      // 14ac: iastore
      // 14ad: dup
      // 14ae: bipush 1
      // 14af: bipush 0
      // 14b0: iastore
      // 14b1: dup
      // 14b2: bipush 2
      // 14b3: bipush 0
      // 14b4: iastore
      // 14b5: dup
      // 14b6: bipush 3
      // 14b7: bipush 0
      // 14b8: iastore
      // 14b9: dup
      // 14ba: bipush 4
      // 14bb: bipush 50
      // 14bd: aload 0
      // 14be: getfield CarMaker.adna [I
      // 14c1: bipush 4
      // 14c2: iaload
      // 14c3: iadd
      // 14c4: iastore
      // 14c5: dup
      // 14c6: bipush 5
      // 14c7: bipush -50
      // 14c9: aload 0
      // 14ca: getfield CarMaker.adna [I
      // 14cd: bipush 5
      // 14ce: iaload
      // 14cf: isub
      // 14d0: iastore
      // 14d1: astore 5
      // 14d3: bipush 0
      // 14d4: istore 6
      // 14d6: iload 6
      // 14d8: bipush 6
      // 14da: if_icmpge 1527
      // 14dd: aload 3
      // 14de: iload 6
      // 14e0: dup2
      // 14e1: iaload
      // 14e2: aload 0
      // 14e3: getfield CarMaker.o LContO;
      // 14e6: getfield ContO.x I
      // 14e9: aload 0
      // 14ea: getfield CarMaker.m LMedium;
      // 14ed: getfield Medium.x I
      // 14f0: isub
      // 14f1: iadd
      // 14f2: iastore
      // 14f3: aload 4
      // 14f5: iload 6
      // 14f7: dup2
      // 14f8: iaload
      // 14f9: aload 0
      // 14fa: getfield CarMaker.o LContO;
      // 14fd: getfield ContO.y I
      // 1500: aload 0
      // 1501: getfield CarMaker.m LMedium;
      // 1504: getfield Medium.y I
      // 1507: isub
      // 1508: iadd
      // 1509: iastore
      // 150a: aload 5
      // 150c: iload 6
      // 150e: dup2
      // 150f: iaload
      // 1510: aload 0
      // 1511: getfield CarMaker.o LContO;
      // 1514: getfield ContO.z I
      // 1517: aload 0
      // 1518: getfield CarMaker.m LMedium;
      // 151b: getfield Medium.z I
      // 151e: isub
      // 151f: iadd
      // 1520: iastore
      // 1521: iinc 6 1
      // 1524: goto 14d6
      // 1527: aload 0
      // 1528: aload 3
      // 1529: aload 4
      // 152b: aload 0
      // 152c: getfield CarMaker.o LContO;
      // 152f: getfield ContO.x I
      // 1532: aload 0
      // 1533: getfield CarMaker.m LMedium;
      // 1536: getfield Medium.x I
      // 1539: isub
      // 153a: aload 0
      // 153b: getfield CarMaker.o LContO;
      // 153e: getfield ContO.y I
      // 1541: aload 0
      // 1542: getfield CarMaker.m LMedium;
      // 1545: getfield Medium.y I
      // 1548: isub
      // 1549: aload 0
      // 154a: getfield CarMaker.o LContO;
      // 154d: getfield ContO.xy I
      // 1550: bipush 6
      // 1552: invokevirtual CarMaker.rot ([I[IIIII)V
      // 1555: aload 0
      // 1556: aload 4
      // 1558: aload 5
      // 155a: aload 0
      // 155b: getfield CarMaker.o LContO;
      // 155e: getfield ContO.y I
      // 1561: aload 0
      // 1562: getfield CarMaker.m LMedium;
      // 1565: getfield Medium.y I
      // 1568: isub
      // 1569: aload 0
      // 156a: getfield CarMaker.o LContO;
      // 156d: getfield ContO.z I
      // 1570: aload 0
      // 1571: getfield CarMaker.m LMedium;
      // 1574: getfield Medium.z I
      // 1577: isub
      // 1578: aload 0
      // 1579: getfield CarMaker.o LContO;
      // 157c: getfield ContO.zy I
      // 157f: bipush 6
      // 1581: invokevirtual CarMaker.rot ([I[IIIII)V
      // 1584: aload 0
      // 1585: aload 3
      // 1586: aload 5
      // 1588: aload 0
      // 1589: getfield CarMaker.o LContO;
      // 158c: getfield ContO.x I
      // 158f: aload 0
      // 1590: getfield CarMaker.m LMedium;
      // 1593: getfield Medium.x I
      // 1596: isub
      // 1597: aload 0
      // 1598: getfield CarMaker.o LContO;
      // 159b: getfield ContO.z I
      // 159e: aload 0
      // 159f: getfield CarMaker.m LMedium;
      // 15a2: getfield Medium.z I
      // 15a5: isub
      // 15a6: aload 0
      // 15a7: getfield CarMaker.o LContO;
      // 15aa: getfield ContO.xz I
      // 15ad: bipush 6
      // 15af: invokevirtual CarMaker.rot ([I[IIIII)V
      // 15b2: aload 0
      // 15b3: aload 3
      // 15b4: aload 5
      // 15b6: aload 0
      // 15b7: getfield CarMaker.m LMedium;
      // 15ba: getfield Medium.cx I
      // 15bd: aload 0
      // 15be: getfield CarMaker.m LMedium;
      // 15c1: getfield Medium.cz I
      // 15c4: aload 0
      // 15c5: getfield CarMaker.m LMedium;
      // 15c8: getfield Medium.xz I
      // 15cb: bipush 6
      // 15cd: invokevirtual CarMaker.rot ([I[IIIII)V
      // 15d0: aload 0
      // 15d1: aload 4
      // 15d3: aload 5
      // 15d5: aload 0
      // 15d6: getfield CarMaker.m LMedium;
      // 15d9: getfield Medium.cy I
      // 15dc: aload 0
      // 15dd: getfield CarMaker.m LMedium;
      // 15e0: getfield Medium.cz I
      // 15e3: aload 0
      // 15e4: getfield CarMaker.m LMedium;
      // 15e7: getfield Medium.zy I
      // 15ea: bipush 6
      // 15ec: invokevirtual CarMaker.rot ([I[IIIII)V
      // 15ef: bipush 6
      // 15f1: newarray 10
      // 15f3: astore 6
      // 15f5: bipush 6
      // 15f7: newarray 10
      // 15f9: astore 7
      // 15fb: bipush 0
      // 15fc: istore 8
      // 15fe: iload 8
      // 1600: bipush 6
      // 1602: if_icmpge 1630
      // 1605: aload 6
      // 1607: iload 8
      // 1609: aload 0
      // 160a: aload 3
      // 160b: iload 8
      // 160d: iaload
      // 160e: aload 5
      // 1610: iload 8
      // 1612: iaload
      // 1613: invokevirtual CarMaker.xs (II)I
      // 1616: iastore
      // 1617: aload 7
      // 1619: iload 8
      // 161b: aload 0
      // 161c: aload 4
      // 161e: iload 8
      // 1620: iaload
      // 1621: aload 5
      // 1623: iload 8
      // 1625: iaload
      // 1626: invokevirtual CarMaker.ys (II)I
      // 1629: iastore
      // 162a: iinc 8 1
      // 162d: goto 15fe
      // 1630: aload 0
      // 1631: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1634: new java/awt/Color
      // 1637: dup
      // 1638: bipush 0
      // 1639: sipush 150
      // 163c: bipush 0
      // 163d: invokespecial java/awt/Color.<init> (III)V
      // 1640: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1643: aload 0
      // 1644: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1647: ldc_w "X+"
      // 164a: aload 6
      // 164c: bipush 0
      // 164d: iaload
      // 164e: bipush 7
      // 1650: isub
      // 1651: aload 7
      // 1653: bipush 0
      // 1654: iaload
      // 1655: bipush 4
      // 1656: iadd
      // 1657: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 165a: aload 0
      // 165b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 165e: ldc_w "-X"
      // 1661: aload 6
      // 1663: bipush 1
      // 1664: iaload
      // 1665: bipush 5
      // 1666: isub
      // 1667: aload 7
      // 1669: bipush 1
      // 166a: iaload
      // 166b: bipush 4
      // 166c: iadd
      // 166d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1670: aload 0
      // 1671: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1674: aload 6
      // 1676: bipush 0
      // 1677: iaload
      // 1678: aload 7
      // 167a: bipush 0
      // 167b: iaload
      // 167c: aload 6
      // 167e: bipush 1
      // 167f: iaload
      // 1680: aload 7
      // 1682: bipush 1
      // 1683: iaload
      // 1684: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1687: aload 0
      // 1688: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 168b: new java/awt/Color
      // 168e: dup
      // 168f: sipush 150
      // 1692: bipush 0
      // 1693: bipush 0
      // 1694: invokespecial java/awt/Color.<init> (III)V
      // 1697: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 169a: aload 0
      // 169b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 169e: ldc_w "Y+"
      // 16a1: aload 6
      // 16a3: bipush 2
      // 16a4: iaload
      // 16a5: bipush 7
      // 16a7: isub
      // 16a8: aload 7
      // 16aa: bipush 2
      // 16ab: iaload
      // 16ac: bipush 4
      // 16ad: iadd
      // 16ae: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 16b1: aload 0
      // 16b2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 16b5: ldc_w "-Y"
      // 16b8: aload 6
      // 16ba: bipush 3
      // 16bb: iaload
      // 16bc: bipush 5
      // 16bd: isub
      // 16be: aload 7
      // 16c0: bipush 3
      // 16c1: iaload
      // 16c2: bipush 4
      // 16c3: iadd
      // 16c4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 16c7: aload 0
      // 16c8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 16cb: aload 6
      // 16cd: bipush 2
      // 16ce: iaload
      // 16cf: aload 7
      // 16d1: bipush 2
      // 16d2: iaload
      // 16d3: aload 6
      // 16d5: bipush 3
      // 16d6: iaload
      // 16d7: aload 7
      // 16d9: bipush 3
      // 16da: iaload
      // 16db: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 16de: aload 0
      // 16df: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 16e2: new java/awt/Color
      // 16e5: dup
      // 16e6: bipush 0
      // 16e7: bipush 0
      // 16e8: sipush 150
      // 16eb: invokespecial java/awt/Color.<init> (III)V
      // 16ee: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 16f1: aload 0
      // 16f2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 16f5: ldc_w "Z+"
      // 16f8: aload 6
      // 16fa: bipush 4
      // 16fb: iaload
      // 16fc: bipush 7
      // 16fe: isub
      // 16ff: aload 7
      // 1701: bipush 4
      // 1702: iaload
      // 1703: bipush 4
      // 1704: iadd
      // 1705: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1708: aload 0
      // 1709: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 170c: ldc_w "-Z"
      // 170f: aload 6
      // 1711: bipush 5
      // 1712: iaload
      // 1713: bipush 5
      // 1714: isub
      // 1715: aload 7
      // 1717: bipush 5
      // 1718: iaload
      // 1719: bipush 4
      // 171a: iadd
      // 171b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 171e: aload 0
      // 171f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1722: aload 6
      // 1724: bipush 4
      // 1725: iaload
      // 1726: aload 7
      // 1728: bipush 4
      // 1729: iaload
      // 172a: aload 6
      // 172c: bipush 5
      // 172d: iaload
      // 172e: aload 7
      // 1730: bipush 5
      // 1731: iaload
      // 1732: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1735: bipush 0
      // 1736: istore 8
      // 1738: iload 8
      // 173a: bipush 6
      // 173c: if_icmpge 181d
      // 173f: aload 7
      // 1741: iload 8
      // 1743: iaload
      // 1744: sipush 207
      // 1747: isub
      // 1748: invokestatic java/lang/Math.abs (I)I
      // 174b: i2f
      // 174c: ldc_w 1.91
      // 174f: fmul
      // 1750: aload 6
      // 1752: iload 8
      // 1754: iaload
      // 1755: sipush 350
      // 1758: isub
      // 1759: invokestatic java/lang/Math.abs (I)I
      // 175c: i2f
      // 175d: fcmpl
      // 175e: ifle 17a9
      // 1761: aload 7
      // 1763: iload 8
      // 1765: iaload
      // 1766: sipush 207
      // 1769: isub
      // 176a: invokestatic java/lang/Math.abs (I)I
      // 176d: sipush 170
      // 1770: isub
      // 1771: invokestatic java/lang/Math.abs (I)I
      // 1774: bipush 10
      // 1776: if_icmple 17ee
      // 1779: aload 7
      // 177b: iload 8
      // 177d: iaload
      // 177e: sipush 207
      // 1781: isub
      // 1782: invokestatic java/lang/Math.abs (I)I
      // 1785: sipush 170
      // 1788: if_icmpge 179a
      // 178b: aload 0
      // 178c: getfield CarMaker.adna [I
      // 178f: iload 8
      // 1791: dup2
      // 1792: iaload
      // 1793: bipush 10
      // 1795: iadd
      // 1796: iastore
      // 1797: goto 17ee
      // 179a: aload 0
      // 179b: getfield CarMaker.adna [I
      // 179e: iload 8
      // 17a0: dup2
      // 17a1: iaload
      // 17a2: bipush 10
      // 17a4: isub
      // 17a5: iastore
      // 17a6: goto 17ee
      // 17a9: aload 6
      // 17ab: iload 8
      // 17ad: iaload
      // 17ae: sipush 350
      // 17b1: isub
      // 17b2: invokestatic java/lang/Math.abs (I)I
      // 17b5: sipush 338
      // 17b8: isub
      // 17b9: invokestatic java/lang/Math.abs (I)I
      // 17bc: bipush 10
      // 17be: if_icmple 17ee
      // 17c1: aload 6
      // 17c3: iload 8
      // 17c5: iaload
      // 17c6: sipush 350
      // 17c9: isub
      // 17ca: invokestatic java/lang/Math.abs (I)I
      // 17cd: sipush 338
      // 17d0: if_icmpge 17e2
      // 17d3: aload 0
      // 17d4: getfield CarMaker.adna [I
      // 17d7: iload 8
      // 17d9: dup2
      // 17da: iaload
      // 17db: bipush 10
      // 17dd: iadd
      // 17de: iastore
      // 17df: goto 17ee
      // 17e2: aload 0
      // 17e3: getfield CarMaker.adna [I
      // 17e6: iload 8
      // 17e8: dup2
      // 17e9: iaload
      // 17ea: bipush 10
      // 17ec: isub
      // 17ed: iastore
      // 17ee: aload 0
      // 17ef: getfield CarMaker.adna [I
      // 17f2: iload 8
      // 17f4: iaload
      // 17f5: sipush 276
      // 17f8: if_icmple 1805
      // 17fb: aload 0
      // 17fc: getfield CarMaker.adna [I
      // 17ff: iload 8
      // 1801: sipush 276
      // 1804: iastore
      // 1805: aload 0
      // 1806: getfield CarMaker.adna [I
      // 1809: iload 8
      // 180b: iaload
      // 180c: ifge 1817
      // 180f: aload 0
      // 1810: getfield CarMaker.adna [I
      // 1813: iload 8
      // 1815: bipush 0
      // 1816: iastore
      // 1817: iinc 8 1
      // 181a: goto 1738
      // 181d: aload 0
      // 181e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1821: new java/awt/Color
      // 1824: dup
      // 1825: sipush 205
      // 1828: sipush 200
      // 182b: sipush 200
      // 182e: invokespecial java/awt/Color.<init> (III)V
      // 1831: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1834: aload 0
      // 1835: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1838: bipush 0
      // 1839: sipush 390
      // 183c: sipush 700
      // 183f: bipush 20
      // 1841: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 1844: aload 0
      // 1845: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1848: new java/awt/Color
      // 184b: dup
      // 184c: sipush 225
      // 184f: sipush 225
      // 1852: sipush 225
      // 1855: invokespecial java/awt/Color.<init> (III)V
      // 1858: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 185b: aload 0
      // 185c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 185f: bipush 0
      // 1860: sipush 410
      // 1863: sipush 700
      // 1866: sipush 140
      // 1869: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 186c: aload 0
      // 186d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1870: new java/awt/Font
      // 1873: dup
      // 1874: ldc_w "Arial"
      // 1877: bipush 1
      // 1878: bipush 12
      // 187a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 187d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1880: aload 0
      // 1881: aload 0
      // 1882: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1885: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1888: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 188b: bipush 7
      // 188d: anewarray 112
      // 1890: dup
      // 1891: bipush 0
      // 1892: ldc_w "3D Controls"
      // 1895: aastore
      // 1896: dup
      // 1897: bipush 1
      // 1898: ldc_w "Color Edit"
      // 189b: aastore
      // 189c: dup
      // 189d: bipush 2
      // 189e: ldc_w "Scale & Align"
      // 18a1: aastore
      // 18a2: dup
      // 18a3: bipush 3
      // 18a4: ldc_w "Wheels"
      // 18a7: aastore
      // 18a8: dup
      // 18a9: bipush 4
      // 18aa: ldc_w "Stats & Class"
      // 18ad: aastore
      // 18ae: dup
      // 18af: bipush 5
      // 18b0: ldc_w "Physics"
      // 18b3: aastore
      // 18b4: dup
      // 18b5: bipush 6
      // 18b7: ldc_w "Test Drive"
      // 18ba: aastore
      // 18bb: astore 3
      // 18bc: bipush 4
      // 18bd: newarray 10
      // 18bf: dup
      // 18c0: bipush 0
      // 18c1: bipush 0
      // 18c2: iastore
      // 18c3: dup
      // 18c4: bipush 1
      // 18c5: bipush 0
      // 18c6: iastore
      // 18c7: dup
      // 18c8: bipush 2
      // 18c9: bipush 100
      // 18cb: iastore
      // 18cc: dup
      // 18cd: bipush 3
      // 18ce: bipush 90
      // 18d0: iastore
      // 18d1: astore 4
      // 18d3: bipush 4
      // 18d4: newarray 10
      // 18d6: dup
      // 18d7: bipush 0
      // 18d8: sipush 390
      // 18db: iastore
      // 18dc: dup
      // 18dd: bipush 1
      // 18de: sipush 410
      // 18e1: iastore
      // 18e2: dup
      // 18e3: bipush 2
      // 18e4: sipush 410
      // 18e7: iastore
      // 18e8: dup
      // 18e9: bipush 3
      // 18ea: sipush 390
      // 18ed: iastore
      // 18ee: astore 5
      // 18f0: bipush 0
      // 18f1: istore 6
      // 18f3: iload 6
      // 18f5: bipush 7
      // 18f7: if_icmpge 1a0a
      // 18fa: aload 0
      // 18fb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 18fe: new java/awt/Color
      // 1901: dup
      // 1902: sipush 170
      // 1905: sipush 170
      // 1908: sipush 170
      // 190b: invokespecial java/awt/Color.<init> (III)V
      // 190e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1911: aload 0
      // 1912: getfield CarMaker.xm I
      // 1915: aload 4
      // 1917: bipush 0
      // 1918: iaload
      // 1919: if_icmple 1952
      // 191c: aload 0
      // 191d: getfield CarMaker.xm I
      // 1920: aload 4
      // 1922: bipush 3
      // 1923: iaload
      // 1924: if_icmpge 1952
      // 1927: aload 0
      // 1928: getfield CarMaker.ym I
      // 192b: sipush 390
      // 192e: if_icmple 1952
      // 1931: aload 0
      // 1932: getfield CarMaker.ym I
      // 1935: sipush 410
      // 1938: if_icmpge 1952
      // 193b: aload 0
      // 193c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 193f: new java/awt/Color
      // 1942: dup
      // 1943: sipush 190
      // 1946: sipush 190
      // 1949: sipush 190
      // 194c: invokespecial java/awt/Color.<init> (III)V
      // 194f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1952: aload 0
      // 1953: getfield CarMaker.dtab I
      // 1956: iload 6
      // 1958: if_icmpne 1972
      // 195b: aload 0
      // 195c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 195f: new java/awt/Color
      // 1962: dup
      // 1963: sipush 225
      // 1966: sipush 225
      // 1969: sipush 225
      // 196c: invokespecial java/awt/Color.<init> (III)V
      // 196f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1972: aload 0
      // 1973: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1976: aload 4
      // 1978: aload 5
      // 197a: bipush 4
      // 197b: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // 197e: aload 0
      // 197f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1982: new java/awt/Color
      // 1985: dup
      // 1986: bipush 0
      // 1987: bipush 0
      // 1988: bipush 0
      // 1989: invokespecial java/awt/Color.<init> (III)V
      // 198c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 198f: aload 0
      // 1990: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1993: aload 3
      // 1994: iload 6
      // 1996: aaload
      // 1997: iload 6
      // 1999: bipush 100
      // 199b: imul
      // 199c: bipush 47
      // 199e: iadd
      // 199f: aload 0
      // 19a0: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 19a3: aload 3
      // 19a4: iload 6
      // 19a6: aaload
      // 19a7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 19aa: bipush 2
      // 19ab: idiv
      // 19ac: isub
      // 19ad: sipush 404
      // 19b0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 19b3: aload 0
      // 19b4: getfield CarMaker.xm I
      // 19b7: aload 4
      // 19b9: bipush 0
      // 19ba: iaload
      // 19bb: if_icmple 19eb
      // 19be: aload 0
      // 19bf: getfield CarMaker.xm I
      // 19c2: aload 4
      // 19c4: bipush 3
      // 19c5: iaload
      // 19c6: if_icmpge 19eb
      // 19c9: aload 0
      // 19ca: getfield CarMaker.ym I
      // 19cd: sipush 390
      // 19d0: if_icmple 19eb
      // 19d3: aload 0
      // 19d4: getfield CarMaker.ym I
      // 19d7: sipush 410
      // 19da: if_icmpge 19eb
      // 19dd: aload 0
      // 19de: getfield CarMaker.mouses I
      // 19e1: bipush -1
      // 19e2: if_icmpne 19eb
      // 19e5: aload 0
      // 19e6: iload 6
      // 19e8: putfield CarMaker.dtab I
      // 19eb: bipush 0
      // 19ec: istore 7
      // 19ee: iload 7
      // 19f0: bipush 4
      // 19f1: if_icmpge 1a04
      // 19f4: aload 4
      // 19f6: iload 7
      // 19f8: dup2
      // 19f9: iaload
      // 19fa: bipush 100
      // 19fc: iadd
      // 19fd: iastore
      // 19fe: iinc 7 1
      // 1a01: goto 19ee
      // 1a04: iinc 6 1
      // 1a07: goto 18f3
      // 1a0a: aload 0
      // 1a0b: getfield CarMaker.dtabed I
      // 1a0e: aload 0
      // 1a0f: getfield CarMaker.dtab I
      // 1a12: if_icmpeq 1a51
      // 1a15: aload 0
      // 1a16: getfield CarMaker.dtabed I
      // 1a19: bipush -1
      // 1a1a: if_icmpeq 1a3d
      // 1a1d: aload 0
      // 1a1e: getfield CarMaker.editor Ljava/awt/TextArea;
      // 1a21: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 1a24: aload 0
      // 1a25: getfield CarMaker.lastedo Ljava/lang/String;
      // 1a28: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1a2b: ifne 1a39
      // 1a2e: aload 0
      // 1a2f: getfield CarMaker.editor Ljava/awt/TextArea;
      // 1a32: aload 0
      // 1a33: getfield CarMaker.lastedo Ljava/lang/String;
      // 1a36: invokevirtual java/awt/TextArea.setText (Ljava/lang/String;)V
      // 1a39: aload 0
      // 1a3a: invokevirtual CarMaker.setupo ()V
      // 1a3d: aload 0
      // 1a3e: new java/awt/Cursor
      // 1a41: dup
      // 1a42: bipush 0
      // 1a43: invokespecial java/awt/Cursor.<init> (I)V
      // 1a46: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 1a49: aload 0
      // 1a4a: invokevirtual CarMaker.hidefields ()V
      // 1a4d: aload 0
      // 1a4e: invokevirtual CarMaker.requestFocus ()V
      // 1a51: aload 0
      // 1a52: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1a55: new java/awt/Color
      // 1a58: dup
      // 1a59: bipush 0
      // 1a5a: bipush 0
      // 1a5b: bipush 0
      // 1a5c: invokespecial java/awt/Color.<init> (III)V
      // 1a5f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1a62: aload 0
      // 1a63: getfield CarMaker.dtab I
      // 1a66: ifne 1aa5
      // 1a69: aload 0
      // 1a6a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1a6d: ldc_w "Rotate Car around its X & Z Axis using:  [ Keyboard Arrows ] "
      // 1a70: bipush 20
      // 1a72: sipush 440
      // 1a75: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1a78: aload 0
      // 1a79: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1a7c: ldc_w "Rotate Car fully around the Y Axis using:    [ < ]  &  [ > ]    or    [ A ]  &  [ D ]    or    [ 4 ]  &  [ 6 ]    Keys"
      // 1a7f: bipush 20
      // 1a81: sipush 465
      // 1a84: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1a87: aload 0
      // 1a88: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1a8b: ldc_w "Move Car Up and Down using:    [ - ]  &  [ + ]    Keys"
      // 1a8e: bipush 20
      // 1a90: sipush 490
      // 1a93: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1a96: aload 0
      // 1a97: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1a9a: ldc_w "Move Car Forwards and Backwards using:    [ W ]  &  [ S ]    or    [ 8 ]  &  [ 2 ]    or    [ Enter ]  &  [ Backspace ]    Keys"
      // 1a9d: bipush 20
      // 1a9f: sipush 515
      // 1aa2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1aa5: aload 0
      // 1aa6: getfield CarMaker.dtab I
      // 1aa9: bipush 1
      // 1aaa: if_icmpne 27c4
      // 1aad: aload 0
      // 1aae: getfield CarMaker.o LContO;
      // 1ab1: getfield ContO.colok I
      // 1ab4: bipush 2
      // 1ab5: if_icmpeq 1b06
      // 1ab8: aload 0
      // 1ab9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1abc: new java/awt/Font
      // 1abf: dup
      // 1ac0: ldc_w "Arial"
      // 1ac3: bipush 1
      // 1ac4: bipush 13
      // 1ac6: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1ac9: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1acc: aload 0
      // 1acd: aload 0
      // 1ace: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1ad1: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1ad4: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 1ad7: aload 0
      // 1ad8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1adb: ldc_w "[  First & Second Color not defined yet  ]"
      // 1ade: sipush 350
      // 1ae1: aload 0
      // 1ae2: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 1ae5: ldc_w "[  First & Second Color not defined yet  ]"
      // 1ae8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 1aeb: bipush 2
      // 1aec: idiv
      // 1aed: isub
      // 1aee: sipush 450
      // 1af1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1af4: aload 0
      // 1af5: ldc_w " Define 1st and 2nd Color "
      // 1af8: sipush 350
      // 1afb: sipush 490
      // 1afe: bipush 0
      // 1aff: bipush 1
      // 1b00: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 1b03: goto 27c4
      // 1b06: aload 0
      // 1b07: getfield CarMaker.dtabed I
      // 1b0a: aload 0
      // 1b0b: getfield CarMaker.dtab I
      // 1b0e: if_icmpeq 1c54
      // 1b11: aload 0
      // 1b12: new java/lang/StringBuilder
      // 1b15: dup
      // 1b16: invokespecial java/lang/StringBuilder.<init> ()V
      // 1b19: ldc_w "("
      // 1b1c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1b1f: aload 0
      // 1b20: getfield CarMaker.o LContO;
      // 1b23: getfield ContO.fcol [I
      // 1b26: bipush 0
      // 1b27: iaload
      // 1b28: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1b2b: ldc_w ","
      // 1b2e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1b31: aload 0
      // 1b32: getfield CarMaker.o LContO;
      // 1b35: getfield ContO.fcol [I
      // 1b38: bipush 1
      // 1b39: iaload
      // 1b3a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1b3d: ldc_w ","
      // 1b40: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1b43: aload 0
      // 1b44: getfield CarMaker.o LContO;
      // 1b47: getfield ContO.fcol [I
      // 1b4a: bipush 2
      // 1b4b: iaload
      // 1b4c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1b4f: ldc_w ")"
      // 1b52: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1b55: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1b58: putfield CarMaker.fcol Ljava/lang/String;
      // 1b5b: aload 0
      // 1b5c: getfield CarMaker.srch Ljava/awt/TextField;
      // 1b5f: aload 0
      // 1b60: getfield CarMaker.fcol Ljava/lang/String;
      // 1b63: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 1b66: aload 0
      // 1b67: aload 0
      // 1b68: getfield CarMaker.fcol Ljava/lang/String;
      // 1b6b: putfield CarMaker.ofcol Ljava/lang/String;
      // 1b6e: aload 0
      // 1b6f: getfield CarMaker.o LContO;
      // 1b72: getfield ContO.fcol [I
      // 1b75: bipush 0
      // 1b76: iaload
      // 1b77: aload 0
      // 1b78: getfield CarMaker.o LContO;
      // 1b7b: getfield ContO.fcol [I
      // 1b7e: bipush 1
      // 1b7f: iaload
      // 1b80: aload 0
      // 1b81: getfield CarMaker.o LContO;
      // 1b84: getfield ContO.fcol [I
      // 1b87: bipush 2
      // 1b88: iaload
      // 1b89: aload 0
      // 1b8a: getfield CarMaker.fhsb [F
      // 1b8d: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 1b90: pop
      // 1b91: aload 0
      // 1b92: getfield CarMaker.fhsb [F
      // 1b95: bipush 1
      // 1b96: faload
      // 1b97: fstore 6
      // 1b99: aload 0
      // 1b9a: getfield CarMaker.fhsb [F
      // 1b9d: bipush 1
      // 1b9e: aload 0
      // 1b9f: getfield CarMaker.fhsb [F
      // 1ba2: bipush 2
      // 1ba3: faload
      // 1ba4: fastore
      // 1ba5: aload 0
      // 1ba6: getfield CarMaker.fhsb [F
      // 1ba9: bipush 2
      // 1baa: fload 6
      // 1bac: fastore
      // 1bad: aload 0
      // 1bae: new java/lang/StringBuilder
      // 1bb1: dup
      // 1bb2: invokespecial java/lang/StringBuilder.<init> ()V
      // 1bb5: ldc_w "("
      // 1bb8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1bbb: aload 0
      // 1bbc: getfield CarMaker.o LContO;
      // 1bbf: getfield ContO.scol [I
      // 1bc2: bipush 0
      // 1bc3: iaload
      // 1bc4: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1bc7: ldc_w ","
      // 1bca: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1bcd: aload 0
      // 1bce: getfield CarMaker.o LContO;
      // 1bd1: getfield ContO.scol [I
      // 1bd4: bipush 1
      // 1bd5: iaload
      // 1bd6: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1bd9: ldc_w ","
      // 1bdc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1bdf: aload 0
      // 1be0: getfield CarMaker.o LContO;
      // 1be3: getfield ContO.scol [I
      // 1be6: bipush 2
      // 1be7: iaload
      // 1be8: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1beb: ldc_w ")"
      // 1bee: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1bf1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1bf4: putfield CarMaker.scol Ljava/lang/String;
      // 1bf7: aload 0
      // 1bf8: getfield CarMaker.rplc Ljava/awt/TextField;
      // 1bfb: aload 0
      // 1bfc: getfield CarMaker.scol Ljava/lang/String;
      // 1bff: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 1c02: aload 0
      // 1c03: aload 0
      // 1c04: getfield CarMaker.scol Ljava/lang/String;
      // 1c07: putfield CarMaker.oscol Ljava/lang/String;
      // 1c0a: aload 0
      // 1c0b: getfield CarMaker.o LContO;
      // 1c0e: getfield ContO.scol [I
      // 1c11: bipush 0
      // 1c12: iaload
      // 1c13: aload 0
      // 1c14: getfield CarMaker.o LContO;
      // 1c17: getfield ContO.scol [I
      // 1c1a: bipush 1
      // 1c1b: iaload
      // 1c1c: aload 0
      // 1c1d: getfield CarMaker.o LContO;
      // 1c20: getfield ContO.scol [I
      // 1c23: bipush 2
      // 1c24: iaload
      // 1c25: aload 0
      // 1c26: getfield CarMaker.shsb [F
      // 1c29: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 1c2c: pop
      // 1c2d: aload 0
      // 1c2e: getfield CarMaker.shsb [F
      // 1c31: bipush 1
      // 1c32: faload
      // 1c33: fstore 6
      // 1c35: aload 0
      // 1c36: getfield CarMaker.shsb [F
      // 1c39: bipush 1
      // 1c3a: aload 0
      // 1c3b: getfield CarMaker.shsb [F
      // 1c3e: bipush 2
      // 1c3f: faload
      // 1c40: fastore
      // 1c41: aload 0
      // 1c42: getfield CarMaker.shsb [F
      // 1c45: bipush 2
      // 1c46: fload 6
      // 1c48: fastore
      // 1c49: aload 0
      // 1c4a: bipush 51
      // 1c4c: putfield CarMaker.bfo I
      // 1c4f: aload 0
      // 1c50: bipush -1
      // 1c51: putfield CarMaker.mouseon I
      // 1c54: aload 0
      // 1c55: getfield CarMaker.mouses I
      // 1c58: bipush 1
      // 1c59: if_icmpeq 1c61
      // 1c5c: aload 0
      // 1c5d: bipush -1
      // 1c5e: putfield CarMaker.mouseon I
      // 1c61: aload 0
      // 1c62: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1c65: new java/awt/Color
      // 1c68: dup
      // 1c69: sipush 170
      // 1c6c: sipush 170
      // 1c6f: sipush 170
      // 1c72: invokespecial java/awt/Color.<init> (III)V
      // 1c75: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1c78: aload 0
      // 1c79: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1c7c: bipush 20
      // 1c7e: sipush 425
      // 1c81: sipush 320
      // 1c84: bipush 110
      // 1c86: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 1c89: aload 0
      // 1c8a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1c8d: new java/awt/Color
      // 1c90: dup
      // 1c91: sipush 225
      // 1c94: sipush 225
      // 1c97: sipush 225
      // 1c9a: invokespecial java/awt/Color.<init> (III)V
      // 1c9d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1ca0: aload 0
      // 1ca1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1ca4: sipush 141
      // 1ca7: sipush 419
      // 1caa: bipush 77
      // 1cac: bipush 9
      // 1cae: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 1cb1: aload 0
      // 1cb2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1cb5: new java/awt/Color
      // 1cb8: dup
      // 1cb9: bipush 0
      // 1cba: bipush 0
      // 1cbb: bipush 0
      // 1cbc: invokespecial java/awt/Color.<init> (III)V
      // 1cbf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1cc2: aload 0
      // 1cc3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1cc6: ldc_w "First Color"
      // 1cc9: sipush 151
      // 1ccc: sipush 428
      // 1ccf: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cd2: aload 0
      // 1cd3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1cd6: ldc_w "Hue:"
      // 1cd9: bipush 75
      // 1cdb: sipush 450
      // 1cde: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1ce1: aload 0
      // 1ce2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1ce5: ldc_w "Brightness:"
      // 1ce8: bipush 35
      // 1cea: sipush 470
      // 1ced: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cf0: aload 0
      // 1cf1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1cf4: ldc_w "Saturation:"
      // 1cf7: bipush 38
      // 1cf9: sipush 490
      // 1cfc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cff: aload 0
      // 1d00: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1d03: ldc_w "RGB Value:"
      // 1d06: bipush 38
      // 1d08: sipush 520
      // 1d0b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1d0e: aload 0
      // 1d0f: aload 0
      // 1d10: getfield CarMaker.srch Ljava/awt/TextField;
      // 1d13: bipush 106
      // 1d15: sipush 504
      // 1d18: sipush 129
      // 1d1b: bipush 22
      // 1d1d: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 1d20: aload 0
      // 1d21: getfield CarMaker.srch Ljava/awt/TextField;
      // 1d24: invokevirtual java/awt/TextField.hasFocus ()Z
      // 1d27: ifeq 1d2f
      // 1d2a: aload 0
      // 1d2b: bipush 0
      // 1d2c: putfield CarMaker.focuson Z
      // 1d2f: aload 0
      // 1d30: getfield CarMaker.srch Ljava/awt/TextField;
      // 1d33: invokevirtual java/awt/TextField.isShowing ()Z
      // 1d36: ifne 1d40
      // 1d39: aload 0
      // 1d3a: getfield CarMaker.srch Ljava/awt/TextField;
      // 1d3d: invokevirtual java/awt/TextField.show ()V
      // 1d40: bipush 0
      // 1d41: istore 6
      // 1d43: iload 6
      // 1d45: sipush 200
      // 1d48: if_icmpge 1d7d
      // 1d4b: aload 0
      // 1d4c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1d4f: iload 6
      // 1d51: i2f
      // 1d52: f2d
      // 1d53: ldc2_w 0.005
      // 1d56: dmul
      // 1d57: d2f
      // 1d58: fconst_1
      // 1d59: fconst_1
      // 1d5a: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 1d5d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1d60: aload 0
      // 1d61: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1d64: bipush 110
      // 1d66: iload 6
      // 1d68: iadd
      // 1d69: sipush 442
      // 1d6c: bipush 110
      // 1d6e: iload 6
      // 1d70: iadd
      // 1d71: sipush 449
      // 1d74: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1d77: iinc 6 1
      // 1d7a: goto 1d43
      // 1d7d: bipush 0
      // 1d7e: istore 6
      // 1d80: iload 6
      // 1d82: sipush 200
      // 1d85: if_icmpge 1dbe
      // 1d88: aload 0
      // 1d89: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1d8c: fconst_0
      // 1d8d: fconst_0
      // 1d8e: ldc_w 0.2
      // 1d91: iload 6
      // 1d93: i2f
      // 1d94: f2d
      // 1d95: ldc2_w 0.004
      // 1d98: dmul
      // 1d99: d2f
      // 1d9a: fadd
      // 1d9b: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 1d9e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1da1: aload 0
      // 1da2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1da5: bipush 110
      // 1da7: iload 6
      // 1da9: iadd
      // 1daa: sipush 462
      // 1dad: bipush 110
      // 1daf: iload 6
      // 1db1: iadd
      // 1db2: sipush 469
      // 1db5: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1db8: iinc 6 1
      // 1dbb: goto 1d80
      // 1dbe: bipush 0
      // 1dbf: istore 6
      // 1dc1: iload 6
      // 1dc3: sipush 200
      // 1dc6: if_icmpge 1e05
      // 1dc9: aload 0
      // 1dca: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1dcd: aload 0
      // 1dce: getfield CarMaker.fhsb [F
      // 1dd1: bipush 0
      // 1dd2: faload
      // 1dd3: iload 6
      // 1dd5: i2f
      // 1dd6: f2d
      // 1dd7: ldc2_w 0.005
      // 1dda: dmul
      // 1ddb: d2f
      // 1ddc: aload 0
      // 1ddd: getfield CarMaker.fhsb [F
      // 1de0: bipush 1
      // 1de1: faload
      // 1de2: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 1de5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1de8: aload 0
      // 1de9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1dec: bipush 110
      // 1dee: iload 6
      // 1df0: iadd
      // 1df1: sipush 482
      // 1df4: bipush 110
      // 1df6: iload 6
      // 1df8: iadd
      // 1df9: sipush 489
      // 1dfc: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1dff: iinc 6 1
      // 1e02: goto 1dc1
      // 1e05: bipush 0
      // 1e06: istore 6
      // 1e08: iload 6
      // 1e0a: bipush 3
      // 1e0b: if_icmpge 1f9d
      // 1e0e: aload 0
      // 1e0f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1e12: new java/awt/Color
      // 1e15: dup
      // 1e16: bipush 0
      // 1e17: bipush 0
      // 1e18: bipush 0
      // 1e19: invokespecial java/awt/Color.<init> (III)V
      // 1e1c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1e1f: aload 0
      // 1e20: getfield CarMaker.fhsb [F
      // 1e23: iload 6
      // 1e25: faload
      // 1e26: ldc_w 200.0
      // 1e29: fmul
      // 1e2a: fstore 7
      // 1e2c: iload 6
      // 1e2e: bipush 1
      // 1e2f: if_icmpne 1e43
      // 1e32: aload 0
      // 1e33: getfield CarMaker.fhsb [F
      // 1e36: iload 6
      // 1e38: faload
      // 1e39: ldc_w 0.2
      // 1e3c: fsub
      // 1e3d: ldc_w 250.0
      // 1e40: fmul
      // 1e41: fstore 7
      // 1e43: aload 0
      // 1e44: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1e47: ldc_w 110.0
      // 1e4a: fload 7
      // 1e4c: fadd
      // 1e4d: f2i
      // 1e4e: sipush 442
      // 1e51: iload 6
      // 1e53: bipush 20
      // 1e55: imul
      // 1e56: iadd
      // 1e57: ldc_w 110.0
      // 1e5a: fload 7
      // 1e5c: fadd
      // 1e5d: f2i
      // 1e5e: sipush 449
      // 1e61: iload 6
      // 1e63: bipush 20
      // 1e65: imul
      // 1e66: iadd
      // 1e67: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1e6a: aload 0
      // 1e6b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1e6e: ldc_w 111.0
      // 1e71: fload 7
      // 1e73: fadd
      // 1e74: f2i
      // 1e75: sipush 442
      // 1e78: iload 6
      // 1e7a: bipush 20
      // 1e7c: imul
      // 1e7d: iadd
      // 1e7e: ldc_w 111.0
      // 1e81: fload 7
      // 1e83: fadd
      // 1e84: f2i
      // 1e85: sipush 449
      // 1e88: iload 6
      // 1e8a: bipush 20
      // 1e8c: imul
      // 1e8d: iadd
      // 1e8e: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1e91: aload 0
      // 1e92: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1e95: ldc_w 109.0
      // 1e98: fload 7
      // 1e9a: fadd
      // 1e9b: f2i
      // 1e9c: sipush 450
      // 1e9f: iload 6
      // 1ea1: bipush 20
      // 1ea3: imul
      // 1ea4: iadd
      // 1ea5: bipush 4
      // 1ea6: bipush 2
      // 1ea7: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 1eaa: aload 0
      // 1eab: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1eae: ldc_w 108.0
      // 1eb1: fload 7
      // 1eb3: fadd
      // 1eb4: f2i
      // 1eb5: sipush 452
      // 1eb8: iload 6
      // 1eba: bipush 20
      // 1ebc: imul
      // 1ebd: iadd
      // 1ebe: ldc_w 113.0
      // 1ec1: fload 7
      // 1ec3: fadd
      // 1ec4: f2i
      // 1ec5: sipush 452
      // 1ec8: iload 6
      // 1eca: bipush 20
      // 1ecc: imul
      // 1ecd: iadd
      // 1ece: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1ed1: aload 0
      // 1ed2: getfield CarMaker.xm I
      // 1ed5: bipush 107
      // 1ed7: if_icmple 1f1a
      // 1eda: aload 0
      // 1edb: getfield CarMaker.xm I
      // 1ede: sipush 313
      // 1ee1: if_icmpge 1f1a
      // 1ee4: aload 0
      // 1ee5: getfield CarMaker.ym I
      // 1ee8: sipush 439
      // 1eeb: iload 6
      // 1eed: bipush 20
      // 1eef: imul
      // 1ef0: iadd
      // 1ef1: if_icmple 1f1a
      // 1ef4: aload 0
      // 1ef5: getfield CarMaker.ym I
      // 1ef8: sipush 452
      // 1efb: iload 6
      // 1efd: bipush 20
      // 1eff: imul
      // 1f00: iadd
      // 1f01: if_icmpge 1f1a
      // 1f04: aload 0
      // 1f05: getfield CarMaker.mouses I
      // 1f08: bipush 1
      // 1f09: if_icmpne 1f1a
      // 1f0c: aload 0
      // 1f0d: getfield CarMaker.mouseon I
      // 1f10: bipush -1
      // 1f11: if_icmpne 1f1a
      // 1f14: aload 0
      // 1f15: iload 6
      // 1f17: putfield CarMaker.mouseon I
      // 1f1a: aload 0
      // 1f1b: getfield CarMaker.mouseon I
      // 1f1e: iload 6
      // 1f20: if_icmpne 1f97
      // 1f23: iload 6
      // 1f25: bipush 1
      // 1f26: if_icmpne 1f5c
      // 1f29: aload 0
      // 1f2a: getfield CarMaker.fhsb [F
      // 1f2d: iload 6
      // 1f2f: ldc_w 0.2
      // 1f32: aload 0
      // 1f33: getfield CarMaker.xm I
      // 1f36: bipush 110
      // 1f38: isub
      // 1f39: i2f
      // 1f3a: ldc_w 250.0
      // 1f3d: fdiv
      // 1f3e: fadd
      // 1f3f: fastore
      // 1f40: aload 0
      // 1f41: getfield CarMaker.fhsb [F
      // 1f44: iload 6
      // 1f46: faload
      // 1f47: f2d
      // 1f48: ldc2_w 0.2
      // 1f4b: dcmpg
      // 1f4c: ifge 1f6f
      // 1f4f: aload 0
      // 1f50: getfield CarMaker.fhsb [F
      // 1f53: iload 6
      // 1f55: ldc_w 0.2
      // 1f58: fastore
      // 1f59: goto 1f6f
      // 1f5c: aload 0
      // 1f5d: getfield CarMaker.fhsb [F
      // 1f60: iload 6
      // 1f62: aload 0
      // 1f63: getfield CarMaker.xm I
      // 1f66: bipush 110
      // 1f68: isub
      // 1f69: i2f
      // 1f6a: ldc_w 200.0
      // 1f6d: fdiv
      // 1f6e: fastore
      // 1f6f: aload 0
      // 1f70: getfield CarMaker.fhsb [F
      // 1f73: iload 6
      // 1f75: faload
      // 1f76: fconst_1
      // 1f77: fcmpl
      // 1f78: ifle 1f83
      // 1f7b: aload 0
      // 1f7c: getfield CarMaker.fhsb [F
      // 1f7f: iload 6
      // 1f81: fconst_1
      // 1f82: fastore
      // 1f83: aload 0
      // 1f84: getfield CarMaker.fhsb [F
      // 1f87: iload 6
      // 1f89: faload
      // 1f8a: fconst_0
      // 1f8b: fcmpg
      // 1f8c: ifge 1f97
      // 1f8f: aload 0
      // 1f90: getfield CarMaker.fhsb [F
      // 1f93: iload 6
      // 1f95: fconst_0
      // 1f96: fastore
      // 1f97: iinc 6 1
      // 1f9a: goto 1e08
      // 1f9d: aload 0
      // 1f9e: ldc_w " Save "
      // 1fa1: sipush 300
      // 1fa4: sipush 520
      // 1fa7: bipush 0
      // 1fa8: aload 0
      // 1fa9: getfield CarMaker.fcol Ljava/lang/String;
      // 1fac: aload 0
      // 1fad: getfield CarMaker.ofcol Ljava/lang/String;
      // 1fb0: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1fb3: ifne 1fba
      // 1fb6: bipush 1
      // 1fb7: goto 1fbb
      // 1fba: bipush 0
      // 1fbb: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 1fbe: aload 0
      // 1fbf: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1fc2: new java/awt/Color
      // 1fc5: dup
      // 1fc6: sipush 170
      // 1fc9: sipush 170
      // 1fcc: sipush 170
      // 1fcf: invokespecial java/awt/Color.<init> (III)V
      // 1fd2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1fd5: aload 0
      // 1fd6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1fd9: sipush 360
      // 1fdc: sipush 425
      // 1fdf: sipush 320
      // 1fe2: bipush 110
      // 1fe4: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 1fe7: aload 0
      // 1fe8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 1feb: new java/awt/Color
      // 1fee: dup
      // 1fef: sipush 225
      // 1ff2: sipush 225
      // 1ff5: sipush 225
      // 1ff8: invokespecial java/awt/Color.<init> (III)V
      // 1ffb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1ffe: aload 0
      // 1fff: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2002: sipush 472
      // 2005: sipush 419
      // 2008: bipush 95
      // 200a: bipush 9
      // 200c: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 200f: aload 0
      // 2010: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2013: new java/awt/Color
      // 2016: dup
      // 2017: bipush 0
      // 2018: bipush 0
      // 2019: bipush 0
      // 201a: invokespecial java/awt/Color.<init> (III)V
      // 201d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2020: aload 0
      // 2021: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2024: ldc_w "Second Color"
      // 2027: sipush 482
      // 202a: sipush 428
      // 202d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2030: aload 0
      // 2031: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2034: ldc_w "Hue:"
      // 2037: sipush 415
      // 203a: sipush 450
      // 203d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2040: aload 0
      // 2041: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2044: ldc_w "Brightness:"
      // 2047: sipush 375
      // 204a: sipush 470
      // 204d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2050: aload 0
      // 2051: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2054: ldc_w "Saturation:"
      // 2057: sipush 378
      // 205a: sipush 490
      // 205d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2060: aload 0
      // 2061: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2064: ldc_w "RGB Value:"
      // 2067: sipush 378
      // 206a: sipush 520
      // 206d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2070: aload 0
      // 2071: aload 0
      // 2072: getfield CarMaker.rplc Ljava/awt/TextField;
      // 2075: sipush 446
      // 2078: sipush 504
      // 207b: sipush 129
      // 207e: bipush 22
      // 2080: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 2083: aload 0
      // 2084: getfield CarMaker.rplc Ljava/awt/TextField;
      // 2087: invokevirtual java/awt/TextField.hasFocus ()Z
      // 208a: ifeq 2092
      // 208d: aload 0
      // 208e: bipush 0
      // 208f: putfield CarMaker.focuson Z
      // 2092: aload 0
      // 2093: getfield CarMaker.rplc Ljava/awt/TextField;
      // 2096: invokevirtual java/awt/TextField.isShowing ()Z
      // 2099: ifne 20a3
      // 209c: aload 0
      // 209d: getfield CarMaker.rplc Ljava/awt/TextField;
      // 20a0: invokevirtual java/awt/TextField.show ()V
      // 20a3: bipush 0
      // 20a4: istore 6
      // 20a6: iload 6
      // 20a8: sipush 200
      // 20ab: if_icmpge 20e2
      // 20ae: aload 0
      // 20af: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 20b2: iload 6
      // 20b4: i2f
      // 20b5: f2d
      // 20b6: ldc2_w 0.005
      // 20b9: dmul
      // 20ba: d2f
      // 20bb: fconst_1
      // 20bc: fconst_1
      // 20bd: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 20c0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 20c3: aload 0
      // 20c4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 20c7: sipush 450
      // 20ca: iload 6
      // 20cc: iadd
      // 20cd: sipush 442
      // 20d0: sipush 450
      // 20d3: iload 6
      // 20d5: iadd
      // 20d6: sipush 449
      // 20d9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 20dc: iinc 6 1
      // 20df: goto 20a6
      // 20e2: bipush 0
      // 20e3: istore 6
      // 20e5: iload 6
      // 20e7: sipush 200
      // 20ea: if_icmpge 2125
      // 20ed: aload 0
      // 20ee: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 20f1: fconst_0
      // 20f2: fconst_0
      // 20f3: ldc_w 0.2
      // 20f6: iload 6
      // 20f8: i2f
      // 20f9: f2d
      // 20fa: ldc2_w 0.004
      // 20fd: dmul
      // 20fe: d2f
      // 20ff: fadd
      // 2100: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 2103: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2106: aload 0
      // 2107: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 210a: sipush 450
      // 210d: iload 6
      // 210f: iadd
      // 2110: sipush 462
      // 2113: sipush 450
      // 2116: iload 6
      // 2118: iadd
      // 2119: sipush 469
      // 211c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 211f: iinc 6 1
      // 2122: goto 20e5
      // 2125: bipush 0
      // 2126: istore 6
      // 2128: iload 6
      // 212a: sipush 200
      // 212d: if_icmpge 216e
      // 2130: aload 0
      // 2131: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2134: aload 0
      // 2135: getfield CarMaker.shsb [F
      // 2138: bipush 0
      // 2139: faload
      // 213a: iload 6
      // 213c: i2f
      // 213d: f2d
      // 213e: ldc2_w 0.005
      // 2141: dmul
      // 2142: d2f
      // 2143: aload 0
      // 2144: getfield CarMaker.shsb [F
      // 2147: bipush 2
      // 2148: faload
      // 2149: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 214c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 214f: aload 0
      // 2150: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2153: sipush 450
      // 2156: iload 6
      // 2158: iadd
      // 2159: sipush 482
      // 215c: sipush 450
      // 215f: iload 6
      // 2161: iadd
      // 2162: sipush 489
      // 2165: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 2168: iinc 6 1
      // 216b: goto 2128
      // 216e: bipush 0
      // 216f: istore 6
      // 2171: iload 6
      // 2173: bipush 3
      // 2174: if_icmpge 230d
      // 2177: aload 0
      // 2178: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 217b: new java/awt/Color
      // 217e: dup
      // 217f: bipush 0
      // 2180: bipush 0
      // 2181: bipush 0
      // 2182: invokespecial java/awt/Color.<init> (III)V
      // 2185: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2188: aload 0
      // 2189: getfield CarMaker.shsb [F
      // 218c: iload 6
      // 218e: faload
      // 218f: ldc_w 200.0
      // 2192: fmul
      // 2193: fstore 7
      // 2195: iload 6
      // 2197: bipush 1
      // 2198: if_icmpne 21ac
      // 219b: aload 0
      // 219c: getfield CarMaker.shsb [F
      // 219f: iload 6
      // 21a1: faload
      // 21a2: ldc_w 0.2
      // 21a5: fsub
      // 21a6: ldc_w 250.0
      // 21a9: fmul
      // 21aa: fstore 7
      // 21ac: aload 0
      // 21ad: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 21b0: ldc_w 450.0
      // 21b3: fload 7
      // 21b5: fadd
      // 21b6: f2i
      // 21b7: sipush 442
      // 21ba: iload 6
      // 21bc: bipush 20
      // 21be: imul
      // 21bf: iadd
      // 21c0: ldc_w 450.0
      // 21c3: fload 7
      // 21c5: fadd
      // 21c6: f2i
      // 21c7: sipush 449
      // 21ca: iload 6
      // 21cc: bipush 20
      // 21ce: imul
      // 21cf: iadd
      // 21d0: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 21d3: aload 0
      // 21d4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 21d7: ldc_w 451.0
      // 21da: fload 7
      // 21dc: fadd
      // 21dd: f2i
      // 21de: sipush 442
      // 21e1: iload 6
      // 21e3: bipush 20
      // 21e5: imul
      // 21e6: iadd
      // 21e7: ldc_w 451.0
      // 21ea: fload 7
      // 21ec: fadd
      // 21ed: f2i
      // 21ee: sipush 449
      // 21f1: iload 6
      // 21f3: bipush 20
      // 21f5: imul
      // 21f6: iadd
      // 21f7: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 21fa: aload 0
      // 21fb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 21fe: ldc_w 449.0
      // 2201: fload 7
      // 2203: fadd
      // 2204: f2i
      // 2205: sipush 450
      // 2208: iload 6
      // 220a: bipush 20
      // 220c: imul
      // 220d: iadd
      // 220e: bipush 4
      // 220f: bipush 2
      // 2210: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2213: aload 0
      // 2214: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2217: ldc_w 448.0
      // 221a: fload 7
      // 221c: fadd
      // 221d: f2i
      // 221e: sipush 452
      // 2221: iload 6
      // 2223: bipush 20
      // 2225: imul
      // 2226: iadd
      // 2227: ldc_w 453.0
      // 222a: fload 7
      // 222c: fadd
      // 222d: f2i
      // 222e: sipush 452
      // 2231: iload 6
      // 2233: bipush 20
      // 2235: imul
      // 2236: iadd
      // 2237: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 223a: aload 0
      // 223b: getfield CarMaker.xm I
      // 223e: sipush 447
      // 2241: if_icmple 2286
      // 2244: aload 0
      // 2245: getfield CarMaker.xm I
      // 2248: sipush 653
      // 224b: if_icmpge 2286
      // 224e: aload 0
      // 224f: getfield CarMaker.ym I
      // 2252: sipush 439
      // 2255: iload 6
      // 2257: bipush 20
      // 2259: imul
      // 225a: iadd
      // 225b: if_icmple 2286
      // 225e: aload 0
      // 225f: getfield CarMaker.ym I
      // 2262: sipush 452
      // 2265: iload 6
      // 2267: bipush 20
      // 2269: imul
      // 226a: iadd
      // 226b: if_icmpge 2286
      // 226e: aload 0
      // 226f: getfield CarMaker.mouses I
      // 2272: bipush 1
      // 2273: if_icmpne 2286
      // 2276: aload 0
      // 2277: getfield CarMaker.mouseon I
      // 227a: bipush -1
      // 227b: if_icmpne 2286
      // 227e: aload 0
      // 227f: iload 6
      // 2281: bipush 3
      // 2282: iadd
      // 2283: putfield CarMaker.mouseon I
      // 2286: aload 0
      // 2287: getfield CarMaker.mouseon I
      // 228a: iload 6
      // 228c: bipush 3
      // 228d: iadd
      // 228e: if_icmpne 2307
      // 2291: iload 6
      // 2293: bipush 1
      // 2294: if_icmpne 22cb
      // 2297: aload 0
      // 2298: getfield CarMaker.shsb [F
      // 229b: iload 6
      // 229d: ldc_w 0.2
      // 22a0: aload 0
      // 22a1: getfield CarMaker.xm I
      // 22a4: sipush 450
      // 22a7: isub
      // 22a8: i2f
      // 22a9: ldc_w 250.0
      // 22ac: fdiv
      // 22ad: fadd
      // 22ae: fastore
      // 22af: aload 0
      // 22b0: getfield CarMaker.shsb [F
      // 22b3: iload 6
      // 22b5: faload
      // 22b6: f2d
      // 22b7: ldc2_w 0.2
      // 22ba: dcmpg
      // 22bb: ifge 22df
      // 22be: aload 0
      // 22bf: getfield CarMaker.shsb [F
      // 22c2: iload 6
      // 22c4: ldc_w 0.2
      // 22c7: fastore
      // 22c8: goto 22df
      // 22cb: aload 0
      // 22cc: getfield CarMaker.shsb [F
      // 22cf: iload 6
      // 22d1: aload 0
      // 22d2: getfield CarMaker.xm I
      // 22d5: sipush 450
      // 22d8: isub
      // 22d9: i2f
      // 22da: ldc_w 200.0
      // 22dd: fdiv
      // 22de: fastore
      // 22df: aload 0
      // 22e0: getfield CarMaker.shsb [F
      // 22e3: iload 6
      // 22e5: faload
      // 22e6: fconst_1
      // 22e7: fcmpl
      // 22e8: ifle 22f3
      // 22eb: aload 0
      // 22ec: getfield CarMaker.shsb [F
      // 22ef: iload 6
      // 22f1: fconst_1
      // 22f2: fastore
      // 22f3: aload 0
      // 22f4: getfield CarMaker.shsb [F
      // 22f7: iload 6
      // 22f9: faload
      // 22fa: fconst_0
      // 22fb: fcmpg
      // 22fc: ifge 2307
      // 22ff: aload 0
      // 2300: getfield CarMaker.shsb [F
      // 2303: iload 6
      // 2305: fconst_0
      // 2306: fastore
      // 2307: iinc 6 1
      // 230a: goto 2171
      // 230d: aload 0
      // 230e: ldc_w " Save "
      // 2311: sipush 640
      // 2314: sipush 520
      // 2317: bipush 0
      // 2318: aload 0
      // 2319: getfield CarMaker.scol Ljava/lang/String;
      // 231c: aload 0
      // 231d: getfield CarMaker.oscol Ljava/lang/String;
      // 2320: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2323: ifne 232a
      // 2326: bipush 1
      // 2327: goto 232b
      // 232a: bipush 0
      // 232b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 232e: aload 0
      // 232f: getfield CarMaker.fhsb [F
      // 2332: bipush 1
      // 2333: faload
      // 2334: f2d
      // 2335: ldc2_w 0.2
      // 2338: dcmpg
      // 2339: ifge 2345
      // 233c: aload 0
      // 233d: getfield CarMaker.fhsb [F
      // 2340: bipush 1
      // 2341: ldc_w 0.2
      // 2344: fastore
      // 2345: aload 0
      // 2346: getfield CarMaker.shsb [F
      // 2349: bipush 1
      // 234a: faload
      // 234b: f2d
      // 234c: ldc2_w 0.2
      // 234f: dcmpg
      // 2350: ifge 235c
      // 2353: aload 0
      // 2354: getfield CarMaker.shsb [F
      // 2357: bipush 1
      // 2358: ldc_w 0.2
      // 235b: fastore
      // 235c: bipush 0
      // 235d: istore 6
      // 235f: iload 6
      // 2361: aload 0
      // 2362: getfield CarMaker.o LContO;
      // 2365: getfield ContO.npl I
      // 2368: if_icmpge 2411
      // 236b: aload 0
      // 236c: getfield CarMaker.o LContO;
      // 236f: getfield ContO.p [LPlane;
      // 2372: iload 6
      // 2374: aaload
      // 2375: getfield Plane.colnum I
      // 2378: bipush 1
      // 2379: if_icmpne 23bb
      // 237c: aload 0
      // 237d: getfield CarMaker.o LContO;
      // 2380: getfield ContO.p [LPlane;
      // 2383: iload 6
      // 2385: aaload
      // 2386: getfield Plane.hsb [F
      // 2389: bipush 0
      // 238a: aload 0
      // 238b: getfield CarMaker.fhsb [F
      // 238e: bipush 0
      // 238f: faload
      // 2390: fastore
      // 2391: aload 0
      // 2392: getfield CarMaker.o LContO;
      // 2395: getfield ContO.p [LPlane;
      // 2398: iload 6
      // 239a: aaload
      // 239b: getfield Plane.hsb [F
      // 239e: bipush 1
      // 239f: aload 0
      // 23a0: getfield CarMaker.fhsb [F
      // 23a3: bipush 2
      // 23a4: faload
      // 23a5: fastore
      // 23a6: aload 0
      // 23a7: getfield CarMaker.o LContO;
      // 23aa: getfield ContO.p [LPlane;
      // 23ad: iload 6
      // 23af: aaload
      // 23b0: getfield Plane.hsb [F
      // 23b3: bipush 2
      // 23b4: aload 0
      // 23b5: getfield CarMaker.fhsb [F
      // 23b8: bipush 1
      // 23b9: faload
      // 23ba: fastore
      // 23bb: aload 0
      // 23bc: getfield CarMaker.o LContO;
      // 23bf: getfield ContO.p [LPlane;
      // 23c2: iload 6
      // 23c4: aaload
      // 23c5: getfield Plane.colnum I
      // 23c8: bipush 2
      // 23c9: if_icmpne 240b
      // 23cc: aload 0
      // 23cd: getfield CarMaker.o LContO;
      // 23d0: getfield ContO.p [LPlane;
      // 23d3: iload 6
      // 23d5: aaload
      // 23d6: getfield Plane.hsb [F
      // 23d9: bipush 0
      // 23da: aload 0
      // 23db: getfield CarMaker.shsb [F
      // 23de: bipush 0
      // 23df: faload
      // 23e0: fastore
      // 23e1: aload 0
      // 23e2: getfield CarMaker.o LContO;
      // 23e5: getfield ContO.p [LPlane;
      // 23e8: iload 6
      // 23ea: aaload
      // 23eb: getfield Plane.hsb [F
      // 23ee: bipush 1
      // 23ef: aload 0
      // 23f0: getfield CarMaker.shsb [F
      // 23f3: bipush 2
      // 23f4: faload
      // 23f5: fastore
      // 23f6: aload 0
      // 23f7: getfield CarMaker.o LContO;
      // 23fa: getfield ContO.p [LPlane;
      // 23fd: iload 6
      // 23ff: aaload
      // 2400: getfield Plane.hsb [F
      // 2403: bipush 2
      // 2404: aload 0
      // 2405: getfield CarMaker.shsb [F
      // 2408: bipush 1
      // 2409: faload
      // 240a: fastore
      // 240b: iinc 6 1
      // 240e: goto 235f
      // 2411: new java/lang/StringBuilder
      // 2414: dup
      // 2415: invokespecial java/lang/StringBuilder.<init> ()V
      // 2418: ldc_w "("
      // 241b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 241e: aload 0
      // 241f: getfield CarMaker.fhsb [F
      // 2422: bipush 0
      // 2423: faload
      // 2424: aload 0
      // 2425: getfield CarMaker.fhsb [F
      // 2428: bipush 2
      // 2429: faload
      // 242a: aload 0
      // 242b: getfield CarMaker.fhsb [F
      // 242e: bipush 1
      // 242f: faload
      // 2430: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 2433: invokevirtual java/awt/Color.getRed ()I
      // 2436: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2439: ldc_w ","
      // 243c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 243f: aload 0
      // 2440: getfield CarMaker.fhsb [F
      // 2443: bipush 0
      // 2444: faload
      // 2445: aload 0
      // 2446: getfield CarMaker.fhsb [F
      // 2449: bipush 2
      // 244a: faload
      // 244b: aload 0
      // 244c: getfield CarMaker.fhsb [F
      // 244f: bipush 1
      // 2450: faload
      // 2451: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 2454: invokevirtual java/awt/Color.getGreen ()I
      // 2457: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 245a: ldc_w ","
      // 245d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2460: aload 0
      // 2461: getfield CarMaker.fhsb [F
      // 2464: bipush 0
      // 2465: faload
      // 2466: aload 0
      // 2467: getfield CarMaker.fhsb [F
      // 246a: bipush 2
      // 246b: faload
      // 246c: aload 0
      // 246d: getfield CarMaker.fhsb [F
      // 2470: bipush 1
      // 2471: faload
      // 2472: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 2475: invokevirtual java/awt/Color.getBlue ()I
      // 2478: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 247b: ldc_w ")"
      // 247e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2481: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2484: astore 6
      // 2486: aload 0
      // 2487: getfield CarMaker.fcol Ljava/lang/String;
      // 248a: aload 6
      // 248c: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 248f: ifne 24a3
      // 2492: aload 0
      // 2493: aload 6
      // 2495: putfield CarMaker.fcol Ljava/lang/String;
      // 2498: aload 0
      // 2499: getfield CarMaker.srch Ljava/awt/TextField;
      // 249c: aload 0
      // 249d: getfield CarMaker.fcol Ljava/lang/String;
      // 24a0: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 24a3: new java/lang/StringBuilder
      // 24a6: dup
      // 24a7: invokespecial java/lang/StringBuilder.<init> ()V
      // 24aa: ldc_w "("
      // 24ad: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 24b0: aload 0
      // 24b1: getfield CarMaker.shsb [F
      // 24b4: bipush 0
      // 24b5: faload
      // 24b6: aload 0
      // 24b7: getfield CarMaker.shsb [F
      // 24ba: bipush 2
      // 24bb: faload
      // 24bc: aload 0
      // 24bd: getfield CarMaker.shsb [F
      // 24c0: bipush 1
      // 24c1: faload
      // 24c2: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 24c5: invokevirtual java/awt/Color.getRed ()I
      // 24c8: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 24cb: ldc_w ","
      // 24ce: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 24d1: aload 0
      // 24d2: getfield CarMaker.shsb [F
      // 24d5: bipush 0
      // 24d6: faload
      // 24d7: aload 0
      // 24d8: getfield CarMaker.shsb [F
      // 24db: bipush 2
      // 24dc: faload
      // 24dd: aload 0
      // 24de: getfield CarMaker.shsb [F
      // 24e1: bipush 1
      // 24e2: faload
      // 24e3: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 24e6: invokevirtual java/awt/Color.getGreen ()I
      // 24e9: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 24ec: ldc_w ","
      // 24ef: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 24f2: aload 0
      // 24f3: getfield CarMaker.shsb [F
      // 24f6: bipush 0
      // 24f7: faload
      // 24f8: aload 0
      // 24f9: getfield CarMaker.shsb [F
      // 24fc: bipush 2
      // 24fd: faload
      // 24fe: aload 0
      // 24ff: getfield CarMaker.shsb [F
      // 2502: bipush 1
      // 2503: faload
      // 2504: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 2507: invokevirtual java/awt/Color.getBlue ()I
      // 250a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 250d: ldc_w ")"
      // 2510: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2513: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2516: astore 6
      // 2518: aload 0
      // 2519: getfield CarMaker.scol Ljava/lang/String;
      // 251c: aload 6
      // 251e: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2521: ifne 2535
      // 2524: aload 0
      // 2525: aload 6
      // 2527: putfield CarMaker.scol Ljava/lang/String;
      // 252a: aload 0
      // 252b: getfield CarMaker.rplc Ljava/awt/TextField;
      // 252e: aload 0
      // 252f: getfield CarMaker.scol Ljava/lang/String;
      // 2532: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 2535: aload 0
      // 2536: getfield CarMaker.srch Ljava/awt/TextField;
      // 2539: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 253c: aload 0
      // 253d: getfield CarMaker.fcol Ljava/lang/String;
      // 2540: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2543: ifeq 2583
      // 2546: aload 0
      // 2547: getfield CarMaker.rplc Ljava/awt/TextField;
      // 254a: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 254d: aload 0
      // 254e: getfield CarMaker.scol Ljava/lang/String;
      // 2551: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2554: ifeq 2583
      // 2557: aload 0
      // 2558: getfield CarMaker.bfo I
      // 255b: bipush 50
      // 255d: if_icmpge 256d
      // 2560: aload 0
      // 2561: dup
      // 2562: getfield CarMaker.bfo I
      // 2565: bipush 1
      // 2566: iadd
      // 2567: putfield CarMaker.bfo I
      // 256a: goto 27c4
      // 256d: aload 0
      // 256e: getfield CarMaker.bfo I
      // 2571: bipush 50
      // 2573: if_icmpne 27c4
      // 2576: aload 0
      // 2577: invokevirtual CarMaker.requestFocus ()V
      // 257a: aload 0
      // 257b: bipush 51
      // 257d: putfield CarMaker.bfo I
      // 2580: goto 27c4
      // 2583: aload 0
      // 2584: bipush 0
      // 2585: putfield CarMaker.bfo I
      // 2588: aload 0
      // 2589: getfield CarMaker.srch Ljava/awt/TextField;
      // 258c: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 258f: aload 0
      // 2590: getfield CarMaker.fcol Ljava/lang/String;
      // 2593: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2596: ifne 26a6
      // 2599: aload 0
      // 259a: aload 0
      // 259b: getfield CarMaker.srch Ljava/awt/TextField;
      // 259e: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 25a1: putfield CarMaker.fcol Ljava/lang/String;
      // 25a4: bipush 3
      // 25a5: newarray 10
      // 25a7: astore 7
      // 25a9: bipush 1
      // 25aa: istore 8
      // 25ac: aload 0
      // 25ad: getfield CarMaker.fcol Ljava/lang/String;
      // 25b0: ldc_w ","
      // 25b3: bipush 0
      // 25b4: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 25b7: istore 9
      // 25b9: aload 0
      // 25ba: getfield CarMaker.fcol Ljava/lang/String;
      // 25bd: ldc_w ","
      // 25c0: iload 9
      // 25c2: bipush 1
      // 25c3: iadd
      // 25c4: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 25c7: istore 10
      // 25c9: aload 7
      // 25cb: bipush 0
      // 25cc: aload 0
      // 25cd: getfield CarMaker.fcol Ljava/lang/String;
      // 25d0: bipush 1
      // 25d1: iload 9
      // 25d3: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 25d6: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 25d9: invokevirtual java/lang/Integer.intValue ()I
      // 25dc: iastore
      // 25dd: aload 7
      // 25df: bipush 0
      // 25e0: iaload
      // 25e1: ifge 25e9
      // 25e4: aload 7
      // 25e6: bipush 0
      // 25e7: bipush 0
      // 25e8: iastore
      // 25e9: aload 7
      // 25eb: bipush 0
      // 25ec: iaload
      // 25ed: sipush 255
      // 25f0: if_icmple 25fa
      // 25f3: aload 7
      // 25f5: bipush 0
      // 25f6: sipush 255
      // 25f9: iastore
      // 25fa: aload 7
      // 25fc: bipush 1
      // 25fd: aload 0
      // 25fe: getfield CarMaker.fcol Ljava/lang/String;
      // 2601: iload 9
      // 2603: bipush 1
      // 2604: iadd
      // 2605: iload 10
      // 2607: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 260a: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 260d: invokevirtual java/lang/Integer.intValue ()I
      // 2610: iastore
      // 2611: aload 7
      // 2613: bipush 1
      // 2614: iaload
      // 2615: ifge 261d
      // 2618: aload 7
      // 261a: bipush 1
      // 261b: bipush 0
      // 261c: iastore
      // 261d: aload 7
      // 261f: bipush 1
      // 2620: iaload
      // 2621: sipush 255
      // 2624: if_icmple 262e
      // 2627: aload 7
      // 2629: bipush 1
      // 262a: sipush 255
      // 262d: iastore
      // 262e: aload 7
      // 2630: bipush 2
      // 2631: aload 0
      // 2632: getfield CarMaker.fcol Ljava/lang/String;
      // 2635: iload 10
      // 2637: bipush 1
      // 2638: iadd
      // 2639: aload 0
      // 263a: getfield CarMaker.fcol Ljava/lang/String;
      // 263d: invokevirtual java/lang/String.length ()I
      // 2640: bipush 1
      // 2641: isub
      // 2642: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 2645: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 2648: invokevirtual java/lang/Integer.intValue ()I
      // 264b: iastore
      // 264c: aload 7
      // 264e: bipush 2
      // 264f: iaload
      // 2650: ifge 2658
      // 2653: aload 7
      // 2655: bipush 2
      // 2656: bipush 0
      // 2657: iastore
      // 2658: aload 7
      // 265a: bipush 2
      // 265b: iaload
      // 265c: sipush 255
      // 265f: if_icmple 2669
      // 2662: aload 7
      // 2664: bipush 2
      // 2665: sipush 255
      // 2668: iastore
      // 2669: goto 2671
      // 266c: astore 9
      // 266e: bipush 0
      // 266f: istore 8
      // 2671: iload 8
      // 2673: ifeq 26a6
      // 2676: aload 7
      // 2678: bipush 0
      // 2679: iaload
      // 267a: aload 7
      // 267c: bipush 1
      // 267d: iaload
      // 267e: aload 7
      // 2680: bipush 2
      // 2681: iaload
      // 2682: aload 0
      // 2683: getfield CarMaker.fhsb [F
      // 2686: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 2689: pop
      // 268a: aload 0
      // 268b: getfield CarMaker.fhsb [F
      // 268e: bipush 1
      // 268f: faload
      // 2690: fstore 9
      // 2692: aload 0
      // 2693: getfield CarMaker.fhsb [F
      // 2696: bipush 1
      // 2697: aload 0
      // 2698: getfield CarMaker.fhsb [F
      // 269b: bipush 2
      // 269c: faload
      // 269d: fastore
      // 269e: aload 0
      // 269f: getfield CarMaker.fhsb [F
      // 26a2: bipush 2
      // 26a3: fload 9
      // 26a5: fastore
      // 26a6: aload 0
      // 26a7: getfield CarMaker.rplc Ljava/awt/TextField;
      // 26aa: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 26ad: aload 0
      // 26ae: getfield CarMaker.scol Ljava/lang/String;
      // 26b1: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 26b4: ifne 27c4
      // 26b7: aload 0
      // 26b8: aload 0
      // 26b9: getfield CarMaker.rplc Ljava/awt/TextField;
      // 26bc: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 26bf: putfield CarMaker.scol Ljava/lang/String;
      // 26c2: bipush 3
      // 26c3: newarray 10
      // 26c5: astore 7
      // 26c7: bipush 1
      // 26c8: istore 8
      // 26ca: aload 0
      // 26cb: getfield CarMaker.scol Ljava/lang/String;
      // 26ce: ldc_w ","
      // 26d1: bipush 0
      // 26d2: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 26d5: istore 9
      // 26d7: aload 0
      // 26d8: getfield CarMaker.scol Ljava/lang/String;
      // 26db: ldc_w ","
      // 26de: iload 9
      // 26e0: bipush 1
      // 26e1: iadd
      // 26e2: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 26e5: istore 10
      // 26e7: aload 7
      // 26e9: bipush 0
      // 26ea: aload 0
      // 26eb: getfield CarMaker.scol Ljava/lang/String;
      // 26ee: bipush 1
      // 26ef: iload 9
      // 26f1: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 26f4: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 26f7: invokevirtual java/lang/Integer.intValue ()I
      // 26fa: iastore
      // 26fb: aload 7
      // 26fd: bipush 0
      // 26fe: iaload
      // 26ff: ifge 2707
      // 2702: aload 7
      // 2704: bipush 0
      // 2705: bipush 0
      // 2706: iastore
      // 2707: aload 7
      // 2709: bipush 0
      // 270a: iaload
      // 270b: sipush 255
      // 270e: if_icmple 2718
      // 2711: aload 7
      // 2713: bipush 0
      // 2714: sipush 255
      // 2717: iastore
      // 2718: aload 7
      // 271a: bipush 1
      // 271b: aload 0
      // 271c: getfield CarMaker.scol Ljava/lang/String;
      // 271f: iload 9
      // 2721: bipush 1
      // 2722: iadd
      // 2723: iload 10
      // 2725: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 2728: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 272b: invokevirtual java/lang/Integer.intValue ()I
      // 272e: iastore
      // 272f: aload 7
      // 2731: bipush 1
      // 2732: iaload
      // 2733: ifge 273b
      // 2736: aload 7
      // 2738: bipush 1
      // 2739: bipush 0
      // 273a: iastore
      // 273b: aload 7
      // 273d: bipush 1
      // 273e: iaload
      // 273f: sipush 255
      // 2742: if_icmple 274c
      // 2745: aload 7
      // 2747: bipush 1
      // 2748: sipush 255
      // 274b: iastore
      // 274c: aload 7
      // 274e: bipush 2
      // 274f: aload 0
      // 2750: getfield CarMaker.scol Ljava/lang/String;
      // 2753: iload 10
      // 2755: bipush 1
      // 2756: iadd
      // 2757: aload 0
      // 2758: getfield CarMaker.scol Ljava/lang/String;
      // 275b: invokevirtual java/lang/String.length ()I
      // 275e: bipush 1
      // 275f: isub
      // 2760: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 2763: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 2766: invokevirtual java/lang/Integer.intValue ()I
      // 2769: iastore
      // 276a: aload 7
      // 276c: bipush 2
      // 276d: iaload
      // 276e: ifge 2776
      // 2771: aload 7
      // 2773: bipush 2
      // 2774: bipush 0
      // 2775: iastore
      // 2776: aload 7
      // 2778: bipush 2
      // 2779: iaload
      // 277a: sipush 255
      // 277d: if_icmple 2787
      // 2780: aload 7
      // 2782: bipush 2
      // 2783: sipush 255
      // 2786: iastore
      // 2787: goto 278f
      // 278a: astore 9
      // 278c: bipush 0
      // 278d: istore 8
      // 278f: iload 8
      // 2791: ifeq 27c4
      // 2794: aload 7
      // 2796: bipush 0
      // 2797: iaload
      // 2798: aload 7
      // 279a: bipush 1
      // 279b: iaload
      // 279c: aload 7
      // 279e: bipush 2
      // 279f: iaload
      // 27a0: aload 0
      // 27a1: getfield CarMaker.shsb [F
      // 27a4: invokestatic java/awt/Color.RGBtoHSB (III[F)[F
      // 27a7: pop
      // 27a8: aload 0
      // 27a9: getfield CarMaker.shsb [F
      // 27ac: bipush 1
      // 27ad: faload
      // 27ae: fstore 9
      // 27b0: aload 0
      // 27b1: getfield CarMaker.shsb [F
      // 27b4: bipush 1
      // 27b5: aload 0
      // 27b6: getfield CarMaker.shsb [F
      // 27b9: bipush 2
      // 27ba: faload
      // 27bb: fastore
      // 27bc: aload 0
      // 27bd: getfield CarMaker.shsb [F
      // 27c0: bipush 2
      // 27c1: fload 9
      // 27c3: fastore
      // 27c4: aload 0
      // 27c5: getfield CarMaker.dtab I
      // 27c8: bipush 2
      // 27c9: if_icmpne 2d89
      // 27cc: aload 0
      // 27cd: getfield CarMaker.dtabed I
      // 27d0: aload 0
      // 27d1: getfield CarMaker.dtab I
      // 27d4: if_icmpeq 2928
      // 27d7: aload 0
      // 27d8: aload 0
      // 27d9: getfield CarMaker.editor Ljava/awt/TextArea;
      // 27dc: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 27df: putfield CarMaker.lastedo Ljava/lang/String;
      // 27e2: aload 0
      // 27e3: getfield CarMaker.scale [I
      // 27e6: bipush 0
      // 27e7: bipush 100
      // 27e9: iastore
      // 27ea: aload 0
      // 27eb: getfield CarMaker.editor Ljava/awt/TextArea;
      // 27ee: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 27f1: ldc_w "\nScaleX("
      // 27f4: bipush 0
      // 27f5: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 27f8: istore 6
      // 27fa: iload 6
      // 27fc: bipush -1
      // 27fd: if_icmpeq 283a
      // 2800: iinc 6 1
      // 2803: aload 0
      // 2804: getfield CarMaker.scale [I
      // 2807: bipush 0
      // 2808: aload 0
      // 2809: getfield CarMaker.editor Ljava/awt/TextArea;
      // 280c: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 280f: iload 6
      // 2811: bipush 7
      // 2813: iadd
      // 2814: aload 0
      // 2815: getfield CarMaker.editor Ljava/awt/TextArea;
      // 2818: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 281b: ldc_w ")"
      // 281e: iload 6
      // 2820: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 2823: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 2826: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 2829: invokevirtual java/lang/Integer.intValue ()I
      // 282c: iastore
      // 282d: goto 283a
      // 2830: astore 7
      // 2832: aload 0
      // 2833: getfield CarMaker.scale [I
      // 2836: bipush 0
      // 2837: bipush 100
      // 2839: iastore
      // 283a: aload 0
      // 283b: getfield CarMaker.oscale [I
      // 283e: bipush 0
      // 283f: aload 0
      // 2840: getfield CarMaker.scale [I
      // 2843: bipush 0
      // 2844: iaload
      // 2845: iastore
      // 2846: aload 0
      // 2847: getfield CarMaker.scale [I
      // 284a: bipush 1
      // 284b: bipush 100
      // 284d: iastore
      // 284e: aload 0
      // 284f: getfield CarMaker.editor Ljava/awt/TextArea;
      // 2852: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 2855: ldc_w "\nScaleY("
      // 2858: bipush 0
      // 2859: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 285c: istore 6
      // 285e: iload 6
      // 2860: bipush -1
      // 2861: if_icmpeq 289e
      // 2864: iinc 6 1
      // 2867: aload 0
      // 2868: getfield CarMaker.scale [I
      // 286b: bipush 1
      // 286c: aload 0
      // 286d: getfield CarMaker.editor Ljava/awt/TextArea;
      // 2870: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 2873: iload 6
      // 2875: bipush 7
      // 2877: iadd
      // 2878: aload 0
      // 2879: getfield CarMaker.editor Ljava/awt/TextArea;
      // 287c: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 287f: ldc_w ")"
      // 2882: iload 6
      // 2884: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 2887: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 288a: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 288d: invokevirtual java/lang/Integer.intValue ()I
      // 2890: iastore
      // 2891: goto 289e
      // 2894: astore 7
      // 2896: aload 0
      // 2897: getfield CarMaker.scale [I
      // 289a: bipush 1
      // 289b: bipush 100
      // 289d: iastore
      // 289e: aload 0
      // 289f: getfield CarMaker.oscale [I
      // 28a2: bipush 1
      // 28a3: aload 0
      // 28a4: getfield CarMaker.scale [I
      // 28a7: bipush 1
      // 28a8: iaload
      // 28a9: iastore
      // 28aa: aload 0
      // 28ab: getfield CarMaker.scale [I
      // 28ae: bipush 2
      // 28af: bipush 100
      // 28b1: iastore
      // 28b2: aload 0
      // 28b3: getfield CarMaker.editor Ljava/awt/TextArea;
      // 28b6: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 28b9: ldc_w "\nScaleZ("
      // 28bc: bipush 0
      // 28bd: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 28c0: istore 6
      // 28c2: iload 6
      // 28c4: bipush -1
      // 28c5: if_icmpeq 2902
      // 28c8: iinc 6 1
      // 28cb: aload 0
      // 28cc: getfield CarMaker.scale [I
      // 28cf: bipush 2
      // 28d0: aload 0
      // 28d1: getfield CarMaker.editor Ljava/awt/TextArea;
      // 28d4: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 28d7: iload 6
      // 28d9: bipush 7
      // 28db: iadd
      // 28dc: aload 0
      // 28dd: getfield CarMaker.editor Ljava/awt/TextArea;
      // 28e0: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 28e3: ldc_w ")"
      // 28e6: iload 6
      // 28e8: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 28eb: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 28ee: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 28f1: invokevirtual java/lang/Integer.intValue ()I
      // 28f4: iastore
      // 28f5: goto 2902
      // 28f8: astore 7
      // 28fa: aload 0
      // 28fb: getfield CarMaker.scale [I
      // 28fe: bipush 2
      // 28ff: bipush 100
      // 2901: iastore
      // 2902: aload 0
      // 2903: getfield CarMaker.oscale [I
      // 2906: bipush 2
      // 2907: aload 0
      // 2908: getfield CarMaker.scale [I
      // 290b: bipush 2
      // 290c: iaload
      // 290d: iastore
      // 290e: aload 0
      // 290f: bipush 0
      // 2910: putfield CarMaker.bfo I
      // 2913: aload 0
      // 2914: bipush 0
      // 2915: putfield CarMaker.compsel I
      // 2918: aload 0
      // 2919: getfield CarMaker.compcar LSmenu;
      // 291c: aload 0
      // 291d: getfield CarMaker.compsel I
      // 2920: invokevirtual Smenu.select (I)V
      // 2923: aload 0
      // 2924: bipush 0
      // 2925: putfield CarMaker.changed2 Z
      // 2928: aload 0
      // 2929: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 292c: new java/awt/Color
      // 292f: dup
      // 2930: sipush 170
      // 2933: sipush 170
      // 2936: sipush 170
      // 2939: invokespecial java/awt/Color.<init> (III)V
      // 293c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 293f: aload 0
      // 2940: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2943: bipush 9
      // 2945: sipush 425
      // 2948: sipush 270
      // 294b: bipush 115
      // 294d: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 2950: aload 0
      // 2951: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2954: new java/awt/Color
      // 2957: dup
      // 2958: sipush 225
      // 295b: sipush 225
      // 295e: sipush 225
      // 2961: invokespecial java/awt/Color.<init> (III)V
      // 2964: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2967: aload 0
      // 2968: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 296b: bipush 119
      // 296d: sipush 419
      // 2970: bipush 51
      // 2972: bipush 9
      // 2974: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2977: aload 0
      // 2978: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 297b: new java/awt/Color
      // 297e: dup
      // 297f: bipush 0
      // 2980: bipush 0
      // 2981: bipush 0
      // 2982: invokespecial java/awt/Color.<init> (III)V
      // 2985: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2988: aload 0
      // 2989: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 298c: ldc_w "Scale"
      // 298f: sipush 129
      // 2992: sipush 428
      // 2995: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2998: aload 0
      // 2999: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 299c: new java/awt/Color
      // 299f: dup
      // 29a0: bipush 0
      // 29a1: bipush 0
      // 29a2: bipush 0
      // 29a3: invokespecial java/awt/Color.<init> (III)V
      // 29a6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 29a9: aload 0
      // 29aa: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 29ad: ldc_w "Scale X"
      // 29b0: bipush 25
      // 29b2: sipush 450
      // 29b5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 29b8: aload 0
      // 29b9: ldc_w " - "
      // 29bc: bipush 92
      // 29be: sipush 450
      // 29c1: bipush 4
      // 29c2: bipush 0
      // 29c3: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 29c6: aload 0
      // 29c7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 29ca: new java/lang/StringBuilder
      // 29cd: dup
      // 29ce: invokespecial java/lang/StringBuilder.<init> ()V
      // 29d1: ldc ""
      // 29d3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 29d6: aload 0
      // 29d7: getfield CarMaker.scale [I
      // 29da: bipush 0
      // 29db: iaload
      // 29dc: i2f
      // 29dd: ldc_w 100.0
      // 29e0: fdiv
      // 29e1: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 29e4: ldc ""
      // 29e6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 29e9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 29ec: bipush 126
      // 29ee: aload 0
      // 29ef: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 29f2: new java/lang/StringBuilder
      // 29f5: dup
      // 29f6: invokespecial java/lang/StringBuilder.<init> ()V
      // 29f9: ldc ""
      // 29fb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 29fe: aload 0
      // 29ff: getfield CarMaker.scale [I
      // 2a02: bipush 0
      // 2a03: iaload
      // 2a04: i2f
      // 2a05: ldc_w 100.0
      // 2a08: fdiv
      // 2a09: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 2a0c: ldc ""
      // 2a0e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a11: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2a14: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2a17: bipush 2
      // 2a18: idiv
      // 2a19: isub
      // 2a1a: sipush 450
      // 2a1d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2a20: aload 0
      // 2a21: ldc_w " + "
      // 2a24: sipush 160
      // 2a27: sipush 450
      // 2a2a: bipush 4
      // 2a2b: bipush 0
      // 2a2c: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2a2f: aload 0
      // 2a30: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2a33: ldc_w "Scale Y"
      // 2a36: bipush 25
      // 2a38: sipush 474
      // 2a3b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2a3e: aload 0
      // 2a3f: ldc_w " - "
      // 2a42: bipush 92
      // 2a44: sipush 474
      // 2a47: bipush 4
      // 2a48: bipush 0
      // 2a49: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2a4c: aload 0
      // 2a4d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2a50: new java/lang/StringBuilder
      // 2a53: dup
      // 2a54: invokespecial java/lang/StringBuilder.<init> ()V
      // 2a57: ldc ""
      // 2a59: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a5c: aload 0
      // 2a5d: getfield CarMaker.scale [I
      // 2a60: bipush 1
      // 2a61: iaload
      // 2a62: i2f
      // 2a63: ldc_w 100.0
      // 2a66: fdiv
      // 2a67: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 2a6a: ldc ""
      // 2a6c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a6f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2a72: bipush 126
      // 2a74: aload 0
      // 2a75: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 2a78: new java/lang/StringBuilder
      // 2a7b: dup
      // 2a7c: invokespecial java/lang/StringBuilder.<init> ()V
      // 2a7f: ldc ""
      // 2a81: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a84: aload 0
      // 2a85: getfield CarMaker.scale [I
      // 2a88: bipush 1
      // 2a89: iaload
      // 2a8a: i2f
      // 2a8b: ldc_w 100.0
      // 2a8e: fdiv
      // 2a8f: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 2a92: ldc ""
      // 2a94: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a97: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2a9a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2a9d: bipush 2
      // 2a9e: idiv
      // 2a9f: isub
      // 2aa0: sipush 474
      // 2aa3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2aa6: aload 0
      // 2aa7: ldc_w " + "
      // 2aaa: sipush 160
      // 2aad: sipush 474
      // 2ab0: bipush 4
      // 2ab1: bipush 0
      // 2ab2: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2ab5: aload 0
      // 2ab6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2ab9: ldc_w "Scale Z"
      // 2abc: bipush 25
      // 2abe: sipush 498
      // 2ac1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2ac4: aload 0
      // 2ac5: ldc_w " - "
      // 2ac8: bipush 92
      // 2aca: sipush 498
      // 2acd: bipush 4
      // 2ace: bipush 0
      // 2acf: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2ad2: aload 0
      // 2ad3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2ad6: new java/lang/StringBuilder
      // 2ad9: dup
      // 2ada: invokespecial java/lang/StringBuilder.<init> ()V
      // 2add: ldc ""
      // 2adf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2ae2: aload 0
      // 2ae3: getfield CarMaker.scale [I
      // 2ae6: bipush 2
      // 2ae7: iaload
      // 2ae8: i2f
      // 2ae9: ldc_w 100.0
      // 2aec: fdiv
      // 2aed: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 2af0: ldc ""
      // 2af2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2af5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2af8: bipush 126
      // 2afa: aload 0
      // 2afb: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 2afe: new java/lang/StringBuilder
      // 2b01: dup
      // 2b02: invokespecial java/lang/StringBuilder.<init> ()V
      // 2b05: ldc ""
      // 2b07: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2b0a: aload 0
      // 2b0b: getfield CarMaker.scale [I
      // 2b0e: bipush 2
      // 2b0f: iaload
      // 2b10: i2f
      // 2b11: ldc_w 100.0
      // 2b14: fdiv
      // 2b15: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 2b18: ldc ""
      // 2b1a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2b1d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2b20: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2b23: bipush 2
      // 2b24: idiv
      // 2b25: isub
      // 2b26: sipush 498
      // 2b29: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2b2c: aload 0
      // 2b2d: ldc_w " + "
      // 2b30: sipush 160
      // 2b33: sipush 498
      // 2b36: bipush 4
      // 2b37: bipush 0
      // 2b38: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2b3b: aload 0
      // 2b3c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2b3f: ldc_w "Scale ALL"
      // 2b42: bipush 25
      // 2b44: sipush 527
      // 2b47: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2b4a: aload 0
      // 2b4b: ldc_w " - "
      // 2b4e: bipush 106
      // 2b50: sipush 527
      // 2b53: bipush 2
      // 2b54: bipush 1
      // 2b55: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2b58: aload 0
      // 2b59: ldc_w " + "
      // 2b5c: sipush 146
      // 2b5f: sipush 527
      // 2b62: bipush 2
      // 2b63: bipush 1
      // 2b64: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2b67: aload 0
      // 2b68: ldc_w "   Save   "
      // 2b6b: sipush 230
      // 2b6e: sipush 454
      // 2b71: bipush 0
      // 2b72: aload 0
      // 2b73: getfield CarMaker.oscale [I
      // 2b76: bipush 0
      // 2b77: iaload
      // 2b78: aload 0
      // 2b79: getfield CarMaker.scale [I
      // 2b7c: bipush 0
      // 2b7d: iaload
      // 2b7e: if_icmpne 2b9f
      // 2b81: aload 0
      // 2b82: getfield CarMaker.oscale [I
      // 2b85: bipush 1
      // 2b86: iaload
      // 2b87: aload 0
      // 2b88: getfield CarMaker.scale [I
      // 2b8b: bipush 1
      // 2b8c: iaload
      // 2b8d: if_icmpne 2b9f
      // 2b90: aload 0
      // 2b91: getfield CarMaker.oscale [I
      // 2b94: bipush 2
      // 2b95: iaload
      // 2b96: aload 0
      // 2b97: getfield CarMaker.scale [I
      // 2b9a: bipush 2
      // 2b9b: iaload
      // 2b9c: if_icmpeq 2ba3
      // 2b9f: bipush 1
      // 2ba0: goto 2ba4
      // 2ba3: bipush 0
      // 2ba4: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2ba7: aload 0
      // 2ba8: ldc_w " Reset "
      // 2bab: sipush 230
      // 2bae: sipush 493
      // 2bb1: bipush 0
      // 2bb2: bipush 0
      // 2bb3: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2bb6: aload 0
      // 2bb7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2bba: ldc_w "Compare scale and"
      // 2bbd: sipush 296
      // 2bc0: sipush 440
      // 2bc3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2bc6: aload 0
      // 2bc7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2bca: ldc_w "alignment with:"
      // 2bcd: sipush 308
      // 2bd0: sipush 455
      // 2bd3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2bd6: aload 0
      // 2bd7: getfield CarMaker.compcar LSmenu;
      // 2bda: sipush 288
      // 2bdd: sipush 462
      // 2be0: invokevirtual Smenu.move (II)V
      // 2be3: aload 0
      // 2be4: getfield CarMaker.compcar LSmenu;
      // 2be7: invokevirtual Smenu.hasFocus ()Z
      // 2bea: ifeq 2c0c
      // 2bed: aload 0
      // 2bee: bipush 0
      // 2bef: putfield CarMaker.focuson Z
      // 2bf2: aload 0
      // 2bf3: dup
      // 2bf4: getfield CarMaker.bfo I
      // 2bf7: bipush 1
      // 2bf8: iadd
      // 2bf9: putfield CarMaker.bfo I
      // 2bfc: aload 0
      // 2bfd: getfield CarMaker.bfo I
      // 2c00: bipush 100
      // 2c02: if_icmpne 2c11
      // 2c05: aload 0
      // 2c06: invokevirtual CarMaker.requestFocus ()V
      // 2c09: goto 2c11
      // 2c0c: aload 0
      // 2c0d: bipush 0
      // 2c0e: putfield CarMaker.bfo I
      // 2c11: aload 0
      // 2c12: getfield CarMaker.compcar LSmenu;
      // 2c15: invokevirtual Smenu.isShowing ()Z
      // 2c18: ifne 2c22
      // 2c1b: aload 0
      // 2c1c: getfield CarMaker.compcar LSmenu;
      // 2c1f: invokevirtual Smenu.show ()V
      // 2c22: aload 0
      // 2c23: getfield CarMaker.compsel I
      // 2c26: aload 0
      // 2c27: getfield CarMaker.compcar LSmenu;
      // 2c2a: invokevirtual Smenu.getSelectedIndex ()I
      // 2c2d: if_icmpeq 2c3f
      // 2c30: aload 0
      // 2c31: aload 0
      // 2c32: getfield CarMaker.compcar LSmenu;
      // 2c35: invokevirtual Smenu.getSelectedIndex ()I
      // 2c38: putfield CarMaker.compsel I
      // 2c3b: aload 0
      // 2c3c: invokevirtual CarMaker.requestFocus ()V
      // 2c3f: aload 0
      // 2c40: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2c43: new java/awt/Color
      // 2c46: dup
      // 2c47: sipush 170
      // 2c4a: sipush 170
      // 2c4d: sipush 170
      // 2c50: invokespecial java/awt/Color.<init> (III)V
      // 2c53: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2c56: aload 0
      // 2c57: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2c5a: sipush 420
      // 2c5d: sipush 425
      // 2c60: sipush 270
      // 2c63: bipush 115
      // 2c65: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 2c68: aload 0
      // 2c69: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2c6c: new java/awt/Color
      // 2c6f: dup
      // 2c70: sipush 225
      // 2c73: sipush 225
      // 2c76: sipush 225
      // 2c79: invokespecial java/awt/Color.<init> (III)V
      // 2c7c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2c7f: aload 0
      // 2c80: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2c83: sipush 531
      // 2c86: sipush 419
      // 2c89: bipush 47
      // 2c8b: bipush 9
      // 2c8d: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2c90: aload 0
      // 2c91: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2c94: new java/awt/Color
      // 2c97: dup
      // 2c98: bipush 0
      // 2c99: bipush 0
      // 2c9a: bipush 0
      // 2c9b: invokespecial java/awt/Color.<init> (III)V
      // 2c9e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2ca1: aload 0
      // 2ca2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2ca5: ldc_w "Align"
      // 2ca8: sipush 541
      // 2cab: sipush 428
      // 2cae: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2cb1: aload 0
      // 2cb2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2cb5: ldc_w "Align in X"
      // 2cb8: sipush 433
      // 2cbb: sipush 450
      // 2cbe: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2cc1: aload 0
      // 2cc2: ldc_w " Rotate 90° "
      // 2cc5: sipush 535
      // 2cc8: sipush 450
      // 2ccb: bipush 4
      // 2ccc: bipush 0
      // 2ccd: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2cd0: aload 0
      // 2cd1: ldc_w "+10"
      // 2cd4: sipush 607
      // 2cd7: sipush 450
      // 2cda: bipush 4
      // 2cdb: bipush 0
      // 2cdc: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2cdf: aload 0
      // 2ce0: ldc_w "-10"
      // 2ce3: sipush 656
      // 2ce6: sipush 450
      // 2ce9: bipush 4
      // 2cea: bipush 0
      // 2ceb: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2cee: aload 0
      // 2cef: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2cf2: ldc_w "Align in Y"
      // 2cf5: sipush 433
      // 2cf8: sipush 474
      // 2cfb: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2cfe: aload 0
      // 2cff: ldc_w " Rotate 90° "
      // 2d02: sipush 535
      // 2d05: sipush 474
      // 2d08: bipush 4
      // 2d09: bipush 0
      // 2d0a: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d0d: aload 0
      // 2d0e: ldc_w "+10"
      // 2d11: sipush 607
      // 2d14: sipush 474
      // 2d17: bipush 4
      // 2d18: bipush 0
      // 2d19: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d1c: aload 0
      // 2d1d: ldc_w "-10"
      // 2d20: sipush 656
      // 2d23: sipush 474
      // 2d26: bipush 4
      // 2d27: bipush 0
      // 2d28: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d2b: aload 0
      // 2d2c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 2d2f: ldc_w "Align in Z"
      // 2d32: sipush 433
      // 2d35: sipush 498
      // 2d38: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2d3b: aload 0
      // 2d3c: ldc_w " Rotate 90° "
      // 2d3f: sipush 535
      // 2d42: sipush 498
      // 2d45: bipush 4
      // 2d46: bipush 0
      // 2d47: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d4a: aload 0
      // 2d4b: ldc_w "+10"
      // 2d4e: sipush 607
      // 2d51: sipush 498
      // 2d54: bipush 4
      // 2d55: bipush 0
      // 2d56: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d59: aload 0
      // 2d5a: ldc_w "-10"
      // 2d5d: sipush 656
      // 2d60: sipush 498
      // 2d63: bipush 4
      // 2d64: bipush 0
      // 2d65: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d68: aload 0
      // 2d69: ldc_w " Reset "
      // 2d6c: sipush 490
      // 2d6f: sipush 527
      // 2d72: bipush 0
      // 2d73: bipush 0
      // 2d74: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d77: aload 0
      // 2d78: ldc_w "      Save      "
      // 2d7b: sipush 607
      // 2d7e: sipush 527
      // 2d81: bipush 0
      // 2d82: aload 0
      // 2d83: getfield CarMaker.changed2 Z
      // 2d86: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 2d89: aload 0
      // 2d8a: getfield CarMaker.dtab I
      // 2d8d: bipush 3
      // 2d8e: if_icmpne 39ef
      // 2d91: aload 0
      // 2d92: getfield CarMaker.dtabed I
      // 2d95: aload 0
      // 2d96: getfield CarMaker.dtab I
      // 2d99: if_icmpeq 3364
      // 2d9c: bipush 45
      // 2d9e: istore 6
      // 2da0: bipush 45
      // 2da2: istore 7
      // 2da4: bipush 15
      // 2da6: istore 8
      // 2da8: bipush 15
      // 2daa: istore 9
      // 2dac: bipush 76
      // 2dae: istore 10
      // 2db0: bipush -76
      // 2db2: istore 11
      // 2db4: bipush 26
      // 2db6: istore 12
      // 2db8: bipush 26
      // 2dba: istore 13
      // 2dbc: bipush 20
      // 2dbe: istore 14
      // 2dc0: bipush 20
      // 2dc2: istore 15
      // 2dc4: bipush 18
      // 2dc6: istore 16
      // 2dc8: bipush 18
      // 2dca: istore 17
      // 2dcc: bipush 10
      // 2dce: istore 18
      // 2dd0: bipush 10
      // 2dd2: istore 19
      // 2dd4: bipush 0
      // 2dd5: istore 20
      // 2dd7: bipush 0
      // 2dd8: istore 21
      // 2dda: ldc_w "(140,140,140)"
      // 2ddd: astore 22
      // 2ddf: ldc_w "(140,140,140)"
      // 2de2: astore 23
      // 2de4: bipush 0
      // 2de5: istore 24
      // 2de7: new java/lang/StringBuilder
      // 2dea: dup
      // 2deb: invokespecial java/lang/StringBuilder.<init> ()V
      // 2dee: ldc ""
      // 2df0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2df3: aload 0
      // 2df4: getfield CarMaker.editor Ljava/awt/TextArea;
      // 2df7: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 2dfa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2dfd: ldc_w "\n"
      // 2e00: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2e03: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2e06: astore 25
      // 2e08: bipush 0
      // 2e09: istore 26
      // 2e0b: aload 25
      // 2e0d: ldc_w "\n"
      // 2e10: bipush 0
      // 2e11: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 2e14: istore 27
      // 2e16: bipush 0
      // 2e17: istore 28
      // 2e19: bipush 15
      // 2e1b: istore 29
      // 2e1d: bipush 20
      // 2e1f: istore 30
      // 2e21: ldc_w "(140,140,140)"
      // 2e24: astore 31
      // 2e26: iload 27
      // 2e28: bipush -1
      // 2e29: if_icmpeq 2fa3
      // 2e2c: iload 26
      // 2e2e: aload 25
      // 2e30: invokevirtual java/lang/String.length ()I
      // 2e33: if_icmpge 2fa3
      // 2e36: aload 25
      // 2e38: iload 26
      // 2e3a: iload 27
      // 2e3c: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 2e3f: astore 32
      // 2e41: aload 32
      // 2e43: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 2e46: astore 32
      // 2e48: iload 27
      // 2e4a: bipush 1
      // 2e4b: iadd
      // 2e4c: istore 26
      // 2e4e: aload 25
      // 2e50: ldc_w "\n"
      // 2e53: iload 26
      // 2e55: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 2e58: istore 27
      // 2e5a: aload 32
      // 2e5c: ldc_w "rims("
      // 2e5f: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 2e62: ifeq 2ec8
      // 2e65: new java/lang/StringBuilder
      // 2e68: dup
      // 2e69: invokespecial java/lang/StringBuilder.<init> ()V
      // 2e6c: ldc_w "("
      // 2e6f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2e72: aload 0
      // 2e73: ldc_w "rims"
      // 2e76: aload 32
      // 2e78: bipush 0
      // 2e79: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2e7c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2e7f: ldc_w ","
      // 2e82: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2e85: aload 0
      // 2e86: ldc_w "rims"
      // 2e89: aload 32
      // 2e8b: bipush 1
      // 2e8c: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2e8f: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2e92: ldc_w ","
      // 2e95: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2e98: aload 0
      // 2e99: ldc_w "rims"
      // 2e9c: aload 32
      // 2e9e: bipush 2
      // 2e9f: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2ea2: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2ea5: ldc_w ")"
      // 2ea8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2eab: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2eae: astore 31
      // 2eb0: aload 0
      // 2eb1: ldc_w "rims"
      // 2eb4: aload 32
      // 2eb6: bipush 3
      // 2eb7: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2eba: istore 29
      // 2ebc: aload 0
      // 2ebd: ldc_w "rims"
      // 2ec0: aload 32
      // 2ec2: bipush 4
      // 2ec3: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2ec6: istore 30
      // 2ec8: aload 32
      // 2eca: ldc_w "gwgr("
      // 2ecd: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 2ed0: ifeq 2edf
      // 2ed3: aload 0
      // 2ed4: ldc_w "gwgr"
      // 2ed7: aload 32
      // 2ed9: bipush 0
      // 2eda: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2edd: istore 28
      // 2edf: aload 32
      // 2ee1: ldc_w "w("
      // 2ee4: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 2ee7: ifeq 2f9b
      // 2eea: aload 0
      // 2eeb: ldc_w "w"
      // 2eee: aload 32
      // 2ef0: bipush 2
      // 2ef1: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2ef4: istore 33
      // 2ef6: iload 33
      // 2ef8: ifle 2f4b
      // 2efb: aload 0
      // 2efc: ldc_w "w"
      // 2eff: aload 32
      // 2f01: bipush 0
      // 2f02: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f05: invokestatic java/lang/Math.abs (I)I
      // 2f08: istore 6
      // 2f0a: aload 0
      // 2f0b: ldc_w "w"
      // 2f0e: aload 32
      // 2f10: bipush 1
      // 2f11: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f14: istore 8
      // 2f16: iload 33
      // 2f18: istore 10
      // 2f1a: aload 0
      // 2f1b: ldc_w "w"
      // 2f1e: aload 32
      // 2f20: bipush 4
      // 2f21: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f24: invokestatic java/lang/Math.abs (I)I
      // 2f27: istore 12
      // 2f29: aload 0
      // 2f2a: ldc_w "w"
      // 2f2d: aload 32
      // 2f2f: bipush 5
      // 2f30: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f33: invokestatic java/lang/Math.abs (I)I
      // 2f36: istore 14
      // 2f38: aload 31
      // 2f3a: astore 22
      // 2f3c: iload 29
      // 2f3e: istore 16
      // 2f40: iload 30
      // 2f42: istore 18
      // 2f44: iload 28
      // 2f46: istore 20
      // 2f48: goto 2f98
      // 2f4b: aload 0
      // 2f4c: ldc_w "w"
      // 2f4f: aload 32
      // 2f51: bipush 0
      // 2f52: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f55: invokestatic java/lang/Math.abs (I)I
      // 2f58: istore 7
      // 2f5a: aload 0
      // 2f5b: ldc_w "w"
      // 2f5e: aload 32
      // 2f60: bipush 1
      // 2f61: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f64: istore 9
      // 2f66: iload 33
      // 2f68: istore 11
      // 2f6a: aload 0
      // 2f6b: ldc_w "w"
      // 2f6e: aload 32
      // 2f70: bipush 4
      // 2f71: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f74: invokestatic java/lang/Math.abs (I)I
      // 2f77: istore 13
      // 2f79: aload 0
      // 2f7a: ldc_w "w"
      // 2f7d: aload 32
      // 2f7f: bipush 5
      // 2f80: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 2f83: invokestatic java/lang/Math.abs (I)I
      // 2f86: istore 15
      // 2f88: aload 31
      // 2f8a: astore 23
      // 2f8c: iload 29
      // 2f8e: istore 17
      // 2f90: iload 30
      // 2f92: istore 19
      // 2f94: iload 28
      // 2f96: istore 21
      // 2f98: iinc 24 1
      // 2f9b: goto 2fa0
      // 2f9e: astore 33
      // 2fa0: goto 2e26
      // 2fa3: iload 24
      // 2fa5: bipush 4
      // 2fa6: if_icmpeq 2fb1
      // 2fa9: aload 0
      // 2faa: bipush 1
      // 2fab: putfield CarMaker.defnow Z
      // 2fae: goto 2fb6
      // 2fb1: aload 0
      // 2fb2: bipush 0
      // 2fb3: putfield CarMaker.defnow Z
      // 2fb6: aload 0
      // 2fb7: getfield CarMaker.wv [Ljava/awt/TextField;
      // 2fba: bipush 0
      // 2fbb: aaload
      // 2fbc: new java/lang/StringBuilder
      // 2fbf: dup
      // 2fc0: invokespecial java/lang/StringBuilder.<init> ()V
      // 2fc3: ldc ""
      // 2fc5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2fc8: iload 7
      // 2fca: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2fcd: ldc ""
      // 2fcf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2fd2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2fd5: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 2fd8: aload 0
      // 2fd9: getfield CarMaker.wv [Ljava/awt/TextField;
      // 2fdc: bipush 1
      // 2fdd: aaload
      // 2fde: new java/lang/StringBuilder
      // 2fe1: dup
      // 2fe2: invokespecial java/lang/StringBuilder.<init> ()V
      // 2fe5: ldc ""
      // 2fe7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2fea: iload 9
      // 2fec: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2fef: ldc ""
      // 2ff1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2ff4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2ff7: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 2ffa: aload 0
      // 2ffb: getfield CarMaker.wv [Ljava/awt/TextField;
      // 2ffe: bipush 2
      // 2fff: aaload
      // 3000: new java/lang/StringBuilder
      // 3003: dup
      // 3004: invokespecial java/lang/StringBuilder.<init> ()V
      // 3007: ldc ""
      // 3009: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 300c: iload 11
      // 300e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3011: ldc ""
      // 3013: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3016: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3019: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 301c: aload 0
      // 301d: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3020: bipush 3
      // 3021: aaload
      // 3022: new java/lang/StringBuilder
      // 3025: dup
      // 3026: invokespecial java/lang/StringBuilder.<init> ()V
      // 3029: ldc ""
      // 302b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 302e: iload 15
      // 3030: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3033: ldc ""
      // 3035: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3038: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 303b: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 303e: aload 0
      // 303f: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3042: bipush 4
      // 3043: aaload
      // 3044: new java/lang/StringBuilder
      // 3047: dup
      // 3048: invokespecial java/lang/StringBuilder.<init> ()V
      // 304b: ldc ""
      // 304d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3050: iload 13
      // 3052: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3055: ldc ""
      // 3057: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 305a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 305d: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 3060: aload 0
      // 3061: getfield CarMaker.srch Ljava/awt/TextField;
      // 3064: aload 23
      // 3066: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 3069: aload 0
      // 306a: getfield CarMaker.wv [Ljava/awt/TextField;
      // 306d: bipush 5
      // 306e: aaload
      // 306f: new java/lang/StringBuilder
      // 3072: dup
      // 3073: invokespecial java/lang/StringBuilder.<init> ()V
      // 3076: ldc ""
      // 3078: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 307b: iload 17
      // 307d: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3080: ldc ""
      // 3082: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3085: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3088: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 308b: aload 0
      // 308c: getfield CarMaker.wv [Ljava/awt/TextField;
      // 308f: bipush 6
      // 3091: aaload
      // 3092: new java/lang/StringBuilder
      // 3095: dup
      // 3096: invokespecial java/lang/StringBuilder.<init> ()V
      // 3099: ldc ""
      // 309b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 309e: iload 19
      // 30a0: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 30a3: ldc ""
      // 30a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 30a8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 30ab: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 30ae: aload 0
      // 30af: getfield CarMaker.wv [Ljava/awt/TextField;
      // 30b2: bipush 7
      // 30b4: aaload
      // 30b5: new java/lang/StringBuilder
      // 30b8: dup
      // 30b9: invokespecial java/lang/StringBuilder.<init> ()V
      // 30bc: ldc ""
      // 30be: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 30c1: iload 21
      // 30c3: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 30c6: ldc ""
      // 30c8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 30cb: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 30ce: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 30d1: aload 0
      // 30d2: getfield CarMaker.wv [Ljava/awt/TextField;
      // 30d5: bipush 8
      // 30d7: aaload
      // 30d8: new java/lang/StringBuilder
      // 30db: dup
      // 30dc: invokespecial java/lang/StringBuilder.<init> ()V
      // 30df: ldc ""
      // 30e1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 30e4: iload 6
      // 30e6: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 30e9: ldc ""
      // 30eb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 30ee: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 30f1: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 30f4: aload 0
      // 30f5: getfield CarMaker.wv [Ljava/awt/TextField;
      // 30f8: bipush 9
      // 30fa: aaload
      // 30fb: new java/lang/StringBuilder
      // 30fe: dup
      // 30ff: invokespecial java/lang/StringBuilder.<init> ()V
      // 3102: ldc ""
      // 3104: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3107: iload 8
      // 3109: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 310c: ldc ""
      // 310e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3111: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3114: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 3117: aload 0
      // 3118: getfield CarMaker.wv [Ljava/awt/TextField;
      // 311b: bipush 10
      // 311d: aaload
      // 311e: new java/lang/StringBuilder
      // 3121: dup
      // 3122: invokespecial java/lang/StringBuilder.<init> ()V
      // 3125: ldc ""
      // 3127: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 312a: iload 10
      // 312c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 312f: ldc ""
      // 3131: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3134: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3137: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 313a: aload 0
      // 313b: getfield CarMaker.wv [Ljava/awt/TextField;
      // 313e: bipush 11
      // 3140: aaload
      // 3141: new java/lang/StringBuilder
      // 3144: dup
      // 3145: invokespecial java/lang/StringBuilder.<init> ()V
      // 3148: ldc ""
      // 314a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 314d: iload 14
      // 314f: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3152: ldc ""
      // 3154: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3157: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 315a: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 315d: aload 0
      // 315e: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3161: bipush 12
      // 3163: aaload
      // 3164: new java/lang/StringBuilder
      // 3167: dup
      // 3168: invokespecial java/lang/StringBuilder.<init> ()V
      // 316b: ldc ""
      // 316d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3170: iload 12
      // 3172: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3175: ldc ""
      // 3177: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 317a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 317d: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 3180: aload 0
      // 3181: getfield CarMaker.rplc Ljava/awt/TextField;
      // 3184: aload 22
      // 3186: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 3189: aload 0
      // 318a: getfield CarMaker.wv [Ljava/awt/TextField;
      // 318d: bipush 13
      // 318f: aaload
      // 3190: new java/lang/StringBuilder
      // 3193: dup
      // 3194: invokespecial java/lang/StringBuilder.<init> ()V
      // 3197: ldc ""
      // 3199: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 319c: iload 16
      // 319e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 31a1: ldc ""
      // 31a3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31a6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 31a9: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 31ac: aload 0
      // 31ad: getfield CarMaker.wv [Ljava/awt/TextField;
      // 31b0: bipush 14
      // 31b2: aaload
      // 31b3: new java/lang/StringBuilder
      // 31b6: dup
      // 31b7: invokespecial java/lang/StringBuilder.<init> ()V
      // 31ba: ldc ""
      // 31bc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31bf: iload 18
      // 31c1: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 31c4: ldc ""
      // 31c6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31c9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 31cc: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 31cf: aload 0
      // 31d0: getfield CarMaker.wv [Ljava/awt/TextField;
      // 31d3: bipush 15
      // 31d5: aaload
      // 31d6: new java/lang/StringBuilder
      // 31d9: dup
      // 31da: invokespecial java/lang/StringBuilder.<init> ()V
      // 31dd: ldc ""
      // 31df: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31e2: iload 20
      // 31e4: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 31e7: ldc ""
      // 31e9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31ec: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 31ef: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 31f2: aload 0
      // 31f3: new java/lang/StringBuilder
      // 31f6: dup
      // 31f7: invokespecial java/lang/StringBuilder.<init> ()V
      // 31fa: ldc ""
      // 31fc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 31ff: aload 0
      // 3200: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3203: bipush 0
      // 3204: aaload
      // 3205: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3208: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 320b: ldc ""
      // 320d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3210: aload 0
      // 3211: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3214: bipush 1
      // 3215: aaload
      // 3216: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3219: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 321c: ldc ""
      // 321e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3221: aload 0
      // 3222: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3225: bipush 2
      // 3226: aaload
      // 3227: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 322a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 322d: ldc ""
      // 322f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3232: aload 0
      // 3233: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3236: bipush 3
      // 3237: aaload
      // 3238: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 323b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 323e: ldc ""
      // 3240: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3243: aload 0
      // 3244: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3247: bipush 4
      // 3248: aaload
      // 3249: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 324c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 324f: ldc ""
      // 3251: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3254: aload 0
      // 3255: getfield CarMaker.srch Ljava/awt/TextField;
      // 3258: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 325b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 325e: ldc ""
      // 3260: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3263: aload 0
      // 3264: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3267: bipush 5
      // 3268: aaload
      // 3269: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 326c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 326f: ldc ""
      // 3271: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3274: aload 0
      // 3275: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3278: bipush 6
      // 327a: aaload
      // 327b: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 327e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3281: ldc ""
      // 3283: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3286: aload 0
      // 3287: getfield CarMaker.wv [Ljava/awt/TextField;
      // 328a: bipush 7
      // 328c: aaload
      // 328d: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3290: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3293: ldc ""
      // 3295: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3298: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 329b: putfield CarMaker.aply1 Ljava/lang/String;
      // 329e: aload 0
      // 329f: new java/lang/StringBuilder
      // 32a2: dup
      // 32a3: invokespecial java/lang/StringBuilder.<init> ()V
      // 32a6: ldc ""
      // 32a8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32ab: aload 0
      // 32ac: getfield CarMaker.wv [Ljava/awt/TextField;
      // 32af: bipush 8
      // 32b1: aaload
      // 32b2: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 32b5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32b8: ldc ""
      // 32ba: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32bd: aload 0
      // 32be: getfield CarMaker.wv [Ljava/awt/TextField;
      // 32c1: bipush 9
      // 32c3: aaload
      // 32c4: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 32c7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32ca: ldc ""
      // 32cc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32cf: aload 0
      // 32d0: getfield CarMaker.wv [Ljava/awt/TextField;
      // 32d3: bipush 10
      // 32d5: aaload
      // 32d6: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 32d9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32dc: ldc ""
      // 32de: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32e1: aload 0
      // 32e2: getfield CarMaker.wv [Ljava/awt/TextField;
      // 32e5: bipush 11
      // 32e7: aaload
      // 32e8: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 32eb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32ee: ldc ""
      // 32f0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 32f3: aload 0
      // 32f4: getfield CarMaker.wv [Ljava/awt/TextField;
      // 32f7: bipush 12
      // 32f9: aaload
      // 32fa: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 32fd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3300: ldc ""
      // 3302: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3305: aload 0
      // 3306: getfield CarMaker.rplc Ljava/awt/TextField;
      // 3309: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 330c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 330f: ldc ""
      // 3311: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3314: aload 0
      // 3315: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3318: bipush 13
      // 331a: aaload
      // 331b: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 331e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3321: ldc ""
      // 3323: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3326: aload 0
      // 3327: getfield CarMaker.wv [Ljava/awt/TextField;
      // 332a: bipush 14
      // 332c: aaload
      // 332d: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3330: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3333: ldc ""
      // 3335: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3338: aload 0
      // 3339: getfield CarMaker.wv [Ljava/awt/TextField;
      // 333c: bipush 15
      // 333e: aaload
      // 333f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3342: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3345: ldc ""
      // 3347: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 334a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 334d: putfield CarMaker.aply2 Ljava/lang/String;
      // 3350: aload 0
      // 3351: bipush 0
      // 3352: putfield CarMaker.aplyd1 Z
      // 3355: aload 0
      // 3356: bipush 0
      // 3357: putfield CarMaker.aplyd2 Z
      // 335a: aload 0
      // 335b: bipush 0
      // 335c: putfield CarMaker.changed2 Z
      // 335f: aload 0
      // 3360: bipush -1
      // 3361: putfield CarMaker.mouseon I
      // 3364: aload 0
      // 3365: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3368: new java/awt/Color
      // 336b: dup
      // 336c: bipush 0
      // 336d: bipush 0
      // 336e: bipush 0
      // 336f: invokespecial java/awt/Color.<init> (III)V
      // 3372: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3375: aload 0
      // 3376: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3379: ldc_w "BACK Wheels :"
      // 337c: bipush 12
      // 337e: sipush 424
      // 3381: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3384: aload 0
      // 3385: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3388: ldc_w "±X :"
      // 338b: bipush 12
      // 338d: sipush 449
      // 3390: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3393: aload 0
      // 3394: aload 0
      // 3395: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3398: bipush 0
      // 3399: aaload
      // 339a: bipush 35
      // 339c: sipush 433
      // 339f: bipush 40
      // 33a1: bipush 22
      // 33a3: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 33a6: aload 0
      // 33a7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 33aa: ldc_w "Y :"
      // 33ad: bipush 86
      // 33af: sipush 449
      // 33b2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 33b5: aload 0
      // 33b6: aload 0
      // 33b7: getfield CarMaker.wv [Ljava/awt/TextField;
      // 33ba: bipush 1
      // 33bb: aaload
      // 33bc: bipush 101
      // 33be: sipush 433
      // 33c1: bipush 40
      // 33c3: bipush 22
      // 33c5: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 33c8: aload 0
      // 33c9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 33cc: ldc_w "Z :"
      // 33cf: sipush 151
      // 33d2: sipush 449
      // 33d5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 33d8: aload 0
      // 33d9: aload 0
      // 33da: getfield CarMaker.wv [Ljava/awt/TextField;
      // 33dd: bipush 2
      // 33de: aaload
      // 33df: sipush 166
      // 33e2: sipush 433
      // 33e5: bipush 40
      // 33e7: bipush 22
      // 33e9: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 33ec: aload 0
      // 33ed: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 33f0: ldc_w "Height :"
      // 33f3: bipush 12
      // 33f5: sipush 479
      // 33f8: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 33fb: aload 0
      // 33fc: aload 0
      // 33fd: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3400: bipush 3
      // 3401: aaload
      // 3402: bipush 56
      // 3404: sipush 463
      // 3407: bipush 40
      // 3409: bipush 22
      // 340b: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 340e: aload 0
      // 340f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3412: ldc_w "Width :"
      // 3415: bipush 107
      // 3417: sipush 479
      // 341a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 341d: aload 0
      // 341e: aload 0
      // 341f: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3422: bipush 4
      // 3423: aaload
      // 3424: sipush 148
      // 3427: sipush 463
      // 342a: bipush 40
      // 342c: bipush 22
      // 342e: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3431: aload 0
      // 3432: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3435: ldc_w "Rims RGB Color :"
      // 3438: bipush 12
      // 343a: sipush 509
      // 343d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3440: aload 0
      // 3441: aload 0
      // 3442: getfield CarMaker.srch Ljava/awt/TextField;
      // 3445: bipush 109
      // 3447: sipush 493
      // 344a: sipush 129
      // 344d: bipush 22
      // 344f: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3452: aload 0
      // 3453: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3456: ldc_w "Rims Size :"
      // 3459: bipush 12
      // 345b: sipush 539
      // 345e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3461: aload 0
      // 3462: aload 0
      // 3463: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3466: bipush 5
      // 3467: aaload
      // 3468: bipush 76
      // 346a: sipush 523
      // 346d: bipush 40
      // 346f: bipush 22
      // 3471: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3474: aload 0
      // 3475: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3478: ldc_w "Rims Depth :"
      // 347b: bipush 126
      // 347d: sipush 539
      // 3480: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3483: aload 0
      // 3484: aload 0
      // 3485: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3488: bipush 6
      // 348a: aaload
      // 348b: sipush 199
      // 348e: sipush 523
      // 3491: bipush 40
      // 3493: bipush 22
      // 3495: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3498: aload 0
      // 3499: getfield CarMaker.xm I
      // 349c: sipush 245
      // 349f: if_icmple 3516
      // 34a2: aload 0
      // 34a3: getfield CarMaker.xm I
      // 34a6: sipush 336
      // 34a9: if_icmpge 3516
      // 34ac: aload 0
      // 34ad: getfield CarMaker.ym I
      // 34b0: sipush 524
      // 34b3: if_icmple 3516
      // 34b6: aload 0
      // 34b7: getfield CarMaker.ym I
      // 34ba: sipush 541
      // 34bd: if_icmpge 3516
      // 34c0: aload 0
      // 34c1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 34c4: new java/awt/Color
      // 34c7: dup
      // 34c8: sipush 255
      // 34cb: bipush 0
      // 34cc: bipush 0
      // 34cd: invokespecial java/awt/Color.<init> (III)V
      // 34d0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 34d3: aload 0
      // 34d4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 34d7: sipush 248
      // 34da: sipush 540
      // 34dd: sipush 279
      // 34e0: sipush 540
      // 34e3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 34e6: aload 0
      // 34e7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 34ea: sipush 327
      // 34ed: sipush 540
      // 34f0: sipush 334
      // 34f3: sipush 540
      // 34f6: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 34f9: aload 0
      // 34fa: getfield CarMaker.mouseon I
      // 34fd: bipush -1
      // 34fe: if_icmpne 352f
      // 3501: aload 0
      // 3502: bipush 1
      // 3503: putfield CarMaker.mouseon I
      // 3506: aload 0
      // 3507: new java/awt/Cursor
      // 350a: dup
      // 350b: bipush 12
      // 350d: invokespecial java/awt/Cursor.<init> (I)V
      // 3510: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 3513: goto 352f
      // 3516: aload 0
      // 3517: getfield CarMaker.mouseon I
      // 351a: bipush 1
      // 351b: if_icmpne 352f
      // 351e: aload 0
      // 351f: bipush -1
      // 3520: putfield CarMaker.mouseon I
      // 3523: aload 0
      // 3524: new java/awt/Cursor
      // 3527: dup
      // 3528: bipush 0
      // 3529: invokespecial java/awt/Cursor.<init> (I)V
      // 352c: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 352f: aload 0
      // 3530: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3533: ldc_w "Hide :                ?"
      // 3536: sipush 249
      // 3539: sipush 539
      // 353c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 353f: aload 0
      // 3540: aload 0
      // 3541: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3544: bipush 7
      // 3546: aaload
      // 3547: sipush 282
      // 354a: sipush 523
      // 354d: bipush 40
      // 354f: bipush 22
      // 3551: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3554: aload 0
      // 3555: ldc_w "   Apply   "
      // 3558: sipush 300
      // 355b: sipush 440
      // 355e: bipush 0
      // 355f: aload 0
      // 3560: getfield CarMaker.aplyd1 Z
      // 3563: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 3566: aload 0
      // 3567: ldc_w "   Save   "
      // 356a: sipush 300
      // 356d: sipush 477
      // 3570: bipush 0
      // 3571: aload 0
      // 3572: getfield CarMaker.changed2 Z
      // 3575: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 3578: aload 0
      // 3579: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 357c: ldc_w "FRONT Wheels :"
      // 357f: sipush 362
      // 3582: sipush 424
      // 3585: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3588: aload 0
      // 3589: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 358c: ldc_w "±X :"
      // 358f: sipush 362
      // 3592: sipush 449
      // 3595: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3598: aload 0
      // 3599: aload 0
      // 359a: getfield CarMaker.wv [Ljava/awt/TextField;
      // 359d: bipush 8
      // 359f: aaload
      // 35a0: sipush 385
      // 35a3: sipush 433
      // 35a6: bipush 40
      // 35a8: bipush 22
      // 35aa: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 35ad: aload 0
      // 35ae: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 35b1: ldc_w "Y :"
      // 35b4: sipush 436
      // 35b7: sipush 449
      // 35ba: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 35bd: aload 0
      // 35be: aload 0
      // 35bf: getfield CarMaker.wv [Ljava/awt/TextField;
      // 35c2: bipush 9
      // 35c4: aaload
      // 35c5: sipush 451
      // 35c8: sipush 433
      // 35cb: bipush 40
      // 35cd: bipush 22
      // 35cf: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 35d2: aload 0
      // 35d3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 35d6: ldc_w "Z :"
      // 35d9: sipush 501
      // 35dc: sipush 449
      // 35df: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 35e2: aload 0
      // 35e3: aload 0
      // 35e4: getfield CarMaker.wv [Ljava/awt/TextField;
      // 35e7: bipush 10
      // 35e9: aaload
      // 35ea: sipush 516
      // 35ed: sipush 433
      // 35f0: bipush 40
      // 35f2: bipush 22
      // 35f4: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 35f7: aload 0
      // 35f8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 35fb: ldc_w "Height :"
      // 35fe: sipush 362
      // 3601: sipush 479
      // 3604: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3607: aload 0
      // 3608: aload 0
      // 3609: getfield CarMaker.wv [Ljava/awt/TextField;
      // 360c: bipush 11
      // 360e: aaload
      // 360f: sipush 406
      // 3612: sipush 463
      // 3615: bipush 40
      // 3617: bipush 22
      // 3619: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 361c: aload 0
      // 361d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3620: ldc_w "Width :"
      // 3623: sipush 457
      // 3626: sipush 479
      // 3629: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 362c: aload 0
      // 362d: aload 0
      // 362e: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3631: bipush 12
      // 3633: aaload
      // 3634: sipush 498
      // 3637: sipush 463
      // 363a: bipush 40
      // 363c: bipush 22
      // 363e: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3641: aload 0
      // 3642: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3645: ldc_w "Rims RGB Color :"
      // 3648: sipush 362
      // 364b: sipush 509
      // 364e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3651: aload 0
      // 3652: aload 0
      // 3653: getfield CarMaker.rplc Ljava/awt/TextField;
      // 3656: sipush 459
      // 3659: sipush 493
      // 365c: sipush 129
      // 365f: bipush 22
      // 3661: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3664: aload 0
      // 3665: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3668: ldc_w "Rims Size :"
      // 366b: sipush 362
      // 366e: sipush 539
      // 3671: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3674: aload 0
      // 3675: aload 0
      // 3676: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3679: bipush 13
      // 367b: aaload
      // 367c: sipush 426
      // 367f: sipush 523
      // 3682: bipush 40
      // 3684: bipush 22
      // 3686: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 3689: aload 0
      // 368a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 368d: ldc_w "Rims Depth :"
      // 3690: sipush 476
      // 3693: sipush 539
      // 3696: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3699: aload 0
      // 369a: aload 0
      // 369b: getfield CarMaker.wv [Ljava/awt/TextField;
      // 369e: bipush 14
      // 36a0: aaload
      // 36a1: sipush 549
      // 36a4: sipush 523
      // 36a7: bipush 40
      // 36a9: bipush 22
      // 36ab: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 36ae: aload 0
      // 36af: getfield CarMaker.xm I
      // 36b2: sipush 595
      // 36b5: if_icmple 372c
      // 36b8: aload 0
      // 36b9: getfield CarMaker.xm I
      // 36bc: sipush 686
      // 36bf: if_icmpge 372c
      // 36c2: aload 0
      // 36c3: getfield CarMaker.ym I
      // 36c6: sipush 524
      // 36c9: if_icmple 372c
      // 36cc: aload 0
      // 36cd: getfield CarMaker.ym I
      // 36d0: sipush 541
      // 36d3: if_icmpge 372c
      // 36d6: aload 0
      // 36d7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 36da: new java/awt/Color
      // 36dd: dup
      // 36de: sipush 255
      // 36e1: bipush 0
      // 36e2: bipush 0
      // 36e3: invokespecial java/awt/Color.<init> (III)V
      // 36e6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 36e9: aload 0
      // 36ea: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 36ed: sipush 598
      // 36f0: sipush 540
      // 36f3: sipush 629
      // 36f6: sipush 540
      // 36f9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 36fc: aload 0
      // 36fd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3700: sipush 677
      // 3703: sipush 540
      // 3706: sipush 684
      // 3709: sipush 540
      // 370c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 370f: aload 0
      // 3710: getfield CarMaker.mouseon I
      // 3713: bipush -1
      // 3714: if_icmpne 3745
      // 3717: aload 0
      // 3718: bipush 2
      // 3719: putfield CarMaker.mouseon I
      // 371c: aload 0
      // 371d: new java/awt/Cursor
      // 3720: dup
      // 3721: bipush 12
      // 3723: invokespecial java/awt/Cursor.<init> (I)V
      // 3726: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 3729: goto 3745
      // 372c: aload 0
      // 372d: getfield CarMaker.mouseon I
      // 3730: bipush 2
      // 3731: if_icmpne 3745
      // 3734: aload 0
      // 3735: bipush -1
      // 3736: putfield CarMaker.mouseon I
      // 3739: aload 0
      // 373a: new java/awt/Cursor
      // 373d: dup
      // 373e: bipush 0
      // 373f: invokespecial java/awt/Cursor.<init> (I)V
      // 3742: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 3745: aload 0
      // 3746: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3749: ldc_w "Hide :                ?"
      // 374c: sipush 599
      // 374f: sipush 539
      // 3752: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3755: aload 0
      // 3756: aload 0
      // 3757: getfield CarMaker.wv [Ljava/awt/TextField;
      // 375a: bipush 15
      // 375c: aaload
      // 375d: sipush 632
      // 3760: sipush 523
      // 3763: bipush 40
      // 3765: bipush 22
      // 3767: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 376a: aload 0
      // 376b: ldc_w "   Apply   "
      // 376e: sipush 650
      // 3771: sipush 440
      // 3774: bipush 0
      // 3775: aload 0
      // 3776: getfield CarMaker.aplyd2 Z
      // 3779: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 377c: aload 0
      // 377d: ldc_w "   Save   "
      // 3780: sipush 650
      // 3783: sipush 477
      // 3786: bipush 0
      // 3787: aload 0
      // 3788: getfield CarMaker.changed2 Z
      // 378b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 378e: aload 0
      // 378f: getfield CarMaker.mouses I
      // 3792: bipush -1
      // 3793: if_icmpne 37b0
      // 3796: aload 0
      // 3797: getfield CarMaker.mouseon I
      // 379a: bipush 1
      // 379b: if_icmpeq 37a6
      // 379e: aload 0
      // 379f: getfield CarMaker.mouseon I
      // 37a2: bipush 2
      // 37a3: if_icmpne 37b0
      // 37a6: aconst_null
      // 37a7: ldc_w "Use this variable to hide the car wheels inside the car if you need to (if they are getting drawn over the car when they\nshould be drawn behind it).\n\nIf you have created a car model with specific places for the wheels go inside them (inside the car), if when you place the\nwheels there they don’t get drawn inside the car (they get drawn over it instead), use this variable to adjust that.\n\nYou can also use this variable to do the opposite, to make the wheels get drawn over the car if they are getting drawn\nbehind it when they shouldn’t.\n\nThe Hide variable takes values from -40 to 40:\nA +ve value from 1 to 40 makes the wheels more hidden, where 40 is the maximum the car wheel can be hidden.\nA -ve value from -1 to -40 does exactly the opposite and makes the wheels more apparent (this if the car wheels appear\ninside the car when they should be outside).\nA 0 value means do nothing.\nMost of the time you will need to use this variable, it will be to enter +ve values from 1-40 for hiding the car wheels.\n\n"
      // 37aa: ldc "Car Maker"
      // 37ac: bipush 1
      // 37ad: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 37b0: bipush 0
      // 37b1: istore 6
      // 37b3: iload 6
      // 37b5: bipush 16
      // 37b7: if_icmpge 37e9
      // 37ba: aload 0
      // 37bb: getfield CarMaker.wv [Ljava/awt/TextField;
      // 37be: iload 6
      // 37c0: aaload
      // 37c1: invokevirtual java/awt/TextField.hasFocus ()Z
      // 37c4: ifeq 37cc
      // 37c7: aload 0
      // 37c8: bipush 0
      // 37c9: putfield CarMaker.focuson Z
      // 37cc: aload 0
      // 37cd: getfield CarMaker.wv [Ljava/awt/TextField;
      // 37d0: iload 6
      // 37d2: aaload
      // 37d3: invokevirtual java/awt/TextField.isShowing ()Z
      // 37d6: ifne 37e3
      // 37d9: aload 0
      // 37da: getfield CarMaker.wv [Ljava/awt/TextField;
      // 37dd: iload 6
      // 37df: aaload
      // 37e0: invokevirtual java/awt/TextField.show ()V
      // 37e3: iinc 6 1
      // 37e6: goto 37b3
      // 37e9: aload 0
      // 37ea: getfield CarMaker.srch Ljava/awt/TextField;
      // 37ed: invokevirtual java/awt/TextField.hasFocus ()Z
      // 37f0: ifeq 37f8
      // 37f3: aload 0
      // 37f4: bipush 0
      // 37f5: putfield CarMaker.focuson Z
      // 37f8: aload 0
      // 37f9: getfield CarMaker.srch Ljava/awt/TextField;
      // 37fc: invokevirtual java/awt/TextField.isShowing ()Z
      // 37ff: ifne 3809
      // 3802: aload 0
      // 3803: getfield CarMaker.srch Ljava/awt/TextField;
      // 3806: invokevirtual java/awt/TextField.show ()V
      // 3809: aload 0
      // 380a: getfield CarMaker.rplc Ljava/awt/TextField;
      // 380d: invokevirtual java/awt/TextField.hasFocus ()Z
      // 3810: ifeq 3818
      // 3813: aload 0
      // 3814: bipush 0
      // 3815: putfield CarMaker.focuson Z
      // 3818: aload 0
      // 3819: getfield CarMaker.rplc Ljava/awt/TextField;
      // 381c: invokevirtual java/awt/TextField.isShowing ()Z
      // 381f: ifne 3829
      // 3822: aload 0
      // 3823: getfield CarMaker.rplc Ljava/awt/TextField;
      // 3826: invokevirtual java/awt/TextField.show ()V
      // 3829: aload 0
      // 382a: getfield CarMaker.focuson Z
      // 382d: ifne 39b2
      // 3830: aload 0
      // 3831: getfield CarMaker.aplyd1 Z
      // 3834: ifne 38ee
      // 3837: aload 0
      // 3838: getfield CarMaker.aply1 Ljava/lang/String;
      // 383b: new java/lang/StringBuilder
      // 383e: dup
      // 383f: invokespecial java/lang/StringBuilder.<init> ()V
      // 3842: ldc ""
      // 3844: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3847: aload 0
      // 3848: getfield CarMaker.wv [Ljava/awt/TextField;
      // 384b: bipush 0
      // 384c: aaload
      // 384d: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3850: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3853: ldc ""
      // 3855: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3858: aload 0
      // 3859: getfield CarMaker.wv [Ljava/awt/TextField;
      // 385c: bipush 1
      // 385d: aaload
      // 385e: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3861: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3864: ldc ""
      // 3866: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3869: aload 0
      // 386a: getfield CarMaker.wv [Ljava/awt/TextField;
      // 386d: bipush 2
      // 386e: aaload
      // 386f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3872: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3875: ldc ""
      // 3877: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 387a: aload 0
      // 387b: getfield CarMaker.wv [Ljava/awt/TextField;
      // 387e: bipush 3
      // 387f: aaload
      // 3880: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3883: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3886: ldc ""
      // 3888: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 388b: aload 0
      // 388c: getfield CarMaker.wv [Ljava/awt/TextField;
      // 388f: bipush 4
      // 3890: aaload
      // 3891: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3894: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3897: ldc ""
      // 3899: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 389c: aload 0
      // 389d: getfield CarMaker.srch Ljava/awt/TextField;
      // 38a0: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 38a3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38a6: ldc ""
      // 38a8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38ab: aload 0
      // 38ac: getfield CarMaker.wv [Ljava/awt/TextField;
      // 38af: bipush 5
      // 38b0: aaload
      // 38b1: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 38b4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38b7: ldc ""
      // 38b9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38bc: aload 0
      // 38bd: getfield CarMaker.wv [Ljava/awt/TextField;
      // 38c0: bipush 6
      // 38c2: aaload
      // 38c3: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 38c6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38c9: ldc ""
      // 38cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38ce: aload 0
      // 38cf: getfield CarMaker.wv [Ljava/awt/TextField;
      // 38d2: bipush 7
      // 38d4: aaload
      // 38d5: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 38d8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38db: ldc ""
      // 38dd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38e0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 38e3: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 38e6: ifne 38ee
      // 38e9: aload 0
      // 38ea: bipush 1
      // 38eb: putfield CarMaker.aplyd1 Z
      // 38ee: aload 0
      // 38ef: getfield CarMaker.aplyd2 Z
      // 38f2: ifne 39b2
      // 38f5: aload 0
      // 38f6: getfield CarMaker.aply2 Ljava/lang/String;
      // 38f9: new java/lang/StringBuilder
      // 38fc: dup
      // 38fd: invokespecial java/lang/StringBuilder.<init> ()V
      // 3900: ldc ""
      // 3902: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3905: aload 0
      // 3906: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3909: bipush 8
      // 390b: aaload
      // 390c: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 390f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3912: ldc ""
      // 3914: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3917: aload 0
      // 3918: getfield CarMaker.wv [Ljava/awt/TextField;
      // 391b: bipush 9
      // 391d: aaload
      // 391e: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3921: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3924: ldc ""
      // 3926: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3929: aload 0
      // 392a: getfield CarMaker.wv [Ljava/awt/TextField;
      // 392d: bipush 10
      // 392f: aaload
      // 3930: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3933: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3936: ldc ""
      // 3938: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 393b: aload 0
      // 393c: getfield CarMaker.wv [Ljava/awt/TextField;
      // 393f: bipush 11
      // 3941: aaload
      // 3942: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3945: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3948: ldc ""
      // 394a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 394d: aload 0
      // 394e: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3951: bipush 12
      // 3953: aaload
      // 3954: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3957: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 395a: ldc ""
      // 395c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 395f: aload 0
      // 3960: getfield CarMaker.rplc Ljava/awt/TextField;
      // 3963: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3966: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3969: ldc ""
      // 396b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 396e: aload 0
      // 396f: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3972: bipush 13
      // 3974: aaload
      // 3975: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 3978: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 397b: ldc ""
      // 397d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3980: aload 0
      // 3981: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3984: bipush 14
      // 3986: aaload
      // 3987: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 398a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 398d: ldc ""
      // 398f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3992: aload 0
      // 3993: getfield CarMaker.wv [Ljava/awt/TextField;
      // 3996: bipush 15
      // 3998: aaload
      // 3999: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 399c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 399f: ldc ""
      // 39a1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 39a4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 39a7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 39aa: ifne 39b2
      // 39ad: aload 0
      // 39ae: bipush 1
      // 39af: putfield CarMaker.aplyd2 Z
      // 39b2: aload 0
      // 39b3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 39b6: new java/awt/Color
      // 39b9: dup
      // 39ba: sipush 170
      // 39bd: sipush 170
      // 39c0: sipush 170
      // 39c3: invokespecial java/awt/Color.<init> (III)V
      // 39c6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 39c9: aload 0
      // 39ca: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 39cd: sipush 350
      // 39d0: sipush 410
      // 39d3: sipush 350
      // 39d6: sipush 550
      // 39d9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 39dc: aload 0
      // 39dd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 39e0: sipush 300
      // 39e3: sipush 409
      // 39e6: sipush 400
      // 39e9: sipush 409
      // 39ec: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 39ef: aload 0
      // 39f0: getfield CarMaker.dtab I
      // 39f3: bipush 4
      // 39f4: if_icmpne 4096
      // 39f7: aload 0
      // 39f8: getfield CarMaker.dtabed I
      // 39fb: aload 0
      // 39fc: getfield CarMaker.dtab I
      // 39ff: if_icmpeq 3c36
      // 3a02: aload 0
      // 3a03: bipush 0
      // 3a04: putfield CarMaker.changed2 Z
      // 3a07: aload 0
      // 3a08: bipush 0
      // 3a09: putfield CarMaker.statdef Z
      // 3a0c: new java/lang/StringBuilder
      // 3a0f: dup
      // 3a10: invokespecial java/lang/StringBuilder.<init> ()V
      // 3a13: ldc ""
      // 3a15: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a18: aload 0
      // 3a19: getfield CarMaker.editor Ljava/awt/TextArea;
      // 3a1c: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 3a1f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a22: ldc_w "\n"
      // 3a25: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a28: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3a2b: astore 6
      // 3a2d: bipush 0
      // 3a2e: istore 7
      // 3a30: aload 6
      // 3a32: ldc_w "\n"
      // 3a35: bipush 0
      // 3a36: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 3a39: istore 8
      // 3a3b: iload 8
      // 3a3d: bipush -1
      // 3a3e: if_icmpeq 3bfc
      // 3a41: iload 7
      // 3a43: aload 6
      // 3a45: invokevirtual java/lang/String.length ()I
      // 3a48: if_icmpge 3bfc
      // 3a4b: aload 6
      // 3a4d: iload 7
      // 3a4f: iload 8
      // 3a51: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 3a54: astore 9
      // 3a56: aload 9
      // 3a58: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 3a5b: astore 9
      // 3a5d: iload 8
      // 3a5f: bipush 1
      // 3a60: iadd
      // 3a61: istore 7
      // 3a63: aload 6
      // 3a65: ldc_w "\n"
      // 3a68: iload 7
      // 3a6a: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 3a6d: istore 8
      // 3a6f: aload 9
      // 3a71: ldc_w "stat("
      // 3a74: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 3a77: ifeq 3bef
      // 3a7a: bipush 0
      // 3a7b: istore 10
      // 3a7d: bipush 0
      // 3a7e: istore 11
      // 3a80: iload 11
      // 3a82: bipush 5
      // 3a83: if_icmpge 3ad6
      // 3a86: aload 0
      // 3a87: getfield CarMaker.stat [I
      // 3a8a: iload 11
      // 3a8c: aload 0
      // 3a8d: ldc_w "stat"
      // 3a90: aload 9
      // 3a92: iload 11
      // 3a94: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 3a97: iastore
      // 3a98: aload 0
      // 3a99: getfield CarMaker.stat [I
      // 3a9c: iload 11
      // 3a9e: iaload
      // 3a9f: sipush 200
      // 3aa2: if_icmple 3aaf
      // 3aa5: aload 0
      // 3aa6: getfield CarMaker.stat [I
      // 3aa9: iload 11
      // 3aab: sipush 200
      // 3aae: iastore
      // 3aaf: aload 0
      // 3ab0: getfield CarMaker.stat [I
      // 3ab3: iload 11
      // 3ab5: iaload
      // 3ab6: bipush 16
      // 3ab8: if_icmpge 3ac4
      // 3abb: aload 0
      // 3abc: getfield CarMaker.stat [I
      // 3abf: iload 11
      // 3ac1: bipush 16
      // 3ac3: iastore
      // 3ac4: iload 10
      // 3ac6: aload 0
      // 3ac7: getfield CarMaker.stat [I
      // 3aca: iload 11
      // 3acc: iaload
      // 3acd: iadd
      // 3ace: istore 10
      // 3ad0: iinc 11 1
      // 3ad3: goto 3a80
      // 3ad6: bipush 0
      // 3ad7: istore 11
      // 3ad9: iload 10
      // 3adb: sipush 680
      // 3ade: if_icmple 3aee
      // 3ae1: sipush 680
      // 3ae4: iload 10
      // 3ae6: isub
      // 3ae7: istore 11
      // 3ae9: aload 0
      // 3aea: bipush 1
      // 3aeb: putfield CarMaker.changed2 Z
      // 3aee: iload 10
      // 3af0: sipush 640
      // 3af3: if_icmple 3b0b
      // 3af6: iload 10
      // 3af8: sipush 680
      // 3afb: if_icmpge 3b0b
      // 3afe: sipush 640
      // 3b01: iload 10
      // 3b03: isub
      // 3b04: istore 11
      // 3b06: aload 0
      // 3b07: bipush 1
      // 3b08: putfield CarMaker.changed2 Z
      // 3b0b: iload 10
      // 3b0d: sipush 600
      // 3b10: if_icmple 3b28
      // 3b13: iload 10
      // 3b15: sipush 640
      // 3b18: if_icmpge 3b28
      // 3b1b: sipush 600
      // 3b1e: iload 10
      // 3b20: isub
      // 3b21: istore 11
      // 3b23: aload 0
      // 3b24: bipush 1
      // 3b25: putfield CarMaker.changed2 Z
      // 3b28: iload 10
      // 3b2a: sipush 560
      // 3b2d: if_icmple 3b45
      // 3b30: iload 10
      // 3b32: sipush 600
      // 3b35: if_icmpge 3b45
      // 3b38: sipush 560
      // 3b3b: iload 10
      // 3b3d: isub
      // 3b3e: istore 11
      // 3b40: aload 0
      // 3b41: bipush 1
      // 3b42: putfield CarMaker.changed2 Z
      // 3b45: iload 10
      // 3b47: sipush 520
      // 3b4a: if_icmple 3b62
      // 3b4d: iload 10
      // 3b4f: sipush 560
      // 3b52: if_icmpge 3b62
      // 3b55: sipush 520
      // 3b58: iload 10
      // 3b5a: isub
      // 3b5b: istore 11
      // 3b5d: aload 0
      // 3b5e: bipush 1
      // 3b5f: putfield CarMaker.changed2 Z
      // 3b62: iload 10
      // 3b64: sipush 520
      // 3b67: if_icmpge 3b77
      // 3b6a: sipush 520
      // 3b6d: iload 10
      // 3b6f: isub
      // 3b70: istore 11
      // 3b72: aload 0
      // 3b73: bipush 1
      // 3b74: putfield CarMaker.changed2 Z
      // 3b77: iload 11
      // 3b79: ifeq 3bcd
      // 3b7c: bipush 0
      // 3b7d: istore 12
      // 3b7f: iload 12
      // 3b81: bipush 5
      // 3b82: if_icmpge 3bca
      // 3b85: iload 11
      // 3b87: ifle 3ba5
      // 3b8a: aload 0
      // 3b8b: getfield CarMaker.stat [I
      // 3b8e: iload 12
      // 3b90: iaload
      // 3b91: sipush 200
      // 3b94: if_icmpge 3ba5
      // 3b97: aload 0
      // 3b98: getfield CarMaker.stat [I
      // 3b9b: iload 12
      // 3b9d: dup2
      // 3b9e: iaload
      // 3b9f: bipush 1
      // 3ba0: iadd
      // 3ba1: iastore
      // 3ba2: iinc 11 -1
      // 3ba5: iload 11
      // 3ba7: ifge 3bc4
      // 3baa: aload 0
      // 3bab: getfield CarMaker.stat [I
      // 3bae: iload 12
      // 3bb0: iaload
      // 3bb1: bipush 16
      // 3bb3: if_icmple 3bc4
      // 3bb6: aload 0
      // 3bb7: getfield CarMaker.stat [I
      // 3bba: iload 12
      // 3bbc: dup2
      // 3bbd: iaload
      // 3bbe: bipush 1
      // 3bbf: isub
      // 3bc0: iastore
      // 3bc1: iinc 11 1
      // 3bc4: iinc 12 1
      // 3bc7: goto 3b7f
      // 3bca: goto 3b77
      // 3bcd: bipush 0
      // 3bce: istore 12
      // 3bd0: iload 12
      // 3bd2: bipush 5
      // 3bd3: if_icmpge 3bea
      // 3bd6: aload 0
      // 3bd7: getfield CarMaker.rstat [I
      // 3bda: iload 12
      // 3bdc: aload 0
      // 3bdd: getfield CarMaker.stat [I
      // 3be0: iload 12
      // 3be2: iaload
      // 3be3: iastore
      // 3be4: iinc 12 1
      // 3be7: goto 3bd0
      // 3bea: aload 0
      // 3beb: bipush 1
      // 3bec: putfield CarMaker.statdef Z
      // 3bef: goto 3bf9
      // 3bf2: astore 10
      // 3bf4: aload 0
      // 3bf5: bipush 0
      // 3bf6: putfield CarMaker.statdef Z
      // 3bf9: goto 3a3b
      // 3bfc: aload 0
      // 3bfd: getfield CarMaker.statdef Z
      // 3c00: ifeq 3c20
      // 3c03: aload 0
      // 3c04: getfield CarMaker.simcar LSmenu;
      // 3c07: invokevirtual Smenu.getItemCount ()I
      // 3c0a: bipush 16
      // 3c0c: if_icmpne 3c36
      // 3c0f: aload 0
      // 3c10: getfield CarMaker.simcar LSmenu;
      // 3c13: aload 0
      // 3c14: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3c17: ldc_w "   "
      // 3c1a: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3c1d: goto 3c36
      // 3c20: aload 0
      // 3c21: getfield CarMaker.simcar LSmenu;
      // 3c24: invokevirtual Smenu.getItemCount ()I
      // 3c27: bipush 17
      // 3c29: if_icmpne 3c36
      // 3c2c: aload 0
      // 3c2d: getfield CarMaker.simcar LSmenu;
      // 3c30: ldc_w "   "
      // 3c33: invokevirtual Smenu.remove (Ljava/lang/String;)V
      // 3c36: aload 0
      // 3c37: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3c3a: new java/awt/Color
      // 3c3d: dup
      // 3c3e: bipush 0
      // 3c3f: bipush 0
      // 3c40: bipush 0
      // 3c41: invokespecial java/awt/Color.<init> (III)V
      // 3c44: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3c47: aload 0
      // 3c48: getfield CarMaker.statdef Z
      // 3c4b: ifne 3cba
      // 3c4e: aload 0
      // 3c4f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3c52: new java/awt/Font
      // 3c55: dup
      // 3c56: ldc_w "Arial"
      // 3c59: bipush 1
      // 3c5a: bipush 13
      // 3c5c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3c5f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3c62: aload 0
      // 3c63: aload 0
      // 3c64: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3c67: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 3c6a: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 3c6d: aload 0
      // 3c6e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3c71: ldc_w "To start, please select the most similar NFM car to this car"
      // 3c74: sipush 350
      // 3c77: aload 0
      // 3c78: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 3c7b: ldc_w "To start, please select the most similar NFM car to this car"
      // 3c7e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3c81: bipush 2
      // 3c82: idiv
      // 3c83: isub
      // 3c84: sipush 450
      // 3c87: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3c8a: aload 0
      // 3c8b: getfield CarMaker.simcar LSmenu;
      // 3c8e: sipush 288
      // 3c91: sipush 460
      // 3c94: invokevirtual Smenu.move (II)V
      // 3c97: aload 0
      // 3c98: getfield CarMaker.simcar LSmenu;
      // 3c9b: invokevirtual Smenu.isShowing ()Z
      // 3c9e: ifne 3ca8
      // 3ca1: aload 0
      // 3ca2: getfield CarMaker.simcar LSmenu;
      // 3ca5: invokevirtual Smenu.show ()V
      // 3ca8: aload 0
      // 3ca9: ldc_w "   OK   "
      // 3cac: sipush 350
      // 3caf: sipush 515
      // 3cb2: bipush 0
      // 3cb3: bipush 1
      // 3cb4: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 3cb7: goto 4096
      // 3cba: aload 0
      // 3cbb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3cbe: ldc_w "Car Class"
      // 3cc1: bipush 54
      // 3cc3: sipush 435
      // 3cc6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3cc9: aload 0
      // 3cca: getfield CarMaker.cls LSmenu;
      // 3ccd: bipush 34
      // 3ccf: sipush 440
      // 3cd2: invokevirtual Smenu.move (II)V
      // 3cd5: aload 0
      // 3cd6: getfield CarMaker.cls LSmenu;
      // 3cd9: invokevirtual Smenu.isShowing ()Z
      // 3cdc: ifne 3ce6
      // 3cdf: aload 0
      // 3ce0: getfield CarMaker.cls LSmenu;
      // 3ce3: invokevirtual Smenu.show ()V
      // 3ce6: bipush 0
      // 3ce7: istore 6
      // 3ce9: bipush 0
      // 3cea: istore 7
      // 3cec: bipush 0
      // 3ced: istore 8
      // 3cef: iload 8
      // 3cf1: bipush 5
      // 3cf2: if_icmpge 3d1b
      // 3cf5: iload 7
      // 3cf7: aload 0
      // 3cf8: getfield CarMaker.stat [I
      // 3cfb: iload 8
      // 3cfd: iaload
      // 3cfe: iadd
      // 3cff: istore 7
      // 3d01: aload 0
      // 3d02: getfield CarMaker.stat [I
      // 3d05: iload 8
      // 3d07: iaload
      // 3d08: aload 0
      // 3d09: getfield CarMaker.rstat [I
      // 3d0c: iload 8
      // 3d0e: iaload
      // 3d0f: if_icmpeq 3d15
      // 3d12: bipush 1
      // 3d13: istore 6
      // 3d15: iinc 8 1
      // 3d18: goto 3cef
      // 3d1b: aload 0
      // 3d1c: getfield CarMaker.clsel I
      // 3d1f: aload 0
      // 3d20: getfield CarMaker.cls LSmenu;
      // 3d23: invokevirtual Smenu.getSelectedIndex ()I
      // 3d26: if_icmpeq 3d9c
      // 3d29: aload 0
      // 3d2a: aload 0
      // 3d2b: getfield CarMaker.cls LSmenu;
      // 3d2e: invokevirtual Smenu.getSelectedIndex ()I
      // 3d31: putfield CarMaker.clsel I
      // 3d34: bipush 4
      // 3d35: aload 0
      // 3d36: getfield CarMaker.clsel I
      // 3d39: isub
      // 3d3a: bipush 40
      // 3d3c: imul
      // 3d3d: sipush 520
      // 3d40: iadd
      // 3d41: iload 7
      // 3d43: isub
      // 3d44: istore 8
      // 3d46: iload 8
      // 3d48: ifeq 3d9c
      // 3d4b: bipush 0
      // 3d4c: istore 9
      // 3d4e: iload 9
      // 3d50: bipush 5
      // 3d51: if_icmpge 3d99
      // 3d54: iload 8
      // 3d56: ifle 3d74
      // 3d59: aload 0
      // 3d5a: getfield CarMaker.stat [I
      // 3d5d: iload 9
      // 3d5f: iaload
      // 3d60: sipush 200
      // 3d63: if_icmpge 3d74
      // 3d66: aload 0
      // 3d67: getfield CarMaker.stat [I
      // 3d6a: iload 9
      // 3d6c: dup2
      // 3d6d: iaload
      // 3d6e: bipush 1
      // 3d6f: iadd
      // 3d70: iastore
      // 3d71: iinc 8 -1
      // 3d74: iload 8
      // 3d76: ifge 3d93
      // 3d79: aload 0
      // 3d7a: getfield CarMaker.stat [I
      // 3d7d: iload 9
      // 3d7f: iaload
      // 3d80: bipush 16
      // 3d82: if_icmple 3d93
      // 3d85: aload 0
      // 3d86: getfield CarMaker.stat [I
      // 3d89: iload 9
      // 3d8b: dup2
      // 3d8c: iaload
      // 3d8d: bipush 1
      // 3d8e: isub
      // 3d8f: iastore
      // 3d90: iinc 8 1
      // 3d93: iinc 9 1
      // 3d96: goto 3d4e
      // 3d99: goto 3d46
      // 3d9c: bipush 4
      // 3d9d: iload 7
      // 3d9f: sipush 520
      // 3da2: isub
      // 3da3: bipush 40
      // 3da5: idiv
      // 3da6: isub
      // 3da7: aload 0
      // 3da8: getfield CarMaker.cls LSmenu;
      // 3dab: invokevirtual Smenu.getSelectedIndex ()I
      // 3dae: if_icmpeq 3dcb
      // 3db1: aload 0
      // 3db2: bipush 4
      // 3db3: iload 7
      // 3db5: sipush 520
      // 3db8: isub
      // 3db9: bipush 40
      // 3dbb: idiv
      // 3dbc: isub
      // 3dbd: putfield CarMaker.clsel I
      // 3dc0: aload 0
      // 3dc1: getfield CarMaker.cls LSmenu;
      // 3dc4: aload 0
      // 3dc5: getfield CarMaker.clsel I
      // 3dc8: invokevirtual Smenu.select (I)V
      // 3dcb: aload 0
      // 3dcc: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3dcf: ldc_w "Most Similar Car"
      // 3dd2: bipush 36
      // 3dd4: sipush 490
      // 3dd7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3dda: aload 0
      // 3ddb: getfield CarMaker.simcar LSmenu;
      // 3dde: bipush 20
      // 3de0: sipush 495
      // 3de3: invokevirtual Smenu.move (II)V
      // 3de6: aload 0
      // 3de7: getfield CarMaker.simcar LSmenu;
      // 3dea: invokevirtual Smenu.isShowing ()Z
      // 3ded: ifne 3df7
      // 3df0: aload 0
      // 3df1: getfield CarMaker.simcar LSmenu;
      // 3df4: invokevirtual Smenu.show ()V
      // 3df7: aload 0
      // 3df8: getfield CarMaker.carsel I
      // 3dfb: aload 0
      // 3dfc: getfield CarMaker.simcar LSmenu;
      // 3dff: invokevirtual Smenu.getSelectedIndex ()I
      // 3e02: if_icmpeq 3e3f
      // 3e05: aload 0
      // 3e06: aload 0
      // 3e07: getfield CarMaker.simcar LSmenu;
      // 3e0a: invokevirtual Smenu.getSelectedIndex ()I
      // 3e0d: putfield CarMaker.carsel I
      // 3e10: aload 0
      // 3e11: getfield CarMaker.carsel I
      // 3e14: bipush 16
      // 3e16: if_icmpeq 3e3b
      // 3e19: bipush 0
      // 3e1a: istore 8
      // 3e1c: iload 8
      // 3e1e: bipush 5
      // 3e1f: if_icmpge 3e3b
      // 3e22: aload 0
      // 3e23: getfield CarMaker.stat [I
      // 3e26: iload 8
      // 3e28: aload 0
      // 3e29: getfield CarMaker.carstat [[I
      // 3e2c: aload 0
      // 3e2d: getfield CarMaker.carsel I
      // 3e30: aaload
      // 3e31: iload 8
      // 3e33: iaload
      // 3e34: iastore
      // 3e35: iinc 8 1
      // 3e38: goto 3e1c
      // 3e3b: aload 0
      // 3e3c: invokevirtual CarMaker.requestFocus ()V
      // 3e3f: bipush 60
      // 3e41: istore 8
      // 3e43: bipush 16
      // 3e45: istore 9
      // 3e47: bipush 0
      // 3e48: istore 10
      // 3e4a: iload 10
      // 3e4c: bipush 16
      // 3e4e: if_icmpge 3e92
      // 3e51: bipush 0
      // 3e52: istore 11
      // 3e54: bipush 0
      // 3e55: istore 12
      // 3e57: iload 12
      // 3e59: bipush 5
      // 3e5a: if_icmpge 3e7d
      // 3e5d: iload 11
      // 3e5f: aload 0
      // 3e60: getfield CarMaker.carstat [[I
      // 3e63: iload 10
      // 3e65: aaload
      // 3e66: iload 12
      // 3e68: iaload
      // 3e69: aload 0
      // 3e6a: getfield CarMaker.stat [I
      // 3e6d: iload 12
      // 3e6f: iaload
      // 3e70: isub
      // 3e71: invokestatic java/lang/Math.abs (I)I
      // 3e74: iadd
      // 3e75: istore 11
      // 3e77: iinc 12 1
      // 3e7a: goto 3e57
      // 3e7d: iload 11
      // 3e7f: iload 8
      // 3e81: if_icmpge 3e8c
      // 3e84: iload 10
      // 3e86: istore 9
      // 3e88: iload 11
      // 3e8a: istore 8
      // 3e8c: iinc 10 1
      // 3e8f: goto 3e4a
      // 3e92: iload 9
      // 3e94: aload 0
      // 3e95: getfield CarMaker.carsel I
      // 3e98: if_icmpeq 3eba
      // 3e9b: aload 0
      // 3e9c: iload 9
      // 3e9e: putfield CarMaker.carsel I
      // 3ea1: aload 0
      // 3ea2: getfield CarMaker.carsel I
      // 3ea5: aload 0
      // 3ea6: getfield CarMaker.simcar LSmenu;
      // 3ea9: invokevirtual Smenu.getItemCount ()I
      // 3eac: if_icmpge 3eba
      // 3eaf: aload 0
      // 3eb0: getfield CarMaker.simcar LSmenu;
      // 3eb3: aload 0
      // 3eb4: getfield CarMaker.carsel I
      // 3eb7: invokevirtual Smenu.select (I)V
      // 3eba: aload 0
      // 3ebb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3ebe: ldc_w "Speed :"
      // 3ec1: sipush 196
      // 3ec4: sipush 435
      // 3ec7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3eca: aload 0
      // 3ecb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3ece: ldc_w "Acceleration :"
      // 3ed1: sipush 160
      // 3ed4: sipush 459
      // 3ed7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3eda: aload 0
      // 3edb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3ede: ldc_w "Stunts :"
      // 3ee1: sipush 195
      // 3ee4: sipush 483
      // 3ee7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3eea: aload 0
      // 3eeb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3eee: ldc_w "Strength :"
      // 3ef1: sipush 183
      // 3ef4: sipush 507
      // 3ef7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3efa: aload 0
      // 3efb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3efe: ldc_w "Endurance :"
      // 3f01: sipush 171
      // 3f04: sipush 531
      // 3f07: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3f0a: bipush 0
      // 3f0b: istore 10
      // 3f0d: iload 10
      // 3f0f: bipush 5
      // 3f10: if_icmpge 403b
      // 3f13: bipush 0
      // 3f14: istore 11
      // 3f16: iload 11
      // 3f18: aload 0
      // 3f19: getfield CarMaker.stat [I
      // 3f1c: iload 10
      // 3f1e: iaload
      // 3f1f: if_icmpge 3f62
      // 3f22: aload 0
      // 3f23: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3f26: iload 11
      // 3f28: i2f
      // 3f29: f2d
      // 3f2a: ldc2_w 7.0E-4
      // 3f2d: dmul
      // 3f2e: d2f
      // 3f2f: fconst_1
      // 3f30: fconst_1
      // 3f31: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 3f34: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3f37: aload 0
      // 3f38: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3f3b: sipush 250
      // 3f3e: iload 11
      // 3f40: iadd
      // 3f41: sipush 426
      // 3f44: iload 10
      // 3f46: bipush 24
      // 3f48: imul
      // 3f49: iadd
      // 3f4a: sipush 250
      // 3f4d: iload 11
      // 3f4f: iadd
      // 3f50: sipush 434
      // 3f53: iload 10
      // 3f55: bipush 24
      // 3f57: imul
      // 3f58: iadd
      // 3f59: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3f5c: iinc 11 1
      // 3f5f: goto 3f16
      // 3f62: aload 0
      // 3f63: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3f66: new java/awt/Color
      // 3f69: dup
      // 3f6a: bipush 0
      // 3f6b: bipush 0
      // 3f6c: bipush 0
      // 3f6d: invokespecial java/awt/Color.<init> (III)V
      // 3f70: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3f73: aload 0
      // 3f74: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3f77: sipush 249
      // 3f7a: sipush 426
      // 3f7d: iload 10
      // 3f7f: bipush 24
      // 3f81: imul
      // 3f82: iadd
      // 3f83: sipush 249
      // 3f86: sipush 434
      // 3f89: iload 10
      // 3f8b: bipush 24
      // 3f8d: imul
      // 3f8e: iadd
      // 3f8f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3f92: aload 0
      // 3f93: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3f96: sipush 450
      // 3f99: sipush 426
      // 3f9c: iload 10
      // 3f9e: bipush 24
      // 3fa0: imul
      // 3fa1: iadd
      // 3fa2: sipush 450
      // 3fa5: sipush 434
      // 3fa8: iload 10
      // 3faa: bipush 24
      // 3fac: imul
      // 3fad: iadd
      // 3fae: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3fb1: aload 0
      // 3fb2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3fb5: sipush 249
      // 3fb8: sipush 435
      // 3fbb: iload 10
      // 3fbd: bipush 24
      // 3fbf: imul
      // 3fc0: iadd
      // 3fc1: sipush 450
      // 3fc4: sipush 435
      // 3fc7: iload 10
      // 3fc9: bipush 24
      // 3fcb: imul
      // 3fcc: iadd
      // 3fcd: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3fd0: bipush 0
      // 3fd1: istore 11
      // 3fd3: iload 11
      // 3fd5: bipush 7
      // 3fd7: if_icmpge 400b
      // 3fda: aload 0
      // 3fdb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 3fde: sipush 275
      // 3fe1: iload 11
      // 3fe3: bipush 25
      // 3fe5: imul
      // 3fe6: iadd
      // 3fe7: sipush 434
      // 3fea: iload 10
      // 3fec: bipush 24
      // 3fee: imul
      // 3fef: iadd
      // 3ff0: sipush 275
      // 3ff3: iload 11
      // 3ff5: bipush 25
      // 3ff7: imul
      // 3ff8: iadd
      // 3ff9: sipush 430
      // 3ffc: iload 10
      // 3ffe: bipush 24
      // 4000: imul
      // 4001: iadd
      // 4002: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4005: iinc 11 1
      // 4008: goto 3fd3
      // 400b: aload 0
      // 400c: ldc_w " - "
      // 400f: sipush 480
      // 4012: sipush 435
      // 4015: iload 10
      // 4017: bipush 24
      // 4019: imul
      // 401a: iadd
      // 401b: bipush 4
      // 401c: bipush 0
      // 401d: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4020: aload 0
      // 4021: ldc_w " + "
      // 4024: sipush 520
      // 4027: sipush 435
      // 402a: iload 10
      // 402c: bipush 24
      // 402e: imul
      // 402f: iadd
      // 4030: bipush 4
      // 4031: bipush 0
      // 4032: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4035: iinc 10 1
      // 4038: goto 3f0d
      // 403b: aload 0
      // 403c: getfield CarMaker.carsel I
      // 403f: bipush 16
      // 4041: if_icmpge 405a
      // 4044: iload 8
      // 4046: ifeq 405a
      // 4049: aload 0
      // 404a: ldc_w " Reselect "
      // 404d: bipush 80
      // 404f: sipush 534
      // 4052: bipush 4
      // 4053: bipush 1
      // 4054: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4057: goto 4068
      // 405a: aload 0
      // 405b: ldc_w " Reselect "
      // 405e: bipush 80
      // 4060: sipush -1000
      // 4063: bipush 4
      // 4064: bipush 1
      // 4065: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4068: aload 0
      // 4069: ldc_w "      Save      "
      // 406c: sipush 620
      // 406f: sipush 459
      // 4072: bipush 0
      // 4073: iload 6
      // 4075: ifne 407f
      // 4078: aload 0
      // 4079: getfield CarMaker.changed2 Z
      // 407c: ifeq 4083
      // 407f: bipush 1
      // 4080: goto 4084
      // 4083: bipush 0
      // 4084: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4087: aload 0
      // 4088: ldc_w "   Reset   "
      // 408b: sipush 620
      // 408e: sipush 507
      // 4091: bipush 0
      // 4092: bipush 0
      // 4093: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4096: aload 0
      // 4097: getfield CarMaker.dtab I
      // 409a: bipush 5
      // 409b: if_icmpne 57ba
      // 409e: aload 0
      // 409f: getfield CarMaker.dtabed I
      // 40a2: aload 0
      // 40a3: getfield CarMaker.dtab I
      // 40a6: if_icmpeq 42cb
      // 40a9: aload 0
      // 40aa: bipush -1
      // 40ab: putfield CarMaker.mouseon I
      // 40ae: aload 0
      // 40af: bipush 0
      // 40b0: putfield CarMaker.pfase I
      // 40b3: aload 0
      // 40b4: getfield CarMaker.o LContO;
      // 40b7: getfield ContO.keyz [I
      // 40ba: bipush 0
      // 40bb: iaload
      // 40bc: ifle 40cb
      // 40bf: aload 0
      // 40c0: getfield CarMaker.o LContO;
      // 40c3: getfield ContO.keyx [I
      // 40c6: bipush 0
      // 40c7: iaload
      // 40c8: iflt 40d0
      // 40cb: aload 0
      // 40cc: bipush -1
      // 40cd: putfield CarMaker.pfase I
      // 40d0: aload 0
      // 40d1: getfield CarMaker.o LContO;
      // 40d4: getfield ContO.keyz [I
      // 40d7: bipush 1
      // 40d8: iaload
      // 40d9: ifle 40e8
      // 40dc: aload 0
      // 40dd: getfield CarMaker.o LContO;
      // 40e0: getfield ContO.keyx [I
      // 40e3: bipush 1
      // 40e4: iaload
      // 40e5: ifgt 40ed
      // 40e8: aload 0
      // 40e9: bipush -1
      // 40ea: putfield CarMaker.pfase I
      // 40ed: aload 0
      // 40ee: getfield CarMaker.o LContO;
      // 40f1: getfield ContO.keyz [I
      // 40f4: bipush 2
      // 40f5: iaload
      // 40f6: ifge 4105
      // 40f9: aload 0
      // 40fa: getfield CarMaker.o LContO;
      // 40fd: getfield ContO.keyx [I
      // 4100: bipush 2
      // 4101: iaload
      // 4102: iflt 410a
      // 4105: aload 0
      // 4106: bipush -1
      // 4107: putfield CarMaker.pfase I
      // 410a: aload 0
      // 410b: getfield CarMaker.o LContO;
      // 410e: getfield ContO.keyz [I
      // 4111: bipush 3
      // 4112: iaload
      // 4113: ifge 4122
      // 4116: aload 0
      // 4117: getfield CarMaker.o LContO;
      // 411a: getfield ContO.keyx [I
      // 411d: bipush 3
      // 411e: iaload
      // 411f: ifgt 4127
      // 4122: aload 0
      // 4123: bipush -1
      // 4124: putfield CarMaker.pfase I
      // 4127: aload 0
      // 4128: bipush 0
      // 4129: putfield CarMaker.crashok Z
      // 412c: invokestatic java/lang/Math.random ()D
      // 412f: invokestatic java/lang/Math.random ()D
      // 4132: dcmpl
      // 4133: ifle 413e
      // 4136: aload 0
      // 4137: bipush 0
      // 4138: putfield CarMaker.crashleft Z
      // 413b: goto 4143
      // 413e: aload 0
      // 413f: bipush 1
      // 4140: putfield CarMaker.crashleft Z
      // 4143: aload 0
      // 4144: bipush 0
      // 4145: putfield CarMaker.engsel I
      // 4148: aload 0
      // 4149: getfield CarMaker.pfase I
      // 414c: ifne 42c6
      // 414f: new java/lang/StringBuilder
      // 4152: dup
      // 4153: invokespecial java/lang/StringBuilder.<init> ()V
      // 4156: ldc ""
      // 4158: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 415b: aload 0
      // 415c: getfield CarMaker.editor Ljava/awt/TextArea;
      // 415f: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 4162: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4165: ldc_w "\n"
      // 4168: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 416b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 416e: astore 6
      // 4170: bipush 0
      // 4171: istore 7
      // 4173: aload 6
      // 4175: ldc_w "\n"
      // 4178: bipush 0
      // 4179: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 417c: istore 8
      // 417e: iload 8
      // 4180: bipush -1
      // 4181: if_icmpeq 42c6
      // 4184: iload 7
      // 4186: aload 6
      // 4188: invokevirtual java/lang/String.length ()I
      // 418b: if_icmpge 42c6
      // 418e: aload 6
      // 4190: iload 7
      // 4192: iload 8
      // 4194: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 4197: astore 9
      // 4199: aload 9
      // 419b: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 419e: astore 9
      // 41a0: iload 8
      // 41a2: bipush 1
      // 41a3: iadd
      // 41a4: istore 7
      // 41a6: aload 6
      // 41a8: ldc_w "\n"
      // 41ab: iload 7
      // 41ad: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 41b0: istore 8
      // 41b2: aload 9
      // 41b4: ldc_w "physics("
      // 41b7: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 41ba: ifeq 42b9
      // 41bd: bipush 0
      // 41be: istore 10
      // 41c0: iload 10
      // 41c2: bipush 11
      // 41c4: if_icmpge 4206
      // 41c7: aload 0
      // 41c8: getfield CarMaker.phys [I
      // 41cb: iload 10
      // 41cd: aload 0
      // 41ce: ldc_w "physics"
      // 41d1: aload 9
      // 41d3: iload 10
      // 41d5: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 41d8: iastore
      // 41d9: aload 0
      // 41da: getfield CarMaker.phys [I
      // 41dd: iload 10
      // 41df: iaload
      // 41e0: bipush 100
      // 41e2: if_icmple 41ee
      // 41e5: aload 0
      // 41e6: getfield CarMaker.phys [I
      // 41e9: iload 10
      // 41eb: bipush 100
      // 41ed: iastore
      // 41ee: aload 0
      // 41ef: getfield CarMaker.phys [I
      // 41f2: iload 10
      // 41f4: iaload
      // 41f5: ifge 4200
      // 41f8: aload 0
      // 41f9: getfield CarMaker.phys [I
      // 41fc: iload 10
      // 41fe: bipush 0
      // 41ff: iastore
      // 4200: iinc 10 1
      // 4203: goto 41c0
      // 4206: bipush 0
      // 4207: istore 10
      // 4209: iload 10
      // 420b: bipush 11
      // 420d: if_icmpge 4224
      // 4210: aload 0
      // 4211: getfield CarMaker.rphys [I
      // 4214: iload 10
      // 4216: aload 0
      // 4217: getfield CarMaker.phys [I
      // 421a: iload 10
      // 421c: iaload
      // 421d: iastore
      // 421e: iinc 10 1
      // 4221: goto 4209
      // 4224: bipush 0
      // 4225: istore 10
      // 4227: iload 10
      // 4229: bipush 3
      // 422a: if_icmpge 426f
      // 422d: aload 0
      // 422e: getfield CarMaker.crash [I
      // 4231: iload 10
      // 4233: aload 0
      // 4234: ldc_w "physics"
      // 4237: aload 9
      // 4239: iload 10
      // 423b: bipush 11
      // 423d: iadd
      // 423e: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 4241: iastore
      // 4242: aload 0
      // 4243: getfield CarMaker.crash [I
      // 4246: iload 10
      // 4248: iaload
      // 4249: bipush 100
      // 424b: if_icmple 4257
      // 424e: aload 0
      // 424f: getfield CarMaker.crash [I
      // 4252: iload 10
      // 4254: bipush 100
      // 4256: iastore
      // 4257: aload 0
      // 4258: getfield CarMaker.crash [I
      // 425b: iload 10
      // 425d: iaload
      // 425e: ifge 4269
      // 4261: aload 0
      // 4262: getfield CarMaker.crash [I
      // 4265: iload 10
      // 4267: bipush 0
      // 4268: iastore
      // 4269: iinc 10 1
      // 426c: goto 4227
      // 426f: bipush 0
      // 4270: istore 10
      // 4272: iload 10
      // 4274: bipush 3
      // 4275: if_icmpge 428c
      // 4278: aload 0
      // 4279: getfield CarMaker.rcrash [I
      // 427c: iload 10
      // 427e: aload 0
      // 427f: getfield CarMaker.crash [I
      // 4282: iload 10
      // 4284: iaload
      // 4285: iastore
      // 4286: iinc 10 1
      // 4289: goto 4272
      // 428c: aload 0
      // 428d: aload 0
      // 428e: ldc_w "physics"
      // 4291: aload 9
      // 4293: bipush 14
      // 4295: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 4298: putfield CarMaker.engsel I
      // 429b: aload 0
      // 429c: getfield CarMaker.engsel I
      // 429f: bipush 4
      // 42a0: if_icmple 42a8
      // 42a3: aload 0
      // 42a4: bipush 0
      // 42a5: putfield CarMaker.engsel I
      // 42a8: aload 0
      // 42a9: getfield CarMaker.engsel I
      // 42ac: ifge 42b4
      // 42af: aload 0
      // 42b0: bipush 0
      // 42b1: putfield CarMaker.engsel I
      // 42b4: aload 0
      // 42b5: bipush 1
      // 42b6: putfield CarMaker.crashok Z
      // 42b9: goto 42c3
      // 42bc: astore 10
      // 42be: aload 0
      // 42bf: bipush 0
      // 42c0: putfield CarMaker.crashok Z
      // 42c3: goto 417e
      // 42c6: aload 0
      // 42c7: bipush 0
      // 42c8: putfield CarMaker.engon Z
      // 42cb: bipush -1
      // 42cc: istore 6
      // 42ce: aload 0
      // 42cf: getfield CarMaker.pfase I
      // 42d2: ifne 4500
      // 42d5: bipush 0
      // 42d6: istore 7
      // 42d8: iload 7
      // 42da: bipush 4
      // 42db: if_icmpge 44b2
      // 42de: aload 0
      // 42df: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 42e2: new java/awt/Color
      // 42e5: dup
      // 42e6: bipush 0
      // 42e7: bipush 0
      // 42e8: bipush 0
      // 42e9: invokespecial java/awt/Color.<init> (III)V
      // 42ec: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 42ef: aload 0
      // 42f0: getfield CarMaker.xm I
      // 42f3: aload 0
      // 42f4: getfield CarMaker.pnx [I
      // 42f7: iload 7
      // 42f9: iaload
      // 42fa: if_icmple 4362
      // 42fd: aload 0
      // 42fe: getfield CarMaker.xm I
      // 4301: sipush 230
      // 4304: if_icmpge 4362
      // 4307: aload 0
      // 4308: getfield CarMaker.ym I
      // 430b: sipush 433
      // 430e: iload 7
      // 4310: bipush 24
      // 4312: imul
      // 4313: iadd
      // 4314: if_icmple 4362
      // 4317: aload 0
      // 4318: getfield CarMaker.ym I
      // 431b: sipush 453
      // 431e: iload 7
      // 4320: bipush 24
      // 4322: imul
      // 4323: iadd
      // 4324: if_icmpge 4362
      // 4327: iload 7
      // 4329: istore 6
      // 432b: aload 0
      // 432c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 432f: new java/awt/Color
      // 4332: dup
      // 4333: sipush 176
      // 4336: bipush 64
      // 4338: bipush 0
      // 4339: invokespecial java/awt/Color.<init> (III)V
      // 433c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 433f: aload 0
      // 4340: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4343: aload 0
      // 4344: getfield CarMaker.pnx [I
      // 4347: iload 7
      // 4349: iaload
      // 434a: sipush 448
      // 434d: iload 7
      // 434f: bipush 24
      // 4351: imul
      // 4352: iadd
      // 4353: sipush 128
      // 4356: sipush 448
      // 4359: iload 7
      // 435b: bipush 24
      // 435d: imul
      // 435e: iadd
      // 435f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4362: aload 0
      // 4363: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4366: new java/lang/StringBuilder
      // 4369: dup
      // 436a: invokespecial java/lang/StringBuilder.<init> ()V
      // 436d: ldc ""
      // 436f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4372: aload 0
      // 4373: getfield CarMaker.pname [Ljava/lang/String;
      // 4376: iload 7
      // 4378: aaload
      // 4379: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 437c: ldc_w " :"
      // 437f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4382: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 4385: aload 0
      // 4386: getfield CarMaker.pnx [I
      // 4389: iload 7
      // 438b: iaload
      // 438c: sipush 447
      // 438f: iload 7
      // 4391: bipush 24
      // 4393: imul
      // 4394: iadd
      // 4395: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4398: aload 0
      // 4399: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 439c: sipush 140
      // 439f: sipush 443
      // 43a2: iload 7
      // 43a4: bipush 24
      // 43a6: imul
      // 43a7: iadd
      // 43a8: sipush 230
      // 43ab: sipush 443
      // 43ae: iload 7
      // 43b0: bipush 24
      // 43b2: imul
      // 43b3: iadd
      // 43b4: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 43b7: bipush 1
      // 43b8: istore 8
      // 43ba: iload 8
      // 43bc: bipush 10
      // 43be: if_icmpge 43f8
      // 43c1: aload 0
      // 43c2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 43c5: sipush 140
      // 43c8: bipush 10
      // 43ca: iload 8
      // 43cc: imul
      // 43cd: iadd
      // 43ce: sipush 443
      // 43d1: iload 8
      // 43d3: isub
      // 43d4: iload 7
      // 43d6: bipush 24
      // 43d8: imul
      // 43d9: iadd
      // 43da: sipush 140
      // 43dd: bipush 10
      // 43df: iload 8
      // 43e1: imul
      // 43e2: iadd
      // 43e3: sipush 443
      // 43e6: iload 8
      // 43e8: iadd
      // 43e9: iload 7
      // 43eb: bipush 24
      // 43ed: imul
      // 43ee: iadd
      // 43ef: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 43f2: iinc 8 1
      // 43f5: goto 43ba
      // 43f8: aload 0
      // 43f9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 43fc: new java/awt/Color
      // 43ff: dup
      // 4400: sipush 255
      // 4403: bipush 0
      // 4404: bipush 0
      // 4405: invokespecial java/awt/Color.<init> (III)V
      // 4408: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 440b: aload 0
      // 440c: getfield CarMaker.phys [I
      // 440f: iload 7
      // 4411: iaload
      // 4412: i2f
      // 4413: ldc_w 1.1111
      // 4416: fdiv
      // 4417: ldc_w 10.0
      // 441a: fdiv
      // 441b: f2i
      // 441c: istore 8
      // 441e: aload 0
      // 441f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4422: sipush 138
      // 4425: aload 0
      // 4426: getfield CarMaker.phys [I
      // 4429: iload 7
      // 442b: iaload
      // 442c: i2f
      // 442d: ldc_w 1.1111
      // 4430: fdiv
      // 4431: f2i
      // 4432: iadd
      // 4433: sipush 443
      // 4436: iload 8
      // 4438: isub
      // 4439: iload 7
      // 443b: bipush 24
      // 443d: imul
      // 443e: iadd
      // 443f: bipush 5
      // 4440: iload 8
      // 4442: bipush 2
      // 4443: imul
      // 4444: bipush 1
      // 4445: iadd
      // 4446: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4449: aload 0
      // 444a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 444d: new java/awt/Color
      // 4450: dup
      // 4451: sipush 255
      // 4454: sipush 128
      // 4457: bipush 0
      // 4458: invokespecial java/awt/Color.<init> (III)V
      // 445b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 445e: aload 0
      // 445f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4462: sipush 139
      // 4465: aload 0
      // 4466: getfield CarMaker.phys [I
      // 4469: iload 7
      // 446b: iaload
      // 446c: i2f
      // 446d: ldc_w 1.1111
      // 4470: fdiv
      // 4471: f2i
      // 4472: iadd
      // 4473: sipush 434
      // 4476: iload 7
      // 4478: bipush 24
      // 447a: imul
      // 447b: iadd
      // 447c: bipush 2
      // 447d: bipush 18
      // 447f: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4482: aload 0
      // 4483: ldc_w " - "
      // 4486: sipush 260
      // 4489: sipush 447
      // 448c: iload 7
      // 448e: bipush 24
      // 4490: imul
      // 4491: iadd
      // 4492: bipush 4
      // 4493: bipush 0
      // 4494: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4497: aload 0
      // 4498: ldc_w " + "
      // 449b: sipush 300
      // 449e: sipush 447
      // 44a1: iload 7
      // 44a3: bipush 24
      // 44a5: imul
      // 44a6: iadd
      // 44a7: bipush 4
      // 44a8: bipush 0
      // 44a9: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 44ac: iinc 7 1
      // 44af: goto 42d8
      // 44b2: aload 0
      // 44b3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 44b6: new java/awt/Color
      // 44b9: dup
      // 44ba: bipush 0
      // 44bb: bipush 0
      // 44bc: bipush 0
      // 44bd: invokespecial java/awt/Color.<init> (III)V
      // 44c0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 44c3: aload 0
      // 44c4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 44c7: ldc_w "<  Click on any variable name to learn about its function & use!"
      // 44ca: sipush 333
      // 44cd: sipush 447
      // 44d0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 44d3: aload 0
      // 44d4: ldc_w " Random "
      // 44d7: sipush 380
      // 44da: sipush 496
      // 44dd: bipush 0
      // 44de: bipush 0
      // 44df: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 44e2: aload 0
      // 44e3: ldc_w " Reset "
      // 44e6: sipush 455
      // 44e9: sipush 496
      // 44ec: bipush 0
      // 44ed: bipush 0
      // 44ee: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 44f1: aload 0
      // 44f2: ldc_w "       Next  >       "
      // 44f5: sipush 570
      // 44f8: sipush 496
      // 44fb: bipush 0
      // 44fc: bipush 1
      // 44fd: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4500: aload 0
      // 4501: getfield CarMaker.pfase I
      // 4504: bipush 1
      // 4505: if_icmpne 4920
      // 4508: bipush 0
      // 4509: istore 7
      // 450b: iload 7
      // 450d: bipush 4
      // 450e: if_icmpge 46ef
      // 4511: aload 0
      // 4512: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4515: new java/awt/Color
      // 4518: dup
      // 4519: bipush 0
      // 451a: bipush 0
      // 451b: bipush 0
      // 451c: invokespecial java/awt/Color.<init> (III)V
      // 451f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4522: aload 0
      // 4523: getfield CarMaker.xm I
      // 4526: aload 0
      // 4527: getfield CarMaker.pnx [I
      // 452a: iload 7
      // 452c: bipush 5
      // 452d: iadd
      // 452e: iaload
      // 452f: if_icmple 459a
      // 4532: aload 0
      // 4533: getfield CarMaker.xm I
      // 4536: sipush 211
      // 4539: if_icmpge 459a
      // 453c: aload 0
      // 453d: getfield CarMaker.ym I
      // 4540: sipush 433
      // 4543: iload 7
      // 4545: bipush 24
      // 4547: imul
      // 4548: iadd
      // 4549: if_icmple 459a
      // 454c: aload 0
      // 454d: getfield CarMaker.ym I
      // 4550: sipush 453
      // 4553: iload 7
      // 4555: bipush 24
      // 4557: imul
      // 4558: iadd
      // 4559: if_icmpge 459a
      // 455c: iload 7
      // 455e: bipush 5
      // 455f: iadd
      // 4560: istore 6
      // 4562: aload 0
      // 4563: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4566: new java/awt/Color
      // 4569: dup
      // 456a: sipush 176
      // 456d: bipush 64
      // 456f: bipush 0
      // 4570: invokespecial java/awt/Color.<init> (III)V
      // 4573: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4576: aload 0
      // 4577: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 457a: aload 0
      // 457b: getfield CarMaker.pnx [I
      // 457e: iload 7
      // 4580: bipush 5
      // 4581: iadd
      // 4582: iaload
      // 4583: sipush 448
      // 4586: iload 7
      // 4588: bipush 24
      // 458a: imul
      // 458b: iadd
      // 458c: bipush 109
      // 458e: sipush 448
      // 4591: iload 7
      // 4593: bipush 24
      // 4595: imul
      // 4596: iadd
      // 4597: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 459a: aload 0
      // 459b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 459e: new java/lang/StringBuilder
      // 45a1: dup
      // 45a2: invokespecial java/lang/StringBuilder.<init> ()V
      // 45a5: ldc ""
      // 45a7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 45aa: aload 0
      // 45ab: getfield CarMaker.pname [Ljava/lang/String;
      // 45ae: iload 7
      // 45b0: bipush 5
      // 45b1: iadd
      // 45b2: aaload
      // 45b3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 45b6: ldc_w " :"
      // 45b9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 45bc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 45bf: aload 0
      // 45c0: getfield CarMaker.pnx [I
      // 45c3: iload 7
      // 45c5: bipush 5
      // 45c6: iadd
      // 45c7: iaload
      // 45c8: sipush 447
      // 45cb: iload 7
      // 45cd: bipush 24
      // 45cf: imul
      // 45d0: iadd
      // 45d1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 45d4: aload 0
      // 45d5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 45d8: bipush 121
      // 45da: sipush 443
      // 45dd: iload 7
      // 45df: bipush 24
      // 45e1: imul
      // 45e2: iadd
      // 45e3: sipush 211
      // 45e6: sipush 443
      // 45e9: iload 7
      // 45eb: bipush 24
      // 45ed: imul
      // 45ee: iadd
      // 45ef: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 45f2: bipush 1
      // 45f3: istore 8
      // 45f5: iload 8
      // 45f7: bipush 10
      // 45f9: if_icmpge 4631
      // 45fc: aload 0
      // 45fd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4600: bipush 121
      // 4602: bipush 10
      // 4604: iload 8
      // 4606: imul
      // 4607: iadd
      // 4608: sipush 443
      // 460b: iload 8
      // 460d: isub
      // 460e: iload 7
      // 4610: bipush 24
      // 4612: imul
      // 4613: iadd
      // 4614: bipush 121
      // 4616: bipush 10
      // 4618: iload 8
      // 461a: imul
      // 461b: iadd
      // 461c: sipush 443
      // 461f: iload 8
      // 4621: iadd
      // 4622: iload 7
      // 4624: bipush 24
      // 4626: imul
      // 4627: iadd
      // 4628: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 462b: iinc 8 1
      // 462e: goto 45f5
      // 4631: aload 0
      // 4632: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4635: new java/awt/Color
      // 4638: dup
      // 4639: sipush 255
      // 463c: bipush 0
      // 463d: bipush 0
      // 463e: invokespecial java/awt/Color.<init> (III)V
      // 4641: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4644: aload 0
      // 4645: getfield CarMaker.phys [I
      // 4648: iload 7
      // 464a: bipush 5
      // 464b: iadd
      // 464c: iaload
      // 464d: i2f
      // 464e: ldc_w 1.1111
      // 4651: fdiv
      // 4652: ldc_w 10.0
      // 4655: fdiv
      // 4656: f2i
      // 4657: istore 8
      // 4659: aload 0
      // 465a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 465d: bipush 119
      // 465f: aload 0
      // 4660: getfield CarMaker.phys [I
      // 4663: iload 7
      // 4665: bipush 5
      // 4666: iadd
      // 4667: iaload
      // 4668: i2f
      // 4669: ldc_w 1.1111
      // 466c: fdiv
      // 466d: f2i
      // 466e: iadd
      // 466f: sipush 443
      // 4672: iload 8
      // 4674: isub
      // 4675: iload 7
      // 4677: bipush 24
      // 4679: imul
      // 467a: iadd
      // 467b: bipush 5
      // 467c: iload 8
      // 467e: bipush 2
      // 467f: imul
      // 4680: bipush 1
      // 4681: iadd
      // 4682: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4685: aload 0
      // 4686: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4689: new java/awt/Color
      // 468c: dup
      // 468d: sipush 255
      // 4690: sipush 128
      // 4693: bipush 0
      // 4694: invokespecial java/awt/Color.<init> (III)V
      // 4697: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 469a: aload 0
      // 469b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 469e: bipush 120
      // 46a0: aload 0
      // 46a1: getfield CarMaker.phys [I
      // 46a4: iload 7
      // 46a6: bipush 5
      // 46a7: iadd
      // 46a8: iaload
      // 46a9: i2f
      // 46aa: ldc_w 1.1111
      // 46ad: fdiv
      // 46ae: f2i
      // 46af: iadd
      // 46b0: sipush 434
      // 46b3: iload 7
      // 46b5: bipush 24
      // 46b7: imul
      // 46b8: iadd
      // 46b9: bipush 2
      // 46ba: bipush 18
      // 46bc: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 46bf: aload 0
      // 46c0: ldc_w " - "
      // 46c3: sipush 241
      // 46c6: sipush 447
      // 46c9: iload 7
      // 46cb: bipush 24
      // 46cd: imul
      // 46ce: iadd
      // 46cf: bipush 4
      // 46d0: bipush 0
      // 46d1: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 46d4: aload 0
      // 46d5: ldc_w " + "
      // 46d8: sipush 281
      // 46db: sipush 447
      // 46de: iload 7
      // 46e0: bipush 24
      // 46e2: imul
      // 46e3: iadd
      // 46e4: bipush 4
      // 46e5: bipush 0
      // 46e6: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 46e9: iinc 7 1
      // 46ec: goto 450b
      // 46ef: bipush 0
      // 46f0: istore 7
      // 46f2: iload 7
      // 46f4: bipush 2
      // 46f5: if_icmpge 48e4
      // 46f8: aload 0
      // 46f9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 46fc: new java/awt/Color
      // 46ff: dup
      // 4700: bipush 0
      // 4701: bipush 0
      // 4702: bipush 0
      // 4703: invokespecial java/awt/Color.<init> (III)V
      // 4706: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4709: aload 0
      // 470a: getfield CarMaker.xm I
      // 470d: aload 0
      // 470e: getfield CarMaker.pnx [I
      // 4711: iload 7
      // 4713: bipush 9
      // 4715: iadd
      // 4716: iaload
      // 4717: if_icmple 4785
      // 471a: aload 0
      // 471b: getfield CarMaker.xm I
      // 471e: sipush 548
      // 4721: if_icmpge 4785
      // 4724: aload 0
      // 4725: getfield CarMaker.ym I
      // 4728: sipush 433
      // 472b: iload 7
      // 472d: bipush 24
      // 472f: imul
      // 4730: iadd
      // 4731: if_icmple 4785
      // 4734: aload 0
      // 4735: getfield CarMaker.ym I
      // 4738: sipush 453
      // 473b: iload 7
      // 473d: bipush 24
      // 473f: imul
      // 4740: iadd
      // 4741: if_icmpge 4785
      // 4744: iload 7
      // 4746: bipush 9
      // 4748: iadd
      // 4749: istore 6
      // 474b: aload 0
      // 474c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 474f: new java/awt/Color
      // 4752: dup
      // 4753: sipush 176
      // 4756: bipush 64
      // 4758: bipush 0
      // 4759: invokespecial java/awt/Color.<init> (III)V
      // 475c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 475f: aload 0
      // 4760: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4763: aload 0
      // 4764: getfield CarMaker.pnx [I
      // 4767: iload 7
      // 4769: bipush 9
      // 476b: iadd
      // 476c: iaload
      // 476d: sipush 448
      // 4770: iload 7
      // 4772: bipush 24
      // 4774: imul
      // 4775: iadd
      // 4776: sipush 446
      // 4779: sipush 448
      // 477c: iload 7
      // 477e: bipush 24
      // 4780: imul
      // 4781: iadd
      // 4782: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4785: aload 0
      // 4786: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4789: new java/lang/StringBuilder
      // 478c: dup
      // 478d: invokespecial java/lang/StringBuilder.<init> ()V
      // 4790: ldc ""
      // 4792: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4795: aload 0
      // 4796: getfield CarMaker.pname [Ljava/lang/String;
      // 4799: iload 7
      // 479b: bipush 9
      // 479d: iadd
      // 479e: aaload
      // 479f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 47a2: ldc_w " :"
      // 47a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 47a8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 47ab: aload 0
      // 47ac: getfield CarMaker.pnx [I
      // 47af: iload 7
      // 47b1: bipush 9
      // 47b3: iadd
      // 47b4: iaload
      // 47b5: sipush 447
      // 47b8: iload 7
      // 47ba: bipush 24
      // 47bc: imul
      // 47bd: iadd
      // 47be: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 47c1: aload 0
      // 47c2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 47c5: sipush 458
      // 47c8: sipush 443
      // 47cb: iload 7
      // 47cd: bipush 24
      // 47cf: imul
      // 47d0: iadd
      // 47d1: sipush 548
      // 47d4: sipush 443
      // 47d7: iload 7
      // 47d9: bipush 24
      // 47db: imul
      // 47dc: iadd
      // 47dd: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 47e0: bipush 1
      // 47e1: istore 8
      // 47e3: iload 8
      // 47e5: bipush 10
      // 47e7: if_icmpge 4821
      // 47ea: aload 0
      // 47eb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 47ee: sipush 458
      // 47f1: bipush 10
      // 47f3: iload 8
      // 47f5: imul
      // 47f6: iadd
      // 47f7: sipush 443
      // 47fa: iload 8
      // 47fc: isub
      // 47fd: iload 7
      // 47ff: bipush 24
      // 4801: imul
      // 4802: iadd
      // 4803: sipush 458
      // 4806: bipush 10
      // 4808: iload 8
      // 480a: imul
      // 480b: iadd
      // 480c: sipush 443
      // 480f: iload 8
      // 4811: iadd
      // 4812: iload 7
      // 4814: bipush 24
      // 4816: imul
      // 4817: iadd
      // 4818: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 481b: iinc 8 1
      // 481e: goto 47e3
      // 4821: aload 0
      // 4822: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4825: new java/awt/Color
      // 4828: dup
      // 4829: sipush 255
      // 482c: bipush 0
      // 482d: bipush 0
      // 482e: invokespecial java/awt/Color.<init> (III)V
      // 4831: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4834: aload 0
      // 4835: getfield CarMaker.phys [I
      // 4838: iload 7
      // 483a: bipush 9
      // 483c: iadd
      // 483d: iaload
      // 483e: i2f
      // 483f: ldc_w 1.1111
      // 4842: fdiv
      // 4843: ldc_w 10.0
      // 4846: fdiv
      // 4847: f2i
      // 4848: istore 8
      // 484a: aload 0
      // 484b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 484e: sipush 456
      // 4851: aload 0
      // 4852: getfield CarMaker.phys [I
      // 4855: iload 7
      // 4857: bipush 9
      // 4859: iadd
      // 485a: iaload
      // 485b: i2f
      // 485c: ldc_w 1.1111
      // 485f: fdiv
      // 4860: f2i
      // 4861: iadd
      // 4862: sipush 443
      // 4865: iload 8
      // 4867: isub
      // 4868: iload 7
      // 486a: bipush 24
      // 486c: imul
      // 486d: iadd
      // 486e: bipush 5
      // 486f: iload 8
      // 4871: bipush 2
      // 4872: imul
      // 4873: bipush 1
      // 4874: iadd
      // 4875: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4878: aload 0
      // 4879: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 487c: new java/awt/Color
      // 487f: dup
      // 4880: sipush 255
      // 4883: sipush 128
      // 4886: bipush 0
      // 4887: invokespecial java/awt/Color.<init> (III)V
      // 488a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 488d: aload 0
      // 488e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4891: sipush 457
      // 4894: aload 0
      // 4895: getfield CarMaker.phys [I
      // 4898: iload 7
      // 489a: bipush 9
      // 489c: iadd
      // 489d: iaload
      // 489e: i2f
      // 489f: ldc_w 1.1111
      // 48a2: fdiv
      // 48a3: f2i
      // 48a4: iadd
      // 48a5: sipush 434
      // 48a8: iload 7
      // 48aa: bipush 24
      // 48ac: imul
      // 48ad: iadd
      // 48ae: bipush 2
      // 48af: bipush 18
      // 48b1: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 48b4: aload 0
      // 48b5: ldc_w " - "
      // 48b8: sipush 578
      // 48bb: sipush 447
      // 48be: iload 7
      // 48c0: bipush 24
      // 48c2: imul
      // 48c3: iadd
      // 48c4: bipush 4
      // 48c5: bipush 0
      // 48c6: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 48c9: aload 0
      // 48ca: ldc_w " + "
      // 48cd: sipush 618
      // 48d0: sipush 447
      // 48d3: iload 7
      // 48d5: bipush 24
      // 48d7: imul
      // 48d8: iadd
      // 48d9: bipush 4
      // 48da: bipush 0
      // 48db: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 48de: iinc 7 1
      // 48e1: goto 46f2
      // 48e4: aload 0
      // 48e5: ldc_w " Random "
      // 48e8: sipush 361
      // 48eb: sipush 519
      // 48ee: bipush 0
      // 48ef: bipush 0
      // 48f0: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 48f3: aload 0
      // 48f4: ldc_w " Reset "
      // 48f7: sipush 436
      // 48fa: sipush 519
      // 48fd: bipush 0
      // 48fe: bipush 0
      // 48ff: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4902: aload 0
      // 4903: ldc_w " <  Back "
      // 4906: sipush 509
      // 4909: sipush 519
      // 490c: bipush 0
      // 490d: bipush 0
      // 490e: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4911: aload 0
      // 4912: ldc_w "       Next  >       "
      // 4915: sipush 603
      // 4918: sipush 519
      // 491b: bipush 0
      // 491c: bipush 1
      // 491d: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4920: aload 0
      // 4921: getfield CarMaker.pfase I
      // 4924: bipush 2
      // 4925: if_icmpne 4f86
      // 4928: aload 0
      // 4929: getfield CarMaker.xm I
      // 492c: bipush 40
      // 492e: if_icmple 497a
      // 4931: aload 0
      // 4932: getfield CarMaker.xm I
      // 4935: sipush 670
      // 4938: if_icmpge 497a
      // 493b: aload 0
      // 493c: getfield CarMaker.ym I
      // 493f: sipush 416
      // 4942: if_icmple 497a
      // 4945: aload 0
      // 4946: getfield CarMaker.ym I
      // 4949: sipush 436
      // 494c: if_icmpge 497a
      // 494f: bipush 11
      // 4951: istore 6
      // 4953: aload 0
      // 4954: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4957: new java/awt/Color
      // 495a: dup
      // 495b: sipush 176
      // 495e: bipush 64
      // 4960: bipush 0
      // 4961: invokespecial java/awt/Color.<init> (III)V
      // 4964: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4967: aload 0
      // 4968: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 496b: sipush 596
      // 496e: sipush 431
      // 4971: sipush 669
      // 4974: sipush 431
      // 4977: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 497a: aload 0
      // 497b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 497e: ldc_w "[   Crash Test,  Damage :                                       ]                                                     What is this?"
      // 4981: sipush 180
      // 4984: sipush 430
      // 4987: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 498a: aload 0
      // 498b: getfield CarMaker.hitmag I
      // 498e: ifge 4996
      // 4991: aload 0
      // 4992: bipush 0
      // 4993: putfield CarMaker.hitmag I
      // 4996: aload 0
      // 4997: getfield CarMaker.hitmag I
      // 499a: sipush 17000
      // 499d: if_icmple 4a13
      // 49a0: aload 0
      // 49a1: bipush 1
      // 49a2: putfield CarMaker.crashok Z
      // 49a5: aload 0
      // 49a6: sipush 17000
      // 49a9: putfield CarMaker.hitmag I
      // 49ac: bipush 0
      // 49ad: istore 7
      // 49af: iload 7
      // 49b1: aload 0
      // 49b2: getfield CarMaker.o LContO;
      // 49b5: getfield ContO.npl I
      // 49b8: if_icmpge 4a13
      // 49bb: aload 0
      // 49bc: getfield CarMaker.o LContO;
      // 49bf: getfield ContO.p [LPlane;
      // 49c2: iload 7
      // 49c4: aaload
      // 49c5: getfield Plane.wz I
      // 49c8: ifeq 49ef
      // 49cb: aload 0
      // 49cc: getfield CarMaker.o LContO;
      // 49cf: getfield ContO.p [LPlane;
      // 49d2: iload 7
      // 49d4: aaload
      // 49d5: getfield Plane.gr I
      // 49d8: bipush -17
      // 49da: if_icmpeq 49ef
      // 49dd: aload 0
      // 49de: getfield CarMaker.o LContO;
      // 49e1: getfield ContO.p [LPlane;
      // 49e4: iload 7
      // 49e6: aaload
      // 49e7: getfield Plane.gr I
      // 49ea: bipush -16
      // 49ec: if_icmpne 4a0d
      // 49ef: aload 0
      // 49f0: getfield CarMaker.o LContO;
      // 49f3: getfield ContO.p [LPlane;
      // 49f6: iload 7
      // 49f8: aaload
      // 49f9: getfield Plane.embos I
      // 49fc: ifne 4a0d
      // 49ff: aload 0
      // 4a00: getfield CarMaker.o LContO;
      // 4a03: getfield ContO.p [LPlane;
      // 4a06: iload 7
      // 4a08: aaload
      // 4a09: bipush 1
      // 4a0a: putfield Plane.embos I
      // 4a0d: iinc 7 1
      // 4a10: goto 49af
      // 4a13: aload 0
      // 4a14: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a17: new java/awt/Color
      // 4a1a: dup
      // 4a1b: sipush 255
      // 4a1e: ldc_w 250.0
      // 4a21: aload 0
      // 4a22: getfield CarMaker.hitmag I
      // 4a25: i2f
      // 4a26: ldc_w 68.0
      // 4a29: fdiv
      // 4a2a: fsub
      // 4a2b: f2i
      // 4a2c: bipush 0
      // 4a2d: invokespecial java/awt/Color.<init> (III)V
      // 4a30: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4a33: aload 0
      // 4a34: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a37: sipush 322
      // 4a3a: sipush 423
      // 4a3d: aload 0
      // 4a3e: getfield CarMaker.hitmag I
      // 4a41: i2f
      // 4a42: ldc_w 170.0
      // 4a45: fdiv
      // 4a46: f2i
      // 4a47: bipush 7
      // 4a49: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4a4c: aload 0
      // 4a4d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a50: new java/awt/Color
      // 4a53: dup
      // 4a54: sipush 255
      // 4a57: bipush 0
      // 4a58: bipush 0
      // 4a59: invokespecial java/awt/Color.<init> (III)V
      // 4a5c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4a5f: aload 0
      // 4a60: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a63: sipush 322
      // 4a66: sipush 423
      // 4a69: bipush 100
      // 4a6b: bipush 7
      // 4a6d: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4a70: iload 6
      // 4a72: bipush 11
      // 4a74: if_icmpeq 4a91
      // 4a77: aload 0
      // 4a78: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a7b: new java/awt/Color
      // 4a7e: dup
      // 4a7f: sipush 170
      // 4a82: sipush 170
      // 4a85: sipush 170
      // 4a88: invokespecial java/awt/Color.<init> (III)V
      // 4a8b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4a8e: goto 4aa5
      // 4a91: aload 0
      // 4a92: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4a95: new java/awt/Color
      // 4a98: dup
      // 4a99: sipush 176
      // 4a9c: bipush 64
      // 4a9e: bipush 0
      // 4a9f: invokespecial java/awt/Color.<init> (III)V
      // 4aa2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4aa5: aload 0
      // 4aa6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4aa9: ldc_w "Normal Crash"
      // 4aac: bipush 39
      // 4aae: sipush 438
      // 4ab1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4ab4: aload 0
      // 4ab5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ab8: ldc_w "Roof Crash"
      // 4abb: sipush 501
      // 4abe: sipush 438
      // 4ac1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4ac4: aload 0
      // 4ac5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ac8: bipush 125
      // 4aca: sipush 426
      // 4acd: sipush 179
      // 4ad0: sipush 426
      // 4ad3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4ad6: aload 0
      // 4ad7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ada: bipush 125
      // 4adc: sipush 426
      // 4adf: bipush 125
      // 4ae1: sipush 440
      // 4ae4: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4ae7: aload 0
      // 4ae8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4aeb: sipush 491
      // 4aee: sipush 426
      // 4af1: sipush 437
      // 4af4: sipush 426
      // 4af7: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4afa: aload 0
      // 4afb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4afe: sipush 491
      // 4b01: sipush 426
      // 4b04: sipush 491
      // 4b07: sipush 440
      // 4b0a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4b0d: aload 0
      // 4b0e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b11: bipush 19
      // 4b13: sipush 440
      // 4b16: sipush 276
      // 4b19: bipush 91
      // 4b1b: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4b1e: aload 0
      // 4b1f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b22: sipush 339
      // 4b25: sipush 440
      // 4b28: sipush 312
      // 4b2b: bipush 67
      // 4b2d: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4b30: aload 0
      // 4b31: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b34: new java/awt/Color
      // 4b37: dup
      // 4b38: bipush 0
      // 4b39: bipush 0
      // 4b3a: bipush 0
      // 4b3b: invokespecial java/awt/Color.<init> (III)V
      // 4b3e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4b41: aload 0
      // 4b42: getfield CarMaker.xm I
      // 4b45: bipush 50
      // 4b47: if_icmple 4b91
      // 4b4a: aload 0
      // 4b4b: getfield CarMaker.xm I
      // 4b4e: sipush 195
      // 4b51: if_icmpge 4b91
      // 4b54: aload 0
      // 4b55: getfield CarMaker.ym I
      // 4b58: sipush 446
      // 4b5b: if_icmple 4b91
      // 4b5e: aload 0
      // 4b5f: getfield CarMaker.ym I
      // 4b62: sipush 466
      // 4b65: if_icmpge 4b91
      // 4b68: bipush 12
      // 4b6a: istore 6
      // 4b6c: aload 0
      // 4b6d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b70: new java/awt/Color
      // 4b73: dup
      // 4b74: sipush 176
      // 4b77: bipush 64
      // 4b79: bipush 0
      // 4b7a: invokespecial java/awt/Color.<init> (III)V
      // 4b7d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4b80: aload 0
      // 4b81: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b84: bipush 50
      // 4b86: sipush 461
      // 4b89: bipush 94
      // 4b8b: sipush 461
      // 4b8e: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4b91: aload 0
      // 4b92: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4b95: ldc_w "Radius :"
      // 4b98: bipush 50
      // 4b9a: sipush 460
      // 4b9d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4ba0: aload 0
      // 4ba1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ba4: bipush 105
      // 4ba6: sipush 456
      // 4ba9: sipush 195
      // 4bac: sipush 456
      // 4baf: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4bb2: bipush 1
      // 4bb3: istore 7
      // 4bb5: iload 7
      // 4bb7: bipush 10
      // 4bb9: if_icmpge 4be5
      // 4bbc: aload 0
      // 4bbd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4bc0: bipush 105
      // 4bc2: bipush 10
      // 4bc4: iload 7
      // 4bc6: imul
      // 4bc7: iadd
      // 4bc8: sipush 456
      // 4bcb: iload 7
      // 4bcd: isub
      // 4bce: bipush 105
      // 4bd0: bipush 10
      // 4bd2: iload 7
      // 4bd4: imul
      // 4bd5: iadd
      // 4bd6: sipush 456
      // 4bd9: iload 7
      // 4bdb: iadd
      // 4bdc: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4bdf: iinc 7 1
      // 4be2: goto 4bb5
      // 4be5: aload 0
      // 4be6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4be9: new java/awt/Color
      // 4bec: dup
      // 4bed: sipush 255
      // 4bf0: bipush 0
      // 4bf1: bipush 0
      // 4bf2: invokespecial java/awt/Color.<init> (III)V
      // 4bf5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4bf8: aload 0
      // 4bf9: getfield CarMaker.crash [I
      // 4bfc: bipush 0
      // 4bfd: iaload
      // 4bfe: i2f
      // 4bff: ldc_w 1.1111
      // 4c02: fdiv
      // 4c03: ldc_w 10.0
      // 4c06: fdiv
      // 4c07: f2i
      // 4c08: istore 7
      // 4c0a: aload 0
      // 4c0b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4c0e: bipush 103
      // 4c10: aload 0
      // 4c11: getfield CarMaker.crash [I
      // 4c14: bipush 0
      // 4c15: iaload
      // 4c16: i2f
      // 4c17: ldc_w 1.1111
      // 4c1a: fdiv
      // 4c1b: f2i
      // 4c1c: iadd
      // 4c1d: sipush 456
      // 4c20: iload 7
      // 4c22: isub
      // 4c23: bipush 5
      // 4c24: iload 7
      // 4c26: bipush 2
      // 4c27: imul
      // 4c28: bipush 1
      // 4c29: iadd
      // 4c2a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4c2d: aload 0
      // 4c2e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4c31: new java/awt/Color
      // 4c34: dup
      // 4c35: sipush 255
      // 4c38: sipush 128
      // 4c3b: bipush 0
      // 4c3c: invokespecial java/awt/Color.<init> (III)V
      // 4c3f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4c42: aload 0
      // 4c43: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4c46: bipush 104
      // 4c48: aload 0
      // 4c49: getfield CarMaker.crash [I
      // 4c4c: bipush 0
      // 4c4d: iaload
      // 4c4e: i2f
      // 4c4f: ldc_w 1.1111
      // 4c52: fdiv
      // 4c53: f2i
      // 4c54: iadd
      // 4c55: sipush 447
      // 4c58: bipush 2
      // 4c59: bipush 18
      // 4c5b: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4c5e: aload 0
      // 4c5f: ldc_w " - "
      // 4c62: sipush 225
      // 4c65: sipush 460
      // 4c68: bipush 4
      // 4c69: bipush 0
      // 4c6a: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4c6d: aload 0
      // 4c6e: ldc_w " + "
      // 4c71: sipush 265
      // 4c74: sipush 460
      // 4c77: bipush 4
      // 4c78: bipush 0
      // 4c79: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4c7c: aload 0
      // 4c7d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4c80: new java/awt/Color
      // 4c83: dup
      // 4c84: bipush 0
      // 4c85: bipush 0
      // 4c86: bipush 0
      // 4c87: invokespecial java/awt/Color.<init> (III)V
      // 4c8a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4c8d: aload 0
      // 4c8e: getfield CarMaker.xm I
      // 4c91: bipush 30
      // 4c93: if_icmple 4cdd
      // 4c96: aload 0
      // 4c97: getfield CarMaker.xm I
      // 4c9a: sipush 195
      // 4c9d: if_icmpge 4cdd
      // 4ca0: aload 0
      // 4ca1: getfield CarMaker.ym I
      // 4ca4: sipush 470
      // 4ca7: if_icmple 4cdd
      // 4caa: aload 0
      // 4cab: getfield CarMaker.ym I
      // 4cae: sipush 490
      // 4cb1: if_icmpge 4cdd
      // 4cb4: bipush 13
      // 4cb6: istore 6
      // 4cb8: aload 0
      // 4cb9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4cbc: new java/awt/Color
      // 4cbf: dup
      // 4cc0: sipush 176
      // 4cc3: bipush 64
      // 4cc5: bipush 0
      // 4cc6: invokespecial java/awt/Color.<init> (III)V
      // 4cc9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4ccc: aload 0
      // 4ccd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4cd0: bipush 30
      // 4cd2: sipush 485
      // 4cd5: bipush 94
      // 4cd7: sipush 485
      // 4cda: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4cdd: aload 0
      // 4cde: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ce1: ldc_w "Magnitude :"
      // 4ce4: bipush 30
      // 4ce6: sipush 484
      // 4ce9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4cec: aload 0
      // 4ced: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4cf0: bipush 105
      // 4cf2: sipush 480
      // 4cf5: sipush 195
      // 4cf8: sipush 480
      // 4cfb: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4cfe: bipush 1
      // 4cff: istore 8
      // 4d01: iload 8
      // 4d03: bipush 10
      // 4d05: if_icmpge 4d31
      // 4d08: aload 0
      // 4d09: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4d0c: bipush 105
      // 4d0e: bipush 10
      // 4d10: iload 8
      // 4d12: imul
      // 4d13: iadd
      // 4d14: sipush 480
      // 4d17: iload 8
      // 4d19: isub
      // 4d1a: bipush 105
      // 4d1c: bipush 10
      // 4d1e: iload 8
      // 4d20: imul
      // 4d21: iadd
      // 4d22: sipush 480
      // 4d25: iload 8
      // 4d27: iadd
      // 4d28: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4d2b: iinc 8 1
      // 4d2e: goto 4d01
      // 4d31: aload 0
      // 4d32: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4d35: new java/awt/Color
      // 4d38: dup
      // 4d39: sipush 255
      // 4d3c: bipush 0
      // 4d3d: bipush 0
      // 4d3e: invokespecial java/awt/Color.<init> (III)V
      // 4d41: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4d44: aload 0
      // 4d45: getfield CarMaker.crash [I
      // 4d48: bipush 1
      // 4d49: iaload
      // 4d4a: i2f
      // 4d4b: ldc_w 1.1111
      // 4d4e: fdiv
      // 4d4f: ldc_w 10.0
      // 4d52: fdiv
      // 4d53: f2i
      // 4d54: istore 7
      // 4d56: aload 0
      // 4d57: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4d5a: bipush 103
      // 4d5c: aload 0
      // 4d5d: getfield CarMaker.crash [I
      // 4d60: bipush 1
      // 4d61: iaload
      // 4d62: i2f
      // 4d63: ldc_w 1.1111
      // 4d66: fdiv
      // 4d67: f2i
      // 4d68: iadd
      // 4d69: sipush 480
      // 4d6c: iload 7
      // 4d6e: isub
      // 4d6f: bipush 5
      // 4d70: iload 7
      // 4d72: bipush 2
      // 4d73: imul
      // 4d74: bipush 1
      // 4d75: iadd
      // 4d76: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4d79: aload 0
      // 4d7a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4d7d: new java/awt/Color
      // 4d80: dup
      // 4d81: sipush 255
      // 4d84: sipush 128
      // 4d87: bipush 0
      // 4d88: invokespecial java/awt/Color.<init> (III)V
      // 4d8b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4d8e: aload 0
      // 4d8f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4d92: bipush 104
      // 4d94: aload 0
      // 4d95: getfield CarMaker.crash [I
      // 4d98: bipush 1
      // 4d99: iaload
      // 4d9a: i2f
      // 4d9b: ldc_w 1.1111
      // 4d9e: fdiv
      // 4d9f: f2i
      // 4da0: iadd
      // 4da1: sipush 471
      // 4da4: bipush 2
      // 4da5: bipush 18
      // 4da7: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4daa: aload 0
      // 4dab: ldc_w " - "
      // 4dae: sipush 225
      // 4db1: sipush 484
      // 4db4: bipush 4
      // 4db5: bipush 0
      // 4db6: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4db9: aload 0
      // 4dba: ldc_w " + "
      // 4dbd: sipush 265
      // 4dc0: sipush 484
      // 4dc3: bipush 4
      // 4dc4: bipush 0
      // 4dc5: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4dc8: aload 0
      // 4dc9: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4dcc: new java/awt/Color
      // 4dcf: dup
      // 4dd0: bipush 0
      // 4dd1: bipush 0
      // 4dd2: bipush 0
      // 4dd3: invokespecial java/awt/Color.<init> (III)V
      // 4dd6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4dd9: aload 0
      // 4dda: getfield CarMaker.xm I
      // 4ddd: sipush 350
      // 4de0: if_icmple 4e2c
      // 4de3: aload 0
      // 4de4: getfield CarMaker.xm I
      // 4de7: sipush 551
      // 4dea: if_icmpge 4e2c
      // 4ded: aload 0
      // 4dee: getfield CarMaker.ym I
      // 4df1: sipush 446
      // 4df4: if_icmple 4e2c
      // 4df7: aload 0
      // 4df8: getfield CarMaker.ym I
      // 4dfb: sipush 466
      // 4dfe: if_icmpge 4e2c
      // 4e01: bipush 14
      // 4e03: istore 6
      // 4e05: aload 0
      // 4e06: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e09: new java/awt/Color
      // 4e0c: dup
      // 4e0d: sipush 176
      // 4e10: bipush 64
      // 4e12: bipush 0
      // 4e13: invokespecial java/awt/Color.<init> (III)V
      // 4e16: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4e19: aload 0
      // 4e1a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e1d: sipush 350
      // 4e20: sipush 461
      // 4e23: sipush 450
      // 4e26: sipush 461
      // 4e29: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4e2c: aload 0
      // 4e2d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e30: ldc_w "Roof Destruction :"
      // 4e33: sipush 350
      // 4e36: sipush 460
      // 4e39: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4e3c: aload 0
      // 4e3d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e40: sipush 461
      // 4e43: sipush 456
      // 4e46: sipush 551
      // 4e49: sipush 456
      // 4e4c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4e4f: bipush 1
      // 4e50: istore 8
      // 4e52: iload 8
      // 4e54: bipush 10
      // 4e56: if_icmpge 4e84
      // 4e59: aload 0
      // 4e5a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e5d: sipush 461
      // 4e60: bipush 10
      // 4e62: iload 8
      // 4e64: imul
      // 4e65: iadd
      // 4e66: sipush 456
      // 4e69: iload 8
      // 4e6b: isub
      // 4e6c: sipush 461
      // 4e6f: bipush 10
      // 4e71: iload 8
      // 4e73: imul
      // 4e74: iadd
      // 4e75: sipush 456
      // 4e78: iload 8
      // 4e7a: iadd
      // 4e7b: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 4e7e: iinc 8 1
      // 4e81: goto 4e52
      // 4e84: aload 0
      // 4e85: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4e88: new java/awt/Color
      // 4e8b: dup
      // 4e8c: sipush 255
      // 4e8f: bipush 0
      // 4e90: bipush 0
      // 4e91: invokespecial java/awt/Color.<init> (III)V
      // 4e94: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4e97: aload 0
      // 4e98: getfield CarMaker.crash [I
      // 4e9b: bipush 2
      // 4e9c: iaload
      // 4e9d: i2f
      // 4e9e: ldc_w 1.1111
      // 4ea1: fdiv
      // 4ea2: ldc_w 10.0
      // 4ea5: fdiv
      // 4ea6: f2i
      // 4ea7: istore 7
      // 4ea9: aload 0
      // 4eaa: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ead: sipush 459
      // 4eb0: aload 0
      // 4eb1: getfield CarMaker.crash [I
      // 4eb4: bipush 2
      // 4eb5: iaload
      // 4eb6: i2f
      // 4eb7: ldc_w 1.1111
      // 4eba: fdiv
      // 4ebb: f2i
      // 4ebc: iadd
      // 4ebd: sipush 456
      // 4ec0: iload 7
      // 4ec2: isub
      // 4ec3: bipush 5
      // 4ec4: iload 7
      // 4ec6: bipush 2
      // 4ec7: imul
      // 4ec8: bipush 1
      // 4ec9: iadd
      // 4eca: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 4ecd: aload 0
      // 4ece: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ed1: new java/awt/Color
      // 4ed4: dup
      // 4ed5: sipush 255
      // 4ed8: sipush 128
      // 4edb: bipush 0
      // 4edc: invokespecial java/awt/Color.<init> (III)V
      // 4edf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4ee2: aload 0
      // 4ee3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4ee6: sipush 460
      // 4ee9: aload 0
      // 4eea: getfield CarMaker.crash [I
      // 4eed: bipush 2
      // 4eee: iaload
      // 4eef: i2f
      // 4ef0: ldc_w 1.1111
      // 4ef3: fdiv
      // 4ef4: f2i
      // 4ef5: iadd
      // 4ef6: sipush 447
      // 4ef9: bipush 2
      // 4efa: bipush 18
      // 4efc: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 4eff: aload 0
      // 4f00: ldc_w " - "
      // 4f03: sipush 581
      // 4f06: sipush 460
      // 4f09: bipush 4
      // 4f0a: bipush 0
      // 4f0b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f0e: aload 0
      // 4f0f: ldc_w " + "
      // 4f12: sipush 621
      // 4f15: sipush 460
      // 4f18: bipush 4
      // 4f19: bipush 0
      // 4f1a: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f1d: aload 0
      // 4f1e: ldc_w "    CRASH!    "
      // 4f21: sipush 143
      // 4f24: sipush 516
      // 4f27: bipush 0
      // 4f28: bipush 1
      // 4f29: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f2c: aload 0
      // 4f2d: ldc_w "  Fix  "
      // 4f30: sipush 235
      // 4f33: sipush 516
      // 4f36: bipush 0
      // 4f37: bipush 0
      // 4f38: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f3b: aload 0
      // 4f3c: ldc_w "    CRASH Roof!    "
      // 4f3f: sipush 484
      // 4f42: sipush 492
      // 4f45: bipush 0
      // 4f46: bipush 1
      // 4f47: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f4a: aload 0
      // 4f4b: ldc_w "  Fix  "
      // 4f4e: sipush 591
      // 4f51: sipush 492
      // 4f54: bipush 0
      // 4f55: bipush 0
      // 4f56: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f59: aload 0
      // 4f5a: ldc_w " Reset "
      // 4f5d: sipush 435
      // 4f60: sipush 535
      // 4f63: bipush 0
      // 4f64: bipush 0
      // 4f65: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f68: aload 0
      // 4f69: ldc_w " <  Back "
      // 4f6c: sipush 508
      // 4f6f: sipush 535
      // 4f72: bipush 0
      // 4f73: bipush 0
      // 4f74: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f77: aload 0
      // 4f78: ldc_w "       Next  >       "
      // 4f7b: sipush 602
      // 4f7e: sipush 535
      // 4f81: bipush 0
      // 4f82: bipush 1
      // 4f83: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 4f86: aload 0
      // 4f87: getfield CarMaker.pfase I
      // 4f8a: bipush 3
      // 4f8b: if_icmpne 513e
      // 4f8e: aload 0
      // 4f8f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4f92: new java/awt/Color
      // 4f95: dup
      // 4f96: bipush 0
      // 4f97: bipush 0
      // 4f98: bipush 0
      // 4f99: invokespecial java/awt/Color.<init> (III)V
      // 4f9c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4f9f: aload 0
      // 4fa0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 4fa3: ldc_w "Select the most suitable engine for your car :"
      // 4fa6: bipush 30
      // 4fa8: sipush 440
      // 4fab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4fae: aload 0
      // 4faf: getfield CarMaker.engine LSmenu;
      // 4fb2: sipush 293
      // 4fb5: sipush 424
      // 4fb8: invokevirtual Smenu.move (II)V
      // 4fbb: aload 0
      // 4fbc: getfield CarMaker.engine LSmenu;
      // 4fbf: invokevirtual Smenu.isShowing ()Z
      // 4fc2: ifne 4fd7
      // 4fc5: aload 0
      // 4fc6: getfield CarMaker.engine LSmenu;
      // 4fc9: invokevirtual Smenu.show ()V
      // 4fcc: aload 0
      // 4fcd: getfield CarMaker.engine LSmenu;
      // 4fd0: aload 0
      // 4fd1: getfield CarMaker.engsel I
      // 4fd4: invokevirtual Smenu.select (I)V
      // 4fd7: aload 0
      // 4fd8: getfield CarMaker.engsel I
      // 4fdb: aload 0
      // 4fdc: getfield CarMaker.engine LSmenu;
      // 4fdf: invokevirtual Smenu.getSelectedIndex ()I
      // 4fe2: if_icmpeq 502d
      // 4fe5: aload 0
      // 4fe6: aload 0
      // 4fe7: getfield CarMaker.engine LSmenu;
      // 4fea: invokevirtual Smenu.getSelectedIndex ()I
      // 4fed: putfield CarMaker.engsel I
      // 4ff0: bipush 0
      // 4ff1: istore 7
      // 4ff3: iload 7
      // 4ff5: bipush 5
      // 4ff6: if_icmpge 5028
      // 4ff9: bipush 0
      // 4ffa: istore 8
      // 4ffc: iload 8
      // 4ffe: bipush 5
      // 4fff: if_icmpge 5022
      // 5002: aload 0
      // 5003: getfield CarMaker.engs [[LsoundClip;
      // 5006: iload 8
      // 5008: aaload
      // 5009: iload 7
      // 500b: aaload
      // 500c: invokevirtual soundClip.stop ()V
      // 500f: aload 0
      // 5010: getfield CarMaker.engs [[LsoundClip;
      // 5013: iload 8
      // 5015: aaload
      // 5016: iload 7
      // 5018: aaload
      // 5019: invokevirtual soundClip.checkopen ()V
      // 501c: iinc 8 1
      // 501f: goto 4ffc
      // 5022: iinc 7 1
      // 5025: goto 4ff3
      // 5028: aload 0
      // 5029: bipush 0
      // 502a: putfield CarMaker.engon Z
      // 502d: aload 0
      // 502e: getfield CarMaker.engsel I
      // 5031: ifne 5043
      // 5034: aload 0
      // 5035: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5038: ldc_w "Normal Engine:  Like Tornado Shark, Sword of Justice or Radical One's engine."
      // 503b: bipush 30
      // 503d: sipush 470
      // 5040: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5043: aload 0
      // 5044: getfield CarMaker.engsel I
      // 5047: bipush 1
      // 5048: if_icmpne 505a
      // 504b: aload 0
      // 504c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 504f: ldc_w "V8 Engine:  High speed engine like Formula 7, Drifter X or Might Eight's engine."
      // 5052: bipush 30
      // 5054: sipush 470
      // 5057: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 505a: aload 0
      // 505b: getfield CarMaker.engsel I
      // 505e: bipush 2
      // 505f: if_icmpne 5071
      // 5062: aload 0
      // 5063: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5066: ldc_w "Retro Engine:  Like Wow Caninaro, Lead Oxide or Kool Kat’s engine."
      // 5069: bipush 30
      // 506b: sipush 470
      // 506e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5071: aload 0
      // 5072: getfield CarMaker.engsel I
      // 5075: bipush 3
      // 5076: if_icmpne 5088
      // 5079: aload 0
      // 507a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 507d: ldc_w "Power Engine:  Turbo/super charged engine like Max Revenge, High Rider or Dr Monstaa’s engine."
      // 5080: bipush 30
      // 5082: sipush 470
      // 5085: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5088: aload 0
      // 5089: getfield CarMaker.engsel I
      // 508c: bipush 4
      // 508d: if_icmpne 509f
      // 5090: aload 0
      // 5091: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5094: ldc_w "Diesel Engine:  Big diesel powered engine for big cars like EL King or  M A S H E E N ."
      // 5097: bipush 30
      // 5099: sipush 470
      // 509c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 509f: aload 0
      // 50a0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 50a3: ldc_w "LISTEN :"
      // 50a6: bipush 30
      // 50a8: sipush 500
      // 50ab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 50ae: aload 0
      // 50af: ldc_w " Idle "
      // 50b2: bipush 108
      // 50b4: sipush 500
      // 50b7: bipush 0
      // 50b8: bipush 1
      // 50b9: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 50bc: aload 0
      // 50bd: ldc_w " RPM 1 "
      // 50c0: sipush 170
      // 50c3: sipush 500
      // 50c6: bipush 0
      // 50c7: bipush 1
      // 50c8: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 50cb: aload 0
      // 50cc: ldc_w " RPM 2 "
      // 50cf: sipush 240
      // 50d2: sipush 500
      // 50d5: bipush 0
      // 50d6: bipush 1
      // 50d7: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 50da: aload 0
      // 50db: ldc_w " RPM 3 "
      // 50de: sipush 310
      // 50e1: sipush 500
      // 50e4: bipush 0
      // 50e5: bipush 1
      // 50e6: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 50e9: aload 0
      // 50ea: ldc_w " RPM MAX "
      // 50ed: sipush 389
      // 50f0: sipush 500
      // 50f3: bipush 0
      // 50f4: bipush 1
      // 50f5: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 50f8: aload 0
      // 50f9: getfield CarMaker.engon Z
      // 50fc: ifeq 5111
      // 50ff: aload 0
      // 5100: ldc_w "          Stop Engine          "
      // 5103: sipush 240
      // 5106: sipush 535
      // 5109: bipush 0
      // 510a: bipush 1
      // 510b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 510e: goto 5120
      // 5111: aload 0
      // 5112: ldc_w "          Stop Engine          "
      // 5115: sipush 240
      // 5118: sipush -2500
      // 511b: bipush 0
      // 511c: bipush 1
      // 511d: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5120: aload 0
      // 5121: ldc_w " <  Back "
      // 5124: sipush 500
      // 5127: sipush 525
      // 512a: bipush 0
      // 512b: bipush 0
      // 512c: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 512f: aload 0
      // 5130: ldc_w "     Save & Finish!     "
      // 5133: sipush 610
      // 5136: sipush 525
      // 5139: bipush 0
      // 513a: bipush 1
      // 513b: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 513e: aload 0
      // 513f: getfield CarMaker.pfase I
      // 5142: bipush 4
      // 5143: if_icmpne 5739
      // 5146: aload 0
      // 5147: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 514a: ldc_w "Testing & Setting up Physics..."
      // 514d: sipush 265
      // 5150: sipush 470
      // 5153: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5156: aload 0
      // 5157: invokevirtual CarMaker.repaint ()V
      // 515a: aload 0
      // 515b: getfield CarMaker.thredo Ljava/lang/Thread;
      // 515e: pop
      // 515f: ldc2_w 100
      // 5162: invokestatic java/lang/Thread.sleep (J)V
      // 5165: goto 516a
      // 5168: astore 7
      // 516a: bipush 0
      // 516b: istore 7
      // 516d: iload 7
      // 516f: bipush 4
      // 5170: if_icmpge 5522
      // 5173: bipush 0
      // 5174: istore 8
      // 5176: bipush 4
      // 5177: istore 9
      // 5179: iload 7
      // 517b: bipush 1
      // 517c: if_icmpne 5182
      // 517f: bipush 2
      // 5180: istore 9
      // 5182: iload 7
      // 5184: bipush 2
      // 5185: if_icmpne 518b
      // 5188: bipush 2
      // 5189: istore 8
      // 518b: bipush 0
      // 518c: istore 10
      // 518e: iload 10
      // 5190: bipush 10
      // 5192: if_icmpge 5249
      // 5195: aload 0
      // 5196: invokevirtual CarMaker.setupo ()V
      // 5199: aload 0
      // 519a: getfield CarMaker.o LContO;
      // 519d: bipush 0
      // 519e: putfield ContO.xy I
      // 51a1: aload 0
      // 51a2: bipush 0
      // 51a3: putfield CarMaker.hitmag I
      // 51a6: bipush 0
      // 51a7: istore 11
      // 51a9: aload 0
      // 51aa: bipush 0
      // 51ab: putfield CarMaker.actmag I
      // 51ae: iload 8
      // 51b0: istore 12
      // 51b2: bipush 0
      // 51b3: istore 13
      // 51b5: aload 0
      // 51b6: getfield CarMaker.hitmag I
      // 51b9: sipush 17000
      // 51bc: if_icmpge 5243
      // 51bf: iload 13
      // 51c1: ifeq 51db
      // 51c4: aload 0
      // 51c5: iload 12
      // 51c7: ldc2_w 150.0
      // 51ca: ldc2_w 600.0
      // 51cd: invokestatic java/lang/Math.random ()D
      // 51d0: dmul
      // 51d1: dadd
      // 51d2: d2i
      // 51d3: i2f
      // 51d4: bipush 1
      // 51d5: invokevirtual CarMaker.regx (IFZ)V
      // 51d8: goto 51ef
      // 51db: aload 0
      // 51dc: iload 12
      // 51de: ldc2_w 150.0
      // 51e1: ldc2_w 600.0
      // 51e4: invokestatic java/lang/Math.random ()D
      // 51e7: dmul
      // 51e8: dadd
      // 51e9: d2i
      // 51ea: i2f
      // 51eb: bipush 1
      // 51ec: invokevirtual CarMaker.regz (IFZ)V
      // 51ef: iinc 12 1
      // 51f2: iload 12
      // 51f4: iload 9
      // 51f6: if_icmpne 51b5
      // 51f9: aload 0
      // 51fa: getfield CarMaker.o LContO;
      // 51fd: dup
      // 51fe: getfield ContO.xz I
      // 5201: bipush 45
      // 5203: iadd
      // 5204: putfield ContO.xz I
      // 5207: aload 0
      // 5208: getfield CarMaker.o LContO;
      // 520b: dup
      // 520c: getfield ContO.zy I
      // 520f: bipush 45
      // 5211: iadd
      // 5212: putfield ContO.zy I
      // 5215: bipush 0
      // 5216: istore 12
      // 5218: iload 13
      // 521a: ifeq 5223
      // 521d: bipush 0
      // 521e: istore 13
      // 5220: goto 5226
      // 5223: bipush 1
      // 5224: istore 13
      // 5226: iload 11
      // 5228: aload 0
      // 5229: getfield CarMaker.actmag I
      // 522c: if_icmpne 523a
      // 522f: aload 0
      // 5230: getfield CarMaker.crash [I
      // 5233: bipush 0
      // 5234: dup2
      // 5235: iaload
      // 5236: bipush 10
      // 5238: iadd
      // 5239: iastore
      // 523a: aload 0
      // 523b: getfield CarMaker.actmag I
      // 523e: istore 11
      // 5240: goto 51b5
      // 5243: iinc 10 1
      // 5246: goto 518e
      // 5249: fconst_0
      // 524a: fstore 10
      // 524c: bipush 0
      // 524d: istore 11
      // 524f: iload 11
      // 5251: bipush 10
      // 5253: if_icmpge 52fd
      // 5256: aload 0
      // 5257: invokevirtual CarMaker.setupo ()V
      // 525a: aload 0
      // 525b: getfield CarMaker.o LContO;
      // 525e: bipush 0
      // 525f: putfield ContO.xy I
      // 5262: aload 0
      // 5263: bipush 0
      // 5264: putfield CarMaker.actmag I
      // 5267: aload 0
      // 5268: bipush 0
      // 5269: putfield CarMaker.hitmag I
      // 526c: iload 8
      // 526e: istore 12
      // 5270: bipush 0
      // 5271: istore 13
      // 5273: aload 0
      // 5274: getfield CarMaker.hitmag I
      // 5277: sipush 17000
      // 527a: if_icmpge 52e7
      // 527d: iload 13
      // 527f: ifeq 5299
      // 5282: aload 0
      // 5283: iload 12
      // 5285: ldc2_w 150.0
      // 5288: ldc2_w 600.0
      // 528b: invokestatic java/lang/Math.random ()D
      // 528e: dmul
      // 528f: dadd
      // 5290: d2i
      // 5291: i2f
      // 5292: bipush 1
      // 5293: invokevirtual CarMaker.regx (IFZ)V
      // 5296: goto 52ad
      // 5299: aload 0
      // 529a: iload 12
      // 529c: ldc2_w 150.0
      // 529f: ldc2_w 600.0
      // 52a2: invokestatic java/lang/Math.random ()D
      // 52a5: dmul
      // 52a6: dadd
      // 52a7: d2i
      // 52a8: i2f
      // 52a9: bipush 1
      // 52aa: invokevirtual CarMaker.regz (IFZ)V
      // 52ad: iinc 12 1
      // 52b0: iload 12
      // 52b2: iload 9
      // 52b4: if_icmpne 5273
      // 52b7: aload 0
      // 52b8: getfield CarMaker.o LContO;
      // 52bb: dup
      // 52bc: getfield ContO.xz I
      // 52bf: bipush 45
      // 52c1: iadd
      // 52c2: putfield ContO.xz I
      // 52c5: aload 0
      // 52c6: getfield CarMaker.o LContO;
      // 52c9: dup
      // 52ca: getfield ContO.zy I
      // 52cd: bipush 45
      // 52cf: iadd
      // 52d0: putfield ContO.zy I
      // 52d3: bipush 0
      // 52d4: istore 12
      // 52d6: iload 13
      // 52d8: ifeq 52e1
      // 52db: bipush 0
      // 52dc: istore 13
      // 52de: goto 5273
      // 52e1: bipush 1
      // 52e2: istore 13
      // 52e4: goto 5273
      // 52e7: fload 10
      // 52e9: aload 0
      // 52ea: getfield CarMaker.actmag I
      // 52ed: i2f
      // 52ee: aload 0
      // 52ef: getfield CarMaker.hitmag I
      // 52f2: i2f
      // 52f3: fdiv
      // 52f4: fadd
      // 52f5: fstore 10
      // 52f7: iinc 11 1
      // 52fa: goto 524f
      // 52fd: fload 10
      // 52ff: ldc_w 10.0
      // 5302: fdiv
      // 5303: fstore 10
      // 5305: aload 0
      // 5306: aload 0
      // 5307: getfield CarMaker.hitmag I
      // 530a: i2f
      // 530b: fload 10
      // 530d: fmul
      // 530e: f2i
      // 530f: putfield CarMaker.actmag I
      // 5312: aload 0
      // 5313: getfield CarMaker.stat [I
      // 5316: bipush 4
      // 5317: iaload
      // 5318: sipush 200
      // 531b: if_icmple 5327
      // 531e: aload 0
      // 531f: getfield CarMaker.stat [I
      // 5322: bipush 4
      // 5323: sipush 200
      // 5326: iastore
      // 5327: aload 0
      // 5328: getfield CarMaker.stat [I
      // 532b: bipush 4
      // 532c: iaload
      // 532d: bipush 16
      // 532f: if_icmpge 533a
      // 5332: aload 0
      // 5333: getfield CarMaker.stat [I
      // 5336: bipush 4
      // 5337: bipush 16
      // 5339: iastore
      // 533a: ldc_w 0.9
      // 533d: aload 0
      // 533e: getfield CarMaker.stat [I
      // 5341: bipush 4
      // 5342: iaload
      // 5343: bipush 90
      // 5345: isub
      // 5346: i2f
      // 5347: ldc_w 0.01
      // 534a: fmul
      // 534b: fadd
      // 534c: fstore 11
      // 534e: fload 11
      // 5350: f2d
      // 5351: ldc2_w 0.6
      // 5354: dcmpg
      // 5355: ifge 535d
      // 5358: ldc_w 0.6
      // 535b: fstore 11
      // 535d: aload 0
      // 535e: getfield CarMaker.stat [I
      // 5361: bipush 4
      // 5362: iaload
      // 5363: sipush 200
      // 5366: if_icmpne 5379
      // 5369: aload 0
      // 536a: getfield CarMaker.stat [I
      // 536d: bipush 0
      // 536e: iaload
      // 536f: bipush 88
      // 5371: if_icmpgt 5379
      // 5374: ldc_w 3.0
      // 5377: fstore 11
      // 5379: aload 0
      // 537a: getfield CarMaker.actmag I
      // 537d: i2f
      // 537e: fload 11
      // 5380: fmul
      // 5381: f2i
      // 5382: istore 12
      // 5384: bipush 0
      // 5385: istore 13
      // 5387: iload 13
      // 5389: bipush 12
      // 538b: if_icmpge 544d
      // 538e: aload 0
      // 538f: invokevirtual CarMaker.setupo ()V
      // 5392: aload 0
      // 5393: getfield CarMaker.o LContO;
      // 5396: bipush 0
      // 5397: putfield ContO.xy I
      // 539a: aload 0
      // 539b: getfield CarMaker.o LContO;
      // 539e: bipush 90
      // 53a0: iload 13
      // 53a2: imul
      // 53a3: putfield ContO.xz I
      // 53a6: aload 0
      // 53a7: getfield CarMaker.o LContO;
      // 53aa: getfield ContO.xz I
      // 53ad: sipush 360
      // 53b0: if_icmplt 53c2
      // 53b3: aload 0
      // 53b4: getfield CarMaker.o LContO;
      // 53b7: dup
      // 53b8: getfield ContO.xz I
      // 53bb: sipush 360
      // 53be: isub
      // 53bf: putfield ContO.xz I
      // 53c2: aload 0
      // 53c3: bipush 0
      // 53c4: putfield CarMaker.hitmag I
      // 53c7: bipush 0
      // 53c8: istore 14
      // 53ca: aload 0
      // 53cb: bipush 0
      // 53cc: putfield CarMaker.actmag I
      // 53cf: iload 8
      // 53d1: istore 15
      // 53d3: bipush 0
      // 53d4: istore 16
      // 53d6: aload 0
      // 53d7: getfield CarMaker.actmag I
      // 53da: iload 12
      // 53dc: if_icmpge 5447
      // 53df: iload 16
      // 53e1: ifeq 53fb
      // 53e4: aload 0
      // 53e5: iload 15
      // 53e7: ldc2_w 150.0
      // 53ea: ldc2_w 600.0
      // 53ed: invokestatic java/lang/Math.random ()D
      // 53f0: dmul
      // 53f1: dadd
      // 53f2: d2i
      // 53f3: i2f
      // 53f4: bipush 1
      // 53f5: invokevirtual CarMaker.regx (IFZ)V
      // 53f8: goto 540f
      // 53fb: aload 0
      // 53fc: iload 15
      // 53fe: ldc2_w 150.0
      // 5401: ldc2_w 600.0
      // 5404: invokestatic java/lang/Math.random ()D
      // 5407: dmul
      // 5408: dadd
      // 5409: d2i
      // 540a: i2f
      // 540b: bipush 1
      // 540c: invokevirtual CarMaker.regz (IFZ)V
      // 540f: iinc 15 1
      // 5412: iload 15
      // 5414: iload 9
      // 5416: if_icmpne 53d6
      // 5419: iload 16
      // 541b: ifeq 5424
      // 541e: bipush 0
      // 541f: istore 16
      // 5421: goto 5427
      // 5424: bipush 1
      // 5425: istore 16
      // 5427: bipush 0
      // 5428: istore 15
      // 542a: iload 14
      // 542c: aload 0
      // 542d: getfield CarMaker.actmag I
      // 5430: if_icmpne 543e
      // 5433: aload 0
      // 5434: getfield CarMaker.crash [I
      // 5437: bipush 0
      // 5438: dup2
      // 5439: iaload
      // 543a: bipush 10
      // 543c: iadd
      // 543d: iastore
      // 543e: aload 0
      // 543f: getfield CarMaker.actmag I
      // 5442: istore 14
      // 5444: goto 53d6
      // 5447: iinc 13 1
      // 544a: goto 5387
      // 544d: iload 7
      // 544f: bipush 3
      // 5450: if_icmpne 551c
      // 5453: fconst_0
      // 5454: fstore 10
      // 5456: bipush 0
      // 5457: istore 13
      // 5459: iload 13
      // 545b: bipush 10
      // 545d: if_icmpge 5507
      // 5460: aload 0
      // 5461: invokevirtual CarMaker.setupo ()V
      // 5464: aload 0
      // 5465: getfield CarMaker.o LContO;
      // 5468: bipush 0
      // 5469: putfield ContO.xy I
      // 546c: aload 0
      // 546d: bipush 0
      // 546e: putfield CarMaker.actmag I
      // 5471: aload 0
      // 5472: bipush 0
      // 5473: putfield CarMaker.hitmag I
      // 5476: iload 8
      // 5478: istore 14
      // 547a: bipush 0
      // 547b: istore 15
      // 547d: aload 0
      // 547e: getfield CarMaker.hitmag I
      // 5481: sipush 17000
      // 5484: if_icmpge 54f1
      // 5487: iload 15
      // 5489: ifeq 54a3
      // 548c: aload 0
      // 548d: iload 14
      // 548f: ldc2_w 150.0
      // 5492: ldc2_w 600.0
      // 5495: invokestatic java/lang/Math.random ()D
      // 5498: dmul
      // 5499: dadd
      // 549a: d2i
      // 549b: i2f
      // 549c: bipush 1
      // 549d: invokevirtual CarMaker.regx (IFZ)V
      // 54a0: goto 54b7
      // 54a3: aload 0
      // 54a4: iload 14
      // 54a6: ldc2_w 150.0
      // 54a9: ldc2_w 600.0
      // 54ac: invokestatic java/lang/Math.random ()D
      // 54af: dmul
      // 54b0: dadd
      // 54b1: d2i
      // 54b2: i2f
      // 54b3: bipush 1
      // 54b4: invokevirtual CarMaker.regz (IFZ)V
      // 54b7: iinc 14 1
      // 54ba: iload 14
      // 54bc: iload 9
      // 54be: if_icmpne 547d
      // 54c1: aload 0
      // 54c2: getfield CarMaker.o LContO;
      // 54c5: dup
      // 54c6: getfield ContO.xz I
      // 54c9: bipush 45
      // 54cb: iadd
      // 54cc: putfield ContO.xz I
      // 54cf: aload 0
      // 54d0: getfield CarMaker.o LContO;
      // 54d3: dup
      // 54d4: getfield ContO.zy I
      // 54d7: bipush 45
      // 54d9: iadd
      // 54da: putfield ContO.zy I
      // 54dd: bipush 0
      // 54de: istore 14
      // 54e0: iload 15
      // 54e2: ifeq 54eb
      // 54e5: bipush 0
      // 54e6: istore 15
      // 54e8: goto 547d
      // 54eb: bipush 1
      // 54ec: istore 15
      // 54ee: goto 547d
      // 54f1: fload 10
      // 54f3: aload 0
      // 54f4: getfield CarMaker.actmag I
      // 54f7: i2f
      // 54f8: aload 0
      // 54f9: getfield CarMaker.hitmag I
      // 54fc: i2f
      // 54fd: fdiv
      // 54fe: fadd
      // 54ff: fstore 10
      // 5501: iinc 13 1
      // 5504: goto 5459
      // 5507: fload 10
      // 5509: ldc_w 10.0
      // 550c: fdiv
      // 550d: fstore 10
      // 550f: aload 0
      // 5510: aload 0
      // 5511: getfield CarMaker.hitmag I
      // 5514: i2f
      // 5515: fload 10
      // 5517: fmul
      // 5518: f2i
      // 5519: putfield CarMaker.actmag I
      // 551c: iinc 7 1
      // 551f: goto 516d
      // 5522: aload 0
      // 5523: invokevirtual CarMaker.setupo ()V
      // 5526: new java/lang/StringBuilder
      // 5529: dup
      // 552a: invokespecial java/lang/StringBuilder.<init> ()V
      // 552d: ldc ""
      // 552f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5532: aload 0
      // 5533: getfield CarMaker.editor Ljava/awt/TextArea;
      // 5536: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 5539: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 553c: ldc_w "\n"
      // 553f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5542: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5545: astore 7
      // 5547: ldc ""
      // 5549: astore 8
      // 554b: bipush 0
      // 554c: istore 9
      // 554e: aload 7
      // 5550: ldc_w "\n"
      // 5553: bipush 0
      // 5554: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 5557: istore 10
      // 5559: iload 10
      // 555b: bipush -1
      // 555c: if_icmpeq 55dd
      // 555f: iload 9
      // 5561: aload 7
      // 5563: invokevirtual java/lang/String.length ()I
      // 5566: if_icmpge 55dd
      // 5569: aload 7
      // 556b: iload 9
      // 556d: iload 10
      // 556f: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 5572: astore 11
      // 5574: aload 11
      // 5576: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 5579: astore 11
      // 557b: iload 10
      // 557d: bipush 1
      // 557e: iadd
      // 557f: istore 9
      // 5581: aload 7
      // 5583: ldc_w "\n"
      // 5586: iload 9
      // 5588: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 558b: istore 10
      // 558d: aload 11
      // 558f: ldc_w "physics("
      // 5592: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 5595: ifne 55bc
      // 5598: new java/lang/StringBuilder
      // 559b: dup
      // 559c: invokespecial java/lang/StringBuilder.<init> ()V
      // 559f: aload 8
      // 55a1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55a4: ldc ""
      // 55a6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55a9: aload 11
      // 55ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55ae: ldc_w "\n"
      // 55b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55b4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 55b7: astore 8
      // 55b9: goto 55da
      // 55bc: aload 8
      // 55be: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 55c1: astore 8
      // 55c3: new java/lang/StringBuilder
      // 55c6: dup
      // 55c7: invokespecial java/lang/StringBuilder.<init> ()V
      // 55ca: aload 8
      // 55cc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55cf: ldc_w "\n"
      // 55d2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55d5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 55d8: astore 8
      // 55da: goto 5559
      // 55dd: aload 8
      // 55df: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 55e2: astore 8
      // 55e4: new java/lang/StringBuilder
      // 55e7: dup
      // 55e8: invokespecial java/lang/StringBuilder.<init> ()V
      // 55eb: aload 8
      // 55ed: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55f0: ldc_w "\n\n\nphysics("
      // 55f3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 55f6: aload 0
      // 55f7: getfield CarMaker.phys [I
      // 55fa: bipush 0
      // 55fb: iaload
      // 55fc: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 55ff: ldc_w ","
      // 5602: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5605: aload 0
      // 5606: getfield CarMaker.phys [I
      // 5609: bipush 1
      // 560a: iaload
      // 560b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 560e: ldc_w ","
      // 5611: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5614: aload 0
      // 5615: getfield CarMaker.phys [I
      // 5618: bipush 2
      // 5619: iaload
      // 561a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 561d: ldc_w ","
      // 5620: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5623: aload 0
      // 5624: getfield CarMaker.phys [I
      // 5627: bipush 3
      // 5628: iaload
      // 5629: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 562c: ldc_w ","
      // 562f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5632: aload 0
      // 5633: getfield CarMaker.phys [I
      // 5636: bipush 4
      // 5637: iaload
      // 5638: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 563b: ldc_w ","
      // 563e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5641: aload 0
      // 5642: getfield CarMaker.phys [I
      // 5645: bipush 5
      // 5646: iaload
      // 5647: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 564a: ldc_w ","
      // 564d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5650: aload 0
      // 5651: getfield CarMaker.phys [I
      // 5654: bipush 6
      // 5656: iaload
      // 5657: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 565a: ldc_w ","
      // 565d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5660: aload 0
      // 5661: getfield CarMaker.phys [I
      // 5664: bipush 7
      // 5666: iaload
      // 5667: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 566a: ldc_w ","
      // 566d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5670: aload 0
      // 5671: getfield CarMaker.phys [I
      // 5674: bipush 8
      // 5676: iaload
      // 5677: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 567a: ldc_w ","
      // 567d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5680: aload 0
      // 5681: getfield CarMaker.phys [I
      // 5684: bipush 9
      // 5686: iaload
      // 5687: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 568a: ldc_w ","
      // 568d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5690: aload 0
      // 5691: getfield CarMaker.phys [I
      // 5694: bipush 10
      // 5696: iaload
      // 5697: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 569a: ldc_w ","
      // 569d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56a0: aload 0
      // 56a1: getfield CarMaker.crash [I
      // 56a4: bipush 0
      // 56a5: iaload
      // 56a6: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 56a9: ldc_w ","
      // 56ac: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56af: aload 0
      // 56b0: getfield CarMaker.crash [I
      // 56b3: bipush 1
      // 56b4: iaload
      // 56b5: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 56b8: ldc_w ","
      // 56bb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56be: aload 0
      // 56bf: getfield CarMaker.crash [I
      // 56c2: bipush 2
      // 56c3: iaload
      // 56c4: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 56c7: ldc_w ","
      // 56ca: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56cd: aload 0
      // 56ce: getfield CarMaker.engsel I
      // 56d1: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 56d4: ldc_w ","
      // 56d7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56da: aload 0
      // 56db: getfield CarMaker.actmag I
      // 56de: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 56e1: ldc_w ")\n\n\n\n"
      // 56e4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 56e7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 56ea: astore 8
      // 56ec: aload 0
      // 56ed: getfield CarMaker.editor Ljava/awt/TextArea;
      // 56f0: aload 8
      // 56f2: invokevirtual java/awt/TextArea.setText (Ljava/lang/String;)V
      // 56f5: aload 0
      // 56f6: invokevirtual CarMaker.savefile ()V
      // 56f9: bipush 0
      // 56fa: istore 11
      // 56fc: iload 11
      // 56fe: bipush 11
      // 5700: if_icmpge 5717
      // 5703: aload 0
      // 5704: getfield CarMaker.rphys [I
      // 5707: iload 11
      // 5709: aload 0
      // 570a: getfield CarMaker.phys [I
      // 570d: iload 11
      // 570f: iaload
      // 5710: iastore
      // 5711: iinc 11 1
      // 5714: goto 56fc
      // 5717: bipush 0
      // 5718: istore 11
      // 571a: iload 11
      // 571c: bipush 3
      // 571d: if_icmpge 5734
      // 5720: aload 0
      // 5721: getfield CarMaker.rcrash [I
      // 5724: iload 11
      // 5726: aload 0
      // 5727: getfield CarMaker.crash [I
      // 572a: iload 11
      // 572c: iaload
      // 572d: iastore
      // 572e: iinc 11 1
      // 5731: goto 571a
      // 5734: aload 0
      // 5735: bipush 5
      // 5736: putfield CarMaker.pfase I
      // 5739: aload 0
      // 573a: getfield CarMaker.pfase I
      // 573d: bipush 5
      // 573e: if_icmpne 5761
      // 5741: aload 0
      // 5742: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5745: ldc_w "Car physics has been successfully set up!"
      // 5748: sipush 231
      // 574b: sipush 450
      // 574e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5751: aload 0
      // 5752: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5755: ldc_w "Test drive your car to see the results..."
      // 5758: sipush 242
      // 575b: sipush 490
      // 575e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5761: iload 6
      // 5763: bipush -1
      // 5764: if_icmpeq 5785
      // 5767: aload 0
      // 5768: getfield CarMaker.mouseon I
      // 576b: bipush -1
      // 576c: if_icmpne 579e
      // 576f: aload 0
      // 5770: iload 6
      // 5772: putfield CarMaker.mouseon I
      // 5775: aload 0
      // 5776: new java/awt/Cursor
      // 5779: dup
      // 577a: bipush 12
      // 577c: invokespecial java/awt/Cursor.<init> (I)V
      // 577f: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 5782: goto 579e
      // 5785: aload 0
      // 5786: getfield CarMaker.mouseon I
      // 5789: bipush -1
      // 578a: if_icmpeq 579e
      // 578d: aload 0
      // 578e: bipush -1
      // 578f: putfield CarMaker.mouseon I
      // 5792: aload 0
      // 5793: new java/awt/Cursor
      // 5796: dup
      // 5797: bipush 0
      // 5798: invokespecial java/awt/Cursor.<init> (I)V
      // 579b: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 579e: aload 0
      // 579f: getfield CarMaker.mouses I
      // 57a2: bipush -1
      // 57a3: if_icmpne 57ba
      // 57a6: iload 6
      // 57a8: bipush -1
      // 57a9: if_icmpeq 57ba
      // 57ac: aconst_null
      // 57ad: aload 0
      // 57ae: getfield CarMaker.usage [Ljava/lang/String;
      // 57b1: iload 6
      // 57b3: aaload
      // 57b4: ldc "Car Maker"
      // 57b6: bipush 1
      // 57b7: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 57ba: aload 0
      // 57bb: getfield CarMaker.dtab I
      // 57be: bipush 6
      // 57c0: if_icmpne 5aa7
      // 57c3: aload 0
      // 57c4: getfield CarMaker.dtab I
      // 57c7: aload 0
      // 57c8: getfield CarMaker.dtabed I
      // 57cb: if_icmpeq 5877
      // 57ce: new java/lang/StringBuilder
      // 57d1: dup
      // 57d2: invokespecial java/lang/StringBuilder.<init> ()V
      // 57d5: ldc ""
      // 57d7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 57da: aload 0
      // 57db: getfield CarMaker.editor Ljava/awt/TextArea;
      // 57de: invokevirtual java/awt/TextArea.getText ()Ljava/lang/String;
      // 57e1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 57e4: ldc_w "\n"
      // 57e7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 57ea: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 57ed: astore 6
      // 57ef: bipush 0
      // 57f0: istore 7
      // 57f2: aload 6
      // 57f4: ldc_w "\n"
      // 57f7: bipush 0
      // 57f8: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 57fb: istore 8
      // 57fd: iload 8
      // 57ff: bipush -1
      // 5800: if_icmpeq 5872
      // 5803: iload 7
      // 5805: aload 6
      // 5807: invokevirtual java/lang/String.length ()I
      // 580a: if_icmpge 5872
      // 580d: aload 6
      // 580f: iload 7
      // 5811: iload 8
      // 5813: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 5816: astore 9
      // 5818: aload 9
      // 581a: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 581d: astore 9
      // 581f: iload 8
      // 5821: bipush 1
      // 5822: iadd
      // 5823: istore 7
      // 5825: aload 6
      // 5827: ldc_w "\n"
      // 582a: iload 7
      // 582c: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 582f: istore 8
      // 5831: aload 9
      // 5833: ldc_w "handling("
      // 5836: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 5839: ifeq 586f
      // 583c: aload 0
      // 583d: aload 0
      // 583e: ldc_w "handling"
      // 5841: aload 9
      // 5843: bipush 0
      // 5844: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 5847: putfield CarMaker.handling I
      // 584a: aload 0
      // 584b: getfield CarMaker.handling I
      // 584e: sipush 200
      // 5851: if_icmple 585b
      // 5854: aload 0
      // 5855: sipush 200
      // 5858: putfield CarMaker.handling I
      // 585b: aload 0
      // 585c: getfield CarMaker.handling I
      // 585f: bipush 50
      // 5861: if_icmpge 586a
      // 5864: aload 0
      // 5865: bipush 50
      // 5867: putfield CarMaker.handling I
      // 586a: goto 586f
      // 586d: astore 10
      // 586f: goto 57fd
      // 5872: aload 0
      // 5873: bipush 0
      // 5874: putfield CarMaker.rateh Z
      // 5877: aload 0
      // 5878: getfield CarMaker.rateh Z
      // 587b: ifne 591e
      // 587e: aload 0
      // 587f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5882: new java/awt/Font
      // 5885: dup
      // 5886: ldc_w "Arial"
      // 5889: bipush 1
      // 588a: bipush 13
      // 588c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 588f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5892: aload 0
      // 5893: aload 0
      // 5894: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5897: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 589a: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 589d: aload 0
      // 589e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 58a1: ldc_w "Test Drive the Car"
      // 58a4: sipush 350
      // 58a7: aload 0
      // 58a8: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 58ab: ldc_w "Test Drive the Car"
      // 58ae: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 58b1: bipush 2
      // 58b2: idiv
      // 58b3: isub
      // 58b4: sipush 445
      // 58b7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 58ba: aload 0
      // 58bb: getfield CarMaker.witho LSmenu;
      // 58be: sipush 292
      // 58c1: sipush 455
      // 58c4: invokevirtual Smenu.move (II)V
      // 58c7: aload 0
      // 58c8: getfield CarMaker.witho LSmenu;
      // 58cb: invokevirtual Smenu.isShowing ()Z
      // 58ce: ifne 58d8
      // 58d1: aload 0
      // 58d2: getfield CarMaker.witho LSmenu;
      // 58d5: invokevirtual Smenu.show ()V
      // 58d8: aload 0
      // 58d9: ldc_w "     TEST DRIVE!     "
      // 58dc: sipush 350
      // 58df: sipush 505
      // 58e2: bipush 0
      // 58e3: bipush 1
      // 58e4: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 58e7: aload 0
      // 58e8: getfield CarMaker.tested Z
      // 58eb: ifeq 5aa7
      // 58ee: aload 0
      // 58ef: ldc_w "  Edit Stats & Class  "
      // 58f2: sipush 150
      // 58f5: sipush 471
      // 58f8: bipush 0
      // 58f9: bipush 0
      // 58fa: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 58fd: aload 0
      // 58fe: ldc_w "  Edit Physics  "
      // 5901: sipush 150
      // 5904: sipush 505
      // 5907: bipush 0
      // 5908: bipush 0
      // 5909: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 590c: aload 0
      // 590d: ldc_w "     Rate Car Handling     "
      // 5910: sipush 550
      // 5913: sipush 471
      // 5916: bipush 0
      // 5917: bipush 1
      // 5918: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 591b: goto 5aa7
      // 591e: aload 0
      // 591f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5922: new java/awt/Font
      // 5925: dup
      // 5926: ldc_w "Arial"
      // 5929: bipush 1
      // 592a: bipush 13
      // 592c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 592f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5932: aload 0
      // 5933: aload 0
      // 5934: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5937: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 593a: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 593d: aload 0
      // 593e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5941: new java/lang/StringBuilder
      // 5944: dup
      // 5945: invokespecial java/lang/StringBuilder.<init> ()V
      // 5948: ldc_w "Based on you test drive(s), how do your rate "
      // 594b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 594e: aload 0
      // 594f: getfield CarMaker.carname Ljava/lang/String;
      // 5952: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5955: ldc_w "'s handling?"
      // 5958: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 595b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 595e: sipush 350
      // 5961: aload 0
      // 5962: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5965: new java/lang/StringBuilder
      // 5968: dup
      // 5969: invokespecial java/lang/StringBuilder.<init> ()V
      // 596c: ldc_w "Based on your test drive(s), how do you rate "
      // 596f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5972: aload 0
      // 5973: getfield CarMaker.carname Ljava/lang/String;
      // 5976: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5979: ldc_w "'s handling?"
      // 597c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 597f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5982: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5985: bipush 2
      // 5986: idiv
      // 5987: isub
      // 5988: sipush 445
      // 598b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 598e: aload 0
      // 598f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5992: new java/awt/Font
      // 5995: dup
      // 5996: ldc_w "Arial"
      // 5999: bipush 1
      // 599a: bipush 12
      // 599c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 599f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 59a2: aload 0
      // 59a3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 59a6: ldc_w "Handling :"
      // 59a9: sipush 183
      // 59ac: sipush 483
      // 59af: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 59b2: bipush 0
      // 59b3: istore 6
      // 59b5: iload 6
      // 59b7: aload 0
      // 59b8: getfield CarMaker.handling I
      // 59bb: if_icmpge 59f2
      // 59be: aload 0
      // 59bf: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 59c2: iload 6
      // 59c4: i2f
      // 59c5: f2d
      // 59c6: ldc2_w 7.0E-4
      // 59c9: dmul
      // 59ca: d2f
      // 59cb: fconst_1
      // 59cc: fconst_1
      // 59cd: invokestatic java/awt/Color.getHSBColor (FFF)Ljava/awt/Color;
      // 59d0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 59d3: aload 0
      // 59d4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 59d7: sipush 250
      // 59da: iload 6
      // 59dc: iadd
      // 59dd: sipush 474
      // 59e0: sipush 250
      // 59e3: iload 6
      // 59e5: iadd
      // 59e6: sipush 482
      // 59e9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 59ec: iinc 6 1
      // 59ef: goto 59b5
      // 59f2: aload 0
      // 59f3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 59f6: new java/awt/Color
      // 59f9: dup
      // 59fa: bipush 0
      // 59fb: bipush 0
      // 59fc: bipush 0
      // 59fd: invokespecial java/awt/Color.<init> (III)V
      // 5a00: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5a03: aload 0
      // 5a04: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5a07: sipush 249
      // 5a0a: sipush 474
      // 5a0d: sipush 249
      // 5a10: sipush 482
      // 5a13: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5a16: aload 0
      // 5a17: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5a1a: sipush 450
      // 5a1d: sipush 474
      // 5a20: sipush 450
      // 5a23: sipush 482
      // 5a26: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5a29: aload 0
      // 5a2a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5a2d: sipush 249
      // 5a30: sipush 483
      // 5a33: sipush 450
      // 5a36: sipush 483
      // 5a39: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5a3c: bipush 0
      // 5a3d: istore 6
      // 5a3f: iload 6
      // 5a41: bipush 7
      // 5a43: if_icmpge 5a6b
      // 5a46: aload 0
      // 5a47: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5a4a: sipush 275
      // 5a4d: iload 6
      // 5a4f: bipush 25
      // 5a51: imul
      // 5a52: iadd
      // 5a53: sipush 482
      // 5a56: sipush 275
      // 5a59: iload 6
      // 5a5b: bipush 25
      // 5a5d: imul
      // 5a5e: iadd
      // 5a5f: sipush 478
      // 5a62: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 5a65: iinc 6 1
      // 5a68: goto 5a3f
      // 5a6b: aload 0
      // 5a6c: ldc_w " - "
      // 5a6f: sipush 480
      // 5a72: sipush 483
      // 5a75: bipush 4
      // 5a76: bipush 0
      // 5a77: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5a7a: aload 0
      // 5a7b: ldc_w " + "
      // 5a7e: sipush 520
      // 5a81: sipush 483
      // 5a84: bipush 4
      // 5a85: bipush 0
      // 5a86: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5a89: aload 0
      // 5a8a: ldc_w "       Save       "
      // 5a8d: sipush 388
      // 5a90: sipush 525
      // 5a93: bipush 0
      // 5a94: bipush 1
      // 5a95: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5a98: aload 0
      // 5a99: ldc_w " Cancel "
      // 5a9c: sipush 298
      // 5a9f: sipush 525
      // 5aa2: bipush 0
      // 5aa3: bipush 0
      // 5aa4: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5aa7: aload 0
      // 5aa8: getfield CarMaker.polynum I
      // 5aab: iflt 5c35
      // 5aae: aload 0
      // 5aaf: getfield CarMaker.cntpls I
      // 5ab2: ifle 5c35
      // 5ab5: bipush 0
      // 5ab6: istore 6
      // 5ab8: iload 6
      // 5aba: aload 0
      // 5abb: getfield CarMaker.o LContO;
      // 5abe: getfield ContO.npl I
      // 5ac1: if_icmpge 5b8b
      // 5ac4: iload 6
      // 5ac6: aload 0
      // 5ac7: getfield CarMaker.polynum I
      // 5aca: if_icmplt 5b53
      // 5acd: iload 6
      // 5acf: aload 0
      // 5ad0: getfield CarMaker.polynum I
      // 5ad3: aload 0
      // 5ad4: getfield CarMaker.cntpls I
      // 5ad7: iadd
      // 5ad8: if_icmpge 5b53
      // 5adb: aload 0
      // 5adc: getfield CarMaker.pflk Z
      // 5adf: ifeq 5af5
      // 5ae2: aload 0
      // 5ae3: getfield CarMaker.o LContO;
      // 5ae6: getfield ContO.p [LPlane;
      // 5ae9: iload 6
      // 5aeb: aaload
      // 5aec: getfield Plane.hsb [F
      // 5aef: bipush 2
      // 5af0: fconst_1
      // 5af1: fastore
      // 5af2: goto 5b85
      // 5af5: aload 0
      // 5af6: getfield CarMaker.o LContO;
      // 5af9: getfield ContO.p [LPlane;
      // 5afc: iload 6
      // 5afe: aaload
      // 5aff: getfield Plane.hsb [F
      // 5b02: bipush 2
      // 5b03: fconst_0
      // 5b04: fastore
      // 5b05: aload 0
      // 5b06: getfield CarMaker.o LContO;
      // 5b09: getfield ContO.p [LPlane;
      // 5b0c: iload 6
      // 5b0e: aaload
      // 5b0f: getfield Plane.hsb [F
      // 5b12: bipush 0
      // 5b13: ldc 0.5
      // 5b15: aload 0
      // 5b16: getfield CarMaker.o LContO;
      // 5b19: getfield ContO.p [LPlane;
      // 5b1c: iload 6
      // 5b1e: aaload
      // 5b1f: getfield Plane.hsb [F
      // 5b22: bipush 0
      // 5b23: faload
      // 5b24: fsub
      // 5b25: invokestatic java/lang/Math.abs (F)F
      // 5b28: fastore
      // 5b29: aload 0
      // 5b2a: getfield CarMaker.o LContO;
      // 5b2d: getfield ContO.p [LPlane;
      // 5b30: iload 6
      // 5b32: aaload
      // 5b33: getfield Plane.hsb [F
      // 5b36: bipush 0
      // 5b37: faload
      // 5b38: fconst_1
      // 5b39: fcmpl
      // 5b3a: ifle 5b85
      // 5b3d: aload 0
      // 5b3e: getfield CarMaker.o LContO;
      // 5b41: getfield ContO.p [LPlane;
      // 5b44: iload 6
      // 5b46: aaload
      // 5b47: getfield Plane.hsb [F
      // 5b4a: bipush 0
      // 5b4b: dup2
      // 5b4c: faload
      // 5b4d: fconst_1
      // 5b4e: fsub
      // 5b4f: fastore
      // 5b50: goto 5b29
      // 5b53: aload 0
      // 5b54: getfield CarMaker.prflk I
      // 5b57: bipush 6
      // 5b59: if_icmple 5b77
      // 5b5c: aload 0
      // 5b5d: getfield CarMaker.prflk I
      // 5b60: bipush 20
      // 5b62: if_icmpge 5b77
      // 5b65: aload 0
      // 5b66: getfield CarMaker.o LContO;
      // 5b69: getfield ContO.p [LPlane;
      // 5b6c: iload 6
      // 5b6e: aaload
      // 5b6f: bipush -13
      // 5b71: putfield Plane.gr I
      // 5b74: goto 5b85
      // 5b77: aload 0
      // 5b78: getfield CarMaker.o LContO;
      // 5b7b: getfield ContO.p [LPlane;
      // 5b7e: iload 6
      // 5b80: aaload
      // 5b81: bipush 1
      // 5b82: putfield Plane.gr I
      // 5b85: iinc 6 1
      // 5b88: goto 5ab8
      // 5b8b: aload 0
      // 5b8c: getfield CarMaker.pflk Z
      // 5b8f: ifeq 5b9a
      // 5b92: aload 0
      // 5b93: bipush 0
      // 5b94: putfield CarMaker.pflk Z
      // 5b97: goto 5b9f
      // 5b9a: aload 0
      // 5b9b: bipush 1
      // 5b9c: putfield CarMaker.pflk Z
      // 5b9f: aload 0
      // 5ba0: getfield CarMaker.prflk I
      // 5ba3: bipush 40
      // 5ba5: if_icmpge 5bb2
      // 5ba8: aload 0
      // 5ba9: dup
      // 5baa: getfield CarMaker.prflk I
      // 5bad: bipush 1
      // 5bae: iadd
      // 5baf: putfield CarMaker.prflk I
      // 5bb2: aload 0
      // 5bb3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5bb6: new java/awt/Font
      // 5bb9: dup
      // 5bba: ldc_w "Arial"
      // 5bbd: bipush 1
      // 5bbe: bipush 12
      // 5bc0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5bc3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5bc6: aload 0
      // 5bc7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5bca: new java/awt/Color
      // 5bcd: dup
      // 5bce: bipush 0
      // 5bcf: bipush 0
      // 5bd0: bipush 0
      // 5bd1: invokespecial java/awt/Color.<init> (III)V
      // 5bd4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5bd7: aload 0
      // 5bd8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5bdb: new java/lang/StringBuilder
      // 5bde: dup
      // 5bdf: invokespecial java/lang/StringBuilder.<init> ()V
      // 5be2: ldc_w "[ Showing "
      // 5be5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5be8: aload 0
      // 5be9: getfield CarMaker.cntpls I
      // 5bec: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5bef: ldc_w " Polygons Selected ]"
      // 5bf2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5bf5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5bf8: sipush 350
      // 5bfb: aload 0
      // 5bfc: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5bff: new java/lang/StringBuilder
      // 5c02: dup
      // 5c03: invokespecial java/lang/StringBuilder.<init> ()V
      // 5c06: ldc_w "[ Showing "
      // 5c09: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5c0c: aload 0
      // 5c0d: getfield CarMaker.cntpls I
      // 5c10: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5c13: ldc_w " Polygons Selected ]"
      // 5c16: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5c19: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5c1c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5c1f: bipush 2
      // 5c20: idiv
      // 5c21: isub
      // 5c22: bipush 45
      // 5c24: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5c27: aload 0
      // 5c28: ldc_w "  Stop  "
      // 5c2b: sipush 350
      // 5c2e: bipush 67
      // 5c30: bipush 5
      // 5c31: bipush 0
      // 5c32: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5c35: bipush 50
      // 5c37: istore 2
      // 5c38: aload 0
      // 5c39: getfield CarMaker.rotr Z
      // 5c3c: ifeq 5c4f
      // 5c3f: aload 0
      // 5c40: getfield CarMaker.o LContO;
      // 5c43: dup
      // 5c44: getfield ContO.xz I
      // 5c47: bipush 5
      // 5c48: isub
      // 5c49: putfield ContO.xz I
      // 5c4c: bipush 15
      // 5c4e: istore 2
      // 5c4f: aload 0
      // 5c50: getfield CarMaker.rotl Z
      // 5c53: ifeq 5c66
      // 5c56: aload 0
      // 5c57: getfield CarMaker.o LContO;
      // 5c5a: dup
      // 5c5b: getfield ContO.xz I
      // 5c5e: bipush 5
      // 5c5f: iadd
      // 5c60: putfield ContO.xz I
      // 5c63: bipush 15
      // 5c65: istore 2
      // 5c66: aload 0
      // 5c67: getfield CarMaker.left Z
      // 5c6a: ifeq 5c7d
      // 5c6d: aload 0
      // 5c6e: getfield CarMaker.o LContO;
      // 5c71: dup
      // 5c72: getfield ContO.xy I
      // 5c75: bipush 5
      // 5c76: isub
      // 5c77: putfield ContO.xy I
      // 5c7a: bipush 15
      // 5c7c: istore 2
      // 5c7d: aload 0
      // 5c7e: getfield CarMaker.right Z
      // 5c81: ifeq 5c94
      // 5c84: aload 0
      // 5c85: getfield CarMaker.o LContO;
      // 5c88: dup
      // 5c89: getfield ContO.xy I
      // 5c8c: bipush 5
      // 5c8d: iadd
      // 5c8e: putfield ContO.xy I
      // 5c91: bipush 15
      // 5c93: istore 2
      // 5c94: aload 0
      // 5c95: getfield CarMaker.up Z
      // 5c98: ifeq 5cab
      // 5c9b: aload 0
      // 5c9c: getfield CarMaker.o LContO;
      // 5c9f: dup
      // 5ca0: getfield ContO.zy I
      // 5ca3: bipush 5
      // 5ca4: isub
      // 5ca5: putfield ContO.zy I
      // 5ca8: bipush 15
      // 5caa: istore 2
      // 5cab: aload 0
      // 5cac: getfield CarMaker.down Z
      // 5caf: ifeq 5cc2
      // 5cb2: aload 0
      // 5cb3: getfield CarMaker.o LContO;
      // 5cb6: dup
      // 5cb7: getfield ContO.zy I
      // 5cba: bipush 5
      // 5cbb: iadd
      // 5cbc: putfield ContO.zy I
      // 5cbf: bipush 15
      // 5cc1: istore 2
      // 5cc2: aload 0
      // 5cc3: getfield CarMaker.plus Z
      // 5cc6: ifeq 5cd9
      // 5cc9: aload 0
      // 5cca: getfield CarMaker.o LContO;
      // 5ccd: dup
      // 5cce: getfield ContO.y I
      // 5cd1: bipush 5
      // 5cd2: iadd
      // 5cd3: putfield ContO.y I
      // 5cd6: bipush 15
      // 5cd8: istore 2
      // 5cd9: aload 0
      // 5cda: getfield CarMaker.minus Z
      // 5cdd: ifeq 5cf0
      // 5ce0: aload 0
      // 5ce1: getfield CarMaker.o LContO;
      // 5ce4: dup
      // 5ce5: getfield ContO.y I
      // 5ce8: bipush 5
      // 5ce9: isub
      // 5cea: putfield ContO.y I
      // 5ced: bipush 15
      // 5cef: istore 2
      // 5cf0: aload 0
      // 5cf1: getfield CarMaker.in Z
      // 5cf4: ifeq 5d08
      // 5cf7: aload 0
      // 5cf8: getfield CarMaker.o LContO;
      // 5cfb: dup
      // 5cfc: getfield ContO.z I
      // 5cff: bipush 10
      // 5d01: iadd
      // 5d02: putfield ContO.z I
      // 5d05: bipush 15
      // 5d07: istore 2
      // 5d08: aload 0
      // 5d09: getfield CarMaker.out Z
      // 5d0c: ifeq 5d20
      // 5d0f: aload 0
      // 5d10: getfield CarMaker.o LContO;
      // 5d13: dup
      // 5d14: getfield ContO.z I
      // 5d17: bipush 10
      // 5d19: isub
      // 5d1a: putfield ContO.z I
      // 5d1d: bipush 15
      // 5d1f: istore 2
      // 5d20: aload 0
      // 5d21: aload 0
      // 5d22: getfield CarMaker.o LContO;
      // 5d25: getfield ContO.x I
      // 5d28: putfield CarMaker.ox I
      // 5d2b: aload 0
      // 5d2c: aload 0
      // 5d2d: getfield CarMaker.o LContO;
      // 5d30: getfield ContO.y I
      // 5d33: putfield CarMaker.oy I
      // 5d36: aload 0
      // 5d37: aload 0
      // 5d38: getfield CarMaker.o LContO;
      // 5d3b: getfield ContO.z I
      // 5d3e: putfield CarMaker.oz I
      // 5d41: aload 0
      // 5d42: aload 0
      // 5d43: getfield CarMaker.o LContO;
      // 5d46: getfield ContO.xz I
      // 5d49: putfield CarMaker.oxz I
      // 5d4c: aload 0
      // 5d4d: aload 0
      // 5d4e: getfield CarMaker.o LContO;
      // 5d51: getfield ContO.xy I
      // 5d54: putfield CarMaker.oxy I
      // 5d57: aload 0
      // 5d58: aload 0
      // 5d59: getfield CarMaker.o LContO;
      // 5d5c: getfield ContO.zy I
      // 5d5f: putfield CarMaker.ozy I
      // 5d62: aload 0
      // 5d63: getfield CarMaker.dtabed I
      // 5d66: aload 0
      // 5d67: getfield CarMaker.dtab I
      // 5d6a: if_icmpeq 5d75
      // 5d6d: aload 0
      // 5d6e: aload 0
      // 5d6f: getfield CarMaker.dtab I
      // 5d72: putfield CarMaker.dtabed I
      // 5d75: aload 0
      // 5d76: getfield CarMaker.dtab I
      // 5d79: bipush 5
      // 5d7a: if_icmpne 5d98
      // 5d7d: aload 0
      // 5d7e: getfield CarMaker.pfase I
      // 5d81: bipush -1
      // 5d82: if_icmpne 5d98
      // 5d85: aload 0
      // 5d86: invokevirtual CarMaker.repaint ()V
      // 5d89: aconst_null
      // 5d8a: ldc_w "Car Wheels not defined or not defined correctly.\nBefore defining the car Physics car Wheels must be defined correctly!\nPlease go to the ‘Wheels’ tab and use  [ Apply ]  and  [ Save ]  to define correctly.\n"
      // 5d8d: ldc "Car Maker"
      // 5d8f: bipush 1
      // 5d90: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 5d93: aload 0
      // 5d94: bipush 3
      // 5d95: putfield CarMaker.dtab I
      // 5d98: aload 0
      // 5d99: getfield CarMaker.tab I
      // 5d9c: bipush 3
      // 5d9d: if_icmpne 7097
      // 5da0: aload 0
      // 5da1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5da4: new java/awt/Font
      // 5da7: dup
      // 5da8: ldc_w "Arial"
      // 5dab: bipush 1
      // 5dac: bipush 13
      // 5dae: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5db1: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5db4: aload 0
      // 5db5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5db8: new java/awt/Color
      // 5dbb: dup
      // 5dbc: bipush 0
      // 5dbd: bipush 0
      // 5dbe: bipush 0
      // 5dbf: invokespecial java/awt/Color.<init> (III)V
      // 5dc2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5dc5: aload 0
      // 5dc6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5dc9: new java/lang/StringBuilder
      // 5dcc: dup
      // 5dcd: invokespecial java/lang/StringBuilder.<init> ()V
      // 5dd0: ldc_w "Publish Car :  [ "
      // 5dd3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5dd6: aload 0
      // 5dd7: getfield CarMaker.carname Ljava/lang/String;
      // 5dda: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5ddd: ldc_w " ]"
      // 5de0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5de3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5de6: bipush 30
      // 5de8: bipush 50
      // 5dea: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ded: aload 0
      // 5dee: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5df1: ldc_w "Publishing Type :"
      // 5df4: bipush 30
      // 5df6: bipush 80
      // 5df8: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5dfb: aload 0
      // 5dfc: getfield CarMaker.pubtyp LSmenu;
      // 5dff: sipush 150
      // 5e02: bipush 63
      // 5e04: invokevirtual Smenu.move (II)V
      // 5e07: aload 0
      // 5e08: getfield CarMaker.pubtyp LSmenu;
      // 5e0b: invokevirtual Smenu.isShowing ()Z
      // 5e0e: ifne 5e20
      // 5e11: aload 0
      // 5e12: getfield CarMaker.pubtyp LSmenu;
      // 5e15: invokevirtual Smenu.show ()V
      // 5e18: aload 0
      // 5e19: getfield CarMaker.pubtyp LSmenu;
      // 5e1c: bipush 1
      // 5e1d: invokevirtual Smenu.select (I)V
      // 5e20: aload 0
      // 5e21: ldc_w "       Publish  >       "
      // 5e24: bipush 102
      // 5e26: bipush 110
      // 5e28: bipush 0
      // 5e29: bipush 1
      // 5e2a: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 5e2d: aload 0
      // 5e2e: getfield CarMaker.pubitem LSmenu;
      // 5e31: sipush 690
      // 5e34: aload 0
      // 5e35: getfield CarMaker.pubitem LSmenu;
      // 5e38: getfield Smenu.w I
      // 5e3b: isub
      // 5e3c: bipush 96
      // 5e3e: invokevirtual Smenu.move (II)V
      // 5e41: aload 0
      // 5e42: getfield CarMaker.pubitem LSmenu;
      // 5e45: invokevirtual Smenu.isShowing ()Z
      // 5e48: ifne 5e52
      // 5e4b: aload 0
      // 5e4c: getfield CarMaker.pubitem LSmenu;
      // 5e4f: invokevirtual Smenu.show ()V
      // 5e52: aload 0
      // 5e53: getfield CarMaker.pubitem LSmenu;
      // 5e56: getfield Smenu.sel I
      // 5e59: ifeq 5e8f
      // 5e5c: bipush 0
      // 5e5d: istore 3
      // 5e5e: bipush 0
      // 5e5f: istore 4
      // 5e61: iload 4
      // 5e63: aload 0
      // 5e64: getfield CarMaker.nmc I
      // 5e67: if_icmpge 5e86
      // 5e6a: aload 0
      // 5e6b: getfield CarMaker.pubitem LSmenu;
      // 5e6e: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 5e71: aload 0
      // 5e72: getfield CarMaker.mycars [Ljava/lang/String;
      // 5e75: iload 4
      // 5e77: aaload
      // 5e78: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5e7b: ifeq 5e80
      // 5e7e: bipush 1
      // 5e7f: istore 3
      // 5e80: iinc 4 1
      // 5e83: goto 5e61
      // 5e86: iload 3
      // 5e87: ifne 5e8f
      // 5e8a: aload 0
      // 5e8b: bipush 2
      // 5e8c: putfield CarMaker.logged I
      // 5e8f: aload 0
      // 5e90: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5e93: new java/awt/Color
      // 5e96: dup
      // 5e97: bipush 0
      // 5e98: bipush 0
      // 5e99: bipush 0
      // 5e9a: invokespecial java/awt/Color.<init> (III)V
      // 5e9d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5ea0: aload 0
      // 5ea1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5ea4: new java/awt/Font
      // 5ea7: dup
      // 5ea8: ldc_w "Arial"
      // 5eab: bipush 0
      // 5eac: bipush 12
      // 5eae: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5eb1: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5eb4: aload 0
      // 5eb5: getfield CarMaker.pubtyp LSmenu;
      // 5eb8: invokevirtual Smenu.getSelectedIndex ()I
      // 5ebb: ifne 5edc
      // 5ebe: aload 0
      // 5ebf: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5ec2: ldc_w "Private :  This means only you can use your car and no one else can add"
      // 5ec5: sipush 268
      // 5ec8: bipush 72
      // 5eca: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ecd: aload 0
      // 5ece: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5ed1: ldc_w "it to their account to play with it !"
      // 5ed4: sipush 268
      // 5ed7: bipush 88
      // 5ed9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5edc: aload 0
      // 5edd: getfield CarMaker.pubtyp LSmenu;
      // 5ee0: invokevirtual Smenu.getSelectedIndex ()I
      // 5ee3: bipush 1
      // 5ee4: if_icmpne 5f05
      // 5ee7: aload 0
      // 5ee8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5eeb: ldc_w "Public :  This means anyone can add this car to their account to play with it,"
      // 5eee: sipush 268
      // 5ef1: bipush 72
      // 5ef3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ef6: aload 0
      // 5ef7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5efa: ldc_w "but only you can download it to your Car Maker (no one else can get it’s code)."
      // 5efd: sipush 268
      // 5f00: bipush 88
      // 5f02: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f05: aload 0
      // 5f06: getfield CarMaker.pubtyp LSmenu;
      // 5f09: invokevirtual Smenu.getSelectedIndex ()I
      // 5f0c: bipush 2
      // 5f0d: if_icmpne 5f2e
      // 5f10: aload 0
      // 5f11: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f14: ldc_w "Super Public :  This means anyone can add this car to their account to play"
      // 5f17: sipush 268
      // 5f1a: bipush 72
      // 5f1c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f1f: aload 0
      // 5f20: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f23: ldc_w "with it and anyone can also download it to their Car Maker to get its code."
      // 5f26: sipush 268
      // 5f29: bipush 88
      // 5f2b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f2e: aload 0
      // 5f2f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f32: new java/awt/Font
      // 5f35: dup
      // 5f36: ldc_w "Arial"
      // 5f39: bipush 1
      // 5f3a: bipush 12
      // 5f3c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5f3f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5f42: aload 0
      // 5f43: aload 0
      // 5f44: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f47: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5f4a: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5f4d: aload 0
      // 5f4e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f51: ldc_w "Car Name"
      // 5f54: bipush 80
      // 5f56: aload 0
      // 5f57: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5f5a: ldc_w "Car Name"
      // 5f5d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5f60: bipush 2
      // 5f61: idiv
      // 5f62: isub
      // 5f63: sipush 138
      // 5f66: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f69: aload 0
      // 5f6a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f6d: ldc_w "Car Class"
      // 5f70: sipush 200
      // 5f73: aload 0
      // 5f74: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5f77: ldc_w "Car Class"
      // 5f7a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5f7d: bipush 2
      // 5f7e: idiv
      // 5f7f: isub
      // 5f80: sipush 138
      // 5f83: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f86: aload 0
      // 5f87: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5f8a: ldc_w "Created By"
      // 5f8d: sipush 300
      // 5f90: aload 0
      // 5f91: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5f94: ldc_w "Created By"
      // 5f97: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5f9a: bipush 2
      // 5f9b: idiv
      // 5f9c: isub
      // 5f9d: sipush 138
      // 5fa0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5fa3: aload 0
      // 5fa4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5fa7: ldc_w "Added By"
      // 5faa: sipush 400
      // 5fad: aload 0
      // 5fae: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5fb1: ldc_w "Added By"
      // 5fb4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5fb7: bipush 2
      // 5fb8: idiv
      // 5fb9: isub
      // 5fba: sipush 138
      // 5fbd: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5fc0: aload 0
      // 5fc1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5fc4: ldc_w "Publish Type"
      // 5fc7: sipush 500
      // 5fca: aload 0
      // 5fcb: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5fce: ldc_w "Publish Type"
      // 5fd1: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5fd4: bipush 2
      // 5fd5: idiv
      // 5fd6: isub
      // 5fd7: sipush 138
      // 5fda: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5fdd: aload 0
      // 5fde: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5fe1: ldc_w "Options"
      // 5fe4: sipush 620
      // 5fe7: aload 0
      // 5fe8: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 5feb: ldc_w "Options"
      // 5fee: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5ff1: bipush 2
      // 5ff2: idiv
      // 5ff3: isub
      // 5ff4: sipush 138
      // 5ff7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ffa: aload 0
      // 5ffb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 5ffe: sipush 150
      // 6001: sipush 129
      // 6004: sipush 150
      // 6007: sipush 140
      // 600a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 600d: aload 0
      // 600e: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6011: sipush 250
      // 6014: sipush 129
      // 6017: sipush 250
      // 601a: sipush 140
      // 601d: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6020: aload 0
      // 6021: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6024: sipush 350
      // 6027: sipush 129
      // 602a: sipush 350
      // 602d: sipush 140
      // 6030: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6033: aload 0
      // 6034: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6037: sipush 450
      // 603a: sipush 129
      // 603d: sipush 450
      // 6040: sipush 140
      // 6043: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6046: aload 0
      // 6047: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 604a: sipush 550
      // 604d: sipush 129
      // 6050: sipush 550
      // 6053: sipush 140
      // 6056: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6059: aload 0
      // 605a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 605d: bipush 10
      // 605f: sipush 140
      // 6062: sipush 680
      // 6065: sipush 402
      // 6068: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 606b: aload 0
      // 606c: getfield CarMaker.logged I
      // 606f: ifne 61d7
      // 6072: aload 0
      // 6073: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6076: new java/awt/Font
      // 6079: dup
      // 607a: ldc_w "Arial"
      // 607d: bipush 1
      // 607e: bipush 13
      // 6080: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6083: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6086: aload 0
      // 6087: aload 0
      // 6088: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 608b: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 608e: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6091: aload 0
      // 6092: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6095: ldc_w "Login to Retrieve your Account Cars"
      // 6098: sipush 350
      // 609b: aload 0
      // 609c: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 609f: ldc_w "Login to Retrieve your Account Cars"
      // 60a2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 60a5: bipush 2
      // 60a6: idiv
      // 60a7: isub
      // 60a8: sipush 220
      // 60ab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 60ae: aload 0
      // 60af: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 60b2: ldc_w "Nickname:"
      // 60b5: sipush 326
      // 60b8: aload 0
      // 60b9: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 60bc: ldc_w "Nickname:"
      // 60bf: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 60c2: isub
      // 60c3: bipush 14
      // 60c5: isub
      // 60c6: sipush 266
      // 60c9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 60cc: aload 0
      // 60cd: aload 0
      // 60ce: getfield CarMaker.tnick Ljava/awt/TextField;
      // 60d1: sipush 326
      // 60d4: sipush 250
      // 60d7: sipush 129
      // 60da: bipush 22
      // 60dc: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 60df: aload 0
      // 60e0: getfield CarMaker.tnick Ljava/awt/TextField;
      // 60e3: invokevirtual java/awt/TextField.isShowing ()Z
      // 60e6: ifne 60f0
      // 60e9: aload 0
      // 60ea: getfield CarMaker.tnick Ljava/awt/TextField;
      // 60ed: invokevirtual java/awt/TextField.show ()V
      // 60f0: aload 0
      // 60f1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 60f4: ldc_w "Password:"
      // 60f7: sipush 326
      // 60fa: aload 0
      // 60fb: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 60fe: ldc_w "Password:"
      // 6101: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6104: isub
      // 6105: bipush 14
      // 6107: isub
      // 6108: sipush 296
      // 610b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 610e: aload 0
      // 610f: aload 0
      // 6110: getfield CarMaker.tpass Ljava/awt/TextField;
      // 6113: sipush 326
      // 6116: sipush 280
      // 6119: sipush 129
      // 611c: bipush 22
      // 611e: invokevirtual CarMaker.movefield (Ljava/awt/Component;IIII)V
      // 6121: aload 0
      // 6122: getfield CarMaker.tpass Ljava/awt/TextField;
      // 6125: invokevirtual java/awt/TextField.isShowing ()Z
      // 6128: ifne 6132
      // 612b: aload 0
      // 612c: getfield CarMaker.tpass Ljava/awt/TextField;
      // 612f: invokevirtual java/awt/TextField.show ()V
      // 6132: aload 0
      // 6133: ldc_w "       Login       "
      // 6136: sipush 350
      // 6139: sipush 340
      // 613c: bipush 0
      // 613d: bipush 1
      // 613e: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 6141: aload 0
      // 6142: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6145: new java/awt/Font
      // 6148: dup
      // 6149: ldc_w "Arial"
      // 614c: bipush 1
      // 614d: bipush 13
      // 614f: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6152: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6155: aload 0
      // 6156: aload 0
      // 6157: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 615a: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 615d: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6160: aload 0
      // 6161: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6164: ldc_w "Register a full account or if you have a trial account upgrade it!"
      // 6167: sipush 350
      // 616a: aload 0
      // 616b: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 616e: ldc_w "Register a full account or if you have a trial account upgrade it!"
      // 6171: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6174: bipush 2
      // 6175: idiv
      // 6176: isub
      // 6177: sipush 450
      // 617a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 617d: aload 0
      // 617e: ldc_w "   Register!   "
      // 6181: sipush 290
      // 6184: sipush 480
      // 6187: bipush 0
      // 6188: bipush 1
      // 6189: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 618c: aload 0
      // 618d: ldc_w "   Upgrade!   "
      // 6190: sipush 410
      // 6193: sipush 480
      // 6196: bipush 0
      // 6197: bipush 1
      // 6198: invokevirtual CarMaker.stringbutton (Ljava/lang/String;IIIZ)V
      // 619b: aload 0
      // 619c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 619f: new java/awt/Font
      // 61a2: dup
      // 61a3: ldc_w "Arial"
      // 61a6: bipush 0
      // 61a7: bipush 12
      // 61a9: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 61ac: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 61af: aload 0
      // 61b0: aload 0
      // 61b1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 61b4: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 61b7: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 61ba: aload 0
      // 61bb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 61be: ldc_w "You need a full account to publish your cars to the multiplayer game!"
      // 61c1: sipush 350
      // 61c4: aload 0
      // 61c5: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 61c8: ldc_w "You need a full account to publish your cars to the multiplayer game!"
      // 61cb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 61ce: bipush 2
      // 61cf: idiv
      // 61d0: isub
      // 61d1: sipush 505
      // 61d4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 61d7: aload 0
      // 61d8: getfield CarMaker.logged I
      // 61db: bipush -1
      // 61dc: if_icmpne 626c
      // 61df: aload 0
      // 61e0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 61e3: new java/awt/Font
      // 61e6: dup
      // 61e7: ldc_w "Arial"
      // 61ea: bipush 1
      // 61eb: bipush 13
      // 61ed: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 61f0: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 61f3: aload 0
      // 61f4: aload 0
      // 61f5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 61f8: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 61fb: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 61fe: aload 0
      // 61ff: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6202: ldc_w "Ready to publish..."
      // 6205: sipush 350
      // 6208: aload 0
      // 6209: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 620c: ldc_w "Ready to publish..."
      // 620f: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6212: bipush 2
      // 6213: idiv
      // 6214: isub
      // 6215: sipush 220
      // 6218: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 621b: aload 0
      // 621c: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 621f: new java/lang/StringBuilder
      // 6222: dup
      // 6223: invokespecial java/lang/StringBuilder.<init> ()V
      // 6226: ldc_w "Click ‘Publish’ above to add car: '"
      // 6229: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 622c: aload 0
      // 622d: getfield CarMaker.carname Ljava/lang/String;
      // 6230: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6233: ldc_w "'."
      // 6236: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6239: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 623c: sipush 350
      // 623f: aload 0
      // 6240: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6243: new java/lang/StringBuilder
      // 6246: dup
      // 6247: invokespecial java/lang/StringBuilder.<init> ()V
      // 624a: ldc_w "Click ‘Publish’ above to add car: '"
      // 624d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6250: aload 0
      // 6251: getfield CarMaker.carname Ljava/lang/String;
      // 6254: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6257: ldc_w "'."
      // 625a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 625d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6260: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6263: bipush 2
      // 6264: idiv
      // 6265: isub
      // 6266: sipush 280
      // 6269: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 626c: aload 0
      // 626d: getfield CarMaker.logged I
      // 6270: bipush 2
      // 6271: if_icmpne 64bf
      // 6274: aload 0
      // 6275: getfield CarMaker.mycars [Ljava/lang/String;
      // 6278: aload 0
      // 6279: getfield CarMaker.roto I
      // 627c: aload 0
      // 627d: getfield CarMaker.pubitem LSmenu;
      // 6280: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 6283: aastore
      // 6284: aload 0
      // 6285: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6288: new java/awt/Font
      // 628b: dup
      // 628c: ldc_w "Arial"
      // 628f: bipush 1
      // 6290: bipush 13
      // 6292: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6295: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6298: aload 0
      // 6299: aload 0
      // 629a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 629d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 62a0: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 62a3: aload 0
      // 62a4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 62a7: new java/awt/Color
      // 62aa: dup
      // 62ab: sipush 225
      // 62ae: sipush 225
      // 62b1: sipush 225
      // 62b4: invokespecial java/awt/Color.<init> (III)V
      // 62b7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 62ba: aload 0
      // 62bb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 62be: bipush 50
      // 62c0: sipush 150
      // 62c3: sipush 600
      // 62c6: sipush 150
      // 62c9: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 62cc: aload 0
      // 62cd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 62d0: new java/awt/Color
      // 62d3: dup
      // 62d4: bipush 0
      // 62d5: bipush 0
      // 62d6: bipush 0
      // 62d7: invokespecial java/awt/Color.<init> (III)V
      // 62da: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 62dd: aload 0
      // 62de: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 62e1: new java/lang/StringBuilder
      // 62e4: dup
      // 62e5: invokespecial java/lang/StringBuilder.<init> ()V
      // 62e8: ldc_w "Loading "
      // 62eb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 62ee: aload 0
      // 62ef: getfield CarMaker.mycars [Ljava/lang/String;
      // 62f2: aload 0
      // 62f3: getfield CarMaker.roto I
      // 62f6: aaload
      // 62f7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 62fa: ldc_w "‘s info..."
      // 62fd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6300: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6303: sipush 350
      // 6306: aload 0
      // 6307: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 630a: new java/lang/StringBuilder
      // 630d: dup
      // 630e: invokespecial java/lang/StringBuilder.<init> ()V
      // 6311: ldc_w "Loading "
      // 6314: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6317: aload 0
      // 6318: getfield CarMaker.mycars [Ljava/lang/String;
      // 631b: aload 0
      // 631c: getfield CarMaker.roto I
      // 631f: aaload
      // 6320: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6323: ldc_w "‘s info..."
      // 6326: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6329: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 632c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 632f: bipush 2
      // 6330: idiv
      // 6331: isub
      // 6332: sipush 220
      // 6335: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6338: aload 0
      // 6339: invokevirtual CarMaker.repaint ()V
      // 633c: aload 0
      // 633d: getfield CarMaker.maker [Ljava/lang/String;
      // 6340: aload 0
      // 6341: getfield CarMaker.roto I
      // 6344: ldc_w "Unkown"
      // 6347: aastore
      // 6348: aload 0
      // 6349: getfield CarMaker.pubt [I
      // 634c: aload 0
      // 634d: getfield CarMaker.roto I
      // 6350: bipush -1
      // 6351: iastore
      // 6352: aload 0
      // 6353: getfield CarMaker.clas [I
      // 6356: aload 0
      // 6357: getfield CarMaker.roto I
      // 635a: bipush 0
      // 635b: iastore
      // 635c: aload 0
      // 635d: getfield CarMaker.nad [I
      // 6360: aload 0
      // 6361: getfield CarMaker.roto I
      // 6364: bipush 0
      // 6365: iastore
      // 6366: ldc ""
      // 6368: astore 3
      // 6369: new java/lang/StringBuilder
      // 636c: dup
      // 636d: invokespecial java/lang/StringBuilder.<init> ()V
      // 6370: ldc_w "http://multiplayer.needformadness.com/cars/"
      // 6373: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6376: aload 0
      // 6377: getfield CarMaker.mycars [Ljava/lang/String;
      // 637a: aload 0
      // 637b: getfield CarMaker.roto I
      // 637e: aaload
      // 637f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6382: ldc_w ".txt?reqlo="
      // 6385: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6388: invokestatic java/lang/Math.random ()D
      // 638b: ldc2_w 1000.0
      // 638e: dmul
      // 638f: d2i
      // 6390: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6393: ldc ""
      // 6395: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6398: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 639b: astore 4
      // 639d: aload 4
      // 639f: bipush 32
      // 63a1: bipush 95
      // 63a3: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // 63a6: astore 4
      // 63a8: new java/net/URL
      // 63ab: dup
      // 63ac: aload 4
      // 63ae: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // 63b1: astore 5
      // 63b3: new java/io/DataInputStream
      // 63b6: dup
      // 63b7: aload 5
      // 63b9: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // 63bc: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 63bf: astore 6
      // 63c1: aload 6
      // 63c3: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // 63c6: dup
      // 63c7: astore 3
      // 63c8: ifnull 6484
      // 63cb: new java/lang/StringBuilder
      // 63ce: dup
      // 63cf: invokespecial java/lang/StringBuilder.<init> ()V
      // 63d2: ldc ""
      // 63d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 63d7: aload 3
      // 63d8: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 63db: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 63de: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 63e1: astore 3
      // 63e2: aload 3
      // 63e3: ldc_w "details"
      // 63e6: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 63e9: ifeq 63c1
      // 63ec: aload 0
      // 63ed: getfield CarMaker.maker [Ljava/lang/String;
      // 63f0: aload 0
      // 63f1: getfield CarMaker.roto I
      // 63f4: aload 0
      // 63f5: ldc_w "details"
      // 63f8: aload 3
      // 63f9: bipush 0
      // 63fa: invokevirtual CarMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // 63fd: aastore
      // 63fe: aload 0
      // 63ff: getfield CarMaker.pubt [I
      // 6402: aload 0
      // 6403: getfield CarMaker.roto I
      // 6406: aload 0
      // 6407: ldc_w "details"
      // 640a: aload 3
      // 640b: bipush 1
      // 640c: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 640f: iastore
      // 6410: aload 0
      // 6411: getfield CarMaker.clas [I
      // 6414: aload 0
      // 6415: getfield CarMaker.roto I
      // 6418: aload 0
      // 6419: ldc_w "details"
      // 641c: aload 3
      // 641d: bipush 2
      // 641e: invokevirtual CarMaker.getvalue (Ljava/lang/String;Ljava/lang/String;I)I
      // 6421: iastore
      // 6422: bipush 0
      // 6423: istore 7
      // 6425: iload 7
      // 6427: ifne 6481
      // 642a: aload 0
      // 642b: getfield CarMaker.addeda [[Ljava/lang/String;
      // 642e: aload 0
      // 642f: getfield CarMaker.roto I
      // 6432: aaload
      // 6433: aload 0
      // 6434: getfield CarMaker.nad [I
      // 6437: aload 0
      // 6438: getfield CarMaker.roto I
      // 643b: iaload
      // 643c: aload 0
      // 643d: ldc_w "details"
      // 6440: aload 3
      // 6441: bipush 3
      // 6442: aload 0
      // 6443: getfield CarMaker.nad [I
      // 6446: aload 0
      // 6447: getfield CarMaker.roto I
      // 644a: iaload
      // 644b: iadd
      // 644c: invokevirtual CarMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // 644f: aastore
      // 6450: aload 0
      // 6451: getfield CarMaker.addeda [[Ljava/lang/String;
      // 6454: aload 0
      // 6455: getfield CarMaker.roto I
      // 6458: aaload
      // 6459: aload 0
      // 645a: getfield CarMaker.nad [I
      // 645d: aload 0
      // 645e: getfield CarMaker.roto I
      // 6461: iaload
      // 6462: aaload
      // 6463: ldc ""
      // 6465: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6468: ifeq 6471
      // 646b: bipush 1
      // 646c: istore 7
      // 646e: goto 6425
      // 6471: aload 0
      // 6472: getfield CarMaker.nad [I
      // 6475: aload 0
      // 6476: getfield CarMaker.roto I
      // 6479: dup2
      // 647a: iaload
      // 647b: bipush 1
      // 647c: iadd
      // 647d: iastore
      // 647e: goto 6425
      // 6481: goto 63c1
      // 6484: aload 0
      // 6485: dup
      // 6486: getfield CarMaker.nmc I
      // 6489: bipush 1
      // 648a: iadd
      // 648b: putfield CarMaker.nmc I
      // 648e: aload 0
      // 648f: getfield CarMaker.nmc I
      // 6492: bipush 20
      // 6494: if_icmple 649d
      // 6497: aload 0
      // 6498: bipush 20
      // 649a: putfield CarMaker.nmc I
      // 649d: aload 0
      // 649e: dup
      // 649f: getfield CarMaker.roto I
      // 64a2: bipush 1
      // 64a3: iadd
      // 64a4: putfield CarMaker.roto I
      // 64a7: aload 0
      // 64a8: getfield CarMaker.roto I
      // 64ab: bipush 20
      // 64ad: if_icmplt 64b5
      // 64b0: aload 0
      // 64b1: bipush 0
      // 64b2: putfield CarMaker.roto I
      // 64b5: goto 64ba
      // 64b8: astore 4
      // 64ba: aload 0
      // 64bb: bipush 3
      // 64bc: putfield CarMaker.logged I
      // 64bf: aload 0
      // 64c0: getfield CarMaker.logged I
      // 64c3: bipush 1
      // 64c4: if_icmpne 6660
      // 64c7: aload 0
      // 64c8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 64cb: new java/awt/Font
      // 64ce: dup
      // 64cf: ldc_w "Arial"
      // 64d2: bipush 1
      // 64d3: bipush 13
      // 64d5: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 64d8: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 64db: aload 0
      // 64dc: aload 0
      // 64dd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 64e0: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 64e3: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 64e6: aload 0
      // 64e7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 64ea: ldc_w "Loading your account car list..."
      // 64ed: sipush 350
      // 64f0: aload 0
      // 64f1: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 64f4: ldc_w "Loading your account car list..."
      // 64f7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 64fa: bipush 2
      // 64fb: idiv
      // 64fc: isub
      // 64fd: sipush 220
      // 6500: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6503: aload 0
      // 6504: invokevirtual CarMaker.repaint ()V
      // 6507: aload 0
      // 6508: getfield CarMaker.pubitem LSmenu;
      // 650b: invokevirtual Smenu.removeAll ()V
      // 650e: aload 0
      // 650f: getfield CarMaker.pubitem LSmenu;
      // 6512: aload 0
      // 6513: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6516: ldc_w "Account Cars"
      // 6519: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 651c: aload 0
      // 651d: bipush 0
      // 651e: putfield CarMaker.nmc I
      // 6521: aload 0
      // 6522: bipush 0
      // 6523: putfield CarMaker.roto I
      // 6526: bipush 0
      // 6527: istore 3
      // 6528: ldc ""
      // 652a: astore 4
      // 652c: new java/net/URL
      // 652f: dup
      // 6530: new java/lang/StringBuilder
      // 6533: dup
      // 6534: invokespecial java/lang/StringBuilder.<init> ()V
      // 6537: ldc_w "http://multiplayer.needformadness.com/cars/lists/"
      // 653a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 653d: aload 0
      // 653e: getfield CarMaker.tnick Ljava/awt/TextField;
      // 6541: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 6544: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6547: ldc_w ".txt?reqlo="
      // 654a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 654d: invokestatic java/lang/Math.random ()D
      // 6550: ldc2_w 1000.0
      // 6553: dmul
      // 6554: d2i
      // 6555: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6558: ldc ""
      // 655a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 655d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6560: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // 6563: astore 5
      // 6565: new java/io/DataInputStream
      // 6568: dup
      // 6569: aload 5
      // 656b: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // 656e: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 6571: astore 6
      // 6573: aload 6
      // 6575: invokevirtual java/io/DataInputStream.readLine ()Ljava/lang/String;
      // 6578: dup
      // 6579: astore 4
      // 657b: ifnull 65e3
      // 657e: new java/lang/StringBuilder
      // 6581: dup
      // 6582: invokespecial java/lang/StringBuilder.<init> ()V
      // 6585: ldc ""
      // 6587: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 658a: aload 4
      // 658c: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 658f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6592: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6595: astore 4
      // 6597: aload 4
      // 6599: ldc_w "mycars"
      // 659c: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 659f: ifeq 6573
      // 65a2: bipush 1
      // 65a3: istore 7
      // 65a5: iload 7
      // 65a7: ifeq 65e0
      // 65aa: iload 3
      // 65ab: sipush 700
      // 65ae: if_icmpge 65e0
      // 65b1: aload 0
      // 65b2: ldc_w "mycars"
      // 65b5: aload 4
      // 65b7: iload 3
      // 65b8: invokevirtual CarMaker.getSvalue (Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
      // 65bb: astore 8
      // 65bd: aload 8
      // 65bf: ldc ""
      // 65c1: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 65c4: ifeq 65cd
      // 65c7: bipush 0
      // 65c8: istore 7
      // 65ca: goto 65dd
      // 65cd: aload 0
      // 65ce: getfield CarMaker.pubitem LSmenu;
      // 65d1: aload 0
      // 65d2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 65d5: aload 8
      // 65d7: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 65da: iinc 3 1
      // 65dd: goto 65a5
      // 65e0: goto 6573
      // 65e3: aload 0
      // 65e4: new java/awt/Cursor
      // 65e7: dup
      // 65e8: bipush 0
      // 65e9: invokespecial java/awt/Cursor.<init> (I)V
      // 65ec: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 65ef: aload 0
      // 65f0: bipush -1
      // 65f1: putfield CarMaker.logged I
      // 65f4: aload 6
      // 65f6: invokevirtual java/io/DataInputStream.close ()V
      // 65f9: goto 6643
      // 65fc: astore 5
      // 65fe: new java/lang/StringBuilder
      // 6601: dup
      // 6602: invokespecial java/lang/StringBuilder.<init> ()V
      // 6605: ldc ""
      // 6607: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 660a: aload 5
      // 660c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 660f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6612: astore 6
      // 6614: aload 6
      // 6616: ldc_w "FileNotFound"
      // 6619: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 661c: bipush -1
      // 661d: if_icmpeq 6634
      // 6620: aload 0
      // 6621: new java/awt/Cursor
      // 6624: dup
      // 6625: bipush 0
      // 6626: invokespecial java/awt/Cursor.<init> (I)V
      // 6629: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 662c: aload 0
      // 662d: bipush -1
      // 662e: putfield CarMaker.logged I
      // 6631: goto 6643
      // 6634: aload 0
      // 6635: bipush 0
      // 6636: putfield CarMaker.logged I
      // 6639: aconst_null
      // 663a: ldc_w "Unable to connect to server at this moment, please try again later."
      // 663d: ldc "Car Maker"
      // 663f: bipush 1
      // 6640: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 6643: aload 0
      // 6644: getfield CarMaker.justpubd Ljava/lang/String;
      // 6647: ldc ""
      // 6649: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 664c: ifne 6660
      // 664f: aload 0
      // 6650: getfield CarMaker.pubitem LSmenu;
      // 6653: aload 0
      // 6654: getfield CarMaker.justpubd Ljava/lang/String;
      // 6657: invokevirtual Smenu.select (Ljava/lang/String;)V
      // 665a: aload 0
      // 665b: ldc ""
      // 665d: putfield CarMaker.justpubd Ljava/lang/String;
      // 6660: aload 0
      // 6661: getfield CarMaker.logged I
      // 6664: bipush 3
      // 6665: if_icmpne 7097
      // 6668: bipush 0
      // 6669: istore 3
      // 666a: iload 3
      // 666b: aload 0
      // 666c: getfield CarMaker.nmc I
      // 666f: if_icmpge 7097
      // 6672: aload 0
      // 6673: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6676: new java/awt/Color
      // 6679: dup
      // 667a: sipush 235
      // 667d: sipush 235
      // 6680: sipush 235
      // 6683: invokespecial java/awt/Color.<init> (III)V
      // 6686: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6689: aload 0
      // 668a: getfield CarMaker.xm I
      // 668d: bipush 11
      // 668f: if_icmple 66d1
      // 6692: aload 0
      // 6693: getfield CarMaker.xm I
      // 6696: sipush 689
      // 6699: if_icmpge 66d1
      // 669c: aload 0
      // 669d: getfield CarMaker.ym I
      // 66a0: sipush 142
      // 66a3: iload 3
      // 66a4: bipush 20
      // 66a6: imul
      // 66a7: iadd
      // 66a8: if_icmple 66d1
      // 66ab: aload 0
      // 66ac: getfield CarMaker.ym I
      // 66af: sipush 160
      // 66b2: iload 3
      // 66b3: bipush 20
      // 66b5: imul
      // 66b6: iadd
      // 66b7: if_icmpge 66d1
      // 66ba: aload 0
      // 66bb: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 66be: new java/awt/Color
      // 66c1: dup
      // 66c2: sipush 255
      // 66c5: sipush 255
      // 66c8: sipush 255
      // 66cb: invokespecial java/awt/Color.<init> (III)V
      // 66ce: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 66d1: aload 0
      // 66d2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 66d5: bipush 11
      // 66d7: sipush 142
      // 66da: iload 3
      // 66db: bipush 20
      // 66dd: imul
      // 66de: iadd
      // 66df: sipush 678
      // 66e2: bipush 18
      // 66e4: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 66e7: aload 0
      // 66e8: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 66eb: new java/awt/Font
      // 66ee: dup
      // 66ef: ldc_w "Arial"
      // 66f2: bipush 0
      // 66f3: bipush 12
      // 66f5: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 66f8: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 66fb: aload 0
      // 66fc: aload 0
      // 66fd: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6700: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6703: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6706: aload 0
      // 6707: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 670a: new java/awt/Color
      // 670d: dup
      // 670e: bipush 0
      // 670f: bipush 0
      // 6710: bipush 0
      // 6711: invokespecial java/awt/Color.<init> (III)V
      // 6714: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6717: aload 0
      // 6718: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 671b: aload 0
      // 671c: getfield CarMaker.mycars [Ljava/lang/String;
      // 671f: iload 3
      // 6720: aaload
      // 6721: bipush 80
      // 6723: aload 0
      // 6724: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6727: aload 0
      // 6728: getfield CarMaker.mycars [Ljava/lang/String;
      // 672b: iload 3
      // 672c: aaload
      // 672d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6730: bipush 2
      // 6731: idiv
      // 6732: isub
      // 6733: sipush 156
      // 6736: iload 3
      // 6737: bipush 20
      // 6739: imul
      // 673a: iadd
      // 673b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 673e: aload 0
      // 673f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6742: new java/awt/Color
      // 6745: dup
      // 6746: sipush 155
      // 6749: sipush 155
      // 674c: sipush 155
      // 674f: invokespecial java/awt/Color.<init> (III)V
      // 6752: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6755: aload 0
      // 6756: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6759: sipush 150
      // 675c: sipush 145
      // 675f: iload 3
      // 6760: bipush 20
      // 6762: imul
      // 6763: iadd
      // 6764: sipush 150
      // 6767: sipush 157
      // 676a: iload 3
      // 676b: bipush 20
      // 676d: imul
      // 676e: iadd
      // 676f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6772: aload 0
      // 6773: getfield CarMaker.pubt [I
      // 6776: iload 3
      // 6777: iaload
      // 6778: bipush -1
      // 6779: if_icmpeq 6f4a
      // 677c: aload 0
      // 677d: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6780: sipush 250
      // 6783: sipush 145
      // 6786: iload 3
      // 6787: bipush 20
      // 6789: imul
      // 678a: iadd
      // 678b: sipush 250
      // 678e: sipush 157
      // 6791: iload 3
      // 6792: bipush 20
      // 6794: imul
      // 6795: iadd
      // 6796: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6799: aload 0
      // 679a: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 679d: sipush 350
      // 67a0: sipush 145
      // 67a3: iload 3
      // 67a4: bipush 20
      // 67a6: imul
      // 67a7: iadd
      // 67a8: sipush 350
      // 67ab: sipush 157
      // 67ae: iload 3
      // 67af: bipush 20
      // 67b1: imul
      // 67b2: iadd
      // 67b3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 67b6: aload 0
      // 67b7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 67ba: sipush 450
      // 67bd: sipush 145
      // 67c0: iload 3
      // 67c1: bipush 20
      // 67c3: imul
      // 67c4: iadd
      // 67c5: sipush 450
      // 67c8: sipush 157
      // 67cb: iload 3
      // 67cc: bipush 20
      // 67ce: imul
      // 67cf: iadd
      // 67d0: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 67d3: aload 0
      // 67d4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 67d7: sipush 550
      // 67da: sipush 145
      // 67dd: iload 3
      // 67de: bipush 20
      // 67e0: imul
      // 67e1: iadd
      // 67e2: sipush 550
      // 67e5: sipush 157
      // 67e8: iload 3
      // 67e9: bipush 20
      // 67eb: imul
      // 67ec: iadd
      // 67ed: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 67f0: aload 0
      // 67f1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 67f4: new java/awt/Color
      // 67f7: dup
      // 67f8: bipush 0
      // 67f9: bipush 0
      // 67fa: bipush 64
      // 67fc: invokespecial java/awt/Color.<init> (III)V
      // 67ff: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6802: ldc_w "C"
      // 6805: astore 4
      // 6807: aload 0
      // 6808: getfield CarMaker.clas [I
      // 680b: iload 3
      // 680c: iaload
      // 680d: bipush 1
      // 680e: if_icmpne 6816
      // 6811: ldc_w "B & C"
      // 6814: astore 4
      // 6816: aload 0
      // 6817: getfield CarMaker.clas [I
      // 681a: iload 3
      // 681b: iaload
      // 681c: bipush 2
      // 681d: if_icmpne 6825
      // 6820: ldc_w "B"
      // 6823: astore 4
      // 6825: aload 0
      // 6826: getfield CarMaker.clas [I
      // 6829: iload 3
      // 682a: iaload
      // 682b: bipush 3
      // 682c: if_icmpne 6834
      // 682f: ldc_w "A & B"
      // 6832: astore 4
      // 6834: aload 0
      // 6835: getfield CarMaker.clas [I
      // 6838: iload 3
      // 6839: iaload
      // 683a: bipush 4
      // 683b: if_icmpne 6843
      // 683e: ldc_w "A"
      // 6841: astore 4
      // 6843: aload 0
      // 6844: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6847: new java/lang/StringBuilder
      // 684a: dup
      // 684b: invokespecial java/lang/StringBuilder.<init> ()V
      // 684e: ldc_w "Class "
      // 6851: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6854: aload 4
      // 6856: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6859: ldc ""
      // 685b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 685e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6861: sipush 200
      // 6864: aload 0
      // 6865: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6868: new java/lang/StringBuilder
      // 686b: dup
      // 686c: invokespecial java/lang/StringBuilder.<init> ()V
      // 686f: ldc_w "Class "
      // 6872: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6875: aload 4
      // 6877: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 687a: ldc ""
      // 687c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 687f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6882: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6885: bipush 2
      // 6886: idiv
      // 6887: isub
      // 6888: sipush 156
      // 688b: iload 3
      // 688c: bipush 20
      // 688e: imul
      // 688f: iadd
      // 6890: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6893: bipush 0
      // 6894: istore 5
      // 6896: aload 0
      // 6897: getfield CarMaker.maker [Ljava/lang/String;
      // 689a: iload 3
      // 689b: aaload
      // 689c: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 689f: aload 0
      // 68a0: getfield CarMaker.tnick Ljava/awt/TextField;
      // 68a3: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 68a6: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 68a9: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 68ac: ifeq 68e9
      // 68af: bipush 1
      // 68b0: istore 5
      // 68b2: aload 0
      // 68b3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 68b6: new java/awt/Color
      // 68b9: dup
      // 68ba: bipush 0
      // 68bb: bipush 64
      // 68bd: bipush 0
      // 68be: invokespecial java/awt/Color.<init> (III)V
      // 68c1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 68c4: aload 0
      // 68c5: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 68c8: ldc_w "You"
      // 68cb: sipush 300
      // 68ce: aload 0
      // 68cf: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 68d2: ldc_w "You"
      // 68d5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 68d8: bipush 2
      // 68d9: idiv
      // 68da: isub
      // 68db: sipush 156
      // 68de: iload 3
      // 68df: bipush 20
      // 68e1: imul
      // 68e2: iadd
      // 68e3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 68e6: goto 6911
      // 68e9: aload 0
      // 68ea: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 68ed: aload 0
      // 68ee: getfield CarMaker.maker [Ljava/lang/String;
      // 68f1: iload 3
      // 68f2: aaload
      // 68f3: sipush 300
      // 68f6: aload 0
      // 68f7: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 68fa: aload 0
      // 68fb: getfield CarMaker.maker [Ljava/lang/String;
      // 68fe: iload 3
      // 68ff: aaload
      // 6900: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6903: bipush 2
      // 6904: idiv
      // 6905: isub
      // 6906: sipush 156
      // 6909: iload 3
      // 690a: bipush 20
      // 690c: imul
      // 690d: iadd
      // 690e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6911: aload 0
      // 6912: getfield CarMaker.nad [I
      // 6915: iload 3
      // 6916: iaload
      // 6917: bipush 1
      // 6918: if_icmple 6a56
      // 691b: aload 0
      // 691c: new java/lang/StringBuilder
      // 691f: dup
      // 6920: invokespecial java/lang/StringBuilder.<init> ()V
      // 6923: ldc ""
      // 6925: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6928: aload 0
      // 6929: getfield CarMaker.nad [I
      // 692c: iload 3
      // 692d: iaload
      // 692e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6931: ldc_w " Players"
      // 6934: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6937: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 693a: sipush 400
      // 693d: sipush 156
      // 6940: iload 3
      // 6941: bipush 20
      // 6943: imul
      // 6944: iadd
      // 6945: invokevirtual CarMaker.ovbutton (Ljava/lang/String;II)Z
      // 6948: ifeq 6a8a
      // 694b: new java/lang/StringBuilder
      // 694e: dup
      // 694f: invokespecial java/lang/StringBuilder.<init> ()V
      // 6952: ldc_w "[ "
      // 6955: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6958: aload 0
      // 6959: getfield CarMaker.mycars [Ljava/lang/String;
      // 695c: iload 3
      // 695d: aaload
      // 695e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6961: ldc_w " ]  has been added by the following players to their accounts:     \n\n"
      // 6964: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6967: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 696a: astore 6
      // 696c: bipush 0
      // 696d: istore 7
      // 696f: bipush 0
      // 6970: istore 8
      // 6972: iload 8
      // 6974: aload 0
      // 6975: getfield CarMaker.nad [I
      // 6978: iload 3
      // 6979: iaload
      // 697a: if_icmpge 6a33
      // 697d: iinc 7 1
      // 6980: iload 7
      // 6982: bipush 17
      // 6984: if_icmpne 69a1
      // 6987: new java/lang/StringBuilder
      // 698a: dup
      // 698b: invokespecial java/lang/StringBuilder.<init> ()V
      // 698e: aload 6
      // 6990: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6993: ldc_w "\n"
      // 6996: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6999: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 699c: astore 6
      // 699e: bipush 1
      // 699f: istore 7
      // 69a1: new java/lang/StringBuilder
      // 69a4: dup
      // 69a5: invokespecial java/lang/StringBuilder.<init> ()V
      // 69a8: aload 6
      // 69aa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 69ad: aload 0
      // 69ae: getfield CarMaker.addeda [[Ljava/lang/String;
      // 69b1: iload 3
      // 69b2: aaload
      // 69b3: iload 8
      // 69b5: aaload
      // 69b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 69b9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 69bc: astore 6
      // 69be: iload 8
      // 69c0: aload 0
      // 69c1: getfield CarMaker.nad [I
      // 69c4: iload 3
      // 69c5: iaload
      // 69c6: bipush 1
      // 69c7: isub
      // 69c8: if_icmpeq 6a2d
      // 69cb: iload 8
      // 69cd: aload 0
      // 69ce: getfield CarMaker.nad [I
      // 69d1: iload 3
      // 69d2: iaload
      // 69d3: bipush 2
      // 69d4: isub
      // 69d5: if_icmpeq 69f2
      // 69d8: new java/lang/StringBuilder
      // 69db: dup
      // 69dc: invokespecial java/lang/StringBuilder.<init> ()V
      // 69df: aload 6
      // 69e1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 69e4: ldc_w ", "
      // 69e7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 69ea: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 69ed: astore 6
      // 69ef: goto 6a2d
      // 69f2: iload 7
      // 69f4: bipush 16
      // 69f6: if_icmpne 6a16
      // 69f9: new java/lang/StringBuilder
      // 69fc: dup
      // 69fd: invokespecial java/lang/StringBuilder.<init> ()V
      // 6a00: aload 6
      // 6a02: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a05: ldc_w "\nand "
      // 6a08: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a0b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6a0e: astore 6
      // 6a10: bipush 0
      // 6a11: istore 7
      // 6a13: goto 6a2d
      // 6a16: new java/lang/StringBuilder
      // 6a19: dup
      // 6a1a: invokespecial java/lang/StringBuilder.<init> ()V
      // 6a1d: aload 6
      // 6a1f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a22: ldc_w " and "
      // 6a25: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a28: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6a2b: astore 6
      // 6a2d: iinc 8 1
      // 6a30: goto 6972
      // 6a33: new java/lang/StringBuilder
      // 6a36: dup
      // 6a37: invokespecial java/lang/StringBuilder.<init> ()V
      // 6a3a: aload 6
      // 6a3c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a3f: ldc_w "\n \n \n"
      // 6a42: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6a45: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6a48: astore 6
      // 6a4a: aconst_null
      // 6a4b: aload 6
      // 6a4d: ldc "Car Maker"
      // 6a4f: bipush 1
      // 6a50: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 6a53: goto 6a8a
      // 6a56: aload 0
      // 6a57: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6a5a: new java/awt/Color
      // 6a5d: dup
      // 6a5e: bipush 0
      // 6a5f: bipush 0
      // 6a60: bipush 64
      // 6a62: invokespecial java/awt/Color.<init> (III)V
      // 6a65: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6a68: aload 0
      // 6a69: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6a6c: ldc_w "None"
      // 6a6f: sipush 400
      // 6a72: aload 0
      // 6a73: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6a76: ldc_w "None"
      // 6a79: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6a7c: bipush 2
      // 6a7d: idiv
      // 6a7e: isub
      // 6a7f: sipush 156
      // 6a82: iload 3
      // 6a83: bipush 20
      // 6a85: imul
      // 6a86: iadd
      // 6a87: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6a8a: aload 0
      // 6a8b: getfield CarMaker.pubt [I
      // 6a8e: iload 3
      // 6a8f: iaload
      // 6a90: ifne 6ac7
      // 6a93: aload 0
      // 6a94: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6a97: new java/awt/Color
      // 6a9a: dup
      // 6a9b: bipush 0
      // 6a9c: bipush 0
      // 6a9d: bipush 64
      // 6a9f: invokespecial java/awt/Color.<init> (III)V
      // 6aa2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6aa5: aload 0
      // 6aa6: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6aa9: ldc_w "Private"
      // 6aac: sipush 500
      // 6aaf: aload 0
      // 6ab0: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6ab3: ldc_w "Private"
      // 6ab6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6ab9: bipush 2
      // 6aba: idiv
      // 6abb: isub
      // 6abc: sipush 156
      // 6abf: iload 3
      // 6ac0: bipush 20
      // 6ac2: imul
      // 6ac3: iadd
      // 6ac4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6ac7: aload 0
      // 6ac8: getfield CarMaker.pubt [I
      // 6acb: iload 3
      // 6acc: iaload
      // 6acd: bipush 1
      // 6ace: if_icmpne 6b05
      // 6ad1: aload 0
      // 6ad2: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6ad5: new java/awt/Color
      // 6ad8: dup
      // 6ad9: bipush 0
      // 6ada: bipush 0
      // 6adb: bipush 64
      // 6add: invokespecial java/awt/Color.<init> (III)V
      // 6ae0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6ae3: aload 0
      // 6ae4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6ae7: ldc_w "Public"
      // 6aea: sipush 500
      // 6aed: aload 0
      // 6aee: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6af1: ldc_w "Public"
      // 6af4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6af7: bipush 2
      // 6af8: idiv
      // 6af9: isub
      // 6afa: sipush 156
      // 6afd: iload 3
      // 6afe: bipush 20
      // 6b00: imul
      // 6b01: iadd
      // 6b02: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6b05: aload 0
      // 6b06: getfield CarMaker.pubt [I
      // 6b09: iload 3
      // 6b0a: iaload
      // 6b0b: bipush 2
      // 6b0c: if_icmpne 6b43
      // 6b0f: aload 0
      // 6b10: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6b13: new java/awt/Color
      // 6b16: dup
      // 6b17: bipush 0
      // 6b18: bipush 64
      // 6b1a: bipush 0
      // 6b1b: invokespecial java/awt/Color.<init> (III)V
      // 6b1e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6b21: aload 0
      // 6b22: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6b25: ldc_w "Super Public"
      // 6b28: sipush 500
      // 6b2b: aload 0
      // 6b2c: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6b2f: ldc_w "Super Public"
      // 6b32: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6b35: bipush 2
      // 6b36: idiv
      // 6b37: isub
      // 6b38: sipush 156
      // 6b3b: iload 3
      // 6b3c: bipush 20
      // 6b3e: imul
      // 6b3f: iadd
      // 6b40: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6b43: aload 0
      // 6b44: getfield CarMaker.pubt [I
      // 6b47: iload 3
      // 6b48: iaload
      // 6b49: bipush 2
      // 6b4a: if_icmpeq 6b52
      // 6b4d: iload 5
      // 6b4f: ifeq 6f47
      // 6b52: aload 0
      // 6b53: ldc_w "Download"
      // 6b56: sipush 600
      // 6b59: sipush 156
      // 6b5c: iload 3
      // 6b5d: bipush 20
      // 6b5f: imul
      // 6b60: iadd
      // 6b61: invokevirtual CarMaker.ovbutton (Ljava/lang/String;II)Z
      // 6b64: ifeq 6f47
      // 6b67: bipush 0
      // 6b68: istore 6
      // 6b6a: bipush 0
      // 6b6b: istore 7
      // 6b6d: iload 7
      // 6b6f: aload 0
      // 6b70: getfield CarMaker.slcar LSmenu;
      // 6b73: invokevirtual Smenu.getItemCount ()I
      // 6b76: if_icmpge 6bbc
      // 6b79: aload 0
      // 6b7a: getfield CarMaker.mycars [Ljava/lang/String;
      // 6b7d: iload 3
      // 6b7e: aaload
      // 6b7f: aload 0
      // 6b80: getfield CarMaker.slcar LSmenu;
      // 6b83: iload 7
      // 6b85: invokevirtual Smenu.getItem (I)Ljava/lang/String;
      // 6b88: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6b8b: ifeq 6bb6
      // 6b8e: aconst_null
      // 6b8f: new java/lang/StringBuilder
      // 6b92: dup
      // 6b93: invokespecial java/lang/StringBuilder.<init> ()V
      // 6b96: ldc_w "Replace the local "
      // 6b99: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6b9c: aload 0
      // 6b9d: getfield CarMaker.mycars [Ljava/lang/String;
      // 6ba0: iload 3
      // 6ba1: aaload
      // 6ba2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6ba5: ldc_w " in your 'mycars' folder with the published online copy?"
      // 6ba8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6bab: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6bae: ldc "Car Maker"
      // 6bb0: bipush 0
      // 6bb1: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 6bb4: istore 6
      // 6bb6: iinc 7 1
      // 6bb9: goto 6b6d
      // 6bbc: iload 6
      // 6bbe: ifne 6f47
      // 6bc1: aload 0
      // 6bc2: new java/awt/Cursor
      // 6bc5: dup
      // 6bc6: bipush 3
      // 6bc7: invokespecial java/awt/Cursor.<init> (I)V
      // 6bca: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 6bcd: aload 0
      // 6bce: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6bd1: new java/awt/Font
      // 6bd4: dup
      // 6bd5: ldc_w "Arial"
      // 6bd8: bipush 1
      // 6bd9: bipush 13
      // 6bdb: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6bde: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6be1: aload 0
      // 6be2: aload 0
      // 6be3: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6be6: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6be9: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6bec: aload 0
      // 6bed: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6bf0: new java/awt/Color
      // 6bf3: dup
      // 6bf4: sipush 225
      // 6bf7: sipush 225
      // 6bfa: sipush 225
      // 6bfd: invokespecial java/awt/Color.<init> (III)V
      // 6c00: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6c03: aload 0
      // 6c04: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6c07: bipush 11
      // 6c09: sipush 141
      // 6c0c: sipush 679
      // 6c0f: sipush 401
      // 6c12: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6c15: aload 0
      // 6c16: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6c19: new java/awt/Color
      // 6c1c: dup
      // 6c1d: bipush 0
      // 6c1e: bipush 0
      // 6c1f: bipush 0
      // 6c20: invokespecial java/awt/Color.<init> (III)V
      // 6c23: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6c26: aload 0
      // 6c27: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6c2a: ldc_w "Downloading car, please wait..."
      // 6c2d: sipush 350
      // 6c30: aload 0
      // 6c31: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6c34: ldc_w "Downloading car, please wait..."
      // 6c37: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6c3a: bipush 2
      // 6c3b: idiv
      // 6c3c: isub
      // 6c3d: sipush 250
      // 6c40: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6c43: aload 0
      // 6c44: invokevirtual CarMaker.repaint ()V
      // 6c47: new java/lang/StringBuilder
      // 6c4a: dup
      // 6c4b: invokespecial java/lang/StringBuilder.<init> ()V
      // 6c4e: ldc_w "http://multiplayer.needformadness.com/cars/"
      // 6c51: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6c54: aload 0
      // 6c55: getfield CarMaker.mycars [Ljava/lang/String;
      // 6c58: iload 3
      // 6c59: aaload
      // 6c5a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6c5d: ldc_w ".radq?reqlo="
      // 6c60: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6c63: invokestatic java/lang/Math.random ()D
      // 6c66: ldc2_w 1000.0
      // 6c69: dmul
      // 6c6a: d2i
      // 6c6b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6c6e: ldc ""
      // 6c70: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6c73: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6c76: astore 7
      // 6c78: aload 7
      // 6c7a: bipush 32
      // 6c7c: bipush 95
      // 6c7e: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // 6c81: astore 7
      // 6c83: new java/net/URL
      // 6c86: dup
      // 6c87: aload 7
      // 6c89: invokespecial java/net/URL.<init> (Ljava/lang/String;)V
      // 6c8c: astore 8
      // 6c8e: aload 8
      // 6c90: invokevirtual java/net/URL.openConnection ()Ljava/net/URLConnection;
      // 6c93: invokevirtual java/net/URLConnection.getContentLength ()I
      // 6c96: istore 10
      // 6c98: new java/io/DataInputStream
      // 6c9b: dup
      // 6c9c: aload 8
      // 6c9e: invokevirtual java/net/URL.openStream ()Ljava/io/InputStream;
      // 6ca1: invokespecial java/io/DataInputStream.<init> (Ljava/io/InputStream;)V
      // 6ca4: astore 11
      // 6ca6: iload 10
      // 6ca8: newarray 8
      // 6caa: astore 12
      // 6cac: aload 11
      // 6cae: aload 12
      // 6cb0: invokevirtual java/io/DataInputStream.readFully ([B)V
      // 6cb3: aload 12
      // 6cb5: bipush 0
      // 6cb6: baload
      // 6cb7: bipush 80
      // 6cb9: if_icmpne 6ce2
      // 6cbc: aload 12
      // 6cbe: bipush 1
      // 6cbf: baload
      // 6cc0: bipush 75
      // 6cc2: if_icmpne 6ce2
      // 6cc5: aload 12
      // 6cc7: bipush 2
      // 6cc8: baload
      // 6cc9: bipush 3
      // 6cca: if_icmpne 6ce2
      // 6ccd: new java/util/zip/ZipInputStream
      // 6cd0: dup
      // 6cd1: new java/io/ByteArrayInputStream
      // 6cd4: dup
      // 6cd5: aload 12
      // 6cd7: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 6cda: invokespecial java/util/zip/ZipInputStream.<init> (Ljava/io/InputStream;)V
      // 6cdd: astore 9
      // 6cdf: goto 6d2d
      // 6ce2: iload 10
      // 6ce4: bipush 40
      // 6ce6: isub
      // 6ce7: newarray 8
      // 6ce9: astore 13
      // 6ceb: bipush 0
      // 6cec: istore 14
      // 6cee: iload 14
      // 6cf0: iload 10
      // 6cf2: bipush 40
      // 6cf4: isub
      // 6cf5: if_icmpge 6d1b
      // 6cf8: bipush 20
      // 6cfa: istore 15
      // 6cfc: iload 14
      // 6cfe: sipush 500
      // 6d01: if_icmplt 6d08
      // 6d04: bipush 40
      // 6d06: istore 15
      // 6d08: aload 13
      // 6d0a: iload 14
      // 6d0c: aload 12
      // 6d0e: iload 14
      // 6d10: iload 15
      // 6d12: iadd
      // 6d13: baload
      // 6d14: bastore
      // 6d15: iinc 14 1
      // 6d18: goto 6cee
      // 6d1b: new java/util/zip/ZipInputStream
      // 6d1e: dup
      // 6d1f: new java/io/ByteArrayInputStream
      // 6d22: dup
      // 6d23: aload 13
      // 6d25: invokespecial java/io/ByteArrayInputStream.<init> ([B)V
      // 6d28: invokespecial java/util/zip/ZipInputStream.<init> (Ljava/io/InputStream;)V
      // 6d2b: astore 9
      // 6d2d: aload 9
      // 6d2f: invokevirtual java/util/zip/ZipInputStream.getNextEntry ()Ljava/util/zip/ZipEntry;
      // 6d32: astore 13
      // 6d34: aload 13
      // 6d36: ifnull 6f2e
      // 6d39: aload 13
      // 6d3b: invokevirtual java/util/zip/ZipEntry.getName ()Ljava/lang/String;
      // 6d3e: invokestatic java/lang/Integer.valueOf (Ljava/lang/String;)Ljava/lang/Integer;
      // 6d41: invokevirtual java/lang/Integer.intValue ()I
      // 6d44: istore 14
      // 6d46: iload 14
      // 6d48: newarray 8
      // 6d4a: astore 15
      // 6d4c: bipush 0
      // 6d4d: istore 16
      // 6d4f: iload 14
      // 6d51: ifle 6d72
      // 6d54: aload 9
      // 6d56: aload 15
      // 6d58: iload 16
      // 6d5a: iload 14
      // 6d5c: invokevirtual java/util/zip/ZipInputStream.read ([BII)I
      // 6d5f: istore 17
      // 6d61: iload 16
      // 6d63: iload 17
      // 6d65: iadd
      // 6d66: istore 16
      // 6d68: iload 14
      // 6d6a: iload 17
      // 6d6c: isub
      // 6d6d: istore 14
      // 6d6f: goto 6d4f
      // 6d72: new java/lang/String
      // 6d75: dup
      // 6d76: aload 15
      // 6d78: invokespecial java/lang/String.<init> ([B)V
      // 6d7b: astore 18
      // 6d7d: new java/lang/StringBuilder
      // 6d80: dup
      // 6d81: invokespecial java/lang/StringBuilder.<init> ()V
      // 6d84: aload 18
      // 6d86: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d89: ldc_w "\n"
      // 6d8c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d8f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6d92: astore 18
      // 6d94: ldc ""
      // 6d96: astore 19
      // 6d98: bipush 0
      // 6d99: istore 20
      // 6d9b: aload 18
      // 6d9d: ldc_w "\n"
      // 6da0: bipush 0
      // 6da1: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 6da4: istore 21
      // 6da6: iload 21
      // 6da8: bipush -1
      // 6da9: if_icmpeq 6e35
      // 6dac: iload 20
      // 6dae: aload 18
      // 6db0: invokevirtual java/lang/String.length ()I
      // 6db3: if_icmpge 6e35
      // 6db6: aload 18
      // 6db8: iload 20
      // 6dba: iload 21
      // 6dbc: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 6dbf: astore 22
      // 6dc1: aload 22
      // 6dc3: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 6dc6: astore 22
      // 6dc8: iload 21
      // 6dca: bipush 1
      // 6dcb: iadd
      // 6dcc: istore 20
      // 6dce: aload 18
      // 6dd0: ldc_w "\n"
      // 6dd3: iload 20
      // 6dd5: invokevirtual java/lang/String.indexOf (Ljava/lang/String;I)I
      // 6dd8: istore 21
      // 6dda: aload 22
      // 6ddc: ldc_w "carmaker("
      // 6ddf: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 6de2: ifne 6e14
      // 6de5: aload 22
      // 6de7: ldc_w "publish("
      // 6dea: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 6ded: ifne 6e14
      // 6df0: new java/lang/StringBuilder
      // 6df3: dup
      // 6df4: invokespecial java/lang/StringBuilder.<init> ()V
      // 6df7: aload 19
      // 6df9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6dfc: ldc ""
      // 6dfe: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e01: aload 22
      // 6e03: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e06: ldc_w "\n"
      // 6e09: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e0c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6e0f: astore 19
      // 6e11: goto 6e32
      // 6e14: aload 19
      // 6e16: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 6e19: astore 19
      // 6e1b: new java/lang/StringBuilder
      // 6e1e: dup
      // 6e1f: invokespecial java/lang/StringBuilder.<init> ()V
      // 6e22: aload 19
      // 6e24: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e27: ldc_w "\n"
      // 6e2a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e2d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6e30: astore 19
      // 6e32: goto 6da6
      // 6e35: aload 19
      // 6e37: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 6e3a: astore 19
      // 6e3c: new java/lang/StringBuilder
      // 6e3f: dup
      // 6e40: invokespecial java/lang/StringBuilder.<init> ()V
      // 6e43: aload 19
      // 6e45: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e48: ldc_w "\n\n"
      // 6e4b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e4e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6e51: astore 19
      // 6e53: new java/io/File
      // 6e56: dup
      // 6e57: new java/lang/StringBuilder
      // 6e5a: dup
      // 6e5b: invokespecial java/lang/StringBuilder.<init> ()V
      // 6e5e: ldc ""
      // 6e60: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e63: getstatic Madness.fpath Ljava/lang/String;
      // 6e66: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e69: ldc "mycars/"
      // 6e6b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e6e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6e71: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 6e74: astore 22
      // 6e76: aload 22
      // 6e78: invokevirtual java/io/File.exists ()Z
      // 6e7b: ifne 6e84
      // 6e7e: aload 22
      // 6e80: invokevirtual java/io/File.mkdirs ()Z
      // 6e83: pop
      // 6e84: new java/io/File
      // 6e87: dup
      // 6e88: new java/lang/StringBuilder
      // 6e8b: dup
      // 6e8c: invokespecial java/lang/StringBuilder.<init> ()V
      // 6e8f: ldc ""
      // 6e91: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e94: getstatic Madness.fpath Ljava/lang/String;
      // 6e97: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e9a: ldc "mycars/"
      // 6e9c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6e9f: aload 0
      // 6ea0: getfield CarMaker.mycars [Ljava/lang/String;
      // 6ea3: iload 3
      // 6ea4: aaload
      // 6ea5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6ea8: ldc ".rad"
      // 6eaa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6ead: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6eb0: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 6eb3: astore 22
      // 6eb5: new java/io/BufferedWriter
      // 6eb8: dup
      // 6eb9: new java/io/FileWriter
      // 6ebc: dup
      // 6ebd: aload 22
      // 6ebf: invokespecial java/io/FileWriter.<init> (Ljava/io/File;)V
      // 6ec2: invokespecial java/io/BufferedWriter.<init> (Ljava/io/Writer;)V
      // 6ec5: astore 23
      // 6ec7: aload 23
      // 6ec9: aload 19
      // 6ecb: invokevirtual java/io/BufferedWriter.write (Ljava/lang/String;)V
      // 6ece: aload 23
      // 6ed0: invokevirtual java/io/BufferedWriter.close ()V
      // 6ed3: aconst_null
      // 6ed4: astore 23
      // 6ed6: aload 9
      // 6ed8: invokevirtual java/util/zip/ZipInputStream.close ()V
      // 6edb: aload 0
      // 6edc: getfield CarMaker.carname Ljava/lang/String;
      // 6edf: aload 0
      // 6ee0: getfield CarMaker.mycars [Ljava/lang/String;
      // 6ee3: iload 3
      // 6ee4: aaload
      // 6ee5: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6ee8: ifeq 6efa
      // 6eeb: aload 0
      // 6eec: getfield CarMaker.editor Ljava/awt/TextArea;
      // 6eef: aload 19
      // 6ef1: invokevirtual java/awt/TextArea.setText (Ljava/lang/String;)V
      // 6ef4: aload 0
      // 6ef5: aload 19
      // 6ef7: putfield CarMaker.lastedo Ljava/lang/String;
      // 6efa: aload 0
      // 6efb: new java/awt/Cursor
      // 6efe: dup
      // 6eff: bipush 0
      // 6f00: invokespecial java/awt/Cursor.<init> (I)V
      // 6f03: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 6f06: aconst_null
      // 6f07: new java/lang/StringBuilder
      // 6f0a: dup
      // 6f0b: invokespecial java/lang/StringBuilder.<init> ()V
      // 6f0e: ldc ""
      // 6f10: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f13: aload 0
      // 6f14: getfield CarMaker.mycars [Ljava/lang/String;
      // 6f17: iload 3
      // 6f18: aaload
      // 6f19: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f1c: ldc_w " has been successfully downloaded!"
      // 6f1f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f22: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6f25: ldc "Car Maker"
      // 6f27: bipush 1
      // 6f28: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 6f2b: goto 6f38
      // 6f2e: aconst_null
      // 6f2f: ldc_w "Unable to download car.  Unknown Error!     \nPlease try again later."
      // 6f32: ldc "Car Maker"
      // 6f34: bipush 1
      // 6f35: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 6f38: goto 6f47
      // 6f3b: astore 7
      // 6f3d: aconst_null
      // 6f3e: ldc_w "Unable to download car.  Unknown Error!     \nPlease try again later."
      // 6f41: ldc "Car Maker"
      // 6f43: bipush 1
      // 6f44: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 6f47: goto 6f6c
      // 6f4a: aload 0
      // 6f4b: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 6f4e: ldc_w "-    Error Loading this car's info!    -"
      // 6f51: sipush 350
      // 6f54: aload 0
      // 6f55: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 6f58: ldc_w "-    Error Loading this car's info!    -"
      // 6f5b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6f5e: bipush 2
      // 6f5f: idiv
      // 6f60: isub
      // 6f61: sipush 156
      // 6f64: iload 3
      // 6f65: bipush 20
      // 6f67: imul
      // 6f68: iadd
      // 6f69: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6f6c: aload 0
      // 6f6d: ldc_w "X"
      // 6f70: sipush 665
      // 6f73: sipush 156
      // 6f76: iload 3
      // 6f77: bipush 20
      // 6f79: imul
      // 6f7a: iadd
      // 6f7b: invokevirtual CarMaker.ovbutton (Ljava/lang/String;II)Z
      // 6f7e: ifeq 7091
      // 6f81: aconst_null
      // 6f82: new java/lang/StringBuilder
      // 6f85: dup
      // 6f86: invokespecial java/lang/StringBuilder.<init> ()V
      // 6f89: ldc_w "Remove "
      // 6f8c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f8f: aload 0
      // 6f90: getfield CarMaker.mycars [Ljava/lang/String;
      // 6f93: iload 3
      // 6f94: aaload
      // 6f95: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f98: ldc_w " from your account?"
      // 6f9b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6f9e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6fa1: ldc "Car Maker"
      // 6fa3: bipush 0
      // 6fa4: invokestatic javax/swing/JOptionPane.showConfirmDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)I
      // 6fa7: ifne 7091
      // 6faa: aload 0
      // 6fab: new java/awt/Cursor
      // 6fae: dup
      // 6faf: bipush 3
      // 6fb0: invokespecial java/awt/Cursor.<init> (I)V
      // 6fb3: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 6fb6: bipush -1
      // 6fb7: istore 4
      // 6fb9: new java/net/Socket
      // 6fbc: dup
      // 6fbd: ldc_w "multiplayer.needformadness.com"
      // 6fc0: sipush 7061
      // 6fc3: invokespecial java/net/Socket.<init> (Ljava/lang/String;I)V
      // 6fc6: astore 5
      // 6fc8: new java/io/BufferedReader
      // 6fcb: dup
      // 6fcc: new java/io/InputStreamReader
      // 6fcf: dup
      // 6fd0: aload 5
      // 6fd2: invokevirtual java/net/Socket.getInputStream ()Ljava/io/InputStream;
      // 6fd5: invokespecial java/io/InputStreamReader.<init> (Ljava/io/InputStream;)V
      // 6fd8: invokespecial java/io/BufferedReader.<init> (Ljava/io/Reader;)V
      // 6fdb: astore 6
      // 6fdd: new java/io/PrintWriter
      // 6fe0: dup
      // 6fe1: aload 5
      // 6fe3: invokevirtual java/net/Socket.getOutputStream ()Ljava/io/OutputStream;
      // 6fe6: bipush 1
      // 6fe7: invokespecial java/io/PrintWriter.<init> (Ljava/io/OutputStream;Z)V
      // 6fea: astore 7
      // 6fec: aload 7
      // 6fee: new java/lang/StringBuilder
      // 6ff1: dup
      // 6ff2: invokespecial java/lang/StringBuilder.<init> ()V
      // 6ff5: ldc_w "9|"
      // 6ff8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6ffb: aload 0
      // 6ffc: getfield CarMaker.tnick Ljava/awt/TextField;
      // 6fff: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 7002: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7005: ldc_w "|"
      // 7008: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 700b: aload 0
      // 700c: getfield CarMaker.tpass Ljava/awt/TextField;
      // 700f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 7012: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7015: ldc_w "|"
      // 7018: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 701b: aload 0
      // 701c: getfield CarMaker.mycars [Ljava/lang/String;
      // 701f: iload 3
      // 7020: aaload
      // 7021: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7024: ldc_w "|"
      // 7027: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 702a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 702d: invokevirtual java/io/PrintWriter.println (Ljava/lang/String;)V
      // 7030: aload 6
      // 7032: invokevirtual java/io/BufferedReader.readLine ()Ljava/lang/String;
      // 7035: astore 8
      // 7037: aload 8
      // 7039: ifnull 7045
      // 703c: aload 0
      // 703d: aload 8
      // 703f: bipush 0
      // 7040: invokevirtual CarMaker.servervalue (Ljava/lang/String;I)I
      // 7043: istore 4
      // 7045: aload 5
      // 7047: invokevirtual java/net/Socket.close ()V
      // 704a: goto 7052
      // 704d: astore 5
      // 704f: bipush -1
      // 7050: istore 4
      // 7052: iload 4
      // 7054: ifne 705f
      // 7057: aload 0
      // 7058: bipush 1
      // 7059: putfield CarMaker.logged I
      // 705c: goto 7091
      // 705f: aload 0
      // 7060: new java/awt/Cursor
      // 7063: dup
      // 7064: bipush 0
      // 7065: invokespecial java/awt/Cursor.<init> (I)V
      // 7068: invokevirtual CarMaker.setCursor (Ljava/awt/Cursor;)V
      // 706b: aconst_null
      // 706c: new java/lang/StringBuilder
      // 706f: dup
      // 7070: invokespecial java/lang/StringBuilder.<init> ()V
      // 7073: ldc_w "Failed to remove "
      // 7076: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7079: aload 0
      // 707a: getfield CarMaker.mycars [Ljava/lang/String;
      // 707d: iload 3
      // 707e: aaload
      // 707f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7082: ldc_w " from your account.  Unknown Error!     \nPlease try again later."
      // 7085: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7088: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 708b: ldc "Car Maker"
      // 708d: bipush 1
      // 708e: invokestatic javax/swing/JOptionPane.showMessageDialog (Ljava/awt/Component;Ljava/lang/Object;Ljava/lang/String;I)V
      // 7091: iinc 3 1
      // 7094: goto 666a
      // 7097: aload 0
      // 7098: getfield CarMaker.tabed I
      // 709b: aload 0
      // 709c: getfield CarMaker.tab I
      // 709f: if_icmpeq 70aa
      // 70a2: aload 0
      // 70a3: aload 0
      // 70a4: getfield CarMaker.tab I
      // 70a7: putfield CarMaker.tabed I
      // 70aa: aload 0
      // 70ab: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 70ae: new java/awt/Color
      // 70b1: dup
      // 70b2: bipush 0
      // 70b3: bipush 0
      // 70b4: bipush 0
      // 70b5: invokespecial java/awt/Color.<init> (III)V
      // 70b8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 70bb: aload 0
      // 70bc: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 70bf: bipush 0
      // 70c0: bipush 0
      // 70c1: sipush 700
      // 70c4: bipush 25
      // 70c6: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 70c9: aload 0
      // 70ca: getfield CarMaker.onbtgame Z
      // 70cd: ifne 70e6
      // 70d0: aload 0
      // 70d1: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 70d4: aload 0
      // 70d5: getfield CarMaker.btgame [Ljava/awt/Image;
      // 70d8: bipush 0
      // 70d9: aaload
      // 70da: sipush 520
      // 70dd: bipush 0
      // 70de: aconst_null
      // 70df: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 70e2: pop
      // 70e3: goto 70f9
      // 70e6: aload 0
      // 70e7: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 70ea: aload 0
      // 70eb: getfield CarMaker.btgame [Ljava/awt/Image;
      // 70ee: bipush 1
      // 70ef: aaload
      // 70f0: sipush 520
      // 70f3: bipush 0
      // 70f4: aconst_null
      // 70f5: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 70f8: pop
      // 70f9: aload 0
      // 70fa: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 70fd: new java/awt/Font
      // 7100: dup
      // 7101: ldc_w "Arial"
      // 7104: bipush 1
      // 7105: bipush 13
      // 7107: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 710a: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 710d: aload 0
      // 710e: aload 0
      // 710f: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 7112: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 7115: putfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 7118: bipush 4
      // 7119: anewarray 112
      // 711c: dup
      // 711d: bipush 0
      // 711e: ldc_w "Car"
      // 7121: aastore
      // 7122: dup
      // 7123: bipush 1
      // 7124: ldc_w "Code Edit"
      // 7127: aastore
      // 7128: dup
      // 7129: bipush 2
      // 712a: ldc_w "3D Edit"
      // 712d: aastore
      // 712e: dup
      // 712f: bipush 3
      // 7130: ldc_w "Publish"
      // 7133: aastore
      // 7134: astore 3
      // 7135: bipush 4
      // 7136: newarray 10
      // 7138: dup
      // 7139: bipush 0
      // 713a: bipush 0
      // 713b: iastore
      // 713c: dup
      // 713d: bipush 1
      // 713e: bipush 0
      // 713f: iastore
      // 7140: dup
      // 7141: bipush 2
      // 7142: bipush 100
      // 7144: iastore
      // 7145: dup
      // 7146: bipush 3
      // 7147: bipush 90
      // 7149: iastore
      // 714a: astore 4
      // 714c: bipush 4
      // 714d: newarray 10
      // 714f: dup
      // 7150: bipush 0
      // 7151: bipush 0
      // 7152: iastore
      // 7153: dup
      // 7154: bipush 1
      // 7155: bipush 25
      // 7157: iastore
      // 7158: dup
      // 7159: bipush 2
      // 715a: bipush 25
      // 715c: iastore
      // 715d: dup
      // 715e: bipush 3
      // 715f: bipush 0
      // 7160: iastore
      // 7161: astore 5
      // 7163: bipush 4
      // 7164: istore 6
      // 7166: aload 0
      // 7167: getfield CarMaker.carname Ljava/lang/String;
      // 716a: ldc ""
      // 716c: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 716f: ifne 7180
      // 7172: aload 0
      // 7173: getfield CarMaker.loadedfile Z
      // 7176: ifeq 7180
      // 7179: aload 0
      // 717a: getfield CarMaker.sfase I
      // 717d: ifeq 7188
      // 7180: aload 0
      // 7181: bipush 0
      // 7182: putfield CarMaker.tab I
      // 7185: bipush 1
      // 7186: istore 6
      // 7188: bipush 0
      // 7189: istore 7
      // 718b: iload 7
      // 718d: iload 6
      // 718f: if_icmpge 72ae
      // 7192: aload 0
      // 7193: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 7196: new java/awt/Color
      // 7199: dup
      // 719a: sipush 170
      // 719d: sipush 170
      // 71a0: sipush 170
      // 71a3: invokespecial java/awt/Color.<init> (III)V
      // 71a6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 71a9: aload 0
      // 71aa: getfield CarMaker.xm I
      // 71ad: aload 4
      // 71af: bipush 0
      // 71b0: iaload
      // 71b1: if_icmple 71e6
      // 71b4: aload 0
      // 71b5: getfield CarMaker.xm I
      // 71b8: aload 4
      // 71ba: bipush 3
      // 71bb: iaload
      // 71bc: if_icmpge 71e6
      // 71bf: aload 0
      // 71c0: getfield CarMaker.ym I
      // 71c3: ifle 71e6
      // 71c6: aload 0
      // 71c7: getfield CarMaker.ym I
      // 71ca: bipush 25
      // 71cc: if_icmpge 71e6
      // 71cf: aload 0
      // 71d0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 71d3: new java/awt/Color
      // 71d6: dup
      // 71d7: sipush 200
      // 71da: sipush 200
      // 71dd: sipush 200
      // 71e0: invokespecial java/awt/Color.<init> (III)V
      // 71e3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 71e6: aload 0
      // 71e7: getfield CarMaker.tab I
      // 71ea: iload 7
      // 71ec: if_icmpne 7206
      // 71ef: aload 0
      // 71f0: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 71f3: new java/awt/Color
      // 71f6: dup
      // 71f7: sipush 225
      // 71fa: sipush 225
      // 71fd: sipush 225
      // 7200: invokespecial java/awt/Color.<init> (III)V
      // 7203: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7206: aload 0
      // 7207: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 720a: aload 4
      // 720c: aload 5
      // 720e: bipush 4
      // 720f: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // 7212: aload 0
      // 7213: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 7216: new java/awt/Color
      // 7219: dup
      // 721a: bipush 0
      // 721b: bipush 0
      // 721c: bipush 0
      // 721d: invokespecial java/awt/Color.<init> (III)V
      // 7220: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7223: aload 0
      // 7224: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 7227: aload 3
      // 7228: iload 7
      // 722a: aaload
      // 722b: iload 7
      // 722d: bipush 100
      // 722f: imul
      // 7230: bipush 45
      // 7232: iadd
      // 7233: aload 0
      // 7234: getfield CarMaker.ftm Ljava/awt/FontMetrics;
      // 7237: aload 3
      // 7238: iload 7
      // 723a: aaload
      // 723b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 723e: bipush 2
      // 723f: idiv
      // 7240: isub
      // 7241: bipush 17
      // 7243: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7246: aload 0
      // 7247: getfield CarMaker.xm I
      // 724a: aload 4
      // 724c: bipush 0
      // 724d: iaload
      // 724e: if_icmple 728f
      // 7251: aload 0
      // 7252: getfield CarMaker.xm I
      // 7255: aload 4
      // 7257: bipush 3
      // 7258: iaload
      // 7259: if_icmpge 728f
      // 725c: aload 0
      // 725d: getfield CarMaker.ym I
      // 7260: ifle 728f
      // 7263: aload 0
      // 7264: getfield CarMaker.ym I
      // 7267: bipush 25
      // 7269: if_icmpge 728f
      // 726c: aload 0
      // 726d: getfield CarMaker.mouses I
      // 7270: bipush -1
      // 7271: if_icmpne 728f
      // 7274: aload 0
      // 7275: getfield CarMaker.tab I
      // 7278: iload 7
      // 727a: if_icmpeq 7289
      // 727d: aload 0
      // 727e: getfield CarMaker.tab I
      // 7281: bipush 1
      // 7282: if_icmpne 7289
      // 7285: aload 0
      // 7286: invokevirtual CarMaker.savefile ()V
      // 7289: aload 0
      // 728a: iload 7
      // 728c: putfield CarMaker.tab I
      // 728f: bipush 0
      // 7290: istore 8
      // 7292: iload 8
      // 7294: bipush 4
      // 7295: if_icmpge 72a8
      // 7298: aload 4
      // 729a: iload 8
      // 729c: dup2
      // 729d: iaload
      // 729e: bipush 100
      // 72a0: iadd
      // 72a1: iastore
      // 72a2: iinc 8 1
      // 72a5: goto 7292
      // 72a8: iinc 7 1
      // 72ab: goto 718b
      // 72ae: aload 0
      // 72af: invokevirtual CarMaker.drawms ()V
      // 72b2: aload 0
      // 72b3: invokevirtual CarMaker.ctachm ()V
      // 72b6: aload 0
      // 72b7: invokevirtual CarMaker.repaint ()V
      // 72ba: aload 0
      // 72bb: getfield CarMaker.exwist Z
      // 72be: ifne 72d0
      // 72c1: aload 0
      // 72c2: getfield CarMaker.thredo Ljava/lang/Thread;
      // 72c5: pop
      // 72c6: iload 2
      // 72c7: i2l
      // 72c8: invokestatic java/lang/Thread.sleep (J)V
      // 72cb: goto 72d0
      // 72ce: astore 7
      // 72d0: goto 01c7
      // 72d3: aload 0
      // 72d4: getfield CarMaker.rd Ljava/awt/Graphics2D;
      // 72d7: invokevirtual java/awt/Graphics2D.dispose ()V
      // 72da: invokestatic java/lang/System.gc ()V
      // 72dd: getstatic Madness.endadv I
      // 72e0: bipush 2
      // 72e1: if_icmpne 72e7
      // 72e4: invokestatic Madness.advopen ()V
      // 72e7: return
      // try (2008 -> 2012): 2013 java/lang/Exception
      // try (4708 -> 4811): 4812 java/lang/Exception
      // try (4865 -> 4968): 4969 java/lang/Exception
      // try (5035 -> 5054): 5055 java/lang/Exception
      // try (5085 -> 5104): 5105 java/lang/Exception
      // try (5135 -> 5154): 5155 java/lang/Exception
      // try (5793 -> 5938): 5939 java/lang/Exception
      // try (7165 -> 7352): 7353 java/lang/Exception
      // try (8057 -> 8190): 8191 java/lang/Exception
      // try (10009 -> 10014): 10015 java/lang/InterruptedException
      // try (10864 -> 10885): 10886 java/lang/Exception
      // try (12167 -> 12334): 12335 java/lang/Exception
      // try (12391 -> 12479): 12480 java/lang/Exception
      // try (13256 -> 13592): 13593 java/lang/Exception
      // try (13653 -> 13714): 13715 java/lang/Exception
      // try (14041 -> 14047): 14048 java/lang/InterruptedException
   }

   public void ctachm() {
      int var1 = -1;

      for (int var2 = 0; var2 < this.btn; var2++) {
         if (Math.abs(this.xm - this.bx[var2]) < this.bw[var2] / 2 + 12 && Math.abs(this.ym - this.by[var2]) < 14 && this.mouses == 1) {
            this.pessd[var2] = true;
         } else {
            this.pessd[var2] = false;
         }

         if (Math.abs(this.xm - this.bx[var2]) < this.bw[var2] / 2 + 12 && Math.abs(this.ym - this.by[var2]) < 14 && this.mouses == -1) {
            var1 = var2;
         }
      }

      if (this.mouses == -1) {
         this.mouses = 0;
      }

      if (this.tab == 0) {
         if (this.sfase == 0) {
            if (var1 == 0) {
               this.sfase = 1;
               var1 = -1;
               this.hidefields();
            }

            if (var1 == 1) {
               if (!this.carname.equals("")) {
                  this.srch.setText(this.carname);
                  this.sfase = 2;
                  var1 = -1;
                  this.hidefields();
               } else {
                  JOptionPane.showMessageDialog(null, "Please Select a Car to Rename!\n", "Car Maker", 1);
               }
            }

            if (var1 == 2) {
               this.delcar(this.carname);
            }

            if (var1 == 3) {
               this.sfase = 3;
               var1 = -1;
               this.hidefields();
            }
         }

         if (this.sfase == 1) {
            if (var1 == 0) {
               this.newcar(this.srch.getText());
               var1 = -1;
            }

            if (var1 == 1) {
               this.srch.setText("");
               this.sfase = 0;
               var1 = -1;
               this.hidefields();
            }
         }

         if (this.sfase == 2) {
            if (var1 == 0) {
               this.rencar(this.srch.getText());
               var1 = -1;
            }

            if (var1 == 1) {
               this.srch.setText("");
               this.sfase = 0;
               var1 = -1;
               this.hidefields();
            }
         }

         if (this.sfase == 3) {
            if (var1 == 0) {
               File var27 = null;
               FileDialog var3 = new FileDialog(new Frame(), "Car Maker - Wavefront OBJ Import");
               var3.setFile("*.obj");
               var3.setMode(0);
               var3.setVisible(true);

               try {
                  if (var3.getFile() != null) {
                     var27 = new File("" + var3.getDirectory() + "" + var3.getFile() + "");
                  }
               } catch (Exception var23) {
               }

               if (var27 != null) {
                  this.setCursor(new Cursor(3));
                  byte var4 = 0;
                  if (this.tutok) {
                     var4 = -70;
                  }

                  this.rd.setColor(new Color(225, 225, 225));
                  this.rd.fillRect(116, 246 + var4, 468, 50);
                  this.rd.setColor(new Color(0, 0, 0));
                  this.rd.setFont(new Font("Arial", 1, 13));
                  this.ftm = this.rd.getFontMetrics();
                  this.rd
                     .drawString(
                        "Reading " + var27.getName() + ", please wait...",
                        350 - this.ftm.stringWidth("Reading " + var27.getName() + ", please wait...") / 2,
                        276 + var4
                     );
                  this.repaint();
                  int[] var5 = new int[6000];
                  int[] var6 = new int[6000];
                  int[] var7 = new int[6000];
                  int var8 = 0;
                  int[][] var9 = new int[600][100];
                  int[] var10 = new int[600];
                  int var11 = 0;
                  if (var27.exists()) {
                     try {
                        BufferedReader var12 = new BufferedReader(new FileReader(var27));
                        String var13 = null;
                        boolean var14 = false;
                        boolean var15 = false;

                        while ((var13 = var12.readLine()) != null) {
                           if (var13.startsWith("v ")) {
                              if (var8 < 6000) {
                                 this.multf10 = true;
                                 var5[var8] = this.objvalue(var13, 0);
                                 var6[var8] = this.objvalue(var13, 1);
                                 var7[var8] = this.objvalue(var13, 2);
                                 var8++;
                              } else {
                                 var14 = true;
                              }
                           }

                           if (var13.startsWith("f ")) {
                              if (var11 < 600) {
                                 this.multf10 = false;
                                 this.objfacend = false;

                                 for (var10[var11] = 0; !this.objfacend && var10[var11] < 100; var10[var11]++) {
                                    var9[var11][var10[var11]] = this.objvalue(var13, var10[var11]);
                                 }

                                 var11++;
                              } else {
                                 var15 = true;
                              }
                           }
                        }

                        if (var14) {
                           JOptionPane.showMessageDialog(
                              null,
                              "Warning!\nThe number of Vertices in file "
                                 + var27.getName()
                                 + " exceeded the maximum of 6000 that the Car Maker can read!     \n\nPlease choose a simpler model to import.\n \n",
                              "Car Maker",
                              0
                           );
                        }

                        if (var15) {
                           JOptionPane.showMessageDialog(
                              null,
                              "Warning!\nThe number of Faces in file "
                                 + var27.getName()
                                 + " exceeded the maximum of 600 that the Car Maker can read!     \n\nPlease choose a simpler model to import.\n \n",
                              "Car Maker",
                              0
                           );
                        }

                        var12.close();
                        Object var200 = null;
                     } catch (Exception var26) {
                        JOptionPane.showMessageDialog(null, "Unable to load file! Error Deatials:\n" + var26, "Car Maker", 1);
                     }

                     this.rd.setColor(new Color(225, 225, 225));
                     this.rd.fillRect(116, 246 + var4, 468, 50);
                     this.rd.setColor(new Color(0, 0, 0));
                     this.rd.setFont(new Font("Arial", 1, 13));
                     this.ftm = this.rd.getFontMetrics();
                     this.rd
                        .drawString(
                           "Importing " + var27.getName() + ", please wait...",
                           350 - this.ftm.stringWidth("Importing " + var27.getName() + ", please wait...") / 2,
                           276 + var4
                        );
                     this.repaint();
                     this.carname = var27.getName();
                     if (this.carname.endsWith(".obj")) {
                        this.carname = this.carname.substring(0, this.carname.length() - 4);
                     }

                     String var201 = "\n// imported car: "
                        + this.carname
                        + "\n---------------------\n\n// Please read the helpful information about importing cars found at:\n// http://www.needformadness.com/developer/extras.html\n\n\n";

                     for (int var209 = 0; var209 < var11; var209++) {
                        var201 = var201 + "<p>\nc(200,200,220)\n\n";

                        for (int var215 = 0; var215 < var10[var209]; var215++) {
                           if (var9[var209][var215] < 6000) {
                              int var221 = var9[var209][var215];
                              var201 = var201 + "p(" + var5[var221] + "," + -var6[var221] + "," + var7[var221] + ")\n";
                           }
                        }

                        var201 = var201 + "</p>\n\n";
                     }

                     var201 = var201 + "\n\n\n\n";
                     var27 = new File("" + Madness.fpath + "mycars/");
                     if (!var27.exists()) {
                        var27.mkdirs();
                     }

                     var27 = new File("" + Madness.fpath + "mycars/" + this.carname + ".rad");
                     int var210 = 0;
                     if (var27.exists()) {
                        var210 = JOptionPane.showConfirmDialog(
                           null, "Another car with the name '" + this.carname + "' already exists, replace it?      \n", "Car Maker", 0
                        );
                     }

                     if (var210 == 0) {
                        try {
                           BufferedWriter var216 = new BufferedWriter(new FileWriter(var27));
                           var216.write(var201);
                           var216.close();
                           Object var217 = null;
                           if (var27.exists()) {
                              this.sfase = 0;
                              this.hidefields();
                              this.tabed = -1;
                           } else {
                              this.carname = "";
                              JOptionPane.showMessageDialog(null, "Failed to create car, unknown reason!\n", "Car Maker", 1);
                           }
                        } catch (Exception var22) {
                           this.carname = "";
                           JOptionPane.showMessageDialog(null, "Unable to create file! Error Deatials:\n" + var22, "Car Maker", 1);
                        }
                     }
                  } else {
                     JOptionPane.showMessageDialog(null, "Error, " + var27.getName() + " was not found!", "Car Maker", 1);
                  }

                  this.setCursor(new Cursor(0));
               }
            }

            if (var1 == 1) {
               this.sfase = 4;
               var1 = -1;
            }

            if (var1 == 2) {
               this.sfase = 0;
               var1 = -1;
            }
         }

         if (this.sfase == 4) {
            if (var1 == 0) {
               File var30 = null;
               FileDialog var38 = new FileDialog(new Frame(), "Car Maker - Wavefront OBJ Import");
               var38.setFile("" + this.carname + ".obj");
               var38.setMode(1);
               var38.setVisible(true);

               try {
                  if (var38.getFile() != null) {
                     var30 = new File("" + var38.getDirectory() + "" + var38.getFile() + "");
                  }
               } catch (Exception var21) {
               }

               if (var30 != null) {
                  int var76 = 0;
                  if (var30.exists()) {
                     var76 = JOptionPane.showConfirmDialog(null, "File " + var30.getName() + " already exists, replace it?      \n", "Car Maker", 0);
                  }

                  if (var76 == 0) {
                     this.setCursor(new Cursor(3));
                     this.setupo();
                     int[] var112 = new int[6000];
                     int[] var142 = new int[6000];
                     int[] var156 = new int[6000];
                     int var171 = 0;
                     String var180 = "";

                     for (int var187 = 0; var187 < this.o.npl; var187++) {
                        for (int var195 = 0; var195 < this.o.p[var187].n; var195++) {
                           boolean var204 = false;

                           for (int var211 = 0; var211 < var171; var211++) {
                              if (var112[var211] == this.o.p[var187].ox[var195]
                                 && var142[var211] == this.o.p[var187].oy[var195]
                                 && var156[var211] == this.o.p[var187].oz[var195]) {
                                 var204 = true;
                              }
                           }

                           if (!var204 && var171 < 6000) {
                              var112[var171] = this.o.p[var187].ox[var195];
                              var142[var171] = this.o.p[var187].oy[var195];
                              var156[var171] = this.o.p[var187].oz[var195];
                              var171++;
                           }
                        }
                     }

                     for (int var188 = 0; var188 < var171; var188++) {
                        var180 = var180 + "v " + var112[var188] / 10.0F + " " + -var142[var188] / 10.0F + " " + var156[var188] / 10.0F + "\n";
                     }

                     for (int var189 = 0; var189 < this.o.npl; var189++) {
                        if (this.o.p[var189].wz == 0) {
                           var180 = var180 + "f";

                           for (int var196 = 0; var196 < this.o.p[var189].n; var196++) {
                              var180 = var180 + " ";

                              for (int var205 = 0; var205 < var171; var205++) {
                                 if (var112[var205] == this.o.p[var189].ox[var196]
                                    && var142[var205] == this.o.p[var189].oy[var196]
                                    && var156[var205] == this.o.p[var189].oz[var196]) {
                                    var180 = var180 + "" + (var205 + 1);
                                 }
                              }
                           }

                           var180 = var180 + "\n";
                        }
                     }

                     try {
                        BufferedWriter var190 = new BufferedWriter(new FileWriter(var30));
                        var190.write(var180);
                        var190.close();
                        Object var191 = null;
                        if (var30.exists()) {
                           JOptionPane.showMessageDialog(
                              null, "Car has been successfully exported to:\n" + var30.getAbsolutePath() + "          \n \n", "Car Maker", 1
                           );
                           this.sfase = 0;
                           this.hidefields();
                           this.tabed = -1;
                        } else {
                           JOptionPane.showMessageDialog(null, "Failed to export car, unknown reason!\n", "Car Maker", 1);
                        }
                     } catch (Exception var20) {
                        JOptionPane.showMessageDialog(null, "Unable to create exported file! Error Deatials:\n" + var20, "Car Maker", 1);
                     }

                     this.setCursor(new Cursor(0));
                  }
               }
            }

            if (var1 == 1) {
               this.sfase = 0;
               var1 = -1;
            }
         }
      }

      if (this.tab == 1) {
         if (var1 == 0) {
            if (this.prefs) {
               this.prefs = false;
            } else {
               this.prefs = true;
            }
         }

         if (var1 == 1 || var1 == 2) {
            this.savefile();
            if (var1 == 2) {
               this.tab = 2;
            }
         }

         if (!this.mirror) {
            boolean var34 = false;
            if (var1 == 4) {
               if (this.sls != -1 && this.sle != -1 && this.editor.getSelectedText().equals(this.srch.getText())) {
                  this.editor.replaceText(this.rplc.getText(), this.sls, this.sle);
                  this.sls = -1;
                  this.sle = -1;
                  var34 = true;

                  try {
                     Thread.sleep(100L);
                  } catch (InterruptedException var19) {
                  }
               }

               var1 = 3;
            }

            if (var1 == 3 && !this.srch.getText().equals("")) {
               this.editor.requestFocus();
               this.sls = this.editor.getText().indexOf(this.srch.getText(), this.editor.getSelectionEnd());
               if (this.sls != -1) {
                  this.sle = this.sls + this.srch.getText().length();
                  this.editor.select(this.sls, this.sle);
               } else if (!var34) {
                  JOptionPane.showMessageDialog(null, "Cannot find  '" + this.srch.getText() + "'  from Cursor position    ", "Car Maker", 1);
               }
            }
         } else {
            if (var1 == 3 || var1 == 4 || var1 == 5) {
               String var31 = "" + this.editor.getSelectedText() + "\n";
               String var39 = "\n\n";
               if (this.cntpls == 1) {
                  var39 = var39 + "// Mirror of the polygon above along the ";
               } else {
                  var39 = var39 + "// Mirror of the " + this.cntpls + " polygons above along the ";
               }

               if (var1 == 3) {
                  var39 = var39 + "X axis:";
               }

               if (var1 == 4) {
                  var39 = var39 + "Y axis:";
               }

               if (var1 == 5) {
                  var39 = var39 + "Z axis:";
               }

               var39 = var39 + "\n\n";
               int var77 = 0;
               int var113 = var31.indexOf("\n", 0);

               while (var113 != -1 && var77 < var31.length()) {
                  String var143 = var31.substring(var77, var113);
                  var143 = var143.trim();
                  var77 = var113 + 1;
                  var113 = var31.indexOf("\n", var77);
                  if (var143.startsWith("fs(-")) {
                     var143 = "fs(" + var143.substring(4, var143.length()) + "";
                  } else if (var143.startsWith("fs(")) {
                     var143 = "fs(-" + var143.substring(3, var143.length()) + "";
                  }

                  if (var1 == 3) {
                     if (var143.startsWith("p(-")) {
                        var143 = "p(" + var143.substring(3, var143.length()) + "";
                     } else if (var143.startsWith("p(")) {
                        var143 = "p(-" + var143.substring(2, var143.length()) + "";
                     }
                  }

                  if (var1 == 4 && var143.startsWith("p(")) {
                     int var157 = var143.indexOf(",", 0);
                     if (var157 >= 0) {
                        if (var143.startsWith(",-", var157)) {
                           var143 = "" + var143.substring(0, var157) + "," + var143.substring(var157 + 2, var143.length()) + "";
                        } else if (var143.startsWith(",", var157)) {
                           var143 = "" + var143.substring(0, var157) + ",-" + var143.substring(var157 + 1, var143.length()) + "";
                        }
                     }
                  }

                  if (var1 == 5 && var143.startsWith("p(")) {
                     int var158 = var143.indexOf(",", 0);
                     var158 = var143.indexOf(",", var158 + 1);
                     if (var158 >= 0) {
                        if (var143.startsWith(",-", var158)) {
                           var143 = "" + var143.substring(0, var158) + "," + var143.substring(var158 + 2, var143.length()) + "";
                        } else if (var143.startsWith(",", var158)) {
                           var143 = "" + var143.substring(0, var158) + ",-" + var143.substring(var158 + 1, var143.length()) + "";
                        }
                     }
                  }

                  var39 = var39 + "" + var143 + "\n";
               }

               var39 = var39 + "\n// End of mirror";
               this.editor.insertText(var39, this.editor.getSelectionEnd());
            }

            if (var1 == 6) {
               this.polynum = 0;
               int var33 = this.editor.getText().lastIndexOf("</p>", this.editor.getSelectionStart());
               boolean var43 = false;

               while (var33 >= 0) {
                  if (!var43) {
                     var33 = this.editor.getText().lastIndexOf("<p>", var33);
                     if (var33 != -1) {
                        var43 = true;
                        this.polynum++;
                     }
                  } else {
                     var33 = this.editor.getText().lastIndexOf("</p>", var33);
                     if (var33 != -1) {
                        var43 = false;
                     }
                  }

                  var33--;
               }

               this.prflk = 0;
               this.tab = 2;
            }
         }

         var1 = -1;
      }

      if (this.tab == 2) {
         byte var35 = 0;
         if (this.dtab == 1) {
            if (this.o.colok != 2) {
               if (var1 == 0) {
                  JOptionPane.showMessageDialog(
                     null,
                     "Car Maker will attempt now to find the first and second colors automatically.\nPlease make sure that they are the correct colors!\n\nPlease note that these are also the colors that will be editable in the multiplayer game.      ",
                     "Car Maker",
                     1
                  );
                  String var46 = "" + this.editor.getText() + "\n";
                  int var80 = 0;
                  int var114 = var46.indexOf("\n", 0);
                  byte var145 = 0;
                  String var160 = "";
                  String var172 = "";

                  while (var114 != -1 && var80 < var46.length() && var145 != 2) {
                     String var182 = var46.substring(var80, var114);
                     var182 = var182.trim();
                     var80 = var114 + 1;
                     var114 = var46.indexOf("\n", var80);
                     if (var182.startsWith("c(")) {
                        String var192 = var182.substring(1, var182.indexOf(")") + 1);
                        if (var145 == 1 && !var192.equals(var172)) {
                           var160 = var160 + "2ndColor" + var192 + "\n\n\n";
                           var145 = 2;
                        }

                        if (var145 == 0) {
                           var172 = var192;
                           var160 = "1stColor" + var192 + "\n";
                           var145 = 1;
                        }
                     }
                  }

                  if (var145 == 0) {
                     var160 = "1stColor(255,0,0)\n2ndColor(0,0,255)\n\n\n";
                     var145 = 2;
                  }

                  if (var145 == 1) {
                     var160 = var160 + "2ndColor(0,0,255)\n\n\n";
                     var145 = 2;
                  }

                  int var184 = this.editor.getText().indexOf("<p>", 0);
                  this.editor.insertText(var160, var184);
                  this.editor.select(var184, var184 + var160.length() - 2);
                  this.breakbond = true;
                  this.tab = 1;
               }

               var35 = 1;
            } else {
               if (var1 == 0) {
                  this.ofcol = "(" + this.o.fcol[0] + "," + this.o.fcol[1] + "," + this.o.fcol[2] + ")";
                  int var44 = this.editor.getText().indexOf(this.ofcol, 0);
                  int var78 = var44;

                  while (var44 != -1) {
                     this.editor.replaceText(this.fcol, var44, var44 + this.ofcol.length());
                     var44 = this.editor.getText().indexOf(this.ofcol, var44 + 1);
                  }

                  this.ofcol = this.fcol;
                  this.editor.select(var78 - 8, var78 - 8);
                  this.savefile();
                  this.o.fcol[0] = Color.getHSBColor(this.fhsb[0], this.fhsb[2], this.fhsb[1]).getRed();
                  this.o.fcol[1] = Color.getHSBColor(this.fhsb[0], this.fhsb[2], this.fhsb[1]).getGreen();
                  this.o.fcol[2] = Color.getHSBColor(this.fhsb[0], this.fhsb[2], this.fhsb[1]).getBlue();
               }

               if (var1 == 1) {
                  this.oscol = "(" + this.o.scol[0] + "," + this.o.scol[1] + "," + this.o.scol[2] + ")";
                  int var45 = this.editor.getText().indexOf(this.oscol, 0);
                  int var79 = var45;

                  while (var45 != -1) {
                     this.editor.replaceText(this.scol, var45, var45 + this.oscol.length());
                     var45 = this.editor.getText().indexOf(this.oscol, var45 + 1);
                  }

                  this.oscol = this.scol;
                  this.editor.select(var79 - 8, var79 - 8);
                  this.savefile();
                  this.o.scol[0] = Color.getHSBColor(this.shsb[0], this.shsb[2], this.shsb[1]).getRed();
                  this.o.scol[1] = Color.getHSBColor(this.shsb[0], this.shsb[2], this.shsb[1]).getGreen();
                  this.o.scol[2] = Color.getHSBColor(this.shsb[0], this.shsb[2], this.shsb[1]).getBlue();
               }

               var35 = 2;
            }
         }

         if (this.dtab == 2) {
            if (var1 == 9) {
               this.scale[0] = 100;
               this.scale[1] = 100;
               this.scale[2] = 100;
            }

            if (var1 == 0 || var1 == 1 || var1 == 6 || var1 == 7 || var1 == 9) {
               if (var1 == 0 || var1 == 6) {
                  this.scale[0] = this.scale[0] - 5;
               }

               if (var1 == 1 || var1 == 7) {
                  this.scale[0] = this.scale[0] + 5;
               }

               if (this.scale[0] < 0) {
                  this.scale[0] = 0;
               }

               int var47 = this.editor.getText().indexOf("\nScaleX(", 0);
               if (var47 != -1) {
                  int var81 = this.editor.getText().indexOf(")", ++var47);
                  int var115 = this.editor.getText().indexOf("\n", var47);
                  if (var115 > var81) {
                     this.editor.replaceText("ScaleX(" + this.scale[0] + ")", var47, var81 + 1);
                  } else {
                     this.editor.replaceText("ScaleX(" + this.scale[0] + ")", var47, var115);
                  }
               } else {
                  int var82 = this.editor.getText().indexOf("<p>", 0);
                  int var116 = this.editor.getText().indexOf("\nScale", 0);
                  if (var116 < var82 && var116 != -1) {
                     this.editor.insertText("\nScaleX(" + this.scale[0] + ")", var116);
                  } else {
                     this.editor.insertText("ScaleX(" + this.scale[0] + ")\n\n\n", var82);
                  }
               }
            }

            if (var1 == 2 || var1 == 3 || var1 == 6 || var1 == 7 || var1 == 9) {
               if (var1 == 2 || var1 == 6) {
                  this.scale[1] = this.scale[1] - 5;
               }

               if (var1 == 3 || var1 == 7) {
                  this.scale[1] = this.scale[1] + 5;
               }

               if (this.scale[1] < 0) {
                  this.scale[1] = 0;
               }

               int var49 = this.editor.getText().indexOf("\nScaleY(", 0);
               if (var49 != -1) {
                  int var83 = this.editor.getText().indexOf(")", ++var49);
                  int var117 = this.editor.getText().indexOf("\n", var49);
                  if (var117 > var83) {
                     this.editor.replaceText("ScaleY(" + this.scale[1] + ")", var49, var83 + 1);
                  } else {
                     this.editor.replaceText("ScaleY(" + this.scale[1] + ")", var49, var117);
                  }
               } else {
                  int var84 = this.editor.getText().indexOf("<p>", 0);
                  int var118 = this.editor.getText().indexOf("\nScale", 0);
                  if (var118 < var84 && var118 != -1) {
                     this.editor.insertText("\nScaleY(" + this.scale[1] + ")", var118);
                  } else {
                     this.editor.insertText("ScaleY(" + this.scale[1] + ")\n\n\n", var84);
                  }
               }
            }

            if (var1 == 4 || var1 == 5 || var1 == 6 || var1 == 7 || var1 == 9) {
               if (var1 == 4 || var1 == 6) {
                  this.scale[2] = this.scale[2] - 5;
               }

               if (var1 == 5 || var1 == 7) {
                  this.scale[2] = this.scale[2] + 5;
               }

               if (this.scale[2] < 0) {
                  this.scale[2] = 0;
               }

               int var51 = this.editor.getText().indexOf("\nScaleZ(", 0);
               if (var51 != -1) {
                  int var85 = this.editor.getText().indexOf(")", ++var51);
                  int var119 = this.editor.getText().indexOf("\n", var51);
                  if (var119 > var85) {
                     this.editor.replaceText("ScaleZ(" + this.scale[2] + ")", var51, var85 + 1);
                  } else {
                     this.editor.replaceText("ScaleZ(" + this.scale[2] + ")", var51, var119);
                  }
               } else {
                  int var86 = this.editor.getText().indexOf("<p>", 0);
                  int var120 = this.editor.getText().indexOf("\nScale", 0);
                  if (var120 < var86 && var120 != -1) {
                     this.editor.insertText("\nScaleZ(" + this.scale[2] + ")", var120);
                  } else {
                     this.editor.insertText("ScaleZ(" + this.scale[2] + ")\n\n\n", var86);
                  }
               }
            }

            if (var1 == 0 || var1 == 1 || var1 == 2 || var1 == 3 || var1 == 4 || var1 == 5 || var1 == 6 || var1 == 7 || var1 == 9) {
               this.setupo();
            }

            if (var1 == 8) {
               this.savefile();
               this.oscale[0] = this.scale[0];
               this.oscale[1] = this.scale[1];
               this.oscale[2] = this.scale[2];
            }

            if (var1 == 10 || var1 == 11 || var1 == 12 || var1 == 13 || var1 == 14 || var1 == 15 || var1 == 16 || var1 == 17 || var1 == 18) {
               try {
                  String var53 = "" + this.editor.getText() + "\n";
                  String var87 = "";
                  int var121 = 0;
                  int var147 = var53.indexOf("\n", 0);

                  while (var147 != -1 && var121 < var53.length()) {
                     String var161 = var53.substring(var121, var147);
                     var161 = var161.trim();
                     var121 = var147 + 1;
                     var147 = var53.indexOf("\n", var121);
                     if (var161.startsWith("p(")) {
                        int var173 = var161.indexOf(",", 0);
                        int var185 = var161.indexOf(",", var173 + 1);
                        int var193 = var161.indexOf(")", var185 + 1);
                        if (var173 != -1 && var185 != -1 && var193 != -1) {
                           int var197 = Float.valueOf(var161.substring(2, var173)).intValue();
                           int var206 = Float.valueOf(var161.substring(var173 + 1, var185)).intValue();
                           int var212 = Float.valueOf(var161.substring(var185 + 1, var193)).intValue();
                           if (var1 == 10) {
                              int var218 = var206;
                              var206 = var212;
                              var212 = -var218;
                           }

                           if (var1 == 11) {
                              var197 += 10;
                           }

                           if (var1 == 12) {
                              var197 -= 10;
                           }

                           if (var1 == 13) {
                              int var219 = var197;
                              var197 = -var212;
                              var212 = var219;
                           }

                           if (var1 == 14) {
                              var206 += 10;
                           }

                           if (var1 == 15) {
                              var206 -= 10;
                           }

                           if (var1 == 16) {
                              int var220 = var206;
                              var206 = -var197;
                              var197 = var220;
                           }

                           if (var1 == 17) {
                              var212 += 10;
                           }

                           if (var1 == 18) {
                              var212 -= 10;
                           }

                           var87 = var87 + "p(" + var197 + "," + var206 + "," + var212 + ")" + var161.substring(var193 + 1, var161.length()) + "\n";
                        } else {
                           var87 = var87 + "" + var161 + "\n";
                        }
                     } else {
                        var87 = var87 + "" + var161 + "\n";
                     }
                  }

                  this.editor.setText(var87);
                  this.setupo();
                  this.changed2 = true;
               } catch (Exception var25) {
               }
            }

            if (var1 == 19) {
               this.editor.setText(this.lastedo);
               this.setupo();
               this.changed2 = false;
            }

            if (var1 == 20 && this.changed2) {
               int var54 = JOptionPane.showConfirmDialog(
                  null, "Saving now will permanently change the point locations & numbers entered in the code!      \n\nContinue?", "Car Maker", 0
               );
               if (var54 == 0) {
                  this.editor.setText(this.editor.getText().trim() + "\n\n\n\n");
                  this.savefile();
                  this.changed2 = false;
               }
            }

            var35 = 21;
         }

         if (this.dtab == 3) {
            if (var1 == 0 || var1 == 2 || this.defnow) {
               if (this.defnow) {
                  this.defnow = false;
                  this.repaint();
                  JOptionPane.showMessageDialog(
                     null,
                     "Car Maker will setup default Front and Back Wheels positions and adjustments.\n\nEnter the desired positions and adjustments then press ' Apply ' to view!\nDon't forget to press ' Save ' when finished!",
                     "Car Maker",
                     1
                  );
               }

               byte var55 = 0;

               try {
                  int var88 = Float.valueOf(this.wv[10].getText()).intValue();
                  if (var88 <= 0) {
                     var55 = 1;
                  }

                  var88 = Float.valueOf(this.wv[2].getText()).intValue();
                  if (var88 >= 0) {
                     var55 = 2;
                  }

                  var88 = Float.valueOf(this.wv[8].getText()).intValue();
                  if (var88 <= 0) {
                     var55 = 3;
                  }

                  var88 = Float.valueOf(this.wv[0].getText()).intValue();
                  if (var88 <= 0) {
                     var55 = 4;
                  }

                  var88 = Float.valueOf(this.wv[15].getText()).intValue();
                  if (var88 > 40) {
                     this.wv[15].setText("40");
                  }

                  if (var88 < -40) {
                     this.wv[15].setText("-40");
                  }

                  var88 = Float.valueOf(this.wv[7].getText()).intValue();
                  if (var88 > 40) {
                     this.wv[7].setText("40");
                  }

                  if (var88 < -40) {
                     this.wv[7].setText("-40");
                  }
               } catch (Exception var18) {
               }

               if (var55 == 1) {
                  JOptionPane.showMessageDialog(
                     null,
                     "ERROR:\nThe Z location value of the FRONT Wheels must be greater then zero! (it should have a +ve value)\nZ :  '"
                        + this.wv[10].getText()
                        + "'  is less or equal to zero, where it should have +ve value!",
                     "Car Maker",
                     1
                  );
               }

               if (var55 == 2) {
                  JOptionPane.showMessageDialog(
                     null,
                     "ERROR:\nThe Z location value of the BACK Wheels must be smaller then zero! (it should have a -ve value)\nZ :  '"
                        + this.wv[2].getText()
                        + "'  is bigger or equal to zero, where it should have -ve value!",
                     "Car Maker",
                     1
                  );
               }

               if (var55 == 3) {
                  JOptionPane.showMessageDialog(
                     null,
                     "ERROR:\nThe ±X location value of the FRONT or BACK Wheels must be greater then zero! (it should have a +ve value)\n±X :  '"
                        + this.wv[8].getText()
                        + "'  is less or equal to zero, where it should have +ve value!",
                     "Car Maker",
                     1
                  );
               }

               if (var55 == 4) {
                  JOptionPane.showMessageDialog(
                     null,
                     "ERROR:\nThe ±X location value of the FRONT or BACK Wheels must be greater then zero! (it should have a +ve value)\n±X :  '"
                        + this.wv[0].getText()
                        + "'  is less or equal to zero, whenr it should have +ve value!",
                     "Car Maker",
                     1
                  );
               }

               if (var55 == 0) {
                  String var94 = "" + this.editor.getText() + "\n";
                  String var122 = "";
                  int var148 = 0;
                  int var163 = var94.indexOf("\n", 0);

                  while (var163 != -1 && var148 < var94.length()) {
                     String var174 = var94.substring(var148, var163);
                     var174 = var174.trim();
                     var148 = var163 + 1;
                     var163 = var94.indexOf("\n", var148);
                     if (!var174.startsWith("rims(") && !var174.startsWith("gwgr(") && !var174.startsWith("w(")) {
                        var122 = var122 + "" + var174 + "\n";
                     } else {
                        var122 = var122.trim();
                        var122 = var122 + "\n";
                     }
                  }

                  var122 = var122.trim();
                  var122 = var122 + "\n\n\ngwgr(" + this.wv[15].getText() + ")\n";
                  String var176 = "140,140,140";
                  if (this.rplc.getText().startsWith("(") && this.rplc.getText().endsWith(")")) {
                     var176 = this.rplc.getText().substring(1, this.rplc.getText().length() - 1);
                  }

                  var122 = var122 + "rims(" + var176 + "," + this.wv[13].getText() + "," + this.wv[14].getText() + ")\n";
                  var122 = var122
                     + "w(-"
                     + this.wv[8].getText()
                     + ","
                     + this.wv[9].getText()
                     + ","
                     + this.wv[10].getText()
                     + ",11,"
                     + this.wv[12].getText()
                     + ","
                     + this.wv[11].getText()
                     + ")\n";
                  var122 = var122
                     + "w("
                     + this.wv[8].getText()
                     + ","
                     + this.wv[9].getText()
                     + ","
                     + this.wv[10].getText()
                     + ",11,-"
                     + this.wv[12].getText()
                     + ","
                     + this.wv[11].getText()
                     + ")\n";
                  var122 = var122 + "\ngwgr(" + this.wv[7].getText() + ")\n";
                  var176 = "140,140,140";
                  if (this.srch.getText().startsWith("(") && this.srch.getText().endsWith(")")) {
                     var176 = this.srch.getText().substring(1, this.srch.getText().length() - 1);
                  }

                  var122 = var122 + "rims(" + var176 + "," + this.wv[5].getText() + "," + this.wv[6].getText() + ")\n";
                  var122 = var122
                     + "w(-"
                     + this.wv[0].getText()
                     + ","
                     + this.wv[1].getText()
                     + ","
                     + this.wv[2].getText()
                     + ",0,"
                     + this.wv[4].getText()
                     + ","
                     + this.wv[3].getText()
                     + ")\n";
                  var122 = var122
                     + "w("
                     + this.wv[0].getText()
                     + ","
                     + this.wv[1].getText()
                     + ","
                     + this.wv[2].getText()
                     + ",0,-"
                     + this.wv[4].getText()
                     + ","
                     + this.wv[3].getText()
                     + ")\n\n\n\n";
                  this.editor.setText(var122);
                  this.forwheels = true;
                  this.setupo();
                  this.forwheels = false;
                  this.aply1 = ""
                     + this.wv[0].getText()
                     + ""
                     + this.wv[1].getText()
                     + ""
                     + this.wv[2].getText()
                     + ""
                     + this.wv[3].getText()
                     + ""
                     + this.wv[4].getText()
                     + ""
                     + this.srch.getText()
                     + ""
                     + this.wv[5].getText()
                     + ""
                     + this.wv[6].getText()
                     + ""
                     + this.wv[7].getText()
                     + "";
                  this.aply2 = ""
                     + this.wv[8].getText()
                     + ""
                     + this.wv[9].getText()
                     + ""
                     + this.wv[10].getText()
                     + ""
                     + this.wv[11].getText()
                     + ""
                     + this.wv[12].getText()
                     + ""
                     + this.rplc.getText()
                     + ""
                     + this.wv[13].getText()
                     + ""
                     + this.wv[14].getText()
                     + ""
                     + this.wv[15].getText()
                     + "";
                  this.aplyd1 = false;
                  this.aplyd2 = false;
                  this.changed2 = true;
               }
            }

            if (var1 == 1 || var1 == 3) {
               if (!this.o.errd) {
                  this.savefile();
                  this.changed2 = false;
               } else {
                  JOptionPane.showMessageDialog(null, "Unable to Save, press  [ Apply ]  to find out why!", "Car Maker", 1);
               }
            }

            var35 = 4;
         }

         if (this.dtab == 4) {
            if (!this.statdef) {
               if (var1 == 0) {
                  this.carsel = this.simcar.getSelectedIndex();
                  int var60 = 0;

                  for (int var101 = 0; var101 < 5; var101++) {
                     this.stat[var101] = this.carstat[this.carsel][var101];
                     this.rstat[var101] = this.stat[var101];
                     var60 += this.stat[var101];
                  }

                  this.clsel = 4 - (var60 - 520) / 40;
                  this.cls.select(this.clsel);
                  if (this.simcar.getItemCount() == 16) {
                     this.simcar.add(this.rd, "   ");
                  }

                  this.statdef = true;
                  this.changed2 = true;
               }

               var35 = 1;
            } else {
               for (int var56 = 0; var56 < 5; var56++) {
                  int var95 = 0;
                  if (var1 == 1 + var56 * 2 && this.stat[var56] < 200) {
                     var95 = 200 - this.stat[var56];
                     if (var95 > 4) {
                        var95 = 4;
                     }
                  }

                  if (var1 == var56 * 2 && this.stat[var56] > 16) {
                     var95 = 16 - this.stat[var56];
                     if (var95 < -4) {
                        var95 = -4;
                     }
                  }

                  int var133 = 0;

                  while (var95 != 0 && var133 != 5) {
                     var133 = 0;

                     for (int var149 = 0; var149 < 5; var149++) {
                        if (var56 == var149 || this.stat[var149] > 200 && var95 <= 0 || this.stat[var149] < 16 && var95 >= 0 || var95 == 0) {
                           var133++;
                        } else if (var95 > 0) {
                           this.stat[var56]++;
                           this.stat[var149]--;
                           var95--;
                        } else {
                           this.stat[var56]--;
                           this.stat[var149]++;
                           var95++;
                        }
                     }
                  }
               }

               if (var1 == 10) {
                  this.carsel = this.simcar.getSelectedIndex();
                  int var57 = 0;

                  for (int var96 = 0; var96 < 5; var96++) {
                     this.stat[var96] = this.carstat[this.carsel][var96];
                     var57 += this.stat[var96];
                  }

                  this.clsel = 4 - (var57 - 520) / 40;
                  this.cls.select(this.clsel);
               }

               if (var1 == 11) {
                  String var58 = "" + this.editor.getText() + "\n";
                  String var97 = "";
                  int var134 = 0;
                  int var150 = var58.indexOf("\n", 0);

                  while (var150 != -1 && var134 < var58.length()) {
                     String var164 = var58.substring(var134, var150);
                     var164 = var164.trim();
                     var134 = var150 + 1;
                     var150 = var58.indexOf("\n", var134);
                     if (!var164.startsWith("stat(")) {
                        var97 = var97 + "" + var164 + "\n";
                     } else {
                        var97 = var97.trim();
                        var97 = var97 + "\n";
                     }
                  }

                  var97 = var97.trim();
                  var97 = var97
                     + "\n\n\nstat("
                     + this.stat[0]
                     + ","
                     + this.stat[1]
                     + ","
                     + this.stat[2]
                     + ","
                     + this.stat[3]
                     + ","
                     + this.stat[4]
                     + ")\n\n\n\n";
                  this.editor.setText(var97);
                  this.savefile();

                  for (int var166 = 0; var166 < 5; var166++) {
                     this.rstat[var166] = this.stat[var166];
                  }

                  this.changed2 = false;
               }

               if (var1 == 12) {
                  for (int var59 = 0; var59 < 5; var59++) {
                     this.stat[var59] = this.rstat[var59];
                  }
               }

               var35 = 13;
            }
         }

         if (this.dtab == 5) {
            if (this.pfase == 0) {
               for (int var61 = 0; var61 < 4; var61++) {
                  if (var1 == 1 + var61 * 2) {
                     this.phys[var61] = this.phys[var61] + 2;
                     if (this.phys[var61] > 100) {
                        this.phys[var61] = 100;
                     }
                  }

                  if (var1 == var61 * 2) {
                     this.phys[var61] = this.phys[var61] - 2;
                     if (this.phys[var61] < 0) {
                        this.phys[var61] = 0;
                     }
                  }
               }

               if (var1 == 8) {
                  for (int var62 = 0; var62 < 5; var62++) {
                     this.phys[var62] = (int)(Math.random() * 100.0);
                  }
               }

               if (var1 == 9) {
                  for (int var63 = 0; var63 < 5; var63++) {
                     this.phys[var63] = this.rphys[var63];
                  }
               }

               if (var1 == 10) {
                  this.pfase = 1;
                  var1 = -1;
               }

               var35 = 11;
            }

            if (this.pfase == 1) {
               for (int var64 = 0; var64 < 6; var64++) {
                  if (var1 == 1 + var64 * 2) {
                     this.phys[var64 + 5] = this.phys[var64 + 5] + 2;
                     if (this.phys[var64 + 5] > 100) {
                        this.phys[var64 + 5] = 100;
                     }
                  }

                  if (var1 == var64 * 2) {
                     this.phys[var64 + 5] = this.phys[var64 + 5] - 2;
                     if (this.phys[var64 + 5] < 0) {
                        this.phys[var64 + 5] = 0;
                     }
                  }
               }

               if (var1 == 12) {
                  for (int var65 = 0; var65 < 6; var65++) {
                     this.phys[var65 + 5] = (int)(Math.random() * 100.0);
                  }
               }

               if (var1 == 13) {
                  for (int var66 = 0; var66 < 6; var66++) {
                     this.phys[var66 + 5] = this.rphys[var66 + 5];
                  }
               }

               if (var1 == 14) {
                  this.pfase = 0;
                  var1 = -1;
               }

               if (var1 == 15) {
                  this.pfase = 2;
                  var1 = -1;
               }

               var35 = 16;
            }

            if (this.pfase == 2) {
               for (int var67 = 0; var67 < 3; var67++) {
                  if (var1 == 1 + var67 * 2) {
                     this.crash[var67] = this.crash[var67] + 2;
                     if (this.crash[var67] > 100) {
                        this.crash[var67] = 100;
                     }
                  }

                  if (var1 == var67 * 2) {
                     this.crash[var67] = this.crash[var67] - 2;
                     if (this.crash[var67] < 0) {
                        this.crash[var67] = 0;
                     }
                  }
               }

               if (var1 == 6) {
                  int var68 = (int)(150.0 + 600.0 * Math.random());
                  boolean var102 = false;
                  boolean var135 = false;
                  if (Math.random() > Math.random()) {
                     var102 = true;
                  }

                  if (Math.random() > Math.random()) {
                     var135 = true;
                  }

                  int[] var151 = new int[]{-101, -101, -101, -101};
                  var151[0] = (int)(Math.random() * 4.0);
                  if (Math.random() > Math.random()) {
                     if (var135) {
                        var151[1] = var151[0] + 1;
                     } else {
                        var151[1] = var151[0] - 1;
                     }

                     if (Math.random() > Math.random()) {
                        if (var135) {
                           var151[2] = var151[1] + 1;
                        } else {
                           var151[2] = var151[1] - 1;
                        }

                        if (Math.random() > Math.random()) {
                           if (var135) {
                              var151[3] = var151[2] + 1;
                           } else {
                              var151[3] = var151[2] - 1;
                           }
                        }
                     }
                  }

                  if (Math.random() > Math.random()) {
                     this.crashup = false;
                  } else {
                     this.crashup = true;
                  }

                  for (int var167 = 0; var167 < 4; var167++) {
                     if (var151[var167] != -101) {
                        if (var151[var167] >= 4) {
                           var151[var167] -= 4;
                        }

                        if (var151[var167] <= -1) {
                           var151[var167] += 4;
                        }

                        var68 -= 50 * var167;
                        if (var68 < 150) {
                           var68 = 150;
                        }

                        if (var102) {
                           this.regx(var151[var167], var68, false);
                        } else {
                           this.regz(var151[var167], var68, false);
                        }
                     }
                  }

                  if (this.hitmag < 17000) {
                     if (this.crashleft) {
                        this.o.xz += 22;
                     } else {
                        this.o.xz -= 22;
                     }
                  }
               }

               if (var1 == 8) {
                  if (Math.random() > Math.random()) {
                     this.crashup = false;
                  } else {
                     this.crashup = true;
                  }

                  this.roofsqsh((int)(230.0 + Math.random() * 80.0));
               }

               if (var1 == 9 || var1 == 7) {
                  this.setupo();
                  if (Math.random() > Math.random()) {
                     this.crashleft = false;
                  } else {
                     this.crashleft = true;
                  }
               }

               if (var1 == 10) {
                  for (int var69 = 0; var69 < 3; var69++) {
                     this.crash[var69] = this.rcrash[var69];
                  }
               }

               if (var1 == 11) {
                  this.setupo();
                  this.pfase = 1;
                  var1 = -1;
               }

               if (var1 == 12) {
                  if (this.crashok) {
                     this.setupo();
                     this.pfase = 3;
                     var1 = -1;
                  } else {
                     JOptionPane.showMessageDialog(null, this.usage[11], "Car Maker", 1);
                  }
               }

               var35 = 13;
            }

            if (this.pfase == 3) {
               for (int var70 = 0; var70 < 5; var70++) {
                  if (var1 == var70) {
                     for (int var103 = 0; var103 < 5; var103++) {
                        for (int var136 = 0; var136 < 5; var136++) {
                           this.engs[var136][var103].stop();
                        }
                     }

                     this.engs[this.engsel][var70].loop();
                     this.engon = true;
                  }

                  if (var1 == 5) {
                     for (int var104 = 0; var104 < 5; var104++) {
                        for (int var137 = 0; var137 < 5; var137++) {
                           this.engs[var137][var104].stop();
                        }
                     }

                     this.engon = false;
                  }

                  if (var1 == 6) {
                     this.pfase = 2;
                     var1 = -1;
                     this.engine.hide();
                  }

                  if (var1 == 7) {
                     this.pfase = 4;
                     var1 = -1;
                     this.engine.hide();
                  }
               }

               var35 = 8;
            }
         }

         if (this.dtab == 6) {
            if (!this.rateh) {
               if (var1 == 0 && this.checko("Test Drive")) {
                  Madness.testcar = this.carname;
                  Madness.testdrive = this.witho.getSelectedIndex() + 1;
                  Madness.game();
               }

               var35 = 1;
               if (this.tested) {
                  if (var1 == 1) {
                     this.dtab = 4;
                     var1 = -1;
                  }

                  if (var1 == 2) {
                     this.dtab = 5;
                     var1 = -1;
                  }

                  if (var1 == 3) {
                     this.rateh = true;
                     this.hidefields();
                  }

                  var35 = 4;
               }
            } else {
               if (var1 == 0) {
                  this.handling -= 2;
                  if (this.handling < 50) {
                     this.handling = 50;
                  }
               }

               if (var1 == 1) {
                  this.handling += 2;
                  if (this.handling > 200) {
                     this.handling = 200;
                  }
               }

               if (var1 == 2) {
                  String var71 = "" + this.editor.getText() + "\n";
                  String var105 = "";
                  int var138 = 0;
                  int var152 = var71.indexOf("\n", 0);

                  while (var152 != -1 && var138 < var71.length()) {
                     String var168 = var71.substring(var138, var152);
                     var168 = var168.trim();
                     var138 = var152 + 1;
                     var152 = var71.indexOf("\n", var138);
                     if (!var168.startsWith("handling(")) {
                        var105 = var105 + "" + var168 + "\n";
                     } else {
                        var105 = var105.trim();
                        var105 = var105 + "\n";
                     }
                  }

                  var105 = var105.trim();
                  var105 = var105 + "\n\n\nhandling(" + this.handling + ")\n\n\n\n";
                  this.editor.setText(var105);
                  this.savefile();
                  this.rateh = false;
               }

               if (var1 == 3) {
                  this.rateh = false;
               }

               var35 = 4;
            }
         }

         if (var1 == var35) {
            for (int var72 = 0; var72 < this.o.npl; var72++) {
               Color.RGBtoHSB(this.o.p[var72].c[0], this.o.p[var72].c[1], this.o.p[var72].c[2], this.o.p[var72].hsb);
               if (this.o.p[var72].gr == -13) {
                  this.o.p[var72].gr = 1;
               }
            }

            this.polynum = -1;
         }

         var1 = -1;
      }

      if (this.tab == 3) {
         if (var1 == 0) {
            if (this.logged == 0) {
               JOptionPane.showMessageDialog(null, "Please login to retrieve your account first before publishing!", "Car Maker", 1);
            }

            if ((this.logged == 3 || this.logged == -1) && this.checko("Publishing")) {
               int var36 = 0;

               for (int var73 = 0; var73 < this.pubitem.no; var73++) {
                  if (this.pubitem.opts[var73].equals(this.carname)) {
                     var36 = JOptionPane.showConfirmDialog(null, "Replace your already online car '" + this.carname + "' with this one?", "Car Maker", 0);
                  }
               }

               if (var36 == 0) {
                  this.setCursor(new Cursor(3));
                  this.rd.setFont(new Font("Arial", 1, 13));
                  this.ftm = this.rd.getFontMetrics();
                  this.rd.setColor(new Color(225, 225, 225));
                  this.rd.fillRect(11, 141, 679, 401);
                  this.rd.setColor(new Color(0, 0, 0));
                  this.rd.drawString("Connecting to Server...", 350 - this.ftm.stringWidth("Connecting to Server...") / 2, 250);
                  this.repaint();
                  int var74 = 0;
                  String var109 = "" + this.editor.getText() + "\n";
                  int var139 = 0;

                  for (int var153 = var109.indexOf("\n", 0); var153 != -1 && var139 < var109.length(); var74++) {
                     var139 = var153 + 1;
                     var153 = var109.indexOf("\n", var139);
                  }

                  this.justpubd = this.carname;
                  int var170 = -1;

                  try {
                     Socket var178 = new Socket("multiplayer.needformadness.com", 7061);
                     BufferedReader var186 = new BufferedReader(new InputStreamReader(var178.getInputStream()));
                     PrintWriter var194 = new PrintWriter(var178.getOutputStream(), true);
                     var194.println("10|" + this.tnick.getText() + "|" + this.tpass.getText() + "|" + this.carname + "|" + this.pubtyp.getSelectedIndex() + "|");
                     String var198 = var186.readLine();
                     if (var198 != null) {
                        var170 = this.servervalue(var198, 0);
                     }

                     if (var170 == 0) {
                        int var207 = 0;
                        var109 = "" + this.editor.getText() + "\n";
                        var139 = 0;
                        int var154 = var109.indexOf("\n", 0);

                        while (var154 != -1 && var139 < var109.length()) {
                           String var213 = var109.substring(var139, var154);
                           var213 = var213.trim();
                           var194.println(var213);
                           var139 = var154 + 1;
                           var154 = var109.indexOf("\n", var139);
                           var207++;
                           this.rd.setColor(new Color(225, 225, 225));
                           this.rd.fillRect(11, 141, 679, 401);
                           this.rd.setColor(new Color(0, 0, 0));
                           this.rd.drawString("Publishing Car...", 350 - this.ftm.stringWidth("Publishing Car...") / 2, 250);
                           this.rd.setColor(new Color(119, 147, 191));
                           this.rd.fillRect(250, 270, (int)((float)var207 / var74 * 200.0F), 7);
                           this.rd.setColor(new Color(0, 0, 0));
                           this.rd.drawRect(250, 270, 200, 7);
                           this.repaint();

                           try {
                              Thread.sleep(10L);
                           } catch (InterruptedException var17) {
                           }
                        }

                        var194.println("QUITX1111");
                        this.rd.setColor(new Color(225, 225, 225));
                        this.rd.fillRect(11, 141, 679, 401);
                        this.rd.setColor(new Color(0, 0, 0));
                        this.rd.drawString("Creating the car online...", 350 - this.ftm.stringWidth("Creating the car online...") / 2, 250);
                        this.rd
                           .drawString(
                              "This may take a couple of minutes, please wait...",
                              350 - this.ftm.stringWidth("This may take a couple of minutes, please wait...") / 2,
                              280
                           );
                        this.repaint();
                        var198 = var186.readLine();
                        if (var198 != null) {
                           var170 = this.servervalue(var198, 0);
                        }
                     }

                     var178.close();
                  } catch (Exception var24) {
                     var170 = -1;
                  }

                  this.setCursor(new Cursor(0));
                  boolean var179 = false;
                  if (var170 == 0) {
                     this.logged = 1;
                     var179 = true;
                  }

                  if (var170 == 3) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Unable to publish car.\nReason:\nCar name is too large.  Please rename your car.  Car name must be less then 15 characters.",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 4) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Unable to publish car.\nReason:  Car name used ("
                           + this.carname
                           + ").\nThe name '"
                           + this.carname
                           + "' is already used by another published car.  Please rename your car.",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 6) {
                     JOptionPane.showMessageDialog(null, "Error Creating Car!\nReason:\nError loading 3D model!  Format maybe incorrect!", "Car Maker", 1);
                     var179 = true;
                  }

                  if (var170 == 7) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nFirst and Second colors not defined yet!\nPlease go to the 'Color Edit' tab to define the colors.",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 8) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar Wheels not defined or not defined correctly!\nPlease go to the ‘Wheels’ tab and use  [ Apply ]  and  [ Save ]  to define correctly.",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 9) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nNo car seems to be designed!\nYou have not built a car yet please go to the ‘Car’ tab to find the tutorial on how to build a car.",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 10) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar contains too many polygons (pieces).\nNumber of polygons used need to be less then 210.\nPlease use the counter in the ‘Code Edit’ to decrease the number of polygons (pieces).",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 11) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar scale size is too large!\nPlease use the ‘Scale All’ option in the ‘Scale & Align’ tab to resize your car to suitable size.       \nCompare it to other NFM cars using the ‘Compare Car...’ option.\nCurrently you car needs to be scaled down by "
                           + (int)((this.o.maxR / 400.0F - 1.0F) * 100.0F)
                           + "%.\n",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 12) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar scale size is too small!\nPlease use the ‘Scale All’ option in the ‘Scale & Align’ tab to resize your car to suitable size.       \nCompare it to other NFM cars using the ‘Compare Car...’ option.\nCurrently you car needs to be scaled up by "
                           + (int)((120.0F / this.o.maxR - 1.0F) * 100.0F)
                           + "%.\n",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 13) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar Stats & Class not defined correctly!\nPlease go to the 'Stats & Class' tab to define stats and don't forget to press  [ Save ]  when finished.\n",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 14) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar Physics not defined correctly!\nPlease go to the 'Physics' tab and complete the car physics definition until it is saved.\n",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 == 15) {
                     JOptionPane.showMessageDialog(
                        null,
                        "Error Creating Car!\nReason:\nCar Handling not rated.\nPlease Test Drive your car to rate its handling before publishing!\n",
                        "Car Maker",
                        1
                     );
                     var179 = true;
                  }

                  if (var170 > 15) {
                     JOptionPane.showMessageDialog(null, "Unable to publish car fully!  Unknown Error.  Please try again later.\n", "Car Maker", 1);
                     var179 = true;
                  }

                  if (!var179) {
                     JOptionPane.showMessageDialog(null, "Unable to publish car!  Unknown Error.\n", "Car Maker", 1);
                  }
               }
            }
         }

         if (this.logged == 0) {
            if (var1 == 1) {
               this.setCursor(new Cursor(3));
               int var37 = -1;

               try {
                  Socket var75 = new Socket("multiplayer.needformadness.com", 7061);
                  BufferedReader var111 = new BufferedReader(new InputStreamReader(var75.getInputStream()));
                  PrintWriter var141 = new PrintWriter(var75.getOutputStream(), true);
                  var141.println("1|" + this.tnick.getText().toLowerCase() + "|" + this.tpass.getText() + "|");
                  String var155 = var111.readLine();
                  if (var155 != null) {
                     var37 = this.servervalue(var155, 0);
                  }

                  var75.close();
               } catch (Exception var16) {
                  var37 = -1;
               }

               if (var37 == 0 || var37 == 3 || var37 > 10) {
                  this.tnick.hide();
                  this.tpass.hide();
                  this.logged = 1;
                  this.savesettings();
               }

               if (var37 == 1 || var37 == 2) {
                  this.setCursor(new Cursor(0));
                  JOptionPane.showMessageDialog(null, "Sorry.  Incorrect Nickname or Password!", "Car Maker", 0);
               }

               if (var37 == -167) {
                  this.setCursor(new Cursor(0));
                  JOptionPane.showMessageDialog(null, "Sorry.  Your trial account cannot publish cars.  Please upgrade to a full account!   ", "Car Maker", 0);
               }

               if (var37 == -1) {
                  this.setCursor(new Cursor(0));
                  JOptionPane.showMessageDialog(null, "Unable to connect to server at this moment, please try again later.", "Car Maker", 1);
               }
            }

            if (var1 == 2) {
               Madness.openurl("http://multiplayer.needformadness.com/register.html?ref=game");
            }

            if (var1 == 3) {
               Madness.openurl("http://multiplayer.needformadness.com/edit.pl");
            }
         }
      }
   }

   public void setupo() {
      this.o = new ContO(this.editor.getText().getBytes(), this.m, this.t);
      this.o.x = this.ox;
      this.o.y = this.oy;
      this.o.z = this.oz;
      this.o.xz = this.oxz;
      this.o.xy = this.oxy;
      this.o.zy = this.ozy;
      this.o.shadow = true;
      this.o.tnt = 0;
      this.o.disp = 0;
      this.o.disline = 7;
      this.o.grounded = 1.0F;
      this.o.noline = false;
      this.o.decor = false;
      if (this.o.errd && (!this.o.err.startsWith("Wheels Error:") || this.forwheels)) {
         JOptionPane.showMessageDialog(null, this.o.err, "Car Maker", 0);
      }

      if (this.o.maxR == 0) {
         this.o.maxR = 100;
      }

      this.squash = 0;
      this.hitmag = 0;
   }

   public void loadfile() {
      this.loadedfile = false;
      this.lastedo = "";

      try {
         File var1 = new File("" + Madness.fpath + "mycars/" + this.carname + ".rad");
         BufferedReader var2 = new BufferedReader(new FileReader(var1));
         Object var3 = null;

         while ((var3 = var2.readLine()) != null) {
            this.lastedo = this.lastedo + "" + var3 + "\n";
         }

         this.loadedfile = true;
         var2.close();
         Object var5 = null;
      } catch (Exception var4) {
         this.loadedfile = false;
         this.lastedo = "";
         JOptionPane.showMessageDialog(null, "Unable to load file! Error Deatials:\n" + var4, "Car Maker", 1);
      }

      this.editor.setText(this.lastedo);
   }

   public void savefile() {
      if (!this.editor.getText().equals("")) {
         try {
            File var1 = new File("" + Madness.fpath + "mycars/");
            if (!var1.exists()) {
               var1.mkdirs();
            }

            var1 = new File("" + Madness.fpath + "mycars/" + this.carname + ".rad");
            BufferedWriter var2 = new BufferedWriter(new FileWriter(var1));
            var2.write(this.editor.getText());
            var2.close();
            Object var5 = null;
            this.changed = false;
            this.lastedo = this.editor.getText();
         } catch (Exception var3) {
            JOptionPane.showMessageDialog(null, "Unable to save file! Error Deatials:\n" + var3, "Car Maker", 1);
         }
      }

      this.savesettings();
   }

   public void newcar(String var1) {
      if (var1.equals("")) {
         JOptionPane.showMessageDialog(null, "Please Enter a Car Name!\n", "Car Maker", 1);
      } else {
         String var2 = "\n// car: "
            + var1
            + "\n---------------------\n\n// To start making you car you must start by reading the tutorial at:\n// http://www.needformadness.com/developer/simplecar.html\n\n\n<p>\nc(100,200,100)\n\np(-40,-50,80)\np(-40,-50,-70)\np(40,-50,-70)\np(40,-50,80)\n</p>\n\n<p>\nc(100,150,200)\n\np(-40,-20,-100)\np(-40,-50,-70)\np(40,-50,-70)\np(40,-20,-100)\n</p>\n\n\n\n";

         try {
            File var3 = new File("" + Madness.fpath + "mycars/");
            if (!var3.exists()) {
               var3.mkdirs();
            }

            this.carname = var1;
            var3 = new File("" + Madness.fpath + "mycars/" + this.carname + ".rad");
            if (!var3.exists()) {
               BufferedWriter var4 = new BufferedWriter(new FileWriter(var3));
               var4.write(var2);
               var4.close();
               Object var7 = null;
               if (var3.exists()) {
                  this.sfase = 0;
                  this.hidefields();
                  this.tabed = -1;
               } else {
                  JOptionPane.showMessageDialog(null, "Failed to create car, unknown reason!\n", "Car Maker", 1);
               }
            } else {
               JOptionPane.showMessageDialog(null, "A car with the name '" + this.carname + "' already exists, please choose another name!\n", "Car Maker", 1);
            }
         } catch (Exception var5) {
            this.carname = "";
            JOptionPane.showMessageDialog(null, "Unable to create file! Error Deatials:\n" + var5, "Car Maker", 1);
         }
      }
   }

   public void delcar(String var1) {
      if (var1.equals("")) {
         JOptionPane.showMessageDialog(null, "Please Select a Car to Delete!\n", "Car Maker", 1);
      } else {
         int var2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete car :  " + var1 + " ?  ", "Car Maker", 0);
         if (var2 == 0) {
            try {
               File var3 = new File("" + Madness.fpath + "mycars/" + var1 + ".rad");
               var3.delete();
               this.slcar.remove(var1);
               this.slcar.select(0);
            } catch (Exception var4) {
               JOptionPane.showMessageDialog(null, "Unable to delete file! Error Deatials:\n" + var4, "Car Maker", 1);
            }
         }
      }
   }

   public void rencar(String var1) {
      if (var1.equals("")) {
         JOptionPane.showMessageDialog(null, "Please Enter a New Car Name!\n", "Car Maker", 1);
      } else {
         try {
            File var2 = new File("" + Madness.fpath + "mycars/" + this.carname + ".rad");
            File var3 = new File("" + Madness.fpath + "mycars/" + var1 + ".rad");
            if (var2.renameTo(var3)) {
               this.carname = var1;
               this.sfase = 0;
               this.hidefields();
               this.tabed = -1;
            } else {
               JOptionPane.showMessageDialog(null, "Unable to rename car to: '" + var1 + "', possible reason: Car name already used!\n", "Car Maker", 1);
            }
         } catch (Exception var4) {
            JOptionPane.showMessageDialog(null, "Unable to rename file! Error Deatials:\n" + var4, "Car Maker", 1);
         }
      }
   }

   public void loadsettings() {
      try {
         File var1 = new File("" + Madness.fpath + "mycars/settings.data");
         if (var1.exists()) {
            BufferedReader var2 = new BufferedReader(new FileReader(var1));
            String var3 = var2.readLine();
            if (var3 != null) {
               this.scar = var3;
               this.carname = this.scar;
            }

            var3 = var2.readLine();
            if (var3 != null) {
               this.suser = var3;
               if (!this.suser.equals("Horaks")) {
                  this.tnick.setText(this.suser);
               }
            }

            var3 = var2.readLine();
            if (var3 != null) {
               this.sfont = var3;
               this.cfont = this.sfont;
            }

            var3 = var2.readLine();
            if (var3 != null) {
               this.sthm = Float.valueOf(var3).intValue();
               this.cthm = this.sthm;
            }

            var2.close();
            Object var5 = null;
         }
      } catch (Exception var4) {
      }
   }

   public void savesettings() {
      if (!this.scar.equals(this.carname) || !this.suser.equals(this.tnick.getText()) || !this.sfont.equals(this.cfont) || this.cthm != this.sthm) {
         String var1 = "" + this.carname + "\n" + this.tnick.getText() + "\n" + this.cfont + "\n" + this.cthm + "\n\n";
         this.scar = this.carname;
         this.suser = this.tnick.getText();
         this.sfont = this.cfont;
         this.sthm = this.cthm;

         try {
            File var2 = new File("" + Madness.fpath + "mycars/");
            if (!var2.exists()) {
               var2.mkdirs();
            }

            var2 = new File("" + Madness.fpath + "mycars/settings.data");
            BufferedWriter var3 = new BufferedWriter(new FileWriter(var2));
            var3.write(var1);
            var3.close();
            Object var6 = null;
         } catch (Exception var4) {
         }
      }
   }

   public boolean checko(String var1) {
      this.loadfile();
      this.setupo();
      if (this.o.colok < 2) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for " + var1 + "!\nReason:\nFirst and Second colors not defined yet!\nPlease go to the 'Color Edit' tab to define the colors.\n",
            "Car Maker",
            1
         );
         return false;
      }

      boolean var2 = true;
      if (this.o.keyz[0] <= 0 || this.o.keyx[0] >= 0) {
         var2 = false;
      }

      if (this.o.keyz[1] <= 0 || this.o.keyx[1] <= 0) {
         var2 = false;
      }

      if (this.o.keyz[2] >= 0 || this.o.keyx[2] >= 0) {
         var2 = false;
      }

      if (this.o.keyz[3] >= 0 || this.o.keyx[3] <= 0) {
         var2 = false;
      }

      if (!var2) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar Wheels not defined or not defined correctly!\nPlease go to the ‘Wheels’ tab and use  [ Apply ]  and  [ Save ]  to define correctly.\n",
            "Car Maker",
            1
         );
         return false;
      }

      if (this.o.npl <= 60) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nNo car seems to be designed!\nYou have not built a car yet please go to the ‘Car’ tab to find the tutorial on how to build a car.\n",
            "Car Maker",
            1
         );
         return false;
      }

      if (this.o.npl > 286) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar contains too many polygons (pieces).\nNumber of polygons used need to be less then 210.\nPlease use the counter in the ‘Code Edit’ to decrease the number of polygons (pieces).\n",
            "Car Maker",
            1
         );
         return false;
      }

      if (this.o.maxR > 400) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar scale size is too large!\nPlease use the ‘Scale All’ option in the ‘Scale & Align’ tab to resize your car to suitable size.       \nCompare it to other NFM cars using the ‘Compare Car...’ option.\nCurrently you car needs to be scaled down by "
               + (int)((this.o.maxR / 400.0F - 1.0F) * 100.0F)
               + "%.\n",
            "Car Maker",
            1
         );
         return false;
      }

      if (this.o.maxR < 120) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar scale size is too small!\nPlease use the ‘Scale All’ option in the ‘Scale & Align’ tab to resize your car to suitable size.       \nCompare it to other NFM cars using the ‘Compare Car...’ option.\nCurrently you car needs to be scaled up by "
               + (int)((120.0F / this.o.maxR - 1.0F) * 100.0F)
               + "%.\n",
            "Car Maker",
            1
         );
         return false;
      }

      String var3 = "" + this.editor.getText() + "\n";
      int var4 = 0;
      int var5 = var3.indexOf("\n", 0);
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;

      while (var5 != -1 && var4 < var3.length()) {
         String var9 = var3.substring(var4, var5);
         var9 = var9.trim();
         var4 = var5 + 1;
         var5 = var3.indexOf("\n", var4);
         if (var9.startsWith("stat(")) {
            var6 = true;

            try {
               int var10 = 0;

               for (int var11 = 0; var11 < 5; var11++) {
                  this.stat[var11] = this.getvalue("stat", var9, var11);
                  if (this.stat[var11] > 200) {
                     var6 = false;
                  }

                  if (this.stat[var11] < 16) {
                     var6 = false;
                  }

                  var10 += this.stat[var11];
               }

               if (var10 != 680 && var10 != 640 && var10 != 600 && var10 != 560 && var10 != 520) {
                  var6 = false;
               }
            } catch (Exception var14) {
               var6 = false;
            }
         }

         if (var9.startsWith("physics(")) {
            var7 = true;

            try {
               for (int var16 = 0; var16 < 11; var16++) {
                  this.phys[var16] = this.getvalue("physics", var9, var16);
                  if (this.phys[var16] > 100) {
                     var7 = false;
                  }

                  if (this.phys[var16] < 0) {
                     var7 = false;
                  }
               }

               for (int var17 = 0; var17 < 3; var17++) {
                  this.crash[var17] = this.getvalue("physics", var9, var17 + 11);
                  if (var17 != 0 && this.crash[var17] > 100) {
                     var7 = false;
                  }

                  if (this.crash[var17] < 0) {
                     var7 = false;
                  }
               }

               this.engsel = this.getvalue("physics", var9, 14);
               if (this.engsel > 4) {
                  var7 = false;
               }

               if (this.engsel < 0) {
                  var7 = false;
               }
            } catch (Exception var13) {
               var7 = false;
            }
         }

         if (var9.startsWith("handling(")) {
            var8 = true;

            try {
               int var18 = this.getvalue("handling", var9, 0);
               if (var18 > 200) {
                  var8 = false;
               }

               if (var18 < 0) {
                  var8 = false;
               }
            } catch (Exception var12) {
               var8 = false;
            }
         }
      }

      if (!var6) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar Stats & Class not defined correctly!\nPlease go to the 'Stats & Class' tab to define stats and don't forget to press  [ Save ]  when finished.\n",
            "Car Maker",
            1
         );
         return false;
      } else if (!var7) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for "
               + var1
               + "!\nReason:\nCar Physics not defined correctly!\nPlease go to the 'Physics' tab and complete the car physics definition until it is saved.\n",
            "Car Maker",
            1
         );
         return false;
      } else if (!var8 && var1.equals("Publishing")) {
         JOptionPane.showMessageDialog(
            null,
            "Car is not ready for " + var1 + "!\nReason:\nCar Handling not rated.\nPlease Test Drive your car to rate its handling before publishing!\n",
            "Car Maker",
            1
         );
         return false;
      } else {
         return true;
      }
   }

   public void regx(int var1, float var2, boolean var3) {
      this.hitmag = (int)(this.hitmag + var2);
      if (!var3) {
         this.crash(var2);
      }

      var2 *= 0.3F + this.crash[1] * 0.005F;
      if (Math.abs(var2) > 100.0F) {
         int var4 = (int)(this.crash[0] * this.crash[0] * 1.5);
         if (var4 < 1000) {
            var4 = 1000;
         }

         if (var2 > 100.0F) {
            var2 -= 100.0F;
         }

         if (var2 < -100.0F) {
            var2 += 100.0F;
         }

         for (int var5 = 0; var5 < this.o.npl; var5++) {
            float var6 = 0.0F;

            for (int var7 = 0; var7 < this.o.p[var5].n; var7++) {
               if (this.o.p[var5].wz == 0 && this.py(this.o.keyx[var1], this.o.p[var5].ox[var7], this.o.keyz[var1], this.o.p[var5].oz[var7]) < var4) {
                  var6 = var2 / 20.0F * this.m.random();
                  this.o.p[var5].oz[var7] = (int)(this.o.p[var5].oz[var7] - var6 * this.m.sin(this.o.xz) * this.m.cos(this.o.zy));
                  this.o.p[var5].ox[var7] = (int)(this.o.p[var5].ox[var7] + var6 * this.m.cos(this.o.xz) * this.m.cos(this.o.xy));
                  if (var3) {
                     this.actmag = (int)(this.actmag + Math.abs(var6));
                  }
               }
            }

            if (var6 != 0.0F) {
               if (Math.abs(var6) >= 1.0F) {
                  this.o.p[var5].chip = 1;
                  this.o.p[var5].ctmag = var6;
               }

               if (!this.o.p[var5].nocol && this.o.p[var5].glass != 1) {
                  if (this.o.p[var5].bfase > 20 && this.o.p[var5].hsb[1] > 0.25) {
                     this.o.p[var5].hsb[1] = 0.25F;
                  }

                  if (this.o.p[var5].bfase > 25 && this.o.p[var5].hsb[2] > 0.7) {
                     this.o.p[var5].hsb[2] = 0.7F;
                  }

                  if (this.o.p[var5].bfase > 30 && this.o.p[var5].hsb[1] > 0.15) {
                     this.o.p[var5].hsb[1] = 0.15F;
                  }

                  if (this.o.p[var5].bfase > 35 && this.o.p[var5].hsb[2] > 0.6) {
                     this.o.p[var5].hsb[2] = 0.6F;
                  }

                  if (this.o.p[var5].bfase > 40) {
                     this.o.p[var5].hsb[0] = 0.075F;
                  }

                  if (this.o.p[var5].bfase > 50 && this.o.p[var5].hsb[2] > 0.5) {
                     this.o.p[var5].hsb[2] = 0.5F;
                  }

                  if (this.o.p[var5].bfase > 60) {
                     this.o.p[var5].hsb[0] = 0.05F;
                  }

                  this.o.p[var5].bfase = (int)(this.o.p[var5].bfase + Math.abs(var6));
                  new Color(this.o.p[var5].c[0], this.o.p[var5].c[1], this.o.p[var5].c[2]);
                  Color var9 = Color.getHSBColor(this.o.p[var5].hsb[0], this.o.p[var5].hsb[1], this.o.p[var5].hsb[2]);
                  this.o.p[var5].c[0] = var9.getRed();
                  this.o.p[var5].c[1] = var9.getGreen();
                  this.o.p[var5].c[2] = var9.getBlue();
               }

               if (this.o.p[var5].glass == 1) {
                  this.o.p[var5].gr = (int)(this.o.p[var5].gr + Math.abs(var6 * 1.5));
               }
            }
         }
      }
   }

   public void regz(int var1, float var2, boolean var3) {
      this.hitmag = (int)(this.hitmag + var2);
      if (!var3) {
         this.crash(var2);
      }

      var2 *= 0.3F + this.crash[1] * 0.005F;
      if (Math.abs(var2) > 100.0F) {
         int var4 = (int)(this.crash[0] * this.crash[0] * 1.5);
         if (var4 < 1000) {
            var4 = 1000;
         }

         if (var2 > 100.0F) {
            var2 -= 100.0F;
         }

         if (var2 < -100.0F) {
            var2 += 100.0F;
         }

         for (int var5 = 0; var5 < this.o.npl; var5++) {
            float var6 = 0.0F;

            for (int var7 = 0; var7 < this.o.p[var5].n; var7++) {
               if (this.o.p[var5].wz == 0 && this.py(this.o.keyx[var1], this.o.p[var5].ox[var7], this.o.keyz[var1], this.o.p[var5].oz[var7]) < var4) {
                  var6 = var2 / 20.0F * this.m.random();
                  this.o.p[var5].oz[var7] = (int)(this.o.p[var5].oz[var7] + var6 * this.m.cos(this.o.xz) * this.m.cos(this.o.zy));
                  this.o.p[var5].ox[var7] = (int)(this.o.p[var5].ox[var7] + var6 * this.m.sin(this.o.xz) * this.m.cos(this.o.xy));
                  if (var3) {
                     this.actmag = (int)(this.actmag + Math.abs(var6));
                  }
               }
            }

            if (var6 != 0.0F) {
               if (Math.abs(var6) >= 1.0F) {
                  this.o.p[var5].chip = 1;
                  this.o.p[var5].ctmag = var6;
               }

               if (!this.o.p[var5].nocol && this.o.p[var5].glass != 1) {
                  if (this.o.p[var5].bfase > 20 && this.o.p[var5].hsb[1] > 0.25) {
                     this.o.p[var5].hsb[1] = 0.25F;
                  }

                  if (this.o.p[var5].bfase > 25 && this.o.p[var5].hsb[2] > 0.7) {
                     this.o.p[var5].hsb[2] = 0.7F;
                  }

                  if (this.o.p[var5].bfase > 30 && this.o.p[var5].hsb[1] > 0.15) {
                     this.o.p[var5].hsb[1] = 0.15F;
                  }

                  if (this.o.p[var5].bfase > 35 && this.o.p[var5].hsb[2] > 0.6) {
                     this.o.p[var5].hsb[2] = 0.6F;
                  }

                  if (this.o.p[var5].bfase > 40) {
                     this.o.p[var5].hsb[0] = 0.075F;
                  }

                  if (this.o.p[var5].bfase > 50 && this.o.p[var5].hsb[2] > 0.5) {
                     this.o.p[var5].hsb[2] = 0.5F;
                  }

                  if (this.o.p[var5].bfase > 60) {
                     this.o.p[var5].hsb[0] = 0.05F;
                  }

                  this.o.p[var5].bfase = (int)(this.o.p[var5].bfase + Math.abs(var6));
                  new Color(this.o.p[var5].c[0], this.o.p[var5].c[1], this.o.p[var5].c[2]);
                  Color var9 = Color.getHSBColor(this.o.p[var5].hsb[0], this.o.p[var5].hsb[1], this.o.p[var5].hsb[2]);
                  this.o.p[var5].c[0] = var9.getRed();
                  this.o.p[var5].c[1] = var9.getGreen();
                  this.o.p[var5].c[2] = var9.getBlue();
               }

               if (this.o.p[var5].glass == 1) {
                  this.o.p[var5].gr = (int)(this.o.p[var5].gr + Math.abs(var6 * 1.5));
               }
            }
         }
      }
   }

   public void roofsqsh(float var1) {
      if (var1 > 100.0F) {
         this.crash(var1);
         var1 -= 100.0F;
         int var2 = (int)(2.0 + this.crash[2] / 7.6);
         int var3 = 0;
         int var4 = 1;

         for (int var5 = 0; var5 < this.o.npl; var5++) {
            float var6 = 0.0F;
            if (Math.random() > 0.9) {
               var6 = var1 / 15.0F * this.m.random();
            }

            for (int var7 = 0; var7 < this.o.p[var5].n; var7++) {
               if (this.o.p[var5].wz == 0
                  && (Math.abs(this.o.p[var5].oy[var7] - this.o.roofat - this.squash) < var2 * 3 || this.o.p[var5].oy[var7] < this.o.roofat + this.squash)
                  && this.squash < var2) {
                  var6 = var1 / 15.0F * this.m.random();
                  this.o.p[var5].oy[var7] = (int)(this.o.p[var5].oy[var7] + var6);
                  var3 = (int)(var3 + var6);
                  var4++;
                  this.hitmag = (int)(this.hitmag + Math.abs(var6));
               }
            }

            if (!this.o.p[var5].nocol && this.o.p[var5].glass != 1) {
               if (var6 != 0.0F) {
                  if (this.o.p[var5].bfase > 20 && this.o.p[var5].hsb[1] > 0.25) {
                     this.o.p[var5].hsb[1] = 0.25F;
                  }

                  if (this.o.p[var5].bfase > 25 && this.o.p[var5].hsb[2] > 0.7) {
                     this.o.p[var5].hsb[2] = 0.7F;
                  }

                  if (this.o.p[var5].bfase > 30 && this.o.p[var5].hsb[1] > 0.15) {
                     this.o.p[var5].hsb[1] = 0.15F;
                  }

                  if (this.o.p[var5].bfase > 35 && this.o.p[var5].hsb[2] > 0.6) {
                     this.o.p[var5].hsb[2] = 0.6F;
                  }

                  if (this.o.p[var5].bfase > 40) {
                     this.o.p[var5].hsb[0] = 0.075F;
                  }

                  if (this.o.p[var5].bfase > 50 && this.o.p[var5].hsb[2] > 0.5) {
                     this.o.p[var5].hsb[2] = 0.5F;
                  }

                  if (this.o.p[var5].bfase > 60) {
                     this.o.p[var5].hsb[0] = 0.05F;
                  }

                  this.o.p[var5].bfase = (int)(this.o.p[var5].bfase + var6);
                  new Color(this.o.p[var5].c[0], this.o.p[var5].c[1], this.o.p[var5].c[2]);
                  Color var9 = Color.getHSBColor(this.o.p[var5].hsb[0], this.o.p[var5].hsb[1], this.o.p[var5].hsb[2]);
                  this.o.p[var5].c[0] = var9.getRed();
                  this.o.p[var5].c[1] = var9.getGreen();
                  this.o.p[var5].c[2] = var9.getBlue();
               }
            } else if (this.o.p[var5].glass == 1) {
               this.o.p[var5].gr += 5;
            }

            if (Math.abs(var6) >= 1.0F) {
               this.o.p[var5].chip = 1;
               this.o.p[var5].ctmag = var6;
            }
         }

         this.squash += var3 / var4;
      }
   }

   public void crash(float var1) {
      if (var1 > 100.0F) {
         var1 -= 100.0F;
      }

      if (var1 < -100.0F) {
         var1 += 100.0F;
      }

      if (Math.abs(var1) > 25.0F && Math.abs(var1) < 170.0F) {
         this.lowcrashs[this.crshturn].play();
      }

      if (Math.abs(var1) >= 170.0F) {
         this.crashs[this.crshturn].play();
      }

      if (Math.abs(var1) > 25.0F) {
         if (this.crashup) {
            this.crshturn--;
         } else {
            this.crshturn++;
         }

         if (this.crshturn == -1) {
            this.crshturn = 2;
         }

         if (this.crshturn == 3) {
            this.crshturn = 0;
         }
      }
   }

   public void setheme() {
      if (this.cthm == 0) {
         this.editor.setForeground(this.deff);
         this.editor.setBackground(this.defb);
      }

      if (this.cthm == 1) {
         this.editor.setForeground(new Color(0, 0, 0));
         this.editor.setBackground(new Color(192, 192, 192));
      }

      if (this.cthm == 2) {
         this.editor.setForeground(new Color(192, 192, 192));
         this.editor.setBackground(new Color(0, 0, 0));
      }

      if (this.cthm == 3) {
         this.editor.setForeground(new Color(0, 0, 0));
         this.editor.setBackground(new Color(50, 200, 0));
      }

      if (this.cthm == 4) {
         this.editor.setForeground(new Color(67, 255, 77));
         this.editor.setBackground(new Color(0, 0, 0));
      }

      if (this.cthm == 5) {
         this.editor.setForeground(new Color(0, 172, 255));
         this.editor.setBackground(new Color(210, 234, 255));
      }

      if (this.cthm == 6) {
         this.editor.setForeground(new Color(255, 230, 0));
         this.editor.setBackground(new Color(255, 77, 67));
      }

      if (this.cthm == 7) {
         this.editor.setForeground(new Color(0, 159, 255));
         this.editor.setBackground(new Color(9, 47, 104));
      }
   }

   public int py(int var1, int var2, int var3, int var4) {
      return (var1 - var2) * (var1 - var2) + (var3 - var4) * (var3 - var4);
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

   @Override
   public void init() {
      this.setBackground(new Color(0, 0, 0));
      this.offImage = this.createImage(700, 550);
      if (this.offImage != null) {
         this.rd = (Graphics2D)this.offImage.getGraphics();
      }

      this.rd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      boolean var1 = true;
      if (System.getProperty("java.vendor").toLowerCase().indexOf("apple") != -1 || System.getProperty("java.vendor").toLowerCase().indexOf("sun") != -1) {
         var1 = false;
      }

      this.setLayout(null);
      this.slcar.setFont(new Font("Arial", 1, 13));
      this.slcar.add(this.rd, "Select a Car...         ");
      this.slcar.setForeground(new Color(63, 80, 110));
      this.slcar.setBackground(new Color(209, 217, 230));
      this.fontsel.setFont(new Font("Arial", 1, 12));
      this.fontsel.add(this.rd, "Arial");
      this.fontsel.add(this.rd, "Dialog");
      this.fontsel.add(this.rd, "DialogInput");
      this.fontsel.add(this.rd, "Monospaced");
      this.fontsel.add(this.rd, "Serif");
      this.fontsel.add(this.rd, "SansSerif");
      this.fontsel.add(this.rd, "Verdana");
      this.fontsel.setBackground(new Color(63, 80, 110));
      this.fontsel.setForeground(new Color(209, 217, 230));
      this.ctheme.setFont(new Font("Arial", 1, 12));
      this.ctheme.add(this.rd, "Default");
      this.ctheme.add(this.rd, "Author");
      this.ctheme.add(this.rd, "Dos");
      this.ctheme.add(this.rd, "Green");
      this.ctheme.add(this.rd, "The Matrix");
      this.ctheme.add(this.rd, "Ice Age");
      this.ctheme.add(this.rd, "Fire");
      this.ctheme.add(this.rd, "Ocean");
      this.ctheme.setBackground(new Color(63, 80, 110));
      this.ctheme.setForeground(new Color(209, 217, 230));
      this.compcar.setFont(new Font("Arial", 1, 12));
      this.compcar.add(this.rd, "Compare Car...");
      this.compcar.add(this.rd, "Tornado Shark");
      this.compcar.add(this.rd, "Formula 7");
      this.compcar.add(this.rd, "Wow Caninaro");
      this.compcar.add(this.rd, "La Vita Crab");
      this.compcar.add(this.rd, "Nimi");
      this.compcar.add(this.rd, "MAX Revenge");
      this.compcar.add(this.rd, "Lead Oxide");
      this.compcar.add(this.rd, "Kool Kat");
      this.compcar.add(this.rd, "Drifter X");
      this.compcar.add(this.rd, "Sword of Justice");
      this.compcar.add(this.rd, "High Rider");
      this.compcar.add(this.rd, "EL KING");
      this.compcar.add(this.rd, "Mighty Eight");
      this.compcar.add(this.rd, "M A S H E E N");
      this.compcar.add(this.rd, "Radical One");
      this.compcar.add(this.rd, "DR Monstaa");
      this.compcar.add(this.rd, " -  None  - ");
      this.compcar.setBackground(new Color(63, 80, 110));
      this.compcar.setForeground(new Color(209, 217, 230));
      this.cls.setFont(new Font("Arial", 1, 12));
      this.cls.add(this.rd, "Class A");
      this.cls.add(this.rd, "Class A & B");
      this.cls.add(this.rd, "Class B");
      this.cls.add(this.rd, "Class B & C");
      this.cls.add(this.rd, "Class C");
      this.cls.setBackground(new Color(63, 80, 110));
      this.cls.setForeground(new Color(209, 217, 230));
      this.simcar.setFont(new Font("Arial", 1, 12));
      this.simcar.add(this.rd, "Tornado Shark");
      this.simcar.add(this.rd, "Formula 7");
      this.simcar.add(this.rd, "Wow Caninaro");
      this.simcar.add(this.rd, "La Vita Crab");
      this.simcar.add(this.rd, "Nimi");
      this.simcar.add(this.rd, "MAX Revenge");
      this.simcar.add(this.rd, "Lead Oxide");
      this.simcar.add(this.rd, "Kool Kat");
      this.simcar.add(this.rd, "Drifter X");
      this.simcar.add(this.rd, "Sword of Justice");
      this.simcar.add(this.rd, "High Rider");
      this.simcar.add(this.rd, "EL KING");
      this.simcar.add(this.rd, "Mighty Eight");
      this.simcar.add(this.rd, "M A S H E E N");
      this.simcar.add(this.rd, "Radical One");
      this.simcar.add(this.rd, "DR Monstaa");
      this.simcar.setBackground(new Color(63, 80, 110));
      this.simcar.setForeground(new Color(209, 217, 230));
      this.witho.setFont(new Font("Arial", 1, 12));
      this.witho.add(this.rd, "With other cars");
      this.witho.add(this.rd, "Alone");
      this.witho.setBackground(new Color(63, 80, 110));
      this.witho.setForeground(new Color(209, 217, 230));
      this.engine.setFont(new Font("Arial", 1, 12));
      this.engine.add(this.rd, "Normal Engine");
      this.engine.add(this.rd, "V8 Engine");
      this.engine.add(this.rd, "Retro Engine");
      this.engine.add(this.rd, "Power Engine");
      this.engine.add(this.rd, "Diesel Engine");
      this.engine.setBackground(new Color(63, 80, 110));
      this.engine.setForeground(new Color(209, 217, 230));
      MenuItem var2 = new MenuItem("Cut");
      MenuItem var3 = new MenuItem("Copy");
      MenuItem var4 = new MenuItem("Paste");
      MenuItem var5 = new MenuItem("Select All");
      this.popupMenu = new PopupMenu();
      this.popupMenu.add(var2);
      this.popupMenu.add(var3);
      this.popupMenu.add(var4);
      this.popupMenu.add(var5);
      var2.addActionListener(this);
      var3.addActionListener(this);
      var4.addActionListener(this);
      var5.addActionListener(this);
      this.add(this.popupMenu);

      for (int var6 = 0; var6 < 16; var6++) {
         this.wv[var6] = new TextField("", 2);
         this.wv[var6].setBackground(new Color(255, 255, 255));
         this.wv[var6].setForeground(new Color(0, 0, 0));
         this.wv[var6].setFont(new Font(this.cfont, 1, 14));
         if (var1) {
            this.wv[var6].addMouseListener(new MouseHandler(this.popupMenu, var6));
         }

         this.add(this.wv[var6]);
      }

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
      this.pubitem.add(this.rd, "Account Cars");
      this.pubitem.setBackground(new Color(209, 217, 230));
      this.pubitem.setForeground(new Color(63, 80, 110));
      this.srch.setBackground(new Color(255, 255, 255));
      this.srch.setForeground(new Color(0, 0, 0));
      if (var1) {
         this.srch.addMouseListener(new MouseHandler(this.popupMenu, 16));
      }

      this.rplc.setBackground(new Color(255, 255, 255));
      this.rplc.setForeground(new Color(0, 0, 0));
      if (var1) {
         this.rplc.addMouseListener(new MouseHandler(this.popupMenu, 17));
      }

      if (var1) {
         this.editor.addMouseListener(new MouseHandler(this.popupMenu, 18));
      }

      this.add(this.tnick);
      this.add(this.tpass);
      this.add(this.editor);
      this.add(this.srch);
      this.add(this.rplc);
      this.defb = new Color(255, 255, 255);
      this.deff = new Color(0, 0, 0);
      this.hidefields();
   }

   public void hidefields() {
      this.pubtyp.hide();
      this.pubitem.hide();
      this.tpass.hide();
      this.tnick.hide();
      this.slcar.hide();
      this.witho.hide();

      for (int var1 = 0; var1 < 16; var1++) {
         this.wv[var1].hide();
      }

      this.simcar.hide();
      this.engine.hide();
      this.cls.hide();
      this.compcar.hide();
      this.editor.hide();
      this.fontsel.hide();
      this.ctheme.hide();
      this.srch.hide();
      this.rplc.hide();
   }

   public void movefield(Component var1, int var2, int var3, int var4, int var5) {
      var2 += this.apx;
      var3 += this.apy;
      if (var1.getX() != var2 || var1.getY() != var3 || var1.getWidth() != var4 || var1.getHeight() != var5) {
         var1.setBounds(var2, var3, var4, var5);
      }
   }

   public void drawms() {
      this.openm = false;
      if (this.pubtyp.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         this.openm = true;
      }

      if (this.pubitem.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         this.openm = true;
      }

      if (this.fontsel.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.ctheme.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.compcar.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.cls.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.simcar.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.engine.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         this.openm = true;
      }

      if (this.witho.draw(this.rd, this.xm, this.ym, this.mousdr, 550, true)) {
         this.openm = true;
      }

      if (this.slcar.draw(this.rd, this.xm, this.ym, this.mousdr, 550, false)) {
         this.openm = true;
      }

      if (this.openm) {
         this.waso = true;
         this.mouses = 0;
      }
   }

   public void openlink() {
      Madness.openurl("http://www.needformadness.com/developer/simplecar.html");
   }

   public void openhlink() {
      Madness.openurl("http://www.needformadness.com/developer/");
   }

   public void openelink() {
      Madness.openurl("http://www.needformadness.com/developer/extras.html");
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
      this.apx = this.getWidth() / 2 - 350;
      this.apy = this.getHeight() / 2 - 275;
      var1.drawImage(this.offImage, this.apx, this.apy, this);
   }

   @Override
   public void update(Graphics var1) {
      this.paint(var1);
   }

   @Override
   public boolean mouseUp(Event var1, int var2, int var3) {
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      if (this.waso) {
         this.waso = false;
      } else {
         this.mouses = -1;
      }

      this.mousdr = false;
      if (this.onbtgame) {
         Madness.game();
      }

      return false;
   }

   @Override
   public boolean mouseDown(Event var1, int var2, int var3) {
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      this.mouses = 1;
      this.mousdr = true;
      if (this.tab != 1) {
         this.requestFocus();
      }

      return false;
   }

   @Override
   public boolean mouseMove(Event var1, int var2, int var3) {
      this.xm = var2 - this.apx;
      this.ym = var3 - this.apy;
      if (this.xm > 520 && this.xm < 674 && this.ym > 0 && this.ym < 23) {
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
   public boolean lostFocus(Event var1, Object var2) {
      this.focuson = false;
      return false;
   }

   @Override
   public boolean gotFocus(Event var1, Object var2) {
      this.focuson = true;
      return false;
   }

   @Override
   public boolean keyDown(Event var1, int var2) {
      if (this.focuson) {
         if (var2 == 54 || var2 == 46 || var2 == 100 || var2 == 68) {
            this.rotr = true;
         }

         if (var2 == 52 || var2 == 44 || var2 == 97 || var2 == 65) {
            this.rotl = true;
         }

         if (var2 == 43 || var2 == 61) {
            this.plus = true;
         }

         if (var2 == 45) {
            this.minus = true;
         }

         if (var2 == 42 || var2 == 10 || var2 == 56 || var2 == 119 || var2 == 87) {
            this.in = true;
         }

         if (var2 == 47 || var2 == 8 || var2 == 50 || var2 == 115 || var2 == 83) {
            this.out = true;
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
      if (var2 == 54 || var2 == 46 || var2 == 100 || var2 == 68) {
         this.rotr = false;
      }

      if (var2 == 52 || var2 == 44 || var2 == 97 || var2 == 65) {
         this.rotl = false;
      }

      if (var2 == 43 || var2 == 61) {
         this.plus = false;
      }

      if (var2 == 45) {
         this.minus = false;
      }

      if (var2 == 42 || var2 == 10 || var2 == 56 || var2 == 119 || var2 == 97) {
         this.in = false;
      }

      if (var2 == 47 || var2 == 8 || var2 == 50 || var2 == 115 || var2 == 83) {
         this.out = false;
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

   public void loadsounds() {
      try {
         File var1 = new File("" + Madness.fpath + "data/sounds.zip");
         FileInputStream var2 = new FileInputStream(var1);
         ZipInputStream var3 = new ZipInputStream(var2);

         for (ZipEntry var4 = var3.getNextEntry(); var4 != null; var4 = var3.getNextEntry()) {
            int var5 = (int)var4.getSize();
            String var6 = var4.getName();
            byte[] var7 = new byte[var5];
            int var8 = 0;

            while (var5 > 0) {
               int var9 = var3.read(var7, var8, var5);
               var8 += var9;
               var5 -= var9;
            }

            for (int var10 = 0; var10 < 5; var10++) {
               for (int var11 = 0; var11 < 5; var11++) {
                  if (var6.equals("" + var11 + "" + var10 + ".wav")) {
                     this.engs[var11][var10] = new soundClip(var7);
                  }
               }
            }

            for (int var13 = 0; var13 < 3; var13++) {
               if (var6.equals("crash" + (var13 + 1) + ".wav")) {
                  this.crashs[var13] = new soundClip(var7);
               }
            }

            for (int var14 = 0; var14 < 3; var14++) {
               if (var6.equals("lowcrash" + (var14 + 1) + ".wav")) {
                  this.lowcrashs[var14] = new soundClip(var7);
               }
            }
         }

         var2.close();
         var3.close();
      } catch (Exception var12) {
         System.out.println("Error Loading Sounds: " + var12);
      }

      System.gc();
   }

   public void loadbase() {
      String[] var1 = new String[]{
         "2000tornados",
         "formula7",
         "canyenaro",
         "lescrab",
         "nimi",
         "maxrevenge",
         "leadoxide",
         "koolkat",
         "drifter",
         "policecops",
         "mustang",
         "king",
         "audir8",
         "masheen",
         "radicalone",
         "drmonster"
      };

      try {
         File var2 = new File("" + Madness.fpath + "data/models.zip");
         ZipInputStream var3 = new ZipInputStream(new FileInputStream(var2));
         ZipEntry var4 = var3.getNextEntry();
         byte[] var5 = null;

         while (var4 != null) {
            int var6 = -1;

            for (int var7 = 0; var7 < 16; var7++) {
               if (var4.getName().startsWith(var1[var7])) {
                  var6 = var7;
               }
            }

            if (var6 != -1) {
               int var12 = (int)var4.getSize();
               var5 = new byte[var12];
               int var8 = 0;

               while (var12 > 0) {
                  int var9 = var3.read(var5, var8, var12);
                  var8 += var9;
                  var12 -= var9;
               }

               this.compo[var6] = new ContO(var5, this.m, this.t);
               this.compo[var6].shadow = false;
               this.compo[var6].noline = true;
            }

            var4 = var3.getNextEntry();
         }

         var3.close();
      } catch (Exception var10) {
         System.out.println("Error Loading Models from Zip: " + var10);
      }

      System.gc();
   }

   public void fixtext(TextField var1) {
      String var2 = var1.getText();
      var2 = var2.replace('"', '#');
      String var3 = "\\";
      String var4 = "";
      int var5 = 0;
      int var6 = -1;

      while (var5 < var2.length()) {
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
            && !var7.equals("'")
            && !var7.equals(";")
            && !var7.equals(":")
            && var5 <= 15) {
            var4 = var4 + var7;
         } else {
            var6 = var5;
         }

         var5++;
      }

      if (var6 != -1) {
         var1.setText(var4);
         var1.select(var6, var6);
      }
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

   public int objvalue(String var1, int var2) {
      int var3 = 0;

      try {
         int var4 = 2;
         int var5 = 0;
         byte var6 = 0;
         String var7 = "";
         String var8 = "";
         boolean var9 = false;

         while (var4 < var1.length() && var6 != 2) {
            var7 = "" + var1.charAt(var4);
            if (var7.equals(" ")) {
               if (var9) {
                  var5++;
                  var9 = false;
               }

               if (var6 == 1 || var5 > var2) {
                  var6 = 2;
               }
            } else {
               if (var5 == var2) {
                  var8 = var8 + var7;
                  var6 = 1;
               }

               var9 = true;
            }

            var4++;
         }

         if (var4 >= var1.length()) {
            this.objfacend = true;
         }

         if (var8.equals("")) {
            var8 = "0";
         }

         if (this.multf10) {
            var3 = (int)(Float.valueOf(var8) * 10.0F);
         } else {
            int var10 = var8.indexOf("/", 0);
            if (var10 != -1) {
               var8 = var8.substring(0, var10);
            }

            var3 = Float.valueOf(var8).intValue() - 1;
            if (var3 < 0) {
               var3 = 0;
            }
         }
      } catch (Exception var11) {
      }

      return var3;
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

   public void stringbutton(String var1, int var2, int var3, int var4, boolean var5) {
      this.rd.setFont(new Font("Arial", 1, 12));
      this.ftm = this.rd.getFontMetrics();
      if (var1.indexOf("Publish") != -1) {
         this.rd.setFont(new Font("Arial", 1, 13));
         this.ftm = this.rd.getFontMetrics();
      }

      this.bx[this.btn] = var2;
      this.by[this.btn] = var3 - 5;
      this.bw[this.btn] = this.ftm.stringWidth(var1);
      if (!this.pessd[this.btn]) {
         this.rd.setColor(new Color(220, 220, 220));
         if (var5) {
            this.rd.setColor(new Color(230, 230, 230));
         }

         this.rd.fillRect(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), this.bw[this.btn] + 20, 25 - var4 * 2);
         this.rd.setColor(new Color(240, 240, 240));
         if (var5) {
            this.rd.setColor(new Color(255, 255, 255));
         }

         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (18 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (18 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 - (19 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 - (19 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         if (var5) {
            this.rd.setColor(new Color(192, 192, 192));
         }

         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 11, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 11, var3 + (7 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 12, var3 - (16 - var4), var2 + this.bw[this.btn] / 2 + 12, var3 + (6 - var4));
         if (var5) {
            this.rd.setColor(new Color(192, 192, 192));
         }

         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (8 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (8 - var4));
         this.rd.setColor(new Color(200, 200, 200));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 + (9 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 + (9 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         if (var5) {
            this.rd.setColor(new Color(255, 255, 255));
         }

         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 11, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 11, var3 + (7 - var4));
         this.rd.setColor(new Color(240, 240, 240));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 12, var3 - (16 - var4), var2 - this.bw[this.btn] / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         this.rd.drawString(var1, var2 - this.bw[this.btn] / 2, var3);
      } else {
         this.rd.setColor(new Color(220, 220, 220));
         this.rd.fillRect(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), this.bw[this.btn] + 20, 25 - var4 * 2);
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (18 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (18 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 - (19 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 - (19 - var4));
         this.rd.setColor(new Color(247, 247, 247));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 11, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 11, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 12, var3 - (16 - var4), var2 + this.bw[this.btn] / 2 + 12, var3 + (6 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (8 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (8 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 + (9 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 + (9 - var4));
         this.rd.setColor(new Color(192, 192, 192));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 11, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 11, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 12, var3 - (16 - var4), var2 - this.bw[this.btn] / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         this.rd.drawString(var1, var2 - this.bw[this.btn] / 2 + 1, var3 + 1);
      }

      this.btn++;
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

   @Override
   public void actionPerformed(ActionEvent var1) {
      TextComponent var2 = this.wv[0];
      if (Madness.textid >= 0 && Madness.textid <= 15) {
         var2 = this.wv[Madness.textid];
      }

      if (Madness.textid == 16) {
         var2 = this.srch;
      }

      if (Madness.textid == 17) {
         var2 = this.rplc;
      }

      if (Madness.textid == 18) {
         var2 = this.editor;
      }

      String var3 = var1.getActionCommand();
      if (var3.equals("Cut")) {
         StringSelection var4 = new StringSelection(var2.getSelectedText());
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var4, null);
         if (Madness.textid == 18) {
            this.editor.replaceText("", this.editor.getSelectionStart(), this.editor.getSelectionEnd());
         } else {
            var2.setText(var2.getText().substring(0, var2.getSelectionStart()) + var2.getText().substring(var2.getSelectionEnd(), var2.getText().length()));
         }
      }

      if (var3.equals("Copy")) {
         StringSelection var6 = new StringSelection(var2.getSelectedText());
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var6, null);
      }

      if (var3.equals("Paste")) {
         try {
            String var7 = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            if (Madness.textid == 18) {
               this.editor.replaceText(var7, this.editor.getSelectionStart(), this.editor.getSelectionEnd());
            } else {
               var2.setText(
                  var2.getText().substring(0, var2.getSelectionStart()) + var7 + var2.getText().substring(var2.getSelectionEnd(), var2.getText().length())
               );
            }
         } catch (Exception var5) {
         }
      }

      if (var3.equals("Select All")) {
         var2.selectAll();
      }
   }
}
