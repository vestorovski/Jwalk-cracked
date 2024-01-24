/*     */ package org.jwalk;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import org.jwalk.out.Answer;
/*     */ import org.jwalk.out.Confirmation;
/*     */ import org.jwalk.out.Edition;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Report;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Console
/*     */   implements QuestionListener, ReportListener
/*     */ {
/*  47 */   private BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
/*  48 */   private PrintStream monitor = System.out;
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
/*     */   public void publish(ReportEvent event) {
/*  60 */     Report report = event.getReport();
/*  61 */     this.monitor.print(report);
/*  62 */     this.monitor.flush();
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
/*  74 */     Question question = event.getQuestion();
/*  75 */     Edition edition = question.getEdition();
/*  76 */     switch (edition) {
/*     */       case CONFIRM_DIALOG:
/*  78 */         return handle((Confirmation)question);
/*     */       case NOTIFY_DIALOG:
/*  80 */         return handle((Notification)question);
/*     */     } 
/*     */     
/*  83 */     return Answer.QUIT;
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
/*     */   private Answer handle(Confirmation question) {
/*  97 */     this.monitor.print("JWalk Query: \n\n");
/*  98 */     this.monitor.print(question.getContent());
/*  99 */     this.monitor.print("\n\n"); try {
/*     */       char key;
/*     */       do {
/* 102 */         this.monitor.print(question.getQuestion());
/* 103 */         String line = this.keyboard.readLine();
/* 104 */         this.monitor.println();
/* 105 */         key = (line.length() == 0) ? 'y' : line.charAt(0);
/* 106 */         if (key == 'y' || key == 'Y')
/* 107 */           return Answer.YES; 
/* 108 */         if (key == 'n' || key == 'N')
/* 109 */           return Answer.NO; 
/* 110 */       } while (key != 'q' && key != 'Q');
/* 111 */       return Answer.QUIT;
/* 112 */     } catch (IOException ex) {
/* 113 */       return Answer.QUIT;
/*     */     } 
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
/*     */   private Answer handle(Notification notice) {
/* 128 */     Urgency urgency = notice.getUrgency();
/* 129 */     switch (urgency) {
/*     */       case NOTICE:
/* 131 */         this.monitor.print("JWalk Notice: \n\n");
/*     */         break;
/*     */       case WARNING:
/* 134 */         this.monitor.print("JWalk Warning: \n\n");
/*     */         break;
/*     */       case null:
/* 137 */         this.monitor.print("JWalk Error: \n\n");
/*     */         break;
/*     */
        default:
            throw new IllegalStateException("Unexpected value: " + urgency);
    } 
/*     */ 
/*     */     
/* 142 */     this.monitor.print(notice.getContent());
/* 143 */     this.monitor.print("\n\n"); try {
/*     */       char key;
/*     */       do {
/* 146 */         this.monitor.print(notice.getQuestion());
/* 147 */         String line = this.keyboard.readLine();
/* 148 */         this.monitor.println();
/* 149 */         key = (line.length() == 0) ? 'y' : line.charAt(0);
/* 150 */       } while (key != 'y' && key != 'Y');
/* 151 */       return Answer.OK;
/* 152 */     } catch (IOException ex) {
/* 153 */       return Answer.OK;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/Console.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */