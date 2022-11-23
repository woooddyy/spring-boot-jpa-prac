package woody.springprac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woody.springprac.aop.TimeTraceAop;
import woody.springprac.repository.*;
import woody.springprac.service.MemberService;

@Configuration
public class SpringConfig {
//    private DataSource dataSource;
//    private final EntityManager em;
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em){
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
