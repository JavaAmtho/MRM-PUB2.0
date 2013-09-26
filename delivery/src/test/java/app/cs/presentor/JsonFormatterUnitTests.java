package app.cs.presentor;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class JsonFormatterUnitTests {

	private JsonFormatter formatter;

	@Before
	public void setUp() {
		formatter = new JsonFormatter();

	}

	@Test
	public void itShouldFormatStringToDesiredJsonFormat() throws ParseException {
		// given

		String products = "{\"62\":{\"id\":\"62\",\"label\":\"Media Player\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=a8a1ba62@CSLive&class=Pdmarticle&rand=9e280543f231782ff0ceda6ad83bc0b2&width=32&height=48\",\"isfolder\":true,\"description\":\"89,00, Blue\",\"service\":\"pim\"},\"91\":{\"id\":\"91\",\"label\":\"Books\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=f85e6a91@CSLive&class=Pdmarticle&rand=8ec636c4d5f14e6b6fa73eb0952bbc6f&width=32&height=48\",\"isfolder\":true,\"description\":\"24,80, white\",\"service\":\"pim\"},\"63\":{\"id\":\"63\",\"label\":\"Accessories\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=6198aa63@CSLive&class=Pdmarticle&rand=8ec636c4d5f14e6b6fa73eb0952bbc6f&width=32&height=48\",\"isfolder\":true,\"service\":\"pim\"}}";
		// when

		String formattedString = formatter.format(products);
		// then
		assertThat(formattedString).contains("id");
	}

}
