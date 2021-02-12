package com.saikat.poc.sentimentAnalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter@Setter
public class PlotRequestModel {

    private Float score;
    private Float magnitude;
}
