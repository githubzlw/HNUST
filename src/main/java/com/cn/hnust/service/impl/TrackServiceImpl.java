package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.TrackMapper;
import com.cn.hnust.dao.TrackPlaceMapper;
import com.cn.hnust.pojo.Track;
import com.cn.hnust.pojo.TrackPlace;
import com.cn.hnust.pojo.TrackQuery;
import com.cn.hnust.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackMapper trackMapper;
	
	@Autowired
	private TrackPlaceMapper trackPlaceMapper;
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		trackPlaceMapper.deleteByTrackId(id);
		return trackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Track record) {
		return trackMapper.insert(record);
	}

	@Override
	public int insertSelective(Track record) {
		return trackMapper.insertSelective(record);
	}

	@Override 
	public Track selectByPrimaryKey(Integer id) {
		return trackMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(TrackQuery record) {
		
		trackPlaceMapper.deleteByTrackId(record.getId());
		String places = record.getTrackPlaces();
		if(places!=null){
			String[] strs = places.split(",");
			for (String string : strs) {
				TrackPlace place = new TrackPlace();
				place.setPlace(string);
				place.setTrackId(record.getId());
				trackPlaceMapper.insertSelective(place);
			}			
		}
		return trackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Track record) {
		return trackMapper.updateByPrimaryKey(record);
	}

	@Transactional
	@Override
	public int insertBatch(List<TrackQuery> list) {
		
		int count = trackMapper.insertBatch(list);
		list.forEach(t->{	
			String places = t.getTrackPlaces();
			if(places!=null){
				String[] strs = places.split(",");
				for (String string : strs) {
					TrackPlace place = new TrackPlace();
					place.setPlace(string);
					place.setTrackId(t.getId());
					trackPlaceMapper.insertSelective(place);
				}			
			}	
		});	
		return count;
	}

	@Override
	public Map<String, Long> selectFromStore(String qualityName,
			String purchaseName,String place,Integer queryDate) {		
		return trackMapper.selectFromStore(qualityName, purchaseName, place,queryDate);
	}



	@Override
	public List<TrackQuery> selectByDate(TrackQuery tarckQuery) {
		return trackMapper.selectByDate(tarckQuery);
	}

}
