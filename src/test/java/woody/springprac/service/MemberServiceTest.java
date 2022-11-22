package woody.springprac.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import woody.springprac.domain.Member;
import woody.springprac.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //MemberService memberService = new MemberService();
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //위의 처럼 new 하면 repository 가 service의 리포지토리와 다른 객체임
    //현재는 store가 static이라 상관은 없지만 안좋은 방법!
    //MemberService에서 repository부분 바꾸기
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository); //DI : 외부에서 넣어줌(new 하지 않고)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();;
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2); //예외가 터져야함
//            fail(); // 넘어오면 실패
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //IllegalStateException이 터져야 하고, 오른쪽 로직을 태울때!!
        //assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //메세지 검증하는 법
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}