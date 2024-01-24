/*    */ package org.jwalk;
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
/*    */ public class JWalkException
/*    */   extends Exception
/*    */ {
/*    */   private Error error;
/*    */   
/*    */   public JWalkException(String message, Error error) {
/* 29 */     super(message);
/* 30 */     this.error = error;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Error getError() {
/* 41 */     return this.error;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/JWalkException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */