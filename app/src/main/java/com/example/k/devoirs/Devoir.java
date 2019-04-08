package com.example.k.devoirs;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by k on 21/03/2016.
 */
public class Devoir {
    public String classe,travail,ok,pour,du;


    public Devoir()
    {

    }
    Devoir(String classe,String pour,String du, String travail,String ok)
    {
        //this.code=code;this.name=name;this.continent=continent;this.region=region;

        this.classe = classe;
        this.pour = pour;
        this.du = du;
        this.travail = travail;
        this.ok = ok;
    }



    public String getClasse() { return classe;}
    public void SetClasse(String x) { this.classe=x;}

    public String getTravail() { return travail;}
    public void SetTravail(String x) { this.travail=x;}

    public String getPour() { return pour;}
    public void SetPour(String x) { this.pour=x;}

    public String getDu() { return du;}
    public void SetDu(String x) { this.du=x;}

    public String getOk() { return ok;}
    public void SetOk(String x) { this.ok=x;}

public String toString() {
    String ret = "";
    ret = ret +"class= "+ classe;
    ret = ret +"pour= "+ pour;
    ret = ret +"du= "+ du;
    ret = ret +"travail= "+ travail;
    ret = ret +"ok= "+ ok;
    return ret;
}
    public static Comparator<Devoir> CC = new Comparator<Devoir>() {
        public int compare(Devoir p1, Devoir p2)
        {
            String c1 = p1.getPour();
            String c2 = p2.getPour();
            return c1.compareTo(c2);
        }};


}
