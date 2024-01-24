/*     */ package org.jwalk.gen;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class EnumGenerator
/*     */   implements CustomGenerator
/*     */ {
/*     */   private MasterGenerator owner;
/*  39 */   protected Map<Class<?>, Integer> indexMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCreate(Class<?> type) {
/*  48 */     return !(!type.isEnum() && type != Enum.class);
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
/*     */   public Object nextValue(Class<?> type) throws GeneratorException {
/*  67 */     if (type == Enum.class) {
/*  68 */       return nextValue(getConcreteSubtype(type));
/*     */     }
/*  70 */     Integer enumSeed = this.indexMap.get(type);
/*  71 */     if (enumSeed == null)
/*  72 */       enumSeed = Integer.valueOf(0); 
/*  73 */     Object[] values = type.getEnumConstants();
/*  74 */     if (values == null)
/*  75 */       throw new GeneratorException(type); 
/*  76 */     Object result = values[enumSeed.intValue()];
/*  77 */     enumSeed = Integer.valueOf((enumSeed.intValue() + 1) % values.length);
/*  78 */     this.indexMap.put(type, enumSeed);
/*  79 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(MasterGenerator generator) {
/*  88 */     this.owner = generator;
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
/*     */   private Class<?> getConcreteSubtype(Class<?> type) throws GeneratorException {
/* 106 */     Class<?> result = this.owner.getTargetType();
/* 107 */     if (!result.isEnum()) {
/* 108 */       Iterator<Class<?>> iterator = this.indexMap.keySet().iterator(); if (iterator.hasNext()) { Class<?> first = iterator.next();
/* 109 */         result = first; }
/*     */ 
/*     */       
/* 112 */       if (!result.isEnum())
/* 113 */         throw new GeneratorException(type); 
/*     */     } 
/* 115 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/EnumGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */