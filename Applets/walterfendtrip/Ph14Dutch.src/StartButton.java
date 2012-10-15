/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ public final class StartButton extends JButton
/*    */ {
/*    */   private int state;
/*    */   private PlayIcon icon1;
/*    */   private PauseIcon icon2;
/*    */   private String[] text;
/*    */ 
/*    */   public StartButton(String paramString1, String paramString2, String paramString3)
/*    */   {
/* 27 */     setBackground(Color.yellow);
/* 28 */     this.icon1 = new PlayIcon();
/* 29 */     this.icon2 = new PauseIcon();
/* 30 */     this.text = new String[3];
/* 31 */     this.text[0] = paramString1; this.text[1] = paramString2; this.text[2] = paramString3;
/* 32 */     setState(0);
/* 33 */     setIconTextGap(50);
/* 34 */     setFocusPainted(false);
/*    */   }
/*    */ 
/*    */   public void setState(int paramInt)
/*    */   {
/* 41 */     this.state = paramInt;
/* 42 */     if (paramInt == 1) setIcon(this.icon2); else setIcon(this.icon1);
/* 43 */     setText(this.text[paramInt]);
/*    */   }
/*    */ 
/*    */   public void setState() {
/* 47 */     setState(this.state == 0 ? 1 : 3 - this.state);
/*    */   }
/*    */ 
/*    */   public int getState()
/*    */   {
/* 53 */     return this.state;
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     StartButton
 * JD-Core Version:    0.6.0
 */