package com.example.springquerydslbasic.member.repo;

import com.example.springquerydslbasic.common.QueryDslUtils;
import com.example.springquerydslbasic.common.Querydsl4RepositorySupport;
import com.example.springquerydslbasic.common.SearchCondition;
import com.example.springquerydslbasic.common.SearchReq;
import com.example.springquerydslbasic.member.dto.MemberSearchCondition;
import com.example.springquerydslbasic.member.dto.MemberSearchRes;
import com.example.springquerydslbasic.member.entity.Member;
import com.example.springquerydslbasic.team.entity.QTeam;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

  public Page<Member> searchWithPage(SearchReq searchReq) {
    JPAQuery<Member> query = selectFrom(member);
    query.where(getBooleanExpressions(searchReq.getConditions()));
    Pageable pageable = searchReq.getPageable();
    List<Member> content = getQuerydsl().applyPagination(pageable, query).fetch();
    return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
  }

  public Page<Member> searchJoinWithPage(SearchReq searchReq){
    JPAQuery<Member> query = selectFrom(member);
    for(SearchCondition condition : searchReq.getConditions()){
      if(condition.getName().equals("member.team.name")){
        query.where(QueryDslUtils.getBooleanExpression(member.team, "name", condition.getOperation(), condition.getValue()));
      }
    }
    Pageable pageable = searchReq.getPageable();
    List<Member> content = getQuerydsl().applyPagination(pageable, query).fetch();
    return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
  }

  private BooleanExpression[] getBooleanExpressions(List<SearchCondition> conditions){
    return conditions.stream()
      .map(c-> QueryDslUtils.getBooleanExpression(member,
        c.getName(), c.getOperation(), c.getValue()))
      .toArray(BooleanExpression[]::new);
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


  public Page<MemberSearchRes> searchV2(SearchReq searchReq) {

    List<BooleanExpression> booleanExpressions = new ArrayList<>();

    for(SearchCondition condition : searchReq.getConditions()){
      if(condition.getName().equals("teamId")){
        booleanExpressions.add(QueryDslUtils.getBooleanExpression(QTeam.team,
          "id", condition.getOperation(), condition.getValue()));
      }else if(condition.getName().equals("teamName")) {
        booleanExpressions.add(QueryDslUtils.getBooleanExpression(QTeam.team,
          "name", condition.getOperation(), condition.getValue()));
      }else{
        booleanExpressions.add(QueryDslUtils.getBooleanExpression(member,
          condition.getName(), condition.getOperation(), condition.getValue()));
      }
    }

    return applyPagination(searchReq.getPageable(), (contentQuery)->
      contentQuery
      .from(member)
      .select(Projections.constructor(MemberSearchRes.class,
        member.id,
        member.name,
        member.age,
        member.createdAt,
        member.team.id,
        member.team.name
      ))
      .where(booleanExpressions.toArray(new BooleanExpression[0]))
    );

  }
}
