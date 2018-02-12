package owl.sparql;

/*
 * 本代码来源于 Jena API 使用介绍  本文比较详细
 * http://blog.csdn.net/javafreely/article/details/8432522
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;

public class DeleteResource {
    public static void main(String[] args) {
        // create the model and import owl file
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
     try
     {
  	   m.read(new FileInputStream("D:\\Fruits-lite.owl"),"");
     }
     catch(IOException ioe)
     {
            System.err.println(ioe.toString());
     }
     

      
        String testURI = "http://www.360iii.org/ontologies/fruit#OWLClass_56ef58aa_adf0_4329_9442_8f6fa51870cd"; 
     
        //增加Resource
        Resource test = m.createResource(testURI);  
        test.addProperty(RDFS.label, m.createLiteral("测试呢", "zh")); 
        test.addProperty(RDFS.label, m.createLiteral("test", "en")); 

                    m.createResource()  
                        .addProperty(RDFS.label, m.createLiteral("Fuck，Why so hard!", "zh"))
                        .addProperty(RDFS.label, m.createLiteral("you are crazy", "en"));   
  
        System.out.println("/r/n原始内容：");  
        m.write(System.out);  
        
        // 删除 Statement           
        m.remove(m.listStatements(null, RDFS.label, (RDFNode)null));  
        m.removeAll(null, RDFS.label, (RDFNode)null);  
          
        System.out.println("\n删除后的内容：");  
        m.write(System.out);  

        m.add(test, VCARD.N, m.createResource()  
        		.addProperty(RDFS.label, m.createLiteral("Fuck，Why so hard!", "zh"))
        		.addProperty(RDFS.label, m.createLiteral("you are crazy", "en")));   
        System.out.println("\n重新增加后的内容：");  
        m.write(System.out);  
    }  

}
