/*    */ package org.jwalk.gen;
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
/*    */ public class StringGenerator
/*    */   implements CustomGenerator
/*    */ {
/* 17 */   private static String[] standardStrings = new String[] { 
/* 18 */       "aubergine", "barking", "crescent", "debatable", 
/* 19 */       "elementary", "forceful", "gumboots", "hawker", 
/* 20 */       "indignant", "jaundice", "kelp", "lambast", 
/* 21 */       "mornington", "nonesense", "opulent", "predator", 
/* 22 */       "queen", "register", "solemn", "tenuous", "undulate", 
/* 23 */       "version", "withering", "xylophone", "yellow", "zebra" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   private int stringSeed = 0;
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
/*    */   public boolean canCreate(Class<?> type) {
/* 44 */     return (type == String.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object nextValue(Class<?> type) {
/* 54 */     String result = standardStrings[this.stringSeed];
/* 55 */     this.stringSeed = (this.stringSeed + 1) % standardStrings.length;
/* 56 */     return result;
/*    */   }
/*    */   
/*    */   public void setOwner(MasterGenerator generator) {}
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/StringGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */