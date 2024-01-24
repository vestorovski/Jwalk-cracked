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
/*    */ public class PermissionException
/*    */   extends JWalkException
/*    */ {
/*    */   private Class<?> type;
/*    */   private boolean generator;
/*    */   
/*    */   public PermissionException(Class<?> type) {
/* 40 */     super("Permission was refused to execute: " + type.getSimpleName(), Error.PERMISSION_ERROR);
/* 41 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PermissionException(Class<?> type, String message) {
/* 51 */     super(String.valueOf(message) + type.getSimpleName(), Error.PERMISSION_ERROR);
/* 52 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PermissionException(Class<?> type, boolean generator) {
/* 63 */     super("Permission was refused to execute: " + type.getSimpleName(), Error.PERMISSION_ERROR);
/* 64 */     this.type = type;
/* 65 */     this.generator = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<?> getType() {
/* 73 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean testClassRefused() {
/* 80 */     return !this.generator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generatorRefused() {
/* 87 */     return this.generator;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/PermissionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */