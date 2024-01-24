/*    */ package org.jwalk.test;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
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
/*    */ public class InputReader
/*    */ {
/* 49 */   private BufferedReader input = new BufferedReader(
/* 50 */       new InputStreamReader(System.in));
/*    */ 
/*    */ 
/*    */   
/*    */   private String data;
/*    */ 
/*    */ 
/*    */   
/*    */   public String getString() throws IOException {
/* 59 */     System.out.println("Enter a string: ");
/* 60 */     this.data = this.input.readLine();
/* 61 */     return this.data;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/test/InputReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */