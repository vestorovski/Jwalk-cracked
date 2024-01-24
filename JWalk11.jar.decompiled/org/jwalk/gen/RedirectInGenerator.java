/*     */ package org.jwalk.gen;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Urgency;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedirectInGenerator
/*     */   implements CustomGenerator
/*     */ {
/*  39 */   private static String[] verses = new String[] { 
/*  40 */       "1 partridge in a pear tree", 
/*  41 */       "2 turtle doves", 
/*  42 */       "3 French hens", 
/*  43 */       "4 colly birds", 
/*  44 */       "5 gold rings", 
/*  45 */       "6 geese a-laying", 
/*  46 */       "7 swans a-swimming", 
/*  47 */       "8 maids a-milking", 
/*  48 */       "9 drummers drumming", 
/*  49 */       "10 pipers piping", 
/*  50 */       "11 ladies dancing", 
/*  51 */       "12 lords a-leaping", 
/*  52 */       "Oxford Dictionary of Nursery Rhymes" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private InputStream oldInput = System.in;
/*     */ 
/*     */ 
/*     */   
/*     */   private MasterGenerator owner;
/*     */ 
/*     */ 
/*     */   
/*     */   private File inputFile;
/*     */ 
/*     */ 
/*     */   
/*     */   private void redirectInput() {
/*  96 */     JWalker walker = this.owner.getJWalker();
/*  97 */     Settings settings = walker.getSettings();
/*  98 */     this.inputFile = new File(settings.getTestClassDirectory(), "input.txt");
/*     */     try {
/* 100 */       if (!this.inputFile.exists()) {
/* 101 */         PrintStream output = new PrintStream(
/* 102 */             new FileOutputStream(this.inputFile)); byte b; int i; String[] arrayOfString;
/* 103 */         for (i = (arrayOfString = verses).length, b = 0; b < i; ) { String line = arrayOfString[b];
/* 104 */           output.println(line); b++; }
/* 105 */          output.close();
/* 106 */         Channels channels = walker.getChannels();
/* 107 */         String message = "The input file `input.txt' was created in: \n" + 
/* 108 */           settings.getTestClassDirectory();
/* 109 */         channels.dispatch((Question)new Notification(walker, message, Urgency.NOTICE));
/*     */       } 
/* 111 */       System.setIn(new FileInputStream(this.inputFile));
/*     */     }
/* 113 */     catch (FileNotFoundException ex) {
/* 114 */       System.setIn(this.oldInput);
/* 115 */       System.err.println(ex);
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
/*     */   protected void finalize() {
/* 127 */     System.setIn(this.oldInput);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(MasterGenerator generator) {
/* 138 */     this.owner = generator;
/* 139 */     redirectInput();
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
/*     */   public boolean canCreate(Class<?> type) {
/* 151 */     return false;
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
/*     */   public Object nextValue(Class<?> type) throws GeneratorException {
/* 164 */     throw new GeneratorException(type);
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/RedirectInGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */