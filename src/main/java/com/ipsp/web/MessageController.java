package com.ipsp.web;

import com.ipsp.domain.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class MessageController {

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public @ResponseBody Message addScore(@RequestBody Message message){

        System.out.println("User: " + message.getUserId() + " Score: " + message.getResult());

        return message;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageController.class, args);
    }


}
