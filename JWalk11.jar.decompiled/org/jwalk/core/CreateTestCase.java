/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Member;
/*     */ import org.jwalk.ExecutionException;
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
/*     */ public class CreateTestCase
/*     */   extends ParamTestCase
/*     */ {
/*     */   private Constructor<?> operation;
/*     */   
/*     */   public CreateTestCase(Constructor<?> constructor) {
/*  34 */     super(constructor);
/*  35 */     this.operation = constructor;
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
/*     */   public Constructor<?> getOperation() {
/*  48 */     return this.operation;
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
/*     */   public Class<?> getReturnType() {
/*  60 */     return this.operation.getDeclaringClass();
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
/*  85 */     Object result = super.execute(generator, target);
/*     */     try {
/*  87 */       result = this.operation.newInstance(this.paramValues);
/*  88 */       generator.logObject(result);
/*  89 */       this.state = inspector.inspect(result);
/*     */     }
/*  91 */     catch (InstantiationException isAbstract) {
/*  92 */       ExecutionException ex = new ExecutionException(this.operation, 
/*  93 */           "Cannot construct an abstract class: ");
/*  94 */       ex.initCause(isAbstract);
/*  95 */       throw ex;
/*     */     }
/*  97 */     catch (IllegalAccessException isNotPublic) {
/*  98 */       ExecutionException ex = new ExecutionException(this.operation, 
/*  99 */           "Cannot invoke non-public constructor: ");
/* 100 */       ex.initCause(isNotPublic);
/* 101 */       throw ex;
/*     */     }
/* 103 */     catch (IllegalArgumentException badArguments) {
/* 104 */       ExecutionException ex = new ExecutionException(this.operation, 
/* 105 */           "Cannot match arguments to expected types: ");
/* 106 */       ex.initCause(badArguments);
/* 107 */       throw ex;
/*     */     }
/* 109 */     catch (ExceptionInInitializerError badInit) {
/* 110 */       ExecutionException ex = new ExecutionException(this.operation, 
/* 111 */           "Failed to initialise static variables: ");
/* 112 */       ex.initCause(badInit);
/* 113 */       throw ex;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return result;
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
/* 132 */     StringBuilder buffer = new StringBuilder("new");
/* 133 */     buffer.append(super.getKey(generator));
/* 134 */     return buffer.toString();
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
/*     */   public String toString(ObjectGenerator generator) {
/* 153 */     String typeName = getReturnType().getSimpleName();
/* 154 */     StringBuilder buffer = new StringBuilder(typeName);
/* 155 */     buffer.append(" target = new ").append(typeName);
/* 156 */     buffer.append(super.toString(generator));
/* 157 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/CreateTestCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */