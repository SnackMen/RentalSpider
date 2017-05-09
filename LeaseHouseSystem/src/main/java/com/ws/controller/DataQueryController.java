package com.ws.controller;

import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;
import com.ws.dto.UserInfo;
import com.ws.service.HouseServiceImpl;
import com.ws.service.IHouseService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by laowang on 17-5-2.
 */
@Controller
public class DataQueryController {
//    @Autowired
//    HouseServiceImpl houseService;
    @Autowired
    IHouseService houseService;

//    @RequestMapping(value = "/zhaofang", method = RequestMethod.GET)
//    public String index(Model model){
//        if(!model.containsAttribute("searchCriteriaDTO")){
//            model.addAttribute("searchCriteriaDTO",new SearchCriteriaDTO());
//        }
//        return "/zhaofang";
//    }

//    @RequestMapping(value = "/zhaofang", method = RequestMethod.POST)
//    @ResponseBody
//    public DataQueryVOPage[] getDetailData(@RequestBody SearchCriteriaDTO searchCriteriaDTO){
//        return houseService.searchAll(searchCriteriaDTO);
//    }

    @RequestMapping(value = "/zhaofang")
    public void getDetailData(HttpServletRequest request, HttpServletResponse response){
        SearchCriteriaDTO searchCriteriaDTO = new SearchCriteriaDTO();
        searchCriteriaDTO.setDataSource(request.getParameter("dataSource"));
        searchCriteriaDTO.setLowPrice(request.getParameter("lowPrice"));
        searchCriteriaDTO.setHighPrice(request.getParameter("highPrice"));
        searchCriteriaDTO.setMapLevel(request.getParameter("mapLevel"));
        searchCriteriaDTO.setWorkPlace(request.getParameter("workPlace"));
        searchCriteriaDTO.setPersonal(Boolean.valueOf(request.getParameter("isPersonal")));
        searchCriteriaDTO.setAnjuke(Boolean.valueOf(request.getParameter("isAnjuke")));
        DataQueryVOPage dataQueryVOPage = houseService.searchAll(searchCriteriaDTO);
//        HouseDTO[] houseDTOs = new HouseDTO[10];
//        dataQueryVOPage.setHouseDTOS(houseDTOs);
        List<HouseDTO> houseDTOList = dataQueryVOPage.getHouseDTOList();
        System.out.println("***"+houseDTOList.size());
        dataQueryVOPage.setVisitQuantity(10);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new JsDateJsonBeanProcessor());
        JSONObject jsonObject = JSONObject.fromObject(dataQueryVOPage, jsonConfig);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.append(jsonObject.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(out != null)
                out.close();
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserInfo userInfo){
        return "success";
    }
}
