/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ 
/*     */ 
/*     */ public final class LicenseManager
/*     */ {
/*     */   public LicenseManager() {
                // lol
///*     */     try {
///*  29 */       String[] license = new String[15];
///*  30 */       openLicense(license);
///*  31 */       MessageDigest md1 = MessageDigest.getInstance("SHA");
///*  32 */       for (int i = 0; i < 14; i++)
///*  33 */         md1.update(license[i].getBytes());
///*  34 */       MessageDigest md2 = (MessageDigest)md1.clone();
///*  35 */       if (makeLicense(md1.digest()).equals(license[14])) {
///*     */
///*  37 */         md2.update(System.getProperty("user.name").getBytes());
///*  38 */         md2.update(System.getProperty("user.home").getBytes());
///*  39 */         license[14] = makeLicense(md2.digest());
///*  40 */         saveLicense(license);
///*     */       }
///*     */       else {
///*     */
///*  44 */         md2.update(System.getProperty("user.name").getBytes());
///*  45 */         md2.update(System.getProperty("user.home").getBytes());
///*  46 */         if (!makeLicense(md2.digest()).equals(license[14])) {
///*  47 */           System.err.println(
///*  48 */               "Detected an invalid JWalk license; terminating");
///*  49 */           System.exit(1);
///*     */         }
///*     */
///*     */       }
///*  53 */     } catch (NoSuchAlgorithmException ex) {
///*  54 */       System.err.println("Unable to validate JWalk license; terminating");
///*  55 */       System.exit(1);
///*     */     }
///*  57 */     catch (CloneNotSupportedException ex) {
///*  58 */       System.err.println("Unable to validate JWalk license; terminating");
///*  59 */       System.exit(1);
///*     */     }
///*  61 */     catch (SecurityException ex) {
///*  62 */       System.err.println("Unable to validate JWalk license; terminating");
///*  63 */       System.exit(1);
///*     */     }
/*     */   }
/*     */   
/*     */   public final void validateLicense() {}
/*     */   
/*     */   private final void openLicense(String[] license) {
    /*     */     try {
        /*  89 */       BufferedReader reader = new BufferedReader(
                /*  90 */           new FileReader("JWalkLicense.txt"));
        /*  91 */       for (int i = 0; i < 15; i++)
            /*  92 */         license[i] = reader.readLine();
        /*  93 */       reader.close();
        /*     */     }
    /*  95 */     catch (FileNotFoundException ex) {
        /*  96 */       System.err.println("Cannot find a valid JWalk license; terminating");
        /*  97 */       System.exit(1);
        /*     */     }
    /*  99 */     catch (IOException ex) {
        /* 100 */       System.err.println("Illegally modified JWalk license; terminating");
        /* 101 */       System.exit(1);
        /*     */     }
    /*     */   }
/*     */   
/*     */   private final void saveLicense(String[] license) {
/*     */     try {
/* 114 */       PrintWriter writer = new PrintWriter(
/* 115 */           new FileWriter("JWalkLicense.txt"));
/* 116 */       for (int i = 0; i < 15; i++)
/* 117 */         writer.println(license[i]); 
/* 118 */       writer.close();
/*     */     }
/* 120 */     catch (IOException ex) {
/* 121 */       System.err.println("Cannot update the JWalk license; terminating");
/* 122 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String makeLicense(byte[] digest) {
/* 132 */     int offset = digest.length / 2;
/* 133 */     StringBuilder buffer = new StringBuilder("License code:  ");
/* 134 */     for (int i = 0; i < offset; i++) {
/* 135 */       int j = digest[i] + digest[i + offset];
/* 136 */       buffer.append(j);
/*     */     } 
/* 138 */     return buffer.toString();
/*     */   }
/*     */ }
