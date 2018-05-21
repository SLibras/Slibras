/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slibras.slibrascore;

import com.slibras.apachepoi.ManipuladorApachePoi;
import java.util.List;

/**
 *
 * @author mtxthales
 */
public class GeradorSlideLibras {
    
    private ManipuladorSlide ms;
    private ConversorTxtLibras conversor;
    private List<String> listaTextos;
    
    public void traduzirSlide(String filePath){
        
        ms = new ManipuladorApachePoi();
        
        listaTextos = ms.getTxtsFromPPT(filePath);  
    }
    
    public List<String> getListaTextos(){
        return listaTextos;
    }
}
