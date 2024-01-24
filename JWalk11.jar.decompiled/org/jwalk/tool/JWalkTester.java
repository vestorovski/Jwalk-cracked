/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Toolkit;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.UIManager;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.Error;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.JWalkException;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.LoaderException;
/*     */ import org.jwalk.PermissionException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JWalkTester
/*     */   extends JFrame
/*     */   implements Thread.UncaughtExceptionHandler
/*     */ {
/*     */   private static final int MAIN_VIEWER_WIDTH = 800;
/*     */   private static final int MAIN_VIEWER_HEIGHT = 550;
/*     */   private static final int INPUT_PANEL_WIDTH = 320;
/*     */   private static final int INPUT_PANEL_HEIGHT = 490;
/*     */   private static final int OUTPUT_PANEL_ROWS = 30;
/*     */   private static final int OUTPUT_PANEL_COLS = 50;
/*     */   private static final String iconPath = "/org/jwalk/tool/minimizeJimbo.gif";
/*     */   private static final String splashText = "<html><h3>JWalk Tester</h3><hr>Version 1.1 (c) 2011, Anthony J H Simons<br><br>Department of Computer Science<br>University of Sheffield<br><br>Harness the power of lazy systematic unit testing and grow<br> test oracles for your Java classes, by walking through each<br> class as it evolves.  Save time writing difficult test cases and<br> rely on the systematic testing engine to discover all those<br> interleaved cases developers usually forget.</html>";
/*     */   private static final String helpText = "<html><h3>Quick Start</h3><hr>Navigate to the working directory, from which you launch<br>Java to execute your chosen test class;<br><br>Select the test class here, or navigate further into the<br>package owning the test class to find it;<br><br>Select a strategy to explore the method protocols, algebraic<br>constructions, or high-level state space;<br><br>Select a modality to inspect the class, explore its behaviour<br>or validate its behaviour against an oracle;<br><br>Select the maximum depth for generated test sequences;<br><br>Click on 'JWalking Jimbo' to start the test engine.</html>";
/*     */   private JWalker walker;
/*     */   private int[] memory;
/*     */   private ClassFinderPanel finderPanel;
/*     */   private TestSettingsPanel settingsPanel;
/*     */   private TestExecutePanel executePanel;
/*     */   private JTabbedPane outputPanel;
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 123 */       UIManager.setLookAndFeel(
/* 124 */           UIManager.getSystemLookAndFeelClassName());
/*     */     }
/* 126 */     catch (Exception ex) {
/* 127 */       JOptionPane.showMessageDialog(null, 
/* 128 */           "Cannot install platform look-and-feel;\nreverting to standard look-and-feel.", 
/* 129 */           "JWalk Warning", 
/* 130 */           2);
/*     */     } 
/* 132 */     JWalkTester viewer = new JWalkTester();
/* 133 */     Thread.setDefaultUncaughtExceptionHandler(viewer);
/* 134 */     viewer.setVisible(true);
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
/*     */   public JWalkTester() {
/* 151 */     this.walker = new JWalker();
/* 152 */     Channels channels = this.walker.getChannels();
/* 153 */     channels.addQuestionListener(new QuestionMaster(this));
/* 154 */     channels.addReportListener(new ReportPublisher(this));
/*     */     
/* 156 */     setLayout(new BorderLayout());
/* 157 */     add(createInputPanel(), "West");
/* 158 */     add(createOutputPanel(), "Center");
/* 159 */     setDefaultCloseOperation(3);
/* 160 */     setTitle("JWalk Tester");
/* 161 */     setSize(800, 550);
/*     */     
/* 163 */     setIconImage(Toolkit.getDefaultToolkit().getImage(
/* 164 */           getClass().getResource("/org/jwalk/tool/minimizeJimbo.gif")));
/* 165 */     setLocationRelativeTo((Component)null);
/* 166 */     setResizable(true);
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
/*     */   private JPanel createInputPanel() {
/* 179 */     JPanel panel = new JPanel();
/* 180 */     panel.setLayout(new BoxLayout(panel, 1));
/* 181 */     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 182 */     panel.setPreferredSize(
/* 183 */         new Dimension(320, 490));
/*     */     
/* 185 */     panel.add(this.finderPanel = new ClassFinderPanel(this));
/* 186 */     panel.add(this.settingsPanel = new TestSettingsPanel(this));
/* 187 */     panel.add(this.executePanel = new TestExecutePanel(this));
/* 188 */     panel.add(new CustomPanel(this));
/* 189 */     JPanel inputPanel = new JPanel(new FlowLayout(3));
/* 190 */     inputPanel.add(panel);
/* 191 */     return inputPanel;
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
/*     */   private JTabbedPane createOutputPanel() {
/* 205 */     this.outputPanel = new JTabbedPane(
/* 206 */         1, 
/* 207 */         0);
/* 208 */     addAboutInfo();
/* 209 */     addHelpInfo();
/* 210 */     return this.outputPanel;
/*     */   }
/*     */   
/*     */   private void addAboutInfo() {
/* 214 */     JLabel about = new JLabel("<html><h3>JWalk Tester</h3><hr>Version 1.1 (c) 2011, Anthony J H Simons<br><br>Department of Computer Science<br>University of Sheffield<br><br>Harness the power of lazy systematic unit testing and grow<br> test oracles for your Java classes, by walking through each<br> class as it evolves.  Save time writing difficult test cases and<br> rely on the systematic testing engine to discover all those<br> interleaved cases developers usually forget.</html>");
/* 215 */     JPanel infoPanel = new JPanel(new FlowLayout(3, 20, 0));
/* 216 */     infoPanel.setBackground(Color.WHITE);
/* 217 */     infoPanel.add(about);
/* 218 */     this.outputPanel.addTab("About JWalk", null, infoPanel, 
/* 219 */         "JWalk splash screen with version information");
/*     */   }
/*     */   
/*     */   private void addHelpInfo() {
/* 223 */     JLabel help = new JLabel("<html><h3>Quick Start</h3><hr>Navigate to the working directory, from which you launch<br>Java to execute your chosen test class;<br><br>Select the test class here, or navigate further into the<br>package owning the test class to find it;<br><br>Select a strategy to explore the method protocols, algebraic<br>constructions, or high-level state space;<br><br>Select a modality to inspect the class, explore its behaviour<br>or validate its behaviour against an oracle;<br><br>Select the maximum depth for generated test sequences;<br><br>Click on 'JWalking Jimbo' to start the test engine.</html>");
/* 224 */     JPanel helpPanel = new JPanel(new FlowLayout(3, 20, 0));
/* 225 */     helpPanel.setBackground(Color.WHITE);
/* 226 */     helpPanel.add(help);
/* 227 */     this.outputPanel.addTab("JWalk Help", null, helpPanel, 
/* 228 */         "Would you like some help to get started?");
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
/*     */   public Settings getSettings() {
/* 240 */     return this.walker.getSettings();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearOutput() {
/* 249 */     this.outputPanel.removeAll();
/* 250 */     addHelpInfo();
/* 251 */     addAboutInfo();
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
/*     */   public void addOutput(String title, String content) {
/* 263 */     JScrollPane scrollPane = new JScrollPane();
/* 264 */     scrollPane.setVerticalScrollBarPolicy(
/* 265 */         20);
/* 266 */     scrollPane.setHorizontalScrollBarPolicy(
/* 267 */         30);
/* 268 */     JLabel padding = new JLabel("   ");
/* 269 */     JTextArea textArea = new JTextArea(
/* 270 */         30, 50);
/* 271 */     textArea.setFont(new Font("SansSerif", 0, 12));
/* 272 */     textArea.setEditable(false);
/* 273 */     textArea.setLineWrap(false);
/* 274 */     textArea.append(content);
/* 275 */     scrollPane.setRowHeaderView(padding);
/* 276 */     scrollPane.setViewportView(textArea);
/* 277 */     scrollPane.getRowHeader().setBackground(Color.WHITE);
/* 278 */     scrollPane.getViewport().setBackground(Color.WHITE);
/* 279 */     this.outputPanel.addTab(title, null, scrollPane, 
/* 280 */         "Select and view test results");
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
/*     */   public void executeTests() {
/*     */     try {
/* 300 */       this.executePanel.setEnabled(false);
/* 301 */       this.outputPanel.removeAll();
/* 302 */       this.finderPanel.saveSettings();
/* 303 */       this.settingsPanel.saveSettings();
/*     */       
/* 305 */       this.memory = new int[1048576];
/*     */       
/* 307 */       Thread worker = new Thread((Runnable)this.walker);
/* 308 */       worker.setPriority(2);
/* 309 */       worker.start();
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 314 */     catch (JWalkException ex) {
/*     */       
/* 316 */       handleException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finishedTests() {
/* 327 */     this.executePanel.setEnabled(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interruptTests() {
/* 336 */     this.walker.getChannels().setUserAborted();
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
/*     */   public void handleException(JWalkException ex) {
/* 349 */     Error error = ex.getError();
/* 350 */     switch (error) {
/*     */       case LOADER_ERROR:
/* 352 */         loaderException((LoaderException)ex);
/*     */         break;
/*     */       case SETTINGS_ERROR:
/* 355 */         settingsException((SettingsException)ex);
/*     */         break;
/*     */       case PERMISSION_ERROR:
/* 358 */         permissionsException((PermissionException)ex);
/*     */         break;
/*     */       case GENERATOR_ERROR:
/* 361 */         generatorException((GeneratorException)ex);
/*     */         break;
/*     */       case null:
/* 364 */         executionException((ExecutionException)ex);
/*     */         break;
/*     */       default:
/* 367 */         logException(Thread.currentThread(), (Throwable)ex); break;
/*     */     } 
/* 369 */     finishedTests();
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
/*     */   public void uncaughtException(Thread thread, Throwable thrown) {
/* 384 */     this.memory = null;
/* 385 */     if (thrown instanceof OutOfMemoryError) {
/* 386 */       this.walker.getChannels().setOutOfMemory();
/* 387 */       Thread.yield();
/* 388 */       if (thread.getName().startsWith("Image")) {
/* 389 */         this.executePanel.restart();
/*     */       }
/*     */     } else {
/* 392 */       this.walker.getChannels().setUserAborted();
/* 393 */       Thread.yield();
/* 394 */       String message = thrown.getMessage();
/* 395 */       JOptionPane.showMessageDialog(this, 
/* 396 */           "JWalk encountered an unexpected exception:\n" + (
/* 397 */           (message == null) ? thrown.toString() : message), 
/* 398 */           "Fatal Error", 
/* 399 */           0);
/*     */     } 
/*     */     
/* 402 */     logException(thread, thrown);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loaderException(LoaderException ex) {
/* 412 */     addHelpInfo();
/* 413 */     if (ex.classNotFound()) {
/* 414 */       JOptionPane.showMessageDialog(this, 
/* 415 */           "The test class could not be found. \nPlease check the location and the \nspelling of the test class's name. ", 
/*     */ 
/*     */           
/* 418 */           "JWalk Error", 
/* 419 */           0);
/*     */     }
/* 421 */     if (ex.classNotQualified()) {
/* 422 */       JOptionPane.showMessageDialog(this, 
/* 423 */           "The test class could not be loaded. \nPlease change the location and load \nusing a package-qualified class name. ", 
/*     */ 
/*     */           
/* 426 */           "JWalk Error", 
/* 427 */           0);
/*     */     }
/*     */     
/* 430 */     logException(Thread.currentThread(), (Throwable)ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void settingsException(SettingsException ex) {
/* 439 */     if (ex.enumConversionFailed()) {
/* 440 */       JOptionPane.showMessageDialog(this, 
/* 441 */           "The user-supplied test setting: " + ex.getValue() + 
/* 442 */           " \ncould not be converted into an enum constant. \n" + 
/* 443 */           "Please supply a canonical string in uppercase.", 
/* 444 */           "JWalk Error", 
/* 445 */           0);
/*     */     }
/* 447 */     if (ex.intConversionFailed()) {
/* 448 */       JOptionPane.showMessageDialog(this, 
/* 449 */           "The user-supplied test setting: " + ex.getValue() + 
/* 450 */           " \ncould not be converted into an int value. \n" + 
/* 451 */           "Please supply a string consisting only of digits. ", 
/* 452 */           "JWalk Error", 
/* 453 */           0);
/*     */     }
/*     */     
/* 456 */     logException(Thread.currentThread(), (Throwable)ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void permissionsException(PermissionException ex) {
/* 467 */     if (ex.testClassRefused()) {
/* 468 */       JOptionPane.showMessageDialog(this, 
/* 469 */           "Permission was refused to create and \nexecute the test class: " + 
/* 470 */           ex.getType().getSimpleName() + 
/* 471 */           ". \nIs it non-public, abstract or an interface? ", 
/* 472 */           "JWalk Error", 
/* 473 */           0);
/*     */     }
/* 475 */     if (ex.generatorRefused()) {
/* 476 */       JOptionPane.showMessageDialog(this, 
/* 477 */           "Permission was refused to instantiate \nthe custom generator: " + 
/* 478 */           ex.getType().getSimpleName() + 
/* 479 */           ". \nDoes it have a public default constructor? ", 
/* 480 */           "JWalk Error", 
/* 481 */           0);
/*     */     }
/*     */     
/* 484 */     logException(Thread.currentThread(), (Throwable)ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generatorException(GeneratorException ex) {
/* 494 */     if (ex.creationFailed()) {
/* 495 */       JOptionPane.showMessageDialog(this, 
/* 496 */           "No test input could be synthesised for \nthe parameter type: " + 
/*     */           
/* 498 */           ex.getType().getSimpleName() + 
/* 499 */           ". \nPlease supply a custom generator. ", 
/* 500 */           "JWalk Error", 
/* 501 */           0);
/*     */     }
/* 503 */     if (ex.generatorFailed()) {
/* 504 */       JOptionPane.showMessageDialog(this, 
/* 505 */           String.valueOf(ex.getGenerator().getClass().getSimpleName()) + 
/* 506 */           " failed while synthesising \n" + 
/* 507 */           "a value for the parameter type: " + 
/* 508 */           ex.getType().getSimpleName() + 
/* 509 */           ". \nPlease check and repair the generator. ", 
/* 510 */           "JWalk Error", 
/* 511 */           0);
/*     */     }
/*     */     
/* 514 */     logException(Thread.currentThread(), (Throwable)ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void executionException(ExecutionException ex) {
/* 525 */     if (ex.invocationFailed()) {
/* 526 */       JOptionPane.showMessageDialog(this, 
/* 527 */           "An illegal attempt was made to invoke \nthe operation: " + 
/* 528 */           ex.getOperation().getName() + ".  \n" + 
/* 529 */           "Check visibility, security and arguments. ", 
/* 530 */           "JWalk Error", 
/* 531 */           0);
/*     */     }
/* 533 */     if (ex.executionFailed()) {
/* 534 */       JOptionPane.showMessageDialog(this, 
/* 535 */           "The operation: " + ex.getOperation().getName() + 
/* 536 */           " \nfailed randomly. Please ensure that \n" + 
/* 537 */           " it behaves in a deterministic way. ", 
/* 538 */           "JWalk Error", 
/* 539 */           0);
/*     */     }
/*     */     
/* 542 */     logException(Thread.currentThread(), (Throwable)ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void logException(Thread thread, Throwable thrown) {
/* 551 */     System.err.println(thread + ": " + thrown);
/* 552 */     Throwable cause = thrown.getCause();
/* 553 */     while (cause != null) {
/* 554 */       System.err.println("Cause: " + cause);
/* 555 */       cause = cause.getCause();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/JWalkTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */