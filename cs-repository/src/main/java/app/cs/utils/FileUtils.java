package app.cs.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

/**
 * The Class FileUtils.
 */
@Component
public class FileUtils {

	/**
	 * Gets the file contents.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the file contents
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public String getFileContents(String relativePath) throws IOException,
			URISyntaxException {

		byte[] encoded = Files.readAllBytes(Paths.get(getClass()
				.getClassLoader().getResource(relativePath).toURI()));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}

	public String getFileContentsFromGivenPath(URI realPath) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(realPath));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}
}
