/*    */ package org.jwalk.test;
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
/*    */ public class LibraryBook
/*    */ {
/* 22 */   private String borrower = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void issue(String person) throws Exception {
/* 31 */     if (this.borrower != null)
/* 32 */       throw new Exception("this book is already on loan"); 
/* 33 */     this.borrower = person;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void discharge() {
/* 41 */     this.borrower = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBorrower() {
/* 46 */     return this.borrower;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnLoan() {
/* 51 */     return (this.borrower != null);
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/test/LibraryBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */