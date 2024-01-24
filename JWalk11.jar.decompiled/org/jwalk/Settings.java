/*     */ package org.jwalk;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jwalk.core.LicenseManager;
/*     */ import org.jwalk.gen.CustomGenerator;
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
/*     */ public class Settings
/*     */ {
/*  53 */   private int testDepth = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private int probeDepth = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private int treeDepth = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private Strategy strategy = Strategy.ALGEBRA;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private Modality modality = Modality.EXPLORE;
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
/*  96 */   private Convention convention = Convention.STANDARD;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   private File testClassDirectory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private File generatorDirectory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   private File oracleDirectory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   private List<Class<CustomGenerator>> customGenerators = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   private ClassLoader loader = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private Class<?> testClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ClassLoader getClassLoader() throws MalformedURLException {
/* 144 */     if (this.loader == null) {
/* 145 */       ClassLoader parent = getClass().getClassLoader();
/* 146 */       URL[] urls = {
/* 147 */           getTestClassDirectory().toURI().toURL(), 
/* 148 */           getGeneratorDirectory().toURI().toURL()
/*     */         };
/* 150 */       this.loader = new URLClassLoader(urls, parent);
/*     */     } 
/* 152 */     return this.loader;
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
/*     */   public Settings() {
/* 168 */     LicenseManager manager = new LicenseManager();
/* 169 */     manager.validateLicense();
/*     */     try {
/* 171 */       addCustomGenerator("org.jwalk.gen.StringGenerator");
/* 172 */       addCustomGenerator("org.jwalk.gen.InterfaceGenerator");
/* 173 */       addCustomGenerator("org.jwalk.gen.EnumGenerator");
/* 174 */       addCustomGenerator("org.jwalk.gen.PlatformGenerator");
/*     */     }
/* 176 */     catch (LoaderException ex) {
/* 177 */       System.out.println(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTestDepth() {
/* 188 */     return this.testDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProbeDepth() {
/* 198 */     return this.probeDepth;
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
/*     */   public int getTreeDepth() {
/* 212 */     return this.treeDepth;
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
/*     */   public Strategy getStrategy() {
/* 230 */     return this.strategy;
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
/*     */   public Modality getModality() {
/* 243 */     return this.modality;
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
/*     */   public Convention getConvention() {
/* 257 */     return this.convention;
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
/*     */   public File getTestClassDirectory() {
/* 269 */     if (this.testClassDirectory == null) {
/* 270 */       String current = System.getProperty("user.dir");
/* 271 */       return new File(current);
/*     */     } 
/*     */     
/* 274 */     return this.testClassDirectory;
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
/*     */   public File getGeneratorDirectory() {
/* 286 */     if (this.generatorDirectory == null) {
/* 287 */       return getTestClassDirectory();
/*     */     }
/* 289 */     return this.generatorDirectory;
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
/*     */   public File getOracleDirectory() {
/* 301 */     if (this.oracleDirectory == null) {
/* 302 */       return getTestClassDirectory();
/*     */     }
/* 304 */     return this.oracleDirectory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getTestClass() {
/* 314 */     return this.testClass;
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
/*     */   public List<Class<CustomGenerator>> getCustomGenerators() {
/* 326 */     if (this.customGenerators == null) {
/* 327 */       return Collections.emptyList();
/*     */     }
/* 329 */     return this.customGenerators;
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
/*     */   public void setTestDepth(int depth) {
/* 343 */     if (depth < 0) {
/* 344 */       this.testDepth = 0;
/*     */     } else {
/* 346 */       this.testDepth = depth;
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
/*     */   public void setProbeDepth(int depth) {
/* 363 */     if (depth < 0) {
/* 364 */       this.probeDepth = 0;
/*     */     } else {
/* 366 */       this.probeDepth = depth;
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
/*     */   public void setTreeDepth(int depth) {
/* 384 */     if (depth < 0) {
/* 385 */       this.treeDepth = 0;
/*     */     } else {
/* 387 */       this.treeDepth = depth;
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
/*     */   public void setStrategy(Strategy strategy) {
/* 406 */     this.strategy = strategy;
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
/*     */   public void setModality(Modality mode) {
/* 420 */     this.modality = mode;
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
/*     */   public void setConvention(Convention convention) {
/* 435 */     this.convention = convention;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTestClassDirectory(File directory) {
/* 444 */     this.testClassDirectory = directory;
/* 445 */     this.loader = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGeneratorDirectory(File directory) {
/* 454 */     this.generatorDirectory = directory;
/* 455 */     this.loader = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOracleDirectory(File directory) {
/* 463 */     this.oracleDirectory = directory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTestClass(Class<?> testClass) {
/* 474 */     this.testClass = testClass;
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
/*     */   public void setTestClass(String name) throws LoaderException {
/* 493 */     File directory = getTestClassDirectory();
/* 494 */     final String clsname = name;
/*     */     try {
/*     */       try {
/* 497 */         URL[] urls = { directory.toURI().toURL() };
/* 498 */         ClassLoader parent = getClassLoader();
/* 499 */         URLClassLoader loader = new URLClassLoader(urls, parent)
/*     */           {
/*     */             public Class<?> loadClass(String name) throws ClassNotFoundException {
/* 502 */               if (!name.equals(clsname) || 
/* 503 */                 name.startsWith("java")) {
/* 504 */                 return super.loadClass(name);
/*     */               }
/* 506 */               return findClass(name);
/*     */             }
/*     */           };
/* 509 */         this.testClass = loader.loadClass(name);
/* 510 */         loader.close();
/*     */       }
/* 512 */       catch (IOException ex) {
/* 513 */         this.testClass = Class.forName(name);
/*     */       }
/*     */     
/* 516 */     } catch (ClassNotFoundException ex) {
/* 517 */       throw new LoaderException(name);
/*     */     }
/* 519 */     catch (NoClassDefFoundError ex) {
/* 520 */       throw new LoaderException(name, true);
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
/*     */   public void addCustomGenerator(Class<CustomGenerator> generator) {
/* 537 */     if (this.customGenerators == null)
/* 538 */       this.customGenerators = new ArrayList<>(); 
/* 539 */     this.customGenerators.add(generator);
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
/*     */   public void addCustomGenerator(String name) throws LoaderException {
/* 557 */     Class<CustomGenerator> generator = null;
/*     */     try {
/*     */       try {
/* 560 */         ClassLoader loader = getClassLoader();
/* 561 */         generator = 
/* 562 */           (Class)loader.loadClass(name);
/*     */       }
/* 564 */       catch (IOException ex) {
/* 565 */         generator = 
/* 566 */           (Class)Class.forName(name);
/*     */       } 
/* 568 */       addCustomGenerator(generator);
/*     */     }
/* 570 */     catch (ClassNotFoundException ex) {
/* 571 */       throw new LoaderException(name);
/*     */     }
/* 573 */     catch (NoClassDefFoundError ex) {
/* 574 */       throw new LoaderException(name, true);
/*     */     }
/* 576 */     catch (ClassCastException ex) {
/* 577 */       throw new LoaderException(name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCustomGenerator(Class<CustomGenerator> generator) {
/* 588 */     if (this.customGenerators != null) {
/* 589 */       this.customGenerators.remove(generator);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCustomGenerator(String name) {
/* 599 */     Class<CustomGenerator> toRemove = null;
/* 600 */     for (Class<CustomGenerator> generator : getCustomGenerators()) {
/* 601 */       if (name.equals(generator.getName())) {
/* 602 */         toRemove = generator; break;
/*     */       } 
/*     */     } 
/* 605 */     if (toRemove != null)
/* 606 */       this.customGenerators.remove(toRemove); 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */