package edu.iastate.cs.entities.nlp;

import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.visitor.NLPVisitor;


/**
 * @author hungphan
 * X - Unknown, uncertain, or unbracketable. X is often used for bracketing typos and in bracketing the...the-constructions.
 */
public class X extends NLPNode{
	public X(){
		
		name="X";
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
