package com.example.bookspace.enums;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    Accion, 
    Aventura,
    Literaria, 
    Contemporanea, 
    Negra, 
    Historica, 
    Romantica,
    Erotica, 
    Poesia, 
    Teatro,
    Terror,
    Comic,
    Manga, 
    SciFi,
    Fantasia,
    Infantil,
    Economia,
    Cocina,
    Comedia,
    Documental,
    Drama,
    Suspense,
    Juvenil,
    Adulto,
    Belico,
    Crimen,
    Deporte,
    Historia,
    Biografia,
    Policiaco,
    Familiar,
    Western,
    Religion,
    Futurismo, 
    Otro;
    

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
            System.out.println(c);
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


    


