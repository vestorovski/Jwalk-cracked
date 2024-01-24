/*    */ package org.jwalk.gen;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.jwalk.GeneratorException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InterfaceGenerator
/*    */   implements CustomGenerator
/*    */ {
/*    */   private MasterGenerator owner;
/*    */   
/*    */   public boolean canCreate(Class<?> type) {
/* 47 */     return type.isInterface();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object nextValue(Class<?> type) throws GeneratorException {
/* 62 */     if (type == CharSequence.class)
/* 63 */       return this.owner.nextValue(String.class); 
/* 64 */     if (type == Iterable.class)
/* 65 */       return this.owner.nextValue(ArrayList.class); 
/* 66 */     if (type == Collection.class)
/* 67 */       return this.owner.nextValue(ArrayList.class); 
/* 68 */     if (type == List.class)
/* 69 */       return this.owner.nextValue(ArrayList.class); 
/* 70 */     if (type == Set.class)
/* 71 */       return this.owner.nextValue(HashSet.class); 
/* 72 */     if (type == Map.class) {
/* 73 */       return this.owner.nextValue(HashMap.class);
/*    */     }
/* 75 */     if (type == Comparable.class)
/* 76 */       return this.owner.nextValue(this.owner.getTargetType()); 
/* 77 */     if (type == Cloneable.class) {
/* 78 */       return this.owner.nextValue(this.owner.getTargetType());
/*    */     }
/*    */     
/* 81 */     throw new GeneratorException(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOwner(MasterGenerator generator) {
/* 89 */     this.owner = generator;
/*    */   }
/*    */ }


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/InterfaceGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */