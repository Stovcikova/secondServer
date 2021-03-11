package sk.kosickaakademia.stovcikova.mysecondproject;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {
    String joke1 = "Police arrested two kids yesterday, one was drinking battery acid, the other was eating fireworks. They charged one – and let the other one off.";
    String joke2 = "I’m on a whiskey diet. I’ve lost three days already.";
    String joke3 = "”I went to the zoo the other day, there was only one dog in it, it was a shitzu.”";
    List<String> list = new ArrayList<>();

    public JokeController() {
        list.add(joke1);
        list.add(joke2);
        list.add(joke3);
    }


    @GetMapping("/jokes")
    public ResponseEntity<String> getJokes() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (String s : list)
            jsonArray.add(s);
        jsonObject.put("jokes", jsonArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id) {
        JSONObject jsonObject = new JSONObject();
        int status;
        if (id < 1 || id > list.size()) {
            jsonObject.put("ERROR", "Invalid id");
            status = 404;
        } else {
            jsonObject.put("joke", list.get(id - 1));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/joke")
    public ResponseEntity<String> getRandomJoke() {
        JSONObject jsonObject = new JSONObject();
        int status;
        if (list.size() == 0) {
            jsonObject.put("ERROR", "Invalid");
            status = 404;
        } else {
            Random random = new Random();
            int idNumber = random.nextInt(list.size());
            jsonObject.put("id", idNumber);
            jsonObject.put("joke", list.get(idNumber));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }
    //add vtipu cez POST
    @RequestMapping(path = "/joke/add", method = RequestMethod.POST)
    public ResponseEntity<String> addJoke(@RequestBody String input){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            String joke = (String)jsonObject.get("joke");
            list.add(joke);

            JSONObject js = new JSONObject();
            JSONArray array = new JSONArray();

            for(int i = 0; i < list.size(); i++){
                array.add(list.get(i));
            }

            js.put("jokes", array);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(js.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(NumberFormatException e){
            JSONObject obj = new JSONObject();
            obj.put("error", "INCORRECT INPUT");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        } catch(Exception e){
            JSONObject obj = new JSONObject();
            obj.put("error", "INCORRECT INPUT");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }
}