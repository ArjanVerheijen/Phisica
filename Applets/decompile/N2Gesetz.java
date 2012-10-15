import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class N2Gesetz extends PhApplet
  implements Runnable, ActionListener, MouseMotionListener
{
  final int width0 = 480;
  final int xStart = 40;
  final int length = 200;
  Thread thr;
  Font fC;
  Font fCLarge;
  N2Gesetz.Canvas1 cv;
  GBLJPanel p;
  JTextField tf1;
  JTextField tf2;
  JTextField tf3;
  JButton bReset;
  JButton bStart;
  JButton bDiagram;
  JTextArea li;
  JScrollPane sp;
  int state;
  boolean diagram;
  double M;
  double m;
  double my;
  final double g = 9.810000000000001D;
  double a;
  int ls;
  double xLS;
  double tLS;
  double t;
  double x;
  Vector s;

  public N2Gesetz()
  {
    this.width0 = 480;
    this.xStart = 40;
    this.length = 200;

    this.g = 9.810000000000001D;
  }

  public void start()
  {
    super.start();
    this.fC = new Font("Courier", 0, 12);
    this.fCLarge = new Font("Courier", 1, 16);
    this.M = 0.1D; this.m = 0.001D; this.my = 0.0D;
    this.a = ((this.m - this.my * this.M) * 9.810000000000001D / (this.M + this.m));
    this.cv = new N2Gesetz.Canvas1(); this.cv.setBackground(this.BG);
    this.cv.setBounds(0, 0, 480, this.height);
    this.cp.add(this.cv);
    this.p = new GBLJPanel(this.PAN);
    this.p.setBounds(480, 0, this.width - 480, this.height);
    this.bReset = new JButton(text(2));
    this.p.add(this.bReset, Color.cyan, 0, 0, 3, 5, 10, 0, 10);
    this.bStart = new JButton(text(3));
    this.p.add(this.bStart, Color.yellow, 0, 1, 3, 10, 10, 5, 10);
    this.bDiagram = new JButton(text(5));
    this.p.add(this.bDiagram, Color.white, 0, 2, 3, 5, 10, 5, 10);
    this.p.add(new JLabel(text(6)), this.PAN, 0, 3, 3, 5, 10, 0, 10);
    this.p.add(new JLabel("M = "), this.PAN, this.fC, 0, 4, 1, 0, 10, 5, 0);
    this.tf1 = new JTextField(4);
    this.p.add(this.tf1, Color.white, this.fC, 1, 4, 1, 0, 0, 5, 0);
    this.p.add(new JLabel("g"), this.PAN, this.fC, 2, 4, 1, 0, 5, 5, 10);
    this.p.add(new JLabel(text(7)), this.PAN, 0, 5, 3, 5, 10, 0, 10);
    this.p.add(new JLabel("m = "), this.PAN, this.fC, 0, 6, 1, 0, 10, 5, 0);
    this.tf2 = new JTextField(5);
    this.p.add(this.tf2, Color.white, this.fC, 1, 6, 1, 0, 0, 5, 0);
    this.p.add(new JLabel("g"), this.PAN, this.fC, 2, 6, 1, 0, 5, 5, 10);
    this.p.add(new JLabel(text(8)), this.PAN, 0, 7, 3, 5, 10, 0, 10);
    this.p.add(new JLabel("µ = "), this.PAN, this.fC, 0, 8, 1, 0, 10, 5, 0);
    this.tf3 = new JTextField(6);
    this.p.add(this.tf3, Color.white, this.fC, 1, 8, 1, 0, 0, 5, 0);
    this.p.add(new JLabel(text(9)), this.PAN, 0, 9, 3, 5, 10, 0, 10);
    this.li = new JTextArea(3, 20);
    this.li.append("s         t         \n");
    this.li.append("--------  --------  \n");
    this.li.setEditable(false); this.li.setFont(this.fC);
    this.sp = new JScrollPane(this.li);
    this.sp.setPreferredSize(new Dimension(100, 36));
    this.sp.setHorizontalScrollBarPolicy(31);
    this.sp.setVerticalScrollBarPolicy(22);
    this.p.add(this.sp, Color.white, this.fC, 0, 10, 3, 0, 10, 5, 10);
    this.p.add(new JLabel(text(14)), this.PAN, 0, 11, 3, 5, 10, 0, 10);
    this.p.add(new JLabel(text(15)), this.PAN, 0, 12, 3, 0, 10, 10, 10);
    this.cp.add(this.p); this.p.repaint();
    this.bStart.addActionListener(this);
    this.bDiagram.addActionListener(this);
    this.bReset.addActionListener(this);
    this.tf1.addActionListener(this);
    this.tf2.addActionListener(this);
    this.tf3.addActionListener(this);
    this.cv.addMouseMotionListener(this);
    updateTF(); newSeries();
    this.bDiagram.setEnabled(false); this.bReset.setEnabled(false);
    this.thr = new Thread(this); this.thr.start();
  }

  public void stop()
  {
    this.thr = null; this.cp.removeAll();
  }

  public void run()
  {
    long l1 = System.currentTimeMillis();
    this.p.repaint();
    while (this.thr == Thread.currentThread()) {
      this.cv.repaint();
      try { Thread.sleep(50L); } catch (InterruptedException localInterruptedException) {
      }
      long l2 = System.currentTimeMillis();
      if (this.state > 0) this.t += l2 - l1 / 1000.0D;
      l1 = l2;
    }
  }

  void updateTF()
  {
    this.tf1.setText(toString(this.M * 1000.0D, 0));
    this.tf2.setText(toString(this.m * 1000.0D, 1));
    this.tf3.setText(toString(this.my, 3));
  }

  String stringVal(double paramDouble, int paramInt, String paramString)
  {
    String str = toString(paramDouble, paramInt);
    return str + " " + paramString;
  }

  void diagram(Graphics paramGraphics, int paramInt1, int paramInt2)
  {
    paramGraphics.setColor(Color.black);
    paramGraphics.setFont(this.fC);
    paramGraphics.drawLine(paramInt1 - 10, paramInt2, paramInt1 + 130, paramInt2);
    paramGraphics.drawLine(paramInt1 + 120, paramInt2 - 3, paramInt1 + 130, paramInt2);
    paramGraphics.drawLine(paramInt1 + 120, paramInt2 + 3, paramInt1 + 130, paramInt2);
    for (int i = 1; i <= 5; i++) {
      paramGraphics.drawLine(paramInt1 + i * 20, paramInt2 - 3, paramInt1 + i * 20, paramInt2 + 3);
      paramGraphics.drawString("" + i, paramInt1 + i * 20 - 3, paramInt2 + 15);
    }
    paramGraphics.drawLine(paramInt1, paramInt2 + 10, paramInt1, paramInt2 - 230);
    paramGraphics.drawLine(paramInt1 - 3, paramInt2 - 220, paramInt1, paramInt2 - 230);
    paramGraphics.drawLine(paramInt1 + 3, paramInt2 - 220, paramInt1, paramInt2 - 230);
    for (i = 1; i <= 10; i++) {
      paramGraphics.drawLine(paramInt1 - 3, paramInt2 - i * 20, paramInt1 + 3, paramInt2 - i * 20);
      paramGraphics.drawString(toString(i / 10.0D, 1), paramInt1 - 25, paramInt2 - i * 20 + 5);
    }
    if (this.diagram)
    {
      int n;
      j = n = paramInt1;
      int i1;
      k = i1 = paramInt2;
      while ((n < paramInt1 + 140) && (i1 > paramInt2 - 220)) {
        j = n; k = i1; n = j + 2;
        d1 = n - paramInt1 / 20.0D;
        d2 = this.a / 2.0D * d1 * d1; i1 = (int)Math.round(paramInt2 - d2 * 200.0D);
        paramGraphics.drawLine(j, k, n, i1);
      }
    }
    for (i = 0; i < this.s.size(); i++) {
      Double localDouble = (Double)this.s.elementAt(i);
      d2 = localDouble.doubleValue();
      d1 = Math.sqrt(2.0D * d2 / this.a);
      j = (int)Math.round(paramInt1 + d1 * 20.0D);
      k = (int)Math.round(paramInt2 - d2 * 200.0D);
      paramGraphics.fillRect(j - 2, k - 2, 5, 5);
    }
    if (this.state >= 2) {
      j = (int)Math.round(paramInt1 + this.tLS * 20.0D);
      k = (int)Math.round(paramInt2 - this.xLS * 200.0D);
      paramGraphics.fillRect(j - 2, k - 2, 5, 5);
    }
    paramGraphics.setColor(Color.red);
    double d1 = Math.min(this.t, Math.sqrt(2.0D / this.a));
    int j = (int)Math.round(paramInt1 + d1 * 20.0D);
    double d2 = this.a / 2.0D * d1 * d1;
    int k = (int)Math.round(paramInt2 - d2 * 200.0D);
    paramGraphics.fillOval(j - 2, k - 2, 5, 5);
    paramGraphics.setFont(HELV); paramGraphics.setColor(Color.black);
    paramGraphics.drawString("t", paramInt1 + 125, paramInt2 + 15);
    paramGraphics.drawString(text(11), paramInt1 + 113, paramInt2 + 30);
    paramGraphics.drawString("s", paramInt1 - 20, paramInt2 - 225);
    paramGraphics.drawString(text(12), paramInt1 - 37, paramInt2 - 210);
  }

  void calculation()
  {
    this.M = (inputTF(this.tf1, 0.0D, 1000.0D, 0) / 1000.0D);
    this.m = (inputTF(this.tf2, 0.0D, 100.0D, 1) / 1000.0D);
    this.my = inputTF(this.tf3, 0.0D, 1.0D, 3);
    if (this.M + this.m > 0.0D) this.a = ((this.m - this.my * this.M) * 9.810000000000001D / (this.M + this.m)); else this.a = 0.0D;
    if (this.a < 0.0D) { this.a = 0.0D; enableTextFields(true); }
    setST();
  }

  void newSeries()
  {
    this.s = new Vector();
    this.state = 0; this.diagram = false;
    this.t = 0.0D;
    this.bStart.setText(text(3));
    this.bStart.setEnabled(true);
    this.bDiagram.setEnabled(false);
    this.bReset.setEnabled(true);
    this.ls = 140;
    calculation();
  }

  void updateList()
  {
    this.li.append(stringVal(this.xLS, 3, "m") + ";    " + stringVal(this.tLS, 3, "s\n"));
  }

  void enableTextFields(boolean paramBoolean)
  {
    this.tf1.setEnabled(paramBoolean);
    this.tf2.setEnabled(paramBoolean);
    this.tf3.setEnabled(paramBoolean);
  }

  void actionEnd(Object paramObject)
  {
    if (paramObject == this.bReset) {
      this.li.setText("");
      this.li.append("s         t         \n");
      this.li.append("--------  --------  \n");
    }
    if ((paramObject == this.bStart) && (this.state >= 2)) {
      updateList(); this.state = 0;
    }
    JScrollBar localJScrollBar = this.sp.getVerticalScrollBar();
    localJScrollBar.setValue(localJScrollBar.getMaximum());
    this.p.repaint();
  }

  void setST()
  {
    this.xLS = (this.ls - 40 / 200.0D);
    this.tLS = Math.sqrt(2.0D * this.xLS / this.a);
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    this.bReset.setEnabled(true);
    Object localObject = paramActionEvent.getSource();
    if (localObject == this.bReset) {
      newSeries();
      enableTextFields(true);
    }
    else if ((localObject == this.bStart) && (this.state == 0)) {
      this.bStart.setEnabled(false);
      enableTextFields(false);
      this.t = 0.0D; this.state = 1;
      calculation();
    }
    else if ((localObject == this.bStart) && (this.state >= 2)) {
      this.bDiagram.setEnabled(this.s.size() >= 4);
      this.bStart.setText(text(3));
      this.s.addElement(new Double(this.xLS));
      this.t = 0.0D; this.state = 0;
      updateList();
    }
    else if (localObject == this.bDiagram) { this.diagram = true;
    } else if ((localObject instanceof JTextField)) { newSeries(); }
    actionEnd(localObject);
  }

  public void mouseDragged(MouseEvent paramMouseEvent)
  {
    if (this.state != 0) return;
    int i = paramMouseEvent.getX(); int j = paramMouseEvent.getY();
    if ((i < this.ls - 40) || (i > this.ls + 40) || (j < 10) || (j > 90)) return;
    this.ls = Math.min(i, 240);
    this.ls = Math.max(this.ls, 50);
    setST();
  }
  public void mouseClicked(MouseEvent paramMouseEvent) {
  }
  public void mouseReleased(MouseEvent paramMouseEvent) {
  }
  public void mouseMoved(MouseEvent paramMouseEvent) {
  }
  public void mouseEntered(MouseEvent paramMouseEvent) {
  }
  public void mouseExited(MouseEvent paramMouseEvent) {
  }

  class Canvas1 extends JPanel {
    Canvas1() {
    }

    void wagon(Graphics paramGraphics) {
      int k = 240;
      int i = (int)Math.round(N2Gesetz.this.x * 200.0D);
      int j = (int)Math.round(N2Gesetz.this.t * 20.0D);
      paramGraphics.setColor(Color.blue);
      paramGraphics.fillRect(40 + i - 40, 50, 40, 20);
      paramGraphics.drawLine(40 + i, 58, k + 10, 58);
      paramGraphics.drawArc(k - 2, 58, 22, 22, 0, 90);
      paramGraphics.drawLine(k + 20, 68, k + 20, 100 + i);
      paramGraphics.fillRect(k + 20 - 2, 100 + i, 5, 6);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillOval(40 + i - 2, 56, 5, 5);
    }

    void clock(Graphics paramGraphics)
    {
      paramGraphics.setColor(Color.gray); paramGraphics.fillRect(90, 150, 100, 30);
      paramGraphics.setColor(Color.black); paramGraphics.fillRect(100, 155, 80, 20);
      paramGraphics.setColor(Color.red); paramGraphics.setFont(N2Gesetz.this.fCLarge);
      paramGraphics.drawString(N2Gesetz.this.stringVal(N2Gesetz.this.t < N2Gesetz.this.tLS ? N2Gesetz.this.t : N2Gesetz.this.tLS, 3, "s"), 106, 170);
    }

    void writeValues(Graphics paramGraphics)
    {
      paramGraphics.setFont(WFApplet.HELV); paramGraphics.setColor(Color.black);
      String str = "s = " + N2Gesetz.this.stringVal(N2Gesetz.this.xLS, 3, "m");
      paramGraphics.drawString(str, 100, 260);
      str = "t = ";
      if (N2Gesetz.this.state >= 2) str = str + N2Gesetz.this.stringVal(N2Gesetz.this.tLS, 3, "s");
      paramGraphics.drawString(str, 100, 280);
      str = "a =  ";
      paramGraphics.drawString(str, 100, 300);
      FontMetrics localFontMetrics = getFontMetrics(WFApplet.HELV);

      int i = localFontMetrics.stringWidth(str);
      paramGraphics.fillRect(100 + i, 295, 21, 2);
      int j = localFontMetrics.stringWidth("2s");
      paramGraphics.drawString("2s", 100 + i + 10 - j / 2, 293);
      j = localFontMetrics.stringWidth("t²");
      paramGraphics.drawString("t²", 100 + i + 10 - j / 2, 308);
      if (N2Gesetz.this.state <= 1) return;
      str = "  = " + N2Gesetz.this.stringVal(N2Gesetz.this.a, 3, "m/s²");
      paramGraphics.drawString(str, 100 + i + 20, 300);
    }

    public void paint(Graphics paramGraphics)
    {
      if ((N2Gesetz.this.state == 1) && (N2Gesetz.this.x > N2Gesetz.this.xLS)) {
        N2Gesetz.this.state = 2;
        N2Gesetz.this.bStart.setText(N2Gesetz.this.text(4));
        N2Gesetz.this.bStart.setEnabled(true);
      }
      if ((N2Gesetz.this.state <= 2) && (N2Gesetz.this.x > 1.0D)) N2Gesetz.this.state = 3;
      switch (N2Gesetz.this.state) { case 0:
        N2Gesetz.this.x = 0.0D; break;
      case 1:
      case 2:
        N2Gesetz.this.x = (N2Gesetz.this.a / 2.0D * N2Gesetz.this.t * N2Gesetz.this.t); break;
      case 3:
        N2Gesetz.this.x = 1.0D;
      }
      paramGraphics.setFont(N2Gesetz.this.fC);
      super.paint(paramGraphics);
      paramGraphics.setColor(Color.orange);
      paramGraphics.fillRect(0, 60, 250, 20);
      paramGraphics.fillOval(240, 60, 20, 20);
      paramGraphics.setColor(Color.black); paramGraphics.drawLine(0, 60, 0, 80);
      paramGraphics.drawLine(0, 60, 250, 60);
      paramGraphics.drawLine(0, 80, 250, 80);
      paramGraphics.drawArc(240, 60, 20, 20, 270, 180);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillRect(240, 55, 6, 15);
      for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) paramGraphics.setColor(Color.green); else
          paramGraphics.setColor(Color.green);
        paramGraphics.fillRect(40 + i * 20, 85, 20, 10);
        paramGraphics.setColor(Color.black);
        paramGraphics.drawRect(40, 85, 200, 10);
      }
      wagon(paramGraphics); clock(paramGraphics);
      N2Gesetz.this.diagram(paramGraphics, 310, 300);
      writeValues(paramGraphics);
      paramGraphics.drawString(N2Gesetz.this.text(10), N2Gesetz.this.ls + 10, 50);
      paramGraphics.setFont(N2Gesetz.this.fC);
      paramGraphics.fillRect(N2Gesetz.this.ls - 3, 40, 7, 20);
      paramGraphics.drawLine(N2Gesetz.this.ls - 20, 30, N2Gesetz.this.ls + 20, 30);
      if (N2Gesetz.this.ls > 50) {
        paramGraphics.drawLine(N2Gesetz.this.ls - 10, 27, N2Gesetz.this.ls - 20, 30);
        paramGraphics.drawLine(N2Gesetz.this.ls - 10, 33, N2Gesetz.this.ls - 20, 30);
      }
      if (N2Gesetz.this.ls < 240) {
        paramGraphics.drawLine(N2Gesetz.this.ls + 10, 27, N2Gesetz.this.ls + 20, 30);
        paramGraphics.drawLine(N2Gesetz.this.ls + 10, 33, N2Gesetz.this.ls + 20, 30);
      }
      if (N2Gesetz.this.my * N2Gesetz.this.M >= N2Gesetz.this.m) {
        paramGraphics.setColor(Color.red); paramGraphics.setFont(WFApplet.HELV);
        paramGraphics.drawString(N2Gesetz.this.text(13), 80, 120);
      }
    }
  }
}