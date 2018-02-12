 /** 
  * 本代码来自《Jena 简介》中第三页
  *http://wenku.baidu.com/link?url=OMsZaKhA6sNkusWOMFRTDljmTKJyPTs-iptFV25-CnMMfitrCmLsn5m9LArEeaPfyKEj3iljU2tIFV3qtFiGHiUHMno8_YorwbgvRJqcVry
  *
  *
  * 
  * 
  * */ 


package parse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;


public class CreateQueryOWL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//URI declarations
		String familyUri = "http://family/";
		String relationshipUri = "http://purl.org/vocab/relationship/";
		
		//Creat an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		//Creat a Resource for each family number
		Resource adam = model.createResource(familyUri+"adam");
		Resource beth = model.createResource(familyUri+"beth");		
		Resource chunk = model.createResource(familyUri+"chunk");		
		Resource dotty = model.createResource(familyUri+"dotty");		
	
		//and so on for other family numbers 
		//Create properties for the different types of relationship to represent
		
		Property childOf = model.createProperty(relationshipUri,"childOf");
		Property parentOf = model.createProperty(relationshipUri,"parentOf");
		Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
		Property spouseOf = model.createProperty(relationshipUri,"spouseOf");
		
		//add properties to adam describing relationships to other family numbers
		adam.addProperty(siblingOf, beth);
		adam.addProperty(spouseOf, dotty);
		adam.addProperty(parentOf, beth);
		
		//can also create statement directly...
		Statement statement = model.createStatement(adam,parentOf,"fran");
		
		//add the created statement to the model
		model.add(statement);
		

//清单1.查询家庭模型中的subject

	//list everyone in the model who has a child;
	ResIterator parents = model.listSubjectsWithProperty(parentOf);
	//Because subjects of statements are Resources, the method returned a ResIterator
	System.out.println("\r\n家庭模型中的所有subject有：");
	while (parents.hasNext()){
		//ResIterator has a typed nextResource() met hold
		Resource person = parents.nextResource();
		//Print the URI of the resource
		System.out.println(person.getURI());
		
		//can also find all the parents by getting the objects of all"childOf" statements.
		//Objects of statements could be Resources or literals,so the Iterator returned
		//contains RDFNodes

	}
		
		
//清单2.查询家庭模型中的object

	//Objects of statements could be Resources or literals,so the Iterator returned
	//contain RDFNodes
	NodeIterator moreParents = model.listObjectsOfProperty(parentOf);
	//to find all the siblings of a specific person,the model itself can be required
	System.out.println("\r\n家庭模型中的所有object有：");
	while (moreParents.hasNext()){
		RDFNode object = moreParents.nextNode();
		System.out.println(object);
	}

	
//清单3.查询家庭模型中的statement
	
	//To find all the siblings of a specific person,the model itself can be queried
			
	StmtIterator stmtparent = model.listStatements();
	System.out.println("\r\n家庭模型中的所有statements有：");
	while (stmtparent.hasNext()){
		Statement s = stmtparent.nextStatement();
		System.out.println(s.getSubject());
		System.out.println(s.getPredicate());
		System.out.println(s.getObject());
	}
	
	
////Find the exact statement"adam is a spouse of dotty"
	StmtIterator o =model.listStatements(adam, spouseOf, dotty);
	System.out.println("\r\n此statement是否存在？");
	while(o.hasNext()){
		Statement s = o.nextStatement();
		System.out.println("此statement存在");
	}
	
//Find all the statements with adam as the subject and dotty as the object
	StmtIterator p =model.listStatements(adam, null, dotty);
	System.out.println("\r\n此statement的谓词是：");
	while(p.hasNext()){
		Statement s1 = p.nextStatement();
		System.out.println(s1.getPredicate());
	}		
	
//Find any statements made about adam
	StmtIterator q =model.listStatements(adam, null, (RDFNode)null);
	System.out.println("\r\n与Adam有关系的statement有：");
	while(q.hasNext()){
		Statement s2 = q.nextStatement();
		System.out.println(s2.getPredicate()+"  "+s2.getObject());
	}		
	

//find any statement with the siblingOf property
	StmtIterator r =model.listStatements(null, siblingOf, (RDFNode)null);
	System.out.println("\r\n具有sibling关系的有：");
	while(r.hasNext()){
		Statement s3 = r.nextStatement();
		System.out.println(s3.getSubject()+"  和  "+s3.getObject());

	}		
	
	}
}
