package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
