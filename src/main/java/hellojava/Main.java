package hellojava;

import entity.Member;
import entity.MemberType;
import entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            // 팀저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            Member member = new Member();
//            member.setId(100L);
            member.setName("hello");
            member.setMemberType(MemberType.USER);
//            member.setTeamId(team.getId());
            member.setTeam(team);// 단방향 연관관계 설정, 참조 저장
            em.persist(member);

            em.flush();
            em.clear();
            // 조회 - 각각 조회해야 된다.
            Member findMember = em.find(Member.class,member.getId());
            Team findTeam = findMember.getTeam();
            findTeam.getName();
//            System.out.println("member = " + member.toString());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
         emf.close();

    }
}
