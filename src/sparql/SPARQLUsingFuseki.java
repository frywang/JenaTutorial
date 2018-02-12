package sparql;

//import static util.Constants."/n";
//
//import org.apache.jena.fusek.*;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;

public class SPARQLUsingFuseki {
    static final String PREFIX = "graph";
    static final String NS = "http://www.nosql.com/graph#";

    public static void main(String[] args) {
        
        sparqlUpdate();

    }

    @SuppressWarnings("deprecation")
    static void sparqlUpdate() {
        String update = "";
        StringBuilder sb = new StringBuilder();

        sb.append("PREFIX graph: <http://www.nosql.com/graph#>").append("/n").append("    INSERT DATA").append("/n").append("{").append("/n")
                .append("graph:Martin graph:friend  graph:ZhouJiaGen2 .").append("/n").append("}");

        update = sb.toString();

//        UpdateRequest request = UpdateFactory.create(update);
//        UpdateRemote.execute(request, "http://localhost:3030/data/update");
        

    }

    // / output:
    // http://www.nosql.com/graph#Pramod
    static void sparqlQuery() {
        // 准备SPARQL查询
        StringBuilder sb = new StringBuilder();
        sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>").append("/n").append("PREFIX owl: <http://www.w3.org/2002/07/owl#>")
                .append("/n").append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>").append("/n")
                .append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>").append("/n").append("PREFIX foaf: <http://xmlns.com/foaf/0.1/>")
                .append("/n").append("PREFIX myfoaf: <http://blog.sina.com.cn/zhoujiagenontology/helloworld.owl#>").append("/n")
                .append("PREFIX " + PREFIX + ": " + "<" + NS + ">").append("/n");
        sb.append("SELECT ?friend WHERE { graph:Martin graph:friend ?friend }");
        String service = "http://localhost:3030/data/query";// http://localhost:3030/<<dataset>>/query
        String query = sb.toString();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(service, query);
        sparqlHelp(queryExecution, "?friend");
    }

    static void sparqlHelp(QueryExecution queryExecution, String valueLabel) {
        ResultSet rs = queryExecution.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();
            RDFNode name = qs.get(valueLabel);
            if (name != null) {
                System.out.println(name.toString());
            } else {
                System.out.println("Not found!");
            }
        }
        queryExecution.close();
    }
}
