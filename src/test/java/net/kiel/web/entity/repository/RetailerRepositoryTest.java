package net.kiel.web.entity.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.web.KielWebApplication;
import net.kiel.web.entity.Amount;
import net.kiel.web.entity.Retailer;
import net.kiel.web.entity.repository.RetailerRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KielWebApplication.class)
@Transactional
public class RetailerRepositoryTest {
    @Autowired
    RetailerRepository retailerRepository;
    
    @Test
    public void testFindAll() {
        List<Retailer> list = retailerRepository.findAll();
        
        assertTrue(list.size() > 0);
    }
    
    @Test
    public void 개수테스트() {
        Long count = retailerRepository.count();
        
        assertTrue(count > 0);
    }
    
    @Test
    public void testListAmounts() {
        final Integer id = 1;
        List<Amount> amounts = retailerRepository.findOne(id).getAmounts();
        
        assertNotNull(amounts);
    }
}
