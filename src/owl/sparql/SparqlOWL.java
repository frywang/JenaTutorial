package owl.sparql;

/**
 * 本代码来源于http://stackoverflow.com/questions/22846026/how-to-query-an-ontology-file-with-jena-on-eclipse
 */



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


public class SparqlOWL{
          public static final String owlFile = "data/食材.owl";
          public static final String NL      = System.getProperty("line.separator") ;  

          public static void main( String[] args ) {
                Model m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
                FileManager.get().readModel( m, owlFile );
//                log.debug( "We have loaded a model with no. statements = " + m.size() );
                String myOntologyPrefix2 = "prefix qieyinChild: <http://www.w3.org/2002/07/owl#> ";          
//                Query string
                String queryString = myOntologyPrefix2+      
        		        "prefix rdfs: <" + RDFS.getURI() + "> "           +
        		        "prefix owl: <" + OWL.getURI() + "> "             +
        		        
						"SELECT ?p where {?s ?p ?o}";//查询三元组
//               
//						"SELECT DISTINCT ?s WHERE {?s rdfs:label \"苹果\"@zh}" ;//查询label所对应的概念
//						
//						"SELECT  ?label WHERE { ?subject rdfs:label ?label }" ;//查询所有lable
//				"SELECT  ?comment WHERE { ?subject rdfs:comment ?comment }" ;//查询所有lable
//						
//						"SELECT DISTINCT ?predicate ?label WHERE { ?subject ?predicate ?object. ?predicate rdfs:label ?label .}";//查询属性和lable

//						"SELECT DISTINCT ?subject ?s WHERE"  +
//						"{?object rdfs:label \"生物\"@zh." +
//						"?subject rdfs:subClassOf ?object." +
//						"?subject rdfs:label ?s}";//查询所有子类和lable
              
//						"SELECT DISTINCT ?e ?s WHERE {?entity a ?subject.?subject rdfs:subClassOf  ?object."+
//						"?object rdfs:label \"生物\"@zh.?entity rdfs:label ?e.?subject rdfs:label ?s}ORDER BY ?s";//查询概念下所有实例
                		

                
        		com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        		QueryExecution qe = QueryExecutionFactory.create(query, m);
        		com.hp.hpl.jena.query.ResultSet results =  qe.execSelect();

        		ResultSetFormatter.out(System.out, results, query);
        		qe.close();

//                Query query = QueryFactory.create(queryString) ;

                query.serialize(new IndentedWriter(System.out,true)) ;
                System.out.println() ;


                
            //  final Query q = QueryFactory.create(qry); 
            //  final QueryExecution qexec = QueryExecutionFactory.create(q, m); 
            //  final List<QuerySolution> retval = WrappedIterator.create( 
//                qexec.execSelect()).toList(); 
            //  for (final QuerySolution qs : retval) { 
            //   System.out.println(qs); 
//              } 
//             } 
            // 
            //// static URL fUrl = J4SConnectionTest.class 
            ////   .getResource("./J4SStatementTest.ttl"); 
            //
            // 
            // 
            ////static String qry = "SELECT ?s ?o  WHERE {?s <http://www.360iii.org/ontologies/fruit#OWLDataProperty_01cb6c89_7e09_4382_b617_255891d3d34121212> ?o }";
            //static String qry = "SELECT ?s   WHERE {?s a <http://www.360iii.org/ontologies/fruit#OWLClass_31e25bf3_1a91_49c5_a2e3_cb7825fa139e> .}";
            // 
            //// static String qry = "SELECT  * " 
            ////   + "   WHERE" 
            ////   + "     { { { { ?fooTable <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://example.com/jdbc4sparql#fooTable> ." 
            ////   + "             ?fooTable <http://example.com/jdbc4sparql#IntCol> ?v_b3f2fd82_c102_3c4d_baed_5958c464a424 ." 
            ////   + "             ?fooTable <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?v_599dcce2_998a_3b40_b1e3_8e8c6006cb0a ." 
            ////   + "             ?fooTable <http://example.com/jdbc4sparql#StringCol> ?v_f20fd591_2dfa_3a09_b81b_7c6f31cc3159" 
            ////   + "           }" 
            ////   + "           OPTIONAL" 
            ////   + "             { ?fooTable <http://example.com/jdbc4sparql#NullableStringCol> ?v_2ca911d9_9e97_3d80_aaae_d6347f341e4e}" 
            ////   + "           OPTIONAL" 
            ////   + "             { ?fooTable <http://example.com/jdbc4sparql#NullableIntCol> ?v_ce84b044_b71d_37a4_bc63_462bd432993c}" 
            ////   + "         }" 
            ////   + "    BIND((?v_b3f2fd82_c102_3c4d_baed_5958c464a424) AS ?IntCol)" 
            ////   + "        BIND((?v_ce84b044_b71d_37a4_bc63_462bd432993c) AS ?NullableIntCol)" 
            ////   + "        BIND((?v_599dcce2_998a_3b40_b1e3_8e8c6006cb0a) AS ?type)" 
            ////   + "        BIND((?v_f20fd591_2dfa_3a09_b81b_7c6f31cc3159) AS ?StringCol)" 
            ////   + "        BIND((?v_2ca911d9_9e97_3d80_aaae_d6347f341e4e) AS ?NullableStringCol)" 
            ////   + "       }" + "     }"; 
            ////}                
                
                

//            QueryExecution qexec = QueryExecutionFactory.create(query, m) ;
//
//            try {
//
//                    ResultSet rs = qexec.execSelect() ;
//
//
//                    for ( ; rs.hasNext() ; ){
//                            QuerySolution rb = rs.nextSolution() ;
//                            RDFNode y = rb.get("person");
//                            System.out.print("name : "+y+"--- ");
//                            Resource z = (Resource) rb.getResource("person");
//                            System.out.println("plus simplement "+z.getLocalName());
//                    }
//            }
//            finally{
//              qexec.close() ;
//            }
    }
}
