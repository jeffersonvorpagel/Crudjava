package model.dao;

import db.DB;
import model.dao.impl.EixoDaoJDBC;

public class DaoFactory {

	
	public static EixoDao createEixoDao() {
		return new EixoDaoJDBC(DB.getConnection());
	}
}
