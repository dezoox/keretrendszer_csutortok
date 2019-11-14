package dao.json.impl.DAO;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.SzereploDAO;
import filmespelda.exceptions.NoMatchingId;
import filmespelda.model.Szereplo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class DAOJSON implements SzereploDAO {
    File jsonFile;
    ObjectMapper mapper;

    public DAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        if (!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public DAOJSON(String jsonFilePath) {
        this.jsonFile = new File(jsonFilePath);
        if(!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFilePath);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }


    public void createSzereplo(Szereplo szereplo) {
        Collection<Szereplo> szereplok = readAllSzereplo();
        szereplok.add(szereplo);
        try {
            mapper.writeValue(jsonFile, szereplok);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Szereplo> readAllSzereplo() {
        Collection<Szereplo> result = new ArrayList<Szereplo>();
        try {
            result = mapper.readValue(jsonFile, new TypeReference<ArrayList<Szereplo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateSzereplo(Szereplo szereplo) {

    }

    public void deleteSzereplo(Szereplo szereplo) throws NoMatchingId {

        Collection<Szereplo> szereplok = readAllSzereplo();
        try {
            Szereplo torlendo = readSzereplo(szereplo.getId());
            szereplok.remove(torlendo);
            mapper.writeValue(jsonFile, szereplo);
        } catch (NoMatchingId noMatchingId) {
            throw noMatchingId;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Szereplo readSzereplo(UUID id) throws NoMatchingId {
        Collection<Szereplo> szereplok = readAllSzereplo();
        for(Szereplo sz: szereplok){
            if(sz.getId().toString().equalsIgnoreCase(id.toString())){
                return sz;
            }
        }
        throw new NoMatchingId();
    }
}
