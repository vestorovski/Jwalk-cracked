/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import javax.swing.JOptionPane;
/*     */ import org.jwalk.QuestionEvent;
/*     */ import org.jwalk.QuestionListener;
/*     */ import org.jwalk.out.Answer;
/*     */ import org.jwalk.out.Confirmation;
/*     */ import org.jwalk.out.Edition;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Urgency;
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
/*     */ public class QuestionMaster
/*     */   implements QuestionListener
/*     */ {
/*     */   private JWalkTester application;
/*     */   
/*     */   public QuestionMaster(JWalkTester tester) {
/*  39 */     this.application = tester;
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
/*     */   public Answer respond(QuestionEvent event) {
/*  51 */     Question question = event.getQuestion();
/*  52 */     Edition edition = question.getEdition();
/*  53 */     switch (edition) {
/*     */       case CONFIRM_DIALOG:
/*  55 */         return handle((Confirmation)question);
/*     */       case NOTIFY_DIALOG:
/*  57 */         return handle((Notification)question);
/*     */     } 
/*     */     
/*  60 */     return Answer.QUIT;
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
/*     */   protected Answer handle(Confirmation question) {
/*  72 */     String title = "JWalk Query";
/*  73 */     int result = JOptionPane.showConfirmDialog(
/*  74 */         this.application, question.getContent(), title, 
/*  75 */         1);
/*  76 */     switch (result) {
/*     */       case 0:
/*  78 */         return Answer.YES;
/*     */       case 1:
/*  80 */         return Answer.NO;
/*     */       case 2:
/*  82 */         return Answer.QUIT;
/*     */     } 
/*  84 */     return Answer.QUIT;
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
/*     */   protected Answer handle(Notification notice) {
/*  96 */     Urgency urgency = notice.getUrgency();
/*  97 */     switch (urgency) {
/*     */       case SILENT:
/*  99 */         this.application.finishedTests();
/* 100 */         return Answer.OK;
/*     */       case NOTICE:
/* 102 */         JOptionPane.showMessageDialog(this.application, 
/* 103 */             notice.getContent(), "JWalk Notice", 
/* 104 */             1);
/* 105 */         return Answer.OK;
/*     */       case WARNING:
/* 107 */         JOptionPane.showMessageDialog(this.application, 
/* 108 */             notice.getContent(), "JWalk Warning", 
/* 109 */             2);
/* 110 */         return Answer.OK;
/*     */       case null:
/* 112 */         this.application.handleException(notice.getException());
/* 113 */         return Answer.OK;
/*     */
        default:
            throw new IllegalStateException("Unexpected value: " + urgency);
    }
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/QuestionMaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */