package com.example.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.exceptions.ErroAutenticacao;
import com.example.api.exceptions.RegraNegocioException;
import com.example.api.model.entity.Usuario;
import com.example.api.model.repository.UsuarioRepository;
import com.example.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()){
			throw new ErroAutenticacao("Usuario não encontrado para o email informado.");
		}
		if(!usuario.get().getSenha().equals(senha)){
			throw new ErroAutenticacao("Senha inválida.");
		}
		return usuario.get(); // retorna a instancia do usuário
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email");
		}
	}

}
