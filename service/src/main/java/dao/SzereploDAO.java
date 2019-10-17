package dao;

import filmespelda.exceptions.NoMatchingId;
import filmespelda.model.Szereplo;

import java.util.Collection;
import java.util.UUID;

public interface SzereploDAO {
    public void createSzereplo(Szereplo szereplo);
    public Collection<Szereplo> readAllSzereplo();
    public void updateSzereplo(Szereplo szereplo);
    public void deleteSzereplo(Szereplo szereplo);
    public Szereplo readSzereplo(UUID id) throws NoMatchingId;
}
