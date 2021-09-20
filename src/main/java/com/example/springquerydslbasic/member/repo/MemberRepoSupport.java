package com.example.springquerydslbasic.member.repo;

import com.example.springquerydslbasic.common.Querydsl4RepositorySupport;
import com.example.springquerydslbasic.member.dto.MemberSearchCondition;
import com.example.springquerydslbasic.member.entity.Member;
import com.example.springquerydslbasic.team.entity.QTeam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.springquerydslbasic.member.entity.QMember.member;

@Repository
public class MemberRepoSupport extends Querydsl4RepositorySupport {

  public MemberRepoSupport() {
    super(Member.class);
  }

  public List<Member> basicSelect(){
    return select(member)
      .from(member)
      .fetch();
  }

  public List<Member> basicSelectFrom(){
    return selectFrom(member).fetch();
  }

  public Page<Member> searchWithPage(MemberSearchCondition condition, Pageable pageable){
    JPAQuery<Member> query = selectFrom(member)
      .leftJoin(member.team, QTeam.team)
      .where(idEq(condition.getId()),
              nameEq(condition.getName()),
              ageEq(condition.getAge()));

    List<Member> content = getQuerydsl().applyPagination(pageable, query).fetch();

    return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
  }

  private BooleanExpression idEq(Long id){
    return id == null ? null : member.id.eq(id);
  }

  private BooleanExpression nameEq(String name){
    return StringUtils.hasText(name) ? member.name.eq(name) : null;
  }

  private BooleanExpression ageEq(Integer age){
    return age == null ? null : member.age.eq(age);
  }


}
