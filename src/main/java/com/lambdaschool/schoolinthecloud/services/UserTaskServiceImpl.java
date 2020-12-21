package com.lambdaschool.schoolinthecloud.services;


import com.lambdaschool.schoolinthecloud.exceptions.ResourceNotFoundException;
import com.lambdaschool.schoolinthecloud.models.User;
import com.lambdaschool.schoolinthecloud.models.UserTasks;
import com.lambdaschool.schoolinthecloud.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userTaskService")
public class UserTaskServiceImpl implements UserTaskService{
    @Autowired
    UserTaskRepository usertaskrepos;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<UserTasks> findAll() {
        List<UserTasks> list = new ArrayList<>();

        usertaskrepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public UserTasks findUsertasksById(long id) throws ResourceNotFoundException {
        return usertaskrepos.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Task id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {

        if (usertaskrepos.findById(id).isPresent()) {
            if (helperFunctions.isAuthorizedToMakeChange(usertaskrepos.findById(id)
                .get()
                .getUser()
                .getUsername())) {
                usertaskrepos.deleteById(id);
            }
        } else {
            throw new ResourceNotFoundException("UserTask with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public UserTasks update(long usertaskid, String description) {
        if (usertaskrepos.findById(usertaskid).isPresent()) {
            if (helperFunctions.isAuthorizedToMakeChange(usertaskrepos.findById(usertaskid)
                    .get()
                    .getUser()
                    .getUsername())) {
                UserTasks usertask = findUsertasksById(usertaskid);
                usertask.setDescription(description);
                return usertaskrepos.save(usertask);
            } else {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else {
            throw new ResourceNotFoundException("Usertask with id " + usertaskid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public UserTasks save(long userid, String description) {
        User currentUser = userService.findUserById(userid);

        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername())) {
            UserTasks newUserTask = new UserTasks(currentUser, description);
            return usertaskrepos.save(newUserTask);
        } else {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
