package entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="USERNAME",nullable = false)
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

//    @Column(name="TEAM_ID")
//    private Long teamId;

    // 단방향매핑 member(다) -> team(일)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", memberType=" + memberType +
                ", team=" + team +
                '}';
    }
    //    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
}
