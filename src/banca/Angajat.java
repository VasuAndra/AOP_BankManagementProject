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

public class Angajat extends Persoana implements ServiciiAngajat{

  
    private int IdAngajat;
    private String functie;
    private int IdSucursala;

    public Angajat() {
    }

    public Angajat(int IdAngajat, String functie, int IdSucursala, long cnp, String nume, String prenume, String adresa) {
        super(cnp, nume, prenume, adresa);
        this.IdAngajat = IdAngajat;
        this.functie = functie;
        this.IdSucursala = IdSucursala;
    }
    
    
    
    public int getIdAngajat() {
        return IdAngajat;
    }

    public void setIdAngajat(int IdAngajat) {
        this.IdAngajat = IdAngajat;
    }

    public String  getFunctie() {
        return functie;
    }

    public void setFunctie(String  functie) {
        this.functie = functie;
    }

    public int getIdSucursala() {
        return IdSucursala;
    }

    public void setIdSucursala(int IdSucursala) {
        this.IdSucursala = IdSucursala;
    }
    
    
    
    public void citesteAngajat() {
        Scanner sc=new Scanner(System.in);
        
            citestePersoana();
            System.out.println("Dati IdAngajat");
            this.IdAngajat=sc.nextInt();
            sc.nextLine();
            System.out.println("Dati Functie");
            this.functie=sc.nextLine();
    }
    
   public void citesteAngajatFis(Scanner sc,FileWriter audit) throws Exception {
        
        try {
            audit.write("In procedura citesteAngajatFis,"+new Timestamp(System.currentTimeMillis())+"\r\n");
            citestePersoanaFis(sc,audit);
            
            this.IdAngajat=sc.nextInt();
            //sc.nextLine();
            audit.write("CNP,"+this.IdAngajat+"\r\n");
            
            this.functie=sc.next();
            audit.write("Functie,"+this.functie+"\r\n");
            
        } catch (IOException ex) {
            System.out.println("Eroare citire angajat"+ex.getMessage());
        }       
}
    public void detaliiAngajat() {
        
            detaliiPersoana();
            System.out.println("Id Angajat: "+this.IdAngajat);
            System.out.println("Functie: "+this.functie);
            System.out.println("Id sucursala: "+this.IdSucursala);
            
    }
    public void afisareTot(){
        System.out.println("Angajat ");
        this.detaliiAngajat();
        
    }
    
    public void detaliiAngajatCSV(FileWriter csv,FileWriter audit) throws Exception {
    
    audit.write("In procedura detaliiAngajatCSV, "+new Timestamp(System.currentTimeMillis())+"\r\n");
    
        try {
            detaliiPersoanaCSV(csv,audit);
            csv.write(","+this.IdAngajat+","+this.functie+","+this.IdSucursala+"\r\n");
        } catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("CSV negasit");
            
        }
}
    
    
    
}
