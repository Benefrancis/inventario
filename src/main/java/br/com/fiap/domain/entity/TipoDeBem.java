package br.com.fiap.domain.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "TB_TIPO_BEM", uniqueConstraints = {
        @UniqueConstraint( name = "UK_TIPO_BEM_NM_TIPO_BEM", columnNames = "NM_TIPO_BEM")
})
public class TipoDeBem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_BEM")
    @SequenceGenerator(name = "SQ_TIPO_BEM", sequenceName = "SQ_TIPO_BEM")
    @Column(name = "ID_TIPO_BEM")
    private Long id;
    @Column(name = "NM_TIPO_BEM", nullable = false)
    private String nome;

    public TipoDeBem() {
    }

    public TipoDeBem(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public TipoDeBem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoDeBem setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "TipoDeBem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
