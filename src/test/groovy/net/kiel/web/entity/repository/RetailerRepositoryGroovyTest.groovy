package net.kiel.web.entity.repository

import net.kiel.web.KielWebApplication
import net.kiel.web.entity.Retailer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification


@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = KielWebApplication)
class RetailerRepositoryGroovyTest extends Specification {
    @Autowired
    RetailerRepository repository
    
    def "Retailer DB에서 하나 조회"() {
       setup:
       def id = 1
              
       when:
       def retailer = repository.findOne(id);
       
       then:
       retailer instanceof Retailer
       retailer.retailerId == id
    }
    
    def "Retailer DB에서 목록 조회"() {
        when:
        def retailers = repository.findAll();
        
        then:
        retailers.size() > 0
    }
    
    def "Retailer 개수"() {
        when:
        def count = repository.count();
        
        then:
        count > 0
    }
    
    def "Retailer.countByStatus()"() {
        expect:
        def count = repository.countByStatus(false);
        count == 2
    }
    
    def "Retailer.findTopByOrderByUpdatedDateDesc()"() {
        when:
        def topRetailer = repository.findTopByOrderByUpdatedDateDesc();
        def retailers = repository.findAll(); 
        
        then:
        topRetailer != null
        for (retailer in retailers) {
            topRetailer.updatedDate >= retailer.updatedDate
        }
    }
    
    def "NPE throw"() {
        when:
        def retailer = repository.findOne(Integer.MAX_VALUE)
        retailer.retailerId + 1 
        
        then:
        thrown(NullPointerException)
    }
}