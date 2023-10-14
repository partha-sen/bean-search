package com.pss.beansearch.dto;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public record Dropdown(

        @Size(min = 3, max = 10)
        String name,
        String id,
        @NotBlank(message = "options cant be blank")
        List<DropDownElement> options) {}
