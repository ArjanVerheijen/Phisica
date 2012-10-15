/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ public final class ResetButton extends JButton
/*    */ {
/*    */   public ResetButton(String paramString)
/*    */   {
/* 15 */     setBackground(Color.cyan);
/* 16 */     setIcon(new ResetIcon());
/* 17 */     setText(paramString);
/* 18 */     setIconTextGap(50);
/* 19 */     setFocusPainted(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     ResetButton
 * JD-Core Version:    0.6.0
 */