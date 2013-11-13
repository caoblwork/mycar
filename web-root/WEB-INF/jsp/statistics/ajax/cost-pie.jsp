<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
@see http://www.highcharts.com/demo/pie-basic
--%>
{
        "chart": {
            "plotBackgroundColor": null,
            "plotBorderWidth": null,
            "plotShadow": true
        },
        "title": {
            "text": ""
        },
        "tooltip": {
        	"enabled": true,
    	    "pointFormat": "{series.name}: <b>{point.percentage:.1f}%</b>"
        },
        "plotOptions": {
            "pie": {
                "allowPointSelect": false,
                "cursor": "pointer",
                "dataLabels": {
                    "enabled": true,
                    "color": "#000000",
                    "connectorColor": "#000000",
                    "format": "<b>{point.name}</b>: {point.percentage:.1f} %"
                }
            }
        },
        "series": [{
            "type": "pie",
            "name": "share",
            "data": [
            <%--
                ["Firefox",   45.0],
                ["IE",       26.8],
                ["Chrome", 12.8],
                ["Safari",    8.5],
                ["Opera",     6.2],
            --%>
            <c:if test="${sum != 0}">
                ["燃油",   ${map['GAS']     / sum * 100}],
                ["停车",   ${map['PARKING'] / sum * 100}],
                ["洗车",   ${map['WASHING'] / sum * 100}],
                ["路桥",   ${map['TOLL']    / sum * 100}],
                ["罚款",   ${map['FINE']    / sum * 100}]
            </c:if>
            <c:if test="${sum == 0}">
            	["没有消费记录", 100]
            </c:if>
            ]
        }]
 }