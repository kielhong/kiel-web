package net.kiel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kiel.web.entity.Amount;
import net.kiel.web.entity.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {
    @Autowired
    private RetailerRepository retailerRepository;
    

    @Override
    public List<Amount> findAmountsByRetailer(Integer retailerId) {
        return retailerRepository.findOne(retailerId).getAmounts();
    }

}
