package br.com.atividade02.helper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.atividade02.entity.Clientes;
import br.com.atividade02.entity.Pedidos;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistAtividade02"); 
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Atividade02Helper fHelper = new Atividade02Helper(em);
		
		Clientes clientes = new Clientes();
		clientes.setNome("Silas Ianez");
		clientes.setEmail("Silas@gmail.com");
		
		Pedidos pedidos = new Pedidos();
        pedidos.setData(new Date());
        pedidos.setDescricao("Caderno do batman");
        pedidos.setValor(10.98);
		
		System.out.println(clientes.getIdcliente());
		
        fHelper.salvar(clientes);
		fHelper.vincularPedido(clientes.getIdcliente(), pedidos);

	}

}