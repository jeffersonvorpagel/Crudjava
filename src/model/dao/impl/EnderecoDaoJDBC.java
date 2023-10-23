package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.EixoDao;
import model.dao.EnderecoDao;
import model.entities.Eixo;
import model.entities.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDaoJDBC implements EnderecoDao {

	private Connection conn;

	public EnderecoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Endereco findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM endereco WHERE endereco_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Endereco obj = new Endereco();
				obj.setId(rs.getInt("endereco_id"));
				obj.setCep(rs.getString("cep"));
				obj.setNumero(rs.getInt("numero"));
				obj.setLogradouro(rs.getString("logradouro"));
				obj.setComplemento(rs.getString("complemento"));
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
	public List<Endereco> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM endereco ORDER BY endereco_id");
			rs = st.executeQuery();

			List<Endereco> list = new ArrayList<>();

			while (rs.next()) {
				Endereco obj = new Endereco();
				obj.setId(rs.getInt("endereco_id"));
				obj.setCep(rs.getString("cep"));
				obj.setNumero(rs.getInt("numero"));
				obj.setLogradouro(rs.getString("logradouro"));
				obj.setComplemento(rs.getString("complemento"));

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
	public void insert(Endereco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO endereco " +
				"(cep,numero,logradouro,complemento) " +
				"VALUES " +
				"(?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getCep());
			st.setInt(2, obj.getNumero());
			st.setString(3, obj.getLogradouro());
			st.setString(4, obj.getComplemento());

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
	public void update(Endereco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE endereco " +
				"SET cep = ?, " +
				"numero = ?, " +
				"logradouro = ?," +
				"complemento = ?" +
				"WHERE endereco_id = ?");

			st.setString(1, obj.getCep());
			st.setInt(2, obj.getNumero());
			st.setString(3, obj.getLogradouro());
			st.setString(4, obj.getComplemento());
			st.setInt(5, obj.getId());
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
				"DELETE FROM endereco WHERE endereco_id = ?");

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
