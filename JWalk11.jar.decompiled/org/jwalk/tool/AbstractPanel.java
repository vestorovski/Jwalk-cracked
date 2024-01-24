/*    */ package org.jwalk.tool;
/*    */ 
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractPanel
/*    */   extends JPanel
/*    */ {
/*    */   protected JWalkTester application;
/*    */   
/*    */   public AbstractPanel(JWalkTester tester) {
/* 31 */     this.application = tester;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/AbstractPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */