package com.gzbook.repository;

import com.gzbook.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByUserId(Long id);
    Iterable<Post> findAllByUserIdOrderByCreatedTimeDesc(Long id);

    Iterable<Post> findAllByStatus(long status);
    Iterable<Post> findAllByUserIdAndStatus(long id, long status);
    Iterable<Post> findAllByStatusIn(long[] status);
    Iterable<Post> findAllByUserIdAndTextPostContaining(long userId, String content);
}
