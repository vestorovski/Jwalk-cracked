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
/*    */ public class LoaderException
/*    */   extends JWalkException
/*    */ {
/*    */   private String name;
/*    */   private boolean qualify;
/*    */   
/*    */   public LoaderException(String name) {
/* 37 */     super("Could not find any class named: " + name, Error.LOADER_ERROR);
/* 38 */     this.name = name;
/* 39 */     this.qualify = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoaderException(String name, boolean qualify) {
/* 50 */     super("Incorrect package qualification for: " + name, Error.LOADER_ERROR);
/* 51 */     this.name = name;
/* 52 */     this.qualify = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 60 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean classNotFound() {
/* 68 */     return !this.qualify;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean classNotQualified() {
/* 76 */     return this.qualify;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/LoaderException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */