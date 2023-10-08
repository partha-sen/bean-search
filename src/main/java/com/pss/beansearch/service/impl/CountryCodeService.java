package com.pss.beansearch.service.impl;

import com.pss.beansearch.dto.Dropdown;
import com.pss.beansearch.dto.DropDownElement;
import com.pss.beansearch.service.UxDropdownPopular;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryCode")
public class CountryCodeService implements UxDropdownPopular {
    @Override
    public Dropdown fetchDropDownItem() {
        DropDownElement e1 = new DropDownElement("IN","India");
        DropDownElement e2 = new DropDownElement("US","United Sate");
        List<DropDownElement> elements = List.of(e1, e2);
        return new Dropdown("CountryCode","cc", elements);
    }
}
