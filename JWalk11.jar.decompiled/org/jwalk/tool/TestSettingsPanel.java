/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.GridLayout;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.SettingsException;
/*     */ import org.jwalk.Strategy;
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
/*     */ public class TestSettingsPanel
/*     */   extends AbstractPanel
/*     */ {
/*     */   private JComboBox<Object> strategyBox;
/*     */   private JComboBox<Object> modalityBox;
/*     */   private JSpinner depthSpinner;
/*     */   
/*     */   public TestSettingsPanel(JWalkTester tester) {
/*  49 */     super(tester);
/*  50 */     setLayout(new GridLayout(3, 2));
/*  51 */     setBorder(BorderFactory.createTitledBorder("Test Settings"));
/*  52 */     Settings settings = this.application.getSettings();
/*     */     
/*  54 */     add(new JLabel(" Strategy: "));
/*  55 */     this.strategyBox = new JComboBox((Object[])Strategy.values());
/*  56 */     this.strategyBox.setSelectedItem(settings.getStrategy());
/*  57 */     this.strategyBox.setToolTipText("Select the desired test strategy");
/*  58 */     add(this.strategyBox);
/*     */     
/*  60 */     add(new JLabel(" Modality: "));
/*  61 */     this.modalityBox = new JComboBox((Object[])Modality.values());
/*  62 */     this.modalityBox.setSelectedItem(settings.getModality());
/*  63 */     this.modalityBox.setToolTipText("Select the desired test modality");
/*  64 */     add(this.modalityBox);
/*     */     
/*  66 */     add(new JLabel(" Test Depth: "));
/*  67 */     this.depthSpinner = new JSpinner(new SpinnerNumberModel(
/*  68 */           settings.getTestDepth(), 0, 20, 1));
/*  69 */     this.depthSpinner.setToolTipText("Select the maximum length of test sequences");
/*  70 */     add(this.depthSpinner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSettings() throws SettingsException {
/*  81 */     Settings settings = this.application.getSettings();
/*  82 */     Object value = null;
/*     */     try {
/*  84 */       value = this.strategyBox.getSelectedItem();
/*  85 */       settings.setStrategy((Strategy)value);
/*  86 */       value = this.modalityBox.getSelectedItem();
/*  87 */       settings.setModality((Modality)value);
/*     */     }
/*  89 */     catch (ClassCastException badConversion) {
/*  90 */       SettingsException ex = new SettingsException(value);
/*  91 */       ex.initCause(badConversion);
/*  92 */       throw ex;
/*     */     } 
/*     */     try {
/*  95 */       value = this.depthSpinner.getValue();
/*  96 */       settings.setTestDepth(((Integer)value).intValue());
/*     */     }
/*  98 */     catch (ClassCastException badConversion) {
/*  99 */       SettingsException ex = new SettingsException(value, true);
/* 100 */       ex.initCause(badConversion);
/* 101 */       throw ex;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/TestSettingsPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */