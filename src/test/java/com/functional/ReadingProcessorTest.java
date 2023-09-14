package com.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadingProcessorTest {
    ReadingProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new ReadingProcessor();
    }

    @Test
    void shouldIgnoreReadingsWithNoData() {
        var reading = buildReading("environmental", "", true, 0D);
        List<Reading> givenReadings = List.of(reading);

        assertThat(processor.processReadings(givenReadings)).isEmpty();
    }

    @Test
    void shouldIgnoreInactiveReadings() {
        var reading = buildReading("environmental", "some data", false, 0D);
        List<Reading> givenReadings = List.of(reading);

        assertThat(processor.processReadings(givenReadings)).isEmpty();
    }

    @Test
    void environmentalIsGrouped() {
        var reading = buildReading("environmental", "some data", true, 0D);
        List<Reading> givenReadings = List.of(reading);

        Map<String, List<Reading>> expectedMap = new HashMap<>();
        var expectedReading = buildReading("environmental", "some data", true, 32D);
        expectedMap.put("environmental", List.of(expectedReading));

        assertThat(processor.processReadings(givenReadings)).isEqualTo(expectedMap);
    }

    @Test
    void assetIsGrouped() {
        var reading = buildReading("asset", "some data", true, 0D);
        List<Reading> givenReadings = List.of(reading);

        Map<String, List<Reading>> expectedMap = new HashMap<>();
        var expectedReading = buildReading("asset", "some data", true, 32D);
        expectedMap.put("asset", List.of(expectedReading));

        assertThat(processor.processReadings(givenReadings)).isEqualTo(expectedMap);
    }

    @Test
    void vehicleIsGrouped() {
        var reading = buildReading("vehicle", "some data", true, 0D);
        List<Reading> givenReadings = List.of(reading);

        Map<String, List<Reading>> expectedMap = new HashMap<>();
        var expectedReading = buildReading("vehicle", "some data", true, 32D);
        expectedMap.put("vehicle", List.of(expectedReading));

        assertThat(processor.processReadings(givenReadings)).isEqualTo(expectedMap);
    }

    @Test
    void otherTypesAreIgnored() {
        var reading = buildReading("some other type", "some data", true, 0D);
        List<Reading> givenReadings = List.of(reading);

        assertThat(processor.processReadings(givenReadings)).isEmpty();
    }

    @Test
    void shouldGroupMultipleReadings() {
        var readingEnv = buildReading("environmental", "some data", true, 0D);
        List<Reading> givenReadings = new ArrayList<>(List.of(readingEnv));

        var readingAsset = buildReading("asset", "some data", true, 0D);
        givenReadings.add(readingAsset);

        var readingVehicle = buildReading("vehicle", "some data", true, 0D);
        givenReadings.add(readingVehicle);

        Map<String, List<Reading>> expectedMap = new HashMap<>();
        var expectedEnv = buildReading("environmental", "some data", true, 32D);
        var expectedAsset = buildReading("asset", "some data", true, 32D);
        var expectedVehicle = buildReading("vehicle", "some data", true, 32D);
        expectedMap.put("environmental", List.of(expectedEnv));
        expectedMap.put("asset", List.of(expectedAsset));
        expectedMap.put("vehicle", List.of(expectedVehicle));

        assertThat(processor.processReadings(givenReadings)).isEqualTo(expectedMap);
    }

    @Test
    void shouldConvertToFahrenheit() {
        var readingMinus30Celsius = buildReading("environmental", "some data", true, -30D);
        var reading20Celsius = buildReading("environmental", "some data", true, 20D);
        List<Reading> givenReadings = new ArrayList<>();
        givenReadings.add(readingMinus30Celsius);
        givenReadings.add(reading20Celsius);

        Map<String, List<Reading>> result = processor.processReadings(givenReadings);
        assertThat(result.get("environmental").get(0).getTemperature()).isEqualTo(-22D);
        assertThat(result.get("environmental").get(1).getTemperature()).isEqualTo(68D);
    }

    //TODO this test does not pass because currently the code mutates the input. This is a bad thing. Perhaps after
    //  your functional refactoring you can enable this test and get it to pass?
//    @Test
//    void shouldNotMutateReadings() {
//        var reading = buildReading("environmental", "some data", true, 0D);
//        List<Reading> givenReadings = List.of(reading);
//
//        Map<String, List<Reading>> result = processor.processReadings(givenReadings);
//
//        assertThat(reading.getTemperature()).isZero();
//    }

    private static Reading buildReading(String type, String data, boolean active, Double temperature) {
        return Reading.builder()
                .data(data)
                .active(active)
                .temperature(temperature)
                .type(type).build();
    }
}