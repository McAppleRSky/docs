package ru.mrs.docs.service.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

//@SuppressWarnings("UnusedDeclaration")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OldTableDataSet {

    private Integer
            id;
    private String
            urlIn;
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
            urlOut;
    private String
            note;

}
