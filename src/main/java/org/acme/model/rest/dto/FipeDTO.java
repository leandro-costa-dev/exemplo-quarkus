package org.acme.model.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@NoArgsConstructor
@Jacksonized
public class FipeDTO {
    private String valor;
    private String marca;
    private String modelo;
    private Integer anoModelo;
    private String combustivel;
    private String codigoFipe;
    private String mesReferencia;
    private Integer tipoVeiculo;
    private String siglaCombustivel;
    private String dataConsulta;

}

//        "valor": "R$ 42.630,00",
//        "marca": "Nissan",
//        "modelo": "MARCH 1.0 12V FlexStart 5p",
//        "anoModelo": 2017,
//        "combustivel": "Gasolina",
//        "codigoFipe": "023146-0",
//        "mesReferencia": "abril de 2023 ",
//        "tipoVeiculo": 1,
//        "siglaCombustivel": "G",
//        "dataConsulta": "quarta-feira, 19 de abril de 2023 21:58