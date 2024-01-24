/*     */ package org.jwalk;
/*     */ 
/*     */ import org.jwalk.core.AlgebraWalker;
/*     */ import org.jwalk.core.ProtocolWalker;
/*     */ import org.jwalk.core.StateInspector;
/*     */ import org.jwalk.core.StateSpaceWalker;
/*     */ import org.jwalk.core.TestCase;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
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
/*     */ public class JWalker
/*     */   implements Runnable
/*     */ {
/*     */   protected Settings settings;
/*     */   protected Channels channels;
/*     */   
/*     */   public JWalker() {
/*  57 */     this.settings = new Settings();
/*  58 */     this.channels = new Channels();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JWalker(Settings settings, Channels channels) {
/*  66 */     this.settings = settings;
/*  67 */     this.channels = channels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Settings getSettings() {
/*  75 */     return this.settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Channels getChannels() {
/*  83 */     return this.channels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getTestClass() {
/*  93 */     return this.settings.getTestClass();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() throws PermissionException, GeneratorException, ExecutionException {
/*     */     ProtocolWalker protocolWalker;
/*     */     AlgebraWalker algebraWalker;
/*     */     StateSpaceWalker stateSpaceWalker = null;
/* 118 */     JWalker walker = null;
/* 119 */     switch (this.settings.getStrategy()) {
/*     */       case PROTOCOL:
/* 121 */         protocolWalker = new ProtocolWalker(this.settings, this.channels);
/*     */         break;
/*     */       case null:
/* 124 */         algebraWalker = new AlgebraWalker(this.settings, this.channels);
/*     */         break;
/*     */
                case ALGEBRA:
                    break;
                case STATES:
/* 127 */         stateSpaceWalker = new StateSpaceWalker(this.settings, this.channels);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 132 */     TestCase.setInspector(
/* 133 */         new StateInspector(this.settings.getTreeDepth()));
/* 134 */     stateSpaceWalker.execute();
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
/*     */   public void run() {
/*     */     try {
/* 153 */       execute();
/* 154 */       this.channels.dispatch((Question)new Notification(this));
/*     */     }
/* 156 */     catch (JWalkException ex) {
/* 157 */       this.channels.dispatch((Question)new Notification(this, ex));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean outOfMemory() {
/* 168 */     return this.channels.outOfMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean userAborted() {
/* 178 */     return this.channels.userAborted();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/JWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */