package service;

import entity.CrRing;

import java.util.List;

/**
 * Created by Ridiculous on 2016/10/13.
 */
public interface CrRingService {
    List<CrRing> getAllCrRing();
    public int getCountByRingid(String ringid);
}
