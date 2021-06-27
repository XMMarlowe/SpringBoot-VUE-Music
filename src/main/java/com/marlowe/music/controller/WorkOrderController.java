package com.marlowe.music.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.WorkOrder;
import com.marlowe.music.service.IWorkOrderService;
import com.marlowe.music.service.impl.WorkOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 歌词报错表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@Api(tags = "工单管理控制类")
@RestController
@RequestMapping("/workOrder")
@Slf4j
public class WorkOrderController {


    @Autowired
    private IWorkOrderService workOrderService;

    /**
     * 添加工单
     *
     * @param workOrder
     * @return
     */
    @ApiOperation(value = "添加工单")
    @PostMapping("add")
    public Result addWorkOrder(@RequestBody WorkOrder workOrder) {
        boolean addWorkOrder = workOrderService.addWorkOrder(workOrder);
        if (addWorkOrder) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }

    }

    /**
     * 更新工单
     *
     * @param workOrder
     * @return
     */
    @ApiOperation(value = "更新工单")
    @PostMapping("update")
    public Result updateWorkOrder(@RequestBody WorkOrder workOrder) {
        log.info("workOrder:" + workOrder);
        boolean updateWorkOrder = workOrderService.updateWorkOrder(workOrder);
        if (updateWorkOrder) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }

    /**
     * 根据id删除工单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除工单")
    @GetMapping("delete/{id}")
    public Result deleteWorkOrderById(@PathVariable int id) {
        boolean deleteWorkOrderById = workOrderService.deleteWorkOrderById(id);
        if (deleteWorkOrderById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 批量删除工单
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除工单")
    @PostMapping("deletes")
    public Result deleteWorkOrders(@RequestBody List<Integer> ids) {
        boolean deleteWorkOrders = workOrderService.deleteWorkOrders(ids);
        if (deleteWorkOrders) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 查询所有工单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询所有工单")
    @GetMapping("allWorkOrder/{pageNo}/{pageSize}")
    public Result<List<WorkOrder>> allWorkOrder(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<WorkOrder> pageInfo = workOrderService.allWorkOrder(pageNo, pageSize);
        List<WorkOrder> workOrders = pageInfo.getList();
        return Result.ok(workOrders);
    }

    /**
     * 根据id查询工单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询工单")
    @GetMapping("detail/{id}")
    public Result findWorkOrderById(@PathVariable int id) {
        WorkOrder workOrder = workOrderService.findWorkOrderById(id);
        return Result.ok(workOrder);
    }


}
