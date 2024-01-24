/*    */ package org.jwalk.out;
/*    */ 
/*    */ import org.jwalk.JWalker;
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
/*    */ public abstract class Report
/*    */ {
/*    */   protected Edition edition;
/*    */   protected JWalker walker;
/*    */   
/*    */   public Report(Edition edition, JWalker walker) {
/* 44 */     this.edition = edition;
/* 45 */     this.walker = walker;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JWalker getJWalker() {
/* 54 */     return this.walker;
/*    */   }
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
/*    */   public Edition getEdition() {
/* 67 */     return this.edition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return getContent();
/*    */   }
/*    */   
/*    */   public abstract String getContent();
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/Report.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */