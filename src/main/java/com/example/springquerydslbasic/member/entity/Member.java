package com.example.springquerydslbasic.member.entity;

import com.example.springquerydslbasic.team.entity.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Member {

  @Id
//  @GeneratedValue
  @EqualsAndHashCode.Include
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

}
