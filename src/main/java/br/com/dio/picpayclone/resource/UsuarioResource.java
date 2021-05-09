package br.com.dio.picpayclone.resource;

import br.com.dio.picpayclone.dto.UsuarioDTO;
import br.com.dio.picpayclone.resource.swagger.IUsuarioResource;
import br.com.dio.picpayclone.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioResource extends ResourceBase<UsuarioDTO> implements IUsuarioResource {

    private final IUsuarioService usuarioService;

    @GetMapping("/{login}/saldo")
    public ResponseEntity<UsuarioDTO> consultarSaldo(@PageableDefault(page = 0, size = 20) Pageable paginacao, @PathVariable String login) {
        var usuarioDTO = usuarioService.consultar(login);
        return responderSucessoComItem(usuarioDTO);
    }

    @GetMapping("/contatos")
    @Override
    public ResponseEntity<List<UsuarioDTO>> listar(@RequestParam String login) {
        var usuarios = usuarioService.listar(login);
        return responderListaDeItens(usuarios);
    }

    @GetMapping("/{login}")
    public ResponseEntity<UsuarioDTO> consultar(@PathVariable String login) {
        var usuario = usuarioService.consultar(login);
        return responderSucessoComItem(usuario);
    }
}
