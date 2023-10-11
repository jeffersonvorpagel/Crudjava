package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EixoDao;
import model.entities.Eixo;

public class CrudEixo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		EixoDao eixoDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TEST 1: findById =======");
		Eixo dep = eixoDao.findById(2);
		System.out.println(dep);

		System.out.println("\n=== TEST 2: findAll =======");
		List<Eixo> list = eixoDao.findAll();
		for (Eixo d : list) {
			System.out.println(d);
		}

		System.out.println("\n=== TEST 3: insert =======");
		Eixo newEixo = new Eixo(null, "agrfoi","agrfoi");
		eixoDao.insert(newEixo);
		System.out.println("Inserted! New id: " + newEixo.getId());

		System.out.println("\n=== TEST 4: update =======");
		Eixo dep2 = eixoDao.findById(5);
		dep2.setCod("update");
		dep2.setDescricao("update");
		eixoDao.update(dep2);
		System.out.println("Update completed");


		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		eixoDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
	}
}
