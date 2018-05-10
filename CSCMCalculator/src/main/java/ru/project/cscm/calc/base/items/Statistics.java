package ru.project.cscm.calc.base.items;


public class Statistics {

	private final Integer replenishmentsCount;
	private final String replenishmentsCost;
	private final String surplusCost;
	private final String totalCost;
	private final String costPerMonth;

	public Integer getReplenishmentsCount() {
		return replenishmentsCount;
	}

	public String getReplenishmentsCost() {
		return replenishmentsCost;
	}

	public String getSurplusCost() {
		return surplusCost;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public String getCostPerMonth() {
		return costPerMonth;
	}

	public Statistics(Integer replenishmentsCount, String replenishmentsCost,
			String surplusCost, String totalCost, String costPerMonth) {
		super();
		this.replenishmentsCount = replenishmentsCount;
		this.replenishmentsCost = replenishmentsCost;
		this.surplusCost = surplusCost;
		this.totalCost = totalCost;
		this.costPerMonth = costPerMonth;
	}

	@Override
	public String toString() {
		return "Statistics [replenishmentsCount=" + replenishmentsCount
				+ ", replenishmentsCost=" + replenishmentsCost
				+ ", surplusCost=" + surplusCost + ", totalCost=" + totalCost
				+ ", costPerMonth=" + costPerMonth + "]";
	}

}
