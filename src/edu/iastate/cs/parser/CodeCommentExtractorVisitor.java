package edu.iastate.cs.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import util.FileIO;

public class CodeCommentExtractorVisitor extends ASTVisitor {

	private String[] arrSource;
	private String jdkPath;
	private String source;
	private StringBuilder doc = new StringBuilder();
	private String currentFilePath;
	HashMap<String, CompilationUnit> cus;
	private StringBuilder sbCommentContent, sbCommentFile,sbCommentLine;

	public CodeCommentExtractorVisitor(String[] arrSource, String jdkPath) {
		this.arrSource = arrSource;
		this.jdkPath = jdkPath;
	}

	public void loadSourceFiles() {
		Map options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
				JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setCompilerOptions(options);
		parser.setEnvironment(null, new String[] {}, new String[] {}, true);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(false);

		cus = new HashMap<>();
		FileASTRequestor r = new FileASTRequestor() {
			@Override
			public void acceptAST(String sourceFilePath, CompilationUnit ast) {
				// System.out.println(ast.toString());
				// if (ast.getPackage() == null)
				// return;

				cus.put(sourceFilePath, ast);
			}
		};
		try {
			parser.createASTs(this.arrSource, null, new String[0], r, null);
		} catch (Throwable t) {
			t.printStackTrace();
			// if (testing) {
			// System.err.println(t.getMessage());
			// t.printStackTrace();
			// System.exit(-1);
			// }
		}

		sbCommentContent = new StringBuilder();
		sbCommentFile = new StringBuilder();
		sbCommentLine=new StringBuilder();

		for (String sourceFilePath : cus.keySet()) {
			CompilationUnit ast = cus.get(sourceFilePath);
			source = FileIO.readStringFromFile(sourceFilePath);
			currentFilePath = sourceFilePath;
			// ast.accept(this);
			for (Comment comment : (List<Comment>) ast.getCommentList()) {
				comment.accept(this);
			}
		}
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		// System.out.println("Method declaration: "+node.toString());

		return false;
	}

	@Override
	public boolean visit(MethodInvocation node) {
		// System.out.println("Method: "+node.toString());
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

	public boolean visit(LineComment node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		CompilationUnit ast = cus.get(currentFilePath);
		int lineNumber = ast.getLineNumber(start) - 1;
		String comment = source.substring(start, end);
		System.out.println(comment + "\t" + lineNumber);
		
		//add line to file
		sbCommentContent.append(comment+"\n");
		sbCommentFile.append(currentFilePath+"\n");
		sbCommentLine.append(lineNumber+"\n");
		
		
//		Scanner sc=new Scanner(System.in);
//		sc.next();
		return true;
	}

	public boolean visit(BlockComment node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		CompilationUnit ast = cus.get(currentFilePath);
		int lineNumber = ast.getLineNumber(start) - 1;
		String comment = source.substring(start, end);
		System.out.println(comment + "\t" + lineNumber);
		
		return true;
	}
	
	public void saveResultToFile(String pathComment,String pathFile,String pathLine){
		FileIO.writeStringToFile(sbCommentContent.toString(), pathComment);
		FileIO.writeStringToFile(sbCommentFile.toString(), pathFile);
		FileIO.writeStringToFile(sbCommentLine.toString(), pathLine);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
