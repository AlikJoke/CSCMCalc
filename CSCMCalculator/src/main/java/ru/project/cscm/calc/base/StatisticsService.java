package ru.project.cscm.calc.base;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.items.SummaryUserStatistics;
import ru.project.cscm.calc.protobuf.AtmTransListProtos.AtmTransList;
import ru.project.cscm.calc.protobuf.AtmTransListProtos.AtmTransList.AtmTrans;

public interface StatisticsService {

	void saveTransactions(@NotNull AtmTransList transList);

	void saveTransactions(@NotNull Collection<AtmTrans> transList);
	
	void saveUserStats(@NotNull SummaryUserStatistics stats);
	
	SummaryUserStatistics findUserStats(@NotNullOrEmpty final String userId);
}
