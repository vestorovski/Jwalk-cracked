/*    */ package org.jwalk.test;
/*    */ 
/*    */ import org.jwalk.out.Answer;
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
/*    */ 
/*    */ public class AnswerCycle
/*    */ {
/* 41 */   private Answer answer = Answer.QUIT;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnswerCycle() {
/* 47 */     this.answer = Answer.QUIT;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnswer(Answer value) {
/* 55 */     this.answer = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Answer getAnswer() {
/* 63 */     return this.answer;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/test/AnswerCycle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */