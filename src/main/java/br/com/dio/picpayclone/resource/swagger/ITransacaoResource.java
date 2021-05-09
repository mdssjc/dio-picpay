package br.com.dio.picpayclone.resource.swagger;

import br.com.dio.picpayclone.dto.ErrorDTO;
import br.com.dio.picpayclone.dto.TransacaoDTO;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Api(value = "/transacoes", tags = "transacoes")
@Tag(name = "transacoes", description = "Operações relacionadas a Transações")
public interface ITransacaoResource {

    @ApiOperation(value = "Salvar a transação", nickname = "salvar", response = TransacaoDTO.class, responseContainer = "object",
            authorizations = {@Authorization(value = "basicAuth")}, tags = {"transacoes"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transação salva com sucesso", response = TransacaoDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Transação não encontrada")})
    ResponseEntity<TransacaoDTO> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO, UriComponentsBuilder uriBuilder);

    @ApiOperation(value = "Listar transações por login", nickname = "listar", response = TransacaoDTO.class, responseContainer = "object",
            authorizations = {@Authorization(value = "basicAuth")}, tags = {"transacoes"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transações encontradas com sucesso", response = TransacaoDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Transações não encontradas")})
    ResponseEntity<Page<TransacaoDTO>> listar(@PageableDefault(size = 20) Pageable paginacao, @RequestParam String login);
}
