package owl.sparql;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.WrappedIterator;

public class AddResource {

	public static void main(final String[] args) throws Exception {

		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		try {
			m.read(new FileInputStream("./data/食材.owl"), "");
		} catch (IOException ioe) {
			System.err.println(ioe.toString());
		}
		
		
		  //添加class和label
		  OntClass cls = m.createClass("http://www.semanticweb.org/fry/ontologies/test#mmmm");   
		  cls.addLabel("测试class1阿道夫121212", "zh");
		  

		//  //添加class并添加Equivalent关系 
		//  OntClass oc = m.getOntClass("http://www.360iii.org/ontologies/fruit#OWLClass_dfe66630_50bb_4596_b79e_ab2e297d5a06");
		//  oc.addEquivalentClass(cls); 
		//  
		//  //添加individual和label
		//  Individual e1 = m.createIndividual("http://www.360iii.org/ontologies/fruit#OWLNamedIndividual_"+ Math.random(), cls);
		//  e1.addLabel("测试实例1", "zh");
		//  e1.addRDFType(cls);
		//  
		//
		//  //添加ObjectProperty和label
		//  ObjectProperty p1 = m.createObjectProperty( "http://www.360iii.org/ontologies/fruit#OWLObjectProperty_"+ Math.random()); 
		//  p1.addLabel("测试对象属性", "zh");
		//  DatatypeProperty p2 = m.createDatatypeProperty( "http://www.360iii.org/ontologies/fruit#OWLDataProperty_"+ Math.random()); 
		//  p2.addLabel("测试数据属性", "zh");
		//
		//  
		  System.out.print("新加类cls的中文label是："+cls.getLabel("zh")+"\r\n"+"新加类cls的URI是："+cls.getURI()+"\r\n\r\n");
		//  System.out.print("新加类randCls的中文label是："+randCls.getLabel("zh")+"\r\n"+"新加类cls的URI是："+randCls.getURI()+"\r\n\r\n");
		//  System.out.print("新加实例e1的label是："+e1.getLabel("zh")+"\r\n"+"新加实例e1的URIl是："+e1.getURI()+"\r\n\r\n");
		//  System.out.print("新加对象属性p1的label是："+p1.getLabel("zh")+"\r\n"+"新加实例e1的URIl是："+p1.getURI()+"\r\n\r\n");
		  

		Property d = m.createProperty("http://example.com/double");
		Property i = m.createProperty("http://example.com/int");
		m.add(m.createResource("http://example.com/A"), d, m.createTypedLiteral(-1.3));
		m.add(m.createResource("http://example.com/A"), i, m.createTypedLiteral(-3));
		m.add(m.createResource("http://example.com/B"), d, m.createTypedLiteral(1.5));
		m.add(m.createResource("http://example.com/B"), i, m.createTypedLiteral(5));
		m.add(m.createResource("http://example.com/C"), d, m.createTypedLiteral(1.7));
		m.add(m.createResource("http://example.com/C"), i, "7");
		
		

		  m.add( m.createResource("http://www.360iii.org/ontologies/fruit#OWLNamedIndividual_cb84b0aa_3218_4f5a_bf48_49398a2a99ae"), d, 
		  m.createTypedLiteral(-1.3) ); 
		  m.add( m.createResource("http://example.com/A"), i, 
		  m.createTypedLiteral(-3) ); 
		  m.add( m.createResource("http://example.com/B"), d, 
		  m.createTypedLiteral(1.5) ); 
		  m.add( m.createResource("http://example.com/B"), i, 
		  m.createTypedLiteral(5) ); 
		  m.add( m.createResource("http://example.com/C"), d, 
		  m.createTypedLiteral(1.7) ); 
		  m.add( m.createResource("http://example.com/C"), i, "7" ); 
		  
		  
		  
		  

		  
		  
		//  String testURI = "http://www.360iii.org/ontologies/fruit#OWLClass_31e25bf3_1a91_49c5_a2e3_cb7825fa139e"; 
		//  
		//  //增加Resource
		//  Resource test = m.createResource(testURI);  
		//  test.addProperty(RDFS.label, m.createLiteral("测试呢", "zh")); 
		//  test.addProperty(RDFS.label, m.createLiteral("test", "en")); 
		//
//		              m.createResource()  
//		                  .addProperty(RDFS.label, m.createLiteral("Fuck，Why so hard!", "zh"))
//		                  .addProperty(RDFS.label, m.createLiteral("you are crazy", "en"));   


	

	  String filepath = "./data/test.owl";  
	  FileOutputStream fileOS = new FileOutputStream(filepath);  
	  RDFWriter rdfWriter = m.getWriter("RDF/XML");  
	  rdfWriter.setProperty("showXMLDeclaration","true");  
	  rdfWriter.setProperty("showDoctypeDeclaration", "true");  
	  rdfWriter.write(m, fileOS, null);  
	  
	  //用writer就不需要用下面的方法了  
	  //baseOnt.write(fileOS, "RDF/XML");  
	  fileOS.close();  
	  
}

}