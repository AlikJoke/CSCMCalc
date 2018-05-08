package ru.project.cscm.calc.rest;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class GenerationParamsResource {

	private final Double averageDemand;
	private final Integer demandDeviationFrom;
	private final Integer demandDeviationTo;
	private final Double averageReplenishmentSum;
	private final Integer atmsCount;
	private final String currencyCode;

	public Double getAverageDemand() {
		return averageDemand;
	}

	public Integer getDemandDeviationFrom() {
		return demandDeviationFrom;
	}

	public Integer getDemandDeviationTo() {
		return demandDeviationTo;
	}

	public Double getAverageReplenishmentSum() {
		return averageReplenishmentSum;
	}

	public Integer getAtmsCount() {
		return atmsCount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	@JsonCreator
	public GenerationParamsResource(
			@JsonProperty("averageDemand") Double averageDemand,
			@JsonProperty("demandDeviationFrom") Integer demandDeviationFrom,
			@JsonProperty("demandDeviationTo") Integer demandDeviationTo,
			@JsonProperty("averageReplenishmentSum") Double averageReplenishmentSum,
			@JsonProperty("atmsCount") Integer atmsCount,
			@JsonProperty("currencyCode") String currencyCode) {
		super();
		this.averageDemand = averageDemand;
		this.demandDeviationFrom = demandDeviationFrom;
		this.demandDeviationTo = demandDeviationTo;
		this.averageReplenishmentSum = averageReplenishmentSum;
		this.atmsCount = atmsCount;
		this.currencyCode = currencyCode;
	}

}
