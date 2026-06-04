public class RadicalMod {
   SuperClip sClip;
   boolean playing = false;
   int loaded = 2;
   int rvol = 0;
   String imod = "";
   String pmod = "";

   public RadicalMod() {
      this.loaded = 2;
   }

   public RadicalMod(String var1) {
      this.loaded = 2;
      this.imod = var1;
   }

   public RadicalMod(String var1, boolean var2) {
      this.loaded = 2;
      this.pmod = var1;
   }

   public RadicalMod(String var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      this.loaded = 2;
   }

   public void loadimod(boolean var1) {
      this.loaded = 2;
   }

   public void loadpmod(boolean var1) {
      this.loaded = 2;
   }

   public void play() {
      this.playing = true;
   }

   public void resume() {
      this.playing = true;
   }

   public void stop() {
      this.playing = false;
   }

   protected void unloadimod() {
      this.playing = false;
      this.loaded = 1;
   }

   protected void unload() {
      this.playing = false;
      this.loaded = 0;
   }
}