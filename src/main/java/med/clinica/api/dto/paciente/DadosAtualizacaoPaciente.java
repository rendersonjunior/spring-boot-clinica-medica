package med.clinica.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.clinica.api.dto.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,

        String nome,
        String telefone,

        @Valid
        DadosEndereco endereco
) {
}
