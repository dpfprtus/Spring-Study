package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//실제 동작에 필요한 구현 객체를 생성 -> DIP 준수
@Configuration
public class AppConfig {

    //메소드 이름을 보면 역할이 잘 들어난다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //Spring 컨테이너에 등록
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //@Bean이 붙은 메서드의 이름을 스프링 빈의 이름으로 사용한다.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    private static DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
