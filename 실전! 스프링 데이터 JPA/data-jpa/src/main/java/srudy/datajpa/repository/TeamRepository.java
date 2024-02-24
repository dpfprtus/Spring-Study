package srudy.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srudy.datajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
