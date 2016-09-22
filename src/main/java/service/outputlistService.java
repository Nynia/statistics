package service;

import entity.OutputList;
import utils.queryCondition;

import java.util.Date;
import java.util.List;

/**
 * Created by Ridiculous on 2016/6/2.
 */
public interface outputlistService {
    List<OutputList> getOutputByDate(queryCondition condition);
}
