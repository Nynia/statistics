package service;

import entity.CrSubscribe;

import java.util.List;

/**
 * Created by Ridiculous on 2016/10/14.
 */
public interface CrSubscribeService {
    public List<CrSubscribe> getSubscribeinGivenTime(String start,String end);
    public List<CrSubscribe> getSubscribeinGivenTimeByRingid(String id, String start,String end);
}