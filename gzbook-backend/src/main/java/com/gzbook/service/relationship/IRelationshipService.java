package com.gzbook.service.relationship;


import com.gzbook.model.friend.Relationship;
import com.gzbook.model.friend.Status;
import com.gzbook.model.user.User;

import java.util.List;

public interface IRelationshipService {
    Relationship saveRelationship(Relationship relationship);

    void deleteRelationship(Long id);

    Iterable<Relationship> findAllRelationship();

    Relationship findRelationshipById(Long id);

    Iterable<Relationship> findAllByRelatedUserIdAndStatus (Long id, Status status);

    Relationship findRelationshipByRelatingUserIdAndRelatedUserId (Long relatingUserId, Long relatedUserId);
    Iterable<Relationship> findAllByRelatingUserIdOrRelatedUserIdAndStatus (Long relatingUserId, Long relatedUserId , Status status);
    Iterable<Relationship> findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(Long relatingUserId, Status status1, Long relatedUserId , Status status2);
    List<User> mutualFriends(Long uerId, Long friendId);
}
