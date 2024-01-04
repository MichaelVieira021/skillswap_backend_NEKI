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

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@GetMapping
	public ResponseEntity<List<SkillResponseDTO>> obterTodos(){
		return ResponseEntity.ok(skillService.obterTodos());
	}
	
	@GetMapping("/skillsUserNot")
	public ResponseEntity<List<SkillResponseDTO>> obterTodosSkillUserNot(@RequestParam Long userId){
		return ResponseEntity.ok(skillService.obterTodosUserNot(userId)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<SkillResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(skillService.obterPorId(id));
	}

	@PostMapping
	public ResponseEntity<SkillResponseDTO> adicionar(@RequestBody SkillRequestDTO skillRequest){
		return ResponseEntity.status(201).body(skillService.adicionar(skillRequest));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SkillResponseDTO> atualizar(@PathVariable Long id, @RequestBody SkillRequestDTO skillRequest){
		return ResponseEntity.status(200).body(skillService.atualizar(id, skillRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		skillService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
