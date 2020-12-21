package com.lambdaschool.schoolinthecloud.services;

import com.lambdaschool.schoolinthecloud.models.UserTasks;

import java.util.List;

public interface UserTaskService {
    /**
     * Returns a list of all users and their tasks
     *
     * @return List of users and their tasks
     */
    List<UserTasks> findAll();

    /**
     * Returns the user task combination associated with the given id
     *
     * @param id The primary key (long) of the user task combination you seek
     * @return The user task combination (UserTasks) you seek
     */
    UserTasks findUsertasksById(long id);

    /**
     * Remove the user task combination referenced by the given id
     *
     * @param id The primary key (long) of the user task combination you seek
     */
    void delete(long id);

    /**
     * Replaces the task of the user task combination you seek
     *
     * @param usertaskid  The primary key (long) of the user task combination you seek
     * @param description The new task description (String) for this user task combination
     * @return The UserTasks object that you updated including the new task address
     */
    UserTasks update(
            long usertaskid,
            String description);

    /**
     * Add a new User Email combination
     *
     * @param userid the userid of the new user task combination
     * @param description the task description of the new user task combination
     * @return the new user task combination
     */
    UserTasks save(
            long userid,
            String description);
}
