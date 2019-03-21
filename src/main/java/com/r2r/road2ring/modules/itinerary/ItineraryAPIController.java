package com.r2r.road2ring.modules.itinerary;

import com.fasterxml.jackson.annotation.JsonView;
import com.r2r.road2ring.modules.common.ResponseView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/itinerary")
public class ItineraryAPIController {

  ItineraryService itineraryService;

  @Autowired
  public void setItineraryService(ItineraryService itineraryService){
    this.itineraryService = itineraryService;
  }

  @RequestMapping(value = "/datatable/{tripId}", method = RequestMethod.GET)
  public DataTablesOutput<Itinerary> datatable(@Valid DataTablesInput input,@PathVariable Integer scheduleId) {
    return itineraryService.getDatatableItinerary(input, scheduleId);
  }

}
