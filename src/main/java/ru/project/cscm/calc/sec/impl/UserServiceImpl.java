package ru.project.cscm.calc.sec.impl;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.project.cscm.calc.base.NotNullOrEmpty;
import ru.project.cscm.calc.base.UserRepository;
import ru.project.cscm.calc.base.UserService;
import ru.project.cscm.calc.base.model.CscmUser;
import ru.project.cscm.calc.sec.AppRole;

import com.google.common.collect.ImmutableList;

@Service
@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder shaPasswordEncoder;

	@Override
	@NotNull
	public List<CscmUser> getUsers() {
		return ImmutableList.copyOf(userRepository.findAll());
	}

	@Override
	public CscmUser getUserByUsername(@NotNullOrEmpty final String username) {
		return userRepository.findById(username).orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void load(@NotNullOrEmpty final String id,
			@NotNullOrEmpty final String password, @NotNull Set<AppRole> roles) {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(id)) {
			throw new RuntimeException("Password and login must be provided!");
		}

		final CscmUser curUser = getUserByUsername(id);
		final String encodedPassword = shaPasswordEncoder.encode(password);
		final CscmUser user;
		if (curUser == null) {
			user = new CscmUser(id, encodedPassword, roles);
		} else {
			user = curUser;
			user.setPassword(encodedPassword);
			user.getAppRoles().clear();
			user.getAppRoles().addAll(roles);
		}

		userRepository.save(user);
	}

	@Override
	public void deactivate(@NotNullOrEmpty final String id) {
		final CscmUser user = getUserByUsername(id);
		user.setActive(false);
		userRepository.save(user);
	}
	
}
