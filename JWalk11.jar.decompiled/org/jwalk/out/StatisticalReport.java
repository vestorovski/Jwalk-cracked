/*     */ package org.jwalk.out;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jwalk.core.ClassInspector;
/*     */ import org.jwalk.core.TestSequence;
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
/*     */ public abstract class StatisticalReport
/*     */   extends ProtocolReport
/*     */ {
/*  32 */   protected int succeeded = 0;
/*  33 */   protected int terminated = 0;
/*  34 */   protected int confirmed = 0;
/*  35 */   protected int rejected = 0;
/*  36 */   protected int passed = 0;
/*  37 */   protected int failed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StatisticalReport(Edition edition, ClassInspector inspector) {
/*  45 */     super(edition, inspector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tallyResults(List<TestSequence> results) {
/*  52 */     for (TestSequence sequence : results) {
/*  53 */       if (sequence.hasSucceeded())
/*  54 */         this.succeeded++; 
/*  55 */       if (sequence.hasTerminated())
/*  56 */         this.terminated++; 
/*  57 */       if (sequence.isConfirmed())
/*  58 */         this.confirmed++; 
/*  59 */       if (sequence.isRejected())
/*  60 */         this.rejected++; 
/*  61 */       if (sequence.hasPassed())
/*  62 */         this.passed++; 
/*  63 */       if (sequence.hasFailed()) {
/*  64 */         this.failed++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countExecuted() {
/*  75 */     return this.succeeded + this.terminated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countSucceeded() {
/*  84 */     return this.succeeded;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countTerminated() {
/*  93 */     return this.terminated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countConfirmed() {
/* 103 */     return this.confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countRejected() {
/* 113 */     return this.rejected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countCorrect() {
/* 123 */     return this.passed - this.confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countIncorrect() {
/* 133 */     return this.failed - this.rejected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countPassed() {
/* 143 */     return this.passed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countFailed() {
/* 153 */     return this.failed;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/StatisticalReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */