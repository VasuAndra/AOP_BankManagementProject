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
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author vasua
 */
public class Client extends Persoana implements ServiciiClient{
    
    public static final int NR_MAX_CONTURI=10;
    private int IdSucursala;
    private int nr_conturi=0;
    
    private Cont [] array_conturi;

    public Client() {
        this.array_conturi=new Cont[10];
    }

    public Client(int IdSucursala, long cnp, String  nume, String prenume, String adresa) {
        
        super(cnp, nume, prenume, adresa);
        this.IdSucursala = IdSucursala;
        this.array_conturi=new Cont[10];
    }

    public int getIdSucursala() {
        return IdSucursala;
    }

    public void setIdSucursala(int IdSucursala) {
        this.IdSucursala = IdSucursala;
    }
    
    public void detaliiClient() {
        
            detaliiPersoana();
            System.out.println("Id Sucursala: "+this.IdSucursala);
            
    }
    
    
    public void citesteClient() {
        Scanner sc=new Scanner(System.in);
        
            citestePersoana();
    }
    
   public void citesteClientFis(Scanner sc,FileWriter audit) throws Exception {
    
        try {
            audit.write("In procedura citesteClientFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            citestePersoanaFis(sc,audit);
            
            
        } catch (IOException ex) {
            System.out.println("Eroare citire client"+ex.getMessage());
        }       
}    
    
    public void afisareConturi() {
        for (int j=0; j<nr_conturi ; j++) {
            this.array_conturi[j].detaliiCont();
        }
    }
        
    public void adaugareCont(Cont c) {
        if(nr_conturi>=NR_MAX_CONTURI)
        {
            System.out.println("Ati depasit nr maxim de conturi");
            return;
        }
        
        c.setCnp(this.getCnp());  
        this.array_conturi[nr_conturi] = c;
        nr_conturi++;
        
        
    }
    
    public Cont getCont(int i) {
         
         if(i>=NR_MAX_CONTURI || i<0)
        {
            System.out.println("Nr c ontului depaseste limitele");
            //return; generam o exceptie
        }
        return this.array_conturi[i-1];
        
    }
    
    public void afisareTot(){
        System.out.println("Client ");
        this.detaliiClient();
        for (int j=0; j<nr_conturi ; j++) {
            this.array_conturi[j].afisareTot();
        }
        
    }
    
    public void detaliiClientCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiClientCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {
            
            detaliiPersoanaCSV(csv,audit);
            csv.write(","+this.IdSucursala+"\r\n");
            for (int j=0; j<nr_conturi ; j++) {
                 this.array_conturi[j].detaliiContCSV(csv,audit);
        }
            
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}    
    
    public static Comparator<Client> NameComparator = new Comparator<Client>() {

        @Override
        public int compare(Client a1, Client a2) {
            return a1.getNume().compareTo(a2.getNume());
        }
    };

    
    
    
  
}
