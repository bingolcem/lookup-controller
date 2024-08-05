package com.hemosoft.lookup;

import java.util.List;

public interface LookupQuerySearch {
    List<LookupItem> getItems(Class entityClass, LookupQuery lookupQuery);

    List<LookupItem> getElementRefs(LookupQuery lookupQuery);

    List<LookupItem> getEnumLookupItems(LookupQuery lookupQuery);

}
