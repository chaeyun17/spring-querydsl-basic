package com.example.springquerydslbasic.team.entity;

import com.example.springquerydslbasic.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Team {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Member> members = new HashSet<>();

  public Team addMember(Member member){
    this.members.add(member);
    member.setTeam(this);
    return this;
  }

  public Team removeMember(Member member){
    this.members.remove(member);
    member.setTeam(null);
    return this;
  }

}
