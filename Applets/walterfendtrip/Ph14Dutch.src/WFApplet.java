/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.PrintStream;
          import java.awt.geom.Line2D;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JFrame;
import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 

/*     */ 

/*     */ public abstract class WFApplet extends JApplet
/*     */ {
/*     */   protected int iApplet;
/*     */   Container cp;
/*     */   int width;
/*     */   int height;
/*     */   Color BG;
/*     */   Color PAN;
/*  22 */   static final Font HELVETICA = new Font("Helvetica", 1, 12);
/*     */ 
/*  24 */   static final Font SANSSERIF = new Font("SansSerif", 1, 12);
/*     */ 
/*  26 */   static Font HELV = SANSSERIF;
/*     */   final BasicStroke THIN;
/*     */   final BasicStroke THICK;
/*  31 */   static Line2D.Double LINE = new Line2D.Double();
/*     */ 
/*  33 */   static GeneralPath SPITZE = new GeneralPath();
/*     */   JFrame fDebug;
/*     */   WFApplet.Textfläche tfl;
/*     */   Vector namenVar;
/*     */   Vector werteVar;
/*     */ 
/*     */   public WFApplet()
/*     */   {
/*  20 */     this.BG = Color.yellow;
/*  21 */     this.PAN = Color.green;
/*     */ 
/*  28 */     this.THIN = new BasicStroke(1.0F);
/*  29 */     this.THICK = new BasicStroke(3.0F, 1, 1);
/*     */   }
/*     */ 
/*     */   abstract void setIndexOfApplet();
/*     */ 
/*     */   abstract String text(int paramInt);
/*     */ 
/*     */   public void start()
/*     */   {
/*  49 */     setIndexOfApplet();
/*  50 */     this.cp = getContentPane();
/*  51 */     this.cp.setLayout(null);
/*  52 */     this.width = getSize().width;
/*  53 */     this.height = getSize().height;
/*  54 */     String str = "Polish";
/*  55 */     this.fDebug = new JFrame();
/*  56 */     this.fDebug.setSize(300, 200);
/*  57 */     this.tfl = new WFApplet.Textfläche(this);
/*  58 */     Container localContainer = this.fDebug.getContentPane();
/*  59 */     localContainer.add(this.tfl);
/*  60 */     this.namenVar = new Vector();
/*  61 */     this.werteVar = new Vector();
/*     */   }
/*     */   
/*     */   String toString(double paramDouble, int paramInt)
/*     */   {
              long l2;
/*  70 */     String str = paramDouble >= 0.0D ? "" : "-";
/*  71 */     if (paramDouble < 0.0D) paramDouble = -paramDouble;
/*  72 */     long l3 = 0L; for (l2 = 1L; l3 < paramInt; l3 += 1L) l2 *= 10L;
/*  73 */     long l1 = Math.round(paramDouble * l2);
/*  74 */     str = str + l1 / l2; if (paramInt > 0) str = str + text(1);
/*  75 */     l1 %= l2; for (l3 = 0L; l3 < paramInt; l3 += 1L) { l2 /= 10L; str = str + l1 / l2; l1 %= l2; }
/*  76 */     return str;
/*     */   }
/*     */ 
/*     */   String toString2(double paramDouble, int paramInt)
/*     */   {
/*  84 */     double d = Math.abs(paramDouble);
/*  85 */     if (d < 1.0E-006D) d = 0.0D;
/*  86 */     int i = (int)Math.floor(Math.log(d) / Math.log(10.0D));
/*  87 */     String str = paramDouble >= 0.0D ? "" : "-";
/*  88 */     return str + toString(d, paramInt - 1 - i);
/*     */   }
/*     */ 
/*     */   double toDouble(String paramString)
/*     */   {
/*  95 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/*  96 */     for (int i = 0; i < localStringBuffer.length(); i++) {
/*  97 */       if (localStringBuffer.charAt(i) != ',') continue; localStringBuffer.setCharAt(i, '.'); } double d;
/*     */     try { d = Double.parseDouble(localStringBuffer.toString()); } catch (NumberFormatException localNumberFormatException) {
/* 100 */       d = 0.0D;
/* 101 */     }return d;
/*     */   }
/*     */ 
/*     */   double inputTF(JTextField paramJTextField, double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/* 111 */     double d = toDouble(paramJTextField.getText());
/* 112 */     if (d < paramDouble1) d = paramDouble1; if (d > paramDouble2) d = paramDouble2;
/* 113 */     paramJTextField.setText(toString(d, paramInt));
/* 114 */     return d;
/*     */   }
/*     */ 
/*     */   double inputTF2(JTextField paramJTextField, double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/* 124 */     double d = toDouble(paramJTextField.getText());
/* 125 */     if (d < paramDouble1) d = paramDouble1; if (d > paramDouble2) d = paramDouble2;
/* 126 */     paramJTextField.setText(toString2(d, paramInt));
/* 127 */     return d;
/*     */   }
/*     */ 
/*     */   void line(Graphics paramGraphics, float paramFloat, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
/*     */   {
/* 138 */     Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
/* 139 */     if (paramFloat == 1.0F) localGraphics2D.setStroke(this.THIN);
/* 140 */     else if (paramFloat == 3.0F) localGraphics2D.setStroke(this.THICK); else
/* 141 */       localGraphics2D.setStroke(new BasicStroke(paramFloat));
/* 142 */     LINE.setLine(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
/* 143 */     localGraphics2D.draw(LINE);
/* 144 */     localGraphics2D.setStroke(this.THIN);
/*     */   }
/*     */ 
/*     */   void arrow(Graphics paramGraphics, float paramFloat, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
/*     */   {
/* 178 */     Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
/*     */ 
/* 180 */     double d1 = paramDouble3 - paramDouble1; double d2 = paramDouble4 - paramDouble2;
/* 181 */     double d3 = Math.sqrt(d1 * d1 + d2 * d2);
/* 182 */     if (d3 == 0.0D) return;
/* 183 */     d1 /= d3; d2 /= d3;
/* 184 */     int i = d3 < 10.0D ? 1 : 0;
/* 185 */     line(paramGraphics, 1.0F, paramDouble1, paramDouble2, paramDouble3, paramDouble4);
/* 186 */     double d4 = 3.0F * paramFloat + 6.0F; double d5 = paramFloat + 3.0F;
/* 187 */     double d6 = paramDouble3 - d4 * d1; double d7 = paramDouble4 - d4 * d2;
/* 188 */     line(paramGraphics, paramFloat, paramDouble1, paramDouble2, d6, d7);
/* 189 */     if (d3 < 10.0D) return;
/* 190 */     double d8 = d6 - d5 * d2; double d9 = d7 + d5 * d1;
/* 191 */     double d10 = d6 + d5 * d2; double d11 = d7 - d5 * d1;
/* 192 */     SPITZE.reset();
/* 193 */     SPITZE.moveTo((float)paramDouble3, (float)paramDouble4);
/* 194 */     SPITZE.lineTo((float)d8, (float)d9);
/* 195 */     SPITZE.lineTo((float)d10, (float)d11);
/* 196 */     SPITZE.lineTo((float)paramDouble3, (float)paramDouble4);
/* 197 */     localGraphics2D.fill(SPITZE);
/*     */   }
/*     */ 
/*     */   void arrow(Graphics paramGraphics, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
/*     */   {
/* 233 */     arrow(paramGraphics, 1.0F, paramDouble1, paramDouble2, paramDouble3, paramDouble4);
/*     */   }
/*     */ 
/*     */   void angle(Graphics paramGraphics, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, Color paramColor, int paramInt)
/*     */   {
/* 245 */     if (paramDouble3 < 0.0D) paramDouble3 += 360.0D;
/* 246 */     if (paramDouble3 > 360.0D) paramDouble3 -= 360.0D;
/* 247 */     if (paramDouble4 < 0.0D) paramDouble4 += 360.0D;
/* 248 */     if (paramDouble4 > 360.0D) paramDouble4 -= 360.0D;
/* 249 */     if (paramDouble4 < 2.0D) return;
/*     */ 
/* 251 */     int i = (int)Math.round(paramDouble1);
/* 252 */     int j = (int)Math.round(paramDouble2);
/* 253 */     int k = Math.round(2 * paramInt);
/* 254 */     int m = (int)Math.round(paramDouble3);
/* 255 */     int n = (int)Math.round(paramDouble4);
/* 256 */     paramGraphics.setColor(paramColor);
/* 257 */     paramGraphics.fillArc(i - paramInt, j - paramInt, k, k, m, n);
/* 258 */     paramGraphics.setColor(Color.black);
/* 259 */     paramGraphics.drawArc(i - paramInt, j - paramInt, k, k, m, n);
/*     */   }
/*     */ 
/*     */   void debug(String paramString)
/*     */   {
/*     */     Field localField;
/*     */     try {
/* 267 */       localField = getClass().getDeclaredField(paramString);
/* 268 */       this.namenVar.addElement(localField.getName());
/*     */     }
/*     */     catch (NoSuchFieldException localNoSuchFieldException) {
/* 271 */       System.out.println("Fehler!"); return;
/*     */     }
/* 273 */     String str = "";
/*     */     try { str = "" + localField.get(this); } catch (Exception localException) {
/*     */     }
/* 276 */     this.werteVar.addElement(str);
/* 277 */     this.fDebug.setVisible(true);
/*     */   }

class Textfläche extends JPanel
/*     */ {
/*     */   int breite;
/*     */   int höhe;
/*     */   private final WFApplet this$0;
/*     */ 
/*     */   Textfläche(WFApplet paramWFApplet)
/*     */   {
/* 287 */     this.this$0 = paramWFApplet;
/*     */ 
/* 285 */     this.breite = 280; this.höhe = 180;
/*     */ 
/* 288 */     setSize(this.breite, this.höhe);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics) {
/* 292 */     paramGraphics.setColor(Color.white);
/* 293 */     paramGraphics.fillRect(0, 0, this.breite, this.höhe);
/* 294 */     paramGraphics.setColor(Color.black);
/* 295 */     paramGraphics.setFont(WFApplet.HELV);
/*     */     String str;
/* 296 */     for (int i = 0; i < this.this$0.namenVar.size(); i++) {
/* 297 */       str = (String)this.this$0.namenVar.elementAt(i);
/* 298 */       paramGraphics.drawString(str, 20, 30 + i * 20);
/*     */     }
/* 300 */     for (int i = 0; i < this.this$0.werteVar.size(); i++) {
/* 301 */       str = (String)this.this$0.werteVar.elementAt(i);
/* 302 */       paramGraphics.drawString(str, 100, 30 + i * 20);
/*     */     }
/*     */   }
/*     */ }            

/*     */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     WFApplet
 * JD-Core Version:    0.6.0
 */