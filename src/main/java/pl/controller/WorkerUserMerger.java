package pl.controller;

import pl.model.Pracownik;
import pl.model.User;

public class WorkerUserMerger {
	private User uzytkownik;
	private Pracownik pracownik;
	
	WorkerUserMerger(){
	}
	
	WorkerUserMerger(User uzytkownik, Pracownik pracownik){
		this.uzytkownik = uzytkownik;
		this.pracownik = pracownik;
	}
	
	public void setUzytkownik(User uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}
	
	public User getUzytkownik() {
		return uzytkownik;
	}
	
	public Pracownik getPracownik() {
		return pracownik;
	}
}
