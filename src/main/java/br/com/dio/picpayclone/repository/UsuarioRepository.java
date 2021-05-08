package br.com.dio.picpayclone.repository;

import br.com.dio.picpayclone.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    @Modifying
    @Query("update Usuario u set u.saldo = u.saldo - ?2 where u.login = ?1")
    void updateDecrementarSaldo(String login, Double valor);

    @Modifying
    @Query("update Usuario u set u.saldo = u.saldo + ?2 where u.login = ?1")
    void updateIncrementarSaldo(String login, Double valor);
}
