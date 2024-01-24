/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.PermissionException;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.out.AlgebraReport;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Report;
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
/*     */ public class AlgebraWalker
/*     */   extends ProtocolWalker
/*     */ {
/*     */   protected Map<Member, Category> category;
/*     */   
/*     */   public AlgebraWalker(Settings settings, Channels channels) {
/*  52 */     super(settings, channels);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Category getCategory(Enum<?> constant) {
/*  62 */     return Category.CONSTRUCTOR;
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
/*     */   public Category getCategory(Member member) {
/*  82 */     if (member instanceof java.lang.reflect.Field) {
/*  83 */       return Category.CONSTRUCTOR;
/*     */     }
/*  85 */     return this.category.get(member);
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
/*     */   private void updateMethodCategory(TestSequence sequence) {
/*  99 */     Method method = (Method)sequence.getOperation();
/* 100 */     if (sequence.isReentrant() && hasPrimitivePrefix(sequence)) {
/* 101 */       this.category.put(method, Category.TRANSFORMER);
/* 102 */     } else if (this.category.get(method) != Category.TRANSFORMER) {
/* 103 */       this.category.put(method, Category.CONSTRUCTOR);
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
/*     */   private boolean hasPrimitivePrefix(TestSequence sequence) {
/* 119 */     List<TestCase> testCases = sequence.getSequence();
/* 120 */     int last = testCases.size() - 1;
/* 121 */     for (int index = 1; index < last; index++) {
/* 122 */       Member operation = ((TestCase)testCases.get(index)).getOperation();
/* 123 */       if (getCategory(operation) != Category.CONSTRUCTOR)
/* 124 */         return false; 
/*     */     } 
/* 126 */     return true;
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
/*     */   private void findDerivedConstructors() {
/* 140 */     for (Constructor<?> big : getConstructors()) {
/* 141 */       for (Constructor<?> small : getConstructors()) {
/* 142 */         if (signatureSubsumes(big, small)) {
/* 143 */           this.category.put(small, Category.TRANSFORMER);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean signatureSubsumes(Constructor<?> one, Constructor<?> two) {
/* 155 */     Class[] bigSig = one.getParameterTypes();
/* 156 */     Class[] smallSig = two.getParameterTypes();
/* 157 */     if (bigSig.length <= smallSig.length)
/* 158 */       return false; 
/* 159 */     for (int i = 0; i < smallSig.length; i++) {
/* 160 */       if (bigSig[i] != smallSig[i])
/* 161 */         return false; 
/* 162 */     }  return true;
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
/*     */   protected boolean isPrunable(TestSequence sequence) {
/* 178 */     return !(!sequence.hasTerminated() && !sequence.isReentrant());
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
/*     */   protected void executeProbeCycle(List<TestSequence> testSet, int cycle) throws PermissionException, GeneratorException, ExecutionException {
/* 203 */     for (TestSequence sequence : testSet) {
/* 204 */       sequence.execute(makeGenerator());
/* 205 */       if (cycle == 0)
/*     */         continue; 
/* 207 */       if (sequence.isUnchanged()) {
/*     */         continue;
/*     */       }
/* 210 */       updateMethodCategory(sequence);
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
/*     */ 
/*     */   
/*     */   protected void discoverAlgebra() throws PermissionException, GeneratorException, ExecutionException {
/* 240 */     ensureExecutable();
/* 241 */     this.category = new LinkedHashMap<>();
/* 242 */     for (Constructor<?> constructor : getConstructors())
/* 243 */       this.category.put(constructor, Category.CONSTRUCTOR); 
/* 244 */     for (Method method : getMethods())
/* 245 */       this.category.put(method, Category.OBSERVER); 
/* 246 */     this.channels.setNominal();
/* 247 */     int depth = this.settings.getProbeDepth();
/* 248 */     int cycle = 0;
/*     */     try {
/* 250 */       findDerivedConstructors();
/* 251 */       List<TestSequence> testSet = firstCycle();
/* 252 */       for (int i = 0; this.channels.nominal() && i <= depth; i++) {
/* 253 */         cycle = i;
/* 254 */         if (cycle > 0)
/* 255 */           testSet = nextCycle(testSet); 
/* 256 */         executeProbeCycle(testSet, cycle);
/*     */       } 
/* 258 */     } catch (OutOfMemoryError ex) {
/* 259 */       System.gc();
/* 260 */       this.channels.setOutOfMemory();
/*     */     } 
/* 262 */     this.channels.dispatch((Report)new AlgebraReport(this));
/* 263 */     if (this.channels.outOfMemory()) {
/* 264 */       this.channels.dispatch((Question)new Notification(this, 
/* 265 */             "Ran out of memory in probe cycle: " + cycle + 
/* 266 */             ". \nSuggest that you decrease the probe depth" + 
/* 267 */             "\nwhen analysing the algebra of this class.", 
/* 268 */             Urgency.WARNING));
/*     */     }
/* 270 */     else if (this.channels.userAborted()) {
/* 271 */       this.channels.dispatch((Question)new Notification(this, 
/* 272 */             "Probing interrupted in cycle: " + cycle + 
/* 273 */             "; \nthe test series was aborted and" + 
/* 274 */             "\ntest statistics are incomplete.", 
/* 275 */             Urgency.WARNING));
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
/*     */   public void execute() throws PermissionException, GeneratorException, ExecutionException {
/* 299 */     inspectProtocols();
/* 300 */     discoverAlgebra();
/* 301 */     if (!this.channels.userAborted() && 
/* 302 */       this.settings.getModality() != Modality.INSPECT)
/* 303 */       executeTestSeries(); 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/AlgebraWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */