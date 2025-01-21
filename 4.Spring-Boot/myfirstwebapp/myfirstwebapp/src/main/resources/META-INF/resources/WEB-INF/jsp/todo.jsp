<%@ include file="common/header.jspf"   %>
            <%@ include file="common/navigation.jspf"   %>
            <div class="container">
                <h3> Add todo here </h3>
                <form:form method="post" modelAttribute="todo">
                    <fieldset class="mb-3">
                        <form:label path="description">Description</form:label>
                        <form:input type="text" path="description" required="required" />
                        <form:errors path="description" cssClass="text-warning" />

                    </fieldset>
                    <fieldset class="mb-3">
                        <form:label path="targetDate">Target Date</form:label>
                        <form:input type="text" path="targetDate" required="required" />
                        <form:errors path="targetDate" cssClass="text-warning" />

                    </fieldset>
                    <form:input type="hidden" path="id" required="required" />
                    <form:input type="hidden" path="done" required="required" />
                    <button type="submit" class="btn btn-success">Submit</button>

                </form:form>
            </div>
            <script type="text/javascript">
                $('#targetDate').datepicker({
    format: 'yyyy-mm-dd',
   
});
            </script>
            <%@ include file="common/footer.jspf"   %>
       