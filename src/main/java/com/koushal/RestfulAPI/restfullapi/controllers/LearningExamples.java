package com.koushal.RestfulAPI.restfullapi.controllers;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.koushal.RestfulAPI.restfullapi.data.FilteringClass;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningExamples {

    @GetMapping("/filters")
    public FilteringClass getFilters(){
        return new FilteringClass("value1","value2","value3");
    }

    @GetMapping("/filters-static")
    public FilteringClass getStaticFilters(){
        //because we have used @JSONIgnore annotation above second value so in response second parameter won't be sent.
        return new FilteringClass("value1","value2","value3");
    }


    @GetMapping("/filters-dynamic")
    public MappingJacksonValue getDynamicFilter(){
        //here first we create filter then we use MappingJacksonValue class
        FilteringClass filteringClass=new FilteringClass("value1","value2","value3");
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(filteringClass);
        //here we are using SimpleBeanPropertyFilter class to create a simple filter
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        //MappingJacksonValue class need FilterProvider
        //here only we need to provide the name of JSONFilter class, means on which class we have annotated this.
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilteringClassFilter", filter );
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

}
