/*    */ package org.jwalk;
/*    */ 
/*    */ import java.lang.reflect.Member;
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
/*    */ public class ExecutionException
/*    */   extends JWalkException
/*    */ {
/* 28 */   private Member operation = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean random = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExecutionException(Member operation) {
/* 41 */     super("Could not invoke the operation: " + operation, Error.EXECUTION_ERROR);
/* 42 */     this.operation = operation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExecutionException(Member operation, String message) {
/* 52 */     super(String.valueOf(message) + operation, Error.EXECUTION_ERROR);
/* 53 */     this.operation = operation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExecutionException(Member operation, boolean random) {
/* 64 */     super("Unpredictable behaviour by the operation: " + operation, Error.EXECUTION_ERROR);
/* 65 */     this.operation = operation;
/* 66 */     this.random = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Member getOperation() {
/* 74 */     return this.operation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean invocationFailed() {
/* 81 */     return !this.random;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean executionFailed() {
/* 88 */     return this.random;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/ExecutionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */