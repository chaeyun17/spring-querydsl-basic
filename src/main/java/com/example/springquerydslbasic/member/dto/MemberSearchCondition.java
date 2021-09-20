package com.example.springquerydslbasic.member.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {

  private Long id;
  private String name;
  private Integer age;

}
