package it.uniroma3.Galleria.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Autore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private String nazionalita;
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	@Temporal(TemporalType.DATE)
	private Date dataMorte;
	@OneToMany(mappedBy="autore")
	private Map<String,Opera> opere;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public Date getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(Date dataMorte) {
		this.dataMorte = dataMorte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nazionalita == null) ? 0 : nazionalita.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataMorte == null) ? 0 : dataMorte.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((opere == null) ? 0 : opere.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		if (nazionalita == null) {
			if (other.nazionalita != null)
				return false;
		} else if (!nazionalita.equals(other.nazionalita))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataMorte == null) {
			if (other.dataMorte != null)
				return false;
		} else if (!dataMorte.equals(other.dataMorte))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (opere == null) {
			if (other.opere != null)
				return false;
		} else if (!opere.equals(other.opere))
			return false;
		return true;
	}

	
	/*public Autore(){
		this.opere = new HashMap<>();
	}
	public void addOpera(Opera o){
		if(o!=null)
			opere.put(o.getTitolo(),o);
	}
	public Opera getOpera(String titolo){
		if(titolo!=null)
			return opere.get(titolo);
		return null;
	}*/
	
}