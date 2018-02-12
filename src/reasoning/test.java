package reasoning;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.util.IndentedWriter;
import com.hp.hpl.jena.sparql.util.Base64.InputStream;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;

public class test {
	
//	public static void testExpert() {
//		String ruleFile = "D:\\Expert.rules";
//		String ontoFile = "D:\\Expert.owl";
//		
//		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
//		
//		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
//		FileManager.get().readModel( m, ontoFile );
//		
////		Model m = FileManager.get().loadModel("file:./expert/Expert.owl");
//		String NS = "http://www.owl-ontologies.com/Expert.owl#";
//		Resource Jim = m.getResource(NS + "ZhaoHongJie");
//		Resource John = m.getResource(NS + "Computer_Applied_Technology");
////		
//		famRea.getInfModel(ruleFile, m);
////		famRea.getInfModel("D:\\Expert.owl", "D:\\Expert.rules");
//		famRea.printInferResult(Jim, John);
//	}
	
//	public static void testFamily() {
//		String ruleFile = "file:./family/family.rules";
//		String ontoFile = "file:./family/family.owl";
//		
//		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
//		
//		Model m = FileManager.get().loadModel("file:./family/family.owl");
//		String NS = "http://www.semanticweb.org/ontologies/2010/0/family.owl#";
//		Resource Jim = m.getResource(NS + "Jim");
//		Resource John = m.getResource(NS + "John");
//		
//		/*
//		Resource Lucy = m.getResource(NS + "Lucy");
//		Resource Kate = m.getResource(NS + "Kate");
//		Resource Sam = m.getResource(NS + "Sam");
//		Resource James = m.createResource(NS + "James");
//		Resource Anna = m.getResource(NS + "Anna");
//		Resource Holly = m.createResource(NS + "Holly");
//		*/
//		
//		famRea.getInfModel(ruleFile, ontoFile);
//		famRea.printInferResult(Jim, John);
//	}
	
//	public static void testQuery() {
//		String ruleFile = "D:\\Expert.rules";
//		String ontoFile = "D:\\Expert.owl";
//		
//		
//		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
//		FileManager.get().readModel( m, ontoFile );
//		
//		String queryString = "PREFIX Expert:<http://www.owl-ontologies.com/Expert.owl#> " +
//	    	"SELECT ?expert ?subject " +
//	    	"WHERE {?expert Expert:familiar_with ?subject} ";
//		
//		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
//		famRea.getInfModel(ruleFile, m);
////		famRea.searchOnto(queryString);
//		System.out.println(queryString);
//	}

	public static void testQuery1() {
//		String ruleFile = "D:\\Expert.rules";
		String ontoFile = "D:\\Fruits-lite.owl";
		
		
		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		FileManager.get().readModel( m, ontoFile );
		
		String queryString = "prefix fruit: <http://www.w3.org/2002/07/owl#> " +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
//				"select ?o where {?s ?p ?o}";
//      "SELECT  ?label WHERE { ?subject rdfs:label ?label }" ;
//      "SELECT DISTINCT ?predicate ?label WHERE { ?subject ?predicate ?object. ?predicate rdfs:label ?label .}";
//       "SELECT DISTINCT ?s WHERE {?s rdfs:label \"Italy\"@en}" ;
//         "SELECT DISTINCT ?s WHERE {?s rdfs:label \"苹果\"@zh}" ;
//      "SELECT DISTINCT ?s WHERE"  +
//      "{?object rdfs:label \"热带水果\"@zh." +
//      "?subject rdfs:subClassOf ?object." +
//      "?subject rdfs:label ?s}";

         //此query没有结果
//	      "SELECT DISTINCT ?s WHERE"  +
//	      "{?subject <http://www.360iii.org/ontologies/fruit#OWLObjectProperty_5229f34d_8ad6_4570_b4c2_34d799cf5063> "
//	      + " <http://www.360iii.org/ontologies/fruit#OWLClass_fd17d08a_b968_4798_870a_d27f03d2f782> ."+
//	      "?subject rdfs:label ?s}";
		
      //owl:intersectionOf 报错
//		"select ?subclass ?superclass where {"
//		+ "{ ?subclass rdfs:subClassOf ?superclass }"
//		+ "union"
//		+ "{ ?subclass rdfs:subClassOf [ owl:intersectionOf [ rdf:rest* [ rdf:first ?superclass ] ] ] }}";

      //推理主要是针对individual的，所以不能以class进行查询
//"SELECT ?sl ?ol WHERE {"
//+ "?s rdfs:subClassOf ?o."
//+ "?o rdfs:label ?ol "
//+ "FILTER REGEX(?ol, \"热带水果\") ."
//+ "?s rdfs:label ?sl.}";
//_:b**是指匿名类，实际上是推理的结果。
//"select ?subclass ?superclass where { ?subclass rdfs:subClassOf ?superclass}";


      //运用到推理的查询结果
//		"SELECT DISTINCT ?e ?s WHERE {?entity a ?subject.?subject rdfs:subClassOf  ?object."+
//"?object rdfs:label \"水果\"@zh.?entity rdfs:label ?e.?subject rdfs:label ?s}ORDER BY ?s";	

//		"SELECT DISTINCT ?e ?s WHERE {?entity a ?subject.?subject rdfs:subClassOf  ?object."+
//"?object rdfs:label \"热带水果\"@zh.?entity rdfs:label ?e.?subject rdfs:label ?s}ORDER BY ?s";	
		
		"SELECT DISTINCT ?e ?s WHERE {"
		+ "?entity a ?subject."
		+ "?subject rdfs:subClassOf  ?object."
		+ "?object rdfs:label \"温带水果\"@zh."
		+ "?entity rdfs:label ?e."
		+ "?subject rdfs:label ?s}"
		+ "ORDER BY ?s";	
//		

//"SELECT DISTINCT ?e ?s WHERE {?entity a ?subject.?subject rdfs:subClassOf  ?object."+
//"?object rdfs:label \"有喜好度的水果种类\"@zh.?entity rdfs:label ?e.?subject rdfs:label ?s}ORDER BY ?s";	

	



//		IReasoner famRea = ReasonerFactory.createFamilyReasoner();

//		famRea.getInfModel(ruleFile, m);
		
		System.out.println(queryString);
		
		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

		ResultSetFormatter.out(System.out, results, query);
        
//		famRea.searchOnto(queryString);

	}
	
	
	

	
	public static void main(String[] args) {
//		testExpert();
//		testFamily();
//		testQuery();
		testQuery1();
	}
	
}


