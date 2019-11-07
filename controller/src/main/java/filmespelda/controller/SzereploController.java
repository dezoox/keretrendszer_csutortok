package filmespelda.controller;

import filmespelda.exceptions.DateIsTooLate;
import filmespelda.model.Szereplo;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SzereploService;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class SzereploController {

    SzereploService service;

    public SzereploController(@Autowired SzereploService service) {
        this.service = service;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String showSzereploCount(){
        return String.valueOf(service.listAllSzereplo().size());
    }

    @RequestMapping(value = "/szereplok", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Szereplo> showAllSzereplo(){
        return service.listAllSzereplo();
    }

    @RequestMapping(value = "/addSzereplo", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addSzereplo(@RequestBody Szereplo szereplo) throws DateIsTooLate, InvalidValue {
        service.addSzereplo(szereplo);
        return"Új szereplő hozzáadva"+szereplo.getId();
    }

    @RequestMapping(value = "/fiatalkoruszereplok", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Szereplo> showfiatalkoruak(){
        Collection<Szereplo> szereplok = service.listAllSzereplo();
        Collection<Szereplo> fiatalok = new ArrayList<>();
        for(Szereplo sz: szereplok) {
            if (sz.getSzuletesi_datum().isAfter(LocalDate.now().minusYears(18))) {
                fiatalok.add(sz);
            }
        }
        return fiatalok;
    }
}
