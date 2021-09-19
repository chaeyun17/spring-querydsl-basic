package com.example.springquerydslbasic.member.entity;

import com.example.springquerydslbasic.team.entity.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

  @Id @GeneratedValue
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

}
