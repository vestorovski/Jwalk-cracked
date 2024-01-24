/*    */ package org.jwalk.tool;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ import org.jwalk.JWalkException;
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
/*    */ public abstract class AbstractDialog
/*    */   extends JDialog
/*    */ {
/*    */   protected JWalkTester application;
/*    */   
/*    */   public AbstractDialog(JWalkTester tester, String title) {
/* 35 */     super(tester, title, true);
/* 36 */     this.application = tester;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected JPanel createExitDialogPanel() {
/* 46 */     JPanel panel = new JPanel();
/* 47 */     panel.setLayout(new FlowLayout(1));
/* 48 */     JButton okButton = new JButton("OK");
/* 49 */     okButton.addActionListener(new ActionListener() {
/*    */           public void actionPerformed(ActionEvent event) {
/* 51 */             AbstractDialog.this.saveSettings(true);
/*    */           }
/*    */         });
/* 54 */     JButton cancelButton = new JButton("Cancel");
/* 55 */     cancelButton.addActionListener(new ActionListener() {
/*    */           public void actionPerformed(ActionEvent event) {
/* 57 */             AbstractDialog.this.saveSettings(false);
/*    */           }
/*    */         });
/* 60 */     panel.add(okButton);
/* 61 */     panel.add(cancelButton);
/* 62 */     return panel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract void saveSettings(boolean paramBoolean);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void logException(JWalkException ex) {
/* 81 */     System.err.println(ex);
/* 82 */     Throwable cause = ex.getCause();
/* 83 */     while (cause != null) {
/* 84 */       System.err.println("Cause: " + cause);
/* 85 */       cause = cause.getCause();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/AbstractDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */