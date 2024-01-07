package br.com.skillswap.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.skillswap.dto.skill.SkillRequestDTO;
import br.com.skillswap.dto.skill.SkillResponseDTO;
import br.com.skillswap.dto.userSkill.UserSkillResponseDTO;
import br.com.skillswap.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/skills", produces = {"application/json"})
@Tag(name = "Funcionalidades skills")
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@Operation(summary = "Buscar todas skills", method = "GET")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@GetMapping(name="BUSCAR SKILLS")
	public ResponseEntity<List<SkillResponseDTO>> obterTodos(){
		return ResponseEntity.ok(skillService.obterTodos());
	}
	
	@Operation(summary = "Buscar todas skills que usuario não possui", method = "GET")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@GetMapping(value= "/skillsUserNot", name="SKILL USER NÃO POSSUI")
	public ResponseEntity<List<SkillResponseDTO>> obterTodosSkillUserNot(@RequestParam Long userId){
		return ResponseEntity.ok(skillService.obterTodosUserNot(userId)); 
	}

	@Operation(summary = "Buscar skill por ID", method = "GET")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@GetMapping(value="/{id}", name="BUSCAR SKILL POR ID")
	public ResponseEntity<SkillResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(skillService.obterPorId(id));
	}

	@Operation(summary = "Adicionar skill", method = "POST")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Adicionada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@PostMapping(name="ADICIONAR SKILL")
	public ResponseEntity<SkillResponseDTO> adicionar(@RequestBody SkillRequestDTO skillRequest){
		return ResponseEntity.status(201).body(skillService.adicionar(skillRequest));
	}
	
	@Operation(summary = "Atualizar skill", method = "PUT")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Atualizado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@PutMapping(value="/{id}", name="ATUALIZAR SKILL")
	public ResponseEntity<SkillResponseDTO> atualizar(@PathVariable Long id, @RequestBody SkillRequestDTO skillRequest){
		return ResponseEntity.status(200).body(skillService.atualizar(id, skillRequest));
	}
	
	
	@Operation(summary = "Deletar skill", method = "DELETE")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autenticado"),
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		skillService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
