package ru.bmn.web.hsdb.model.entity.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DownloadCache {
	@Id
	@GeneratedValue
	private Integer id;

	private String url;
	private Date updated;
	private String tag;
	private byte[] data;

	public Integer getId() {
		return id;
	}

	public DownloadCache setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public DownloadCache setUrl(String url) {
		this.url = url;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public DownloadCache setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	public String getTag() {
		return tag;
	}

	public DownloadCache setTag(String tag) {
		this.tag = tag;
		return this;
	}

	public byte[] getData() {
		return data;
	}

	public DownloadCache setData(byte[] data) {
		this.data = data;
		return this;
	}
}
