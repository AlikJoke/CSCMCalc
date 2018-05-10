package ru.project.cscm.auth.core;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

public final class UserCredentials {

	private final String username;
	private final String password;

	public UserCredentials(@NotNull @NotEmpty final String username,
			@NotNull @NotEmpty final String password) {
		super();
		this.username = username;
		this.password = password;
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
		return Objects.hashCode(username, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof UserCredentials)) {
			return false;
		}
		
		final UserCredentials other = (UserCredentials) obj;
		if (!Objects.equal(username, other.username) || !Objects.equal(password, other.password)) {
			return false;
		}
		
		return true;
	}

}
