/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ class WFApplet$Textfläche extends JPanel
/*     */ {
/*     */   int breite;
/*     */   int höhe;
/*     */   private final WFApplet this$0;
/*     */ 
/*     */   WFApplet$Textfläche(WFApplet paramWFApplet)
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
/* 300 */     for (i = 0; i < this.this$0.werteVar.size(); i++) {
/* 301 */       str = (String)this.this$0.werteVar.elementAt(i);
/* 302 */       paramGraphics.drawString(str, 100, 30 + i * 20);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     WFApplet.TextflÃ¤che
 * JD-Core Version:    0.6.0
 */