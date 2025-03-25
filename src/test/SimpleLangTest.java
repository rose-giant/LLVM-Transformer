//package test;
//
//import java.io.File;
//import java.io.IOException;
//import org.junit.Test;
//import org.junit.jupiter.api.Test;
//
//import static jdk.jpackage.internal.IOUtils.getProcessOutput;
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;
//public class SimpleLangTest {
//
//    @Test
//    public void testRunLLVMCode() {
//        // Run LLVM code
//        runLLVMCode();
//
//        File dir = new File("./codeGenOutput");
//        File llFile = new File(dir, "main2.ll");
//        File objFile = new File(dir, "main.o");
//        File exeFile = new File(dir, "main");
//
//        // Check if generated files exist
//        assertTrue("LLVM IR file (main2.ll) should exist.", llFile.exists());
//        assertTrue("Object file (main.o) should exist.", objFile.exists());
//        assertTrue("Executable file (main) should exist.", exeFile.exists());
//
//        // Run the generated executable and capture output
//        try {
//            Process process = Runtime.getRuntime().exec("./main", null, dir);
//            String output = getProcessOutput(process);
//
//            assertNotNull(output, "Program output should not be null.");
//            assertFalse("Program should produce some output.", output.isEmpty());
//            assertTrue("Program output should match expectations.", output.contains("expected output"));
//        } catch (IOException e) {
//            fail("Failed to execute compiled LLVM code: " + e.getMessage());
//        }
//    }
//
//    // Helper method to read process output
////    private String getProcessOutput(Process process) throws IOException {
////        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////        StringBuilder output = new StringBuilder();
////        String line;
////        while ((line = reader.readLine()) != null) {
////            output.append(line).append("\n");
////        }
////        return output.toString().trim();
////    }
//}
