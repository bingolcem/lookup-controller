package com.hemosoft.lookup;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLookupDataSource implements ILookupDataSource {

    @Autowired
    LookupQuerySearch lookupQuerySearch;

    @Autowired
    IDsUtils dsUtils;

    LookupQuery params;


    @Override
    public void setParams(LookupQuery params) {
        this.params = params;
    }

    @Override
    public LookupQuery getParams() {
        return params;
    }

    public Object getValue(String key){
        return getParams().getParameters().get(key);

    }

    boolean isElement(String entityName ) {
        return entityName.startsWith("L_") || entityName.startsWith("@");
    }

    @Override
    public List<LookupItem> getItems() {
        List<LookupItem> all = Collections.emptyList();
        // Element ise
        if (isElement(params.getEntityName())) {
            // if (params.getParentIdAsLong() == 0) {
            all = dsUtils.getElementRefs(params.getEntityName(),"");

        } else if(getParams().isEnum)
        {
        all=lookupQuerySearch.getEnumLookupItems(params);
        }
        else { // Entity ise

            Class<?> entityClass;
            try {
                entityClass = Class.forName(params.getEntityName());
                all = lookupQuerySearch.getItems(entityClass, params);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return all;

    }

    @Override
    public List<LookupItem> postProcessItems(List<LookupItem> input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postProcessItems'");
    }

}
