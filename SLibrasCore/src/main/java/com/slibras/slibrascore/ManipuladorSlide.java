/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slibras.slibrascore;

import java.io.File;
import java.util.List;

/**
 *
 * @author mtxthales
 */
public interface ManipuladorSlide {
    
    public abstract List<String> getTxtsFromPPT(String filePath);
    public abstract File addVideoToPPT();    
}
