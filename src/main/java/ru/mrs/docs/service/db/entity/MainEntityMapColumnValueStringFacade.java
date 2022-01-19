package ru.mrs.docs.service.db.entity;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainEntityMapColumnValueStringFacade implements Map<MainColumns, String>{

    private MainEntity entity;
    private Map<MainColumns, String> mainColumnToValues;

    public MainEntityMapColumnValueStringFacade(MainEntity entity) {
        this.entity = entity;
        mainColumnToValues = new HashMap<>();
        mainColumnToValues.put(MainColumns.ID, entity.getId().toString());
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
        mainColumnToValues.put(MainColumns.ANSWER_NUMB,entity.getAnswerNumb());
        mainColumnToValues.put(MainColumns.URL_OUTPUT, entity.getUrlOutput());
        mainColumnToValues.put(MainColumns.NOTE, entity.getNote());
    }

    @Override
    public int size() {
        throw new NotImplementedException("");
    }

    @Override
    public boolean isEmpty() {
        throw new NotImplementedException("");
    }

    @Override
    public boolean containsKey(Object key) {
        throw new NotImplementedException("");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NotImplementedException("");
    }

    @Override
    public String get(Object key) {
        throw new NotImplementedException("");
    }

    @Override
    public String put(MainColumns key, String value) {
        throw new NotImplementedException("");
    }

    @Override
    public String remove(Object key) {
        throw new NotImplementedException("");
    }

    @Override
    public void putAll(Map<? extends MainColumns, ? extends String> m) {
        throw new NotImplementedException("");
    }

    @Override
    public void clear() {
        throw new NotImplementedException("");
    }

    @Override
    public Set<MainColumns> keySet() {
        throw new NotImplementedException("");
    }

    @Override
    public Collection<String> values() {
        throw new NotImplementedException("");
    }

    @Override
    public Set<Entry<MainColumns, String>> entrySet() {
        throw new NotImplementedException("");
    }

    /*@Override
    public Long getId() {
        throw new NotImplementedException("Long getId()");
    }

    @Override
    public String getUrlInput() {
        throw new NotImplementedException("String getUrlInput()");
    }

    @Override
    public String getGenOrgNumb() {
        throw new NotImplementedException("String getGenOrgNumb()");
    }

    @Override
    public Date getGenOrgDate() {
        throw new NotImplementedException("Date getGenOrgDate()");
    }

    @Override
    public String getOutputNumb() {
        throw new NotImplementedException("String getOutputNumb()");
    }

    @Override
    public Date getOutputDate() {
        throw new NotImplementedException("Date getOutputDate()");
    }

    @Override
    public String getFromOwner() {
        throw new NotImplementedException("String getFromOwner()");
    }

    @Override
    public Date getInputDate() {
        throw new NotImplementedException("Date getInputDate()");
    }

    @Override
    public String getInputNumb() {
        throw new NotImplementedException("String getInputNumb()");
    }

    @Override
    public String getWorker() {
        throw new NotImplementedException("String getWorker()");
    }

    @Override
    public Date getHandPass() {
        throw new NotImplementedException("Date getHandPass()");
    }

    @Override
    public Date getAnswerComp() {
        throw new NotImplementedException("Date getAnswerComp()");
    }

    @Override
    public Date getAnswerDate() {
        throw new NotImplementedException("Date getAnswerDate()");
    }

    @Override
    public String getAnswerNumb() {
        throw new NotImplementedException("String getAnswerNumb()");
    }

    @Override
    public String getUrlOutput() {
        throw new NotImplementedException("String getUrlOutput()");
    }

    @Override
    public String getNote() {
        throw new NotImplementedException("String getNote()");
    }

    @Override
    public MainEntity setId(Long id) {
        throw new NotImplementedException("MainEntity setId(Long id)");
    }

    @Override
    public MainEntity setUrlInput(String urlInput) {
        throw new NotImplementedException("MainEntity setUrlInput(String urlInput)");
    }

    @Override
    public MainEntity setGenOrgNumb(String genOrgNumb) {
        throw new NotImplementedException("MainEntity setGenOrgNumb(String genOrgNumb)");
    }

    @Override
    public MainEntity setGenOrgDate(Date genOrgDate) {
        throw new NotImplementedException("MainEntity setGenOrgDate(Date genOrgDate)");
    }

    @Override
    public MainEntity setOutputNumb(String outputNumb) {
        throw new NotImplementedException("MainEntity setOutputNumb(String outputNumb)");
    }

    @Override
    public MainEntity setOutputDate(Date outputDate) {
        throw new NotImplementedException("MainEntity setOutputDate(Date outputDate)");
    }

    @Override
    public MainEntity setFromOwner(String fromOwner) {
        throw new NotImplementedException("MainEntity setFromOwner(String fromOwner)");
    }

    @Override
    public MainEntity setInputDate(Date inputDate) {
        throw new NotImplementedException("MainEntity setInputDate(Date inputDate)");
    }

    @Override
    public MainEntity setInputNumb(String inputNumb) {
        throw new NotImplementedException("MainEntity setInputNumb(String inputNumb)");
    }

    @Override
    public MainEntity setWorker(String worker) {
        throw new NotImplementedException("MainEntity setWorker(String worker)");
    }

    @Override
    public MainEntity setHandPass(Date handPass) {
        throw new NotImplementedException("MainEntity setHandPass(Date handPass)");
    }

    @Override
    public MainEntity setAnswerComp(Date AnswerComp) {
        throw new NotImplementedException("MainEntity setAnswerComp(Date AnswerComp)");
    }

    @Override
    public MainEntity setAnswerDate(Date answerDate) {
        throw new NotImplementedException("MainEntity setAnswerDate(Date answerDate)");
    }

    @Override
    public MainEntity setAnswerNumb(String answerNumb) {
        throw new NotImplementedException("MainEntity setAnswerNumb(String answerNumb)");
    }

    @Override
    public MainEntity setUrlOutput(String urlOutput) {
        throw new NotImplementedException("MainEntity setUrlOutput(String urlOutput)");
    }

    @Override
    public MainEntity setNote(String note) {
        throw new NotImplementedException("MainEntity setNote(String note)");
    }*/

}
