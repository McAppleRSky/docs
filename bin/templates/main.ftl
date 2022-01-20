<#import "parts/common.ftl" as c>
  <@c.page>
    <div id="mainContainer">
      <div class="mainSection">
        <label onclick="createForm()" style="outline: 2px solid #000;">(+) Add docs on this list</label>
      </div>
      <div class="mainSection">
        <table id="mainTable" style="text-align: center;">
          <thead>
            <tr>
              <th>${col_beautify[0]}</th>
              <th nowrap>${col_beautify[1]}</th>
              <th>${col_beautify[2]}</th>
              <th>${col_beautify[3]}</th>
              <th>${col_beautify[4]}</th>
              <th>${col_beautify[5]}</th>
              <th>${col_beautify[6]}</th>
              <th>${col_beautify[7]}</th>
              <th>${col_beautify[8]}</th>
              <th nowrap>${col_beautify[9]}</th>
              <th>${col_beautify[10]}</th>
              <th>${col_beautify[11]}</th>
              <th>${col_beautify[12]}</th>
              <th>${col_beautify[13]}</th>
              <th nowrap>${col_beautify[14]}</th>
              <th>${col_beautify[15]}</th>
            </tr>
          </thead>
          <tbody>
            <#list main_values as values>
              <tr>
                <td>${(values.id)!"-"}</td>
                <td nowrap>${(values.urlInput)!"-"}</td>
                <td>${(values.genOrgNumb)!"-"}</td>
                <td>${(values.genOrgDate)!"-"}</td>
                <td>${(values.outputNumb)!"-"}</td>
                <td>${(values.outputDate)!"-"}</td>
                <td nowrap>${(values.fromOwner)!"-"}</td>
                <td>${(values.inputDate)!"-"}</td>
                <td>${(values.inputNumb)!"-"}</td>
                <td>${(values.worker)!"-"}</td>
                <td>${(values.handPass)!"-"}</td>
                <td>${(values.answerComp)!"-"}</td>
                <td>${(values.answerDate)!"-"}</td>
                <td>${(values.answerNumb)!"-"}</td>
                <td nowrap>${(values.urlOutput)!"-"}</td>
                <td>${(values.note)!"-"}</td>
              </tr>
              <#else>No message
            </#list>
          </tbody>
        </table>
      </div>
      <div class="mainSection">
      </div>
      <div id="formDiv" class="hidden" style="left: 65px; top: 54px; visibility: visible; position: absolute; overflow: visible; background: #fff;">
        <label onclick="updateForm()" style="outline: 2px solid #000;">(-) close</label>
        <form action="/main" method="post">
          <table>
            <thead>
              <tr>
                <th>Field</th>
                <th nowrap>Value</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><label for="${col_texts[0]}">${col_beautify[0]}</label></td>
                <td><input type="text" class="values" id="${col_texts[0]}" name="${col_names[0]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[1]}">Inp.URL</label></td>
                <td><input type="text" class="values" id="${col_texts[1]}" name="${col_names[1]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[2]}">${col_beautify[2]}</label></td>
                <td><input type="text" class="values" id="${col_texts[2]}" name="${col_names[2]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[3]}">${col_beautify[3]}</label></td>
                <td><input type="text" class="values" id="${col_texts[3]}" name="${col_names[3]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[4]}">${col_beautify[4]}</label></td>
                <td><input type="text" class="values" id="${col_texts[4]}" name="${col_names[4]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[5]}">${col_beautify[5]}</label></td>
                <td><input type="text" class="values" id="${col_texts[5]}" name="${col_names[5]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[6]}">${col_beautify[6]}</label></td>
                <td><input type="text" class="values" id="${col_texts[0]}" name="${col_names[6]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[7]}">${col_beautify[7]}</label></td>
                <td><input type="text" class="values" id="${col_texts[7]}" name="${col_names[7]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[8]}">${col_beautify[8]}</label></td>
                <td><input type="text" class="values" id="${col_texts[8]}" name="${col_names[8]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[9]}">${col_beautify[9]}</label></td>
                <td><input type="text" class="values" id="${col_texts[9]}" name="${col_names[9]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[10]}">${col_beautify[10]}</label></td>
                <td><input type="text" class="values" id="${col_texts[10]}" name="${col_names[10]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[11]}">${col_beautify[11]}</label></td>
                <td><input type="text" class="values" id="${col_texts[11]}" name="${col_names[11]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[12]}">${col_beautify[12]}</label></td>
                <td><input type="text" class="values" id="${col_texts[12]}" name="${col_names[12]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[13]}">${col_beautify[13]}</label></td>
                <td><input type="text" class="values" id="${col_texts[13]}" name="${col_names[13]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[14]}">Outp.URL</label></td>
                <td><input type="text" class="values" id="${col_texts[14]}" name="${col_names[14]}"></td>
              </tr>
              <tr>
                <td><label for="${col_texts[15]}">${col_beautify[15]}</label></td>
                <td><input type="text" class="values" id="${col_texts[15]}" name="${col_names[15]}"></td>
              </tr>
            </tbody>
          </table>
          <input type="submit" value="Enter">
        </form>
      </div>

    </div>
    <script>
      var mainTable = document.getElementById("mainTable");
      var formDiv = document.getElementById('formDiv');
      var inputValues = document.querySelectorAll('.values');

      function ready() {
        var rows = mainTable.getElementsByTagName("tr");
        for (let i = 0; i < rows.length; i++) {
          var currentRow = mainTable.rows[i];
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
