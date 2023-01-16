package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionUtil_Basic;
import connection.ConnectionUtil_HikariCP;
import model.Korisnik;
import model.Film;

public class KorisnikDAOImpl implements KorisnikDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Korisnik entity) throws SQLException {
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
	public Iterable<Korisnik> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Korisnik> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik findById(String korIme) throws SQLException {
		// TODO Auto-generated method stub
		
		Korisnik retKorisnik=null;
		String query="select korimek,imek,przk,polk,aktivank from Korisnik where korimek = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			
			preparedStatement.setString(1,korIme);
			

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				if (resultSet!=null) {
				boolean polK=false;
				boolean acBool=false;
				String pol=resultSet.getString(4);
				String active=resultSet.getString(5);
				if (resultSet.getString(4)!=null) {
					if (pol=="z") {
						polK=true;
					}
				}
				
				if (active=="y"){
					acBool=true;
				}
					
				retKorisnik= new Korisnik(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),polK,acBool);
				
				}
	
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
			connection.close();
		
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retKorisnik;
	}
	

	@Override
	public boolean save(Korisnik entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Korisnik> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(Korisnik k) {
		String pol;
		String akt;
		
			if (k.isPolK()) {
				pol="Zensko";
			} else pol="Musko";
		
		
		if (k.isAktivanK()) {
			akt="Aktivan";
		}else akt="Neaktivan";
		
		return String.format("%-6s %-6s %-6s %-6s %-6s \n", k.getKorIme(),k.getImeK(),k.getPrzK(),pol,akt);
	}

	public String getFormattedHeader() {
		return String.format("%-6s %-6s %-6s %-6s %-6s \n", "USERNAME", "IME", "PREZIME", "POL", "AKTIVNOST");
	}

	@Override
	public Korisnik findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean deactivateUser(String username) throws SQLException {
		boolean deactivated=false;
		String query = "update korisnik set aktivank='n' where korimek= ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1,username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				deactivated= true;
			}
		return deactivated;
		
		}

	}


}
