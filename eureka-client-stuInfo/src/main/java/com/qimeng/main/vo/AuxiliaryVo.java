package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月20日 上午9:23:59 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class AuxiliaryVo {
	private Integer id;

	private Byte type;

    private Byte status;

    private String mark;
    
    private String uint;

    private Integer factor;
    
    private String position;

    private Byte weight;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getUint() {
		return uint;
	}

	public void setUint(String uint) {
		this.uint = uint;
	}

	public Integer getFactor() {
		return factor;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Byte getWeight() {
		return weight;
	}

	public void setWeight(Byte weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "AuxiliaryVo [id=" + id + ", type=" + type + ", status=" + status + ", mark=" + mark + ", uint=" + uint
				+ ", factor=" + factor + ", position=" + position + ", weight=" + weight + "]";
	}


    
}

