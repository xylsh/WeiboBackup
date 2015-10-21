package io.github.xylsh.controller;

import static com.google.common.base.Preconditions.*;

import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.common.JsonResult;
import io.github.xylsh.model.ext.WeiboListVo;
import io.github.xylsh.model.ext.WeiboVo;
import io.github.xylsh.service.IMonitorService;
import io.github.xylsh.service.ISystemService;
import io.github.xylsh.service.IUserService;
import io.github.xylsh.service.IWeiboService;
import io.github.xylsh.util.StringUtil;
import io.github.xylsh.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by apple on 15-4-12.
 */
@Controller
@RequestMapping("/admin")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Resource
    private IUserService userService;
    @Resource
    private IMonitorService monitorService;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private ISystemService systemService;

    @RequestMapping(value = "/dashboard.htm")
    public String dashboardPage(HttpServletRequest request) {
        return "admin/dashboard";
    }

    @RequestMapping(value = "/addMonitor.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> addMonitor(@Valid MonitorDomain monitorDomain, BindingResult bindingResult) {
        LOGGER.info("/addMonitor.action,monitorDomain={}", monitorDomain);

        JsonResult<String> result = new JsonResult<String>();

        boolean isSucc = false;
        if (!bindingResult.hasErrors()) {
            isSucc = monitorService.addMonitor(monitorDomain);
            result.setData("添加成功！");
        } else {
            result.setErrmsg(StringUtil.generateErrorTip(bindingResult));
        }
        result.setRet(isSucc);

        return result;
    }

    @RequestMapping("/getAllMonitor.json")
    @ResponseBody
    public JsonResult<List<MonitorDomain>> getAllMonitor() {
        List<MonitorDomain> monitorList = monitorService.getAllMonitor();

        JsonResult<List<MonitorDomain>> result = new JsonResult<List<MonitorDomain>>();
        result.setRet(true);
        result.setData(monitorList);

        return result;
    }

    @RequestMapping("/getWeiboByMonitorId.json")
    @ResponseBody
    public JsonResult<List<WeiboVo>> getWeiboByMonitorId(@RequestParam long monitorId) {
        checkArgument(monitorId >= 0);

        List<WeiboVo> weiboVoList = weiboService.getWeiboByMonitorId(monitorId);

        JsonResult<List<WeiboVo>> result = new JsonResult<List<WeiboVo>>();
        result.setRet(true);
        result.setData(weiboVoList);
        return result;
    }

    @RequestMapping("/getWeiboByMonitorIdWithPage.json")
    @ResponseBody
    public JsonResult<WeiboListVo> getWeiboByMonitorId(@RequestParam long monitorId, @RequestParam int currPage,
                                                     @RequestParam int pageSize) {
        checkArgument(monitorId >= 0);
        checkArgument(currPage > 0);
        checkArgument(pageSize > 0);

        int totalWeibo = monitorService.getWeiboIds(monitorId).size();
        int totalPage = Util.calTotalPage(totalWeibo, pageSize);

        List<WeiboVo> weiboVoList = weiboService.getWeiboByMonitorId(monitorId, currPage, pageSize);
        WeiboListVo weiboListVo = new WeiboListVo(weiboVoList);
        weiboListVo.setTotalWeibo(totalWeibo);
        weiboListVo.setTotalPage(totalPage);
        weiboListVo.setCurrPage(currPage);
        weiboListVo.setPageSize(pageSize);

        JsonResult<WeiboListVo> result = new JsonResult<WeiboListVo>();
        result.setRet(true);
        result.setData(weiboListVo);
        return result;
    }

    @RequestMapping("/stopMonitor.json")
    @ResponseBody
    public JsonResult<String> stopMonitor(@RequestParam long monitorId) {
        checkArgument(monitorId >= 0);

        JsonResult<String> result = new JsonResult<String>();

        int rowAffected = monitorService.stopMonitor(monitorId);
        if (rowAffected == 1) {
            result.setRet(true);
            result.setData("停止成功！");
        } else {
            result.setRet(true);
            result.setErrmsg("停止失败！rowAffected=" + rowAffected);
        }

        return result;
    }

    /**
     * 如果当前后台正在备份，则停止备份
     * @return
     */
    @RequestMapping("/stopBackup.json")
    @ResponseBody
    public JsonResult<String> stopBackup(){
        systemService.stopBackup();

        JsonResult<String> result = new JsonResult<String>();
        result.setRet(true);
        result.setData("已发出停止备份请求.");
        return result;
    }
}
