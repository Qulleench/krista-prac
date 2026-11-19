package ru.rsatu.rest.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"volume_weight", "chargeable_weight", "delivery_days", "final_cost"})
public class DeliveryResponse {

    @JsonbProperty("volume_weight")
    private Double volumeWeight;

    @JsonbProperty("chargeable_weight")
    private Double chargeableWeight;

    @JsonbProperty("delivery_days")
    private Integer deliveryDays;

    @JsonbProperty("final_cost")
    private Double finalCost;

    public DeliveryResponse() {}

    public DeliveryResponse(Double volumeWeight, Double chargeableWeight,
                            Integer deliveryDays, Double finalCost) {
        this.volumeWeight = volumeWeight;
        this.chargeableWeight = chargeableWeight;
        this.deliveryDays = deliveryDays;
        this.finalCost = finalCost;
    }

    public Double getVolumeWeight() { return volumeWeight; }
    public void setVolumeWeight(Double volumeWeight) { this.volumeWeight = volumeWeight; }

    public Double getChargeableWeight() { return chargeableWeight; }
    public void setChargeableWeight(Double chargeableWeight) { this.chargeableWeight = chargeableWeight; }

    public Integer getDeliveryDays() { return deliveryDays; }
    public void setDeliveryDays(Integer deliveryDays) { this.deliveryDays = deliveryDays; }

    public Double getFinalCost() { return finalCost; }
    public void setFinalCost(Double finalCost) { this.finalCost = finalCost; }
}