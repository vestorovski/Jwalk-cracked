/*    */ package org.jwalk.gen;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
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
/*    */ public class PlatformGenerator
/*    */   implements CustomGenerator
/*    */ {
/* 28 */   private static Locale[] standardLocales = Locale.getAvailableLocales();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   private static Charset[] standardCharsets = (Charset[])Charset.availableCharsets().values().toArray((Object[])new Charset[0]);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   private int localeSeed = 0;
/* 42 */   private int charsetSeed = 0;
/* 43 */   private long dateSeed = 0L;
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
/* 57 */     return !(type != Locale.class && type != Charset.class && 
/* 58 */       type != Date.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object nextValue(Class<?> type) {
/*    */     Object result;
/* 70 */     if (type == Locale.class) {
/* 71 */       result = (this.localeSeed == 0) ? Locale.getDefault() : 
/* 72 */         standardLocales[this.localeSeed];
/* 73 */       this.localeSeed = (this.localeSeed + 17) % standardLocales.length;
/*    */     }
/* 75 */     else if (type == Charset.class) {
/* 76 */       result = (this.charsetSeed == 0) ? Charset.defaultCharset() : 
/* 77 */         standardCharsets[this.charsetSeed];
/* 78 */       this.charsetSeed = (this.charsetSeed + 17) % standardCharsets.length;
/*    */     } else {
/*    */       
/* 81 */       result = new Date(this.dateSeed);
/* 82 */       this.dateSeed = (this.dateSeed + 250000000000L) % Long.MAX_VALUE;
/*    */     } 
/* 84 */     return result;
/*    */   }
/*    */   
/*    */   public void setOwner(MasterGenerator generator) {}
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/PlatformGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */