package com.example.property_management.controller;

import com.example.property_management.dto.CalculadorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator") // class level mapping of url to a controller class

public class CalculatorController {
    // http://localhost:3000/api/v1/calculator/add?num111=6.7&num222=1.2 6.7 sera asignado a num1 y 1.2 sera asignado a num2
    //Best practice, name the params as the variables
    // /api/v1/calculator/add?num1=6.7&num2=1.2
    @GetMapping("/add/{num3}") //method level mapping of a url to a controller function
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2, @PathVariable("num3") Double num3){
        return num1 + num2 + num3;
    }

    //http://localhost:3000/api/v1/sub/4.8/9.7
    @GetMapping("/sub/{num111}/{num2}") // Map the values of url to java variables by Path variable method
    public Double substract(@PathVariable("num111") Double num1, @PathVariable("num2") Double num2) {
        Double result = null;
        if (num1 > num2) {
            result = num1 - num2;
        } else {
            result = num2 - num1;
        }
        return result;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculadorDTO calculadorDTO){
        Double result = null;
        result = calculadorDTO.getNum1() * calculadorDTO.getNum2() * calculadorDTO.getNum3() * calculadorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED); //Response with data and status code
        return responseEntity;
    }

}
