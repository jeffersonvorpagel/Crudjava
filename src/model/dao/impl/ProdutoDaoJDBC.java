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
import model.dao.ProdutoDao;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {

	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Produtos WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Produto obj = new Produto();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Nome"));
				obj.setPreco(rs.getDouble("Preco"));
				obj.setQuantidade(rs.getInt("quantidade"));
				obj.setDespesas(rs.getDouble("despesas"));
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
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Produtos ORDER BY id");
			rs = st.executeQuery();

			List<Produto> list = new ArrayList<>();

			while (rs.next()) {
				Produto obj = new Produto();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Nome"));
				obj.setPreco(rs.getDouble("Preco"));
				obj.setQuantidade(rs.getInt("quantidade"));
				obj.setDespesas(rs.getDouble("despesas"));
				obj.setDescricao(rs.getString("descricao"));
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
	public void insert(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO Produtos " +
			"(Nome,preco,quantidade,despesas,descricao) " +
				"VALUES " +
				"(?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setDouble(4, obj.getDespesas());
			st.setString(5, obj.getDescricao());

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
	public void update(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE Produtos " +
				"SET Name = ?," +
				"preco = ?," +
				"quantidade = ?," +
				"despesas = ?," +
				"descricao= ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setDouble(4, obj.getDespesas());
			st.setString(5, obj.getDescricao());
			st.setInt(6, obj.getId());

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
				"DELETE FROM Produtos WHERE Id = ?");

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
