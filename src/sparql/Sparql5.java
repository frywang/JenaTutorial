/*
 * 本代码来源于 http://goro.iteye.com/blog/1596091
 * 
 */



package sparql;

import java.io.IOException;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;  
  
import com.hp.hpl.jena.ontology.OntModel;  
import com.hp.hpl.jena.ontology.OntModelSpec;  
import com.hp.hpl.jena.query.Query;  
import com.hp.hpl.jena.query.QueryExecution;  
import com.hp.hpl.jena.query.QueryExecutionFactory;  
import com.hp.hpl.jena.query.QueryFactory;  
import com.hp.hpl.jena.query.QuerySolution;  
import com.hp.hpl.jena.query.ResultSet;  
import com.hp.hpl.jena.rdf.model.ModelFactory;  
  
public class Sparql5 {  
  
    public static void main(String args[]) throws IOException {  
  
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);  
  
        URL url = new URL("http://jvn.jp/rss/jvn.rdf");       
  
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();  
        InputStream in = url.openStream();  
        ontModel.read(in, "");  
        String queryString = "PREFIX rss:   <http://purl.org/rss/1.0/> SELECT ?item_title ?item_link"  
                +  
                " WHERE " + "{ ?item rss:title ?item_title " +  
  
                "FILTER regex(?item_title, \"MAC\", \"i\" ) ." +  
  
                "?item rss:link ?item_link }";  
  
        Query query = QueryFactory.create(queryString);  
        QueryExecution qe = QueryExecutionFactory.create(query, ontModel);  
        ResultSet results = qe.execSelect();  
        
        System.out.println("item_link"); 
        		
        while (results.hasNext()) {  
            QuerySolution qs = (QuerySolution) results.next();  
            System.out.println(qs.get("item_title"));  
            System.out.println(qs.get("item_link"));  
        }  
  
        // ResultSetFormatter.out(System.out, results, query);  
        qe.close();  
    }  
  
}  
