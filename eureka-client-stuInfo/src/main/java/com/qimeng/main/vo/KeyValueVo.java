package com.qimeng.main.vo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年9月5日 下午4:10:32
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class KeyValueVo {
	Integer id;
	String strKey;
	String strValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	@Override
	public String toString() {
		return "KeyValueVo [id=" + id + ", strKey=" + strKey + ", strValue=" + strValue + "]";
	}

}
