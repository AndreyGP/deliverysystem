package com.delsystem.instamart.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: JSONFileParser.java
 * Date/time: 11 октябрь 2021 in 23:44
 * The class instance takes a string with the location of the .json file,
 * reads from it and returns a List of objects passed in JSON
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */
@Component
public class JSONFileParser {
    private String filePath;

//    public JSONFileParser(final String filePath) {
//        this.filePath = filePath;
//    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private List<String> getJSONStringsList() {
        if (filePath == null) return Collections.emptyList();
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder strings = new StringBuilder();
            while (reader.ready()) strings.append(reader.readLine());
            result = splitJSONsStringToJSONList(strings.toString());
        } catch (IOException ex) {
            System.out.println("Проблема с файлом " + filePath);
        }
        return result;
    }

    private List<String> splitJSONsStringToJSONList(final String jsons) {
        return Arrays.stream(jsons.substring(2, jsons.length() - 2).split("},\\{")).collect(toList());
    }

    private List<List<String>> getOrdersList(final List<String> jsonStrings) {
        if (jsonStrings == null) return Collections.emptyList();
        return jsonStrings.parallelStream()
                .map(s -> Arrays.stream(s.replaceAll("\":","\" : ")
                                .replaceAll(":null", ": null")
                                .replaceAll("\\s:\\d", " : ")
                                .replaceAll(" : ", " | ")
                                .replaceAll(",\"", ";")
                                .replaceAll("\"", "")
                                .split(";"))
                        .collect(toList()))
                .collect(toList());
    }

    private List<Map<String, String>> getListOrdersMap(final List<List<String>> ordersList) {
        List<Map<String, String>> result = new ArrayList<>();
        if (ordersList.isEmpty()) return result;
        for (List<String> orders : ordersList) {
            Map<String, String> orderMap = new HashMap<>();
            for (String fieldOrder : orders) {
                String[] keyValue = fieldOrder.split(" \\| ");
                if (keyValue.length > 1) orderMap.put(keyValue[0], keyValue[1]);
            }
            result.add(orderMap);
        }
        return result;
    }

    public List<Map<String, String>> getCurrentOrdersMap() {
        return getListOrdersMap(getOrdersList(getJSONStringsList()));
    }

}
