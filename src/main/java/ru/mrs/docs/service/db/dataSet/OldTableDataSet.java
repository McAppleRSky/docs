package ru.mrs.docs.service.db.dataSet;
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
            urlSedInput;
    private String
            generalOrgNumb;
    private Date
            generalOrgDate;
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
            AnswerComplete;
    private Date
            answerDate;
    private String
            answerNumb;
    private String
            urlSedOutput;
    private String
            note;

}
