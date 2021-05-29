package com.example.bookspace.enums;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    action, 
    adventure,
    literary, 
    contemporary, 
    black, 
    historic, 
    horror,
    romantic,
    erotic, 
    poetry, 
    theater,
    terror,
    comic,
    SciFi,
    fantasy,
    children,
    economy,
    kitchen,
    comedy,
    documentary,
    drama,
    suspense,
    teen,
    adult,
    war,
    crime,
    sport,
    history,
    biography,
    cops,
    family,
    western,
    religion,
    futurism, 
    other;
    

    public static boolean existsCategory(String category) {
        for (Category c: values()) {

            if (c.name().equals(category)) return true;
        }

        return false;
    }

    public static Category getCategory(String category) {
        return Category.valueOf(category);
    }

    public static List<Category> getCategories(List<String> favCategories) {
        List<Category> result = new ArrayList<>();
        for (String c: favCategories) {
            result.add(getCategory(c));
        }
        return result;
    }

    public static List<String> parseToString(List<Category> favCategories) {
        List<String> result = new ArrayList<>();
        for (Category c: favCategories) {
            System.out.println(c.name());
            result.add(c.name());
        }
        return result;
    }
}


    


