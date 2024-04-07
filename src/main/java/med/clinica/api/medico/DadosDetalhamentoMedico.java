package med.clinica.api.medico;

import med.clinica.api.domain.endereco.Endereco;
import med.clinica.api.domain.medico.Medico;
import med.clinica.api.dto.medico.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }

}
