package ru.project.cscm.calc.base.items;

public class StatisticsByGroup extends Statistics {

	private final String groupName;
	private final Integer groupId;
	private final String descx;

	@Override
	public String toString() {
		return "StatisticsByGroup [groupName=" + groupName + ", groupId="
				+ groupId + ", descx=" + descx + "]";
	}

	public String getDescx() {
		return descx;
	}

	public String getGroupName() {
		return groupName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public StatisticsByGroup(Integer replenishmentsCount,
			String replenishmentsCost, String surplusCost, String totalCost,
			String costPerMonth, String groupName, Integer groupId, String descx) {
		super(replenishmentsCount, replenishmentsCost, surplusCost, totalCost,
				costPerMonth);
		this.groupName = groupName;
		this.groupId = groupId;
		this.descx = descx;
	}

}
