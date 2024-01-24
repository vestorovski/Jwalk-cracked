/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Member;
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
/*     */ public class EnumTestCase
/*     */   extends TestCase
/*     */ {
/*     */   private Class<?> enumType;
/*     */   private Enum<?> enumValue;
/*     */   
/*     */   public EnumTestCase(Enum<?> value) {
/*  38 */     this.enumValue = value;
/*  39 */     this.enumType = value.getClass();
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
/*     */   public Object execute(ObjectGenerator generator, Object target) {
/*  52 */     this.state = inspector.inspect(this.enumValue);
/*  53 */     return this.enumValue;
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
/*  67 */     if (this.enumValue == null) {
/*  68 */       return "null";
/*     */     }
/*  70 */     return this.enumValue.name();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Member getOperation() {
/*  81 */     Field field = null;
/*     */     try {
/*  83 */       field = this.enumType.getField(this.enumValue.name());
/*     */     }
/*  85 */     catch (NoSuchFieldException badName) {
/*  86 */       badName.printStackTrace();
/*     */     }
/*  88 */     catch (NullPointerException nullName) {
/*  89 */       nullName.printStackTrace();
/*     */     }
/*  91 */     catch (SecurityException securityBreach) {
/*  92 */       securityBreach.printStackTrace();
/*     */     } 
/*  94 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getReturnType() {
/* 104 */     return this.enumType;
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
/*     */   public String toString(ObjectGenerator generator) {
/* 119 */     String typeName = this.enumType.getSimpleName();
/* 120 */     StringBuilder buffer = new StringBuilder(typeName);
/* 121 */     buffer.append(" target = ");
/* 122 */     if (this.enumValue == null) {
/* 123 */       buffer.append("null");
/*     */     } else {
/* 125 */       buffer.append(typeName).append('.').append(this.enumValue.name());
/* 126 */     }  buffer.append(';');
/* 127 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/EnumTestCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */