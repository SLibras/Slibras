/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slibras.apachepoi;

import com.slibras.slibrascore.ManipuladorSlide;
import com.slibras.slibrascore.MyProperties;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

/**
 *
 * @author mtxthales
 */
public class ManipuladorApachePoi implements ManipuladorSlide{

    public static void gerarImagens(String filePath, String pastaImgs){
        
        try {
            
            FileInputStream arquivo = new FileInputStream(filePath);
            XMLSlideShow ppt = new XMLSlideShow(arquivo);
            arquivo.close();
            
            Dimension pgsize = ppt.getPageSize();
            
            int idx = 1;
            
            for (XSLFSlide slide : ppt.getSlides()){

                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                slide.draw(graphics);

                FileOutputStream out = new  FileOutputStream(MyProperties.getProp().getProperty("caminho.img")+pastaImgs+"/slide "+idx+".png");
                ImageIO.write(img, "png", out);
                out.close();
                
                idx++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorApachePoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<String> getTxtsFromPPT(String filePath) {
        
        List<String> lista = new ArrayList<>();
        
        try {
            
            FileInputStream arquivo = new FileInputStream(filePath);
            XMLSlideShow ppt = new XMLSlideShow(arquivo);
             
        for (XSLFSlide slide : ppt.getSlides()){
            
            String s = XSLFPowerPointExtractor.getText(slide,true, true, true);
            
            lista.add(s);
        }
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorApachePoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    @Override
    public File addVideoToPPT() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
