package com.theironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<Person> people = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        readFile();
        ArrayList<Person> personList = readFile();

        System.out.println(personList.toString());

        Spark.get(
                "/",
                (request, response) -> {
                    HashMap p = new HashMap();
                    p.put("personList", personList);
                    return new ModelAndView(p, "home.html");

                },
                new MustacheTemplateEngine()

        );



    }


    public static ArrayList<Person> readFile() throws FileNotFoundException {

        File peopleFile = new File("People.csv");
        Scanner fileScanner = new Scanner(peopleFile);
        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }
        return people;
    }
}
