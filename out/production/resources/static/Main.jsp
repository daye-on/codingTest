<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap core CSS -->
<link href="assets/dist/css/main.css" rel="stylesheet"> <!-- Custom styles for this template -->
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>
<div class="card div-main">
    <div class="div-userInfo">
        <h3>2024 코딩역량테스트</h3>
        <span><b>응시자 : </b><span th:text="${userInfo?.userName}"></span></span>
        <span class="mx-3"><b>학과 : </b><span th:text="${userInfo?.userMajor}"></span></span>
        <span class="mx-3"><b>레벨 : </b><span th:text="${userInfo?.userLevel}"></span></span>
        <input type="hidden" id="sessionId" th:value="${userInfo?.userId}"/>
    </div>
    <div class="text-right">
        <label id="timer"></label>
    </div>
    <hr>
    <div class="card-body">
        <h5 class="mb-3">주관식</h5>
        <div th:if="${#lists.isEmpty(sQuestion)}">
            <p>해당 레벨에서는 주관식 문제가 존재하지 않습니다.</p>
        </div>
        <div th:unless="${#lists.isEmpty(sQuestion)}">
            <th:block th:each="sq, sqq: ${sQuestion}" class="p-1 m-3">
                <label class="mb-3" th:text="|${sqq.count}. ${sq.sqQuestion}|"></label>
                <div th:unless="${#strings.isEmpty(sq.sqComment)}">
                    <span>◉ 조건 및 추가설명</span>
                    <pre class="p-3 w-50" style="border: solid 1px;" th:text="${sq.sqComment}"></pre>
                </div>
                <div th:if="${not #strings.isEmpty(sq.sqImg)}">
                    <img th:src="@{images/}+${sq.sqImg}" class="w-75 p-1"/>
                </div>
                <p><input type="text" th:id="${sqq.count}" th:seq="${sq.sqSeq}" class="p-1 w-50 mb-4"/></p>
            </th:block>
        </div>

        <hr>
        <br>
        <h5 class="mb-3">프로그래밍</h5>
        <th:block th:each="pq, pqq: ${pQuestion}" class="p-1 m-3">
            <label class="mb-3" th:text="|${pqq.count}. ${pq.pqQuestion}|"></label>
            <div th:if="${not #strings.isEmpty(pq.pqImg)}" class="mb-3">
                <span>◉ 참고 그림</span>
                <div style="border: solid 1px;">
                    <img th:src="@{images/}+${pq.pqImg}" class="w-75 p-1" />
                </div>
            </div>
            <div th:unless="${#strings.isEmpty(pq.pqComment1)}">
                <span>◉ 조건 및 추가설명</span>
                <pre class="p-3" style="border: solid 1px;" th:text="${pq.pqComment1}"></pre>
            </div>
            <div th:unless="${#strings.isEmpty(pq.pqComment2)}">
                <label>◉ 입출력 설명</label>
                <pre class="p-3" style="border: solid 1px;" th:text="${pq.pqComment2}"></pre>
            </div>
            <div class="row">
                <div class="col" th:unless="${#strings.isEmpty(pq.pqExInput)}">
                    <lable>◉ 입력 예제</lable>
                    <pre class="p-3" style="border: solid 1px;" th:text="${pq.pqExInput}"></pre>
                </div>
                <div class="col-5" th:unless="${#strings.isEmpty(pq.pqExOutput)}">
                    <lable>◉ 출력 예제</lable>
                    <pre class="p-3" style="border: solid 1px;" th:text="${pq.pqExOutput}"></pre>
                </div>
            </div>
            <p><textarea type="text" class="p-1 w-100" rows="10"
                         onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"
                         th:id="|pq_${pqq.count}|" th:seq="${pq.pqSeq}" th:text="${pq.savedResult}">
            </textarea></p>
            <input type="button" class="btn btn-outline-success float-right" th:id="|savePQ_${pqq.count}|"
                   value="답안 등록"/><br><br>
            <hr>
        </th:block>

        <br>
        <h5 class="mb-3">객관식</h5>
        <th:block th:each="q, qq:${question}" class="p-1 m-3">
            <label class="mb-3" th:text="|${qq.count}. ${q.mcqQuestion}|"></label>
            <div th:if="${not #strings.isEmpty(q.mcqImg)}">
                <img th:src="@{images/}+${q.mcqImg}" class="w-75 p-1"/>
            </div>
            <p><input type="radio" th:name="${qq.count}" th:seq="${q.mcqSeq}" th:text="| &#9312; ${q.mcqOption1}|"
                      value="1" class="p-1"/></p>
            <p><input type="radio" th:name="${qq.count}" th:seq="${q.mcqSeq}" th:text="| &#9313; ${q.mcqOption2}|"
                      value="2" class="p-1"/></p>
            <p><input type="radio" th:name="${qq.count}" th:seq="${q.mcqSeq}" th:text="| &#9314; ${q.mcqOption3}|"
                      value="3" class="p-1"/></p>
            <p><input type="radio" th:name="${qq.count}" th:seq="${q.mcqSeq}" th:text="| &#9315; ${q.mcqOption4}|"
                      value="4" class="p-1"/></p>
            <p><input type="radio" th:name="${qq.count}" th:seq="${q.mcqSeq}" th:text="| &#9316; ${q.mcqOption5}|"
                      value="5" class="p-1 mb-4"/></p>
        </th:block>

        <br>
    </div>
</div>
<input type="button" class="btn btn-primary my-2 float-right" id="submit" value="제출하기"/>
<script th:inline="javascript">
    $(document).ready(function () {
        function submitAction(status) {
            //객관식 문제
            let qSize = [[${question}]].length;
            let qString = "";

            for (let q = 1; q <= qSize; q++) {
                let question = document.getElementsByName(q.toString());
                let concatStr = "";

                let n = 0;
                while (n < 5) {
                    let checkSeq = question[n].getAttribute('seq');     //문제 번호
                    let qValue = question[n].getAttribute('value');     //답안 번호
                    let check = question[n].checked;                    //체크 여부

                    if (check) concatStr = "\"" + checkSeq + "\":\"" + qValue + "\",";
                    n++;
                }

                qString = qString.concat(concatStr);
            }

            //주관식 문제
            let sqSize = [[${sQuestion}]].length;
            let sqString = "";

            for (let sq = 1; sq <= sqSize; sq++) {
                let sqSeq = document.getElementById(sq.toString()).getAttribute('seq');
                let sQuestion = document.getElementById(sq.toString()).value;
                sqString = sqString.concat("\"" + sqSeq + "\":\"" + sQuestion + "\",");
            }


            //프로그래밍 문제
            let pqSize = [[${pQuestion}]].length;
            let pqString = "";

            for (let pq = 1; pq <= pqSize; pq++) {
                let pqSeqStr = "pq_" + pq;
                let pqSeq = document.getElementById(pqSeqStr).getAttribute('seq');
                let pqResult = document.getElementById(pqSeqStr).value;
                pqString = pqString.concat(pqSeq + ":" + pqResult + ",,");
            }


            qString = qString.substring(0, qString.lastIndexOf(","));
            sqString = sqString.substring(0, sqString.lastIndexOf(","));
            pqString = pqString.substring(0, pqString.lastIndexOf(",,"));

            $.ajax({
                type: "POST"
                , url: "/submitQuestion"
                , data: {
                    "mcqResult": "{" + qString + "}"
                    , "sqResult": "{" + sqString + "}"
                    , "pqResult": pqString + ""
                }
                , success: function (data) {
                    if (status == 0) alert("시험시간이 종료되었습니다. 입력된 답안만 제출됩니다.\n확인 버튼을 클릭하여 답안을 저장하세요.");
                    else alert("제출되었습니다.");

                    window.location.href = "/finish";
                }
            });
        }

        $("#submit").click(function () {
            $.ajax({
                type: "POST"
                , url: "/checkSubmitDt"
                , success: function (result) {
                    if(result){
                        alert('이미 제출한 답안이 존재합니다. 해당 답안은 제출되지 않습니다.');
                        location.href="/";
                    } else {
                        submitAction(1);
                    }

                }
            });
        });

        //프로그래밍 문제
        $("#savePQ_1").click(function () {
            savePQToggle(1);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_1").getAttribute('seq');
            let pqResult = document.getElementById("pq_1").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_1"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_2").click(function () {
            savePQToggle(2);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_2").getAttribute('seq');
            let pqResult = document.getElementById("pq_2").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_2"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_3").click(function () {
            savePQToggle(3);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_3").getAttribute('seq');
            let pqResult = document.getElementById("pq_3").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_3"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_4").click(function () {
            savePQToggle(4);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_4").getAttribute('seq');
            let pqResult = document.getElementById("pq_4").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_4"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_5").click(function () {
            savePQToggle(5);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_5").getAttribute('seq');
            let pqResult = document.getElementById("pq_5").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_5"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_6").click(function () {
            savePQToggle(6);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_6").getAttribute('seq');
            let pqResult = document.getElementById("pq_6").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_6"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_7").click(function () {
            savePQToggle(7);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_7").getAttribute('seq');
            let pqResult = document.getElementById("pq_7").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_7"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        $("#savePQ_8").click(function () {
            savePQToggle(8);
            if ($(this).val() == "답안 등록")
                return;

            let pqSeq = document.getElementById("pq_8").getAttribute('seq');
            let pqResult = document.getElementById("pq_8").value;

            $.ajax({
                type: "POST"
                , url: "/savePQ_8"
                , data: {
                    "pqSeq": pqSeq
                    , "pqResult": pqResult
                }
                , success: function (data) {
                    alert("임시저장 되었습니다.");
                }
            });
        });

        function savePQToggle(num) {
            let question = $("#pq_" + num);
            let saveBtn = $("#savePQ_" + num);

            if (saveBtn.hasClass("btn-outline-success")) {
                saveBtn.removeClass("btn-outline-success");
                saveBtn.addClass("btn-success");
                question.attr("disabled", true);
                saveBtn.val("답안 수정");
            } else {
                saveBtn.removeClass("btn-success");
                saveBtn.addClass("btn-outline-success");
                question.attr("disabled", false);
                saveBtn.val("답안 등록");
            }
        }

        //서버 시간
        let xmlHttpRequest;
        if(window.XMLHttpRequest){
            xmlHttpRequest = new XMLHttpRequest();
        } else if (window.ActiveXObject){
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } else {
            return ;
        }

        xmlHttpRequest.open('HEAD', window.location.href.toString(), false);
        xmlHttpRequest.setRequestHeader("ContentType", "text/html");
        xmlHttpRequest.send('');

        let serverDate = xmlHttpRequest.getResponseHeader("Date");
        let now = new Date(serverDate);

        let startDateNum = Date.parse(now) / 1000;
        let end = new Date([[${timer}]]);
        let endDateNum = Date.parse(end) / 1000;
        let time = endDateNum - startDateNum;

        let x = setInterval(function () {
            let min = parseInt(time / 60);
            let sec = time % 60;
            document.getElementById("timer").innerHTML = "<span>남은 시간: " + min + "분 " + sec + "초</span>";
            time--;

            if (min == 0 && sec == 0) {
                clearInterval(x);
                submitAction(0);
            }
        }, 1000);

        //세션 유지
        let session = setInterval(function () {
            $.ajax({
                type: "GET"
                , url: "/sessionConsole"
                , success: function (data) {
                    console.log("session -success");
                }, error: function () {
                    console.log("session -error");
                }
            });
        }, 60000);
    });
</script>
</body>
</html>