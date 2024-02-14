<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>account settings - Bootdey.com</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	background: #f5f5f5;
	margin-top: 20px;
}

.ui-w-80 {
	width: 80px !important;
	height: auto;
}

.btn-default {
	border-color: rgba(24, 28, 33, 0.1);
	background: rgba(0, 0, 0, 0);
	color: #4E5155;
}

label.btn {
	margin-bottom: 0;
}

.btn-outline-primary {
	border-color: #26B4FF;
	background: transparent;
	color: #26B4FF;
}

.btn {
	cursor: pointer;
}

.text-light {
	color: #babbbc !important;
}

.btn-facebook {
	border-color: rgba(0, 0, 0, 0);
	background: #3B5998;
	color: #fff;
}

.btn-instagram {
	border-color: rgba(0, 0, 0, 0);
	background: #000;
	color: #fff;
}

.card {
	background-clip: padding-box;
	box-shadow: 0 1px 4px rgba(24, 28, 33, 0.012);
}

.row-bordered {
	overflow: hidden;
}

.account-settings-fileinput {
	position: absolute;
	visibility: hidden;
	width: 1px;
	height: 1px;
	opacity: 0;
}

.account-settings-links .list-group-item.active {
	font-weight: bold !important;
}

html:not(.dark-style) .account-settings-links .list-group-item.active {
	background: transparent !important;
}

.account-settings-multiselect ~ .select2-container {
	width: 100% !important;
}

.light-style .account-settings-links .list-group-item {
	padding: 0.85rem 1.5rem;
	border-color: rgba(24, 28, 33, 0.03) !important;
}

.light-style .account-settings-links .list-group-item.active {
	color: #4e5155 !important;
}

.material-style .account-settings-links .list-group-item {
	padding: 0.85rem 1.5rem;
	border-color: rgba(24, 28, 33, 0.03) !important;
}

.material-style .account-settings-links .list-group-item.active {
	color: #4e5155 !important;
}

.dark-style .account-settings-links .list-group-item {
	padding: 0.85rem 1.5rem;
	border-color: rgba(255, 255, 255, 0.03) !important;
}

.dark-style .account-settings-links .list-group-item.active {
	color: #fff !important;
}

.light-style .account-settings-links .list-group-item.active {
	color: #4E5155 !important;
}

.light-style .account-settings-links .list-group-item {
	padding: 0.85rem 1.5rem;
	border-color: rgba(24, 28, 33, 0.03) !important;
}
</style>
</head>
<body>
	<div class="container light-style flex-grow-1 container-p-y">
		<h4 class="font-weight-bold py-3 mb-4">Account settings</h4>
		<div class="card overflow-hidden">
			<div class="row no-gutters row-bordered row-border-light">
				<div class="col-md-3 pt-0">
					<div class="list-group list-group-flush account-settings-links">
						<a class="list-group-item list-group-item-action active"
							data-toggle="list" href="#account-general">General</a> <a
							class="list-group-item list-group-item-action" data-toggle="list"
							href="#account-change-password">Change password</a> <a
							class="list-group-item list-group-item-action" data-toggle="list"
							href="#account-info">Order List</a>
					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">
						<div class="tab-pane fade active show" id="account-general">
							<div class="card-body media align-items-center">
								<img src="https://bootdey.com/img/Content/avatar/avatar1.png"
									alt class="d-block ui-w-80">
								<div class="media-body ml-4">
									<label class="btn btn-outline-primary"> Upload new
										photo <input type="file" class="account-settings-fileinput">
									</label> &nbsp;
									<button type="button" class="btn btn-default md-btn-flat">Reset</button>
									<div class="text-light small mt-1">Allowed JPG, GIF or
										PNG. Max size of 800K</div>
								</div>
							</div>
							<hr class="border-light m-0">
							<div class="card-body">
								<span style = "color:yellow;">${notification}</span>
								<form onsubmit ="return checkAddress();" action ="./updateinforcus" name="frm"   method = "post">
									<div class="form-group">
										<label class="form-label">Name</label> <input type="text"
											name = "name" class="form-control mb-1" value="${sessionScope.acc.tenkh}" required>
									</div>
									<div class="form-group">
										<label class="form-label">Phone</label> <input type="text"
											name ="phone" class="form-control" value="${sessionScope.acc.dienthoai} " required>
									</div>
									<div class="form-group">
										<label class="form-label">E-mail</label> <input type="text"
											name ="email"class="form-control mb-1" value="${sessionScope.acc.email}" readonly>
									</div>
									<div class = "form-group">
										<select class="form-select form-control form-select-sm mb-3" name ="city" id="city" aria-label=".form-select-sm">
										<option value="" selected>Chọn tỉnh thành</option>           
										</select>
										          
										<select class="form-select form-control form-select-sm mb-3" name="district" id="district" aria-label=".form-select-sm">
										<option value="" selected>Chọn quận huyện</option>
										</select>
										
										<select class="form-select form-control form-select-sm" name="ward" id="ward" aria-label=".form-select-sm">
										<option value = "" selected>Chọn phường xã</option>
										</select>
									 </div>
									 <div class = "form-group">
									 	<label class="form-label">Số nhà/Tên đường/...</label> <input type="text"
											id = "street_housenumber" name="street_housenumber" class="form-control mb-1">
									 </div>
									 <div class="form-group">
										<input type="text"
											name ="oldaddress" class="form-control mb-1" value="${sessionScope.acc.diachi}" readonly>
									</div>
									<div class="text-right mt-3">
										<button type="submit" class="btn btn-primary">Save changes</button>
											&nbsp;
										<button type="button" class="btn btn-default">Cancel</button>
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane fade" id="account-change-password">
							<div class="card-body pb-2">
								<form action="./updatepassword" name="frmp" method="post" id="myForm" onsubmit = "return checkPassword(event)">
									<div class="form-group">
										<label class="form-label">Current password</label> <input
										 id="curpassword" name="curpassword" type="password" class="form-control" required>
										 <span style="color:red;" id="mess"></span>
									</div>
									<div class="form-group">
										<label class="form-label">New password</label> <input
											id = "newpassword" name="newpassword" type="password" class="form-control" required>
									</div>
									<div class="form-group">
										<label class="form-label">Repeat new password</label> <input
											id = "repeatpassword" name="repeatpassword" type="password" class="form-control" required>
									</div>
									<div class="text-right mt-3">
										<button type="submit" class="btn btn-primary">Save changes</button>
											&nbsp;
										<button type="button" class="btn btn-default">Cancel</button>
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane fade" id="account-info">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>OD</th>
										<th>DATE</th>
										<th>Items</th>
										<th>Total</th>
										<th>Payment</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listO}" var = "o">
										<tr>
											<td>${o.madh}</td>
											<td>${o.ngay}</td>
											<td>${o.soluong}</td>
											<td>${o.tongtien}</td>
											<td>${o.thanhtoan}</td>
											<td>${o.trangthai}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script data-cfasync="false"
		src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
	<script>
		var citis = document.getElementById("city");
		var districts = document.getElementById("district");
		var wards = document.getElementById("ward");
		var Parameter = {
		  url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json", 
		  method: "GET", 
		  responseType: "application/json", 
		};
		var promise = axios(Parameter);
		promise.then(function (result) {
		  renderCity(result.data);
		});
	
		function renderCity(data) {
		  for (const x of data) {
		    citis.options[citis.options.length] = new Option(x.Name, x.Id);
		  }
		  citis.onchange = function () {
		    district.length = 1;
		    ward.length = 1;
		    if(this.value != ""){
		      const result = data.filter(n => n.Id === this.value);
	
		      for (const k of result[0].Districts) {
		        district.options[district.options.length] = new Option(k.Name, k.Id);
		      }
		    }
		  };
		  district.onchange = function () {
		    ward.length = 1;
		    const dataCity = data.filter((n) => n.Id === citis.value);
		    if (this.value != "") {
		      const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;
	
		      for (const w of dataWards) {
		        wards.options[wards.options.length] = new Option(w.Name, w.Id);
		      }
		    }
		  };
		}
		
		function checkAddress(){
			var st_nh = document.frm.street_housenumber.value;
			var ward = document.frm.ward.value;
				if(st_nh.trim().length == 0 && ward.trim().length !=0 || st_nh.trim().length != 0 && ward.trim().length ==0 ){
					alert("Ban hay nhap day du dia chi");
					return false;
				}
		}
		
		function checkPassword(event){
			event.preventDefault();
			var newp = document.getElementById("myForm").elements["newpassword"].value;
			var repeatp = document.getElementById("myForm").elements["repeatpassword"].value;
			
			var pwd = document.getElementById("curpassword").value
			var mess = document.getElementById("mess");
	    	var xhr = new XMLHttpRequest();
	    		  xhr.open("POST", "updatepassword", true);
	    		  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    		  xhr.onreadystatechange = function(){
	    		     if (xhr.readyState === 4 && xhr.status === 200){
	    		    	 var response = JSON.parse(xhr.responseText);
	    		    	 console.log(response.check);
	    		         if(response.check == false){
	    		        	mess.innerHTML = response.mess
	    		         }else{
	    		        	 if(newp != repeatp){
	    		 				alert("Mat khau phai trung nhau !");
	    		 			}else{
	    		 				document.getElementById("myForm").submit();
	    		 			}
	    		         }
	    		     }
	    		  };
	    		  xhr.send("currentpwd="+pwd+"&check=true");
		}
		</script>
</body>
</html>