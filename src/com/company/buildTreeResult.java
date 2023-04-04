package com.company;

import java.util.ArrayList;

public class buildTreeResult {


    public static ArrayList<treeSet> buildTreeResult(ArrayList<com.company.custoemr> testingSet , int num){
        com.company.treeSet t = new treeSet();
        ArrayList<com.company.treeSet> branche = new ArrayList();

        for (int i=0 ; i<num ; i++){
            t = new com.company.treeSet();
            if (testingSet.get(i).housing.equals("no")){
                t.desicion = "no";
                t.orgDesicion = testingSet.get(i).decision;
                branche.add(t);
            }
            else if(testingSet.get(i).housing.equals("yes")){
                if(testingSet.get(i).job.equals("technician")){
                    t.desicion = "yes";
                    t.orgDesicion = testingSet.get(i).decision;
                    branche.add(t);
                }
                else {
                    t.desicion = "no";
                    t.orgDesicion = testingSet.get(i).decision;
                    branche.add(t);
                }
            }
            else {
                t.desicion = "no";
                t.orgDesicion = testingSet.get(i).decision;
                branche.add(t);
            }
        }
        return branche;
    }
}