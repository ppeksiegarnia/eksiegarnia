package pl.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Suchy
 *
 */
/**
 * @author Suchy
 *
 */
@Entity
@Data
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Firma;
    private String Regon;
    private String Nip;
    private String Imie;
    private String Nazwisko;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getFirma() {
		return Firma;
	}
	public void setFirma(String firma) {
		Firma = firma;
	}
	public String getRegon() {
		return Regon;
	}
	public void setRegon(String regon) {
		Regon = regon;
	}
	public String getNip() {
		return Nip;
	}
	public void setNip(String nip) {
		Nip = nip;
	}
	public String getImie() {
		return Imie;
	}
	public void setImie(String imie) {
		Imie = imie;
	}
	public String getNazwisko() {
		return Nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		Nazwisko = nazwisko;
	}
	@Override
	public String toString() {
		return "Klient [ID=" + ID + ", Firma=" + Firma + ", Regon=" + Regon + ", Nip=" + Nip + ", Imie=" + Imie
				+ ", Nazwisko=" + Nazwisko + "]";
	}
	public Klient(Long iD, String firma, String regon, String nip, String imie, String nazwisko) {
		super();
		ID = iD;
		Firma = firma;
		Regon = regon;
		Nip = nip;
		Imie = imie;
		Nazwisko = nazwisko;
	}
	public Klient() {
		super();
	}


   
    
    
}
