package com.company;

import java.util.ArrayList;

public class navieBaysModel {

    static class feature{
        String name;
        double yes;
        double no;
    }

    static class categ{
        String attr;
        feature f;
    }
    static double truncateTo( double unroundedNumber, int decimalPlaces ){
        int truncatedNumberInt = (int)( unroundedNumber * Math.pow( 10, decimalPlaces ) );
        double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
        return truncatedNumber;
    }

    public static double loop(String head , String attr , String des , ArrayList<custoemr> c) {
        double propability = 0;
        int counter = 0;

        //Total
        if (head.equals("d")) {
            for(int i=0 ; i<c.size() ; i++)
                if (c.get(i).decision.equals(attr))
                    counter++;
        }

        //Age
        else if (head.equals("a")) {
            if(attr.equals(">")){
                for(int i=0 ; i<c.size() ; i++)
                    if (c.get(i).age>=60 && c.get(i).decision.equals(des))
                        counter++;
            }
            if(attr.equals("<")){
                for(int i=0 ; i<c.size() ; i++)
                    if (c.get(i).age < 60 && c.get(i).decision.equals(des))
                        counter++;
            }
        }

        //job
        else if (head.equals("j")) {
            for(int i=0 ; i<c.size() ; i++)
                if (c.get(i).job.equals(attr) && c.get(i).decision.equals(des))
                    counter++;
        }

        //education
        else if (head.equals("e")) {
            for(int i=0 ; i<c.size() ; i++)
                if (c.get(i).education.equals(attr) && c.get(i).decision.equals(des))
                    counter++;
        }

        //marital
        else if (head.equals("m")) {
            for(int i=0 ; i<c.size() ; i++)
                if (c.get(i).marital.equals(attr) && c.get(i).decision.equals(des))
                    counter++;
        }

        //housing
        else if (head.equals("h")) {
            if(attr.equals("yes")){
                for(int i=0 ; i<c.size() ; i++)
                    if (c.get(i).housing.equals("yes") && c.get(i).decision.equals(des))
                        counter++;
            }
            if(attr.equals("no")){
                for(int i=0 ; i<c.size() ; i++)
                    if (c.get(i).housing.equals("no") && c.get(i).decision.equals(des))
                        counter++;
            }
        }

        int countAttr =0;
        for (int i=0 ; i<c.size() ; i++){
            if (c.get(i).decision.equals(des))
                countAttr++;
        }
        if(head.equals("d")){
            propability = (double)counter/c.size();
            propability = truncateTo(propability,2 );
        }
        else {
            propability = (double)counter/countAttr;
            propability = truncateTo(propability,2 );
        }
        return propability;
    }
    public static double getValue( String feature , String des , ArrayList<categ> c){
        double x =0;
        for (int i=0 ;i<c.size();i++){
            if( c.get(i).f.name.equals(feature)){
                if(des.equals("yes"))
                    x = c.get(i).f.yes;
                if(des.equals("no"))
                    x = c.get(i).f.no;
            }
        }
        return x;
    }




    public static ArrayList<categ> buildBaysModel(ArrayList<custoemr> trainingSet){
        ArrayList<categ> naves = new ArrayList<>();
        categ c = new categ();
        feature f = new feature();

        //Table of propability for(decision)
        double total_probabiltyofyes =(double) loop("d","yes" ,"s" ,trainingSet );
        double total_probabiltyofNo =(double) loop("d","no" ,"s" ,trainingSet );
        f.name = "descision"; f.yes = total_probabiltyofyes; f.no = total_probabiltyofNo;
        c.attr = "descision"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();


        //Table of propability for(housing)
        double housing_yes_probabiltyofyes =(double)loop("h","yes" ,"yes" ,trainingSet );
        double housing_yes_probabiltyofNo =(double)loop("h","yes" ,"no" ,trainingSet );
        f.name="yes" ;f.yes = housing_yes_probabiltyofyes; f.no = housing_yes_probabiltyofNo;
        c.attr = "housing"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double housing_no_probabiltyofyes =(double)loop("h","no" ,"yes" ,trainingSet );
        double housing_no_probabiltyofNo =(double)loop("h","no" ,"no" ,trainingSet );
        f.name="no" ;f.yes = housing_no_probabiltyofyes; f.no = housing_no_probabiltyofNo;
        c.attr = "housing"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();


        //Table of propability for(job)
        double manageYes =(double)loop("j","management" ,"yes" ,trainingSet );
        double manageNo =(double)loop("j","management" ,"no" ,trainingSet );
        f.name="management" ;f.yes = manageYes; f.no = manageNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double technicianYes =(double)loop("j","technician" ,"yes" ,trainingSet );
        double technicianNo =(double)loop("j","technician" ,"no" ,trainingSet );
        f.name="technician" ;f.yes = technicianYes; f.no = technicianNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double self_employedYes =(double)loop("j","self-employed" ,"yes" ,trainingSet );
        double self_employedNo =(double)loop("j","self-employed" ,"no" ,trainingSet );
        f.name="self-employed" ;f.yes = self_employedYes; f.no = self_employedNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double entrepreneurYes =(double)loop("j","entrepreneur" ,"yes" ,trainingSet );
        double entrepreneurNo =(double)loop("j","entrepreneur" ,"no" ,trainingSet );
        f.name="entrepreneur" ;f.yes = entrepreneurYes; f.no = entrepreneurNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double adminYes =(double)loop("j","admin." ,"yes" ,trainingSet );
        double adminNo =(double)loop("j","admin." ,"no" ,trainingSet );
        f.name="admin." ;f.yes = adminYes; f.no = adminNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double studentYes =(double)loop("j","student" ,"yes" ,trainingSet );
        double studentNo =(double)loop("j","student" ,"no" ,trainingSet );
        f.name="student" ;f.yes = studentYes; f.no = studentNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double housemaidYes =(double)loop("j","housemaid" ,"yes" ,trainingSet );
        double housemaidNo =(double)loop("j","housemaid" ,"no" ,trainingSet );
        f.name="housemaid" ;f.yes = housemaidYes; f.no = housemaidNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double retiredYes =(double)loop("j","retired" ,"yes" ,trainingSet );
        double retiredNo =(double)loop("j","retired" ,"no" ,trainingSet );
        f.name="retired" ;f.yes = retiredYes; f.no = retiredNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double unknownYes =(double)loop("j","unknown" ,"yes" ,trainingSet );
        double unknownNo =(double)loop("j","unknown" ,"no" ,trainingSet );
        f.name="unknown" ;f.yes = unknownYes; f.no = unknownNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double unemployedYes =(double)loop("j","unemployed" ,"yes" ,trainingSet );
        double unemployedNo =(double)loop("j","unemployed" ,"no" ,trainingSet );
        f.name="unemployed" ;f.yes = unemployedYes; f.no = unemployedNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double servicesYes =(double)loop("j","services" ,"yes" ,trainingSet );
        double servicesNo =(double)loop("j","services" ,"no" ,trainingSet );
        f.name="services" ;f.yes = servicesYes; f.no = servicesNo;
        c.attr = "job"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        //Table of propability for(age)
        double over60_probabiltyofyes =(double)loop("a",">" ,"yes" ,trainingSet );
        double over60_probabiltyofNo =(double)loop("a",">" ,"no" ,trainingSet );
        double under60_probabiltyofyes =(double)loop("a","<" ,"yes" ,trainingSet );
        double under60_probabiltyofNo =(double)loop("a","<" ,"no" ,trainingSet );

        //Table of propability for(education)
        double primary_probabiltyofyes =(double)loop("e","primary" ,"yes" ,trainingSet);
        double primary_probabiltyofNo =(double)loop("e","primary" ,"no" ,trainingSet);
        f.name="primary" ;f.yes = primary_probabiltyofyes; f.no = primary_probabiltyofNo;
        c.attr = "education"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double secondary_probabiltyofyes =(double)loop("e","secondary" ,"yes" ,trainingSet);
        double secondary_probabiltyofNo =(double)loop("e","secondary" ,"no" ,trainingSet);
        f.name="secondary" ;f.yes = secondary_probabiltyofyes; f.no = secondary_probabiltyofNo;
        c.attr = "education"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double teriority_probabiltyofyes =(double)loop("e","tertiary" ,"yes" ,trainingSet);
        double teriority_probabiltyofNo =(double)loop("e","tertiary" ,"no" ,trainingSet);
        f.name="teriority" ;f.yes = teriority_probabiltyofyes; f.no = teriority_probabiltyofNo;
        c.attr = "education"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double unknown_probabiltyofyes =(double)loop("e","unknown" ,"yes" ,trainingSet);
        double unknown_probabiltyofNo =(double)loop("e","unknown" ,"no" ,trainingSet);
        f.name="unknown" ;f.yes = unknown_probabiltyofyes; f.no = unknown_probabiltyofNo;
        c.attr = "education"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        //Table of propability for(marital)
        double married_probabiltyofyes =(double)loop("m","married" ,"yes" ,trainingSet );
        double married_probabiltyofNo =(double)loop("m","married" ,"no" ,trainingSet );
        f.name="married" ;f.yes = married_probabiltyofyes; f.no = married_probabiltyofNo;
        c.attr = "marital"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double single_probabiltyofyes =(double)loop("m","single" ,"yes" ,trainingSet );
        double single_probabiltyofNo =(double)loop("m","single" ,"no" ,trainingSet );
        f.name="single" ;f.yes = single_probabiltyofyes; f.no = single_probabiltyofNo;
        c.attr = "marital"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double divorced_probabiltyofyes =(double)loop("m","divorced" ,"yes" ,trainingSet );
        double divorced_probabiltyofNo =(double)loop("m","divorced" ,"no" ,trainingSet );
        f.name="divorced" ;f.yes = divorced_probabiltyofyes; f.no = divorced_probabiltyofNo;
        c.attr = "marital"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        double unknown_mar_probabiltyofyes =(double)loop("m","unknown" ,"yes" ,trainingSet );
        double unknown_mar_probabiltyofNo =(double)loop("m","unknown" ,"no" ,trainingSet );
        f.name="unknown" ;f.yes = unknown_mar_probabiltyofyes; f.no = unknown_mar_probabiltyofNo;
        c.attr = "marital"; c.f = f;
        naves.add(c); c = new categ(); f= new feature();

        return naves;
    }
}
