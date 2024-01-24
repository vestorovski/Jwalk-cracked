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
/*    */ public class Wallet
/*    */ {
/* 40 */   private int balance = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void payIn(int amount) {
/* 51 */     if (amount <= 0)
/* 52 */       throw new IllegalArgumentException(
/* 53 */           "amount must be positive: " + amount); 
/* 54 */     this.balance += amount;
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
/*    */   public boolean payOut(int amount) {
/* 66 */     if (amount <= 0)
/* 67 */       throw new IllegalArgumentException(
/* 68 */           "amount must be positive: " + amount); 
/* 69 */     if (amount <= this.balance) {
/* 70 */       this.balance -= amount;
/* 71 */       return true;
/*    */     } 
/*    */     
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int money() {
/* 82 */     return this.balance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 90 */     return (this.balance == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean inCredit() {
/* 98 */     return (this.balance > 0);
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/test/Wallet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */