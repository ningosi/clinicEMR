<%
    if (sessionContext.authenticated && !sessionContext.currentProvider) {
        throw new IllegalStateException("Logged-in user is not a Provider")
    }
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "handlebars/handlebars.min.js", Integer.MAX_VALUE - 1);
    ui.includeJavascript("uicommons", "navigator/validators.js", Integer.MAX_VALUE - 19)
    ui.includeJavascript("uicommons", "navigator/navigator.js", Integer.MAX_VALUE - 20)
    ui.includeJavascript("uicommons", "navigator/navigatorHandlers.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/navigatorModels.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/navigatorTemplates.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/exitHandlers.js", Integer.MAX_VALUE - 22);
    ui.includeJavascript("registrationapp", "registerPatient.js");
    ui.includeCss("registrationapp","registerPatient.css")
    def genderOptions = [ [label: ui.message("emr.gender.M"), value: 'M'],
                          [label: ui.message("emr.gender.F"), value: 'F'] ]
    Calendar cal = Calendar.getInstance()
    def maxAgeYear = cal.get(Calendar.YEAR)
    def minAgeYear = maxAgeYear - 120
    def minRegistrationAgeYear= maxAgeYear - 15 // do not allow backlog registrations older than 15 years
    def breadcrumbMiddle = breadcrumbOverride ?: '';
    def patientDashboardLink = patientDashboardLink ? ("/${contextPath}/" + patientDashboardLink) : ui.pageLink("coreapps", "clinicianfacing/patient")
    def identifierSectionFound = false
%>

<% if(includeFragments){
    includeFragments.each{ %>
${ ui.includeFragment(it.extensionParams.provider, it.extensionParams.fragment)}
<%   }
} %>

<!-- used within registerPatient.js -->
<%= ui.includeFragment("appui", "messages", [ codes: [
        'emr.gender.M',
        'emr.gender.F'
].flatten()
]) %>

${ ui.includeFragment("uicommons", "validationMessages")}

<style type="text/css">
.matchingPatientContainer .container {
    overflow: hidden;
}
.matchingPatientContainer .container div {
    margin: 5px 10px;
}
.matchingPatientContainer .container .name {
    font-size: 25px;
    display: inline-block;
}
.matchingPatientContainer .container .info {
    font-size: 15px;
    display: inline-block;
}
.matchingPatientContainer .container .identifiers {
    font-size: 15px;
    display:inline-block;
    min-width: 600px;
}
.matchingPatientContainer .container .identifiers .idName {
    font-size: 15px;
    font-weight: bold;
}
.matchingPatientContainer .container .identifiers .idValue {
    font-size: 15px;
    margin: 0 20px 0 0;
}
</style>

<script type="text/javascript">
    var breadcrumbs = _.compact(_.flatten([
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        ${ breadcrumbMiddle },
        { label: "${ ui.message("registrationapp.registration.label") }", link: "${ ui.pageLink("registrationapp", "registerPatient") }" }
    ]));
    var testFormStructure = "${formStructure}";
    var patientDashboardLink = '${patientDashboardLink}';
    var appId = '${ui.escapeJs(appId)}';
    // hack to create the sections variable used by the unknown patient handler in registerPatient.js
    var sections =  [];
    <% formStructure.sections.each { structure ->
            def section = structure.value;  %>
    sections.push('${section.id}');
    <% } %>
</script>