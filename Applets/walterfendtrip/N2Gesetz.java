/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class N2Gesetz extends PhApplet
/*     */   implements Runnable, ActionListener, MouseMotionListener
/*     */ {
/*     */   final int width0 = 480;
/*     */   final int xStart = 40;
/*     */   final int length = 200;
/*     */   Thread thr;
/*     */   Font fC;
/*     */   Font fCLarge;
/*     */   N2Gesetz.Canvas1 cv;
/*     */   GBLJPanel p;
/*     */   JTextField tf1;
/*     */   JTextField tf2;
/*     */   JTextField tf3;
/*     */   JButton bReset;
/*     */   JButton bStart;
/*     */   JButton bDiagram;
/*     */   JTextArea li;
/*     */   JScrollPane sp;
/*     */   int state;
/*     */   boolean diagram;
/*     */   double M;
/*     */   double m;
/*     */   double my;
/*     */   final double g = 9.810000000000001D;
/*     */   double a;
/*     */   int ls;
/*     */   double xLS;
/*     */   double tLS;
/*     */   double t;
/*     */   double x;
/*     */   Vector s;
/*     */ 
/*     */   public N2Gesetz()
/*     */   {
/*  16 */     this.width0 = 480;
/*  17 */     this.xStart = 40;
/*  18 */     this.length = 200;
/*     */ 
/*  32 */     this.g = 9.810000000000001D;
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/*  44 */     super.start();
/*  45 */     this.fC = new Font("Courier", 0, 12);
/*  46 */     this.fCLarge = new Font("Courier", 1, 16);
/*  47 */     this.M = 0.1D; this.m = 0.001D; this.my = 0.0D;
/*  48 */     this.a = ((this.m - this.my * this.M) * 9.810000000000001D / (this.M + this.m));
/*  49 */     this.cv = new N2Gesetz.Canvas1(); this.cv.setBackground(this.BG);
/*  50 */     this.cv.setBounds(0, 0, 480, this.height);
/*  51 */     this.cp.add(this.cv);
/*  52 */     this.p = new GBLJPanel(this.PAN);
/*  53 */     this.p.setBounds(480, 0, this.width - 480, this.height);
/*  54 */     this.bReset = new JButton(text(2));
/*  55 */     this.p.add(this.bReset, Color.cyan, 0, 0, 3, 5, 10, 0, 10);
/*  56 */     this.bStart = new JButton(text(3));
/*  57 */     this.p.add(this.bStart, Color.yellow, 0, 1, 3, 10, 10, 5, 10);
/*  58 */     this.bDiagram = new JButton(text(5));
/*  59 */     this.p.add(this.bDiagram, Color.white, 0, 2, 3, 5, 10, 5, 10);
/*  60 */     this.p.add(new JLabel(text(6)), this.PAN, 0, 3, 3, 5, 10, 0, 10);
/*  61 */     this.p.add(new JLabel("M = "), this.PAN, this.fC, 0, 4, 1, 0, 10, 5, 0);
/*  62 */     this.tf1 = new JTextField(4);
/*  63 */     this.p.add(this.tf1, Color.white, this.fC, 1, 4, 1, 0, 0, 5, 0);
/*  64 */     this.p.add(new JLabel("g"), this.PAN, this.fC, 2, 4, 1, 0, 5, 5, 10);
/*  65 */     this.p.add(new JLabel(text(7)), this.PAN, 0, 5, 3, 5, 10, 0, 10);
/*  66 */     this.p.add(new JLabel("m = "), this.PAN, this.fC, 0, 6, 1, 0, 10, 5, 0);
/*  67 */     this.tf2 = new JTextField(5);
/*  68 */     this.p.add(this.tf2, Color.white, this.fC, 1, 6, 1, 0, 0, 5, 0);
/*  69 */     this.p.add(new JLabel("g"), this.PAN, this.fC, 2, 6, 1, 0, 5, 5, 10);
/*  70 */     this.p.add(new JLabel(text(8)), this.PAN, 0, 7, 3, 5, 10, 0, 10);
/*  71 */     this.p.add(new JLabel("µ = "), this.PAN, this.fC, 0, 8, 1, 0, 10, 5, 0);
/*  72 */     this.tf3 = new JTextField(6);
/*  73 */     this.p.add(this.tf3, Color.white, this.fC, 1, 8, 1, 0, 0, 5, 0);
/*  74 */     this.p.add(new JLabel(text(9)), this.PAN, 0, 9, 3, 5, 10, 0, 10);
/*  75 */     this.li = new JTextArea(3, 20);
/*  76 */     this.li.append("s         t         \n");
/*  77 */     this.li.append("--------  --------  \n");
/*  78 */     this.li.setEditable(false); this.li.setFont(this.fC);
/*  79 */     this.sp = new JScrollPane(this.li);
/*  80 */     this.sp.setPreferredSize(new Dimension(100, 36));
/*  81 */     this.sp.setHorizontalScrollBarPolicy(31);
/*  82 */     this.sp.setVerticalScrollBarPolicy(22);
/*  83 */     this.p.add(this.sp, Color.white, this.fC, 0, 10, 3, 0, 10, 5, 10);
/*  84 */     this.p.add(new JLabel(text(14)), this.PAN, 0, 11, 3, 5, 10, 0, 10);
/*  85 */     this.p.add(new JLabel(text(15)), this.PAN, 0, 12, 3, 0, 10, 10, 10);
/*  86 */     this.cp.add(this.p); this.p.repaint();
/*  87 */     this.bStart.addActionListener(this);
/*  88 */     this.bDiagram.addActionListener(this);
/*  89 */     this.bReset.addActionListener(this);
/*  90 */     this.tf1.addActionListener(this);
/*  91 */     this.tf2.addActionListener(this);
/*  92 */     this.tf3.addActionListener(this);
/*  93 */     this.cv.addMouseMotionListener(this);
/*  94 */     updateTF(); newSeries();
/*  95 */     this.bDiagram.setEnabled(false); this.bReset.setEnabled(false);
/*  96 */     this.thr = new Thread(this); this.thr.start();
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 102 */     this.thr = null; this.cp.removeAll();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 109 */     long l1 = System.currentTimeMillis();
/* 110 */     this.p.repaint();
/* 111 */     while (this.thr == Thread.currentThread()) {
/* 112 */       this.cv.repaint();
/*     */       try { Thread.sleep(50L); } catch (InterruptedException localInterruptedException) {
/*     */       }
/* 115 */       long l2 = System.currentTimeMillis();
/* 116 */       if (this.state > 0) this.t += (l2 - l1) / 1000.0D;
/* 117 */       l1 = l2;
/*     */     }
/*     */   }
/*     */ 
/*     */   void updateTF()
/*     */   {
/* 124 */     this.tf1.setText(toString(this.M * 1000.0D, 0));
/* 125 */     this.tf2.setText(toString(this.m * 1000.0D, 1));
/* 126 */     this.tf3.setText(toString(this.my, 3));
/*     */   }
/*     */ 
/*     */   String stringVal(double paramDouble, int paramInt, String paramString)
/*     */   {
/* 132 */     String str = toString(paramDouble, paramInt);
/* 133 */     return str + " " + paramString;
/*     */   }
/*     */ 
/*     */   void diagram(Graphics paramGraphics, int paramInt1, int paramInt2)
/*     */   {
/* 142 */     paramGraphics.setColor(Color.black);
/* 143 */     paramGraphics.setFont(this.fC);
/* 144 */     paramGraphics.drawLine(paramInt1 - 10, paramInt2, paramInt1 + 130, paramInt2);
/* 145 */     paramGraphics.drawLine(paramInt1 + 120, paramInt2 - 3, paramInt1 + 130, paramInt2);
/* 146 */     paramGraphics.drawLine(paramInt1 + 120, paramInt2 + 3, paramInt1 + 130, paramInt2);
/* 147 */     for (int i = 1; i <= 5; i++) {
/* 148 */       paramGraphics.drawLine(paramInt1 + i * 20, paramInt2 - 3, paramInt1 + i * 20, paramInt2 + 3);
/* 149 */       paramGraphics.drawString("" + i, paramInt1 + i * 20 - 3, paramInt2 + 15);
/*     */     }
/* 151 */     paramGraphics.drawLine(paramInt1, paramInt2 + 10, paramInt1, paramInt2 - 230);
/* 152 */     paramGraphics.drawLine(paramInt1 - 3, paramInt2 - 220, paramInt1, paramInt2 - 230);
/* 153 */     paramGraphics.drawLine(paramInt1 + 3, paramInt2 - 220, paramInt1, paramInt2 - 230);
/* 154 */     for (i = 1; i <= 10; i++) {
/* 155 */       paramGraphics.drawLine(paramInt1 - 3, paramInt2 - i * 20, paramInt1 + 3, paramInt2 - i * 20);
/* 156 */       paramGraphics.drawString(toString(i / 10.0D, 1), paramInt1 - 25, paramInt2 - i * 20 + 5);
/*     */     }
/* 158 */     if (this.diagram)
/*     */     {
/*     */       int n;
/* 159 */       j = n = paramInt1;
/*     */       int i1;
/* 159 */       k = i1 = paramInt2;
/* 160 */       while ((n < paramInt1 + 140) && (i1 > paramInt2 - 220)) {
/* 161 */         j = n; k = i1; n = j + 2;
/* 162 */         d1 = (n - paramInt1) / 20.0D;
/* 163 */         d2 = this.a / 2.0D * d1 * d1; i1 = (int)Math.round(paramInt2 - d2 * 200.0D);
/* 164 */         paramGraphics.drawLine(j, k, n, i1);
/*     */       }
/*     */     }
/* 167 */     for (i = 0; i < this.s.size(); i++) {
/* 168 */       Double localDouble = (Double)this.s.elementAt(i);
/* 169 */       d2 = localDouble.doubleValue();
/* 170 */       d1 = Math.sqrt(2.0D * d2 / this.a);
/* 171 */       j = (int)Math.round(paramInt1 + d1 * 20.0D);
/* 172 */       k = (int)Math.round(paramInt2 - d2 * 200.0D);
/* 173 */       paramGraphics.fillRect(j - 2, k - 2, 5, 5);
/*     */     }
/* 175 */     if (this.state >= 2) {
/* 176 */       j = (int)Math.round(paramInt1 + this.tLS * 20.0D);
/* 177 */       k = (int)Math.round(paramInt2 - this.xLS * 200.0D);
/* 178 */       paramGraphics.fillRect(j - 2, k - 2, 5, 5);
/*     */     }
/* 180 */     paramGraphics.setColor(Color.red);
/* 181 */     double d1 = Math.min(this.t, Math.sqrt(2.0D / this.a));
/* 182 */     int j = (int)Math.round(paramInt1 + d1 * 20.0D);
/* 183 */     double d2 = this.a / 2.0D * d1 * d1;
/* 184 */     int k = (int)Math.round(paramInt2 - d2 * 200.0D);
/* 185 */     paramGraphics.fillOval(j - 2, k - 2, 5, 5);
/* 186 */     paramGraphics.setFont(HELV); paramGraphics.setColor(Color.black);
/* 187 */     paramGraphics.drawString("t", paramInt1 + 125, paramInt2 + 15);
/* 188 */     paramGraphics.drawString(text(11), paramInt1 + 113, paramInt2 + 30);
/* 189 */     paramGraphics.drawString("s", paramInt1 - 20, paramInt2 - 225);
/* 190 */     paramGraphics.drawString(text(12), paramInt1 - 37, paramInt2 - 210);
/*     */   }
/*     */ 
/*     */   void calculation()
/*     */   {
/* 196 */     this.M = (inputTF(this.tf1, 0.0D, 1000.0D, 0) / 1000.0D);
/* 197 */     this.m = (inputTF(this.tf2, 0.0D, 100.0D, 1) / 1000.0D);
/* 198 */     this.my = inputTF(this.tf3, 0.0D, 1.0D, 3);
/* 199 */     if (this.M + this.m > 0.0D) this.a = ((this.m - this.my * this.M) * 9.810000000000001D / (this.M + this.m)); else this.a = 0.0D;
/* 200 */     if (this.a < 0.0D) { this.a = 0.0D; enableTextFields(true); }
/* 201 */     setST();
/*     */   }
/*     */ 
/*     */   void newSeries()
/*     */   {
/* 207 */     this.s = new Vector();
/* 208 */     this.state = 0; this.diagram = false;
/* 209 */     this.t = 0.0D;
/* 210 */     this.bStart.setText(text(3));
/* 211 */     this.bStart.setEnabled(true);
/* 212 */     this.bDiagram.setEnabled(false);
/* 213 */     this.bReset.setEnabled(true);
/* 214 */     this.ls = 140;
/* 215 */     calculation();
/*     */   }
/*     */ 
/*     */   void updateList()
/*     */   {
/* 221 */     this.li.append(stringVal(this.xLS, 3, "m") + ";    " + stringVal(this.tLS, 3, "s\n"));
/*     */   }
/*     */ 
/*     */   void enableTextFields(boolean paramBoolean)
/*     */   {
/* 228 */     this.tf1.setEnabled(paramBoolean);
/* 229 */     this.tf2.setEnabled(paramBoolean);
/* 230 */     this.tf3.setEnabled(paramBoolean);
/*     */   }
/*     */ 
/*     */   void actionEnd(Object paramObject)
/*     */   {
/* 236 */     if (paramObject == this.bReset) {
/* 237 */       this.li.setText("");
/* 238 */       this.li.append("s         t         \n");
/* 239 */       this.li.append("--------  --------  \n");
/*     */     }
/* 241 */     if ((paramObject == this.bStart) && (this.state >= 2)) {
/* 242 */       updateList(); this.state = 0;
/*     */     }
/* 244 */     JScrollBar localJScrollBar = this.sp.getVerticalScrollBar();
/* 245 */     localJScrollBar.setValue(localJScrollBar.getMaximum());
/* 246 */     this.p.repaint();
/*     */   }
/*     */ 
/*     */   void setST()
/*     */   {
/* 252 */     this.xLS = ((this.ls - 40) / 200.0D);
/* 253 */     this.tLS = Math.sqrt(2.0D * this.xLS / this.a);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 261 */     this.bReset.setEnabled(true);
/* 262 */     Object localObject = paramActionEvent.getSource();
/* 263 */     if (localObject == this.bReset) {
/* 264 */       newSeries();
/* 265 */       enableTextFields(true);
/*     */     }
/* 267 */     else if ((localObject == this.bStart) && (this.state == 0)) {
/* 268 */       this.bStart.setEnabled(false);
/* 269 */       enableTextFields(false);
/* 270 */       this.t = 0.0D; this.state = 1;
/* 271 */       calculation();
/*     */     }
/* 273 */     else if ((localObject == this.bStart) && (this.state >= 2)) {
/* 274 */       this.bDiagram.setEnabled(this.s.size() >= 4);
/* 275 */       this.bStart.setText(text(3));
/* 276 */       this.s.addElement(new Double(this.xLS));
/* 277 */       this.t = 0.0D; this.state = 0;
/* 278 */       updateList();
/*     */     }
/* 280 */     else if (localObject == this.bDiagram) { this.diagram = true;
/* 281 */     } else if ((localObject instanceof JTextField)) { newSeries(); }
/* 282 */     actionEnd(localObject);
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent)
/*     */   {
/* 288 */     if (this.state != 0) return;
/* 289 */     int i = paramMouseEvent.getX(); int j = paramMouseEvent.getY();
/* 290 */     if ((i < this.ls - 40) || (i > this.ls + 40) || (j < 10) || (j > 90)) return;
/* 291 */     this.ls = Math.min(i, 240);
/* 292 */     this.ls = Math.max(this.ls, 50);
/* 293 */     setST();
/*     */   }
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) {
/*     */   }
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) {
/*     */   }
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) {
/*     */   }
/*     */   public void mouseExited(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   class Canvas1 extends JPanel {
/*     */     Canvas1() {
/*     */     }
/*     */ 
/*     */     void wagon(Graphics paramGraphics) {
/* 311 */       int k = 240;
/* 312 */       int i = (int)Math.round(N2Gesetz.this.x * 200.0D);
/* 313 */       int j = (int)Math.round(N2Gesetz.this.t * 20.0D);
/* 314 */       paramGraphics.setColor(Color.blue);
/* 315 */       paramGraphics.fillRect(40 + i - 40, 50, 40, 20);
/* 316 */       paramGraphics.drawLine(40 + i, 58, k + 10, 58);
/* 317 */       paramGraphics.drawArc(k - 2, 58, 22, 22, 0, 90);
/* 318 */       paramGraphics.drawLine(k + 20, 68, k + 20, 100 + i);
/* 319 */       paramGraphics.fillRect(k + 20 - 2, 100 + i, 5, 6);
/* 320 */       paramGraphics.setColor(Color.red);
/* 321 */       paramGraphics.fillOval(40 + i - 2, 56, 5, 5);
/*     */     }
/*     */ 
/*     */     void clock(Graphics paramGraphics)
/*     */     {
/* 327 */       paramGraphics.setColor(Color.gray); paramGraphics.fillRect(90, 150, 100, 30);
/* 328 */       paramGraphics.setColor(Color.black); paramGraphics.fillRect(100, 155, 80, 20);
/* 329 */       paramGraphics.setColor(Color.red); paramGraphics.setFont(N2Gesetz.this.fCLarge);
/* 330 */       paramGraphics.drawString(N2Gesetz.this.stringVal(N2Gesetz.this.t < N2Gesetz.this.tLS ? N2Gesetz.this.t : N2Gesetz.this.tLS, 3, "s"), 106, 170);
/*     */     }
/*     */ 
/*     */     void writeValues(Graphics paramGraphics)
/*     */     {
/* 336 */       paramGraphics.setFont(WFApplet.HELV); paramGraphics.setColor(Color.black);
/* 337 */       String str = "s = " + N2Gesetz.this.stringVal(N2Gesetz.this.xLS, 3, "m");
/* 338 */       paramGraphics.drawString(str, 100, 260);
/* 339 */       str = "t = ";
/* 340 */       if (N2Gesetz.this.state >= 2) str = str + N2Gesetz.this.stringVal(N2Gesetz.this.tLS, 3, "s");
/* 341 */       paramGraphics.drawString(str, 100, 280);
/* 342 */       str = "a =  ";
/* 343 */       paramGraphics.drawString(str, 100, 300);
/* 344 */       FontMetrics localFontMetrics = getFontMetrics(WFApplet.HELV);
/*     */ 
/* 346 */       int i = localFontMetrics.stringWidth(str);
/* 347 */       paramGraphics.fillRect(100 + i, 295, 21, 2);
/* 348 */       int j = localFontMetrics.stringWidth("2s");
/* 349 */       paramGraphics.drawString("2s", 100 + i + 10 - j / 2, 293);
/* 350 */       j = localFontMetrics.stringWidth("t²");
/* 351 */       paramGraphics.drawString("t²", 100 + i + 10 - j / 2, 308);
/* 352 */       if (N2Gesetz.this.state <= 1) return;
/* 353 */       str = "  = " + N2Gesetz.this.stringVal(N2Gesetz.this.a, 3, "m/s²");
/* 354 */       paramGraphics.drawString(str, 100 + i + 20, 300);
/*     */     }
/*     */ 
/*     */     public void paint(Graphics paramGraphics)
/*     */     {
/* 360 */       if ((N2Gesetz.this.state == 1) && (N2Gesetz.this.x > N2Gesetz.this.xLS)) {
/* 361 */         N2Gesetz.this.state = 2;
/* 362 */         N2Gesetz.this.bStart.setText(N2Gesetz.this.text(4));
/* 363 */         N2Gesetz.this.bStart.setEnabled(true);
/*     */       }
/* 365 */       if ((N2Gesetz.this.state <= 2) && (N2Gesetz.this.x > 1.0D)) N2Gesetz.this.state = 3;
/* 366 */       switch (N2Gesetz.this.state) { case 0:
/* 367 */         N2Gesetz.this.x = 0.0D; break;
/*     */       case 1:
/*     */       case 2:
/* 368 */         N2Gesetz.this.x = (N2Gesetz.this.a / 2.0D * N2Gesetz.this.t * N2Gesetz.this.t); break;
/*     */       case 3:
/* 369 */         N2Gesetz.this.x = 1.0D;
/*     */       }
/* 371 */       paramGraphics.setFont(N2Gesetz.this.fC);
/* 372 */       super.paint(paramGraphics);
/* 373 */       paramGraphics.setColor(Color.orange);
/* 374 */       paramGraphics.fillRect(0, 60, 250, 20);
/* 375 */       paramGraphics.fillOval(240, 60, 20, 20);
/* 376 */       paramGraphics.setColor(Color.black); paramGraphics.drawLine(0, 60, 0, 80);
/* 377 */       paramGraphics.drawLine(0, 60, 250, 60);
/* 378 */       paramGraphics.drawLine(0, 80, 250, 80);
/* 379 */       paramGraphics.drawArc(240, 60, 20, 20, 270, 180);
/* 380 */       paramGraphics.setColor(Color.red);
/* 381 */       paramGraphics.fillRect(240, 55, 6, 15);
/* 382 */       for (int i = 0; i < 10; i++) {
/* 383 */         if (i % 2 == 0) paramGraphics.setColor(Color.black); else
/* 384 */           paramGraphics.setColor(Color.green);
/* 385 */         paramGraphics.fillRect(40 + i * 20, 85, 20, 10);
/* 386 */         paramGraphics.setColor(Color.black);
/* 387 */         paramGraphics.drawRect(40, 85, 200, 10);
/*     */       }
/* 389 */       wagon(paramGraphics); clock(paramGraphics);
/* 390 */       N2Gesetz.this.diagram(paramGraphics, 310, 300);
/* 391 */       writeValues(paramGraphics);
/* 392 */       paramGraphics.drawString(N2Gesetz.this.text(10), N2Gesetz.this.ls + 10, 50);
/* 393 */       paramGraphics.setFont(N2Gesetz.this.fC);
/* 394 */       paramGraphics.fillRect(N2Gesetz.this.ls - 3, 40, 7, 20);
/* 395 */       paramGraphics.drawLine(N2Gesetz.this.ls - 20, 30, N2Gesetz.this.ls + 20, 30);
/* 396 */       if (N2Gesetz.this.ls > 50) {
/* 397 */         paramGraphics.drawLine(N2Gesetz.this.ls - 10, 27, N2Gesetz.this.ls - 20, 30);
/* 398 */         paramGraphics.drawLine(N2Gesetz.this.ls - 10, 33, N2Gesetz.this.ls - 20, 30);
/*     */       }
/* 400 */       if (N2Gesetz.this.ls < 240) {
/* 401 */         paramGraphics.drawLine(N2Gesetz.this.ls + 10, 27, N2Gesetz.this.ls + 20, 30);
/* 402 */         paramGraphics.drawLine(N2Gesetz.this.ls + 10, 33, N2Gesetz.this.ls + 20, 30);
/*     */       }
/* 404 */       if (N2Gesetz.this.my * N2Gesetz.this.M >= N2Gesetz.this.m) {
/* 405 */         paramGraphics.setColor(Color.red); paramGraphics.setFont(WFApplet.HELV);
/* 406 */         paramGraphics.drawString(N2Gesetz.this.text(13), 80, 120);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\N2Gesetz.jar
 * Qualified Name:     N2Gesetz
 * JD-Core Version:    0.6.0
 */