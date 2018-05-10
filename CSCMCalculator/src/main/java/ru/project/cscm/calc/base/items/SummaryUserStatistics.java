package ru.project.cscm.calc.base.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SummaryUserStatistics {

	private final String userId;
	private final Collection<StatisticsAllGroups> statsAllGroups;
	private final Collection<StatisticsByGroup> statsByGroup;

	public SummaryUserStatistics(String userId,
			Collection<StatisticsAllGroups> statsAllGroups,
			Collection<StatisticsByGroup> statsByGroup) {
		super();
		this.userId = userId;
		this.statsAllGroups = statsAllGroups;
		this.statsByGroup = sortStats(statsByGroup);
	}

	public Collection<StatisticsAllGroups> getStatsAllGroups() {
		return statsAllGroups;
	}

	public Collection<StatisticsByGroup> getStatsByGroup() {
		return statsByGroup;
	}

	public String getUserId() {
		return userId;
	}

	private List<StatisticsByGroup> sortStats(
			final Collection<StatisticsByGroup> stats) {
		final List<StatisticsByGroup> result = new ArrayList<>(stats);
		Collections.sort(
				result,
				(o1, o2) -> Comparator
						.comparingInt(StatisticsByGroup::getGroupId)
						.thenComparing(StatisticsByGroup::getDescx)
						.compare(o1, o2));
		return result;
	}
}
