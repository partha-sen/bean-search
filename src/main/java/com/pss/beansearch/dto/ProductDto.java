package com.pss.beansearch.dto;

public record ProductDto(long id,
                          String name,
                          long version,
                          Double price) {}