package util;

import java.util.LinkedList;

public class StringErro {
	
	public static String tipoInvalido(String token, String tipo){
		return "Tipo de " + token + " deveria ser " + tipo;
	}
	
	public static String invalidoCollectionLiteral(){
		return "Tipos dos argumentos da Collection nao sao iguais.";
	}
	
	public static String tipoDiferentes(String token1, String token2, String tipo1, String tipo2){
		return "Tipo de " + token1 + " eh diferente do tipo de " + token2 + ": [" + 
		tipo1 + "] " + "[" + tipo2 + "] respectivamente.";
	}
	
	public static String naoExiste(String element, String token){
		return element + " com nome " + token + " nao existe.";
	}
	
	public static String jaExiste(String element, String token){
		return element + " com nome " + token + " ja existe.";
	}
	
	public static String parametrosInvalidos(String token, LinkedList<String> esperado, LinkedList<String> recebido){
		return "Os parametros passados na funcao " + token + " sao invalidos. \n" +
				"Esperado " + esperado + " mas foi passado " + recebido;
	}
	
	public static String contextoInvalido(String token){
		return "Contexto " + token + " nao existe.";
	}
	
	public static String packageInvalido(String token){
		return "Package " + token + " nao existe.";
	}
	
	public static String tipoConsultaInvalida(String token, String tipoDeveria, String tipoEh){
		return "Tipo da consulta " + token + " deveria ser " + tipoDeveria + " mas eh " + tipoEh;
	}
	
	public static String funcaoOperacaoInvalida(String token){
		return token + " deveria ser uma operacao de Collection.";
	}
	
	public static String atributoOperacaoInvalida(String token){
		return token + " deveria ser um atributo ou funcao.";
	}
	
}
