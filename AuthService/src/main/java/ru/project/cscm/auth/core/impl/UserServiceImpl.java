package ru.project.cscm.auth.core.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ru.project.cscm.auth.core.CscmUser;
import ru.project.cscm.auth.core.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECT_USER_QUERY = "select password_hash AS password, locking from T_CM_USER where login = ?";
	
	@Override
	public CscmUser getUserByUsername(@NotNull @NotEmpty final String username) {
		return jdbcTemplate.query(SELECT_USER_QUERY, new Object[] { username },
				rs -> {
					final CscmUser user;
					if (rs.next()) {
						final String password = rs.getString("password").replaceAll("\n", "");
						final long locking = Math.round(rs.getDouble("locking"));
						user = new CscmUser(username, password, locking == 0);
					} else {
						user = null;
					}
					
					return user;
				});
	}

}
