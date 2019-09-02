package com.qimeng.main.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年9月1日 下午2:49:37
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class RecycleLogVo {
	private Integer count;
	private Integer points;
	private Integer remainder;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") // 页面写入数据库时格式化
	@JSONField(format = "yyyy-MM-dd HH-mm-ss") // 数据库导出页面时json格式化
	private Date createTime;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getRemainder() {
		return remainder;
	}
	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "RecycleLogVo [count=" + count + ", points=" + points + ", remainder=" + remainder + ", createTime="
				+ createTime + "]";
	}

}
