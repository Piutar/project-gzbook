package com.gzbook.repository;


import com.gzbook.model.friend.Relationship;
import com.gzbook.model.friend.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> findAllByRelatedUserIdAndStatus (Long id, Status status);
    Relationship findRelationshipByRelatingUserIdAndRelatedUserId (Long relatingUserId, Long relatedUserId);
    Iterable<Relationship> findAllByRelatingUserIdOrRelatedUserIdAndStatus (Long relatingUserId, Long relatedUserId , Status status);
    Iterable<Relationship> findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(Long relatingUserId, Status status1, Long relatedUserId , Status status2);
}
