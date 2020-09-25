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
public class Card implements ServiciiCard{
    
    private long nrCard;
    private String status; //activ-inactiv
    private int valabilitateLuna;
    private int valabilitateAn;
    private TipCard numeTipCard;
    private long Iban;

    public Card() {
    }

    public Card(long nrCard, String  status, int valabilitateLuna, int valabilitateAn, TipCard numeTipCard, long Iban) {
        this.nrCard = nrCard;
        this.status = status;
        this.valabilitateLuna = valabilitateLuna;
        this.valabilitateAn = valabilitateAn;
        this.numeTipCard = numeTipCard;
        this.Iban = Iban;
    }

    public long getNrCard() {
        return nrCard;
    }

    public void setNrCard(long nrCard) {
        this.nrCard = nrCard;
    }

    public String  getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public int getValabilitateLuna() {
        return valabilitateLuna;
    }

    public void setValabilitateLuna(int valabilitateLuna) {
        this.valabilitateLuna = valabilitateLuna;
    }

    public int getValabilitateAn() {
        return valabilitateAn;
    }

    public void setValabilitateAn(int valabilitateAn) {
        this.valabilitateAn = valabilitateAn;
    }

    public TipCard getNumeTipCard() {
        return numeTipCard;
    }

    public void setNumeTipCard(TipCard numeTipCard) {
        this.numeTipCard = numeTipCard;
    }

    public long getIban() {
        return Iban;
    }

    public void setIban(long Iban) {
        this.Iban = Iban;
    }
    
    public void citesteCard() {
        Scanner sc=new Scanner(System.in);
        
            System.out.println("Dati nr card");
            this.nrCard=sc.nextLong();
            sc.nextLine();
            System.out.println("Dati status");
            this.status=sc.nextLine();
            System.out.println("Dati tipul cardului: 1-Visa 2-Mastercard 3-Revolut");
            int option=sc.nextInt();
            switch(option)
            {
                case 1:
                    this.numeTipCard=TipCard.VISA;
                    break;
                case 2:
                    this.numeTipCard=TipCard.MASTERCARD;
                    break;
                case 3:
                    this.numeTipCard=TipCard.REVOLUT;
                    break;
                default:
                    System.out.println("Tip card inexistent. Introduceti din nou");       
            }
            
            System.out.println("Dati valabilitate luna");
            this.valabilitateLuna=sc.nextInt();
            System.out.println("Dati valabilitate an");
            this.valabilitateAn=sc.nextInt();
    }
    
    public void citesteCardFis(Scanner sc,FileWriter audit) throws Exception {

        try {
            audit.write("In procedura citesteCardFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            
            this.nrCard=sc.nextLong();
            audit.write("Nr card,"+this.nrCard+"\r\n");
            sc.nextLine();
            
            this.status=sc.nextLine();
            audit.write("Status,"+this.status+"\r\n");
            
            String option=sc.nextLine();
            switch(option)
            {
                case "visa":
                {
                    this.numeTipCard=TipCard.VISA;
                    audit.write("Tip card,VISA"+"\r\n");
                    break;
                }
                case "mastercard":
                {
                    this.numeTipCard=TipCard.MASTERCARD;
                    audit.write("Tip card,MASTERCARD"+"\r\n");
                    break;
                }
                case "revolut" :
                {
                    this.numeTipCard=TipCard.REVOLUT;
                    audit.write("Tip card,REVOLUT"+"\r\n");
                    break;
                }
                default:
                    audit.write("Tip card inexistent.");       
            }
            
            
            this.valabilitateLuna=sc.nextInt();
            audit.write("Valabilitate Luna,"+this.valabilitateLuna+"\r\n");
            sc.nextLine();
            
            this.valabilitateAn=sc.nextInt();
            audit.write("Valabilitate An,"+this.valabilitateAn+"\r\n");
            sc.nextLine();
 
        } catch (IOException ex) {
            System.out.println("Eroare citire card"+ex.getMessage());
        }       
}
   
    public void detaliiCard() {
        
            System.out.println("Nr card: "+this.nrCard);
            System.out.println("Status: "+this.status);
            System.out.println("Nune tip card: "+this.numeTipCard);
            System.out.println("Valabilitate Luna: "+this.valabilitateLuna);
            System.out.println("Valabilitate An: "+this.valabilitateAn);
    }
    
    public void afisareTot(){
        System.out.println("Card ");
        this.detaliiCard();
        
    }
    
    public void detaliiCardCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiCardCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {

            csv.write(this.nrCard+","+this.status+","+this.numeTipCard+","+this.valabilitateLuna
            +","+this.valabilitateAn+","+this.Iban+"\r\n");
            
            
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}
    
    
    
}
