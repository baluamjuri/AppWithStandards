package org.balu.learn.common.validation.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ValidationRepositoryImpl implements ValidationRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public <T> boolean existsById(Class<T> entityClass, Object primaryKey) {
		Metamodel metamodel = em.getMetamodel();
	    EntityType<T> entity = metamodel.entity(entityClass);
	    SingularAttribute<T, ? extends Object> declaredId = entity.getDeclaredId(primaryKey.getClass());
		
		CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<T> root = query.from(entityClass);
	    
	    Predicate condition = cb.equal(root.get(declaredId), primaryKey);
	    
	    query = query.select(cb.count(root))
                .where(condition);
	    
	    Long count = em.createQuery(query).getSingleResult();

        return count > 0;
	}
	
	@Override
	public <T> boolean exists(Class<T> entityClass, String property, Object value) {		
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<T> root = query.from(entityClass);

        Predicate condition = cb.equal(root.get(property), value);
        
        query = query.select(cb.count(root))
                .where(condition);

        Long count = em.createQuery(query).getSingleResult();
        
        return count > 0;
	}
}
