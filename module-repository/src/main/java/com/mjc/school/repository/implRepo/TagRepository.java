package com.mjc.school.repository.implRepo;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.entity.impl.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<Tag, Long> {
    @PersistenceContext
    private EntityManager entityManager;

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
}
