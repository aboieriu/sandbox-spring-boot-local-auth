package com.aboieriu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author aboieriu
 */
@Entity
@Table(name = "t_user")
public class User extends AbstractIdentifiableEntity {

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = AuthToken.class)
	private List<AuthToken> tokens;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users", targetEntity = Right.class)
	private List<Right> rights;

	public User() {
	}

	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Transient
	public Optional<AuthToken> getActiveAuthToken(){
		return tokens != null ? tokens.stream().filter(AuthToken::isActive).findFirst() : Optional.empty();
	}

	@Transient
	public void setToken(AuthToken authToken) {
		if (tokens == null) {
			tokens = new ArrayList<>();
		}

		tokens.add(authToken);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
}
