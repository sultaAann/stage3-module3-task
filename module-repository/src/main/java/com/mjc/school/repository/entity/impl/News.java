package com.mjc.school.repository.entity.impl;

import com.mjc.school.repository.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
public class News implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorId;

    @ManyToMany
    @JoinTable(
            name = "newsid-tagid",
            joinColumns = {@JoinColumn(name = "newsId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")}
    )
    private List<Tag> tags;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createDate) {
        this.createdDate = createDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorID) {
        this.authorId = authorID;
    }
}
