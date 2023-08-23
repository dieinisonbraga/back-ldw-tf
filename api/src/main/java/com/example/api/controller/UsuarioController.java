package com.example.api.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UsuarioDTO;
import com.example.api.exceptions.ErroAutenticacao;
import com.example.api.exceptions.RegraNegocioException;
import com.example.api.model.entity.Usuario;
import com.example.api.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	//método auxiliar para converter ObjetoDTO em uma Entidade
	private Usuario converter(UsuarioDTO dto) {
		Usuario usuario = new Usuario();	
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		
		return usuario;
	}
	
	@PostMapping("/salvarUsuario")
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario entidadeUsuario = converter(dto);
			entidadeUsuario = service.salvarUsuario(entidadeUsuario);
			return ResponseEntity.ok(entidadeUsuario);
			//return ResponseEntity(entidadeUsuario, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
