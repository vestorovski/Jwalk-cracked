/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jwalk.Generator;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.JWalker;
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
/*     */ public class ObjectGenerator
/*     */   extends ArrayGenerator
/*     */ {
/*     */   protected JWalker walker;
/*     */   protected Class<?> targetType;
/*     */   protected Object target;
/*     */   boolean selfReturned = false;
/*     */   protected Map<Object, String> oracleMap;
/*     */   protected Map<Class<?>, Integer> indexMap;
/*     */   
/*     */   public ObjectGenerator(JWalker walker) {
/*  87 */     this.walker = walker;
/*  88 */     this.targetType = walker.getTestClass();
/*  89 */     this.oracleMap = new HashMap<>();
/*  90 */     this.indexMap = new HashMap<>();
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
/*     */   public boolean canCreate(Class<?> type) {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isMetaClass(Class<?> type) {
/* 113 */     return (type == Class.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object createClass(Class<?> type) {
/* 124 */     return this.targetType;
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
/*     */   protected Object createFromNextConstructor(Class<?> type) throws GeneratorException {
/*     */     try {
/*     */       byte b;
/*     */       int i;
/*     */       Constructor[] arrayOfConstructor;
/* 142 */       for (i = (arrayOfConstructor = (Constructor[])type.getConstructors()).length, b = 0; b < i; ) { Constructor<?> constructor = arrayOfConstructor[b];
/* 143 */         Class[] paramTypes = constructor.getParameterTypes();
/* 144 */         Object[] paramValues = new Object[paramTypes.length];
/*     */         try {
/* 146 */           for (int j = 0; j < paramTypes.length; j++)
/* 147 */             paramValues[j] = nextValue(paramTypes[j]); 
/* 148 */           Object exemplar = constructor.newInstance(paramValues);
/* 149 */           logObject(exemplar);
/* 150 */           return exemplar;
/*     */         }
/* 152 */         catch (Exception exception) {}
/*     */         
/*     */         b++; }
/*     */       
/* 156 */       throw new GeneratorException(type);
/*     */     }
/* 158 */     catch (SecurityException securityBreach) {
/* 159 */       GeneratorException ex = new GeneratorException(type);
/* 160 */       ex.initCause(securityBreach);
/* 161 */       throw ex;
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
/*     */   protected Object createObject(Class<?> type) throws GeneratorException {
/*     */     try {
/* 177 */       Object exemplar = type.newInstance();
/* 178 */       logObject(exemplar);
/* 179 */       return exemplar;
/*     */     }
/* 181 */     catch (InstantiationException ex) {
/* 182 */       return createFromNextConstructor(type);
/*     */     }
/* 184 */     catch (IllegalAccessException ex) {
/* 185 */       return createFromNextConstructor(type);
/*     */     }
/* 187 */     catch (Exception ex) {
/* 188 */       GeneratorException genEx = new GeneratorException(type);
/* 189 */       genEx.initCause(ex);
/* 190 */       throw genEx;
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
/* 218 */     if (type == this.targetType && this.target != null && !this.selfReturned) {
/* 219 */       this.selfReturned = true;
/* 220 */       return this.target;
/*     */     } 
/*     */     
/* 223 */     for (Generator generator : this.delegates) {
/* 224 */       if (generator.canCreate(type)) {
/*     */         try {
/* 226 */           Object exemplar = generator.nextValue(type);
/* 227 */           logObject(exemplar);
/* 228 */           return exemplar;
/*     */         }
/* 230 */         catch (GeneratorException ex) {
/* 231 */           throw ex;
/*     */         }
/* 233 */         catch (Exception badGenerator) {
/* 234 */           GeneratorException ex = 
/* 235 */             new GeneratorException(type, generator);
/* 236 */           ex.initCause(badGenerator);
/* 237 */           throw ex;
/*     */         } 
/*     */       }
/*     */     } 
/* 241 */     if (isPrimitive(type))
/* 242 */       return createPrimitive(type); 
/* 243 */     if (isWrapped(type))
/* 244 */       return createWrapped(type); 
/* 245 */     if (isArray(type))
/* 246 */       return createArray(type); 
/* 247 */     if (isMetaClass(type)) {
/* 248 */       return createClass(type);
/*     */     }
/* 250 */     return createObject(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logTarget(Object object) {
/* 260 */     this.target = object;
/* 261 */     logObject(object.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getTarget() {
/* 269 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getTargetType() {
/* 277 */     return this.targetType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JWalker getJWalker() {
/* 286 */     return this.walker;
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
/*     */   public void logObject(Object object) {
/* 300 */     if (object != null && this.oracleMap.get(object) == null) {
/* 301 */       Class<?> type = object.getClass();
/* 302 */       if (!isPrintable(type)) {
/*     */         
/* 304 */         Integer count = this.indexMap.get(type);
/* 305 */         if (count == null)
/* 306 */           count = Integer.valueOf(0); 
/* 307 */         this.indexMap.put(type, count = Integer.valueOf(count.intValue() + 1));
/*     */         
/* 309 */         String oracleValue = type.getSimpleName();
/* 310 */         if (isMetaClass(type)) {
/* 311 */           String param = "<" + ((Class)object).getSimpleName() + ">";
/* 312 */           oracleValue = String.valueOf(oracleValue) + param;
/*     */         } else {
/*     */           
/* 315 */           oracleValue = String.valueOf(oracleValue) + "#" + count;
/* 316 */         }  this.oracleMap.put(object, oracleValue);
/*     */       } 
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
/*     */   public String oracleValue(Object object) {
/* 332 */     String result = this.oracleMap.get(object);
/* 333 */     if (result != null) {
/* 334 */       return result;
/*     */     }
/* 336 */     return super.oracleValue(object);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ObjectGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */