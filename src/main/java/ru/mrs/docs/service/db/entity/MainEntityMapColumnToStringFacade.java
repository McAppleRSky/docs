package ru.mrs.docs.service.db.entity;

import org.apache.commons.lang3.NotImplementedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainEntityMapColumnToStringFacade implements Map<MainColumns, String>, IMainEntity{

    private IMainEntity entity;
    private Map<MainColumns, String> mainColumnToValue;
    public static final String DATE_FORMAT = "yyyy-MM-dd";

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.mainColumnToValue = mainColumnToValue;
        entity = new MainEntityImpl();
        try {
            entity.setId(
                    mainColumnToValue.get(MainColumns.ID)==null ? null :
                            Long.valueOf(
                                    mainColumnToValue.get(
                                            MainColumns.ID ) ) );
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        entity.setUrlInput(
                mainColumnToValue.get(
                        MainColumns.URL_INPUT) );
        entity.setGenOrgNumb(
                mainColumnToValue.get(
                        MainColumns.GEN_ORG_NUMB) );
        try {
            entity.setGenOrgDate(
                    mainColumnToValue.get(MainColumns.GEN_ORG_DATE)==null ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.GEN_ORG_DATE ) ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setOutputNumb(
                mainColumnToValue.get(
                        MainColumns.OUTPUT_NUMB ) );
        try {
            entity.setOutputDate(
                    mainColumnToValue.get(MainColumns.OUTPUT_DATE)==null? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.OUTPUT_DATE ) ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
            entity.setFromOwner(
                    mainColumnToValue.get(
                            MainColumns.FROM_OWNER ) );
        try{
            entity.setInputDate(
                    mainColumnToValue.get(MainColumns.INPUT_DATE)==null ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.INPUT_DATE ) ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setInputNumb(
                        mainColumnToValue.get(
                                MainColumns.INPUT_NUMB ) );
        entity.setWorker(
                        mainColumnToValue.get(
                                MainColumns.WORKER ) );
        try{
            entity.setHandPass(
                    mainColumnToValue.get(MainColumns.HAND_PASS)==null ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.HAND_PASS ) ) );
            entity.setAnswerComp(
                    mainColumnToValue.get(MainColumns.ANSWER_COMP)==null ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.ANSWER_COMP ) ) );
            entity.setAnswerDate(
                    mainColumnToValue.get(MainColumns.ANSWER_DATE)==null ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.ANSWER_DATE ) ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        return this.entity.getId();
    }

    @Override
    public String getUrlInput() {
        return this.entity.getUrlInput();
    }

    @Override
    public String getGenOrgNumb() {
        return this.entity.getGenOrgNumb();
    }

    @Override
    public Date getGenOrgDate() {
        return this.entity.getGenOrgDate();
    }

    @Override
    public String getOutputNumb() {
        return this.entity.getOutputNumb();
    }

    @Override
    public Date getOutputDate() {
        return this.entity.getOutputDate();
    }

    @Override
    public void setOutputDate(Date outputDate) {
        this.entity.setOutputDate(outputDate);
    }

    @Override
    public String getFromOwner() {
        return this.entity.getFromOwner();
    }

    @Override
    public Date getInputDate() {
        return this.entity.getInputDate();
    }

    @Override
    public String getInputNumb() {
        return this.entity.getInputNumb();
    }

    @Override
    public String getWorker() {
        return this.entity.getWorker();
    }

    @Override
    public Date getHandPass() {
        return this.entity.getHandPass();
    }

    @Override
    public Date getAnswerComp() {
        return this.entity.getAnswerComp();
    }

    @Override
    public Date getAnswerDate() {
        return this.entity.getAnswerDate();
    }

    @Override
    public String getAnswerNumb() {
        return this.entity.getAnswerNumb();
    }

    @Override
    public String getUrlOutput() {
        return this.entity.getUrlOutput();
    }

    @Override
    public String getNote() {
        return this.entity.getNote();
    }

    @Override
    public void setId(Long id) {
        this.entity.setId(id);
    }

    @Override
    public void setUrlInput(String urlInput) {
        this.entity.setUrlInput(urlInput);
    }

    @Override
    public void setGenOrgNumb(String genOrgNumb) {
        this.entity.setGenOrgNumb(genOrgNumb);
    }

    @Override
    public void setGenOrgDate(Date genOrgDate) {
        this.entity.setGenOrgDate(genOrgDate);
    }

    @Override
    public void setOutputNumb(String outputNumb) {
        this.entity.setOutputNumb(outputNumb);
    }

    @Override
    public void setFromOwner(String fromOwner) {
        this.entity.setFromOwner(fromOwner);
    }

    @Override
    public void setInputDate(Date inputDate) {
        this.entity.setInputDate(inputDate);
    }

    @Override
    public void setInputNumb(String inputNumb) {
        this.entity.setInputNumb(inputNumb);
    }

    @Override
    public void setWorker(String worker) {
        this.entity.setWorker(worker);
    }

    @Override
    public void setHandPass(Date handPass) {
        this.entity.setHandPass(handPass);
    }

    @Override
    public void setAnswerComp(Date AnswerComp) {
        this.entity.setAnswerComp(getAnswerComp());
    }

    @Override
    public void setAnswerDate(Date answerDate) {
        this.entity.setAnswerDate(answerDate);
    }

    @Override
    public void setAnswerNumb(String answerNumb) {
        this.entity.setAnswerNumb(answerNumb);
    }

    @Override
    public void setUrlOutput(String urlOutput) {
        this.entity.setUrlOutput(urlOutput);
    }

    @Override
    public void setNote(String note) {
        this.entity.setNote(note);
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
