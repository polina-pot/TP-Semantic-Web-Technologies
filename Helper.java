package com.ontoservlet.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

public class Helper {
	
	//method reads and uploads the ontology
	public static OntModel readOntology () throws FileNotFoundException{	
		FileInputStream fis = null;
		OntModel model = ModelFactory
		        .createOntologyModel(OntModelSpec.OWL_DL_MEM);
	    try
	    {
	        // Erstellung eines FileInputStreams, damit wir 
	        // aus der Datei t.tmp lesen kцnnen
	        fis = new FileInputStream("/Users/theresaandelfinger/Documents/workspace/Macqui/WebContent/OntologyJenaTest-2.rdf");

	    	    model.read(fis,null);
  
	    	    Iterator classIter = model.listClasses();
	    	    while (classIter.hasNext()) {
	    	        OntClass ontClass = (OntClass) classIter.next();
	    	        String uri = ontClass.getURI();
	    	        String className = ontClass.getLocalName();
	    	        OntClass demography = model.getOntClass(uri);
	    	    }
	    }
	    	    catch(FileNotFoundException ex)
		        {
		            ex.printStackTrace();
		        }	
			return model;
		}
	}