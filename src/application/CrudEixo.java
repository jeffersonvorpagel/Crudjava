package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EixoDao;
import model.entities.Eixo;

public class CrudEixo extends Funcoes {
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
				findIdEixo();
				break;
			case 1:
				findAllEixo();
				break;
			case 2:
				insertEixo();
				break;
			case 3:
				deleteEixo();
				break;
			case 4:
				updateEixo();
				break;
			default:
				System.out.println("escolha invalida");
		}
	}
}
