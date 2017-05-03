<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Consulta de Usuarios</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>

</head>
<body>
<div class="container">
    <br />
    <h2 align="center">Consulta de Usuarios</h2><br />
    <div class="form-group">
        <div class="input-group">
            <span class="input-group-addon">Busqueda</span>
            <input type="text" name="search_text" id="search_text" placeholder="Busqueda por nombre de Usuarios" class="form-control" />
        </div>
    </div>
    <br />
    <div id="result"></div>

</div>
</body>
</html>


<script>
    $(document).ready(function(){

        load_data();

        function load_data(query)
        {
            $.ajax({
                url:"fetch.php",
                method:"POST",
                data:{query:query},
                success:function(data)
                {
                    $('#result').html(data);
                }
            });
        }
        $('#search_text').keyup(function(){
            var search = $(this).val();
            if(!(search == ''))
            {
                load_data(search);
            }
            else
            {
                load_data();
            }
        });
    });
</script>