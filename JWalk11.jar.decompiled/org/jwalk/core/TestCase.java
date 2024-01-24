/*    */ package org.jwalk.core;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Member;
/*    */ import org.jwalk.ExecutionException;
/*    */ import org.jwalk.GeneratorException;
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
/*    */ 
/*    */ public abstract class TestCase
/*    */   implements Cloneable
/*    */ {
/* 30 */   protected static StateInspector inspector = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setInspector(StateInspector inspector) {
/* 40 */     TestCase.inspector = inspector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   protected int state = 0;
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
/*    */   public TestCase clone() {
/* 61 */     TestCase result = null;
/*    */     try {
/* 63 */       result = (TestCase)super.clone();
/* 64 */       result.state = 0;
/*    */     }
/* 66 */     catch (CloneNotSupportedException ex) {
/* 67 */       ex.printStackTrace();
/*    */     } 
/* 69 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getState() {
/* 78 */     return this.state;
/*    */   }
/*    */   
/*    */   public abstract Member getOperation();
/*    */   
/*    */   public abstract Class<?> getReturnType();
/*    */   
/*    */   public abstract Object execute(ObjectGenerator paramObjectGenerator, Object paramObject) throws InvocationTargetException, GeneratorException, ExecutionException;
/*    */   
/*    */   public abstract String toString(ObjectGenerator paramObjectGenerator);
/*    */   
/*    */   public abstract String getKey(ObjectGenerator paramObjectGenerator);
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/TestCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */