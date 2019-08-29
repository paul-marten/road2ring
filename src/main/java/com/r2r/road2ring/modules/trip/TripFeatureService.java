package com.r2r.road2ring.modules.trip;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class TripFeatureService {

  @Autowired
  TripFeatureRepository tripFeatureRepository;

  @Autowired
  TripService tripService;

  @Autowired
  TripViewService tripViewService;


  public List<TripFeature> bindTripToTripFeature(){
    List<TripView> trips = (List<TripView>) tripViewService.getListTripView(0,5).getObject();
    TripFeature item;
    List<TripFeature> result = new ArrayList<TripFeature>();
    for(TripView trip : trips){
      item = new TripFeature();
      item.setId(1);
      item.setCover(trip.getCoverLandscape());
      item.setIcon(trip.getIconCover());
      item.setTitle(trip.getTitle());
      item.setLink("#");
      result.add(item);

    }

    return result;

  }

  public TripFeature save(TripFeature saved){
    if(saved.getId() == null){
      saved.setPublishStatus(PublishedStatus.UNPUBLISHED);
    }
    return tripFeatureRepository.save(saved);
  }

  public List<TripFeature> getTripFeature(){
    return tripFeatureRepository.findAllByPublishStatusOrderByUpdatedDesc(PublishedStatus.PUBLISHED);
  }

  public TripFeature updatePublishStatus(TripFeature updated){
    updated = tripFeatureRepository.findOne(updated.getId());
    if(updated.getPublishStatus().equals(PublishedStatus.PUBLISHED)){
      updated.setPublishStatus(PublishedStatus.UNPUBLISHED);
    }else{
      updated.setPublishStatus(PublishedStatus.PUBLISHED);
    }
    return save(updated);
  }

  public List<TripFeature> getDatatableContents() {
    return tripFeatureRepository.findAll();
  }

  public TripFeature getTripFeatureById(int id) {
    return tripFeatureRepository.findOne(id);
  }
}
