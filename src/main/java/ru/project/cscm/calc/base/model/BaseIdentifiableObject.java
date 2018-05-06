package ru.project.cscm.calc.base.model;

import java.util.Calendar;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.IdentifiableObject;

@MappedSuperclass
public abstract class BaseIdentifiableObject<T> implements IdentifiableObject<T> {

	@Id
	protected T id;

	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createdTime;

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar modifiedTime;

	protected BaseIdentifiableObject() {
		super();
	}
	
	protected BaseIdentifiableObject(@NotNull final T id) {
		this(id, Calendar.getInstance(), Calendar.getInstance());
	}

	protected BaseIdentifiableObject(@NotNull final T id, @NotNull final Calendar createdTime,
			@NotNull final Calendar modifiedTime) {
		this.id = id;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	@Override
	@NotNull
	public T getId() {
		return id;
	}

	@Override
	@NotNull
	public Calendar getCreatedTime() {
		return createdTime;
	}

	@Override
	@NotNull
	public Calendar getModifiedTime() {
		return modifiedTime;
	}

}
