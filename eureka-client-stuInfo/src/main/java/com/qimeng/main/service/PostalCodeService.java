package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qimeng.main.dao.PostalCodeDao;
import com.qimeng.main.entity.City;
import com.qimeng.main.entity.Province;
import com.qimeng.main.entity.Region;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月9日 下午3:44:39 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class PostalCodeService {

	private static Logger logger = Logger.getLogger(PostalCodeService.class);
	@Autowired
	PostalCodeDao postalCodeDao;
	
    public List<Integer> selectCityByPostalCode(Integer postalCode){
    	try {
    		return postalCodeDao.selectCityByPostalCode(postalCode);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置查询城市邮编");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
    }
	
	public List<Integer> selectCityByRegion(Integer postalCode){
		try {
			return postalCodeDao.selectCityByRegion(postalCode);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置查询城市区邮编");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
		
	}
	@Cacheable(value="PostalCode",key="#postalCode")
	public String selectPostalCode(String postalCode) {
		String postalCodeString=new String();			
		List<Integer> list=selectCityByPostalCode(Integer.parseInt(postalCode));
		if(!list.isEmpty()) {
			for (Integer integer : list) {
				postalCodeString += integer.toString();
				postalCodeString += "-";
				List<Integer> listregion = selectCityByRegion(integer);
				for (Integer integer2 : listregion) {
					postalCodeString += integer2.toString();
					postalCodeString += "-";
				}
			}
		}
		else {
			List<Integer> listregion = selectCityByRegion(Integer.parseInt(postalCode));
			for (Integer integer2 : listregion) {
				postalCodeString += integer2.toString();
				postalCodeString += "-";
			}
		}
		logger.debug(postalCodeString);
		if(StringUtils.isEmpty(postalCodeString))
		{
			return postalCode;
		}
		return postalCodeString;
	}
	@Cacheable(value="PostalCodeName",key="#postalCode")
	public String selectPostalCodeName(String postalCode) {	
		Region region=postalCodeDao.selectRegion(Integer.parseInt(postalCode));
		City city;
		if(region==null){
			city=postalCodeDao.selectCity(Integer.parseInt(postalCode));
		}
		else {
			city=postalCodeDao.selectCity(region.getCityId());
		}
		Province province;
		if(city==null) {
			province=postalCodeDao.selectProvince(Integer.parseInt(postalCode));
		}else {
			province=postalCodeDao.selectProvince(city.getProvinceId());
		}
		
		String name=new String();
		if(province==null) {
			return null;
		}
		name+=province.getProvinceName();
		if(city!=null) {
			name+=city.getCityName();
		}
		if(region!=null){
			name+=region.getRegionName();
		}
		
		return name;
	}
}

