package com.example.springquerydslbasic.common;

import com.querydsl.core.types.Constant;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class QueryDslUtils {

  public static BooleanExpression getBooleanExpression(EntityPathBase entityPathBase, String name, String operationChar, Object value){

    Field field = null;

    String opMethodName = "";
    if(operationChar.equals("~")){
      opMethodName = "containsIgnoreCase";
    }else if(operationChar.equals(":")){
      opMethodName = "eq";
    }else if(operationChar.equals(">")){
      opMethodName = "gt";
    }else if(operationChar.equals("<")){
      opMethodName = "lt";
    }

    try {
      field = entityPathBase.getClass().getDeclaredField(name);
      Type type = field.getType();
      if(type.equals(StringPath.class)) {
        StringPath stringPath = (StringPath) field.get(entityPathBase);
        return (BooleanExpression) stringPath.getClass()
            .getMethod(opMethodName, String.class)
            .invoke(stringPath, getValueByString(value));
      }else if(type.equals(NumberPath.class)){
        NumberPath numberPath = (NumberPath) field.get(entityPathBase);
        Constant constant = ConstantImpl.create(getValueByLong(value));
        return (BooleanExpression) numberPath.getClass()
            .getMethod(opMethodName, Expression.class)
            .invoke(numberPath, constant);
      }

    } catch (NoSuchFieldException |
      IllegalAccessException |
      NoSuchMethodException |
      InvocationTargetException e) {

      e.printStackTrace();
    }
    return null;
  }

  private static Long getValueByLong(Object value){
    return Long.valueOf((String) value);
  }

  private static Integer getValueByInteger(Object value){
    return Integer.valueOf((String) value);
  }

  private static String getValueByString(Object value){
    return (String) value;
  }

}
