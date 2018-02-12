/*
 * 本代码来源于 http://stackoverflow.com/questions/13255744/trying-to-create-a-sparql-query-using-jenas-java-api
 */


package sparql;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

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


public class Sparql0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		String inputFileName="pizza.owl";
		InputStream in = FileManager.get().open("D:\\pizza.owl");
		if (in == null) {
		    throw new IllegalArgumentException(
		         "File: " + inputFileName + " not found");
		}
		model.read(in, null);
		FileManager.get().readModel( model, "D:\\pizza.owl" );

		String queryString =
		        "prefix pizza: <http://www.co-ode.org/ontologies/pizza/pizza.owl#> "+        
		        "prefix rdfs: <" + RDFS.getURI() + "> "           +
		        "prefix owl: <" + OWL.getURI() + "> "             +
		        "select ?pizza where {?pizza a owl:Class ; "      +
		        "rdfs:subClassOf ?restriction. "                  +
		        "?restriction owl:onProperty pizza:hasTopping ;"  +
		        "owl:someValuesFrom pizza:PeperoniSausageTopping" +
		        "}";


		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}

}
