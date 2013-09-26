package app.cs.impl.dimension;

import org.junit.Test;

import junit.framework.Assert;

public class ImageLookupUnitTests {

	private ImageLookup imageLookup;

	@Test
	public void itShouldReturnImageUrlForGivenDimensionIdIfExists() {
		// when
		imageLookup = new ImageLookup();
		String id = "pub01";
		String image = imageLookup.get(id);
		// then
		Assert.assertEquals(
				"http://cdn.arstechnica.net/wp-content/uploads/2012/10/01_Place_Peters-640x450.jpg",
				image);

	}

	@Test
	public void itShouldReturnNullIfUrlDoesnotExist() {
		// when
		imageLookup = new ImageLookup();
		String id = "pub";
		String image = imageLookup.get(id);
		// then
		Assert.assertEquals(null, image);

	}
}
