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
import model.Zanr;
import model.Film;

public class FilmDAOImpl implements FilmDAO {

	@Override
	
	public int count() throws SQLException {
		String query="select count(*) from film";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			int res=0;
			if (connection!=null) {
				System.out.printf("connected\n");
			}
			if (resultSet.next()) {
				res=resultSet.getInt(1);
			}
			
			connection.close();
			return res;
				
			}catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}
	}
	
	

	@Override
	public boolean delete(Film entity) throws SQLException {
		return deleteById(entity.getIdF());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from film";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			connection.close();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from film where idF=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			connection.close();
			return rowsAffected == 1;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
		
			boolean ret=existsByIdTransactional(connection, id);
			connection.close();
			return ret;
		}
	}
	
	
	private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
		String query = "select * from film where idF=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				boolean ret=resultSet.isBeforeFirst();

				return ret;
			}
		}
	}

	@Override
	public Iterable<Film> findAll() throws SQLException {
		String query = "select idf,nazivf,trajanjef,godf,zanrf from film ";
		List<Film> filmList = new ArrayList<Film>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			
			while (resultSet.next()) {
				Film film = new Film(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4),resultSet.getInt(5));
				filmList.add(film);
			}

		}
		Iterable<Film> it=filmList;
		connection.close();
		return it;
	}

	@Override
	public Iterable<Film> findAllById(Iterable<Integer> ids) throws SQLException {
		
		List<Film> filmList = new ArrayList<>();
		String query="select idf,nazivf,trajanjef,godf,nazivf from film where idF in (";
		StringBuilder sb= new StringBuilder();
		sb.append(query);
		
		for(Integer id: ids) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1); 
		sb.append(")");
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());) {

			int i = 0;
			for (Integer id : ids) {
				preparedStatement.setInt(++i, id);
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					filmList.add(new Film(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3), resultSet.getString(4),
							resultSet.getInt(5)));
				}
				connection.close();
			}
		}
		
		return filmList;
		
	}

	@Override
	public Film findById(Integer id) throws SQLException {
		Film retFilm=null;
		String query="select idf,nazivf,trajanjef,godf,zanrf from film where idF = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			
			preparedStatement.setInt(1,id);
			

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					retFilm= new Film(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(4),resultSet.getString(4),resultSet.getInt(5));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retFilm;
	}
	
	@Override
	public Film findByName(String name) {
		Film retFilm=null;
		String query="select idf,nazivf,trajanjef,godf,zanrf from film where nazivf = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			
			preparedStatement.setString(1,name);
			

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					retFilm= new Film(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(4),resultSet.getString(4),resultSet.getInt(5));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return retFilm;
	}

	

	@Override
	public boolean save(Film entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false);
			if (connection.isClosed()) {
				System.out.printf("closed here");
			}
			boolean ret=saveTransactional(connection, entity);
			connection.commit();
			return ret;
		}
	}

	@Override
	public int saveAll(Iterable<Film> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); 

			
			for (Film entity : entities) {
				boolean success = saveTransactional(connection, entity); //promene vidljive samo na transakciji
				if (success) rowsSaved++;
			}

			connection.commit();//promene vidljive na ostalim konekcijama
			
		}
		
		return rowsSaved;
	}
	
	private boolean saveTransactional(Connection connection, Film entity) throws SQLException {
		// id_th intentionally in the last place, so that the order between commands remains the same
		String insertCommand = "insert into Film (nazivF,trajanjeF,godF,zanrF,idF) values (?, ? , ?, ?,?)";
		String updateCommand = "update Film set nazivF=?, trajanjeF=?, godF=?, zanrF=? where idF=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				existsByIdTransactional(connection, entity.getIdF()) ? updateCommand : insertCommand)) {
			int i = 1;
			
			preparedStatement.setString(i++, entity.getNazivF());
			preparedStatement.setInt(i++, entity.getTrajanjeF());
			preparedStatement.setString(i++, entity.getGodF());
			preparedStatement.setInt(i++, entity.getZanrF());
			preparedStatement.setInt(i++, entity.getIdF());
			
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}



	@Override
	public Film findById(String korIme) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public String toString(Film f) throws SQLException {
		return String.format("%-6d %-30.30s %6d %-30.30s %-30.30s \n", f.getIdF(),f.getNazivF(),f.getTrajanjeF(),f.getGodF(),f.getZanrF());
	}



	@Override
	public String getFormattedHeader() {
		return String.format("%30.30s %-30.30s %30.30s %30.30s %30.30s \n", "ID", "NAZIV","TRAJANJE","GODINA","ZANR");

	}



	@Override
	public Iterable<Film> findAllbyG(int zanrID) throws SQLException {
		
		Film retFilm=null;
		Iterable<Film> res=null;
		List<Film> listFilmovi= new ArrayList<Film>();
		String query="select idf,nazivf,trajanjef,godf,zanrf from film where zanrf = ?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			
			preparedStatement.setInt(1,zanrID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while(resultSet.next()) {
					retFilm= new Film(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getInt(5));
					listFilmovi.add(retFilm);
				}
				
				res=listFilmovi;
	
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
			connection.close();
		
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}


	
}


