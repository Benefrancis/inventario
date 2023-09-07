package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //Aqui a gente mata a cobra e mostra a Cobra morta!!!!!
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        // persistDados( manager );

        // findInventarioById( manager );

        //   manager.createQuery( "FROM Bem" ).getResultList().forEach( System.out::println );

        manager.close();
        factory.close();


    }

    private static void findInventarioById(EntityManager manager) {
        Long idInventario = Long.valueOf(JOptionPane.showInputDialog("ID do Inventário"));
        Inventario inv = manager.find(Inventario.class, idInventario);
        System.out.println(inv);
    }

    private static void persistDados(EntityManager manager) {
        TipoDeBem tipo = new TipoDeBem();
        tipo.setNome("IMOVEIS");

        Departamento departamento = new Departamento();
        departamento.setNome("DIRETORIA REGIONAL");

        Inventario inventario = new Inventario();
        inventario.setDepartamento(departamento)
                .setInicio(LocalDate.now());

        Bem casa = new Bem();
        casa.setAquisicao(LocalDate.now().minusYears(10))
                .setEtiqueta("123456")
                .setTipo(tipo)
                .setLocalizacao(departamento)
                .setNome("Escritório da Vila Mariana - SP");

        manager.getTransaction().begin();

        manager.persist(casa);
        manager.persist(inventario);

        manager.getTransaction().commit();

        System.out.println(casa);
        System.out.println(inventario);
    }
}