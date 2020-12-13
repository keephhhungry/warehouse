<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script src="jquery.js"></script>
    <div align="center">
        <input type="button" value="add artist" onclick="showAddButton()">
        <table border="1" cellspacing="0" id="table">
            <tr>
                <th>Artist ID</th>
                <th>Artist name</th>
                <th>Artist mobile number</th>
                <th>Artist gender</th>
                <th>operating</th>
            </tr>
        </table>
        <div id="shade" class="shade" style="display: none">
        </div>
        <div class="body1" id="body1" style="display: none">
            <div >
                <h6 align="center" id="body1Name">add artist</h6>
            </div>
            <div >
                <div>
                    Artist name: <input id="artistName" type="text"  placeholder="Artist name">
                </div>
                <div >
                    Artist mobile number:<input id="artistPhoneNum" type="text" placeholder="Artist mobile number">
                </div>
                <div >
                    Artist gender: <input id="artistGender" type="text" placeholder="Artist gender">
                </div>
            </div>
            <input id="body1Button" type="button"  value="确定"></input>
            <input  type="button" onclick = "cancle()" value="取消"></input>
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
            url:"artist?type=1",
            type:"GET",
            success:function(data){
                let artists = $.parseJSON(data);
                let table = document.getElementById("table");
                for(let i = 0 ; i < artists.length ; i++){
                    // 创建一个行元素
                    var row = document.createElement('tr');
                    // 创建td单元格
                    var artistId = document.createElement('td');
                    var artistName = document.createElement('td');
                    var artistPhoneNumber = document.createElement('td');
                    var artistGender = document.createElement('td');
                    var operating = document.createElement('td');
                    //赋值
                    artistId.innerHTML = artists[i].artistId;
                    artistName.innerHTML = artists[i].artistName;
                    artistPhoneNumber.innerHTML = artists[i].artistPhoneNumber;
                    artistGender.innerHTML = artists[i].artistGender;
                    operating.innerHTML = "<input type='button' value='modify' onclick='getSingleArtist("+ artists[i].artistId +")'> " +
                        "<input type='button' value='delete' onclick='deleteArtist("+ artists[i].artistId +")'>" +
                        "<input type='button' value='submit artwork' onclick='showAddArtwork("+ artists[i].artistId +")'>";
                    // 将新建的td加入新建的tr行
                    row.appendChild(artistId);
                    row.appendChild(artistName);
                    row.appendChild(artistPhoneNumber);
                    row.appendChild(artistGender);
                    row.appendChild(operating);
                    // 将tr加入table
                    table.appendChild(row);
                }
            }
        });

    //获取单个艺术家
    function getSingleArtist(artistId) {
        $("#body1Name").innerText= "edit artist";
        $("#body1Button").attr("onclick","updateArtist()");
        $.ajax({
            url:"artist?type=2&artistId="+artistId,
            type:"GET",
            success:function(data){
                let artist = $.parseJSON(data);
                $("#artistName").val(artist.artistName);
                $("#artistPhoneNum").val(artist.artistPhoneNumber);
                $("#artistGender").val(artist.artistGender);
                onlyId = artist.artistId;
                document.getElementById("shade").style.display = "block";
                document.getElementById("body1").style.display = "block";
            }
        });
    }

    //新增艺术家
    function addArtist(artistId) {
        let artistName = $("#artistName").val();
        let artistPhoneNum = $("#artistPhoneNum").val();
        let artistGender = $("#artistGender").val();
        $.ajax({
            url:"artist?type=3&artistName="+artistName+"&artistPhoneNumber="+artistPhoneNum+"&artistGender="+artistGender,
            type:"GET",
            success:function(data){
                alert("add success");
                document.location.reload();
            }
        });
    }

    //修改艺术家
    function updateArtist() {
        let artistName = $("#artistName").val();
        let artistPhoneNum = $("#artistPhoneNum").val();
        let artistGender = $("#artistGender").val();
        $.ajax({
            url:"artist?type=4&artistId="+onlyId+"&artistName="+artistName+"&artistPhoneNumber="+artistPhoneNum+"&artistGender="+artistGender,
            type:"GET",
            success:function(data){
               alert("modify success");
               document.location.reload();
            }
        });
    }

    //删除艺术家
    function deleteArtist(artistId) {
        $.ajax({
            url:"artist?type=5&artistId="+artistId,
            type:"GET",
            success:function(data){
               alert("delete success");
               document.location.reload();
            }
        });
    }

    function showAddButton() {
        $("#body1Name").innerText= "add artist";
        $("#body1Button").attr("onclick","addArtist()");
        document.getElementById("shade").style.display = "block";
        document.getElementById("body1").style.display = "block";
    }
    
    function cancle() {
        document.getElementById("shade").style.display = "none";
        document.getElementById("body1").style.display = "none";
        $("#artistName").val("");
        $("#artistPhoneNum").val("");
        $("#artistGender").val("");
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
                "&status="+ "not_sold"+
                "&ownType="+ "artist" +
                "&ownersId="+onlyId,
            type:"GET",
            success:function(data){
                alert("add success");
                document.location.reload();
            }
        });
    }

</script>
