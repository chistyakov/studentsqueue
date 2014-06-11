function prepareData(arrayOfObject) {
	var queues = arrayOfObject[0].data,
        students_in_queues = arrayOfObject[1].data,
        users = arrayOfObject[2].data;
    try {
        for (var i = 0; i < users.length; i++) {
            // set students's names
            for (var j = 0; j < students_in_queues.length; j++) {
                if (students_in_queues[j].studentId.userId === users[i].id) {

                    students_in_queues[j].studentName = users[i].realName;
                    students_in_queues[j].groupName = users[i].student.groupName;
                    students_in_queues[j].student_id = users[i].id;
                }                
            }
            // set teacher's name
            for (var j = 0; j < queues.length; j++) {
                if (queues[j].teacherId.userId === users[i].id) {
                    queues[j].teacherName = users[i].realName;
                    break;
                }
            }
        }
        console.log(students_in_queues);
        // set queue's students
        for (var i = 0; i < queues.length; i++) {
            queues[i].students = [];
            var max_rank = 0;
            for (var j = 0; j < students_in_queues.length; j++) {
                if (students_in_queues[j].queueId.id === queues[i].id) {
                    if (students_in_queues[j].rank > max_rank) {
                        max_rank = students_in_queues[j].rank;
                    }
                    queues[i].students.push(students_in_queues[j]);
                }
            }
            queues[i].max_rank = max_rank;
            queues[i].teacher_id = queues[i].teacherId.userId;
            // sort students in queue
            // convert DB rank to current applicetion rank (e.g 4, 5, 8, 9 -> 1, 2, 3, 4)
            queues[i].students.sort(function(a, b) {
                return a.rank - b.rank;
            });
        }
        console.log(queues);
        return queues;
    } catch(e) {
        $.pnotify({
            type: 'error',
            text: "Error while converting data. Error message: " + e.name,
            delay: 5000
        });
    }    
    
};