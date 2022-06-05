package com.tests2.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpeedMeasurementRecord {

    private String driver;

    private String carName;

    private int measuredSpeed;

    private int speedLimitForArea;

}
