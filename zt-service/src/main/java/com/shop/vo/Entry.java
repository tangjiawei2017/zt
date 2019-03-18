package com.shop.vo;

import java.util.List;

public class Entry {
	private Integer index;
	private String key;
	private List<NamePairValue> value;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<NamePairValue> getValue() {
		return value;
	}

	public void setValue(List<NamePairValue> value) {
		this.value = value;
	}

	public static class NamePairValue {
		private String key;
		private String value;

		public NamePairValue(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
