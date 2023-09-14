package com.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingProcessor {

    public Map<String, List<Reading>> processReadings(List<Reading> readings) {

        Map<String, List<Reading>> grouped = new HashMap<>();

        for (int i = 0; i < readings.size(); i++) {
            Reading reading = readings.get(i);

            // only process if we received data for reading
            if (!reading.getData().isEmpty() && reading.isActive()) {
                // convert temperature readings to Fahrenheit
                reading.setTemperature(reading.getTemperature() * 1.8 + 32);

                // group by reading type
                if (reading.getType().equals("environmental")) {
                    if (grouped.get("environmental") == null) {
                        grouped.put("environmental", new ArrayList<Reading>());
                    }
                    grouped.get("environmental").add(reading);
                } else if (reading.getType().equals("asset")) {
                    if (grouped.get("asset") == null) {
                        grouped.put("asset", new ArrayList<Reading>());
                    }
                    grouped.get("asset").add(reading);
                } else if (reading.getType().equals("vehicle")) {
                    if (grouped.get("vehicle") == null) {
                        grouped.put("vehicle", new ArrayList<Reading>());
                    }
                    grouped.get("vehicle").add(reading);
                }
            }
        }

        return grouped;
    }
}