package br.com.skillswap.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.skillswap.dto.userSkill.UserSkillResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioLoginRequestDTO;
import br.com.skillswap.dto.usuario.UsuarioLoginResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioRequestDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;
import br.com.skillswap.modal.exceptions.ResourceBadRequestException;
import br.com.skillswap.security.JWTService;
import br.com.skillswap.service.UserSkillService;
import br.com.skillswap.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UserSkillService userSkillService;
	
	@Autowired
	private JWTService jwtService;
	
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
		return ResponseEntity.ok(usuarioService.obterTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.obterPorId(id));
	}

	@GetMapping("/login/{login}")
	public ResponseEntity<UsuarioResponseDTO> obterPorLogin(@PathVariable String login){
		return ResponseEntity.ok(usuarioService.obterPorLogin(login));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuarioRequest){

		if(usuarioRequest.getLogin() == null || usuarioRequest.getLogin().length() < 1){
			throw new  ResourceBadRequestException("Login não pode estar vazio.");
		}else if(usuarioRequest.getLogin().length() > 15){
			throw new  ResourceBadRequestException("Ops! Parece que seu login está um pouco longo. MAX:15");
		}else if(usuarioRequest.getLogin().length() < 4){
			throw new  ResourceBadRequestException("Ops! Parece que seu login está um pouco curto. MIN:4");
		}

		if(usuarioRequest.getSenha() == null || usuarioRequest.getSenha().length() < 1){
			throw new  ResourceBadRequestException("Senha não pode estar vazio.");
		}else if(usuarioRequest.getSenha().length() > 15){
			throw new  ResourceBadRequestException("Ops! Parece que sua senha está um pouco longa. MAX:15");
		}else if(usuarioRequest.getSenha().length() < 4){
			throw new  ResourceBadRequestException("Ops! Parece que sua senha está um pouco curta. MIN:4");
		}

		return ResponseEntity.status(201).body(usuarioService.adicionar(usuarioRequest));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequest){

		if(usuarioRequest.getLogin() == null || usuarioRequest.getLogin().length() < 1){
			throw new  ResourceBadRequestException("Login não pode estar vazio.");
		}else if(usuarioRequest.getLogin().length() > 15){
			throw new  ResourceBadRequestException("Ops! Parece que seu login está um pouco longo. MAX:15");
		}else if(usuarioRequest.getLogin().length() < 4){
			throw new  ResourceBadRequestException("Ops! Parece que seu login está um pouco curto. MIN:4");
		}

		if(usuarioRequest.getSenha() == null || usuarioRequest.getSenha().length() < 1){
			throw new  ResourceBadRequestException("Senha não pode estar vazio.");
		}else if(usuarioRequest.getSenha().length() > 15){
			throw new  ResourceBadRequestException("Ops! Parece que sua senha está um pouco longa. MAX:15");
		}else if(usuarioRequest.getSenha().length() < 4){
			throw new  ResourceBadRequestException("Ops! Parece que sua senha está um pouco curta. MIN:4");
		}

		return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		usuarioService.deletar(id);
		return ResponseEntity.status(204).build();
	}
	
	@PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuariologinRequest){
//        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuariologinRequest.getLogin(), usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(usuarioService.logar(usuariologinRequest.getLogin(), usuariologinRequest.getSenha()));
    }
	
	@PostMapping("/validar/token")
    public ResponseEntity<String> validarToken(@RequestParam String token) {
	    if (token.startsWith("Bearer ")) {
	        token = token.substring(7);
	    }
	    
        if (jwtService.validarToken(token)) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.ok("Token inválido ou expirado");
        }
    }
	
	@GetMapping("/skillsUser")
	public ResponseEntity<List<UserSkillResponseDTO>> obterTodosSkillUser(@RequestParam Long userId){
		return ResponseEntity.ok(userSkillService.obterTodos(userId)); 
	}
		
	@PostMapping("/adicionar/skill")
	public ResponseEntity<UserSkillResponseDTO> adicionarSkill(@RequestParam Long idUser, @RequestParam Long idSkill, @RequestParam Long level){
		return ResponseEntity.status(200).body(userSkillService.adicionar(idUser, idSkill, level));
	}
	
	@PatchMapping("/levelUp/skill/{id}")
	public ResponseEntity<UserSkillResponseDTO> levelUp(@PathVariable Long id){
		
		return ResponseEntity.status(200).body(userSkillService.levelUp(id));		
	}
	
	@PatchMapping("/levelDown/skill/{id}")
	public ResponseEntity<UserSkillResponseDTO> levelDown(@PathVariable Long id){
		
		return ResponseEntity.status(200).body(userSkillService.levelDown(id));		
	}
	
	@DeleteMapping("/deletar/skill/{id}")
	public ResponseEntity<?> deletarSkillUser(@PathVariable Long id){
		userSkillService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
