/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import org.jwalk.GeneratorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ArrayGenerator
/*     */   extends ValueGenerator
/*     */ {
/*  34 */   private int length = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isArray(Class<?> type) {
/*  52 */     return type.isArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isPrintable(Class<?> type) {
/*  66 */     return !(!isArray(type) && !super.isPrintable(type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object createArray(Class<?> type) throws GeneratorException {
/*  82 */     this.length++;
/*  83 */     Class<?> elemType = type.getComponentType();
/*     */     try {
/*  85 */       Object array = Array.newInstance(elemType, this.length);
/*  86 */       for (int i = 0; i < this.length; i++) {
/*  87 */         if (elemType.isArray())
/*  88 */           this.length--; 
/*  89 */         Object elem = nextValue(elemType);
/*  90 */         Array.set(array, i, elem);
/*     */       } 
/*  92 */       return array;
/*     */     }
/*  94 */     catch (RuntimeException runEx) {
/*     */ 
/*     */ 
/*     */       
/*  98 */       GeneratorException genEx = new GeneratorException(type);
/*  99 */       genEx.initCause(runEx);
/* 100 */       throw genEx;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String oracleValue(Object object) {
/* 117 */     if (object != null && isArray(object.getClass())) {
/* 118 */       StringBuilder buffer = new StringBuilder();
/* 119 */       buffer.append('{');
/* 120 */       for (int i = 0; i < Array.getLength(object); i++) {
/* 121 */         Object elem = Array.get(object, i);
/* 122 */         if (i > 0) buffer.append(", "); 
/* 123 */         buffer.append(oracleValue(elem));
/*     */       } 
/* 125 */       buffer.append('}');
/* 126 */       return buffer.toString();
/*     */     } 
/*     */     
/* 129 */     return super.oracleValue(object);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ArrayGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */