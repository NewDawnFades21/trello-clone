package edu.zsc.todolistproject.util;

import java.util.List;

public class ListToStringUtils {

    public static String listToString(List<String> list){
        return String.join(",", list);
    }
}
