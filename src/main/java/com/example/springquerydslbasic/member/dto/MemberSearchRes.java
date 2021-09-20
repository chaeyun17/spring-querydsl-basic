package com.example.springquerydslbasic.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class MemberSearchRes {

  private Long id;
  private String name;
  private Integer age;
  private LocalDateTime createdAt;
  private Long teamId;
  private String teamName;
  private Long companyId;
  private String companyName;
  private Long cityId;
  private String cityName;

}
