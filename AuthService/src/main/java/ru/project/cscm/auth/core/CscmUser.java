package ru.project.cscm.auth.core;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

public class CscmUser {

	private final String username;
	private final String password;
	private final boolean isActive;

	public CscmUser(@NotNull @NotEmpty final String username,
			@NotNull @NotEmpty final String password, final boolean isActive) {
		super();
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}
	
	public final boolean isActive() {
		return isActive;
	}

	@NotNull
	@NotEmpty
	public final String getUsername() {
		return username;
	}

	@NotNull
	@NotEmpty
	public final String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(username, password, isActive);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof CscmUser)) {
			return false;
		}
		
		final CscmUser other = (CscmUser) obj;
		if (!Objects.equal(username, other.username)
				|| !Objects.equal(password, other.password)
				|| isActive == other.isActive) {
			return false;
		}
		
		return true;
	}
}
