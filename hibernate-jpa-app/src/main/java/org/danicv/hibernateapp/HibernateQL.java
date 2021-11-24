package org.danicv.hibernateapp;

import jakarta.persistence.EntityManager;
import org.danicv.hibernateapp.entity.Cliente;
import org.danicv.hibernateapp.models.ClienteDto;
import org.danicv.hibernateapp.util.jpaUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();
        System.out.println("======= CONSULTAR TODO =======");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("======= por id =======");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1l)
                .getSingleResult();
        System.out.println(cliente);
        System.out.println("======= CONSULTAR NOMBRE POR ID  =======");
        String nombreCLiente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                .setParameter("id", 2L).getSingleResult();
        System.out.println(nombreCLiente);

        System.out.println("======= CONSULTA POR CAMPOS PERSONALIZADOS =======");
        Object[] objectCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 3L).getSingleResult();
        Long id = (Long) objectCliente[0];
        String nombre = (String) objectCliente[1];
        String apellido = (String) objectCliente[2];
        List campos = Arrays.asList(objectCliente);
        System.out.println(campos);
        System.out.println("id: " + id + " Nombre: " + nombre + " apellido: " + apellido);

        System.out.println("======= OBTENER UNA LISTA DE CAMPOS PERSONALIZADOS =======");
        List<Object[]> objectClienteS = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class).getResultList();
        System.out.println(objectClienteS);
        for (Object[] registro : objectClienteS) {
            id = (Long) registro[0];
            nombre = (String) registro[1];
            apellido = (String) registro[2];
            System.out.println("id: " + id + " Nombre: " + nombre + " apellido: " + apellido);
        }

        System.out.println("======= CONSULTA POR CLIENTE Y FORMA DE PAGO =======");
        List<Object[]> registro = em.createQuery("select c, c.formaPago from Cliente c", Object[].class).getResultList();
        for (Object[] registros : registro) {
            Cliente c = (Cliente) registros[0];
            String formaPago = (String) registros[1];
            System.out.println("forma de pago= " + formaPago + ", " + c);
        }
        System.out.println("======= CONSULTA QUE PUEBLA Y DEVUELVE OBJETO ENTITY DE UNA CLASE PERSONALIZADA =======");
        List<Cliente> clientes1 = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class).getResultList();
        clientes1.forEach(System.out::println);

        System.out.println("======= CONSULTA QUE PUEBLA Y DEVUELVE OBJETO DTO DE UNA CLASE PERSONALIZADA =======");
        List<ClienteDto> clientesDto = em.createQuery("select new org.danicv.hibernateapp.models.ClienteDto(c.nombre, c.apellido) from Cliente c"
                , ClienteDto.class).getResultList();
        clientesDto.forEach(System.out::println);

        System.out.println("====== CONSULTA CON NOMBRES DE CLIENTES ======");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("====== CONSULTA CON NOMBRES UNICOS DE CLIENTES ======");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("====== CONSULTA CON FORMA DE PAGO UNICAS ======");
        List<String> pagos = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class).getResultList();
        pagos.forEach(System.out::println);

        System.out.println("====== CONSULTA LA CANTIDAD DE FORMA DE PAGO UNICAS ======");
        Long cantMetoPagos = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class).getSingleResult();
        System.out.println(cantMetoPagos);

        System.out.println("====== CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS  ======");
        List<String> nombreApeliido = em.createQuery("select concat(c.nombre, ' ', c.apellido) from Cliente c", String.class).getResultList();
        nombreApeliido.forEach(System.out::println);

        // OTRA FORMA DE CONSULTAR ES:
        nombreApeliido = em.createQuery("select c.nombre || ' ' || c.apellido from Cliente c", String.class).getResultList();
        nombreApeliido.forEach(System.out::println);

        System.out.println("====== CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS  EN MAYUSCULA ======");
        List<String> nombreApeliidoMayu = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) from Cliente c", String.class).getResultList();
        nombreApeliidoMayu.forEach(System.out::println);

        System.out.println("====== CONSULTA PARA BUSCAR POR NOMBRES  ======");
        String param = "Alex";
        clientes = em.createQuery("select c from Cliente c where c.nombre like :parametro", Cliente.class)
                .setParameter("parametro", "%" + param + "%")
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("====== CONSULTA POR RANGOS  ======");
        //   clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'A' and 'k'", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("====== CONSULTA CON ORDENAMIENTO  ======");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("====== CONSULTA CON TOTAL DE REGISTRO  DE LA TABLA  ======");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        System.out.println("====== CONSULTA CON EL VALOR MINIMO DEL ID ======");
        long min = em.createQuery("select min(c.id) as min from Cliente c", Long.class).getSingleResult();
        System.out.println(min);

        System.out.println("====== CONSULTA CON EL VALOR Maximo DEL ID ======");
        Long max = em.createQuery("select max(c.id) as max from Cliente c", Long.class).getSingleResult();
        System.out.println(max);

        System.out.println("====== CONSULTA CON NOMBRE Y SU LARGO ======");
        registro = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registro.forEach(reg -> {
            String nombreReg = (String) reg[0];
            int largo = (int) reg[1];
            System.out.println("Nombre= " + nombreReg + " largo= " + largo);
        });

        System.out.println("====== CONSULTA CON EL NOMBRE MAS CORTO ======");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        System.out.println("====== CONSULTA CON EL NOMBRE MAS LARGO ======");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        System.out.println("====== CONSULTA RESUMEN FUNCIONES AGREGACIONES COUNT, MAX, MIN, SUM, AVG ======");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                .getSingleResult();
        Long minId = (Long) estadisticas[0];
        Long maxId = (Long) estadisticas[1];
        Long sumId = (Long) estadisticas[2];
        Long totalId = (Long) estadisticas[3];
        Double promedioNombre = (double) estadisticas[4];
        System.out.println("minimoId= " + minId + " maximoId= " + maxId + " sumaId= " + sumId + " totalId=" + totalId
                + " nombrePromedio=" + promedioNombre);

        System.out.println("====== SUBCONSULTA CON EL NOMBRE MAS CORTO Y SU LARGO  ======");
        registro = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where length(c.nombre) = " +
                "(select min(length(c.nombre)) from Cliente c)", Object[].class).getResultList();
        for (Object[] reg : registro) {
            String nombreReg = (String) reg[0];
            Integer minNombre = (Integer) reg[1];
            System.out.println("nombre= " + nombreReg + " minNombre= " + minNombre);
        }
        System.out.println("====== CONSULTA PARA OBTENER EL ULTIMO REGISTRO ======");
        Cliente ultimoCLiente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)",
                Cliente.class).getSingleResult();
        System.out.println(ultimoCLiente);

        System.out.println("====== CONSULTA where IN ======");
        clientes = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L, 2L, 8L))
                .getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }

}
