/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.Generator;
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
/*     */ public abstract class ParamTestCase
/*     */   extends TestCase
/*     */ {
/*     */   protected boolean isBinary = false;
/*  30 */   protected Class<?>[] paramTypes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   protected Object[] paramValues = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParamTestCase(Constructor<?> constructor) {
/*  44 */     this.paramTypes = constructor.getParameterTypes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParamTestCase(Method method) {
/*  54 */     this.paramTypes = method.getParameterTypes();
/*  55 */     String name = method.getName();
/*  56 */     if (name.equals("equals") || name.equals("compareTo"))
/*     */     {
/*     */       
/*  59 */       this.isBinary = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParamTestCase clone() {
/*  69 */     ParamTestCase result = (ParamTestCase)super.clone();
/*  70 */     result.paramValues = null;
/*  71 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object execute(ObjectGenerator generator, Object target) throws InvocationTargetException, GeneratorException, ExecutionException {
/*  96 */     this.paramValues = new Object[this.paramTypes.length];
/*  97 */     for (int i = 0; i < this.paramValues.length; i++) {
/*     */       try {
/*  99 */         if (this.isBinary) {
/* 100 */           this.paramValues[i] = generator.nextValue(
/* 101 */               generator.getTargetType());
/*     */         } else {
/* 103 */           this.paramValues[i] = generator.nextValue(this.paramTypes[i]);
/*     */         } 
/* 105 */       } catch (GeneratorException ex) {
/* 106 */         throw ex;
/*     */       }
/* 108 */       catch (Exception badGenerator) {
/* 109 */         GeneratorException ex = 
/* 110 */           new GeneratorException(this.paramTypes[i], (Generator)generator);
/* 111 */         ex.initCause(badGenerator);
/* 112 */         throw ex;
/*     */       } 
/*     */     } 
/* 115 */     return null;
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
/*     */   public String getKey(ObjectGenerator generator) {
/* 129 */     StringBuilder buffer = new StringBuilder("(");
/* 130 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 131 */       if (i > 0) buffer.append(','); 
/* 132 */       if (this.paramValues == null) {
/* 133 */         buffer.append("null");
/*     */       } else {
/* 135 */         buffer.append(generator.oracleValue(this.paramValues[i]));
/*     */       } 
/* 137 */     }  buffer.append(')');
/* 138 */     return buffer.toString();
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
/*     */   public String toString(ObjectGenerator generator) {
/* 151 */     StringBuilder buffer = new StringBuilder("(");
/* 152 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 153 */       if (i > 0) buffer.append(", "); 
/* 154 */       buffer.append(this.paramTypes[i].getSimpleName()).append(' ');
/* 155 */       if (this.paramValues == null) {
/* 156 */         buffer.append("null");
/*     */       } else {
/* 158 */         buffer.append(generator.oracleValue(this.paramValues[i]));
/*     */       } 
/* 160 */     }  buffer.append(");");
/* 161 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ParamTestCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */