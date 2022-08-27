package com.bale.jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("TEST START");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 등록
            Member member1 = new Member(10L, "HelloAA");
            Member member2 = new Member(20L, "HelloBB");

            System.out.println("member1 = " + member1);
            System.out.println("member2 = " + member2);

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, 20L);

            System.out.println("m = " + m);

            m.setName("HelloCC");
//            final List<Member> mList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();

//            System.out.println("mList = " + mList);

            tx.commit();
        } catch (Exception e) {
            System.out.println("Exception Occurs");
            e.getStackTrace();
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
