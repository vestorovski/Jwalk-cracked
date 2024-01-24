/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import org.jwalk.LoaderException;
/*     */ import org.jwalk.Settings;
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
/*     */ public class ClassFinderPanel
/*     */   extends AbstractPanel
/*     */ {
/*     */   private JTextField rootField;
/*     */   private JTextField nameField;
/*  40 */   private File directory = null;
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
/*     */   public ClassFinderPanel(JWalkTester tester) {
/*  55 */     super(tester);
/*  56 */     Settings settings = this.application.getSettings();
/*  57 */     this.directory = settings.getTestClassDirectory();
/*  58 */     setLayout(new GridLayout(4, 1));
/*  59 */     setBorder(BorderFactory.createTitledBorder("Test Class"));
/*     */     
/*  61 */     this.rootField = new JTextField();
/*  62 */     this.rootField.setText(this.directory.getAbsolutePath());
/*  63 */     this.rootField.setEditable(false);
/*  64 */     this.rootField.setToolTipText("The working directory for the test class");
/*  65 */     JButton rootButton = new JButton("Browse");
/*  66 */     rootButton.setToolTipText("Browse to select the working directory");
/*  67 */     rootButton.addActionListener(
/*  68 */         new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/*  70 */             ClassFinderPanel.this.browseForClass(true);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  75 */     JPanel rootPanel = new JPanel();
/*  76 */     rootPanel.setLayout(new BoxLayout(rootPanel, 0));
/*  77 */     rootPanel.add(this.rootField);
/*  78 */     rootPanel.add(rootButton);
/*  79 */     add(new JLabel(" Location: "));
/*  80 */     add(rootPanel);
/*     */     
/*  82 */     this.nameField = new JTextField();
/*  83 */     this.nameField.setEditable(true);
/*  84 */     this.nameField.setToolTipText("Enter the (qualified) name of the test class");
/*  85 */     JButton nameButton = new JButton("Browse");
/*  86 */     nameButton.setToolTipText("Browse within a package for the test class");
/*  87 */     nameButton.addActionListener(
/*  88 */         new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/*  90 */             ClassFinderPanel.this.browseForClass(false);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  95 */     JPanel namePanel = new JPanel();
/*  96 */     namePanel.setLayout(new BoxLayout(namePanel, 0));
/*  97 */     namePanel.add(this.nameField);
/*  98 */     namePanel.add(nameButton);
/*  99 */     add(new JLabel(" Name: "));
/* 100 */     add(namePanel);
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
/*     */   private void browseForClass(boolean isRoot) {
/* 116 */     JFileChooser chooser = new JFileChooser(this.directory);
/* 117 */     chooser.setFileSelectionMode(2);
/* 118 */     chooser.setFileFilter(new FileFilter() {
/*     */           public boolean accept(File f) {
/* 120 */             return !(!f.isDirectory() && !f.getName().endsWith(".class"));
/*     */           }
/*     */           public String getDescription() {
/* 123 */             return "Folders and Java class files";
/*     */           }
/*     */         });
/* 126 */     int result = chooser.showOpenDialog(this.application);
/* 127 */     if (result == 0) {
/* 128 */       File selected = chooser.getSelectedFile();
/* 129 */       if (this.directory == null || isRoot)
/* 130 */         if (selected.isDirectory()) {
/* 131 */           this.directory = selected;
/*     */         } else {
/* 133 */           this.directory = chooser.getCurrentDirectory();
/*     */         }  
/* 135 */       String dirName = this.directory.getAbsolutePath();
/* 136 */       String clsName = selected.getAbsolutePath();
/*     */       
/* 138 */       if (selected.isFile() && clsName.contains(dirName)) {
/* 139 */         clsName = clsName.substring(dirName.length() + 1);
/* 140 */         clsName = clsName.substring(0, clsName.indexOf(".class"));
/*     */       } else {
/*     */         
/* 143 */         clsName = "";
/* 144 */       }  this.rootField.setText(dirName);
/* 145 */       this.nameField.setText(clsName.replace(File.separatorChar, '.'));
/* 146 */       this.application.clearOutput();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSettings() throws LoaderException {
/* 156 */     Settings settings = this.application.getSettings();
/* 157 */     settings.setTestClassDirectory(this.directory);
/* 158 */     settings.setTestClass(this.nameField.getText());
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/ClassFinderPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */