package br.com.dio.picpayclone.conversor;

import java.util.ArrayList;
import java.util.List;

public interface ConversorBase<E, D> {

    D converterEntidadeParaDto(E entidade);

    E converterDtoParaEntidade(D dto);

    default List<D> converterEntidadesParaDtos(List<E> entidades) {
        var dtos = new ArrayList<D>();
        entidades.forEach(entidade -> dtos.add(converterEntidadeParaDto(entidade)));
        return dtos;
    }

    default List<E> converterDtosParaEntidades(List<D> dtos) {
        var entidades = new ArrayList<E>();
        dtos.forEach(dto -> entidades.add(converterDtoParaEntidade(dto)));
        return entidades;
    }
}
