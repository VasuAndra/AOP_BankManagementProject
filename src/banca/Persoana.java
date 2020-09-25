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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vasua
 */
public class Persoana implements ServiciiPersoana{
    private long cnp;
    private String  nume;
    private String  prenume;
    private String  adresa;

    public Persoana() {
    }
    
    

    public Persoana(long  cnp, String  nume, String  prenume, String  adresa) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
    }
    
    

    public long  getCnp() {
        return cnp;
    }

    public void setCnp(long  cnp) {
        this.cnp = cnp;
    }

    public String  getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String  getPrenume() {
        return prenume;
    }

    public void setPrenume(String  prenume) {
        this.prenume = prenume;
    }

    public String  getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
public void citestePersoana() {
    Scanner sc=new Scanner(System.in);

        System.out.println("Dati CNP");
        this.cnp=sc.nextLong();
        sc.nextLine();
        System.out.println("Dati Nume");
        this.nume=sc.nextLine();
        System.out.println("Dati Prenume");
        this.prenume=sc.nextLine();
        System.out.println("Dati Adresa");
        this.adresa=sc.nextLine();
}
 
public void citestePersoanaFis(Scanner sc,FileWriter audit) throws Exception {
    
        try {
            audit.write("In procedura citestePersoanaFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            
            this.cnp=sc.nextLong();
            sc.nextLine();
            audit.write("CNP,"+this.cnp+"\r\n");
            
            this.nume=sc.nextLine();
            audit.write("Nume,"+this.nume+"\r\n");
            
            this.prenume=sc.nextLine();
            audit.write("Prenume,"+this.prenume+"\r\n");
            
            this.adresa=sc.nextLine();
            audit.write("Adresa,"+this.adresa+"\r\n");
            
            
            
        } catch (IOException ex) {
            System.out.println("Eroare citire persoana"+ex.getMessage());
        }       
}

public void detaliiPersoana() {
    
        System.out.println("CNP: "+this.cnp);
        System.out.println("Nume: "+this.nume);
        System.out.println("Prenume: "+this.prenume);
        System.out.println("Adresa: "+this.adresa);
}


public void detaliiPersoanaCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiPersoanaCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {
            csv.write(this.cnp+","+this.nume+","+this.prenume+","+this.adresa);
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}
    
}
