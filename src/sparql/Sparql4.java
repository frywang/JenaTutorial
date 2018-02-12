/**
 * 本文档代码来源于 http://stackoverflow.com/questions/22870793/how-to-query-an-owl-file-with-jena-on-eclipse
 */



package sparql;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.util.IndentedWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;


public class Sparql4{
          public static final String owlFile = "D:\\Fruits-lite.owl";
//        public static final String NL      = System.getProperty("line.separator") ;  

        public static void main( String[] args ) {

                Model m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);


                FileManager.get().readModel( m, owlFile );
              String myOntologyName = "http://www.w3.org/2002/07/owl#";
                String myOntologyNS   = "http://www.w3.org/2002/07/owl#";
////
////
//                  String rdfPrefix        = "PREFIX rdf: <"+RDF.getURI()+">" ;
//                String myOntologyPrefix = "PREFIX "+myOntologyName+": <"+myOntologyNS+">" ;
//                String myOntologyPrefix1 = "PREFIX "+myOntologyName ;
              String myOntologyPrefix2 = "prefix pizza: <http://www.w3.org/2002/07/owl#> ";
//
//
////                String queryString =   myOntologyPrefix + NL
////                                     + rdfPrefix + NL +
////                                       "SELECT ?subject" ;
                
                String queryString = rdfPrefix + myOntologyPrefix2 +
//        		        "prefix pizza: <http://www.w3.org/2002/07/owl#> "+        
        		        "prefix rdfs: <" + RDFS.getURI() + "> "           +
        		        "prefix owl: <" + OWL.getURI() + "> "             +
//        		        "select ?o where {?s ?p ?o}";
                
//						"select ?s where {?s rdfs:label "苹果"@zh}";
                
//                "SELECT  ?label WHERE { ?subject rdfs:label ?label }" ;
//                "SELECT DISTINCT ?predicate ?label WHERE { ?subject ?predicate ?object. ?predicate rdfs:label ?label .}";
//                 "SELECT DISTINCT ?s WHERE {?s rdfs:label \"Italy\"@en}" ;
//                   "SELECT DISTINCT ?s WHERE {?s rdfs:label \"苹果\"@zh}" ;
//                "SELECT DISTINCT ?s WHERE"  +
//                "{?object rdfs:label \"水果\"@zh." +
//                "?subject rdfs:subClassOf ?object." +
//                "?subject rdfs:label ?s}";
                //星号是转义字符，分号是转义字符
				"SELECT DISTINCT ?e ?s WHERE {?entity a ?subject.?subject rdfs:subClassOf  ?object."+
  "?object rdfs:label \"富士苹果\"@zh.?entity rdfs:label ?e.?subject rdfs:label ?s}ORDER BY ?s";
                		

                
        		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        		QueryExecution qe = QueryExecutionFactory.create(query, m);
        		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

        		ResultSetFormatter.out(System.out, results, query);
        		qe.close();

//                Query query = QueryFactory.create(queryString) ;

                query.serialize(new IndentedWriter(System.out,true)) ;
                System.out.println() ;



            QueryExecution qexec = QueryExecutionFactory.create(query, m) ;

            try {

                    ResultSet rs = qexec.execSelect() ;


                    for ( ; rs.hasNext() ; ){
                            QuerySolution rb = rs.nextSolution() ;
                            RDFNode y = rb.get("person");
                            System.out.print("name : "+y+"--- ");
                            Resource z = (Resource) rb.getResource("person");
                            System.out.println("plus simplement "+z.getLocalName());
                    }
            }
            finally{
              qexec.close() ;
            }
    }
}

