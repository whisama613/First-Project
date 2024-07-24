<%-- 
    Document   : Cart
    Created on : Feb 26, 2024, 4:53:40 PM
    Author     : whisa
--%>

<%@page import="java.util.Map"%>
<%@page import="DuyPH.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <% 
            //1. Cust goes to his/her cart place
            if(session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. All item is show
        %>
        <form action="DispatchServlet">
        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for (String key : items.keySet()) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%= ++count %>
                                                </td>
                                                <td>
                                                    <%= key %>
                                                </td>
                                                <td>
                                                    <%= items.get(key) %>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="chkItem"
                                                           value="<%= key %>" />
                                                </td>
                                            </tr>
                                <%
                                        }
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="product.html">Add more Book to your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Item" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
        </form>
        <%
                        return;
                    }
                }
            }
        %>
        
        <h2>
            <font color="red">
                No cart is existed !!!
            </font>
        </h2>
    </body>
</html>
