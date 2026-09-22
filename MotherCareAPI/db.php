<?php

header("Content-Type: application/json; charset=UTF-8");

$host = "127.0.0.1";
$username = "root";
$password = "";
$database = "mothercare_db";
$port = 3308;

$conn = new mysqli($host, $username, $password, $database, $port);

if ($conn->connect_error) {
    http_response_code(500);
    echo json_encode([
        "success" => false,
        "message" => "Database connection failed: " . $conn->connect_error
    ]);
    exit;
}

$conn->set_charset("utf8mb4");
?>