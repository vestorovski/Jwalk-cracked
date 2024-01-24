/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.PermissionException;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.gen.CustomGenerator;
/*     */ import org.jwalk.out.CycleReport;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Report;
/*     */ import org.jwalk.out.SummaryReport;
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
/*     */ public class ProtocolWalker
/*     */   extends ClassInspector
/*     */ {
/*  38 */   protected Oracle oracle = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected SummaryReport summary = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProtocolWalker(Settings settings, Channels channels) {
/*  53 */     super(settings, channels);
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
/*     */   protected boolean isPrunable(TestSequence sequence) {
/*  68 */     return sequence.hasTerminated();
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
/*     */   protected ObjectGenerator makeGenerator() throws PermissionException {
/*  84 */     ObjectGenerator generator = new ObjectGenerator(this);
/*  85 */     for (Class<CustomGenerator> type : (Iterable<Class<CustomGenerator>>)this.settings.getCustomGenerators()) {
/*     */       try {
/*  87 */         generator.addDelegate(type.newInstance());
/*     */       }
/*  89 */       catch (InstantiationException ex) {
/*  90 */         PermissionException gen = new PermissionException(type, true);
/*  91 */         gen.initCause(ex);
/*  92 */         throw gen;
/*     */       }
/*  94 */       catch (IllegalAccessException ex) {
/*  95 */         PermissionException gen = new PermissionException(type, true);
/*  96 */         gen.initCause(ex);
/*  97 */         throw gen;
/*     */       } 
/*     */     } 
/* 100 */     return generator;
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
/*     */   protected void ensureExecutable() throws PermissionException {
/* 113 */     if (classIsInterface())
/* 114 */       throw new PermissionException(this.testClass, 
/* 115 */           "Permission denied to execute the interface: "); 
/* 116 */     if (classIsAbstract())
/* 117 */       throw new PermissionException(this.testClass, 
/* 118 */           "Permission denied to execute the abstract class: "); 
/* 119 */     if (classNotPublic()) {
/* 120 */       throw new PermissionException(this.testClass, 
/* 121 */           "Permission denied to execute the non-public class: ");
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
/*     */   protected List<TestSequence> firstCycle() {
/* 133 */     List<TestSequence> newCycle = new LinkedList<>();
/* 134 */     if (this.testClass.isEnum()) {
/* 135 */       for (Enum<?> value : getConstants()) {
/* 136 */         TestSequence sequence = new TestSequence();
/* 137 */         sequence.add(new EnumTestCase(value));
/* 138 */         newCycle.add(sequence);
/*     */       } 
/*     */     } else {
/*     */       
/* 142 */       for (Constructor<?> constructor : getConstructors()) {
/* 143 */         TestSequence sequence = new TestSequence();
/* 144 */         sequence.add(new CreateTestCase(constructor));
/* 145 */         newCycle.add(sequence);
/*     */       } 
/* 147 */     }  return newCycle;
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
/*     */   protected List<TestSequence> nextCycle(List<TestSequence> oldCycle) {
/* 159 */     List<TestSequence> newCycle = new LinkedList<>();
/* 160 */     for (TestSequence prefix : oldCycle) {
/* 161 */       if (!isPrunable(prefix))
/* 162 */         for (Method method : getMethods()) {
/* 163 */           if (this.channels.interrupted())
/* 164 */             return newCycle; 
/* 165 */           TestSequence sequence = new TestSequence(prefix);
/* 166 */           sequence.add(new InvokeTestCase(method));
/* 167 */           newCycle.add(sequence);
/*     */         }  
/*     */     } 
/* 170 */     return newCycle;
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
/*     */   protected void executeTestCycle(List<TestSequence> testSet, int cycle) throws PermissionException, GeneratorException, ExecutionException {
/* 189 */     for (TestSequence sequence : testSet) {
/* 190 */       if (this.channels.interrupted())
/*     */         break; 
/* 192 */       sequence.execute(makeGenerator());
/* 193 */       if (this.oracle != null) {
/* 194 */         Outcome outcome = this.oracle.validate(sequence);
/*     */ 
/*     */         
/* 197 */         if (outcome == Outcome.UNKNOWN) {
/* 198 */           this.channels.setUserAborted();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void executeTestSeries() throws PermissionException, GeneratorException, ExecutionException {
/* 223 */     ensureExecutable();
/* 224 */     this.summary = new SummaryReport(this);
/* 225 */     if (this.settings.getModality() == Modality.VALIDATE) {
/* 226 */       this.oracle = new Oracle(this);
/* 227 */       this.oracle.open();
/*     */     } 
/* 229 */     this.channels.setNominal();
/* 230 */     int depth = this.settings.getTestDepth();
/* 231 */     int cycle = 0;
/*     */     try {
/* 233 */       List<TestSequence> testSet = firstCycle();
/* 234 */       for (int i = 0; this.channels.nominal() && i <= depth; i++) {
/* 235 */         cycle = i;
/* 236 */         if (cycle > 0)
/* 237 */           testSet = nextCycle(testSet); 
/* 238 */         executeTestCycle(testSet, cycle);
/* 239 */         CycleReport report = new CycleReport(this, testSet, cycle);
/* 240 */         this.summary.tallyResults(report);
/* 241 */         this.channels.dispatch((Report)report);
/*     */       }
/*     */     
/* 244 */     } catch (OutOfMemoryError ex) {
/* 245 */       System.gc();
/* 246 */       this.channels.setOutOfMemory();
/*     */     } 
/* 248 */     this.channels.dispatch((Report)this.summary);
/* 249 */     if (this.oracle != null)
/* 250 */       this.oracle.close(); 
/* 251 */     if (this.channels.outOfMemory()) {
/* 252 */       this.channels.dispatch((Question)new Notification(this, 
/* 253 */             "Ran out of memory in cycle: " + cycle + 
/* 254 */             "; \nthe test series was aborted and" + 
/* 255 */             "\ntest statistics are incomplete.", 
/* 256 */             Urgency.WARNING));
/*     */     }
/* 258 */     else if (this.channels.userAborted()) {
/* 259 */       this.channels.dispatch((Question)new Notification(this, 
/* 260 */             "Testing interrupted in cycle: " + cycle + 
/* 261 */             "; \nthe test series was aborted and" + 
/* 262 */             "\ntest statistics are incomplete.", 
/* 263 */             Urgency.WARNING));
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
/*     */   public void execute() throws PermissionException, GeneratorException, ExecutionException {
/* 286 */     inspectProtocols();
/* 287 */     if (this.settings.getModality() != Modality.INSPECT)
/* 288 */       executeTestSeries(); 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/ProtocolWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */