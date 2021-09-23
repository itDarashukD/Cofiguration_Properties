package com.example.brands.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private String name;
    private  String timeBegin;
    private  String timeFinish;

}
