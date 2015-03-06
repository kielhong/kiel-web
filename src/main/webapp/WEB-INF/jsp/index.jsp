<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Index</title>
</head>
<body>
<h1>Index</h1>
<ul>
  <li><a href="/retailer/">JPA findAll()</a></li>
  <li><a href="/retailer/1">JPA findOne()</a></li>
  <li><a href="/retailer/updated_time">JPA  @Query</a></li>
  <li><a href="/retailer/list_by_status?status=0">JPA Custom findByProperty</a></li>
  <li><a href="/retailer/count_by_status?status=1">JPA Custom countByProperty</a></li>
  <li><a href="/retailer/1/amounts">JPA findOne() + Stream</a></li>
  <li><a href="/retailer/page?page=0&amp;size=10">JPA findAll() + Pageable</a></li>
  <li><a href="/retailer/search_name?name=tar">JPA specification : Like</a></li>
  <li><a href="/retailer/search_createddate?beginDate=2014-05-10&endDate=2014-05-20">JPA specification : Between</a></li>
  <li><a href="/retailer/search_name_date?name=tar&beginDate=2014-05-10&endDate=2014-05-20">JPA specification : And</a></li>
  <li><a href="/retailer/search?name=tar&beginDate=2014-05-10&endDate=2014-05-20">JPA specification + pageable + Data Binding + Entity-View</a></li>
</ul>      
</body>
</html>

