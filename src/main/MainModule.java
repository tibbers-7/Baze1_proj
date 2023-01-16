package main;
import java.sql.SQLException;

import dao.FilmDAOImpl;
import model.Film;
import ui.UIHandler;

//import connection.*;
//import dao.*;
//import model.*;
public class MainModule {
	
	public static final UIHandler handleUi= new UIHandler();

	public static void main(String[] args) throws SQLException {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

		
		handleUi.handleMenu();
		
	}

}
