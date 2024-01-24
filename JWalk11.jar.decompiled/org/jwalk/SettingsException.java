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
/*    */ public class SettingsException
/*    */   extends JWalkException
/*    */ {
/*    */   private Object badValue;
/*    */   private boolean badInteger = false;
/*    */   
/*    */   public SettingsException(Object badValue) {
/* 36 */     super("Could not convert: " + badValue + " into an enumerated constant", Error.SETTINGS_ERROR);
/* 37 */     this.badValue = badValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SettingsException(Object badValue, boolean integral) {
/* 46 */     super("Could not convert: " + badValue + " into an integer value", Error.SETTINGS_ERROR);
/* 47 */     this.badValue = badValue;
/* 48 */     this.badInteger = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 55 */     return this.badValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean enumConversionFailed() {
/* 64 */     return !this.badInteger;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean intConversionFailed() {
/* 73 */     return this.badInteger;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/SettingsException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */