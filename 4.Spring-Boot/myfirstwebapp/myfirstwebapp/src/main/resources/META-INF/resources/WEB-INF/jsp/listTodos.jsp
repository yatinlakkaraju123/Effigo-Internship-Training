<%@ include file="common/header.jspf"   %>
		<%@ include file="common/navigation.jspf"   %>
		<div class="container">
			<h3> Here are all your todos </h3>
			<table class="table">
				<thead>
					<tr>
						
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${todos}" var="todo">

						<tr>
							
							<td>
								${todo.description}
							</td>
							<td>
								${todo.targetDate}
							</td>
							<td>
								${todo.done}
							</td>
							<td>
								<a href="update-todo?id=${todo.id}" class="btn btn-info">Update</a>
							</td>
							<td>
								<a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add-todos" class="btn btn-success">Add Todo</a>
		</div>

		<%@ include file="common/footer.jspf"   %>