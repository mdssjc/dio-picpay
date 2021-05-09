package br.com.dio.picpayclone.service.impl;

import br.com.dio.picpayclone.conversor.TransacaoConversor;
import br.com.dio.picpayclone.dto.TransacaoDTO;
import br.com.dio.picpayclone.modelo.Transacao;
import br.com.dio.picpayclone.repository.TransacaoRepository;
import br.com.dio.picpayclone.service.ICartaoCreditoService;
import br.com.dio.picpayclone.service.ITransacaoService;
import br.com.dio.picpayclone.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransacaoService implements ITransacaoService {

    private final TransacaoConversor transacaoConversor;
    private final TransacaoRepository transacaoRepository;
    private final ICartaoCreditoService cartaoCreditoService;
    private final IUsuarioService usuarioService;

    @Override
    public TransacaoDTO processar(TransacaoDTO transacaoDTO) {
        var transacaoSalva = salvar(transacaoDTO);
        cartaoCreditoService.salvar(transacaoDTO.getCartaoCredito());
        usuarioService.atualizarSaldo(transacaoSalva, transacaoDTO.getIsCartaoCredito());
        return transacaoConversor.converterEntidadeParaDto(transacaoSalva);
    }

    private Transacao salvar(TransacaoDTO transacaoDTO) {
        var transacao = transacaoConversor.converterDtoParaEntidade(transacaoDTO);
        usuarioService.validar(transacao.getDestino(), transacao.getDestino());
        return transacaoRepository.save(transacao);
    }

    @Override
    public Page<TransacaoDTO> listar(Pageable paginacao, String loginUsuario) {
        var transacaoes = transacaoRepository.findByOrigem_LoginOrDestino_Login(loginUsuario, loginUsuario, paginacao);
        return transacaoConversor.converterPageEntidadeParaDto(transacaoes);
    }
}
