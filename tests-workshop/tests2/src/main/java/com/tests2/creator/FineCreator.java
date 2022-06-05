package com.tests2.creator;

import com.tests2.model.Fine;
import com.tests2.model.SpeedMeasurementRecord;
import com.tests2.util.LicenceStatus;


public class FineCreator {

    public Fine create(SpeedMeasurementRecord speedMeasurementRecord) {
        if (speedMeasurementRecord.getMeasuredSpeed() <= speedMeasurementRecord.getSpeedLimitForArea()) {
            throw new IllegalArgumentException("Speed was correct");
        }

        int speedOverLimit = speedMeasurementRecord.getMeasuredSpeed() - speedMeasurementRecord.getSpeedLimitForArea();
        if (speedOverLimit <= 30) {
            return Fine.builder()
                    .driver(speedMeasurementRecord.getDriver())
                    .amountOfMoney(100 + speedOverLimit * 5)
                    .licenceStatus(LicenceStatus.LICENCE_STAYS_WITH_DRIVER)
                    .build();
        } else if (speedOverLimit <= 50) {
            return Fine.builder()
                    .driver(speedMeasurementRecord.getDriver())
                    .amountOfMoney(100 + speedOverLimit * 10)
                    .licenceStatus(LicenceStatus.LICENCE_TAKEN_FOR_3_MONTHS)
                    .build();
        } else {
            return Fine.builder()
                    .driver(speedMeasurementRecord.getDriver())
                    .amountOfMoney(100 + speedOverLimit * 15)
                    .licenceStatus(LicenceStatus.LICENCE_LOST)
                    .build();
        }
    }
}
