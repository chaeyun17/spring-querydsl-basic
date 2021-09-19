package com.example.springquerydslbasic.team.entity;

import com.example.springquerydslbasic.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Team {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

}
