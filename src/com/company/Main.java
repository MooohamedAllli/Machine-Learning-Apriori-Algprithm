package com.company;

import java.io.*;
import java.util.*;

class calculations{
    String name;
    double value;
}

class custoemr{
    int age;
    String job;
    String marital;
    String education;
    String housing;
    String decision;
}
class treeSet{
    String desicion;
    String orgDesicion;
}

public class Main {

    public static double log2(double x) {
        return (Math.log(x) / Math.log(2));
    }

    public static void main(String[] args) throws IOException {

        ArrayList<custoemr> trainingSet = new ArrayList<>();
        ArrayList<custoemr> testingSet = new ArrayList<>();
        ArrayList<calculations> gain = new ArrayList<>();
        ArrayList<treeSet> branche = new ArrayList<>();

        int numRows = 1;

        File file = new File("E:\\Com & inf\\Computer Science level 4\\Data Mining\\Ass3\\src\\com\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 4000; i++) {
            String x = br.readLine();
            String[] xx = x.split("\\s+");
            custoemr c = new custoemr();

            c.age = Integer.parseInt(xx[0]);
            c.job = xx[1];
            c.marital = xx[2];
            c.education = xx[3];
            c.housing = xx[6];
            c.decision = xx[16];

            if (i<3000)
               trainingSet.add(c);

            if (i >= 3000)
                testingSet.add(c);
        }

        //Model 1 The desicion tree

        // Calc The Class entroupy
        double total_e =0  ;
        int counter1 =0 , counter2 =0;
        for (int i=0 ; i<trainingSet.size() ; i++) {
            if(trainingSet.get(i).decision.equals("yes"))
                counter1++;
            else
                counter2++;
        }
        double co1=(double) counter1/3000 ,co2= (double) counter2/3000 ;
        total_e = -((log2(co1)*co1)+(co2)*log2(co2));

        Vector <Double> v =new Vector <Double>();
        Vector <Double> v_Calculations =new Vector <Double>();
        double sumVector =0;
        calculations ca = new calculations();

        //entropy for age;
        v.add(calc_IG.calc("<" , "a" ,trainingSet ));
        v.add(calc_IG.calc(">" , "a" ,trainingSet ));
        for (int i=0 ; i<v.size() ; i++){
            sumVector += v.get(i);
        }
        ca.name = "age";
        ca.value = total_e - sumVector;
        gain.add(ca);
        v = new Vector<>();
        ca = new calculations();
        sumVector =0;

        //entropy for job;
        v.add(calc_IG.calc("unemployed" , "j" ,trainingSet ));
        v.add(calc_IG.calc("services" , "j" ,trainingSet ));
        v.add(calc_IG.calc("management" , "j" ,trainingSet ));
        v.add(calc_IG.calc("blue-collar" , "j" ,trainingSet ));
        v.add(calc_IG.calc("self-employed" , "j" ,trainingSet ));
        v.add(calc_IG.calc("technician" , "j" ,trainingSet ));
        v.add(calc_IG.calc("entrepreneur" , "j" ,trainingSet ));
        v.add(calc_IG.calc("admin." , "j" ,trainingSet ));
        v.add(calc_IG.calc("student" , "j" ,trainingSet ));
        v.add(calc_IG.calc("housemaid" , "j" ,trainingSet ));
        v.add(calc_IG.calc("retired" , "j" ,trainingSet ));
        v.add(calc_IG.calc("unknown" , "j" ,trainingSet ));
        for (int i=0 ; i<v.size() ; i++){
            sumVector += v.get(i);
        }
        ca.name = "job";
        ca.value = total_e -  sumVector;
        gain.add(ca);
        v = new Vector<>();
        ca = new calculations();
        sumVector =0;

        //entropy for marital;
        v.add(calc_IG.calc("married" , "m" ,trainingSet ));
        v.add(calc_IG.calc("single" , "m" ,trainingSet ));
        v.add(calc_IG.calc("divorced" , "m" ,trainingSet ));
        for (int i=0 ; i<v.size() ; i++){
            sumVector += v.get(i);
        }
        ca.name = "marital";
        ca.value = total_e -  sumVector;
        gain.add(ca);
        v = new Vector<>();
        ca = new calculations();
        sumVector =0;

        //entropy for education;
        v.add(calc_IG.calc("primary" , "e" ,trainingSet ));
        v.add(calc_IG.calc("secondary" , "e" ,trainingSet ));
        v.add(calc_IG.calc("tertiary" , "e" ,trainingSet ));
        v.add(calc_IG.calc("unknown" , "e" ,trainingSet ));
        for (int i=0 ; i<v.size() ; i++){
            sumVector += v.get(i);
        }
        ca.name = "education";
        ca.value = total_e -  sumVector;
        gain.add(ca);
        v = new Vector<>();
        ca = new calculations();
        sumVector =0;

        //entropy for housing;
        v.add(calc_IG.calc("unknown" , "e" ,trainingSet ));
        v.add(calc_IG.calc("no" , "h" ,trainingSet ));
        for (int i=0 ; i<v.size() ; i++){
            sumVector += v.get(i);
        }
        ca.name = "housing";
        ca.value = total_e - sumVector;
        gain.add(ca);
        v = new Vector<>();
        ca = new calculations();
        sumVector =0;

        Collections.sort(gain , (a , b) -> String.valueOf(a.value).compareTo(String.valueOf(b.value)));
        Collections.reverse(gain);

        Scanner in = new Scanner(System.in);

        System.out.println("Enter Percantage of testing Set : ");
        numRows = in.nextInt();
        numRows = (numRows * 1000)/100;

        mainMenu.main(branche, testingSet, trainingSet, numRows);


    }
}