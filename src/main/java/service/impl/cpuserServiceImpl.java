package service.impl;

import dao.CpUserMapper;
import entity.CpUser;
import org.springframework.stereotype.Service;
import service.cpuserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ridiculous on 2016/6/2.
 */
@Service
public class cpuserServiceImpl implements cpuserService {
    @Resource
    private CpUserMapper cpUserMapper;

    public List<CpUser> getAllCpUsers() {
        return cpUserMapper.selectAllCpUsers();
    }
}
