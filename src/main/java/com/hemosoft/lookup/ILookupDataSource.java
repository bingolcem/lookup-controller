package com.hemosoft.lookup;

import java.util.List;

public interface ILookupDataSource {

    void setParams(LookupQuery params);
    LookupQuery getParams();

    List<LookupItem> getItems();

    List<LookupItem> postProcessItems(List<LookupItem> input);

    /**
     * Eğer veri aynı veri kaynağı sunucudaki bağlama göre farlı listeler dönüyorsa
     * bu verinin doğru bir şekilde ayrıştırılabilmesi için her duruma göre bir
     * sürüm numarası verilmelidir.
     * 
     * Eğer veri sunucudaki bağlama göre değişmiyorsa bu metodu yeniden yazmak
     * gerekmez.
     * 
     * @return
     */
    default String getLookupVersion() {
        return "";
    }
}
