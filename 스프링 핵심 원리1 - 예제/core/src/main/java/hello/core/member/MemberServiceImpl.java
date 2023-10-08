package hello.core.member;

public class MemberServiceImpl implements MemberService{
    
    //추상화와 구체화에 동시에 의존하고 있다 -> DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
