package parse;

import java.io.InputStream;
import java.net.URL;
import java.util.List; 
 
 
import com.hp.hpl.jena.query.Query; 
import com.hp.hpl.jena.query.QueryExecution; 
import com.hp.hpl.jena.query.QueryExecutionFactory; 
import com.hp.hpl.jena.query.QueryFactory; 
import com.hp.hpl.jena.query.QuerySolution; 
import com.hp.hpl.jena.rdf.model.Model; 
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.WrappedIterator; 



public class QueryTester { 
 
 public static void main(final String[] args) throws Exception { 
  // String qry = 
  // "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> SELECT  (sum(?x) as ?y) WHERE { [] <http://example.com/int> ?x1 . bind( xsd:integer(?x1) as ?x) }"; 
  final Model m = ModelFactory.createDefaultModel(); 
  
  String filename = "./data/食材.owl"; 
		  
  InputStream in = FileManager.get().open(filename); 
  
  if (in == null) 
	    throw new IllegalArgumentException("File: "+filename+" not found"); 
  
  m.read(in, null); 
//  
//   Property d = m.createProperty( "http://example.com/double"); 
//   Property i = m.createProperty( "http://example.com/int"); 
//   m.add( m.createResource("http://example.com/A"), d, 
//   m.createTypedLiteral(-1.3) ); 
//   m.add( m.createResource("http://example.com/A"), i, 
//   m.createTypedLiteral(-3) ); 
//   m.add( m.createResource("http://example.com/B"), d, 
//   m.createTypedLiteral(1.5) ); 
//   m.add( m.createResource("http://example.com/B"), i, 
//   m.createTypedLiteral(5) ); 
//   m.add( m.createResource("http://example.com/C"), d, 
//   m.createTypedLiteral(1.7) ); 
//   m.add( m.createResource("http://example.com/C"), i, "7" ); 
  
  
  final Query q = QueryFactory.create(qry); 
  final QueryExecution qexec = QueryExecutionFactory.create(q, m); 
  final List<QuerySolution> retval = WrappedIterator.create( 
    qexec.execSelect()).toList(); 
  for (final QuerySolution qs : retval) { 
   System.out.println(qs); 
  } 
 } 
 
// static URL fUrl = J4SConnectionTest.class 
//   .getResource("./J4SStatementTest.ttl"); 

 
 
static String qry = "SELECT ?o  WHERE {?s ?p ?o }";
}