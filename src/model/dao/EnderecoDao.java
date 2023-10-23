package model.dao;

import model.entities.Eixo;
import model.entities.Endereco;

import java.util.List;

public interface EnderecoDao {

	void insert(Endereco obj);
	void update(Endereco obj);
	void deleteById(Integer id);
	Endereco findById(Integer id);
	List<Endereco> findAll();
}
