package edu.iastate.cs.entities.nlp;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.internal.compiler.ASTVisitor;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;

import edu.iastate.cs.entities.Scope;
import edu.iastate.cs.visitor.NLPVisitor;


/**
 * @author hungphan
 * S - simple declarative clause, i.e. one that is not introduced by a (possible empty) subordinating conjunction or a wh-word and that does not exhibit subject-verb inversion.
 */
public class S extends NLPNode{
	public S(){
		name="S";
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
