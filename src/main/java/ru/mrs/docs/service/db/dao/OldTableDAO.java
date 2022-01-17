package ru.mrs.docs.service.db.dao;

import ru.mrs.docs.service.db.dataSet.OldTableDataSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OldTableDAO {

    List<OldTableDataSet> getOldTableList() throws SQLException;

    Integer createDocsOldTable(Map<String, String[]> parameterMap) throws SQLException;

    Integer updateDocsOldTable(Map<String, String[]> parameterMap) throws SQLException;

}
