package com.real.matcher.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Person {

    private Integer movieId;
    private String name;
    private String role;
}
