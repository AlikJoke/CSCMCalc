package ru.project.cscm.calc.sec.rest;

import java.util.Calendar;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.NotNullOrEmpty;
import ru.project.cscm.calc.base.model.CscmUser;
import ru.project.cscm.calc.sec.AppRole;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CscmUserResource {

	private final String id;
	private final String password;
	private final Collection<String> roles;
	private final boolean isActive;
	private final Calendar createdTime;
	private final Calendar modifiedTime;

	public CscmUserResource(@JsonProperty("id") final String id,
			@JsonProperty("password") final String password,
			@JsonProperty("roles") final Set<String> roles,
			@JsonProperty("isActive") final boolean isActive,
			@JsonProperty("createdTime") final Calendar createdTime,
			@JsonProperty("modifiedTime") final Calendar modifiedTime) {
		super();
		this.id = id;
		this.password = password;
		this.roles = roles;
		this.isActive = isActive;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public CscmUserResource(@NotNull final CscmUser user) {
		this.id = user.getId();
		this.password = null;
		this.roles = user.getAppRoles().stream().map(AppRole::name)
				.collect(Collectors.toSet());
		this.isActive = user.isActive();
		this.createdTime = user.getCreatedTime();
		this.modifiedTime = user.getModifiedTime();
	}

	@NotNullOrEmpty
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	@NotNullOrEmpty
	public Collection<String> getRoles() {
		return roles;
	}

	@JsonIgnore
	@NotNullOrEmpty
	public Collection<AppRole> getAppRoles() {
		return getRoles().stream().map(AppRole::valueOf)
				.collect(Collectors.toSet());
	}

	public boolean isActive() {
		return isActive;
	}

	@NotNullOrEmpty
	public Calendar getCreatedTime() {
		return createdTime;
	}

	@NotNullOrEmpty
	public Calendar getModifiedTime() {
		return modifiedTime;
	}

}
