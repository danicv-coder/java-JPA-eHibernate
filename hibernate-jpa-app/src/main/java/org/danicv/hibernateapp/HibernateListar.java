package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c from Cliente c ").getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}
