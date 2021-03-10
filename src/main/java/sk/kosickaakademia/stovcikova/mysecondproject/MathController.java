package sk.kosickaakademia.stovcikova.mysecondproject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
    @PostMapping(path="/add")
    public ResponseEntity<String> addTwoNumbers(@RequestBody String input){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            int a=Integer.parseInt(String.valueOf(jsonObject.get("a")));
            int b=Integer.parseInt(String.valueOf(jsonObject.get("b")));
            int result=a+b;

            JSONObject res = new JSONObject();
            res.put("result", result);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(res.toJSONString());
        }catch(ParseException parseException){
            parseException.printStackTrace();
        }catch (NumberFormatException e){
            JSONObject obj = new JSONObject();
            obj.put("Error:", "Incorrect body of the request");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }
    @GetMapping("/mul")
    public ResponseEntity<String> multiply(@RequestParam(value = "a") int value1, @RequestParam(value = "b")int value2){
        int result = (value1*value2);
        JSONObject obj = new JSONObject();
        obj.put("result", result);

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
    }
}

