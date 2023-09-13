package br.com.fiap.domain.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
@Entity
@Table(name = "TB_INVENTARIO")
public class Inventario  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INVENTARIO")
    @SequenceGenerator(name = "SQ_INVENTARIO", sequenceName = "SQ_INVENTARIO")
    @Column(name = "ID_INVENTARIO")
    private Long id;

    //Aula 06/09/2023
    //Não posso deixar o usuario dar um 'SET' na lista(bens)
    //Só pode add um bem por vez
    //Ele não cria duplicado, só ignora

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_INVENARIO_BENS",
            joinColumns = {
                    @JoinColumn(name = "ID_INVENTARIO",
                                referencedColumnName = "ID_INVENTARIO",
                                foreignKey = @ForeignKey(name = "FK_ID_BEM")
                    )
            }, //primeira coluna do join

            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_BEM", //essa tanto faz
                            referencedColumnName = "ID_BEM",  //essa tem que bater com a coluna referenciada
                            foreignKey = @ForeignKey(name = "FK_BEM_ID")
                    )
            } //segunda coluna
    )
    private Set<Bem> bens = new LinkedHashSet<>();

    public Set<Bem> getBem() {
        return Collections.unmodifiableSet( bens );
    }

    public Inventario addBem(Bem id) {
        bens.add(id);
        return this;
    }

    public Inventario removeBem(Bem id) {
        bens.remove(id);
        return this;
    }

    @Column(name = "DT_INICIO", nullable = false)
     private LocalDate inicio;
    @Column(name = "DT_FIM")
    private LocalDate fim;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_DEPARTAMENTO",
            referencedColumnName = "ID_DEPARTAMENTO",
            foreignKey = @ForeignKey(name = "FK_INVENTARIO_DEPARTAMENTO")
    )
    private Departamento departamento;
    @Column(name = "RL_INVENTARIO", nullable = false)
    private String relatorio;

    public Inventario() {
    }

    public Inventario(Long id, Departamento departamento, LocalDate inicio, LocalDate fim, String relatorio) {
        this.id = id;
        this.departamento = departamento;
        this.inicio = inicio;
        this.fim = fim;
        this.relatorio = relatorio;
    }

    public Long getId() {
        return id;
    }

    public Inventario setId(Long id) {
        this.id = id;
        return this;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Inventario setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        return this;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public Inventario setInicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Inventario setFim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public Inventario setRelatorio(String relatorio) {
        this.relatorio = relatorio;
        return this;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", departamento=" + departamento +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", relatorio='" + relatorio + '\'' +
                '}';
    }
}
