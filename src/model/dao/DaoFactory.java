package model.dao;

import db.DB;
import model.dao.impl.EixoDaoJDBC;
import model.dao.impl.EnderecoDaoJDBC;

public class DaoFactory {

	
	public static EixoDao createEixoDao() {
		return new EixoDaoJDBC(DB.getConnection());
	}
	public static EnderecoDao createEnderecoDao() { return new EnderecoDaoJDBC(DB.getConnection());}

}
