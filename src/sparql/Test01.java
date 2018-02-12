package sparql;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.RDFS;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        final String NS = "http://stackoverflow.com/q/20194409/1281433/";
        final OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM );

        OntProperty p = model.createOntProperty( NS+"PropertyName" );
        p.addDomain( model.getOntClass( NS+"ClassName" ));
        p.addRange( RDFS.Literal );

        model.write( System.out, "RDF/XML-ABBREV" );

	}

}
