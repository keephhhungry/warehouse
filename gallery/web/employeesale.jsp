<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script src="jquery.js"></script>
    <div align="center">
        <table border="1" cellspacing="0" id="table">
            <tr>
                <th>Artwork ID</th>
                <th>Artwork name</th>
                <th>Art price</th>
                <th>Artwork description</th>
                <th>Artwork type</th>
                <th>Artwork status</th>
            </tr>
        </table>
        <div id="shade" class="shade" style="display: none">
        </div>
        <div class="body1" id="body1" style="display: none">
            <div >
                <h6 align="center" id="body1Name">Fill in the purchase information</h6>
            </div>
            <div >
                <div>
                    Purchaser's name: <input id="customerName" type="text"  placeholder="Purchaser's name">
                </div>
                <div >
                    Salesperson's name:<input id="employeeName" type="text" placeholder="Salesperson's name">
                </div>
            </div>
            <input id="body1Button" type="button" onclick = "sale()" value="sure"></input>
            <input  type="button" onclick = "cancle()" value="cancle"></input>
        </div>
    </div>
</body>
</html>
<script>
    let onlyId;
    $.ajax({
        url:"artwork?type=1",
        type:"GET",
        success:function(data){
            let artworks = $.parseJSON(data);
            let table = document.getElementById("table");
            for(let i = 0 ; i < artworks.length ; i++){
                // 创建一个行元素
                var row = document.createElement('tr');
                // 创建td单元格
                var artworkId = document.createElement('td');
                var artworkName = document.createElement('td');
                var price = document.createElement('td');
                var description = document.createElement('td');
                var type = document.createElement('td');
                // var dateReceived = document.createElement('td');
                var status = document.createElement('td');
                var operating = document.createElement('td');
                //赋值
                artworkId.innerHTML = artworks[i].artworkId;
                artworkName.innerHTML = artworks[i].artworkName;
                price.innerHTML = artworks[i].price;
                description.innerHTML = artworks[i].description;
                type.innerHTML = artworks[i].type;
                // dateReceived.innerHTML = artworks[i].dateReceived;
                status.innerHTML = artworks[i].status;
                // operating.innerHTML = "<input type='button' value='购买' onclick='showSaleButton("+ artworks[i].artworkId +","+  artworks[i].status+ ")'> ";
                if(artworks[i].status =="not_sold"){
                    operating.innerHTML = "<input type='button' value='buy' onclick='showSaleButton("+ artworks[i].artworkId + ")'> ";
                }else{
                    operating.innerHTML = "<input type='button' value='buy' disabled='disabled'> ";
                }
                // 将新建的td加入新建的tr行
                row.appendChild(artworkId);
                row.appendChild(artworkName);
                row.appendChild(price);
                row.appendChild(description);
                row.appendChild(type);
                // row.appendChild(dateReceived);
                row.appendChild(status);
                row.appendChild(operating);
                // 将tr加入table
                table.appendChild(row);
            }
        }
    });

    function showSaleButton(artworkId,status) {
        onlyId = artworkId;
        document.getElementById("shade").style.display = "block";
        document.getElementById("body1").style.display = "block";
    }

    function sale() {
        let customerName = $("#customerName").val();
        let exmployeeName = $("#employeeName").val();
        $.ajax({
            url:"sale?type=1&customerName="+customerName+
                "&exmployeeName="+exmployeeName+
                "&artworkId="+onlyId,
            type:"GET",
            success:function(data){
                alert("Successful purchase");
                document.location.reload();
            }
        });
    }

    function cancle() {
        document.getElementById("shade").style.display = "none";
        document.getElementById("body1").style.display = "none";
    }

</script>
