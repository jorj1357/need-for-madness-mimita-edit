import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class Lobby implements Runnable {
   Graphics2D rd;
   Login lg;
   Globe gb;
   xtGraphics xt;
   CarDefine cd;
   Medium m;
   FontMetrics ftm;
   ImageObserver ob;
   GameSparker gs;
   Thread connector;
   int conon = 0;
   boolean regnow = false;
   boolean lanlogged = false;
   int fase = 0;
   int stage = 0;
   int laps = 3;
   String stagename = "";
   int nfix = 0;
   boolean notb = false;
   boolean[] pessd = new boolean[]{false, false, false, false, false, false, false, false};
   int[] bx = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
   int[] by = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
   int[] bw = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
   int btn = 0;
   int pbtn = 0;
   int nflk = 0;
   int ncnt = 0;
   int rerr = 0;
   int pback = 0;
   int cflk = 0;
   int sflk = 0;
   String msg = "";
   String lmsg = "| Searching/Waiting for other LAN Players |";
   int opselect = 0;
   boolean lloaded = false;
   int npo = 0;
   String[] pnames = new String[200];
   int[] pcars = new int[200];
   String[] pcarnames = new String[200];
   String[] pclan = new String[200];
   int[] pgames = new int[200];
   float[][] pcols = new float[200][6];
   int prnpo = 0;
   String[] prnames = new String[]{"", "", "", "", "", "", "", ""};
   int[] ppos = new int[]{6, 6, 6, 6, 6, 6, 6, 6};
   int[] plap = new int[]{6, 6, 6, 6, 6, 6, 6, 6};
   int[] ppow = new int[]{50, 50, 50, 50, 50, 50, 50, 50};
   int[] pdam = new int[]{50, 50, 50, 50, 50, 50, 50, 50};
   int prevloaded = -1;
   String stuntname = "";
   String lapsname = "";
   String wastename = "";
   int ngm = 0;
   int[] gnum = new int[500];
   int[] gstgn = new int[500];
   String[] gstages = new String[500];
   int[] gnlaps = new int[500];
   int[] mnpls = new int[500];
   int[] mnbts = new int[500];
   int[] wait = new int[500];
   int[] gcrs = new int[500];
   int[] gclss = new int[500];
   int[] gfx = new int[500];
   int[] gntb = new int[500];
   String[] gplyrs = new String[500];
   int[] gwarb = new int[500];
   String[] gwarbnum = new String[500];
   int[] gameturn = new int[500];
   String[] gaclan = new String[500];
   String[] gvclan = new String[500];
   int[] gascore = new int[500];
   int[] gvscore = new int[500];
   int[] gwtyp = new int[500];
   String[] gmaker = new String[500];
   int[] npls = new int[500];
   int ongame = -1;
   int join = -1;
   int chalngd = -1;
   int im = 0;
   int longame = -1;
   int onjoin = -1;
   int ontyp = 0;
   int dispcar = -1;
   int forcar = -1;
   int addstage = 0;
   ContO dispco = null;
   boolean fstart = false;
   boolean jflexo = false;
   String chalby = "";
   int ctime = 0;
   boolean invo = false;
   String[] invos = new String[]{"", "", "", "", "", "", "", ""};
   String[] dinvi = new String[]{"", "", "", "", "", "", "", ""};
   String[] cnames = new String[]{"", "", "", "", "", "", ""};
   String[] sentn = new String[]{"", "", "", "", "", "", ""};
   int updatec = -1;
   int loadstage = 0;
   int gstage = 0;
   int gstagelaps = 0;
   int gnpls = 8;
   int gwait = 0;
   int gnbts = 0;
   int gcars;
   int gclass = 0;
   int gfix = 0;
   int gnotp = 0;
   int remstage = 0;
   int msload = 0;
   int sgflag = 0;
   String gstagename = "";
   String gplayers = "";
   boolean inwab = false;
   boolean loadwarb = false;
   int warbsel = 0;
   int cancreate = 0;
   int pgamesel = 0;
   int cntchkn = 0;
   Socket socket;
   BufferedReader din;
   PrintWriter dout;
   int spos = 0;
   int spos2 = 0;
   int spos3 = 28;
   int mscro = 125;
   int lspos = 0;
   int mscro2 = 145;
   int lspos2 = 0;
   int mscro3 = 345;
   int lspos3 = 0;
   int clicked = -1;
   int opengame = 0;
   int britchl = 0;
   boolean zeromsw = false;
   int mousonp = -1;
   int cmonp = -1;
   long ptime = 0L;
   int pcurs = 0;
   boolean grprsd = false;
   int pend = 0;
   int mrot = 0;
   boolean pendb = false;
   int[] cac = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
   int[] cax = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   int[] cay = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   boolean mousedout = false;
   int flks = 0;
   int waitlink = 0;
   boolean pre1 = false;
   boolean pre2 = false;
   int prereq = 0;
   int plsndt = 0;
   int lxm = 0;
   int lym = 0;

   public Lobby(Medium var1, Graphics2D var2, Login var3, Globe var4, xtGraphics var5, CarDefine var6, GameSparker var7) {
      this.m = var1;
      this.rd = var2;
      this.xt = var5;
      this.cd = var6;
      this.gs = var7;
      this.lg = var3;
      this.gb = var4;
      this.gs.cmsg.setBackground(this.color2k(240, 240, 240));
      this.gs.swait.setBackground(this.color2k(220, 220, 220));
      this.gs.snpls.setBackground(this.color2k(220, 220, 220));
      this.gs.snbts.setBackground(this.color2k(220, 220, 220));
      this.gs.sgame.setBackground(this.color2k(200, 200, 200));
      this.gs.wgame.setBackground(this.color2k(200, 200, 200));
      this.gs.pgame.setBackground(this.color2k(200, 200, 200));
      this.gs.vnpls.setBackground(this.color2k(200, 200, 200));
      this.gs.vtyp.setBackground(this.color2k(200, 200, 200));
      this.gs.warb.setBackground(this.color2k(200, 200, 200));
      this.gs.snfmm.setBackground(this.color2k(200, 200, 200));
      this.gs.snfm1.setBackground(this.color2k(200, 200, 200));
      this.gs.snfm2.setBackground(this.color2k(200, 200, 200));
      this.gs.mstgs.setBackground(this.color2k(230, 230, 230));
      this.gs.slaps.setBackground(this.color2k(200, 200, 200));
      this.gs.sclass.setBackground(this.color2k(220, 220, 220));
      this.gs.scars.setBackground(this.color2k(220, 220, 220));
      this.gs.sfix.setBackground(this.color2k(220, 220, 220));
      this.gs.mycar.setBackground(this.color2k(255, 255, 255));
      this.gs.notp.setBackground(this.color2k(255, 255, 255));
      this.gs.rooms.setBackground(this.color2k(170, 170, 170));
      this.gs.swait.setForeground(new Color(0, 0, 0));
      this.gs.snpls.setForeground(new Color(0, 0, 0));
      this.gs.snbts.setForeground(new Color(0, 0, 0));
      this.gs.sgame.setForeground(new Color(0, 0, 0));
      this.gs.wgame.setForeground(new Color(0, 0, 0));
      this.gs.pgame.setForeground(new Color(0, 0, 0));
      this.gs.vnpls.setForeground(new Color(0, 0, 0));
      this.gs.vtyp.setForeground(new Color(0, 0, 0));
      this.gs.warb.setForeground(new Color(0, 0, 0));
      this.gs.snfmm.setForeground(new Color(0, 0, 0));
      this.gs.snfm1.setForeground(new Color(0, 0, 0));
      this.gs.slaps.setForeground(new Color(0, 0, 0));
      this.gs.snfm2.setForeground(new Color(0, 0, 0));
      this.gs.mstgs.setForeground(new Color(0, 0, 0));
      this.gs.sclass.setForeground(new Color(0, 0, 0));
      this.gs.scars.setForeground(new Color(0, 0, 0));
      this.gs.sfix.setForeground(new Color(0, 0, 0));
      this.gs.mycar.setForeground(new Color(0, 0, 0));
      this.gs.notp.setForeground(new Color(0, 0, 0));
      this.gs.rooms.setForeground(new Color(0, 0, 0));
      this.gs.sgame.removeAll();
      this.gs.sgame.add(this.rd, " NFM Multiplayer ");
      this.gs.sgame.add(this.rd, " NFM 2     ");
      this.gs.sgame.add(this.rd, " NFM 1     ");
      this.gs.sgame.add(this.rd, " My Stages ");
      this.gs.sgame.add(this.rd, " My Clan Stages ");
      this.gs.sgame.add(this.rd, " Weekly Top 20 ");
      this.gs.sgame.add(this.rd, " Monthly Top 20 ");
   }

   public void inishlobby() {
      this.gs.tnick.hide();
      this.gs.tpass.hide();
      this.gs.temail.hide();
      this.hideinputs();
      this.gs.mycar.setBackground(this.color2k(255, 255, 255));
      this.gs.mycar.setForeground(new Color(0, 0, 0));
      this.gs.rooms.removeAll();
      this.gs.rooms.add(this.rd, "" + this.xt.servername + " :: " + (this.xt.servport - 7070) + "");
      this.gs.rooms.select(0);
      this.gs.requestFocus();
      this.cd.loadready();

      for (int var1 = 0; var1 < 500; var1++) {
         this.gnum[var1] = -2;
         this.gstgn[var1] = 0;
         this.gstages[var1] = "";
         this.gnlaps[var1] = 0;
         this.mnpls[var1] = 0;
         this.mnbts[var1] = 0;
         this.wait[var1] = 0;
         this.gmaker[var1] = "";
         this.gcrs[var1] = 0;
         this.gclss[var1] = 0;
         this.gfx[var1] = 0;
         this.gntb[var1] = 0;
         this.gplyrs[var1] = "";
         this.npls[var1] = 0;
         this.gwarb[var1] = 0;
         this.gwarbnum[var1] = "";
         this.gameturn[var1] = 0;
         this.gaclan[var1] = "";
         this.gvclan[var1] = "";
         this.gascore[var1] = 0;
         this.gvscore[var1] = 0;
         this.gwtyp[var1] = 0;
      }

      for (int var4 = 0; var4 < 200; var4++) {
         this.pnames[var4] = "";
         this.pcars[var4] = 0;
         this.pcarnames[var4] = "";
         this.pgames[var4] = -1;
         this.pclan[var4] = "";

         for (int var2 = 0; var2 < 6; var2++) {
            this.pcols[var4][var2] = 0.0F;
         }
      }

      this.ongame = -1;
      this.join = -1;
      this.onjoin = -1;
      this.chalngd = -1;
      this.dispcar = -1;
      this.forcar = -1;
      this.chalby = "";
      this.im = 0;
      this.fstart = false;
      this.updatec = -1;
      this.prevloaded = -1;
      this.spos = 0;
      this.spos2 = 0;
      this.spos3 = 0;
      this.ngm = 0;
      this.npo = 0;
      this.fase = 1;
      this.lloaded = false;
      this.lg.gamec = -1;

      try {
         System.out.println("Connecting to: " + this.xt.server + ":" + this.xt.servport);
         this.socket = new Socket(this.xt.server, this.xt.servport);
         this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
         this.dout = new PrintWriter(this.socket.getOutputStream(), true);
      } catch (Exception var3) {
      }

      this.conon = 1;
      this.connector = new Thread(this);
      this.connector.start();
   }

   @Override
   public void run() {
      int var1 = -1;

      while (this.conon == 1) {
         System.out.println("Lobby thread running...");
         Date var2 = new Date();
         long var3 = var2.getTime();
         if (!this.lloaded) {
            this.gs.setCursor(new Cursor(3));
         }

         if (!this.xt.logged && !this.xt.lan && this.xt.nfreeplays - this.xt.ndisco >= 5) {
            if (this.join >= 0) {
               this.join = -1;
               this.regnow = true;
            }

            if (this.pgames[this.im] != -1) {
               this.join = -2;
               this.regnow = true;
            }
         }

         if ((this.join >= 0 || this.pgames[this.im] != -1) && !this.regnow) {
            int var5 = -1;

            for (int var6 = 0; var6 < this.ngm; var6++) {
               if (this.join == this.gnum[var6] || this.pgames[this.im] == this.gnum[var6]) {
                  var5 = var6;
               }
            }

            if (var5 != -1) {
               boolean var48 = false;
               this.ontyp = 0;
               if (this.gcrs[var5] == 1 && this.pcars[this.im] < 16) {
                  var48 = true;
                  this.ontyp = 10;
                  if (this.gclss[var5] > 0 && this.gclss[var5] <= 5) {
                     this.ontyp = this.ontyp + this.gclss[var5];
                  }
               }

               if (this.gcrs[var5] == 2 && this.pcars[this.im] >= 16) {
                  var48 = true;
                  this.ontyp = 20;
                  if (this.gclss[var5] > 0 && this.gclss[var5] <= 5) {
                     this.ontyp = this.ontyp + this.gclss[var5];
                  }
               }

               if (this.gcrs[var5] == 1 && this.gwarb[var5] != 0 && this.xt.sc[0] != 36) {
                  var48 = true;
                  this.ontyp = 30;
                  if (this.gclss[var5] > 0 && this.gclss[var5] <= 5) {
                     this.ontyp = this.ontyp + this.gclss[var5];
                  }
               }

               if (this.gclss[var5] > 0 && this.gclss[var5] <= 5 && Math.abs(this.cd.cclass[this.pcars[this.im]] - (this.gclss[var5] - 1)) > 1) {
                  var48 = true;
                  if (this.gcrs[var5] == 1) {
                     if (this.gwarb[var5] == 0) {
                        this.ontyp = 10;
                     } else {
                        this.ontyp = 30;
                     }
                  }

                  if (this.gcrs[var5] == 2) {
                     this.ontyp = 20;
                  }

                  this.ontyp = this.ontyp + this.gclss[var5];
               }

               if (this.gclss[var5] <= -2 && this.pcars[this.im] != Math.abs(this.gclss[var5] + 2)) {
                  var48 = true;
                  this.ontyp = this.gclss[var5];
               }

               if (this.gstgn[var5] == -2 && !this.xt.logged) {
                  var48 = true;
                  this.ontyp = 76;
               }

               if (var48) {
                  this.onjoin = this.gnum[var5];
                  this.jflexo = false;
                  if (this.join >= 0) {
                     this.join = -1;
                  }

                  if (this.pgames[this.im] != -1) {
                     this.join = -2;
                  }
               }
            }
         }

         if (this.xt.onjoin != -1) {
            this.join = this.xt.onjoin;
            this.ongame = this.xt.onjoin;
            this.xt.onjoin = -1;
         }

         if (this.updatec < -17) {
            this.updatec = -17;
         }

         boolean var45 = false;
         if (this.lloaded) {
            var1 = this.pgames[this.im];
            if (var1 != -1) {
               for (int var49 = 0; var49 < this.ngm; var49++) {
                  if (var1 == this.gnum[var49]) {
                     this.laps = this.gnlaps[var49];
                     this.stage = this.gstgn[var49];
                     this.stagename = this.gstages[var49];
                     this.nfix = this.gfx[var49];
                     if (this.gntb[var49] == 1) {
                        this.notb = true;
                     } else {
                        this.notb = false;
                     }
                  }
               }
            }
         }

         String var50 = "" + this.xt.sc[0] + "";
         if (this.xt.sc[0] >= 16) {
            var50 = "C" + this.cd.names[this.xt.sc[0]] + "";
         }

         String var7 = "1|"
            + this.xt.nickname
            + ":"
            + this.xt.nickey
            + "|"
            + this.xt.clan
            + "|"
            + this.xt.clankey
            + "|"
            + var50
            + "|"
            + this.join
            + "|"
            + (int)(this.xt.arnp[0] * 100.0F)
            + "|"
            + (int)(this.xt.arnp[1] * 100.0F)
            + "|"
            + (int)(this.xt.arnp[2] * 100.0F)
            + "|"
            + (int)(this.xt.arnp[3] * 100.0F)
            + "|"
            + (int)(this.xt.arnp[4] * 100.0F)
            + "|"
            + (int)(this.xt.arnp[5] * 100.0F)
            + "|"
            + this.ongame
            + "|";
         if (this.xt.lan) {
            String var64 = "Nonex";

            try {
               var64 = InetAddress.getLocalHost().getHostName();
               if (var64.indexOf("|") != -1) {
                  var64 = InetAddress.getLocalHost().getHostAddress();
               }
            } catch (Exception var37) {
               var64 = "Nonex";
            }

            byte var9 = 0;
            if (this.xt.logged) {
               var9 = 1;
            }

            var7 = var7 + "" + var64 + "|" + var9 + "|";
         } else {
            var7 = var7 + "" + this.updatec + "|";
            if (this.updatec <= -11) {
               for (int var8 = 0; var8 < -this.updatec - 10; var8++) {
                  var7 = var7 + "" + this.cnames[6 - var8] + "|" + this.sentn[6 - var8] + "|";
               }

               this.updatec = -2;
            }
         }

         if (this.fstart) {
            var7 = var7 + "3|";
            var45 = true;
         }

         if (this.chalngd == -5 && !this.fstart) {
            var7 = var7
               + "11|"
               + this.gstage
               + "|"
               + this.gstagename
               + "|"
               + this.gstagelaps
               + "|"
               + this.gnpls
               + "|"
               + this.gwait
               + "|"
               + this.pnames[this.im]
               + "|"
               + this.gcars
               + "|"
               + this.gclass
               + "|"
               + this.gfix
               + "|"
               + this.gnotp
               + "|"
               + this.gplayers
               + "|";
            if (this.xt.lan) {
               var7 = var7 + "" + this.gnbts + "|";
            }
         }

         if (this.ongame != -1 && this.chalngd != -5 && !this.fstart) {
            boolean var66 = false;

            for (int var78 = 0; var78 < 7; var78++) {
               if (!this.invos[var78].equals("") && !this.dinvi[var78].equals(this.invos[var78])) {
                  if (!var66) {
                     var7 = var7 + "2|" + this.ongame + "|";
                     var66 = true;
                  }

                  var7 = var7 + "" + this.invos[var78] + "|";
                  this.dinvi[var78] = this.invos[var78];
               }
            }
         }

         String var67 = "";
         boolean var79 = false;

         try {
            this.dout.println(var7);
            var67 = this.din.readLine();
            if (var67 == null) {
               var79 = true;
            }
         } catch (Exception var36) {
            var79 = true;
         }

         if (var79) {
            try {
               this.socket.close();
               this.socket = null;
               this.din.close();
               this.din = null;
               this.dout.close();
               this.dout = null;
            } catch (Exception var35) {
            }

            try {
               this.socket = new Socket(this.xt.server, this.xt.servport);
               this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
               this.dout = new PrintWriter(this.socket.getOutputStream(), true);
               this.dout.println(var7);
               var67 = this.din.readLine();
               if (var67 != null) {
                  var79 = false;
               }
            } catch (Exception var34) {
            }
         }

         if (var79) {
            try {
               this.socket.close();
               this.socket = null;
            } catch (Exception var33) {
            }

            this.conon = 0;
            this.lg.exitfromlobby();
            this.hideinputs();
            // this.connector.stop();
         }

         if (this.regnow && this.join == -2) {
            this.join = -1;
         }

         this.npo = this.getvalue(var67, 0);
         if (this.npo < 0) {
            this.npo = 0;
         }

         this.im = this.getvalue(var67, 1);
         if (this.im < 0) {
            this.im = 0;
         }

         for (int var10 = 0; var10 < this.npo; var10++) {
            this.pnames[var10] = this.getSvalue(var67, 2 + var10 * 10);
            if (this.pnames[var10].equals("")) {
               this.pnames[var10] = "Unknown";
            }

            String var11 = this.getSvalue(var67, 3 + var10 * 10);
            if (!var11.startsWith("C")) {
               this.pcars[var10] = this.getvalue(var67, 3 + var10 * 10);
               if (this.pcars[var10] == -1) {
                  this.pcars[var10] = 0;
               }

               this.pcarnames[var10] = this.cd.names[this.pcars[var10]];
            } else {
               this.pcarnames[var10] = var11.substring(1);
               if (this.pcarnames[var10].equals("")) {
                  this.pcars[var10] = 0;
                  this.pcarnames[var10] = this.cd.names[this.pcars[var10]];
               } else {
                  int var12 = 0;

                  for (int var13 = 16; var13 < 56; var13++) {
                     if (this.pcarnames[var10].equals(this.cd.names[var13])) {
                        var12 = var13;
                        break;
                     }
                  }

                  if (var12 != 0) {
                     this.pcars[var10] = var12;
                  } else {
                     this.pcars[var10] = -1;
                     boolean var119 = false;

                     for (int var14 = 0; var14 < this.cd.nl; var14++) {
                        if (this.pcarnames[var10].equals(this.cd.loadnames[var14])) {
                           var119 = true;
                        }
                     }

                     if (!var119 && this.cd.nl < 20 && this.cd.nl >= 0) {
                        this.cd.loadnames[this.cd.nl] = this.pcarnames[var10];
                        this.cd.nl++;
                     }

                     this.cd.sparkcarloader();
                  }
               }
            }

            this.pclan[var10] = this.getSvalue(var67, 4 + var10 * 10);
            this.pgames[var10] = this.getvalue(var67, 5 + var10 * 10);
            this.pcols[var10][0] = this.getvalue(var67, 6 + var10 * 10) / 100.0F;
            if (this.pcols[var10][0] == -1.0F) {
               this.pcols[var10][0] = 0.0F;
            }

            this.pcols[var10][1] = this.getvalue(var67, 7 + var10 * 10) / 100.0F;
            if (this.pcols[var10][1] == -1.0F) {
               this.pcols[var10][1] = 0.0F;
            }

            this.pcols[var10][2] = this.getvalue(var67, 8 + var10 * 10) / 100.0F;
            if (this.pcols[var10][2] == -1.0F) {
               this.pcols[var10][2] = 0.0F;
            }

            this.pcols[var10][3] = this.getvalue(var67, 9 + var10 * 10) / 100.0F;
            if (this.pcols[var10][3] == -1.0F) {
               this.pcols[var10][3] = 0.0F;
            }

            this.pcols[var10][4] = this.getvalue(var67, 10 + var10 * 10) / 100.0F;
            if (this.pcols[var10][4] == -1.0F) {
               this.pcols[var10][4] = 0.0F;
            }

            this.pcols[var10][5] = this.getvalue(var67, 11 + var10 * 10) / 100.0F;
            if (this.pcols[var10][5] == -1.0F) {
               this.pcols[var10][5] = 0.0F;
            }
         }

         int var90 = 12 + (this.npo - 1) * 10;
         this.ngm = this.getvalue(var67, var90);
         if (this.ngm < 0) {
            this.ngm = 0;
         }

         byte var100 = 12;
         if (this.xt.lan) {
            var100 = 13;
         }

         for (int var107 = 0; var107 < this.ngm; var107++) {
            this.gnum[var107] = this.getvalue(var67, var90 + 1 + var107 * var100);
            this.gstgn[var107] = this.getvalue(var67, var90 + 2 + var107 * var100);
            this.gstgn[var107] = Math.abs(this.gstgn[var107]);
            if (this.gstgn[var107] > 100) {
               this.gstgn[var107] = -2;
            }

            this.gstages[var107] = this.getSvalue(var67, var90 + 3 + var107 * var100);
            this.gnlaps[var107] = this.getvalue(var67, var90 + 4 + var107 * var100);
            this.mnpls[var107] = this.getvalue(var67, var90 + 5 + var107 * var100);
            this.wait[var107] = this.getvalue(var67, var90 + 6 + var107 * var100);
            this.gmaker[var107] = this.getSvalue(var67, var90 + 7 + var107 * var100);
            this.gcrs[var107] = this.getvalue(var67, var90 + 8 + var107 * var100);
            this.gclss[var107] = this.getvalue(var67, var90 + 9 + var107 * var100);
            this.gfx[var107] = this.getvalue(var67, var90 + 10 + var107 * var100);
            this.gntb[var107] = this.getvalue(var67, var90 + 11 + var107 * var100);
            this.gplyrs[var107] = this.getSvalue(var67, var90 + 12 + var107 * var100);
            if (!this.gplyrs[var107].startsWith("#warb#")) {
               this.gwarb[var107] = 0;
               this.gwarbnum[var107] = "";
               this.gameturn[var107] = 0;
               this.gaclan[var107] = "";
               this.gvclan[var107] = "";
               this.gascore[var107] = 0;
               this.gvscore[var107] = 0;
               this.gwtyp[var107] = 0;
            } else {
               this.gwarb[var107] = this.getHvalue(this.gplyrs[var107], 2);
               this.gwarbnum[var107] = this.getHSvalue(this.gplyrs[var107], 3);
               this.gameturn[var107] = this.getHvalue(this.gplyrs[var107], 4);
               this.gaclan[var107] = this.getHSvalue(this.gplyrs[var107], 5);
               this.gvclan[var107] = this.getHSvalue(this.gplyrs[var107], 6);
               this.gascore[var107] = this.getHvalue(this.gplyrs[var107], 7);
               this.gvscore[var107] = this.getHvalue(this.gplyrs[var107], 8);
               this.gwtyp[var107] = this.getHvalue(this.gplyrs[var107], 9);
               if (this.gwtyp[var107] < 1 || this.gwtyp[var107] > 5) {
                  this.gwtyp[var107] = 1;
               }

               this.gplyrs[var107] = "";
            }

            if (this.xt.lan) {
               this.mnbts[var107] = this.getvalue(var67, var90 + 13 + var107 * var100);
            }

            if (this.xt.playingame > -1 && this.xt.playingame == this.gnum[var107] && !this.xt.lan) {
               this.ongame = this.gnum[var107];
            }

            if (var1 == this.gnum[var107] && this.wait[var107] == 0 && this.lloaded && var1 != -1) {
               for (int var120 = 0; var120 < this.npo; var120++) {
                  if (this.pgames[var120] == this.gnum[var107] && this.pnames[var120].equals(this.xt.nickname)) {
                     this.im = var120;
                     break;
                  }
               }

               this.conon = 2;
               this.gs.setCursor(new Cursor(3));
            }
         }

         for (int var108 = 0; var108 < this.ngm; var108++) {
            this.npls[var108] = 0;

            for (int var121 = 0; var121 < this.npo; var121++) {
               if (this.pgames[var121] == this.gnum[var108]) {
                  this.npls[var108]++;
               }
            }
         }

         if (this.conon != 0 && this.xt.playingame != -1) {
            this.xt.playingame = -1;
         }

         if (this.ongame != -1) {
            boolean var109 = false;

            for (int var122 = 0; var122 < this.ngm; var122++) {
               if (this.ongame == this.gnum[var122]) {
                  var109 = true;
               }
            }

            if (!var109) {
               this.britchl = -1;
            }
         }

         if (this.join > -1) {
            boolean var110 = false;

            for (int var123 = 0; var123 < this.ngm; var123++) {
               if (this.join == this.gnum[var123]) {
                  var110 = true;
               }
            }

            if (!var110) {
               this.join = -1;
            }
         }

         for (int var111 = 0; var111 < this.npo; var111++) {
            if (this.pgames[var111] != -1) {
               boolean var124 = false;

               for (int var131 = 0; var131 < this.ngm; var131++) {
                  if (this.pgames[var111] == this.gnum[var131]) {
                     var124 = true;
                  }
               }

               if (!var124) {
                  this.pgames[var111] = -1;
               }
            }
         }

         if (this.xt.lan) {
            var90 += 14 + (this.ngm - 1) * 13;
         } else {
            var90 += 13 + (this.ngm - 1) * 12;
         }

         if (this.xt.lan) {
            int var113 = this.getvalue(var67, var90);
            if (var113 == 1) {
               this.lanlogged = true;
            } else {
               this.lanlogged = false;
            }

            var90++;
         } else {
            int var112 = this.getvalue(var67, var90);
            int var125 = this.getvalue(var67, var90 + 1);
            var90 += 2;
            if (this.updatec != var112 && this.updatec >= -2 && var125 == this.ongame) {
               for (int var132 = 0; var132 < 7; var132++) {
                  this.cnames[var132] = this.getSvalue(var67, var90 + var132 * 2);
                  this.sentn[var132] = this.getSvalue(var67, var90 + 1 + var132 * 2);
               }

               this.updatec = var112;
               if (this.ongame == -1) {
                  this.spos3 = 28;
               }

               var90 += 14;
            }

            if (this.ongame != -1) {
               if (this.prevloaded != -1) {
                  this.prevloaded = -1;
               }

               boolean var133 = false;

               for (int var15 = 0; var15 < this.ngm; var15++) {
                  if (this.ongame == this.gnum[var15] && this.wait[var15] <= 0) {
                     var133 = true;
                  }
               }

               if (var133) {
                  this.prevloaded = this.getvalue(var67, var90);
                  var90++;
                  if (this.prevloaded == 1) {
                     this.prnpo = this.getvalue(var67, var90);
                     var90++;

                     for (int var140 = 0; var140 < this.prnpo; var140++) {
                        this.prnames[var140] = this.getSvalue(var67, var90);
                        var90++;
                     }

                     for (int var141 = 0; var141 < this.prnpo; var141++) {
                        this.ppos[var141] = this.getvalue(var67, var90);
                        var90++;
                     }

                     for (int var142 = 0; var142 < this.prnpo; var142++) {
                        this.plap[var142] = this.getvalue(var67, var90);
                        var90++;
                     }

                     for (int var143 = 0; var143 < this.prnpo; var143++) {
                        this.ppow[var143] = (int)(this.getvalue(var67, var90) / 9800.0F * 55.0F);
                        var90++;
                     }

                     for (int var144 = 0; var144 < this.prnpo; var144++) {
                        int var16 = this.getvalue(var67, var90);
                        if (var16 != -17) {
                           this.pdam[var144] = (int)(var16 / 100.0F * 55.0F);
                        } else {
                           this.pdam[var144] = -17;
                        }

                        var90++;
                     }

                     this.stuntname = this.getSvalue(var67, var90);
                     this.lapsname = this.getSvalue(var67, ++var90);
                     this.wastename = this.getSvalue(var67, ++var90);
                     var90++;
                  }
               }
            }
         }

         int var114 = this.getvalue(var67, var90);
         if (var114 != -1) {
            int var126 = 0;

            for (int var134 = 0; var134 < this.ngm; var134++) {
               if (var114 == this.gnum[var134]) {
                  var126 = var134;
               }
            }

            boolean var135 = false;
            if (this.gwarb[var126] != 0) {
               if (this.xt.clan.toLowerCase().equals(this.gaclan[var126].toLowerCase()) || this.xt.clan.toLowerCase().equals(this.gvclan[var126].toLowerCase())
                  )
                {
                  var135 = true;
               }
            } else {
               var135 = true;
            }

            if ((this.pgames[this.im] != this.ongame || this.ongame == -1)
               && var114 != this.ongame
               && this.chalngd == -1
               && this.join == -1
               && this.fase == 1
               && this.wait[var126] > 0
               && var135) {
               this.chalngd = var114;
               this.chalby = this.getSvalue(var67, var90 + 1);
               this.cflk = 20;
               this.ctime = 20;
               this.ptime = 0L;
               this.longame = this.ongame;
               if (this.gs.rooms.open) {
                  this.gs.rooms.open = false;
               }

               if (this.ongame != -1) {
                  this.britchl = -1;
               }
            }

            var90++;
         }

         if (!this.xt.lan) {
            int var127 = 1;

            for (int var136 = 1; var136 < 6; var136++) {
               if (var136 != this.xt.servport - 7070) {
                  int var145 = this.getvalue(var67, var90 + var136);
                  if (var145 != -1) {
                     this.gs.rooms.sopts[var127] = "Room " + var136 + "  ::  " + var145 + "";
                     this.gs.rooms.opts[var127] = "";
                     this.gs.rooms.iroom[var127] = var136;
                     var127++;
                  }
               }
            }

            for (int var137 = 0; var137 < this.lg.nservers; var137++) {
               if (!this.xt.server.equals(this.lg.servers[var137]) && this.xt.delays[var137] < 300) {
                  this.gs.rooms.sopts[var127] = ":: " + this.lg.snames[var137] + "";
                  this.gs.rooms.opts[var127] = "";
                  this.gs.rooms.iroom[var127] = 1000 + var137;
                  var127++;
               }
            }

            this.gs.rooms.no = var127;
         }

         if (this.join > -1) {
            boolean var128 = false;

            for (int var138 = 0; var138 < this.ngm; var138++) {
               if (this.join == this.gnum[var138] && this.wait[var138] == 0) {
                  var128 = true;
               }
            }

            if (this.pgames[this.im] == this.join || var128) {
               this.join = -1;
               this.nflk = 3;
            }
         }

         if (this.join == -2) {
            boolean var129 = false;

            for (int var139 = 0; var139 < this.ngm; var139++) {
               if (this.pgames[this.im] == this.gnum[var139] && this.wait[var139] == 0) {
                  var129 = true;
               }
            }

            if (this.pgames[this.im] == -1 || var129) {
               this.join = -1;
               if (!var129) {
                  this.ongame = -1;
               }
            }
         }

         if (this.chalngd == -5 && this.pgames[this.im] != -1) {
            this.ongame = this.pgames[this.im];
            this.chalngd = -1;
            if (!this.xt.lan && this.gplayers.equals("")) {
               this.lg.gamealert();
            }
         }

         if (this.fstart && var45) {
            this.fstart = false;
         }

         this.rerr = 0;
         if (!this.lloaded) {
            this.gs.setCursor(new Cursor(0));
            this.lloaded = true;
            System.out.println("LLOADED TRUE");
         }

         if (!this.gb.domon) {
            this.gb.roomlogos(this.pnames, this.npo);
            if (this.ongame == -1) {
               if (this.cntchkn == 5) {
                  this.lg.checkgamealerts();
               }
            } else if (this.lg.gamec != -1) {
               this.lg.gamec = -1;
            }

            if (this.cntchkn == 5) {
               this.lg.checknotifcations();
               this.cntchkn = 0;
            } else {
               this.cntchkn++;
            }
         } else if (this.lg.gamec != -1) {
            this.lg.gamec = -1;
         }

         var2 = new Date();
         long var130 = var2.getTime();
         int var146 = 700 - (int)(var130 - var3);
         if (var146 < 50) {
            var146 = 50;
         }

         try {
            Thread.sleep(var146);
         } catch (InterruptedException var32) {
         }
      }

      if (this.conon == 2) {
         int var39 = 20;
         this.xt.playingame = -1;

         while (var39 != 0) {
            String var41 = "2|" + var1 + "|";
            String var4 = "";
            boolean var46 = false;

            try {
               this.dout.println(var41);
               var4 = this.din.readLine();
               if (var4 == null) {
                  var46 = true;
               }
            } catch (Exception var31) {
               var46 = true;
            }

            if (var46) {
               try {
                  this.socket.close();
                  this.socket = null;
                  this.din.close();
                  this.din = null;
                  this.dout.close();
                  this.dout = null;
               } catch (Exception var30) {
               }

               try {
                  this.socket = new Socket(this.xt.server, this.xt.servport);
                  this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                  this.dout = new PrintWriter(this.socket.getOutputStream(), true);
                  this.dout.println(var41);
                  var4 = this.din.readLine();
                  if (var4 != null) {
                     var46 = false;
                  }
               } catch (Exception var29) {
               }
            }

            if (var46) {
               try {
                  this.socket.close();
                  this.socket = null;
               } catch (Exception var28) {
               }

               this.conon = 0;
               this.lg.exitfromlobby();
               this.hideinputs();
               // this.connector.stop();
            }

            if (!this.xt.lan) {
               this.xt.gameport = this.getvalue(var4, 0);
            } else {
               this.xt.gameport = -1;
               this.xt.localserver = this.getSevervalue(var4, 0);
               if (!this.xt.localserver.equals("")) {
                  this.xt.gameport = 0;
               }
            }

            if (this.xt.gameport == -1) {
               var39--;
            } else {
               int var51 = 0;
               this.xt.im = -1;
               this.xt.nplayers = this.getvalue(var4, 1);
               if (this.xt.nplayers < 1) {
                  this.xt.nplayers = 1;
               }

               if (this.xt.nplayers > Madness.maxPlayers()) {
                  this.xt.nplayers = Madness.maxPlayers();
               }

               for (int var54 = 0; var54 < this.xt.nplayers; var54++) {
                  this.xt.plnames[var54] = this.getSvalue(var4, 2 + var54);
                  if (this.xt.nickname.equals(this.xt.plnames[var54])) {
                     this.xt.im = var54;
                  }
               }

               int var55 = 2 + this.xt.nplayers;

               for (int var68 = 0; var68 < this.xt.nplayers; var68++) {
                  String var80 = this.getSvalue(var4, var55 + var68);
                  if (!var80.startsWith("C")) {
                     this.xt.sc[var68] = this.getvalue(var4, var55 + var68);
                     if (this.xt.sc[var68] == -1) {
                        this.xt.im = -1;
                     }
                  } else {
                     var80 = var80.substring(1);
                     if (var80.equals("")) {
                        this.xt.im = -1;
                     } else {
                        int var96 = 0;

                        for (int var101 = 16; var101 < 56; var101++) {
                           if (var80.equals(this.cd.names[var101])) {
                              var96 = var101;
                              break;
                           }
                        }

                        while (var96 == 0 && var51 < 100) {
                           boolean var102 = false;

                           for (int var115 = 0; var115 < this.cd.nl; var115++) {
                              if (var80.equals(this.cd.loadnames[var115])) {
                                 var102 = true;
                              }
                           }

                           if (!var102 && this.cd.nl < 20) {
                              this.cd.loadnames[this.cd.nl] = var80;
                              this.cd.nl++;
                           }

                           this.cd.sparkcarloader();

                           try {
                              Thread.sleep(100L);
                           } catch (InterruptedException var27) {
                           }

                           for (int var116 = 16; var116 < 56; var116++) {
                              if (var80.equals(this.cd.names[var116])) {
                                 var96 = var116;
                              }
                           }

                           var51++;
                        }

                        if (var96 != 0) {
                           this.xt.sc[var68] = var96;

                           for (int var103 = 0; var103 < this.npo; var103++) {
                              if (this.pcarnames[var103].equals(var80)) {
                                 this.pcars[var103] = var96;
                              }
                           }
                        } else {
                           this.xt.im = -1;
                        }
                     }
                  }
               }

               var55 += this.xt.nplayers;

               for (int var69 = 0; var69 < this.xt.nplayers; var69++) {
                  this.xt.xstart[var69] = this.getvalue(var4, var55 + var69);
               }

               var55 += this.xt.nplayers;

               for (int var70 = 0; var70 < this.xt.nplayers; var70++) {
                  this.xt.zstart[var70] = this.getvalue(var4, var55 + var70);
               }

               var55 += this.xt.nplayers;

               for (int var71 = 0; var71 < this.xt.nplayers; var71++) {
                  for (int var82 = 0; var82 < 6; var82++) {
                     this.xt.allrnp[var71][var82] = this.getvalue(var4, var55 + var71 * 6 + var82) / 100.0F;
                  }
               }

               if (this.xt.im != -1) {
                  this.xt.playingame = var1;
                  int var72 = 0;

                  for (int var83 = 0; var83 < this.ngm; var83++) {
                     if (var1 == this.gnum[var83]) {
                        var72 = var83;
                     }
                  }

                  if (this.gwarb[var72] != 0) {
                     this.xt.clangame = this.gwtyp[var72];
                     this.xt.clanchat = true;
                     this.xt.gaclan = this.gaclan[var72];

                     for (int var84 = 0; var84 < this.xt.nplayers; var84++) {
                        for (int var97 = 0; var97 < this.npo; var97++) {
                           if (this.xt.plnames[var84].equals(this.pnames[var97]) && this.pgames[var97] == var1) {
                              this.xt.pclan[var84] = this.pclan[var97];
                           }
                        }
                     }
                  } else {
                     this.xt.clangame = 0;
                  }
               } else {
                  this.xt.playingame = -1;
                  this.xt.im = 0;
               }

               var39 = 0;
            }

            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var26) {
            }
         }

         try {
            this.socket.close();
            this.socket = null;
            this.din.close();
            this.din = null;
            this.dout.close();
            this.dout = null;
         } catch (Exception var25) {
         }

         if (this.xt.playingame != -1) {
            if (!this.xt.lan && !this.xt.logged) {
               this.xt.nfreeplays++;

               try {
                  this.socket = new Socket(this.lg.servers[0], 7061);
                  this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                  this.dout = new PrintWriter(this.socket.getOutputStream(), true);
                  this.dout.println("7|" + this.xt.nfreeplays + "|");
                  String var42 = this.din.readLine();
                  this.xt.hours = this.getvalue(var42, 0);
                  this.socket.close();
                  this.socket = null;
                  this.din.close();
                  this.din = null;
                  this.dout.close();
                  this.dout = null;
               } catch (Exception var24) {
               }
            }

            this.hideinputs();
            this.xt.multion = 1;
            this.fase = 76;
            System.gc();
         } else {
            this.inishlobby();
         }
      }

      if (this.conon == 3) {
         int var40 = 20;
         this.xt.playingame = -1;

         while (var40 != 0) {
            String var43 = "4|" + this.ongame + "|";
            String var44 = "";
            boolean var47 = false;

            try {
               this.dout.println(var43);
               var44 = this.din.readLine();
               if (var44 == null) {
                  var47 = true;
               }
            } catch (Exception var23) {
               var47 = true;
            }

            if (var47) {
               try {
                  this.socket.close();
                  this.socket = null;
                  this.din.close();
                  this.din = null;
                  this.dout.close();
                  this.dout = null;
               } catch (Exception var22) {
               }

               try {
                  this.socket = new Socket(this.xt.server, this.xt.servport);
                  this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                  this.dout = new PrintWriter(this.socket.getOutputStream(), true);
                  this.dout.println(var43);
                  var44 = this.din.readLine();
                  if (var44 != null) {
                     var47 = false;
                  }
               } catch (Exception var21) {
               }
            }

            if (var47) {
               try {
                  this.socket.close();
                  this.socket = null;
               } catch (Exception var20) {
               }

               this.conon = 0;
               this.lg.exitfromlobby();
               this.hideinputs();
               // this.connector.stop();
            }

            if (!this.xt.lan) {
               this.xt.gameport = this.getvalue(var44, 0);
            } else {
               this.xt.gameport = -1;
               this.xt.localserver = this.getSevervalue(var44, 0);
               if (!this.xt.localserver.equals("")) {
                  this.xt.gameport = 0;
               }
            }

            if (this.xt.gameport == -1) {
               var40--;
            } else {
               int var52 = 0;
               this.xt.nplayers = this.getvalue(var44, 1);
               if (this.xt.nplayers < 1) {
                  this.xt.nplayers = 1;
               }

               if (this.xt.nplayers > Madness.maxPlayers()) {
                  this.xt.nplayers = Madness.maxPlayers();
               }

               this.xt.im = this.getvalue(var44, 2) + this.xt.nplayers;

               for (int var59 = 0; var59 < this.xt.nplayers; var59++) {
                  this.xt.plnames[var59] = this.getSvalue(var44, 3 + var59);
               }

               int var60 = 3 + this.xt.nplayers;

               for (int var73 = 0; var73 < this.xt.nplayers; var73++) {
                  String var85 = this.getSvalue(var44, var60 + var73);
                  if (!var85.startsWith("C")) {
                     this.xt.sc[var73] = this.getvalue(var44, var60 + var73);
                     if (this.xt.sc[var73] == -1) {
                        this.xt.im = -1;
                     }
                  } else {
                     var85 = var85.substring(1);
                     if (var85.equals("")) {
                        this.xt.im = -1;
                     } else {
                        int var98 = 0;

                        for (int var104 = 16; var104 < 56; var104++) {
                           if (var85.equals(this.cd.names[var104])) {
                              var98 = var104;
                              break;
                           }
                        }

                        while (var98 == 0 && var52 < 100) {
                           boolean var105 = false;

                           for (int var117 = 0; var117 < this.cd.nl; var117++) {
                              if (var85.equals(this.cd.loadnames[var117])) {
                                 var105 = true;
                              }
                           }

                           if (!var105 && this.cd.nl < 20) {
                              this.cd.loadnames[this.cd.nl] = var85;
                              this.cd.nl++;
                           }

                           this.cd.sparkcarloader();

                           try {
                              Thread.sleep(100L);
                           } catch (InterruptedException var19) {
                           }

                           for (int var118 = 16; var118 < 56; var118++) {
                              if (var85.equals(this.cd.names[var118])) {
                                 var98 = var118;
                              }
                           }

                           var52++;
                        }

                        if (var98 != 0) {
                           this.xt.sc[var73] = var98;

                           for (int var106 = 0; var106 < this.npo; var106++) {
                              if (this.pcarnames[var106].equals(var85)) {
                                 this.pcars[var106] = var98;
                              }
                           }
                        } else {
                           this.xt.im = -1;
                        }
                     }
                  }
               }

               var60 += this.xt.nplayers;

               for (int var74 = 0; var74 < this.xt.nplayers; var74++) {
                  this.xt.xstart[var74] = this.getvalue(var44, var60 + var74);
               }

               var60 += this.xt.nplayers;

               for (int var75 = 0; var75 < this.xt.nplayers; var75++) {
                  this.xt.zstart[var75] = this.getvalue(var44, var60 + var75);
               }

               var60 += this.xt.nplayers;

               for (int var76 = 0; var76 < this.xt.nplayers; var76++) {
                  for (int var87 = 0; var87 < 6; var87++) {
                     this.xt.allrnp[var76][var87] = this.getvalue(var44, var60 + var76 * 6 + var87) / 100.0F;
                  }
               }

               if (this.xt.im >= this.xt.nplayers && this.xt.im < this.xt.nplayers + 3) {
                  this.xt.playingame = this.ongame;
                  int var77 = 0;

                  for (int var88 = 0; var88 < this.ngm; var88++) {
                     if (this.ongame == this.gnum[var88]) {
                        var77 = var88;
                     }
                  }

                  if (this.gwarb[var77] != 0) {
                     this.xt.clangame = this.gwtyp[var77];
                     this.xt.gaclan = this.gaclan[var77];
                     if (this.xt.clan.toLowerCase().equals(this.gaclan[var77].toLowerCase())
                        || this.xt.clan.toLowerCase().equals(this.gvclan[var77].toLowerCase())) {
                        this.xt.clanchat = true;
                     }

                     for (int var89 = 0; var89 < this.xt.nplayers; var89++) {
                        for (int var99 = 0; var99 < this.npo; var99++) {
                           if (this.xt.plnames[var89].equals(this.pnames[var99]) && this.pgames[var99] == this.ongame) {
                              this.xt.pclan[var89] = this.pclan[var99];
                           }
                        }
                     }
                  } else {
                     this.xt.clangame = 0;
                  }
               } else {
                  this.xt.playingame = -1;
                  this.xt.im = 0;
               }

               var40 = 0;
            }

            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var18) {
            }
         }

         try {
            this.socket.close();
            this.socket = null;
            this.din.close();
            this.din = null;
            this.dout.close();
            this.dout = null;
         } catch (Exception var17) {
         }

         if (this.xt.playingame != -1) {
            this.hideinputs();
            this.xt.multion = 3;
            this.fase = 76;
            System.gc();
         } else {
            this.inishlobby();
         }
      }
   }

   public void stopallnow() {
      this.conon = 0;

      try {
         this.socket.close();
         this.socket = null;
         this.din.close();
         this.din = null;
         this.dout.close();
         this.dout = null;
      } catch (Exception var2) {
      }

      if (this.connector != null) {
         // this.connector.stop();
         this.connector = null;
      }
   }

   public void lobby(int param1, int param2, boolean param3, int param4, CheckPoints param5, Control param6, ContO[] param7) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 0000: aload 0
      // 0001: bipush 0
      // 0002: putfield Lobby.pre1 Z
      // 0005: aload 0
      // 0006: bipush 0
      // 0007: putfield Lobby.pre2 Z
      // 000a: bipush 0
      // 000b: istore 8
      // 000d: aload 0
      // 000e: getfield Lobby.xt LxtGraphics;
      // 0011: getfield xtGraphics.warning I
      // 0014: ifne 001c
      // 0017: goto 002e
      // 001c: aload 0
      // 001d: getfield Lobby.xt LxtGraphics;
      // 0020: getfield xtGraphics.warning I
      // 0023: sipush 210
      // 0026: if_icmpeq 002e
      // 0029: goto d07e
      // 002e: aload 0
      // 002f: getfield Lobby.regnow Z
      // 0032: ifeq 003a
      // 0035: goto cd54
      // 003a: aload 0
      // 003b: getfield Lobby.onjoin I
      // 003e: bipush -1
      // 003f: if_icmpeq 0047
      // 0042: goto c6fa
      // 0047: aload 0
      // 0048: getfield Lobby.xt LxtGraphics;
      // 004b: bipush 3
      // 004c: invokevirtual xtGraphics.mainbg (I)V
      // 004f: aload 0
      // 0050: getfield Lobby.britchl I
      // 0053: bipush -1
      // 0054: if_icmpeq 005c
      // 0057: goto 006b
      // 005c: aload 0
      // 005d: bipush -1
      // 005e: putfield Lobby.ongame I
      // 0061: aload 0
      // 0062: bipush 0
      // 0063: putfield Lobby.britchl I
      // 0066: goto 006b
      // 006b: aload 0
      // 006c: bipush 0
      // 006d: putfield Lobby.btn I
      // 0070: aload 0
      // 0071: bipush 0
      // 0072: putfield Lobby.pbtn I
      // 0075: aload 0
      // 0076: bipush 0
      // 0077: putfield Lobby.zeromsw Z
      // 007a: aload 0
      // 007b: getfield Lobby.npo I
      // 007e: istore 9
      // 0080: aload 0
      // 0081: getfield Lobby.invo Z
      // 0084: ifne 008c
      // 0087: goto 00c8
      // 008c: bipush 0
      // 008d: istore 9
      // 008f: bipush 0
      // 0090: istore 10
      // 0092: iload 10
      // 0094: aload 0
      // 0095: getfield Lobby.npo I
      // 0098: if_icmplt 00a0
      // 009b: goto 00c0
      // 00a0: aload 0
      // 00a1: getfield Lobby.pgames [I
      // 00a4: iload 10
      // 00a6: iaload
      // 00a7: bipush -1
      // 00a8: if_icmpeq 00b0
      // 00ab: goto 00b8
      // 00b0: iinc 9 1
      // 00b3: goto 00b8
      // 00b8: iinc 10 1
      // 00bb: goto 0092
      // 00c0: iinc 9 2
      // 00c3: goto 00c8
      // 00c8: iload 9
      // 00ca: bipush 11
      // 00cc: isub
      // 00cd: bipush 30
      // 00cf: imul
      // 00d0: istore 10
      // 00d2: iload 10
      // 00d4: iflt 00dc
      // 00d7: goto 00e4
      // 00dc: bipush 0
      // 00dd: istore 10
      // 00df: goto 00e4
      // 00e4: aload 0
      // 00e5: getfield Lobby.spos I
      // 00e8: i2f
      // 00e9: ldc_w 295.0
      // 00ec: fdiv
      // 00ed: iload 10
      // 00ef: i2f
      // 00f0: fmul
      // 00f1: f2i
      // 00f2: istore 11
      // 00f4: bipush 0
      // 00f5: istore 12
      // 00f7: bipush -1
      // 00f8: istore 13
      // 00fa: bipush -1
      // 00fb: istore 14
      // 00fd: aload 0
      // 00fe: getfield Lobby.conon I
      // 0101: bipush 1
      // 0102: if_icmpeq 010a
      // 0105: goto 0f2e
      // 010a: aload 0
      // 010b: getfield Lobby.invo Z
      // 010e: ifeq 0116
      // 0111: goto 04c3
      // 0116: bipush 0
      // 0117: istore 15
      // 0119: iload 15
      // 011b: aload 0
      // 011c: getfield Lobby.npo I
      // 011f: if_icmplt 0127
      // 0122: goto 04be
      // 0127: aload 0
      // 0128: getfield Lobby.pgames [I
      // 012b: iload 15
      // 012d: iaload
      // 012e: bipush -1
      // 012f: if_icmpne 0137
      // 0132: goto 04b6
      // 0137: bipush 0
      // 0138: istore 16
      // 013a: bipush 0
      // 013b: istore 17
      // 013d: iload 17
      // 013f: aload 0
      // 0140: getfield Lobby.ngm I
      // 0143: if_icmplt 014b
      // 0146: goto 0172
      // 014b: aload 0
      // 014c: getfield Lobby.pgames [I
      // 014f: iload 15
      // 0151: iaload
      // 0152: aload 0
      // 0153: getfield Lobby.gnum [I
      // 0156: iload 17
      // 0158: iaload
      // 0159: if_icmpeq 0161
      // 015c: goto 016a
      // 0161: iload 17
      // 0163: istore 16
      // 0165: goto 016a
      // 016a: iinc 17 1
      // 016d: goto 013d
      // 0172: aload 0
      // 0173: getfield Lobby.wait [I
      // 0176: iload 16
      // 0178: iaload
      // 0179: ifgt 0181
      // 017c: goto 04b1
      // 0181: bipush 82
      // 0183: bipush 30
      // 0185: iload 12
      // 0187: imul
      // 0188: iadd
      // 0189: iload 11
      // 018b: isub
      // 018c: bipush 50
      // 018e: if_icmpgt 0196
      // 0191: goto 04a9
      // 0196: bipush 82
      // 0198: bipush 30
      // 019a: iload 12
      // 019c: bipush 1
      // 019d: isub
      // 019e: imul
      // 019f: iadd
      // 01a0: iload 11
      // 01a2: isub
      // 01a3: sipush 415
      // 01a6: if_icmplt 01ae
      // 01a9: goto 04a9
      // 01ae: bipush 0
      // 01af: istore 17
      // 01b1: iload 1
      // 01b2: bipush 70
      // 01b4: if_icmpgt 01bc
      // 01b7: goto 0345
      // 01bc: iload 1
      // 01bd: sipush 185
      // 01c0: if_icmplt 01c8
      // 01c3: goto 0345
      // 01c8: iload 2
      // 01c9: bipush 52
      // 01cb: bipush 30
      // 01cd: iload 12
      // 01cf: imul
      // 01d0: iadd
      // 01d1: iload 11
      // 01d3: isub
      // 01d4: if_icmpgt 01dc
      // 01d7: goto 0345
      // 01dc: iload 2
      // 01dd: bipush 82
      // 01df: bipush 30
      // 01e1: iload 12
      // 01e3: imul
      // 01e4: iadd
      // 01e5: iload 11
      // 01e7: isub
      // 01e8: if_icmplt 01f0
      // 01eb: goto 0345
      // 01f0: aload 0
      // 01f1: getfield Lobby.pgames [I
      // 01f4: aload 0
      // 01f5: getfield Lobby.im I
      // 01f8: iaload
      // 01f9: bipush -1
      // 01fa: if_icmpeq 0202
      // 01fd: goto 02fa
      // 0202: aload 0
      // 0203: getfield Lobby.join I
      // 0206: bipush -1
      // 0207: if_icmpeq 020f
      // 020a: goto 02fa
      // 020f: aload 0
      // 0210: getfield Lobby.chalngd I
      // 0213: bipush -1
      // 0214: if_icmpge 021c
      // 0217: goto 02fa
      // 021c: iload 3
      // 021d: ifeq 0225
      // 0220: goto 0233
      // 0225: aload 0
      // 0226: getfield Lobby.mousonp I
      // 0229: iload 15
      // 022b: if_icmpeq 0233
      // 022e: goto 02c5
      // 0233: aload 0
      // 0234: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0237: aload 0
      // 0238: sipush 255
      // 023b: sipush 255
      // 023e: sipush 255
      // 0241: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0244: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0247: aload 0
      // 0248: iload 15
      // 024a: putfield Lobby.mousonp I
      // 024d: bipush 52
      // 024f: bipush 30
      // 0251: iload 12
      // 0253: imul
      // 0254: iadd
      // 0255: iload 11
      // 0257: isub
      // 0258: istore 14
      // 025a: iload 3
      // 025b: ifne 0263
      // 025e: goto 028b
      // 0263: aload 0
      // 0264: getfield Lobby.cmonp I
      // 0267: iload 15
      // 0269: if_icmpeq 0271
      // 026c: goto 0281
      // 0271: aload 0
      // 0272: aload 0
      // 0273: getfield Lobby.pgames [I
      // 0276: iload 15
      // 0278: iaload
      // 0279: putfield Lobby.ongame I
      // 027c: goto 0281
      // 0281: aload 0
      // 0282: bipush -1
      // 0283: putfield Lobby.chalngd I
      // 0286: goto 02d9
      // 028b: aload 0
      // 028c: getfield Lobby.cmonp I
      // 028f: bipush -1
      // 0290: if_icmpeq 0298
      // 0293: goto 02a8
      // 0298: aload 0
      // 0299: bipush -1
      // 029a: putfield Lobby.ongame I
      // 029d: aload 0
      // 029e: iload 15
      // 02a0: putfield Lobby.cmonp I
      // 02a3: goto 02a8
      // 02a8: aload 0
      // 02a9: getfield Lobby.ongame I
      // 02ac: aload 0
      // 02ad: getfield Lobby.pgames [I
      // 02b0: iload 15
      // 02b2: iaload
      // 02b3: if_icmpeq 02bb
      // 02b6: goto 02d9
      // 02bb: aload 0
      // 02bc: bipush -1
      // 02bd: putfield Lobby.mousonp I
      // 02c0: goto 02d9
      // 02c5: aload 0
      // 02c6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 02c9: aload 0
      // 02ca: sipush 220
      // 02cd: sipush 220
      // 02d0: sipush 220
      // 02d3: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 02d6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 02d9: aload 0
      // 02da: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 02dd: bipush 70
      // 02df: bipush 53
      // 02e1: bipush 30
      // 02e3: iload 12
      // 02e5: imul
      // 02e6: iadd
      // 02e7: iload 11
      // 02e9: isub
      // 02ea: bipush 116
      // 02ec: bipush 29
      // 02ee: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 02f1: iload 15
      // 02f3: istore 13
      // 02f5: goto 02fa
      // 02fa: bipush 1
      // 02fb: istore 17
      // 02fd: aload 6
      // 02ff: getfield Control.handb Z
      // 0302: ifne 030a
      // 0305: goto 0345
      // 030a: aload 0
      // 030b: getfield Lobby.gs LGameSparker;
      // 030e: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 0311: new java/lang/StringBuilder
      // 0314: dup
      // 0315: invokespecial java/lang/StringBuilder.<init> ()V
      // 0318: aload 0
      // 0319: getfield Lobby.gs LGameSparker;
      // 031c: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 031f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 0322: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0325: ldc ""
      // 0327: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 032a: aload 0
      // 032b: getfield Lobby.pnames [Ljava/lang/String;
      // 032e: iload 15
      // 0330: aaload
      // 0331: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0334: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0337: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 033a: aload 6
      // 033c: bipush 0
      // 033d: putfield Control.handb Z
      // 0340: goto 0345
      // 0345: aload 0
      // 0346: getfield Lobby.pgames [I
      // 0349: aload 0
      // 034a: getfield Lobby.im I
      // 034d: iaload
      // 034e: bipush -1
      // 034f: if_icmpeq 0357
      // 0352: goto 0389
      // 0357: aload 0
      // 0358: getfield Lobby.join I
      // 035b: bipush -1
      // 035c: if_icmpeq 0364
      // 035f: goto 0389
      // 0364: aload 0
      // 0365: getfield Lobby.chalngd I
      // 0368: bipush -1
      // 0369: if_icmpge 0371
      // 036c: goto 0389
      // 0371: aload 0
      // 0372: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0375: new java/awt/Color
      // 0378: dup
      // 0379: bipush 49
      // 037b: bipush 79
      // 037d: bipush 0
      // 037e: invokespecial java/awt/Color.<init> (III)V
      // 0381: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0384: goto 039c
      // 0389: aload 0
      // 038a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 038d: new java/awt/Color
      // 0390: dup
      // 0391: bipush 34
      // 0393: bipush 55
      // 0395: bipush 0
      // 0396: invokespecial java/awt/Color.<init> (III)V
      // 0399: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 039c: aload 0
      // 039d: getfield Lobby.gb LGlobe;
      // 03a0: aload 0
      // 03a1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 03a4: aload 0
      // 03a5: getfield Lobby.pnames [Ljava/lang/String;
      // 03a8: iload 15
      // 03aa: aaload
      // 03ab: bipush 68
      // 03ad: bipush 53
      // 03af: bipush 30
      // 03b1: iload 12
      // 03b3: imul
      // 03b4: iadd
      // 03b5: iload 11
      // 03b7: isub
      // 03b8: iload 17
      // 03ba: invokevirtual Globe.drawl (Ljava/awt/Graphics2D;Ljava/lang/String;IIZ)Z
      // 03bd: istore 18
      // 03bf: iload 17
      // 03c1: ifne 03c9
      // 03c4: goto 03d3
      // 03c9: iload 18
      // 03cb: ifeq 03d3
      // 03ce: goto 046e
      // 03d3: aload 0
      // 03d4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 03d7: new java/awt/Font
      // 03da: dup
      // 03db: ldc_w "Arial"
      // 03de: bipush 1
      // 03df: bipush 12
      // 03e1: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 03e4: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 03e7: aload 0
      // 03e8: aload 0
      // 03e9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 03ec: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 03ef: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 03f2: aload 0
      // 03f3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 03f6: aload 0
      // 03f7: getfield Lobby.pnames [Ljava/lang/String;
      // 03fa: iload 15
      // 03fc: aaload
      // 03fd: bipush 127
      // 03ff: aload 0
      // 0400: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0403: aload 0
      // 0404: getfield Lobby.pnames [Ljava/lang/String;
      // 0407: iload 15
      // 0409: aaload
      // 040a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 040d: bipush 2
      // 040e: idiv
      // 040f: isub
      // 0410: bipush 66
      // 0412: bipush 30
      // 0414: iload 12
      // 0416: imul
      // 0417: iadd
      // 0418: iload 11
      // 041a: isub
      // 041b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 041e: aload 0
      // 041f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0422: new java/awt/Font
      // 0425: dup
      // 0426: ldc_w "Arial"
      // 0429: bipush 0
      // 042a: bipush 10
      // 042c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 042f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0432: aload 0
      // 0433: aload 0
      // 0434: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0437: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 043a: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 043d: aload 0
      // 043e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0441: aload 0
      // 0442: getfield Lobby.pcarnames [Ljava/lang/String;
      // 0445: iload 15
      // 0447: aaload
      // 0448: bipush 127
      // 044a: aload 0
      // 044b: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 044e: aload 0
      // 044f: getfield Lobby.pcarnames [Ljava/lang/String;
      // 0452: iload 15
      // 0454: aaload
      // 0455: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0458: bipush 2
      // 0459: idiv
      // 045a: isub
      // 045b: bipush 78
      // 045d: bipush 30
      // 045f: iload 12
      // 0461: imul
      // 0462: iadd
      // 0463: iload 11
      // 0465: isub
      // 0466: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0469: goto 046e
      // 046e: aload 0
      // 046f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0472: aload 0
      // 0473: sipush 150
      // 0476: sipush 150
      // 0479: sipush 150
      // 047c: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 047f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0482: aload 0
      // 0483: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0486: bipush 70
      // 0488: bipush 82
      // 048a: bipush 30
      // 048c: iload 12
      // 048e: imul
      // 048f: iadd
      // 0490: iload 11
      // 0492: isub
      // 0493: sipush 185
      // 0496: bipush 82
      // 0498: bipush 30
      // 049a: iload 12
      // 049c: imul
      // 049d: iadd
      // 049e: iload 11
      // 04a0: isub
      // 04a1: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 04a4: goto 04a9
      // 04a9: iinc 12 1
      // 04ac: goto 04b1
      // 04b1: goto 04b6
      // 04b6: iinc 15 1
      // 04b9: goto 0119
      // 04be: goto 04c3
      // 04c3: bipush -1
      // 04c4: istore 15
      // 04c6: aload 0
      // 04c7: getfield Lobby.invo Z
      // 04ca: ifne 04d2
      // 04cd: goto 0606
      // 04d2: bipush 0
      // 04d3: istore 16
      // 04d5: iload 16
      // 04d7: aload 0
      // 04d8: getfield Lobby.ngm I
      // 04db: if_icmplt 04e3
      // 04de: goto 051b
      // 04e3: aload 0
      // 04e4: getfield Lobby.gwarb [I
      // 04e7: iload 16
      // 04e9: iaload
      // 04ea: ifne 04f2
      // 04ed: goto 0513
      // 04f2: aload 0
      // 04f3: getfield Lobby.pgames [I
      // 04f6: aload 0
      // 04f7: getfield Lobby.im I
      // 04fa: iaload
      // 04fb: aload 0
      // 04fc: getfield Lobby.gnum [I
      // 04ff: iload 16
      // 0501: iaload
      // 0502: if_icmpeq 050a
      // 0505: goto 0513
      // 050a: iload 16
      // 050c: istore 15
      // 050e: goto 0513
      // 0513: iinc 16 1
      // 0516: goto 04d5
      // 051b: aload 0
      // 051c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 051f: new java/awt/Color
      // 0522: dup
      // 0523: bipush 0
      // 0524: bipush 0
      // 0525: bipush 0
      // 0526: invokespecial java/awt/Color.<init> (III)V
      // 0529: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 052c: aload 0
      // 052d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0530: new java/awt/Font
      // 0533: dup
      // 0534: ldc_w "Arial"
      // 0537: bipush 1
      // 0538: bipush 12
      // 053a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 053d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0540: aload 0
      // 0541: aload 0
      // 0542: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0545: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0548: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 054b: iload 15
      // 054d: bipush -1
      // 054e: if_icmpeq 0556
      // 0551: goto 0579
      // 0556: aload 0
      // 0557: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 055a: ldc_w "Free Players"
      // 055d: bipush 127
      // 055f: aload 0
      // 0560: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0563: ldc_w "Free Players"
      // 0566: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0569: bipush 2
      // 056a: idiv
      // 056b: isub
      // 056c: bipush 75
      // 056e: iload 11
      // 0570: isub
      // 0571: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0574: goto 0597
      // 0579: aload 0
      // 057a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 057d: ldc_w "Members of Clans"
      // 0580: bipush 127
      // 0582: aload 0
      // 0583: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0586: ldc_w "Members of Clans"
      // 0589: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 058c: bipush 2
      // 058d: idiv
      // 058e: isub
      // 058f: bipush 75
      // 0591: iload 11
      // 0593: isub
      // 0594: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0597: aload 0
      // 0598: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 059b: new java/awt/Font
      // 059e: dup
      // 059f: ldc_w "Arial"
      // 05a2: bipush 0
      // 05a3: bipush 10
      // 05a5: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 05a8: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 05ab: aload 0
      // 05ac: aload 0
      // 05ad: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 05b0: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 05b3: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 05b6: aload 0
      // 05b7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 05ba: ldc_w "Click a player to invite:"
      // 05bd: bipush 127
      // 05bf: aload 0
      // 05c0: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 05c3: ldc_w "Click a player to invite:"
      // 05c6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 05c9: bipush 2
      // 05ca: idiv
      // 05cb: isub
      // 05cc: bipush 92
      // 05ce: iload 11
      // 05d0: isub
      // 05d1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 05d4: aload 0
      // 05d5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 05d8: aload 0
      // 05d9: sipush 150
      // 05dc: sipush 150
      // 05df: sipush 150
      // 05e2: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 05e5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 05e8: aload 0
      // 05e9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 05ec: bipush 70
      // 05ee: bipush 112
      // 05f0: iload 11
      // 05f2: isub
      // 05f3: sipush 185
      // 05f6: bipush 112
      // 05f8: iload 11
      // 05fa: isub
      // 05fb: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 05fe: iinc 12 2
      // 0601: goto 0606
      // 0606: bipush 0
      // 0607: istore 16
      // 0609: iload 16
      // 060b: aload 0
      // 060c: getfield Lobby.npo I
      // 060f: if_icmplt 0617
      // 0612: goto 0a53
      // 0617: bipush 0
      // 0618: istore 17
      // 061a: aload 0
      // 061b: getfield Lobby.invo Z
      // 061e: ifne 0626
      // 0621: goto 06cf
      // 0626: aload 0
      // 0627: getfield Lobby.im I
      // 062a: iload 16
      // 062c: if_icmpeq 0634
      // 062f: goto 063c
      // 0634: bipush 1
      // 0635: istore 17
      // 0637: goto 063c
      // 063c: bipush 0
      // 063d: istore 18
      // 063f: iload 18
      // 0641: bipush 7
      // 0643: if_icmplt 064b
      // 0646: goto 067e
      // 064b: aload 0
      // 064c: getfield Lobby.invos [Ljava/lang/String;
      // 064f: iload 18
      // 0651: aaload
      // 0652: aload 0
      // 0653: getfield Lobby.pnames [Ljava/lang/String;
      // 0656: iload 16
      // 0658: aaload
      // 0659: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 065c: ifne 0664
      // 065f: goto 0676
      // 0664: iload 17
      // 0666: ifeq 066e
      // 0669: goto 0676
      // 066e: bipush 1
      // 066f: istore 17
      // 0671: goto 0676
      // 0676: iinc 18 1
      // 0679: goto 063f
      // 067e: iload 15
      // 0680: bipush -1
      // 0681: if_icmpne 0689
      // 0684: goto 06cf
      // 0689: aload 0
      // 068a: getfield Lobby.pclan [Ljava/lang/String;
      // 068d: iload 16
      // 068f: aaload
      // 0690: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 0693: aload 0
      // 0694: getfield Lobby.gaclan [Ljava/lang/String;
      // 0697: iload 15
      // 0699: aaload
      // 069a: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 069d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 06a0: ifeq 06a8
      // 06a3: goto 06cf
      // 06a8: aload 0
      // 06a9: getfield Lobby.pclan [Ljava/lang/String;
      // 06ac: iload 16
      // 06ae: aaload
      // 06af: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 06b2: aload 0
      // 06b3: getfield Lobby.gvclan [Ljava/lang/String;
      // 06b6: iload 15
      // 06b8: aaload
      // 06b9: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 06bc: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 06bf: ifeq 06c7
      // 06c2: goto 06cf
      // 06c7: bipush 1
      // 06c8: istore 17
      // 06ca: goto 06cf
      // 06cf: aload 0
      // 06d0: getfield Lobby.pgames [I
      // 06d3: iload 16
      // 06d5: iaload
      // 06d6: bipush -1
      // 06d7: if_icmpeq 06df
      // 06da: goto 0a4b
      // 06df: iload 17
      // 06e1: ifeq 06e9
      // 06e4: goto 0a4b
      // 06e9: bipush 82
      // 06eb: bipush 30
      // 06ed: iload 12
      // 06ef: imul
      // 06f0: iadd
      // 06f1: iload 11
      // 06f3: isub
      // 06f4: bipush 50
      // 06f6: if_icmpgt 06fe
      // 06f9: goto 0a43
      // 06fe: bipush 82
      // 0700: bipush 30
      // 0702: iload 12
      // 0704: bipush 1
      // 0705: isub
      // 0706: imul
      // 0707: iadd
      // 0708: iload 11
      // 070a: isub
      // 070b: sipush 415
      // 070e: if_icmplt 0716
      // 0711: goto 0a43
      // 0716: bipush 0
      // 0717: istore 18
      // 0719: iload 1
      // 071a: bipush 70
      // 071c: if_icmpgt 0724
      // 071f: goto 0901
      // 0724: iload 1
      // 0725: sipush 185
      // 0728: if_icmplt 0730
      // 072b: goto 0901
      // 0730: iload 2
      // 0731: bipush 52
      // 0733: bipush 30
      // 0735: iload 12
      // 0737: imul
      // 0738: iadd
      // 0739: iload 11
      // 073b: isub
      // 073c: if_icmpgt 0744
      // 073f: goto 0901
      // 0744: iload 2
      // 0745: bipush 82
      // 0747: bipush 30
      // 0749: iload 12
      // 074b: imul
      // 074c: iadd
      // 074d: iload 11
      // 074f: isub
      // 0750: if_icmplt 0758
      // 0753: goto 0901
      // 0758: aload 0
      // 0759: getfield Lobby.invo Z
      // 075c: ifne 0764
      // 075f: goto 082c
      // 0764: iload 3
      // 0765: ifne 076d
      // 0768: goto 078c
      // 076d: aload 0
      // 076e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0771: aload 0
      // 0772: sipush 255
      // 0775: sipush 255
      // 0778: sipush 255
      // 077b: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 077e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0781: aload 0
      // 0782: iload 16
      // 0784: putfield Lobby.mousonp I
      // 0787: goto 080b
      // 078c: aload 0
      // 078d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0790: aload 0
      // 0791: sipush 220
      // 0794: sipush 220
      // 0797: sipush 220
      // 079a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 079d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 07a0: aload 0
      // 07a1: getfield Lobby.mousonp I
      // 07a4: iload 16
      // 07a6: if_icmpeq 07ae
      // 07a9: goto 080b
      // 07ae: bipush 0
      // 07af: istore 19
      // 07b1: bipush 0
      // 07b2: istore 20
      // 07b4: iload 19
      // 07b6: bipush 7
      // 07b8: if_icmplt 07c0
      // 07bb: goto 07fc
      // 07c0: iload 20
      // 07c2: ifeq 07ca
      // 07c5: goto 07fc
      // 07ca: aload 0
      // 07cb: getfield Lobby.invos [Ljava/lang/String;
      // 07ce: iload 19
      // 07d0: aaload
      // 07d1: ldc ""
      // 07d3: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 07d6: ifne 07de
      // 07d9: goto 07f4
      // 07de: aload 0
      // 07df: getfield Lobby.invos [Ljava/lang/String;
      // 07e2: iload 19
      // 07e4: aload 0
      // 07e5: getfield Lobby.pnames [Ljava/lang/String;
      // 07e8: iload 16
      // 07ea: aaload
      // 07eb: aastore
      // 07ec: bipush 1
      // 07ed: istore 20
      // 07ef: goto 07f4
      // 07f4: iinc 19 1
      // 07f7: goto 07b4
      // 07fc: aload 0
      // 07fd: bipush -1
      // 07fe: putfield Lobby.mousonp I
      // 0801: aload 0
      // 0802: bipush 0
      // 0803: putfield Lobby.invo Z
      // 0806: goto 080b
      // 080b: aload 0
      // 080c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 080f: bipush 70
      // 0811: bipush 53
      // 0813: bipush 30
      // 0815: iload 12
      // 0817: imul
      // 0818: iadd
      // 0819: iload 11
      // 081b: isub
      // 081c: bipush 116
      // 081e: bipush 29
      // 0820: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 0823: iload 16
      // 0825: istore 13
      // 0827: goto 08b6
      // 082c: aload 0
      // 082d: getfield Lobby.pgames [I
      // 0830: aload 0
      // 0831: getfield Lobby.im I
      // 0834: iaload
      // 0835: bipush -1
      // 0836: if_icmpeq 083e
      // 0839: goto 08b6
      // 083e: aload 0
      // 083f: getfield Lobby.join I
      // 0842: bipush -1
      // 0843: if_icmpeq 084b
      // 0846: goto 08b6
      // 084b: aload 0
      // 084c: getfield Lobby.chalngd I
      // 084f: bipush -1
      // 0850: if_icmpge 0858
      // 0853: goto 08b6
      // 0858: bipush 12
      // 085a: istore 8
      // 085c: iload 3
      // 085d: ifne 0865
      // 0860: goto 08b6
      // 0865: aload 0
      // 0866: getfield Lobby.gb LGlobe;
      // 0869: getfield Globe.proname Ljava/lang/String;
      // 086c: aload 0
      // 086d: getfield Lobby.pnames [Ljava/lang/String;
      // 0870: iload 16
      // 0872: aaload
      // 0873: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0876: ifeq 087e
      // 0879: goto 0899
      // 087e: aload 0
      // 087f: getfield Lobby.gb LGlobe;
      // 0882: aload 0
      // 0883: getfield Lobby.pnames [Ljava/lang/String;
      // 0886: iload 16
      // 0888: aaload
      // 0889: putfield Globe.proname Ljava/lang/String;
      // 088c: aload 0
      // 088d: getfield Lobby.gb LGlobe;
      // 0890: bipush 0
      // 0891: putfield Globe.loadedp Z
      // 0894: goto 0899
      // 0899: aload 0
      // 089a: getfield Lobby.gb LGlobe;
      // 089d: bipush 1
      // 089e: putfield Globe.tab I
      // 08a1: aload 0
      // 08a2: getfield Lobby.gb LGlobe;
      // 08a5: bipush 2
      // 08a6: putfield Globe.open I
      // 08a9: aload 0
      // 08aa: getfield Lobby.gb LGlobe;
      // 08ad: bipush 1
      // 08ae: putfield Globe.upo Z
      // 08b1: goto 08b6
      // 08b6: bipush 1
      // 08b7: istore 18
      // 08b9: aload 6
      // 08bb: getfield Control.handb Z
      // 08be: ifne 08c6
      // 08c1: goto 0901
      // 08c6: aload 0
      // 08c7: getfield Lobby.gs LGameSparker;
      // 08ca: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 08cd: new java/lang/StringBuilder
      // 08d0: dup
      // 08d1: invokespecial java/lang/StringBuilder.<init> ()V
      // 08d4: aload 0
      // 08d5: getfield Lobby.gs LGameSparker;
      // 08d8: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 08db: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 08de: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08e1: ldc ""
      // 08e3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08e6: aload 0
      // 08e7: getfield Lobby.pnames [Ljava/lang/String;
      // 08ea: iload 16
      // 08ec: aaload
      // 08ed: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 08f0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 08f3: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 08f6: aload 6
      // 08f8: bipush 0
      // 08f9: putfield Control.handb Z
      // 08fc: goto 0901
      // 0901: aload 0
      // 0902: getfield Lobby.invo Z
      // 0905: ifne 090d
      // 0908: goto 0925
      // 090d: aload 0
      // 090e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0911: new java/awt/Color
      // 0914: dup
      // 0915: bipush 62
      // 0917: bipush 98
      // 0919: bipush 0
      // 091a: invokespecial java/awt/Color.<init> (III)V
      // 091d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0920: goto 0936
      // 0925: aload 0
      // 0926: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0929: new java/awt/Color
      // 092c: dup
      // 092d: bipush 0
      // 092e: bipush 0
      // 092f: bipush 0
      // 0930: invokespecial java/awt/Color.<init> (III)V
      // 0933: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0936: aload 0
      // 0937: getfield Lobby.gb LGlobe;
      // 093a: aload 0
      // 093b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 093e: aload 0
      // 093f: getfield Lobby.pnames [Ljava/lang/String;
      // 0942: iload 16
      // 0944: aaload
      // 0945: bipush 68
      // 0947: bipush 53
      // 0949: bipush 30
      // 094b: iload 12
      // 094d: imul
      // 094e: iadd
      // 094f: iload 11
      // 0951: isub
      // 0952: iload 18
      // 0954: invokevirtual Globe.drawl (Ljava/awt/Graphics2D;Ljava/lang/String;IIZ)Z
      // 0957: istore 19
      // 0959: iload 18
      // 095b: ifne 0963
      // 095e: goto 096d
      // 0963: iload 19
      // 0965: ifeq 096d
      // 0968: goto 0a08
      // 096d: aload 0
      // 096e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0971: new java/awt/Font
      // 0974: dup
      // 0975: ldc_w "Arial"
      // 0978: bipush 1
      // 0979: bipush 12
      // 097b: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 097e: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0981: aload 0
      // 0982: aload 0
      // 0983: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0986: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0989: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 098c: aload 0
      // 098d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0990: aload 0
      // 0991: getfield Lobby.pnames [Ljava/lang/String;
      // 0994: iload 16
      // 0996: aaload
      // 0997: bipush 127
      // 0999: aload 0
      // 099a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 099d: aload 0
      // 099e: getfield Lobby.pnames [Ljava/lang/String;
      // 09a1: iload 16
      // 09a3: aaload
      // 09a4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 09a7: bipush 2
      // 09a8: idiv
      // 09a9: isub
      // 09aa: bipush 66
      // 09ac: bipush 30
      // 09ae: iload 12
      // 09b0: imul
      // 09b1: iadd
      // 09b2: iload 11
      // 09b4: isub
      // 09b5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 09b8: aload 0
      // 09b9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 09bc: new java/awt/Font
      // 09bf: dup
      // 09c0: ldc_w "Arial"
      // 09c3: bipush 0
      // 09c4: bipush 10
      // 09c6: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 09c9: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 09cc: aload 0
      // 09cd: aload 0
      // 09ce: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 09d1: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 09d4: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 09d7: aload 0
      // 09d8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 09db: aload 0
      // 09dc: getfield Lobby.pcarnames [Ljava/lang/String;
      // 09df: iload 16
      // 09e1: aaload
      // 09e2: bipush 127
      // 09e4: aload 0
      // 09e5: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 09e8: aload 0
      // 09e9: getfield Lobby.pcarnames [Ljava/lang/String;
      // 09ec: iload 16
      // 09ee: aaload
      // 09ef: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 09f2: bipush 2
      // 09f3: idiv
      // 09f4: isub
      // 09f5: bipush 78
      // 09f7: bipush 30
      // 09f9: iload 12
      // 09fb: imul
      // 09fc: iadd
      // 09fd: iload 11
      // 09ff: isub
      // 0a00: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0a03: goto 0a08
      // 0a08: aload 0
      // 0a09: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0a0c: aload 0
      // 0a0d: sipush 150
      // 0a10: sipush 150
      // 0a13: sipush 150
      // 0a16: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0a19: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0a1c: aload 0
      // 0a1d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0a20: bipush 70
      // 0a22: bipush 82
      // 0a24: bipush 30
      // 0a26: iload 12
      // 0a28: imul
      // 0a29: iadd
      // 0a2a: iload 11
      // 0a2c: isub
      // 0a2d: sipush 185
      // 0a30: bipush 82
      // 0a32: bipush 30
      // 0a34: iload 12
      // 0a36: imul
      // 0a37: iadd
      // 0a38: iload 11
      // 0a3a: isub
      // 0a3b: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0a3e: goto 0a43
      // 0a43: iinc 12 1
      // 0a46: goto 0a4b
      // 0a4b: iinc 16 1
      // 0a4e: goto 0609
      // 0a53: aload 0
      // 0a54: getfield Lobby.invo Z
      // 0a57: ifne 0a5f
      // 0a5a: goto 0a74
      // 0a5f: iload 12
      // 0a61: bipush 2
      // 0a62: if_icmpeq 0a6a
      // 0a65: goto 0a74
      // 0a6a: aload 0
      // 0a6b: bipush 0
      // 0a6c: putfield Lobby.invo Z
      // 0a6f: goto 0a74
      // 0a74: aload 0
      // 0a75: getfield Lobby.invo Z
      // 0a78: ifeq 0a80
      // 0a7b: goto 0f29
      // 0a80: aload 0
      // 0a81: getfield Lobby.npo I
      // 0a84: bipush 1
      // 0a85: isub
      // 0a86: istore 16
      // 0a88: iload 16
      // 0a8a: ifge 0a92
      // 0a8d: goto 0f24
      // 0a92: aload 0
      // 0a93: getfield Lobby.pgames [I
      // 0a96: iload 16
      // 0a98: iaload
      // 0a99: bipush -1
      // 0a9a: if_icmpne 0aa2
      // 0a9d: goto 0f1c
      // 0aa2: bipush 0
      // 0aa3: istore 17
      // 0aa5: bipush 0
      // 0aa6: istore 18
      // 0aa8: iload 18
      // 0aaa: aload 0
      // 0aab: getfield Lobby.ngm I
      // 0aae: if_icmplt 0ab6
      // 0ab1: goto 0add
      // 0ab6: aload 0
      // 0ab7: getfield Lobby.pgames [I
      // 0aba: iload 16
      // 0abc: iaload
      // 0abd: aload 0
      // 0abe: getfield Lobby.gnum [I
      // 0ac1: iload 18
      // 0ac3: iaload
      // 0ac4: if_icmpeq 0acc
      // 0ac7: goto 0ad5
      // 0acc: iload 18
      // 0ace: istore 17
      // 0ad0: goto 0ad5
      // 0ad5: iinc 18 1
      // 0ad8: goto 0aa8
      // 0add: aload 0
      // 0ade: getfield Lobby.wait [I
      // 0ae1: iload 17
      // 0ae3: iaload
      // 0ae4: ifle 0aec
      // 0ae7: goto 0f17
      // 0aec: bipush 0
      // 0aed: istore 18
      // 0aef: bipush 0
      // 0af0: istore 19
      // 0af2: iload 19
      // 0af4: aload 0
      // 0af5: getfield Lobby.npo I
      // 0af8: if_icmplt 0b00
      // 0afb: goto 0b90
      // 0b00: iload 16
      // 0b02: iload 19
      // 0b04: if_icmpne 0b0c
      // 0b07: goto 0b88
      // 0b0c: aload 0
      // 0b0d: getfield Lobby.pnames [Ljava/lang/String;
      // 0b10: iload 16
      // 0b12: aaload
      // 0b13: aload 0
      // 0b14: getfield Lobby.pnames [Ljava/lang/String;
      // 0b17: iload 19
      // 0b19: aaload
      // 0b1a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0b1d: ifne 0b25
      // 0b20: goto 0b88
      // 0b25: aload 0
      // 0b26: getfield Lobby.pgames [I
      // 0b29: iload 19
      // 0b2b: iaload
      // 0b2c: bipush -1
      // 0b2d: if_icmpeq 0b35
      // 0b30: goto 0b3d
      // 0b35: bipush 1
      // 0b36: istore 18
      // 0b38: goto 0b88
      // 0b3d: bipush 0
      // 0b3e: istore 20
      // 0b40: iload 20
      // 0b42: aload 0
      // 0b43: getfield Lobby.ngm I
      // 0b46: if_icmplt 0b4e
      // 0b49: goto 0b83
      // 0b4e: aload 0
      // 0b4f: getfield Lobby.pgames [I
      // 0b52: iload 19
      // 0b54: iaload
      // 0b55: aload 0
      // 0b56: getfield Lobby.gnum [I
      // 0b59: iload 20
      // 0b5b: iaload
      // 0b5c: if_icmpeq 0b64
      // 0b5f: goto 0b7b
      // 0b64: aload 0
      // 0b65: getfield Lobby.wait [I
      // 0b68: iload 20
      // 0b6a: iaload
      // 0b6b: ifgt 0b73
      // 0b6e: goto 0b7b
      // 0b73: bipush 1
      // 0b74: istore 18
      // 0b76: goto 0b7b
      // 0b7b: iinc 20 1
      // 0b7e: goto 0b40
      // 0b83: goto 0b88
      // 0b88: iinc 19 1
      // 0b8b: goto 0af2
      // 0b90: iload 18
      // 0b92: ifeq 0b9a
      // 0b95: goto 0f12
      // 0b9a: bipush 82
      // 0b9c: bipush 30
      // 0b9e: iload 12
      // 0ba0: imul
      // 0ba1: iadd
      // 0ba2: iload 11
      // 0ba4: isub
      // 0ba5: bipush 50
      // 0ba7: if_icmpgt 0baf
      // 0baa: goto 0f0a
      // 0baf: bipush 82
      // 0bb1: bipush 30
      // 0bb3: iload 12
      // 0bb5: bipush 1
      // 0bb6: isub
      // 0bb7: imul
      // 0bb8: iadd
      // 0bb9: iload 11
      // 0bbb: isub
      // 0bbc: sipush 415
      // 0bbf: if_icmplt 0bc7
      // 0bc2: goto 0f0a
      // 0bc7: bipush 0
      // 0bc8: istore 19
      // 0bca: iload 1
      // 0bcb: bipush 70
      // 0bcd: if_icmpgt 0bd5
      // 0bd0: goto 0d5e
      // 0bd5: iload 1
      // 0bd6: sipush 185
      // 0bd9: if_icmplt 0be1
      // 0bdc: goto 0d5e
      // 0be1: iload 2
      // 0be2: bipush 52
      // 0be4: bipush 30
      // 0be6: iload 12
      // 0be8: imul
      // 0be9: iadd
      // 0bea: iload 11
      // 0bec: isub
      // 0bed: if_icmpgt 0bf5
      // 0bf0: goto 0d5e
      // 0bf5: iload 2
      // 0bf6: bipush 82
      // 0bf8: bipush 30
      // 0bfa: iload 12
      // 0bfc: imul
      // 0bfd: iadd
      // 0bfe: iload 11
      // 0c00: isub
      // 0c01: if_icmplt 0c09
      // 0c04: goto 0d5e
      // 0c09: aload 0
      // 0c0a: getfield Lobby.pgames [I
      // 0c0d: aload 0
      // 0c0e: getfield Lobby.im I
      // 0c11: iaload
      // 0c12: bipush -1
      // 0c13: if_icmpeq 0c1b
      // 0c16: goto 0d13
      // 0c1b: aload 0
      // 0c1c: getfield Lobby.join I
      // 0c1f: bipush -1
      // 0c20: if_icmpeq 0c28
      // 0c23: goto 0d13
      // 0c28: aload 0
      // 0c29: getfield Lobby.chalngd I
      // 0c2c: bipush -1
      // 0c2d: if_icmpge 0c35
      // 0c30: goto 0d13
      // 0c35: iload 3
      // 0c36: ifeq 0c3e
      // 0c39: goto 0c4c
      // 0c3e: aload 0
      // 0c3f: getfield Lobby.mousonp I
      // 0c42: iload 16
      // 0c44: if_icmpeq 0c4c
      // 0c47: goto 0cde
      // 0c4c: aload 0
      // 0c4d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0c50: aload 0
      // 0c51: sipush 255
      // 0c54: sipush 255
      // 0c57: sipush 255
      // 0c5a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0c5d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0c60: aload 0
      // 0c61: iload 16
      // 0c63: putfield Lobby.mousonp I
      // 0c66: bipush 52
      // 0c68: bipush 30
      // 0c6a: iload 12
      // 0c6c: imul
      // 0c6d: iadd
      // 0c6e: iload 11
      // 0c70: isub
      // 0c71: istore 14
      // 0c73: iload 3
      // 0c74: ifne 0c7c
      // 0c77: goto 0ca4
      // 0c7c: aload 0
      // 0c7d: getfield Lobby.cmonp I
      // 0c80: iload 16
      // 0c82: if_icmpeq 0c8a
      // 0c85: goto 0c9a
      // 0c8a: aload 0
      // 0c8b: aload 0
      // 0c8c: getfield Lobby.pgames [I
      // 0c8f: iload 16
      // 0c91: iaload
      // 0c92: putfield Lobby.ongame I
      // 0c95: goto 0c9a
      // 0c9a: aload 0
      // 0c9b: bipush -1
      // 0c9c: putfield Lobby.chalngd I
      // 0c9f: goto 0cf2
      // 0ca4: aload 0
      // 0ca5: getfield Lobby.cmonp I
      // 0ca8: bipush -1
      // 0ca9: if_icmpeq 0cb1
      // 0cac: goto 0cc1
      // 0cb1: aload 0
      // 0cb2: bipush -1
      // 0cb3: putfield Lobby.ongame I
      // 0cb6: aload 0
      // 0cb7: iload 16
      // 0cb9: putfield Lobby.cmonp I
      // 0cbc: goto 0cc1
      // 0cc1: aload 0
      // 0cc2: getfield Lobby.ongame I
      // 0cc5: aload 0
      // 0cc6: getfield Lobby.pgames [I
      // 0cc9: iload 16
      // 0ccb: iaload
      // 0ccc: if_icmpeq 0cd4
      // 0ccf: goto 0cf2
      // 0cd4: aload 0
      // 0cd5: bipush -1
      // 0cd6: putfield Lobby.mousonp I
      // 0cd9: goto 0cf2
      // 0cde: aload 0
      // 0cdf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0ce2: aload 0
      // 0ce3: sipush 220
      // 0ce6: sipush 220
      // 0ce9: sipush 220
      // 0cec: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0cef: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0cf2: aload 0
      // 0cf3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0cf6: bipush 70
      // 0cf8: bipush 53
      // 0cfa: bipush 30
      // 0cfc: iload 12
      // 0cfe: imul
      // 0cff: iadd
      // 0d00: iload 11
      // 0d02: isub
      // 0d03: bipush 116
      // 0d05: bipush 29
      // 0d07: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 0d0a: iload 16
      // 0d0c: istore 13
      // 0d0e: goto 0d13
      // 0d13: bipush 1
      // 0d14: istore 19
      // 0d16: aload 6
      // 0d18: getfield Control.handb Z
      // 0d1b: ifne 0d23
      // 0d1e: goto 0d5e
      // 0d23: aload 0
      // 0d24: getfield Lobby.gs LGameSparker;
      // 0d27: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 0d2a: new java/lang/StringBuilder
      // 0d2d: dup
      // 0d2e: invokespecial java/lang/StringBuilder.<init> ()V
      // 0d31: aload 0
      // 0d32: getfield Lobby.gs LGameSparker;
      // 0d35: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 0d38: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 0d3b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d3e: ldc ""
      // 0d40: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d43: aload 0
      // 0d44: getfield Lobby.pnames [Ljava/lang/String;
      // 0d47: iload 16
      // 0d49: aaload
      // 0d4a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 0d4d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 0d50: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 0d53: aload 6
      // 0d55: bipush 0
      // 0d56: putfield Control.handb Z
      // 0d59: goto 0d5e
      // 0d5e: aload 0
      // 0d5f: getfield Lobby.pgames [I
      // 0d62: aload 0
      // 0d63: getfield Lobby.im I
      // 0d66: iaload
      // 0d67: bipush -1
      // 0d68: if_icmpeq 0d70
      // 0d6b: goto 0dc6
      // 0d70: aload 0
      // 0d71: getfield Lobby.join I
      // 0d74: bipush -1
      // 0d75: if_icmpeq 0d7d
      // 0d78: goto 0dc6
      // 0d7d: aload 0
      // 0d7e: getfield Lobby.chalngd I
      // 0d81: bipush -1
      // 0d82: if_icmpge 0d8a
      // 0d85: goto 0dc6
      // 0d8a: aload 0
      // 0d8b: getfield Lobby.wait [I
      // 0d8e: iload 17
      // 0d90: iaload
      // 0d91: ifeq 0d99
      // 0d94: goto 0db1
      // 0d99: aload 0
      // 0d9a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0d9d: new java/awt/Color
      // 0da0: dup
      // 0da1: bipush 117
      // 0da3: bipush 67
      // 0da5: bipush 0
      // 0da6: invokespecial java/awt/Color.<init> (III)V
      // 0da9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0dac: goto 0dfd
      // 0db1: aload 0
      // 0db2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0db5: aload 0
      // 0db6: bipush 0
      // 0db7: bipush 28
      // 0db9: bipush 102
      // 0dbb: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0dbe: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0dc1: goto 0dfd
      // 0dc6: aload 0
      // 0dc7: getfield Lobby.wait [I
      // 0dca: iload 17
      // 0dcc: iaload
      // 0dcd: ifeq 0dd5
      // 0dd0: goto 0ded
      // 0dd5: aload 0
      // 0dd6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0dd9: new java/awt/Color
      // 0ddc: dup
      // 0ddd: bipush 82
      // 0ddf: bipush 47
      // 0de1: bipush 0
      // 0de2: invokespecial java/awt/Color.<init> (III)V
      // 0de5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0de8: goto 0dfd
      // 0ded: aload 0
      // 0dee: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0df1: aload 0
      // 0df2: bipush 0
      // 0df3: bipush 20
      // 0df5: bipush 71
      // 0df7: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0dfa: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0dfd: aload 0
      // 0dfe: getfield Lobby.gb LGlobe;
      // 0e01: aload 0
      // 0e02: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e05: aload 0
      // 0e06: getfield Lobby.pnames [Ljava/lang/String;
      // 0e09: iload 16
      // 0e0b: aaload
      // 0e0c: bipush 68
      // 0e0e: bipush 53
      // 0e10: bipush 30
      // 0e12: iload 12
      // 0e14: imul
      // 0e15: iadd
      // 0e16: iload 11
      // 0e18: isub
      // 0e19: iload 19
      // 0e1b: invokevirtual Globe.drawl (Ljava/awt/Graphics2D;Ljava/lang/String;IIZ)Z
      // 0e1e: istore 20
      // 0e20: iload 19
      // 0e22: ifne 0e2a
      // 0e25: goto 0e34
      // 0e2a: iload 20
      // 0e2c: ifeq 0e34
      // 0e2f: goto 0ecf
      // 0e34: aload 0
      // 0e35: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e38: new java/awt/Font
      // 0e3b: dup
      // 0e3c: ldc_w "Arial"
      // 0e3f: bipush 1
      // 0e40: bipush 12
      // 0e42: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e45: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0e48: aload 0
      // 0e49: aload 0
      // 0e4a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e4d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0e50: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0e53: aload 0
      // 0e54: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e57: aload 0
      // 0e58: getfield Lobby.pnames [Ljava/lang/String;
      // 0e5b: iload 16
      // 0e5d: aaload
      // 0e5e: bipush 127
      // 0e60: aload 0
      // 0e61: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0e64: aload 0
      // 0e65: getfield Lobby.pnames [Ljava/lang/String;
      // 0e68: iload 16
      // 0e6a: aaload
      // 0e6b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0e6e: bipush 2
      // 0e6f: idiv
      // 0e70: isub
      // 0e71: bipush 66
      // 0e73: bipush 30
      // 0e75: iload 12
      // 0e77: imul
      // 0e78: iadd
      // 0e79: iload 11
      // 0e7b: isub
      // 0e7c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0e7f: aload 0
      // 0e80: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e83: new java/awt/Font
      // 0e86: dup
      // 0e87: ldc_w "Arial"
      // 0e8a: bipush 0
      // 0e8b: bipush 10
      // 0e8d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0e90: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0e93: aload 0
      // 0e94: aload 0
      // 0e95: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0e98: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0e9b: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0e9e: aload 0
      // 0e9f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0ea2: aload 0
      // 0ea3: getfield Lobby.pcarnames [Ljava/lang/String;
      // 0ea6: iload 16
      // 0ea8: aaload
      // 0ea9: bipush 127
      // 0eab: aload 0
      // 0eac: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0eaf: aload 0
      // 0eb0: getfield Lobby.pcarnames [Ljava/lang/String;
      // 0eb3: iload 16
      // 0eb5: aaload
      // 0eb6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0eb9: bipush 2
      // 0eba: idiv
      // 0ebb: isub
      // 0ebc: bipush 78
      // 0ebe: bipush 30
      // 0ec0: iload 12
      // 0ec2: imul
      // 0ec3: iadd
      // 0ec4: iload 11
      // 0ec6: isub
      // 0ec7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0eca: goto 0ecf
      // 0ecf: aload 0
      // 0ed0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0ed3: aload 0
      // 0ed4: sipush 150
      // 0ed7: sipush 150
      // 0eda: sipush 150
      // 0edd: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0ee0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0ee3: aload 0
      // 0ee4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0ee7: bipush 70
      // 0ee9: bipush 82
      // 0eeb: bipush 30
      // 0eed: iload 12
      // 0eef: imul
      // 0ef0: iadd
      // 0ef1: iload 11
      // 0ef3: isub
      // 0ef4: sipush 185
      // 0ef7: bipush 82
      // 0ef9: bipush 30
      // 0efb: iload 12
      // 0efd: imul
      // 0efe: iadd
      // 0eff: iload 11
      // 0f01: isub
      // 0f02: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0f05: goto 0f0a
      // 0f0a: iinc 12 1
      // 0f0d: goto 0f12
      // 0f12: goto 0f17
      // 0f17: goto 0f1c
      // 0f1c: iinc 16 -1
      // 0f1f: goto 0a88
      // 0f24: goto 0f29
      // 0f29: goto 0f2e
      // 0f2e: aload 0
      // 0f2f: getfield Lobby.mousonp I
      // 0f32: iload 13
      // 0f34: if_icmpne 0f3c
      // 0f37: goto 0f4b
      // 0f3c: aload 0
      // 0f3d: bipush -1
      // 0f3e: putfield Lobby.mousonp I
      // 0f41: aload 0
      // 0f42: bipush -1
      // 0f43: putfield Lobby.cmonp I
      // 0f46: goto 0f4b
      // 0f4b: aload 0
      // 0f4c: getfield Lobby.npo I
      // 0f4f: ifeq 0f57
      // 0f52: goto 0fa7
      // 0f57: aload 0
      // 0f58: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0f5b: new java/awt/Color
      // 0f5e: dup
      // 0f5f: bipush 0
      // 0f60: bipush 0
      // 0f61: bipush 0
      // 0f62: invokespecial java/awt/Color.<init> (III)V
      // 0f65: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0f68: aload 0
      // 0f69: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0f6c: new java/awt/Font
      // 0f6f: dup
      // 0f70: ldc_w "Arial"
      // 0f73: bipush 1
      // 0f74: bipush 12
      // 0f76: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 0f79: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 0f7c: aload 0
      // 0f7d: aload 0
      // 0f7e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0f81: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 0f84: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0f87: aload 0
      // 0f88: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0f8b: ldc_w "|  Loading Players  |"
      // 0f8e: bipush 127
      // 0f90: aload 0
      // 0f91: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 0f94: ldc_w "|  Loading Players  |"
      // 0f97: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 0f9a: bipush 2
      // 0f9b: idiv
      // 0f9c: isub
      // 0f9d: bipush 95
      // 0f9f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 0fa2: goto 0fa7
      // 0fa7: aload 0
      // 0fa8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0fab: aload 0
      // 0fac: sipush 205
      // 0faf: sipush 205
      // 0fb2: sipush 205
      // 0fb5: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0fb8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0fbb: aload 0
      // 0fbc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0fbf: bipush 65
      // 0fc1: bipush 25
      // 0fc3: sipush 145
      // 0fc6: bipush 28
      // 0fc8: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 0fcb: aload 0
      // 0fcc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0fcf: aload 0
      // 0fd0: sipush 150
      // 0fd3: sipush 150
      // 0fd6: sipush 150
      // 0fd9: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 0fdc: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 0fdf: aload 0
      // 0fe0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0fe3: bipush 65
      // 0fe5: bipush 50
      // 0fe7: sipush 190
      // 0fea: bipush 50
      // 0fec: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 0fef: aload 0
      // 0ff0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 0ff3: aload 0
      // 0ff4: sipush 205
      // 0ff7: sipush 205
      // 0ffa: sipush 205
      // 0ffd: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1000: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1003: aload 0
      // 1004: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1007: bipush 65
      // 1009: sipush 413
      // 100c: sipush 145
      // 100f: bipush 12
      // 1011: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 1014: aload 0
      // 1015: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1018: aload 0
      // 1019: sipush 150
      // 101c: sipush 150
      // 101f: sipush 150
      // 1022: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1025: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1028: aload 0
      // 1029: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 102c: bipush 65
      // 102e: sipush 415
      // 1031: sipush 190
      // 1034: sipush 415
      // 1037: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 103a: aload 0
      // 103b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 103e: aload 0
      // 103f: sipush 205
      // 1042: sipush 205
      // 1045: sipush 205
      // 1048: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 104b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 104e: aload 0
      // 104f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1052: sipush 193
      // 1055: bipush 53
      // 1057: bipush 17
      // 1059: sipush 360
      // 105c: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 105f: aload 0
      // 1060: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1063: new java/awt/Color
      // 1066: dup
      // 1067: bipush 0
      // 1068: bipush 0
      // 1069: bipush 0
      // 106a: invokespecial java/awt/Color.<init> (III)V
      // 106d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1070: aload 0
      // 1071: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1074: sipush 211
      // 1077: bipush 25
      // 1079: sipush 211
      // 107c: sipush 425
      // 107f: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 1082: aload 0
      // 1083: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1086: aload 0
      // 1087: getfield Lobby.xt LxtGraphics;
      // 108a: getfield xtGraphics.roomp Ljava/awt/Image;
      // 108d: bipush 72
      // 108f: bipush 30
      // 1091: aconst_null
      // 1092: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 1095: pop
      // 1096: aload 0
      // 1097: getfield Lobby.mscro I
      // 109a: sipush 131
      // 109d: if_icmpne 10a5
      // 10a0: goto 10af
      // 10a5: iload 10
      // 10a7: ifeq 10af
      // 10aa: goto 10fb
      // 10af: iload 10
      // 10b1: ifeq 10b9
      // 10b4: goto 10d2
      // 10b9: aload 0
      // 10ba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 10bd: aload 0
      // 10be: sipush 205
      // 10c1: sipush 205
      // 10c4: sipush 205
      // 10c7: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 10ca: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 10cd: goto 10e6
      // 10d2: aload 0
      // 10d3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 10d6: aload 0
      // 10d7: sipush 215
      // 10da: sipush 215
      // 10dd: sipush 215
      // 10e0: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 10e3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 10e6: aload 0
      // 10e7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 10ea: sipush 193
      // 10ed: bipush 53
      // 10ef: bipush 17
      // 10f1: bipush 17
      // 10f3: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 10f6: goto 1120
      // 10fb: aload 0
      // 10fc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 10ff: aload 0
      // 1100: sipush 220
      // 1103: sipush 220
      // 1106: sipush 220
      // 1109: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 110c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 110f: aload 0
      // 1110: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1113: sipush 193
      // 1116: bipush 53
      // 1118: bipush 17
      // 111a: bipush 17
      // 111c: bipush 1
      // 111d: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 1120: iload 10
      // 1122: ifne 112a
      // 1125: goto 1144
      // 112a: aload 0
      // 112b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 112e: aload 0
      // 112f: getfield Lobby.xt LxtGraphics;
      // 1132: getfield xtGraphics.asu Ljava/awt/Image;
      // 1135: sipush 198
      // 1138: bipush 59
      // 113a: aconst_null
      // 113b: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 113e: pop
      // 113f: goto 1144
      // 1144: aload 0
      // 1145: getfield Lobby.mscro I
      // 1148: sipush 132
      // 114b: if_icmpne 1153
      // 114e: goto 115d
      // 1153: iload 10
      // 1155: ifeq 115d
      // 1158: goto 11aa
      // 115d: iload 10
      // 115f: ifeq 1167
      // 1162: goto 1180
      // 1167: aload 0
      // 1168: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 116b: aload 0
      // 116c: sipush 205
      // 116f: sipush 205
      // 1172: sipush 205
      // 1175: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1178: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 117b: goto 1194
      // 1180: aload 0
      // 1181: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1184: aload 0
      // 1185: sipush 215
      // 1188: sipush 215
      // 118b: sipush 215
      // 118e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1191: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1194: aload 0
      // 1195: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1198: sipush 193
      // 119b: sipush 396
      // 119e: bipush 17
      // 11a0: bipush 17
      // 11a2: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 11a5: goto 11d0
      // 11aa: aload 0
      // 11ab: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 11ae: aload 0
      // 11af: sipush 220
      // 11b2: sipush 220
      // 11b5: sipush 220
      // 11b8: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 11bb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 11be: aload 0
      // 11bf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 11c2: sipush 193
      // 11c5: sipush 396
      // 11c8: bipush 17
      // 11ca: bipush 17
      // 11cc: bipush 1
      // 11cd: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 11d0: iload 10
      // 11d2: ifne 11da
      // 11d5: goto 11f5
      // 11da: aload 0
      // 11db: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 11de: aload 0
      // 11df: getfield Lobby.xt LxtGraphics;
      // 11e2: getfield xtGraphics.asd Ljava/awt/Image;
      // 11e5: sipush 198
      // 11e8: sipush 403
      // 11eb: aconst_null
      // 11ec: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 11ef: pop
      // 11f0: goto 11f5
      // 11f5: iload 10
      // 11f7: ifne 11ff
      // 11fa: goto 1636
      // 11ff: aload 0
      // 1200: getfield Lobby.conon I
      // 1203: bipush 1
      // 1204: if_icmpeq 120c
      // 1207: goto 1636
      // 120c: aload 0
      // 120d: getfield Lobby.lspos I
      // 1210: aload 0
      // 1211: getfield Lobby.spos I
      // 1214: if_icmpne 121c
      // 1217: goto 124a
      // 121c: aload 0
      // 121d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1220: aload 0
      // 1221: sipush 215
      // 1224: sipush 215
      // 1227: sipush 215
      // 122a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 122d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1230: aload 0
      // 1231: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1234: sipush 193
      // 1237: bipush 70
      // 1239: aload 0
      // 123a: getfield Lobby.spos I
      // 123d: iadd
      // 123e: bipush 17
      // 1240: bipush 31
      // 1242: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 1245: goto 1288
      // 124a: aload 0
      // 124b: getfield Lobby.mscro I
      // 124e: sipush 131
      // 1251: if_icmpeq 1259
      // 1254: goto 1272
      // 1259: aload 0
      // 125a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 125d: aload 0
      // 125e: sipush 215
      // 1261: sipush 215
      // 1264: sipush 215
      // 1267: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 126a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 126d: goto 1272
      // 1272: aload 0
      // 1273: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1276: sipush 193
      // 1279: bipush 70
      // 127b: aload 0
      // 127c: getfield Lobby.spos I
      // 127f: iadd
      // 1280: bipush 17
      // 1282: bipush 31
      // 1284: bipush 1
      // 1285: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 1288: aload 0
      // 1289: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 128c: aload 0
      // 128d: sipush 150
      // 1290: sipush 150
      // 1293: sipush 150
      // 1296: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1299: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 129c: aload 0
      // 129d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 12a0: sipush 198
      // 12a3: bipush 83
      // 12a5: aload 0
      // 12a6: getfield Lobby.spos I
      // 12a9: iadd
      // 12aa: sipush 204
      // 12ad: bipush 83
      // 12af: aload 0
      // 12b0: getfield Lobby.spos I
      // 12b3: iadd
      // 12b4: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 12b7: aload 0
      // 12b8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 12bb: sipush 198
      // 12be: bipush 85
      // 12c0: aload 0
      // 12c1: getfield Lobby.spos I
      // 12c4: iadd
      // 12c5: sipush 204
      // 12c8: bipush 85
      // 12ca: aload 0
      // 12cb: getfield Lobby.spos I
      // 12ce: iadd
      // 12cf: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 12d2: aload 0
      // 12d3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 12d6: sipush 198
      // 12d9: bipush 87
      // 12db: aload 0
      // 12dc: getfield Lobby.spos I
      // 12df: iadd
      // 12e0: sipush 204
      // 12e3: bipush 87
      // 12e5: aload 0
      // 12e6: getfield Lobby.spos I
      // 12e9: iadd
      // 12ea: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 12ed: aload 0
      // 12ee: getfield Lobby.mscro I
      // 12f1: bipush 101
      // 12f3: if_icmpgt 12fb
      // 12f6: goto 1318
      // 12fb: aload 0
      // 12fc: getfield Lobby.lspos I
      // 12ff: aload 0
      // 1300: getfield Lobby.spos I
      // 1303: if_icmpne 130b
      // 1306: goto 1318
      // 130b: aload 0
      // 130c: aload 0
      // 130d: getfield Lobby.spos I
      // 1310: putfield Lobby.lspos I
      // 1313: goto 1318
      // 1318: iload 3
      // 1319: ifne 1321
      // 131c: goto 158d
      // 1321: aload 0
      // 1322: getfield Lobby.mscro I
      // 1325: bipush 125
      // 1327: if_icmpeq 132f
      // 132a: goto 1376
      // 132f: iload 1
      // 1330: sipush 193
      // 1333: if_icmpgt 133b
      // 1336: goto 1376
      // 133b: iload 1
      // 133c: sipush 210
      // 133f: if_icmplt 1347
      // 1342: goto 1376
      // 1347: iload 2
      // 1348: bipush 70
      // 134a: aload 0
      // 134b: getfield Lobby.spos I
      // 134e: iadd
      // 134f: if_icmpgt 1357
      // 1352: goto 1376
      // 1357: iload 2
      // 1358: aload 0
      // 1359: getfield Lobby.spos I
      // 135c: bipush 101
      // 135e: iadd
      // 135f: if_icmplt 1367
      // 1362: goto 1376
      // 1367: aload 0
      // 1368: iload 2
      // 1369: aload 0
      // 136a: getfield Lobby.spos I
      // 136d: isub
      // 136e: putfield Lobby.mscro I
      // 1371: goto 1376
      // 1376: aload 0
      // 1377: getfield Lobby.mscro I
      // 137a: bipush 125
      // 137c: if_icmpeq 1384
      // 137f: goto 13be
      // 1384: iload 1
      // 1385: sipush 191
      // 1388: if_icmpgt 1390
      // 138b: goto 13be
      // 1390: iload 1
      // 1391: sipush 212
      // 1394: if_icmplt 139c
      // 1397: goto 13be
      // 139c: iload 2
      // 139d: bipush 51
      // 139f: if_icmpgt 13a7
      // 13a2: goto 13be
      // 13a7: iload 2
      // 13a8: bipush 72
      // 13aa: if_icmplt 13b2
      // 13ad: goto 13be
      // 13b2: aload 0
      // 13b3: sipush 131
      // 13b6: putfield Lobby.mscro I
      // 13b9: goto 13be
      // 13be: aload 0
      // 13bf: getfield Lobby.mscro I
      // 13c2: bipush 125
      // 13c4: if_icmpeq 13cc
      // 13c7: goto 1408
      // 13cc: iload 1
      // 13cd: sipush 191
      // 13d0: if_icmpgt 13d8
      // 13d3: goto 1408
      // 13d8: iload 1
      // 13d9: sipush 212
      // 13dc: if_icmplt 13e4
      // 13df: goto 1408
      // 13e4: iload 2
      // 13e5: sipush 394
      // 13e8: if_icmpgt 13f0
      // 13eb: goto 1408
      // 13f0: iload 2
      // 13f1: sipush 415
      // 13f4: if_icmplt 13fc
      // 13f7: goto 1408
      // 13fc: aload 0
      // 13fd: sipush 132
      // 1400: putfield Lobby.mscro I
      // 1403: goto 1408
      // 1408: aload 0
      // 1409: getfield Lobby.mscro I
      // 140c: bipush 125
      // 140e: if_icmpeq 1416
      // 1411: goto 145a
      // 1416: iload 1
      // 1417: sipush 193
      // 141a: if_icmpgt 1422
      // 141d: goto 145a
      // 1422: iload 1
      // 1423: sipush 210
      // 1426: if_icmplt 142e
      // 1429: goto 145a
      // 142e: iload 2
      // 142f: bipush 70
      // 1431: if_icmpgt 1439
      // 1434: goto 145a
      // 1439: iload 2
      // 143a: sipush 396
      // 143d: if_icmplt 1445
      // 1440: goto 145a
      // 1445: aload 0
      // 1446: bipush 85
      // 1448: putfield Lobby.mscro I
      // 144b: aload 0
      // 144c: iload 2
      // 144d: aload 0
      // 144e: getfield Lobby.mscro I
      // 1451: isub
      // 1452: putfield Lobby.spos I
      // 1455: goto 145a
      // 145a: sipush 1350
      // 145d: iload 10
      // 145f: idiv
      // 1460: istore 15
      // 1462: iload 15
      // 1464: bipush 1
      // 1465: if_icmplt 146d
      // 1468: goto 1475
      // 146d: bipush 1
      // 146e: istore 15
      // 1470: goto 1475
      // 1475: aload 0
      // 1476: getfield Lobby.mscro I
      // 1479: sipush 131
      // 147c: if_icmpeq 1484
      // 147f: goto 14cd
      // 1484: aload 0
      // 1485: dup
      // 1486: getfield Lobby.spos I
      // 1489: iload 15
      // 148b: isub
      // 148c: putfield Lobby.spos I
      // 148f: aload 0
      // 1490: getfield Lobby.spos I
      // 1493: sipush 295
      // 1496: if_icmpgt 149e
      // 1499: goto 14aa
      // 149e: aload 0
      // 149f: sipush 295
      // 14a2: putfield Lobby.spos I
      // 14a5: goto 14aa
      // 14aa: aload 0
      // 14ab: getfield Lobby.spos I
      // 14ae: iflt 14b6
      // 14b1: goto 14c0
      // 14b6: aload 0
      // 14b7: bipush 0
      // 14b8: putfield Lobby.spos I
      // 14bb: goto 14c0
      // 14c0: aload 0
      // 14c1: aload 0
      // 14c2: getfield Lobby.spos I
      // 14c5: putfield Lobby.lspos I
      // 14c8: goto 14cd
      // 14cd: aload 0
      // 14ce: getfield Lobby.mscro I
      // 14d1: sipush 132
      // 14d4: if_icmpeq 14dc
      // 14d7: goto 1525
      // 14dc: aload 0
      // 14dd: dup
      // 14de: getfield Lobby.spos I
      // 14e1: iload 15
      // 14e3: iadd
      // 14e4: putfield Lobby.spos I
      // 14e7: aload 0
      // 14e8: getfield Lobby.spos I
      // 14eb: sipush 295
      // 14ee: if_icmpgt 14f6
      // 14f1: goto 1502
      // 14f6: aload 0
      // 14f7: sipush 295
      // 14fa: putfield Lobby.spos I
      // 14fd: goto 1502
      // 1502: aload 0
      // 1503: getfield Lobby.spos I
      // 1506: iflt 150e
      // 1509: goto 1518
      // 150e: aload 0
      // 150f: bipush 0
      // 1510: putfield Lobby.spos I
      // 1513: goto 1518
      // 1518: aload 0
      // 1519: aload 0
      // 151a: getfield Lobby.spos I
      // 151d: putfield Lobby.lspos I
      // 1520: goto 1525
      // 1525: aload 0
      // 1526: getfield Lobby.mscro I
      // 1529: bipush 101
      // 152b: if_icmple 1533
      // 152e: goto 156e
      // 1533: aload 0
      // 1534: iload 2
      // 1535: aload 0
      // 1536: getfield Lobby.mscro I
      // 1539: isub
      // 153a: putfield Lobby.spos I
      // 153d: aload 0
      // 153e: getfield Lobby.spos I
      // 1541: sipush 295
      // 1544: if_icmpgt 154c
      // 1547: goto 1558
      // 154c: aload 0
      // 154d: sipush 295
      // 1550: putfield Lobby.spos I
      // 1553: goto 1558
      // 1558: aload 0
      // 1559: getfield Lobby.spos I
      // 155c: iflt 1564
      // 155f: goto 156e
      // 1564: aload 0
      // 1565: bipush 0
      // 1566: putfield Lobby.spos I
      // 1569: goto 156e
      // 156e: aload 0
      // 156f: getfield Lobby.mscro I
      // 1572: bipush 125
      // 1574: if_icmpeq 157c
      // 1577: goto 1588
      // 157c: aload 0
      // 157d: sipush 225
      // 1580: putfield Lobby.mscro I
      // 1583: goto 1588
      // 1588: goto 15a6
      // 158d: aload 0
      // 158e: getfield Lobby.mscro I
      // 1591: bipush 125
      // 1593: if_icmpne 159b
      // 1596: goto 15a6
      // 159b: aload 0
      // 159c: bipush 125
      // 159e: putfield Lobby.mscro I
      // 15a1: goto 15a6
      // 15a6: iload 4
      // 15a8: ifne 15b0
      // 15ab: goto 1636
      // 15b0: iload 1
      // 15b1: bipush 65
      // 15b3: if_icmpgt 15bb
      // 15b6: goto 1636
      // 15bb: iload 1
      // 15bc: sipush 170
      // 15bf: if_icmplt 15c7
      // 15c2: goto 1636
      // 15c7: iload 2
      // 15c8: bipush 93
      // 15ca: if_icmpgt 15d2
      // 15cd: goto 1636
      // 15d2: iload 2
      // 15d3: sipush 413
      // 15d6: if_icmplt 15de
      // 15d9: goto 1636
      // 15de: aload 0
      // 15df: dup
      // 15e0: getfield Lobby.spos I
      // 15e3: iload 4
      // 15e5: isub
      // 15e6: putfield Lobby.spos I
      // 15e9: aload 0
      // 15ea: bipush 1
      // 15eb: putfield Lobby.zeromsw Z
      // 15ee: aload 0
      // 15ef: getfield Lobby.spos I
      // 15f2: sipush 295
      // 15f5: if_icmpgt 15fd
      // 15f8: goto 160e
      // 15fd: aload 0
      // 15fe: sipush 295
      // 1601: putfield Lobby.spos I
      // 1604: aload 0
      // 1605: bipush 0
      // 1606: putfield Lobby.zeromsw Z
      // 1609: goto 160e
      // 160e: aload 0
      // 160f: getfield Lobby.spos I
      // 1612: iflt 161a
      // 1615: goto 1629
      // 161a: aload 0
      // 161b: bipush 0
      // 161c: putfield Lobby.spos I
      // 161f: aload 0
      // 1620: bipush 0
      // 1621: putfield Lobby.zeromsw Z
      // 1624: goto 1629
      // 1629: aload 0
      // 162a: aload 0
      // 162b: getfield Lobby.spos I
      // 162e: putfield Lobby.lspos I
      // 1631: goto 1636
      // 1636: aload 0
      // 1637: getfield Lobby.ongame I
      // 163a: bipush -1
      // 163b: if_icmpeq 1643
      // 163e: goto 6f78
      // 1643: aload 0
      // 1644: getfield Lobby.opengame I
      // 1647: bipush 2
      // 1648: if_icmpge 1650
      // 164b: goto 18fb
      // 1650: aload 0
      // 1651: getfield Lobby.opengame I
      // 1654: bipush 27
      // 1656: if_icmpge 165e
      // 1659: goto 1669
      // 165e: aload 0
      // 165f: bipush 26
      // 1661: putfield Lobby.opengame I
      // 1664: goto 1669
      // 1669: sipush 229
      // 166c: aload 0
      // 166d: getfield Lobby.opengame I
      // 1670: iadd
      // 1671: istore 15
      // 1673: iload 15
      // 1675: sipush 255
      // 1678: if_icmpgt 1680
      // 167b: goto 168a
      // 1680: sipush 255
      // 1683: istore 15
      // 1685: goto 168a
      // 168a: iload 15
      // 168c: iflt 1694
      // 168f: goto 169c
      // 1694: bipush 0
      // 1695: istore 15
      // 1697: goto 169c
      // 169c: aload 0
      // 169d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 16a0: aload 0
      // 16a1: iload 15
      // 16a3: iload 15
      // 16a5: iload 15
      // 16a7: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 16aa: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 16ad: aload 0
      // 16ae: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 16b1: sipush 225
      // 16b4: bipush 59
      // 16b6: aload 0
      // 16b7: getfield Lobby.opengame I
      // 16ba: i2f
      // 16bb: ldc_w 2.23
      // 16be: fmul
      // 16bf: f2i
      // 16c0: isub
      // 16c1: sipush 495
      // 16c4: sipush 200
      // 16c7: aload 0
      // 16c8: getfield Lobby.opengame I
      // 16cb: bipush 8
      // 16cd: imul
      // 16ce: iadd
      // 16cf: bipush 20
      // 16d1: bipush 20
      // 16d3: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 16d6: aload 0
      // 16d7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 16da: new java/awt/Color
      // 16dd: dup
      // 16de: bipush 0
      // 16df: bipush 0
      // 16e0: bipush 0
      // 16e1: invokespecial java/awt/Color.<init> (III)V
      // 16e4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 16e7: aload 0
      // 16e8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 16eb: sipush 225
      // 16ee: bipush 59
      // 16f0: aload 0
      // 16f1: getfield Lobby.opengame I
      // 16f4: i2f
      // 16f5: ldc_w 2.23
      // 16f8: fmul
      // 16f9: f2i
      // 16fa: isub
      // 16fb: sipush 495
      // 16fe: sipush 200
      // 1701: aload 0
      // 1702: getfield Lobby.opengame I
      // 1705: bipush 8
      // 1707: imul
      // 1708: iadd
      // 1709: bipush 20
      // 170b: bipush 20
      // 170d: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 1710: aload 0
      // 1711: getfield Lobby.xt LxtGraphics;
      // 1714: getfield xtGraphics.lan Z
      // 1717: ifeq 171f
      // 171a: goto 1787
      // 171f: aload 0
      // 1720: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1723: aload 0
      // 1724: sipush 217
      // 1727: sipush 217
      // 172a: sipush 217
      // 172d: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1730: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1733: aload 0
      // 1734: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1737: sipush 225
      // 173a: sipush 263
      // 173d: aload 0
      // 173e: getfield Lobby.opengame I
      // 1741: bipush 7
      // 1743: imul
      // 1744: iadd
      // 1745: sipush 495
      // 1748: sipush 157
      // 174b: bipush 20
      // 174d: bipush 20
      // 174f: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 1752: aload 0
      // 1753: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1756: new java/awt/Color
      // 1759: dup
      // 175a: bipush 0
      // 175b: bipush 0
      // 175c: bipush 0
      // 175d: invokespecial java/awt/Color.<init> (III)V
      // 1760: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1763: aload 0
      // 1764: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1767: sipush 225
      // 176a: sipush 263
      // 176d: aload 0
      // 176e: getfield Lobby.opengame I
      // 1771: bipush 7
      // 1773: imul
      // 1774: iadd
      // 1775: sipush 495
      // 1778: sipush 157
      // 177b: bipush 20
      // 177d: bipush 20
      // 177f: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 1782: goto 1787
      // 1787: aload 0
      // 1788: bipush 0
      // 1789: putfield Lobby.btn I
      // 178c: aload 0
      // 178d: getfield Lobby.prevloaded I
      // 1790: bipush -1
      // 1791: if_icmpne 1799
      // 1794: goto 17a3
      // 1799: aload 0
      // 179a: bipush -1
      // 179b: putfield Lobby.prevloaded I
      // 179e: goto 17a3
      // 17a3: aload 0
      // 17a4: getfield Lobby.updatec I
      // 17a7: bipush -1
      // 17a8: if_icmpne 17b0
      // 17ab: goto 17ba
      // 17b0: aload 0
      // 17b1: bipush -1
      // 17b2: putfield Lobby.updatec I
      // 17b5: goto 17ba
      // 17ba: aload 0
      // 17bb: getfield Lobby.gs LGameSparker;
      // 17be: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 17c1: invokevirtual java/awt/TextField.isShowing ()Z
      // 17c4: ifne 17cc
      // 17c7: goto 17e2
      // 17cc: aload 0
      // 17cd: getfield Lobby.gs LGameSparker;
      // 17d0: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 17d3: invokevirtual java/awt/TextField.hide ()V
      // 17d6: aload 0
      // 17d7: getfield Lobby.gs LGameSparker;
      // 17da: invokevirtual GameSparker.requestFocus ()V
      // 17dd: goto 17e2
      // 17e2: aload 0
      // 17e3: dup
      // 17e4: getfield Lobby.opengame I
      // 17e7: bipush 2
      // 17e8: isub
      // 17e9: putfield Lobby.opengame I
      // 17ec: aload 0
      // 17ed: getfield Lobby.opengame I
      // 17f0: ifeq 17f8
      // 17f3: goto 1824
      // 17f8: aload 0
      // 17f9: getfield Lobby.longame I
      // 17fc: bipush -1
      // 17fd: if_icmpne 1805
      // 1800: goto 1824
      // 1805: aload 0
      // 1806: getfield Lobby.chalngd I
      // 1809: bipush -1
      // 180a: if_icmpeq 1812
      // 180d: goto 1824
      // 1812: aload 0
      // 1813: aload 0
      // 1814: getfield Lobby.longame I
      // 1817: putfield Lobby.ongame I
      // 181a: aload 0
      // 181b: bipush -1
      // 181c: putfield Lobby.longame I
      // 181f: goto 1824
      // 1824: aload 0
      // 1825: getfield Lobby.invo Z
      // 1828: ifne 1830
      // 182b: goto 183a
      // 1830: aload 0
      // 1831: bipush 0
      // 1832: putfield Lobby.invo Z
      // 1835: goto 183a
      // 183a: bipush 0
      // 183b: istore 16
      // 183d: iload 16
      // 183f: bipush 7
      // 1841: if_icmplt 1849
      // 1844: goto 1895
      // 1849: aload 0
      // 184a: getfield Lobby.invos [Ljava/lang/String;
      // 184d: iload 16
      // 184f: aaload
      // 1850: ldc ""
      // 1852: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1855: ifeq 185d
      // 1858: goto 186b
      // 185d: aload 0
      // 185e: getfield Lobby.invos [Ljava/lang/String;
      // 1861: iload 16
      // 1863: ldc ""
      // 1865: aastore
      // 1866: goto 186b
      // 186b: aload 0
      // 186c: getfield Lobby.dinvi [Ljava/lang/String;
      // 186f: iload 16
      // 1871: aaload
      // 1872: ldc ""
      // 1874: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1877: ifeq 187f
      // 187a: goto 188d
      // 187f: aload 0
      // 1880: getfield Lobby.dinvi [Ljava/lang/String;
      // 1883: iload 16
      // 1885: ldc ""
      // 1887: aastore
      // 1888: goto 188d
      // 188d: iinc 16 1
      // 1890: goto 183d
      // 1895: aload 0
      // 1896: getfield Lobby.fstart Z
      // 1899: ifne 18a1
      // 189c: goto 18ab
      // 18a1: aload 0
      // 18a2: bipush 0
      // 18a3: putfield Lobby.fstart Z
      // 18a6: goto 18ab
      // 18ab: bipush 0
      // 18ac: istore 16
      // 18ae: iload 16
      // 18b0: bipush 9
      // 18b2: if_icmplt 18ba
      // 18b5: goto 18df
      // 18ba: aload 0
      // 18bb: getfield Lobby.cac [I
      // 18be: iload 16
      // 18c0: iaload
      // 18c1: bipush -1
      // 18c2: if_icmpne 18ca
      // 18c5: goto 18d7
      // 18ca: aload 0
      // 18cb: getfield Lobby.cac [I
      // 18ce: iload 16
      // 18d0: bipush -1
      // 18d1: iastore
      // 18d2: goto 18d7
      // 18d7: iinc 16 1
      // 18da: goto 18ae
      // 18df: aload 0
      // 18e0: getfield Lobby.dispcar I
      // 18e3: bipush -1
      // 18e4: if_icmpne 18ec
      // 18e7: goto 18f6
      // 18ec: aload 0
      // 18ed: bipush -1
      // 18ee: putfield Lobby.dispcar I
      // 18f1: goto 18f6
      // 18f6: goto c6f5
      // 18fb: aload 0
      // 18fc: getfield Lobby.xt LxtGraphics;
      // 18ff: getfield xtGraphics.lan Z
      // 1902: ifeq 190a
      // 1905: goto 1a8d
      // 190a: aload 0
      // 190b: aload 0
      // 190c: getfield Lobby.xt LxtGraphics;
      // 190f: getfield xtGraphics.cgame Ljava/awt/Image;
      // 1912: sipush 292
      // 1915: bipush 42
      // 1917: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 191a: aload 0
      // 191b: aload 0
      // 191c: getfield Lobby.xt LxtGraphics;
      // 191f: getfield xtGraphics.ccar Ljava/awt/Image;
      // 1922: sipush 442
      // 1925: bipush 42
      // 1927: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 192a: aload 0
      // 192b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 192e: new java/awt/Font
      // 1931: dup
      // 1932: ldc_w "Arial"
      // 1935: bipush 1
      // 1936: bipush 13
      // 1938: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 193b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 193e: aload 0
      // 193f: aload 0
      // 1940: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1943: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1946: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1949: aload 0
      // 194a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 194d: aload 0
      // 194e: bipush 60
      // 1950: bipush 60
      // 1952: bipush 60
      // 1954: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1957: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 195a: aload 0
      // 195b: getfield Lobby.gs LGameSparker;
      // 195e: getfield GameSparker.rooms LSmenu;
      // 1961: invokevirtual Smenu.isShowing ()Z
      // 1964: ifeq 196c
      // 1967: goto 197b
      // 196c: aload 0
      // 196d: getfield Lobby.gs LGameSparker;
      // 1970: getfield GameSparker.rooms LSmenu;
      // 1973: invokevirtual Smenu.show ()V
      // 1976: goto 197b
      // 197b: aload 0
      // 197c: getfield Lobby.gs LGameSparker;
      // 197f: getfield GameSparker.rooms LSmenu;
      // 1982: sipush 580
      // 1985: aload 0
      // 1986: getfield Lobby.gs LGameSparker;
      // 1989: getfield GameSparker.rooms LSmenu;
      // 198c: getfield Smenu.w I
      // 198f: bipush 2
      // 1990: idiv
      // 1991: isub
      // 1992: bipush 29
      // 1994: invokevirtual Smenu.move (II)V
      // 1997: aload 0
      // 1998: getfield Lobby.gs LGameSparker;
      // 199b: getfield GameSparker.rooms LSmenu;
      // 199e: getfield Smenu.sel I
      // 19a1: ifne 19a9
      // 19a4: goto 1a60
      // 19a9: aload 0
      // 19aa: invokevirtual Lobby.stopallnow ()V
      // 19ad: aload 0
      // 19ae: getfield Lobby.gs LGameSparker;
      // 19b1: getfield GameSparker.rooms LSmenu;
      // 19b4: getfield Smenu.iroom [I
      // 19b7: aload 0
      // 19b8: getfield Lobby.gs LGameSparker;
      // 19bb: getfield GameSparker.rooms LSmenu;
      // 19be: getfield Smenu.sel I
      // 19c1: iaload
      // 19c2: istore 15
      // 19c4: iload 15
      // 19c6: sipush 1000
      // 19c9: if_icmplt 19d1
      // 19cc: goto 19f9
      // 19d1: iload 15
      // 19d3: bipush 1
      // 19d4: if_icmpge 19dc
      // 19d7: goto 1a4b
      // 19dc: iload 15
      // 19de: bipush 5
      // 19df: if_icmple 19e7
      // 19e2: goto 1a4b
      // 19e7: aload 0
      // 19e8: getfield Lobby.xt LxtGraphics;
      // 19eb: sipush 7070
      // 19ee: iload 15
      // 19f0: iadd
      // 19f1: putfield xtGraphics.servport I
      // 19f4: goto 1a4b
      // 19f9: wide iinc 15 -1000
      // 19ff: iload 15
      // 1a01: ifge 1a09
      // 1a04: goto 1a4b
      // 1a09: iload 15
      // 1a0b: aload 0
      // 1a0c: getfield Lobby.lg LLogin;
      // 1a0f: getfield Login.nservers I
      // 1a12: if_icmplt 1a1a
      // 1a15: goto 1a4b
      // 1a1a: aload 0
      // 1a1b: getfield Lobby.xt LxtGraphics;
      // 1a1e: sipush 7071
      // 1a21: putfield xtGraphics.servport I
      // 1a24: aload 0
      // 1a25: getfield Lobby.xt LxtGraphics;
      // 1a28: aload 0
      // 1a29: getfield Lobby.lg LLogin;
      // 1a2c: getfield Login.servers [Ljava/lang/String;
      // 1a2f: iload 15
      // 1a31: aaload
      // 1a32: putfield xtGraphics.server Ljava/lang/String;
      // 1a35: aload 0
      // 1a36: getfield Lobby.xt LxtGraphics;
      // 1a39: aload 0
      // 1a3a: getfield Lobby.lg LLogin;
      // 1a3d: getfield Login.snames [Ljava/lang/String;
      // 1a40: iload 15
      // 1a42: aaload
      // 1a43: putfield xtGraphics.servername Ljava/lang/String;
      // 1a46: goto 1a4b
      // 1a4b: aload 0
      // 1a4c: invokevirtual Lobby.inishlobby ()V
      // 1a4f: aload 0
      // 1a50: getfield Lobby.gs LGameSparker;
      // 1a53: getfield GameSparker.rooms LSmenu;
      // 1a56: bipush 20
      // 1a58: putfield Smenu.kmoused I
      // 1a5b: goto 1a60
      // 1a60: aload 0
      // 1a61: getfield Lobby.gs LGameSparker;
      // 1a64: getfield GameSparker.rooms LSmenu;
      // 1a67: getfield Smenu.kmoused I
      // 1a6a: ifne 1a72
      // 1a6d: goto 1dae
      // 1a72: bipush -1
      // 1a73: istore 1
      // 1a74: bipush -1
      // 1a75: istore 2
      // 1a76: bipush 0
      // 1a77: istore 3
      // 1a78: aload 0
      // 1a79: getfield Lobby.gs LGameSparker;
      // 1a7c: getfield GameSparker.rooms LSmenu;
      // 1a7f: dup
      // 1a80: getfield Smenu.kmoused I
      // 1a83: bipush 1
      // 1a84: isub
      // 1a85: putfield Smenu.kmoused I
      // 1a88: goto 1dae
      // 1a8d: aload 0
      // 1a8e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1a91: aload 0
      // 1a92: getfield Lobby.xt LxtGraphics;
      // 1a95: getfield xtGraphics.lanm Ljava/awt/Image;
      // 1a98: sipush 241
      // 1a9b: bipush 31
      // 1a9d: aconst_null
      // 1a9e: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 1aa1: pop
      // 1aa2: aload 0
      // 1aa3: getfield Lobby.npo I
      // 1aa6: bipush 1
      // 1aa7: if_icmple 1aaf
      // 1aaa: goto 1c4f
      // 1aaf: aload 0
      // 1ab0: aload 0
      // 1ab1: getfield Lobby.xt LxtGraphics;
      // 1ab4: getfield xtGraphics.cgame Ljava/awt/Image;
      // 1ab7: sipush 292
      // 1aba: sipush -1000
      // 1abd: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 1ac0: aload 0
      // 1ac1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1ac4: new java/awt/Color
      // 1ac7: dup
      // 1ac8: bipush 0
      // 1ac9: bipush 0
      // 1aca: bipush 0
      // 1acb: invokespecial java/awt/Color.<init> (III)V
      // 1ace: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1ad1: aload 0
      // 1ad2: getfield Lobby.ncnt I
      // 1ad5: ifeq 1add
      // 1ad8: goto 1af6
      // 1add: aload 0
      // 1ade: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1ae1: new java/awt/Color
      // 1ae4: dup
      // 1ae5: sipush 188
      // 1ae8: bipush 111
      // 1aea: bipush 0
      // 1aeb: invokespecial java/awt/Color.<init> (III)V
      // 1aee: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1af1: goto 1af6
      // 1af6: aload 0
      // 1af7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1afa: new java/awt/Font
      // 1afd: dup
      // 1afe: ldc_w "Arial"
      // 1b01: bipush 1
      // 1b02: bipush 13
      // 1b04: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1b07: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1b0a: aload 0
      // 1b0b: aload 0
      // 1b0c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1b0f: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1b12: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1b15: aload 0
      // 1b16: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1b19: aload 0
      // 1b1a: getfield Lobby.lmsg Ljava/lang/String;
      // 1b1d: sipush 472
      // 1b20: aload 0
      // 1b21: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1b24: aload 0
      // 1b25: getfield Lobby.lmsg Ljava/lang/String;
      // 1b28: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 1b2b: bipush 2
      // 1b2c: idiv
      // 1b2d: isub
      // 1b2e: sipush 295
      // 1b31: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1b34: aload 0
      // 1b35: getfield Lobby.lmsg Ljava/lang/String;
      // 1b38: ldc_w ". . . | Searching/Waiting for other LAN Players | . . ."
      // 1b3b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1b3e: ifne 1b46
      // 1b41: goto 1b62
      // 1b46: aload 0
      // 1b47: getfield Lobby.ncnt I
      // 1b4a: ifeq 1b52
      // 1b4d: goto 1b62
      // 1b52: aload 0
      // 1b53: ldc "| Searching/Waiting for other LAN Players |"
      // 1b55: putfield Lobby.lmsg Ljava/lang/String;
      // 1b58: aload 0
      // 1b59: bipush 5
      // 1b5a: putfield Lobby.ncnt I
      // 1b5d: goto 1b62
      // 1b62: aload 0
      // 1b63: getfield Lobby.lmsg Ljava/lang/String;
      // 1b66: ldc_w ". . | Searching/Waiting for other LAN Players | . ."
      // 1b69: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1b6c: ifne 1b74
      // 1b6f: goto 1b91
      // 1b74: aload 0
      // 1b75: getfield Lobby.ncnt I
      // 1b78: ifeq 1b80
      // 1b7b: goto 1b91
      // 1b80: aload 0
      // 1b81: ldc_w ". . . | Searching/Waiting for other LAN Players | . . ."
      // 1b84: putfield Lobby.lmsg Ljava/lang/String;
      // 1b87: aload 0
      // 1b88: bipush 5
      // 1b89: putfield Lobby.ncnt I
      // 1b8c: goto 1b91
      // 1b91: aload 0
      // 1b92: getfield Lobby.lmsg Ljava/lang/String;
      // 1b95: ldc_w ". | Searching/Waiting for other LAN Players | ."
      // 1b98: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1b9b: ifne 1ba3
      // 1b9e: goto 1bc0
      // 1ba3: aload 0
      // 1ba4: getfield Lobby.ncnt I
      // 1ba7: ifeq 1baf
      // 1baa: goto 1bc0
      // 1baf: aload 0
      // 1bb0: ldc_w ". . | Searching/Waiting for other LAN Players | . ."
      // 1bb3: putfield Lobby.lmsg Ljava/lang/String;
      // 1bb6: aload 0
      // 1bb7: bipush 5
      // 1bb8: putfield Lobby.ncnt I
      // 1bbb: goto 1bc0
      // 1bc0: aload 0
      // 1bc1: getfield Lobby.lmsg Ljava/lang/String;
      // 1bc4: ldc "| Searching/Waiting for other LAN Players |"
      // 1bc6: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1bc9: ifne 1bd1
      // 1bcc: goto 1bee
      // 1bd1: aload 0
      // 1bd2: getfield Lobby.ncnt I
      // 1bd5: ifeq 1bdd
      // 1bd8: goto 1bee
      // 1bdd: aload 0
      // 1bde: ldc_w ". | Searching/Waiting for other LAN Players | ."
      // 1be1: putfield Lobby.lmsg Ljava/lang/String;
      // 1be4: aload 0
      // 1be5: bipush 5
      // 1be6: putfield Lobby.ncnt I
      // 1be9: goto 1bee
      // 1bee: aload 0
      // 1bef: getfield Lobby.ncnt I
      // 1bf2: ifne 1bfa
      // 1bf5: goto 1c09
      // 1bfa: aload 0
      // 1bfb: dup
      // 1bfc: getfield Lobby.ncnt I
      // 1bff: bipush 1
      // 1c00: isub
      // 1c01: putfield Lobby.ncnt I
      // 1c04: goto 1c09
      // 1c09: aload 0
      // 1c0a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c0d: aload 0
      // 1c0e: bipush 70
      // 1c10: bipush 70
      // 1c12: bipush 70
      // 1c14: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1c17: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1c1a: aload 0
      // 1c1b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c1e: ldc_w "So far, you are the only player connected to this network!"
      // 1c21: sipush 225
      // 1c24: sipush 325
      // 1c27: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1c2a: aload 0
      // 1c2b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c2e: ldc_w "There needs to be at least one more player logged in here with your same"
      // 1c31: sipush 225
      // 1c34: sipush 345
      // 1c37: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1c3a: aload 0
      // 1c3b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c3e: ldc_w "internet connection..."
      // 1c41: sipush 225
      // 1c44: sipush 365
      // 1c47: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1c4a: goto 1d9d
      // 1c4f: aload 0
      // 1c50: getfield Lobby.lanlogged Z
      // 1c53: ifeq 1c5b
      // 1c56: goto 1d0f
      // 1c5b: aload 0
      // 1c5c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c5f: new java/awt/Color
      // 1c62: dup
      // 1c63: bipush 0
      // 1c64: bipush 0
      // 1c65: bipush 0
      // 1c66: invokespecial java/awt/Color.<init> (III)V
      // 1c69: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1c6c: aload 0
      // 1c6d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c70: new java/awt/Font
      // 1c73: dup
      // 1c74: ldc_w "Arial"
      // 1c77: bipush 1
      // 1c78: bipush 13
      // 1c7a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1c7d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1c80: aload 0
      // 1c81: aload 0
      // 1c82: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c85: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1c88: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1c8b: aload 0
      // 1c8c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1c8f: ldc_w "You have played the allowed 3 LAN games per day!"
      // 1c92: sipush 472
      // 1c95: aload 0
      // 1c96: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1c99: ldc_w "You have played the allowed 3 LAN games per day!"
      // 1c9c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 1c9f: bipush 2
      // 1ca0: idiv
      // 1ca1: isub
      // 1ca2: sipush 295
      // 1ca5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1ca8: aload 0
      // 1ca9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1cac: aload 0
      // 1cad: bipush 70
      // 1caf: bipush 70
      // 1cb1: bipush 70
      // 1cb3: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1cb6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1cb9: aload 0
      // 1cba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1cbd: ldc_w "There needs to be at least one of the LAN players in the lobby with a registered"
      // 1cc0: sipush 225
      // 1cc3: sipush 325
      // 1cc6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cc9: aload 0
      // 1cca: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1ccd: ldc_w "account to be able to play LAN unlimitedly..."
      // 1cd0: sipush 225
      // 1cd3: sipush 345
      // 1cd6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cd9: aload 0
      // 1cda: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1cdd: ldc_w "Just one registered user allows everyone in the LAN game to play unlimitedly!"
      // 1ce0: sipush 225
      // 1ce3: sipush 365
      // 1ce6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1ce9: aload 0
      // 1cea: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1ced: ldc_w "Please register now!"
      // 1cf0: sipush 225
      // 1cf3: sipush 385
      // 1cf6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1cf9: aload 0
      // 1cfa: aload 0
      // 1cfb: getfield Lobby.xt LxtGraphics;
      // 1cfe: getfield xtGraphics.register Ljava/awt/Image;
      // 1d01: sipush 472
      // 1d04: sipush 395
      // 1d07: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 1d0a: goto 1d9d
      // 1d0f: aload 0
      // 1d10: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1d13: aload 0
      // 1d14: bipush 90
      // 1d16: bipush 90
      // 1d18: bipush 90
      // 1d1a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1d1d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1d20: aload 0
      // 1d21: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1d24: new java/awt/Font
      // 1d27: dup
      // 1d28: ldc_w "Arial"
      // 1d2b: bipush 1
      // 1d2c: bipush 12
      // 1d2e: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 1d31: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 1d34: aload 0
      // 1d35: aload 0
      // 1d36: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1d39: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 1d3c: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1d3f: aload 0
      // 1d40: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1d43: new java/lang/StringBuilder
      // 1d46: dup
      // 1d47: invokespecial java/lang/StringBuilder.<init> ()V
      // 1d4a: ldc_w "[  "
      // 1d4d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1d50: iload 12
      // 1d52: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1d55: ldc_w " Players Connected  ]"
      // 1d58: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1d5b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1d5e: sipush 472
      // 1d61: aload 0
      // 1d62: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 1d65: new java/lang/StringBuilder
      // 1d68: dup
      // 1d69: invokespecial java/lang/StringBuilder.<init> ()V
      // 1d6c: ldc_w "[  "
      // 1d6f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1d72: iload 12
      // 1d74: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 1d77: ldc_w " Players Connected  ]"
      // 1d7a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 1d7d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 1d80: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 1d83: bipush 2
      // 1d84: idiv
      // 1d85: isub
      // 1d86: sipush 295
      // 1d89: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 1d8c: aload 0
      // 1d8d: aload 0
      // 1d8e: getfield Lobby.xt LxtGraphics;
      // 1d91: getfield xtGraphics.cgame Ljava/awt/Image;
      // 1d94: sipush 472
      // 1d97: sipush 325
      // 1d9a: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 1d9d: aload 0
      // 1d9e: aload 0
      // 1d9f: getfield Lobby.xt LxtGraphics;
      // 1da2: getfield xtGraphics.ccar Ljava/awt/Image;
      // 1da5: sipush 442
      // 1da8: sipush -1000
      // 1dab: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 1dae: aload 0
      // 1daf: aload 0
      // 1db0: getfield Lobby.xt LxtGraphics;
      // 1db3: getfield xtGraphics.exit Ljava/awt/Image;
      // 1db6: sipush 690
      // 1db9: bipush 42
      // 1dbb: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 1dbe: aload 6
      // 1dc0: getfield Control.enter Z
      // 1dc3: ifne 1dcb
      // 1dc6: goto 1f16
      // 1dcb: aload 0
      // 1dcc: getfield Lobby.gs LGameSparker;
      // 1dcf: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 1dd2: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 1dd5: ldc_w "Type here..."
      // 1dd8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1ddb: ifeq 1de3
      // 1dde: goto 1f16
      // 1de3: aload 0
      // 1de4: getfield Lobby.gs LGameSparker;
      // 1de7: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 1dea: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 1ded: ldc ""
      // 1def: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 1df2: ifeq 1dfa
      // 1df5: goto 1f16
      // 1dfa: aload 0
      // 1dfb: getfield Lobby.chalngd I
      // 1dfe: bipush -1
      // 1dff: if_icmpeq 1e07
      // 1e02: goto 1e13
      // 1e07: aload 0
      // 1e08: getfield Lobby.pessd [Z
      // 1e0b: bipush 2
      // 1e0c: bipush 1
      // 1e0d: bastore
      // 1e0e: goto 1e1a
      // 1e13: aload 0
      // 1e14: getfield Lobby.pessd [Z
      // 1e17: bipush 5
      // 1e18: bipush 1
      // 1e19: bastore
      // 1e1a: aload 6
      // 1e1c: bipush 0
      // 1e1d: putfield Control.enter Z
      // 1e20: aload 0
      // 1e21: getfield Lobby.gs LGameSparker;
      // 1e24: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 1e27: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 1e2a: bipush 124
      // 1e2c: bipush 58
      // 1e2e: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // 1e31: astore 15
      // 1e33: aload 15
      // 1e35: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 1e38: aload 0
      // 1e39: getfield Lobby.gs LGameSparker;
      // 1e3c: getfield GameSparker.tpass Ljava/awt/TextField;
      // 1e3f: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // 1e42: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 1e45: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 1e48: bipush -1
      // 1e49: if_icmpne 1e51
      // 1e4c: goto 1e5b
      // 1e51: ldc_w " "
      // 1e54: astore 15
      // 1e56: goto 1e5b
      // 1e5b: aload 0
      // 1e5c: getfield Lobby.xt LxtGraphics;
      // 1e5f: aload 15
      // 1e61: invokevirtual xtGraphics.msgcheck (Ljava/lang/String;)Z
      // 1e64: ifeq 1e6c
      // 1e67: goto 1ef8
      // 1e6c: aload 0
      // 1e6d: getfield Lobby.updatec I
      // 1e70: bipush -12
      // 1e72: if_icmpgt 1e7a
      // 1e75: goto 1ef8
      // 1e7a: bipush 0
      // 1e7b: istore 16
      // 1e7d: iload 16
      // 1e7f: bipush 6
      // 1e81: if_icmplt 1e89
      // 1e84: goto 1eb1
      // 1e89: aload 0
      // 1e8a: getfield Lobby.sentn [Ljava/lang/String;
      // 1e8d: iload 16
      // 1e8f: aload 0
      // 1e90: getfield Lobby.sentn [Ljava/lang/String;
      // 1e93: iload 16
      // 1e95: bipush 1
      // 1e96: iadd
      // 1e97: aaload
      // 1e98: aastore
      // 1e99: aload 0
      // 1e9a: getfield Lobby.cnames [Ljava/lang/String;
      // 1e9d: iload 16
      // 1e9f: aload 0
      // 1ea0: getfield Lobby.cnames [Ljava/lang/String;
      // 1ea3: iload 16
      // 1ea5: bipush 1
      // 1ea6: iadd
      // 1ea7: aaload
      // 1ea8: aastore
      // 1ea9: iinc 16 1
      // 1eac: goto 1e7d
      // 1eb1: aload 0
      // 1eb2: getfield Lobby.sentn [Ljava/lang/String;
      // 1eb5: bipush 6
      // 1eb7: aload 15
      // 1eb9: aastore
      // 1eba: aload 0
      // 1ebb: getfield Lobby.cnames [Ljava/lang/String;
      // 1ebe: bipush 6
      // 1ec0: aload 0
      // 1ec1: getfield Lobby.pnames [Ljava/lang/String;
      // 1ec4: aload 0
      // 1ec5: getfield Lobby.im I
      // 1ec8: aaload
      // 1ec9: aastore
      // 1eca: aload 0
      // 1ecb: getfield Lobby.updatec I
      // 1ece: bipush -11
      // 1ed0: if_icmpgt 1ed8
      // 1ed3: goto 1ee3
      // 1ed8: aload 0
      // 1ed9: bipush -11
      // 1edb: putfield Lobby.updatec I
      // 1ede: goto 1eed
      // 1ee3: aload 0
      // 1ee4: dup
      // 1ee5: getfield Lobby.updatec I
      // 1ee8: bipush 1
      // 1ee9: isub
      // 1eea: putfield Lobby.updatec I
      // 1eed: aload 0
      // 1eee: bipush 28
      // 1ef0: putfield Lobby.spos3 I
      // 1ef3: goto 1f05
      // 1ef8: aload 0
      // 1ef9: getfield Lobby.xt LxtGraphics;
      // 1efc: dup
      // 1efd: getfield xtGraphics.warning I
      // 1f00: bipush 1
      // 1f01: iadd
      // 1f02: putfield xtGraphics.warning I
      // 1f05: aload 0
      // 1f06: getfield Lobby.gs LGameSparker;
      // 1f09: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 1f0c: ldc ""
      // 1f0e: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // 1f11: goto 1f16
      // 1f16: aload 0
      // 1f17: getfield Lobby.chalngd I
      // 1f1a: bipush -1
      // 1f1b: if_icmpeq 1f23
      // 1f1e: goto 353a
      // 1f23: aload 0
      // 1f24: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1f27: aload 0
      // 1f28: sipush 230
      // 1f2b: sipush 230
      // 1f2e: sipush 230
      // 1f31: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 1f34: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1f37: aload 0
      // 1f38: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1f3b: sipush 225
      // 1f3e: bipush 59
      // 1f40: sipush 495
      // 1f43: sipush 200
      // 1f46: bipush 20
      // 1f48: bipush 20
      // 1f4a: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 1f4d: aload 0
      // 1f4e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1f51: new java/awt/Color
      // 1f54: dup
      // 1f55: bipush 0
      // 1f56: bipush 0
      // 1f57: bipush 0
      // 1f58: invokespecial java/awt/Color.<init> (III)V
      // 1f5b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 1f5e: aload 0
      // 1f5f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 1f62: sipush 225
      // 1f65: bipush 59
      // 1f67: sipush 495
      // 1f6a: sipush 200
      // 1f6d: bipush 20
      // 1f6f: bipush 20
      // 1f71: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 1f74: aload 0
      // 1f75: getfield Lobby.britchl I
      // 1f78: ifne 1f80
      // 1f7b: goto 1f8a
      // 1f80: aload 0
      // 1f81: bipush 0
      // 1f82: putfield Lobby.britchl I
      // 1f85: goto 1f8a
      // 1f8a: aload 0
      // 1f8b: getfield Lobby.ngm I
      // 1f8e: bipush 5
      // 1f8f: isub
      // 1f90: bipush 24
      // 1f92: imul
      // 1f93: istore 10
      // 1f95: iload 10
      // 1f97: iflt 1f9f
      // 1f9a: goto 1fa7
      // 1f9f: bipush 0
      // 1fa0: istore 10
      // 1fa2: goto 1fa7
      // 1fa7: aload 0
      // 1fa8: getfield Lobby.spos2 I
      // 1fab: i2f
      // 1fac: ldc_w 82.0
      // 1faf: fdiv
      // 1fb0: iload 10
      // 1fb2: i2f
      // 1fb3: fmul
      // 1fb4: fconst_2
      // 1fb5: fsub
      // 1fb6: f2i
      // 1fb7: istore 11
      // 1fb9: aload 0
      // 1fba: getfield Lobby.ngm I
      // 1fbd: newarray 10
      // 1fbf: astore 15
      // 1fc1: aload 0
      // 1fc2: getfield Lobby.ngm I
      // 1fc5: newarray 10
      // 1fc7: astore 16
      // 1fc9: bipush 0
      // 1fca: istore 17
      // 1fcc: iload 17
      // 1fce: aload 0
      // 1fcf: getfield Lobby.ngm I
      // 1fd2: if_icmplt 1fda
      // 1fd5: goto 1fe8
      // 1fda: aload 15
      // 1fdc: iload 17
      // 1fde: bipush 0
      // 1fdf: iastore
      // 1fe0: iinc 17 1
      // 1fe3: goto 1fcc
      // 1fe8: bipush 0
      // 1fe9: istore 17
      // 1feb: iload 17
      // 1fed: aload 0
      // 1fee: getfield Lobby.ngm I
      // 1ff1: if_icmplt 1ff9
      // 1ff4: goto 2100
      // 1ff9: iload 17
      // 1ffb: bipush 1
      // 1ffc: iadd
      // 1ffd: istore 18
      // 1fff: iload 18
      // 2001: aload 0
      // 2002: getfield Lobby.ngm I
      // 2005: if_icmplt 200d
      // 2008: goto 20ee
      // 200d: aload 0
      // 200e: getfield Lobby.wait [I
      // 2011: iload 17
      // 2013: iaload
      // 2014: aload 0
      // 2015: getfield Lobby.wait [I
      // 2018: iload 18
      // 201a: iaload
      // 201b: if_icmpne 2023
      // 201e: goto 20c3
      // 2023: aload 0
      // 2024: getfield Lobby.wait [I
      // 2027: iload 17
      // 2029: iaload
      // 202a: ifle 2032
      // 202d: goto 2073
      // 2032: aload 0
      // 2033: getfield Lobby.wait [I
      // 2036: iload 18
      // 2038: iaload
      // 2039: ifle 2041
      // 203c: goto 2073
      // 2041: aload 0
      // 2042: getfield Lobby.wait [I
      // 2045: iload 17
      // 2047: iaload
      // 2048: aload 0
      // 2049: getfield Lobby.wait [I
      // 204c: iload 18
      // 204e: iaload
      // 204f: if_icmplt 2057
      // 2052: goto 2065
      // 2057: aload 15
      // 2059: iload 17
      // 205b: dup2
      // 205c: iaload
      // 205d: bipush 1
      // 205e: iadd
      // 205f: iastore
      // 2060: goto 20e6
      // 2065: aload 15
      // 2067: iload 18
      // 2069: dup2
      // 206a: iaload
      // 206b: bipush 1
      // 206c: iadd
      // 206d: iastore
      // 206e: goto 20e6
      // 2073: aload 0
      // 2074: getfield Lobby.wait [I
      // 2077: iload 17
      // 2079: iaload
      // 207a: aload 0
      // 207b: getfield Lobby.wait [I
      // 207e: iload 18
      // 2080: iaload
      // 2081: if_icmple 2089
      // 2084: goto 2098
      // 2089: aload 0
      // 208a: getfield Lobby.wait [I
      // 208d: iload 17
      // 208f: iaload
      // 2090: ifle 2098
      // 2093: goto 20b5
      // 2098: aload 0
      // 2099: getfield Lobby.wait [I
      // 209c: iload 18
      // 209e: iaload
      // 209f: ifgt 20a7
      // 20a2: goto 20b5
      // 20a7: aload 15
      // 20a9: iload 17
      // 20ab: dup2
      // 20ac: iaload
      // 20ad: bipush 1
      // 20ae: iadd
      // 20af: iastore
      // 20b0: goto 20e6
      // 20b5: aload 15
      // 20b7: iload 18
      // 20b9: dup2
      // 20ba: iaload
      // 20bb: bipush 1
      // 20bc: iadd
      // 20bd: iastore
      // 20be: goto 20e6
      // 20c3: iload 18
      // 20c5: iload 17
      // 20c7: if_icmplt 20cf
      // 20ca: goto 20dd
      // 20cf: aload 15
      // 20d1: iload 17
      // 20d3: dup2
      // 20d4: iaload
      // 20d5: bipush 1
      // 20d6: iadd
      // 20d7: iastore
      // 20d8: goto 20e6
      // 20dd: aload 15
      // 20df: iload 18
      // 20e1: dup2
      // 20e2: iaload
      // 20e3: bipush 1
      // 20e4: iadd
      // 20e5: iastore
      // 20e6: iinc 18 1
      // 20e9: goto 1fff
      // 20ee: aload 16
      // 20f0: aload 15
      // 20f2: iload 17
      // 20f4: iaload
      // 20f5: iload 17
      // 20f7: iastore
      // 20f8: iinc 17 1
      // 20fb: goto 1feb
      // 2100: aload 6
      // 2102: getfield Control.down Z
      // 2105: ifne 210d
      // 2108: goto 219d
      // 210d: aload 0
      // 210e: dup
      // 210f: getfield Lobby.opselect I
      // 2112: bipush 1
      // 2113: iadd
      // 2114: putfield Lobby.opselect I
      // 2117: bipush 0
      // 2118: istore 17
      // 211a: bipush 80
      // 211c: bipush 24
      // 211e: aload 0
      // 211f: getfield Lobby.opselect I
      // 2122: imul
      // 2123: iadd
      // 2124: iload 11
      // 2126: isub
      // 2127: sipush 202
      // 212a: if_icmpgt 2132
      // 212d: goto 2192
      // 2132: iload 17
      // 2134: ifeq 213c
      // 2137: goto 2192
      // 213c: aload 0
      // 213d: dup
      // 213e: getfield Lobby.spos2 I
      // 2141: bipush 1
      // 2142: iadd
      // 2143: putfield Lobby.spos2 I
      // 2146: aload 0
      // 2147: getfield Lobby.spos2 I
      // 214a: bipush 82
      // 214c: if_icmpgt 2154
      // 214f: goto 2162
      // 2154: aload 0
      // 2155: bipush 82
      // 2157: putfield Lobby.spos2 I
      // 215a: bipush 1
      // 215b: istore 17
      // 215d: goto 2162
      // 2162: aload 0
      // 2163: getfield Lobby.spos2 I
      // 2166: iflt 216e
      // 2169: goto 217b
      // 216e: aload 0
      // 216f: bipush 0
      // 2170: putfield Lobby.spos2 I
      // 2173: bipush 1
      // 2174: istore 17
      // 2176: goto 217b
      // 217b: aload 0
      // 217c: getfield Lobby.spos2 I
      // 217f: i2f
      // 2180: ldc_w 82.0
      // 2183: fdiv
      // 2184: iload 10
      // 2186: i2f
      // 2187: fmul
      // 2188: fconst_2
      // 2189: fsub
      // 218a: f2i
      // 218b: istore 11
      // 218d: goto 211a
      // 2192: aload 6
      // 2194: bipush 0
      // 2195: putfield Control.down Z
      // 2198: goto 219d
      // 219d: aload 6
      // 219f: getfield Control.up Z
      // 21a2: ifne 21aa
      // 21a5: goto 2239
      // 21aa: aload 0
      // 21ab: dup
      // 21ac: getfield Lobby.opselect I
      // 21af: bipush 1
      // 21b0: isub
      // 21b1: putfield Lobby.opselect I
      // 21b4: bipush 0
      // 21b5: istore 17
      // 21b7: bipush 80
      // 21b9: bipush 24
      // 21bb: aload 0
      // 21bc: getfield Lobby.opselect I
      // 21bf: imul
      // 21c0: iadd
      // 21c1: iload 11
      // 21c3: isub
      // 21c4: bipush 80
      // 21c6: if_icmplt 21ce
      // 21c9: goto 222e
      // 21ce: iload 17
      // 21d0: ifeq 21d8
      // 21d3: goto 222e
      // 21d8: aload 0
      // 21d9: dup
      // 21da: getfield Lobby.spos2 I
      // 21dd: bipush 1
      // 21de: isub
      // 21df: putfield Lobby.spos2 I
      // 21e2: aload 0
      // 21e3: getfield Lobby.spos2 I
      // 21e6: bipush 82
      // 21e8: if_icmpgt 21f0
      // 21eb: goto 21fe
      // 21f0: aload 0
      // 21f1: bipush 82
      // 21f3: putfield Lobby.spos2 I
      // 21f6: bipush 1
      // 21f7: istore 17
      // 21f9: goto 21fe
      // 21fe: aload 0
      // 21ff: getfield Lobby.spos2 I
      // 2202: iflt 220a
      // 2205: goto 2217
      // 220a: aload 0
      // 220b: bipush 0
      // 220c: putfield Lobby.spos2 I
      // 220f: bipush 1
      // 2210: istore 17
      // 2212: goto 2217
      // 2217: aload 0
      // 2218: getfield Lobby.spos2 I
      // 221b: i2f
      // 221c: ldc_w 82.0
      // 221f: fdiv
      // 2220: iload 10
      // 2222: i2f
      // 2223: fmul
      // 2224: fconst_2
      // 2225: fsub
      // 2226: f2i
      // 2227: istore 11
      // 2229: goto 21b7
      // 222e: aload 6
      // 2230: bipush 0
      // 2231: putfield Control.up Z
      // 2234: goto 2239
      // 2239: bipush -1
      // 223a: istore 17
      // 223c: aload 0
      // 223d: getfield Lobby.mousonp I
      // 2240: bipush -1
      // 2241: if_icmpne 2249
      // 2244: goto 2372
      // 2249: bipush 0
      // 224a: istore 18
      // 224c: bipush 0
      // 224d: istore 19
      // 224f: iload 19
      // 2251: aload 0
      // 2252: getfield Lobby.ngm I
      // 2255: if_icmplt 225d
      // 2258: goto 2286
      // 225d: aload 0
      // 225e: getfield Lobby.pgames [I
      // 2261: aload 0
      // 2262: getfield Lobby.mousonp I
      // 2265: iaload
      // 2266: aload 0
      // 2267: getfield Lobby.gnum [I
      // 226a: iload 19
      // 226c: iaload
      // 226d: if_icmpeq 2275
      // 2270: goto 227e
      // 2275: iload 19
      // 2277: istore 18
      // 2279: goto 227e
      // 227e: iinc 19 1
      // 2281: goto 224f
      // 2286: bipush 91
      // 2288: bipush 24
      // 228a: aload 15
      // 228c: iload 18
      // 228e: iaload
      // 228f: imul
      // 2290: iadd
      // 2291: iload 11
      // 2293: isub
      // 2294: istore 17
      // 2296: bipush 80
      // 2298: bipush 24
      // 229a: aload 15
      // 229c: iload 18
      // 229e: iaload
      // 229f: imul
      // 22a0: iadd
      // 22a1: iload 11
      // 22a3: isub
      // 22a4: sipush 202
      // 22a7: if_icmpgt 22af
      // 22aa: goto 22dd
      // 22af: sipush 1000
      // 22b2: iload 10
      // 22b4: idiv
      // 22b5: istore 19
      // 22b7: iload 19
      // 22b9: bipush 1
      // 22ba: if_icmplt 22c2
      // 22bd: goto 22ca
      // 22c2: bipush 1
      // 22c3: istore 19
      // 22c5: goto 22ca
      // 22ca: aload 0
      // 22cb: dup
      // 22cc: getfield Lobby.spos2 I
      // 22cf: iload 19
      // 22d1: iadd
      // 22d2: putfield Lobby.spos2 I
      // 22d5: bipush -1
      // 22d6: istore 17
      // 22d8: goto 22dd
      // 22dd: bipush 80
      // 22df: bipush 24
      // 22e1: aload 15
      // 22e3: iload 18
      // 22e5: iaload
      // 22e6: imul
      // 22e7: iadd
      // 22e8: iload 11
      // 22ea: isub
      // 22eb: bipush 80
      // 22ed: if_icmplt 22f5
      // 22f0: goto 2323
      // 22f5: sipush 1000
      // 22f8: iload 10
      // 22fa: idiv
      // 22fb: istore 19
      // 22fd: iload 19
      // 22ff: bipush 1
      // 2300: if_icmplt 2308
      // 2303: goto 2310
      // 2308: bipush 1
      // 2309: istore 19
      // 230b: goto 2310
      // 2310: aload 0
      // 2311: dup
      // 2312: getfield Lobby.spos2 I
      // 2315: iload 19
      // 2317: isub
      // 2318: putfield Lobby.spos2 I
      // 231b: bipush -1
      // 231c: istore 17
      // 231e: goto 2323
      // 2323: aload 0
      // 2324: getfield Lobby.spos2 I
      // 2327: bipush 82
      // 2329: if_icmpgt 2331
      // 232c: goto 233c
      // 2331: aload 0
      // 2332: bipush 82
      // 2334: putfield Lobby.spos2 I
      // 2337: goto 233c
      // 233c: aload 0
      // 233d: getfield Lobby.spos2 I
      // 2340: iflt 2348
      // 2343: goto 2352
      // 2348: aload 0
      // 2349: bipush 0
      // 234a: putfield Lobby.spos2 I
      // 234d: goto 2352
      // 2352: aload 0
      // 2353: getfield Lobby.spos2 I
      // 2356: i2f
      // 2357: ldc_w 82.0
      // 235a: fdiv
      // 235b: iload 10
      // 235d: i2f
      // 235e: fmul
      // 235f: fconst_2
      // 2360: fsub
      // 2361: f2i
      // 2362: istore 11
      // 2364: aload 0
      // 2365: aload 15
      // 2367: iload 18
      // 2369: iaload
      // 236a: putfield Lobby.opselect I
      // 236d: goto 2372
      // 2372: aload 0
      // 2373: getfield Lobby.opselect I
      // 2376: bipush -1
      // 2377: if_icmple 237f
      // 237a: goto 2389
      // 237f: aload 0
      // 2380: bipush 0
      // 2381: putfield Lobby.opselect I
      // 2384: goto 2389
      // 2389: aload 0
      // 238a: getfield Lobby.opselect I
      // 238d: aload 0
      // 238e: getfield Lobby.ngm I
      // 2391: if_icmpge 2399
      // 2394: goto 23a8
      // 2399: aload 0
      // 239a: aload 0
      // 239b: getfield Lobby.ngm I
      // 239e: bipush 1
      // 239f: isub
      // 23a0: putfield Lobby.opselect I
      // 23a3: goto 23a8
      // 23a8: bipush 0
      // 23a9: istore 18
      // 23ab: bipush 0
      // 23ac: istore 19
      // 23ae: iload 19
      // 23b0: aload 0
      // 23b1: getfield Lobby.ngm I
      // 23b4: if_icmplt 23bc
      // 23b7: goto 2c8d
      // 23bc: bipush 80
      // 23be: bipush 24
      // 23c0: iload 19
      // 23c2: imul
      // 23c3: iadd
      // 23c4: iload 11
      // 23c6: isub
      // 23c7: sipush 224
      // 23ca: if_icmplt 23d2
      // 23cd: goto 2c2b
      // 23d2: bipush 80
      // 23d4: bipush 24
      // 23d6: iload 19
      // 23d8: imul
      // 23d9: iadd
      // 23da: iload 11
      // 23dc: isub
      // 23dd: bipush 56
      // 23df: if_icmpgt 23e7
      // 23e2: goto 2c2b
      // 23e7: aload 0
      // 23e8: getfield Lobby.opselect I
      // 23eb: iload 19
      // 23ed: if_icmpeq 23f5
      // 23f0: goto 243e
      // 23f5: bipush 80
      // 23f7: bipush 24
      // 23f9: iload 19
      // 23fb: imul
      // 23fc: iadd
      // 23fd: iload 11
      // 23ff: isub
      // 2400: sipush 224
      // 2403: if_icmpge 240b
      // 2406: goto 241a
      // 240b: aload 0
      // 240c: dup
      // 240d: getfield Lobby.opselect I
      // 2410: bipush 1
      // 2411: isub
      // 2412: putfield Lobby.opselect I
      // 2415: goto 241a
      // 241a: bipush 80
      // 241c: bipush 24
      // 241e: iload 19
      // 2420: imul
      // 2421: iadd
      // 2422: iload 11
      // 2424: isub
      // 2425: bipush 62
      // 2427: if_icmplt 242f
      // 242a: goto 243e
      // 242f: aload 0
      // 2430: dup
      // 2431: getfield Lobby.opselect I
      // 2434: bipush 1
      // 2435: iadd
      // 2436: putfield Lobby.opselect I
      // 2439: goto 243e
      // 243e: bipush 0
      // 243f: istore 20
      // 2441: bipush 0
      // 2442: istore 21
      // 2444: aload 0
      // 2445: getfield Lobby.gs LGameSparker;
      // 2448: getfield GameSparker.openm Z
      // 244b: ifeq 2453
      // 244e: goto 2688
      // 2453: iload 1
      // 2454: sipush 241
      // 2457: if_icmpgt 245f
      // 245a: goto 2634
      // 245f: iload 1
      // 2460: sipush 692
      // 2463: if_icmplt 246b
      // 2466: goto 2634
      // 246b: iload 2
      // 246c: bipush 92
      // 246e: bipush 24
      // 2470: iload 19
      // 2472: imul
      // 2473: iadd
      // 2474: iload 11
      // 2476: isub
      // 2477: if_icmpgt 247f
      // 247a: goto 2634
      // 247f: iload 2
      // 2480: bipush 110
      // 2482: bipush 24
      // 2484: iload 19
      // 2486: imul
      // 2487: iadd
      // 2488: iload 11
      // 248a: isub
      // 248b: if_icmplt 2493
      // 248e: goto 2634
      // 2493: aload 0
      // 2494: getfield Lobby.lxm I
      // 2497: iload 1
      // 2498: if_icmpeq 24a0
      // 249b: goto 24ad
      // 24a0: aload 0
      // 24a1: getfield Lobby.lym I
      // 24a4: iload 2
      // 24a5: if_icmpne 24ad
      // 24a8: goto 24b8
      // 24ad: aload 0
      // 24ae: iload 19
      // 24b0: putfield Lobby.opselect I
      // 24b3: goto 24b8
      // 24b8: bipush 1
      // 24b9: istore 20
      // 24bb: iload 3
      // 24bc: ifne 24c4
      // 24bf: goto 24df
      // 24c4: aload 0
      // 24c5: getfield Lobby.clicked I
      // 24c8: bipush -1
      // 24c9: if_icmpeq 24d1
      // 24cc: goto 2637
      // 24d1: aload 0
      // 24d2: aload 16
      // 24d4: iload 19
      // 24d6: iaload
      // 24d7: putfield Lobby.clicked I
      // 24da: goto 2637
      // 24df: aload 0
      // 24e0: getfield Lobby.clicked I
      // 24e3: aload 16
      // 24e5: iload 19
      // 24e7: iaload
      // 24e8: if_icmpeq 24f0
      // 24eb: goto 262c
      // 24f0: aload 0
      // 24f1: aload 0
      // 24f2: getfield Lobby.gnum [I
      // 24f5: aload 16
      // 24f7: iload 19
      // 24f9: iaload
      // 24fa: iaload
      // 24fb: putfield Lobby.ongame I
      // 24fe: aload 0
      // 24ff: bipush 0
      // 2500: putfield Lobby.opengame I
      // 2503: iload 1
      // 2504: sipush 641
      // 2507: if_icmpge 250f
      // 250a: goto 2622
      // 250f: iload 1
      // 2510: sipush 689
      // 2513: if_icmple 251b
      // 2516: goto 2622
      // 251b: iload 2
      // 251c: bipush 92
      // 251e: bipush 24
      // 2520: iload 19
      // 2522: imul
      // 2523: iadd
      // 2524: iload 11
      // 2526: isub
      // 2527: if_icmpgt 252f
      // 252a: goto 2622
      // 252f: iload 2
      // 2530: bipush 110
      // 2532: bipush 24
      // 2534: iload 19
      // 2536: imul
      // 2537: iadd
      // 2538: iload 11
      // 253a: isub
      // 253b: if_icmplt 2543
      // 253e: goto 2622
      // 2543: aload 0
      // 2544: getfield Lobby.wait [I
      // 2547: aload 16
      // 2549: iload 19
      // 254b: iaload
      // 254c: iaload
      // 254d: ifgt 2555
      // 2550: goto 2622
      // 2555: bipush 0
      // 2556: istore 22
      // 2558: aload 0
      // 2559: getfield Lobby.gwarb [I
      // 255c: aload 16
      // 255e: iload 19
      // 2560: iaload
      // 2561: iaload
      // 2562: ifeq 256a
      // 2565: goto 25a8
      // 256a: aload 0
      // 256b: getfield Lobby.gplyrs [Ljava/lang/String;
      // 256e: aload 16
      // 2570: iload 19
      // 2572: iaload
      // 2573: aaload
      // 2574: ldc ""
      // 2576: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2579: ifeq 2581
      // 257c: goto 25a0
      // 2581: aload 0
      // 2582: getfield Lobby.gplyrs [Ljava/lang/String;
      // 2585: aload 16
      // 2587: iload 19
      // 2589: iaload
      // 258a: aaload
      // 258b: aload 0
      // 258c: getfield Lobby.pnames [Ljava/lang/String;
      // 258f: aload 0
      // 2590: getfield Lobby.im I
      // 2593: aaload
      // 2594: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 2597: bipush -1
      // 2598: if_icmpne 25a0
      // 259b: goto 25f4
      // 25a0: bipush 1
      // 25a1: istore 22
      // 25a3: goto 25f4
      // 25a8: aload 0
      // 25a9: getfield Lobby.xt LxtGraphics;
      // 25ac: getfield xtGraphics.clan Ljava/lang/String;
      // 25af: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 25b2: aload 0
      // 25b3: getfield Lobby.gaclan [Ljava/lang/String;
      // 25b6: aload 16
      // 25b8: iload 19
      // 25ba: iaload
      // 25bb: aaload
      // 25bc: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 25bf: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 25c2: ifeq 25ca
      // 25c5: goto 25ec
      // 25ca: aload 0
      // 25cb: getfield Lobby.xt LxtGraphics;
      // 25ce: getfield xtGraphics.clan Ljava/lang/String;
      // 25d1: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 25d4: aload 0
      // 25d5: getfield Lobby.gvclan [Ljava/lang/String;
      // 25d8: aload 16
      // 25da: iload 19
      // 25dc: iaload
      // 25dd: aaload
      // 25de: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 25e1: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 25e4: ifne 25ec
      // 25e7: goto 25f4
      // 25ec: bipush 1
      // 25ed: istore 22
      // 25ef: goto 25f4
      // 25f4: iload 22
      // 25f6: ifne 25fe
      // 25f9: goto 261d
      // 25fe: aload 0
      // 25ff: aload 0
      // 2600: getfield Lobby.gnum [I
      // 2603: aload 16
      // 2605: iload 19
      // 2607: iaload
      // 2608: iaload
      // 2609: putfield Lobby.join I
      // 260c: aload 0
      // 260d: ldc_w "| Joining Game |"
      // 2610: putfield Lobby.msg Ljava/lang/String;
      // 2613: aload 0
      // 2614: bipush 0
      // 2615: putfield Lobby.spos I
      // 2618: goto 261d
      // 261d: goto 2622
      // 2622: aload 0
      // 2623: bipush -1
      // 2624: putfield Lobby.clicked I
      // 2627: goto 262c
      // 262c: iinc 18 1
      // 262f: goto 2637
      // 2634: iinc 18 1
      // 2637: iload 1
      // 2638: sipush 641
      // 263b: if_icmpge 2643
      // 263e: goto 2688
      // 2643: iload 1
      // 2644: sipush 689
      // 2647: if_icmple 264f
      // 264a: goto 2688
      // 264f: iload 2
      // 2650: bipush 92
      // 2652: bipush 24
      // 2654: iload 19
      // 2656: imul
      // 2657: iadd
      // 2658: iload 11
      // 265a: isub
      // 265b: if_icmpgt 2663
      // 265e: goto 2688
      // 2663: iload 2
      // 2664: bipush 110
      // 2666: bipush 24
      // 2668: iload 19
      // 266a: imul
      // 266b: iadd
      // 266c: iload 11
      // 266e: isub
      // 266f: if_icmplt 2677
      // 2672: goto 2688
      // 2677: iload 3
      // 2678: ifne 2680
      // 267b: goto 2688
      // 2680: bipush 1
      // 2681: istore 21
      // 2683: goto 2688
      // 2688: aload 0
      // 2689: getfield Lobby.opselect I
      // 268c: iload 19
      // 268e: if_icmpeq 2696
      // 2691: goto 2760
      // 2696: iload 20
      // 2698: ifne 26a0
      // 269b: goto 26a9
      // 26a0: iload 3
      // 26a1: ifeq 26a9
      // 26a4: goto 26b6
      // 26a9: aload 6
      // 26ab: getfield Control.enter Z
      // 26ae: ifne 26b6
      // 26b1: goto 26f5
      // 26b6: aload 0
      // 26b7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 26ba: aload 0
      // 26bb: sipush 200
      // 26be: sipush 200
      // 26c1: sipush 200
      // 26c4: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 26c7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 26ca: aload 6
      // 26cc: getfield Control.enter Z
      // 26cf: ifne 26d7
      // 26d2: goto 2709
      // 26d7: aload 0
      // 26d8: aload 0
      // 26d9: getfield Lobby.gnum [I
      // 26dc: aload 16
      // 26de: iload 19
      // 26e0: iaload
      // 26e1: iaload
      // 26e2: putfield Lobby.ongame I
      // 26e5: aload 0
      // 26e6: bipush 0
      // 26e7: putfield Lobby.opengame I
      // 26ea: aload 6
      // 26ec: bipush 0
      // 26ed: putfield Control.enter Z
      // 26f0: goto 2709
      // 26f5: aload 0
      // 26f6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 26f9: aload 0
      // 26fa: sipush 255
      // 26fd: sipush 255
      // 2700: sipush 255
      // 2703: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2706: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2709: aload 0
      // 270a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 270d: sipush 241
      // 2710: bipush 92
      // 2712: bipush 24
      // 2714: iload 19
      // 2716: imul
      // 2717: iadd
      // 2718: iload 11
      // 271a: isub
      // 271b: sipush 451
      // 271e: bipush 18
      // 2720: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2723: iload 20
      // 2725: ifne 272d
      // 2728: goto 2760
      // 272d: aload 0
      // 272e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2731: aload 0
      // 2732: sipush 150
      // 2735: sipush 150
      // 2738: sipush 150
      // 273b: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 273e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2741: aload 0
      // 2742: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2745: sipush 239
      // 2748: bipush 90
      // 274a: bipush 24
      // 274c: iload 19
      // 274e: imul
      // 274f: iadd
      // 2750: iload 11
      // 2752: isub
      // 2753: sipush 454
      // 2756: bipush 21
      // 2758: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 275b: goto 2760
      // 2760: aload 0
      // 2761: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2764: new java/awt/Color
      // 2767: dup
      // 2768: bipush 0
      // 2769: bipush 0
      // 276a: bipush 0
      // 276b: invokespecial java/awt/Color.<init> (III)V
      // 276e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2771: aload 0
      // 2772: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2775: new java/awt/Font
      // 2778: dup
      // 2779: ldc_w "Arial"
      // 277c: bipush 1
      // 277d: bipush 12
      // 277f: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 2782: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 2785: aload 0
      // 2786: aload 0
      // 2787: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 278a: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 278d: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2790: aload 0
      // 2791: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2794: aload 0
      // 2795: getfield Lobby.gstages [Ljava/lang/String;
      // 2798: aload 16
      // 279a: iload 19
      // 279c: iaload
      // 279d: aaload
      // 279e: sipush 382
      // 27a1: aload 0
      // 27a2: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 27a5: aload 0
      // 27a6: getfield Lobby.gstages [Ljava/lang/String;
      // 27a9: aload 16
      // 27ab: iload 19
      // 27ad: iaload
      // 27ae: aaload
      // 27af: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 27b2: bipush 2
      // 27b3: idiv
      // 27b4: isub
      // 27b5: bipush 105
      // 27b7: bipush 24
      // 27b9: iload 19
      // 27bb: imul
      // 27bc: iadd
      // 27bd: iload 11
      // 27bf: isub
      // 27c0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 27c3: aload 0
      // 27c4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 27c7: ldc "|"
      // 27c9: sipush 525
      // 27cc: bipush 105
      // 27ce: bipush 24
      // 27d0: iload 19
      // 27d2: imul
      // 27d3: iadd
      // 27d4: iload 11
      // 27d6: isub
      // 27d7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 27da: aload 0
      // 27db: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 27de: ldc "|"
      // 27e0: sipush 584
      // 27e3: bipush 105
      // 27e5: bipush 24
      // 27e7: iload 19
      // 27e9: imul
      // 27ea: iadd
      // 27eb: iload 11
      // 27ed: isub
      // 27ee: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 27f1: aload 0
      // 27f2: getfield Lobby.wait [I
      // 27f5: aload 16
      // 27f7: iload 19
      // 27f9: iaload
      // 27fa: iaload
      // 27fb: ifgt 2803
      // 27fe: goto 2a7b
      // 2803: aload 0
      // 2804: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2807: new java/lang/StringBuilder
      // 280a: dup
      // 280b: invokespecial java/lang/StringBuilder.<init> ()V
      // 280e: ldc ""
      // 2810: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2813: aload 0
      // 2814: getfield Lobby.npls [I
      // 2817: aload 16
      // 2819: iload 19
      // 281b: iaload
      // 281c: iaload
      // 281d: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2820: ldc_w " / "
      // 2823: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2826: aload 0
      // 2827: getfield Lobby.mnpls [I
      // 282a: aload 16
      // 282c: iload 19
      // 282e: iaload
      // 282f: iaload
      // 2830: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2833: ldc ""
      // 2835: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2838: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 283b: sipush 556
      // 283e: aload 0
      // 283f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2842: new java/lang/StringBuilder
      // 2845: dup
      // 2846: invokespecial java/lang/StringBuilder.<init> ()V
      // 2849: ldc ""
      // 284b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 284e: aload 0
      // 284f: getfield Lobby.npls [I
      // 2852: aload 16
      // 2854: iload 19
      // 2856: iaload
      // 2857: iaload
      // 2858: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 285b: ldc_w " / "
      // 285e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2861: aload 0
      // 2862: getfield Lobby.mnpls [I
      // 2865: aload 16
      // 2867: iload 19
      // 2869: iaload
      // 286a: iaload
      // 286b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 286e: ldc ""
      // 2870: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2873: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2876: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2879: bipush 2
      // 287a: idiv
      // 287b: isub
      // 287c: bipush 105
      // 287e: bipush 24
      // 2880: iload 19
      // 2882: imul
      // 2883: iadd
      // 2884: iload 11
      // 2886: isub
      // 2887: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 288a: aload 0
      // 288b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 288e: new java/awt/Font
      // 2891: dup
      // 2892: ldc_w "Arial"
      // 2895: bipush 0
      // 2896: bipush 12
      // 2898: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 289b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 289e: aload 0
      // 289f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 28a2: new java/awt/Color
      // 28a5: dup
      // 28a6: bipush 80
      // 28a8: sipush 128
      // 28ab: bipush 0
      // 28ac: invokespecial java/awt/Color.<init> (III)V
      // 28af: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 28b2: aload 0
      // 28b3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 28b6: ldc_w "Waiting"
      // 28b9: sipush 593
      // 28bc: bipush 105
      // 28be: bipush 24
      // 28c0: iload 19
      // 28c2: imul
      // 28c3: iadd
      // 28c4: iload 11
      // 28c6: isub
      // 28c7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 28ca: aload 0
      // 28cb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 28ce: new java/awt/Font
      // 28d1: dup
      // 28d2: ldc_w "Arial"
      // 28d5: bipush 1
      // 28d6: bipush 12
      // 28d8: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 28db: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 28de: aload 0
      // 28df: aload 0
      // 28e0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 28e3: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 28e6: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 28e9: iload 21
      // 28eb: ifeq 28f3
      // 28ee: goto 2940
      // 28f3: aload 0
      // 28f4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 28f7: aload 0
      // 28f8: sipush 230
      // 28fb: sipush 230
      // 28fe: sipush 230
      // 2901: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2904: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2907: aload 0
      // 2908: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 290b: sipush 641
      // 290e: bipush 92
      // 2910: bipush 24
      // 2912: iload 19
      // 2914: imul
      // 2915: iadd
      // 2916: iload 11
      // 2918: isub
      // 2919: bipush 48
      // 291b: bipush 18
      // 291d: bipush 1
      // 291e: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 2921: aload 0
      // 2922: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2925: sipush 642
      // 2928: bipush 93
      // 292a: bipush 24
      // 292c: iload 19
      // 292e: imul
      // 292f: iadd
      // 2930: iload 11
      // 2932: isub
      // 2933: bipush 46
      // 2935: bipush 16
      // 2937: bipush 1
      // 2938: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 293b: goto 296d
      // 2940: aload 0
      // 2941: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2944: aload 0
      // 2945: sipush 230
      // 2948: sipush 230
      // 294b: sipush 230
      // 294e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2951: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2954: aload 0
      // 2955: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2958: sipush 641
      // 295b: bipush 92
      // 295d: bipush 24
      // 295f: iload 19
      // 2961: imul
      // 2962: iadd
      // 2963: iload 11
      // 2965: isub
      // 2966: bipush 48
      // 2968: bipush 18
      // 296a: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 296d: aload 0
      // 296e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2971: new java/awt/Color
      // 2974: dup
      // 2975: bipush 0
      // 2976: bipush 0
      // 2977: bipush 0
      // 2978: invokespecial java/awt/Color.<init> (III)V
      // 297b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 297e: bipush 0
      // 297f: istore 22
      // 2981: aload 0
      // 2982: getfield Lobby.gwarb [I
      // 2985: aload 16
      // 2987: iload 19
      // 2989: iaload
      // 298a: iaload
      // 298b: ifeq 2993
      // 298e: goto 29d1
      // 2993: aload 0
      // 2994: getfield Lobby.gplyrs [Ljava/lang/String;
      // 2997: aload 16
      // 2999: iload 19
      // 299b: iaload
      // 299c: aaload
      // 299d: ldc ""
      // 299f: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 29a2: ifeq 29aa
      // 29a5: goto 29c9
      // 29aa: aload 0
      // 29ab: getfield Lobby.gplyrs [Ljava/lang/String;
      // 29ae: aload 16
      // 29b0: iload 19
      // 29b2: iaload
      // 29b3: aaload
      // 29b4: aload 0
      // 29b5: getfield Lobby.pnames [Ljava/lang/String;
      // 29b8: aload 0
      // 29b9: getfield Lobby.im I
      // 29bc: aaload
      // 29bd: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 29c0: bipush -1
      // 29c1: if_icmpne 29c9
      // 29c4: goto 2a1d
      // 29c9: bipush 1
      // 29ca: istore 22
      // 29cc: goto 2a1d
      // 29d1: aload 0
      // 29d2: getfield Lobby.xt LxtGraphics;
      // 29d5: getfield xtGraphics.clan Ljava/lang/String;
      // 29d8: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 29db: aload 0
      // 29dc: getfield Lobby.gaclan [Ljava/lang/String;
      // 29df: aload 16
      // 29e1: iload 19
      // 29e3: iaload
      // 29e4: aaload
      // 29e5: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 29e8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 29eb: ifeq 29f3
      // 29ee: goto 2a15
      // 29f3: aload 0
      // 29f4: getfield Lobby.xt LxtGraphics;
      // 29f7: getfield xtGraphics.clan Ljava/lang/String;
      // 29fa: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 29fd: aload 0
      // 29fe: getfield Lobby.gvclan [Ljava/lang/String;
      // 2a01: aload 16
      // 2a03: iload 19
      // 2a05: iaload
      // 2a06: aaload
      // 2a07: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 2a0a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 2a0d: ifne 2a15
      // 2a10: goto 2a1d
      // 2a15: bipush 1
      // 2a16: istore 22
      // 2a18: goto 2a1d
      // 2a1d: iload 22
      // 2a1f: ifne 2a27
      // 2a22: goto 2a51
      // 2a27: aload 0
      // 2a28: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2a2b: ldc_w "Join"
      // 2a2e: sipush 665
      // 2a31: aload 0
      // 2a32: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2a35: ldc_w "Join"
      // 2a38: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2a3b: bipush 2
      // 2a3c: idiv
      // 2a3d: isub
      // 2a3e: bipush 105
      // 2a40: bipush 24
      // 2a42: iload 19
      // 2a44: imul
      // 2a45: iadd
      // 2a46: iload 11
      // 2a48: isub
      // 2a49: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2a4c: goto 2a76
      // 2a51: aload 0
      // 2a52: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2a55: ldc_w "View"
      // 2a58: sipush 665
      // 2a5b: aload 0
      // 2a5c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2a5f: ldc_w "View"
      // 2a62: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2a65: bipush 2
      // 2a66: idiv
      // 2a67: isub
      // 2a68: bipush 105
      // 2a6a: bipush 24
      // 2a6c: iload 19
      // 2a6e: imul
      // 2a6f: iadd
      // 2a70: iload 11
      // 2a72: isub
      // 2a73: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2a76: goto 2c26
      // 2a7b: aload 0
      // 2a7c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2a7f: new java/lang/StringBuilder
      // 2a82: dup
      // 2a83: invokespecial java/lang/StringBuilder.<init> ()V
      // 2a86: ldc ""
      // 2a88: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a8b: aload 0
      // 2a8c: getfield Lobby.npls [I
      // 2a8f: aload 16
      // 2a91: iload 19
      // 2a93: iaload
      // 2a94: iaload
      // 2a95: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2a98: ldc ""
      // 2a9a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2a9d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2aa0: sipush 556
      // 2aa3: aload 0
      // 2aa4: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2aa7: new java/lang/StringBuilder
      // 2aaa: dup
      // 2aab: invokespecial java/lang/StringBuilder.<init> ()V
      // 2aae: ldc ""
      // 2ab0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2ab3: aload 0
      // 2ab4: getfield Lobby.npls [I
      // 2ab7: aload 16
      // 2ab9: iload 19
      // 2abb: iaload
      // 2abc: iaload
      // 2abd: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 2ac0: ldc ""
      // 2ac2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 2ac5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 2ac8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2acb: bipush 2
      // 2acc: idiv
      // 2acd: isub
      // 2ace: bipush 105
      // 2ad0: bipush 24
      // 2ad2: iload 19
      // 2ad4: imul
      // 2ad5: iadd
      // 2ad6: iload 11
      // 2ad8: isub
      // 2ad9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2adc: aload 0
      // 2add: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2ae0: new java/awt/Font
      // 2ae3: dup
      // 2ae4: ldc_w "Arial"
      // 2ae7: bipush 0
      // 2ae8: bipush 12
      // 2aea: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 2aed: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 2af0: aload 0
      // 2af1: aload 0
      // 2af2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2af5: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 2af8: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2afb: aload 0
      // 2afc: getfield Lobby.wait [I
      // 2aff: aload 16
      // 2b01: iload 19
      // 2b03: iaload
      // 2b04: iaload
      // 2b05: ifeq 2b0d
      // 2b08: goto 2b3e
      // 2b0d: aload 0
      // 2b0e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b11: new java/awt/Color
      // 2b14: dup
      // 2b15: sipush 128
      // 2b18: bipush 73
      // 2b1a: bipush 0
      // 2b1b: invokespecial java/awt/Color.<init> (III)V
      // 2b1e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2b21: aload 0
      // 2b22: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b25: ldc_w "Started"
      // 2b28: sipush 594
      // 2b2b: bipush 105
      // 2b2d: bipush 24
      // 2b2f: iload 19
      // 2b31: imul
      // 2b32: iadd
      // 2b33: iload 11
      // 2b35: isub
      // 2b36: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2b39: goto 2b67
      // 2b3e: aload 0
      // 2b3f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b42: aload 0
      // 2b43: bipush 100
      // 2b45: bipush 100
      // 2b47: bipush 100
      // 2b49: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2b4c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2b4f: aload 0
      // 2b50: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b53: ldc_w "Finished"
      // 2b56: sipush 590
      // 2b59: bipush 105
      // 2b5b: bipush 24
      // 2b5d: iload 19
      // 2b5f: imul
      // 2b60: iadd
      // 2b61: iload 11
      // 2b63: isub
      // 2b64: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2b67: aload 0
      // 2b68: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b6b: new java/awt/Font
      // 2b6e: dup
      // 2b6f: ldc_w "Arial"
      // 2b72: bipush 1
      // 2b73: bipush 12
      // 2b75: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 2b78: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 2b7b: aload 0
      // 2b7c: aload 0
      // 2b7d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b80: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 2b83: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2b86: iload 21
      // 2b88: ifeq 2b90
      // 2b8b: goto 2bc3
      // 2b90: aload 0
      // 2b91: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2b94: aload 0
      // 2b95: sipush 230
      // 2b98: sipush 230
      // 2b9b: sipush 230
      // 2b9e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2ba1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2ba4: aload 0
      // 2ba5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2ba8: sipush 641
      // 2bab: bipush 92
      // 2bad: bipush 24
      // 2baf: iload 19
      // 2bb1: imul
      // 2bb2: iadd
      // 2bb3: iload 11
      // 2bb5: isub
      // 2bb6: bipush 48
      // 2bb8: bipush 18
      // 2bba: bipush 1
      // 2bbb: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 2bbe: goto 2bf0
      // 2bc3: aload 0
      // 2bc4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2bc7: aload 0
      // 2bc8: sipush 230
      // 2bcb: sipush 230
      // 2bce: sipush 230
      // 2bd1: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2bd4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2bd7: aload 0
      // 2bd8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2bdb: sipush 641
      // 2bde: bipush 92
      // 2be0: bipush 24
      // 2be2: iload 19
      // 2be4: imul
      // 2be5: iadd
      // 2be6: iload 11
      // 2be8: isub
      // 2be9: bipush 48
      // 2beb: bipush 18
      // 2bed: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2bf0: aload 0
      // 2bf1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2bf4: new java/awt/Color
      // 2bf7: dup
      // 2bf8: bipush 0
      // 2bf9: bipush 0
      // 2bfa: bipush 0
      // 2bfb: invokespecial java/awt/Color.<init> (III)V
      // 2bfe: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2c01: aload 0
      // 2c02: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2c05: ldc_w "View"
      // 2c08: sipush 665
      // 2c0b: aload 0
      // 2c0c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2c0f: ldc_w "View"
      // 2c12: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2c15: bipush 2
      // 2c16: idiv
      // 2c17: isub
      // 2c18: bipush 105
      // 2c1a: bipush 24
      // 2c1c: iload 19
      // 2c1e: imul
      // 2c1f: iadd
      // 2c20: iload 11
      // 2c22: isub
      // 2c23: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2c26: goto 2c85
      // 2c2b: aload 0
      // 2c2c: getfield Lobby.opselect I
      // 2c2f: iload 19
      // 2c31: if_icmpeq 2c39
      // 2c34: goto 2c82
      // 2c39: bipush 80
      // 2c3b: bipush 24
      // 2c3d: iload 19
      // 2c3f: imul
      // 2c40: iadd
      // 2c41: iload 11
      // 2c43: isub
      // 2c44: sipush 224
      // 2c47: if_icmpge 2c4f
      // 2c4a: goto 2c5e
      // 2c4f: aload 0
      // 2c50: dup
      // 2c51: getfield Lobby.opselect I
      // 2c54: bipush 1
      // 2c55: isub
      // 2c56: putfield Lobby.opselect I
      // 2c59: goto 2c5e
      // 2c5e: bipush 80
      // 2c60: bipush 24
      // 2c62: iload 19
      // 2c64: imul
      // 2c65: iadd
      // 2c66: iload 11
      // 2c68: isub
      // 2c69: bipush 56
      // 2c6b: if_icmple 2c73
      // 2c6e: goto 2c82
      // 2c73: aload 0
      // 2c74: dup
      // 2c75: getfield Lobby.opselect I
      // 2c78: bipush 1
      // 2c79: iadd
      // 2c7a: putfield Lobby.opselect I
      // 2c7d: goto 2c82
      // 2c82: iinc 18 1
      // 2c85: iinc 19 1
      // 2c88: goto 23ae
      // 2c8d: iload 18
      // 2c8f: aload 0
      // 2c90: getfield Lobby.ngm I
      // 2c93: if_icmpeq 2c9b
      // 2c96: goto 2cb2
      // 2c9b: aload 0
      // 2c9c: getfield Lobby.clicked I
      // 2c9f: bipush -1
      // 2ca0: if_icmpne 2ca8
      // 2ca3: goto 2cb2
      // 2ca8: aload 0
      // 2ca9: bipush -1
      // 2caa: putfield Lobby.clicked I
      // 2cad: goto 2cb2
      // 2cb2: aload 0
      // 2cb3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2cb6: new java/awt/Color
      // 2cb9: dup
      // 2cba: bipush 0
      // 2cbb: bipush 0
      // 2cbc: bipush 0
      // 2cbd: invokespecial java/awt/Color.<init> (III)V
      // 2cc0: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2cc3: aload 0
      // 2cc4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2cc7: new java/awt/Font
      // 2cca: dup
      // 2ccb: ldc_w "Arial"
      // 2cce: bipush 1
      // 2ccf: bipush 12
      // 2cd1: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 2cd4: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 2cd7: aload 0
      // 2cd8: aload 0
      // 2cd9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2cdc: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 2cdf: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2ce2: aload 0
      // 2ce3: getfield Lobby.ngm I
      // 2ce6: ifeq 2cee
      // 2ce9: goto 2d4d
      // 2cee: aload 0
      // 2cef: getfield Lobby.lloaded Z
      // 2cf2: ifeq 2cfa
      // 2cf5: goto 2d1c
      // 2cfa: aload 0
      // 2cfb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2cfe: ldc_w "|  Loading Games  |"
      // 2d01: sipush 472
      // 2d04: aload 0
      // 2d05: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2d08: ldc_w "|  Loading Games  |"
      // 2d0b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2d0e: bipush 2
      // 2d0f: idiv
      // 2d10: isub
      // 2d11: sipush 165
      // 2d14: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2d17: goto 2d4d
      // 2d1c: aload 0
      // 2d1d: getfield Lobby.xt LxtGraphics;
      // 2d20: getfield xtGraphics.lan Z
      // 2d23: ifeq 2d2b
      // 2d26: goto 2d4d
      // 2d2b: aload 0
      // 2d2c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d2f: ldc_w "No Games Created"
      // 2d32: sipush 472
      // 2d35: aload 0
      // 2d36: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2d39: ldc_w "No Games Created"
      // 2d3c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2d3f: bipush 2
      // 2d40: idiv
      // 2d41: isub
      // 2d42: sipush 165
      // 2d45: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2d48: goto 2d4d
      // 2d4d: aload 0
      // 2d4e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d51: aload 0
      // 2d52: sipush 205
      // 2d55: sipush 205
      // 2d58: sipush 205
      // 2d5b: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2d5e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2d61: aload 0
      // 2d62: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d65: sipush 235
      // 2d68: bipush 65
      // 2d6a: sipush 480
      // 2d6d: bipush 25
      // 2d6f: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2d72: aload 0
      // 2d73: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d76: aload 0
      // 2d77: getfield Lobby.xt LxtGraphics;
      // 2d7a: getfield xtGraphics.games Ljava/awt/Image;
      // 2d7d: sipush 241
      // 2d80: bipush 69
      // 2d82: aconst_null
      // 2d83: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 2d86: pop
      // 2d87: aload 0
      // 2d88: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d8b: aload 0
      // 2d8c: bipush 70
      // 2d8e: bipush 70
      // 2d90: bipush 70
      // 2d92: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2d95: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2d98: aload 0
      // 2d99: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2d9c: ldc_w "Stage Name"
      // 2d9f: sipush 382
      // 2da2: aload 0
      // 2da3: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2da6: ldc_w "Stage Name"
      // 2da9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2dac: bipush 2
      // 2dad: idiv
      // 2dae: isub
      // 2daf: bipush 81
      // 2db1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2db4: aload 0
      // 2db5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2db8: ldc "|"
      // 2dba: sipush 525
      // 2dbd: bipush 81
      // 2dbf: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2dc2: aload 0
      // 2dc3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2dc6: ldc_w "Players"
      // 2dc9: sipush 556
      // 2dcc: aload 0
      // 2dcd: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2dd0: ldc_w "Players"
      // 2dd3: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2dd6: bipush 2
      // 2dd7: idiv
      // 2dd8: isub
      // 2dd9: bipush 81
      // 2ddb: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2dde: aload 0
      // 2ddf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2de2: ldc "|"
      // 2de4: sipush 584
      // 2de7: bipush 81
      // 2de9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2dec: aload 0
      // 2ded: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2df0: ldc_w "Status"
      // 2df3: sipush 641
      // 2df6: aload 0
      // 2df7: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 2dfa: ldc_w "Status"
      // 2dfd: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 2e00: bipush 2
      // 2e01: idiv
      // 2e02: isub
      // 2e03: bipush 81
      // 2e05: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 2e08: aload 0
      // 2e09: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e0c: aload 0
      // 2e0d: sipush 150
      // 2e10: sipush 150
      // 2e13: sipush 150
      // 2e16: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2e19: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2e1c: aload 0
      // 2e1d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e20: sipush 235
      // 2e23: bipush 87
      // 2e25: sipush 696
      // 2e28: bipush 87
      // 2e2a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 2e2d: aload 0
      // 2e2e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e31: aload 0
      // 2e32: sipush 205
      // 2e35: sipush 205
      // 2e38: sipush 205
      // 2e3b: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2e3e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2e41: aload 0
      // 2e42: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e45: sipush 235
      // 2e48: sipush 237
      // 2e4b: sipush 480
      // 2e4e: bipush 17
      // 2e50: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2e53: aload 0
      // 2e54: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e57: aload 0
      // 2e58: sipush 150
      // 2e5b: sipush 150
      // 2e5e: sipush 150
      // 2e61: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2e64: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2e67: aload 0
      // 2e68: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e6b: sipush 235
      // 2e6e: sipush 239
      // 2e71: sipush 696
      // 2e74: sipush 239
      // 2e77: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 2e7a: aload 0
      // 2e7b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e7e: aload 0
      // 2e7f: sipush 205
      // 2e82: sipush 205
      // 2e85: sipush 205
      // 2e88: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2e8b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2e8e: aload 0
      // 2e8f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2e92: sipush 698
      // 2e95: bipush 107
      // 2e97: bipush 17
      // 2e99: bipush 113
      // 2e9b: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2e9e: aload 0
      // 2e9f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2ea2: aload 0
      // 2ea3: sipush 205
      // 2ea6: sipush 205
      // 2ea9: sipush 205
      // 2eac: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2eaf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2eb2: aload 0
      // 2eb3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2eb6: sipush 231
      // 2eb9: bipush 65
      // 2ebb: bipush 4
      // 2ebc: sipush 189
      // 2ebf: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2ec2: aload 0
      // 2ec3: getfield Lobby.mscro2 I
      // 2ec6: sipush 141
      // 2ec9: if_icmpne 2ed1
      // 2ecc: goto 2edb
      // 2ed1: iload 10
      // 2ed3: ifeq 2edb
      // 2ed6: goto 2f27
      // 2edb: iload 10
      // 2edd: ifeq 2ee5
      // 2ee0: goto 2efe
      // 2ee5: aload 0
      // 2ee6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2ee9: aload 0
      // 2eea: sipush 205
      // 2eed: sipush 205
      // 2ef0: sipush 205
      // 2ef3: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2ef6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2ef9: goto 2f12
      // 2efe: aload 0
      // 2eff: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f02: aload 0
      // 2f03: sipush 215
      // 2f06: sipush 215
      // 2f09: sipush 215
      // 2f0c: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2f0f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2f12: aload 0
      // 2f13: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f16: sipush 698
      // 2f19: bipush 90
      // 2f1b: bipush 17
      // 2f1d: bipush 17
      // 2f1f: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2f22: goto 2f4c
      // 2f27: aload 0
      // 2f28: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f2b: aload 0
      // 2f2c: sipush 220
      // 2f2f: sipush 220
      // 2f32: sipush 220
      // 2f35: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2f38: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2f3b: aload 0
      // 2f3c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f3f: sipush 698
      // 2f42: bipush 90
      // 2f44: bipush 17
      // 2f46: bipush 17
      // 2f48: bipush 1
      // 2f49: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 2f4c: iload 10
      // 2f4e: ifne 2f56
      // 2f51: goto 2f70
      // 2f56: aload 0
      // 2f57: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f5a: aload 0
      // 2f5b: getfield Lobby.xt LxtGraphics;
      // 2f5e: getfield xtGraphics.asu Ljava/awt/Image;
      // 2f61: sipush 703
      // 2f64: bipush 96
      // 2f66: aconst_null
      // 2f67: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 2f6a: pop
      // 2f6b: goto 2f70
      // 2f70: aload 0
      // 2f71: getfield Lobby.mscro2 I
      // 2f74: sipush 142
      // 2f77: if_icmpne 2f7f
      // 2f7a: goto 2f89
      // 2f7f: iload 10
      // 2f81: ifeq 2f89
      // 2f84: goto 2fd6
      // 2f89: iload 10
      // 2f8b: ifeq 2f93
      // 2f8e: goto 2fac
      // 2f93: aload 0
      // 2f94: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2f97: aload 0
      // 2f98: sipush 205
      // 2f9b: sipush 205
      // 2f9e: sipush 205
      // 2fa1: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2fa4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2fa7: goto 2fc0
      // 2fac: aload 0
      // 2fad: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2fb0: aload 0
      // 2fb1: sipush 215
      // 2fb4: sipush 215
      // 2fb7: sipush 215
      // 2fba: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2fbd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2fc0: aload 0
      // 2fc1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2fc4: sipush 698
      // 2fc7: sipush 220
      // 2fca: bipush 17
      // 2fcc: bipush 17
      // 2fce: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 2fd1: goto 2ffc
      // 2fd6: aload 0
      // 2fd7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2fda: aload 0
      // 2fdb: sipush 220
      // 2fde: sipush 220
      // 2fe1: sipush 220
      // 2fe4: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 2fe7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 2fea: aload 0
      // 2feb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 2fee: sipush 698
      // 2ff1: sipush 220
      // 2ff4: bipush 17
      // 2ff6: bipush 17
      // 2ff8: bipush 1
      // 2ff9: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 2ffc: iload 10
      // 2ffe: ifne 3006
      // 3001: goto 3021
      // 3006: aload 0
      // 3007: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 300a: aload 0
      // 300b: getfield Lobby.xt LxtGraphics;
      // 300e: getfield xtGraphics.asd Ljava/awt/Image;
      // 3011: sipush 703
      // 3014: sipush 226
      // 3017: aconst_null
      // 3018: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 301b: pop
      // 301c: goto 3021
      // 3021: iload 10
      // 3023: ifne 302b
      // 3026: goto 3458
      // 302b: aload 0
      // 302c: getfield Lobby.lspos2 I
      // 302f: aload 0
      // 3030: getfield Lobby.spos2 I
      // 3033: if_icmpne 303b
      // 3036: goto 3069
      // 303b: aload 0
      // 303c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 303f: aload 0
      // 3040: sipush 215
      // 3043: sipush 215
      // 3046: sipush 215
      // 3049: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 304c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 304f: aload 0
      // 3050: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3053: sipush 698
      // 3056: bipush 107
      // 3058: aload 0
      // 3059: getfield Lobby.spos2 I
      // 305c: iadd
      // 305d: bipush 17
      // 305f: bipush 31
      // 3061: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 3064: goto 30a7
      // 3069: aload 0
      // 306a: getfield Lobby.mscro2 I
      // 306d: sipush 141
      // 3070: if_icmpeq 3078
      // 3073: goto 3091
      // 3078: aload 0
      // 3079: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 307c: aload 0
      // 307d: sipush 215
      // 3080: sipush 215
      // 3083: sipush 215
      // 3086: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 3089: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 308c: goto 3091
      // 3091: aload 0
      // 3092: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3095: sipush 698
      // 3098: bipush 107
      // 309a: aload 0
      // 309b: getfield Lobby.spos2 I
      // 309e: iadd
      // 309f: bipush 17
      // 30a1: bipush 31
      // 30a3: bipush 1
      // 30a4: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 30a7: aload 0
      // 30a8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 30ab: aload 0
      // 30ac: sipush 150
      // 30af: sipush 150
      // 30b2: sipush 150
      // 30b5: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 30b8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 30bb: aload 0
      // 30bc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 30bf: sipush 703
      // 30c2: bipush 120
      // 30c4: aload 0
      // 30c5: getfield Lobby.spos2 I
      // 30c8: iadd
      // 30c9: sipush 709
      // 30cc: bipush 120
      // 30ce: aload 0
      // 30cf: getfield Lobby.spos2 I
      // 30d2: iadd
      // 30d3: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 30d6: aload 0
      // 30d7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 30da: sipush 703
      // 30dd: bipush 122
      // 30df: aload 0
      // 30e0: getfield Lobby.spos2 I
      // 30e3: iadd
      // 30e4: sipush 709
      // 30e7: bipush 122
      // 30e9: aload 0
      // 30ea: getfield Lobby.spos2 I
      // 30ed: iadd
      // 30ee: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 30f1: aload 0
      // 30f2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 30f5: sipush 703
      // 30f8: bipush 124
      // 30fa: aload 0
      // 30fb: getfield Lobby.spos2 I
      // 30fe: iadd
      // 30ff: sipush 709
      // 3102: bipush 124
      // 3104: aload 0
      // 3105: getfield Lobby.spos2 I
      // 3108: iadd
      // 3109: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 310c: aload 0
      // 310d: getfield Lobby.mscro2 I
      // 3110: sipush 138
      // 3113: if_icmpgt 311b
      // 3116: goto 3138
      // 311b: aload 0
      // 311c: getfield Lobby.lspos2 I
      // 311f: aload 0
      // 3120: getfield Lobby.spos2 I
      // 3123: if_icmpne 312b
      // 3126: goto 3138
      // 312b: aload 0
      // 312c: aload 0
      // 312d: getfield Lobby.spos2 I
      // 3130: putfield Lobby.lspos2 I
      // 3133: goto 3138
      // 3138: iload 3
      // 3139: ifne 3141
      // 313c: goto 33ae
      // 3141: aload 0
      // 3142: getfield Lobby.mscro2 I
      // 3145: sipush 145
      // 3148: if_icmpeq 3150
      // 314b: goto 3198
      // 3150: iload 1
      // 3151: sipush 698
      // 3154: if_icmpgt 315c
      // 3157: goto 3198
      // 315c: iload 1
      // 315d: sipush 715
      // 3160: if_icmplt 3168
      // 3163: goto 3198
      // 3168: iload 2
      // 3169: bipush 107
      // 316b: aload 0
      // 316c: getfield Lobby.spos2 I
      // 316f: iadd
      // 3170: if_icmpgt 3178
      // 3173: goto 3198
      // 3178: iload 2
      // 3179: aload 0
      // 317a: getfield Lobby.spos2 I
      // 317d: sipush 138
      // 3180: iadd
      // 3181: if_icmplt 3189
      // 3184: goto 3198
      // 3189: aload 0
      // 318a: iload 2
      // 318b: aload 0
      // 318c: getfield Lobby.spos2 I
      // 318f: isub
      // 3190: putfield Lobby.mscro2 I
      // 3193: goto 3198
      // 3198: aload 0
      // 3199: getfield Lobby.mscro2 I
      // 319c: sipush 145
      // 319f: if_icmpeq 31a7
      // 31a2: goto 31e1
      // 31a7: iload 1
      // 31a8: sipush 696
      // 31ab: if_icmpgt 31b3
      // 31ae: goto 31e1
      // 31b3: iload 1
      // 31b4: sipush 717
      // 31b7: if_icmplt 31bf
      // 31ba: goto 31e1
      // 31bf: iload 2
      // 31c0: bipush 88
      // 31c2: if_icmpgt 31ca
      // 31c5: goto 31e1
      // 31ca: iload 2
      // 31cb: bipush 109
      // 31cd: if_icmplt 31d5
      // 31d0: goto 31e1
      // 31d5: aload 0
      // 31d6: sipush 141
      // 31d9: putfield Lobby.mscro2 I
      // 31dc: goto 31e1
      // 31e1: aload 0
      // 31e2: getfield Lobby.mscro2 I
      // 31e5: sipush 145
      // 31e8: if_icmpeq 31f0
      // 31eb: goto 322c
      // 31f0: iload 1
      // 31f1: sipush 696
      // 31f4: if_icmpgt 31fc
      // 31f7: goto 322c
      // 31fc: iload 1
      // 31fd: sipush 717
      // 3200: if_icmplt 3208
      // 3203: goto 322c
      // 3208: iload 2
      // 3209: sipush 218
      // 320c: if_icmpgt 3214
      // 320f: goto 322c
      // 3214: iload 2
      // 3215: sipush 239
      // 3218: if_icmplt 3220
      // 321b: goto 322c
      // 3220: aload 0
      // 3221: sipush 142
      // 3224: putfield Lobby.mscro2 I
      // 3227: goto 322c
      // 322c: aload 0
      // 322d: getfield Lobby.mscro2 I
      // 3230: sipush 145
      // 3233: if_icmpeq 323b
      // 3236: goto 327f
      // 323b: iload 1
      // 323c: sipush 698
      // 323f: if_icmpgt 3247
      // 3242: goto 327f
      // 3247: iload 1
      // 3248: sipush 715
      // 324b: if_icmplt 3253
      // 324e: goto 327f
      // 3253: iload 2
      // 3254: bipush 107
      // 3256: if_icmpgt 325e
      // 3259: goto 327f
      // 325e: iload 2
      // 325f: sipush 220
      // 3262: if_icmplt 326a
      // 3265: goto 327f
      // 326a: aload 0
      // 326b: bipush 122
      // 326d: putfield Lobby.mscro2 I
      // 3270: aload 0
      // 3271: iload 2
      // 3272: aload 0
      // 3273: getfield Lobby.mscro2 I
      // 3276: isub
      // 3277: putfield Lobby.spos2 I
      // 327a: goto 327f
      // 327f: sipush 400
      // 3282: iload 10
      // 3284: idiv
      // 3285: istore 19
      // 3287: iload 19
      // 3289: bipush 1
      // 328a: if_icmplt 3292
      // 328d: goto 329a
      // 3292: bipush 1
      // 3293: istore 19
      // 3295: goto 329a
      // 329a: aload 0
      // 329b: getfield Lobby.mscro2 I
      // 329e: sipush 141
      // 32a1: if_icmpeq 32a9
      // 32a4: goto 32f0
      // 32a9: aload 0
      // 32aa: dup
      // 32ab: getfield Lobby.spos2 I
      // 32ae: iload 19
      // 32b0: isub
      // 32b1: putfield Lobby.spos2 I
      // 32b4: aload 0
      // 32b5: getfield Lobby.spos2 I
      // 32b8: bipush 82
      // 32ba: if_icmpgt 32c2
      // 32bd: goto 32cd
      // 32c2: aload 0
      // 32c3: bipush 82
      // 32c5: putfield Lobby.spos2 I
      // 32c8: goto 32cd
      // 32cd: aload 0
      // 32ce: getfield Lobby.spos2 I
      // 32d1: iflt 32d9
      // 32d4: goto 32e3
      // 32d9: aload 0
      // 32da: bipush 0
      // 32db: putfield Lobby.spos2 I
      // 32de: goto 32e3
      // 32e3: aload 0
      // 32e4: aload 0
      // 32e5: getfield Lobby.spos2 I
      // 32e8: putfield Lobby.lspos2 I
      // 32eb: goto 32f0
      // 32f0: aload 0
      // 32f1: getfield Lobby.mscro2 I
      // 32f4: sipush 142
      // 32f7: if_icmpeq 32ff
      // 32fa: goto 3346
      // 32ff: aload 0
      // 3300: dup
      // 3301: getfield Lobby.spos2 I
      // 3304: iload 19
      // 3306: iadd
      // 3307: putfield Lobby.spos2 I
      // 330a: aload 0
      // 330b: getfield Lobby.spos2 I
      // 330e: bipush 82
      // 3310: if_icmpgt 3318
      // 3313: goto 3323
      // 3318: aload 0
      // 3319: bipush 82
      // 331b: putfield Lobby.spos2 I
      // 331e: goto 3323
      // 3323: aload 0
      // 3324: getfield Lobby.spos2 I
      // 3327: iflt 332f
      // 332a: goto 3339
      // 332f: aload 0
      // 3330: bipush 0
      // 3331: putfield Lobby.spos2 I
      // 3334: goto 3339
      // 3339: aload 0
      // 333a: aload 0
      // 333b: getfield Lobby.spos2 I
      // 333e: putfield Lobby.lspos2 I
      // 3341: goto 3346
      // 3346: aload 0
      // 3347: getfield Lobby.mscro2 I
      // 334a: sipush 138
      // 334d: if_icmple 3355
      // 3350: goto 338e
      // 3355: aload 0
      // 3356: iload 2
      // 3357: aload 0
      // 3358: getfield Lobby.mscro2 I
      // 335b: isub
      // 335c: putfield Lobby.spos2 I
      // 335f: aload 0
      // 3360: getfield Lobby.spos2 I
      // 3363: bipush 82
      // 3365: if_icmpgt 336d
      // 3368: goto 3378
      // 336d: aload 0
      // 336e: bipush 82
      // 3370: putfield Lobby.spos2 I
      // 3373: goto 3378
      // 3378: aload 0
      // 3379: getfield Lobby.spos2 I
      // 337c: iflt 3384
      // 337f: goto 338e
      // 3384: aload 0
      // 3385: bipush 0
      // 3386: putfield Lobby.spos2 I
      // 3389: goto 338e
      // 338e: aload 0
      // 338f: getfield Lobby.mscro2 I
      // 3392: sipush 145
      // 3395: if_icmpeq 339d
      // 3398: goto 33a9
      // 339d: aload 0
      // 339e: sipush 225
      // 33a1: putfield Lobby.mscro2 I
      // 33a4: goto 33a9
      // 33a9: goto 33c9
      // 33ae: aload 0
      // 33af: getfield Lobby.mscro2 I
      // 33b2: sipush 145
      // 33b5: if_icmpne 33bd
      // 33b8: goto 33c9
      // 33bd: aload 0
      // 33be: sipush 145
      // 33c1: putfield Lobby.mscro2 I
      // 33c4: goto 33c9
      // 33c9: iload 4
      // 33cb: ifne 33d3
      // 33ce: goto 3458
      // 33d3: iload 1
      // 33d4: sipush 235
      // 33d7: if_icmpgt 33df
      // 33da: goto 3458
      // 33df: iload 1
      // 33e0: sipush 698
      // 33e3: if_icmplt 33eb
      // 33e6: goto 3458
      // 33eb: iload 2
      // 33ec: bipush 87
      // 33ee: if_icmpgt 33f6
      // 33f1: goto 3458
      // 33f6: iload 2
      // 33f7: sipush 239
      // 33fa: if_icmplt 3402
      // 33fd: goto 3458
      // 3402: aload 0
      // 3403: dup
      // 3404: getfield Lobby.spos2 I
      // 3407: iload 4
      // 3409: isub
      // 340a: putfield Lobby.spos2 I
      // 340d: aload 0
      // 340e: bipush 1
      // 340f: putfield Lobby.zeromsw Z
      // 3412: aload 0
      // 3413: getfield Lobby.spos2 I
      // 3416: bipush 82
      // 3418: if_icmpgt 3420
      // 341b: goto 3430
      // 3420: aload 0
      // 3421: bipush 82
      // 3423: putfield Lobby.spos2 I
      // 3426: aload 0
      // 3427: bipush 0
      // 3428: putfield Lobby.zeromsw Z
      // 342b: goto 3430
      // 3430: aload 0
      // 3431: getfield Lobby.spos2 I
      // 3434: iflt 343c
      // 3437: goto 344b
      // 343c: aload 0
      // 343d: bipush 0
      // 343e: putfield Lobby.spos2 I
      // 3441: aload 0
      // 3442: bipush 0
      // 3443: putfield Lobby.zeromsw Z
      // 3446: goto 344b
      // 344b: aload 0
      // 344c: aload 0
      // 344d: getfield Lobby.spos2 I
      // 3450: putfield Lobby.lspos2 I
      // 3453: goto 3458
      // 3458: aload 0
      // 3459: getfield Lobby.mousonp I
      // 345c: bipush -1
      // 345d: if_icmpne 3465
      // 3460: goto 3535
      // 3465: iload 17
      // 3467: bipush -1
      // 3468: if_icmpne 3470
      // 346b: goto 3535
      // 3470: aload 0
      // 3471: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3474: aload 0
      // 3475: sipush 255
      // 3478: sipush 255
      // 347b: sipush 255
      // 347e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 3481: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3484: bipush 4
      // 3485: newarray 10
      // 3487: dup
      // 3488: bipush 0
      // 3489: sipush 185
      // 348c: iastore
      // 348d: dup
      // 348e: bipush 1
      // 348f: sipush 241
      // 3492: iastore
      // 3493: dup
      // 3494: bipush 2
      // 3495: sipush 241
      // 3498: iastore
      // 3499: dup
      // 349a: bipush 3
      // 349b: sipush 185
      // 349e: iastore
      // 349f: astore 19
      // 34a1: bipush 4
      // 34a2: newarray 10
      // 34a4: dup
      // 34a5: bipush 0
      // 34a6: iload 14
      // 34a8: iastore
      // 34a9: dup
      // 34aa: bipush 1
      // 34ab: iload 17
      // 34ad: iastore
      // 34ae: dup
      // 34af: bipush 2
      // 34b0: iload 17
      // 34b2: bipush 19
      // 34b4: iadd
      // 34b5: iastore
      // 34b6: dup
      // 34b7: bipush 3
      // 34b8: iload 14
      // 34ba: bipush 30
      // 34bc: iadd
      // 34bd: iastore
      // 34be: astore 20
      // 34c0: aload 0
      // 34c1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 34c4: aload 19
      // 34c6: aload 20
      // 34c8: bipush 4
      // 34c9: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // 34cc: aload 0
      // 34cd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 34d0: aload 0
      // 34d1: sipush 150
      // 34d4: sipush 150
      // 34d7: sipush 150
      // 34da: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 34dd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 34e0: aload 0
      // 34e1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 34e4: sipush 185
      // 34e7: iload 14
      // 34e9: sipush 241
      // 34ec: iload 17
      // 34ee: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 34f1: aload 0
      // 34f2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 34f5: sipush 185
      // 34f8: iload 14
      // 34fa: bipush 30
      // 34fc: iadd
      // 34fd: sipush 241
      // 3500: iload 17
      // 3502: bipush 19
      // 3504: iadd
      // 3505: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3508: aload 0
      // 3509: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 350c: sipush 241
      // 350f: iload 17
      // 3511: sipush 692
      // 3514: iload 17
      // 3516: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3519: aload 0
      // 351a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 351d: sipush 241
      // 3520: iload 17
      // 3522: bipush 19
      // 3524: iadd
      // 3525: sipush 692
      // 3528: iload 17
      // 352a: bipush 19
      // 352c: iadd
      // 352d: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 3530: goto 3535
      // 3535: goto 602a
      // 353a: sipush 230
      // 353d: aload 0
      // 353e: getfield Lobby.britchl I
      // 3541: iadd
      // 3542: istore 15
      // 3544: iload 15
      // 3546: sipush 255
      // 3549: if_icmpgt 3551
      // 354c: goto 355b
      // 3551: sipush 255
      // 3554: istore 15
      // 3556: goto 355b
      // 355b: iload 15
      // 355d: iflt 3565
      // 3560: goto 356d
      // 3565: bipush 0
      // 3566: istore 15
      // 3568: goto 356d
      // 356d: aload 0
      // 356e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3571: aload 0
      // 3572: iload 15
      // 3574: iload 15
      // 3576: iload 15
      // 3578: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 357b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 357e: aload 0
      // 357f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3582: sipush 225
      // 3585: bipush 59
      // 3587: sipush 495
      // 358a: sipush 200
      // 358d: bipush 20
      // 358f: bipush 20
      // 3591: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 3594: aload 0
      // 3595: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3598: new java/awt/Color
      // 359b: dup
      // 359c: bipush 0
      // 359d: bipush 0
      // 359e: bipush 0
      // 359f: invokespecial java/awt/Color.<init> (III)V
      // 35a2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 35a5: aload 0
      // 35a6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 35a9: sipush 225
      // 35ac: bipush 59
      // 35ae: sipush 495
      // 35b1: sipush 200
      // 35b4: bipush 20
      // 35b6: bipush 20
      // 35b8: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 35bb: aload 0
      // 35bc: getfield Lobby.britchl I
      // 35bf: bipush 25
      // 35c1: if_icmplt 35c9
      // 35c4: goto 35d8
      // 35c9: aload 0
      // 35ca: dup
      // 35cb: getfield Lobby.britchl I
      // 35ce: bipush 5
      // 35cf: iadd
      // 35d0: putfield Lobby.britchl I
      // 35d3: goto 35d8
      // 35d8: aload 0
      // 35d9: getfield Lobby.chalngd I
      // 35dc: bipush -1
      // 35dd: if_icmpgt 35e5
      // 35e0: goto 3acc
      // 35e5: bipush 0
      // 35e6: istore 16
      // 35e8: bipush 0
      // 35e9: istore 17
      // 35eb: iload 17
      // 35ed: aload 0
      // 35ee: getfield Lobby.ngm I
      // 35f1: if_icmplt 35f9
      // 35f4: goto 361d
      // 35f9: aload 0
      // 35fa: getfield Lobby.chalngd I
      // 35fd: aload 0
      // 35fe: getfield Lobby.gnum [I
      // 3601: iload 17
      // 3603: iaload
      // 3604: if_icmpeq 360c
      // 3607: goto 3615
      // 360c: iload 17
      // 360e: istore 16
      // 3610: goto 3615
      // 3615: iinc 17 1
      // 3618: goto 35eb
      // 361d: aload 0
      // 361e: getfield Lobby.cflk I
      // 3621: bipush 4
      // 3622: irem
      // 3623: ifeq 362b
      // 3626: goto 3637
      // 362b: aload 0
      // 362c: getfield Lobby.cflk I
      // 362f: ifeq 3637
      // 3632: goto 3711
      // 3637: aload 0
      // 3638: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 363b: new java/awt/Color
      // 363e: dup
      // 363f: bipush 0
      // 3640: bipush 0
      // 3641: bipush 0
      // 3642: invokespecial java/awt/Color.<init> (III)V
      // 3645: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3648: aload 0
      // 3649: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 364c: new java/awt/Font
      // 364f: dup
      // 3650: ldc_w "Arial"
      // 3653: bipush 1
      // 3654: bipush 13
      // 3656: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3659: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 365c: aload 0
      // 365d: aload 0
      // 365e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3661: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 3664: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3667: aload 0
      // 3668: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 366b: new java/lang/StringBuilder
      // 366e: dup
      // 366f: invokespecial java/lang/StringBuilder.<init> ()V
      // 3672: ldc_w "You have been invited by "
      // 3675: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3678: aload 0
      // 3679: getfield Lobby.chalby Ljava/lang/String;
      // 367c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 367f: ldc_w " to join a game!"
      // 3682: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3685: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3688: sipush 472
      // 368b: aload 0
      // 368c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 368f: new java/lang/StringBuilder
      // 3692: dup
      // 3693: invokespecial java/lang/StringBuilder.<init> ()V
      // 3696: ldc_w "You have been invited by "
      // 3699: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 369c: aload 0
      // 369d: getfield Lobby.chalby Ljava/lang/String;
      // 36a0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 36a3: ldc_w " to join a game!"
      // 36a6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 36a9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 36ac: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 36af: bipush 2
      // 36b0: idiv
      // 36b1: isub
      // 36b2: bipush 95
      // 36b4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 36b7: aload 0
      // 36b8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 36bb: new java/awt/Color
      // 36be: dup
      // 36bf: bipush 117
      // 36c1: bipush 67
      // 36c3: bipush 0
      // 36c4: invokespecial java/awt/Color.<init> (III)V
      // 36c7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 36ca: aload 0
      // 36cb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 36ce: aload 0
      // 36cf: getfield Lobby.chalby Ljava/lang/String;
      // 36d2: sipush 472
      // 36d5: aload 0
      // 36d6: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 36d9: new java/lang/StringBuilder
      // 36dc: dup
      // 36dd: invokespecial java/lang/StringBuilder.<init> ()V
      // 36e0: ldc_w "You have been invited by "
      // 36e3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 36e6: aload 0
      // 36e7: getfield Lobby.chalby Ljava/lang/String;
      // 36ea: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 36ed: ldc_w " to join a game!"
      // 36f0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 36f3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 36f6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 36f9: bipush 2
      // 36fa: idiv
      // 36fb: isub
      // 36fc: aload 0
      // 36fd: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3700: ldc_w "You have been invited by "
      // 3703: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3706: iadd
      // 3707: bipush 95
      // 3709: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 370c: goto 3711
      // 3711: aload 0
      // 3712: getfield Lobby.cflk I
      // 3715: ifne 371d
      // 3718: goto 372c
      // 371d: aload 0
      // 371e: dup
      // 371f: getfield Lobby.cflk I
      // 3722: bipush 1
      // 3723: isub
      // 3724: putfield Lobby.cflk I
      // 3727: goto 372c
      // 372c: aload 0
      // 372d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3730: new java/awt/Color
      // 3733: dup
      // 3734: bipush 0
      // 3735: bipush 0
      // 3736: bipush 0
      // 3737: invokespecial java/awt/Color.<init> (III)V
      // 373a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 373d: aload 0
      // 373e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3741: new java/awt/Font
      // 3744: dup
      // 3745: ldc_w "Arial"
      // 3748: bipush 1
      // 3749: bipush 12
      // 374b: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 374e: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3751: aload 0
      // 3752: aload 0
      // 3753: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3756: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 3759: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 375c: aload 0
      // 375d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3760: new java/lang/StringBuilder
      // 3763: dup
      // 3764: invokespecial java/lang/StringBuilder.<init> ()V
      // 3767: ldc_w "Stage:  "
      // 376a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 376d: aload 0
      // 376e: getfield Lobby.gstages [Ljava/lang/String;
      // 3771: iload 16
      // 3773: aaload
      // 3774: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3777: ldc_w " ,  Laps: "
      // 377a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 377d: aload 0
      // 377e: getfield Lobby.gnlaps [I
      // 3781: iload 16
      // 3783: iaload
      // 3784: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3787: ldc ""
      // 3789: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 378c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 378f: sipush 472
      // 3792: aload 0
      // 3793: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3796: new java/lang/StringBuilder
      // 3799: dup
      // 379a: invokespecial java/lang/StringBuilder.<init> ()V
      // 379d: ldc_w "Stage:  "
      // 37a0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 37a3: aload 0
      // 37a4: getfield Lobby.gstages [Ljava/lang/String;
      // 37a7: iload 16
      // 37a9: aaload
      // 37aa: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 37ad: ldc_w " ,  Laps: "
      // 37b0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 37b3: aload 0
      // 37b4: getfield Lobby.gnlaps [I
      // 37b7: iload 16
      // 37b9: iaload
      // 37ba: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 37bd: ldc ""
      // 37bf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 37c2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 37c5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 37c8: bipush 2
      // 37c9: idiv
      // 37ca: isub
      // 37cb: sipush 130
      // 37ce: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 37d1: aload 0
      // 37d2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 37d5: new java/awt/Color
      // 37d8: dup
      // 37d9: bipush 62
      // 37db: bipush 98
      // 37dd: bipush 0
      // 37de: invokespecial java/awt/Color.<init> (III)V
      // 37e1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 37e4: aload 0
      // 37e5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 37e8: aload 0
      // 37e9: getfield Lobby.gstages [Ljava/lang/String;
      // 37ec: iload 16
      // 37ee: aaload
      // 37ef: sipush 472
      // 37f2: aload 0
      // 37f3: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 37f6: new java/lang/StringBuilder
      // 37f9: dup
      // 37fa: invokespecial java/lang/StringBuilder.<init> ()V
      // 37fd: ldc_w "Stage:  "
      // 3800: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3803: aload 0
      // 3804: getfield Lobby.gstages [Ljava/lang/String;
      // 3807: iload 16
      // 3809: aaload
      // 380a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 380d: ldc_w " ,  Laps: "
      // 3810: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3813: aload 0
      // 3814: getfield Lobby.gnlaps [I
      // 3817: iload 16
      // 3819: iaload
      // 381a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 381d: ldc ""
      // 381f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3822: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3825: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3828: bipush 2
      // 3829: idiv
      // 382a: isub
      // 382b: aload 0
      // 382c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 382f: ldc_w "Stage:  "
      // 3832: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3835: iadd
      // 3836: sipush 130
      // 3839: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 383c: aload 0
      // 383d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3840: new java/lang/StringBuilder
      // 3843: dup
      // 3844: invokespecial java/lang/StringBuilder.<init> ()V
      // 3847: ldc ""
      // 3849: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 384c: aload 0
      // 384d: getfield Lobby.gnlaps [I
      // 3850: iload 16
      // 3852: iaload
      // 3853: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3856: ldc ""
      // 3858: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 385b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 385e: sipush 472
      // 3861: aload 0
      // 3862: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3865: new java/lang/StringBuilder
      // 3868: dup
      // 3869: invokespecial java/lang/StringBuilder.<init> ()V
      // 386c: ldc_w "Stage:  "
      // 386f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3872: aload 0
      // 3873: getfield Lobby.gstages [Ljava/lang/String;
      // 3876: iload 16
      // 3878: aaload
      // 3879: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 387c: ldc_w " ,  Laps: "
      // 387f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3882: aload 0
      // 3883: getfield Lobby.gnlaps [I
      // 3886: iload 16
      // 3888: iaload
      // 3889: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 388c: ldc ""
      // 388e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3891: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3894: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3897: bipush 2
      // 3898: idiv
      // 3899: isub
      // 389a: aload 0
      // 389b: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 389e: new java/lang/StringBuilder
      // 38a1: dup
      // 38a2: invokespecial java/lang/StringBuilder.<init> ()V
      // 38a5: ldc_w "Stage:  "
      // 38a8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38ab: aload 0
      // 38ac: getfield Lobby.gstages [Ljava/lang/String;
      // 38af: iload 16
      // 38b1: aaload
      // 38b2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38b5: ldc_w " ,  Laps: "
      // 38b8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38bb: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 38be: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 38c1: iadd
      // 38c2: sipush 130
      // 38c5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 38c8: aload 0
      // 38c9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 38cc: new java/awt/Color
      // 38cf: dup
      // 38d0: bipush 0
      // 38d1: bipush 0
      // 38d2: bipush 0
      // 38d3: invokespecial java/awt/Color.<init> (III)V
      // 38d6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 38d9: aload 0
      // 38da: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 38dd: new java/lang/StringBuilder
      // 38e0: dup
      // 38e1: invokespecial java/lang/StringBuilder.<init> ()V
      // 38e4: ldc_w "Players:  "
      // 38e7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38ea: aload 0
      // 38eb: getfield Lobby.mnpls [I
      // 38ee: iload 16
      // 38f0: iaload
      // 38f1: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 38f4: ldc ""
      // 38f6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 38f9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 38fc: sipush 472
      // 38ff: aload 0
      // 3900: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3903: new java/lang/StringBuilder
      // 3906: dup
      // 3907: invokespecial java/lang/StringBuilder.<init> ()V
      // 390a: ldc_w "Players:  "
      // 390d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3910: aload 0
      // 3911: getfield Lobby.mnpls [I
      // 3914: iload 16
      // 3916: iaload
      // 3917: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 391a: ldc ""
      // 391c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 391f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3922: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3925: bipush 2
      // 3926: idiv
      // 3927: isub
      // 3928: sipush 150
      // 392b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 392e: aload 0
      // 392f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3932: new java/awt/Color
      // 3935: dup
      // 3936: bipush 62
      // 3938: bipush 98
      // 393a: bipush 0
      // 393b: invokespecial java/awt/Color.<init> (III)V
      // 393e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3941: aload 0
      // 3942: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3945: new java/lang/StringBuilder
      // 3948: dup
      // 3949: invokespecial java/lang/StringBuilder.<init> ()V
      // 394c: ldc ""
      // 394e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3951: aload 0
      // 3952: getfield Lobby.mnpls [I
      // 3955: iload 16
      // 3957: iaload
      // 3958: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 395b: ldc ""
      // 395d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3960: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3963: sipush 472
      // 3966: aload 0
      // 3967: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 396a: new java/lang/StringBuilder
      // 396d: dup
      // 396e: invokespecial java/lang/StringBuilder.<init> ()V
      // 3971: ldc_w "Players:  "
      // 3974: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3977: aload 0
      // 3978: getfield Lobby.mnpls [I
      // 397b: iload 16
      // 397d: iaload
      // 397e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3981: ldc ""
      // 3983: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3986: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3989: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 398c: bipush 2
      // 398d: idiv
      // 398e: isub
      // 398f: aload 0
      // 3990: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3993: ldc_w "Players:  "
      // 3996: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3999: iadd
      // 399a: sipush 150
      // 399d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 39a0: new java/util/Date
      // 39a3: dup
      // 39a4: invokespecial java/util/Date.<init> ()V
      // 39a7: astore 17
      // 39a9: aload 17
      // 39ab: invokevirtual java/util/Date.getTime ()J
      // 39ae: lstore 18
      // 39b0: aload 0
      // 39b1: getfield Lobby.ptime J
      // 39b4: lconst_0
      // 39b5: lcmp
      // 39b6: ifne 39be
      // 39b9: goto 39d1
      // 39be: lload 18
      // 39c0: aload 0
      // 39c1: getfield Lobby.ptime J
      // 39c4: ldc2_w 1000
      // 39c7: ladd
      // 39c8: lcmp
      // 39c9: ifgt 39d1
      // 39cc: goto 39f9
      // 39d1: aload 0
      // 39d2: getfield Lobby.ptime J
      // 39d5: lconst_0
      // 39d6: lcmp
      // 39d7: ifne 39df
      // 39da: goto 39ee
      // 39df: aload 0
      // 39e0: dup
      // 39e1: getfield Lobby.ctime I
      // 39e4: bipush 1
      // 39e5: isub
      // 39e6: putfield Lobby.ctime I
      // 39e9: goto 39ee
      // 39ee: aload 0
      // 39ef: lload 18
      // 39f1: putfield Lobby.ptime J
      // 39f4: goto 39f9
      // 39f9: aload 0
      // 39fa: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 39fd: new java/awt/Color
      // 3a00: dup
      // 3a01: bipush 0
      // 3a02: bipush 0
      // 3a03: bipush 0
      // 3a04: invokespecial java/awt/Color.<init> (III)V
      // 3a07: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3a0a: aload 0
      // 3a0b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3a0e: new java/awt/Font
      // 3a11: dup
      // 3a12: ldc_w "Arial"
      // 3a15: bipush 0
      // 3a16: bipush 12
      // 3a18: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3a1b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3a1e: aload 0
      // 3a1f: aload 0
      // 3a20: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3a23: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 3a26: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3a29: aload 0
      // 3a2a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3a2d: new java/lang/StringBuilder
      // 3a30: dup
      // 3a31: invokespecial java/lang/StringBuilder.<init> ()V
      // 3a34: ldc_w "( "
      // 3a37: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a3a: aload 0
      // 3a3b: getfield Lobby.ctime I
      // 3a3e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3a41: ldc_w " )"
      // 3a44: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a47: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3a4a: sipush 472
      // 3a4d: aload 0
      // 3a4e: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3a51: new java/lang/StringBuilder
      // 3a54: dup
      // 3a55: invokespecial java/lang/StringBuilder.<init> ()V
      // 3a58: ldc_w "( "
      // 3a5b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a5e: aload 0
      // 3a5f: getfield Lobby.ctime I
      // 3a62: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 3a65: ldc_w " )"
      // 3a68: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 3a6b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 3a6e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3a71: bipush 2
      // 3a72: idiv
      // 3a73: isub
      // 3a74: sipush 170
      // 3a77: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3a7a: aload 0
      // 3a7b: getfield Lobby.ctime I
      // 3a7e: ifeq 3a86
      // 3a81: goto 3a9d
      // 3a86: aload 0
      // 3a87: aload 0
      // 3a88: getfield Lobby.longame I
      // 3a8b: putfield Lobby.ongame I
      // 3a8e: aload 0
      // 3a8f: bipush -1
      // 3a90: putfield Lobby.chalngd I
      // 3a93: aload 0
      // 3a94: bipush -1
      // 3a95: putfield Lobby.longame I
      // 3a98: goto 3a9d
      // 3a9d: aload 0
      // 3a9e: ldc_w "   View Game   "
      // 3aa1: sipush 352
      // 3aa4: sipush 215
      // 3aa7: bipush 2
      // 3aa8: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 3aab: aload 0
      // 3aac: ldc_w "   Join Game   "
      // 3aaf: sipush 462
      // 3ab2: sipush 215
      // 3ab5: bipush 2
      // 3ab6: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 3ab9: aload 0
      // 3aba: ldc_w "   Decline X   "
      // 3abd: sipush 599
      // 3ac0: sipush 215
      // 3ac3: bipush 2
      // 3ac4: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 3ac7: goto 602a
      // 3acc: aload 0
      // 3acd: getfield Lobby.chalngd I
      // 3ad0: bipush -5
      // 3ad2: if_icmpne 3ada
      // 3ad5: goto 3aec
      // 3ada: aload 0
      // 3adb: ldc_w " Cancel X "
      // 3ade: sipush 669
      // 3ae1: bipush 85
      // 3ae3: bipush 2
      // 3ae4: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 3ae7: goto 3aec
      // 3aec: aload 0
      // 3aed: getfield Lobby.chalngd I
      // 3af0: bipush -6
      // 3af2: if_icmpeq 3afa
      // 3af5: goto 3bd9
      // 3afa: aload 0
      // 3afb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3afe: new java/awt/Color
      // 3b01: dup
      // 3b02: bipush 0
      // 3b03: bipush 0
      // 3b04: bipush 0
      // 3b05: invokespecial java/awt/Color.<init> (III)V
      // 3b08: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3b0b: aload 0
      // 3b0c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3b0f: new java/awt/Font
      // 3b12: dup
      // 3b13: ldc_w "Arial"
      // 3b16: bipush 1
      // 3b17: bipush 13
      // 3b19: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3b1c: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3b1f: aload 0
      // 3b20: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3b23: ldc_w "This room already has a game that has started."
      // 3b26: sipush 288
      // 3b29: bipush 120
      // 3b2b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3b2e: aload 0
      // 3b2f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3b32: ldc_w "Please switch to another room to create a new game."
      // 3b35: sipush 288
      // 3b38: sipush 140
      // 3b3b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3b3e: aload 0
      // 3b3f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3b42: ldc_w "Or wait for the game to finish."
      // 3b45: sipush 288
      // 3b48: sipush 160
      // 3b4b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3b4e: aload 0
      // 3b4f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3b52: ldc_w "Use the dropdown menu above to change room or server."
      // 3b55: sipush 288
      // 3b58: sipush 180
      // 3b5b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3b5e: bipush 7
      // 3b60: newarray 10
      // 3b62: dup
      // 3b63: bipush 0
      // 3b64: sipush 580
      // 3b67: iastore
      // 3b68: dup
      // 3b69: bipush 1
      // 3b6a: sipush 569
      // 3b6d: iastore
      // 3b6e: dup
      // 3b6f: bipush 2
      // 3b70: sipush 576
      // 3b73: iastore
      // 3b74: dup
      // 3b75: bipush 3
      // 3b76: sipush 576
      // 3b79: iastore
      // 3b7a: dup
      // 3b7b: bipush 4
      // 3b7c: sipush 584
      // 3b7f: iastore
      // 3b80: dup
      // 3b81: bipush 5
      // 3b82: sipush 584
      // 3b85: iastore
      // 3b86: dup
      // 3b87: bipush 6
      // 3b89: sipush 591
      // 3b8c: iastore
      // 3b8d: astore 16
      // 3b8f: bipush 7
      // 3b91: newarray 10
      // 3b93: dup
      // 3b94: bipush 0
      // 3b95: bipush 66
      // 3b97: iastore
      // 3b98: dup
      // 3b99: bipush 1
      // 3b9a: bipush 77
      // 3b9c: iastore
      // 3b9d: dup
      // 3b9e: bipush 2
      // 3b9f: bipush 77
      // 3ba1: iastore
      // 3ba2: dup
      // 3ba3: bipush 3
      // 3ba4: bipush 102
      // 3ba6: iastore
      // 3ba7: dup
      // 3ba8: bipush 4
      // 3ba9: bipush 102
      // 3bab: iastore
      // 3bac: dup
      // 3bad: bipush 5
      // 3bae: bipush 77
      // 3bb0: iastore
      // 3bb1: dup
      // 3bb2: bipush 6
      // 3bb4: bipush 77
      // 3bb6: iastore
      // 3bb7: astore 17
      // 3bb9: aload 0
      // 3bba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3bbd: aload 16
      // 3bbf: aload 17
      // 3bc1: bipush 7
      // 3bc3: invokevirtual java/awt/Graphics2D.fillPolygon ([I[II)V
      // 3bc6: aload 0
      // 3bc7: ldc_w "     OK     "
      // 3bca: sipush 472
      // 3bcd: sipush 215
      // 3bd0: bipush 1
      // 3bd1: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 3bd4: goto 3bd9
      // 3bd9: aload 0
      // 3bda: getfield Lobby.chalngd I
      // 3bdd: bipush -2
      // 3bdf: if_icmpeq 3be7
      // 3be2: goto 548b
      // 3be7: bipush 0
      // 3be8: istore 16
      // 3bea: bipush 0
      // 3beb: istore 17
      // 3bed: aload 0
      // 3bee: getfield Lobby.gs LGameSparker;
      // 3bf1: getfield GameSparker.wgame LSmenu;
      // 3bf4: invokevirtual Smenu.isShowing ()Z
      // 3bf7: ifeq 3bff
      // 3bfa: goto 3c0e
      // 3bff: aload 0
      // 3c00: getfield Lobby.gs LGameSparker;
      // 3c03: getfield GameSparker.wgame LSmenu;
      // 3c06: invokevirtual Smenu.show ()V
      // 3c09: goto 3c0e
      // 3c0e: aload 0
      // 3c0f: getfield Lobby.gs LGameSparker;
      // 3c12: getfield GameSparker.wgame LSmenu;
      // 3c15: sipush 236
      // 3c18: bipush 68
      // 3c1a: invokevirtual Smenu.move (II)V
      // 3c1d: aload 0
      // 3c1e: getfield Lobby.gs LGameSparker;
      // 3c21: getfield GameSparker.wgame LSmenu;
      // 3c24: invokevirtual Smenu.getSelectedIndex ()I
      // 3c27: ifeq 3c2f
      // 3c2a: goto 4ceb
      // 3c2f: aload 0
      // 3c30: getfield Lobby.inwab Z
      // 3c33: ifne 3c3b
      // 3c36: goto 3c54
      // 3c3b: aload 0
      // 3c3c: bipush 0
      // 3c3d: putfield Lobby.inwab Z
      // 3c40: aload 0
      // 3c41: getfield Lobby.gs LGameSparker;
      // 3c44: getfield GameSparker.warb LSmenu;
      // 3c47: invokevirtual Smenu.hide ()V
      // 3c4a: aload 0
      // 3c4b: bipush 0
      // 3c4c: putfield Lobby.loadstage I
      // 3c4f: goto 3c54
      // 3c54: aload 0
      // 3c55: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3c58: new java/awt/Color
      // 3c5b: dup
      // 3c5c: bipush 0
      // 3c5d: bipush 0
      // 3c5e: bipush 0
      // 3c5f: invokespecial java/awt/Color.<init> (III)V
      // 3c62: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 3c65: aload 0
      // 3c66: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3c69: new java/awt/Font
      // 3c6c: dup
      // 3c6d: ldc_w "Arial"
      // 3c70: bipush 1
      // 3c71: bipush 13
      // 3c73: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 3c76: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 3c79: aload 0
      // 3c7a: aload 0
      // 3c7b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3c7e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 3c81: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3c84: aload 0
      // 3c85: getfield Lobby.sflk I
      // 3c88: bipush 4
      // 3c89: irem
      // 3c8a: ifeq 3c92
      // 3c8d: goto 3c9e
      // 3c92: aload 0
      // 3c93: getfield Lobby.sflk I
      // 3c96: ifeq 3c9e
      // 3c99: goto 3cbf
      // 3c9e: aload 0
      // 3c9f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3ca2: ldc_w "Select Stage"
      // 3ca5: sipush 472
      // 3ca8: aload 0
      // 3ca9: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 3cac: ldc_w "Select Stage"
      // 3caf: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 3cb2: bipush 2
      // 3cb3: idiv
      // 3cb4: isub
      // 3cb5: bipush 85
      // 3cb7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 3cba: goto 3cbf
      // 3cbf: aload 0
      // 3cc0: getfield Lobby.sflk I
      // 3cc3: ifne 3ccb
      // 3cc6: goto 3cda
      // 3ccb: aload 0
      // 3ccc: dup
      // 3ccd: getfield Lobby.sflk I
      // 3cd0: bipush 1
      // 3cd1: isub
      // 3cd2: putfield Lobby.sflk I
      // 3cd5: goto 3cda
      // 3cda: bipush 0
      // 3cdb: istore 18
      // 3cdd: aload 0
      // 3cde: getfield Lobby.gs LGameSparker;
      // 3ce1: getfield GameSparker.sgame LSmenu;
      // 3ce4: sipush 139
      // 3ce7: bipush 22
      // 3ce9: invokevirtual Smenu.setSize (II)V
      // 3cec: aload 0
      // 3ced: getfield Lobby.gs LGameSparker;
      // 3cf0: getfield GameSparker.sgame LSmenu;
      // 3cf3: invokevirtual Smenu.getSelectedIndex ()I
      // 3cf6: ifeq 3cfe
      // 3cf9: goto 3d28
      // 3cfe: sipush 472
      // 3d01: aload 0
      // 3d02: getfield Lobby.gs LGameSparker;
      // 3d05: getfield GameSparker.sgame LSmenu;
      // 3d08: invokevirtual Smenu.getWidth ()I
      // 3d0b: bipush 6
      // 3d0d: iadd
      // 3d0e: aload 0
      // 3d0f: getfield Lobby.gs LGameSparker;
      // 3d12: getfield GameSparker.snfmm LSmenu;
      // 3d15: invokevirtual Smenu.getWidth ()I
      // 3d18: iadd
      // 3d19: bipush 2
      // 3d1a: idiv
      // 3d1b: isub
      // 3d1c: istore 18
      // 3d1e: aload 0
      // 3d1f: bipush 0
      // 3d20: putfield Lobby.sgflag I
      // 3d23: goto 3d28
      // 3d28: aload 0
      // 3d29: getfield Lobby.gs LGameSparker;
      // 3d2c: getfield GameSparker.sgame LSmenu;
      // 3d2f: invokevirtual Smenu.getSelectedIndex ()I
      // 3d32: bipush 1
      // 3d33: if_icmpeq 3d3b
      // 3d36: goto 3d65
      // 3d3b: sipush 472
      // 3d3e: aload 0
      // 3d3f: getfield Lobby.gs LGameSparker;
      // 3d42: getfield GameSparker.sgame LSmenu;
      // 3d45: invokevirtual Smenu.getWidth ()I
      // 3d48: bipush 6
      // 3d4a: iadd
      // 3d4b: aload 0
      // 3d4c: getfield Lobby.gs LGameSparker;
      // 3d4f: getfield GameSparker.snfm1 LSmenu;
      // 3d52: invokevirtual Smenu.getWidth ()I
      // 3d55: iadd
      // 3d56: bipush 2
      // 3d57: idiv
      // 3d58: isub
      // 3d59: istore 18
      // 3d5b: aload 0
      // 3d5c: bipush 1
      // 3d5d: putfield Lobby.sgflag I
      // 3d60: goto 3d65
      // 3d65: aload 0
      // 3d66: getfield Lobby.gs LGameSparker;
      // 3d69: getfield GameSparker.sgame LSmenu;
      // 3d6c: invokevirtual Smenu.getSelectedIndex ()I
      // 3d6f: bipush 2
      // 3d70: if_icmpeq 3d78
      // 3d73: goto 3da2
      // 3d78: sipush 472
      // 3d7b: aload 0
      // 3d7c: getfield Lobby.gs LGameSparker;
      // 3d7f: getfield GameSparker.sgame LSmenu;
      // 3d82: invokevirtual Smenu.getWidth ()I
      // 3d85: bipush 6
      // 3d87: iadd
      // 3d88: aload 0
      // 3d89: getfield Lobby.gs LGameSparker;
      // 3d8c: getfield GameSparker.snfm2 LSmenu;
      // 3d8f: invokevirtual Smenu.getWidth ()I
      // 3d92: iadd
      // 3d93: bipush 2
      // 3d94: idiv
      // 3d95: isub
      // 3d96: istore 18
      // 3d98: aload 0
      // 3d99: bipush 2
      // 3d9a: putfield Lobby.sgflag I
      // 3d9d: goto 3da2
      // 3da2: aload 0
      // 3da3: getfield Lobby.gs LGameSparker;
      // 3da6: getfield GameSparker.sgame LSmenu;
      // 3da9: invokevirtual Smenu.getSelectedIndex ()I
      // 3dac: bipush 3
      // 3dad: if_icmpeq 3db5
      // 3db0: goto 3e82
      // 3db5: aload 0
      // 3db6: getfield Lobby.gs LGameSparker;
      // 3db9: getfield GameSparker.mstgs LSmenu;
      // 3dbc: sipush 338
      // 3dbf: bipush 22
      // 3dc1: invokevirtual Smenu.setSize (II)V
      // 3dc4: aload 0
      // 3dc5: getfield Lobby.sgflag I
      // 3dc8: bipush 3
      // 3dc9: if_icmpne 3dd1
      // 3dcc: goto 3e5d
      // 3dd1: aload 0
      // 3dd2: bipush 0
      // 3dd3: putfield Lobby.gstage I
      // 3dd6: aload 0
      // 3dd7: getfield Lobby.xt LxtGraphics;
      // 3dda: getfield xtGraphics.logged Z
      // 3ddd: ifne 3de5
      // 3de0: goto 3e25
      // 3de5: aload 0
      // 3de6: getfield Lobby.cd LCarDefine;
      // 3de9: getfield CarDefine.msloaded I
      // 3dec: bipush 1
      // 3ded: if_icmpne 3df5
      // 3df0: goto 3e53
      // 3df5: aload 0
      // 3df6: getfield Lobby.gs LGameSparker;
      // 3df9: getfield GameSparker.mstgs LSmenu;
      // 3dfc: invokevirtual Smenu.removeAll ()V
      // 3dff: aload 0
      // 3e00: getfield Lobby.gs LGameSparker;
      // 3e03: getfield GameSparker.mstgs LSmenu;
      // 3e06: aload 0
      // 3e07: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3e0a: ldc_w "Loading your stages now, please wait..."
      // 3e0d: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3e10: aload 0
      // 3e11: getfield Lobby.gs LGameSparker;
      // 3e14: getfield GameSparker.mstgs LSmenu;
      // 3e17: bipush 0
      // 3e18: invokevirtual Smenu.select (I)V
      // 3e1b: aload 0
      // 3e1c: bipush 1
      // 3e1d: putfield Lobby.msload I
      // 3e20: goto 3e53
      // 3e25: aload 0
      // 3e26: getfield Lobby.gs LGameSparker;
      // 3e29: getfield GameSparker.mstgs LSmenu;
      // 3e2c: invokevirtual Smenu.removeAll ()V
      // 3e2f: aload 0
      // 3e30: getfield Lobby.gs LGameSparker;
      // 3e33: getfield GameSparker.mstgs LSmenu;
      // 3e36: aload 0
      // 3e37: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3e3a: ldc_w "You need to have a full account to access this."
      // 3e3d: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3e40: aload 0
      // 3e41: getfield Lobby.gs LGameSparker;
      // 3e44: getfield GameSparker.mstgs LSmenu;
      // 3e47: bipush 0
      // 3e48: invokevirtual Smenu.select (I)V
      // 3e4b: aload 0
      // 3e4c: getfield Lobby.cd LCarDefine;
      // 3e4f: bipush 0
      // 3e50: putfield CarDefine.msloaded I
      // 3e53: aload 0
      // 3e54: bipush 3
      // 3e55: putfield Lobby.sgflag I
      // 3e58: goto 3e5d
      // 3e5d: sipush 472
      // 3e60: aload 0
      // 3e61: getfield Lobby.gs LGameSparker;
      // 3e64: getfield GameSparker.sgame LSmenu;
      // 3e67: invokevirtual Smenu.getWidth ()I
      // 3e6a: bipush 6
      // 3e6c: iadd
      // 3e6d: aload 0
      // 3e6e: getfield Lobby.gs LGameSparker;
      // 3e71: getfield GameSparker.mstgs LSmenu;
      // 3e74: invokevirtual Smenu.getWidth ()I
      // 3e77: iadd
      // 3e78: bipush 2
      // 3e79: idiv
      // 3e7a: isub
      // 3e7b: istore 18
      // 3e7d: goto 3e82
      // 3e82: aload 0
      // 3e83: getfield Lobby.gs LGameSparker;
      // 3e86: getfield GameSparker.sgame LSmenu;
      // 3e89: invokevirtual Smenu.getSelectedIndex ()I
      // 3e8c: bipush 4
      // 3e8d: if_icmpeq 3e95
      // 3e90: goto 3f64
      // 3e95: aload 0
      // 3e96: getfield Lobby.gs LGameSparker;
      // 3e99: getfield GameSparker.mstgs LSmenu;
      // 3e9c: sipush 338
      // 3e9f: bipush 22
      // 3ea1: invokevirtual Smenu.setSize (II)V
      // 3ea4: aload 0
      // 3ea5: getfield Lobby.sgflag I
      // 3ea8: bipush 4
      // 3ea9: if_icmpne 3eb1
      // 3eac: goto 3f3f
      // 3eb1: aload 0
      // 3eb2: bipush 0
      // 3eb3: putfield Lobby.gstage I
      // 3eb6: aload 0
      // 3eb7: getfield Lobby.xt LxtGraphics;
      // 3eba: getfield xtGraphics.logged Z
      // 3ebd: ifne 3ec5
      // 3ec0: goto 3f07
      // 3ec5: aload 0
      // 3ec6: getfield Lobby.cd LCarDefine;
      // 3ec9: getfield CarDefine.msloaded I
      // 3ecc: bipush 7
      // 3ece: if_icmpne 3ed6
      // 3ed1: goto 3f35
      // 3ed6: aload 0
      // 3ed7: getfield Lobby.gs LGameSparker;
      // 3eda: getfield GameSparker.mstgs LSmenu;
      // 3edd: invokevirtual Smenu.removeAll ()V
      // 3ee0: aload 0
      // 3ee1: getfield Lobby.gs LGameSparker;
      // 3ee4: getfield GameSparker.mstgs LSmenu;
      // 3ee7: aload 0
      // 3ee8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3eeb: ldc_w "Loading your stages now, please wait..."
      // 3eee: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3ef1: aload 0
      // 3ef2: getfield Lobby.gs LGameSparker;
      // 3ef5: getfield GameSparker.mstgs LSmenu;
      // 3ef8: bipush 0
      // 3ef9: invokevirtual Smenu.select (I)V
      // 3efc: aload 0
      // 3efd: bipush 7
      // 3eff: putfield Lobby.msload I
      // 3f02: goto 3f35
      // 3f07: aload 0
      // 3f08: getfield Lobby.gs LGameSparker;
      // 3f0b: getfield GameSparker.mstgs LSmenu;
      // 3f0e: invokevirtual Smenu.removeAll ()V
      // 3f11: aload 0
      // 3f12: getfield Lobby.gs LGameSparker;
      // 3f15: getfield GameSparker.mstgs LSmenu;
      // 3f18: aload 0
      // 3f19: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3f1c: ldc_w "You need to have a full account to access this."
      // 3f1f: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3f22: aload 0
      // 3f23: getfield Lobby.gs LGameSparker;
      // 3f26: getfield GameSparker.mstgs LSmenu;
      // 3f29: bipush 0
      // 3f2a: invokevirtual Smenu.select (I)V
      // 3f2d: aload 0
      // 3f2e: getfield Lobby.cd LCarDefine;
      // 3f31: bipush 0
      // 3f32: putfield CarDefine.msloaded I
      // 3f35: aload 0
      // 3f36: bipush 4
      // 3f37: putfield Lobby.sgflag I
      // 3f3a: goto 3f3f
      // 3f3f: sipush 472
      // 3f42: aload 0
      // 3f43: getfield Lobby.gs LGameSparker;
      // 3f46: getfield GameSparker.sgame LSmenu;
      // 3f49: invokevirtual Smenu.getWidth ()I
      // 3f4c: bipush 6
      // 3f4e: iadd
      // 3f4f: aload 0
      // 3f50: getfield Lobby.gs LGameSparker;
      // 3f53: getfield GameSparker.mstgs LSmenu;
      // 3f56: invokevirtual Smenu.getWidth ()I
      // 3f59: iadd
      // 3f5a: bipush 2
      // 3f5b: idiv
      // 3f5c: isub
      // 3f5d: istore 18
      // 3f5f: goto 3f64
      // 3f64: aload 0
      // 3f65: getfield Lobby.gs LGameSparker;
      // 3f68: getfield GameSparker.sgame LSmenu;
      // 3f6b: invokevirtual Smenu.getSelectedIndex ()I
      // 3f6e: bipush 5
      // 3f6f: if_icmpeq 3f77
      // 3f72: goto 4044
      // 3f77: aload 0
      // 3f78: getfield Lobby.gs LGameSparker;
      // 3f7b: getfield GameSparker.mstgs LSmenu;
      // 3f7e: sipush 338
      // 3f81: bipush 22
      // 3f83: invokevirtual Smenu.setSize (II)V
      // 3f86: aload 0
      // 3f87: getfield Lobby.sgflag I
      // 3f8a: bipush 5
      // 3f8b: if_icmpne 3f93
      // 3f8e: goto 401f
      // 3f93: aload 0
      // 3f94: bipush 0
      // 3f95: putfield Lobby.gstage I
      // 3f98: aload 0
      // 3f99: getfield Lobby.xt LxtGraphics;
      // 3f9c: getfield xtGraphics.logged Z
      // 3f9f: ifne 3fa7
      // 3fa2: goto 3fe7
      // 3fa7: aload 0
      // 3fa8: getfield Lobby.cd LCarDefine;
      // 3fab: getfield CarDefine.msloaded I
      // 3fae: bipush 3
      // 3faf: if_icmpne 3fb7
      // 3fb2: goto 4015
      // 3fb7: aload 0
      // 3fb8: getfield Lobby.gs LGameSparker;
      // 3fbb: getfield GameSparker.mstgs LSmenu;
      // 3fbe: invokevirtual Smenu.removeAll ()V
      // 3fc1: aload 0
      // 3fc2: getfield Lobby.gs LGameSparker;
      // 3fc5: getfield GameSparker.mstgs LSmenu;
      // 3fc8: aload 0
      // 3fc9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3fcc: ldc_w "Loading your stages now, please wait..."
      // 3fcf: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 3fd2: aload 0
      // 3fd3: getfield Lobby.gs LGameSparker;
      // 3fd6: getfield GameSparker.mstgs LSmenu;
      // 3fd9: bipush 0
      // 3fda: invokevirtual Smenu.select (I)V
      // 3fdd: aload 0
      // 3fde: bipush 3
      // 3fdf: putfield Lobby.msload I
      // 3fe2: goto 4015
      // 3fe7: aload 0
      // 3fe8: getfield Lobby.gs LGameSparker;
      // 3feb: getfield GameSparker.mstgs LSmenu;
      // 3fee: invokevirtual Smenu.removeAll ()V
      // 3ff1: aload 0
      // 3ff2: getfield Lobby.gs LGameSparker;
      // 3ff5: getfield GameSparker.mstgs LSmenu;
      // 3ff8: aload 0
      // 3ff9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 3ffc: ldc_w "You need to have a full account to access this."
      // 3fff: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 4002: aload 0
      // 4003: getfield Lobby.gs LGameSparker;
      // 4006: getfield GameSparker.mstgs LSmenu;
      // 4009: bipush 0
      // 400a: invokevirtual Smenu.select (I)V
      // 400d: aload 0
      // 400e: getfield Lobby.cd LCarDefine;
      // 4011: bipush 0
      // 4012: putfield CarDefine.msloaded I
      // 4015: aload 0
      // 4016: bipush 5
      // 4017: putfield Lobby.sgflag I
      // 401a: goto 401f
      // 401f: sipush 472
      // 4022: aload 0
      // 4023: getfield Lobby.gs LGameSparker;
      // 4026: getfield GameSparker.sgame LSmenu;
      // 4029: invokevirtual Smenu.getWidth ()I
      // 402c: bipush 6
      // 402e: iadd
      // 402f: aload 0
      // 4030: getfield Lobby.gs LGameSparker;
      // 4033: getfield GameSparker.mstgs LSmenu;
      // 4036: invokevirtual Smenu.getWidth ()I
      // 4039: iadd
      // 403a: bipush 2
      // 403b: idiv
      // 403c: isub
      // 403d: istore 18
      // 403f: goto 4044
      // 4044: aload 0
      // 4045: getfield Lobby.gs LGameSparker;
      // 4048: getfield GameSparker.sgame LSmenu;
      // 404b: invokevirtual Smenu.getSelectedIndex ()I
      // 404e: bipush 6
      // 4050: if_icmpeq 4058
      // 4053: goto 4127
      // 4058: aload 0
      // 4059: getfield Lobby.gs LGameSparker;
      // 405c: getfield GameSparker.mstgs LSmenu;
      // 405f: sipush 338
      // 4062: bipush 22
      // 4064: invokevirtual Smenu.setSize (II)V
      // 4067: aload 0
      // 4068: getfield Lobby.sgflag I
      // 406b: bipush 6
      // 406d: if_icmpne 4075
      // 4070: goto 4102
      // 4075: aload 0
      // 4076: bipush 0
      // 4077: putfield Lobby.gstage I
      // 407a: aload 0
      // 407b: getfield Lobby.xt LxtGraphics;
      // 407e: getfield xtGraphics.logged Z
      // 4081: ifne 4089
      // 4084: goto 40c9
      // 4089: aload 0
      // 408a: getfield Lobby.cd LCarDefine;
      // 408d: getfield CarDefine.msloaded I
      // 4090: bipush 4
      // 4091: if_icmpne 4099
      // 4094: goto 40f7
      // 4099: aload 0
      // 409a: getfield Lobby.gs LGameSparker;
      // 409d: getfield GameSparker.mstgs LSmenu;
      // 40a0: invokevirtual Smenu.removeAll ()V
      // 40a3: aload 0
      // 40a4: getfield Lobby.gs LGameSparker;
      // 40a7: getfield GameSparker.mstgs LSmenu;
      // 40aa: aload 0
      // 40ab: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 40ae: ldc_w "Loading your stages now, please wait..."
      // 40b1: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 40b4: aload 0
      // 40b5: getfield Lobby.gs LGameSparker;
      // 40b8: getfield GameSparker.mstgs LSmenu;
      // 40bb: bipush 0
      // 40bc: invokevirtual Smenu.select (I)V
      // 40bf: aload 0
      // 40c0: bipush 4
      // 40c1: putfield Lobby.msload I
      // 40c4: goto 40f7
      // 40c9: aload 0
      // 40ca: getfield Lobby.gs LGameSparker;
      // 40cd: getfield GameSparker.mstgs LSmenu;
      // 40d0: invokevirtual Smenu.removeAll ()V
      // 40d3: aload 0
      // 40d4: getfield Lobby.gs LGameSparker;
      // 40d7: getfield GameSparker.mstgs LSmenu;
      // 40da: aload 0
      // 40db: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 40de: ldc_w "You need to have a full account to access this."
      // 40e1: invokevirtual Smenu.add (Ljava/awt/Graphics2D;Ljava/lang/String;)V
      // 40e4: aload 0
      // 40e5: getfield Lobby.gs LGameSparker;
      // 40e8: getfield GameSparker.mstgs LSmenu;
      // 40eb: bipush 0
      // 40ec: invokevirtual Smenu.select (I)V
      // 40ef: aload 0
      // 40f0: getfield Lobby.cd LCarDefine;
      // 40f3: bipush 0
      // 40f4: putfield CarDefine.msloaded I
      // 40f7: aload 0
      // 40f8: bipush 6
      // 40fa: putfield Lobby.sgflag I
      // 40fd: goto 4102
      // 4102: sipush 472
      // 4105: aload 0
      // 4106: getfield Lobby.gs LGameSparker;
      // 4109: getfield GameSparker.sgame LSmenu;
      // 410c: invokevirtual Smenu.getWidth ()I
      // 410f: bipush 6
      // 4111: iadd
      // 4112: aload 0
      // 4113: getfield Lobby.gs LGameSparker;
      // 4116: getfield GameSparker.mstgs LSmenu;
      // 4119: invokevirtual Smenu.getWidth ()I
      // 411c: iadd
      // 411d: bipush 2
      // 411e: idiv
      // 411f: isub
      // 4120: istore 18
      // 4122: goto 4127
      // 4127: aload 0
      // 4128: getfield Lobby.gs LGameSparker;
      // 412b: getfield GameSparker.sgame LSmenu;
      // 412e: invokevirtual Smenu.isShowing ()Z
      // 4131: ifeq 4139
      // 4134: goto 4182
      // 4139: aload 0
      // 413a: getfield Lobby.gs LGameSparker;
      // 413d: getfield GameSparker.sgame LSmenu;
      // 4140: invokevirtual Smenu.show ()V
      // 4143: aload 0
      // 4144: bipush 0
      // 4145: putfield Lobby.remstage I
      // 4148: aload 0
      // 4149: getfield Lobby.loadstage I
      // 414c: ifeq 4154
      // 414f: goto 4182
      // 4154: invokestatic java/lang/Math.random ()D
      // 4157: ldc2_w 3.0
      // 415a: dmul
      // 415b: d2i
      // 415c: istore 19
      // 415e: iload 19
      // 4160: bipush 3
      // 4161: if_icmpeq 4169
      // 4164: goto 4171
      // 4169: bipush 2
      // 416a: istore 19
      // 416c: goto 4171
      // 4171: aload 0
      // 4172: getfield Lobby.gs LGameSparker;
      // 4175: getfield GameSparker.sgame LSmenu;
      // 4178: iload 19
      // 417a: invokevirtual Smenu.select (I)V
      // 417d: goto 4182
      // 4182: aload 0
      // 4183: getfield Lobby.gs LGameSparker;
      // 4186: getfield GameSparker.sgame LSmenu;
      // 4189: iload 18
      // 418b: bipush 105
      // 418d: invokevirtual Smenu.move (II)V
      // 4190: iload 18
      // 4192: aload 0
      // 4193: getfield Lobby.gs LGameSparker;
      // 4196: getfield GameSparker.sgame LSmenu;
      // 4199: invokevirtual Smenu.getWidth ()I
      // 419c: bipush 6
      // 419e: iadd
      // 419f: iadd
      // 41a0: istore 18
      // 41a2: aload 0
      // 41a3: getfield Lobby.gs LGameSparker;
      // 41a6: getfield GameSparker.sgame LSmenu;
      // 41a9: invokevirtual Smenu.getSelectedIndex ()I
      // 41ac: ifeq 41b4
      // 41af: goto 425d
      // 41b4: aload 0
      // 41b5: getfield Lobby.gs LGameSparker;
      // 41b8: getfield GameSparker.snfmm LSmenu;
      // 41bb: invokevirtual Smenu.isShowing ()Z
      // 41be: ifeq 41c6
      // 41c1: goto 41ec
      // 41c6: aload 0
      // 41c7: getfield Lobby.gs LGameSparker;
      // 41ca: getfield GameSparker.snfmm LSmenu;
      // 41cd: invokevirtual Smenu.show ()V
      // 41d0: aload 0
      // 41d1: getfield Lobby.loadstage I
      // 41d4: ifeq 41dc
      // 41d7: goto 41ec
      // 41dc: aload 0
      // 41dd: getfield Lobby.gs LGameSparker;
      // 41e0: getfield GameSparker.snfmm LSmenu;
      // 41e3: bipush 0
      // 41e4: invokevirtual Smenu.select (I)V
      // 41e7: goto 41ec
      // 41ec: aload 0
      // 41ed: getfield Lobby.gs LGameSparker;
      // 41f0: getfield GameSparker.snfmm LSmenu;
      // 41f3: iload 18
      // 41f5: bipush 105
      // 41f7: invokevirtual Smenu.move (II)V
      // 41fa: aload 0
      // 41fb: getfield Lobby.gs LGameSparker;
      // 41fe: getfield GameSparker.snfm1 LSmenu;
      // 4201: invokevirtual Smenu.isShowing ()Z
      // 4204: ifne 420c
      // 4207: goto 421b
      // 420c: aload 0
      // 420d: getfield Lobby.gs LGameSparker;
      // 4210: getfield GameSparker.snfm1 LSmenu;
      // 4213: invokevirtual Smenu.hide ()V
      // 4216: goto 421b
      // 421b: aload 0
      // 421c: getfield Lobby.gs LGameSparker;
      // 421f: getfield GameSparker.snfm2 LSmenu;
      // 4222: invokevirtual Smenu.isShowing ()Z
      // 4225: ifne 422d
      // 4228: goto 423c
      // 422d: aload 0
      // 422e: getfield Lobby.gs LGameSparker;
      // 4231: getfield GameSparker.snfm2 LSmenu;
      // 4234: invokevirtual Smenu.hide ()V
      // 4237: goto 423c
      // 423c: aload 0
      // 423d: getfield Lobby.gs LGameSparker;
      // 4240: getfield GameSparker.mstgs LSmenu;
      // 4243: invokevirtual Smenu.isShowing ()Z
      // 4246: ifne 424e
      // 4249: goto 425d
      // 424e: aload 0
      // 424f: getfield Lobby.gs LGameSparker;
      // 4252: getfield GameSparker.mstgs LSmenu;
      // 4255: invokevirtual Smenu.hide ()V
      // 4258: goto 425d
      // 425d: aload 0
      // 425e: getfield Lobby.gs LGameSparker;
      // 4261: getfield GameSparker.sgame LSmenu;
      // 4264: invokevirtual Smenu.getSelectedIndex ()I
      // 4267: ifeq 426f
      // 426a: goto 42bf
      // 426f: aload 0
      // 4270: getfield Lobby.gs LGameSparker;
      // 4273: getfield GameSparker.snfmm LSmenu;
      // 4276: invokevirtual Smenu.getSelectedIndex ()I
      // 4279: ifne 4281
      // 427c: goto 42bf
      // 4281: aload 0
      // 4282: getfield Lobby.gstage I
      // 4285: aload 0
      // 4286: getfield Lobby.gs LGameSparker;
      // 4289: getfield GameSparker.snfmm LSmenu;
      // 428c: invokevirtual Smenu.getSelectedIndex ()I
      // 428f: bipush 27
      // 4291: iadd
      // 4292: if_icmpne 429a
      // 4295: goto 42bf
      // 429a: aload 0
      // 429b: aload 0
      // 429c: getfield Lobby.gs LGameSparker;
      // 429f: getfield GameSparker.snfmm LSmenu;
      // 42a2: invokevirtual Smenu.getSelectedIndex ()I
      // 42a5: bipush 27
      // 42a7: iadd
      // 42a8: putfield Lobby.loadstage I
      // 42ab: aload 0
      // 42ac: aload 0
      // 42ad: getfield Lobby.loadstage I
      // 42b0: putfield Lobby.gstage I
      // 42b3: aload 0
      // 42b4: getfield Lobby.gs LGameSparker;
      // 42b7: invokevirtual GameSparker.requestFocus ()V
      // 42ba: goto 42bf
      // 42bf: aload 0
      // 42c0: getfield Lobby.gs LGameSparker;
      // 42c3: getfield GameSparker.sgame LSmenu;
      // 42c6: invokevirtual Smenu.getSelectedIndex ()I
      // 42c9: bipush 1
      // 42ca: if_icmpeq 42d2
      // 42cd: goto 437b
      // 42d2: aload 0
      // 42d3: getfield Lobby.gs LGameSparker;
      // 42d6: getfield GameSparker.snfm2 LSmenu;
      // 42d9: invokevirtual Smenu.isShowing ()Z
      // 42dc: ifeq 42e4
      // 42df: goto 430a
      // 42e4: aload 0
      // 42e5: getfield Lobby.gs LGameSparker;
      // 42e8: getfield GameSparker.snfm2 LSmenu;
      // 42eb: invokevirtual Smenu.show ()V
      // 42ee: aload 0
      // 42ef: getfield Lobby.loadstage I
      // 42f2: ifeq 42fa
      // 42f5: goto 430a
      // 42fa: aload 0
      // 42fb: getfield Lobby.gs LGameSparker;
      // 42fe: getfield GameSparker.snfm2 LSmenu;
      // 4301: bipush 0
      // 4302: invokevirtual Smenu.select (I)V
      // 4305: goto 430a
      // 430a: aload 0
      // 430b: getfield Lobby.gs LGameSparker;
      // 430e: getfield GameSparker.snfm2 LSmenu;
      // 4311: iload 18
      // 4313: bipush 105
      // 4315: invokevirtual Smenu.move (II)V
      // 4318: aload 0
      // 4319: getfield Lobby.gs LGameSparker;
      // 431c: getfield GameSparker.snfmm LSmenu;
      // 431f: invokevirtual Smenu.isShowing ()Z
      // 4322: ifne 432a
      // 4325: goto 4339
      // 432a: aload 0
      // 432b: getfield Lobby.gs LGameSparker;
      // 432e: getfield GameSparker.snfmm LSmenu;
      // 4331: invokevirtual Smenu.hide ()V
      // 4334: goto 4339
      // 4339: aload 0
      // 433a: getfield Lobby.gs LGameSparker;
      // 433d: getfield GameSparker.snfm1 LSmenu;
      // 4340: invokevirtual Smenu.isShowing ()Z
      // 4343: ifne 434b
      // 4346: goto 435a
      // 434b: aload 0
      // 434c: getfield Lobby.gs LGameSparker;
      // 434f: getfield GameSparker.snfm1 LSmenu;
      // 4352: invokevirtual Smenu.hide ()V
      // 4355: goto 435a
      // 435a: aload 0
      // 435b: getfield Lobby.gs LGameSparker;
      // 435e: getfield GameSparker.mstgs LSmenu;
      // 4361: invokevirtual Smenu.isShowing ()Z
      // 4364: ifne 436c
      // 4367: goto 437b
      // 436c: aload 0
      // 436d: getfield Lobby.gs LGameSparker;
      // 4370: getfield GameSparker.mstgs LSmenu;
      // 4373: invokevirtual Smenu.hide ()V
      // 4376: goto 437b
      // 437b: aload 0
      // 437c: getfield Lobby.gs LGameSparker;
      // 437f: getfield GameSparker.sgame LSmenu;
      // 4382: invokevirtual Smenu.getSelectedIndex ()I
      // 4385: bipush 1
      // 4386: if_icmpeq 438e
      // 4389: goto 43de
      // 438e: aload 0
      // 438f: getfield Lobby.gs LGameSparker;
      // 4392: getfield GameSparker.snfm2 LSmenu;
      // 4395: invokevirtual Smenu.getSelectedIndex ()I
      // 4398: ifne 43a0
      // 439b: goto 43de
      // 43a0: aload 0
      // 43a1: getfield Lobby.gstage I
      // 43a4: aload 0
      // 43a5: getfield Lobby.gs LGameSparker;
      // 43a8: getfield GameSparker.snfm2 LSmenu;
      // 43ab: invokevirtual Smenu.getSelectedIndex ()I
      // 43ae: bipush 10
      // 43b0: iadd
      // 43b1: if_icmpne 43b9
      // 43b4: goto 43de
      // 43b9: aload 0
      // 43ba: aload 0
      // 43bb: getfield Lobby.gs LGameSparker;
      // 43be: getfield GameSparker.snfm2 LSmenu;
      // 43c1: invokevirtual Smenu.getSelectedIndex ()I
      // 43c4: bipush 10
      // 43c6: iadd
      // 43c7: putfield Lobby.loadstage I
      // 43ca: aload 0
      // 43cb: aload 0
      // 43cc: getfield Lobby.loadstage I
      // 43cf: putfield Lobby.gstage I
      // 43d2: aload 0
      // 43d3: getfield Lobby.gs LGameSparker;
      // 43d6: invokevirtual GameSparker.requestFocus ()V
      // 43d9: goto 43de
      // 43de: aload 0
      // 43df: getfield Lobby.gs LGameSparker;
      // 43e2: getfield GameSparker.sgame LSmenu;
      // 43e5: invokevirtual Smenu.getSelectedIndex ()I
      // 43e8: bipush 2
      // 43e9: if_icmpeq 43f1
      // 43ec: goto 449a
      // 43f1: aload 0
      // 43f2: getfield Lobby.gs LGameSparker;
      // 43f5: getfield GameSparker.snfm1 LSmenu;
      // 43f8: invokevirtual Smenu.isShowing ()Z
      // 43fb: ifeq 4403
      // 43fe: goto 4429
      // 4403: aload 0
      // 4404: getfield Lobby.gs LGameSparker;
      // 4407: getfield GameSparker.snfm1 LSmenu;
      // 440a: invokevirtual Smenu.show ()V
      // 440d: aload 0
      // 440e: getfield Lobby.loadstage I
      // 4411: ifeq 4419
      // 4414: goto 4429
      // 4419: aload 0
      // 441a: getfield Lobby.gs LGameSparker;
      // 441d: getfield GameSparker.snfm1 LSmenu;
      // 4420: bipush 0
      // 4421: invokevirtual Smenu.select (I)V
      // 4424: goto 4429
      // 4429: aload 0
      // 442a: getfield Lobby.gs LGameSparker;
      // 442d: getfield GameSparker.snfm1 LSmenu;
      // 4430: iload 18
      // 4432: bipush 105
      // 4434: invokevirtual Smenu.move (II)V
      // 4437: aload 0
      // 4438: getfield Lobby.gs LGameSparker;
      // 443b: getfield GameSparker.snfmm LSmenu;
      // 443e: invokevirtual Smenu.isShowing ()Z
      // 4441: ifne 4449
      // 4444: goto 4458
      // 4449: aload 0
      // 444a: getfield Lobby.gs LGameSparker;
      // 444d: getfield GameSparker.snfmm LSmenu;
      // 4450: invokevirtual Smenu.hide ()V
      // 4453: goto 4458
      // 4458: aload 0
      // 4459: getfield Lobby.gs LGameSparker;
      // 445c: getfield GameSparker.snfm2 LSmenu;
      // 445f: invokevirtual Smenu.isShowing ()Z
      // 4462: ifne 446a
      // 4465: goto 4479
      // 446a: aload 0
      // 446b: getfield Lobby.gs LGameSparker;
      // 446e: getfield GameSparker.snfm2 LSmenu;
      // 4471: invokevirtual Smenu.hide ()V
      // 4474: goto 4479
      // 4479: aload 0
      // 447a: getfield Lobby.gs LGameSparker;
      // 447d: getfield GameSparker.mstgs LSmenu;
      // 4480: invokevirtual Smenu.isShowing ()Z
      // 4483: ifne 448b
      // 4486: goto 449a
      // 448b: aload 0
      // 448c: getfield Lobby.gs LGameSparker;
      // 448f: getfield GameSparker.mstgs LSmenu;
      // 4492: invokevirtual Smenu.hide ()V
      // 4495: goto 449a
      // 449a: aload 0
      // 449b: getfield Lobby.gs LGameSparker;
      // 449e: getfield GameSparker.sgame LSmenu;
      // 44a1: invokevirtual Smenu.getSelectedIndex ()I
      // 44a4: bipush 2
      // 44a5: if_icmpeq 44ad
      // 44a8: goto 44f7
      // 44ad: aload 0
      // 44ae: getfield Lobby.gs LGameSparker;
      // 44b1: getfield GameSparker.snfm1 LSmenu;
      // 44b4: invokevirtual Smenu.getSelectedIndex ()I
      // 44b7: ifne 44bf
      // 44ba: goto 44f7
      // 44bf: aload 0
      // 44c0: getfield Lobby.gstage I
      // 44c3: aload 0
      // 44c4: getfield Lobby.gs LGameSparker;
      // 44c7: getfield GameSparker.snfm1 LSmenu;
      // 44ca: invokevirtual Smenu.getSelectedIndex ()I
      // 44cd: if_icmpne 44d5
      // 44d0: goto 44f7
      // 44d5: aload 0
      // 44d6: aload 0
      // 44d7: getfield Lobby.gs LGameSparker;
      // 44da: getfield GameSparker.snfm1 LSmenu;
      // 44dd: invokevirtual Smenu.getSelectedIndex ()I
      // 44e0: putfield Lobby.loadstage I
      // 44e3: aload 0
      // 44e4: aload 0
      // 44e5: getfield Lobby.loadstage I
      // 44e8: putfield Lobby.gstage I
      // 44eb: aload 0
      // 44ec: getfield Lobby.gs LGameSparker;
      // 44ef: invokevirtual GameSparker.requestFocus ()V
      // 44f2: goto 44f7
      // 44f7: aload 0
      // 44f8: getfield Lobby.gs LGameSparker;
      // 44fb: getfield GameSparker.sgame LSmenu;
      // 44fe: invokevirtual Smenu.getSelectedIndex ()I
      // 4501: bipush 3
      // 4502: if_icmpeq 450a
      // 4505: goto 45b3
      // 450a: aload 0
      // 450b: getfield Lobby.gs LGameSparker;
      // 450e: getfield GameSparker.mstgs LSmenu;
      // 4511: invokevirtual Smenu.isShowing ()Z
      // 4514: ifeq 451c
      // 4517: goto 4542
      // 451c: aload 0
      // 451d: getfield Lobby.gs LGameSparker;
      // 4520: getfield GameSparker.mstgs LSmenu;
      // 4523: invokevirtual Smenu.show ()V
      // 4526: aload 0
      // 4527: getfield Lobby.loadstage I
      // 452a: ifeq 4532
      // 452d: goto 4542
      // 4532: aload 0
      // 4533: getfield Lobby.gs LGameSparker;
      // 4536: getfield GameSparker.mstgs LSmenu;
      // 4539: bipush 0
      // 453a: invokevirtual Smenu.select (I)V
      // 453d: goto 4542
      // 4542: aload 0
      // 4543: getfield Lobby.gs LGameSparker;
      // 4546: getfield GameSparker.mstgs LSmenu;
      // 4549: iload 18
      // 454b: bipush 105
      // 454d: invokevirtual Smenu.move (II)V
      // 4550: aload 0
      // 4551: getfield Lobby.gs LGameSparker;
      // 4554: getfield GameSparker.snfmm LSmenu;
      // 4557: invokevirtual Smenu.isShowing ()Z
      // 455a: ifne 4562
      // 455d: goto 4571
      // 4562: aload 0
      // 4563: getfield Lobby.gs LGameSparker;
      // 4566: getfield GameSparker.snfmm LSmenu;
      // 4569: invokevirtual Smenu.hide ()V
      // 456c: goto 4571
      // 4571: aload 0
      // 4572: getfield Lobby.gs LGameSparker;
      // 4575: getfield GameSparker.snfm1 LSmenu;
      // 4578: invokevirtual Smenu.isShowing ()Z
      // 457b: ifne 4583
      // 457e: goto 4592
      // 4583: aload 0
      // 4584: getfield Lobby.gs LGameSparker;
      // 4587: getfield GameSparker.snfm1 LSmenu;
      // 458a: invokevirtual Smenu.hide ()V
      // 458d: goto 4592
      // 4592: aload 0
      // 4593: getfield Lobby.gs LGameSparker;
      // 4596: getfield GameSparker.snfm2 LSmenu;
      // 4599: invokevirtual Smenu.isShowing ()Z
      // 459c: ifne 45a4
      // 459f: goto 45b3
      // 45a4: aload 0
      // 45a5: getfield Lobby.gs LGameSparker;
      // 45a8: getfield GameSparker.snfm2 LSmenu;
      // 45ab: invokevirtual Smenu.hide ()V
      // 45ae: goto 45b3
      // 45b3: aload 0
      // 45b4: getfield Lobby.remstage I
      // 45b7: bipush 2
      // 45b8: if_icmpne 45c0
      // 45bb: goto 467d
      // 45c0: aload 0
      // 45c1: getfield Lobby.gs LGameSparker;
      // 45c4: getfield GameSparker.sgame LSmenu;
      // 45c7: invokevirtual Smenu.getSelectedIndex ()I
      // 45ca: bipush 3
      // 45cb: if_icmpeq 45d3
      // 45ce: goto 4667
      // 45d3: aload 0
      // 45d4: getfield Lobby.gs LGameSparker;
      // 45d7: getfield GameSparker.mstgs LSmenu;
      // 45da: invokevirtual Smenu.getSelectedIndex ()I
      // 45dd: ifne 45e5
      // 45e0: goto 4667
      // 45e5: aload 0
      // 45e6: getfield Lobby.gstage I
      // 45e9: aload 0
      // 45ea: getfield Lobby.gs LGameSparker;
      // 45ed: getfield GameSparker.mstgs LSmenu;
      // 45f0: invokevirtual Smenu.getSelectedIndex ()I
      // 45f3: bipush 100
      // 45f5: iadd
      // 45f6: if_icmpne 45fe
      // 45f9: goto 4628
      // 45fe: aload 0
      // 45ff: aload 0
      // 4600: getfield Lobby.gs LGameSparker;
      // 4603: getfield GameSparker.mstgs LSmenu;
      // 4606: invokevirtual Smenu.getSelectedIndex ()I
      // 4609: bipush 100
      // 460b: iadd
      // 460c: putfield Lobby.loadstage I
      // 460f: aload 0
      // 4610: aload 0
      // 4611: getfield Lobby.loadstage I
      // 4614: putfield Lobby.gstage I
      // 4617: aload 0
      // 4618: getfield Lobby.gs LGameSparker;
      // 461b: invokevirtual GameSparker.requestFocus ()V
      // 461e: aload 0
      // 461f: bipush 0
      // 4620: putfield Lobby.remstage I
      // 4623: goto 4628
      // 4628: aload 0
      // 4629: getfield Lobby.loadstage I
      // 462c: ifle 4634
      // 462f: goto 467d
      // 4634: aload 0
      // 4635: getfield Lobby.remstage I
      // 4638: ifeq 4640
      // 463b: goto 467d
      // 4640: aload 0
      // 4641: getfield Lobby.xt LxtGraphics;
      // 4644: bipush 1
      // 4645: aconst_null
      // 4646: ldc_w "X"
      // 4649: sipush 674
      // 464c: sipush 136
      // 464f: iload 1
      // 4650: iload 2
      // 4651: iload 3
      // 4652: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // 4655: ifne 465d
      // 4658: goto 467d
      // 465d: aload 0
      // 465e: bipush 1
      // 465f: putfield Lobby.remstage I
      // 4662: goto 467d
      // 4667: aload 0
      // 4668: getfield Lobby.remstage I
      // 466b: ifne 4673
      // 466e: goto 467d
      // 4673: aload 0
      // 4674: bipush 0
      // 4675: putfield Lobby.remstage I
      // 4678: goto 467d
      // 467d: aload 0
      // 467e: getfield Lobby.gs LGameSparker;
      // 4681: getfield GameSparker.sgame LSmenu;
      // 4684: invokevirtual Smenu.getSelectedIndex ()I
      // 4687: bipush 4
      // 4688: if_icmpge 4690
      // 468b: goto 4739
      // 4690: aload 0
      // 4691: getfield Lobby.gs LGameSparker;
      // 4694: getfield GameSparker.mstgs LSmenu;
      // 4697: invokevirtual Smenu.isShowing ()Z
      // 469a: ifeq 46a2
      // 469d: goto 46c8
      // 46a2: aload 0
      // 46a3: getfield Lobby.gs LGameSparker;
      // 46a6: getfield GameSparker.mstgs LSmenu;
      // 46a9: invokevirtual Smenu.show ()V
      // 46ac: aload 0
      // 46ad: getfield Lobby.loadstage I
      // 46b0: ifeq 46b8
      // 46b3: goto 46c8
      // 46b8: aload 0
      // 46b9: getfield Lobby.gs LGameSparker;
      // 46bc: getfield GameSparker.mstgs LSmenu;
      // 46bf: bipush 0
      // 46c0: invokevirtual Smenu.select (I)V
      // 46c3: goto 46c8
      // 46c8: aload 0
      // 46c9: getfield Lobby.gs LGameSparker;
      // 46cc: getfield GameSparker.mstgs LSmenu;
      // 46cf: iload 18
      // 46d1: bipush 105
      // 46d3: invokevirtual Smenu.move (II)V
      // 46d6: aload 0
      // 46d7: getfield Lobby.gs LGameSparker;
      // 46da: getfield GameSparker.snfmm LSmenu;
      // 46dd: invokevirtual Smenu.isShowing ()Z
      // 46e0: ifne 46e8
      // 46e3: goto 46f7
      // 46e8: aload 0
      // 46e9: getfield Lobby.gs LGameSparker;
      // 46ec: getfield GameSparker.snfmm LSmenu;
      // 46ef: invokevirtual Smenu.hide ()V
      // 46f2: goto 46f7
      // 46f7: aload 0
      // 46f8: getfield Lobby.gs LGameSparker;
      // 46fb: getfield GameSparker.snfm1 LSmenu;
      // 46fe: invokevirtual Smenu.isShowing ()Z
      // 4701: ifne 4709
      // 4704: goto 4718
      // 4709: aload 0
      // 470a: getfield Lobby.gs LGameSparker;
      // 470d: getfield GameSparker.snfm1 LSmenu;
      // 4710: invokevirtual Smenu.hide ()V
      // 4713: goto 4718
      // 4718: aload 0
      // 4719: getfield Lobby.gs LGameSparker;
      // 471c: getfield GameSparker.snfm2 LSmenu;
      // 471f: invokevirtual Smenu.isShowing ()Z
      // 4722: ifne 472a
      // 4725: goto 4739
      // 472a: aload 0
      // 472b: getfield Lobby.gs LGameSparker;
      // 472e: getfield GameSparker.snfm2 LSmenu;
      // 4731: invokevirtual Smenu.hide ()V
      // 4734: goto 4739
      // 4739: aload 0
      // 473a: getfield Lobby.gs LGameSparker;
      // 473d: getfield GameSparker.sgame LSmenu;
      // 4740: invokevirtual Smenu.getSelectedIndex ()I
      // 4743: bipush 4
      // 4744: if_icmpge 474c
      // 4747: goto 479c
      // 474c: aload 0
      // 474d: getfield Lobby.gs LGameSparker;
      // 4750: getfield GameSparker.mstgs LSmenu;
      // 4753: invokevirtual Smenu.getSelectedIndex ()I
      // 4756: ifne 475e
      // 4759: goto 479c
      // 475e: aload 0
      // 475f: getfield Lobby.gstage I
      // 4762: aload 0
      // 4763: getfield Lobby.gs LGameSparker;
      // 4766: getfield GameSparker.mstgs LSmenu;
      // 4769: invokevirtual Smenu.getSelectedIndex ()I
      // 476c: bipush 100
      // 476e: iadd
      // 476f: if_icmpne 4777
      // 4772: goto 479c
      // 4777: aload 0
      // 4778: aload 0
      // 4779: getfield Lobby.gs LGameSparker;
      // 477c: getfield GameSparker.mstgs LSmenu;
      // 477f: invokevirtual Smenu.getSelectedIndex ()I
      // 4782: bipush 100
      // 4784: iadd
      // 4785: putfield Lobby.loadstage I
      // 4788: aload 0
      // 4789: aload 0
      // 478a: getfield Lobby.loadstage I
      // 478d: putfield Lobby.gstage I
      // 4790: aload 0
      // 4791: getfield Lobby.gs LGameSparker;
      // 4794: invokevirtual GameSparker.requestFocus ()V
      // 4797: goto 479c
      // 479c: aload 0
      // 479d: getfield Lobby.loadstage I
      // 47a0: ifgt 47a8
      // 47a3: goto 4806
      // 47a8: aload 0
      // 47a9: getfield Lobby.remstage I
      // 47ac: ifeq 47b4
      // 47af: goto 4806
      // 47b4: aload 0
      // 47b5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 47b8: new java/awt/Color
      // 47bb: dup
      // 47bc: bipush 0
      // 47bd: bipush 0
      // 47be: bipush 0
      // 47bf: invokespecial java/awt/Color.<init> (III)V
      // 47c2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 47c5: aload 0
      // 47c6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 47c9: new java/awt/Font
      // 47cc: dup
      // 47cd: ldc_w "Arial"
      // 47d0: bipush 1
      // 47d1: bipush 12
      // 47d3: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 47d6: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 47d9: aload 0
      // 47da: aload 0
      // 47db: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 47de: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 47e1: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 47e4: aload 0
      // 47e5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 47e8: ldc_w "Loading stage, please wait..."
      // 47eb: sipush 472
      // 47ee: aload 0
      // 47ef: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 47f2: ldc_w "Loading Stage, please wait..."
      // 47f5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 47f8: bipush 2
      // 47f9: idiv
      // 47fa: isub
      // 47fb: sipush 165
      // 47fe: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4801: goto 4806
      // 4806: aload 0
      // 4807: getfield Lobby.gs LGameSparker;
      // 480a: getfield GameSparker.sgame LSmenu;
      // 480d: invokevirtual Smenu.getSelectedIndex ()I
      // 4810: bipush 3
      // 4811: if_icmpge 4819
      // 4814: goto 48ee
      // 4819: aload 0
      // 481a: getfield Lobby.xt LxtGraphics;
      // 481d: getfield xtGraphics.logged Z
      // 4820: ifeq 4828
      // 4823: goto 48ee
      // 4828: aload 0
      // 4829: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 482c: new java/awt/Color
      // 482f: dup
      // 4830: bipush 0
      // 4831: bipush 0
      // 4832: bipush 0
      // 4833: invokespecial java/awt/Color.<init> (III)V
      // 4836: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4839: aload 0
      // 483a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 483d: new java/awt/Font
      // 4840: dup
      // 4841: ldc_w "Arial"
      // 4844: bipush 1
      // 4845: bipush 12
      // 4847: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 484a: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 484d: aload 0
      // 484e: aload 0
      // 484f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4852: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4855: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4858: aload 0
      // 4859: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 485c: ldc_w "You are currently using a trial account."
      // 485f: sipush 472
      // 4862: aload 0
      // 4863: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4866: ldc_w "You are currently using a trial account."
      // 4869: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 486c: bipush 2
      // 486d: idiv
      // 486e: isub
      // 486f: sipush 155
      // 4872: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4875: aload 0
      // 4876: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4879: ldc_w "You need to upgrade your account to access and publish custom stages!"
      // 487c: sipush 472
      // 487f: aload 0
      // 4880: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4883: ldc_w "You need to upgrade your account to access and publish custom stages!"
      // 4886: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4889: bipush 2
      // 488a: idiv
      // 488b: isub
      // 488c: sipush 175
      // 488f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4892: aload 0
      // 4893: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4896: aload 0
      // 4897: sipush 200
      // 489a: sipush 200
      // 489d: sipush 200
      // 48a0: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 48a3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 48a6: aload 0
      // 48a7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 48aa: sipush 382
      // 48ad: sipush 185
      // 48b0: sipush 180
      // 48b3: bipush 50
      // 48b5: bipush 20
      // 48b7: bipush 20
      // 48b9: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 48bc: aload 0
      // 48bd: aload 0
      // 48be: getfield Lobby.xt LxtGraphics;
      // 48c1: getfield xtGraphics.upgrade Ljava/awt/Image;
      // 48c4: sipush 472
      // 48c7: sipush 210
      // 48ca: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // 48cd: aload 0
      // 48ce: getfield Lobby.gs LGameSparker;
      // 48d1: getfield GameSparker.slaps LSmenu;
      // 48d4: invokevirtual Smenu.isShowing ()Z
      // 48d7: ifne 48df
      // 48da: goto 4ce6
      // 48df: aload 0
      // 48e0: getfield Lobby.gs LGameSparker;
      // 48e3: getfield GameSparker.slaps LSmenu;
      // 48e6: invokevirtual Smenu.hide ()V
      // 48e9: goto 4ce6
      // 48ee: aload 0
      // 48ef: getfield Lobby.loadstage I
      // 48f2: iflt 48fa
      // 48f5: goto 4a36
      // 48fa: aload 0
      // 48fb: getfield Lobby.remstage I
      // 48fe: ifeq 4906
      // 4901: goto 4a36
      // 4906: aload 0
      // 4907: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 490a: new java/awt/Color
      // 490d: dup
      // 490e: bipush 0
      // 490f: bipush 0
      // 4910: bipush 0
      // 4911: invokespecial java/awt/Color.<init> (III)V
      // 4914: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4917: aload 0
      // 4918: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 491b: new java/awt/Font
      // 491e: dup
      // 491f: ldc_w "Arial"
      // 4922: bipush 1
      // 4923: bipush 12
      // 4925: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4928: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 492b: aload 0
      // 492c: aload 0
      // 492d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4930: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4933: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4936: aload 0
      // 4937: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 493a: new java/lang/StringBuilder
      // 493d: dup
      // 493e: invokespecial java/lang/StringBuilder.<init> ()V
      // 4941: ldc ""
      // 4943: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4946: aload 0
      // 4947: getfield Lobby.gstagename Ljava/lang/String;
      // 494a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 494d: ldc_w "  -  Laps:              "
      // 4950: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4953: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 4956: sipush 472
      // 4959: aload 0
      // 495a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 495d: new java/lang/StringBuilder
      // 4960: dup
      // 4961: invokespecial java/lang/StringBuilder.<init> ()V
      // 4964: ldc ""
      // 4966: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4969: aload 0
      // 496a: getfield Lobby.gstagename Ljava/lang/String;
      // 496d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4970: ldc_w "  -  Laps:              "
      // 4973: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4976: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 4979: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 497c: bipush 2
      // 497d: idiv
      // 497e: isub
      // 497f: sipush 155
      // 4982: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4985: aload 0
      // 4986: getfield Lobby.gs LGameSparker;
      // 4989: getfield GameSparker.slaps LSmenu;
      // 498c: invokevirtual Smenu.isShowing ()Z
      // 498f: ifeq 4997
      // 4992: goto 49b6
      // 4997: aload 0
      // 4998: getfield Lobby.gs LGameSparker;
      // 499b: getfield GameSparker.slaps LSmenu;
      // 499e: invokevirtual Smenu.show ()V
      // 49a1: aload 0
      // 49a2: getfield Lobby.gs LGameSparker;
      // 49a5: getfield GameSparker.slaps LSmenu;
      // 49a8: aload 0
      // 49a9: getfield Lobby.gstagelaps I
      // 49ac: bipush 1
      // 49ad: isub
      // 49ae: invokevirtual Smenu.select (I)V
      // 49b1: goto 49b6
      // 49b6: aload 0
      // 49b7: getfield Lobby.gs LGameSparker;
      // 49ba: getfield GameSparker.slaps LSmenu;
      // 49bd: sipush 472
      // 49c0: aload 0
      // 49c1: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 49c4: new java/lang/StringBuilder
      // 49c7: dup
      // 49c8: invokespecial java/lang/StringBuilder.<init> ()V
      // 49cb: ldc ""
      // 49cd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 49d0: aload 0
      // 49d1: getfield Lobby.gstagename Ljava/lang/String;
      // 49d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 49d7: ldc_w "  -  Laps:              "
      // 49da: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 49dd: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 49e0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 49e3: bipush 2
      // 49e4: idiv
      // 49e5: iadd
      // 49e6: bipush 35
      // 49e8: isub
      // 49e9: sipush 138
      // 49ec: invokevirtual Smenu.move (II)V
      // 49ef: aload 0
      // 49f0: getfield Lobby.gs LGameSparker;
      // 49f3: getfield GameSparker.slaps LSmenu;
      // 49f6: invokevirtual Smenu.getSelectedIndex ()I
      // 49f9: aload 0
      // 49fa: getfield Lobby.gstagelaps I
      // 49fd: bipush 1
      // 49fe: isub
      // 49ff: if_icmpne 4a07
      // 4a02: goto 4a23
      // 4a07: aload 0
      // 4a08: aload 0
      // 4a09: getfield Lobby.gs LGameSparker;
      // 4a0c: getfield GameSparker.slaps LSmenu;
      // 4a0f: invokevirtual Smenu.getSelectedIndex ()I
      // 4a12: bipush 1
      // 4a13: iadd
      // 4a14: putfield Lobby.gstagelaps I
      // 4a17: aload 0
      // 4a18: getfield Lobby.gs LGameSparker;
      // 4a1b: invokevirtual GameSparker.requestFocus ()V
      // 4a1e: goto 4a23
      // 4a23: aload 0
      // 4a24: ldc_w "    Preview Stage    "
      // 4a27: sipush 472
      // 4a2a: sipush 185
      // 4a2d: bipush 2
      // 4a2e: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 4a31: goto 4a57
      // 4a36: aload 0
      // 4a37: getfield Lobby.gs LGameSparker;
      // 4a3a: getfield GameSparker.slaps LSmenu;
      // 4a3d: invokevirtual Smenu.isShowing ()Z
      // 4a40: ifne 4a48
      // 4a43: goto 4a57
      // 4a48: aload 0
      // 4a49: getfield Lobby.gs LGameSparker;
      // 4a4c: getfield GameSparker.slaps LSmenu;
      // 4a4f: invokevirtual Smenu.hide ()V
      // 4a52: goto 4a57
      // 4a57: aload 0
      // 4a58: getfield Lobby.remstage I
      // 4a5b: bipush 3
      // 4a5c: if_icmpeq 4a64
      // 4a5f: goto 4b0b
      // 4a64: aload 0
      // 4a65: getfield Lobby.loadstage I
      // 4a68: iflt 4a70
      // 4a6b: goto 4a83
      // 4a70: aload 0
      // 4a71: ldc_w "    Preview Stage    "
      // 4a74: sipush 472
      // 4a77: sipush -160
      // 4a7a: bipush 2
      // 4a7b: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 4a7e: goto 4a83
      // 4a83: aload 0
      // 4a84: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4a87: new java/awt/Color
      // 4a8a: dup
      // 4a8b: bipush 0
      // 4a8c: bipush 0
      // 4a8d: bipush 0
      // 4a8e: invokespecial java/awt/Color.<init> (III)V
      // 4a91: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4a94: aload 0
      // 4a95: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4a98: new java/awt/Font
      // 4a9b: dup
      // 4a9c: ldc_w "Arial"
      // 4a9f: bipush 1
      // 4aa0: bipush 13
      // 4aa2: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4aa5: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4aa8: aload 0
      // 4aa9: aload 0
      // 4aaa: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4aad: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4ab0: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4ab3: aload 0
      // 4ab4: getfield Lobby.xt LxtGraphics;
      // 4ab7: sipush 135
      // 4aba: bipush 75
      // 4abc: invokevirtual xtGraphics.drawlprom (II)V
      // 4abf: aload 0
      // 4ac0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4ac3: ldc_w "Failed to remove stage, server error, please try again later."
      // 4ac6: sipush 472
      // 4ac9: aload 0
      // 4aca: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4acd: ldc_w "Failed to remove stage, server error, please try again later."
      // 4ad0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4ad3: bipush 2
      // 4ad4: idiv
      // 4ad5: isub
      // 4ad6: sipush 155
      // 4ad9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4adc: aload 0
      // 4add: getfield Lobby.xt LxtGraphics;
      // 4ae0: bipush 1
      // 4ae1: aconst_null
      // 4ae2: ldc_w " OK "
      // 4ae5: sipush 451
      // 4ae8: sipush 175
      // 4aeb: iload 1
      // 4aec: iload 2
      // 4aed: iload 3
      // 4aee: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // 4af1: ifne 4af9
      // 4af4: goto 4b0b
      // 4af9: aload 0
      // 4afa: bipush 0
      // 4afb: putfield Lobby.remstage I
      // 4afe: aload 0
      // 4aff: getfield Lobby.gs LGameSparker;
      // 4b02: bipush 0
      // 4b03: putfield GameSparker.mouses I
      // 4b06: goto 4b0b
      // 4b0b: aload 0
      // 4b0c: getfield Lobby.remstage I
      // 4b0f: bipush 2
      // 4b10: if_icmpeq 4b18
      // 4b13: goto 4bd5
      // 4b18: aload 0
      // 4b19: getfield Lobby.loadstage I
      // 4b1c: iflt 4b24
      // 4b1f: goto 4b37
      // 4b24: aload 0
      // 4b25: ldc_w "    Preview Stage    "
      // 4b28: sipush 472
      // 4b2b: sipush -160
      // 4b2e: bipush 2
      // 4b2f: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 4b32: goto 4b37
      // 4b37: aload 0
      // 4b38: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4b3b: new java/awt/Color
      // 4b3e: dup
      // 4b3f: bipush 0
      // 4b40: bipush 0
      // 4b41: bipush 0
      // 4b42: invokespecial java/awt/Color.<init> (III)V
      // 4b45: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4b48: aload 0
      // 4b49: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4b4c: new java/awt/Font
      // 4b4f: dup
      // 4b50: ldc_w "Arial"
      // 4b53: bipush 1
      // 4b54: bipush 13
      // 4b56: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4b59: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4b5c: aload 0
      // 4b5d: aload 0
      // 4b5e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4b61: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4b64: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4b67: aload 0
      // 4b68: getfield Lobby.xt LxtGraphics;
      // 4b6b: sipush 135
      // 4b6e: bipush 75
      // 4b70: invokevirtual xtGraphics.drawlprom (II)V
      // 4b73: aload 0
      // 4b74: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4b77: ldc_w "Removing stage from your account..."
      // 4b7a: sipush 472
      // 4b7d: aload 0
      // 4b7e: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4b81: ldc_w "Removing stage from your account..."
      // 4b84: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4b87: bipush 2
      // 4b88: idiv
      // 4b89: isub
      // 4b8a: sipush 175
      // 4b8d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4b90: aload 0
      // 4b91: getfield Lobby.cd LCarDefine;
      // 4b94: getfield CarDefine.staction I
      // 4b97: ifeq 4b9f
      // 4b9a: goto 4bb3
      // 4b9f: aload 0
      // 4ba0: bipush 0
      // 4ba1: putfield Lobby.gstage I
      // 4ba4: aload 0
      // 4ba5: bipush 0
      // 4ba6: putfield Lobby.loadstage I
      // 4ba9: aload 0
      // 4baa: bipush 0
      // 4bab: putfield Lobby.remstage I
      // 4bae: goto 4bb3
      // 4bb3: aload 0
      // 4bb4: getfield Lobby.cd LCarDefine;
      // 4bb7: getfield CarDefine.staction I
      // 4bba: bipush -1
      // 4bbb: if_icmpeq 4bc3
      // 4bbe: goto 4bd5
      // 4bc3: aload 0
      // 4bc4: bipush 3
      // 4bc5: putfield Lobby.remstage I
      // 4bc8: aload 0
      // 4bc9: getfield Lobby.gs LGameSparker;
      // 4bcc: bipush 0
      // 4bcd: putfield GameSparker.mouses I
      // 4bd0: goto 4bd5
      // 4bd5: aload 0
      // 4bd6: getfield Lobby.remstage I
      // 4bd9: bipush 1
      // 4bda: if_icmpeq 4be2
      // 4bdd: goto 4cd8
      // 4be2: aload 0
      // 4be3: getfield Lobby.loadstage I
      // 4be6: iflt 4bee
      // 4be9: goto 4c01
      // 4bee: aload 0
      // 4bef: ldc_w "    Preview Stage    "
      // 4bf2: sipush 472
      // 4bf5: sipush -160
      // 4bf8: bipush 2
      // 4bf9: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 4bfc: goto 4c01
      // 4c01: aload 0
      // 4c02: getfield Lobby.xt LxtGraphics;
      // 4c05: sipush 135
      // 4c08: bipush 75
      // 4c0a: invokevirtual xtGraphics.drawlprom (II)V
      // 4c0d: aload 0
      // 4c0e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4c11: new java/awt/Color
      // 4c14: dup
      // 4c15: bipush 0
      // 4c16: bipush 0
      // 4c17: bipush 0
      // 4c18: invokespecial java/awt/Color.<init> (III)V
      // 4c1b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4c1e: aload 0
      // 4c1f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4c22: new java/awt/Font
      // 4c25: dup
      // 4c26: ldc_w "Arial"
      // 4c29: bipush 1
      // 4c2a: bipush 13
      // 4c2c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4c2f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4c32: aload 0
      // 4c33: aload 0
      // 4c34: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4c37: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4c3a: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4c3d: aload 0
      // 4c3e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4c41: ldc_w "Remove this stage from your account?"
      // 4c44: sipush 472
      // 4c47: aload 0
      // 4c48: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4c4b: ldc_w "Remove this stage from your account?"
      // 4c4e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4c51: bipush 2
      // 4c52: idiv
      // 4c53: isub
      // 4c54: sipush 155
      // 4c57: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4c5a: aload 0
      // 4c5b: getfield Lobby.xt LxtGraphics;
      // 4c5e: bipush 1
      // 4c5f: aconst_null
      // 4c60: ldc_w " Yes "
      // 4c63: sipush 426
      // 4c66: sipush 175
      // 4c69: iload 1
      // 4c6a: iload 2
      // 4c6b: iload 3
      // 4c6c: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // 4c6f: ifne 4c77
      // 4c72: goto 4ca9
      // 4c77: aload 0
      // 4c78: bipush 2
      // 4c79: putfield Lobby.remstage I
      // 4c7c: aload 0
      // 4c7d: getfield Lobby.cd LCarDefine;
      // 4c80: aload 0
      // 4c81: getfield Lobby.gs LGameSparker;
      // 4c84: getfield GameSparker.mstgs LSmenu;
      // 4c87: invokevirtual Smenu.getSelectedItem ()Ljava/lang/String;
      // 4c8a: putfield CarDefine.onstage Ljava/lang/String;
      // 4c8d: aload 0
      // 4c8e: getfield Lobby.cd LCarDefine;
      // 4c91: bipush 1
      // 4c92: putfield CarDefine.staction I
      // 4c95: aload 0
      // 4c96: getfield Lobby.cd LCarDefine;
      // 4c99: invokevirtual CarDefine.sparkstageaction ()V
      // 4c9c: aload 0
      // 4c9d: getfield Lobby.gs LGameSparker;
      // 4ca0: bipush 0
      // 4ca1: putfield GameSparker.mouses I
      // 4ca4: goto 4ca9
      // 4ca9: aload 0
      // 4caa: getfield Lobby.xt LxtGraphics;
      // 4cad: bipush 1
      // 4cae: aconst_null
      // 4caf: ldc_w " No "
      // 4cb2: sipush 480
      // 4cb5: sipush 175
      // 4cb8: iload 1
      // 4cb9: iload 2
      // 4cba: iload 3
      // 4cbb: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // 4cbe: ifne 4cc6
      // 4cc1: goto 4cd8
      // 4cc6: aload 0
      // 4cc7: bipush 0
      // 4cc8: putfield Lobby.remstage I
      // 4ccb: aload 0
      // 4ccc: getfield Lobby.gs LGameSparker;
      // 4ccf: bipush 0
      // 4cd0: putfield GameSparker.mouses I
      // 4cd3: goto 4cd8
      // 4cd8: aload 0
      // 4cd9: ldc_w "   Next >   "
      // 4cdc: sipush 472
      // 4cdf: sipush 235
      // 4ce2: bipush 1
      // 4ce3: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 4ce6: goto 542c
      // 4ceb: aload 0
      // 4cec: getfield Lobby.inwab Z
      // 4cef: ifeq 4cf7
      // 4cf2: goto 4d38
      // 4cf7: aload 0
      // 4cf8: getfield Lobby.gs LGameSparker;
      // 4cfb: getfield GameSparker.sgame LSmenu;
      // 4cfe: invokevirtual Smenu.hide ()V
      // 4d01: aload 0
      // 4d02: getfield Lobby.gs LGameSparker;
      // 4d05: getfield GameSparker.mstgs LSmenu;
      // 4d08: invokevirtual Smenu.hide ()V
      // 4d0b: aload 0
      // 4d0c: getfield Lobby.gs LGameSparker;
      // 4d0f: getfield GameSparker.slaps LSmenu;
      // 4d12: invokevirtual Smenu.hide ()V
      // 4d15: aload 0
      // 4d16: getfield Lobby.gs LGameSparker;
      // 4d19: getfield GameSparker.snfm1 LSmenu;
      // 4d1c: invokevirtual Smenu.hide ()V
      // 4d1f: aload 0
      // 4d20: getfield Lobby.gs LGameSparker;
      // 4d23: getfield GameSparker.snfmm LSmenu;
      // 4d26: invokevirtual Smenu.hide ()V
      // 4d29: aload 0
      // 4d2a: getfield Lobby.gs LGameSparker;
      // 4d2d: getfield GameSparker.snfm2 LSmenu;
      // 4d30: invokevirtual Smenu.hide ()V
      // 4d33: goto 4d38
      // 4d38: aload 0
      // 4d39: getfield Lobby.xt LxtGraphics;
      // 4d3c: getfield xtGraphics.clan Ljava/lang/String;
      // 4d3f: ldc ""
      // 4d41: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 4d44: ifeq 4d4c
      // 4d47: goto 53bb
      // 4d4c: aload 0
      // 4d4d: getfield Lobby.inwab Z
      // 4d50: ifeq 4d58
      // 4d53: goto 4db9
      // 4d58: aload 0
      // 4d59: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4d5c: new java/awt/Color
      // 4d5f: dup
      // 4d60: bipush 0
      // 4d61: bipush 0
      // 4d62: bipush 0
      // 4d63: invokespecial java/awt/Color.<init> (III)V
      // 4d66: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4d69: aload 0
      // 4d6a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4d6d: new java/awt/Font
      // 4d70: dup
      // 4d71: ldc_w "Arial"
      // 4d74: bipush 1
      // 4d75: bipush 12
      // 4d77: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4d7a: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4d7d: aload 0
      // 4d7e: aload 0
      // 4d7f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4d82: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4d85: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4d88: aload 0
      // 4d89: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4d8c: ldc_w "Loading your clan's wars and battles, please wait..."
      // 4d8f: sipush 472
      // 4d92: aload 0
      // 4d93: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4d96: ldc_w "Loading your clan's wars and battles, please wait..."
      // 4d99: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4d9c: bipush 2
      // 4d9d: idiv
      // 4d9e: isub
      // 4d9f: sipush 155
      // 4da2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4da5: aload 0
      // 4da6: bipush 1
      // 4da7: putfield Lobby.loadwarb Z
      // 4daa: aload 0
      // 4dab: bipush 0
      // 4dac: putfield Lobby.warbsel I
      // 4daf: aload 0
      // 4db0: bipush 0
      // 4db1: putfield Lobby.cancreate I
      // 4db4: goto 5416
      // 4db9: aload 0
      // 4dba: getfield Lobby.gs LGameSparker;
      // 4dbd: getfield GameSparker.warb LSmenu;
      // 4dc0: invokevirtual Smenu.isShowing ()Z
      // 4dc3: ifeq 4dcb
      // 4dc6: goto 4dda
      // 4dcb: aload 0
      // 4dcc: getfield Lobby.gs LGameSparker;
      // 4dcf: getfield GameSparker.warb LSmenu;
      // 4dd2: invokevirtual Smenu.show ()V
      // 4dd5: goto 4dda
      // 4dda: aload 0
      // 4ddb: getfield Lobby.gs LGameSparker;
      // 4dde: getfield GameSparker.warb LSmenu;
      // 4de1: sipush 472
      // 4de4: aload 0
      // 4de5: getfield Lobby.gs LGameSparker;
      // 4de8: getfield GameSparker.warb LSmenu;
      // 4deb: getfield Smenu.w I
      // 4dee: bipush 2
      // 4def: idiv
      // 4df0: isub
      // 4df1: bipush 105
      // 4df3: invokevirtual Smenu.move (II)V
      // 4df6: aload 0
      // 4df7: getfield Lobby.gs LGameSparker;
      // 4dfa: getfield GameSparker.warb LSmenu;
      // 4dfd: getfield Smenu.sel I
      // 4e00: ifne 4e08
      // 4e03: goto 5416
      // 4e08: aload 0
      // 4e09: getfield Lobby.gs LGameSparker;
      // 4e0c: getfield GameSparker.warb LSmenu;
      // 4e0f: getfield Smenu.sel I
      // 4e12: aload 0
      // 4e13: getfield Lobby.warbsel I
      // 4e16: if_icmpne 4e1e
      // 4e19: goto 4ea6
      // 4e1e: aload 0
      // 4e1f: getfield Lobby.gb LGlobe;
      // 4e22: bipush 1
      // 4e23: putfield Globe.loadwbgames I
      // 4e26: aload 0
      // 4e27: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4e2a: new java/awt/Color
      // 4e2d: dup
      // 4e2e: bipush 0
      // 4e2f: bipush 0
      // 4e30: bipush 0
      // 4e31: invokespecial java/awt/Color.<init> (III)V
      // 4e34: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4e37: aload 0
      // 4e38: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4e3b: new java/awt/Font
      // 4e3e: dup
      // 4e3f: ldc_w "Arial"
      // 4e42: bipush 1
      // 4e43: bipush 12
      // 4e45: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4e48: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4e4b: aload 0
      // 4e4c: aload 0
      // 4e4d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4e50: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4e53: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4e56: aload 0
      // 4e57: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4e5a: ldc_w "Loading scheduled games, please wait..."
      // 4e5d: sipush 472
      // 4e60: aload 0
      // 4e61: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4e64: ldc_w "Loading scheduled games, please wait..."
      // 4e67: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4e6a: bipush 2
      // 4e6b: idiv
      // 4e6c: isub
      // 4e6d: sipush 165
      // 4e70: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4e73: aload 0
      // 4e74: aload 0
      // 4e75: getfield Lobby.gs LGameSparker;
      // 4e78: getfield GameSparker.warb LSmenu;
      // 4e7b: getfield Smenu.sel I
      // 4e7e: putfield Lobby.warbsel I
      // 4e81: aload 0
      // 4e82: getfield Lobby.gs LGameSparker;
      // 4e85: getfield GameSparker.vnpls LSmenu;
      // 4e88: bipush 0
      // 4e89: putfield Smenu.sel I
      // 4e8c: aload 0
      // 4e8d: getfield Lobby.gs LGameSparker;
      // 4e90: getfield GameSparker.vtyp LSmenu;
      // 4e93: bipush 0
      // 4e94: putfield Smenu.sel I
      // 4e97: aload 0
      // 4e98: bipush 0
      // 4e99: putfield Lobby.pgamesel I
      // 4e9c: aload 0
      // 4e9d: bipush 0
      // 4e9e: putfield Lobby.cancreate I
      // 4ea1: goto 5416
      // 4ea6: aload 0
      // 4ea7: getfield Lobby.gb LGlobe;
      // 4eaa: getfield Globe.loadwbgames I
      // 4ead: bipush 7
      // 4eaf: if_icmpeq 4eb7
      // 4eb2: goto 4f09
      // 4eb7: aload 0
      // 4eb8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4ebb: new java/awt/Color
      // 4ebe: dup
      // 4ebf: bipush 0
      // 4ec0: bipush 0
      // 4ec1: bipush 0
      // 4ec2: invokespecial java/awt/Color.<init> (III)V
      // 4ec5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4ec8: aload 0
      // 4ec9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4ecc: new java/awt/Font
      // 4ecf: dup
      // 4ed0: ldc_w "Arial"
      // 4ed3: bipush 1
      // 4ed4: bipush 12
      // 4ed6: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4ed9: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4edc: aload 0
      // 4edd: aload 0
      // 4ede: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4ee1: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4ee4: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4ee7: aload 0
      // 4ee8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4eeb: ldc_w "Redoing last game, please wait..."
      // 4eee: sipush 472
      // 4ef1: aload 0
      // 4ef2: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4ef5: ldc_w "Redoing last game, please wait..."
      // 4ef8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4efb: bipush 2
      // 4efc: idiv
      // 4efd: isub
      // 4efe: sipush 155
      // 4f01: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4f04: goto 4f09
      // 4f09: aload 0
      // 4f0a: getfield Lobby.gb LGlobe;
      // 4f0d: getfield Globe.loadwbgames I
      // 4f10: bipush 2
      // 4f11: if_icmpeq 4f19
      // 4f14: goto 5324
      // 4f19: aload 0
      // 4f1a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4f1d: new java/awt/Color
      // 4f20: dup
      // 4f21: bipush 0
      // 4f22: bipush 0
      // 4f23: bipush 0
      // 4f24: invokespecial java/awt/Color.<init> (III)V
      // 4f27: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 4f2a: aload 0
      // 4f2b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4f2e: new java/awt/Font
      // 4f31: dup
      // 4f32: ldc_w "Arial"
      // 4f35: bipush 1
      // 4f36: bipush 12
      // 4f38: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 4f3b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 4f3e: aload 0
      // 4f3f: aload 0
      // 4f40: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4f43: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 4f46: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4f49: aload 0
      // 4f4a: getfield Lobby.gs LGameSparker;
      // 4f4d: getfield GameSparker.wgame LSmenu;
      // 4f50: invokevirtual Smenu.getSelectedIndex ()I
      // 4f53: bipush 1
      // 4f54: if_icmpeq 4f5c
      // 4f57: goto 51dd
      // 4f5c: aload 0
      // 4f5d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 4f60: new java/lang/StringBuilder
      // 4f63: dup
      // 4f64: invokespecial java/lang/StringBuilder.<init> ()V
      // 4f67: ldc_w "[ "
      // 4f6a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4f6d: aload 0
      // 4f6e: getfield Lobby.gb LGlobe;
      // 4f71: getfield Globe.gameturndisp Ljava/lang/String;
      // 4f74: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4f77: ldc_w " ]"
      // 4f7a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4f7d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 4f80: sipush 472
      // 4f83: aload 0
      // 4f84: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 4f87: new java/lang/StringBuilder
      // 4f8a: dup
      // 4f8b: invokespecial java/lang/StringBuilder.<init> ()V
      // 4f8e: ldc_w "[ "
      // 4f91: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4f94: aload 0
      // 4f95: getfield Lobby.gb LGlobe;
      // 4f98: getfield Globe.gameturndisp Ljava/lang/String;
      // 4f9b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4f9e: ldc_w " ]"
      // 4fa1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 4fa4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 4fa7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 4faa: bipush 2
      // 4fab: idiv
      // 4fac: isub
      // 4fad: sipush 155
      // 4fb0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 4fb3: bipush 1
      // 4fb4: istore 17
      // 4fb6: sipush 472
      // 4fb9: aload 0
      // 4fba: getfield Lobby.gs LGameSparker;
      // 4fbd: getfield GameSparker.vnpls LSmenu;
      // 4fc0: getfield Smenu.w I
      // 4fc3: aload 0
      // 4fc4: getfield Lobby.gs LGameSparker;
      // 4fc7: getfield GameSparker.vtyp LSmenu;
      // 4fca: getfield Smenu.w I
      // 4fcd: iadd
      // 4fce: bipush 10
      // 4fd0: iadd
      // 4fd1: bipush 2
      // 4fd2: idiv
      // 4fd3: isub
      // 4fd4: istore 18
      // 4fd6: aload 0
      // 4fd7: getfield Lobby.gs LGameSparker;
      // 4fda: getfield GameSparker.vnpls LSmenu;
      // 4fdd: iload 18
      // 4fdf: sipush 168
      // 4fe2: invokevirtual Smenu.move (II)V
      // 4fe5: iload 18
      // 4fe7: aload 0
      // 4fe8: getfield Lobby.gs LGameSparker;
      // 4feb: getfield GameSparker.vnpls LSmenu;
      // 4fee: getfield Smenu.w I
      // 4ff1: bipush 10
      // 4ff3: iadd
      // 4ff4: iadd
      // 4ff5: istore 18
      // 4ff7: aload 0
      // 4ff8: getfield Lobby.gs LGameSparker;
      // 4ffb: getfield GameSparker.vtyp LSmenu;
      // 4ffe: iload 18
      // 5000: sipush 168
      // 5003: invokevirtual Smenu.move (II)V
      // 5006: aload 0
      // 5007: getfield Lobby.gb LGlobe;
      // 500a: getfield Globe.lwbwinner Ljava/lang/String;
      // 500d: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 5010: aload 0
      // 5011: getfield Lobby.xt LxtGraphics;
      // 5014: getfield xtGraphics.clan Ljava/lang/String;
      // 5017: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 501a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 501d: ifeq 5025
      // 5020: goto 512c
      // 5025: aload 0
      // 5026: getfield Lobby.gs LGameSparker;
      // 5029: getfield GameSparker.vnpls LSmenu;
      // 502c: getfield Smenu.sel I
      // 502f: ifne 5037
      // 5032: goto 51b6
      // 5037: aload 0
      // 5038: getfield Lobby.pgamesel I
      // 503b: aload 0
      // 503c: getfield Lobby.gs LGameSparker;
      // 503f: getfield GameSparker.vnpls LSmenu;
      // 5042: getfield Smenu.sel I
      // 5045: ineg
      // 5046: if_icmpne 504e
      // 5049: goto 507a
      // 504e: aload 0
      // 504f: aload 0
      // 5050: getfield Lobby.gb LGlobe;
      // 5053: getfield Globe.wbstages [Ljava/lang/String;
      // 5056: aload 0
      // 5057: getfield Lobby.gb LGlobe;
      // 505a: getfield Globe.gameturn I
      // 505d: aaload
      // 505e: putfield Lobby.gstagename Ljava/lang/String;
      // 5061: aload 0
      // 5062: bipush 0
      // 5063: putfield Lobby.cancreate I
      // 5066: aload 0
      // 5067: aload 0
      // 5068: getfield Lobby.gs LGameSparker;
      // 506b: getfield GameSparker.vnpls LSmenu;
      // 506e: getfield Smenu.sel I
      // 5071: ineg
      // 5072: putfield Lobby.pgamesel I
      // 5075: goto 507a
      // 507a: aload 0
      // 507b: getfield Lobby.cancreate I
      // 507e: bipush 1
      // 507f: if_icmpeq 5087
      // 5082: goto 5091
      // 5087: aload 0
      // 5088: bipush 2
      // 5089: putfield Lobby.cancreate I
      // 508c: goto 5091
      // 5091: aload 0
      // 5092: getfield Lobby.cancreate I
      // 5095: ifeq 509d
      // 5098: goto 50dd
      // 509d: aload 0
      // 509e: getfield Lobby.gb LGlobe;
      // 50a1: getfield Globe.wbstage [I
      // 50a4: aload 0
      // 50a5: getfield Lobby.gb LGlobe;
      // 50a8: getfield Globe.gameturn I
      // 50ab: iaload
      // 50ac: bipush 101
      // 50ae: if_icmpeq 50b6
      // 50b1: goto 50c0
      // 50b6: aload 0
      // 50b7: bipush 2
      // 50b8: putfield Lobby.cancreate I
      // 50bb: goto 50dd
      // 50c0: aload 0
      // 50c1: aload 0
      // 50c2: getfield Lobby.gb LGlobe;
      // 50c5: getfield Globe.wbstage [I
      // 50c8: aload 0
      // 50c9: getfield Lobby.gb LGlobe;
      // 50cc: getfield Globe.gameturn I
      // 50cf: iaload
      // 50d0: putfield Lobby.loadstage I
      // 50d3: aload 0
      // 50d4: bipush 1
      // 50d5: putfield Lobby.cancreate I
      // 50d8: goto 50dd
      // 50dd: aload 0
      // 50de: getfield Lobby.cancreate I
      // 50e1: bipush 1
      // 50e2: if_icmpeq 50ea
      // 50e5: goto 510c
      // 50ea: aload 0
      // 50eb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 50ee: ldc_w "Loading..."
      // 50f1: sipush 472
      // 50f4: aload 0
      // 50f5: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 50f8: ldc_w "Loading..."
      // 50fb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 50fe: bipush 2
      // 50ff: idiv
      // 5100: isub
      // 5101: sipush 235
      // 5104: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5107: goto 510c
      // 510c: aload 0
      // 510d: getfield Lobby.cancreate I
      // 5110: bipush 2
      // 5111: if_icmpeq 5119
      // 5114: goto 51b6
      // 5119: aload 0
      // 511a: ldc_w "   Create Game   "
      // 511d: sipush 472
      // 5120: sipush 235
      // 5123: bipush 1
      // 5124: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5127: goto 51b6
      // 512c: aload 0
      // 512d: getfield Lobby.sflk I
      // 5130: ifne 5138
      // 5133: goto 5147
      // 5138: aload 0
      // 5139: dup
      // 513a: getfield Lobby.sflk I
      // 513d: bipush 1
      // 513e: isub
      // 513f: putfield Lobby.sflk I
      // 5142: goto 515f
      // 5147: aload 0
      // 5148: bipush 4
      // 5149: putfield Lobby.sflk I
      // 514c: aload 0
      // 514d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5150: new java/awt/Color
      // 5153: dup
      // 5154: bipush 117
      // 5156: bipush 67
      // 5158: bipush 0
      // 5159: invokespecial java/awt/Color.<init> (III)V
      // 515c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 515f: aload 0
      // 5160: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5163: new java/lang/StringBuilder
      // 5166: dup
      // 5167: invokespecial java/lang/StringBuilder.<init> ()V
      // 516a: ldc_w "Your clan won the last game.  "
      // 516d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5170: aload 0
      // 5171: getfield Lobby.gb LGlobe;
      // 5174: getfield Globe.vclan Ljava/lang/String;
      // 5177: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 517a: ldc_w " must create this game!"
      // 517d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5180: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5183: sipush 472
      // 5186: aload 0
      // 5187: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 518a: new java/lang/StringBuilder
      // 518d: dup
      // 518e: invokespecial java/lang/StringBuilder.<init> ()V
      // 5191: ldc_w "Your clan won the last game.  "
      // 5194: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5197: aload 0
      // 5198: getfield Lobby.gb LGlobe;
      // 519b: getfield Globe.vclan Ljava/lang/String;
      // 519e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 51a1: ldc_w " must create this game!"
      // 51a4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 51a7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 51aa: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 51ad: bipush 2
      // 51ae: idiv
      // 51af: isub
      // 51b0: sipush 211
      // 51b3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 51b6: aload 0
      // 51b7: getfield Lobby.gb LGlobe;
      // 51ba: getfield Globe.canredo Z
      // 51bd: ifne 51c5
      // 51c0: goto 51d8
      // 51c5: aload 0
      // 51c6: ldc_w " Redo last game  < "
      // 51c9: sipush 644
      // 51cc: sipush 242
      // 51cf: bipush 1
      // 51d0: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 51d3: goto 51d8
      // 51d8: goto 51dd
      // 51dd: aload 0
      // 51de: getfield Lobby.gs LGameSparker;
      // 51e1: getfield GameSparker.wgame LSmenu;
      // 51e4: invokevirtual Smenu.getSelectedIndex ()I
      // 51e7: bipush 2
      // 51e8: if_icmpeq 51f0
      // 51eb: goto 5324
      // 51f0: bipush 1
      // 51f1: istore 16
      // 51f3: aload 0
      // 51f4: getfield Lobby.gs LGameSparker;
      // 51f7: getfield GameSparker.pgame LSmenu;
      // 51fa: sipush 472
      // 51fd: aload 0
      // 51fe: getfield Lobby.gs LGameSparker;
      // 5201: getfield GameSparker.pgame LSmenu;
      // 5204: getfield Smenu.w I
      // 5207: bipush 2
      // 5208: idiv
      // 5209: isub
      // 520a: sipush 150
      // 520d: invokevirtual Smenu.move (II)V
      // 5210: aload 0
      // 5211: getfield Lobby.gs LGameSparker;
      // 5214: getfield GameSparker.pgame LSmenu;
      // 5217: getfield Smenu.sel I
      // 521a: ifne 5222
      // 521d: goto 5324
      // 5222: aload 0
      // 5223: getfield Lobby.pgamesel I
      // 5226: aload 0
      // 5227: getfield Lobby.gs LGameSparker;
      // 522a: getfield GameSparker.pgame LSmenu;
      // 522d: getfield Smenu.sel I
      // 5230: if_icmpne 5238
      // 5233: goto 5268
      // 5238: aload 0
      // 5239: aload 0
      // 523a: getfield Lobby.gb LGlobe;
      // 523d: getfield Globe.wbstages [Ljava/lang/String;
      // 5240: aload 0
      // 5241: getfield Lobby.gs LGameSparker;
      // 5244: getfield GameSparker.pgame LSmenu;
      // 5247: getfield Smenu.sel I
      // 524a: bipush 1
      // 524b: isub
      // 524c: aaload
      // 524d: putfield Lobby.gstagename Ljava/lang/String;
      // 5250: aload 0
      // 5251: bipush 0
      // 5252: putfield Lobby.cancreate I
      // 5255: aload 0
      // 5256: aload 0
      // 5257: getfield Lobby.gs LGameSparker;
      // 525a: getfield GameSparker.pgame LSmenu;
      // 525d: getfield Smenu.sel I
      // 5260: putfield Lobby.pgamesel I
      // 5263: goto 5268
      // 5268: aload 0
      // 5269: getfield Lobby.cancreate I
      // 526c: bipush 1
      // 526d: if_icmpeq 5275
      // 5270: goto 527f
      // 5275: aload 0
      // 5276: bipush 2
      // 5277: putfield Lobby.cancreate I
      // 527a: goto 527f
      // 527f: aload 0
      // 5280: getfield Lobby.cancreate I
      // 5283: ifeq 528b
      // 5286: goto 52d5
      // 528b: aload 0
      // 528c: getfield Lobby.gb LGlobe;
      // 528f: getfield Globe.wbstage [I
      // 5292: aload 0
      // 5293: getfield Lobby.gs LGameSparker;
      // 5296: getfield GameSparker.pgame LSmenu;
      // 5299: getfield Smenu.sel I
      // 529c: bipush 1
      // 529d: isub
      // 529e: iaload
      // 529f: bipush 101
      // 52a1: if_icmpeq 52a9
      // 52a4: goto 52b3
      // 52a9: aload 0
      // 52aa: bipush 2
      // 52ab: putfield Lobby.cancreate I
      // 52ae: goto 52d5
      // 52b3: aload 0
      // 52b4: aload 0
      // 52b5: getfield Lobby.gb LGlobe;
      // 52b8: getfield Globe.wbstage [I
      // 52bb: aload 0
      // 52bc: getfield Lobby.gs LGameSparker;
      // 52bf: getfield GameSparker.pgame LSmenu;
      // 52c2: getfield Smenu.sel I
      // 52c5: bipush 1
      // 52c6: isub
      // 52c7: iaload
      // 52c8: putfield Lobby.loadstage I
      // 52cb: aload 0
      // 52cc: bipush 1
      // 52cd: putfield Lobby.cancreate I
      // 52d0: goto 52d5
      // 52d5: aload 0
      // 52d6: getfield Lobby.cancreate I
      // 52d9: bipush 1
      // 52da: if_icmpeq 52e2
      // 52dd: goto 5304
      // 52e2: aload 0
      // 52e3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 52e6: ldc_w "Loading..."
      // 52e9: sipush 472
      // 52ec: aload 0
      // 52ed: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 52f0: ldc_w "Loading..."
      // 52f3: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 52f6: bipush 2
      // 52f7: idiv
      // 52f8: isub
      // 52f9: sipush 235
      // 52fc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 52ff: goto 5304
      // 5304: aload 0
      // 5305: getfield Lobby.cancreate I
      // 5308: bipush 2
      // 5309: if_icmpeq 5311
      // 530c: goto 5324
      // 5311: aload 0
      // 5312: ldc_w "   Create Practice Game   "
      // 5315: sipush 472
      // 5318: sipush 235
      // 531b: bipush 1
      // 531c: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 531f: goto 5324
      // 5324: aload 0
      // 5325: getfield Lobby.gb LGlobe;
      // 5328: getfield Globe.loadwbgames I
      // 532b: bipush 3
      // 532c: if_icmpeq 5334
      // 532f: goto 5356
      // 5334: aload 0
      // 5335: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5338: ldc_w "Failed to load scheduled games, please try again later..."
      // 533b: sipush 472
      // 533e: aload 0
      // 533f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5342: ldc_w "Failed to load scheduled games, please try again later..."
      // 5345: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5348: bipush 2
      // 5349: idiv
      // 534a: isub
      // 534b: sipush 165
      // 534e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5351: goto 5356
      // 5356: aload 0
      // 5357: getfield Lobby.gb LGlobe;
      // 535a: getfield Globe.loadwbgames I
      // 535d: bipush 4
      // 535e: if_icmpeq 5366
      // 5361: goto 5388
      // 5366: aload 0
      // 5367: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 536a: ldc_w "This war or battle was not found, it may have been expired."
      // 536d: sipush 472
      // 5370: aload 0
      // 5371: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5374: ldc_w "This war or battle was not found, it may have been expired."
      // 5377: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 537a: bipush 2
      // 537b: idiv
      // 537c: isub
      // 537d: sipush 165
      // 5380: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5383: goto 5388
      // 5388: aload 0
      // 5389: getfield Lobby.gb LGlobe;
      // 538c: getfield Globe.loadwbgames I
      // 538f: bipush 6
      // 5391: if_icmpeq 5399
      // 5394: goto 5416
      // 5399: aload 0
      // 539a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 539d: ldc_w "Failed to undo the last game, please try again later..."
      // 53a0: sipush 472
      // 53a3: aload 0
      // 53a4: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 53a7: ldc_w "Failed to undo the last game, please try again later..."
      // 53aa: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 53ad: bipush 2
      // 53ae: idiv
      // 53af: isub
      // 53b0: sipush 165
      // 53b3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 53b6: goto 5416
      // 53bb: aload 0
      // 53bc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 53bf: new java/awt/Color
      // 53c2: dup
      // 53c3: bipush 0
      // 53c4: bipush 0
      // 53c5: bipush 0
      // 53c6: invokespecial java/awt/Color.<init> (III)V
      // 53c9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 53cc: aload 0
      // 53cd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 53d0: new java/awt/Font
      // 53d3: dup
      // 53d4: ldc_w "Arial"
      // 53d7: bipush 1
      // 53d8: bipush 12
      // 53da: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 53dd: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 53e0: aload 0
      // 53e1: aload 0
      // 53e2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 53e5: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 53e8: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 53eb: aload 0
      // 53ec: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 53ef: ldc_w "You must join a clan first to play wars and battles!"
      // 53f2: sipush 472
      // 53f5: aload 0
      // 53f6: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 53f9: ldc_w "You must join a clan first to play wars and battles!"
      // 53fc: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 53ff: bipush 2
      // 5400: idiv
      // 5401: isub
      // 5402: sipush 145
      // 5405: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5408: aload 0
      // 5409: ldc_w "    Find a clan to join    "
      // 540c: sipush 472
      // 540f: sipush 185
      // 5412: bipush 2
      // 5413: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5416: aload 0
      // 5417: getfield Lobby.inwab Z
      // 541a: ifeq 5422
      // 541d: goto 542c
      // 5422: aload 0
      // 5423: bipush 1
      // 5424: putfield Lobby.inwab Z
      // 5427: goto 542c
      // 542c: iload 16
      // 542e: ifne 5436
      // 5431: goto 5445
      // 5436: aload 0
      // 5437: getfield Lobby.gs LGameSparker;
      // 543a: getfield GameSparker.pgame LSmenu;
      // 543d: invokevirtual Smenu.show ()V
      // 5440: goto 544f
      // 5445: aload 0
      // 5446: getfield Lobby.gs LGameSparker;
      // 5449: getfield GameSparker.pgame LSmenu;
      // 544c: invokevirtual Smenu.hide ()V
      // 544f: iload 17
      // 5451: ifne 5459
      // 5454: goto 5472
      // 5459: aload 0
      // 545a: getfield Lobby.gs LGameSparker;
      // 545d: getfield GameSparker.vnpls LSmenu;
      // 5460: invokevirtual Smenu.show ()V
      // 5463: aload 0
      // 5464: getfield Lobby.gs LGameSparker;
      // 5467: getfield GameSparker.vtyp LSmenu;
      // 546a: invokevirtual Smenu.show ()V
      // 546d: goto 5486
      // 5472: aload 0
      // 5473: getfield Lobby.gs LGameSparker;
      // 5476: getfield GameSparker.vnpls LSmenu;
      // 5479: invokevirtual Smenu.hide ()V
      // 547c: aload 0
      // 547d: getfield Lobby.gs LGameSparker;
      // 5480: getfield GameSparker.vtyp LSmenu;
      // 5483: invokevirtual Smenu.hide ()V
      // 5486: goto 548b
      // 548b: aload 0
      // 548c: getfield Lobby.chalngd I
      // 548f: bipush -3
      // 5491: if_icmpeq 5499
      // 5494: goto 5798
      // 5499: aload 0
      // 549a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 549d: new java/awt/Color
      // 54a0: dup
      // 54a1: bipush 0
      // 54a2: bipush 0
      // 54a3: bipush 0
      // 54a4: invokespecial java/awt/Color.<init> (III)V
      // 54a7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 54aa: aload 0
      // 54ab: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 54ae: new java/awt/Font
      // 54b1: dup
      // 54b2: ldc_w "Arial"
      // 54b5: bipush 1
      // 54b6: bipush 13
      // 54b8: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 54bb: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 54be: aload 0
      // 54bf: aload 0
      // 54c0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 54c3: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 54c6: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 54c9: aload 0
      // 54ca: getfield Lobby.sflk I
      // 54cd: bipush 4
      // 54ce: irem
      // 54cf: ifeq 54d7
      // 54d2: goto 54e3
      // 54d7: aload 0
      // 54d8: getfield Lobby.sflk I
      // 54db: ifeq 54e3
      // 54de: goto 5504
      // 54e3: aload 0
      // 54e4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 54e7: ldc_w "Select Number of Players"
      // 54ea: sipush 472
      // 54ed: aload 0
      // 54ee: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 54f1: ldc_w "Select Number of Players"
      // 54f4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 54f7: bipush 2
      // 54f8: idiv
      // 54f9: isub
      // 54fa: bipush 85
      // 54fc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 54ff: goto 5504
      // 5504: aload 0
      // 5505: getfield Lobby.sflk I
      // 5508: ifne 5510
      // 550b: goto 551f
      // 5510: aload 0
      // 5511: dup
      // 5512: getfield Lobby.sflk I
      // 5515: bipush 1
      // 5516: isub
      // 5517: putfield Lobby.sflk I
      // 551a: goto 551f
      // 551f: bipush 0
      // 5520: istore 16
      // 5522: aload 0
      // 5523: getfield Lobby.xt LxtGraphics;
      // 5526: getfield xtGraphics.lan Z
      // 5529: ifne 5531
      // 552c: goto 553a
      // 5531: bipush 59
      // 5533: istore 16
      // 5535: goto 553a
      // 553a: aload 0
      // 553b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 553e: ldc_w "Players"
      // 5541: sipush 413
      // 5544: iload 16
      // 5546: isub
      // 5547: bipush 122
      // 5549: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 554c: aload 0
      // 554d: getfield Lobby.gs LGameSparker;
      // 5550: getfield GameSparker.snpls LSmenu;
      // 5553: invokevirtual Smenu.isShowing ()Z
      // 5556: ifeq 555e
      // 5559: goto 557d
      // 555e: aload 0
      // 555f: getfield Lobby.gs LGameSparker;
      // 5562: getfield GameSparker.snpls LSmenu;
      // 5565: invokevirtual Smenu.show ()V
      // 5568: aload 0
      // 5569: getfield Lobby.gs LGameSparker;
      // 556c: getfield GameSparker.snpls LSmenu;
      // 556f: aload 0
      // 5570: getfield Lobby.gnpls I
      // 5573: bipush 1
      // 5574: isub
      // 5575: invokevirtual Smenu.select (I)V
      // 5578: goto 557d
      // 557d: aload 0
      // 557e: getfield Lobby.gs LGameSparker;
      // 5581: getfield GameSparker.snpls LSmenu;
      // 5584: sipush 467
      // 5587: iload 16
      // 5589: isub
      // 558a: bipush 105
      // 558c: invokevirtual Smenu.move (II)V
      // 558f: bipush 0
      // 5590: istore 17
      // 5592: aload 0
      // 5593: getfield Lobby.gs LGameSparker;
      // 5596: getfield GameSparker.snpls LSmenu;
      // 5599: invokevirtual Smenu.getSelectedIndex ()I
      // 559c: ifne 55a4
      // 559f: goto 55de
      // 55a4: aload 0
      // 55a5: getfield Lobby.gs LGameSparker;
      // 55a8: getfield GameSparker.snpls LSmenu;
      // 55ab: invokevirtual Smenu.getSelectedIndex ()I
      // 55ae: aload 0
      // 55af: getfield Lobby.gnpls I
      // 55b2: bipush 1
      // 55b3: isub
      // 55b4: if_icmpne 55bc
      // 55b7: goto 55de
      // 55bc: aload 0
      // 55bd: aload 0
      // 55be: getfield Lobby.gs LGameSparker;
      // 55c1: getfield GameSparker.snpls LSmenu;
      // 55c4: invokevirtual Smenu.getSelectedIndex ()I
      // 55c7: bipush 1
      // 55c8: iadd
      // 55c9: putfield Lobby.gnpls I
      // 55cc: bipush 1
      // 55cd: istore 17
      // 55cf: aload 0
      // 55d0: getfield Lobby.gs LGameSparker;
      // 55d3: getfield GameSparker.swait LSmenu;
      // 55d6: invokevirtual Smenu.hide ()V
      // 55d9: goto 55de
      // 55de: aload 0
      // 55df: getfield Lobby.xt LxtGraphics;
      // 55e2: getfield xtGraphics.lan Z
      // 55e5: ifne 55ed
      // 55e8: goto 569f
      // 55ed: aload 0
      // 55ee: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 55f1: ldc_w "Bots"
      // 55f4: sipush 490
      // 55f7: bipush 122
      // 55f9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 55fc: aload 0
      // 55fd: getfield Lobby.gs LGameSparker;
      // 5600: getfield GameSparker.snbts LSmenu;
      // 5603: invokevirtual Smenu.isShowing ()Z
      // 5606: ifeq 560e
      // 5609: goto 562d
      // 560e: aload 0
      // 560f: getfield Lobby.gs LGameSparker;
      // 5612: getfield GameSparker.snbts LSmenu;
      // 5615: invokevirtual Smenu.show ()V
      // 5618: aload 0
      // 5619: getfield Lobby.gs LGameSparker;
      // 561c: getfield GameSparker.snbts LSmenu;
      // 561f: bipush 0
      // 5620: invokevirtual Smenu.select (I)V
      // 5623: aload 0
      // 5624: bipush 0
      // 5625: putfield Lobby.gnbts I
      // 5628: goto 562d
      // 562d: aload 0
      // 562e: getfield Lobby.gs LGameSparker;
      // 5631: getfield GameSparker.snbts LSmenu;
      // 5634: sipush 524
      // 5637: bipush 105
      // 5639: invokevirtual Smenu.move (II)V
      // 563c: aload 0
      // 563d: getfield Lobby.gs LGameSparker;
      // 5640: getfield GameSparker.snbts LSmenu;
      // 5643: invokevirtual Smenu.getSelectedIndex ()I
      // 5646: aload 0
      // 5647: getfield Lobby.gnbts I
      // 564a: if_icmpeq 5652
      // 564d: goto 565c
      // 5652: iload 17
      // 5654: ifne 565c
      // 5657: goto 569f
      // 565c: aload 0
      // 565d: aload 0
      // 565e: getfield Lobby.gs LGameSparker;
      // 5661: getfield GameSparker.snbts LSmenu;
      // 5664: invokevirtual Smenu.getSelectedIndex ()I
      // 5667: putfield Lobby.gnbts I
      // 566a: aload 0
      // 566b: getfield Lobby.gnbts I
      // 566e: aload 0
      // 566f: getfield Lobby.gnpls I
      // 5672: iadd
      // 5673: bipush 8
      // 5675: if_icmpgt 567d
      // 5678: goto 568c
      // 567d: aload 0
      // 567e: dup
      // 567f: getfield Lobby.gnbts I
      // 5682: bipush 1
      // 5683: isub
      // 5684: putfield Lobby.gnbts I
      // 5687: goto 566a
      // 568c: aload 0
      // 568d: getfield Lobby.gs LGameSparker;
      // 5690: getfield GameSparker.snbts LSmenu;
      // 5693: aload 0
      // 5694: getfield Lobby.gnbts I
      // 5697: invokevirtual Smenu.select (I)V
      // 569a: goto 569f
      // 569f: aload 0
      // 56a0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 56a3: ldc_w "Wait"
      // 56a6: sipush 414
      // 56a9: sipush 162
      // 56ac: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 56af: aload 0
      // 56b0: getfield Lobby.gs LGameSparker;
      // 56b3: getfield GameSparker.swait LSmenu;
      // 56b6: invokevirtual Smenu.isShowing ()Z
      // 56b9: ifeq 56c1
      // 56bc: goto 56e7
      // 56c1: aload 0
      // 56c2: getfield Lobby.gs LGameSparker;
      // 56c5: getfield GameSparker.swait LSmenu;
      // 56c8: invokevirtual Smenu.show ()V
      // 56cb: aload 0
      // 56cc: getfield Lobby.gwait I
      // 56cf: ifeq 56d7
      // 56d2: goto 56e7
      // 56d7: aload 0
      // 56d8: getfield Lobby.gs LGameSparker;
      // 56db: getfield GameSparker.swait LSmenu;
      // 56de: bipush 1
      // 56df: invokevirtual Smenu.select (I)V
      // 56e2: goto 56e7
      // 56e7: aload 0
      // 56e8: getfield Lobby.gs LGameSparker;
      // 56eb: getfield GameSparker.swait LSmenu;
      // 56ee: sipush 451
      // 56f1: sipush 145
      // 56f4: invokevirtual Smenu.move (II)V
      // 56f7: aload 0
      // 56f8: getfield Lobby.gs LGameSparker;
      // 56fb: getfield GameSparker.swait LSmenu;
      // 56fe: invokevirtual Smenu.getSelectedIndex ()I
      // 5701: bipush 1
      // 5702: iadd
      // 5703: bipush 60
      // 5705: imul
      // 5706: aload 0
      // 5707: getfield Lobby.gwait I
      // 570a: if_icmpne 5712
      // 570d: goto 572a
      // 5712: aload 0
      // 5713: aload 0
      // 5714: getfield Lobby.gs LGameSparker;
      // 5717: getfield GameSparker.swait LSmenu;
      // 571a: invokevirtual Smenu.getSelectedIndex ()I
      // 571d: bipush 1
      // 571e: iadd
      // 571f: bipush 60
      // 5721: imul
      // 5722: putfield Lobby.gwait I
      // 5725: goto 572a
      // 572a: aload 0
      // 572b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 572e: aload 0
      // 572f: bipush 90
      // 5731: bipush 90
      // 5733: bipush 90
      // 5735: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 5738: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 573b: aload 0
      // 573c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 573f: new java/awt/Font
      // 5742: dup
      // 5743: ldc_w "Arial"
      // 5746: bipush 0
      // 5747: bipush 11
      // 5749: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 574c: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 574f: aload 0
      // 5750: aload 0
      // 5751: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5754: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5757: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 575a: aload 0
      // 575b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 575e: ldc_w "( Maximum time to wait for all players to `join. )"
      // 5761: sipush 472
      // 5764: aload 0
      // 5765: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5768: ldc_w "( Maximum time to wait for all players to join. )"
      // 576b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 576e: bipush 2
      // 576f: idiv
      // 5770: isub
      // 5771: sipush 179
      // 5774: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5777: aload 0
      // 5778: ldc_w "   < Back   "
      // 577b: sipush 422
      // 577e: sipush 235
      // 5781: bipush 1
      // 5782: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5785: aload 0
      // 5786: ldc_w "   Next >   "
      // 5789: sipush 522
      // 578c: sipush 235
      // 578f: bipush 1
      // 5790: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5793: goto 5798
      // 5798: aload 0
      // 5799: getfield Lobby.chalngd I
      // 579c: bipush -4
      // 579e: if_icmpeq 57a6
      // 57a1: goto 5ef1
      // 57a6: aload 0
      // 57a7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 57aa: new java/awt/Color
      // 57ad: dup
      // 57ae: bipush 0
      // 57af: bipush 0
      // 57b0: bipush 0
      // 57b1: invokespecial java/awt/Color.<init> (III)V
      // 57b4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 57b7: aload 0
      // 57b8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 57bb: new java/awt/Font
      // 57be: dup
      // 57bf: ldc_w "Arial"
      // 57c2: bipush 1
      // 57c3: bipush 13
      // 57c5: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 57c8: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 57cb: aload 0
      // 57cc: aload 0
      // 57cd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 57d0: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 57d3: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 57d6: aload 0
      // 57d7: getfield Lobby.sflk I
      // 57da: bipush 4
      // 57db: irem
      // 57dc: ifeq 57e4
      // 57df: goto 57f0
      // 57e4: aload 0
      // 57e5: getfield Lobby.sflk I
      // 57e8: ifeq 57f0
      // 57eb: goto 5811
      // 57f0: aload 0
      // 57f1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 57f4: ldc_w "Game Options"
      // 57f7: sipush 472
      // 57fa: aload 0
      // 57fb: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 57fe: ldc_w "Game Options"
      // 5801: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5804: bipush 2
      // 5805: idiv
      // 5806: isub
      // 5807: bipush 85
      // 5809: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 580c: goto 5811
      // 5811: aload 0
      // 5812: getfield Lobby.sflk I
      // 5815: ifne 581d
      // 5818: goto 582c
      // 581d: aload 0
      // 581e: dup
      // 581f: getfield Lobby.sflk I
      // 5822: bipush 1
      // 5823: isub
      // 5824: putfield Lobby.sflk I
      // 5827: goto 582c
      // 582c: sipush 472
      // 582f: aload 0
      // 5830: getfield Lobby.gs LGameSparker;
      // 5833: getfield GameSparker.scars LSmenu;
      // 5836: invokevirtual Smenu.getWidth ()I
      // 5839: aload 0
      // 583a: getfield Lobby.gs LGameSparker;
      // 583d: getfield GameSparker.sclass LSmenu;
      // 5840: invokevirtual Smenu.getWidth ()I
      // 5843: iadd
      // 5844: aload 0
      // 5845: getfield Lobby.gs LGameSparker;
      // 5848: getfield GameSparker.sfix LSmenu;
      // 584b: invokevirtual Smenu.getWidth ()I
      // 584e: iadd
      // 584f: bipush 16
      // 5851: iadd
      // 5852: bipush 2
      // 5853: idiv
      // 5854: isub
      // 5855: istore 16
      // 5857: aload 0
      // 5858: getfield Lobby.gs LGameSparker;
      // 585b: getfield GameSparker.scars LSmenu;
      // 585e: invokevirtual Smenu.isShowing ()Z
      // 5861: ifeq 5869
      // 5864: goto 58af
      // 5869: aload 0
      // 586a: getfield Lobby.gs LGameSparker;
      // 586d: getfield GameSparker.scars LSmenu;
      // 5870: invokevirtual Smenu.show ()V
      // 5873: aload 0
      // 5874: getfield Lobby.gcars I
      // 5877: ifge 587f
      // 587a: goto 589f
      // 587f: aload 0
      // 5880: getfield Lobby.gcars I
      // 5883: bipush 2
      // 5884: if_icmple 588c
      // 5887: goto 589f
      // 588c: aload 0
      // 588d: getfield Lobby.gs LGameSparker;
      // 5890: getfield GameSparker.scars LSmenu;
      // 5893: aload 0
      // 5894: getfield Lobby.gcars I
      // 5897: invokevirtual Smenu.select (I)V
      // 589a: goto 58af
      // 589f: aload 0
      // 58a0: getfield Lobby.gs LGameSparker;
      // 58a3: getfield GameSparker.scars LSmenu;
      // 58a6: bipush 0
      // 58a7: invokevirtual Smenu.select (I)V
      // 58aa: goto 58af
      // 58af: aload 0
      // 58b0: getfield Lobby.gs LGameSparker;
      // 58b3: getfield GameSparker.scars LSmenu;
      // 58b6: iload 16
      // 58b8: bipush 105
      // 58ba: invokevirtual Smenu.move (II)V
      // 58bd: iload 16
      // 58bf: aload 0
      // 58c0: getfield Lobby.gs LGameSparker;
      // 58c3: getfield GameSparker.scars LSmenu;
      // 58c6: invokevirtual Smenu.getWidth ()I
      // 58c9: bipush 8
      // 58cb: iadd
      // 58cc: iadd
      // 58cd: istore 16
      // 58cf: aload 0
      // 58d0: getfield Lobby.gs LGameSparker;
      // 58d3: getfield GameSparker.sclass LSmenu;
      // 58d6: invokevirtual Smenu.isShowing ()Z
      // 58d9: ifeq 58e1
      // 58dc: goto 5927
      // 58e1: aload 0
      // 58e2: getfield Lobby.gs LGameSparker;
      // 58e5: getfield GameSparker.sclass LSmenu;
      // 58e8: invokevirtual Smenu.show ()V
      // 58eb: aload 0
      // 58ec: getfield Lobby.gclass I
      // 58ef: ifge 58f7
      // 58f2: goto 5917
      // 58f7: aload 0
      // 58f8: getfield Lobby.gclass I
      // 58fb: bipush 5
      // 58fc: if_icmple 5904
      // 58ff: goto 5917
      // 5904: aload 0
      // 5905: getfield Lobby.gs LGameSparker;
      // 5908: getfield GameSparker.sclass LSmenu;
      // 590b: aload 0
      // 590c: getfield Lobby.gclass I
      // 590f: invokevirtual Smenu.select (I)V
      // 5912: goto 5927
      // 5917: aload 0
      // 5918: getfield Lobby.gs LGameSparker;
      // 591b: getfield GameSparker.sclass LSmenu;
      // 591e: bipush 0
      // 591f: invokevirtual Smenu.select (I)V
      // 5922: goto 5927
      // 5927: aload 0
      // 5928: getfield Lobby.gs LGameSparker;
      // 592b: getfield GameSparker.sclass LSmenu;
      // 592e: iload 16
      // 5930: bipush 105
      // 5932: invokevirtual Smenu.move (II)V
      // 5935: iload 16
      // 5937: aload 0
      // 5938: getfield Lobby.gs LGameSparker;
      // 593b: getfield GameSparker.sclass LSmenu;
      // 593e: invokevirtual Smenu.getWidth ()I
      // 5941: bipush 8
      // 5943: iadd
      // 5944: iadd
      // 5945: istore 16
      // 5947: aload 0
      // 5948: getfield Lobby.gs LGameSparker;
      // 594b: getfield GameSparker.sfix LSmenu;
      // 594e: invokevirtual Smenu.isShowing ()Z
      // 5951: ifeq 5959
      // 5954: goto 599f
      // 5959: aload 0
      // 595a: getfield Lobby.gs LGameSparker;
      // 595d: getfield GameSparker.sfix LSmenu;
      // 5960: invokevirtual Smenu.show ()V
      // 5963: aload 0
      // 5964: getfield Lobby.gfix I
      // 5967: ifge 596f
      // 596a: goto 598f
      // 596f: aload 0
      // 5970: getfield Lobby.gfix I
      // 5973: bipush 5
      // 5974: if_icmple 597c
      // 5977: goto 598f
      // 597c: aload 0
      // 597d: getfield Lobby.gs LGameSparker;
      // 5980: getfield GameSparker.sfix LSmenu;
      // 5983: aload 0
      // 5984: getfield Lobby.gfix I
      // 5987: invokevirtual Smenu.select (I)V
      // 598a: goto 599f
      // 598f: aload 0
      // 5990: getfield Lobby.gs LGameSparker;
      // 5993: getfield GameSparker.sfix LSmenu;
      // 5996: bipush 0
      // 5997: invokevirtual Smenu.select (I)V
      // 599a: goto 599f
      // 599f: aload 0
      // 59a0: getfield Lobby.gs LGameSparker;
      // 59a3: getfield GameSparker.sfix LSmenu;
      // 59a6: iload 16
      // 59a8: bipush 105
      // 59aa: invokevirtual Smenu.move (II)V
      // 59ad: iload 16
      // 59af: aload 0
      // 59b0: getfield Lobby.gs LGameSparker;
      // 59b3: getfield GameSparker.sfix LSmenu;
      // 59b6: invokevirtual Smenu.getWidth ()I
      // 59b9: iadd
      // 59ba: istore 16
      // 59bc: aload 0
      // 59bd: getfield Lobby.gs LGameSparker;
      // 59c0: getfield GameSparker.openm Z
      // 59c3: ifeq 59cb
      // 59c6: goto 59eb
      // 59cb: aload 0
      // 59cc: getfield Lobby.gs LGameSparker;
      // 59cf: aload 0
      // 59d0: getfield Lobby.gs LGameSparker;
      // 59d3: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 59d6: iload 16
      // 59d8: bipush 112
      // 59da: isub
      // 59db: sipush 131
      // 59de: sipush 150
      // 59e1: bipush 17
      // 59e3: invokevirtual GameSparker.movefield (Ljava/awt/Component;IIII)V
      // 59e6: goto 5a06
      // 59eb: aload 0
      // 59ec: getfield Lobby.gs LGameSparker;
      // 59ef: aload 0
      // 59f0: getfield Lobby.gs LGameSparker;
      // 59f3: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 59f6: iload 16
      // 59f8: bipush 112
      // 59fa: isub
      // 59fb: sipush -2000
      // 59fe: sipush 150
      // 5a01: bipush 17
      // 5a03: invokevirtual GameSparker.movefield (Ljava/awt/Component;IIII)V
      // 5a06: aload 0
      // 5a07: getfield Lobby.gs LGameSparker;
      // 5a0a: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 5a0d: invokevirtual java/awt/Checkbox.isShowing ()Z
      // 5a10: ifeq 5a18
      // 5a13: goto 5a4e
      // 5a18: aload 0
      // 5a19: getfield Lobby.gs LGameSparker;
      // 5a1c: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 5a1f: invokevirtual java/awt/Checkbox.show ()V
      // 5a22: aload 0
      // 5a23: getfield Lobby.gnotp I
      // 5a26: ifeq 5a2e
      // 5a29: goto 5a3e
      // 5a2e: aload 0
      // 5a2f: getfield Lobby.gs LGameSparker;
      // 5a32: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 5a35: bipush 0
      // 5a36: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5a39: goto 5a4e
      // 5a3e: aload 0
      // 5a3f: getfield Lobby.gs LGameSparker;
      // 5a42: getfield GameSparker.notp Ljava/awt/Checkbox;
      // 5a45: bipush 1
      // 5a46: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5a49: goto 5a4e
      // 5a4e: aload 0
      // 5a4f: getfield Lobby.xt LxtGraphics;
      // 5a52: getfield xtGraphics.sc [I
      // 5a55: bipush 0
      // 5a56: iaload
      // 5a57: bipush 16
      // 5a59: if_icmplt 5a61
      // 5a5c: goto 5b4a
      // 5a61: aload 0
      // 5a62: getfield Lobby.gs LGameSparker;
      // 5a65: getfield GameSparker.openm Z
      // 5a68: ifeq 5a70
      // 5a6b: goto 5ab4
      // 5a70: aload 0
      // 5a71: getfield Lobby.gs LGameSparker;
      // 5a74: aload 0
      // 5a75: getfield Lobby.gs LGameSparker;
      // 5a78: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5a7b: sipush 472
      // 5a7e: aload 0
      // 5a7f: getfield Lobby.gs LGameSparker;
      // 5a82: getfield GameSparker.scars LSmenu;
      // 5a85: invokevirtual Smenu.getWidth ()I
      // 5a88: aload 0
      // 5a89: getfield Lobby.gs LGameSparker;
      // 5a8c: getfield GameSparker.sclass LSmenu;
      // 5a8f: invokevirtual Smenu.getWidth ()I
      // 5a92: iadd
      // 5a93: aload 0
      // 5a94: getfield Lobby.gs LGameSparker;
      // 5a97: getfield GameSparker.sfix LSmenu;
      // 5a9a: invokevirtual Smenu.getWidth ()I
      // 5a9d: iadd
      // 5a9e: bipush 16
      // 5aa0: iadd
      // 5aa1: bipush 2
      // 5aa2: idiv
      // 5aa3: isub
      // 5aa4: sipush 131
      // 5aa7: sipush 150
      // 5aaa: bipush 17
      // 5aac: invokevirtual GameSparker.movefield (Ljava/awt/Component;IIII)V
      // 5aaf: goto 5acd
      // 5ab4: aload 0
      // 5ab5: getfield Lobby.gs LGameSparker;
      // 5ab8: aload 0
      // 5ab9: getfield Lobby.gs LGameSparker;
      // 5abc: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5abf: sipush 410
      // 5ac2: sipush -2000
      // 5ac5: sipush 150
      // 5ac8: bipush 17
      // 5aca: invokevirtual GameSparker.movefield (Ljava/awt/Component;IIII)V
      // 5acd: aload 0
      // 5ace: getfield Lobby.gs LGameSparker;
      // 5ad1: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5ad4: invokevirtual java/awt/Checkbox.isShowing ()Z
      // 5ad7: ifeq 5adf
      // 5ada: goto 5b6c
      // 5adf: aload 0
      // 5ae0: getfield Lobby.gs LGameSparker;
      // 5ae3: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5ae6: invokevirtual java/awt/Checkbox.show ()V
      // 5ae9: aload 0
      // 5aea: getfield Lobby.gs LGameSparker;
      // 5aed: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5af0: new java/lang/StringBuilder
      // 5af3: dup
      // 5af4: invokespecial java/lang/StringBuilder.<init> ()V
      // 5af7: ldc ""
      // 5af9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5afc: aload 0
      // 5afd: getfield Lobby.cd LCarDefine;
      // 5b00: getfield CarDefine.names [Ljava/lang/String;
      // 5b03: aload 0
      // 5b04: getfield Lobby.xt LxtGraphics;
      // 5b07: getfield xtGraphics.sc [I
      // 5b0a: bipush 0
      // 5b0b: iaload
      // 5b0c: aaload
      // 5b0d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5b10: ldc_w " Game!"
      // 5b13: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5b16: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5b19: invokevirtual java/awt/Checkbox.setLabel (Ljava/lang/String;)V
      // 5b1c: aload 0
      // 5b1d: getfield Lobby.gclass I
      // 5b20: bipush -2
      // 5b22: if_icmple 5b2a
      // 5b25: goto 5b3a
      // 5b2a: aload 0
      // 5b2b: getfield Lobby.gs LGameSparker;
      // 5b2e: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5b31: bipush 1
      // 5b32: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5b35: goto 5b6c
      // 5b3a: aload 0
      // 5b3b: getfield Lobby.gs LGameSparker;
      // 5b3e: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5b41: bipush 0
      // 5b42: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5b45: goto 5b6c
      // 5b4a: aload 0
      // 5b4b: getfield Lobby.gs LGameSparker;
      // 5b4e: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5b51: invokevirtual java/awt/Checkbox.getState ()Z
      // 5b54: ifne 5b5c
      // 5b57: goto 5b6c
      // 5b5c: aload 0
      // 5b5d: getfield Lobby.gs LGameSparker;
      // 5b60: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5b63: bipush 0
      // 5b64: invokevirtual java/awt/Checkbox.setState (Z)V
      // 5b67: goto 5b6c
      // 5b6c: aload 0
      // 5b6d: getfield Lobby.gs LGameSparker;
      // 5b70: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5b73: invokevirtual java/awt/Checkbox.getState ()Z
      // 5b76: ifne 5b7e
      // 5b79: goto 5bc0
      // 5b7e: aload 0
      // 5b7f: getfield Lobby.gs LGameSparker;
      // 5b82: getfield GameSparker.sclass LSmenu;
      // 5b85: invokevirtual Smenu.isEnabled ()Z
      // 5b88: ifne 5b90
      // 5b8b: goto 5b9f
      // 5b90: aload 0
      // 5b91: getfield Lobby.gs LGameSparker;
      // 5b94: getfield GameSparker.sclass LSmenu;
      // 5b97: invokevirtual Smenu.disable ()V
      // 5b9a: goto 5b9f
      // 5b9f: aload 0
      // 5ba0: getfield Lobby.gs LGameSparker;
      // 5ba3: getfield GameSparker.scars LSmenu;
      // 5ba6: invokevirtual Smenu.isEnabled ()Z
      // 5ba9: ifne 5bb1
      // 5bac: goto 5c5e
      // 5bb1: aload 0
      // 5bb2: getfield Lobby.gs LGameSparker;
      // 5bb5: getfield GameSparker.scars LSmenu;
      // 5bb8: invokevirtual Smenu.disable ()V
      // 5bbb: goto 5c5e
      // 5bc0: aload 0
      // 5bc1: getfield Lobby.gs LGameSparker;
      // 5bc4: getfield GameSparker.sclass LSmenu;
      // 5bc7: invokevirtual Smenu.isEnabled ()Z
      // 5bca: ifeq 5bd2
      // 5bcd: goto 5be1
      // 5bd2: aload 0
      // 5bd3: getfield Lobby.gs LGameSparker;
      // 5bd6: getfield GameSparker.sclass LSmenu;
      // 5bd9: invokevirtual Smenu.enable ()V
      // 5bdc: goto 5be1
      // 5be1: aload 0
      // 5be2: getfield Lobby.gs LGameSparker;
      // 5be5: getfield GameSparker.scars LSmenu;
      // 5be8: invokevirtual Smenu.isEnabled ()Z
      // 5beb: ifeq 5bf3
      // 5bee: goto 5c02
      // 5bf3: aload 0
      // 5bf4: getfield Lobby.gs LGameSparker;
      // 5bf7: getfield GameSparker.scars LSmenu;
      // 5bfa: invokevirtual Smenu.enable ()V
      // 5bfd: goto 5c02
      // 5c02: aload 0
      // 5c03: getfield Lobby.gs LGameSparker;
      // 5c06: getfield GameSparker.sclass LSmenu;
      // 5c09: invokevirtual Smenu.getSelectedIndex ()I
      // 5c0c: aload 0
      // 5c0d: getfield Lobby.gclass I
      // 5c10: if_icmpne 5c18
      // 5c13: goto 5c35
      // 5c18: aload 0
      // 5c19: aload 0
      // 5c1a: getfield Lobby.gs LGameSparker;
      // 5c1d: getfield GameSparker.sclass LSmenu;
      // 5c20: invokevirtual Smenu.getSelectedIndex ()I
      // 5c23: putfield Lobby.gclass I
      // 5c26: aload 0
      // 5c27: getfield Lobby.gs LGameSparker;
      // 5c2a: getfield GameSparker.mycar Ljava/awt/Checkbox;
      // 5c2d: invokevirtual java/awt/Checkbox.hide ()V
      // 5c30: goto 5c35
      // 5c35: aload 0
      // 5c36: getfield Lobby.gs LGameSparker;
      // 5c39: getfield GameSparker.scars LSmenu;
      // 5c3c: invokevirtual Smenu.getSelectedIndex ()I
      // 5c3f: aload 0
      // 5c40: getfield Lobby.gcars I
      // 5c43: if_icmpne 5c4b
      // 5c46: goto 5c5e
      // 5c4b: aload 0
      // 5c4c: aload 0
      // 5c4d: getfield Lobby.gs LGameSparker;
      // 5c50: getfield GameSparker.scars LSmenu;
      // 5c53: invokevirtual Smenu.getSelectedIndex ()I
      // 5c56: putfield Lobby.gcars I
      // 5c59: goto 5c5e
      // 5c5e: aload 0
      // 5c5f: getfield Lobby.gs LGameSparker;
      // 5c62: getfield GameSparker.sfix LSmenu;
      // 5c65: invokevirtual Smenu.getSelectedIndex ()I
      // 5c68: aload 0
      // 5c69: getfield Lobby.gfix I
      // 5c6c: if_icmpne 5c74
      // 5c6f: goto 5c87
      // 5c74: aload 0
      // 5c75: aload 0
      // 5c76: getfield Lobby.gs LGameSparker;
      // 5c79: getfield GameSparker.sfix LSmenu;
      // 5c7c: invokevirtual Smenu.getSelectedIndex ()I
      // 5c7f: putfield Lobby.gfix I
      // 5c82: goto 5c87
      // 5c87: ldc_w "Public Game, anyone can join..."
      // 5c8a: astore 17
      // 5c8c: bipush 0
      // 5c8d: istore 18
      // 5c8f: bipush 0
      // 5c90: istore 19
      // 5c92: iload 19
      // 5c94: bipush 7
      // 5c96: if_icmplt 5c9e
      // 5c99: goto 5cc2
      // 5c9e: aload 0
      // 5c9f: getfield Lobby.invos [Ljava/lang/String;
      // 5ca2: iload 19
      // 5ca4: aaload
      // 5ca5: ldc ""
      // 5ca7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5caa: ifeq 5cb2
      // 5cad: goto 5cba
      // 5cb2: iinc 18 1
      // 5cb5: goto 5cba
      // 5cba: iinc 19 1
      // 5cbd: goto 5c92
      // 5cc2: iload 18
      // 5cc4: ifgt 5ccc
      // 5cc7: goto 5d74
      // 5ccc: ldc_w "Players Allowed:  "
      // 5ccf: astore 17
      // 5cd1: bipush 0
      // 5cd2: istore 19
      // 5cd4: bipush 0
      // 5cd5: istore 20
      // 5cd7: iload 20
      // 5cd9: bipush 7
      // 5cdb: if_icmplt 5ce3
      // 5cde: goto 5d6f
      // 5ce3: aload 0
      // 5ce4: getfield Lobby.invos [Ljava/lang/String;
      // 5ce7: iload 20
      // 5ce9: aaload
      // 5cea: ldc ""
      // 5cec: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5cef: ifeq 5cf7
      // 5cf2: goto 5d67
      // 5cf7: new java/lang/StringBuilder
      // 5cfa: dup
      // 5cfb: invokespecial java/lang/StringBuilder.<init> ()V
      // 5cfe: aload 17
      // 5d00: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d03: aload 0
      // 5d04: getfield Lobby.invos [Ljava/lang/String;
      // 5d07: iload 20
      // 5d09: aaload
      // 5d0a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d0d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5d10: astore 17
      // 5d12: iinc 19 1
      // 5d15: iload 19
      // 5d17: iload 18
      // 5d19: if_icmpne 5d21
      // 5d1c: goto 5d67
      // 5d21: iload 19
      // 5d23: iload 18
      // 5d25: bipush 1
      // 5d26: isub
      // 5d27: if_icmpeq 5d2f
      // 5d2a: goto 5d4b
      // 5d2f: new java/lang/StringBuilder
      // 5d32: dup
      // 5d33: invokespecial java/lang/StringBuilder.<init> ()V
      // 5d36: aload 17
      // 5d38: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d3b: ldc_w " and "
      // 5d3e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d41: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5d44: astore 17
      // 5d46: goto 5d67
      // 5d4b: new java/lang/StringBuilder
      // 5d4e: dup
      // 5d4f: invokespecial java/lang/StringBuilder.<init> ()V
      // 5d52: aload 17
      // 5d54: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d57: ldc_w ", "
      // 5d5a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5d5d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5d60: astore 17
      // 5d62: goto 5d67
      // 5d67: iinc 20 1
      // 5d6a: goto 5cd7
      // 5d6f: goto 5d74
      // 5d74: aload 0
      // 5d75: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5d78: new java/awt/Color
      // 5d7b: dup
      // 5d7c: bipush 0
      // 5d7d: bipush 0
      // 5d7e: bipush 0
      // 5d7f: invokespecial java/awt/Color.<init> (III)V
      // 5d82: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 5d85: iload 18
      // 5d87: aload 0
      // 5d88: getfield Lobby.gnpls I
      // 5d8b: bipush 1
      // 5d8c: isub
      // 5d8d: if_icmplt 5d95
      // 5d90: goto 5e2c
      // 5d95: aload 0
      // 5d96: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5d99: new java/awt/Font
      // 5d9c: dup
      // 5d9d: ldc_w "Arial"
      // 5da0: bipush 1
      // 5da1: bipush 13
      // 5da3: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5da6: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5da9: aload 0
      // 5daa: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5dad: new java/lang/StringBuilder
      // 5db0: dup
      // 5db1: invokespecial java/lang/StringBuilder.<init> ()V
      // 5db4: ldc_w "Private Game, only specific players allowed to join?  "
      // 5db7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5dba: iload 18
      // 5dbc: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5dbf: ldc_w "/"
      // 5dc2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5dc5: aload 0
      // 5dc6: getfield Lobby.gnpls I
      // 5dc9: bipush 1
      // 5dca: isub
      // 5dcb: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5dce: ldc ""
      // 5dd0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5dd3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5dd6: sipush 330
      // 5dd9: sipush 180
      // 5ddc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ddf: aload 0
      // 5de0: ldc_w "<   Select   "
      // 5de3: sipush 281
      // 5de6: sipush 180
      // 5de9: bipush 2
      // 5dea: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5ded: aload 0
      // 5dee: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5df1: new java/awt/Font
      // 5df4: dup
      // 5df5: ldc_w "Tahoma"
      // 5df8: bipush 0
      // 5df9: bipush 11
      // 5dfb: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5dfe: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5e01: aload 0
      // 5e02: aload 0
      // 5e03: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e06: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5e09: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5e0c: aload 0
      // 5e0d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e10: aload 17
      // 5e12: sipush 472
      // 5e15: aload 0
      // 5e16: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5e19: aload 17
      // 5e1b: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5e1e: bipush 2
      // 5e1f: idiv
      // 5e20: isub
      // 5e21: sipush 203
      // 5e24: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5e27: goto 5ed0
      // 5e2c: aload 0
      // 5e2d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e30: new java/awt/Font
      // 5e33: dup
      // 5e34: ldc_w "Arial"
      // 5e37: bipush 1
      // 5e38: bipush 13
      // 5e3a: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5e3d: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5e40: aload 0
      // 5e41: aload 0
      // 5e42: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e45: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5e48: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5e4b: aload 0
      // 5e4c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e4f: new java/lang/StringBuilder
      // 5e52: dup
      // 5e53: invokespecial java/lang/StringBuilder.<init> ()V
      // 5e56: ldc ""
      // 5e58: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5e5b: iload 18
      // 5e5d: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5e60: ldc_w " Allowed Players Selected"
      // 5e63: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5e66: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5e69: sipush 472
      // 5e6c: aload 0
      // 5e6d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5e70: new java/lang/StringBuilder
      // 5e73: dup
      // 5e74: invokespecial java/lang/StringBuilder.<init> ()V
      // 5e77: ldc ""
      // 5e79: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5e7c: iload 18
      // 5e7e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 5e81: ldc_w " Allowed Players Selected"
      // 5e84: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 5e87: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 5e8a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5e8d: bipush 2
      // 5e8e: idiv
      // 5e8f: isub
      // 5e90: sipush 180
      // 5e93: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5e96: aload 0
      // 5e97: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5e9a: new java/awt/Font
      // 5e9d: dup
      // 5e9e: ldc_w "Tahoma"
      // 5ea1: bipush 0
      // 5ea2: bipush 11
      // 5ea4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5ea7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5eaa: aload 0
      // 5eab: aload 0
      // 5eac: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5eaf: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5eb2: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5eb5: aload 0
      // 5eb6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5eb9: aload 17
      // 5ebb: sipush 472
      // 5ebe: aload 0
      // 5ebf: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5ec2: aload 17
      // 5ec4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5ec7: bipush 2
      // 5ec8: idiv
      // 5ec9: isub
      // 5eca: sipush 203
      // 5ecd: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5ed0: aload 0
      // 5ed1: ldc_w "   < Back   "
      // 5ed4: sipush 422
      // 5ed7: sipush 235
      // 5eda: bipush 1
      // 5edb: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5ede: aload 0
      // 5edf: ldc_w "   Finish!   "
      // 5ee2: sipush 522
      // 5ee5: sipush 235
      // 5ee8: bipush 1
      // 5ee9: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 5eec: goto 5ef1
      // 5ef1: aload 0
      // 5ef2: getfield Lobby.chalngd I
      // 5ef5: bipush -5
      // 5ef7: if_icmpeq 5eff
      // 5efa: goto 602a
      // 5eff: aload 0
      // 5f00: getfield Lobby.fstart Z
      // 5f03: ifne 5f0b
      // 5f06: goto 5f15
      // 5f0b: aload 0
      // 5f0c: bipush 0
      // 5f0d: putfield Lobby.fstart Z
      // 5f10: goto 5f15
      // 5f15: aload 0
      // 5f16: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5f19: new java/awt/Font
      // 5f1c: dup
      // 5f1d: ldc_w "Arial"
      // 5f20: bipush 1
      // 5f21: bipush 13
      // 5f23: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 5f26: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 5f29: aload 0
      // 5f2a: aload 0
      // 5f2b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5f2e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 5f31: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5f34: aload 0
      // 5f35: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 5f38: aload 0
      // 5f39: getfield Lobby.msg Ljava/lang/String;
      // 5f3c: sipush 472
      // 5f3f: aload 0
      // 5f40: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 5f43: aload 0
      // 5f44: getfield Lobby.msg Ljava/lang/String;
      // 5f47: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 5f4a: bipush 2
      // 5f4b: idiv
      // 5f4c: isub
      // 5f4d: sipush 145
      // 5f50: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 5f53: aload 0
      // 5f54: getfield Lobby.msg Ljava/lang/String;
      // 5f57: ldc_w ". . . | Creating Game | . . ."
      // 5f5a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5f5d: ifne 5f65
      // 5f60: goto 5f82
      // 5f65: aload 0
      // 5f66: getfield Lobby.ncnt I
      // 5f69: ifeq 5f71
      // 5f6c: goto 5f82
      // 5f71: aload 0
      // 5f72: ldc_w "| Creating Game |"
      // 5f75: putfield Lobby.msg Ljava/lang/String;
      // 5f78: aload 0
      // 5f79: bipush 5
      // 5f7a: putfield Lobby.ncnt I
      // 5f7d: goto 5f82
      // 5f82: aload 0
      // 5f83: getfield Lobby.msg Ljava/lang/String;
      // 5f86: ldc_w ". . | Creating Game | . ."
      // 5f89: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5f8c: ifne 5f94
      // 5f8f: goto 5fb1
      // 5f94: aload 0
      // 5f95: getfield Lobby.ncnt I
      // 5f98: ifeq 5fa0
      // 5f9b: goto 5fb1
      // 5fa0: aload 0
      // 5fa1: ldc_w ". . . | Creating Game | . . ."
      // 5fa4: putfield Lobby.msg Ljava/lang/String;
      // 5fa7: aload 0
      // 5fa8: bipush 5
      // 5fa9: putfield Lobby.ncnt I
      // 5fac: goto 5fb1
      // 5fb1: aload 0
      // 5fb2: getfield Lobby.msg Ljava/lang/String;
      // 5fb5: ldc_w ". | Creating Game | ."
      // 5fb8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5fbb: ifne 5fc3
      // 5fbe: goto 5fe0
      // 5fc3: aload 0
      // 5fc4: getfield Lobby.ncnt I
      // 5fc7: ifeq 5fcf
      // 5fca: goto 5fe0
      // 5fcf: aload 0
      // 5fd0: ldc_w ". . | Creating Game | . ."
      // 5fd3: putfield Lobby.msg Ljava/lang/String;
      // 5fd6: aload 0
      // 5fd7: bipush 5
      // 5fd8: putfield Lobby.ncnt I
      // 5fdb: goto 5fe0
      // 5fe0: aload 0
      // 5fe1: getfield Lobby.msg Ljava/lang/String;
      // 5fe4: ldc_w "| Creating Game |"
      // 5fe7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5fea: ifne 5ff2
      // 5fed: goto 600f
      // 5ff2: aload 0
      // 5ff3: getfield Lobby.ncnt I
      // 5ff6: ifeq 5ffe
      // 5ff9: goto 600f
      // 5ffe: aload 0
      // 5fff: ldc_w ". | Creating Game | ."
      // 6002: putfield Lobby.msg Ljava/lang/String;
      // 6005: aload 0
      // 6006: bipush 5
      // 6007: putfield Lobby.ncnt I
      // 600a: goto 600f
      // 600f: aload 0
      // 6010: getfield Lobby.ncnt I
      // 6013: ifne 601b
      // 6016: goto 602a
      // 601b: aload 0
      // 601c: dup
      // 601d: getfield Lobby.ncnt I
      // 6020: bipush 1
      // 6021: isub
      // 6022: putfield Lobby.ncnt I
      // 6025: goto 602a
      // 602a: aload 0
      // 602b: getfield Lobby.xt LxtGraphics;
      // 602e: getfield xtGraphics.lan Z
      // 6031: ifeq 6039
      // 6034: goto 6bc6
      // 6039: aload 0
      // 603a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 603d: aload 0
      // 603e: sipush 230
      // 6041: sipush 230
      // 6044: sipush 230
      // 6047: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 604a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 604d: aload 0
      // 604e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6051: sipush 225
      // 6054: sipush 263
      // 6057: sipush 495
      // 605a: sipush 157
      // 605d: bipush 20
      // 605f: bipush 20
      // 6061: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 6064: aload 0
      // 6065: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6068: new java/awt/Color
      // 606b: dup
      // 606c: bipush 0
      // 606d: bipush 0
      // 606e: bipush 0
      // 606f: invokespecial java/awt/Color.<init> (III)V
      // 6072: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6075: aload 0
      // 6076: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6079: sipush 225
      // 607c: sipush 263
      // 607f: sipush 495
      // 6082: sipush 157
      // 6085: bipush 20
      // 6087: bipush 20
      // 6089: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 608c: bipush 14
      // 608e: anewarray 30
      // 6091: astore 15
      // 6093: bipush 14
      // 6095: anewarray 30
      // 6098: astore 16
      // 609a: bipush 14
      // 609c: newarray 4
      // 609e: astore 17
      // 60a0: bipush 0
      // 60a1: istore 18
      // 60a3: iload 18
      // 60a5: bipush 14
      // 60a7: if_icmplt 60af
      // 60aa: goto 60cb
      // 60af: aload 15
      // 60b1: iload 18
      // 60b3: ldc ""
      // 60b5: aastore
      // 60b6: aload 16
      // 60b8: iload 18
      // 60ba: ldc ""
      // 60bc: aastore
      // 60bd: aload 17
      // 60bf: iload 18
      // 60c1: bipush 0
      // 60c2: bastore
      // 60c3: iinc 18 1
      // 60c6: goto 60a3
      // 60cb: bipush 0
      // 60cc: istore 18
      // 60ce: aload 0
      // 60cf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 60d2: new java/awt/Font
      // 60d5: dup
      // 60d6: ldc_w "Tahoma"
      // 60d9: bipush 0
      // 60da: bipush 11
      // 60dc: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 60df: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 60e2: aload 0
      // 60e3: aload 0
      // 60e4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 60e7: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 60ea: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 60ed: aload 0
      // 60ee: getfield Lobby.updatec I
      // 60f1: bipush -1
      // 60f2: if_icmpne 60fa
      // 60f5: goto 64b4
      // 60fa: bipush 0
      // 60fb: istore 19
      // 60fd: iload 19
      // 60ff: bipush 7
      // 6101: if_icmplt 6109
      // 6104: goto 62cb
      // 6109: aload 15
      // 610b: iload 18
      // 610d: ldc ""
      // 610f: aastore
      // 6110: aload 16
      // 6112: iload 18
      // 6114: aload 0
      // 6115: getfield Lobby.cnames [Ljava/lang/String;
      // 6118: iload 19
      // 611a: aaload
      // 611b: aastore
      // 611c: bipush 0
      // 611d: istore 20
      // 611f: bipush 0
      // 6120: istore 21
      // 6122: bipush 0
      // 6123: istore 22
      // 6125: bipush 0
      // 6126: istore 23
      // 6128: bipush 0
      // 6129: istore 24
      // 612b: iload 20
      // 612d: aload 0
      // 612e: getfield Lobby.sentn [Ljava/lang/String;
      // 6131: iload 19
      // 6133: aaload
      // 6134: invokevirtual java/lang/String.length ()I
      // 6137: if_icmplt 613f
      // 613a: goto 6268
      // 613f: new java/lang/StringBuilder
      // 6142: dup
      // 6143: invokespecial java/lang/StringBuilder.<init> ()V
      // 6146: ldc ""
      // 6148: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 614b: aload 0
      // 614c: getfield Lobby.sentn [Ljava/lang/String;
      // 614f: iload 19
      // 6151: aaload
      // 6152: iload 20
      // 6154: invokevirtual java/lang/String.charAt (I)C
      // 6157: invokevirtual java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
      // 615a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 615d: astore 25
      // 615f: aload 25
      // 6161: ldc_w " "
      // 6164: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6167: ifne 616f
      // 616a: goto 617f
      // 616f: iload 22
      // 6171: istore 21
      // 6173: iload 20
      // 6175: istore 23
      // 6177: iinc 24 1
      // 617a: goto 6182
      // 617f: bipush 0
      // 6180: istore 24
      // 6182: iload 24
      // 6184: bipush 1
      // 6185: if_icmple 618d
      // 6188: goto 6260
      // 618d: new java/lang/StringBuilder
      // 6190: dup
      // 6191: invokespecial java/lang/StringBuilder.<init> ()V
      // 6194: aload 15
      // 6196: iload 18
      // 6198: dup2_x1
      // 6199: aaload
      // 619a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 619d: aload 25
      // 619f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 61a2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 61a5: aastore
      // 61a6: iinc 22 1
      // 61a9: aload 0
      // 61aa: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 61ad: aload 15
      // 61af: iload 18
      // 61b1: aaload
      // 61b2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 61b5: sipush 367
      // 61b8: if_icmpgt 61c0
      // 61bb: goto 6260
      // 61c0: iload 21
      // 61c2: ifne 61ca
      // 61c5: goto 6251
      // 61ca: aload 15
      // 61cc: iload 18
      // 61ce: aload 15
      // 61d0: iload 18
      // 61d2: aaload
      // 61d3: bipush 0
      // 61d4: iload 21
      // 61d6: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 61d9: aastore
      // 61da: iload 18
      // 61dc: bipush 13
      // 61de: if_icmpeq 61e6
      // 61e1: goto 6233
      // 61e6: bipush 0
      // 61e7: istore 26
      // 61e9: iload 26
      // 61eb: bipush 13
      // 61ed: if_icmplt 61f5
      // 61f0: goto 6221
      // 61f5: aload 15
      // 61f7: iload 26
      // 61f9: aload 15
      // 61fb: iload 26
      // 61fd: bipush 1
      // 61fe: iadd
      // 61ff: aaload
      // 6200: aastore
      // 6201: aload 16
      // 6203: iload 26
      // 6205: aload 16
      // 6207: iload 26
      // 6209: bipush 1
      // 620a: iadd
      // 620b: aaload
      // 620c: aastore
      // 620d: aload 17
      // 620f: iload 26
      // 6211: aload 17
      // 6213: iload 26
      // 6215: bipush 1
      // 6216: iadd
      // 6217: baload
      // 6218: bastore
      // 6219: iinc 26 1
      // 621c: goto 61e9
      // 6221: aload 15
      // 6223: iload 18
      // 6225: ldc ""
      // 6227: aastore
      // 6228: aload 17
      // 622a: iload 18
      // 622c: bipush 1
      // 622d: bastore
      // 622e: goto 6242
      // 6233: iinc 18 1
      // 6236: aload 16
      // 6238: iload 18
      // 623a: aload 0
      // 623b: getfield Lobby.cnames [Ljava/lang/String;
      // 623e: iload 19
      // 6240: aaload
      // 6241: aastore
      // 6242: iload 23
      // 6244: istore 20
      // 6246: bipush 0
      // 6247: istore 22
      // 6249: bipush 0
      // 624a: istore 21
      // 624c: goto 6260
      // 6251: aload 15
      // 6253: iload 18
      // 6255: ldc ""
      // 6257: aastore
      // 6258: bipush 0
      // 6259: istore 22
      // 625b: goto 6260
      // 6260: iinc 20 1
      // 6263: goto 612b
      // 6268: iload 18
      // 626a: bipush 13
      // 626c: if_icmpeq 6274
      // 626f: goto 62c0
      // 6274: iload 19
      // 6276: bipush 6
      // 6278: if_icmpne 6280
      // 627b: goto 62c0
      // 6280: bipush 0
      // 6281: istore 25
      // 6283: iload 25
      // 6285: bipush 13
      // 6287: if_icmplt 628f
      // 628a: goto 62bb
      // 628f: aload 15
      // 6291: iload 25
      // 6293: aload 15
      // 6295: iload 25
      // 6297: bipush 1
      // 6298: iadd
      // 6299: aaload
      // 629a: aastore
      // 629b: aload 16
      // 629d: iload 25
      // 629f: aload 16
      // 62a1: iload 25
      // 62a3: bipush 1
      // 62a4: iadd
      // 62a5: aaload
      // 62a6: aastore
      // 62a7: aload 17
      // 62a9: iload 25
      // 62ab: aload 17
      // 62ad: iload 25
      // 62af: bipush 1
      // 62b0: iadd
      // 62b1: baload
      // 62b2: bastore
      // 62b3: iinc 25 1
      // 62b6: goto 6283
      // 62bb: goto 62c3
      // 62c0: iinc 18 1
      // 62c3: iinc 19 1
      // 62c6: goto 60fd
      // 62cb: iload 18
      // 62cd: bipush 6
      // 62cf: isub
      // 62d0: bipush 15
      // 62d2: imul
      // 62d3: istore 10
      // 62d5: iload 10
      // 62d7: iflt 62df
      // 62da: goto 62e7
      // 62df: bipush 0
      // 62e0: istore 10
      // 62e2: goto 62e7
      // 62e7: aload 0
      // 62e8: getfield Lobby.spos3 I
      // 62eb: i2f
      // 62ec: ldc_w 28.0
      // 62ef: fdiv
      // 62f0: iload 10
      // 62f2: i2f
      // 62f3: fmul
      // 62f4: f2i
      // 62f5: istore 11
      // 62f7: ldc ""
      // 62f9: astore 19
      // 62fb: aload 0
      // 62fc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 62ff: new java/awt/Font
      // 6302: dup
      // 6303: ldc_w "Tahoma"
      // 6306: bipush 1
      // 6307: bipush 11
      // 6309: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 630c: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 630f: aload 0
      // 6310: aload 0
      // 6311: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6314: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6317: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 631a: bipush 0
      // 631b: istore 20
      // 631d: iload 20
      // 631f: iload 18
      // 6321: if_icmplt 6329
      // 6324: goto 63ce
      // 6329: aload 19
      // 632b: aload 16
      // 632d: iload 20
      // 632f: aaload
      // 6330: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6333: ifeq 633b
      // 6336: goto 63c6
      // 633b: sipush 280
      // 633e: iload 20
      // 6340: bipush 15
      // 6342: imul
      // 6343: iadd
      // 6344: iload 11
      // 6346: isub
      // 6347: sipush 266
      // 634a: if_icmpgt 6352
      // 634d: goto 63ba
      // 6352: sipush 280
      // 6355: iload 20
      // 6357: bipush 15
      // 6359: imul
      // 635a: iadd
      // 635b: iload 11
      // 635d: isub
      // 635e: sipush 370
      // 6361: if_icmplt 6369
      // 6364: goto 63ba
      // 6369: aload 0
      // 636a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 636d: new java/lang/StringBuilder
      // 6370: dup
      // 6371: invokespecial java/lang/StringBuilder.<init> ()V
      // 6374: aload 16
      // 6376: iload 20
      // 6378: aaload
      // 6379: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 637c: ldc ":"
      // 637e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6381: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6384: sipush 320
      // 6387: aload 0
      // 6388: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 638b: new java/lang/StringBuilder
      // 638e: dup
      // 638f: invokespecial java/lang/StringBuilder.<init> ()V
      // 6392: aload 16
      // 6394: iload 20
      // 6396: aaload
      // 6397: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 639a: ldc ":"
      // 639c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 639f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 63a2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 63a5: isub
      // 63a6: sipush 305
      // 63a9: iload 20
      // 63ab: bipush 15
      // 63ad: imul
      // 63ae: iadd
      // 63af: iload 11
      // 63b1: isub
      // 63b2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 63b5: goto 63ba
      // 63ba: aload 16
      // 63bc: iload 20
      // 63be: aaload
      // 63bf: astore 19
      // 63c1: goto 63c6
      // 63c6: iinc 20 1
      // 63c9: goto 631d
      // 63ce: aload 0
      // 63cf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 63d2: new java/awt/Font
      // 63d5: dup
      // 63d6: ldc_w "Tahoma"
      // 63d9: bipush 0
      // 63da: bipush 11
      // 63dc: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 63df: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 63e2: bipush 0
      // 63e3: istore 20
      // 63e5: iload 20
      // 63e7: iload 18
      // 63e9: if_icmplt 63f1
      // 63ec: goto 64af
      // 63f1: aload 17
      // 63f3: iload 20
      // 63f5: baload
      // 63f6: ifne 63fe
      // 63f9: goto 6459
      // 63fe: iload 20
      // 6400: ifeq 6408
      // 6403: goto 6459
      // 6408: aload 15
      // 640a: iload 20
      // 640c: aaload
      // 640d: ldc_w " "
      // 6410: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 6413: bipush -1
      // 6414: if_icmpne 641c
      // 6417: goto 6459
      // 641c: aload 15
      // 641e: iload 20
      // 6420: new java/lang/StringBuilder
      // 6423: dup
      // 6424: invokespecial java/lang/StringBuilder.<init> ()V
      // 6427: ldc_w "..."
      // 642a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 642d: aload 15
      // 642f: iload 20
      // 6431: aaload
      // 6432: aload 15
      // 6434: iload 20
      // 6436: aaload
      // 6437: ldc_w " "
      // 643a: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 643d: aload 15
      // 643f: iload 20
      // 6441: aaload
      // 6442: invokevirtual java/lang/String.length ()I
      // 6445: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 6448: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 644b: ldc ""
      // 644d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6450: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6453: aastore
      // 6454: goto 6459
      // 6459: sipush 280
      // 645c: iload 20
      // 645e: bipush 15
      // 6460: imul
      // 6461: iadd
      // 6462: iload 11
      // 6464: isub
      // 6465: sipush 266
      // 6468: if_icmpgt 6470
      // 646b: goto 64a7
      // 6470: sipush 280
      // 6473: iload 20
      // 6475: bipush 15
      // 6477: imul
      // 6478: iadd
      // 6479: iload 11
      // 647b: isub
      // 647c: sipush 370
      // 647f: if_icmplt 6487
      // 6482: goto 64a7
      // 6487: aload 0
      // 6488: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 648b: aload 15
      // 648d: iload 20
      // 648f: aaload
      // 6490: sipush 325
      // 6493: sipush 305
      // 6496: iload 20
      // 6498: bipush 15
      // 649a: imul
      // 649b: iadd
      // 649c: iload 11
      // 649e: isub
      // 649f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 64a2: goto 64a7
      // 64a7: iinc 20 1
      // 64aa: goto 63e5
      // 64af: goto 64d7
      // 64b4: bipush 0
      // 64b5: istore 10
      // 64b7: bipush 0
      // 64b8: istore 11
      // 64ba: aload 0
      // 64bb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 64be: ldc_w "Loading chat..."
      // 64c1: sipush 465
      // 64c4: aload 0
      // 64c5: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 64c8: ldc_w "Loading chat..."
      // 64cb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 64ce: bipush 2
      // 64cf: idiv
      // 64d0: isub
      // 64d1: sipush 325
      // 64d4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 64d7: aload 0
      // 64d8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 64db: aload 0
      // 64dc: sipush 205
      // 64df: sipush 205
      // 64e2: sipush 205
      // 64e5: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 64e8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 64eb: aload 0
      // 64ec: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 64ef: sipush 235
      // 64f2: sipush 269
      // 64f5: sipush 480
      // 64f8: bipush 25
      // 64fa: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 64fd: aload 0
      // 64fe: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6501: aload 0
      // 6502: getfield Lobby.xt LxtGraphics;
      // 6505: getfield xtGraphics.chat Ljava/awt/Image;
      // 6508: sipush 241
      // 650b: sipush 273
      // 650e: aconst_null
      // 650f: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 6512: pop
      // 6513: aload 0
      // 6514: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6517: new java/awt/Font
      // 651a: dup
      // 651b: ldc_w "Arial"
      // 651e: bipush 1
      // 651f: bipush 12
      // 6521: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6524: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6527: aload 0
      // 6528: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 652b: aload 0
      // 652c: bipush 120
      // 652e: bipush 120
      // 6530: bipush 120
      // 6532: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6535: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6538: aload 0
      // 6539: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 653c: ldc_w "( Room Chat )"
      // 653f: sipush 299
      // 6542: sipush 285
      // 6545: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6548: aload 0
      // 6549: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 654c: aload 0
      // 654d: sipush 150
      // 6550: sipush 150
      // 6553: sipush 150
      // 6556: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6559: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 655c: aload 0
      // 655d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6560: sipush 235
      // 6563: sipush 291
      // 6566: sipush 696
      // 6569: sipush 291
      // 656c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 656f: aload 0
      // 6570: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6573: aload 0
      // 6574: sipush 205
      // 6577: sipush 205
      // 657a: sipush 205
      // 657d: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6580: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6583: aload 0
      // 6584: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6587: sipush 235
      // 658a: sipush 387
      // 658d: sipush 480
      // 6590: bipush 28
      // 6592: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6595: aload 0
      // 6596: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6599: aload 0
      // 659a: sipush 150
      // 659d: sipush 150
      // 65a0: sipush 150
      // 65a3: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 65a6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 65a9: aload 0
      // 65aa: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 65ad: sipush 235
      // 65b0: sipush 389
      // 65b3: sipush 696
      // 65b6: sipush 389
      // 65b9: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 65bc: aload 0
      // 65bd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 65c0: aload 0
      // 65c1: sipush 205
      // 65c4: sipush 205
      // 65c7: sipush 205
      // 65ca: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 65cd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 65d0: aload 0
      // 65d1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 65d4: sipush 698
      // 65d7: sipush 311
      // 65da: bipush 17
      // 65dc: bipush 59
      // 65de: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 65e1: aload 0
      // 65e2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 65e5: aload 0
      // 65e6: sipush 205
      // 65e9: sipush 205
      // 65ec: sipush 205
      // 65ef: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 65f2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 65f5: aload 0
      // 65f6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 65f9: sipush 231
      // 65fc: sipush 269
      // 65ff: bipush 4
      // 6600: sipush 146
      // 6603: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6606: aload 0
      // 6607: getfield Lobby.mscro3 I
      // 660a: sipush 351
      // 660d: if_icmpne 6615
      // 6610: goto 661f
      // 6615: iload 10
      // 6617: ifeq 661f
      // 661a: goto 666c
      // 661f: iload 10
      // 6621: ifeq 6629
      // 6624: goto 6642
      // 6629: aload 0
      // 662a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 662d: aload 0
      // 662e: sipush 205
      // 6631: sipush 205
      // 6634: sipush 205
      // 6637: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 663a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 663d: goto 6656
      // 6642: aload 0
      // 6643: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6646: aload 0
      // 6647: sipush 215
      // 664a: sipush 215
      // 664d: sipush 215
      // 6650: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6653: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6656: aload 0
      // 6657: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 665a: sipush 698
      // 665d: sipush 294
      // 6660: bipush 17
      // 6662: bipush 17
      // 6664: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6667: goto 6692
      // 666c: aload 0
      // 666d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6670: aload 0
      // 6671: sipush 220
      // 6674: sipush 220
      // 6677: sipush 220
      // 667a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 667d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6680: aload 0
      // 6681: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6684: sipush 698
      // 6687: sipush 294
      // 668a: bipush 17
      // 668c: bipush 17
      // 668e: bipush 1
      // 668f: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 6692: iload 10
      // 6694: ifne 669c
      // 6697: goto 66b7
      // 669c: aload 0
      // 669d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 66a0: aload 0
      // 66a1: getfield Lobby.xt LxtGraphics;
      // 66a4: getfield xtGraphics.asu Ljava/awt/Image;
      // 66a7: sipush 703
      // 66aa: sipush 300
      // 66ad: aconst_null
      // 66ae: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 66b1: pop
      // 66b2: goto 66b7
      // 66b7: aload 0
      // 66b8: getfield Lobby.mscro3 I
      // 66bb: sipush 352
      // 66be: if_icmpne 66c6
      // 66c1: goto 66d0
      // 66c6: iload 10
      // 66c8: ifeq 66d0
      // 66cb: goto 671d
      // 66d0: iload 10
      // 66d2: ifeq 66da
      // 66d5: goto 66f3
      // 66da: aload 0
      // 66db: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 66de: aload 0
      // 66df: sipush 205
      // 66e2: sipush 205
      // 66e5: sipush 205
      // 66e8: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 66eb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 66ee: goto 6707
      // 66f3: aload 0
      // 66f4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 66f7: aload 0
      // 66f8: sipush 215
      // 66fb: sipush 215
      // 66fe: sipush 215
      // 6701: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6704: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6707: aload 0
      // 6708: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 670b: sipush 698
      // 670e: sipush 370
      // 6711: bipush 17
      // 6713: bipush 17
      // 6715: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6718: goto 6743
      // 671d: aload 0
      // 671e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6721: aload 0
      // 6722: sipush 220
      // 6725: sipush 220
      // 6728: sipush 220
      // 672b: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 672e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6731: aload 0
      // 6732: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6735: sipush 698
      // 6738: sipush 370
      // 673b: bipush 17
      // 673d: bipush 17
      // 673f: bipush 1
      // 6740: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 6743: iload 10
      // 6745: ifne 674d
      // 6748: goto 6768
      // 674d: aload 0
      // 674e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6751: aload 0
      // 6752: getfield Lobby.xt LxtGraphics;
      // 6755: getfield xtGraphics.asd Ljava/awt/Image;
      // 6758: sipush 703
      // 675b: sipush 376
      // 675e: aconst_null
      // 675f: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 6762: pop
      // 6763: goto 6768
      // 6768: iload 10
      // 676a: ifne 6772
      // 676d: goto 6bae
      // 6772: aload 0
      // 6773: getfield Lobby.lspos3 I
      // 6776: aload 0
      // 6777: getfield Lobby.spos3 I
      // 677a: if_icmpne 6782
      // 677d: goto 67b1
      // 6782: aload 0
      // 6783: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6786: aload 0
      // 6787: sipush 215
      // 678a: sipush 215
      // 678d: sipush 215
      // 6790: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6793: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6796: aload 0
      // 6797: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 679a: sipush 698
      // 679d: sipush 311
      // 67a0: aload 0
      // 67a1: getfield Lobby.spos3 I
      // 67a4: iadd
      // 67a5: bipush 17
      // 67a7: bipush 31
      // 67a9: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 67ac: goto 67f0
      // 67b1: aload 0
      // 67b2: getfield Lobby.mscro3 I
      // 67b5: sipush 141
      // 67b8: if_icmpeq 67c0
      // 67bb: goto 67d9
      // 67c0: aload 0
      // 67c1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 67c4: aload 0
      // 67c5: sipush 215
      // 67c8: sipush 215
      // 67cb: sipush 215
      // 67ce: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 67d1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 67d4: goto 67d9
      // 67d9: aload 0
      // 67da: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 67dd: sipush 698
      // 67e0: sipush 311
      // 67e3: aload 0
      // 67e4: getfield Lobby.spos3 I
      // 67e7: iadd
      // 67e8: bipush 17
      // 67ea: bipush 31
      // 67ec: bipush 1
      // 67ed: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 67f0: aload 0
      // 67f1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 67f4: aload 0
      // 67f5: sipush 150
      // 67f8: sipush 150
      // 67fb: sipush 150
      // 67fe: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6801: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6804: aload 0
      // 6805: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6808: sipush 703
      // 680b: sipush 324
      // 680e: aload 0
      // 680f: getfield Lobby.spos3 I
      // 6812: iadd
      // 6813: sipush 709
      // 6816: sipush 324
      // 6819: aload 0
      // 681a: getfield Lobby.spos3 I
      // 681d: iadd
      // 681e: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 6821: aload 0
      // 6822: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6825: sipush 703
      // 6828: sipush 326
      // 682b: aload 0
      // 682c: getfield Lobby.spos3 I
      // 682f: iadd
      // 6830: sipush 709
      // 6833: sipush 326
      // 6836: aload 0
      // 6837: getfield Lobby.spos3 I
      // 683a: iadd
      // 683b: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 683e: aload 0
      // 683f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6842: sipush 703
      // 6845: sipush 328
      // 6848: aload 0
      // 6849: getfield Lobby.spos3 I
      // 684c: iadd
      // 684d: sipush 709
      // 6850: sipush 328
      // 6853: aload 0
      // 6854: getfield Lobby.spos3 I
      // 6857: iadd
      // 6858: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 685b: aload 0
      // 685c: getfield Lobby.mscro3 I
      // 685f: sipush 342
      // 6862: if_icmpgt 686a
      // 6865: goto 6887
      // 686a: aload 0
      // 686b: getfield Lobby.lspos3 I
      // 686e: aload 0
      // 686f: getfield Lobby.spos3 I
      // 6872: if_icmpne 687a
      // 6875: goto 6887
      // 687a: aload 0
      // 687b: aload 0
      // 687c: getfield Lobby.spos3 I
      // 687f: putfield Lobby.lspos3 I
      // 6882: goto 6887
      // 6887: iload 3
      // 6888: ifne 6890
      // 688b: goto 6b01
      // 6890: aload 0
      // 6891: getfield Lobby.mscro3 I
      // 6894: sipush 345
      // 6897: if_icmpeq 689f
      // 689a: goto 68e8
      // 689f: iload 1
      // 68a0: sipush 698
      // 68a3: if_icmpgt 68ab
      // 68a6: goto 68e8
      // 68ab: iload 1
      // 68ac: sipush 715
      // 68af: if_icmplt 68b7
      // 68b2: goto 68e8
      // 68b7: iload 2
      // 68b8: sipush 311
      // 68bb: aload 0
      // 68bc: getfield Lobby.spos3 I
      // 68bf: iadd
      // 68c0: if_icmpgt 68c8
      // 68c3: goto 68e8
      // 68c8: iload 2
      // 68c9: aload 0
      // 68ca: getfield Lobby.spos3 I
      // 68cd: sipush 342
      // 68d0: iadd
      // 68d1: if_icmplt 68d9
      // 68d4: goto 68e8
      // 68d9: aload 0
      // 68da: iload 2
      // 68db: aload 0
      // 68dc: getfield Lobby.spos3 I
      // 68df: isub
      // 68e0: putfield Lobby.mscro3 I
      // 68e3: goto 68e8
      // 68e8: aload 0
      // 68e9: getfield Lobby.mscro3 I
      // 68ec: sipush 345
      // 68ef: if_icmpeq 68f7
      // 68f2: goto 6933
      // 68f7: iload 1
      // 68f8: sipush 696
      // 68fb: if_icmpgt 6903
      // 68fe: goto 6933
      // 6903: iload 1
      // 6904: sipush 717
      // 6907: if_icmplt 690f
      // 690a: goto 6933
      // 690f: iload 2
      // 6910: sipush 292
      // 6913: if_icmpgt 691b
      // 6916: goto 6933
      // 691b: iload 2
      // 691c: sipush 313
      // 691f: if_icmplt 6927
      // 6922: goto 6933
      // 6927: aload 0
      // 6928: sipush 351
      // 692b: putfield Lobby.mscro3 I
      // 692e: goto 6933
      // 6933: aload 0
      // 6934: getfield Lobby.mscro3 I
      // 6937: sipush 345
      // 693a: if_icmpeq 6942
      // 693d: goto 697e
      // 6942: iload 1
      // 6943: sipush 696
      // 6946: if_icmpgt 694e
      // 6949: goto 697e
      // 694e: iload 1
      // 694f: sipush 717
      // 6952: if_icmplt 695a
      // 6955: goto 697e
      // 695a: iload 2
      // 695b: sipush 368
      // 695e: if_icmpgt 6966
      // 6961: goto 697e
      // 6966: iload 2
      // 6967: sipush 389
      // 696a: if_icmplt 6972
      // 696d: goto 697e
      // 6972: aload 0
      // 6973: sipush 352
      // 6976: putfield Lobby.mscro3 I
      // 6979: goto 697e
      // 697e: aload 0
      // 697f: getfield Lobby.mscro3 I
      // 6982: sipush 345
      // 6985: if_icmpeq 698d
      // 6988: goto 69d3
      // 698d: iload 1
      // 698e: sipush 698
      // 6991: if_icmpgt 6999
      // 6994: goto 69d3
      // 6999: iload 1
      // 699a: sipush 715
      // 699d: if_icmplt 69a5
      // 69a0: goto 69d3
      // 69a5: iload 2
      // 69a6: sipush 311
      // 69a9: if_icmpgt 69b1
      // 69ac: goto 69d3
      // 69b1: iload 2
      // 69b2: sipush 370
      // 69b5: if_icmplt 69bd
      // 69b8: goto 69d3
      // 69bd: aload 0
      // 69be: sipush 326
      // 69c1: putfield Lobby.mscro3 I
      // 69c4: aload 0
      // 69c5: iload 2
      // 69c6: aload 0
      // 69c7: getfield Lobby.mscro3 I
      // 69ca: isub
      // 69cb: putfield Lobby.spos3 I
      // 69ce: goto 69d3
      // 69d3: bipush 100
      // 69d5: iload 10
      // 69d7: idiv
      // 69d8: istore 19
      // 69da: iload 19
      // 69dc: bipush 1
      // 69dd: if_icmplt 69e5
      // 69e0: goto 69ed
      // 69e5: bipush 1
      // 69e6: istore 19
      // 69e8: goto 69ed
      // 69ed: aload 0
      // 69ee: getfield Lobby.mscro3 I
      // 69f1: sipush 351
      // 69f4: if_icmpeq 69fc
      // 69f7: goto 6a43
      // 69fc: aload 0
      // 69fd: dup
      // 69fe: getfield Lobby.spos3 I
      // 6a01: iload 19
      // 6a03: isub
      // 6a04: putfield Lobby.spos3 I
      // 6a07: aload 0
      // 6a08: getfield Lobby.spos3 I
      // 6a0b: bipush 28
      // 6a0d: if_icmpgt 6a15
      // 6a10: goto 6a20
      // 6a15: aload 0
      // 6a16: bipush 28
      // 6a18: putfield Lobby.spos3 I
      // 6a1b: goto 6a20
      // 6a20: aload 0
      // 6a21: getfield Lobby.spos3 I
      // 6a24: iflt 6a2c
      // 6a27: goto 6a36
      // 6a2c: aload 0
      // 6a2d: bipush 0
      // 6a2e: putfield Lobby.spos3 I
      // 6a31: goto 6a36
      // 6a36: aload 0
      // 6a37: aload 0
      // 6a38: getfield Lobby.spos3 I
      // 6a3b: putfield Lobby.lspos3 I
      // 6a3e: goto 6a43
      // 6a43: aload 0
      // 6a44: getfield Lobby.mscro3 I
      // 6a47: sipush 352
      // 6a4a: if_icmpeq 6a52
      // 6a4d: goto 6a99
      // 6a52: aload 0
      // 6a53: dup
      // 6a54: getfield Lobby.spos3 I
      // 6a57: iload 19
      // 6a59: iadd
      // 6a5a: putfield Lobby.spos3 I
      // 6a5d: aload 0
      // 6a5e: getfield Lobby.spos3 I
      // 6a61: bipush 28
      // 6a63: if_icmpgt 6a6b
      // 6a66: goto 6a76
      // 6a6b: aload 0
      // 6a6c: bipush 28
      // 6a6e: putfield Lobby.spos3 I
      // 6a71: goto 6a76
      // 6a76: aload 0
      // 6a77: getfield Lobby.spos3 I
      // 6a7a: iflt 6a82
      // 6a7d: goto 6a8c
      // 6a82: aload 0
      // 6a83: bipush 0
      // 6a84: putfield Lobby.spos3 I
      // 6a87: goto 6a8c
      // 6a8c: aload 0
      // 6a8d: aload 0
      // 6a8e: getfield Lobby.spos3 I
      // 6a91: putfield Lobby.lspos3 I
      // 6a94: goto 6a99
      // 6a99: aload 0
      // 6a9a: getfield Lobby.mscro3 I
      // 6a9d: sipush 342
      // 6aa0: if_icmple 6aa8
      // 6aa3: goto 6ae1
      // 6aa8: aload 0
      // 6aa9: iload 2
      // 6aaa: aload 0
      // 6aab: getfield Lobby.mscro3 I
      // 6aae: isub
      // 6aaf: putfield Lobby.spos3 I
      // 6ab2: aload 0
      // 6ab3: getfield Lobby.spos3 I
      // 6ab6: bipush 28
      // 6ab8: if_icmpgt 6ac0
      // 6abb: goto 6acb
      // 6ac0: aload 0
      // 6ac1: bipush 28
      // 6ac3: putfield Lobby.spos3 I
      // 6ac6: goto 6acb
      // 6acb: aload 0
      // 6acc: getfield Lobby.spos3 I
      // 6acf: iflt 6ad7
      // 6ad2: goto 6ae1
      // 6ad7: aload 0
      // 6ad8: bipush 0
      // 6ad9: putfield Lobby.spos3 I
      // 6adc: goto 6ae1
      // 6ae1: aload 0
      // 6ae2: getfield Lobby.mscro3 I
      // 6ae5: sipush 345
      // 6ae8: if_icmpeq 6af0
      // 6aeb: goto 6afc
      // 6af0: aload 0
      // 6af1: sipush 425
      // 6af4: putfield Lobby.mscro3 I
      // 6af7: goto 6afc
      // 6afc: goto 6b1c
      // 6b01: aload 0
      // 6b02: getfield Lobby.mscro3 I
      // 6b05: sipush 345
      // 6b08: if_icmpne 6b10
      // 6b0b: goto 6b1c
      // 6b10: aload 0
      // 6b11: sipush 345
      // 6b14: putfield Lobby.mscro3 I
      // 6b17: goto 6b1c
      // 6b1c: iload 4
      // 6b1e: ifne 6b26
      // 6b21: goto 6bae
      // 6b26: iload 1
      // 6b27: sipush 235
      // 6b2a: if_icmpgt 6b32
      // 6b2d: goto 6bae
      // 6b32: iload 1
      // 6b33: sipush 698
      // 6b36: if_icmplt 6b3e
      // 6b39: goto 6bae
      // 6b3e: iload 2
      // 6b3f: sipush 291
      // 6b42: if_icmpgt 6b4a
      // 6b45: goto 6bae
      // 6b4a: iload 2
      // 6b4b: sipush 389
      // 6b4e: if_icmplt 6b56
      // 6b51: goto 6bae
      // 6b56: aload 0
      // 6b57: dup
      // 6b58: getfield Lobby.spos3 I
      // 6b5b: iload 4
      // 6b5d: bipush 2
      // 6b5e: idiv
      // 6b5f: isub
      // 6b60: putfield Lobby.spos3 I
      // 6b63: aload 0
      // 6b64: bipush 1
      // 6b65: putfield Lobby.zeromsw Z
      // 6b68: aload 0
      // 6b69: getfield Lobby.spos3 I
      // 6b6c: bipush 28
      // 6b6e: if_icmpgt 6b76
      // 6b71: goto 6b86
      // 6b76: aload 0
      // 6b77: bipush 28
      // 6b79: putfield Lobby.spos3 I
      // 6b7c: aload 0
      // 6b7d: bipush 0
      // 6b7e: putfield Lobby.zeromsw Z
      // 6b81: goto 6b86
      // 6b86: aload 0
      // 6b87: getfield Lobby.spos3 I
      // 6b8a: iflt 6b92
      // 6b8d: goto 6ba1
      // 6b92: aload 0
      // 6b93: bipush 0
      // 6b94: putfield Lobby.spos3 I
      // 6b97: aload 0
      // 6b98: bipush 0
      // 6b99: putfield Lobby.zeromsw Z
      // 6b9c: goto 6ba1
      // 6ba1: aload 0
      // 6ba2: aload 0
      // 6ba3: getfield Lobby.spos3 I
      // 6ba6: putfield Lobby.lspos3 I
      // 6ba9: goto 6bae
      // 6bae: aload 0
      // 6baf: bipush 1
      // 6bb0: putfield Lobby.pre1 Z
      // 6bb3: aload 0
      // 6bb4: ldc_w "Send Message"
      // 6bb7: sipush 657
      // 6bba: sipush 406
      // 6bbd: bipush 3
      // 6bbe: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 6bc1: goto 6bc6
      // 6bc6: aload 0
      // 6bc7: getfield Lobby.chalngd I
      // 6bca: bipush -1
      // 6bcb: if_icmpne 6bd3
      // 6bce: goto 6be1
      // 6bd3: aload 0
      // 6bd4: getfield Lobby.chalngd I
      // 6bd7: bipush -6
      // 6bd9: if_icmpeq 6be1
      // 6bdc: goto c6f5
      // 6be1: aload 0
      // 6be2: getfield Lobby.lg LLogin;
      // 6be5: getfield Login.gamec I
      // 6be8: bipush -1
      // 6be9: if_icmpne 6bf1
      // 6bec: goto c6f5
      // 6bf1: aload 0
      // 6bf2: getfield Lobby.lg LLogin;
      // 6bf5: getfield Login.cntgame I
      // 6bf8: ifge 6c00
      // 6bfb: goto 6c2d
      // 6c00: aload 0
      // 6c01: getfield Lobby.lg LLogin;
      // 6c04: getfield Login.cntgame I
      // 6c07: bipush 10
      // 6c09: if_icmplt 6c11
      // 6c0c: goto 6c2d
      // 6c11: aload 0
      // 6c12: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6c15: bipush 3
      // 6c16: aload 0
      // 6c17: getfield Lobby.lg LLogin;
      // 6c1a: getfield Login.cntgame I
      // 6c1d: i2f
      // 6c1e: ldc_w 10.0
      // 6c21: fdiv
      // 6c22: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 6c25: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 6c28: goto 6c2d
      // 6c2d: aload 0
      // 6c2e: getfield Lobby.lg LLogin;
      // 6c31: getfield Login.cntgame I
      // 6c34: sipush 390
      // 6c37: if_icmpgt 6c3f
      // 6c3a: goto 6c71
      // 6c3f: aload 0
      // 6c40: getfield Lobby.lg LLogin;
      // 6c43: getfield Login.cntgame I
      // 6c46: sipush 400
      // 6c49: if_icmplt 6c51
      // 6c4c: goto 6c71
      // 6c51: aload 0
      // 6c52: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6c55: bipush 3
      // 6c56: sipush 400
      // 6c59: aload 0
      // 6c5a: getfield Lobby.lg LLogin;
      // 6c5d: getfield Login.cntgame I
      // 6c60: isub
      // 6c61: i2f
      // 6c62: ldc_w 10.0
      // 6c65: fdiv
      // 6c66: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 6c69: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 6c6c: goto 6c71
      // 6c71: aload 0
      // 6c72: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6c75: aload 0
      // 6c76: sipush 245
      // 6c79: sipush 245
      // 6c7c: sipush 245
      // 6c7f: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6c82: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6c85: aload 0
      // 6c86: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6c89: sipush 383
      // 6c8c: sipush 242
      // 6c8f: sipush 337
      // 6c92: bipush 46
      // 6c94: bipush 20
      // 6c96: bipush 20
      // 6c98: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 6c9b: aload 0
      // 6c9c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6c9f: new java/awt/Color
      // 6ca2: dup
      // 6ca3: bipush 0
      // 6ca4: bipush 0
      // 6ca5: bipush 0
      // 6ca6: invokespecial java/awt/Color.<init> (III)V
      // 6ca9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6cac: aload 0
      // 6cad: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6cb0: sipush 383
      // 6cb3: sipush 242
      // 6cb6: sipush 337
      // 6cb9: bipush 46
      // 6cbb: bipush 20
      // 6cbd: bipush 20
      // 6cbf: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 6cc2: aload 0
      // 6cc3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6cc6: new java/awt/Font
      // 6cc9: dup
      // 6cca: ldc_w "Arial"
      // 6ccd: bipush 1
      // 6cce: bipush 12
      // 6cd0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 6cd3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 6cd6: aload 0
      // 6cd7: aload 0
      // 6cd8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6cdb: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 6cde: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 6ce1: aload 0
      // 6ce2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ce5: new java/lang/StringBuilder
      // 6ce8: dup
      // 6ce9: invokespecial java/lang/StringBuilder.<init> ()V
      // 6cec: ldc ""
      // 6cee: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6cf1: aload 0
      // 6cf2: getfield Lobby.lg LLogin;
      // 6cf5: getfield Login.gmaker Ljava/lang/String;
      // 6cf8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6cfb: ldc_w " created a game in "
      // 6cfe: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d01: aload 0
      // 6d02: getfield Lobby.lg LLogin;
      // 6d05: getfield Login.gservern Ljava/lang/String;
      // 6d08: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d0b: ldc_w " :: Room "
      // 6d0e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d11: aload 0
      // 6d12: getfield Lobby.lg LLogin;
      // 6d15: getfield Login.groom I
      // 6d18: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6d1b: ldc ""
      // 6d1d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d20: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6d23: sipush 551
      // 6d26: aload 0
      // 6d27: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 6d2a: new java/lang/StringBuilder
      // 6d2d: dup
      // 6d2e: invokespecial java/lang/StringBuilder.<init> ()V
      // 6d31: ldc ""
      // 6d33: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d36: aload 0
      // 6d37: getfield Lobby.lg LLogin;
      // 6d3a: getfield Login.gmaker Ljava/lang/String;
      // 6d3d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d40: ldc_w " created a game in "
      // 6d43: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d46: aload 0
      // 6d47: getfield Lobby.lg LLogin;
      // 6d4a: getfield Login.gservern Ljava/lang/String;
      // 6d4d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d50: ldc_w " :: Room "
      // 6d53: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d56: aload 0
      // 6d57: getfield Lobby.lg LLogin;
      // 6d5a: getfield Login.groom I
      // 6d5d: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 6d60: ldc ""
      // 6d62: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 6d65: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 6d68: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6d6b: bipush 2
      // 6d6c: idiv
      // 6d6d: isub
      // 6d6e: sipush 260
      // 6d71: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6d74: iload 1
      // 6d75: sipush 488
      // 6d78: if_icmpgt 6d80
      // 6d7b: goto 6e46
      // 6d80: iload 1
      // 6d81: sipush 614
      // 6d84: if_icmplt 6d8c
      // 6d87: goto 6e46
      // 6d8c: iload 2
      // 6d8d: sipush 264
      // 6d90: if_icmpgt 6d98
      // 6d93: goto 6e46
      // 6d98: iload 2
      // 6d99: sipush 287
      // 6d9c: if_icmplt 6da4
      // 6d9f: goto 6e46
      // 6da4: iload 3
      // 6da5: ifne 6dad
      // 6da8: goto 6db7
      // 6dad: aload 0
      // 6dae: bipush 1
      // 6daf: putfield Lobby.grprsd Z
      // 6db2: goto 6e5c
      // 6db7: aload 0
      // 6db8: getfield Lobby.grprsd Z
      // 6dbb: ifne 6dc3
      // 6dbe: goto 6e5c
      // 6dc3: bipush 0
      // 6dc4: istore 15
      // 6dc6: iload 15
      // 6dc8: aload 0
      // 6dc9: getfield Lobby.lg LLogin;
      // 6dcc: getfield Login.nservers I
      // 6dcf: if_icmplt 6dd7
      // 6dd2: goto 6e3c
      // 6dd7: aload 0
      // 6dd8: getfield Lobby.lg LLogin;
      // 6ddb: getfield Login.gservern Ljava/lang/String;
      // 6dde: aload 0
      // 6ddf: getfield Lobby.lg LLogin;
      // 6de2: getfield Login.snames [Ljava/lang/String;
      // 6de5: iload 15
      // 6de7: aaload
      // 6de8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 6deb: ifne 6df3
      // 6dee: goto 6e34
      // 6df3: aload 0
      // 6df4: invokevirtual Lobby.stopallnow ()V
      // 6df7: aload 0
      // 6df8: getfield Lobby.xt LxtGraphics;
      // 6dfb: aload 0
      // 6dfc: getfield Lobby.lg LLogin;
      // 6dff: getfield Login.servers [Ljava/lang/String;
      // 6e02: iload 15
      // 6e04: aaload
      // 6e05: putfield xtGraphics.server Ljava/lang/String;
      // 6e08: aload 0
      // 6e09: getfield Lobby.xt LxtGraphics;
      // 6e0c: aload 0
      // 6e0d: getfield Lobby.lg LLogin;
      // 6e10: getfield Login.snames [Ljava/lang/String;
      // 6e13: iload 15
      // 6e15: aaload
      // 6e16: putfield xtGraphics.servername Ljava/lang/String;
      // 6e19: aload 0
      // 6e1a: getfield Lobby.xt LxtGraphics;
      // 6e1d: sipush 7070
      // 6e20: aload 0
      // 6e21: getfield Lobby.lg LLogin;
      // 6e24: getfield Login.groom I
      // 6e27: iadd
      // 6e28: putfield xtGraphics.servport I
      // 6e2b: aload 0
      // 6e2c: invokevirtual Lobby.inishlobby ()V
      // 6e2f: goto 6e3c
      // 6e34: iinc 15 1
      // 6e37: goto 6dc6
      // 6e3c: aload 0
      // 6e3d: bipush 0
      // 6e3e: putfield Lobby.grprsd Z
      // 6e41: goto 6e5c
      // 6e46: aload 0
      // 6e47: getfield Lobby.grprsd Z
      // 6e4a: ifne 6e52
      // 6e4d: goto 6e5c
      // 6e52: aload 0
      // 6e53: bipush 0
      // 6e54: putfield Lobby.grprsd Z
      // 6e57: goto 6e5c
      // 6e5c: aload 0
      // 6e5d: getfield Lobby.grprsd Z
      // 6e60: ifeq 6e68
      // 6e63: goto 6ea4
      // 6e68: aload 0
      // 6e69: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6e6c: aload 0
      // 6e6d: sipush 230
      // 6e70: sipush 230
      // 6e73: sipush 230
      // 6e76: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6e79: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6e7c: aload 0
      // 6e7d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6e80: sipush 490
      // 6e83: sipush 266
      // 6e86: bipush 122
      // 6e88: bipush 19
      // 6e8a: bipush 1
      // 6e8b: invokevirtual java/awt/Graphics2D.fill3DRect (IIIIZ)V
      // 6e8e: aload 0
      // 6e8f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6e92: new java/awt/Color
      // 6e95: dup
      // 6e96: bipush 0
      // 6e97: bipush 0
      // 6e98: bipush 0
      // 6e99: invokespecial java/awt/Color.<init> (III)V
      // 6e9c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6e9f: goto 6eda
      // 6ea4: aload 0
      // 6ea5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ea8: aload 0
      // 6ea9: sipush 230
      // 6eac: sipush 230
      // 6eaf: sipush 230
      // 6eb2: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6eb5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6eb8: aload 0
      // 6eb9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ebc: sipush 490
      // 6ebf: sipush 266
      // 6ec2: bipush 122
      // 6ec4: bipush 19
      // 6ec6: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 6ec9: aload 0
      // 6eca: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ecd: aload 0
      // 6ece: bipush 60
      // 6ed0: bipush 60
      // 6ed2: bipush 60
      // 6ed4: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6ed7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6eda: aload 0
      // 6edb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ede: ldc_w "View / Join Game"
      // 6ee1: sipush 551
      // 6ee4: aload 0
      // 6ee5: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 6ee8: ldc_w "View / Join Game"
      // 6eeb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 6eee: bipush 2
      // 6eef: idiv
      // 6ef0: isub
      // 6ef1: sipush 280
      // 6ef4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 6ef7: aload 0
      // 6ef8: getfield Lobby.lg LLogin;
      // 6efb: getfield Login.cntgame I
      // 6efe: ifge 6f06
      // 6f01: goto 6f17
      // 6f06: aload 0
      // 6f07: getfield Lobby.lg LLogin;
      // 6f0a: getfield Login.cntgame I
      // 6f0d: bipush 10
      // 6f0f: if_icmpge 6f17
      // 6f12: goto 6f3b
      // 6f17: aload 0
      // 6f18: getfield Lobby.lg LLogin;
      // 6f1b: getfield Login.cntgame I
      // 6f1e: sipush 390
      // 6f21: if_icmpgt 6f29
      // 6f24: goto 6f4c
      // 6f29: aload 0
      // 6f2a: getfield Lobby.lg LLogin;
      // 6f2d: getfield Login.cntgame I
      // 6f30: sipush 400
      // 6f33: if_icmplt 6f3b
      // 6f36: goto 6f4c
      // 6f3b: aload 0
      // 6f3c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6f3f: bipush 3
      // 6f40: fconst_1
      // 6f41: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // 6f44: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // 6f47: goto 6f4c
      // 6f4c: aload 0
      // 6f4d: getfield Lobby.lg LLogin;
      // 6f50: dup
      // 6f51: getfield Login.cntgame I
      // 6f54: bipush 1
      // 6f55: iadd
      // 6f56: putfield Login.cntgame I
      // 6f59: aload 0
      // 6f5a: getfield Lobby.lg LLogin;
      // 6f5d: getfield Login.cntgame I
      // 6f60: sipush 400
      // 6f63: if_icmpeq 6f6b
      // 6f66: goto c6f5
      // 6f6b: aload 0
      // 6f6c: getfield Lobby.lg LLogin;
      // 6f6f: bipush -1
      // 6f70: putfield Login.gamec I
      // 6f73: goto c6f5
      // 6f78: aload 0
      // 6f79: getfield Lobby.opengame I
      // 6f7c: bipush 26
      // 6f7e: if_icmplt 6f86
      // 6f81: goto 7190
      // 6f86: sipush 229
      // 6f89: aload 0
      // 6f8a: getfield Lobby.opengame I
      // 6f8d: iadd
      // 6f8e: istore 15
      // 6f90: iload 15
      // 6f92: sipush 255
      // 6f95: if_icmpgt 6f9d
      // 6f98: goto 6fa7
      // 6f9d: sipush 255
      // 6fa0: istore 15
      // 6fa2: goto 6fa7
      // 6fa7: iload 15
      // 6fa9: iflt 6fb1
      // 6fac: goto 6fb9
      // 6fb1: bipush 0
      // 6fb2: istore 15
      // 6fb4: goto 6fb9
      // 6fb9: aload 0
      // 6fba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6fbd: aload 0
      // 6fbe: iload 15
      // 6fc0: iload 15
      // 6fc2: iload 15
      // 6fc4: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 6fc7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 6fca: aload 0
      // 6fcb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6fce: sipush 225
      // 6fd1: bipush 59
      // 6fd3: aload 0
      // 6fd4: getfield Lobby.opengame I
      // 6fd7: i2f
      // 6fd8: ldc_w 2.23
      // 6fdb: fmul
      // 6fdc: f2i
      // 6fdd: isub
      // 6fde: sipush 495
      // 6fe1: sipush 200
      // 6fe4: aload 0
      // 6fe5: getfield Lobby.opengame I
      // 6fe8: bipush 8
      // 6fea: imul
      // 6feb: iadd
      // 6fec: bipush 20
      // 6fee: bipush 20
      // 6ff0: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 6ff3: aload 0
      // 6ff4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 6ff7: new java/awt/Color
      // 6ffa: dup
      // 6ffb: bipush 0
      // 6ffc: bipush 0
      // 6ffd: bipush 0
      // 6ffe: invokespecial java/awt/Color.<init> (III)V
      // 7001: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7004: aload 0
      // 7005: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7008: sipush 225
      // 700b: bipush 59
      // 700d: aload 0
      // 700e: getfield Lobby.opengame I
      // 7011: i2f
      // 7012: ldc_w 2.23
      // 7015: fmul
      // 7016: f2i
      // 7017: isub
      // 7018: sipush 495
      // 701b: sipush 200
      // 701e: aload 0
      // 701f: getfield Lobby.opengame I
      // 7022: bipush 8
      // 7024: imul
      // 7025: iadd
      // 7026: bipush 20
      // 7028: bipush 20
      // 702a: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 702d: aload 0
      // 702e: getfield Lobby.xt LxtGraphics;
      // 7031: getfield xtGraphics.lan Z
      // 7034: ifeq 703c
      // 7037: goto 70a4
      // 703c: aload 0
      // 703d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7040: aload 0
      // 7041: sipush 217
      // 7044: sipush 217
      // 7047: sipush 217
      // 704a: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 704d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7050: aload 0
      // 7051: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7054: sipush 225
      // 7057: sipush 263
      // 705a: aload 0
      // 705b: getfield Lobby.opengame I
      // 705e: bipush 7
      // 7060: imul
      // 7061: iadd
      // 7062: sipush 495
      // 7065: sipush 157
      // 7068: bipush 20
      // 706a: bipush 20
      // 706c: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 706f: aload 0
      // 7070: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7073: new java/awt/Color
      // 7076: dup
      // 7077: bipush 0
      // 7078: bipush 0
      // 7079: bipush 0
      // 707a: invokespecial java/awt/Color.<init> (III)V
      // 707d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7080: aload 0
      // 7081: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7084: sipush 225
      // 7087: sipush 263
      // 708a: aload 0
      // 708b: getfield Lobby.opengame I
      // 708e: bipush 7
      // 7090: imul
      // 7091: iadd
      // 7092: sipush 495
      // 7095: sipush 157
      // 7098: bipush 20
      // 709a: bipush 20
      // 709c: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 709f: goto 70a4
      // 70a4: aload 0
      // 70a5: bipush 0
      // 70a6: putfield Lobby.btn I
      // 70a9: aload 0
      // 70aa: getfield Lobby.prevloaded I
      // 70ad: bipush -1
      // 70ae: if_icmpne 70b6
      // 70b1: goto 70c0
      // 70b6: aload 0
      // 70b7: bipush -1
      // 70b8: putfield Lobby.prevloaded I
      // 70bb: goto 70c0
      // 70c0: aload 0
      // 70c1: getfield Lobby.updatec I
      // 70c4: bipush -1
      // 70c5: if_icmpne 70cd
      // 70c8: goto 70d7
      // 70cd: aload 0
      // 70ce: bipush -1
      // 70cf: putfield Lobby.updatec I
      // 70d2: goto 70d7
      // 70d7: aload 0
      // 70d8: getfield Lobby.gs LGameSparker;
      // 70db: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 70de: invokevirtual java/awt/TextField.isShowing ()Z
      // 70e1: ifne 70e9
      // 70e4: goto 70ff
      // 70e9: aload 0
      // 70ea: getfield Lobby.gs LGameSparker;
      // 70ed: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 70f0: invokevirtual java/awt/TextField.hide ()V
      // 70f3: aload 0
      // 70f4: getfield Lobby.gs LGameSparker;
      // 70f7: invokevirtual GameSparker.requestFocus ()V
      // 70fa: goto 70ff
      // 70ff: aload 0
      // 7100: getfield Lobby.gs LGameSparker;
      // 7103: getfield GameSparker.rooms LSmenu;
      // 7106: invokevirtual Smenu.isShowing ()Z
      // 7109: ifne 7111
      // 710c: goto 7120
      // 7111: aload 0
      // 7112: getfield Lobby.gs LGameSparker;
      // 7115: getfield GameSparker.rooms LSmenu;
      // 7118: invokevirtual Smenu.hide ()V
      // 711b: goto 7120
      // 7120: aload 0
      // 7121: getfield Lobby.fstart Z
      // 7124: ifne 712c
      // 7127: goto 7136
      // 712c: aload 0
      // 712d: bipush 0
      // 712e: putfield Lobby.fstart Z
      // 7131: goto 7136
      // 7136: bipush 0
      // 7137: istore 16
      // 7139: iload 16
      // 713b: bipush 9
      // 713d: if_icmplt 7145
      // 7140: goto 716a
      // 7145: aload 0
      // 7146: getfield Lobby.cac [I
      // 7149: iload 16
      // 714b: iaload
      // 714c: bipush -1
      // 714d: if_icmpne 7155
      // 7150: goto 7162
      // 7155: aload 0
      // 7156: getfield Lobby.cac [I
      // 7159: iload 16
      // 715b: bipush -1
      // 715c: iastore
      // 715d: goto 7162
      // 7162: iinc 16 1
      // 7165: goto 7139
      // 716a: aload 0
      // 716b: getfield Lobby.dispcar I
      // 716e: bipush -1
      // 716f: if_icmpne 7177
      // 7172: goto 7181
      // 7177: aload 0
      // 7178: bipush -1
      // 7179: putfield Lobby.dispcar I
      // 717c: goto 7181
      // 7181: aload 0
      // 7182: dup
      // 7183: getfield Lobby.opengame I
      // 7186: bipush 2
      // 7187: iadd
      // 7188: putfield Lobby.opengame I
      // 718b: goto c6f5
      // 7190: aload 0
      // 7191: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7194: aload 0
      // 7195: sipush 255
      // 7198: sipush 255
      // 719b: sipush 255
      // 719e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 71a1: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 71a4: aload 0
      // 71a5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 71a8: sipush 225
      // 71ab: bipush 1
      // 71ac: sipush 495
      // 71af: sipush 417
      // 71b2: bipush 20
      // 71b4: bipush 20
      // 71b6: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // 71b9: aload 0
      // 71ba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 71bd: new java/awt/Color
      // 71c0: dup
      // 71c1: bipush 0
      // 71c2: bipush 0
      // 71c3: bipush 0
      // 71c4: invokespecial java/awt/Color.<init> (III)V
      // 71c7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 71ca: aload 0
      // 71cb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 71ce: sipush 225
      // 71d1: bipush 1
      // 71d2: sipush 495
      // 71d5: sipush 417
      // 71d8: bipush 20
      // 71da: bipush 20
      // 71dc: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // 71df: aload 0
      // 71e0: getfield Lobby.join I
      // 71e3: bipush -1
      // 71e4: if_icmpgt 71ec
      // 71e7: goto 7201
      // 71ec: aload 0
      // 71ed: getfield Lobby.pgames [I
      // 71f0: aload 0
      // 71f1: getfield Lobby.im I
      // 71f4: iaload
      // 71f5: aload 0
      // 71f6: getfield Lobby.join I
      // 71f9: if_icmpeq 7201
      // 71fc: goto 720f
      // 7201: aload 0
      // 7202: getfield Lobby.join I
      // 7205: bipush -2
      // 7207: if_icmpeq 720f
      // 720a: goto 7491
      // 720f: aload 0
      // 7210: getfield Lobby.join I
      // 7213: bipush -1
      // 7214: if_icmpgt 721c
      // 7217: goto 7346
      // 721c: aload 0
      // 721d: getfield Lobby.pgames [I
      // 7220: aload 0
      // 7221: getfield Lobby.im I
      // 7224: iaload
      // 7225: aload 0
      // 7226: getfield Lobby.join I
      // 7229: if_icmpne 7231
      // 722c: goto 7346
      // 7231: aload 0
      // 7232: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7235: new java/awt/Font
      // 7238: dup
      // 7239: ldc_w "Arial"
      // 723c: bipush 1
      // 723d: bipush 13
      // 723f: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 7242: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 7245: aload 0
      // 7246: aload 0
      // 7247: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 724a: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 724d: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 7250: aload 0
      // 7251: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7254: aload 0
      // 7255: getfield Lobby.msg Ljava/lang/String;
      // 7258: sipush 472
      // 725b: aload 0
      // 725c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 725f: aload 0
      // 7260: getfield Lobby.msg Ljava/lang/String;
      // 7263: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 7266: bipush 2
      // 7267: idiv
      // 7268: isub
      // 7269: sipush 195
      // 726c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 726f: aload 0
      // 7270: getfield Lobby.msg Ljava/lang/String;
      // 7273: ldc_w ". . . | Joining Game | . . ."
      // 7276: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7279: ifne 7281
      // 727c: goto 729e
      // 7281: aload 0
      // 7282: getfield Lobby.ncnt I
      // 7285: ifeq 728d
      // 7288: goto 729e
      // 728d: aload 0
      // 728e: ldc_w "| Joining Game |"
      // 7291: putfield Lobby.msg Ljava/lang/String;
      // 7294: aload 0
      // 7295: bipush 5
      // 7296: putfield Lobby.ncnt I
      // 7299: goto 729e
      // 729e: aload 0
      // 729f: getfield Lobby.msg Ljava/lang/String;
      // 72a2: ldc_w ". . | Joining Game | . ."
      // 72a5: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 72a8: ifne 72b0
      // 72ab: goto 72cd
      // 72b0: aload 0
      // 72b1: getfield Lobby.ncnt I
      // 72b4: ifeq 72bc
      // 72b7: goto 72cd
      // 72bc: aload 0
      // 72bd: ldc_w ". . . | Joining Game | . . ."
      // 72c0: putfield Lobby.msg Ljava/lang/String;
      // 72c3: aload 0
      // 72c4: bipush 5
      // 72c5: putfield Lobby.ncnt I
      // 72c8: goto 72cd
      // 72cd: aload 0
      // 72ce: getfield Lobby.msg Ljava/lang/String;
      // 72d1: ldc_w ". | Joining Game | ."
      // 72d4: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 72d7: ifne 72df
      // 72da: goto 72fc
      // 72df: aload 0
      // 72e0: getfield Lobby.ncnt I
      // 72e3: ifeq 72eb
      // 72e6: goto 72fc
      // 72eb: aload 0
      // 72ec: ldc_w ". . | Joining Game | . ."
      // 72ef: putfield Lobby.msg Ljava/lang/String;
      // 72f2: aload 0
      // 72f3: bipush 5
      // 72f4: putfield Lobby.ncnt I
      // 72f7: goto 72fc
      // 72fc: aload 0
      // 72fd: getfield Lobby.msg Ljava/lang/String;
      // 7300: ldc_w "| Joining Game |"
      // 7303: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7306: ifne 730e
      // 7309: goto 732b
      // 730e: aload 0
      // 730f: getfield Lobby.ncnt I
      // 7312: ifeq 731a
      // 7315: goto 732b
      // 731a: aload 0
      // 731b: ldc_w ". | Joining Game | ."
      // 731e: putfield Lobby.msg Ljava/lang/String;
      // 7321: aload 0
      // 7322: bipush 5
      // 7323: putfield Lobby.ncnt I
      // 7326: goto 732b
      // 732b: aload 0
      // 732c: getfield Lobby.ncnt I
      // 732f: ifne 7337
      // 7332: goto 7346
      // 7337: aload 0
      // 7338: dup
      // 7339: getfield Lobby.ncnt I
      // 733c: bipush 1
      // 733d: isub
      // 733e: putfield Lobby.ncnt I
      // 7341: goto 7346
      // 7346: aload 0
      // 7347: getfield Lobby.join I
      // 734a: bipush -2
      // 734c: if_icmpeq 7354
      // 734f: goto 7469
      // 7354: aload 0
      // 7355: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7358: new java/awt/Font
      // 735b: dup
      // 735c: ldc_w "Arial"
      // 735f: bipush 1
      // 7360: bipush 13
      // 7362: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 7365: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 7368: aload 0
      // 7369: aload 0
      // 736a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 736d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 7370: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 7373: aload 0
      // 7374: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7377: aload 0
      // 7378: getfield Lobby.msg Ljava/lang/String;
      // 737b: sipush 472
      // 737e: aload 0
      // 737f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 7382: aload 0
      // 7383: getfield Lobby.msg Ljava/lang/String;
      // 7386: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 7389: bipush 2
      // 738a: idiv
      // 738b: isub
      // 738c: sipush 195
      // 738f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7392: aload 0
      // 7393: getfield Lobby.msg Ljava/lang/String;
      // 7396: ldc_w ". . . | Leaving Game | . . ."
      // 7399: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 739c: ifne 73a4
      // 739f: goto 73c1
      // 73a4: aload 0
      // 73a5: getfield Lobby.ncnt I
      // 73a8: ifeq 73b0
      // 73ab: goto 73c1
      // 73b0: aload 0
      // 73b1: ldc_w "| Leaving Game |"
      // 73b4: putfield Lobby.msg Ljava/lang/String;
      // 73b7: aload 0
      // 73b8: bipush 5
      // 73b9: putfield Lobby.ncnt I
      // 73bc: goto 73c1
      // 73c1: aload 0
      // 73c2: getfield Lobby.msg Ljava/lang/String;
      // 73c5: ldc_w ". . | Leaving Game | . ."
      // 73c8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 73cb: ifne 73d3
      // 73ce: goto 73f0
      // 73d3: aload 0
      // 73d4: getfield Lobby.ncnt I
      // 73d7: ifeq 73df
      // 73da: goto 73f0
      // 73df: aload 0
      // 73e0: ldc_w ". . . | Leaving Game | . . ."
      // 73e3: putfield Lobby.msg Ljava/lang/String;
      // 73e6: aload 0
      // 73e7: bipush 5
      // 73e8: putfield Lobby.ncnt I
      // 73eb: goto 73f0
      // 73f0: aload 0
      // 73f1: getfield Lobby.msg Ljava/lang/String;
      // 73f4: ldc_w ". | Leaving Game | ."
      // 73f7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 73fa: ifne 7402
      // 73fd: goto 741f
      // 7402: aload 0
      // 7403: getfield Lobby.ncnt I
      // 7406: ifeq 740e
      // 7409: goto 741f
      // 740e: aload 0
      // 740f: ldc_w ". . | Leaving Game | . ."
      // 7412: putfield Lobby.msg Ljava/lang/String;
      // 7415: aload 0
      // 7416: bipush 5
      // 7417: putfield Lobby.ncnt I
      // 741a: goto 741f
      // 741f: aload 0
      // 7420: getfield Lobby.msg Ljava/lang/String;
      // 7423: ldc_w "| Leaving Game |"
      // 7426: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7429: ifne 7431
      // 742c: goto 744e
      // 7431: aload 0
      // 7432: getfield Lobby.ncnt I
      // 7435: ifeq 743d
      // 7438: goto 744e
      // 743d: aload 0
      // 743e: ldc_w ". | Leaving Game | ."
      // 7441: putfield Lobby.msg Ljava/lang/String;
      // 7444: aload 0
      // 7445: bipush 5
      // 7446: putfield Lobby.ncnt I
      // 7449: goto 744e
      // 744e: aload 0
      // 744f: getfield Lobby.ncnt I
      // 7452: ifne 745a
      // 7455: goto 7469
      // 745a: aload 0
      // 745b: dup
      // 745c: getfield Lobby.ncnt I
      // 745f: bipush 1
      // 7460: isub
      // 7461: putfield Lobby.ncnt I
      // 7464: goto 7469
      // 7469: aload 0
      // 746a: getfield Lobby.gs LGameSparker;
      // 746d: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 7470: invokevirtual java/awt/TextField.isShowing ()Z
      // 7473: ifne 747b
      // 7476: goto c6f5
      // 747b: aload 0
      // 747c: getfield Lobby.gs LGameSparker;
      // 747f: getfield GameSparker.cmsg Ljava/awt/TextField;
      // 7482: invokevirtual java/awt/TextField.hide ()V
      // 7485: aload 0
      // 7486: getfield Lobby.gs LGameSparker;
      // 7489: invokevirtual GameSparker.requestFocus ()V
      // 748c: goto c6f5
      // 7491: bipush 0
      // 7492: istore 15
      // 7494: bipush 0
      // 7495: istore 16
      // 7497: iload 16
      // 7499: aload 0
      // 749a: getfield Lobby.ngm I
      // 749d: if_icmplt 74a5
      // 74a0: goto 74c9
      // 74a5: aload 0
      // 74a6: getfield Lobby.ongame I
      // 74a9: aload 0
      // 74aa: getfield Lobby.gnum [I
      // 74ad: iload 16
      // 74af: iaload
      // 74b0: if_icmpeq 74b8
      // 74b3: goto 74c1
      // 74b8: iload 16
      // 74ba: istore 15
      // 74bc: goto 74c1
      // 74c1: iinc 16 1
      // 74c4: goto 7497
      // 74c9: aload 0
      // 74ca: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 74cd: new java/awt/Font
      // 74d0: dup
      // 74d1: ldc_w "Arial"
      // 74d4: bipush 1
      // 74d5: bipush 11
      // 74d7: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 74da: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 74dd: aload 0
      // 74de: aload 0
      // 74df: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 74e2: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 74e5: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 74e8: aload 0
      // 74e9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 74ec: new java/awt/Color
      // 74ef: dup
      // 74f0: bipush 0
      // 74f1: bipush 0
      // 74f2: bipush 0
      // 74f3: invokespecial java/awt/Color.<init> (III)V
      // 74f6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 74f9: bipush 23
      // 74fb: istore 16
      // 74fd: bipush 0
      // 74fe: istore 17
      // 7500: aload 0
      // 7501: getfield Lobby.gwarb [I
      // 7504: iload 15
      // 7506: iaload
      // 7507: ifne 750f
      // 750a: goto 7599
      // 750f: bipush 28
      // 7511: istore 16
      // 7513: bipush 2
      // 7514: istore 17
      // 7516: ldc_w "Clan war"
      // 7519: astore 18
      // 751b: aload 0
      // 751c: getfield Lobby.gwarb [I
      // 751f: iload 15
      // 7521: iaload
      // 7522: bipush 2
      // 7523: if_icmpeq 752b
      // 7526: goto 7535
      // 752b: ldc_w "Car battle"
      // 752e: astore 18
      // 7530: goto 7535
      // 7535: aload 0
      // 7536: getfield Lobby.gwarb [I
      // 7539: iload 15
      // 753b: iaload
      // 753c: bipush 3
      // 753d: if_icmpeq 7545
      // 7540: goto 754f
      // 7545: ldc_w "Stage battle"
      // 7548: astore 18
      // 754a: goto 754f
      // 754f: aload 0
      // 7550: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7553: new java/lang/StringBuilder
      // 7556: dup
      // 7557: invokespecial java/lang/StringBuilder.<init> ()V
      // 755a: ldc ""
      // 755c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 755f: aload 18
      // 7561: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7564: ldc_w " between "
      // 7567: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 756a: aload 0
      // 756b: getfield Lobby.gaclan [Ljava/lang/String;
      // 756e: iload 15
      // 7570: aaload
      // 7571: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7574: ldc_w " and "
      // 7577: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 757a: aload 0
      // 757b: getfield Lobby.gvclan [Ljava/lang/String;
      // 757e: iload 15
      // 7580: aaload
      // 7581: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7584: ldc ""
      // 7586: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7589: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 758c: sipush 243
      // 758f: bipush 14
      // 7591: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7594: goto 7599
      // 7599: ldc ""
      // 759b: astore 18
      // 759d: ldc ""
      // 759f: astore 19
      // 75a1: ldc ""
      // 75a3: astore 20
      // 75a5: aload 0
      // 75a6: getfield Lobby.conon I
      // 75a9: bipush 1
      // 75aa: if_icmpeq 75b2
      // 75ad: goto 7bd2
      // 75b2: aload 0
      // 75b3: getfield Lobby.wait [I
      // 75b6: iload 15
      // 75b8: iaload
      // 75b9: ifgt 75c1
      // 75bc: goto 7b19
      // 75c1: aload 0
      // 75c2: getfield Lobby.gwarb [I
      // 75c5: iload 15
      // 75c7: iaload
      // 75c8: ifeq 75d0
      // 75cb: goto 78c1
      // 75d0: ldc ""
      // 75d2: astore 21
      // 75d4: aload 0
      // 75d5: getfield Lobby.gplyrs [Ljava/lang/String;
      // 75d8: iload 15
      // 75da: aaload
      // 75db: ldc ""
      // 75dd: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 75e0: ifne 75e8
      // 75e3: goto 75f2
      // 75e8: ldc_w "Public Game"
      // 75eb: astore 21
      // 75ed: goto 75f7
      // 75f2: ldc_w "Private Game"
      // 75f5: astore 21
      // 75f7: aload 0
      // 75f8: getfield Lobby.gfx [I
      // 75fb: iload 15
      // 75fd: iaload
      // 75fe: bipush 1
      // 75ff: if_icmpeq 7607
      // 7602: goto 7623
      // 7607: new java/lang/StringBuilder
      // 760a: dup
      // 760b: invokespecial java/lang/StringBuilder.<init> ()V
      // 760e: aload 21
      // 7610: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7613: ldc_w " | 4 Fixes"
      // 7616: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7619: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 761c: astore 21
      // 761e: goto 7623
      // 7623: aload 0
      // 7624: getfield Lobby.gfx [I
      // 7627: iload 15
      // 7629: iaload
      // 762a: bipush 2
      // 762b: if_icmpeq 7633
      // 762e: goto 764f
      // 7633: new java/lang/StringBuilder
      // 7636: dup
      // 7637: invokespecial java/lang/StringBuilder.<init> ()V
      // 763a: aload 21
      // 763c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 763f: ldc_w " | 3 Fixes"
      // 7642: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7645: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7648: astore 21
      // 764a: goto 764f
      // 764f: aload 0
      // 7650: getfield Lobby.gfx [I
      // 7653: iload 15
      // 7655: iaload
      // 7656: bipush 3
      // 7657: if_icmpeq 765f
      // 765a: goto 767b
      // 765f: new java/lang/StringBuilder
      // 7662: dup
      // 7663: invokespecial java/lang/StringBuilder.<init> ()V
      // 7666: aload 21
      // 7668: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 766b: ldc_w " | 2 Fixes"
      // 766e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7671: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7674: astore 21
      // 7676: goto 767b
      // 767b: aload 0
      // 767c: getfield Lobby.gfx [I
      // 767f: iload 15
      // 7681: iaload
      // 7682: bipush 4
      // 7683: if_icmpeq 768b
      // 7686: goto 76a7
      // 768b: new java/lang/StringBuilder
      // 768e: dup
      // 768f: invokespecial java/lang/StringBuilder.<init> ()V
      // 7692: aload 21
      // 7694: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7697: ldc_w " | 1 Fix"
      // 769a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 769d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 76a0: astore 21
      // 76a2: goto 76a7
      // 76a7: aload 0
      // 76a8: getfield Lobby.gfx [I
      // 76ab: iload 15
      // 76ad: iaload
      // 76ae: bipush 5
      // 76af: if_icmpeq 76b7
      // 76b2: goto 76d3
      // 76b7: new java/lang/StringBuilder
      // 76ba: dup
      // 76bb: invokespecial java/lang/StringBuilder.<init> ()V
      // 76be: aload 21
      // 76c0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 76c3: ldc_w " | No Fixing"
      // 76c6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 76c9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 76cc: astore 21
      // 76ce: goto 76d3
      // 76d3: ldc ""
      // 76d5: astore 22
      // 76d7: aload 0
      // 76d8: getfield Lobby.gclss [I
      // 76db: iload 15
      // 76dd: iaload
      // 76de: bipush -2
      // 76e0: if_icmpgt 76e8
      // 76e3: goto 77fc
      // 76e8: aload 0
      // 76e9: getfield Lobby.gcrs [I
      // 76ec: iload 15
      // 76ee: iaload
      // 76ef: bipush 1
      // 76f0: if_icmpeq 76f8
      // 76f3: goto 7702
      // 76f8: ldc_w "Custom Cars"
      // 76fb: astore 22
      // 76fd: goto 7702
      // 7702: aload 0
      // 7703: getfield Lobby.gcrs [I
      // 7706: iload 15
      // 7708: iaload
      // 7709: bipush 2
      // 770a: if_icmpeq 7712
      // 770d: goto 771c
      // 7712: ldc_w "Game Cars"
      // 7715: astore 22
      // 7717: goto 771c
      // 771c: ldc ""
      // 771e: astore 23
      // 7720: aload 0
      // 7721: getfield Lobby.gclss [I
      // 7724: iload 15
      // 7726: iaload
      // 7727: bipush 1
      // 7728: if_icmpeq 7730
      // 772b: goto 773a
      // 7730: ldc_w "Class C"
      // 7733: astore 23
      // 7735: goto 773a
      // 773a: aload 0
      // 773b: getfield Lobby.gclss [I
      // 773e: iload 15
      // 7740: iaload
      // 7741: bipush 2
      // 7742: if_icmpeq 774a
      // 7745: goto 7754
      // 774a: ldc_w "Class B & C"
      // 774d: astore 23
      // 774f: goto 7754
      // 7754: aload 0
      // 7755: getfield Lobby.gclss [I
      // 7758: iload 15
      // 775a: iaload
      // 775b: bipush 3
      // 775c: if_icmpeq 7764
      // 775f: goto 776e
      // 7764: ldc_w "Class B"
      // 7767: astore 23
      // 7769: goto 776e
      // 776e: aload 0
      // 776f: getfield Lobby.gclss [I
      // 7772: iload 15
      // 7774: iaload
      // 7775: bipush 4
      // 7776: if_icmpeq 777e
      // 7779: goto 7788
      // 777e: ldc_w "Class A & B"
      // 7781: astore 23
      // 7783: goto 7788
      // 7788: aload 0
      // 7789: getfield Lobby.gclss [I
      // 778c: iload 15
      // 778e: iaload
      // 778f: bipush 5
      // 7790: if_icmpeq 7798
      // 7793: goto 77a2
      // 7798: ldc_w "Class A"
      // 779b: astore 23
      // 779d: goto 77a2
      // 77a2: aload 22
      // 77a4: ldc ""
      // 77a6: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 77a9: ifeq 77b1
      // 77ac: goto 77e1
      // 77b1: aload 23
      // 77b3: ldc ""
      // 77b5: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 77b8: ifeq 77c0
      // 77bb: goto 77e1
      // 77c0: new java/lang/StringBuilder
      // 77c3: dup
      // 77c4: invokespecial java/lang/StringBuilder.<init> ()V
      // 77c7: aload 22
      // 77c9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 77cc: ldc_w " | "
      // 77cf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 77d2: aload 23
      // 77d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 77d7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 77da: astore 22
      // 77dc: goto 77f7
      // 77e1: new java/lang/StringBuilder
      // 77e4: dup
      // 77e5: invokespecial java/lang/StringBuilder.<init> ()V
      // 77e8: aload 22
      // 77ea: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 77ed: aload 23
      // 77ef: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 77f2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 77f5: astore 22
      // 77f7: goto 7829
      // 77fc: new java/lang/StringBuilder
      // 77ff: dup
      // 7800: invokespecial java/lang/StringBuilder.<init> ()V
      // 7803: ldc ""
      // 7805: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7808: aload 0
      // 7809: getfield Lobby.cd LCarDefine;
      // 780c: getfield CarDefine.names [Ljava/lang/String;
      // 780f: aload 0
      // 7810: getfield Lobby.gclss [I
      // 7813: iload 15
      // 7815: iaload
      // 7816: bipush 2
      // 7817: iadd
      // 7818: invokestatic java/lang/Math.abs (I)I
      // 781b: aaload
      // 781c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 781f: ldc ""
      // 7821: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7824: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7827: astore 22
      // 7829: aload 22
      // 782b: ldc ""
      // 782d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7830: ifne 7838
      // 7833: goto 786e
      // 7838: aload 0
      // 7839: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 783c: ldc_w "Type:"
      // 783f: sipush 243
      // 7842: bipush 23
      // 7844: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7847: aload 0
      // 7848: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 784b: new java/awt/Color
      // 784e: dup
      // 784f: bipush 80
      // 7851: sipush 128
      // 7854: bipush 0
      // 7855: invokespecial java/awt/Color.<init> (III)V
      // 7858: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 785b: aload 0
      // 785c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 785f: aload 21
      // 7861: sipush 279
      // 7864: bipush 23
      // 7866: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7869: goto 78bc
      // 786e: aload 0
      // 786f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7872: ldc_w "Type:"
      // 7875: sipush 243
      // 7878: bipush 14
      // 787a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 787d: aload 0
      // 787e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7881: ldc_w "Cars:"
      // 7884: sipush 244
      // 7887: bipush 28
      // 7889: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 788c: aload 0
      // 788d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7890: new java/awt/Color
      // 7893: dup
      // 7894: bipush 80
      // 7896: sipush 128
      // 7899: bipush 0
      // 789a: invokespecial java/awt/Color.<init> (III)V
      // 789d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 78a0: aload 0
      // 78a1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 78a4: aload 21
      // 78a6: sipush 279
      // 78a9: bipush 14
      // 78ab: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 78ae: aload 0
      // 78af: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 78b2: aload 22
      // 78b4: sipush 279
      // 78b7: bipush 28
      // 78b9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 78bc: goto 7b19
      // 78c1: new java/lang/StringBuilder
      // 78c4: dup
      // 78c5: invokespecial java/lang/StringBuilder.<init> ()V
      // 78c8: ldc_w "Game #"
      // 78cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 78ce: aload 0
      // 78cf: getfield Lobby.gameturn [I
      // 78d2: iload 15
      // 78d4: iaload
      // 78d5: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 78d8: ldc ""
      // 78da: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 78dd: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 78e0: astore 21
      // 78e2: aload 0
      // 78e3: getfield Lobby.gcrs [I
      // 78e6: iload 15
      // 78e8: iaload
      // 78e9: bipush 1
      // 78ea: if_icmpeq 78f2
      // 78ed: goto 790e
      // 78f2: new java/lang/StringBuilder
      // 78f5: dup
      // 78f6: invokespecial java/lang/StringBuilder.<init> ()V
      // 78f9: aload 21
      // 78fb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 78fe: ldc_w " | Clan Cars"
      // 7901: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7904: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7907: astore 21
      // 7909: goto 790e
      // 790e: aload 0
      // 790f: getfield Lobby.gcrs [I
      // 7912: iload 15
      // 7914: iaload
      // 7915: bipush 2
      // 7916: if_icmpeq 791e
      // 7919: goto 793a
      // 791e: new java/lang/StringBuilder
      // 7921: dup
      // 7922: invokespecial java/lang/StringBuilder.<init> ()V
      // 7925: aload 21
      // 7927: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 792a: ldc_w " | Game Cars"
      // 792d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7930: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7933: astore 21
      // 7935: goto 793a
      // 793a: aload 0
      // 793b: getfield Lobby.gclss [I
      // 793e: iload 15
      // 7940: iaload
      // 7941: bipush 1
      // 7942: if_icmpeq 794a
      // 7945: goto 7966
      // 794a: new java/lang/StringBuilder
      // 794d: dup
      // 794e: invokespecial java/lang/StringBuilder.<init> ()V
      // 7951: aload 21
      // 7953: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7956: ldc_w " | Class C"
      // 7959: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 795c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 795f: astore 21
      // 7961: goto 7966
      // 7966: aload 0
      // 7967: getfield Lobby.gclss [I
      // 796a: iload 15
      // 796c: iaload
      // 796d: bipush 2
      // 796e: if_icmpeq 7976
      // 7971: goto 7992
      // 7976: new java/lang/StringBuilder
      // 7979: dup
      // 797a: invokespecial java/lang/StringBuilder.<init> ()V
      // 797d: aload 21
      // 797f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7982: ldc_w " | Class B & C"
      // 7985: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7988: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 798b: astore 21
      // 798d: goto 7992
      // 7992: aload 0
      // 7993: getfield Lobby.gclss [I
      // 7996: iload 15
      // 7998: iaload
      // 7999: bipush 3
      // 799a: if_icmpeq 79a2
      // 799d: goto 79be
      // 79a2: new java/lang/StringBuilder
      // 79a5: dup
      // 79a6: invokespecial java/lang/StringBuilder.<init> ()V
      // 79a9: aload 21
      // 79ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 79ae: ldc_w " | Class B"
      // 79b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 79b4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 79b7: astore 21
      // 79b9: goto 79be
      // 79be: aload 0
      // 79bf: getfield Lobby.gclss [I
      // 79c2: iload 15
      // 79c4: iaload
      // 79c5: bipush 4
      // 79c6: if_icmpeq 79ce
      // 79c9: goto 79ea
      // 79ce: new java/lang/StringBuilder
      // 79d1: dup
      // 79d2: invokespecial java/lang/StringBuilder.<init> ()V
      // 79d5: aload 21
      // 79d7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 79da: ldc_w " | Class A & B"
      // 79dd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 79e0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 79e3: astore 21
      // 79e5: goto 79ea
      // 79ea: aload 0
      // 79eb: getfield Lobby.gclss [I
      // 79ee: iload 15
      // 79f0: iaload
      // 79f1: bipush 5
      // 79f2: if_icmpeq 79fa
      // 79f5: goto 7a16
      // 79fa: new java/lang/StringBuilder
      // 79fd: dup
      // 79fe: invokespecial java/lang/StringBuilder.<init> ()V
      // 7a01: aload 21
      // 7a03: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a06: ldc_w " | Class A"
      // 7a09: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a0c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7a0f: astore 21
      // 7a11: goto 7a16
      // 7a16: aload 0
      // 7a17: getfield Lobby.gfx [I
      // 7a1a: iload 15
      // 7a1c: iaload
      // 7a1d: bipush 1
      // 7a1e: if_icmpeq 7a26
      // 7a21: goto 7a42
      // 7a26: new java/lang/StringBuilder
      // 7a29: dup
      // 7a2a: invokespecial java/lang/StringBuilder.<init> ()V
      // 7a2d: aload 21
      // 7a2f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a32: ldc_w " | 4 Fixes"
      // 7a35: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a38: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7a3b: astore 21
      // 7a3d: goto 7a42
      // 7a42: aload 0
      // 7a43: getfield Lobby.gfx [I
      // 7a46: iload 15
      // 7a48: iaload
      // 7a49: bipush 2
      // 7a4a: if_icmpeq 7a52
      // 7a4d: goto 7a6e
      // 7a52: new java/lang/StringBuilder
      // 7a55: dup
      // 7a56: invokespecial java/lang/StringBuilder.<init> ()V
      // 7a59: aload 21
      // 7a5b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a5e: ldc_w " | 3 Fixes"
      // 7a61: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a64: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7a67: astore 21
      // 7a69: goto 7a6e
      // 7a6e: aload 0
      // 7a6f: getfield Lobby.gfx [I
      // 7a72: iload 15
      // 7a74: iaload
      // 7a75: bipush 3
      // 7a76: if_icmpeq 7a7e
      // 7a79: goto 7a9a
      // 7a7e: new java/lang/StringBuilder
      // 7a81: dup
      // 7a82: invokespecial java/lang/StringBuilder.<init> ()V
      // 7a85: aload 21
      // 7a87: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a8a: ldc_w " | 2 Fixes"
      // 7a8d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7a90: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7a93: astore 21
      // 7a95: goto 7a9a
      // 7a9a: aload 0
      // 7a9b: getfield Lobby.gfx [I
      // 7a9e: iload 15
      // 7aa0: iaload
      // 7aa1: bipush 4
      // 7aa2: if_icmpeq 7aaa
      // 7aa5: goto 7ac6
      // 7aaa: new java/lang/StringBuilder
      // 7aad: dup
      // 7aae: invokespecial java/lang/StringBuilder.<init> ()V
      // 7ab1: aload 21
      // 7ab3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7ab6: ldc_w " | 1 Fix"
      // 7ab9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7abc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7abf: astore 21
      // 7ac1: goto 7ac6
      // 7ac6: aload 0
      // 7ac7: getfield Lobby.gfx [I
      // 7aca: iload 15
      // 7acc: iaload
      // 7acd: bipush 5
      // 7ace: if_icmpeq 7ad6
      // 7ad1: goto 7af2
      // 7ad6: new java/lang/StringBuilder
      // 7ad9: dup
      // 7ada: invokespecial java/lang/StringBuilder.<init> ()V
      // 7add: aload 21
      // 7adf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7ae2: ldc_w " | No Fixing"
      // 7ae5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7ae8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7aeb: astore 21
      // 7aed: goto 7af2
      // 7af2: aload 0
      // 7af3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7af6: new java/awt/Color
      // 7af9: dup
      // 7afa: bipush 80
      // 7afc: sipush 128
      // 7aff: bipush 0
      // 7b00: invokespecial java/awt/Color.<init> (III)V
      // 7b03: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7b06: aload 0
      // 7b07: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b0a: aload 21
      // 7b0c: sipush 243
      // 7b0f: bipush 28
      // 7b11: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7b14: goto 7b19
      // 7b19: aload 0
      // 7b1a: getfield Lobby.wait [I
      // 7b1d: iload 15
      // 7b1f: iaload
      // 7b20: ifeq 7b28
      // 7b23: goto 7b88
      // 7b28: aload 0
      // 7b29: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b2c: ldc_w "Status:"
      // 7b2f: sipush 241
      // 7b32: iload 17
      // 7b34: iadd
      // 7b35: iload 16
      // 7b37: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7b3a: aload 0
      // 7b3b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b3e: new java/awt/Color
      // 7b41: dup
      // 7b42: sipush 128
      // 7b45: bipush 73
      // 7b47: bipush 0
      // 7b48: invokespecial java/awt/Color.<init> (III)V
      // 7b4b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7b4e: aload 0
      // 7b4f: getfield Lobby.prevloaded I
      // 7b52: ifeq 7b5a
      // 7b55: goto 7b71
      // 7b5a: aload 0
      // 7b5b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b5e: ldc_w "Starting..."
      // 7b61: sipush 286
      // 7b64: iload 17
      // 7b66: iadd
      // 7b67: iload 16
      // 7b69: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7b6c: goto 7b88
      // 7b71: aload 0
      // 7b72: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b75: ldc_w "Started"
      // 7b78: sipush 286
      // 7b7b: iload 17
      // 7b7d: iadd
      // 7b7e: iload 16
      // 7b80: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7b83: goto 7b88
      // 7b88: aload 0
      // 7b89: getfield Lobby.wait [I
      // 7b8c: iload 15
      // 7b8e: iaload
      // 7b8f: bipush -1
      // 7b90: if_icmpeq 7b98
      // 7b93: goto 7c0a
      // 7b98: aload 0
      // 7b99: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7b9c: ldc_w "Status:"
      // 7b9f: sipush 241
      // 7ba2: iload 17
      // 7ba4: iadd
      // 7ba5: iload 16
      // 7ba7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7baa: aload 0
      // 7bab: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7bae: aload 0
      // 7baf: bipush 100
      // 7bb1: bipush 100
      // 7bb3: bipush 100
      // 7bb5: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 7bb8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7bbb: aload 0
      // 7bbc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7bbf: ldc_w "Finished"
      // 7bc2: sipush 286
      // 7bc5: iload 17
      // 7bc7: iadd
      // 7bc8: iload 16
      // 7bca: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7bcd: goto 7c0a
      // 7bd2: aload 0
      // 7bd3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7bd6: ldc_w "Status:"
      // 7bd9: sipush 241
      // 7bdc: iload 17
      // 7bde: iadd
      // 7bdf: iload 16
      // 7be1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7be4: aload 0
      // 7be5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7be8: new java/awt/Color
      // 7beb: dup
      // 7bec: sipush 128
      // 7bef: bipush 73
      // 7bf1: bipush 0
      // 7bf2: invokespecial java/awt/Color.<init> (III)V
      // 7bf5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7bf8: aload 0
      // 7bf9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7bfc: ldc_w "Starting..."
      // 7bff: sipush 286
      // 7c02: iload 17
      // 7c04: iadd
      // 7c05: iload 16
      // 7c07: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7c0a: aload 0
      // 7c0b: getfield Lobby.gwarb [I
      // 7c0e: iload 15
      // 7c10: iaload
      // 7c11: ifeq 7c19
      // 7c14: goto 7c8e
      // 7c19: aload 0
      // 7c1a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7c1d: new java/awt/Color
      // 7c20: dup
      // 7c21: bipush 0
      // 7c22: bipush 0
      // 7c23: bipush 0
      // 7c24: invokespecial java/awt/Color.<init> (III)V
      // 7c27: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7c2a: aload 0
      // 7c2b: getfield Lobby.gmaker [Ljava/lang/String;
      // 7c2e: iload 15
      // 7c30: aaload
      // 7c31: aload 0
      // 7c32: getfield Lobby.pnames [Ljava/lang/String;
      // 7c35: aload 0
      // 7c36: getfield Lobby.im I
      // 7c39: aaload
      // 7c3a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7c3d: ifne 7c45
      // 7c40: goto 7c59
      // 7c45: aload 0
      // 7c46: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7c49: ldc_w "Created by You"
      // 7c4c: sipush 449
      // 7c4f: bipush 23
      // 7c51: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7c54: goto 83ea
      // 7c59: aload 0
      // 7c5a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7c5d: ldc_w "Created by"
      // 7c60: sipush 449
      // 7c63: bipush 23
      // 7c65: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7c68: aload 0
      // 7c69: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7c6c: ldc ":"
      // 7c6e: sipush 511
      // 7c71: bipush 23
      // 7c73: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7c76: aload 0
      // 7c77: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7c7a: aload 0
      // 7c7b: getfield Lobby.gmaker [Ljava/lang/String;
      // 7c7e: iload 15
      // 7c80: aaload
      // 7c81: sipush 520
      // 7c84: bipush 23
      // 7c86: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 7c89: goto 83ea
      // 7c8e: aload 0
      // 7c8f: getfield Lobby.wait [I
      // 7c92: iload 15
      // 7c94: iaload
      // 7c95: ifne 7c9d
      // 7c98: goto 7cba
      // 7c9d: aload 0
      // 7c9e: getfield Lobby.wait [I
      // 7ca1: iload 15
      // 7ca3: iaload
      // 7ca4: bipush -1
      // 7ca5: if_icmpne 7cad
      // 7ca8: goto 7cba
      // 7cad: aload 0
      // 7cae: getfield Lobby.conon I
      // 7cb1: bipush 1
      // 7cb2: if_icmpne 7cba
      // 7cb5: goto 83ea
      // 7cba: aload 0
      // 7cbb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7cbe: aload 0
      // 7cbf: sipush 200
      // 7cc2: sipush 200
      // 7cc5: sipush 200
      // 7cc8: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 7ccb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7cce: aload 0
      // 7ccf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7cd2: sipush 349
      // 7cd5: bipush 16
      // 7cd7: sipush 253
      // 7cda: bipush 16
      // 7cdc: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 7cdf: aload 0
      // 7ce0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7ce3: new java/awt/Font
      // 7ce6: dup
      // 7ce7: ldc_w "Arial"
      // 7cea: bipush 0
      // 7ceb: bipush 11
      // 7ced: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 7cf0: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 7cf3: aload 0
      // 7cf4: aload 0
      // 7cf5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7cf8: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 7cfb: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 7cfe: aload 0
      // 7cff: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 7d02: new java/awt/Color
      // 7d05: dup
      // 7d06: bipush 0
      // 7d07: bipush 0
      // 7d08: bipush 0
      // 7d09: invokespecial java/awt/Color.<init> (III)V
      // 7d0c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 7d0f: bipush 0
      // 7d10: istore 21
      // 7d12: bipush 0
      // 7d13: istore 22
      // 7d15: aload 0
      // 7d16: getfield Lobby.wait [I
      // 7d19: iload 15
      // 7d1b: iaload
      // 7d1c: bipush -1
      // 7d1d: if_icmpeq 7d25
      // 7d20: goto 8239
      // 7d25: bipush 8
      // 7d27: anewarray 30
      // 7d2a: dup
      // 7d2b: bipush 0
      // 7d2c: ldc ""
      // 7d2e: aastore
      // 7d2f: dup
      // 7d30: bipush 1
      // 7d31: ldc ""
      // 7d33: aastore
      // 7d34: dup
      // 7d35: bipush 2
      // 7d36: ldc ""
      // 7d38: aastore
      // 7d39: dup
      // 7d3a: bipush 3
      // 7d3b: ldc ""
      // 7d3d: aastore
      // 7d3e: dup
      // 7d3f: bipush 4
      // 7d40: ldc ""
      // 7d42: aastore
      // 7d43: dup
      // 7d44: bipush 5
      // 7d45: ldc ""
      // 7d47: aastore
      // 7d48: dup
      // 7d49: bipush 6
      // 7d4b: ldc ""
      // 7d4d: aastore
      // 7d4e: dup
      // 7d4f: bipush 7
      // 7d51: ldc ""
      // 7d53: aastore
      // 7d54: astore 23
      // 7d56: bipush 0
      // 7d57: istore 24
      // 7d59: iload 24
      // 7d5b: aload 0
      // 7d5c: getfield Lobby.prnpo I
      // 7d5f: if_icmplt 7d67
      // 7d62: goto 7dc8
      // 7d67: bipush 0
      // 7d68: istore 25
      // 7d6a: iload 25
      // 7d6c: aload 0
      // 7d6d: getfield Lobby.npo I
      // 7d70: if_icmplt 7d78
      // 7d73: goto 7dc0
      // 7d78: aload 0
      // 7d79: getfield Lobby.prnames [Ljava/lang/String;
      // 7d7c: iload 24
      // 7d7e: aaload
      // 7d7f: aload 0
      // 7d80: getfield Lobby.pnames [Ljava/lang/String;
      // 7d83: iload 25
      // 7d85: aaload
      // 7d86: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7d89: ifne 7d91
      // 7d8c: goto 7db8
      // 7d91: aload 0
      // 7d92: getfield Lobby.pgames [I
      // 7d95: iload 25
      // 7d97: iaload
      // 7d98: aload 0
      // 7d99: getfield Lobby.gnum [I
      // 7d9c: iload 15
      // 7d9e: iaload
      // 7d9f: if_icmpeq 7da7
      // 7da2: goto 7db8
      // 7da7: aload 23
      // 7da9: iload 24
      // 7dab: aload 0
      // 7dac: getfield Lobby.pclan [Ljava/lang/String;
      // 7daf: iload 25
      // 7db1: aaload
      // 7db2: aastore
      // 7db3: goto 7db8
      // 7db8: iinc 25 1
      // 7dbb: goto 7d6a
      // 7dc0: iinc 24 1
      // 7dc3: goto 7d59
      // 7dc8: bipush 0
      // 7dc9: istore 24
      // 7dcb: iload 24
      // 7dcd: aload 0
      // 7dce: getfield Lobby.prnpo I
      // 7dd1: if_icmplt 7dd9
      // 7dd4: goto 7dfc
      // 7dd9: aload 0
      // 7dda: getfield Lobby.ppos [I
      // 7ddd: iload 24
      // 7ddf: iaload
      // 7de0: ifeq 7de8
      // 7de3: goto 7df4
      // 7de8: aload 23
      // 7dea: iload 24
      // 7dec: aaload
      // 7ded: astore 18
      // 7def: goto 7dfc
      // 7df4: iinc 24 1
      // 7df7: goto 7dcb
      // 7dfc: aload 18
      // 7dfe: ldc ""
      // 7e00: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7e03: ifeq 7e0b
      // 7e06: goto 81eb
      // 7e0b: aload 0
      // 7e0c: getfield Lobby.gwtyp [I
      // 7e0f: iload 15
      // 7e11: iaload
      // 7e12: bipush 2
      // 7e13: if_icmpeq 7e1b
      // 7e16: goto 7ead
      // 7e1b: bipush 0
      // 7e1c: istore 24
      // 7e1e: bipush 0
      // 7e1f: istore 25
      // 7e21: iload 25
      // 7e23: aload 0
      // 7e24: getfield Lobby.prnpo I
      // 7e27: if_icmplt 7e2f
      // 7e2a: goto 7e79
      // 7e2f: aload 23
      // 7e31: iload 25
      // 7e33: aaload
      // 7e34: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7e37: aload 18
      // 7e39: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7e3c: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7e3f: ifeq 7e47
      // 7e42: goto 7e71
      // 7e47: aload 0
      // 7e48: getfield Lobby.pdam [I
      // 7e4b: iload 25
      // 7e4d: iaload
      // 7e4e: bipush 55
      // 7e50: if_icmplt 7e58
      // 7e53: goto 7e71
      // 7e58: aload 0
      // 7e59: getfield Lobby.pdam [I
      // 7e5c: iload 25
      // 7e5e: iaload
      // 7e5f: bipush -17
      // 7e61: if_icmpne 7e69
      // 7e64: goto 7e71
      // 7e69: bipush 1
      // 7e6a: istore 24
      // 7e6c: goto 7e79
      // 7e71: iinc 25 1
      // 7e74: goto 7e21
      // 7e79: iload 24
      // 7e7b: ifeq 7e83
      // 7e7e: goto 7ea8
      // 7e83: new java/lang/StringBuilder
      // 7e86: dup
      // 7e87: invokespecial java/lang/StringBuilder.<init> ()V
      // 7e8a: ldc ""
      // 7e8c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7e8f: aload 18
      // 7e91: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7e94: ldc_w " should have raced in this game!"
      // 7e97: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7e9a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7e9d: astore 20
      // 7e9f: ldc ""
      // 7ea1: astore 18
      // 7ea3: goto 7ea8
      // 7ea8: goto 7ead
      // 7ead: aload 0
      // 7eae: getfield Lobby.gwtyp [I
      // 7eb1: iload 15
      // 7eb3: iaload
      // 7eb4: bipush 3
      // 7eb5: if_icmpeq 7ebd
      // 7eb8: goto 7f4f
      // 7ebd: bipush 1
      // 7ebe: istore 24
      // 7ec0: bipush 0
      // 7ec1: istore 25
      // 7ec3: iload 25
      // 7ec5: aload 0
      // 7ec6: getfield Lobby.prnpo I
      // 7ec9: if_icmplt 7ed1
      // 7ecc: goto 7f1b
      // 7ed1: aload 23
      // 7ed3: iload 25
      // 7ed5: aaload
      // 7ed6: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7ed9: aload 18
      // 7edb: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7ede: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7ee1: ifeq 7ee9
      // 7ee4: goto 7f13
      // 7ee9: aload 0
      // 7eea: getfield Lobby.pdam [I
      // 7eed: iload 25
      // 7eef: iaload
      // 7ef0: bipush 55
      // 7ef2: if_icmplt 7efa
      // 7ef5: goto 7f13
      // 7efa: aload 0
      // 7efb: getfield Lobby.pdam [I
      // 7efe: iload 25
      // 7f00: iaload
      // 7f01: bipush -17
      // 7f03: if_icmpne 7f0b
      // 7f06: goto 7f13
      // 7f0b: bipush 0
      // 7f0c: istore 24
      // 7f0e: goto 7f1b
      // 7f13: iinc 25 1
      // 7f16: goto 7ec3
      // 7f1b: iload 24
      // 7f1d: ifeq 7f25
      // 7f20: goto 7f4a
      // 7f25: new java/lang/StringBuilder
      // 7f28: dup
      // 7f29: invokespecial java/lang/StringBuilder.<init> ()V
      // 7f2c: ldc ""
      // 7f2e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f31: aload 18
      // 7f33: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f36: ldc_w " should have wasted in this game!"
      // 7f39: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7f3c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7f3f: astore 20
      // 7f41: ldc ""
      // 7f43: astore 18
      // 7f45: goto 7f4a
      // 7f4a: goto 7f4f
      // 7f4f: aload 0
      // 7f50: getfield Lobby.gwtyp [I
      // 7f53: iload 15
      // 7f55: iaload
      // 7f56: bipush 4
      // 7f57: if_icmpeq 7f5f
      // 7f5a: goto 809d
      // 7f5f: aload 18
      // 7f61: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7f64: aload 0
      // 7f65: getfield Lobby.gaclan [Ljava/lang/String;
      // 7f68: iload 15
      // 7f6a: aaload
      // 7f6b: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7f6e: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7f71: ifne 7f79
      // 7f74: goto 800b
      // 7f79: bipush 1
      // 7f7a: istore 24
      // 7f7c: bipush 0
      // 7f7d: istore 25
      // 7f7f: iload 25
      // 7f81: aload 0
      // 7f82: getfield Lobby.prnpo I
      // 7f85: if_icmplt 7f8d
      // 7f88: goto 7fd7
      // 7f8d: aload 23
      // 7f8f: iload 25
      // 7f91: aaload
      // 7f92: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7f95: aload 18
      // 7f97: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 7f9a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 7f9d: ifeq 7fa5
      // 7fa0: goto 7fcf
      // 7fa5: aload 0
      // 7fa6: getfield Lobby.pdam [I
      // 7fa9: iload 25
      // 7fab: iaload
      // 7fac: bipush 55
      // 7fae: if_icmplt 7fb6
      // 7fb1: goto 7fcf
      // 7fb6: aload 0
      // 7fb7: getfield Lobby.pdam [I
      // 7fba: iload 25
      // 7fbc: iaload
      // 7fbd: bipush -17
      // 7fbf: if_icmpne 7fc7
      // 7fc2: goto 7fcf
      // 7fc7: bipush 0
      // 7fc8: istore 24
      // 7fca: goto 7fd7
      // 7fcf: iinc 25 1
      // 7fd2: goto 7f7f
      // 7fd7: iload 24
      // 7fd9: ifeq 7fe1
      // 7fdc: goto 8006
      // 7fe1: new java/lang/StringBuilder
      // 7fe4: dup
      // 7fe5: invokespecial java/lang/StringBuilder.<init> ()V
      // 7fe8: ldc ""
      // 7fea: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7fed: aload 18
      // 7fef: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7ff2: ldc_w " should have wasted in this game!"
      // 7ff5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 7ff8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 7ffb: astore 20
      // 7ffd: ldc ""
      // 7fff: astore 18
      // 8001: goto 8006
      // 8006: goto 809d
      // 800b: bipush 0
      // 800c: istore 24
      // 800e: bipush 0
      // 800f: istore 25
      // 8011: iload 25
      // 8013: aload 0
      // 8014: getfield Lobby.prnpo I
      // 8017: if_icmplt 801f
      // 801a: goto 8069
      // 801f: aload 23
      // 8021: iload 25
      // 8023: aaload
      // 8024: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8027: aload 18
      // 8029: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 802c: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 802f: ifeq 8037
      // 8032: goto 8061
      // 8037: aload 0
      // 8038: getfield Lobby.pdam [I
      // 803b: iload 25
      // 803d: iaload
      // 803e: bipush 55
      // 8040: if_icmplt 8048
      // 8043: goto 8061
      // 8048: aload 0
      // 8049: getfield Lobby.pdam [I
      // 804c: iload 25
      // 804e: iaload
      // 804f: bipush -17
      // 8051: if_icmpne 8059
      // 8054: goto 8061
      // 8059: bipush 1
      // 805a: istore 24
      // 805c: goto 8069
      // 8061: iinc 25 1
      // 8064: goto 8011
      // 8069: iload 24
      // 806b: ifeq 8073
      // 806e: goto 8098
      // 8073: new java/lang/StringBuilder
      // 8076: dup
      // 8077: invokespecial java/lang/StringBuilder.<init> ()V
      // 807a: ldc ""
      // 807c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 807f: aload 18
      // 8081: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8084: ldc_w " should have raced in this game!"
      // 8087: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 808a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 808d: astore 20
      // 808f: ldc ""
      // 8091: astore 18
      // 8093: goto 8098
      // 8098: goto 809d
      // 809d: aload 0
      // 809e: getfield Lobby.gwtyp [I
      // 80a1: iload 15
      // 80a3: iaload
      // 80a4: bipush 5
      // 80a5: if_icmpeq 80ad
      // 80a8: goto 81f0
      // 80ad: aload 18
      // 80af: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 80b2: aload 0
      // 80b3: getfield Lobby.gaclan [Ljava/lang/String;
      // 80b6: iload 15
      // 80b8: aaload
      // 80b9: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 80bc: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 80bf: ifeq 80c7
      // 80c2: goto 8159
      // 80c7: bipush 1
      // 80c8: istore 24
      // 80ca: bipush 0
      // 80cb: istore 25
      // 80cd: iload 25
      // 80cf: aload 0
      // 80d0: getfield Lobby.prnpo I
      // 80d3: if_icmplt 80db
      // 80d6: goto 8125
      // 80db: aload 23
      // 80dd: iload 25
      // 80df: aaload
      // 80e0: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 80e3: aload 18
      // 80e5: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 80e8: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 80eb: ifeq 80f3
      // 80ee: goto 811d
      // 80f3: aload 0
      // 80f4: getfield Lobby.pdam [I
      // 80f7: iload 25
      // 80f9: iaload
      // 80fa: bipush 55
      // 80fc: if_icmplt 8104
      // 80ff: goto 811d
      // 8104: aload 0
      // 8105: getfield Lobby.pdam [I
      // 8108: iload 25
      // 810a: iaload
      // 810b: bipush -17
      // 810d: if_icmpne 8115
      // 8110: goto 811d
      // 8115: bipush 0
      // 8116: istore 24
      // 8118: goto 8125
      // 811d: iinc 25 1
      // 8120: goto 80cd
      // 8125: iload 24
      // 8127: ifeq 812f
      // 812a: goto 8154
      // 812f: new java/lang/StringBuilder
      // 8132: dup
      // 8133: invokespecial java/lang/StringBuilder.<init> ()V
      // 8136: ldc ""
      // 8138: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 813b: aload 18
      // 813d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8140: ldc_w " should have wasted in this game!"
      // 8143: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8146: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8149: astore 20
      // 814b: ldc ""
      // 814d: astore 18
      // 814f: goto 8154
      // 8154: goto 81f0
      // 8159: bipush 0
      // 815a: istore 24
      // 815c: bipush 0
      // 815d: istore 25
      // 815f: iload 25
      // 8161: aload 0
      // 8162: getfield Lobby.prnpo I
      // 8165: if_icmplt 816d
      // 8168: goto 81b7
      // 816d: aload 23
      // 816f: iload 25
      // 8171: aaload
      // 8172: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8175: aload 18
      // 8177: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 817a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 817d: ifeq 8185
      // 8180: goto 81af
      // 8185: aload 0
      // 8186: getfield Lobby.pdam [I
      // 8189: iload 25
      // 818b: iaload
      // 818c: bipush 55
      // 818e: if_icmplt 8196
      // 8191: goto 81af
      // 8196: aload 0
      // 8197: getfield Lobby.pdam [I
      // 819a: iload 25
      // 819c: iaload
      // 819d: bipush -17
      // 819f: if_icmpne 81a7
      // 81a2: goto 81af
      // 81a7: bipush 1
      // 81a8: istore 24
      // 81aa: goto 81b7
      // 81af: iinc 25 1
      // 81b2: goto 815f
      // 81b7: iload 24
      // 81b9: ifeq 81c1
      // 81bc: goto 81e6
      // 81c1: new java/lang/StringBuilder
      // 81c4: dup
      // 81c5: invokespecial java/lang/StringBuilder.<init> ()V
      // 81c8: ldc ""
      // 81ca: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 81cd: aload 18
      // 81cf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 81d2: ldc_w " should have raced in this game!"
      // 81d5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 81d8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 81db: astore 20
      // 81dd: ldc ""
      // 81df: astore 18
      // 81e1: goto 81e6
      // 81e6: goto 81f0
      // 81eb: ldc_w "No one finished first - no one survived!"
      // 81ee: astore 20
      // 81f0: aload 18
      // 81f2: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 81f5: aload 0
      // 81f6: getfield Lobby.gaclan [Ljava/lang/String;
      // 81f9: iload 15
      // 81fb: aaload
      // 81fc: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 81ff: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8202: ifne 820a
      // 8205: goto 8212
      // 820a: bipush 1
      // 820b: istore 21
      // 820d: goto 8212
      // 8212: aload 18
      // 8214: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8217: aload 0
      // 8218: getfield Lobby.gvclan [Ljava/lang/String;
      // 821b: iload 15
      // 821d: aaload
      // 821e: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8221: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8224: ifne 822c
      // 8227: goto 8234
      // 822c: bipush 1
      // 822d: istore 22
      // 822f: goto 8234
      // 8234: goto 8239
      // 8239: aload 0
      // 823a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 823d: new java/lang/StringBuilder
      // 8240: dup
      // 8241: invokespecial java/lang/StringBuilder.<init> ()V
      // 8244: ldc ""
      // 8246: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8249: aload 0
      // 824a: getfield Lobby.gaclan [Ljava/lang/String;
      // 824d: iload 15
      // 824f: aaload
      // 8250: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8253: ldc_w " : "
      // 8256: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8259: aload 0
      // 825a: getfield Lobby.gascore [I
      // 825d: iload 15
      // 825f: iaload
      // 8260: iload 21
      // 8262: iadd
      // 8263: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 8266: ldc_w "     |     "
      // 8269: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 826c: aload 0
      // 826d: getfield Lobby.gvclan [Ljava/lang/String;
      // 8270: iload 15
      // 8272: aaload
      // 8273: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8276: ldc_w " : "
      // 8279: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 827c: aload 0
      // 827d: getfield Lobby.gvscore [I
      // 8280: iload 15
      // 8282: iaload
      // 8283: iload 22
      // 8285: iadd
      // 8286: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 8289: ldc ""
      // 828b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 828e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8291: sipush 474
      // 8294: aload 0
      // 8295: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 8298: new java/lang/StringBuilder
      // 829b: dup
      // 829c: invokespecial java/lang/StringBuilder.<init> ()V
      // 829f: ldc ""
      // 82a1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82a4: aload 0
      // 82a5: getfield Lobby.gaclan [Ljava/lang/String;
      // 82a8: iload 15
      // 82aa: aaload
      // 82ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82ae: ldc_w " : "
      // 82b1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82b4: aload 0
      // 82b5: getfield Lobby.gascore [I
      // 82b8: iload 15
      // 82ba: iaload
      // 82bb: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 82be: ldc_w "     |     "
      // 82c1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82c4: aload 0
      // 82c5: getfield Lobby.gvclan [Ljava/lang/String;
      // 82c8: iload 15
      // 82ca: aaload
      // 82cb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82ce: ldc_w " : "
      // 82d1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82d4: aload 0
      // 82d5: getfield Lobby.gvscore [I
      // 82d8: iload 15
      // 82da: iaload
      // 82db: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 82de: ldc ""
      // 82e0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 82e3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 82e6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 82e9: bipush 2
      // 82ea: idiv
      // 82eb: isub
      // 82ec: bipush 28
      // 82ee: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 82f1: aload 0
      // 82f2: getfield Lobby.gwarb [I
      // 82f5: iload 15
      // 82f7: iaload
      // 82f8: bipush 1
      // 82f9: if_icmpeq 8301
      // 82fc: goto 8373
      // 8301: aload 0
      // 8302: getfield Lobby.gascore [I
      // 8305: iload 15
      // 8307: iaload
      // 8308: iload 21
      // 830a: iadd
      // 830b: bipush 5
      // 830c: if_icmpge 8314
      // 830f: goto 833a
      // 8314: new java/lang/StringBuilder
      // 8317: dup
      // 8318: invokespecial java/lang/StringBuilder.<init> ()V
      // 831b: ldc ""
      // 831d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8320: aload 0
      // 8321: getfield Lobby.gaclan [Ljava/lang/String;
      // 8324: iload 15
      // 8326: aaload
      // 8327: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 832a: ldc_w " wins the war!"
      // 832d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8330: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 8333: astore 19
      // 8335: goto 833a
      // 833a: aload 0
      // 833b: getfield Lobby.gvscore [I
      // 833e: iload 15
      // 8340: iaload
      // 8341: iload 22
      // 8343: iadd
      // 8344: bipush 5
      // 8345: if_icmpge 834d
      // 8348: goto 83e5
      // 834d: new java/lang/StringBuilder
      // 8350: dup
      // 8351: invokespecial java/lang/StringBuilder.<init> ()V
      // 8354: ldc ""
      // 8356: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8359: aload 0
      // 835a: getfield Lobby.gvclan [Ljava/lang/String;
      // 835d: iload 15
      // 835f: aaload
      // 8360: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8363: ldc_w " wins the war!"
      // 8366: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8369: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 836c: astore 19
      // 836e: goto 83e5
      // 8373: aload 0
      // 8374: getfield Lobby.gascore [I
      // 8377: iload 15
      // 8379: iaload
      // 837a: iload 21
      // 837c: iadd
      // 837d: bipush 3
      // 837e: if_icmpge 8386
      // 8381: goto 83ac
      // 8386: new java/lang/StringBuilder
      // 8389: dup
      // 838a: invokespecial java/lang/StringBuilder.<init> ()V
      // 838d: ldc ""
      // 838f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 8392: aload 0
      // 8393: getfield Lobby.gaclan [Ljava/lang/String;
      // 8396: iload 15
      // 8398: aaload
      // 8399: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 839c: ldc_w " wins the battle!"
      // 839f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83a2: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 83a5: astore 19
      // 83a7: goto 83ac
      // 83ac: aload 0
      // 83ad: getfield Lobby.gvscore [I
      // 83b0: iload 15
      // 83b2: iaload
      // 83b3: iload 22
      // 83b5: iadd
      // 83b6: bipush 3
      // 83b7: if_icmpge 83bf
      // 83ba: goto 83e5
      // 83bf: new java/lang/StringBuilder
      // 83c2: dup
      // 83c3: invokespecial java/lang/StringBuilder.<init> ()V
      // 83c6: ldc ""
      // 83c8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83cb: aload 0
      // 83cc: getfield Lobby.gvclan [Ljava/lang/String;
      // 83cf: iload 15
      // 83d1: aaload
      // 83d2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83d5: ldc_w " wins the battle!"
      // 83d8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 83db: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 83de: astore 19
      // 83e0: goto 83e5
      // 83e5: goto 83ea
      // 83ea: aload 0
      // 83eb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 83ee: aload 0
      // 83ef: sipush 200
      // 83f2: sipush 200
      // 83f5: sipush 200
      // 83f8: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 83fb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 83fe: aload 0
      // 83ff: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8402: sipush 233
      // 8405: bipush 32
      // 8407: sipush 602
      // 840a: bipush 32
      // 840c: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 840f: aload 0
      // 8410: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8413: sipush 602
      // 8416: bipush 7
      // 8418: sipush 602
      // 841b: bipush 32
      // 841d: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 8420: aload 0
      // 8421: getfield Lobby.conon I
      // 8424: bipush 1
      // 8425: if_icmpeq 842d
      // 8428: goto 8466
      // 842d: aload 0
      // 842e: getfield Lobby.pgames [I
      // 8431: aload 0
      // 8432: getfield Lobby.im I
      // 8435: iaload
      // 8436: aload 0
      // 8437: getfield Lobby.ongame I
      // 843a: if_icmpeq 8442
      // 843d: goto 8454
      // 8442: aload 0
      // 8443: ldc_w "Leave Game X"
      // 8446: sipush 660
      // 8449: bipush 26
      // 844b: bipush 0
      // 844c: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 844f: goto 8466
      // 8454: aload 0
      // 8455: ldc_w "Close X"
      // 8458: sipush 679
      // 845b: bipush 26
      // 845d: bipush 0
      // 845e: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 8461: goto 8466
      // 8466: aload 0
      // 8467: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 846a: aload 0
      // 846b: getfield Lobby.xt LxtGraphics;
      // 846e: getfield xtGraphics.pls Ljava/awt/Image;
      // 8471: sipush 292
      // 8474: bipush 39
      // 8476: aconst_null
      // 8477: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 847a: pop
      // 847b: aload 0
      // 847c: getfield Lobby.opengame I
      // 847f: bipush 27
      // 8481: if_icmpne 8489
      // 8484: goto 84e1
      // 8489: aload 0
      // 848a: getfield Lobby.m LMedium;
      // 848d: bipush 1
      // 848e: putfield Medium.crs Z
      // 8491: aload 0
      // 8492: getfield Lobby.m LMedium;
      // 8495: sipush -335
      // 8498: putfield Medium.x I
      // 849b: aload 0
      // 849c: getfield Lobby.m LMedium;
      // 849f: bipush 0
      // 84a0: putfield Medium.y I
      // 84a3: aload 0
      // 84a4: getfield Lobby.m LMedium;
      // 84a7: bipush -50
      // 84a9: putfield Medium.z I
      // 84ac: aload 0
      // 84ad: getfield Lobby.m LMedium;
      // 84b0: bipush 0
      // 84b1: putfield Medium.xz I
      // 84b4: aload 0
      // 84b5: getfield Lobby.m LMedium;
      // 84b8: bipush 20
      // 84ba: putfield Medium.zy I
      // 84bd: aload 0
      // 84be: getfield Lobby.m LMedium;
      // 84c1: sipush -2000
      // 84c4: putfield Medium.ground I
      // 84c7: aload 0
      // 84c8: bipush 0
      // 84c9: putfield Lobby.pend I
      // 84cc: aload 0
      // 84cd: bipush 0
      // 84ce: putfield Lobby.pendb Z
      // 84d1: aload 0
      // 84d2: lconst_0
      // 84d3: putfield Lobby.ptime J
      // 84d6: aload 0
      // 84d7: bipush 27
      // 84d9: putfield Lobby.opengame I
      // 84dc: goto 84e1
      // 84e1: bipush 0
      // 84e2: istore 21
      // 84e4: bipush -1
      // 84e5: istore 22
      // 84e7: bipush 0
      // 84e8: istore 23
      // 84ea: iload 23
      // 84ec: aload 0
      // 84ed: getfield Lobby.npo I
      // 84f0: if_icmplt 84f8
      // 84f3: goto 8c74
      // 84f8: aload 0
      // 84f9: getfield Lobby.pgames [I
      // 84fc: iload 23
      // 84fe: iaload
      // 84ff: aload 0
      // 8500: getfield Lobby.ongame I
      // 8503: if_icmpeq 850b
      // 8506: goto 8c6c
      // 850b: aload 0
      // 850c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 850f: aload 0
      // 8510: sipush 240
      // 8513: sipush 240
      // 8516: sipush 240
      // 8519: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 851c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 851f: iload 23
      // 8521: aload 0
      // 8522: getfield Lobby.im I
      // 8525: if_icmpeq 852d
      // 8528: goto 8583
      // 852d: aload 0
      // 852e: getfield Lobby.wait [I
      // 8531: iload 15
      // 8533: iaload
      // 8534: bipush -1
      // 8535: if_icmpne 853d
      // 8538: goto 8583
      // 853d: aload 0
      // 853e: getfield Lobby.nflk I
      // 8541: ifne 8549
      // 8544: goto 8556
      // 8549: aload 0
      // 854a: getfield Lobby.conon I
      // 854d: bipush 2
      // 854e: if_icmpeq 8556
      // 8551: goto 8574
      // 8556: aload 0
      // 8557: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 855a: aload 0
      // 855b: sipush 255
      // 855e: sipush 255
      // 8561: sipush 255
      // 8564: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 8567: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 856a: aload 0
      // 856b: bipush 3
      // 856c: putfield Lobby.nflk I
      // 856f: goto 8583
      // 8574: aload 0
      // 8575: dup
      // 8576: getfield Lobby.nflk I
      // 8579: bipush 1
      // 857a: isub
      // 857b: putfield Lobby.nflk I
      // 857e: goto 8583
      // 8583: aload 0
      // 8584: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8587: sipush 237
      // 858a: bipush 54
      // 858c: iload 21
      // 858e: bipush 42
      // 8590: imul
      // 8591: iadd
      // 8592: sipush 170
      // 8595: bipush 40
      // 8597: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // 859a: aload 0
      // 859b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 859e: aload 0
      // 859f: sipush 200
      // 85a2: sipush 200
      // 85a5: sipush 200
      // 85a8: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 85ab: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 85ae: aload 0
      // 85af: getfield Lobby.gwarb [I
      // 85b2: iload 15
      // 85b4: iaload
      // 85b5: ifne 85bd
      // 85b8: goto 8688
      // 85bd: aload 0
      // 85be: getfield Lobby.pclan [Ljava/lang/String;
      // 85c1: iload 23
      // 85c3: aaload
      // 85c4: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 85c7: aload 0
      // 85c8: getfield Lobby.gaclan [Ljava/lang/String;
      // 85cb: iload 15
      // 85cd: aaload
      // 85ce: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 85d1: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 85d4: ifne 85dc
      // 85d7: goto 85f6
      // 85dc: aload 0
      // 85dd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 85e0: new java/awt/Color
      // 85e3: dup
      // 85e4: sipush 255
      // 85e7: sipush 128
      // 85ea: bipush 0
      // 85eb: invokespecial java/awt/Color.<init> (III)V
      // 85ee: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 85f1: goto 85f6
      // 85f6: aload 0
      // 85f7: getfield Lobby.pclan [Ljava/lang/String;
      // 85fa: iload 23
      // 85fc: aaload
      // 85fd: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8600: aload 0
      // 8601: getfield Lobby.gvclan [Ljava/lang/String;
      // 8604: iload 15
      // 8606: aaload
      // 8607: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 860a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 860d: ifne 8615
      // 8610: goto 862f
      // 8615: aload 0
      // 8616: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8619: new java/awt/Color
      // 861c: dup
      // 861d: bipush 0
      // 861e: sipush 128
      // 8621: sipush 255
      // 8624: invokespecial java/awt/Color.<init> (III)V
      // 8627: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 862a: goto 862f
      // 862f: aload 0
      // 8630: getfield Lobby.wait [I
      // 8633: iload 15
      // 8635: iaload
      // 8636: bipush -1
      // 8637: if_icmpeq 863f
      // 863a: goto 870d
      // 863f: aload 0
      // 8640: getfield Lobby.prevloaded I
      // 8643: bipush 1
      // 8644: if_icmpeq 864c
      // 8647: goto 870d
      // 864c: aload 18
      // 864e: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8651: aload 0
      // 8652: getfield Lobby.pclan [Ljava/lang/String;
      // 8655: iload 23
      // 8657: aaload
      // 8658: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 865b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 865e: ifne 8666
      // 8661: goto 870d
      // 8666: aload 0
      // 8667: getfield Lobby.nflk I
      // 866a: ifeq 8672
      // 866d: goto 870d
      // 8672: aload 0
      // 8673: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8676: new java/awt/Color
      // 8679: dup
      // 867a: bipush 0
      // 867b: bipush 0
      // 867c: bipush 0
      // 867d: invokespecial java/awt/Color.<init> (III)V
      // 8680: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8683: goto 870d
      // 8688: aload 0
      // 8689: getfield Lobby.wait [I
      // 868c: iload 15
      // 868e: iaload
      // 868f: bipush -1
      // 8690: if_icmpeq 8698
      // 8693: goto 870d
      // 8698: aload 0
      // 8699: getfield Lobby.prevloaded I
      // 869c: bipush 1
      // 869d: if_icmpeq 86a5
      // 86a0: goto 870d
      // 86a5: bipush 0
      // 86a6: istore 24
      // 86a8: iload 24
      // 86aa: aload 0
      // 86ab: getfield Lobby.prnpo I
      // 86ae: if_icmplt 86b6
      // 86b1: goto 8708
      // 86b6: aload 0
      // 86b7: getfield Lobby.pnames [Ljava/lang/String;
      // 86ba: iload 23
      // 86bc: aaload
      // 86bd: aload 0
      // 86be: getfield Lobby.prnames [Ljava/lang/String;
      // 86c1: iload 24
      // 86c3: aaload
      // 86c4: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 86c7: ifne 86cf
      // 86ca: goto 8700
      // 86cf: aload 0
      // 86d0: getfield Lobby.ppos [I
      // 86d3: iload 24
      // 86d5: iaload
      // 86d6: ifeq 86de
      // 86d9: goto 8700
      // 86de: aload 0
      // 86df: getfield Lobby.nflk I
      // 86e2: ifeq 86ea
      // 86e5: goto 8700
      // 86ea: aload 0
      // 86eb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 86ee: new java/awt/Color
      // 86f1: dup
      // 86f2: bipush 0
      // 86f3: bipush 0
      // 86f4: bipush 0
      // 86f5: invokespecial java/awt/Color.<init> (III)V
      // 86f8: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 86fb: goto 8700
      // 8700: iinc 24 1
      // 8703: goto 86a8
      // 8708: goto 870d
      // 870d: aload 0
      // 870e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8711: sipush 237
      // 8714: bipush 54
      // 8716: iload 21
      // 8718: bipush 42
      // 871a: imul
      // 871b: iadd
      // 871c: sipush 170
      // 871f: bipush 40
      // 8721: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 8724: aload 0
      // 8725: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8728: new java/awt/Color
      // 872b: dup
      // 872c: bipush 0
      // 872d: bipush 0
      // 872e: bipush 0
      // 872f: invokespecial java/awt/Color.<init> (III)V
      // 8732: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8735: aload 0
      // 8736: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8739: new java/awt/Font
      // 873c: dup
      // 873d: ldc_w "Arial"
      // 8740: bipush 1
      // 8741: bipush 12
      // 8743: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8746: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8749: aload 0
      // 874a: aload 0
      // 874b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 874e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 8751: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 8754: aload 0
      // 8755: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8758: aload 0
      // 8759: getfield Lobby.pnames [Ljava/lang/String;
      // 875c: iload 23
      // 875e: aaload
      // 875f: sipush 282
      // 8762: aload 0
      // 8763: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 8766: aload 0
      // 8767: getfield Lobby.pnames [Ljava/lang/String;
      // 876a: iload 23
      // 876c: aaload
      // 876d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 8770: bipush 2
      // 8771: idiv
      // 8772: isub
      // 8773: bipush 72
      // 8775: iload 21
      // 8777: bipush 42
      // 8779: imul
      // 877a: iadd
      // 877b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 877e: aload 0
      // 877f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8782: new java/awt/Font
      // 8785: dup
      // 8786: ldc_w "Arial"
      // 8789: bipush 0
      // 878a: bipush 10
      // 878c: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 878f: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8792: aload 0
      // 8793: aload 0
      // 8794: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8797: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 879a: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 879d: aload 0
      // 879e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 87a1: aload 0
      // 87a2: getfield Lobby.pcarnames [Ljava/lang/String;
      // 87a5: iload 23
      // 87a7: aaload
      // 87a8: sipush 282
      // 87ab: aload 0
      // 87ac: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 87af: aload 0
      // 87b0: getfield Lobby.pcarnames [Ljava/lang/String;
      // 87b3: iload 23
      // 87b5: aaload
      // 87b6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 87b9: bipush 2
      // 87ba: idiv
      // 87bb: isub
      // 87bc: bipush 84
      // 87be: iload 21
      // 87c0: bipush 42
      // 87c2: imul
      // 87c3: iadd
      // 87c4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 87c7: aload 0
      // 87c8: getfield Lobby.m LMedium;
      // 87cb: bipush 1
      // 87cc: putfield Medium.crs Z
      // 87cf: aload 0
      // 87d0: getfield Lobby.m LMedium;
      // 87d3: sipush -335
      // 87d6: putfield Medium.x I
      // 87d9: aload 0
      // 87da: getfield Lobby.m LMedium;
      // 87dd: bipush 0
      // 87de: putfield Medium.y I
      // 87e1: aload 0
      // 87e2: getfield Lobby.m LMedium;
      // 87e5: bipush -50
      // 87e7: putfield Medium.z I
      // 87ea: aload 0
      // 87eb: getfield Lobby.m LMedium;
      // 87ee: bipush 0
      // 87ef: putfield Medium.xz I
      // 87f2: aload 0
      // 87f3: getfield Lobby.m LMedium;
      // 87f6: bipush 20
      // 87f8: putfield Medium.zy I
      // 87fb: aload 0
      // 87fc: getfield Lobby.m LMedium;
      // 87ff: sipush -2000
      // 8802: putfield Medium.ground I
      // 8805: aload 0
      // 8806: getfield Lobby.pcars [I
      // 8809: iload 23
      // 880b: iaload
      // 880c: bipush -1
      // 880d: if_icmpne 8815
      // 8810: goto 8c2a
      // 8815: bipush 0
      // 8816: istore 24
      // 8818: iload 24
      // 881a: aload 7
      // 881c: aload 0
      // 881d: getfield Lobby.pcars [I
      // 8820: iload 23
      // 8822: iaload
      // 8823: aaload
      // 8824: getfield ContO.npl I
      // 8827: if_icmplt 882f
      // 882a: goto 8945
      // 882f: aload 7
      // 8831: aload 0
      // 8832: getfield Lobby.pcars [I
      // 8835: iload 23
      // 8837: iaload
      // 8838: aaload
      // 8839: getfield ContO.p [LPlane;
      // 883c: iload 24
      // 883e: aaload
      // 883f: bipush 0
      // 8840: putfield Plane.flx I
      // 8843: aload 7
      // 8845: aload 0
      // 8846: getfield Lobby.pcars [I
      // 8849: iload 23
      // 884b: iaload
      // 884c: aaload
      // 884d: getfield ContO.p [LPlane;
      // 8850: iload 24
      // 8852: aaload
      // 8853: getfield Plane.colnum I
      // 8856: bipush 1
      // 8857: if_icmpeq 885f
      // 885a: goto 88c0
      // 885f: aload 7
      // 8861: aload 0
      // 8862: getfield Lobby.pcars [I
      // 8865: iload 23
      // 8867: iaload
      // 8868: aaload
      // 8869: getfield ContO.p [LPlane;
      // 886c: iload 24
      // 886e: aaload
      // 886f: getfield Plane.hsb [F
      // 8872: bipush 0
      // 8873: aload 0
      // 8874: getfield Lobby.pcols [[F
      // 8877: iload 23
      // 8879: aaload
      // 887a: bipush 0
      // 887b: faload
      // 887c: fastore
      // 887d: aload 7
      // 887f: aload 0
      // 8880: getfield Lobby.pcars [I
      // 8883: iload 23
      // 8885: iaload
      // 8886: aaload
      // 8887: getfield ContO.p [LPlane;
      // 888a: iload 24
      // 888c: aaload
      // 888d: getfield Plane.hsb [F
      // 8890: bipush 1
      // 8891: aload 0
      // 8892: getfield Lobby.pcols [[F
      // 8895: iload 23
      // 8897: aaload
      // 8898: bipush 1
      // 8899: faload
      // 889a: fastore
      // 889b: aload 7
      // 889d: aload 0
      // 889e: getfield Lobby.pcars [I
      // 88a1: iload 23
      // 88a3: iaload
      // 88a4: aaload
      // 88a5: getfield ContO.p [LPlane;
      // 88a8: iload 24
      // 88aa: aaload
      // 88ab: getfield Plane.hsb [F
      // 88ae: bipush 2
      // 88af: fconst_1
      // 88b0: aload 0
      // 88b1: getfield Lobby.pcols [[F
      // 88b4: iload 23
      // 88b6: aaload
      // 88b7: bipush 2
      // 88b8: faload
      // 88b9: fsub
      // 88ba: fastore
      // 88bb: goto 88c0
      // 88c0: aload 7
      // 88c2: aload 0
      // 88c3: getfield Lobby.pcars [I
      // 88c6: iload 23
      // 88c8: iaload
      // 88c9: aaload
      // 88ca: getfield ContO.p [LPlane;
      // 88cd: iload 24
      // 88cf: aaload
      // 88d0: getfield Plane.colnum I
      // 88d3: bipush 2
      // 88d4: if_icmpeq 88dc
      // 88d7: goto 893d
      // 88dc: aload 7
      // 88de: aload 0
      // 88df: getfield Lobby.pcars [I
      // 88e2: iload 23
      // 88e4: iaload
      // 88e5: aaload
      // 88e6: getfield ContO.p [LPlane;
      // 88e9: iload 24
      // 88eb: aaload
      // 88ec: getfield Plane.hsb [F
      // 88ef: bipush 0
      // 88f0: aload 0
      // 88f1: getfield Lobby.pcols [[F
      // 88f4: iload 23
      // 88f6: aaload
      // 88f7: bipush 3
      // 88f8: faload
      // 88f9: fastore
      // 88fa: aload 7
      // 88fc: aload 0
      // 88fd: getfield Lobby.pcars [I
      // 8900: iload 23
      // 8902: iaload
      // 8903: aaload
      // 8904: getfield ContO.p [LPlane;
      // 8907: iload 24
      // 8909: aaload
      // 890a: getfield Plane.hsb [F
      // 890d: bipush 1
      // 890e: aload 0
      // 890f: getfield Lobby.pcols [[F
      // 8912: iload 23
      // 8914: aaload
      // 8915: bipush 4
      // 8916: faload
      // 8917: fastore
      // 8918: aload 7
      // 891a: aload 0
      // 891b: getfield Lobby.pcars [I
      // 891e: iload 23
      // 8920: iaload
      // 8921: aaload
      // 8922: getfield ContO.p [LPlane;
      // 8925: iload 24
      // 8927: aaload
      // 8928: getfield Plane.hsb [F
      // 892b: bipush 2
      // 892c: fconst_1
      // 892d: aload 0
      // 892e: getfield Lobby.pcols [[F
      // 8931: iload 23
      // 8933: aaload
      // 8934: bipush 5
      // 8935: faload
      // 8936: fsub
      // 8937: fastore
      // 8938: goto 893d
      // 893d: iinc 24 1
      // 8940: goto 8818
      // 8945: aload 0
      // 8946: getfield Lobby.cac [I
      // 8949: iload 21
      // 894b: iaload
      // 894c: aload 0
      // 894d: getfield Lobby.pcars [I
      // 8950: iload 23
      // 8952: iaload
      // 8953: if_icmpne 895b
      // 8956: goto 8afc
      // 895b: aload 7
      // 895d: aload 0
      // 895e: getfield Lobby.pcars [I
      // 8961: iload 23
      // 8963: iaload
      // 8964: aaload
      // 8965: getfield ContO.p [LPlane;
      // 8968: bipush 0
      // 8969: aaload
      // 896a: getfield Plane.oz [I
      // 896d: bipush 0
      // 896e: iaload
      // 896f: istore 24
      // 8971: iload 24
      // 8973: istore 25
      // 8975: aload 7
      // 8977: aload 0
      // 8978: getfield Lobby.pcars [I
      // 897b: iload 23
      // 897d: iaload
      // 897e: aaload
      // 897f: getfield ContO.p [LPlane;
      // 8982: bipush 0
      // 8983: aaload
      // 8984: getfield Plane.oy [I
      // 8987: bipush 0
      // 8988: iaload
      // 8989: istore 26
      // 898b: iload 26
      // 898d: istore 27
      // 898f: bipush 0
      // 8990: istore 28
      // 8992: iload 28
      // 8994: aload 7
      // 8996: aload 0
      // 8997: getfield Lobby.pcars [I
      // 899a: iload 23
      // 899c: iaload
      // 899d: aaload
      // 899e: getfield ContO.npl I
      // 89a1: if_icmplt 89a9
      // 89a4: goto 8acd
      // 89a9: bipush 0
      // 89aa: istore 29
      // 89ac: iload 29
      // 89ae: aload 7
      // 89b0: aload 0
      // 89b1: getfield Lobby.pcars [I
      // 89b4: iload 23
      // 89b6: iaload
      // 89b7: aaload
      // 89b8: getfield ContO.p [LPlane;
      // 89bb: iload 28
      // 89bd: aaload
      // 89be: getfield Plane.n I
      // 89c1: if_icmplt 89c9
      // 89c4: goto 8ac5
      // 89c9: aload 7
      // 89cb: aload 0
      // 89cc: getfield Lobby.pcars [I
      // 89cf: iload 23
      // 89d1: iaload
      // 89d2: aaload
      // 89d3: getfield ContO.p [LPlane;
      // 89d6: iload 28
      // 89d8: aaload
      // 89d9: getfield Plane.oz [I
      // 89dc: iload 29
      // 89de: iaload
      // 89df: iload 24
      // 89e1: if_icmplt 89e9
      // 89e4: goto 8a06
      // 89e9: aload 7
      // 89eb: aload 0
      // 89ec: getfield Lobby.pcars [I
      // 89ef: iload 23
      // 89f1: iaload
      // 89f2: aaload
      // 89f3: getfield ContO.p [LPlane;
      // 89f6: iload 28
      // 89f8: aaload
      // 89f9: getfield Plane.oz [I
      // 89fc: iload 29
      // 89fe: iaload
      // 89ff: istore 24
      // 8a01: goto 8a06
      // 8a06: aload 7
      // 8a08: aload 0
      // 8a09: getfield Lobby.pcars [I
      // 8a0c: iload 23
      // 8a0e: iaload
      // 8a0f: aaload
      // 8a10: getfield ContO.p [LPlane;
      // 8a13: iload 28
      // 8a15: aaload
      // 8a16: getfield Plane.oz [I
      // 8a19: iload 29
      // 8a1b: iaload
      // 8a1c: iload 25
      // 8a1e: if_icmpgt 8a26
      // 8a21: goto 8a43
      // 8a26: aload 7
      // 8a28: aload 0
      // 8a29: getfield Lobby.pcars [I
      // 8a2c: iload 23
      // 8a2e: iaload
      // 8a2f: aaload
      // 8a30: getfield ContO.p [LPlane;
      // 8a33: iload 28
      // 8a35: aaload
      // 8a36: getfield Plane.oz [I
      // 8a39: iload 29
      // 8a3b: iaload
      // 8a3c: istore 25
      // 8a3e: goto 8a43
      // 8a43: aload 7
      // 8a45: aload 0
      // 8a46: getfield Lobby.pcars [I
      // 8a49: iload 23
      // 8a4b: iaload
      // 8a4c: aaload
      // 8a4d: getfield ContO.p [LPlane;
      // 8a50: iload 28
      // 8a52: aaload
      // 8a53: getfield Plane.oy [I
      // 8a56: iload 29
      // 8a58: iaload
      // 8a59: iload 26
      // 8a5b: if_icmplt 8a63
      // 8a5e: goto 8a80
      // 8a63: aload 7
      // 8a65: aload 0
      // 8a66: getfield Lobby.pcars [I
      // 8a69: iload 23
      // 8a6b: iaload
      // 8a6c: aaload
      // 8a6d: getfield ContO.p [LPlane;
      // 8a70: iload 28
      // 8a72: aaload
      // 8a73: getfield Plane.oy [I
      // 8a76: iload 29
      // 8a78: iaload
      // 8a79: istore 26
      // 8a7b: goto 8a80
      // 8a80: aload 7
      // 8a82: aload 0
      // 8a83: getfield Lobby.pcars [I
      // 8a86: iload 23
      // 8a88: iaload
      // 8a89: aaload
      // 8a8a: getfield ContO.p [LPlane;
      // 8a8d: iload 28
      // 8a8f: aaload
      // 8a90: getfield Plane.oy [I
      // 8a93: iload 29
      // 8a95: iaload
      // 8a96: iload 27
      // 8a98: if_icmpgt 8aa0
      // 8a9b: goto 8abd
      // 8aa0: aload 7
      // 8aa2: aload 0
      // 8aa3: getfield Lobby.pcars [I
      // 8aa6: iload 23
      // 8aa8: iaload
      // 8aa9: aaload
      // 8aaa: getfield ContO.p [LPlane;
      // 8aad: iload 28
      // 8aaf: aaload
      // 8ab0: getfield Plane.oy [I
      // 8ab3: iload 29
      // 8ab5: iaload
      // 8ab6: istore 27
      // 8ab8: goto 8abd
      // 8abd: iinc 29 1
      // 8ac0: goto 89ac
      // 8ac5: iinc 28 1
      // 8ac8: goto 8992
      // 8acd: aload 0
      // 8ace: getfield Lobby.cax [I
      // 8ad1: iload 21
      // 8ad3: iload 25
      // 8ad5: iload 24
      // 8ad7: iadd
      // 8ad8: bipush 2
      // 8ad9: idiv
      // 8ada: iastore
      // 8adb: aload 0
      // 8adc: getfield Lobby.cay [I
      // 8adf: iload 21
      // 8ae1: iload 27
      // 8ae3: iload 26
      // 8ae5: iadd
      // 8ae6: bipush 2
      // 8ae7: idiv
      // 8ae8: iastore
      // 8ae9: aload 0
      // 8aea: getfield Lobby.cac [I
      // 8aed: iload 21
      // 8aef: aload 0
      // 8af0: getfield Lobby.pcars [I
      // 8af3: iload 23
      // 8af5: iaload
      // 8af6: iastore
      // 8af7: goto 8afc
      // 8afc: iload 1
      // 8afd: sipush 327
      // 8b00: if_icmpgt 8b08
      // 8b03: goto 8b7a
      // 8b08: iload 1
      // 8b09: sipush 402
      // 8b0c: if_icmplt 8b14
      // 8b0f: goto 8b7a
      // 8b14: iload 2
      // 8b15: bipush 57
      // 8b17: iload 21
      // 8b19: bipush 42
      // 8b1b: imul
      // 8b1c: iadd
      // 8b1d: if_icmpgt 8b25
      // 8b20: goto 8b7a
      // 8b25: iload 2
      // 8b26: bipush 91
      // 8b28: iload 21
      // 8b2a: bipush 42
      // 8b2c: imul
      // 8b2d: iadd
      // 8b2e: if_icmplt 8b36
      // 8b31: goto 8b7a
      // 8b36: bipush 12
      // 8b38: istore 8
      // 8b3a: iload 23
      // 8b3c: istore 22
      // 8b3e: bipush 0
      // 8b3f: istore 24
      // 8b41: iload 24
      // 8b43: aload 7
      // 8b45: aload 0
      // 8b46: getfield Lobby.pcars [I
      // 8b49: iload 23
      // 8b4b: iaload
      // 8b4c: aaload
      // 8b4d: getfield ContO.npl I
      // 8b50: if_icmplt 8b58
      // 8b53: goto 8b75
      // 8b58: aload 7
      // 8b5a: aload 0
      // 8b5b: getfield Lobby.pcars [I
      // 8b5e: iload 23
      // 8b60: iaload
      // 8b61: aaload
      // 8b62: getfield ContO.p [LPlane;
      // 8b65: iload 24
      // 8b67: aaload
      // 8b68: bipush 77
      // 8b6a: putfield Plane.flx I
      // 8b6d: iinc 24 1
      // 8b70: goto 8b41
      // 8b75: goto 8b7a
      // 8b7a: aload 7
      // 8b7c: aload 0
      // 8b7d: getfield Lobby.pcars [I
      // 8b80: iload 23
      // 8b82: iaload
      // 8b83: aaload
      // 8b84: sipush 2500
      // 8b87: iload 21
      // 8b89: bipush 80
      // 8b8b: imul
      // 8b8c: isub
      // 8b8d: putfield ContO.z I
      // 8b90: aload 7
      // 8b92: aload 0
      // 8b93: getfield Lobby.pcars [I
      // 8b96: iload 23
      // 8b98: iaload
      // 8b99: aaload
      // 8b9a: sipush 150
      // 8b9d: sipush 250
      // 8ba0: iload 21
      // 8ba2: imul
      // 8ba3: iadd
      // 8ba4: aload 0
      // 8ba5: getfield Lobby.cay [I
      // 8ba8: iload 21
      // 8baa: iaload
      // 8bab: isub
      // 8bac: putfield ContO.y I
      // 8baf: aload 7
      // 8bb1: aload 0
      // 8bb2: getfield Lobby.pcars [I
      // 8bb5: iload 23
      // 8bb7: iaload
      // 8bb8: aaload
      // 8bb9: sipush -145
      // 8bbc: aload 0
      // 8bbd: getfield Lobby.cax [I
      // 8bc0: iload 21
      // 8bc2: iaload
      // 8bc3: isub
      // 8bc4: putfield ContO.x I
      // 8bc7: aload 7
      // 8bc9: aload 0
      // 8bca: getfield Lobby.pcars [I
      // 8bcd: iload 23
      // 8bcf: iaload
      // 8bd0: aaload
      // 8bd1: bipush 0
      // 8bd2: putfield ContO.zy I
      // 8bd5: aload 7
      // 8bd7: aload 0
      // 8bd8: getfield Lobby.pcars [I
      // 8bdb: iload 23
      // 8bdd: iaload
      // 8bde: aaload
      // 8bdf: bipush -90
      // 8be1: putfield ContO.xz I
      // 8be4: aload 7
      // 8be6: aload 0
      // 8be7: getfield Lobby.pcars [I
      // 8bea: iload 23
      // 8bec: iaload
      // 8bed: aaload
      // 8bee: aload 0
      // 8bef: getfield Lobby.pend I
      // 8bf2: iload 21
      // 8bf4: bipush 5
      // 8bf5: imul
      // 8bf6: isub
      // 8bf7: putfield ContO.xy I
      // 8bfa: aload 0
      // 8bfb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8bfe: getstatic java/awt/RenderingHints.KEY_ANTIALIASING Ljava/awt/RenderingHints$Key;
      // 8c01: getstatic java/awt/RenderingHints.VALUE_ANTIALIAS_OFF Ljava/lang/Object;
      // 8c04: invokevirtual java/awt/Graphics2D.setRenderingHint (Ljava/awt/RenderingHints$Key;Ljava/lang/Object;)V
      // 8c07: aload 7
      // 8c09: aload 0
      // 8c0a: getfield Lobby.pcars [I
      // 8c0d: iload 23
      // 8c0f: iaload
      // 8c10: aaload
      // 8c11: aload 0
      // 8c12: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8c15: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // 8c18: aload 0
      // 8c19: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8c1c: getstatic java/awt/RenderingHints.KEY_ANTIALIASING Ljava/awt/RenderingHints$Key;
      // 8c1f: getstatic java/awt/RenderingHints.VALUE_ANTIALIAS_ON Ljava/lang/Object;
      // 8c22: invokevirtual java/awt/Graphics2D.setRenderingHint (Ljava/awt/RenderingHints$Key;Ljava/lang/Object;)V
      // 8c25: goto 8c64
      // 8c2a: aload 0
      // 8c2b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8c2e: new java/awt/Font
      // 8c31: dup
      // 8c32: ldc_w "Arial"
      // 8c35: bipush 1
      // 8c36: bipush 11
      // 8c38: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 8c3b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 8c3e: aload 0
      // 8c3f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8c42: aload 0
      // 8c43: bipush 80
      // 8c45: bipush 80
      // 8c47: bipush 80
      // 8c49: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 8c4c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8c4f: aload 0
      // 8c50: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8c53: ldc_w "Loading..."
      // 8c56: sipush 339
      // 8c59: bipush 77
      // 8c5b: iload 21
      // 8c5d: bipush 42
      // 8c5f: imul
      // 8c60: iadd
      // 8c61: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 8c64: iinc 21 1
      // 8c67: goto 8c6c
      // 8c6c: iinc 23 1
      // 8c6f: goto 84ea
      // 8c74: aload 0
      // 8c75: getfield Lobby.pendb Z
      // 8c78: ifeq 8c80
      // 8c7b: goto 8ca2
      // 8c80: aload 0
      // 8c81: dup
      // 8c82: getfield Lobby.pend I
      // 8c85: bipush 2
      // 8c86: iadd
      // 8c87: putfield Lobby.pend I
      // 8c8a: aload 0
      // 8c8b: getfield Lobby.pend I
      // 8c8e: bipush 80
      // 8c90: if_icmpgt 8c98
      // 8c93: goto 8cc4
      // 8c98: aload 0
      // 8c99: bipush 1
      // 8c9a: putfield Lobby.pendb Z
      // 8c9d: goto 8cc4
      // 8ca2: aload 0
      // 8ca3: dup
      // 8ca4: getfield Lobby.pend I
      // 8ca7: bipush 2
      // 8ca8: isub
      // 8ca9: putfield Lobby.pend I
      // 8cac: aload 0
      // 8cad: getfield Lobby.pend I
      // 8cb0: bipush -10
      // 8cb2: if_icmplt 8cba
      // 8cb5: goto 8cc4
      // 8cba: aload 0
      // 8cbb: bipush 0
      // 8cbc: putfield Lobby.pendb Z
      // 8cbf: goto 8cc4
      // 8cc4: iload 22
      // 8cc6: bipush -1
      // 8cc7: if_icmpne 8ccf
      // 8cca: goto 8d63
      // 8ccf: iload 3
      // 8cd0: ifne 8cd8
      // 8cd3: goto 8ce2
      // 8cd8: aload 0
      // 8cd9: bipush 1
      // 8cda: putfield Lobby.mousedout Z
      // 8cdd: goto 8d79
      // 8ce2: aload 0
      // 8ce3: getfield Lobby.mousedout Z
      // 8ce6: ifne 8cee
      // 8ce9: goto 8d79
      // 8cee: aload 0
      // 8cef: getfield Lobby.dispcar I
      // 8cf2: iload 22
      // 8cf4: if_icmpne 8cfc
      // 8cf7: goto 8d54
      // 8cfc: iload 22
      // 8cfe: bipush -1
      // 8cff: if_icmpne 8d07
      // 8d02: goto 8d54
      // 8d07: aload 0
      // 8d08: getfield Lobby.cd LCarDefine;
      // 8d0b: getfield CarDefine.action I
      // 8d0e: bipush 6
      // 8d10: if_icmpne 8d18
      // 8d13: goto 8d54
      // 8d18: aload 0
      // 8d19: getfield Lobby.cd LCarDefine;
      // 8d1c: bipush 0
      // 8d1d: putfield CarDefine.action I
      // 8d20: aload 0
      // 8d21: iload 22
      // 8d23: putfield Lobby.dispcar I
      // 8d26: aload 0
      // 8d27: aload 0
      // 8d28: getfield Lobby.pcars [I
      // 8d2b: iload 22
      // 8d2d: iaload
      // 8d2e: putfield Lobby.forcar I
      // 8d31: aload 0
      // 8d32: aconst_null
      // 8d33: putfield Lobby.dispco LContO;
      // 8d36: invokestatic java/lang/System.gc ()V
      // 8d39: aload 0
      // 8d3a: new ContO
      // 8d3d: dup
      // 8d3e: aload 7
      // 8d40: aload 0
      // 8d41: getfield Lobby.forcar I
      // 8d44: aaload
      // 8d45: bipush 0
      // 8d46: bipush 0
      // 8d47: bipush 0
      // 8d48: bipush 0
      // 8d49: invokespecial ContO.<init> (LContO;IIII)V
      // 8d4c: putfield Lobby.dispco LContO;
      // 8d4f: goto 8d59
      // 8d54: aload 0
      // 8d55: bipush -1
      // 8d56: putfield Lobby.dispcar I
      // 8d59: aload 0
      // 8d5a: bipush 0
      // 8d5b: putfield Lobby.mousedout Z
      // 8d5e: goto 8d79
      // 8d63: aload 0
      // 8d64: getfield Lobby.mousedout Z
      // 8d67: ifne 8d6f
      // 8d6a: goto 8d79
      // 8d6f: aload 0
      // 8d70: bipush 0
      // 8d71: putfield Lobby.mousedout Z
      // 8d74: goto 8d79
      // 8d79: bipush 0
      // 8d7a: istore 23
      // 8d7c: iload 23
      // 8d7e: bipush 7
      // 8d80: if_icmplt 8d88
      // 8d83: goto 8e3d
      // 8d88: bipush 0
      // 8d89: istore 24
      // 8d8b: iload 24
      // 8d8d: aload 0
      // 8d8e: getfield Lobby.npo I
      // 8d91: if_icmplt 8d99
      // 8d94: goto 8e35
      // 8d99: aload 0
      // 8d9a: getfield Lobby.pgames [I
      // 8d9d: iload 24
      // 8d9f: iaload
      // 8da0: aload 0
      // 8da1: getfield Lobby.ongame I
      // 8da4: if_icmpeq 8dac
      // 8da7: goto 8e2d
      // 8dac: aload 0
      // 8dad: getfield Lobby.invos [Ljava/lang/String;
      // 8db0: iload 23
      // 8db2: aaload
      // 8db3: aload 0
      // 8db4: getfield Lobby.pnames [Ljava/lang/String;
      // 8db7: iload 24
      // 8db9: aaload
      // 8dba: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8dbd: ifne 8dc5
      // 8dc0: goto 8e2d
      // 8dc5: aload 0
      // 8dc6: getfield Lobby.dinvi [Ljava/lang/String;
      // 8dc9: iload 23
      // 8dcb: aaload
      // 8dcc: aload 0
      // 8dcd: getfield Lobby.invos [Ljava/lang/String;
      // 8dd0: iload 23
      // 8dd2: aaload
      // 8dd3: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8dd6: ifne 8dde
      // 8dd9: goto 8e2d
      // 8dde: iload 23
      // 8de0: istore 25
      // 8de2: iload 25
      // 8de4: bipush 6
      // 8de6: if_icmplt 8dee
      // 8de9: goto 8e16
      // 8dee: aload 0
      // 8def: getfield Lobby.invos [Ljava/lang/String;
      // 8df2: iload 25
      // 8df4: aload 0
      // 8df5: getfield Lobby.invos [Ljava/lang/String;
      // 8df8: iload 25
      // 8dfa: bipush 1
      // 8dfb: iadd
      // 8dfc: aaload
      // 8dfd: aastore
      // 8dfe: aload 0
      // 8dff: getfield Lobby.dinvi [Ljava/lang/String;
      // 8e02: iload 25
      // 8e04: aload 0
      // 8e05: getfield Lobby.dinvi [Ljava/lang/String;
      // 8e08: iload 25
      // 8e0a: bipush 1
      // 8e0b: iadd
      // 8e0c: aaload
      // 8e0d: aastore
      // 8e0e: iinc 25 1
      // 8e11: goto 8de2
      // 8e16: aload 0
      // 8e17: getfield Lobby.invos [Ljava/lang/String;
      // 8e1a: bipush 6
      // 8e1c: ldc ""
      // 8e1e: aastore
      // 8e1f: aload 0
      // 8e20: getfield Lobby.dinvi [Ljava/lang/String;
      // 8e23: bipush 6
      // 8e25: ldc ""
      // 8e27: aastore
      // 8e28: goto 8e2d
      // 8e2d: iinc 24 1
      // 8e30: goto 8d8b
      // 8e35: iinc 23 1
      // 8e38: goto 8d7c
      // 8e3d: aload 0
      // 8e3e: getfield Lobby.wait [I
      // 8e41: iload 15
      // 8e43: iaload
      // 8e44: ifgt 8e4c
      // 8e47: goto 91b1
      // 8e4c: bipush 0
      // 8e4d: istore 23
      // 8e4f: iload 21
      // 8e51: istore 24
      // 8e53: iload 24
      // 8e55: aload 0
      // 8e56: getfield Lobby.mnpls [I
      // 8e59: iload 15
      // 8e5b: iaload
      // 8e5c: if_icmplt 8e64
      // 8e5f: goto 91ac
      // 8e64: aload 0
      // 8e65: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8e68: aload 0
      // 8e69: sipush 200
      // 8e6c: sipush 200
      // 8e6f: sipush 200
      // 8e72: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 8e75: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 8e78: aload 0
      // 8e79: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8e7c: sipush 237
      // 8e7f: bipush 54
      // 8e81: iload 24
      // 8e83: bipush 42
      // 8e85: imul
      // 8e86: iadd
      // 8e87: sipush 170
      // 8e8a: bipush 40
      // 8e8c: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 8e8f: bipush 0
      // 8e90: istore 25
      // 8e92: aload 0
      // 8e93: getfield Lobby.pgames [I
      // 8e96: aload 0
      // 8e97: getfield Lobby.im I
      // 8e9a: iaload
      // 8e9b: aload 0
      // 8e9c: getfield Lobby.ongame I
      // 8e9f: if_icmpeq 8ea7
      // 8ea2: goto 8ec3
      // 8ea7: aload 0
      // 8ea8: getfield Lobby.gplyrs [Ljava/lang/String;
      // 8eab: iload 15
      // 8ead: aaload
      // 8eae: ldc ""
      // 8eb0: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8eb3: ifeq 8ebb
      // 8eb6: goto 8f50
      // 8ebb: bipush 1
      // 8ebc: istore 25
      // 8ebe: goto 8f50
      // 8ec3: aload 0
      // 8ec4: getfield Lobby.gwarb [I
      // 8ec7: iload 15
      // 8ec9: iaload
      // 8eca: ifeq 8ed2
      // 8ecd: goto 8f0a
      // 8ed2: aload 0
      // 8ed3: getfield Lobby.gplyrs [Ljava/lang/String;
      // 8ed6: iload 15
      // 8ed8: aaload
      // 8ed9: ldc ""
      // 8edb: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8ede: ifeq 8ee6
      // 8ee1: goto 8f50
      // 8ee6: aload 0
      // 8ee7: getfield Lobby.gplyrs [Ljava/lang/String;
      // 8eea: iload 15
      // 8eec: aaload
      // 8eed: aload 0
      // 8eee: getfield Lobby.pnames [Ljava/lang/String;
      // 8ef1: aload 0
      // 8ef2: getfield Lobby.im I
      // 8ef5: aaload
      // 8ef6: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 8ef9: bipush -1
      // 8efa: if_icmpeq 8f02
      // 8efd: goto 8f50
      // 8f02: bipush 1
      // 8f03: istore 25
      // 8f05: goto 8f50
      // 8f0a: aload 0
      // 8f0b: getfield Lobby.xt LxtGraphics;
      // 8f0e: getfield xtGraphics.clan Ljava/lang/String;
      // 8f11: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8f14: aload 0
      // 8f15: getfield Lobby.gaclan [Ljava/lang/String;
      // 8f18: iload 15
      // 8f1a: aaload
      // 8f1b: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8f1e: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8f21: ifeq 8f29
      // 8f24: goto 8f50
      // 8f29: aload 0
      // 8f2a: getfield Lobby.xt LxtGraphics;
      // 8f2d: getfield xtGraphics.clan Ljava/lang/String;
      // 8f30: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8f33: aload 0
      // 8f34: getfield Lobby.gvclan [Ljava/lang/String;
      // 8f37: iload 15
      // 8f39: aaload
      // 8f3a: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 8f3d: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8f40: ifeq 8f48
      // 8f43: goto 8f50
      // 8f48: bipush 1
      // 8f49: istore 25
      // 8f4b: goto 8f50
      // 8f50: iload 1
      // 8f51: sipush 237
      // 8f54: if_icmpgt 8f5c
      // 8f57: goto 8fde
      // 8f5c: iload 1
      // 8f5d: sipush 407
      // 8f60: if_icmplt 8f68
      // 8f63: goto 8fde
      // 8f68: iload 2
      // 8f69: bipush 54
      // 8f6b: iload 24
      // 8f6d: bipush 42
      // 8f6f: imul
      // 8f70: iadd
      // 8f71: if_icmpgt 8f79
      // 8f74: goto 8fde
      // 8f79: iload 2
      // 8f7a: bipush 94
      // 8f7c: iload 24
      // 8f7e: bipush 42
      // 8f80: imul
      // 8f81: iadd
      // 8f82: if_icmplt 8f8a
      // 8f85: goto 8fde
      // 8f8a: iload 25
      // 8f8c: ifeq 8f94
      // 8f8f: goto 8fde
      // 8f94: aload 0
      // 8f95: getfield Lobby.pgames [I
      // 8f98: aload 0
      // 8f99: getfield Lobby.im I
      // 8f9c: iaload
      // 8f9d: aload 0
      // 8f9e: getfield Lobby.ongame I
      // 8fa1: if_icmpeq 8fa9
      // 8fa4: goto 8fc1
      // 8fa9: aload 0
      // 8faa: ldc_w "<     Invite Player      "
      // 8fad: sipush 322
      // 8fb0: bipush 79
      // 8fb2: iload 24
      // 8fb4: bipush 42
      // 8fb6: imul
      // 8fb7: iadd
      // 8fb8: bipush 0
      // 8fb9: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 8fbc: goto 8fd4
      // 8fc1: aload 0
      // 8fc2: ldc_w "    Join Game    "
      // 8fc5: sipush 322
      // 8fc8: bipush 79
      // 8fca: iload 24
      // 8fcc: bipush 42
      // 8fce: imul
      // 8fcf: iadd
      // 8fd0: bipush 0
      // 8fd1: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 8fd4: aload 0
      // 8fd5: bipush 1
      // 8fd6: putfield Lobby.pbtn I
      // 8fd9: goto 91a1
      // 8fde: aload 0
      // 8fdf: getfield Lobby.invos [Ljava/lang/String;
      // 8fe2: iload 23
      // 8fe4: aaload
      // 8fe5: ldc ""
      // 8fe7: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 8fea: ifne 8ff2
      // 8fed: goto 908a
      // 8ff2: aload 0
      // 8ff3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 8ff6: new java/awt/Color
      // 8ff9: dup
      // 8ffa: bipush 0
      // 8ffb: bipush 0
      // 8ffc: bipush 0
      // 8ffd: invokespecial java/awt/Color.<init> (III)V
      // 9000: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9003: aload 0
      // 9004: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9007: new java/awt/Font
      // 900a: dup
      // 900b: ldc_w "Arial"
      // 900e: bipush 1
      // 900f: bipush 12
      // 9011: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9014: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9017: aload 0
      // 9018: aload 0
      // 9019: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 901c: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 901f: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9022: aload 0
      // 9023: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9026: ldc_w "Empty"
      // 9029: sipush 322
      // 902c: aload 0
      // 902d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9030: ldc_w "Empty"
      // 9033: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9036: bipush 2
      // 9037: idiv
      // 9038: isub
      // 9039: bipush 72
      // 903b: iload 24
      // 903d: bipush 42
      // 903f: imul
      // 9040: iadd
      // 9041: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9044: aload 0
      // 9045: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9048: new java/awt/Font
      // 904b: dup
      // 904c: ldc_w "Arial"
      // 904f: bipush 0
      // 9050: bipush 10
      // 9052: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9055: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9058: aload 0
      // 9059: aload 0
      // 905a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 905d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9060: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9063: aload 0
      // 9064: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9067: ldc_w "Waiting for player..."
      // 906a: sipush 322
      // 906d: aload 0
      // 906e: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9071: ldc_w "Waiting for player..."
      // 9074: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9077: bipush 2
      // 9078: idiv
      // 9079: isub
      // 907a: bipush 84
      // 907c: iload 24
      // 907e: bipush 42
      // 9080: imul
      // 9081: iadd
      // 9082: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9085: goto 91a1
      // 908a: aload 0
      // 908b: getfield Lobby.dinvi [Ljava/lang/String;
      // 908e: iload 23
      // 9090: aaload
      // 9091: aload 0
      // 9092: getfield Lobby.invos [Ljava/lang/String;
      // 9095: iload 23
      // 9097: aaload
      // 9098: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 909b: ifeq 90a3
      // 909e: goto 9106
      // 90a3: aload 0
      // 90a4: getfield Lobby.nflk I
      // 90a7: ifne 90af
      // 90aa: goto 91a1
      // 90af: aload 0
      // 90b0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 90b3: new java/awt/Color
      // 90b6: dup
      // 90b7: bipush 0
      // 90b8: bipush 0
      // 90b9: bipush 0
      // 90ba: invokespecial java/awt/Color.<init> (III)V
      // 90bd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 90c0: aload 0
      // 90c1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 90c4: new java/awt/Font
      // 90c7: dup
      // 90c8: ldc_w "Arial"
      // 90cb: bipush 0
      // 90cc: bipush 12
      // 90ce: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 90d1: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 90d4: aload 0
      // 90d5: aload 0
      // 90d6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 90d9: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 90dc: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 90df: aload 0
      // 90e0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 90e3: ldc_w "Inviting player..."
      // 90e6: sipush 322
      // 90e9: aload 0
      // 90ea: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 90ed: ldc_w "Inviting player..."
      // 90f0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 90f3: bipush 2
      // 90f4: idiv
      // 90f5: isub
      // 90f6: bipush 79
      // 90f8: iload 24
      // 90fa: bipush 42
      // 90fc: imul
      // 90fd: iadd
      // 90fe: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9101: goto 91a1
      // 9106: aload 0
      // 9107: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 910a: new java/awt/Color
      // 910d: dup
      // 910e: bipush 0
      // 910f: bipush 0
      // 9110: bipush 0
      // 9111: invokespecial java/awt/Color.<init> (III)V
      // 9114: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9117: aload 0
      // 9118: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 911b: new java/awt/Font
      // 911e: dup
      // 911f: ldc_w "Arial"
      // 9122: bipush 0
      // 9123: bipush 12
      // 9125: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9128: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 912b: aload 0
      // 912c: aload 0
      // 912d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9130: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9133: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9136: aload 0
      // 9137: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 913a: ldc_w "Player Invited!"
      // 913d: sipush 322
      // 9140: aload 0
      // 9141: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9144: ldc_w "Player Invited!"
      // 9147: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 914a: bipush 2
      // 914b: idiv
      // 914c: isub
      // 914d: bipush 71
      // 914f: iload 24
      // 9151: bipush 42
      // 9153: imul
      // 9154: iadd
      // 9155: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9158: aload 0
      // 9159: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 915c: new java/awt/Font
      // 915f: dup
      // 9160: ldc_w "Arial"
      // 9163: bipush 1
      // 9164: bipush 12
      // 9166: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9169: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 916c: aload 0
      // 916d: aload 0
      // 916e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9171: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9174: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9177: aload 0
      // 9178: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 917b: aload 0
      // 917c: getfield Lobby.invos [Ljava/lang/String;
      // 917f: iload 23
      // 9181: aaload
      // 9182: sipush 322
      // 9185: aload 0
      // 9186: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9189: aload 0
      // 918a: getfield Lobby.invos [Ljava/lang/String;
      // 918d: iload 23
      // 918f: aaload
      // 9190: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9193: bipush 2
      // 9194: idiv
      // 9195: isub
      // 9196: bipush 87
      // 9198: iload 24
      // 919a: bipush 42
      // 919c: imul
      // 919d: iadd
      // 919e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 91a1: iinc 23 1
      // 91a4: iinc 24 1
      // 91a7: goto 8e53
      // 91ac: goto 91b1
      // 91b1: aload 0
      // 91b2: getfield Lobby.xt LxtGraphics;
      // 91b5: getfield xtGraphics.lan Z
      // 91b8: ifne 91c0
      // 91bb: goto 9265
      // 91c0: aload 0
      // 91c1: getfield Lobby.mnbts [I
      // 91c4: iload 15
      // 91c6: iaload
      // 91c7: ifne 91cf
      // 91ca: goto 9265
      // 91cf: aload 0
      // 91d0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 91d3: new java/awt/Color
      // 91d6: dup
      // 91d7: bipush 0
      // 91d8: bipush 0
      // 91d9: bipush 0
      // 91da: invokespecial java/awt/Color.<init> (III)V
      // 91dd: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 91e0: aload 0
      // 91e1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 91e4: new java/awt/Font
      // 91e7: dup
      // 91e8: ldc_w "Arial"
      // 91eb: bipush 1
      // 91ec: bipush 12
      // 91ee: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 91f1: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 91f4: aload 0
      // 91f5: aload 0
      // 91f6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 91f9: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 91fc: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 91ff: aload 0
      // 9200: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9203: new java/lang/StringBuilder
      // 9206: dup
      // 9207: invokespecial java/lang/StringBuilder.<init> ()V
      // 920a: ldc_w "Plus "
      // 920d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9210: aload 0
      // 9211: getfield Lobby.mnbts [I
      // 9214: iload 15
      // 9216: iaload
      // 9217: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 921a: ldc_w " MadBots!"
      // 921d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9220: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9223: sipush 322
      // 9226: aload 0
      // 9227: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 922a: new java/lang/StringBuilder
      // 922d: dup
      // 922e: invokespecial java/lang/StringBuilder.<init> ()V
      // 9231: ldc_w "Plus "
      // 9234: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9237: aload 0
      // 9238: getfield Lobby.mnbts [I
      // 923b: iload 15
      // 923d: iaload
      // 923e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9241: ldc_w " MadBots!"
      // 9244: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9247: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 924a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 924d: bipush 2
      // 924e: idiv
      // 924f: isub
      // 9250: bipush 73
      // 9252: aload 0
      // 9253: getfield Lobby.mnpls [I
      // 9256: iload 15
      // 9258: iaload
      // 9259: bipush 42
      // 925b: imul
      // 925c: iadd
      // 925d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9260: goto 9265
      // 9265: aload 0
      // 9266: getfield Lobby.dispcar I
      // 9269: bipush -1
      // 926a: if_icmpne 9272
      // 926d: goto 927f
      // 9272: aload 0
      // 9273: getfield Lobby.conon I
      // 9276: bipush 1
      // 9277: if_icmpne 927f
      // 927a: goto b722
      // 927f: aload 0
      // 9280: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9283: aload 0
      // 9284: getfield Lobby.xt LxtGraphics;
      // 9287: getfield xtGraphics.sts Ljava/awt/Image;
      // 928a: sipush 537
      // 928d: bipush 39
      // 928f: aconst_null
      // 9290: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // 9293: pop
      // 9294: aload 0
      // 9295: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9298: aload 0
      // 9299: sipush 200
      // 929c: sipush 200
      // 929f: sipush 200
      // 92a2: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // 92a5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 92a8: aload 0
      // 92a9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 92ac: sipush 415
      // 92af: bipush 54
      // 92b1: sipush 293
      // 92b4: sipush 166
      // 92b7: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 92ba: aload 0
      // 92bb: getfield Lobby.conon I
      // 92be: bipush 1
      // 92bf: if_icmpeq 92c7
      // 92c2: goto ae97
      // 92c7: aload 0
      // 92c8: getfield Lobby.wait [I
      // 92cb: iload 15
      // 92cd: iaload
      // 92ce: ifgt 92d6
      // 92d1: goto a1b2
      // 92d6: aload 0
      // 92d7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 92da: new java/awt/Color
      // 92dd: dup
      // 92de: bipush 0
      // 92df: bipush 0
      // 92e0: bipush 0
      // 92e1: invokespecial java/awt/Color.<init> (III)V
      // 92e4: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 92e7: aload 0
      // 92e8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 92eb: new java/awt/Font
      // 92ee: dup
      // 92ef: ldc_w "Arial"
      // 92f2: bipush 1
      // 92f3: bipush 12
      // 92f5: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 92f8: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 92fb: aload 0
      // 92fc: aload 0
      // 92fd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9300: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9303: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9306: aload 0
      // 9307: getfield Lobby.gwarb [I
      // 930a: iload 15
      // 930c: iaload
      // 930d: ifeq 9315
      // 9310: goto 98a2
      // 9315: aload 0
      // 9316: getfield Lobby.wait [I
      // 9319: iload 15
      // 931b: iaload
      // 931c: bipush 30
      // 931e: if_icmple 9326
      // 9321: goto 9336
      // 9326: aload 0
      // 9327: getfield Lobby.npls [I
      // 932a: iload 15
      // 932c: iaload
      // 932d: bipush 1
      // 932e: if_icmple 9336
      // 9331: goto 95b4
      // 9336: ldc_w "s"
      // 9339: astore 23
      // 933b: aload 0
      // 933c: getfield Lobby.mnpls [I
      // 933f: iload 15
      // 9341: iaload
      // 9342: aload 0
      // 9343: getfield Lobby.npls [I
      // 9346: iload 15
      // 9348: iaload
      // 9349: isub
      // 934a: bipush 1
      // 934b: if_icmpeq 9353
      // 934e: goto 935c
      // 9353: ldc ""
      // 9355: astore 23
      // 9357: goto 935c
      // 935c: aload 0
      // 935d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9360: new java/lang/StringBuilder
      // 9363: dup
      // 9364: invokespecial java/lang/StringBuilder.<init> ()V
      // 9367: ldc_w "Waiting for "
      // 936a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 936d: aload 0
      // 936e: getfield Lobby.mnpls [I
      // 9371: iload 15
      // 9373: iaload
      // 9374: aload 0
      // 9375: getfield Lobby.npls [I
      // 9378: iload 15
      // 937a: iaload
      // 937b: isub
      // 937c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 937f: ldc_w " more player"
      // 9382: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9385: aload 23
      // 9387: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 938a: ldc_w " to join to start."
      // 938d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9390: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9393: sipush 561
      // 9396: aload 0
      // 9397: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 939a: new java/lang/StringBuilder
      // 939d: dup
      // 939e: invokespecial java/lang/StringBuilder.<init> ()V
      // 93a1: ldc_w "Waiting for "
      // 93a4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 93a7: aload 0
      // 93a8: getfield Lobby.mnpls [I
      // 93ab: iload 15
      // 93ad: iaload
      // 93ae: aload 0
      // 93af: getfield Lobby.npls [I
      // 93b2: iload 15
      // 93b4: iaload
      // 93b5: isub
      // 93b6: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 93b9: ldc_w " more player"
      // 93bc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 93bf: aload 23
      // 93c1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 93c4: ldc_w " to join to start."
      // 93c7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 93ca: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 93cd: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 93d0: bipush 2
      // 93d1: idiv
      // 93d2: isub
      // 93d3: bipush 98
      // 93d5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 93d8: aload 0
      // 93d9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 93dc: new java/awt/Font
      // 93df: dup
      // 93e0: ldc_w "Arial"
      // 93e3: bipush 0
      // 93e4: bipush 12
      // 93e6: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 93e9: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 93ec: aload 0
      // 93ed: aload 0
      // 93ee: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 93f1: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 93f4: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 93f7: sipush 134
      // 93fa: istore 24
      // 93fc: aload 0
      // 93fd: getfield Lobby.gmaker [Ljava/lang/String;
      // 9400: iload 15
      // 9402: aaload
      // 9403: ldc_w "Coach Insano"
      // 9406: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9409: ifeq 9411
      // 940c: goto 94e9
      // 9411: aload 0
      // 9412: getfield Lobby.gmaker [Ljava/lang/String;
      // 9415: iload 15
      // 9417: aaload
      // 9418: aload 0
      // 9419: getfield Lobby.pnames [Ljava/lang/String;
      // 941c: aload 0
      // 941d: getfield Lobby.im I
      // 9420: aaload
      // 9421: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9424: ifeq 942c
      // 9427: goto 94e9
      // 942c: bipush 0
      // 942d: istore 25
      // 942f: bipush 0
      // 9430: istore 26
      // 9432: iload 26
      // 9434: aload 0
      // 9435: getfield Lobby.npo I
      // 9438: if_icmplt 9440
      // 943b: goto 947c
      // 9440: aload 0
      // 9441: getfield Lobby.pgames [I
      // 9444: iload 26
      // 9446: iaload
      // 9447: aload 0
      // 9448: getfield Lobby.ongame I
      // 944b: if_icmpeq 9453
      // 944e: goto 9474
      // 9453: aload 0
      // 9454: getfield Lobby.gmaker [Ljava/lang/String;
      // 9457: iload 15
      // 9459: aaload
      // 945a: aload 0
      // 945b: getfield Lobby.pnames [Ljava/lang/String;
      // 945e: iload 26
      // 9460: aaload
      // 9461: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9464: ifne 946c
      // 9467: goto 9474
      // 946c: bipush 1
      // 946d: istore 25
      // 946f: goto 9474
      // 9474: iinc 26 1
      // 9477: goto 9432
      // 947c: iload 25
      // 947e: ifne 9486
      // 9481: goto 94e4
      // 9486: sipush 144
      // 9489: istore 24
      // 948b: aload 0
      // 948c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 948f: new java/lang/StringBuilder
      // 9492: dup
      // 9493: invokespecial java/lang/StringBuilder.<init> ()V
      // 9496: ldc ""
      // 9498: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 949b: aload 0
      // 949c: getfield Lobby.gmaker [Ljava/lang/String;
      // 949f: iload 15
      // 94a1: aaload
      // 94a2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 94a5: ldc_w " can start this game at anytime."
      // 94a8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 94ab: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 94ae: sipush 561
      // 94b1: aload 0
      // 94b2: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 94b5: new java/lang/StringBuilder
      // 94b8: dup
      // 94b9: invokespecial java/lang/StringBuilder.<init> ()V
      // 94bc: ldc ""
      // 94be: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 94c1: aload 0
      // 94c2: getfield Lobby.gmaker [Ljava/lang/String;
      // 94c5: iload 15
      // 94c7: aaload
      // 94c8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 94cb: ldc_w " can start this game at anytime."
      // 94ce: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 94d1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 94d4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 94d7: bipush 2
      // 94d8: idiv
      // 94d9: isub
      // 94da: bipush 124
      // 94dc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 94df: goto 94e4
      // 94e4: goto 94e9
      // 94e9: aload 0
      // 94ea: getfield Lobby.npls [I
      // 94ed: iload 15
      // 94ef: iaload
      // 94f0: bipush 1
      // 94f1: if_icmpgt 94f9
      // 94f4: goto 95af
      // 94f9: new java/lang/StringBuilder
      // 94fc: dup
      // 94fd: invokespecial java/lang/StringBuilder.<init> ()V
      // 9500: ldc ""
      // 9502: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9505: aload 0
      // 9506: getfield Lobby.wait [I
      // 9509: iload 15
      // 950b: iaload
      // 950c: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 950f: ldc_w " seconds"
      // 9512: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9515: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9518: astore 25
      // 951a: aload 0
      // 951b: getfield Lobby.wait [I
      // 951e: iload 15
      // 9520: iaload
      // 9521: bipush 60
      // 9523: if_icmpgt 952b
      // 9526: goto 955e
      // 952b: new java/lang/StringBuilder
      // 952e: dup
      // 952f: invokespecial java/lang/StringBuilder.<init> ()V
      // 9532: ldc ""
      // 9534: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9537: aload 0
      // 9538: getfield Lobby.wait [I
      // 953b: iload 15
      // 953d: iaload
      // 953e: i2f
      // 953f: ldc_w 60.0
      // 9542: fdiv
      // 9543: ldc 100.0
      // 9545: fmul
      // 9546: f2i
      // 9547: i2f
      // 9548: ldc 100.0
      // 954a: fdiv
      // 954b: invokevirtual java/lang/StringBuilder.append (F)Ljava/lang/StringBuilder;
      // 954e: ldc_w " minutes"
      // 9551: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9554: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9557: astore 25
      // 9559: goto 955e
      // 955e: aload 0
      // 955f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9562: new java/lang/StringBuilder
      // 9565: dup
      // 9566: invokespecial java/lang/StringBuilder.<init> ()V
      // 9569: ldc_w "( Waiting "
      // 956c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 956f: aload 25
      // 9571: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9574: ldc_w " maximum! )"
      // 9577: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 957a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 957d: sipush 561
      // 9580: aload 0
      // 9581: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9584: new java/lang/StringBuilder
      // 9587: dup
      // 9588: invokespecial java/lang/StringBuilder.<init> ()V
      // 958b: ldc_w "( Waiting "
      // 958e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9591: aload 25
      // 9593: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9596: ldc_w " maximum! )"
      // 9599: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 959c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 959f: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 95a2: bipush 2
      // 95a3: idiv
      // 95a4: isub
      // 95a5: iload 24
      // 95a7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 95aa: goto 95af
      // 95af: goto 96e0
      // 95b4: new java/util/Date
      // 95b7: dup
      // 95b8: invokespecial java/util/Date.<init> ()V
      // 95bb: astore 23
      // 95bd: aload 23
      // 95bf: invokevirtual java/util/Date.getTime ()J
      // 95c2: lstore 24
      // 95c4: aload 0
      // 95c5: getfield Lobby.ptime J
      // 95c8: lconst_0
      // 95c9: lcmp
      // 95ca: ifne 95d2
      // 95cd: goto 95e5
      // 95d2: lload 24
      // 95d4: aload 0
      // 95d5: getfield Lobby.ptime J
      // 95d8: ldc2_w 1500
      // 95db: ladd
      // 95dc: lcmp
      // 95dd: ifgt 95e5
      // 95e0: goto 9626
      // 95e5: aload 0
      // 95e6: getfield Lobby.ptime J
      // 95e9: lconst_0
      // 95ea: lcmp
      // 95eb: ifne 95f3
      // 95ee: goto 961b
      // 95f3: aload 0
      // 95f4: getfield Lobby.wait [I
      // 95f7: iload 15
      // 95f9: dup2
      // 95fa: iaload
      // 95fb: bipush 1
      // 95fc: isub
      // 95fd: iastore
      // 95fe: aload 0
      // 95ff: getfield Lobby.wait [I
      // 9602: iload 15
      // 9604: iaload
      // 9605: bipush 1
      // 9606: if_icmplt 960e
      // 9609: goto 961b
      // 960e: aload 0
      // 960f: getfield Lobby.wait [I
      // 9612: iload 15
      // 9614: bipush 1
      // 9615: iastore
      // 9616: goto 961b
      // 961b: aload 0
      // 961c: lload 24
      // 961e: putfield Lobby.ptime J
      // 9621: goto 9626
      // 9626: aload 0
      // 9627: getfield Lobby.pgames [I
      // 962a: aload 0
      // 962b: getfield Lobby.im I
      // 962e: iaload
      // 962f: aload 0
      // 9630: getfield Lobby.ongame I
      // 9633: if_icmpne 963b
      // 9636: goto 9647
      // 963b: aload 0
      // 963c: getfield Lobby.nflk I
      // 963f: ifne 9647
      // 9642: goto 96c1
      // 9647: aload 0
      // 9648: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 964b: new java/lang/StringBuilder
      // 964e: dup
      // 964f: invokespecial java/lang/StringBuilder.<init> ()V
      // 9652: ldc_w "Game starts in "
      // 9655: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9658: aload 0
      // 9659: getfield Lobby.wait [I
      // 965c: iload 15
      // 965e: iaload
      // 965f: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9662: ldc_w " seconds!"
      // 9665: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9668: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 966b: sipush 561
      // 966e: aload 0
      // 966f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9672: new java/lang/StringBuilder
      // 9675: dup
      // 9676: invokespecial java/lang/StringBuilder.<init> ()V
      // 9679: ldc_w "Game starts in "
      // 967c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 967f: aload 0
      // 9680: getfield Lobby.wait [I
      // 9683: iload 15
      // 9685: iaload
      // 9686: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9689: ldc_w " seconds!"
      // 968c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 968f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9692: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9695: bipush 2
      // 9696: idiv
      // 9697: isub
      // 9698: bipush 124
      // 969a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 969d: aload 0
      // 969e: getfield Lobby.pgames [I
      // 96a1: aload 0
      // 96a2: getfield Lobby.im I
      // 96a5: iaload
      // 96a6: aload 0
      // 96a7: getfield Lobby.ongame I
      // 96aa: if_icmpne 96b2
      // 96ad: goto 96e0
      // 96b2: aload 0
      // 96b3: dup
      // 96b4: getfield Lobby.nflk I
      // 96b7: bipush 1
      // 96b8: isub
      // 96b9: putfield Lobby.nflk I
      // 96bc: goto 96e0
      // 96c1: aload 0
      // 96c2: getfield Lobby.pgames [I
      // 96c5: aload 0
      // 96c6: getfield Lobby.im I
      // 96c9: iaload
      // 96ca: aload 0
      // 96cb: getfield Lobby.ongame I
      // 96ce: if_icmpne 96d6
      // 96d1: goto 96e0
      // 96d6: aload 0
      // 96d7: bipush 3
      // 96d8: putfield Lobby.nflk I
      // 96db: goto 96e0
      // 96e0: aload 0
      // 96e1: getfield Lobby.pgames [I
      // 96e4: aload 0
      // 96e5: getfield Lobby.im I
      // 96e8: iaload
      // 96e9: aload 0
      // 96ea: getfield Lobby.ongame I
      // 96ed: if_icmpne 96f5
      // 96f0: goto 979b
      // 96f5: aload 0
      // 96f6: getfield Lobby.gplyrs [Ljava/lang/String;
      // 96f9: iload 15
      // 96fb: aaload
      // 96fc: ldc ""
      // 96fe: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9701: ifeq 9709
      // 9704: goto 9725
      // 9709: aload 0
      // 970a: getfield Lobby.gplyrs [Ljava/lang/String;
      // 970d: iload 15
      // 970f: aaload
      // 9710: aload 0
      // 9711: getfield Lobby.pnames [Ljava/lang/String;
      // 9714: aload 0
      // 9715: getfield Lobby.im I
      // 9718: aaload
      // 9719: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 971c: bipush -1
      // 971d: if_icmpne 9725
      // 9720: goto 9738
      // 9725: aload 0
      // 9726: ldc_w "    Join this Game    "
      // 9729: sipush 561
      // 972c: sipush 182
      // 972f: bipush 0
      // 9730: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 9733: goto af5f
      // 9738: aload 0
      // 9739: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 973c: new java/awt/Font
      // 973f: dup
      // 9740: ldc_w "Arial"
      // 9743: bipush 1
      // 9744: bipush 12
      // 9746: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9749: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 974c: aload 0
      // 974d: aload 0
      // 974e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9751: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9754: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9757: aload 0
      // 9758: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 975b: new java/awt/Color
      // 975e: dup
      // 975f: sipush 128
      // 9762: bipush 73
      // 9764: bipush 0
      // 9765: invokespecial java/awt/Color.<init> (III)V
      // 9768: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 976b: aload 0
      // 976c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 976f: ldc_w "Private Game, only specific players allowed."
      // 9772: sipush 561
      // 9775: aload 0
      // 9776: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9779: ldc_w "Private Game, only specific players allowed."
      // 977c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 977f: bipush 2
      // 9780: idiv
      // 9781: isub
      // 9782: sipush 180
      // 9785: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9788: aload 0
      // 9789: ldc_w "    Join this Game    "
      // 978c: sipush 561
      // 978f: sipush -1000
      // 9792: bipush 0
      // 9793: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 9796: goto af5f
      // 979b: aload 0
      // 979c: getfield Lobby.gmaker [Ljava/lang/String;
      // 979f: iload 15
      // 97a1: aaload
      // 97a2: aload 0
      // 97a3: getfield Lobby.pnames [Ljava/lang/String;
      // 97a6: aload 0
      // 97a7: getfield Lobby.im I
      // 97aa: aaload
      // 97ab: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 97ae: ifne 97b6
      // 97b1: goto 984d
      // 97b6: aload 0
      // 97b7: getfield Lobby.npls [I
      // 97ba: iload 15
      // 97bc: iaload
      // 97bd: bipush 1
      // 97be: if_icmpgt 97c6
      // 97c1: goto 97f8
      // 97c6: aload 0
      // 97c7: getfield Lobby.fstart Z
      // 97ca: ifeq 97d2
      // 97cd: goto 97e5
      // 97d2: aload 0
      // 97d3: ldc_w "    Start this Game Now!    "
      // 97d6: sipush 561
      // 97d9: sipush 182
      // 97dc: bipush 0
      // 97dd: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 97e0: goto af5f
      // 97e5: aload 0
      // 97e6: ldc_w "    Starting game now, one moment...    "
      // 97e9: sipush 561
      // 97ec: sipush 182
      // 97ef: bipush 0
      // 97f0: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 97f3: goto af5f
      // 97f8: aload 0
      // 97f9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 97fc: new java/awt/Font
      // 97ff: dup
      // 9800: ldc_w "Arial"
      // 9803: bipush 1
      // 9804: bipush 12
      // 9806: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9809: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 980c: aload 0
      // 980d: aload 0
      // 980e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9811: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9814: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9817: aload 0
      // 9818: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 981b: new java/awt/Color
      // 981e: dup
      // 981f: sipush 128
      // 9822: bipush 73
      // 9824: bipush 0
      // 9825: invokespecial java/awt/Color.<init> (III)V
      // 9828: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 982b: aload 0
      // 982c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 982f: ldc_w "You have created this game."
      // 9832: sipush 561
      // 9835: aload 0
      // 9836: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9839: ldc_w "You have created this game."
      // 983c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 983f: bipush 2
      // 9840: idiv
      // 9841: isub
      // 9842: sipush 180
      // 9845: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9848: goto af5f
      // 984d: aload 0
      // 984e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9851: new java/awt/Font
      // 9854: dup
      // 9855: ldc_w "Arial"
      // 9858: bipush 1
      // 9859: bipush 12
      // 985b: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 985e: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9861: aload 0
      // 9862: aload 0
      // 9863: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9866: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9869: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 986c: aload 0
      // 986d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9870: new java/awt/Color
      // 9873: dup
      // 9874: bipush 80
      // 9876: sipush 128
      // 9879: bipush 0
      // 987a: invokespecial java/awt/Color.<init> (III)V
      // 987d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9880: aload 0
      // 9881: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9884: ldc_w "You have joined this game."
      // 9887: sipush 561
      // 988a: aload 0
      // 988b: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 988e: ldc_w "You have joined this game."
      // 9891: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9894: bipush 2
      // 9895: idiv
      // 9896: isub
      // 9897: sipush 180
      // 989a: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 989d: goto af5f
      // 98a2: ldc_w "s"
      // 98a5: astore 23
      // 98a7: aload 0
      // 98a8: getfield Lobby.mnpls [I
      // 98ab: iload 15
      // 98ad: iaload
      // 98ae: aload 0
      // 98af: getfield Lobby.npls [I
      // 98b2: iload 15
      // 98b4: iaload
      // 98b5: isub
      // 98b6: bipush 1
      // 98b7: if_icmpeq 98bf
      // 98ba: goto 98c8
      // 98bf: ldc ""
      // 98c1: astore 23
      // 98c3: goto 98c8
      // 98c8: aload 0
      // 98c9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 98cc: new java/lang/StringBuilder
      // 98cf: dup
      // 98d0: invokespecial java/lang/StringBuilder.<init> ()V
      // 98d3: ldc_w "Waiting for "
      // 98d6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 98d9: aload 0
      // 98da: getfield Lobby.mnpls [I
      // 98dd: iload 15
      // 98df: iaload
      // 98e0: aload 0
      // 98e1: getfield Lobby.npls [I
      // 98e4: iload 15
      // 98e6: iaload
      // 98e7: isub
      // 98e8: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 98eb: ldc_w " clan member"
      // 98ee: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 98f1: aload 23
      // 98f3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 98f6: ldc_w " to join to start."
      // 98f9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 98fc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 98ff: sipush 561
      // 9902: aload 0
      // 9903: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9906: new java/lang/StringBuilder
      // 9909: dup
      // 990a: invokespecial java/lang/StringBuilder.<init> ()V
      // 990d: ldc_w "Waiting for "
      // 9910: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9913: aload 0
      // 9914: getfield Lobby.mnpls [I
      // 9917: iload 15
      // 9919: iaload
      // 991a: aload 0
      // 991b: getfield Lobby.npls [I
      // 991e: iload 15
      // 9920: iaload
      // 9921: isub
      // 9922: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9925: ldc_w " clan member"
      // 9928: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 992b: aload 23
      // 992d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9930: ldc_w " to join to start."
      // 9933: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9936: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9939: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 993c: bipush 2
      // 993d: idiv
      // 993e: isub
      // 993f: bipush 72
      // 9941: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9944: aload 0
      // 9945: getfield Lobby.mnpls [I
      // 9948: iload 15
      // 994a: iaload
      // 994b: bipush 2
      // 994c: idiv
      // 994d: istore 24
      // 994f: aload 0
      // 9950: getfield Lobby.mnpls [I
      // 9953: iload 15
      // 9955: iaload
      // 9956: bipush 2
      // 9957: idiv
      // 9958: istore 25
      // 995a: bipush 0
      // 995b: istore 26
      // 995d: iload 26
      // 995f: aload 0
      // 9960: getfield Lobby.npo I
      // 9963: if_icmplt 996b
      // 9966: goto 99f1
      // 996b: aload 0
      // 996c: getfield Lobby.pgames [I
      // 996f: iload 26
      // 9971: iaload
      // 9972: aload 0
      // 9973: getfield Lobby.gnum [I
      // 9976: iload 15
      // 9978: iaload
      // 9979: if_icmpeq 9981
      // 997c: goto 99e9
      // 9981: aload 0
      // 9982: getfield Lobby.pclan [Ljava/lang/String;
      // 9985: iload 26
      // 9987: aaload
      // 9988: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 998b: aload 0
      // 998c: getfield Lobby.gaclan [Ljava/lang/String;
      // 998f: iload 15
      // 9991: aaload
      // 9992: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9995: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9998: ifne 99a0
      // 999b: goto 99b5
      // 99a0: iinc 24 -1
      // 99a3: iload 24
      // 99a5: iflt 99ad
      // 99a8: goto 99b5
      // 99ad: bipush 0
      // 99ae: istore 24
      // 99b0: goto 99b5
      // 99b5: aload 0
      // 99b6: getfield Lobby.pclan [Ljava/lang/String;
      // 99b9: iload 26
      // 99bb: aaload
      // 99bc: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 99bf: aload 0
      // 99c0: getfield Lobby.gvclan [Ljava/lang/String;
      // 99c3: iload 15
      // 99c5: aaload
      // 99c6: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 99c9: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 99cc: ifne 99d4
      // 99cf: goto 99e9
      // 99d4: iinc 25 -1
      // 99d7: iload 25
      // 99d9: iflt 99e1
      // 99dc: goto 99e9
      // 99e1: bipush 0
      // 99e2: istore 25
      // 99e4: goto 99e9
      // 99e9: iinc 26 1
      // 99ec: goto 995d
      // 99f1: aload 0
      // 99f2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 99f5: new java/awt/Font
      // 99f8: dup
      // 99f9: ldc_w "Arial"
      // 99fc: bipush 0
      // 99fd: bipush 12
      // 99ff: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9a02: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9a05: aload 0
      // 9a06: aload 0
      // 9a07: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9a0a: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9a0d: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9a10: aload 0
      // 9a11: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9a14: new java/lang/StringBuilder
      // 9a17: dup
      // 9a18: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a1b: ldc_w "( "
      // 9a1e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a21: iload 24
      // 9a23: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9a26: ldc_w " of "
      // 9a29: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a2c: aload 0
      // 9a2d: getfield Lobby.gaclan [Ljava/lang/String;
      // 9a30: iload 15
      // 9a32: aaload
      // 9a33: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a36: ldc_w "  &  "
      // 9a39: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a3c: iload 25
      // 9a3e: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9a41: ldc_w " of "
      // 9a44: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a47: aload 0
      // 9a48: getfield Lobby.gvclan [Ljava/lang/String;
      // 9a4b: iload 15
      // 9a4d: aaload
      // 9a4e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a51: ldc_w " )"
      // 9a54: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a57: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9a5a: sipush 561
      // 9a5d: aload 0
      // 9a5e: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9a61: new java/lang/StringBuilder
      // 9a64: dup
      // 9a65: invokespecial java/lang/StringBuilder.<init> ()V
      // 9a68: ldc_w "( "
      // 9a6b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a6e: iload 24
      // 9a70: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9a73: ldc_w " of "
      // 9a76: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a79: aload 0
      // 9a7a: getfield Lobby.gaclan [Ljava/lang/String;
      // 9a7d: iload 15
      // 9a7f: aaload
      // 9a80: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a83: ldc_w "  &  "
      // 9a86: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a89: iload 25
      // 9a8b: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9a8e: ldc_w " of "
      // 9a91: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a94: aload 0
      // 9a95: getfield Lobby.gvclan [Ljava/lang/String;
      // 9a98: iload 15
      // 9a9a: aaload
      // 9a9b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9a9e: ldc_w " )"
      // 9aa1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9aa4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9aa7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9aaa: bipush 2
      // 9aab: idiv
      // 9aac: isub
      // 9aad: bipush 87
      // 9aaf: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9ab2: aload 0
      // 9ab3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ab6: aload 0
      // 9ab7: getfield Lobby.gaclan [Ljava/lang/String;
      // 9aba: iload 15
      // 9abc: aaload
      // 9abd: sipush 491
      // 9ac0: aload 0
      // 9ac1: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9ac4: aload 0
      // 9ac5: getfield Lobby.gaclan [Ljava/lang/String;
      // 9ac8: iload 15
      // 9aca: aaload
      // 9acb: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9ace: bipush 2
      // 9acf: idiv
      // 9ad0: isub
      // 9ad1: bipush 125
      // 9ad3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9ad6: aload 0
      // 9ad7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ada: aload 0
      // 9adb: getfield Lobby.gvclan [Ljava/lang/String;
      // 9ade: iload 15
      // 9ae0: aaload
      // 9ae1: sipush 631
      // 9ae4: aload 0
      // 9ae5: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9ae8: aload 0
      // 9ae9: getfield Lobby.gvclan [Ljava/lang/String;
      // 9aec: iload 15
      // 9aee: aaload
      // 9aef: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9af2: bipush 2
      // 9af3: idiv
      // 9af4: isub
      // 9af5: bipush 125
      // 9af7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9afa: aload 0
      // 9afb: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9afe: new java/awt/Font
      // 9b01: dup
      // 9b02: ldc_w "Arial"
      // 9b05: bipush 1
      // 9b06: bipush 12
      // 9b08: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9b0b: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9b0e: aload 0
      // 9b0f: aload 0
      // 9b10: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9b13: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9b16: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9b19: ldc_w "War"
      // 9b1c: astore 26
      // 9b1e: aload 0
      // 9b1f: getfield Lobby.gwarb [I
      // 9b22: iload 15
      // 9b24: iaload
      // 9b25: bipush 1
      // 9b26: if_icmpgt 9b2e
      // 9b29: goto 9b38
      // 9b2e: ldc_w "Battle"
      // 9b31: astore 26
      // 9b33: goto 9b38
      // 9b38: aload 0
      // 9b39: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9b3c: new java/lang/StringBuilder
      // 9b3f: dup
      // 9b40: invokespecial java/lang/StringBuilder.<init> ()V
      // 9b43: ldc ""
      // 9b45: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b48: aload 26
      // 9b4a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b4d: ldc_w " Score"
      // 9b50: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b53: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9b56: sipush 561
      // 9b59: aload 0
      // 9b5a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9b5d: new java/lang/StringBuilder
      // 9b60: dup
      // 9b61: invokespecial java/lang/StringBuilder.<init> ()V
      // 9b64: ldc ""
      // 9b66: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b69: aload 26
      // 9b6b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b6e: ldc_w " Score"
      // 9b71: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b74: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9b77: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9b7a: bipush 2
      // 9b7b: idiv
      // 9b7c: isub
      // 9b7d: bipush 107
      // 9b7f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9b82: aload 0
      // 9b83: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9b86: new java/lang/StringBuilder
      // 9b89: dup
      // 9b8a: invokespecial java/lang/StringBuilder.<init> ()V
      // 9b8d: ldc ""
      // 9b8f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9b92: aload 0
      // 9b93: getfield Lobby.gascore [I
      // 9b96: iload 15
      // 9b98: iaload
      // 9b99: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9b9c: ldc ""
      // 9b9e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9ba1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9ba4: sipush 491
      // 9ba7: aload 0
      // 9ba8: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9bab: new java/lang/StringBuilder
      // 9bae: dup
      // 9baf: invokespecial java/lang/StringBuilder.<init> ()V
      // 9bb2: ldc ""
      // 9bb4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9bb7: aload 0
      // 9bb8: getfield Lobby.gascore [I
      // 9bbb: iload 15
      // 9bbd: iaload
      // 9bbe: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9bc1: ldc ""
      // 9bc3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9bc6: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9bc9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9bcc: bipush 2
      // 9bcd: idiv
      // 9bce: isub
      // 9bcf: sipush 139
      // 9bd2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9bd5: aload 0
      // 9bd6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9bd9: new java/lang/StringBuilder
      // 9bdc: dup
      // 9bdd: invokespecial java/lang/StringBuilder.<init> ()V
      // 9be0: ldc ""
      // 9be2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9be5: aload 0
      // 9be6: getfield Lobby.gvscore [I
      // 9be9: iload 15
      // 9beb: iaload
      // 9bec: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9bef: ldc ""
      // 9bf1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9bf4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9bf7: sipush 631
      // 9bfa: aload 0
      // 9bfb: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9bfe: new java/lang/StringBuilder
      // 9c01: dup
      // 9c02: invokespecial java/lang/StringBuilder.<init> ()V
      // 9c05: ldc ""
      // 9c07: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9c0a: aload 0
      // 9c0b: getfield Lobby.gascore [I
      // 9c0e: iload 15
      // 9c10: iaload
      // 9c11: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // 9c14: ldc ""
      // 9c16: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 9c19: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 9c1c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9c1f: bipush 2
      // 9c20: idiv
      // 9c21: isub
      // 9c22: sipush 139
      // 9c25: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9c28: aload 0
      // 9c29: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c2c: sipush 421
      // 9c2f: bipush 111
      // 9c31: sipush 280
      // 9c34: bipush 33
      // 9c36: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 9c39: aload 0
      // 9c3a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c3d: sipush 561
      // 9c40: bipush 111
      // 9c42: sipush 561
      // 9c45: sipush 144
      // 9c48: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // 9c4b: aload 0
      // 9c4c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c4f: new java/awt/Color
      // 9c52: dup
      // 9c53: sipush 255
      // 9c56: sipush 128
      // 9c59: bipush 0
      // 9c5a: invokespecial java/awt/Color.<init> (III)V
      // 9c5d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9c60: aload 0
      // 9c61: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c64: sipush 422
      // 9c67: bipush 112
      // 9c69: sipush 138
      // 9c6c: bipush 31
      // 9c6e: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 9c71: aload 0
      // 9c72: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c75: new java/awt/Color
      // 9c78: dup
      // 9c79: bipush 0
      // 9c7a: sipush 128
      // 9c7d: sipush 255
      // 9c80: invokespecial java/awt/Color.<init> (III)V
      // 9c83: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9c86: aload 0
      // 9c87: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9c8a: sipush 562
      // 9c8d: bipush 112
      // 9c8f: sipush 138
      // 9c92: bipush 31
      // 9c94: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // 9c97: aload 0
      // 9c98: getfield Lobby.pgames [I
      // 9c9b: aload 0
      // 9c9c: getfield Lobby.im I
      // 9c9f: iaload
      // 9ca0: aload 0
      // 9ca1: getfield Lobby.ongame I
      // 9ca4: if_icmpne 9cac
      // 9ca7: goto 9d33
      // 9cac: aload 0
      // 9cad: getfield Lobby.xt LxtGraphics;
      // 9cb0: getfield xtGraphics.clan Ljava/lang/String;
      // 9cb3: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9cb6: aload 0
      // 9cb7: getfield Lobby.gaclan [Ljava/lang/String;
      // 9cba: iload 15
      // 9cbc: aaload
      // 9cbd: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9cc0: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9cc3: ifeq 9ccb
      // 9cc6: goto 9cea
      // 9ccb: aload 0
      // 9ccc: getfield Lobby.xt LxtGraphics;
      // 9ccf: getfield xtGraphics.clan Ljava/lang/String;
      // 9cd2: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9cd5: aload 0
      // 9cd6: getfield Lobby.gvclan [Ljava/lang/String;
      // 9cd9: iload 15
      // 9cdb: aaload
      // 9cdc: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // 9cdf: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9ce2: ifne 9cea
      // 9ce5: goto 9cfd
      // 9cea: aload 0
      // 9ceb: ldc_w "    Join this Game    "
      // 9cee: sipush 561
      // 9cf1: sipush 200
      // 9cf4: bipush 0
      // 9cf5: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 9cf8: goto 9da2
      // 9cfd: aload 0
      // 9cfe: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9d01: new java/awt/Color
      // 9d04: dup
      // 9d05: sipush 128
      // 9d08: bipush 73
      // 9d0a: bipush 0
      // 9d0b: invokespecial java/awt/Color.<init> (III)V
      // 9d0e: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9d11: aload 0
      // 9d12: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9d15: ldc_w "You must be a member of either clan to join."
      // 9d18: sipush 561
      // 9d1b: aload 0
      // 9d1c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9d1f: ldc_w "You must be a member of either clan to join."
      // 9d22: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9d25: bipush 2
      // 9d26: idiv
      // 9d27: isub
      // 9d28: sipush 198
      // 9d2b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9d2e: goto 9da2
      // 9d33: aload 0
      // 9d34: getfield Lobby.gmaker [Ljava/lang/String;
      // 9d37: iload 15
      // 9d39: aaload
      // 9d3a: aload 0
      // 9d3b: getfield Lobby.pnames [Ljava/lang/String;
      // 9d3e: aload 0
      // 9d3f: getfield Lobby.im I
      // 9d42: aaload
      // 9d43: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 9d46: ifne 9d4e
      // 9d49: goto 9d71
      // 9d4e: aload 0
      // 9d4f: getfield Lobby.npls [I
      // 9d52: iload 15
      // 9d54: iaload
      // 9d55: bipush 1
      // 9d56: if_icmpgt 9d5e
      // 9d59: goto 9d71
      // 9d5e: aload 0
      // 9d5f: ldc_w "    Start this Game Now!    "
      // 9d62: sipush 561
      // 9d65: sipush -1000
      // 9d68: bipush 0
      // 9d69: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // 9d6c: goto 9d71
      // 9d71: aload 0
      // 9d72: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9d75: new java/awt/Color
      // 9d78: dup
      // 9d79: bipush 80
      // 9d7b: sipush 128
      // 9d7e: bipush 0
      // 9d7f: invokespecial java/awt/Color.<init> (III)V
      // 9d82: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9d85: aload 0
      // 9d86: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9d89: ldc_w "You have joined this game."
      // 9d8c: sipush 561
      // 9d8f: aload 0
      // 9d90: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9d93: ldc_w "You have joined this game."
      // 9d96: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9d99: bipush 2
      // 9d9a: idiv
      // 9d9b: isub
      // 9d9c: sipush 198
      // 9d9f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9da2: aload 0
      // 9da3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9da6: new java/awt/Font
      // 9da9: dup
      // 9daa: ldc_w "Arial"
      // 9dad: bipush 1
      // 9dae: bipush 12
      // 9db0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9db3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9db6: aload 0
      // 9db7: aload 0
      // 9db8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9dbb: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9dbe: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9dc1: aload 0
      // 9dc2: getfield Lobby.gwtyp [I
      // 9dc5: iload 15
      // 9dc7: iaload
      // 9dc8: bipush 1
      // 9dc9: if_icmpeq 9dd1
      // 9dcc: goto 9e51
      // 9dd1: aload 0
      // 9dd2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9dd5: new java/awt/Color
      // 9dd8: dup
      // 9dd9: bipush 0
      // 9dda: bipush 0
      // 9ddb: bipush 0
      // 9ddc: invokespecial java/awt/Color.<init> (III)V
      // 9ddf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9de2: aload 0
      // 9de3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9de6: ldc_w "This is a normal clan game."
      // 9de9: sipush 561
      // 9dec: aload 0
      // 9ded: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9df0: ldc_w "This is a normal clan game."
      // 9df3: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9df6: bipush 2
      // 9df7: idiv
      // 9df8: isub
      // 9df9: sipush 161
      // 9dfc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9dff: aload 0
      // 9e00: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e03: new java/awt/Font
      // 9e06: dup
      // 9e07: ldc_w "Arial"
      // 9e0a: bipush 0
      // 9e0b: bipush 12
      // 9e0d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9e10: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9e13: aload 0
      // 9e14: aload 0
      // 9e15: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e18: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9e1b: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9e1e: aload 0
      // 9e1f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e22: new java/awt/Color
      // 9e25: dup
      // 9e26: bipush 0
      // 9e27: bipush 0
      // 9e28: bipush 0
      // 9e29: invokespecial java/awt/Color.<init> (III)V
      // 9e2c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9e2f: aload 0
      // 9e30: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e33: ldc_w "Any clan can win by racing or wasting."
      // 9e36: sipush 561
      // 9e39: aload 0
      // 9e3a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9e3d: ldc_w "Any clan can win by racing or wasting."
      // 9e40: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9e43: bipush 2
      // 9e44: idiv
      // 9e45: isub
      // 9e46: sipush 176
      // 9e49: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9e4c: goto 9e51
      // 9e51: aload 0
      // 9e52: getfield Lobby.gwtyp [I
      // 9e55: iload 15
      // 9e57: iaload
      // 9e58: bipush 2
      // 9e59: if_icmpeq 9e61
      // 9e5c: goto 9f14
      // 9e61: aload 0
      // 9e62: getfield Lobby.sflk I
      // 9e65: ifne 9e6d
      // 9e68: goto 9e8d
      // 9e6d: aload 0
      // 9e6e: dup
      // 9e6f: getfield Lobby.sflk I
      // 9e72: bipush 1
      // 9e73: isub
      // 9e74: putfield Lobby.sflk I
      // 9e77: aload 0
      // 9e78: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e7b: new java/awt/Color
      // 9e7e: dup
      // 9e7f: bipush 0
      // 9e80: bipush 0
      // 9e81: bipush 0
      // 9e82: invokespecial java/awt/Color.<init> (III)V
      // 9e85: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9e88: goto 9ea5
      // 9e8d: aload 0
      // 9e8e: bipush 3
      // 9e8f: putfield Lobby.sflk I
      // 9e92: aload 0
      // 9e93: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9e96: new java/awt/Color
      // 9e99: dup
      // 9e9a: bipush 117
      // 9e9c: bipush 67
      // 9e9e: bipush 0
      // 9e9f: invokespecial java/awt/Color.<init> (III)V
      // 9ea2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9ea5: aload 0
      // 9ea6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ea9: ldc_w "This is a racing only game!"
      // 9eac: sipush 561
      // 9eaf: aload 0
      // 9eb0: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9eb3: ldc_w "This is a racing only game!"
      // 9eb6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9eb9: bipush 2
      // 9eba: idiv
      // 9ebb: isub
      // 9ebc: sipush 161
      // 9ebf: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9ec2: aload 0
      // 9ec3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ec6: new java/awt/Font
      // 9ec9: dup
      // 9eca: ldc_w "Arial"
      // 9ecd: bipush 0
      // 9ece: bipush 12
      // 9ed0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9ed3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9ed6: aload 0
      // 9ed7: aload 0
      // 9ed8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9edb: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9ede: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9ee1: aload 0
      // 9ee2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ee5: new java/awt/Color
      // 9ee8: dup
      // 9ee9: bipush 0
      // 9eea: bipush 0
      // 9eeb: bipush 0
      // 9eec: invokespecial java/awt/Color.<init> (III)V
      // 9eef: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9ef2: aload 0
      // 9ef3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9ef6: ldc_w "A clan can only win by racing."
      // 9ef9: sipush 561
      // 9efc: aload 0
      // 9efd: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9f00: ldc_w "A clan can only win by racing."
      // 9f03: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9f06: bipush 2
      // 9f07: idiv
      // 9f08: isub
      // 9f09: sipush 176
      // 9f0c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9f0f: goto 9f14
      // 9f14: aload 0
      // 9f15: getfield Lobby.gwtyp [I
      // 9f18: iload 15
      // 9f1a: iaload
      // 9f1b: bipush 3
      // 9f1c: if_icmpeq 9f24
      // 9f1f: goto 9fd7
      // 9f24: aload 0
      // 9f25: getfield Lobby.sflk I
      // 9f28: ifne 9f30
      // 9f2b: goto 9f50
      // 9f30: aload 0
      // 9f31: dup
      // 9f32: getfield Lobby.sflk I
      // 9f35: bipush 1
      // 9f36: isub
      // 9f37: putfield Lobby.sflk I
      // 9f3a: aload 0
      // 9f3b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9f3e: new java/awt/Color
      // 9f41: dup
      // 9f42: bipush 0
      // 9f43: bipush 0
      // 9f44: bipush 0
      // 9f45: invokespecial java/awt/Color.<init> (III)V
      // 9f48: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9f4b: goto 9f68
      // 9f50: aload 0
      // 9f51: bipush 3
      // 9f52: putfield Lobby.sflk I
      // 9f55: aload 0
      // 9f56: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9f59: new java/awt/Color
      // 9f5c: dup
      // 9f5d: bipush 117
      // 9f5f: bipush 67
      // 9f61: bipush 0
      // 9f62: invokespecial java/awt/Color.<init> (III)V
      // 9f65: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9f68: aload 0
      // 9f69: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9f6c: ldc_w "This is a wasting only game!"
      // 9f6f: sipush 561
      // 9f72: aload 0
      // 9f73: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9f76: ldc_w "This is a wasting only game!"
      // 9f79: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9f7c: bipush 2
      // 9f7d: idiv
      // 9f7e: isub
      // 9f7f: sipush 161
      // 9f82: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9f85: aload 0
      // 9f86: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9f89: new java/awt/Font
      // 9f8c: dup
      // 9f8d: ldc_w "Arial"
      // 9f90: bipush 0
      // 9f91: bipush 12
      // 9f93: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // 9f96: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // 9f99: aload 0
      // 9f9a: aload 0
      // 9f9b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9f9e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // 9fa1: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9fa4: aload 0
      // 9fa5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9fa8: new java/awt/Color
      // 9fab: dup
      // 9fac: bipush 0
      // 9fad: bipush 0
      // 9fae: bipush 0
      // 9faf: invokespecial java/awt/Color.<init> (III)V
      // 9fb2: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // 9fb5: aload 0
      // 9fb6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // 9fb9: ldc_w "A clan can only win by wasting."
      // 9fbc: sipush 561
      // 9fbf: aload 0
      // 9fc0: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // 9fc3: ldc_w "A clan can only win by wasting."
      // 9fc6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // 9fc9: bipush 2
      // 9fca: idiv
      // 9fcb: isub
      // 9fcc: sipush 176
      // 9fcf: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // 9fd2: goto 9fd7
      // 9fd7: aload 0
      // 9fd8: getfield Lobby.gwtyp [I
      // 9fdb: iload 15
      // 9fdd: iaload
      // 9fde: bipush 4
      // 9fdf: if_icmpeq 9fe7
      // 9fe2: goto a0c2
      // 9fe7: aload 0
      // 9fe8: getfield Lobby.sflk I
      // 9feb: ifne 9ff3
      // 9fee: goto a013
      // 9ff3: aload 0
      // 9ff4: dup
      // 9ff5: getfield Lobby.sflk I
      // 9ff8: bipush 1
      // 9ff9: isub
      // 9ffa: putfield Lobby.sflk I
      // 9ffd: aload 0
      // 9ffe: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a001: new java/awt/Color
      // a004: dup
      // a005: bipush 0
      // a006: bipush 0
      // a007: bipush 0
      // a008: invokespecial java/awt/Color.<init> (III)V
      // a00b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a00e: goto a02b
      // a013: aload 0
      // a014: bipush 3
      // a015: putfield Lobby.sflk I
      // a018: aload 0
      // a019: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a01c: new java/awt/Color
      // a01f: dup
      // a020: bipush 117
      // a022: bipush 67
      // a024: bipush 0
      // a025: invokespecial java/awt/Color.<init> (III)V
      // a028: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a02b: aload 0
      // a02c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a02f: ldc_w "This is Racers VS Wasters game!"
      // a032: sipush 561
      // a035: aload 0
      // a036: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a039: ldc_w "This is Racers VS Wasters game!"
      // a03c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a03f: bipush 2
      // a040: idiv
      // a041: isub
      // a042: sipush 161
      // a045: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a048: aload 0
      // a049: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a04c: new java/lang/StringBuilder
      // a04f: dup
      // a050: invokespecial java/lang/StringBuilder.<init> ()V
      // a053: ldc ""
      // a055: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a058: aload 0
      // a059: getfield Lobby.gaclan [Ljava/lang/String;
      // a05c: iload 15
      // a05e: aaload
      // a05f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a062: ldc_w " wastes & "
      // a065: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a068: aload 0
      // a069: getfield Lobby.gvclan [Ljava/lang/String;
      // a06c: iload 15
      // a06e: aaload
      // a06f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a072: ldc_w " races."
      // a075: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a078: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a07b: sipush 561
      // a07e: aload 0
      // a07f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a082: new java/lang/StringBuilder
      // a085: dup
      // a086: invokespecial java/lang/StringBuilder.<init> ()V
      // a089: ldc ""
      // a08b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a08e: aload 0
      // a08f: getfield Lobby.gaclan [Ljava/lang/String;
      // a092: iload 15
      // a094: aaload
      // a095: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a098: ldc_w " wastes & "
      // a09b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a09e: aload 0
      // a09f: getfield Lobby.gvclan [Ljava/lang/String;
      // a0a2: iload 15
      // a0a4: aaload
      // a0a5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a0a8: ldc_w " races."
      // a0ab: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a0ae: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a0b1: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a0b4: bipush 2
      // a0b5: idiv
      // a0b6: isub
      // a0b7: sipush 176
      // a0ba: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a0bd: goto a0c2
      // a0c2: aload 0
      // a0c3: getfield Lobby.gwtyp [I
      // a0c6: iload 15
      // a0c8: iaload
      // a0c9: bipush 5
      // a0ca: if_icmpeq a0d2
      // a0cd: goto a1ad
      // a0d2: aload 0
      // a0d3: getfield Lobby.sflk I
      // a0d6: ifne a0de
      // a0d9: goto a0fe
      // a0de: aload 0
      // a0df: dup
      // a0e0: getfield Lobby.sflk I
      // a0e3: bipush 1
      // a0e4: isub
      // a0e5: putfield Lobby.sflk I
      // a0e8: aload 0
      // a0e9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a0ec: new java/awt/Color
      // a0ef: dup
      // a0f0: bipush 0
      // a0f1: bipush 0
      // a0f2: bipush 0
      // a0f3: invokespecial java/awt/Color.<init> (III)V
      // a0f6: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a0f9: goto a116
      // a0fe: aload 0
      // a0ff: bipush 3
      // a100: putfield Lobby.sflk I
      // a103: aload 0
      // a104: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a107: new java/awt/Color
      // a10a: dup
      // a10b: bipush 117
      // a10d: bipush 67
      // a10f: bipush 0
      // a110: invokespecial java/awt/Color.<init> (III)V
      // a113: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a116: aload 0
      // a117: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a11a: ldc_w "This is Racers VS Wasters game!"
      // a11d: sipush 561
      // a120: aload 0
      // a121: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a124: ldc_w "This is Racers VS Wasters game!"
      // a127: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a12a: bipush 2
      // a12b: idiv
      // a12c: isub
      // a12d: sipush 161
      // a130: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a133: aload 0
      // a134: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a137: new java/lang/StringBuilder
      // a13a: dup
      // a13b: invokespecial java/lang/StringBuilder.<init> ()V
      // a13e: ldc ""
      // a140: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a143: aload 0
      // a144: getfield Lobby.gaclan [Ljava/lang/String;
      // a147: iload 15
      // a149: aaload
      // a14a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a14d: ldc_w " races & "
      // a150: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a153: aload 0
      // a154: getfield Lobby.gvclan [Ljava/lang/String;
      // a157: iload 15
      // a159: aaload
      // a15a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a15d: ldc_w " wastes."
      // a160: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a163: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a166: sipush 561
      // a169: aload 0
      // a16a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a16d: new java/lang/StringBuilder
      // a170: dup
      // a171: invokespecial java/lang/StringBuilder.<init> ()V
      // a174: ldc ""
      // a176: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a179: aload 0
      // a17a: getfield Lobby.gaclan [Ljava/lang/String;
      // a17d: iload 15
      // a17f: aaload
      // a180: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a183: ldc_w " races & "
      // a186: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a189: aload 0
      // a18a: getfield Lobby.gvclan [Ljava/lang/String;
      // a18d: iload 15
      // a18f: aaload
      // a190: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a193: ldc_w " wastes."
      // a196: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a199: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a19c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a19f: bipush 2
      // a1a0: idiv
      // a1a1: isub
      // a1a2: sipush 176
      // a1a5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a1a8: goto a1ad
      // a1ad: goto af5f
      // a1b2: aload 0
      // a1b3: getfield Lobby.prevloaded I
      // a1b6: bipush 1
      // a1b7: if_icmpeq a1bf
      // a1ba: goto ada1
      // a1bf: ldc2_w 80.0
      // a1c2: aload 0
      // a1c3: getfield Lobby.rerr I
      // a1c6: i2f
      // a1c7: f2d
      // a1c8: ldc2_w 1.243
      // a1cb: ddiv
      // a1cc: dadd
      // a1cd: d2i
      // a1ce: istore 23
      // a1d0: iload 23
      // a1d2: sipush 255
      // a1d5: if_icmpgt a1dd
      // a1d8: goto a1e7
      // a1dd: sipush 255
      // a1e0: istore 23
      // a1e2: goto a1e7
      // a1e7: iload 23
      // a1e9: iflt a1f1
      // a1ec: goto a1f9
      // a1f1: bipush 0
      // a1f2: istore 23
      // a1f4: goto a1f9
      // a1f9: ldc2_w 128.0
      // a1fc: aload 0
      // a1fd: getfield Lobby.rerr I
      // a200: i2f
      // a201: f2d
      // a202: ldc2_w 2.428
      // a205: ddiv
      // a206: dadd
      // a207: d2i
      // a208: istore 24
      // a20a: iload 24
      // a20c: sipush 255
      // a20f: if_icmpgt a217
      // a212: goto a221
      // a217: sipush 255
      // a21a: istore 24
      // a21c: goto a221
      // a221: iload 24
      // a223: iflt a22b
      // a226: goto a233
      // a22b: bipush 0
      // a22c: istore 24
      // a22e: goto a233
      // a233: aload 0
      // a234: getfield Lobby.rerr I
      // a237: istore 25
      // a239: iload 25
      // a23b: sipush 255
      // a23e: if_icmpgt a246
      // a241: goto a250
      // a246: sipush 255
      // a249: istore 25
      // a24b: goto a250
      // a250: iload 25
      // a252: iflt a25a
      // a255: goto a262
      // a25a: bipush 0
      // a25b: istore 25
      // a25d: goto a262
      // a262: aload 0
      // a263: getfield Lobby.wait [I
      // a266: iload 15
      // a268: iaload
      // a269: ifeq a271
      // a26c: goto a2bc
      // a271: aload 0
      // a272: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a275: new java/awt/Color
      // a278: dup
      // a279: iload 23
      // a27b: iload 24
      // a27d: iload 25
      // a27f: invokespecial java/awt/Color.<init> (III)V
      // a282: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a285: aload 0
      // a286: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a289: new java/awt/Font
      // a28c: dup
      // a28d: ldc_w "Arial"
      // a290: bipush 1
      // a291: bipush 12
      // a293: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a296: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a299: aload 0
      // a29a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a29d: ldc_w "Live Info!"
      // a2a0: sipush 621
      // a2a3: bipush 51
      // a2a5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a2a8: aload 0
      // a2a9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a2ac: ldc_w "Live Info!"
      // a2af: sipush 451
      // a2b2: bipush 51
      // a2b4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a2b7: goto a2bc
      // a2bc: aload 0
      // a2bd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a2c0: new java/awt/Color
      // a2c3: dup
      // a2c4: bipush 0
      // a2c5: bipush 0
      // a2c6: bipush 0
      // a2c7: invokespecial java/awt/Color.<init> (III)V
      // a2ca: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a2cd: aload 0
      // a2ce: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a2d1: new java/awt/Font
      // a2d4: dup
      // a2d5: ldc_w "Tahoma"
      // a2d8: bipush 1
      // a2d9: bipush 11
      // a2db: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a2de: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a2e1: aload 0
      // a2e2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a2e5: ldc_w "Player       Position   Laps      Power        Damage"
      // a2e8: sipush 439
      // a2eb: bipush 69
      // a2ed: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a2f0: aload 0
      // a2f1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a2f4: aload 0
      // a2f5: sipush 200
      // a2f8: sipush 200
      // a2fb: sipush 200
      // a2fe: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // a301: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a304: aload 0
      // a305: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a308: sipush 489
      // a30b: bipush 61
      // a30d: sipush 489
      // a310: sipush 162
      // a313: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a316: aload 0
      // a317: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a31a: sipush 542
      // a31d: bipush 61
      // a31f: sipush 542
      // a322: sipush 162
      // a325: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a328: aload 0
      // a329: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a32c: sipush 579
      // a32f: bipush 61
      // a331: sipush 579
      // a334: sipush 162
      // a337: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a33a: aload 0
      // a33b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a33e: sipush 641
      // a341: bipush 61
      // a343: sipush 641
      // a346: sipush 162
      // a349: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a34c: aload 0
      // a34d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a350: sipush 422
      // a353: bipush 72
      // a355: sipush 702
      // a358: bipush 72
      // a35a: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a35d: aload 0
      // a35e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a361: sipush 422
      // a364: sipush 163
      // a367: sipush 702
      // a36a: sipush 163
      // a36d: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // a370: bipush 0
      // a371: istore 21
      // a373: bipush 0
      // a374: istore 26
      // a376: iload 26
      // a378: aload 0
      // a379: getfield Lobby.prnpo I
      // a37c: if_icmplt a384
      // a37f: goto a851
      // a384: bipush 0
      // a385: istore 27
      // a387: iload 27
      // a389: aload 0
      // a38a: getfield Lobby.prnpo I
      // a38d: if_icmplt a395
      // a390: goto a849
      // a395: aload 0
      // a396: getfield Lobby.ppos [I
      // a399: iload 27
      // a39b: iaload
      // a39c: iload 26
      // a39e: if_icmpeq a3a6
      // a3a1: goto a841
      // a3a6: aload 0
      // a3a7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a3aa: new java/awt/Font
      // a3ad: dup
      // a3ae: ldc_w "Tahoma"
      // a3b1: bipush 0
      // a3b2: bipush 11
      // a3b4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a3b7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a3ba: aload 0
      // a3bb: aload 0
      // a3bc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a3bf: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a3c2: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // a3c5: aload 0
      // a3c6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a3c9: new java/awt/Color
      // a3cc: dup
      // a3cd: bipush 0
      // a3ce: bipush 44
      // a3d0: bipush 124
      // a3d2: invokespecial java/awt/Color.<init> (III)V
      // a3d5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a3d8: aload 0
      // a3d9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a3dc: aload 0
      // a3dd: getfield Lobby.prnames [Ljava/lang/String;
      // a3e0: iload 27
      // a3e2: aaload
      // a3e3: sipush 455
      // a3e6: aload 0
      // a3e7: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a3ea: aload 0
      // a3eb: getfield Lobby.prnames [Ljava/lang/String;
      // a3ee: iload 27
      // a3f0: aaload
      // a3f1: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a3f4: bipush 2
      // a3f5: idiv
      // a3f6: isub
      // a3f7: bipush 83
      // a3f9: bipush 11
      // a3fb: iload 21
      // a3fd: imul
      // a3fe: iadd
      // a3ff: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a402: aload 0
      // a403: getfield Lobby.pdam [I
      // a406: iload 27
      // a408: iaload
      // a409: bipush 55
      // a40b: if_icmplt a413
      // a40e: goto a69b
      // a413: aload 0
      // a414: getfield Lobby.pdam [I
      // a417: iload 27
      // a419: iaload
      // a41a: bipush -17
      // a41c: if_icmpne a424
      // a41f: goto a69b
      // a424: aload 0
      // a425: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a428: new java/awt/Color
      // a42b: dup
      // a42c: bipush 80
      // a42e: sipush 128
      // a431: bipush 0
      // a432: invokespecial java/awt/Color.<init> (III)V
      // a435: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a438: ldc_w "th"
      // a43b: astore 28
      // a43d: aload 0
      // a43e: getfield Lobby.ppos [I
      // a441: iload 27
      // a443: iaload
      // a444: ifeq a44c
      // a447: goto a456
      // a44c: ldc_w "st"
      // a44f: astore 28
      // a451: goto a456
      // a456: aload 0
      // a457: getfield Lobby.ppos [I
      // a45a: iload 27
      // a45c: iaload
      // a45d: bipush 1
      // a45e: if_icmpeq a466
      // a461: goto a470
      // a466: ldc_w "nd"
      // a469: astore 28
      // a46b: goto a470
      // a470: aload 0
      // a471: getfield Lobby.ppos [I
      // a474: iload 27
      // a476: iaload
      // a477: bipush 2
      // a478: if_icmpeq a480
      // a47b: goto a48a
      // a480: ldc_w "rd"
      // a483: astore 28
      // a485: goto a48a
      // a48a: aload 0
      // a48b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a48e: new java/lang/StringBuilder
      // a491: dup
      // a492: invokespecial java/lang/StringBuilder.<init> ()V
      // a495: ldc ""
      // a497: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a49a: aload 0
      // a49b: getfield Lobby.ppos [I
      // a49e: iload 27
      // a4a0: iaload
      // a4a1: bipush 1
      // a4a2: iadd
      // a4a3: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a4a6: ldc ""
      // a4a8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a4ab: aload 28
      // a4ad: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a4b0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a4b3: sipush 515
      // a4b6: aload 0
      // a4b7: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a4ba: new java/lang/StringBuilder
      // a4bd: dup
      // a4be: invokespecial java/lang/StringBuilder.<init> ()V
      // a4c1: ldc ""
      // a4c3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a4c6: aload 0
      // a4c7: getfield Lobby.ppos [I
      // a4ca: iload 27
      // a4cc: iaload
      // a4cd: bipush 1
      // a4ce: iadd
      // a4cf: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a4d2: ldc ""
      // a4d4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a4d7: aload 28
      // a4d9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a4dc: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a4df: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a4e2: bipush 2
      // a4e3: idiv
      // a4e4: isub
      // a4e5: bipush 83
      // a4e7: bipush 11
      // a4e9: iload 21
      // a4eb: imul
      // a4ec: iadd
      // a4ed: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a4f0: aload 0
      // a4f1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a4f4: new java/awt/Color
      // a4f7: dup
      // a4f8: sipush 128
      // a4fb: bipush 73
      // a4fd: bipush 0
      // a4fe: invokespecial java/awt/Color.<init> (III)V
      // a501: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a504: aload 0
      // a505: getfield Lobby.plap [I
      // a508: iload 27
      // a50a: iaload
      // a50b: aload 0
      // a50c: getfield Lobby.gnlaps [I
      // a50f: iload 15
      // a511: iaload
      // a512: bipush 1
      // a513: isub
      // a514: if_icmpgt a51c
      // a517: goto a531
      // a51c: aload 0
      // a51d: getfield Lobby.plap [I
      // a520: iload 27
      // a522: aload 0
      // a523: getfield Lobby.gnlaps [I
      // a526: iload 15
      // a528: iaload
      // a529: bipush 1
      // a52a: isub
      // a52b: iastore
      // a52c: goto a531
      // a531: aload 0
      // a532: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a535: new java/lang/StringBuilder
      // a538: dup
      // a539: invokespecial java/lang/StringBuilder.<init> ()V
      // a53c: ldc ""
      // a53e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a541: aload 0
      // a542: getfield Lobby.plap [I
      // a545: iload 27
      // a547: iaload
      // a548: bipush 1
      // a549: iadd
      // a54a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a54d: ldc_w " / "
      // a550: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a553: aload 0
      // a554: getfield Lobby.gnlaps [I
      // a557: iload 15
      // a559: iaload
      // a55a: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a55d: ldc ""
      // a55f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a562: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a565: sipush 560
      // a568: aload 0
      // a569: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a56c: new java/lang/StringBuilder
      // a56f: dup
      // a570: invokespecial java/lang/StringBuilder.<init> ()V
      // a573: ldc ""
      // a575: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a578: aload 0
      // a579: getfield Lobby.plap [I
      // a57c: iload 27
      // a57e: iaload
      // a57f: bipush 1
      // a580: iadd
      // a581: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a584: ldc_w " / "
      // a587: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a58a: aload 0
      // a58b: getfield Lobby.gnlaps [I
      // a58e: iload 15
      // a590: iaload
      // a591: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // a594: ldc ""
      // a596: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a599: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a59c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a59f: bipush 2
      // a5a0: idiv
      // a5a1: isub
      // a5a2: bipush 83
      // a5a4: bipush 11
      // a5a6: iload 21
      // a5a8: imul
      // a5a9: iadd
      // a5aa: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a5ad: aload 0
      // a5ae: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a5b1: new java/awt/Color
      // a5b4: dup
      // a5b5: bipush 0
      // a5b6: sipush 128
      // a5b9: sipush 255
      // a5bc: invokespecial java/awt/Color.<init> (III)V
      // a5bf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a5c2: aload 0
      // a5c3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a5c6: sipush 582
      // a5c9: bipush 76
      // a5cb: bipush 11
      // a5cd: iload 21
      // a5cf: imul
      // a5d0: iadd
      // a5d1: bipush 56
      // a5d3: bipush 6
      // a5d5: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // a5d8: aload 0
      // a5d9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a5dc: sipush 583
      // a5df: bipush 79
      // a5e1: bipush 11
      // a5e3: iload 21
      // a5e5: imul
      // a5e6: iadd
      // a5e7: aload 0
      // a5e8: getfield Lobby.ppow [I
      // a5eb: iload 27
      // a5ed: iaload
      // a5ee: bipush 3
      // a5ef: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a5f2: aload 0
      // a5f3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a5f6: new java/awt/Color
      // a5f9: dup
      // a5fa: sipush 128
      // a5fd: sipush 210
      // a600: sipush 255
      // a603: invokespecial java/awt/Color.<init> (III)V
      // a606: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a609: aload 0
      // a60a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a60d: sipush 583
      // a610: bipush 77
      // a612: bipush 11
      // a614: iload 21
      // a616: imul
      // a617: iadd
      // a618: aload 0
      // a619: getfield Lobby.ppow [I
      // a61c: iload 27
      // a61e: iaload
      // a61f: bipush 2
      // a620: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a623: aload 0
      // a624: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a627: new java/awt/Color
      // a62a: dup
      // a62b: sipush 255
      // a62e: bipush 0
      // a62f: bipush 0
      // a630: invokespecial java/awt/Color.<init> (III)V
      // a633: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a636: aload 0
      // a637: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a63a: sipush 645
      // a63d: bipush 76
      // a63f: bipush 11
      // a641: iload 21
      // a643: imul
      // a644: iadd
      // a645: bipush 56
      // a647: bipush 6
      // a649: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // a64c: aload 0
      // a64d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a650: sipush 646
      // a653: bipush 79
      // a655: bipush 11
      // a657: iload 21
      // a659: imul
      // a65a: iadd
      // a65b: aload 0
      // a65c: getfield Lobby.pdam [I
      // a65f: iload 27
      // a661: iaload
      // a662: bipush 3
      // a663: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a666: aload 0
      // a667: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a66a: new java/awt/Color
      // a66d: dup
      // a66e: sipush 255
      // a671: sipush 155
      // a674: bipush 64
      // a676: invokespecial java/awt/Color.<init> (III)V
      // a679: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a67c: aload 0
      // a67d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a680: sipush 646
      // a683: bipush 77
      // a685: bipush 11
      // a687: iload 21
      // a689: imul
      // a68a: iadd
      // a68b: aload 0
      // a68c: getfield Lobby.pdam [I
      // a68f: iload 27
      // a691: iaload
      // a692: bipush 2
      // a693: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a696: goto a839
      // a69b: ldc2_w 85.0
      // a69e: aload 0
      // a69f: getfield Lobby.rerr I
      // a6a2: bipush 2
      // a6a3: imul
      // a6a4: i2f
      // a6a5: f2d
      // a6a6: ldc2_w 1.5
      // a6a9: ddiv
      // a6aa: dadd
      // a6ab: d2i
      // a6ac: istore 23
      // a6ae: iload 23
      // a6b0: sipush 255
      // a6b3: if_icmpgt a6bb
      // a6b6: goto a6c5
      // a6bb: sipush 255
      // a6be: istore 23
      // a6c0: goto a6c5
      // a6c5: iload 23
      // a6c7: iflt a6cf
      // a6ca: goto a6d7
      // a6cf: bipush 0
      // a6d0: istore 23
      // a6d2: goto a6d7
      // a6d7: aload 0
      // a6d8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a6db: aload 0
      // a6dc: iload 23
      // a6de: iload 23
      // a6e0: iload 23
      // a6e2: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // a6e5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a6e8: aload 0
      // a6e9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a6ec: sipush 490
      // a6ef: bipush 75
      // a6f1: bipush 11
      // a6f3: iload 21
      // a6f5: imul
      // a6f6: iadd
      // a6f7: sipush 213
      // a6fa: bipush 9
      // a6fc: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // a6ff: aload 0
      // a700: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a703: new java/awt/Font
      // a706: dup
      // a707: ldc_w "Tahoma"
      // a70a: bipush 1
      // a70b: bipush 11
      // a70d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a710: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a713: aload 0
      // a714: aload 0
      // a715: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a718: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a71b: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // a71e: sipush 255
      // a721: aload 0
      // a722: getfield Lobby.rerr I
      // a725: bipush 2
      // a726: imul
      // a727: isub
      // a728: istore 23
      // a72a: iload 23
      // a72c: sipush 255
      // a72f: if_icmpgt a737
      // a732: goto a741
      // a737: sipush 255
      // a73a: istore 23
      // a73c: goto a741
      // a741: iload 23
      // a743: iflt a74b
      // a746: goto a753
      // a74b: bipush 0
      // a74c: istore 23
      // a74e: goto a753
      // a753: ldc2_w 155.0
      // a756: aload 0
      // a757: getfield Lobby.rerr I
      // a75a: bipush 2
      // a75b: imul
      // a75c: i2f
      // a75d: f2d
      // a75e: ldc2_w 1.645
      // a761: ddiv
      // a762: dsub
      // a763: d2i
      // a764: istore 24
      // a766: iload 24
      // a768: sipush 255
      // a76b: if_icmpgt a773
      // a76e: goto a77d
      // a773: sipush 255
      // a776: istore 24
      // a778: goto a77d
      // a77d: iload 24
      // a77f: iflt a787
      // a782: goto a78f
      // a787: bipush 0
      // a788: istore 24
      // a78a: goto a78f
      // a78f: ldc2_w 64.0
      // a792: aload 0
      // a793: getfield Lobby.rerr I
      // a796: bipush 2
      // a797: imul
      // a798: i2f
      // a799: f2d
      // a79a: ldc2_w 3.984
      // a79d: ddiv
      // a79e: dsub
      // a79f: d2i
      // a7a0: istore 25
      // a7a2: iload 25
      // a7a4: sipush 255
      // a7a7: if_icmpgt a7af
      // a7aa: goto a7b9
      // a7af: sipush 255
      // a7b2: istore 25
      // a7b4: goto a7b9
      // a7b9: iload 25
      // a7bb: iflt a7c3
      // a7be: goto a7cb
      // a7c3: bipush 0
      // a7c4: istore 25
      // a7c6: goto a7cb
      // a7cb: aload 0
      // a7cc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a7cf: new java/awt/Color
      // a7d2: dup
      // a7d3: iload 23
      // a7d5: iload 24
      // a7d7: iload 25
      // a7d9: invokespecial java/awt/Color.<init> (III)V
      // a7dc: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a7df: aload 0
      // a7e0: getfield Lobby.pdam [I
      // a7e3: iload 27
      // a7e5: iaload
      // a7e6: bipush -17
      // a7e8: if_icmpne a7f0
      // a7eb: goto a817
      // a7f0: aload 0
      // a7f1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a7f4: ldc_w "=   =   =   =    W A S T E D    =   =   =   ="
      // a7f7: sipush 597
      // a7fa: aload 0
      // a7fb: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a7fe: ldc_w "=   =   =   =    W A S T E D    =   =   =   ="
      // a801: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a804: bipush 2
      // a805: idiv
      // a806: isub
      // a807: bipush 84
      // a809: bipush 11
      // a80b: iload 21
      // a80d: imul
      // a80e: iadd
      // a80f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a812: goto a839
      // a817: aload 0
      // a818: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a81b: ldc_w "=   =   =   DISCONNECTED   =   =   ="
      // a81e: sipush 597
      // a821: aload 0
      // a822: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a825: ldc_w "=   =   =   DISCONNECTED   =   =   ="
      // a828: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a82b: bipush 2
      // a82c: idiv
      // a82d: isub
      // a82e: bipush 84
      // a830: bipush 11
      // a832: iload 21
      // a834: imul
      // a835: iadd
      // a836: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a839: iinc 21 1
      // a83c: goto a841
      // a841: iinc 27 1
      // a844: goto a387
      // a849: iinc 26 1
      // a84c: goto a376
      // a851: aload 0
      // a852: getfield Lobby.wait [I
      // a855: iload 15
      // a857: iaload
      // a858: ifeq a860
      // a85b: goto a873
      // a860: aload 0
      // a861: ldc_w "    Watch Live Now!    "
      // a864: sipush 561
      // a867: sipush 196
      // a86a: bipush 0
      // a86b: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // a86e: goto ad92
      // a873: aload 0
      // a874: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a877: new java/awt/Color
      // a87a: dup
      // a87b: bipush 0
      // a87c: bipush 0
      // a87d: bipush 0
      // a87e: invokespecial java/awt/Color.<init> (III)V
      // a881: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a884: aload 0
      // a885: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a888: new java/awt/Font
      // a88b: dup
      // a88c: ldc_w "Arial"
      // a88f: bipush 1
      // a890: bipush 12
      // a892: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // a895: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // a898: aload 0
      // a899: aload 0
      // a89a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a89d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // a8a0: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // a8a3: sipush 186
      // a8a6: istore 26
      // a8a8: aload 0
      // a8a9: getfield Lobby.lapsname Ljava/lang/String;
      // a8ac: ldc ""
      // a8ae: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a8b1: ifeq a8b9
      // a8b4: goto a8e5
      // a8b9: aload 0
      // a8ba: getfield Lobby.wastename Ljava/lang/String;
      // a8bd: ldc ""
      // a8bf: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a8c2: ifeq a8ca
      // a8c5: goto a8e5
      // a8ca: aload 0
      // a8cb: getfield Lobby.stuntname Ljava/lang/String;
      // a8ce: ldc ""
      // a8d0: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a8d3: ifeq a8db
      // a8d6: goto a8e5
      // a8db: sipush 183
      // a8de: istore 26
      // a8e0: goto a8e5
      // a8e5: aload 0
      // a8e6: getfield Lobby.gwarb [I
      // a8e9: iload 15
      // a8eb: iaload
      // a8ec: ifeq a8f4
      // a8ef: goto abe9
      // a8f4: ldc ""
      // a8f6: astore 27
      // a8f8: bipush 0
      // a8f9: istore 28
      // a8fb: iload 28
      // a8fd: aload 0
      // a8fe: getfield Lobby.prnpo I
      // a901: if_icmplt a909
      // a904: goto a92e
      // a909: aload 0
      // a90a: getfield Lobby.ppos [I
      // a90d: iload 28
      // a90f: iaload
      // a910: ifeq a918
      // a913: goto a926
      // a918: aload 0
      // a919: getfield Lobby.prnames [Ljava/lang/String;
      // a91c: iload 28
      // a91e: aaload
      // a91f: astore 27
      // a921: goto a92e
      // a926: iinc 28 1
      // a929: goto a8fb
      // a92e: aload 27
      // a930: ldc ""
      // a932: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // a935: ifne a93d
      // a938: goto a95e
      // a93d: aload 0
      // a93e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a941: ldc_w "Game Finished!    Nobody Won!"
      // a944: sipush 561
      // a947: aload 0
      // a948: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a94b: ldc_w "Game Finished!    Nobody Won!"
      // a94e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a951: bipush 2
      // a952: idiv
      // a953: isub
      // a954: iload 26
      // a956: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a959: goto aa32
      // a95e: aload 0
      // a95f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a962: new java/lang/StringBuilder
      // a965: dup
      // a966: invokespecial java/lang/StringBuilder.<init> ()V
      // a969: ldc_w "Game Finished!    Winner:  "
      // a96c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a96f: aload 27
      // a971: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a974: ldc ""
      // a976: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a979: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a97c: sipush 561
      // a97f: aload 0
      // a980: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // a983: new java/lang/StringBuilder
      // a986: dup
      // a987: invokespecial java/lang/StringBuilder.<init> ()V
      // a98a: ldc_w "Game Finished!    Winner:  "
      // a98d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a990: aload 27
      // a992: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a995: ldc ""
      // a997: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a99a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a99d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // a9a0: bipush 2
      // a9a1: idiv
      // a9a2: isub
      // a9a3: iload 26
      // a9a5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // a9a8: aload 0
      // a9a9: getfield Lobby.nflk I
      // a9ac: ifeq a9b4
      // a9af: goto a9d4
      // a9b4: aload 0
      // a9b5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a9b8: new java/awt/Color
      // a9bb: dup
      // a9bc: sipush 255
      // a9bf: sipush 176
      // a9c2: bipush 67
      // a9c4: invokespecial java/awt/Color.<init> (III)V
      // a9c7: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // a9ca: aload 0
      // a9cb: bipush 3
      // a9cc: putfield Lobby.nflk I
      // a9cf: goto a9de
      // a9d4: aload 0
      // a9d5: dup
      // a9d6: getfield Lobby.nflk I
      // a9d9: bipush 1
      // a9da: isub
      // a9db: putfield Lobby.nflk I
      // a9de: aload 0
      // a9df: getfield Lobby.rd Ljava/awt/Graphics2D;
      // a9e2: new java/lang/StringBuilder
      // a9e5: dup
      // a9e6: invokespecial java/lang/StringBuilder.<init> ()V
      // a9e9: ldc ""
      // a9eb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a9ee: aload 27
      // a9f0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a9f3: ldc ""
      // a9f5: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // a9f8: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // a9fb: sipush 561
      // a9fe: aload 0
      // a9ff: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // aa02: new java/lang/StringBuilder
      // aa05: dup
      // aa06: invokespecial java/lang/StringBuilder.<init> ()V
      // aa09: ldc_w "Game Finished!    Winner:  "
      // aa0c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa0f: aload 27
      // aa11: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa14: ldc ""
      // aa16: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa19: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // aa1c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // aa1f: bipush 2
      // aa20: idiv
      // aa21: isub
      // aa22: aload 0
      // aa23: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // aa26: ldc_w "Game Finished!    Winner:  "
      // aa29: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // aa2c: iadd
      // aa2d: iload 26
      // aa2f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // aa32: aload 0
      // aa33: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aa36: new java/awt/Color
      // aa39: dup
      // aa3a: bipush 0
      // aa3b: bipush 0
      // aa3c: bipush 0
      // aa3d: invokespecial java/awt/Color.<init> (III)V
      // aa40: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // aa43: aload 0
      // aa44: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aa47: new java/awt/Font
      // aa4a: dup
      // aa4b: ldc_w "Arial"
      // aa4e: bipush 0
      // aa4f: bipush 11
      // aa51: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // aa54: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // aa57: aload 0
      // aa58: aload 0
      // aa59: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aa5c: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // aa5f: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // aa62: ldc_w "    "
      // aa65: astore 28
      // aa67: bipush 0
      // aa68: istore 29
      // aa6a: aload 0
      // aa6b: getfield Lobby.lapsname Ljava/lang/String;
      // aa6e: ldc ""
      // aa70: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // aa73: ifeq aa7b
      // aa76: goto aaa7
      // aa7b: new java/lang/StringBuilder
      // aa7e: dup
      // aa7f: invokespecial java/lang/StringBuilder.<init> ()V
      // aa82: aload 28
      // aa84: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa87: ldc_w "Fastest lap: "
      // aa8a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa8d: aload 0
      // aa8e: getfield Lobby.lapsname Ljava/lang/String;
      // aa91: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa94: ldc_w "    "
      // aa97: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aa9a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // aa9d: astore 28
      // aa9f: iinc 29 1
      // aaa2: goto aaa7
      // aaa7: aload 0
      // aaa8: getfield Lobby.wastename Ljava/lang/String;
      // aaab: ldc ""
      // aaad: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // aab0: ifeq aab8
      // aab3: goto aae4
      // aab8: new java/lang/StringBuilder
      // aabb: dup
      // aabc: invokespecial java/lang/StringBuilder.<init> ()V
      // aabf: aload 28
      // aac1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aac4: ldc_w "Deadliest waster: "
      // aac7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aaca: aload 0
      // aacb: getfield Lobby.wastename Ljava/lang/String;
      // aace: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aad1: ldc_w "    "
      // aad4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aad7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // aada: astore 28
      // aadc: iinc 29 1
      // aadf: goto aae4
      // aae4: iload 29
      // aae6: bipush 2
      // aae7: if_icmpeq aaef
      // aaea: goto ab8f
      // aaef: aload 0
      // aaf0: getfield Lobby.stuntname Ljava/lang/String;
      // aaf3: ldc ""
      // aaf5: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // aaf8: ifeq ab00
      // aafb: goto ab6f
      // ab00: aload 0
      // ab01: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ab04: aload 28
      // ab06: sipush 561
      // ab09: aload 0
      // ab0a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ab0d: aload 28
      // ab0f: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ab12: bipush 2
      // ab13: idiv
      // ab14: isub
      // ab15: sipush 199
      // ab18: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ab1b: aload 0
      // ab1c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ab1f: new java/lang/StringBuilder
      // ab22: dup
      // ab23: invokespecial java/lang/StringBuilder.<init> ()V
      // ab26: ldc_w "Best stunt: "
      // ab29: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab2c: aload 0
      // ab2d: getfield Lobby.stuntname Ljava/lang/String;
      // ab30: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab33: ldc ""
      // ab35: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab38: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ab3b: sipush 561
      // ab3e: aload 0
      // ab3f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ab42: new java/lang/StringBuilder
      // ab45: dup
      // ab46: invokespecial java/lang/StringBuilder.<init> ()V
      // ab49: ldc_w "Best stunt: "
      // ab4c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab4f: aload 0
      // ab50: getfield Lobby.stuntname Ljava/lang/String;
      // ab53: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab56: ldc ""
      // ab58: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ab5b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ab5e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ab61: bipush 2
      // ab62: idiv
      // ab63: isub
      // ab64: sipush 213
      // ab67: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ab6a: goto abe4
      // ab6f: aload 0
      // ab70: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ab73: aload 28
      // ab75: sipush 561
      // ab78: aload 0
      // ab79: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ab7c: aload 28
      // ab7e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ab81: bipush 2
      // ab82: idiv
      // ab83: isub
      // ab84: sipush 206
      // ab87: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ab8a: goto abe4
      // ab8f: aload 0
      // ab90: getfield Lobby.stuntname Ljava/lang/String;
      // ab93: ldc ""
      // ab95: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // ab98: ifeq aba0
      // ab9b: goto abc9
      // aba0: new java/lang/StringBuilder
      // aba3: dup
      // aba4: invokespecial java/lang/StringBuilder.<init> ()V
      // aba7: aload 28
      // aba9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // abac: ldc_w "Best stunt: "
      // abaf: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // abb2: aload 0
      // abb3: getfield Lobby.stuntname Ljava/lang/String;
      // abb6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // abb9: ldc_w "    "
      // abbc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // abbf: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // abc2: astore 28
      // abc4: goto abc9
      // abc9: aload 0
      // abca: getfield Lobby.rd Ljava/awt/Graphics2D;
      // abcd: aload 28
      // abcf: sipush 561
      // abd2: aload 0
      // abd3: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // abd6: aload 28
      // abd8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // abdb: bipush 2
      // abdc: idiv
      // abdd: isub
      // abde: sipush 206
      // abe1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // abe4: goto ad92
      // abe9: aload 19
      // abeb: ldc ""
      // abed: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // abf0: ifne abf8
      // abf3: goto ad41
      // abf8: aload 18
      // abfa: ldc ""
      // abfc: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // abff: ifne ac07
      // ac02: goto ac63
      // ac07: aload 0
      // ac08: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ac0b: ldc_w "Game Finished!    Nobody Won!"
      // ac0e: sipush 561
      // ac11: aload 0
      // ac12: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ac15: ldc_w "Game Finished!    Nobody Won!"
      // ac18: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ac1b: bipush 2
      // ac1c: idiv
      // ac1d: isub
      // ac1e: sipush 186
      // ac21: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ac24: aload 0
      // ac25: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ac28: new java/awt/Font
      // ac2b: dup
      // ac2c: ldc_w "Arial"
      // ac2f: bipush 1
      // ac30: bipush 11
      // ac32: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // ac35: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // ac38: aload 0
      // ac39: aload 0
      // ac3a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ac3d: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // ac40: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // ac43: aload 0
      // ac44: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ac47: aload 20
      // ac49: sipush 561
      // ac4c: aload 0
      // ac4d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ac50: aload 20
      // ac52: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ac55: bipush 2
      // ac56: idiv
      // ac57: isub
      // ac58: sipush 206
      // ac5b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ac5e: goto ad92
      // ac63: aload 0
      // ac64: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ac67: new java/lang/StringBuilder
      // ac6a: dup
      // ac6b: invokespecial java/lang/StringBuilder.<init> ()V
      // ac6e: ldc_w "Game Finished!    "
      // ac71: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ac74: aload 18
      // ac76: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ac79: ldc_w "  Wins!"
      // ac7c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ac7f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ac82: sipush 561
      // ac85: aload 0
      // ac86: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ac89: new java/lang/StringBuilder
      // ac8c: dup
      // ac8d: invokespecial java/lang/StringBuilder.<init> ()V
      // ac90: ldc_w "Game Finished!    "
      // ac93: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ac96: aload 18
      // ac98: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ac9b: ldc_w "  Wins!"
      // ac9e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // aca1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // aca4: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // aca7: bipush 2
      // aca8: idiv
      // aca9: isub
      // acaa: sipush 196
      // acad: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // acb0: aload 0
      // acb1: getfield Lobby.nflk I
      // acb4: ifeq acbc
      // acb7: goto acdc
      // acbc: aload 0
      // acbd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // acc0: new java/awt/Color
      // acc3: dup
      // acc4: sipush 255
      // acc7: sipush 176
      // acca: bipush 67
      // accc: invokespecial java/awt/Color.<init> (III)V
      // accf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // acd2: aload 0
      // acd3: bipush 3
      // acd4: putfield Lobby.nflk I
      // acd7: goto ace6
      // acdc: aload 0
      // acdd: dup
      // acde: getfield Lobby.nflk I
      // ace1: bipush 1
      // ace2: isub
      // ace3: putfield Lobby.nflk I
      // ace6: aload 0
      // ace7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // acea: new java/lang/StringBuilder
      // aced: dup
      // acee: invokespecial java/lang/StringBuilder.<init> ()V
      // acf1: ldc ""
      // acf3: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // acf6: aload 18
      // acf8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // acfb: ldc ""
      // acfd: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ad00: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ad03: sipush 561
      // ad06: aload 0
      // ad07: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ad0a: new java/lang/StringBuilder
      // ad0d: dup
      // ad0e: invokespecial java/lang/StringBuilder.<init> ()V
      // ad11: ldc_w "Game Finished!    "
      // ad14: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ad17: aload 18
      // ad19: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ad1c: ldc_w "  Wins!"
      // ad1f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ad22: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ad25: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ad28: bipush 2
      // ad29: idiv
      // ad2a: isub
      // ad2b: aload 0
      // ad2c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ad2f: ldc_w "Game Finished!    "
      // ad32: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ad35: iadd
      // ad36: sipush 196
      // ad39: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ad3c: goto ad92
      // ad41: aload 0
      // ad42: getfield Lobby.nflk I
      // ad45: ifeq ad4d
      // ad48: goto ad6d
      // ad4d: aload 0
      // ad4e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ad51: new java/awt/Color
      // ad54: dup
      // ad55: sipush 255
      // ad58: sipush 176
      // ad5b: bipush 67
      // ad5d: invokespecial java/awt/Color.<init> (III)V
      // ad60: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // ad63: aload 0
      // ad64: bipush 3
      // ad65: putfield Lobby.nflk I
      // ad68: goto ad77
      // ad6d: aload 0
      // ad6e: dup
      // ad6f: getfield Lobby.nflk I
      // ad72: bipush 1
      // ad73: isub
      // ad74: putfield Lobby.nflk I
      // ad77: aload 0
      // ad78: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ad7b: aload 19
      // ad7d: sipush 561
      // ad80: aload 0
      // ad81: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ad84: aload 19
      // ad86: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ad89: bipush 2
      // ad8a: idiv
      // ad8b: isub
      // ad8c: sipush 196
      // ad8f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ad92: aload 0
      // ad93: dup
      // ad94: getfield Lobby.rerr I
      // ad97: bipush 3
      // ad98: iadd
      // ad99: putfield Lobby.rerr I
      // ad9c: goto af5f
      // ada1: aload 0
      // ada2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ada5: new java/awt/Color
      // ada8: dup
      // ada9: bipush 0
      // adaa: bipush 0
      // adab: bipush 0
      // adac: invokespecial java/awt/Color.<init> (III)V
      // adaf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // adb2: aload 0
      // adb3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // adb6: new java/awt/Font
      // adb9: dup
      // adba: ldc_w "Arial"
      // adbd: bipush 1
      // adbe: bipush 12
      // adc0: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // adc3: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // adc6: aload 0
      // adc7: aload 0
      // adc8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // adcb: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // adce: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // add1: aload 0
      // add2: getfield Lobby.prevloaded I
      // add5: bipush -1
      // add6: if_icmpeq adde
      // add9: goto ae75
      // adde: aload 0
      // addf: getfield Lobby.xt LxtGraphics;
      // ade2: getfield xtGraphics.lan Z
      // ade5: ifeq aded
      // ade8: goto ae0f
      // aded: aload 0
      // adee: getfield Lobby.rd Ljava/awt/Graphics2D;
      // adf1: ldc_w "Loading Info..."
      // adf4: sipush 561
      // adf7: aload 0
      // adf8: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // adfb: ldc_w "Loading Info..."
      // adfe: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ae01: bipush 2
      // ae02: idiv
      // ae03: isub
      // ae04: sipush 134
      // ae07: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ae0a: goto af5f
      // ae0f: aload 0
      // ae10: getfield Lobby.pgames [I
      // ae13: aload 0
      // ae14: getfield Lobby.im I
      // ae17: iaload
      // ae18: aload 0
      // ae19: getfield Lobby.ongame I
      // ae1c: if_icmpeq ae24
      // ae1f: goto ae46
      // ae24: aload 0
      // ae25: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ae28: ldc_w "About to Start..."
      // ae2b: sipush 561
      // ae2e: aload 0
      // ae2f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ae32: ldc_w "About to Start..."
      // ae35: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ae38: bipush 2
      // ae39: idiv
      // ae3a: isub
      // ae3b: sipush 134
      // ae3e: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ae41: goto af5f
      // ae46: aload 0
      // ae47: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ae4a: ldc_w "Game Started"
      // ae4d: sipush 561
      // ae50: aload 0
      // ae51: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ae54: ldc_w "Game Started"
      // ae57: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ae5a: bipush 2
      // ae5b: idiv
      // ae5c: isub
      // ae5d: bipush 117
      // ae5f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ae62: aload 0
      // ae63: ldc_w "    Watch this Game    "
      // ae66: sipush 561
      // ae69: sipush 154
      // ae6c: bipush 0
      // ae6d: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // ae70: goto af5f
      // ae75: aload 0
      // ae76: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ae79: ldc_w "About to Start..."
      // ae7c: sipush 561
      // ae7f: aload 0
      // ae80: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ae83: ldc_w "About to Start..."
      // ae86: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ae89: bipush 2
      // ae8a: idiv
      // ae8b: isub
      // ae8c: sipush 134
      // ae8f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ae92: goto af5f
      // ae97: aload 0
      // ae98: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ae9b: new java/awt/Color
      // ae9e: dup
      // ae9f: bipush 0
      // aea0: bipush 0
      // aea1: bipush 0
      // aea2: invokespecial java/awt/Color.<init> (III)V
      // aea5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // aea8: aload 0
      // aea9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aeac: new java/awt/Font
      // aeaf: dup
      // aeb0: ldc_w "Arial"
      // aeb3: bipush 1
      // aeb4: bipush 12
      // aeb6: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // aeb9: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // aebc: aload 0
      // aebd: aload 0
      // aebe: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aec1: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // aec4: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // aec7: aload 0
      // aec8: getfield Lobby.conon I
      // aecb: bipush 2
      // aecc: if_icmpeq aed4
      // aecf: goto aef5
      // aed4: aload 0
      // aed5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // aed8: ldc_w "Starting Game Now!"
      // aedb: sipush 561
      // aede: aload 0
      // aedf: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // aee2: ldc_w "Starting Game Now!"
      // aee5: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // aee8: bipush 2
      // aee9: idiv
      // aeea: isub
      // aeeb: bipush 124
      // aeed: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // aef0: goto aef5
      // aef5: aload 0
      // aef6: getfield Lobby.conon I
      // aef9: bipush 3
      // aefa: if_icmpeq af02
      // aefd: goto af23
      // af02: aload 0
      // af03: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af06: ldc_w "Opening Game Now!"
      // af09: sipush 561
      // af0c: aload 0
      // af0d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // af10: ldc_w "Opening Game Now!"
      // af13: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // af16: bipush 2
      // af17: idiv
      // af18: isub
      // af19: bipush 124
      // af1b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // af1e: goto af23
      // af23: aload 0
      // af24: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af27: new java/awt/Font
      // af2a: dup
      // af2b: ldc_w "Arial"
      // af2e: bipush 0
      // af2f: bipush 12
      // af31: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // af34: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // af37: aload 0
      // af38: aload 0
      // af39: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af3c: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // af3f: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // af42: aload 0
      // af43: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af46: ldc_w "Please Wait..."
      // af49: sipush 561
      // af4c: aload 0
      // af4d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // af50: ldc_w "Please Wait..."
      // af53: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // af56: bipush 2
      // af57: idiv
      // af58: isub
      // af59: sipush 154
      // af5c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // af5f: aload 0
      // af60: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af63: aload 0
      // af64: sipush 200
      // af67: sipush 200
      // af6a: sipush 200
      // af6d: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // af70: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // af73: aload 0
      // af74: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af77: sipush 415
      // af7a: sipush 222
      // af7d: sipush 293
      // af80: bipush 40
      // af82: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // af85: aload 0
      // af86: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af89: aload 0
      // af8a: getfield Lobby.xt LxtGraphics;
      // af8d: getfield xtGraphics.stg Ljava/awt/Image;
      // af90: sipush 422
      // af93: sipush 227
      // af96: aconst_null
      // af97: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // af9a: pop
      // af9b: aload 0
      // af9c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // af9f: new java/awt/Color
      // afa2: dup
      // afa3: bipush 0
      // afa4: bipush 0
      // afa5: bipush 0
      // afa6: invokespecial java/awt/Color.<init> (III)V
      // afa9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // afac: aload 0
      // afad: getfield Lobby.rd Ljava/awt/Graphics2D;
      // afb0: new java/awt/Font
      // afb3: dup
      // afb4: ldc_w "Arial"
      // afb7: bipush 1
      // afb8: bipush 10
      // afba: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // afbd: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // afc0: aload 0
      // afc1: aload 0
      // afc2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // afc5: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // afc8: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // afcb: aload 0
      // afcc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // afcf: new java/lang/StringBuilder
      // afd2: dup
      // afd3: invokespecial java/lang/StringBuilder.<init> ()V
      // afd6: ldc_w "Laps: "
      // afd9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // afdc: aload 0
      // afdd: getfield Lobby.gnlaps [I
      // afe0: iload 15
      // afe2: iaload
      // afe3: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // afe6: ldc ""
      // afe8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // afeb: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // afee: sipush 660
      // aff1: sipush 235
      // aff4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // aff7: iload 1
      // aff8: sipush 415
      // affb: if_icmpgt b003
      // affe: goto b06a
      // b003: iload 1
      // b004: sipush 708
      // b007: if_icmplt b00f
      // b00a: goto b06a
      // b00f: iload 2
      // b010: sipush 222
      // b013: if_icmpgt b01b
      // b016: goto b06a
      // b01b: iload 2
      // b01c: sipush 262
      // b01f: if_icmplt b027
      // b022: goto b06a
      // b027: aload 0
      // b028: getfield Lobby.wait [I
      // b02b: iload 15
      // b02d: iaload
      // b02e: ifle b036
      // b031: goto b045
      // b036: aload 0
      // b037: getfield Lobby.gstgn [I
      // b03a: iload 15
      // b03c: iaload
      // b03d: iflt b045
      // b040: goto b06a
      // b045: aload 0
      // b046: getfield Lobby.conon I
      // b049: bipush 1
      // b04a: if_icmpeq b052
      // b04d: goto b06a
      // b052: aload 0
      // b053: ldc_w "       Preview Stage       "
      // b056: sipush 562
      // b059: sipush 247
      // b05c: bipush 0
      // b05d: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // b060: aload 0
      // b061: bipush 2
      // b062: putfield Lobby.pbtn I
      // b065: goto b19a
      // b06a: aload 0
      // b06b: getfield Lobby.gstgn [I
      // b06e: iload 15
      // b070: iaload
      // b071: ifgt b079
      // b074: goto b139
      // b079: ldc_w "NFM 1"
      // b07c: astore 23
      // b07e: aload 0
      // b07f: getfield Lobby.gstgn [I
      // b082: iload 15
      // b084: iaload
      // b085: istore 24
      // b087: aload 0
      // b088: getfield Lobby.gstgn [I
      // b08b: iload 15
      // b08d: iaload
      // b08e: bipush 10
      // b090: if_icmpgt b098
      // b093: goto b0ae
      // b098: ldc_w "NFM 2"
      // b09b: astore 23
      // b09d: aload 0
      // b09e: getfield Lobby.gstgn [I
      // b0a1: iload 15
      // b0a3: iaload
      // b0a4: bipush 10
      // b0a6: isub
      // b0a7: istore 24
      // b0a9: goto b0ae
      // b0ae: aload 0
      // b0af: getfield Lobby.gstgn [I
      // b0b2: iload 15
      // b0b4: iaload
      // b0b5: bipush 27
      // b0b7: if_icmpgt b0bf
      // b0ba: goto b0d5
      // b0bf: ldc_w "Multiplayer"
      // b0c2: astore 23
      // b0c4: aload 0
      // b0c5: getfield Lobby.gstgn [I
      // b0c8: iload 15
      // b0ca: iaload
      // b0cb: bipush 27
      // b0cd: isub
      // b0ce: istore 24
      // b0d0: goto b0d5
      // b0d5: aload 0
      // b0d6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b0d9: new java/lang/StringBuilder
      // b0dc: dup
      // b0dd: invokespecial java/lang/StringBuilder.<init> ()V
      // b0e0: ldc ""
      // b0e2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b0e5: aload 23
      // b0e7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b0ea: ldc_w " - Stage "
      // b0ed: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b0f0: iload 24
      // b0f2: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // b0f5: ldc ""
      // b0f7: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b0fa: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b0fd: sipush 562
      // b100: aload 0
      // b101: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b104: new java/lang/StringBuilder
      // b107: dup
      // b108: invokespecial java/lang/StringBuilder.<init> ()V
      // b10b: ldc ""
      // b10d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b110: aload 23
      // b112: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b115: ldc_w " - Stage "
      // b118: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b11b: iload 24
      // b11d: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
      // b120: ldc ""
      // b122: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b125: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b128: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b12b: bipush 2
      // b12c: idiv
      // b12d: isub
      // b12e: sipush 237
      // b131: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b134: goto b156
      // b139: aload 0
      // b13a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b13d: ldc_w "Custom Stage"
      // b140: sipush 562
      // b143: aload 0
      // b144: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b147: ldc_w "Custom Stage"
      // b14a: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b14d: bipush 2
      // b14e: idiv
      // b14f: isub
      // b150: sipush 237
      // b153: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b156: aload 0
      // b157: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b15a: new java/awt/Font
      // b15d: dup
      // b15e: ldc_w "Arial"
      // b161: bipush 1
      // b162: bipush 12
      // b164: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // b167: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // b16a: aload 0
      // b16b: aload 0
      // b16c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b16f: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // b172: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // b175: aload 0
      // b176: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b179: aload 0
      // b17a: getfield Lobby.gstages [Ljava/lang/String;
      // b17d: iload 15
      // b17f: aaload
      // b180: sipush 562
      // b183: aload 0
      // b184: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b187: aload 0
      // b188: getfield Lobby.gstages [Ljava/lang/String;
      // b18b: iload 15
      // b18d: aaload
      // b18e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b191: bipush 2
      // b192: idiv
      // b193: isub
      // b194: sipush 254
      // b197: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b19a: aload 0
      // b19b: getfield Lobby.xt LxtGraphics;
      // b19e: getfield xtGraphics.lan Z
      // b1a1: ifeq b1a9
      // b1a4: goto c6f5
      // b1a9: sipush 237
      // b1ac: istore 23
      // b1ae: sipush 471
      // b1b1: istore 24
      // b1b3: aload 0
      // b1b4: getfield Lobby.wait [I
      // b1b7: iload 15
      // b1b9: iaload
      // b1ba: ifgt b1c2
      // b1bd: goto b1e1
      // b1c2: aload 0
      // b1c3: getfield Lobby.mnpls [I
      // b1c6: iload 15
      // b1c8: iaload
      // b1c9: bipush 5
      // b1ca: if_icmpgt b1d2
      // b1cd: goto b200
      // b1d2: sipush 415
      // b1d5: istore 23
      // b1d7: sipush 293
      // b1da: istore 24
      // b1dc: goto b200
      // b1e1: aload 0
      // b1e2: getfield Lobby.npls [I
      // b1e5: iload 15
      // b1e7: iaload
      // b1e8: bipush 5
      // b1e9: if_icmpgt b1f1
      // b1ec: goto b200
      // b1f1: sipush 415
      // b1f4: istore 23
      // b1f6: sipush 293
      // b1f9: istore 24
      // b1fb: goto b200
      // b200: aload 0
      // b201: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b204: aload 0
      // b205: sipush 200
      // b208: sipush 200
      // b20b: sipush 200
      // b20e: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // b211: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b214: aload 0
      // b215: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b218: iload 23
      // b21a: sipush 264
      // b21d: iload 24
      // b21f: bipush 124
      // b221: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // b224: aload 0
      // b225: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b228: aload 0
      // b229: sipush 240
      // b22c: sipush 240
      // b22f: sipush 240
      // b232: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // b235: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b238: aload 0
      // b239: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b23c: iload 23
      // b23e: bipush 1
      // b23f: iadd
      // b240: sipush 265
      // b243: iload 24
      // b245: bipush 1
      // b246: isub
      // b247: bipush 21
      // b249: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b24c: aload 0
      // b24d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b250: aload 0
      // b251: getfield Lobby.xt LxtGraphics;
      // b254: getfield xtGraphics.gmc Ljava/awt/Image;
      // b257: iload 23
      // b259: bipush 7
      // b25b: iadd
      // b25c: sipush 269
      // b25f: aconst_null
      // b260: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // b263: pop
      // b264: aload 0
      // b265: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b268: new java/awt/Font
      // b26b: dup
      // b26c: ldc_w "Tahoma"
      // b26f: bipush 0
      // b270: bipush 11
      // b272: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // b275: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // b278: aload 0
      // b279: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b27c: aload 0
      // b27d: bipush 110
      // b27f: bipush 110
      // b281: bipush 110
      // b283: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // b286: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b289: aload 0
      // b28a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b28d: ldc_w "( Game Chat )"
      // b290: iload 23
      // b292: bipush 57
      // b294: iadd
      // b295: sipush 278
      // b298: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b29b: aload 0
      // b29c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b29f: new java/awt/Color
      // b2a2: dup
      // b2a3: bipush 0
      // b2a4: bipush 0
      // b2a5: bipush 0
      // b2a6: invokespecial java/awt/Color.<init> (III)V
      // b2a9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b2ac: aload 0
      // b2ad: getfield Lobby.updatec I
      // b2b0: bipush -1
      // b2b1: if_icmpne b2b9
      // b2b4: goto b597
      // b2b9: bipush 7
      // b2bb: anewarray 30
      // b2be: astore 25
      // b2c0: bipush 7
      // b2c2: anewarray 30
      // b2c5: astore 26
      // b2c7: bipush 7
      // b2c9: newarray 4
      // b2cb: dup
      // b2cc: bipush 0
      // b2cd: bipush 0
      // b2ce: bastore
      // b2cf: dup
      // b2d0: bipush 1
      // b2d1: bipush 0
      // b2d2: bastore
      // b2d3: dup
      // b2d4: bipush 2
      // b2d5: bipush 0
      // b2d6: bastore
      // b2d7: dup
      // b2d8: bipush 3
      // b2d9: bipush 0
      // b2da: bastore
      // b2db: dup
      // b2dc: bipush 4
      // b2dd: bipush 0
      // b2de: bastore
      // b2df: dup
      // b2e0: bipush 5
      // b2e1: bipush 0
      // b2e2: bastore
      // b2e3: dup
      // b2e4: bipush 6
      // b2e6: bipush 0
      // b2e7: bastore
      // b2e8: astore 27
      // b2ea: bipush 0
      // b2eb: istore 28
      // b2ed: iload 28
      // b2ef: bipush 7
      // b2f1: if_icmplt b2f9
      // b2f4: goto b442
      // b2f9: aload 25
      // b2fb: iload 28
      // b2fd: ldc ""
      // b2ff: aastore
      // b300: aload 26
      // b302: iload 28
      // b304: aload 0
      // b305: getfield Lobby.cnames [Ljava/lang/String;
      // b308: iload 28
      // b30a: aaload
      // b30b: aastore
      // b30c: bipush 0
      // b30d: istore 29
      // b30f: bipush 0
      // b310: istore 30
      // b312: bipush 0
      // b313: istore 31
      // b315: bipush 0
      // b316: istore 32
      // b318: bipush 0
      // b319: istore 33
      // b31b: iload 29
      // b31d: aload 0
      // b31e: getfield Lobby.sentn [Ljava/lang/String;
      // b321: iload 28
      // b323: aaload
      // b324: invokevirtual java/lang/String.length ()I
      // b327: if_icmplt b32f
      // b32a: goto b43a
      // b32f: new java/lang/StringBuilder
      // b332: dup
      // b333: invokespecial java/lang/StringBuilder.<init> ()V
      // b336: ldc ""
      // b338: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b33b: aload 0
      // b33c: getfield Lobby.sentn [Ljava/lang/String;
      // b33f: iload 28
      // b341: aaload
      // b342: iload 29
      // b344: invokevirtual java/lang/String.charAt (I)C
      // b347: invokevirtual java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
      // b34a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b34d: astore 34
      // b34f: aload 34
      // b351: ldc_w " "
      // b354: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // b357: ifne b35f
      // b35a: goto b36f
      // b35f: iload 31
      // b361: istore 30
      // b363: iload 29
      // b365: istore 32
      // b367: iinc 33 1
      // b36a: goto b372
      // b36f: bipush 0
      // b370: istore 33
      // b372: iload 33
      // b374: bipush 1
      // b375: if_icmple b37d
      // b378: goto b432
      // b37d: new java/lang/StringBuilder
      // b380: dup
      // b381: invokespecial java/lang/StringBuilder.<init> ()V
      // b384: aload 25
      // b386: iload 28
      // b388: dup2_x1
      // b389: aaload
      // b38a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b38d: aload 34
      // b38f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b392: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b395: aastore
      // b396: iinc 31 1
      // b399: aload 0
      // b39a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b39d: aload 25
      // b39f: iload 28
      // b3a1: aaload
      // b3a2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b3a5: iload 24
      // b3a7: bipush 94
      // b3a9: isub
      // b3aa: if_icmpgt b3b2
      // b3ad: goto b432
      // b3b2: iload 30
      // b3b4: ifne b3bc
      // b3b7: goto b423
      // b3bc: aload 25
      // b3be: iload 28
      // b3c0: aload 25
      // b3c2: iload 28
      // b3c4: aaload
      // b3c5: bipush 0
      // b3c6: iload 30
      // b3c8: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // b3cb: aastore
      // b3cc: bipush 0
      // b3cd: istore 35
      // b3cf: iload 35
      // b3d1: iload 28
      // b3d3: if_icmplt b3db
      // b3d6: goto b407
      // b3db: aload 25
      // b3dd: iload 35
      // b3df: aload 25
      // b3e1: iload 35
      // b3e3: bipush 1
      // b3e4: iadd
      // b3e5: aaload
      // b3e6: aastore
      // b3e7: aload 26
      // b3e9: iload 35
      // b3eb: aload 26
      // b3ed: iload 35
      // b3ef: bipush 1
      // b3f0: iadd
      // b3f1: aaload
      // b3f2: aastore
      // b3f3: aload 27
      // b3f5: iload 35
      // b3f7: aload 27
      // b3f9: iload 35
      // b3fb: bipush 1
      // b3fc: iadd
      // b3fd: baload
      // b3fe: bastore
      // b3ff: iinc 35 1
      // b402: goto b3cf
      // b407: aload 25
      // b409: iload 28
      // b40b: ldc ""
      // b40d: aastore
      // b40e: aload 27
      // b410: iload 28
      // b412: bipush 1
      // b413: bastore
      // b414: iload 32
      // b416: istore 29
      // b418: bipush 0
      // b419: istore 31
      // b41b: bipush 0
      // b41c: istore 30
      // b41e: goto b432
      // b423: aload 25
      // b425: iload 28
      // b427: ldc ""
      // b429: aastore
      // b42a: bipush 0
      // b42b: istore 31
      // b42d: goto b432
      // b432: iinc 29 1
      // b435: goto b31b
      // b43a: iinc 28 1
      // b43d: goto b2ed
      // b442: ldc ""
      // b444: astore 28
      // b446: aload 0
      // b447: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b44a: new java/awt/Font
      // b44d: dup
      // b44e: ldc_w "Tahoma"
      // b451: bipush 1
      // b452: bipush 11
      // b454: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // b457: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // b45a: aload 0
      // b45b: aload 0
      // b45c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b45f: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // b462: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // b465: bipush 0
      // b466: istore 29
      // b468: iload 29
      // b46a: bipush 7
      // b46c: if_icmplt b474
      // b46f: goto b4e5
      // b474: aload 28
      // b476: aload 26
      // b478: iload 29
      // b47a: aaload
      // b47b: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // b47e: ifeq b486
      // b481: goto b4dd
      // b486: aload 0
      // b487: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b48a: new java/lang/StringBuilder
      // b48d: dup
      // b48e: invokespecial java/lang/StringBuilder.<init> ()V
      // b491: aload 26
      // b493: iload 29
      // b495: aaload
      // b496: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b499: ldc ":"
      // b49b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b49e: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b4a1: iload 23
      // b4a3: bipush 84
      // b4a5: iadd
      // b4a6: aload 0
      // b4a7: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b4aa: new java/lang/StringBuilder
      // b4ad: dup
      // b4ae: invokespecial java/lang/StringBuilder.<init> ()V
      // b4b1: aload 26
      // b4b3: iload 29
      // b4b5: aaload
      // b4b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b4b9: ldc ":"
      // b4bb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b4be: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b4c1: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b4c4: isub
      // b4c5: sipush 299
      // b4c8: iload 29
      // b4ca: bipush 14
      // b4cc: imul
      // b4cd: iadd
      // b4ce: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b4d1: aload 26
      // b4d3: iload 29
      // b4d5: aaload
      // b4d6: astore 28
      // b4d8: goto b4dd
      // b4dd: iinc 29 1
      // b4e0: goto b468
      // b4e5: aload 0
      // b4e6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b4e9: new java/awt/Font
      // b4ec: dup
      // b4ed: ldc_w "Tahoma"
      // b4f0: bipush 0
      // b4f1: bipush 11
      // b4f3: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // b4f6: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // b4f9: bipush 0
      // b4fa: istore 29
      // b4fc: iload 29
      // b4fe: bipush 7
      // b500: if_icmplt b508
      // b503: goto b592
      // b508: aload 27
      // b50a: iload 29
      // b50c: baload
      // b50d: ifne b515
      // b510: goto b570
      // b515: iload 29
      // b517: ifeq b51f
      // b51a: goto b570
      // b51f: aload 25
      // b521: iload 29
      // b523: aaload
      // b524: ldc_w " "
      // b527: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // b52a: bipush -1
      // b52b: if_icmpne b533
      // b52e: goto b570
      // b533: aload 25
      // b535: iload 29
      // b537: new java/lang/StringBuilder
      // b53a: dup
      // b53b: invokespecial java/lang/StringBuilder.<init> ()V
      // b53e: ldc_w "..."
      // b541: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b544: aload 25
      // b546: iload 29
      // b548: aaload
      // b549: aload 25
      // b54b: iload 29
      // b54d: aaload
      // b54e: ldc_w " "
      // b551: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // b554: aload 25
      // b556: iload 29
      // b558: aaload
      // b559: invokevirtual java/lang/String.length ()I
      // b55c: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // b55f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b562: ldc ""
      // b564: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // b567: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // b56a: aastore
      // b56b: goto b570
      // b570: aload 0
      // b571: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b574: aload 25
      // b576: iload 29
      // b578: aaload
      // b579: iload 23
      // b57b: bipush 88
      // b57d: iadd
      // b57e: sipush 299
      // b581: iload 29
      // b583: bipush 14
      // b585: imul
      // b586: iadd
      // b587: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b58a: iinc 29 1
      // b58d: goto b4fc
      // b592: goto b5b8
      // b597: aload 0
      // b598: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b59b: ldc_w "Loading chat..."
      // b59e: iload 23
      // b5a0: iload 24
      // b5a2: bipush 2
      // b5a3: idiv
      // b5a4: iadd
      // b5a5: aload 0
      // b5a6: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b5a9: ldc_w "Loading chat..."
      // b5ac: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b5af: bipush 2
      // b5b0: idiv
      // b5b1: isub
      // b5b2: sipush 315
      // b5b5: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b5b8: aload 0
      // b5b9: getfield Lobby.conon I
      // b5bc: bipush 1
      // b5bd: if_icmpeq b5c5
      // b5c0: goto b5cf
      // b5c5: aload 0
      // b5c6: bipush 1
      // b5c7: putfield Lobby.pre2 Z
      // b5ca: goto b5d3
      // b5cf: aload 0
      // b5d0: invokevirtual Lobby.hideinputs ()V
      // b5d3: aload 6
      // b5d5: getfield Control.enter Z
      // b5d8: ifne b5e0
      // b5db: goto b70f
      // b5e0: aload 0
      // b5e1: getfield Lobby.gs LGameSparker;
      // b5e4: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b5e7: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // b5ea: ldc_w "Type here..."
      // b5ed: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // b5f0: ifeq b5f8
      // b5f3: goto b70f
      // b5f8: aload 0
      // b5f9: getfield Lobby.gs LGameSparker;
      // b5fc: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b5ff: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // b602: ldc ""
      // b604: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // b607: ifeq b60f
      // b60a: goto b70f
      // b60f: aload 0
      // b610: getfield Lobby.pessd [Z
      // b613: aload 0
      // b614: getfield Lobby.btn I
      // b617: bipush 1
      // b618: bastore
      // b619: aload 6
      // b61b: bipush 0
      // b61c: putfield Control.enter Z
      // b61f: aload 0
      // b620: getfield Lobby.gs LGameSparker;
      // b623: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b626: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // b629: bipush 124
      // b62b: bipush 58
      // b62d: invokevirtual java/lang/String.replace (CC)Ljava/lang/String;
      // b630: astore 25
      // b632: aload 25
      // b634: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // b637: aload 0
      // b638: getfield Lobby.gs LGameSparker;
      // b63b: getfield GameSparker.tpass Ljava/awt/TextField;
      // b63e: invokevirtual java/awt/TextField.getText ()Ljava/lang/String;
      // b641: invokevirtual java/lang/String.toLowerCase ()Ljava/lang/String;
      // b644: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // b647: bipush -1
      // b648: if_icmpne b650
      // b64b: goto b65a
      // b650: ldc_w " "
      // b653: astore 25
      // b655: goto b65a
      // b65a: aload 0
      // b65b: getfield Lobby.xt LxtGraphics;
      // b65e: aload 25
      // b660: invokevirtual xtGraphics.msgcheck (Ljava/lang/String;)Z
      // b663: ifeq b66b
      // b666: goto b6f1
      // b66b: aload 0
      // b66c: getfield Lobby.updatec I
      // b66f: bipush -12
      // b671: if_icmpgt b679
      // b674: goto b6f1
      // b679: bipush 0
      // b67a: istore 26
      // b67c: iload 26
      // b67e: bipush 6
      // b680: if_icmplt b688
      // b683: goto b6b0
      // b688: aload 0
      // b689: getfield Lobby.sentn [Ljava/lang/String;
      // b68c: iload 26
      // b68e: aload 0
      // b68f: getfield Lobby.sentn [Ljava/lang/String;
      // b692: iload 26
      // b694: bipush 1
      // b695: iadd
      // b696: aaload
      // b697: aastore
      // b698: aload 0
      // b699: getfield Lobby.cnames [Ljava/lang/String;
      // b69c: iload 26
      // b69e: aload 0
      // b69f: getfield Lobby.cnames [Ljava/lang/String;
      // b6a2: iload 26
      // b6a4: bipush 1
      // b6a5: iadd
      // b6a6: aaload
      // b6a7: aastore
      // b6a8: iinc 26 1
      // b6ab: goto b67c
      // b6b0: aload 0
      // b6b1: getfield Lobby.sentn [Ljava/lang/String;
      // b6b4: bipush 6
      // b6b6: aload 25
      // b6b8: aastore
      // b6b9: aload 0
      // b6ba: getfield Lobby.cnames [Ljava/lang/String;
      // b6bd: bipush 6
      // b6bf: aload 0
      // b6c0: getfield Lobby.pnames [Ljava/lang/String;
      // b6c3: aload 0
      // b6c4: getfield Lobby.im I
      // b6c7: aaload
      // b6c8: aastore
      // b6c9: aload 0
      // b6ca: getfield Lobby.updatec I
      // b6cd: bipush -11
      // b6cf: if_icmpgt b6d7
      // b6d2: goto b6e2
      // b6d7: aload 0
      // b6d8: bipush -11
      // b6da: putfield Lobby.updatec I
      // b6dd: goto b6fe
      // b6e2: aload 0
      // b6e3: dup
      // b6e4: getfield Lobby.updatec I
      // b6e7: bipush 1
      // b6e8: isub
      // b6e9: putfield Lobby.updatec I
      // b6ec: goto b6fe
      // b6f1: aload 0
      // b6f2: getfield Lobby.xt LxtGraphics;
      // b6f5: dup
      // b6f6: getfield xtGraphics.warning I
      // b6f9: bipush 1
      // b6fa: iadd
      // b6fb: putfield xtGraphics.warning I
      // b6fe: aload 0
      // b6ff: getfield Lobby.gs LGameSparker;
      // b702: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b705: ldc ""
      // b707: invokevirtual java/awt/TextField.setText (Ljava/lang/String;)V
      // b70a: goto b70f
      // b70f: aload 0
      // b710: ldc_w "Send Message"
      // b713: sipush 655
      // b716: sipush 405
      // b719: bipush 3
      // b71a: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // b71d: goto c6f5
      // b722: aload 0
      // b723: getfield Lobby.gs LGameSparker;
      // b726: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b729: invokevirtual java/awt/TextField.isShowing ()Z
      // b72c: ifne b734
      // b72f: goto b743
      // b734: aload 0
      // b735: getfield Lobby.gs LGameSparker;
      // b738: getfield GameSparker.cmsg Ljava/awt/TextField;
      // b73b: invokevirtual java/awt/TextField.hide ()V
      // b73e: goto b743
      // b743: aload 0
      // b744: getfield Lobby.pcars [I
      // b747: aload 0
      // b748: getfield Lobby.dispcar I
      // b74b: iaload
      // b74c: aload 0
      // b74d: getfield Lobby.forcar I
      // b750: if_icmpeq b758
      // b753: goto c6f0
      // b758: aload 0
      // b759: getfield Lobby.forcar I
      // b75c: bipush -1
      // b75d: if_icmpne b765
      // b760: goto c6f0
      // b765: aload 0
      // b766: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b769: aload 0
      // b76a: getfield Lobby.xt LxtGraphics;
      // b76d: getfield xtGraphics.crd Ljava/awt/Image;
      // b770: sipush 517
      // b773: bipush 81
      // b775: aconst_null
      // b776: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // b779: pop
      // b77a: aload 0
      // b77b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b77e: new java/awt/Color
      // b781: dup
      // b782: bipush 16
      // b784: sipush 198
      // b787: sipush 255
      // b78a: invokespecial java/awt/Color.<init> (III)V
      // b78d: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b790: aload 0
      // b791: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b794: sipush 415
      // b797: bipush 96
      // b799: sipush 293
      // b79c: sipush 315
      // b79f: invokevirtual java/awt/Graphics2D.drawRect (IIII)V
      // b7a2: aload 0
      // b7a3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b7a6: aload 0
      // b7a7: sipush 240
      // b7aa: sipush 240
      // b7ad: sipush 240
      // b7b0: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // b7b3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b7b6: aload 0
      // b7b7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b7ba: sipush 416
      // b7bd: bipush 97
      // b7bf: bipush 4
      // b7c0: sipush 314
      // b7c3: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b7c6: aload 0
      // b7c7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b7ca: sipush 704
      // b7cd: bipush 97
      // b7cf: bipush 4
      // b7d0: sipush 314
      // b7d3: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b7d6: aload 0
      // b7d7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b7da: sipush 416
      // b7dd: bipush 97
      // b7df: sipush 292
      // b7e2: bipush 4
      // b7e3: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b7e6: aload 0
      // b7e7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b7ea: sipush 416
      // b7ed: sipush 407
      // b7f0: sipush 292
      // b7f3: bipush 4
      // b7f4: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b7f7: aload 0
      // b7f8: getfield Lobby.flks I
      // b7fb: ifge b803
      // b7fe: goto b83b
      // b803: aload 0
      // b804: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b807: new java/awt/Color
      // b80a: dup
      // b80b: sipush 239
      // b80e: sipush 234
      // b811: sipush 177
      // b814: invokespecial java/awt/Color.<init> (III)V
      // b817: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b81a: aload 0
      // b81b: dup
      // b81c: getfield Lobby.flks I
      // b81f: bipush 1
      // b820: iadd
      // b821: putfield Lobby.flks I
      // b824: aload 0
      // b825: getfield Lobby.flks I
      // b828: bipush 3
      // b829: if_icmpgt b831
      // b82c: goto b874
      // b831: aload 0
      // b832: bipush -1
      // b833: putfield Lobby.flks I
      // b836: goto b874
      // b83b: aload 0
      // b83c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b83f: new java/awt/Color
      // b842: dup
      // b843: sipush 224
      // b846: sipush 226
      // b849: sipush 176
      // b84c: invokespecial java/awt/Color.<init> (III)V
      // b84f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b852: aload 0
      // b853: dup
      // b854: getfield Lobby.flks I
      // b857: bipush 1
      // b858: isub
      // b859: putfield Lobby.flks I
      // b85c: aload 0
      // b85d: getfield Lobby.flks I
      // b860: bipush -4
      // b862: if_icmplt b86a
      // b865: goto b874
      // b86a: aload 0
      // b86b: bipush 0
      // b86c: putfield Lobby.flks I
      // b86f: goto b874
      // b874: aload 0
      // b875: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b878: sipush 445
      // b87b: bipush 120
      // b87d: sipush 233
      // b880: bipush 127
      // b882: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // b885: aload 0
      // b886: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b889: new java/awt/Color
      // b88c: dup
      // b88d: bipush 0
      // b88e: bipush 0
      // b88f: bipush 0
      // b890: invokespecial java/awt/Color.<init> (III)V
      // b893: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // b896: aload 0
      // b897: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b89a: new java/awt/Font
      // b89d: dup
      // b89e: ldc_w "Arial"
      // b8a1: bipush 1
      // b8a2: bipush 13
      // b8a4: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // b8a7: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // b8aa: aload 0
      // b8ab: aload 0
      // b8ac: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b8af: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // b8b2: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // b8b5: aload 0
      // b8b6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // b8b9: aload 0
      // b8ba: getfield Lobby.cd LCarDefine;
      // b8bd: getfield CarDefine.names [Ljava/lang/String;
      // b8c0: aload 0
      // b8c1: getfield Lobby.forcar I
      // b8c4: aaload
      // b8c5: sipush 561
      // b8c8: aload 0
      // b8c9: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // b8cc: aload 0
      // b8cd: getfield Lobby.cd LCarDefine;
      // b8d0: getfield CarDefine.names [Ljava/lang/String;
      // b8d3: aload 0
      // b8d4: getfield Lobby.forcar I
      // b8d7: aaload
      // b8d8: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // b8db: bipush 2
      // b8dc: idiv
      // b8dd: isub
      // b8de: bipush 117
      // b8e0: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // b8e3: bipush 0
      // b8e4: istore 23
      // b8e6: iload 23
      // b8e8: aload 0
      // b8e9: getfield Lobby.dispco LContO;
      // b8ec: getfield ContO.npl I
      // b8ef: if_icmplt b8f7
      // b8f2: goto b9db
      // b8f7: aload 7
      // b8f9: aload 0
      // b8fa: getfield Lobby.forcar I
      // b8fd: aaload
      // b8fe: getfield ContO.p [LPlane;
      // b901: iload 23
      // b903: aaload
      // b904: getfield Plane.colnum I
      // b907: bipush 1
      // b908: if_icmpeq b910
      // b90b: goto b965
      // b910: aload 0
      // b911: getfield Lobby.dispco LContO;
      // b914: getfield ContO.p [LPlane;
      // b917: iload 23
      // b919: aaload
      // b91a: getfield Plane.hsb [F
      // b91d: bipush 0
      // b91e: aload 0
      // b91f: getfield Lobby.pcols [[F
      // b922: aload 0
      // b923: getfield Lobby.dispcar I
      // b926: aaload
      // b927: bipush 0
      // b928: faload
      // b929: fastore
      // b92a: aload 0
      // b92b: getfield Lobby.dispco LContO;
      // b92e: getfield ContO.p [LPlane;
      // b931: iload 23
      // b933: aaload
      // b934: getfield Plane.hsb [F
      // b937: bipush 1
      // b938: aload 0
      // b939: getfield Lobby.pcols [[F
      // b93c: aload 0
      // b93d: getfield Lobby.dispcar I
      // b940: aaload
      // b941: bipush 1
      // b942: faload
      // b943: fastore
      // b944: aload 0
      // b945: getfield Lobby.dispco LContO;
      // b948: getfield ContO.p [LPlane;
      // b94b: iload 23
      // b94d: aaload
      // b94e: getfield Plane.hsb [F
      // b951: bipush 2
      // b952: fconst_1
      // b953: aload 0
      // b954: getfield Lobby.pcols [[F
      // b957: aload 0
      // b958: getfield Lobby.dispcar I
      // b95b: aaload
      // b95c: bipush 2
      // b95d: faload
      // b95e: fsub
      // b95f: fastore
      // b960: goto b965
      // b965: aload 7
      // b967: aload 0
      // b968: getfield Lobby.forcar I
      // b96b: aaload
      // b96c: getfield ContO.p [LPlane;
      // b96f: iload 23
      // b971: aaload
      // b972: getfield Plane.colnum I
      // b975: bipush 2
      // b976: if_icmpeq b97e
      // b979: goto b9d3
      // b97e: aload 0
      // b97f: getfield Lobby.dispco LContO;
      // b982: getfield ContO.p [LPlane;
      // b985: iload 23
      // b987: aaload
      // b988: getfield Plane.hsb [F
      // b98b: bipush 0
      // b98c: aload 0
      // b98d: getfield Lobby.pcols [[F
      // b990: aload 0
      // b991: getfield Lobby.dispcar I
      // b994: aaload
      // b995: bipush 3
      // b996: faload
      // b997: fastore
      // b998: aload 0
      // b999: getfield Lobby.dispco LContO;
      // b99c: getfield ContO.p [LPlane;
      // b99f: iload 23
      // b9a1: aaload
      // b9a2: getfield Plane.hsb [F
      // b9a5: bipush 1
      // b9a6: aload 0
      // b9a7: getfield Lobby.pcols [[F
      // b9aa: aload 0
      // b9ab: getfield Lobby.dispcar I
      // b9ae: aaload
      // b9af: bipush 4
      // b9b0: faload
      // b9b1: fastore
      // b9b2: aload 0
      // b9b3: getfield Lobby.dispco LContO;
      // b9b6: getfield ContO.p [LPlane;
      // b9b9: iload 23
      // b9bb: aaload
      // b9bc: getfield Plane.hsb [F
      // b9bf: bipush 2
      // b9c0: fconst_1
      // b9c1: aload 0
      // b9c2: getfield Lobby.pcols [[F
      // b9c5: aload 0
      // b9c6: getfield Lobby.dispcar I
      // b9c9: aaload
      // b9ca: bipush 5
      // b9cb: faload
      // b9cc: fsub
      // b9cd: fastore
      // b9ce: goto b9d3
      // b9d3: iinc 23 1
      // b9d6: goto b8e6
      // b9db: aload 0
      // b9dc: getfield Lobby.m LMedium;
      // b9df: sipush 561
      // b9e2: putfield Medium.cx I
      // b9e5: aload 0
      // b9e6: getfield Lobby.dispco LContO;
      // b9e9: sipush 1200
      // b9ec: putfield ContO.z I
      // b9ef: aload 0
      // b9f0: getfield Lobby.dispco LContO;
      // b9f3: sipush 605
      // b9f6: aload 0
      // b9f7: getfield Lobby.dispco LContO;
      // b9fa: getfield ContO.grat I
      // b9fd: isub
      // b9fe: putfield ContO.y I
      // ba01: aload 0
      // ba02: getfield Lobby.dispco LContO;
      // ba05: sipush 225
      // ba08: putfield ContO.x I
      // ba0b: aload 0
      // ba0c: getfield Lobby.dispco LContO;
      // ba0f: bipush 0
      // ba10: putfield ContO.zy I
      // ba13: aload 0
      // ba14: getfield Lobby.dispco LContO;
      // ba17: aload 0
      // ba18: getfield Lobby.mrot I
      // ba1b: putfield ContO.xz I
      // ba1e: aload 0
      // ba1f: dup
      // ba20: getfield Lobby.mrot I
      // ba23: bipush 5
      // ba24: isub
      // ba25: putfield Lobby.mrot I
      // ba28: aload 0
      // ba29: getfield Lobby.mrot I
      // ba2c: sipush -360
      // ba2f: if_icmplt ba37
      // ba32: goto ba48
      // ba37: aload 0
      // ba38: dup
      // ba39: getfield Lobby.mrot I
      // ba3c: sipush 360
      // ba3f: iadd
      // ba40: putfield Lobby.mrot I
      // ba43: goto ba48
      // ba48: aload 0
      // ba49: getfield Lobby.dispco LContO;
      // ba4c: bipush 0
      // ba4d: putfield ContO.xy I
      // ba50: aload 0
      // ba51: getfield Lobby.dispco LContO;
      // ba54: dup
      // ba55: getfield ContO.wzy I
      // ba58: bipush 10
      // ba5a: isub
      // ba5b: putfield ContO.wzy I
      // ba5e: aload 0
      // ba5f: getfield Lobby.dispco LContO;
      // ba62: getfield ContO.wzy I
      // ba65: bipush -45
      // ba67: if_icmplt ba6f
      // ba6a: goto ba82
      // ba6f: aload 0
      // ba70: getfield Lobby.dispco LContO;
      // ba73: dup
      // ba74: getfield ContO.wzy I
      // ba77: bipush 45
      // ba79: iadd
      // ba7a: putfield ContO.wzy I
      // ba7d: goto ba82
      // ba82: aload 0
      // ba83: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ba86: getstatic java/awt/RenderingHints.KEY_ANTIALIASING Ljava/awt/RenderingHints$Key;
      // ba89: getstatic java/awt/RenderingHints.VALUE_ANTIALIAS_OFF Ljava/lang/Object;
      // ba8c: invokevirtual java/awt/Graphics2D.setRenderingHint (Ljava/awt/RenderingHints$Key;Ljava/lang/Object;)V
      // ba8f: aload 0
      // ba90: getfield Lobby.dispco LContO;
      // ba93: aload 0
      // ba94: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ba97: invokevirtual ContO.d (Ljava/awt/Graphics2D;)V
      // ba9a: aload 0
      // ba9b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ba9e: getstatic java/awt/RenderingHints.KEY_ANTIALIASING Ljava/awt/RenderingHints$Key;
      // baa1: getstatic java/awt/RenderingHints.VALUE_ANTIALIAS_ON Ljava/lang/Object;
      // baa4: invokevirtual java/awt/Graphics2D.setRenderingHint (Ljava/awt/RenderingHints$Key;Ljava/lang/Object;)V
      // baa7: aload 0
      // baa8: getfield Lobby.m LMedium;
      // baab: sipush 400
      // baae: putfield Medium.cx I
      // bab1: aload 0
      // bab2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bab5: new java/awt/Font
      // bab8: dup
      // bab9: ldc_w "Arial"
      // babc: bipush 1
      // babd: bipush 11
      // babf: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // bac2: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // bac5: sipush 424
      // bac8: istore 23
      // baca: bipush -55
      // bacc: istore 24
      // bace: aload 0
      // bacf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bad2: new java/awt/Color
      // bad5: dup
      // bad6: bipush 0
      // bad7: bipush 63
      // bad9: sipush 128
      // badc: invokespecial java/awt/Color.<init> (III)V
      // badf: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // bae2: aload 0
      // bae3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bae6: ldc_w "Top Speed:"
      // bae9: bipush 30
      // baeb: iload 23
      // baed: iadd
      // baee: sipush 318
      // baf1: iload 24
      // baf3: iadd
      // baf4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // baf7: aload 0
      // baf8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bafb: aload 0
      // bafc: getfield Lobby.xt LxtGraphics;
      // baff: getfield xtGraphics.statb Ljava/awt/Image;
      // bb02: bipush 97
      // bb04: iload 23
      // bb06: iadd
      // bb07: sipush 312
      // bb0a: iload 24
      // bb0c: iadd
      // bb0d: aconst_null
      // bb0e: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bb11: pop
      // bb12: aload 0
      // bb13: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bb16: ldc_w "Acceleration:"
      // bb19: bipush 20
      // bb1b: iload 23
      // bb1d: iadd
      // bb1e: sipush 333
      // bb21: iload 24
      // bb23: iadd
      // bb24: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bb27: aload 0
      // bb28: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bb2b: aload 0
      // bb2c: getfield Lobby.xt LxtGraphics;
      // bb2f: getfield xtGraphics.statb Ljava/awt/Image;
      // bb32: bipush 97
      // bb34: iload 23
      // bb36: iadd
      // bb37: sipush 327
      // bb3a: iload 24
      // bb3c: iadd
      // bb3d: aconst_null
      // bb3e: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bb41: pop
      // bb42: aload 0
      // bb43: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bb46: aload 0
      // bb47: sipush 255
      // bb4a: sipush 255
      // bb4d: sipush 255
      // bb50: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // bb53: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // bb56: aload 0
      // bb57: getfield Lobby.cd LCarDefine;
      // bb5a: getfield CarDefine.swits [[I
      // bb5d: aload 0
      // bb5e: getfield Lobby.forcar I
      // bb61: aaload
      // bb62: bipush 2
      // bb63: iaload
      // bb64: sipush 220
      // bb67: isub
      // bb68: i2f
      // bb69: ldc_w 90.0
      // bb6c: fdiv
      // bb6d: fstore 25
      // bb6f: fload 25
      // bb71: f2d
      // bb72: ldc2_w 0.2
      // bb75: dcmpg
      // bb76: iflt bb7e
      // bb79: goto bb88
      // bb7e: ldc_w 0.2
      // bb81: fstore 25
      // bb83: goto bb88
      // bb88: aload 0
      // bb89: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bb8c: ldc_w 97.0
      // bb8f: ldc_w 156.0
      // bb92: fload 25
      // bb94: fmul
      // bb95: fadd
      // bb96: f2i
      // bb97: iload 23
      // bb99: iadd
      // bb9a: sipush 312
      // bb9d: iload 24
      // bb9f: iadd
      // bba0: ldc_w 156.0
      // bba3: fconst_1
      // bba4: fload 25
      // bba6: fsub
      // bba7: fmul
      // bba8: fconst_1
      // bba9: fadd
      // bbaa: f2i
      // bbab: bipush 7
      // bbad: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // bbb0: aload 0
      // bbb1: getfield Lobby.cd LCarDefine;
      // bbb4: getfield CarDefine.acelf [[F
      // bbb7: aload 0
      // bbb8: getfield Lobby.forcar I
      // bbbb: aaload
      // bbbc: bipush 1
      // bbbd: faload
      // bbbe: aload 0
      // bbbf: getfield Lobby.cd LCarDefine;
      // bbc2: getfield CarDefine.acelf [[F
      // bbc5: aload 0
      // bbc6: getfield Lobby.forcar I
      // bbc9: aaload
      // bbca: bipush 0
      // bbcb: faload
      // bbcc: fmul
      // bbcd: aload 0
      // bbce: getfield Lobby.cd LCarDefine;
      // bbd1: getfield CarDefine.acelf [[F
      // bbd4: aload 0
      // bbd5: getfield Lobby.forcar I
      // bbd8: aaload
      // bbd9: bipush 2
      // bbda: faload
      // bbdb: fmul
      // bbdc: aload 0
      // bbdd: getfield Lobby.cd LCarDefine;
      // bbe0: getfield CarDefine.grip [F
      // bbe3: aload 0
      // bbe4: getfield Lobby.forcar I
      // bbe7: faload
      // bbe8: fmul
      // bbe9: ldc_w 7700.0
      // bbec: fdiv
      // bbed: fstore 25
      // bbef: fload 25
      // bbf1: fconst_1
      // bbf2: fcmpl
      // bbf3: ifgt bbfb
      // bbf6: goto bc03
      // bbfb: fconst_1
      // bbfc: fstore 25
      // bbfe: goto bc03
      // bc03: aload 0
      // bc04: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc07: ldc_w 97.0
      // bc0a: ldc_w 156.0
      // bc0d: fload 25
      // bc0f: fmul
      // bc10: fadd
      // bc11: f2i
      // bc12: iload 23
      // bc14: iadd
      // bc15: sipush 327
      // bc18: iload 24
      // bc1a: iadd
      // bc1b: ldc_w 156.0
      // bc1e: fconst_1
      // bc1f: fload 25
      // bc21: fsub
      // bc22: fmul
      // bc23: fconst_1
      // bc24: fadd
      // bc25: f2i
      // bc26: bipush 7
      // bc28: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // bc2b: aload 0
      // bc2c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc2f: aload 0
      // bc30: getfield Lobby.xt LxtGraphics;
      // bc33: getfield xtGraphics.statbo Ljava/awt/Image;
      // bc36: bipush 97
      // bc38: iload 23
      // bc3a: iadd
      // bc3b: sipush 312
      // bc3e: iload 24
      // bc40: iadd
      // bc41: aconst_null
      // bc42: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bc45: pop
      // bc46: aload 0
      // bc47: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc4a: aload 0
      // bc4b: getfield Lobby.xt LxtGraphics;
      // bc4e: getfield xtGraphics.statbo Ljava/awt/Image;
      // bc51: bipush 97
      // bc53: iload 23
      // bc55: iadd
      // bc56: sipush 327
      // bc59: iload 24
      // bc5b: iadd
      // bc5c: aconst_null
      // bc5d: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bc60: pop
      // bc61: bipush 50
      // bc63: istore 23
      // bc65: bipush -25
      // bc67: istore 24
      // bc69: aload 0
      // bc6a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc6d: new java/awt/Color
      // bc70: dup
      // bc71: bipush 0
      // bc72: bipush 63
      // bc74: sipush 128
      // bc77: invokespecial java/awt/Color.<init> (III)V
      // bc7a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // bc7d: aload 0
      // bc7e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc81: ldc_w "Stunts:"
      // bc84: sipush 427
      // bc87: iload 23
      // bc89: iadd
      // bc8a: sipush 318
      // bc8d: iload 24
      // bc8f: iadd
      // bc90: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bc93: aload 0
      // bc94: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bc97: aload 0
      // bc98: getfield Lobby.xt LxtGraphics;
      // bc9b: getfield xtGraphics.statb Ljava/awt/Image;
      // bc9e: sipush 471
      // bca1: iload 23
      // bca3: iadd
      // bca4: sipush 312
      // bca7: iload 24
      // bca9: iadd
      // bcaa: aconst_null
      // bcab: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bcae: pop
      // bcaf: aload 0
      // bcb0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bcb3: ldc_w "Strength:"
      // bcb6: sipush 415
      // bcb9: iload 23
      // bcbb: iadd
      // bcbc: sipush 333
      // bcbf: iload 24
      // bcc1: iadd
      // bcc2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bcc5: aload 0
      // bcc6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bcc9: aload 0
      // bcca: getfield Lobby.xt LxtGraphics;
      // bccd: getfield xtGraphics.statb Ljava/awt/Image;
      // bcd0: sipush 471
      // bcd3: iload 23
      // bcd5: iadd
      // bcd6: sipush 327
      // bcd9: iload 24
      // bcdb: iadd
      // bcdc: aconst_null
      // bcdd: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bce0: pop
      // bce1: aload 0
      // bce2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bce5: ldc_w "Endurance:"
      // bce8: sipush 405
      // bceb: iload 23
      // bced: iadd
      // bcee: sipush 348
      // bcf1: iload 24
      // bcf3: iadd
      // bcf4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bcf7: aload 0
      // bcf8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bcfb: aload 0
      // bcfc: getfield Lobby.xt LxtGraphics;
      // bcff: getfield xtGraphics.statb Ljava/awt/Image;
      // bd02: sipush 471
      // bd05: iload 23
      // bd07: iadd
      // bd08: sipush 342
      // bd0b: iload 24
      // bd0d: iadd
      // bd0e: aconst_null
      // bd0f: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // bd12: pop
      // bd13: aload 0
      // bd14: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bd17: aload 0
      // bd18: sipush 255
      // bd1b: sipush 255
      // bd1e: sipush 255
      // bd21: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // bd24: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // bd27: aload 0
      // bd28: getfield Lobby.cd LCarDefine;
      // bd2b: getfield CarDefine.airc [I
      // bd2e: aload 0
      // bd2f: getfield Lobby.forcar I
      // bd32: iaload
      // bd33: i2f
      // bd34: aload 0
      // bd35: getfield Lobby.cd LCarDefine;
      // bd38: getfield CarDefine.airs [F
      // bd3b: aload 0
      // bd3c: getfield Lobby.forcar I
      // bd3f: faload
      // bd40: fmul
      // bd41: aload 0
      // bd42: getfield Lobby.cd LCarDefine;
      // bd45: getfield CarDefine.bounce [F
      // bd48: aload 0
      // bd49: getfield Lobby.forcar I
      // bd4c: faload
      // bd4d: fmul
      // bd4e: ldc_w 28.0
      // bd51: fadd
      // bd52: ldc_w 139.0
      // bd55: fdiv
      // bd56: fstore 25
      // bd58: fload 25
      // bd5a: fconst_1
      // bd5b: fcmpl
      // bd5c: ifgt bd64
      // bd5f: goto bd6c
      // bd64: fconst_1
      // bd65: fstore 25
      // bd67: goto bd6c
      // bd6c: aload 0
      // bd6d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bd70: ldc_w 471.0
      // bd73: ldc_w 156.0
      // bd76: fload 25
      // bd78: fmul
      // bd79: fadd
      // bd7a: f2i
      // bd7b: iload 23
      // bd7d: iadd
      // bd7e: sipush 312
      // bd81: iload 24
      // bd83: iadd
      // bd84: ldc_w 156.0
      // bd87: fconst_1
      // bd88: fload 25
      // bd8a: fsub
      // bd8b: fmul
      // bd8c: fconst_1
      // bd8d: fadd
      // bd8e: f2i
      // bd8f: bipush 7
      // bd91: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // bd94: ldc_w 0.5
      // bd97: fstore 26
      // bd99: aload 0
      // bd9a: getfield Lobby.cd LCarDefine;
      // bd9d: getfield CarDefine.moment [F
      // bda0: aload 0
      // bda1: getfield Lobby.forcar I
      // bda4: faload
      // bda5: fload 26
      // bda7: fadd
      // bda8: ldc_w 2.6
      // bdab: fdiv
      // bdac: fstore 25
      // bdae: fload 25
      // bdb0: fconst_1
      // bdb1: fcmpl
      // bdb2: ifgt bdba
      // bdb5: goto bdc2
      // bdba: fconst_1
      // bdbb: fstore 25
      // bdbd: goto bdc2
      // bdc2: aload 0
      // bdc3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bdc6: ldc_w 471.0
      // bdc9: ldc_w 156.0
      // bdcc: fload 25
      // bdce: fmul
      // bdcf: fadd
      // bdd0: f2i
      // bdd1: iload 23
      // bdd3: iadd
      // bdd4: sipush 327
      // bdd7: iload 24
      // bdd9: iadd
      // bdda: ldc_w 156.0
      // bddd: fconst_1
      // bdde: fload 25
      // bde0: fsub
      // bde1: fmul
      // bde2: fconst_1
      // bde3: fadd
      // bde4: f2i
      // bde5: bipush 7
      // bde7: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // bdea: aload 0
      // bdeb: getfield Lobby.cd LCarDefine;
      // bdee: getfield CarDefine.outdam [F
      // bdf1: aload 0
      // bdf2: getfield Lobby.forcar I
      // bdf5: faload
      // bdf6: fstore 25
      // bdf8: aload 0
      // bdf9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bdfc: ldc_w 471.0
      // bdff: ldc_w 156.0
      // be02: fload 25
      // be04: fmul
      // be05: fadd
      // be06: f2i
      // be07: iload 23
      // be09: iadd
      // be0a: sipush 342
      // be0d: iload 24
      // be0f: iadd
      // be10: ldc_w 156.0
      // be13: fconst_1
      // be14: fload 25
      // be16: fsub
      // be17: fmul
      // be18: fconst_1
      // be19: fadd
      // be1a: f2i
      // be1b: bipush 7
      // be1d: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // be20: aload 0
      // be21: getfield Lobby.rd Ljava/awt/Graphics2D;
      // be24: aload 0
      // be25: getfield Lobby.xt LxtGraphics;
      // be28: getfield xtGraphics.statbo Ljava/awt/Image;
      // be2b: sipush 471
      // be2e: iload 23
      // be30: iadd
      // be31: sipush 312
      // be34: iload 24
      // be36: iadd
      // be37: aconst_null
      // be38: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // be3b: pop
      // be3c: aload 0
      // be3d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // be40: aload 0
      // be41: getfield Lobby.xt LxtGraphics;
      // be44: getfield xtGraphics.statbo Ljava/awt/Image;
      // be47: sipush 471
      // be4a: iload 23
      // be4c: iadd
      // be4d: sipush 327
      // be50: iload 24
      // be52: iadd
      // be53: aconst_null
      // be54: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // be57: pop
      // be58: aload 0
      // be59: getfield Lobby.rd Ljava/awt/Graphics2D;
      // be5c: aload 0
      // be5d: getfield Lobby.xt LxtGraphics;
      // be60: getfield xtGraphics.statbo Ljava/awt/Image;
      // be63: sipush 471
      // be66: iload 23
      // be68: iadd
      // be69: sipush 342
      // be6c: iload 24
      // be6e: iadd
      // be6f: aconst_null
      // be70: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // be73: pop
      // be74: aload 0
      // be75: getfield Lobby.rd Ljava/awt/Graphics2D;
      // be78: new java/awt/Color
      // be7b: dup
      // be7c: bipush 0
      // be7d: bipush 0
      // be7e: bipush 0
      // be7f: invokespecial java/awt/Color.<init> (III)V
      // be82: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // be85: aload 0
      // be86: getfield Lobby.forcar I
      // be89: bipush 16
      // be8b: if_icmplt be93
      // be8e: goto bfdb
      // be93: aload 0
      // be94: getfield Lobby.rd Ljava/awt/Graphics2D;
      // be97: new java/awt/Font
      // be9a: dup
      // be9b: ldc_w "Arial"
      // be9e: bipush 1
      // be9f: bipush 12
      // bea1: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // bea4: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // bea7: aload 0
      // bea8: aload 0
      // bea9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // beac: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // beaf: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // beb2: aload 0
      // beb3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // beb6: ldc_w "Created by Radicalplay.com"
      // beb9: sipush 561
      // bebc: aload 0
      // bebd: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // bec0: ldc_w "Created by Radicalplay.com"
      // bec3: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // bec6: bipush 2
      // bec7: idiv
      // bec8: isub
      // bec9: sipush 347
      // becc: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // becf: ldc_w "Game Car"
      // bed2: astore 27
      // bed4: aload 0
      // bed5: getfield Lobby.cd LCarDefine;
      // bed8: getfield CarDefine.cclass [I
      // bedb: aload 0
      // bedc: getfield Lobby.forcar I
      // bedf: iaload
      // bee0: ifeq bee8
      // bee3: goto bef2
      // bee8: ldc_w "Class C ,  Game Car"
      // beeb: astore 27
      // beed: goto bef2
      // bef2: aload 0
      // bef3: getfield Lobby.cd LCarDefine;
      // bef6: getfield CarDefine.cclass [I
      // bef9: aload 0
      // befa: getfield Lobby.forcar I
      // befd: iaload
      // befe: bipush 1
      // beff: if_icmpeq bf07
      // bf02: goto bf11
      // bf07: ldc_w "Class B & C ,  Game Car"
      // bf0a: astore 27
      // bf0c: goto bf11
      // bf11: aload 0
      // bf12: getfield Lobby.cd LCarDefine;
      // bf15: getfield CarDefine.cclass [I
      // bf18: aload 0
      // bf19: getfield Lobby.forcar I
      // bf1c: iaload
      // bf1d: bipush 2
      // bf1e: if_icmpeq bf26
      // bf21: goto bf30
      // bf26: ldc_w "Class B ,  Game Car"
      // bf29: astore 27
      // bf2b: goto bf30
      // bf30: aload 0
      // bf31: getfield Lobby.cd LCarDefine;
      // bf34: getfield CarDefine.cclass [I
      // bf37: aload 0
      // bf38: getfield Lobby.forcar I
      // bf3b: iaload
      // bf3c: bipush 3
      // bf3d: if_icmpeq bf45
      // bf40: goto bf4f
      // bf45: ldc_w "Class A & B ,  Game Car"
      // bf48: astore 27
      // bf4a: goto bf4f
      // bf4f: aload 0
      // bf50: getfield Lobby.cd LCarDefine;
      // bf53: getfield CarDefine.cclass [I
      // bf56: aload 0
      // bf57: getfield Lobby.forcar I
      // bf5a: iaload
      // bf5b: bipush 4
      // bf5c: if_icmpeq bf64
      // bf5f: goto bf6e
      // bf64: ldc_w "Class A ,  Game Car"
      // bf67: astore 27
      // bf69: goto bf6e
      // bf6e: aload 0
      // bf6f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bf72: aload 27
      // bf74: sipush 561
      // bf77: aload 0
      // bf78: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // bf7b: aload 27
      // bf7d: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // bf80: bipush 2
      // bf81: idiv
      // bf82: isub
      // bf83: sipush 367
      // bf86: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bf89: aload 0
      // bf8a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bf8d: new java/awt/Color
      // bf90: dup
      // bf91: bipush 0
      // bf92: bipush 0
      // bf93: bipush 0
      // bf94: invokespecial java/awt/Color.<init> (III)V
      // bf97: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // bf9a: aload 0
      // bf9b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bf9e: new java/awt/Font
      // bfa1: dup
      // bfa2: ldc_w "Arial"
      // bfa5: bipush 0
      // bfa6: bipush 12
      // bfa8: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // bfab: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // bfae: aload 0
      // bfaf: aload 0
      // bfb0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bfb3: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // bfb6: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // bfb9: aload 0
      // bfba: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bfbd: ldc_w "You already have this car."
      // bfc0: sipush 561
      // bfc3: aload 0
      // bfc4: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // bfc7: ldc_w "You already have this car."
      // bfca: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // bfcd: bipush 2
      // bfce: idiv
      // bfcf: isub
      // bfd0: sipush 395
      // bfd3: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // bfd6: goto c6c5
      // bfdb: aload 0
      // bfdc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bfdf: new java/awt/Font
      // bfe2: dup
      // bfe3: ldc_w "Arial"
      // bfe6: bipush 1
      // bfe7: bipush 12
      // bfe9: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // bfec: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // bfef: aload 0
      // bff0: aload 0
      // bff1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // bff4: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // bff7: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // bffa: aload 0
      // bffb: getfield Lobby.cd LCarDefine;
      // bffe: getfield CarDefine.createdby [Ljava/lang/String;
      // c001: aload 0
      // c002: getfield Lobby.forcar I
      // c005: bipush 16
      // c007: isub
      // c008: aaload
      // c009: aload 0
      // c00a: getfield Lobby.xt LxtGraphics;
      // c00d: getfield xtGraphics.nickname Ljava/lang/String;
      // c010: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // c013: ifeq c01b
      // c016: goto c1c1
      // c01b: aload 0
      // c01c: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c01f: new java/lang/StringBuilder
      // c022: dup
      // c023: invokespecial java/lang/StringBuilder.<init> ()V
      // c026: ldc_w "Created by :  "
      // c029: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c02c: aload 0
      // c02d: getfield Lobby.cd LCarDefine;
      // c030: getfield CarDefine.createdby [Ljava/lang/String;
      // c033: aload 0
      // c034: getfield Lobby.forcar I
      // c037: bipush 16
      // c039: isub
      // c03a: aaload
      // c03b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c03e: ldc ""
      // c040: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c043: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c046: sipush 561
      // c049: aload 0
      // c04a: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c04d: new java/lang/StringBuilder
      // c050: dup
      // c051: invokespecial java/lang/StringBuilder.<init> ()V
      // c054: ldc_w "Created by :  "
      // c057: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c05a: aload 0
      // c05b: getfield Lobby.cd LCarDefine;
      // c05e: getfield CarDefine.createdby [Ljava/lang/String;
      // c061: aload 0
      // c062: getfield Lobby.forcar I
      // c065: bipush 16
      // c067: isub
      // c068: aaload
      // c069: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c06c: ldc ""
      // c06e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c071: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c074: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c077: bipush 2
      // c078: idiv
      // c079: isub
      // c07a: sipush 347
      // c07d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c080: aload 0
      // c081: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c084: aload 0
      // c085: getfield Lobby.cd LCarDefine;
      // c088: getfield CarDefine.createdby [Ljava/lang/String;
      // c08b: aload 0
      // c08c: getfield Lobby.forcar I
      // c08f: bipush 16
      // c091: isub
      // c092: aaload
      // c093: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c096: istore 27
      // c098: sipush 561
      // c09b: aload 0
      // c09c: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c09f: new java/lang/StringBuilder
      // c0a2: dup
      // c0a3: invokespecial java/lang/StringBuilder.<init> ()V
      // c0a6: ldc_w "Created by :  "
      // c0a9: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0ac: aload 0
      // c0ad: getfield Lobby.cd LCarDefine;
      // c0b0: getfield CarDefine.createdby [Ljava/lang/String;
      // c0b3: aload 0
      // c0b4: getfield Lobby.forcar I
      // c0b7: bipush 16
      // c0b9: isub
      // c0ba: aaload
      // c0bb: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0be: ldc ""
      // c0c0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0c3: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c0c6: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c0c9: bipush 2
      // c0ca: idiv
      // c0cb: isub
      // c0cc: aload 0
      // c0cd: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c0d0: new java/lang/StringBuilder
      // c0d3: dup
      // c0d4: invokespecial java/lang/StringBuilder.<init> ()V
      // c0d7: ldc_w "Created by :  "
      // c0da: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0dd: aload 0
      // c0de: getfield Lobby.cd LCarDefine;
      // c0e1: getfield CarDefine.createdby [Ljava/lang/String;
      // c0e4: aload 0
      // c0e5: getfield Lobby.forcar I
      // c0e8: bipush 16
      // c0ea: isub
      // c0eb: aaload
      // c0ec: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0ef: ldc ""
      // c0f1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c0f4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c0f7: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c0fa: iadd
      // c0fb: iload 27
      // c0fd: isub
      // c0fe: istore 28
      // c100: aload 0
      // c101: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c104: iload 28
      // c106: sipush 349
      // c109: iload 28
      // c10b: iload 27
      // c10d: iadd
      // c10e: bipush 2
      // c10f: isub
      // c110: sipush 349
      // c113: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // c116: iload 1
      // c117: iload 28
      // c119: bipush 2
      // c11a: isub
      // c11b: if_icmpgt c123
      // c11e: goto c1bc
      // c123: iload 1
      // c124: iload 28
      // c126: iload 27
      // c128: iadd
      // c129: if_icmplt c131
      // c12c: goto c1bc
      // c131: iload 2
      // c132: sipush 334
      // c135: if_icmpgt c13d
      // c138: goto c1bc
      // c13d: iload 2
      // c13e: sipush 351
      // c141: if_icmplt c149
      // c144: goto c1bc
      // c149: iload 3
      // c14a: ifne c152
      // c14d: goto c1b3
      // c152: aload 0
      // c153: getfield Lobby.gb LGlobe;
      // c156: getfield Globe.proname Ljava/lang/String;
      // c159: aload 0
      // c15a: getfield Lobby.cd LCarDefine;
      // c15d: getfield CarDefine.createdby [Ljava/lang/String;
      // c160: aload 0
      // c161: getfield Lobby.forcar I
      // c164: bipush 16
      // c166: isub
      // c167: aaload
      // c168: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // c16b: ifeq c173
      // c16e: goto c196
      // c173: aload 0
      // c174: getfield Lobby.gb LGlobe;
      // c177: aload 0
      // c178: getfield Lobby.cd LCarDefine;
      // c17b: getfield CarDefine.createdby [Ljava/lang/String;
      // c17e: aload 0
      // c17f: getfield Lobby.forcar I
      // c182: bipush 16
      // c184: isub
      // c185: aaload
      // c186: putfield Globe.proname Ljava/lang/String;
      // c189: aload 0
      // c18a: getfield Lobby.gb LGlobe;
      // c18d: bipush 0
      // c18e: putfield Globe.loadedp Z
      // c191: goto c196
      // c196: aload 0
      // c197: getfield Lobby.gb LGlobe;
      // c19a: bipush 1
      // c19b: putfield Globe.tab I
      // c19e: aload 0
      // c19f: getfield Lobby.gb LGlobe;
      // c1a2: bipush 2
      // c1a3: putfield Globe.open I
      // c1a6: aload 0
      // c1a7: getfield Lobby.gb LGlobe;
      // c1aa: bipush 1
      // c1ab: putfield Globe.upo Z
      // c1ae: goto c1b3
      // c1b3: bipush 12
      // c1b5: istore 8
      // c1b7: goto c1bc
      // c1bc: goto c1de
      // c1c1: aload 0
      // c1c2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c1c5: ldc_w "Created by You"
      // c1c8: sipush 561
      // c1cb: aload 0
      // c1cc: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c1cf: ldc_w "Created by You"
      // c1d2: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c1d5: bipush 2
      // c1d6: idiv
      // c1d7: isub
      // c1d8: sipush 347
      // c1db: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c1de: aload 0
      // c1df: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c1e2: new java/awt/Color
      // c1e5: dup
      // c1e6: sipush 128
      // c1e9: bipush 73
      // c1eb: bipush 0
      // c1ec: invokespecial java/awt/Color.<init> (III)V
      // c1ef: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c1f2: ldc ""
      // c1f4: astore 27
      // c1f6: aload 0
      // c1f7: getfield Lobby.cd LCarDefine;
      // c1fa: getfield CarDefine.cclass [I
      // c1fd: aload 0
      // c1fe: getfield Lobby.forcar I
      // c201: iaload
      // c202: ifeq c20a
      // c205: goto c214
      // c20a: ldc_w "Class C ,  "
      // c20d: astore 27
      // c20f: goto c214
      // c214: aload 0
      // c215: getfield Lobby.cd LCarDefine;
      // c218: getfield CarDefine.cclass [I
      // c21b: aload 0
      // c21c: getfield Lobby.forcar I
      // c21f: iaload
      // c220: bipush 1
      // c221: if_icmpeq c229
      // c224: goto c233
      // c229: ldc_w "Class B & C ,  "
      // c22c: astore 27
      // c22e: goto c233
      // c233: aload 0
      // c234: getfield Lobby.cd LCarDefine;
      // c237: getfield CarDefine.cclass [I
      // c23a: aload 0
      // c23b: getfield Lobby.forcar I
      // c23e: iaload
      // c23f: bipush 2
      // c240: if_icmpeq c248
      // c243: goto c252
      // c248: ldc_w "Class B ,  "
      // c24b: astore 27
      // c24d: goto c252
      // c252: aload 0
      // c253: getfield Lobby.cd LCarDefine;
      // c256: getfield CarDefine.cclass [I
      // c259: aload 0
      // c25a: getfield Lobby.forcar I
      // c25d: iaload
      // c25e: bipush 3
      // c25f: if_icmpeq c267
      // c262: goto c271
      // c267: ldc_w "Class A & B ,  "
      // c26a: astore 27
      // c26c: goto c271
      // c271: aload 0
      // c272: getfield Lobby.cd LCarDefine;
      // c275: getfield CarDefine.cclass [I
      // c278: aload 0
      // c279: getfield Lobby.forcar I
      // c27c: iaload
      // c27d: bipush 4
      // c27e: if_icmpeq c286
      // c281: goto c290
      // c286: ldc_w "Class A ,  "
      // c289: astore 27
      // c28b: goto c290
      // c290: aload 0
      // c291: getfield Lobby.cd LCarDefine;
      // c294: getfield CarDefine.publish [I
      // c297: aload 0
      // c298: getfield Lobby.forcar I
      // c29b: bipush 16
      // c29d: isub
      // c29e: iaload
      // c29f: ifeq c2a7
      // c2a2: goto c2c3
      // c2a7: new java/lang/StringBuilder
      // c2aa: dup
      // c2ab: invokespecial java/lang/StringBuilder.<init> ()V
      // c2ae: aload 27
      // c2b0: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c2b3: ldc_w "Private Car"
      // c2b6: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c2b9: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c2bc: astore 27
      // c2be: goto c2c3
      // c2c3: aload 0
      // c2c4: getfield Lobby.cd LCarDefine;
      // c2c7: getfield CarDefine.publish [I
      // c2ca: aload 0
      // c2cb: getfield Lobby.forcar I
      // c2ce: bipush 16
      // c2d0: isub
      // c2d1: iaload
      // c2d2: bipush 1
      // c2d3: if_icmpeq c2db
      // c2d6: goto c30b
      // c2db: new java/lang/StringBuilder
      // c2de: dup
      // c2df: invokespecial java/lang/StringBuilder.<init> ()V
      // c2e2: aload 27
      // c2e4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c2e7: ldc_w "Public Car"
      // c2ea: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c2ed: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c2f0: astore 27
      // c2f2: aload 0
      // c2f3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c2f6: new java/awt/Color
      // c2f9: dup
      // c2fa: bipush 0
      // c2fb: bipush 64
      // c2fd: sipush 128
      // c300: invokespecial java/awt/Color.<init> (III)V
      // c303: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c306: goto c30b
      // c30b: aload 0
      // c30c: getfield Lobby.cd LCarDefine;
      // c30f: getfield CarDefine.publish [I
      // c312: aload 0
      // c313: getfield Lobby.forcar I
      // c316: bipush 16
      // c318: isub
      // c319: iaload
      // c31a: bipush 2
      // c31b: if_icmpeq c323
      // c31e: goto c353
      // c323: new java/lang/StringBuilder
      // c326: dup
      // c327: invokespecial java/lang/StringBuilder.<init> ()V
      // c32a: aload 27
      // c32c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c32f: ldc_w "Super Public Car"
      // c332: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c335: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c338: astore 27
      // c33a: aload 0
      // c33b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c33e: new java/awt/Color
      // c341: dup
      // c342: bipush 0
      // c343: bipush 64
      // c345: sipush 128
      // c348: invokespecial java/awt/Color.<init> (III)V
      // c34b: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c34e: goto c353
      // c353: aload 0
      // c354: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c357: aload 27
      // c359: sipush 561
      // c35c: aload 0
      // c35d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c360: aload 27
      // c362: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c365: bipush 2
      // c366: idiv
      // c367: isub
      // c368: sipush 367
      // c36b: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c36e: aload 0
      // c36f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c372: new java/awt/Color
      // c375: dup
      // c376: bipush 0
      // c377: bipush 0
      // c378: bipush 0
      // c379: invokespecial java/awt/Color.<init> (III)V
      // c37c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c37f: aload 0
      // c380: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c383: new java/awt/Font
      // c386: dup
      // c387: ldc_w "Arial"
      // c38a: bipush 0
      // c38b: bipush 12
      // c38d: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // c390: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // c393: aload 0
      // c394: aload 0
      // c395: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c398: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // c39b: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // c39e: aload 0
      // c39f: getfield Lobby.cd LCarDefine;
      // c3a2: getfield CarDefine.publish [I
      // c3a5: aload 0
      // c3a6: getfield Lobby.forcar I
      // c3a9: bipush 16
      // c3ab: isub
      // c3ac: iaload
      // c3ad: bipush 1
      // c3ae: if_icmpne c3b6
      // c3b1: goto c3ce
      // c3b6: aload 0
      // c3b7: getfield Lobby.cd LCarDefine;
      // c3ba: getfield CarDefine.publish [I
      // c3bd: aload 0
      // c3be: getfield Lobby.forcar I
      // c3c1: bipush 16
      // c3c3: isub
      // c3c4: iaload
      // c3c5: bipush 2
      // c3c6: if_icmpeq c3ce
      // c3c9: goto c6a8
      // c3ce: aload 0
      // c3cf: getfield Lobby.cd LCarDefine;
      // c3d2: getfield CarDefine.action I
      // c3d5: bipush -9
      // c3d7: if_icmpeq c3df
      // c3da: goto c401
      // c3df: aload 0
      // c3e0: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c3e3: ldc_w "Failed to add car!  Unknown error!"
      // c3e6: sipush 561
      // c3e9: aload 0
      // c3ea: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c3ed: ldc_w "Failed to add car!  Unknown error!"
      // c3f0: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c3f3: bipush 2
      // c3f4: idiv
      // c3f5: isub
      // c3f6: sipush 395
      // c3f9: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c3fc: goto c401
      // c401: aload 0
      // c402: getfield Lobby.cd LCarDefine;
      // c405: getfield CarDefine.action I
      // c408: bipush -8
      // c40a: if_icmpeq c412
      // c40d: goto c434
      // c412: aload 0
      // c413: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c416: ldc_w "Failed!  You already have 20 cars!"
      // c419: sipush 561
      // c41c: aload 0
      // c41d: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c420: ldc_w "Failed!  You already have 20 cars!"
      // c423: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c426: bipush 2
      // c427: idiv
      // c428: isub
      // c429: sipush 395
      // c42c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c42f: goto c434
      // c434: aload 0
      // c435: getfield Lobby.cd LCarDefine;
      // c438: getfield CarDefine.action I
      // c43b: bipush 7
      // c43d: if_icmpeq c445
      // c440: goto c4af
      // c445: aload 0
      // c446: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c449: new java/lang/StringBuilder
      // c44c: dup
      // c44d: invokespecial java/lang/StringBuilder.<init> ()V
      // c450: ldc ""
      // c452: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c455: aload 0
      // c456: getfield Lobby.cd LCarDefine;
      // c459: getfield CarDefine.names [Ljava/lang/String;
      // c45c: aload 0
      // c45d: getfield Lobby.cd LCarDefine;
      // c460: getfield CarDefine.ac I
      // c463: aaload
      // c464: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c467: ldc_w " has been added to your cars!"
      // c46a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c46d: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c470: sipush 561
      // c473: aload 0
      // c474: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c477: new java/lang/StringBuilder
      // c47a: dup
      // c47b: invokespecial java/lang/StringBuilder.<init> ()V
      // c47e: ldc ""
      // c480: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c483: aload 0
      // c484: getfield Lobby.cd LCarDefine;
      // c487: getfield CarDefine.names [Ljava/lang/String;
      // c48a: aload 0
      // c48b: getfield Lobby.cd LCarDefine;
      // c48e: getfield CarDefine.ac I
      // c491: aaload
      // c492: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c495: ldc_w " has been added to your cars!"
      // c498: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c49b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c49e: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c4a1: bipush 2
      // c4a2: idiv
      // c4a3: isub
      // c4a4: sipush 395
      // c4a7: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c4aa: goto c4af
      // c4af: aload 0
      // c4b0: getfield Lobby.cd LCarDefine;
      // c4b3: getfield CarDefine.action I
      // c4b6: bipush -7
      // c4b8: if_icmpeq c4c0
      // c4bb: goto c4e2
      // c4c0: aload 0
      // c4c1: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c4c4: ldc_w "You already have this car."
      // c4c7: sipush 561
      // c4ca: aload 0
      // c4cb: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c4ce: ldc_w "You already have this car."
      // c4d1: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c4d4: bipush 2
      // c4d5: idiv
      // c4d6: isub
      // c4d7: sipush 395
      // c4da: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c4dd: goto c4e2
      // c4e2: aload 0
      // c4e3: getfield Lobby.cd LCarDefine;
      // c4e6: getfield CarDefine.action I
      // c4e9: bipush 6
      // c4eb: if_icmpeq c4f3
      // c4ee: goto c515
      // c4f3: aload 0
      // c4f4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c4f7: ldc_w "Adding Car..."
      // c4fa: sipush 561
      // c4fd: aload 0
      // c4fe: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c501: ldc_w "Adding Car..."
      // c504: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c507: bipush 2
      // c508: idiv
      // c509: isub
      // c50a: sipush 395
      // c50d: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c510: goto c515
      // c515: aload 0
      // c516: getfield Lobby.cd LCarDefine;
      // c519: getfield CarDefine.action I
      // c51c: bipush -6
      // c51e: if_icmpeq c526
      // c521: goto c60d
      // c526: ldc_w "Upgrade to a full account to add custom cars!"
      // c529: astore 28
      // c52b: sipush 561
      // c52e: aload 0
      // c52f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c532: aload 28
      // c534: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c537: bipush 2
      // c538: idiv
      // c539: isub
      // c53a: istore 29
      // c53c: iload 29
      // c53e: aload 0
      // c53f: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c542: aload 28
      // c544: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c547: iadd
      // c548: istore 30
      // c54a: aload 0
      // c54b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c54e: aload 28
      // c550: iload 29
      // c552: sipush 395
      // c555: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c558: aload 0
      // c559: getfield Lobby.waitlink I
      // c55c: bipush -1
      // c55d: if_icmpne c565
      // c560: goto c57b
      // c565: aload 0
      // c566: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c569: iload 29
      // c56b: sipush 396
      // c56e: iload 30
      // c570: sipush 396
      // c573: invokevirtual java/awt/Graphics2D.drawLine (IIII)V
      // c576: goto c57b
      // c57b: iload 1
      // c57c: iload 29
      // c57e: if_icmpgt c586
      // c581: goto c5ed
      // c586: iload 1
      // c587: iload 30
      // c589: if_icmplt c591
      // c58c: goto c5ed
      // c591: iload 2
      // c592: sipush 384
      // c595: if_icmpgt c59d
      // c598: goto c5ed
      // c59d: iload 2
      // c59e: sipush 397
      // c5a1: if_icmplt c5a9
      // c5a4: goto c5ed
      // c5a9: aload 0
      // c5aa: getfield Lobby.waitlink I
      // c5ad: bipush -1
      // c5ae: if_icmpne c5b6
      // c5b1: goto c5bf
      // c5b6: bipush 12
      // c5b8: istore 8
      // c5ba: goto c5bf
      // c5bf: iload 3
      // c5c0: ifne c5c8
      // c5c3: goto c5ed
      // c5c8: aload 0
      // c5c9: getfield Lobby.waitlink I
      // c5cc: ifeq c5d4
      // c5cf: goto c5ed
      // c5d4: aload 0
      // c5d5: getfield Lobby.gs LGameSparker;
      // c5d8: aload 0
      // c5d9: getfield Lobby.xt LxtGraphics;
      // c5dc: getfield xtGraphics.nickname Ljava/lang/String;
      // c5df: bipush 1
      // c5e0: invokevirtual GameSparker.editlink (Ljava/lang/String;Z)V
      // c5e3: aload 0
      // c5e4: bipush -1
      // c5e5: putfield Lobby.waitlink I
      // c5e8: goto c5ed
      // c5ed: aload 0
      // c5ee: getfield Lobby.waitlink I
      // c5f1: ifgt c5f9
      // c5f4: goto c608
      // c5f9: aload 0
      // c5fa: dup
      // c5fb: getfield Lobby.waitlink I
      // c5fe: bipush 1
      // c5ff: isub
      // c600: putfield Lobby.waitlink I
      // c603: goto c608
      // c608: goto c60d
      // c60d: aload 0
      // c60e: getfield Lobby.cd LCarDefine;
      // c611: getfield CarDefine.action I
      // c614: ifeq c61c
      // c617: goto c6c5
      // c61c: aload 0
      // c61d: getfield Lobby.xt LxtGraphics;
      // c620: bipush 1
      // c621: aconst_null
      // c622: ldc_w " Add to My Cars "
      // c625: sipush 503
      // c628: sipush 375
      // c62b: iload 1
      // c62c: iload 2
      // c62d: iload 3
      // c62e: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // c631: ifne c639
      // c634: goto c6c5
      // c639: aload 0
      // c63a: getfield Lobby.xt LxtGraphics;
      // c63d: getfield xtGraphics.logged Z
      // c640: ifne c648
      // c643: goto c694
      // c648: aload 0
      // c649: getfield Lobby.cd LCarDefine;
      // c64c: getfield CarDefine.lastload I
      // c64f: bipush 2
      // c650: if_icmpeq c658
      // c653: goto c666
      // c658: aload 0
      // c659: getfield Lobby.forcar I
      // c65c: bipush 36
      // c65e: if_icmpge c666
      // c661: goto c686
      // c666: aload 0
      // c667: getfield Lobby.cd LCarDefine;
      // c66a: bipush 6
      // c66c: putfield CarDefine.action I
      // c66f: aload 0
      // c670: getfield Lobby.cd LCarDefine;
      // c673: aload 0
      // c674: getfield Lobby.forcar I
      // c677: putfield CarDefine.ac I
      // c67a: aload 0
      // c67b: getfield Lobby.cd LCarDefine;
      // c67e: invokevirtual CarDefine.sparkactionloader ()V
      // c681: goto c6c5
      // c686: aload 0
      // c687: getfield Lobby.cd LCarDefine;
      // c68a: bipush -7
      // c68c: putfield CarDefine.action I
      // c68f: goto c6c5
      // c694: aload 0
      // c695: getfield Lobby.cd LCarDefine;
      // c698: bipush -6
      // c69a: putfield CarDefine.action I
      // c69d: aload 0
      // c69e: bipush 20
      // c6a0: putfield Lobby.waitlink I
      // c6a3: goto c6c5
      // c6a8: aload 0
      // c6a9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c6ac: ldc_w "Private Car.  Cannot be added to account."
      // c6af: sipush 561
      // c6b2: aload 0
      // c6b3: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // c6b6: ldc_w "Private Car.  Cannot be added to account."
      // c6b9: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // c6bc: bipush 2
      // c6bd: idiv
      // c6be: isub
      // c6bf: sipush 395
      // c6c2: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // c6c5: aload 0
      // c6c6: getfield Lobby.xt LxtGraphics;
      // c6c9: bipush 1
      // c6ca: aconst_null
      // c6cb: ldc_w "X"
      // c6ce: sipush 682
      // c6d1: bipush 99
      // c6d3: iload 1
      // c6d4: iload 2
      // c6d5: iload 3
      // c6d6: invokevirtual xtGraphics.drawcarb (ZLjava/awt/Image;Ljava/lang/String;IIIIZ)Z
      // c6d9: ifne c6e1
      // c6dc: goto c6eb
      // c6e1: aload 0
      // c6e2: bipush -1
      // c6e3: putfield Lobby.dispcar I
      // c6e6: goto c6eb
      // c6eb: goto c6f5
      // c6f0: aload 0
      // c6f1: bipush -1
      // c6f2: putfield Lobby.dispcar I
      // c6f5: goto d101
      // c6fa: aload 0
      // c6fb: getfield Lobby.jflexo Z
      // c6fe: ifeq c706
      // c701: goto c717
      // c706: aload 0
      // c707: getfield Lobby.xt LxtGraphics;
      // c70a: invokevirtual xtGraphics.jflexo ()V
      // c70d: aload 0
      // c70e: bipush 1
      // c70f: putfield Lobby.jflexo Z
      // c712: goto c717
      // c717: aload 0
      // c718: bipush 0
      // c719: putfield Lobby.btn I
      // c71c: aload 0
      // c71d: getfield Lobby.gs LGameSparker;
      // c720: getfield GameSparker.cmsg Ljava/awt/TextField;
      // c723: invokevirtual java/awt/TextField.isShowing ()Z
      // c726: ifne c72e
      // c729: goto c744
      // c72e: aload 0
      // c72f: getfield Lobby.gs LGameSparker;
      // c732: getfield GameSparker.cmsg Ljava/awt/TextField;
      // c735: invokevirtual java/awt/TextField.hide ()V
      // c738: aload 0
      // c739: getfield Lobby.gs LGameSparker;
      // c73c: invokevirtual GameSparker.requestFocus ()V
      // c73f: goto c744
      // c744: aload 0
      // c745: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c748: aload 0
      // c749: sipush 255
      // c74c: sipush 255
      // c74f: sipush 255
      // c752: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // c755: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c758: aload 0
      // c759: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c75c: sipush 155
      // c75f: sipush 148
      // c762: sipush 490
      // c765: bipush 127
      // c767: bipush 20
      // c769: bipush 20
      // c76b: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // c76e: aload 0
      // c76f: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c772: new java/awt/Color
      // c775: dup
      // c776: bipush 0
      // c777: bipush 0
      // c778: bipush 0
      // c779: invokespecial java/awt/Color.<init> (III)V
      // c77c: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c77f: aload 0
      // c780: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c783: sipush 155
      // c786: sipush 148
      // c789: sipush 490
      // c78c: bipush 127
      // c78e: bipush 20
      // c790: bipush 20
      // c792: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // c795: aload 0
      // c796: getfield Lobby.ontyp I
      // c799: bipush 76
      // c79b: if_icmpne c7a3
      // c79e: goto cc9c
      // c7a3: ldc ""
      // c7a5: astore 9
      // c7a7: bipush 0
      // c7a8: istore 10
      // c7aa: aload 0
      // c7ab: getfield Lobby.ontyp I
      // c7ae: bipush 10
      // c7b0: if_icmpge c7b8
      // c7b3: goto c7eb
      // c7b8: bipush 10
      // c7ba: istore 10
      // c7bc: ldc_w "Custom Cars"
      // c7bf: astore 9
      // c7c1: aload 0
      // c7c2: getfield Lobby.ontyp I
      // c7c5: bipush 10
      // c7c7: if_icmpgt c7cf
      // c7ca: goto c7eb
      // c7cf: new java/lang/StringBuilder
      // c7d2: dup
      // c7d3: invokespecial java/lang/StringBuilder.<init> ()V
      // c7d6: aload 9
      // c7d8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c7db: ldc_w ", "
      // c7de: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c7e1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c7e4: astore 9
      // c7e6: goto c7eb
      // c7eb: aload 0
      // c7ec: getfield Lobby.ontyp I
      // c7ef: bipush 20
      // c7f1: if_icmpge c7f9
      // c7f4: goto c82c
      // c7f9: bipush 20
      // c7fb: istore 10
      // c7fd: ldc_w "Game Cars"
      // c800: astore 9
      // c802: aload 0
      // c803: getfield Lobby.ontyp I
      // c806: bipush 20
      // c808: if_icmpgt c810
      // c80b: goto c82c
      // c810: new java/lang/StringBuilder
      // c813: dup
      // c814: invokespecial java/lang/StringBuilder.<init> ()V
      // c817: aload 9
      // c819: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c81c: ldc_w ", "
      // c81f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c822: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c825: astore 9
      // c827: goto c82c
      // c82c: aload 0
      // c82d: getfield Lobby.ontyp I
      // c830: bipush 30
      // c832: if_icmpge c83a
      // c835: goto c86d
      // c83a: bipush 30
      // c83c: istore 10
      // c83e: ldc_w "Clan Cars"
      // c841: astore 9
      // c843: aload 0
      // c844: getfield Lobby.ontyp I
      // c847: bipush 30
      // c849: if_icmpgt c851
      // c84c: goto c86d
      // c851: new java/lang/StringBuilder
      // c854: dup
      // c855: invokespecial java/lang/StringBuilder.<init> ()V
      // c858: aload 9
      // c85a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c85d: ldc_w ", "
      // c860: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c863: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c866: astore 9
      // c868: goto c86d
      // c86d: aload 0
      // c86e: getfield Lobby.ontyp I
      // c871: iload 10
      // c873: isub
      // c874: bipush 1
      // c875: if_icmpeq c87d
      // c878: goto c899
      // c87d: new java/lang/StringBuilder
      // c880: dup
      // c881: invokespecial java/lang/StringBuilder.<init> ()V
      // c884: aload 9
      // c886: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c889: ldc_w "Class C"
      // c88c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c88f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c892: astore 9
      // c894: goto c899
      // c899: aload 0
      // c89a: getfield Lobby.ontyp I
      // c89d: iload 10
      // c89f: isub
      // c8a0: bipush 2
      // c8a1: if_icmpeq c8a9
      // c8a4: goto c8c5
      // c8a9: new java/lang/StringBuilder
      // c8ac: dup
      // c8ad: invokespecial java/lang/StringBuilder.<init> ()V
      // c8b0: aload 9
      // c8b2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c8b5: ldc_w "Class B & C"
      // c8b8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c8bb: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c8be: astore 9
      // c8c0: goto c8c5
      // c8c5: aload 0
      // c8c6: getfield Lobby.ontyp I
      // c8c9: iload 10
      // c8cb: isub
      // c8cc: bipush 3
      // c8cd: if_icmpeq c8d5
      // c8d0: goto c8f1
      // c8d5: new java/lang/StringBuilder
      // c8d8: dup
      // c8d9: invokespecial java/lang/StringBuilder.<init> ()V
      // c8dc: aload 9
      // c8de: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c8e1: ldc_w "Class B"
      // c8e4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c8e7: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c8ea: astore 9
      // c8ec: goto c8f1
      // c8f1: aload 0
      // c8f2: getfield Lobby.ontyp I
      // c8f5: iload 10
      // c8f7: isub
      // c8f8: bipush 4
      // c8f9: if_icmpeq c901
      // c8fc: goto c91d
      // c901: new java/lang/StringBuilder
      // c904: dup
      // c905: invokespecial java/lang/StringBuilder.<init> ()V
      // c908: aload 9
      // c90a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c90d: ldc_w "Class A & B"
      // c910: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c913: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c916: astore 9
      // c918: goto c91d
      // c91d: aload 0
      // c91e: getfield Lobby.ontyp I
      // c921: iload 10
      // c923: isub
      // c924: bipush 5
      // c925: if_icmpeq c92d
      // c928: goto c949
      // c92d: new java/lang/StringBuilder
      // c930: dup
      // c931: invokespecial java/lang/StringBuilder.<init> ()V
      // c934: aload 9
      // c936: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c939: ldc_w "Class A"
      // c93c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c93f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c942: astore 9
      // c944: goto c949
      // c949: aload 0
      // c94a: getfield Lobby.ontyp I
      // c94d: bipush -2
      // c94f: if_icmple c957
      // c952: goto c9cb
      // c957: aload 0
      // c958: getfield Lobby.ontyp I
      // c95b: bipush 2
      // c95c: iadd
      // c95d: invokestatic java/lang/Math.abs (I)I
      // c960: bipush 13
      // c962: if_icmpeq c96a
      // c965: goto c99b
      // c96a: new java/lang/StringBuilder
      // c96d: dup
      // c96e: invokespecial java/lang/StringBuilder.<init> ()V
      // c971: ldc_w " "
      // c974: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c977: aload 0
      // c978: getfield Lobby.cd LCarDefine;
      // c97b: getfield CarDefine.names [Ljava/lang/String;
      // c97e: aload 0
      // c97f: getfield Lobby.ontyp I
      // c982: bipush 2
      // c983: iadd
      // c984: invokestatic java/lang/Math.abs (I)I
      // c987: aaload
      // c988: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c98b: ldc_w "  Game"
      // c98e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c991: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c994: astore 9
      // c996: goto c9cb
      // c99b: new java/lang/StringBuilder
      // c99e: dup
      // c99f: invokespecial java/lang/StringBuilder.<init> ()V
      // c9a2: ldc ""
      // c9a4: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c9a7: aload 0
      // c9a8: getfield Lobby.cd LCarDefine;
      // c9ab: getfield CarDefine.names [Ljava/lang/String;
      // c9ae: aload 0
      // c9af: getfield Lobby.ontyp I
      // c9b2: bipush 2
      // c9b3: iadd
      // c9b4: invokestatic java/lang/Math.abs (I)I
      // c9b7: aaload
      // c9b8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c9bb: ldc_w " Game"
      // c9be: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // c9c1: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // c9c4: astore 9
      // c9c6: goto c9cb
      // c9cb: aload 0
      // c9cc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c9cf: new java/awt/Color
      // c9d2: dup
      // c9d3: bipush 0
      // c9d4: bipush 0
      // c9d5: bipush 0
      // c9d6: invokespecial java/awt/Color.<init> (III)V
      // c9d9: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // c9dc: aload 0
      // c9dd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c9e0: new java/awt/Font
      // c9e3: dup
      // c9e4: ldc_w "Arial"
      // c9e7: bipush 1
      // c9e8: bipush 13
      // c9ea: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // c9ed: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // c9f0: aload 0
      // c9f1: aload 0
      // c9f2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c9f5: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // c9f8: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // c9fb: aload 0
      // c9fc: getfield Lobby.rd Ljava/awt/Graphics2D;
      // c9ff: new java/lang/StringBuilder
      // ca02: dup
      // ca03: invokespecial java/lang/StringBuilder.<init> ()V
      // ca06: ldc_w ": :   "
      // ca09: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca0c: aload 9
      // ca0e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca11: ldc_w "   : :"
      // ca14: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca17: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ca1a: sipush 400
      // ca1d: aload 0
      // ca1e: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ca21: new java/lang/StringBuilder
      // ca24: dup
      // ca25: invokespecial java/lang/StringBuilder.<init> ()V
      // ca28: ldc_w ": :   "
      // ca2b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca2e: aload 9
      // ca30: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca33: ldc_w "   : :"
      // ca36: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // ca39: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // ca3c: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ca3f: bipush 2
      // ca40: idiv
      // ca41: isub
      // ca42: sipush 175
      // ca45: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // ca48: ldc_w "a"
      // ca4b: astore 9
      // ca4d: aload 0
      // ca4e: getfield Lobby.ontyp I
      // ca51: iload 10
      // ca53: isub
      // ca54: bipush 1
      // ca55: if_icmpeq ca5d
      // ca58: goto ca67
      // ca5d: ldc_w "a class C"
      // ca60: astore 9
      // ca62: goto ca67
      // ca67: aload 0
      // ca68: getfield Lobby.ontyp I
      // ca6b: iload 10
      // ca6d: isub
      // ca6e: bipush 2
      // ca6f: if_icmpeq ca77
      // ca72: goto ca81
      // ca77: ldc_w "a class B or C"
      // ca7a: astore 9
      // ca7c: goto ca81
      // ca81: aload 0
      // ca82: getfield Lobby.ontyp I
      // ca85: iload 10
      // ca87: isub
      // ca88: bipush 3
      // ca89: if_icmpeq ca91
      // ca8c: goto ca9b
      // ca91: ldc_w "a class B"
      // ca94: astore 9
      // ca96: goto ca9b
      // ca9b: aload 0
      // ca9c: getfield Lobby.ontyp I
      // ca9f: iload 10
      // caa1: isub
      // caa2: bipush 4
      // caa3: if_icmpeq caab
      // caa6: goto cab5
      // caab: ldc_w "a class A or B"
      // caae: astore 9
      // cab0: goto cab5
      // cab5: aload 0
      // cab6: getfield Lobby.ontyp I
      // cab9: iload 10
      // cabb: isub
      // cabc: bipush 5
      // cabd: if_icmpeq cac5
      // cac0: goto cacf
      // cac5: ldc_w "a class A"
      // cac8: astore 9
      // caca: goto cacf
      // cacf: iload 10
      // cad1: ifeq cad9
      // cad4: goto caf5
      // cad9: new java/lang/StringBuilder
      // cadc: dup
      // cadd: invokespecial java/lang/StringBuilder.<init> ()V
      // cae0: aload 9
      // cae2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cae5: ldc_w " car"
      // cae8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // caeb: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // caee: astore 9
      // caf0: goto caf5
      // caf5: iload 10
      // caf7: bipush 10
      // caf9: if_icmpeq cb01
      // cafc: goto cb1d
      // cb01: new java/lang/StringBuilder
      // cb04: dup
      // cb05: invokespecial java/lang/StringBuilder.<init> ()V
      // cb08: aload 9
      // cb0a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb0d: ldc_w " custom car"
      // cb10: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb13: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cb16: astore 9
      // cb18: goto cb1d
      // cb1d: iload 10
      // cb1f: bipush 20
      // cb21: if_icmpeq cb29
      // cb24: goto cb45
      // cb29: new java/lang/StringBuilder
      // cb2c: dup
      // cb2d: invokespecial java/lang/StringBuilder.<init> ()V
      // cb30: aload 9
      // cb32: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb35: ldc_w " game car"
      // cb38: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb3b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cb3e: astore 9
      // cb40: goto cb45
      // cb45: iload 10
      // cb47: bipush 30
      // cb49: if_icmpeq cb51
      // cb4c: goto cb6d
      // cb51: new java/lang/StringBuilder
      // cb54: dup
      // cb55: invokespecial java/lang/StringBuilder.<init> ()V
      // cb58: aload 9
      // cb5a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb5d: ldc_w " clan car"
      // cb60: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb63: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cb66: astore 9
      // cb68: goto cb6d
      // cb6d: aload 0
      // cb6e: getfield Lobby.ontyp I
      // cb71: bipush -2
      // cb73: if_icmple cb7b
      // cb76: goto cbee
      // cb7b: aload 0
      // cb7c: getfield Lobby.ontyp I
      // cb7f: bipush 2
      // cb80: iadd
      // cb81: invokestatic java/lang/Math.abs (I)I
      // cb84: bipush 13
      // cb86: if_icmpeq cb8e
      // cb89: goto cbbf
      // cb8e: new java/lang/StringBuilder
      // cb91: dup
      // cb92: invokespecial java/lang/StringBuilder.<init> ()V
      // cb95: ldc_w " "
      // cb98: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cb9b: aload 0
      // cb9c: getfield Lobby.cd LCarDefine;
      // cb9f: getfield CarDefine.names [Ljava/lang/String;
      // cba2: aload 0
      // cba3: getfield Lobby.ontyp I
      // cba6: bipush 2
      // cba7: iadd
      // cba8: invokestatic java/lang/Math.abs (I)I
      // cbab: aaload
      // cbac: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbaf: ldc_w " "
      // cbb2: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbb5: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cbb8: astore 9
      // cbba: goto cbee
      // cbbf: new java/lang/StringBuilder
      // cbc2: dup
      // cbc3: invokespecial java/lang/StringBuilder.<init> ()V
      // cbc6: ldc ""
      // cbc8: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbcb: aload 0
      // cbcc: getfield Lobby.cd LCarDefine;
      // cbcf: getfield CarDefine.names [Ljava/lang/String;
      // cbd2: aload 0
      // cbd3: getfield Lobby.ontyp I
      // cbd6: bipush 2
      // cbd7: iadd
      // cbd8: invokestatic java/lang/Math.abs (I)I
      // cbdb: aaload
      // cbdc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbdf: ldc ""
      // cbe1: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbe4: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cbe7: astore 9
      // cbe9: goto cbee
      // cbee: aload 0
      // cbef: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cbf2: new java/lang/StringBuilder
      // cbf5: dup
      // cbf6: invokespecial java/lang/StringBuilder.<init> ()V
      // cbf9: ldc_w "To join this game you need to have "
      // cbfc: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cbff: aload 9
      // cc01: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc04: ldc_w "!"
      // cc07: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc0a: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cc0d: sipush 400
      // cc10: aload 0
      // cc11: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // cc14: new java/lang/StringBuilder
      // cc17: dup
      // cc18: invokespecial java/lang/StringBuilder.<init> ()V
      // cc1b: ldc_w "To join this game you need to have "
      // cc1e: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc21: aload 9
      // cc23: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc26: ldc_w "!"
      // cc29: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc2c: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cc2f: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // cc32: bipush 2
      // cc33: idiv
      // cc34: isub
      // cc35: sipush 206
      // cc38: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cc3b: aload 0
      // cc3c: new java/lang/StringBuilder
      // cc3f: dup
      // cc40: invokespecial java/lang/StringBuilder.<init> ()V
      // cc43: ldc_w "  Get "
      // cc46: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc49: aload 9
      // cc4b: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc4e: ldc_w " now  "
      // cc51: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // cc54: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // cc57: sipush 400
      // cc5a: sipush 247
      // cc5d: bipush 0
      // cc5e: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // cc61: aload 0
      // cc62: ldc_w "  Cancel X  "
      // cc65: sipush 593
      // cc68: sipush 259
      // cc6b: bipush 2
      // cc6c: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // cc6f: aload 0
      // cc70: getfield Lobby.gb LGlobe;
      // cc73: getfield Globe.open I
      // cc76: ifgt cc7e
      // cc79: goto cc97
      // cc7e: aload 0
      // cc7f: getfield Lobby.gb LGlobe;
      // cc82: getfield Globe.upo Z
      // cc85: ifne cc8d
      // cc88: goto cc97
      // cc8d: aload 0
      // cc8e: bipush -1
      // cc8f: putfield Lobby.onjoin I
      // cc92: goto cc97
      // cc97: goto d101
      // cc9c: aload 0
      // cc9d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cca0: new java/awt/Color
      // cca3: dup
      // cca4: bipush 0
      // cca5: bipush 0
      // cca6: bipush 0
      // cca7: invokespecial java/awt/Color.<init> (III)V
      // ccaa: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // ccad: aload 0
      // ccae: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ccb1: new java/awt/Font
      // ccb4: dup
      // ccb5: ldc_w "Arial"
      // ccb8: bipush 1
      // ccb9: bipush 13
      // ccbb: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // ccbe: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // ccc1: aload 0
      // ccc2: aload 0
      // ccc3: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ccc6: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // ccc9: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // cccc: aload 0
      // cccd: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ccd0: ldc_w ": :   Custom Stage   : :"
      // ccd3: sipush 400
      // ccd6: aload 0
      // ccd7: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ccda: ldc_w ": :   Custom Stage   : :"
      // ccdd: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // cce0: bipush 2
      // cce1: idiv
      // cce2: isub
      // cce3: sipush 175
      // cce6: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cce9: aload 0
      // ccea: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cced: ldc_w "You need to upgrade to a full account to join this game!"
      // ccf0: sipush 400
      // ccf3: aload 0
      // ccf4: getfield Lobby.ftm Ljava/awt/FontMetrics;
      // ccf7: ldc_w "You need to upgrade to a full account to join this game!"
      // ccfa: invokevirtual java/awt/FontMetrics.stringWidth (Ljava/lang/String;)I
      // ccfd: bipush 2
      // ccfe: idiv
      // ccff: isub
      // cd00: sipush 202
      // cd03: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cd06: aload 0
      // cd07: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cd0a: aload 0
      // cd0b: sipush 200
      // cd0e: sipush 200
      // cd11: sipush 200
      // cd14: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // cd17: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cd1a: aload 0
      // cd1b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cd1e: sipush 310
      // cd21: sipush 215
      // cd24: sipush 180
      // cd27: bipush 50
      // cd29: bipush 20
      // cd2b: bipush 20
      // cd2d: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // cd30: aload 0
      // cd31: aload 0
      // cd32: getfield Lobby.xt LxtGraphics;
      // cd35: getfield xtGraphics.upgrade Ljava/awt/Image;
      // cd38: sipush 400
      // cd3b: sipush 240
      // cd3e: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // cd41: aload 0
      // cd42: ldc_w "  Cancel X  "
      // cd45: sipush 593
      // cd48: sipush 259
      // cd4b: bipush 2
      // cd4c: invokevirtual Lobby.stringbutton (Ljava/lang/String;III)V
      // cd4f: goto d101
      // cd54: aload 0
      // cd55: getfield Lobby.xt LxtGraphics;
      // cd58: bipush 3
      // cd59: invokevirtual xtGraphics.mainbg (I)V
      // cd5c: aload 0
      // cd5d: bipush 0
      // cd5e: putfield Lobby.btn I
      // cd61: aload 0
      // cd62: getfield Lobby.gs LGameSparker;
      // cd65: getfield GameSparker.cmsg Ljava/awt/TextField;
      // cd68: invokevirtual java/awt/TextField.isShowing ()Z
      // cd6b: ifne cd73
      // cd6e: goto cd89
      // cd73: aload 0
      // cd74: getfield Lobby.gs LGameSparker;
      // cd77: getfield GameSparker.cmsg Ljava/awt/TextField;
      // cd7a: invokevirtual java/awt/TextField.hide ()V
      // cd7d: aload 0
      // cd7e: getfield Lobby.gs LGameSparker;
      // cd81: invokevirtual GameSparker.requestFocus ()V
      // cd84: goto cd89
      // cd89: aload 0
      // cd8a: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cd8d: bipush 3
      // cd8e: ldc_w 0.2
      // cd91: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // cd94: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // cd97: aload 0
      // cd98: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cd9b: aload 0
      // cd9c: getfield Lobby.xt LxtGraphics;
      // cd9f: getfield xtGraphics.bggo Ljava/awt/Image;
      // cda2: bipush 0
      // cda3: bipush 0
      // cda4: aconst_null
      // cda5: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // cda8: pop
      // cda9: aload 0
      // cdaa: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cdad: bipush 3
      // cdae: fconst_1
      // cdaf: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // cdb2: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // cdb5: aload 0
      // cdb6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cdb9: new java/awt/Color
      // cdbc: dup
      // cdbd: bipush 0
      // cdbe: bipush 0
      // cdbf: bipush 0
      // cdc0: invokespecial java/awt/Color.<init> (III)V
      // cdc3: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cdc6: aload 0
      // cdc7: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cdca: bipush 65
      // cdcc: sipush 425
      // cdcf: sipush 670
      // cdd2: bipush 25
      // cdd4: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // cdd7: aload 0
      // cdd8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cddb: bipush 0
      // cddc: bipush 0
      // cddd: bipush 65
      // cddf: sipush 450
      // cde2: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // cde5: aload 0
      // cde6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cde9: sipush 735
      // cdec: bipush 0
      // cded: bipush 65
      // cdef: sipush 450
      // cdf2: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // cdf5: aload 0
      // cdf6: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cdf9: bipush 65
      // cdfb: bipush 0
      // cdfc: sipush 670
      // cdff: bipush 25
      // ce01: invokevirtual java/awt/Graphics2D.fillRect (IIII)V
      // ce04: fconst_1
      // ce05: aload 0
      // ce06: getfield Lobby.lg LLogin;
      // ce09: getfield Login.flipo I
      // ce0c: bipush 10
      // ce0e: isub
      // ce0f: i2f
      // ce10: ldc_w 80.0
      // ce13: fdiv
      // ce14: fsub
      // ce15: fstore 9
      // ce17: fload 9
      // ce19: fconst_1
      // ce1a: fcmpl
      // ce1b: ifgt ce23
      // ce1e: goto ce2b
      // ce23: fconst_1
      // ce24: fstore 9
      // ce26: goto ce2b
      // ce2b: fload 9
      // ce2d: fconst_0
      // ce2e: fcmpg
      // ce2f: iflt ce37
      // ce32: goto ce3f
      // ce37: fconst_0
      // ce38: fstore 9
      // ce3a: goto ce3f
      // ce3f: aload 0
      // ce40: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ce43: bipush 3
      // ce44: fload 9
      // ce46: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // ce49: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // ce4c: aload 0
      // ce4d: getfield Lobby.lg LLogin;
      // ce50: getfield Login.flipo I
      // ce53: bipush 10
      // ce55: if_icmpgt ce5d
      // ce58: goto ce90
      // ce5d: aload 0
      // ce5e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ce61: aload 0
      // ce62: getfield Lobby.xt LxtGraphics;
      // ce65: getfield xtGraphics.logomadnes Ljava/awt/Image;
      // ce68: bipush 97
      // ce6a: ldc2_w 2.0
      // ce6d: invokestatic java/lang/Math.random ()D
      // ce70: ldc2_w 4.0
      // ce73: dmul
      // ce74: dsub
      // ce75: d2i
      // ce76: iadd
      // ce77: bipush 36
      // ce79: ldc2_w 2.0
      // ce7c: invokestatic java/lang/Math.random ()D
      // ce7f: ldc2_w 4.0
      // ce82: dmul
      // ce83: dsub
      // ce84: d2i
      // ce85: iadd
      // ce86: aconst_null
      // ce87: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // ce8a: pop
      // ce8b: goto cea4
      // ce90: aload 0
      // ce91: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ce94: aload 0
      // ce95: getfield Lobby.xt LxtGraphics;
      // ce98: getfield xtGraphics.logomadnes Ljava/awt/Image;
      // ce9b: bipush 97
      // ce9d: bipush 36
      // ce9f: aconst_null
      // cea0: invokevirtual java/awt/Graphics2D.drawImage (Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
      // cea3: pop
      // cea4: aload 0
      // cea5: getfield Lobby.lg LLogin;
      // cea8: dup
      // cea9: getfield Login.flipo I
      // ceac: bipush 1
      // cead: iadd
      // ceae: putfield Login.flipo I
      // ceb1: aload 0
      // ceb2: getfield Lobby.lg LLogin;
      // ceb5: getfield Login.flipo I
      // ceb8: bipush 50
      // ceba: if_icmpgt cec2
      // cebd: goto cecf
      // cec2: aload 0
      // cec3: getfield Lobby.lg LLogin;
      // cec6: bipush 0
      // cec7: putfield Login.flipo I
      // ceca: goto cecf
      // cecf: bipush 30
      // ced1: istore 10
      // ced3: aload 0
      // ced4: getfield Lobby.rd Ljava/awt/Graphics2D;
      // ced7: bipush 3
      // ced8: ldc_w 0.25
      // cedb: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // cede: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // cee1: aload 0
      // cee2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cee5: new java/awt/Color
      // cee8: dup
      // cee9: sipush 203
      // ceec: sipush 227
      // ceef: sipush 253
      // cef2: invokespecial java/awt/Color.<init> (III)V
      // cef5: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cef8: aload 0
      // cef9: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cefc: bipush 115
      // cefe: bipush 57
      // cf00: iload 10
      // cf02: iadd
      // cf03: sipush 570
      // cf06: sipush 307
      // cf09: bipush 20
      // cf0b: bipush 20
      // cf0d: invokevirtual java/awt/Graphics2D.fillRoundRect (IIIIII)V
      // cf10: aload 0
      // cf11: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf14: bipush 3
      // cf15: fconst_1
      // cf16: invokestatic java/awt/AlphaComposite.getInstance (IF)Ljava/awt/AlphaComposite;
      // cf19: invokevirtual java/awt/Graphics2D.setComposite (Ljava/awt/Composite;)V
      // cf1c: aload 0
      // cf1d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf20: aload 0
      // cf21: bipush 90
      // cf23: bipush 90
      // cf25: bipush 90
      // cf27: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // cf2a: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cf2d: aload 0
      // cf2e: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf31: bipush 115
      // cf33: bipush 57
      // cf35: iload 10
      // cf37: iadd
      // cf38: sipush 570
      // cf3b: sipush 307
      // cf3e: bipush 20
      // cf40: bipush 20
      // cf42: invokevirtual java/awt/Graphics2D.drawRoundRect (IIIIII)V
      // cf45: aload 0
      // cf46: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf49: new java/awt/Font
      // cf4c: dup
      // cf4d: ldc_w "Arial"
      // cf50: bipush 1
      // cf51: bipush 13
      // cf53: invokespecial java/awt/Font.<init> (Ljava/lang/String;II)V
      // cf56: invokevirtual java/awt/Graphics2D.setFont (Ljava/awt/Font;)V
      // cf59: aload 0
      // cf5a: aload 0
      // cf5b: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf5e: invokevirtual java/awt/Graphics2D.getFontMetrics ()Ljava/awt/FontMetrics;
      // cf61: putfield Lobby.ftm Ljava/awt/FontMetrics;
      // cf64: aload 0
      // cf65: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf68: aload 0
      // cf69: bipush 0
      // cf6a: bipush 0
      // cf6b: bipush 0
      // cf6c: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // cf6f: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cf72: aload 0
      // cf73: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf76: ldc_w "You are allowed 5 multiplayer turns per day to try the game with your trial account."
      // cf79: sipush 135
      // cf7c: bipush 85
      // cf7e: iload 10
      // cf80: iadd
      // cf81: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cf84: aload 0
      // cf85: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cf88: ldc_w "Upgrade your account to a full account to purchase and play the multiplayer game."
      // cf8b: sipush 135
      // cf8e: bipush 105
      // cf90: iload 10
      // cf92: iadd
      // cf93: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cf96: aload 0
      // cf97: aload 0
      // cf98: getfield Lobby.xt LxtGraphics;
      // cf9b: getfield xtGraphics.upgrade Ljava/awt/Image;
      // cf9e: sipush 400
      // cfa1: sipush 130
      // cfa4: iload 10
      // cfa6: iadd
      // cfa7: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // cfaa: aload 0
      // cfab: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cfae: new java/awt/Color
      // cfb1: dup
      // cfb2: bipush 30
      // cfb4: bipush 70
      // cfb6: bipush 110
      // cfb8: invokespecial java/awt/Color.<init> (III)V
      // cfbb: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // cfbe: aload 0
      // cfbf: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cfc2: ldc_w "You can upgrade your account by just sharing the game & posting about it online!"
      // cfc5: sipush 135
      // cfc8: sipush 165
      // cfcb: iload 10
      // cfcd: iadd
      // cfce: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cfd1: aload 0
      // cfd2: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cfd5: ldc_w "Click 'Upgrade' for more details."
      // cfd8: sipush 135
      // cfdb: sipush 185
      // cfde: iload 10
      // cfe0: iadd
      // cfe1: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cfe4: aload 0
      // cfe5: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cfe8: ldc_w "Or try the multiplayer again tomorrow."
      // cfeb: sipush 135
      // cfee: sipush 205
      // cff1: iload 10
      // cff3: iadd
      // cff4: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // cff7: aload 0
      // cff8: getfield Lobby.rd Ljava/awt/Graphics2D;
      // cffb: aload 0
      // cffc: bipush 0
      // cffd: bipush 0
      // cffe: bipush 0
      // cfff: invokevirtual Lobby.color2k (III)Ljava/awt/Color;
      // d002: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // d005: aload 0
      // d006: getfield Lobby.rd Ljava/awt/Graphics2D;
      // d009: ldc_w "For now to preview and try the multiplayer more, with your trial account you can:"
      // d00c: sipush 135
      // d00f: sipush 245
      // d012: iload 10
      // d014: iadd
      // d015: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // d018: aload 0
      // d019: getfield Lobby.rd Ljava/awt/Graphics2D;
      // d01c: new java/awt/Color
      // d01f: dup
      // d020: bipush 30
      // d022: bipush 70
      // d024: bipush 110
      // d026: invokespecial java/awt/Color.<init> (III)V
      // d029: invokevirtual java/awt/Graphics2D.setColor (Ljava/awt/Color;)V
      // d02c: aload 0
      // d02d: getfield Lobby.rd Ljava/awt/Graphics2D;
      // d030: ldc_w "-  Watch online multiplayer games."
      // d033: sipush 135
      // d036: sipush 265
      // d039: iload 10
      // d03b: iadd
      // d03c: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // d03f: aload 0
      // d040: getfield Lobby.rd Ljava/awt/Graphics2D;
      // d043: ldc_w "-  Access the multiplayer dome."
      // d046: sipush 135
      // d049: sipush 285
      // d04c: iload 10
      // d04e: iadd
      // d04f: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // d052: aload 0
      // d053: getfield Lobby.rd Ljava/awt/Graphics2D;
      // d056: ldc_w "-  Play LAN multiplayer games (unlimitedly)."
      // d059: sipush 135
      // d05c: sipush 305
      // d05f: iload 10
      // d061: iadd
      // d062: invokevirtual java/awt/Graphics2D.drawString (Ljava/lang/String;II)V
      // d065: aload 0
      // d066: aload 0
      // d067: getfield Lobby.xt LxtGraphics;
      // d06a: getfield xtGraphics.exit Ljava/awt/Image;
      // d06d: sipush 400
      // d070: sipush 336
      // d073: iload 10
      // d075: iadd
      // d076: invokevirtual Lobby.drawSbutton (Ljava/awt/Image;II)V
      // d079: goto d101
      // d07e: aload 0
      // d07f: getfield Lobby.xt LxtGraphics;
      // d082: invokevirtual xtGraphics.drawWarning ()V
      // d085: aload 0
      // d086: getfield Lobby.gs LGameSparker;
      // d089: getfield GameSparker.cmsg Ljava/awt/TextField;
      // d08c: invokevirtual java/awt/TextField.isShowing ()Z
      // d08f: ifne d097
      // d092: goto d0ad
      // d097: aload 0
      // d098: getfield Lobby.gs LGameSparker;
      // d09b: getfield GameSparker.cmsg Ljava/awt/TextField;
      // d09e: invokevirtual java/awt/TextField.hide ()V
      // d0a1: aload 0
      // d0a2: getfield Lobby.gs LGameSparker;
      // d0a5: invokevirtual GameSparker.requestFocus ()V
      // d0a8: goto d0ad
      // d0ad: aload 0
      // d0ae: getfield Lobby.xt LxtGraphics;
      // d0b1: getfield xtGraphics.warning I
      // d0b4: sipush 220
      // d0b7: if_icmpgt d0bf
      // d0ba: goto d0f4
      // d0bf: aload 0
      // d0c0: bipush 0
      // d0c1: putfield Lobby.conon I
      // d0c4: aload 0
      // d0c5: getfield Lobby.socket Ljava/net/Socket;
      // d0c8: invokevirtual java/net/Socket.close ()V
      // d0cb: aload 0
      // d0cc: aconst_null
      // d0cd: putfield Lobby.socket Ljava/net/Socket;
      // d0d0: aload 0
      // d0d1: getfield Lobby.din Ljava/io/BufferedReader;
      // d0d4: invokevirtual java/io/BufferedReader.close ()V
      // d0d7: aload 0
      // d0d8: aconst_null
      // d0d9: putfield Lobby.din Ljava/io/BufferedReader;
      // d0dc: aload 0
      // d0dd: getfield Lobby.dout Ljava/io/PrintWriter;
      // d0e0: invokevirtual java/io/PrintWriter.close ()V
      // d0e3: aload 0
      // d0e4: aconst_null
      // d0e5: putfield Lobby.dout Ljava/io/PrintWriter;
      // d0e8: goto d0f4
      // d0ed: astore 9
      // d0ef: goto d0f4
      // d0f4: aload 0
      // d0f5: getfield Lobby.xt LxtGraphics;
      // d0f8: dup
      // d0f9: getfield xtGraphics.warning I
      // d0fc: bipush 1
      // d0fd: iadd
      // d0fe: putfield xtGraphics.warning I
      // d101: iload 8
      // d103: aload 0
      // d104: getfield Lobby.pcurs I
      // d107: if_icmpne d10f
      // d10a: goto d12a
      // d10f: aload 0
      // d110: getfield Lobby.gs LGameSparker;
      // d113: new java/awt/Cursor
      // d116: dup
      // d117: iload 8
      // d119: invokespecial java/awt/Cursor.<init> (I)V
      // d11c: invokevirtual GameSparker.setCursor (Ljava/awt/Cursor;)V
      // d11f: aload 0
      // d120: iload 8
      // d122: putfield Lobby.pcurs I
      // d125: goto d12a
      // d12a: return
      // try (22551 -> 22569): 22570 java/lang/Exception
   }

   public void preforma(int var1, int var2) {
      if (this.pre1) {
         boolean var3 = false;
         if (!this.gs.openm && this.gb.open == 0) {
            if (this.conon != 0) {
               var3 = true;
            }
         } else if (this.gs.cmsg.isShowing()) {
            this.gs.cmsg.hide();
         }

         this.gs.movefieldd(this.gs.cmsg, 235, 390, 360, 22, var3);
         if (this.gs.cmsg.getText().equals("Type here...") && var1 > 234 && var1 < 603 && var2 > 385 && var2 < 417) {
            this.gs.cmsg.setText("");
         }
      }

      if (this.pre2) {
         boolean var4 = false;
         if (!this.gs.openm && this.gb.open == 0) {
            var4 = true;
         } else if (this.gs.cmsg.isShowing()) {
            this.gs.cmsg.hide();
         }

         this.gs.movefieldd(this.gs.cmsg, 237, 390, 360, 22, var4);
         if (this.gs.cmsg.getText().equals("Type here...") && var1 > 232 && var1 < 601 && var2 > 385 && var2 < 417) {
            this.gs.cmsg.setText("");
         }
      }

      if (this.pre1 || this.pre2) {
         if (this.gs.cmsg.getText().length() > 100) {
            this.gs.cmsg.setText(this.gs.cmsg.getText().substring(0, 100));
            this.gs.cmsg.select(100, 100);
         }

         this.pre1 = false;
         this.pre2 = false;
      }
   }

   public void stageselect(CheckPoints var1, Control var2, int var3, int var4, boolean var5) {
      this.rd.setColor(new Color(0, 0, 0));
      this.rd.fillRect(0, 0, 65, 450);
      this.rd.fillRect(735, 0, 65, 450);
      this.rd.fillRect(65, 0, 670, 25);
      this.rd.fillRect(65, 425, 670, 25);
      this.btn = 0;
      byte var6 = 0;
      this.rd.drawImage(this.xt.br, 65, 25, null);
      if (this.britchl == -1) {
         this.ongame = -1;
         this.britchl = 0;
      }

      int var7 = 0;

      for (int var8 = 0; var8 < this.ngm; var8++) {
         if (this.ongame == this.gnum[var8]) {
            var7 = var8;
         }
      }

      if (this.chalngd != -2 && this.ongame != -1) {
         this.rd.setColor(this.color2k(20, 20, 20));
         this.rd.fillRect(80, 0, 640, 40);
         this.rd.setColor(this.color2k(70, 70, 70));
         this.rd.drawLine(80, 40, 720, 40);
         this.rd.drawLine(80, 40, 80, 0);
         this.rd.drawLine(720, 40, 720, 0);
         this.rd.setColor(new Color(193, 106, 0));
         this.rd.setFont(new Font("Arial", 1, 12));
         this.ftm = this.rd.getFontMetrics();
         if (this.wait[var7] > 0) {
            if (this.gwarb[var7] != 0) {
               this.rd.drawString("Waiting for " + (this.mnpls[var7] - this.npls[var7]) + " clan members to join to start this game!", 95, 23);
            } else if (this.wait[var7] <= 30 && this.npls[var7] > 1) {
               Date var13 = new Date();
               long var17 = var13.getTime();
               if (this.ptime == 0L || var17 > this.ptime + 1500L) {
                  if (this.ptime != 0L) {
                     this.wait[var7]--;
                     if (this.wait[var7] < 1) {
                        this.wait[var7] = 1;
                     }
                  }

                  this.ptime = var17;
               }

               if (this.pgames[this.im] == this.ongame || this.nflk != 0) {
                  this.rd
                     .drawString(
                        "Game starts in " + this.wait[var7] + " seconds!",
                        400 - this.ftm.stringWidth("Game starts in " + this.wait[var7] + " seconds!") / 2,
                        23
                     );
                  if (this.pgames[this.im] != this.ongame) {
                     this.nflk--;
                  }
               } else if (this.pgames[this.im] != this.ongame) {
                  this.nflk = 3;
               }
            } else {
               String var12 = "";
               if (this.npls[var7] > 1) {
                  Date var9 = new Date();
                  long var10 = var9.getTime();
                  if (this.ptime == 0L || var10 > this.ptime + 1500L) {
                     if (this.ptime != 0L) {
                        this.wait[var7]--;
                        if (this.wait[var7] < 1) {
                           this.wait[var7] = 1;
                        }
                     }

                     this.ptime = var10;
                  }

                  var12 = " (waiting " + this.wait[var7] + " seconds maximum)";
                  if (this.wait[var7] > 60) {
                     var12 = " (waiting " + (int)(this.wait[var7] / 60.0F * 100.0F) / 100.0F + " minutes maximum)";
                  }
               }

               this.rd.drawString("Waiting for " + (this.mnpls[var7] - this.npls[var7]) + " more players to join to start this game" + var12 + "!", 95, 15);
               this.rd.setFont(new Font("Arial", 0, 12));
               if (!this.gmaker[var7].equals("Coach Insano") && !this.gmaker[var7].equals(this.pnames[this.im])) {
                  boolean var16 = false;

                  for (int var20 = 0; var20 < this.npo; var20++) {
                     if (this.pgames[var20] == this.ongame && this.gmaker[var7].equals(this.pnames[var20])) {
                        var16 = true;
                     }
                  }

                  if (var16) {
                     this.rd.drawString("" + this.gmaker[var7] + " can start this game at anytime (the game creator)...", 95, 31);
                  }
               }
            }

            if (this.pgames[this.im] != this.ongame) {
               boolean var14 = false;
               if (this.gwarb[var7] == 0) {
                  if (this.gplyrs[var7].equals("") || this.gplyrs[var7].indexOf(this.pnames[this.im]) != -1) {
                     var14 = true;
                  }
               } else if (this.xt.clan.toLowerCase().equals(this.gaclan[var7].toLowerCase())
                  || this.xt.clan.toLowerCase().equals(this.gvclan[var7].toLowerCase())) {
                  var14 = true;
               }

               if (var14) {
                  this.stringbutton(" Join Game ", 660, 23, 2);
               }
            }
         } else {
            this.rd.setColor(this.color2k(120, 120, 120));
            if (this.wait[var7] == 0) {
               this.rd.drawString("Game Started", 400 - this.ftm.stringWidth("Game Started") / 2, 20);
            } else {
               this.rd.drawString("Game Finished", 400 - this.ftm.stringWidth("Game Finished") / 2, 20);
            }
         }
      }

      this.rd.setFont(new Font("Arial", 1, 11));
      this.xt.ftm = this.rd.getFontMetrics();
      this.ftm = this.rd.getFontMetrics();
      String var15 = "";
      if (var1.stage < 0) {
         var15 = "Custom Stage";
      }

      if (var1.stage > 0 && var1.stage <= 10) {
         var15 = "Stage " + var1.stage + " NFM 1";
      }

      if (var1.stage > 10 && var1.stage <= 27) {
         var15 = "Stage " + (var1.stage - 10) + " NFM 2";
      }

      if (var1.stage > 27) {
         var15 = "Multiplayer Stage " + (var1.stage - 27) + "";
      }

      this.xt.drawcs(85, "Previewing " + var15 + "  >", 255, 138, 0, 5);
      this.xt.drawcs(105, "| " + var1.name + " |", 255, 176, 85, 5);
      this.rd.drawImage(this.xt.back[this.pback], 532, 285, null);
      if (this.plsndt == 0) {
         String var18 = "Play Soundtrack >";
         int var21 = 562 - this.ftm.stringWidth(var18) / 2;
         int var11 = var21 + this.ftm.stringWidth(var18);
         this.rd.setColor(new Color(0, 0, 0));
         this.rd.drawString(var18, var21 + 1, 326);
         this.rd.drawLine(var21 + 1, 328, var11 + 1, 328);
         this.rd.setColor(new Color(255, 138, 0));
         this.rd.drawString(var18, var21, 325);
         this.rd.drawLine(var21, 327, var11, 327);
         if (var3 > var21 && var3 < var11 && var4 > 314 && var4 < 327) {
            var6 = 12;
            if (var5) {
               this.plsndt = 1;
            }
         }
      }

      if (this.plsndt == 1) {
         this.xt.drawcs(190, "Loading Soundtrack, please wait...", 255, 138, 0, 5);
      }

      if (var1.stage < 0) {
         this.rd.setColor(new Color(255, 138, 0));
         this.rd.drawString("Created by: " + var1.maker + "", 85, 413);
         if (var1.pubt > 0) {
            if (var1.pubt == 2) {
               this.xt.drawcs(413, "Super Public Stage", 41, 177, 255, 3);
            } else {
               this.xt.drawcs(413, "Public Stage", 41, 177, 255, 3);
            }

            if (this.addstage == 0 && this.xt.drawcarb(true, null, " Add to My Stages ", 334, 420, var3, var4, var5)) {
               if (this.xt.logged) {
                  this.cd.onstage = var1.name;
                  this.cd.staction = 2;
                  this.cd.sparkstageaction();
                  this.addstage = 2;
               } else {
                  this.addstage = 1;
                  this.waitlink = 20;
               }
            }

            if (this.addstage == 1) {
               this.rd.setFont(new Font("Arial", 1, 11));
               this.ftm = this.rd.getFontMetrics();
               this.rd.setColor(new Color(193, 106, 0));
               String var19 = "Upgrade to a full account to add custom stages!";
               int var22 = 400 - this.ftm.stringWidth(var19) / 2;
               int var23 = var22 + this.ftm.stringWidth(var19);
               this.rd.drawString(var19, var22, 435);
               if (this.waitlink != -1) {
                  this.rd.drawLine(var22, 437, var23, 437);
               }

               if (var3 > var22 && var3 < var23 && var4 > 424 && var4 < 437) {
                  if (this.waitlink != -1) {
                     var6 = 12;
                  }

                  if (var5 && this.waitlink == 0) {
                     this.gs.editlink(this.xt.nickname, true);
                     this.waitlink = -1;
                  }
               }

               if (this.waitlink > 0) {
                  this.waitlink--;
               }
            }

            if (this.addstage == 2) {
               this.xt.drawcs(435, "Adding stage please wait...", 193, 106, 0, 3);
               if (this.cd.staction == 0) {
                  this.addstage = 3;
               }

               if (this.cd.staction == -2) {
                  this.addstage = 4;
               }

               if (this.cd.staction == -3) {
                  this.addstage = 5;
               }

               if (this.cd.staction == -1) {
                  this.addstage = 6;
               }
            }

            if (this.addstage == 3) {
               this.xt.drawcs(435, "Stage has been successfully added to your stages!", 193, 106, 0, 3);
            }

            if (this.addstage == 4) {
               this.xt.drawcs(435, "You already have this stage!", 193, 106, 0, 3);
            }

            if (this.addstage == 5) {
               this.xt.drawcs(435, "Cannot add more then 20 stages to your account!", 193, 106, 0, 3);
            }

            if (this.addstage == 6) {
               this.xt.drawcs(435, "Failed to add stage, unknown error, please try again later.", 193, 106, 0, 3);
            }
         } else {
            this.xt.drawcs(435, "Private Stage", 193, 106, 0, 3);
         }
      }

      if (var2.enter || this.conon == 2 || this.ongame == -1 && this.chalngd != -2) {
         this.m.trk = 0;
         if (this.xt.loadedt) {
            this.xt.strack.unload();
         }

         this.m.focus_point = 400;
         this.m.crs = true;
         this.m.x = -335;
         this.m.y = 0;
         this.m.z = -50;
         this.m.xz = 0;
         this.m.zy = 20;
         this.m.ground = -2000;
         this.fase = 1;
         var2.enter = false;
      }

      if (var6 != this.pcurs) {
         this.gs.setCursor(new Cursor(var6));
         this.pcurs = var6;
      }
   }

   public void ctachm(int var1, int var2, int var3, Control var4) {
      int var5 = -1;
      if (this.fase == 1 || this.fase == 4) {
         for (int var6 = 0; var6 < this.btn; var6++) {
            if (Math.abs(var1 - this.bx[var6]) >= this.bw[var6] / 2 + 12 || Math.abs(var2 - this.by[var6]) >= 14 || var3 != 1 && var3 != 11) {
               this.pessd[var6] = false;
            } else {
               this.pessd[var6] = true;
            }

            if (Math.abs(var1 - this.bx[var6]) < this.bw[var6] / 2 + 12 && Math.abs(var2 - this.by[var6]) < 14 && var3 <= -1) {
               var5 = var6;
               this.gs.mouses = 0;
            }
         }
      }

      if (this.conon == 1) {
         if (!this.regnow) {
            if (this.onjoin == -1) {
               if (this.fase == 4) {
                  if (var1 <= 532 || var1 >= 592 || var2 <= 285 || var2 >= 306 || var3 != 1 && var3 != 11) {
                     this.pback = 0;
                  } else {
                     this.pback = 1;
                  }

                  if (var1 > 532 && var1 < 592 && var2 > 285 && var2 < 306 && var3 <= -1) {
                     this.gs.mouses = 0;
                     this.m.trk = 0;
                     if (this.xt.loadedt) {
                        this.xt.strack.unload();
                     }

                     this.m.focus_point = 400;
                     this.m.crs = true;
                     this.m.x = -335;
                     this.m.y = 0;
                     this.m.z = -50;
                     this.m.xz = 0;
                     this.m.zy = 20;
                     this.m.ground = -2000;
                     this.fase = 1;
                  }

                  if (var5 == 0 && this.chalngd == -1) {
                     var5 = -1;
                     this.join = this.ongame;
                     this.msg = "| Joining Game |";
                     this.spos = 0;
                     this.m.trk = 0;
                     if (this.xt.loadedt) {
                        this.xt.strack.unload();
                     }

                     this.m.focus_point = 400;
                     this.m.crs = true;
                     this.m.x = -335;
                     this.m.y = 0;
                     this.m.z = -50;
                     this.m.xz = 0;
                     this.m.zy = 20;
                     this.m.ground = -2000;
                     this.fase = 1;
                  }
               }

               if (this.fase == 1) {
                  if (this.ongame == -1) {
                     if (var5 == 0) {
                        if (this.chalngd == -1) {
                           if (this.xt.lan && !this.lanlogged) {
                              this.gs.reglink();
                           } else {
                              boolean var12 = false;

                              for (int var7 = 0; var7 < this.ngm; var7++) {
                                 if (this.wait[var7] == 0) {
                                    var12 = true;
                                    break;
                                 }
                              }

                              if (var12 && !this.xt.lan) {
                                 this.chalngd = -6;
                              } else {
                                 this.loadstage = 0;
                                 this.remstage = 0;
                                 this.gstage = 0;
                                 this.gnpls = 8;
                                 this.gwait = 0;
                                 this.gnbts = 0;
                                 this.gclass = 0;
                                 this.gcars = 0;
                                 this.gfix = 0;
                                 this.gnotp = 0;
                                 this.gplayers = "";
                                 this.gs.wgame.select(0);
                                 this.chalngd = -2;
                              }
                           }
                        } else if (this.chalngd != -5) {
                           if (this.invo) {
                              this.invo = false;
                           }

                           for (int var11 = 0; var11 < 7; var11++) {
                              if (!this.invos[var11].equals("")) {
                                 this.invos[var11] = "";
                              }

                              if (!this.dinvi[var11].equals("")) {
                                 this.dinvi[var11] = "";
                              }
                           }

                           this.gs.swait.hide();
                           this.gs.snpls.hide();
                           this.gs.snbts.hide();
                           this.gs.sgame.hide();
                           this.gs.wgame.hide();
                           this.gs.pgame.hide();
                           this.gs.vnpls.hide();
                           this.gs.vtyp.hide();
                           this.gs.warb.hide();
                           this.gs.mstgs.hide();
                           this.gs.slaps.hide();
                           this.gs.snfm1.hide();
                           this.gs.snfmm.hide();
                           this.gs.snfm2.hide();
                           this.gs.sclass.hide();
                           this.gs.scars.hide();
                           this.gs.sfix.hide();
                           this.gs.mycar.hide();
                           this.gs.notp.hide();
                           this.gs.requestFocus();
                           this.chalngd = -1;
                        }
                     }

                     if (var5 == 1) {
                        this.xt.fase = 23;
                     }

                     if (var5 == 2) {
                        this.conon = 0;
                        this.lg.exitfromlobby();

                        try {
                           this.socket.close();
                           this.socket = null;
                           this.din.close();
                           this.din = null;
                           this.dout.close();
                           this.dout = null;
                        } catch (Exception var10) {
                        }

                        this.hideinputs();
                     }

                     byte var13 = 3;
                     if (this.chalngd > -1) {
                        if (var5 == 3) {
                           this.ongame = this.chalngd;
                           this.chalngd = -1;
                        }

                        if (var5 == 4) {
                           this.ongame = this.chalngd;
                           this.join = this.chalngd;
                           this.msg = "| Joining Game |";
                           this.chalngd = -1;
                           this.longame = -1;
                        }

                        if (var5 == 5) {
                           this.ongame = this.longame;
                           this.chalngd = -1;
                           this.longame = -1;
                        }

                        var13 = 6;
                     } else {
                        if (this.chalngd != -1 && this.chalngd != -5 && var5 == 3) {
                           if (this.invo) {
                              this.invo = false;
                           }

                           for (int var15 = 0; var15 < 7; var15++) {
                              if (!this.invos[var15].equals("")) {
                                 this.invos[var15] = "";
                              }

                              if (!this.dinvi[var15].equals("")) {
                                 this.dinvi[var15] = "";
                              }
                           }

                           this.gs.swait.hide();
                           this.gs.snpls.hide();
                           this.gs.snbts.hide();
                           this.gs.sgame.hide();
                           this.gs.wgame.hide();
                           this.gs.pgame.hide();
                           this.gs.vnpls.hide();
                           this.gs.vtyp.hide();
                           this.gs.warb.hide();
                           this.gs.mstgs.hide();
                           this.gs.slaps.hide();
                           this.gs.snfm1.hide();
                           this.gs.snfmm.hide();
                           this.gs.snfm2.hide();
                           this.gs.sclass.hide();
                           this.gs.scars.hide();
                           this.gs.sfix.hide();
                           this.gs.mycar.hide();
                           this.gs.notp.hide();
                           this.gs.requestFocus();
                           this.chalngd = -1;
                        }

                        if (this.chalngd == -6 && var5 == 4) {
                           this.chalngd = -1;
                        }

                        if (this.chalngd == -2) {
                           if (this.gs.wgame.getSelectedIndex() == 0) {
                              if (this.gs.sgame.getSelectedIndex() >= 3 && !this.xt.logged) {
                                 if (var5 == 4) {
                                    this.gs.editlink(this.xt.nickname, true);
                                 }

                                 var13 = 5;
                              } else {
                                 if (var5 == 4) {
                                    if (this.loadstage < 0) {
                                       this.rd.setColor(new Color(0, 0, 0));
                                       this.rd.fillRect(0, 0, 670, 400);
                                       this.gs.repaint();
                                       this.gs.rooms.hide();
                                       this.gs.cmsg.hide();
                                       this.gs.sgame.hide();
                                       this.gs.wgame.hide();
                                       this.gs.warb.hide();
                                       this.gs.pgame.hide();
                                       this.gs.vnpls.hide();
                                       this.gs.vtyp.hide();
                                       this.gs.mstgs.hide();
                                       this.gs.slaps.hide();
                                       this.gs.snfm1.hide();
                                       this.gs.snfmm.hide();
                                       this.gs.snfm2.hide();
                                       this.gs.requestFocus();
                                       this.m.ptr = 0;
                                       this.m.ptcnt = -10;
                                       this.m.hit = 20000;
                                       this.m.fallen = 0;
                                       this.m.nrnd = 0;
                                       this.m.ih = 25;
                                       this.m.iw = 65;
                                       this.m.h = 425;
                                       this.m.w = 735;
                                       this.m.trk = 4;
                                       this.plsndt = 0;
                                       this.addstage = 0;
                                       this.fase = 4;
                                    } else {
                                       this.sflk = 25;
                                    }
                                 }

                                 if (this.loadstage >= 0) {
                                    var13 = 5;
                                 } else {
                                    if (var5 == 5) {
                                       if (this.gstage != 0) {
                                          this.gs.sgame.hide();
                                          this.gs.wgame.hide();
                                          this.gs.pgame.hide();
                                          this.gs.vnpls.hide();
                                          this.gs.vtyp.hide();
                                          this.gs.warb.hide();
                                          this.gs.mstgs.hide();
                                          this.gs.slaps.hide();
                                          this.gs.snfm1.hide();
                                          this.gs.snfmm.hide();
                                          this.gs.snfm2.hide();
                                          this.gs.requestFocus();
                                          this.chalngd = -3;
                                          var5 = -1;
                                       } else {
                                          this.sflk = 25;
                                       }
                                    }

                                    var13 = 6;
                                 }
                              }
                           } else if (!this.xt.clan.equals("")) {
                              int var16 = 4;
                              if (this.gs.warb.sel != 0 && this.gb.loadwbgames == 2) {
                                 if (this.gs.wgame.getSelectedIndex() == 1 && this.gs.vnpls.sel != 0 && this.cancreate == 2) {
                                    if (var5 == 4) {
                                       if (this.invo) {
                                          this.invo = false;
                                       }

                                       this.msg = "| Creating Game |";
                                       this.gplayers = "#warb#"
                                          + this.gb.warb
                                          + "#"
                                          + this.gb.warbnum
                                          + "#"
                                          + (this.gb.gameturn + 1)
                                          + "#"
                                          + this.xt.clan
                                          + "#"
                                          + this.gb.vclan
                                          + "#"
                                          + this.gb.ascore
                                          + "#"
                                          + this.gb.vscore
                                          + "#"
                                          + (this.gs.vtyp.sel + 1)
                                          + "#";
                                       this.gstage = this.gb.wbstage[this.gb.gameturn];
                                       this.gstagelaps = this.gb.wblaps[this.gb.gameturn];
                                       this.gcars = this.gb.wbcars[this.gb.gameturn] - 1;
                                       this.gclass = this.gb.wbclass[this.gb.gameturn];
                                       this.gfix = this.gb.wbfix[this.gb.gameturn];
                                       this.gnpls = 8;
                                       if (this.gs.vnpls.sel == 1) {
                                          this.gnpls = 4;
                                       }

                                       if (this.gs.vnpls.sel == 2) {
                                          this.gnpls = 6;
                                       }

                                       this.gwait = 120;
                                       this.gnotp = 0;
                                       this.gs.wgame.hide();
                                       this.gs.pgame.hide();
                                       this.gs.vnpls.hide();
                                       this.gs.vtyp.hide();
                                       this.gs.warb.hide();
                                       this.gs.requestFocus();
                                       this.chalngd = -5;
                                    }

                                    var16++;
                                 }

                                 if (this.gs.wgame.getSelectedIndex() == 2 && this.gs.pgame.sel != 0 && var5 == 4) {
                                    if (this.invo) {
                                       this.invo = false;
                                    }

                                    this.msg = "| Creating Game |";
                                    this.gstage = this.gb.wbstage[this.gs.pgame.sel - 1];
                                    this.gstagelaps = this.gb.wblaps[this.gs.pgame.sel - 1];
                                    this.gcars = this.gb.wbcars[this.gs.pgame.sel - 1] - 1;
                                    this.gclass = this.gb.wbclass[this.gs.pgame.sel - 1];
                                    this.gfix = this.gb.wbfix[this.gs.pgame.sel - 1];
                                    this.gnpls = 8;
                                    this.gwait = 120;
                                    this.gnotp = 0;
                                    this.gplayers = "";
                                    this.gs.wgame.hide();
                                    this.gs.pgame.hide();
                                    this.gs.vnpls.hide();
                                    this.gs.vtyp.hide();
                                    this.gs.warb.hide();
                                    this.gs.requestFocus();
                                    this.chalngd = -5;
                                 }

                                 if (this.gs.wgame.getSelectedIndex() == 1 && this.gb.canredo && var5 == var16) {
                                    this.gb.loadwbgames = 7;
                                 }
                              }
                           } else if (var5 == 4) {
                              this.gb.tab = 3;
                              this.gb.cfase = 2;
                              this.gb.em = 1;
                              this.gb.msg = "Clan Search";
                              this.gb.smsg = "Listing clans with recent activity...";
                              this.gb.nclns = 0;
                              this.gb.spos5 = 0;
                              this.gb.lspos5 = 0;
                              this.gb.flko = 0;
                              this.gb.open = 2;
                              this.gb.upo = true;
                           }
                        }

                        if (this.chalngd == -3) {
                           if (var5 == 4) {
                              this.chalngd = -2;
                              this.gs.snpls.hide();
                              this.gs.snbts.hide();
                              this.gs.swait.hide();
                              this.gs.requestFocus();
                           }

                           if (var5 == 5) {
                              if (this.gnpls != 0 && this.gs.snpls.getSelectedIndex() != 0) {
                                 this.chalngd = -4;

                                 for (int var17 = 0; var17 < 7; var17++) {
                                    if (!this.invos[var17].equals("")) {
                                       this.invos[var17] = "";
                                    }

                                    if (!this.dinvi[var17].equals("")) {
                                       this.dinvi[var17] = "";
                                    }
                                 }

                                 var5 = -1;
                                 this.gs.snpls.hide();
                                 this.gs.snbts.hide();
                                 this.gs.swait.hide();
                                 this.gs.requestFocus();
                              } else {
                                 this.sflk = 25;
                              }
                           }

                           var13 = 6;
                        }

                        if (this.chalngd == -4) {
                           var13 = 7;
                           int var18 = 0;

                           for (int var8 = 0; var8 < 7; var8++) {
                              if (!this.invos[var8].equals("")) {
                                 var18++;
                              }
                           }

                           if (var18 < this.gnpls - 1) {
                              if (var5 == 4) {
                                 if (!this.invo) {
                                    this.invo = true;
                                 } else {
                                    this.invo = false;
                                 }
                              }
                           } else {
                              var13 = 6;
                           }

                           if (var5 == var13 - 2) {
                              if (this.invo) {
                                 this.invo = false;
                              }

                              if (this.gs.mycar.getState() && this.xt.sc[0] < 16) {
                                 this.gclass = -(this.xt.sc[0] + 2);
                                 this.gcars = 0;
                              }

                              if (this.gs.notp.getState()) {
                                 this.gnotp = 1;
                              } else {
                                 this.gnotp = 0;
                              }

                              this.gplayers = "";
                              this.gs.sclass.hide();
                              this.gs.scars.hide();
                              this.gs.sfix.hide();
                              this.gs.mycar.hide();
                              this.gs.notp.hide();
                              this.gs.requestFocus();
                              this.chalngd = -3;
                           }

                           if (var5 == var13 - 1) {
                              if (this.invo) {
                                 this.invo = false;
                              }

                              this.msg = "| Creating Game |";
                              if (this.gs.mycar.getState() && this.xt.sc[0] < 16) {
                                 this.gclass = -(this.xt.sc[0] + 2);
                                 this.gcars = 0;
                              }

                              if (this.gclass != 0) {
                                 this.gwait = 120;
                              }

                              if (this.gs.notp.getState()) {
                                 this.gnotp = 1;
                              } else {
                                 this.gnotp = 0;
                              }

                              this.gplayers = "";
                              if (var18 != 0) {
                                 this.gnpls = var18 + 1;
                                 this.gplayers = "" + this.pnames[this.im] + "#";

                                 for (int var22 = 0; var22 < var18; var22++) {
                                    this.gplayers = this.gplayers + this.invos[var22] + "#";
                                 }
                              }

                              this.gs.sclass.hide();
                              this.gs.scars.hide();
                              this.gs.sfix.hide();
                              this.gs.mycar.hide();
                              this.gs.notp.hide();
                              this.gs.requestFocus();
                              this.chalngd = -5;
                           }
                        }
                     }

                     if (var5 == var13 && !this.xt.lan && !this.gs.cmsg.getText().equals("Type here...") && !this.gs.cmsg.getText().equals("")) {
                        String var19 = this.gs.cmsg.getText().replace('|', ':');
                        if (var19.toLowerCase().indexOf(this.gs.tpass.getText().toLowerCase()) != -1) {
                           var19 = " ";
                        }

                        if (!this.xt.msgcheck(var19) && this.updatec > -12) {
                           for (int var23 = 0; var23 < 6; var23++) {
                              this.sentn[var23] = this.sentn[var23 + 1];
                              this.cnames[var23] = this.cnames[var23 + 1];
                           }

                           this.sentn[6] = var19;
                           this.cnames[6] = this.pnames[this.im];
                           if (this.updatec > -11) {
                              this.updatec = -11;
                           } else {
                              this.updatec--;
                           }

                           this.spos3 = 28;
                        } else {
                           this.xt.warning++;
                        }

                        this.gs.cmsg.setText("");
                     }
                  } else if (this.dispcar == -1) {
                     int var14 = 0;

                     for (int var20 = 0; var20 < this.ngm; var20++) {
                        if (this.ongame == this.gnum[var20]) {
                           var14 = var20;
                        }
                     }

                     boolean var21 = false;
                     if (this.gwarb[var14] == 0) {
                        if (!this.gplyrs[var14].equals("") && this.gplyrs[var14].indexOf(this.pnames[this.im]) == -1) {
                           var21 = true;
                        }
                     } else if (!this.xt.clan.toLowerCase().equals(this.gaclan[var14].toLowerCase())
                        && !this.xt.clan.toLowerCase().equals(this.gvclan[var14].toLowerCase())) {
                        var21 = true;
                     }

                     if (var4.enter && this.wait[var14] > 0 && this.pgames[this.im] == -1 && !var21) {
                        this.join = this.ongame;
                        this.msg = "| Joining Game |";
                        this.spos = 0;
                        if (this.pbtn == 0) {
                           this.pessd[1] = true;
                        }
                     }

                     if (this.wait[var14] == -1 && this.pgames[this.im] == -1 && var4.enter) {
                        var5 = 0;
                        this.pessd[0] = true;
                     }

                     if (this.pgames[this.im] == -1 && var4.exit) {
                        var5 = 0;
                        this.pessd[0] = true;
                     }

                     if (var5 == 0) {
                        if (this.pgames[this.im] == -1) {
                           this.ongame = -1;
                           this.chalngd = -1;
                        } else {
                           this.join = -2;
                           this.msg = "| Leaving Game |";
                           this.longame = -1;
                        }
                     }

                     if (this.pbtn == 0) {
                        if (var5 == 1) {
                           if (this.wait[var14] > 0) {
                              if (this.pgames[this.im] == -1) {
                                 this.join = this.ongame;
                                 this.msg = "| Joining Game |";
                                 this.spos = 0;
                              } else if (this.gmaker[var14].equals(this.pnames[this.im]) && this.npls[var14] > 1) {
                                 this.fstart = true;
                              } else {
                                 var5 = 2;
                              }
                           } else {
                              if (this.wait[var14] == 0 && this.prevloaded == 1) {
                                 this.laps = this.gnlaps[var14];
                                 this.stage = this.gstgn[var14];
                                 this.stagename = this.gstages[var14];
                                 this.nfix = this.gfx[var14];
                                 if (this.gntb[var14] == 1) {
                                    this.notb = true;
                                 } else {
                                    this.notb = false;
                                 }

                                 this.gs.setCursor(new Cursor(3));
                                 this.conon = 3;
                              } else {
                                 var5 = 2;
                              }

                              if (this.wait[var14] == 0 && this.xt.lan) {
                                 this.laps = this.gnlaps[var14];
                                 this.stage = this.gstgn[var14];
                                 this.stagename = this.gstages[var14];
                                 this.nfix = this.gfx[var14];
                                 if (this.gntb[var14] == 1) {
                                    this.notb = true;
                                 } else {
                                    this.notb = false;
                                 }

                                 this.gs.setCursor(new Cursor(3));
                                 this.conon = 3;
                              }
                           }
                        }

                        if (var5 == 2 && !this.xt.lan && !this.gs.cmsg.getText().equals("Type here...") && !this.gs.cmsg.getText().equals("")) {
                           String var24 = this.gs.cmsg.getText().replace('|', ':');
                           if (var24.toLowerCase().indexOf(this.gs.tpass.getText().toLowerCase()) != -1) {
                              var24 = " ";
                           }

                           if (!this.xt.msgcheck(var24) && this.updatec > -12) {
                              for (int var9 = 0; var9 < 6; var9++) {
                                 this.sentn[var9] = this.sentn[var9 + 1];
                                 this.cnames[var9] = this.cnames[var9 + 1];
                              }

                              this.sentn[6] = var24;
                              this.cnames[6] = this.pnames[this.im];
                              if (this.updatec > -11) {
                                 this.updatec = -11;
                              } else {
                                 this.updatec--;
                              }
                           } else {
                              this.xt.warning++;
                           }

                           this.gs.cmsg.setText("");
                        }
                     }

                     if (this.pbtn == 1 && var5 == 1) {
                        if (this.pgames[this.im] == -1) {
                           this.join = this.ongame;
                           this.msg = "| Joining Game |";
                           this.spos = 0;
                        } else if (!this.invo) {
                           this.invo = true;
                        } else {
                           this.invo = false;
                        }
                     }

                     if (this.pbtn == 2 && (var5 == 1 || var5 == 2)) {
                        this.fase = 2;
                        this.m.ptr = 0;
                        this.m.ptcnt = -10;
                        this.m.hit = 20000;
                        this.m.fallen = 500;
                        this.m.nrnd = 0;
                        this.m.ih = 25;
                        this.m.iw = 65;
                        this.m.h = 425;
                        this.m.w = 735;
                        this.m.trk = 4;
                        this.plsndt = 0;
                        if (this.gs.cmsg.isShowing()) {
                           this.gs.cmsg.hide();
                           this.gs.requestFocus();
                        }
                     }
                  } else {
                     if (var5 == 0) {
                        if (this.pgames[this.im] == -1) {
                           this.ongame = -1;
                           this.chalngd = -1;
                        } else {
                           this.join = -2;
                           this.msg = "| Leaving Game |";
                           this.longame = -1;
                        }
                     }

                     if (this.pbtn == 1 && var5 == 1) {
                        if (this.pgames[this.im] == -1) {
                           this.join = this.ongame;
                           this.msg = "| Joining Game |";
                           this.spos = 0;
                        } else if (!this.invo) {
                           this.invo = true;
                        } else {
                           this.invo = false;
                        }
                     }
                  }
               }
            } else if (this.ontyp != 76) {
               if (var5 == 0) {
                  if (this.ontyp < 30) {
                     this.xt.onjoin = this.onjoin;
                     this.xt.ontyp = this.ontyp;
                     this.xt.playingame = -101;
                     this.xt.fase = 23;
                  } else {
                     if (!this.gb.claname.equals(this.xt.clan)) {
                        this.gb.claname = this.xt.clan;
                        this.gb.loadedc = false;
                     }

                     this.gb.spos5 = 0;
                     this.gb.lspos5 = 0;
                     this.gb.cfase = 3;
                     this.gb.loadedcars = -1;
                     this.gb.loadedcar = 0;
                     this.gb.ctab = 2;
                     this.gb.tab = 3;
                     this.gb.open = 2;
                     this.gb.upo = true;
                     this.onjoin = -1;
                  }
               }

               if (var5 == 1) {
                  this.onjoin = -1;
               }
            } else {
               if (var5 == 0) {
                  this.gs.editlink(this.xt.nickname, true);
                  this.onjoin = -1;
               }

               if (var5 == 1) {
                  this.onjoin = -1;
               }
            }
         } else {
            if (var5 == 0) {
               this.gs.editlink(this.xt.nickname, true);
            }

            if (var5 == 1) {
               this.regnow = false;
            }
         }
      }

      this.lxm = var1;
      this.lym = var2;
      var4.enter = false;
      var4.exit = false;
   }

   public void hideinputs() {
      this.gs.cmsg.hide();
      this.gs.swait.hide();
      this.gs.snpls.hide();
      this.gs.snbts.hide();
      this.gs.sgame.hide();
      this.gs.wgame.hide();
      this.gs.pgame.hide();
      this.gs.vnpls.hide();
      this.gs.vtyp.hide();
      this.gs.warb.hide();
      this.gs.mstgs.hide();
      this.gs.snfm1.hide();
      this.gs.snfmm.hide();
      this.gs.slaps.hide();
      this.gs.snfm2.hide();
      this.gs.sclass.hide();
      this.gs.scars.hide();
      this.gs.sfix.hide();
      this.gs.mycar.hide();
      this.gs.notp.hide();
      this.gs.rooms.hide();
      this.gs.requestFocus();
   }

   public void drawSbutton(Image var1, int var2, int var3) {
      this.bx[this.btn] = var2;
      this.by[this.btn] = var3;
      this.bw[this.btn] = var1.getWidth(this.ob);
      if (!this.pessd[this.btn]) {
         this.rd.drawImage(var1, var2 - this.bw[this.btn] / 2, var3 - var1.getHeight(this.ob) / 2 - 1, null);
         this.rd.drawImage(this.xt.bols, var2 - this.bw[this.btn] / 2 - 15, var3 - 13, null);
         this.rd.drawImage(this.xt.bors, var2 + this.bw[this.btn] / 2 + 9, var3 - 13, null);
         this.rd.drawImage(this.xt.bot, var2 - this.bw[this.btn] / 2 - 9, var3 - 13, this.bw[this.btn] + 18, 3, null);
         this.rd.drawImage(this.xt.bob, var2 - this.bw[this.btn] / 2 - 9, var3 + 10, this.bw[this.btn] + 18, 3, null);
      } else {
         this.rd.drawImage(var1, var2 - this.bw[this.btn] / 2 + 1, var3 - var1.getHeight(this.ob) / 2, null);
         this.rd.drawImage(this.xt.bolps, var2 - this.bw[this.btn] / 2 - 15, var3 - 13, null);
         this.rd.drawImage(this.xt.borps, var2 + this.bw[this.btn] / 2 + 9, var3 - 13, null);
         this.rd.drawImage(this.xt.bob, var2 - this.bw[this.btn] / 2 - 9, var3 - 13, this.bw[this.btn] + 18, 3, null);
         this.rd.drawImage(this.xt.bot, var2 - this.bw[this.btn] / 2 - 9, var3 + 10, this.bw[this.btn] + 18, 3, null);
      }

      this.btn++;
   }

   public void stringbutton(String var1, int var2, int var3, int var4) {
      this.rd.setFont(new Font("Arial", 1, 12));
      this.ftm = this.rd.getFontMetrics();
      this.bx[this.btn] = var2;
      this.by[this.btn] = var3 - 5;
      this.bw[this.btn] = this.ftm.stringWidth(var1);
      if (!this.pessd[this.btn]) {
         this.rd.setColor(this.color2k(220, 220, 220));
         this.rd.fillRect(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), this.bw[this.btn] + 20, 25 - var4 * 2);
         this.rd.setColor(this.color2k(240, 240, 240));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (18 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (18 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 - (19 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 - (19 - var4));
         this.rd.setColor(this.color2k(200, 200, 200));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 11, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 11, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 12, var3 - (16 - var4), var2 + this.bw[this.btn] / 2 + 12, var3 + (6 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (8 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (8 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 + (9 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 + (9 - var4));
         this.rd.setColor(this.color2k(240, 240, 240));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 11, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 11, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 12, var3 - (16 - var4), var2 - this.bw[this.btn] / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         this.rd.drawString(var1, var2 - this.bw[this.btn] / 2, var3);
      } else {
         this.rd.setColor(this.color2k(210, 210, 210));
         this.rd.fillRect(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), this.bw[this.btn] + 20, 25 - var4 * 2);
         this.rd.setColor(this.color2k(200, 200, 200));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (18 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 - (18 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 - (19 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 - (19 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 10, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 11, var3 - (17 - var4), var2 + this.bw[this.btn] / 2 + 11, var3 + (7 - var4));
         this.rd.drawLine(var2 + this.bw[this.btn] / 2 + 12, var3 - (16 - var4), var2 + this.bw[this.btn] / 2 + 12, var3 + (6 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 + (8 - var4), var2 + this.bw[this.btn] / 2 + 10, var3 + (8 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 9, var3 + (9 - var4), var2 + this.bw[this.btn] / 2 + 9, var3 + (9 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 10, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 10, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 11, var3 - (17 - var4), var2 - this.bw[this.btn] / 2 - 11, var3 + (7 - var4));
         this.rd.drawLine(var2 - this.bw[this.btn] / 2 - 12, var3 - (16 - var4), var2 - this.bw[this.btn] / 2 - 12, var3 + (6 - var4));
         this.rd.setColor(new Color(0, 0, 0));
         this.rd.drawString(var1, var2 - this.bw[this.btn] / 2 + 1, var3);
      }

      this.btn++;
   }

   public Color color2k(int var1, int var2, int var3) {
      Color var4 = new Color(var1, var2, var3);
      float[] var5 = new float[3];
      Color.RGBtoHSB(var4.getRed(), var4.getGreen(), var4.getBlue(), var5);
      var5[0] = 0.13F;
      var5[1] = 0.35F;
      return Color.getHSBColor(var5[0], var5[1], var5[2]);
   }

   public int getvalue(String var1, int var2) {
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

   public String getSvalue(String var1, int var2) {
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

   public int getHvalue(String var1, int var2) {
      int var3 = -1;

      try {
         int var4 = 0;
         int var5 = 0;
         byte var6 = 0;
         String var7 = "";
         String var8 = "";

         while (var4 < var1.length() && var6 != 2) {
            var7 = "" + var1.charAt(var4);
            if (var7.equals("#")) {
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

   public String getHSvalue(String var1, int var2) {
      String var3 = "";

      try {
         int var4 = 0;
         int var5 = 0;
         byte var6 = 0;
         String var7 = "";
         String var8 = "";

         while (var4 < var1.length() && var6 != 2) {
            var7 = "" + var1.charAt(var4);
            if (var7.equals("#")) {
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

   public String getSevervalue(String var1, int var2) {
      String var3 = "";
      if (!var1.equals("")) {
         try {
            boolean var4 = false;
            int var5 = 0;
            int var6 = 0;
            byte var7 = 0;
            String var8 = "";
            String var9 = "";

            while (var5 < var1.length() && var7 != 2) {
               var8 = "" + var1.charAt(var5);
               if (var8.equals("|")) {
                  if (var7 == 1 || ++var6 > var2) {
                     var7 = 2;
                  }
               } else if (var6 == var2) {
                  var9 = var9 + var8;
                  var7 = 1;
               }

               var5++;
            }

            var3 = var9;
         } catch (Exception var10) {
            var3 = "";
         }
      }

      return var3;
   }
}
