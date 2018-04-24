package pl.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String imie;
    private String nazwisko;
    private String data_zatrudnienia;
    private String data_zwolnienia;
    private int pesel;
    private double pensja;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getData_zatrudnienia() {
		return data_zatrudnienia;
	}
	public void setData_zatrudnienia(String data_zatrudnienia) {
		this.data_zatrudnienia = data_zatrudnienia;
	}
	public String getData_zwolnienia() {
		return data_zwolnienia;
	}
	public void setData_zwolnienia(String data_zwolnienia) {
		this.data_zwolnienia = data_zwolnienia;
	}
	public int getPesel() {
		return pesel;
	}
	public void setPesel(int pesel) {
		this.pesel = pesel;
	}
	public double getPensja() {
		return pensja;
	}
	public void setPensja(double pensja) {
		this.pensja = pensja;
	}
	@Override
	public String toString() {
		return "Pracownik [ID=" + ID + ", imie=" + imie + ", nazwisko=" + nazwisko + ", data_zatrudnienia="
				+ data_zatrudnienia + ", data_zwolnienia=" + data_zwolnienia + ", pesel=" + pesel + ", pensja=" + pensja
				+ "]";
	}
	public Pracownik(Long iD, String imie, String nazwisko, String data_zatrudnienia, String data_zwolnienia, int pesel,
			double pensja) {
		super();
		ID = iD;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data_zatrudnienia = data_zatrudnienia;
		this.data_zwolnienia = data_zwolnienia;
		this.pesel = pesel;
		this.pensja = pensja;
	}
	public Pracownik() {
		super();
	}

    
	
}
