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
/*    */ public abstract class Question
/*    */   extends Report
/*    */ {
/*    */   protected Question(Edition edition, JWalker walker) {
/* 32 */     super(edition, walker);
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
/*    */   public String toString() {
/* 45 */     return String.valueOf(getContent()) + getQuestion();
/*    */   }
/*    */   
/*    */   public abstract String getQuestion();
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/Question.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */