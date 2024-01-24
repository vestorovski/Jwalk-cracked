/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.gen.CustomGenerator;
/*     */ import org.jwalk.gen.MasterGenerator;
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
/*     */ public abstract class ValueGenerator
/*     */   implements MasterGenerator
/*     */ {
/*  41 */   private static Set<Class<?>> wrapped = getWrappedTypes();
/*     */ 
/*     */   
/*     */   protected List<CustomGenerator> delegates;
/*     */ 
/*     */ 
/*     */   
/*     */   private static HashSet getWrappedTypes() {
/*  49 */     Class[] wrapped = { Byte.class, Boolean.class, 
/*  50 */         Character.class, Integer.class, Short.class, 
/*  51 */         Long.class, Float.class, Double.class };
/*  52 */     return new HashSet<>(Arrays.asList(wrapped));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean boolSeed = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private byte byteSeed = 1;
/*  68 */   private char charSeed = 'a';
/*  69 */   private short shortSeed = 1;
/*  70 */   private int intSeed = 1;
/*  71 */   private long longSeed = 1L;
/*  72 */   private float floatSeed = 1.0F;
/*  73 */   private double doubleSeed = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ValueGenerator() {
/*  83 */     this.delegates = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isPrimitive(Class<?> type) {
/*  91 */     return type.isPrimitive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isWrapped(Class<?> type) {
/*  99 */     return wrapped.contains(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEnumerated(Class<?> type) {
/* 107 */     return type.isEnum();
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
/*     */   protected boolean isPrintable(Class<?> type) {
/* 119 */     return !(!isPrimitive(type) && !isWrapped(type) && !isEnumerated(type) && 
/* 120 */       type != String.class);
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
/*     */   protected Object createPrimitive(Class<?> type) throws GeneratorException {
/* 136 */     if (type == boolean.class)
/* 137 */       return Boolean.valueOf(!(this.boolSeed = !this.boolSeed)); 
/* 138 */     if (type == byte.class) {
/* 139 */       this.byteSeed = (byte)(this.byteSeed + 1); return new Byte(this.byteSeed);
/* 140 */     }  if (type == char.class) {
/* 141 */       this.charSeed = (char)(this.charSeed + 1); return new Character(this.charSeed);
/* 142 */     }  if (type == short.class) {
/* 143 */       this.shortSeed = (short)(this.shortSeed + 1); return new Short(this.shortSeed);
/* 144 */     }  if (type == int.class)
/* 145 */       return new Integer(this.intSeed++); 
/* 146 */     if (type == long.class)
/* 147 */       return new Long(this.longSeed++); 
/* 148 */     if (type == float.class)
/* 149 */       return new Float(this.floatSeed++); 
/* 150 */     if (type == double.class) {
/* 151 */       return new Double(this.doubleSeed++);
/*     */     }
/* 153 */     throw new GeneratorException(type);
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
/*     */   protected Object createWrapped(Class<?> type) throws GeneratorException {
/* 169 */     if (type == Boolean.class)
/* 170 */       return Boolean.valueOf(!(this.boolSeed = !this.boolSeed)); 
/* 171 */     if (type == Byte.class) {
/* 172 */       this.byteSeed = (byte)(this.byteSeed + 1); return new Byte(this.byteSeed);
/* 173 */     }  if (type == Character.class) {
/* 174 */       this.charSeed = (char)(this.charSeed + 1); return new Character(this.charSeed);
/* 175 */     }  if (type == Short.class) {
/* 176 */       this.shortSeed = (short)(this.shortSeed + 1); return new Short(this.shortSeed);
/* 177 */     }  if (type == Integer.class)
/* 178 */       return new Integer(this.intSeed++); 
/* 179 */     if (type == Long.class)
/* 180 */       return new Long(this.longSeed++); 
/* 181 */     if (type == Float.class)
/* 182 */       return new Float(this.floatSeed++); 
/* 183 */     if (type == Double.class) {
/* 184 */       return new Double(this.doubleSeed++);
/*     */     }
/* 186 */     throw new GeneratorException(type);
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
/*     */   public String oracleValue(Object object) {
/* 201 */     if (object instanceof Character)
/* 202 */       return "'" + object.toString() + "'"; 
/* 203 */     if (object instanceof String) {
/* 204 */       return "\"" + object.toString() + "\"";
/*     */     }
/* 206 */     return String.valueOf(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDelegate(CustomGenerator generator) {
/* 217 */     this.delegates.add(0, generator);
/* 218 */     generator.setOwner(this);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ValueGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */