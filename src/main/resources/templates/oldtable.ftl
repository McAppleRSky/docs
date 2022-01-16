<#import "parts/common.ftl" as c>
<@c.page>
<table id="oldTable">
<script>
  var table = document.getElementById("oldTable");

  function ready() {
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
      var currentRow = table.rows[i];
      var createClickHandler = function(row) {
        return function() {
          let cell = row.getElementsByTagName("td")[0];
          let id = cell.innerHTML;
          alert("id:" + id);
        };
      };
      currentRow.onclick = createClickHandler(currentRow);
    }
  }

  document.addEventListener("DOMContentLoaded", ready);
</script>
  <thead>
    <tr>
      <th>ID</th>
      <th>URL_INPUT</th>
      <th>G_NUMB</th>
      <th>G_DATE</th>
      <th>OUT_NUMB</th>
      <th>OUT_DATE</th>
      <th>OWNER</th>
      <th>IN_DATE</th>
      <th>IN_NUMB</th>
      <th>WORKER</th>
      <th>HAND_PASS</th>
      <th>COMPLETE</th>
      <th>COMPLETE_DATE</th>
      <th>COMPLETE_NUMB</th>
      <th>URL_OUTPUT</th>
      <th>NOTE</th>
    </tr>
  </thead>
  <tbody>
  <#list old_docs_tables as docs>
    <tr>
      <td>${(docs.id)!"-"}</td>
      <td>${(docs.urlIn)!"-"}</td>
      <td>${(docs.genOrgNumb)!"-"}</td>
      <td>${(docs.genOrgDate)!"-"}</td>
      <td>${(docs.outputNumb)!"-"}</td>
      <td>${(docs.outputDate)!"-"}</td>
      <td>${(docs.fromOwner)!"-"}</td>
      <td>${(docs.inputDate)!"-"}</td>
      <td>${(docs.inputNumb)!"-"}</td>
      <td>${(docs.worker)!"-"}</td>
      <td>${(docs.handPass)!"-"}</td>
      <td>${(docs.answerComp)!"-"}</td>
      <td>${(docs.answerDate)!"-"}</td>
      <td>${(docs.answerNumb)!"-"}</td>
      <td>${(docs.urlOut)!"-"}</td>
      <td>${(docs.note)!"-"}</td>
    </tr>
  <#else>No message</#list>
  </tbody>
</table>
</@c.page>
