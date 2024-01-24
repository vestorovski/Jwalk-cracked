/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
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
/*     */ public class TestExecutePanel
/*     */   extends AbstractPanel
/*     */ {
/*     */   private static final String standingPath = "/org/jwalk/tool/standingJimbo.gif";
/*     */   private static final String steppingPath = "/org/jwalk/tool/steppingJimbo.gif";
/*     */   private static final String walkingPath = "/org/jwalk/tool/walkingJimbo.gif";
/*     */   private JButton walkerButton;
/*     */   private JButton cancelButton;
/*     */   
/*     */   public TestExecutePanel(JWalkTester tester) {
/*  54 */     super(tester);
/*  55 */     setLayout(new GridLayout(1, 2));
/*  56 */     setBorder(BorderFactory.createTitledBorder("Test Execution"));
/*     */     
/*  58 */     ImageIcon standingIcon = new ImageIcon(
/*  59 */         getClass().getResource("/org/jwalk/tool/standingJimbo.gif"));
/*  60 */     ImageIcon steppingIcon = new ImageIcon(
/*  61 */         getClass().getResource("/org/jwalk/tool/steppingJimbo.gif"));
/*  62 */     ImageIcon walkingIcon = new ImageIcon(
/*  63 */         getClass().getResource("/org/jwalk/tool/walkingJimbo.gif"));
/*     */     
/*  65 */     this.walkerButton = new JButton(standingIcon);
/*  66 */     this.walkerButton.setBackground(Color.WHITE);
/*  67 */     this.walkerButton.setPressedIcon(walkingIcon);
/*  68 */     this.walkerButton.setRolloverIcon(steppingIcon);
/*  69 */     this.walkerButton.setDisabledIcon(walkingIcon);
/*  70 */     this.walkerButton.setToolTipText("Run the current test series");
/*  71 */     this.walkerButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/*  73 */             TestExecutePanel.this.application.executeTests();
/*     */           }
/*     */         });
/*     */     
/*  77 */     this.cancelButton = new JButton("Cancel");
/*  78 */     this.cancelButton.setToolTipText("Abort the current test series");
/*  79 */     this.cancelButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/*  81 */             TestExecutePanel.this.application.interruptTests();
/*     */           }
/*     */         });
/*  84 */     this.cancelButton.setEnabled(false);
/*     */     
/*  86 */     JPanel labelPanel = new JPanel();
/*  87 */     labelPanel.setLayout(new FlowLayout(1, 0, 30));
/*  88 */     labelPanel.add(new JLabel(" Start JWalking! "));
/*  89 */     labelPanel.add(this.cancelButton);
/*     */     
/*  91 */     JPanel buttonPanel = new JPanel();
/*  92 */     buttonPanel.setLayout(new FlowLayout(1, 0, 0));
/*  93 */     buttonPanel.add(this.walkerButton);
/*     */     
/*  95 */     add(labelPanel);
/*  96 */     add(buttonPanel);
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
/*     */   public void setEnabled(boolean enabled) {
/* 110 */     synchronized (this) {
/* 111 */       super.setEnabled(enabled);
/* 112 */       this.walkerButton.setEnabled(enabled);
/* 113 */       this.cancelButton.setEnabled(!enabled);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void restart() {
/* 123 */     this.walkerButton.setPressedIcon((Icon)null);
/* 124 */     this.walkerButton.setDisabledIcon((Icon)null);
/* 125 */     ImageIcon walkingIcon = new ImageIcon(
/* 126 */         Toolkit.getDefaultToolkit().createImage(
/* 127 */           getClass().getResource("/org/jwalk/tool/walkingJimbo.gif")));
/* 128 */     this.walkerButton.setPressedIcon(walkingIcon);
/* 129 */     this.walkerButton.setDisabledIcon(walkingIcon);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/TestExecutePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */