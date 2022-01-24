package ru.mrs.docs.service.db.entity;

import org.apache.commons.lang3.NotImplementedException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainEntityMapColumnToStringFacade implements Map<MainColumns, String>, IMainEntity{

    private IMainEntity entity;
    private Map<MainColumns, String> mainColumnToValue;
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public MainEntityMapColumnToStringFacade(IMainEntity entity) {
        this.entity = entity;
        mainColumnToValue = new HashMap<>();
        if ( entity.getId() != null ) {
            mainColumnToValue.put(MainColumns.ID, entity.getId().toString());
        }
//        Long id = entity.getId();
//        mainColumnToValues.put(MainColumns.ID, id==null ? null : id.toString());
        mainColumnToValue.put(MainColumns.URL_INPUT, entity.getUrlInput());
        mainColumnToValue.put(MainColumns.GEN_ORG_NUMB, entity.getGenOrgNumb());
        mainColumnToValue.put(
                MainColumns.GEN_ORG_DATE,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getGenOrgDate() ) );
        mainColumnToValue.put(MainColumns.OUTPUT_NUMB, entity.getOutputNumb());
        mainColumnToValue.put(
                MainColumns.OUTPUT_DATE,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getOutputDate() ) );
        mainColumnToValue.put(MainColumns.FROM_OWNER, entity.getFromOwner());
        mainColumnToValue.put(
                MainColumns.INPUT_DATE,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getInputDate() ) );
        mainColumnToValue.put(MainColumns.INPUT_NUMB, entity.getInputNumb());
        mainColumnToValue.put(MainColumns.WORKER, entity.getWorker());
        mainColumnToValue.put(
                MainColumns.HAND_PASS,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getHandPass() ) );
        mainColumnToValue.put(
                MainColumns.ANSWER_COMP,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getAnswerComp() ) );
        mainColumnToValue.put(
                MainColumns.ANSWER_DATE,
                new SimpleDateFormat(DATE_FORMAT)
                        .format(
                                entity.getAnswerDate() ) );
        mainColumnToValue.put(MainColumns.ANSWER_NUMB, entity.getAnswerNumb());
        mainColumnToValue.put(MainColumns.URL_OUTPUT, entity.getUrlOutput());
        mainColumnToValue.put(MainColumns.NOTE, entity.getNote());
    }

    public MainEntityMapColumnToStringFacade(Map<MainColumns, String> mainColumnToValue) {
        this.mainColumnToValue = mainColumnToValue;
        entity = new MainEntityImpl();
        entity.setId(
                        Long.valueOf(
                                mainColumnToValue.get(
                                        MainColumns.ID ) ) );

        entity.setUrlInput(
                        mainColumnToValue.get(
                                MainColumns.URL_INPUT) );
        entity.setGenOrgNumb(
                        mainColumnToValue.get(
                                MainColumns.GEN_ORG_NUMB) );
        entity.setGenOrgDate(
                        mainColumnToValue.get(
                                MainColumns.GEN_ORG_DATE ) );
        entity.setOutputNumb(
                        mainColumnToValue.get(
                                MainColumns.OUTPUT_NUMB ) );
        entity.setOutputDate(
                        mainColumnToValue.get(
                                MainColumns.OUTPUT_DATE ) );
        entity.setFromOwner(
                        mainColumnToValue.get(
                                MainColumns.FROM_OWNER ) );
        entity.setInputDate(
                        mainColumnToValue.get(
                                MainColumns.INPUT_DATE ) );
        entity.setInputNumb(
                        mainColumnToValue.get(
                                MainColumns.INPUT_NUMB ) );
        entity.setWorker(
                        mainColumnToValue.get(
                                MainColumns.WORKER ) );
        entity.setHandPass(
                        mainColumnToValue.get(
                                MainColumns.HAND_PASS ) );
        entity.setAnswerComp(
                        mainColumnToValue.get(
                                MainColumns.ANSWER_COMP ) );
        entity.setAnswerDate(
                        mainColumnToValue.get(
                                MainColumns.ANSWER_DATE ) );
        entity.setAnswerNumb(
                        mainColumnToValue.get(
                                MainColumns.ANSWER_NUMB ) );
        entity.setUrlOutput(
                        mainColumnToValue.get(
                                MainColumns.URL_OUTPUT ) );
        entity.setNote(
                        mainColumnToValue.get(
                                MainColumns.NOTE ) );
    }

    public MainEntityMapColumnToStringFacade() {
        throw new NotImplementedException();
    }

    @Override
    public Long getId() {
        throw new NotImplementedException();
    }

    @Override
    public String getUrlInput() {
        throw new NotImplementedException();
    }

    @Override
    public String getGenOrgNumb() {
        throw new NotImplementedException();
    }

    @Override
    public Date getGenOrgDate() {
        throw new NotImplementedException();
    }

    @Override
    public String getOutputNumb() {
        throw new NotImplementedException();
    }

    @Override
    public Date getOutputDate() {
        throw new NotImplementedException();
    }

    @Override
    public String getFromOwner() {
        throw new NotImplementedException();
    }

    @Override
    public Date getInputDate() {
        throw new NotImplementedException();
    }

    @Override
    public String getInputNumb() {
        throw new NotImplementedException();
    }

    @Override
    public String getWorker() {
        throw new NotImplementedException();
    }

    @Override
    public Date getHandPass() {
        throw new NotImplementedException();
    }

    @Override
    public Date getAnswerComp() {
        throw new NotImplementedException();
    }

    @Override
    public Date getAnswerDate() {
        throw new NotImplementedException();
    }

    @Override
    public String getAnswerNumb() {
        throw new NotImplementedException();
    }

    @Override
    public String getUrlOutput() {
        throw new NotImplementedException();
    }

    @Override
    public String getNote() {
        throw new NotImplementedException();
    }

    @Override
    public void setId(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public void setUrlInput(String urlInput) {
        throw new NotImplementedException();
    }

    @Override
    public void setGenOrgNumb(String genOrgNumb) {
        throw new NotImplementedException();
    }

    @Override
    public void setGenOrgDate(Date genOrgDate) {
        throw new NotImplementedException();
    }

    @Override
    public void setOutputNumb(String outputNumb) {
        throw new NotImplementedException();
    }

    @Override
    public void setOutputDate(Date outputDate) {
        throw new NotImplementedException();
    }

    @Override
    public void setFromOwner(String fromOwner) {
        throw new NotImplementedException();
    }

    @Override
    public void setInputDate(Date inputDate) {
        throw new NotImplementedException();
    }

    @Override
    public void setInputNumb(String inputNumb) {
        throw new NotImplementedException();
    }

    @Override
    public void setWorker(String worker) {
        throw new NotImplementedException();
    }

    @Override
    public void setHandPass(Date handPass) {
        throw new NotImplementedException();
    }

    @Override
    public void setAnswerComp(Date AnswerComp) {
        throw new NotImplementedException();
    }

    @Override
    public void setAnswerDate(Date answerDate) {
        throw new NotImplementedException();
    }

    @Override
    public void setAnswerNumb(String answerNumb) {
        throw new NotImplementedException();
    }

    @Override
    public void setUrlOutput(String urlOutput) {
        throw new NotImplementedException();
    }

    @Override
    public void setNote(String note) {
        throw new NotImplementedException();
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
        Object result = mainColumnToValue.get(key);
        return result==null ? "NULL" : "'" + result + "'";
    }

    @Override
    public String put(MainColumns key, String value) {
//        throw new NotImplementedException("String put(MainColumns key, String value)");
        mainColumnToValue.put(key, value);
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
