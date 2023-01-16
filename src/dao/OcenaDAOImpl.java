package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionUtil_HikariCP;
import model.Film;
import model.Korisnik;
import model.Ocena;

public class OcenaDAOImpl implements OcenaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Ocena entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Ocena> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Ocena> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Iterable<Ocena> findAllByU(String korIme) throws SQLException {
		// TODO Auto-generated method stub
		Ocena retOcena=null;
		Iterable<Ocena> res=null;
		List<Ocena> listOcene= new ArrayList<Ocena>();
		String query="select ido,ocenao,vazecao,filmo,korimeo from Ocena where korimeo = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			
			preparedStatement.setString(1,korIme);
			boolean vazBool=false;
			

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
		
				
				while (resultSet.next()) {
					if (resultSet.getString(4)=="y"){
						vazBool=true;
					}
					retOcena= new Ocena(resultSet.getInt(1),resultSet.getInt(2),vazBool,resultSet.getInt(4),resultSet.getString(5));
					listOcene.add(retOcena);
				}
				
				res=listOcene;
	
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
			connection.close();
		
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public boolean save(Ocena entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Ocena> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(Ocena o) {
		String vazeca;
		if(o.isVazecaO()) {
			vazeca="DA";
		}else
			vazeca="NE";
		
		return String.format("%-6d %-6d %-6s %-6s %-6s \n", o.getIdO(),o.getOcenaO(),vazeca,o.getFilmO(),o.getKorImeO());
	}

	public String getFormattedHeader() {
		return String.format("%-6s %-6s %-6s %-6s %-6s \n", "ID", "OCENA", "VAZECA", "FILM", "KORISNIK");
	}

	@Override
	public Ocena findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean dismissReview(int id) throws SQLException {
		boolean dismiss=false;
		String query = "update ocena set vazecao='n' where ido= ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1,id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				dismiss= true;
			}
		return dismiss;
	}

	}

}
