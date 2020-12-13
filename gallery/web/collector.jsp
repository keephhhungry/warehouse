<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script src="jquery.js"></script>
    <div align="center">
        <input type="button" value="add collector" onclick="showAddButton()">
        <table border="1" cellspacing="0" id="table">
            <tr>
                <th>Collector ID</th>
                <th>Collection name</th>
                <th>Collector mobile number</th>
                <th>Collector gender</th>
                <th>operating</th>
            </tr>
        </table>
        <div id="shade" class="shade" style="display: none">
        </div>
        <div class="body1" id="body1" style="display: none">
            <div >
                <h6 align="center" id="body1Name">add collector</h6>
            </div>
            <div >
                <div>
                    Collection name: <input id="collectorName" type="text"  placeholder="Collection name">
                </div>
                <div >
                    Collector mobile number:<input id="collectorPhoneNum" type="text" placeholder="Collector mobile number">
                </div>
                <div >
                    Collector gender: <input id="collectorGender" type="text" placeholder="Collector gender">
                </div>
            </div>
            <input id="body1Button" type="button"  value="sure"></input>
            <input  type="button" onclick = "cancle()" value="cancle"></input>
        </div>
        <div class="body2" id="body2" style="display: none">
            <div >
                <h6 align="center" id="body2Name">submit artwork</h6>
            </div>
            <div >
                <div>
                    Artwork name: <input id="artworkName" type="text"  placeholder="Artwork name">
                </div>
                <div >
                    Artwork price:<input id="price" type="text" placeholder="Artwork price">
                </div>
                <div >
                    Artwork description: <input id="description" type="text" placeholder="Artwork description">
                </div>
                <div >
                    Artwork type: <input id="type" type="text" placeholder="Artwork type">
                </div>
            </div>
            <input id="body2Button" type="button" onclick="addArtwork()"  value="sure"></input>
            <input  type="button" onclick = "cancle()" value="cancle"></input>
        </div>
    </div>
</body>
</html>
<script>
        let onlyId;
        $.ajax({
            url:"collector?type=1",
            type:"GET",
            success:function(data){
                let collectors = $.parseJSON(data);
                let table = document.getElementById("table");
                for(let i = 0 ; i < collectors.length ; i++){
                    // 创建一个行元素
                    var row = document.createElement('tr');
                    // 创建td单元格
                    var collectorId = document.createElement('td');
                    var collectorName = document.createElement('td');
                    var collectorPhoneNumber = document.createElement('td');
                    var collectorGender = document.createElement('td');
                    var operating = document.createElement('td');
                    //赋值
                    collectorId.innerHTML = collectors[i].collectorId;
                    collectorName.innerHTML = collectors[i].collectorName;
                    collectorPhoneNumber.innerHTML = collectors[i].collectorPhoneNumber;
                    collectorGender.innerHTML = collectors[i].collectorGender;
                    operating.innerHTML = "<input type='button' value='modify' onclick='getSingleCollector("+ collectors[i].collectorId +")'> " +
                        "<input type='button' value='delete' onclick='deleteCollector("+ collectors[i].collectorId +")'>" +
                        "<input type='button' value='submit artwork' onclick='showAddArtwork("+ collectors[i].collectorId +")'>";
                    // 将新建的td加入新建的tr行
                    row.appendChild(collectorId);
                    row.appendChild(collectorName);
                    row.appendChild(collectorPhoneNumber);
                    row.appendChild(collectorGender);
                    row.appendChild(operating);
                    // 将tr加入table
                    table.appendChild(row);
                }
            }
        });

    //获取单个收藏家
    function getSingleCollector(collectorId) {
        $("#body1Name").innerText= "edit collector";
        $("#body1Button").attr("onclick","updateCollector()");
        $.ajax({
            url:"collector?type=2&collectorId="+collectorId,
            type:"GET",
            success:function(data){
                let collector = $.parseJSON(data);
                $("#collectorName").val(collector.collectorName);
                $("#collectorPhoneNum").val(collector.collectorPhoneNumber);
                $("#collectorGender").val(collector.collectorGender);
                onlyId = collector.collectorId;
                document.getElementById("shade").style.display = "block";
                document.getElementById("body1").style.display = "block";
            }
        });
    }

    //新增收藏家
    function addCollector(collectorId) {
        let collectorName = $("#collectorName").val();
        let collectorPhoneNum = $("#collectorPhoneNum").val();
        let collectorGender = $("#collectorGender").val();
        $.ajax({
            url:"collector?type=3&collectorName="+collectorName+"&collectorPhoneNumber="+collectorPhoneNum+"&collectorGender="+collectorGender,
            type:"GET",
            success:function(data){
                alert("add success");
                document.location.reload();
            }
        });
    }

    //修改收藏家
    function updateCollector() {
        let collectorName = $("#collectorName").val();
        let collectorPhoneNum = $("#collectorPhoneNum").val();
        let collectorGender = $("#collectorGender").val();
        $.ajax({
            url:"collector?type=4&collectorId="+onlyId+"&collectorName="+collectorName+"&collectorPhoneNumber="+collectorPhoneNum+"&collectorGender="+collectorGender,
            type:"GET",
            success:function(data){
               alert("modify success");
               document.location.reload();
            }
        });
    }

    //删除收藏家
    function deleteCollector(collectorId) {
        $.ajax({
            url:"collector?type=5&collectorId="+collectorId,
            type:"GET",
            success:function(data){
               alert("delete success");
               document.location.reload();
            }
        });
    }

    function showAddButton() {
        $("#body1Name").innerText= "add collector";
        $("#body1Button").attr("onclick","addCollector()");
        document.getElementById("shade").style.display = "block";
        document.getElementById("body1").style.display = "block";
    }
    
    function cancle() {
        document.getElementById("shade").style.display = "none";
        document.getElementById("body1").style.display = "none";
        $("#collectorName").val("");
        $("#collectorPhoneNum").val("");
        $("#collectorGender").val("");
    }

        function showAddArtwork(artistId){
            onlyId = artistId;
            document.getElementById("shade").style.display = "block";
            document.getElementById("body2").style.display = "block";
        }

        function addArtwork(){
            let artworkName = $("#artworkName").val();
            let price = $("#price").val();
            let description = $("#description").val();
            let type = $("#type").val();
            let dateReceived = $("#dateReceived").val();
            let status = $("#status").val();
            $.ajax({
                url:"artwork?type=2&artworkName="+artworkName+
                    "&price="+price+
                    "&artworkName="+artworkName+
                    "&description="+description+
                    "&type="+type+
                    "&status="+ " not_for_sell"+
                    "&ownType="+ "collector" +
                    "&ownersId="+onlyId,
                type:"GET",
                success:function(data){
                    alert("add success");
                    document.location.reload();
                }
            });
        }

</script>
