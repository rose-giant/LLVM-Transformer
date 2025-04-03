import main.ast.nodes.Program;
import main.visitor.CodeGenerator;
import main.visitor.NameAnalyzer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.SimpleLangLexer;
import parsers.SimpleLangParser;
import java.io.*;

public class SimpleLang {
    public static void main(String[] args) throws IOException {
        CharStream reader = CharStreams.fromFileName("cgSamples/sample.sl");
        SimpleLangLexer simpleLangLexer = new SimpleLangLexer(reader);
        CommonTokenStream tokens = new CommonTokenStream(simpleLangLexer);
        SimpleLangParser flParser = new SimpleLangParser(tokens);
        Program program = flParser.program().programRet;
        NameAnalyzer nameAnalyzer = new NameAnalyzer();
        nameAnalyzer.visit(program);

        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.visit(program);

        runLLVMCode();
    }

    public static void runLLVMCode() {
        //test#2
        ProcessBuilder pb = new ProcessBuilder("lli", "./codeGenOutput/main.ll");
        pb.inheritIO();
        try {
            Process p = pb.start();
            int exitStatus = p.waitFor();
            System.out.println(exitStatus);
        }
        catch (InterruptedException | IOException x) {
            x.printStackTrace();
        }
    }
}