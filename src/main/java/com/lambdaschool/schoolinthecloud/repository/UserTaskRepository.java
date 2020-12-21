package com.lambdaschool.schoolinthecloud.repository;

import com.lambdaschool.schoolinthecloud.models.UserTasks;
import org.springframework.data.repository.CrudRepository;

public interface UserTaskRepository extends CrudRepository<UserTasks, Long> {
}
