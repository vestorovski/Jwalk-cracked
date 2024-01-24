/*     */ package org.jwalk.out;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.List;
/*     */ import org.jwalk.JWalker;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProtocolReport
/*     */   extends Report
/*     */ {
/*     */   public ProtocolReport(ClassInspector walker) {
/*  35 */     super(Edition.PROTOCOL_REPORT, (JWalker)walker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProtocolReport(Edition edition, ClassInspector walker) {
/*  45 */     super(edition, (JWalker)walker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInspector getJWalker() {
/*  54 */     return (ClassInspector)this.walker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Enum<?>> getConstants() {
/*  62 */     return getJWalker().getConstants();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Constructor<?>> getConstructors() {
/*  72 */     return getJWalker().getConstructors();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Method> getMethods() {
/*  82 */     return getJWalker().getMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getTestClass() {
/*  92 */     return getJWalker().getTestClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String banner() {
/* 101 */     ClassInspector inspector = getJWalker();
/* 102 */     Class<?> testClass = inspector.getTestClass();
/* 103 */     StringBuilder text = new StringBuilder();
/* 104 */     text.append("Protocol analysis of the ");
/* 105 */     if (inspector.classIsInterface()) {
/* 106 */       text.append("interface: ");
/* 107 */     } else if (inspector.classIsAbstract()) {
/* 108 */       text.append("abstract class: ");
/* 109 */     } else if (inspector.classNotPublic()) {
/* 110 */       text.append("non-public class: ");
/* 111 */     } else if (inspector.classIsEnum()) {
/* 112 */       text.append("enumerated type: ");
/*     */     } else {
/* 114 */       text.append("class: ");
/* 115 */     }  text.append(toString(testClass)).append("\n\n");
/* 116 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContent() {
/* 127 */     ClassInspector inspector = getJWalker();
/* 128 */     StringBuilder text = new StringBuilder();
/* 129 */     text.append(banner());
/* 130 */     if (inspector.countConstants() > 0) {
/* 131 */       text.append("Public constants:\n");
/* 132 */       for (Enum<?> constant : getConstants())
/* 133 */         text.append('\t').append(constant.name()).append('\n'); 
/*     */     } 
/* 135 */     if (inspector.countConstructors() > 0) {
/* 136 */       text.append("Public constructors:\n");
/* 137 */       for (Constructor<?> constructor : getConstructors())
/* 138 */         text.append('\t').append(toString(constructor)).append('\n'); 
/*     */     } 
/* 140 */     if (inspector.countMethods() > 0) {
/* 141 */       text.append("Public methods:\n");
/* 142 */       for (Method method : getMethods())
/* 143 */         text.append('\t').append(toString(method)).append('\n'); 
/*     */     } 
/* 145 */     text.append("\n\n");
/* 146 */     return text.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAbstract(Method method) {
/* 155 */     return Modifier.isAbstract(method.getModifiers());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Class<?> aClass) {
/* 165 */     return aClass.getSimpleName();
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
/*     */   public String toString(Constructor<?> constructor) {
/* 180 */     StringBuilder text = new StringBuilder();
/* 181 */     text.append(toString(constructor.getDeclaringClass()));
/* 182 */     Class[] paramTypes = constructor.getParameterTypes();
/* 183 */     text.append('(');
/* 184 */     for (int i = 0; i < paramTypes.length; i++) {
/* 185 */       if (i > 0) text.append(", "); 
/* 186 */       text.append(toString(paramTypes[i]));
/*     */     } 
/* 188 */     text.append(");");
/* 189 */     return text.toString();
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
/*     */   public String toString(Method method) {
/* 205 */     StringBuilder text = new StringBuilder();
/* 206 */     if (isAbstract(method))
/* 207 */       text.append("abstract "); 
/* 208 */     text.append(toString(method.getReturnType())).append(' ');
/* 209 */     text.append(method.getName());
/* 210 */     Class[] paramTypes = method.getParameterTypes();
/* 211 */     text.append('(');
/* 212 */     for (int i = 0; i < paramTypes.length; i++) {
/* 213 */       if (i > 0) text.append(", "); 
/* 214 */       text.append(toString(paramTypes[i]));
/*     */     } 
/* 216 */     text.append(");");
/* 217 */     return text.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/out/ProtocolReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */