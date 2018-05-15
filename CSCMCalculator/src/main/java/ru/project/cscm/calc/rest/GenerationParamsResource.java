package ru.project.cscm.calc.rest;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class GenerationParamsResource {

	private final Integer cost;
	private final Double refinanceRate;
	private final Integer maxLoading;
	private final Integer serviceCost;
	private final Double averageDemand;
	private final Integer demandDevMin;
	private final Integer demandDevMax;
	private final Double averageSum;
	private final Integer atmsCount;
	private final String currencyCode;

	public Integer getCost() {
		return cost;
	}
	
	public Double getRefinanceRate() {
		return refinanceRate;
	}
	
	public Integer getMaxLoading() {
		return maxLoading;
	}
	
	public Integer getServiceCost() {
		return serviceCost;
	}
	
	public Double getAverageDemand() {
		return averageDemand;
	}

	public Integer getDemandDevMin() {
		return demandDevMin;
	}

	public Integer getDemandDevMax() {
		return demandDevMax;
	}

	public Double getAverageSum() {
		return averageSum;
	}

	public Integer getAtmsCount() {
		return atmsCount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	@JsonCreator
	public GenerationParamsResource(
			@JsonProperty("cost") Integer cost,
			@JsonProperty("refinanceRate") Double refinanceRate,
			@JsonProperty("maxLoading") Integer maxLoading,
			@JsonProperty("erviceCost") Integer serviceCost,
			@JsonProperty("averageDemand") Double averageDemand,
			@JsonProperty("demandDevMin") Integer demandDevMin,
			@JsonProperty("demandDevMax") Integer demandDevMax,
			@JsonProperty("averageSum") Double averageSum,
			@JsonProperty("atmsCount") Integer atmsCount,
			@JsonProperty("currencyCode") String currencyCode) {
		super();
		this.cost = cost;
		this.refinanceRate = refinanceRate;
		this.maxLoading = maxLoading;
		this.serviceCost = serviceCost;
		this.averageDemand = averageDemand;
		this.demandDevMin = demandDevMin;
		this.demandDevMax = demandDevMax;
		this.averageSum = averageSum;
		this.atmsCount = atmsCount;
		this.currencyCode = currencyCode;
	}

}
