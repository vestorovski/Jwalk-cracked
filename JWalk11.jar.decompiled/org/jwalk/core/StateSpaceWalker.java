/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jwalk.Channels;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ import org.jwalk.Modality;
/*     */ import org.jwalk.PermissionException;
/*     */ import org.jwalk.Settings;
/*     */ import org.jwalk.out.CycleReport;
/*     */ import org.jwalk.out.Notification;
/*     */ import org.jwalk.out.Question;
/*     */ import org.jwalk.out.Report;
/*     */ import org.jwalk.out.StateReport;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateSpaceWalker
/*     */   extends ProtocolWalker
/*     */ {
/*     */   private boolean searchFlag = false;
/*     */   protected boolean stateFlag = false;
/*  53 */   protected List<Method> predicates = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected Map<String, TestSequence> stateCover = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateSpaceWalker(Settings settings, Channels channels) {
/*  68 */     super(settings, channels);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMissingStates() {
/*  76 */     return this.stateFlag;
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
/*     */   public int countMaxStates() {
/*  88 */     int max = 1;
/*  89 */     for (int i = 0; i < this.predicates.size(); i++)
/*  90 */       max *= 2; 
/*  91 */     return max;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, TestSequence> getStateCover() {
/* 100 */     return this.stateCover;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Method> getStatePredicates() {
/* 110 */     return this.predicates;
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
/*     */   public long countPermutations() {
/* 123 */     long permutations = 0L;
/* 124 */     long activeEdges = this.stateCover.size();
/* 125 */     for (int i = 0; i < this.settings.getTestDepth(); i++) {
/* 126 */       permutations += activeEdges;
/* 127 */       activeEdges *= countMethods();
/*     */     } 
/* 129 */     permutations += activeEdges;
/* 130 */     return permutations;
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
/*     */   public int countActiveEdges(int cycle) {
/* 146 */     if (cycle < 0)
/* 147 */       return 0; 
/* 148 */     int activeEdges = 1;
/* 149 */     for (int i = 0; i < cycle; i++)
/* 150 */       activeEdges *= countMethods(); 
/* 151 */     return activeEdges;
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
/*     */   protected boolean isPrunable(TestSequence sequence) {
/* 165 */     return !(!sequence.hasTerminated() && (
/* 166 */       !this.searchFlag || !sequence.isReentrant()));
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
/*     */   protected List<TestSequence> firstCycle(String stateKey) {
/* 182 */     return Collections.singletonList(
/* 183 */         new TestSequence(this.stateCover.get(stateKey)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void findStatePredicates() {
/* 193 */     this.predicates = new ArrayList<>(5);
/* 194 */     for (Method method : getMethods()) {
/* 195 */       if (method.getReturnType() == boolean.class && (
/* 196 */         method.getParameterTypes()).length == 0) {
/* 197 */         this.predicates.add(method);
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
/*     */   private String getStateName(String name) {
/* 211 */     if (name.startsWith("is"))
/* 212 */       name = name.substring(2); 
/* 213 */     char first = name.charAt(0);
/* 214 */     if (Character.isLowerCase(first))
/* 215 */       name = String.valueOf(String.valueOf(Character.toUpperCase(first))) + 
/* 216 */         name.substring(1); 
/* 217 */     return name;
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
/*     */   private String detectState(TestSequence prefix) throws PermissionException, GeneratorException, ExecutionException {
/* 239 */     List<Method> truePredicates = new ArrayList<>(3);
/* 240 */     for (Method predicate : this.predicates) {
/* 241 */       TestSequence probe = new TestSequence(prefix);
/* 242 */       probe.add(new InvokeTestCase(predicate));
/* 243 */       Boolean result = (Boolean)probe.execute(makeGenerator());
/* 244 */       if (result.booleanValue())
/* 245 */         truePredicates.add(predicate); 
/*     */     } 
/* 247 */     if (truePredicates.size() > 0) {
/* 248 */       StringBuilder buffer = new StringBuilder();
/* 249 */       for (Method predicate : truePredicates) {
/* 250 */         String name = getStateName(predicate.getName());
/* 251 */         if (buffer.length() > 0)
/* 252 */           buffer.append('&'); 
/* 253 */         buffer.append(name);
/*     */       } 
/* 255 */       return buffer.toString();
/*     */     } 
/*     */     
/* 258 */     return "Default";
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
/*     */   private void checkDetectedStates() {
/* 271 */     for (Method predicate : this.predicates) {
/* 272 */       String name = getStateName(predicate.getName());
/* 273 */       boolean present = false;
/* 274 */       boolean absent = false;
/* 275 */       for (String stateKey : this.stateCover.keySet()) {
/* 276 */         if (stateKey.contains(name)) {
/* 277 */           present = true; continue;
/*     */         } 
/* 279 */         absent = true;
/* 280 */       }  if (!present || !absent) {
/* 281 */         this.stateFlag = true;
/*     */         break;
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
/*     */   protected void executeProbeCycle(List<TestSequence> testSet, int cycle) throws PermissionException, GeneratorException, ExecutionException {
/* 305 */     for (TestSequence prefix : testSet) {
/* 306 */       prefix.execute(makeGenerator());
/* 307 */       if (!isPrunable(prefix)) {
/* 308 */         String stateKey = detectState(prefix);
/* 309 */         if (!this.stateCover.containsKey(stateKey)) {
/* 310 */           this.stateCover.put(stateKey, prefix);
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
/*     */   protected void discoverStates() throws PermissionException, GeneratorException, ExecutionException {
/* 333 */     ensureExecutable();
/* 334 */     findStatePredicates();
/* 335 */     this.stateFlag = false;
/* 336 */     this.searchFlag = true;
/* 337 */     this.stateCover = new LinkedHashMap<>();
/* 338 */     this.channels.setNominal();
/* 339 */     int maxStates = countMaxStates();
/* 340 */     int depth = this.settings.getProbeDepth();
/* 341 */     int cycle = 0;
/*     */     try {
/* 343 */       List<TestSequence> testSet = firstCycle();
/* 344 */       for (int i = 0; this.channels.nominal() && i <= depth && 
/* 345 */         this.stateCover.size() < maxStates; i++) {
/* 346 */         cycle = i;
/* 347 */         if (cycle > 0)
/* 348 */           testSet = nextCycle(testSet); 
/* 349 */         executeProbeCycle(testSet, cycle);
/*     */       }
/*     */     
/* 352 */     } catch (OutOfMemoryError ex) {
/* 353 */       System.gc();
/* 354 */       this.channels.setOutOfMemory();
/*     */     } 
/* 356 */     checkDetectedStates();
/* 357 */     this.channels.dispatch((Report)new StateReport(this));
/* 358 */     this.searchFlag = false;
/* 359 */     if (this.channels.outOfMemory()) {
/* 360 */       this.channels.dispatch((Question)new Notification(this, 
/* 361 */             "Ran out of memory in probe cycle: " + cycle + 
/* 362 */             ". \nSuggest that you decrease the probe depth" + 
/* 363 */             "\nwhen analysing the state space of this class.", 
/* 364 */             Urgency.WARNING));
/*     */     }
/* 366 */     else if (this.channels.userAborted()) {
/* 367 */       this.channels.dispatch((Question)new Notification(this, 
/* 368 */             "Probing interrupted in cycle: " + cycle + 
/* 369 */             "; \nthe test series was aborted and" + 
/* 370 */             "\ntest statistics are incomplete.", 
/* 371 */             Urgency.WARNING));
/*     */     } 
/* 373 */     if (this.stateFlag) {
/* 374 */       this.channels.dispatch((Question)new Notification(this, 
/* 375 */             "Expected states not found after probe cycle: " + cycle + 
/* 376 */             ". \nSuggest that you increase the probe depth" + 
/* 377 */             "\nwhen analysing the state space of this class.", 
/* 378 */             Urgency.WARNING));
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
/*     */   protected void executeTestSeries() throws PermissionException, GeneratorException, ExecutionException {
/* 402 */     this.summary = new SummaryReport(this);
/* 403 */     if (this.settings.getModality() == Modality.VALIDATE) {
/* 404 */       this.oracle = new Oracle(this);
/* 405 */       this.oracle.open();
/*     */     } 
/* 407 */     this.channels.setNominal();
/* 408 */     int depth = this.settings.getTestDepth();
/* 409 */     int cycle = 0;
/*     */     try {
/* 411 */       for (String stateKey : this.stateCover.keySet()) {
/* 412 */         List<TestSequence> testSet = firstCycle(stateKey);
/* 413 */         for (int i = 0; this.channels.nominal() && i <= depth; i++) {
/* 414 */           cycle = i;
/* 415 */           if (cycle > 0)
/* 416 */             testSet = nextCycle(testSet); 
/* 417 */           executeTestCycle(testSet, cycle);
/* 418 */           CycleReport report = 
/* 419 */             new CycleReport(this, testSet, stateKey, cycle);
/* 420 */           this.summary.tallyResults(report);
/* 421 */           this.channels.dispatch((Report)report);
/*     */         }
/*     */       
/*     */       } 
/* 425 */     } catch (OutOfMemoryError ex) {
/* 426 */       System.gc();
/* 427 */       this.channels.setOutOfMemory();
/*     */     } 
/* 429 */     this.channels.dispatch((Report)this.summary);
/* 430 */     if (this.oracle != null)
/* 431 */       this.oracle.close(); 
/* 432 */     if (this.channels.outOfMemory()) {
/* 433 */       this.channels.dispatch((Question)new Notification(this, 
/* 434 */             "Ran out of memory in cycle: " + cycle + 
/* 435 */             "; \nthe test series was aborted and" + 
/* 436 */             "\ntest statistics are incomplete.", 
/* 437 */             Urgency.WARNING));
/*     */     }
/* 439 */     else if (this.channels.userAborted()) {
/* 440 */       this.channels.dispatch((Question)new Notification(this, 
/* 441 */             "Testing interrupted in cycle: " + cycle + 
/* 442 */             "; \nthe test series was aborted and" + 
/* 443 */             "\ntest statistics are incomplete.", 
/* 444 */             Urgency.WARNING));
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
/* 468 */     inspectProtocols();
/* 469 */     discoverStates();
/* 470 */     if (!this.channels.userAborted() && 
/* 471 */       this.settings.getModality() != Modality.INSPECT)
/* 472 */       executeTestSeries(); 
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/StateSpaceWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */