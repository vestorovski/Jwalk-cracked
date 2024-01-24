/*     */ package org.jwalk.tool;
/*     */ 
/*     */ import org.jwalk.Convention;
/*     */ import org.jwalk.JWalker;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JWalkUtility
/*     */ {
/*     */   public static void main(String[] args) {
/*  48 */     StringBuilder splash = new StringBuilder();
/*  49 */     splash.append("\t**************************************************\n");
/*  50 */     splash.append("\t*                                                *\n");
/*  51 */     splash.append("\t*  JWalk - a lazy, systematic class unit tester  *\n");
/*  52 */     splash.append("\t*      v1.1 (c) 2011, Anthony J H Simons         *\n");
/*  53 */     splash.append("\t*                                                *\n");
/*  54 */     splash.append("\t*         Department of Computer Science         *\n");
/*  55 */     splash.append("\t*          University of Sheffield, UK.          *\n");
/*  56 */     splash.append("\t*                                                *\n");
/*  57 */     splash.append("\t**************************************************\n");
/*     */ 
/*     */ 
/*     */     
/*  61 */     StringBuilder usage = new StringBuilder();
/*  62 */     usage.append("JWalkUtility: command line usage is :-\n\n");
/*  63 */     usage.append("    java org.jwalk.tool.JWalkUtility <testClass>\n\t[<strategy> | <modality> | <testDepth> | <generator>\n\t    | <convention> | <probeDepth> | <stateDepth>]*\n\n");
/*     */ 
/*     */     
/*  66 */     usage.append("<testClass> ::= the name of the test class (mandatory)\n");
/*  67 */     usage.append("<strategy> ::= [protocol | algebra | states] (default = algebra)\n");
/*  68 */     usage.append("<modality> ::= [inspect | explore | validate] (default = explore)\n");
/*  69 */     usage.append("<convention> ::= [standard | custom | complete] (default = standard)\n");
/*  70 */     usage.append("<testDepth> ::= [t]0..n, length limit for test sequences (default = 3)\n");
/*  71 */     usage.append("<probeDepth> ::= p0..n, length limit for probe sequences (default = 12)\n");
/*  72 */     usage.append("<stateDepth> ::= s0..n, tree depth for state comparison (default = 0)\n");
/*  73 */     usage.append("<generator> ::= the name of a custom input generator (optional)\n");
/*     */ 
/*     */ 
/*     */     
/*  77 */     System.out.println(splash);
/*  78 */     if (args.length == 0)
/*  79 */     { System.out.println(usage); }
/*     */     else { try {
/*  81 */         JWalker walker = new JWalker();
/*  82 */         Settings settings = walker.getSettings();
/*  83 */         for (int i = 0; i < args.length; i++) {
/*  84 */           String parameter = args[i];
/*  85 */           if (i == 0) {
/*  86 */             settings.setTestClass(parameter);
/*  87 */           } else if (parameter.endsWith("Generator")) {
/*  88 */             settings.addCustomGenerator(parameter);
/*  89 */           } else if (Character.isDigit(parameter.charAt(0))) {
/*     */             try {
/*  91 */               int depth = Integer.parseInt(parameter);
/*  92 */               settings.setTestDepth(depth);
/*     */             }
/*  94 */             catch (NumberFormatException ex) {
/*  95 */               throw new SettingsException(parameter, true);
/*     */             }
/*     */           
/*  98 */           } else if (Character.isDigit(parameter.charAt(1))) {
/*     */             try {
/* 100 */               int depth = Integer.parseInt(parameter.substring(1));
/* 101 */               switch (parameter.charAt(0)) {
/*     */                 case 'p':
/* 103 */                   settings.setProbeDepth(depth);
/*     */                   break;
/*     */                 case 's':
/* 106 */                   settings.setTreeDepth(depth);
/*     */                   break;
/*     */                 case 't':
/* 109 */                   settings.setTestDepth(depth);
/*     */                   break;
/*     */                 default:
/* 112 */                   throw new SettingsException(parameter, true);
/*     */               } 
/*     */             
/* 115 */             } catch (NumberFormatException ex) {
/* 116 */               throw new SettingsException(parameter, true);
/*     */             }
/*     */           
/* 119 */           } else if (parameter.equals("protocol")) {
/* 120 */             settings.setStrategy(Strategy.PROTOCOL);
/* 121 */           } else if (parameter.equals("algebra")) {
/* 122 */             settings.setStrategy(Strategy.ALGEBRA);
/* 123 */           } else if (parameter.equals("states")) {
/* 124 */             settings.setStrategy(Strategy.STATES);
/* 125 */           } else if (parameter.equals("inspect")) {
/* 126 */             settings.setModality(Modality.INSPECT);
/* 127 */           } else if (parameter.equals("explore")) {
/* 128 */             settings.setModality(Modality.EXPLORE);
/* 129 */           } else if (parameter.equals("validate")) {
/* 130 */             settings.setModality(Modality.VALIDATE);
/* 131 */           } else if (parameter.equals("standard")) {
/* 132 */             settings.setConvention(Convention.STANDARD);
/* 133 */           } else if (parameter.equals("custom")) {
/* 134 */             settings.setConvention(Convention.CUSTOM);
/* 135 */           } else if (parameter.equals("complete")) {
/* 136 */             settings.setConvention(Convention.COMPLETE);
/*     */           } else {
/* 138 */             throw new SettingsException(parameter);
/*     */           } 
/* 140 */         }  walker.execute();
/*     */       }
/* 142 */       catch (Exception ex) {
/* 143 */         System.err.println("JWalk terminated for the following reason:\n");
/* 144 */         System.err.println(ex);
/* 145 */         Throwable cause = ex.getCause();
/* 146 */         while (cause != null) {
/* 147 */           System.err.println("Cause: " + cause);
/* 148 */           cause = cause.getCause();
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/tool/JWalkUtility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */