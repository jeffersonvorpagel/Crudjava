package application;

import model.dao.DaoFactory;
import model.dao.EixoDao;
import model.entities.Eixo;

import java.util.List;
import java.util.Scanner;

public class Funcoes {
// eixo
	static void updateEixo(){
		Scanner sc = new Scanner(System.in);
		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 4: update =======");
		System.out.println("informe o id que deseja atualizar");
		Eixo dep2 = eixoDao.findById(sc.nextInt());
		System.out.println("digite o codigo");
		dep2.setCod(sc.next());
		System.out.println("digite a descricao");
		dep2.setDescricao(sc.next());
		eixoDao.update(dep2);
		System.out.println("Update completed");
		sc.close();

	}

	static void findIdEixo(){
		Scanner sc = new Scanner(System.in);

		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("=== TEST 1: findById =======");
		System.out.println("informe o id a ser exibido");
		Eixo dep = eixoDao.findById(sc.nextInt());
		System.out.println(dep);
		sc.close();

	}

	static void findAllEixo(){
		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 2: findAll =======");
		List<Eixo> list = eixoDao.findAll();
		for (Eixo d : list) {
			System.out.println(d);
		}

	}

	static void insertEixo(){
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 3: insert =======");
		System.out.println("digite o codigo");
		String cod= sc.next();
		System.out.println("digite a descricao");
		String desc= sc2.next();
		Eixo newEixo = new Eixo(null,cod,desc);
		eixoDao.insert(newEixo);
		System.out.println("Inserted! New id: " + newEixo.getId());
		sc.close();
		sc2.close();
	}

	static void deleteEixo(){
		Scanner sc = new Scanner(System.in);

		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		eixoDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
	}
// fim eixo
}
