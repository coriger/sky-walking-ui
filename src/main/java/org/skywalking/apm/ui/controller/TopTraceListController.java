package org.skywalking.apm.ui.controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skywalking.apm.ui.service.TopTraceListService;
import org.skywalking.apm.ui.web.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pengys5
 */
@Controller
public class TopTraceListController extends ControllerBase {

    private Logger logger = LogManager.getFormatterLogger(TopTraceListController.class);

    @Autowired
    private TopTraceListService service;

    @RequestMapping(value = "topTraceListDataLoad", method = RequestMethod.GET)
    @ResponseBody
    public void topTraceListDataLoad(@ModelAttribute("startTime") long startTime,
        @ModelAttribute("endTime") long endTime,
        @ModelAttribute("limit") int limit, @ModelAttribute("from") int from, @ModelAttribute("minCost") int minCost,
        @ModelAttribute("maxCost") int maxCost, @ModelAttribute("globalTraceId") String globalTraceId,
        @ModelAttribute("operationName") String operationName, @ModelAttribute("sort") String sort,
        HttpServletResponse response) throws IOException {
        logger.debug("topTraceListDataLoad startTime = %s, endTime = %s, from=%s, minCost=%s, maxCost=%s, globalTraceId=%s, operationName=%s, sort=%s", startTime, endTime, from, minCost, maxCost, globalTraceId, operationName, sort);
        JsonObject topSegJson = service.topTraceListDataLoad(startTime, endTime, minCost, maxCost, limit, from, globalTraceId, operationName, sort);
        reply(topSegJson.toString(), response);
    }
}
