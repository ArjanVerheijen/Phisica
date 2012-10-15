/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Polygon;
/*    */ 
/*    */ public final class ResetIcon extends ButtonIcon
/*    */ {
/*    */   private Polygon poly1;
/*    */   private Polygon poly2;
/*    */ 
/*    */   ResetIcon()
/*    */   {
/* 15 */     this.poly1 = new Polygon(new int[] { 10, 0, 10 }, new int[] { 5, 0, -5 }, 3);
/* 16 */     this.poly2 = new Polygon(new int[] { 0, -10, 0 }, new int[] { 5, 0, -5 }, 3);
/*    */   }
/*    */ 
/*    */   public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
/*    */   {
/* 25 */     super.paintIcon(paramComponent, paramGraphics, paramInt1, paramInt2);
/* 26 */     int i = paramInt1 - this.poly1.xpoints[1]; int j = paramInt2 - this.poly1.ypoints[1];
/* 27 */     this.poly1.translate(i, j); this.poly2.translate(i, j);
/* 28 */     paramGraphics.setColor(Color.black);
/* 29 */     paramGraphics.fillPolygon(this.poly1); paramGraphics.fillPolygon(this.poly2);
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     ResetIcon
 * JD-Core Version:    0.6.0
 */