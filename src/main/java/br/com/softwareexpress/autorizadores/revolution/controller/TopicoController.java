package br.com.softwareexpress.autorizadores.revolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import br.com.softwareexpress.autorizadores.revolution.model.Topico;
import br.com.softwareexpress.autorizadores.revolution.repository.CursoRepository;
import br.com.softwareexpress.autorizadores.revolution.repository.TopicoRepository;
import br.com.softwareexpress.autorizadores.revolution.dto.TopicoDto;
import br.com.softwareexpress.autorizadores.revolution.dto.TopicoForm;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	public TopicoRepository topicoRepository;
	
	@Autowired
	public CursoRepository cursoRepository;
	
    @GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			//List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			List<Topico> topicos = topicoRepository.listarPorNomeCurso(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}
    
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
    	Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	
    	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    	return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
    
}
