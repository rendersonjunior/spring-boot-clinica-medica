package med.clinica.api.dto.paciente;

import med.clinica.api.domain.paciente.Paciente;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
