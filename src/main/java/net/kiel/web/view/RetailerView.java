package net.kiel.web.view;

import java.util.Date;

import net.kiel.web.entity.Retailer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RetailerView {
    private Retailer retailer;
    
    public RetailerView(Retailer retailer) {
        this.retailer = retailer;
    }
    
    @JsonProperty("retailer_id")
    public Integer getRetailerId() {
        return retailer.getRetailerId();
    }
    
    public String getName() {
        return retailer.getName();
    }
    
    public String getDescription() {
        return retailer.getDescription();
    }
    
    @JsonProperty("created_date")
    public Date getCreatedDate() {
        return retailer.getCreatedDate();
    }
    
    @JsonProperty("barcode_type")
    public String getBarCodeType() {
        return retailer.getBarcodeType().name();
    }
}
