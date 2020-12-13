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
                <th>Sales order ID</th>
                <th>Artwork name</th>
                <th>Sales revenue</th>
                <th>Sale time</th>
            </tr>
        </table>
    </div>
</body>
</html>
<script>
    let onlyId;
    $.ajax({
        url:"sale?type=3&incomeType=gallery",
        type:"GET",
        success:function(data){
            let incomes = $.parseJSON(data);
            let table = document.getElementById("table");
            for(let i = 0 ; i < incomes.length ; i++){
                // 创建一个行元素
                var row = document.createElement('tr');
                // 创建td单元格
                var incomeId = document.createElement('td');
                var artworkName = document.createElement('td');
                var receiveMoney = document.createElement('td');
                var createTime = document.createElement('td');
                //赋值
                incomeId.innerHTML = incomes[i].incomeId;
                artworkName.innerHTML = incomes[i].artworkName;
                receiveMoney.innerHTML = incomes[i].receiveMoney;
                createTime.innerHTML =timestampToTime(incomes[i].createTime);
                // 将新建的td加入新建的tr行
                row.appendChild(incomeId);
                row.appendChild(artworkName);
                row.appendChild(receiveMoney);
                row.appendChild(createTime);
                // 将tr加入table
                table.appendChild(row);
            }
        }
    });

    function timestampToTime(timestamp) {
        var  date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() + ' ';
        return Y+M+D;
    }

</script>
