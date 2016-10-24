package service.impl;

import dao.CrRingMapper;
import entity.CrRing;
import org.springframework.stereotype.Service;
import service.CrRingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ridiculous on 2016/10/13.
 */
@Service
public class CrRingServiceImpl implements CrRingService{
    @Resource
    private CrRingMapper crRingMapper;

    public List<CrRing> getAllCrRing() {
        return crRingMapper.selectAll();
    }

    public int getCountByRingid(String ringid) {
        return crRingMapper.selectSumOfCountByRingid(ringid);
    }
}
