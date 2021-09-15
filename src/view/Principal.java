package view;

import controller.ThreadIngressos;
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore aguarde = new Semaphore(1);
		
		for (int login = 1; login <= 300; login++) {
			Thread compraIngresso = new ThreadIngressos(login, aguarde);
			compraIngresso.start();
		}

	}

}
