/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import org.jwalk.JWalkException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeneratorDialog
/*     */   extends AbstractDialog
/*     */ {
/*     */   private static final int DIALOG_WIDTH = 320;
/*     */   private static final int DIALOG_HEIGHT = 280;
/*     */   private static final String emptyString = "<empty>";
/*  58 */   private File directory = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private JTextField rootField;
/*     */ 
/*     */ 
/*     */   
/*     */   private JTextField nameField;
/*     */ 
/*     */ 
/*     */   
/*     */   private JList<String> generatorList;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneratorDialog(JWalkTester tester) {
/*  76 */     super(tester, "JWalk Custom Generators");
/*     */     
/*  78 */     setDefaultCloseOperation(2);
/*  79 */     getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  80 */     setMinimumSize(new Dimension(320, 280));
/*  81 */     setLayout(new BoxLayout(getContentPane(), 1));
/*  82 */     setLocationRelativeTo(tester);
/*  83 */     add(createGeneratorFinderPanel());
/*  84 */     add(createGeneratorChooserPanel());
/*  85 */     add(createExitDialogPanel());
/*  86 */     pack();
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
/*     */   private JPanel createGeneratorFinderPanel() {
/* 103 */     Settings settings = this.application.getSettings();
/* 104 */     this.directory = settings.getOracleDirectory();
/* 105 */     JPanel panel = new JPanel(new GridLayout(4, 1));
/* 106 */     panel.setBorder(BorderFactory.createTitledBorder("Generator"));
/*     */     
/* 108 */     this.rootField = new JTextField();
/* 109 */     this.rootField.setText(this.directory.getAbsolutePath());
/* 110 */     this.rootField.setEditable(false);
/* 111 */     this.rootField.setToolTipText("The directory for loading custom generators");
/*     */     
/* 113 */     JButton rootButton = new JButton("Browse");
/* 114 */     rootButton.setToolTipText("Browse to select the custom generator directory");
/* 115 */     rootButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 117 */             GeneratorDialog.this.browseForGenerator(true);
/*     */           }
/*     */         });
/*     */     
/* 121 */     JPanel rootPanel = new JPanel();
/* 122 */     rootPanel.setLayout(new BoxLayout(rootPanel, 0));
/* 123 */     rootPanel.add(this.rootField);
/* 124 */     rootPanel.add(rootButton);
/* 125 */     panel.add(new JLabel(" Location: "));
/* 126 */     panel.add(rootPanel);
/*     */     
/* 128 */     this.nameField = new JTextField();
/* 129 */     this.nameField.setEditable(true);
/* 130 */     this.nameField.setToolTipText("Enter the (qualified) name of the generator");
/* 131 */     JButton nameButton = new JButton("Browse");
/* 132 */     nameButton.setToolTipText("Browse within a package for the generator");
/* 133 */     nameButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 135 */             GeneratorDialog.this.browseForGenerator(false);
/*     */           }
/*     */         });
/*     */     
/* 139 */     JPanel namePanel = new JPanel();
/* 140 */     namePanel.setLayout(new BoxLayout(namePanel, 0));
/* 141 */     namePanel.add(this.nameField);
/* 142 */     namePanel.add(nameButton);
/* 143 */     panel.add(new JLabel(" Name: "));
/* 144 */     panel.add(namePanel);
/* 145 */     return panel;
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
/*     */   private JPanel createGeneratorChooserPanel() {
/* 159 */     JPanel panel = new JPanel();
/* 160 */     panel.setLayout(new BoxLayout(panel, 1));
/* 161 */     panel.setBorder(BorderFactory.createTitledBorder("Custom Generators"));
/*     */     
/* 163 */     JPanel addRemovePanel = new JPanel(new FlowLayout(1));
/* 164 */     JButton addButton = new JButton("Add");
/* 165 */     addButton.setToolTipText(
/* 166 */         "Add the selected generator to the custom generators");
/* 167 */     addButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 169 */             GeneratorDialog.this.addGenerator(GeneratorDialog.this.nameField.getText());
/*     */           }
/*     */         });
/* 172 */     JButton removeButton = new JButton("Remove");
/* 173 */     removeButton.setToolTipText(
/* 174 */         "Remove the selected generator from the custom generators");
/* 175 */     removeButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent event) {
/* 177 */             GeneratorDialog.this.removeGenerator(GeneratorDialog.this.nameField.getText());
/*     */           }
/*     */         });
/* 180 */     addRemovePanel.add(addButton);
/* 181 */     addRemovePanel.add(removeButton);
/* 182 */     panel.add(addRemovePanel);
/*     */     
/* 184 */     JPanel listPanel = new JPanel(new GridLayout(1, 1));
/* 185 */     listPanel.setBorder(BorderFactory.createEtchedBorder());
/* 186 */     DefaultListModel<String> model = new DefaultListModel<>();
/* 187 */     model.addElement("<empty>");
/* 188 */     this.generatorList = new JList<>(model);
/* 189 */     this.generatorList.setBackground(Color.WHITE);
/* 190 */     this.generatorList.setSelectionMode(0);
/* 191 */     Settings settings = this.application.getSettings();
/* 192 */     for (Class<?> generator : settings.getCustomGenerators())
/*     */     {
/* 194 */       addGenerator(generator.getName());
/*     */     }
/* 196 */     this.generatorList.setModel(model);
/* 197 */     this.generatorList.addListSelectionListener(new ListSelectionListener()
/*     */         {
/*     */           public void valueChanged(ListSelectionEvent event) {
/* 200 */             if (!event.getValueIsAdjusting())
/* 201 */               GeneratorDialog.this.selectGenerator(); 
/*     */           }
/*     */         });
/* 204 */     listPanel.add(this.generatorList);
/* 205 */     panel.add(listPanel);
/* 206 */     return panel;
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
/*     */   private void browseForGenerator(boolean isRoot) {
/* 222 */     JFileChooser chooser = new JFileChooser(this.directory);
/* 223 */     chooser.setFileSelectionMode(2);
/* 224 */     chooser.setFileFilter(new FileFilter() {
/*     */           public boolean accept(File f) {
/* 226 */             return !(!f.isDirectory() && 
/* 227 */               !f.getName().endsWith("Generator.class"));
/*     */           }
/*     */           public String getDescription() {
/* 230 */             return "Folders and JWalk generators";
/*     */           }
/*     */         });
/* 233 */     int result = chooser.showOpenDialog(this);
/* 234 */     if (result == 0) {
/* 235 */       File selected = chooser.getSelectedFile();
/* 236 */       if (this.directory == null || isRoot)
/* 237 */         if (selected.isDirectory()) {
/* 238 */           this.directory = selected;
/*     */         } else {
/* 240 */           this.directory = chooser.getCurrentDirectory();
/*     */         }  
/* 242 */       String dirName = this.directory.getAbsolutePath();
/* 243 */       String clsName = selected.getAbsolutePath();
/*     */       
/* 245 */       if (selected.isFile() && clsName.contains(dirName)) {
/* 246 */         clsName = clsName.substring(dirName.length() + 1);
/* 247 */         clsName = clsName.substring(0, clsName.indexOf(".class"));
/*     */       } else {
/*     */         
/* 250 */         clsName = "";
/*     */       } 
/* 252 */       this.rootField.setText(dirName);
/* 253 */       this.nameField.setText(clsName.replace(File.separatorChar, '.'));
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
/*     */   private void addGenerator(String name) {
/* 267 */     if (name.endsWith("Generator")) {
/* 268 */       DefaultListModel<String> model = (DefaultListModel)this.generatorList.getModel();
/* 269 */       if (model.contains("<empty>"))
/* 270 */         model.removeElement("<empty>"); 
/* 271 */       if (!model.contains(name))
/* 272 */         model.addElement(name); 
/* 273 */       this.generatorList.setModel(model);
/* 274 */       pack();
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
/*     */   private void removeGenerator(String name) {
/* 287 */     if (name.endsWith("Generator")) {
/* 288 */       DefaultListModel<String> model = (DefaultListModel)this.generatorList.getModel();
/* 289 */       model.removeElement(name);
/* 290 */       if (model.size() == 0)
/* 291 */         model.addElement("<empty>"); 
/* 292 */       this.generatorList.setModel(model);
/* 293 */       pack();
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
/*     */   private void selectGenerator() {
/* 305 */     int index = this.generatorList.getSelectedIndex();
/*     */     
/* 307 */     if (index != -1) {
/* 308 */       DefaultListModel<String> model = (DefaultListModel)this.generatorList.getModel();
/* 309 */       String selection = model.get(index);
/* 310 */       if (selection != "<empty>") {
/* 311 */         this.nameField.setText(selection);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveSettings(boolean okClicked) {
/* 331 */     boolean closeOnExit = true;
/* 332 */     if (okClicked) {
/*     */       
/* 334 */       Settings settings = this.application.getSettings();
/* 335 */       settings.setGeneratorDirectory(this.directory);
/*     */       
/* 337 */       DefaultListModel<String> model = (DefaultListModel)this.generatorList.getModel();
/* 338 */       List<String> newNames = new ArrayList<>(); byte b; int i; Object[] arrayOfObject;
/* 339 */       for (i = (arrayOfObject = model.toArray()).length, b = 0; b < i; ) { Object object = arrayOfObject[b];
/* 340 */         if (object != "<empty>")
/* 341 */           newNames.add((String)object); 
/*     */         b++; }
/*     */       
/* 344 */       List<String> oldNames = new ArrayList<>();
/* 345 */       for (Class<?> generator : settings.getCustomGenerators()) {
/* 346 */         oldNames.add(generator.getName());
/*     */       }
/* 348 */       for (String oldName : oldNames) {
/* 349 */         if (!newNames.contains(oldName))
/* 350 */           settings.removeCustomGenerator(oldName); 
/*     */       } 
/*     */       try {
/* 353 */         for (String newName : newNames) {
/* 354 */           if (!oldNames.contains(newName))
/* 355 */             settings.addCustomGenerator(newName); 
/*     */         } 
/* 357 */       } catch (LoaderException ex) {
/* 358 */         closeOnExit = false;
/* 359 */         handleLoaderException(ex);
/*     */       } 
/*     */     } 
/* 362 */     if (closeOnExit) {
/* 363 */       setVisible(false);
/* 364 */       dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleLoaderException(LoaderException ex) {
/* 375 */     if (ex.classNotFound()) {
/* 376 */       JOptionPane.showMessageDialog(this.application, 
/* 377 */           "A custom generator could not be found. \nPlease check each generator's location and \nthe spelling of each generator's name. ", 
/*     */ 
/*     */           
/* 380 */           "JWalk Error", 
/* 381 */           0);
/*     */     }
/* 383 */     if (ex.classNotQualified()) {
/* 384 */       JOptionPane.showMessageDialog(this.application, 
/* 385 */           "A custom generator could not be loaded. \nPlease change the location and load each \ngenerator using its package-qualified name. ", 
/*     */ 
/*     */           
/* 388 */           "JWalk Error", 
/* 389 */           0);
/*     */     }
/*     */     
/* 392 */     logException((JWalkException)ex);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/GeneratorDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */