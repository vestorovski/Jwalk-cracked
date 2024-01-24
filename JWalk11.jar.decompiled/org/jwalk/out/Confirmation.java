/*    */ package org.jwalk.out;
/*    */ 
/*    */ import org.jwalk.JWalker;
/*    */ import org.jwalk.core.TestSequence;
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
/*    */ public class Confirmation
/*    */   extends Question
/*    */ {
/*    */   protected TestSequence sequence;
/*    */   
/*    */   public Confirmation(JWalker walker, TestSequence sequence) {
/* 31 */     super(Edition.CONFIRM_DIALOG, walker);
/* 32 */     this.sequence = sequence;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getContent() {
/* 41 */     return this.sequence.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getQuestion() {
/* 50 */     return "Confirm? (y|n|q): ";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TestSequence getTestResult() {
/* 59 */     return this.sequence;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/Confirmation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */