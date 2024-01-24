/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import org.jwalk.Convention;
/*     */ import org.jwalk.JWalkException;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.SettingsException;
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
/*     */ public class ConfigureDialog
/*     */   extends AbstractDialog
/*     */ {
/*     */   private static final int DIALOG_WIDTH = 320;
/*     */   private static final int DIALOG_HEIGHT = 280;
/*  51 */   private File directory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JTextField rootField;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JComboBox<Object> conventionBox;
/*     */ 
/*     */ 
/*     */   
/*     */   private JSpinner probeSpinner;
/*     */ 
/*     */ 
/*     */   
/*     */   private JSpinner stateSpinner;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigureDialog(JWalkTester tester) {
/*  75 */     super(tester, "JWalk Custom Settings");
/*  76 */     setDefaultCloseOperation(2);
/*  77 */     getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  78 */     setMinimumSize(new Dimension(320, 280));
/*  79 */     setLayout(new BoxLayout(getContentPane(), 1));
/*  80 */     setLocationRelativeTo(tester);
/*  81 */     add(createOracleFinderPanel());
/*  82 */     add(createCustomSettingsPanel());
/*  83 */     add(createExitDialogPanel());
/*  84 */     pack();
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
/*     */   private JPanel createOracleFinderPanel() {
/*  97 */     Settings settings = this.application.getSettings();
/*  98 */     this.directory = settings.getOracleDirectory();
/*  99 */     JPanel panel = new JPanel(new GridLayout(2, 1));
/* 100 */     panel.setBorder(BorderFactory.createTitledBorder("Test Oracles"));
/*     */     
/* 102 */     this.rootField = new JTextField();
/* 103 */     this.rootField.setText(this.directory.getAbsolutePath());
/* 104 */     this.rootField.setEditable(false);
/* 105 */     this.rootField.setToolTipText("The directory for loading and saving test oracles");
/*     */     
/* 107 */     JButton rootButton = new JButton("Browse");
/* 108 */     rootButton.setToolTipText("Browse to select the test oracle directory");
/* 109 */     rootButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 111 */             ConfigureDialog.this.browseForOracle();
/*     */           }
/*     */         });
/*     */     
/* 115 */     JPanel rootPanel = new JPanel();
/* 116 */     rootPanel.setLayout(new BoxLayout(rootPanel, 0));
/* 117 */     rootPanel.add(this.rootField);
/* 118 */     rootPanel.add(rootButton);
/* 119 */     panel.add(new JLabel(" Location: "));
/* 120 */     panel.add(rootPanel);
/* 121 */     return panel;
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
/*     */   private JPanel createCustomSettingsPanel() {
/* 133 */     Settings settings = this.application.getSettings();
/* 134 */     JPanel panel = new JPanel(new GridLayout(3, 2));
/* 135 */     panel.setBorder(BorderFactory.createTitledBorder("Configuration"));
/*     */     
/* 137 */     panel.add(new JLabel(" Convention: "));
/* 138 */     this.conventionBox = new JComboBox((Object[])Convention.values());
/* 139 */     this.conventionBox.setSelectedItem(settings.getConvention());
/* 140 */     this.conventionBox.setToolTipText(
/* 141 */         "Select the convention on including Object's methods");
/* 142 */     panel.add(this.conventionBox);
/*     */     
/* 144 */     panel.add(new JLabel(" Probe Depth: "));
/* 145 */     this.probeSpinner = new JSpinner(new SpinnerNumberModel(
/* 146 */           settings.getProbeDepth(), 0, 20, 1));
/* 147 */     this.probeSpinner.setToolTipText(
/* 148 */         "Select the maximum length of probe sequences");
/* 149 */     panel.add(this.probeSpinner);
/*     */     
/* 151 */     panel.add(new JLabel(" State Depth: "));
/* 152 */     this.stateSpinner = new JSpinner(new SpinnerNumberModel(
/* 153 */           settings.getTreeDepth(), 0, 5, 1));
/* 154 */     this.stateSpinner.setToolTipText(
/* 155 */         "Select the object tree-depth for state comparison");
/* 156 */     panel.add(this.stateSpinner);
/* 157 */     return panel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void browseForOracle() {
/* 165 */     JFileChooser chooser = new JFileChooser(this.directory);
/* 166 */     chooser.setFileSelectionMode(2);
/* 167 */     chooser.setFileFilter(new FileFilter() {
/*     */           public boolean accept(File f) {
/* 169 */             return !(!f.isDirectory() && !f.getName().endsWith(".jwk"));
/*     */           }
/*     */           public String getDescription() {
/* 172 */             return "Folders and JWalk test oracles";
/*     */           }
/*     */         });
/* 175 */     int result = chooser.showOpenDialog(this);
/* 176 */     if (result == 0) {
/* 177 */       File selected = chooser.getSelectedFile();
/* 178 */       if (selected.isDirectory()) {
/* 179 */         this.directory = selected;
/*     */       } else {
/* 181 */         this.directory = selected.getParentFile();
/* 182 */       }  String dirName = this.directory.getAbsolutePath();
/* 183 */       this.rootField.setText(dirName);
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
/*     */   protected void saveSettings(boolean okClicked) {
/* 198 */     boolean closeOnExit = true;
/* 199 */     if (okClicked) {
/*     */       
/* 201 */       Settings settings = this.application.getSettings();
/* 202 */       settings.setOracleDirectory(this.directory);
/* 203 */       Object value = null;
/*     */       try {
/* 205 */         value = this.conventionBox.getSelectedItem();
/* 206 */         settings.setConvention((Convention)value);
/*     */       }
/* 208 */       catch (ClassCastException badConversion) {
/* 209 */         closeOnExit = false;
/* 210 */         SettingsException ex = new SettingsException(value);
/* 211 */         ex.initCause(badConversion);
/* 212 */         handleSettingsException(ex);
/*     */       } 
/*     */       try {
/* 215 */         value = this.probeSpinner.getValue();
/* 216 */         settings.setProbeDepth(((Integer)value).intValue());
/* 217 */         value = this.stateSpinner.getValue();
/* 218 */         settings.setTreeDepth(((Integer)value).intValue());
/*     */       }
/* 220 */       catch (ClassCastException badConversion) {
/* 221 */         closeOnExit = false;
/* 222 */         SettingsException ex = new SettingsException(value, true);
/* 223 */         ex.initCause(badConversion);
/* 224 */         handleSettingsException(ex);
/*     */       } 
/*     */     } 
/* 227 */     if (closeOnExit) {
/* 228 */       setVisible(false);
/* 229 */       dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleSettingsException(SettingsException ex) {
/* 239 */     if (ex.enumConversionFailed()) {
/* 240 */       JOptionPane.showMessageDialog(this, 
/* 241 */           "The user-supplied test setting: " + ex.getValue() + 
/* 242 */           " \ncould not be converted into an enum constant. \n" + 
/* 243 */           "Please supply a canonical string in uppercase.", 
/* 244 */           "JWalk Error", 
/* 245 */           0);
/*     */     }
/* 247 */     if (ex.intConversionFailed()) {
/* 248 */       JOptionPane.showMessageDialog(this, 
/* 249 */           "The user-supplied test setting: " + ex.getValue() + 
/* 250 */           " \ncould not be converted into an int value. \n" + 
/* 251 */           "Please supply a string consisting only of digits. ", 
/* 252 */           "JWalk Error", 
/* 253 */           0);
/*     */     }
/*     */     
/* 256 */     logException((JWalkException)ex);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/ConfigureDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */