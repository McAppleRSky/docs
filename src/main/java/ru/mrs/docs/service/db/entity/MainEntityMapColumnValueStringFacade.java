package ru.mrs.docs.service.db.entity;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainEntityMapColumnValueStringFacade implements Map<MainColumns, String> {

    private MainEntity entity;
    private Map<MainColumns, String> mainColumnToValues;

    public MainEntityMapColumnValueStringFacade(MainEntity entity) {
        this.entity = entity;
        mainColumnToValues = new HashMap<>();
        Long id = entity.getId();
        mainColumnToValues.put(MainColumns.ID, id==null ? null : id.toString());
        mainColumnToValues.put(MainColumns.URL_INPUT, entity.getUrlInput());
        mainColumnToValues.put(MainColumns.GEN_ORG_NUMB, entity.getGenOrgNumb());
//        mainColumnToValues.put(MainColumns.GEN_ORG_DATE,entity.getGenOrgDate());
        mainColumnToValues.put(MainColumns.OUTPUT_NUMB, entity.getOutputNumb());
//        mainColumnToValues.put(MainColumns.OUTPUT_DATE, entity.getOutputDate());
        mainColumnToValues.put(MainColumns.FROM_OWNER, entity.getFromOwner());
//        mainColumnToValues.put(MainColumns.INPUT_DATE,entity.getInputDate());
        mainColumnToValues.put(MainColumns.INPUT_NUMB, entity.getInputNumb());
        mainColumnToValues.put(MainColumns.WORKER, entity.getWorker());
//        mainColumnToValues.put(MainColumns.HAND_PASS,entity.getHandPass());
//        mainColumnToValues.put(MainColumns.ANSWER_COMP,entity.getAnswerComp());
//        mainColumnToValues.put(MainColumns.ANSWER_DATE,entity.getAnswerDate());
        mainColumnToValues.put(MainColumns.ANSWER_NUMB, entity.getAnswerNumb());
        mainColumnToValues.put(MainColumns.URL_OUTPUT, entity.getUrlOutput());
        mainColumnToValues.put(MainColumns.NOTE, entity.getNote());
    }

    @Override
    public int size() {
        throw new NotImplementedException("int size()");
    }

    @Override
    public boolean isEmpty() {
        throw new NotImplementedException("boolean isEmpty()");
    }

    @Override
    public boolean containsKey(Object key) {
        throw new NotImplementedException("boolean containsKey(Object key)");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NotImplementedException("boolean containsValue(Object value)");
    }

    @Override
    public String get(Object key) {
//        throw new NotImplementedException("String get(Object key)");
        String result = mainColumnToValues.get(key);
        return result==null ? "NULL" : "'" + result + "'";
    }

    @Override
    public String put(MainColumns key, String value) {
//        throw new NotImplementedException("String put(MainColumns key, String value)");
        mainColumnToValues.put(key, value);
        return null;
    }

    @Override
    public String remove(Object key) {
        throw new NotImplementedException("String remove(Object key)");
    }

    @Override
    public void putAll(Map<? extends MainColumns, ? extends String> m) {
        throw new NotImplementedException("void putAll(Map<? extends MainColumns, ? extends String> m)");
    }

    @Override
    public void clear() {
        throw new NotImplementedException("void clear()");
    }

    @Override
    public Set<MainColumns> keySet() {
        throw new NotImplementedException("Set<MainColumns> keySet()");
    }

    @Override
    public Collection<String> values() {
        throw new NotImplementedException("Collection<String> values()");
    }

    @Override
    public Set<Entry<MainColumns, String>> entrySet() {
        throw new NotImplementedException("Set<Entry<MainColumns, String>> entrySet()");
    }

}
