/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.out.Answer;
/*     */ import org.jwalk.out.Confirmation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Oracle
/*     */ {
/*     */   protected JWalker walker;
/*     */   protected Map<String, String> correct;
/*     */   protected Map<String, String> incorrect;
/*     */   
/*     */   public Oracle(JWalker walker) {
/*  67 */     this.walker = walker;
/*  68 */     this.correct = new LinkedHashMap<>();
/*  69 */     this.incorrect = new LinkedHashMap<>();
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
/*     */   public boolean open() {
/*  83 */     Settings settings = this.walker.getSettings();
/*  84 */     Class<?> testClass = settings.getTestClass();
/*  85 */     File file = new File(settings.getOracleDirectory(), 
/*  86 */         String.valueOf(testClass.getSimpleName()) + ".jwk");
/*  87 */     if (!file.exists() && testClass != Object.class) {
/*  88 */       testClass = testClass.getSuperclass();
/*  89 */       file = new File(settings.getOracleDirectory(), 
/*  90 */           String.valueOf(testClass.getSimpleName()) + ".jwk");
/*     */     } 
/*  92 */     if (!file.exists()) {
/*  93 */       return false;
/*     */     }
/*     */     try {
/*  96 */       BufferedReader reader = new BufferedReader(
/*  97 */           new FileReader(file));
/*  98 */       boolean mark = false;
/*  99 */       while (reader.ready()) {
/* 100 */         String data = reader.readLine();
/* 101 */         if (data.length() == 0) {
/* 102 */           mark = true; continue;
/*     */         } 
/* 104 */         String[] pair = data.split("=");
/* 105 */         if (mark) {
/* 106 */           this.incorrect.put(pair[0], pair[1]); continue;
/*     */         } 
/* 108 */         this.correct.put(pair[0], pair[1]);
/*     */       } 
/*     */       
/* 111 */       reader.close();
/*     */     }
/* 113 */     catch (IOException ex) {
/* 114 */       ex.printStackTrace();
/*     */     } 
/* 116 */     return !(this.correct.isEmpty() && this.incorrect.isEmpty());
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
/*     */   public boolean close() {
/* 132 */     Channels channels = this.walker.getChannels();
/* 133 */     Settings settings = this.walker.getSettings();
/* 134 */     String oracleName = String.valueOf(settings.getTestClass().getSimpleName()) + ".jwk";
/* 135 */     File file = new File(settings.getOracleDirectory(), oracleName);
/* 136 */     boolean writtenOK = false;
/*     */     try {
/* 138 */       PrintWriter writer = new PrintWriter(
/* 139 */           new FileWriter(file));
/* 140 */       for (String key : this.correct.keySet()) {
/* 141 */         writer.print(key);
/* 142 */         writer.print("=");
/* 143 */         writer.println(this.correct.get(key));
/*     */       } 
/* 145 */       writer.println();
/* 146 */       for (String key : this.incorrect.keySet()) {
/* 147 */         writer.print(key);
/* 148 */         writer.print("=");
/* 149 */         writer.println(this.incorrect.get(key));
/*     */       } 
/* 151 */       writtenOK = true;
/* 152 */       writer.close();
/* 153 */     } catch (IOException ex) {
/* 154 */       ex.printStackTrace();
/*     */     } 
/* 156 */     if (writtenOK) {
/* 157 */       String message = "The oracle file `" + oracleName + 
/* 158 */         "' was written to:\n" + settings.getOracleDirectory();
/* 159 */       channels.dispatch((Question)new Notification(this.walker, message, Urgency.NOTICE));
/*     */     } else {
/*     */       
/* 162 */       String message = "The oracle file `" + oracleName + 
/* 163 */         "' cannot be written to:\n" + settings.getOracleDirectory();
/* 164 */       channels.dispatch((Question)new Notification(this.walker, message, Urgency.WARNING));
/*     */     } 
/* 166 */     return writtenOK;
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
/*     */   public Outcome validate(TestSequence sequence) {
/* 178 */     Outcome outcome = null;
/* 179 */     if (!sequence.hasExecuted())
/*     */     
/* 181 */     { outcome = Outcome.UNKNOWN; }
/*     */     
/* 183 */     else if (sequence.getReturnType() == void.class && 
/* 184 */       sequence.getResult() == void.class)
/*     */     
/* 186 */     { outcome = Outcome.CORRECT;
/*     */        }
/*     */     
/*     */     else
/*     */     
/* 191 */     { String result = sequence.oracleResult();
/* 192 */       String longKey = sequence.getLongKey();
/* 193 */       String pass = this.correct.get(longKey);
/* 194 */       String fail = this.incorrect.get(longKey);
/* 195 */       if (pass == null && fail == null) {
/*     */ 
/*     */         
/* 198 */         String shortKey = sequence.getShortKey();
/* 199 */         pass = this.correct.get(shortKey);
/* 200 */         fail = this.incorrect.get(shortKey);
/*     */       } 
/*     */       
/* 203 */       if (pass != null && result.equals(pass))
/* 204 */       { outcome = Outcome.CORRECT; }
/* 205 */       else if (fail != null && result.equals(fail))
/* 206 */       { outcome = Outcome.INCORRECT; }
/*     */       
/*     */       else
/*     */       
/* 210 */       { Channels channels = this.walker.getChannels();
/* 211 */         switch (channels.dispatch((Question)new Confirmation(this.walker, sequence)))
/*     */         {
                    case YES:
/* 213 */             outcome = Outcome.CONFIRMED;
/* 214 */             this.correct.put(longKey, result);
/* 228 */             sequence.setOutcome(outcome);
/* 229 */             return outcome;
                    case null:
                        outcome = Outcome.REJECTED;
                        this.incorrect.put(longKey, result);
                        sequence.setOutcome(outcome);
                        return outcome;
                        case QUIT:
                            outcome = Outcome.UNKNOWN;
                            sequence.setOutcome(outcome);
                            return outcome;
                default:
                    break;
}
outcome = Outcome.UNKNOWN;
}  }
    sequence.setOutcome(outcome);
    return outcome;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/Oracle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */