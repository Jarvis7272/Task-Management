<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login Form</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet" />
<link href="Assets/css/login-style.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<div class="form-box login-form">
			<img
				src="https://cdni.iconscout.com/illustration/free/thumb/free-task-list-2080780-1753768.png" />
			<div class="form-content">
				<h2>EMPLOYEE LOGIN</h2>

				<form action="login" method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="email"
							placeholder="Email Address" required />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="password"
							placeholder="Password" required />
					</div>
					<div class="btn-group mx-auto">
						<button type="submit" class="btn">
							Login <i class="fas fa-arrow-right"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="Assets/js/script.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
