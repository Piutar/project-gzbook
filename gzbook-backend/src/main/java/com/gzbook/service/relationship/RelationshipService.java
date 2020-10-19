package com.gzbook.service.relationship;

import com.gzbook.model.friend.Relationship;
import com.gzbook.model.friend.Status;
import com.gzbook.model.user.User;
import com.gzbook.repository.IRelationshipRepository;
import com.gzbook.service.status.IStatusService;
import com.gzbook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationshipService implements IRelationshipService {
    @Autowired
    private IRelationshipRepository iRelationshipRepository;
    @Autowired
    private IStatusService statusService;
    @Autowired
    private IUserService userService;
    @Override
    public Relationship saveRelationship(Relationship relationship) {
        return iRelationshipRepository.save(relationship);
    }

    @Override
    public void deleteRelationship(Long id) {
        iRelationshipRepository.deleteById(id);
    }

    @Override
    public Iterable<Relationship> findAllRelationship() {
        return iRelationshipRepository.findAll();
    }

    @Override
    public Relationship findRelationshipById(Long id) {
        return iRelationshipRepository.findById(id).get();
    }

    @Override
    public Iterable<Relationship> findAllByRelatedUserIdAndStatus(Long id, Status status) {
        return iRelationshipRepository.findAllByRelatedUserIdAndStatus(id,status);
    }

    @Override
    public Relationship findRelationshipByRelatingUserIdAndRelatedUserId(Long relatingId, Long relatedId) {
        return iRelationshipRepository.findRelationshipByRelatingUserIdAndRelatedUserId(relatingId,relatedId);
    }

    @Override
    public Iterable<Relationship> findAllByRelatingUserIdOrRelatedUserIdAndStatus(Long relatingUserId, Long relatedUserId, Status status) {
        return iRelationshipRepository.findAllByRelatingUserIdOrRelatedUserIdAndStatus(relatingUserId,relatedUserId,status);
    }

    @Override
    public Iterable<Relationship> findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(Long relatingUserId, Status status1, Long relatedUserId, Status status2) {
        return iRelationshipRepository.findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(relatingUserId,status1,relatedUserId,status2);
    }

    public List<User> mutualFriends(Long userId, Long friendId) {
        Iterable<Relationship> relationships1 = this.findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(userId, statusService.findStatusById(2L), userId, statusService.findStatusById(2L));

        List<User> users1 = new ArrayList<>();
        for (Relationship relationship : relationships1) {
            if (relationship.getRelatingUserId().equals(userId)) {
                users1.add(userService.findUserById(relationship.getRelatedUserId()));
            } else users1.add(userService.findUserById(relationship.getRelatingUserId()));
        }

        Iterable<Relationship> relationships2 = this.findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(friendId, statusService.findStatusById(2L), friendId, statusService.findStatusById(2L));

        List<User> users2 = new ArrayList<>();
        for (Relationship relationship : relationships2) {
            if (relationship.getRelatingUserId().equals(friendId)) {
                users2.add(userService.findUserById(relationship.getRelatedUserId()));
            } else users2.add(userService.findUserById(relationship.getRelatingUserId()));
        }

        List<User> temp = new ArrayList<>();
        for (User i : users1) {
            for (User j : users2) {
                if (i.getId() == j.getId()) {
                    i.setPassword("00000000000000000");
                    temp.add(i);
                }
            }
        }
        return temp;

    }
}

