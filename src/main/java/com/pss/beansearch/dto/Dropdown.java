package com.pss.beansearch.dto;

import java.util.List;

public record Dropdown(String name, String id, List<DropDownElement> options) {
}
