package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import java.util.List;
import java.util.Scanner;

public class hibernateSingleResult {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        EntityManager em = jpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingrese una forma de pago");
        String pago = teclado.next();
        query.setParameter(1, pago);
        Cliente c = (Cliente) query.getSingleResult(); // SINGLERESULT ME PERMITE DEVOLVER UN SOLO CAMPO DEL OBJETO CLIENTE
        System.out.println(c);
        em.close();
    }
}
