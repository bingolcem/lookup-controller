package com.hemosoft.lookup;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupQueryResult {
    public ArrayList<LookupItem> items;
    public int offset;
    public String errorMessage;
}
