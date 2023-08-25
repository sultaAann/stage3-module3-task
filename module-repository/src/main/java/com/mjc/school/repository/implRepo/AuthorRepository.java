package com.mjc.school.repository.implRepo;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.entity.impl.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    @Override
    public Author update(Author model) {
        model.setLastUpdatedDate(LocalDateTime.now());
        return entityManager.merge(model);
    }

    @Transactional
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
}
