package com.elouazzani.util;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elouazzani.security.AppUser;
import com.elouazzani.security.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {

        if (!userService.findAll().iterator().hasNext()) {
            logger.info("No Users accounts found.\nCreating some users");

            AppUser user = new AppUser("jamal@gmail.com", "password", "Mohamed Jamal");
            userService.save(user);
        }
    }
}
