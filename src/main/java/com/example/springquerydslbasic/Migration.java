package com.example.springquerydslbasic;

import com.example.springquerydslbasic.member.entity.Member;
import com.example.springquerydslbasic.member.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


@Component
public class Migration {

  @Autowired
  private MemberRepo memberRepo;

  @PostConstruct
  @Transactional
  public void init() throws Exception {
    Member m1 = new Member(1L, "a", 19, null);
    Member m2 = new Member(2L, "ab", 23, null);
    Member m3 = new Member(3L, "ac", 21, null);
    Member m4 = new Member(4L, "aa", 30, null);
    Member m5 = new Member(5L, "abb", 40, null);
    Member m6 = new Member(6L, "bba", 50, null);

    memberRepo.save(m1);
    memberRepo.save(m2);
    memberRepo.save(m3);
    memberRepo.save(m4);
    memberRepo.save(m5);
    memberRepo.save(m6);
  }
}
