package application;

import model.dao.DaoFactory;
import model.dao.EixoDao;
import model.dao.EnderecoDao;
import model.entities.Eixo;
import model.entities.Endereco;

import java.util.List;
import java.util.Scanner;

public class Funcoes {
// eixo
	static void updateEixo(){
		Scanner sc = new Scanner(System.in);
		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 4: update =======");
		System.out.println("informe o id que deseja atualizar");
		Eixo eixo = eixoDao.findById(sc.nextInt());
		System.out.println("digite o codigo:");
		String cod= sc.next();
		System.out.println("digite a descricao:");
		sc.nextLine();// para limpar o buffer e liberar a proxima pergunta
		String desc= sc.nextLine();
		eixo.setCod(cod);
		eixo.setDescricao(desc);
		eixoDao.update(eixo);
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

		EixoDao eixoDao = DaoFactory.createEixoDao();

		System.out.println("\n=== TEST 3: insert =======");
		System.out.println("digite o codigo");
		String cod= sc.nextLine();
		System.out.println("digite a descricao");
		String desc= sc.nextLine();
		Eixo newEixo = new Eixo(null,cod,desc);
		eixoDao.insert(newEixo);
		System.out.println("Inserted! New id: " + newEixo.getId());
		sc.close();
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
	// inicio endereco
	static void updateEndereco(){
		Scanner sc = new Scanner(System.in);
		EnderecoDao enderecoDao = DaoFactory.createEnderecoDao();

		System.out.println("\n=== TEST 4: update =======");
		System.out.println("informe o id que deseja atualizar");
		Endereco endereco = enderecoDao.findById(sc.nextInt());
		System.out.println("digite o cep");
		sc.nextLine();
		endereco.setCep(sc.nextLine());
		System.out.println("digite a numero");
		endereco.setNumero(sc.nextInt());
		System.out.println("digite o logradouro");
		sc.nextLine();
		endereco.setLogradouro(sc.nextLine());
		System.out.println("digite o complemento");
		endereco.setComplemento(sc.nextLine());
		enderecoDao.update(endereco);
		System.out.println("Update completed");
		sc.close();

	}

	static void findIdEndereco(){
		Scanner sc = new Scanner(System.in);

		EnderecoDao enderecoDao = DaoFactory.createEnderecoDao();

		System.out.println("=== TEST 1: findById =======");
		System.out.println("informe o id a ser exibido");
		Endereco endereco = enderecoDao.findById(sc.nextInt());
		System.out.println(endereco);
		sc.close();

	}

	static void findAllEndereco(){
		EnderecoDao enderecoDao = DaoFactory.createEnderecoDao();

		System.out.println("\n=== TEST 2: findAll =======");
		List<Endereco> list = enderecoDao.findAll();
		for (Endereco endereco : list) {
			System.out.println(endereco);
		}

	}

	static void insertEndereco(){
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		EnderecoDao enderecoDao = DaoFactory.createEnderecoDao();

		System.out.println("\n=== TEST 3: insert =======");
		System.out.println("digite o cep");
		String cep= sc.nextLine();
		System.out.println("digite o numero");
		Integer numero= sc2.nextInt();
		System.out.println("digite o logradouro");
		sc2.nextLine();
		String logradouro= sc2.nextLine();
		System.out.println("digite o complemento");
		String complemento= sc2.nextLine();

		Endereco newEndereco = new Endereco(null,cep,numero,logradouro,complemento);
		enderecoDao.insert(newEndereco);

		System.out.println("Inserted! New id: " + newEndereco.getId());
		sc.close();
		sc2.close();
	}

	static void deleteEndereco(){
		Scanner sc = new Scanner(System.in);

		EnderecoDao enderecoDao = DaoFactory.createEnderecoDao();

		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		enderecoDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
	}
// fim endereco
}

