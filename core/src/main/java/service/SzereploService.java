package service;

import filmespelda.exceptions.DateIsTooLate;
import filmespelda.exceptions.NoMatchingId;
import filmespelda.model.Szereplo;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

import java.util.Collection;
import java.util.UUID;

public interface SzereploService {
    public Collection<Szereplo> listAllSzereplo();
    public Szereplo getSzereplo(UUID id) throws NoMatchingId;
    public void addSzereplo(Szereplo szereplo) throws DateIsTooLate, InvalidValue;
    public void updateSzereplo(Szereplo szereplo) throws DateIsTooLate, InvalidValue;
    public void deleteSzereplo(Szereplo szereplo) throws NoMatchingId;
    public void deleteSzereplo(UUID id) throws NoMatchingId;
}
