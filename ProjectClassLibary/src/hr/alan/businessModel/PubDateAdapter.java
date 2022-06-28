/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import sun.util.resources.LocaleData;

/**
 *
 * @author Alan
 */
public class PubDateAdapter extends XmlAdapter<String, LocaleData>{

    @Override
    public LocaleData unmarshal(String v) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String marshal(LocaleData v) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
