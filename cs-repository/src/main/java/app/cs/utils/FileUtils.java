package app.cs.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {

	public String getFileContents(String relativePath) throws IOException,
			URISyntaxException {
		byte[] encoded = Files.readAllBytes(Paths.get(getClass()
				.getClassLoader().getResource(relativePath).toURI()));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}

	public String getFileContents(URI realPath) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(realPath));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}
}
