package com.qimeng.main.dao;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fasterxml.jackson.core.sym.Name;
import com.qimeng.main.vo.CommodityEntity;
import com.qimeng.main.vo.NewCommodityEntity;
import com.qimeng.main.vo.Student;
@Mapper
public interface OldServiceDao {
	
	@Select("select bimg from qmhb_adv")
    public List<String> getPic();
	
	@Select("select nid as id,pname as mc,jifen as jf,bimg as pic from qmhb_products where dpid=#{jqbh} or dpid=0 and ck=0")
	public List<CommodityEntity> getProut(String jqbh);
	
	@Select("select nid as id,pname as mc,jifen as jf,bimg as pic,spower as priority from qmhb_products where dpid=#{jqbh} or dpid=0 and ck=0")
	public List<NewCommodityEntity> getNewProut(String jqbh);
	
	@Select("select nid as id,realname as name,qrid as code,njf as points,rootid as bind from qmhb_student where qrid=#{code}")
	public Student getStudent(String code);
	
	@Update("update qmhb_student set njf=#{points} where qrid=#{code}")
	public int updatePoints(@Param("code")String code,@Param("points")int points);
	
	@Select("select nid as id,realname as name,qrid as code,njf as points,rootid as bind from qmhb_student where nid=#{id}")
	public Student getStudentByID(int id);
	
	@Select("select nid as id,pname as mc,jifen as jf,bimg as pic from qmhb_products where nid=#{id}")
	public CommodityEntity getProutBy(int id);
	
	@Select("select dpname from qmhb_dpj where dpcode=#{code}")
	public String getNameByCode(String code);
	
	@Insert("insert into qmhb_trade (stuid,realname,proid,proname,jifen,dpjid,dpjname,adddate,ddh)"
			       + "value(#{studid},#{name},#{proid},#{proname},#{jifen},#{dpjid},#{dpjname},#{adddate},#{ddh})")
	public int insertPointsLog(@Param("studid")int studid,@Param("name")String name,@Param("proid")int proid,
			                  @Param("proname")String proname,@Param("jifen")int jifen,@Param("dpjid")String dpjid,
			                  @Param("dpjname")String dpjname,@Param("adddate")Date adddate,@Param("ddh")String ddh);
	@Update("update qmhb_trade set hx=1 where ddh=#{orderno}")
	public int updataorder(String orderno);
		
}
