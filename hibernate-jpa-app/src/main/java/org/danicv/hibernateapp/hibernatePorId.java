package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import java.util.Scanner;

public class hibernatePorId {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        EntityManager em = jpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
        System.out.println("Ingrese un id");
        Long id = teclado.nextLong();
        query.setParameter(1, id);
        Cliente c = (Cliente) query.getSingleResult(); // SINGLERESULT ME PERMITE DEVOLVER UN SOLO CAMPO DEL OBJETO CLIENTE
        System.out.println(c);
        em.close();
    }
}
