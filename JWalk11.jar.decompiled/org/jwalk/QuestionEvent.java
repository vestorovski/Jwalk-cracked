/*    */ package org.jwalk;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.jwalk.out.Question;
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
/*    */ public class QuestionEvent
/*    */   extends EventObject
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected Question question;
/*    */   
/*    */   public QuestionEvent(JWalker source, Question question) {
/* 37 */     super(source);
/* 38 */     this.question = question;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JWalker getSource() {
/* 46 */     return (JWalker)super.getSource();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Question getQuestion() {
/* 54 */     return this.question;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     return this.question.toString();
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/QuestionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */