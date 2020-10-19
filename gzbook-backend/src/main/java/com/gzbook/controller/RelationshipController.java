package com.gzbook.controller;

import com.gzbook.model.friend.Relationship;
import com.gzbook.model.friend.Status;
import com.gzbook.model.user.User;
import com.gzbook.service.relationship.IRelationshipService;
import com.gzbook.service.status.IStatusService;
import com.gzbook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/relationship")
public class RelationshipController {
    @Autowired
    private IRelationshipService relationshipService;
    @Autowired
    private IStatusService statusService;
    @Autowired
    private IUserService userService;
    @ModelAttribute("statusList")
    public Iterable<Status> statusList() {
        return statusService.findAllStatus();
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<Relationship>> findAll() {
        return new ResponseEntity<>(relationshipService.findAllRelationship(), HttpStatus.OK);
    }
    @PostMapping("/create/{relatingId}")
    public ResponseEntity<Relationship> addInviteFriend(@PathVariable Long relatingId, @RequestBody User user){
        Relationship relationship = this.checkRelationship(relatingId,user.getId());
        if (relationship==null){
            Relationship relationship1 = new Relationship(relatingId,user.getId());
            Status status = statusService.findStatusById(1L);
            relationship1.setStatus(status);
            relationshipService.saveRelationship(relationship1);
        }else {relationship.setStatus(statusService.findStatusById(1l));
            relationshipService.saveRelationship(relationship);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{relatedId}/{statusId}")
    public ResponseEntity<Relationship> acceptInviteFriend2(@PathVariable Long relatedId, @PathVariable Long statusId, @RequestBody User user){
      Relationship relationship = relationshipService.findRelationshipByRelatingUserIdAndRelatedUserId(user.getId(),relatedId);
      relationship.setStatus(statusService.findStatusById(statusId));
      return new ResponseEntity<>(relationshipService.saveRelationship(relationship), HttpStatus.OK);
    }

    @PutMapping("/unfriend/{relatedId}/{statusId}")
    public ResponseEntity<Relationship> unFriend(@PathVariable Long relatedId, @PathVariable Long statusId, @RequestBody User user){
        Relationship relationship = this.checkRelationship(user.getId(),relatedId);
        relationship.setStatus(statusService.findStatusById(statusId));
        return new ResponseEntity<>(relationshipService.saveRelationship(relationship), HttpStatus.OK);

    }

    @GetMapping("/listFriend/{userId}")
    public ResponseEntity<Iterable<User>> findAllFriend(@PathVariable Long userId) {
        Iterable<Relationship> relationships = relationshipService.findAllByRelatingUserIdAndStatusOrRelatedUserIdAndStatus(userId,statusService.findStatusById(2L),userId,statusService.findStatusById(2L));

        List<User> users = new ArrayList<>();
        for (Relationship relationship:relationships
             ) {if (relationship.getRelatingUserId().equals(userId)){
                 users.add(userService.findUserById(relationship.getRelatedUserId()));
        }else users.add(userService.findUserById(relationship.getRelatingUserId()));

        }

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }
    @GetMapping("/listPending/{userId}")
    public ResponseEntity<Iterable<User>> findPending(@PathVariable Long userId){
        Iterable<Relationship> relationships = relationshipService.findAllByRelatedUserIdAndStatus(userId,statusService.findStatusById(1L));

        List<User> users = new ArrayList<>();
        for (Relationship relationship:relationships
        ) {
            users.add(userService.findUserById(relationship.getRelatingUserId()));
        }

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }
    @GetMapping("/checkFriend/{relatingId}/{relatedId}")
    public ResponseEntity<Long> checkFriend(@PathVariable Long relatingId, @PathVariable Long relatedId){
        Long status;
        Relationship relationship = this.checkRelationship(relatingId,relatedId);
        if (relationship!=null){
            status = relationship.getStatus().getId();
        }else status=0l;
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/mutualFriends/{userId}/{friendId}")
    public ResponseEntity<List<User>> mutualFriends(@PathVariable Long userId, @PathVariable Long friendId) {
        return new ResponseEntity<>(relationshipService.mutualFriends(userId, friendId), HttpStatus.OK);
    }

    public Relationship checkRelationship(Long relatingId, Long relatedId){
        Relationship relationship ;
        if (relationshipService.findRelationshipByRelatingUserIdAndRelatedUserId(relatingId,relatedId)!=null){
            relationship = relationshipService.findRelationshipByRelatingUserIdAndRelatedUserId(relatingId,relatedId);
        }else if (relationshipService.findRelationshipByRelatingUserIdAndRelatedUserId(relatedId,relatingId)!=null){
            relationship = relationshipService.findRelationshipByRelatingUserIdAndRelatedUserId(relatedId,relatingId);
        }else relationship = null;
        return relationship;
    }


}
