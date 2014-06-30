<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Students Queue</title>
	<link rel="stylesheet" href="lib/css/bootstrap.min.css">
	<link rel="stylesheet" href="lib/css/font-awesome.min.css">
	<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="lib/css/jquery-pnotify.css">
	<link rel="stylesheet" href="css/style.css">

	<script src="lib/js/jquery.js"></script>
	<script src="lib/js/jquery-pnotify.js"></script>
	<script src="lib/js/angular.js"></script>
	<script src="js/converter.js"></script>
	<script src="js/teacher.js"></script>
</head>
<body>
	<div ng-app='myApp'>
		<div ng-controller="TodoCtrl" >
			<div class='margin'>
				<h3>This is {{current_teacher.realName}}'s page </h3>
				<a href="" class="logout" href="">Logout</a>
			</div>
			<hr></hr>
			<!--
				<div >
					<input type="checkbox" class='margin center' ng-model="isStudent"></input>
					<span> student</span>
				</div>
			-->
			<div>
				<h4 class="margin">My queues:</h4>	            
				<div class="queues">
					<div ng-repeat="queue in queues | filter:isMyQueue()" class='well margin' ng-class="{'in-process': queue.inProcess == 'Y'}">
						<span>
							<i ng-show="queue.studentInQueueList.length" class="expand-queue icon-large icon-angle-down"></i>
						</span>
						<span class="lead">{{queue.name}}</span>
						<span class="lead">({{queue.studentInQueueList.length}})</span>
						<span class="lead reception-control">
							<label for="">Start reception</label>
							<input type="checkbox" ng-checked='queue.inProcess == "Y"' ng-click="toggleReception(queue)">
						</span>
						<span ng-click='removeQueue(queue)'>
							<i class="icon-remove pull-right" title="Delete this queue"></i>
						</span>
						<table class="table" ng-show="queue.studentInQueueList.length">
							<tr ng-show="queue.studentInQueueList.length">
								<th>Number</th>
								<th>Student</th>
								<th>Group</th>
							</tr>
							<tr ng-repeat="item in queue.studentInQueueList">
								<td>{{item.rank + 1}}</td>
								<td>{{item.student.realName}}</td>
								<td>{{item.student.groupName}}</td>
								<td ng-click='deleteStudentFromQueue(queue, item.student)'><i title="drop student from queue" class="icon-remove pull-right"></i></td>
							</tr>
						</table>
					</div>
				</div>
				<div class='margin add_queue'>	
					<div>
						<h4>New queue</h4>     
						<i class="icon-plus icon-large"></i> 
					</div>
					<div class="create_queue_form">
						<div>
							<label>Name</label>
							<input class="dropdown" 
								ng-model="newQueue.name"
								placeholder='MPI masters, 2'
							>
						</div>
						<div>
							<label>Description</label>
							<textarea
								ng-model="newQueue.desc"
								placeholder='Select queue'
							></textarea>
						</div>	
						<button class="btn add_queue_btn" ng-click='createNewQueue()'>Create queue</button>					
					</div>                     
				</div>	           
			</div>
		</div>
	</div>
</body>
</html>