package com.codecika.usermanagement.persistence.repository.impl;

import com.codecika.usermanagement.persistence.repository.BaseRepository;
import com.codecika.usermanagement.persistence.domain.Base;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by yukibuwana on 1/24/17.
 */

public class BaseRepositoryImpl<T extends Base> extends SimpleJpaRepository<T, Integer> implements BaseRepository<T> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }

    @Override
    public T findBySecureId(final String secureId) {
        return findOne(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("secureId"), secureId)
                );
            }
        });
    }

    @Override
    public T findById(Integer id) {
        return findOne(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), id)
                );
            }
        });
    }

    @Override
    public boolean exists(Integer id) {
        return findById(id)!=null;
    }
}
