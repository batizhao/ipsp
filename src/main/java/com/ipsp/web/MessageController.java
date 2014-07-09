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
    public String addScore(@RequestParam String message){

        //From Session
        int userId = 1;

        System.out.println("User: " + userId + " Score: " + message);

        return "ok";
    }

    @RequestMapping(value = "/score1", method = RequestMethod.POST)
    public String addScore1(@RequestBody Message message){

        System.out.println("User: " + message.getUserId() + " Score: " + message.getResult());

        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageController.class, args);
    }


}
