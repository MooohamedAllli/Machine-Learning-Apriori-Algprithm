package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class mainMenu {

    public static int calcTreeAccuracy(ArrayList<treeSet> t){
        int result =0;
        for (int i=0 ; i<t.size() ; i++)
            if(t.get(i).orgDesicion.equals(t.get(i).desicion))
                result++;

        result = (result*100)/t.size();

        return result;
    }

    public static int calcClassLables(ArrayList<treeSet> t ) {
        int numOfYes =0;
        for (int i=0 ; i<t.size() ; i++)
            if(t.get(i).desicion.equals("yes"))
                numOfYes++;

        return numOfYes;
    }

    public static void main(ArrayList<treeSet> branche , ArrayList<custoemr> testingSet , ArrayList<custoemr> trainingSet , int numRows ){
//        Scanner in = new Scanner(System.in);

        int x=0;
        int acuracy =0 , classLabeyes =0 , classLabelNo =0 ;
            branche = buildTreeResult.buildTreeResult(testingSet, numRows);

            acuracy = calcTreeAccuracy(branche);
            classLabeyes = calcClassLables(branche);
            classLabelNo = numRows - calcClassLables(branche);
            printResult.result(classLabeyes, classLabelNo, acuracy,'t');
            System.out.println((" "));


            classLabelNo =0; classLabeyes =0; acuracy=0;
            ArrayList<navieBaysModel.categ> c = new ArrayList<>();
            branche = new ArrayList<>();
            treeSet tt = new treeSet();
            c = navieBaysModel.buildBaysModel(trainingSet);

            for (int i=0 ; i<numRows ;i++){
                double prob_yes =0 , prob_no =0;
                double T_prob_yes =0 , T_prob_no =0;
                double v1yes=0 ,v2yes=0 ,v3yes=0 ,v4yes=0 ,v1no=0 ,v2no=0 ,v3no=0 ,v4no=0;


                v1yes =navieBaysModel.getValue(testingSet.get(i).job, "no", c);
                v2yes =navieBaysModel.getValue(testingSet.get(i).education, "yes", c);
                v3yes =navieBaysModel.getValue(testingSet.get(i).marital, "yes", c);
                v4yes =navieBaysModel.getValue(testingSet.get(i).housing, "yes", c);
                T_prob_yes=navieBaysModel.getValue("descision", "no",c );
                T_prob_no=navieBaysModel.getValue("descision", "yes",c );
                v1no =navieBaysModel.getValue(testingSet.get(i).job, "no", c);
                v2no =navieBaysModel.getValue(testingSet.get(i).education, "no", c);
                v3no =navieBaysModel.getValue(testingSet.get(i).marital, "no", c);
                v4no =navieBaysModel.getValue(testingSet.get(i).housing, "no", c);

                prob_yes = T_prob_yes * v1yes * v2yes * v3yes * v4yes;
                prob_no = T_prob_no * v1no * v2no * v3no * v4no;

                prob_yes = navieBaysModel.truncateTo(prob_yes, 12);
                prob_no = navieBaysModel.truncateTo(prob_no,12 );

                if(prob_yes > prob_no){
                    tt.desicion = "yes";
                    tt.orgDesicion = testingSet.get(i).decision;
                    branche.add(tt);
                    tt = new treeSet();
                }
                else if(prob_yes <= prob_no){
                    tt.desicion = "no";
                    tt.orgDesicion = testingSet.get(i).decision;
                    branche.add(tt);
                    tt = new treeSet();
                }
                System.out.println(tt.desicion +" " + tt.orgDesicion +" " +prob_yes + " " + prob_no );

            }

        acuracy = calcTreeAccuracy(branche);
        classLabeyes = calcClassLables(branche);
        classLabelNo = numRows - calcClassLables(branche);
        System.out.println((" "));
        if(classLabelNo<0)
                classLabelNo = classLabelNo*(-1);
        printResult.result(classLabeyes, classLabelNo, acuracy,'n');

    }
}
