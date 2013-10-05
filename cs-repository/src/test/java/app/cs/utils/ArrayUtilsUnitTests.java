package app.cs.utils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilsUnitTests {
	private ArrayUtils utils;

	@Before
	public void setUp() {
		utils = new ArrayUtils();
	}

	@Test
	public void itShouldSkipElementsStillGivenIndex() {
		// given
		String[] rules = { "Campaign", "MasterPublication", "PublicationGroup",
				"Publication" };
		// when
		String []updatedrules=utils.skip(rules, 1);
		System.out.println(updatedrules[0]);
		//then
		Assert.assertEquals(updatedrules.length, 3);

	}

}
