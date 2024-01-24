/*    */ package org.jwalk;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
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
/*    */ public class GeneratorLoader
/*    */   extends URLClassLoader
/*    */ {
/*    */   public GeneratorLoader(ClassLoader delegate) {
/* 29 */     super(new URL[0], delegate);
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
/*    */   public boolean addDirectory(File directory) {
/* 42 */     URL[] urls = getURLs();
/*    */     try {
/* 44 */       URL url = directory.toURI().toURL();
/* 45 */       for (int i = 0; i < urls.length; i++) {
/* 46 */         if (urls[i].equals(url))
/* 47 */           return false; 
/* 48 */       }  addURL(url);
/* 49 */       return true;
/*    */     }
/* 51 */     catch (MalformedURLException ex) {
/* 52 */       System.out.println(ex);
/* 53 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/GeneratorLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */