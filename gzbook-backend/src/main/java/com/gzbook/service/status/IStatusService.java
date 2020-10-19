package com.gzbook.service.status;


import com.gzbook.model.friend.Status;

public interface IStatusService {
    Status saveStatus(Status status);

    void deleteStatus(Long id);

    Iterable<Status> findAllStatus();

    Status findStatusById(Long id);
}
