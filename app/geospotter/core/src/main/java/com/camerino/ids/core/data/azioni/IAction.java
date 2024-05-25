package com.camerino.ids.core.data.azioni;

public interface IAction<E,R> {
    E getOldData();
    E getNewData();
    void SetStatus(R newStatus);
}
