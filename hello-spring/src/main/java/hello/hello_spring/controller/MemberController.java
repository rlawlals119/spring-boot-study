package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    // @Autowired private MemberService memberservice; --> 이게 필드주입, 비추
    // Setter를 만들고 그걸 @Autowired하는 Setter 주입도 있음, public으로 열려있어야 하기 때문에 비추

    /*
    * DI 3가지 방법 (차이점 공부하면 좋을거 같음)
    * - 필드 주입
    * - Setter 주입
    * - 생성자 주입 */

    // Autowired가 작동하기 위해서는 MemberService클래스 위에 @Service를 작성해야 함
    @Autowired  // 스프링 컨테이너에서 MemberService를 가져와서 인자로 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { // springdl MemberForm에 setName을 통해 name값을 저장해줌
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member: " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // members라는 이름(key)으로 회원 목록 데이터를 Model에 담습니다.
        // 템플릿에서 ${members} 로 데이터를 접근할 수 있게 됩니다.
        return "members/memberList";
    }
}
