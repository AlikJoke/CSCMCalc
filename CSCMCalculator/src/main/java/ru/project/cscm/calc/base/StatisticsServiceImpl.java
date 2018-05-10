package ru.project.cscm.calc.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ru.project.cscm.calc.base.items.SummaryUserStatistics;
import ru.project.cscm.calc.protobuf.AtmTransListProtos.AtmTransList;
import ru.project.cscm.calc.protobuf.AtmTransListProtos.AtmTransList.AtmTrans;

@Service
@Repository
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${spring.datasource.batch.size}")
	private int batchSize;
	
	private static final Map<String, SummaryUserStatistics> statsByUser = new ConcurrentHashMap<>();

	@Override
	public void saveTransactions(@NotNull final AtmTransList transList) {
		this.saveTransactions(transList.getTransactionsList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveTransactions(@NotNull final Collection<AtmTrans> transList) {
		if (!CollectionUtils.isEmpty(transList)) {
			transList.forEach(trans -> jdbcTemplate.batchUpdate(
					PreparedQueries.INSERT_TRANS_QUERY, new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int pos)
								throws SQLException {
							int index = 0;
							ps.setLong(++index, Long.valueOf(trans.getOperId()));
							ps.setTimestamp(++index, new Timestamp(trans.getDatetime()));
							ps.setInt(++index, trans.getOperType().getNumber());
							ps.setInt(++index, trans.getNoteRetracted());
							ps.setInt(++index, trans.getNoteRejected());

							for (int i = 0; i <= 7; i++) {
								if (trans.getCassettesCount() >= i + 1){
									ps.setInt(++index, trans.getCassettes(i).getNoteOper());
									ps.setInt(++index, Integer.valueOf(trans.getCassettes(i).getFace()));
									ps.setInt(++index, Integer.valueOf(trans.getCassettes(i).getCurrency()));
									ps.setInt(++index, trans.getCassettes(i).getCassType());
								} else {
									ps.setNull(++index, java.sql.Types.INTEGER);
									ps.setNull(++index, java.sql.Types.INTEGER);
									ps.setNull(++index, java.sql.Types.INTEGER);
									ps.setNull(++index, java.sql.Types.INTEGER);
								}
							}
							
							ps.setString(++index, trans.getTerminalId());
						}

						@Override
						public int getBatchSize() {
							return transList.size() < batchSize ? transList.size() : batchSize;
						}
					}));
		}

	}

	@Override
	public void saveUserStats(@NotNull final SummaryUserStatistics stats) {
		statsByUser.put(stats.getUserId(), stats);
	}

	@Override
	public SummaryUserStatistics findUserStats(@NotNull final String userId) {
		return statsByUser.get(userId);
	}

}
