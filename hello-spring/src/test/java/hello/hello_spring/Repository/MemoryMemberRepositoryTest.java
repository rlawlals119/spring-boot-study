package hello.hello_spring.Repository;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 순서 상관없이 실행이 되기 때문에 각각의 테스트 후에 리포지토리를 비우지 않으면 에러 발생 가능
    // 테스트 끝날때마다 레포지토리 지워줌
    // 콜백 메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional에서 값을 꺼낼때는 get() 사용
        Member result = repository.findById(member.getId()).get();
        // member와 result 값 비교
        Assertions.assertEquals(member, result);
        // option + enter로 Assertions를 static import함, Assertions 빼고 assertThat 바로 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + F6 하면 한번에 rename 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // .get()을 사용했기 때문에 Optional 감싸줄 필요없음
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
