package hellojava;

import entity.Member;
import entity.MemberType;
import entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.*;
import java.util.List;

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
//            member.setTeam(team);// 단방향 연관관계 설정, 참조 저장
            em.persist(member);
//            team.getMembers().add(member);
            member.setTeam(team);

//            em.flush(); // db에 반영
//            em.clear(); // 영속성 컨텍스트에서 지운다.
//            // 조회 - 각각 조회해야 된다.
//            Member findMember = em.find(Member.class,member.getId());
//            em.close();
//            Team findTeam = findMember.getTeam();
//            findTeam.getName();
//            System.out.println("findTeam = "+ findTeam);
//            Team findTeam = findMember.getTeam();
//            findTeam.getName();
//            List<Member> members = findTeam.getMembers();
//            for (Member member1 : members) {
//                System.out.println("member1 = " + member1);
//            }
//            System.out.println("member = " + member.toString());


            String jpql = "select m from Member m join fetch m.team where m.name like '%hello%'";
            List<Member> result = em.createQuery(jpql, Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
         emf.close();

    }
}
