/*     */ package org.jwalk;
/*     */ 
/*     */ import org.jwalk.out.Answer;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Report;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Channels
/*     */ {
/*  66 */   private static Console console = new Console();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private int operatingStatus = 0;
/*  75 */   private QuestionListener questionListener = console;
/*  76 */   private ReportListener reportListener = console;
/*     */ 
/*     */   
/*     */   private static final int NOMINAL = 0;
/*     */   
/*     */   private static final int MEMORY = 1;
/*     */   
/*     */   private static final int ABORT = 2;
/*     */ 
/*     */   
/*     */   public void addQuestionListener(QuestionListener listener) {
/*  87 */     this.questionListener = (listener == null) ? console : listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReportListener(ReportListener listener) {
/*  98 */     this.reportListener = (listener == null) ? console : listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeQuestionListener(QuestionListener listener) {
/* 108 */     if (this.questionListener == listener) {
/* 109 */       this.questionListener = console;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeReportListener(ReportListener listener) {
/* 119 */     if (this.reportListener == listener) {
/* 120 */       this.reportListener = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatch(Report report) {
/* 131 */     this.reportListener.publish(new ReportEvent(report.getJWalker(), report));
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
/*     */   public Answer dispatch(Question question) {
/* 145 */     return this.questionListener.respond(new QuestionEvent(question.getJWalker(), question));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean nominal() {
/* 155 */     return (this.operatingStatus == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interrupted() {
/* 164 */     return (this.operatingStatus != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean outOfMemory() {
/* 174 */     return (this.operatingStatus == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean userAborted() {
/* 184 */     return (this.operatingStatus == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNominal() {
/* 192 */     this.operatingStatus = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutOfMemory() {
/* 200 */     this.operatingStatus = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserAborted() {
/* 208 */     this.operatingStatus = 2;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/Channels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */