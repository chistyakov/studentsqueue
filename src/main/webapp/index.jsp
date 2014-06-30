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
</head>
<body>
	<div ng-app='myApp'>
		<div ng-controller="TodoCtrl" >
			<div class='margin'>
				<h3>This is {{current_student.realName}}'s page </h3>
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
				<div ng-show="isAnyActiveQueue()">
					<h4 class="margin">My queues:</h4>	            
					<div class="queues">
						<div ng-repeat="queue in queues | filter:isCurrentQueue()" class='well margin'  ng-class="{'in-process': queue.inProcess == 'Y'}">
							<span ng-click='expand(queue)'>
								<i class="expand-queue icon-large icon-angle-down"></i>
							</span>
							<span class="lead">{{queue.name}}</span>
							<span class="lead">(Total students: {{queue.studentInQueueList.length}})</span>
							<span ng-click='deleteStudentFromQueue(queue)'>
								<i class="icon-remove pull-right" title="Exit from the queue"></i>
							</span>
							<table class="table">
								<tr>
									<th>Number</th>
									<th>Student</th>
									<th>Group</th>
								</tr>
								<tr ng-repeat="item in queue.studentInQueueList">
									<td ng-class="{'current': item.student.id==current_student.id}" >{{item.rank + 1}}</td>
									<td ng-class="{'current': item.student.id==current_student.id}">{{item.student.realName}}</td>
									<td ng-class="{'current': item.student.id==current_student.id}">{{item.student.groupName}}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				
				<div class='margin add_queue'>	
					<div>
						<h4>Join a queue</h4>     
						<i class="icon-plus icon-large"></i> 
					</div>
					<div class="add_queue_form">
						<div>
							<label>Select teacher</label>
							<select class="dropdown" 
								ng-options="item.realName for item in teachers | filter:hasQueues()"
								ng-model="newTeacher"
								placeholder='Select teacher'
							></select>
						</div>
						<div ng-show="newTeacher">
							<div>
								<label>Select queue</label>
								<select class="dropdown" 
									ng-options="item.name for item in newTeacher.queues | filter:isNewQueue()"
									ng-model="newQueue"
									placeholder='Select queue'
								>
								</select>
							</div>
							<button ng-show="newQueue" class="btn add_queue_btn" ng-click='addStudentToQueue()'>Join the queue</button>
						</div>						
					</div>                     
				</div>	           
			</div>
		</div>
	</div>
	<script src="lib/js/jquery.js"></script>
	<script src="lib/js/jquery-pnotify.js"></script>
	<script src="lib/js/angular.js"></script>
	<script src="js/converter.js"></script>
	<script src="js/student.js"></script>
</body>
</html>