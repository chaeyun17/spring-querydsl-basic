package com.example.springquerydslbasic;

import com.example.springquerydslbasic.member.entity.Member;
import com.example.springquerydslbasic.member.repo.MemberRepo;
import com.example.springquerydslbasic.team.entity.Team;
import com.example.springquerydslbasic.team.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Component
public class Migration {

  @Autowired
  private MemberRepo memberRepo;

  @Autowired
  private TeamRepo teamRepo;

  @PostConstruct
  @Transactional
  public void init() throws Exception {
    Team t1 = new Team(1L, "t1");
    Team t2 = new Team(2L, "t2");
    teamRepo.save(t1);
    teamRepo.save(t2);

    Member m1 = new Member(1L, "a", 19, t1, LocalDateTime.now().minusHours(6));
    Member m2 = new Member(2L, "ab", 23, t1, LocalDateTime.now().minusHours(5));
    Member m3 = new Member(3L, "ac", 21, t1, LocalDateTime.now().minusHours(4));
    Member m4 = new Member(4L, "aa", 30, t2, LocalDateTime.now().minusHours(3));
    Member m5 = new Member(5L, "abb", 40, t2, LocalDateTime.now().minusHours(2));
    Member m6 = new Member(6L, "bba", 50, t2, LocalDateTime.now().minusHours(1));

    memberRepo.save(m1);
    memberRepo.save(m2);
    memberRepo.save(m3);
    memberRepo.save(m4);
    memberRepo.save(m5);
    memberRepo.save(m6);
  }
}
