package com.lucas.imobiliaria.model.domain.imovel;

import com.lucas.imobiliaria.model.domain.imovel.imagensImovel.ImagensImovel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Imovel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table= "cliente", name = "id_cliente", referencedColumnName = "id")
    private Long idCliente;

    private String nome;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private int numeroQuartos;
    private int numeroBanheiros;
    private double precoAluguel;
    private boolean mobiliada;
    private boolean disponivel;
    private LocalDate dataDisponibilidade;
    private String descricao;
    private String tipoAnuncio;

    @OneToMany
    private List<ImagensImovel> imagensImoveis;

    public Imovel(ImovelRequestDTO imovelRequestDTO) {
        this.nome = imovelRequestDTO.nome();
        this.rua = imovelRequestDTO.rua();
        this.numero = imovelRequestDTO.numero();
        this.complemento = imovelRequestDTO.complemento();
        this.cep = imovelRequestDTO.cep();
        this.cidade = imovelRequestDTO.cidade();
        this.estado = imovelRequestDTO.estado();
        this.bairro = imovelRequestDTO.bairro();
        this.numeroQuartos = imovelRequestDTO.numeroQuartos();
        this.numeroBanheiros = imovelRequestDTO.numeroBanheiros();
    this.precoAluguel = imovelRequestDTO.precoAluguel();
        this.mobiliada = imovelRequestDTO.mobiliada();
    this.disponivel = imovelRequestDTO.disponivel();
        this.dataDisponibilidade = imovelRequestDTO.dataDisponibilidade();
    this.descricao = imovelRequestDTO.descricao();
    this.tipoAnuncio = imovelRequestDTO.tipoAnuncio();
    this.imagensImoveis = imovelRequestDTO.imagensImoveis();
    }
}
