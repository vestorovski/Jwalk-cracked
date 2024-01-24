/*     */ package org.jwalk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeneratorException
/*     */   extends JWalkException
/*     */ {
/*  29 */   private Class<?> type = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   private Generator generator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean internal = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneratorException(Class<?> type) {
/*  53 */     super("Could not synthesise a value for: " + ((type == null) ? null : type.getSimpleName()), Error.GENERATOR_ERROR);
/*  54 */     this.type = type;
/*  55 */     this.internal = false;
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
/*     */   public GeneratorException(Class<?> type, Generator generator) {
/*  72 */     super(String.valueOf((generator == null) ? "A generator" : generator.getClass().getSimpleName()) + " failed while synthesising: " + ((type == null) ? null : type.getSimpleName()), Error.GENERATOR_ERROR);
/*  73 */     this.type = type;
/*  74 */     this.generator = generator;
/*  75 */     this.internal = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean creationFailed() {
/*  85 */     return !this.internal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean generatorFailed() {
/*  95 */     return this.internal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getType() {
/* 103 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Generator getGenerator() {
/* 112 */     return this.generator;
/*     */   }
/*     */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/GeneratorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */