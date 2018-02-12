package sparql;

import javax.sql.DataSource;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import virtuoso.jena.driver.VirtGraph;

public class AshTest
{
    public static void main( String[] args ) {
    
    	/**
    	 * 这是你给我的那个网上的 sql 地址查询的是你的？这个类里面没有连接到我的数据库，它是查询一个网页页面上的，就是http://dbpedia.org/ontology/这个页面吧
    	 * 要不就不用ResultSetFormatter.out了吧，它一直都报错Method virtuoso/jena/driver/VirtuosoQueryExecution$VResultSet.isOrdered()Z is abstract，也搜不到解决方法

    	 * 把你的数据库拿来
    	 */
    	VirtGraph set = new VirtGraph ("jdbc:virtuoso://192.168.1.112:1111", "dba", "dba");
    	DataSource aaa =set.getDataSource();
    	System.out.println(aaa);
//    为什么出来的还是那个网站的结果。。 我不知道。我看着这个地址是第一个地址。。
     //   Query query = QueryFactory.create("select distinct ?Concept where {[] a ?Concept} LIMIT 100" ); //s2 = the query above
      //  QueryExecution qExe = QueryExecutionFactory.create(query, (Dataset) set);//( "jdbc:virtuoso://192.168.1.112:1111", query );
     //   ResultSet results = qExe.execSelect();
     //   ResultSetFormatter.out(System.out, results, query) ;
    }
}
