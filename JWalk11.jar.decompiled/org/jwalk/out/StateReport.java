/*     */ package org.jwalk.out;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.core.ClassInspector;
/*     */ import org.jwalk.core.StateSpaceWalker;
/*     */ import org.jwalk.core.TestSequence;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateReport
/*     */   extends ProtocolReport
/*     */ {
/*     */   public StateReport(StateSpaceWalker walker) {
/*  35 */     super(Edition.STATE_REPORT, (ClassInspector)walker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateSpaceWalker getJWalker() {
/*  44 */     return (StateSpaceWalker)this.walker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, TestSequence> getStateCover() {
/*  54 */     return getJWalker().getStateCover();
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
/*     */   public List<String> getStateNames() {
/*  68 */     return new ArrayList<>(getStateCover().keySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Method> getStatePredicates() {
/*  76 */     return getJWalker().getStatePredicates();
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
/*     */   public int countMaxStates() {
/*  89 */     return getJWalker().countMaxStates();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMissingStates() {
/*  97 */     return getJWalker().hasMissingStates();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String toString(TestSequence sequence) {
/* 108 */     int linemax = 40;
/* 109 */     int count = 0;
/* 110 */     StringBuilder text = new StringBuilder();
/* 111 */     String pretty = sequence.getLongKey();
/* 112 */     for (int i = 0; i < pretty.length(); i++) {
/* 113 */       char ch = pretty.charAt(i);
/* 114 */       if (ch == '.' && count > linemax) {
/* 115 */         text.append("\n\t\t");
/* 116 */         count = 0;
/*     */       } 
/* 118 */       text.append(ch);
/* 119 */       count++;
/*     */     } 
/* 121 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String banner() {
/* 130 */     StateSpaceWalker inspector = getJWalker();
/* 131 */     Class<?> testClass = inspector.getTestClass();
/* 132 */     StringBuilder text = new StringBuilder();
/* 133 */     text.append("State space analysis for the ");
/* 134 */     if (inspector.classIsEnum()) {
/* 135 */       text.append("enumerated type: ");
/*     */     } else {
/* 137 */       text.append("class: ");
/* 138 */     }  text.append(toString(testClass)).append("\n\n");
/* 139 */     return text.toString();
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
/*     */   public String getContent() {
/* 153 */     Map<String, TestSequence> cover = getStateCover();
/* 154 */     StringBuilder text = new StringBuilder();
/* 155 */     text.append(banner());
/* 156 */     for (String stateKey : getStateNames()) {
/* 157 */       text.append("\t").append(stateKey).append(" : ");
/* 158 */       text.append(toString(cover.get(stateKey)));
/* 159 */       text.append('\n');
/*     */     } 
/* 161 */     if (hasMissingStates()) {
/* 162 */       text.append("\nWarning: there were some undetected states.\n");
/* 163 */     } else if (cover.size() < countMaxStates()) {
/* 164 */       text.append("\nWarning: state predicates are not independent.\n");
/* 165 */     } else if (cover.size() == 1) {
/* 166 */       text.append("\nNote: this class has only one high-level state.\n");
/* 167 */     }  if (this.walker.outOfMemory())
/* 168 */       text.append("\nWarning: searching exceeded memory capacity.\n"); 
/* 169 */     text.append("\n\n");
/* 170 */     return text.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/StateReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */