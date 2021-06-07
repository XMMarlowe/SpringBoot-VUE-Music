package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.WorkOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 歌词报错表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
public interface IWorkOrderService extends IService<WorkOrder> {

    /**
     * 添加工单
     *
     * @param workOrder
     * @return
     */
    boolean addWorkOrder(WorkOrder workOrder);


    /**
     * 更新工单状态以及歌曲歌词信息
     *
     * @param workOrder
     * @return
     */
    boolean updateWorkOrder(WorkOrder workOrder);


    /**
     * 根据工单id删除工单
     *
     * @param id
     * @return
     */
    boolean deleteWorkOrderById(Integer id);


    /**
     * 分页查询所有工单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<WorkOrder> allWorkOrder(int pageNo, int pageSize);

    /**
     * 根据id查询工单信息
     *
     * @param id
     * @return
     */
    WorkOrder findWorkOrderById(Integer id);

}
