package net.kiel.web.service;

import java.util.List;

import net.kiel.web.entity.Amount;

public interface RetailerService {
    List<Amount> findAmountsByRetailer(Integer retailerId);
}
