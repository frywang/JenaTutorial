/*
 * 本代码来源于 http://www.xuebuyuan.com/1890653.html，修改后结果不是很完善
 */

package parse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class QueryPropertyWithClass
{
  
  public static void main(String [] args)
  {



              OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
              try
              {
           	   model.read(new FileInputStream("D:\\Fruits-lite.owl"),"");
              }
              catch(IOException ioe)
              {
                     System.err.println(ioe.toString());
              }
              
              int j=0;
              //list classes
              for(Iterator i=model.listClasses();i.hasNext();)
              {

                   //the subclass string
                  String strSub="";
                  j++;
              OntClass c = (OntClass) i.next();
              String strClass1=c.getURI();
              String strClass2=c.getLabel("zh");
//              strClass=strClass.substring(1);
//              if (strClass == null){
//            	  System.out.println("kon ");
//              }else{ 
            	  System.out.println(j+":"+strClass1+":"+strClass2);

////              
              

//              //to list sub-classes for each class
//
//              for(Iterator k=c.listSubClasses(true);k.hasNext();)
//              {
//                  //这里列出直接的subclass
//                  System.out.print("  "+"hasSubClass"+":");
//                  OntClass subclass=(OntClass)k.next();
//                  //OntClass的作用是Interface that represents an ontology node 
//                  //characterising a class description
//                  String strSubClass=subclass.getURI();
//                  String strSubClassLabel=subclass.getLabel("zh");
////                  strSubClass=strSubClass.substring(1);
//                  System.out.println(strSubClass+":"+strSubClassLabel);
////                  strSub+=strSubClass+" ";
////                  try
////                  {
////                          Statement stmRelation=conn.createStatement();
////                          String strsql="insert into relation values ('"+strClass+"','"+strSubClass+"',"+"'hasSubClass')";
////                          stmRelation.execute(strsql);
////                          stmRelation.close();
////                  }
////                  catch(Exception ex)
////                  {
////                          ex.printStackTrace();
////                  }
//              }//end of class for
          
              
              
              //这里列出类的property 
              //list property for each class
              for(Iterator y=c.listDeclaredProperties(true);y.hasNext();)
              {
                    
                     OntProperty property=(OntProperty)y.next();
                     
                 
                     String strPropertyURI=property.getURI();        
                     String strPropertyName=property.getLabel("zh");

                      String strRangeName="";
                       try {
                    	 if (property.getRange()!=null) {
                    		 strRangeName = property.getRange().getLabel("zh");
                    		 
                             
                    	 }
                      } catch (NullPointerException e){
                    	  strRangeName  ="空";
                    
                      }
   
                       
                       if(strPropertyName.equals("有出售价格"))
                       {
                           System.out.print("  ");
                           System.out.print(strPropertyURI+strPropertyName+"\r\n"); 
                           System.out.println("\r\n"+strRangeName);

                       }

                     
                   

                           



              }
              }     
  }
}
