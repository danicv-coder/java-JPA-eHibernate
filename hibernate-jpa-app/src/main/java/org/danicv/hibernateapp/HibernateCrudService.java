package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.services.ClienteServiceImpl;
import org.danicv.hibernateapp.services.ClienteServices;
import org.danicv.hibernateapp.util.jpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();
        ClienteServices service = new ClienteServiceImpl(em);

        System.out.println("========== Listar ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("========== porId ==========");
        Optional<Cliente> obtenerCliente = service.porId(1L);
        if (obtenerCliente.isPresent()) {
            System.out.println(obtenerCliente.get());
        }

        System.out.println("========== Insertar un cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Tomas");
        cliente.setApellido("Fonsi");
        cliente.setFormaPago("debito");
        service.guardar(cliente);
        System.out.println("Cliente guardado con exito!");
        service.listar().forEach(System.out::println);

        System.out.println("========== Modificar un cliente ==========");
        Long id = cliente.getId();
        obtenerCliente = service.porId(id);
        if (obtenerCliente.isPresent()) {
            cliente.setFormaPago("mercadopago");
            service.guardar(cliente);
            System.out.println("cliente modificado con exito!");
            service.listar().forEach(System.out::println);

        }

        System.out.println("========== Eliminar un cliente ==========");
        id = cliente.getId();
        obtenerCliente = service.porId(id);
        if (obtenerCliente.isPresent()) {
            service.eliminar(cliente.getId());
            System.out.println("Se elimino con exito!");
            service.listar().forEach(System.out::println);


        }
    em.close();
    }
}
