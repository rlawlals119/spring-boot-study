package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // 반환값이 null일 수 있기 때문에 Optional로 감싸서 반환
    Optional<Member> findById(Long Id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
