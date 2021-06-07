package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.WorkOrder;
import com.marlowe.music.mapper.SongMapper;
import com.marlowe.music.mapper.WorkOrderMapper;
import com.marlowe.music.service.IWorkOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 歌词报错表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-07
 */
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private SongMapper songMapper;

    /**
     * 添加工单
     *
     * @param workOrder
     * @return
     */
    @Override
    public boolean addWorkOrder(WorkOrder workOrder) {
        return workOrderMapper.insert(workOrder) > 0;
    }

    /**
     * 更新工单状态
     *
     * @param workOrder
     * @return
     */
    @Override
    public boolean updateWorkOrder(WorkOrder workOrder) {
        String newLyric = workOrder.getLyric();
        System.out.println(newLyric);
        int updateStatus = workOrderMapper.update(workOrder, null);

        // 更新歌曲歌词信息

        // 根据歌曲id查询歌曲信息
        QueryWrapper<Song> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("song_id", workOrder.getSongId());
        Song song = songMapper.selectOne(queryWrapper1);

        // 设置新的歌词
        song.setLyric(newLyric);

        // 更新歌曲歌词
        songMapper.updateById(song);
        return updateStatus > 0;
    }


    /**
     * 根据工单id删除工单
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteWorkOrderById(Integer id) {
        return workOrderMapper.deleteById(id) > 0;
    }

    /**
     * 分页查询所有工单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<WorkOrder> allWorkOrder(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<WorkOrder> workOrders = workOrderMapper.selectList(null);
        PageInfo<WorkOrder> pageInfo = new PageInfo(workOrders);
        return pageInfo;
    }

    /**
     * 根据id查询工单信息
     *
     * @param id
     * @return
     */
    @Override
    public WorkOrder findWorkOrderById(Integer id) {
        WorkOrder workOrder = workOrderMapper.selectById(id);
        return workOrder;
    }
}
