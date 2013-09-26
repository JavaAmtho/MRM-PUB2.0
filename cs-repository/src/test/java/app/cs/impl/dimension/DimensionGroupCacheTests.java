package app.cs.impl.dimension;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IInMemoryDimensionGroup;

import com.cs.data.core.nosql.redis.RedisRepository;

@RunWith(MockitoJUnitRunner.class)
public class DimensionGroupCacheTests {

	private MultiDimensionalObject dimensionModel;
	private IInMemoryDimensionGroup groupCache;

	@Mock
	private RedisRepository redisRepository;

	@Before
	public void setUp() {

		groupCache = new InMemoryDimensionGroup(redisRepository);
		dimensionModel = new MultiDimensionalObject("test01", "campaign", "co01", "cp01","-1");
	}

	@Test
	public void itShouldGetDimensionGroupIdForGivenDimension() {

		// given

		String expectedId = "group01";
		String path = "testPath";
		// when

		when(redisRepository.get(path)).thenReturn(expectedId);
		String actualGroupId = groupCache.getDimensionGroupIdFor(path);
		// then
		verify(redisRepository).get(path);
		assertThat(actualGroupId).isEqualTo(expectedId);
	}

	@Test
	public void itShouldUpdateDimensionGroupIdForGivenDimension() {
		// given
		String key = "key";
		String value = "value";
		String groupId = "abc@123";
		// when
		groupCache.updateCache(dimensionModel, groupId);

		// then
		verify(redisRepository).delete(dimensionModel.getPath());
		verify(redisRepository)
				.set(dimensionModel.getPath().concat(
						"," + dimensionModel.getName()), groupId);
	}

	@Test
	public void itShouldSetDimensionGroupIdForGivenDimension() {
		// given
		String key = "key";
		String value = "value";
		String exptectedResult = "test";
		String groupId = "abc@123";
		// when
		groupCache.addNewGroup(dimensionModel, groupId);

		// then
		verify(redisRepository).set(dimensionModel.getName(), groupId);

	}

	@Test
	public void itShouldReturnFalseIfGroupIdIsAbsentInCache() {
		// given
		String path = "testKey";

		// when
		when(redisRepository.get(path)).thenReturn(null);
		boolean flag = groupCache.ifGroupIdExistsFor(path);

		// then
		verify(redisRepository).get(path);
		assertThat(flag).isEqualTo(false);
	}
}
