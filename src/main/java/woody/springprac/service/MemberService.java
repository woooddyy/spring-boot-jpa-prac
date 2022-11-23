package woody.springprac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woody.springprac.domain.Member;
import woody.springprac.repository.MemberRepository;
import woody.springprac.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    //원래 : private final MemberRepository memberRepository = new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름 중복 방지하기
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{ //null일 가능성이 있다면 Optional로 감싸서 ifPresent해주기
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //깔끔하게 코드 정리 & 메서드로 따로 빼내기
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
