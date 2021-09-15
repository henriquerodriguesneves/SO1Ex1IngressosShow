package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadIngressos extends Thread{

	Random aleatorio = new Random();
	private Semaphore aguarde;
	private int idLogin;
	private static int qtdIngressos = 300;
	int ingresso = aleatorio.nextInt(4) + 1;
	
	public ThreadIngressos() {
		
	}
	
	public ThreadIngressos (int idLogin, Semaphore aguarde){
		this.idLogin = idLogin;
		this.aguarde = aguarde;
	}
	
	@Override
	public void run () {
		
		acesso();
		try {
			aguarde.acquire();
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			aguarde.release();
		}
	}
	
	private void acesso() {
		System.out.println("Novo acesso de comprador");
		int time = (int)((Math.random() * 2000) + 50);
		
		if (time < 1000) {
			try {
				sleep(time);
				compraDeIngresso();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		else {
			
			System.out.println("Compra não realizada!! (Timeout)");
			
		}
		
	}
	
	private void compraDeIngresso(){
		System.out.println("Login: " + idLogin + " está comprando " + ingresso + " ingressos");
		int time = (int)((Math.random() * 3000) + 1000);
		
		if (time < 2500) {
			try {
				sleep(time);
				validandoCompraIngressos();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		else {
			
			System.out.println("Compra não realizada!! (Timeout)");
			
		}
	}
	
	private void validandoCompraIngressos() {
		
		while (qtdIngressos > 0){
			if (ingresso <= qtdIngressos && qtdIngressos > 0) {
				qtdIngressos = qtdIngressos - ingresso;
				System.out.println("Compra de " + ingresso + " ingressos realizada com sucesso!");
				System.out.println(qtdIngressos + " ingressos disponíveis para compra.");
			}
		}
	}
	
}
