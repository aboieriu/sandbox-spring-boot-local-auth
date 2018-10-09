package com.aboieriu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author aboieriu
 */
@Entity
@Table(name = "t_auth_token", indexes = {
	@Index(columnList = "user_id", name = "IDX_auth_token_user_id")
})
public class AuthToken extends AbstractIdentifiableEntity {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_auth_token_user"))
	private User user;

	@Column(name = "token")
	private String token;

	@Column(name = "expiry")
	private Date expiry;

	@Column(name = "active", nullable = false, columnDefinition = "boolean default true")
	private boolean active;

	public AuthToken() {
	}

	public AuthToken(User user, String token, Date expiry) {
		this.user = user;
		this.token = token;
		this.expiry = expiry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
