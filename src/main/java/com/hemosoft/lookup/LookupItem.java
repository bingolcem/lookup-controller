package com.hemosoft.lookup;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupItem {
    Long id;
    String label;
    Long parentId;
    List<LookupItem> children= new ArrayList<>();
    String enumType;
    

      public LookupItem(Long id, String description, Long parentId) {
        this.id = id;
        this.label = description;
        this.parentId = parentId;
    }


     /**
     * Given a flat list of elements, builds a tree (if source is a tree),
     * otherwise returns a list. For leafs chidren field is null.
     *
     * @param elements
     * @param parent
     */
    protected void buildHierarchy(List<LookupItem> elements, LookupItem parent) {
        if (elements == null) {
            return;
        }
        // 20160730 Kök yoksa hiyerarşiyi yok say ve düz liste dön
        // find children and set
        List<LookupItem> children = elements.stream()
                .filter(e -> Objects.equals(e.parentId, parent.getId())).collect(Collectors.toList());
        if (children.size() > 0) {
            parent.setChildren(children);
            // dive
            if (children.size() > 0) {
                children.forEach(c -> {
                    buildHierarchy(elements, c);
                });
            }
        }
    }

    protected void buildHierarchySafe(List<LookupItem> elements, LookupItem parent) {
        // 20160730 Kök yoksa hiyerarşiyi yok say ve düz liste dön
        // find children and set
        List<LookupItem> roots = elements.stream()
                .filter(e -> Objects.equals(e.parentId, null)).collect(Collectors.toList());
        if (roots.isEmpty()) {
            parent.setChildren(elements);
        } else {
            buildHierarchy(elements, parent);
        }
    }

    private List<LookupItem> arrangeElements(List<Object[]> results, LookupItem root) {
        List<LookupItem> value = new ArrayList<>();
        for (Object[] row : results) {
            value.add(new LookupItem((Long) row[0], (String) row[1], (Long) row[2]));
        }
        buildHierarchySafe(value, root);
        return root.getChildren();
    }

    public List<LookupItem> arrangeElements(List<Object[]> list) {
        return arrangeElements(list, this);
    }

    //20200926 buildHierarchy yerine buildHierarchySafe çağrıldı
    public void buildHierarchy(List<LookupItem> all) {
//        buildHierarchy(all, this);
        buildHierarchySafe(all, this);
    }

    // /**
    //  * Add child.
    //  *
    //  * @param childName
    //  * @param object
    //  * @param previousId
    //  * @return Added child.
    //  */
    // public LookupItem add(String childName, Object object, Long previousId) {
    //     if (children == null) {
    //         children = new ArrayList<>();
    //     }
    //     LookupItem addedEntity = new LookupItem(previousId + 1, childName, this.getId());
    //     addedEntity.setData(object);
    //     children.add(addedEntity);
    //     return addedEntity;
    // }
}
