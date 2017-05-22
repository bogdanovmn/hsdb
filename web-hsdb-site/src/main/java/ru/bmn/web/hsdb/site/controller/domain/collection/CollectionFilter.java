package ru.bmn.web.hsdb.site.controller.domain.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionFilter {
	public static final String CHARACTER_ID = "characterId";
	public static final String SERIES_ID    = "seriesId";
	public static final String RARITY_ID    = "rarityId";

	private final Map<String, Integer> params = new HashMap<>();

	public CollectionFilter(Integer characterId, Integer rarityId, Integer seriesId) {
		this.params.put(CHARACTER_ID, characterId == null ? 1 : characterId);
		if (rarityId != null) this.params.put(RARITY_ID, rarityId);
		if (seriesId != null) this.params.put(SERIES_ID, seriesId);
	}

	public Integer getSeriesId() {
		return this.params.get(SERIES_ID);
	}

	public Integer getRarityId() {
		return this.params.get(RARITY_ID);
	}

	public Integer getCharacterId() {
		return this.params.get(CHARACTER_ID);
	}

	public String getParamsWOSpecified(String excludeParam) {
		return this.params.entrySet().stream()
			.filter(
				x -> !x.getKey().equals(excludeParam)
			)
			.map(
				x -> x.getValue() > 0
					? String.format("%s=%d", x.getKey(), x.getValue())
					: ""
			)
			.collect(
				Collectors.joining("&")
			);
	}
}
