package com.r2r.road2ring.modules.trip;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.TripFacility.TripFacility;
import com.r2r.road2ring.modules.common.ResponseCode;
import com.r2r.road2ring.modules.common.ResponseMessage;
import com.r2r.road2ring.modules.common.ResponseView;
import com.r2r.road2ring.modules.common.UploadService;
import com.r2r.road2ring.modules.itinerary.Itinerary;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/trip")
public class TripAPIController {

  TripService tripService;

  UploadService uploadService;

  @Autowired
  public void setTripService(TripService tripService) {
    this.tripService = tripService;
  }

  @Autowired
  public void setUploadService(UploadService uploadService){
    this.uploadService = uploadService;
  }

  @RequestMapping(value = "/datatable", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public DataTablesOutput<Trip> datatable(@Valid DataTablesInput input,
      HttpServletRequest request) {

    return tripService.getDatatableContents(input);
//    return tripService.getAllTrip();
  }

  @RequestMapping(value = "/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<Trip> datatable(
      HttpServletRequest request) {

    return tripService.getAllTrip();
  }

  @RequestMapping(value = "/{tripId}/itinerary/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<Itinerary> datatableItinerary(@PathVariable("tripId") int id,
      HttpServletRequest request) {
    return tripService.getTripItinerary(id);
  }

  @RequestMapping(value = "/{tripId}/price-list/data", method = RequestMethod.GET)
  @JsonView(ResponseView.DetailedTrip.class)
  public List<TripPrice> datatablePriceList(@PathVariable("tripId") int id,
      HttpServletRequest request) {
    return tripService.getTripPriceList(id);
  }

  @PostMapping("/upload_trip/{typeImage}")
  public ResponseMessage uploadPhoto(@PathVariable String typeImage,@RequestParam("file") MultipartFile file) {
    System.out.println("@here we are right now");
    ResponseMessage responseMessage = new ResponseMessage();
    try {
      if (!file.isEmpty()) {
        responseMessage.setCode(ResponseCode.SUCCESS.getCode());
        responseMessage.setObject(uploadService.uploadImagePicture(file, typeImage));
      }
    }catch (IOException e){
      responseMessage.setMessage(e.getMessage());
      responseMessage.setCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }catch (FileSizeLimitExceededException e){
      responseMessage.setMessage(e.getMessage());
      responseMessage.setCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }
    return responseMessage;
  }

}
