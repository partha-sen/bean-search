package com.pss.beansearch.service;

import com.pss.beansearch.dto.Dropdown;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DropdownService {
    private Map<String, UxDropdownPopular> beanNameAndInstanceMap;

    private Set<String> allBeanNamesTypeDropdownService;
    public DropdownService(ApplicationContext springContext) {
        this.beanNameAndInstanceMap = springContext.getBeansOfType(UxDropdownPopular.class);
        this.allBeanNamesTypeDropdownService = beanNameAndInstanceMap.keySet();
    }

    public Set<String> getAllBeanName(){
        return this.allBeanNamesTypeDropdownService;
    }

    //todo introduce readability by introducing Intermediate records type
    public List<Dropdown> getAllDropdown(){
        return beanNameAndInstanceMap.values().stream()
                .map(UxDropdownPopular::fetchDropDownItem)
                .collect(Collectors.toList());
    }

    //todo introduce readability by introducing Intermediate records type
    public List<Dropdown> getDropdownsByKeys(@RequestBody List<String> dropdownKeys){
        return dropdownKeys.stream()
                .map(beanNameAndInstanceMap::get) // todo it may leads to nullPointerException fix it
                .map(UxDropdownPopular::fetchDropDownItem)
                .collect(Collectors.toList());
    }

    public Dropdown getDropdown(String dropdownKey) {
        UxDropdownPopular dropdownPopular = beanNameAndInstanceMap.get(dropdownKey);
        return dropdownPopular.fetchDropDownItem();
    }
}
