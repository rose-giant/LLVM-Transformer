import main.ast.nodes.Program;
import main.visitor.CodeGenerator;
import main.visitor.NameAnalyzer;
import main.visitor.TypeChecker;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.SimpleLangLexer;
import parsers.SimpleLangParser;

import java.io.*;

public class SimpleLang {
    public static void main(String[] args) throws IOException {
//        CharStream reader = CharStreams.fromFileName("cgSamples/sample.sl");
//        SimpleLangLexer simpleLangLexer = new SimpleLangLexer(reader);
//        CommonTokenStream tokens = new CommonTokenStream(simpleLangLexer);
//        SimpleLangParser flParser = new SimpleLangParser(tokens);
//        Program program = flParser.program().programRet;
//
//        NameAnalyzer nameAnalyzer = new NameAnalyzer();
//        nameAnalyzer.visit(program);
//
//        TypeChecker typeChecker = new TypeChecker();
//        typeChecker.visit(program);
//
//        CodeGenerator codeGenerator = new CodeGenerator();
//        codeGenerator.visit(program);

        runLLVMCode();
    }

    private static void runLLVMCode() {
        //test#2
        ProcessBuilder pb = new ProcessBuilder("lli", "./codeGenOutput/main3.ll");
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

    private static void runJasminCode() {
        try {
            System.out.println("---------------------------Compilation Successful---------------------------");
            File dir = new File("./codeGenOutput");
            Process process = Runtime.getRuntime().exec("java -jar jasmin.jar -d . Main.j", null, dir);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            process = Runtime.getRuntime().exec("java Main", null, dir);
            printResults(process.getInputStream());
            printResults(process.getErrorStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void printResults(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}