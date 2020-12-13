<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script src="jquery.js"></script>
    <div align="center">
        <input type="button" value="add customer" onclick="showAddButton()">
        <table border="1" cellspacing="0" id="table">
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Customer gender</th>
                <th>Customer phone</th>
                <th>Customer address</th>
                <th>operating</th>
            </tr>
        </table>
        <div id="shade" class="shade" style="display: none">
        </div>
        <div class="body1" id="body1" style="display: none">
            <div >
                <h6 align="center" id="body1Name">add customer</h6>
            </div>
            <div >
                <div>
                    Customer Name: <input id="CustomerName" type="text"  placeholder="Customer Name">
                </div>
                <div >
                    Customer gender: <input id="CustomerGender" type="text" placeholder="Customer gender">
                </div>
                <div >
                    Customer phone:<input id="CustomerPhoneNumber" type="text" placeholder="Customer phone">
                </div>
                <div >
                    Customer address:<input id="CustomerAddress" type="text" placeholder="Customer address">
                </div>
            </div>
            <input id="body1Button" type="button"  value="sure"></input>
            <input  type="button" onclick = "cancle()" value="cancle"></input>
        </div>
    </div>
</body>
</html>
<script>
    let onlyId;
    $.ajax({
        url:"customer?type=1",
        type:"GET",
        success:function(data){
            let Customers = $.parseJSON(data);
            let table = document.getElementById("table");
            for(let i = 0 ; i < Customers.length ; i++){
                // 创建一个行元素
                var row = document.createElement('tr');
                // 创建td单元格
                var CustomerId = document.createElement('td');
                var CustomerName = document.createElement('td');
                var CustomerPhoneNumber = document.createElement('td');
                var CustomerGender = document.createElement('td');
                var CustomerAddress = document.createElement('td');
                var operating = document.createElement('td');
                //赋值
                CustomerId.innerHTML = Customers[i].customerId;
                CustomerName.innerHTML = Customers[i].customerName;
                CustomerPhoneNumber.innerHTML = Customers[i].customerPhoneNumber;
                CustomerGender.innerHTML = Customers[i].customerGender;
                CustomerAddress.innerHTML = Customers[i].customerAddress;
                operating.innerHTML = "<input type='button' value='modify' onclick='getSingleCustomer("+ Customers[i].customerId +")'> " +
                    "<input type='button' value='delete' onclick='deleteCustomer("+ Customers[i].customerId +")'>";
                // 将新建的td加入新建的tr行
                row.appendChild(CustomerId);
                row.appendChild(CustomerName);
                row.appendChild(CustomerGender);
                row.appendChild(CustomerPhoneNumber);
                row.appendChild(CustomerAddress);
                row.appendChild(operating);
                // 将tr加入table
                table.appendChild(row);
            }
        }
    });

    //获取单个艺术家
    function getSingleCustomer(customerId) {
        $("#body1Name").innerText= "edit customer";
        $("#body1Button").attr("onclick","updateCustomer()");
        $.ajax({
            url:"customer?type=2&customerId="+customerId,
            type:"GET",
            success:function(data){
                let Customer = $.parseJSON(data);
                $("#CustomerName").val(Customer.customerName);
                $("#CustomerPhoneNumber").val(Customer.customerPhoneNumber);
                $("#CustomerGender").val(Customer.customerGender);
                $("#CustomerAddress").val(Customer.customerAddress);
                onlyId = Customer.customerId;
                document.getElementById("shade").style.display = "block";
                document.getElementById("body1").style.display = "block";
            }
        });
    }

    //新增艺术家
    function addCustomer() {
        let customerName = $("#CustomerName").val();
        let customerPhoneNumber = $("#CustomerPhoneNumber").val();
        let customerGender = $("#CustomerGender").val();
        let customerAddress = $("#CustomerAddress").val();
        $.ajax({
            url:"customer?type=3&customerName="+customerName+
                "&customerPhoneNumber="+customerPhoneNumber+
                "&customerGender="+customerGender+
                "&customerAddress="+customerAddress,
            type:"GET",
            success:function(data){
                alert("add success");
                document.location.reload();
            }
        });
    }

    //修改艺术家
    function updateCustomer() {
        let customerName = $("#CustomerName").val();
        let customerPhoneNumber = $("#CustomerPhoneNumber").val();
        let customerGender = $("#CustomerGender").val();
        let customerAddress = $("#CustomerAddress").val();
        $.ajax({
            url:"customer?type=4&customerId="+onlyId+
                "&customerName="+customerName+
                "&customerPhoneNumber="+customerPhoneNumber+
                "&customerGender="+customerGender+
                "&customerAddress="+customerAddress,
            type:"GET",
            success:function(data){
                alert("modify success");
                document.location.reload();
            }
        });
    }

    //删除艺术家
    function deleteCustomer(customerId) {
        $.ajax({
            url:"customer?type=5&customerId="+customerId,
            type:"GET",
            success:function(data){
                alert("delete success");
                document.location.reload();
            }
        });
    }

    function showAddButton() {
        $("#body1Name").innerText= "add customer";
        $("#body1Button").attr("onclick","addCustomer()");
        document.getElementById("shade").style.display = "block";
        document.getElementById("body1").style.display = "block";
    }

    function cancle() {
        document.getElementById("shade").style.display = "none";
        document.getElementById("body1").style.display = "none";
        $("#CustomerName").val("");
        $("#CustomerPhoneNumber").val("");
        $("#CustomerGender").val("");
        $("#CustomerAddress").val("");
    }

</script>
