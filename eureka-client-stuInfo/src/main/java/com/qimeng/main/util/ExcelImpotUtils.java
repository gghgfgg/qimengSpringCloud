package com.qimeng.main.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author     : WH
 * @group      : tgb8
 * @Date       : 2014-1-2 下午9:13:21
 * @Comments   : 导入导出Excel工具类
 * @Version    : 1.0.0
 */

public class ExcelImpotUtils {

	private static Logger logger = Logger.getLogger(ExcelImpotUtils.class);

    static private Workbook wb;
    static private Sheet sheet;

    /**
     * @param in          ：承载着Excel的输入流
     * @param sheetIndex  ：要导入的工作表序号
     * @param skipRows    跳过读取的条数,默认为0
     * @param entityClass ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap    ：Excel中的中文列头和类的英文属性的对应关系Map
     * @param fileName    ：文件名
     * @return ：List
     * @throws ServiceException
     * @MethodName : excelToList
     * @Description : 将Excel转化为List
     */
    public static <T> List<T> excelToList(InputStream in, int sheetIndex, int skipRows, Class<T> entityClass,
                                          List<FieldDefine> fieldMap, String fileName) throws ExcelException {
        // 定义要返回的list
        List<T> resultList = new ArrayList<>();
        try {
            // 根据Excel数据源创建WorkBook
            String postfix = fileName.substring(fileName.lastIndexOf("."),
                    fileName.length());
            if (".xls".equals(postfix)) {
                // 针对 2003 Excel 文件
                wb = new HSSFWorkbook(in);
                //获取excel文件某个sheet
                sheet = wb.getSheetAt(sheetIndex);
            } else {
                // 针对2007 Excel 文件
                wb = new XSSFWorkbook(in);
                sheet = wb.getSheetAt(sheetIndex);
            }
            // 获取工作表
            Integer rowCount = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(skipRows);
            String[] excelFieldNames = new String[firstRow.getLastCellNum()];
            // 获取Excel中的列名
            for (int i = 0; i < firstRow.getLastCellNum(); i++) {
                Cell currentCell = firstRow.getCell(i);
                excelFieldNames[i] = currentCell.getStringCellValue();
            }
            // 将列名和列号放入Map中,这样通过列名就可以拿到列号
            HashMap<String, Integer> colMap = new HashMap<>();
            for (int i = 0; i < excelFieldNames.length; i++) {
                colMap.put(excelFieldNames[i], firstRow.getCell(i).getColumnIndex());
            }
            // 将sheet转换为list
            for (int i = 1; i <= rowCount; i++) {
                // 新建要转换的对象，每一行都可以理解为一个对象
                T entity = entityClass.newInstance();
                // 给对象中的字段赋值
                for (FieldDefine field : fieldMap) {
                    // 获取中文字段名
                    String cnName = field.title;
                    // 获取英文字段名
                    String enName = field.fieldName;
                    // 根据中文字段名获取列号
                    
                    if (colMap.containsKey(cnName)) {
                    	int col = colMap.get(cnName);
                    	   // 获取当前单元格中的内容
                        String content = getCellFormatValue(sheet.getRow(i).getCell(col));
                        // 给对象赋值             
                        setFieldValueByName(enName, content, entity);
                    }
                 
                }
                resultList.add(entity);
            }
        } catch (Exception e) {
            logger.error("异常:", e);
            throw new ExcelException("excel转list异常");
        }
        return resultList;
    }

    public static <T> List<T> excelToList(InputStream in, Class<T> entityClass,
                                          List<FieldDefine> fieldMap, String fileName) throws ExcelException {
        return excelToList(in, 0, 0, entityClass, fieldMap, fileName);
    }

    /**
     * @param in
     * @param skipRows    跳过读取的条数,默认为0
     */
    public static <T> List<T> excelToList(InputStream in, int skipRows, Class<T> entityClass,
                                          List<FieldDefine> fieldMap, String fileName) throws ExcelException {
        return excelToList(in, 0, skipRows, entityClass, fieldMap, fileName);
    }

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();
        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        // 如果本类和父类都没有，则返回空
        return null;
    }

    /**
     * 根据Cell类型设置数据
     */
    private static String getCellFormatValue(Cell cell) {
        String cellvalue;
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cellvalue = sdf.format(date);
                    } else {
                        // 如果是纯数字取得当前Cell的数值
                    	NumberFormat nf = NumberFormat.getInstance();

                        cellvalue = nf.format(cell.getNumericCellValue());
                        if (cellvalue.indexOf(",") >= 0) {
                        	cellvalue = cellvalue.replace(",", "");
                        }
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    // 默认的Cell值
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    /**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param entity          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     */
    private static void setFieldValueByName(String fieldName, Object fieldValue, Object entity)
            throws Exception {

        //通过反射得到成员变量
        Field field = getFieldByName(fieldName, entity.getClass());
        if (field != null) {
            //暴力反射：获取本类private修饰的成员变量
            field.setAccessible(true);
            // 获取成员变量类型
            Class<?> fieldType = field.getType();

            // 根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(entity, String.valueOf(fieldValue));
            } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                field.set(entity, NumberUtils.createNumber(fieldValue.toString()).intValue());
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                field.set(entity, NumberUtils.createNumber(fieldValue.toString()).longValue());
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                field.set(entity, NumberUtils.createNumber(fieldValue.toString()).floatValue());
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                field.set(entity, NumberUtils.createNumber(fieldValue.toString()).shortValue());
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                field.set(entity, NumberUtils.createNumber(fieldValue.toString()).doubleValue());
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(entity, Character.valueOf(fieldValue.toString().charAt(0)));
                }
            } else if (Date.class == fieldType) {
                field.set(entity, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
            } else {
                field.set(entity, fieldValue);
            }
        } else {
            throw new ExcelException("获取excel数据异常，请检查excel数据是否异常");
        }
    }

    public static class FieldDefine {

        private String title;

        private String fieldName;

        public FieldDefine(String title, String fieldName) {
            this.fieldName = fieldName;
            this.title = title;
        }
    }
    
    
    public static <T>  void   listToExcel (
            List<T> list ,
            List<FieldDefine> fieldMap,
            String sheetName,
            int sheetSize,
            OutputStream out
            ) throws ExcelException{


        if(list.size()==0 || list==null){
            throw new ExcelException("数据源中没有任何数据");
        }

        if(sheetSize>65535 || sheetSize<1){
            sheetSize=65535;
        }
     try {
            wb =new HSSFWorkbook();

            //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
            //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
            //1.计算一共有多少个工作表
            double sheetNum=Math.ceil(list.size()/new Integer(sheetSize).doubleValue());

            //2.创建相应的工作表，并向其中填充数据
            for(int i=0; i<sheetNum; i++){
                //如果只有一个工作表的情况
                if(1==sheetNum){
                    sheet=wb.createSheet(sheetName);
                    fillSheet(sheet, list, fieldMap, 0, list.size()-1);

                //有多个工作表的情况
                }else{
                     sheet=wb.createSheet(sheetName+(i+1));

                    //获取开始索引和结束索引
                    int firstIndex=i*sheetSize;
                    int lastIndex=(i+1)*sheetSize-1>list.size()-1 ? list.size()-1 : (i+1)*sheetSize-1;
                    //填充工作表
                    fillSheet(sheet, list, fieldMap, firstIndex, lastIndex);
                }
            }

            wb.write(out);
            wb.close();

        }catch (Exception e) {
            e.printStackTrace();
            //如果是ExcelException，则直接抛出
            if(e instanceof ExcelException){
                throw (ExcelException)e;

            //否则将其它异常包装成ExcelException再抛出
            }else{
                throw new ExcelException("导出Excel失败");
            }
        }

    }
    
    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，工作表大小为2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param out       导出流
     * @throws ExcelException
     */
    public static  <T>  void   listToExcel (
            List<T> list ,
            List<FieldDefine> fieldMap,
            String sheetName,
            OutputStream out
            ) throws ExcelException{

        listToExcel(list, fieldMap, sheetName, 65535, out);

    }


    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（导出到浏览器，可以自定义工作表的大小）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param sheetSize    每个工作表中记录的最大个数
     * @param response  使用response可以导出到浏览器
     * @throws ExcelException
     */
    public static  <T>  void   listToExcel (
            List<T> list ,
            List<FieldDefine> fieldMap,
            String sheetName,
            int sheetSize,
            HttpServletResponse response 
            ) throws ExcelException{

        //设置默认文件名为当前时间：年月日时分秒
        String fileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();

        //设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xls");
        response.setCharacterEncoding("utf-8");
        //response.setContentType("application/vnd.ms-excel;charset=gb2312");        //改成输出excel文件
       // response.setHeader("Content-disposition","attachment; filename="+filename+".xls" );
        //创建工作簿并发送到浏览器
        try {

            OutputStream out=response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize,out);

        } catch (Exception e) {
            e.printStackTrace();

            //如果是ExcelException，则直接抛出
            if(e instanceof ExcelException){
                throw (ExcelException)e;

            //否则将其它异常包装成ExcelException再抛出
            }else{
                throw new ExcelException("导出Excel失败");
            }
        }
    }


    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param response  使用response可以导出到浏览器
     * @throws ExcelException
     */
    public static <T>  void   listToExcel (
            List<T> list ,
            List<FieldDefine> fieldMap,
            String sheetName,
            HttpServletResponse response 
            ) throws ExcelException{

        listToExcel(list, fieldMap, sheetName, 65535, response);
    }
    /**
     * @MethodName  : fillSheet
     * @Description : 向工作表中填充数据
     * @param sheet     工作表 
     * @param list  数据源
     * @param fieldMap 中英文字段对应关系的Map
     * @param firstIndex    开始索引
     * @param lastIndex 结束索引
     */
    private static <T> void fillSheet(
            Sheet sheet,
            List<T> list,
            List<FieldDefine> fieldMap,
            int firstIndex,
            int lastIndex
            )throws Exception{

    	Row row=sheet.createRow(0);
        //填充表头
        for(int i=0;i<fieldMap.size();i++){
        	Cell cell=row.createCell(i);
        	cell.setCellValue(fieldMap.get(i).title);
        }

        //填充内容
        int rowNo=1;
        for(int index=firstIndex;index<=lastIndex;index++){
            //获取单个对象
            T item=list.get(index);
            row=sheet.createRow(rowNo);
            for(int i=0;i<fieldMap.size();i++){
            	
                Object objValue=getFieldValueByNameSequence(fieldMap.get(i).fieldName, item);
                String fieldValue=objValue==null ? "" : objValue.toString();
                Cell cell=row.createCell(i);
            	cell.setCellValue(fieldValue);
      
            }

            rowNo++;
        }

        //设置自动列宽
        setColumnAutoSize(sheet, 5);
    }
    
    /**
     * @MethodName  : getFieldValueByNameSequence
     * @Description : 
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     * 
     * @param fieldNameSequence  带路径的属性名或简单属性名
     * @param o 对象
     * @return  属性值
     * @throws Exception
     */
    private static  Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception{

        Object value=null;

        //将fieldNameSequence进行拆分
        String[] attributes=fieldNameSequence.split("\\.");
        if(attributes.length==1){
            value=getFieldValueByName(fieldNameSequence, o);
        }else{
            //根据属性名获取属性对象
            Object fieldObj=getFieldValueByName(attributes[0], o);
            String subFieldNameSequence=fieldNameSequence.substring(fieldNameSequence.indexOf(".")+1);
            value=getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value; 

    }
    
    /**
     * @MethodName  : getFieldValueByName
     * @Description : 根据字段名获取字段值
     * @param fieldName 字段名
     * @param o 对象
     * @return  字段值
     */
    private static  Object getFieldValueByName(String fieldName, Object o) throws Exception{

        Object value=null;
        Field field=getFieldByName(fieldName, o.getClass());

        if(field !=null){
            field.setAccessible(true);
            value=field.get(o);
        }else{
            throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName);
        }

        return value;
    }
    /**
     * @MethodName  : setColumnAutoSize
     * @Description : 设置工作表自动列宽和首行加粗
     * @param ws
     */
    private static void setColumnAutoSize(Sheet st,int extraWith){
        //获取本列的最宽单元格的宽度
    	
        for(int i=0;i<st.getRow(0).getLastCellNum();i++){
            int colWith=0;
            for(int j=0;j<st.getLastRowNum();j++){
                String content=st.getRow(j).getCell(i).getStringCellValue();
                int cellWith=content.length();
                if(colWith<cellWith){
                    colWith=cellWith;
                }
            }
            //设置单元格的宽度为最宽宽度+额外宽度
            st.setColumnWidth(i, 255*(colWith+5));
        }

    }
}

