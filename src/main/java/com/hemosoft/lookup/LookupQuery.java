package com.hemosoft.lookup;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupQuery {
    
    public String dataSource; //ILookupDatasource
    public String entityName; //element kodu veya datasource canonical name
    public String searchTerm;
    public boolean isEnum;
    public int offset;
    public int pageSize;
    Map<String, Object> parameters = new HashMap<>();

    public void put(String key, Object value){
        parameters.put(key, value);
    }
}
