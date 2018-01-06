package edu.iastate.cs.entities.nlp;


/**
 * @author hungphan
 * SBARQ - Direct question introduced by a wh-word or a wh-phrase. Indirect questions and relative clauses should be bracketed as SBAR, not SBARQ.
 */
public class SBARQ extends NLPNode{
	public SBARQ(){
		name="SBARQ";
	}
}
