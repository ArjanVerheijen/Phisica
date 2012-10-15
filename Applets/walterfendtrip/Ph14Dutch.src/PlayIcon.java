/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Polygon;
/*    */ 
/*    */ public final class PlayIcon extends ButtonIcon
/*    */ {
/*    */   private Polygon poly;
/*    */ 
/*    */   PlayIcon()
/*    */   {
/* 15 */     this.poly = new Polygon(new int[] { 5, -5, -5 }, new int[] { 0, -5, 5 }, 3);
/*    */   }
/*    */ 
/*    */   public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
/*    */   {
/* 24 */     super.paintIcon(paramComponent, paramGraphics, paramInt1, paramInt2);
/* 25 */     paramGraphics.setColor(Color.black);
/* 26 */     this.poly.translate(paramInt1 + 5 - this.poly.xpoints[0], paramInt2 - this.poly.ypoints[0]);
/* 27 */     paramGraphics.fillPolygon(this.poly);
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     PlayIcon
 * JD-Core Version:    0.6.0
 */