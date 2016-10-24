package service.impl;

import dao.CrSubscribeMapper;
import entity.CrSubscribe;
import org.springframework.stereotype.Service;
import service.CrSubscribeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ridiculous on 2016/10/14.
 */
@Service
public class CrSubscribeServiceImpl implements CrSubscribeService {
    @Resource
    private CrSubscribeMapper crSubscribeMapper;
    public List<CrSubscribe> getSubscribeinGivenTime(String start, String end) {
        return crSubscribeMapper.selectSubscribeinGivenTime(start, end);
    }

    public List<CrSubscribe> getSubscribeinGivenTimeByRingid(String id, String start, String end) {
        return crSubscribeMapper.selectSubscribeinGivenTimeByRingid(id,start,end);
    }
}
