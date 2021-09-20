package com.example.springquerydslbasic.common;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class SearchCondition {

  private String name;
  private Object value;
  private String operation;

  public Long getValueByLong(){
    return Long.valueOf((String) value);
  }

  public Integer getValueByInteger(){
    return Integer.valueOf((String) value);
  }

  public String getValueByString(){
    return (String) value;
  }

  public boolean isOpEq(){
    return this.operation.equals(OperationType.EQUAL.value);
  }

  public boolean isOpContains(){
    return this.operation.equals(OperationType.CONTAINS.value);
  }

  public boolean isOpGreaterThan(){
    return this.operation.equals(OperationType.GREATER_THAN.value);
  }

  public boolean isOpLowerThan(){
    return this.operation.equals(OperationType.LOWER_THAN.value);
  }

  public enum OperationType{

    EQUAL(":"),
    CONTAINS("~"),
    GREATER_THAN(">"),
    LOWER_THAN("<");

    public final String value;

    private OperationType(String value){
      this.value = value;
    }

  }


}


