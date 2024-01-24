/*     */ package org.jwalk.out;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.core.AlgebraWalker;
/*     */ import org.jwalk.core.Category;
/*     */ import org.jwalk.core.ClassInspector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlgebraReport
/*     */   extends ProtocolReport
/*     */ {
/*     */   public AlgebraReport(AlgebraWalker walker) {
/*  31 */     super(Edition.ALGEBRA_REPORT, (ClassInspector)walker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AlgebraWalker getJWalker() {
/*  40 */     return (AlgebraWalker)this.walker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPrimitive(Enum<?> value) {
/*  49 */     return true;
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
/*     */   public boolean isPrimitive(Member member) {
/*  62 */     return (getJWalker().getCategory(member) == Category.CONSTRUCTOR);
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
/*     */   public boolean isTransformer(Member member) {
/*  76 */     return (getJWalker().getCategory(member) == Category.TRANSFORMER);
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
/*     */   public boolean isObserver(Member member) {
/*  89 */     return (getJWalker().getCategory(member) == Category.OBSERVER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String banner() {
/*  98 */     AlgebraWalker inspector = getJWalker();
/*  99 */     Class<?> testClass = inspector.getTestClass();
/* 100 */     StringBuilder text = new StringBuilder();
/* 101 */     text.append("Algebraic analysis for the ");
/* 102 */     if (inspector.classIsEnum()) {
/* 103 */       text.append("enumerated type: ");
/*     */     } else {
/* 105 */       text.append("class: ");
/* 106 */     }  text.append(toString(testClass)).append("\n\n");
/* 107 */     return text.toString();
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
/*     */   public String getContent() {
/* 120 */     StringBuilder text = new StringBuilder();
/* 121 */     text.append(banner());
/* 122 */     text.append("Primitive constructions:\n");
/* 123 */     for (Enum<?> constant : getConstants())
/* 124 */       text.append('\t').append(constant.name()).append('\n'); 
/* 125 */     for (Constructor<?> constructor : getConstructors()) {
/* 126 */       if (isPrimitive(constructor))
/* 127 */         text.append('\t').append(toString(constructor)).append('\n'); 
/* 128 */     }  for (Method method : getMethods()) {
/* 129 */       if (isPrimitive(method))
/* 130 */         text.append('\t').append(toString(method)).append('\n'); 
/* 131 */     }  text.append("Transformer operations:\n");
/* 132 */     for (Constructor<?> constructor : getConstructors()) {
/* 133 */       if (isTransformer(constructor))
/* 134 */         text.append('\t').append(toString(constructor)).append('\n'); 
/* 135 */     }  for (Method method : getMethods()) {
/* 136 */       if (isTransformer(method))
/* 137 */         text.append('\t').append(toString(method)).append('\n'); 
/* 138 */     }  text.append("Observer operations:\n");
/* 139 */     for (Method method : getMethods()) {
/* 140 */       if (isObserver(method))
/* 141 */         text.append('\t').append(toString(method)).append('\n'); 
/* 142 */     }  if (this.walker.outOfMemory())
/* 143 */       text.append("\nWarning: searching exceeded memory capacity.\n"); 
/* 144 */     text.append("\n\n");
/* 145 */     return text.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/AlgebraReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */