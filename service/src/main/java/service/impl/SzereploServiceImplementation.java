package service.impl;

import dao.SzereploDAO;
import filmespelda.exceptions.DateIsTooLate;
import filmespelda.exceptions.NoMatchingId;
import filmespelda.model.Szereplo;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import service.SzereploService;

import java.util.Collection;
import java.util.UUID;

public class SzereploServiceImplementation implements SzereploService {

    SzereploDAO dao;

    public SzereploServiceImplementation(SzereploDAO dao) {
        this.dao = dao;
    }

    public Collection<Szereplo> listAllSzereplo() {
        return dao.readAllSzereplo();
    }

    public Szereplo getSzereplo(UUID id) throws NoMatchingId {
        return dao.readSzereplo(id);
    }

    public void addSzereplo(Szereplo szereplo) throws DateIsTooLate, InvalidValue {
        dao.createSzereplo(szereplo);
    }

    public void updateSzereplo(Szereplo szereplo) throws DateIsTooLate, InvalidValue {
        dao.updateSzereplo(szereplo);
    }

    public void deleteSzereplo(Szereplo szereplo) throws NoMatchingId {
        dao.deleteSzereplo(szereplo);
    }

    public void deleteSzereplo(UUID id) throws NoMatchingId {
        Szereplo result = dao.readSzereplo(id);
        deleteSzereplo(result);
    }
}
