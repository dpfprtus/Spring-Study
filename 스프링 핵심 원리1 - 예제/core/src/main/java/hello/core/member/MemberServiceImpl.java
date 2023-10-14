package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    
    //추상화와 구체화에 동시에 의존하고 있다 -> DIP 위반
    private final MemberRepository memberRepository;

    @Autowired //의존 관계 주입, 생성자가 하나만 있다면 생략 가능.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
