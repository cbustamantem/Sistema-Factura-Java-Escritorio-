/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testingpy.business.usuario.boundary.db;

import java.util.List;
import org.testingpy.clases.DataHandler;

/**
 *
 * @author cbustamante
 */
public class DataHandlerMock implements DataHandler {

    private List<String> content;
    
    private Boolean saveResult;

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public Boolean getSaveResult() {
        return saveResult;
    }

    public void setSaveResult(Boolean saveResult) {
        this.saveResult = saveResult;
    }
    
    
    
    @Override
    public List<String> readFileContent() {
        return content;
    }

    @Override
    public Boolean saveContentToFile(String content) {
        return saveResult;
    }

    @Override
    public Boolean saveContentToFile(String[] content) {
        return saveResult;
    }
    
}
