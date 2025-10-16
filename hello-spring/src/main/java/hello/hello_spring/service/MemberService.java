package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

//    회원가입
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    // control + t, extract method를 이용해 코드를 메소드로 만들기
    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());
        // result에 값이 있으면 "이미 존재하는 회원입니다" 출력
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니.");
        });
//        memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니.");
//        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
