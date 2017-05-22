package ru.bmn.web.hsdb.model.entity.common;

import org.junit.Test;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.entity.hs.Artist;
import ru.bmn.web.hsdb.model.entity.hs.CharacterClass;

import static org.junit.Assert.assertEquals;

public class EntityWithUniqueNameTest {
	@Test
	public void setNameNormalize_1() throws Exception {
		Artist artist = new Artist();
		artist.setName("Sean O’Daniels");

		assertEquals(
			"Sean O'Daniels",
			artist.getName()
		);

		artist.setName("Sean ODaniels");
		assertEquals(
			"Sean ODaniels",
			artist.getName()
		);
	}

	@Test
	public void setNameNormalize_2() throws Exception {
		Artist artist = new Artist();
		artist.setName("E.M. Gist");
		assertEquals(
			"E. M. Gist",
			artist.getName()
		);
	}

	@Test
	public void setNameNormalize_3() throws Exception {
		Artist artist = new Artist();
		artist.setName("  Some 		 Name ");
		assertEquals(
			"Some Name",
			artist.getName()
		);
	}

	@Test
	public void setNameNormalize_4() throws Exception {
		CharacterClass characterClass = new CharacterClass();
		characterClass.setName(" Warlock");
		assertEquals(
			"Warlock",
			characterClass.getName()
		);
	}

	@Test
	public void setNameNormalize_5() throws Exception {
		User user = new User();
		user.setName("Имя");
		assertEquals(
			"Имя",
			user.getName()
		);
	}

}