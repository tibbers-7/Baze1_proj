package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import model.Film;
import model.Zanr;
import model.Zanr;
import model.Zanr;

public class ZanrDAOImpl implements ZanrDAO {

	@Override
	public int count() throws SQLException {
		return 0;
	}

	@Override
	public boolean delete(Zanr entity) throws SQLException {	
		return false;
	}

	@Override
	public int deleteAll() throws SQLException {
		return 0;
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		return false;
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		
		return false;
	}

	@Override
	public Iterable<Zanr> findAll() throws SQLException {
		
		return null;
	}

	@Override
	public Iterable<Zanr> findAllById(Iterable<Integer> ids) throws SQLException {
		
		return null;
	}

	@Override
	public Zanr findById(Integer id) throws SQLException {
		
		return null;
	}

	@Override
	public boolean save(Zanr entity) throws SQLException {
		
		return false;
	}

	@Override
	public int saveAll(Iterable<Zanr> entities) throws SQLException {
		
		return 0;
	}

	@Override
	public Zanr findById(String zanrIme) throws SQLException {
		Zanr retZanr=null;
		String query="select idz,nazivz from Zanr where nazivz = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setString(1,zanrIme);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					retZanr= new Zanr(resultSet.getInt(1),resultSet.getString(2));	
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return retZanr;
	}
	

	@Override
	public String toString(Zanr z) throws SQLException {
		return String.format("%-6d %-6s \n", z.getIdZ(),z.getNazivZ());
	
	}

	@Override
	public String getFormattedHeader() {
		return String.format("%-6s %-6s \n", "ID", "NAZIV");

	}


	

}
