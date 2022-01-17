<#import "parts/common.ftl" as c>
  <@c.page>
    <div id="oldTableContainer">
      <div class="oldTableSection">
        <label onclick="createForm()" style="outline: 2px solid #000;">(+) Add docs on this list</label>
      </div>
      <div class="oldTableSection">
        <table id="oldTable" style="text-align: center;">
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
                <td nowrap>${(docs.urlIn)!"-"}</td>
                <td>${(docs.genOrgNumb)!"-"}</td>
                <td>${(docs.genOrgDate)!"-"}</td>
                <td>${(docs.outputNumb)!"-"}</td>
                <td>${(docs.outputDate)!"-"}</td>
                <td nowrap>${(docs.fromOwner)!"-"}</td>
                <td>${(docs.inputDate)!"-"}</td>
                <td>${(docs.inputNumb)!"-"}</td>
                <td>${(docs.worker)!"-"}</td>
                <td>${(docs.handPass)!"-"}</td>
                <td>${(docs.answerComp)!"-"}</td>
                <td>${(docs.answerDate)!"-"}</td>
                <td>${(docs.answerNumb)!"-"}</td>
                <td nowrap>${(docs.urlOut)!"-"}</td>
                <td>${(docs.note)!"-"}</td>
              </tr>
              <#else>No message
            </#list>
          </tbody>
        </table>
      </div>
      <div class="oldTableSection">
      </div>
      <div id="formDiv" class="hidden" style="left: 65px; top: 54px; visibility: visible; position: absolute; overflow: visible; background: #fff;">
        <label onclick="updateForm()" style="outline: 2px solid #000;">(-) close</label>
        <form action="/oldtable" method="post">
          <table id="oldTableDocs">
            <thead>
              <tr>
                <th>Field</th>
                <th nowrap>Value</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><label for="id">ID</label></td>
                <td><input type="text" class="values" id="id" name="ID"></td>
              </tr>
              <tr>
                <td><label for="urlInput">URL_INPUT</label></td>
                <td><input type="text" class="values" id="urlInput" name="URL_INPUT"></td>
              </tr>
              <tr>
                <td><label for="gNumb">G_NUMB</label></td>
                <td><input type="text" class="values" id="gNumb" name="G_NUMB"></td>
              </tr>
              <tr>
                <td><label for="gDate">G_DATE</label></td>
                <td><input type="text" class="values" id="gDate" name="G_DATE"></td>
              </tr>
              <tr>
                <td><label for="outNumb">OUT_NUMB</label></td>
                <td><input type="text" class="values" id="outNumb" name="OUT_NUMB"></td>
              </tr>
              <tr>
                <td><label for="outDate">OUT_DATE</label></td>
                <td><input type="text" class="values" id="outDate" name="OUT_DATE"></td>
              </tr>
              <tr>
                <td><label for="owner">OWNER</label></td>
                <td><input type="text" class="values" id="owner" name="OWNER"></td>
              </tr>
              <tr>
                <td><label for="inDate">IN_DATE</label></td>
                <td><input type="text" class="values" id="inDate" name="IN_DATE"></td>
              </tr>
              <tr>
                <td><label for="inNumb">IN_NUMB</label></td>
                <td><input type="text" class="values" id="inNumb" name="IN_NUMB"></td>
              </tr>
              <tr>
                <td><label for="worker">WORKER</label></td>
                <td><input type="text" class="values" id="worker" name="WORKER"></td>
              </tr>
              <tr>
                <td><label for="handPass">HAND_PASS</label></td>
                <td><input type="text" class="values" id="handPass" name="HAND_PASS"></td>
              </tr>
              <tr>
                <td><label for="complete">COMPLETE</label></td>
                <td><input type="text" class="values" id="complete" name="COMPLETE"></td>
              </tr>
              <tr>
                <td><label for="completeDate">COMPLETE_DATE</label></td>
                <td><input type="text" class="values" id="completeDate" name="COMPLETE_DATE"></td>
              </tr>
              <tr>
                <td><label for="completeNumb">COMPLETE_NUMB</label></td>
                <td><input type="text" class="values" id="completeNumb" name="COMPLETE_NUMB"></td>
              </tr>
              <tr>
                <td><label for="urlOutput">URL_OUTPUT</label></td>
                <td><input type="text" class="values" id="urlOutput" name="URL_OUTPUT"></td>
              </tr>
              <tr>
                <td><label for="note">NOTE</label></td>
                <td><input type="text" class="values" id="note" name="NOTE"></td>
              </tr>
            </tbody>
          </table>
          <input type="submit" value="Enter">
        </form>
      </div>

    </div>
    <script>
      var oldTable = document.getElementById("oldTable");
      var formDiv = document.getElementById('formDiv');
      var inputValues = document.querySelectorAll('.values');

      function ready() {
        var rows = oldTable.getElementsByTagName("tr");
        for (let i = 0; i < rows.length; i++) {
          var currentRow = oldTable.rows[i];
          var createClickHandler = function(row) {
            return function() {
              var cell = row.getElementsByTagName("td")[0];
              var id = cell.innerHTML;
              var cells = row.getElementsByTagName("td");
              for (let i = 0; i < cells.length; i++) {
                // alert(cells[i].innerText);
                inputValues[i].value = cells[i].innerText == '-' ? '' : cells[i].innerText;
              }
              inputValues[0].disabled = false;
              formDiv.setAttribute('class', 'visible');
            };
          };

          currentRow.onclick = createClickHandler(currentRow);
        }
      }

      function updateForm() {
        formDiv.setAttribute('class', 'hidden');
        for (let i = 0; i < inputValues.length; i++) {
          inputValues[i].value = '';
        }
      }

      function createForm() {
        formDiv.setAttribute('class', 'visible');
        inputValues[0].disabled = true;
      }

      document.addEventListener("DOMContentLoaded", ready);
    </script>
  </@c.page>
