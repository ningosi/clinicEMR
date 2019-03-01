<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeCss("reportingui", "reportsapp/home.css")
    def appFrameworkService = context.getService(context.loadClass("org.openmrs.module.appframework.service.AppFrameworkService"))
    def overview = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.southsudanemr.reports.overview")
    def monthly = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.southsudanemr.reports.monthly")
    def registers = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.southsudanemr.reports.registers")
    def contextModel = [:]
%>

<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {
            label: "${ ui.message("reportingui.reportsapp.home.title") }", link: "${ ui.pageLink("southsudanemr",
        "reportsHome")
}"
        }
    ];
</script>
<style>
.dashboard .info-container {
    width: 30%;
}
</style>
<h2>South Sudan Reports</h2>
<div class="dashboard clear">
    <div class="info-container column">
        <% if (overview) { %>
        <div class="info-section">
            <div class="info-header"><h3>Facility Reports</h3></div>

            <div class="info-body">
                <ul>
                    <% overview.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>

        <% if (registers) { %>
        <div class="info-section">
            <div class="info-header"><h3>Registers</h3></div>

            <div class="info-body">
                <ul>
                    <% registers.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>

    <div class="info-container column">
        <% if (monthly) { %>
        <div class="info-section">
            <div class="info-header"><h3>Monthly Reports</h3></div>

            <div class="info-body">
                <ul>
                    <% monthly.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>
</div>