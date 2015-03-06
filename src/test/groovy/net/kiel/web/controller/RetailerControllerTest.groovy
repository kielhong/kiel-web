package net.kiel.web.controller

import javax.net.ssl.SSLEngineResult.Status;

import net.kiel.web.KielWebApplication;
import net.kiel.web.entity.repository.RetailerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import spock.lang.Specification;

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = KielWebApplication)
class RetailerControllerTest extends Specification  {
    MockMvc mockMvc
    
    def repository = Mock(RetailerRepository)
    
    def controller = new RetailerController(retailerRepository : repository)
    
        
    def setup() {
        
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }
    
    def "test listRetailerAll()"() {
        when:
        def response = mockMvc.perform(get("/retailer"))
        
        then:
        response.andExpect(status().isOk())
    }
    

}
