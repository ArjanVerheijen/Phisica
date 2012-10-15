/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class GBLJPanel extends JPanel
/*     */ {
/*     */   Color c;
/*     */   Vector components;
/*  15 */   final Font fH = WFApplet.HELV;
/*  16 */   final Color BLACK = Color.black;
/*     */   private boolean legal;
/*     */ 
/*     */   GBLJPanel(Color paramColor)
/*     */   {
/*  23 */     this.c = paramColor;
/*  24 */     setLayout(new GridBagLayout());
/*  25 */     setBackground(paramColor);
/*  26 */     this.components = new Vector(1, 1);
/*  27 */     this.legal = false;
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor1, Color paramColor2, Font paramFont, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/*  42 */     if (((paramJComponent instanceof JLabel)) && (!this.legal)) {
/*  43 */       int i = 0;
/*     */ 
/*  45 */       String str = ((JLabel)paramJComponent).getText();
                int j;
/*  46 */       for (j = 0; j < 11; j++) try {
/*  47 */           i += str.charAt(j);
/*     */         }
/*     */         catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/*     */         } if (i == 895) {
/*  51 */         for (j = 0; j < this.components.size(); j++) {
/*  52 */           JComponent localJComponent = (JComponent)this.components.elementAt(j);
/*  53 */           if (this.legal) continue; add(localJComponent);
/*     */         }
/*  55 */         this.legal = true;
/*     */       }
/*     */     }
/*  58 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  59 */     localGridBagConstraints.gridx = paramInt1; localGridBagConstraints.gridy = paramInt2; localGridBagConstraints.gridwidth = paramInt3; localGridBagConstraints.gridheight = 1;
/*  60 */     localGridBagConstraints.fill = 2; localGridBagConstraints.anchor = 10;
/*  61 */     localGridBagConstraints.weightx = paramInt3; localGridBagConstraints.weighty = 1.0D;
/*  62 */     localGridBagConstraints.insets = new Insets(paramInt4, paramInt5, paramInt6, paramInt7);
/*  63 */     ((GridBagLayout)getLayout()).setConstraints(paramJComponent, localGridBagConstraints);
/*  64 */     paramJComponent.setFont(paramFont);
/*  65 */     paramJComponent.setBackground(paramColor1);
/*  66 */     paramJComponent.setForeground(paramColor2);
/*  67 */     if (this.legal) add(paramJComponent);
/*  68 */     this.components.addElement(paramJComponent);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor, Font paramFont, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/*  75 */     add(paramJComponent, paramColor, this.BLACK, paramFont, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor1, Color paramColor2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/*  80 */     add(paramJComponent, paramColor1, paramColor2, this.fH, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/*  85 */     add(paramJComponent, paramColor, this.BLACK, this.fH, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor1, Color paramColor2, int paramInt1, int paramInt2, int paramInt3) {
/*  89 */     add(paramJComponent, paramColor1, paramColor2, this.fH, 0, paramInt1, 1, paramInt2, 10, paramInt3, 10);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, Color paramColor, int paramInt1, int paramInt2, int paramInt3) {
/*  93 */     add(paramJComponent, paramColor, this.BLACK, this.fH, 0, paramInt1, 1, paramInt2, 10, paramInt3, 10);
/*     */   }
/*     */ 
/*     */   void add(JComponent paramJComponent, int paramInt1, int paramInt2, int paramInt3) {
/*  97 */     add(paramJComponent, paramJComponent.getBackground(), this.BLACK, this.fH, 0, paramInt1, 1, paramInt2, 10, paramInt3, 10);
/*     */   }
/*     */ 
/*     */   void setLegal(GBLJPanel paramGBLJPanel)
/*     */   {
/* 103 */     this.legal = paramGBLJPanel.legal;
/*     */   }
/*     */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     GBLJPanel
 * JD-Core Version:    0.6.0
 */