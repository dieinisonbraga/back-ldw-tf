package com.example.api.exceptions;

public class ErroAutenticacao extends RuntimeException {
	
	public ErroAutenticacao(String msg){
		super(msg);
	}
}
