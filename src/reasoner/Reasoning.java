/*
 * 
 *本代码来源于 http://opentox.org/data/documents/development/RDF%20files/JavaOnly/query-reasoning-with-jena-and-sparql，非常重要！！
 */


package reasoner;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Reasoning {
//	public static void main (String args[]) {
//		
//        String SOURCE = "http://www.opentox.org/api/1.1";
//        String NS = SOURCE + "#";
//       //create a model using reasoner
//        OntModel model1 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
//       //create a model which doesn't use a reasoner
//        OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
//        
//        // read the RDF/XML file
//        model1.read( SOURCE, "RDF/XML" );
//        model2.read( SOURCE, "RDF/XML" );
//        //prints out the RDF/XML structure
////        qe.close();
//        System.out.println(" ");
        

//	 public static void main(String args[]) 
//	 { 
//	  String filename = "D:\\Fruits-lite.owl"; 
//	   
//	  // Create an empty model 
//	  Model model = ModelFactory.createDefaultModel(); 
//	     
//	  // Use the FileManager to find the input file 
//	  InputStream in = FileManager.get().open(filename); 
//	 
//	  if (in == null) 
//	   throw new IllegalArgumentException("File: "+filename+" not found"); 
//	 
//	  // Read the RDF/XML file 
//	  model.read(in, null); 
//	   
//	  // List all the resources with the property "vcard:FN" 
//	  String queryString =  
//	    "PREFIX vcard: <" + VCARD.getURI() + "> " + 
//	    "SELECT ?o  WHERE {?s ?p ?o }"; 
//	  
//		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
//		QueryExecution qe = QueryExecutionFactory.create(query, model);
//		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();
//
//		ResultSetFormatter.out(System.out, results, query);
//		qe.close();

      public static void main( String[] args ) {
    	  
    	  String filename = "D:\\Fruits-lite.owl";

//        String NS = SOURCE + "#";
       //create a model using reasoner
        OntModel model1 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
       //create a model which doesn't use a reasoner     
        OntModel model2 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);
        InputStream in = FileManager.get().open(filename);    
        
  	    if (in == null) 
 	    throw new IllegalArgumentException("File: "+filename+" not found"); 
 	
        // read the RDF/XML file
        model1.read(in, null);
        model2.read(in, null);
        

        
//        qe.close();
        System.out.println("fadfdsdsfdsfdfs ");
        
        
        
        
    // Create a new query
    String queryString =        
      "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+
//        "select ?uri "+
//        "where { "+
//         "?uri rdfs:subClassOf <http://www.360iii.org/ontologies/fruit#OWLClass_6fb6bbb8_5884_419f_889a_1adf48c2433e>  "+
//        "} \n ";

    "SELECT ?sl ?ol WHERE {?s rdfs:subClassOf ?o.?o rdfs:label ?ol FILTER REGEX(?ol, \"热带水果\") .?s rdfs:label ?sl.}";

//      "SELECT DISTINCT ?s WHERE"  +
//      "{?object rdfs:label \"热带水果\"@zh." +
//      "?subject rdfs:subClassOf ?object." +
//      "?subject rdfs:label ?s}";
    
    Query query = QueryFactory.create(queryString);

    System.out.println("----------------------");

    System.out.println("Query Result Sheet");

    System.out.println("----------------------");

    System.out.println("Direct&Indirect Descendants (model1)");

    System.out.println("-------------------");

   
    // Execute the query and obtain results
    QueryExecution qe = QueryExecutionFactory.create(query, model1);
    com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

    // Output query results    
    ResultSetFormatter.out(System.out, results, query);

    qe.close();
    
    System.out.println("----------------------");
    System.out.println("Only Direct Descendants");
    System.out.println("----------------------");
    
    // Execute the query and obtain results
    qe = QueryExecutionFactory.create(query, model2);
    results =  qe.execSelect();

    // Output query results    
    ResultSetFormatter.out(System.out, results, query);  
   qe.close();
}
}
