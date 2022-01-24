package ru.mrs.docs.service.db.entity;


import java.util.Date;

//@SuppressWarnings("UnusedDeclaration")
//@Accessors(chain = true)
public class MainEntityImpl implements IMainEntity{

    private Long
            id;
    private String
            urlInput;
    private String
            genOrgNumb;
    private Date
            genOrgDate;
    private String
            outputNumb;
    private Date
            outputDate;
    private String
            fromOwner;
    private Date
            inputDate;
    private String
            inputNumb;
    private String
            worker;
    private Date
            handPass;
    private Date
            AnswerComp;
    private Date
            answerDate;
    private String
            answerNumb;
    private String
            urlOutput;
    private String
            note;

    public MainEntityImpl() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUrlInput() {
        return urlInput;
    }

    @Override
    public void setUrlInput(String urlInput) {
        this.urlInput = urlInput;
    }

    @Override
    public String getGenOrgNumb() {
        return genOrgNumb;
    }

    @Override
    public void setGenOrgNumb(String genOrgNumb) {
        this.genOrgNumb = genOrgNumb;
    }

    @Override
    public Date getGenOrgDate() {
        return genOrgDate;
    }

    @Override
    public void setGenOrgDate(Date genOrgDate) {
        this.genOrgDate = genOrgDate;
    }

    @Override
    public String getOutputNumb() {
        return outputNumb;
    }

    @Override
    public void setOutputNumb(String outputNumb) {
        this.outputNumb = outputNumb;
    }

    @Override
    public Date getOutputDate() {
        return outputDate;
    }

    @Override
    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
    }

    @Override
    public String getFromOwner() {
        return fromOwner;
    }

    @Override
    public void setFromOwner(String fromOwner) {
        this.fromOwner = fromOwner;
    }

    @Override
    public Date getInputDate() {
        return inputDate;
    }

    @Override
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public String getInputNumb() {
        return inputNumb;
    }

    @Override
    public void setInputNumb(String inputNumb) {
        this.inputNumb = inputNumb;
    }

    @Override
    public String getWorker() {
        return worker;
    }

    @Override
    public void setWorker(String worker) {
        this.worker = worker;
    }

    @Override
    public Date getHandPass() {
        return handPass;
    }

    @Override
    public void setHandPass(Date handPass) {
        this.handPass = handPass;
    }

    @Override
    public Date getAnswerComp() {
        return AnswerComp;
    }

    @Override
    public void setAnswerComp(Date answerComp) {
        AnswerComp = answerComp;
    }

    @Override
    public Date getAnswerDate() {
        return answerDate;
    }

    @Override
    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String getAnswerNumb() {
        return answerNumb;
    }

    @Override
    public void setAnswerNumb(String answerNumb) {
        this.answerNumb = answerNumb;
    }

    @Override
    public String getUrlOutput() {
        return urlOutput;
    }

    @Override
    public void setUrlOutput(String urlOutput) {
        this.urlOutput = urlOutput;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

}
