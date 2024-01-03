package br.com.skillswap.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.skillswap.dto.usuario.UsuarioLoginResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioRequestDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;
import br.com.skillswap.modal.Usuario;
import br.com.skillswap.modal.exceptions.ResourceBadRequestException;
import br.com.skillswap.modal.exceptions.ResourceConflict;
import br.com.skillswap.repository.UsuarioRepository;
import br.com.skillswap.security.JWTService;

@Service
public class UsuarioService {
	private static final String BEARER = "Bearer ";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private JWTService jwtService;

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UsuarioResponseDTO> obterTodos(){
		List<Usuario> usuarios = usuarioRepository.findAll();

		return usuarios
			.stream()
			.map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public UsuarioResponseDTO obterPorId(Long id){
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()){
            throw new ResourceBadRequestException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest){
		uniqueLOGIN(usuarioRequest, 0L);
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
		String senha =  passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(senha);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
		uniqueLOGIN(usuarioRequest, id);
		obterPorId(id);
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
		usuarioModel.setId(id);
		String senha =  passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(senha);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		usuarioRepository.deleteById(id);
	}

	public UsuarioResponseDTO obterPorLogin(String login){
        Optional<Usuario> optUsuario =  usuarioRepository.findByLogin(login);
        
		if(optUsuario.isEmpty()){
            throw new ResourceBadRequestException("Nenhum registro encontrado para o login: " + login);
        }
        return mapper.map(optUsuario.get(),UsuarioResponseDTO.class);
    }

	public void uniqueLOGIN(UsuarioRequestDTO usuarioRequest, Long id){
		Optional<Usuario> optUsuario =  usuarioRepository.findByLogin(usuarioRequest.getLogin());

		if(optUsuario.isPresent() && optUsuario.get().getId() != id){ 
			throw new ResourceConflict("Login já cadastrado!");
		}
	}
	
	public UsuarioLoginResponseDTO logar(String login, String senha){
		try{
			Authentication autenticacao = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha,Collections.emptyList()));
		
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
			String token =  BEARER + jwtService.gerarToken(autenticacao);
			UsuarioResponseDTO usuarioResponse = obterPorLogin(login);
			return new UsuarioLoginResponseDTO(token, usuarioResponse);

		} catch (RuntimeException e){
			throw new ResourceBadRequestException("Login ou senha incorretos.");
		}
        
    }
}
