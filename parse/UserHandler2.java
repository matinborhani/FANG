/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;




public class UserHandler2 extends DefaultHandler{
    



    
    
    
    private boolean bDocID,bUrl;
       private StringBuilder content = new StringBuilder();
       private FileWriter input = null;
       private String filename="textdocs.txt";
        private FileWriter file=null;

   
        


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if(localName.equalsIgnoreCase("html")){
            content.setLength(0);
            //System.out.println(localName);
        }
         else if (qName.equalsIgnoreCase("DOCID")) {
         bDocID = true;
      } else if (qName.equalsIgnoreCase("URL")) {
         bUrl = true;
      }
        
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        content.append(ch, start, length);
        if (bDocID) {
          try {
         //System.out.println("DOCID: " + new String(ch, start, length));
          input = new FileWriter(filename,true);
         input.write(new String(ch, start, length));
         input.write("\n");
         
          file=new FileWriter("URL&ID.txt",true);
              file.write(new String(ch, start, length));
              file.write("\n");
              file.close();
          } catch (IOException ex) {
              Logger.getLogger(UserHandler2.class.getName()).log(Level.SEVERE, null, ex);
          }
         bDocID = false;
      } else if (bUrl) {
          try {
         //System.out.println("URL: " + new String(ch, start, length));
          
         input.write(new String(ch, start, length));
         input.write("\n");
         
         FileWriter file=new FileWriter("URL&ID.txt",true);
              file.write(new String(ch, start, length));
              file.write("\n");
              file.close();
          } catch (IOException ex) {
              Logger.getLogger(UserHandler2.class.getName()).log(Level.SEVERE, null, ex);
          }
         bUrl = false;
      } 

    }

    
    
    
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(localName.equalsIgnoreCase("html")){
            
            
           
                org.jsoup.nodes.Document doc =  Jsoup.parse(content.toString(), "UTF-8");
            try {
                
                input.write(doc.text()+"\n");
                input.close();
                
                
                

            } catch (IOException ex) {
                Logger.getLogger(UserHandler2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        content.setLength(0);
    }

        public void writeToFile(char ch,int i) throws IOException {
            file = new FileWriter("file2.txt",true);
            file.write(ch);
//        if(content.charAt(i+1)==' ')
//            file.write(' ');//(char)32
        file.close();
    }
        public void writeToFile(char ch) throws IOException {
            file = new FileWriter("file2.txt",true);
        file.write(ch);
        file.close();
    }

  
}
