/*
 * 本代码来源于 http://stackoverflow.com/questions/13255744/trying-to-create-a-sparql-query-using-jenas-java-api
 */


package sparql;

import java.io.InputStream;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;


public class Sparql2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		FileManager.get().readModel( model, "D:\\Fruits-lite.owl" );

		String queryString =
		        "prefix pizza: <http://www.w3.org/2002/07/owl#> "+        
		        "prefix rdfs: <" + RDFS.getURI() + "> "           +
		        "prefix owl: <" + OWL.getURI() + "> "             +
//		        "select  ?id ?element where {?element rdfs:label ?id }";
// "select ?subclass ?superclass where { ?subclass rdfs:subClassOf ?superclass}";

"select ?subclass ?superclass where {"
+ "{ ?subclass rdfs:subClassOf ?superclass }"
+ "union"
+ "{ ?subclass rdfs:subClassOf [ owl:intersectionOf [ rdf:rest* [ rdf:first ?superclass ] ] ] }}";

		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}

}
