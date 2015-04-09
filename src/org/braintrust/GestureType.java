package org.braintrust;

public enum GestureType {

  TRIANGLE("triangle"),
  X("x"),
  RECTANGLE("rectangle"),
  CIRCLE("circle"),
  CHECK("check"),
  CARET("caret"),
  ZIGZAG("zigzag"),
  ARROW("arrow"),
  LEFT_SQUARE_BRACKET("leftSquareBracket"),
  RIGHT_SQUARE_BRACKET("rightSquareBracket"),
  V("v"),
  DELETE("delete"),
  LEFT_CURLY_BRACE("leftCurlyBrace"),
  RIGHT_CURLY_BRACE("rightCurlyBrace"),
  STAR("star"),
  PIGTAIL("pigtail");

  public final String name;
  
  public static int size() {
    return GestureType.values().length;
  }

  private GestureType(String name) {
    this.name = name;
  }

  public static GestureType random() {
    return Utilities.random(GestureType.values());      
  }
  
  public static GestureType fromOutput(double[] output) {
    assert(output.length == GestureType.values().length);    
    return GestureType.values()[Utilities.maxIndex(output)];
  }
}
