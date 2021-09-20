package com.example.springquerydslbasic.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class MemberSearchRes {

  private Long memberId;
  private String memberName;
  private Integer memberAge;
  private LocalDateTime memberCreatedAt;
  private Long teamId;
  private String teamName;

}
