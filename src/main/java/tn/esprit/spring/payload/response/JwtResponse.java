package tn.esprit.spring.payload.response;


import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private List<String> roles;
	private int role;
	private String nom;
	private String prenom;
	private Boolean active;
	public JwtResponse(String token,  Long id, String username, List<String> roles, int role, String nom, String prenom,Boolean active) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
		this.active=active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
