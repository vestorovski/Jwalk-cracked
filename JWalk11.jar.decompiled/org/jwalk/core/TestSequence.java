/*     */ package org.jwalk.core;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Member;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jwalk.ExecutionException;
/*     */ import org.jwalk.GeneratorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestSequence
/*     */ {
/*     */   private boolean executed = false;
/*  44 */   private ObjectGenerator generator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<TestCase> sequence;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object target;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object result;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private Outcome outcome = Outcome.UNKNOWN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureDeterminstic(Throwable cause, int counter) throws ExecutionException {
/*  75 */     if (counter > 0) {
/*  76 */       int index = this.sequence.size() - counter + 1;
/*  77 */       TestCase testCase = this.sequence.get(index);
/*  78 */       ExecutionException ex = 
/*  79 */         new ExecutionException(testCase.getOperation(), true);
/*  80 */       ex.initCause(cause);
/*  81 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TestSequence() {
/*  91 */     this.sequence = new ArrayList<>(1);
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
/*     */   public TestSequence(TestSequence prefix) {
/* 105 */     this.sequence = new ArrayList<>(prefix.size() + 1);
/* 106 */     for (TestCase testCase : prefix.sequence) {
/* 107 */       add(testCase.clone());
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
/*     */   public void add(TestCase testCase) {
/* 122 */     this.sequence.add(testCase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 131 */     return this.sequence.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TestCase> getSequence() {
/* 139 */     return this.sequence;
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
/*     */   public Member getOperation() {
/* 151 */     int last = size() - 1;
/* 152 */     if (last >= 0) {
/* 153 */       return ((TestCase)this.sequence.get(last)).getOperation();
/*     */     }
/* 155 */     return null;
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
/*     */   public Class<?> getReturnType() {
/* 167 */     int last = size() - 1;
/* 168 */     if (last >= 0) {
/* 169 */       return ((TestCase)this.sequence.get(last)).getReturnType();
/*     */     }
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getTarget() {
/* 180 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getResult() {
/* 189 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String oracleResult() {
/* 200 */     return this.generator.oracleValue(this.result);
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
/*     */   public Object execute(ObjectGenerator generator) throws GeneratorException, ExecutionException {
/* 220 */     this.generator = generator;
/* 221 */     int counter = this.sequence.size();
/*     */     try {
/* 223 */       this.executed = true;
/* 224 */       for (TestCase testCase : this.sequence) {
/* 225 */         counter--;
/* 226 */         this.result = testCase.execute(generator, this.target);
/* 227 */         if (this.target == null) {
/* 228 */           this.target = this.result;
/* 229 */           generator.logTarget(this.target);
/*     */         }
/*     */       
/*     */       } 
/* 233 */     } catch (InvocationTargetException callFailed) {
/*     */ 
/*     */       
/* 236 */       this.result = callFailed.getCause();
/* 237 */       generator.logObject(this.result);
/* 238 */       ensureDeterminstic(callFailed.getCause(), counter);
/*     */     } 
/* 240 */     return this.result;
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
/*     */   public Outcome getOutcome() {
/* 254 */     return this.outcome;
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
/*     */   public void setOutcome(Outcome outcome) {
/* 268 */     this.outcome = outcome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExecuted() {
/* 276 */     return this.executed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSucceeded() {
/* 287 */     return (this.executed && !(this.result instanceof Exception));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTerminated() {
/* 298 */     return (this.executed && this.result instanceof Exception);
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
/*     */   public boolean isUnchanged() {
/* 310 */     int last = size() - 1;
/* 311 */     return (last > 0 && (
/* 312 */       (TestCase)this.sequence.get(last)).getState() == (
/* 313 */       (TestCase)this.sequence.get(last - 1)).getState());
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
/*     */   public boolean isReentrant() {
/* 327 */     int last = size() - 1;
/* 328 */     if (last > 0) {
/* 329 */       int state = ((TestCase)this.sequence.get(last)).getState();
/* 330 */       for (int index = 0; index < last; index++) {
/* 331 */         if (((TestCase)this.sequence.get(index)).getState() == state)
/* 332 */           return true; 
/*     */       } 
/* 334 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPassed() {
/* 343 */     return !(this.outcome != Outcome.CORRECT && 
/* 344 */       this.outcome != Outcome.CONFIRMED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFailed() {
/* 353 */     return !(this.outcome != Outcome.INCORRECT && 
/* 354 */       this.outcome != Outcome.REJECTED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConfirmed() {
/* 363 */     return (this.outcome == Outcome.CONFIRMED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRejected() {
/* 372 */     return (this.outcome == Outcome.REJECTED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCorrect() {
/* 381 */     return (this.outcome == Outcome.CORRECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncorrect() {
/* 390 */     return (this.outcome == Outcome.INCORRECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidated() {
/* 401 */     return (this.outcome != Outcome.UNKNOWN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLongKey() {
/* 412 */     StringBuffer buffer = new StringBuffer();
/* 413 */     for (TestCase testCase : this.sequence)
/* 414 */       buffer.append(testCase.getKey(this.generator)); 
/* 415 */     return buffer.toString();
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
/*     */   public String getShortKey() {
/* 429 */     int lastIndex = size() - 1;
/*     */     
/* 431 */     TestCase lastCase = this.sequence.get(lastIndex);
/* 432 */     List<TestCase> prefix = new ArrayList<>();
/* 433 */     int lastState = 0;
/*     */     
/* 435 */     for (TestCase testCase : this.sequence.subList(0, lastIndex)) {
/* 436 */       int nextState = testCase.getState();
/* 437 */       if (nextState != lastState)
/* 438 */         prefix.add(testCase); 
/* 439 */       lastState = nextState;
/*     */     } 
/*     */     
/* 442 */     StringBuilder buffer = new StringBuilder();
/* 443 */     if (!prefix.isEmpty()) {
/* 444 */       lastIndex = prefix.size() - 1;
/* 445 */       lastState = ((TestCase)prefix.get(lastIndex)).getState();
/* 446 */       for (TestCase testCase : prefix) {
/* 447 */         buffer.append(testCase.getKey(this.generator));
/* 448 */         if (testCase.getState() == lastState) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 453 */     buffer.append(lastCase.getKey(this.generator));
/* 454 */     return buffer.toString();
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
/*     */   public String toString() {
/* 468 */     StringBuffer buffer = new StringBuffer();
/* 469 */     for (TestCase testCase : this.sequence)
/* 470 */       buffer.append(testCase.toString(this.generator)).append('\n'); 
/* 471 */     buffer.append("\t==> ").append(oracleResult());
/* 472 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/core/TestSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */