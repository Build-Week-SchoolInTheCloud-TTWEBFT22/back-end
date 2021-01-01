package com.lambdaschool.schoolinthecloud;

import com.github.javafaker.Educator;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.schoolinthecloud.models.*;
import com.lambdaschool.schoolinthecloud.services.RoleService;
import com.lambdaschool.schoolinthecloud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("student");
        Role r3 = new Role("volunteer");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getRoles()
                .add(new UserRoles(u1,
                        r1));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r2));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r3));
        userService.save(u1);

        // data, user
        User u2 = new User("student",
                "1234567",
                "student@lambdaschool.local");
        u2.getRoles()
                .add(new UserRoles(u2,
                        r2));
        userService.save(u2);

        // user
        User u3 = new User("volunteer",
                "password",
                "volunteer@lambdaschool.local");
        u3.getRoles()
                .add(new UserRoles(u3,
                        r3));
        u3.getUsertasks()
                .add(new UserTasks( u3, "Teach Math"));
        u3.getUsertasks()
                .add(new UserTasks( u3, "Teach Science"));
        u3.getUsertasks()
                .add(new UserTasks( u3, "Teach History"));
        u3.getUsertasks()
                .add(new UserTasks( u3, "Teach Music"));
        userService.save(u3);


        if (true)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress());
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r3));
                fakeUser.setCountry(nameFaker.country().name());
                fakeUser.setAvailability(nameFaker.date().future(30, TimeUnit.DAYS).toString());
                fakeUser.getUsertasks()
                    .add(new UserTasks(fakeUser, "Teach " +
                      nameFaker.job().field()));
                userService.save(fakeUser);
            }
        }
    }
}