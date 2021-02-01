package com.webhook.User.controller;

import com.webhook.User.model.URLCaller;
import com.webhook.User.requestforms.UserRequestForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

/**
 * This is the controller for the api that reads incoming communications and
 * sends the data to where it needs to be. This is initial to be tested
 * @author Netanel Avraham Eklind
 */

@RestController
public class RestUserController {

    @PostMapping("/userJoined")
    public void userJoined(@RequestBody UserRequestForm userRequestForm){

        System.out.println("User: "+userRequestForm.getUserName()+" joined with tag: "+userRequestForm.getTag());

        String url = "http://127.0.0.1:8000/userOnline";

        try {
            URLCaller urlCaller = new URLCaller(url);
            urlCaller.callServer(userRequestForm.getUserName(),userRequestForm.getTag());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
