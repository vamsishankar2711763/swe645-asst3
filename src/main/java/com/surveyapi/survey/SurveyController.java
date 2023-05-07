package com.surveyapi.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @CrossOrigin
    @RequestMapping(value = "/surveys", method = RequestMethod.GET)
    @ResponseBody
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/survey", method = RequestMethod.POST)
    @ResponseBody
    public Survey addSurvey(@RequestBody Survey survey) {
        try{
        return surveyRepository.save(survey);
        }
        catch(Exception ex){
            throw ex;
        }
    }

}
