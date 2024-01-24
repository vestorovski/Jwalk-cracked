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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReservableBook
/*    */   extends LibraryBook
/*    */ {
/* 26 */   private String requester = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void issue(String person) throws Exception {
/* 36 */     if (this.requester != null && !person.equals(this.requester)) {
/* 37 */       throw new Exception("this book is reserved for another");
/*    */     }
/* 39 */     super.issue(person);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reserve(String person) throws Exception {
/* 47 */     if (this.requester != null)
/* 48 */       throw new Exception("Book is already reserved"); 
/* 49 */     this.requester = person;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cancel() {
/* 57 */     this.requester = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRequester() {
/* 62 */     return this.requester;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReserved() {
/* 67 */     return (this.requester != null);
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/test/ReservableBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */