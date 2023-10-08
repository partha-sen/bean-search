package com.pss.beansearch.controller;

import com.pss.beansearch.dto.Dropdown;
import com.pss.beansearch.exception.InputValidationException;
import com.pss.beansearch.service.DropdownService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/org/referenceData")
public class ReferenceDataController {

    DropdownService dropdownService;
    Set<String> allBeanNameOfTypeDropdownService;


    @GetMapping(value = "/v1/dropdowns", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dropdown> getAllDropdown(){
        return dropdownService.getAllDropdown();
    }

    @PostMapping(value = "/v1/dropdowns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dropdown> getDropdownsByKeys(@RequestBody List<String> dropdownKeys){
        validateInput(dropdownKeys);
        return dropdownService.getDropdownsByKeys(dropdownKeys);
    }

    @GetMapping(value = "/v1/dropdowns/{dropdownKey}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Dropdown getDropdown(@PathVariable String dropdownKey){
        validateInput(dropdownKey);
        return dropdownService.getDropdown(dropdownKey);
    }

    public ReferenceDataController(DropdownService dropdownService){
        this.dropdownService = dropdownService;
        this.allBeanNameOfTypeDropdownService = dropdownService.getAllBeanName();
    }

    private void validateInput(List<String> dropdownKeys){
        if(Objects.isNull(dropdownKeys) || dropdownKeys.isEmpty()){
            throw new InputValidationException("DropdownKey is empty!");
        }
        if ( !allBeanNameOfTypeDropdownService.containsAll(dropdownKeys)){
            throw new InputValidationException("Invalid dropdownKeys!");
        }

    }
    private void validateInput(String dropdownKey){
        if ( !allBeanNameOfTypeDropdownService.contains(dropdownKey)){
            throw new InputValidationException("Invalid dropdownKey!");
        }
    }

}
