package application;

import java.util.Scanner;

public class CrudEndereco extends Funcoes {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha uma das opções abaixo");
		System.out.println("0 - findId\n" +
				"1 - findAll\n" +
				"2 - insert\n" +
				"3 - delete\n" +
				"4 - update\n" +
				"Digite o numero:");
		Integer escolha = sc.nextInt();

		switch (escolha){
			case 0:
				findIdEndereco();
				break;
			case 1:
				findAllEndereco();
				break;
			case 2:
				insertEndereco();
				break;
			case 3:
				deleteEndereco();
				break;
			case 4:
				updateEndereco();
				break;
			default:
				System.out.println("escolha invalida");
		}
	}
}
