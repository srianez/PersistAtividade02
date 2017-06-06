package br.com.atividade02.helper;

import java.util.Date;
import java.util.List;

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
		
		//alimenta objeto cliente para novo cadastro
		Clientes clientes = new Clientes();
		clientes.setNome("Papai noel nogueira");
		clientes.setEmail("Papainoel@gmail.com");
		
		//id do cliente gerado
		//System.out.println(clientes.getIdcliente());
		
		//alimenta objeto pedidos para novo cadastro
		Pedidos pedidos = new Pedidos();
        pedidos.setData(new Date());
        pedidos.setDescricao("Renas de papai noel velozes (promoção)");
        pedidos.setValor(1087.99);
		
		//persiste os cadastros no BD
        fHelper.cadastrarCliente(clientes);
        
        //vincula um pedido pro cliente que acabou de ser cadastrado
		fHelper.cadastrarPedido(clientes.getIdcliente(), pedidos);

		
		System.out.println("==============Lista dos clientes==============");
		listarClientes(em);

		System.out.println("==============Recupera um único cliente==============");
		findOnecliente(em, 1);
		
		System.out.println("==============Lista de pedidos==============");
		listarPedidos(em);
		
	}

	//consulta que lista todos os clientes cadastrados
	private static void listarClientes(EntityManager em){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		List<Clientes> clientes = dao.listarClientes(); 
		for (Clientes cliente : clientes) {
			System.out.println(cliente.getNome() + " - " + cliente.getEmail());
		}
	}
	
	//consulta que busca um único cliente pelo ID
	private static void findOnecliente(EntityManager em, Integer idcliente){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		Clientes f = dao.findOnecliente(idcliente); 
		System.out.println(f.getNome() + ": " + f.getEmail());
	} 
	
	//consulta que lista todos os pedidos
	private static void listarPedidos(EntityManager em){ 
		Atividade02Helper dao = new Atividade02Helper(em);
		List<Pedidos> pedidos = dao.listarPedidos(); 
		for (Pedidos pedido : pedidos) {
			System.out.println("Pedido do cliente: " + pedido.getClientes().getNome() + " - " + pedido.getDescricao() + " no valor de " + pedido.getValor() );
		}
	}
		
	

}