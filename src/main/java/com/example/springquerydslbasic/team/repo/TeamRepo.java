package com.example.springquerydslbasic.team.repo;

import com.example.springquerydslbasic.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {
}
