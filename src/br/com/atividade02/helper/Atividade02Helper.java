package br.com.atividade02.helper;

import javax.persistence.EntityManager;

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
}