package br.com.dio.picpayclone.modelo;

import br.com.dio.picpayclone.enums.BandeiraCartao;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CARTAO_CREDITO")
public class CartaoCredito extends EntidadeBase {

    @Column(name = "CC_NUMERO", nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "CC_BANDEIRA", nullable = false)
    private BandeiraCartao bandeira;

    @Column(name = "CC_TOKEN")
    private String numeroToken;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CC_USUARIO_ID", nullable = false)
    private Usuario usuario;
}
