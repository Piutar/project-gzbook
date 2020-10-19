package com.gzbook.model.friend;

import javax.persistence.*;

@Entity
@Table(name = "relationship")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long relatingUserId;
    private Long relatedUserId;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Relationship() {
    }
    public Relationship(Long relatingUserId,Long relatedUserId ) {

        this.relatingUserId = relatingUserId;
        this.relatedUserId = relatedUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelatingUserId() {
        return relatingUserId;
    }

    public void setRelatingUserId(Long relatingUserId) {
        this.relatingUserId = relatingUserId;
    }

    public Long getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(Long relatedUserId) {
        this.relatedUserId = relatedUserId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
