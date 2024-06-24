<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap core CSS -->
<link href="/assets/dist/css/scoreDetail.css" rel="stylesheet"> <!-- Custom styles for this template -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.css" /> <!-- Datatable -->
<script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.js"></script> <!-- Datatable -->
<head>
    <meta charset="UTF-8">
    <title>Score</title>
</head>
<body>
<div class="card div-score">
    <div class="div-userInfo">
        <h3>2023 코딩역량테스트 채점페이지</h3>
        <span><b>학과 : </b><span th:text="${user.userMajor}"></span></span>
        <span class="mx-3"><b>학번 : </b><span th:text="${user.userId}"></span></span>
        <span class="mx-3"><b>이름 : </b><span th:text="${user.userName}"></span></span>
        <input type="hidden" id="scoreSeq" th:value="${score.scoreSeq}"/>
    </div>
    <div class="card-body">
        <hr>
        <div class="mb-5">
            <h5>주관식 문제 답안</h5>
            <div class="float-right my-2">
                <input type="number" id="scoreSq" placeholder="주관식 점수 부여" th:value="${score.scoreSq}"/>
                <input type="button" id="scoreSqBtn" value="점수 등록"/>
            </div>
            <div th:if="${#lists.isEmpty(sQResult)}">
                <p>해당 레벨에서는 주관식 문제와 답안이 존재하지 않습니다.</p>
            </div>
            <table th:unless="${#lists.isEmpty(sQResult)}" class="table table-bordered">
                <th:block th:each="sqr : ${sQResult}">
                    <tr class="table-active">
                        <td class="col-2 font-weight-bold">문제</td>
                        <td th:text="${sqr.sqQuestion}"></td>
                    </tr>
                    <tr>
                        <td class="col-2 font-weight-bold">학생 답안</td>
                        <td th:text="${sqr.sqResult}"></td>
                    </tr>
                    <tr>
                        <td class="col-2 font-weight-bold">정답</td>
                        <td th:text="${sqr.sqAnswer}"></td>
                    </tr>
                </th:block>
            </table>
        </div>
        <hr>
        <div class="mb-5">
            <h5>프로그래밍 문제 답안</h5>
            <div class="float-right my-2">
                <input type="number" id="scorePq" placeholder="프로그래밍 점수 부여" th:value="${score.scorePq}"/>
                <input type="button" id="scorePqBtn" value="점수 등록"/>
            </div>
            <table class="table table-bordered">
                <th:block th:each="pqr : ${pQResult}">
                    <tr class="table-active">
                        <td class="col-2 font-weight-bold">문제</td>
                        <td th:text="${pqr.pQuestion.pqQuestion}"></td>
                    </tr>
                    <tr>
                        <td class="col-2 font-weight-bold">학생 답안</td>
                        <td>
                            <textarea type="text" class="p-1 w-100" rows="10" readonly
                                      onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"
                                      th:text="${pqr.pqResult}">
                            </textarea>
                        </td>
                    </tr>
                </th:block>
            </table>
        </div>
        <hr>
        <div class="mb-5">
            <h5>객관식 문제 답안</h5>
            <p th:text="|(해당 레벨에서 ${mcQCount}문제 출제되었습니다. 답안이 부족한 경우, 해당 문제에 대한 답안을 작성하지 않은 상태입니다.)|"></p>
            <div class="float-right my-2 text-right">
                <input type="number" id="scoreMcq" placeholder="객관식 점수 부여" th:value="${mcQResultScore}" disabled/>
                <input type="button" id="scoreMcqBtn" value="점수 등록" class="my-1" disabled/><br>
                <span>객관식 정답 개수 : <span th:text="${mcQResultCount}"></span> <small>(객관식 답안은 자동채점 되었으므로 수정이 불가합니다.)</small></span>
            </div>
            <table class="table table-bordered">
                <th:block th:each="mcqr : ${mcQResult}">
                    <tr class="table-active">
                        <td class="col-2 font-weight-bold">문제</td>
                        <td th:text="${mcqr.key}"></td>
                    </tr>
                    <tr>
                        <td class="col-2 font-weight-bold">학생 답안</td>
                        <td th:text="${mcqr.value}"></td>
                    </tr>
                </th:block>
            </table>
        </div>
    </div>
</div>
<div class="float-right">
    <button type="button" class="btn btn-primary my-2" id="backBtn">뒤로 가기</button>
    <button type="button" class="btn btn-success my-2" id="registerBtn">최종 점수 등록하기</button>
</div>
<script th:inline="javascript">
    $(document).ready(function () {
        let scoreSeq = $("#scoreSeq").val();

        $("#backBtn").click(function () {
            location.href="/score";
        });

        $("#scorePqBtn").click(function () {
            let scorePq = $("#scorePq").val();
            if(scorePq.length==0){
                alert("해당 답안의 점수를 입력해주세요.");
                return;
            }

            btnToggle("scorePq");
            $.ajax({
                type: "POST"
                , url: "/insertScorePq"
                , data: {
                    "scorePq": scorePq
                    , "scoreSeq": scoreSeq
                }
            })
        });

        $("#scoreSqBtn").click(function () {
            let scoreSq = $("#scoreSq").val();
            if(scoreSq.length==0){
                alert("해당 답안의 점수를 입력해주세요.");
                return;
            }

            btnToggle("scoreSq");
            $.ajax({
                type: "POST"
                , url: "/insertScoreSq"
                , data: {
                    "scoreSq": scoreSq
                    , "scoreSeq": scoreSeq
                }
            })
        });

        $("#scoreMcqBtn").click(function () {
            let scoreMcq = $("#scoreMcq").val();
            if(scoreMcq.length==0){
                alert("해당 답안의 점수를 입력해주세요.");
                return;
            }

            btnToggle("scoreMcq");
            $.ajax({
                type: "POST"
                , url: "/insertScoreMcq"
                , data: {
                    "scoreMcq": scoreMcq
                    , "scoreSeq": scoreSeq
                }
            });
        });

        $("#registerBtn").click(function () {
            let scoreMcq = $("#scoreMcq").val();
            let scoreSq = $("#scoreSq").val();
            let scorePq = $("#scorePq").val();
            let scoreAll = Number(scoreMcq)+Number(scoreSq)+Number(scorePq);

            let alarmStr = "최종 점수: ["+scoreAll+"]점을 등록하시겠습니까? " +
                "\n(주관식="+scoreSq+", 프로그래밍="+scorePq+", 객관식="+scoreMcq+")";

            let alarm = confirm(alarmStr);
            if(alarm){
                $.ajax({
                    type: "POST"
                    , url: "/registerScore"
                    , data: {
                        "scoreMcq": scoreMcq
                        , "scoreSq": scoreSq
                        , "scorePq": scorePq
                        , "scoreSeq": scoreSeq
                        , "scoreAll": scoreAll
                    }
                    , success: function (data) {
                        alert("채점 완료되었습니다.");
                        window.location.href = "/score";
                    }
                });
            }

        });

        function btnToggle(id) {
            let score = $("#" + id);
            let btn = $("#" + id+"Btn");

            if(score.attr("disabled")) {
                score.attr("disabled", false);
                btn.val("점수 등록");
            } else {
                score.attr("disabled", true);
                btn.val("점수 수정");
            }
        }
    });
</script>
</body>
</html>