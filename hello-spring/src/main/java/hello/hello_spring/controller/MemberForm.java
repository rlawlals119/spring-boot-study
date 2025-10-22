package hello.hello_spring.controller;

public class MemberForm {
    private String name;    // createMemberForm.html에 있는 name과 매칭됨, name이 "name"인 input값이랑 매칭

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
