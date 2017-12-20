package edu.iastate.cs.parser;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class CodeCommentExtractorVisitor  extends ASTVisitor  {

	private String[] arrSource;
	private String jdkPath;
	private StringBuilder doc = new StringBuilder();
	HashMap<String, CompilationUnit> cus;
	public CodeCommentExtractorVisitor(String[] arrSource,String jdkPath){
		this.arrSource=arrSource;
		this.jdkPath=jdkPath;
	}
	
	public void loadSourceFiles(){
		Map options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setCompilerOptions(options);
		parser.setEnvironment(null, new String[]{}, new String[]{}, true);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(false);
		
		cus = new HashMap<>();
		FileASTRequestor r = new FileASTRequestor() {
			@Override
			public void acceptAST(String sourceFilePath, CompilationUnit ast) {
				if (ast.getPackage() == null)
					return;
				cus.put(sourceFilePath, ast);
			}
		};
		try {
			parser.createASTs(this.arrSource, null, new String[0], r, null);
		} catch (Throwable t) {
			t.printStackTrace();
//			if (testing) {
//				System.err.println(t.getMessage());
//				t.printStackTrace();
//				System.exit(-1);
//			}
		}
		
		for (String sourceFilePath : cus.keySet()) {
			CompilationUnit ast = cus.get(sourceFilePath);
			ast.accept(this);
		}
	}
	
	
	
	@Override
	public boolean visit(MethodInvocation node) {
		System.out.println(node.toString());
		return false;
	}
	
	@Override
	public boolean visit(MemberRef node) {
		return false;
	}
	@Override
	public boolean visit(MethodRef node) {
		return false;
	}
	@Override
	public boolean visit(QualifiedName node) {
		return false;
	}
	@Override
	public boolean visit(SimpleName node) {
		return false;
	}
	@Override
	public boolean visit(TagElement node) {
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
