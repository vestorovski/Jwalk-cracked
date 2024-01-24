/*     */ package org.jwalk.out;
/*     */ 
/*     */ import org.jwalk.JWalkException;
/*     */ import org.jwalk.JWalker;
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
/*     */ public class Notification
/*     */   extends Question
/*     */ {
/*     */   private String content;
/*     */   private JWalkException exception;
/*     */   private Urgency urgency;
/*     */   
/*     */   public Notification(JWalker walker, String content, Urgency urgency) {
/*  46 */     super(Edition.NOTIFY_DIALOG, walker);
/*  47 */     this.content = content;
/*  48 */     this.urgency = urgency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Notification(JWalker walker, JWalkException exception) {
/*  58 */     super(Edition.NOTIFY_DIALOG, walker);
/*  59 */     this.exception = exception;
/*  60 */     this.urgency = Urgency.ERROR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Notification(JWalker walker) {
/*  71 */     super(Edition.NOTIFY_DIALOG, walker);
/*  72 */     this.content = "Testing terminated";
/*  73 */     this.urgency = Urgency.SILENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQuestion() {
/*  83 */     return "OK? (y): ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContent() {
/*  93 */     if (this.content != null) {
/*  94 */       return this.content;
/*     */     }
/*  96 */     return this.exception.getMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JWalkException getException() {
/* 104 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Urgency getUrgency() {
/* 115 */     return this.urgency;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/Notification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */