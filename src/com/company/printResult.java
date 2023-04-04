package com.company;

public class printResult {

    public static void result( int yes , int no , int acc , char x){
        System.out.println( "--------------------------------------------");
        if (x == 't')
            System.out.println( "For The tree Model");
        if (x == 'n')
            System.out.println( "For The Baysian Model");
        System.out.println( "Number of Yes : " + yes);
        System.out.println( "Number of no : " + no );
        System.out.println( "Percentage of accuracy : " + acc +"%");
        System.out.println( "--------------------------------------------");

    }
}