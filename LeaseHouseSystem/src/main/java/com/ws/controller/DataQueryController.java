package com.ws.controller;

import com.ws.dto.DataQueryVOPage;
import com.ws.dto.SearchCriteriaDTO;
import com.ws.dto.UserInfo;
import com.ws.service.HouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by laowang on 17-5-2.
 */
@Controller
public class DataQueryController {
    @Autowired
    HouseServiceImpl houseService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public DataQueryVOPage[] getDetailData(@RequestBody SearchCriteriaDTO searchCriteriaDTO){
        return houseService.searchAll(searchCriteriaDTO);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserInfo userInfo){
        return "success";
    }
}
