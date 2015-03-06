package net.kiel.web.entity.repository;

import java.util.Date;
import java.util.List;

import net.kiel.web.entity.Retailer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Integer>,
                                            JpaSpecificationExecutor<Retailer> {    
    @Query("SELECT max(r.updatedDate) FROM Retailer r")
    Date selectLastUpdatedTime();
    
    Retailer findTopByOrderByUpdatedDateDesc();
    
    List<Retailer> findByStatus(Boolean status);
    
    long countByStatus(Boolean status);
    
    List<Retailer> findByRetailerIdIn(List<Integer> ids); 
    
    List<Retailer> findTop3ByOrderByNameDesc();
}
