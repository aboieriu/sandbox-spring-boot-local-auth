package com.aboieriu.entity;

import com.aboieriu.domain.RightCodeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * @author aboieriu
 */
@Entity
@Table(name = "t_right")
public class Right extends AbstractIdentifiableEntity {

	@Column(name = "code")
	@Enumerated(EnumType.STRING)
	private RightCodeEnum code;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinTable(name = "t_user_right",
		joinColumns = {
			@JoinColumn(name = "right_id", nullable = false, foreignKey = @ForeignKey(name = "fk_t_user_right_right"))
		},
		inverseJoinColumns = {
			@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_t_user_right_user"))
		},
		uniqueConstraints = {
			@UniqueConstraint(columnNames = { "right_id", "user_id" }, name = "UK_t_user_right_pk")
		}
	)
	private List<User> users;

	public Right() {
	}

	public Right(RightCodeEnum code) {
		this.code = code;
	}

	public RightCodeEnum getCode() {
		return code;
	}

	public void setCode(RightCodeEnum code) {
		this.code = code;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
