package com.fatec.optimus.NivelEmpresa;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class GeradorVendas 
{
	/*
    public static void main( String[] args )
    {
    	HashMap<String, Integer> qtdEquipes = new HashMap<String, Integer>();
    	qtdEquipes.put("Logistica", 2);
    	qtdEquipes.put("Comercial", 3);
    	
    	HashMap<String, Integer> qtdERBsRegiao = new HashMap<String, Integer>();
    	qtdERBsRegiao.put("Sul/Sudeste", 10);
    	qtdERBsRegiao.put("Nordeste", 10);
    	qtdERBsRegiao.put("Norte", 100);
    	
    	//calcularNivelEmpresa(1, qtdEquipes, qtdERBsRegiao, 23, 104000);
    }
    */
    private static Integer calcularNivelEmpresa(Integer idEmpresa, HashMap<String, Integer> qtdEquipes, HashMap<String, Integer> qtdERBsRegiao, Integer qtdArmazem, Integer qtdProdutosEstoque, List<Double> precosVendaMercado, Double precoVendaProduto) {
    	Integer nivelMercado = 1;
    	
    	Integer mediaProdutosEquipe = produtosEquipe(qtdEquipes, qtdProdutosEstoque);
    	
    	Integer mediaArmazemEquipe = armazemProduto(qtdEquipes, qtdArmazem);
    	
    	Integer mediaProdutoERB = produtoERB(qtdERBsRegiao, qtdProdutosEstoque);
    	
    	Double mediaPreco = mediaPreco(precosVendaMercado, precoVendaProduto);
    	
    	if (mediaProdutosEquipe > 0) {
    		nivelMercado++;
    	}
    	if (mediaArmazemEquipe > 0) {
    		nivelMercado++;
    	}
    	if (mediaProdutoERB > 0) {
    		nivelMercado++;
    	}
    	if (mediaPreco > 800) {
    		nivelMercado+=2;
    	}
    	
    	System.out.println(nivelMercado);
    	return nivelMercado;
    }
    
    private static Double mediaPreco(List<Double> precosVendaMercado, Double precoVendaProduto) {
    	int precoMedio = 0;
    	for (Double preco : precosVendaMercado) {
			precoMedio += preco;
		}
    	
    	precoMedio = precoMedio / precosVendaMercado.size();
    	
    	return Math.abs(precoMedio - precoVendaProduto);
    }
    
    private static Integer produtoERB(HashMap<String, Integer> qtdERBsRegiao, Integer qtdProdutosEstoque) {
    	Integer mediaProdutosERB = 0;
    	
    	for (String regiao : qtdERBsRegiao.keySet()) {	
    		Integer produtoERB = 0;
    		if (regiao.equals("Sul/Sudeste"))
    			produtoERB = qtdERBsRegiao.get(regiao) * 1307;
    		
    		if (regiao.equals("Nordeste"))
    			produtoERB = qtdERBsRegiao.get(regiao) * 1727;
    		
    		if (regiao.equals("Norte"))
    			produtoERB = qtdERBsRegiao.get(regiao) * 2346;
    		
    		mediaProdutosERB = qtdProdutosEstoque - produtoERB;
    		
    		if (mediaProdutosERB <= 0) {
    			return mediaProdutosERB;
    		}
    		
    		qtdProdutosEstoque -= produtoERB;
    	}
    	
    	return mediaProdutosERB;
    	
    }
    
    private static Integer produtosEquipe(HashMap<String, Integer> qtdEquipes, Integer qtdProdutosEstoque) {
    	Integer mediaProdutosEquipe = 0;
    	
    	for (String tipoEquipe : qtdEquipes.keySet()) {	
    		Integer produtoEquipe = 0;
    		if (tipoEquipe.equals("Logistica"))
    			produtoEquipe = qtdEquipes.get(tipoEquipe) * 50000;
    		
    		if (tipoEquipe.equals("Comercial"))
    			produtoEquipe = qtdEquipes.get(tipoEquipe) * 5000;
    		
    		mediaProdutosEquipe = qtdProdutosEstoque - produtoEquipe;
    		
    		if (mediaProdutosEquipe <= 0) {
    			return mediaProdutosEquipe;
    		}
    		
    		qtdProdutosEstoque -= produtoEquipe;
    	}
    	
		return mediaProdutosEquipe;
    	
    }
    
    private static Integer armazemProduto(HashMap<String, Integer> qtdEquipes, Integer qtdArmazem) {
    	Integer mediaArmazemEquipe = 0;
    	
    	for (String tipoEquipe : qtdEquipes.keySet()) {	
    		Integer produtoEquipe = 0;
    		if (tipoEquipe.equals("Logistica"))
    			produtoEquipe = qtdEquipes.get(tipoEquipe) * 10;
    		
    		if (tipoEquipe.equals("Comercial"))
    			produtoEquipe = qtdEquipes.get(tipoEquipe) * 1;
    		
    		mediaArmazemEquipe = qtdArmazem - produtoEquipe;
    		
    		if (mediaArmazemEquipe <= 0) {
    			return mediaArmazemEquipe;
    		}
    		
    		qtdArmazem -= produtoEquipe;
    	}
    	
		return mediaArmazemEquipe;

    	
    }
    
    private Integer definirQtdVendas(Integer nivelEmpresa) {
    	Random random = new Random();
    	
    	int vendas = random.nextInt(500000 / nivelEmpresa);
    	
    	return vendas;
    }
    
    
    public Integer executarVendas(Integer idEmpresa, HashMap<String, Integer> qtdEquipes, HashMap<String, Integer> qtdERBsRegiao, Integer qtdArmazem, Integer qtdProdutosEstoque, List<Double> precosVendaMercado, Double precoVendaProduto) {
    	Integer nivelEmpresa = calcularNivelEmpresa(idEmpresa, qtdEquipes, qtdERBsRegiao, qtdArmazem, qtdProdutosEstoque, precosVendaMercado, precoVendaProduto);
    	Integer vendas = definirQtdVendas(nivelEmpresa);
    	
    	return vendas;
    }
}
