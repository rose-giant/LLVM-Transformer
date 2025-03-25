import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SimpleLangTest {

    @Test
    public void testRunLLVMCodeWithLLI() throws IOException, InterruptedException {
        String expectedOutput = "22";

        // Ensure the LLVM IR file exists
        File irFile = new File("main.ll");
        assertTrue(irFile.exists());

        // Run the LLVM IR directly using lli
        Process runProcess = Runtime.getRuntime().exec("lli main2.ll", null, irFile.getParentFile());

        // Get the output
        String actualOutput = getProcessOutput(runProcess);

        // Assert the output matches expectations
        assertEquals(expectedOutput, actualOutput.trim(), "Program output mismatch!");
    }

    private static String getProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }
}
