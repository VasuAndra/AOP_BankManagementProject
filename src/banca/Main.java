/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca;

import java.util.Collections;

/**
 *
 * @author vasua
 */
public class Main {
    
    public static void main(String[] args) {
        
        Sucursala s;
        Client c;
        Angajat a;
        Cont cont;
        Card card;
        Credit cr;
        Rata r;
        
        s=new Sucursala();
        System.out.println("Date pentru sucursala ");
        s.citesteSucursala();
        s.detaliiSucursala();
        
        for(int i=0;i<1;i++)
        {
            a=new Angajat();
            System.out.println("Date pentru angajatul "+(i+1));
            a.citesteAngajat();
            s.adaugareAngajat(a);
            a.detaliiAngajat();
            
            
        }
        
        for(int i=0;i<2;i++) //clienti
         {   c=new Client();
            System.out.println("Date pentru clientul "+(i+1));
            c.citesteClient();
            s.adaugareClient(c);
            c.detaliiClient();
            
            System.out.println("Date pentru conturile clientului "+(i+1)+"\n");
            for(int k=0;k<1;k++)//conturile clientului i
            {   
                cont=new Cont();
                System.out.println("Date pentru contul "+(k+1));
                cont.citesteCont();
                c.adaugareCont(cont);
                cont.detaliiCont();
                System.out.println("Date pentru cardurile contului "+(k+1)+"\n");
                for(int j=0;j<1;j++)//cardurile conutului k
                {
                    
                    card=new Card();
                    System.out.println("Date pentru cardul "+(j+1));
                    card.citesteCard();
                    cont.adaugareCard(card);
                    card.detaliiCard();
                }
                System.out.println("Date pentru creditele contului "+(k+1)+"\n");
                for(int j=0;j<1;j++)//creditele conutului k
                {
                
                    cr=new Credit();
                    System.out.println("Date pentru creditul "+(j+1));
                    cr.citesteCredit();
                    cont.adaugareCredit(cr);
                    cr.detaliiCredit();
                    
                    System.out.println("Date pentru ratele creditului "+(j+1)+"\n");
                    for(int m=0;m<2;m++)//ratele creditului j
                    {
                         
                         r=new Rata();
                         System.out.println("Date pentru rata "+(m+1));
                         r.citesteRata();
                         cr.adaugareRata(r);
                         r.detaliiRata();


                    } 
                }
            }
            
         
        }
        s.afisareTot();
        
        System.out.println("Vectorul de clienti ordonati alfabetic:");
        Collections.sort(s.getVector_clienti(),Client.NameComparator);
        s.afisareClienti();
        
        s.getClient(1).getCont(1).afisareSold();
        s.getClient(1).getCont(1).depunere(100);
        s.getClient(1).getCont(1).afisareSold();
        s.getClient(1).getCont(1).retragere(50);
        s.getClient(1).getCont(1).afisareSold();
        
        
        s.getClient(2).getCont(1).afisareSold();
        s.getClient(2).getCont(1).depunere(170);
        s.getClient(2).getCont(1).afisareSold();
        s.getClient(2).getCont(1).retragere(20);
        s.getClient(2).getCont(1).afisareSold();
        
        
        
        
         
}
}
