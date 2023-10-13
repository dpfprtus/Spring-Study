package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //AppConfig 제외
) //@Component가 붙은 애들을 bean 다 등록해준다.
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
