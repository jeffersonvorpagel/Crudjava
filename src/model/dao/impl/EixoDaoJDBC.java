package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.EixoDao;
import model.entities.Eixo;

public class EixoDaoJDBC implements EixoDao {

	private Connection conn;
	
	public EixoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Eixo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM eixo WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Eixo obj = new Eixo();
				obj.setId(rs.getInt("Id"));
				obj.setCod(rs.getString("Cod"));
				obj.setDescricao(rs.getString("descricao"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Eixo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM eixo ORDER BY Id");
			rs = st.executeQuery();

			List<Eixo> list = new ArrayList<>();

			while (rs.next()) {
				Eixo obj = new Eixo();
				obj.setId(rs.getInt("Id"));
				obj.setCod(rs.getString("Cod"));
				obj.setDescricao(rs.getString("Descricao"));

				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Eixo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO eixo " +
				"(cod,Descricao) " +
				"VALUES " +
				"(?,?)",
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getCod());
			st.setString(2, obj.getDescricao());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Eixo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE eixo " +
				"SET Cod = ?, " +
				"Descricao = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getCod());
			st.setString(2, obj.getDescricao());
			st.setInt(3, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM eixo WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
