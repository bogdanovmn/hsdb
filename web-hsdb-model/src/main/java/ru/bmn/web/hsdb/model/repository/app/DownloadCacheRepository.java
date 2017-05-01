package ru.bmn.web.hsdb.model.repository.app;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.app.DownloadCache;

public interface DownloadCacheRepository extends CrudRepository<DownloadCache, Integer> {
}
