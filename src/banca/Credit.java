/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author vasua
 */
public class Credit implements ServiciiCredit{
    
    public static final int NR_MAX_RATE=10;
    
    private int nr_rate=0;
    private int idCredit;
    private int sumaImprumutata;
    private Valuta valuta;
    private float dobanda;
    private int durataLuni;
    private long Iban;
    
    private Rata[] array_rate;

    public Credit() {
        this.array_rate=new Rata[10];
    }

    public Credit(int idCredit, int sumaImprumutata, Valuta valuta, float dobanda, int durataLuni, long Iban) {
        
        this.array_rate=new Rata[10];
        this.idCredit = idCredit;
        this.sumaImprumutata = sumaImprumutata;
        this.valuta = valuta;
        this.dobanda = dobanda;
        this.durataLuni = durataLuni;
        this.Iban = Iban;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public int getSumaImprumutata() {
        return sumaImprumutata;
    }

    public void setSumaImprumutata(int sumaImprumutata) {
        this.sumaImprumutata = sumaImprumutata;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public float getDobanda() {
        return dobanda;
    }

    public void setDobanda(float dobanda) {
        this.dobanda = dobanda;
    }

    public int getDurataLuni() {
        return durataLuni;
    }

    public void setDurataLuni(int durataLuni) {
        this.durataLuni = durataLuni;
    }

    public long getIban() {
        return Iban;
    }

    public void setIban(long Iban) {
        this.Iban = Iban;
    }
    
    public void citesteCredit() {
        Scanner sc=new Scanner(System.in);
        
            System.out.println("Dati id credit");
            this.idCredit=sc.nextInt();
            System.out.println("Dati suma imprumutata");
            this.sumaImprumutata=sc.nextInt();     
            System.out.println("Dati valuta: 1-RON 2-USD 3-EUR");
            int option=sc.nextInt();
            switch(option)
            { 
                case 1:
                    this.valuta=Valuta.RON;
                    break;
                case 2:
                    this.valuta=Valuta.USD;
                    break;
                case 3:
                    this.valuta=Valuta.EUR;
                    break;
                default:
                    System.out.println("Tip valuta inexistenta. Introduceti din nou");       
            }
            
            System.out.println("Dati dobanda");
            this.dobanda=sc.nextFloat();
            System.out.println("Dati durata luni");
            this.durataLuni=sc.nextInt();
            
    }
    
 public void citesteCreditFis(Scanner sc,FileWriter audit) throws Exception {

        try {
            audit.write("In procedura citesteCreditFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            
 
           this.idCredit=sc.nextInt();
           audit.write("Id credit,"+this.idCredit+"\r\n");
           sc.nextLine();
           
            
            String option=sc.nextLine();
            switch(option)
            {
                case "ron":
                {
                    this.valuta=Valuta.RON;
                    audit.write("Valuta,RON"+"\r\n");
                    break;
                }
                case "usd":
                {
                    this.valuta=Valuta.USD;
                    audit.write("Valuta,USD"+"\r\n");
                    break;
                }
                case "eur" :
                {
                    this.valuta=Valuta.EUR;
                    audit.write("Valuta,EUR"+"\r\n");
                    break;
                }
                default:
                    audit.write("Tip valuta inexistent.");       
            }
            
            this.sumaImprumutata=sc.nextInt(); 
            audit.write("Suma imprumutata,"+this.sumaImprumutata+"\r\n");
            sc.nextLine();
            
            this.dobanda=sc.nextFloat();
            audit.write("Dobanda,"+this.dobanda+"\r\n");
            sc.nextLine();
            
            this.durataLuni=sc.nextInt();
            audit.write("Durata luni,"+this.durataLuni+"\r\n");
            sc.nextLine();
 
        } catch (IOException ex) {
            System.out.println("Eroare citire credit"+ex.getMessage());
        }       
}
   
    public void detaliiCredit() {
        
            System.out.println("Id credit: "+this.idCredit);
            System.out.println("Suma imprumutata: "+this.sumaImprumutata);
            System.out.println("Valuta: "+this.valuta);
            System.out.println("Dobanda: "+this.dobanda);
            System.out.println("Durata luni: "+this.durataLuni);
            System.out.println("Iban: "+this.Iban);
    }
    
    public void afisareRate() {
        for (int j=0; j<nr_rate ; j++) {
            this.array_rate[j].detaliiRata();
        }
    }
    
    public void adaugareRata(Rata c) {
        if(nr_rate>=NR_MAX_RATE)
        {
            System.out.println("Ati depasit nr maxim de rate");
            return;
        }
        c.setIdCredit(this.getIdCredit());     
        this.array_rate[nr_rate] = c;
        nr_rate++;
        
    }
    
    public Rata getRata(int i) {
         
         if(i>=NR_MAX_RATE || i<0)
        {
            System.out.println("Nr ratei depaseste limitele");
            //return; generam o exceptie
        }
        return this.array_rate[i-1];
        
    }
    
    public void afisareTot(){
        System.out.println("Credit ");
        this.detaliiCredit();
        for (int j=0; j<nr_rate ; j++) {
            this.array_rate[j].afisareTot();
        }
    }
    
public void detaliiCreditCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiCreditCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {

            csv.write(this.idCredit+","+this.sumaImprumutata+","+this.valuta+","+this.dobanda
            +","+this.durataLuni+","+this.Iban+"\r\n");
            
            for (int j=0; j<nr_rate ; j++) {
                this.array_rate[j].detaliiRataCSV(csv,audit);
        }
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}
    
    
    
}
