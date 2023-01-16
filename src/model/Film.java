package model;

public class Film {
	private int idF;
	private String nazivF;
	private int trajanjeF;
	private String godF;
	private int zanrF;
	public Film(int idF, String nazivF, int trajanjeF, String godF, int zanrF) {
		super();
		this.idF = idF;
		this.nazivF = nazivF;
		this.trajanjeF = trajanjeF;
		this.godF = godF;
		this.zanrF = zanrF;
	}
	
	public Film() {
		// TODO Auto-generated constructor stub
	}
	public int getIdF() {
		return idF;
	}
	public void setIdF(int idF) {
		this.idF = idF;
	}
	public String getNazivF() {
		return nazivF;
	}
	public void setNazivF(String nazivF) {
		this.nazivF = nazivF;
	}
	public int getTrajanjeF() {
		return trajanjeF;
	}
	public void setTrajanjeF(int trajanjeF) {
		this.trajanjeF = trajanjeF;
	}
	public String getGodF() {
		return godF;
	}
	public void setGodF(String godF) {
		this.godF = godF;
	}
	public int getZanrF() {
		return zanrF;
	}
	public void setZanrF(int zanrF) {
		this.zanrF = zanrF;
	}
	
	public String toString() {
		return String.format("%-6d %-37.37s %-6d %-30.30s %-6d", idF, nazivF, trajanjeF, godF,
				zanrF);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s", "ID", "NAZIV", "TRAJANJE", "GODINA", "ZANR");
	}

	
	
}
