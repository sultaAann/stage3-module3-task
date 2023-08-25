package com.mjc.school.repository.implRepo;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.entity.impl.News;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<News, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> readAll() {
        return entityManager.createQuery("select a from News a").getResultList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    @Override
    public News create(News model) {
        model.setCreatedDate(LocalDateTime.now());
        entityManager.persist(model);
        return model;
    }

    @Override
    public News update(News model) {
        model.setLastUpdatedDate(LocalDateTime.now());
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            News news = entityManager.find(News.class, id);
            entityManager.remove(news);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        News model = entityManager.find(News.class, id);
        return entityManager.contains(model);
    }
}
