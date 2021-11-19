package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.util.jpaUtil;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a modificar"));
            Cliente cliente = em.find(Cliente.class, id);
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:", cliente.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:", cliente.getApellido());
            String formaPago = JOptionPane.showInputDialog("Ingrese el metodo de pago:", cliente.getFormaPago());

            em.getTransaction().begin();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFormaPago(formaPago);
            em.merge(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
