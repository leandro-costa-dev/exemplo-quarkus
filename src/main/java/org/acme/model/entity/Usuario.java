package org.acme.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "cpf", nullable = true, length = 11)
    private String cpf;

    @Column(name = "data_cadastro", nullable = true)
    private LocalDate dataCadastro;

    @Column(name = "teste", nullable = true)
    private String teste;

    public Usuario(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
