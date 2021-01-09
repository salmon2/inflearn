package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //ㅇ게 클리어해준다. 먼저 트랙잭션 후 테스트 후 롤백해준다.
public class MemberServiceINtegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given 주워젔을대
        Member member = new Member();
        member.setName("spring100");


        //when 실행할때
        Long saveId = memberService.join(member);


        //then 이게 나와야해
        Member one = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(one.getName());
    }
    @Test
    void 중복회원예외() {
        //given 주워젔을대
        Member member1 = new Member();
        member1.setName("helloㅁㄴㅇㄹ");

        Member member2 = new Member();
        member2.setName("helloㅁㄴㅇㄹ");

        //when 실행할때
        Long saveId = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }



        //then 이게 나와야해

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }







}
