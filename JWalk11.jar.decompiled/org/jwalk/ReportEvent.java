/*    */ package org.jwalk;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.jwalk.out.Report;
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
/*    */ public class ReportEvent
/*    */   extends EventObject
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected Report report;
/*    */   
/*    */   public ReportEvent(JWalker source, Report report) {
/* 36 */     super(source);
/* 37 */     this.report = report;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JWalker getSource() {
/* 45 */     return (JWalker)super.getSource();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Report getReport() {
/* 53 */     return this.report;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return this.report.toString();
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/ReportEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */