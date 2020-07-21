import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

public class DateWriter {
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length < 1)
			throw new RuntimeException("Missing output file parameter");
		String fileOutputPath = args[0];
		try(PrintWriter writer = new PrintWriter(fileOutputPath)) {
			while(true) {
				writer.println(Instant.now().toString());
				writer.flush();
				Thread.sleep(1000);
			}
		}
	}
}