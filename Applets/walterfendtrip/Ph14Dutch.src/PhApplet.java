/*    */ public class PhApplet extends WFApplet
/*    */ {
/*    */   void setIndexOfApplet()
/*    */   {
/* 12 */     String str = getClass().getName();
/* 13 */     String[][] arrayOfString = TextPh14.allTexts;
/* 14 */     for (this.iApplet = 0; (this.iApplet < arrayOfString.length) && 
/* 15 */       (!arrayOfString[this.iApplet][0].equals(str)); this.iApplet += 1);
/*    */   }
/*    */ 
/*    */   String text(int paramInt)
/*    */   {
/* 22 */     return TextPh14.allTexts[this.iApplet][paramInt];
/*    */   }
/*    */ }

/* Location:           C:\Users\PJay\Documents\PWS\walterfendtrip\ph14_jar\Ph14Dutch.jar
 * Qualified Name:     PhApplet
 * JD-Core Version:    0.6.0
 */