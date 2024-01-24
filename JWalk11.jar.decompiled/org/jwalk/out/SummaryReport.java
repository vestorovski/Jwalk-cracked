/*     */ package org.jwalk.out;
/*     */ 
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.Strategy;
/*     */ import org.jwalk.core.ClassInspector;
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
/*     */ public class SummaryReport
/*     */   extends StatisticalReport
/*     */ {
/*     */   public SummaryReport(ClassInspector inspector) {
/*  39 */     super(Edition.SUMMARY_REPORT, inspector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tallyResults(CycleReport report) {
/*  49 */     this.succeeded += report.countSucceeded();
/*  50 */     this.terminated += report.countTerminated();
/*  51 */     this.confirmed += report.countConfirmed();
/*  52 */     this.rejected += report.countRejected();
/*  53 */     this.passed += report.countPassed();
/*  54 */     this.failed += report.countFailed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContent() {
/*  65 */     StringBuilder text = new StringBuilder();
/*  66 */     text.append(banner()).append('\n');
/*  67 */     text.append(explorationSummary()).append('\n');
/*  68 */     if (countPassed() + countFailed() > 0)
/*  69 */       text.append(validationSummary()).append('\n'); 
/*  70 */     if (this.walker.outOfMemory()) {
/*  71 */       text.append("Ran out of memory: test statistics are incomplete.\n");
/*  72 */     } else if (this.walker.userAborted()) {
/*  73 */       text.append("Test series was aborted: statistics are incomplete.\n");
/*  74 */     }  text.append('\n');
/*  75 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String banner() {
/*  86 */     ClassInspector inspector = (ClassInspector)this.walker;
/*  87 */     Settings settings = inspector.getSettings();
/*  88 */     Strategy strategy = settings.getStrategy();
/*  89 */     Modality mode = settings.getModality();
/*  90 */     String name = toString(getTestClass());
/*  91 */     StringBuilder text = new StringBuilder();
/*  92 */     text.append("Test summary for the ");
/*  93 */     if (inspector.classIsEnum()) {
/*  94 */       text.append("enumerated type: ").append(name);
/*     */     } else {
/*  96 */       text.append("class: ").append(name);
/*  97 */     }  text.append("\n\n").append("\tTest class: ").append(name);
/*  98 */     text.append("\n\tTest strategy: ");
/*  99 */     if (strategy == Strategy.STATES) {
/* 100 */       text.append("State space ");
/* 101 */     } else if (strategy == Strategy.ALGEBRA) {
/* 102 */       text.append("Algebraic ");
/*     */     } else {
/* 104 */       text.append("Protocol ");
/* 105 */     }  if (mode == Modality.VALIDATE) {
/* 106 */       text.append("validation\n");
/*     */     } else {
/* 108 */       text.append("exploration\n");
/* 109 */     }  text.append("\tTest depth: ").append(settings.getTestDepth());
/* 110 */     text.append("\n");
/* 111 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String explorationSummary() {
/* 121 */     StringBuilder text = new StringBuilder();
/* 122 */     text.append("Exploration summary:\n\n");
/* 123 */     text.append("\tExecuted " + countExecuted() + " test sequences in total\n");
/* 124 */     text.append("\tDiscarded " + countPruned() + " redundant test sequences\n");
/* 125 */     text.append("\tExercised " + countSucceeded() + " successful test sequences\n");
/* 126 */     text.append("\tTerminated " + countTerminated() + " exceptional test sequences\n");
/* 127 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String validationSummary() {
/* 137 */     StringBuilder text = new StringBuilder();
/* 138 */     text.append("Validation summary:\n\n");
/* 139 */     text.append("\tPassed " + countPassed() + " test sequences in total\n");
/* 140 */     text.append("\tFailed " + countFailed() + " test sequences in total\n");
/* 141 */     text.append("\tUser confirmed " + countConfirmed() + " test outcomes manually\n");
/* 142 */     text.append("\tUser rejected " + countRejected() + " test outcomes manually\n");
/* 143 */     text.append("\tOracle passed " + countCorrect() + " correct test outcomes\n");
/* 144 */     text.append("\tOracle failed " + countIncorrect() + " incorrect test outcomes\n");
/* 145 */     return text.toString();
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
/*     */   public long countPruned() {
/* 158 */     return ((ClassInspector)this.walker).countPermutations() - countExecuted();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/SummaryReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */