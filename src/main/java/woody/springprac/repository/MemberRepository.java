package woody.springprac.repository;

import woody.springprac.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional : null일경우 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
