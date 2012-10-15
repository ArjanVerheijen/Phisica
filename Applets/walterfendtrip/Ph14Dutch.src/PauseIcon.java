/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public final class PauseIcon extends ButtonIcon
/*    */ {
/*    */   public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
/*    */   {
/* 16 */     super.paintIcon(paramComponent, paramGraphics, paramInt1, paramInt2);
/* 17 */     paramGraphics.setColor(Color.black);
/* 18 */     paramGraphics.fillRect(paramInt1 - 5, paramInt2 - 3, 10, 6);
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     PauseIcon
 * JD-Core Version:    0.6.0
 */