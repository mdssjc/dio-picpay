package br.com.dio.picpayclone.conversor;

import java.util.ArrayList;
import java.util.List;

public abstract class ConversorBase<E, D> {

    public abstract D converterEntidadeParaDto(E entidade);

    public abstract E converterDtoParaEntidade(D dto);

    public List<D> converterEntidadesParaDtos(List<E> entidades) {
        var dtos = new ArrayList<D>();
        entidades.forEach(entidade -> dtos.add(converterEntidadeParaDto(entidade)));
        return dtos;
    }

    public List<E> converterDtosParaEntidades(List<D> dtos) {
        var entidades = new ArrayList<E>();
        dtos.forEach(dto -> entidades.add(converterDtoParaEntidade(dto)));
        return entidades;
    }
}
