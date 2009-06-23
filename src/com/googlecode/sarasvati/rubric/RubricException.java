package com.googlecode.sarasvati.rubric;

import org.antlr.runtime.RecognitionException;

public class RubricException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public RubricException (String message)
  {
    super( message );
  }

  public RecognitionException getRecognitionException ()
  {
    return null;
  }
}
