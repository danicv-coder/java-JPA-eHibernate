package org.danicv.hibernateapp;

import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
            String formaPago = JOptionPane.showInputDialog("Ingrese metodo de pago");
            em.getTransaction().begin();
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFormaPago(formaPago);
            em.persist(cliente);
            em.getTransaction().commit();

            System.out.println("el id del cliente registrado es: " + cliente.getId());
            cliente = em.find(Cliente.class, cliente.getId());
            System.out.println(cliente);


        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
