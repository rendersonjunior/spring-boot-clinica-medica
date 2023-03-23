package med.voll.api.dto.paciente;

import med.voll.api.domain.paciente.Paciente;

public record DadosListagemPaciente(
        String nome,
        String email,
        String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
