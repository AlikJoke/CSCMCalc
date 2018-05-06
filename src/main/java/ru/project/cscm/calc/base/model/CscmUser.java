package ru.project.cscm.calc.base.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ru.project.cscm.calc.base.HasAuthenticationAccess;
import ru.project.cscm.calc.base.NotNullOrEmpty;
import ru.project.cscm.calc.sec.AppRole;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "findUserById", query = CscmUser.NamedQueries.FIND_USER_BY_ID) })
public class CscmUser extends BaseIdentifiableObject<String> implements
		HasAuthenticationAccess<String> {

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", uniqueConstraints = { @UniqueConstraint(name = "idx_user_id", columnNames = {
			"cscm_user_id", "roles" }) }, joinColumns = { @JoinColumn(name = "cscm_user_id", referencedColumnName = "id") })
	@Enumerated(EnumType.STRING)
	private Set<AppRole> roles;

	@Column(length = 64, nullable = false)
	private String password;

	@Column
	private boolean isActive;

	protected CscmUser() {
		super();
	}

	public CscmUser(@NotNullOrEmpty final String id,
			@NotNullOrEmpty final String password,
			@NotNullOrEmpty final Set<AppRole> roles, final boolean isActive,
			@NotNull final Calendar createdTime,
			@NotNull final Calendar modifiedTime) {
		super(id, createdTime, modifiedTime);
		this.password = password;
		this.roles = roles;
		this.isActive = isActive;
	}

	public CscmUser(@NotNullOrEmpty final String id,
			@NotNullOrEmpty final String password,
			@NotNullOrEmpty final Set<AppRole> roles) {
		super(id);
		this.password = password;
		this.roles = roles;
		this.isActive = true;
	}

	@Override
	@NotNullOrEmpty
	public Collection<GrantedAuthority> getRoles() {
		return Optional.ofNullable(roles).orElse(new HashSet<>()).stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toSet());
	}

	@NotNull
	public Collection<AppRole> getAppRoles() {
		return roles;
	}

	@Override
	@NotNullOrEmpty
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	public void setActive(final boolean isActive) {
		this.isActive = isActive;
	}

	public void setPassword(@NotNullOrEmpty final String password) {
		this.password = password;
	}

	@PrePersist
	@PreUpdate
	public void preModify() {
		modifiedTime = Calendar.getInstance();
	}

	static class NamedQueries {

		public static final String FIND_USER_BY_ID = "SELECT u FROM CscmUser u WHERE u.isActive = true AND u.id = :userId";
	}
}
