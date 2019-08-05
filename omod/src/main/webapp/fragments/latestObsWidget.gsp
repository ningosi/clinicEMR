<div>
    <div class="info-header">
    		<i class="icon-user-md"></i>
    		<h3>${ ui.message("latest observations").toUpperCase() }</h3>

    </div>
    <div class="info-body">
        <table>
            <% details.each { key, value -> %>
                <tr><th>Seen on: ${key}</th></tr>
                <tr><td>${value}</td></tr>

            <%}%>
        </table>

    </div>
</div>