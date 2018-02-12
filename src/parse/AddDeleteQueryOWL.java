


package parse;

import java.io.FileInputStream;

import java.io.IOException;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.vocabulary.RDF; 




public class AddDeleteQueryOWL {
	       public static void main(String[] args) {
	              // create the model and import owl file
	              OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
	           try
	           {
	        	   ontModel.read(new FileInputStream("D:\\Fruits-lite.owl"),"");
	           }
	           catch(IOException ioe)
	           {
	                  System.err.println(ioe.toString());
	           }
	           
	           OntDocumentManager dm = ontModel.getDocumentManager();
	           

	           //添加class和label
	           OntClass cls = ontModel.createClass("http://www.360iii.org/ontologies/fruit#OWLClass_dfe66630_50bb_4596_b79e_ab2e457d5a06");   
	           cls.addLabel("测试class", "zh");
	           //添加class并添加Equivalent关系 
	           OntClass oc = ontModel.getOntClass("http://www.360iii.org/ontologies/fruit#OWLClass_dfe66630_50bb_4596_b79e_ab2e297d5a06");
	           oc.addEquivalentClass(cls); 
	           System.out.print("新加类的中文label是："+cls.getLabel("zh")+"\r\n");
	          //****奇怪，为什么是空值？？？？****
	           System.out.print("新加类cls的URI是："+cls.getURI()+"\r\n");
	           System.out.print("新加类oc的URIl是："+oc.getURI()+"\r\n");
	           
	           
	         //清单1.查询家庭模型中的subject

	       	//list everyone in the model who has a child;
	        Property p1 =ontModel.getProperty("http://www.360iii.org/ontologies/fruit#OWLObjectProperty_370f6e23_1161_40a8_8046_c3e0ac331d4e");
	        OntProperty op1 = (OntProperty) p1.as( OntProperty.class );
	        
	       	ResIterator r1 = ontModel.listSubjectsWithProperty(RDF.type,op1);
	       	//Because subjects of statements are Resources, the method returned a ResIterator
	       	System.out.println("\r\n"+op1.getLabel("zh")+"  的所有subject有：");

	       	while (r1.hasNext()){
//	       		//ResIterator has a typed nextResource() met hold
	       		Resource o1 = r1.nextResource();
//	       		//Print the URI of the resource
	       		System.out.println(o1.getURI());
//	       		
//	       		//can also find all the parents by getting the objects of all"childOf" statements.
//	       		//Objects of statements could be Resources or literals,so the Iterator returned
//	       		//contains RDFNodes

	       	}
	         
	       	
	       	
//	           
	    	//Objects of statements could be Resources or literals,so the Iterator returned
	    	//contain RDFNodes
	       	Property p2 =ontModel.getProperty("http://www.360iii.org/ontologies/fruit#OWLObjectProperty_c0511a91_343e_4539_8707_d9000d45e033");
	       	OntProperty op2 = (OntProperty) p2.as( OntProperty.class );
	    	NodeIterator ni1 = ontModel.listObjectsOfProperty(RDF.type);
//	    	//to find all the siblings of a specific person,the model itself can be required
	    	System.out.println("\r\n"+op2.getLabel("zh")+"  的所有subject有：");
	    	while (ni1.hasNext()){
	    		RDFNode object = ni1.nextNode();
	    		System.out.println(object.getClass());
	    	}
//  
	           
	           
	           
	           
	           
	           
	           
	           
	           /** 
	            * get all classes and properties 
	            *  
	            * @param model 
	            */  
////	           public static void getClasses(OntModel ontModel) {  
//	               for (ExtendedIterator<OntClass> ei = ontModel.listClasses(); ei.hasNext();) {  
//	                   OntClass oc = ei.next();  
//	                   System.out.println(oc.getLocalName());  
//	                   for (ExtendedIterator<OntProperty> eip = oc  
//	                           .listDeclaredProperties(); eip.hasNext();) {  
//	                       OntProperty op = eip.next();  
//	                       System.out.println(op.getLocalName());  
//	                   }  
//	               }  
////	           }  
	           
	           
	      
//	        // ***迭代显示模型中的类，在迭代过程中完成各种操作***
//	           for (Iterator i = ontModel.listClasses(); i.hasNext();) {
//	           OntClass c = (OntClass) i.next(); // 返回类型强制转换
//	           		if (!c.isAnon()) { // 如果不是匿名类，则打印类的名字
//	           			System.out.print("Class:");	           			
//	           // *********获取类的label和IRI地址**********
//	           			System.out.println(c.getLabel("zh"));
//	           				for (Iterator j = ontModel.listObjectProperties(); j.hasNext();) {
//	           					OntProperty op = (OntProperty) j.next();  
//	           					System.out.print("Property:");
//	           		            System.out.println(op.getLabel("zh"));  
//	           				}
//	 	           }
	           				
	                   				
	           				
	           				
	           
	           
	           
//	           public static void printModel(OntModel ontModel) {  
//	        	    for (Iterator<OntClass><span style="font-family: 'Myriad Pro';"> i = ontModel.listClasses(); i.hasNext();) {</span>  
//	        	        OntClass oc = i.next();  
//	        	        System.out.println(oc.getLocalName());  
//	        	    }  
	           
	           
//	        	    
	        	    
	           		
//	           	public static void getClasses(OntModel ontModel) {  
//	           		   for (ExtendedIterator<OntClass> ei = ontModel.listClasses(); ei.hasNext();) {  
//	           		       OntClass ot = ei.next();  
//	           		       System.out.println(oc.getLocalName());  
//	           		       for (ExtendedIterator<OntProperty> eip = ot  
//	           		                .listDeclaredProperties(); eip.hasNext();) {  
//	           		           OntProperty op = eip.next();  
//	           		           System.out.println(op.getLocalName());  
//	           		       }  
//	           		   }  
//	           	}  	           		
	           		
	           		
	           		
	           		
	           
//	           if(c.getLabel("zh").equals(oc.getLabel("zh"))){
//	        	   System.out.println("URI@"+c.getURI());
////	           		System.out.println("水果类的等价类");	
//           			System.out.println(c.getLocalName());		
//		       }                       
//		       } 
	                    
	           
		// TODO Auto-generated method stub
//	   	OntModel ontModel = ModelFactory.createOntologyModel();
//	   	ontModel.read("D:\\wine.Owl");
//		String NS = "http://example.com/test/";
//		
//		Resource r = m.createResource(NS + "r");
//		Property t = m.createProperty(NS + "t");		
//		
//		r.addProperty(t, "hello world",XSDDatatype.XSDstring);
//		m.write(System.out, "Turtle");
		
	}


}
