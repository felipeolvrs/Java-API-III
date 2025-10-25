package com.example.api.exception;

public class EstoqueInsuficienteException extends  RuntimeException{

    public EstoqueInsuficienteException(String mensagem){
        super(mensagem);
    }

    public EstoqueInsuficienteException(){
        super("Estoque insuficiente para realizar a operacão.");
    }
}
