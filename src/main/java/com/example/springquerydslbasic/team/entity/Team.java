package com.example.springquerydslbasic.team.entity;

import com.example.springquerydslbasic.company.entity.Company;
import com.example.springquerydslbasic.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Team {

  @Id
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

}
