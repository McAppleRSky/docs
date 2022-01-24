package ru.mrs.docs.service.db.entity;

import java.util.Date;

public interface IMainEntity {

    Long getId();

    void setId(Long id);

    String getUrlInput();

    void setUrlInput(String urlInput);

    String getGenOrgNumb();

    void setGenOrgNumb(String genOrgNumb);

    Date getGenOrgDate();

    void setGenOrgDate(Date genOrgDate);

    String getOutputNumb();

    void setOutputNumb(String outputNumb);

    Date getOutputDate();

    void setOutputDate(Date outputDate);

    String getFromOwner();

    void setFromOwner(String fromOwner);

    Date getInputDate();

    void setInputDate(Date inputDate);

    String getInputNumb();

    void setInputNumb(String inputNumb);

    String getWorker();

    void setWorker(String worker);

    Date getHandPass();

    void setHandPass(Date handPass);

    Date getAnswerComp();

    void setAnswerComp(Date answerComp);

    Date getAnswerDate();

    void setAnswerDate(Date answerDate);

    String getAnswerNumb();

    void setAnswerNumb(String answerNumb);

    String getUrlOutput();

    void setUrlOutput(String urlOutput);

    String getNote();

    void setNote(String note);

}
