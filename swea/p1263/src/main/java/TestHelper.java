import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class TestHelper {
    private static final String INPUT_FILE = "res/input.txt";
    private static final String OUTPUT_FILE = "res/output.txt";
    private static final String ANSI_RESET = "[0m";
    private static final String ANSI_RED = "[31m";
    private static final String ANSI_GREEN = "[32m";
    private static final String ANSI_CYAN = "[36m";

    public static void main(String[] args) throws Exception {
        String actual = runWithSampleInput();
        String expected = loadExpectedOutput();

        if (expected.isEmpty()) {
            System.out.println(ANSI_CYAN + "[안내] res/output.txt 파일이 없어 결과 비교를 생략합니다." + ANSI_RESET);
            System.out.println("[실제 출력]");
            System.out.println(actual);
            return;
        }

        boolean pass = actual.equals(expected);

        if (pass) {
            System.out.println("===============");
            System.out.println("테스트 완료");
            System.out.println(ANSI_GREEN + "주어진 케이스에 대해 잘 동작하고 있습니다." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "====== 출력이 기대값과 다릅니다 ======" + ANSI_RESET);
            System.out.println("[기대 값]");
            System.out.println(expected);
            System.out.println();
            System.out.println("[실제 값]");
            System.out.println(actual);
        }
    }

    private static String runWithSampleInput() throws Exception {
        InputStream originIn = System.in;
        PrintStream originOut = System.out;

        try {
            InputStream testIn = new FileInputStream(INPUT_FILE);
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            PrintStream testPrint = new PrintStream(testOut, true, StandardCharsets.UTF_8);

            System.setIn(testIn);
            System.setOut(testPrint);

            Solution.main(new String[0]);

            return normalize(testOut.toString(StandardCharsets.UTF_8));
        } finally {
            System.setIn(originIn);
            System.setOut(originOut);
        }
    }

    private static String loadExpectedOutput() throws IOException {
        Path outputPath = Path.of(OUTPUT_FILE);
        if (!Files.exists(outputPath)) {
            return "";
        }
        return normalizeNewlines(Files.readString(outputPath, StandardCharsets.UTF_8)).trim();
    }

    private static String normalize(String value) {
        return normalizeNewlines(value).trim();
    }

    private static String normalizeNewlines(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\r\n", "\n").replace("\r", "\n");
    }
}
