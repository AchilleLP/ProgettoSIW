package it.uniroma3.Galleria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import it.uniroma3.Galleria.model.Autore;


@Entity
public class Opera {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String titolo;
	private String tecnica;
	private int dataRealizzazione;
	@ManyToOne
	private Autore autore;
	private String dimensione;
	
	public Opera(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public int getDataRealizzazione() {
		return dataRealizzazione;
	}

	public void setDataRealizzazione(int i) {
		this.dataRealizzazione = i;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public String getDimensione() {
		return dimensione;
	}

	public void setDimensione(String dimensione) {
		this.dimensione = dimensione;
	}

}
