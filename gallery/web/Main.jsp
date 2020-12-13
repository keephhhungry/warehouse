<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <script src="jquery.js"></script>
    <body>
        <div align="center">
            <input type="button" value="Artist management" onclick="change(1)">
            <input type="button" value="Customer management" onclick="change(2)">
            <input type="button" value="Collector Management" onclick="change(3)">
            <input type="button" value="Sales management" onclick="change(5)">
            <input type="button" value="Salesperson commission view" onclick="change(4)">
            <input type="button" value="Gallery commission view" onclick="change(6)">
            <input type="button" value="Artist commission view" onclick="change(7)">
            <hr>
            <%--        <iframe id="iframe" align="center" width="100%"  src="./artist.jsp"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no">--%>
            <iframe id="iframe" width="100%" height="100%" src="./artist.jsp" frameborder="0" scrolling="no">
            </iframe>
        </div>
    </body>
</html>
<script type="text/javascript">
    function change(num) {
        var iframe = document.getElementById("iframe")
        if(num == 1){
            iframe.src = "./artist.jsp";
        }else if(num == 2){
            iframe.src = "./customer.jsp";
        }else if(num == 3){
            iframe.src = "./collector.jsp";
        }else if(num == 4){
            iframe.src = "./employee.jsp";
        }else if(num == 5){
            iframe.src = "./employeesale.jsp";
        }else if(num == 6){
            iframe.src = "./gallerysale.jsp";
        }else if(num == 7){
            iframe.src = "./artistsale.jsp";
        }
    }
</script>
