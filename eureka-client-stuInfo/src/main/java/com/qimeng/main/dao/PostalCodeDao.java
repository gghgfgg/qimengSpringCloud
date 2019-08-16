package com.qimeng.main.dao;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月9日 下午3:36:14 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qimeng.main.entity.City;
import com.qimeng.main.entity.Province;
import com.qimeng.main.entity.Region;

@Mapper
public interface PostalCodeDao {

	@Select("select city_id from city where province_id=#{postalCode}")
	List<Integer> selectCityByPostalCode(Integer postalCode);
	
	
	@Select("select region_id from region where city_id=#{postalCode}")
	List<Integer> selectCityByRegion(Integer postalCode);
	
	@Select("select region_id,region_name, city_id from region where region_id=#{postalCode}")
	Region selectRegion(Integer postalCode);
	
	@Select("select city_id,city_name,province_id,first_letter,is_hot,state from city where city_id=#{postalCode}")
	City selectCity(Integer postalCode);
	
	@Select("select province_id,province_name from province where province_id=#{postalCode}")
	Province selectProvince(Integer postalCode);
	
}

