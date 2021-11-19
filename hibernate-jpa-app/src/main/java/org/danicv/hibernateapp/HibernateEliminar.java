package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import javax.swing.*;
import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {
        //  Scanner teclado = new Scanner(System.in);
        //  System.out.println("Ingrese el id a eliminar");
        //  Long id = teclado.nextLong();
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id a eliminar"));
        EntityManager em = jpaUtil.getEntityManager();
        try {
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
