package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import javax.swing.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "oracle" );
        EntityManager manager = factory.createEntityManager();

        //saveData( manager );
        findById( manager );
        //findAll( manager );

        manager.close();
        factory.close();
    }

    private static void saveData( EntityManager manager ) {

        Departamento departamento = new Departamento()
                .setNome( "Logistica1" );

        TipoDeBem tipoBem = new TipoDeBem()
                .setNome( "material1" );

        Inventario inventario = new Inventario()
                .setDepartamento( departamento )
                .setFim( null )
                .setInicio( LocalDate.now() )
                .setRelatorio( "Teste1" );

        Bem bem = new Bem()
                .setNome("Carro1")
                .setEtiqueta( "veiculo1" )
                .setLocalizacao( departamento )
                .setAquisicao( LocalDate.now() )
                .setTipo( tipoBem);

        manager.getTransaction().begin();
        manager.persist( bem );
        manager.persist( inventario );
        manager.persist( departamento );
        manager.persist( tipoBem );

        manager.getTransaction().commit();
    }

    private static void findAll(EntityManager manager) {
        List<Bem> list = manager.createQuery( "FROM Bem" ).getResultList();

        list.forEach( System.out::println );
    }

    private static void findById(EntityManager manager) {
        Long id = Long.valueOf( JOptionPane.showInputDialog( "ID do Processo" ) );
        Inventario inventario = manager.find( Inventario.class, id );
        System.out.println( inventario );
    }


}