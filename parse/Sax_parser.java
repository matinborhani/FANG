/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;


public class Sax_parser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
              try {
//         File inputFile = new File("index.xml");
//         SAXParserFactory factory = SAXParserFactory.newInstance();
//         SAXParser saxParser = factory.newSAXParser();
//         UserHandler2 userhandler = new UserHandler2();
//         saxParser.parse(inputFile, userhandler);    
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        String []docs={"WebIR-002","WebIR-014","WebIR-058","WebIR-087","WebIR-110","WebIR-113","WebIR-156","WebIR-157"
        ,"WebIR-175","WebIR-180","WebIR-187","WebIR-222","WebIR-223","WebIR-224","WebIR-225","WebIR-234","WebIR-427"
        ,"WebIR-428","WebIR-429","WebIR-435","WebIR-436"};
        
        
                  String []doc=new String[22];
                  for (int i = 1; i < doc.length; i++) {
                      doc[i]="1 "+"("+i+")";
                      
                  }
                  System.out.println("Waiting A Few Min...\n"+"Loading Docs...");
                  for (int i = 1; i < doc.length; i++) {
                      
                      System.out.println(doc[i]+" is loading");
//                      if(i==1)
                          factory.newSAXParser().parse(new File("C:\\Users\\MatinB\\Desktop\\University\\Term 7\\Search Engine\\Project Shariat\\WEBIR_S\\" + doc[i] + ".xml"), new UserHandler2());
                      
                      
                  }
        
       
        
      } catch (Exception e) {
         e.printStackTrace();
      }
  
   


        
    }
    
}
