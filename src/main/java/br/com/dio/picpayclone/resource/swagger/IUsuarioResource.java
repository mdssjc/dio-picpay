package br.com.dio.picpayclone.resource.swagger;

import br.com.dio.picpayclone.dto.ErrorDTO;
import br.com.dio.picpayclone.dto.UsuarioDTO;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/usuarios", tags = "usuarios")
@Tag(name = "usuarios", description = "Operações relacionadas a Usuários")
public interface IUsuarioResource {

    @ApiOperation(value = "Consultar saldo de um usuário por login", nickname = "consultarSaldo", response = UsuarioDTO.class, responseContainer = "object",
            authorizations = {@Authorization(value = "basicAuth")}, tags = {"usuarios"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saldo consultado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado")})
    ResponseEntity<UsuarioDTO> consultarSaldo(@PageableDefault(size = 20) Pageable paginacao, @PathVariable String login);

    @ApiOperation(value = "Consultar contatos de um usuário por login", nickname = "listarContatos", response = UsuarioDTO.class, responseContainer = "List",
            authorizations = {@Authorization(value = "basicAuth")}, tags = {"usuarios"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contatos encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuários não encontrados")})
    ResponseEntity<List<UsuarioDTO>> listar(@RequestParam String login);

    @ApiOperation(value = "Consultar usuário por login", nickname = "consultarUsuarios", response = UsuarioDTO.class, responseContainer = "object",
            authorizations = {@Authorization(value = "basicAuth")}, tags = {"usuarios"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso", response = UsuarioDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado")})
    ResponseEntity<UsuarioDTO> consultar(@PathVariable String login);
}
