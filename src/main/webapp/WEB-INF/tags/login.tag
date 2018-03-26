<%@attribute name="title"%>

<!DOCTYPE html>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous" />
    <title>${title} - Social portal</title>
</head>
<body class="bg-light p-4">
    <div id="body" class="container bg-white p-4">
        <jsp:doBody/>
    </div>
</body>
</html>