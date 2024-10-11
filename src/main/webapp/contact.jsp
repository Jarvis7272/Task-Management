<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Portfolio</title>
<link rel="stylesheet" href="Assets/css/profile.css">
</head>
<body>

	<!-- Profile Section -->
	<div class="profile-section">
		<img src="Assets/images/Hasan.jpg" alt="Profile"
			style="border-radius: 50%; width: 150px; height: 150px;">
		<h1>MD Hasan Raja</h1>
		<p>Motivated Computer Science graduate with a strong foundation in
			Java development and AWS implementation.Seeking to kickstart my
			career as a Java Developer with a focus on AWS.</p>
		<div class="buttons">
			<button onclick="location.href='#';">Resume</button>
			<button onclick="location.href='home';">Home</button>
			<button onclick="location.href='#skills';">My Skills</button>
			<button onclick="location.href='#projects';">Projects</button>
			<button onclick="location.href='#contact';">Contact</button>

		</div>
	</div>

	<!-- Projects Section -->
	<div class="table-container" id="projects">
		<h2>Projects</h2>
		<table>
			<tr>
				<th>Project Name</th>
				<th>Tech Stack</th>
				<th>Description</th>
				<th>Demo Link</th>
			</tr>
			<tr>
				<td>Employee Management System</td>
				<td>Java, JSP, MySQL, CSS</td>
				<td>A system for managing employees, leads, and tasks.</td>
				<td><a href="#">Live Demo</a></td>
			</tr>
			<tr>
				<td>Task Management App</td>
				<td>JavaScript, HTML, CSS, Bootstrap</td>
				<td>A web-based task management app designed for team
					collaboration.</td>
				<td><a href="#">Live Demo</a></td>
			</tr>
			<tr>
				<td>Banking Portal</td>
				<td>Java, JSP, MySQL</td>
				<td>Bank customer and employee management system with login
					module.</td>
				<td><a href="#">Live Demo</a></td>
			</tr>
		</table>
	</div>

	<!-- Skills Section -->
	<div class="skills-section" id="skills">
		<h2>Skills</h2>
		<div class="skill-item">
			<div class="circle">95%</div>
			<p>Core Java</p>
		</div>
		<div class="skill-item">
			<div class="circle">70%</div>
			<p>Java Development</p>
		</div>
		<div class="skill-item">
			<div class="circle">40%</div>
			<p>DSA</p>
		</div>
		<div class="skill-item">
			<div class="circle">60%</div>
			<p>AWS</p>
		</div>
		<div class="skill-item">
			<div class="circle">60%</div>
			<p>Frontend</p>
		</div>
	</div>

	<!-- Contact Section -->
	<div class="contact-section" id="contact">
		<div class="contact-details">
			<h3>ADDRESS</h3>
			<p>6th Main Road, BTM Layout, 2nd Stage, Bengaluru, India</p>
			<h3>PHONE</h3>
			<p>+91 8877859893</p>
			<h3>EMAIL</h3>
			<p>aahilraja523@gmail.com</p>
			<p>
				<a href="https://github.com/Jarvis7272" style="color: #2196f3;">Github</a>
				| <a href="https://leetcode.com/u/jarvis7272/"
					style="color: #2196f3;">Leetcode</a> | <a
					href="https://www.linkedin.com/in/md-hasan-raja-b4970516b/"
					style="color: #2196f3;">LinkedIn</a>
			</p>
		</div>
		<div class="contact-form">
			<form>
				<input type="text" name="name" placeholder="Name"><br>
				<input type="email" name="email" placeholder="Email"><br>
				<textarea name="message" placeholder="Message"></textarea>
				<br>
				<button type="submit">Send Message</button>
			</form>
		</div>
	</div>

</body>
</html>
