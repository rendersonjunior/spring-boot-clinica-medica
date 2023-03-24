package med.clinica.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.clinica.api.dto.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String nome,
        String telefone,
        DadosEndereco endereco) {
}
