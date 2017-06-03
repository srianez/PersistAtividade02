package br.com.atividade02.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.atividade02.entity.Clientes;
import br.com.atividade02.entity.Pedidos;



public class Atividade02Helper {
	private EntityManager em;

	public Atividade02Helper(EntityManager em) {
		this.em = em;
	}

	public String salvar(Clientes clientes) {
		try {
			em.getTransaction().begin();
			em.persist(clientes);
			em.getTransaction().commit();
			return "Clientes salvo";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String vincularPedido(int idCliente, Pedidos pedidos) {
		try {
			Clientes c = em.find(Clientes.class, idCliente);
			pedidos.setClientes(c);
			c.getPedidos().add(pedidos);
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			return "Inclusao dos pedidos realizada";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//JPQL: Usando Query
	@SuppressWarnings("unchecked")
	public List<Clientes> listarClientes(){
		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c", Clientes.class); 
		return tQuery.getResultList();
	}	
	
	//Busca o cliente por ID
	public Clientes findOnecliente(Integer idcliente){		
		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c where idcliente = :idcliente", Clientes.class);
		tQuery.setParameter("idcliente", idcliente); 
		return tQuery.getSingleResult(); 	
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedidos> listarPedidos(){
		TypedQuery<Pedidos> tQuery = em.createQuery("select p from Pedidos p", Pedidos.class); 
		return tQuery.getResultList();
	}
}