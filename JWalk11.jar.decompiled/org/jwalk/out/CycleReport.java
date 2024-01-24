/*     */ package org.jwalk.out;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.Strategy;
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
/*     */ public class CycleReport
/*     */   extends StatisticalReport
/*     */ {
/*     */   private List<TestSequence> results;
/*     */   private int cycle;
/*  64 */   private String state = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CycleReport(ClassInspector inspector, List<TestSequence> results, int cycle) {
/*  75 */     super(Edition.CYCLE_REPORT, inspector);
/*  76 */     this.results = results;
/*  77 */     this.cycle = cycle;
/*  78 */     tallyResults(results);
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
/*     */   public CycleReport(ClassInspector inspector, List<TestSequence> results, String state, int cycle) {
/*  91 */     super(Edition.CYCLE_REPORT, inspector);
/*  92 */     this.results = results;
/*  93 */     this.state = state;
/*  94 */     this.cycle = cycle;
/*  95 */     tallyResults(results);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTestCycle() {
/* 104 */     return this.cycle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartingState() {
/* 115 */     return this.state;
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
/*     */   public List<TestSequence> getTestResults() {
/* 127 */     return this.results;
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
/*     */   public String getContent() {
/* 141 */     StringBuilder text = new StringBuilder();
/* 142 */     text.append(banner()).append('\n');
/* 143 */     for (TestSequence sequence : this.results) {
/* 144 */       if (sequence.hasExecuted()) {
/* 145 */         text.append(toString(sequence, true)).append('\n'); continue;
/*     */       } 
/* 147 */       text.append("Warning: this test cycle was aborted;\n");
/* 148 */       text.append(countAbandoned());
/* 149 */       text.append(" test sequences were not executed.\n\n");
/*     */       break;
/*     */     } 
/* 152 */     int pruned = countPruned();
/* 153 */     if (pruned > 0) {
/* 154 */       text.append("Pruned: ").append(pruned);
/* 155 */       text.append(" test sequences in this cycle.\n\n");
/*     */     } 
/* 157 */     text.append('\n');
/* 158 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String banner() {
/* 166 */     Strategy strategy = this.walker.getSettings().getStrategy();
/* 167 */     Modality mode = this.walker.getSettings().getModality();
/* 168 */     StringBuilder text = new StringBuilder();
/* 169 */     text.append(toString(getTestClass())).append(": ");
/* 170 */     if (this.state != null)
/* 171 */       text.append(this.state).append(" state: "); 
/* 172 */     if (mode == Modality.VALIDATE) {
/* 173 */       text.append("Validating all ");
/*     */     } else {
/* 175 */       text.append("Exploring all ");
/* 176 */     }  if (strategy == Strategy.STATES) {
/* 177 */       text.append("transitions ");
/* 178 */     } else if (strategy == Strategy.ALGEBRA) {
/* 179 */       text.append("constructions ");
/*     */     } else {
/* 181 */       text.append("protocols ");
/* 182 */     }  text.append("of length: ").append(this.cycle);
/* 183 */     text.append('\n');
/* 184 */     return text.toString();
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
/*     */   
/*     */   public String toString(TestSequence sequence, boolean outcome) {
/* 203 */     StringBuilder text = new StringBuilder(sequence.toString());
/* 204 */     if (outcome)
/* 205 */       if (sequence.isValidated()) {
/* 206 */         if (sequence.hasPassed()) {
/* 207 */           text.append(" : PASSED");
/*     */         } else {
/* 209 */           text.append(" : FAILED");
/* 210 */         }  if (sequence.isConfirmed()) {
/* 211 */           text.append(" (confirmed)");
/* 212 */         } else if (sequence.isRejected()) {
/* 213 */           text.append(" (rejected)");
/*     */         } 
/*     */       } else {
/* 216 */         if (sequence.hasSucceeded()) {
/* 217 */           text.append(" : NORMAL");
/*     */         } else {
/* 219 */           text.append(" : EXCEPTION");
/* 220 */         }  if (sequence.isUnchanged()) {
/* 221 */           text.append(" (unchanged)");
/* 222 */         } else if (sequence.isReentrant()) {
/* 223 */           text.append(" (reentrant)");
/*     */         } 
/* 225 */       }   text.append('\n');
/* 226 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countAbandoned() {
/* 236 */     return this.results.size() - this.succeeded + this.terminated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countPruned() {
/* 247 */     return ((ClassInspector)this.walker).countActiveEdges(this.cycle) - this.results.size();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/CycleReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */