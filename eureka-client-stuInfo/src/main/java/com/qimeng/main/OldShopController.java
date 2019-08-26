package com.qimeng.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.Service.UsePointsLogService;
import com.qimeng.main.dao.OldServiceDao;
import com.qimeng.main.entity.Repertory;
import com.qimeng.main.entity.UsePointsLog;
import com.qimeng.main.vo.CommodityEntity;
import com.qimeng.main.vo.NewCommodityEntity;
import com.qimeng.main.vo.Student;

@RestController
@RequestMapping("/shop")
public class OldShopController {

	@Autowired
	OldServiceDao oldServiceDao;
	@Autowired
	UsePointsLogService usePointsLogService;
	
	@RequestMapping("/getGg")
	public String getGg(String jqbh) 
	{
		 
		 List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
		 List<String> list=oldServiceDao.getPic(jqbh);
		 for (int i = 0; i < list.size(); i++) {
			 String string=list.get(i);
			 string="http://sys.qimenghb.com/"+string;
			 Map<String,String> map1=new HashMap<String,String>();
			 map1.put("pic", string);
			 list1.add(map1);
		}
		 Map<String, List<Map<String,String>>> map=new HashMap<String, List<Map<String,String>>>();
		 map.put("pic", list1);
		 return JSONObject.toJSONString(map);
	}
		
	@RequestMapping("/getLp")
	public String getLp(String jqbh)
	{
		List<NewCommodityEntity> list=oldServiceDao.getNewProut(jqbh);
		 for (int i = 0; i < list.size(); i++) {
			 CommodityEntity comm=list.get(i);
			 String string=comm.getPic();
			 string="http://sys.qimenghb.com/"+string;
			 list.get(i).setPic(string);
		}
		 Collections.sort(list,new Comparator<NewCommodityEntity>() {

			@Override
			public int compare(NewCommodityEntity o1, NewCommodityEntity o2) {
				// TODO Auto-generated method stub
				return o2.getPriority()-o1.getPriority();
			}
		});
		 Map<String,List<CommodityEntity>> map=new HashMap<String,List<CommodityEntity>>();
		 List<CommodityEntity> list1=new ArrayList<CommodityEntity>();
		 for (NewCommodityEntity commodityEntity : list) {
			 CommodityEntity tempCommodityEntity=new CommodityEntity();
			 tempCommodityEntity.setId(commodityEntity.getId());
			 tempCommodityEntity.setJf(commodityEntity.getJf());
			 tempCommodityEntity.setMc(commodityEntity.getMc());
			 tempCommodityEntity.setPic(commodityEntity.getPic());
			 list1.add(tempCommodityEntity);
		}
		 map.put("lp", list1);
		 System.out.print(list1.toString());
		 return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("/getUserDp")
	public String getUserDp(String code)
	{
		Student student=oldServiceDao.getStudent(code);
		Map<String, Object> map = new HashMap<String, Object>();
		if(student==null){
			map.put("msg", 0);
			map.put("success", false);
		}
		else {
			map.put("msg", student.getBind()>0?1:0);
			map.put("success", true);
			map.put("xm", student.getName());
			map.put("id", student.getId());
			map.put("jf", student.getPoints());
		}
		return JSONObject.toJSONString(map);
	}
	@RequestMapping("/getLpdh")
	public String getLpdh( String jqbh, String lpid, String jf, String userid)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Student student=oldServiceDao.getStudentByID(Integer.parseInt(userid));
		
		CommodityEntity commodityEntity=oldServiceDao.getProutBy(Integer.parseInt(lpid));
		String name=oldServiceDao.getNameByCode(jqbh);
		int i=Integer.parseInt(jqbh);
		int j=Integer.parseInt(lpid);
		Repertory repertory=oldServiceDao.getRepertory(i,j);
		
		if(student==null||commodityEntity==null||repertory==null||repertory.getKucun()==0||name==null||student.getPoints()<(Integer.parseInt(jf)))
		{
			map.put("success", false);
			return JSONObject.toJSONString(map);
		}
		
		UsePointsLog usepointsLog=new UsePointsLog();
		//自定义订单编号生成规则   由YYYYMMDD(年月日) + 时间戳的格式组成
		Date currDate = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String newDate=sdf.format(currDate);
		String strLong = String.valueOf(currDate.getTime()/1000);
		String orderNo=newDate+strLong;
		
		repertory.setKucun(repertory.getKucun()-1);
		repertory.setSale(repertory.getSale()+1);
		repertory.setLastdate(currDate);
		oldServiceDao.updateRepertory(repertory);
		oldServiceDao.insertPointsLog(Integer.parseInt(userid), student.getName(), Integer.parseInt(lpid), commodityEntity.getMc(), Integer.parseInt(jf), jqbh, name, currDate, orderNo);
		usepointsLog.setAppid("7564564864648644");
		usepointsLog.setStudentName(student.getName());
		usepointsLog.setStudentCode(student.getCode());
		usepointsLog.setUsedPoints(Integer.parseInt(jf));
		usepointsLog.setCreateTime(currDate);
		usepointsLog.setMark("大屏机扣分");
		oldServiceDao.updatePoints(student.getCode(), student.getPoints()-(Integer.parseInt(jf)));
		usePointsLogService.insertPointsLog(usepointsLog);
		map.put("ddh", orderNo);
		map.put("success", true);
		return JSONObject.toJSONString(map);
	}
	@RequestMapping("/hxdd")
	public String hxdd(String ddh)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(oldServiceDao.updataorder(ddh)>0)
		{
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		return JSONObject.toJSONString(map);
	}
	@RequestMapping("/getXsJfSy")
	public String getXsJfSy(String code,String jf)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Student student=oldServiceDao.getStudent(code);
		if(student==null||student.getPoints()<Integer.parseInt(jf)*10)
		{
			map.put("success", false);
			return JSONObject.toJSONString(map);
		}
		UsePointsLog usepointsLog=new UsePointsLog();
		Date currDate = new Date();
		usepointsLog.setAppid("7845345646154645");
		usepointsLog.setStudentName(student.getName());
		usepointsLog.setStudentCode(student.getCode());
		usepointsLog.setUsedPoints(Integer.parseInt(jf));
		usepointsLog.setCreateTime(currDate);
		usepointsLog.setMark("商城扣分");
		oldServiceDao.updatePoints(student.getCode(), student.getPoints()-(Integer.parseInt(jf)*10));
		usePointsLogService.insertPointsLog(usepointsLog);
		map.put("success", true);
		return JSONObject.toJSONString(map);
	}



}
