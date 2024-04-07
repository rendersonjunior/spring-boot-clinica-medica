package med.clinica.api.controller.medico;

import jakarta.validation.Valid;
import med.clinica.api.domain.medico.Medico;
import med.clinica.api.domain.medico.MedicoRepository;
import med.clinica.api.dto.medico.DadosAtualizacaoMedico;
import med.clinica.api.dto.medico.DadosCadastroMedico;
import med.clinica.api.dto.medico.DadosListagemMedico;
import med.clinica.api.medico.DadosDetalhamentoMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid final DadosCadastroMedico dados,
                                    final UriComponentsBuilder uriComponentsBuilder) {
        final var medico = new Medico(dados);
        repository.save(medico);
        final var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) final Pageable paginacao) {
        final var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid final DadosAtualizacaoMedico dados) {
        final var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable final Long id) {
        final var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable final Long id) {
        final var medico = repository.findByIdAndAtivoTrue(id);

        if (medico.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico.get()));
        }
        return ResponseEntity.notFound().build();
    }

}