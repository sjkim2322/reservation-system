$.ajax({
		url:'/api/categories',
		dataType:'json',
		type:'get',
	success:function(result) {
		 reprint(result);
	}
});

function reprint(result) {
	for(var i in result) {
		var node = createNewNode(result[i]);
		$("#categoryTable").append(node);
	}
}


function createNewNode(node) {
	var tr = $('<tr/>');
	var thId = $('<th/>',{
		text : "id"
	});
	var thName = $('<th/>',{
		text: "name"
	});
	var tdId = $('<td/>',{
		class:'categoryId',
		text: node.id
	});
	
	var tdName = $('<td/>',{
		class:'categoryName'
	});
	
	var inputName =$('<input/>',{
		type:"text",
		value:node.name
	})
	
	tdName.append(inputName);
	
	var updateTh = $('<th/>');

	var deleteTh = $('<th/>');

	var updatebutton = $('<button/>',{
		class:'update',
		text:'수정'
	});
	var deletebutton = $('<button/>',{
		class:'delete',
		text:'삭제',
		value:node.id
	});
	
	updateTh.append(updatebutton);
	deleteTh.append(deletebutton);
	
	tr.append(thId).append(tdId).append(tdId).append(thName).append(tdName).append(updateTh).append(deleteTh);


	return tr;
}


$('#insert').on('click',function() {
	var data = {}
	data.name = $('#insertName').val() ;
	$.ajax({
		url:'/api/categories',
		dataType:'json',
		headers: {
				'Content-Type': 'application/json'
			},
		data : JSON.stringify(data),
		type:'POST',
		processData:true,
 	 success:function(result) {
 		 $('#categoryTable').append(createNewNode(result));
	 }
	});
});

$('#categoryTable').on('click','.update',function() {
	var button=$(this);
	var id=button.parent().siblings('.categoryId').text();
	var name=button.parent().siblings('.categoryName').children().val();
	var data = {'id':id,'name':name}
	$.ajax({
		url:'/api/categories',
		contentType: 'application/json',
		data : JSON.stringify(data),
		type:'PUT',
 	 success:function() {
	 }
	});
});
$('#categoryTable').on('click','.delete',function() {
	var button=$(this);
	var data={'id' : $(this).val()}
	$.ajax({
		url:'/api/categories/'+$(this).val(),
		contentType: 'application/json',
		type:'DELETE',
 	 success:function() {
 		button.parents('tr').remove();
	 }
	});
});	
