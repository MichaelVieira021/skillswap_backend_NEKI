package br.com.skillswap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.skillswap.dto.skill.SkillRequestDTO;
import br.com.skillswap.dto.skill.SkillResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;
import br.com.skillswap.modal.Skill;
import br.com.skillswap.modal.UserSkill;
import br.com.skillswap.modal.Usuario;
import br.com.skillswap.modal.exceptions.ResourceBadRequestException;
import br.com.skillswap.repository.SkillRepository;
import br.com.skillswap.repository.UserSkillRepository;

@Service
public class SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private UserSkillRepository userSkillRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<SkillResponseDTO> obterTodos(){
		List<Skill> skills = skillRepository.findAll();

		return skills
			.stream()
			.map(skill -> mapper.map(skill, SkillResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public List<SkillResponseDTO> obterTodosUserNot(Long userId){
		UsuarioResponseDTO userResponse = usuarioService.obterPorId(userId);
		List<Skill> skills = skillRepository.findAll();
//		List<Skill> skillsDoUsuario = userSkillRepository.findBySkill(mapper.map(userResponse, Usuario.class));
		List<UserSkill> skillsUser = userSkillRepository.findByUsuarioOrderById(mapper.map(userResponse, Usuario.class));
		
	    List<Skill> skillsUsuario = skillsUser.stream()
	            .map(UserSkill::getSkill)
	            .collect(Collectors.toList());
	    
	    List<Skill> skillsNaoPresentes = skills.stream()
	            .filter(skill -> !skillsUsuario.contains(skill))
	            .collect(Collectors.toList());
		
		
		return skillsNaoPresentes
			.stream()
			.map(skill -> mapper.map(skill, SkillResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public SkillResponseDTO obterPorId(Long id){
		Optional<Skill> optSkill = skillRepository.findById(id);
		
		if(optSkill.isEmpty()){
            throw new ResourceBadRequestException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optSkill.get(), SkillResponseDTO.class);
	}
	
	public SkillResponseDTO adicionar(SkillRequestDTO skillRequest){
		Skill skillModel = mapper.map(skillRequest, Skill.class);
		skillModel = skillRepository.save(skillModel);
		
		return mapper.map(skillModel, SkillResponseDTO.class);
	}
	
	public SkillResponseDTO atualizar(Long id, SkillRequestDTO skillRequest){
		obterPorId(id);
		Skill skillModel = mapper.map(skillRequest, Skill.class);
		skillModel.setId(id);
		skillModel = skillRepository.save(skillModel);
		
		return mapper.map(skillModel, SkillResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		skillRepository.deleteById(id);
	}
}
