package sk.kosickaakademia.stovcikova.mysecondproject;


import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

    @RequestMapping(path= "/hello")
    public String getHello(){
        return "Hi. How are you? Do you like Java?";
    }
    @RequestMapping(path="/time")
    public String currentTime(){
        return new Date().toString();
    }

    @RequestMapping(path="/hello/sk")
    public String getHelloSk(){
        return "<h1>Ahoj, ako sa mas ty java expert???</h1>";
    }

    @RequestMapping(path="/hi")
    public String getHi(){
        return "<h2>Hi user. What are you doing??</h2>";
    }
    @RequestMapping(path="/hi/{username}")
    public String getHiWhitUsename(@PathVariable String username){
        return "<h2>Hi "+ username+". What are you doing??</h2>";
    }
    @RequestMapping(path = "/jsontest", method = RequestMethod.POST)
    public String getJsonTest(@RequestBody String userName){

        return "Your name is " + userName;
    }
}
