package br.com.dio.picpayclone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BandeiraCartao {

    VISA("Visa"),
    MASTERCARD("Master Card"),
    ELO("Elo");

    private final String descricao;
}
