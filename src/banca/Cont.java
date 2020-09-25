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
public class Cont implements ServiciiCont{
    
    public static final int NR_MAX_CARDURI=10;
    public static final int NR_MAX_CREDITE=10;
    
    private int nr_carduri=0;
    private int nr_credite=0;
    private long Iban;
    private Valuta  valuta;
    private float suma;
    private long cnp;
    
    private Card [] array_carduri;
    private Credit[] array_credite;

    public Cont() {
        this.array_carduri=new Card[10];
        this.array_credite=new Credit[10];
        //restul var se init cu 0 automat
    }

    public Cont(long Iban,Valuta  valuta, float suma, long cnp) {
        
        this.array_carduri=new Card[10];
        this.array_credite=new Credit[10];
        this.Iban = Iban;
        this.valuta = valuta;
        this.suma = suma;
        this.cnp = cnp;
    }

    public long getIban() {
        return Iban;
    }

    public void setIban(long Iban) {
        this.Iban = Iban;
    }

    public Valuta  getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }
    
    public void depunere(float suma) {
        this.suma += suma;
    }
    
    public void retragere(float suma) {
        if (this.suma >= suma) 
        {
            this.suma -=suma;
        } else 
        {
            // se arunca exceptie
            //throw new NotEnoughMoneyException();
            System.out.println("Nu aveti destui bani");
        }
    }
    
    public void afisareSold() {
        
            System.out.println("Soldul este "+this.suma);
    }
    
    public void detaliiCont() {
        
            System.out.println("Iban: "+this.Iban);
            System.out.println("Valuta: "+this.valuta);
            System.out.println("Sold: "+this.suma);
            System.out.println("CNP client: "+this.cnp);
            
    }
    
    public void citesteCont() {
        Scanner sc=new Scanner(System.in);
        
            System.out.println("Dati Iban");
            this.Iban=sc.nextLong();
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
            System.out.println("Dati Suma");
            this.suma=sc.nextFloat();
             
    } 

public void citesteContFis(Scanner sc,FileWriter audit) throws Exception {
    
        try {
            audit.write("In procedura citesteContFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            
            this.Iban=sc.nextLong();
            audit.write("Iban,"+this.Iban+"\r\n");
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
            
            
            
            this.suma=sc.nextFloat();
            audit.write("Suma,"+this.suma+"\r\n");
            sc.nextLine();

            
            
            
        } catch (IOException ex) {
            System.out.println("Eroare citire cont"+ex.getMessage());
        }       
}    
    
    public void afisareCarduri() {
        for (int j=0; j<nr_carduri ; j++) {
            this.array_carduri[j].detaliiCard();
        }
    }
    
    public void afisareCredite() {
        for (int j=0; j<nr_credite ; j++) {
            this.array_credite[j].detaliiCredit();
        }
    }
    public void adaugareCard(Card c) {
        if(nr_carduri>=NR_MAX_CARDURI)
        {
            System.out.println("Ati depasit nr maxim de carduru");
            return;
        }
        c.setIban(this.getIban());  
        this.array_carduri[nr_carduri] = c;
        nr_carduri++;
        
    }
    
    public void adaugareCredit(Credit c) {
        if(nr_credite>=NR_MAX_CREDITE)
        {
            System.out.println("Ati depasit nr maxim de credite");
            return;
        }
        c.setIban(this.getIban());      
        this.array_credite[nr_credite] = c;
        nr_credite++;
        
    }
    
    public Card getCard(int i) {
         
         if(i>=NR_MAX_CARDURI || i<0)
        {
            System.out.println("Nr cardului depaseste limitele");
            //return; generam o exceptie
        }
        return this.array_carduri[i-1];
        
    }
    
     public Credit getCredit(int i) {
         
         if(i>=NR_MAX_CREDITE || i<0)
        {
            System.out.println("Nr creditului depaseste limitele");
            //return; generam o exceptie
        }
        return this.array_credite[i-1];
        
    }
    
     public void afisareTot(){
        System.out.println("Cont ");
        this.detaliiCont();
        for (int j=0; j<nr_carduri ; j++) {
            this.array_carduri[j].afisareTot();
        }
        
        for (int j=0; j<nr_credite ; j++) {
            this.array_credite[j].afisareTot();
        }
    }
     
     public void detaliiContCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiContCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {
            csv.write(this.Iban+","+this.valuta+","+this.suma+","+this.cnp+"\r\n");
            for (int j=0; j<nr_carduri ; j++) {
                this.array_carduri[j].detaliiCardCSV(csv,audit);
            }
        
            for (int j=0; j<nr_credite ; j++) {
                this.array_credite[j].detaliiCreditCSV(csv,audit);
            }
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}
    
 
}
