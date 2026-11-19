package ru.rsatu.rest.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"length", "width", "height", "weight", "is_express", "insurance_value"})
public class DeliveryRequest {

    @JsonbProperty("length")
    private Double length;

    @JsonbProperty("width")
    private Double width;

    @JsonbProperty("height")
    private Double height;

    @JsonbProperty("weight")
    private Double weight = 0.0;

    @JsonbProperty("is_express")
    private Boolean isExpress = false;

    @JsonbProperty("insurance_value")
    private Double insuranceValue = 0.0;

    public DeliveryRequest() {}

    public Double getLength() { return length; }
    public void setLength(Double length) { this.length = length; }

    public Double getWidth() { return width; }
    public void setWidth(Double width) { this.width = width; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight != null ? weight : 0.0; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Boolean getIsExpress() { return isExpress != null ? isExpress : false; }
    public void setIsExpress(Boolean isExpress) { this.isExpress = isExpress; }

    public Double getInsuranceValue() { return insuranceValue != null ? insuranceValue : 0.0; }
    public void setInsuranceValue(Double insuranceValue) { this.insuranceValue = insuranceValue; }
}