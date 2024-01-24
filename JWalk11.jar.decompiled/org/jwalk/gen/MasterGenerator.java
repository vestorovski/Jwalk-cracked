package org.jwalk.gen;

import org.jwalk.Generator;
import org.jwalk.JWalker;

public interface MasterGenerator extends Generator {
  JWalker getJWalker();
  
  void logTarget(Object paramObject);
  
  Object getTarget();
  
  Class<?> getTargetType();
  
  void logObject(Object paramObject);
  
  String oracleValue(Object paramObject);
  
  void addDelegate(CustomGenerator paramCustomGenerator);
}


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/gen/MasterGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */