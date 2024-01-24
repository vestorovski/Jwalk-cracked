package org.jwalk;

public interface Generator {
  Object nextValue(Class<?> paramClass) throws GeneratorException;
  
  boolean canCreate(Class<?> paramClass);
}


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/Generator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */