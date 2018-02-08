package edu.iastate.cs.entities.nlp;

import java.util.ArrayList;

import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.visitor.NLPVisitor;

public class ROOT extends NLPNode {

	public ROOT(){
		name="ROOT";
		listChildren=new ArrayList<NLPNode>();	
	}
	
	public void traverse(NLPVisitor visitor, 	Scope scope) throws Exception {
		if (visitor.visit(this, scope)) {
			for(int i=0;i<listChildren.size();i++){
				listChildren.get(i).traverse(visitor, scope);
			}
		} else{
			
			throw new Exception("Invalid production rules "+getName()+" : "+getRhs()); 
		}
		visitor.endVisit(this, scope);
	}

}
