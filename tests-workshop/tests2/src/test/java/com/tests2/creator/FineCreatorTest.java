package com.tests2.creator;

import com.tests2.model.Fine;
import com.tests2.model.SpeedMeasurementRecord;
import com.tests2.util.LicenceStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FineCreatorTest {

    private FineCreator fineCreator;

     @BeforeEach
    public void setup() {
        fineCreator = new FineCreator();
    }

    @Test
    public void shouldThrowsExceptionWhenSpeedIsLegal() {
        // given
        SpeedMeasurementRecord speedMeasurementRecord = SpeedMeasurementRecord.builder()
                .driver("Tony Stark")
                .measuredSpeed(50)
                .speedLimitForArea(50)
                .build();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fineCreator.create(speedMeasurementRecord));

        // then
        assertEquals("Speed was correct", exception.getMessage());
    }

    @Test
    public void shouldCreateFineWithLicenceNotTakenWhenSpeedIsHigherThanLowerLimit() {
        // given
        SpeedMeasurementRecord record = SpeedMeasurementRecord.builder()
                .driver("Steve Rogers")
                .carName("Fiat 126p")
                .measuredSpeed(51)
                .speedLimitForArea(50)
                .build();

        // when
        Fine fine = fineCreator.create(record);

        // then
        assertEquals("Steve Rogers", fine.getDriver());
        assertEquals(105, fine.getAmountOfMoney());
        assertEquals(LicenceStatus.LICENCE_STAYS_WITH_DRIVER, fine.getLicenceStatus());
    }

    @Test
    public void shouldCreateFineWithLicenceNotTaken_WhenSpeedIsLowerThanHigherLimitByOne() {
        // given
        SpeedMeasurementRecord record = SpeedMeasurementRecord.builder()
                .driver("Thor")
                .carName("BMW")
                .measuredSpeed(100)
                .speedLimitForArea(70)
                .build();

        // when
        Fine fine = fineCreator.create(record);

        //then
        assertEquals("Thor", fine.getDriver());
        assertEquals(250, fine.getAmountOfMoney());
        assertEquals(LicenceStatus.LICENCE_STAYS_WITH_DRIVER, fine.getLicenceStatus());
    }

    @Test
    public void shouldCreateFineWithLicenceTakenFor3MonthsWhenSpeedIsHigherThanLowerLimitByOne() {
        // given
        SpeedMeasurementRecord record = SpeedMeasurementRecord.builder()
                .driver("Bruce Banner")
                .carName("Audi")
                .measuredSpeed(91)
                .speedLimitForArea(60)
                .build();

        // when
        Fine fine = fineCreator.create(record);

        //then
        assertEquals("Bruce Banner", fine.getDriver());
        assertEquals(410, fine.getAmountOfMoney());
        assertEquals(LicenceStatus.LICENCE_TAKEN_FOR_3_MONTHS, fine.getLicenceStatus());
    }

    @Test
    public void shouldCreateFineWithLicenceTakenFor3MonthsWhenSpeedIsLowerThanHigherLimitByOne() {
        // given
        SpeedMeasurementRecord record = SpeedMeasurementRecord.builder()
                .driver("Clint Barton")
                .carName("Ferrari")
                .measuredSpeed(100)
                .speedLimitForArea(50)
                .build();

        // when
        Fine fine = fineCreator.create(record);

        //then
        assertEquals("Clint Barton", fine.getDriver());
        assertEquals(600, fine.getAmountOfMoney());
        assertEquals(LicenceStatus.LICENCE_TAKEN_FOR_3_MONTHS, fine.getLicenceStatus());
    }

    @Test
    public void shouldCreateFineWithLicenceTakenLostWhenSpeedIsHigherThanLowerLimitByOne() {
        // given
        SpeedMeasurementRecord record = SpeedMeasurementRecord.builder()
                .driver("Bruce Wayne")
                .carName("Batmobil")
                .measuredSpeed(111)
                .speedLimitForArea(60)
                .build();

        // when
        Fine fine = fineCreator.create(record);

        //then
        assertEquals("Bruce Wayne", fine.getDriver());
        assertEquals(865, fine.getAmountOfMoney());
        assertEquals(LicenceStatus.LICENCE_LOST, fine.getLicenceStatus());
    }

}
