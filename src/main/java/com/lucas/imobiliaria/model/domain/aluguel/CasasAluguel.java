package com.lucas.imobiliaria.model.domain.aluguel;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "casas_aluguel")
@Getter
@Setter
public class CasasAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

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

    public CasasAluguel(Integer id, String rua, String numero, String complemento, String cep, String cidade, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numeroQuartos = numeroQuartos;
        this.numeroBanheiros = numeroBanheiros;
        this.precoAluguel = precoAluguel;
        this.mobiliada = mobiliada;
        this.disponivel = disponivel;
        this.dataDisponibilidade = dataDisponibilidade;
        this.descricao = descricao;
    }

    public CasasAluguel() {

    }

    public CasasAluguel(CasasAluguelRequestDTO casasAluguelRequestDTO) {
        this.rua = casasAluguelRequestDTO.rua();
        this.numero = casasAluguelRequestDTO.numero();
        this.complemento = casasAluguelRequestDTO.complemento();
        this.cep = casasAluguelRequestDTO.cep();
        this.cidade = casasAluguelRequestDTO.cidade();
        this.bairro = casasAluguelRequestDTO.bairro();
        this.numeroQuartos = casasAluguelRequestDTO.numeroQuartos();
        this.numeroBanheiros = casasAluguelRequestDTO.numeroBanheiros();
    this.precoAluguel = casasAluguelRequestDTO.precoAluguel();
        this.mobiliada = casasAluguelRequestDTO.mobiliada();
    this.disponivel = casasAluguelRequestDTO.disponivel();
        this.dataDisponibilidade = casasAluguelRequestDTO.dataDisponibilidade();
    this.descricao = casasAluguelRequestDTO.descricao();
    }
}
