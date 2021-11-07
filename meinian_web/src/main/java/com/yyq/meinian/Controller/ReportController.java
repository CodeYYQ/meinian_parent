package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.service.MemberService;
import com.yyq.meinian.service.ReportService;
import com.yyq.meinian.service.SetmealService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title: ReportController
 * @Author yyq
 * @Date: 2021/11/5 20:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;
    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        //根据当前时间，获取前12个月的日历(当前日历2020-02，12个月前，日历时间2019-03)
        //第一个参数，日历字段
        //第二个参数，要添加到字段中的日期或时间
        calendar.add(Calendar.MONTH,-12);

        List<String> months = new ArrayList<String>();
        for(int i=0;i<12;i++){
            //第一个参数是月份 2018-7
            //第二个参数是月份+1个月
            calendar.add(Calendar.MONTH,1);
            months.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }
        //获取去年一年每个月会员数量
        List<Integer> memberCount = memberService.getMemberCountByMonth(months);
        //将月份和会员数量map中存储
        Map map = new HashMap();
        map.put("months",months);
        map.put("memberCount",memberCount);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        List<Map> list = setmealService.getSetmealReport();
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,list);
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        Map map = reportService.getBusinessReportData();
        return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
    }

    @RequestMapping("/exportBusinessReport")
    public void exportBusinessReport(HttpServletRequest request, HttpServletResponse response){
        //获取运营统计数据
        Map result = reportService.getBusinessReportData();
        String reportDate = (String) result.get("reportDate");
        Integer todayNewMember = (Integer) result.get("todayNewMember");
        Integer totalMember = (Integer) result.get("totalMember");
        Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
        Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
        Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
        Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
        Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
        Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
        Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
        Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
        List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");
        try {
            //获取模板文件的真实路径
            String excelFilePath = request.getSession().getServletContext().getRealPath("template")+ File.separator+"report_template.xlsx";
            //通过POI读取excel文件，获取工作簿对象
            XSSFWorkbook xssf = new XSSFWorkbook(excelFilePath);
            //获取工作表对象
            XSSFSheet sheet = xssf.getSheetAt(0);
            sheet.getRow(2).getCell(5).setCellValue(reportDate);
            sheet.getRow(4).getCell(5).setCellValue(todayNewMember);
            sheet.getRow(4).getCell(7).setCellValue(totalMember);
            sheet.getRow(5).getCell(5).setCellValue(thisWeekNewMember);
            sheet.getRow(5).getCell(7).setCellValue(thisMonthNewMember);
            sheet.getRow(7).getCell(5).setCellValue(todayOrderNumber);
            sheet.getRow(7).getCell(7).setCellValue(todayVisitsNumber);
            sheet.getRow(8).getCell(5).setCellValue(thisWeekOrderNumber);
            sheet.getRow(8).getCell(7).setCellValue(thisWeekVisitsNumber);
            sheet.getRow(9).getCell(5).setCellValue(thisMonthOrderNumber);
            sheet.getRow(9).getCell(7).setCellValue(thisMonthVisitsNumber);
            int num = 12;
            for (Map map : hotSetmeal) {
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                sheet.getRow(num).getCell(4).setCellValue(name);
                sheet.getRow(num).getCell(5).setCellValue(setmeal_count);
                sheet.getRow(num).getCell(6).setCellValue(proportion.doubleValue());
                num++;
            }
            //获取响应流
            ServletOutputStream os = response.getOutputStream();
            //设置响应数据的格式
            response.setContentType("application/vnd.ms-excel");
            //设置下载方式
            response.setHeader("Content-Disposition", "attachment;filename=report.xlsx");
            //将excel输出
            xssf.write(os);
            os.close();
            xssf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
