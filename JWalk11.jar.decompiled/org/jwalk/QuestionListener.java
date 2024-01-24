package org.jwalk;

import java.util.EventListener;
import org.jwalk.out.Answer;

public interface QuestionListener extends EventListener {
  Answer respond(QuestionEvent paramQuestionEvent);
}


/* Location:              /home/seraph/Documents/University/Jwalk/JWalk11.jar!/org/jwalk/QuestionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */