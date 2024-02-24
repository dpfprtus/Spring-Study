package srudy.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of={"id","username","age"})
public class Member {

    //JPA 표준 스펙이 기본 생성자가 있어야 한다. -> private으로 닫아놓으면 안된다.
    public Member(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    //연관관계 편의 메소드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
