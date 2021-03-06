package it.uniroma3.Galleria.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.validator.constraints.NotEmpty;

import it.uniroma3.Galleria.model.Role;

@Entity
public class Amministratore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	@NotEmpty(message="Campo obbligatorio")
	@Column(unique=true)
	private String nickname;
	@NotEmpty(message="Campo obbligatorio")
	private String password;
	private boolean enabled;
	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String user) {
		this.nickname = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Amministratore(){
		enabled=true;
	}
	
	public Amministratore(String nick,String password){
		enabled=true;
		this.nickname=nick;
		this.password=password;
	}
	
	
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
