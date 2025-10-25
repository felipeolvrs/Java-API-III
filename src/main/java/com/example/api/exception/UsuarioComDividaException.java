package com.example.api.exception;

public class UsuarioComDividaException extends RuntimeException{

    public UsuarioComDividaException(String mensagem){
        super(mensagem);
    }

    public UsuarioComDividaException(){
        super("Usuário possui dívidas e não pode realizar empréstimos.");

    }
}
