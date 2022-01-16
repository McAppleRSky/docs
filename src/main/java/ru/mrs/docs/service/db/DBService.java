package ru.mrs.docs.service.db;

import ru.mrs.docs.service.db.dataSet.OldTableDataSet;

import java.util.List;

public interface DBService {

    List<OldTableDataSet> allDocuments();
}
