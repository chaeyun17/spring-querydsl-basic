package com.example.springquerydslbasic.member.repo;

import com.example.springquerydslbasic.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {
}
