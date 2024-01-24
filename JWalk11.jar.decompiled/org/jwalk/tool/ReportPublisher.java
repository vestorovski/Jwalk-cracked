/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import org.jwalk.ReportEvent;
/*     */ import org.jwalk.ReportListener;
/*     */ import org.jwalk.out.AlgebraReport;
/*     */ import org.jwalk.out.CycleReport;
/*     */ import org.jwalk.out.Edition;
/*     */ import org.jwalk.out.ProtocolReport;
/*     */ import org.jwalk.out.Report;
/*     */ import org.jwalk.out.StateReport;
/*     */ import org.jwalk.out.SummaryReport;
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
/*     */ public class ReportPublisher
/*     */   implements ReportListener
/*     */ {
/*     */   private JWalkTester application;
/*     */   
/*     */   public ReportPublisher(JWalkTester tester) {
/*  38 */     this.application = tester;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(ReportEvent event) {
/*  49 */     Report report = event.getReport();
/*  50 */     Edition edition = report.getEdition();
/*  51 */     switch (edition) {
/*     */       case PROTOCOL_REPORT:
/*  53 */         handle((ProtocolReport)report);
/*     */         break;
/*     */       case null:
/*  56 */         handle((AlgebraReport)report);
/*     */         break;
/*     */       case STATE_REPORT:
/*  59 */         handle((StateReport)report);
/*     */         break;
/*     */       case CYCLE_REPORT:
/*  62 */         handle((CycleReport)report);
/*     */         break;
/*     */       case SUMMARY_REPORT:
/*  65 */         handle((SummaryReport)report);
/*     */         break;
/*     */
        default:
            throw new IllegalStateException("Unexpected value: " + edition);
    } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handle(ProtocolReport report) {
/*  76 */     String title = String.valueOf(report.toString(report.getTestClass())) + 
/*  77 */       " Protocol Analysis";
/*  78 */     this.application.addOutput(title, report.getContent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handle(AlgebraReport report) {
/*  85 */     String title = String.valueOf(report.toString(report.getTestClass())) + 
/*  86 */       " Algebraic Analysis";
/*  87 */     this.application.addOutput(title, report.getContent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handle(StateReport report) {
/*  94 */     String title = String.valueOf(report.toString(report.getTestClass())) + 
/*  95 */       " State Space Analysis";
/*  96 */     this.application.addOutput(title, report.getContent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handle(CycleReport report) {
/* 103 */     String state = report.getStartingState();
/* 104 */     String title = "Test Cycle #" + report.getTestCycle();
/* 105 */     if (state != null)
/* 106 */       title = String.valueOf(state) + ": " + title; 
/* 107 */     this.application.addOutput(title, report.getContent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handle(SummaryReport report) {
/* 114 */     String title = String.valueOf(report.toString(report.getTestClass())) + 
/* 115 */       " Test Summary";
/* 116 */     this.application.addOutput(title, report.getContent());
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/ReportPublisher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */