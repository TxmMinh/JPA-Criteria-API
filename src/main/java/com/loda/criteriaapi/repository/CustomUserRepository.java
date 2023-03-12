package com.loda.criteriaapi.repository;

import com.loda.criteriaapi.entity.User;
import com.loda.criteriaapi.entity.User.UserType;
import com.loda.criteriaapi.entity.User_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CustomUserRepository {

    @PersistenceContext // Khi Spring Boot tạo ra đối tượng CustomUserRepository, nó sẽ tự động cung cấp một EntityManager
    private EntityManager em;

    public User getUserById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate condition = builder.equal(root.get(User_.ID), id);

        query.select(root).where(condition); // Đoạn code này tương đương với SELECT u FROM User u WHERE u.id = :id

        // Sử dụng EntityManager để tương tác với cơ sở dữ liệu
        return em.createQuery(query).getSingleResult();
    }

    public Collection<User> getUserByComplexConditions(String name, UserType type) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate hasNameLike = builder.like(root.get(User_.NAME), name);
        Predicate hasType = builder.equal(root.get(User_.TYPE), type);

        Predicate condition = builder.and(hasNameLike, hasType);

        query.select(root).where(condition);
        return em.createQuery(query).getResultList();
    }
}
