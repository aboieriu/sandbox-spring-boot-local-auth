package com.aboieriu.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;

/**
 * @author aboieriu
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create")
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update")
	private Date dateUpdate;

	public AbstractEntity(){
		// default constructor
	}

	public AbstractEntity(Date dateCreate, Date dateUpdate) {
		this.dateCreate = dateCreate;
		this.dateUpdate = dateUpdate;
	}

	public AbstractEntity(AbstractEntity source) {
		this.dateCreate = source.dateCreate;
		this.dateUpdate = source.dateUpdate;
	}

	@PrePersist
	public void preUpdateCallback(){
		this.dateCreate = Calendar.getInstance().getTime();
	}

	@PreUpdate
	public void prePersitCallback(){
		this.dateUpdate = Calendar.getInstance().getTime();
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
}
