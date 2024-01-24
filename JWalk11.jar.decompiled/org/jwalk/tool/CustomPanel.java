/*    */ package org.jwalk.tool;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JPanel;
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
/*    */ public class CustomPanel
/*    */   extends AbstractPanel
/*    */ {
/*    */   public CustomPanel(JWalkTester tester) {
/* 38 */     super(tester);
/* 39 */     setLayout(new GridLayout(1, 2));
/* 40 */     setBorder(BorderFactory.createTitledBorder("Custom Settings"));
/*    */     
/* 42 */     JButton genButton = new JButton("Generators");
/* 43 */     genButton.setToolTipText("Add or remove custom test input generators");
/* 44 */     genButton.addActionListener(
/* 45 */         new ActionListener() {
/*    */           public void actionPerformed(ActionEvent event) {
/* 47 */             CustomPanel.this.openGeneratorDialog();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 52 */     JButton confButton = new JButton("Configuration");
/* 53 */     confButton.setToolTipText("Change the basic configuration of JWalk");
/* 54 */     confButton.addActionListener(
/* 55 */         new ActionListener() {
/*    */           public void actionPerformed(ActionEvent event) {
/* 57 */             CustomPanel.this.openConfigureDialog();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 62 */     JPanel genPanel = new JPanel();
/* 63 */     genPanel.setLayout(new FlowLayout(1, 0, 0));
/* 64 */     genPanel.add(genButton);
/* 65 */     JPanel confPanel = new JPanel();
/* 66 */     confPanel.setLayout(new FlowLayout(1, 0, 0));
/* 67 */     confPanel.add(confButton);
/* 68 */     add(genPanel);
/* 69 */     add(confPanel);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void openConfigureDialog() {
/* 78 */     ConfigureDialog dialog = new ConfigureDialog(this.application);
/* 79 */     dialog.setVisible(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void openGeneratorDialog() {
/* 88 */     GeneratorDialog dialog = new GeneratorDialog(this.application);
/* 89 */     dialog.setVisible(true);
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/CustomPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */