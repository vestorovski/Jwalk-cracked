/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
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
/*     */ public class InvokeTestCase
/*     */   extends ParamTestCase
/*     */ {
/*     */   private Method operation;
/*     */   
/*     */   public InvokeTestCase(Method method) {
/*  33 */     super(method);
/*  34 */     this.operation = method;
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
/*     */   public Method getOperation() {
/*  47 */     return this.operation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getReturnType() {
/*  58 */     return this.operation.getReturnType();
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
/*  83 */     Object result = super.execute(generator, target);
/*     */     try {
/*  85 */       result = this.operation.invoke(target, this.paramValues);
/*  86 */       generator.logObject(result);
/*  87 */       this.state = inspector.inspect(target);
/*     */     }
/*  89 */     catch (IllegalAccessException isNotPublic) {
/*  90 */       ExecutionException ex = new ExecutionException(this.operation, 
/*  91 */           "Cannot invoke non-public operation: ");
/*  92 */       ex.initCause(isNotPublic);
/*  93 */       throw ex;
/*     */     }
/*  95 */     catch (IllegalArgumentException badArguments) {
/*  96 */       ExecutionException ex = new ExecutionException(this.operation, 
/*  97 */           "Cannot match arguments to expected types: ");
/*  98 */       ex.initCause(badArguments);
/*  99 */       throw ex;
/*     */     }
/* 101 */     catch (NullPointerException nullTarget) {
/* 102 */       ExecutionException ex = new ExecutionException(this.operation, 
/* 103 */           "Cannot invoke a method on a null target: ");
/* 104 */       ex.initCause(nullTarget);
/* 105 */       throw ex;
/*     */     }
/* 107 */     catch (ExceptionInInitializerError badInit) {
/* 108 */       ExecutionException ex = new ExecutionException(this.operation, 
/* 109 */           "Failed to initialise static variables: ");
/* 110 */       ex.initCause(badInit);
/* 111 */       throw ex;
/*     */     }
/* 113 */     catch (InvocationTargetException callFailed) {
/*     */ 
/*     */ 
/*     */       
/* 117 */       this.state = inspector.inspect(target);
/* 118 */       throw callFailed;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (getReturnType() == void.class) {
/* 124 */       return void.class;
/*     */     }
/* 126 */     return result;
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
/*     */   public String getKey(ObjectGenerator generator) {
/* 141 */     StringBuilder buffer = new StringBuilder(".");
/* 142 */     buffer.append(this.operation.getName()).append(super.getKey(generator));
/* 143 */     return buffer.toString();
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
/*     */   public String toString(ObjectGenerator generator) {
/* 161 */     StringBuilder buffer = new StringBuilder("\ttarget.");
/* 162 */     buffer.append(this.operation.getName()).append(super.toString(generator));
/* 163 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/InvokeTestCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */