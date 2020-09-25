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
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vasua
 */
public class MainFis {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Andra_Drive\\C++_programe\\An2_Sem2\\PAO\\ProiectBanca copie\\in.txt");
        Scanner in;
    
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Eroare fisier"+ex.getMessage());
            throw new Exception("Scanner negasit");
            
        }
        FileWriter audit;
        
        try {
            audit = new FileWriter("D:\\Andra_Drive\\C++_programe\\An2_Sem2\\PAO\\ProiectBanca copie\\audit.txt",true);
        } 
        catch (IOException ex) {
            System.out.println("Eroare fisier audit"+ex.getMessage());
            throw new Exception("Audit negasit");
        }
        
        FileWriter csv;
        try {
            csv = new FileWriter("D:\\Andra_Drive\\C++_programe\\An2_Sem2\\PAO\\ProiectBanca copie\\csv.txt");
        } 
        catch (IOException ex) {
            System.out.println("Eroare fisier csv"+ex.getMessage());
            throw new Exception("Csv negasit");
        }
        
        Sucursala s;
        Client c;
        Angajat a;
        Cont cont;
        Card card;
        Credit cr;
        Rata r;
        try{
            
        
        s=new Sucursala();
        s.citesteSucursalaFis(in, audit);
        
        
        for(int i=0;i<1;i++)//angajati
        {
            a=new Angajat();
            a.citesteAngajatFis(in,audit);
            s.adaugareAngajat(a);    
        }
        
        for(int i=0;i<1;i++) //clienti
         {   
            c=new Client();
            c.citesteClientFis(in,audit);
            s.adaugareClient(c);
            
            
            for(int k=0;k<1;k++)//conturile clientului i
            {   
                cont=new Cont();
                cont.citesteContFis(in,audit);
                c.adaugareCont(cont);
                
                for(int j=0;j<1;j++)//cardurile conutului k
                {
                    
                    card=new Card();
                    card.citesteCardFis(in,audit);
                    cont.adaugareCard(card);
                }
                
                for(int j=0;j<1;j++)//creditele conutului k
                {
                
                    cr=new Credit();
                    cr.citesteCreditFis(in,audit);
                    cont.adaugareCredit(cr);
                    
                    for(int m=0;m<1;m++)//ratele creditului j
                    {
                         
                         r=new Rata();
                         r.citesteRataFis(in,audit);
                         cr.adaugareRata(r);


                    } 
                }
           }
         
        }
       long cnp_cl=in.nextLong();
       in.nextLine();
       
       Vector<Client> vect_cl=s.getVector_clienti();
       for(int i=0;i<vect_cl.size();i++)
           vect_cl[i].detaliiClient();
//       for(int i=0;i<vect_cl.size();i++)
//       {
//           if(vect_cl[i].getCnp()==cnp_cl)
//               System.out.println("ok");
//               
//       }
       
       
       
        s.detaliiSucursalaCSV(csv, audit);
        
        audit.close();
        csv.close();
        

    }
        catch(IOException ex) {
            System.out.println("Eroare main"+ex.getMessage());
            audit.close();
            csv.close();
            throw new Exception("Eroare main");
        }
    }
}

