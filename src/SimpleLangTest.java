import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SimpleLangTest {

    @Test
    public void testRunLLVMCodeWithLLI() throws IOException, InterruptedException {
        SimpleLang.runLLVMCode();
    }

}
