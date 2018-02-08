package edu.iastate.cs.visitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.internal.compiler.parser.ScannerHelper;

import util.FileIO;
import util.JavaASTUtil;

public class NaiveASTVisitor  extends ASTVisitor {

	private boolean isInMethodVisit=false;
	private String[] arrSource;
	private String jdkPath;
	private String source;
	private StringBuilder doc = new StringBuilder();
	private String currentFilePath;
	HashMap<String, CompilationUnit> cus;	
	private StringBuilder sbASTContent, sbFile;
	
	
	
	public StringBuilder getSbASTContent() {
		return sbASTContent;
	}

	public void setSbASTContent(StringBuilder sbASTContent) {
		this.sbASTContent = sbASTContent;
	}

	public StringBuilder getSbFile() {
		return sbFile;
	}

	public void setSbFile(StringBuilder sbFile) {
		this.sbFile = sbFile;
	}

	public NaiveASTVisitor(String[] arrSource, String jdkPath) {
		this.arrSource = arrSource;
		this.jdkPath = jdkPath;
		this.buffer = new StringBuffer();
	}
	
	/**
	 * Internal synonym for {@link AST#JLS2}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private static final int JLS2 = AST.JLS2;

	/**
	 * Internal synonym for {@link AST#JLS3}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private static final int JLS3 = AST.JLS3;

	/**
	 * Internal synonym for {@link AST#JLS4}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.10
	 */
	private static final int JLS4 = AST.JLS4;

	/**
	 * The string buffer into which the serialized representation of the AST is
	 * written.
	 */
	protected StringBuffer buffer;

	private int indent = 0;

	/**
	 * Creates a new AST printer.
	 */
//	public NaiveASTVisitor() {
//		this.buffer = new StringBuffer();
//	}
	
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

//		sbCommentContent = new StringBuilder();
//		sbCommentFile = new StringBuilder();
//		sbCommentLine = new StringBuilder();
		sbASTContent=new StringBuilder();
		sbFile=new StringBuilder();

		for (String sourceFilePath : cus.keySet()) {
			CompilationUnit ast = cus.get(sourceFilePath);
			source = FileIO.readStringFromFile(sourceFilePath);
			currentFilePath = sourceFilePath;
			ast.accept(this);
			
		}
	}
	
	public void saveResultToFile(String pathFile,String pathComment) {
		FileIO.writeStringToFile(sbFile.toString(), pathFile);
		FileIO.writeStringToFile(sbASTContent.toString(), pathComment);
				
	}
	
	private String getSigParameters(MethodDeclaration method) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < method.parameters().size(); i++) {
			SingleVariableDeclaration d = (SingleVariableDeclaration) (method.parameters().get(i));
			String type = util.JavaASTUtil.getSimpleType(d.getType());
			sb.append( type);
			if(i<method.parameters().size()-1){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Internal synonym for {@link ClassInstanceCreation#getName()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private Name getName(ClassInstanceCreation node) {
		return node.getName();
	}

	/**
	 * Returns the string accumulated in the visit.
	 *
	 * @return the serialized
	 */
	public String getResult() {
		return this.buffer.toString();
	}

	/**
	 * Internal synonym for {@link MethodDeclaration#getReturnType()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private static Type getReturnType(MethodDeclaration node) {
		return node.getReturnType();
	}

	/**
	 * Internal synonym for {@link TypeDeclaration#getSuperclass()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private static Name getSuperclass(TypeDeclaration node) {
		return node.getSuperclass();
	}

	/**
	 * Internal synonym for {@link TypeDeclarationStatement#getTypeDeclaration()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private static TypeDeclaration getTypeDeclaration(TypeDeclarationStatement node) {
		return node.getTypeDeclaration();
	}

	/**
	 * Internal synonym for {@link MethodDeclaration#thrownExceptions()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.10
	 */
	private static List thrownExceptions(MethodDeclaration node) {
		return node.thrownExceptions();
	}

	void printIndent() {
		for (int i = 0; i < this.indent; i++)
			appendToken("  "); //$NON-NLS-1$
	}

	/**
	 * Appends the text representation of the given modifier flags, followed by a single space.
	 * Used for JLS2 modifiers.
	 *
	 * @param modifiers the modifier flags
	 */
	void printModifiers(int modifiers) {
		if (Modifier.isPublic(modifiers)) {
			appendToken("public ");//$NON-NLS-1$
		}
		if (Modifier.isProtected(modifiers)) {
			appendToken("protected ");//$NON-NLS-1$
		}
		if (Modifier.isPrivate(modifiers)) {
			appendToken("private ");//$NON-NLS-1$
		}
		if (Modifier.isStatic(modifiers)) {
			appendToken("static ");//$NON-NLS-1$
		}
		if (Modifier.isAbstract(modifiers)) {
			appendToken("abstract ");//$NON-NLS-1$
		}
		if (Modifier.isFinal(modifiers)) {
			appendToken("final ");//$NON-NLS-1$
		}
		if (Modifier.isSynchronized(modifiers)) {
			appendToken("synchronized ");//$NON-NLS-1$
		}
		if (Modifier.isVolatile(modifiers)) {
			appendToken("volatile ");//$NON-NLS-1$
		}
		if (Modifier.isNative(modifiers)) {
			appendToken("native ");//$NON-NLS-1$
		}
		if (Modifier.isStrictfp(modifiers)) {
			appendToken("strictfp ");//$NON-NLS-1$
		}
		if (Modifier.isTransient(modifiers)) {
			appendToken("transient ");//$NON-NLS-1$
		}
	}

	/**
	 * Appends the text representation of the given modifier flags, followed by a single space.
	 * Used for 3.0 modifiers and annotations.
	 *
	 * @param ext the list of modifier and annotation nodes
	 * (element type: <code>IExtendedModifiers</code>)
	 */
	void printModifiers(List ext) {
		for (Iterator it = ext.iterator(); it.hasNext(); ) {
			ASTNode p = (ASTNode) it.next();
			p.accept(this);
			appendToken(" ");//$NON-NLS-1$
		}
	}

	/**
	 * reference node helper function that is common to all
	 * the difference reference nodes.
	 *
	 * @param typeArguments list of type arguments
	 */
	private void visitReferenceTypeArguments(List typeArguments) {
		appendToken("::");//$NON-NLS-1$
		if (!typeArguments.isEmpty()) {
			appendToken('<');
			for (Iterator it = typeArguments.iterator(); it.hasNext(); ) {
				Type t = (Type) it.next();
				t.accept(this);
				if (it.hasNext()) {
					appendToken(',');
				}
			}
			appendToken('>');
		}
	}

	private void visitTypeAnnotations(AnnotatableType node) {
		if (node.getAST().apiLevel() >= AST.JLS8) {
			visitAnnotationsList(node.annotations());
		}
	}

	private void visitAnnotationsList(List annotations) {
		for (Iterator it = annotations.iterator(); it.hasNext(); ) {
			Annotation annotation = (Annotation) it.next();
			annotation.accept(this);
			appendToken(' ');
		}
	}

	/**
	 * Resets this printer so that it can be used again.
	 */
	public void reset() {
		this.buffer.setLength(0);
	}

	public void appendToken(String strToken){
		if(isInMethodVisit){
			this.buffer.append(strToken+" ");
		}
	}

	public void appendToken(char strToken){
		if(isInMethodVisit){
			this.buffer.append(strToken+" ");
		}
	}

	/**
	 * Internal synonym for {@link TypeDeclaration#superInterfaces()}. Use to alleviate
	 * deprecation warnings.
	 * @deprecated
	 * @since 3.4
	 */
	private List superInterfaces(TypeDeclaration node) {
		return node.superInterfaces();
	}

	/*
	 * @see ASTVisitor#visit(AnnotationTypeDeclaration)
	 * @since 3.1
	 */
	public boolean visit(AnnotationTypeDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		printModifiers(node.modifiers());
		appendToken("@interface ");//$NON-NLS-1$
		node.getName().accept(this);
		appendToken(" {");//$NON-NLS-1$
		for (Iterator it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
			BodyDeclaration d = (BodyDeclaration) it.next();
			d.accept(this);
		}
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(AnnotationTypeMemberDeclaration)
	 * @since 3.1
	 */
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		printModifiers(node.modifiers());
		node.getType().accept(this);
		appendToken(" ");//$NON-NLS-1$
		node.getName().accept(this);
		appendToken("()");//$NON-NLS-1$
		if (node.getDefault() != null) {
			appendToken(" default ");//$NON-NLS-1$
			node.getDefault().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(AnonymousClassDeclaration)
	 */
	public boolean visit(AnonymousClassDeclaration node) {
		appendToken("{ ");//$NON-NLS-1$
		this.indent++;
		for (Iterator it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
			BodyDeclaration b = (BodyDeclaration) it.next();
			b.accept(this);
		}
		this.indent--;
		printIndent();
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ArrayAccess)
	 */
	public boolean visit(ArrayAccess node) {
		node.getArray().accept(this);
		appendToken("[");//$NON-NLS-1$
		node.getIndex().accept(this);
		appendToken("]");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ArrayCreation)
	 */
	public boolean visit(ArrayCreation node) {
		appendToken("new ");//$NON-NLS-1$
		ArrayType at = node.getType();
		int dims = at.getDimensions();
		Type elementType = at.getElementType();
		elementType.accept(this);
		for (Iterator it = node.dimensions().iterator(); it.hasNext(); ) {
			appendToken("[");//$NON-NLS-1$
			Expression e = (Expression) it.next();
			e.accept(this);
			appendToken("]");//$NON-NLS-1$
			dims--;
		}
		// add empty "[]" for each extra array dimension
		for (int i= 0; i < dims; i++) {
			appendToken("[]");//$NON-NLS-1$
		}
		if (node.getInitializer() != null) {
			node.getInitializer().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ArrayInitializer)
	 */
	public boolean visit(ArrayInitializer node) {
		appendToken("{");//$NON-NLS-1$
		for (Iterator it = node.expressions().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken("}");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ArrayType)
	 */
	public boolean visit(ArrayType node) {
		if (node.getAST().apiLevel() < AST.JLS8) {
			visitComponentType(node);
			appendToken("[]");//$NON-NLS-1$
		} else {
			node.getElementType().accept(this);
			List dimensions = node.dimensions();
			int size = dimensions.size();
			for (int i = 0; i < size; i++) {
				Dimension aDimension = (Dimension) dimensions.get(i);
				aDimension.accept(this);
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(AssertStatement)
	 */
	public boolean visit(AssertStatement node) {
		printIndent();
		appendToken("assert ");//$NON-NLS-1$
		node.getExpression().accept(this);
		if (node.getMessage() != null) {
			appendToken(" : ");//$NON-NLS-1$
			node.getMessage().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(Assignment)
	 */
	public boolean visit(Assignment node) {
		node.getLeftHandSide().accept(this);
		appendToken(node.getOperator().toString());
		node.getRightHandSide().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(Block)
	 */
	public boolean visit(Block node) {
		appendToken("{ ");//$NON-NLS-1$
		this.indent++;
		for (Iterator it = node.statements().iterator(); it.hasNext(); ) {
			Statement s = (Statement) it.next();
			s.accept(this);
		}
		this.indent--;
		printIndent();
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(BlockComment)
	 * @since 3.0
	 */
	public boolean visit(BlockComment node) {
		printIndent();
		appendToken("/* */");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(BooleanLiteral)
	 */
	public boolean visit(BooleanLiteral node) {
		if (node.booleanValue() == true) {
			appendToken("true");//$NON-NLS-1$
		} else {
			appendToken("false");//$NON-NLS-1$
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(BreakStatement)
	 */
	public boolean visit(BreakStatement node) {
		printIndent();
		appendToken("break");//$NON-NLS-1$
		if (node.getLabel() != null) {
			appendToken(" ");//$NON-NLS-1$
			node.getLabel().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(CastExpression)
	 */
	public boolean visit(CastExpression node) {
		appendToken("(");//$NON-NLS-1$
		node.getType().accept(this);
		appendToken(")");//$NON-NLS-1$
		node.getExpression().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(CatchClause)
	 */
	public boolean visit(CatchClause node) {
		appendToken("catch (");//$NON-NLS-1$
		node.getException().accept(this);
		appendToken(") ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(CharacterLiteral)
	 */
	public boolean visit(CharacterLiteral node) {
		appendToken(node.getEscapedValue());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ClassInstanceCreation)
	 */
	public boolean visit(ClassInstanceCreation node) {
		if (node.getExpression() != null) {
			node.getExpression().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		appendToken("new ");//$NON-NLS-1$
		if (node.getAST().apiLevel() == JLS2) {
			getName(node).accept(this);
		}
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeArguments().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
			node.getType().accept(this);
		}
		appendToken("(");//$NON-NLS-1$
		for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(")");//$NON-NLS-1$
		if (node.getAnonymousClassDeclaration() != null) {
			node.getAnonymousClassDeclaration().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(CompilationUnit)
	 */
	public boolean visit(CompilationUnit node) {
		if (node.getPackage() != null) {
			node.getPackage().accept(this);
		}
		for (Iterator it = node.imports().iterator(); it.hasNext(); ) {
			ImportDeclaration d = (ImportDeclaration) it.next();
			d.accept(this);
		}
		for (Iterator it = node.types().iterator(); it.hasNext(); ) {
			AbstractTypeDeclaration d = (AbstractTypeDeclaration) it.next();
			d.accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ConditionalExpression)
	 */
	public boolean visit(ConditionalExpression node) {
		node.getExpression().accept(this);
		appendToken(" ? ");//$NON-NLS-1$
		node.getThenExpression().accept(this);
		appendToken(" : ");//$NON-NLS-1$
		node.getElseExpression().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ConstructorInvocation)
	 */
	public boolean visit(ConstructorInvocation node) {
		printIndent();
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeArguments().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
		}
		appendToken("this(");//$NON-NLS-1$
		for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken("); ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ContinueStatement)
	 */
	public boolean visit(ContinueStatement node) {
		printIndent();
		appendToken("continue");//$NON-NLS-1$
		if (node.getLabel() != null) {
			appendToken(" ");//$NON-NLS-1$
			node.getLabel().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(CreationReference)
	 *
	 * @since 3.10
	 */
	public boolean visit(CreationReference node) {
		node.getType().accept(this);
		visitReferenceTypeArguments(node.typeArguments());
		appendToken("new");//$NON-NLS-1$
		return false;
	}

	public boolean visit(Dimension node) {
		List annotations = node.annotations();
		if (annotations.size() > 0)
			appendToken(' ');
		visitAnnotationsList(annotations);
		appendToken("[]"); //$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(DoStatement)
	 */
	public boolean visit(DoStatement node) {
		printIndent();
		appendToken("do ");//$NON-NLS-1$
		node.getBody().accept(this);
		appendToken(" while (");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken("); ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(EmptyStatement)
	 */
	public boolean visit(EmptyStatement node) {
		printIndent();
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(EnhancedForStatement)
	 * @since 3.1
	 */
	public boolean visit(EnhancedForStatement node) {
		printIndent();
		appendToken("for (");//$NON-NLS-1$
		node.getParameter().accept(this);
		appendToken(" : ");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(") ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(EnumConstantDeclaration)
	 * @since 3.1
	 */
	public boolean visit(EnumConstantDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		printModifiers(node.modifiers());
		node.getName().accept(this);
		if (!node.arguments().isEmpty()) {
			appendToken("(");//$NON-NLS-1$
			for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
				Expression e = (Expression) it.next();
				e.accept(this);
				if (it.hasNext()) {
					appendToken(",");//$NON-NLS-1$
				}
			}
			appendToken(")");//$NON-NLS-1$
		}
		if (node.getAnonymousClassDeclaration() != null) {
			node.getAnonymousClassDeclaration().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(EnumDeclaration)
	 * @since 3.1
	 */
	public boolean visit(EnumDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		printModifiers(node.modifiers());
		appendToken("enum ");//$NON-NLS-1$
		node.getName().accept(this);
		appendToken(" ");//$NON-NLS-1$
		if (!node.superInterfaceTypes().isEmpty()) {
			appendToken("implements ");//$NON-NLS-1$
			for (Iterator it = node.superInterfaceTypes().iterator(); it.hasNext(); ) {
				Type t = (Type) it.next();
				t.accept(this);
				if (it.hasNext()) {
					appendToken(", ");//$NON-NLS-1$
				}
			}
			appendToken(" ");//$NON-NLS-1$
		}
		appendToken("{");//$NON-NLS-1$
		for (Iterator it = node.enumConstants().iterator(); it.hasNext(); ) {
			EnumConstantDeclaration d = (EnumConstantDeclaration) it.next();
			d.accept(this);
			// enum constant declarations do not include punctuation
			if (it.hasNext()) {
				// enum constant declarations are separated by commas
				appendToken(", ");//$NON-NLS-1$
			}
		}
		if (!node.bodyDeclarations().isEmpty()) {
			appendToken("; ");//$NON-NLS-1$
			for (Iterator it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
				BodyDeclaration d = (BodyDeclaration) it.next();
				d.accept(this);
				// other body declarations include trailing punctuation
			}
		}
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ExpressionMethodReference)
	 *
	 * @since 3.10
	 */
	public boolean visit(ExpressionMethodReference node) {
		node.getExpression().accept(this);
		visitReferenceTypeArguments(node.typeArguments());
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ExpressionStatement)
	 */
	public boolean visit(ExpressionStatement node) {
		printIndent();
		node.getExpression().accept(this);
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(FieldAccess)
	 */
	public boolean visit(FieldAccess node) {
		node.getExpression().accept(this);
		appendToken(".");//$NON-NLS-1$
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(FieldDeclaration)
	 */
	public boolean visit(FieldDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		node.getType().accept(this);
		appendToken(" ");//$NON-NLS-1$
		for (Iterator it = node.fragments().iterator(); it.hasNext(); ) {
			VariableDeclarationFragment f = (VariableDeclarationFragment) it.next();
			f.accept(this);
			if (it.hasNext()) {
				appendToken(", ");//$NON-NLS-1$
			}
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ForStatement)
	 */
	public boolean visit(ForStatement node) {
		printIndent();
		appendToken("for (");//$NON-NLS-1$
		for (Iterator it = node.initializers().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) appendToken(", ");//$NON-NLS-1$
		}
		appendToken("; ");//$NON-NLS-1$
		if (node.getExpression() != null) {
			node.getExpression().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		for (Iterator it = node.updaters().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) appendToken(", ");//$NON-NLS-1$
		}
		appendToken(") ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(IfStatement)
	 */
	public boolean visit(IfStatement node) {
		printIndent();
		appendToken("if (");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(") ");//$NON-NLS-1$
		node.getThenStatement().accept(this);
		if (node.getElseStatement() != null) {
			appendToken(" else ");//$NON-NLS-1$
			node.getElseStatement().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ImportDeclaration)
	 */
	public boolean visit(ImportDeclaration node) {
		printIndent();
		appendToken("import ");//$NON-NLS-1$
		if (node.getAST().apiLevel() >= JLS3) {
			if (node.isStatic()) {
				appendToken("static ");//$NON-NLS-1$
			}
		}
		node.getName().accept(this);
		if (node.isOnDemand()) {
			appendToken(".*");//$NON-NLS-1$
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(InfixExpression)
	 */
	public boolean visit(InfixExpression node) {
		node.getLeftOperand().accept(this);
		appendToken(' ');  // for cases like x= i - -1; or x= i++ + ++i;
		appendToken(node.getOperator().toString());
		appendToken(' ');
		node.getRightOperand().accept(this);
		final List extendedOperands = node.extendedOperands();
		if (extendedOperands.size() != 0) {
			appendToken(' ');
			for (Iterator it = extendedOperands.iterator(); it.hasNext(); ) {
				appendToken(node.getOperator().toString()+" ");
				Expression e = (Expression) it.next();
				e.accept(this);
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(Initializer)
	 */
	public boolean visit(Initializer node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(InstanceofExpression)
	 */
	public boolean visit(InstanceofExpression node) {
		node.getLeftOperand().accept(this);
		appendToken(" instanceof ");//$NON-NLS-1$
		node.getRightOperand().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(IntersectionType)
	 * @since 3.7
	 */
	public boolean visit(IntersectionType node) {
		for (Iterator it = node.types().iterator(); it.hasNext(); ) {
			Type t = (Type) it.next();
			t.accept(this);
			if (it.hasNext()) {
				appendToken(" & "); //$NON-NLS-1$
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(Javadoc)
	 */
	public boolean visit(Javadoc node) {
		printIndent();
		appendToken("/** ");//$NON-NLS-1$
		for (Iterator it = node.tags().iterator(); it.hasNext(); ) {
			ASTNode e = (ASTNode) it.next();
			e.accept(this);
		}
		appendToken("  */ ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(LabeledStatement)
	 */
	public boolean visit(LabeledStatement node) {
		printIndent();
		node.getLabel().accept(this);
		appendToken(": ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(LambdaExpression)
	 */
	public boolean visit(LambdaExpression node) {
		boolean hasParentheses = node.hasParentheses();
		if (hasParentheses)
			appendToken('(');
		for (Iterator it = node.parameters().iterator(); it.hasNext(); ) {
			VariableDeclaration v = (VariableDeclaration) it.next();
			v.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		if (hasParentheses)
			appendToken(')');
		appendToken(" -> "); //$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(LineComment)
	 * @since 3.0
	 */
	public boolean visit(LineComment node) {
		appendToken("// ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MarkerAnnotation)
	 * @since 3.1
	 */
	public boolean visit(MarkerAnnotation node) {
		appendToken("@");//$NON-NLS-1$
		node.getTypeName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MemberRef)
	 * @since 3.0
	 */
	public boolean visit(MemberRef node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
		}
		appendToken("#");//$NON-NLS-1$
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MemberValuePair)
	 * @since 3.1
	 */
	public boolean visit(MemberValuePair node) {
		node.getName().accept(this);
		appendToken("=");//$NON-NLS-1$
		node.getValue().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MethodDeclaration)
	 */
	public boolean visit(MethodDeclaration node) {
		isInMethodVisit=true;
		this.reset();
		if(node.getBody()!=null){
			node.getBody().accept(this);
			isInMethodVisit=false;
			String strMethodTokens=this.buffer.toString().trim();
			String strFileSig=currentFilePath+"\t"+node.getName().toString()+"\t"+getSigParameters(node);
			if(!strMethodTokens.equals("{  }")){
				sbFile.append(strFileSig+"\n");
				sbASTContent.append(strMethodTokens+"\n");
			}
			
			
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MethodInvocation)
	 */
	public boolean visit(MethodInvocation node) {
		if (node.getExpression() != null) {
			node.getExpression().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeArguments().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
		}
		node.getName().accept(this);
		appendToken("(");//$NON-NLS-1$
		for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MethodRef)
	 * @since 3.0
	 */
	public boolean visit(MethodRef node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
		}
		appendToken("#");//$NON-NLS-1$
		node.getName().accept(this);
		appendToken("(");//$NON-NLS-1$
		for (Iterator it = node.parameters().iterator(); it.hasNext(); ) {
			MethodRefParameter e = (MethodRefParameter) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(MethodRefParameter)
	 * @since 3.0
	 */
	public boolean visit(MethodRefParameter node) {
		node.getType().accept(this);
		if (node.getAST().apiLevel() >= JLS3) {
			if (node.isVarargs()) {
				appendToken("...");//$NON-NLS-1$
			}
		}
		if (node.getName() != null) {
			appendToken(" ");//$NON-NLS-1$
			node.getName().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(Modifier)
	 * @since 3.1
	 */
	public boolean visit(Modifier node) {
		appendToken(node.getKeyword().toString());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(NameQualifiedType)
	 * @since 3.10
	 */
	public boolean visit(NameQualifiedType node) {
		node.getQualifier().accept(this);
		appendToken('.');
		visitTypeAnnotations(node);
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(NormalAnnotation)
	 * @since 3.1
	 */
	public boolean visit(NormalAnnotation node) {
		appendToken("@");//$NON-NLS-1$
		node.getTypeName().accept(this);
		appendToken("(");//$NON-NLS-1$
		for (Iterator it = node.values().iterator(); it.hasNext(); ) {
			MemberValuePair p = (MemberValuePair) it.next();
			p.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(NullLiteral)
	 */
	public boolean visit(NullLiteral node) {
		appendToken("null");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(NumberLiteral)
	 */
	public boolean visit(NumberLiteral node) {
		appendToken(node.getToken());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(PackageDeclaration)
	 */
	public boolean visit(PackageDeclaration node) {
		if (node.getAST().apiLevel() >= JLS3) {
			if (node.getJavadoc() != null) {
				node.getJavadoc().accept(this);
			}
			for (Iterator it = node.annotations().iterator(); it.hasNext(); ) {
				Annotation p = (Annotation) it.next();
				p.accept(this);
				appendToken(" ");//$NON-NLS-1$
			}
		}
		printIndent();
		appendToken("package ");//$NON-NLS-1$
		node.getName().accept(this);
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ParameterizedType)
	 * @since 3.1
	 */
	public boolean visit(ParameterizedType node) {
		node.getType().accept(this);
		appendToken("<");//$NON-NLS-1$
		for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
			Type t = (Type) it.next();
			t.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(">");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ParenthesizedExpression)
	 */
	public boolean visit(ParenthesizedExpression node) {
		appendToken("(");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(PostfixExpression)
	 */
	public boolean visit(PostfixExpression node) {
		node.getOperand().accept(this);
		appendToken(node.getOperator().toString());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(PrefixExpression)
	 */
	public boolean visit(PrefixExpression node) {
		appendToken(node.getOperator().toString());
		node.getOperand().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(PrimitiveType)
	 */
	public boolean visit(PrimitiveType node) {
		visitTypeAnnotations(node);
		appendToken(node.getPrimitiveTypeCode().toString());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(QualifiedName)
	 */
	public boolean visit(QualifiedName node) {
		node.getQualifier().accept(this);
		appendToken(".");//$NON-NLS-1$
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(QualifiedType)
	 * @since 3.1
	 */
	public boolean visit(QualifiedType node) {
		node.getQualifier().accept(this);
		appendToken(".");//$NON-NLS-1$
		visitTypeAnnotations(node);
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ReturnStatement)
	 */
	public boolean visit(ReturnStatement node) {
		printIndent();
		appendToken("return");//$NON-NLS-1$
		if (node.getExpression() != null) {
			appendToken(" ");//$NON-NLS-1$
			node.getExpression().accept(this);
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SimpleName)
	 */
	public boolean visit(SimpleName node) {
		appendToken(node.getIdentifier());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SimpleType)
	 */
	public boolean visit(SimpleType node) {
		visitTypeAnnotations(node);
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SingleMemberAnnotation)
	 * @since 3.1
	 */
	public boolean visit(SingleMemberAnnotation node) {
		appendToken("@");//$NON-NLS-1$
		node.getTypeName().accept(this);
		appendToken("(");//$NON-NLS-1$
		node.getValue().accept(this);
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SingleVariableDeclaration)
	 */
	public boolean visit(SingleVariableDeclaration node) {
		printIndent();
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		node.getType().accept(this);
		if (node.getAST().apiLevel() >= JLS3) {
			if (node.isVarargs()) {
				if (node.getAST().apiLevel() >= AST.JLS8) {
					List annotations = node.varargsAnnotations();
					if (annotations.size() > 0) {
						appendToken(' ');
					}
					visitAnnotationsList(annotations);
				}
				appendToken("...");//$NON-NLS-1$
			}
		}
		appendToken(" ");//$NON-NLS-1$
		node.getName().accept(this);
		int size = node.getExtraDimensions();
		if (node.getAST().apiLevel() >= AST.JLS8) {
			List dimensions = node.extraDimensions();
			for (int i = 0; i < size; i++) {
				visit((Dimension) dimensions.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				appendToken("[]"); //$NON-NLS-1$
			}
		}
		if (node.getInitializer() != null) {
			appendToken("=");//$NON-NLS-1$
			node.getInitializer().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(StringLiteral)
	 */
	public boolean visit(StringLiteral node) {
		appendToken(node.getEscapedValue());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SuperConstructorInvocation)
	 */
	public boolean visit(SuperConstructorInvocation node) {
		printIndent();
		if (node.getExpression() != null) {
			node.getExpression().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeArguments().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
		}
		appendToken("super(");//$NON-NLS-1$
		for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken("); ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SuperFieldAccess)
	 */
	public boolean visit(SuperFieldAccess node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		appendToken("super.");//$NON-NLS-1$
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SuperMethodInvocation)
	 */
	public boolean visit(SuperMethodInvocation node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		appendToken("super.");//$NON-NLS-1$
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeArguments().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeArguments().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
		}
		node.getName().accept(this);
		appendToken("(");//$NON-NLS-1$
		for (Iterator it = node.arguments().iterator(); it.hasNext(); ) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				appendToken(",");//$NON-NLS-1$
			}
		}
		appendToken(")");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SuperMethodReference)
	 *
	 * @since 3.10
	 */
	public boolean visit(SuperMethodReference node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
			appendToken('.');
		}
		appendToken("super");//$NON-NLS-1$
		visitReferenceTypeArguments(node.typeArguments());
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SwitchCase)
	 */
	public boolean visit(SwitchCase node) {
		if (node.isDefault()) {
			appendToken("default : ");//$NON-NLS-1$
		} else {
			appendToken("case ");//$NON-NLS-1$
			node.getExpression().accept(this);
			appendToken(": ");//$NON-NLS-1$
		}
		this.indent++; //decremented in visit(SwitchStatement)
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SwitchStatement)
	 */
	public boolean visit(SwitchStatement node) {
		appendToken("switch (");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(") ");//$NON-NLS-1$
		appendToken("{ ");//$NON-NLS-1$
		this.indent++;
		for (Iterator it = node.statements().iterator(); it.hasNext(); ) {
			Statement s = (Statement) it.next();
			s.accept(this);
			this.indent--; // incremented in visit(SwitchCase)
		}
		this.indent--;
		printIndent();
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SynchronizedStatement)
	 */
	public boolean visit(SynchronizedStatement node) {
		appendToken("synchronized (");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(") ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TagElement)
	 * @since 3.0
	 */
	public boolean visit(TagElement node) {
		if (node.isNested()) {
			// nested tags are always enclosed in braces
			appendToken("{");//$NON-NLS-1$
		} else {
			// top-level tags always begin on a new line
			appendToken("  * ");//$NON-NLS-1$
		}
		boolean previousRequiresWhiteSpace = false;
		if (node.getTagName() != null) {
			appendToken(node.getTagName());
			previousRequiresWhiteSpace = true;
		}
		boolean previousRequiresNewLine = false;
		for (Iterator it = node.fragments().iterator(); it.hasNext(); ) {
			ASTNode e = (ASTNode) it.next();
			// Name, MemberRef, MethodRef, and nested TagElement do not include white space.
			// TextElements don't always include whitespace, see <https://bugs.eclipse.org/206518>.
			boolean currentIncludesWhiteSpace = false;
			if (e instanceof TextElement) {
				String text = ((TextElement) e).getText();
				if (text.length() > 0 && ScannerHelper.isWhitespace(text.charAt(0))) {
					currentIncludesWhiteSpace = true; // workaround for https://bugs.eclipse.org/403735
				}
			}
			if (previousRequiresNewLine && currentIncludesWhiteSpace) {
				appendToken("  * ");//$NON-NLS-1$
			}
			previousRequiresNewLine = currentIncludesWhiteSpace;
			// add space if required to separate
			if (previousRequiresWhiteSpace && !currentIncludesWhiteSpace) {
				appendToken(" "); //$NON-NLS-1$
			}
			e.accept(this);
			previousRequiresWhiteSpace = !currentIncludesWhiteSpace && !(e instanceof TagElement);
		}
		if (node.isNested()) {
			appendToken("}");//$NON-NLS-1$
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TextElement)
	 * @since 3.0
	 */
	public boolean visit(TextElement node) {
		appendToken(node.getText());
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ThisExpression)
	 */
	public boolean visit(ThisExpression node) {
		if (node.getQualifier() != null) {
			node.getQualifier().accept(this);
			appendToken(".");//$NON-NLS-1$
		}
		appendToken("this");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(ThrowStatement)
	 */
	public boolean visit(ThrowStatement node) {
		printIndent();
		appendToken("throw ");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TryStatement)
	 */
	public boolean visit(TryStatement node) {
		printIndent();
		appendToken("try ");//$NON-NLS-1$
		if (node.getAST().apiLevel() >= JLS4) {
			List resources = node.resources();
			if (!resources.isEmpty()) {
				appendToken('(');
				for (Iterator it = resources.iterator(); it.hasNext(); ) {
					VariableDeclarationExpression variable = (VariableDeclarationExpression) it.next();
					variable.accept(this);
					if (it.hasNext()) {
						appendToken(';');
					}
				}
				appendToken(')');
			}
		}
		node.getBody().accept(this);
		appendToken(" ");//$NON-NLS-1$
		for (Iterator it = node.catchClauses().iterator(); it.hasNext(); ) {
			CatchClause cc = (CatchClause) it.next();
			cc.accept(this);
		}
		if (node.getFinally() != null) {
			appendToken(" finally ");//$NON-NLS-1$
			node.getFinally().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TypeDeclaration)
	 */
	public boolean visit(TypeDeclaration node) {
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		appendToken(node.isInterface() ? "interface " : "class ");//$NON-NLS-2$//$NON-NLS-1$
		node.getName().accept(this);
		if (node.getAST().apiLevel() >= JLS3) {
			if (!node.typeParameters().isEmpty()) {
				appendToken("<");//$NON-NLS-1$
				for (Iterator it = node.typeParameters().iterator(); it.hasNext(); ) {
					TypeParameter t = (TypeParameter) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(",");//$NON-NLS-1$
					}
				}
				appendToken(">");//$NON-NLS-1$
			}
		}
		appendToken(" ");//$NON-NLS-1$
		if (node.getAST().apiLevel() == JLS2) {
			if (getSuperclass(node) != null) {
				appendToken("extends ");//$NON-NLS-1$
				getSuperclass(node).accept(this);
				appendToken(" ");//$NON-NLS-1$
			}
			if (!superInterfaces(node).isEmpty()) {
				appendToken(node.isInterface() ? "extends " : "implements ");//$NON-NLS-2$//$NON-NLS-1$
				for (Iterator it = superInterfaces(node).iterator(); it.hasNext(); ) {
					Name n = (Name) it.next();
					n.accept(this);
					if (it.hasNext()) {
						appendToken(", ");//$NON-NLS-1$
					}
				}
				appendToken(" ");//$NON-NLS-1$
			}
		}
		if (node.getAST().apiLevel() >= JLS3) {
			if (node.getSuperclassType() != null) {
				appendToken("extends ");//$NON-NLS-1$
				node.getSuperclassType().accept(this);
				appendToken(" ");//$NON-NLS-1$
			}
			if (!node.superInterfaceTypes().isEmpty()) {
				appendToken(node.isInterface() ? "extends " : "implements ");//$NON-NLS-2$//$NON-NLS-1$
				for (Iterator it = node.superInterfaceTypes().iterator(); it.hasNext(); ) {
					Type t = (Type) it.next();
					t.accept(this);
					if (it.hasNext()) {
						appendToken(", ");//$NON-NLS-1$
					}
				}
				appendToken(" ");//$NON-NLS-1$
			}
		}
		appendToken("{ ");//$NON-NLS-1$
		this.indent++;
		for (Iterator it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
			BodyDeclaration d = (BodyDeclaration) it.next();
			d.accept(this);
		}
		this.indent--;
		printIndent();
		appendToken("} ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TypeDeclarationStatement)
	 */
	public boolean visit(TypeDeclarationStatement node) {
		if (node.getAST().apiLevel() == JLS2) {
			getTypeDeclaration(node).accept(this);
		}
		if (node.getAST().apiLevel() >= JLS3) {
			node.getDeclaration().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TypeLiteral)
	 */
	public boolean visit(TypeLiteral node) {
		node.getType().accept(this);
		appendToken(".class");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TypeMethodReference)
	 *
	 * @since 3.10
	 */
	public boolean visit(TypeMethodReference node) {
		node.getType().accept(this);
		visitReferenceTypeArguments(node.typeArguments());
		node.getName().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(TypeParameter)
	 * @since 3.1
	 */
	public boolean visit(TypeParameter node) {
		if (node.getAST().apiLevel() >= AST.JLS8) {
			printModifiers(node.modifiers());
		}
		node.getName().accept(this);
		if (!node.typeBounds().isEmpty()) {
			appendToken(" extends ");//$NON-NLS-1$
			for (Iterator it = node.typeBounds().iterator(); it.hasNext(); ) {
				Type t = (Type) it.next();
				t.accept(this);
				if (it.hasNext()) {
					appendToken(" & ");//$NON-NLS-1$
				}
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(UnionType)
	 * @since 3.7
	 */
	public boolean visit(UnionType node) {
		for (Iterator it = node.types().iterator(); it.hasNext(); ) {
			Type t = (Type) it.next();
			t.accept(this);
			if (it.hasNext()) {
				appendToken('|');
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(VariableDeclarationExpression)
	 */
	public boolean visit(VariableDeclarationExpression node) {
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		node.getType().accept(this);
		appendToken(" ");//$NON-NLS-1$
		for (Iterator it = node.fragments().iterator(); it.hasNext(); ) {
			VariableDeclarationFragment f = (VariableDeclarationFragment) it.next();
			f.accept(this);
			if (it.hasNext()) {
				appendToken(", ");//$NON-NLS-1$
			}
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(VariableDeclarationFragment)
	 */
	public boolean visit(VariableDeclarationFragment node) {
		node.getName().accept(this);
		int size = node.getExtraDimensions();
		if (node.getAST().apiLevel() >= AST.JLS8) {
			List dimensions = node.extraDimensions();
			for (int i = 0; i < size; i++) {
				visit((Dimension) dimensions.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				appendToken("[]");//$NON-NLS-1$
			}
		}
		if (node.getInitializer() != null) {
			appendToken("=");//$NON-NLS-1$
			node.getInitializer().accept(this);
		}
		return false;
	}

	/*
	 * @see ASTVisitor#visit(VariableDeclarationStatement)
	 */
	public boolean visit(VariableDeclarationStatement node) {
		printIndent();
		if (node.getAST().apiLevel() == JLS2) {
			printModifiers(node.getModifiers());
		}
		if (node.getAST().apiLevel() >= JLS3) {
			printModifiers(node.modifiers());
		}
		node.getType().accept(this);
		appendToken(" ");//$NON-NLS-1$
		for (Iterator it = node.fragments().iterator(); it.hasNext(); ) {
			VariableDeclarationFragment f = (VariableDeclarationFragment) it.next();
			f.accept(this);
			if (it.hasNext()) {
				appendToken(", ");//$NON-NLS-1$
			}
		}
		appendToken("; ");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(WhileStatement)
	 */
	public boolean visit(WhileStatement node) {
		printIndent();
		appendToken("while (");//$NON-NLS-1$
		node.getExpression().accept(this);
		appendToken(") ");//$NON-NLS-1$
		node.getBody().accept(this);
		return false;
	}

	/*
	 * @see ASTVisitor#visit(WildcardType)
	 * @since 3.1
	 */
	public boolean visit(WildcardType node) {
		visitTypeAnnotations(node);
		appendToken("?");//$NON-NLS-1$
		Type bound = node.getBound();
		if (bound != null) {
			if (node.isUpperBound()) {
				appendToken(" extends ");//$NON-NLS-1$
			} else {
				appendToken(" super ");//$NON-NLS-1$
			}
			bound.accept(this);
		}
		return false;
	}

	/**
	 * @deprecated
	 */
	private void visitComponentType(ArrayType node) {
		node.getComponentType().accept(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}
