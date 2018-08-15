package pl.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Produkty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String wydawnictwo;
    private String tytul;
    private String ISBN;
    private String kategoria;
    private int iloscStron;
    private String opis;
    private double cenaNetto;
    private double cenaBrutto;
    private boolean czyEbook;
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private List<Zdjecia> zdjecia=new ArrayList<>();


	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getWydawnictwo() {
		return wydawnictwo;
	}
	public void setWydawnictwo(String wydawnictwo) {
		this.wydawnictwo = wydawnictwo;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	public int getIloscStron() {
		return iloscStron;
	}
	public void setIloscStron(int iloscStron) {
		this.iloscStron = iloscStron;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCenaNetto() {
		return cenaNetto;
	}
	public void setCenaNetto(double cenaNetto) {
		this.cenaNetto = cenaNetto;
	}
	public double getCenaBrutto() {
		return cenaBrutto;
	}
	public void setCenaBrutto(double cenaBrutto) {
		this.cenaBrutto = cenaBrutto;
	}
	public boolean isCzyEbook() {
		return czyEbook;
	}
	public void setCzyEbook(boolean czyEbook) {
		this.czyEbook = czyEbook;
	}

	public List<Zdjecia> getZdjecia() {
		return zdjecia;
	}

	public void setZdjecia(List<Zdjecia> zdjecia) {
		this.zdjecia = zdjecia;
	}

	@Override
	public String toString() {
		return "Produkty [ID=" + ID + ", wydawnictwo=" + wydawnictwo + ", tytul=" + tytul + ", ISBN=" + ISBN
				+ ", kategoria=" + kategoria + ", iloscStron=" + iloscStron + ", opis=" + opis + ", cenaNetto="
				+ cenaNetto + ", cenaBrutto=" + cenaBrutto + ", czyEbook=" + czyEbook + "]";
	}
	public Produkty(Long iD, String wydawnictwo, String tytul, String iSBN, String kategoria, int iloscStron,
			String opis, double cenaNetto, double cenaBrutto, boolean czyEbook) {
		super();
		ID = iD;
		this.wydawnictwo = wydawnictwo;
		this.tytul = tytul;
		ISBN = iSBN;
		this.kategoria = kategoria;
		this.iloscStron = iloscStron;
		this.opis = opis;
		this.cenaNetto = cenaNetto;
		this.cenaBrutto = cenaBrutto;
		this.czyEbook = czyEbook;
	}
	public Produkty() {
		super();
	}

   
    
    
}
