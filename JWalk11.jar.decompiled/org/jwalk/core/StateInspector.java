/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateInspector
/*     */ {
/*  29 */   private int depth = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateInspector() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateInspector(int depth) {
/*  47 */     this.depth = (depth < 0) ? 0 : depth;
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
/*     */   public int inspect(Object testObject) {
/*  60 */     if (testObject == null) {
/*  61 */       return 0;
/*     */     }
/*  63 */     return inspectObject(testObject.getClass(), testObject, this.depth);
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
/*     */   protected int inspectObject(Class<?> testClass, Object testObject, int depth) {
/*  78 */     List<Field> fields = getFields(testClass);
/*  79 */     List<Object> stateVector = new ArrayList();
/*  80 */     for (Field field : fields) {
/*  81 */       Class<?> fieldType = field.getType();
/*  82 */       Object fieldValue = null;
/*     */       try {
/*  84 */         fieldValue = field.get(testObject);
/*     */       }
/*  86 */       catch (IllegalAccessException illegalAccessException) {
/*     */ 
/*     */       
/*  89 */       } catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */       
/*  92 */       } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */       
/*  95 */       if (fieldValue == null) {
/*     */         
/*  97 */         stateVector.add(Integer.valueOf(0)); continue;
/*  98 */       }  if (fieldType.isPrimitive()) {
/*     */         
/* 100 */         stateVector.add(Integer.valueOf(fieldValue.hashCode())); continue;
/* 101 */       }  if (fieldType.isArray()) {
/*     */         
/* 103 */         stateVector.add(Integer.valueOf(inspectArray(fieldType, fieldValue, depth))); continue;
/* 104 */       }  if (depth > 0) {
/* 105 */         if (fieldType.isInterface() || 
/* 106 */           Modifier.isAbstract(fieldType.getModifiers())) {
/* 107 */           fieldType = fieldValue.getClass();
/*     */         }
/* 109 */         stateVector.add(Integer.valueOf(inspectObject(fieldType, fieldValue, depth - 1)));
/*     */         
/*     */         continue;
/*     */       } 
/* 113 */       stateVector.add(Integer.valueOf(System.identityHashCode(fieldValue)));
/*     */     } 
/* 115 */     return stateVector.hashCode();
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
/*     */   protected int inspectArray(Class<?> type, Object array, int depth) {
/* 133 */     List<Object> stateVector = new ArrayList();
/* 134 */     for (int index = 0; index < Array.getLength(array); index++) {
/* 135 */       Class<?> elemType = type.getComponentType();
/* 136 */       Object elemValue = null;
/*     */       try {
/* 138 */         elemValue = Array.get(array, index);
/*     */       }
/* 140 */       catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */       
/* 143 */       } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
/*     */ 
/*     */       
/* 146 */       } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */       
/* 149 */       if (elemValue == null) {
/*     */         
/* 151 */         stateVector.add(Integer.valueOf(0));
/* 152 */       } else if (elemType.isPrimitive()) {
/*     */         
/* 154 */         stateVector.add(Integer.valueOf(elemValue.hashCode()));
/* 155 */       } else if (elemType.isArray()) {
/*     */         
/* 157 */         stateVector.add(Integer.valueOf(inspectArray(elemType, elemValue, depth)));
/* 158 */       } else if (depth > 0) {
/* 159 */         if (elemType.isInterface() || 
/* 160 */           Modifier.isAbstract(elemType.getModifiers())) {
/* 161 */           elemType = elemValue.getClass();
/*     */         }
/* 163 */         stateVector.add(Integer.valueOf(inspectObject(elemType, elemValue, depth - 1)));
/*     */       }
/*     */       else {
/*     */         
/* 167 */         stateVector.add(Integer.valueOf(System.identityHashCode(elemValue)));
/*     */       } 
/* 169 */     }  return stateVector.hashCode();
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
/*     */   protected List<Field> getFields(Class<?> testClass) {
/* 186 */     List<Field> fields = new ArrayList<>();
/* 187 */     while (testClass != null) {
/* 188 */       Field[] array = testClass.getDeclaredFields(); byte b; int i; Field[] arrayOfField1;
/* 189 */       for (i = (arrayOfField1 = array).length, b = 0; b < i; ) { Field field = arrayOfField1[b];
/* 190 */         field.setAccessible(true);
/* 191 */         fields.add(field); b++; }
/*     */       
/* 193 */       testClass = testClass.getSuperclass();
/*     */     } 
/* 195 */     return fields;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/StateInspector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */