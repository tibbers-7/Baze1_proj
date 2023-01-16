package model;

public class Ocena {
	private int idO;
	private int ocenaO;
	private boolean vazecaO;
	private int filmO;
	private String korImeO;
	
	
	public Ocena(int idO, int ocenaO, boolean vazecaO, int filmO, String korImeO) {
		super();
		this.idO = idO;
		this.ocenaO = ocenaO;
		this.vazecaO = vazecaO;
		this.filmO = filmO;
		this.korImeO = korImeO;
	}
	public Ocena(int idO, int ocenaO, int filmO, String korImeO) {
		super();
		this.idO = idO;
		this.ocenaO = ocenaO;
		this.filmO = filmO;
		this.korImeO = korImeO;
	}
	public int getIdO() {
		return idO;
	}
	public void setIdO(int idO) {
		this.idO = idO;
	}
	public int getOcenaO() {
		return ocenaO;
	}
	public void setOcenaO(int ocenaO) {
		this.ocenaO = ocenaO;
	}
	public boolean isVazecaO() {
		return vazecaO;
	}
	public void setVazecaO(boolean vazecaO) {
		this.vazecaO = vazecaO;
	}
	public int getFilmO() {
		return filmO;
	}
	public void setFilmO(int filmO) {
		this.filmO = filmO;
	}
	public String getKorImeO() {
		return korImeO;
	}
	public void setKorImeO(String korImeO) {
		this.korImeO = korImeO;
	}
	
	
}
