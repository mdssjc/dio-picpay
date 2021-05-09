package br.com.dio.picpayclone.service.impl;

import br.com.dio.picpayclone.conversor.CartaoCreditoConversor;
import br.com.dio.picpayclone.dto.CartaoCreditoDTO;
import br.com.dio.picpayclone.repository.CartaoCreditoRepository;
import br.com.dio.picpayclone.service.ICartaoCreditoService;
import br.com.dio.picpayclone.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartaoCreditoService implements ICartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final CartaoCreditoConversor cartaoCreditoConversor;
    private final IUsuarioService usuarioService;

    @Override
    public CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCreditoDTO cartaoCreditoRetorno = null;
        if (cartaoCreditoDTO.getIsSalva()) {
            var cartaoCredito = cartaoCreditoConversor.converterDtoParaEntidade(cartaoCreditoDTO);
            usuarioService.validar(cartaoCredito.getUsuario());
            var cartaoCreditoSalvo = cartaoCreditoRepository.save(cartaoCredito);
            cartaoCreditoRetorno = cartaoCreditoConversor.converterEntidadeParaDto(cartaoCreditoSalvo);
        }

        return cartaoCreditoRetorno;
    }
}
