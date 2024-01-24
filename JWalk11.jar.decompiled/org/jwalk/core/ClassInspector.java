/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.Convention;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.JWalker;
/*     */ import org.jwalk.PermissionException;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.out.ProtocolReport;
/*     */ import org.jwalk.out.Report;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassInspector
/*     */   extends JWalker
/*     */ {
/*  40 */   protected Class<?> testClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected List<Enum<?>> constants = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected List<Constructor<?>> constructors = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected List<Method> methods = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInspector(Settings settings, Channels channels) {
/*  72 */     super(settings, channels);
/*  73 */     this.testClass = settings.getTestClass();
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
/*     */   public Class<?> getTestClass() {
/*  85 */     return this.testClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classIsInterface() {
/*  93 */     return Modifier.isInterface(this.testClass.getModifiers());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classIsAbstract() {
/* 101 */     return Modifier.isAbstract(this.testClass.getModifiers());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classIsEnum() {
/* 109 */     return this.testClass.isEnum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classNotPublic() {
/* 117 */     return !Modifier.isPublic(this.testClass.getModifiers());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Enum<?>> getConstants() {
/* 126 */     return (this.constants == null) ? Collections.<Enum<?>>emptyList() : this.constants;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Constructor<?>> getConstructors() {
/* 135 */     return (this.constructors == null) ? 
/* 136 */       Collections.<Constructor<?>>emptyList() : this.constructors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Method> getMethods() {
/* 145 */     return (this.methods == null) ? 
/* 146 */       Collections.<Method>emptyList() : this.methods;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countConstants() {
/* 156 */     return (this.constants == null) ? 0 : this.constants.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countConstructors() {
/* 166 */     return (this.constructors == null) ? 0 : this.constructors.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countMethods() {
/* 176 */     return (this.methods == null) ? 0 : this.methods.size();
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
/*     */   public long countPermutations() {
/* 188 */     long permutations = 0L;
/* 189 */     long activeEdges = (classIsEnum() ? 
/* 190 */       countConstants() : countConstructors());
/* 191 */     for (int i = 0; i < this.settings.getTestDepth(); i++) {
/* 192 */       permutations += activeEdges;
/* 193 */       activeEdges *= countMethods();
/*     */     } 
/* 195 */     permutations += activeEdges;
/* 196 */     return permutations;
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
/*     */   public int countActiveEdges(int cycle) {
/* 210 */     if (cycle < 0)
/* 211 */       return 0; 
/* 212 */     int activeEdges = classIsEnum() ? 
/* 213 */       countConstants() : countConstructors();
/* 214 */     for (int i = 0; i < cycle; i++)
/* 215 */       activeEdges *= countMethods(); 
/* 216 */     return activeEdges;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void inspectConstants() {
/* 225 */     if (classIsEnum()) {
/* 226 */       Object[] array = this.testClass.getEnumConstants();
/* 227 */       this.constants = new ArrayList<>(array.length); byte b; int i; Object[] arrayOfObject1;
/* 228 */       for (i = (arrayOfObject1 = array).length, b = 0; b < i; ) { Object constant = arrayOfObject1[b];
/* 229 */         this.constants.add((Enum)constant);
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void inspectConstructors() throws PermissionException {
/*     */     try {
/* 240 */       Constructor[] array = (Constructor[])this.testClass.getConstructors();
/* 241 */       this.constructors = new ArrayList<>(array.length); byte b; int i; Constructor[] arrayOfConstructor1;
/* 242 */       for (i = (arrayOfConstructor1 = array).length, b = 0; b < i; ) { Constructor<?> constructor = arrayOfConstructor1[b];
/* 243 */         this.constructors.add(constructor); b++; }
/*     */     
/* 245 */     } catch (SecurityException securityBreach) {
/* 246 */       PermissionException ex = new PermissionException(this.testClass, 
/* 247 */           "Permission denied to use the constructors of: ");
/* 248 */       ex.initCause(securityBreach);
/* 249 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void inspectMethods() throws PermissionException {
/* 259 */     Convention convention = (this.testClass == Object.class) ? 
/* 260 */       Convention.COMPLETE : this.settings.getConvention();
/* 261 */     int adjust = 0;
/* 262 */     if (!classIsInterface())
/* 263 */       switch (convention) { case STANDARD:
/* 264 */           adjust += 4;
/* 265 */         case CUSTOM: adjust += 5;
/*     */           break; }
/*     */        
/*     */     try {
/* 269 */       String selectedMethods = "equalshashCodegetClasstoString";
/* 270 */       Method[] array = this.testClass.getMethods();
/* 271 */       this.methods = new ArrayList<>(array.length - adjust); byte b; int i; Method[] arrayOfMethod1;
/* 272 */       for (i = (arrayOfMethod1 = array).length, b = 0; b < i; ) { Method method = arrayOfMethod1[b];
/* 273 */         boolean accept = !(convention != Convention.COMPLETE && 
/* 274 */           method.getDeclaringClass() == Object.class && (
/* 275 */           convention != Convention.CUSTOM || 
/* 276 */           !selectedMethods.contains(method.getName())));
/* 277 */         if (accept)
/* 278 */           this.methods.add(method); 
/*     */         b++; }
/*     */     
/* 281 */     } catch (SecurityException securityBreach) {
/* 282 */       PermissionException ex = new PermissionException(this.testClass, 
/* 283 */           "Permission denied to use the methods of: ");
/* 284 */       ex.initCause(securityBreach);
/* 285 */       throw ex;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inspectProtocols() throws PermissionException {
/* 313 */     inspectConstants();
/* 314 */     inspectConstructors();
/* 315 */     inspectMethods();
/* 316 */     this.channels.dispatch((Report)new ProtocolReport(this));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() throws PermissionException, GeneratorException, ExecutionException {
/* 338 */     inspectProtocols();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ClassInspector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */