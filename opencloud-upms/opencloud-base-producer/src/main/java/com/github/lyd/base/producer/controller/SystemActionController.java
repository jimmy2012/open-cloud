package com.github.lyd.base.producer.controller;

import com.github.lyd.base.producer.service.SystemActionService;
import com.github.lyd.common.model.PageList;
import com.github.lyd.common.model.PageParams;
import com.github.lyd.common.model.ResultBody;
import com.github.lyd.base.client.api.SystemActionRemoteService;
import com.github.lyd.base.client.entity.SystemAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuyadu
 */
@Api(tags = "动作资源管理")
@RestController
public class SystemActionController implements SystemActionRemoteService {
    @Autowired
    private SystemActionService actionService;

    /**
     * 动作列表
     *
     * @return
     */
    @ApiOperation(value = "动作列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form"),
            @ApiImplicitParam(name = "limit", value = "显示条数:最大999", paramType = "form"),
            @ApiImplicitParam(name = "keyword", value = "查询字段", paramType = "form"),
    })
    @PostMapping("/action")
    @Override
    public ResultBody<PageList<SystemAction>> action(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        return ResultBody.success(actionService.findListPage(new PageParams(page, limit), keyword));
    }

    /**
     * 获取动作资源
     *
     * @param actionId 动作Id
     * @return 应用信息
     */
    @ApiOperation(value = "获取动作资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "actionId", required = true, value = "动作Id", paramType = "path"),
    })
    @GetMapping("/action/{actionId}")
    @Override
    public ResultBody<SystemAction> getAction(@PathVariable("actionId") Long actionId) {
        return ResultBody.success(actionService.getAction(actionId));
    }

    /**
     * 添加动作资源
     *
     * @param actionCode  动作编码
     * @param actionName  动作名称
     * @param menuId      归属菜单
     * @param path         请求路径
     * @param status     是否启用
     * @param priority    优先级越小越靠前
     * @param actionDesc 描述
     * @return
     */
    @ApiOperation(value = "添加动作资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "actionCode", required = true, value = "动作编码", paramType = "form"),
            @ApiImplicitParam(name = "actionName", required = true, value = "动作名称", paramType = "form"),
            @ApiImplicitParam(name = "menuId", required = true, value = "归属菜单", paramType = "form"),
            @ApiImplicitParam(name = "path", required = false, value = "请求路径", paramType = "form"),
            @ApiImplicitParam(name = "status", required = true, defaultValue = "1", allowableValues = "0,1", value = "是否启用", paramType = "form"),
            @ApiImplicitParam(name = "priority", required = false, value = "优先级越小越靠前", paramType = "form"),
            @ApiImplicitParam(name = "actionDesc", required = false, value = "描述", paramType = "form"),
    })
    @PostMapping("/action/add")
    @Override
    public ResultBody<Boolean> addAction(
            @RequestParam(value = "actionCode") String actionCode,
            @RequestParam(value = "actionName") String actionName,
            @RequestParam(value = "menuId") Long menuId,
            @RequestParam(value = "path", required = false, defaultValue = "") String path,
            @RequestParam(value = "status", defaultValue = "1") Integer status,
            @RequestParam(value = "priority", required = false, defaultValue = "0") Integer priority,
            @RequestParam(value = "actionDesc", required = false, defaultValue = "") String actionDesc
    ) {
        SystemAction action = new SystemAction();
        action.setActionCode(actionCode);
        action.setActionName(actionName);
        action.setMenuId(menuId);
        action.setPath(path);
        action.setStatus(status);
        action.setPriority(priority);
        action.setActionDesc(actionDesc);
        return ResultBody.success(actionService.addAction(action));
    }

    /**
     * 编辑动作资源
     *
     * @param actionId    动作ID
     * @param actionCode  动作编码
     * @param actionName  动作名称
     * @param menuId      归属菜单
     * @param path         请求路径
     * @param status     是否启用
     * @param priority    优先级越小越靠前
     * @param actionDesc 描述
     * @return
     */
    @ApiOperation(value = "编辑动作资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "actionId", required = true, value = "动作ID", paramType = "form"),
            @ApiImplicitParam(name = "actionCode", required = true, value = "动作编码", paramType = "form"),
            @ApiImplicitParam(name = "actionName", required = true, value = "动作名称", paramType = "form"),
            @ApiImplicitParam(name = "menuId", required = true, value = "归属菜单", paramType = "form"),
            @ApiImplicitParam(name = "path", required = false, value = "请求路径", paramType = "form"),
            @ApiImplicitParam(name = "status",required = true, defaultValue = "1", allowableValues = "0,1", value = "是否启用", paramType = "form"),
            @ApiImplicitParam(name = "priority", required = false, value = "优先级越小越靠前", paramType = "form"),
            @ApiImplicitParam(name = "actionDesc", required = false, value = "描述", paramType = "form"),
    })
    @PostMapping("/action/update")
    @Override
    public ResultBody<Boolean> updateAction(
            @RequestParam("actionId") Long actionId,
            @RequestParam(value = "actionCode") String actionCode,
            @RequestParam(value = "actionName") String actionName,
            @RequestParam(value = "menuId") Long menuId,
            @RequestParam(value = "path", required = false, defaultValue = "") String path,
            @RequestParam(value = "status", defaultValue = "1") Integer status,
            @RequestParam(value = "priority", required = false, defaultValue = "0") Integer priority,
            @RequestParam(value = "actionDesc", required = false, defaultValue = "") String actionDesc
    ) {
        SystemAction action = new SystemAction();
        action.setActionId(actionId);
        action.setActionCode(actionCode);
        action.setActionName(actionName);
        action.setMenuId(menuId);
        action.setPath(path);
        action.setStatus(status);
        action.setPriority(priority);
        action.setActionDesc(actionDesc);
        return ResultBody.success(actionService.updateAction(action));
    }

    /**
     * 更新状态
     *
     * @param actionId 动作ID
     * @return
     */
    @ApiOperation(value = "更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "actionId", required = true, value = "动作ID", paramType = "form"),
            @ApiImplicitParam(name = "status",required = true, defaultValue = "1", allowableValues = "0,1", value = "是否启用", paramType = "form")
    })
    @PostMapping("/action/update/status")
    @Override
    public ResultBody<Boolean> updateStatus(
            @RequestParam("actionId") Long actionId,
            @RequestParam(value = "status", defaultValue = "1") Integer status
    ) {
        return ResultBody.success(actionService.updateStatus(actionId, status));
    }

    /**
     * 移除动作
     *
     * @param actionId 动作ID
     * @return
     */
    @ApiOperation(value = "移除动作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "actionId", required = true, value = "动作ID", paramType = "form")
    })
    @PostMapping("/action/remove")
    @Override
    public ResultBody<Boolean> removeAction(
            @RequestParam("actionId") Long actionId
    ) {
        return ResultBody.success(actionService.removeAction(actionId));
    }
}