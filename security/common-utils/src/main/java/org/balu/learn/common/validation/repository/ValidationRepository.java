package org.balu.learn.common.validation.repository;

public interface ValidationRepository {
//	boolean existsById(Class<? extends BusinessObject> entityClass, Object primaryKey);
	<T> boolean existsById(Class<T> entityClass, Object primaryKey);
	<T> boolean exists(Class<T> entityClass, String property, Object value);
}
