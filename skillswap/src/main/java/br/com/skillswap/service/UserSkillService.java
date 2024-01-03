package br.com.skillswap.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.skillswap.dto.userSkill.UserSkillResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;
import br.com.skillswap.modal.Skill;
import br.com.skillswap.modal.UserSkill;
import br.com.skillswap.modal.Usuario;
import br.com.skillswap.modal.exceptions.ResourceBadRequestException;
import br.com.skillswap.repository.UserSkillRepository;

@Service
public class UserSkillService {
	
	@Autowired
	private UserSkillRepository userSkillRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UserSkillResponseDTO> obterTodos(Long userId){
		UsuarioResponseDTO userResponse = usuarioService.obterPorId(userId);
		List<UserSkill> skillsUser = userSkillRepository.findByUsuarioOrderById(mapper.map(userResponse, Usuario.class));
		
		for(UserSkill uSkill : skillsUser) {
			if(uSkill.getLevel() > 1) {
				Skill skill = uSkill.getSkill();
				skill.setAtkAdicional((int) (skill.getAtkAdicional() + (skill.getAtkAdicional() * (0.2 * uSkill.getLevel()))));
				skill.setTecAmp((int) (skill.getTecAmp() + (skill.getTecAmp() * (0.2 * uSkill.getLevel()))));
				skill.setResfriamento(skill.getResfriamento() - (skill.getResfriamento() * (0.005 * uSkill.getLevel())));
				skill.setDuracao(skill.getDuracao() + (skill.getDuracao() * (0.04 * uSkill.getLevel())));
			}
		}
		return skillsUser
			.stream()
			.map(skillUser -> mapper.map(skillUser, UserSkillResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public UserSkillResponseDTO obterPorId(Long id){
		Optional<UserSkill> optSkillUser = userSkillRepository.findById(id);
		
		if(optSkillUser.isEmpty()){
            throw new ResourceBadRequestException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optSkillUser.get(), UserSkillResponseDTO.class);
	}
	
	public UserSkillResponseDTO adicionar(Long idUser, Long idSkill, Long level){
		Usuario user = mapper.map(usuarioService.obterPorId(idUser), Usuario.class);
		Skill skill = mapper.map(skillService.obterPorId(idSkill), Skill.class);
		UserSkill userSkillModel = new UserSkill();
		userSkillModel.setSkill(skill);
		userSkillModel.setUsuario(user);
		userSkillModel.setLevel(level);
		userSkillModel = userSkillRepository.save(userSkillModel);
		
		return mapper.map(userSkillModel, UserSkillResponseDTO.class);
	}
	
	public UserSkillResponseDTO levelUp(Long id) {
		UserSkill userSkill = mapper.map(obterPorId(id), UserSkill.class);
		userSkill.setId(id);
		
		if(userSkill.getLevel() == 20) {
			throw new ResourceBadRequestException("Level maximo");
		}
		
		userSkill.setLevel(userSkill.getLevel() + 1);
		userSkill = userSkillRepository.save(userSkill);
		
		return mapper.map(userSkill, UserSkillResponseDTO.class);
	}
	
	public UserSkillResponseDTO levelDown(Long id) {
		UserSkill userSkill = mapper.map(obterPorId(id), UserSkill.class);
		userSkill.setId(id);
		
		if(userSkill.getLevel() == 1) {
			throw new ResourceBadRequestException("Level mínimo");
		}
		
		userSkill.setLevel(userSkill.getLevel() - 1);
		userSkill = userSkillRepository.save(userSkill);
		
		return mapper.map(userSkill, UserSkillResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		userSkillRepository.deleteById(id);
	}
}
