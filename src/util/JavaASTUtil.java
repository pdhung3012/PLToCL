package util;

import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Expression;

public class JavaASTUtil {

	@SuppressWarnings("rawtypes")
	public static ASTNode parseSource(String source, int kind) {
		Map<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setCompilerOptions(options);
		parser.setSource(source.toCharArray());
		parser.setKind(kind);
		parser.setResolveBindings(false);
		parser.setBindingsRecovery(false);
		ASTNode ast = parser.createAST(null);
		return ast;
	}

	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
