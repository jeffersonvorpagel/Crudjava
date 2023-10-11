package model.dao;

import java.util.List;

import model.entities.Eixo;

public interface EixoDao {

	void insert(Eixo obj);
	void update(Eixo obj);
	void deleteById(Integer id);
	Eixo findById(Integer id);
	List<Eixo> findAll();
}
