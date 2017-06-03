package br.com.atividade02.helper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.atividade02.entity.Clientes;
import br.com.atividade02.entity.Pedidos;


public class Main {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistAtividade02"); 
		EntityManager em = entityManagerFactory.createEntityManager();		

		
		Atividade02Helper fHelper = new Atividade02Helper(em);
		
		Clientes clientes = new Clientes();
		clientes.setNome("Papai noel nogueira");
		clientes.setEmail("Papainoel@gmail.com");
		
		Pedidos pedidos = new Pedidos();
        pedidos.setData(new Date());
        pedidos.setDescricao("trenó para 8 renas");
        pedidos.setValor(1987.98);
		
		System.out.println(clientes.getIdcliente());
		
        fHelper.salvar(clientes);
		fHelper.vincularPedido(clientes.getIdcliente(), pedidos);

		System.out.println("==============Lista dos clientes==============");
		listarClientes(em);

		System.out.println("==============Recupera um único cliente==============");
		findOnecliente(em, 1);
		
		System.out.println("==============Lista de pedidos==============");
		listarPedidos(em);
		
	}

	private static void listarClientes(EntityManager em){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		List<Clientes> clientes = dao.listarClientes(); 
		for (Clientes cliente : clientes) {
			System.out.println(cliente.getNome() + " - " + cliente.getEmail());
		}
	}
	
	private static void findOnecliente(EntityManager em, Integer idcliente){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		Clientes f = dao.findOnecliente(idcliente); 
		System.out.println(f.getNome() + ": " + f.getEmail());
	} 
	
	private static void listarPedidos(EntityManager em){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		List<Pedidos> pedidos = dao.listarPedidos(); 
		for (Pedidos pedido : pedidos) {
			System.out.println("Pedido do cliente: " + pedido.getClientes().getNome() + " - " + pedido.getDescricao() + " no valor de " + pedido.getValor() );
		}
	}
		
	

}