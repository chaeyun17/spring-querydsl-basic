//package com.example.springquerydslbasic.member.repo;
//
//import com.example.springquerydslbasic.member.dto.MemberSearchCondition;
//import com.example.springquerydslbasic.member.entity.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class MemberRepoSupportTest {
//
//  @Autowired
//  MemberRepoSupport memberRepoSupport;
//
//
//  @PersistenceContext
//  EntityManager entityManager;
//
//  @Test
//  void searchWithPage() {
//    Member m1 = new Member(1L, "a", 19, null);
//    Member m2 = new Member(2L, "a", 21, null);
//    Member m3 = new Member(3L, "a", 20, null);
//    entityManager.persist(m1);
//    entityManager.persist(m2);
//    entityManager.persist(m3);
//
//    Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending());
//    MemberSearchCondition condition = new MemberSearchCondition();
//    condition.setName("a");
//
//    Page<Member> memberPage = memberRepoSupport.searchWithPage(condition, pageable);
//
//    System.out.println(memberPage.getPageable());
//    System.out.println(memberPage.getContent());
//
//    assertThat(memberPage.getContent().get(0).getAge())
//      .isEqualTo(21l);
//    assertThat(memberPage.getPageable().getPageNumber()).isEqualTo(0);
//
//  }
//
//
//}