package com.company;

import java.util.ArrayList;

public class calc_IG {
    public static double log2(double x) {
        return (Math.log(x) / Math.log(2));
    }

    public static double calc(String attr  , String head , ArrayList<custoemr> c){
        double value = 0;

        if (head.equals("a")){
            int countAtrr =0 ,counter1 =0 , counter2 =0;
            if (attr.equals(">")){
                for (int i=0 ; i<c.size() ; i++){
                    if(c.get(i).age>=60){
                        countAtrr++;
                        if(c.get(i).decision.equals("yes"))
                            counter1++;
                    }
                }
            }
            else if(attr.equals("<")) {
                for (int i=0 ; i<c.size() ; i++) {
                    if (c.get(i).age < 60) {
                        countAtrr++;
                        if (c.get(i).decision.equals("yes"))
                            counter1++;
                    }
                }
            }
            counter2 = countAtrr - counter1;
            double co1 =(double) counter1/countAtrr , co2 =(double) counter2/countAtrr ;

            double ratio = (double) countAtrr/3000;
            value = (-((log2(co1)*co1)+(co2)*log2(co2)))*(ratio);
        }

        else if (head.equals("j")) {
            int counter1 =0 , counter2 =0 , countAtrr =0;
            for (int i=0 ; i<c.size() ; i++){
                if (c.get(i).job.equals(attr)){
                    countAtrr++;
                    if(c.get(i).decision.equals("yes"))
                        counter1++;
                }
            }
            counter2 = countAtrr - counter1;
            double co1 =(double) counter1/countAtrr , co2 =(double) counter2/countAtrr ;

            double ratio = (double) countAtrr/3000;
            value = (-((log2(co1)*co1)+(co2)*log2(co2)))*(ratio);
        }

        else if (head.equals("m")) {
            int counter1 =0 , counter2 =0 , countAtrr =0;
            for (int i=0 ; i<c.size() ; i++){
                if (c.get(i).marital.equals(attr)){
                    countAtrr++;
                    if(c.get(i).decision.equals("yes"))
                        counter1++;
                }
            }
            counter2 = countAtrr - counter1;
            double co1 =(double) counter1/countAtrr , co2 =(double) counter2/countAtrr ;

            double ratio = (double) countAtrr/3000;
            value = (-((log2(co1)*co1)+(co2)*log2(co2)))*(ratio);
        }
        else if (head.equals("e")) {
            int counter1 =0 , counter2 =0 , countAtrr =0;
            for (int i=0 ; i<c.size() ; i++){
                if (c.get(i).education.equals(attr)){
                    countAtrr++;
                    if(c.get(i).decision.equals("yes"))
                        counter1++;
                }
            }
            counter2 = countAtrr - counter1;
            double co1 =(double) counter1/countAtrr , co2 =(double) counter2/countAtrr ;

            double ratio = (double) countAtrr/3000;
            value = (-((log2(co1)*co1)+(co2)*log2(co2)))*(ratio);
        }
        else if (head.equals("h")) {
            int counter1 =0 , counter2 =0 , countAtrr =0;
            for (int i=0 ; i<c.size() ; i++){
                if (c.get(i).housing.equals(attr)){
                    countAtrr++;
                    if(c.get(i).decision.equals("yes"))
                        counter1++;
                }
            }
            counter2 = countAtrr - counter1;
            double co1 =(double) counter1/countAtrr , co2 =(double) counter2/countAtrr ;

            double ratio = (double) countAtrr/3000;
            value = (-((log2(co1)*co1)+(co2)*log2(co2)))*(ratio);
        }

        return value;
    }

}
