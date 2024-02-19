package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Casas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Casas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Clientes clientes;

    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String bairro;
    private int numeroQuartos;
    private int numeroBanheiros;
    private double precoAluguel;
    private boolean mobiliada;
    private boolean disponivel;
    private LocalDate dataDisponibilidade;
    private String descricao;
    private String tipoAnuncio;

    public Casas(CasasRequestDTO casasRequestDTO) {
        this.rua = casasRequestDTO.rua();
        this.numero = casasRequestDTO.numero();
        this.complemento = casasRequestDTO.complemento();
        this.cep = casasRequestDTO.cep();
        this.cidade = casasRequestDTO.cidade();
        this.bairro = casasRequestDTO.bairro();
        this.numeroQuartos = casasRequestDTO.numeroQuartos();
        this.numeroBanheiros = casasRequestDTO.numeroBanheiros();
    this.precoAluguel = casasRequestDTO.precoAluguel();
        this.mobiliada = casasRequestDTO.mobiliada();
    this.disponivel = casasRequestDTO.disponivel();
        this.dataDisponibilidade = casasRequestDTO.dataDisponibilidade();
    this.descricao = casasRequestDTO.descricao();
    this.tipoAnuncio = casasRequestDTO.tipoAnuncio();
    }
}
