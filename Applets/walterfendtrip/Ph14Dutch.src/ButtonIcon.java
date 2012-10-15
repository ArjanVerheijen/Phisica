/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ public class ButtonIcon extends ImageIcon
/*    */ {
/* 13 */   final int width = 30; final int height = 20;
/*    */ 
/*    */   public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
/*    */   {
/* 21 */     paramGraphics.setColor(paramComponent.getBackground());
/* 22 */     paramGraphics.fillRect(paramInt1 - 15, paramInt2 - 10, 30, 20);
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     ButtonIcon
 * JD-Core Version:    0.6.0
 */