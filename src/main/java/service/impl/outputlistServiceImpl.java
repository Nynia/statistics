package service.impl;

import dao.OutputListMapper;
import entity.OutputList;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;
import service.outputlistService;
import utils.queryCondition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Ridiculous on 2016/6/2.
 */
@Service
public class outputlistServiceImpl implements outputlistService {
    @Resource
    OutputListMapper outputListMapper;

    public List<OutputList> getOutputByDate(queryCondition condition) {
        return outputListMapper.selectOutputByDate(condition);
    }
}
