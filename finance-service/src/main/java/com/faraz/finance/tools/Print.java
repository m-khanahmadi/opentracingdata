package com.faraz.finance.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Print {
    public static void print(Object item) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
        } catch (Exception er) {
            System.out.println("Catch.........");
        }
    }

    public static void print(String title, Object item) {
        System.out.print(title + "\t");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
        } catch (Exception er) {
            System.out.println("Catch.........");
        }
    }
}
