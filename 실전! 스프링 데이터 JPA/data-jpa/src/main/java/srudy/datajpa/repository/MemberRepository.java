package srudy.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srudy.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
