package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.model.impl.News;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Author> readAll() {
        return entityManager.createQuery("select a from Author a").getResultList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author create(Author model) {
        model.setCreatedDate(LocalDateTime.now());
        entityManager.persist(model);
        return model;
    }

    @Override
    public Author update(Author model) {
        model.setLastUpdatedDate(LocalDateTime.now());
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Author author = entityManager.find(Author.class, id);
            entityManager.remove(author);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        Author author = entityManager.find(Author.class, id);
        return entityManager.contains(author);
    }


    // Additional command
    public List<Author> readAuthorByNewsId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> tags = criteriaQuery.from(Author.class);
        Join<Author, News> newsJoin = tags.join("news", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("author_id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
