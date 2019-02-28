
<p>
    <label>
        ${config.label}
    </label>
    <input type="text" id="dateToday" readonly />
</p>
<script type="text/javascript">
    var today = new Date();
    var day = today.getDate();
    if(day < 10){
        day ='0'+day;
    }
    var month = today.getMonth() + 1;
    if(month < 10){
        month = '0'+month;
    }
    var year = today.getFullYear();

    document.getElementById('dateToday').value = day+"-"+month+"-"+year ;
</script>