package ru.project.cscm.calc.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ru.project.cscm.calc.base.items.Statistics;
import ru.project.cscm.calc.base.items.StatisticsAllGroups;
import ru.project.cscm.calc.base.items.StatisticsByGroup;
import ru.project.cscm.calc.base.items.SummaryUserStatistics;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class StatsResource {

	private final List<StatsAllGroupsResource> statsAllGroups;
	private final List<StatsByGroupsResource> statsByGroup;

	public StatsResource(SummaryUserStatistics stats) {
		super();
		this.statsAllGroups = stats.getStatsAllGroups().stream()
				.map(StatsAllGroupsResource::new).collect(Collectors.toList());
		this.statsByGroup = stats.getStatsByGroup().stream()
				.map(StatsByGroupsResource::new).collect(Collectors.toList());
	}

	public Collection<StatsAllGroupsResource> getStatsAllGroups() {
		return statsAllGroups;
	}

	public Collection<StatsByGroupsResource> getStatsByGroup() {
		return statsByGroup;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	public static class StatsItemResource {

		private final Integer replenishmentsNumber;
		private final String replenishmentsCost;
		private final String surplusCost;
		private final String costPerMonth;
		private final String totalCost;

		public Integer getReplenishmentsNumber() {
			return replenishmentsNumber;
		}

		public String getReplenishmentsCost() {
			return replenishmentsCost;
		}

		public String getSurplusCost() {
			return surplusCost;
		}

		public String getCostPerMonth() {
			return costPerMonth;
		}

		public String getTotalCost() {
			return totalCost;
		}

		public StatsItemResource(Statistics statsItem) {
			this.replenishmentsNumber = statsItem.getReplenishmentsCount();
			this.replenishmentsCost = statsItem.getReplenishmentsCost();
			this.surplusCost = statsItem.getSurplusCost();
			this.totalCost = statsItem.getTotalCost();
			this.costPerMonth = statsItem.getCostPerMonth();

		}

		public StatsItemResource(Integer replenishmentsNumber,
				String replenishmentsCost, String surplusCost,
				String costPerMonth, String totalCost) {
			super();
			this.replenishmentsNumber = replenishmentsNumber;
			this.replenishmentsCost = replenishmentsCost;
			this.surplusCost = surplusCost;
			this.costPerMonth = costPerMonth;
			this.totalCost = totalCost;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	public static class StatsAllGroupsResource extends StatsItemResource {

		public StatsAllGroupsResource(StatisticsAllGroups statsItem) {
			super(statsItem);
		}

		public StatsAllGroupsResource(Integer replenishmentsNumber,
				String replenishmentsCost, String surplusCost,
				String costPerMonth, String totalCost) {
			super(replenishmentsNumber, replenishmentsCost, surplusCost,
					costPerMonth, totalCost);
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	public static class StatsByGroupsResource extends StatsItemResource {

		private final Integer groupId;
		private final String groupName;
		private final String descx;

		public StatsByGroupsResource(StatisticsByGroup statsItem) {
			super(statsItem);
			this.groupId = statsItem.getGroupId();
			this.groupName = statsItem.getGroupName();
			this.descx = statsItem.getDescx();

		}

		public StatsByGroupsResource(Integer replenishmentsNumber,
				String replenishmentsCost, String surplusCost,
				String costPerMonth, String totalCost, Integer groupId,
				String groupName, String descx) {
			super(replenishmentsNumber, replenishmentsCost, surplusCost,
					costPerMonth, totalCost);
			this.groupId = groupId;
			this.groupName = groupName;
			this.descx = descx;
		}

		public Integer getGroupId() {
			return groupId;
		}

		public String getGroupName() {
			return groupName;
		}

		public String getDescx() {
			return descx;
		}

	}
}
