/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca;

/**
 *
 * @author vasua
 */
public interface ServiciiClient {
    
    public void detaliiClient();
    public void citesteClient();
    public void afisareConturi();
    public void adaugareCont(Cont c);
    public Cont getCont(int i);
    public void afisareTot();
    
}
