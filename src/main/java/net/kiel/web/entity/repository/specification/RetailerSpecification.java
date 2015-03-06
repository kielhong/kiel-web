package net.kiel.web.entity.repository.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.kiel.web.controller.form.RetailerForm;
import net.kiel.web.entity.Retailer;

import org.springframework.data.jpa.domain.Specification;

// @see http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
public class RetailerSpecification {
    public static Specification<Retailer> searchName(String name) {
        return new Specification<Retailer>() {
            @Override
            public Predicate toPredicate(Root<Retailer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // name like '%' + name + '%'
                // FROM retailer WHERE name like '%' + name + '%'  
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }
    
    public static Specification<Retailer> nameLike(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
    
    public static Specification<Retailer> betweenCreateDate(Date beginDate, Date endDate) {
        return new Specification<Retailer>() {

            @Override
            public Predicate toPredicate(Root<Retailer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between(root.get("createdDate"), beginDate, endDate);
            }
        };
    }
    
    public static Specification<Retailer> searchNameAndCreateDate(String name, Date beginDate, Date endDate) {
        return new Specification<Retailer>() {

            @Override
            public Predicate toPredicate(Root<Retailer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate nameLike = cb.like(root.get("name"), "%" + name + "%");
                Predicate betweenDate = cb.between(root.get("createdDate"), beginDate, endDate);
                
                // WHERE (name like ..) and (created_date between ..) 
                return cb.and(nameLike, betweenDate);
            }
        };        
    }        
    
    public static Specification<Retailer> search(RetailerForm condition) {
        return new Specification<Retailer>() {

            @Override
            public Predicate toPredicate(Root<Retailer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate nameLike = cb.like(root.get("name"), "%" + condition.getName() + "%");
                Predicate betweenDate = cb.between(root.get("createdDate"), condition.getBeginDate(), condition.getEndDate());
                
                return cb.and(nameLike, betweenDate);
            }
        };        
    } 
}