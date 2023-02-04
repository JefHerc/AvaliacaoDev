package br.com.soc.sistema.exception;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault") 
public class BusinessException extends RuntimeException{
	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
