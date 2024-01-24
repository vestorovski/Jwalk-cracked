/*    */ package org.jwalk;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestClassLoader
/*    */   extends GeneratorLoader
/*    */ {
/*    */   private String className;
/*    */   
/*    */   public TestClassLoader(ClassLoader delegate, String name) {
/* 32 */     super(delegate);
/* 33 */     this.className = name;
/*    */   }
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
/*    */   public Class<?> loadClass(String name) throws ClassNotFoundException {
/* 46 */     if (!name.equals(this.className)) {
/* 47 */       return super.loadClass(name);
/*    */     }
/* 49 */     return findClass(name);
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/TestClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */