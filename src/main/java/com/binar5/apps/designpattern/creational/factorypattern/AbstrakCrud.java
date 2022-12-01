package com.binar5.apps.designpattern.creational.factorypattern;

import java.util.Map;

public abstract  class AbstrakCrud {
    public abstract  Map save(Object req);
    public abstract  Map update(Object req);
    public abstract  Map delete(Object req);
    public abstract  Map list(Object req);
}
