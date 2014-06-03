<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Students Queue</title>
	<link rel="stylesheet" href="lib/css/bootstrap.min.css">
	<link rel="stylesheet" href="lib/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div ng-app='myApp'>
		<div ng-controller="TodoCtrl" >
			<div class='margin'>
				<h3>This is student's page <i class="icon-th-list pull-right"></i></h3>
			</div>
			<hr></hr>
			<!--
				<div >
					<input type="checkbox" class='margin center' ng-model="isStudent"></input>
					<span> student</span>
				</div>
			-->
			<div ng-show='isStudent'>
				<h4 class="margin">Queues list:</h4>	            
				<div class="queues">
					<div ng-repeat="queue in queues" class='well margin'>
						<span ng-click='expand(queue.id)'>
							<i ng-class="{'icon-angle-down': !queue.expanded, 'icon-angle-up': queue.expanded}"class="expand-queue icon-large"></i>
						</span>
						<span class="lead">{{queue.name}}</span>
						<span class="lead">({{queue.items.length}})</span>
						<span ng-click='deleteStudentToQueues(queue.id)'>
							<i class="icon-remove pull-right"></i>
						</span>
						<table class="table" ng-show='queue.expanded'>
							<tr>
								<th>Number</th>
								<th>Fio</th>
								<th>Description</th>
							</tr>
							<tr ng-repeat="item in queue.items| orderBy:'number'">
								<td ng-class="{'red': item.id==studentInfo.id}" >{{$index+1}}</td>
								<td ng-class="{'red': item.id==studentInfo.id}">{{item.fio}}</td>
								<td ng-class="{'red': item.id==studentInfo.id}">{{item.description}}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class='margin add_queue'>	
					<div>
						<h4>New queue</h4>     
						<i class="icon-plus icon-large"></i> 
					</div>
					<div class="add_queue_form">
						<div>
							<label>Select queues</label>
							<select class="dropdown" 
								ng-options="item.name for item in arrayQueues | filter:filterQueues()"
								ng-model="selectNewQueue"
								placeholder='Select queue'
							></select>
						</div>
						<div>	            			
							<label>Description</label>
							<textarea ng-model='description'></textarea>
						</div>
						<button class="btn add_queue_btn" ng-click='addStudentToQueues()'>Add queue</button>
					</div>                     
				</div>	           
			</div>
		</div>
	</div>
	<script src="lib/js/jquery.js"></script>
	<script src="lib/js/angular.js"></script>
	<script src="js/app.js"></script>
</body>
</html>