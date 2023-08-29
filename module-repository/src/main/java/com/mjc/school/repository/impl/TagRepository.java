package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.repository.model.impl.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<Tag, Long> {

    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Tag> readAll() {
        return entityManager.createQuery("select a from Tag a").getResultList();
    }

    @Override
    public Optional<Tag> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Tag create(Tag model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public Tag update(Tag model) {
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Tag tag = entityManager.find(Tag.class, id);
            entityManager.remove(tag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        Tag model = entityManager.find(Tag.class, id);
        return entityManager.contains(model);
    }

    //Additional Commands
    public List<Tag> readTagsByNewsId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tags = criteriaQuery.from(Tag.class);
        Join<Tag, News> newsJoin = tags.join("news", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("tag_id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
