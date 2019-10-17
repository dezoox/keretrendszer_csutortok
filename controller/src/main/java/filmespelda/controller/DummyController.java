package filmespelda.controller;

import filmespelda.exceptions.DateIsTooLate;
import filmespelda.model.Szereplo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class DummyController {

    @RequestMapping(value = "/cica")
    public void dummy(){
        System.out.println("CICA");
    }

    @RequestMapping(value = "/dummy")
    @ResponseBody
    public Szereplo returnWithSzereplo() throws DateIsTooLate {
        Szereplo szereplo = new Szereplo();
        szereplo.setSzuletesi_datum(LocalDate.of(1987,12,19));
        szereplo.setKarakter_neve("Don Juan");
        szereplo.setId(UUID.randomUUID());
        szereplo.setKeresztnev("Zoltán");
        szereplo.setVezeteknev("Mucsi");
        return szereplo;
    }

    @RequestMapping(value = "/dummyWrong")
    @ResponseBody
    public void handleException(){
        System.out.println("HEEE");
    }
}
