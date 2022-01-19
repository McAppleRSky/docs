package ru.mrs.docs.service.db;

import ru.mrs.docs.service.db.entity.OldTableColumns;
import ru.mrs.docs.service.db.entity.OldTableDataSet;

import java.util.List;
import java.util.Map;

public interface DBService {

    List<OldTableDataSet> allDocs();

    int createDocsOldTable(Map<OldTableColumns, String> parameterMap);

    int updateOldTable(Map<OldTableColumns, String> parameterMap);

}
