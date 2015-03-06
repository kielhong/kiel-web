package net.kiel.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import net.kiel.web.controller.form.RetailerForm;
import net.kiel.web.entity.Amount;
import net.kiel.web.entity.Retailer;
import net.kiel.web.entity.repository.RetailerRepository;
import net.kiel.web.entity.repository.specification.RetailerSpecification;
import net.kiel.web.service.RetailerService;
import net.kiel.web.view.RetailerView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/retailer")
public class RetailerController {
    @Autowired
    private RetailerService retailerService;
    @Autowired
    private RetailerRepository retailerRepository; 
        
    // JPA findAll
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Retailer> listRetailerAll () {
        List<Retailer> retailers = retailerRepository.findAll();
        
        return retailers;
    }
    
    // JPA findAll Pageable
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public List<RetailerView> listRetailerPaging(Pageable pageable) {
        Page<Retailer> retailers = retailerRepository.findAll(pageable);

        return retailers.getContent().stream().map(RetailerView::new).collect(Collectors.toList());
    }
    
    // JPA Specification - CB like
    @RequestMapping(value = "/search_name", method = RequestMethod.GET)
    public List<Retailer> searchName(@RequestParam String name) {
        List<Retailer> retailers = retailerRepository.findAll(RetailerSpecification.nameLike(name));
        
        return retailers;
    }
    
    // JPA Specification - CB between
    @RequestMapping(value = "/search_createddate", method = RequestMethod.GET)
    public List<Retailer> searchCreatedDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate, 
                                            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        List<Retailer> retailers = retailerRepository.findAll(RetailerSpecification.betweenCreateDate(beginDate, endDate));
        
        return retailers;
    }
    
    // JPA Specification - CB and
    @RequestMapping(value = "/search_name_date", method = RequestMethod.GET)
    public List<Retailer> searchNameAndDate(@RequestParam String name,
                                            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate, 
                                            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        List<Retailer> retailers = retailerRepository.findAll(RetailerSpecification.searchNameAndCreateDate(name, beginDate, endDate));
        
        return retailers;
    }
    
    // Controller Data Binding
    // JPA Specification
    // JPA Pageable
    // Entity -> View Strategy
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<RetailerView> search(@Valid RetailerForm condition, Pageable pageable) {
        Page<Retailer> retailers = retailerRepository.findAll(RetailerSpecification.search(condition), pageable);
        
        return retailers.getContent().stream().map(r -> new RetailerView(r)).collect(Collectors.toList());
    }
    
    // JPA findOne
    @RequestMapping(value = "/{retailerId}", method = RequestMethod.GET)
    public Retailer findRetailer(@PathVariable Integer retailerId) {
        Retailer retailer = retailerRepository.findOne(retailerId);
        
        return retailer;
    }
    
    // JPA Custome @Query
    @RequestMapping(value = "/updated_time", method = RequestMethod.GET)
    public Map<String, Date> getLastUpdatedTime() {
        Date updatedTime = retailerRepository.selectLastUpdatedTime();
        Map<String, Date> result = new HashMap<String, Date>();
        result.put("updated_time", updatedTime);
        
        return result;
    }
    
    @RequestMapping(value = "/updated_date", method = RequestMethod.GET)
    public Map<String, Date> getLastUpdatedDate() {
        Date updatedTime = retailerRepository.findTopByOrderByUpdatedDateDesc().getUpdatedDate();
        Map<String, Date> result = new HashMap<String, Date>();
        result.put("updated_time", updatedTime);
        
        return result;
    }
    
    // JPA findOne
    // Java 8 Stream - List Converting
    @RequestMapping(value = "/{retailer_id}/amounts", method = RequestMethod.GET)
    public List<Integer> listRetailerAmount(@PathVariable("retailer_id") Integer retailerId) {
        List<Amount> amounts = retailerService.findAmountsByRetailer(retailerId);
        
        List<Integer> result = amounts.stream().map(Amount::getAmount).collect(Collectors.toList());
                
        return result;
    }
    
    // JPA Cusotom find - Entity property
    @RequestMapping(value = "/list_by_status", method = RequestMethod.GET)
    public List<Retailer> listRetailerByStatus(@RequestParam Boolean status) {
        List<Retailer> retailers = retailerRepository.findByStatus(status);
        
        return retailers;
    }
    
    @RequestMapping(value = "/list_ids", method = RequestMethod.GET) 
    public List<Retailer> listRetailerByIds(@RequestParam(value = "ids", required = false) List<Integer> paramIds) {
        List<Retailer> retailers = retailerRepository.findByRetailerIdIn(paramIds);
        
        return retailers;
    }
    
    // JAP Custom count By
    @RequestMapping(value = "/count_by_status", method = RequestMethod.GET)
    public ResponseEntity<Long> countRetailer(@RequestParam Boolean status) {
        Long count = retailerRepository.countByStatus(status);
        
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{retailerId}/lastModified", method = RequestMethod.GET)
    public Retailer modifiedDate(@PathVariable Integer retailerId) {
        Retailer retailer = retailerRepository.findOne(retailerId);
        retailer.setStatus(!retailer.getStatus());
        retailer = retailerRepository.save(retailer);
        
        //retailer = retailerRepository.findOne(retailerId);
        
        return retailer;
    }
    
    @RequestMapping(value = "top3", method = RequestMethod.GET)
    public List<Retailer> top3() {
        List<Retailer> retailers = retailerRepository.findTop3ByOrderByNameDesc();
                
        return retailers;
    }
}
